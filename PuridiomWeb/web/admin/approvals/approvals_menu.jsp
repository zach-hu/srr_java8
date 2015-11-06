<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	appUdf1 = HiltonUtility.ckNull((String)propertiesManager.getProperty("APPROVALS", "APPUDF1", "javascript: browseFilter('app-udf1'); void(0);"));
	String browseUDF1=null;
	if(appUdf1.indexOf("VendorId") >= 0)
	{
		appUdf1 = "VendorId";
	}else
	{
		String[] arrayAppudf1= appUdf1.split(",");

		if(arrayAppudf1.length == 5)
		{
			browseUDF1 =  arrayAppudf1[4];
		}else
		{
			browseUDF1 = "javascript: browseFilter('app-udf1'); void(0);";
		}
	}


%>
<tsa:hidden name="returnpage" value="approvalsmenu"/>
<tsa:hidden name="AppRulesExt_ruleType" value="REQ"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Approval Rules Menu</div>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellspacing=0 cellpadding=0 width=680px align=center>
		<!--tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=30% class=formType>
				<a href="javascript: unavailable(); void(0);" class=formType>Approval Activation</a></td>
			<td>Set up and activate approval types.</td>
		</tr>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=30% class=formType>
				<a href="javascript: unavailable(); void(0);" class=formType>Approval Extended Rules</a></td>
			<td>Set up extended rule definitions and assign approvers accordingly.</td>
		</tr-->
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=30% class=formType>
				<a href="javascript: browse('app_pool'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvalPools", "Approval Pools")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-approvalPools", "Create approval pools and assign approvers to a pool")%>.</td>
		</tr>
		<!--tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=30% class=formType>
				<a href="javascript: unavailable(); void(0);" class=formType>Approval UDFs Setup</a></td>
			<td>Set up and activate approval definitions based on udfs.</td>
		</tr-->
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=30% class=formType>
				<a href="javascript: setAction('SETUP'); browseFilter('approver-admin'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvalUserRulesSetup", "Approval User Rules Setup")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-approvalUserRulesSetup", "Assign approval user rule definitions, including authorization amounts")%>.</td>
		</tr>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=35% class=formType>
				<%--To set up a BROWSE use the Label Manager!--%>
				<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "APPRULEUDF1SETUP", "Appruleudf1 Approval Rules Setup", false, true, "appruleudf1", "formType")%>
			</td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "assignApprovalRules", "Assign approval rules for")%>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf1", "Approval UDF 1")%>.</td>
		</tr>
		<!--tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=30% class=formType>
				<a href="javascript: unavailable(); void(0);" class=formType>Extended Approval List</a></td>
			<td>Set up and activate extended approval lists.</td>
		</tr-->
		<%if(!oid.equalsIgnoreCase("vse06p")) {%>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=30% class=formType>
				<a href="javascript: retrieveExtendedApprovalRules('REQ'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "extendedApprovalRules", "Extended Approval Rules")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-extendedApprovalRules", "View and assign extended approval user rule definitions, including authorization amounts")%>.</td>
		</tr>
		<%} %>
		<%	if (PropertiesManager.getInstance(oid).getProperty("AUDITLOGADMIN", "ENABLED", "N").equalsIgnoreCase("Y")) {%>
		<tr>
			<td align="right" height="32px" width="10%">
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width="30%" class="formType">
				<a href="javascript: viewApprovalsAuditLog(); void(0);" class="formType"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "apprule-auditlog", "View Approvals Audit Log")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "viewAditLog", "View Audit Log")%>.</td>
		</tr>
		<% } %>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=30% class=formType>
				<a href="javascript: doSubmit('admin/approvals/approvals_setup.jsp', 'DoNothing'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvalSetupOptions", "Approval Setup Options")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-approvalSetupOptions", "Specify approval setup options")%>.</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("admin/approvals/approvals_menu.jsp", "DoNothing", "Approval Rules Menu");

	function browseAppRules(udf){
		if(udf=='AC03')
		{
			browseFilter('app-ac03');
		}
	}

	function viewApprovalsAuditLog()
	{
		setOriginalFilter("AuditRecord_entityClass", "=", "AppRule");
		browse('auditrecord');
	}

	function setAction(action)
	{
		var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"appMenuAction\" value=\"" + action + "\" >";
		setHiddenFields(newInputField);
	}

	function retrieveExtendedApprovalRules(type) {
		frm.AppRulesExt_ruleType.value = type;
		doSubmit('admin/approvals/ext_rules.jsp', 'AppRulesExtRetrieveByRuleType');
	}

	function browseSetup(browseName, browseType, fieldName) {
		if (!isEmpty(browseType) && browseName.indexOf("stdtable") == 0) {
			setFilter('StdTable_tableType', '=', browseType);
		}
		browseFilter(browseName);
	}
		
// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>