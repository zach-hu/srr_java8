package com.tsagate.foundation.security.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.service.properties.LDAPProperties;
import java.util.*;
import netscape.ldap.*;

/**
 * Creation date: November 2003
 * @author: Kelli Knisely
 */
public class ValidOrganizationCheck extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Boolean result = Boolean.FALSE;
		boolean disconnect = false;
		
		try 
		{
			String	o = (String) incomingRequest.get("o");
			String	dn = "o=" + o + "," + LDAPProperties.getInstance().getLDAPSearchbase();
			LDAPConnection conn = (LDAPConnection) incomingRequest.get("LDAPConnection");
			
			if (conn == null)
			{
				GetLDAPConnection getConnectionTask = new GetLDAPConnection();
				conn = (LDAPConnection) getConnectionTask.executeTask(incomingRequest);
				
				if (conn == null)
				{
					this.status = Status.FAILED;
					throw new Exception("Unable to connect to LDAP.");
				}
				else
				{
					incomingRequest.put("LDAPConnection", conn);
					disconnect = true;
				}
			}
			
			LDAPEntry entry = conn.read(dn);

			if (entry != null)
			{
				result = Boolean.TRUE;
			}
			else
			{
				result = Boolean.FALSE;
			}
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		finally
		{
			if (disconnect)
			{
				try
				{
					DisconnectLDAPConnection disconnectTask = new DisconnectLDAPConnection();
					incomingRequest = (Map) disconnectTask.executeTask(incomingRequest);
				}
				catch (LDAPException ldape)
				{
					ldape.printStackTrace();
				}
			}
			return result;
		}
	}
}