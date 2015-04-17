<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>
<%@ page import="com.tsa.puridiom.usermanager.*" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.encryptor.*" %>
<%@ page import="com.tsagate.properties.*" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%
	Encryptor enc = new Encryptor();

	String	 uid = HiltonUtility.ckNull((String) request.getParameter("ext"));
	if (!HiltonUtility.isEmpty(uid)) {
		uid = enc.ofDecode(uid);
	}

	String	oid = HiltonUtility.ckNull((String) request.getParameter("zat"));
	if (HiltonUtility.isEmpty(oid)) {
		oid = HiltonUtility.ckNull((String) request.getParameter("oid"));
	}
	else {
		oid = enc.ofDecode(oid);
	}

	String	mid = HiltonUtility.ckNull((String) request.getParameter("ail"));
	Encoder encoder = DefaultEncoder.getInstance();
	mid = encoder.encodeForHTMLAttribute(enc.ofDecode(mid));

	String	icPoHeader = HiltonUtility.ckNull((String) request.getParameter("ciop"));
	if (HiltonUtility.isEmpty(icPoHeader)) {
		icPoHeader = HiltonUtility.ckNull((String) request.getParameter("ciop"));
	}
	if (HiltonUtility.isEmpty(icPoHeader)) {
		icPoHeader = "0";
	}
	else {
		icPoHeader = enc.ofDecode(icPoHeader);
	}

	UserProfile	user = UserManager.getInstance().getUser(oid,uid);
	UserRole	role = UserManager.getInstance().getUserRole(oid,uid);
%>
<html>
<head>
	<meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
	<META HTTP-EQUIV="Cache-Control" content="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="Sat, 11 Sep 1971 12:00:00 GMT">
	<META NAME="Description" Content="<%=contextPath%>/system/header.jsp">
	<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/puridiom.js"></SCRIPT>
	<script language='Javascript1.2' src="<%=contextPath%>/scripts/filter.js"></script>
	<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>
	<title>Puridiom, Enabling Self-Service Procurement</title>
	<%@ include file="/system/stylesheet_link.jsp" %>
</head>
<body>
<br>
<div id="forwardTextDiv" style="align:center; display:none;">
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
<tr>
	<td width="100%" height="400px" align="center" valign="top">
		<b>Processing your Request to create a Return Receipt<br>
	... Please wait ...</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border="1" width="200px" height="15px">
	</td>
</tr>
</table>
</div>
<form name="purchaseform" target="_parent" action="<%=contextPath%>/procure" method="POST">
<tsa:hidden name="userId" value="<${esapi:encodeForHTMLAttribute(userId)}"/><P><br>
<tsa:hidden name="organizationId" value="<%=oid%>"/><P><br>
<tsa:hidden name="mailId" value="${mailId}"/><P><br>
<tsa:hidden name="handler" value=""/><br>
<tsa:hidden name="successPage" value=""/><br>
<tsa:hidden name="failurePage" value="/system/error.jsp"/><br>
<tsa:hidden name="noMenuOptions" value="Y"/>
<tsa:hidden name="fromEmail" value="Y"/>
<tsa:hidden name="receiptMethod" value="Return"/>
<tsa:hidden name="ReceiptHeader_icPoHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=icPoHeader%>"/><br>
</form>
</body>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	loadMe();

	function createReturnReceipt() {
		doSubmit('/receipts/rec_return.jsp','ReceiptCreateRetrieve');
	}

	function loadMe() {
		displayArea("forwardTextDiv");
//		createReturnReceipt();
	}

// End Hide script -->
</SCRIPT>
</html>