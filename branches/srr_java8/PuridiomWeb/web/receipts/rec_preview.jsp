<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<%@ page import="com.tsa.puridiom.common.documents.InspectionLevel" %>
<%@ page import="com.tsa.puridiom.jasperreports.ReportUtils" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@page import="com.tsa.puridiom.common.documents.ScheduleType"%>
<%@ page import="java.math.BigDecimal" %>
<%pageContext.setAttribute("oid", oid); %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	BigDecimal b_req_ic_header = receiptHeader.getIcReqHeader();
	String	s_rec_number = receiptHeader.getReceiptNumber();
	String	s_status_code =receiptHeader.getReceiptStatus();
	String	s_rec_type = receiptHeader.getReceiptType();
	String	s_rec_status = receiptHeader.getReceiptStatus();
	String	s_rec_fiscal_year = receiptHeader.getFiscalYear();

	if (HiltonUtility.isEmpty(s_rec_number))	{	s_rec_number = "N/A";			}

	if (HiltonUtility.isEmpty(s_rec_status))
		s_rec_status = DocumentStatus.RCV_INPROGRESS;

	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	if (poHeader == null) {
		poHeader = new PoHeader();
	}
	String	s_po_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_poNumber"));
	if (HiltonUtility.isEmpty(s_po_number)) {
		s_po_number = poHeader.getPoNumber();
	}
	BigDecimal	bd_revision_number = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(0);
	Object	o_revision_number = (Object)request.getAttribute("PoHeader_revisionNumber");
	Object	o_release_number = (Object)request.getAttribute("PoHeader_releaseNumber");
	if (o_revision_number != null) {
		bd_revision_number = new BigDecimal(o_revision_number.toString());
	}
	if (o_release_number != null) {
		bd_release_number = new BigDecimal(o_release_number.toString());
	}
	if (bd_revision_number.compareTo(new BigDecimal(0)) == 0) {
		bd_revision_number = poHeader.getRevisionNumber();
	}
	if (bd_release_number.compareTo(new BigDecimal(0)) == 0) {
		bd_release_number = poHeader.getReleaseNumber();
	}

	String	receiptMethod = HiltonUtility.ckNull((String)request.getAttribute("receiptMethod"));
	String	s_current_process = "HEADER_REVIEW";
	String	s_current_page = "/receipts/rec_review.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";

	BigDecimal	bd_zero = new BigDecimal(0);

	if (HiltonUtility.isEmpty(receiptMethod) && s_rec_type.equals("A") && (s_rec_status.compareTo(DocumentStatus.RCV_PENDINGFINALIZATION) <= 0)) {
		receiptMethod = "ReceiveByPackage";
	}
	if (HiltonUtility.isEmpty(receiptMethod) && s_rec_type.equals("A") && (s_rec_status.compareTo(DocumentStatus.RCV_PENDINGFINALIZATION) > 0)) {
		receiptMethod = "FinalizeReceipt";
	}
	if (HiltonUtility.isEmpty(receiptMethod) && s_rec_type.equals("P")) {
		receiptMethod = "ReceiveByOrder";
	}
	if (HiltonUtility.isEmpty(receiptMethod) && s_rec_type.equals("O")) {
		receiptMethod = "ReceiveFromNothing";
	}

	boolean allowEdit = false;
	if ((receiptMethod.equals("ReceiveByPackage") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
		(receiptMethod.equals("FinalizeReceipt") && s_rec_status.compareTo(DocumentStatus.RCV_PENDINGFINALIZATION) == 0))
		allowEdit = true;

	List receiptLineList = (List)request.getAttribute("receiptLineList");
	int	i_linecount = 0;
	if (receiptLineList != null) {
		i_linecount = receiptLineList.size();
	}

	String recallAccess = String.valueOf(uid.equalsIgnoreCase(receiptHeader.getOwner()));
	if (user.getReceiver().equals("Y"))
	{
		recallAccess = "true";
	}

	int iBefore = 0;
	int iAfter = 0;
	int iBeforeItem = 0;
	int iAfterItem = 0;

	String	quantityDecimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	String	s_curr_code = "";
	String	tableType = "AC";
	pageContext.setAttribute("oid", oid);
	pageContext.setAttribute("language",language);
%>

<SCRIPT language='Javascript1.2' type="text/javascript">
<!--
	var oid = "<%=oid%>";
	var uid = "<%=user.getAdminBuyer()%>";
	recnumber = "<%=s_rec_number%>";
	recType = "<%=s_rec_type%>";
	recStatus = "<%=s_rec_status%>";
	recInProgress = "<%=DocumentStatus.RCV_INPROGRESS%>";
	recPendingFinalization = "<%=DocumentStatus.RCV_PENDINGFINALIZATION%>";

	recallAccess = "<%=recallAccess%>";
	receiptMethod = "<%=receiptMethod%>";

	Array91= createRecOptionsMenu(Array91[0]);

//-->
</SCRIPT>

<%@ include file="/receipts/rec_process_steps.jsp" %>

<table width="660px" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="formtype" valign="middle">
				&nbsp;&nbsp;<%=ReceiptType.toString(s_rec_type, oid)%>
				<tsa:label labelName="information" defaultString="Information" />
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" align="right" height="30px">
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="receiptNumber" defaultString="Receipt #" />:</b></tsa:td>
			<tsa:td width="100px"><%=s_rec_number%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="status" defaultString="Status" />:</b></tsa:td>
			<tsa:td width="100px"><%=DocumentStatus.toString(receiptHeader.getReceiptStatus())%></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=2 width=<%=formEntryWidth%>>
<tr>
	<td colspan=2 align=center class="error"><%=HiltonUtility.ckNull((String)request.getAttribute("errorMsg"))%></td>
</tr>
</table>
<div style="display:none;">
<%@ include file="/receipts/rec_info.jsp" %>
</div>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=<%=formEntryWidth%> align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
		<tr>
			<td width=350px align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=325px>
				<tr>
					<td align=center>
						<table border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "packageInformation", "Package Information")%></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
							<tr <%=HtmlWriter.isVisible(oid, "rec-receiptDate")%>>
								<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptDate", "Receipt Date")%>:&nbsp;</b></td>
								<td nowrap><%=HiltonUtility.getFormattedDate(receiptHeader.getReceiptDate(), oid, userDateFormat)%></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "rec-receivedBy")%>>
								<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receivedBy", "Received By")%>:&nbsp;</b></td>
								<td nowrap><%=receiptHeader.getReceivedBy()%></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "rec-receivedBy")%>>
								<td nowrap align=right>&nbsp;</td>
								<td nowrap><%=UserManager.getInstance().getUser(oid, receiptHeader.getReceivedBy()).getDisplayName()%></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "rec-inventoryLocation")%>>
								<td nowrap align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inventoryLocation", "Inventory Location")%>:&nbsp;</td>
								<td nowrap><%=receiptHeader.getItemLocation()%></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "rec-numberOfPackages")%>>
								<td nowrap align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-numberOfPackages", "# of Packages")%>:&nbsp;</td>
								<td nowrap><%=receiptHeader.getPkgsReceived().setScale(0, BigDecimal.ROUND_HALF_UP)%></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "rec-packingSlip")%>>
								<td nowrap align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-packingSlip", "Packing Slip")%>:&nbsp;</td>
								<td nowrap><%=receiptHeader.getPackingSlip()%></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "rec-carrierCode")%>>
								<td nowrap align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-carrierCode", "Carrier Code")%>:&nbsp;</td>
								<td nowrap><%=receiptHeader.getCarrierCode()%></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "rec-forwardTo")%>>
								<td nowrap align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-forwardTo", "Forward To")%>:&nbsp;</td>
								<td nowrap><%=receiptHeader.getForwardTo()%></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "rec-forwardTo")%>>
								<td nowrap align=right>&nbsp;</td>
								<td nowrap><%=UserManager.getInstance().getUser(oid, receiptHeader.getForwardTo()).getDisplayName()%></td>
							</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
