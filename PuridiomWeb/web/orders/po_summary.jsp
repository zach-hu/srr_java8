<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.po.tasks.PoBlanketInfo" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	PoBlanketInfo blanketInfo = (PoBlanketInfo) request.getAttribute("blanketInfo");
	BigDecimal	bd_ic_po_header = poHeader.getIcPoHeader();
	BigDecimal	bd_ic_header_history = poHeader.getIcHeaderHistory();
	BigDecimal	bd_release_number = poHeader.getReleaseNumber();
	BigDecimal	bd_revision_number = poHeader.getRevisionNumber();
	BigDecimal	bd_zero = new BigDecimal(0);
	String	s_po_number = poHeader.getPoNumber();
	String	s_po_status = poHeader.getStatus();
	String	s_po_type = poHeader.getPoType();
	String	s_receipt_required = poHeader.getReceiptRequired();
	String	errorMsg = (String) request.getAttribute("errorMsg");

 	if (s_receipt_required.equalsIgnoreCase("R"))	{	s_receipt_required = "Receipt Required";		}
	if (s_receipt_required.equalsIgnoreCase("P"))	{	s_receipt_required = "Previously Received";	}
	if (s_receipt_required.equalsIgnoreCase("N"))	{	s_receipt_required = "No Receipt Required";	}
	if (s_receipt_required.equalsIgnoreCase("E"))	{	s_receipt_required = "End User Receipt";		}

	if (HiltonUtility.isEmpty(s_po_number))	{	s_po_number = "N/A";									}
	if (bd_release_number == null)		{	bd_release_number = new BigDecimal(0000);	}
	if (bd_revision_number == null)		{	bd_revision_number = new BigDecimal(0000);	}
	if (HiltonUtility.isEmpty(s_po_status))		{	s_po_status = DocumentStatus.PO_INPROGRESS;	}

	List lineList = (List) poHeader.getPoLineList();
	int	i_line_count = 0;

	if (lineList != null)
	{
		i_line_count = lineList.size();
	}
%>

<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>
<script language='Javascript1.2' src='<%=contextPath%>/scripts/po_review.js' type='text/javascript'></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/scheduleArrays.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/poOptionArrays.js"></script>
<%@ include file="/system/header.jsp" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}

	String	s_current_page = "/orders/po_review.jsp";

	String	s_buyer_code = poHeader.getBuyerCode();
	UserProfile buyer = UserManager.getInstance().getUser(oid, s_buyer_code);
	UserProfile requisitioner = UserManager.getInstance().getUser(oid, poHeader.getRequisitionerCode());

	boolean bAllowEdit = true;
	boolean bAllowSaveAs = true;
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer() && !user.getUserId().equals(s_buyer_code))
	{
		bAllowEdit = false;
	}
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer())
	{
		bAllowSaveAs = false;
	}
	boolean bDisplaySchedules = true;
	if (s_po_type.equalsIgnoreCase("CT") && oid.equalsIgnoreCase("bsc04p")) {
		bDisplaySchedules = false;
	}
%>

<script language='Javascript1.2' type="text/javascript">
<!--

	viewType = "<%=s_view%>";
	poNumber = "<%=s_po_number%>";
	poStatus = "<%=s_po_status%>";
	poType = "<%=s_po_type%>";
	poInProgress = "<%=DocumentStatus.PO_INPROGRESS%>";
	poAwarded = "<%=DocumentStatus.PO_AWARDED%>";
	poReceived = "<%=DocumentStatus.RCV_RECEIVED%>";
	poInvoiced = "<%=DocumentStatus.RCV_INVOICED%>";
	poClosed = "<%=DocumentStatus.CLOSED%>";
	poCancelled = "<%=DocumentStatus.CANCELLED%>"
	poAccess = <%=role.getAccessRights("PO")%>;
	revisionNumber = <%=bd_revision_number%>;
	viewPaymentHistory = <%=propertiesManager.getProperty("Sun", "PaymentHist", "N").equalsIgnoreCase("Y")%>;
	allowEdit = <%=bAllowEdit%>;
	allowSaveAs = <%=bAllowSaveAs%>;
	lastRevision = "<%=poHeader.getLastRevision()%>";

	Array91= createPoOptionsMenu(Array91[0]);

	Array91[0][1] = Array91[0][1] - 165;
	Array98[0][1] = Array91[0][1] - 155;

//-->
</SCRIPT>

<%@ include file="/orders/po_hidden_fields.jsp" %>
<tsa:hidden name="PoHeader_rated" value="<%=poHeader.getRated()%>"/>
<tsa:hidden name="allowBrowse" value="false"/>
<tsa:hidden name="frompage" value="summary"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=OrderType.toString(s_po_type, oid)%> <tsa:label labelName="information" defaultString="Information" /></div>
			</td>
			<td nowrap class=hdr12 valign=middle>
<%	if (propertiesManager.isModuleActive("DOCUMENTS") && role.getAccessRights("PO") > 1) {%>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><a href="javascript: viewAttachments(); void(0);"><img src="<%=contextPath%>/images/clip.gif" border=0 alt="Attachments"></a></div>
<%	}%>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
    <%@ include file="/orders/po_display_number.jsp" %>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
	<!--td align=center><a href="javascript: doSubmit('/requests/req_summary.jsp', 'RequisitionSave'); void(0);">Approvers</a></td-->
	<!--td align=center><a href="javascript: doSubmit('/requests/req_summary.jsp', 'RequisitionSave'); void(0);">Check Budget</a></td-->
<%	if (s_po_status.compareTo(DocumentStatus.PO_AWARDED) < 0 && !s_po_number.equals("N/A") && bAllowEdit ) { %>
	<td id="forward_link" align="center" width="350px" nowrap><a href="javascript: poValidate('FORWARD'); void(0);"><tsa:label labelName="forward" defaultString="Forward" /></a></td>
<%	} else {%>
	<td align="center" width="350px"></td>
<%	} %>
	<td align=center nowrap>
