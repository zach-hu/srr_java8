package com.tsagate.foundation.security.tasks.test;

import netscape.ldap.LDAPConnection;
import com.tsagate.foundation.security.tasks.GetAuthenticatedLDAPConnection;
import java.util.*;

public class GetAuthenticatedLDAPConnectionTest {

	public GetAuthenticatedLDAPConnectionTest()
	{
	}
	
	public static void main(String args[]) throws Exception 
	{
		try 
		{
			Map request = new HashMap();
			GetAuthenticatedLDAPConnection getConnectionTask = new GetAuthenticatedLDAPConnection();
			LDAPConnection ldapConnection = (LDAPConnection) getConnectionTask.executeTask(request); 
			
			if (ldapConnection != null)
			{
				//system.out.println("SUCCESS!");
			}
		}
		catch(Exception e)
		{
			//system.out.println("FAILURE: " + e.getMessage());
			e.printStackTrace();
		}
		
		//system.out.println("TEST COMPLETE!");
	}
	
}