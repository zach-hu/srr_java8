<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.entity.InvLocationPK.*" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>

<tsa:hidden name="stdTable_tableType" value=""/>
<tsa:hidden name="allowBrowse" value="TRUE"/>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=50% align=center valign=top>
				<table id=invLocationsTable border=1 cellspacing=0 cellpadding=0 width=680px class=browseHdr>
				<tr>
<%
	String invItemNumber = (String) request.getAttribute("InvLocation_itemNumber");
%>
					<td class=browseHdr height=18px nowrap>&nbsp;Add Location for "<%=invItemNumber%>"</td>
					<tsa:hidden name="InvLocation_itemNumber" value="<%=invItemNumber%>"/>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>

						<tr>
							<td width=25% align=right><a href="javascript: browseLookup('InvLocation_itemLocation', 'item_location'); void(0);">Location:</a>&nbsp<input type=text size=15 name="InvLocation_itemLocation" TABINDEX=1 value="" disabled></td>
							<td width=25% align=right nowrap>Min On Hand:&nbsp<input type=text size=15 name="InvLocation_minOnHand" TABINDEX=7 value="0.00"></td>
							<td width=25% align=right nowrap><a href="javascript: browseStd('InvLocation_udf1Code', 'IN01'); void(0);">UDF 1:</a>&nbsp<input type=text size=15 name="InvLocation_udf1Code" TABINDEX=13 value=""></td>
							<td width=25% align=right nowrap>Auto Replenish:&nbsp<INPUT TYPE="CHECKBOX" NAME="c_checkbox" TABINDEX=19 value="" onclick="setReplenish();">
								<tsa:hidden name="InvLocation_autoReplenish" value=""/><BR>
							</td>
						</tr>
						<tr>
							<td width=25% align=right nowrap>Qty On Hand:&nbsp<input type=text size=15 name="InvLocation_qtyOnHand" TABINDEX=2 value="0.00"></td>
							<td width=25% align=right nowrap>Max On Hand:&nbsp<input type=text size=15 name="InvLocation_maxOnHand" TABINDEX=8 value="0.00"></td>
							<td width=25% align=right nowrap><a href="javascript: browseStd('InvLocation_udf1Code', 'IN02'); void(0);">UDF 2:</a>&nbsp<input type=text size=15 name="InvLocation_udf2Code" value="" TABINDEX=14 ONCHANGE="upperCase(this);"></td>
							<!--td width=25% align=right nowrap>Phys. Orginal:&nbsp<input type=text size=15 name="InvLocation_physOriginal" TABINDEX=20 value="0.00"></td-->
							<td width=25% align=right nowrap>Prime Location:&nbsp<input type=checkbox name="c_checkbox" TABINDEX=18 value="" ONCLICK="setPrime();">
								<tsa:hidden name="InvLocation_primeLocation" value=""/><BR>
							</td>
						</tr>
						<tr>
							<td width=25% align=right nowrap>Qty On Order:&nbsp<input type=text size=15 name="InvLocation_qtyOnOrder" TABINDEX=3 value="0.00"></td>
							<td width=25% align=right nowrap>Average Cost:&nbsp<input type=text size=15 name="InvLocation_averageCost" TABINDEX=9 value="0.00"></td>
							<td width=25% align=right nowrap><a href="javascript: browseStd('InvLocation_udf1Code', 'IN03'); void(0);">UDF 3:</a>&nbsp<input type=text size=15 name="InvLocation_udf3Code" value="" TABINDEX=15 ONCHANGE="upperCase(this);"></td>
							<!--td width=25% align=right nowrap>Original Alloc.:&nbsp<input type=text size=15 name="InvLocation_originalAlloc" TABINDEX=21 value="0.00"></td-->
						</tr>
						<tr>
							<td width=25% align=right nowrap>Allocated:&nbsp<input type=text size=15 name="InvLocation_qtyAlloc" TABINDEX=4 value="0.00"></td>
							<td width=25% align=right nowrap>Eco Order Qty:&nbsp<input type=text size=15 name="InvLocation_qtyEoq" TABINDEX=10 value="0.00"></td>
							<td width=25% align=right nowrap><a href="javascript: browseStd('InvLocation_udf1Code', 'IN04'); void(0);">UDF 4:</a>&nbsp<input type=text size=15 name="InvLocation_udf4Code" value="" TABINDEX=16 ONCHANGE="upperCase(this);"></td>
							<td width=25%>&nbsp</td>
						</tr>
						<tr>
							<td width=25% align=right nowrap>Available:&nbsp<input type=text size=15 name="InvLocation_physActual" TABINDEX=5 value="0.00"></td>
							<td width=25% align=right nowrap>Eco Stock Qty:&nbsp<input type=text size=15 name="InvLocation_qtyEsq" TABINDEX=11 value="0.00"></td>
							<td width=25% align=right nowrap><a href="javascript: browseStd('InvLocation_udf1Code', 'IN05'); void(0);">UDF 5:</a>&nbsp<input type=text size=15 name="InvLocation_udf5Code" TABINDEX=17 value="" ONCHANGE="upperCase(this);"></td>
							<td width=25%>&nbsp</td>
						</tr>
						<tr>
							<td width=25% align=right nowrap>Backorder:&nbsp<input type=text size=15 name="InvLocation_qtyPendOrder" TABINDEX=6 value="0.00"></td>
							<td width=25% align=right nowrap>Phys. Alloc.:&nbsp<input type=text size=15 name="InvLocation_physAlloc" TABINDEX=12 value="0.00"></td>
							<!--td width=25% align=right nowrap>Prime Location:&nbsp<input type=text size=15 name="InvLocation_primeLocation" TABINDEX=18 value="" ONCHANGE="upperCase(this);"></td-->
							<td width=25%>&nbsp</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('inventory/edit_location.jsp', 'InvLocationAdd'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('inventory/inv_locations.jsp', 'InvLocationRetrieveByItem');"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0></a></td>
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
	
	function setReplenish(){
		if(frm.c_checkbox.checked==true){
			frm.InvLocation_autoReplenish.value = 'Y';
		}
		else{
			frm.InvLocation_autoReplenish.value = 'N';
		}
	}
	
	function setPrime(){
		if(frm.c_checkbox[1].checked==true){
			frm.InvLocation_primeLocation.value = 'Y';
		}
		else{
			frm.InvLocation_primeLocation.value = 'N';
		}
	}
	
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