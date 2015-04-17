<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.entity.StdTable" %>
<%@ page import="com.tsa.puridiom.stdtable.tasks.StdTableManager" %>
<%@ page import="java.math.BigDecimal" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	quantityDecimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	String	currentRow = HiltonUtility.ckNull((String) request.getAttribute("currentRow"));
	String	dispositionCode = HiltonUtility.ckNull((String) request.getAttribute("dispositionCode"));
	BigDecimal	qtyReceived = new BigDecimal(HiltonUtility.ckNull((String) request.getAttribute("qtyReceived")));
	BigDecimal	qtyRejected = new BigDecimal(HiltonUtility.ckNull((String) request.getAttribute("qtyRejected")));
	BigDecimal	icPoLine = new BigDecimal(HiltonUtility.ckNull((String) request.getAttribute("icPoLine")));
	StdTableManager stdTableManager = StdTableManager.getInstance(oid);
	List carrierList = stdTableManager.getStdTables("CARR");
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

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
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
									<td nowrap><input type=text name="ReceiptHeader_packingSlip" value="<%=receiptHeader.getPackingSlip()%>" tabIndex=4 onchange="upperCase(this);" maxLength=15></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-carrierCode")%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-carrierCode", "Carrier Code", true)%>:&nbsp;</b></td>
									<td nowrap>
									<select name="ReceiptHeader_carrierCode" size=1>
									<option <% if (receiptHeader.getCarrierCode().equals("")) {%> SELECTED <%}%> value=""></option>
									<% for (Iterator iterator = carrierList.iterator(); iterator.hasNext();) {
										StdTable stdTable = (StdTable) iterator.next();
									%>
									<option <% if (stdTable.getComp_id().getTableKey().equals(receiptHeader.getCarrierCode())) {%> SELECTED <%}%> value="<%=stdTable.getComp_id().getTableKey() %>"><%=stdTable.getDescription() %></option>
									<% } %>
									</select>
									</td>
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
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=6% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineNumber", "Line #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=48% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-itemNumber", "Item/Part #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=12% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityOrdered", "Ordered")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=12% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityReceived", "Received")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReturned")%> width=18% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityReturned", "Returned")%></td>
					<td width=4% class=browseHdr nowrap>&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
