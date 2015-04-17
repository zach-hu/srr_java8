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
public class RetrieveUserRoleProperties extends Task
{
	public RetrieveUserRoleProperties()
	{
		//do nothing
	}
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		boolean disconnect = false;

		try
		{
			HashMap userRoleProperties = new HashMap();
			String roleId = (String) incomingRequest.get("roleId");
			String o = (String) incomingRequest.get("o");
			String dn = (String) incomingRequest.get("dn");

			if (dn == null)
			{
				dn = "rid=" + roleId + ",ou=Roles,o=" + o + "," + LDAPProperties.getInstance().getLDAPSearchbase();
			}

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

			LDAPSearchResults res = conn.search( dn, LDAPv2.SCOPE_SUB, "(accessType=*)", null, false );
			LDAPAttributeSet mainAttributeSet = null;
			Map		accessProperties = new HashMap();
			String	type = "";
			String	level = "";
			String	description = "";

			/* Iterate through the results until finished. */
			while ( res.hasMoreElements() )
			{
				/* Get the next entry in the results. */
				LDAPEntry findEntry = null;
				try
				{
					findEntry = res.next();

					if (findEntry != null)
					{
						mainAttributeSet = findEntry.getAttributeSet();
						type = getAttribute(mainAttributeSet, "accessType");
						level = getAttribute(mainAttributeSet, "accessLevel");

						accessProperties.put(type, level);
					}
				}
				catch	 ( LDAPException e )
				{
					//system.out.println( "Error: " + e.toString() );
					continue;
				}
			}

			if (res != null)
			{
				String	base = "ou=Roles,o=" + o +"," + LDAPProperties.getInstance().getLDAPSearchbase();
				String	filter = "(& (rid=" + roleId + ") (!(objectClass=puridiomSecurity)) )" ;

				/* Perform the search */
				res = conn.search( base, LDAPv2.SCOPE_SUB, filter, null, false );

				/* Get the description from the puridiomRole objectClass entry */
				if (res.hasMoreElements())
				{
					LDAPEntry findEntry = (LDAPEntry) res.next();
					LDAPAttributeSet attributeSet = findEntry.getAttributeSet();

					description = getAttribute(attributeSet, "description");
				}

				userRoleProperties.put("rid", roleId);
				userRoleProperties.put("o", o);
				userRoleProperties.put("description", description);
				userRoleProperties.put("accessProperties", accessProperties);

				incomingRequest.put("userRoleProperties", userRoleProperties);
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
			return incomingRequest;
		}
	}

	public String getAttribute(LDAPAttributeSet attributeSet, String attributeName)
		throws Exception
	{
		try
		{
			String value = "";
			LDAPAttribute attribute = attributeSet.getAttribute(attributeName);

			if (attribute != null)
			{
				String[] values = attribute.getStringValueArray();
				value = values[0];
			}

			return value;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}