<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="java.math.BigDecimal" %>
<%
	InvItem invItem = (InvItem) request.getAttribute("invItem");
	List binList = (List)request.getAttribute("invBinLocationList");
	
	String invItemNumber = (String) request.getAttribute("InvItem_itemNumber");
	
	if(invItem == null){
		invItem = new InvItem();
	}
%>

<iframe id="getInfoFrame" name="getInfoFrame" src="<%=contextPath%>/system/processing.jsp" frameborder="0" marginheight="0" marginwidth="0" style="position: absolute; border: 1px solid #C0C0C0; display: none; visibility:hidden;"></iframe>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<table width=775px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12>&nbsp;Item Bin Locations for "<%=invItemNumber%>"</div></td></tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right></td>
</tr>
</table>
<hr/>
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
							<td <%=HtmlWriter.isVisible(oid, "rec-quantityUom")%> align=center width=6%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityUom", "U/M")%></b></td>
							<td width=3% class=browseHdr>&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=0 width=100% id=binLocations>
<%
	int i_rowcount = 0;
	if (binList != null)
	{
		for(int i = 0; i < binList.size(); i++)
		{
			InvBinLocation invBinLocation = (InvBinLocation) binList.get(i);

			BigDecimal bd_qtyAlloc = HiltonUtility.getFormattedQuantity(invBinLocation.getQtyAlloc(), oid);
			BigDecimal bd_qtyOnHand = HiltonUtility.getFormattedQuantity(invBinLocation.getQtyOnHand(), oid);
			BigDecimal bd_qtyAvailable = bd_qtyOnHand.subtract(bd_qtyAlloc);  
%>
						<tr>
							<td align=center>
								<table border=0 cellspacing=0 cellpadding=1 width=100%>
								<tr>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-lotNumber")%> align=left width=8%><%=invBinLocation.getLot()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-aisle")%> align=left width=8%><%=invBinLocation.getAisle()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-row")%> align=left width=8%><%=invBinLocation.getLocrow()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-tier")%> align=left width=8%><%=invBinLocation.getTier()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-bin")%> align=left width=8%><%=invBinLocation.getBin()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-unitCost")%> align=right width=10%><%=HiltonUtility.getFormattedDollar(invBinLocation.getCost(), oid)%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-quantityOnHand")%> align=right width=10%><%=bd_qtyOnHand%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-allocated")%> align=right width=10%><%=bd_qtyAlloc%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-available")%> align=right width=10%><%=bd_qtyAvailable%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-quantityUom")%> align=left width=6%><%=invItem.getUnitOfOrder()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-details")%> width=3% align=right><div id="detailLink<%=i%>"><a href="javascript: viewDetails(<%=i%>); void(0);"><img src="<%=contextPath%>/images/plus.gif" border=0 class=processOnImg></a></div></td>
								</tr>
								<tr>
									<td colspan=10>
										<div id="binDetails<%=i%>" style="visibility:hidden; display:none; width:100%;" class=browseRow>
										<hr size=0 width=98%>
										<table border=0 cellspacing=0 cellpadding=2 width=100%>
										<tr>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-supplier")%> width=14% align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-supplier", "Supplier", false)%>:</td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-supplier")%> width=22% nowrap><%=invBinLocation.getVendorId()%></td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN01")%> width=20% align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BN01", "Inventory Bin UDF 1", false)%>:</td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN01")%> width=12% nowrap><%=invBinLocation.getUdf1Code()%></td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN03")%> width=20% align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BN03", "Inventory Bin UDF 3", false)%>:</td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN03")%> width=12% nowrap><%=invBinLocation.getUdf3Code()%></td>
										</tr>
										<tr>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-manufacturer")%> width=14% align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "manufacturer", "Manufacturer", false)%>:</td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-manufacturer")%> width=22% nowrap><%=invBinLocation.getManufactNo()%></td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN02")%> width=20% align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BN02", "Inventory Bin UDF 2", false)%>:</td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN02")%> width=12% nowrap><%=invBinLocation.getUdf2Code()%></td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN04")%> width=20% align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BN04", "Inventory Bin UDF 4", false)%>:</td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN04")%> width=12% nowrap><%=invBinLocation.getUdf4Code()%></td>
										</tr>
										<tr>
											<td colspan=4>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN05")%> width=20% align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BN05", "Inventory Bin UDF 5", false)%>:</td>
											<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-BN05")%> width=12% nowrap><%=invBinLocation.getUdf5Code()%></td>
										</tr>
										</table>
										<hr size=0 width=98%>
										</div>
									</td>
								</tr>
								</table>
							</td>
						</tr>
<%			i_rowcount ++;
		} %>
						</table>
<%		if (binList.size() <= 0){ %>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr><td align=center>There are currently no bins available.</td></tr>
						</table>
<%		}
	} %>
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

<table border=0 cellpadding=0 cellspacing=0 width=775px>
<tr>
<!--	<td width=50% align=center><div id="pxbutton"><a href="javascript: updateBins(); void(0);">Save</a></div></td>-->
	<td width=50% align=center><div id="pxbutton"><a href="javascript: window.top.hidePopWin(); void(0);">Close</a></div></td>
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

	var currentRow = <%=i_rowcount%>;
	var maxRow = <%=i_rowcount%>;


	function hideDetails(x) {
		setInnerHTML("detailLink" + x, "<a href='javascript: viewDetails(" + x + "); void(0);'><img src=\"<%=contextPath%>/images/plus.gif\" border=0 class=processOnImg alt=\"View Details\"></a>");
		hideArea("binDetails" + x);
	}

	function viewDetails(x) {
		setInnerHTML("detailLink" + x, "<a href='javascript: hideDetails(" + x + "); void(0);'><img src=\"<%=contextPath%>/images/minus.gif\" border=0 class=processOnImg alt=\"Hide Details\"></a>");
		displayArea("binDetails" + x);
	}

// End Hide script -->
</SCRIPT>