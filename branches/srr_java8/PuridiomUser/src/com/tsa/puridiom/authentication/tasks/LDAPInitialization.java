package com.tsa.puridiom.authentication.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.service.properties.LDAPProperties;

/**
 * Creation date: August 2003
 * @author: Kelli Knisely
 */
public class LDAPInitialization extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String						active="true" ;

		String portalLogin = (String) incomingRequest.get("portalLogin") ;
		if (portalLogin == null) portalLogin = "N" ;
		String uid = (String) incomingRequest.get("mailId");
		if (portalLogin.equalsIgnoreCase("Y")) {
			uid = (String) incomingRequest.get("userId") ;
		}
		if (uid == null)uid = "" ;
		if (! portalLogin.equalsIgnoreCase("Y")) {
			if (uid.indexOf("@") >= 1) {
				active = "false" ;
			} else {
				LDAPProperties ldap = LDAPProperties.getInstance();
				System.out.println("ldapmode=" + ldap.getLDAPMode());
				if (!ldap.isSSOMode()) {
				   if (ldap.getLDAPHost() == null || ldap.getLDAPHost().trim().length() == 0) {
					  active = "false" ;
				   } else {
					  String pass = ldap.getLDAPConnectPW();
					  
					  if (ldap.isUseBlackBox()) {
						  pass = BlackBox.getDecrypt(pass);
					  }
					  
				      incomingRequest.put("LDAPConnectPw", pass);
				   }
				}
			}
		}
		System.out.println("PortalLogin=" + portalLogin) ;
		System.out.println("LDAP Active=" + active) ;
		System.out.println("UserId=" + uid) ;
		return active ;
	}
	
	public static void main (String [] args) throws Exception {
		Map map = new HashMap();
		
		LDAPInitialization task = new LDAPInitialization();
		
		task.executeTask(map);
		
		System.out.println("LDAPConnectPw: " + map.get("LDAPConnectPw"));
	}
}
