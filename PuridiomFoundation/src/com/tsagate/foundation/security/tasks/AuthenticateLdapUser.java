package com.tsagate.foundation.security.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.service.properties.LDAPProperties;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.ProxyCall;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;

import org.apache.commons.lang.*;

import netscape.ldap.*;

/**
 * Creation date: August 2003
 * @author: Kelli Knisely
 */
public class AuthenticateLdapUser extends Task
{
    private String    LDAPSearchbase = LDAPProperties.getInstance().getLDAPSearchbase();
    private String err = "" ;
    private String    uid = null ;
    private    String     password = null ;
    private String    userdn = null ;
    private String    userAttr = null ;
    private String    mailAttr = null ;
    private String  loginAttr = null ;
    private String    orgId = null ;
    private String    dn = null ;
    private String    ldapAuthOnly = null ;
    private String  ldapVersion = null ;
    private String  ldapReferrals = "N" ;
    private String    ldapHopMax = "5" ;
    private String    ldapLogging = "N" ;
    private String    proxyUrl = null ;

    private String ldapProtocal = "" ;
    private String ldapAuthentication="" ;
    private String ldapKeystore="" ;
    private String ldapUrl = "" ;

    private LDAPConnection conn = null;
    private boolean disconnectOnComplete = false;
    private LDAPProperties ldap = LDAPProperties.getInstance();
    private Map    columnMap = ldap.getLDAPColumnMap() ;

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
//        String    dn = (String) incomingRequest.get("ldapDn");

        userAttr = (String) columnMap.get("userId") ;
        mailAttr = (String) columnMap.get("mailId") ;
        loginAttr = ldap.getLDAPLoginAttr() ;
        if (loginAttr == null) loginAttr = userAttr ;

        orgId = ldap.getLDAPOrganization() ;
        dn = ldap.getLDAPSearchbase() ;
        proxyUrl=ldap.getLDAPProxyUrl() ;

        ldapUrl = ldap.getLDAPUrl() ;
        ldapProtocal = ldap.getLDAPProtocal() ;
        ldapAuthentication = ldap.getLDAPAuthentication() ;
        ldapKeystore = ldap.getLDAPKeystore() ;

        ldapAuthOnly = ldap.getLDAPAuthOnly() ;
//        Log.debug(this, "Attributes user=" + uid + ", userAttr=" + userAttr + ",  mailAttr=" + mailAttr + ", orgId=" + orgId + ", base=" + dn + ", authOnly=" + ldapAuthOnly ) ;

        Log.debug(this,"LDAP user=" + uid ) ;
        Log.debug(this,"LDAP userAttr=" + userAttr);
        Log.debug(this,"LDAP mailAttr=" + mailAttr) ;
        Log.debug(this,"LDAP orgId=" + orgId);
        Log.debug(this,"LDAP base=" + dn) ;
        Log.debug(this,"LDAP authOnly=" + ldapAuthOnly ) ;
        Log.debug(this,"LDAP proxyUrl=" + proxyUrl ) ;
        Log.debug(this,"LDAP url=" + ldapUrl ) ;
        Log.debug(this,"LDAP protocal=" + ldapProtocal ) ;
        Log.debug(this,"LDAP ldapKeystore=" + ldapKeystore ) ;
        Log.debug(this,"LDAP authentication=" + ldapAuthentication) ;


        if (ldapProtocal != null && ldapProtocal.trim().length() > 0) {
            // perform ssl authentication
            returnObject = (Map) ldapAuth(incomingRequest) ;
        } else if (proxyUrl == null || proxyUrl.length() == 0) {
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
//            password = (String) paramMap.get("loginPassword") ;
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

                if (connectUser == null || connectUser.length() == 0) {
                    conn.connect(host, port);
                } else {
                    conn.connect(3,host, port, connectUser, connectPw);
                }
            }
            catch (LDAPException e)
            {
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

//                this.status = Status.FAILED;
            }

            LDAPSearchResults result = null ;
            if (ldapVersion.equals("3")) {
                result = conn.search(dn, LDAPv3.SCOPE_SUB, "(" + loginAttr + "=" + uid + ")", null, false);
            } else {
                result = conn.search(dn, LDAPv2.SCOPE_SUB, "(" + loginAttr + "=" + uid + ")", null, false);
            }

