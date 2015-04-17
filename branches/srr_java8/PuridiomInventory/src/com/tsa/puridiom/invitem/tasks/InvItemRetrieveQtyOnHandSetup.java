package com.tsa.puridiom.invitem.tasks;

import java.util.Map;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Kelli
 */
public class InvItemRetrieveQtyOnHandSetup extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
			String	oid = (String) incomingRequest.get("organizationId") ;
			String	itemNumber = (String) incomingRequest.get("InvItem_itemNumber") ;

			incomingRequest.put("InvBinLocation_itemNumber", itemNumber) ;
			incomingRequest.put("permanentOnly", "true");
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
		}
		return null ;
	}
}
