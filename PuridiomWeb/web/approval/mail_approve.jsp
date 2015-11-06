<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.usermanager.*" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.encryptor.*" %>
<%@ page import="com.tsagate.properties.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
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

	oid = oid.toUpperCase();

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
	else if (type.equalsIgnoreCase("INV"))
	{
		formType = "Inventory";
	}
	else if (type.equalsIgnoreCase("HRA"))
	{
		formType = "HtmlReceipts";
	}
	else if (type.equalsIgnoreCase("RFQ"))
	{
		formType = "Solicitation";
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
	s_view = encoder.encodeForHTMLAttribute(s_view);
	if (s_view == null) {	s_view = "WIZARD";			}
	pageContext.setAttribute("oid", oid);
	pageContext.setAttribute("language", "");
%>
<html>
<HEAD>
	<meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
	<META HTTP-EQUIV="Cache-Control" content="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="Sat, 11 Sep 1971 12:00:00 GMT">
	<META NAME="Description" Content="<%=contextPath%>/system/header.jsp">
	<script type="text/javascript" src="<%=contextPath%>/scripts/jquery-1.11.0.min.js"></script>
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
		<b>Processing your Request to&nbsp;<%=action%>&nbsp;<%=formType%> # <%=reqNumber%><BR>
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
			<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=icHeader%>"/><br>
			<tsa:hidden name="PoHeader_icPoHeader" value="<%=icHeader%>"/><br>
			<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=icHeader%>"/><br>
			<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=icHeader%>"/><br>
			<tsa:hidden name="ApprovalLog_icHeader" value="<%=icHeader%>"/><br>
			<tsa:hidden name="userId" value="<%=uid %>"/><br>
			<tsa:hidden name="organizationId" value="<%=oid%>"/><P><br>
			<tsa:hidden name="mailId" value="<%=mid %>"/><P><br>
			<tsa:hidden name="language" value=""/><P><br>
			<tsa:hidden name="reqNumber" value="<%=reqNumber%>"/><P><br>
			<tsa:hidden name="InvItem_itemNumber" value="<%=reqNumber%>"/><P><br>
			<!-- <a href="javascript: retrieveReqApproval(); void(0);"><img src="<%=contextPath%>/images/approve_black.gif" border=0 alt="Approve">-->
			<tsa:hidden name="handler" value=""/><br>
			<tsa:hidden name="successPage" value=""/><br>
			<tsa:hidden name="failurePage" value="/system/error.jsp"/><br>
			<tsa:hidden name="emailAction" value="<%=action%>"/><br>
			<tsa:hidden name="noMenuOptions" value="Y"/><P><br>
			<tsa:hidden name="filename" value="<%=docFileName%>"/><P>
			<tsa:hidden name="fromEmail" value="Y"/><P>
			<tsa:hidden name="fromApproval" value="Y"/><P>
			<tsa:hidden name="receiptMethod" value="ReceiveByOrder"/><P>
		</form>
	</body>

<SCRIPT value=JavaScript>
<!-- Hide script
	var myPage = "mail_approve.jsp" ;
	frm = document.purchaseform;
	loadMe();

	function retrieveReqApproval()
	{
		<%if (action.equals("Forward"))
		{%>
			doSubmit('/requests/req_forward_copy.jsp', 'RequisitionApprovalRetrieve');
		<%}
		else
		{%>
			doSubmit('/requests/req_approval.jsp', 'RequisitionApprovalRetrieve');
		<%}%>
	}

	function retrievePoApproval()
	{
		<%if (action.equals("Forward"))
		{%>
			doSubmit('/orders/req_forward_copy.jsp', 'RequisitionApprovalRetrieve');
		<%}
		else
		{%>
			doSubmit('/orders/po_approval.jsp', 'PoApprovalRetrieve');
		<%}%>
	}

	function retrieveRfqApproval()
	{
		<%if (action.equals("Forward"))
		{%>
			doSubmit('/orders/req_forward_copy.jsp', 'RequisitionApprovalRetrieve');
		<%}
		else
		{%>
			doSubmit('/rfq/rfq_approval.jsp', 'RfqApprovalRetrieve');
		<%}%>
	}

	function retrieveInvoiceApproval()
	{
		<%if (action.equals("Forward"))
		{%>
			doSubmit('/invoice/iv_forward_copy.jsp', 'InvoiceApprovalRetrieve');
		<%}
		else
		{%>
			doSubmit('/invoice/iv_approval.jsp', 'InvoiceApprovalRetrieve');
		<%}%>
	}

	function retrieveInventoryApprovall()
	{
		doSubmit('/invoice/inv_approval.jsp', 'InvItemRetrieveById');
	}

	function htmlReceipts()
	{
			doSubmit('/receipts/rec_item_entry.jsp', 'ReceiptCreateRetrieve');
	}


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
			<%if(formType.equalsIgnoreCase("Requisition"))
			{%>
				retrieveReqApproval();
			<%}
			else if (formType.equalsIgnoreCase("Invoice"))
			{%>
				retrieveInvoiceApproval();
			<%}
			else if (formType.equalsIgnoreCase("Inventory"))
			{%>
				retrieveInventoryApproval();
			<%}
			else if(formType.equalsIgnoreCase("HtmlReceipts"))
			{%>
				htmlReceipts();
			<%}
			else if(formType.equalsIgnoreCase("Solicitation"))
			{%>
				retrieveRfqApproval();
			<%}
			else
			{%>
				retrievePoApproval();
			<%}%>
		}
	}

// End Hide script -->
</SCRIPT>
</html>