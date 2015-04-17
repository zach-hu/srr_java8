<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.entity.InvLocationPK.*" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>

<form name="purchaseform" target="_parent" action="/procure" method="POST">
<tsa:hidden name="allowBrowse" value="TRUE"/>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=750px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=750px>
		<tr>
			<td width=50% align=center valign=top>
				<table id=invLocationsTable border=1 cellspacing=0 cellpadding=0 width=750px class=browseHdr>
				<tr>
<% String invItemNumber = (String) request.getAttribute("InvLocation_itemNumber");
%>
					<td class=browseHdr height=18px nowrap>&nbsp;Move From Location "<%=invItemNumber%>"</td>
					<tsa:hidden name="InvLocation_itemNumber" value="<%=invItemNumber%>"/>
					<tsa:hidden name="InvItem_itemNumber" value="<%=invItemNumber%>"/>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr>
							<td align=left width=13%><b>Item Location</b></td>
							<td align=center width=9%><b>Qty on Hand</b></td>
							<td align=center width=9%><b>Qty on Order</b></td>
							<td align=center width=10%><b>Backorder</b></td>
							<td align=center width=10%><b>Qty Alloc.</b></td>
							<td align=center width=9%><b>Min on Hand</b></td>
							<td align=center width=9%><b>Max on Hand</b></td>
							<td align=center width=10%><b>Qty EOQ</b></td>
							<td align=center width=10%><b>Qty ESQ</b></td>
							<td align=center width=11%><b>Qty Requested</b></td>
						</tr>
						</table>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<% 
	InvLocation invLocation = (InvLocation) request.getAttribute("invLocation");
	InvLocationPK invLocationPK = invLocation.getComp_id();
	String s_location = invLocationPK.getItemLocation();
	String s_item_number = invLocationPK.getItemNumber();
	BigDecimal b_qty_onhand = invLocation.getQtyOnHand();
	if (b_qty_onhand==null){b_qty_onhand = new BigDecimal(0);}
	BigDecimal b_qty_onorder = invLocation.getQtyOnOrder();
	if (b_qty_onorder==null){b_qty_onorder = new BigDecimal(0);}
	BigDecimal b_backorder = invLocation.getQtyPendOrder();
	if (b_backorder==null){b_backorder = new BigDecimal(0);}
	BigDecimal b_qty_alloc = invLocation.getQtyAlloc();
	if (b_qty_alloc==null){b_qty_alloc = new BigDecimal(0);}
	BigDecimal b_min_onhand = invLocation.getMinOnHand();
	if (b_min_onhand==null){b_min_onhand = new BigDecimal(0);}
	BigDecimal b_max_onhand = invLocation.getMaxOnHand();
	if (b_max_onhand==null){b_max_onhand = new BigDecimal(0);}
	BigDecimal b_qty_eoq = invLocation.getQtyEoq();
	if (b_qty_eoq==null){b_qty_eoq = new BigDecimal(0);}
	BigDecimal b_qty_esq = invLocation.getQtyEsq();
	if (b_qty_esq==null){b_qty_esq = new BigDecimal(0);}
	BigDecimal b_qty_requested = invLocation.getQtyRequested();
	if (b_qty_requested==null){b_qty_requested = new BigDecimal(0);}
%>
						<tr>
							<td width=13% align=left><%=s_location%>
							<tsa:hidden  name="fromLocation" value="<%=s_location%>"/><tsa:hidden id="invNumber" value="<%=s_item_number%>"/><tsa:hidden name="InvLocation_itemLocation" value="<%=s_location%>"/></td>
							<td width=9% align=center nowrap><%=b_qty_onhand%></td>
							<td width=9% align=center nowrap><%=b_qty_onorder%></td>
							<td width=10% align=center nowrap><%=b_backorder%></td>
							<td width=10% align=center nowrap><%=b_qty_alloc%></td>
							<td width=9% align=center nowrap><%=b_min_onhand%></td>
							<td width=9% align=center nowrap><%=b_max_onhand%></td>
							<td width=10% align=center nowrap><%=b_qty_eoq%></td>
							<td width=10% align=center nowrap><%=b_qty_esq%></td>
							<td width=11% align=center nowrap><%=b_qty_requested%></td>
						</tr>
						<tr>
							<td colspan=10>
								<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow><tr>
									<td width=10% align=center nowrap>Quantity to Move:</td>
									<td width=3 align=center>&nbsp;</td>
									<td width=10% align=center><input type="text"  name="qtyToMove" value=""></td>
									<td width=5% align=center>&nbsp;</td>
									<td width=10% align=left nowrap><a href="javascript: browseMove('toLocation', '<%=s_item_number%>', 0); void(0);">New Location:</a></td>
									<td width=3% align=center>&nbsp;</td>
									<td width=10% align=center><input type="text"  name="toLocation" value=""></td>
									<td width=49% align=center>&nbsp;</td>
								</tr></table>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InventoryMove;InvItemRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
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

	function toggleDetailsDisplay(areaName, type) {
		var myAreas = document.getElementsByName(areaName);
		var myArea;
		var myImg = document.getElementById(areaName + "Img");
		var myText = document.getElementById(areaName + "Text");
		var hideArea = false;

		for (i = 0; i < myAreas.length; i++) {
			myArea = myAreas(i);


			if (myArea.style.display == "none") {
				myArea.style.display = "block";
			}
			else {
				hideArea = true;
				myArea.style.display = "none";
			}
		}

		if (type == undefined) {
			type = "Details";
		}
	
		if (hideArea) {
			myImg.src = "<%=contextPath%>/images/plus.gif";
			myImg.alt = "View" + type;
			myText.innerText = "View " + type;
		}
		else {
			myImg.src = "<%=contextPath%>/images/minus.gif";
			myImg.alt = "Hide " + type;
			myText.innerText = "Hide " + type;
		}
	}

	function viewLocation(row) {
		var loc = document.getElementById("invLocation_" + row);
		frm.InvLocation_itemLocation.value = loc.value;
		doSubmit('inventory/edit_location.jsp','InvLocationRetrieveById');
	}
	
	function moveItems(row) {
		var loc = document.getElementById("invLocation_" + row);
		frm.InvLocation_itemLocation.value = loc.value;
		doSubmit('inventory/std_item_move.jsp','InvLocationRetrieveById');
	}
	
	function deleteLocation(row) {
		var loc = document.getElementById("invLocation_" + row);
		frm.InvLocation_itemLocation.value = loc.value;
		confirm("Are you sure you want to delete " + frm.InvLocation_itemLocation.value + "?");
		doSubmit('inventory/inv_locations.jsp','InvLocationDelete');
	}
	
	function addLocation(){
		doSubmit('inventory/add_location.jsp', 'DoNothing');
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