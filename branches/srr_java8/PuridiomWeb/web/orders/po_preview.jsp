<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.po.tasks.PoBlanketInfo" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@page import="com.tsa.puridiom.common.documents.ScheduleType"%>
<%@ page import="java.math.BigDecimal" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<%
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	Contact contact = (Contact) request.getAttribute("contact");
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
	UserProfile requisitioner = UserManager.getInstance().getUser(oid, poHeader.getRequisitionerCode());

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

<tsa:hidden name="emailTo" value="N" />
<tsa:hidden name="faxTo" value="N" />
<tsa:hidden name="PoHeader_icPoHeader" value="<%=bd_ic_po_header%>" />
<tsa:hidden name="print_option_view" value="Y" />
<tsa:hidden name="viewNow" value="Y" />

<%@ include file="/orders/po_process_steps.jsp" %>

<table width=660px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td nowrap class=formtype valign=middle>&nbsp;&nbsp;<%=OrderType.toString(s_po_type, oid)%> Information</td>
		</tr>
		</table>
	</td>
	<td valign=bottom align=right height=30px>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
<%	int i_colspan = 1;%>
		<tr>
			<td nowrap align=right><b>Order #:</b></td>
			<td width=100px><%=s_po_number%></td>
<%	if (bd_release_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b>Release #:</b></td>
			<td width=100px><%=bd_release_number%></td>
<%	}
		if (bd_revision_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b>Revision #:</b></td>
			<td width=100px><%=bd_revision_number%></td>
<%	} %>
		</tr>
		<tr>
			<td colspan=<%=i_colspan%> nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
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
							<td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getPoDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-fiscalYear")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-fiscalYear", "Fiscal Year")%>:</b>&nbsp;</td>
							<td nowrap><%=poHeader.getFiscalYear()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "contract")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "contract", "Contract")%>:</b>&nbsp;</td>
							<td nowrap><%=poHeader.getContractNo()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-msrNumber")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-msrNumber", "MSR #")%>:</b>&nbsp;</td>
							<td nowrap><%=poHeader.getMsrNumber()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "effectiveDate")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "effectiveDate", "Effective Date")%>:&nbsp;</b></td>
							<td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getEffectiveDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "promisedDate")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "promisedDate", "Promised Date")%>:&nbsp;</b></td>
							<td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getPromisedDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-requiredDate")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-requiredDate", "Required Date")%>:&nbsp;</b></td>
							<td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getRequiredDate(), oid, userDateFormat)%></td>
						</tr>
						<%	if (!HiltonUtility.isEmpty(poHeader.getRequisitionerCode())) { %>
	            			<tr <%=HtmlWriter.isVisible(oid, "po-requisitionerName")%>>
	              				<td nowrap align="right" width="40%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-requisitionerName", "Requisitioner Name")%>:&nbsp;</b></td>
	              				<td nowrap><%=requisitioner.getDisplayName()%></td>
	            			</tr>
						<%	} if (!HiltonUtility.isEmpty(poHeader.getDepartmentCode())) { %>
	        				<tr <%=HtmlWriter.isVisible(oid, "po-department")%>>
						        <td align="right" width="40%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-department", "Department")%>:&nbsp;</b></td>
						        <td nowrap><%=poHeader.getDepartmentCode()%></td>
	        				</tr>
						<%	} %>
						<tr <%=HtmlWriter.isVisible(oid, "po-pystatus")%>>
							<td nowrap align="right" width="40%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-pystatus", "Invoice Status")%>:</b></td>
							<td valign=top><%=DocumentStatus.toString(poHeader.getPyStatus(), oid)%></td>
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
		<tr><td colspan="2">
		<table style="margin-left: 87px" border="0">
		  <tr>
		  	<td valign=top align="right"><b><%=DictionaryManager.getLabel(oid, "po-purpose", "Purpose")%>:</b></td>
		    	<td> <textarea readonly class="browseRow" style="border: 0" style="overflow:hidden" cols="84" rows="5" >${esapi:encodeForHTML(poHeader.internalComments)}</textarea></td>
		  </tr>
		</table>
		</td></tr>
		<tr><td colspan="2">&nbsp;</td></tr>
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
<%		if (!HiltonUtility.isEmpty(poHeader.getContactPhone())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "po-sup-phone")%>><td colspan="2" class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-phone", "Phone")%>:&nbsp;<%=poHeader.getContactPhone()%></td></tr>
<%		}
		if(contact != null) {
			%>
						<tr <%=HtmlWriter.isVisible(oid, "cnt-email")%>><td colspan="2" class="browseRow" height="12px" nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "contact-information", "Contact information")%></b></td></tr>
						<tr>
							<td nowrap align="right" width="40%"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-email", "email")%>:&nbsp;</td>
							<td nowrap><%= contact.getEmailAddr() %></td>
						</tr>
						<tr>
							<td nowrap align="right" width="40%"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "contact-phoneNumber", "Phone")%>:&nbsp;</td>
							<td nowrap><%= contact.getPhoneNumber() %></td>
						</tr>
						<tr>
							<td nowrap align="right" width="40%"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "contact-mobileNumber", "Mobile Phone")%>:&nbsp;</td>
							<td nowrap><%= contact.getMobileNumber() %></td>
						</tr>
			<%
		}
