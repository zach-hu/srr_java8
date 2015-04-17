package com.tsa.puridiom.receiptline.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

public class ReceiptLineCreateFromReqLineList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List receiptLineList = new ArrayList();

			String userId = (String) incomingRequest.get("userId");
            String organizationId = (String)incomingRequest.get("organizationId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
			String userDateFormat = (String) incomingRequest.get("userDateFormat");
			String reqType = "";

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
            }
			List requisitionLineList = (List)incomingRequest.get("requisitionLineList");
			ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
			RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");

			if (requisitionHeader != null) {
				reqType = requisitionHeader.getRequisitionType();
			}

			if (receiptHeader == null) {
				receiptHeader = new ReceiptHeader();
			}
			String today = Dates.today(userDateFormat, userTimeZone);

			if (requisitionLineList != null && requisitionLineList.size() > 0)
			{
				for (int i=0; i<requisitionLineList.size(); i++)
				{
					RequisitionLine requisitionLine = (RequisitionLine)requisitionLineList.get(i);

					PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
					PuridiomProcess process = processLoader.loadProcess("receiptline-create-from-reqline.xml");

					Map newIncomingRequest = new HashMap();
					newIncomingRequest.put("userId", incomingRequest.get("userId"));
					newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
					newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
					newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));

					UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
					newIncomingRequest.put("ReceiptLine_icRecLine", ukg.getUniqueKey().toString());
					newIncomingRequest.put("ReceiptLine_icLineHistory", requisitionLine.getIcLineHistory().toString());
					newIncomingRequest.put("ReceiptLine_icRecHeader", (String)incomingRequest.get("ReceiptHeader_icRecHeader"));
					newIncomingRequest.put("ReceiptLine_receiptNumber", (String)incomingRequest.get("ReceiptHeader_receiptNumber"));
					newIncomingRequest.put("ReceiptLine_receiptStatus", receiptHeader.getReceiptStatus());
					newIncomingRequest.put("ReceiptLine_receiptType", (String)incomingRequest.get("ReceiptHeader_receiptType"));
					newIncomingRequest.put("ReceiptLine_releaseNumber", (String)incomingRequest.get("PoHeader_releaseNumber"));
					newIncomingRequest.put("ReceiptLine_receiptLine", "");
					newIncomingRequest.put("ReceiptLine_receiptDate", today);
					newIncomingRequest.put("ReceiptLine_receivedBy", userId);
					newIncomingRequest.put("ReceiptLine_timeZone", userTimeZone);
					newIncomingRequest.put("ReceiptLine_icReqLine", requisitionLine.getIcReqLine().toString());
					//newIncomingRequest.put("ReceiptLine_icPoHeader", requisitionLine.getIcReqHeader().toString());
					newIncomingRequest.put("ReceiptLine_carrierCode", receiptHeader.getCarrierCode());
					newIncomingRequest.put("ReceiptLine_packingSlip", receiptHeader.getPackingSlip());

					if (reqType.equalsIgnoreCase("T")) {
						newIncomingRequest.put("ReceiptLine_itemLocation", requisitionHeader.getShipToCode());
					}
					else
					{
						if (!HiltonUtility.isEmpty(requisitionLine.getItemLocation())){
							newIncomingRequest.put("ReceiptLine_itemLocation", requisitionLine.getItemLocation());
						} else {
							newIncomingRequest.put("ReceiptLine_itemLocation", receiptHeader.getItemLocation());
						}
					}

					if (requisitionHeader.getKit().equalsIgnoreCase("Y"))
					{
						newIncomingRequest.put("ReceiptLine_inventoryFlag", "K");
					} else if(!HiltonUtility.isEmpty(requisitionLine.getItemLocation()) && requisitionLine.getItemSource() != null && requisitionLine.getItemSource().equalsIgnoreCase("INV")){
						newIncomingRequest.put("ReceiptLine_inventoryFlag", "I");
					}


					newIncomingRequest.put("ReceiptLine_status", "4000");
					newIncomingRequest.put("ReceiptLine_qtyStep0Received", "0");
					newIncomingRequest.put("ReceiptLine_qtyStep0Rejected", "0");
					newIncomingRequest.put("ReceiptLine_qtyStep0Accepted", "0");
					newIncomingRequest.put("ReceiptLine_qtyStep1Received", "0");
					newIncomingRequest.put("ReceiptLine_qtyStep1Rejected", "0");
					newIncomingRequest.put("ReceiptLine_qtyStep1Accepted", "0");
					newIncomingRequest.put("ReceiptLine_qtyStep2Received", "0");
					newIncomingRequest.put("ReceiptLine_qtyStep2Rejected", "0");
					newIncomingRequest.put("ReceiptLine_qtyStep2Accepted", "0");
					newIncomingRequest.put("ReceiptLine_qtyStep3Received", "0");
					newIncomingRequest.put("ReceiptLine_qtyStep3Rejected", "0");
					newIncomingRequest.put("ReceiptLine_qtyStep3Accepted", "0");

					process.executeProcess(newIncomingRequest);
					ReceiptLine receiptLine = (ReceiptLine)newIncomingRequest.get("receiptLine");
					receiptLineList.add(receiptLine);
				}
			}
			result = receiptLineList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
