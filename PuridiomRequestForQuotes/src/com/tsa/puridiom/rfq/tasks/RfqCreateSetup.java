package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;

public class RfqCreateSetup extends Task{

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String	organizationId = (String) incomingRequest.get("organizationId");
		String	userId = (String) incomingRequest.get("userId");
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

		UserProfile user = UserManager.getInstance().getUser(organizationId, userId);

		// Create new ic codes
		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")) ;

        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = propertiesManager.getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }

        String	fYear = Dates.getFiscalYear(Integer.parseInt(propertiesManager.getProperty("MISC","FyBegin","1")), userTimeZone) ;
		String	today = Dates.today(userDateFormat, userTimeZone) ;
        String qaEvent = propertiesManager.getProperty("RFQ DEFAULTS", "QaEvent", "N");
        String bidEvent = propertiesManager.getProperty("RFQ DEFAULTS", "BidEvent", "N");
        String auctionEvent = propertiesManager.getProperty("RFQ DEFAULTS", "AuctionEvent", "N");

		int		offsetDays = 0;
		try
		{
			offsetDays = Integer.valueOf(propertiesManager.getProperty("RFQ DEFAULTS", "DueDateOffset", "0")).intValue();
		}
		catch(NumberFormatException e)
		{
			offsetDays = 0;
		}
		String	rfqType = (String) incomingRequest.get("RfqHeader_rfqType") ;
		if (rfqType == null)
		{
			rfqType = propertiesManager.getProperty("RFQ DEFAULTS", "RfqType", "RQ");
		}

		CurrencyManager currencyManager = CurrencyManager.getInstance(organizationId);
		String currencyCode = currencyManager.getCurrencyCodeForCountry(user.getLocale());

		if (Utility.isEmpty(currencyCode)) {
		    currencyCode = propertiesManager.getProperty("RFQ DEFAULTS","CurrencyCode","");
		}
		if (Utility.isEmpty(currencyCode)) {
		    currencyCode = currencyManager.getBaseCurrencyCode();
		}

		String	shipToCode = user.getShipToCode();
		if (Utility.isEmpty(shipToCode)) {
		    shipToCode = propertiesManager.getProperty("RFQ DEFAULTS","ShipToCode","");
		}

		incomingRequest.put("RfqHeader_icRfqHeader", ukg.getUniqueKey().toString());
		incomingRequest.put("RfqHeader_rfqNumber", "N/A");
		incomingRequest.put("RfqHeader_rfqDate", today) ;
		incomingRequest.put("RfqHeader_dueDate", Dates.add(today, offsetDays, userDateFormat)) ;
		incomingRequest.put("RfqHeader_status", DocumentStatus.RFQ_INPROGRESS) ;
		incomingRequest.put("RfqHeader_rfqDescription", "");
		incomingRequest.put("RfqHeader_buyer", userId) ;
		incomingRequest.put("RfqHeader_vendorAwarded", "");
		incomingRequest.put("RfqHeader_udf1Code", propertiesManager.getProperty("RFQ DEFAULTS", "Udf1Code", ""));
		incomingRequest.put("RfqHeader_udf2Code",propertiesManager.getProperty("RFQ DEFAULTS", "Udf2Code", ""));
		incomingRequest.put("RfqHeader_udf3Code",propertiesManager.getProperty("RFQ DEFAULTS", "Udf3Code", ""));
		incomingRequest.put("RfqHeader_udf4Code",propertiesManager.getProperty("RFQ DEFAULTS", "Udf4Code", ""));
		incomingRequest.put("RfqHeader_udf5Code",propertiesManager.getProperty("RFQ DEFAULTS", "Udf5Code", ""));
		incomingRequest.put("RfqHeader_udf6Code",propertiesManager.getProperty("RFQ DEFAULTS", "Udf6Code", ""));
		incomingRequest.put("RfqHeader_udf7Code",propertiesManager.getProperty("RFQ DEFAULTS", "Udf7Code", ""));
		incomingRequest.put("RfqHeader_udf8Code",propertiesManager.getProperty("RFQ DEFAULTS", "Udf8Code", ""));
		incomingRequest.put("RfqHeader_udf9Code",propertiesManager.getProperty("RFQ DEFAULTS", "Udf9Code", ""));
		incomingRequest.put("RfqHeader_udf10Code",propertiesManager.getProperty("RFQ DEFAULTS", "Udf10Code", ""));
		incomingRequest.put("RfqHeader_owner",userId) ;
		incomingRequest.put("RfqHeader_dateEntered",today) ;
		incomingRequest.put("RfqHeader_fiscalYear",fYear);
		incomingRequest.put("RfqHeader_language", propertiesManager.getProperty("RFQ DEFAULTS", "Language", "(Default)")) ;
		incomingRequest.put("RfqHeader_approved","N") ;
		incomingRequest.put("RfqHeader_appBy","") ;
		incomingRequest.put("RfqHeader_appSigned","N") ;
		incomingRequest.put("RfqHeader_lastChgBy",userId) ;
		incomingRequest.put("RfqHeader_lastChgDate",today) ;
		incomingRequest.put("RfqHeader_requisitionNumber","") ;
		incomingRequest.put("RfqHeader_icReqHeader", "0") ;
		incomingRequest.put("RfqHeader_webpost", propertiesManager.getProperty("RFQ DEFAULTS", "WebPost", "N"));
		incomingRequest.put("RfqHeader_rfqType",rfqType) ;
		incomingRequest.put("RfqHeader_rfqAmendment", "");
		incomingRequest.put("RfqHeader_bidAccess", propertiesManager.getProperty("RFQ DEFAULTS", "BidAccess", "U"));
		incomingRequest.put("RfqHeader_auctionType", propertiesManager.getProperty("RFQ DEFAULTS", "AuctionType", "O"));
		incomingRequest.put("RfqHeader_bidItemOptions", "");
		incomingRequest.put("RfqHeader_currencyCode", currencyCode);
		incomingRequest.put("RfqHeader_currencyFactor", String.valueOf(currencyManager.getCurrencyFactor(currencyCode)));
		incomingRequest.put("RfqHeader_actionAlertFlag", "");
		incomingRequest.put("RfqHeader_lowestBidSource", propertiesManager.getProperty("RFQ DEFAULTS", "LowestBidSource", "L"));
		incomingRequest.put("RfqHeader_lowestBidReq", propertiesManager.getProperty("RFQ DEFAULTS", "LowestBidReq", "N"));
		incomingRequest.put("RfqHeader_lowestDisplay", propertiesManager.getProperty("RFQ DEFAULTS", "LowestDisplay", "A"));
		incomingRequest.put("RfqHeader_postFilename", "");
		incomingRequest.put("RfqHeader_icHeaderHistory", ukg.getUniqueKey().toString());
		incomingRequest.put("RfqHeader_requisitionerCode",user.getUserId()) ;
		incomingRequest.put("RfqHeader_departmentCode",user.getDepartment()) ;
		incomingRequest.put("RfqHeader_authorizationCode","") ;
		incomingRequest.put("RfqHeader_shippingCode", propertiesManager.getProperty("RFQ DEFAULTS","ShippingCode",""));
		incomingRequest.put("RfqHeader_shipToCode", shipToCode);
		incomingRequest.put("RfqHeader_shipToContact", propertiesManager.getProperty("RFQ DEFAULTS","ShipToContact",""));
		incomingRequest.put("RfqHeader_billToCode",propertiesManager.getProperty("RFQ DEFAULTS","BillToCode",""));
		incomingRequest.put("RfqHeader_billToContact",propertiesManager.getProperty("RFQ DEFAULTS","BillToContact",""));
		incomingRequest.put("RfqHeader_routing",user.getRouting()) ;
		incomingRequest.put("RfqHeader_priorityCode",propertiesManager.getProperty("RFQ DEFAULTS","PriorityCode",""));
		incomingRequest.put("RfqHeader_taxCode", propertiesManager.getProperty("RFQ DEFAULTS","TaxCode",""));
		incomingRequest.put("RfqHeader_requiredDate", Dates.add(today, offsetDays, userDateFormat)) ;
		incomingRequest.put("RfqHeader_itemLocation", "");
		incomingRequest.put("RfqHeader_receiptRequired", propertiesManager.getProperty("RFQ DEFAULTS","ReceiptRequired",""));
		incomingRequest.put("RfqHeader_extendMinutes", propertiesManager.getProperty("RFQ DEFAULTS", "ExtendMinutes", "5"));
		incomingRequest.put("RfqHeader_bidVariance", propertiesManager.getProperty("RFQ DEFAULTS", "BidVariance", "25"));
		incomingRequest.put("RfqHeader_caIndicateLowest", propertiesManager.getProperty("RFQ DEFAULTS", "CaIndicateLowest", "Y"));
		incomingRequest.put("RfqHeader_caLowestAmount", propertiesManager.getProperty("RFQ DEFAULTS", "CaLowestAmount", "N"));
		incomingRequest.put("RfqHeader_caWinningVendor", propertiesManager.getProperty("RFQ DEFAULTS", "CaWinningVendor", "N"));
		incomingRequest.put("RfqHeader_qaEvent", propertiesManager.getProperty("RFQ DEFAULTS", "QaEvent", "N"));
		incomingRequest.put("RfqHeader_bidEvent", propertiesManager.getProperty("RFQ DEFAULTS", "BidEvent", "N"));
		incomingRequest.put("RfqHeader_auctionEvent", auctionEvent);
		incomingRequest.put("RfqHeader_qaStartDate", today) ;
		incomingRequest.put("RfqHeader_qaStartTime", propertiesManager.getProperty("RFQ DEFAULTS", "QaStartTime", "09:00"));
		incomingRequest.put("RfqHeader_qaEndDate", Dates.add(today, offsetDays, userDateFormat)) ;
		incomingRequest.put("RfqHeader_qaEndTime", propertiesManager.getProperty("RFQ DEFAULTS", "QaEndTime", "10:00"));
		incomingRequest.put("RfqHeader_bidStartDate", today) ;
		incomingRequest.put("RfqHeader_bidStartTime", propertiesManager.getProperty("RFQ DEFAULTS", "BidStartTime", "10:00"));
		incomingRequest.put("RfqHeader_bidEndDate", Dates.add(today, offsetDays, userDateFormat)) ;
		incomingRequest.put("RfqHeader_bidEndTime", propertiesManager.getProperty("RFQ DEFAULTS", "BidEndTime", "11:00"));
		incomingRequest.put("RfqHeader_auctionStartDate", today) ;
		incomingRequest.put("RfqHeader_auctionStartTime", propertiesManager.getProperty("RFQ DEFAULTS", "AuctionStartTime", "11:00"));
		incomingRequest.put("RfqHeader_auctionEndDate", Dates.add(today, offsetDays, userDateFormat)) ;
		incomingRequest.put("RfqHeader_auctionEndTime", propertiesManager.getProperty("RFQ DEFAULTS", "AuctionEndTime", "12:00"));
        incomingRequest.put("RfqHeader_timeZone", userTimeZone);

        //Default the bidDueTime to the latest event end time
        if (propertiesManager.isModuleActive("EXTENDED RFQS") && auctionEvent.equals("Y")) {
            incomingRequest.put("RfqHeader_bidDueTime", propertiesManager.getProperty("RFQ DEFAULTS", "AuctionEndTime", "12:00"));
        } else if (propertiesManager.isModuleActive("EXTENDED RFQS") && bidEvent.equals("Y")) {
            incomingRequest.put("RfqHeader_bidDueTime", propertiesManager.getProperty("RFQ DEFAULTS", "BidEndTime", "11:00"));
        } else if (propertiesManager.isModuleActive("EXTENDED RFQS") && qaEvent.equals("Y")) {
            incomingRequest.put("RfqHeader_bidDueTime", propertiesManager.getProperty("RFQ DEFAULTS", "QaEndTime", "10:00"));
        } else {
            incomingRequest.put("RfqHeader_bidDueTime", propertiesManager.getProperty("RFQ DEFAULTS", "BidDueTime", "23:59"));
        }

		// set values for generating the rfq number
		if (fYear == null || fYear.trim().length() == 0) {
			fYear = "1994";
		}
		incomingRequest.put("AutoGen_documentType", "RFQ") ;
		incomingRequest.put("AutoGen_genYear", fYear) ;

		return null ;
	}

}