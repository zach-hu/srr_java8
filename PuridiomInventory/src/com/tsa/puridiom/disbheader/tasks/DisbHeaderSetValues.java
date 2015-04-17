package com.tsa.puridiom.disbheader.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class DisbHeaderSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DisbHeader disbHeader = (DisbHeader) incomingRequest.get("disbHeader");
			if (disbHeader == null)
			{
				disbHeader = new DisbHeader();
			}

			if (incomingRequest.containsKey("DisbHeader_icDsbHeader"))
			{
				String icDsbHeaderString = (String) incomingRequest.get("DisbHeader_icDsbHeader");
				if (Utility.isEmpty(icDsbHeaderString))
				{
					icDsbHeaderString = "0";
				}
				BigDecimal icDsbHeader = new BigDecimal ( icDsbHeaderString );
				disbHeader.setIcDsbHeader(icDsbHeader);
			}
			if (incomingRequest.containsKey("DisbHeader_disbNumber"))
			{
				String disbNumber = (String ) incomingRequest.get("DisbHeader_disbNumber");
				disbHeader.setDisbNumber(disbNumber);
			}
			if (incomingRequest.containsKey("DisbHeader_disbDate"))
			{
				String disbDateString = (String) incomingRequest.get("DisbHeader_disbDate");
				Date disbDate = Dates.getDate(disbDateString);
				disbHeader.setDisbDate(disbDate);
			}
			if (incomingRequest.containsKey("DisbHeader_status"))
			{
				String status = (String ) incomingRequest.get("DisbHeader_status");
				disbHeader.setStatus(status);
			}
			if (incomingRequest.containsKey("DisbHeader_internalComments"))
			{
				String internalComments = (String ) incomingRequest.get("DisbHeader_internalComments");
				disbHeader.setInternalComments(internalComments);
			}
			if (incomingRequest.containsKey("DisbHeader_fiscalYear"))
			{
				String fiscalYear = (String ) incomingRequest.get("DisbHeader_fiscalYear");
				disbHeader.setFiscalYear(fiscalYear);
			}
			if (incomingRequest.containsKey("DisbHeader_owner"))
			{
				String owner = (String ) incomingRequest.get("DisbHeader_owner");
				disbHeader.setOwner(owner);
			}
			if (incomingRequest.containsKey("DisbHeader_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("DisbHeader_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				disbHeader.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("DisbHeader_itemLocation"))
			{
				String itemLocation = (String ) incomingRequest.get("DisbHeader_itemLocation");
				disbHeader.setItemLocation(itemLocation);
			}
			if (incomingRequest.containsKey("DisbHeader_icAccount"))
			{
				String icAccountString = (String) incomingRequest.get("DisbHeader_icAccount");
				if (Utility.isEmpty(icAccountString))
				{
					icAccountString = "0";
				}
				BigDecimal icAccount = new BigDecimal ( icAccountString );
				disbHeader.setIcAccount(icAccount);
			}
			if (incomingRequest.containsKey("DisbHeader_subtotal"))
			{
				String subtotalString = (String) incomingRequest.get("DisbHeader_subtotal");
				if (Utility.isEmpty(subtotalString))
				{
					subtotalString = "0";
				}
				BigDecimal subtotal = new BigDecimal ( subtotalString );
				disbHeader.setSubtotal(subtotal);
			}
			if (incomingRequest.containsKey("DisbHeader_approved"))
			{
				String approved = (String ) incomingRequest.get("DisbHeader_approved");
				disbHeader.setApproved(approved);
			}
			if (incomingRequest.containsKey("DisbHeader_appBy"))
			{
				String appBy = (String ) incomingRequest.get("DisbHeader_appBy");
				disbHeader.setAppBy(appBy);
			}
			if (incomingRequest.containsKey("DisbHeader_appDate"))
			{
				String appDateString = (String) incomingRequest.get("DisbHeader_appDate");
				Date appDate = Dates.getDate(appDateString);
				disbHeader.setAppDate(appDate);
			}
			if (incomingRequest.containsKey("DisbHeader_appSigned"))
			{
				String appSigned = (String ) incomingRequest.get("DisbHeader_appSigned");
				disbHeader.setAppSigned(appSigned);
			}
			if (incomingRequest.containsKey("DisbHeader_lastChgBy"))
			{
				String lastChgBy = (String ) incomingRequest.get("DisbHeader_lastChgBy");
				disbHeader.setLastChgBy(lastChgBy);
			}
			if (incomingRequest.containsKey("DisbHeader_lastChgDate"))
			{
				String lastChgDateString = (String) incomingRequest.get("DisbHeader_lastChgDate");
				Date lastChgDate = Dates.getDate(lastChgDateString);
				disbHeader.setLastChgDate(lastChgDate);
			}
			if (incomingRequest.containsKey("DisbHeader_disbType"))
			{
				String disbType = (String ) incomingRequest.get("DisbHeader_disbType");
				disbHeader.setDisbType(disbType);
			}
			if (incomingRequest.containsKey("DisbHeader_icReqHeader"))
			{
				String icReqHeaderString = (String) incomingRequest.get("DisbHeader_icReqHeader");
				if (Utility.isEmpty(icReqHeaderString))
				{
					icReqHeaderString = "0";
				}
				BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );
				disbHeader.setIcReqHeader(icReqHeader);
			}
			if (incomingRequest.containsKey("DisbHeader_requisitionNumber"))
			{
				String requisitionNumber = (String ) incomingRequest.get("DisbHeader_requisitionNumber");
				disbHeader.setRequisitionNumber(requisitionNumber);
			}
			if (incomingRequest.containsKey("DisbHeader_requisitionerCode"))
			{
				String requisitionerCode = (String ) incomingRequest.get("DisbHeader_requisitionerCode");
				disbHeader.setRequisitionerCode(requisitionerCode);
			}
			if (incomingRequest.containsKey("DisbHeader_printed"))
			{
				String printed = (String ) incomingRequest.get("DisbHeader_printed");
				disbHeader.setPrinted(printed);
			}
			if (incomingRequest.containsKey("DisbHeader_icHeaderHistory"))
			{
				String icHeaderHistoryString = (String) incomingRequest.get("DisbHeader_icHeaderHistory");
				if (Utility.isEmpty(icHeaderHistoryString))
				{
					icHeaderHistoryString = "0";
				}
				BigDecimal icHeaderHistory = new BigDecimal ( icHeaderHistoryString );
				disbHeader.setIcHeaderHistory(icHeaderHistory);
			}

			result = disbHeader;
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