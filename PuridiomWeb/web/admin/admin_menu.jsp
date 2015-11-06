<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.browse.BrowseManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<%
	BrowseManager.getInstance().cleanupBrowsesBySession(oid, sid);

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	alertView = propertiesManager.getProperty("ALERT_CONFIGURATION", "ENABLED", "N");
	String	validationRulesAdmin = propertiesManager.getProperty("VALIDATIONRULESADMIN", "ENABLED", "N");

%>
<tsa:hidden name="process" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "systemAdministrationMenu", "System Administration Menu")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width="100%">
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=680px align=center>
		<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%> align=center>

<%	if (role.getAccessRights("APPRVLS") > 0) { %>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
				<a href="javascript: doSubmit('admin/approvals/approvals_menu.jsp', 'DoNothing'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvalRulesSetup", "Approval Rules Setup")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-approvalRulesSetup", "Specify approval setup options and assign approval user rule definitions, including authorization amounts.")%></td>
		</tr>
<%	}
	if (role.getAccessRights("USRLOG") > 0) { %>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
				<a href="javascript: doSubmit('admin/audittrail/audittrail_menu.jsp', 'DoNothing'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "auditTrailManagement", "Audit Trail Management")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-auditTrailManagement", "View the Audit Trail Management.")%></td>
		</tr>
<%	}
	if (role.getAccessRights("CAT") > 0) { %>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
				<a href="javascript: browseFilter('catalog'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "catalogManagement", "Catalog Management")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-catalogManagement", "Set up and manage catalogs and catalog items.")%></td>
		</tr>
<%	}
	if (role.getAccessRights("COMPINFO") > 0 && ! isXpress ) { %>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=30% class=formType>
				<a href="javascript: doSubmit('admin/company_information.jsp', 'DoNothing'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "companyNameAddress", "Company Name and Address")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-companyNameAddress", "Set up or change your company name and address information.")%></td>
		</tr>
<%	}
	if (role.getAccessRights("LABELS") > 0 ) { %>
	<tr>
	<td align=right height=32px width=10%>
	<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
	<td nowrap width=25% class=formType>
	<a href="javascript: labelsManagement(); void(0);" class="formType"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labelsManagement", "Labels Management")%></a></td>
	<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-labelsManagement", "Change labels, hide fields, indicate required fields, etc.")%></td>
	</tr>
<%	}
	if (role.getAccessRights("RULES") > 0 ) { %>
	<tr>
		<td align=right height=32px width=10%>
			<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
		<td nowrap width=25% class=formType>
			<a href="javascript: rulesManagement(); void(0);" class="formType"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rulesManagement", "Rules Management")%></a></td>
		<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-rulesManagement", "Rules Options Menu.")%></td>
	</tr>
<%	}
	if (role.getAccessRights("BBDOCS") > 0) { %>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
				<a href="javascript: doSubmit('/admin/documents/std_documents.jsp?origin=admin','StdDocumentRetrieveAll'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "standardBidboardDocuments", "Standard Bidboard Documents")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-standardBidboardDocuments", "Manage standard documents for the BidBoard.")%></td>
		</tr>
<%	}
	if (role.getAccessRights("VEND") > 0) { %>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
				<a href="javascript: browseFilter('supplier_mgt'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "supplierManagement", "Supplier Management")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-supplierManagement", "Manage the system's suppliers.")%></td>
		</tr>
<%	}
	if (role.getAccessRights("EMAILS") > 0) { %>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
				<a href="javascript: doSubmit('admin/system_alerts.jsp', 'SystemAlertsLoad'); void(0);" class=formType>Email and Alerts Administration</a></td>
			<td>Configure the frequency and send to of System Emails and Alerts.</td>
		</tr>
<%	}
	if (role.getAccessRights("SYSDFLTS") > 0) { %>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
				<a href="javascript: doSubmit('admin/system_defaults.jsp', 'DoNothing'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "systemdefaults", "System Defaults")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "admin-systemDefaultsDesc", "Set supplier, requisition, solicitation, and purchase order defaults.")%></td>
		</tr>
<%	}
	if (role.getAccessRights("SYSSETUP") > 0) { %>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
				<a href="javascript: doSubmit('admin/system_mappings_document_menu.jsp', 'DoNothing'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "systemMappings", "System Mappings")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "admin-systemMappingsDesc", "Set up system mappings from user profiles, requisitions, orders, etc..")%></td>
		</tr>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
				<a href="javascript: doSubmit('admin/system_setup.jsp', 'DoNothing;AutoGenRetrieveByGenYear'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "systemSetupOptions", "System Setup Options")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "admin-systemSetupDesc", "Set up system directories, report options, login options, auto numbering and more.")%></td>
		</tr>
<%	}
	if (role.getAccessRights("TABLES") > 0) { %>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
				<a href="javascript: doSubmit('admin/systemtables/system_tables.jsp', 'DoNothing'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "systemTables", "System Tables")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-systemTables", "Set up system tables including udfs, standard comments and accounts and more.")%></td>
		</tr>
