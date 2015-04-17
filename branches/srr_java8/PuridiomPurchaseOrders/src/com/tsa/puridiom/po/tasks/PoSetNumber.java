/*
 * Created on Dec 8, 2003
 * @author renzo
 * com.tsa.puridiom.po.tasks.InventorySetDisbursementNumber.java
 */

package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class PoSetNumber extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			String poNumber = (String)incomingRequest.get("PoHeader_poNumber");
			poHeader.setPoNumber(poNumber);
			ret = poHeader;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
