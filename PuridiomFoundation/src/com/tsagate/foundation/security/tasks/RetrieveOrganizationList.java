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
public class RetrieveOrganizationList extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List	list = new ArrayList();	
		boolean disconnect = false;
		
		try 
		{
			LDAPConnection conn = (LDAPConnection) incomingRequest.get("LDAPConnection");
			if (conn == null)
			{
				GetAuthenticatedLDAPConnection getConnectionTask = new GetAuthenticatedLDAPConnection();
				conn = (LDAPConnection) getConnectionTask.executeTask(new HashMap());
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

			String	base = LDAPProperties.getInstance().getLDAPSearchbase();
			String	filter = "(&(o=*) (objectClass=organization))" ;
			
			/* Perform the search */
			LDAPSearchResults res = conn.search( base, LDAPv2.SCOPE_SUB, filter, null, false );
	
			/* Get the individual results */ 
			while (res.hasMoreElements())
			{
				LDAPEntry findEntry = (LDAPEntry) res.next();
				LDAPAttributeSet attributeSet = findEntry.getAttributeSet();
				
				String	oValues[] = attributeSet.getAttribute("o").getStringValueArray();
				String	o = oValues[0];

				list.add(o);
			}			
			this.status = Status.SUCCEEDED;
		}
		catch (LDAPException e)
		{
			this.status = Status.FAILED;
			throw new Exception(e);
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
			
			return list;
		}
	}
}