<%	if ( !oid.equalsIgnoreCase("qri06p") && bAllowEdit && bDisplaySchedules) {	%>
		<a href="javascript: void(0);" onMouseOut="popDown('Menu98')" onMouseOver="popUp('Menu98',event)"><tsa:label labelName="schedules" defaultString="Schedules" /></a>
<%	}	%>
	</td>
	<td align=center><a href="javascript: void(0);" onMouseOut="popDown('Menu91')" onMouseOver="popUp('Menu91',event)"><tsa:label labelName="moreOptions" defaultString="More Options" /></a></td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=50% align=center valign=top>
				<table border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=BrowseHdr>
						<tr>
							<td width=4% class=browseHdr height=18px nowrap><a href="javascript: doSubmit('/orders/po_general_info.jsp', 'PoHeaderRetrieveById'); void(0)"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
							<td width=93% class=browseHdr height=18px nowrap>&nbsp;<tsa:label labelName="general_information" defaultString="General Information" /></td>
							<td width=3% class=browseHdr height=18px nowrap><a href="javascript: toggleDetailsDisplay('generalDetails', 'Details'); void(0);"><img id='generalDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tsa:tr field="po-buyerName">
							<td nowrap align=right width=40%><b><tsa:label labelName="po-buyerName" defaultString="Buyer" />:</b>&nbsp;</td>
							<td nowrap><%=buyer.getDisplayName()%></td>
						</tsa:tr>
						<tsa:tr field="orderDate">
							<td nowrap align=right width=40%><b><tsa:label labelName="orderDate" defaultString="Order Date" />:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getPoDate(), oid, userDateFormat)%></td>
						</tsa:tr>
						<tsa:tr field="po-fiscalYear">
							<td nowrap align=right width=40%><b><tsa:label labelName="po-fiscalYear" defaultString="Fiscal Year" />:</b>&nbsp;</td>
							<td nowrap>
								<%=poHeader.getFiscalYear()%>
								<%-- input type=hidden name=PoHeader_fiscalYear value="<%=poHeader.getFiscalYear()%>"--%>
							</td>
						</tsa:tr>
						</table>
						<div id="generalDetails" style="display:none;">
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tsa:tr field="contract">
							<td nowrap align=right width=40%><b><tsa:label labelName="contract" defaultString="Contract" />:</b>&nbsp;</td>
							<td nowrap><%=poHeader.getContractNo()%></td>
						</tsa:tr>
						<tsa:tr field="effectiveDate">
							<td nowrap align=right width=40%><b><tsa:label labelName="effectiveDate" defaultString="Effective Date" />:&nbsp;</b></td>
							<td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getEffectiveDate(), oid, userDateFormat)%></td>
						</tsa:tr>
						<tsa:tr field="promisedDate">
							<td nowrap align=right width=40%><b><tsa:label labelName="promisedDate" defaultString="Promised Date" />:&nbsp;</b></td>
							<td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getPromisedDate(), oid, userDateFormat)%></td>
						</tsa:tr>
						<tsa:tr field="po-requiredDate">
							<td nowrap align=right width=40%><b><tsa:label labelName="po-requiredDate" defaultString="Required Date" />:&nbsp;</b></td>
							<td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getRequiredDate(), oid, userDateFormat)%></td>
						</tsa:tr>
<%	if (!HiltonUtility.isEmpty(poHeader.getRequisitionNumber())) { %>
						<tsa:tr field="po-requisitionNumber">
							<td nowrap align="right" width="40%"><b><tsa:label labelName="po-requisitionNumber" defaultString="Requisition Number" />:&nbsp;</b></td>
							<td nowrap><%=headerEncoder.encodeForHTML(poHeader.getRequisitionNumber())%></td>
						</tsa:tr>
<%	}
	if (!HiltonUtility.isEmpty(poHeader.getRequisitionerCode())) { %>
						<tsa:tr field="po-requisitionerName">
							<td nowrap align="right" width="40%"><b><tsa:label labelName="po-requisitionerName" defaultString="Requisitioner Name" />:&nbsp;</b></td>
							<td nowrap><%=requisitioner.getDisplayName()%></td>
						</tsa:tr>
<%	}
	if (!HiltonUtility.isEmpty(poHeader.getRfqNumber())) { %>
            <tsa:tr field="po-solicitationNumber">
              <td nowrap align="right" width="40%"><b><tsa:label labelName="po-solicitationNumber" defaultString="Solicitation Number" />:&nbsp;</b></td>
              <td nowrap><%=poHeader.getRfqNumber()%></td>
            </tsa:tr>
<%	}%>
						<tsa:tr field="po-currency">
							<td nowrap align=right valign=top><b><tsa:label labelName="po-currency" defaultString="Currency" />:</b></td>
							<td valign=top><%=poHeader.getCurrencyCode()%></td>
						</tsa:tr>
						</table>
						</div>
					</td>
				</tr>
				</table>
			</td>
