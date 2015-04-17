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

	boolean	extendedInventory = propertiesManager.getProperty("Modules", "Extended Inventory", "N").equalsIgnoreCase("Y");
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
	String	s_current_process = "SHOPCART";
	String	s_current_page = "/receipts/rec_items.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";
	String	duomUmCode = "" ;

	BigDecimal	bd_zero = new BigDecimal(0);

	boolean allowEdit = false;
	if ((receiptMethod.equals("FinalizeReceipt") && s_rec_status.compareTo(DocumentStatus.RCV_PENDINGFINALIZATION) == 0) ||
		(receiptMethod.equals("ReceiveFromNothing") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
		(receiptMethod.equals("ReceiveByTransfer") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
		(receiptMethod.equals("Return") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
		(receiptMethod.equals("ReceiveByOrder") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0))
		allowEdit = true;
	
	ReceiptHeader	receiptHeader = (ReceiptHeader)request.getAttribute("receiptHeader");
	if (receiptHeader == null) {
		receiptHeader = new ReceiptHeader();
	}

	ReceiptLine	receiptLine = (ReceiptLine)request.getAttribute("receiptLine");
	if (receiptLine == null) {
		receiptLine = new ReceiptLine();
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
	
	if(receiptMethod.equals("ReceiveByTransfer") && reqLine.getStatus().compareTo(DocumentStatus.INV_BACKORDERED) == 0){
		allowEdit = false;
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

	duomUmCode = receiptLine.getDuomUmCode();
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
		s_return_page = "/receipts/rec_items.jsp";
	}
	String	s_return_method = HiltonUtility.ckNull((String)request.getAttribute("returnMethod"));
	if (HiltonUtility.isEmpty(s_return_method)) {
		s_return_method = "ReceiptLineRetrieveByHeader";
	}
	String	quantityDecimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	priceDecimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");
	BigDecimal	bd_line_number = HiltonUtility.getBigDecimalFormatted(receiptLine.getReceiptLine(), 0);
	if (bd_line_number.compareTo(new BigDecimal(0)) == 0)
		bd_line_number = new BigDecimal(1);
	String	s_line_count = (String) request.getAttribute("lineCount");

	int invPropertyCount = 0;
	List invPropertyList = (List)request.getAttribute("invPropertyList");
	if (invPropertyList != null && invPropertyList.size() > 0) {
		invPropertyCount = invPropertyList.size();
	}

	boolean allowCheckViewItems = false;
	String[] checkItemValues = new String[Integer.valueOf(s_line_count).intValue()];

	String checkViewItems = (String)request.getAttribute("checkViewItems");
	if (!HiltonUtility.isEmpty(checkViewItems) && checkViewItems.length() > 0)
	{
		String[] checkItemsArray = checkViewItems.split(";");
		if (checkItemsArray != null && checkItemsArray.length == Integer.valueOf(s_line_count).intValue())
		{
			boolean allowCheckItem = true;
			for (int i = 0; i < checkItemsArray.length; i++)
			{
				String checkItems = checkItemsArray[i];
				String[] checkItem = checkItems.split(",");
				if (checkItem != null && checkItem.length == 3)
				{
					checkItemValues[i] = checkItem[0] + ", '" + checkItem[1] + "', " + checkItem[2];
				}
				else
				{
					allowCheckItem = false;
				}
			}
			allowCheckViewItems = allowCheckItem;
		}
	}

	String s_shipToInv = receiptHeader.getShipToInv();
%>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=s_rec_number%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="<%=s_rec_type%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=s_rec_status%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=s_rec_fiscal_year%>"/>
<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_ic_req_header%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=s_ic_req_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>

<tsa:hidden name="Account_icHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RCH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="formType" value="REC"/>
<tsa:hidden name="allowBrowse" value="true"/>

<tsa:hidden name="PoLine_icPoLine" value="<%=poLine.getIcPoLine()%>"/>
<tsa:hidden name="ReceiptLine_icRecLine" value="<%=receiptLine.getIcRecLine()%>"/>
<tsa:hidden name="ReceiptHeader_shipToInv" value="<%=receiptHeader.getShipToInv()%>"/>
<tsa:hidden name="InvBinLocation_tempIc" value="<%=receiptLine.getIcRecLine()%>"/>
<tsa:hidden name="ReceiptHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="ReceiptLine_receiptLine" value="<%=bd_line_number%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="lineToRetrieve" value=""/>
<tsa:hidden name="InvProperty_flag" value="I"/>
<tsa:hidden name="returnPage" value="<%=s_return_page%>"/>
<tsa:hidden name="returnMethod" value="<%=s_return_method%>"/>

<tsa:hidden name="invPropertyCount" value="<%=invPropertyCount%>"/>
<tsa:hidden name="checkViewItems" value="<%=checkViewItems%>"/>

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
				if (i == bd_line_number.intValue()) { %>
					&nbsp;<%=i%>
<%				} else { %>
					<%	if (allowCheckViewItems) { %>
					&nbsp;<a href="javascript: checkViewItem(<%=i%>, <%=checkItemValues[i - 1]%>);"><%=i%></a>
					<%	} else { %>
					&nbsp;<a href="javascript: retrieveLine(<%=i%>);"><%=i%></a>
					<%	} %>
<%				}
			}
		} %>
	</td>
</tr>
</table>
<br>

<%	if (receiptMethod.equals("FinalizeReceipt")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=780px>
<tr>
	<td align=center width=780px>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=770px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRowHdr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=6% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineNumber", "Line #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=20% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-itemNumber", "Item/Part #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=11% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityOrdered", "Ordered")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width=11% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-balance", "Balance")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=11% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityReceived", "Received")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=11% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityRejected", "Rejected")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=11% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityAccepted", "Accepted")%></td>
					<td width=3% class=browseHdr nowrap>&nbsp;</td>
					<td width=3% class=browseHdr nowrap>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-receiptNotes")%> width=3% class=browseHdr nowrap>&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
<%
		if (poLine != null && poLine.getStatus().compareTo(DocumentStatus.RCV_INVOICED) < 0) {
			BigDecimal balanceQty = (poLine.getQuantity()).subtract(poLine.getQtyReceived());
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
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=6% class=browseRow nowrap align=right><%=poLine.getLineNumber().setScale(1, BigDecimal.ROUND_HALF_UP)%>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=20% class=browseRow nowrap><%=poLine.getItemNumber()%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=11% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid)%> (<%=uom%>)</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width=11% class=browseRow nowrap align=right id="balanceQty"><%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived", s_rec_type)%> width=11% class=browseRow nowrap align=right>
						<input type=text name="ReceiptLine_qtyReceived" value="<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyReceived(), oid)%>" style="text-align:right" size=10 onchange="checkBalance();checkInventory();">
						<tsa:hidden name="balance" value="<%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%>"/>
						<tsa:hidden name="originalQtyReceived" value="<%=poLine.getQtyReceived()%>"/>
						<tsa:hidden name="ReceiptLine_icPoLine" value="<%=poLine.getIcPoLine()%>"/>
						<tsa:hidden name="PoLine_itemSource" value="<%=poLine.getItemSource()%>"/>
						<tsa:hidden name="PoLine_itemNumber" value="<%=poLine.getItemNumber()%>"/>
						<tsa:hidden name="PoLine_itemLocation" value="<%=poLine.getItemLocation()%>"/>
						<tsa:hidden name="PoLine_unitPrice" value="<%=poLine.getUnitPrice()%>"/>
						<tsa:hidden name="PoLine_umCode" value="<%=poLine.getUmCode()%>"/>
						<tsa:hidden name="PoLine_commodity" value="<%=poLine.getCommodity()%>"/>
						<tsa:hidden name="PoLine_receiptRequired" value="<%=poLine.getReceiptRequired()%>"/>
						<tsa:hidden name="PoLine_asset" value="<%=poLine.getAsset()%>"/>
						<tsa:hidden name="PoLine_commodity" value="<%=poLine.getCommodity()%>"/>
						<tsa:hidden name="PoLine_quantity" value="<%=poLine.getQuantity()%>"/>
						<tsa:hidden name="createReturn" value=""/>
						<tsa:hidden name="returnReceiptHeader_receiptNotes" value=""/>
						<tsa:hidden name="returnReceiptHeader_pkgsReceived" value=""/>
						<tsa:hidden name="returnReceiptHeader_packingSlip" value=""/>
						<tsa:hidden name="returnReceiptHeader_carrierCode" value=""/>
						<tsa:hidden name="returnReceiptHeader_returnDate" value=""/>
						<tsa:hidden name="returnReceiptHeader_icRecHeader" value=""/>
						<tsa:hidden name="returnReceiptHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
						<tsa:hidden name="returnReceiptLine_icRecHeader" value=""/>
						<tsa:hidden name="returnReceiptLine_qtyReceived" value=""/>
						<tsa:hidden name="returnReceiptLine_qtyRejected" value=""/>
						<tsa:hidden name="returnReceiptLine_qtyReturned" value=""/>
						<tsa:hidden name="returnReceiptLine_qtyAccepted" value=""/>
						<tsa:hidden name="returnReceiptLine_icPoLine" value="<%=poLine.getIcPoLine()%>"/>
						<tsa:hidden name="returnReceiptLine_dispositionCode" value=""/>
						<tsa:hidden name="returnReceiptLine_receiptNotes" value=""/>
						<tsa:hidden name="receiptLineFactor" value=""/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=11% class=browseRow nowrap align=right>
						<input type=text name="ReceiptLine_qtyRejected" value="<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyRejected(), oid)%>" style="text-align:right" size=10 onchange="setReceived();checkRejected();">
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=11% class=browseRow nowrap align=right>
						<input type=text name="ReceiptLine_qtyAccepted" value="<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyAccepted(), oid)%>" style="text-align:right" size=10 readOnly>
					</td>
					<td width=3% class=browseRow nowrap>
						<div id="receiveAllLink">
<%	if (balanceQty.compareTo(bd_zero) >= 1 && allowEdit) {%><a href="javascript: receiveAllForItem(); void(0);"><img src="<%=contextPath%>/images/checkmark.gif" border=0 alt="Receive Remaining Balance" tabIndex=-1></a><%}%>
						</div>
					</td>
<%  if (extendedInventory) { %>
					<td width=3% class=browseRow align=center>
						<div id="invBins"><a href="javascript: checkInventory(); void(0);"><img src="<%=contextPath%>/images/cmd_add_item.gif" border=0 alt="Enter/Edit Inventory Bin Information" tabIndex=-1></a></div>
					</td>
<% } %>
					<td width=3% class=browseRow align=center>
						<div id="receiptNotes"><a href="javascript: viewReceiptNotes(); void(0);"><img src="<%=contextPath%>/images/cmd_edit.gif" border=0 alt="Enter Item Receipt Notes" tabIndex=-1></a></div>
					</td>
				</tr>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td class=browseRow valign=top><%=poLine.getDescription()%></td>
					<td colspan=5 class=browseRow valign=top align=right>
						<table border=0 cellspacing=0 cellpadding=1 class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rec-inspectionCode")%>>
							<td height=16px align=right><a href="javascript: browseStd('ReceiptLine_inspectionCode', 'INSP', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inspectionCode", "Inspection Code")%>:</a></td>
							<td height=16px><input type=text name="ReceiptLine_inspectionCode" value="<%=receiptLine.getInspectionCode()%>" size=20 onchange="upperCase(this);"></td>
						</tr>
						<tr id="itemRejectionCode" style="visibility: hidden; display:none;">
							<td height=16px align=right><a href="javascript: browseStd('ReceiptLine_rejectionCode', 'REJ', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-rejectionCode", "Rejection Code",true)%>:</a></td>
							<td height=16px><input type=text name="ReceiptLine_rejectionCode" value="<%=receiptLine.getRejectionCode()%>" size=20 onchange="upperCase(this);"></td>
						</tr>
						<tr id="itemDispositionCode" style="visibility: hidden; display:none;">
							<td  <%=HtmlWriter.isVisible(oid, "rec-dispositionCode")%> height=16px align=right><a href="javascript: browseStd('ReceiptLine_dispositionCode', 'DISP', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-dispositionCode", "Disposition Code", true)%>:</a></td>
							<td  <%=HtmlWriter.isVisible(oid, "rec-dispositionCode")%> height=16px><input type=text name="ReceiptLine_dispositionCode" value="<%=receiptLine.getDispositionCode()%>" size=20 onchange="upperCase(this);"></td>
						</tr>
						<tr id="itemReturnOption" style="visibility: hidden; display:none;">
							<td height=16px colspan=2 align=right><a href="javascript: returnRejected(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-returnRejectedItems", "Return Rejected Items")%></a></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rec-LN01")%>>
							<td height=16px align=right><a href="javascript: browseStd('ReceiptLine_udf1Code', 'LN01', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN01", "Line UDF 1")%>:</a></td>
							<td height=16px><input type=text name="ReceiptLine_udf1Code" value="<%=receiptLine.getUdf1Code()%>" size=20 maxLength=15 onchange="upperCase(this);"></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rec-LN02")%>>
						<% if (oid.equals("SRR10P")) { %>
							<td height=16px align=right><a href="javascript: browseStd('ReceiptLine_udf2Code', 'PROCLVL', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN02", "Line UDF 2")%>:</a></td>
							<td height=16px><input type=text name="ReceiptLine_udf2Code" value="<%=receiptLine.getUdf2Code()%>" size=20 maxLength=15 onchange="upperCase(this);"></td>
						<% } else { %>
							<td height=16px align=right><a href="javascript: browseStd('ReceiptLine_udf2Code', 'LN02', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN02", "Line UDF 2")%>:</a></td>
							<td height=16px><input type=text name="ReceiptLine_udf2Code" value="<%=receiptLine.getUdf2Code()%>" size=20 maxLength=15 onchange="upperCase(this);"></td>
						<% } %>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rec-LN03")%>>
							<td height=16px align=right>
								<% if (DictionaryManager.isLink(oid, "rec-LN03")) { %>
									<a href="javascript: browseStd('ReceiptLine_udf3Code', 'LN03', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN03", "Line UDF 3")%>:</a>
								<% } else { %>
									<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN03", "Line UDF 3")%>:
								<% } %>
							</td>
							<td height=16px><input type=text name="ReceiptLine_udf3Code" value="<%=receiptLine.getUdf3Code()%>" size=20 maxLength=15 onchange="upperCase(this);"></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rec-LN04")%>>
							<td height=16px align=right><a href="javascript: browseStd('ReceiptLine_udf4Code', 'LN04', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN04", "Line UDF 4")%>:</a></td>
							<td height=16px><input type=text name="ReceiptLine_udf4Code" value="<%=receiptLine.getUdf4Code()%>" size=20 maxLength=15 onchange="upperCase(this);"></td>
						</tr>
					<%if (!oid.equalsIgnoreCase("vse06p")) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rec-LN05")%>>
							<td height=16px align=right><a href="javascript: browseStd('ReceiptLine_udf5Code', 'LN05', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN05", "Line UDF 5")%>:</a></td>
							<td height=16px><input type=text name="ReceiptLine_udf5Code" value="<%=receiptLine.getUdf5Code()%>" size=20 maxLength=15 onchange="upperCase(this);"></td>
						</tr>
					<% } %>
						</table>
					</td>
					<td colspan=2 class=browseRow nowrap>&nbsp;</td>
				</tr>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td valign=top>
						<div id="openItemDetails">
						<table border=0 cellspacing=0 cellpadding=1>
						<tr>
							<td align=right valign=middle><a href="javascript: viewItemDetails(); void(0);" tabIndex="-1"><img src="<%=contextPath%>/images/plus.gif" border=0></a></td>
							<td valign=middle><a href="javascript: viewItemDetails(); void(0);" tabIndex="-1"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-viewItemDetails", "View Item Details")%></a></td>
						</tr>
						</table>
						</div>
						<div id="closeItemDetails" style="visibility: hidden; display: none;">
						<table border=0 cellspacing=0 cellpadding=1>
						<tr>
							<td align=right valign=middle><a href="javascript: hideItemDetails(); void(0);" tabIndex="-1"><img src="<%=contextPath%>/images/minus.gif" border=0></a></td>
							<td valign=middle><a href="javascript: hideItemDetails(); void(0);" tabIndex="-1">Hide Item Details</a></td>
						</tr>
						</table>
						</div>
					</td>
					<td align=right colspan=5>
						<div id="itemReceiptNotes" style="visibility: hidden; display: none;">
						<table border=0 cellspacing=0 cellpadding=1 class=browseRow>
						<tr>
							<td align=right valign=top><a href="javascript: hideReceiptNotes(); void(0);"><img src="<%=contextPath%>/images/bar_close.gif" border=0 alt='Hide Item Receipt Notes'></a></td>
							<td align=right valign=top><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcl-rec-receiptNotes", "Notes", false)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rcl-rec-receiptNotes")%> valign=top><textarea name="ReceiptLine_receiptNotes" cols=45 rows=4 onKeyDown="textCounter(this, 225);" onKeyUp="textCounter(this,225);">${esapi:encodeForHTML(receiptLine.receiptNotes)}</textarea></td>
						</tr>
						</table>
						</div>
					</td>
					<td colspan=2 class=browseRow nowrap>&nbsp;</td>
				</tr>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td colspan=6 class=browseRow>
						<div id="itemDetails" style="visibility: hidden; display: none;">
						<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rec-endUser")%> height=16px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "End User", false)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-endUser")%> height=16px nowrap><a href="javascript: viewUserInfo('<%=endUser%>'); void(0);"><%=UserManager.getInstance().getUser(oid,endUser).getDisplayName()%></a></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-asset")%> height=16px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-asset", "Asset", false)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-asset")%> height=16px  colspan=3>
								<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
								<tr>
									<td valign=middle><input type="checkbox" name="c_asset" <% if (poLine.getAsset().equals("Y") ) {%> checked="checked" <% } %> disabled="disabled"/></td>
	<%	if (assetsActive && poLine.getAsset().equals("Y") && Integer.valueOf(poLine.getStatus()).intValue() >= 3030 ) {%>
									<td valign=middle align=right><a href="javascript: viewAssetRelated('<%=iclinekey%>'); "><img src="<%=contextPath%>/images/asset3.gif" border=0></a></td>
									<td valign=middle><a href="javascript: viewAssetRelated('<%=iclinekey%>'); ">View Asset Related Information</a></td>
	<%	}%>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> height=16px width=18% align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN01", "Line UDF 1", false)%>:</td>