%>
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
							<td nowrap><%=HiltonUtility.getFormattedDate(blanketInfo.getPoEffective(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "expirationDate")%>>
							<td nowrap align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "expirationDate", "Expiration Date")%>:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getFormattedDate(blanketInfo.getPoExpires(), oid, userDateFormat)%></td>
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
			/*if (s_cmt_print.equals("N"))
			{
				continue;
			}*/
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
	}
	List atcList = (List) poHeader.getDocAttachmentList();
	if (atcList != null)
	{ %>
		<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
		<tsa:tr><tsa:td colspan="3"><hr size="0"></tsa:td></tsa:tr>

		<tsa:tr>
			<tsa:td width="30%" cssClass="browseRow">&nbsp;<b><tsa:label labelName="req-attachment-title" defaultString="Attachment Title"></tsa:label></b></tsa:td>
			<tsa:td width="60%" cssClass="browseRow">&nbsp;<b><tsa:label labelName="req-attachment-description" defaultString="Attachment Description"></tsa:label></b></tsa:td>
			<tsa:td width="10%" cssClass="browseRow">&nbsp;</tsa:td>
		</tsa:tr>

		<tsa:tr>
			<tsa:td>
<%
		for(int i = 0; i < atcList.size(); i++)
		{
			DocAttachment docAttachment = (DocAttachment) atcList.get(i);
			String s_atc_title = docAttachment.getDocTitle();
			String filename = docAttachment.getDocFilename();
			String ext = filename.substring(filename.lastIndexOf(".") + 1);
%>
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
				<tsa:tr>
					<td width=25px align=center valign=middle>
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
					<tsa:td cssClass="browseRow">
						<a href="javascript: openDocument(<%=i%>); void(0);" title="Click here to open the <%=docAttachment.getDocTitle()%> attachment."><%=s_atc_title%></a>
						<tsa:hidden name="docFilename" value="<%=filename%>"/>
					</tsa:td>
				</tsa:tr>
				</table>
<%				}%>
			</tsa:td>
		</tsa:tr>
		</table>
	<%} %>
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
		int iRow = 0;
		for (int i = 0; i < i_line_count; i++)
		{
			PoLine poLine = (PoLine) lineList.get(i);

			BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid);
			BigDecimal bd_unit_price = poLine.getUnitPrice();
			BigDecimal b_um_factor = poLine.getUmFactor();
			BigDecimal bd_extended_cost = HiltonUtility.getFormattedDollar(bd_quantity.multiply(bd_unit_price).multiply(b_um_factor), oid);

			List shipToList = (List) poLine.getShipToList();
			List billToList = (List) poLine.getBillToList();
			List accountList = (List) poLine.getAccountList();
			List commentList = (List) poLine.getDocCommentList();
