<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.math.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<tsa:hidden name="stdTable_tableType" value=""/>
<tsa:hidden name="allowBrowse" value="TRUE"/>

<br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=1 id=itemTable cellpadding=0 cellspacing=0 width=680px>
			<tr>
				<td class=browseHdr height=18px nowrap>&nbsp;Bin Locations Addition for "${esapi:encodeForHTML(InvBinLocation_itemLocation)}"</td>
				<tsa:hidden name="InvLocation_itemNumber" value="${InvLocation_itemNumber}"/>
			</tr>
			<tr>
				<td class=browseRow>
				<table border=0 cellspacing=0 cellpadding=1 width=80%>
					<tsa:hidden name="InvBinLocation_icRc" value=""/>
					<tsa:hidden name="InvBinLocation_hdrIc" value=""/>
					<tsa:hidden name="InvBinLocation_icRecLine" value=""/>
						<tr>
							<td width=25% align=right nowrap>Item Number:&nbsp<input type=text size=15 name="InvBinLocation_itemNumber" value="${esapi:encodeForHTMLAttribute(InvLocation_itemNumber)}" disabled></td>
							<td width=25% align=right nowrap>
								Item Date:&nbsp<input type=text size=15 name="InvBinLocation_itemDate" value="">
								<a href="javascript: show_calendar('InvBinLocation_itemDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
							</td>
							<td width=25% align=right nowrap>Manufacturer No.:&nbsp<input type=text size=15 name="InvBinLocation_manufactNo" value=""></td>
							<td width=25% align=right nowrap><a href="javascript: browseStd('InvBinLocation_udf1Code', 'BN01');">UDF 1:</a>&nbsp<input type=text size=15 name="InvBinLocation_udf1Code"  value=""></td>
						</tr>
						<tr>
							<td width=25% align=right nowrap>Item Location:&nbsp<input type=text size=15 name="InvBinLocation_itemLocation" value="${esapi:encodeForHTMLAttribute(InvBinLocation_itemLocation}" disabled></td>
							<td width=25% align=right nowrap>Cost:&nbsp<input type=text size=15 name="InvBinLocation_cost" value=""></td>
							<td width=25% align=right nowrap>Vendor ID:&nbsp<input type=text size=15 name="InvBinLocation_vendorId" value=""></td>
							<td width=25% align=right nowrap><a href="javascript: browseStd('InvBinLocation_udf2Code', 'BN02');">UDF 2:</a>&nbsp<input type=text size=15 name="InvBinLocation_udf2Code" value=""></td>
						</tr>
						<tr>
							<td width=25% align=right>Aisle:&nbsp<input type=text size=15 name="InvBinLocation_aisle" value=""></td>
							<td width=25% align=right nowrap>Lot:&nbsp<input type=text size=15 name="InvBinLocation_lot" value=""></td>
							<td width=25% align=right nowrap>Original Alloc.:&nbsp<input type=text size=15 name="InvBinLocation_originalAlloc" value=""></td>
							<td width=25% align=right nowrap><a href="javascript: browseStd('InvBinLocation_udf3Code', 'BN03');">UDF 3:</a>&nbsp<input type=text size=15 name="InvBinLocation_udf3Code" value=""></td>
						</tr>
						<tr>
							<td width=25% align=right nowrap>Location Row:&nbsp<input type=text size=15 name="InvBinLocation_locrow" value=""></td>
							<td width=25% align=right nowrap>Quantity On Hand:&nbsp<input type=text size=15 name="InvBinLocation_qtyOnHand" value=""></td>
							<td width=25% align=right nowrap>Physical Actual:&nbsp<input type=text size=15 name="InvBinLocation_physActual" value="" ></td>
							<td width=25% align=right nowrap><a href="javascript: browseStd('InvBinLocation', 'BN04');">UDF 4:</a>&nbsp<input type=text size=15 name="InvBinLocation_udf4Code" value=""></td>
						</tr>
						<tr>
							<td width=25% align=right nowrap>Tier:&nbsp<input type=text size=15 name="InvBinLocation_tier" value=""></td>
							<td width=25% align=right nowrap>Quantity Alloc.:&nbsp<input type=text size=15 name="InvBinLocation_qtyAlloc" value=""></td>
							<td width=25% align=right nowrap>Physical Original:&nbsp<input type=text size=15 name="InvBinLocation_physOriginal" value=""></td>
							<td width=25% align=right nowrap><a href="javascript: browseStd('InvBinLocation', 'BN05');">UDF 5:</a>&nbsp<input type=text size=15 name="InvBinLocation_udf5Code" value=""></td>
						</tr>
						<tr>
							<td width=25% align=right nowrap>Bin:&nbsp<input type=text size=15 name="InvBinLocation_bin" value=""></td>
							<td width=25%>&nbsp</td>
							<td width=25%>&nbsp</td>
							<td width=25%>&nbsp</td>
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
	<td width=50% align=center><a href="javascript: doSubmit('inventory/inv_bin.jsp', 'InvBinLocationAdd'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/inv_bin_locations.jsp', 'InvBinLocationRetrieveByItem'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0></a></td>
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

	function viewItem(row) {
		doSubmit('/requests/req_item.jsp', 'DoNothing');
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