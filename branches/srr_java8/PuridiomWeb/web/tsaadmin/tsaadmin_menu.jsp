<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<br>

<table border=0 cellpadding=3 cellspacing=0 width=680px>
<tr>
	<td align=center>
		<table border=0 cellpadding=3 cellspacing=0>
		<tr>
			<td colspan=3 align=center><a href="javascript: viewBrowseObjects(); void(0);"><b>VIEW BROWSE OBJECTS</b></a></td>
		</tr>
		<tr><td colspan=3><br><hr><br></td></tr>
		<tr>
			<td align=right><b>Organization Id:</b></td>
			<td><input type=text name="as_log_oid" value="" onchange="upperCase(this);"></td>
			<td><a href="javascript: getUserLog(); void(0);"><b>DISPLAY USER LOG</b></a></td>
		</tr>
		<tr><td colspan=3><br><hr><br></td></tr>
		<tr>
			<td align=right><b>Password:</b></td>
			<td><input type=text name="encryptedPassword" value=""></td>
			<td><input type=checkbox name="decryptCaseSensitive" value="Y"><b>Case Sensitive</b>&nbsp;<a href="javascript: decryptPassword(); void(0);"><b>DECRYPT PASSWORD</b></a></td>
		</tr>
		<tr><td colspan=3><br><hr><br></td></tr>
		<tr>
			<td align=right><b>Password:</b></td>
			<td><input type=text name="decryptedPassword" value=""></td>
			<td><input type=checkbox name="encryptCaseSensitive" value="Y"><b>Case Sensitive</b>&nbsp;<a href="javascript: encryptPassword(); void(0);"><b>ENCRYPT PASSWORD</b></a></td>
		</tr>
		<tr><td colspan=3><br><hr><br></td></tr>
		<tr>
			<td align=right><b>Config Password:</b></td>
			<td><input type=text name="plaintextConfigPassword" value=""></td>
			<td><a href="javascript: encryptConfigPassword(); void(0);"><b>ENCRYPT CONFIG PASSWORD</b></a></td>
		</tr>
		<tr><td colspan=3><br><hr><br></td></tr>
		<tr>
		  <td align="right"><b>Organization Id:</b></td>
		  <td><input type=text name="as_po_oid" value="" onchange="upperCase(this);"></td>
		</tr>
		<tr>
			<td align=right><b>IC_PO_HEADER:</b></td>
			<td><input type=text name="PoHeader_icPoHeader" value=""></td>
			<td><a href="javascript: orderPreview(); void(0);"><b>PREVIEW ORDER</b></a></td>
		</tr>
		<tr><td colspan=3><br><hr><br></td></tr>
		<tr>
			<td align="right"><b>Organization Id:</b></td>
			<td><input type=text name="as_property_oid" value="" onchange="upperCase(this);"></td>
			<td><a href="javascript: refreshProperties(); void(0);"><b>REFRESH PROPERTIES, RULES & LABELS</b></a></td>
		</tr>
		<tr><td colspan=3><br><hr><br></td></tr>
		<tr>
			<td align="right"><b>Organization Id:</b></td>
			<td><input type=text name="as_currency_oid" value="" onchange="upperCase(this);"></td>
			<td><a href="javascript: refreshCurrencies(); void(0);"><b>REFRESH CURRENCIES & COUNTRIES</b></a></td>
		</tr>
		<tr><td colspan=3><br><hr><br></td></tr>
		<tr>
			<td colspan=3 align=center><a href="javascript: refreshOrganizations(); void(0);"><b>REFRESH ORGANIZATIONS</b></a></td>
		</tr>
		<tr><td colspan=3><br><hr><br></td></tr>
		<tr>
			<td align="right"><b>Organization Id:</b></td>
			<td><input type=text name="as_extrule_oid" value="" onchange="upperCase(this);"></td>
			<td><a href="javascript: updateExtRules(); void(0);"><b>UPDATE EXTENDED APPROVAL RULES FROM XML</b></a></td>
		</tr>
		<tr><td colspan=3><br><hr><br></td></tr>
		<tr>
			<td align="right"><b>Organization Id:</b></td>
			<td><input type=text name="as_labels_oid" value="" onchange="upperCase(this);"></td>
			<!--td><a href="javascript: updateLabels(); void(0);"><b>UPDATE LABELS FROM labels.properties</b></a></td-->
			<td><a href="javascript: refreshLabels(); void(0);"><b>REFRESH LABELS FROM DATABASE</b></a></td>
		</tr>
		<tr><td colspan=3><br><hr><br></td></tr>
		</table>
	</td>
