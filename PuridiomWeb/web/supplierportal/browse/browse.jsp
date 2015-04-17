<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ include file="/supplierportal/browse/browse_form.jsp" %>

<% if (browseName.indexOf("rfq-bidboard-posts") >= 0) {%>
<table border=0 width=680px>
	<tr>
	<td align=center>
		<a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing');  void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_cancel.gif" border=0></a>
	</td>
	</tr>
</table>
<%	}%>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	function returnMe(code){
		var title = '<%=browseObject.getTitle()%>';
		var myCell = document.getElementById("hiddenFields");

		if ((title.indexOf("Currency")) >= 0) {
			myCell.innerHTML = "";
			var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"CurrCode_currencyCode\" value=\"" + code + "\" >";
			myCell.innerHTML = newInputField;
			doSubmit('/admin/systemtables/currcode_details.jsp', 'CurrCodeRetrieveById');
		}
		else {
			myCell.innerHTML = "";
			var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"StdTable_tableType\" value=\"" + tableType + "\" ><INPUT TYPE=\"HIDDEN\" NAME=\"StdTable_tableKey\" value=\"" + code + "\" >";
			myCell.innerHTML = newInputField;
			doSubmit('/admin/systemtables/systable_record_details.jsp', 'StdTableRetrieveBy');
		}
	}

	function viewPo(ic, type) {
		var myCell = document.getElementById("hiddenFields");
		myCell.innerHTML = "";
		var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";

		myCell.innerHTML = newInputField;
		doSubmit('/orders/po_review.jsp','PoRetrieve');
	}

// End Hide script -->
</SCRIPT>


