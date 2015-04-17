package com.tsagate.foundation.security.tasks;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.service.properties.LDAPProperties;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.ProxyCall;

import netscape.ldap.*;

public class AuthenticateSSOUser extends Task {
   private String LDAPSearchbase = LDAPProperties.getInstance().getLDAPSearchbase();
   private String err = "";
   private String uid = null;
   private String password = null;
   private String userdn = null;
   private String userAttr = null;
   private String mailAttr = null;
   private String loginAttr = null;
   private String orgId = null;
   private String dn = null;
   private String ldapAuthOnly = null;
   private String ldapVersion = null;
   private String ldapReferrals = "N";
   private String ldapHopMax = "5";
   private String ldapLogging = "N";
   private String proxyUrl = null;
   private LDAPConnection conn = null;
   private boolean disconnectOnComplete = false;
   private LDAPProperties ldap = LDAPProperties.getInstance();
   private Map columnMap = ldap.getLDAPColumnMap();

   /*
    * (non-Javadoc)
    *
    * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
    */
   public Object executeTask(Object object) throws Exception {
      Map incomingRequest = (Map) object;
      Map returnObject = null;

      System.out.println("Entering AuthenticateSSOUser.executeTask - incomingParms: " + incomingRequest);

      uid = (String) incomingRequest.get((String) LDAPProperties.getInstance().getLDAPColumnMap().get("mailId"));
      password = (String) incomingRequest.get("password");
      incomingRequest.put("password", password) ;
    	  // String dn = (String) incomingRequest.get("ldapDn");

      userAttr = (String) columnMap.get("userId"); // not set (comes from rqst logind)
      mailAttr = (String) columnMap.get("mailId"); // set
      loginAttr = ldap.getLDAPLoginAttr();
      if (loginAttr == null)
         loginAttr = userAttr;

      orgId = ldap.getLDAPOrganization();
      dn = ldap.getLDAPSearchbase();
      proxyUrl = ldap.getLDAPProxyUrl();

      ldapAuthOnly = ldap.getLDAPAuthOnly();

      String userId = (String) incomingRequest.get("SSO_LoginID");
      if (userId == null) {
         userId = "";
      }

      incomingRequest.put("ldapUser",userId.toUpperCase()) ;
      incomingRequest.put("ldapAuthenticated","true") ;
      incomingRequest.put("HostUser_userId", userId.toUpperCase()) ;
      incomingRequest.put("UserProfile_userId", userId.toUpperCase()) ;
      incomingRequest.put("HostUser_mailId",(String) incomingRequest.get("SSO_Email")) ;
      incomingRequest.put("UserProfile_mailId",(String) incomingRequest.get("SSO_Email")) ;
      incomingRequest.put("mailId",(String) (String) incomingRequest.get("SSO_Email")) ;
      incomingRequest.put("HostUser_organizationId", (String) incomingRequest.get("SSO_Organization")) ;

      System.out.println("Exiting AuthenticateSSOUser.executeTask - outgoingParms: " + incomingRequest);

      /*
      HostUser_userId=(value)
      HostUser_mailId=(value)
      UserProfile_userId=(value)
      UserProfile_mailId=
      UserProfile_title=
      UserProfile_departmentCode=
      */

      returnObject = (Map) this.authenticate(incomingRequest);

      return returnObject;
   }

   private Object authenticate(Map incomingRequest) {

      Map returnObject = new HashMap();
      Map userProperties = new HashMap();

      returnObject.put("responseCode", "Ok");
      returnObject.put("userProperties", userProperties);

      // Copy SSO attributes
      java.util.Iterator iter = incomingRequest.keySet().iterator();
      while (iter.hasNext()) {
        String name = (String) iter.next();
        if (name.startsWith("SSO_")) {
           userProperties.put(name, incomingRequest.get(name));
        }
      }
      /*
      try {

         boolean oK = false;
         while (result.hasMoreElements()) {
            Object entry = result.nextElement();
            if (entry instanceof LDAPEntry) {
                  LDAPAttributeSet mainAttributeSet = null;
                  if (ldapEntry != null) {
                     mainAttributeSet = ldapEntry.getAttributeSet();
                  }

                  HashMap userProperties = (HashMap) this
                        .convertAttributeSetToHashMap(mainAttributeSet);

                  String userId = (String) userProperties.get(userAttr);
                  if (userId.length() > 15) {
                     userId = userId.substring(0, 15);
                     userProperties.put(userAttr, userId);
                  }
                  oK = true;
                  // NEV NEV NEV - if the Response is good ... then copy the parameters into userProperties (that start with SSO_*)
                  returnObject.put("responseCode", "Ok");
                  returnObject.put("userProperties", userProperties);
                  break;
               } else {
                  continue;
               }
            } else if (entry instanceof LDAPReferralException) {
               // Ignore referrals
               continue;
            } else if (entry instanceof LDAPException) {
            }
         }

         if (!oK) {
            if (ldapLogging.equalsIgnoreCase("Y")) {
               System.out.println("Search unsuccessful, not found user " + uid);
            }
            returnObject.put("responseCode", "Failed");
            returnObject.put("errorMsg", "You have entered an invalid user id or password!");
         }

      } catch (Exception e) {
         System.out.println("LDAP authentication failed! ");
         System.out.println("LDAP user=" + uid);
         System.out.println("LDAP userAttr=" + userAttr);
         System.out.println("LDAP mailAttr=" + mailAttr);
         System.out.println("LDAP orgId=" + orgId);
         System.out.println("LDAP base=" + dn);
         System.out.println("LDAP authOnly=" + ldapAuthOnly);

         // System.out.println("Attributes userAttr=" + userAttr +
         // ",  mailAttr=" + mailAttr + ", orgId=" + orgId + ", base=" + dn +
         // ", authOnly=" + ldapAuthOnly ) ;
         returnObject.put("errorMsg", "Failed to connect to LDAP server!");
         System.out.println(e.getMessage());
         returnObject.put("responseCode", "Failed");
         e.printStackTrace();
      } finally {
      }
      */
      return returnObject;
   }
}
