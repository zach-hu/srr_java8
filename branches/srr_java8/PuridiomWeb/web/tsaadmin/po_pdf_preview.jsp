<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.po.tasks.PoBlanketInfo" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.math.BigDecimal" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

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

	if (HiltonUtility.isEmpty(s_receipt_required))				{	s_receipt_required = "";							}
	if (s_receipt_required.equalsIgnoreCase("R"))	{	s_receipt_required = "Receipt Required";		}
	if (s_receipt_required.equalsIgnoreCase("P"))	{	s_receipt_required = "Previously Received";	}
	if (s_receipt_required.equalsIgnoreCase("N"))	{	s_receipt_required = "No Receipt Required";	}
	if (s_receipt_required.equalsIgnoreCase("E"))	{	s_receipt_required = "End User Receipt";		}

	if (HiltonUtility.isEmpty(s_po_number))	{	s_po_number = "N/A";									}
	if (bd_release_number == null)		{	bd_release_number = new BigDecimal(0000);	}
	if (bd_revision_number == null)		{	bd_revision_number = new BigDecimal(0000);	}
	if (HiltonUtility.isEmpty(s_po_status))		{	s_po_status =DocumentStatus.PO_INPROGRESS;									}

	List lineList = (List) poHeader.getPoLineList();
	int	i_line_count = 0;

	if (lineList != null)
	{
		i_line_count = lineList.size();
	}

	int iBefore = 0;
	int iAfter = 0;
	int iBeforeItem = 0;
	int iAfterItem = 0;

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	String	s_buyer_code = poHeader.getBuyerCode();
	UserProfile buyer = UserManager.getInstance().getUser(oid, s_buyer_code);
%>

<%@ include file="/orders/po_process_steps.jsp" %>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top><img src="<%=contextPath%>/images/newblue.gif" width=277px height=62.5px border=0></td>
	<td valign=bottom align=right height=30px>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
<%	int i_colspan = 1;%>
		<tr>
			<td nowrap align=right><b><%=OrderType.toString(s_po_type, oid)%> #:</b></td>
			<td width=100px><%=s_po_number%></td>
		</tr>
<%	if (bd_release_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
		<tr>
			<td nowrap align=right><b>Release #:</b></td>
			<td width=100px><%=bd_release_number%></td>
		</tr>
<%	}
		if (bd_revision_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
		<tr>
			<td nowrap align=right><b>Revision #:</b></td>
			<td width=100px><%=bd_revision_number%></td>
		</tr>
<%	} %>
		<tr>
			<td nowrap align=right><b>Status:</b></td>
			<td width=100px><%=DocumentStatus.toString(s_po_status, oid)%></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<td width=660px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=660px>
		<tr>
			<td width=50% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "general_information", "General Information")%></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr <%=HtmlWriter.isVisible(oid, "po-buyerName")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-buyerName", "Buyer")%>:</b>&nbsp;</td>
							<td nowrap><%=buyer.getDisplayName()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "orderDate")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "orderDate", "Order Date")%>:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getPoDate(), oid)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-fiscalYear")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-fiscalYear", "Fiscal Year")%>:</b>&nbsp;</td>
							<td nowrap><%=poHeader.getFiscalYear()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "contract")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "contract", "Contract")%>:</b>&nbsp;</td>
							<td nowrap><%=poHeader.getContractNo()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "effectiveDate")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "effectiveDate", "Effective Date")%>:&nbsp;</b></td>
							<td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getEffectiveDate(), oid)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "promisedDate")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "promisedDate", "Promised Date")%>:&nbsp;</b></td>
							<td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getPromisedDate(), oid)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-requiredDate")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-requiredDate", "Required Date")%>:&nbsp;</b></td>
							<td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getRequiredDate(), oid)%></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
