package com.tsa.puridiom.invbinlochistory.tasks;

import com.tsa.puridiom.entity.InvBinLocHistory;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * InvBinLocationAddSetup.java
 * @author renzo
 * Jan 16, 2004
 */

public class InvBinLocHistoryAddSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Object result = null;

		try
		{
            Map incomingRequest = (Map)object;
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
			InvBinLocHistory invBinLocHistory = new InvBinLocHistory();

			UniqueKeyGenerator uk = UniqueKeyGenerator.getInstance();
			BigDecimal icCode = new BigDecimal(uk.getUniqueKey().toString());
			invBinLocHistory.setIcCode(icCode) ;
			incomingRequest.put("InvBinLocHistory_icCode", icCode.toString());

			result = invBinLocHistory;
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return result;
	}

}