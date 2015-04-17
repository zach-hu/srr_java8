package com.tsagate.foundation.security.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.service.properties.LDAPProperties;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.ProxyCall;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import netscape.ldap.*;

/**
 * Creation date: August 2003
 * @author: Kelli Knisely
 */
public class AuthenticateLdapUser extends Task
{
	private String	LDAPSearchbase = LDAPProperties.getInstance().getLDAPSearchbase();
	private String err = "" ;
	private String	uid = null ;
	private	String 	password = null ;
	private String	userdn = null ;
	private String	userAttr = null ;
	private String	mailAttr = null ;
	private String  loginAttr = null ;
	private String	orgId = null ;
	private String	dn = null ;
	private String	ldapAuthOnly = null ;
	private String  ldapVersion = null ;
	private String  ldapReferrals = "N" ;
	private String	ldapHopMax = "5" ;
	private String	ldapLogging = "N" ;
	private String	proxyUrl = null ;
	private LDAPConnection conn = null;
	private boolean disconnectOnComplete = false;
	private LDAPProperties ldap = LDAPProperties.getInstance();
	private Map	columnMap = ldap.getLDAPColumnMap() ;

	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
	   // Big of a hack - if in SSO mode, bypass the regular LDAP stuff and delegate
	   // directory the the AuthenticateSSOUser task. (The idea is that the
	   // authentication has already happened and the creds are available in the parameters)
	   if (ldap.getLDAPMode() != null && ldap.getLDAPMode().equalsIgnoreCase("SSO")) {
	      AuthenticateSSOUser ssoTask = new AuthenticateSSOUser();
	      return ssoTask.executeTask(object);
	   }

		Map incomingRequest = (Map) object;
		Map returnObject = null ;

		uid = (String) incomingRequest.get("mailId");
		password = (String) incomingRequest.get("password");
//		String	dn = (String) incomingRequest.get("ldapDn");

		userAttr = (String) columnMap.get("userId") ;
		mailAttr = (String) columnMap.get("mailId") ;
		loginAttr = ldap.getLDAPLoginAttr() ;
		if (loginAttr == null) loginAttr = userAttr ;

		orgId = ldap.getLDAPOrganization() ;
		dn = ldap.getLDAPSearchbase() ;
		proxyUrl=ldap.getLDAPProxyUrl() ;

		ldapAuthOnly = ldap.getLDAPAuthOnly() ;
//		Log.debug(this, "Attributes user=" + uid + ", userAttr=" + userAttr + ",  mailAttr=" + mailAttr + ", orgId=" + orgId + ", base=" + dn + ", authOnly=" + ldapAuthOnly ) ;

		Log.debug(this,"LDAP user=" + uid ) ;
		Log.debug(this,"LDAP userAttr=" + userAttr);
		Log.debug(this,"LDAP mailAttr=" + mailAttr) ;
		Log.debug(this,"LDAP orgId=" + orgId);
		Log.debug(this,"LDAP base=" + dn) ;
		Log.debug(this,"LDAP authOnly=" + ldapAuthOnly ) ;
		Log.debug(this,"LDAP proxyUrl=" + proxyUrl ) ;

		if (proxyUrl == null || proxyUrl.length() == 0) {
			// perform authentication
			returnObject = (Map) this.authenticate(incomingRequest) ;
		} else {
			// perform authentication via proxy servlet
			returnObject = (Map) this.authenticateProxy(incomingRequest) ;
		}

