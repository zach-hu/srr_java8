/*
 * Created on Dec 8, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventoryDisbursementLineRenumber.java
 */
 
package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class DisbLineRenumber extends Task
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
			List disbLines = (List)incomingRequest.get("disbLines");
			for(int i = 0; i < disbLines.size(); i++)
			{
				DisbLine disbLine = (DisbLine)disbLines.get(i);
				disbLine.setLineNumber(new BigDecimal(i + 1));
				disbLines.set(i, disbLine);
			}
			ret = disbLines;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		finally
		{
			return ret;
		}
	}
}