<%
	String	s_ship_address_line_1 = "";
	String	s_ship_address_line_2 = "";
	String	s_ship_address_line_3 = "";
	String	s_ship_address_line_4 = "";
	String	s_ship_city = "";
	String	s_ship_state = "";
	String	s_ship_postal_code = "";
	String	s_ship_country = "";

	Address shipTo = (Address) poHeader.getShipToAddress();
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
			<td width=50% align=center valign=top>
			<table border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
						<tr>
							<td width=4% class=browseHdr height=18px nowrap><a href="javascript: doSubmit('/orders/po_shipping.jsp', 'PoHeaderShipToRetrieveById'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
							<td width=93% class=browseHdr height=18px nowrap>&nbsp;<tsa:label labelName="shipping_information" defaultString="Shipping Information" /></td>
							<td width=3% class=browseHdr height=18px nowrap><a href="javascript: toggleDetailsDisplay('shippingDetails', 'Details'); void(0);"><img id='shippingDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tsa:tr field="po-shipToCode"><td class=browseRow height=12px nowrap><b><%=poHeader.getShipToCode()%></b></td></tsa:tr>
						<tsa:tr field="po-shp-addressLine1"><td class=browseRow height=12px nowrap><%=s_ship_address_line_1%></td></tsa:tr>
						</table>

						<div id="shippingDetails" style="display:none;">
						<table id=supplierRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (!HiltonUtility.isEmpty(s_ship_address_line_2))
		{ %>
						<tsa:tr field="po-shp-addressLine2"><td class=browseRow height=12px nowrap><%=s_ship_address_line_2%></td></tsa:tr>
<%	}
		if (!HiltonUtility.isEmpty(s_ship_address_line_3))
		{ %>
						<tsa:tr field="po-shp-addressLine3"><td class=browseRow height=12px nowrap><%=s_ship_address_line_3%></td></tsa:tr>
<%	}
		if (!HiltonUtility.isEmpty(s_ship_address_line_4))
		{ %>
						<tsa:tr field="po-shp-addressLine4"><td class=browseRow height=12px nowrap><%=s_ship_address_line_4%></td></tsa:tr>
<%	} %>
						<tr><td class=browseRow height=12px nowrap><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></td></tr>
						<tsa:tr field="po-shp-country"><td class=browseRow height=12px nowrap><%=s_ship_country%></td></tsa:tr>
						</table>
						</div>
<%	if (!HiltonUtility.isEmpty(poHeader.getShipToContact())) { %>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tsa:tr field="po-shp-attention"><td class=browseRow height=12px nowrap><tsa:label labelName="po-shp-attention" defaultString="Attn" />:&nbsp<%=poHeader.getShipToContact()%></td></tsa:tr>
						</table>
<%	} %>
						<div id="shippingDetails" style="display:none;">
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (!HiltonUtility.isEmpty(poHeader.getShipViaCode())) { %>
						<tsa:tr field="po-shipVia"><td class=browseRow height=12px nowrap><tsa:label labelName="po-shipVia" defaultString="Ship Via" />:&nbsp<%=poHeader.getShipViaCode()%></td></tsa:tr>
<%	}
		if (!HiltonUtility.isEmpty(poHeader.getPriorityCode())) { %>
						<tsa:tr field="po-priority"><td class=browseRow height=12px nowrap><tsa:label labelName="po-priority" defaultString="Priority" />:&nbsp<%=poHeader.getPriorityCode()%></td></tsa:tr>
<%	}
		if (!HiltonUtility.isEmpty(poHeader.getRouting())) { %>
						<tsa:tr field="po-routing"><td class="browseRow" height="12px" nowrap><tsa:label labelName="po-routing" defaultString="Routing" />:&nbsp<%=poHeader.getRouting()%></td></tsa:tr>
<%	} %>
						</table>
						</div>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr><td colspan=2>&nbsp;</td></tr>
<%
	String	s_vend_address_line_1 = "";
	String	s_vend_address_line_2 = "";
	String	s_vend_address_line_3 = "";
	String	s_vend_address_line_4 = "";
	String	s_vend_city = "";
	String	s_vend_state = "";
	String	s_vend_postal_code = "";
	String	s_vend_country = "";

	Address vendor = (Address) poHeader.getVendorAddress();
	if (vendor != null)
	{
		s_vend_address_line_1 = vendor.getAddressLine1();
		s_vend_address_line_2 = vendor.getAddressLine2();
		s_vend_address_line_3 = vendor.getAddressLine3();
		s_vend_address_line_4 = vendor.getAddressLine4();
		s_vend_city = vendor.getCity();
		s_vend_state = vendor.getState();
		s_vend_postal_code = vendor.getPostalCode();
		s_vend_country = vendor.getCountry();
	}
%>
		<td width=50% align=center valign=top>
				<table id=supplierTable border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
						<tr>
							<td width=4% class=browseHdr height=18px nowrap><a href="javascript: doSubmit('/orders/po_supplier.jsp','PoHeaderVendorRetrieveById'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
							<td width=93% class=browseHdr height=12px nowrap>&nbsp;<tsa:label labelName="supplier_information" defaultString="Supplier Information" /></td>
							<td width=3% class=browseHdr height=12px nowrap><a href="javascript: toggleDetailsDisplay('supplierDetails', 'Details'); void(0);"><img id='supplierDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;</td>
						</tr>
						</table>
				</tr>
				<tr>
					<td class=browseRow id=supplierRows>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tsa:tr field="po-supplier"><td class=browseRow height=12px nowrap><b><%=poHeader.getVendorId()%></b></td></tsa:tr>
						<tsa:tr field="po-sup-addressLine1"><td class=browseRow height=12px nowrap><%=s_vend_address_line_1%></td></tsa:tr>
						</table>
						<div id="supplierDetails" style="display:none;">
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (!HiltonUtility.isEmpty(s_vend_address_line_2))
		{ %>
						<tsa:tr field="po-sup-addressLine2"><td class=browseRow height=12px nowrap><%=s_vend_address_line_2%></td></tsa:tr>
<%	}
		if (!HiltonUtility.isEmpty(s_vend_address_line_3))
		{ %>
						<tsa:tr field="po-sup-addressLine3"><td class=browseRow height=12px nowrap><%=s_vend_address_line_3%></td></tsa:tr>
<%	}
		if (!HiltonUtility.isEmpty(s_vend_address_line_4))
		{ %>
						<tsa:tr field="po-sup-addressLine4"><td class=browseRow height=12px nowrap><%=s_vend_address_line_4%></td></tsa:tr>
<%	}
		if (!HiltonUtility.isEmpty(s_vend_city) || !HiltonUtility.isEmpty(s_vend_state) || !HiltonUtility.isEmpty(s_vend_postal_code)) { %>
						<tr>
							<td class=browseRow height=12px nowrap>
<%		if (!HiltonUtility.isEmpty(s_vend_city)) { %>
								<%=s_vend_city%>&nbsp;
<%		}
			if (!HiltonUtility.isEmpty(s_vend_state)) { %>
								<%=s_vend_state%>&nbsp;
<%		}
			if (!HiltonUtility.isEmpty(s_vend_postal_code)) { %>
								<%=s_vend_postal_code%>
<%		} %>
							</td>
						</tr>
<%	} %>
<%	if (!HiltonUtility.isEmpty(s_vend_country)) { %>
						<tsa:tr field="po-sup-country"><td class=browseRow height=12px nowrap><%=s_vend_country%></td></tsa:tr>
<%	} %>
						</table>
						</div>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (!HiltonUtility.isEmpty(poHeader.getContactName()))
		{ %>
						<tsa:tr field="po-sup-attention"><td class=browseRow height=12px nowrap><tsa:label labelName="po-sup-attention" defaultString="Attn" />:&nbsp;<%=poHeader.getContactName()%></td></tsa:tr>
<%	} %>
<%	if (!HiltonUtility.isEmpty(poHeader.getContactPhone()))
		{ %>
						<tsa:tr field="po-sup-phone"><td class=browseRow height=12px nowrap><tsa:label labelName="po-sup-phone" defaultString="Phone" />:&nbsp;<%=poHeader.getContactPhone()%></td></tsa:tr>
<%	} %>
						</table>
					</td>
				</tr>
				</table>
			</td>