<%	if (!(s_rec_type.equals("A") && receiptMethod.equals("ReceiveByPackage") && (s_rec_status.compareTo(DocumentStatus.RCV_PENDINGFINALIZATION) <= 0))) {
	String	s_ship_address_line_1 = "";
	String	s_ship_address_line_2 = "";
	String	s_ship_address_line_3 = "";
	String	s_ship_address_line_4 = "";
	String	s_ship_city = "";
	String	s_ship_state = "";
	String	s_ship_postal_code = "";
	String	s_ship_country = "";
	Address shipTo = (Address) receiptHeader.getShipToAddress();
	if (shipTo != null)
	{
		s_ship_address_line_1 = shipTo.getAddressLine1();
		s_ship_address_line_2 = shipTo.getAddressLine2();
		s_ship_address_line_3 = shipTo.getAddressLine3();
		s_ship_address_line_4 = shipTo.getAddressLine4();
		s_ship_city = shipTo.getCity();
		s_ship_state = shipTo.getState();
		s_ship_postal_code = shipTo.getPostalCode();
		s_ship_country = shipTo.getCountry();
	}
%>
			<td width=350px align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "shippingInformation", "Shipping Information")%></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="browseRow">
						<table id="shipToRows" border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
							<tr <%=HtmlWriter.isVisible(oid, "rec-shipToCode")%>><td class="browseRow" height="12px" nowrap><b><%=receiptHeader.getShipToCode()%></b></td></tr>
						<%	if (!HiltonUtility.isEmpty(s_ship_address_line_1)) { %>
							<tr <%=HtmlWriter.isVisible(oid, "rec-shp-addressLine1")%>><td class="browseRow" height="12px" nowrap><%=s_ship_address_line_1%></td></tr>
						<%	}
							if (!HiltonUtility.isEmpty(s_ship_address_line_2)) { %>
							<tr <%=HtmlWriter.isVisible(oid, "rec-shp-addressLine2")%>><td class="browseRow" height="12px" nowrap><%=s_ship_address_line_2%></td></tr>
						<%	}
							if (!HiltonUtility.isEmpty(s_ship_address_line_3)) { %>
							<tr <%=HtmlWriter.isVisible(oid, "rec-shp-addressLine3")%>><td class="browseRow" height="12px" nowrap><%=s_ship_address_line_3%></td></tr>
						<%	}
							if (!HiltonUtility.isEmpty(s_ship_address_line_4)) { %>
							<tr <%=HtmlWriter.isVisible(oid, "rec-shp-addressLine4")%>><td class="browseRow" height="12px" nowrap><%=s_ship_address_line_4%></td></tr>
						<%	} %>
							<tr><td class="browseRow" height="12px" nowrap><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></td></tr>
							<tr <%=HtmlWriter.isVisible(oid, "rec-shp-country")%>><td class="browseRow" height="12px" nowrap><%=s_ship_country%></td></tr>
						</table>
					<%	if (!HiltonUtility.isEmpty(poHeader.getShipToContact())) { %>
						<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
							<tr <%=HtmlWriter.isVisible(oid, "rec-shp-attention")%>><td class="browseRow" height="12px" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-shp-attention", "Attn")%>:&nbsp;<%=poHeader.getShipToContact()%></td></tr>
						</table>
					<%	} %>
						<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
						<%	if (!HiltonUtility.isEmpty(poHeader.getShipViaCode())) { %>
							<tr <%=HtmlWriter.isVisible(oid, "rec-shipVia")%>>
								<td class="browseRow" width=10% height="12px" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-shipVia", "Ship Via")%>:</td>
								<td class="browseRow" width=90% height="12px" nowrap><%=poHeader.getShipViaCode()%></td>
							</tr>
						<%	}
							if (!HiltonUtility.isEmpty(poHeader.getPriorityCode())) { %>
							<tsa:tr field="rec-priority">
								<tsa:td field="rec-priority" colspan="2" height="12px" noWrap="nowrap" >
								<tsa:label labelName="rec-priority" defaultString="Priority" noinstance="yes"/>:&nbsp;<%=poHeader.getPriorityCode()%>
								</tsa:td>
							</tsa:tr>
						<%	}
							if (!HiltonUtility.isEmpty(poHeader.getRouting())) { %>
							<tsa:tr field="rec-routing">
								<tsa:td field="rec-routing" colspan="2" height="12px" noWrap="nowrap" >
								<tsa:label labelName="rec-routing" defaultString="Routing" noinstance="yes"/>:&nbsp;<%=poHeader.getRouting()%>
								</tsa:td>
							</tsa:tr>
						<%	} %>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
					</td>
				</tr>
				</table>
			</td>