<%
		String s_udf1= poLine.getUdf1Code();
		if (oid.equalsIgnoreCase("vse06p")) {%>
							<td <%=HtmlWriter.isVisible(oid, "po-LN01")%>>
								<input type=checkbox name="c_checkbox" tabindex="9" <% if (s_udf1.equals("Y")) { %> CHECKED <% } %> value="Y" DISABLED>
								<tsa:hidden name="PoLine_udf1Code" value="<%=s_udf1%>"/>
							</td>
<%	} else {	%>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> height=16px width=18%><%=poLine.getUdf1Code()%></td>
<%	}
		String poLine_udf2 = poLine.getUdf2Code();
		if (oid.equalsIgnoreCase("vse06p"))
		{
			poLine_udf2 = InspectionLevel.toString(poLine.getUdf2Code(), oid);
		}
%>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> height=16px width=15% align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN02", "Line UDF 2", false)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> height=16px width=18%><%=poLine_udf2%></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN03")%> height=16px width=15% align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN03", "Line UDF 3", false)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN03")%> height=16px width=16%><%=poLine.getUdf3Code()%></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN04")%> height=16px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN04", "Line UDF 4", false)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN04")%>height=16px ><%=poLine.getUdf4Code()%></td>
						<%if (!oid.equalsIgnoreCase("vse06p")) { %>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN05")%>height=16px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN05", "Line UDF 5", false)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN05")%>height=16px ><%=poLine.getUdf5Code()%></td>
						<% } %>
						</tr>
						</table>
						</div>
					</td>
					<td colspan=2 class=browseRow nowrap>&nbsp;</td>
				</tr>
