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
	Encoder encoder = DefaultEncoder.getInstance();

	String action = HiltonUtility.ckNull((String) request.getAttribute("ack"));

	String	 uid = HiltonUtility.ckNull((String) request.getAttribute("ext"));
	uid = (HiltonUtility.isEmpty(uid)) ? uid : enc.ofDecode(uid);

	String	oid = HiltonUtility.ckNull((String) request.getAttribute("zat"));
	oid = (HiltonUtility.isEmpty(oid)) ? oid : enc.ofDecode(oid);
	oid = oid.toUpperCase();

	String	mid = HiltonUtility.ckNull((String) request.getAttribute("ail"));
	mid = (HiltonUtility.isEmpty(mid)) ? mid : encoder.encodeForHTMLAttribute(enc.ofDecode(mid));

	String	documentNumber = HiltonUtility.ckNull((String) request.getAttribute("xnot"));
	documentNumber = (HiltonUtility.isEmpty(documentNumber)) ? documentNumber : enc.ofDecode(documentNumber);

	String	type = HiltonUtility.ckNull((String) request.getAttribute("xpe"));
	type = (HiltonUtility.isEmpty(type)) ? "REQ" : enc.ofDecode(type);

	String formType = "Requisition";

	if(type.equalsIgnoreCase("REQ"))
	{
		formType = "Requisition";
	}
	else if (type.equalsIgnoreCase("IV"))
	{
		formType = "Invoice";
	}
	else
	{
		formType = "Order";
	}

	String icApprovalLink = HiltonUtility.ckNull((String) request.getAttribute("std"));
	icApprovalLink = (HiltonUtility.isEmpty(icApprovalLink)) ? icApprovalLink : enc.ofDecode(icApprovalLink);

	UserProfile	user = UserManager.getInstance().getUser(oid,uid);
	UserRole	role = UserManager.getInstance().getUserRole(oid,uid);

	String s_view = (String) request.getAttribute("viewType");
	if (s_view == null) {	s_view = "WIZARD";			}
%>
<html>
<HEAD>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<META HTTP-EQUIV="Cache-Control" content="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="Sat, 11 Sep 1971 12:00:00 GMT">
	<META NAME="Description" Content="<%=contextPath%>/system/header.jsp">
	<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/puridiom.js"></SCRIPT>
	<script language='Javascript1.2' src="<%=contextPath%>/scripts/filter.js"></script>
	<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>
	<TITLE>Puridiom, Enabling Self-Service Procurement</TITLE>
<%@ include file="/system/stylesheet_link.jsp" %>
</HEAD>
<body>
	<br>
	<div id="forwardTextDiv" style="align:center; display:none;">
		<table border="0" cellspacing="0" cellpadding="0" width="680px">
			<tr>
				<td width="100%" align="center" valign="top">
					<b>Processing your Request to&nbsp;<%= formType %> # <%= documentNumber %><BR>
				... Please wait ...</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border="1" width="200px" height="15px">
				</td>
			</tr>
		</table>
	</div>
	<form name="purchaseform" target="_parent" action="<%=contextPath%>/procure" method="POST">
		<tsa:hidden name="ApprovalLink_icApprovalLink" value="<%= icApprovalLink %>"/><br>
		<tsa:hidden name="userId" value="${userId}"/><P><br>
		<tsa:hidden name="organizationId" value="<%= oid %>"/><P><br>
		<tsa:hidden name="mailId" value="${mailId}"/><P><br>
		<tsa:hidden name="docNumber" value="<%= documentNumber %>"/><P><br>
		<tsa:hidden name="handler" value=""/><br>
		<tsa:hidden name="successPage" value=""/><br>
		<tsa:hidden name="failurePage" value="/system/error.jsp"/><br>
		<tsa:hidden name="serviceAction" value="<%= action %>"/><br>
		<tsa:hidden name="noMenuOptions" value="Y"/><P><br>
		<tsa:hidden name="fromEmail" value="Y"/><P>
		<tsa:hidden name="fromApproval" value="Y"/><P>
	</form>
</body>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	loadMe();

	function executePoService()
	{
		<%if (action.equalsIgnoreCase("posupack"))
		{%>
			doSubmit('/orders/po_acknowledge.jsp', 'ApprovalLinkService');
		<% } %>
	}

	function loadMe()
	{
		displayArea("forwardTextDiv");

		executePoService();
	}

// End Hide script -->
</SCRIPT>
</html>