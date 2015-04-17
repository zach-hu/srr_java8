<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.math.*" %>

<%
	String invItemNumber = (String) request.getAttribute("InvLocation_itemNumber");
	String invItemLocation = (String) request.getAttribute("InvLocation_itemLocation");

	String s_umCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_unitOfOrder"));
	String s_duomUmCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_duomUmCode"));
	String dualUmRequired = HiltonUtility.ckNull((String) request.getAttribute("dualUmRequired")) ;
	String serNoRequired = HiltonUtility.ckNull((String) request.getAttribute("serNoRequired")) ;
%>

<tsa:hidden name="InvItem_itemNumber" value="<%=invItemNumber%>"/>
<tsa:hidden name="InvLocation_itemNumber" value="<%=invItemNumber%>"/>
<tsa:hidden name="InvLocation_itemLocation" value="<%=invItemLocation%>"/>
<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="itemAction" value="UPDATE"/>

<tsa:hidden name="InvItem_commodity" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_commodity\"))%>"/>
<tsa:hidden name="InvItem_unitOfOrder" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_unitOfOrder\"))%>"/>
<tsa:hidden name="InvItem_duomUmCode" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_duomUmCode\"))%>"/>
<tsa:hidden name="dualUmRequired" value="<%=dualUmRequired%>"/>
<tsa:hidden name="serNoRequired" value="<%=serNoRequired%>"/>

<%
	InvProperty invProperty = (InvProperty) request.getAttribute("invProperty");
	BigDecimal  icProperty  = invProperty.getIcProperty();
	BigDecimal  icRc        = invProperty.getIcRc();
%>

<tsa:hidden name="InvProperty_icProperty" value="<%=icProperty.toString()%>"/>
<tsa:hidden name="InvProperty_icRc" value="<%=icRc.toString()%>"/>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=50% align=center valign=top>
				<table id=invLocationsTable border=1 cellspacing=0 cellpadding=0 width=680px class=browseHdr>
				<tr>
					<td class=browseHdr height=18px nowrap>&nbsp;Move For Item "<%=invItemNumber%>"</td>
				</tr>
				<tr>
					<td>
						<table border=0>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-quantityOnHand")%> align=center width=15% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-quantityOnHand", "On Hand")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-allocated")%> align=center width=15% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-allocated", "Allocated")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-available")%> align=center width=15% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-available", "Available")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-lotNumber")%> align=center width=10% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-lotNumber", "Lot")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-unitCost")%> align=center width=10% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-unitCost", "Cost")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-dateInInventory")%> align=center width=15% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-dateInInventory", "Date")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-aisle")%> align=center width=5% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-aisle", "Aisle")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-row")%> align=center width=5% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-row", "Row")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-tier")%> align=center width=5% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-tier", "Tier")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-bin")%> align=center width=5% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-bin", "Bin")%></b></td>
						</tr>
<%
	InvBinLocation invBinLocation = (InvBinLocation) request.getAttribute("invBinLocation");
	String s_item_location = invBinLocation.getItemLocation();
	String s_bin_aisle = invBinLocation.getAisle();
	if (s_bin_aisle==null){s_bin_aisle = "";}
	String s_bin_bin = invBinLocation.getBin();
	if (s_bin_bin==null){s_bin_bin = "";}
	BigDecimal b_bin_cost = invBinLocation.getCost();
	if (b_bin_cost==null){b_bin_cost = new BigDecimal(0);}
	BigDecimal b_bin_IcRc = invBinLocation.getIcRc();
	BigDecimal b_bin_IcRecLine = invBinLocation.getIcRecLine();
	String s_bin_itemDate = "";
	Date d_bin_itemDate = invBinLocation.getItemDate();
	if (d_bin_itemDate != null){
		s_bin_itemDate = HiltonUtility.getFormattedDate(d_bin_itemDate, oid, userDateFormat);
	}
	String s_bin_itemNumber = invBinLocation.getItemNumber();
	String s_bin_locrow = invBinLocation.getLocrow();
	if (s_bin_locrow==null){s_bin_locrow = "";}
	String s_bin_lot = invBinLocation.getLot();
	if (s_bin_lot==null){s_bin_lot = "";}
	String s_bin_manufactNo = invBinLocation.getManufactNo();
	if (s_bin_manufactNo==null){s_bin_manufactNo = "";}
	BigDecimal b_bin_originalAlloc = invBinLocation.getOriginalAlloc();
	if (b_bin_originalAlloc==null){b_bin_originalAlloc = new BigDecimal(0);}
	BigDecimal b_bin_physActual = invBinLocation.getPhysActual();
	if (b_bin_physActual==null){b_bin_physActual = new BigDecimal(0);}
	BigDecimal b_bin_physOriginal = invBinLocation.getPhysOriginal();
	if (b_bin_physOriginal==null){b_bin_physOriginal = new BigDecimal(0);}
	BigDecimal b_bin_qtyAlloc = invBinLocation.getQtyAlloc();
	if (b_bin_qtyAlloc==null){b_bin_qtyAlloc = new BigDecimal(0);}
	BigDecimal b_bin_qtyOnHand = invBinLocation.getQtyOnHand();
	if (b_bin_qtyOnHand==null){b_bin_qtyOnHand = new BigDecimal(0);}
	String s_bin_tier = invBinLocation.getTier();
	if (s_bin_tier==null){s_bin_tier = "";}

	BigDecimal b_bin_qtyAvailable = b_bin_qtyOnHand.subtract(b_bin_qtyAlloc);
