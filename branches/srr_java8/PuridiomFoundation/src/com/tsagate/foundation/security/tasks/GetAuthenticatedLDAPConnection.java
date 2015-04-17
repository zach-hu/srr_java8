package com.tsagate.foundation.security.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.service.properties.LDAPProperties;
import java.util.Map;
import netscape.ldap.LDAPConnection;

/**
 * Creation date: August 2003
 * @author: Kelli Knisely
 */
public class GetAuthenticatedLDAPConnection extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		LDAPConnection conn = new LDAPConnection();
		
		try 
		{
			String	userId = (String) incomingRequest.get("userId");
			String	password = (String) incomingRequest.get("password");
			String	dn = (String) incomingRequest.get("ldapDn");
	
			if (dn == null || dn.length() <= 0)
			{
				if (userId == null || password == null)
				{
					dn = "cn=Manager";
					password =  "secret";
				}
				else
				{
					dn = "uid=" + userId;
				}
			}
			
			dn = dn + "," + LDAPProperties.getInstance().getLDAPSearchbase();

			LDAPProperties ldap = LDAPProperties.getInstance();
			
			conn.connect(ldap.getLDAPHost(), ldap.getLDAPPort(), dn, password);
				
			if (conn == null)
			{
				this.status = Status.FAILED;
				throw new Exception("Unable to connect!");
			}

			if (!conn.isAuthenticated())
			{
				this.status = Status.FAILED;
				throw new Exception("Unable to Authenticate!");
			}
			
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;			
			throw e;
		}
		
		return conn;
	}
}