%>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
<%			if (commentList != null && commentList.size() > 0)
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
						/*if (s_cmt_print.equals("N"))
						{
							continue;
						}*/
						iBeforeItem++;
%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow>
								<b><tsa:label labelName="po-catalogItemComment" defaultString="Comment" />:&nbsp;</b>
								<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
							</td>
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
<%				if (accountList != null)
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
<%					}
				}

				if (!(poLine.getRequisitionNumber().equalsIgnoreCase(poHeader.getRequisitionNumber())) || (!poLine.getRequisitionerCode().equals(poHeader.getRequisitionerCode()) && !HiltonUtility.isEmpty(poLine.getRequisitionerCode())))
				{	%>
								<tr>
									<td class=browseRow nowrap>&nbsp;</td>
<%					if (!poLine.getRequisitionNumber().equals(poHeader.getRequisitionNumber())) {%>
									<td <%=HtmlWriter.isVisible(oid, "ln-po-requisitionNumber")%> colspan=4 class=browseRow><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ln-po-requisitionNumber", "Requisition #")%>:</b>&nbsp;<%=headerEncoder.encodeForHTML(poLine.getRequisitionNumber())%></td>
<%					}
					if (!poLine.getRequisitionerCode().equals(poHeader.getRequisitionerCode()) && !HiltonUtility.isEmpty(poLine.getRequisitionerCode())) {%>
									<td <%=HtmlWriter.isVisible(oid, "ln-po-requisitionerName")%> colspan=4 class=browseRow><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ln-po-requisitionerName", "Requisitioner Name")%>:</b>&nbsp;<%=UserManager.getInstance().getUser(oid, poLine.getRequisitionerCode()).getDisplayName()%></td>
<%					}%>
								</tr>
<%				}

				if (poLine != null) {
	    			List inspList = poLine.getInspectionList();
	    			if (inspList != null && !inspList.isEmpty()) {
	    			%>
	    			<tr>
	    					<tsa:td colspan="7" height="18px">
	    						<br></br>
	    						<table cellpadding="0" cellspacing="0" border="0"  width="100%">
	    						<td>
	    						<ol>
	    					<%
	    						for (int ix = 0; ix < inspList.size(); ix++) {
	    						InspectionHeader insp = (InspectionHeader) inspList.get(ix);
	    						%>
	    						    <% String inspType = insp.getInspectType() ;
	    						    		String inspDesc = "Receipt Inspection" ;
	    						    		if (inspType == null) inspType = "RI" ;
	    						    		if (inspType.equals("FI"))   {
	    						    			inspDesc = "Field Inspection" ;
	    						    		} else if (inspType.equals("GI"))   {
	    						    			inspDesc = "General Inspection" ;
	    						    		} else if (inspType.equals("CG"))   {
	    						    			inspDesc = "CGD Inspection" ;
	    						    		}
	    						    %>
	    							<li>
	    								<%=HiltonUtility.ckNull(inspDesc) %>&nbsp;&nbsp;&nbsp; <%=HiltonUtility.ckNull(insp.getStandardCode())%>
	    							</li>
	    								<ul>
	    							<%	if (insp != null)
	    								{
	    									List inspLineLi	= (List) insp.getInspectionLineList();
	    									if (inspLineLi != null) {
	    										for (int j = 0; j < inspLineLi.size(); j++)
	    										{
	    												InspectionLine inspLine = (InspectionLine) inspLineLi.get(j) ;
	    							%>
	    												<li>&nbsp;<%=HiltonUtility.ckNull(inspLine.getCritNo())%>
	    												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=HiltonUtility.ckNull(inspLine.getInspectCode())%>
	    												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=HiltonUtility.ckNull(inspLine.getCritDescription())%></li>
	    												<br></br>
	    												<br>
	    							<%			}
	    									}
	    								}
	    								if (insp.getInspectionLineList() != null && insp.getInspectionLineList().size() > 0) {
	    								} else {%>
	    											<li><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noInspectionsItem", "There are no inspection records for this item")%>.</b></li>
	    											<br></br>
	    											<br>
	    								<%}%>
	    								</ul>
	    						<% }%>
	    						</ol>
	    						</td>
	    						</table>
	    				</tsa:td>
	    			</tr>
	    			<% 		}
	    				}
	    			iRow++;

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
						/*if (s_cmt_print.equals("N"))
						{
							continue;
						}*/
						iAfterItem++;
