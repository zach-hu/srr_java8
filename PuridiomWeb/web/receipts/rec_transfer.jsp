<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.InspectionLevel" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<%@ page import="com.tsagate.foundation.utility.UniqueKeyGenerator"%>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<%
	String	quantityDecimals = PropertiesManager.getInstance(oid).getProperty("MISC", "QtyDecimals", "2");
	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	if (receiptHeader == null) {
		receiptHeader = new ReceiptHeader();
	}
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	if (requisitionHeader == null) {
		requisitionHeader = new RequisitionHeader();
	}
	List	requisitionLineList = requisitionHeader.getRequisitionLineList();
	if (requisitionLineList == null) {
		requisitionLineList = new ArrayList();
	}
	Address vendor = (Address) requisitionHeader.getVendorAddress();
	if (vendor == null) {
		vendor = new Address();
	}
	Address shipTo = (Address) requisitionHeader.getShipToAddress();
	if (shipTo == null) {
		shipTo = new Address();
	}
	String	receiptNumber = receiptHeader.getReceiptNumber();
	if (HiltonUtility.isEmpty(receiptNumber)) {
		receiptNumber = "N/A";
	}
	String	receiptMethod = (String) request.getAttribute("receiptMethod");
	if (HiltonUtility.isEmpty(receiptMethod)) {
		receiptMethod = "ReceiveByTransfer";
	}
	String	createAction = "FINALIZE";
	if (receiptMethod.equals("ReceiveByTransfer")) {
		createAction = "SAVE";
	}
	BigDecimal	bd_zero = new BigDecimal(0);
%>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=receiptHeader.getIcRecHeader()%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=receiptHeader.getReceiptNumber()%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=receiptHeader.getReceiptStatus()%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=receiptHeader.getFiscalYear()%>"/>
<tsa:hidden name="ReceiptHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
<tsa:hidden name="ReceiptHeader_icReqHeader" value="<%=receiptHeader.getIcReqHeader()%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="O"/>
<tsa:hidden name="ReceiptHeader_shipToCode" value="<%=receiptHeader.getShipToCode()%>"/>
<tsa:hidden name="ReceiptHeader_shipToInv" value="<%=receiptHeader.getShipToInv()%>"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="createAction" value="<%=createAction%>"/>
<tsa:hidden name="receiptAction" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="InvBinLocation_tempIc" value="<%=UniqueKeyGenerator.getInstance().getUniqueKey().toString()%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Receipt Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitionNumber", "Requisition #")%>:</b></td>
			<td width=100px><%=headerEncoder.encodeForHTML(requisitionHeader.getRequisitionNumber())%></td>
		</tr>
		<tr>
			<td align=right><b>Receipt #:</b></td>
			<td width=100px><%=receiptNumber%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
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

<br>
<%@ include file="/receipts/rec_info.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=100% align=center valign=top>
				<table id=receiptInfoTable border=1 cellspacing=0 cellpadding=0 width=605px class=browseHdr>
				<tr>
					<td colspan=2 class=browseHdr height=18px nowrap>&nbsp;Receipt Information</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr>
							<td class=browseRow width=50%>
								<table border=0 cellspacing=0 cellpadding=1>
								<tr <%=HtmlWriter.isVisible(oid, "rec-supplier")%>>
									<td nowrap align=right width=135px><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-supplier", "Supplier")%>:&nbsp;</b></td>
									<td nowrap><%=receiptHeader.getVendorId()%></td>
								</tr>
		<%		if (!HiltonUtility.isEmpty(vendor.getAddressLine1())) { %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine1")%>><td>&nbsp;</td><td nowrap><%=vendor.getAddressLine1()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(vendor.getAddressLine2())) { %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine2")%>><td>&nbsp;</td><td  height=12px nowrap><%=vendor.getAddressLine2()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(vendor.getAddressLine3())) { %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine3")%>><td>&nbsp;</td><td nowrap><%=vendor.getAddressLine3()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(vendor.getAddressLine4())) { %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine4")%>><td>&nbsp;</td><td nowrap><%=vendor.getAddressLine4()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(vendor.getCity() + vendor.getState() + vendor.getPostalCode())) { %>
								<tr><td>&nbsp;</td><td nowrap><%=vendor.getCity()%>&nbsp;<%=vendor.getState()%>&nbsp;<%=vendor.getPostalCode()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(vendor.getCountry())) {%>
								<tr <%=HtmlWriter.isVisible(oid, "rec-sup-country")%>><td>&nbsp;</td><td nowrap><%=vendor.getCountry()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(requisitionHeader.getVendContactCode())) { %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-sup-attention")%>><td>&nbsp;</td><td nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po--attention", "Attn")%>:&nbsp<%=requisitionHeader.getVendContactCode()%></td></tr>
		<%		}%>
								<tr <%=HtmlWriter.isVisible(oid, "rec-shipToCoder")%>>
									<td nowrap align=right width=135px><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-shipToCode", "Ship To")%>:&nbsp;</b></td>
									<td nowrap><%=receiptHeader.getShipToCode()%></td>
								</tr>
		<%		if (!HiltonUtility.isEmpty(shipTo.getAddressLine1())) { %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-shp-addressLine1")%>><td>&nbsp;</td><td nowrap><%=shipTo.getAddressLine1()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(shipTo.getAddressLine2())) { %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-shp-addressLine2")%>><td>&nbsp;</td><td  height=12px nowrap><%=shipTo.getAddressLine2()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(shipTo.getAddressLine3())) { %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-shp-addressLine3")%>><td>&nbsp;</td><td nowrap><%=shipTo.getAddressLine3()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(shipTo.getAddressLine4())) { %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-shp-addressLine4")%>><td>&nbsp;</td><td nowrap><%=shipTo.getAddressLine4()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(shipTo.getCity() + shipTo.getState() + shipTo.getPostalCode())) { %>
								<tr><td>&nbsp;</td><td nowrap><%=shipTo.getCity()%>&nbsp;<%=shipTo.getState()%>&nbsp;<%=shipTo.getPostalCode()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(shipTo.getCountry())) {%>
								<tr <%=HtmlWriter.isVisible(oid, "rec-shp-country")%>><td>&nbsp;</td><td nowrap><%=shipTo.getCountry()%></td></tr>
		<%		}%>

								<tr <%=HtmlWriter.isVisible(oid, "rec-po-PO01",requisitionHeader.getRequisitionType())%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-PO01", "UDF1", requisitionHeader.getRequisitionType())%>:&nbsp;</b></td>
									<td nowrap><%=requisitionHeader.getUdf1Code()%></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-po-PO02",requisitionHeader.getRequisitionType())%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-PO02", "UDF2", requisitionHeader.getRequisitionType())%>:&nbsp;</b></td>
