<%@ page language="java" isErrorPage="true" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%
	Encoder encoder = DefaultEncoder.getInstance();
	StringBuffer s_errorMsg = new StringBuffer();
	String	s_errorTitle	= "Exception Page";
	String	s_errorCode	= (String) request.getAttribute("err");
	String	s_errorstr	= (String) request.getAttribute("errorMsg");
	String	xssValue	= (String) request.getAttribute("XSS");
	String 	xssMessage = "";
	if (s_errorstr == null) {
		s_errorstr = "";
	}
	if ( request.getParameter("errorMsg") != null ) {
		s_errorMsg.append("<BR>" + request.getParameter("errorMsg"));
	}
	if ( request.getParameter("err") != null )
	{
		s_errorCode = encoder.encodeForHTML(request.getParameter("err"));
	}
	
	if (xssValue != null){
		xssMessage = "We found the following malicious code in your request: " + xssValue + ". ";
	}

	if (HiltonUtility.isEmpty(s_errorCode)) {
		s_errorCode = HiltonUtility.ckNull((String) request.getAttribute("errorCode"));
	}

	if ( request.getParameter("uri") != null )
	{
		s_errorTitle = "Compile Error" ;
		s_errorMsg.append("<BR>A compiler error occurred.<BR>") ;
	}
	else if (s_errorCode.equals("systemUnavailable"))
	{
		s_errorTitle = "System Unavailable";
	}
	else if ( s_errorCode.equals("invalidSession") )
	{
		out.println("<!-- errorCode = " + s_errorCode + "! -->");
		s_errorTitle = "Session Expired";
		s_errorMsg.append("<BR>Your session has expired.  Please login again.<BR>");
		response.addHeader("refresh", "5; URL=" + contextPath + "/index.jsp");
	}
	else if ( s_errorCode.equals("notAuthorized") )
	{
		out.println("<!-- errorCode = " + s_errorCode + "! -->");
		s_errorTitle = "Not Authorized";
		s_errorMsg.append("<BR>You are no longer logged in or you are not authorized to view this page.<BR>");
		response.addHeader("refresh", "5; URL=" + contextPath + "/index.jsp");
		
	} else if (s_errorstr.length() > 0) {
		s_errorMsg.append(s_errorstr);
		exception = (Exception) request.getAttribute("exception");
		
	} else {
		exception = (Exception) request.getAttribute("exception");
		if (exception == null) {
			s_errorMsg.append( s_errorCode );
			s_errorMsg.append( "<BR> An unknown error occurred." );
		}
	}
	
	if ( (s_errorCode.indexOf("invalidSession") < 0) && !s_errorCode.equals("systemUnavailable") && !s_errorCode.equals("notAuthorized")) {
		s_errorMsg.append( "<BR><BR>Please contact your system administrator." );
	}
	
	if (oid == null || oid.equals("")) {
		Map urlproperty = DictionaryManager.getInstance("oidurl", "PURIDIOM").getPropertyMap();
		Iterator it = urlproperty.keySet().iterator() ;
		String coid = "PURIDIOM";
		while (it.hasNext()) {
			String ikey = (String) it.next();
			String itxt = (String) urlproperty.get(ikey);
			if (requestURLPath.indexOf(itxt)>0){
				coid = ikey;
			}
		}
		
		oid = coid;
	}
	
	String showDetailExcepcion = PropertiesManager.getInstance(oid).getProperty("MISC", "SHOW DETAIL EXCEPTION", "N");
	
%>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
	<tr><td align=center height=50px class=error><%=s_errorTitle%></td></tr>

	<tr><td align=center><b><%=xssMessage != null ? headerEncoder.encodeForHTML(xssMessage.toString()) : ""%>Your information has been compromised. </b><br></td></tr>

<%	if ( s_errorCode.equals("invalidSession") || s_errorCode.equals("notAuthorized")) {%>
	<tr><td align=center>Click on this button if your browser does not support redirects.</td></tr>
	<tr><td align=center><br></td></tr>
	<tr><td align=center><div id="pxbutton"><a href="<%=contextPath%>/index.jsp">Login</a></div></td></tr>
<%	}%>
</table>
<br>
<table border="0" cellspacing="0" cellpadding="0" width="680px">
	<tr>
	  <td align="center">For further assistance , please contact us at  <a href="mailto:<%=PropertiesManager.getInstance(oid).getProperty("MAILEVENTS", "AdminEmailAddr","support@puridiom.com")%>?&body=error">Puridiom Help Desk.</a></td>
	  <td>&nbsp;</td>
	</tr>
	<tr>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	</tr>
	<tr>
	  <td align="center">To help us resolve this issue faster, Please include a description of the <b>action</b> you were trying to perform.</td>
	  <td>&nbsp;</td>
	</tr>
	<tr>
	  <td colspan="2">&nbsp;</td>
	</tr>
	<tr>
	  <td colspan="2">&nbsp;</td>
	</tr>
	<%if (exception != null && showDetailExcepcion.equals("Y")) { %>
	<tr id="btnShowDetails">
		<td align="center" colspan="2">
			<div id="pxbutton"><a href="javascript:showDetails()">Show Details</a></div>&nbsp;&nbsp;
		</td>
	</tr>
	<tr id="btnHideDetails" style="display:none">
		<td align="center" colspan="2">
			<div id="pxbutton"><a href="javascript:hideDetails()">Hide Details</a></div>&nbsp;&nbsp;
		</td>
	</tr>
	<% } %>
</table>

<%
if (exception != null && showDetailExcepcion.equals("Y")) { 
	String exceptionMessage = ""; %>
<table id="tableExceptionDetail" border="0" cellpadding="0" cellspacing="0" width="680px" style="display:none">
	<tr><td align="center"><br><b><%=exception%></b></td></tr>
<%		StackTraceElement exceptionStack[] = exception.getStackTrace();
		if (exceptionStack != null) {
			for (int i = 0; i < exceptionStack.length; i++) {
				exceptionMessage = exceptionStack[i].toString();%>
				<tr><td align=center><%=exceptionStack[i].toString()%></td></tr>	
<%			}  
		}	
		Throwable causeException = exception.getCause();
		if (causeException != null) { %>
			<tr><td align="center"><br><b>Cause stack trace</b></td></tr>
			<tr><td align="center"><br><b><%=causeException%></b></td></tr>
<%		
			StackTraceElement causeStack[] = causeException.getStackTrace();
			if (causeStack != null) {
				for (int i = 0; i < causeStack.length; i++) {
					exceptionMessage = causeStack[i].toString();%>
					<tr><td align=center><%=causeStack[i].toString()%></td></tr>	
<%				}  
			}	
		}%>	
	<tr><td align="center"><br/></td></tr>
	<tr><td align="center">&nbsp;</td></tr>
</table>
<%}%>


<%@ include file="/system/footer.jsp" %>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	var error_code = "<%=encoder.encodeForJavaScript(encoder.canonicalize(s_errorCode))%>";

	if ( (error_code == "invalidSession" || error_code == "notAuthorized") && (opener != null) ) {
		opener.location.href = "<%=contextPath%>/system/error.jsp";
		self.close();
	}

	function checkHiddenMenu()
	{
		hideArea("navTable");
	}

	function showDetails() {
		displayArea("tableExceptionDetail");
		displayArea("btnHideDetails");
		hideArea("btnShowDetails");
   	}
    
    function hideDetails() {
    	hideArea("btnHideDetails");
		hideArea("tableExceptionDetail");
		displayArea("btnShowDetails");
   	}
// end hiding contents -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>