<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%@ page import="java.math.*" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String 	s_duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
	String	quantityDecimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	invModule = propertiesManager.getProperty("Modules", "Extended Inventory", "N");
	String invItemBinCost = (String) request.getAttribute("InvBinLocation_cost");
	String invItemBinNumber = (String) request.getAttribute("InvBinLocation_itemNumber");
	String invItemNumber = (String) request.getAttribute("InvLocation_itemNumber");
	String invItemLocation = (String) request.getAttribute("InvLocation_itemLocation");
	String qtyReceived = (String) request.getAttribute("qtyReceived");
	String tempIc = (String) request.getAttribute("InvBinLocation_tempIc");
	String icPoLine = (String) request.getAttribute("PoLine_icPoLine");
	String poLineUom = (String) request.getAttribute("PoLine_umCode");
	String invItemUoi = "";
	String receiptLineFactor = (String) request.getAttribute("receiptLineFactor");
	String receiptRow = (String) request.getAttribute("receiptRow");
	InvItem invItem = (InvItem) request.getAttribute("invItem");
	String s_commodity = (String) request.getAttribute("commodity");
	String duomUmCode = (String) request.getAttribute("duomUmCode") ;
	String duomQtyReceived = (String) request.getAttribute("duomQtyReceived");

	String icRecHeader = (String) request.getAttribute("ReceiptHeader_icRecHeader");
	String icRecLine = (String) request.getAttribute("ReceiptLine_icRecLine");
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_rec_type = (String)request.getAttribute("ReceiptHeader_receiptType");
	String	s_rec_number = (String)request.getAttribute("ReceiptHeader_receiptNumber");
	String	receiptMethod = HiltonUtility.ckNull((String)request.getAttribute("receiptMethod"));
	String	s_ic_po_header = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_icPoHeader"));
	//String	browseName = HiltonUtility.ckNull((String)request.getAttribute("browseName"));
	String	s_current_process = HiltonUtility.ckNull((String)request.getAttribute("currentProcess"));
	String	s_ic_req_header = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_icMsrHeader"));
	String	s_reqNumber = HiltonUtility.ckNull((String)request.getAttribute("RequisitionHeader_requisitionNumber"));
	String s_inventoryFlag = HiltonUtility.ckNull((String)request.getAttribute("ReceiptLine_inventoryFlag"));
	String s_msrNumber = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_msrNumber"));
	String s_recLine = HiltonUtility.ckNull((String)request.getAttribute("ReceiptLine_lineNumber"));
	String s_icLineHistory = HiltonUtility.ckNull((String)request.getAttribute("PoLine_icLineHistory"));
	String browseIdComesFrom = HiltonUtility.ckNull((String)request.getAttribute("browseId"));
	ReceiptLine receiptLine = (ReceiptLine)  request.getAttribute("receiptLine") ;
	PoLine poLine = (PoLine) request.getAttribute("poLine") ;
	RequisitionLine msrLine = (RequisitionLine) request.getAttribute("requisitionLine") ;


	if (receiptLine == null) receiptLine = new ReceiptLine() ;
	if (poLine == null) poLine = new PoLine();
	String s_shelfRequired = HiltonUtility.ckNull(poLine.getShelfLifeRqd()) ;

	if (invItem == null) {
		invItem = new InvItem() ;
	} else
	{
		s_shelfRequired = HiltonUtility.ckNull(invItem.getShelfLifeRqd()) ;
	}

	if (s_inventoryFlag.equalsIgnoreCase("K")) {
		if(msrLine != null){
			int lne = (int) Double.parseDouble(msrLine.getLineNumber().toString()) ;
			String s_msrLine = String.format("%04d", lne) ;
			invItemNumber = s_msrNumber + "." + s_msrLine ;
		} else {
			int lne = (int) Double.parseDouble(s_recLine) ;
			s_recLine = String.format("%04d", lne) ;
			invItemNumber = s_msrNumber + "." + s_recLine ;
		}
		invItemBinNumber = invItemNumber ;
	}
	if (invItem != null && HiltonUtility.isEmpty(duomUmCode)) {
		duomUmCode = invItem.getDuomUmCode() ;
	}

	boolean defaultBins = true;
	boolean requireFactor = false;
	boolean displayFactor = false;


	if (invItem != null && ! HiltonUtility.isEmpty(invItem.getUnitOfIssue()) && !invItem.getUnitOfIssue().equals(poLineUom)) {
		displayFactor = true;
		invItemUoi = invItem.getUnitOfIssue();
		if (HiltonUtility.isEmpty(receiptLineFactor)) {
			requireFactor = true;
		}
	}
	if (HiltonUtility.isEmpty(receiptLineFactor)) {
		receiptLineFactor = "1";
	}
	if (receiptRow == null) {
		receiptRow = "0";
	}

	Commodity commodity = CommodityManager.getInstance().getCommodity(oid, s_commodity) ;
	boolean isAsset = false ;
	boolean isDualUm = false ;
	boolean isSerNoRequired=false ;

	if (commodity != null) {
		String atemp = commodity.getSerialNumbersRequired() ;
		String umtemp = commodity.getDuomRequired() ;
		if (atemp == null) atemp = "N" ;
		if (umtemp == null) umtemp = "N" ;
		isSerNoRequired = atemp.equalsIgnoreCase("Y") ;
		if (s_duomRequired.equalsIgnoreCase("Y")) isDualUm = umtemp.equalsIgnoreCase("Y") ;
	}

	String	s_return_page = HiltonUtility.ckNull((String)request.getAttribute("returnPage"));
	if (HiltonUtility.isEmpty(s_return_page)) {
		s_return_page = "/receipts/rec_items_to_step_0.jsp";
	}
	String	s_return_method = HiltonUtility.ckNull((String)request.getAttribute("returnMethod"));
	if (HiltonUtility.isEmpty(s_return_method)) {
		s_return_method = "ReceiptHeaderRetrieveById;ReceiptLineRetrieveByHeader";
	}

	String s_ic_req_line = receiptLine.getIcReqLine().toString();