<%	} %>
		</tr>
		<tr>
			<td colspan="2">
				<table style="margin-left: 87px" border="0">
					<tr <%=HtmlWriter.isVisible(oid, "rec-receiptNotes")%>>
						<td nowrap align=right valign=top class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptNotes", "Notes")%>:&nbsp;</td>
						<td nowrap><textarea readonly class="browseRow" style="border: 0" style="overflow:hidden" cols="70" rows="5" >${esapi:encodeForHTML(receiptHeader.receiptNotes)}</textarea></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
<%	if (processMap.containsKey("HEADER_SUPPLIER")) {
	String	s_vend_address_line_1 = "";
	String	s_vend_address_line_2 = "";
	String	s_vend_address_line_3 = "";
	String	s_vend_address_line_4 = "";
	String	s_vend_city = "";
	String	s_vend_state = "";
	String	s_vend_postal_code = "";
	String	s_vend_country = "";
	Address vendor = (Address) receiptHeader.getVendorAddress();
	if (vendor != null) {
		s_vend_address_line_1 = vendor.getAddressLine1();
		s_vend_address_line_2 = vendor.getAddressLine2();
		s_vend_address_line_3 = vendor.getAddressLine3();
		s_vend_address_line_4 = vendor.getAddressLine4();
		s_vend_city = vendor.getCity();
		s_vend_state = vendor.getState();
		s_vend_postal_code = vendor.getPostalCode();
		s_vend_country = vendor.getCountry();
	}

	String	s_contact_displayName = "";
	String	s_contact_phoneNumber = "";
	Contact contact = (Contact) request.getAttribute("contact");
	if(contact != null) {
		s_contact_displayName = contact.getDisplayName();
		s_contact_phoneNumber = contact.getPhoneNumber();
	}
	%>
			<td width=350px align=center valign=top>
				<table id=supplierTable border=0 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "supplierInformation", "Supplier Information")%></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="browseRow" id="supplierRows">
					<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
						<tr <%=HtmlWriter.isVisible(oid, "rec-supplier")%>><td colspan="2" class="browseRow" height="12px" nowrap><b><%=receiptHeader.getVendorId()%></b></td></tr>
					<%	if (!HiltonUtility.isEmpty(s_vend_address_line_1)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine1")%>><td colspan="2" class="browseRow" height="12px" nowrap><%=s_vend_address_line_1%></td></tr>
					<%	}
						if (!HiltonUtility.isEmpty(s_vend_address_line_2)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine2")%>><td colspan="2" class="browseRow" height="12px" nowrap><%=s_vend_address_line_2%></td></tr>
					<%	}
						if (!HiltonUtility.isEmpty(s_vend_address_line_3)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine3")%>><td colspan="2" class="browseRow" height="12px" nowrap><%=s_vend_address_line_3%></td></tr>
					<%	}
						if (!HiltonUtility.isEmpty(s_vend_address_line_4)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine4")%>><td colspan="2" class="browseRow" height="12px" nowrap><%=s_vend_address_line_4%></td></tr>
					<%	}
						if (!HiltonUtility.isEmpty(s_vend_city) || !HiltonUtility.isEmpty(s_vend_state) || !HiltonUtility.isEmpty(s_vend_postal_code)) { %>
						<tr>
							<td colspan="2" class="browseRow" height="12px" nowrap>
							<%	if (!HiltonUtility.isEmpty(s_vend_city)) { %>
								<%=s_vend_city%>&nbsp;
							<%	}
								if (!HiltonUtility.isEmpty(s_vend_state)) { %>
								<%=s_vend_state%>&nbsp;
							<%	}
								if (!HiltonUtility.isEmpty(s_vend_postal_code)) { %>
								<%=s_vend_postal_code%>
							<%	} %>
							</td>
						</tr>
					<%	}
						if (!HiltonUtility.isEmpty(s_vend_country)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rec-sup-country")%>><td colspan="2" class="browseRow" height="12px" nowrap><%=s_vend_country%></td></tr>
					<%	}
						if (!HiltonUtility.isEmpty(s_contact_displayName)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rec-sup-attention")%>><td colspan="2" class="browseRow" height="12px" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-sup-attention", "Attn")%>:&nbsp;<%=s_contact_displayName%></td></tr>
					<%	}
						if (!HiltonUtility.isEmpty(s_contact_phoneNumber)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "cnt-telephoneNumber")%>><td colspan="2" class="browseRow" height="12px" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-telephoneNumber", "Phone Number")%>:&nbsp;<%=s_contact_phoneNumber%></td></tr>
					<%	} %>
					</table>
					</td>
				</tr>
				</table>
			</td>
