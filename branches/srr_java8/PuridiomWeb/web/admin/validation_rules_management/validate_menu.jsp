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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Approval Rules Menu</div>
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
				<a href="javascript: browse('app_pool'); void(0);" class=formType>Approval Pools</a></td>
			<td>Create approval pools and assign approvers to a pool.</td>
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
				<a href="javascript: doSubmit('/admin/validate_management/validate_menu.jsp', 'DoNothing'); void(0);" class=formType>Approval User Rules Setup</a></td>
			<td>Assign approval user rule definitions, including authorization amounts.</td>
		</tr>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=35% class=formType>
				<a href="javascript: browseFilter('app-udf1'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf1", "Approval UDF 1")%> Approval Rules Setup</a></td>
			<td>Assign approval rules for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf1", "Approval UDF 1")%>.</td>
		</tr>
		<!--tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=30% class=formType>
				<a href="javascript: unavailable(); void(0);" class=formType>Extended Approval List</a></td>
			<td>Set up and activate extended approval lists.</td>
		</tr-->
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("admin/approvals/approvals_menu.jsp", "DoNothing", "Approval Rules Menu");

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>