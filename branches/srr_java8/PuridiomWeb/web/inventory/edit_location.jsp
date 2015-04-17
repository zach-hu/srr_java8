<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>

<%
	String s_title = "Edit Location";
%>

<tsa:hidden name="InvLocation_itemNumber" value="${InvLocation_itemNumber}"/>
<tsa:hidden name="stdTable_tableType" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=s_title%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Item #:</b></td>
			<td width=100px nowrap>${esapi:encodeForHTML(InvLocation_itemNumber)}</td>
		</tr>
		<tr>
			<td align=right><b>Item Location:</b></td>
			<td width=100px nowrap>${esapi:encodeForHTML(InvLocation_itemLocation)}</td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
<% 
	InvLocation invLocation = (InvLocation) request.getAttribute("invLocation");
	BigDecimal bd_qtyonhand = HiltonUtility.getFormattedQuantity(invLocation.getQtyOnHand(), oid);
	BigDecimal bd_qtyonorder = HiltonUtility.getFormattedQuantity(invLocation.getQtyOnOrder(), oid);
	BigDecimal bd_qtyalloc = HiltonUtility.getFormattedQuantity(invLocation.getQtyAlloc(), oid);
	BigDecimal bd_qtypendorder = HiltonUtility.getFormattedQuantity(invLocation.getQtyPendOrder(), oid);
	BigDecimal bd_backorder = new BigDecimal(0);
	BigDecimal bd_minonhand = HiltonUtility.getFormattedQuantity(invLocation.getMinOnHand(), oid);
	BigDecimal bd_maxonhand = HiltonUtility.getFormattedQuantity(invLocation.getMaxOnHand(), oid);
	BigDecimal bd_averagecost = HiltonUtility.getFormattedDollar(invLocation.getAverageCost(), oid);
	BigDecimal bd_qtyeoq = HiltonUtility.getFormattedQuantity(invLocation.getQtyEoq(), oid);
	BigDecimal bd_qtyesq = HiltonUtility.getFormattedQuantity(invLocation.getQtyEsq(), oid);
	
	BigDecimal bd_qty_available = new BigDecimal(0);
	
	if ( (bd_qtyonhand.subtract(bd_qtyalloc)).compareTo(new BigDecimal(0)) > 0)
	{
		bd_qty_available = bd_qtyonhand.subtract(bd_qtyalloc);
	}

	String s_udf1 = HiltonUtility.ckNull(invLocation.getUdf1Code());
	String s_udf2 = HiltonUtility.ckNull(invLocation.getUdf2Code());
	String s_udf3 = HiltonUtility.ckNull(invLocation.getUdf3Code());
	String s_udf4 = HiltonUtility.ckNull(invLocation.getUdf4Code());
	String s_udf5 = HiltonUtility.ckNull(invLocation.getUdf5Code());
	String s_autoreplenish = HiltonUtility.ckNull(invLocation.getAutoReplenish());
	String s_primelocation = HiltonUtility.ckNull(invLocation.getPrimeLocation());
