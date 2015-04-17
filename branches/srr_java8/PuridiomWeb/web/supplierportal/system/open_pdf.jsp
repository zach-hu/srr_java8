<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ page import="javax.servlet.http.*" %>
<%
	String	s_doc = request.getParameter("doc");
	StringBuffer	requestUrl = request.getRequestURL();
	String	docUrl = "";
	
	if (requestUrl != null) {
		docUrl = requestUrl.substring(0, requestUrl.indexOf("/system/open_pdf.jsp"));
		docUrl = docUrl + "hilton-documents/";
	}
	out.println("docUrl = " + docUrl);
	
	if (s_doc == null) {
		s_doc = "";
	}
	
%>
<html>
<head></head>

<body></body>

<SCRIPT language="JavaScript1.2">
<!--  hide script from old browsers

	window.location.href = "<%=docUrl + s_doc%>";

//-->
</SCRIPT>
</html>