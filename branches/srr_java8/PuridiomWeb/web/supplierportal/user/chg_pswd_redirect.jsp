<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/context_path.jsp" %>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.encryptor.*" %>
<%@ page import="com.tsagate.properties.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%

	Encryptor enc = new Encryptor();

	String	oid = (String) request.getAttribute("zat");
	if (oid == null)
	{
		oid = (String) request.getAttribute("oid");
		if (oid == null)
		{
			oid = "";
		}
	}
	else
	{
		oid = enc.ofDecode(oid);
	}

	oid = oid.toUpperCase();
	System.out.println("OID is what? " + oid);
%>
<html>
<HEAD>
	<meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
	<META HTTP-EQUIV="Cache-Control" content="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="Sat, 11 Sep 1971 12:00:00 GMT">
	<META NAME="Description" Content="<%=contextPath%>/supplierportal/user/chg_pswd_redirect.jsp">
	<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/puridiom.js"></SCRIPT>
	<script language='Javascript1.2' src="<%=contextPath%>/scripts/filter.js"></script>
	<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>
	<TITLE>Puridiom, Enabling Self-Service Procurement</TITLE>
<%@ include file="/supplierportal/system/stylesheet_link.jsp" %>
</HEAD>
	<body>
	<br>
			<div id="forwardTextDiv" style="align:center; display:none;">
<table border="0" cellspacing="0" cellpadding="0" width=880px>
<tr>
	<td width="100%" align="center" valign="top">
	... Please wait ...</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border="1" width="200px" height="15px">
	</td>
</tr>
</table>
</div>
<div id="openFileTextDiv" style="align:center; display:none;">
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
<tr>
	<td width="100%" height="400px" align="center" valign="top">
	<b> <%=DictionaryManager.getLabel(oid, "waitopendoc", "Please wait while we open your Document")%>.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border="1" width="200px" height="15px">
	</td>
</tr>
</table>
		</div>
		<form name="purchaseform" target="_parent" action="<%=contextPath%>/procure" method="POST">
			<tsa:hidden name="ResetPasswordLink_icLink" value="${edoc}"/>
			<tsa:hidden name="userId" value="${userId}"/>
			<tsa:hidden name="organizationId" value="<%=oid%>"/>
			<tsa:hidden name="mailId" value="${mailId}"/>
			<tsa:hidden name="handler" value=""/>
			<tsa:hidden name="successPage" value=""/>
			<tsa:hidden name="failurePage" value="/system/error.jsp"/>
			<tsa:hidden name="noMenuOptions" value="Y"/>
		</form>
	</body>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	// Added for puridiomx to avoid javascript error
	myPage = "chg_pswd_rediret.jsp" ;

	doSubmit('/supplierportal/user/chg_pswd_directly.jsp', 'ResetPasswordLinkCheck');

// End Hide script -->
</SCRIPT>
</html>