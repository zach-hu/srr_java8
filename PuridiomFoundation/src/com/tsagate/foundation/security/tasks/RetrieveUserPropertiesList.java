package com.tsagate.foundation.security.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.service.properties.LDAPProperties;
import java.util.*;
import netscape.ldap.*;
import netscape.ldap.controls.*;

/**
 * Creation date: August 2003
 * @author: Kelli Knisely
 */
public class RetrieveUserPropertiesList extends Task
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

			LDAPSortKey sortBy = new LDAPSortKey("uid");
			LDAPSortControl sortCtrl = new LDAPSortControl(sortBy, true);
			LDAPSearchConstraints cons = conn.getSearchConstraints();

			cons.setServerControls(sortCtrl);

			HashMap filterMap = (HashMap) incomingRequest.get("filterCriteria");
			String	base = LDAPProperties.getInstance().getLDAPSearchbase();			
			String	uid = null;
			String	filter = "";

			if (filterMap != null)
			{
				Iterator keys = filterMap.keySet().iterator();
				while (keys.hasNext())
				{
					String key = (String) keys.next();
					String value = (String) filterMap.get(key);
					
					if (key == "uid") {
						uid = value;
					}
					else {
						filter = filter + " (" + key + "=" + value + ")";
					}
				}		
			}
			
			if (uid == null || uid.trim().length() == 0)
			{
				uid = "*";
			}
			
			filter = "(& (uid=" + uid + ")" + filter + ")";
			
			/* Perform the search */
			//LDAPSearchResults res = conn.search( base, LDAPv2.SCOPE_SUB, filter, null, false, cons );
			LDAPSearchResults res = conn.search( base, LDAPv2.SCOPE_SUB, filter, null, false );
	
			/* Get the individual results */ 
			while (res.hasMoreElements())
			{
				LDAPEntry findEntry = (LDAPEntry) res.next();
				LDAPAttributeSet attributeSet = findEntry.getAttributeSet();
				HashMap userProperties = new HashMap();
				
				HashMap conversionParameters = new HashMap();
				conversionParameters.put("attributeSet", attributeSet);

				ConvertAttributeSetToHashMap conversionTask = new ConvertAttributeSetToHashMap();

				userProperties = (HashMap) conversionTask.executeTask(conversionParameters);
				
				list.add(userProperties);
			}

			if (conn != null)
			{
				DisconnectLDAPConnection disconnectTask = new DisconnectLDAPConnection();
				incomingRequest = (HashMap) disconnectTask.executeTask(incomingRequest);
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