		return returnObject ;
	}

	private Object authenticate(Map incomingRequest) {

		Map returnObject = new HashMap();
    	try {

			String host = ldap.getLDAPHost() ;
			int port =  ldap.getLDAPPort() ;
			String connectUser = ldap.getLDAPConnectUser() ;
			String connectPw = (String) incomingRequest.get("LDAPConnectPw") ;

			String orgId = ldap.getLDAPOrganization() ;
			String dn = ldap.getLDAPSearchbase() ;
			String ldapAuthOnly = (String) ldap.getLDAPAuthOnly();
//			password = (String) paramMap.get("loginPassword") ;
			// Options
			String ldapVersion = ldap.getLDAPVersion();
			String ldapAutoReferrals = ldap.getLDAPAutoReferrals() ;
			String ldapHopMax = ldap.getLDAPMaxHops();
			String ldapLogging = ldap.getLDAPLogging() ;
			if (ldapVersion == null) ldapVersion = "2" ;
			if (ldapAutoReferrals == null) ldapAutoReferrals = "N" ;
			if (ldapHopMax == null) ldapHopMax = "5" ;
			if (ldapLogging == null) ldapLogging = "N" ;

			Log.debug(this,connectUser) ;
			if (ldapLogging.equalsIgnoreCase("Y")) {
				Log.debug(this,connectPw) ;
			}

    		try
    		{
    			Log.debug(this,"LDAP Connection: Host: " + host) ;
    			Log.debug(this,"LDAP Connection: Port: " + port) ;
    			Log.debug(this,"LDAP Connection: User: " + connectUser) ;
    			Log.debug(this,"LDAP Connection: Attr: (" + loginAttr + "=" + uid + ")") ;
    			Log.debug(this,"LDAP Connection: DN: " + dn) ;

    			conn = new LDAPConnection();
    			
    			if (ldapAutoReferrals.equalsIgnoreCase("Y")) {
    				conn.setOption(conn.REFERRALS, true) ;
    				int hmax = Integer.parseInt("ldapHopMax") ;
    				conn.setOption(conn.REFERRALS_HOP_LIMIT, hmax) ;
    			}

    			System.out.println("LDAP Connect  : " + System.currentTimeMillis());
    			if (connectUser == null || connectUser.length() == 0) {
    				conn.connect(host, port);
    			} else {
    				conn.connect(3,host, port, connectUser, connectPw);
    			}
    			System.out.println("LDAP Connected: " + System.currentTimeMillis());
    		}
    		catch (LDAPException e)
    		{
    			e.printStackTrace();
    			switch (e.getLDAPResultCode()) {
    			case LDAPException.INVALID_CREDENTIALS:
    				err = "Connection failed authenticate do to Invalid credentials!" ;
    				Log.error(this,err) ;
    				break;
    			case LDAPException.CONNECT_ERROR:
    				err = "Authentication failed! Unable to connect to authentication server." ;
    				Log.error(this,err) ;
    				break;
    			default:
    				err = "Unable to connect to authentication.server (" + e.getLDAPResultCode() + ")" ;
    				Log.error(this,err) ;
    				break;
    			}

//    			this.status = Status.FAILED;
    		}

    		LDAPSearchResults result = null ;
    		System.out.println("LDAP Search         : " + System.currentTimeMillis());
    		if (ldapVersion.equals("3")) {
    			result = conn.search(dn, LDAPv3.SCOPE_SUB, "(" + loginAttr + "=" + uid + ")", null, false);
    		} else {
    			result = conn.search(dn, LDAPv2.SCOPE_SUB, "(" + loginAttr + "=" + uid + ")", null, false);
    		}
    		System.out.println("LDAP Search Complete: " + System.currentTimeMillis());

	        boolean oK = false ;
	        while (result.hasMoreElements()) {
				Object entry = result.nextElement();
				if (entry instanceof LDAPEntry) {
					LDAPEntry ldapEntry = (LDAPEntry) entry ;
					dn = ldapEntry.getDN();
					Log.debug(this,"Search successful, found user " + uid) ;
					Log.debug(this,"Authenticate DN: " + dn) ;
					Log.debug(this,"Start Authentication") ;

					LDAPAttributeSet mainAttributeSet = null;
					if (ldapEntry != null) {
						mainAttributeSet = ldapEntry.getAttributeSet();
					}

					HashMap userProperties = (HashMap) this.convertAttributeSetToHashMap(mainAttributeSet);

					String		userId = (String) userProperties.get(userAttr) ;
					if (userId.length() > 15) {
						userId = userId.substring(0,15) ;
						userProperties.put(userAttr, userId) ;
					}

					incomingRequest.put("ldapUser",userId.toUpperCase()) ;
					incomingRequest.put("HostUser_userId",userId.toUpperCase()) ;
					incomingRequest.put("HostUser_mailId",(String) userProperties.get(mailAttr)) ;
					incomingRequest.put("mailId",(String) userProperties.get(mailAttr)) ;
					incomingRequest.put("HostUser_organizationId", orgId.toUpperCase()) ;

					boolean authenticated = false ;
					if (password != null && password.length() == 0) {
						// Single Signon
						authenticated = true ;
					} else {
						try {
						conn.authenticate(dn, password);
						authenticated = conn.isAuthenticated() ;
						}
						catch (LDAPException e){
							authenticated = false;
			    			err = "Connection failed authenticate do to Invalid credentials! " + e ;
			    			Log.debug(this,err) ;
			    			}
					}

					if (authenticated)
					{
						Log.debug(this,"Authentication succeeded!") ;

						oK = true ;

						incomingRequest.put("ldapAuthenticated","true") ;

						returnObject.put("responseCode","Ok") ;
						returnObject.put("userProperties", userProperties);
						break ;
					}
					else
					{
						Log.debug(this,"Authentication failed!") ;

						oK = true;

						incomingRequest.put("ldapAuthenticated","false") ;

						returnObject.put("responseCode","Ok") ;
						returnObject.put("userProperties", userProperties);
						
						continue  ;
					}
				} else if (entry instanceof LDAPReferralException) {
					LDAPReferralException referrals = (LDAPReferralException) entry ;
	        		LDAPUrl refUrls[] = referrals.getURLs() ;
	        		Log.debug(this,"Referrals") ;
	        		for (int ix=0; ix < refUrls.length; ix++) {
	        			Log.debug(this,"Url=" + refUrls[ix].getUrl()) ;
	        			Log.debug(this,"Host=" + refUrls[ix].getHost()) ;
	        			Log.debug(this,"Port=" + refUrls[ix].getPort()) ;
	        			Log.debug(this,"DN=" + refUrls[ix].getDN()) ;
	        			Log.debug(this,"Scope=" + refUrls[ix].getScope()) ;
	        			Log.debug(this,"Filter=" + refUrls[ix].getFilter()) ;
	        		}
	        		// Ignore referrals
	        		continue ;
				} else if (entry instanceof LDAPException) {
					LDAPException e = (LDAPException) entry ;
	                Log.debug(this,"LDAP authentication failed! " + e.getLDAPResultCode() );
	    			Log.debug(this,"Attributes userAttr=" + userAttr + ",  mailAttr=" + mailAttr + ", orgId=" + orgId + ", base=" + dn + ", authOnly=" + ldapAuthOnly + ", " + password ) ;
	    			Log.debug(this,"LDAP authentication failed! " + e.getLDAPResultCode()) ;
	    			Log.debug(this,"Attributes userAttr=" + userAttr + ",  mailAttr=" + mailAttr + ", orgId=" + orgId + ", base=" + dn + ", authOnly=" + ldapAuthOnly + ", " + password ) ;
	                e.printStackTrace() ;
				}
	        }

	        if (! oK) {
				Log.debug(this,"Search unsuccessful, not found user " + uid) ;
                returnObject.put("responseCode","Failed") ;
			}

		}
		catch(Exception e)
		{
	        Log.debug(this,"LDAP authentication failed! " );
			Log.debug(this,"LDAP user=" + uid ) ;
			Log.debug(this,"LDAP userAttr=" + userAttr);
			Log.debug(this,"LDAP mailAttr=" + mailAttr) ;
			Log.debug(this,"LDAP orgId=" + orgId);
			Log.debug(this,"LDAP base=" + dn) ;
			Log.debug(this,"LDAP authOnly=" + ldapAuthOnly ) ;

	//        Log.debug(this,"Attributes userAttr=" + userAttr + ",  mailAttr=" + mailAttr + ", orgId=" + orgId + ", base=" + dn + ", authOnly=" + ldapAuthOnly ) ;
            returnObject.put("errorMsg", "Failed to connect to LDAP server!") ;
            returnObject.put("responseCode","Failed") ;
			e.printStackTrace() ;
		}
		finally
		{
			// Disconnect

			if (conn != null)
			{
				try
				{
					conn.disconnect();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
				}
			}

		}
		return returnObject ;
	}


	private Object authenticateProxy(Map incomingRequest) {
		Map returnObject = null ;

		uid = (String) incomingRequest.get("mailId");
		password = (String) incomingRequest.get("password");

		Map ldapProperties = (Map) ldap.getLDAPPropertyMap() ;
		String proxyAddress = (String) ldap.getLDAPProxyUrl() ;

		ldapProperties.put("loginUser",uid) ;
		ldapProperties.put("loginPassword", password) ;
		String	connectPw = (String) incomingRequest.get("LDAPConnectPw") ;

		ProxyCall proxy = new ProxyCall() ;
		Map parameters = new HashMap() ;
		parameters.put("paramObject",ldapProperties) ;
		parameters.put("connectPw", connectPw) ;

		try {
			returnObject = (Map) proxy.proxyCall(proxyAddress, parameters) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnObject = new HashMap() ;
			returnObject.put("responseCode", "Failed") ;
			returnObject.put("errorMsg", "Servlet exception calling proxy, please contact you system administrator!") ;
		}

		String status = (String) returnObject.get("responseCode") ;
		if (status.equalsIgnoreCase("OK")) {

			HashMap userProperties = (HashMap) returnObject.get("userProperties") ;
			String		userId = (String) userProperties.get(userAttr) ;
			if (userId.length() > 15) {
				userId = userId.substring(0,15) ;
				userProperties.put(userAttr, userId) ;
			}

			String mailId = (String) userProperties.get(mailAttr) ;
			if (mailId == null || mailId.trim().length() < 1) {
				returnObject = new HashMap() ;
				returnObject.put("responseCode", "Failed") ;
				returnObject.put("errorMsg", "Login user must have a valid email address to log into Puridiom!") ;
			} else {
				incomingRequest.put("ldapUser",userId.toUpperCase()) ;
				incomingRequest.put("ldapAuthenticated","true") ;
				incomingRequest.put("HostUser_userId",userId.toUpperCase()) ;
				incomingRequest.put("HostUser_mailId",(String) userProperties.get(mailAttr)) ;
				incomingRequest.put("mailId",(String) userProperties.get(mailAttr)) ;
				incomingRequest.put("HostUser_organizationId", orgId.toUpperCase()) ;
			}
		}


		return returnObject ;
	}

    private Map convertAttributeSetToHashMap(LDAPAttributeSet attributeSet) {

		HashMap attributeMap = new HashMap();

		try
		{
			if (attributeSet == null)
			{
				throw new Exception("attributeSet was not defined.");
			}

			Enumeration enumeration = attributeSet.getAttributes();
			LDAPAttribute	attribute;
			String	attributeName = "";
			Object	attributeValue;

			while (enumeration.hasMoreElements())
			{
				attribute = (LDAPAttribute) enumeration.nextElement();

				if (attribute != null)
				{
					attributeName = attribute.getName();

					String[] values = attribute.getStringValueArray();
					if (values.length > 1)
					{
						List list = new ArrayList();
						for (int i=0; i < values.length; i++)
						{
							list.add(values[i]);
						}

						attributeValue = list;

						attributeMap.put(attributeName, attributeValue);
					}
					else
					{
						attributeValue = values[0];
						attributeMap.put(attributeName, attributeValue);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}

		return attributeMap;
    }

}