<%
		String requisition_udf2 = requisitionHeader.getUdf2Code();
		if (oid.equalsIgnoreCase("vse06p"))
		{
			requisition_udf2 = InspectionLevel.toString(requisitionHeader.getUdf2Code(), oid);
		}
%>
									<td nowrap><%=requisition_udf2%></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-po-PO03",requisitionHeader.getRequisitionType())%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-PO03", "UDF3", requisitionHeader.getRequisitionType())%>:&nbsp;</b></td>
									<td nowrap><%=requisitionHeader.getUdf3Code()%></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-po-PO04",requisitionHeader.getRequisitionType())%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-PO04", "UDF4", requisitionHeader.getRequisitionType())%>:&nbsp;</b></td>
									<td nowrap><%=requisitionHeader.getUdf4Code()%></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-po-PO05",requisitionHeader.getRequisitionType())%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-PO05", "UDF5", requisitionHeader.getRequisitionType())%>:&nbsp;</b></td>
									<td nowrap><%=requisitionHeader.getUdf5Code()%></td>
								</tr>
								</table>
							</td>
							<td class=browseRow width=50% align=center>
								<table border=0 cellspacing=0 cellpadding=1 class=browseRow>
								<tr <%=HtmlWriter.isVisible(oid, "rec-numberOfPackages")%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-numberOfPackages", "# of Packages", true)%>:</b>&nbsp;</td>
									<td nowrap><input type=text name="ReceiptHeader_pkgsReceived" value="<%=receiptHeader.getPkgsReceived().setScale(0, BigDecimal.ROUND_HALF_UP)%>" style="text-align:right" tabIndex=2 size=10 onchange="formatPkgQty()"></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-packingSlip")%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-packingSlip", "Packing Slip", true)%>:&nbsp;</b></td>
									<td nowrap><input type=text name="ReceiptHeader_packingSlip" value="<%=receiptHeader.getPackingSlip()%>" tabIndex=4 onchange="upperCase(this);" maxLength=15></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-carrierCode")%>>
									<td nowrap align=right><b><a href="javascript: browseStd('ReceiptHeader_carrierCode', 'CARR'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-carrierCode", "Carrier Code")%> for this receipt or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-carrierCode", "Carrier Code", true)%>:</a>&nbsp;</b></td>
									<td nowrap><input type=text name="ReceiptHeader_carrierCode" value="<%=receiptHeader.getCarrierCode()%>" tabIndex=6 onchange="upperCase(this);" maxLength=15></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-receiptDate")%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptDate", "Receipt Date")%>:</b>&nbsp;</td>
									<td nowrap><input type=text name="ReceiptHeader_receiptDate" value="<%=HiltonUtility.getFormattedDate(receiptHeader.getReceiptDate(), oid, userDateFormat)%>"></td>
									<td><a href="javascript: show_calendar('ReceiptHeader_receiptDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-receivedBy")%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receivedBy", "Received By")%>:&nbsp;</b></td>
									<td nowrap><tsa:hidden name="ReceiptHeader_receivedBy" value="<%=receiptHeader.getReceivedBy()%>"/><%=receiptHeader.getReceivedBy()%></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-receivedBy")%>>
									<td nowrap align=right>&nbsp;</td>
									<td nowrap><%=UserManager.getInstance().getUser(oid, receiptHeader.getReceivedBy()).getDisplayName()%></td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan=2 align=center>
								<table border=0 cellspacing=0 cellpadding=0 class=browseRow>
								<tr <%=HtmlWriter.isVisible(oid, "rec-receiptNotes")%>>
									<td nowrap align=right valign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptNotes", "Notes", true)%>:</b>&nbsp;</td>
									<td><textarea name="ReceiptHeader_receiptNotes" cols=90 rows=3  onKeyDown="textCounter(this, 225);" onKeyUp="textCounter(this,225);" tabIndex=8>${esapi:encodeForHTML(receiptHeader.receiptNotes)}</textarea></td>
								</tr>
								</table>
							<td>
						</tr>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<br>

		<table border=0 cellpadding=2 cellspacing=0 width=675px>
		<tr>
			<td width=50%>&nbsp;<a href="javascript: viewAllItemDetails(); void(0);">View Details for All Items</a></td>
			<td width=50% align=right><a href="javascript: receiveAll(); void(0);">Receive All Items</a></td>
		</tr>
		</table>

		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=670px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRowHdr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=6% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineNumber", "Line #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=33% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-itemNumber", "Item/Part #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=11% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityOrdered", "Ordered")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width=11% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-balance", "Balance")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=11% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityReceived", "Received")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=11% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityRejected", "Rejected")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=11% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityAccepted", "Accepted")%></td>
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
		int ipl = 0;
		for (int il=0; il < requisitionLineList.size(); il++) {
			RequisitionLine requisitionLine = (RequisitionLine) requisitionLineList.get(il);
			if (requisitionLine != null) { // && requisitionLine.getStatus().compareTo(DocumentStatus.RCV_INVOICED) < 0) {
				BigDecimal balanceQty = (requisitionLine.getQuantity()).subtract(requisitionLine.getQuantity()); //CHECK THE QUANTITY RECEIVED, MAYBE THE QUANTITY RECEIVED IN THIS CASE ALWAYS BE 0
				String	endUser = requisitionLine.getRequisitionerCode();

				if (HiltonUtility.isEmpty(endUser)) {
					endUser = requisitionHeader.getRequisitionerCode();
				}
				if (HiltonUtility.isEmpty(endUser)) {
					endUser = requisitionHeader.getBuyer(); // THERE IS 2 POSSIBILITIES (BUYER , BUYERASSIGNED)
				}

				if (receiptMethod.equals("FinalizeReceipt")) {
					String forwardTo = receiptHeader.getForwardTo();
					if (forwardTo.equals("END-USERS") && !endUser.equals(uid)) {
						continue;
					}
				} else {
					if (requisitionLine.getReceiptRequired().equals("E")) {
						if (role.getAccessRights("RCVBYENDUSER") < 99 && !endUser.equals(uid)) {
							continue;
						} else if (role.getAccessRights("RCVBYENDUSER") < 3) {
							continue;
						}
					}
					else if (requisitionLine.getReceiptRequired().equals("R")) {
						if (role.getAccessRights("RCVBYITEM") < 3) {
							continue;
						}
					} else {
						// Item must be Previously Received or No Receipt Required
						continue;
					}
				}
				if (balanceQty.compareTo(bd_zero) <= 0) {
					balanceQty = bd_zero;
				}
				List receiptLineList = requisitionLine.getReceiptLineList();
				BigDecimal iclinekey = new BigDecimal("0");//requisitionLine.getIcLineKey();
				String	uom = requisitionLine.getUmCode();
				if (HiltonUtility.isEmpty(uom)) {
					uom = "EA";
				}
%>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=6% class=browseRow nowrap align=right><%=requisitionLine.getLineNumber().setScale(1, BigDecimal.ROUND_HALF_UP)%>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=33% class=browseRow nowrap><%=requisitionLine.getItemNumber()%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=11% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(requisitionLine.getQuantity(), oid)%> (<%=uom%>)</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width=11% class=browseRow nowrap align=right id="balanceQty"><%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=11% class=browseRow nowrap align=right>
						<input type=text name="ReceiptLine_qtyReceived" value="" style="text-align:right" size=10 onchange="checkBalance(<%=ipl%>);checkInventory(<%=ipl%>);" tabIndex=<%=((ipl + 1) *10)%>>
						<tsa:hidden name="balance" value="<%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%>"/>
						<tsa:hidden name="originalQtyReceived" value="<%=requisitionLine.getQuantity()%>"/>	<!-- CHECK THE QUANTITY RECEIVED, MAYBE THE QUANTITY RECEIVED IN THIS CASE ALWAYS BE 0 -->
						<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=receiptHeader.getIcRecHeader()%>"/>
						<tsa:hidden name="ReceiptLine_icRecLine" value=""/>
						<tsa:hidden name="ReceiptLine_icPoLine" value=""/>
						<tsa:hidden name="ReceiptLine_icReqLine" value="<%=requisitionLine.getIcReqLine()%>"/>
						<tsa:hidden name="PoLine_itemSource" value="<%=requisitionLine.getItemSource()%>"/>
						<tsa:hidden name="PoLine_itemNumber" value="<%=requisitionLine.getItemNumber()%>"/>
						<tsa:hidden name="PoLine_itemLocation" value="<%=requisitionLine.getItemLocation()%>"/>
						<tsa:hidden name="PoLine_unitPrice" value="<%=requisitionLine.getUnitPrice()%>"/>
						<tsa:hidden name="PoLine_umCode" value="<%=requisitionLine.getUmCode()%>"/>
						<tsa:hidden name="PoLine_receiptRequired" value="<%=requisitionLine.getReceiptRequired()%>"/>
						<tsa:hidden name="PoLine_asset" value="<%=requisitionLine.getAsset()%>"/>
						<tsa:hidden name="createReturn" value=""/>
						<tsa:hidden name="returnReceiptHeader_receiptNotes" value=""/>
						<tsa:hidden name="returnReceiptHeader_pkgsReceived" value=""/>
						<tsa:hidden name="returnReceiptHeader_packingSlip" value=""/><!--RMA #-->
						<tsa:hidden name="returnReceiptHeader_carrierCode" value=""/>
						<tsa:hidden name="returnReceiptHeader_returnDate" value=""/>
						<tsa:hidden name="returnReceiptHeader_icRecHeader" value=""/>
						<tsa:hidden name="returnReceiptHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
						<tsa:hidden name="returnReceiptHeader_icReqHeader" value="<%=requisitionHeader.getIcReqHeader()%>"/>
						<tsa:hidden name="returnReceiptLine_icRecHeader" value=""/>
						<tsa:hidden name="returnReceiptLine_qtyReceived" value=""/>
						<tsa:hidden name="returnReceiptLine_qtyRejected" value=""/>
						<tsa:hidden name="returnReceiptLine_qtyReturned" value=""/>
						<tsa:hidden name="returnReceiptLine_qtyAccepted" value=""/>
						<tsa:hidden name="returnReceiptLine_icPoLine" value=""/>
						<tsa:hidden name="returnReceiptLine_dispositionCode" value=""/>
						<tsa:hidden name="returnReceiptLine_receiptNotes" value=""/>
						<tsa:hidden name="receiptLineFactor" value=""/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=11% class=browseRow nowrap align=right>
						<input type=text name="ReceiptLine_qtyRejected" value="" style="text-align:right" size=10 onchange="setReceived(<%=ipl%>);checkRejected(<%=ipl%>);" tabIndex=<%=((ipl + 1) *10) + 1%>>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=11% class=browseRow nowrap align=right>
						<input type=text name="ReceiptLine_qtyAccepted" value="" style="text-align:right" size=10 readOnly>
					</td>
					<td width=3% class=browseRow nowrap>
						<div id="receiveAllLink">
<%	if (balanceQty.compareTo(bd_zero) >= 1) {%><a href="javascript: receiveAllForItem(<%=ipl%>); void(0);"><img src="<%=contextPath%>/images/checkmark.gif" border=0 alt="Receive Remaining Balance" tabIndex=-1></a><%}%>
						</div>
					</td>
					<td width=3% class=browseRow align=center>
						<div id="receiptNotes"><a href="javascript: viewReceiptNotes(<%=ipl%>); void(0);"><img src="<%=contextPath%>/images/cmd_edit.gif" border=0 alt="Enter Item Receipt Notes" tabIndex=-1></a></div>
					</td>
				</tr>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td class=browseRow valign=top><%=requisitionLine.getDescription()%></td>
					<td colspan=5 class=browseRow valign=top align=right>
						<table border=0 cellspacing=0 cellpadding=1 class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rec-inspectionCode")%>>
							<td height=16px  class=label align=right><a href="javascript: setCurrentRow(<%=ipl%>); browseStd('ReceiptLine_inspectionCode', 'INSP', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inspectionCode", "Inspection Code")%>:</a></td>
							<td height=16px><input type=text name="ReceiptLine_inspectionCode" size=20 onchange="upperCase(this);"></td>
						</tr>
						<tr id="itemRejectionCode" style="visibility: hidden; display:none;">
							<td height=16px  class=label align=right><a href="javascript: setCurrentRow(<%=ipl%>); browseStd('ReceiptLine_rejectionCode', 'REJ', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-rejectionCode", "Rejection Code",true)%>:</a></td>
							<td height=16px><input type=text name="ReceiptLine_rejectionCode" size=20 onchange="upperCase(this);"></td>
						</tr>
						<tr id="itemDispositionCode" style="visibility: hidden; display:none;">
							<td  <%=HtmlWriter.isVisible(oid, "rec-dispositionCode")%> height=16px  class=label align=right><a href="javascript: setCurrentRow(<%=ipl%>); browseStd('ReceiptLine_dispositionCode', 'DISP', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-dispositionCode", "Disposition Code", true)%>:</a></td>
							<td  <%=HtmlWriter.isVisible(oid, "rec-dispositionCode")%> height=16px><input type=text name="ReceiptLine_dispositionCode" size=20 onchange="upperCase(this);"></td>
						</tr>
						<tr id="itemReturnOption" style="visibility: hidden; display:none;">
							<td height=16px colspan=2 align=right><a href="javascript: returnRejected(<%=ipl%>); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-returnRejectedItems", "Return Rejected Items")%></a></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rec-LN01")%>>
							<td height=16px  class=label align=right><a href="javascript: setCurrentRow(<%=ipl%>); browseStd('ReceiptLine_udf1Code', 'LN01', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN01", "Line UDF 1")%>:</a></td>
							<td height=16px><input type=text name="ReceiptLine_udf1Code" size=20 maxLength=15 onchange="upperCase(this);"></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rec-LN02")%>>
							<td height=16px  class=label align=right><a href="javascript: setCurrentRow(<%=ipl%>); browseStd('ReceiptLine_udf2Code', 'LN02', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN02", "Line UDF 2")%>:</a></td>
							<td height=16px><input type=text name="ReceiptLine_udf2Code" size=20 maxLength=15 onchange="upperCase(this);"></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rec-LN03")%>>
							<td height=16px  class=label align=right><a href="javascript: setCurrentRow(<%=ipl%>); browseStd('ReceiptLine_udf3Code', 'LN03', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN03", "Line UDF 3")%>:</a></td>
							<td height=16px><input type=text name="ReceiptLine_udf3Code" size=20 maxLength=15 onchange="upperCase(this);"></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rec-LN04")%>>
							<td height=16px  class=label align=right><a href="javascript: setCurrentRow(<%=ipl%>); browseStd('ReceiptLine_udf4Code', 'LN04', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN04", "Line UDF 4")%>:</a></td>
							<td height=16px><input type=text name="ReceiptLine_udf4Code" size=20 maxLength=15 onchange="upperCase(this);"></td>
						</tr>
					<%if (!oid.equalsIgnoreCase("vse06p")) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rec-LN05")%>>
							<td height=16px  class=label align=right><a href="javascript: setCurrentRow(<%=ipl%>); browseStd('ReceiptLine_udf5Code', 'LN05', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN05", "Line UDF 5")%>:</a></td>
							<td height=16px><input type=text name="ReceiptLine_udf5Code" size=20 maxLength=15 onchange="upperCase(this);"></td>
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
							<td align=right valign=middle><a href="javascript: viewItemDetails(<%=ipl%>); void(0);" tabIndex="-1"><img src="<%=contextPath%>/images/plus.gif" border=0></a></td>
							<td valign=middle><a href="javascript: viewItemDetails(<%=ipl%>); void(0);" tabIndex="-1">View Item Details</a></td>
						</tr>
						</table>
						</div>
						<div id="closeItemDetails" style="visibility: hidden; display: none;">
						<table border=0 cellspacing=0 cellpadding=1>
						<tr>
							<td align=right valign=middle><a href="javascript: hideItemDetails(<%=ipl%>); void(0);" tabIndex="-1"><img src="<%=contextPath%>/images/minus.gif" border=0></a></td>
							<td valign=middle><a href="javascript: hideItemDetails(<%=ipl%>); void(0);" tabIndex="-1">Hide Item Details</a></td>
						</tr>
						</table>
						</div>
					</td>
					<td align=right colspan=5>
