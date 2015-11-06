<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>
<%@ page import="com.tsa.puridiom.common.utility.TokenProcessor" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.organization.OrganizationManager" %>
<%@ page import="com.tsa.puridiom.usermanager.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.Dates" %>
<%@ page import="com.tsagate.html.*" %>
<%@ page import="com.tsagate.properties.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="com.tsa.puridiom.RequestIdManager" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Encoder headerEncoder = DefaultEncoder.getInstance();
	String	uid = (String) request.getAttribute("userId");
	String	oid = (String) request.getAttribute("organizationId");
	String	sid = (String) request.getAttribute("sessionId");
	String 	userTokenId = (String) request.getAttribute("userTokenId");
	
	String	module = (String) request.getAttribute("module");
	String	moduleType = (String) request.getAttribute("moduleType");
	String	originalOid = oid;
	String	auditTransactionId = (String)request.getAttribute("auditTransactionId");
	String	language = "";//(String)request.getAttribute("language");
	String  returnUrl = HiltonUtility.ckNull((String)request.getParameter("ReturnURL"));
	String  changeRequisitioner = HiltonUtility.ckNull((String) request.getAttribute("ChangeRequisitioner"));
	String  s_flag = "";
	String	formWidth = "980px";
	String	formEntryWidth = "880px";
	boolean isXpress = false;
	boolean authenticated = false;
	UserProfile	user = UserManager.getInstance().getUser(oid,uid);
	UserRole	role = UserManager.getInstance().getUserRole(oid,uid);
	String userTimeZone = user.getTimeZone();
// 	String  epmc = HiltonUtility.ckNull((String) request.getAttribute("epmc"));
	
	/*if(HiltonUtility.isEmpty(userTimeZone)){
		userTimeZone = PropertiesManager.getInstance(oid).getProperty("USER DEFAULTS", "TIMEZONE", "EST"); 
	}*/
	String userDateFormat = user.getDateFormat();
	Date 	d_today = Dates.getCurrentDate(userTimeZone);
	String  vendorValueDefault = "";

	if (uid == null) {		uid = "";	}
	if (oid == null) {		oid = "";	}
	if (sid == null) {		sid = "";	}
	if (language == null) {		language = "";	}
	if (module == null) {		module = "";	}
	if (moduleType == null) {		moduleType = "";	}
	if (HiltonUtility.isEmpty(userDateFormat)) {
		userDateFormat = PropertiesManager.getInstance(oid).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
	}

	String view = HiltonUtility.ckNull((String) request.getAttribute("view"));
	String s_view = (String) request.getAttribute("viewType");
	if (HiltonUtility.isEmpty(s_view)) {
		s_view = "WIZARD";
	}
	if (!HiltonUtility.isEmpty((String) request.getAttribute("authenticated"))) {
		String	authenticatedValue = (String) request.getAttribute("authenticated");
		authenticated = authenticatedValue.equals("true");
	}
	String	s_account_cds_description = PropertiesManager.getInstance(oid).getProperty("INV OPTIONS","CDSDESCRIPTION","N");
	String methodPercent = PropertiesManager.getInstance(oid).getProperty("ACCOUNTS", "METHODPERCENT", "Y") ;
	if (requestURLPath.indexOf("puridiomx") >= 0 || (requestURLPath.indexOf("localhost") >= 0 && oid.startsWith("PX"))) {
		isXpress = true;
	}
	if (HiltonUtility.isEmpty(oid) && isXpress) {
		oid = "PURIDIOMX";
	}

	String s_req_udf10 = "";

	String s_req_departmentCode = "";

	String s_req_appBy = "";

	pageContext.setAttribute("oid", oid);
	pageContext.setAttribute("module", module);
	pageContext.setAttribute("moduleType", moduleType);

	String enableAudit=PropertiesManager.getInstance(oid).getProperty("AUDITTRAIL","ENABLE","Y");
