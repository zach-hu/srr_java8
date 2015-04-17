<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header_popup.jsp" %>
<table border=0 cellspacing=0 cellpadding=0 width=100%>
<tr>
	<td width=100% align=center valign=top><br><b>Processing... Please wait.</b><br><br><br><img src="<%=contextPath%>/supplierportal/images/processing.gif" border=1 width=200px height=15px></td>
</tr>
<tr><td id=requestParameters></td></tr>
</table>

<%@ include file="/supplierportal/system/footer.jsp" %>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	setParameters();
	submitPopup();

	function setParameters() {
		var params = opener.popupParameters;
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
		opener.popupParameters = "";
		document.getElementById("requestParameters").innerHTML = myHtml;
	}

	function submitPopup() {
		var handlerList = opener.popupHandler;
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
		frm.successPage.value = opener.popupUrl;
		frm.userId.value = opener.popupUserId;
		frm.organizationId.value = opener.popupOrganizationId;

		//set cursor to hourglass while the system is processing
		document.body.style.cursor = "wait";

		if (opener.popupAction != null) {
			frm.action = opener.popupAction;
			opener.popupAction = null;
		}
	
		frm.target = "_self"
		frm.submit();
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/supplierportal/system/prevent_caching.jsp" %>