            boolean oK = false ;
            while (result.hasMoreElements()) {
                Object entry = result.nextElement();
                if (entry instanceof LDAPEntry) {
                    LDAPEntry ldapEntry = (LDAPEntry) entry ;
                    dn = ldapEntry.getDN();
                    Log.debug(this,"Search successful, found user " + uid) ;
                    Log.debug(this,"Authenticate DN: " + dn) ;
                    Log.debug(this,"Start Authentication") ;

                    boolean authenticated = false ;
                    if (password != null && password.length() == 0 && (ldap.getLDAPMode().equalsIgnoreCase("sso"))) {
                        // Single Signon
                        authenticated = false ; // force to false for SRR
                    } else {
                        conn.authenticate(dn, password);
                        authenticated = conn.isAuthenticated() ;
                    }

                    if (authenticated)
                    {
                        Log.debug(this,"Authentication succeeded!") ;
                        LDAPAttributeSet mainAttributeSet = null;
                        if (ldapEntry != null) {
                            mainAttributeSet = ldapEntry.getAttributeSet();
                        }

                        HashMap userProperties = (HashMap) this.convertAttributeSetToHashMap(mainAttributeSet);

                        String        userId = (String) userProperties.get(userAttr) ;
                        if (userId.length() > 15) {
                            userId = userId.substring(0,15) ;
                            userProperties.put(userAttr, userId) ;
                        }
                        oK = true ;

                        incomingRequest.put("ldapUser",userId.toUpperCase()) ;
                        incomingRequest.put("ldapAuthenticated","true") ;
                        incomingRequest.put("HostUser_userId",userId.toUpperCase()) ;
                        incomingRequest.put("HostUser_mailId",(String) userProperties.get(mailAttr)) ;
                        incomingRequest.put("mailId",(String) userProperties.get(mailAttr)) ;
                        incomingRequest.put("HostUser_organizationId", orgId.toUpperCase()) ;

                        returnObject.put("responseCode","Ok") ;
                        returnObject.put("userProperties", userProperties);
                        break ;
                    }
                    else
                    {
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
                returnObject.put("errorMsg", "You have entered an invalid user id or password!");
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
        String    connectPw = (String) incomingRequest.get("LDAPConnectPw") ;

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
            String        userId = (String) userProperties.get(userAttr) ;
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

    private Object ldapAuth(Map incomingRequest) {

        Hashtable ctxTab = new Hashtable();
        // Create return Map
        Map returnObject = new HashMap();

        // Success Flag
        boolean oK = false ;

        // Keystore file where cert was installed
        System.setProperty("javax.net.ssl.trustStore",ldap.getLDAPKeystore());

        ctxTab.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");

        //set security credentials (default) simple
        ctxTab.put(Context.SECURITY_AUTHENTICATION,ldap.getLDAPAuthentication());
        ctxTab.put(Context.SECURITY_PRINCIPAL,ldap.getLDAPConnectUser());
        ctxTab.put(Context.SECURITY_CREDENTIALS,ldap.getLDAPConnectPW());

        //specify use of ssl
        ctxTab.put(Context.SECURITY_PROTOCOL,ldap.getLDAPProtocal());

        //connect to my domain controller
        ctxTab.put(Context.PROVIDER_URL,ldap.getLDAPUrl());
        try {

             // Create the initial directory context
            System.out.println(ctxTab) ;
             DirContext ctx = new InitialLdapContext(ctxTab,null);


             //Create the search controls
             SearchControls searchCtls = new SearchControls();

             //Specify the attributes to return
             String returnedAtts[]={"sn","givenName","mail"};
    //         searchCtls.setReturningAttributes(returnedAtts);

             // Search scope
             searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

             // LDAP search filter
             String searchFilter = ldap.getLDAPFilter() ;
             searchFilter = org.apache.commons.lang.StringUtils.replace(searchFilter, "$uid", uid) ;

             // SearchBase
             // Test - Base directory
             //        OU=Users,OU=MyBusiness,DC=TechnicalServicesAssociates,DC=local
             String searchBase = ldap.getLDAPSearchbase() ;

             // Search based of base dn and filter
             NamingEnumeration searchResult = ctx.search(searchBase, searchFilter, searchCtls);

             //Test for results
             if(searchResult.hasMoreElements()) {
                 SearchResult result = (SearchResult)searchResult.next();

                  // Result Attributes
                  Attributes attrs = result.getAttributes();
                  if (attrs != null) {
                    Hashtable ctxAuth = new Hashtable();

                    // specify use of ssl
                    ctxAuth.put(Context.SECURITY_PROTOCOL, ldap.getLDAPProtocal());

                    // connect to my domain controller
                    ctxAuth.put(Context.PROVIDER_URL, ldap.getLDAPUrl());

                    ctxAuth.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
                    Attribute dnAttr = attrs.get("distinguishedName");
                    String userDn = (String) dnAttr.get();

                    ctxAuth.put(Context.SECURITY_PRINCIPAL, userDn);
                    ctxAuth.put(Context.SECURITY_CREDENTIALS, password);

                    DirContext authContext = new InitialLdapContext(ctxAuth, null);

                    // If no exception, authentication was successful
                    try {
                        // Populate values from result
                        HashMap userProperties = new HashMap();
                        NamingEnumeration fields = attrs.getIDs();
                        while (fields.hasMoreElements()) {
                            String key = (String) fields.next();
                            userProperties.put(key, attrs.get(key).get());
                        }

                        // Truncate username if over 15 characters
                        String userId = (String) userProperties.get(userAttr);
                        if (userId.length() > 15) {
                            userId = userId.substring(0, 15);
                            userProperties.put(userAttr, userId);
                        }

                        // Setup return data values
                        incomingRequest.put("ldapUser", userId.toUpperCase());
                        incomingRequest.put("ldapAuthenticated", "true");
                        incomingRequest.put("HostUser_userId", userId.toUpperCase());
                        incomingRequest.put("HostUser_mailId",(String) userProperties.get(mailAttr));
                        incomingRequest.put("mailId", (String) userProperties.get(mailAttr));
                        incomingRequest.put("HostUser_organizationId", orgId.toUpperCase());

                        returnObject.put("responseCode", "Ok");
                        returnObject.put("userProperties", userProperties);

                        oK = true;
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                  }
             }

             ctx.close();

        }
        catch (NamingException e) {
            e.printStackTrace() ;
        }

        if (! oK) {
            Log.debug(this,"Search unsuccessful, not found user " + uid) ;
            returnObject.put("responseCode","Failed") ;
            returnObject.put("errorMsg", "You have entered an invalid user id or password!");
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
            LDAPAttribute    attribute;
            String    attributeName = "";
            Object    attributeValue;

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