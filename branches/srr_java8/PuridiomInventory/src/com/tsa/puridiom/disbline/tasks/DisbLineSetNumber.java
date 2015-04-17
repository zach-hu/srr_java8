/*
 * Created on Dec 8, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventorySetDisbursementNumber.java
 */
 
package com.tsa.puridiom.disbline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;


public class DisbLineSetNumber extends Task
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
			List disbLineList = (List) incomingRequest.get("disbLines");
			
			if (disbLineList != null)
			{
				String disbNumber = (String)incomingRequest.get("DisbHeader_disbNumber");
				for (int i = 0; i < disbLineList.size(); i++)
				{
					DisbLine disbLine = (DisbLine) disbLineList.get(i);
					disbLine.setDisbNumber(disbNumber);
					disbLineList.set(i, disbLine);
				}
			}
			
			ret = disbLineList;
			incomingRequest.put("disbLines", disbLineList);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
		    this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}
}