%>
<HTML>
<HEAD>
	<META HTTP-EQUIV="Cache-Control" content="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="Sat, 11 Sep 1971 12:00:00 GMT">
	<META NAME="Description" Content="<%=contextPath%>/system/header.jsp">
	<meta http-equiv=content-type content="text/html; charset=UTF-8">
	<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/puridiom.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/spell.js"></SCRIPT>
	<script language='Javascript1.2' src="<%=contextPath%>/scripts/filter.js"></script>
	<TITLE><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "systemtitle", "Puridiom 4.0, Enabling Self-Service Procurement")%></TITLE>
	<script type="text/javascript" src="<%=contextPath%>/scripts/subCommon.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/scripts/subModal.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/scripts/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/scripts/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/scripts/core-min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/scripts/crypto-1.1.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/scripts/md5-min.js"></script>
 	<script type="text/javascript" src="<%=contextPath%>/scripts/sha1-min.js"></script>
    
<%@ include file="/system/stylesheet_link.jsp" %>
	<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=contextPath%>/system/styles/subModal.css">
</HEAD>
<style type="text/css">
body {
	margin:0;
	padding:0;
}

@keyframes iconNew {
 from {
   background-color:#FFEB3B ;
   border: 2px solid #F57F17 ;
 } 
 to {
   background-color:#FDD835 ;
   border: 2px solid #FFEB3B ;
 }
}
.iconNew {
    border-radius: 5px;
   width: 42px;
   padding: 0 3px;
   background-color:#FFEB3B ;
   border: 2px solid #F57F17 ;
    -moz-animation: iconNew 1s infinite alternate; 
    -webkit-animation: iconNew 1s infinite alternate; 
    -ms-animation: iconNew 1s infinite alternate; 
    animation: iconNew 1s infinite alternate; 
}
.iconNew:hover {
    border-radius: 5px;
   width: 42px;
   padding: 0 3px;
    transition: all 0.5s ease;
     background-color:#FDD835 ;
   border: 2px solid #FFEB3B ;
}
</style>

<script language='Javascript1.2' type="text/javascript">
<!--
	   DOM = (document.getElementById) ? true : false;
	   NS4 = (document.layers) ? true : false;
	NS4old = (NS4 && (parseFloat(navigator.appVersion) < 4.02));
	    IE = (document.all) ? true : false;
	   IE4 = IE && !DOM;
	   Mac = (navigator.appVersion.indexOf("Mac") != -1);
	  IE4M = IE4 && Mac;
	IsMenu = (DOM || (NS4 && !NS4old) || (IE && !IE4M));

	if(window.event + "" == "undefined") event = null;
	function f_PopUp(){return};
	function f_PopDown(){return};
	popUp = f_PopUp;
	popDown = f_PopDown;
	var browseTxt = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browse", "Browse", false)%>";
	var newTxt = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "createnew", "Create New", false)%>";
	var browseMsrTxt = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browsemsr", "Browse MSR", false)%>";
	var newMsrTxt = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "createnewmsr", "Create New MSR", false)%>";
	var newFromReqTxt = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "createfromreq", "Create From Requisition", false)%>";
	var browseItem = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browseitem", "Browse by Item", false)%>";
	var browseAccount = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browseaccount", "Browse By Account", false)%>";
	var menuLocation = <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "menulocation", "72", false)%>;
	var printPdfLabel = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "printPdf", "Issue Document", false)%>";
	var printLabelPdf = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "printLabelPdf", "Print Labels", false)%>";
	var save = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "save", "Save", false)%>";
	var saveas = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "saveas", "Save As", false)%>";
	var saveastemplate = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "saveastemplate", "Save As Template", false)%>";
	var recall = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "recall", "Recall", false)%>";
	var rountinglist = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routinglist", "Routing List", false)%>";
	var cancel = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cancel", "Cancel", false)%>";
	var validate = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "validate", "Validate", false)%>";
	var viewhistory = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "history", "History", false)%>";
	var printpreview = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "printpreview", "Print Preview", false)%>";
	var procurementremarks = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "procurementremarks", "Procurement Remarks", false)%>";
	var generateroutinglist = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "generateroutinglist", "Generate Routing List", false)%>";
	var adjustReceipt = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "adjustReceipt", "Adjust Receipt", false)%>";
	var returnReceivedItems = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "returnReceivedItems", "Return Received Items", false)%>";

	if(IsMenu) {
		document.write("<scr" + "ipt language='Javascript1.2' src='<%=contextPath%>/menu/menubar_arrays.js' type='text/javascript'><\/scr" + "ipt>");
		document.write("<scr" + "ipt language='Javascript1.2' src='<%=contextPath%>/menu/hierMainMenu.js' type='text/javascript'><\/scr" + "ipt>");
	}

	var orgId = "<%=headerEncoder.encodeForJavaScript(oid)%>";
	var userCode = "<%=headerEncoder.encodeForJavaScript(HiltonUtility.ckNull(user.getUserId()))%>";
	var userDepartment = "<%=headerEncoder.encodeForJavaScript(HiltonUtility.ckNull(user.getDepartment()))%>";
	var userNameUdf1 = "<%=headerEncoder.encodeForJavaScript(HiltonUtility.ckNull(user.getNameUdf1()))%>";
	var userNameUdf2 = "<%=headerEncoder.encodeForJavaScript(HiltonUtility.ckNull(user.getNameUdf2()))%>";
	var userNameUdf3 = "<%=headerEncoder.encodeForJavaScript(HiltonUtility.ckNull(user.getNameUdf3()))%>";
	var methodPercent = "<%=headerEncoder.encodeForJavaScript(methodPercent)%>";
	var isXpress = <%=isXpress%> ;
	var reqType = "";

	//Variable for desactivate audit Trail defined in puridiom.js
	strEnableAuditTrail = "<%=headerEncoder.encodeForJavaScript(enableAudit)%>";
