<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.menu.MenuManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.entity.PoHeader" %>
<%@ page import="com.tsa.puridiom.catalogsecurity.tasks.CatalogSecurityManager" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/browse.js"></SCRIPT>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	String	s_ic_rec_header = (String)request.getAttribute("ReceiptHeader_icRecHeader");
	String	s_rec_number = (String)request.getAttribute("ReceiptHeader_receiptNumber");
	String	s_rec_type = (String)request.getAttribute("ReceiptHeader_receiptType");
	String	s_rec_status = (String)request.getAttribute("ReceiptHeader_receiptStatus");
	String	s_rec_fiscal_year = (String)request.getAttribute("ReceiptHeader_fiscalYear");
	if (HiltonUtility.isEmpty(s_rec_status)) {
		s_rec_status = DocumentStatus.RCV_INPROGRESS;
	}

	String 	s_receiveAll = propertiesManager.getProperty("REC DEFAULTS", "RECEIVEALLENABLED", "Y");

	String	s_ic_po_header = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_icPoHeader"));
	String	s_ic_req_header = HiltonUtility.ckNull((String)request.getAttribute("RequisitionHeader_icReqHeader"));
	String	s_po_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_poNumber"));
	String	s_req_number = HiltonUtility.ckNull((String)request.getAttribute("RequisitionHeader_requisitionNumber"));
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

	String	receiptMethod = HiltonUtility.ckNull((String)request.getAttribute("receiptMethod"));
	String	s_current_process = "STEP2";
	String	s_current_page = "/receipts/rec_items_to_step_2.jsp";
	String	s_current_method = "ReceiptHeaderRetrieveById";
	String	s_current_process_method = "";

	BigDecimal	bd_zero = new BigDecimal(0);

	ReceiptHeader	receiptHeader = (ReceiptHeader)request.getAttribute("receiptHeader");
	if (receiptHeader == null) {
		receiptHeader = new ReceiptHeader();
	}
	List	receiptLineList = (List)request.getAttribute("receiptLineList");
	int	i_linecount = 0;
	if (receiptLineList != null) {
		i_linecount = receiptLineList.size();
	}
	if(s_ic_req_header.equals("")){
		s_ic_req_header = receiptHeader.getIcReqHeader().toString();
	}
	
	String s_shipToInv = receiptHeader.getShipToInv() ;
	BigDecimal totalBalanceRemaining = new BigDecimal(0);
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
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<input type=hidden name="RequisitionHeader_icReqHeader" value="<%=s_ic_req_header%>">
<input type=hidden name="RequisitionLine_icReqHeader" value="<%=s_ic_req_header%>">
<input type=hidden name="RequisitionLine_icReqLine" value="0">
<input type=hidden name="Account_icHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="Account_icLine" value="0">
<input type=hidden name="Account_accountType" value="RCH">
<input type=hidden name="DocComment_icHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="DocComment_icLine" value="0">
<input type=hidden name="DocAttachment_icHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="receiptMethod" value="<%=receiptMethod%>">
<input type=hidden name="formType" value="REC">
<input type=hidden name="allowBrowse" value="true">
<input type=hidden name="receiptStep" value="step_2">

<!-- inventory hidden fields -->
<input type=hidden name="InvItem_itemNumber" value="">
<input type=hidden name="InvBinLocation_itemNumber" value="">
<input type=hidden name="InvBinLocation_itemLocation" value="">
<input type=hidden name="InvLocation_itemNumber" value="">
<input type=hidden name="InvLocation_itemLocation" value="">
<input type=hidden name="qtyReceived" value="">
<input type=hidden name="duomQtyReceived" value="">
<input type=hidden name="InvBinLocation_tempIc" value="">
<input type=hidden name="InvBinLocation_cost" value="">
<input type=hidden name="PoLine_icPoLine" value="">
<input type=hidden name="PoLine_umCode" value="">
<input type=hidden name="commodity" value="">
<input type=hidden name="receiptLineFactor" value="">
<input type=hidden name="ReceiptLine_icRecLine" value="">
<input type=hidden name="InvProperty_icRecLine" value="">

<input type=hidden name="index" value="0">
<input type=hidden name="receiptRow" value="0">


