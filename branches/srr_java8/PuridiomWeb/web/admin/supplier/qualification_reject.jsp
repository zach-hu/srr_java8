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
<tr><td align="center" class="boldMessage"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rejectFollowingSupplier", "You have chosen to reject the following suppliers")%>.  <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reasonForEachSupplierListed", "Please assign a reason for each supplier listed")%>.<b></td></tr>
<tr><td><br><br></td></tr>
<tr>
	<td valign=top align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=90% class=browseRow>
<%		for (int i=0; i < vendorRegisterList.size(); i++)
			{
				VendorRegister vendorRegister = (VendorRegister) vendorRegisterList.get(i);%>
		<tr>
			<td width="10%">&nbsp;</td>
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
			<td width="35%" valign="top">
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
		<tr><td colspan="3"><br></td></tr>
		<tr>
			<td colspan="3" align="center">
				<table>
				<tr>
					<td align="right" valign="top" class="label"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reason", "Reason")%>&nbsp;</td>
					<td>
						<textarea name="VendorRegister_rejectionNotes" cols=60 rows=5 tabindex=4 onKeyDown="textCounter(this, 255);" onKeyUp="textCounter(this, 255);"></textarea>
						<tsa:hidden name="VendorRegister_vendorId" value="<%=vendorRegister.getComp_id().getVendorId()%>"/>
					</td>
				</tr>
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

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td width=50% align=center><a href="javascript: rejectSuppliers(); void(0);"><img src="<%=contextPath%>/images/button_reject.gif" border=0 alt="Reject" class=button></a></td>
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

	function rejectSuppliers()
	{
		var ids = document.all("VendorRegister_rejectionNotes");
		if (ids.length > 1)
		{
			for (var i=0; i < ids.length; i++)
			{
				if (ids(i).value.trim().length <= 0)
				{
					alert("Please enter the reason you are rejecting each supplier!");
					return false;
				}
			}
		}
		else
		{
			if (ids.value.trim().length <= 0)
			{
				alert("Please enter the reason you are rejecting this supplier!");
				return false;
			}
		}

		doSubmit('/admin/supplier/rejection_confirmation.jsp', 'VendorRegisterRejectList');
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