<%
		int ipol = 0;
		for (int il=0; il < poLineList.size(); il++) {
			PoLine poLine = (PoLine) poLineList.get(il);
			if (poLine != null && poLine.getStatus().compareTo(DocumentStatus.RCV_INVOICED) < 0 && (poLine.getReceiptRequired().equals("E") || poLine.getReceiptRequired().equals("R")) && (poLine.getIcPoLine().compareTo(icPoLine) ==0) ) {
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
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=48% class=browseRow nowrap><%=poLine.getItemNumber()%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=12% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid)%> (<%=uom%>)</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=12% class=browseRow nowrap align=right id="updatedQty"><%=HiltonUtility.getFormattedQuantity(qtyReceived, oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReturned")%> width=18% class=browseRow nowrap align=right>
					<% if (qtyReceived.compareTo(new BigDecimal(0)) <= 0) {%>
							<tsa:hidden name="adjFactorDisplay" value="-"/>
							<tsa:hidden name="adjustmentQty" value=""/>
					<% } else {%>
							<input type=text name="adjFactorDisplay" disabled value="-" size=1>
							<input type=text name="adjustmentQty" value="<%=qtyRejected%>" style="text-align:right" size=10 disabled>
					<% }%>
							<tsa:hidden name="adjFactor" value="-1.0"/>
							<tsa:hidden name="ReceiptLine_qtyReturned" value="<%=qtyRejected%>"/>
							<tsa:hidden name="ReceiptLine_qtyRejected" value="<%=qtyRejected.multiply(new BigDecimal(-1))%>"/>
							<tsa:hidden name="ReceiptLine_qtyReceived" value="0"/>
							<tsa:hidden name="qtyReceived" value="<%=HiltonUtility.getFormattedQuantity(qtyReceived, oid)%>"/>
							<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=receiptHeader.getIcRecHeader()%>"/>
							<tsa:hidden name="ReceiptLine_icRecLine" value=""/>
							<tsa:hidden name="ReceiptLine_icPoLine" value="<%=poLine.getIcPoLine()%>"/>
							<tsa:hidden name="ReceiptLine_dispositionCode" value="<%=dispositionCode%>"/>
					</td>
					<td width=4% class=browseRow align=center nowrap>
							<div id="receiptNotes" <% if (qtyReceived.compareTo(new BigDecimal(0)) <= 0) {%>style="visibility: hidden;"<%}%>><a href="javascript: viewReceiptNotes(<%=ipol%>); void(0);"><img src="<%=contextPath%>/images/cmd_edit.gif" border=0 alt="Item Return Notes" tabIndex=-1></a></div>
					</td>
				</tr>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td class=browseRow valign=top><%=poLine.getDescription()%></td>
					<td class=browseRow valign=top align=right colspan=4>&nbsp;</td>
				</tr>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td class=browseRow colspan=4 align=right>
						<div id="itemReceiptNotes" style="visibility: hidden; display: none;">
							<table border=0 cellspacing=0 cellpadding=1>
							<tr>
								<td align=right valign=top><a href="javascript: hideReceiptNotes(<%=ipol%>); void(0);"><img src="<%=contextPath%>/images/bar_close.gif" border=0 alt"Close Item Return Notes"></a></td>
								<td valign=top><b>Item Return Notes</b></td>
								<td valign=top><textarea name="ReceiptLine_receiptNotes" cols=45 rows=4  onKeyDown="textCounter(this, 225);" onKeyUp="textCounter(this,225);"></textarea></td>
							</tr>
							</table>
						</div>
					</td>
					<td class=browseRow nowrap>&nbsp;</td>
				</tr>
<%
				ipol++;
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

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><div id="forward_link"><a href="javascript: saveReturn(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 tabIndex=100></a></div></td>
	<td align="center" width=50%><a href="javascript: window.top.hidePopWin();"><img class="button" src="<%=contextPath%>/images/button_close.gif" border=0></a></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();

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

	function saveReturn() {
		var row = <%=currentRow%>;

		if (row == 0) {
			window.parent.frm.returnReceiptHeader_icRecHeader.value=frm.ReceiptHeader_icRecHeader.value;
			window.parent.frm.returnReceiptHeader_receiptNotes.value=frm.ReceiptHeader_receiptNotes.value;
			window.parent.frm.returnReceiptHeader_pkgsReceived.value=frm.ReceiptHeader_pkgsReceived.value;
			window.parent.frm.returnReceiptHeader_packingSlip.value=frm.ReceiptHeader_packingSlip.value;
			window.parent.frm.returnReceiptHeader_carrierCode.value=frm.ReceiptHeader_carrierCode.value;
			window.parent.frm.returnReceiptHeader_returnDate.value=frm.ReceiptHeader_returnDate.value;
			window.parent.frm.returnReceiptLine_dispositionCode.value=frm.ReceiptLine_dispositionCode.value;
			window.parent.frm.returnReceiptLine_icRecHeader.value=frm.ReceiptLine_icRecHeader.value;
			window.parent.frm.returnReceiptLine_receiptNotes.value=frm.ReceiptLine_receiptNotes.value;
			window.parent.frm.returnReceiptLine_qtyReceived.value=frm.ReceiptLine_qtyReceived.value;
			window.parent.frm.returnReceiptLine_qtyRejected.value=frm.ReceiptLine_qtyRejected.value;
			window.parent.frm.returnReceiptLine_qtyReturned.value=frm.ReceiptLine_qtyReturned.value;
			window.parent.frm.createReturn.value = "Y";
		}
		else
		{
			window.parent.frm.returnReceiptHeader_icRecHeader[row].value=frm.ReceiptHeader_icRecHeader.value;
			window.parent.frm.returnReceiptHeader_receiptNotes[row].value=frm.ReceiptHeader_receiptNotes.value;
			window.parent.frm.returnReceiptHeader_pkgsReceived[row].value=frm.ReceiptHeader_pkgsReceived.value;
			window.parent.frm.returnReceiptHeader_packingSlip[row].value=frm.ReceiptHeader_packingSlip.value;
			window.parent.frm.returnReceiptHeader_carrierCode[row].value=frm.ReceiptHeader_carrierCode.value;
			window.parent.frm.returnReceiptHeader_returnDate[row].value=frm.ReceiptHeader_returnDate.value;
			window.parent.frm.returnReceiptLine_dispositionCode[row].value=frm.ReceiptLine_dispositionCode.value;
			window.parent.frm.returnReceiptLine_icRecHeader[row].value=frm.ReceiptLine_icRecHeader.value;
			window.parent.frm.returnReceiptLine_receiptNotes[row].value=frm.ReceiptLine_receiptNotes.value;
			window.parent.frm.returnReceiptLine_qtyReceived[row].value=frm.ReceiptLine_qtyReceived.value;
			window.parent.frm.returnReceiptLine_qtyRejected[row].value=frm.ReceiptLine_qtyRejected.value;
			window.parent.frm.returnReceiptLine_qtyReturned[row].value=frm.ReceiptLine_qtyReturned.value;
			window.parent.frm.createReturn[row].value = "Y";
		}
		
		doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');
	}

// end hiding contents -->
</SCRIPT>