package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ReceiptLineSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
			if (receiptLine == null)
			{
				receiptLine = new ReceiptLine();
			}

			if (incomingRequest.containsKey("ReceiptLine_icRecLine"))
			{
				String icRecLineString = (String) incomingRequest.get("ReceiptLine_icRecLine");
				if (Utility.isEmpty(icRecLineString))
				{
					icRecLineString = "0";
				}
				BigDecimal icRecLine = new BigDecimal ( icRecLineString );
				receiptLine.setIcRecLine(icRecLine);
			}
			if (incomingRequest.containsKey("ReceiptLine_icRecHeader"))
			{
				String icRecHeaderString = (String) incomingRequest.get("ReceiptLine_icRecHeader");
				if (Utility.isEmpty(icRecHeaderString))
				{
					icRecHeaderString = "0";
				}
				BigDecimal icRecHeader = new BigDecimal ( icRecHeaderString );
				receiptLine.setIcRecHeader(icRecHeader);
			}
			if (incomingRequest.containsKey("ReceiptLine_receiptLine"))
			{
				String receiptLineString = (String) incomingRequest.get("ReceiptLine_receiptLine");
				if (Utility.isEmpty(receiptLineString))
				{
					receiptLineString = "0";
				}
				BigDecimal receiptLineNumber = new BigDecimal ( receiptLineString );
				receiptLine.setReceiptLine(receiptLineNumber);
			}
			if (incomingRequest.containsKey("ReceiptLine_icPoLine"))
			{
				String icPoLineString = (String) incomingRequest.get("ReceiptLine_icPoLine");
				if (Utility.isEmpty(icPoLineString))
				{
					icPoLineString = "0";
				}
				BigDecimal icPoLine = new BigDecimal ( icPoLineString );
				receiptLine.setIcPoLine(icPoLine);
			}
			if (incomingRequest.containsKey("ReceiptLine_icReqLine"))
			{
				String icReqLineString = (String) incomingRequest.get("ReceiptLine_icReqLine");
				if (Utility.isEmpty(icReqLineString))
				{
					icReqLineString = "0";
				}
				BigDecimal icReqLine = new BigDecimal ( icReqLineString );
				receiptLine.setIcReqLine(icReqLine);
			}
			if (incomingRequest.containsKey("ReceiptLine_receiptDate"))
			{
				String receiptDateString = (String) incomingRequest.get("ReceiptLine_receiptDate");
				Date receiptDate = Dates.getDate(receiptDateString);
				receiptLine.setReceiptDate(receiptDate);
			}
			if (incomingRequest.containsKey("ReceiptLine_receiptNumber"))
			{
				String receiptNumber = (String) incomingRequest.get("ReceiptLine_receiptNumber");
				receiptLine.setReceiptNumber(receiptNumber);
			}
			if (incomingRequest.containsKey("ReceiptLine_packingSlip"))
			{
				String packingSlip = (String) incomingRequest.get("ReceiptLine_packingSlip");
				receiptLine.setPackingSlip(packingSlip);
			}
			if (incomingRequest.containsKey("ReceiptLine_lotNumber"))
			{
				String lotNumber = (String) incomingRequest.get("ReceiptLine_lotNumber");
				receiptLine.setLotNumber(lotNumber);
			}
			if (incomingRequest.containsKey("ReceiptLine_receivedBy"))
			{
				String receivedBy = (String) incomingRequest.get("ReceiptLine_receivedBy");
				receiptLine.setReceivedBy(receivedBy);
			}
			if (incomingRequest.containsKey("ReceiptLine_qtyReceived"))
			{
				String qtyReceivedString = (String) incomingRequest.get("ReceiptLine_qtyReceived");
				if (Utility.isEmpty(qtyReceivedString))
				{
					qtyReceivedString = "0";
				}
				BigDecimal qtyReceived = new BigDecimal ( qtyReceivedString );
				receiptLine.setQtyReceived(qtyReceived);
			}
			if (incomingRequest.containsKey("ReceiptLine_qtyReturned"))
			{
				String qtyReturnedString = (String) incomingRequest.get("ReceiptLine_qtyReturned");
				if (Utility.isEmpty(qtyReturnedString))
				{
					qtyReturnedString = "0";
				}
				BigDecimal qtyReturned = new BigDecimal ( qtyReturnedString );
				receiptLine.setQtyReturned(qtyReturned);
			}
			if (incomingRequest.containsKey("ReceiptLine_qtyInspected"))
			{
				String qtyInspectedString = (String) incomingRequest.get("ReceiptLine_qtyInspected");
				if (Utility.isEmpty(qtyInspectedString))
				{
					qtyInspectedString = "0";
				}
				BigDecimal qtyInspected = new BigDecimal ( qtyInspectedString );
				receiptLine.setQtyInspected(qtyInspected);
			}
			if (incomingRequest.containsKey("ReceiptLine_qtyMarked"))
			{
				String qtyMarkedString = (String) incomingRequest.get("ReceiptLine_qtyMarked");
				if (Utility.isEmpty(qtyMarkedString))
				{
					qtyMarkedString = "0";
				}
				BigDecimal qtyMarked = new BigDecimal ( qtyMarkedString );
				receiptLine.setQtyMarked(qtyMarked);
			}
			if (incomingRequest.containsKey("ReceiptLine_qtyDelivered"))
			{
				String qtyDeliveredString = (String) incomingRequest.get("ReceiptLine_qtyDelivered");
				if (Utility.isEmpty(qtyDeliveredString))
				{
					qtyDeliveredString = "0";
				}
				BigDecimal qtyDelivered = new BigDecimal ( qtyDeliveredString );
				receiptLine.setQtyDelivered(qtyDelivered);
			}
			if (incomingRequest.containsKey("ReceiptLine_inspectionCode"))
			{
				String inspectionCode = (String) incomingRequest.get("ReceiptLine_inspectionCode");
				receiptLine.setInspectionCode(inspectionCode);
			}
			if (incomingRequest.containsKey("ReceiptLine_status"))
			{
				String status = (String) incomingRequest.get("ReceiptLine_status");
				receiptLine.setStatus(status);
			}
			if (incomingRequest.containsKey("ReceiptLine_icPoHeader"))
			{
				String icPoHeaderString = (String) incomingRequest.get("ReceiptLine_icPoHeader");
				if (Utility.isEmpty(icPoHeaderString))
				{
					icPoHeaderString = "0";
				}
				BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
				receiptLine.setIcPoHeader(icPoHeader);
			}
			if (incomingRequest.containsKey("ReceiptLine_qtyRejected"))
			{
				String qtyRejectedString = (String) incomingRequest.get("ReceiptLine_qtyRejected");
				if (Utility.isEmpty(qtyRejectedString))
				{
					qtyRejectedString = "0";
				}
				BigDecimal qtyRejected = new BigDecimal ( qtyRejectedString );
				receiptLine.setQtyRejected(qtyRejected);
			}
			if (incomingRequest.containsKey("ReceiptLine_convFactor"))
			{
				String convFactorString = (String) incomingRequest.get("ReceiptLine_convFactor");
				if (Utility.isEmpty(convFactorString))
				{
					convFactorString = "0";
				}
				BigDecimal convFactor = new BigDecimal ( convFactorString );
				receiptLine.setConvFactor(convFactor);
			}
			if (incomingRequest.containsKey("ReceiptLine_udf1Code"))
			{
				String udf1Code = (String) incomingRequest.get("ReceiptLine_udf1Code");
				receiptLine.setUdf1Code(udf1Code);
			}
			if (incomingRequest.containsKey("ReceiptLine_udf2Code"))
			{
				String udf2Code = (String) incomingRequest.get("ReceiptLine_udf2Code");
				receiptLine.setUdf2Code(udf2Code);
			}
			if (incomingRequest.containsKey("ReceiptLine_udf3Code"))
			{
				String udf3Code = (String) incomingRequest.get("ReceiptLine_udf3Code");
				receiptLine.setUdf3Code(udf3Code);
			}
			if (incomingRequest.containsKey("ReceiptLine_udf4Code"))
			{
				String udf4Code = (String) incomingRequest.get("ReceiptLine_udf4Code");
				receiptLine.setUdf4Code(udf4Code);
			}
			if (incomingRequest.containsKey("ReceiptLine_udf5Code"))
			{
				String udf5Code = (String) incomingRequest.get("ReceiptLine_udf5Code");
				receiptLine.setUdf5Code(udf5Code);
			}
			if (incomingRequest.containsKey("ReceiptLine_carrierCode"))
			{
				String carrierCode = (String) incomingRequest.get("ReceiptLine_carrierCode");
				receiptLine.setCarrierCode(carrierCode);
			}
			if (incomingRequest.containsKey("ReceiptLine_linComflag"))
			{
				String linComflag = (String) incomingRequest.get("ReceiptLine_linComflag");
				receiptLine.setLinComflag(linComflag);
			}
			if (incomingRequest.containsKey("ReceiptLine_receiptType"))
			{
				String receiptType = (String) incomingRequest.get("ReceiptLine_receiptType");
				receiptLine.setReceiptType(receiptType);
			}
			if (incomingRequest.containsKey("ReceiptLine_apBatchid"))
			{
				String apBatchid = (String) incomingRequest.get("ReceiptLine_apBatchid");
				receiptLine.setApBatchid(apBatchid);
			}
			if (incomingRequest.containsKey("ReceiptLine_receiptNotes"))
			{
				String receiptNotes = (String) incomingRequest.get("ReceiptLine_receiptNotes");
				receiptLine.setReceiptNotes(receiptNotes);
			}
			if (incomingRequest.containsKey("ReceiptLine_releaseNumber"))
			{
				String releaseNumberString = (String) incomingRequest.get("ReceiptLine_releaseNumber");
				if (Utility.isEmpty(releaseNumberString))
				{
					releaseNumberString = "0";
				}
				BigDecimal releaseNumber = new BigDecimal ( releaseNumberString );
				receiptLine.setReleaseNumber(releaseNumber);
			}
			if (incomingRequest.containsKey("ReceiptLine_rejectionCode"))
			{
				String rejectionCode = (String) incomingRequest.get("ReceiptLine_rejectionCode");
				receiptLine.setRejectionCode(rejectionCode);
			}
			if (incomingRequest.containsKey("ReceiptLine_dispositionCode"))
			{
				String dispositionCode = (String) incomingRequest.get("ReceiptLine_dispositionCode");
				receiptLine.setDispositionCode(dispositionCode);
			}
			if (incomingRequest.containsKey("ReceiptLine_qtyAccepted"))
			{
				String qtyAcceptedString = (String) incomingRequest.get("ReceiptLine_qtyAccepted");
				if (Utility.isEmpty(qtyAcceptedString))
				{
				    qtyAcceptedString = "0";
				}
				BigDecimal qtyAccepted = new BigDecimal ( qtyAcceptedString );
				receiptLine.setQtyAccepted(qtyAccepted);
			}
			if (incomingRequest.containsKey("ReceiptLine_icLineHistory"))
			{
				String icLineHistoryString = (String) incomingRequest.get("ReceiptLine_icLineHistory");
				if (Utility.isEmpty(icLineHistoryString))
				{
					icLineHistoryString = "0";
				}
				BigDecimal icLineHistory = new BigDecimal ( icLineHistoryString );
				receiptLine.setIcLineHistory(icLineHistory);
			}
			if (incomingRequest.containsKey("ReceiptLine_itemLocation"))
			{
				String itemLocation = (String) incomingRequest.get("ReceiptLine_itemLocation");
				receiptLine.setItemLocation(itemLocation);
			}
			if (incomingRequest.containsKey("ReceiptLine_duomQtyReceived"))
			{
				String duomQtyReceivedString = (String) incomingRequest.get("ReceiptLine_duomQtyReceived");
				if (Utility.isEmpty(duomQtyReceivedString))
				{
				    duomQtyReceivedString = "0";
				}
				BigDecimal duomQtyReceived = new BigDecimal ( duomQtyReceivedString );
				receiptLine.setDuomQtyReceived(duomQtyReceived);
			}
			if (incomingRequest.containsKey("ReceiptLine_umCode"))
			{
				String umCode = (String) incomingRequest.get("ReceiptLine_umCode");
				receiptLine.setUmCode(umCode);
			}
			if (incomingRequest.containsKey("ReceiptLine_duomUmCode"))
			{
				String duomUmCode = (String) incomingRequest.get("ReceiptLine_duomUmCode");
				receiptLine.setDuomUmCode(duomUmCode);
			}

			/** STEP 0 */
			if (incomingRequest.containsKey("ReceiptLine_qtyStep0Received"))
			{
				String qtyStep0ReceivedString = (String) incomingRequest.get("ReceiptLine_qtyStep0Received");
				if(Utility.isEmpty(qtyStep0ReceivedString))
				{
					qtyStep0ReceivedString = "0";
				}
				BigDecimal qtyStep0Received = new BigDecimal(qtyStep0ReceivedString);
				receiptLine.setQtyStep0Received(qtyStep0Received);
			}
			if (incomingRequest.containsKey("ReceiptLine_qtyStep0Rejected"))
			{
				String qtyStep0RejectedString = (String) incomingRequest.get("ReceiptLine_qtyStep0Rejected");
				if(Utility.isEmpty(qtyStep0RejectedString))
				{
					qtyStep0RejectedString = "0";
				}
				BigDecimal qtyStep0Rejected = new BigDecimal(qtyStep0RejectedString);
				receiptLine.setQtyStep0Rejected(qtyStep0Rejected);
			}
			if (incomingRequest.containsKey("ReceiptLine_qtyStep0Accepted"))
			{
				String qtyStep0AcceptedString = (String) incomingRequest.get("ReceiptLine_qtyStep0Accepted");
				if(Utility.isEmpty(qtyStep0AcceptedString))
				{
					qtyStep0AcceptedString = "0";
				}
				BigDecimal qtyStep0Accepted = new BigDecimal(qtyStep0AcceptedString);
				receiptLine.setQtyStep0Accepted(qtyStep0Accepted);
			}

			/** STEP 1 */
			if (incomingRequest.containsKey("ReceiptLine_qtyStep1Received"))
			{
				String qtyStep1ReceivedString = (String) incomingRequest.get("ReceiptLine_qtyStep1Received");
				if(Utility.isEmpty(qtyStep1ReceivedString))
				{
					qtyStep1ReceivedString = "0";
				}
				BigDecimal qtyStep1Received = new BigDecimal(qtyStep1ReceivedString);
				receiptLine.setQtyStep1Received(qtyStep1Received);
			}
			if (incomingRequest.containsKey("ReceiptLine_qtyStep1Rejected"))
			{
				String qtyStep1RejectedString = (String) incomingRequest.get("ReceiptLine_qtyStep1Rejected");
				if(Utility.isEmpty(qtyStep1RejectedString))
				{
					qtyStep1RejectedString = "0";
				}
				BigDecimal qtyStep1Rejected = new BigDecimal(qtyStep1RejectedString);
				receiptLine.setQtyStep1Rejected(qtyStep1Rejected);
			}
			if (incomingRequest.containsKey("ReceiptLine_qtyStep1Accepted"))
			{
				String qtyStep1AcceptedString = (String) incomingRequest.get("ReceiptLine_qtyStep1Accepted");
				if(Utility.isEmpty(qtyStep1AcceptedString))
				{
					qtyStep1AcceptedString = "0";
				}
				BigDecimal qtyStep1Accepted = new BigDecimal(qtyStep1AcceptedString);
				receiptLine.setQtyStep1Accepted(qtyStep1Accepted);
			}

			/** STEP 2 */
			if (incomingRequest.containsKey("ReceiptLine_qtyStep2Received"))
			{
				String qtyStep2ReceivedString = (String) incomingRequest.get("ReceiptLine_qtyStep2Received");
				if(Utility.isEmpty(qtyStep2ReceivedString))
				{
					qtyStep2ReceivedString = "0";
				}
				BigDecimal qtyStep2Received = new BigDecimal(qtyStep2ReceivedString);
				receiptLine.setQtyStep2Received(qtyStep2Received);
			}
			if (incomingRequest.containsKey("ReceiptLine_qtyStep2Rejected"))
			{
				String qtyStep2RejectedString = (String) incomingRequest.get("ReceiptLine_qtyStep2Rejected");
				if(Utility.isEmpty(qtyStep2RejectedString))
				{
					qtyStep2RejectedString = "0";
				}
				BigDecimal qtyStep2Rejected = new BigDecimal(qtyStep2RejectedString);
				receiptLine.setQtyStep2Rejected(qtyStep2Rejected);
			}
			if (incomingRequest.containsKey("ReceiptLine_qtyStep2Accepted"))
			{
				String qtyStep2AcceptedString = (String) incomingRequest.get("ReceiptLine_qtyStep2Accepted");
				if(Utility.isEmpty(qtyStep2AcceptedString))
				{
					qtyStep2AcceptedString = "0";
				}
				BigDecimal qtyStep2Accepted = new BigDecimal(qtyStep2AcceptedString);
				receiptLine.setQtyStep2Accepted(qtyStep2Accepted);
			}

			/** STEP 3 */
			if (incomingRequest.containsKey("ReceiptLine_qtyStep3Received"))
			{
				String qtyStep3ReceivedString = (String) incomingRequest.get("ReceiptLine_qtyStep3Received");
				if(Utility.isEmpty(qtyStep3ReceivedString))
				{
					qtyStep3ReceivedString = "0";
				}
				BigDecimal qtyStep3Received = new BigDecimal(qtyStep3ReceivedString);
				receiptLine.setQtyStep3Received(qtyStep3Received);
			}
			if (incomingRequest.containsKey("ReceiptLine_qtyStep3Rejected"))
			{
				String qtyStep3RejectedString = (String) incomingRequest.get("ReceiptLine_qtyStep3Rejected");
				if(Utility.isEmpty(qtyStep3RejectedString))
				{
					qtyStep3RejectedString = "0";
				}
				BigDecimal qtyStep3Rejected = new BigDecimal(qtyStep3RejectedString);
				receiptLine.setQtyStep3Rejected(qtyStep3Rejected);
			}
			if (incomingRequest.containsKey("ReceiptLine_qtyStep3Accepted"))
			{
				String qtyStep3AcceptedString = (String) incomingRequest.get("ReceiptLine_qtyStep3Accepted");
				if(Utility.isEmpty(qtyStep3AcceptedString))
				{
					qtyStep3AcceptedString = "0";
				}
				BigDecimal qtyStep3Accepted = new BigDecimal(qtyStep3AcceptedString);
				receiptLine.setQtyStep3Accepted(qtyStep3Accepted);
			}
			if (incomingRequest.containsKey("ReceiptLine_inspectorAssigned"))
			{
				String inspectorAssigned = (String) incomingRequest.get("ReceiptLine_inspectorAssigned");
				receiptLine.setInspectorAssigned(inspectorAssigned);
			}
			if (incomingRequest.containsKey("ReceiptLine_engineerAssigned"))
			{
				String engineerAssigned = (String) incomingRequest.get("ReceiptLine_engineerAssigned");
				receiptLine.setEngineerAssigned(engineerAssigned);
			}
			if (incomingRequest.containsKey("ReceiptLine_inspectionRequired"))
			{
				String inspectionRequired = (String) incomingRequest.get("ReceiptLine_inspectionRequired");
				receiptLine.setInspectionRequired(inspectionRequired);
			}
			if (incomingRequest.containsKey("ReceiptLine_markTagRequired"))
			{
				String markTagRequired = (String) incomingRequest.get("ReceiptLine_markTagRequired");
				receiptLine.setMarkTagRequired(markTagRequired);
			}
			if (incomingRequest.containsKey("ReceiptLine_inspectionStatus"))
			{
				String inspectionStatus = (String) incomingRequest.get("ReceiptLine_inspectionStatus");
				receiptLine.setInspectionStatus(inspectionStatus);
			}
			if (incomingRequest.containsKey("ReceiptLine_inventoryStatus"))
			{
				String inventoryStatus = (String) incomingRequest.get("ReceiptLine_inventoryStatus");
				receiptLine.setInventoryStatus(inventoryStatus);
			}
			if (incomingRequest.containsKey("ReceiptLine_inspLocation"))
			{
				String inspLocation = (String) incomingRequest.get("ReceiptLine_inspLocation");
				receiptLine.setInspLocation(inspLocation);
			}
			if (incomingRequest.containsKey("ReceiptLine_inspArea"))
			{
				String inspArea = (String) incomingRequest.get("ReceiptLine_inspArea");
				receiptLine.setInspArea(inspArea);
			}
			if (incomingRequest.containsKey("ReceiptLine_inspStorage"))
			{
				String inspStorage = (String) incomingRequest.get("ReceiptLine_inspStorage");
				receiptLine.setInspStorage(inspStorage);
			}
			if (incomingRequest.containsKey("ReceiptLine_inventoryFlag"))
			{
				String inventoryFlag = (String) incomingRequest.get("ReceiptLine_inventoryFlag");
				receiptLine.setInventoryFlag(inventoryFlag);
			}
			if (incomingRequest.containsKey("ReceiptLine_chemicalItemNumber"))
			{
				String chemicalItemNumber = (String) incomingRequest.get("ReceiptLine_chemicalItemNumber");
				receiptLine.setChemicalItemNumber(chemicalItemNumber);
			}
			if (incomingRequest.containsKey("ReceiptLine_inspectionAssignedDate"))
			{
				String inspectionAssignedDateString = (String) incomingRequest.get("ReceiptLine_inspectionAssignedDate");
				Date inspectionAssignedDate = Dates.getDate(inspectionAssignedDateString);
				receiptLine.setInspectionAssignedDate(inspectionAssignedDate);
			}
			if (incomingRequest.containsKey("ReceiptLine_inspectionClosedDate"))
			{
				String inspectionClosedDateString = (String) incomingRequest.get("ReceiptLine_inspectionClosedDate");
				Date inspectionClosedDate = Dates.getDate(inspectionClosedDateString);
				receiptLine.setInspectionClosedDate(inspectionClosedDate);
			}
			if (incomingRequest.containsKey("ReceiptLine_shelfLifeDate"))
			{
				String shelfLifeDateString = (String) incomingRequest.get("ReceiptLine_shelfLifeDate");
				Date shelfLifeDate = Dates.getDate(shelfLifeDateString);
				receiptLine.setShelfLifeDate(shelfLifeDate);
			}
			if (incomingRequest.containsKey("ReceiptLine_edws"))
			{
				String edws = (String) incomingRequest.get("ReceiptLine_edws");
				receiptLine.setEdws(edws);
			}

			result = receiptLine;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
