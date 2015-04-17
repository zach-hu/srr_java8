package com.tsagate.foundation.security.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.service.properties.LDAPProperties;
import java.util.*;
import netscape.ldap.*;

/**
 * Creation date: September 2003
 * @author: Kelli Knisely
 */
public class AddUserRoleEntry extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Boolean	success = Boolean.FALSE;
		LDAPConnection conn = null;

		try
		{
			GetAuthenticatedLDAPConnection getConnectionTask = new GetAuthenticatedLDAPConnection();
			conn = (LDAPConnection) getConnectionTask.executeTask(new HashMap());

			if (conn == null)	throw new Exception("Unable to connect Exception");

			LDAPAttributeSet attributeSet = new LDAPAttributeSet();
			LDAPAttribute attribute = null;
			LDAPEntry entry = null;

			String	oValues[] = this.getArrayParameter(incomingRequest, "oValues");
			String	ridValues[] = this.getArrayParameter(incomingRequest, "ridValues");
			String	descriptionValues[] = this.getArrayParameter(incomingRequest, "descriptionValues");
			String	objectClassValues[] = {"top", "puridiomRole"};

			if (ridValues != null)
			{
				attribute = new LDAPAttribute("rid", ridValues);
				attributeSet.add(attribute);
			}
			if (descriptionValues != null)
			{
				attribute = new LDAPAttribute("description", descriptionValues);
				attributeSet.add(attribute);
			}

			attribute = new LDAPAttribute("objectClass", objectClassValues);
			attributeSet.add(attribute);

			String	newDN = "rid=" + ridValues[0] + ",ou=Roles,o=" + oValues[0] + "," + LDAPProperties.getInstance().getLDAPSearchbase();

			entry = new LDAPEntry(newDN, attributeSet);

			conn.add(entry);

			// now add the security access entries
			Map	accessProperties = (HashMap) incomingRequest.get("accessPropertyValues");

			if (accessProperties != null)
			{
				objectClassValues = new String[2];
				objectClassValues[0] = "top";
				objectClassValues[1] = "puridiomSecurity";

				Iterator iterator = accessProperties.keySet().iterator();
				String	newSecurityDN = "";
				String	accessType = "";
				String	accessLevel = "";
				String	accessTypeValues[] = new String[1];
				String	accessLevelValues[] = new String[1];
				int	i = 0;

				while (iterator.hasNext())
				{
					attributeSet = new LDAPAttributeSet();

					accessType = (String) iterator.next();
					accessLevel = (String) accessProperties.get(accessType);

					accessTypeValues = new String[1];
					accessLevelValues = new String[1];

					accessTypeValues[0] = accessType;
					accessLevelValues[0] = accessLevel;

					attribute = new LDAPAttribute("rid", ridValues);
					attributeSet.add(attribute);

					attribute = new LDAPAttribute("accessType", accessTypeValues);
					attributeSet.add(attribute);

					if (accessLevel != null && accessLevel.trim().length() > 0)
					{
						attribute = new LDAPAttribute("accessLevel", accessLevelValues);
						attributeSet.add(attribute);
					}

					attribute = new LDAPAttribute("objectclass", objectClassValues);
					attributeSet.add(attribute);

					newSecurityDN = "accessType=" + accessType + ", rid=" + ridValues[0] + ",ou=Roles,o=" + oValues[0] + "," + LDAPProperties.getInstance().getLDAPSearchbase();

					entry = new LDAPEntry(newSecurityDN, attributeSet);

					conn.add(entry);
				}
			}

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
			return success;
		}
	}

	private String[] getArrayParameter(Map parameters, String parameterName)
	{
		//system.out.println("parameter: " + parameterName);
		if (!parameters.containsKey(parameterName))
		{
			return null;
		}
		else
		{
			String	array[] =	(String[]) parameters.get(parameterName);
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
}
