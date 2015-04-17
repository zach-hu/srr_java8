<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%@ page import="java.math.BigDecimal" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String quantityDecimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String itemNumber = (String) request.getAttribute("InvBinLocation_itemNumber");
	String unitCost = (String) request.getAttribute("unitCost");
	if (HiltonUtility.isEmpty(unitCost))
	{
		unitCost = "0";
	}
%>

<iframe id="getInfoFrame" name="getInfoFrame" src="<%=contextPath%>/system/processing.jsp" frameborder="0" marginheight="0" marginwidth="0" style="position: absolute; border: 1px solid #C0C0C0; display: none; visibility:hidden;"></iframe>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="binAction" value="UPDATE"/>
<tsa:hidden name="itemAction" value="UPDATE"/>

<tsa:hidden name="addInvBinLocation" value="Y"/>

<table width=775px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>&nbsp;Item Bin Locations for "<%=itemNumber%>"</div>
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

<br><br>

<table border=0 cellpadding=0 cellspacing=0 width=775px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=775px>
		<tr>
			<td width=775px align=center>
				<table border=1 id=invBinLocationTable cellpadding=0 cellspacing=0 width=775px>
				<tr>
					<td class=browseRow>
						<table border=0 cellpadding=1 cellspacing=0 width=100% class=browseHdr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-lotNumber")%> align=center width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-lotNumber", "Lots")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-aisle")%> align=center width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-aisle", "Aisle")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-row")%> align=center width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-row", "Row")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-tier")%> align=center width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-tier", "Tier")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-bin")%> align=center width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-bin", "Bin")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-unitCost")%> align=right width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-unitCost", "Unit Cost")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-quantityOnHand")%> align=right width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-quantityOnHand", "On Hand")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-allocated")%> align=right width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-allocated", "Allocated")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-available")%> align=right width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-available", "Available")%></b></td>
							<td width=3% class=browseHdr>&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=0 width=100% id=binLocations>
