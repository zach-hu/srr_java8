<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%@ page import="com.tsa.puridiom.entity.Organization"%>
<%@ page import="com.tsa.puridiom.organization.OrganizationManager"%>
<%@ page import="com.tsagate.foundation.utility.Utility"%>
<%@ page import="com.tsagate.foundation.security.tasks.PortalUserInfo"%>

<%@ page import="javax.xml.parsers.*"%>
<%@ page import="org.xml.sax.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.w3c.dom.*"%>

<%
HttpSession sessionId = request.getSession() ;

Enumeration en = request.getParameterNames() ;
while (en.hasMoreElements())
{
	String paramName = (String) en.nextElement();
	System.out.println(paramName + "=" + request.getParameter(paramName) );
};
en = request.getAttributeNames() ;
while (en.hasMoreElements())
{
	String paramName = (String) en.nextElement();
	System.out.println(paramName + "=" + request.getAttribute(paramName)) ;
};
en = request.getHeaderNames() ;
while (en.hasMoreElements())
{
	String paramName = (String) en.nextElement();
	System.out.println(paramName + "=" + request.getHeader(paramName));
};
String sTicket = request.getParameter("SessionID");
String sApplication = request.getParameter("Application");
String sTargetUrl = request.getParameter("TargetURL");
String sReturnUrl = request.getParameter("ReturnURL");
String sTimeout = request.getParameter("TimeOut");
String sTimeoutUrl = request.getParameter("TimeOutURL");

String loginID = request.getParameter("uid") ;
String email = request.getParameter("mail") ;
String hashValue = request.getParameter("HashValue") ;
String expireDateTime = request.getParameter("ExpireDateTime") ;
String firstName = request.getParameter("givenName") ;
String lastName = request.getParameter("sn") ;
String title = request.getParameter("title") ;
oid = request.getParameter("oid") ;
String gpiCosting = request.getParameter("gpiCosting") ;
String location = request.getParameter("ln");


System.out.println("Ticket=" + sTicket) ;
System.out.println("Application=" + sApplication) ;
System.out.println("Target=" + sTargetUrl);

System.out.println("ReturnUrl=" + sReturnUrl) ;
System.out.println("Timeout=" + sTimeout) ;
System.out.println("TimeUrl=" + sTimeoutUrl);

if (sTicket == null) sTicket = "" ;
if (sApplication == null) sApplication = "" ;
if (sTargetUrl == null) sTargetUrl = "" ;
if (sReturnUrl == null) sReturnUrl = "" ;
if (sTimeout == null) sTimeout = "" ;
if (sTimeoutUrl == null) sTimeoutUrl = "" ;

if (sTicket.length() > 0) {
	if (sApplication.equalsIgnoreCase("Puridiom")) {
	   Cookie c = new Cookie("PuridiomCp","Y") ;
	   response.addCookie(c) ;
	}
	Cookie c = new Cookie("PTimeout",sTimeoutUrl) ;
	response.addCookie(c) ;


	try {
	    int timeout = Integer.parseInt(sTimeout) ;
	    sessionId.setMaxInactiveInterval(timeout * 60) ;
	}
	catch (NumberFormatException e) {
	    // Exception occurred - Use Server Default
	}
}

PortalUserInfo userInfo = new PortalUserInfo() ;
if (loginID.length() > 0) {
	if (sReturnUrl == null) sReturnUrl = request.getHeader("referer") ;
	userInfo.setLoginId(loginID) ;
	userInfo.setOrganization(oid) ;
	userInfo.setFirstName(firstName) ;
	userInfo.setLastName(lastName) ;
	userInfo.setLocation(location) ;
	userInfo.setDivisionCode(gpiCosting) ;
	userInfo.setEmail(email) ;
	userInfo.setTitle(title) ;
	userInfo.setStatusCode("0") ;
	userInfo.setStatusDesc("Successful") ;
	userInfo.setHashValue(hashValue) ;
	userInfo.setExpireDateTime(expireDateTime) ;
	userInfo.processPortalUserInfo() ;
} else {
	userInfo.processTicket(sTargetUrl,sTicket,"Puridiom",sApplication) ;
}

String status = userInfo.getStatusCode() ;
if (! status.trim().equals("0") && ! status.trim().equals("2")) {
    String errorUrl = userInfo.getErrorUrl();
    if (errorUrl == null) errorUrl = contextPath + "/portal-error.jsp" ;
    %>
	<tsa:hidden name="errorMsg" value="<%=userInfo.getStatusDesc()%>"/>
	<%
} else {
	status = "0" ;
    uid = userInfo.getLoginId().toUpperCase();
    oid = userInfo.getOrganization() ;
    mid = userInfo.getEmail() ;
	Map userInfoMap = userInfo.getUserInfoMap() ;

	Set keys = userInfoMap.keySet();         // The set of keys in the map.
    Iterator keyIter = keys.iterator();
    while (keyIter.hasNext()) {
       String key = (String) keyIter.next();  // Get the next key.
       String value = (String) userInfoMap.get(key);  // Get the value for that key.
       if (key.compareTo("UserProfile_userId") != 0 && key.compareTo("UserProfile_mailId") !=0) {
	       out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"" + key + "\" value=\"" + value + "\">") ;
       }
    }
}

%>

	<tsa:hidden name="SessionID" value="<%=sid%>"/>
    <tsa:hidden name="AppName" value="Puridiom"/>
    <tsa:hidden name="portalLogin" value="Y"/>
	<tsa:hidden name="ldapAuthenticated" value="true"/>
	<tsa:hidden name="ldapUserUpdate" value="Y"/>
	<tsa:hidden name="ldapUser" value="<%=uid%>"/>
	<tsa:hidden name="HostUser_userId" value="<%=uid%>"/>
	<tsa:hidden name="HostUser_mailId" value="<%=mid%>"/>
	<tsa:hidden name="HostUser_organizationId" value="<%=oid%>"/>
	<tsa:hidden name="UserProfile_userId" value="<%=uid%>"/>
	<tsa:hidden name="UserProfile_mailId" value="<%=mid%>"/>
	<tsa:hidden name="UserProfile_organizationId" value="<%=oid%>"/>
	<tsa:hidden name="logOffUrl" value="<%=sReturnUrl%>"/>

	</form>
</body>
</html>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

frm = document.purchaseform;
frm.organizationId.value = "<%=oid%>" ;
frm.userId.value = "<%=uid%>" ;
frm.mailId.value = "<%=mid%>" ;

<% if (status.equals("0")) { %>
	doSubmit('menu/main_menu.jsp', 'UserLogin');
<% } else { %>
	doSubmit('portal-error.jsp', 'DoNothing');
<% } %>

// end hiding contents -->
</SCRIPT>

