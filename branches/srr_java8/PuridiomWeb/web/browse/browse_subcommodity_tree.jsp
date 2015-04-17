<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>

<tsa:hidden name="browsePage" value="browse/browse_subcommodity_tree.jsp"/>
<tsa:hidden name="browseName" value="subcommodity"/>

<%@ include file="/browse/browse_subcommodity_tree_form.jsp" %>

<table width=100% border=0>
<tr>
	<td align=center><br><a href="javascript: window.top.hidePopWin();"><img class=button src="<%=contextPath%>/images/button_close.gif" border=0></a></td>
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
		var fld = "<%=formField%>";
		var openerNameElements = window.parent.frm.elements("as_commodityName");
		selectedData = x;
		
		window.parent.frm.elements(fld).value = selectedData;
		
		if (openerNameElements != undefined) {
			if (totalRows > 1) {
				if (openerNameElements.length > 1) {
					window.parent.frm.as_commodityName[window.parent.currentRow].value = frm.asSubCommodity_description[rowSelect].value;
				} else {
					window.parent.frm.elements("as_commodityName").value = frm.asSubCommodity_description[rowSelect].value;
				}
			} else {
				window.parent.frm.elements("as_commodityName").value = frm.asSubCommodity_description.value;
			}
		}
		window.parent.updateReqLineAccount();	
		window.top.hidePopWin();
	}

	function browse(x) {
		frm.browseName.value = x;

		doSubmit('/browse/browse_popup.jsp', 'BrowseRetrieve');
	}

	function filterMe() {
		if (!isEmpty(frm.commodityKeywords.value)) {
			setOriginalFilter("SubCommodity_description", "LIKE", frm.commodityKeywords.value);
		}
		if (!isEmpty(frm.commodityCode.value)) {
			setOriginalFilter("SubCommodity_commodity", "LIKE", frm.commodityCode.value);
		}
		frm.browseName.value = 'subcommodity';
		doSubmit('/browse/browse_commodity_popup.jsp', 'BrowseRetrieve');
	}
	
// End Hide script -->
</SCRIPT>