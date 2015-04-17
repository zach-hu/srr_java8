<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="java.math.*" %>
<%
	List invPropertyList = (List) request.getAttribute("invPropertyList");
	String receiptRow = (String) request.getAttribute("receiptRow");
	String	icRc = "";

	if (invPropertyList != null && invPropertyList.size() > 0) {
		InvProperty invProperty = (InvProperty) invPropertyList.get(0);
		if (invProperty != null) {
			icRc = String.valueOf(invProperty.getIcRc());
		}
	}

	if (receiptRow == null) {	receiptRow = "0";	}
%>
<HTML>
<HEAD></HEAD>
<BODY></BODY>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers
	window.parent.focus();
//	window.parent.setInvPropertyIcRc('<%=icRc%>', <%=receiptRow%>);
//	window.parent.frm.ReceiptLine_duomUmCode.value = "aa" ;
	if (window.parent.openedFromPopup) {
		window.parent.hideArea('getInfoFrame');
	} else {
		window.top.hidePopWin();
	}
//-->
</SCRIPT>
</HTML>