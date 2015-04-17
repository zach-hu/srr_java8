<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>

<form name="purchaseform" target="_parent" action="/procure" method="POST">

<br>

<table border=0 cellpadding=0 cellspacing=0 width=800px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=800px>
		<tr>
			<td width=100% align=center valign=top>
				<table id=supplierTable border=1 cellspacing=0 cellpadding=0 width=800px class=browseHdr>
				<tr>
					<td class=browseHdr height=18px nowrap>&nbsp;Item Inventory</td>
				</tr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td width=12% align=left><b>Item #</b></td>
							<td width=20% align=center><b>Description</b></td>
							<td width=13% align=center><b>Commodity</b></td>
							<td width=5% align=center><b>U/M</b></td>
							<td width=13% align=center><b>Cost</b></td>
							<td width=8% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%></b></td>
							<td width=8% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%></b></td>
							<td width=9% align=center><b>Locations</b></td>
							<td width=8% align=center><b>Delete</b></td>
						</tr>
						</table>
						<tsa:hidden name="InvItem_itemNumber" value=""/>
						<tsa:hidden name="InvLocation_itemNumber" value=""/>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%
	List itemList = (List)request.getAttribute("invItem");
	for(int i=0;i<itemList.size();i++)
	{
	InvItem invItem = (InvItem) itemList.get(i);
	String s_item_number = invItem.getItemNumber();
	String s_item_description = invItem.getDescription();
	if (s_item_description==null){s_item_description = "";}
	String s_item_commodity = invItem.getCommodity();
	if (s_item_commodity==null){s_item_commodity = "";}
	String s_item_umcode = invItem.getUnitOfOrder();
	if (s_item_umcode==null){s_item_umcode = "";}
	BigDecimal b_item_cost = invItem.getCost();
	if (b_item_cost==null){b_item_cost = new BigDecimal(0);}
	String s_item_owner = invItem.getOwner();
	if (s_item_owner==null){s_item_owner = "";}
	String s_item_status = invItem.getStatus();
	if (s_item_status==null){s_item_status = "";}
%>
						<tr>
							<td width=12% align=left class=browseRow nowrap><a href="javascript: viewItem(<%=i%>);void(0);"><%=s_item_number%></a><tsa:hidden id="invNumber_<%=i%>" name="invNumber_<%=i%>" value="<%=s_item_number%>"/></td>
							<td width=19% align=left><%=s_item_description%></td>
							<td width=14% align=center><%=s_item_commodity%></td>
							<td width=5% align=center nowrap><%=s_item_umcode%></td>
							<td width=12% align=center nowrap><%=b_item_cost%></td>
							<td width=8% align=center nowrap><%=s_item_owner%></td>
							<td width=8% align=center nowrap><%=s_item_status%></td>
							<td width=9% align=center><a href="javascript: viewLocation(<%=i%>);void(0);"><IMG SRC="<%=contextPath%>/images/cmd_search_item.gif" BORDER="0" ALT="Item Locations"></a></td>
							<td width=8% align=center><a href="javascript: deleteItem(<%=i%>);void(0);"><IMG SRC="<%=contextPath%>/images/delete.gif" BORDER="0" ALT="Delete Item"></a></td>
						</tr>
<% } %>
						</table>
					</td>
				</tr>
				</table>
			</td>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=100% align=center><a href="javascript: doSubmit('inventory/inv_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0></a></td>
</tr>
</table>
</td>
</tr>
</table>

<br>

</FORM>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;


	function viewItem(row) {
		var num = document.getElementById("invNumber_" + row);
		frm.InvItem_itemNumber.value = num.value;
		doSubmit('/inventory/inv_item.jsp','InvItemRetrieveById');
	}

	function viewLocation(row) {
		var num = document.getElementById("invNumber_" + row);
		frm.InvLocation_itemNumber.value = num.value;
		doSubmit('inventory/inv_locations.jsp','InvLocationRetrieveByItem');
	}

	function deleteItem(row) {
		var num = document.getElementById("invNumber_" + row);
		frm.InvItem_itemNumber.value = num.value;
		if (confirm("Are you sure you want to delete \"" + frm.InvItem_itemNumber.value  + "\"?")){
			doSubmit('inventory/inv_items.jsp','InvItemDelete');
		}
	}


	function highlightItem(row) {
		var myRow;

		if (row == 0) {
			myRow = document.all.itemRow.rows(row);
		}
		else {
			myRow = document.all.itemRows.rows(row - 1);
		}

		setRowClassName(myRow, "selectedRow");
	}

	function removeItemHighlight(row) {
		var myRow;

		if (row == 0) {
			myRow = document.all.itemRow.rows(row);
		}
		else {
			myRow = document.all.itemRows.rows(row - 1);
		}

		setRowClassName(myRow, "browseRow");
	}


// End Hide script -->
</SCRIPT>