<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header_popup.jsp" %>
<%@ include file="/supplierportal/browse/browse_form.jsp" %>

<%
	String	formField = (String) request.getAttribute("formField");
	String	fromPage = (String) request.getAttribute("fromPage");
	String	row = (String) request.getAttribute("index");
	if (fromPage == null)	{	fromPage = "";	}
	if (row == null)			{	row = "0";			}
%>

<table width=100% border=0>
<tr>
	<td align=center><br><a href="javascript: window.close();"><img class=button src="<%=contextPath%>/supplierportal/images/button_close.gif" border=0></a></td>
</tr>
</table>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	var row = "<%=row%>";

	function returnMe(x) {
		var fld = "<%=formField%>";
		selectedData = x;

		if (fld == "InvoiceLine_umCode")
		{
			var Ocolumns = opener.frm.elements.item(fld);
			if (Ocolumns.length==undefined)
			{
				opener.frm.elements(fld).value = x;
			}
			else
			{
				opener.frm.elements(fld)[row].value = x;
			}
		}
		else
		{
			opener.frm.elements(fld).value = selectedData;
			opener.frm.elements(fld).fireEvent("onchange");
		}

		returnOthers(fld);

		window.close();
	}

	function returnOthers(formField) {
		if (formField=="InvoiceHeader_shipToCode") {
			opener.frm.Address_addressLine1.value = frm.Address_addressLine1[rowSelect].value;
			opener.frm.Address_addressLine2.value = frm.Address_addressLine2[rowSelect].value;
			opener.frm.Address_addressLine3.value = frm.Address_addressLine3[rowSelect].value;
			opener.frm.Address_addressLine4.value = frm.Address_addressLine4[rowSelect].value;
			opener.frm.Address_city.value = frm.Address_city[rowSelect].value;
			opener.frm.Address_state.value = frm.Address_state[rowSelect].value;
			opener.frm.Address_postalCode.value = frm.Address_postalCode[rowSelect].value;
			opener.frm.Address_country.value = frm.Address_country[rowSelect].value;
		}
		 else if (formField == "InvoiceHeader_orderByName")
		{
			opener.frm.InvoiceHeader_orderByName.value = frm.UserProfile_displayName[rowSelect].value;
			opener.frm.InvoiceHeader_orderByEmail.value = frm.UserProfile_mailId[rowSelect].value;
			opener.frm.InvoiceHeader_orderByPhone.value = frm.UserProfile_phoneNumber[rowSelect].value;
		}
		else if (formField == "InvoiceLine_umCode")
		{
			opener.setUmFactor(frm.UnitOfMeasure_factor[rowSelect].value, row);
		}
		else if (formField == "temp_commodity_code")
		{
			opener.frm.temp_commodity_desc.value = frm.Commodity_description[rowSelect].value;
		}
	}

// End Hide script -->
</SCRIPT>