<%	} %>
<%	if (processMap.containsKey("HEADER_BILLING")) { %>
			<td width=350px align=center valign=top>
				<table id=billingTable border=0 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "billingInformation", "Billing Information")%></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow id=billingRows>
					</td>
				</tr>
				</table>
			</td>
<%	} %>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<%	if (processMap.containsKey("SHOPCART") || s_rec_type.equals("A") || processMap.containsKey("STEP0")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center width=<%=formEntryWidth%>>
		<table id=itemTable border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%> class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%> class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
							<tr>
								<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=8% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineNumber", "Line #")%></td>
								<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=20% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-itemNumber", "Item/Part #")%></td>
							<%	if (receiptMethod.equals("ReceiveByPackage")) { %>
								<td <%=HtmlWriter.isVisible(oid, "rec-asset")%> width=15% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-asset", "Asset")%></td>
								<td <%=HtmlWriter.isVisible(oid, "rec-endUser")%> width=15% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "End User")%></td>
							<% 	} %>
								<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=8% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityOrdered", "Qty Ordered")%></td>
								<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width=8% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-balance", "Balance")%></td>
<%--							<%	if (receiptMethod.equals("FinalizeReceipt") || receiptMethod.equals("ReceiveByOrder")) { %>--%>
								<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=8% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityReceived", "Received")%></td>
								<td <%=HtmlWriter.isVisible(oid, "rec-inspected")%> width=8% class=browseHdr nowrap align="center"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inspected", "Inspected")%></td>
								<td <%=HtmlWriter.isVisible(oid, "rec-marked_tagged")%> width=8% class=browseHdr nowrap align="center"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-marked_tagged", "Marked/Tagged")%></td>
								<td <%=HtmlWriter.isVisible(oid, "rec-delivered")%> width=8% class=browseHdr nowrap align="center"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-delivered", "Delivered")%></td>
								<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=8% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityRejected", "Rejected")%></td>
								<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=8% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityAccepted", "Accepted")%></td>
							<%-- <% 	} %>--%>
								<td <%=HtmlWriter.isVisible(oid, "rec-lineStatus")%> width=10% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineStatus", "Status")%></td>
							</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow id="itemRows">
					<tr>
						<td>
							<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
