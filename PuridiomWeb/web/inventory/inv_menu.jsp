<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<tsa:hidden name="process" value=""/>
<tsa:hidden name="itemAction" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Inventory Administration Menu</div>
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
			<td nowrap width=35% class=formType>
				<a href="javascript: browseFilter('invitem-admin'); void(0);">Open Item</a></td>
			<td>Open an inventory item.</td>
		</tr>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=35% class=formType>
				<a href="javascript: addItem(); void(0);">Add Item</a></td>
			<td>Create a new inventory item.</td>
		</tr-->
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=35% class=formType>
				<a href="javascript: browse('inv-locations'); void(0);" class=formType>Inventory Physical Counts</a></td>
			<td>Enter inventory physical counts for a location.</td>
		</tr>
		<!--tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=35% class=formType>
				<a href="javascript: doSubmit('inventory/open_backorders.jsp', 'DoNothing'); void(0);">Backorder Notifications</a></td>
			<td>View notifications of backorders.</td>
		</tr-->
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=35% class=formType>
				<a href="javascript: browseFilter('inventory-returns'); void(0);" class=formType>Inventory Returns</a></td>
			<td>View returns to the inventory.</td>
		</tr>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=35% class=formType>
				<a href="javascript: doSubmit('/inventory/change_location_name.jsp', 'AddressRetrieveAllInventory'); void(0);" class=formType>Change Location Name</a></td>
			<td>Change the name of a location.</td>
		</tr>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=35% class=formType>
				<a href="javascript: doSubmit('/inventory/consolidate_locations.jsp', 'AddressRetrieveAllInventory'); void(0);" class=formType>Consolidate Locations
			<td>Combine locations into one convenient location.</td>
		</tr>
		<!--tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=35% class=formType>
				<a href="javascript: void(0);">Form Approvals</a></td>
			<td>Forms for the use of approval.</td>
		</tr-->
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	setNavCookie("/inventory/inv_menu.jsp", "DoNothing", "Inventory Administration Menu");

	frm = document.purchaseform;

	/*
	function addItem()
	{
		frm.itemAction.value = "CREATE";
		doSubmit('/inventory/inv_item.jsp', 'DoNothing');
	}
	*/


// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>