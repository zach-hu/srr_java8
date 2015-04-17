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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>New Return to Inventory Options</div>
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
<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
<tr>
	<td width="<%=formEntryWidth%>" align="center"><b>Type of Return</b></td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr><td>&nbsp;</td></tr>
<tr>
	<td width="<%=formEntryWidth%>" align="center">
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><input type="radio" name="r_from" checked></td>
				<td width="150px" class="processOn">Inventory Request</td>
				<td><input type="radio" name="r_from"></td>
				<td width="150px" class="processOn">Over the Counter</td>
			</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
<tr>
	<td width="<%=formEntryWidth%>" align="center">
		<div id="pxbutton"><a href="javascript: newInventoryReturn(); void(0);">Continue</a></div>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	setNavCookie("/inventory/inv_return_options.jsp", "DoNothing", "New Return to Inventory Options");

	frm = document.purchaseform;

	function newInventoryReturn()
	{
		if (frm.r_from[0].checked)
		{
			browseFilter('inv-returns-reqline');
		}
		else
		{
			//browseFilter('inv-returns-dsbline');
			var newInputField = "<input type='hidden' name='viewAction' value='save'>";
			newInputField = newInputField + "<input type='hidden' name='returnMethod' value='OTC'>";
			setHiddenFields(newInputField);
			doSubmit('/inventory/inv_return_summary.jsp', 'DoNothing');
		}
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>