<%
		int invBinLocationListCount = 0;
		List invBinLocationList = (List)request.getAttribute("invBinLocationList");
		if (invBinLocationList != null && invBinLocationList.size() > 0)
		{
			for (int i = 0; i < invBinLocationList.size(); i++)
			{
				InvBinLocation invBinLocation = (InvBinLocation) invBinLocationList.get(i);

				BigDecimal bdCost = HiltonUtility.getFormattedDollar(invBinLocation.getCost(), oid);
				BigDecimal bdQtyOnHand = HiltonUtility.getFormattedQuantity(invBinLocation.getQtyOnHand(), oid);
				BigDecimal bdQtyAlloc = HiltonUtility.getFormattedQuantity(invBinLocation.getQtyAlloc(), oid);
				BigDecimal bdQtyAvailable = bdQtyOnHand.subtract(bdQtyAlloc);
%>
						<tr>
							<td align=center>
								<tsa:hidden name="InvBinLocation_tempIc" value="0"/>
								<tsa:hidden name="InvBinLocation_itemLocation" value="${InvBinLocation_itemLocation}"/>
								<tsa:hidden name="InvBinLocation_itemNumber" value="<%=itemNumber%>"/>
								<table border=0 cellspacing=0 cellpadding=1 width=100%>
								<tr>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-lotNumber")%> align=left width=8%><%=invBinLocation.getLot()%><tsa:hidden name="InvBinLocation_lot" value="<%=invBinLocation.getLot()%>"/></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-aisle")%> align=left width=8%><tsa:hidden name="InvBinLocation_aisle" value="<%=invBinLocation.getAisle()%>"/><%=invBinLocation.getAisle()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-row")%> align=left width=8%><tsa:hidden name="InvBinLocation_locrow" value="<%=invBinLocation.getLocrow()%>"/><%=invBinLocation.getLocrow()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-tier")%> align=left width=8%><tsa:hidden name="InvBinLocation_tier" value="<%=invBinLocation.getTier()%>"/><%=invBinLocation.getTier()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-bin")%> align=left width=8%><tsa:hidden name="InvBinLocation_bin" value="<%=invBinLocation.getBin()%>"/><%=invBinLocation.getBin()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-unitCost")%> align=right width=10%><%=bdCost%><tsa:hidden name="InvBinLocation_cost" value="<%=invBinLocation.getCost()%>"/></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-quantityOnHand")%> align=right width=10%><%=bdQtyOnHand%><tsa:hidden name="InvBinLocation_qtyOnHand" value="<%=invBinLocation.getQtyOnHand()%>"/></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-allocated")%> align=right width=10%><%=bdQtyAlloc%><tsa:hidden name="InvBinLocation_qtyAlloc" value="<%=invBinLocation.getQtyAlloc()%>"/></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-available")%> align=right width=10%><%=bdQtyAvailable%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-details")%> width=3% align=right><div id="detailLink<%=i%>"><a href="javascript: viewDetails(<%=i%>); void(0);"><img src="<%=contextPath%>/images/plus.gif" border=0 class=processOnImg></a></div></td>
								</tr>
								<tr>
									<td>
										<tsa:hidden name="InvBinLocation_duomQtyReceived" value="0"/>
										<tsa:hidden name="InvBinLocation_duomQtyOnHand" value="0"/>
									</td>
								</tr>
								<tr>
									<td colspan=10>
										<div id="binDetails<%=i%>" style="visibility:hidden; display:none; width:100%;" class=browseRow>
										<hr size=0 width=98%>
										<table border=0 cellspacing=0 cellpadding=2 width=100%>
										<tr>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-supplier")%> width=14% align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-supplier", "Supplier", false)%>:</td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-supplier")%> width=22% nowrap><%=invBinLocation.getVendorId()%>&nbsp;<tsa:hidden name="InvBinLocation_vendorId" value="<%=invBinLocation.getVendorId()%>"/></td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN01")%> width=20% align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BN01", "Inventory Bin UDF 1", false)%>:</td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN01")%> width=12% nowrap><%=invBinLocation.getUdf1Code()%>&nbsp;<tsa:hidden name="InvBinLocation_udf1Code" value="<%=invBinLocation.getUdf1Code()%>"/></td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN03")%> width=20% align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BN03", "Inventory Bin UDF 3", false)%>:</td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN03")%> width=12% nowrap><%=invBinLocation.getUdf3Code()%>&nbsp;<tsa:hidden name="InvBinLocation_udf3Code" value="<%=invBinLocation.getUdf3Code()%>"/></td>
										</tr>
										<tr>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-manufacturer")%> width=14% align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "manufacturer", "Manufacturer", false)%>:</td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-manufacturer")%> width=22% nowrap><%=invBinLocation.getManufactNo()%>&nbsp;<tsa:hidden name="InvBinLocation_manufactNo" value="<%=invBinLocation.getManufactNo()%>"/></td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN02")%> width=20% align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BN02", "Inventory Bin UDF 2", false)%>:</td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN02")%> width=12% nowrap><%=invBinLocation.getUdf2Code()%>&nbsp;<tsa:hidden name="InvBinLocation_udf2Code" value="<%=invBinLocation.getUdf2Code()%>"/></td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN04")%> width=20% align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BN04", "Inventory Bin UDF 4", false)%>:</td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN04")%> width=12% nowrap><%=invBinLocation.getUdf4Code()%>&nbsp;<tsa:hidden name="InvBinLocation_udf4Code" value="<%=invBinLocation.getUdf4Code()%>"/></td>
										</tr>
										<tr>
											<td colspan=4>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN05")%> width=20% align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BN05", "Inventory Bin UDF 5", false)%>:</td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN05")%> width=12% nowrap><%=invBinLocation.getUdf5Code()%>&nbsp;<tsa:hidden name="InvBinLocation_udf5Code" value="<%=invBinLocation.getUdf5Code()%>"/></td>
										</tr>
										</table>
										<hr size=0 width=98%>
										</div>
									</td>
								</tr>
								</table>
							</td>
						</tr>
<%				invBinLocationListCount ++;
			} %>
						</table>
