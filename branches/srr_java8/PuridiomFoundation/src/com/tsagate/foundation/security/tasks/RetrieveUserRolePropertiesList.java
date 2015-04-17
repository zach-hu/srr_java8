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
public class RetrieveUserRolePropertiesList extends Task
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

			LDAPSortKey sortBy = new LDAPSortKey("rid");
			LDAPSortControl sortCtrl = new LDAPSortControl(sortBy, true);
			LDAPSearchConstraints cons = conn.getSearchConstraints();

			cons.setServerControls(sortCtrl);

			String o = (String) incomingRequest.get("o");
			String rid = (String) incomingRequest.get("rid");
			HashMap filterMap = (HashMap) incomingRequest.get("filterCriteria");
			HashMap excludeMap = (HashMap) incomingRequest.get("excludeCriteria");
			String	base = "ou=Roles,o=" + o +"," + LDAPProperties.getInstance().getLDAPSearchbase();

			if (rid == null || rid.trim().length() == 0)
			{
				rid = "*";
			}

			String	classes[] = {"top", "puridiomRole"};
			String	filter = "(rid=" + rid + ") (!(objectClass=puridiomSecurity))" ;

			if (filterMap != null)
			{
				Iterator keys = filterMap.keySet().iterator();
				while (keys.hasNext())
				{
					String key = (String) keys.next();
					String value = (String) filterMap.get(key);

					filter = filter + " (" + key + "=" + value + ")";
				}
			}

			if (excludeMap != null)
			{
				Iterator keys = excludeMap.keySet().iterator();
				while (keys.hasNext())
				{
					String key = (String) keys.next();
					String value = (String) excludeMap.get(key);

					int ind = value.indexOf(",");
					while (ind > 0)
					{
						filter = filter + " (!(" + key + "=" + value.substring(0, ind) + "))";

						value = value.substring(ind + 1);
						ind = value.indexOf(",");
					}

					filter = filter + " (!(" + key + "=" + value + "))";
				}
			}

			filter = "(& " + filter + ")";

			/* Perform the search */
			//LDAPSearchResults res = conn.search( base, LDAPv2.SCOPE_SUB, filter, null, false, cons );
			LDAPSearchResults res = conn.search( base, LDAPv2.SCOPE_SUB, filter, null, false );

			/* Get the individual results */
			while (res.hasMoreElements())
			{
				LDAPEntry findEntry = (LDAPEntry) res.next();
				LDAPAttributeSet attributeSet = findEntry.getAttributeSet();
				HashMap roleProperties = new HashMap();

				HashMap conversionParameters = new HashMap();
				conversionParameters.put("attributeSet", attributeSet);

				ConvertAttributeSetToHashMap conversionTask = new ConvertAttributeSetToHashMap();

				roleProperties = (HashMap) conversionTask.executeTask(conversionParameters);

				list.add(roleProperties);
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