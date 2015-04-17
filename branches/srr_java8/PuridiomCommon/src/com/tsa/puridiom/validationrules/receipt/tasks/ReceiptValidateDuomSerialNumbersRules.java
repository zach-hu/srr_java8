package com.tsa.puridiom.validationrules.receipt.tasks;

import com.tsa.puridiom.commodity.CommodityManager;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Commodity;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiptValidateDuomSerialNumbersRules extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
			String oid = (String)incomingRequest.get("organizationId");
			String receiptMethod = HiltonUtility.ckNull((String)incomingRequest.get("receiptMethod"));

			String validDuom = "Y";
			String validDuomLines = "";
			String validSerialNumbers = "Y";
			String validSerialNumbersLines = "";

			boolean isShipToInv = false;
			ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
			if (receiptHeader != null) {
				isShipToInv = receiptHeader.getShipToInv().equalsIgnoreCase("Y");
			}

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("invproperty-retrieve-by-icrecline.xml");

			List receiptLineList = (List)incomingRequest.get("receiptLineList");

			if (receiptLineList != null && receiptLineList.size() > 0)
			{
				for (int i = 0; i < receiptLineList.size(); i++)
				{
					ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);

					if (receiptLine != null)
					{
						boolean isItemSourceINV = false;
						String commodityCode = "";

						PoLine poLine = receiptLine.getPoLine();
						RequisitionLine requisitionLine = receiptLine.getRequisitionLine();

						if (receiptMethod.equalsIgnoreCase("ReceiveByOrder") && poLine != null) {
							isItemSourceINV = poLine.getItemSource().equalsIgnoreCase("INV");
							commodityCode = poLine.getCommodity();
						}
						if (receiptMethod.equalsIgnoreCase("ReceiveByTransfer") && requisitionLine != null) {
							isItemSourceINV = requisitionLine.getItemSource().equalsIgnoreCase("INV");
							commodityCode = requisitionLine.getCommodityCode();
						}

						boolean hasInvLocation = false;
						if (receiptHeader != null && receiptLine.getItemLocation().equalsIgnoreCase(receiptHeader.getShipToCode())) {
							hasInvLocation = true;
						}

						if (isShipToInv && isItemSourceINV && !hasInvLocation) {
							continue;
						}

						if (!HiltonUtility.isEmpty(commodityCode))
						{
							Commodity commodity = CommodityManager.getInstance().getCommodity(oid, commodityCode);

							if (commodity != null)
							{
								boolean duomRequired = commodity.getDuomRequired().equalsIgnoreCase("Y");
								boolean serialNumbers = commodity.getSerialNumbersRequired().equalsIgnoreCase("Y");

								if (duomRequired)
								{
									BigDecimal qtyReceived = receiptLine.getQtyReceived();
									if (qtyReceived.compareTo(new BigDecimal(0)) > 0)
									{
										BigDecimal duomQtyReceived = receiptLine.getDuomQtyReceived();
										if (duomQtyReceived.compareTo(new BigDecimal(0)) <= 0)
										{
											validDuom = "N";
											if (HiltonUtility.isEmpty(validDuomLines)) {
												validDuomLines = String.valueOf(i + 1);
											} else {
												validDuomLines = validDuomLines + ", " + String.valueOf(i + 1);
											}
										}
									}
								}

								if (serialNumbers)
								{
									int invPropertyCount = 0;

									Map retrieveParameters = new HashMap();
									retrieveParameters.put("dbsession", incomingRequest.get("dbsession"));
									retrieveParameters.put("organizationId", incomingRequest.get("organizationId"));
									retrieveParameters.put("InvProperty_icRecLine", receiptLine.getIcRecLine().toString());

									process.executeProcess(retrieveParameters);

									if (process.getStatus() == Status.SUCCEEDED)
									{
										List invPropertyList = (List)retrieveParameters.get("invPropertyList");
										if (invPropertyList != null) {
											invPropertyCount = invPropertyList.size();
										}
									}

									BigDecimal qtyReceived = receiptLine.getQtyReceived();
									if (qtyReceived.compareTo(new BigDecimal(invPropertyCount)) != 0)
									{
										validSerialNumbers = "N";
										if (HiltonUtility.isEmpty(validSerialNumbersLines)) {
											validSerialNumbersLines = String.valueOf(i + 1);
										} else {
											validSerialNumbersLines = validSerialNumbersLines + ", " + String.valueOf(i + 1);
										}
									}
								}
							}
						}
					}
				}
			}

			incomingRequest.put("validDuom", validDuom);
			incomingRequest.put("validDuomLines", validDuomLines);
			incomingRequest.put("validSerialNumbers", validSerialNumbers);
			incomingRequest.put("validSerialNumbersLines", validSerialNumbersLines);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("An Error occurred at ReceiptValidateDuomSerialNumbersRules", e);
		}
		return null;
	}
}