%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow>
								<b><tsa:label labelName="po-catalogItemComment" defaultString="Comment" />:&nbsp;</b>
								<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
							</td>
						</tr>
<%				}
				}

	            List attachmentList = (List) poLine.getDocAttachmentList();
	            boolean flagAttachment = false;
	            if (attachmentList != null)
				{
						for (int ix = 0; ix < attachmentList.size(); ix++)
						{
							DocAttachment docAttachment = (DocAttachment) attachmentList.get(ix);
							String	filename = docAttachment.getDocFilename();
							String	ext = filename.substring(filename.lastIndexOf(".") + 1);
							%>
							<tr>
								<td class=browseRow nowrap>&nbsp;</td>
								<td class=browseRow align="right">
								<%if(!flagAttachment){%>
									<b><tsa:label labelName="po-catalogItemAttachment" defaultString="Attachments" />:&nbsp;</b>
									<%flagAttachment=true;
								}%>

								<input align="left" type=hidden name="docFilename" value = "<%=filename%>">
	<%		if (ext.equalsIgnoreCase("DOC")) {%>
								<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>');void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
	<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
								<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
	<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
								<a href="javascript: oopenAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
	<%		} else if (ext.equalsIgnoreCase("EML")) {%>
								<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/email.gif" border=0 alt="Open Attached Email"></a>
	<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
								<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
	<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
								<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
	<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
								<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
	<%		} else {%>
								<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
	<%		}%>
							</td>
								<td nowrap>
									<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);" title="Click here to Open the file"><%=docAttachment.getDocTitle()%></a>
								</td>
							</tr>
	<%
						}
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
							<td colspan=7 class=browseRow><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-requiredDate", "Required Date")%>: <%=HiltonUtility.getFormattedDate(shipTo.getShipDate(), oid, userDateFormat)%></td>
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
				<tr><td><br></td></tr>
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
<%	if (HiltonUtility.isQriCanadian(oid, poHeader.getUdf1Code()) || !oid.equalsIgnoreCase("qri06p"))
		{ %>
						<tr <%=HtmlWriter.isVisible(oid, "po-useTaxAmount")%>>
							<td class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-useTaxAmount", "Tax")%>:</td>
							<td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(poHeader.getUseTaxAmount(), oid)%></td>
							<td class=browseRow nowrap align=right>&nbsp;</td>
						</tr>
<%	} %>
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
						<tsa:tr field="po-estCost" >
							<tsa:td cssClass="processOn" field="po-estCost" noWrap="nowrap" align="right"><b><tsa:label labelName="po-estCost" defaultString="Total" checkRequired="false" />:</b></tsa:td>
							<tsa:td cssClass="browseRow" field="po-estCost" noWrap="nowrap" align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getEstimatedCost(), oid)%></tsa:td>
							<td class=browseRow nowrap align=right>&nbsp;</td>
						</tsa:tr>
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
					/*if (s_cmt_print.equals("N"))
					{
						continue;
					}*/
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

<!-- ADDED 2007/01/27 -->
<br><br>
<table border="0" cellpadding="0" cellspacing="0" width="660px">
<tr>
  <td align="center" width="660px">
