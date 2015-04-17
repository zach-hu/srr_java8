package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

import java.util.*;

@SuppressWarnings(value = { "unchecked" })
public class ReceiptLineCreateFromPoLineList extends Task
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

			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
            }
			List poLineList = (List)incomingRequest.get("poLineList");
			List requisitionLineList = (List)incomingRequest.get("requisitionLineList");

			RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");

			if (receiptHeader == null) {
				receiptHeader = new ReceiptHeader();
			}
			String today = Dates.today(userDateFormat, userTimeZone);

			if (poLineList != null && poLineList.size() > 0)
			{
				for (int i=0; i<poLineList.size(); i++)
				{
					PoLine poLine = (PoLine)poLineList.get(i);
					RequisitionLine requisitionLine = null;
					if(requisitionLineList.size() != 0){
						try{
							requisitionLine = (RequisitionLine)requisitionLineList.get(i);
						} catch (IndexOutOfBoundsException e) {
							requisitionLine = null;
						}
					}

					PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
					PuridiomProcess process = processLoader.loadProcess("receiptline-create-from-poline.xml");

					Map newIncomingRequest = new HashMap();
					newIncomingRequest.put("userId", incomingRequest.get("userId"));
					newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
					newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
					newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));

					UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
					newIncomingRequest.put("ReceiptLine_icRecLine", ukg.getUniqueKey().toString());
					newIncomingRequest.put("ReceiptLine_icLineHistory", poLine.getIcLineHistory().toString());
					newIncomingRequest.put("ReceiptLine_icRecHeader", (String)incomingRequest.get("ReceiptHeader_icRecHeader"));
					if (!HiltonUtility.isEmpty(receiptHeader.getReceiptNumber()))
					{
						newIncomingRequest.put("ReceiptLine_receiptNumber", receiptHeader.getReceiptNumber());
					}
					else
					{
						newIncomingRequest.put("ReceiptLine_receiptNumber", (String)incomingRequest.get("ReceiptHeader_receiptNumber"));
					}
					if(poLine.getStatus().equals(DocumentStatus.CANCELLED)){
						newIncomingRequest.put("ReceiptLine_status",DocumentStatus.CANCELLED);
					}else{
						newIncomingRequest.put("ReceiptLine_status", (String)incomingRequest.get("ReceiptHeader_receiptStatus"));
					}			
					newIncomingRequest.put("ReceiptLine_receiptType", (String)incomingRequest.get("ReceiptHeader_receiptType"));
					newIncomingRequest.put("ReceiptLine_releaseNumber", (String)incomingRequest.get("PoHeader_releaseNumber"));
					newIncomingRequest.put("ReceiptLine_receiptLine", "");
					newIncomingRequest.put("ReceiptLine_receiptDate", today);
					newIncomingRequest.put("ReceiptLine_receivedBy", userId);
					newIncomingRequest.put("ReceiptLine_timeZone", userTimeZone);
					newIncomingRequest.put("ReceiptLine_icPoLine", poLine.getIcPoLine().toString());
					newIncomingRequest.put("ReceiptLine_icPoHeader", poLine.getIcPoHeader().toString());
					newIncomingRequest.put("ReceiptLine_icReqLine", poLine.getIcReqLine().toString());
					newIncomingRequest.put("ReceiptLine_carrierCode", receiptHeader.getCarrierCode());
					newIncomingRequest.put("ReceiptLine_packingSlip", receiptHeader.getPackingSlip());
					newIncomingRequest.put("ReceiptLine_inspectorAssigned", "UNASSIGNED");
					newIncomingRequest.put("ReceiptLine_engineerAssigned", "UNASSIGNED");
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
					newIncomingRequest.put("ReceiptLine_chemicalItemNumber", poLine.getChemicalItemNumber());

					ReceiptLineCheckInspection checkInspection = new ReceiptLineCheckInspection();
					Boolean inspectionRequired = (Boolean) checkInspection.executeTask(newIncomingRequest);
					if (inspectionRequired)
					{
						newIncomingRequest.put("ReceiptLine_inspectionRequired", "Y");
						receiptHeader.setInspectionRequired("Y");
					}
					else
					{
						newIncomingRequest.put("ReceiptLine_inspectionRequired", "N");
					}
