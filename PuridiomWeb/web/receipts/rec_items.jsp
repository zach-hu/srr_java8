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
	String	s_current_process = "SHOPCART";
	String	s_current_page = "/receipts/rec_items.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";

	BigDecimal	bd_zero = new BigDecimal(0);

	boolean allowEdit = false;
	if ((receiptMethod.equals("ReceiveFromNothing") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0))
		allowEdit = true;

	ReceiptHeader	receiptHeader = (ReceiptHeader)request.getAttribute("receiptHeader");
	if (receiptHeader == null) {
		receiptHeader = new ReceiptHeader();
	}
	List	receiptLineList = (List)request.getAttribute("receiptLineList");
	int	i_linecount = 0;
	if (receiptLineList != null) {
		i_linecount = receiptLineList.size();
	}
	String s_shipToInv = receiptHeader.getShipToInv() ;
	BigDecimal totalBalanceRemaining = new BigDecimal(0);
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
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_ic_req_header%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=s_ic_req_header%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RCH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="formType" value="REC"/>
<tsa:hidden name="allowBrowse" value="true"/>

<tsa:hidden name="ReceiptLine_icRecLine" value=""/>
<tsa:hidden name="InvItem_itemNumber" value=""/>
<tsa:hidden name="InvProperty_icRecLine" value=""/>

<tsa:hidden name="checkViewItems" value=""/>

