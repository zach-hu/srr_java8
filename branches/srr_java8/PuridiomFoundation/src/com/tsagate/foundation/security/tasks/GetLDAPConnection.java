package com.tsagate.foundation.security.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.service.properties.LDAPProperties;
import com.tsagate.foundation.utility.Log ;
import java.util.Map;
import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPException;

/**
 * Creation date: August 2003
 * @author: Kelli Knisely
 */
public class GetLDAPConnection extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		LDAPConnection conn = null;
		LDAPProperties ldap = LDAPProperties.getInstance();
		String	err = "";
		this.status = Status.SUCCEEDED;

		try
		{
			Log.debug(this,"LDAP Connection: Host: " + ldap.getLDAPHost()) ;
			Log.debug(this,"LDAP Connection: Port: " + ldap.getLDAPPort()) ;
			Log.debug(this,"LDAP Connection: User: " + ldap.getLDAPConnectUser()) ;

			conn = new LDAPConnection();
			if (ldap.getLDAPConnectUser() == null || ldap.getLDAPConnectUser().length() == 0) {
				conn.connect(ldap.getLDAPHost(), ldap.getLDAPPort());
			} else {
				String			lsPw = (String) incomingRequest.get("LDAPConnectPw") ;
				conn.connect(3,ldap.getLDAPHost(), ldap.getLDAPPort(), ldap.getLDAPConnectUser(),lsPw);
			}
		}
		catch (LDAPException e)
		{
			switch (e.getLDAPResultCode()) {
			case LDAPException.INVALID_CREDENTIALS:
				err = "Connection failed authenticate do to Invalid credentials!" ;
				break;
			case LDAPException.CONNECT_ERROR:
				err = "Authentication failed! Unable to connect to authentication server." ;
				break;
			default:
				err = "Unable to connect to authentication.server (" + e.getLDAPResultCode() + ")" ;
				break;
			}
			this.status = Status.FAILED;
		}

		try {
			if (conn == null || !conn.isConnected()) {
				String	alternateHost = ldap.getLDAPAlternateHost() ;
				if (alternateHost == null) alternateHost = "" ;
				if (alternateHost.trim().length() > 0) {
					// Try alternate if available
					conn = new LDAPConnection() ;
					if (ldap.getLDAPConnectUser() == null) {
						conn.connect(alternateHost, ldap.getLDAPPort());
					} else {
						String			lsPw = (String) incomingRequest.get("LDAPConnectPw") ;
						conn.connect(3,alternateHost, ldap.getLDAPPort(), ldap.getLDAPConnectUser(),lsPw);
					}
				}
			}

			if (conn == null || !conn.isConnected())
			{
				this.status = Status.FAILED;
				throw new Exception("Authentication failed! Unable to connect to authentication server.");
			} else {
    			Log.debug(this,"LDAP Connection successful!") ;
			}

			incomingRequest.put("LDAPConnection", conn);
			this.status = Status.SUCCEEDED;
		}

		catch (LDAPException e)
		{
			switch (e.getLDAPResultCode()) {
			case LDAPException.INVALID_CREDENTIALS:
				err = "Connection failed authenticate do to Invalid credentials!" ;
				System.out.println("LDAP Error: " + err) ;
				break;
			case LDAPException.CONNECT_ERROR:
				err = "Authentication failed! Unable to connect to authentication server!" ;
				System.out.println("LDAP Error: " + err) ;
				break;
			default:
				err = "Unable to connect to authentication.server (" + e.getLDAPResultCode() + ")" ;
				System.out.println("LDAP Error: " + err) ;
				break;
			}
			this.status = Status.FAILED;
		}

		if (this.status == Status.FAILED)
		{
			this.status = Status.FAILED;
			throw new Exception (err);
		}

		return conn;
	}
}