<%
	if (processMap.containsKey("HEADER_SHIPPING"))
	{
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
				<table border=0 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "shipping_information", "Shipping Information")%></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "po-shipToCode")%>><td class=browseRow height=12px nowrap><b><%=poHeader.getShipToCode()%></b></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-shp-addressLine1")%>><td class=browseRow height=12px nowrap><%=s_ship_address_line_1%></td></tr>
						</table>

						<table id=supplierRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (!HiltonUtility.isEmpty(s_ship_address_line_2))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-shp-addressLine2")%>><td class=browseRow height=12px nowrap><%=s_ship_address_line_2%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_ship_address_line_3))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-shp-addressLine3")%>><td class=browseRow height=12px nowrap><%=s_ship_address_line_3%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_ship_address_line_4))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-shp-addressLine4")%>><td class=browseRow height=12px nowrap><%=s_ship_address_line_4%></td></tr>
<%		} %>
						<tr><td class=browseRow height=12px nowrap><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-shp-country")%>><td class=browseRow height=12px nowrap><%=s_ship_country%></td></tr>
						</table>
<%		if (!HiltonUtility.isEmpty(poHeader.getShipToContact())) { %>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "po-shp-attention")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shp-attention", "Attn")%>:&nbsp<%=poHeader.getShipToContact()%></td></tr>
						</table>
<%		} %>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "po-shipVia")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shipVia", "Ship Via")%>:&nbsp<%=poHeader.getShipViaCode()%></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-priority")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-priority", "Priority")%>:&nbsp<%=poHeader.getPriorityCode()%></td></tr>
						<!--tr><td class=browseRow height=12px nowrap>Currency:&nbsp<%=poHeader.getCurrencyCode()%></td></tr-->
						</table>
					</td>
				</tr>
				</table>
			</td>
<%	} %>
		</tr>
		<tr><td colspan=2>&nbsp;</td></tr>
<%
	if (processMap.containsKey("HEADER_SUPPLIER"))
	{
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
		<tr>
			<td width=50% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "supplier_information", "Supplier Information")%></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "po-supplier")%>><td class=browseRow height=12px nowrap><b><%=poHeader.getVendorId()%></b></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-sup-addressLine1")%>><td class=browseRow height=12px nowrap><%=s_vend_address_line_1%></td></tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (!HiltonUtility.isEmpty(s_vend_address_line_2))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-sup-addressLine2")%>><td class=browseRow height=12px nowrap><%=s_vend_address_line_2%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_vend_address_line_3))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-sup-addressLine3")%>><td class=browseRow height=12px nowrap><%=s_vend_address_line_3%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_vend_address_line_4))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-sup-addressLine4")%>><td class=browseRow height=12px nowrap><%=s_vend_address_line_4%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_vend_city) || !HiltonUtility.isEmpty(s_vend_state) || !HiltonUtility.isEmpty(s_vend_postal_code)) { %>
						<tr>
							<td class=browseRow height=12px nowrap>
<%			if (!HiltonUtility.isEmpty(s_vend_city)) { %>
								<%=s_vend_city%>&nbsp;
<%			}
				if (!HiltonUtility.isEmpty(s_vend_state)) { %>
								<%=s_vend_state%>&nbsp;
<%			}
				if (!HiltonUtility.isEmpty(s_vend_postal_code)) { %>
								<%=s_vend_postal_code%>
<%			} %>
							</td>
						</tr>
<%		}
			if (!HiltonUtility.isEmpty(s_vend_country)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "po-sup-country")%>><td class=browseRow height=12px nowrap><%=s_vend_country%></td></tr>
<%		} %>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (!HiltonUtility.isEmpty(poHeader.getContactName()))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-sup-attention")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-attention", "Attn")%>:&nbsp;<%=poHeader.getContactName()%></td></tr>
<%		} %>
						</table>
					</td>
				</tr>
				</table>
			</td>
<%	}
		if (processMap.containsKey("HEADER_BILLING"))
		{
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
				<table border=0 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "billing_information", "Billing Information")%></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=210px class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "po-billToCode")%>><td class=browseRow height=12px nowrap><b><%=poHeader.getBillToCode()%></b></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-bil-addressLine1")%>><td class=browseRow height=12px nowrap><%=s_bill_address_line_1%></td></tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (!HiltonUtility.isEmpty(s_bill_address_line_2))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-bil-addressLine2")%>><td class=browseRow height=12px nowrap><%=s_bill_address_line_2%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_bill_address_line_3))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-bil-addressLine3")%>><td class=browseRow height=12px nowrap><%=s_bill_address_line_3%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_bill_address_line_4))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-bil-addressLine4")%>><td class=browseRow height=12px nowrap><%=s_bill_address_line_4%></td></tr>
