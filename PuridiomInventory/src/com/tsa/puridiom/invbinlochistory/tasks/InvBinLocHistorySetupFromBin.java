package com.tsa.puridiom.invbinlochistory.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.math.BigDecimal;
import java.util.*;

public class InvBinLocHistorySetupFromBin extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        String userId = (String) incomingRequest.get("userTimeZone");
        String reasonCode = (String) incomingRequest.get("InvBinLocation_reasonCode");
        String binAction = (String) incomingRequest.get("binAction");

		try
		{
			InvBinLocHistory invBinLocHistory = (InvBinLocHistory) incomingRequest.get("invBinLocHistory");
			if (invBinLocHistory == null) {
				invBinLocHistory = new InvBinLocHistory();
				UniqueKeyGenerator uk = UniqueKeyGenerator.getInstance();
				BigDecimal icCode = new BigDecimal(uk.getUniqueKey().toString());
				invBinLocHistory.setIcCode(icCode) ;
				incomingRequest.put("InvBinLocHistory_icCode", icCode.toString());
			}

			/* Expects incoming request to contain invBinLocHistory */
			InvBinLocation	invBinLocation = (InvBinLocation) incomingRequest.get("invBinLocation");
			if (invBinLocation != null) {
				invBinLocHistory.setIcRc(invBinLocation.getIcRc()) ;
				invBinLocHistory.setItemNumber(invBinLocation.getItemNumber());
				invBinLocHistory.setItemLocation(invBinLocation.getItemLocation());
				invBinLocHistory.setVendorId(invBinLocation.getVendorId());
				invBinLocHistory.setManufactNo(invBinLocation.getManufactNo());
				invBinLocHistory.setLot(invBinLocation.getLot());
				invBinLocHistory.setCost(invBinLocation.getCost());
				invBinLocHistory.setItemDate(invBinLocation.getItemDate());
				invBinLocHistory.setAisle(invBinLocation.getAisle());
				invBinLocHistory.setLocrow(invBinLocation.getLocrow());
				invBinLocHistory.setTier(invBinLocation.getTier());
				invBinLocHistory.setBin(invBinLocation.getBin());
				invBinLocHistory.setUdf1Code(invBinLocation.getUdf1Code());
				invBinLocHistory.setUdf2Code(invBinLocation.getUdf2Code());
				invBinLocHistory.setUdf3Code(invBinLocation.getUdf3Code());
				invBinLocHistory.setUdf4Code(invBinLocation.getUdf4Code());
				invBinLocHistory.setUdf5Code(invBinLocation.getUdf5Code());
				invBinLocHistory.setQtyOnHand(invBinLocation.getQtyOnHand());
				invBinLocHistory.setQtyAlloc(invBinLocation.getQtyAlloc());
				invBinLocHistory.setDuomQtyOnHand(invBinLocation.getDuomQtyOnHand());
				invBinLocHistory.setDuomQtyAlloc(invBinLocation.getDuomQtyAlloc());
				invBinLocHistory.setTransactionDate(Dates.getCurrentDate(userTimeZone));
				invBinLocHistory.setItemDate(Dates.getCurrentDate(userTimeZone));
				invBinLocHistory.setUserId(userId) ;
				invBinLocHistory.setReasonCode(reasonCode) ;
				invBinLocHistory.setActionCode(binAction) ;

			}
			incomingRequest.put("invBinLocHistory", invBinLocHistory);

			result = invBinLocHistory;
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