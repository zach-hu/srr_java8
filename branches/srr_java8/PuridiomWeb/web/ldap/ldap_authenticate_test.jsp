<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ page import=" com.tsagate.foundation.service.properties.*" %>
<%@ page import="com.tsa.puridiom.common.utility.*"%>
<%@ page import="netscape.ldap.*" %>
<%@ page import="java.util.*" %>

<%

		ArrayList outList = new ArrayList() ;
		LDAPConnection conn = null ;
		String uid = "" ;
		String dn = "" ;
		String userAttr = "" ;
		String orgId = "" ;

       	try {
       	   LDAPProperties ldap = LDAPProperties.getInstance() ;

       	   String host = request.getParameter("ldaphost") ;
   			int port = Integer.parseInt(request.getParameter("ldapport")) ;
   			String connectUser = request.getParameter("ldapconnectuser") ;
   			String connectPw = request.getParameter("ldapconnectpw") ;

   			String loginAttr = request.getParameter("ldaploginattr") ;
   			uid = request.getParameter("authid");
   			String password = request.getParameter("authpw") ;

   			orgId = request.getParameter("organization") ;
   			dn = request.getParameter("ldapsearch") ;
   			String ldapAuthOnly = "N" ;

   			String ldapVersion = ldap.getLDAPVersion();
   			String ldapAutoReferrals = ldap.getLDAPAutoReferrals() ;
   			String ldapHopMax = ldap.getLDAPMaxHops();

   			if (ldapVersion == null) ldapVersion = "2" ;
   			if (ldapAutoReferrals == null) ldapAutoReferrals = "N" ;
   			if (ldapHopMax == null) ldapHopMax = "5" ;

 			outList.add("Connect User:  " + connectUser) ;

       		try
       		{
   	    			outList.add("LDAP Connection: Host: " + host) ;
   	    			outList.add("LDAP Connection: Port: " + port) ;
   	    			outList.add("LDAP Connection: User: " + connectUser) ;
   	    			outList.add("LDAP Connection: Attr: (" + loginAttr + "=" + uid + ")") ;
   	    			outList.add("LDAP Connection: DN: " + dn) ;

				outList.add("Creating Connection Object") ;
   	    		conn = new LDAPConnection();
       			if (ldapAutoReferrals.equalsIgnoreCase("Y")) {
       				conn.setOption(conn.REFERRALS, true) ;
       				int hmax = Integer.parseInt("ldapHopMax") ;
       				conn.setOption(conn.REFERRALS_HOP_LIMIT, hmax) ;
       			}
				outList.add("Connection Object Created") ;

				outList.add("Connecting to " + host + ":" + port + "  Id:" + connectUser  + "/" + connectPw) ;
       			if (connectUser == null || connectUser.length() == 0) {
       				conn.connect(host, port);
       			} else {
       				conn.connect(3,host, port, connectUser, connectPw);
       			}
				outList.add("Connection succeeded!!!") ;
				outList.add("Connection Constraints: " + conn.getConstraints()) ;
				outList.add("Connection Protocol : " + conn.getProperty(conn.LDAP_PROPERTY_PROTOCOL)) ;
				outList.add("Connection SDK : " + conn.getProperty(conn.LDAP_PROPERTY_SDK)) ;
				outList.add("Connection Security : " + conn.getProperty(conn.LDAP_PROPERTY_SECURITY)) ;
       		}
       		catch (LDAPException e)
       		{
       			switch (e.getLDAPResultCode()) {
       			case LDAPException.INVALID_CREDENTIALS:
       				outList.add("Connection failed authenticate do to Invalid credentials!") ; ;
       				break;
       			case LDAPException.CONNECT_ERROR:
       				outList.add( "Authentication failed! Unable to connect to authentication server." );
       				break;
       			default:
       				outList.add("Unable to connect to authentication.server (" + e.getLDAPResultCode() + ")" );
       				break;
       			}
       		}

			outList.add("DN=" + dn) ;
			outList.add("Starting search = " +  "(" + loginAttr + "=" + uid + ")") ;
       		LDAPSearchResults result = null ;
       		if (ldapVersion.equals("3")) {
       			result = conn.search(dn, LDAPv3.SCOPE_SUB, "(" + loginAttr + "=" + uid + ")", null, false);
       		} else {
       			result = conn.search(dn, LDAPv2.SCOPE_SUB, "(" + loginAttr + "=" + uid + ")", null, false);
       		}
			outList.add("Search returned result!") ;

   	        boolean oK = false ;
   	        while (result.hasMoreElements()) {
   				Object entry = result.nextElement();
   				if (entry instanceof LDAPEntry) {
   					LDAPEntry ldapEntry = (LDAPEntry) entry ;
   					dn = ldapEntry.getDN();
   						outList.add("Search successful, found user " + uid) ;
   						outList.add("Authenticate DN: " + dn) ;
   						outList.add("Start Authentication") ;

   					conn.authenticate(dn, password);
					outList.add("Authentication completed") ;
   					if (conn.isAuthenticated())
   					{
   						oK = true ;
   						outList.add("AUTHENTICATION SUCCEEDED!") ;
   						break ;
   					}
   					else
   					{
   						continue  ;
   					}
   				} else if (entry instanceof LDAPReferralException) {
   					LDAPReferralException referrals = (LDAPReferralException) entry ;
   	        		LDAPUrl refUrls[] = referrals.getURLs() ;
   	        		outList.add("Referrals") ;
   	        		for (int ix=0; ix < refUrls.length; ix++) {
   		        			outList.add("Url=" + refUrls[ix].getUrl()) ;
   		        			outList.add("Host=" + refUrls[ix].getHost()) ;
   		        			outList.add("Port=" + refUrls[ix].getPort()) ;
   		        			outList.add("DN=" + refUrls[ix].getDN()) ;
   		        			outList.add("Scope=" + refUrls[ix].getScope()) ;
   		        			outList.add("Filter=" + refUrls[ix].getFilter()) ;
   	        		}
   	        		// Ignore referrals
   	        		continue ;
   				} else if (entry instanceof LDAPException) {
   					LDAPException e = (LDAPException) entry ;
   					outList.add("LDAP authentication failed! " + e.getLDAPResultCode() ) ;
   					outList.add(e.getMessage() ) ;
   		   			e.printStackTrace() ;
   				}
   	        }

   	        if (! oK) {
				outList.add("Search unsuccessful, not found user " + uid) ;
   				outList.add("You have entered an invalid user id or password!");
   	        }

   		}
   		catch(LDAPException e)
   		{
   	        outList.add("LDAP authentication failed! " );
   			outList.add("LDAP user=" + uid ) ;
   			outList.add("LDAP userAttr=" + userAttr);
   			outList.add("LDAP orgId=" + orgId);
   			outList.add("LDAP base=" + dn) ;
   			outList.add(e.getLDAPErrorMessage()) ;
   			e.printStackTrace() ;
   		}
   		finally
   		{
   			// Disconnect

   			if (conn != null)
   			{
   				try
   				{
   					outList.add("Disconnecting") ;
   					conn.disconnect();
   				}
   				catch (Exception e)
   				{
   					outList.add("Exception: " + e.getMessage()) ;
   					e.printStackTrace();
   				}
   				finally
   				{
   				}
   			}

   		}

   		for (int i=0; i < outList.size() ; i++) {
   			out.println(outList.get(i)) ;
   			out.println("<br>") ;
   		}
%>



