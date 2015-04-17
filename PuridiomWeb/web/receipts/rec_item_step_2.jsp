<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.math.*" %>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%@ page import="com.tsa.puridiom.common.documents.InspectionLevel" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.catalog.tasks.CatalogItemManager" %>
<%@ page import="com.tsagate.foundation.utility.UniqueKeyGenerator"%>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	String	s_date_format = propertiesManager.getProperty("MISC", "DateFormat", "MM-dd-yyyy");
	//boolean	extendedInventory = propertiesManager.getProperty("Modules", "Extended Inventory", "N").equalsIgnoreCase("Y");
	boolean extendedInventory = false;
	String 	s_duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
	String 	s_invitemxref = propertiesManager.getProperty("INVENTORY", "ITEMCROSSREF", "N");
	String	s_ic_rec_header = (String)request.getAttribute("ReceiptHeader_icRecHeader");
	String	s_rec_number = (String)request.getAttribute("ReceiptHeader_receiptNumber");
	String	s_rec_type = (String)request.getAttribute("ReceiptHeader_receiptType");
	String	s_rec_status = (String)request.getAttribute("ReceiptHeader_receiptStatus");
	String	s_rec_fiscal_year = (String)request.getAttribute("ReceiptHeader_fiscalYear");

	if (HiltonUtility.isEmpty(s_rec_status)) {
		s_rec_status = DocumentStatus.RCV_INPROGRESS;
	}

	String	s_ic_po_header = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_icPoHeader"));
	String	s_ic_req_header = HiltonUtility.ckNull((String)request.getAttribute("RequisitionHeader_icReqHeader"));
	String	s_req_number = HiltonUtility.ckNull((String)request.getAttribute("RequisitionHeader_requisitionNumber"));
	String	s_po_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_poNumber"));
	String	s_revision_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_revisionNumber"));
	String	s_release_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_releaseNumber"));
	if (HiltonUtility.isEmpty(s_revision_number)) {
		s_revision_number = "0";
	}
	if (HiltonUtility.isEmpty(s_release_number)) {
		s_release_number = "0";
	}
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	if (poHeader == null) {
		poHeader = new PoHeader();
	}

	String	receiptMethod = HiltonUtility.ckNull((String)request.getAttribute("receiptMethod"));
	String	s_current_process = "STEP2";
	String	s_current_page = "/receipts/rec_items_to_step_2.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";
	String	duomUmCode = "" ;

	BigDecimal	bd_zero = new BigDecimal(0);

	boolean allowEdit = false;
	/*if ((receiptMethod.equals("FinalizeReceipt") && s_rec_status.compareTo(DocumentStatus.RCV_PENDINGFINALIZATION) == 0) ||
		(receiptMethod.equals("ReceiveFromNothing") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
		(receiptMethod.equals("ReceiveByTransfer") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
		(receiptMethod.equals("Return") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
		(receiptMethod.equals("ReceiveByOrder") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0))
		allowEdit = true;*/

	ReceiptHeader	receiptHeader = (ReceiptHeader)request.getAttribute("receiptHeader");
	if (receiptHeader == null) {
		receiptHeader = new ReceiptHeader();
	}

	String markTagRqd = "";

	ReceiptLine	receiptLine = (ReceiptLine)request.getAttribute("receiptLine");
	if (receiptLine == null) {
		receiptLine = new ReceiptLine();
	}
	else {
		markTagRqd = receiptLine.getMarkTagRequired();
	}

	if(user.getMarker().equals("Y")){
		allowEdit = true;
	}

	String s_commodity = "" ;
	PoLine	poLine = receiptLine.getPoLine();
	if (poLine == null) {
		poLine = new PoLine();
	} else {
		s_commodity = poLine.getCommodity() ;
	}

	RequisitionLine reqLine = receiptLine.getRequisitionLine() ;
	if (reqLine == null) {
		reqLine = new RequisitionLine() ;
	} else {
		s_commodity = reqLine.getCommodityCode() ;
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

	boolean isInventoryItem = false;
	InvItem invItem = (InvItem) request.getAttribute("invItem") ;
	if (! (invItem == null)) {
		isInventoryItem = true ;
		duomUmCode = receiptLine.getDuomUmCode() ;
		if (HiltonUtility.isEmpty(duomUmCode)) {
			duomUmCode = invItem.getDuomUmCode() ;
		}
		if (receiptMethod.equals("ReceiveByTransfer")) {
			receiptHeader.setShipToInv("Y") ;
		}
	} else {
		isInventoryItem = false ;
	}

	String	s_return_page = HiltonUtility.ckNull((String)request.getAttribute("returnPage"));
	if (HiltonUtility.isEmpty(s_return_page)) {
		s_return_page = "/receipts/rec_items_to_step_2.jsp";
	}
	String	s_return_method = HiltonUtility.ckNull((String)request.getAttribute("returnMethod"));
	if (HiltonUtility.isEmpty(s_return_method)) {
		s_return_method = "ReceiptHeaderDataRetrieve;ReceiptLineRetrieveByHeader";
	}
	String	quantityDecimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	priceDecimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");
	BigDecimal	bd_line_number = HiltonUtility.getBigDecimalFormatted(receiptLine.getReceiptLine(), 0);
	if (bd_line_number.compareTo(new BigDecimal(0)) == 0)
		bd_line_number = new BigDecimal(1);
	String	s_line_count = (String) request.getAttribute("lineCount");

	if(s_ic_req_header.equals("") || s_ic_req_header.equals("0")){
		s_ic_req_header = poHeader.getIcReqHeader().toString();
	}

	String shelfLifeRqd = poLine.getShelfLifeRqd();

	if(HiltonUtility.isEmpty(s_req_number)){
		s_req_number = poHeader.getMsrNumber();
	}
	if(HiltonUtility.isEmpty(s_req_number)){
		s_req_number = "0";
	}

	String	s_corrosion_eval = receiptHeader.getCorrosionEval();
	String	s_udf2Code = receiptLine.getUdf2Code();
	String	s_udf3Code = receiptLine.getUdf3Code();

	InspectionHeader inspectionHeader = (InspectionHeader) request.getAttribute("inspectionHeader") ;
	if (inspectionHeader == null) inspectionHeader = new InspectionHeader() ;

	String s_inspNo = (String)  request.getAttribute("InspectionDiscrep_icInspNo") ;
	if (inspectionHeader.getComp_id() != null)
		if (inspectionHeader.getComp_id().getIcInspNo() != null) s_inspNo = inspectionHeader.getComp_id().getIcInspNo().toString() ;
%>

<input type=hidden name="ReceiptHeader_icRecHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="ReceiptHeader_receiptNumber" value="<%=s_rec_number%>">
<input type=hidden name="ReceiptHeader_receiptType" value="<%=s_rec_type%>">
<input type=hidden name="ReceiptHeader_receiptStatus" value="<%=s_rec_status%>">
<input type=hidden name="ReceiptHeader_fiscalYear" value="<%=s_rec_fiscal_year%>">
<input type=hidden name="ReceiptLine_icRecHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>">
<input type=hidden name="PoHeader_poNumber" value="<%=s_po_number%>">
<input type=hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>">
<input type=hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>">
<input type=hidden name="PoLine_icPoHeader" value="<%=s_ic_po_header%>">
<input type=hidden name="RequisitionHeader_icReqHeader" value="<%=s_ic_req_header%>">
<input type=hidden name="RequisitionLine_icReqHeader" value="<%=s_ic_req_header%>">
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<input type=hidden name="ReceiptLine_rejectionCode" value="<%=receiptLine.getRejectionCode()%>">
<input type=hidden name="ReceiptLine_qtyStep1Accepted" value="<%=HiltonUtility.ckNull(receiptLine.getQtyStep1Accepted())%>">
<input type=hidden name="ReceiptLine_qtyStep0Accepted" value="<%=HiltonUtility.ckNull(receiptLine.getQtyStep0Accepted())%>">

<input type=hidden name="Account_icHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="Account_icLine" value="0">
<input type=hidden name="Account_accountType" value="RCH">
<input type=hidden name="DocComment_icHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="DocComment_icLine" value="0">
<input type=hidden name="DocAttachment_icHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="receiptMethod" value="<%=receiptMethod%>">
<input type=hidden name="formType" value="REC">
<input type=hidden name="allowBrowse" value="true">

<input type=hidden name="PoLine_icPoLine" value="<%=poLine.getIcPoLine()%>">
<input type=hidden name="ReceiptLine_icRecLine" value="<%=receiptLine.getIcRecLine()%>">
<input type=hidden name="ReceiptHeader_shipToInv" value="<%=receiptHeader.getShipToInv()%>">
<input type=hidden name="InvBinLocation_tempIc" value="<%=receiptLine.getIcRecLine()%>">
<input type=hidden name="ReceiptHeader_icPoHeader" value="<%=s_ic_po_header%>">
<input type=hidden name="ReceiptLine_receiptLine" value="<%=bd_line_number%>">
<input type=hidden name="lineCount" value="<%=s_line_count%>">
<input type=hidden name="lineToRetrieve" value="">
<input type=hidden name="InvProperty_flag" value="I">
<input type=hidden name="returnPage" value="/receipts/rec_item_step_2.jsp">
<input type=hidden name="returnMethod" value="InvPropertyRetrieveByIcRecLine;ReceiptHeaderDataRetrieve;ReceiptLineRetrieve">

<input type="hidden" name="InspectionHeader_icMsrLine" value="<%=receiptLine.getIcLineHistory()%>">
<input type="hidden" name="InspectionHeader_icInspNo" value="<%=s_inspNo %>">

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Line Item <%=bd_line_number%> of <%=s_line_count%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td>
		<%	if (!HiltonUtility.isEmpty(s_po_number)) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Order #:</b>&nbsp;<%=s_po_number%>
		<%	} else if (!HiltonUtility.isEmpty(s_req_number)) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Transfer Request #:</b>&nbsp;<%=headerEncoder.encodeForHTML(s_req_number)%>
		<%  } %>
			</td>
			<td align=right><b>Receipt #:</b></td>
		<%	if (HiltonUtility.isEmpty(s_rec_number)) { %>
			<td width=100px>N/A</td>
		<%	} else { %>
			<td width=100px><%=s_rec_number%></td>
		<%	} %>
		</tr>
		<tr>
			<td>
		<%	if (bd_revision_number.compareTo(bd_zero) > 0) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Revision #:</b>&nbsp;<%=bd_revision_number%>
		<%	} %>
		<%	if (bd_release_number.compareTo(bd_zero) > 0) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Release #:</b>&nbsp;<%=bd_release_number%>
		<%	} %>
			</td>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=120px><%=DocumentStatus.toString(s_rec_status, oid)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>
