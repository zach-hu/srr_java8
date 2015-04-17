<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>

<br>

<table border=0 cellspacing=0 cellpadding=0 width=100%>
<tr>
	<td width=100% height=400px align=center valign=top><b>Loading system setup information... Please wait.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	submitPopup();

	function thisLoad() {
		return;
	}

	function submitPopup() {
		var handler = window.parent.popupHandler;

		if (handler == null) { handler = "DoNothing"; }

		frm.handler.value = handler + "Handler";
		frm.successPage.value = window.parent.popupUrl;

		//set cursor to hourglass while the system is processing
		document.body.style.cursor = "wait";
		frm.target = "_self"
		frm.submit();
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>