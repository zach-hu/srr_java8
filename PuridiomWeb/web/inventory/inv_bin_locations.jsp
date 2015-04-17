<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.math.*" %>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	invModule = propertiesManager.getProperty("Modules", "Extended Inventory", "N");
	String s_umCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_unitOfOrder"));
	String s_duomUmCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_duomUmCode"));
	String dualUmRequired = HiltonUtility.ckNull((String) request.getAttribute("dualUmRequired")) ;
	String serNoRequired = HiltonUtility.ckNull((String) request.getAttribute("serNoRequired")) ;
%>

<tsa:hidden name="fromPage" value="inv_bin_locations"/>
<tsa:hidden name="binAction" value="UPDATE"/>
<tsa:hidden name="itemAction" value="UPDATE"/>

<tsa:hidden name="InvItem_commodity" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_commodity\"))%>"/>
<tsa:hidden name="InvItem_unitOfOrder" value="<%=HiltonUtility.ckNull(s_umCode)%>"/>
<tsa:hidden name="InvItem_duomUmCode" value="<%=HiltonUtility.ckNull(s_duomUmCode)%>"/>
<tsa:hidden name="dualUmRequired" value="<%=dualUmRequired%>"/>
<tsa:hidden name="serNoRequired" value="<%=serNoRequired%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Item Bin Locations</div>
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
<table border=0 cellpadding=0 cellspacing=0 width=975px>
<tr>
<td>
	<table border=0 cellpadding=0 cellspacing=0 width=975px>
	<tr>
	<td width=900px align=center>
		<table border=1 id=invBinLocationTable cellpadding=0 cellspacing=0 width=975px>
			<tr>
<%
	String invItemBinNumber = (String) request.getAttribute("InvBinLocation_itemNumber");
	String invItemNumber = (String) request.getAttribute("InvLocation_itemNumber");
	String invItemLocation = (String) request.getAttribute("InvLocation_itemLocation");
%>
				<td class=browseHdr height=18px nowrap>&nbsp;Bin Locations for "<%=headerEncoder.encodeForHTML(invItemNumber)%>" at the <%= invItemLocation %> Location</td>
				<tsa:hidden name="InvItem_itemNumber" value="<%=invItemNumber%>"/>
				<tsa:hidden name="InvBinLocation_itemLocation" value="${InvBinLocation_itemLocation}"/>
				<tsa:hidden name="InvBinLocation_itemNumber" value="<%=invItemBinNumber%>"/>
				<tsa:hidden name="InvLocation_itemNumber" value="<%=invItemNumber%>"/>
				<tsa:hidden name="InvLocation_itemLocation" value="<%=invItemLocation%>"/>
				<tsa:hidden name="InvBinLocation_icRc" value=""/>
				<tsa:hidden name="InvProperty_icRc" value=""/>

			</tr>
			<tr>
			<td class=browseRow>
				<table border=0	cellpadding=1 cellspacing=0 width=975px>
				<tr>
				<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-aisle")%> align=center width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-aisle", "Aisle")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-row")%> align=center width=5%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-row", "Row")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-tier")%> align=center width=5%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-tier", "Tier")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-bin")%> align=center width=5%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-bin", "Bin")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-unitOfMeasure")%> align=right width=7%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "locbrw-inv-unitOfMeasure", "UOM")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-quantityOnHand")%> align=right width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-quantityOnHand", "On Hand")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-allocated")%> align=right width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-allocated", "Allocated")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-available")%> align=right width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-available", "Available")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-lotNumber")%> align=center width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-lotNumber", "Lot")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-conditionCode")%> align=right width=8%><b><tsa:label labelName="inv-chargeCode" defaultString="Condition Code" checkRequired="false"/></b></td>
				<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-unitCost")%> align=right width=7%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-unitCost", "Unit Cost")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-dateInInventory")%> align=center width=12%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-dateInInventory", "Item Date")%></b></td>
				<td id="editLabel" align=center width=5%><b>Edit</b></td>
