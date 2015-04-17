<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>

<%@page import="java.math.BigDecimal"%>
<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/scheduleArrays.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/recOptionArrays.js"></script>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.InspectionLevel" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<%@ page import="com.tsa.puridiom.jasperreports.ReportUtils" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	BigDecimal bd_ic_rec_header = receiptHeader.getIcRecHeader();
	BigDecimal bd_ic_po_header = receiptHeader.getIcPoHeader();
	String	s_rec_number = receiptHeader.getReceiptNumber();
	String	s_rec_type = receiptHeader.getReceiptType();
	String	s_rec_status = receiptHeader.getReceiptStatus();
	String	s_rec_fiscal_year = receiptHeader.getFiscalYear();
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

	String	s_ic_req_header = HiltonUtility.ckNull((String)request.getAttribute("RequisitionHeader_icReqHeader"));

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
	if (HiltonUtility.isEmpty(receiptMethod) && s_rec_type.equals("T")) {
		receiptMethod = "ReceiveByTransfer";
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

	String	quantityDecimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	String 	hideForwardToForAll = propertiesManager.getProperty("REC SELECTIONS", "HIDEFORWARDTOONALL", "N");
	String	s_curr_code = "";
	String	tableType = "AC";

	if(s_ic_req_header.equals("")){
		s_ic_req_header = receiptHeader.getIcReqHeader().toString();
	}

	boolean displayQtyAccepted = true;
	if (receiptHeader.getInspectionRequired().equalsIgnoreCase("Y") && receiptMethod.equalsIgnoreCase("ReceiveByOrder") &&
		(s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0 || s_rec_status.compareTo(DocumentStatus.RCV_STEP_1) == 0)
		) {
		displayQtyAccepted = false;
	}
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
	receiptMethod = "${esapi:encodeForJavaScript(receiptMethod)}";

	Array91= createRecOptionsMenu(Array91[0]);

//-->
</SCRIPT>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=s_rec_number%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="<%=s_rec_type%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=s_rec_status%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=s_rec_fiscal_year%>"/>
<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RCH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="formType" value="REC"/>
<tsa:hidden name="allowBrowse" value="true"/>

<tsa:hidden name="receiptAction" value=""/>
<tsa:hidden name="ReceiptLine_icRecLine" value=""/>
<tsa:hidden name="lineCount" value="<%=i_linecount%>"/>
<tsa:hidden name="returnPage" value="/receipts/rec_review.jsp"/>
<tsa:hidden name="returnMethod" value="ReceiptRetrieve"/>

<input type=hidden name="RequisitionLine_icReqLine" value="">
<input type=hidden name="InvProperty_icRecLine" value="">
<input type=hidden name="InvItem_itemNumber" value="">
<input type=hidden name="RequisitionHeader_icReqHeader" value="<%=s_ic_req_header%>">

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
			<%	if (receiptMethod.equals("ReceiveByPackage")) { %>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiveByPackage", "Receipt Package")%> Information</div>
			<%	} else if (receiptMethod.equals("FinalizeReceipt")) { %>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "finalizeReceipt", "Finalize Receipt")%> Information</div>
			<%	} else if (receiptMethod.equals("ReceiveByOrder")) { %>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiveByOrder", "Full Receipt From Order")%> Information</div>
			<%	} else if (receiptMethod.equals("ReceiveFromNothing")) { %>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiveFromNothing", "Full Receipt From Nothing")%> Information</div>
			<%	} else { %>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receipt", "Receipt")%> Information</div>
			<%	} %>
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
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Order #:</b>&nbsp;<%=headerEncoder.encodeForHTML(s_po_number)%>
		<%	} %>
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

<table border=0 cellspacing=0 cellpadding=2 width=<%=formEntryWidth%>>
<tr>
	<td colspan=2 align=center class="error"><%=HiltonUtility.ckNull((String)request.getAttribute("errorMsg"))%></td>
</tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" width=<%=formEntryWidth%>>
<tr>
	<td width="350px">&nbsp;</td>
	<td width="100px" align="right" nowrap>&nbsp;</td>
	<td width="200px" align="right" nowrap><a href="javascript: void(0);" onMouseOut="popDown('Menu91')" onMouseOver="popUp('Menu91',event)"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "moreOptions", "More Options")%></a></td>
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
						<% if (!hideForwardToForAll.equals("Y")) {%>
							<tr <%=HtmlWriter.isVisible(oid, "rec-forwardTo")%>>
								<td nowrap align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-forwardTo", "Forward To")%>:&nbsp;</td>
								<td nowrap><%=receiptHeader.getForwardTo()%></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "rec-forwardTo")%>>
								<td nowrap align=right>&nbsp;</td>
								<td nowrap><%=UserManager.getInstance().getUser(oid, receiptHeader.getForwardTo()).getDisplayName()%></td>
							</tr>
						<% } %>
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
							if (!HiltonUtility.isEmpty(poHeader.getUdf10Code())) { %>
							<tsa:tr field="rec-udf10">
								<tsa:td field="rec-udf10" colspan="2" height="12px" noWrap="nowrap" >
								<tsa:label labelName="rec-udf10" defaultString="UDF 10" noinstance="yes"/>:&nbsp;<%=poHeader.getUdf10Code()%>
								</tsa:td>
							</tsa:tr>
						<% }
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
			<td rowspan=3 align=right valign=top><%@ include file="/receipts/rec_sidebar.jsp" %></td>
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
							<%	if (displayQtyAccepted) { %>
								<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=8% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityAccepted", "Accepted")%></td>
							<%	} %>
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

			String s_rec_line_status = receiptLine.getStatus();

			if (poLine != null) {
				BigDecimal qtyAccepted=null;
				BigDecimal balanceQty=null;

				BigDecimal recvd = HiltonUtility.ckNull(receiptLine.getQtyStep0Received()).subtract(qtyRejected);
				balanceQty = (poLine.getQuantity()).subtract(recvd);
				qtyAccepted = HiltonUtility.ckNull(receiptLine.getQtyStep0Received()).subtract(qtyRejected);

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
							<%	if (receiptMethod.equals("ReceiveByPackage") || receiptLine.getStatus().equals(DocumentStatus.CANCELLED)) { %>
								<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=8% class=browseRow nowrap align="left">
									<%=receiptLine.getReceiptLine().setScale(1, BigDecimal.ROUND_HALF_UP)%>
								</td>
							<%	} else { %>
								<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=8% class=browseRow nowrap align="left">
									<a href="javascript: viewItem(<%=i%>, '<%=s_rec_line_status %>'); void(0);" ><%=receiptLine.getReceiptLine().setScale(1, BigDecimal.ROUND_HALF_UP)%></a>
									<input type=hidden name="icRecLine_<%=i%>"  value="<%=receiptLine.getIcRecLine()%>"/>
									<input type=hidden name="itemNumber_<%=i%>" id="itemNumber_<%=i%>" value="<%=poLine.getItemNumber()%>">
									<input type=hidden name="RequisitionLine_icReqLine_<%=i%>" value="<%=poLine.getIcReqLine().toString()%>">
								</td>
							<%	} %>
								<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=20% class=browseRow nowrap align="left"><%=poLine.getItemNumber()%></td>
							<%	if (receiptMethod.equals("ReceiveByPackage")) { %>
								<td <%=HtmlWriter.isVisible(oid, "rec-asset")%> width=15% class=browseRow align="left" nowrap><input type="checkbox" name="c_asset" <% if (poLine.getAsset().equals("Y")) {%> checked="checked" <% } %> disabled="disabled"/><tsa:hidden name="PoLine_asset" value="<%=poLine.getAsset()%>"/></td>
								<td <%=HtmlWriter.isVisible(oid, "rec-endUser")%> width=15% class=browseRow nowrap align="left"><a href="javascript: viewUserInfo('<%=endUser%>'); void(0);"><%=UserManager.getInstance().getUser(oid,endUser).getDisplayName()%></a><tsa:hidden name="endUser" value=""/></td>
							<%	} %>
								<td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=8% class=browseRow nowrap align="left">
									<%=HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid)%>

								</td>
								<td <%=HtmlWriter.isVisible(oid, "rec-balance")%> width=8% class=browseRow nowrap align="left">
									<%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%>
								</td>
							<%--	if (receiptMethod.equals("FinalizeReceipt") || receiptMethod.equals("ReceiveByOrder")) { --%>
								<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=8% class=browseRow nowrap align="left">
									<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep0Received(), oid)%>
								</td>
								<td <%=HtmlWriter.isVisible(oid, "rec-inspected")%> width="8%"  class="browseRow" align="center" nowrap>
									<%=HiltonUtility.getFormattedQuantity(HiltonUtility.ckNull(receiptLine.getQtyStep1Accepted()).add(HiltonUtility.ckNull(receiptLine.getQtyStep1Rejected())), oid)%>
								</td>
								<td <%=HtmlWriter.isVisible(oid, "rec-marked_tagged")%> width="8%"  class="browseRow" align="center" nowrap>
									<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep2Accepted(), oid)%>
								</td>
								<td <%=HtmlWriter.isVisible(oid, "rec-delivered")%> width="8%"  class="browseRow" align="center" nowrap>
									<%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep3Accepted(), oid)%>
								</td>

								<% if((receiptLine.getInspectionRequired()).equals("Y")){ %>
								<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=8% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(qtyRejected, oid)%></td>
									<%	if (displayQtyAccepted) { %>
								<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=8% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(qtyAccepted, oid)%></td>
									<%	} %>
								<%	} else{%>
								<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=8% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyRejected(), oid)%></td>
									<%	if (displayQtyAccepted) { %>
								<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=8% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyAccepted(), oid)%></td>
									<%	} %>
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
              <td width="75%" height="18px" class="browseHdr">&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-comments", "Comment")%></b></td>
              <td width="8%" height="18px" class="browseHdr" align="center" nowrap="nowrap">&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-print", "Print")%></b></td>
              <td width="7%" height="18px" class="browseHdr" align="center" nowrap="nowrap">&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bold", "Bold")%></b></td>
              <td width="10%" height="18px" class="browseHdr" align="center" nowrap="nowrap">&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-placement", "Placement")%></b></td>
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
      List cmtList = (List) receiptHeader.getDocCommentList();
      int ci = 0;
      if (cmtList != null)
      {
        for(int i = 0; i < cmtList.size(); i++)
        {
          DocComment docComment = (DocComment) cmtList.get(i);
          if (docComment == null) {
            continue;
          }
          DocText docText = docComment.getDocText();
          if (docText == null) {
            docText = new DocText();
          }
          String s_cmt_title = docComment.getCommentTitle();
          String s_cmt_print = docComment.getCommentPrint();
          String s_cmt_bold = docComment.getCommentBold();
          String s_cmt_place = docComment.getCommentPlace();
          String s_cmt_text = docText.getStdText();

          if (s_cmt_place.equals("A"))
          {
            s_cmt_place = "After";
          }
          else if (s_cmt_place.equals("B"))
          {
            s_cmt_place = "Before";
          }
          ci++;
%>
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tr>
          <td width="75%" class="browseRow"><%=s_cmt_title%></td>
          <td width="8%" class="browseRow" align="center" valign="top"><%=s_cmt_print%></td>
          <td width="7%" class="browseRow" align="center" valign="top"><%=s_cmt_bold%></td>
          <td width="10%" class="browseRow" align="center" valign="top"><%=s_cmt_place%></td>
        </tr>
        </table>

        <table class="browseRow">
        <tr>
          <td width="50px">&nbsp;</td>
          <td width="100%"><%=s_cmt_text%>	</td>
        </tr>
        </table>
<% 			}
      }
      if (ci == 0) {%>
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tr><td class="browseRow"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-nocomments", "There are no comments associated with this receipt")%>.</td></tr>
        </table>
<%		}%>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
<%	} %>
<%	if (processMap.containsKey("HEADER_ATTACHMENTS")) {%>
<br>

<table border="0" cellpadding="0" cellspacing="0" width=<%=formEntryWidth%>>
<tr>
  <td align="center" width=<%=formEntryWidth%>>
    <table border="0" cellspacing="0" cellpadding="0" width=<%=formEntryWidth%> class="browseHdr">
    <tr>
      <td>
        <table border="1" cellspacing="0" cellpadding="0" width=<%=formEntryWidth%> class="browseHdr">
        <tr>
          <td>
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdr">
            <tr>
              <td width="80%" height="18px" class="browseHdr">&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-attachment", "Attachment",s_rec_type)%></b></td>
              <td width="10%" height="18px" class="browseHdr" align="center">&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-print", "Print")%></b></td>
              <td width="10%" height="18px" class="browseHdr" align="center">&nbsp;</td>
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
    List attachmentList = (List) receiptHeader.getDocAttachmentList();
      int ai = 0;
      if (attachmentList != null) {
        for(int i = 0; i < attachmentList.size(); i++) {
          DocAttachment docAttachment = (DocAttachment) attachmentList.get(i);
          if (docAttachment == null) {
            continue;
          }
          String	filename = docAttachment.getDocFilename();
          String	ext = filename.substring(filename.lastIndexOf(".") + 1);
          ai++;
%>
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tr>
          <td width="80%" class="browseRow">
            <table border="0" cellpadding="0" cellspacing="0" width="100%" class="browseRow">
            <tr>
              <td width="25px" align="center" valign="middle">
<%		if (ext.equalsIgnoreCase("DOC")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border="0" alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border="0" alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border="0" alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border="0" alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border="0" alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border="0" alt="Open Attached Image"></a>
<%		} else {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border="0" alt="Open Attached Document"></a>
<%		}%>
              </td>
              <td>
                <a href="javascript: openDocument(<%=i%>); void(0);" title="Click here to open the <%=docAttachment.getDocTitle()%> attachment."><%=docAttachment.getDocTitle()%></a>
                <tsa:hidden name="docFilename" value="<%=filename%>"/>
              </td>
            </tr>
            </table>
          </td>
          <td width="10%" class="browseRow" align="center" valign="top"><%=docAttachment.getDocPrint()%></td>
          <td width="10%" class="browseRow" align="center" valign="top"></td>
        </tr>
        </table>

  <% 		}
      }
      if (ai == 0) {%>
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tr><td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-noattachments", "There are no attachments associated with this receipt")%>.</td></tr>
        </table>
<%		}%>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
<%	}%>

