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
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>

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

	Commodity commodity = CommodityManager.getInstance().getCommodity(oid, s_commodity) ;
	boolean isAsset = false ;
	boolean isDualUm = false ;

	if (commodity != null) {
		String atemp = commodity.getAsset() ;
		String umtemp = commodity.getDuomRequired() ;
		if (atemp == null) atemp = "N" ;
		if (umtemp == null) umtemp = "N" ;
		isAsset = atemp.equalsIgnoreCase("Y") ;
		if (s_duomRequired.equalsIgnoreCase("Y")) isDualUm = umtemp.equalsIgnoreCase("Y") ;
	}

	if (isDualUm) {
		duomUmCode = receiptLine.getDuomUmCode() ;
		if (HiltonUtility.isEmpty(duomUmCode)) {
			InvItem invItem = (InvItem) request.getAttribute("invItem") ;
			if (! (invItem == null)) {
				duomUmCode = invItem.getDuomUmCode() ;
			}
		}
	}

	String	s_return_page = HiltonUtility.ckNull((String)request.getAttribute("returnPage"));
	if (HiltonUtility.isEmpty(s_return_page)) {
		s_return_page = "/receipts/rec_return_items.jsp";
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

<table border=0 cellpadding=0 cellspacing=0 width=670px>
<tr>
	<td align=right width=670px>
<%		int linecount = Integer.valueOf(s_line_count).intValue();
		if (linecount > 1) { %>
		View Item:
<%			for (int i=1; i<=linecount; i++) {
				if (i == bd_line_number.intValue()) { %>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=660px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRowHdr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=6% class=browseHdr nowrap align=center valign=bottom><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineNumber", "Line #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=35% class=browseHdr nowrap valign=bottom><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-itemNumber", "Item/Part #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-asset")%> width=8% class=browseHdr nowrap valign=bottom><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-asset", "Asset")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=11% class=browseHdr nowrap align=right valign=bottom><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityOrdered", "Ordered")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=11% class=browseHdr nowrap align=right valign=bottom><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityReceived", "Received")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReturned")%> width=15% class=browseHdr nowrap align=right valign=bottom><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityReturned", "Returned")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-previouslyRejected")%> width=10% class=browseHdr align=center valign=bottom><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-previouslyRejected", "Previously Rejected")%></td>
					<td width=4% class=browseHdr nowrap>&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
<%
		int ipl = 0;
			if (poLine != null && poLine.getStatus().compareTo(DocumentStatus.RCV_INVOICED) < 0 && (poLine.getReceiptRequired().equals("E") || poLine.getReceiptRequired().equals("R")) ) {
				String	endUser = poLine.getRequisitionerCode();

				if (HiltonUtility.isEmpty(endUser)) {
					endUser = poHeader.getRequisitionerCode();
				}
				if (HiltonUtility.isEmpty(endUser)) {
					endUser = poHeader.getBuyerCode();
				}
				List receiptLineList = poLine.getReceiptLineList();
				String	uom = poLine.getUmCode();
				if (HiltonUtility.isEmpty(uom)) {
					uom = "EA";
				}
%>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=6% class=browseRow nowrap align=right><%=poLine.getLineNumber().setScale(1, BigDecimal.ROUND_HALF_UP)%>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=35% class=browseRow nowrap><%=poLine.getItemNumber()%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-asset")%> width=8% class=browseRow nowrap><input type="checkbox" name="c_asset" <% if (poLine.getAsset().equals("Y") ) {%> checked="checked" <% } %> disabled="disabled"/><tsa:hidden name="PoLine_asset" value="<%=poLine.getAsset()%>"/></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=11% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid)%> (<%=uom%>)</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=11% class=browseRow nowrap align=right id="updatedQty"><%=HiltonUtility.getFormattedQuantity(poLine.getQtyReceived(), oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReturned")%> width=15% class=browseRow nowrap align=right>
					<% if (poLine.getQtyReceived().compareTo(new BigDecimal(0)) <= 0) {%>
							<tsa:hidden name="adjFactorDisplay" value=""/>
							<tsa:hidden name="adjustmentQty" value=""/>
					<% } else {%>
							<input type=text name="adjFactorDisplay" disabled value="-" size=1>
							<input type=text name="adjustmentQty" value="<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyReturned(), oid)%>" style="text-align:right" size=10 onchange="checkBalance(); void(0);" tabIndex=<%=ipl + 10%>>
					<% }%>
							<tsa:hidden name="adjFactor" value="-1.0"/>
							<tsa:hidden name="ReceiptLine_qtyReceived" value=""/>
							<tsa:hidden name="ReceiptLine_qtyRejected" value=""/>
							<tsa:hidden name="ReceiptLine_qtyReturned" value=""/>
							<tsa:hidden name="ReceiptLine_qtyAccepted" value=""/>
							<tsa:hidden name="qtyReceived" value="<%=HiltonUtility.getFormattedQuantity(poLine.getQtyReceived(), oid)%>"/>
							<tsa:hidden name="ReceiptLine_icPoLine" value="<%=poLine.getIcPoLine()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-previouslyRejected")%> width=10% class=browseRow nowrap align=center><input type=checkbox name="c_prevRejected" value="Y" onclick="checkBalance(<%=ipl%>);"></td>
					<td width=4% class=browseRow align=center nowrap>
							<div id="receiptNotes" <% if (poLine.getQtyReceived().compareTo(new BigDecimal(0)) <= 0) {%>style="visibility: hidden;"<%}%>><a href="javascript: viewReceiptNotes(<%=ipl%>); void(0);"><img src="<%=contextPath%>/images/cmd_edit.gif" border=0 alt="Item Return Notes" tabIndex=-1></a></div>
					</td>
				</tr>
				<tr>
<%				if (isDualUm && poLine.getQtyReceived().compareTo(new BigDecimal(0)) > 0) { %>
					<td colspan=1>&nbsp</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-duomUmCode")%> align=right colspan=1 nowrap>
						<A HREF="javascript: browseLookup('ReceiptLine_duomUmCode', 'unitofmeasure'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-uom", "UOM")%> for this item or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-uom", "UOM")%> in box on the right." ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-duomUmCode", "Secondary UOM", true)%></a>:&nbsp;
					</td>

					<td <%=HtmlWriter.isVisible(oid, "rec-duomUmCode")%> align=right class=browseRow valign=top>
						<input type=text name="ReceiptLine_duomUmCode" tabindex=6 size="3" maxlength=15 value="<%=duomUmCode%>" onchange="upperCase(this);">
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-duomQtyReceived")%> colspan=2 align=right class=browseRow valign=top>
						<input type=text name="ReceiptLine_duomQtyReceived" tabindex=5 size="10" maxlength=25 value="<%=receiptLine.getDuomQtyReceived()%>" style="text-align:right" >
					</td>
<% } else { %>
					<td>
						<tsa:hidden name="ReceiptLine_duomQtyReceived" value="0"/>
						<tsa:hidden name="ReceiptLine_duomUmCode" value=""/>
					</td>
<% } %>
				</tr>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td colspan=2 class=browseRow valign=top><%=poLine.getDescription()%></td>
					<td colspan=3 class=browseRow nowrap align=right>
					<% if (poLine.getQtyReceived().compareTo(new BigDecimal(0)) <= 0) {%>
							<tsa:hidden name="ReceiptLine_dispositionCode"/>
					<% } else {
						if ( !oid.equalsIgnoreCase("DTN07P")) {%>
						<table border=0 cellspacing=0 cellpadding=1 class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rec-dispositionCode")%> id="itemDispostionCode">
							<td height=16px  class=label align=right><a href="javascript: browseStd('ReceiptLine_dispositionCode', 'DISP', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-dispositionCode", "Disposition Code", true)%>:</a></td>
							<td height=16px><input type=text name="ReceiptLine_dispositionCode" size=20 onchange="upperCase(this);"></td>
						</tr>
						</table>
						<% }%>
					<% }%>
					</td>
					<td class=browseRow nowrap>&nbsp;</td>
				</tr>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td colspan=5 class=browseRow>
						<div id="itemDetails" style="visibility: hidden; display: none;">
						<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> width=16% align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN01", "Line UDF 1", false)%>:</b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> width=18%><%=poLine.getUdf1Code()%></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> width=15% align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN02", "Line UDF 2", false)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> width=18%><%=poLine.getUdf2Code()%></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN03")%> width=15% align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN03", "Line UDF 3", false)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN03")%> width=18%><%=poLine.getUdf3Code()%></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN04")%> align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN04", "Line UDF 4", false)%>:</b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN04")%>><%=poLine.getUdf4Code()%></td>
						<%if (!oid.equalsIgnoreCase("vse06p")) { %>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN05")%> align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN05", "Line UDF 5", false)%>:</b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN05")%>><%=poLine.getUdf5Code()%></td>
						<% } %>
						</tr>
						</table>
						</div>
						<div id="itemReceiptNotes" style="visibility: hidden; display: none;">
						<table border=0 cellspacing=0 cellpadding=1 class=browseRow width=100%>
						<tr>
							<td align=right valign=top>
								<table border=0 cellspacing=0 cellpadding=1 class=browseRow>
								<tr>
									<td align=right valign=top><a href="javascript: hideReceiptNotes(); void(0);"><img src="<%=contextPath%>/images/bar_close.gif" border=0 alt='Hide Item Receipt Notes'></a></td>
									<td class=label align=right valign=top><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcl-rec-receiptNotes", "Notes", false)%>:</td>
									<td <%=HtmlWriter.isVisible(oid, "rcl-rec-receiptNotes")%> valign=top><textarea name="ReceiptLine_receiptNotes" cols=45 rows=4 onKeyDown="textCounter(this, 225);" onKeyUp="textCounter(this,225);"></textarea></td>
								</tr>
								</table>
							</td>
						</tr>
						</table>
						</div>