<% if (invModule.indexOf('Y')>=0) { %>
				<td id="moveLabel" align=center width=6%><b>Move&nbsp;</b></td>
				<td id="deleteLabel" align=center width=6%><b>Delete&nbsp;</b></td>
<% }else{ %>
				<td id="deleteLabel"align=center width=6%><b>Delete&nbsp;</b></td>
				<td align=center width=6%>&nbsp;</td>
<%	} %>
<% 	if (serNoRequired.indexOf('Y')>=0) { %>
				<td align=center width=6% nowrap><b>Serial #&nbsp;</b></td>
<% 	} %>
				<td <%=HtmlWriter.isVisible(oid, "bin-inv-audittrail")%> align=center width=6%><b>Audit</b></td>
				</tr>

<%
	InvItem invItem = (InvItem) request.getAttribute("invItem") ;
	List binList = (List)request.getAttribute("invBinLocationList");
	if (binList != null)
	{
		String rowClass = "browseRow";
		for(int i = 0; i < binList.size(); i++)
		{
			InvBinLocation invBinLocation = (InvBinLocation) binList.get(i);

			String s_bin_bin = invBinLocation.getBin();
			if (s_bin_bin==null){s_bin_bin = "";}

			String s_bin_HdrIc = invBinLocation.getHdrIc();
			BigDecimal b_bin_IcRc = invBinLocation.getIcRc();
			BigDecimal b_bin_IcRecLine = invBinLocation.getIcRecLine();

			String s_bin_itemLocation = invBinLocation.getItemLocation();
			String s_bin_itemNumber = invBinLocation.getItemNumber();

			String s_bin_manufactNo = invBinLocation.getManufactNo();
			if (s_bin_manufactNo==null){s_bin_manufactNo = "";}
			BigDecimal b_bin_originalAlloc = invBinLocation.getOriginalAlloc();
			if (b_bin_originalAlloc==null){b_bin_originalAlloc = new BigDecimal(0);}
			BigDecimal b_bin_physActual = invBinLocation.getPhysActual();
			if (b_bin_physActual==null){b_bin_physActual = new BigDecimal(0);}
			BigDecimal b_bin_physOriginal = invBinLocation.getPhysOriginal();
			if (b_bin_physOriginal==null){b_bin_physOriginal = new BigDecimal(0);}


			BigDecimal bd_qtyAlloc = HiltonUtility.getFormattedQuantity(invBinLocation.getQtyAlloc(), oid);
			BigDecimal bd_qtyOnHand = HiltonUtility.getFormattedQuantity(invBinLocation.getQtyOnHand(), oid);

			BigDecimal bd_qtyAvailable = bd_qtyOnHand.subtract(bd_qtyAlloc);
			String s_conditionCode = invBinLocation.getUdf1Code();

			BigDecimal bd_duomQtyAlloc = HiltonUtility.getFormattedQuantity(invBinLocation.getDuomQtyAlloc(), oid);
			BigDecimal bd_duomQtyOnHand = HiltonUtility.getFormattedQuantity(invBinLocation.getDuomQtyOnHand(), oid);

			BigDecimal bd_duomQtyAvailable = bd_duomQtyOnHand.subtract(bd_duomQtyAlloc);


			String s_bin_udf1 = invBinLocation.getUdf1Code();
			if (s_bin_udf1==null){s_bin_udf1 = "";}
			String s_bin_udf2 = invBinLocation.getUdf2Code();
			if (s_bin_udf2==null){s_bin_udf2 = "";}
			String s_bin_udf3 = invBinLocation.getUdf3Code();
			if (s_bin_udf3==null){s_bin_udf3 = "";}
			String s_bin_udf4 = invBinLocation.getUdf4Code();
			if (s_bin_udf4==null){s_bin_udf4 = "";}
			String s_bin_udf5 = invBinLocation.getUdf5Code();
			if (s_bin_udf5==null){s_bin_udf5 = "";}
			String s_bin_vendorId = invBinLocation.getVendorId();
			if (s_bin_vendorId==null){s_bin_vendorId = "";}
			pageContext.setAttribute("i", i);
%>
						<tr class=<%=rowClass%>>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-aisle")%> width=8% align=center><%=HiltonUtility.ckNull(invBinLocation.getAisle())%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-row")%> width=5% align=center><%=HiltonUtility.ckNull(invBinLocation.getLocrow())%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-tier")%> width=5% align=center><%=HiltonUtility.ckNull(invBinLocation.getTier())%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-bin")%> width=5% align=center><%=HiltonUtility.ckNull(invBinLocation.getBin())%></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-unitOfMeasure")%> width=7% align=right nowrap><%=HiltonUtility.ckNull(invItem.getUnitOfOrder())%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-quantityOnHand")%> width=8% align=right>
								<%=bd_qtyOnHand%>
								<tsa:hidden id="invLocationBin_${i}" name="invLocationBin_${i}" value="<%=b_bin_IcRc%>"/>
								<tsa:hidden id="qtyOnHand_${i}" name="qtyOnHand_${i}" value="<%=bd_qtyOnHand%>"/>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-allocated")%> width=8% align=right><%=bd_qtyAlloc%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-available")%> width=8% align=right><%=bd_qtyAvailable%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-lotNumber")%> width=8% align=center><%=HiltonUtility.ckNull(invBinLocation.getLot())%>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-conditionCode")%> width=8% align=right><%=s_conditionCode%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-unitCost")%> width=7% align=right><%=HiltonUtility.getFormattedPrice(invBinLocation.getCost(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-dateInInventory")%> width=12% align=center><%=HiltonUtility.getFormattedDate(invBinLocation.getItemDate(), oid, userDateFormat)%></td>
							<td  id="editItem" width=5% align=center><a href="javascript: viewBin(<%=i%>);"><IMG  SRC="<%=contextPath%>/images/cmd_search_item.gif" BORDER="0" ALT="Bin Location Adjustment"></a></td>
