<%@ page language="java" isErrorPage="true" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.organization.OrganizationManager" %>
<%
	StringBuffer s_errorMsg = new StringBuffer();
	String	s_errorTitle	= "Exception Page";
	String	s_errorCode	= "";
	String	s_errorstr	= (String) request.getAttribute("errorMsg");

	if (!HiltonUtility.isEmpty(oid) && !OrganizationManager.getInstance().isOrganizationValid(oid)) {
		s_errorMsg.append( "<b>The Organization Id specified [" + oid + "] is invalid.</b><br><br>" );
	}
	if (s_errorstr == null) {
		s_errorstr = "";
	}
	if ( request.getParameter("errorMsg") != null ) {
		s_errorMsg.append("<BR>" + request.getParameter("errorMsg"));
	}
	if ( request.getParameter("err") != null ) {
		s_errorCode = request.getParameter("err");
	}
	if ( request.getParameter("uri") != null ) {
		s_errorTitle = "Compile Error" ;
		s_errorMsg.append("<BR>A compiler error occurred.<BR>") ;
	}
	else if (s_errorCode.equals("systemUnavailable")) {
		s_errorTitle = "System Unavailable";
	}
	else if ( s_errorCode.equals("invalidSession") ) {
		out.println("<!-- errorCode = " + s_errorCode + "! -->");
		s_errorTitle = "Session Expired";
		s_errorMsg.append("<BR>Your session has expired.  Please login again.<BR>");
		response.addHeader("refresh", "5; URL=" + contextPath + "/index.jsp");
	} 
	else if (s_errorstr.length() > 0) {
		s_errorMsg.append(s_errorstr);
		exception = (Exception) request.getAttribute("exception");
		
	} 
	else {
		exception = (Exception) request.getAttribute("exception");
		if (exception == null) {
			s_errorMsg.append( s_errorCode );
			s_errorMsg.append( "<BR> An unknown error occurred." );
		}
	}
	
	/*else if ( (exception == null) && (s_errorstr.length() <=0) ) {
		exception = (Exception) request.getAttribute("exception");
		if (exception == null) {
			s_errorMsg.append( s_errorCode );
			s_errorMsg.append( "<BR> An unknown error occurred." );
		}
	}
	else {
		s_errorMsg.append( s_errorstr );
	}*/

	if ( (s_errorCode.indexOf("invalidSession") < 0) && !s_errorCode.equals("systemUnavailable")) {
		s_errorMsg.append( "<br><br>Please contact your system administrator." );
	}
	
	String showDetailExcepcion = PropertiesManager.getInstance(oid).getProperty("MISC", "SHOW DETAIL EXCEPTION", "N");
%>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
	<tr><td align=center height=50px class=error><%=s_errorTitle%></td></tr>

	<tr><td align=center><b><%=s_errorMsg%></b><br></td></tr>

<%	if ( !s_errorCode.equals("invalidSession") ) {%>
	<tr><td><br><br></td></tr>
	<tr><td align=center><div id="pxbutton"><A HREF="javascript: closeMe(); void(0);">Close</a></div></td></tr>
<%	}%>
	
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


<%if (exception != null && showDetailExcepcion.equals("Y")) { 
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

	var error_code = "<%=s_errorCode%>";

	if ( (error_code == "invalidSession") && (opener != null) ) {
		window.parent.location.href = "<%=contextPath%>/system/error.jsp";
		self.close();
	}

	function thisLoad() {
		return;
	}

	function closeMe() {
		window.top.hidePopWin();
//		if (parent != undefined && parent.document.all("getInfoFrame") != undefined) {
//			parent.hideArea("getInfoFrame");
//		} else {
//			self.close();
//		}
	}

	function showDetails() {
		document.getElementById("tableExceptionDetail").style.display = '';
		document.getElementById("btnHideDetails").style.display = '';
		document.getElementById("btnShowDetails").style.display = 'none';
   	}
    
    function hideDetails() {
    	document.getElementById("btnHideDetails").style.display = 'none';
    	document.getElementById("tableExceptionDetail").style.display = 'none';
		document.getElementById("btnShowDetails").style.display = '';
   	}

// end hiding contents -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>