%>
		<tsa:hidden name="InvBinLocation_itemLocation" value="${InvLocation_itemLocation}"/>
		<tsa:hidden name="InvBinLocation_itemNumber" value="${InvLocation_itemNumber}"/>
		<tr>
			<td width=25% align=right>Location:&nbsp<input type=text size=15 name="InvLocation_itemLocation"  TABINDEX=1 value="${esapi:encodeForHTMLAttribute(InvLocation_itemLocation)}" disabled></td>
			<td width=25% align=right nowrap>Min On Hand:&nbsp<input type=text size=15 name="InvLocation_minOnHand" TABINDEX=7 value="<%=bd_minonhand%>"></td>
			<td width=25% align=right nowrap><a href="javascript: browseStd('InvLocation_udf1Code', 'IN01'); void(0);">UDF 1:</a>&nbsp<input type=text size=15 name="InvLocation_udf1Code" TABINDEX=12 value="<%=s_udf1%>"></td>
			<td width=25% align=right nowrap>
				Auto Replenish:&nbsp;
				<input type=checkbox name="c_checkbox" tabindex=17 <% if (s_autoreplenish.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(frm.InvLocation_autoReplenish, 0);">
				<tsa:hidden name="InvLocation_autoReplenish" value="<%=s_autoreplenish%>"/>
			</td>
		</tr>
		<tr>
			<td width=25% align=right nowrap>Qty On Hand:&nbsp<input type=text size=15 name="InvLocation_qtyOnHand" TABINDEX=2 value="<%=bd_qtyonhand%>" disabled></td>
			<td width=25% align=right nowrap>Max On Hand:&nbsp<input type=text size=15 name="InvLocation_maxOnHand" TABINDEX=8 value="<%=bd_maxonhand%>"></td>
			<td width=25% align=right nowrap><a href="javascript: browseStd('InvLocation_udf2Code', 'IN02'); void(0);">UDF 2:</a>&nbsp<input type=text size=15 name="InvLocation_udf2Code" TABINDEX=13 value="<%=s_udf2%>"></td>
			<td width=25% align=right nowrap>
				Prime Location:&nbsp;
				<input type=checkbox name="c_checkbox" tabindex=18 <% if (s_primelocation.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(frm.InvLocation_primeLocation, 0);">
				<tsa:hidden name="InvLocation_primeLocation" value="<%=s_primelocation%>"/>
			</td>
		</tr>
		<tr>
			<td width=25% align=right nowrap>Qty On Order:&nbsp<input type=text size=15 name="InvLocation_qtyOnOrder" TABINDEX=3 value="<%=bd_qtyonorder%>"></td>
			<td width=25% align=right nowrap>Average Cost:&nbsp<input type=text size=15 name="InvLocation_averageCost" TABINDEX=9 value="<%=bd_averagecost%>" disabled></td>
			<td width=25% align=right nowrap><a href="javascript: browseStd('InvLocation_udf3Code', 'IN03'); void(0);">UDF 3:</a>&nbsp<input type=text size=15 name="InvLocation_udf3Code" TABINDEX=14 value="<%=s_udf3%>"></td>
			<td width=25% align=right nowrap>&nbspBin Location(s):&nbsp<a href="javascript: viewBin();"><IMG SRC="<%=contextPath%>/images/cmd_search_item.gif" BORDER="0"  TABINDEX=18 ALT="Bin Location Adjustment"></a></td>
		</tr>
		<tr>
			<td width=25% align=right nowrap>Allocated:&nbsp<input type=text size=15 name="InvLocation_qtyAlloc" TABINDEX=4 value="<%=bd_qtyalloc%>" disabled></td>
			<td width=25% align=right nowrap>Eco Order Qty:&nbsp<input type=text size=15 name="InvLocation_qtyEoq" TABINDEX=10 value="<%=bd_qtyeoq%>"></td>
			<td width=25% align=right nowrap><a href="javascript: browseStd('InvLocation_udf4Code', 'IN04'); void(0);">UDF 4:</a>&nbsp<input type=text size=15 name="InvLocation_udf4Code" TABINDEX=15 value="<%=s_udf4%>"></td>
			<td width=25%>&nbsp</td>
		</tr>
		<tr>
			<td width=25% align=right nowrap>Available:&nbsp<input type=text size=15 name="qtyAvailable" TABINDEX=5 value="<%=bd_qty_available%>" disabled></td>
			<td width=25% align=right nowrap>Eco Stock Qty:&nbsp<input type=text size=15 name="InvLocation_qtyEsq" TABINDEX=11 value="<%=bd_qtyesq%>"></td>
			<td width=25% align=right nowrap><a href="javascript: browseStd('InvLocation_udf5Code', 'IN05'); void(0);">UDF 5:</a>&nbsp<input type=text size=15 name="InvLocation_udf5Code" TABINDEX=16 value="<%=s_udf5%>"></td>
			<td width=25%>&nbsp</td>
		</tr>
		<tr>
			<td width=25% align=right nowrap>Backorder:&nbsp<input type=text size=15 name="backorder" TABINDEX=6 value="need to calculate backorder!" disabled></td>
			<td width=25% align=right nowrap>Qty Pend Order:&nbsp<input type=text size=15 name="InvLocation_qtyPendOrder" TABINDEX=12 value="<%=bd_qtypendorder%>"></td>
			<td width=25%>&nbsp</td>
			<td width=25%>&nbsp</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('inventory/edit_location.jsp', 'InvLocationUpdate'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('inventory/inv_locations.jsp', 'InvLocationRetrieveByItem');"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

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

	function viewBin() {
		doSubmit('/inventory/inv_bin_locations.jsp', 'InvBinLocationRetrieveByItem');
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