<% 	if (invModule.indexOf('Y')>=0) { %>
							<td id="moveItem" width=6% align=center nowrap><a href="javascript: moveItems(<%=i%>);"><IMG  SRC="<%=contextPath%>/images/square_arrows_right.gif" BORDER="0" ALT="Move"></a></td>
							<td id="deleteBin" width=6% align=center><a href="javascript: deleteBin(<%=i%>);"><IMG  SRC="<%=contextPath%>/images/delete.gif" BORDER="0" ALT="Delete Bin Location"></a></td>
<% 	} else{ %>
							<td id="deleteBin" width=6% align=center><a href="javascript: deleteBin(<%=i%>);"><IMG  SRC="<%=contextPath%>/images/delete.gif" BORDER="0" ALT="Delete Bin Location"></a></td>
							<td  width=6% align=center nowrap>&nbsp</td>
<% 	} %>
<% 	if (serNoRequired.indexOf('Y')>=0) { %>
							<td id="itemProperty" width=6% align=center><a href="javascript: viewProperty(<%=i%>);"><IMG  SRC="<%=contextPath%>/images/asset3.gif" BORDER="0" ALT="Property Adjustment"></a></td>
<% 	} %>
							<td  <%=HtmlWriter.isVisible(oid, "bin-inv-audittrail")%> width=6% align=center><a href="javascript: viewAuditTrail('<%=HiltonUtility.ckNull(invBinLocation.getAisle())%>');" title="Audit Trail">Audit</a></td>

						</tr>
