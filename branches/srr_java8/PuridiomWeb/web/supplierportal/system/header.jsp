<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<%@ page import="com.tsa.puridiom.supplierportal.*" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.html.*" %>
<%@ page import="com.tsagate.properties.*" %>
<%@ page import="java.math.BigDecimal" %>
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

	String s_view = (String) request.getAttribute("viewType");
	if (HiltonUtility.isEmpty(s_view)) {
		s_view = "WIZARD";
	}
	boolean requireAcceptance = PropertiesManager.getInstance(oid).getProperty("VENDOR OPTIONS", "REQUIRETERMSACCEPTANCE", "Y").equals("Y");
%>

<HTML>
<HEAD>
	<meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
	<META HTTP-EQUIV="Cache-Control" content="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="Sat, 11 Sep 1971 12:00:00 GMT">
	<META NAME="Description" Content="<%=contextPath%>/supplierportal/menu/menu_pg.jsp">
	<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/hilton.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/filter.js"></SCRIPT>
	<TITLE>Puridiom Supplier Portal</TITLE>
	<%@ include file="/supplierportal/system/stylesheet_link.jsp" %>
</HEAD>
<SCRIPT language="JavaScript1.2">
<!--  hide script from old browsers

	contextPath = "<%=contextPath%>/supplierportal/";

//-->
</SCRIPT>

<body marginwidth=0 marginheight=0 topmargin=0 leftmargin=0 width=680px onload="thisLoad();" onunload="closeOpenWindows();">
<form name="purchaseform" target="_parent" action="<%=contextPath%>/supplier" method="POST">
<div id="filterFields" style="display:none;"></div>
<div id="hiddenFields" style="display:none;"></div>

<input type=hidden name="handler" value=""></input>
<input type=hidden name="successPage" value=""></input>
<input type=hidden name="failurePage" value="system/error.jsp"></input>
<input type=hidden name="userName" value="<%=user.getDisplayName()%>"></input>
<input type=hidden name="userId" value="<%=userId%>"></input>
<input type=hidden name="organizationId" value="<%=oid%>"></input>
<input type=hidden name="browseName" value=""></input>
<input type=hidden name="VendorRegister_contactEmailAddr" value="<%=userId%>"></input>
<input type=hidden name="Contact_emailAddr" value="<%=userId%>"></input>
<input type=hidden name="vendorId" value="<%=user.getVendorId()%>"></input>

<%@ include file="/supplierportal/system/header_menu_options.jsp" %>
