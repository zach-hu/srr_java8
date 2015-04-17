/**
 * 
 */
package com.tsa.puridiom.commodity.tasks;

import java.text.DecimalFormat;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class CommoditySetNIGPLevel extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			String commodity = (String) incomingRequest.get("Commodity_commodity");
			String fCommodity = new DecimalFormat("00000").format(Long.parseLong(commodity));
			String commodityNigPlevel;
			String commodityLevel1;
			String commodityLevel2;

			if (fCommodity.endsWith("00"))
			{
				commodityNigPlevel = "1";
			} else
			{
				commodityNigPlevel = "2";
			}

			commodityLevel1 = fCommodity.substring(0, 3) + "00";
			commodityLevel2 = fCommodity;

			incomingRequest.put("Commodity_commodity", fCommodity);
			incomingRequest.put("Commodity_nigplevel", commodityNigPlevel);
			incomingRequest.put("Commodity_level1", commodityLevel1);
			incomingRequest.put("Commodity_level2", commodityLevel2);

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "CommoditySetNIGPLevel error " + e.getMessage());

			throw e;
		}

		return result;
	}

}
