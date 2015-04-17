<%@ page language="java" isErrorPage="true" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header_popup.jsp" %>
<%	
	StringBuffer s_errorMsg = new StringBuffer();
	String	s_errorTitle	= "Exception Page";
	String	s_errorCode	= "";
	String	s_errorstr	= (String) request.getAttribute("errorMsg");

	if ( !HiltonUtility.isEmpty(s_errorstr) ) 
	{
		s_errorMsg.append("<BR>" + s_errorstr);
	}
	if ( request.getAttribute("err") != null )
	{
		s_errorCode = (String) request.getAttribute("err");
	}
	if ( request.getAttribute("uri") != null )
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
	else if ( (exception == null) && HiltonUtility.isEmpty(s_errorstr) )
	{
		s_errorMsg.append( s_errorCode );
		s_errorMsg.append( "<BR> An unknown error occurred." );
	}
%>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
	<tr><td align=center height=50px class=heading><%=s_errorTitle%></td></tr>
	<tr><td><br></td></tr>
<%	if (exception != null) {%>
	<tr><td align=center class=error>An exception error has occurred: <br><%=exception%>.<br></td></tr>
<%	}%>
	<tr><td align=center class=error><%=s_errorMsg%><br></td></tr>
<%	if ( (s_errorCode.indexOf("invalidSession") < 0) && !s_errorCode.equals("systemUnavailable")) {%>
	<tr><td><br></td></tr>
	<tr><td align=center>Please contact your system administrator.</td></tr>
<%	}
		if ( s_errorCode.equals("invalidSession") ) {%>
	<tr><td align=center>Click on this button if your browser does not support redirects.<br></td></tr>
	<tr><td align=center><a href="<%=contextPath%>/supplierportal/index.jsp"><img class=button src="<%=contextPath%>/supplierportal/images/button_login.gif" border=0 alt=Login></a></td></tr>
<%	} else {%>
	<tr><td><br></td></tr>
	<tr><td><br></td></tr>
	<tr><td align=center><a href="javascript: window.close(); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_close.gif" border=0 alt=Close></a></td></tr>
<%	}%>
</table>

<br>

<%@ include file="/supplierportal/system/footer.jsp" %>
</form>
</body>
</html>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	
	if ( document.all || document.layers )
	{
	    window.resizeTo(720, 300);
	}

	var error_code = "<%=s_errorCode%>";

	if ( (error_code == "invalidSession") && (opener != null) ) {
		opener.location.href = "<%=contextPath%>/supplierportal/error.jsp";
		self.close();
	}

	function thisLoad() {
		return;
	}

// end hiding contents -->
</SCRIPT>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>