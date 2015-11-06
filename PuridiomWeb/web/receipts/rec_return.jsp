<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	quantityDecimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	if (receiptHeader == null) {
		receiptHeader = new ReceiptHeader();
	}
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	if (poHeader == null) {
		poHeader = new PoHeader();
	}
	List	poLineList = poHeader.getPoLineList();
	if (poLineList == null) {
		poLineList = new ArrayList();
	}
	Address vendor = (Address) poHeader.getVendorAddress();
	if (vendor == null) {
		vendor = new Address();
	}
	String	receiptNumber = receiptHeader.getReceiptNumber();
	if (HiltonUtility.isEmpty(receiptNumber)) {
		receiptNumber = "N/A";
	}
	BigDecimal	bd_zero = new BigDecimal(0);
%>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=receiptHeader.getIcRecHeader()%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=receiptHeader.getReceiptNumber()%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=receiptHeader.getReceiptStatus()%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=receiptHeader.getFiscalYear()%>"/>
<tsa:hidden name="ReceiptHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="R"/>
<tsa:hidden name="receiptMethod" value="Return"/>
<tsa:hidden name="createAction" value="SAVE"/>
<tsa:hidden name="receiptAction" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>

<table width=780px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Return Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td nowrap align=right><b>Order #:</b></td>
			<td width=100px><%=poHeader.getPoNumber()%></td>