<%			if (receiptLineList != null && receiptLineList.size() > 0) {%>
						<div id="receiptHistory">
						<table border=0 cellspacing=0 cellpadding=0 width=100%>
						<tr><td align=right><a href="javascript: viewReceiptHistory(<%=ipl%>); void(0);">View Receipt History</a></td></tr>
						</table>
						</div>
<%			} else {%>
						<div id="receiptHistory">
						<table border=0 cellspacing=0 cellpadding=0 width=100%>
						<tr><td align=right>No Receipt History</td></tr>
						</table>
						</div>
<%			}%>
						<div id="itemReceiptNotes" style="visibility: hidden; display: none;">
						<table border=0 cellspacing=0 cellpadding=1 class=browseRow>
						<tr>
							<td align=right valign=top><a href="javascript: hideReceiptNotes(<%=ipl%>); void(0);"><img src="<%=contextPath%>/images/bar_close.gif" border=0 alt='Hide Item Receipt Notes'></a></td>
							<td class=label align=right valign=top><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcl-rec-receiptNotes", "Notes", false)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rcl-rec-receiptNotes")%> valign=top><textarea name="ReceiptLine_receiptNotes" cols=45 rows=4 onKeyDown="textCounter(this, 225);" onKeyUp="textCounter(this,225);"></textarea></td>
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
							<td <%=HtmlWriter.isVisible(oid, "rec-endUser")%> height=16px class=label align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "End User", false)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-endUser")%> height=16px nowrap><a href="javascript: viewUserInfo('<%=endUser%>'); void(0);"><%=UserManager.getInstance().getUser(oid,endUser).getDisplayName()%></a></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-asset")%> height=16px  class=label align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-asset", "Asset", false)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-asset")%> height=16px  colspan=3>
								<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
								<tr>
									<td valign=middle><input type="checkbox" name="c_asset" <% if (requisitionLine.getAsset().equals("Y") ) {%> checked="checked" <% } %> disabled="disabled"/></td>
	<%	if (assetsActive && requisitionLine.getAsset().equals("Y") && Integer.valueOf(requisitionLine.getStatus()).intValue() >= 3030 ) {%>
									<td valign=middle align=right><a href="javascript: viewAssetRelated('<%=iclinekey%>'); "><img src="<%=contextPath%>/images/asset3.gif" border=0></a></td>
									<td valign=middle><a href="javascript: viewAssetRelated('<%=iclinekey%>'); ">View Asset Related Information</a></td>
	<%	}%>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> height=16px width=18% class=label align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN01", "Line UDF 1", false)%>:</td>