%>

<iframe id="getInfoFrame" name="getInfoFrame" src="<%=contextPath%>/system/processing.jsp" frameborder="0" marginheight="0" marginwidth="0" style="position: absolute; border: 1px solid #C0C0C0; display: none; visibility:hidden;"></iframe>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="binAction" value="UPDATE"/>
<tsa:hidden name="itemAction" value="UPDATE"/>
<tsa:hidden name="receiptRow" value="<%=receiptRow%>"/>
<tsa:hidden name="serialNumberRequired" value="<%=isSerNoRequired%>"/>
<tsa:hidden name="from" value="REC"/>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=icRecHeader%>"/>
<tsa:hidden name="ReceiptLine_icRecLine" value="<%=icRecLine%>"/>
<tsa:hidden name="InvItem_itemNumber" value="<%=invItemNumber%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="<%=s_rec_type%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=s_rec_number%>"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="InvProperty_icRecLine" value="<%=icRecLine%>"/>
<tsa:hidden name="RequisitionLine_icLineHistory" value="<%=s_icLineHistory%>"/>
<tsa:hidden name="PoLine_icPoLine" value="<%=icPoLine%>"/>
<tsa:hidden name="browseIdComesFrom" value=""/>
<tsa:hidden name="returnPage" value=""/>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_ic_req_header%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value="<%=s_ic_req_line%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_reqNumber%>"/>

<table width=775px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12>&nbsp;Item Bin Locations for "<%=headerEncoder.encodeForHTML(invItemNumber)%>"</div></td></tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
		<b>Quantity Received:</b> <%=headerEncoder.encodeForHTML(qtyReceived)%>&nbsp;
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br><br>

<table border=0 cellpadding=0 cellspacing=0 width=775px>
<tr>
	<td>
		<tsa:hidden name="InvLocation_itemNumber" value="<%=invItemNumber%>"/>
		<tsa:hidden name="InvLocation_itemLocation" value="<%=invItemLocation%>"/>
		<table border=0 cellpadding=0 cellspacing=0 width=775px>
		<tr><td class=error align=center>Warning! You must click SAVE button to update the received value.</td></tr>
		</table>
		<br>