<%		} %>
						<tr><td class=browseRow height=12px nowrap><%=s_bill_city%>&nbsp;<%=s_bill_state%>&nbsp;<%=s_bill_postal_code%></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-bil-country")%>><td class=browseRow height=12px nowrap><%=s_bill_country%></td></tr>
						</table>

<%		if (!HiltonUtility.isEmpty(poHeader.getBillToContact()))
			{ %>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "po-bil-attention")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-bil-attention", "Attn")%>:&nbsp<%=poHeader.getBillToContact()%></td></tr>
						</table>
<%		} %>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "po-paymentTerms")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-paymentTerms", "Terms")%>:&nbsp<%=poHeader.getTermsCode()%></td></tr>
<%		if (!HiltonUtility.isEmpty(poHeader.getFobCode())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "po-fob")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-fob", "FOB")%>:&nbsp<%=poHeader.getFobCode()%></td></tr>
<%		} %>
						</table>

					</td>
				</tr>
				</table>
			</td>
<%	} %>
		</tr>
		<tr><td colspan=2>&nbsp;</td></tr>
		<tr>
			<td width=50% align=center valign=top>
<%	if (s_po_type.equals("BO") || s_po_type.equals("RO") || s_po_type.equals("DO") || s_po_type.equals("DR") || s_po_type.equals("SB") || s_po_type.equals("SR")) { %>
				<table border=0 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;Blanket Information</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr <%=HtmlWriter.isVisible(oid, "effectiveDate")%>>
							<td nowrap align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "effectiveDate", "Effective Date")%>:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getFormattedDate(blanketInfo.getPoEffective(), oid)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "dateExpires")%>>
							<td nowrap align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dateExpires", "Date Expires")%>:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getFormattedDate(blanketInfo.getPoExpires(), oid)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "releaseLimit")%>>
							<td nowrap align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "releaseLimit", "Release Limit")%>:</b>&nbsp;</td>
							<td nowrap>$<%=HiltonUtility.getFormattedDollar(blanketInfo.getPoReleaseLimit(), oid)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "blanketLimit")%>>
							<td nowrap align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "blanketLimit", "Blanket Limit")%>:</b>&nbsp;</td>
							<td nowrap>$<%=HiltonUtility.getFormattedDollar(blanketInfo.getPoBlanket(), oid)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "numberOfReleases")%>>
							<td nowrap align=right width=50%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "numberOfReleases", "Number of Releases")%>:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getBigDecimalFormatted(blanketInfo.getReleaseCount(), 0)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "releaseTotal")%>>
							<td nowrap align=right width=50%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "releaseTotal", "Release Total")%>:</b>&nbsp;</td>
							<td nowrap>$<%=HiltonUtility.getFormattedDollar(blanketInfo.getReleaseTotal(), oid)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "availableBlanket")%>>
							<td nowrap align=right width=50%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "availableBlanket", "Available Blanket")%>:</b>&nbsp;</td>
							<td nowrap>$<%=HiltonUtility.getFormattedDollar(blanketInfo.getAvailableBlanket(), oid)%></td>
						</tr>
						</table>
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

