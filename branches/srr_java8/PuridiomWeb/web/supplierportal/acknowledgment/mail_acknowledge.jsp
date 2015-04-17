<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.supplierportal.*" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.encryptor.*" %>
<%@ page import="com.tsagate.properties.*" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%
	Encryptor enc = new Encryptor();
	Encoder encoder = DefaultEncoder.getInstance();

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

	String	reqNumber = (String) request.getAttribute("xnot");
	if (reqNumber == null)
	{
		reqNumber = (String) request.getAttribute("not");
		if (reqNumber == null)
		{
			reqNumber = "";
		}
		else
		{
			reqNumber = enc.ofDecode(reqNumber);
		}
	}
	else
	{
		reqNumber = enc.ofDecode(reqNumber);
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
	else if (type.equalsIgnoreCase("IV"))
	{
		formType = "Invoice";
	}
	else if (type.equalsIgnoreCase("HRA"))
	{
		formType = "HtmlReceipts";
	}
	else
	{
		formType = "Order";
	}
	out.println("formtype: " + formType);


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

	BidBoardUser	user = BidBoardUserManager.getInstance().getUserInCache(oid, uid);

	String s_view = (String) request.getAttribute("viewType");
	if (s_view == null) {	s_view = "WIZARD";			}
%>
<html>
<HEAD>
	<meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
	<META HTTP-EQUIV="Cache-Control" content="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="Sat, 11 Sep 1971 12:00:00 GMT">
	<META NAME="Description" Content="<%=contextPath%>/supplierportal/system/header.jsp">
	<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/hilton.js"></SCRIPT>
	<script language='Javascript1.2' src="<%=contextPath%>/supplierportal/scripts/filter.js"></script>
	<script language='Javascript1.2' src="<%=contextPath%>/supplierportal/scripts/dynamicTables.js"></script>
	<TITLE>Puridiom, Enabling Self-Service Procurement</TITLE>
</HEAD>
	<body onload="javascript:loadMe();">
	<br>
			<div id="forwardTextDiv" style="align:center; display:none;">
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
<tr>
	<td width="100%" height="400px" align="center" valign="top">
		<b>Processing your Request to &nbsp;<%=action%>&nbsp;<%=formType%> # <%=reqNumber%><BR>
	... Please wait ...</b><br><br><br><img src="<%=contextPath%>/supplierportal/images/processing.gif" border="1" width="200px" height="15px">
	</td>
</tr>
</table>
</div>
<div id="openFileTextDiv" style="align:center; display:none;">
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
<tr>
	<td width="100%" height="400px" align="center" valign="top">
	<b> <%=DictionaryManager.getLabel(oid, "waitopendoc", "Please wait while we open your Document")%>.</b><br><br><br><img src="<%=contextPath%>/supplierportal/images/processing.gif" border="1" width="200px" height="15px">
	</td>
</tr>
</table>
		</div>
		<form name="purchaseform" target="_parent" action="<%=contextPath%>/supplierportal/suppliers" method="POST">
			<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=icHeader%>"/><br>
			<tsa:hidden name="PoHeader_icPoHeader" value="<%=icHeader%>"/><br>
			<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=icHeader%>"/><br>
			<tsa:hidden name="ApprovalLog_icHeader" value="<%=icHeader%>"/><br>
			<tsa:hidden name="userId" value="${userId}"/><P><br>
			<tsa:hidden name="organizationId" value="<%=oid%>"/><P><br>
			<tsa:hidden name="vendorId" value="<%=user.getVendorId()%>"/>
			<tsa:hidden name="mailId" value="<%=mid%>"/><P><br>
			<tsa:hidden name="reqNumber" value="<%=reqNumber%>"/><P><br>
			<!-- <a href="javascript: retrieveReqApproval(); void(0);"><img src="<%=contextPath%>/supplierportal/images/approve_black.gif" border=0 alt="Approve">-->
			<tsa:hidden name="handler" value=""/><br>
			<tsa:hidden name="successPage" value=""/><br>
			<tsa:hidden name="failurePage" value="/system/error.jsp"/><br>
			<tsa:hidden name="emailAction" value="<%=action%>"/><br>
			<tsa:hidden name="noMenuOptions" value="Y"/><P><br>
			<tsa:hidden name="filename" value="<%=docFileName%>"/><P>
			<tsa:hidden name="fromEmail" value="Y"/><P>
			<tsa:hidden name="fromApproval" value="Y"/><P>
			<tsa:hidden name="receiptMethod" value="ReceiveByOrder"/><P>
			<tsa:hidden name="browseName" value=""/>
		</form>
	</body>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function loadMe()
	{
		if(frm.filename.value.length > 0)
		{
			displayArea("openFileTextDiv");
			frm.userId.value='sysadm';
			doSubmit('', 'DocAttachmentDownloadFile');
		}
		else
		{
			displayArea("forwardTextDiv");
			browse('po-pending-acknowledgement');
		}
	}

// End Hide script -->
</SCRIPT>
</html>