<%
		String	defaultForwardTo = "";
		if (receiptLineList == null) {
			receiptLineList = new ArrayList();
		}
		for (int i=0; i<receiptLineList.size(); i++) {
			ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);
			PoLine poLine = receiptLine.getPoLine();
			BigDecimal qtyRejectedStep0 = HiltonUtility.ckNull(receiptLine.getQtyStep0Rejected());
			BigDecimal qtyRejectedStep1 = HiltonUtility.ckNull(receiptLine.getQtyStep1Rejected());
			BigDecimal qtyRejectedStep2 = HiltonUtility.ckNull(receiptLine.getQtyStep2Rejected());
			BigDecimal qtyRejectedStep3 = HiltonUtility.ckNull(receiptLine.getQtyStep3Rejected());
			BigDecimal qtyRejected = qtyRejectedStep0.add(qtyRejectedStep1).add(qtyRejectedStep2).add(qtyRejectedStep3);

			if (poLine != null) {
				BigDecimal qtyAccepted=null;
				BigDecimal balanceQty=null;
				if((receiptLine.getInspectionRequired()).equals("Y")){

					BigDecimal recvd = HiltonUtility.ckNull(receiptLine.getQtyStep0Received()).subtract(qtyRejected);
					balanceQty = (poLine.getQuantity()).subtract(recvd);
					qtyAccepted = HiltonUtility.ckNull(receiptLine.getQtyStep0Received()).subtract(qtyRejected);
				} else{
					qtyAccepted = (poLine.getQuantity()).subtract(receiptLine.getQtyRejected());
					BigDecimal recvd = receiptLine.getQtyReceived().subtract(receiptLine.getQtyRejected());
					balanceQty = (poLine.getQuantity()).subtract(recvd);
				}
				String	endUser = poLine.getRequisitionerCode();

				if (HiltonUtility.isEmpty(endUser)) {
					endUser = poHeader.getRequisitionerCode();
				}
				if (HiltonUtility.isEmpty(endUser)) {
					endUser = poHeader.getBuyerCode();
				}

				if (i == 0) {
					defaultForwardTo = endUser;
				}
				if (!endUser.equals(defaultForwardTo)) {
						defaultForwardTo = "";
				}
%>
							<tr>
								<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=8% class=browseRow nowrap align="left">
									<%=receiptLine.getReceiptLine().setScale(1, BigDecimal.ROUND_HALF_UP)%>
								</td>
								<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=20% class=browseRow nowrap align="left"><%=poLine.getItemNumber()%></td>
							<%	if (receiptMethod.equals("ReceiveByPackage")) { %>
								<td <%=HtmlWriter.isVisible(oid, "rec-asset")%> width=15% class=browseRow align="left" nowrap><input type="checkbox" name="c_asset" <% if (poLine.getAsset().equals("Y")) {%> checked="checked" <% } %> disabled="disabled"/><tsa:hidden name="PoLine_asset" value="<%=poLine.getAsset()%>"/></td>
								<td <%=HtmlWriter.isVisible(oid, "rec-endUser")%> width=15% class=browseRow nowrap align="left"><td name="<%=UserManager.getInstance().getUser(oid,endUser).getDisplayName()%>"></td><tsa:hidden name="endUser" value=""/></td>
							<%	} %>
								<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=8% class=browseRow nowrap align="left">
									<%=HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid)%>

								</td>
								<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width=8% class=browseRow nowrap align="left">
									<%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%>
								</td>
							<%--	if (receiptMethod.equals("FinalizeReceipt") || receiptMethod.equals("ReceiveByOrder")) { --%>
								<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=8% class=browseRow nowrap align="left">
								<% if((receiptLine.getInspectionRequired()).equals("Y")){ %>
									<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep0Received(), oid)%>
								<%	} else{%>
									<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyReceived(), oid)%>
								<%} %>
								</td>
								<td <%=HtmlWriter.isVisible(oid, "rec-inspected")%> width="8%"  class="browseRow" align="center" nowrap>
									<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep1Received(), oid)%>
								</td>
								<td <%=HtmlWriter.isVisible(oid, "rec-marked_tagged")%> width="8%"  class="browseRow" align="center" nowrap>
									<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep2Received(), oid)%>
								</td>
								<td <%=HtmlWriter.isVisible(oid, "rec-delivered")%> width="8%"  class="browseRow" align="center" nowrap>
									<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep3Received(), oid)%>
								</td>

								<% if((receiptLine.getInspectionRequired()).equals("Y")){ %>
								<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=8% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(qtyRejected, oid)%></td>
								<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=8% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(qtyAccepted, oid)%></td>
								<%	} else{%>
								<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=8% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyRejected(), oid)%></td>
								<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=8% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyAccepted(), oid)%></td>
								<%} %>
								<td <%=HtmlWriter.isVisible(oid, "rec-lineStatus")%> width=10% class=browseRow nowrap align=center><%=DocumentStatus.toString(receiptLine.getStatus())%></td>
							</tr>
							<tr>
								<td class=browseRow nowrap>&nbsp;</td>