<%	if (processMap.containsKey("HEADER_ACCOUNTS"))
		{
			List accList = (List) poHeader.getAccountList();
			BigDecimal bd_total_perc = new BigDecimal(0.00);
			BigDecimal bd_total_amt = new BigDecimal(0.00);
			if (accList != null && accList.size() > 0)
			{
%>
<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<td align=center width=660px>
		<table border=0 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=70% height=18px class=browseHdr>&nbsp;<b>Account</b></td>
							<td width=15% height=18px class=browseHdr align=right>&nbsp;<b>Percent Alloc.</b></td>
							<td width=15% height=18px class=browseHdr align=right>&nbsp;<b>Amount Alloc.</b></td>
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
		for (int i = 0;i < accList.size(); i++)
		{
			Account account = (Account) accList.get(i);

			BigDecimal bd_alloc_perc = HiltonUtility.getBigDecimalFormatted(account.getAllocPercent(), 2);
			BigDecimal bd_alloc_amt = HiltonUtility.getFormattedDollar(account.getAllocAmount(), oid);

			bd_total_perc = bd_total_perc.add(bd_alloc_perc);
			bd_total_amt = bd_total_amt.add(bd_alloc_amt);

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
			} %>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=70% class=browseRow><%=s_account%></td>
					<td width=15% class=browseRow align=right><%=bd_alloc_perc%>%</td>
					<td width=15% class=browseRow align=right><%=bd_alloc_amt%></td>
				</tr>
				</table>
<%	} %>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td class=browseRow>&nbsp;</td>
					<td class=browseRow colspan=2 valign=top><hr size=0 align=right colspan=2></td>
				</tr>
				<tr>
					<td width=70% class=browseRow>&nbsp;</td>
					<td width=15% class=browseRow align=right><%=HiltonUtility.getBigDecimalFormatted(bd_total_perc, 2)%>%</td>
					<td width=15% class=browseRow align=right><%=HiltonUtility.getFormattedDollar(bd_total_amt, oid)%></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%		}
		} %>

<br>

<%	if (processMap.containsKey("HEADER_NOTES")) { %>
<div id="commentBefore" style="display:none;">
<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<td align=center width=660px>
		<table border=0 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=100% colspan=2 height=18px class=browseHdr>&nbsp;<b>Comment</b></td>
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
	List cmtList = (List) poHeader.getDocCommentList();
	if (cmtList != null)
	{
		for (int i = 0; i < cmtList.size(); i++)
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
				continue;
			}
			if (s_cmt_print.equals("N"))
			{
				continue;
			}
			iBefore++;
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=100%  colspan=2 class=browseRow>
<%				if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_title%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				<tr>
					<td width=75px>&nbsp;</td>
					<td width=100%>
<%				if (s_cmt_bold.equals("Y")) 	{ %>	<b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				</table>
<% 	}
	} %>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
</div>
<%	} %>

<br>

<%	if (processMap.containsKey("SHOPCART")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<td align=center width=660px>
		<table border=0 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "po-lineNumber")%> width=7% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-lineNumber", "Line #")%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-itemNumber")%> width=18% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-itemNumber", "Item/Part #")%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-commodity")%> width=15% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-commodity", "Commodity")%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-quantity")%> width=10% class=browseHdr nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-quantity", "Quantity")%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-uom")%> width=10% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-uom", "UOM")%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-extendedCost")%> width=15% class=browseHdr nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-extendedCost", "Ext Cost")%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-discountAmount")%> width=12% class=browseHdr nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-discountAmount", "Discount")%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-lineStatus")%> width=13% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-lineStatus", "Line Status")%></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<table id=itemRow border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td>
<%
		for (int i = 0; i < i_line_count; i++)
		{
			PoLine poLine = (PoLine) lineList.get(i);

			BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid);
			BigDecimal bd_unit_price = HiltonUtility.getFormattedDollar(poLine.getUnitPrice(), oid);
			BigDecimal bd_extended_cost = HiltonUtility.getFormattedDollar(bd_quantity.multiply(bd_unit_price), oid);

			List shipToList = (List) poLine.getShipToList();
			List billToList = (List) poLine.getBillToList();
			List accountList = (List) poLine.getAccountList();
			List commentList = (List) poLine.getDocCommentList();
