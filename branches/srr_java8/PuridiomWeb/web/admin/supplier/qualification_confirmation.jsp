<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	List	vendorList = (List) request.getAttribute("vendorList");
%>
<table width=680px cellpadding=0 cellspacing=0 border=0>
<%	if (vendorList != null && vendorList.size() > 0) {%>
<tr><td align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "suppliersQualified", "The following suppliers have been qualified")%>:<b></td></tr>
<tr><td><br><br></td></tr>
<%		for (int i=0; i < vendorList.size(); i++) {
				Vendor vendor = (Vendor) vendorList.get(i);%>
<tr><td align=center><%=vendor.getVendorId()%> - <%=vendor.getVendorName()%></td></tr>
<tr><td><br><br></td></tr>
<%		}
		}%>
<tr><td><br><br></td></tr>
<tr><td align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "returnLlistPre-QualifiedSuppliers", "Would you like to return to the list of Pre-Qualified Suppliers")%>?</td></tr>
<tr><td><br><br></td></tr>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td width=50% align=center><a href="javascript: viewPrequalifiedSuppliers(); void(0);"><img src="<%=contextPath%>/images/button_yes.gif" border=0 alt="yes" class=button></a></td>
			<td width=50% align=center><a href="javascript: cancelMe(); void(0);"><img src="<%=contextPath%>/images/button_no.gif" border=0 alt="no" class=button></a></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menubarSpacer");
	}

	function viewPrequalifiedSuppliers() {
		frm.browseName.value = "vendor-register";
		doSubmit("admin/supplier/prequalify_suppliers.jsp", "BrowseRetrieve");
	}

	function cancelMe() {
		doSubmit('/menu/main_menu.jsp', 'DoNothing');
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>