//					newIncomingRequest.put("ReceiptLine_inspectionRequired", "Y");

					/*ReceiptLineCheckMarkTag checkMarkTag = new ReceiptLineCheckMarkTag();
					Boolean markTagRequired = (Boolean) checkMarkTag.executeTask(newIncomingRequest);*/
					if (!poLine.getAsset().equalsIgnoreCase("N") && !HiltonUtility.isEmpty(poLine.getAsset()))
					{
						newIncomingRequest.put("ReceiptLine_markTagRequired", poLine.getAsset());
					}
					else
					{
						newIncomingRequest.put("ReceiptLine_markTagRequired", "");
					}
//					newIncomingRequest.put("ReceiptLine_markTagRequired", "Y");

					if(requisitionHeader != null && requisitionLine != null && requisitionHeader.getRequisitionType().equalsIgnoreCase(RequisitionType.MSR_REQUEST) && requisitionHeader.getKit().equalsIgnoreCase("Y")){
							newIncomingRequest.put("ReceiptLine_itemLocation", requisitionLine.getItemLocation());
					} else if (!HiltonUtility.isEmpty(poLine.getItemLocation())){
						newIncomingRequest.put("ReceiptLine_itemLocation", poLine.getItemLocation());
					} else {
						newIncomingRequest.put("ReceiptLine_itemLocation", receiptHeader.getItemLocation());
					}

					if (poHeader.getKit().equalsIgnoreCase("Y"))
					{
						newIncomingRequest.put("ReceiptLine_inventoryFlag", "K");
					}

					newIncomingRequest.put("ReceiptLine_inventoryStatus", "0000");

					/** Source Inspect and Post Del Test*/
					if(poLine.getUdf8Code().equalsIgnoreCase("RISI") || poLine.getUdf8Code().equalsIgnoreCase("SI")){ // Source Inspect
						newIncomingRequest.put("ReceiptLine_udf2Code", "yes");
						newIncomingRequest.put("ReceiptLine_udf3Code", "no");
					} else if(poLine.getUdf8Code().equalsIgnoreCase("RIPDT") || poLine.getUdf8Code().equalsIgnoreCase("PDT")){ // Post Del Test
						newIncomingRequest.put("ReceiptLine_udf2Code", "no");
						newIncomingRequest.put("ReceiptLine_udf3Code", "yes");
					} else if(poLine.getUdf8Code().equalsIgnoreCase("RISIPDT")){
						newIncomingRequest.put("ReceiptLine_udf2Code", "yes");
						newIncomingRequest.put("ReceiptLine_udf3Code", "yes");
					} else {
						newIncomingRequest.put("ReceiptLine_udf2Code", "no");
						newIncomingRequest.put("ReceiptLine_udf3Code", "no");
					}

					process.executeProcess(newIncomingRequest);
					ReceiptLine receiptLine = (ReceiptLine)newIncomingRequest.get("receiptLine");
					receiptLineList.add(receiptLine);

					/** SRR - Task #495 - SRR - MSR Kitting Functionality
					if(requisitionHeader != null && requisitionLine != null && requisitionHeader.getRequisitionType().equalsIgnoreCase(RequisitionType.MSR_REQUEST)){
						PuridiomProcess invItemProcess = processLoader.loadProcess("invitem-retrieve-by-id-msrline.xml");

						Map invIncomingRequest = new HashMap();
						invIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
						invIncomingRequest.put("organizationId", organizationId);
						invIncomingRequest.put("InvItem_itemNumber", poLine.getItemNumber());
						//invIncomingRequest.put("InvLocation_itemNumber", poLine.getItemNumber());
						//invIncomingRequest.put("InvBinLocation_itemNumber", poLine.getItemNumber());
						//invIncomingRequest.put("InvLocation_itemLocation", poLine.getItemLocation());
						//invIncomingRequest.put("InvBinLocation_itemLocation", poLine.getItemLocation());
						invIncomingRequest.put("RequisitionHeader_icReqHeader", requisitionHeader.getIcReqHeader().toString());
						invIncomingRequest.put("RequisitionLine_icReqLine", requisitionLine.getIcReqLine().toString());

						invItemProcess.executeProcess(invIncomingRequest);

					}
					*/
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