%>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
<%			if (commentList != null)
				{
					for (int ix = 0; ix < commentList.size(); ix++)
					{
						DocComment docComment = (DocComment) commentList.get(ix);
						DocText docText = docComment.getDocText();

						String s_cmt_title = docComment.getCommentTitle();
						String s_cmt_print = docComment.getCommentPrint();
						String s_cmt_bold = docComment.getCommentBold();
						String s_cmt_place = docComment.getCommentPlace();
						String s_cmt_text = docText.getStdText();

						if (s_cmt_place.equals("A"))
						{
							continue;
						}
						if (s_cmt_print.equals("N"))
						{
							continue;
						}
						iBeforeItem++;
%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %></td>
						</tr>
<%				}
				} %>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "po-lineNumber")%> width=7% class=browseRow nowrap align=right><%=i+1%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-itemNumber")%> width=18% class=browseRow nowrap><%=poLine.getItemNumber()%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-commodity")%> width=15% class=browseRow nowrap><%=poLine.getCommodity()%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-quantity")%> width=10% class=browseRow nowrap align=right><%=bd_quantity%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-uom")%> width=10% class=browseRow nowrap><%=poLine.getUmCode()%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-extendedCost")%> width=15% class=browseRow nowrap align=right><%=bd_extended_cost%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-discountAmount")%> width=12% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(poLine.getDiscountAmount(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-lineStatus")%> width=13% class=browseRow nowrap><%=DocumentStatus.toString(poLine.getStatus())%></td>
						</tr>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=poLine.getDescription()%></td>
						</tr>
<%			if (accountList != null)
				{
					BigDecimal bd_total_perc = new BigDecimal(0.00);
					BigDecimal bd_total_amt = new BigDecimal(0.00);

					for (int ix = 0; ix < accountList.size(); ix++)
					{
						Account account = (Account) accountList.get(ix);

						BigDecimal bd_alloc_perc = account.getAllocPercent();
						BigDecimal bd_alloc_amt = account.getAllocAmount();

						if (bd_alloc_perc == null)	{	bd_alloc_perc = new BigDecimal(0);	}
						if (bd_alloc_amt == null)	{	bd_alloc_amt = new BigDecimal(0);	}

						bd_total_perc = bd_total_perc.add(bd_alloc_perc);
						bd_total_amt = bd_total_amt.add(bd_alloc_amt);

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
							<td colspan=7 class=browseRow><b>Account:</b>&nbsp;<%=s_account%>&nbsp;&nbsp;&nbsp;$<%=HiltonUtility.getFormattedDollar(bd_alloc_amt, oid)%></td>
						</tr>
<%				}
				}
				if (commentList != null)
				{
					for (int ix = 0; ix < commentList.size(); ix++)
					{
						DocComment docComment = (DocComment) commentList.get(ix);
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
					iAfterItem++;
%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %></td>
						</tr>
<%				}
				}

				if (shipToList != null)
				{
					for (int ix = 0; ix < shipToList.size(); ix++)
					{
						ShipTo shipTo = (ShipTo) shipToList.get(ix);
						ShipToPK shipToPK = shipTo.getComp_id();
						String s_shp_attn = shipTo.getAttention();

						String	s_ship_address_line_1 = "";
						String	s_ship_address_line_2 = "";
						String	s_ship_address_line_3 = "";
						String	s_ship_address_line_4 = "";
						String	s_ship_city = "";
						String	s_ship_state = "";
						String	s_ship_postal_code = "";
						String	s_ship_country = "";

						Address shipToAddress = (Address) shipTo.getShipToAddress();
						if (shipToAddress != null)
						{
							s_ship_address_line_1 = shipToAddress.getAddressLine1();
							s_ship_address_line_2 = shipToAddress.getAddressLine2();
							s_ship_address_line_3 = shipToAddress.getAddressLine3();
							s_ship_address_line_4 = shipToAddress.getAddressLine4();
							s_ship_city = shipToAddress.getCity();
							s_ship_state = shipToAddress.getState();
							s_ship_postal_code = shipToAddress.getPostalCode();
							s_ship_country = shipToAddress.getCountry();
						}
%>
						<tr><td colspan=8><br></td></tr>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow>Ship <b><%=HiltonUtility.getFormattedQuantity(shipTo.getQuantity(), oid)%></b> to:</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-shipToCode")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><b><%=shipToPK.getShipToCode()%></b></td>
						</tr>
<%					if (!HiltonUtility.isEmpty(s_ship_address_line_1))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-shp-addressline1")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_address_line_1%></td>
						</tr>
<%					}
						if (!HiltonUtility.isEmpty(s_ship_address_line_2))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-shp-addressline2")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_address_line_2%></td>
						</tr>
<%					}
						if (!HiltonUtility.isEmpty(s_ship_address_line_3))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-shp-addressline3")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_address_line_3%></td>
						</tr>
<%					}
						if (!HiltonUtility.isEmpty(s_ship_address_line_4))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-shp-addressline4")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_address_line_4%></td>
						</tr>
<%					}%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></td>
						</tr>
