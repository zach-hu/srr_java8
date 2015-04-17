<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>
<%@ page import="com.tsa.puridiom.supplierportal.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ include file="/supplierportal/system/context_path.jsp" %>
<%
	String	userId = (String) request.getAttribute("userId");
	String	oid = (String) request.getAttribute("organizationId");
	String userDateFormat = "";
	String userTimeZone = "";
	BidBoardUser	user = BidBoardUserManager.getInstance().getUserInCache(oid, userId);

	if (oid == null) {
		oid = "";
	}
	if (userId == null) {
		userId = "";
	}
	if (user == null) {
		user = new BidBoardUser();
	}
%>

<HTML>
<HEAD>
	<meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
	<META HTTP-EQUIV="Cache-Control" content="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="Sat, 11 Sep 1971 12:00:00 GMT">
	<META NAME="Description" Content="<%=contextPath%>/supplierportal/">
	<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/hilton.js"></SCRIPT>
	<TITLE>Puridiom Supplier Portal</TITLE>
	<%@ include file="/supplierportal/system/stylesheet_link.jsp" %>
</HEAD>
<SCRIPT language="JavaScript1.2">
<!--  hide script from old browsers

	contextPath = "<%=contextPath%>/supplierportal/";

//-->
</SCRIPT>
<body marginwidth=0 marginheight=0 topmargin=0 leftmargin=0 width=680px onload="thisLoadPopup();">
<form name="purchaseform" target="_parent" action="<%=contextPath%>/supplier" method="POST">
<div id="hiddenFields" style="display:none;"></div>

<tsa:hidden name="handler" value=""/>
<tsa:hidden name="successPage" value=""/>
<tsa:hidden name="failurePage" value="/system/error_popup.jsp"/>
<tsa:hidden name="userId" value="<%=userId%>"/>
<tsa:hidden name="organizationId" value="<%=oid%>"/>

<br>