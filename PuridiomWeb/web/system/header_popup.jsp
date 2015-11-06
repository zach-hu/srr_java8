<%@ include file="/system/context_path.jsp" %>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.organization.OrganizationManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.common.utility.TokenProcessor" %>
<%@ page import="com.tsa.puridiom.usermanager.*" %>
<%@ page import="com.tsagate.foundation.utility.Dates" %>
<%@ page import="com.tsagate.html.*" %>
<%@ page import="com.tsagate.properties.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%
	Encoder headerEncoder = DefaultEncoder.getInstance();
	String	uid = (String) request.getAttribute("userId");
	String	oid = (String) request.getAttribute("organizationId");
	String language = (String)request.getAttribute("language");
	String view = HiltonUtility.ckNull((String) request.getAttribute("view"));
	UserProfile	user = UserManager.getInstance().getUser(oid,uid);
	UserRole	role = UserManager.getInstance().getUserRole(oid,uid);
	String userTimeZone = user.getTimeZone();
	/*if(HiltonUtility.isEmpty(userTimeZone)){
		userTimeZone = PropertiesManager.getInstance(oid).getProperty("USER DEFAULTS", "TIMEZONE", "EST"); 
	}*/
	String userDateFormat = user.getDateFormat();
	Date 	d_today = Dates.getCurrentDate(userTimeZone);
	String	formWidth = "980px";
	String	formEntryWidth = "880px";
	String	module = (String) request.getAttribute("module");
	String	moduleType = (String) request.getAttribute("moduleType");
	boolean isXpress = false ;

	if (requestURLPath.indexOf("puridiomx") >= 0 || (requestURLPath.indexOf("localhost") >= 0 && oid.startsWith("PX"))) {
		isXpress = true;
	}
	if (HiltonUtility.isEmpty(oid) && isXpress) {
		oid = "PURIDIOMX";
	}

	if (uid == null) {
		uid = "";
	}
	if (oid == null) {
		oid = "";
	}
	if (language == null) {
		language = "";
	}
	if (HiltonUtility.isEmpty(userDateFormat)) {
		userDateFormat = PropertiesManager.getInstance(oid).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
	}
	if (module == null) {
		module = "";
	}
	if (moduleType == null) {
		moduleType = "";
	}
	pageContext.setAttribute("oid", oid);
	pageContext.setAttribute("module", module);
	pageContext.setAttribute("moduleType", moduleType);
	pageContext.setAttribute("language", language);
// 	String  epmc = HiltonUtility.ckNull((String) request.getAttribute("epmc"));
%>
<HTML>
<HEAD>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<META HTTP-EQUIV="Cache-Control" content="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="Sat, 11 Sep 1971 12:00:00 GMT">
	<META NAME="Description" Content="<%=contextPath%>/system/header_popup.jsp">
	<script type="text/javascript" src="<%=contextPath%>/scripts/jquery-1.11.0.min.js"></script>
	<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/puridiom.js"></SCRIPT>
	<script language='Javascript1.2' src="<%=contextPath%>/scripts/filter.js"></script>
	<SCRIPT LANGUAGE="JavaScript" SRC="<%=contextPath%>/scripts/dragiframe.js"></SCRIPT>
	
	<TITLE>Puridiom, Enabling Self-Service Procurement</TITLE>
<%@ include file="/system/stylesheet_link.jsp" %>
</HEAD>

<body marginwidth=0 marginheight=0 topmargin=0 leftmargin=0 width=680px onload="thisLoadPopup();" onunload="thisUnLoadPopup();">
<form name="purchaseform" target="_parent" action="<%=contextPath%>/procure" method="POST">
<tsa:hidden name="nonce" value="<%=session.getAttribute(\"nonce\") %>"/>
<tsa:hidden name="handler" value=""/>
<tsa:hidden name="successPage" value=""/>
<tsa:hidden name="failurePage" value="system/error_popup.jsp"/>
<tsa:hidden name="userId" value="<%=headerEncoder.encodeForHTMLAttribute(uid)%>"/>
<tsa:hidden name="mailId" value="${esapi:encodeForHTMLAttribute(mailId)}"/>
<tsa:hidden name="organizationId" value="<%=headerEncoder.encodeForHTMLAttribute(oid)%>"/>
<tsa:hidden name="userDateFormat" value="<%=userDateFormat%>"/>
<tsa:hidden name="<%= TokenProcessor.TOKEN_KEY %>" value="<%= TokenProcessor.getInstance().generateToken(request) %>"/>
<tsa:hidden name="language" value="${language}"/>
<tsa:hidden name="epmc" value='<%= TokenProcessor.getInstance().generateToken(request, "")%>'/>

<div id="filterFields" style="display:none;"></div>
<div id="hiddenFields" style="display:none;"></div>

<SCRIPT value=JavaScript>
<!-- Hide script

	var myPage = "";


function doSubmitToPopup (url, handler, w, h) {
  popupUrl = url;
  popupHandler = handler;
  popupUserId = frm.userId.value;
  popupMailId = frm.mailId.value;
  popupOrganizationId = frm.organizationId.value;
  openedFromPopup = true;

  w = w.toLowerCase().replace("width=","") ;
  w = w.toLowerCase().replace("px","") ;
  h = h.toLowerCase().replace("height=","") ;
  h = h.toLowerCase().replace("px","") ;

  w = parseInt(w) ;
  h = parseInt(h) ;

  if( typeof( window.innerWidth ) == 'number' ) {
    //Non-IE
    wWidth = window.innerWidth;
    wHeight = window.innerHeight;
  } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
    //IE 6+ in 'standards compliant mode'
    wWidth = document.documentElement.clientWidth;
    wHeight = document.documentElement.clientHeight;
  } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
    //IE 4 compatible
    wWidth = document.body.clientWidth;
    wHeight = document.body.clientHeight;
  }

  // Make it fit
  if (w > wWidth) {
  	w = wWidth - 10
  }
  if (h > wHeight) {
  	h = wHeight - 10
  }

  displayArea('getInfoFrame');
  document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
  document.getElementById('getInfoFrame').style.width = w;
  document.getElementById('getInfoFrame').style.height = h;
}

// End Hide script -->
</SCRIPT>
