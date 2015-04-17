package com.tsa.puridiom.receiptheader.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ReceiptHeaderSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");
			if (receiptHeader == null)
			{
				receiptHeader = new ReceiptHeader();
			}

			if (incomingRequest.containsKey("ReceiptHeader_icRecHeader"))
			{
				String icRecHeaderString = (String) incomingRequest.get("ReceiptHeader_icRecHeader");
				if (Utility.isEmpty(icRecHeaderString))
				{
					icRecHeaderString = "0";
				}
				BigDecimal icRecHeader = new BigDecimal ( icRecHeaderString );
				receiptHeader.setIcRecHeader(icRecHeader);
			}
			if (incomingRequest.containsKey("ReceiptHeader_icPoHeader"))
			{
				String icPoHeaderString = (String) incomingRequest.get("ReceiptHeader_icPoHeader");
				if (Utility.isEmpty(icPoHeaderString))
				{
					icPoHeaderString = "0";
				}
				BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
				receiptHeader.setIcPoHeader(icPoHeader);
			}
			if (incomingRequest.containsKey("ReceiptHeader_icReqHeader"))
			{
				String icReqHeaderString = (String) incomingRequest.get("ReceiptHeader_icReqHeader");
				if (Utility.isEmpty(icReqHeaderString))
				{
					icReqHeaderString = "0";
				}
				BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );
				receiptHeader.setIcReqHeader(icReqHeader);
			}
			if (incomingRequest.containsKey("ReceiptHeader_receiptDate"))
			{
				String receiptDateString = (String) incomingRequest.get("ReceiptHeader_receiptDate");
				Date receiptDate = Dates.getDate(receiptDateString);
				receiptHeader.setReceiptDate(receiptDate);
			}
			if (incomingRequest.containsKey("ReceiptHeader_receiptType"))
			{
				String receiptType = (String) incomingRequest.get("ReceiptHeader_receiptType");
				receiptHeader.setReceiptType(receiptType);
			}
			if (incomingRequest.containsKey("ReceiptHeader_receivedBy"))
			{
				String receivedBy = (String) incomingRequest.get("ReceiptHeader_receivedBy");
				receiptHeader.setReceivedBy(receivedBy);
			}
			if (incomingRequest.containsKey("ReceiptHeader_carrierCode"))
			{
				String carrierCode = (String) incomingRequest.get("ReceiptHeader_carrierCode");
				receiptHeader.setCarrierCode(carrierCode);
			}
			if (incomingRequest.containsKey("ReceiptHeader_packingSlip"))
			{
				String packingSlip = (String) incomingRequest.get("ReceiptHeader_packingSlip");
				receiptHeader.setPackingSlip(packingSlip);
			}
			if (incomingRequest.containsKey("ReceiptHeader_vendorId"))
			{
				String vendorId = (String) incomingRequest.get("ReceiptHeader_vendorId");
				receiptHeader.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("ReceiptHeader_shipToCode"))
			{
				String shipToCode = (String) incomingRequest.get("ReceiptHeader_shipToCode");
				receiptHeader.setShipToCode(shipToCode);
			}
			if (incomingRequest.containsKey("ReceiptHeader_owner"))
			{
				String owner = (String) incomingRequest.get("ReceiptHeader_owner");
				receiptHeader.setOwner(owner);
			}
			if (incomingRequest.containsKey("ReceiptHeader_receiptStatus"))
			{
				String receiptStatus = (String) incomingRequest.get("ReceiptHeader_receiptStatus");
				receiptHeader.setReceiptStatus(receiptStatus);
			}
			if (incomingRequest.containsKey("ReceiptHeader_refNumber"))
			{
				String refNumber = (String) incomingRequest.get("ReceiptHeader_refNumber");
				receiptHeader.setRefNumber(refNumber);
			}
			if (incomingRequest.containsKey("ReceiptHeader_refRelease"))
			{
				String refRelease = (String) incomingRequest.get("ReceiptHeader_refRelease");
				receiptHeader.setRefRelease(refRelease);
			}
			if (incomingRequest.containsKey("ReceiptHeader_refDate"))
			{
				String refDateString = (String) incomingRequest.get("ReceiptHeader_refDate");
				Date refDate = Dates.getDate(refDateString);
				receiptHeader.setRefDate(refDate);
			}
			if (incomingRequest.containsKey("ReceiptHeader_refType"))
			{
				String refType = (String) incomingRequest.get("ReceiptHeader_refType");
				receiptHeader.setRefType(refType);
			}
			if (incomingRequest.containsKey("ReceiptHeader_vendorName"))
			{
				String vendorName = (String) incomingRequest.get("ReceiptHeader_vendorName");
				receiptHeader.setVendorName(vendorName);
			}
			if (incomingRequest.containsKey("ReceiptHeader_receiptNumber"))
			{
				String receiptNumber = (String) incomingRequest.get("ReceiptHeader_receiptNumber");
				receiptHeader.setReceiptNumber(receiptNumber);
			}
			if (incomingRequest.containsKey("ReceiptHeader_fiscalYear"))
			{
				String fiscalYear = (String) incomingRequest.get("ReceiptHeader_fiscalYear");
				receiptHeader.setFiscalYear(fiscalYear);
			}
			if (incomingRequest.containsKey("ReceiptHeader_forwardTo"))
			{
				String forwardTo = (String) incomingRequest.get("ReceiptHeader_forwardTo");
				receiptHeader.setForwardTo(forwardTo);
			}
			if (incomingRequest.containsKey("ReceiptHeader_receiptNotes"))
			{
				String receiptNotes = (String) incomingRequest.get("ReceiptHeader_receiptNotes");
				receiptHeader.setReceiptNotes(receiptNotes);
			}
			if (incomingRequest.containsKey("ReceiptHeader_releaseNumber"))
			{
				String releaseNumberString = (String) incomingRequest.get("ReceiptHeader_releaseNumber");
				if (Utility.isEmpty(releaseNumberString))
				{
					releaseNumberString = "0";
				}
				BigDecimal releaseNumber = new BigDecimal ( releaseNumberString );
				receiptHeader.setReleaseNumber(releaseNumber);
			}
			if (incomingRequest.containsKey("ReceiptHeader_pkgsReceived"))
			{
				String pkgsReceivedString = (String) incomingRequest.get("ReceiptHeader_pkgsReceived");
				if (Utility.isEmpty(pkgsReceivedString))
				{
					pkgsReceivedString = "0";
				}
				BigDecimal pkgsReceived = new BigDecimal ( pkgsReceivedString );
				receiptHeader.setPkgsReceived(pkgsReceived);
			}
			if (incomingRequest.containsKey("ReceiptHeader_returnDate"))
			{
				String returnDateString = (String) incomingRequest.get("ReceiptHeader_returnDate");
				Date returnDate = Dates.getDate(returnDateString);
				receiptHeader.setReturnDate(returnDate);
			}
			if (incomingRequest.containsKey("ReceiptHeader_timeZone"))
			{
				String timeZone = (String) incomingRequest.get("ReceiptHeader_timeZone");
				receiptHeader.setTimeZone(timeZone);
			}
			if (incomingRequest.containsKey("ReceiptHeader_icHeaderHistory"))
			{
				String icHeaderHistoryString = (String) incomingRequest.get("ReceiptHeader_icHeaderHistory");
				if (Utility.isEmpty(icHeaderHistoryString))
				{
					icHeaderHistoryString = "0";
				}
				BigDecimal icHeaderHistory = new BigDecimal ( icHeaderHistoryString );
				receiptHeader.setIcHeaderHistory(icHeaderHistory);
			}
			if (incomingRequest.containsKey("ReceiptHeader_itemLocation"))
			{
				String itemLocation = (String) incomingRequest.get("ReceiptHeader_itemLocation");
				receiptHeader.setItemLocation(itemLocation);
			}
			if (incomingRequest.containsKey("ReceiptHeader_tempIc"))
			{
				String tempIcString = (String) incomingRequest.get("ReceiptHeader_tempIc");
				if (Utility.isEmpty(tempIcString))
				{
					tempIcString = "0";
				}
				BigDecimal tempIc = new BigDecimal ( tempIcString );
				receiptHeader.setTempIc(tempIc);
			}
			if (incomingRequest.containsKey("ReceiptHeader_corrosionEval"))
			{
				String corrosionEval = (String) incomingRequest.get("ReceiptHeader_corrosionEval");
				receiptHeader.setCorrosionEval(corrosionEval);
			}
			if (incomingRequest.containsKey("ReceiptHeader_inspectionRequired"))
			{
				String inspectionRequired = (String) incomingRequest.get("ReceiptHeader_inspectionRequired");
				receiptHeader.setInspectionRequired(inspectionRequired);
			}

			result = receiptHeader;
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