<tsa:hidden name="lineCount" value="<%=i_linecount%>"/>
<tsa:hidden name="returnPage" value="/receipts/rec_items.jsp"/>
<tsa:hidden name="returnMethod" value="ReceiptLineRetrieveByHeader;ReceiptHeaderRetrieveById"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "shopping_cart", "Shopping Cart")%></div>
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
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Order #:</b>&nbsp;<%=headerEncoder.encodeForHTML(s_po_number)%>
		<%	} else if (!HiltonUtility.isEmpty(s_req_number)) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Transfer Request #:</b>&nbsp;<%=headerEncoder.encodeForHTML(s_req_number)%>
		<%  } %>
			</td>
			<td align=right><b>Receipt #:</b></td>
		<%	if (HiltonUtility.isEmpty(s_rec_number)) { %>
			<td width=100px>N/A</td>
		<%	} else { %>
			<td width=100px><%=headerEncoder.encodeForHTML(s_rec_number)%></td>
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
		<% if (s_receiveAll.equalsIgnoreCase("Y")) { %>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr id="receiveAllOption"><td align="right">
		<div id="pxmediumbutton"><a href="javascript: receiveAll(); void(0);">Receive All</a></div>
		</td>
		</tr>
		</table>
		<% } %>
		<table id="itemTable" border="1" cellspacing="0" cellpadding="0" class="browseHdrDk" width=100%>
		<tr>
			<td width="100%" align="right" valign="top">
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdrDk">
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=40px height=18px class=browseHdrDk nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineNumber", "Line #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=140px height="18px" class="browseHdrDk" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-itemNumber", "Item/Part #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width="10%" height="18px" class="browseHdrDk" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityOrdered", "Qty Ordered")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width="8%" height="18px" class="browseHdrDk" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-balance", "Balance")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width="8%" height="18px" class="browseHdrDk" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityReceived", "Received")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityInspected")%> width=8% height="18px" class=browseHdrDk nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityInspected", "Inspected")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityMarked")%> width=8% height="18px" class=browseHdrDk nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityMarked", "Mark/Tag")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityDelivered")%> width=8% height="18px" class=browseHdrDk nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityDelivered", "Delivered")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width="8%" height="18px" class="browseHdrDk" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityRejected", "Rejected")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width="8%" height="18px" class="browseHdrDk" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityAccepted", "Accepted")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineStatus")%> width="10%" height="18px" class="browseHdrDk" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineStatus", "Status")%></td>
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
			String checkViewItems = "";
			String linesHasInvLocationNoValid = "";
			for (int i = 0; i < i_linecount; i++)
			{
				ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);
				BigDecimal balanceQty = new BigDecimal(0) ;
				BigDecimal recvd = new BigDecimal(0);
				String	endUser = "" ;
				String	description = "" ;
				String	itemNumber = "" ;
				String	status = "" ;
				BigDecimal	quantity = new BigDecimal(0) ;
				boolean checkInv = false;
				String itemSource = "";
				boolean hasInvLocation = false;
				if (receiptMethod.equalsIgnoreCase("ReceiveByTransfer")) {
					RequisitionLine reqLine = receiptLine.getRequisitionLine() ;
					quantity = reqLine.getQuantity() ;
					recvd = receiptLine.getQtyReceived() ;
					if (quantity == null) quantity = new BigDecimal(0) ;
					if (recvd == null) recvd = new BigDecimal(0) ;
					balanceQty = quantity.subtract(recvd);
					endUser = reqLine.getRequisitionerCode();
					description = reqLine.getDescription() ;
					itemNumber = reqLine.getItemNumber();
					status = DocumentStatus.toString(reqLine.getStatus()) ;
					quantity = reqLine.getQuantity() ;
					checkInv = true;
					itemSource = reqLine.getItemSource();
					if (receiptLine.getInvLocation() != null &&
						receiptLine.getInvLocation().getComp_id().getItemLocation().equalsIgnoreCase(receiptHeader.getShipToCode())) {
						hasInvLocation = true;
					}
					if (i == 0) {
						checkViewItems = Boolean.toString(checkInv) + "," + itemSource + "," + Boolean.toString(hasInvLocation);
					} else {
						checkViewItems = checkViewItems + ";" + Boolean.toString(checkInv) + "," + itemSource + "," + Boolean.toString(hasInvLocation);
					}
				} else {
					PoLine poLine = receiptLine.getPoLine();
					quantity = poLine.getQuantity() ;
					recvd = poLine.getQtyReceived().add(receiptLine.getQtyAccepted());
					if (quantity == null) quantity = new BigDecimal(0) ;
					if (recvd == null) recvd = new BigDecimal(0) ;
					balanceQty = quantity.subtract(recvd);
					endUser = poLine.getRequisitionerCode();
					description = poLine.getDescription() ;
					itemNumber = poLine.getItemNumber();
					status = DocumentStatus.toString(poLine.getStatus()) ;
					if (receiptMethod.equalsIgnoreCase("ReceiveByOrder")) {
						checkInv = true;
						itemSource = poLine.getItemSource();
						if (receiptLine.getInvLocation() != null &&
							receiptLine.getInvLocation().getComp_id().getItemLocation().equalsIgnoreCase(receiptHeader.getShipToCode())) {
							hasInvLocation = true;
						}
						if (i == 0) {
							checkViewItems = Boolean.toString(checkInv) + "," + itemSource + "," + Boolean.toString(hasInvLocation);
						} else {
							checkViewItems = checkViewItems + ";" + Boolean.toString(checkInv) + "," + itemSource + "," + Boolean.toString(hasInvLocation);
						}
					}
				}
				if (checkInv && itemSource.equalsIgnoreCase("INV") && !hasInvLocation) {
					if (HiltonUtility.isEmpty(linesHasInvLocationNoValid)) {
						linesHasInvLocationNoValid = String.valueOf(i + 1);
					} else {
						linesHasInvLocationNoValid = linesHasInvLocationNoValid + ", " + String.valueOf(i + 1);
					}
				}
				totalBalanceRemaining = totalBalanceRemaining.add(balanceQty);
		%>
				<tr>
					<% if(!s_rec_type.equalsIgnoreCase("A")) { %>
						<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=40px height=18px class=browseRow nowrap>
							<a href="javascript: checkViewItem(<%=i%>, <%=checkInv%>, '<%=itemSource%>', <%=hasInvLocation%>); void(0);" title="Click here to View/Modify Item Details." onMouseOver="highlightRow(<%=i%>);" onMouseOut="removeHighlight(<%=i%>);"><%=receiptLine.getReceiptLine().setScale(1, BigDecimal.ROUND_HALF_UP)%></a>
							<input type="hidden" name="icRecLine_<%=i%>" id="icRecLine_<%=i%>" value="<%=receiptLine.getIcRecLine()%>">
						</td>
					<% } else { %>
						<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=40px height=18px class=browseRow nowrap><%=receiptLine.getReceiptLine().setScale(1, BigDecimal.ROUND_HALF_UP)%>
							<input type="hidden" name="icRecLine_<%=i%>" id="icRecLine_<%=i%>" value="<%=receiptLine.getIcRecLine()%>">
						</td>
					<% } %>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width="140px" height="18px" class="browseRow" nowrap><%=itemNumber%>
						<input type="hidden" name="itemNumber_<%=i%>" id="itemNumber_<%=i%>" value="<%=itemNumber%>">
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width="10%" height="18px" class="browseRow" align="center" nowrap><%=HiltonUtility.getFormattedQuantity(quantity, oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width="8%" height="18px" class="browseRow" align="center" nowrap><%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width="8%" height="18px" class="browseRow" align="center" nowrap><%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyReceived(), oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityInspected")%> width=8% height="18px" class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep1Accepted(), oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityMarked")%> width=8% height="18px" class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep2Accepted(), oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityDelivered")%> width=8% height="18px" class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep3Accepted(), oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width="8%" height="18px" class="browseRow" align="center" nowrap><%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyRejected(), oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width="8%" height="18px" class="browseRow" align="center" nowrap><%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyAccepted(), oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineStatus")%> width="10%" height="18px" class="browseRow" align="center" nowrap><%=status%></td>
				</tr>
				<tr>
					<td width=40px height="18px" class="browseRow" nowrap>&nbsp;</td>
					<td colspan=10 height="18px" class="browseRow"><%=description%></td>
				</tr>
		<%	} %>
				</table>
			</td>
		</tr>
		</table>
		<br>
		<br>
	<%	if (allowEdit) { %>
		<tsa:hidden name="createAction" value="SAVE"/>
		<table  border=0 cellpadding=0 cellspacing=0 align=right>
			<tr>
				<td valign=middle><a href="javascript: addItem();"><img src="<%=contextPath%>/images/cmd_add_item.gif" border=0 alt="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addnonstditem", "Add Non-Standard Item")%>"><font style="text-decoration: none;"></font></a>&nbsp;</td>
				<td valign=middle><a href="javascript: addItem();" title="All services and any goods that are not listed in a catalog."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addnonstditem", "Add Non-Standard Item")%></a></td>
				<td width=60px></td>
			</tr>
		</table>
	<%	}%>
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
	var recNumber = "<%=headerEncoder.encodeForJavaScript(s_rec_number)%>";
	var fiscalyear = "<%=headerEncoder.encodeForJavaScript(s_rec_fiscal_year)%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	frm.checkViewItems.value = "<%=checkViewItems%>";