<%
	String	s_bill_address_line_1 = "";
	String	s_bill_address_line_2 = "";
	String	s_bill_address_line_3 = "";
	String	s_bill_address_line_4 = "";
	String	s_bill_city = "";
	String	s_bill_state = "";
	String	s_bill_postal_code = "";
	String	s_bill_country = "";

	Address billTo = (Address) poHeader.getBillToAddress();
	if (billTo != null)
	{
		s_bill_address_line_1 = billTo.getAddressLine1();
		s_bill_address_line_2 = billTo.getAddressLine2();
		s_bill_address_line_3 = billTo.getAddressLine3();
		s_bill_address_line_4 = billTo.getAddressLine4();
		s_bill_city = billTo.getCity();
		s_bill_state = billTo.getState();
		s_bill_postal_code = billTo.getPostalCode();
		s_bill_country = billTo.getCountry();
	}
%>
			<td width=50% align=center valign=top>
				<table id=billingTable border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
							<td width=4% class=browseHdr height=18px nowrap><a href="javascript: doSubmit('/orders/po_billing.jsp', 'PoHeaderBillToRetrieveById'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
							<td width=93% class=browseHdr height=12px nowrap>&nbsp;<tsa:label labelName="billing_information" defaultString="Billing Information" /></td>
							<td width=3% class=browseHdr height=12px nowrap><a href="javascript: toggleDetailsDisplay('billingDetails', 'Details'); void(0);"><img id='billingDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;</td>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow id=billingRows>
						<table border=0 cellspacing=0 cellpadding=2 width=210px class=browseRow>
						<tsa:tr field="po-billToCode"><td class=browseRow height=12px nowrap><b><%=poHeader.getBillToCode()%></b></td></tsa:tr>
						<tsa:tr field="po-bil-addressLine1"><td class=browseRow height=12px nowrap><%=s_bill_address_line_1%></td></tsa:tr>
						</table>
						<div id="billingDetails" style="display:none;">
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (!HiltonUtility.isEmpty(s_bill_address_line_2))
		{ %>
						<tsa:tr field="po-bil-addressLine2"><td class=browseRow height=12px nowrap><%=s_bill_address_line_2%></td></tsa:tr>
<%	}
		if (!HiltonUtility.isEmpty(s_bill_address_line_3))
		{ %>
						<tsa:tr field="po-bil-addressLine3"><td class=browseRow height=12px nowrap><%=s_bill_address_line_3%></td></tsa:tr>
<%	}
		if (!HiltonUtility.isEmpty(s_bill_address_line_4))
		{ %>
						<tsa:tr field="po-bil-addressLine4"><td class=browseRow height=12px nowrap><%=s_bill_address_line_4%></td></tsa:tr>
<%	} %>
						<tr><td class=browseRow height=12px nowrap><%=s_bill_city%>&nbsp;<%=s_bill_state%>&nbsp;<%=s_bill_postal_code%></td></tr>
						<tsa:tr field="po-bil-country"><td class=browseRow height=12px nowrap><%=s_bill_country%></td></tsa:tr>
						</table>
						</div>
<%	if (!HiltonUtility.isEmpty(poHeader.getBillToContact()))
		{ %>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tsa:tr field="po-bil-attention"><td class=browseRow height=12px nowrap><tsa:label labelName="po-bil-attention" defaultString="Attn" />:&nbsp<%=poHeader.getBillToContact()%></td></tsa:tr>
						</table>
<%	} %>
						<div id="billingDetails" style="display:none;">
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tsa:tr field="po-paymentTerms"><td class=browseRow height=12px nowrap><tsa:label labelName="po-paymentTerms" defaultString="Terms" />:&nbsp<%=poHeader.getTermsCode()%></td></tsa:tr>
						<tsa:tr field="po-fob"><td class=browseRow height=12px nowrap><tsa:label labelName="po-fob" defaultString="FOB" />:&nbsp;<%=poHeader.getFobCode()%></td></tsa:tr>
						</table>
						</div>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr><td colspan=2>&nbsp;</td></tr>
		<tr>
			<td width=50% align=center valign=top>