<%		if (dualUmRequired.equalsIgnoreCase("Y")) { %>
						<tr class=<%=rowClass%>>
							<td colspan=4>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-duomUmCode")%> width=7% align=right nowrap><%=HiltonUtility.ckNull(invItem.getDuomUmCode())%></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-duomQtyOnHand")%> width=9% align=right nowrap><%=bd_duomQtyOnHand%></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-duomQtyAlloc")%> width=9% align=right nowrap><%=bd_duomQtyAlloc%></td>
							<td <%=HtmlWriter.isVisible(oid, "bin-locbrw-inv-duomAvailable")%> width=9% align=right><%=bd_duomQtyAvailable%></td>
							<td colspan=9>&nbsp;</td>
						</tr>
<% 		}

		if (rowClass.equals("browseRow"))
		{
			rowClass = "summary";
		} else {
			rowClass = "browseRow";
		}%>
<%	}


		if (binList.size() <= 0)
		{ %>
						<tr><td align=center>There are currently no bins available.</td></tr>
<%	}


	} %>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<tsa:hidden name="isShelfLifeRqd" value="<%=HiltonUtility.ckNull(invItem.getShelfLifeRqd())%>"/>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=820px>
<tr>
	<td id="addBin" width=50% align=center><div id="pxbutton"><a href="javascript: addBin(); void(0);">Add</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_location.jsp','InvLocationRetrieveById'); void(0);">Return</a></div></td>
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
	setNavCookie("/inventory/inv_bin_locations.jsp", "DoNothing", "Bin Locations");
	frm = document.purchaseform;
	var itemNumber = '<%=invItemNumber%>' ;
	var itemLocation = '<%=invItemLocation%>' ;

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

	function viewBin(row) {
		var num = document.getElementById("invLocationBin_" + row);
		frm.InvBinLocation_icRc.value = num.value;
		frm.binAction.value = "UPDATE";
		doSubmit('/inventory/inv_bin.jsp', 'InvBinLocationRetrieveById;InvItemRetrieveById');
	}

	function viewProperty(row) {
		var num = document.getElementById("invLocationBin_" + row);
		frm.InvBinLocation_icRc.value = num.value;
		frm.binAction.value = "PROPERTY";
		doSubmit('/inventory/inv_property_list.jsp', 'InvPropertyRetrieveByIcRc');
	}

	function moveItems(row) {
		var loc = document.getElementById("invLocationBin_" + row);
		frm.InvBinLocation_icRc.value = loc.value;
		doSubmit('/inventory/ext_inv_move.jsp','InvBinLocationRetrieveById');
	}

	function addBin()
	{
		frm.binAction.value = "CREATE";
		doSubmit('/inventory/inv_bin.jsp', 'DoNothing');
	}

	function deleteBin(row) {
		var qtyOnHand = document.getElementById("qtyOnHand_" + row);
		if (eval(qtyOnHand.value > 0))
		{
			alert("Bin Location quantity on hand is not zero.  Cannot delete!");
		}
		else
		{
			var num = document.getElementById("invLocationBin_" + row);
			frm.InvBinLocation_icRc.value = num.value;
			if (confirm("Are you sure you want to delete this bin location?"))
			{
				doSubmit('/inventory/inv_bin_locations.jsp','InvBinLocationDelete');
			}
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

	function viewAuditTrail(aisle)
	{
		popupParameters = popupParameters + "browseName=invbinaudittrail";
		popupParameters = popupParameters + ";colname=InvBinAudit_itemNumber;operator==;filter_txt=" + itemNumber + ";originalFilter=N;sort=N;logicalOperator=AND;colname=InvBinAudit_itemLocation;operator==;filter_txt=" + itemLocation + ";originalFilter=N;sort=N;logicalOperator=AND;colname=InvBinAudit_aisle;operator==;filter_txt=" + aisle + ";logicalOperator=AND;originalFilter=N;sort=N;"
		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}

	function thisLoad()
	{
		var invAccess = <%=role.getAccessRights("INV")%>;
		f_StartIt();
		if (invAccess <= 1)
		{
			$('#addBin, #editItem, #moveItem, #deleteBin, #itemProperty, #editLabel, #deleteLabel, #moveLabel').hide();
		}
	}

// End Hide script -->
</SCRIPT>