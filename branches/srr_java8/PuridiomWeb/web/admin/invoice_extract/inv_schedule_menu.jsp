<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<table width=680px cellpadding=0 cellspacing=0 border=0>





<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Invoice Extract Management</div>
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

<!--   Interfaces Manual Run   -->
<%
if (role.getAccessRights("INTERFACESRUN") > 0) { %>
<tr>
	<td width=680px align=center>
		<table border=0 cellspacing=0 cellpadding=0 width=680px align=center>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=30% class=formType>
				<a href="javascript: doSubmit('/invoice/inv_schedule_run.jsp', 'DoNothing');" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "scheduleInterfaceManualRun", "Schedule Interface Manual Run")%></a></td>
			<td width=40%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "desc-interfacesManualRun", "Execute interface manually.")%></td>
		</tr>
		</table>
	</td>
</tr>
<% } %>

<tr>
	<td width=680px align=center>
		<table border=0 cellspacing=0 cellpadding=0 width=680px align=center>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=30% class=formType>
				<a href="javascript: browseFilter('invoice-exported-reset'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "resetExtractedFlagByInvoice", "Reset Extracted Flag by Invoice")%></a></td>
			<td width=40%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "", "Reset Extracted Flag by Invoice")%>.</td>
		</tr>
		</table>
	</td>
</tr>



</table>

	<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	//setNavCookie("admin/invoice_extract/inv_schedule_menu.jsp", "DoNothing", "Invoice Extract Management");

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>