<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/browse/browse_form.jsp" %>

<%
	String s_icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String s_rfqNumber = (String) request.getAttribute("RfqHeader_rfqNumber");
	String s_rfqAmendment = (String) request.getAttribute("RfqHeader_rfqAmendment");
	String s_rfqStatus = (String) request.getAttribute("RfqHeader_status");
	String s_fiscalYear = (String) request.getAttribute("RfqHeader_fiscalYear");
%>

<tsa:hidden name="browsePage" value="/browse/browse_rfq_vendor.jsp"/>
<tsa:hidden name="RfqVendor_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="${RfqHeader_rfqType}"/>
<tsa:hidden name="RfqHeader_fiscalYear" value="<%=s_fiscalYear%>"/>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formWidth%>>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: addSuppliers(); void(0);">Add</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: returnAbort(); void(0);">Return</a></div></td>
</tr>
</table>


<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

<%	if (sbOriginalFilterText.length() <= 0) { %>
	hideArea("filterTextDisplay");
	hideArea("resetOriginalButton");
<%	} %>

	var browser = browserCheck();
	var s_icRfqHeader = '<%=headerEncoder.encodeForJavaScript(s_icRfqHeader)%>';

	function addSuppliers()
	{
		var myCell = document.getElementById("hiddenFields");
		var checkboxes = document.all("c_checkbox");
		var ids = document.all("Contact_id_vendorId");
		var newInputField = "";
		var isAtLeastOneSelected = false;
		if (checkboxes.length > 1)
		{
			for (var i = 0; i < checkboxes.length; i++)
			{
				var cbox = checkboxes[i];
				if (cbox.checked == true)
				{
					var id = ids[i].value;
					newInputField =  newInputField + "<INPUT TYPE=\"HIDDEN\" NAME=\"RfqVendor_vendorId\" value=\"" + id + "\">";
					isAtLeastOneSelected = true;
				}
			}
		}
		else
		{
			var cbox = checkboxes;
			if (cbox.checked == true)
			{
				var id = ids.value;
				newInputField =  newInputField + "<INPUT TYPE=\"HIDDEN\" NAME=\"RfqVendor_vendorId\" value=\"" + id + "\">";
				isAtLeastOneSelected = true;
			}
		}

		if (isAtLeastOneSelected)
		{
			//myCell.innerHTML = newInputField;
			doSubmit('/rfq/rfq_suppliers.jsp', 'BrowseSetInputRequestValues;RfqVendorAddFromList;RfqVendorRetrieveByHeader');
		}
		else
		{
			alert("Please select at least one supplier for this Solicitation!");
		}
	}

	function returnAbort()
	{
		doSubmit('/rfq/rfq_suppliers.jsp', 'RfqRetrieve');
	}

	function browse(x)
	{
		frm.browseName.value = x;

		doSubmit('/browse/browse_rfq_vendor.jsp', 'BrowseRetrieve');
	}

	function browseFilter(x) {
		frm.browseName.value = x;

		doSubmit('/browse/browse_filter_rfqvendor.jsp', 'BrowseGetOptions');
	}

// End Hide script -->
</SCRIPT>