//-->
</SCRIPT>
<body marginwidth=0 marginheight=0 topmargin=0 leftmargin=0 width=<%=formWidth%> onload="thisLoad();loadNavMenu();onLoadComplete();" onUnload="event.returnValue=thisUnLoad();">
<form name="purchaseform" target="_parent" action="<%=contextPath%>/procure" method="POST">
<tsa:hidden name="nonce" value="<%= session.getAttribute(\"nonce\") %>"/>
<div id="filterFields" style="display:none;"></div>
<div id="hiddenFields" style="display:none;"></div>
<div id="audit" style="display:block;">
	<tsa:hidden name="auditTransactionId" value="${esapi:encodeForHTMLAttribute(auditTransactionId)}"/>
	<tsa:hidden name="auditFields" id="auditFields" value=""/>
	<tsa:hidden name="auditedFields" id="auditedFields" value=""/>
	<tsa:hidden name="auditTables" id="auditTables" value=""/>
</div>
<iframe id="getInfoFrame" name="getInfoFrame" src="<%=contextPath%>/system/processing.jsp" frameborder="0" marginheight="0" marginwidth="0" style="position: absolute; border: 1px solid #C0C0C0; display: none; visibility:hidden;"></iframe>
<tsa:hidden name="strEnableAuditTrail" value="${esapi:encodeForHTMLAttribute(enableAudit)}"/>
<tsa:hidden name="userDateFormat" value="<%=userDateFormat%>"/>
<% if (userTokenId != null) { %>
<tsa:hidden name="userTokenId" value="<%=userTokenId%>"/>
<% } %>

<tsa:hidden name="<%= TokenProcessor.TOKEN_KEY %>" value="<%= TokenProcessor.getInstance().generateToken(request) %>"/>
<tsa:hidden name="UserLog_status" value="<%=DocumentStatus.USERLOG_SUCCESSFUL_LOGOUT%>"/>
<tsa:hidden name="ReturnURL" value="<%=returnUrl%>"/>
<tsa:hidden name="epmc" value='<%= TokenProcessor.getInstance().generateToken(request, "")%>'/>
<%-- <tsa:hidden name="sfbm" value='<%= TokenProcessor.getInstance().generateToken(request, "")%>'/> --%>
<%@ include file="header_menu_options.jsp" %>
