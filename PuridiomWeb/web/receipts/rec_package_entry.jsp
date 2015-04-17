<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.InspectionLevel" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/browse.js"></SCRIPT>
<%
	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	if (receiptHeader == null) {
		receiptHeader = new ReceiptHeader();
	}
	if (poHeader == null) {
		poHeader = new PoHeader();
	}
	List	lineList = poHeader.getPoLineList();
	if (lineList == null) {
		lineList = new ArrayList();
	}
	Address vendorAddress = (Address) poHeader.getVendorAddress();
	if (vendorAddress == null) {
		vendorAddress = new Address();
	}
	String	forwardTo = receiptHeader.getForwardTo();
	String	receiptNumber = receiptHeader.getReceiptNumber();
	if (HiltonUtility.isEmpty(receiptNumber)) {
		receiptNumber = "N/A";
	}
	BigDecimal	bd_zero = new BigDecimal(0);
%>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=receiptHeader.getIcRecHeader()%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=receiptHeader.getReceiptNumber()%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=DocumentStatus.RCV_INPROGRESS%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=receiptHeader.getFiscalYear()%>"/>
<tsa:hidden name="ReceiptHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="O"/>
<tsa:hidden name="receiptMethod" value="ReceiveByPackage"/>
<tsa:hidden name="receiptAction" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>

<table width=<%=formEntryWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<td colspan=2 class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receipt_information", "Receipt Information")%></td>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=* align=right>
		<table border=0 cellspacing=0 cellpadding=1>
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

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=100% align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td width=100% align=center valign=top>
				<table id=receiptInfoTable border=1 cellspacing=0 cellpadding=0 width=90% class=browseHdr>
				<tr>
					<td colspan=2 class=browseHdr height=18px nowrap>&nbsp;Receipt Information</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr>
							<td class=browseRow width=40% vAlign=top>
								<table border=0 cellspacing=0 cellpadding=1>
								<tr <%=HtmlWriter.isVisible(oid, "rec-supplier")%>>
									<td nowrap align=right width=91px><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-supplier", "Supplier")%>:&nbsp;</b></td>
									<td nowrap><tsa:hidden name="ReceiptHeader_vendorId" value="<%=poHeader.getVendorId()%>"/><%=poHeader.getVendorId()%></td>
								</tr>
		<%		if (!HiltonUtility.isEmpty(vendorAddress.getAddressLine1())) { %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine1")%>><td>&nbsp;</td><td nowrap><%=vendorAddress.getAddressLine1()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(vendorAddress.getAddressLine2())) { %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine2")%>><td>&nbsp;</td><td  height=12px nowrap><%=vendorAddress.getAddressLine2()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(vendorAddress.getAddressLine3())) { %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine3")%>><td>&nbsp;</td><td nowrap><%=vendorAddress.getAddressLine3()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(vendorAddress.getAddressLine4())) { %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine4")%>><td>&nbsp;</td><td nowrap><%=vendorAddress.getAddressLine4()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(vendorAddress.getCity() + vendorAddress.getState() + vendorAddress.getPostalCode())) { %>
								<tr><td>&nbsp;</td><td nowrap><%=vendorAddress.getCity()%>&nbsp;<%=vendorAddress.getState()%>&nbsp;<%=vendorAddress.getPostalCode()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(vendorAddress.getCountry())) {%>
								<tr <%=HtmlWriter.isVisible(oid, "rec-sup-country")%>><td>&nbsp;</td><td nowrap><%=vendorAddress.getCountry()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(poHeader.getContactName())) { %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-sup-attention")%>><td>&nbsp;</td><td nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-sup-attention", "Attn")%>:&nbsp<%=HiltonUtility.ckNull(poHeader.getContactName())%></td></tr>
		<%		} %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-receiptDate")%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptDate", "Receipt Date")%>:&nbsp;</b></td>
									<td nowrap><tsa:hidden name="ReceiptHeader_receiptDate" value="<%=HiltonUtility.getFormattedDate(receiptHeader.getReceiptDate(), oid, userDateFormat)%>"/><%=HiltonUtility.getFormattedDate(receiptHeader.getReceiptDate(), oid, userDateFormat)%></td>
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
							<td class=browseRow width=60% align=center vAlign=top>
								<table border=0 cellspacing=0 cellpadding=1 class=browseRow>
								<tr <%=HtmlWriter.isVisible(oid, "rec-numberOfPackages")%>>
									<td nowrap align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-numberOfPackages", "# of Packages", true)%>:&nbsp;</td>
									<td nowrap><input type=text name="ReceiptHeader_pkgsReceived" value="<%=receiptHeader.getPkgsReceived().setScale(0, BigDecimal.ROUND_HALF_UP)%>" style="text-align:right" tabIndex=2 size=10 onchange="formatPkgQty()"></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-packingSlip")%>>
									<td nowrap align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-packingSlip", "Packing Slip", true)%>:&nbsp;</td>
									<td nowrap><input type=text name="ReceiptHeader_packingSlip" value="<%=receiptHeader.getPackingSlip()%>" tabIndex=4 onchange="upperCase(this);"></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-carrierCode")%>>
									<td nowrap align=right class=label><a href="javascript: browseStd('ReceiptHeader_carrierCode', 'CARR'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-carrierCode", "Carrier Code")%> for this receipt or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-carrierCode", "Carrier Code", true)%>:</a>&nbsp;</td>
									<td nowrap><input type=text name="ReceiptHeader_carrierCode" value="<%=receiptHeader.getCarrierCode()%>" tabIndex=6 onchange="upperCase(this);"></td>
								</tr>
								<tr>
									<td nowrap align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-forwardto", "Forward To")%>:</td>
									<td nowrap>
										<table border=0 cellpadding=1 cellspacing=0>
										<tr>
											<td align=right><input type=radio name=forwardToType value="E" onclick="setForwardTo();"></td>
											<td nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "End User")%></td>
											<td align=right><input type=radio name=forwardToType value="S" onclick="setForwardTo();"></td>
											<td nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-espUser", "Specify User")%></td>

										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td nowrap>
										<div id=forwardToSelection style="visibility:hidden; display:block;">
										<table border=0 cellpadding=1 cellspacing=0>
										<tr>
											<td><input type=text name="as_forwardTo" value="<%=receiptHeader.getForwardTo()%>" onchange="setForwardTo(); getNewInfo('receiver', this);" tabIndex=8></td>
											<td><a href="javascript: setEndUsersChecked(false); browseLookup('ReceiptHeader_forwardTo', 'receiver');void(0);"><img src="<%=contextPath%>/images/browser.gif" border=0></a></td>
										</tr>
										<tr>
											<td colspan=2>
												<tsa:hidden name="ReceiptHeader_forwardTo" value="<%=receiptHeader.getForwardTo()%>"/>
												<input type=text name="as_forwardToName" value="<%=UserManager.getInstance().getUser(oid, receiptHeader.getForwardTo()).getDisplayName()%>" disabled size=25>
											</td>
										</tr>
										</table>
										</div>
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
									<td><textarea name="ReceiptHeader_receiptNotes" cols=90 rows=3  onKeyDown="textCounter(this, 225);" onKeyUp="textCounter(this,225);" tabIndex=10>${esapi:encodeForHTML(receiptHeader.receiptNotes)}</textarea></td>
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
		<tr><td>&nbsp;</td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center width=100%>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=85% class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRowHdr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=10% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineNumber", "Line #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=30% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-itemNumber", "Item/Part #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-asset")%> width=6% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-asset", "Asset")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-endUser")%> width=12% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "End User")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=12% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityOrdered", "Qty Ordered")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width=12% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-balance", "Balance")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineStatus")%> width=18% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineStatus", "Status")%></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow id="itemRows">
