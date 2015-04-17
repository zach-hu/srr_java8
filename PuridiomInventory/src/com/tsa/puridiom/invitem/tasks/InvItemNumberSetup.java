package com.tsa.puridiom.invitem.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsa.puridiom.common.documents.AutoGenType;

/**
 * @author Jeff
 */
public class InvItemNumberSetup extends Task
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

			String  fiscalYear = "XXXX";
			String	docType = "INV" ;

			incomingRequest.put("AutoGen_documentType",  docType) ;
			incomingRequest.put("AutoGen_genYear", fiscalYear) ;

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
