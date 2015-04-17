<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/inventory/inv_property_checklist_fields.jsp" %>
<%@ page import="java.math.*" %>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
	String invItemNumber = (String) request.getAttribute("InvLocation_itemNumber");
	String invItemLocation = (String) request.getAttribute("InvLocation_itemLocation");

	String s_umCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_unitOfOrder"));
	String s_duomUmCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_duomUmCode"));
	String dualUmRequired = HiltonUtility.ckNull((String) request.getAttribute("dualUmRequired")) ;
	String serNoRequired = HiltonUtility.ckNull((String) request.getAttribute("serNoRequired")) ;

	String qtyToMove = (String) request.getAttribute("qtyToMove");

	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
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
<tsa:hidden name="InvBinLocation_icRc" value="${invBinLocation.icRc}"/>
<tsa:hidden name="binAction" value=""/>
<tsa:hidden name="fromPage" value="ext_inv_move"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Move Inventory Item</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>
<br>
<table border=0 cellpadding=0 cellspacing=0 width=820px>
<tr>
	<td width=820px align=left>
		<table border=0 cellpadding=0 cellspacing=0 width=820px>
		<tr>
			<td width=50% align=left valign=top>
				<table id=invLocationsTable border=1 cellspacing=0 cellpadding=0 width=820px class=browseHdr>
				<tr>
					<td class=browseHdr height=18px nowrap>&nbsp;Move Item <%=invItemNumber%> Location <%=invItemLocation %>"</td>
				</tr>
				<tr>
					<td>
						<table border=0>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-quantityOnHand")%> align=center width=10% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-quantityOnHand", "On Hand")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-allocated")%> align=center width=10% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-allocated", "Allocated")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-available")%> align=center width=10% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-available", "Available")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-lotNumber")%> align=center width=10% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-lotNumber", "Lot")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-unitCost")%> align=center width=10% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-unitCost", "Cost")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-dateInInventory")%> align=center width=15% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-dateInInventory", "Date")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-aisle")%> align=center width=10% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-aisle", "Aisle")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-row")%> align=center width=10% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-row", "Row")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-tier")%> align=center width=10% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-tier", "Tier")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-bin")%> align=center width=10% valign=bottom><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-bin", "Bin")%></b></td>
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
	String s_bin_itemLocation = invBinLocation.getItemLocation();
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
								<tsa:hidden name="InvBinLocation_itemLocation" value="<%=s_bin_itemLocation%>"/>
								<tsa:hidden name="InvBinLocation_itemNumber" value="${invBinLocation.itemNumber}"/>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-allocated")%> width=10% align=center><%=HiltonUtility.getFormattedQuantity(invBinLocation.getQtyAlloc(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-available")%> width=10% align=center><%=HiltonUtility.getFormattedQuantity(b_bin_qtyAvailable, oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-lotNumber")%> width=10% align=center><%=s_bin_lot%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-unitCost")%> width=10% align=center><%=HiltonUtility.getFormattedDollar(invBinLocation.getCost(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-dateInInventory")%> width=15% align=center><%=s_bin_itemDate%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-aisle")%> width=10% align=center><%=s_bin_aisle%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-row")%> width=10% align=center><%=s_bin_locrow%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-tier")%> width=10% align=center><%=s_bin_tier%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-bin")%> width=10% align=center><%=s_bin_bin%></td>
						</tr>
						<tr height=20px>
							<td width=10% align=right nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-quantityToMove", "Quantity to Move")%>:</td>
							<td width=10%><input type="text" name="qtyToMove" value="${qtyToMove}" size=10 style="text-align:right" onchange="addUp(this);"></td>
							<td width=10%><input type=text size=10 name="InvLocation_unitOfOrder" value="<%=s_umCode%>" disabled></td>
							<td colspan=2>
								<% if (serNoRequired.equals("Y")) { %>
									<a href="javascript: viewProperty();">
										<IMG SRC="<%=contextPath%>/images/asset3.gif" BORDER="0" ALT="Property Serial Numbers">
											Serial Numbers
									</a>
								<% } else { %>
									&nbsp;
								<% } %>
							</td>
							<td width=5% align=right nowrap><a href="javascript: browseMove('toBin', '<%=invItemNumber%>', '<%=s_item_location%>', '<%=b_bin_IcRc%>'); void(0);">To Bin:</a></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-aisle")%> width=8% align=center><input type="text"  name="newAisle" value="${newAisle}" size=10 disabled></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-row")%> width=8% align=center><input type="text"  name="newRow" value="${newRow}" size=10 disabled></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-tier")%> width=8% align=center><input type="text"  name="newTier" value="${newTier}" size=10 disabled></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-bin")%> width=8% align=center>
								<input type="text"  name="newBin" value="" size=10 disabled>
								<tsa:hidden name="toBin" value=""/>
							</td>
						</tr>
		<% if (dualUmRequired.equalsIgnoreCase("Y")) { %>
						<tr height=20px>
							<td width=10% align=right nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-secondaryQuantityToMove", "Secondary Quantity to Move")%>:</td>
							<td width=10%><input type="text"  name="duomQtyToMove" value="${duomQtyToMove}" size=10></td>
							<td width=10%><input type=text size=10 name="InvLocation_duomUmCode" value="<%=s_duomUmCode%>"  disabled></td>
						</tr>
		<% } %>
						</table>
					</td>
				</tr>

				</table>
			</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=820px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis('/inventory/inv_bin_locations.jsp', 'DisbSetProperty;InventoryMove;InvBinLocationRetrieveByItem'); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_bin_locations.jsp', 'InvBinLocationRetrieveByItem'); void(0);">Return</a></div></td>
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
		doSubmit('inventory/std_item_move.jsp','DisbSetProperty;InvLocationRetrieveById');
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

	function viewProperty() {
		frm.binAction.value = "PROPERTY";
		doSubmit('/inventory/inv_property_checklist.jsp', 'DisbSetProperty;InvPropertyRetrieveByIcRc');
	}

	function submitThis(page, handlers)
	{
		var message = "";

		if (frm.qtyToMove.value == "") {
			message = message + "\"<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-quantityToMove", "Quantity to Move")%>\" is required\n";
		}
		if (frm.toBin.value == "") {
			message = message + "\"To Bin\" is required\n";
		}

		if (message == "") {
			doSubmit(page, handlers);
		} else {
			alert(message);
		}
	}

	function addUp(quantity)
	{
		var qty_dec = <%=Integer.valueOf(s_quantity_decimals).intValue()%>;
		quantity.value = nformat(eval(nfilter(quantity)),qty_dec);
	}

// End Hide script -->
</SCRIPT>