<%			} %>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	} else if (receiptMethod.equalsIgnoreCase("ReceiveByTransfer")){ %>
<table border=0 cellpadding=0 cellspacing=0 width=780px>
<tr>
	<td align=center width=780px>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=770px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRowHdr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=6% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineNumber", "Line #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=20% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-itemNumber", "Item/Part #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-unitCost")%> width=12% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-unitCost", "Unit Cost")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityUom")%> width=8% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityUom", "UOM")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=8% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityOrdered", "Ordered")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width=8% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-balance", "Balance")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=10% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityReceived", "Received")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=10% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityRejected", "Rejected")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=10% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityAccepted", "Accepted")%></td>
				<%  if (extendedInventory) { %>
					<td width=3% class=browseHdr nowrap>&nbsp;</td>
					<td width=3% class=browseHdr nowrap>&nbsp;</td>
				<% } %>
					<td <%=HtmlWriter.isVisible(oid, "rec-receiptNotes")%> width=8% class=browseHdr nowrap>&nbsp;</td>
				</tr>

<%
		if (reqLine != null && (reqLine.getStatus().compareTo(DocumentStatus.INV_BACKORDERED) == 0 || reqLine.getStatus().compareTo(DocumentStatus.INV_DISBURSED) == 0 || reqLine.getStatus().compareTo(DocumentStatus.RCV_PARTIALLYRECEIVED) == 0 || reqLine.getStatus().compareTo(DocumentStatus.RCV_RECEIVED) == 0)) {
			BigDecimal balanceQty = (reqLine.getQuantity()).subtract(reqLine.getAllocated());
			String	endUser = reqLine.getRequisitionerCode();
			if (balanceQty.compareTo(bd_zero) <= 0) {
				balanceQty = bd_zero;
			}
			List receiptLineList = reqLine.getReceiptLineList();
			BigDecimal iclinekey = reqLine.getIcReqLine();
			String	uom = reqLine.getUmCode();
			if (HiltonUtility.isEmpty(uom)) {
				uom = "EA";
			}
%>
				<tr class=browseRow>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=6% class=browseRow nowrap align=right>
						<%	if (reqLine.getLineNumber().compareTo(new BigDecimal(0)) == 0) { %>
							<%=new BigDecimal(1).setScale(1, BigDecimal.ROUND_HALF_UP)%>&nbsp;
						<%	} else { %>
							<%=reqLine.getLineNumber().setScale(1, BigDecimal.ROUND_HALF_UP)%>&nbsp;
						<%	} %>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=20% class=browseRow nowrap>
						<input type=text name="RequisitionLine_itemNumber" size=25 maxlength=30 value="<%=reqLine.getItemNumber()%>" onchange="upperCase(this); getItemInfo(); void(0);">
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-unitCost")%> width=12% class=browseRow nowrap align=right>
						<input type=text name="RequisitionLine_unitPrice" size=10 maxlength=25 value="<%=HiltonUtility.getFormattedPrice(reqLine.getUnitPrice(), oid)%>" style="text-align:right" onchange="formatUnitPrice();">
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityUom")%> width=8% class=browseRow nowrap align=right>
						<a href="javascript: browseLookup('RequisitionLine_umCode', 'unitofmeasure'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-uom", "UOM", true)%></a>:
						<input type=text name="RequisitionLine_umCode" size=3 maxlength=15 value="<%=reqLine.getUmCode()%>" onchange="upperCase(this); updateUMFactor();">
						<tsa:hidden name="RequisitionLine_umFactor" value="1"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=8% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid)%> (<%=uom%>)</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width=8% class=browseRow nowrap align=right id="balanceQty"><%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=10% class=browseRow nowrap align=right>
						<input type=text name="ReceiptLine_qtyReceived" size=10 maxlength=25 value="<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyReceived(), oid)%>" style="text-align:right" onchange="checkBalance();" >
						<tsa:hidden name="balance" value="<%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%>"/>
						<tsa:hidden name="originalQtyReceived" value="<%=reqLine.getAllocated()%>"/>
						<tsa:hidden name="ReceiptLine_icPoLine" value="<%=reqLine.getIcReqLine()%>"/>
						<tsa:hidden name="RequisitionLine_itemSource" value="<%=reqLine.getItemSource()%>"/>
						<!--  Transfer receive to Ship To Location -->
						<tsa:hidden name="RequisitionLine_itemLocation" value="<%=receiptHeader.getShipToCode()%>"/>
						<tsa:hidden name="RequisitionLine_quantity" value="<%=reqLine.getQuantity()%>"/>
						<tsa:hidden name="RequisitionLine_receiptRequired" value="<%=reqLine.getReceiptRequired()%>"/>
						<tsa:hidden name="RequisitionLine_asset" value="<%=reqLine.getAsset()%>"/>
						<tsa:hidden name="RequisitionLine_commodityCode" value="<%=reqLine.getCommodityCode()%>"/>
						<tsa:hidden name="createReturn" value=""/>
						<tsa:hidden name="returnReceiptHeader_receiptNotes" value=""/>
						<tsa:hidden name="returnReceiptHeader_pkgsReceived" value=""/>
						<tsa:hidden name="returnReceiptHeader_packingSlip" value=""/><!--RMA #-->
						<tsa:hidden name="returnReceiptHeader_carrierCode" value=""/>
						<tsa:hidden name="returnReceiptHeader_returnDate" value=""/>
						<tsa:hidden name="returnReceiptHeader_icRecHeader" value=""/>
						<tsa:hidden name="returnReceiptHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
						<tsa:hidden name="returnReceiptLine_icRecHeader" value=""/>
						<tsa:hidden name="returnReceiptLine_qtyReceived" value=""/>
						<tsa:hidden name="returnReceiptLine_qtyRejected" value=""/>
						<tsa:hidden name="returnReceiptLine_qtyReturned" value=""/>
						<tsa:hidden name="returnReceiptLine_icPoLine" value=""/>
						<tsa:hidden name="returnReceiptLine_dispositionCode" value=""/>
						<tsa:hidden name="returnReceiptLine_receiptNotes" value=""/>
						<tsa:hidden name="receiptLineFactor" value=""/>
						<tsa:hidden name="count_flag" value=""/>
						<tsa:hidden name="icRc" value=""/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=10% class=browseRow nowrap align=right>
						<input type=text name="ReceiptLine_qtyRejected" size=10 value="<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyRejected(), oid)%>" style="text-align:right" onchange="setReceived(); checkRejected();">
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=10% class=browseRow nowrap align=right>
						<input type=text name="ReceiptLine_qtyAccepted" size=10 value="<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyAccepted(), oid)%>" style="text-align:right" readonly>
					</td>
					<td width=3% class=browseRow nowrap>
						<div id="receiveAllLink">
