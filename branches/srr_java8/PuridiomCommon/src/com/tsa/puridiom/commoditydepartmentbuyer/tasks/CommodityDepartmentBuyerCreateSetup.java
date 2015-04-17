

package com.tsa.puridiom.commoditydepartmentbuyer.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.util.Map;

public class CommodityDepartmentBuyerCreateSetup extends Task
{

	public Object executeTask (Object object) throws Exception
	{
		Object result = null;
		try
		{
			Map incomingRequest = (Map) object;
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

			incomingRequest.put("CommodityDepartmentBuyer_icHeader", ukg.getUniqueKey().toString());

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}