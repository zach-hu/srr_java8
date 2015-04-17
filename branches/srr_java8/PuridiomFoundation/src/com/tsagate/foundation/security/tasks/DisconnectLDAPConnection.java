package com.tsagate.foundation.security.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;
import netscape.ldap.LDAPConnection;

/**
 * Creation date: August 2003
 * @author: Kelli Knisely
 */
public class DisconnectLDAPConnection extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		
		try 
		{
			LDAPConnection conn = null;

			if (incomingRequest.containsKey("LDAPConnection"))
			{
				conn = (LDAPConnection) incomingRequest.get("LDAPConnection");
			}
			
			if (conn != null)
			{
				conn.disconnect();
			}
			
			incomingRequest.remove("LDAPConnection");
			
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		finally
		{
			return incomingRequest;
		}
	}
}