<%	if (balanceQty.compareTo(bd_zero) >= 1 && allowEdit) {%><a href="javascript: receiveAllForItem(); void(0);"><img src="<%=contextPath%>/images/checkmark.gif" border=0 alt="Receive Remaining Balance" tabIndex=-1></a><%}%>
						</div>
					</td>
<%  if (extendedInventory) { %>
					<td width=3% class=browseRow align=center>
						<div id="invBins"><a href="javascript: checkTransferInventory(); void(0);"><img src="<%=contextPath%>/images/cmd_add_item.gif" border=0 alt="Enter/Edit Inventory Bin Information" tabIndex=-1></a></div>
					</td>
<% } %>
					<td <%=HtmlWriter.isVisible(oid, "rec-receiptNotes")%> width=3% class=browseRow nowrap align=right>
						<div id="receiptNotes"><a href="javascript: viewReceiptNotes(); void(0);"><img src="<%=contextPath%>/images/cmd_edit.gif" border=0 alt="Enter Item Receipt Notes" tabIndex=-1></a></div>
					</td>
				</tr>
				<tr>
<%				if (isDualUm) { %>
					<td <%=HtmlWriter.isVisible(oid, "rec-duomUmCode")%> align=right colspan=3 nowrap>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-duomUmCode", "Secondary UOM", true)%>:
						<input type=text name="ReceiptLine_duomUmCode" tabindex=6 size="3" maxlength=15 value="<%=duomUmCode%>" onchange="upperCase(this);" disabled>
					</td>
					<td colspan="2">&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-duomQtyReceived")%> colspan=1 align=right class=browseRow valign=top>
						<input type=text name="ReceiptLine_duomQtyReceived" tabindex=5 size="10" maxlength=25 value="<%=HiltonUtility.getFormattedQuantity(receiptLine.getDuomQtyReceived(), oid)%>" onchange="verifyDuomQty();" style="text-align:right" >
					</td>
<% } else { %>
					<td>
						<tsa:hidden name="ReceiptLine_duomQtyReceived" value="0"/>
						<tsa:hidden name="ReceiptLine_duomUmCode" value=""/>
					</td>
<% } %>
				</tr>
				<tr>

					<td <%=HtmlWriter.isVisible(oid, "rec-inventoryLocation")%> colspan=2 class=browseRow nowrap align=right>
						<a href="javascript: browseLookup('ReceiptLine_itemLocation', 'item_location'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inventoryLocation", "Inventory Location")%> for this receipt or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inventoryLocation", "Inventory Location", true)%></a>:&nbsp;
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-inventoryLocation")%> colspan=3 class=browseRow valign=top>
						<input type=text name="ReceiptLine_itemLocation" value="<%=receiptLine.getItemLocation()%>" tabIndex=6 onchange="upperCase(this);">
					</td>
				</tr>
				<tr>
					<td class=browseRow>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-description")%> colspan=4 class=browseRow valign=top>
						<textarea wrap="virtual" name="RequisitionLine_description" rows=5 cols=60 onKeyDown="textCounter(this, 2000);" onKeyUp="textCounter(this,2000);" onchange="textCounter(this,2000);">${esapi:encodeForHTML(reqLine.description)}</textarea>
					</td>
					<td colspan=5 class=browseRow valign=top align=right>
						<table id="inspectionCodes" border=0 cellspacing=0 cellpadding=1 class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rec-inspectionCode")%>>
							<td height=16px align=right>
								<a href="javascript: browseStd('ReceiptLine_inspectionCode', 'INSP', true); void(0);" tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inspectionCode", "Inspection Code")%></a>:&nbsp;
							</td>
							<td height=16px>
								<input type=text name="ReceiptLine_inspectionCode" value="<%=receiptLine.getInspectionCode()%>" size=20 onchange="upperCase(this);">
							</td>
						</tr>
						<tr id="itemRejectionCode" style="visibility: hidden; display:none;">
							<td height=16px align=right>
								<a href="javascript: browseStd('ReceiptLine_rejectionCode', 'REJ', true); void(0);" tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-rejectionCode", "Rejection Code")%></a>:&nbsp;
							</td>
							<td height=16px>
								<input type=text name="ReceiptLine_rejectionCode" value="<%=receiptLine.getRejectionCode()%>" size=20 onchange="upperCase(this);">
							</td>
						</tr>
						<tr id="itemDispositionCode" style="visibility: hidden; display:none;">
							<td height=16px align=right>
								<a href="javascript: browseStd('ReceiptLine_dispositionCode', 'DISP', true); void(0);" tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-dispositionCode", "Disposition Code", true)%></a>:&nbsp;
							</td>
							<td height=16px>
								<input type=text name="ReceiptLine_dispositionCode" value="<%=receiptLine.getDispositionCode()%>" size=20 onchange="upperCase(this);">
							</td>
						</tr>
						<tr id="itemReturnOption" style="visibility: hidden; display:none;">
							<td colspan=2 height=16px align=right>
								<a href="javascript: returnRejected(); void(0);" tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-returnRejectedItems", "Return Rejected Items")%></a>
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
					<td class=browseRow colspan=9>
						<div id="itemDetails" style="visibility: hidden; display: none;">
						<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
						<tr>
						<%	if (!DictionaryManager.isLink(oid, "rec-endUser")) { %>
							<td <%=HtmlWriter.isVisible(oid, "rec-endUser")%> height=16px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "End User", false)%>:</td>
						<%	} else { %>
							<td <%=HtmlWriter.isVisible(oid, "rec-endUser")%> height=16px align=right nowrap><a href="javascript: browseLookup('RequisitionLine_requisitionerCode', 'requisitioner'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "End User")%> for this order or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "Requisitioner")%> in the box on the right." tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "End User", false)%>:</a></td>
						<%	}%>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rec-endUserName")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUserName", "End User Name")%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-endUserName")%> nowrap><input type=text name="as_requisitionerName" size=20 value="<%=UserManager.getInstance().getUser(oid, poLine.getRequisitionerCode()).getDisplayName()%>" onfocus="this.blur();" disabled></td>
						</tr>
						</table>
						</div>
					</td>
				</tr>