<%	if (displayFactor) {%>
		<table border=0 cellpadding=0 cellspacing=0 width=775px>
		<tr><td class=error align=center>The Unit of Order specified on the receipt [<%=headerEncoder.encodeForHTML(poLineUom)%>] is different than the Unit of Issue specified for this inventory item [<%=invItemUoi%>].<br>Please provide a conversion factor.</td></tr>
		<tr>
			<td align=right>
				<table border=0 cellpadding=2 cellspacing=0>
				<tr>
					<td align=right class=label nowrap>Conversion Factor:</td>
					<td align=left><input type=text name="receiptLineFactor" value="<%=receiptLineFactor%>" size=10></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
<%	} else {%>
		<tsa:hidden name="receiptLineFactor" value=""/>
<%	}%>
		<table border=0 cellpadding=0 cellspacing=0 width=775px>
		<tr>
			<td width=775px align=center>
				<table border=1 id=invBinLocationTable cellpadding=0 cellspacing=0 width=775px>
				<tr>
					<td class=browseRow>
						<table border=0 cellpadding=1 cellspacing=0 width=100% class=browseHdr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-lotNumber")%> align=center width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-lotNumber", "Lots")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-aisle")%> align=center width=8%><b><a href="javascript: browseXrefCombo('InvBinLocation_aisle', 'xref-combo-aisle'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-aisle", "Aisle", true)%></a></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-row")%> align=center width=8%><b><a href="javascript: browseXrefCombo('InvBinLocation_locrow', 'xref-combo-row'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-row", "Row", true)%></a></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-tier")%> align=center width=8%><b><a href="javascript: browseXrefCombo('InvBinLocation_tier', 'xref-combo-tier'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-tier", "Tier", true)%></a></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-bin")%> align=center width=8%><b><a href="javascript: browseXrefCombo('InvBinLocation_bin', 'xref-combo-bin'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-bin", "Bin", true)%></a></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-unitCost")%> align=right width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-unitCost", "Unit Cost")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-quantityOnHand")%> align=right width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-quantityOnHand", "On Hand")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-allocated")%> align=right width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-allocated", "Allocated")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-available")%> align=right width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bin-locbrw-inv-available", "Available")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-received")%> align=right width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-received", "Received")%></b></td>
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
	List binList = (List)request.getAttribute("invBinLocationList");
	if (binList != null)
	{
		for(int i = 0; i < binList.size(); i++)
		{
			InvBinLocation invBinLocation = (InvBinLocation) binList.get(i);

			BigDecimal bd_qtyAlloc = HiltonUtility.getFormattedQuantity(new BigDecimal(0), oid);
			BigDecimal bd_qtyOnHand = HiltonUtility.getFormattedQuantity(new BigDecimal(0), oid);
			BigDecimal bd_qtyAvailable =  HiltonUtility.getFormattedQuantity(new BigDecimal(0), oid);
			BigDecimal bd_qtyReceived =  HiltonUtility.getFormattedQuantity(new BigDecimal(0), oid);
			BigDecimal bd_duomQtyReceived =  HiltonUtility.getFormattedQuantity(new BigDecimal(0), oid);

			if (!invBinLocation.getTempIc().equals(tempIc) && !invBinLocation.getStatus().equals("00")) {
				bd_qtyAlloc = HiltonUtility.getFormattedQuantity(invBinLocation.getQtyAlloc(), oid);
				bd_qtyOnHand = HiltonUtility.getFormattedQuantity(invBinLocation.getQtyOnHand(), oid);
				bd_qtyAvailable = bd_qtyOnHand.subtract(bd_qtyAlloc);
			} else {
				bd_qtyReceived = HiltonUtility.getFormattedQuantity(invBinLocation.getQtyOnHand(), oid);
				bd_duomQtyReceived = HiltonUtility.getFormattedQuantity(invBinLocation.getDuomQtyOnHand(), oid);
				defaultBins = false;
			}
%>
						<tr>
							<td align=center>
								<tsa:hidden name="InvBinLocation_icRecLine" value="<%=icRecLine%>"/>
								<tsa:hidden name="InvBinLocation_tempIc" value="<%=tempIc%>"/>
								<tsa:hidden name="InvBinLocation_itemLocation" value="${InvBinLocation_itemLocation}"/>
								<tsa:hidden name="InvBinLocation_itemNumber" value="<%=invItemBinNumber%>"/>
								<table border=0 cellspacing=0 cellpadding=1 width=100%>
								<tr>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-lotNumber")%> align=left width=8%><%=invBinLocation.getLot()%><tsa:hidden name="InvBinLocation_lot" value="<%=invBinLocation.getLot()%>"/></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-aisle")%> align=left width=8%><tsa:hidden name="InvBinLocation_aisle" value="<%=invBinLocation.getAisle()%>"/><%=invBinLocation.getAisle()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-row")%> align=left width=8%><tsa:hidden name="InvBinLocation_locrow" value="<%=invBinLocation.getLocrow()%>"/><%=invBinLocation.getLocrow()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-tier")%> align=left width=8%><tsa:hidden name="InvBinLocation_tier" value="<%=invBinLocation.getTier()%>"/><%=invBinLocation.getTier()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-bin")%> align=left width=8%><tsa:hidden name="InvBinLocation_bin" value="<%=invBinLocation.getBin()%>"/><%=invBinLocation.getBin()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-unitCost")%> align=right width=10%><%=HiltonUtility.getFormattedDollar(invItemBinCost, oid)%><tsa:hidden name="InvBinLocation_cost" value="<%=invItemBinCost%>"/></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-quantityOnHand")%> align=right width=10%><%=bd_qtyOnHand%>	<tsa:hidden name="InvBinLocation_qtyOnHand" value=""/></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-allocated")%> align=right width=10%><%=bd_qtyAlloc%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-available")%> align=right width=10%><%=bd_qtyAvailable%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-received")%> align=right width=10%><input type=text name="InvBinLocation_qtyReceived" value="<%=bd_qtyReceived%>" onchange="formatReceiptQty(<%=i%>);" size=10 style="text-align: right" value="<%=bd_qtyReceived%>"></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-quantityUom")%> align=left width=6%><%=headerEncoder.encodeForHTML(poLineUom)%><tsa:hidden name="PoLine_UmCode" value="<%=poLineUom%>"/></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-bin-locbrw-inv-details")%> width=3% align=right><div id="detailLink<%=i%>"><a href="javascript: viewDetails(<%=i%>); void(0);"><img src="<%=contextPath%>/images/plus.gif" border=0 class=processOnImg></a></div></td>
								</tr>
								<tr>