<%@ include file="/receipts/rec_info.jsp" %>

<table border=0 cellpadding=0 cellspacing=0 width=770px>
<tr>
	<td align=right width=770px>
<%		int linecount = Integer.valueOf(s_line_count).intValue();
		if (linecount > 1) { %>
		View Item:
<%			for (int i=1; i<=linecount; i++) {
				if (i == bd_line_number.intValue() || receiptHeader.getReceiptLineByLineNumber(i).getStatus().equals(DocumentStatus.CANCELLED)) { %>
					&nbsp;<%=i%>
<%				} else { %>
					&nbsp;<a href="javascript: retrieveLine(<%=i%>);"><%=i%></a>
<%				}
			}
		} %>
	</td>
</tr>
</table>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=780px>
<tr>
	<td align=center width=780px>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=770px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRowHdr>
				<!-- HEADER -->
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=6% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineNumber", "Line #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=20% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-itemNumber", "Item/Part #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-uom")%>  width=12% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-uom", "UOM")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityToMark")%> width=7% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityToMark", "Mark")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width=7% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-balance", "Balance")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityMarked")%> width=14% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityMarked", "Received")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=14% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityRejected", "Rejected")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=14% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityAccepted", "Accepted")%></td>
					<!-- no va  pero ayuda  --><td width=3% class=browseHdr nowrap>&nbsp;</td>
					<!-- no va  pero ayuda  --><td <%=HtmlWriter.isVisible(oid, "rec-receiptInventory")%> width=3% class=browseHdr nowrap>&nbsp;</td>
					<!-- no va  pero ayuda  --><td <%=HtmlWriter.isVisible(oid, "rec-receiptNotes")%> width=3% class=browseHdr nowrap>&nbsp;</td>
				</tr>
