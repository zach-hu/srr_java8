<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ include file="/supplierportal/browse/browse_form.jsp" %>
<%
		String	docType = (String) request.getAttribute("docType");
		docType = HiltonUtility.ckNull(docType);
%>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
	<td width=100% align=center>
		<a href="javascript: doSubmit('/documents/std_documents_menu.jsp', 'DoNothing'); void(0);">
			<img src="<%=contextPath%>/supplierportal/images/button_return.gif" border=0 class=button>
		</a>
	</td>
</tr>
</table>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	
	hideArea("filterTextDisplay");
	hideArea("resetOption");
	hideArea("resetOriginalOption");
	hideArea("resetFilterOptions");
	
	var docType = "<%=docType%>";
	if (docType == "S") {
		document.all("browseTitle").innerText = "Standard Terms & Condition Documents";
	} else {
		document.all("browseTitle").innerText = "Other Solicitation Documents";
	}
	
// End Hide script -->
</SCRIPT>