<% if (isDualUm) { %>
									<td colspan=9 align=right>Secondary UOM:&nbsp;&nbsp;</td>
									<td <%=HtmlWriter.isVisible(oid, "rec-duomQtyReceived")%> align=right width=10%><input type=text name="InvBinLocation_duomQtyReceived" value="<%=bd_duomQtyReceived%>" onchange="formatReceiptQtyDuom(<%=i%>);" size=10 style="text-align: right" value="<%=bd_duomQtyReceived%>"><tsa:hidden name="InvBinLocation_duomQtyOnHand" value=""/></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-duomUmCode")%> align=center width=6%><%=duomUmCode%><tsa:hidden name="ReceiptLine_duomUmCode" value="<%=duomUmCode%>"/></td>
									<td>&nbsp;</td>
<% } else { %>
								<td>
									<tsa:hidden name="InvBinLocation_duomQtyReceived" value="0"/>
									<tsa:hidden name="ReceiptLine_duomUmCode" value=""/>
									<tsa:hidden name="InvBinLocation_duomQtyOnHand" value="0"/>
									<tsa:hidden name="shelfRequired" value="<%=s_shelfRequired%>"/>
								</td>
<% } %>
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
<%			i_rowcount ++;
		} %>
						</table>
<%	if (binList.size() <= 0){ %>
						<table id="NoBins" border=0 cellspacing=0 cellpadding=2 width=100%>
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
	<td width=50% align=center><div id="pxbutton"><a href="javascript: browse('items-receipt-inventory'); void(0);">Cancel</a></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<div id=dummyFields style="visibility:none;display:block">
<tsa:hidden name="InvBinLocation_lot" value=""/>
<tsa:hidden name="InvBinLocation_aisle" value=""/>
<tsa:hidden name="InvBinLocation_locrow" value=""/>
<tsa:hidden name="InvBinLocation_tier" value=""/>
<tsa:hidden name="InvBinLocation_bin" value=""/>
<tsa:hidden name="InvBinLocation_cost" value=""/>
<tsa:hidden name="InvBinLocation_qtyReceived" value=""/>
<tsa:hidden name="InvBinLocation_qtyOnHand" value=""/>
<tsa:hidden name="InvBinLocation_duomQtyReceived" value=""/>
<tsa:hidden name="InvBinLocation_duomQtyOnHand" value=""/>

<tsa:hidden name="InvBinLocation_vendorId" value=""/>
<tsa:hidden name="InvBinLocation_manufactNo" value=""/>
<tsa:hidden name="InvBinLocation_udf1Code" value=""/>
<tsa:hidden name="InvBinLocation_udf2Code" value=""/>
<tsa:hidden name="InvBinLocation_udf3Code" value=""/>
<tsa:hidden name="InvBinLocation_udf4Code" value=""/>
<tsa:hidden name="InvBinLocation_udf5Code" value=""/>
<tsa:hidden name="InvBinLocation_tempIc" value=""/>
<tsa:hidden name="InvBinLocation_icRecLine" value=""/>
<tsa:hidden name="receiptMethod" value="${receiptMethod}"/>

</div>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var currentRow = <%=i_rowcount%>;
	var maxRow = <%=i_rowcount%>;

	var qtyReceived = <%=headerEncoder.encodeForJavaScript(HiltonUtility.getFormattedQuantity(qtyReceived, oid).toString())%>;
	var duomQtyReceived = <%=HiltonUtility.getFormattedQuantity(duomQtyReceived, oid)%>;

	var poUmCode = "<%=headerEncoder.encodeForJavaScript(poLineUom)%>" ;
	var duomUmCode = "<%=duomUmCode%>" ;
	var isSerNoRequired = <%=isSerNoRequired%> ;

	if (maxRow < 1) {
		addBinLocation() ;
	}

<%	if (defaultBins) {%>
	defaultQtyReceived(0);
<%	}%>

	function formatReceiptQty(row) {
		var qtyDecimals = <%=Integer.valueOf(quantityDecimals).intValue()%>;
		var receiptQty = nformat(eval(nfilter(frm.InvBinLocation_qtyReceived[row])), qtyDecimals);

		frm.InvBinLocation_qtyReceived[row].value = receiptQty;
	}

	function defaultQtyReceived(row) {
		var columns = frm.elements.item("InvBinLocation_qtyReceived");
		var defaultQty = 0;
		var qtyAllocated = 0;
		if (columns != undefined) {
			if (columns.length != undefined && columns.length > row) {
				for (var i=0; i < columns.length; i++) {
					if (isEmpty(frm.InvBinLocation_qtyReceived[i].value)) {
						frm.InvBinLocation_qtyReceived[i].value = nformat(0, <%=quantityDecimals%>);
					}
					qtyAllocated = qtyAllocated + eval(nfilter(frm.InvBinLocation_qtyReceived[i]));
				}
				if (qtyAllocated < qtyReceived) {
					defaultQty = nformat( (qtyReceived - qtyAllocated), <%=quantityDecimals%>);
				} else {
					defaultQty = nformat( defaultQty, <%=quantityDecimals%>);
				}
				frm.InvBinLocation_qtyReceived[row].value = defaultQty;
			}
		}
	}

	var binLocationsAdded = 0;
	function addBinLocation() {
		var myTable = document.getElementById("binLocations");
		var rowCount = myTable.rows.length;
		var myRow;
		var myCell;
		var binLocationTableHTML = "";

		hideArea("NoBins");
		var invItemBinNumber = "<%= headerEncoder.encodeForJavaScript(invItemBinNumber) %>";
		var itemNumberId = "InvBinLocation_itemNumber" + binLocationsAdded++;
		binLocationTableHTML = "<input type=hidden name=\"InvBinLocation_icRecLine\" value=\"<%=icRecLine%>\">" +
				"<input type=hidden name=\"InvBinLocation_tempIc\" value=\"<%=tempIc%>\">" +
				"<input type=hidden name=\"InvBinLocation_itemLocation\" value=\"${esapi:encodeForJavaScript(InvBinLocation_itemLocation)}\">" +
				"<input type=hidden id=\"" + itemNumberId + "\" name=\"InvBinLocation_itemNumber\">" +
				"<input type=hidden name=\"shelfRequired\" value=\"<%=s_shelfRequired%>\">" +
				"<table border=0 cellpadding=1 cellspacing=0 width=100% id=binLocation" + rowCount + "></table>";

		myRow = myTable.insertRow(rowCount);

		myCell = myRow.insertCell();
		myCell.width = "100%";
		myCell.align = "center";
//		myCell.innerHTML = "<table border=0 cellpadding=1 cellspacing=0 width=100% id=binLocation" + rowCount + "><input type=hidden id=\"InvBinLocation_tempIc\" value=\"<%=tempIc%>\"></table>";
		myCell.innerHTML = binLocationTableHTML;
		$("#" + itemNumberId).val(invItemBinNumber);

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
		myCell.innerHTML = "<input type=text name=\"InvBinLocation_aisle\" value=\"\" size=10 onchange='upperCase(this); void(0);' onfocus='setCurrentRow(" + maxRow + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-aisle")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-aisle")%>

		myCell = myRow.insertCell();
		myCell.width = "8%";
		myCell.align = "left";
		myCell.innerHTML = "<input type=text name=\"InvBinLocation_locrow\" value=\"\" size=10 onchange='upperCase(this); void(0);' onfocus='setCurrentRow(" + maxRow + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-row")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-row")%>

		myCell = myRow.insertCell();
		myCell.width = "8%";
		myCell.align = "left";
		myCell.innerHTML = "<input type=text name=\"InvBinLocation_tier\" value=\"\" size=10 onchange='upperCase(this); void(0);' onfocus='setCurrentRow(" + maxRow + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-tier")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-tier")%>

		myCell = myRow.insertCell();
		myCell.width = "8%";
		myCell.align = "left";
		myCell.innerHTML = "<input type=text name=\"InvBinLocation_bin\" value=\"\" size=10 onchange='upperCase(this); void(0);' onfocus='setCurrentRow(" + maxRow + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-bin")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-bin")%>

		myCell = myRow.insertCell();
		myCell.width = "10%";
		myCell.align = "right";
		var invItemBinCost = "<%=headerEncoder.encodeForJavaScript(invItemBinCost)%>";
		myCell.innerHTML = invItemBinCost + "<input id=invitembincost type=hidden name=\"InvBinLocation_cost\" size=9 style=\"text-align: right\">";
		$('#invitembincost').val(invItemBinCost);
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-unitCost")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-unitCost")%>

		myCell = myRow.insertCell();
		myCell.width = "10%";
		myCell.align = "right";
		myCell.innerHTML = "<%=HiltonUtility.getFormattedQuantity("0", oid)%><input type=hidden name=InvBinLocation_qtyOnHand value=\"\">";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-quantityOnHand")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-quantityOnHand")%>

		myCell = myRow.insertCell();
		myCell.width = "10%";
		myCell.align = "right";
		myCell.innerHTML = "<%=HiltonUtility.getFormattedQuantity("0", oid)%>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-allocated")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-allocated")%>

		myCell = myRow.insertCell();
		myCell.width = "10%";
		myCell.align = "right";
		myCell.innerHTML = "<%=HiltonUtility.getFormattedQuantity("0", oid)%>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-available")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-available")%>

		myCell = myRow.insertCell();
		myCell.width = "10%";
		myCell.align = "right";
		myCell.innerHTML = "<input type=text name=\"InvBinLocation_qtyReceived\" value=\"\" onchange=\"formatReceiptQty(" + rowCount + ");\" size=10 style=\"text-align: right\">";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-received")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-received")%>

		myCell = myRow.insertCell();
		myCell.width = "6%";
		myCell.align = "left";
		var polineumcode = '<%=headerEncoder.encodeForJavaScript(poLineUom)%>';
		myCell.innerHTML = "<input id=polineumcode type=text name=\"PoLine_umCode\" size=11 onchange='upperCase(this); void(0);' disabled>";
		$('#polineumcode').val(polineumcode);
		
		<%=HtmlWriter.cellVisibility(oid, "rec-quantityUom")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-quantityUom")%>

		myCell = myRow.insertCell();
		myCell.width = "3%";
		myCell.align = "right";
		myCell.innerHTML = "<div id=\"detailLink" + rowCount + "\"><a href=\"javascript: viewDetails(" + rowCount + "); void(0);\"><img src=\"<%=contextPath%>/images/plus.gif\" border=0 class=processOnImg></a></div>";
		<%=HtmlWriter.cellVisibility(oid, "rec-bin-locbrw-inv-details")%>
		<%=HtmlWriter.cellDisplay(oid, "rec-bin-locbrw-inv-details")%>

		myRow = myTable.insertRow();
		<% if (isDualUm) { %>

			myCell = myRow.insertCell();
			myCell.colSpan = 8;
			myCell.align = "right";
			myCell.innerHTML = "<input type=hidden name=InvBinLocation_duomQtyOnHand value=\"\">";

			myCell = myRow.insertCell();
			myCell.align = "right";
			myCell.innerHTML = "Secondary UOM:&nbsp;&nbsp;";

			myCell = myRow.insertCell();
			myCell.width = "10%";
			myCell.align = "right";
			myCell.innerHTML = "<input type=text name=\"InvBinLocation_duomQtyReceived\" value=\"0.00\" onchange=\"formatReceiptQtyDuom(" + rowCount + ");\" size=10 style=\"text-align: right\">";
			<%=HtmlWriter.cellVisibility(oid, "rec-duomQtyReceived")%>
			<%=HtmlWriter.cellDisplay(oid, "rec-duomQtyReceived")%>

			myCell = myRow.insertCell();
			myCell.width = "6%";
			myCell.align = "center";
			myCell.innerHTML = "<input type=text name=\"ReceiptLine_duomUmCode\" value=\"<%=duomUmCode%>\" size=11 onchange='upperCase(this); void(0);' disabled>";

			<%=HtmlWriter.cellVisibility(oid, "rec-duomUmCode")%>
			<%=HtmlWriter.cellDisplay(oid, "rec-duomUmCode")%>
		<% } else { %>
			myCell = myRow.insertCell();
			myCell.innerHTML = "<input type=hidden name=\"InvBinLocation_duomQtyReceived\" value=\"0\"><input type=hidden name=\"ReceiptLine_duomUmCode\" value=\"\"><input type=hidden name=InvBinLocation_duomQtyOnHand value=\"\">";
		<% } %>


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
		myCell.innerHTML = "<a href=\"javascript: browseLookup('InvBinLocation_udf1Code', 'BN01', '" + rowCount + "');void(0);\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-BN01", "UDF 1")%>:</a>&nbsp;";
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
		var msrNumber = "<%=headerEncoder.encodeForJavaScript(s_msrNumber)%>";
		myCell.innerHTML = "<input type=text id=msrNumber name=\"InvBinLocation_udf3Code\" size=12>";
		$('#msrNumber').val(msrNumber);
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

		defaultQtyReceived(rowCount);

		setCurrentRow(maxRow);
		maxRow ++;
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
		var qtyAllocated = 0;

		for (i=0; i < rowCount; i++) {
			if (eval(nfilter(frm.InvBinLocation_qtyReceived[i])) != 0.00) {
				if (isEmpty(frm.InvBinLocation_aisle[i].value + frm.InvBinLocation_locrow[i].value + frm.InvBinLocation_tier[i].value + frm.InvBinLocation_bin.value)) {
					alert("At least one of the following need data: Aisle, Row, Tier, or Bin.");
					return false;
				}
				if (frm.shelfRequired.value == "Y" && isEmpty(frm.InvBinLocation_udf2Code[i].value)) {
					alert("Shelf life date is required for this item!");
					return false;
				}
				frm.InvBinLocation_qtyOnHand[i].value = frm.InvBinLocation_qtyReceived[i].value;
				frm.InvBinLocation_duomQtyOnHand[i].value = frm.InvBinLocation_duomQtyReceived[i].value;
			} else {
				frm.InvBinLocation_qtyOnHand[i].value = "0.00";
				frm.InvBinLocation_duomQtyOnHand[i].value = "0.00" ;
			}
			qtyAllocated = eval(qtyAllocated) + eval(frm.InvBinLocation_qtyOnHand[i].value);
		}

	<%	if (isDualUm) { %>
		for (var j = 0; j < rowCount; j++)
		{
			if (frm.InvBinLocation_qtyReceived[j] && frm.InvBinLocation_duomQtyReceived[j]) {
				if (frm.InvBinLocation_qtyReceived[j].value > 0 && frm.InvBinLocation_duomQtyReceived[j].value <= 0) {
					alert("You must enter a quantity greater than ZERO for the Secondary UOM for the bin(s) into which you are receiving.");
					return false;
				}
			}
		}
	<%	} %>

		if (qtyAllocated != qtyReceived) {
			alert("You must allocated the quantity you are receiving [<%=headerEncoder.encodeForJavaScript(qtyReceived)%>].");
			return false;
		}


		frm.browseName.value = "items-receipt-inventory";
		frm.browseIdComesFrom.value = "<%=browseIdComesFrom%>";
		doSubmit('/browse/browse.jsp', 'InvBinLocationUpdateKitLocation;BrowseRetrieve');