<%
		if (poLine != null && poLine.getStatus().compareTo(DocumentStatus.RCV_INVOICED) < 0) {

			BigDecimal	qtyToMarked = new BigDecimal(0);
			if(receiptLine.getInspectionRequired().equalsIgnoreCase("Y")){
				qtyToMarked = HiltonUtility.ckNull(receiptLine.getQtyStep1Accepted());
			} else {
				qtyToMarked = HiltonUtility.ckNull(receiptLine.getQtyStep0Accepted());
			}

			BigDecimal balanceQty = qtyToMarked;
			String	endUser = poLine.getRequisitionerCode();
			if (balanceQty.compareTo(bd_zero) <= 0) {
				balanceQty = bd_zero;
			}
			List receiptLineList = poLine.getReceiptLineList();
			BigDecimal iclinekey = poLine.getIcLineKey();
			String	uom = poLine.getUmCode();
			if (HiltonUtility.isEmpty(uom)) {
				uom = "EA";
			}
%>
				<tr class=browseRow>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=6% class=browseRow nowrap align=right>
						<%	if (poLine.getLineNumber().compareTo(new BigDecimal(0)) == 0) { %>
							<%=new BigDecimal(1).setScale(1, BigDecimal.ROUND_HALF_UP)%>&nbsp;
						<%	} else { %>
							<%=poLine.getLineNumber().setScale(1, BigDecimal.ROUND_HALF_UP)%>&nbsp;
						<%	} %>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=20% align="center" =browseRow nowrap>
						<input  type=text name="PoLine_itemNumber" size=25 maxlength=30 value="<%=poLine.getItemNumber()%>" onchange="upperCase(this); getItemInfo(); void(0);">
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-uom")%> width=12% class=browseRow nowrap align="center">
						<input type=text name="PoLine_umCode" size=3 maxlength=15 value="<%=poLine.getUmCode()%>" onchange="upperCase(this); updateUMFactor();">
						<input type=hidden name="PoLine_commodity" value="<%=poLine.getCommodity()%>">
						<input type=hidden name="PoLine_umFactor" value="1">
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityToMark")%> width=7% class=browseRow nowrap align="center">
						<%=HiltonUtility.getFormattedQuantity(qtyToMarked, oid)%> (<%=uom%>)</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width=7% class=browseRow nowrap align="center" id="balanceQty"><%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%></td>
					<!-- Still don't fix -->
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityMarked")%> width="13%" height="18px" class="browseRow" align="center" nowrap>
						<input size=10 maxlength=15 name="ReceiptLine_qtyStep2Received" value="<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep2Received(), oid)%>" style="text-align:right" onchange="checkBalance();addProperty()">
						<input type=hidden name="balance" value="<%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%>">
						<input type=hidden name="originalQtyReceived" value="<%=poLine.getQtyReceived()%>">
						<input type=hidden name="ReceiptLine_icPoLine" value="<%=poLine.getIcPoLine()%>">
						<input type=hidden name="PoLine_itemSource" value="<%=poLine.getItemSource()%>">
						<input type=hidden name="PoLine_itemLocation" value="<%=poLine.getItemLocation()%>">
						<input type=hidden name="PoLine_quantity" value="<%=poLine.getQuantity()%>">
						<input type=hidden name="PoLine_receiptRequired" value="<%=poLine.getReceiptRequired()%>">
						<input type=hidden name="PoLine_asset" value="<%=poLine.getAsset()%>">
						<input type=hidden name="createReturn" value="">
						<input type=hidden name="PoLine_unitPrice" value="<%=poLine.getUnitPrice()%>">
						<input type=hidden name="returnReceiptHeader_receiptNotes" value="">
						<input type=hidden name="returnReceiptHeader_pkgsReceived" value="">
						<input type=hidden name="returnReceiptHeader_packingSlip" value=""><!--RMA #-->
						<input type=hidden name="returnReceiptHeader_carrierCode" value="">
						<input type=hidden name="returnReceiptHeader_returnDate" value="">
						<input type=hidden name="returnReceiptHeader_icRecHeader" value="">
						<input type=hidden name="returnReceiptHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>">
						<input type=hidden name="returnReceiptLine_icRecHeader" value="">
						<input type=hidden name="returnReceiptLine_qtyReceived" value="">
						<input type=hidden name="returnReceiptLine_qtyRejected" value="">
						<input type=hidden name="returnReceiptLine_qtyReturned" value="">
						<input type=hidden name="returnReceiptLine_icPoLine" value="<%=receiptLine.getIcPoLine()%>">
						<input type=hidden name="returnReceiptLine_dispositionCode" value="">
						<input type=hidden name="returnReceiptLine_receiptNotes" value="">
						<input type=hidden name="receiptLineFactor" value="">
						<input type=hidden name="count_flag" value="">
						<input type=hidden name="icRc" value="">
					</td>

					<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=13% class=browseRow nowrap align="center">
						<input type=text name="ReceiptLine_qtyStep2Rejected" size=10 value="<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep2Rejected(), oid)%>" style="text-align:right" onchange="setReceived(); checkRejected();">
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=13% class=browseRow nowrap align="center">
						<input type=text name="ReceiptLine_qtyStep2Accepted" size=10 value="<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep2Accepted(), oid)%>" style="text-align:right" readonly>
					</td>
					<td width=3% class=browseRow nowrap>
<% if (propertiesManager.getProperty("REC SELECTIONS","RECEIVEALL","N").equalsIgnoreCase("Y")) { %>
						<div id="receiveAllLink">
<%	if (balanceQty.compareTo(bd_zero) >= 1 && allowEdit) {%><a href="javascript: receiveAllForItem(); void(0);"><img src="<%=contextPath%>/images/checkmark.gif" border=0 alt="Receive Remaining Balance" tabIndex=-1></a><%}%>
						</div>
<% } %>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-receiptNotes")%> width=3% class=browseRow nowrap align=right>
						<div id="receiptNotes"><a href="javascript: viewReceiptNotes(); void(0);"><img src="<%=contextPath%>/images/cmd_edit.gif" border=0 alt="Enter Item Receipt Notes" tabIndex=-1></a></div>
					</td>
				</tr>
				<tr>
<%				if (isDualUm) { %>
					<td <%=HtmlWriter.isVisible(oid, "rec-duomUmCode")%> align=right colspan=3 nowrap>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-duomUmCode", "Secondary UOM", true)%>:&nbsp;
					</td>

					<td <%=HtmlWriter.isVisible(oid, "rec-duomUmCode")%> align=left class=browseRow valign=top>
						<input type=text name="ReceiptLine_duomUmCode" tabindex=6 size="3" maxlength=15 value="<%=duomUmCode%>" onchange="upperCase(this);" disabled>
					</td>
					<td colspan="2">&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-duomQtyReceived")%> align=right class=browseRow valign=top>
						<input type=text name="ReceiptLine_duomQtyReceived" tabindex=5 size="10" maxlength=25 value="<%=receiptLine.getDuomQtyReceived()%>" style="text-align:right" >
					</td>
<% } else { %>
					<td colspan=5>&nbsp;
						<input type=hidden name="ReceiptLine_duomQtyReceived" value="0">
						<input type=hidden name="ReceiptLine_duomUmCode" value="">
					</td>
<% } %>
				</tr>

				<tr>
					<td class=browseRow>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-description")%> colspan=4 class=browseRow valign=top>
						<!-- Este es el TextArea en la pantalla -->
						<textarea wrap="virtual" name="PoLine_description" rows=5 cols=60 onKeyDown="textCounter(this, 2000);" onKeyUp="textCounter(this,2000);" onchange="textCounter(this,2000);" disabled>${esapi:encodeForHTML(poLine.description)}</textarea>
					</td>
					<td align="right" noWrap="nowrap" >
						<tsa:label labelName='rec-EDWS' defaultString='EDWS' noinstance='true' fieldName="ReceiptLine_edws"/>:&nbsp;
					</td>
					<td>
						<input type="text" name="ReceiptLine_edws" tabIndex="5" maxLength="30" value="<%=HiltonUtility.ckNull(receiptLine.getEdws())%>"/>
					</td>
					<td colspan=5 class=browseRow valign=top align=right>
						<table id="inspectionCodes" border=0 cellspacing=0 cellpadding=1 class=browseRow>
						<tsa:tr field="rec-inspectionCode">
							<td class=browseRow>&nbsp;</td>
							<td height=16px align=right>
								<a href="javascript: browseStd('ReceiptLine_inspectionCode', 'INSP', true); void(0);" tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inspectionCode", "Inspection Code")%></a>:&nbsp;
							</td>
							<td height=16px>
								<input  type=text name="ReceiptLine_inspectionCode" value="<%=receiptLine.getInspectionCode()%>" size=13 onchange="upperCase(this);">
							</td>
							<td class=browseRow>&nbsp;</td>
							<td class=browseRow>&nbsp;</td>
						</tsa:tr>
						<tr id="ShelfLifeClauses">
							<td class=browseRow>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-shelfLifeData")%> height=16px align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-shelfLifeData", "Shelf Life Data")%>:&nbsp;</td>
							<td><input name="ReceiptLine_udf1Code" id="ReceiptLine_udf1Code" value="<%=HiltonUtility.getFormattedDate(receiptLine.getUdf1Code(), oid)%>" size=13></td>
							<td><a href="javascript: show_calendar('ReceiptLine_udf1Code', '<%=s_date_format%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign="bottom" border="0"></a></td>
							<td class=browseRow>&nbsp;</td>
						</tr>
						<tr id="itemRejectionCode" style="visibility: hidden; display:none;">
							<td height=16px align=right>
							</td>
							<td height=16px>
							</td>
						</tr>
						<tr id="itemDispositionCode" style="visibility: hidden; display:none;">
							<td height=16px align=right>
							</td>
							<td height=16px>
							</td>
						</tr>
						<tr id="itemReturnOption" style="visibility: hidden; display:none;">
							<td colspan=2 height=16px align=right>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>&nbsp;</td>
					<td colspan=4 class=browseRow valign=top>
						<div id="openItemDetails">
							<table border=0 cellspacing=0 cellpadding=1 id="openItemDetailsTable">
							<tr>
								<td valign=middle align=right>
									<a href="javascript: viewItemDetails(); void(0);" tabIndex="-1"><img src="<%=contextPath%>/images/plus.gif" border=0></a>
								</td>
								<td valign=middle>
									<a href="javascript: viewItemDetails(); void(0);" tabIndex="-1">View Item Details</a>
								</td>
							</tr>
							</table>
						</div>
						<div id="closeItemDetails" style="visibility:hidden;display:none;">
							<table border=0 cellspacing=0 cellpadding=1 id="closeItemDetailsTable">
							<tr>
								<td valign=middle align=right>
									<a href="javascript: hideItemDetails(); void(0);" tabIndex="-1"><img src="<%=contextPath%>/images/minus.gif" border=0></a>
								</td>
								<td valign=middle>
									<a href="javascript: hideItemDetails(); void(0);" tabIndex="-1">Hide Item Details</a>
								</td>
							</tr>
							</table>
						</div>
					</td>
					<td class=browseRow colspan=5 valign=top align=right>
						<div id="itemReceiptNotes" style="visibility: hidden; display: none;">
							<table border=0 cellspacing=0 cellpadding=1 class=browseRow id="itemReceiptNotesTable">
							<tr>
								<td valign=middle align=right width=5%>
									<a href="javascript: hideReceiptNotes(); void(0);" tabIndex="-1"><img src="<%=contextPath%>/images/bar_close.gif" border=0 alt='Hide Item Receipt Notes'></a>
								</td>
								<td valign=middle width=95%>
									<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcl-rec-receiptNotes", "Notes", false)%>:&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan=2>
									<textarea name="ReceiptLine_receiptNotes" cols=50 rows=4 onKeyDown="textCounter(this, 225);" onKeyUp="textCounter(this,225);">${esapi:encodeForHTML(receiptLine.receiptNotes)}</textarea>
								</td>
							</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td class=browseRow colspan=4>
						<div id="itemDetails" style="visibility: hidden; display: none;">
						<table border=0 cellpadding="0" cellspacing="0" width="100%" height="100%">
							<tr>
								<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-LineUDF01")%> width=8% height=18px align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "OrderLineItem-LineUDF01", "Line UDF 1")%> :&nbsp;</td>
								<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-LineUDF01")%> width=8% height=18px align="left" nowrap><%=poLine.getUdf1Code() %></td>
								<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-LineUDF02")%> width=8% height=18px align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "OrderLineItem-LineUDF02", "Line UDF 2")%> :&nbsp;</td>
								<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-LineUDF02")%> width=8% height=18px align="left" nowrap><%=poLine.getUdf2Code() %></td>
							</tr>
							<tr>
								<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-ShelfLifeRequired")%> width=8% height=18px align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "OrderLineItem-ShelfLifeRequired", "Shelf Life Required")%> :&nbsp;</td>
								<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-ShelfLifeRequired")%> width=8% height=18px align="left" nowrap>
									<input type="checkbox" title="Check for Shelf Life" name="ReceiptLine_shelfLifeRqd" tabIndex="4" value="Y" <%if(shelfLifeRqd.equals("Y")) {%> checked <%}%> onclick="ShelfLifeClauses();" DISABLED>
								</td>
								<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-LineUDF07")%> width=8% height=18px align="right" nowrap><tsa:label labelName="rec-LN07" defaultString="UDF 7" checkRequired="false"/> :&nbsp;</td>
								<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-LineUDF07")%> width=8% height=18px align="left" nowrap><%=poLine.getUdf7Code() %></td>
							</tr>
							<tr>
								<tsa:td field="rec-LN08" width="8%" height="18px" align="right" noWrap="nowrap">
						        	<tsa:label labelName="rec-LN08" defaultString="Mark/Tag" checkRequired="true" noinstance="yes"/>:&nbsp;
						        </tsa:td>
						        <tsa:td field="rec-LN08" align="left" noWrap="nowrap">
						        	<tsa:input type="dropdown" title="" name="ReceiptHeader_markTagRequired" labelName="rec-LN08" value="<%=markTagRqd%>" disabled="disabled"/>
						        </tsa:td>
								<td width=8% height=18px align="right" nowrap>&nbsp;</td>
								<td >&nbsp;</td>
							</tr>
							<tr>
								<td <%=HtmlWriter.isVisible(oid, "OrderGeneralInf-OrderUDF3")%> width=8% height=18px align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "OrderGeneralInf-OrderUDF3", "Order UDF 3", true)%> :&nbsp;</td>
								<td <%=HtmlWriter.isVisible(oid, "OrderGeneralInf-OrderUDF3")%> width=8% height=18px align="left" nowrap><%=poHeader.getUdf3Code() %></td>
								<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-InventoryLocation")%> width=8% height=18px align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "OrderLineItem-InventoryLocation", "Item Location")%> :&nbsp;</td>
								<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-InventoryLocation")%> width=8% height=18px align="left" nowrap><%=receiptLine.getItemLocation() %></td>
							</tr>
							<tr>
								<td <%=HtmlWriter.isVisible(oid, "rec-chemicalnumberln")%> width=8% height=18px align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-chemicalnumberln", "Chemical item Number", true)%> :&nbsp;</td>
								<td <%=HtmlWriter.isVisible(oid, "rec-chemicalnumberln")%> width=8% height=18px align="left" nowrap><%=receiptLine.getChemicalItemNumber() %></td>
								<tsa:td id="corrosionEvalLabelRow" field="rec-corrosionEval" width="8%" height="18px" align="right" noWrap="nowrap">
						        	<tsa:label labelName="rec-corrosionEval" defaultString="Corrosion Evaluation" checkRequired="true" noinstance="yes"/>:&nbsp;
						        </tsa:td>
						        <tsa:td id="corrosionEvalFieldRow" field="rec-corrosionEval">
						        	<tsa:input type="yesnoradio" title="" name="ReceiptHeader_corrosionEval" value="<%=s_corrosion_eval%>" disabled="disabled"/>
						        </tsa:td>
							</tr>
							<tr>
								<tsa:td field="rec-LN02" width="8%" height="18px" align="right" noWrap="nowrap">
						        	<tsa:label labelName="rec-LN02" defaultString="Udf 2 Code" checkRequired="false" noinstance="yes"/>:&nbsp;
						        </tsa:td>
						        <tsa:td field="rec-LN02">
						        	<tsa:input type="yesnoradio" title="" name="ReceiptLine_udf2Code" value="<%=s_udf2Code%>" disabled="disabled"/>
						        </tsa:td>
								<tsa:td field="rec-LN03" width="8%" height="18px" align="right" noWrap="nowrap">
						        	<tsa:label labelName="rec-LN03" defaultString="Udf 3 Code" checkRequired="false" noinstance="yes"/>:&nbsp;
						        </tsa:td>
						        <tsa:td field="rec-LN03">
						        	<tsa:input type="yesnoradio" title="" name="ReceiptLine_udf3Code" value="<%=s_udf3Code%>" disabled="disabled"/>
						        </tsa:td>
							</tr>
						</table>

						</div>
					</td>
					<td class=browseRow nowrap>&nbsp;</td>
					<td >
					<table border=0 cellpadding="0" cellspacing="0" width="100%" height="100%">
							<tr>
								<td colspan="2" <%=HtmlWriter.isVisible(oid, "rec-printBarcodeFromAMIS")%> width=8% height=18px align="center" nowrap>
									<a href="javascript: printBarcode(); void(0);" tabIndex="-1"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-printBarcodeFromAMIS", "Print Barcode From AMIS")%></a>
								</td>
								<td class=browseRow>&nbsp;</td>
								<td class=browseRow>&nbsp;</td>
							</tr>
							<tr>
								<td class=browseRow>&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2" <%=HtmlWriter.isVisible(oid, "rec-printLabel")%> width=8% height=18px align="center" nowrap>
								<a href="javascript: printLabel('<%=receiptLine.getStatus()%>'); void(0);" tabIndex="-1"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-printLabel", "Print Label")%></a>
								</td>
								<td class=browseRow>&nbsp;</td>
								<td class=browseRow>&nbsp;</td>
							</tr>

						</table>
						</td>
					<td class=browseRow nowrap>&nbsp;</td>
					<td class=browseRow nowrap>&nbsp;</td>
					<td class=browseRow nowrap>&nbsp;</td>
				</tr>
				<tr>
					<td class=browseRow>&nbsp;</td>
					<td class=browseRow colspan=9 valign=top>
					</td>
				</tr>
