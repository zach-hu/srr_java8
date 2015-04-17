package com.tsa.puridiom.invbinlochistory.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InvBinLocHistorySaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain invBinLocHistory */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InvBinLocHistory	originalInvBinLocHistory = (InvBinLocHistory) incomingRequest.get("invBinLocHistory");
			InvBinLocHistory	invBinLocHistory = new InvBinLocHistory();
			invBinLocHistory.setItemNumber(originalInvBinLocHistory.getItemNumber());
			invBinLocHistory.setItemLocation(originalInvBinLocHistory.getItemLocation());
			invBinLocHistory.setVendorId(originalInvBinLocHistory.getVendorId());
			invBinLocHistory.setManufactNo(originalInvBinLocHistory.getManufactNo());
			invBinLocHistory.setLot(originalInvBinLocHistory.getLot());
			invBinLocHistory.setCost(originalInvBinLocHistory.getCost());
			invBinLocHistory.setItemDate(originalInvBinLocHistory.getItemDate());
			invBinLocHistory.setAisle(originalInvBinLocHistory.getAisle());
			invBinLocHistory.setLocrow(originalInvBinLocHistory.getLocrow());
			invBinLocHistory.setTier(originalInvBinLocHistory.getTier());
			invBinLocHistory.setBin(originalInvBinLocHistory.getBin());
			invBinLocHistory.setUdf1Code(originalInvBinLocHistory.getUdf1Code());
			invBinLocHistory.setUdf2Code(originalInvBinLocHistory.getUdf2Code());
			invBinLocHistory.setUdf3Code(originalInvBinLocHistory.getUdf3Code());
			invBinLocHistory.setUdf4Code(originalInvBinLocHistory.getUdf4Code());
			invBinLocHistory.setUdf5Code(originalInvBinLocHistory.getUdf5Code());
			invBinLocHistory.setQtyOnHand(originalInvBinLocHistory.getQtyOnHand());
			invBinLocHistory.setQtyAlloc(originalInvBinLocHistory.getQtyAlloc());
			invBinLocHistory.setActionCode(originalInvBinLocHistory.getActionCode());
			invBinLocHistory.setQtyMoved(originalInvBinLocHistory.getQtyMoved());
			invBinLocHistory.setHistText(originalInvBinLocHistory.getHistText());
			invBinLocHistory.setTransactionDate(originalInvBinLocHistory.getTransactionDate());
			invBinLocHistory.setIcCode(originalInvBinLocHistory.getIcCode());
			invBinLocHistory.setTransactionTime(originalInvBinLocHistory.getTransactionTime());
			invBinLocHistory.setUserId(originalInvBinLocHistory.getUserId());
			invBinLocHistory.setReasonCode(originalInvBinLocHistory.getReasonCode());
			invBinLocHistory.setIcPoHeader(originalInvBinLocHistory.getIcPoHeader());
			invBinLocHistory.setIcDsbHeader(originalInvBinLocHistory.getIcDsbHeader());
			invBinLocHistory.setIcDsbLine(originalInvBinLocHistory.getIcDsbLine());
			invBinLocHistory.setDuomQtyOnHand(originalInvBinLocHistory.getDuomQtyOnHand());
			invBinLocHistory.setDuomQtyAlloc(originalInvBinLocHistory.getDuomQtyAlloc());

			incomingRequest.put("invBinLocHistory", invBinLocHistory);

			InvBinLocHistoryAdd addTask = new InvBinLocHistoryAdd();
			invBinLocHistory = (InvBinLocHistory) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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