<%	if (poHeader.getReleaseNumber().compareTo(bd_zero) > 0)
		{%>
			<td nowrap align=right><b>Release #:</b></td>
			<td width=100px><%=poHeader.getReleaseNumber()%></td>
<%	}
		if (poHeader.getRevisionNumber().compareTo(bd_zero) > 0)
		{%>
			<td nowrap align=right><b>Revision #:</b></td>
			<td width=100px><%=poHeader.getRevisionNumber()%></td>
<%	} %>
		</tr>
		<tr>
			<td align=right><b>Receipt #:</b></td>
			<td width=100px><%=receiptNumber%></td>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=100% align=center valign=top>
				<table id=receiptInfoTable border=1 cellspacing=0 cellpadding=0 width=660px class=browseHdr>
				<tr>
					<td colspan=2 class=browseHdr height=18px nowrap>&nbsp;Return Information</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr>
							<td class=browseRow width=45%>
								<table border=0 cellspacing=0 cellpadding=1>
								<tr <%=HtmlWriter.isVisible(oid, "rec-supplier")%>>
									<td nowrap align=right width=91px><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-supplier", "Supplier")%>:&nbsp;</b></td>
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
					if (!HiltonUtility.isEmpty(poHeader.getContactName())) { %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-sup-attention")%>><td>&nbsp;</td><td nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-sup-attention", "Attn")%>:&nbsp<%=HiltonUtility.ckNull(poHeader.getContactName())%></td></tr>
		<%		} %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-receiptDate")%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptDate", "Receipt Date")%>:</b>&nbsp;</td>
									<td nowrap><%=HiltonUtility.getFormattedDate(receiptHeader.getReceiptDate(), oid, userDateFormat)%></td>
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
							<td class=browseRow width=55% align=center>
								<table border=0 cellspacing=0 cellpadding=1 class=browseRow>
								<tr <%=HtmlWriter.isVisible(oid, "rec-numberOfPackages")%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-numberOfPackages", "# of Packages", true)%>:</b>&nbsp;</td>
									<td nowrap><input type=text name="ReceiptHeader_pkgsReceived" value="<%=receiptHeader.getPkgsReceived().setScale(0, BigDecimal.ROUND_HALF_UP)%>" style="text-align:right" tabIndex=2 size=10></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-rmaNumber")%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-rmaNumber", "RMA #", true)%>:&nbsp;</b></td>
									<td nowrap><input type=text name="ReceiptHeader_packingSlip" value="<%=receiptHeader.getPackingSlip()%>" tabIndex=4 onchange="upperCase(this);"></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-carrierCode")%>>
									<td nowrap align=right><b><a href="javascript: browseStd('ReceiptHeader_carrierCode', 'CARR'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-carrierCode", "Carrier Code")%> for this return or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-carrierCode", "Carrier Code", true)%>:</a>&nbsp;</b></td>
									<td nowrap><input type=text name="ReceiptHeader_carrierCode" value="<%=receiptHeader.getCarrierCode()%>" tabIndex=6 onchange="upperCase(this);"></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-returnDate")%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-returnDate", "Return Date")%>:&nbsp;</b></td>
									<td nowrap>
										<input type=text name="ReceiptHeader_returnDate" value="<%=HiltonUtility.getFormattedDate(receiptHeader.getReturnDate(), oid, userDateFormat)%>" tabIndex=7>
										<a href="javascript: show_calendar('ReceiptHeader_returnDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan=2>
								<table border=0 cellspacing=0 cellpadding=0 class=browseRow>
								<tr <%=HtmlWriter.isVisible(oid, "rec-receiptNotes")%>>
									<td nowrap align=right valign=top width=93px><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptNotes", "Notes", true)%>:</b>&nbsp;</td>
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
		for (int il=0; il < poLineList.size(); il++) {
			PoLine poLine = (PoLine) poLineList.get(il);
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
							<tsa:hidden name="adjFactorDisplay" value="-"/>
							<tsa:hidden name="adjustmentQty" value=""/>
					<% } else {%>
							<input type=text name="adjFactorDisplay" disabled value="-" size=1>
							<input type=text name="adjustmentQty" value="" style="text-align:right" size=10 onchange="checkBalance(<%=ipl%>); void(0);" tabIndex=<%=ipl + 10%>>
					<% }%>
							<tsa:hidden name="adjFactor" value="-1.0"/>
							<tsa:hidden name="ReceiptLine_qtyReceived" value=""/>
							<tsa:hidden name="ReceiptLine_qtyRejected" value=""/>
							<tsa:hidden name="ReceiptLine_qtyReturned" value=""/>
							<tsa:hidden name="ReceiptLine_qtyAccepted" value=""/>
							<tsa:hidden name="qtyReceived" value="<%=HiltonUtility.getFormattedQuantity(poLine.getQtyReceived(), oid)%>"/>
							<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=receiptHeader.getIcRecHeader()%>"/>
							<tsa:hidden name="ReceiptLine_icRecLine" value=""/>
							<tsa:hidden name="ReceiptLine_icPoLine" value="<%=poLine.getIcPoLine()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-previouslyRejected")%> width=10% class=browseRow nowrap align=center><input type=checkbox name="c_prevRejected" value="Y" onclick="checkBalance(<%=ipl%>);"></td>
					<td width=4% class=browseRow align=center nowrap>
							<div id="receiptNotes" <% if (poLine.getQtyReceived().compareTo(new BigDecimal(0)) <= 0) {%>style="visibility: hidden;"<%}%>><a href="javascript: viewReceiptNotes(<%=ipl%>); void(0);"><img src="<%=contextPath%>/images/cmd_edit.gif" border=0 alt="Item Return Notes" tabIndex=-1></a></div>
					</td>
				</tr>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td colspan=2 class=browseRow valign=top><%=poLine.getDescription()%></td>
					<td colspan=3 class=browseRow nowrap align=right>
					<% if (poLine.getQtyReceived().compareTo(new BigDecimal(0)) <= 0) {%>
							<tsa:hidden name="ReceiptLine_dispositionCode" value=""/>
					<% } else {
						if ( !oid.equalsIgnoreCase("DTN07P")) {%>
						<table border=0 cellspacing=0 cellpadding=1 class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rec-dispositionCode")%> id="itemDispostionCode">
							<td height=16px  class=label align=right><a href="javascript: setCurrentRow(<%=il%>); browseStd('ReceiptLine_dispositionCode', 'DISP', true); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-dispositionCode", "Disposition Code", true)%>:</a></td>
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
									<td align=right valign=top><a href="javascript: hideReceiptNotes(<%=il%>); void(0);"><img src="<%=contextPath%>/images/bar_close.gif" border=0 alt='Hide Item Receipt Notes'></a></td>
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
						<tr><td align=right><a href="javascript: viewReceiptHistory(<%=il%>); void(0);">View Receipt History</a></td></tr>
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
<tsa:hidden name="ReceiptLine_qtyReturned" value=""/>
<tsa:hidden name="ReceiptLine_qtyAccepted" value=""/>
<tsa:hidden name="ReceiptLine_icRecHeader" value=""/>
<tsa:hidden name="ReceiptLine_icRecLine" value=""/>
<tsa:hidden name="ReceiptLine_icPoLine" value=""/>
<tsa:hidden name="ReceiptLine_notes" value=""/>
<tsa:hidden name="adjFactor" value=""/>
<tsa:hidden name="adjustmentQty" value=""/>
<tsa:hidden name="qtyReceived" value=""/>
<tsa:hidden name="qtyPrevRejected" value=""/>
<tsa:hidden name="c_prevRejected" value=""/>
</div>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: finalizeReceipt(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
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

	var qtyDecimals = <%=Integer.valueOf(quantityDecimals).intValue()%>;

	function checkBalance(row) {
		var adjustmentQty = nformat(eval(nfilter(frm.adjustmentQty[row])), qtyDecimals);
		var adjustmentFactor = frm.adjFactor[row].value;
		var adjustedQty = adjustmentQty;
		var previouslyRejected = frm.c_prevRejected[row].checked;

		frm.adjustmentQty[row].value = adjustmentQty;

		adjustedQty = eval(adjustmentQty * adjustmentFactor);
		var receiptQty = eval(nfilter(frm.qtyReceived[row]));
		var newBalance = nformat(eval(receiptQty + adjustedQty), qtyDecimals);
		if (newBalance < 0) {
			alert("You cannot return more than the quantity received.");
			frm.adjustmentQty[row].value =eval(nformat(receiptQty, qtyDecimals));
			frm.adjustmentQty[row].select();
//			frm.adjustmentQty[row].focus();
			newBalance = "0.00";
		} else {
			if (previouslyRejected) {
				var qtyPrevRejected = nformat(eval(nfilter(frm.qtyPrevRejected[row])), qtyDecimals);
				var newRejectedQty = eval(qtyPrevRejected) + eval(adjustedQty);
				if (newRejectedQty < 0) {
					if (qtyPrevRejected >0) {
						alert("Only " + qtyPrevRejected + " have previously been rejected and not yet returned.");
					} else {
						alert("This item does not  have a previously rejected quantity that has not yet been returned.");
					}
					 frm.c_prevRejected[row].checked = false;
					previouslyRejeted = false;
				}
			}
			if (!previouslyRejected) {
				adjustedQty = eval("0.00");
			}
			frm.ReceiptLine_qtyRejected[row].value = adjustedQty;
			frm.ReceiptLine_qtyReceived[row].value =eval("0.00");
			frm.ReceiptLine_qtyReturned[row].value = adjustmentQty;
			frm.ReceiptLine_qtyAccepted[row].value = eval(-(adjustmentQty) - (adjustedQty));
		}
		var d = document.all("updatedQty");
		if (d.length > 1) {
			d(row).innerHTML = nformat(newBalance, qtyDecimals);
		} else {
			d.innerHTML = nformat(newBalance, qtyDecimals);
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
			t(row).style.display = "none";
		} else {
			t.style.visibility = "hidden";
			t.style.display = "none";
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

	function finalizeReceipt() {
		frm.receiptAction.value = "FORWARD";
		frm.failurePage.value = "/system/error_popup.jsp";
		if (doSubmitToNewTarget('receipts/rec_validate.jsp', 'ReceiptValidate', 'lookup_window')) {
			hideAreaWithBlock('forward_link');
		}
		frm.failurePage.value = "/system/error.jsp";
//		doSubmit('receipts/rec_confirmation.jsp', 'ReceiptUpdate')
	}

// End Hide script -->
</SCRIPT>