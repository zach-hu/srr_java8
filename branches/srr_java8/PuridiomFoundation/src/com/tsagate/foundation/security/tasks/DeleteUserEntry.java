package com.tsagate.foundation.security.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.service.properties.LDAPProperties;
import netscape.ldap.*;
import java.util.*;

/**
 * Creation date: August 2003
 * @author: Kelli Knisely
 */
public class DeleteUserEntry extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Boolean success = Boolean.FALSE;
		LDAPConnection conn = null;
		
		try 
		{
			GetAuthenticatedLDAPConnection getConnectionTask = new GetAuthenticatedLDAPConnection();
			Map connParameters = new HashMap();
			conn = (LDAPConnection) getConnectionTask.executeTask( new HashMap());
			
			if (conn == null)	throw new Exception("Unable to connect Exception");
			
			String	uid = (String) incomingRequest.get("User_userId");
			String	dn ="uid=" + uid + "," + LDAPProperties.getInstance().getLDAPSearchbase();
			
			conn.delete(dn);
			
			success = Boolean.TRUE;
			this.status = Status.SUCCEEDED;
		}
		catch (LDAPException e)
		{
			e.printStackTrace();
			success = Boolean.FALSE;
			this.status = Status.FAILED;
		}
		finally
		{
			return success;
		}
	}
	
}
