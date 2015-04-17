package com.tsa.puridiom.invitem.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import java.util.*;

/**
 * @author Renzo
 */
public class InvItemNumberFormatSetup extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
			String	itemNumber = (String) incomingRequest.get("InvItem_itemNumber");
			incomingRequest.put("documentNumber", itemNumber);

			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}
}