<%			if (receiptLineList != null && receiptLineList.size() > 0) {%>
						<div id="receiptHistory">
						<table border=0 cellspacing=0 cellpadding=0 width=100%>
						<tr><td align=right><a href="javascript: viewReceiptHistory(); void(0);">View Receipt History</a></td></tr>
						</table>
						</div>
<%			} else {%>
						<div id="receiptHistory">
						<table border=0 cellspacing=0 cellpadding=0 width=100%>
						<tr><td align=right>No Receipt History</td></tr>
						</table>
						</div>
<%			}%>
<%@ include file="/receipts/rec_item_history.jsp"%>
					</td>
					<td class=browseRow nowrap>&nbsp;</td>
				</tr>
<%
				ipl++;
%>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
	</td>
</tr>
</table>
<%	} %>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (allowEdit) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=s_return_page%>', 'ReceiptLineUpdate;<%=s_return_method%>'); void(0);">Save</a></div></td>
<%	} %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=s_return_page%>', '<%=s_return_method%>'); void(0);">Return</a></div></td>
</tr>
</table>

<table>
<tr>
	<td>
		<tsa:hidden name="PoLine_itemSource" value="<%=poLine.getItemSource()%>"/>
		<tsa:hidden name="PoLine_itemNumber" value="<%=poLine.getItemNumber()%>"/>
		<tsa:hidden name="PoLine_itemLocation" value="<%=poLine.getItemLocation()%>"/>
		<tsa:hidden name="PoLine_unitPrice" value="<%=poLine.getUnitPrice()%>"/>
		<tsa:hidden name="PoLine_umCode" value="<%=poLine.getUmCode()%>"/>
		<tsa:hidden name="PoLine_receiptRequired" value="<%=poLine.getReceiptRequired()%>"/>
		<tsa:hidden name="PoLine_asset" value="<%=poLine.getAsset()%>"/>
		<tsa:hidden name="PoLine_commodity" value="<%=poLine.getCommodity()%>"/>
		<tsa:hidden name="PoLine_quantity" value="<%=poLine.getQuantity()%>"/>

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

