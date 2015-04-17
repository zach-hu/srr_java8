<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<tsa:hidden name="browsePage" value="browse/browse_subcommodity_tree_admin.jsp"/>

<%@ include file="/browse/browse_subcommodity_tree_form.jsp" %>

<br>

<table width=100% border=0>
<tr>
	<td align=center width=50%><a href="javascript: addCommodity();"><img class=button src="<%=contextPath%>/images/button_add.gif" border=0></a></td>
	<td align=center width=50%><a href="javascript: doSubmit('/admin/systemtables/system_tables.jsp','DoNothing');"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	
	frm = document.purchaseform;

	var rowSelect;
	var selectedData;
	var totalRows = <%=irows%>;
	
	if (totalRows <= 0) {
		document.getElementById("noRecords").style.visibility = "visible";
		document.getElementById("noRecords").style.display = "block";
	}
	
	function returnMe(x) {
		var newInputField = "<input type='hidden' name='SubCommodity_commodity' value='" + x + "'>";

		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/subcommodity.jsp', 'SubCommodityRetrieveById');
	}
	
	function addCommodity() {
		doSubmit('admin/systemtables/subcommodity.jsp','DoNothing');	
	}

	function filterMe() {
		if (!isEmpty(frm.commodityKeywords.value)) {
			setOriginalFilter("SubCommodity_description", "LIKE", frm.commodityKeywords.value);
		}
		if (!isEmpty(frm.commodityCode.value)) {
			setOriginalFilter("SubCommodity_commodity", "LIKE", frm.commodityCode.value);
		}
		frm.browseName.value = 'subcommodity';
		doSubmit('/browse/browse_subcommodity_admin.jsp', 'BrowseRetrieve');
	}

	function setFieldFocus() {
		// focus should already have been set to the last selected commodity... do not set to the first field
	}
	
// End Hide script -->
</SCRIPT>