<%
						if (!HiltonUtility.isEmpty(s_ship_country))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-shp-country")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_country%></td>
						</tr>
<%					}
						if (!HiltonUtility.isEmpty(s_shp_attn))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-shp-attention")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shp-attention", "Attn")%>: <%=s_shp_attn%></td>
						</tr>
<%					} %>
						<tr <%=HtmlWriter.isVisible(oid, "po-requiredDate")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-requiredDate", "Required Date")%>: <%=HiltonUtility.getFormattedDate(shipTo.getShipDate(), oid)%></td>
						</tr>
<%				}
				}

				if (billToList != null)
				{
					for (int ix = 0; ix < billToList.size(); ix++)
					{
						BillTo billTo = (BillTo) billToList.get(ix);
						BillToPK billToPK = billTo.getComp_id();
						String s_bill_attn = billTo.getAttention();

						String	s_bill_address_line_1 = "";
						String	s_bill_address_line_2 = "";
						String	s_bill_address_line_3 = "";
						String	s_bill_address_line_4 = "";
						String	s_bill_city = "";
						String	s_bill_state = "";
						String	s_bill_postal_code = "";
						String	s_bill_country = "";

						Address billToAddress = (Address) billTo.getBillToAddress();
						if (billToAddress != null)
						{
							s_bill_address_line_1 = billToAddress.getAddressLine1();
							s_bill_address_line_2 = billToAddress.getAddressLine2();
							s_bill_address_line_3 = billToAddress.getAddressLine3();
							s_bill_city = billToAddress.getCity();
							s_bill_state = billToAddress.getState();
							s_bill_postal_code = billToAddress.getPostalCode();
							s_bill_country = billToAddress.getCountry();
						}
%>
						<tr><td colspan=8><br></td></tr>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow>Bill <b><%=HiltonUtility.getFormattedQuantity(billTo.getQuantity(), oid)%></b> to:</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-billToCode")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><b><%=billToPK.getBillToCode()%></b></td>
						</tr>
<%					if (!HiltonUtility.isEmpty(s_bill_address_line_1))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-bil-addressline1")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_address_line_1%></td>
						</tr>
<%					}
						if (!HiltonUtility.isEmpty(s_bill_address_line_2))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-bil-addressline2")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_address_line_2%></td>
						</tr>
<%					}
						if (!HiltonUtility.isEmpty(s_bill_address_line_3))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-bil-addressline3")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_address_line_3%></td>
						</tr>
<%					}
						if (!HiltonUtility.isEmpty(s_bill_address_line_4))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-bil-addressline4")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_address_line_4%></td>
						</tr>
<%					}%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_city%>&nbsp;<%=s_bill_state%>&nbsp;<%=s_bill_postal_code%></td>
						</tr>
<%
						if (!HiltonUtility.isEmpty(s_bill_country))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-bil-country")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_country%></td>
						</tr>
<%					}
						if (!HiltonUtility.isEmpty(s_bill_attn))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-bil-attention")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-bil-attention", "Attn")%>: <%=s_bill_attn%></td>
						</tr>
<%					} %>
<%				}
				} %>
						<tr><td colspan=8><hr></td></tr>
						</table>
