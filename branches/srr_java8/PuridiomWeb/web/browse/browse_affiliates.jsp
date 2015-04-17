<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/browse/browse_form.jsp" %>

<tsa:hidden name="browsePage" value="/browse/browse_affiliates.jsp"/>
<tsa:hidden name="Vendor_vendorId" value="${VendorAffiliate_vendorId}"/>
<tsa:hidden name="VendorAffiliate_vendorId" value="${VendorAffiliate_vendorId}"/>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
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
	var s_vendorId = '${esapi:encodeForJavaScript(VendorAffiliate_vendorId)}';

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
					newInputField =  newInputField + "<INPUT TYPE=\"HIDDEN\" NAME=\"VendorAffiliate_affiliateId\" value=\"" + id + "\">";
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
				newInputField =  newInputField + "<INPUT TYPE=\"HIDDEN\" NAME=\"VendorAffiliate_affiliateId\" value=\"" + id + "\">";
				isAtLeastOneSelected = true;
			}
		}
		if (isAtLeastOneSelected)
		{
			myCell.innerHTML = newInputField;
			doSubmit('/admin/supplier/supplier_affiliations.jsp', 'BrowseSetInputRequestValues;VendorAffiliateAddFromList;VendorAffiliateRetrieveByVendorId;VendorRetrieveById');
		}
		else
		{
			alert("Please select at least one affiliate for this supplier!");
		}
	}

	function returnAbort()
	{
		doSubmit('/admin/supplier/supplier_affiliations.jsp', 'VendorAffiliateRetrieveByVendorId;VendorRetrieveById');
	}

	function browse(x)
	{
		frm.browseName.value = x;

		doSubmit('/browse/browse_affiliate.jsp', 'BrowseRetrieve');
	}

	function browseFilter(x) {
		frm.browseName.value = x;

		doSubmit('/browse/browse_filter_affiliate.jsp', 'BrowseGetOptions');
	}

// End Hide script -->
</SCRIPT>