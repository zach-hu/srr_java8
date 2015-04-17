<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>

<tsa:hidden name="browsePage" value="/supplierportal/browse/browse_commodity_tree.jsp"/>
<tsa:hidden name="browseName" value="commodity"/>

<%@ include file="/supplierportal/browse/browse_nigp_tree_form.jsp" %>

<tsa:hidden name="index" value="<%=row%>"/>

<table width=100% border=0>
<tr>
	<td align=center><br><a href="javascript: window.top.hidePopWin();"><img class=button src="<%=contextPath%>/supplierportal/images/button_close.gif" border=0></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

//	frm = document.purchaseform;

	var rowSelect;
	var selectedData;
	var totalRows = <%=irows%>;
	var row = "<%=row%>";

	if (totalRows <= 0) {
		document.getElementById("noRecords").style.visibility = "visible";
		document.getElementById("noRecords").style.display = "block";
	}

	function returnMe(x) {
		var fld = "<%=formField%>";
		selectedData = x;

		var Ocolumns = opener.frm.elements.item(fld);
		if (Ocolumns.length==undefined) {
			opener.frm.elements[fld].value = selectedData;
		} else {
			opener.frm.elements(fld)[row].value = x;
		}
		if (returnOthers(fld)) {
			doSubmit('/supplierportal/system/close_window.jsp', 'BrowseCleanupByBrowseId');
		}
	}

	function returnOthers(formField) {
		if (opener.frm.elements("as_commodityName") != undefined) {
			var Ocolumns = opener.frm.elements.item("as_commodityName");
			if (Ocolumns.length==undefined) {
				opener.frm.elements["as_commodityName"].value = frm.asCommodity_description[rowSelect].value;
			} else {
				opener.frm.elements("as_commodityName")[row].value = frm.asCommodity_description[rowSelect].value;
			}
//			opener.frm.elements("as_commodityName").value = frm.Commodity_description[rowSelect].value;
		}

		if (opener.frm.elements("RequisitionLine_udf10Code") != undefined) {
			var Ocolumns = opener.frm.elements.item("RequisitionLine_udf10Code");
				if (Ocolumns.length==undefined) {
					opener.frm.elements["RequisitionLine_udf10Code"].value = frm.asCommodity_type[rowSelect].value;
	
				} else {
					opener.frm.elements["RequisitionLine_udf10Code"].value = frm.asCommodity_type[rowSelect].value;
	
				}
		}

		if (formField.indexOf("_commodityCode") > 0) {
			if (opener.frm.as_authbyName) {
				opener.frm.as_authbyName.value = frm.UserProfile_displayName[rowSelect].value;
			}
		} else if (formField == "temp_commodity_code") {
			opener.frm.temp_commodity_desc.value = frm.asCommodity_description[rowSelect].value;
			opener.setCommodityCode();
		}
		return true;
	}

	function browse(x) {
		frm.browseName.value = x;

		doSubmit('/browse/browse_popup.jsp', 'BrowseRetrieve');
	}

	function filterMe() {
		if (!isEmpty(frm.commodityKeywords.value)) {
			setOriginalFilter("Commodity_description", "LIKE", frm.commodityKeywords.value);
		}
		if (!isEmpty(frm.commodityCode.value)) {
			setOriginalFilter("Commodity_commodity", "LIKE", frm.commodityCode.value);
		}
		frm.browseName.value = 'commodity';
		doSubmit('/browse/browse_commodity_popup.jsp', 'BrowseRetrieve');
	}

// End Hide script -->
</SCRIPT>