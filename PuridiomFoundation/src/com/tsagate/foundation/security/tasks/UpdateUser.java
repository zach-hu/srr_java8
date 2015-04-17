package com.tsagate.foundation.security.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.service.properties.LDAPProperties;
import java.util.*;
import netscape.ldap.*;

/**
 * Creation date: August 2003
 * @author: Kelli Knisely
 */
public class UpdateUser extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Boolean success = Boolean.FALSE;
		boolean disconnect = false;
		
		try 
		{
			LDAPModificationSet modificationSet = new LDAPModificationSet();
			LDAPAttribute attribute = null;
			LDAPEntry	entry = null;
			
			Set	keySet = incomingRequest.keySet();
			Iterator	iterator = keySet.iterator();
			String	key = "";
			String	attributeName = "";
			String	uid = "";

			while (iterator.hasNext())
			{
				key = (String) iterator.next();
				if (key.indexOf("Values") > 0)
				{
					String values[] = this.getArrayParameter((String[]) incomingRequest.get(key));
	
					if (values != null)
					{
						attributeName = key.substring(0, key.length() - 6);
						attribute = new LDAPAttribute(attributeName, values);
						modificationSet.add(LDAPModification.REPLACE, attribute);
			
						if (attributeName.equals("uid"))
						{
							uid = values[0];
						}
					}
				}
			}
			
			String	newDN = (String) incomingRequest.get("newDN");
			
			if (newDN == null || newDN.trim().length() == 0)
			{
				newDN =  "uid=" + uid + ", " + LDAPProperties.getInstance().getLDAPSearchbase();
			}
			
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
			
			if (conn == null)
			{
				this.status = Status.FAILED;
				throw new Exception ("Unable to connect to LDAP.");
			}

			conn.modify(newDN, modificationSet);
			
			success = Boolean.TRUE;
			this.status = Status.SUCCEEDED;
		}
		catch (LDAPException e)
		{
			success = Boolean.FALSE;
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
			return success;
		}
	}
	
	private String[] getArrayParameter(String[] array)
	{
		String	value = "";
		boolean	valid = false;
		
		for (int i=0; i < array.length; i++)
		{
			value = array[i];
			
			if (value != null && value.trim().length() > 0)
			{
				valid = true;
				break;
			}
		}
		
		if (valid)
		{
			return array;
		}
		else
		{
			return null;
		}
	}
}