//	checkRejected();
	if (isAsset) {
		frm.c_asset.checked = true ;
		setAsset() ;
	}

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
			displayArea("invPropertyArea");
		} else {
			hideArea("invPropertyArea");
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
		var balance = nformat(frm.PoLine_quantity.value, qtyDecimals);
		frm.ReceiptLine_qtyReceived.value = balance;
		frm.ReceiptLine_qtyRejected.value = nformat(0, qtyDecimals);
		checkBalance();
	}

	function checkBalance() {
		var adjustmentQty = nformat(eval(nfilter(frm.adjustmentQty)), qtyDecimals);
		var adjustmentFactor = frm.adjFactor.value;
		var adjustedQty = adjustmentQty;
		var previouslyRejected = frm.c_prevRejected.checked;

		frm.adjustmentQty.value = adjustmentQty;

		adjustedQty = eval(adjustmentQty * adjustmentFactor);
		var receiptQty = eval(nfilter(frm.qtyReceived));
		var newBalance = nformat(eval(receiptQty + adjustedQty), qtyDecimals);
		if (newBalance < 0) {
			alert("You cannot return more than the quantity received.");
			frm.adjustmentQty.value =eval(nformat(receiptQty, qtyDecimals));
			frm.adjustmentQty.select();
//			frm.adjustmentQty.focus();
			newBalance = "0.00";
		} else {
			if (previouslyRejected) {
				var qtyPrevRejected = nformat(eval(nfilter(frm.qtyPrevRejected)), qtyDecimals);
				var newRejectedQty = eval(qtyPrevRejected) + eval(adjustedQty);
				if (newRejectedQty < 0) {
					if (qtyPrevRejected >0) {
						alert("Only " + qtyPrevRejected + " have previously been rejected and not yet returned.");
					} else {
						alert("This item does not  have a previously rejected quantity that has not yet been returned.");
					}
					 frm.c_prevRejected.checked = false;
					previouslyRejeted = false;
				}
			}
			if (!previouslyRejected) {
				adjustedQty = eval("0.00");
			}
			frm.ReceiptLine_qtyRejected.value = adjustedQty;
			frm.ReceiptLine_qtyReceived.value =eval("0.00");
			frm.ReceiptLine_qtyReturned.value = adjustmentQty;
			frm.ReceiptLine_qtyAccepted.value = eval(-(adjustmentQty) - (adjustedQty));
		}
		var d = document.all("updatedQty");
		if (d.length > 1) {
			d(row).innerHTML = nformat(newBalance, qtyDecimals);
		} else {
			d.innerHTML = nformat(newBalance, qtyDecimals);
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

	function viewReceiptHistory() {
		var d = document.all("itemReceiptHistory");
		if (d.length > 1) {
			d(row).style.visibility = "visible";
			d(row).style.display = "block";
		} else {
			d.style.visibility = "visible";
			d.style.display = "block";
		}
		var t = document.all("receiptHistory");
		if (t.length > 1) {
			t(row).style.visibility = "hidden";
			t(row).style.display = "none";
		} else {
			t.style.visibility = "hidden";
			t.style.display = "none";
		}
	}

	function hideReceiptHistory() {
		var d = document.all("itemReceiptHistory");
		if (d.length > 1) {
			d(row).style.visibility = "hidden";
			d(row).style.display = "none";
		} else {
			d.style.visibility = "hidden";
			d.style.display = "none";
		}
		var t = document.all("receiptHistory");
		if (t.length > 1) {
			t(row).style.visibility = "visible";
			t(row).style.display = "block";
		} else {
			t.style.visibility = "visible";
			t.style.display = "block";
		}
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

		if (extendedInventory && shipToInv == "Y" && (receiptOption == "R" || receiptOption == "E")) {
			if (qtyReceived == 0) {
				popupParameters = "InvBinLocation_itemNumber=" + itemNumber + ";InvBinLocation_itemLocation=" + itemLocation + ";InvBinLocation_tempIc=" + frm.InvBinLocation_tempIc.value;

				setLookupParameters('/system/hide_iframe.jsp', 'InvBinLocationDeleteItemByTempIc');
				displayArea('getInfoFrame');
				document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
			} else {
				popupParameters = "InvItem_itemNumber=" + itemNumber + ";InvBinLocation_itemNumber=" + itemNumber + ";InvBinLocation_itemLocation=" + itemLocation + ";InvLocation_itemNumber=" + itemNumber + ";InvLocation_itemLocation=" + itemLocation + ";qtyReceived=" + qtyReceived + ";duomQtyReceived=" + duomQtyReceived + ";index=0;InvBinLocation_tempIc=" + frm.InvBinLocation_tempIc.value + ";InvBinLocation_cost=" + itemCost + ";PoLine_icPoLine=" + icPoLine + ";PoLine_umCode=" + umCode + ";commodity=" + commodity + ";receiptRow=0;receiptLineFactor=" + receiptLineFactor;
				doSubmitToPopup('receipts/rec_inv_bin_locations.jsp', 'InvItemRetrieveById;InvBinLocationRetrieveByItem', 'WIDTH=700px', 'HEIGHT=500px');
			}
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
				popupParameters = "InvItem_itemNumber=" + itemNumber + ";InvBinLocation_itemNumber=" + itemNumber + ";InvBinLocation_itemLocation=" + itemLocation + ";InvLocation_itemNumber=" + itemNumber + ";InvLocation_itemLocation=" + itemLocation + ";qtyReceived=" + qtyReceived + ";index=0;InvBinLocation_tempIc=" + frm.InvBinLocation_tempIc.value + ";InvBinLocation_cost=" + itemCost + ";PoLine_icPoLine=" + icPoLine + ";PoLine_umCode=" + umCode + ";duomUmCode=" + duomUmCode + ";duomQtyReceived=" + duomQtyReceived + ";commodity=" + commodity + ";receiptRow=0;receiptLineFactor=" + receiptLineFactor;
				doSubmitToPopup('receipts/rec_inv_bin_locations.jsp', 'InvItemRetrieveById;InvBinLocationRetrieveByItem', 'WIDTH=700px', 'HEIGHT=500px');
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

		frm.ReceiptLine_qtyRejected.value = qtyRejected;
		if (qtyRejected > qtyReceived) {
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

		doSubmitToPopup('receipts/rec_return_popup.jsp', 'ReceiptCreateRetrieve', 'width=692px', 'height=325px');
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

// End Hide script -->
</SCRIPT>