<%			} %>
				</table>
			</td>
		</tr>
		<tr>
		<%@ include file="/receipts/rec_property.jsp" %>
		</tr>
		</table>
	</td>
</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=780px>
<tr>
<%	if (allowEdit) { %>
<!--	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis('<%=s_return_page%>', 'ReceiptLineUpdateById;PoLineUpdateBalanceReceiptLine;InvPropertyDeleteByIcRecLine;ReceiptTempPropertyUpdate;<%=s_return_method%>'); void(0);">Save</a></div></td>-->
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis('<%=s_return_page%>', 'ReceiptLineUpdateById;<%=s_return_method%>'); void(0);">Save</a></div></td>
<%	} %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=s_return_page%>', '<%=s_return_method%>'); void(0);">Return</a></div></td>
</tr>
</table>

<table>
<tr>
	<td>
		<input type=HIDDEN name="PoLine_discOvr" value="<%=poLine.getDiscOvr()%>">
		<input type=HIDDEN name="PoLine_discountPercent" value="<%=HiltonUtility.getBigDecimalFormatted(poLine.getDiscountPercent(), 2)%>">
		<input type=HIDDEN name="PoLine_discountAmount" value="<%=HiltonUtility.getFormattedDollar(poLine.getDiscountAmount(), oid)%>">
		<input type=HIDDEN name="PoLine_taxOvr" value="<%=poLine.getTaxOvr()%>">
		<input type=HIDDEN name="PoLine_taxPercent" value="<%=HiltonUtility.getBigDecimalFormatted(poLine.getTaxPercent(), 5)%>">
		<input type=HIDDEN name="PoLine_taxAmount" value="<%=HiltonUtility.getFormattedDollar(poLine.getTaxAmount(), oid)%>">
		<input type=HIDDEN name="PoLine_shipOvr" value="<%=poLine.getShipOvr()%>">
		<input type=HIDDEN name="PoLine_shippingCharges" value="<%=HiltonUtility.getFormattedDollar(poLine.getShippingCharges(), oid)%>">
		<input type=HIDDEN name="PoLine_otherOvr" value="<%=poLine.getOtherOvr()%>">
		<input type=HIDDEN name="PoLine_otherCharges" value="<%=HiltonUtility.getFormattedDollar(poLine.getOtherCharges(), oid)%>">
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var currentRow = 0;
	var qtyDecimals  = <%=Integer.valueOf(quantityDecimals).intValue()%>;
	var displayRejectionCode = <%=DictionaryManager.isVisible(oid, "rec-rejectionCode")%>;
	var displayDispositionCode = <%=DictionaryManager.isVisible(oid, "rec-dispositionCode")%>;
	var info_window = null;
	var uomArray = new Array();
	var populated = false;
	var sum = 1;
	var isAsset = <%=isAsset%> ;
	var extendedInventory = <%=extendedInventory%> ;
	var isSerNoRequired = <%=isSerNoRequired%> ;
	var isInventoryItem = <%=isInventoryItem%> ;
	var receiptMethod = '<%=receiptMethod%>';
	checkRejected();
	ShelfLifeClauses();
	//setAsset() ;

	function thisLoad()
	{
		f_StartIt();
	<%	if (!allowEdit) { %>
		checkInputSettings();
	<%	} %>
	}

	function formatUnitPrice() {
		var price_dec = <%=Integer.valueOf(priceDecimals).intValue()%>;
		var p = nformat(eval(nfilter(frm.PoLine_unitPrice)),price_dec);
		var q = nformat(eval(nfilter(frm.ReceiptLine_qtyStep2Received)), qtyDecimals);
		var f = eval(nfilter(frm.PoLine_umFactor));

		if (f == 0) { f = 1; }

		frm.PoLine_umFactor.value = f;
		frm.PoLine_unitPrice.value = p;
		frm.PoLine_quantity.value = q;

		if (p == (nformat(eval(0.00),price_dec)) && q == nformat(eval(0.00),qtyDecimals)) {
			frm.PoLine_unitPrice.value = '';
			frm.PoLine_quantity.value = '';
		}
	}

	function setGfp(InvProperty_flag) {
		if (frm.c_gfp.checked) {
			frm.c_asset.checked = false;
			frm.c_fgp.checked = false;
			frm.InvProperty_flag.value = "A";
			//displayArea("invPropertyArea");
		} else {
			frm.InvProperty_flag.value = "I";
			//hideArea("invPropertyArea");
		}
		frm.gfp.value =  frm.InvProperty_flag.value;
	}

	function setFgp(InvProperty_flag) {
		if (frm.c_fgp.checked) {
			frm.c_asset.checked = false;
			frm.c_gfp.checked = false;
			frm.InvProperty_flag.value = "A";
			//displayArea("invPropertyArea");
		} else {
			frm.InvProperty_flag.value = "I";
			//hideArea("invPropertyArea");
		}
	}

	function setAsset() {
		if (frm.c_asset.checked) {
			frm.c_gfp.checked = false;
			frm.c_fgp.checked = false;
//			displayArea("invPropertyArea");
		} else {
//			hideArea("invPropertyArea");
		}
	}

	function updateUMFactor() {
		var open = true;
		var browser = browserTest();
		var factor = "";
		var code = "";

		frm.PoLine_umCode.value = trim(frm.PoLine_umCode);

		if (info_window != null) {
			if (browser == "Netscape") {
				if (info_window.closed == false) {
					info_window.setUomCode("PoLine_");
					open = false;
				}
			}
			else {
				info_window.setUomCode("PoLine_");
				open = false;
			}
		}

		if (open == true) {
			if (uomArray.length > 0 || populated) {
				code = frm.PoLine_umCode.value;
				for (var i = 0; i < uomArray.length; i++) {
					if (code == (uomArray[i][0]).toString()) {
						factor = uomArray[i][1];
						break;
					}
				}
				frm.PoLine_umFactor.value = factor;
			}
			else {
				popupParameters = "as_prefix=PoLine_;as_row=" + currentRow;

				setLookupParameters('/base/get_uom_info.jsp', 'UnitOfMeasureRetrieveAll');
				document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
			}
		}
	}

	function setUmFactor(factor) {
		frm.PoLine_umFactor.value = factor;
	}

	function populateUOM(uoma)
	{
		var e = 0;

		for (var i = 0; i < uoma.length; i++) {
			uomArray[e] = new Array(uoma[i][0], uoma[i][1]);
			e++;
		}
		populated = true;
	}

	function getItemInfo() {
		if (isEmpty(frm.PoLine_itemNumber.value)) {
			frm.PoLine_altItemNumber.value = "";
		} else {
			var icPoHeader = frm.PoHeader_icPoHeader.value;
			var icPoLine = frm.PoLine_icPoLine.value;
			var itemNumber = frm.PoLine_itemNumber.value;
			var shipToInv = frm.ReceiptHeader_shipToInv.value;

			popupParameters = "PoHeader_icPoHeader=" + icPoHeader + ";PoLine_icPoHeader=" + icPoHeader + ";PoLine_icPoLine=" + icPoLine + ";PoLine_itemNumber=" + itemNumber + ";currentRow=" + currentRow + ";shipToInv=" + shipToInv;

			setLookupParameters('/receipts/rec_get_poline_info.jsp', 'ReceiptPoLineItemLookup');
			displayArea('getInfoFrame');
			document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
		}
	}

	function receiveAllForItem() {
		//var balance = nformat(frm.balance.value, qtyDecimals);

		<% if (receiptMethod.equalsIgnoreCase("ReceiveByTransfer")){ %>
			var quantity = frm.RequisitionLine_quantity.value - frm.originalQtyReceived.value ;
		<% } else { %>
			var quantity = frm.PoLine_quantity.value - frm.originalQtyReceived.value ;
		<% } %>
		var balance = nformat(quantity, qtyDecimals);
		frm.ReceiptLine_qtyStep2Received.value = balance;
		frm.ReceiptLine_qtyStep2Rejected.value = nformat(0, qtyDecimals);
		checkBalance();
		addProperty();
	}

	function checkBalance() {
		var receiptQty = nformat(eval(nfilter(frm.ReceiptLine_qtyStep2Received)), qtyDecimals);
		var rejectedQty = nformat(eval(nfilter(frm.ReceiptLine_qtyStep2Rejected)), qtyDecimals);
		var acceptedQty = nformat(receiptQty - rejectedQty, qtyDecimals);
		//var originalBalance = nformat(eval(nfilter(frm.balance)), qtyDecimals);

		<% if (receiptMethod.equalsIgnoreCase("ReceiveByTransfer")){ %>
			var quantity = frm.RequisitionLine_quantity.value;
		<% } else if(receiptLine.getInspectionRequired().equalsIgnoreCase("Y")){ %>
			var quantity = frm.ReceiptLine_qtyStep1Accepted.value;
		<% } else {%>
			var quantity = frm.ReceiptLine_qtyStep0Accepted.value;
		<% }%>

		var newBalance = nformat(quantity - acceptedQty, qtyDecimals);

		frm.ReceiptLine_qtyStep2Received.value = receiptQty;
		frm.ReceiptLine_qtyStep2Accepted.value = acceptedQty;

		if (newBalance <= 0) {
			var d = document.all("balanceQty");
			d.innerHTML = nformat(0.0, qtyDecimals);
			<% if (propertiesManager.getProperty("REC SELECTIONS","RECEIVEALL","N").equalsIgnoreCase("Y")) { %>
				var imgs = document.all("receiveAllLink");
				imgs.style.visibility = "hidden";
			<% } %>
		} else {
			var d = document.all("balanceQty");
			d.innerHTML = newBalance;
			<% if (propertiesManager.getProperty("REC SELECTIONS","RECEIVEALL","N").equalsIgnoreCase("Y")) { %>
				var imgs = document.all("receiveAllLink");
				imgs.style.visibility = "visible";
			<% } %>
		}
	}

	function checkTransferBalance() {
		var receiptQty = nformat(eval(nfilter(frm.ReceiptLine_qtyStep2Received)), qtyDecimals);
		var rejectedQty = nformat(eval(nfilter(frm.ReceiptLine_qtyStep2Rejected)), qtyDecimals);
		var acceptedQty = nformat(receiptQty - rejectedQty, qtyDecimals);
		//var originalBalance = nformat(eval(nfilter(frm.balance)), qtyDecimals);
		var newBalance = nformat(frm.RequisitionLine_quantity.value - acceptedQty, qtyDecimals);

		frm.ReceiptLine_qtyStep2Received.value = receiptQty;
		frm.ReceiptLine_qtyStep2Accepted.value = acceptedQty;

		if (newBalance <= 0) {
			var d = document.all("balanceQty");
			d.innerHTML = nformat(0.0, qtyDecimals);
			<% if (propertiesManager.getProperty("REC SELECTIONS","RECEIVEALL","N").equalsIgnoreCase("Y")) { %>
				var imgs = document.all("receiveAllLink");
				imgs.style.visibility = "hidden";
			<% } %>
		} else {
			var d = document.all("balanceQty");
			d.innerHTML = newBalance;
			<% if (propertiesManager.getProperty("REC SELECTIONS","RECEIVEALL","N").equalsIgnoreCase("Y")) { %>
				var imgs = document.all("receiveAllLink");
				imgs.style.visibility = "visible";
			<% } %>
		}
	}

	function viewReceiptNotes() {
		var d = document.all("itemReceiptNotes");
		d.style.visibility = "visible";
		d.style.display = "block";
		var t = document.all("receiptNotes");
		t.style.visibility = "hidden";
		t.style.display = "block";
	}

	function hideReceiptNotes() {
		var d = document.all("itemReceiptNotes");
		d.style.visibility = "hidden";
		d.style.display = "none";
		var t = document.all("receiptNotes");
		t.style.visibility = "visible";
		t.style.display = "block";
	}

	function checkProperty() {
		var shipToInv = frm.ReceiptHeader_shipToInv.value;
		var itemLocation = frm.PoLine_itemLocation.value;
		var itemNumber = frm.PoLine_itemNumber.value;
		var itemCost = frm.PoLine_unitPrice.value;
		var umCode = frm.PoLine_umCode.value;
		var commodity = "<%=s_commodity%>" ;
		var receiptOption = frm.PoLine_receiptRequired.value;
		var icPoLine = frm.ReceiptLine_icPoLine.value;
		var qtyReceived = frm.ReceiptLine_qtyStep2Accepted.value;
		var duomQtyReceived = frm.ReceiptLine_duomQtyReceived.value;
		var duomUmCode = frm.ReceiptLine_duomUmCode.value;
		var receiptLineFactor = frm.receiptLineFactor.value;
		var icRecLine = frm.ReceiptLine_icRecLine.value ;


		if (qtyReceived == 0) {
			popupParameters = "ReceiptLine_icRecLine=" + icRecLine ;

			setLookupParameters('/system/hide_iframe.jsp', 'InvPropertyDeleteItemByIcRecLine');
			displayArea('getInfoFrame');
			document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
		} else {
			popupParameters = "InvItem_itemNumber=" + itemNumber + ";InvBinLocation_itemNumber=" + itemNumber +
				";InvBinLocation_itemLocation=" + itemLocation + ";InvLocation_itemNumber=" + itemNumber +
				";InvLocation_itemLocation=" + itemLocation + ";qtyReceived=" + qtyReceived + ";duomQtyReceived=" + duomQtyReceived +
				";index=0;InvBinLocation_tempIc=" + frm.InvBinLocation_tempIc.value + ";InvBinLocation_cost=" + itemCost +
				";PoLine_icPoLine=" + icPoLine + ";PoLine_umCode=" + umCode + ";commodity=" + commodity +
				";receiptRow=0;receiptLineFactor=" +  receiptLineFactor + ";ReceiptLine_icRecLine=" + icRecLine +
				";InvProperty_icRecLine=" + icRecLine ;
			doSubmitToPopup('receipts/rec_property.jsp', 'InvPropertyRetrieveByIcRecLine', 'WIDTH=800px', 'HEIGHT=500px');
		}
	}


	function checkTransferInventory() {
		var shipToInv = frm.ReceiptHeader_shipToInv.value;
		var itemLocation = frm.RequisitionLine_itemLocation.value;
		var itemNumber = frm.RequisitionLine_itemNumber.value;
		var itemCost = frm.RequisitionLine_unitPrice.value;
		var umCode = frm.RequisitionLine_umCode.value;
		var receiptOption = frm.RequisitionLine_receiptRequired.value;
		var icPoLine = frm.ReceiptLine_icPoLine.value;
		var commodity = "<%=s_commodity%>" ;
		var qtyReceived = frm.ReceiptLine_qtyStep2Accepted.value;
		var duomQtyReceived = frm.ReceiptLine_duomQtyReceived.value;
		var duomUmCode = frm.ReceiptLine_duomUmCode.value;
		var receiptLineFactor = frm.receiptLineFactor.value;
		if (extendedInventory && (shipToInv == "Y" || shipToInv == "T") && (receiptOption == "R" || receiptOption == "E")) {
			if (qtyReceived == 0) {
				popupParameters = "InvBinLocation_itemNumber=" + itemNumber + ";InvBinLocation_itemLocation=" + itemLocation + ";InvBinLocation_tempIc=" + frm.InvBinLocation_tempIc.value;

				setLookupParameters('/system/hide_iframe.jsp', 'InvBinLocationDeleteItemByTempIc');
				displayArea('getInfoFrame');
				document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
			} else {
				popupParameters = "InvItem_itemNumber=" + itemNumber + ";InvBinLocation_itemNumber=" + itemNumber + ";InvBinLocation_itemLocation=" + itemLocation + ";InvLocation_itemNumber=" + itemNumber + ";InvLocation_itemLocation=" + itemLocation + ";qtyReceived=" + qtyReceived + ";index=0;InvBinLocation_tempIc=" + frm.InvBinLocation_tempIc.value + ";InvBinLocation_cost=" + itemCost + ";PoLine_icPoLine=" + icPoLine + ";PoLine_umCode=" + umCode + ";duomUmCode=" + duomUmCode + ";duomQtyReceived=" + duomQtyReceived + ";commodity=" + commodity + ";receiptRow=0;receiptLineFactor=" + receiptLineFactor + ";receiptMethod=" + receiptMethod + ";";
				doSubmitToPopup('receipts/rec_inv_bin_locations.jsp', 'InvItemRetrieveById;InvBinLocationRetrieveByItem', 'WIDTH=800px', 'HEIGHT=500px');
			}
		}
	}

	function viewAssetRelated(iclinekey) {
  		var newInputField = "<input type='hidden' name='Asset_icLineKey' value='" + iclinekey + "'>";
  			newInputField = newInputField + "<input type='hidden' name='allowBrowse' value='true'>";
  			newInputField = newInputField + "<input type='hidden' name='action'		value='rec-item-entry'>";
			newInputField = newInputField + "<input type='hidden' name='process'	value='AssetRetrieveByIcLineKey'>";
			newInputField = newInputField + "<input type='hidden' name='urlret'		value='/receipts/rec_item_entry'>";
		setHiddenFields(newInputField);
		doSubmit("/asset/asset_rec_browse.jsp", "AssetRetrieveByIcLineKey");
 	}

	function setReceived() {
		var qtyReceived = nformat(eval(nfilter(frm.ReceiptLine_qtyStep2Received)), qtyDecimals);
		var qtyRejected = nformat(eval(nfilter(frm.ReceiptLine_qtyStep2Rejected)), qtyDecimals);
		var qtyReceivedValue = eval(nfilter(frm.ReceiptLine_qtyStep2Received));
		var qtyRejectedValue = eval(nfilter(frm.ReceiptLine_qtyStep2Rejected));

		frm.ReceiptLine_qtyStep2Rejected.value = qtyRejected;
		if (qtyRejectedValue > qtyReceivedValue) {
			alert("You cannot reject more than the quantity you have received.");
			qtyRejected = qtyReceived;
			frm.ReceiptLine_qtyStep2Rejected.value = qtyReceived;
		}
		checkBalance();
	}

	function checkRejected() {
		var qtyRejected = nformat(eval(nfilter(frm.ReceiptLine_qtyStep2Rejected)), qtyDecimals);
		if (qtyRejected > 0) {
			if (displayRejectionCode) {
				viewRejectionCode();
				frm.ReceiptLine_rejectionCode.select();
			}
			if (displayDispositionCode) {
				viewDispositionCode();
				if (!displayRejectionCode) {
					frm.ReceiptLine_dispositionCode.select();
				}
			}
		}
		else {
			frm.createReturn.value = "N";
			hideRejectionCode();
			hideDispositionCode();
		}
		checkBalance();
	}

	function viewRejectionCode() {
		var d = document.all("itemRejectionCode");
		d.style.visibility = "visible";
		d.style.display = "block";
		var d = document.all("itemReturnOption");
		d.style.visibility = "visible";
		d.style.display = "block";
	}

	function hideRejectionCode() {
		var d = document.all("itemRejectionCode");
		d.style.visibility = "hidden";
		d.style.display = "none";
		var d = document.all("itemReturnOption");
		d.style.visibility = "hidden";
		d.style.display = "none";
	}

	function viewDispositionCode() {
		var d = document.all("itemDispositionCode");
		d.style.visibility = "visible";
		d.style.display = "block";
	}

	function hideDispositionCode() {
		var d = document.all("itemDispositionCode");
		d.style.visibility = "hidden";
		d.style.display = "none";
	}

	function viewItemDetails() {
		var d = document.all("openItemDetails");
		d.style.visibility = "hidden";
		d.style.display = "none";
		var t = document.all("closeItemDetails");
		t.style.visibility = "visible";
		t.style.display = "block";
		var s = document.all("itemDetails");
		s.style.visibility = "visible";
		s.style.display = "block";
	}

	function hideItemDetails() {
		var d = document.all("openItemDetails");
		d.style.visibility = "visible";
		d.style.display = "block";
		var t = document.all("closeItemDetails");
		t.style.visibility = "hidden";
		t.style.display = "none";
		var s = document.all("itemDetails");
		s.style.visibility = "hidden";
		s.style.display = "none";
	}

	function returnRejected() {
		var originalQtyReceived = eval(nfilter(frm.originalQtyReceived));
		var qtyReceived = eval(nfilter(frm.ReceiptLine_qtyStep2Received));
		var totalQtyRecieved = eval(originalQtyReceived + qtyReceived);

		popupParameters = "ReceiptHeader_icPoHeader=" + frm.ReceiptHeader_icPoHeader.value;
		popupParameters = popupParameters + ";icPoLine=" + frm.ReceiptLine_icPoLine.value;
		popupParameters = popupParameters + ";qtyRejected=" + frm.ReceiptLine_qtyStep2Rejected.value;
		popupParameters = popupParameters + ";qtyReceived=" + totalQtyRecieved;
		popupParameters = popupParameters + ";dispositionCode=" + frm.ReceiptLine_dispositionCode.value;
		popupParameters = popupParameters + ";currentRow=0";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		popupParameters = popupParameters + ";receiptMethod=" + frm.receiptMethod.value;

		doSubmitToPopup('receipts/rec_return_popup.jsp', 'ReceiptCreateRetrieve', 'width=692px', 'height=450px');
	}

	function retrieveLine(linenumber) {
		frm.lineToRetrieve.value = linenumber;
		frm.returnPage.value = "<%=s_return_page%>";
		frm.returnMethod.value = "<%=s_return_method%>";
		doSubmit('/receipts/rec_item_step_2.jsp', 'ReceiptLineUpdate;InvPropertyRetrieveByIcRecLine;ReceiptHeaderDataRetrieve;PoHeaderRetrieveById;ReceiptLineRetrieveByLineNumber');
	}

	function altItemLookup() {
		var itemNumber = frm.PoLine_itemNumber.value;
		popupParameters = "colname=ItemCrossRef_itemNumber;operator==;filter_txt=%" + itemNumber + "%;logicalOperator=AND;originalFilter=Y;sort=N";
		popupParameters = popupParameters + ";formField=PoLine_altItemNumber;browseName=itemcrossref;allowBrowse=" + frm.allowBrowse.value;

		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}

	function setAssetBox(fld,x) {
		  fld.value = 'N';
		  if (x == 0 && !frm.c_asset[x]) {
		  	if ( frm.c_asset.checked ) {
		    fld.value = 'Y';
		  }
		  }
		  else if ( frm.c_asset[x].checked ) {
		    fld.value = 'Y';
		  }
		  return true;
		}

	function verifyDuomQty()
	{
		frm.ReceiptLine_duomQtyReceived.value = nformat(eval(nfilter(frm.ReceiptLine_duomQtyReceived)), qtyDecimals);
	}

	function submitThis(page, handlers)
	{
		/*if (qtyReceived > 0)
		{
			if (qtyReceived == 1)
			{
				if (frm.InvProperty_serialNumber && trim(frm.InvProperty_serialNumber) == "")
				{
					alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-serialnumber", "Serial #")%> is required");
					frm.InvProperty_serialNumber.focus();
					return false;
				}
			}
			else
			{
				for (var i = 0; i < qtyReceived; i++)
				{
					if (frm.InvProperty_serialNumber[i] && trim(frm.InvProperty_serialNumber[i]) == "")
					{
						alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-serialnumber", "Serial #")%> is required");
						frm.InvProperty_serialNumber[i].focus();
						return false;
					}
				}
			}
		}*/

	<%	if (isDualUm) { %>
			if (frm.ReceiptLine_duomQtyReceived && frm.ReceiptLine_duomQtyReceived.value > 0) {
				doSubmit(page, 'InvPropertyDeleteByIcRecLine;ReceiptTempPropertyUpdate;'+handlers);
			} else {
				alert("You must enter a quantity greater than ZERO for the Secondary UOM.");
			}
	<%	} else { %>
			doSubmit(page, 'InvPropertyDeleteByIcRecLine;ReceiptTempPropertyUpdate;'+handlers);
	<%	} %>
	}

	function printLabel(status)
	{
		popupParameters = "ReceiptHeader_icRecHeader=<%=s_ic_rec_header%>";
		popupParameters = popupParameters + ";ReceiptLine_icRecLine=<%=receiptLine.getIcRecLine()%>";
		popupParameters = popupParameters + ";PoHeader_icPoHeader=<%=receiptHeader.getIcPoHeader()%>";
		popupParameters = popupParameters + ";PoLine_icPoLine=<%=receiptLine.getIcPoLine()%>";
		popupParameters = popupParameters + ";RequisitionHeader_icReqHeader=<%=s_ic_req_header%>";
		popupParameters = popupParameters + ";InspectionHeader_icMsrLine="+frm.InspectionHeader_icMsrLine.value;
		popupParameters = popupParameters + ";InspectionHeader_icInspNo="+frm.InspectionHeader_icInspNo.value;
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		popupParameters = popupParameters + ";viewNow=Y";
		popupParameters = popupParameters + ";status="+status;

		doSubmitToPopup('/system/popupDocAttachment.jsp', 'PrintRecLineLabelPdf', 'width=775px', 'height=850px');
	}

	function printBarcode()
	{
		popupParameters = "ReceiptHeader_icRecHeader=<%=s_ic_rec_header%>";
		popupParameters = popupParameters + ";ReceiptLine_icRecLine=<%=receiptLine.getIcRecLine()%>";
		popupParameters = popupParameters + ";PoHeader_icPoHeader=<%=receiptHeader.getIcPoHeader()%>";
		popupParameters = popupParameters + ";PoLine_icPoLine=<%=receiptLine.getIcPoLine()%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		popupParameters = popupParameters + ";viewNow=Y";

		doSubmitToLookup('', 'PrintRecLineBarcodePdf', 'width=775px', 'height=850px');
	}

	function ShelfLifeClauses()
	{
		if(frm.ReceiptLine_shelfLifeRqd.checked == true){
			displayArea("ShelfLifeClauses");
		} else {
			hideArea("ShelfLifeClauses");
		}
	}

// End Hide script -->
</SCRIPT>