<%	if (receiptMethod.equals("ReceiveByPackage")) { %>
								<td colspan=5 class=browseRow>
									<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
									<tr><td colspan=4><%=poLine.getDescription()%></td></tr>
									<tr>
										<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> width=16% align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN01", "Line UDF 1", false)%>:</b></td>
										<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> width=18%><%=poLine.getUdf1Code()%></td>
										<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> width=15% align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN02", "Line UDF 2", false)%>:</b></td>
<%
		String poLine_udf2 = poLine.getUdf2Code();
		if (oid.equalsIgnoreCase("vse06p"))
		{
			poLine_udf2 = InspectionLevel.toString(poLine.getUdf2Code(), oid);
		}
%>
										<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> width=18% nowrap><%=poLine_udf2%></td>
									</tr>
									<tr>
										<td <%=HtmlWriter.isVisible(oid, "rec-po-LN03")%> width=15% align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN03", "Line UDF 3", false)%>:</b></td>
										<td <%=HtmlWriter.isVisible(oid, "rec-po-LN03")%> width=18%><%=poLine.getUdf3Code()%></td>
										<td <%=HtmlWriter.isVisible(oid, "rec-po-LN04")%> align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN04", "Line UDF 4", false)%>:</b></td>
										<td <%=HtmlWriter.isVisible(oid, "rec-po-LN04")%>><%=poLine.getUdf4Code()%></td>
									</tr>
									<tr>
									<%if (!oid.equalsIgnoreCase("vse06p")) { %>
										<td <%=HtmlWriter.isVisible(oid, "rec-po-LN05")%> align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN05", "Line UDF 5", false)%>:</b></td>
										<td <%=HtmlWriter.isVisible(oid, "rec-po-LN05")%>><%=poLine.getUdf5Code()%></td>
									<% } %>
										<td align=right nowrap>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									</table>
								</td>
								<td colspan=2 class=browseRow nowrap>&nbsp;</td>
<%	} else if (receiptMethod.equals("FinalizeReceipt") || receiptMethod.equals("ReceiveByOrder")) { %>
								<td colspan=6 class=browseRow>
									<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
									<tr><td colspan=6><%=poLine.getDescription()%></td></tr>
									<tr>
										<td <%=HtmlWriter.isVisible(oid, "rec-LN01")%> width=18% align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN01", "Line UDF 1")%>:</b></td>
										<td <%=HtmlWriter.isVisible(oid, "rec-LN01")%> width=15%><%=receiptLine.getUdf1Code()%></td>
										<td <%=HtmlWriter.isVisible(oid, "rec-LN03")%> width=18% align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN03", "Line UDF 3")%>:</b></td>
										<td <%=HtmlWriter.isVisible(oid, "rec-LN03")%> width=15%><%=receiptLine.getUdf3Code()%></td>
									</tr>
									<tr>
										<td <%=HtmlWriter.isVisible(oid, "rec-LN02")%> width=18% align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN02", "Line UDF 2")%>:</b></td>
										<td <%=HtmlWriter.isVisible(oid, "rec-LN02")%> width=15%><%=receiptLine.getUdf2Code()%></td>
										<td <%=HtmlWriter.isVisible(oid, "rec-LN04")%> width=18% align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN04", "Line UDF 4")%>:</b></td>
										<td <%=HtmlWriter.isVisible(oid, "rec-LN04")%> width=15%><%=receiptLine.getUdf4Code()%></td>
									</tr>
									<tr>
										<td width=18% align=right nowrap>&nbsp;</td>
										<td width=15%>&nbsp;</td>
										<td width=18% align=right nowrap>&nbsp;</td>
										<td width=15%>&nbsp;</td>
									</tr>
									</table>
								</td>
								<td class=browseRow nowrap>&nbsp;</td>
<%	} %>
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
	</td>
