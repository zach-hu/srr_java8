package com.tsa.puridiom.alert.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.*;

public class AlertSetFromRequest extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{

		}

		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		this.setStatus(Status.SUCCEEDED) ;

		return result ;
	}
}