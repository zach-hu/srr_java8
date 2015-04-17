package com.tsagate.foundation.security.tasks;

import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.service.properties.LDAPProperties;
import com.tsagate.foundation.utility.Log;
import java.util.*;


/**
 * Creation date: August 2003
 * @author: Jeff Knisely
 */
public class LDAPToUserProfileSetup extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Boolean success = Boolean.FALSE;

		HashMap ldapUserProperties = (HashMap) incomingRequest.get("ldapUserProperties") ;
		HashMap userProperties = (HashMap) ldapUserProperties.get("userProperties") ;
		LDAPProperties ldap = LDAPProperties.getInstance();
		Map	columnMap = ldap.getLDAPColumnMap() ;

		// Map Ldap attributes to database columns
		Collection c = columnMap.keySet() ;
		Iterator it = c.iterator() ;
		while (it.hasNext()) {
			String 	key = (String) it.next() ;
			String	attr = (String) columnMap.get(key) ;
			Object   prop = userProperties.get(attr) ;
			String	value = null ;

			if (prop == null) {
				value = "";
			} else {
				Class    cls = prop.getClass() ;
				String    type = (String) cls.getName() ;
				if (type.indexOf("String") >= 0) {
					value = (String) userProperties.get(attr) ;
				} else {
					List al = (List) userProperties.get(attr) ;
					if (al.size() >= 0) {
						value = (String) al.get(0) ;
					}
				}
			}

			if (value == null) value = "" ;
			if (key.equalsIgnoreCase("userId") && value.length() > 15) {
				value = value.substring(0, 15) ;
			}

			Log.debug(this,"key=" + key + "  Value=" + value) ;

			incomingRequest.put("UserProfile_" + key, value) ;
		}
		// Force user profile review off for update
		incomingRequest.put("UserProfile_reviewProfile","N") ;
		incomingRequest.put("UserProfile_organizationId",ldap.getLDAPOrganization()) ;
		incomingRequest.put("UserProfile_userPassword",(String) incomingRequest.get("password")) ;
		incomingRequest.put("userId",(String) incomingRequest.get("UserProfile_userId")) ;

		return success;
	}

}
