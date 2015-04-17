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
public class UpdateUserRole extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		boolean disconnect = false;

		try
		{
			LDAPModificationSet modifications = new LDAPModificationSet();
			LDAPAttribute attribute = null;
			String	entryDN = (String) incomingRequest.get("entryDN");
			String	oValues[] = this.getArrayParameter(incomingRequest, "oValues");
			String	ridValues[] = this.getArrayParameter(incomingRequest, "ridValues");
			String	descriptionValues[] = this.getArrayParameter(incomingRequest, "descriptionValues");
			String	objectClassValues[] = {"top", "puridiomRole"};

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

			if (entryDN == null)
			{
				entryDN = "rid=" + ridValues[0] + ",ou=Roles,o=" + oValues[0] + "," + LDAPProperties.getInstance().getLDAPSearchbase();
			}

			attribute = new LDAPAttribute("objectclass", objectClassValues);
			modifications.add(LDAPModification.REPLACE, attribute);
			if (ridValues != null)
			{
				attribute = new LDAPAttribute("rid", ridValues);
				modifications.add(LDAPModification.REPLACE, attribute);
			}
			if (descriptionValues != null)
			{
				attribute = new LDAPAttribute("description", descriptionValues);
				modifications.add(LDAPModification.REPLACE, attribute);
			}

			conn.modify(entryDN, modifications);

//			now update the security access entries
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
					modifications = new LDAPModificationSet();

					accessType = (String) iterator.next();
					accessLevel = (String) accessProperties.get(accessType);

					newSecurityDN = "accessType=" + accessType + ", rid=" + ridValues[0] + ",ou=Roles,o=" + oValues[0] + "," + LDAPProperties.getInstance().getLDAPSearchbase();

					if (accessLevel == null || accessLevel.trim().length() == 0)
					{
						conn.delete(newSecurityDN);
					}
					else
					{
						accessTypeValues = new String[1];
						accessLevelValues = new String[1];

						accessTypeValues[0] = accessType;
						accessLevelValues[0] = accessLevel;

						attribute = new LDAPAttribute("rid", ridValues);
						modifications.add(LDAPModification.REPLACE, attribute);

						attribute = new LDAPAttribute("accessType", accessTypeValues);
						modifications.add(LDAPModification.REPLACE, attribute);

						attribute = new LDAPAttribute("accessLevel", accessLevelValues);
						modifications.add(LDAPModification.REPLACE, attribute);

						attribute = new LDAPAttribute("objectclass", objectClassValues);
						modifications.add(LDAPModification.REPLACE, attribute);

						conn.modify(newSecurityDN, modifications);
					}
				 }
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
			return incomingRequest;
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
