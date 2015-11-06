<%@ include file="/system/context_path.jsp" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ page import="com.tsa.puridiom.common.utility.TokenProcessor" %>
<HTML>
<HEAD>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<META HTTP-EQUIV="Cache-Control" content="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="Sat, 11 Sep 1971 12:00:00 GMT">
	<META NAME="Description" Content="/system/popup.html">
	<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/puridiom.js"></SCRIPT>
	<TITLE>Puridiom</TITLE>
	<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=contextPath%>/system/styles/orange.css">
</HEAD>

<body marginwidth=0 marginheight=0 topmargin=0 leftmargin=0 width=680px onload="thisLoadPopup();">
<form name="purchaseform" target="_parent" action="<%=contextPath%>/procure" method="POST">
<tsa:hidden name="handler" value=""/>
<tsa:hidden name="successPage" value=""/>
<tsa:hidden name="failurePage" value="system/error_popup.jsp"/>
<tsa:hidden name="userId" value=""/>
<tsa:hidden name="mailId" value=""/>
<tsa:hidden name="<%= TokenProcessor.TOKEN_KEY %>" value="<%= TokenProcessor.getInstance().generateToken(request) %>"/>
<tsa:hidden name="epmc" value='<%= TokenProcessor.getInstance().generateToken(request, "")%>'/>
<tsa:hidden name="organizationId" value=""/>

<table border=0 cellspacing=0 cellpadding=0 width=100%>
<tr>
	<td width=100% align=center valign=top><br><b>Processing... Please wait.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td>
</tr>
<tr><td id=requestParameters></td></tr>
</table>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	setParameters();
	submitPopup();

	function setParameters() {
		var params = parent.popupParameters;
		var myHtml = "";

		if (params != null || params.length > 0) {
			var sepInd = params.indexOf(";");
			var keyInd = params.indexOf("=");

			while (sepInd > 0) {
				key = params.substring(0, keyInd);
				val = params.substring(keyInd + 1, sepInd);

				myHtml = myHtml + "<input type=hidden name=" + '"' + key + '"' + " value=" + '"' + val + '"' + ">";

				params = params.substring(sepInd + 1, params.length);

				sepInd = params.indexOf(";");
				keyInd = params.indexOf("=");
			}

			if (keyInd > 0) {
				key = params.substring(0, keyInd);
				val = params.substring(keyInd + 1, params.length);

				myHtml = myHtml + "<input type=hidden name=" + '"' + key + '"' + " value=" + '"' + val + '"' + ">";
			}
		}
		parent.popupParameters = "";
		document.getElementById("requestParameters").innerHTML = myHtml;
	}

	function submitPopup() {
		var handlerList = parent.popupHandler;
		var handlerValue = "";

		if (handlerList == null) { handlerList = "DoNothing"; }

		if (handlerList.indexOf(";") > 0) {
			if (handlerList.lastIndexOf(";") != handlerList.length) {
				handlerList = handlerList + ";";
			}
			while (handlerList.length > 0) {
				var handlerName = handlerList.substring(0, handlerList.indexOf(";"));
				if (handlerList.indexOf(";") < handlerList.length) {
					handlerList = handlerList.substring(handlerList.indexOf(";") + 1);
				}
				else {
					handlerList = "";
				}
				handlerName = handlerName + "Handler";
				handlerValue = handlerValue + handlerName + ";";
			}
		}
		else {
			handlerValue = handlerList + "Handler;";
		}

		frm.handler.value = handlerValue;
		frm.successPage.value = parent.popupUrl;
		frm.userId.value = parent.popupUserId;
		frm.mailId.value = parent.popupMailId;
		frm.organizationId.value = parent.popupOrganizationId;

		//set cursor to hourglass while the system is processing
		document.body.style.cursor = "wait";

		if (parent.popupAction != null) {
			frm.action = parent.popupAction;
			parent.popupAction = null;
		}
		frm.target = "_self"
		frm.submit();
	}

// end hiding contents -->
</SCRIPT>