<%
    List scheduleList = (List) poHeader.getScheduleList();
      int si = 0;
      String typeOfSchedule= "";
      String lastTypeOfSchedule= "";
      if (scheduleList != null) {
        for(int i = 0; i < scheduleList.size(); i++) {
          Schedule schedule = (Schedule) scheduleList.get(i);
          typeOfSchedule = schedule.getComp_id().getScheduleType();

          //if (schedule == null) {
          //  continue;
          //}
          si++;
%>
    <table border="0" cellspacing="0" cellpadding="0" width="650px" class="browseHdr">
<%

          if(!typeOfSchedule.equals(lastTypeOfSchedule)) {
%>
    <tr>
      <td>
        <table border="1" cellspacing="0" cellpadding="0" width="650px" class="browseHdr">
        <tr>
          <td>
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdr">
            <tr>
              <td width="55%" height="18px" class="browseHdr">&nbsp;<b><%=ScheduleType.toString(typeOfSchedule, oid)%></b></td>
              <td width="15%" height="18px" class="browseHdr" align="center">&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-scheduleDate", "Schedule")%></b></td>
              <td width="15%" height="18px" class="browseHdr" align="center">&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-revisedDate", "Revised")%></b></td>
              <td width="15%" height="18px" class="browseHdr" align="center">&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-completionDate", "Completion")%></b></td>
            </tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
<%
          }
%>
    <tr>
      <td>
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tr>
          <td width="55%" class="browseRow"><%=(schedule.getDescription() == null)?"":String.valueOf(schedule.getDescription())%></td>
          <td width="15%" class="browseRow" align="center" valign="top"><%=(schedule.getScheduleDate() == null)?"":String.valueOf(schedule.getScheduleDate())%></td>
		  <td width="15%" class="browseRow" align="center" valign="top"><%=(schedule.getRevisedDate() == null)?"":String.valueOf(schedule.getRevisedDate())%></td>
          <td width="15%" class="browseRow" align="center" valign="top"><%=(schedule.getCompletionDate() == null)?"":String.valueOf(schedule.getCompletionDate())%></td>
        </tr>
        </table>
      </td>
    </tr>

    </table>

<%
		lastTypeOfSchedule = schedule.getComp_id().getScheduleType();
        }
      }
%>
  </td>
</tr>
</table>
<!-- END ADDED 2007/01/27 -->

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<td width=33% align=center id="buttons"><div id="pxbutton"><a href="javascript: printMe(); void(0);">Print</a></div></td>
	<td width=33% align=center id="buttons"><div id="pxbutton"><a href="javascript: printPdf('<%=bd_ic_po_header%>'); void(0);">Preview</a></div></td>
	<td width=33% align=center id="buttons"><div id="pxbutton"><a href="javascript: closeMe(); void(0);">Close</a></div></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

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

<% if (oid.equalsIgnoreCase("BLY07P")) { %>
		doSubmit('', 'PrintPoPdf');
<% } else { %>
		this.print();
<% } %>
	}

	function closeMe() {
		window.top.hidePopWin();
	}

    function printPdf(icPoHeader) {
		popupParameters = "PoHeader_icPoHeader=" + icPoHeader ;
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		frm.emailTo.value = 'N';
		frm.faxTo.value = 'N';
		frm.viewNow.value = 'Y' ;
		frm.PoHeader_icPoHeader.value =icPoHeader ;
		popupParameters = popupParameters + ";viewNow=Y" ;
		doSubmit('', 'PrintPoPdf');
    }

	function displayButtons() {
		displayArea("buttons");
	}

	function openDocument(row)
	{
		var filename = "";
		if (document.all("docFilename").length > 1)
		{
			filename = frm.docFilename[row].value;
		}
		else
		{
			filename = frm.docFilename.value;
		}
		openAttachment(filename);
	}

// End Hide script -->
</SCRIPT>