</tr>
</table>
<%	} %>
<br>

<%	if (processMap.containsKey("HEADER_ACCOUNTS")) { %>
<table border="0" cellpadding="0" cellspacing="0" width=<%=formEntryWidth%>>
<tr>
  <td align="center" width="680px">
    <table border="0" cellspacing="0" cellpadding="0" width=<%=formEntryWidth%> class="browseHdr">
    <tr>
      <td>
        <table border="1" cellspacing="0" cellpadding="0" width=<%=formEntryWidth%> class="browseHdr">
        <tr>
          <td>
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdr">
            <tr>
              <td width="70%" height="18px" class="browseHdr">&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-account_information", "Account")%></b></td>
              <td width="15%" height="18px" class="browseHdr" align="right">&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-allocationPercent", "Percent Alloc")%>.</b></td>
              <td width="15%" height="18px" class="browseHdr" align="right">&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-allocationAmount", "Amount Alloc")%>.</b></td>
            </tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td>
<%
			List accList = (List) request.getAttribute("accountAllocAmountList");
			BigDecimal bd_total_amt = new BigDecimal(0.00);
			if (accList != null)
			{
				for (int i = 0; i < accList.size(); i++)
				{
					Object obj[] = (Object[]) accList.get(i);
					BigDecimal bd_alloc_amt = (BigDecimal) obj[0];
					String	s_account = "";
					String allocDesc = "";

					if (obj.length > 16)
					{
						allocDesc = (String) obj[16];
					}

					String udf = "";
					String fld4 = "";
					String fld5 = "";
					for (int j = 1; j < 16; j ++)
					{
						udf = (String) obj[j];
						if (!HiltonUtility.isEmpty(udf))
						{
							if (s_account.length() > 0)
							{
								s_account = s_account + s_account_separator + udf;
							}
							else
							{
								s_account = udf;
							}
						}
						if (j == 4 && !HiltonUtility.isEmpty(udf))
						{
							fld4 = udf;
						}
						if (j == 5 && !HiltonUtility.isEmpty(udf))
						{
							fld5 = udf;
						}
					}
					bd_total_amt = bd_total_amt.add(bd_alloc_amt);
%>
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
				<tr>
					<td width="75%" class="browseRow"><%=s_account%></td>
					<td width="25%" class="browseRow" align="right"><%=HiltonUtility.getCurrency(bd_alloc_amt, s_curr_code, oid, false)%></td>
				</tr>
<%				if (!HiltonUtility.isEmpty(allocDesc)) {	%>
				<tr>
					<td>
						<table border=0 cellpadding=2 cellspacing=0 width=100%>
						<tr>
							<td class=browseRow align=right width=75px><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-allocationDescription", "Description")%>:</b></td>
							<td class=browseRow><%=allocDesc%></td>
						</tr>
						</table>
					</td>
					<td>&nbsp;</td>
				</tr>
<%				}
					if (oid.equalsIgnoreCase("qri06p")) {	%>
				<tr>
					<td colspan="2">
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
<%					if ( !HiltonUtility.isEmpty(fld4)) {	%>
						<tr>
							<td width="50px">&nbsp;</td>
							<td><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC04", "UDF4")%>:&nbsp;<%=fld4%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "04", fld4)%></td>
						</tr>
<%					}
						if ( !HiltonUtility.isEmpty(fld5)) {	%>
						<tr>
							<td width="50px">&nbsp;</td>
							<td><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC05", "UDF5")%>:&nbsp;<%=fld5%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "05", fld5)%></td>
						</tr>
<%					}	%>
						</table>
					</td>
				</tr>
<%				}	%>

				</table>
<%			}
				if (accList.size() > 0) {	%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td class=browseRow>&nbsp;</td>
					<td class=browseRow colspan=2 valign=top><hr size=0 align=right colspan=2></td>
				</tr>
				<tr>
					<td width=75% class=browseRow>&nbsp;</td>
					<td width=25% class=browseRow align=right><%=HiltonUtility.getCurrency(bd_total_amt, s_curr_code, oid)%></td>
				</tr>
				</table>
<%			}
			}
			if (accList == null || accList.size() == 0) {%>
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
				<tr><td class="browseRow">There are no accounts associated with this receipt.</td></tr>
				</table>
<%		}	%>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
<%	} %>
<br>