<%			} %>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	} else { %>
<table border=0 cellpadding=0 cellspacing=0 width=780px>
<tr>
	<td align=center width=780px>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=770px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRowHdr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=6% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineNumber", "Line #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=20% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-itemNumber", "Item/Part #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-unitCost")%> width=12% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-unitCost", "Unit Cost")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityUom")%> colspan=2 width=12% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityUom", "UOM")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=8% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityOrdered", "Ordered")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width=8% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-balance", "Balance")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=10% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityReceived", "Received")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=10% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityRejected", "Rejected")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=10% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityAccepted", "Accepted")%></td>
					<td width=3% class=browseHdr nowrap>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-receiptNotes")%> width=3% class=browseHdr nowrap>&nbsp;</td>
				</tr>
<%
		if (poLine != null && poLine.getStatus().compareTo(DocumentStatus.RCV_INVOICED) < 0) {
			BigDecimal balanceQty = (poLine.getQuantity()).subtract((poLine.getQtyReceived().add(receiptLine.getQtyAccepted())));
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
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=20% class=browseRow nowrap>
						<input type=text name="PoLine_itemNumber" size=25 maxlength=30 value="<%=poLine.getItemNumber()%>" onchange="upperCase(this); getItemInfo(); void(0);">
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-unitCost")%> width=12% class=browseRow nowrap align=right>
						<input type=text name="PoLine_unitPrice" size=10 maxlength=25 value="<%=HiltonUtility.getFormattedPrice(poLine.getUnitPrice(), oid)%>" style="text-align:right" onchange="formatUnitPrice();">
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityUom")%> width=6% class=browseRow nowrap align=right>
						<a href="javascript: browseLookup('PoLine_umCode', 'unitofmeasure'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-uom", "UOM", true)%></a>:&nbsp;
					</td><td width="6%" align="left">
						<input type=text name="PoLine_umCode" size=3 maxlength=15 value="<%=poLine.getUmCode()%>" onchange="upperCase(this); updateUMFactor();">
						<tsa:hidden name="PoLine_commodity" value="<%=poLine.getCommodity()%>"/>
						<tsa:hidden name="PoLine_umFactor" value="1"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=8% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid)%> (<%=uom%>)</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width=8% class=browseRow nowrap align=right id="balanceQty"><%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=10% class=browseRow nowrap align=right>
						<input type=text name="ReceiptLine_qtyReceived" size=10 maxlength=25 value="<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyReceived(), oid)%>" style="text-align:right" onchange="checkBalance(); checkInventory();">
						<tsa:hidden name="balance" value="<%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%>"/>
						<tsa:hidden name="originalQtyReceived" value="<%=poLine.getQtyReceived()%>"/>
						<tsa:hidden name="ReceiptLine_icPoLine" value="<%=poLine.getIcPoLine()%>"/>
						<tsa:hidden name="PoLine_itemSource" value="<%=poLine.getItemSource()%>"/>
						<tsa:hidden name="PoLine_itemLocation" value="<%=poLine.getItemLocation()%>"/>
						<tsa:hidden name="PoLine_quantity" value="<%=poLine.getQuantity()%>"/>
						<tsa:hidden name="PoLine_receiptRequired" value="<%=poLine.getReceiptRequired()%>"/>
						<tsa:hidden name="PoLine_asset" value="<%=poLine.getAsset()%>"/>
						<tsa:hidden name="createReturn" value=""/>
						<tsa:hidden name="returnReceiptHeader_receiptNotes" value=""/>
						<tsa:hidden name="returnReceiptHeader_pkgsReceived" value=""/>
						<tsa:hidden name="returnReceiptHeader_packingSlip" value=""/><!--RMA #-->
						<tsa:hidden name="returnReceiptHeader_carrierCode" value=""/>
						<tsa:hidden name="returnReceiptHeader_returnDate" value=""/>
						<tsa:hidden name="returnReceiptHeader_icRecHeader" value=""/>
						<tsa:hidden name="returnReceiptHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
						<tsa:hidden name="returnReceiptLine_icRecHeader" value=""/>
						<tsa:hidden name="returnReceiptLine_qtyReceived" value=""/>
						<tsa:hidden name="returnReceiptLine_qtyRejected" value=""/>
						<tsa:hidden name="returnReceiptLine_qtyReturned" value=""/>
						<tsa:hidden name="returnReceiptLine_icPoLine" value="<%=receiptLine.getIcPoLine()%>"/>
						<tsa:hidden name="returnReceiptLine_dispositionCode" value=""/>
						<tsa:hidden name="returnReceiptLine_receiptNotes" value=""/>
						<tsa:hidden name="receiptLineFactor" value=""/>
						<tsa:hidden name="count_flag" value=""/>
						<tsa:hidden name="icRc" value=""/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=10% class=browseRow nowrap align=right>
						<input type=text name="ReceiptLine_qtyRejected" size=10 value="<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyRejected(), oid)%>" style="text-align:right" onchange="setReceived(); checkRejected();">
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=10% class=browseRow nowrap align=right>
						<input type=text name="ReceiptLine_qtyAccepted" size=10 value="<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyAccepted(), oid)%>" style="text-align:right" readonly>
					</td>
					<td width=3% class=browseRow nowrap>
						<div id="receiveAllLink">
<%	if (balanceQty.compareTo(bd_zero) >= 1 && allowEdit) {%><a href="javascript: receiveAllForItem(); void(0);"><img src="<%=contextPath%>/images/checkmark.gif" border=0 alt="Receive Remaining Balance" tabIndex=-1></a><%}%>
						</div>
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
						<tsa:hidden name="ReceiptLine_duomQtyReceived" value="0"/>
						<tsa:hidden name="ReceiptLine_duomUmCode" value=""/>
					</td>
<% } %>
				</tr>
				<tr>