<%	if (totalBalanceRemaining.compareTo(bd_zero) <= 0) {%>
		hideArea('receiveAllOption');
<%	}%>

	function checkViewItem(row, checkInv, itemSource, hasInvLocation) {
		if (checkInv) {
			if ("<%=s_shipToInv%>" == "Y") {
				if (itemSource == "INV") {
						viewItem(row);
				} else {
					if (confirm("This is not an inventory item.\nTo receive as inventory, this item must be setup as an inventory item assigned to this location.\nClick \"OK\" to continue and receive this as non-inventory.")) {
						viewItem(row);
					}
				}
			} else {
				viewItem(row);
			}
		} else {
			viewItem(row);
		}
	}

	function viewItem(row) {
		var icRecLine = document.getElementById("icRecLine_" + row);
		var itemNumber = document.getElementById("itemNumber_" + row);
		frm.ReceiptLine_icRecLine.value = icRecLine.value;
		frm.InvItem_itemNumber.value = itemNumber.value;
		frm.InvProperty_icRecLine.value = icRecLine.value;

		doSubmit('/receipts/rec_item.jsp','InvItemRetrieveById;ReceiptHeaderRetrieveById;ReceiptLineRetrieve;InvPropertyRetrieveByIcRecLine');
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
			<%	if (!HiltonUtility.isEmpty(linesHasInvLocationNoValid)) { %>
			alert("One or more of these lines cannot be received because this inventroy location is not established for the item(s): <%=linesHasInvLocationNoValid%>\nPlease contact Supply/Materials Coordinator.");
			<%	} %>
			doSubmit('/receipts/rec_items.jsp', 'ReceiptLineReceiveAll;ReceiptLineRetrieveByHeader');
		}
	}

	function addItem() {
		var line_count = frm.lineCount.value;
		line_count++;
		frm.lineCount.value = line_count;
		doSubmit('/receipts/rec_item.jsp','DoNothing');
	}

// End Hide script -->
</SCRIPT>