<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/browse/browse_form.jsp" %>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String usePuridiomSupplierID = propertiesManager.getProperty("VENDOR-REGISTRATION", "USE PURIDIOM SUPPLIER ID", "Y");
	String acctingNotification = propertiesManager.getProperty("VENDOR-REGISTRATION", "ACCOUNTING NOTIFICATION", "N");
%>

<tsa:hidden name="browsePage" value="/admin/supplier/prequalify_suppliers.jsp"/>
<tsa:hidden name="vendorQualification" value="Y"/>
<tsa:hidden name="returnQualification" value="Y"/>

<table border=0 width=680px>
<tr>
<%	if (acctingNotification.equalsIgnoreCase("Y")) {	%>
	<td width="25%" align="center" valign="bottom"><a href="javascript: emailAccounting(); void(0);"><img src="<%=contextPath%>/images/email_new.gif" border=0 alt="Email Accounting"></a></td>
<%	}	%>
	<td width="25%" align="center" valign="bottom"><a href="javascript: qualifySuppliers(); void(0);"><img src="<%=contextPath%>/images/checkmark_orange.gif" border=0 alt="Accept Supplier"></a></td>
	<td width="25%" align="center" valign="bottom"><a href="javascript: rejectSuppliers(); void(0);"><img src="<%=contextPath%>/images/doc_reject.gif" border=0 alt="Reject Supplier"></a></td>
	<td width="25%" align="center" valign="bottom"><a href="javascript: cancelMe(); void(0);"><img src="<%=contextPath%>/images/pwac_sm.gif" border=0 alt="Return to Main Menu"></a></td>
</tr>
<tr>
<%	if (acctingNotification.equalsIgnoreCase("Y")) {	%>
	<td width="25%" align="center" class="boldMessage"><a style="text-decoration:none; color:#7777ff;" href="javascript: emailAccounting(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "emailAccounting", "Email Accounting")%></a></td>
<%	}	%>
	<td width="25%" align="center" class="boldMessage"><a style="text-decoration:none; color:#7777ff;" href="javascript: qualifySuppliers(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "acceptSupplier", "Accept Supplier")%></a></td>
	<td width="25%" align="center" class="boldMessage"><a style="text-decoration:none; color:#7777ff;" href="javascript: rejectSuppliers(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rejectSupplier", "Reject Supplier")%></a></td>
	<td width="25%" align="center" class="boldMessage"><a style="text-decoration:none; color:#7777ff;" href="javascript: cancelMe(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "returnMainMenu", "Return to Main Menu")%></a></td>
</tr>
</table>



<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	hideArea("filterTextDisplay");
	hideArea("resetOption");
	hideArea("resetOriginalOption");

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menubarSpacer");
	}

	function qualifySuppliers() {
		var usePuridiomID = "<%=usePuridiomSupplierID%>";

		if (isSelected()) {
			if (usePuridiomID == "Y") {
				doSubmit('/admin/supplier/qualification_confirmation.jsp', 'BrowseSetInputRequestValues;VendorRegisterQualifyList');
			} else {
				doSubmit('/admin/supplier/qualification_assignid.jsp', 'BrowseSetInputRequestValues;VendorRegisterRetrieveList');
			}
		}
		else {
			alert("You have not selected any vendors to qualify!");
		}
	}

	function rejectSuppliers() {
		if (isSelected()) {
			doSubmit('/admin/supplier/qualification_reject.jsp', 'BrowseSetInputRequestValues;VendorRegisterRetrieveList');
		}
		else {
			alert("You have not selected any vendors to reject!");
		}
	}

	function emailAccounting() {
		if (isSelected()) {
			doSubmit('/admin/supplier/emailaccting_confirmation.jsp', 'BrowseSetInputRequestValues;VendorRegisterEmailList');
		}
		else {
			alert("You have not selected any vendors!");
		}
	}

	function cancelMe()
	{
		doSubmit('/menu/main_menu.jsp', 'DoNothing');
	}

	function viewPrequalifiedVendor(vendorId, emailAddr) {
		 var newInputField = "<input type='hidden' name='VendorRegister_contactEmailAddr' value='" + emailAddr + "'>";
		setHiddenFields(newInputField);
<%	if (oid.equals("BSC04P")) {%>
		doSubmit('/admin/supplier/bsc_prequalification.jsp', 'VendorRegisterRetrieveByEmail;VendorOptionsRetrieve');
<%	} else {%>
		doSubmit('/admin/supplier/prequalification.jsp', 'VendorRegisterRetrieveByEmail;VendorOptionsRetrieve');
<%	}%>
	}

	function isSelected()
	{
		var isSelected = false;

		if (rowCount > 1) {
			for (i = 0; i < rowCount; i++) {
				if (frm.c_checkbox[i] && frm.Input_checkbox[i]) {
					if (frm.c_checkbox[i].checked) {
						isSelected = true;
					}
				}
			}
		} else {
			if (frm.c_checkbox && frm.Input_checkbox) {
				if (frm.c_checkbox.checked) {
					isSelected = true;
				}
			}
		}
		return isSelected;
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>