<%				if (s_invitemxref.equalsIgnoreCase("Y")) { %>
					<td class=browseRow>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=15% class=browseRow nowrap align=right>
						<a href="javascript: altItemLookup(); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-itemNumber", "Item Number")%> for this receipt or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-itemNumber", "Item Number")%></a>:&nbsp;
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-altItemNumber")%> colspan=3 class=browseRow valign=top>
						<input type=text name="PoLine_altItemNumber" size=25 maxlength=30 value="<%=poLine.getAltItemNumber()%>" disabled> (<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-altItemNumber", "Alt. Item/Part #")%>)
					</td>
<% } %>
					<td <%=HtmlWriter.isVisible(oid, "rec-inventoryLocation")%> colspan=2 class=browseRow nowrap align=right>
						<a href="javascript: browseLookup('ReceiptLine_itemLocation', 'item_location'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inventoryLocation", "Inventory Location")%> for this receipt or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inventoryLocation", "Inventory Location", true)%></a>:&nbsp;
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-inventoryLocation")%> colspan=3 class=browseRow valign=top>
						<input type=text name="ReceiptLine_itemLocation" value="<%=receiptLine.getItemLocation()%>" tabIndex=6 onchange="upperCase(this);">
					</td>
				</tr>
				<tr>
					<td class=browseRow>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-description")%> colspan=4 class=browseRow valign=top>
						<textarea wrap="virtual" name="PoLine_description" rows=5 cols=60 onKeyDown="textCounter(this, 2000);" onKeyUp="textCounter(this,2000);" onchange="textCounter(this,2000);">${esapi:encodeForHTML(poLine.description)}</textarea>
					</td>
					<td colspan=5 class=browseRow valign=top align=right>
						<table id="inspectionCodes" border=0 cellspacing=0 cellpadding=1 class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rec-inspectionCode")%>>
							<td height=16px align=right>
								<a href="javascript: browseStd('ReceiptLine_inspectionCode', 'INSP', true); void(0);" tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inspectionCode", "Inspection Code")%></a>:&nbsp;
							</td>
							<td height=16px>
								<input type=text name="ReceiptLine_inspectionCode" value="<%=receiptLine.getInspectionCode()%>" size=20 onchange="upperCase(this);">
							</td>
						</tr>
						<tr id="itemRejectionCode" style="visibility: hidden; display:none;">
							<td height=16px align=right>
								<a href="javascript: browseStd('ReceiptLine_rejectionCode', 'REJ', true); void(0);" tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-rejectionCode", "Rejection Code")%></a>:&nbsp;
							</td>
							<td height=16px>
								<input type=text name="ReceiptLine_rejectionCode" value="<%=receiptLine.getRejectionCode()%>" size=20 onchange="upperCase(this);">
							</td>
						</tr>
						<tr id="itemDispositionCode" style="visibility: hidden; display:none;">
							<td height=16px align=right>
								<a href="javascript: browseStd('ReceiptLine_dispositionCode', 'DISP', true); void(0);" tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-dispositionCode", "Disposition Code", true)%></a>:&nbsp;
							</td>
							<td height=16px>
								<input type=text name="ReceiptLine_dispositionCode" value="<%=receiptLine.getDispositionCode()%>" size=20 onchange="upperCase(this);">
							</td>
						</tr>
						<tr id="itemReturnOption" style="visibility: hidden; display:none;">
							<td colspan=2 height=16px align=right>
								<a href="javascript: returnRejected(); void(0);" tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-returnRejectedItems", "Return Rejected Items")%></a>
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
					<td class=browseRow colspan=9>
						<div id="itemDetails" style="visibility: hidden; display: none;">
						<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
						<tr>
						<%	if (!DictionaryManager.isLink(oid, "rec-endUser")) { %>
							<td <%=HtmlWriter.isVisible(oid, "rec-endUser")%> height=16px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "End User", false)%>:</td>
						<%	} else { %>
							<td <%=HtmlWriter.isVisible(oid, "rec-endUser")%> height=16px align=right nowrap><a href="javascript: browseLookup('PoLine_requisitionerCode', 'requisitioner'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "End User")%> for this order or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "Requisitioner")%> in the box on the right." tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "End User", false)%>:</a></td>
						<%	}%>
							<td <%=HtmlWriter.isVisible(oid, "rec-endUser")%>><input type=text name="PoLine_requisitionerCode" tabindex="23" size="20" maxlength=20 value="<%=poLine.getRequisitionerCode()%>" onchange="upperCase(this); getNewInfo('requisitioner', this, 0);" <%if(oid.equalsIgnoreCase("vse06p")){%> disabled <%}%>></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> height=16px align=right nowrap><%if(!oid.equalsIgnoreCase("vse06p")){%><a href="javascript: <% if (oid.equalsIgnoreCase("QRI06P")) {%>browseCommodity('PoLine_udf1Code','subcommodity','<%=propertiesManager.getProperty("MISC", "SubCommodityType", "")%>');<% } else {%> browseStd('PoLine_udf1Code', 'LN01');<%}%> void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN01", "Line UDF 1")%> for this item or enter the value in the box on the right." tabIndex=-1><%}%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN01", "Line UDF 1", true)%></a>:</td>
						<%	if (oid.equalsIgnoreCase("vse06p")) { %>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> height=16px>
								<input type=checkbox name="c_checkbox" tabindex="9" <% if (poLine.getUdf1Code().equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(PoLine_udf1Code, 0);">
								<tsa:hidden name="PoLine_udf1Code" value="<%=poLine.getUdf1Code()%>"/>
							</td>
						<%	} else { %>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> height=16px><input type=text name="PoLine_udf1Code" tabindex="35" size=15 maxlength=15 value="<%=poLine.getUdf1Code()%>" onchange="upperCase(this);"></td>
						<%	} %>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN04")%> height=16px align=right nowrap><a href="javascript: browseStd('PoLine_udf4Code', 'LN04'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN04", "Line UDF 4")%> for this item or enter the value in the box on the right." tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN04", "Line UDF 4", true)%></a>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN04")%> height=16px><input type=text name="PoLine_udf4Code" tabindex="41" size=10 maxlength=10 value="<%=poLine.getUdf4Code()%>" onchange="upperCase(this);"></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rec-endUserName")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUserName", "End User Name")%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-endUserName")%> nowrap><input type=text name="as_requisitionerName" size=20 value="<%=UserManager.getInstance().getUser(oid, poLine.getRequisitionerCode()).getDisplayName()%>" onfocus="this.blur();" disabled></td>
						<%	if (oid.equalsIgnoreCase("vse06p")) { %>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-LN02", "Line UDF2", true)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%>>
								<select tabindex="37" name="PoLine_udf2Code" size=1>
								<%	if (HiltonUtility.isEmpty(poLine.getUdf2Code())) {
										poLine.setUdf2Code(DictionaryManager.getInstance("inspection-level", oid).getLabelProperty("DEFAULT", ""));
									}
									Map inspectionLevels = new TreeMap(DictionaryManager.getInstance("inspection-level", oid).getPropertyMap());
									Iterator inspectionIterator = inspectionLevels.keySet().iterator();
									String	inspectionLevelCode = "";
									String inspectionLevelName = "";
									while (inspectionIterator.hasNext())
									{
										inspectionLevelCode = (String) inspectionIterator.next();
										inspectionLevelName = (String) inspectionLevels.get(inspectionLevelCode);
										if (inspectionLevelCode.equals("DEFAULT")) {
											continue;
										} %>
									<option value="<%=inspectionLevelCode%>" <%if (poLine.getUdf2Code().equalsIgnoreCase(inspectionLevelCode)) {%>SELECTED<%}%>><%=inspectionLevelName%></value>
								<%	} %>
								</select>
							</td>
						<%	} else if (oid.equals("SRR10P")){ %>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> height=16px align=right nowrap><a href="javascript: browseStd('PoLine_udf2Code', 'PROCLVL'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN02", "Line UDF 2")%> for this item or enter the value in the box on the right." tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN02", "Line UDF 2", true)%></a>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> height=16px><input type=text name="PoLine_udf2Code" tabindex="37" size=15 maxlength=15 value="<%=poLine.getUdf2Code()%>" onchange="upperCase(this); "></td>
						<%	} else {%>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> height=16px align=right nowrap><a href="javascript: browseStd('PoLine_udf2Code', 'LN02'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN02", "Line UDF 2")%> for this item or enter the value in the box on the right." tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN02", "Line UDF 2", true)%></a>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> height=16px><input type=text name="PoLine_udf2Code" tabindex="37" size=15 maxlength=15 value="<%=poLine.getUdf2Code()%>" onchange="upperCase(this); "></td>
						<%	} %>
						<%	if (!oid.equalsIgnoreCase("vse06p")) { %>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN05")%>height=16px align=right nowrap><a href="javascript: browseStd('PoLine_udf5Code', 'LN05'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN05", "Line UDF 5")%> for this item or enter the value in the box on the right." tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN05", "Line UDF 5", true)%></a>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN05")%>height=16px><input type=text name="PoLine_udf5Code" tabindex="43" size=15 maxlength=15 value="<%=poLine.getUdf5Code()%>" onchange="upperCase(this); "></td>
						<%	} %>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rec-asset")%> height=16px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-asset", "Asset", false)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-asset")%> height=16px colspan=3>
								<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
								<tr>
									<td valign=middle <%=HtmlWriter.isVisible(oid, "rec-asset")%>>
								<input type=checkbox name="c_asset" tabindex="9" <% if (poLine.getAsset().equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setAssetBox(PoLine_asset, 0);">
									</td>
									<td valign=middle <%=HtmlWriter.isVisible(oid, "rec-gfpProperty")%> height=16px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-gfpProperty", "Gov't Property", false)%>:</td>
									<td valign=middle <%=HtmlWriter.isVisible(oid, "rec-gfpProperty")%>><input type="checkbox" name="c_gfp" value=Y onclick="setGfp(InvProperty_flag);"></td>
									<tsa:hidden name="gfp" value="I"/>
									<td valign=middle <%=HtmlWriter.isVisible(oid, "rec-fgpProperty")%> height=16px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-fgpProperty", "Foreign Gov't Property", false)%>:</td>
									<td valign=middle <%=HtmlWriter.isVisible(oid, "rec-fgpProperty")%>><input type="checkbox" name="c_fgp" value=Y onclick="setFgp(InvProperty_flag);"></td>
								</tr>
								</table>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN03")%> height=16px align=right nowrap><a href="javascript: browseStd('PoLine_udf3Code', 'LN03'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN03", "Line UDF 3")%> for this item or enter the value in the box on the right." tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN03", "Line UDF 3", true)%></a>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN03")%> height=16px><input type=text name="PoLine_udf3Code" tabindex="39" size=10 maxlength=10 value="<%=poLine.getUdf3Code()%>" onchange="upperCase(this); "></td>
						</tr>
						</table>
						</div>
					</td>
				</tr>
				<tr>
					<td class=browseRow>&nbsp;</td>
					<td class=browseRow colspan=9 valign=top>
						<div id="invPropertyArea" style="visibility: hidden; display: none;">
							<table border=0 cellspacing=0 cellpadding=1 id="invPropertiesTable">
							<tr>
								<td>
									<hr size=0>
									<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
									<tr>
										<td align=right width=5px></td>
	<!--
										<td <%=HtmlWriter.isVisible(oid, "rec-invprop-tagnumber")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-invprop-tagnumber", "GPIN", false)%>:</td>
										<td <%=HtmlWriter.isVisible(oid, "rec-invprop-tagnumber")%>>
											<input type=text name="InvProperty_tagNumber" value="" size=35>
										</td>
-->
										<td <%=HtmlWriter.isVisible(oid, "rec-invprop-serialnumber")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-invprop-serialnumber", "Serial #", false)%>:</td>
										<td <%=HtmlWriter.isVisible(oid, "rec-invprop-serialnumber")%>><input type=text name="InvProperty_serialNumber" value="" size=35></td>

										<td width=5px align=center>&nbsp;
											<tsa:hidden name="InvProperty_itemNumber" value=""/>
											<tsa:hidden name="InvProperty_icRc" value=""/>
											<tsa:hidden name="InvProperty_icPoLine" value=""/>
											<tsa:hidden name="InvProperty_receiptRow" value=""/>
										</td>
									</tr>
<!--
									<tr>
										<td align=right width=5px>&nbsp;</td>
										<td <%=HtmlWriter.isVisible(oid, "rec-invprop-contractnumber")%> align=right nowrap><a href="javascript: void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-invprop-contractnumber", "Contract", false)%></a>:</td>
										<td <%=HtmlWriter.isVisible(oid, "rec-invprop-contractnumber")%>><input type=text name="InvProperty_contract" value=""></td>
										<td <%=HtmlWriter.isVisible(oid, "rec-invprop-shipnumber")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-invprop-shipnumber", "Shipping Number", false)%>:</td>
										<td <%=HtmlWriter.isVisible(oid, "rec-invprop-shipnumber")%> colspan=2><input type=text name="InvProperty_shipNumber" value=""></td>
									</tr>
									<tr>
										<td align=right width=5px>&nbsp;</td>
										<td <%=HtmlWriter.isVisible(oid, "rec-invprop-ponumber")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-invprop-ponumber", "PO Number", false)%>:</td>
										<td <%=HtmlWriter.isVisible(oid, "rec-invprop-ponumber")%>><input type=text name="InvProperty_poNumber" value=""></td>
										<td <%=HtmlWriter.isVisible(oid, "rec-invprop-cblout")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-invprop-cblout", "CBL Out", false)%>:</td>
										<td <%=HtmlWriter.isVisible(oid, "rec-invprop-cblout")%> colspan=2><input type=text name="InvProperty_cblOutNumber" value=""></td>
									</tr>
										<td align=right width=5px>&nbsp;</td>
										<td <%=HtmlWriter.isVisible(oid, "rec-invprop-armynumber")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-invprop-armynumber", "Army #", false)%>:</td>
										<td <%=HtmlWriter.isVisible(oid, "rec-invprop-armynumber")%> colspan=4><input type=text name="InvProperty_armyNumber" value=""></td>
									</tr>
									<tr>
										<td align=right width=5px>&nbsp;</td>
										<td <%=HtmlWriter.isVisible(oid, "rec-invprop-remarks")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-invprop-remarks", "Remarks", false)%>:</td>
										<td <%=HtmlWriter.isVisible(oid, "rec-invprop-remarks")%> colspan=4><input type=text name="InvProperty_remarks" value="" size=50></td>
									</tr>