<%	if (s_po_type.equals("BO") || s_po_type.equals("RO") || s_po_type.equals("DO") || s_po_type.equals("DR") || s_po_type.equals("SB") || s_po_type.equals("SR")) { %>
				<table border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
							<td width=4% class=browseHdr height=18px nowrap><a href="javascript: doSubmit('/orders/po_blanket.jsp', 'PoGetBlanketInfo'); void(0)"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
							<td width=93% class=browseHdr height=18px nowrap>&nbsp;Blanket Information</td>
							<td width=3% class=browseHdr height=12px nowrap><a href="javascript: toggleDetailsDisplay('blanketDetails', 'Details'); void(0);"><img id='blanketDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;</td>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tsa:tr field="effectiveDate">
							<td nowrap align="right" width="50%"><b><tsa:label labelName="effectiveDate" defaultString="Effective Date" />:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getFormattedDate(blanketInfo.getPoEffective(), oid, userDateFormat)%></td>
						</tsa:tr>
						<tsa:tr field="expirationDate">
							<td nowrap align="right" width="50%"><b><tsa:label labelName="expirationDate" defaultString="Expiration Date" />:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getFormattedDate(blanketInfo.getPoExpires(), oid, userDateFormat)%></td>
						</tsa:tr>
						<tsa:tr field="releaseLimit">
							<td nowrap align="right" width="50%"><b><tsa:label labelName="releaseLimit" defaultString="Release Limit" />:</b>&nbsp;</td>
							<td nowrap>$<%=HiltonUtility.getFormattedDollar(blanketInfo.getPoReleaseLimit(), oid)%></td>
						</tsa:tr>
						<tsa:tr field="blanketLimit">
							<td nowrap align="right" width="50%"><b><tsa:label labelName="blanketLimit" defaultString="Blanket Limit" />:</b>&nbsp;</td>
							<td nowrap>$<%=HiltonUtility.getFormattedDollar(blanketInfo.getPoBlanket(), oid)%></td>
						</tsa:tr>
						</table>
						<div id="blanketDetails" style="display:none;">
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tsa:tr field="numberOfReleases">
							<td nowrap align=right width=50%><b><tsa:label labelName="numberOfReleases" defaultString="Number of Releases" />:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getBigDecimalFormatted(blanketInfo.getReleaseCount(), 0)%></td>
						</tsa:tr>
						<tsa:tr field="releaseTotal">
							<td nowrap align=right width=50%><b><tsa:label labelName="releaseTotal" defaultString="Release Total" />:</b>&nbsp;</td>
							<td nowrap>$<%=HiltonUtility.getFormattedDollar(blanketInfo.getReleaseTotal(), oid)%></td>
						</tsa:tr>
						<tsa:tr field="availableBlanket">
							<td nowrap align=right width=50%><b><tsa:label labelName="availableBlanket" defaultString="Available Blanket" />:</b>&nbsp;</td>
							<td nowrap>$<%=HiltonUtility.getFormattedDollar(blanketInfo.getAvailableBlanket(), oid)%></td>
						</tsa:tr>
						</table>
						</div>
					</td>
				</tr>
				</table>
<%	} %>
			</td>
			<td width=50% align=center valign=top>

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
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height=18px class=browseHdr><a href="javascript: doSubmit('/orders/po_accounts.jsp', 'AccountRetrieveByLine'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a></td>
					<td width=68% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="account" defaultString="Account" /></b></td>
					<td width=15% height=18px class=browseHdr align=right>&nbsp;<b><tsa:label labelName="percent-alloc" defaultString="Percent Alloc." /></b></td>
					<td width=15% height=18px class=browseHdr align=right>&nbsp;<b><tsa:label labelName="amount-alloc" defaultString="Amount Alloc." /></b></td>
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
					String udf = "";
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
					}
					bd_total_amt = bd_total_amt.add(bd_alloc_amt);
%>
        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
        <tr>
          <td width=75% class=browseRow><%=s_account%></td>
          <td width=25% class=browseRow align=right><%=HiltonUtility.getFormattedDollar(bd_alloc_amt, oid)%></td>
        </tr>
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
					<td width=25% class=browseRow align=right><%=HiltonUtility.getFormattedDollar(bd_total_amt, oid)%></td>
				</tr>
				</table>
<%			}
			}
			if (accList == null || accList.size() == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow><tsa:label labelName="noAccountsAssociatedMessage" defaultString="There are no accounts associated with this requisition." /></td></tr>
				</table>
<%		}	%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<% List cmtList = (List) poHeader.getDocCommentList(); %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height=18px class=browseHdr><a href="javascript: doSubmit('/orders/po_notes.jsp', 'DocCommentRetrieveByLine'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a></td>
					<td width=72% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="comment" defaultString="Comment" /></b></td>
					<td width=7% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="print" defaultString="Print" /></b></td>
					<td width=7% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="bold" defaultString="Bold" /></b></td>
					<td width=10% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="placement" defaultString="Placement" /></b></td>
					<%	if (cmtList != null && cmtList.size() > 1) {%>
					<td width=2% height=18px class=browseHdr nowrap><a href="javascript: toggleDetailsDisplay('commentDetails', 'Details'); void(0);"><img id='commentDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Notes"></a></td>
					<%	}%>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