<%	if (invBinLocationList.size() <= 0){ %>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr><td align=center>There are currently no bins available.  Click below to add a new bin location.</td></tr>
						</table>
<%	}
	} %>
					</td>
				</tr>
				</table>
				<table border=0 cellspacing=0 cellpadding=2 width=100%>
				<tr><td align=left nowrap>&nbsp;<a href="javascript: addBinLocation(); void(0);">Add Bin Location</a></td></tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=775px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: updateBins(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: returnThis(); void(0);">Return</a></div></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function addBinLocation() {
		var myTable = document.getElementById("binLocations");
		var rowCount = myTable.rows.length;
		var myRow;
		var myCell;
		var binLocationTableHTML = "";

		binLocationTableHTML = "<input type=hidden name=\"InvBinLocation_tempIc\" value=\"0\">" +
				"<input type=hidden name=\"InvBinLocation_itemLocation\" value=\"${esapi:encodeForJavaScript(InvBinLocation_itemLocation)}\">" +
				"<input type=hidden name=\"InvBinLocation_itemNumber\" value=\"<%=itemNumber%>\">" +
				"<table border=0 cellpadding=1 cellspacing=0 width=100% id=binLocation" + rowCount + "></table>";

		myRow = myTable.insertRow(rowCount);

		myCell = myRow.insertCell();
		myCell.width = "100%";
		myCell.align = "center";
		myCell.innerHTML = binLocationTableHTML;

		myTable = document.getElementById("binLocation" + rowCount);

		myRow = myTable.insertRow();

		myCell = myRow.insertCell();
		myCell.width = "8%";
		myCell.align = "left";
		myCell.innerHTML = "<input type=text name=\"InvBinLocation_lot\" value=\"\" size=10 onchange='upperCase(this); void(0);'>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-lotNumber")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-lotNumber")%>

		myCell = myRow.insertCell();
		myCell.width = "8%";
		myCell.align = "left";
		myCell.innerHTML = "<input type=text name=\"InvBinLocation_aisle\" value=\"\" size=10 onchange='upperCase(this); void(0);'>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-aisle")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-aisle")%>

		myCell = myRow.insertCell();
		myCell.width = "8%";
		myCell.align = "left";
		myCell.innerHTML = "<input type=text name=\"InvBinLocation_locrow\" value=\"\" size=10 onchange='upperCase(this); void(0);'>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-row")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-row")%>

		myCell = myRow.insertCell();
		myCell.width = "8%";
		myCell.align = "left";
		myCell.innerHTML = "<input type=text name=\"InvBinLocation_tier\" value=\"\" size=10 onchange='upperCase(this); void(0);'>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-tier")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-tier")%>

		myCell = myRow.insertCell();
		myCell.width = "8%";
		myCell.align = "left";
		myCell.innerHTML = "<input type=text name=\"InvBinLocation_bin\" value=\"\" size=10>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-bin")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-bin")%>

		myCell = myRow.insertCell();
		myCell.width = "10%";
		myCell.align = "right";
		myCell.innerHTML = "<%=HiltonUtility.getFormattedQuantity(unitCost, oid)%><input type=hidden name=\"InvBinLocation_cost\" value=\"<%=HiltonUtility.getFormattedQuantity(unitCost, oid)%>\">";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-unitCost")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-unitCost")%>

		myCell = myRow.insertCell();
		myCell.width = "10%";
		myCell.align = "right";
		myCell.innerHTML = "<%=HiltonUtility.getFormattedQuantity("0", oid)%><input type=hidden name=InvBinLocation_qtyOnHand value=\"0.00\">";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-quantityOnHand")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-quantityOnHand")%>

		myCell = myRow.insertCell();
		myCell.width = "10%";
		myCell.align = "right";
		myCell.innerHTML = "<%=HiltonUtility.getFormattedQuantity("0", oid)%><input type=hidden name=InvBinLocation_qtyAlloc value=\"0.00\">";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-allocated")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-allocated")%>

		myCell = myRow.insertCell();
		myCell.width = "10%";
		myCell.align = "right";
		myCell.innerHTML = "<%=HiltonUtility.getFormattedQuantity("0", oid)%>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-available")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-available")%>

		myCell = myRow.insertCell();
		myCell.width = "3%";
		myCell.align = "right";
		myCell.innerHTML = "<div id=\"detailLink" + rowCount + "\"><a href=\"javascript: viewDetails(" + rowCount + "); void(0);\"><img src=\"<%=contextPath%>/images/plus.gif\" border=0 class=processOnImg></a></div>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-details")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-details")%>

		myRow = myTable.insertRow();
		myCell = myRow.insertCell();
		myCell.innerHTML = "<input type=hidden name=\"InvBinLocation_duomQtyReceived\" value=\"0\"><input type=hidden name=\"ReceiptLine_duomUmCode\" value=\"\"><input type=hidden name=InvBinLocation_duomQtyOnHand value=\"\">";

		myRow = myTable.insertRow();

		myCell = myRow.insertCell();
		myCell.colSpan = 10;
		myCell.align = "center";
		myCell.innerHTML = "<div id=\"binDetails" + rowCount + "\" style=\"visibility:hidden; display:none; width:100%;\" class=browseRow><hr size=0 width=98%><table border=0 cellspacing=0 cellpadding=1 width=100% id=binDetailRows" + rowCount + "></table><hr size=0 width=98%></div>";

		myTable = document.getElementById("binDetailRows" + rowCount);

		myRow = myTable.insertRow();

		myCell = myRow.insertCell();
		myCell.align = "right";
		myCell.width = "14%";
		myCell.className = "label";
		myCell.innerHTML = "<a href=\"javascript: browseSupplier('InvBinLocation_vendorId', 'supplier', '" + rowCount + "'); void(0);\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-supplier", "Supplier", false)%>:</a>&nbsp;";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-supplier")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-supplier")%>

		myCell = myRow.insertCell();
		myCell.width = "22%";
		myCell.innerHTML = "<input type=text name=\"InvBinLocation_vendorId\" value=\"\" size=24>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-supplier")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-supplier")%>

		myCell = myRow.insertCell();
		myCell.align = "right";
		myCell.width = "20%";
		myCell.className = "label";
		myCell.innerHTML = "<a href=\"javascript: browseLookup('InvBinLocation_udf1Code', 'BN01', '" + rowCount + "'); void(0);\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-BN01", "UDF 1")%>:</a>&nbsp;";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-BN01")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-BN01")%>

		myCell = myRow.insertCell();
		myCell.width = "12%";
		myCell.innerHTML = "<input type=text name=\"InvBinLocation_udf1Code\" value=\"\" size=12>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-BN01")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-BN01")%>

		myCell = myRow.insertCell();
		myCell.align = "right";
		myCell.width = "20%";
		myCell.className = "label";
		myCell.innerHTML = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-BN03", "Inventory Bin UDF 3", false)%>:";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-BN03")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-BN03")%>

		myCell = myRow.insertCell();
		myCell.width = "12%";
		myCell.innerHTML = "<input type=text name=\"InvBinLocation_udf3Code\" value=\"\" size=12>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-BN03")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-BN03")%>

		myRow = myTable.insertRow();

		myCell = myRow.insertCell();
		myCell.width = "14%";
		myCell.align = "right";
		myCell.className = "label";
		myCell.innerHTML = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-manufacturer", "Manufacturer", false)%>:";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-manufacturer")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-manufacturer")%>

		myCell = myRow.insertCell();
		myCell.width = "22%";
		myCell.innerHTML = "<input type=text name=\"InvBinLocation_manufactNo\" value=\"\" size=24>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-manufacturer")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-manufacturer")%>

		myCell = myRow.insertCell();
		myCell.width = "20%";
		myCell.align = "right";
		myCell.className = "label";
		myCell.innerHTML = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-BN02", "Inventory Bin UDF 2", false)%>:";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-BN02")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-BN02")%>

		myCell = myRow.insertCell();
		myCell.width = "12%";
		myCell.innerHTML = "<input type=text name=\"InvBinLocation_udf2Code\" value=\"\" size=12>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-BN02")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-BN02")%>

		myCell = myRow.insertCell();
		myCell.width = "20%";
		myCell.align = "right";
		myCell.className = "label";
		myCell.innerHTML = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-BN04", "Inventory Bin UDF 4", false)%>:";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-BN04")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-BN04")%>

		myCell = myRow.insertCell();
		myCell.width = "12%";
		myCell.innerHTML = "<input type=text name=\"InvBinLocation_udf4Code\" value=\"\" size=12>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-BN04")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-BN04")%>

		myRow = myTable.insertRow();

		myCell = myRow.insertCell();
		myCell.colSpan = 4;

		myCell = myRow.insertCell();
		myCell.width = "20%";
		myCell.align = "right";
		myCell.className = "label";
		myCell.innerHTML = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-BN05", "Inventory Bin UDF 5", false)%>:";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-BN05")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-BN05")%>

		myCell = myRow.insertCell();
		myCell.width = "12%";
		myCell.innerHTML = "<input type=text name=\"InvBinLocation_udf5Code\" value=\"\" size=12>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-BN05")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-BN05")%>

		myRow = myTable.insertRow();

		myCell = myRow.insertCell();
		myCell.colSpan = 8;
		myCell.align = "right";
	}

	function hideDetails(x) {
		setInnerHTML("detailLink" + x, "<a href='javascript: viewDetails(" + x + "); void(0);'><img src=\"<%=contextPath%>/images/plus.gif\" border=0 class=processOnImg alt=\"View Details\"></a>");
		hideArea("binDetails" + x);
	}

	function viewDetails(x) {
		setInnerHTML("detailLink" + x, "<a href='javascript: hideDetails(" + x + "); void(0);'><img src=\"<%=contextPath%>/images/minus.gif\" border=0 class=processOnImg alt=\"Hide Details\"></a>");
		displayArea("binDetails" + x);
	}

	function updateBins() {
		var myTable = document.getElementById("binLocations");
		var rowCount = myTable.rows.length;

		if (rowCount == 1)
		{
			if (isEmpty(frm.InvBinLocation_aisle.value + frm.InvBinLocation_locrow.value + frm.InvBinLocation_tier.value + frm.InvBinLocation_bin.value)) {
				alert("At least one of the following need data: Aisle, Row, Tier, or Bin.");
				return false;
			}
		}
		else
		{
			for (var i = 0; i < rowCount; i++)
			{
				if (i >= <%=invBinLocationListCount%>)
				{
					if (isEmpty(frm.InvBinLocation_aisle[i].value + frm.InvBinLocation_locrow[i].value + frm.InvBinLocation_tier[i].value + frm.InvBinLocation_bin[i].value)) {
						alert("At least one of the following need data: Aisle, Row, Tier, or Bin.");
						return false;
					}
				}
			}
		}

		doSubmit('inventory/inv_return_bin_locations_temp.jsp','InvBinLocationAddTempList');
	}

	function browseLookup(formField, udf, row)
	{
		xml = "stdtable";
		popupParameters =  "fromPage=Receipt Bins;formField=" + formField +";browseName=" + xml + ";allowBrowse=true;" ;
		popupParameters = popupParameters + "tableType=" + udf + ";";
		popupParameters = popupParameters + "colname=StdTable_id_tableType;operator==;filter_txt=" + udf + ";logicalOperator=AND;originalFilter=Y;sort=N;";
		popupParameters = popupParameters + "index=" + row;

		doSubmitToPopup('/browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=800px', 'HEIGHT=500px');
	}

	function browseSupplier(formField, xml, row)
	{
		popupParameters = "formField=" + formField + ";browseName=" + xml + ";allowBrowse=true;";
		popupParameters = popupParameters + "index=" + row;

		doSubmitToPopup('/browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=800px', 'HEIGHT=500px');
	}

	function returnThis()
	{
		window.parent.browseMove('toBin', '<%=itemNumber%>', '${esapi:encodeForJavaScript(InvBinLocation_itemLocation)}', '0');
	}

	function addUp(obj)
	{
		var qtyDecimals = <%=Integer.valueOf(quantityDecimals).intValue()%>;
		obj.value = nformat(eval(nfilter(obj)), qtyDecimals);
	}

// End Hide script -->
</SCRIPT>