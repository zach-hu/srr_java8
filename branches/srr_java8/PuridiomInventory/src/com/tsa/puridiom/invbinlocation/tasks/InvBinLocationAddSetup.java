package com.tsa.puridiom.invbinlocation.tasks;

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

public class InvBinLocationAddSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Object result = null;

		try
		{
            Map incomingRequest = (Map)object;
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
			InvBinLocation invBinLocation = new InvBinLocation();

			UniqueKeyGenerator uk = UniqueKeyGenerator.getInstance();
			BigDecimal icRc = new BigDecimal(uk.getUniqueKey().toString());
			invBinLocation.setIcRc(icRc);
			invBinLocation.setItemDate(Dates.getCurrentDate(userTimeZone));

			result = invBinLocation;
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return result;
	}

}