package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.math.BigDecimal;
import java.util.*;

public class RfqHeaderSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain originalRfqHeader and userId */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			RfqHeader	originalRfqHeader = (RfqHeader) incomingRequest.get("originalRfqHeader");
			RfqHeader	rfqHeader = new RfqHeader();

			String	userId = (String) incomingRequest.get("userId");

			rfqHeader.setActionAlertFlag(originalRfqHeader.getActionAlertFlag());
			rfqHeader.setAppBy("");
			rfqHeader.setApproved("N");
			rfqHeader.setAppSigned("N");
			rfqHeader.setAuctionEndDate(originalRfqHeader.getAuctionEndDate());
			rfqHeader.setAuctionEndTime(originalRfqHeader.getAuctionEndTime());
			rfqHeader.setAuctionEvent(originalRfqHeader.getAuctionEvent());
			rfqHeader.setAuctionStartDate(originalRfqHeader.getAuctionStartDate());
			rfqHeader.setAuctionStartTime(originalRfqHeader.getAuctionStartTime());
			rfqHeader.setAuctionEvent(originalRfqHeader.getAuctionEvent());
			rfqHeader.setAuctionType(originalRfqHeader.getAuctionType());
			rfqHeader.setAuthorizationCode("");
			rfqHeader.setBidAccess(originalRfqHeader.getBidAccess());
			rfqHeader.setBidDueTime(originalRfqHeader.getBidDueTime());
			rfqHeader.setBidEndDate(originalRfqHeader.getBidEndDate());
			rfqHeader.setBidEndTime(originalRfqHeader.getBidEndTime());
			rfqHeader.setBidEvent(originalRfqHeader.getBidEvent());
			rfqHeader.setBidStartDate(originalRfqHeader.getBidStartDate());
			rfqHeader.setBidStartTime(originalRfqHeader.getBidStartTime());
			rfqHeader.setBidItemOptions(originalRfqHeader.getBidItemOptions());
			rfqHeader.setBidVariance(originalRfqHeader.getBidVariance());
			rfqHeader.setBuyer(originalRfqHeader.getBuyer());
			rfqHeader.setCaIndicateLowest(originalRfqHeader.getCaIndicateLowest());
			rfqHeader.setCaLowestAmount(originalRfqHeader.getCaLowestAmount());
			rfqHeader.setCaWinningVendor(originalRfqHeader.getCaWinningVendor());
			rfqHeader.setDateEntered(new Date());
			rfqHeader.setDepartmentCode(originalRfqHeader.getDepartmentCode());
			rfqHeader.setDueDate(new Date());
			rfqHeader.setExtendMinutes(originalRfqHeader.getExtendMinutes());
			if (!HiltonUtility.isEmpty((String) incomingRequest.get("RfqHeader_fiscalYear")))
			{
				rfqHeader.setFiscalYear((String) incomingRequest.get("RfqHeader_fiscalYear"));
			}
			else
			{
				rfqHeader.setFiscalYear(originalRfqHeader.getFiscalYear());
			}
			rfqHeader.setIcHeaderHistory(new BigDecimal(ukg.getUniqueKey().toString()));
			rfqHeader.setIcReqHeader(new BigDecimal("0"));
			rfqHeader.setIcRfqHeader(new BigDecimal(ukg.getUniqueKey().toString()));
			rfqHeader.setItemLocation(originalRfqHeader.getItemLocation());
			rfqHeader.setLanguage(originalRfqHeader.getLanguage());
			rfqHeader.setLastChgBy(userId);
			rfqHeader.setLastChgDate(new Date());
			rfqHeader.setLowestBidReq(originalRfqHeader.getLowestBidReq());
			rfqHeader.setLowestBidSource(originalRfqHeader.getLowestBidSource());
			rfqHeader.setLowestDisplay(originalRfqHeader.getLowestDisplay());
			rfqHeader.setOwner(userId);
			rfqHeader.setPostFilename("");
			rfqHeader.setPriorityCode(originalRfqHeader.getPriorityCode());
			rfqHeader.setQaEndDate(originalRfqHeader.getQaEndDate());
			rfqHeader.setQaEndTime(originalRfqHeader.getQaEndTime());
			rfqHeader.setQaEvent(originalRfqHeader.getQaEvent());
			rfqHeader.setQaStartDate(originalRfqHeader.getQaStartDate());
			rfqHeader.setQaStartTime(originalRfqHeader.getQaStartTime());
			rfqHeader.setRequiredDate(new Date());
			rfqHeader.setRequisitionNumber("");
			rfqHeader.setRfqAmendment("");
			rfqHeader.setCurrencyCode(originalRfqHeader.getCurrencyCode());
			rfqHeader.setRfqDate(new Date());
			rfqHeader.setRfqDescription(originalRfqHeader.getRfqDescription());
			rfqHeader.setRfqNumber((String) incomingRequest.get("newRfqHeader_rfqNumber"));
			rfqHeader.setRfqType(originalRfqHeader.getRfqType());
			rfqHeader.setRouting(originalRfqHeader.getRouting());
			rfqHeader.setShippingCode(originalRfqHeader.getShippingCode());
			rfqHeader.setShipToCode(originalRfqHeader.getShipToCode());
			rfqHeader.setShipToContact(originalRfqHeader.getShipToContact());
			rfqHeader.setStatus(DocumentStatus.RFQ_INPROGRESS);
			rfqHeader.setTaxCode(originalRfqHeader.getTaxCode());
			rfqHeader.setTimeZone(originalRfqHeader.getTimeZone());
			rfqHeader.setUdf1Code(originalRfqHeader.getUdf1Code());
			rfqHeader.setUdf2Code(originalRfqHeader.getUdf2Code());
			rfqHeader.setUdf3Code(originalRfqHeader.getUdf3Code());
			rfqHeader.setUdf4Code(originalRfqHeader.getUdf4Code());
			rfqHeader.setUdf5Code(originalRfqHeader.getUdf5Code());
			rfqHeader.setUdf6Code(originalRfqHeader.getUdf6Code());
			rfqHeader.setUdf7Code(originalRfqHeader.getUdf7Code());
			rfqHeader.setUdf8Code(originalRfqHeader.getUdf8Code());
			rfqHeader.setUdf9Code(originalRfqHeader.getUdf9Code());
			rfqHeader.setUdf10Code(originalRfqHeader.getUdf10Code());
			rfqHeader.setVendorAwarded("");
			rfqHeader.setWebpost(originalRfqHeader.getWebpost());

			if (incomingRequest.containsKey("newRfqHeader_rfqType")) {
				rfqHeader.setRfqType((String) incomingRequest.get("newRfqHeader_rfqType"));
			}

			incomingRequest.put("rfqHeader", rfqHeader) ;

			/*
			RfqHeaderAdd addTask = new RfqHeaderAdd() ;
			rfqHeader = (RfqHeader) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;
			*/
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