int size = 0;
	if (cmtList != null)
	{
		size = cmtList.size();
		for(int i = 0; i < size; i++)
		{
			DocComment docComment = (DocComment) cmtList.get(i);
			DocText docText = docComment.getDocText();
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
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=74% class=browseRow>&nbsp;<%=s_cmt_title%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_cmt_print%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_cmt_bold%></td>
					<td width=10% class=browseRow align=center valign=top><%=s_cmt_place%></td>
					<td width=2% class=browseRow align=center valign=top>&nbsp;</td>
				</tr>
				</table>
				<div id="commentDetails" style="display:none;">
				<table>
				<tr>
					<td width=75px>&nbsp;</td>
					<td width=100%><%=s_cmt_text%></td>
				</tr>
				</table>
				</div>
<% 	}
	}
	if (size == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow><tsa:label labelName="noCommentsAssociatedMessage" defaultString="There are no comments associated with this requisition." /></td></tr>
				</table>
<%}%>
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
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td width=2% class=browseHdr height=18px nowrap align=center><a href="javascript: doSubmit('/orders/po_items.jsp', 'PoHeaderUpdate;PoLineRetrieveByHeader'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit Items"></a></td>
					<tsa:td field="po-itemNumber" width="13%" cssClass="browseHdr" height="18px" noWrap="true" align="center"><tsa:label labelName="po-hdg-itemNumber" defaultString="Item/Part #" /></tsa:td>
					<tsa:td field="po-catalog" width="10%" cssClass="browseHdr" noWrap="true" align="center"><tsa:label labelName="po-hdg-catalog" defaultString="Catalog" /></tsa:td>
					<tsa:td field="po-commodity" width="19%" cssClass="browseHdr" height="18px" noWrap="true" align="center"><tsa:label labelName="po-hdg-commodity" defaultString="Commodity" /></tsa:td>
					<tsa:td field="po-quantity" width="10%" cssClass="browseHdr" height="18px" noWrap="true" align="center"><tsa:label labelName="po-hdg-quantity" defaultString="Quantity" /></tsa:td>
					<tsa:td field="po-uom" width="6%" cssClass="browseHdr" height="18px" noWrap="true" align="center"><tsa:label labelName="po-hdg-uom" defaultString="UOM" /></tsa:td>
					<tsa:td field="po-unitCost" width="10%" cssClass="browseHdr" height="18px" noWrap="true" align="center"><tsa:label labelName="po-hdg-unitCost" defaultString="Unit Cost" /></tsa:td>
					<tsa:td field="po-extendedCost" width="13%" cssClass="browseHdr" height="18px" noWrap="true" align="center"><tsa:label labelName="po-hdg-extendedCost" defaultString="Ext Cost" /></tsa:td>
					<tsa:td field="po-lineStatus" width="15%" cssClass="browseHdr" height="18px" noWrap="true" align="center"><tsa:label labelName="po-hdg-lineStatus" defaultString="Line Status" /></tsa:td>
					<%	if (i_line_count > 1) { %>
					<td width=2% class=browseHdr height=18px nowrap><a href="javascript: toggleDetailsDisplay('itemDetails', 'Items'); void(0);"><img id='itemDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Items"></a></td>
					<%	} %>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
			<tsa:hidden name="PoLine_icPoLine" value=""/>
				<table id=itemRow border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
				<tr>
					<td>
<%
		for (int i = 0; i < i_line_count; i++)
		{
			PoLine poLine = (PoLine) lineList.get(i);
			if (i == 1)
			{
%>
						<div id="itemDetails" style="display:none;">
<%
			}
			BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid);
			BigDecimal bd_unit_price = HiltonUtility.getFormattedPrice(poLine.getUnitPrice(), oid);
	        BigDecimal b_um_factor = HiltonUtility.getFormattedDollar(poLine.getUmFactor(), oid);
			BigDecimal bd_extended_cost = HiltonUtility.getFormattedDollar(bd_quantity.multiply(bd_unit_price).multiply(b_um_factor), oid);
%>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
						<tr>
							<td width=2% class=browseRow nowrap align=center><a href="javascript: viewItem(<%=i%>); void(0);"><%=i+1%></a></td>
							<td <%=HtmlWriter.isVisible(oid, "po-itemNumber")%> width=13% class=browseRow nowrap align=center>
								<%=poLine.getItemNumber()%>
								<tsa:hidden id="icPoLine_<%=i%>" name="icPoLine_<%=i%>" value="<%=poLine.getIcPoLine()%>"/>
							</td>
							<tsa:td field="po-catalog" width="10%" cssClass="browseRow" noWrap="true"><%=poLine.getCatalogId()%></tsa:td>
							<tsa:td field="po-commodity" width="18%" cssClass="browseRow" noWrap="true" align="center"><%=poLine.getCommodity()%></tsa:td>
							<tsa:td field="po-quantity" width="10%" cssClass="browseRow" noWrap="true" align="center"><%=bd_quantity%></tsa:td>
							<tsa:td field="po-uom" width="6%" cssClass="browseRow" noWrap="true" align="center"><%=poLine.getUmCode()%></tsa:td>
							<tsa:td field="po-unitCost" width="10%" cssClass="browseRow" noWrap="true" align="center"><%=bd_unit_price%></tsa:td>
							<tsa:td field="po-extendedCost" width="13%" cssClass="browseRow" noWrap="true" align="center"><%=bd_extended_cost%></tsa:td>
							<tsa:td field="po-lineStatus" width="15%" cssClass="browseRow" noWrap="true" align="center"><%=DocumentStatus.toString(poLine.getStatus())%></tsa:td>
							<td width=2% class=browseRow>&nbsp;</td>
						</tr>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=8 class=browseRow><%=poLine.getDescription()%></td>
						</tr>