<%	if (processMap.containsKey("HEADER_NOTES")) { %>
<div id="commentAfter" style="display: none;">
<table border="0" cellpadding="0" cellspacing="0" width="660px">
<tsa:tr>
	<tsa:td align="center" width="660px">
		<table border="0" cellspacing="0" cellpadding="0" width="650px" class="browseHdr">
		<tsa:tr>
			<tsa:td>
				<table border="1" cellspacing="0" cellpadding="0" width="650px" class="browseHdr">
				<tsa:tr>
					<tsa:td>
						<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdr">
						<tsa:tr>
							<tsa:td width="100%" colspan="2" height="18px" cssClass="browseHdr">&nbsp;<b><tsa:label labelName="req-comment" defaultString="Comment"></tsa:label></b></tsa:td>
						</tsa:tr>
						</table>
					</tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td>
<%
			List cmtList = (List) receiptHeader.getDocCommentList();
			if (cmtList != null)
			{
				for(int i = 0; i < cmtList.size(); i++)
				{
					DocComment docComment = (DocComment) cmtList.get(i);
					DocText docText = docComment.getDocText();

					String s_cmt_title = docComment.getCommentTitle();
					String s_cmt_print = docComment.getCommentPrint();
					String s_cmt_bold = docComment.getCommentBold();
					String s_cmt_place = docComment.getCommentPlace();
					String s_cmt_text = docText.getStdText();

					if (s_cmt_place.equals("B"))
					{
						continue;
					}
					if (s_cmt_print.equals("N"))
					{
						continue;
					}
					iAfter++;
%>
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
				<tsa:tr>
					<tsa:td width="100%"  colspan="2" cssClass="browseRow">
<%				if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_title%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td width="50px">&nbsp;</tsa:td>
					<tsa:td width="100%">
<%				if (s_cmt_bold.equals("Y")) 	{ %>	<b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</tsa:td>
				</tsa:tr>
				</table>
<%			}
			} %>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>
</div>
<%	} %>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="660px">
<tsa:tr>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: closeMe(); void(0);">Close</a></div></tsa:td>
</tsa:tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value="JavaScript">
<!-- Hide script

	var netscape  = "";
	var ver = navigator.appVersion;
	var len = ver.length;

	for(iln = 0; iln < len; iln++) {
		if (ver.charAt(iln) == "(") break;
	}
	netscape = (ver.charAt(iln+1).toUpperCase() != "C");

	window.onfocus = displayButtons; // displayButtons on window.onfocus

	if (netscape) window.captureEvents(window.onfocus);

<%	if (iBefore > 0) { %>
			displayArea('commentBefore');
<%	}
		if (iAfter > 0) { %>
			displayArea('commentAfter');
<%	} %>

	function printMe() {
		hideArea("buttons");

		this.print();
	}

	function closeMe() {
		window.top.hidePopWin();
	}

	function displayButtons() {
		displayArea("buttons");
	}

// End Hide script -->
</SCRIPT>