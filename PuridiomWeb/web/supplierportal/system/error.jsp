<%@ page language="java" isErrorPage="true" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%	
	StringBuffer s_errorMsg = new StringBuffer();
	String	s_errorTitle	= "Exception Page";
	String	s_errorCode	= HiltonUtility.ckNull((String) request.getAttribute("errorCode"));
	String	s_errorstr	=  HiltonUtility.ckNull((String) request.getAttribute("errorMsg"));

	if ( request.getParameter("errorMsg") != null )
	{
		s_errorMsg.append("<BR>" + request.getParameter("errorMsg"));
	}
	if ( HiltonUtility.isEmpty(s_errorCode) &&  !HiltonUtility.isEmpty(request.getParameter("err")) )
	{
		s_errorCode = request.getParameter("err");
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
	}
	else if ( (exception == null) && (s_errorstr.length() <=0) )
	{
		exception = (Exception) request.getAttribute("exception");
		if (exception == null) {
			s_errorMsg.append( s_errorCode );
			s_errorMsg.append( "<BR> An unknown error occurred." );
		}
	}
	else {
		s_errorMsg.append( s_errorstr );
	}

	if ( (s_errorCode.indexOf("invalidSession") < 0) && !s_errorCode.equals("systemUnavailable") && !s_errorCode.equals("notAuthorized")) {
		s_errorMsg.append( "<BR><BR>Please contact your system administrator." );
	}
%>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
	<tr><td align=center height=50px class=error><%=s_errorTitle%></td></tr>

<%	if (exception != null) {%>
	<tr><td align=center><br><b><%=exception%></b></td></tr>
<%		StackTraceElement	exceptionStack[] = exception.getStackTrace();
			if (exceptionStack != null) {
				try {%>
	<tr><td align=center><%=exceptionStack[0].toString()%></td></tr>
<%			} catch (Exception e) {
				}
				try {%>
	<tr><td align=center><%=exceptionStack[1].toString()%></td></tr>
<%			} catch (Exception e) {
				}
			}%>
	<tr><td align=center><br><br></td></tr>
<%	}%>

	<tr><td align=center><%=s_errorMsg%><br></td></tr>

<%	if ( s_errorCode.equals("invalidSession") || s_errorCode.equals("notAuthorized")) {%>
	<tr><td align=center>Click on this button if your browser does not support redirects.</td></tr>
	<tr><td align=center><br></td></tr>
	<tr><td align=center><a href="<%=contextPath%>/supplierportal/index.jsp"><img class=button src="<%=contextPath%>/supplierportal/images/button_login.gif" border="0"></a></td></tr>
<%	}%>
</table>

<%@ include file="/supplierportal/system/footer.jsp" %>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	var error_code = "<%=s_errorCode%>";

	if ( (error_code == "invalidSession") && (opener != null) ) {
		opener.location.href = "<%=contextPath%>/supplierportal/system/error.jsp";
		self.close();
	}

// end hiding contents -->
</SCRIPT>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