<%
		List accountList = (List) poLine.getAccountList();

		if (accountList != null)
		{
			BigDecimal bd_total_perc = new BigDecimal(0.00);
			BigDecimal bd_total_amount = new BigDecimal(0.00);

			for (int ix = 0; ix < accountList.size(); ix++)
			{
				Account account = (Account) accountList.get(ix);

				BigDecimal bd_alloc_perc = account.getAllocPercent();
				BigDecimal bd_alloc_amt = account.getAllocAmount();

				if (bd_alloc_perc == null)	{	bd_alloc_perc = new BigDecimal(0);	}
				if (bd_alloc_amt == null)	{	bd_alloc_amt = new BigDecimal(0);	}

				bd_total_perc = bd_total_perc.add(bd_alloc_perc);
				bd_total_amount = bd_total_amount.add(bd_alloc_amt);

				String	s_account = "";
				String	s_accArray[] = new String[15];

				s_accArray[0] = account.getFld1();
				s_accArray[1] = account.getFld2();
				s_accArray[2] = account.getFld3();
				s_accArray[3] = account.getFld4();
				s_accArray[4] = account.getFld5();
				s_accArray[5] = account.getFld6();
				s_accArray[6] = account.getFld7();
				s_accArray[7] = account.getFld8();
				s_accArray[8] = account.getFld9();
				s_accArray[9] = account.getFld10();
				s_accArray[10] = account.getFld11();
				s_accArray[11] = account.getFld12();
				s_accArray[12] = account.getFld13();
				s_accArray[13] = account.getFld14();
				s_accArray[14] = account.getFld15();

				for (int j = 0; j <15; j++)
				{

					if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
					{
						if (s_account.length() > 0)
						{
							s_account = s_account + s_account_separator + s_accArray[j];
						}
						else
						{
							s_account = s_accArray[j];
						}
					}

				}
%>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td colspan=7 class=browseRow><b><tsa:label labelName="account" defaultString="Account" />:</b>&nbsp;<%=s_account%>&nbsp;&nbsp;&nbsp;$<%=HiltonUtility.getFormattedDollar(bd_alloc_amt, oid)%></td>
				</tr>
<%			}
		}  %>

<%		if (!(poLine.getRequisitionNumber().equalsIgnoreCase(poHeader.getRequisitionNumber())) || (!poLine.getRequisitionerCode().equals(poHeader.getRequisitionerCode()) && !HiltonUtility.isEmpty(poLine.getRequisitionerCode())))
		{	%>
            <tr>
              <td class=browseRow nowrap>&nbsp;</td>
<%			if (!poLine.getRequisitionNumber().equals(poHeader.getRequisitionNumber())) {%>
              <tsa:td field="ln-po-requisitionNumber" colspan="4" cssClass="browseRow"><b><tsa:label labelName="ln-po-requisitionNumber" defaultString="Requisition #" />:</b>&nbsp;<%=headerEncoder.encodeForHTML(poLine.getRequisitionNumber())%></tsa:td>
<%			}
			if (!poLine.getRequisitionerCode().equals(poHeader.getRequisitionerCode()) && !HiltonUtility.isEmpty(poLine.getRequisitionerCode())) {%>
              <tsa:td field="ln-po-requisitionerName" colspan="4" cssClass="browseRow"><b><tsa:label labelName="ln-po-requisitionerName" defaultString="Requisitioner Name" />:</b>&nbsp;<%=UserManager.getInstance().getUser(oid, poLine.getRequisitionerCode()).getDisplayName()%></tsa:td>
<%			}%>
            </tr>
<%		}%>
						</table>
<%		}
			if (i_line_count > 1) { %>
						</div>
<%		} %>
					</td>
				</tr>
				</table>

				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow width=100%><hr size=0></td></tr>
				</table>

				<div id="totals" style="display:none;">
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tsa:tr field="po-subtotal">
							<td width="65%" class="browseRow" nowrap align="right">&nbsp;<tsa:label labelName="po-subtotal" defaultString="Subtotal" />:</td>
							<td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getSubtotal(), oid)%></td>
							<td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
						</tsa:tr>
						<tsa:tr field="po-discountAmount">
							<td width="65%" class="browseRow" nowrap align="right">&nbsp;<tsa:label labelName="po-discountAmount" defaultString="Discount" />:</td>
							<td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getDiscountAmount(), oid)%></td>
							<td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
						</tsa:tr>
						<tsa:tr field="po-taxAmount">
							<td width="65%" class="browseRow" nowrap align="right">&nbsp;<tsa:label labelName="po-taxAmount" defaultString="Tax" />:</td>
							<td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getTaxAmount(), oid)%></td>
							<td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
						</tsa:tr>
						<tsa:tr field="po-shippingCharges">
							<td width="65%" class="browseRow" nowrap align="right">&nbsp;<tsa:label labelName="po-shippingCharges" defaultString="Shipping" />:</td>
							<td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getShippingCharges(), oid)%></td>
							<td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
						</tsa:tr>
						<tsa:tr field="po-shippingTaxAmount">
							<td width="65%" class="browseRow" nowrap align="right">&nbsp;<tsa:label labelName="po-shippingTaxAmount" defaultString="Shipping Tax" />:</td>
							<td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getShippingTax(), oid)%></td>
							<td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
						</tsa:tr>
						<tsa:tr field="po-otherCharges">
							<td width="65%" class="browseRow" nowrap align="right">&nbsp;<tsa:label labelName="po-otherCharges" defaultString="Other" />:</td>
							<td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getOtherCharges(), oid)%></td>
							<td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
						</tsa:tr>
						<tsa:tr field="po-otherTaxAmount">
							<td width="65%" class="browseRow" nowrap align="right">&nbsp;<tsa:label labelName="po-otherTaxAmount" defaultString="Other Tax" />:</td>
							<td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getOtherTax(), oid)%></td>
							<td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
						</tsa:tr>
						</table>
					</td>
				</tr>
				</table>
				</div>

				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td width=2% class=browseRow nowrap align=right><a href="javascript: doSubmit('/orders/po_totals.jsp', 'PoHeaderRetrieveById'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseRow border=0 alt="Edit Totals"></a></td>
							<tsa:td field="po-total" width="60%" cssClass="browseRow" noWrap="true" align="right"><b><tsa:label labelName="po-total" defaultString="Total" />:</b></tsa:td>
							<tsa:td field="po-total" width="10%" cssClass="browseRow" noWrap="true" align="right"><b>$<%=HiltonUtility.getFormattedDollar(poHeader.getTotal(), oid)%></b></tsa:td>
							<td width=25% class=browseRow nowrap align=right>&nbsp;</td>
							<td width=3% class=browseRow nowrap align=right><a href="javascript: toggleDetailsDisplay('totals', 'Totals'); void(0);"><img id='totalsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Totals"></a>&nbsp;
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