</tr>
</table>

<br><br>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;

	document.title = "TSA Admin Menu";

	function viewBrowseObjects() {
		frm.action = "<%=contextPath%>/tsaadmin/browse_objects.jsp";
		frm.submit();
	}

	function getUserProfileDisplay() {
		frm.organizationId.value = frm.as_oid.value;
		frm.action = "<%=contextPath%>/tsaadmin/user_profile_display.jsp";
		frm.submit();
	}

	function getUserLog() {
		frm.organizationId.value = frm.as_log_oid.value;
		doSubmit("/tsaadmin/user_log.jsp", "DoNothing");
//		frm.submit();
	}

	function decryptPassword() {
		if (!frm.decryptCaseSensitive.checked) {
			upperCase(frm.encryptedPassword);
		}
		frm.action = "<%=contextPath%>/tsaadmin/decrypt_password.jsp";
		frm.submit();
	}

	function encryptPassword() {
		if (!frm.encryptCaseSensitive.checked) {
			upperCase(frm.decryptedPassword);
		}
		frm.action = "<%=contextPath%>/tsaadmin/encrypt_password.jsp";
		frm.submit();
	}

	function encryptConfigPassword() {
		frm.action = "<%=contextPath%>/tsaadmin/encrypt_config_password.jsp";
		frm.submit();
	}

	function orderPreview() {
		frm.organizationId.value = frm.as_po_oid.value;
		popupParameters = "PoHeader_icPoHeader=" + frm.PoHeader_icPoHeader.value;
		doSubmitToPopup('tsaadmin/po_pdf_preview.jsp', 'PoRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
	}

	function openWindow (url, w, h) {
		if (browser == "Netscape") {
			if (w == undefined) { w = 'WIDTH=500'; }
			if (h == undefined) { h = 'HEIGHT=300'; }
			if (theFocus == undefined) { theFocus = 'lookup'; }
		}
		else {
			if (w == null) { w = 'WIDTH=500'; }
			if (h == null) { h = 'HEIGHT=300'; }
			if (theFocus == null) { theFocus = 'lookup'; }
		}
		var winspecs = w +","+ h +",resizable=1,scrollbars=1,location=0,top=0,left=0";

		lookup_window = window.open("<%=contextPath%>/system/popup_html.jsp", "lookup_window", winspecs);

		if (theFocus == 'main') {
			self.focus();
		}
		else {
			lookup_window.focus();
		}

		if (lookup_window.opener == null) lookup_window.opener = window;
		self.name = "main";
	}

	 function refreshProperties() {
		frm.organizationId.value = frm.as_property_oid.value;
		doSubmit('tsaadmin/reload_properties.jsp', 'DoNothing');
	}

	 function refreshOrganizations() {
		doSubmit('tsaadmin/reload_organizations.jsp', 'DoNothing');
	}

	 function refreshCurrencies() {
		frm.organizationId.value = frm.as_currency_oid.value;
		doSubmit('tsaadmin/reload_currencies.jsp', 'DoNothing');
	}

	function updateExtRules() {
		frm.organizationId.value = frm.as_extrule_oid.value;
		doSubmit('tsaadmin/reload_apprulesext.jsp', 'AppRulesExtUpdateFromXml');
	}

	function updateLabels() {
		if (confirm("This option will attempt to recreate the LABELS table in the database and should only be used for INITIAL loads.  Are you sure you wish to continue?")) {
			frm.organizationId.value = frm.as_labels_oid.value;
			doSubmit('tsaadmin/update_db_labels.jsp', 'LabelsPopulateFromPropertiesFile');
		}
	}

	function refreshLabels() {
		frm.organizationId.value = frm.as_labels_oid.value;
		doSubmit('tsaadmin/update_db_labels.jsp', 'LabelsRefresh');
	}

//-->
</script>