<input type=hidden name="lineCount" value="<%=i_linecount%>">
<input type=hidden name="returnPage" value="/receipts/rec_items_to_step_2.jsp">
<input type=hidden name="returnMethod" value="ReceiptHeaderRetrieveById;ReceiptLineRetrieveByHeader">

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-mark_tag", "Mark/Tag")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=* align=right>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top align=center>
		
		<table id="itemTable" border="1" cellspacing="0" cellpadding="0" class="browseHdrDk" width=98%>
		<tr>
			<td width="100%" align="right" valign="top">
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdrDk">
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=8% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineNumber", "Line #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=30% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-itemNumber", "Item/Part #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityToMark")%> width=10% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityToMark", "Qty to Mark")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width=10% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-balance", "Balance")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityMarked")%> width=10% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityMarked", "Marked")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=10% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityRejected", "Rejected")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=10% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityAccepted", "Accepted")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineStatus")%> width="12%" height="18px" class="browseHdr" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineStatus", "Status")%></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align="center" valign="top">
				<table id=itemRows border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
		<%	if (i_linecount <= 0) { %>
				<tr>
					<td align="center"><br><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noitems", "There are currently no items in your shopping cart.", s_rec_type)%></b><br><br></td>
				</tr>
		<%	}
			for (int i = 0; i < i_linecount; i++)
			{
				String lineType = "";
				ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);
				if(!receiptLine.getStatus().equals(DocumentStatus.RCV_STEP_2)){
					lineType = "style=\"display: none;\"";
				}
				BigDecimal balanceQty = new BigDecimal(0) ;
				BigDecimal marked = new BigDecimal(0);
				String	endUser = "" ;
				String	description = "" ;
				String	itemNumber = "" ;
				String	itemLocation = "" ;
				String	status = "" ;
				BigDecimal	qtyToMarked = new BigDecimal(0) ;
				
				PoLine poLine = receiptLine.getPoLine();
				if(receiptLine.getInspectionRequired().equalsIgnoreCase("Y")){
					qtyToMarked = receiptLine.getQtyStep1Accepted();
				} else {
					qtyToMarked = receiptLine.getQtyStep0Accepted();
				}
				
				marked = receiptLine.getQtyStep2Accepted();
				if (qtyToMarked == null) qtyToMarked = new BigDecimal(0) ;
				if (marked == null) marked = new BigDecimal(0) ;
				balanceQty = qtyToMarked.subtract(marked);
				endUser = poLine.getRequisitionerCode();
				description = poLine.getDescription() ;
				itemNumber = poLine.getItemNumber();
				itemLocation = poLine.getItemLocation();
				status = DocumentStatus.toString(receiptLine.getStatus()) ;

				totalBalanceRemaining = totalBalanceRemaining.add(balanceQty);
		%>
				<tr <%=lineType %>>
					<% if(!s_rec_type.equalsIgnoreCase("A") && !receiptLine.getStatus().equals(DocumentStatus.CANCELLED)) { %>
						<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=8% height=18px class=browseRow nowrap>
							<a href="javascript: viewItem(<%=i%>); void(0);" title="Click here to View/Modify Item Details." onMouseOver="highlightRow(<%=i%>);" onMouseOut="removeHighlight(<%=i%>);"><%=receiptLine.getReceiptLine().setScale(1, BigDecimal.ROUND_HALF_UP)%></a>
							<input type=hidden name="icRecLine_<%=i%>" id="icRecLine_<%=i%>" value="<%=receiptLine.getIcRecLine()%>">
						</td>
					<% } else { %>
						<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=8% height=18px class=browseRow nowrap><%=receiptLine.getReceiptLine().setScale(1, BigDecimal.ROUND_HALF_UP)%>
							<input type=hidden name="icRecLine_<%=i%>" id="icRecLine_<%=i%>" value="<%=receiptLine.getIcRecLine()%>">
						</td>
					<% } %>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width="30%" height="18px" class="browseRow" nowrap><%=itemNumber%>
						<input type=hidden name="itemNumber_<%=i%>" id="itemNumber_<%=i%>" value="<%=itemNumber%>">
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityToMark")%> width="10%" height="18px" class="browseRow" align="center" nowrap><%=HiltonUtility.getFormattedQuantity(qtyToMarked, oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width="10%" height="18px" class="browseRow" align="center" nowrap><%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityMarked")%> width="10%" height="18px" class="browseRow" align="center" nowrap><%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep2Received(), oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width="10%" height="18px" class="browseRow" align="center" nowrap><%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep2Rejected(), oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width="10%" height="18px" class="browseRow" align="center" nowrap><%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep2Accepted(), oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineStatus")%> width="12%" height="18px" class="browseRow" align="center" nowrap><%=status%></td>
				</tr>
				<tr <%=lineType %>>
					<td width=5% height="18px" class="browseRow" nowrap>
						<input type=hidden name="InvItem_itemNumber_<%=i %>" value="<%=itemNumber %>">
						<input type=hidden name="InvBinLocation_itemNumber_<%=i %>" value="<%=itemNumber %>">
						<input type=hidden name="InvBinLocation_itemLocation_<%=i %>" value="<%=itemLocation %>">
						<input type=hidden name="InvLocation_itemNumber_<%=i %>" value="<%=itemNumber %>">
						<input type=hidden name="InvLocation_itemLocation_<%=i %>" value="<%=itemLocation %>">
						<input type=hidden name="qtyReceived_<%=i %>" value="<%=HiltonUtility.ckNull(receiptLine.getQtyStep2Accepted()).toString() %>">
						<input type=hidden name="duomQtyReceived_<%=i %>" value="<%=receiptLine.getDuomQtyReceived().toString() %>">
						<input type=hidden name="InvBinLocation_tempIc_<%=i %>" value="<%=receiptLine.getIcRecLine().toString() %>">
						<input type=hidden name="InvBinLocation_cost_<%=i %>" value="<%=poLine.getUnitPrice().toString() %>">
						<input type=hidden name="PoLine_icPoLine_<%=i %>" value="<%=receiptLine.getIcPoLine().toString() %>">
						<input type=hidden name="PoLine_umCode_<%=i %>" value="<%=poLine.getUmCode() %>">
						<input type=hidden name="commodity_<%=i %>" value="<%=poLine.getCommodity() %>">
						<input type=hidden name="receiptLineFactor_<%=i %>" value="">
						<input type=hidden name="ReceiptLine_icRecLine_<%=i %>" value="<%=receiptLine.getIcRecLine().toString() %>">
						<input type=hidden name="InvProperty_icRecLine_<%=i %>" value="<%=receiptLine.getIcRecLine().toString() %>">
						<input type=hidden name="RequisitionLine_icReqLine_<%=i %>" value="<%=receiptLine.getIcReqLine().toString() %>">
						
						<input type=hidden name="index" value="0">
						<input type=hidden name="receiptRow" value="0">
					</td>
					<td colspan=7 height="18px" class="browseRow"><%=description%></td>
				</tr>
		<%	} %>
				</table>
			</td>
		</tr>
		</table>
		<br>
		<br>
		<br>
		<br>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
	<td rowspan=2 align="right" valign="top"><%@ include file="/receipts/rec_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>