<%	}
	if (role.getAccessRights("STDFORM") > 0)
	{ %>
	<tr>
		<td align=right height=32px width=10%>
			<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
		<td nowrap width="30%" class="formType">
			<a href="javascript: doSubmit('/admin/documents/std_attachments.jsp','StdAttachmentRetrieveAll'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "standardDocuments", "Standard Documents")%></a></td>
		<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-standardDocuments", "Set up Standard Documents.")%></td>
	</tr>
<%	}
	if (role.getAccessRights("USRLOG") > 0) { %>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
				<a href="javascript: doSubmit('admin/user/user_log.jsp', 'DoNothing'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userLogMaintenance", "User Log Maintenance")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-userLogMaintenance", "View the user log and statuses.")%></td>
		</tr>
<%	}
	if (role.getAccessRights("USRPRFL") > 0) { %>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
				<a href="javascript: userProfileAdmin(); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userProfileAdministration", "Employee Administration")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-userProfileAdministration", "Add employees.  Register requisitioners, buyers, and approvers.  Change existing profile information.")%></td>
		</tr>
<%	}
	if (role.getAccessRights("USRROLE") > 0) { %>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
				<a href="javascript: document.purchaseform.process.value = 'GROUPADMIN'; browseFilter('admin-securitygroup'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "groupProfileAdministration", "Group Profile Administration")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-groupProfileAdministration", "Add a new user role, change security access for existing roles.")%></td>
		</tr>
<%	}
	if (role.getAccessRights("BDG") > 0 && PropertiesManager.getInstance(oid).isModuleActive("ACCOUNT BUDGET")) { %>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
				<a href="javascript: doSubmit('admin/budget/budget_menu.jsp','DoNothing'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budgetManagement", "Budget Management")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-budgetManagement", "Manage the budget options and the budget items.")%></td>
		</tr>
<%	}

	if (role.getAccessRights("VALIDATIONRULES") > 0 ) { %>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
				<a href="javascript: doSubmit('admin/validation_rules_management/validation_rules_management_menu.jsp','DoNothing'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "validation-rules-management", "Validation Rules Management")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-validation-rules-management", "Validation Rules Options Menu.")%></td>
		</tr>
<%	} %>

	<!--  supplier evaluation  -->
	<%	if (role.getAccessRights("SUPPLIEREVAL") > 0 ) { %>
		<tr>
			<td align="right" height="32px" width="10%">
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width="30%" class="formType">
				<a href="javascript: doSubmit('admin/supplier/supplier_eval_setup.jsp', 'DoNothing'); void(0);" class="formType"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "supplierevalsetup", "Supplier Evaluation Setup")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-supplierevalsetup", "Set Supplier Evaluation Settings.")%></td>
		</tr>
	<% } %>
			<!--  supplier evaluation  -->
		<!--tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
				<a href="javascript: doSubmit('admin/refresh-dictionaries.jsp', 'DoNothing'); void(0);" class=formType>Refresh Dictionaries</a></td>
			<td>Change labels</td>
		</tr-->
	<!--  audit log-->
	<%	if (PropertiesManager.getInstance(oid).getProperty("AUDITLOGADMIN", "ENABLED", "N").equalsIgnoreCase("Y")) {%>
		<tr>
			<td align="right" height="32px" width="10%">
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width="30%" class="formType">
				<a href="javascript: browse('auditrecord'); void(0);" class="formType"> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "auditlog", "View Audit Log")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-auditlog", "View Audit Log.")%></td>
		</tr>
	<% } %>

	<!--   alert configuration   -->
	<% if (role.getAccessRights("ALERTS") > 0 ) { %>
		<tr>
			<td align=right height=32px width=10%>
			<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
			<a href="javascript: doSubmit('admin/alerts_configuration.jsp','AlertsConfiguration'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "alertsConfiguration", "Alerts Configuration")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-alertsConfiguration", "Manage the alerts messages.")%></td>
		</tr>
	<% } %>

	<!--   Interfaces Manual Run   -->
	<%
	if (role.getAccessRights("INTERFACESRUN") > 0) { %>
		<tr>
			<td align=right height=32px width=10%>
			<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=25% class=formType>
			<a href="javascript: doSubmit('admin/invoice_extract/inv_schedule_menu.jsp', 'DoNothing'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoiceExtractManagementMenu", "Invoice Extract Management")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-interfacesManualRun", "Execute interface manually.")%></td>
		</tr>
	<% } %>

	<% if (role.getAccessRights("WHATSNEW") > 0 ) { %>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
				<td nowrap width=25% class=formType>
				<a href="javascript: doSubmit('admin/whats_new_management/whats_new_setup.jsp','DoNothing'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "whatsNewManagement", "Whats New Management")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-whatsNewManagement", "Manage the Whats New Section.")%></td>
		</tr>
	<% } %>
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	setNavCookie('/admin/admin_menu.jsp', 'DoNothing', 'Admin', true);

	function labelsManagement() {
<%	if (role.getAccessRights("LABELS") == 99 ) { %>
		frm.browseName.value = 'labels-admin';
<%	} else {%>
		frm.browseName.value = 'labels';
<%	}%>
		setFilter("Labels_moduleAccess", "=", "ADMIN");
		doSubmit('/admin/labels/labels.jsp', 'BrowseRetrieve');
	}

	function rulesManagement() {
		frm.browseName.value = 'rules-admin';
		doSubmit('/admin/rules/rules.jsp', 'BrowseRetrieve');
	}

	function userProfileAdmin() {
<%	if (isXpress) {%>
		browse('admin-userprofile')
<%	} else {%>
		browseFilter('admin-userprofile')
<%	}%>
	}
// End Hide script -->
</SCRIPT>
