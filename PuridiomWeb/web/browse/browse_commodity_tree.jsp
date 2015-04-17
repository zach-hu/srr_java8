<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>

<tsa:hidden name="browsePage" value="browse/browse_commodity_tree.jsp"/>
<tsa:hidden name="browseName" value="commodity"/>

<%@ include file="/browse/browse_nigp_tree_form.jsp" %>

<tsa:hidden name="index" value="<%=row%>"/>

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
	var row = "<%=row%>";

	if (totalRows <= 0) {
		document.getElementById("noRecords").style.visibility = "visible";
		document.getElementById("noRecords").style.display = "block";
	}

	function returnMe(x) {
		var fld = "<%=formField%>";
		selectedData = x;

		var Ocolumns = window.parent.frm.elements.item(fld);
		if (Ocolumns.length==undefined) {
			window.parent.frm.elements[fld].value = selectedData;
		} else {
			window.parent.frm.elements(fld)[row].value = x;
		}
		returnOthers(fld);
		if (window.parent.updateReqLineAccount) {
			window.parent.updateReqLineAccount();
		}
		doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');
	}

	function returnOthers(formField) {
		if (window.parent.frm.elements("as_commodityName") != undefined) {
			var Ocolumns = window.parent.frm.elements.item("as_commodityName");
			if (Ocolumns.length==undefined) {
				window.parent.frm.elements["as_commodityName"].value = frm.asCommodity_description[rowSelect].value;
			} else {
				window.parent.frm.elements("as_commodityName")[row].value = frm.asCommodity_description[rowSelect].value;
			}
//			window.parent.frm.elements("as_commodityName").value = frm.Commodity_description[rowSelect].value;
		}

		if (window.parent.frm.elements("RequisitionLine_udf10Code") != undefined) {
			var Ocolumns = window.parent.frm.elements.item("RequisitionLine_udf10Code");
				if (Ocolumns.length==undefined) {
					window.parent.frm.elements["RequisitionLine_udf10Code"].value = frm.asCommodity_type[rowSelect].value;
	
				} else {
					window.parent.frm.elements["RequisitionLine_udf10Code"].value = frm.asCommodity_type[rowSelect].value;
	
				}
		}

		if (formField.indexOf("_commodityCode") > 0) {
			if (window.parent.frm.as_authbyName) {
				window.parent.frm.as_authbyName.value = frm.UserProfile_displayName[rowSelect].value;
			}
		}
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