<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding="0" cellspacing="0" width=<%=formEntryWidth%>>
<tr>
	<td width=50% align="center"><a href="javascript: doSubmit('/receipts/rec_summary.jsp', 'ReceiptHeaderUpdate;ReceiptRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align="center"><a href="javascript: doSubmit('/receipts/rec_summary.jsp', 'ReceiptRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var recNumber = "<%=s_rec_number%>";
	var fiscalyear = "<%=s_rec_fiscal_year%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

<%	if (totalBalanceRemaining.compareTo(bd_zero) <= 0) {%>
		hideArea('receiveAllOption');
<%	}%>
	function viewItem(row) {

			var icRecLine = document.getElementById("icRecLine_" + row);
			var itemNumber = document.getElementById("itemNumber_" + row);

			frm.InvItem_itemNumber.value = itemNumber.value;
			frm.ReceiptLine_icRecLine.value = icRecLine.value;
			frm.InvBinLocation_itemNumber.value = document.getElementById("InvBinLocation_itemNumber_" + row).value;
			frm.InvBinLocation_itemLocation.value = document.getElementById("InvBinLocation_itemLocation_" + row).value;
			frm.InvLocation_itemNumber.value = document.getElementById("InvLocation_itemNumber_" + row).value;
			frm.InvLocation_itemLocation.value = document.getElementById("InvLocation_itemLocation_" + row).value;
			frm.qtyReceived.value = document.getElementById("qtyReceived_" + row).value;
			frm.duomQtyReceived.value = document.getElementById("duomQtyReceived_" + row).value;
			frm.InvBinLocation_tempIc.value = document.getElementById("InvBinLocation_tempIc_" + row).value;
			frm.InvBinLocation_cost.value = document.getElementById("InvBinLocation_cost_" + row).value;
			frm.PoLine_icPoLine.value = document.getElementById("PoLine_icPoLine_" + row).value;
			frm.PoLine_umCode.value = document.getElementById("PoLine_umCode_" + row).value;
			frm.commodity.value = document.getElementById("commodity_" + row).value;
			frm.receiptLineFactor.value = document.getElementById("receiptLineFactor_" + row).value;
			frm.InvProperty_icRecLine.value = document.getElementById("InvProperty_icRecLine_" + row).value;
			frm.RequisitionLine_icReqLine.value = document.getElementById("RequisitionLine_icReqLine_" + row).value;

			frm.index.value = document.getElementById("index_" + row);
			frm.receiptRow.value = document.getElementById("receiptRow_" + row);
			
			doSubmit('/receipts/rec_item_step_2.jsp','InvPropertyRetrieveByIcRecLine;ReceiptHeaderDataRetrieve;ReceiptLineRetrieve;PoHeaderRetrieveById');

	}

	function highlightRow(row) {
		row = row * 2;
		var myRow = document.all.itemRows.rows(row);
		setRowClassName(myRow, "selectedRow");
		myRow = document.all.itemRows.rows(row + 1);
		setRowClassName(myRow, "selectedRow");
	}

	function removeHighlight(row) {
		row = row * 2;
		var myRow = document.all.itemRows.rows(row);
		setRowClassName(myRow, "browseRow");
		myRow = document.all.itemRows.rows(row + 1);
		setRowClassName(myRow, "browseRow");
	}

	function receiveAll() {
		if (confirm("Receive all remaining items for this receipt?")) {
			doSubmit('/receipts/rec_items.jsp', 'ReceiptLineReceiveAll;ReceiptLineRetrieveByHeader');
		}
	}

	function recApproveStep2() {
		doSubmit('/receipts/rec_items_to_step_2.jsp', 'ReceiptUpdateNSteps;ReceiptLineRetrieveByHeader');
	}

	function addItem() {
		var line_count = frm.lineCount.value;
		line_count++;
		frm.lineCount.value = line_count;
		doSubmit('/receipts/rec_item.jsp','DoNothing');
	}

// End Hide script -->
</SCRIPT>