<%
		String	defaultForwardTo = "";

		for (int il=0; il < lineList.size(); il++) {
			PoLine poLine = (PoLine) lineList.get(il);
			if (poLine != null) {
				BigDecimal balanceQty = (poLine.getQuantity()).subtract(poLine.getQtyReceived());
				String	endUser = poLine.getRequisitionerCode();

				if (HiltonUtility.isEmpty(endUser)) {
					endUser = poHeader.getRequisitionerCode();
				}
				if (HiltonUtility.isEmpty(endUser)) {
					endUser = poHeader.getBuyerCode();
				}

				if (il == 0) {
					defaultForwardTo = endUser;
				}
				if (!endUser.equals(defaultForwardTo)) {
						defaultForwardTo = "";
				}
%>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=10% class=browseRow nowrap align=right><%=poLine.getLineNumber().setScale(1, BigDecimal.ROUND_HALF_UP)%>&nbsp;<tsa:hidden name="icPoLine" value="<%=poLine.getIcPoLine()%>"/></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=30% class=browseRow nowrap><%=poLine.getItemNumber()%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-asset")%> width=6% class=browseRow nowrap><input type="checkbox" name="c_asset" <% if (poLine.getAsset().equals("Y") ) {%> checked="checked" <% } %> disabled="disabled"/><tsa:hidden name="PoLine_asset" value="<%=poLine.getAsset()%>"/></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-endUser")%> width=12% class=browseRow nowrap align=left><a href="javascript: viewUserInfo('<%=endUser%>'); void(0);"><%=UserManager.getInstance().getUser(oid,endUser).getDisplayName()%></a><tsa:hidden name="endUser" value=""/></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=12% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width=12% class=browseRow nowrap align=right id="balanceQty"><%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineStatus")%> width=18% class=browseRow nowrap align=center><%=DocumentStatus.toString(poLine.getStatus())%></td>
				</tr>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td colspan=5 class=browseRow>
						<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
						<tr><td colspan=4><%=poLine.getDescription()%></td></tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> width=16% align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN01", "Line UDF 1", false)%>:</b></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> width=18%><%=poLine.getUdf1Code()%></td>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> width=15% align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN02", "Line UDF 2", false)%>:</td>