<%	if (propertiesManager.isModuleActive("DOCUMENTS")) {%>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height 18px class=browseHdr><a href="javascript: doSubmit('/orders/po_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
					<td width=78% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="attachment" defaultString="Attachment" /></b></td>
					<td width=10% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="print" defaultString="Print" /></b></td>
					<td width=10% height=18px class=browseHdr align=center>&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
		List atcList = (List) poHeader.getDocAttachmentList();
		int	ai = 0;
		if (atcList != null)
		{
			for(int i = 0; i < atcList.size(); i++)
			{
				DocAttachment docAttachment = (DocAttachment) atcList.get(i);
				if (docAttachment == null) {
					continue;
				}
				String	filename = docAttachment.getDocFilename();
				String	ext = filename.substring(filename.lastIndexOf(".") + 1);
				ai++;
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=80% class=browseRow>
						<table border=0 cellpadding=0 cellspacing=0 width=100% class=browseRow>
						<tr>
							<td width=25px>
<%		if (ext.equalsIgnoreCase("DOC")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
								</td>
								<td>
									<a href="javascript: openDocument(<%=i%>); void(0);"><%=docAttachment.getDocTitle()%></a>
									<tsa:hidden name="docFilename" value="<%=filename%>"/>
								</td>
							</tr>
							</table>
						</td>
						<td width=10% class=browseRow align=center valign=top><%=docAttachment.getDocPrint()%></td>
						<td width=10% class=browseRow align=center valign=top></td>
					</tr>
					</table>
<%		}
		}
		if (ai == 0) {%>
					<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
					<tr><td class=browseRow><tsa:label labelName="noAttachmentsAssociatedMessage" defaultString="There are no attachments associated with this order." /></td></tr>
					</table>
<%	}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	}%>
<br>

<%	if (s_po_status.compareTo(DocumentStatus.PO_AWARDED) < 0) {%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%		if (role.getAccessRights("PO") > 1 && bAllowEdit) { %>
	<td width=50% align=center>
<%			if (s_po_number == null || s_po_number.equals("N/A")) { %>
		<a href="javascript: orderSave(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a>
<%			} else { %>
		<div id="forward_link" style="visibility: visible;"><a href="javascript: poValidate('FORWARD'); void(0);"><img class=button src="<%=contextPath%>/images/button_forward.gif" border=0 alt="Forward"></a></div>
<%			} %>
	</td>
	<td width=50% align=center><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Main Menu"></a></td>
<%		} else { %>
	<td width=100% align=center><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Main Menu"></a></td>
<%		} %>
</tr>
</table>
<%	} %>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

/***** PLEASE ADD ANY JS FUNCTIONS THAT APPLY TO BOTH po_review.jsp and po_summary.jsp TO /scripts/po_review.js *****/

	frm = document.purchaseform;

	var ponumber = "<%=s_po_number%>";
	var fiscalyear = "<%=poHeader.getFiscalYear()%>";
	var itemLocation = "<%=poHeader.getItemLocation()%>";

<%	if (role.getAccessRights("PO") < 2 || s_po_admin.equalsIgnoreCase("Y") && s_po_status.compareTo(DocumentStatus.PO_APPROVING) >= 0) { %>
			hideForwardButton();
<%	} %>

	//setNavCookie("/orders/po_review.jsp", "PoRetrieve", "<%=OrderType.toString(s_po_type, oid)%>");

<%	if (!HiltonUtility.isEmpty(errorMsg)) {%>
	alert("<%= headerEncoder.encodeForJavaScript(errorMsg) %>");
<%	}%>

	function toggleDetailsDisplay(areaName, type) {
		var myAreas = document.getElementsByName(areaName);
		var myArea;
		var myImg = document.getElementById(areaName + "Img");
		var hideArea = false;

		for (var i = 0; i < myAreas.length; i++) {
			myArea = myAreas(i);


			if (myArea.style.display == "none") {
				myArea.style.display = "block";
			}
			else {
				hideArea = true;
				myArea.style.display = "none";
			}
		}

		if (type == undefined) {
			type = "Details";
		}

		if (hideArea) {
			myImg.src = "<%=contextPath%>/images/arrows_down.gif";
			myImg.alt = "View" + type;
		}
		else {
			myImg.src = "<%=contextPath%>/images/arrows_up.gif";
			myImg.alt = "Hide " + type;
		}
	}

	function orderForward()
	{
		var po_type = "<%=s_po_type%>";
		var line_count = frm.lineCount.value;

		if (line_count > 0 || po_type == "BO" || po_type == "CT")
		{
			//goforward is used to check if any "errors" occured during the forward process.
			//errors where user input is required--- RR 01/12/05
			/*  this may need to change once receipts are done*/
			//frm.goforward.value = "Y";
<%		if (propertiesManager.getProperty("MODULES", "PO APPROVALS", "N").equalsIgnoreCase("Y") &&
		(!poHeader.getPoType().equals("CT") || propertiesManager.getProperty("PO APPROVALS", "CONTRACTAPPROVALS", "Y").equalsIgnoreCase("Y"))) { %>
				doSubmitToPopup('/orders/po_forward_options.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
<%		} else { %>
				frm.poForwardOption.value = "F";	/*  award Order*/
				doSubmit('/orders/po_forward_options.jsp', 'PoForward', 'WIDTH=350', 'HEIGHT=165');
<%		} %>
		}
		else
		{
			alert("You must add items before forwarding an Order!");
		}
	}

	function switchView()
	{
		frm.viewType.value = "WIZARD";
		doSubmit('/orders/po_review.jsp', 'PoRetrieve');
	}

// End Hide script -->
</SCRIPT>
