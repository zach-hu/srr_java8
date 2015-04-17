<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	List	vendorRegisterList = (List) request.getAttribute("vendorRegisterList");
%>
<tsa:hidden name="vendorQualification" value="Y"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<%	if (vendorRegisterList != null && vendorRegisterList.size() > 0) {	%>
<tr><td align="center" class="boldMessage"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "haveChosenQualifySuppliers", "You have chosen to qualify the following suppliers")%>.  <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "newIDSupplierListed", "Please assign a new ID for each supplier listed")%>.<b></td></tr>
<tr><td><br><br></td></tr>
<tr>
	<td valign=top align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=95% class=browseRow>
<%		for (int i=0; i < vendorRegisterList.size(); i++)
			{
				VendorRegister vendorRegister = (VendorRegister) vendorRegisterList.get(i);%>
		<tr>
			<td width="15%" align="right" class="label"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "vendorId", "Supplier ID")%>&nbsp;</td>
			<td colspan="2">
				<input type="text" name="newVendorRegister_vendorId" value="" size="25">
				<tsa:hidden name="VendorRegister_vendorId" value="<%=vendorRegister.getComp_id().getVendorId()%>"/>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td width="55%" valign="top">
				<table>
				<tr><td><%=vendorRegister.getVendorName()%></td></tr>
<%			if ( !HiltonUtility.isEmpty(vendorRegister.getAddressLine2()) ) {	%>
				<tr><td><%=vendorRegister.getAddressLine2()%></td></tr>
<%			}
				if ( !HiltonUtility.isEmpty(vendorRegister.getAddressLine3()) ) {	%>
				<tr><td><%=vendorRegister.getAddressLine3()%></td></tr>
<%			}
				if ( !HiltonUtility.isEmpty(vendorRegister.getAddressLine4()) ) {	%>
				<tr><td><%=vendorRegister.getAddressLine4()%></td></tr>
<%			}
				if ( !HiltonUtility.isEmpty(vendorRegister.getAddressCityStateZip()) ) {	%>
				<tr><td><%=vendorRegister.getAddressCityStateZip()%></td></tr>
<%			}
				if ( !HiltonUtility.isEmpty(vendorRegister.getAddressCountry()) ) {	%>
				<tr><td><%=vendorRegister.getAddressCountry()%></td></tr>
<%			}	%>
				</table>
			</td>
			<td width="30%" valign="top">
				<table>
				<tr><td><%=vendorRegister.getContactDisplayName()%></td></tr>
<%			if ( !HiltonUtility.isEmpty(vendorRegister.getContactTitle()) ) {	%>
				<tr><td><%=vendorRegister.getContactTitle()%></td></tr>
<%			}
				if ( !HiltonUtility.isEmpty(vendorRegister.getContactPhoneNo()) ) {	%>
				<tr><td><%=vendorRegister.getContactPhoneNo()%></td></tr>
<%			}
				if ( !HiltonUtility.isEmpty(vendorRegister.getComp_id().getContactEmailAddr()) ) {	%>
				<tr><td><%=vendorRegister.getComp_id().getContactEmailAddr()%></td></tr>
<%			}	%>
				</table>
			</td>
		</tr>
<%			if ( i < vendorRegisterList.size() -1 ) {	%>
		<tr><td colspan="3"><hr class=browseHR></td></tr>
<%			}
			}	%>
		</table>
	</td>
</tr>
<%	}	%>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width="680px">
<tr>
	<td width=50% align=center><a href="javascript: qualifySuppliers(); void(0);"><img src="<%=contextPath%>/images/button_submit.gif" border=0 alt="Submit" class=button></a></td>
	<td width=50% align=center><a href="javascript: viewPrequalifiedSuppliers(); void(0);"><img src="<%=contextPath%>/images/button_cancel.gif" border=0 alt="Cancel" class=button></a></td>
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

	function qualifySuppliers()
	{
		var ids = document.all("newVendorRegister_vendorId");
		if (ids.length > 1)
		{
			for (var i=0; i < ids.length; i++)
			{
				if (ids(i).value.trim().length <= 0)
				{
					alert("A valid supplier id is required for each supplier listed!");
					return false;
				}
			}
		}
		else
		{
			if (ids.value.trim().length <= 0)
			{
				alert("Please enter a valid id for this supplier!");
				return false;
			}
		}

		doSubmit('/admin/supplier/qualification_confirmation.jsp', 'VendorRegisterQualifyList');
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