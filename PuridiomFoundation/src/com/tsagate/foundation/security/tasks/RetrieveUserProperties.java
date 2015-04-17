package com.tsagate.foundation.security.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.service.properties.LDAPProperties;
import java.util.*;
import netscape.ldap.LDAPAttributeSet;
import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPEntry;
import netscape.ldap.LDAPException;

/**
 * Creation date: August 2003
 * @author: Kelli Knisely
 */
public class RetrieveUserProperties extends Task
{
	private String LDAPSearchbase = LDAPProperties.getInstance().getLDAPSearchbase();

	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		HashMap connectionParameters = new HashMap();
		boolean	newConnection = false;
		LDAPConnection conn = null;
			
		try 
		{
			String userId = (String) incomingRequest.get("userId");
			String dn = (String) incomingRequest.get("dn");
			HashMap userProperties = new HashMap();
						
			conn = (LDAPConnection) incomingRequest.get("LDAPConnection");
			
			if (conn == null)
			{
				GetLDAPConnection getConnectionTask = new GetLDAPConnection();
				conn = (LDAPConnection) getConnectionTask.executeTask(connectionParameters);
				connectionParameters.put("LDAPConnection", conn);
				
				newConnection = true;
			}
			if (conn == null)
			{
				this.status = Status.FAILED;
				throw new Exception("Unable to connect Exception");
			}
			
			if (dn == null)
			{
				dn = "uid=" + userId + "," + LDAPSearchbase;
			}
				
			LDAPEntry entry = conn.read(dn);
			LDAPAttributeSet mainAttributeSet = null;
			
			if (entry == null)
			{
				this.status = Status.FAILED;
				throw new Exception("Entry not found");
			}
			
			mainAttributeSet = entry.getAttributeSet();
			
			HashMap conversionParameters = new HashMap();
			conversionParameters.put("attributeSet", mainAttributeSet);
			
			ConvertAttributeSetToHashMap conversionTask = new ConvertAttributeSetToHashMap();
			
			userProperties = (HashMap) conversionTask.executeTask(conversionParameters);
			
			incomingRequest.put("userProperties", userProperties);
			
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		finally
		{
			if (newConnection && conn != null)
			{
				try
				{
					DisconnectLDAPConnection disconnectTask = new DisconnectLDAPConnection();
					disconnectTask.executeTask(connectionParameters);
				}
				catch (LDAPException e)
				{
					e.printStackTrace();
				}
			}
			
			return incomingRequest;
		}
	}
}