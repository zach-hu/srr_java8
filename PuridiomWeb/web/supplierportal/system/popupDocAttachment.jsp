<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header_popup.jsp" %>

<%
	String url = (String) request.getAttribute("urlFileDownload");
%>

<table border=0 cellspacing=0 cellpadding=0 width=100%>
<tr>
	<td width=100% align=center valign=top><br><br><b>If you can not view the selected File. please click <a href="<%= url %>">here</a></b></td>
</tr>
</table>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function thisLoadPopup() {
		window.location = '<%= url %>';
	}


// End Hide script -->
</SCRIPT>

