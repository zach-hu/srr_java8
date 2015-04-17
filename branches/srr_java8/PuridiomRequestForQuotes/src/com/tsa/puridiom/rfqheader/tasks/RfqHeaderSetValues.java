package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class RfqHeaderSetValues extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		String organizationId = (String) incomingRequest.get("organizationId");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }
		try
		{
			RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
			if (rfqHeader == null)
			{
				rfqHeader = new RfqHeader();
			}

			if (incomingRequest.containsKey("RfqHeader_icRfqHeader"))
			{
				String icRfqHeaderString = (String) incomingRequest.get("RfqHeader_icRfqHeader");
				if (! Utility.isEmpty(icRfqHeaderString)) {
					BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
					rfqHeader.setIcRfqHeader(icRfqHeader);
				}
			}
			if(incomingRequest.containsKey("RfqHeader_rfqNumber"))
			{
				String rfqNumber = (String ) incomingRequest.get("RfqHeader_rfqNumber");
				rfqHeader.setRfqNumber(rfqNumber);
			}
			if (incomingRequest.containsKey("RfqHeader_rfqDate"))
			{
				String rfqDateString = (String) incomingRequest.get("RfqHeader_rfqDate");
				Date rfqDate = Dates.getSqlDate(rfqDateString, userDateFormat);
				rfqHeader.setRfqDate(rfqDate);
			}
			if (incomingRequest.containsKey("RfqHeader_dueDate"))
			{
				String dueDateString = (String) incomingRequest.get("RfqHeader_dueDate");
				if (!"null".equals(dueDateString)) {
					Date dueDate =  Dates.getSqlDate(dueDateString, userDateFormat);
					rfqHeader.setDueDate(dueDate);
				}
			}
			if(incomingRequest.containsKey("RfqHeader_requiredDate"))
			{
				String requiredDateString = (String) incomingRequest.get("RfqHeader_requiredDate");
				Date dueDate =  Dates.getSqlDate(requiredDateString, userDateFormat);
				rfqHeader.setRequiredDate(dueDate);
			}
			if(incomingRequest.containsKey("RfqHeader_awardDate"))
			{
				String awardDateString = (String) incomingRequest.get("RfqHeader_awardDate");
				Date awardDate =  Dates.getSqlDate(awardDateString, userDateFormat);
				rfqHeader.setAwardDate(awardDate);
			}
			if (incomingRequest.containsKey("RfqHeader_status"))
			{
				String status = (String) incomingRequest.get("RfqHeader_status");
				rfqHeader.setStatus(status);
			}
			if (incomingRequest.containsKey("RfqHeader_rfqDescription"))
			{
				String rfqDescription = (String) incomingRequest.get("RfqHeader_rfqDescription");
				rfqHeader.setRfqDescription(rfqDescription);
			}
			if (incomingRequest.containsKey("RfqHeader_buyer"))
			{
				String buyer = (String) incomingRequest.get("RfqHeader_buyer");
				rfqHeader.setBuyer(buyer);
			}
			if (incomingRequest.containsKey("RfqHeader_vendorAwarded"))
			{
				String vendorAwarded = (String) incomingRequest.get("RfqHeader_vendorAwarded");
				rfqHeader.setVendorAwarded(vendorAwarded);
			}
			if (incomingRequest.containsKey("RfqHeader_udf1Code"))
			{
				String udf1Code = (String) incomingRequest.get("RfqHeader_udf1Code");
				rfqHeader.setUdf1Code(udf1Code);
			}
			if (incomingRequest.containsKey("RfqHeader_udf2Code"))
			{
				String udf2Code = (String) incomingRequest.get("RfqHeader_udf2Code");
				rfqHeader.setUdf2Code(udf2Code);
			}
			if (incomingRequest.containsKey("RfqHeader_udf3Code"))
			{
				String udf3Code = (String) incomingRequest.get("RfqHeader_udf3Code");
				rfqHeader.setUdf3Code(udf3Code);
			}
			if (incomingRequest.containsKey("RfqHeader_udf4Code"))
			{
				String udf4Code = (String) incomingRequest.get("RfqHeader_udf4Code");
				rfqHeader.setUdf4Code(udf4Code);
			}
			if (incomingRequest.containsKey("RfqHeader_udf5Code"))
			{
				String udf5Code = (String) incomingRequest.get("RfqHeader_udf5Code");
				rfqHeader.setUdf5Code(udf5Code);
			}
			if (incomingRequest.containsKey("RfqHeader_udf6Code"))
			{
				String udf6Code = (String) incomingRequest.get("RfqHeader_udf6Code");
				rfqHeader.setUdf6Code(udf6Code);
			}
			if (incomingRequest.containsKey("RfqHeader_udf7Code"))
			{
				String udf7Code = (String) incomingRequest.get("RfqHeader_udf7Code");
				rfqHeader.setUdf7Code(udf7Code);
			}
			if (incomingRequest.containsKey("RfqHeader_udf8Code"))
			{
				String udf8Code = (String) incomingRequest.get("RfqHeader_udf8Code");
				rfqHeader.setUdf8Code(udf8Code);
			}
			if (incomingRequest.containsKey("RfqHeader_udf9Code"))
			{
				String udf9Code = (String) incomingRequest.get("RfqHeader_udf9Code");
				rfqHeader.setUdf9Code(udf9Code);
			}
			if (incomingRequest.containsKey("RfqHeader_udf10Code"))
			{
				String udf10Code = (String) incomingRequest.get("RfqHeader_udf10Code");
				rfqHeader.setUdf10Code(udf10Code);
			}
			if (incomingRequest.containsKey("RfqHeader_owner"))
			{
				String owner = (String) incomingRequest.get("RfqHeader_owner");
				rfqHeader.setOwner(owner);
			}
			if (incomingRequest.containsKey("RfqHeader_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("RfqHeader_dateEntered");
				Date dateEntered = Dates.getSqlDate(dateEnteredString, userDateFormat);
				rfqHeader.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("RfqHeader_fiscalYear"))
			{
				String fiscalYear = (String) incomingRequest.get("RfqHeader_fiscalYear");
				rfqHeader.setFiscalYear(fiscalYear);
			}
			if (incomingRequest.containsKey("RfqHeader_language"))
			{
				String language = (String) incomingRequest.get("RfqHeader_language");
				rfqHeader.setLanguage(language);
			}
			if (incomingRequest.containsKey("RfqHeader_approved"))
			{
				String approved = (String) incomingRequest.get("RfqHeader_approved");
				rfqHeader.setApproved(approved);
			}
			if (incomingRequest.containsKey("RfqHeader_appBy"))
			{
				String appBy = (String) incomingRequest.get("RfqHeader_appBy");
				rfqHeader.setAppBy(appBy);
			}
			if (incomingRequest.containsKey("RfqHeader_appDate"))
			{
				String appDateString = (String) incomingRequest.get("RfqHeader_appDate");
				Date appDate = Dates.getSqlDate(appDateString, userDateFormat);
				rfqHeader.setAppDate(appDate);
			}
			if (incomingRequest.containsKey("RfqHeader_appSigned"))
			{
				String appSigned = (String) incomingRequest.get("RfqHeader_appSigned");
				rfqHeader.setAppSigned(appSigned);
			}
			if(incomingRequest.containsKey("RfqHeader_departmentCode"))
			{
				String departmentCode = (String ) incomingRequest.get("RfqHeader_departmentCode");
				rfqHeader.setDepartmentCode(departmentCode);
			}
			if(incomingRequest.containsKey("RfqHeader_authorizationCode"))
			{
				String authorizationCode = (String ) incomingRequest.get("RfqHeader_authorizationCode");
				rfqHeader.setAuthorizationCode(authorizationCode);
			}
			if(incomingRequest.containsKey("RfqHeader_itemLocation"))
			{
				String itemLocation = (String ) incomingRequest.get("RfqHeader_itemLocation");
				rfqHeader.setItemLocation(itemLocation);
			}
			if(incomingRequest.containsKey("RfqHeader_taxCode"))
			{
				String taxCode = (String ) incomingRequest.get("RfqHeader_taxCode");
				rfqHeader.setTaxCode(taxCode);
			}
			if(incomingRequest.containsKey("RfqHeader_billToContact"))
			{
				String billToContact = (String ) incomingRequest.get("RfqHeader_billToContact");
				rfqHeader.setBillToContact(billToContact);
			}
			if(incomingRequest.containsKey("RfqHeader_billToCode"))
			{
				String billToCode = (String ) incomingRequest.get("RfqHeader_billToCode");
				rfqHeader.setBillToCode(billToCode);
			}
			if(incomingRequest.containsKey("RfqHeader_lastChgBy"))
			{
				String lastChgBy = (String ) incomingRequest.get("RfqHeader_lastChgBy");
				rfqHeader.setLastChgBy(lastChgBy);
			}
			if (incomingRequest.containsKey("RfqHeader_lastChgDate"))
			{
				String lastChgDateString = (String) incomingRequest.get("RfqHeader_lastChgDate");
				Date lastChgDate = Dates.getSqlDate(lastChgDateString, userDateFormat);
				rfqHeader.setLastChgDate(lastChgDate);
			}
			if (incomingRequest.containsKey("RfqHeader_requisitionNumber"))
			{
				String requisitionNumber = (String) incomingRequest.get("RfqHeader_requisitionNumber");
				rfqHeader.setRequisitionNumber(requisitionNumber);
			}
			if(incomingRequest.containsKey("RfqHeader_requisitionerCode"))
			{
				String requisitionCode = (String ) incomingRequest.get("RfqHeader_requisitionerCode");
				rfqHeader.setRequisitionerCode(requisitionCode);
			}
			if(incomingRequest.containsKey("RfqHeader_shipToCode"))
			{
				String shipToCode = (String ) incomingRequest.get("RfqHeader_shipToCode");
				rfqHeader.setShipToCode(shipToCode);
			}
			if(incomingRequest.containsKey("RfqHeader_shipToContact"))
			{
				String shipToContact = (String ) incomingRequest.get("RfqHeader_shipToContact");
				rfqHeader.setShipToContact(shipToContact);
			}
			if(incomingRequest.containsKey("RfqHeader_priorityCode"))
			{
				String priorityCode = (String ) incomingRequest.get("RfqHeader_priorityCode");
				rfqHeader.setPriorityCode(priorityCode);
			}
			if(incomingRequest.containsKey("RfqHeader_shippingCode"))
			{
				String shippingCode = (String ) incomingRequest.get("RfqHeader_shippingCode");
				rfqHeader.setShippingCode(shippingCode);
			}
			if(incomingRequest.containsKey("RfqHeader_routing"))
			{
				String routing = (String ) incomingRequest.get("RfqHeader_routing");
				rfqHeader.setRouting(routing);
			}
			if(incomingRequest.containsKey("RfqHeader_icReqHeader"))
			{
				String icReqHeaderString = (String) incomingRequest.get("RfqHeader_icReqHeader");
				if (! Utility.isEmpty(icReqHeaderString)) {
					BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );
					rfqHeader.setIcReqHeader(icReqHeader);
				}
			}
			if(incomingRequest.containsKey("RfqHeader_webpost"))
			{
				String webpost = (String) incomingRequest.get("RfqHeader_webpost");
				rfqHeader.setWebpost(webpost);
			}
			if (incomingRequest.containsKey("RfqHeader_rfqType"))
			{
				String rfqType = (String) incomingRequest.get("RfqHeader_rfqType");
				rfqHeader.setRfqType(rfqType);
			}
			if (incomingRequest.containsKey("RfqHeader_rfqAmendment"))
			{
				String rfqAmendment = (String) incomingRequest.get("RfqHeader_rfqAmendment");
				rfqHeader.setRfqAmendment(rfqAmendment);
			}
			if (incomingRequest.containsKey("RfqHeader_bidAccess"))
			{
				String bidAccess = (String) incomingRequest.get("RfqHeader_bidAccess");
				rfqHeader.setBidAccess(bidAccess);
			}
			if (incomingRequest.containsKey("RfqHeader_auctionType"))
			{
				String auctionType = (String) incomingRequest.get("RfqHeader_auctionType");
				rfqHeader.setAuctionType(auctionType);
			}
			if(incomingRequest.containsKey("RfqHeader_bidDueTime"))
			{
				String bidDueTime = (String ) incomingRequest.get("RfqHeader_bidDueTime");
				rfqHeader.setBidDueTime(bidDueTime);
			}
			if(incomingRequest.containsKey("RfqHeader_timeZone"))
			{
				String timeZone = (String ) incomingRequest.get("RfqHeader_timeZone");
				rfqHeader.setTimeZone(timeZone);
			}
			if (incomingRequest.containsKey("RfqHeader_bidItemOptions"))
			{
				String bidItemOptions = (String) incomingRequest.get("RfqHeader_bidItemOptions");
				rfqHeader.setBidItemOptions(bidItemOptions);
			}
			if (incomingRequest.containsKey("RfqHeader_actionAlertFlag"))
			{
				String actionAlertFlag = (String) incomingRequest.get("RfqHeader_actionAlertFlag");
				rfqHeader.setActionAlertFlag(actionAlertFlag);
			}
			if (incomingRequest.containsKey("RfqHeader_lowestBidSource"))
			{
				String lowestBidSource = (String) incomingRequest.get("RfqHeader_lowestBidSource");
				rfqHeader.setLowestBidSource(lowestBidSource);
			}
			if (incomingRequest.containsKey("RfqHeader_lowestBidReq"))
			{
				String lowestBidReq = (String) incomingRequest.get("RfqHeader_lowestBidReq");
				rfqHeader.setLowestBidReq(lowestBidReq);
			}
			if (incomingRequest.containsKey("RfqHeader_lowestDisplay"))
			{
				String lowestDisplay = (String) incomingRequest.get("RfqHeader_lowestDisplay");
				rfqHeader.setLowestDisplay(lowestDisplay);
			}
			if (incomingRequest.containsKey("RfqHeader_postFilename"))
			{
				String postFilename = (String) incomingRequest.get("RfqHeader_postFilename");
				rfqHeader.setPostFilename(postFilename);
			}
			if (incomingRequest.containsKey("RfqHeader_icHeaderHistory"))
			{
				String icHeaderHistoryString = (String) incomingRequest.get("RfqHeader_icHeaderHistory");
				if (! Utility.isEmpty(icHeaderHistoryString)) {
					BigDecimal icHeaderHistory = new BigDecimal ( icHeaderHistoryString );
					rfqHeader.setIcHeaderHistory(icHeaderHistory);
				}
			}
			if(incomingRequest.containsKey("RfqHeader_receiptRequired"))
			{
				String receiptRequired = (String ) incomingRequest.get("RfqHeader_receiptRequired");
				rfqHeader.setReceiptRequired(receiptRequired);
			}
			if (incomingRequest.containsKey("RfqHeader_extendMinutes"))
			{
				String	extendMinutesString = (String) incomingRequest.get("RfqHeader_extendMinutes");
				if (! Utility.isEmpty(extendMinutesString)) {
					BigDecimal extendMinutes = new BigDecimal ( extendMinutesString );
					rfqHeader.setExtendMinutes(extendMinutes);
				}
			}
			if(incomingRequest.containsKey("RfqHeader_bidVariance"))
			{
				String	bidVarianceString = (String) incomingRequest.get("RfqHeader_bidVariance");
				if (! Utility.isEmpty(bidVarianceString)) {
					BigDecimal bidVariance = new BigDecimal ( bidVarianceString );
					rfqHeader.setBidVariance(bidVariance);
				}
			}
			if(incomingRequest.containsKey("RfqHeader_caIndicateLowest"))
			{
				String caIndicateLowest = (String) incomingRequest.get("RfqHeader_caIndicateLowest");
				rfqHeader.setCaIndicateLowest(caIndicateLowest);
			}
			if (incomingRequest.containsKey("RfqHeader_caLowestAmount"))
			{
				String caLowestAmount = (String) incomingRequest.get("RfqHeader_caLowestAmount");
				rfqHeader.setCaLowestAmount(caLowestAmount);
			}
			if (incomingRequest.containsKey("RfqHeader_caWinningVendor"))
			{
				String caWinningVendor = (String) incomingRequest.get("RfqHeader_caWinningVendor");
				rfqHeader.setCaWinningVendor(caWinningVendor);
			}
			if (incomingRequest.containsKey("RfqHeader_qaEvent"))
			{
				String qaEvent = (String) incomingRequest.get("RfqHeader_qaEvent");
				rfqHeader.setQaEvent(qaEvent);
			}
			if (incomingRequest.containsKey("RfqHeader_bidEvent"))
			{
				String bidEvent = (String) incomingRequest.get("RfqHeader_bidEvent");
				rfqHeader.setBidEvent(bidEvent);
			}
			if (incomingRequest.containsKey("RfqHeader_auctionEvent"))
			{
				String auctionEvent = (String) incomingRequest.get("RfqHeader_auctionEvent");
				rfqHeader.setAuctionEvent(auctionEvent);
			}
			if (incomingRequest.containsKey("RfqHeader_qaStartDate"))
			{
				String qaStartDateString = (String) incomingRequest.get("RfqHeader_qaStartDate");
				Date qaStartDate = Dates.getSqlDate(qaStartDateString, userDateFormat);
				rfqHeader.setQaStartDate(qaStartDate);
			}
			if (incomingRequest.containsKey("RfqHeader_qaStartTime"))
			{
				String qaStartTime = (String) incomingRequest.get("RfqHeader_qaStartTime");
				rfqHeader.setQaStartTime(qaStartTime);
			}
			if (incomingRequest.containsKey("RfqHeader_qaEndDate"))
			{
				String qaEndDateString = (String) incomingRequest.get("RfqHeader_qaEndDate");
				Date qaEndDate = Dates.getSqlDate(qaEndDateString, userDateFormat);
				rfqHeader.setQaEndDate(qaEndDate);
			}
			if (incomingRequest.containsKey("RfqHeader_qaEndTime"))
			{
				String qaEndTime = (String) incomingRequest.get("RfqHeader_qaEndTime");
				rfqHeader.setQaEndTime(qaEndTime);
			}
			if (incomingRequest.containsKey("RfqHeader_bidStartDate"))
			{
				String bidStartDateString = (String) incomingRequest.get("RfqHeader_bidStartDate");
				Date bidStartDate = Dates.getSqlDate(bidStartDateString, userDateFormat);
				rfqHeader.setBidStartDate(bidStartDate);
			}
			if (incomingRequest.containsKey("RfqHeader_bidStartTime"))
			{
				String bidStartTime = (String) incomingRequest.get("RfqHeader_bidStartTime");
				rfqHeader.setBidStartTime(bidStartTime);
			}
			if (incomingRequest.containsKey("RfqHeader_bidEndDate"))
			{
				String	bidEndDateString = (String) incomingRequest.get("RfqHeader_bidEndDate");
				Date bidEndDate = Dates.getSqlDate(bidEndDateString, userDateFormat);
				rfqHeader.setBidEndDate(bidEndDate);
			}
			if (incomingRequest.containsKey("RfqHeader_bidEndTime"))
			{
				String bidEndTime = (String) incomingRequest.get("RfqHeader_bidEndTime");
				rfqHeader.setBidEndTime(bidEndTime);
			}
			if (incomingRequest.containsKey("RfqHeader_auctionStartDate"))
			{
				String auctionStartDateString = (String) incomingRequest.get("RfqHeader_auctionStartDate");
				Date auctionStartDate = Dates.getSqlDate(auctionStartDateString, userDateFormat);
				rfqHeader.setAuctionStartDate(auctionStartDate);
			}
			if (incomingRequest.containsKey("RfqHeader_auctionStartTime"))
			{
				String auctionStartTime = (String) incomingRequest.get("RfqHeader_auctionStartTime");
				rfqHeader.setAuctionStartTime(auctionStartTime);
			}
			if (incomingRequest.containsKey("RfqHeader_auctionEndDate"))
			{
				String auctionEndDateString = (String) incomingRequest.get("RfqHeader_auctionEndDate");
				Date auctionEndDate = Dates.getSqlDate(auctionEndDateString, userDateFormat);
				rfqHeader.setAuctionEndDate(auctionEndDate);
			}
			if (incomingRequest.containsKey("RfqHeader_auctionEndTime"))
			{
				String auctionEndTime = (String) incomingRequest.get("RfqHeader_auctionEndTime");
				rfqHeader.setAuctionEndTime(auctionEndTime);
			}
			if (incomingRequest.containsKey("RfqHeader_allowProxyBids"))
			{
				String allowProxyBids = (String) incomingRequest.get("RfqHeader_allowProxyBids");
				rfqHeader.setAllowProxyBids(allowProxyBids);
			}
			if (incomingRequest.containsKey("RfqHeader_eventPaused"))
			{
				String eventPaused = (String) incomingRequest.get("RfqHeader_eventPaused");
				rfqHeader.setEventPaused(eventPaused);
			}
			if (incomingRequest.containsKey("RfqHeader_currencyCode"))
			{
				String currencyCode = (String) incomingRequest.get("RfqHeader_currencyCode");
				rfqHeader.setCurrencyCode(currencyCode);
			}
			if (incomingRequest.containsKey("RfqHeader_currencyFactor"))
			{
				String currencyFactorString = (String) incomingRequest.get("RfqHeader_currencyFactor");
				if (Utility.isEmpty(currencyFactorString))
				{
					currencyFactorString = "1";
				}
				BigDecimal currencyFactor = new BigDecimal ( currencyFactorString );
				rfqHeader.setCurrencyFactor(currencyFactor);
			}
			if (incomingRequest.containsKey("RfqHeader_maxPoints"))
			{
				String maxPointsString = (String) incomingRequest.get("RfqHeader_maxPoints");
				if (Utility.isEmpty(maxPointsString))
				{
					maxPointsString = "0";
				}
				BigDecimal maxPoints = new BigDecimal ( maxPointsString );
				rfqHeader.setMaxPoints(maxPoints);
			}
			if (incomingRequest.containsKey("RfqHeader_gfpGfm"))
			{
				String gfpGfm = (String) incomingRequest.get("RfqHeader_gfpGfm");
				rfqHeader.setGfpGfm(gfpGfm);
			}
            if (incomingRequest.containsKey("RfqHeader_kit"))
			{
				String kit = (String ) incomingRequest.get("RfqHeader_kit");
				rfqHeader.setKit(kit);
			}
            if (incomingRequest.containsKey("RfqHeader_workOrder"))
			{
				String workOrder = (String ) incomingRequest.get("RfqHeader_workOrder");
				rfqHeader.setWorkOrder(workOrder);
			}
            if (incomingRequest.containsKey("RfqHeader_requestCat"))
			{
				String cat = (String) incomingRequest.get("RfqHeader_requestCat");
				rfqHeader.setRequestCat(cat);
			}
            if (incomingRequest.containsKey("RfqHeader_naics"))
			{
				String naics = (String) incomingRequest.get("RfqHeader_naics");
				rfqHeader.setNaics(naics);
			}
            if (incomingRequest.containsKey("RfqHeader_setAside"))
			{
				String setAside = (String) incomingRequest.get("RfqHeader_setAside");
				rfqHeader.setSetAside(setAside);
			}
            if (incomingRequest.containsKey("RfqHeader_estimatedCost"))
			{
				String estimatedCostString = (String) incomingRequest.get("RfqHeader_estimatedCost");
				if (Utility.isEmpty(estimatedCostString))
				{
					estimatedCostString = "1";
				}
				BigDecimal estimatedCost = new BigDecimal ( estimatedCostString );
				rfqHeader.setEstimatedCost(estimatedCost);
			}
            if (incomingRequest.containsKey("RfqHeader_corrosionEval"))
			{
				String corrosionEval = (String) incomingRequest.get("RfqHeader_corrosionEval");
				rfqHeader.setCorrosionEval(corrosionEval);
			}
            if (incomingRequest.containsKey("RfqHeader_originalReqType"))
            {
            	String originalReqType = (String) incomingRequest.get("RfqHeader_originalReqType");
            	rfqHeader.setOriginalReqType(originalReqType);
            }

			result = rfqHeader;
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
