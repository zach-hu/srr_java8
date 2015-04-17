package com.tsa.puridiom.invbinlochistory.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.math.BigDecimal;
import java.util.Map;

public class InvBinLocHistorySetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String userId = (String) incomingRequest.get("userId");
			incomingRequest.put("InvBinLocHistory_itemNumber", "");
			incomingRequest.put("InvBinLocHistory_itemLocation", "");
			incomingRequest.put("InvBinLocHistory_vendorId", "");
			incomingRequest.put("InvBinLocHistory_manufactNo", "");
			incomingRequest.put("InvBinLocHistory_lot", "");
			incomingRequest.put("InvBinLocHistory_cost", "0");
			incomingRequest.put("InvBinLocHistory_itemDate", Dates.today(""));
			incomingRequest.put("InvBinLocHistory_aisle", "");
			incomingRequest.put("InvBinLocHistory_locrow", "");
			incomingRequest.put("InvBinLocHistory_tier", "");
			incomingRequest.put("InvBinLocHistory_bin", "");
			incomingRequest.put("InvBinLocHistory_udf1Code", "");
			incomingRequest.put("InvBinLocHistory_udf2Code", "");
			incomingRequest.put("InvBinLocHistory_udf3Code", "");
			incomingRequest.put("InvBinLocHistory_udf4Code", "");
			incomingRequest.put("InvBinLocHistory_udf5Code", "");
			incomingRequest.put("InvBinLocHistory_qtyOnHand", "0");
			incomingRequest.put("InvBinLocHistory_qtyAlloc", "0");
			incomingRequest.put("InvBinLocHistory_actionCode", "");
			incomingRequest.put("InvBinLocHistory_qtyMoved", "0");
			incomingRequest.put("InvBinLocHistory_histText", "");
			incomingRequest.put("InvBinLocHistory_transactionDate", Dates.today(""));
			incomingRequest.put("InvBinLocHistory_icCode", "0");
			incomingRequest.put("InvBinLocHistory_transactionTime", "");
			incomingRequest.put("InvBinLocHistory_userId", userId);
			incomingRequest.put("InvBinLocHistory_reasonCode", "");
			incomingRequest.put("InvBinLocHistory_icPoHeader", "0");
			incomingRequest.put("InvBinLocHistory_icDsbHeader", "0");
			incomingRequest.put("InvBinLocHistory_icDsbLine", "0");
			incomingRequest.put("InvBinLocHistory_duomQtyOnHand", "0");
			incomingRequest.put("InvBinLocHistory_duomQtyAlloc", "0");

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