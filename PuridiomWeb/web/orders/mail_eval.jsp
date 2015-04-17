<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.usermanager.*" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.encryptor.*" %>
<%@ page import="com.tsagate.properties.*" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%
	Encoder encoder = DefaultEncoder.getInstance();
	Encryptor enc = new Encryptor();
	String action = (String) request.getAttribute("ack");
	if (action == null) {	action = "";	}

	String	 uid = (String) request.getAttribute("ext");
	if (uid == null)
	{
		uid = "";
	}
	else
	{
		uid = encoder.encodeForHTMLAttribute(enc.ofDecode(uid));
	}

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

	String	mid = (String) request.getAttribute("ail");
	if (mid == null)
	{
		mid = "";
	}
	else
	{
		mid = encoder.encodeForHTMLAttribute(enc.ofDecode(mid));
	}

	String	poNumber = (String) request.getAttribute("xnot");
	if (poNumber == null)
	{
		poNumber = (String) request.getAttribute("not");
		if (poNumber == null)
		{
			poNumber = "";
		}
		else
		{
			poNumber = enc.ofDecode(poNumber);
		}
	}
	else
	{
		poNumber = enc.ofDecode(poNumber);
	}

	String	type = (String) request.getAttribute("xpe");
	String formType = "Requisition";
	if (type == null)
	{
		type = "REQ";
	}
	else
	{
		type = enc.ofDecode(type);
	}
	if(type.equalsIgnoreCase("REQ"))
	{
		formType = "Requisition";
	}
	else
	{
		formType = "Order";
	}


	String	docFileName = (String) request.getAttribute("filename");
	if (docFileName == null) {	docFileName = "";	}

	String icHeader = (String) request.getAttribute("std");
	if (icHeader == null)
	{
		icHeader = "";
	}
	else
	{
		icHeader = enc.ofDecode(icHeader);
	}

	String fromEmail = (String) request.getAttribute("em");
	if (fromEmail == null)
	{
		fromEmail = "N";
	}

	UserProfile	user = UserManager.getInstance().getUser(oid,uid);
	UserRole	role = UserManager.getInstance().getUserRole(oid,uid);

	String s_view = (String) request.getAttribute("viewType");
	if (s_view == null) {	s_view = "WIZARD";			}
%>
<html>
<HEAD>
	<meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
	<META HTTP-EQUIV="Cache-Control" content="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="Sat, 11 Sep 1971 12:00:00 GMT">
	<META NAME="Description" Content="<%=contextPath%>/system/header.jsp">
	<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/puridiom.js"></SCRIPT>
	<script language='Javascript1.2' src="<%=contextPath%>/scripts/filter.js"></script>
	<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>
	<TITLE>Puridiom, Enabling Self-Service Procurement</TITLE>
</HEAD>
	<body>
	<br>
			<div id="forwardTextDiv" style="align:center; display:none;">
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
<tr>
	<td width="100%" height="400px" align="center" valign="top">
		<b>Processing your Request to <%=action%> <%=formType%> # <%=poNumber%><br>
	... Please wait ...</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border="1" width="200px" height="15px">
	</td>
</tr>
</table>
</div>
<div id="openFileTextDiv" style="align:center; display:none;">
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
<tr>
	<td width="100%" height="400px" align="center" valign="top">
	<b> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "waitopendoc", "Please wait while we open your Document")%>.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border="1" width="200px" height="15px">
	</td>
</tr>
</table>
		</div>
		<form name="purchaseform" target="_parent" action="<%=contextPath%>/procure" method="POST">
			<tsa:hidden name="PoHeader_icPoHeader" value="<%=icHeader%>"/><br>
			<tsa:hidden name="PerformanceDetail_icPoHeader" value="<%=icHeader%>"/>
			<tsa:hidden name="poNumber" value="<%=poNumber%>"/>
			<tsa:hidden name="userId" value="${userId}"/><P><br>
			<tsa:hidden name="organizationId" value="<%=oid%>"/><P><br>
			<tsa:hidden name="mailId" value="${mailId}"/><P><br>
			<tsa:hidden name="handler" value=""/><br>
			<tsa:hidden name="successPage" value=""/><br>
			<tsa:hidden name="failurePage" value="/system/error.jsp"/><br>
			<tsa:hidden name="emailAction" value="<%=action%>"/><br>
			<tsa:hidden name="noMenuOptions" value="Y"/><P><br>
			<tsa:hidden name="filename" value="<%=docFileName%>"/><P>
			<tsa:hidden name="fromEmail" value="Y"/><P>
		</form>
	</body>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	loadMe();

	function retrievePoEvaluation()
	{
		doSubmit('/orders/po_supplier_eval.jsp', 'VendorPerformanceRetrieveByOrder');
	}


	function loadMe()
	{
		displayArea("forwardTextDiv");
		retrievePoEvaluation();
	}

// End Hide script -->
</SCRIPT>
</html>