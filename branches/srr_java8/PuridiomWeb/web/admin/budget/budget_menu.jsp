<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budgetMenu", "Budget Menu")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
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
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=30% class=formType>
				<a href="javascript: doSubmit('admin/budget/budget.jsp','DoNothing'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "", "Budget Setup")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-budgetSetup", "Set the Options for Budgets")%>.</td>
		</tr>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=30% class=formType>
				<a href="javascript: browseFilter('budget_center'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budgetAdministration", "Budget Administration")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "add-deleteBudgets", "Add or delete budgets")%>. <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "changeBudgetInformation", "Change budget information")%>.</td>
		</tr>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=30% class=formType>
				<a href="javascript: doSubmit('admin/budget/budget_xls_upload.jsp','DoNothing'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budgetUpload", "Budget Upload")%></a></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "add-budgets-xls-file", "Add budgets from XLS file")%>. <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "changeBudgetInformation", "Change budget information")%>.</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("admin/budget/budget_menu.jsp", "DoNothing", "Budget Menu");

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>