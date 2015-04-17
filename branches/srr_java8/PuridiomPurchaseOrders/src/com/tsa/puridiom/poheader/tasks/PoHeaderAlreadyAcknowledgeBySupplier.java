/**
 * 
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class PoHeaderAlreadyAcknowledgeBySupplier extends Task
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
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			String message = "Order " + poHeader.getPoNumber() + " has already been acknowledge!";
			
			incomingRequest.put("errorMessage", message);

			Log.info(this, message);
			
			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "PoHeaderAlreadyAcknowledgeBySupplier error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

}