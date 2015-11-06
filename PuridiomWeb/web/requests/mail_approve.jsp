<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.usermanager.*" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.encryptor.*" %>
<%@ page import="com.tsagate.properties.*" %>
<%
	Encryptor enc = new Encryptor();

	String action = (String) request.getParameter("ack");
	if (action == null) {	action = "";	}

	String	 uid = (String) request.getParameter("ext");
	if (uid == null)
	{
		uid = "";
	}
	else
	{
		uid = enc.ofDecode(uid);
	}

	String	oid = (String) request.getParameter("zat");
	if (oid == null)
	{
		oid = (String) request.getParameter("oid");
		if (oid == null)
		{
			oid = "";
		}

	}
	else
	{
		oid = enc.ofDecode(oid);
	}

	String	mid = (String) request.getParameter("ail");
	if (mid == null)
	{
		mid = "";
	}
	else
	{
		mid = enc.ofDecode(mid);
	}

	String	reqNumber = (String) request.getParameter("xnot");
	if (reqNumber == null)
	{
		reqNumber = (String) request.getParameter("not");
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

	String	type = (String) request.getParameter("xpe");
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

	String	docFileName = (String) request.getParameter("filename");
	if (docFileName == null) {	docFileName = "";	}

	String icHeader = (String) request.getParameter("std");
	if (icHeader == null)
	{
		icHeader = "";
	}
	else
	{
		icHeader = enc.ofDecode(icHeader);
	}

	String fromEmail = (String) request.getParameter("em");
	if (fromEmail == null)
	{
		fromEmail = "N";
	}

	UserProfile	user = UserManager.getInstance().getUser(oid,uid);
	UserRole	role = UserManager.getInstance().getUserRole(oid,uid);

	String s_view = (String) request.getAttribute("viewType");
	if (s_view == null) {	s_view = "WIZARD";			}
	pageContext.setAttribute("oid", oid);
	pageContext.setAttribute("language", "");
%>
<html>
<HEAD>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
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
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
<tr>
	<td width="100%" height="400px" align="center" valign="top">
		<b>Processing your Request to &nbsp;<%=action%>&nbsp;<%=formType%> # <%=reqNumber%><BR>
	... Please wait ...</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border="1" width="200px" height="15px">
	</td>
</tr>
</table>
</div>
<div id="openFileTextDiv" style="align:center; display:none;">
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
<tr>
	<td width="100%" height="400px" align="center" valign="top">
	<b><tsa:label labelName="waitopendoc" defaultString="Please wait while we open your Document" noinstance="true" /> .
	</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border="1" width="200px" height="15px">
	</td>
</tr>
</table>
		</div>
		<form name="purchaseform" target="_parent" action="<%=contextPath%>/procure" method="POST">
			<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=icHeader%>"/><br>
			<tsa:hidden name="PoHeader_icPoHeader" value="<%=icHeader%>"/><br>
			 <tsa:hidden name="ApprovalLog_icHeader" value="<%=icHeader%>"/><br>
			<tsa:hidden name="userId" value="<%=uid %>"/><P><br>
			<tsa:hidden name="organizationId" value="<%=oid%>"/><P><br>
			<tsa:hidden name="mailId" value="<%=mid %>"/><P><br>
			<tsa:hidden name="reqNumber" value="<%=reqNumber%>"/><P><br>
			<!-- <a href="javascript: retrieveReqApproval(); void(0);"><img src="<%=contextPath%>/images/approve_black.gif" border=0 alt="Approve">-->
			<tsa:hidden name="handler" value=""/><br>
			<tsa:hidden name="successPage" value=""/><br>
			<tsa:hidden name="failurePage" value="/system/error.jsp"/><br>
			<tsa:hidden name="emailAction" value="<%=action%>"/><br>
			<tsa:hidden name="noMenuOptions" value="Y"/><P><br>
			<tsa:hidden name="filename" value="<%=docFileName%>"/><P>
			<tsa:hidden name="fromEmail" value="Y"/><P>
			<tsa:hidden name="fromApproval" value="Y"/><P>
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


	function loadMe()
	{
		if(frm.filename.value.length > 0)
		{
			displayArea("openFileTextDiv");
			frm.userId.value='sysadm';
			doSubmit('/system/popupDocAttachment.jsp', 'DocAttachmentDownloadFile');
		}
		else
		{
			displayArea("forwardTextDiv");
			<%if(formType.equalsIgnoreCase("Requisition"))
			{%>
				retrieveReqApproval();
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