//		doSubmit('/browse/browse.jsp', 'InvBinLocationAddTempList;InvBinLocationAddKitLocation;BrowseRetrieve');
		//doSubmit('receipts/rec_set_icrc.jsp', 'InvBinLocationAddTempList');
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

	function formatReceiptQtyDuom(row)
	{
		var qtyDecimals = <%=Integer.valueOf(quantityDecimals).intValue()%>;
		frm.InvBinLocation_duomQtyReceived[row].value = nformat(eval(nfilter(frm.InvBinLocation_duomQtyReceived[row])), qtyDecimals);
	}

	function browseXrefCombo(field, xml)
	{
		popupParameters = "";
		popupParameters = popupParameters + "formField=" + field + ";browseName=" + xml + ";index=" + currentRow + ";";

		if (frm.InvBinLocation_aisle[currentRow] && trim(frm.InvBinLocation_aisle[currentRow]) != "") {
			popupParameters = popupParameters + "colname=XrefCombo_code1;operator==;filter_txt='" + frm.InvBinLocation_aisle[currentRow].value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
		}
		if (frm.InvBinLocation_locrow[currentRow] && trim(frm.InvBinLocation_locrow[currentRow]) != "") {
			popupParameters = popupParameters + "colname=XrefCombo_code2;operator==;filter_txt='" + frm.InvBinLocation_locrow[currentRow].value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
		}
		if (frm.InvBinLocation_tier[currentRow] && trim(frm.InvBinLocation_tier[currentRow]) != "") {
			popupParameters = popupParameters + "colname=XrefCombo_code3;operator==;filter_txt='" + frm.InvBinLocation_tier[currentRow].value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
		}
		if (frm.InvBinLocation_bin[currentRow] && trim(frm.InvBinLocation_bin[currentRow]) != "") {
			popupParameters = popupParameters + "colname=XrefCombo_code4;operator==;filter_txt='" + frm.InvBinLocation_bin[currentRow].value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
		}

		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=900px', 'HEIGHT=600px');
	}

// End Hide script -->
</SCRIPT>