<%
		String s_udf1= requisitionLine.getUdf1Code();
		if (oid.equalsIgnoreCase("vse06p")) {%>
							<td <%=HtmlWriter.isVisible(oid, "po-LN01")%>>
								<input type=checkbox name="c_checkbox" tabindex="9" <% if (s_udf1.equals("Y")) { %> CHECKED <% } %> value="Y" DISABLED>
								<tsa:hidden name="RequisitionLine_udf1Code" value="<%=s_udf1%>"/>
							</td>
<%	} else {	%>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> height=16px width=18%><%=requisitionLine.getUdf1Code()%></td>
<%	}
		String requisitionLine_udf2 = requisitionLine.getUdf2Code();
		if (oid.equalsIgnoreCase("vse06p"))
		{
			requisitionLine_udf2 = InspectionLevel.toString(requisitionLine.getUdf2Code(), oid);
		}
%>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> height=16px width=15% class=label align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN02", "Line UDF 2", false)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> height=16px width=18%><%=requisitionLine_udf2%></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN03")%> height=16px width=15% class=label align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN03", "Line UDF 3", false)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN03")%> height=16px width=16%><%=requisitionLine.getUdf3Code()%></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN04")%> height=16px class=label align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN04", "Line UDF 4", false)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN04")%>height=16px ><%=requisitionLine.getUdf4Code()%></td>
						<%if (!oid.equalsIgnoreCase("vse06p")) { %>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN05")%>height=16px  class=label align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN05", "Line UDF 5", false)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN05")%>height=16px ><%=requisitionLine.getUdf5Code()%></td>
						<% } %>
						</tr>
						</table>
						</div>