<%
		String poLine_udf2 = poLine.getUdf2Code();
		if (oid.equalsIgnoreCase("vse06p"))
		{
			poLine_udf2 = InspectionLevel.toString(poLine.getUdf2Code(), oid);
		}
%>
							<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> width=18% nowrap><%=poLine_udf2%></td>
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
					</td>
					<td colspan=2 class=browseRow nowrap>&nbsp;</td>
				</tr>
<%		}
		}%>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=50% align=center>
		<div id="forward_link"><a href="javascript: forwardReceipt(); void(0);"><img class=button src="<%=contextPath%>/images/button_forward.gif" border=0 tabIndex=20></a></div>
	</td>
	<td width=50% align=center><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 tabIndex=22></a></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<tsa:hidden name="PoLine_icPoLine" value=""/>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var defaultForwardTo = "<%=defaultForwardTo%>";
	var defaultForwardToName = "<%=UserManager.getInstance().getUser(oid, defaultForwardTo).getDisplayName()%>";

	if (!isEmpty(frm.as_forwardToName.value)) {
		frm.forwardToType[1].checked = true;
		frm.forwardToType[1].fireEvent("onclick");
	} else {
		if (isEmpty(defaultForwardTo) || defaultForwardTo == "END-USERS") {
			frm.forwardToType[0].checked = true;
			frm.forwardToType[0].fireEvent("onclick");
		} else {
			frm.forwardToType[1].checked = true;
			frm.forwardToType[1].fireEvent("onclick");
			frm.ReceiptHeader_forwardTo.value = defaultForwardTo;
			frm.as_forwardTo.value = defaultForwardTo;
			frm.as_forwardToName.value = defaultForwardToName;
		}
	}

	function setForwardToEndUsers() {
		if (frm.forwardToType.value == "E") {
			frm.as_forwardTo.value = "";
			frm.as_forwardToName.value = "";
			frm.ReceiptHeader_forwardTo.value = "END-USERS";
			hideArea("forwardToSelection");
		} else {
			displayArea("forwardToSelection");
			frm.ReceiptHeader_forwardTo.value = "";
			frm.as_forwardTo.focus();
		}
	}

	function setForwardTo() {
		if (frm.forwardToType[1].checked) {
			upperCase(frm.ReceiptHeader_forwardTo);
			frm.ReceiptHeader_forwardTo.value = frm.as_forwardTo.value;
			displayArea("forwardToSelection");
		}
		else {
			//forward to end users
			frm.as_forwardTo.value = "";
			frm.as_forwardToName.value = "";
			frm.ReceiptHeader_forwardTo.value = "END-USERS";
			hideAreaWithBlock("forwardToSelection");
		}
	}

	function validateForm() {
		if (frm.handler.value.indexOf("ReceiptCreateForward") >= 0) {
			var missingFlds = "";
			if (isEmpty(frm.ReceiptHeader_forwardTo.value)) {
				missingFlds = missingFlds + "\nFoward To";
			}
			if (!isEmpty(missingFlds)) {
				alert("The following field(s) are required:\n" + missingFlds);
				return false;
			}
		}
		return true;
	}

	function setEndUsersChecked(setChecked) {
		if (setChecked) {
			frm.forwardToType[0].checked = true;
			frm.forwardToType[0].fireEvent("onclick");
		} else {
			frm.forwardToType[1].checked = true;
			frm.forwardToType[1].fireEvent("onclick");
		}
	}

	function formatPkgQty() {
		frm.ReceiptHeader_pkgsReceived.value = nformat(eval(nfilter(frm.ReceiptHeader_pkgsReceived)), 0);
	}

	function forwardReceipt() {
		frm.receiptAction.value = "FORWARD";
		frm.failurePage.value = "/system/error_popup.jsp";
		if (doSubmitToNewTarget('receipts/rec_validate.jsp', 'ReceiptValidate', 'lookup_window')) {
			hideAreaWithBlock('forward_link');
		}
		frm.failurePage.value = "/system/error.jsp";
//		doSubmit('receipts/rec_forward.jsp', 'ReceiptCreateForward');
	}

// End Hide script -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>