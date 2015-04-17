<%@ page language="java" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	StringBuffer s_errorMsg = new StringBuffer();
	String	s_errorTitle	= "Puridiom Portal Login Failure";
	String	s_errorCode	= (String) request.getAttribute("err");
	String	s_errorstr	= (String) request.getAttribute("errorMsg");
	String exceptionMessage = "";

	if (s_errorstr == null) {
		s_errorstr = "";
	}
	if ( request.getParameter("errorMsg") != null ) {
		s_errorMsg.append("<BR>" + request.getParameter("errorMsg"));
	}
	if ( request.getParameter("err") != null )
	{
		s_errorCode = request.getParameter("err");
	}

	if (HiltonUtility.isEmpty(s_errorCode)) {
		s_errorCode = HiltonUtility.ckNull((String) request.getAttribute("errorCode"));
	}

	s_errorMsg.append( "<BR><BR>Please contact your system administrator." );
%>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
	<tr><td align=center height=50px class=error><%=s_errorTitle%></td></tr>
	<tr><td align=center><%=s_errorMsg%><br></td></tr>
</table>
<br>
<table border="0" cellspacing="0" cellpadding="0" width="680px">
	<tr>
	  <td align="center">For further assistance , please contact us at  <a href="mailto:<%=PropertiesManager.getInstance(oid).getProperty("MAILEVENTS", "AdminEmailAddr","support@puridiom.com")%>?&body=&nbsp;%0A%0&nbsp;%0A%0<%=exceptionMessage%>">Puridiom Help Desk.</a></td>
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
</table>

<%@ include file="/system/footer.jsp" %>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;


// end hiding contents -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>