-->
									</table>
								</td>
							</tr>
							</table>
						</div>
					</td>
				</tr>
<%			} %>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	} %>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=780px>
<tr>
<%	if (allowEdit) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis('<%=s_return_page%>', 'ReceiptLineUpdateById;<%=s_return_method%>'); void(0);">Save</a></div></td>
<%	} %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=s_return_page%>', '<%=s_return_method%>'); void(0);">Return</a></div></td>
</tr>
</table>

<table>
<tr>
	<td>
		<tsa:hidden name="PoLine_discOvr" value="<%=poLine.getDiscOvr()%>"/>
		<tsa:hidden name="PoLine_discountPercent" value="<%=HiltonUtility.getBigDecimalFormatted(poLine.getDiscountPercent(), 2)%>"/>
		<tsa:hidden name="PoLine_discountAmount" value="<%=HiltonUtility.getFormattedDollar(poLine.getDiscountAmount(), oid)%>"/>
		<tsa:hidden name="PoLine_taxOvr" value="<%=poLine.getTaxOvr()%>"/>
		<tsa:hidden name="PoLine_taxPercent" value="<%=HiltonUtility.getBigDecimalFormatted(poLine.getTaxPercent(), 5)%>"/>
		<tsa:hidden name="PoLine_taxAmount" value="<%=HiltonUtility.getFormattedDollar(poLine.getTaxAmount(), oid)%>"/>
		<tsa:hidden name="PoLine_shipOvr" value="<%=poLine.getShipOvr()%>"/>
		<tsa:hidden name="PoLine_shippingCharges" value="<%=HiltonUtility.getFormattedDollar(poLine.getShippingCharges(), oid)%>"/>
		<tsa:hidden name="PoLine_otherOvr" value="<%=poLine.getOtherOvr()%>"/>
		<tsa:hidden name="PoLine_otherCharges" value="<%=HiltonUtility.getFormattedDollar(poLine.getOtherCharges(), oid)%>"/>
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
		var q = nformat(eval(nfilter(frm.ReceiptLine_qtyReceived)), qtyDecimals);
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
			displayArea("invPropertyArea");
		} else {
			frm.InvProperty_flag.value = "I";
			hideArea("invPropertyArea");
		}
		frm.gfp.value =  frm.InvProperty_flag.value;
	}

	function setFgp(InvProperty_flag) {
		if (frm.c_fgp.checked) {
			frm.c_asset.checked = false;
			frm.c_gfp.checked = false;
			frm.InvProperty_flag.value = "A";
			displayArea("invPropertyArea");
		} else {
			frm.InvProperty_flag.value = "I";
			hideArea("invPropertyArea");
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
		frm.ReceiptLine_qtyReceived.value = balance;
		frm.ReceiptLine_qtyRejected.value = nformat(0, qtyDecimals);
		checkBalance();
	}

	function checkBalance() {
		var receiptQty = nformat(eval(nfilter(frm.ReceiptLine_qtyReceived)), qtyDecimals);
		var rejectedQty = nformat(eval(nfilter(frm.ReceiptLine_qtyRejected)), qtyDecimals);
		var acceptedQty = nformat(receiptQty - rejectedQty, qtyDecimals);
		//var originalBalance = nformat(eval(nfilter(frm.balance)), qtyDecimals);

		<% if (receiptMethod.equalsIgnoreCase("ReceiveByTransfer")){ %>
			var quantity = frm.RequisitionLine_quantity.value;
		<% } else { %>
			var quantity = frm.PoLine_quantity.value - frm.originalQtyReceived.value ;
		<% } %>

		var newBalance = nformat(quantity - acceptedQty, qtyDecimals);

		frm.ReceiptLine_qtyReceived.value = receiptQty;
		frm.ReceiptLine_qtyAccepted.value = acceptedQty;

		if (newBalance <= 0) {
			var d = document.all("balanceQty");
			d.innerHTML = nformat(0.0, qtyDecimals);
			var imgs = document.all("receiveAllLink");
			imgs.style.visibility = "hidden";
		} else {
			var d = document.all("balanceQty");
			d.innerHTML = newBalance;
			var imgs = document.all("receiveAllLink");
			imgs.style.visibility = "visible";
		}
	}

	function checkTransferBalance() {
		var receiptQty = nformat(eval(nfilter(frm.ReceiptLine_qtyReceived)), qtyDecimals);
		var rejectedQty = nformat(eval(nfilter(frm.ReceiptLine_qtyRejected)), qtyDecimals);
		var acceptedQty = nformat(receiptQty - rejectedQty, qtyDecimals);
		//var originalBalance = nformat(eval(nfilter(frm.balance)), qtyDecimals);
		var newBalance = nformat(frm.RequisitionLine_quantity.value - acceptedQty, qtyDecimals);

		frm.ReceiptLine_qtyReceived.value = receiptQty;
		frm.ReceiptLine_qtyAccepted.value = acceptedQty;

		if (newBalance <= 0) {
			var d = document.all("balanceQty");
			d.innerHTML = nformat(0.0, qtyDecimals);
			var imgs = document.all("receiveAllLink");
			imgs.style.visibility = "hidden";
		} else {
			var d = document.all("balanceQty");
			d.innerHTML = newBalance;
			var imgs = document.all("receiveAllLink");
			imgs.style.visibility = "visible";
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

	function checkInventory() {
		var shipToInv = frm.ReceiptHeader_shipToInv.value;
		var itemLocation = frm.PoLine_itemLocation.value;
		var itemNumber = frm.PoLine_itemNumber.value;
		var itemCost = frm.PoLine_unitPrice.value;
		var umCode = frm.PoLine_umCode.value;
		var commodity = "<%=s_commodity%>" ;
		var receiptOption = frm.PoLine_receiptRequired.value;
		var icPoLine = frm.ReceiptLine_icPoLine.value;
		var qtyReceived = frm.ReceiptLine_qtyAccepted.value;
		var duomQtyReceived = frm.ReceiptLine_duomQtyReceived.value;
		var duomUmCode = frm.ReceiptLine_duomUmCode.value;
		var receiptLineFactor = frm.receiptLineFactor.value;
		var assetFlag = frm.PoLine_asset.value ;

		if (extendedInventory && isInventoryItem && shipToInv == "Y" && itemLocation.length > 0 && (receiptOption == "R" || receiptOption == "E")) {
			if (qtyReceived == 0) {
				popupParameters = "InvBinLocation_itemNumber=" + itemNumber + ";InvBinLocation_itemLocation=" + itemLocation + ";InvBinLocation_tempIc=" + frm.InvBinLocation_tempIc.value;

				setLookupParameters('/system/hide_iframe.jsp', 'InvBinLocationDeleteItemByTempIc');
				displayArea('getInfoFrame');
				document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
			} else {
				popupParameters = "InvItem_itemNumber=" + itemNumber + ";InvBinLocation_itemNumber=" + itemNumber + ";InvBinLocation_itemLocation=" + itemLocation + ";InvLocation_itemNumber=" + itemNumber + ";InvLocation_itemLocation=" + itemLocation + ";qtyReceived=" + qtyReceived + ";duomQtyReceived=" + duomQtyReceived + ";index=0;InvBinLocation_tempIc=" + frm.InvBinLocation_tempIc.value + ";InvBinLocation_cost=" + itemCost + ";PoLine_icPoLine=" + icPoLine + ";PoLine_umCode=" + umCode + ";commodity=" + commodity + ";receiptRow=0;receiptLineFactor=" + receiptLineFactor;
				doSubmitToPopup('receipts/rec_inv_bin_locations.jsp', 'InvItemRetrieveById;InvBinLocationRetrieveByItem', 'WIDTH=800px', 'HEIGHT=500px');
			}
		} else {
			if (assetFlag == "Y") {
				checkProperty() ;
			}
		}
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
		var qtyReceived = frm.ReceiptLine_qtyAccepted.value;
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
		var qtyReceived = frm.ReceiptLine_qtyAccepted.value;
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
		var qtyReceived = nformat(eval(nfilter(frm.ReceiptLine_qtyReceived)), qtyDecimals);
		var qtyRejected = nformat(eval(nfilter(frm.ReceiptLine_qtyRejected)), qtyDecimals);
		var qtyReceivedValue = eval(nfilter(frm.ReceiptLine_qtyReceived));
		var qtyRejectedValue = eval(nfilter(frm.ReceiptLine_qtyRejected));

		frm.ReceiptLine_qtyRejected.value = qtyRejected;
		if (qtyRejectedValue > qtyReceivedValue) {
			alert("You cannot reject more than the quantity you have received.");
			qtyRejected = qtyReceived;
			frm.ReceiptLine_qtyRejected.value = qtyReceived;
		}
		checkBalance();
	}

	function checkRejected() {
		var qtyRejected = nformat(eval(nfilter(frm.ReceiptLine_qtyRejected)), qtyDecimals);
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
		var qtyReceived = eval(nfilter(frm.ReceiptLine_qtyReceived));
		var totalQtyRecieved = eval(originalQtyReceived + qtyReceived);

		popupParameters = "ReceiptHeader_icPoHeader=" + frm.ReceiptHeader_icPoHeader.value;
		popupParameters = popupParameters + ";icPoLine=" + frm.ReceiptLine_icPoLine.value;
		popupParameters = popupParameters + ";qtyRejected=" + frm.ReceiptLine_qtyRejected.value;
		popupParameters = popupParameters + ";qtyReceived=" + totalQtyRecieved;
		popupParameters = popupParameters + ";dispositionCode=" + frm.ReceiptLine_dispositionCode.value;
		popupParameters = popupParameters + ";currentRow=0";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		popupParameters = popupParameters + ";receiptMethod=" + frm.receiptMethod.value;

		doSubmitToPopup('receipts/rec_return_popup.jsp', 'ReceiptCreateRetrieve', 'width=692px', 'height=450px');
	}

	function checkViewItem(row, checkInv, itemSource, hasInvLocation) {
		if (checkInv) {
			if ("<%=s_shipToInv%>" == "Y") {
				if (itemSource == "INV") {
					if (hasInvLocation) {
						retrieveLine(row);
					} else {
						alert("This Location is not established for this inventory item.\nThis location must be set up for this Inventory item by\nsupply/materials coordinator.");
					}
				} else {
					if (confirm("This is not an inventory item.\nTo receive as inventory, this item must be setup as an inventory item assigned to this location.\nClick \"OK\" to continue and receive this as non-inventory.")) {
						retrieveLine(row);
					}
				}
			} else {
				retrieveLine(row);
			}
		} else {
			retrieveLine(row);
		}
	}

	function retrieveLine(linenumber) {
		frm.lineToRetrieve.value = linenumber;
		doSubmit('/receipts/rec_item.jsp', 'ReceiptLineUpdate;PoLineUpdateBalanceReceiptLine;ReceiptHeaderRetrieveById;ReceiptLineRetrieveByLineNumber');
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
	<%	if (isDualUm) { %>
		if (frm.ReceiptLine_duomQtyReceived && frm.ReceiptLine_duomQtyReceived.value > 0) {
			doSubmit(page, handlers);
		} else {
			alert("You must enter a quantity greater than ZERO for the Secondary UOM.");
		}
	<%	} else { %>
		doSubmit(page, handlers);
	<%	} %>
	}

// End Hide script -->
</SCRIPT>