<br>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var recNumber = "<%=s_rec_number%>";
	var fiscalyear = "<%=s_rec_fiscal_year%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	<%	if (receiptMethod.equals("ReceiveByPackage")) { %>
	setNavCookie("/receipts/rec_review.jsp", "ReceiptRetrieve", "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiveByPackage", "Receipt Package")%>");
	<%	} else if (receiptMethod.equals("FinalizeReceipt")) { %>
	setNavCookie("/receipts/rec_review.jsp", "ReceiptRetrieve", "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "finalizeReceipt", "Finalize Receipt")%>");
	<%	} else if (receiptMethod.equals("ReceiveByOrder")) { %>
	setNavCookie("/receipts/rec_review.jsp", "ReceiptRetrieve", "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiveByOrder", "Full Receipt From Order")%>");
	<%	} else if (receiptMethod.equals("ReceiveFromNothing")) { %>
	setNavCookie("/receipts/rec_review.jsp", "ReceiptRetrieve", "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiveFromNothing", "Full Receipt From Nothing")%>");
	<%	} else { %>
	setNavCookie("/receipts/rec_review.jsp", "ReceiptRetrieve", "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receipt", "Receipt")%>");
	<%	} %>

	function validateRec(action)
	{
		var message = "";

		if (action == 'FORWARD')
		{
			message = "Forward";
			if (verifyAction(message + ' this receipt?')) {
				hideAreaWithBlock('forward_link');
			} else {
				return;
			}
		}
		else if (action == 'FINALIZE')
		{
			message = "Finalize";
			if (verifyAction(message + ' this receipt?')) {
				hideAreaWithBlock('forward_link');
			} else {
				return;
			}
		}

		frm.receiptAction.value = action;
		doSubmit('/receipts/rec_validate_no_popup.jsp', 'ReceiptValidationRules');
	}

	function recallRec()
	{
		if (verifyAction('Recall this Receipt?'))
		{}
	}

	function adjustRec()
	{}

	function returnRec()
	{}

	function finalizeRec()
	{
		doSubmit('receipts/rec_confirmation.jsp', 'ReceiptUpdate');
	}

	function viewHistory()
	{
		popupParameters = "HistoryLog_icHeaderHistory=<%=receiptHeader.getIcHeaderHistory()%>;formtype=RCH;ReceiptLine_icRecHeader=<%=receiptHeader.getIcRecHeader()%>;receiptMethod=${esapi:encodeForJavaScript(receiptMethod)};";
		popupParameters = popupParameters + "ReceiptHeader_receiptNumber=<%=s_rec_number%>;ReceiptHeader_receiptStatus=<%=s_rec_status%>;ReceiptHeader_receiptType=<%=s_rec_type%>;";
		popupParameters = popupParameters + "PoHeader_icPoHeader=<%=bd_ic_po_header%>;";
		popupParameters = popupParameters + "PoHeader_poNumber=<%=headerEncoder.encodeForJavaScript(s_po_number)%>;PoHeader_revisionNumber=<%=bd_revision_number%>;PoHeader_releaseNumber=<%=bd_release_number%>";
		doSubmitToPopup('/receipts/rec_history.jsp', 'HistoryLogRetrieveByHistoryHeader', 'width=675px', 'height=450px');
	}

	function printPdf()
	{
		var icHeader = "<%=receiptHeader.getIcRecHeader()%>";
		popupParameters = "ReceiptHeader_icRecHeader=" + icHeader;
		popupParameters = popupParameters + ";uid=" + '${esapi:encodeForJavaScript(userId)}';
		popupParameters = popupParameters + ";oid=" + '<%=oid%>';

		doSubmit('/receipts/rec_print_pdf.jsp', 'DoNothing');
	}

	function printLabels()
	{
		<% String sbHiddenFields = "<input type=\\\"hidden\\\" name=\\\"labels\\\" value=\\\"Y\\\"/>";%>
		setHiddenFields("<%=sbHiddenFields%>");
		doSubmit('/receipts/rec_print_pdf.jsp', 'DoNothing');
	}

	function openDocument(row) {
		var filename = "";
		if (document.all("docFilename").length > 1) {
			filename = frm.docFilename[row].value;
		}
		else {
			filename = frm.docFilename.value;
		}
		openAttachment(filename);
	}

	function viewItem(row, status) {
		var icRecLine = document.getElementById("icRecLine_" + row);
		var itemNumber = document.getElementById("itemNumber_" + row);
		var icReqLine = document.getElementById("RequisitionLine_icReqLine_" + row);

		frm.ReceiptLine_icRecLine.value = icRecLine.value;
		frm.InvItem_itemNumber.value = itemNumber.value;
		frm.RequisitionLine_icReqLine.value = icReqLine.value;
		frm.InvProperty_icRecLine.value = icRecLine.value;

		if(status == '<%=DocumentStatus.RCV_INPROGRESS%>'){
			doSubmit('/receipts/rec_item_step_0.jsp','ReceiptHeaderDataRetrieve;ReceiptLineRetrieve;PoHeaderRetrieveById');
		} else if(status == '<%=DocumentStatus.RCV_STEP_1%>'){
			doSubmit('/receipts/rec_item_step_1.jsp','ReceiptHeaderDataRetrieve;ReceiptLineRetrieve;PoHeaderRetrieveById');
		} else if(status == '<%=DocumentStatus.RCV_STEP_2%>'){
			doSubmit('/receipts/rec_item_step_2.jsp','InvPropertyRetrieveByIcRecLine;ReceiptHeaderDataRetrieve;ReceiptLineRetrieve;PoHeaderRetrieveById');
		} else if(status == '<%=DocumentStatus.RCV_STEP_3%>'){
			doSubmit('/receipts/rec_item_step_3.jsp','ReceiptHeaderDataRetrieve;ReceiptLineRetrieve;PoHeaderRetrieveById');
		}
	}

	function receiptForward() {
		if (frm.receiptMethod.value == "ReceiveByOrder" || frm.receiptMethod.value == "Return" || frm.receiptMethod.value == "ReceiveByTransfer") {
			doSubmit('receipts/rec_confirmation.jsp', 'ReceiptUpdate');
		} else if (frm.receiptMethod.value == "ReceiveFromNothing") {
			frm.receiptMethod.value = "ReceiveFromNothing";
			doSubmit('receipts/rec_confirmation.jsp', 'ReceiptCreateFromNothing');
		} else {
			doSubmit('receipts/rec_forward.jsp', 'ReceiptCreateForward');
		}
	}

// End Hide script -->
</SCRIPT>