<%@ include file="/receipts/rec_item_history.jsp"%>
					</td>
					<td colspan=2 class=browseRow nowrap>&nbsp;</td>
				</tr>
<%			if (il < (requisitionLineList.size() - 1)) {%>
				<tr><td colspan=8><hr size=0 width=100%></td></tr>
<%			}
				ipl++;
			}
		}
%>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<div id="dummyFields" style="display:none;">
<tsa:hidden name="ReceiptLine_qtyReceived" value=""/>
<tsa:hidden name="ReceiptLine_qtyRejected" value=""/>
<tsa:hidden name="ReceiptLine_qtyAccepted" value=""/>
<tsa:hidden name="balance" value=""/>
<tsa:hidden name="originalQtyReceived" value=""/>
<tsa:hidden name="ReceiptLine_icRecHeader" value=""/>
<tsa:hidden name="ReceiptLine_icRecLine" value=""/>
<tsa:hidden name="ReceiptLine_icPoLine" value=""/>
<tsa:hidden name="ReceiptLine_receiptNotes" value=""/>
<tsa:hidden name="ReceiptLine_inspectionCode" value=""/>
<tsa:hidden name="ReceiptLine_rejectionCode" value=""/>
<tsa:hidden name="ReceiptLine_dispositionCode" value=""/>
<tsa:hidden name="PoLine_itemSource" value=""/>
<tsa:hidden name="PoLine_itemNumber" value=""/>
<tsa:hidden name="PoLine_itemLocation" value=""/>
<tsa:hidden name="PoLine_receiptRequired" value=""/>
<tsa:hidden name="PoLine_unitPrice" value=""/>
<tsa:hidden name="PoLine_umCode" value=""/>
<tsa:hidden name="createReturn" value=""/>
<tsa:hidden name="returnReceiptHeader_receiptNotes" value=""/>
<tsa:hidden name="returnReceiptHeader_pkgsReceived" value=""/>
<tsa:hidden name="returnReceiptHeader_packingSlip" value=""/><!--RMA #-->
<tsa:hidden name="returnReceiptHeader_carrierCode" value=""/>
<tsa:hidden name="returnReceiptHeader_returnDate" value=""/>
<tsa:hidden name="returnReceiptHeader_icRecHeader" value=""/>
<tsa:hidden name="returnReceiptHeader_icPoHeader" value=""/>
<tsa:hidden name="returnReceiptLine_icRecHeader" value=""/>
<tsa:hidden name="returnReceiptLine_qtyReceived" value=""/>
<tsa:hidden name="returnReceiptLine_qtyRejected" value=""/>
<tsa:hidden name="returnReceiptLine_qtyReturned" value=""/>
<tsa:hidden name="returnReceiptLine_qtyAccepted" value=""/>
<tsa:hidden name="returnReceiptLine_icPoLine" value=""/>
<tsa:hidden name="returnReceiptLine_dispositionCode" value=""/>
<tsa:hidden name="returnReceiptLine_receiptNotes" value=""/>
<tsa:hidden name="receiptLineFactor" value=""/>
</div>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><div id="forward_link"><a href="javascript: finalizeReceipt(); void(0);"><img class=button src="<%=contextPath%>/images/button_finalize.gif" border=0 tabIndex=100></a></div></td>
	<td width=50% align=center><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 tabIndex=102></a></td>
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