<%		} %>
					</td>
				</tr>
				</table>

				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "po-subtotal")%>>
							<td width="65%" class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-subtotal", "Subtotal")%>:</td>
							<td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getSubtotal(), oid)%></td>
							<td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-discountAmount")%>>
							<td width="65%" class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-discountAmount", "Discount")%>:</td>
							<td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getDiscountAmount(), oid)%></td>
							<td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-taxAmount")%>>
							<td width="65%" class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-taxAmount", "Tax")%>:</td>
							<td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getTaxAmount(), oid)%></td>
							<td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-shippingCharges")%>>
							<td width="65%" class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shippingCharges", "Shipping")%>:</td>
							<td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getShippingCharges(), oid)%></td>
							<td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-shippingTaxAmount")%>>
							<td width="65%" class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shippingTaxAmount", "Shipping Tax")%>:</td>
							<td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getShippingTax(), oid)%></td>
							<td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-otherCharges")%>>
							<td width="65%" class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-otherCharges", "Other")%>:</td>
							<td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getOtherCharges(), oid)%></td>
							<td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-otherTaxAmount")%>>
							<td width="65%" class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-otherTaxAmount", "Other Tax")%>:</td>
							<td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getOtherTax(), oid)%></td>
							<td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>

				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "po-total")%>>
							<td width=65% class=browseRow nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-total", "Total")%>:</b></td>
							<td width=10% class=browseRow nowrap align=right><b>$<%=HiltonUtility.getFormattedDollar(poHeader.getTotal(), oid)%></b></td>
							<td width=25% class=browseRow nowrap align=right>&nbsp;</td>
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
<%	} %>

<br>

<%	if (processMap.containsKey("HEADER_NOTES")) { %>
<div id="commentAfter" style="display:none;">
<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<td align=center width=660px>
		<table border=0 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=100% colspan=2 height=18px class=browseHdr>&nbsp;<b>Comment</b></td>
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
			List cmtList = (List) poHeader.getDocCommentList();
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
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=100%  colspan=2 class=browseRow>
<%				if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_title%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				<tr>
					<td width=50px>&nbsp;</td>
					<td width=100%>
<%				if (s_cmt_bold.equals("Y")) 	{ %>	<b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				</table>
<%			}
			} %>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
</div>
<%	} %>

<br>

<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
	<td align=right>
		<table border=0 cellspacing=0>
		<tr><td align=center><img src="<%=contextPath%>/images/sigs/hilton/bsc04p_david.gif"  width=183px height=59px border=0></td></tr>
		<tr><td align=center><hr size=0 color=black></td></tr>
		<tr><td align=center>Authorized Signature</td></tr>
		</table>
	</td>
</tr>
</table>

<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
	<td align=center>
		<hr size=0 width=98%>
		<table border=0 cellpadding=0 cellspacing=0 width=98%>
		<tr><td>This Order is subject to Blue Shield's terms and conditions, a copy of which will be provided upon request.  Commencement of any work pursuant to this order shall be deemed to be acceptance of these terms and conditions.</td></tr>
		<tr><td><br></td></tr>
		<tr><td>This Establishment is a Government contractor subject to all provisions of Executive Order 11246, as amended, Section 503 of the Rehabilitation Act of 1973, as amended, the Vietnam
era Veterans' Readjustment Assistance Act of 1974, as amended, 38 U.S.C. 4212, (formerly 2012) and the implementing regulations at 41 CFR Chapter 60, and these provisions are
incorporated by reference as part of this agreement.</td>
		</tr>
		</table>
		<hr size=0 width=98%>
	</td>
</tr>
</table>

<!--<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<td width=50% align=center id="buttons"><a href="javascript: printMe(); void(0);"><img src="<%=contextPath%>/images/button_print.gif" border=0 class=button alt="Print"></a></td>
	<td width=50% align=center id="buttons"><a href="javascript: closeMe(); void(0);"><img src="<%=contextPath%>/images/button_close.gif" border=0 class=button alt="Close"></a></td>
</tr>
</table>-->

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
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
		hideArea("copyright");

		this.print();
	}

	function closeMe() {
		window.top.hidePopWin();
	}

	function displayButtons() {
		displayArea("buttons");
		displayArea("copyright");
	}

// End Hide script -->
</SCRIPT>