%>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-quantityOnHand")%> width=15% align=center><%=HiltonUtility.getFormattedQuantity(invBinLocation.getQtyOnHand(), oid)%>
								<tsa:hidden name="fromBin" value="<%=b_bin_IcRc%>"/>
								<tsa:hidden name="InvBinLocation_icRc2" value="<%=b_bin_IcRc%>"/>
								<tsa:hidden name="InvBinLocation_itemLocation" value="${invBinLocation.itemLocation}"/>
								<tsa:hidden name="InvBinLocation_itemNumber" value="<%=s_bin_itemNumber%>"/>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-allocated")%> width=15% align=center><%=HiltonUtility.getFormattedQuantity(invBinLocation.getQtyAlloc(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-available")%> width=15% align=center><%=HiltonUtility.getFormattedQuantity(b_bin_qtyAvailable, oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-lotNumber")%> width=10% align=center><%=s_bin_lot%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-unitCost")%> width=10% align=center><%=HiltonUtility.getFormattedDollar(invBinLocation.getCost(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-dateInInventory")%> width=15% align=center><%=s_bin_itemDate%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-aisle")%> width=5% align=center><%=s_bin_aisle%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-row")%> width=5% align=center><%=s_bin_locrow%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-tier")%> width=5% align=center><%=s_bin_tier%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-bin")%> width=5% align=center><%=s_bin_bin%></td>
						</tr>
						<tr height=30px>
							<!--<td width=10% align=right nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-quantityToMove", "Quantity to Move")%>:</td>-->
							<!--<td width=10%><input type="text"  name="qtyToMove" value="" size=15></td>-->
							<td width=10%>&nbsp;</td>
							<td width=10%>&nbsp;</td>
							<td width=10%><tsa:hidden name="qtyToMove" value="1"/></td>
							<td width=10% align=right nowrap colspan=2><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-currentLocation", "Current Location")%>:</td>
							<td width=10%><%=s_item_location%></td>
							<td width=10% align=right nowrap><a href="javascript: browseMove('toBin', '<%=invItemNumber%>', '<%=s_item_location%>', '<%=b_bin_IcRc%>'); void(0);">To Bin:</a></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-aisle")%> width=5% align=center><input type="text"  name="newAisle" value="" size=5 disabled></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-row")%> width=5% align=center><input type="text"  name="newRow" value="" size=5 disabled></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-tier")%> width=5% align=center><input type="text"  name="newTier" value="" size=5 disabled></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-bin")%> width=5% align=center>
								<input type="text"  name="newBin" value="" size=5 disabled>
								<tsa:hidden name="toBin" value=""/>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: submitThis(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/inv_bin_locations.jsp', 'InvBinLocationRetrieveByItem'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
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

	function submitThis()
	{
		if(frm.newAisle.value == "")
		{
			alert("To Bin is empty. Please enter a Bin Location");
		}
		else
		{
			frm.InvProperty_icRc.value = frm.toBin.value;
			doSubmit('/inventory/inv_bin_locations.jsp', 'InventoryMove;InvPropertyUpdate;InvBinLocationRetrieveByItem');
		}
	}

// End Hide script -->
</SCRIPT>