<%	if (receiptMethod.equals("FinalizeReceipt")) {%>
	frm.ReceiptHeader_pkgsReceived.disabled = true;
	frm.ReceiptHeader_packingSlip.disabled = true;
	frm.ReceiptHeader_carrierCode.disabled = true;
	frm.ReceiptHeader_receiptNotes.disabled = true;
<%	}%>

	var currentRow = 0;
	var qtyDecimals  = <%=Integer.valueOf(quantityDecimals).intValue()%>;
	var displayRejectionCode = <%=DictionaryManager.isVisible(oid, "rec-rejectionCode")%>;
	var displayDispositionCode = <%=DictionaryManager.isVisible(oid, "rec-dispositionCode")%>;

	function receiveAllForItem(row) {
		var balance = nformat(frm.balance[row].value, qtyDecimals);
		frm.ReceiptLine_qtyReceived[row].value = balance;
		frm.ReceiptLine_qtyRejected[row].value = nformat(0, qtyDecimals);

		checkBalance(row);
		if (frm.ReceiptLine_qtyReceived[row + 1] && frm.ReceiptLine_qtyReceived[row + 1].type != "hidden") {
			frm.ReceiptLine_qtyReceived[row + 1].focus();
		}
	}

	function checkBalance(row) {
		var receiptQty = nformat(eval(nfilter(frm.ReceiptLine_qtyReceived[row])), qtyDecimals);
		var rejectedQty = nformat(eval(nfilter(frm.ReceiptLine_qtyRejected[row])), qtyDecimals);
		var acceptedQty = nformat(receiptQty - rejectedQty, qtyDecimals);
		var originalBalance = nformat(eval(nfilter(frm.balance[row])), qtyDecimals);
		var newBalance = nformat(originalBalance - acceptedQty, qtyDecimals);

		frm.ReceiptLine_qtyReceived[row].value = receiptQty;
		frm.ReceiptLine_qtyAccepted[row].value = acceptedQty;

		if (newBalance <= 0) {
			var d = document.all("balanceQty");
			if (d.length > 1) {
				d(row).innerHTML = nformat(0.0, qtyDecimals);
			} else {
				d.innerHTML = nformat(0.0, qtyDecimals);
			}

			var imgs = document.all("receiveAllLink");
			if (imgs.length > 1) {
				imgs(row).style.visibility = "hidden";
			} else {
				imgs.style.visibility = "hidden";
			}
		} else {
			var d = document.all("balanceQty");
			if (d.length > 1) {
				d(row).innerHTML = newBalance;
			} else {
				d.innerHTML = newBalance;
			}

			var imgs = document.all("receiveAllLink");
			if (imgs.length > 1) {
				imgs(row).style.visibility = "visible";
			} else {
				imgs.style.visibility = "visible";
			}
		}
	}

	function viewReceiptHistory(row) {
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

	function hideReceiptHistory(row) {
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

	function viewReceiptNotes(row) {
		var d = document.all("itemReceiptNotes");
		if (d.length > 1) {
			d(row).style.visibility = "visible";
			d(row).style.display = "block";
		} else {
			d.style.visibility = "visible";
			d.style.display = "block";
		}
		var t = document.all("receiptNotes");
		if (t.length > 1) {
			t(row).style.visibility = "hidden";
			t(row).style.display = "block";
		} else {
			t.style.visibility = "hidden";
			t.style.display = "block";
		}
	}

	function hideReceiptNotes(row) {
		var d = document.all("itemReceiptNotes");
		if (d.length > 1) {
			d(row).style.visibility = "hidden";
			d(row).style.display = "none";
		} else {
			d.style.visibility = "hidden";
			d.style.display = "none";
		}
		var t = document.all("receiptNotes");
		if (t.length > 1) {
			t(row).style.visibility = "visible";
			t(row).style.display = "block";
		} else {
			t.style.visibility = "visible";
			t.style.display = "block";
		}
	}

	function receiveAll() {
		var items = frm.elements.item("ReceiptLine_qtyReceived");
		var itemCount = 0;

		if (items.length != undefined){
			itemCount = items.length - 1;
		}
		for (var i=0; i < itemCount; i++) {
			receiveAllForItem(i);
		}
	}

	function finalizeReceipt() {
		if (frm.receiptMethod.value == "ReceiveByTransfer") {
			if (checkInspectionRequirements()) {
				frm.receiptAction.value = "FORWARD";
				frm.failurePage.value = "/system/error_popup.jsp";
				if (doSubmitToNewTarget('receipts/rec_validate.jsp', 'ReceiptValidate', 'lookup_window')) {
					hideAreaWithBlock('forward_link');
				}
				frm.failurePage.value = "/system/error.jsp";
			}
		} else {
			doSubmit('receipts/rec_confirmation.jsp', 'ReceiptUpdate')
		}
	}

	function checkInventory(row) {
		var shipToInv = frm.ReceiptHeader_shipToInv.value;
		var itemLocation = frm.PoLine_itemLocation[row].value;
		var itemNumber = frm.PoLine_itemNumber[row].value;
		var itemCost = frm.PoLine_unitPrice[row].value;
		var umCode = frm.PoLine_umCode[row].value;
		var receiptOption = frm.PoLine_receiptRequired[row].value;
		var icRequisitionLine = frm.ReceiptLine_icReqLine[row].value;
		var qtyReceived = frm.ReceiptLine_qtyAccepted[row].value;
		var receiptLineFactor = frm.receiptLineFactor[row].value;

		if (shipToInv == "Y" && (receiptOption == "R" || receiptOption == "E")) {
			if (qtyReceived == 0) {
				popupParameters = "InvBinLocation_itemNumber=" + itemNumber + ";InvBinLocation_itemLocation=" + itemLocation + ";InvBinLocation_tempIc=" + frm.InvBinLocation_tempIc.value;

				setLookupParameters('/system/hide_iframe.jsp', 'InvBinLocationDeleteItemByTempIc');
				displayArea('getInfoFrame');
				document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
			} else {
				popupParameters = "InvItem_itemNumber=" + itemNumber + ";InvBinLocation_itemNumber=" + itemNumber + ";InvBinLocation_itemLocation=" + itemLocation + ";InvLocation_itemNumber=" + itemNumber + ";InvLocation_itemLocation=" + itemLocation + ";qtyReceived=" + qtyReceived + ";index=" + row + ";InvBinLocation_tempIc=" + frm.InvBinLocation_tempIc.value + ";InvBinLocation_cost=" + itemCost + ";RequisitionLine_icReqLine=" + icReqLine + ";RequisitionLine_umCode=" + umCode + ";receiptRow=" + row + ";receiptLineFactor=" + receiptLineFactor;
				doSubmitToPopup('receipts/rec_inv_bin_locations.jsp', 'InvItemRetrieveById;InvBinLocationRetrieveByItem', 'WIDTH=700px', 'HEIGHT=500px');
			}
		}
	}

	function formatPkgQty() {
		frm.ReceiptHeader_pkgsReceived.value = nformat(eval(nfilter(frm.ReceiptHeader_pkgsReceived)), 0);
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

	function setCurrentRow(row) {
		currentRow = row;
	}

	function checkInspectionRequirements() {
		var inspectionReqd = false;//<=requisitionHeader.isInspectionRequired()%>;
		if (inspectionReqd) {
			var items = frm.elements.item("ReceiptLine_qtyReceived");
			var itemCount = 0;

			if (items.length != undefined){
				itemCount = items.length - 1;
			}

			for (var i=0; i < itemCount; i++) {
				var receiptQty = eval(nfilter(frm.ReceiptLine_qtyReceived[i]));

				if (receiptQty > 0 && isEmpty(frm.ReceiptLine_inspectionCode[i].value)) {
					alert("An Inspection Code is required for all items received for this order.");
					return false;
				}
			}
		}
		return true;
	}

	function setReceived(row) {
		var qtyReceived = eval(nfilter(frm.ReceiptLine_qtyReceived[row]));
		var qtyRejected = eval(nfilter(frm.ReceiptLine_qtyRejected[row]));

		if (qtyRejected > qtyReceived) {
			alert("You cannot reject more than the quantity you have received.");
			qtyRejected = qtyReceived;

			frm.ReceiptLine_qtyRejected[row].value = qtyReceived;
		}
		checkBalance(row);
	}

	function checkRejected(row) {
		var qtyRejected = nformat(eval(nfilter(frm.ReceiptLine_qtyRejected[row])), qtyDecimals);

		if (qtyRejected > 0) {
			if (displayRejectionCode) {
				viewRejectionCode(row);
				frm.ReceiptLine_rejectionCode[row].select();
			}
			if (displayDispositionCode) {
				viewDispositionCode(row);
				if (!displayRejectionCode) {
					frm.ReceiptLine_dispositionCode[row].select();
				}
			}
		}
		else {
			frm.createReturn[row].value = "N";
			hideRejectionCode(row);
			hideDispositionCode(row);
		}
	}

	function viewRejectionCode(row) {
		var d = document.all("itemRejectionCode");
		if (d.length > 1) {
			d(row).style.visibility = "visible";
			d(row).style.display = "block";
		} else {
			d.style.visibility = "visible";
			d.style.display = "block";
		}
		var d = document.all("itemReturnOption");
		if (d.length > 1) {
			d(row).style.visibility = "visible";
			d(row).style.display = "block";
		} else {
			d.style.visibility = "visible";
			d.style.display = "block";
		}
	}

	function hideRejectionCode(row) {
		var d = document.all("itemRejectionCode");
		if (d.length > 1) {
			d(row).style.visibility = "hidden";
			d(row).style.display = "none";
		} else {
			d.style.visibility = "hidden";
			d.style.display = "none";
		}
		var d = document.all("itemReturnOption");
		if (d.length > 1) {
			d(row).style.visibility = "hidden";
			d(row).style.display = "none";
		} else {
			d.style.visibility = "hidden";
			d.style.display = "none";
		}
	}

	function viewDispositionCode(row) {
		var d = document.all("itemDispositionCode");
		if (d.length > 1) {
			d(row).style.visibility = "visible";
			d(row).style.display = "block";
		} else {
			d.style.visibility = "visible";
			d.style.display = "block";
		}
	}

	function hideDispositionCode(row) {
		var d = document.all("itemDispositionCode");
		if (d.length > 1) {
			d(row).style.visibility = "hidden";
			d(row).style.display = "none";
		} else {
			d.style.visibility = "hidden";
			d.style.display = "none";
		}
	}

	function viewAllItemDetails() {
		var items = frm.elements.item("ReceiptLine_qtyReceived");
		var itemCount = 0;

		if (items.length != undefined){
			itemCount = items.length - 1;
		}
		for (var i=0; i < itemCount; i++) {
			viewItemDetails(i);
		}
	}

	function viewItemDetails(row) {
		var d = document.all("openItemDetails");
		if (d.length > 1) {
			d(row).style.visibility = "hidden";
			d(row).style.display = "none";
		} else {
			d.style.visibility = "hidden";
			d.style.display = "none";
		}
		var t = document.all("closeItemDetails");
		if (t.length > 1) {
			t(row).style.visibility = "visible";
			t(row).style.display = "block";
		} else {
			t.style.visibility = "visible";
			t.style.display = "block";
		}
		var s = document.all("itemDetails");
		if (s.length > 1) {
			s(row).style.visibility = "visible";
			s(row).style.display = "block";
		} else {
			s.style.visibility = "visible";
			s.style.display = "block";
		}
	}

	function hideItemDetails(row) {
		var d = document.all("openItemDetails");
		if (d.length > 1) {
			d(row).style.visibility = "visible";
			d(row).style.display = "block";
		} else {
			d.style.visibility = "visible";
			d.style.display = "block";
		}
		var t = document.all("closeItemDetails");
		if (t.length > 1) {
			t(row).style.visibility = "hidden";
			t(row).style.display = "none";
		} else {
			t.style.visibility = "hidden";
			t.style.display = "none";
		}
		var s = document.all("itemDetails");
		if (s.length > 1) {
			s(row).style.visibility = "hidden";
			s(row).style.display = "none";
		} else {
			s.style.visibility = "hidden";
			s.style.display = "none";
		}
	}

	function returnRejected(row) {
		var originalQtyReceived = eval(nfilter(frm.originalQtyReceived[row]));
		var qtyReceived = eval(nfilter(frm.ReceiptLine_qtyReceived[row]));
		var totalQtyRecieved = eval(originalQtyReceived + qtyReceived);

		popupParameters = "ReceiptHeader_icReqHeader=" + frm.ReceiptHeader_icReqHeader.value;
		popupParameters = popupParameters + ";icReqLine=" + frm.ReceiptLine_icReqLine[row].value;
		popupParameters = popupParameters + ";qtyRejected=" + frm.ReceiptLine_qtyRejected[row].value;
		popupParameters = popupParameters + ";qtyReceived=" + totalQtyRecieved;
		popupParameters = popupParameters + ";dispositionCode=" + frm.ReceiptLine_dispositionCode[row].value;
		popupParameters = popupParameters + ";currentRow=" + row;
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(uid)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";

		doSubmitToPopup('receipts/rec_return_popup.jsp', 'ReceiptCreateRetrieve', 'width=692px', 'height=325px');
	}

// End Hide script -->
</SCRIPT>