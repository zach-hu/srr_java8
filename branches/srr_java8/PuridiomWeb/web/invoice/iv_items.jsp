<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.pomanager.PoManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String s_allow_itemdesc_edit = propertiesManager.getProperty("VOUCHER OPTIONS", "ALLOW ITEMDESC EDIT", "N");
	String	awardedPO = propertiesManager.getProperty("VOUCHER", "AWARDEDPO", "N");
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
	String	s_ivc_number = invoiceHeader.getInvoiceNumber();
	String	s_ivc_status = invoiceHeader.getStatus();
	String	s_current_process = "ITEMS";
	String	s_current_page = "/invoice/iv_items.jsp";
	String	s_current_method = "InvoiceLineUpdateAll";
	String	s_current_process_method = "";
	String  s_curr_code = invoiceHeader.getCurrencyCode();

	String  total_terms = invoiceHeader.getUdf9Code();
	String  base_terms = invoiceHeader.getUdf10Code();
	if ( (total_terms.equals("Y")&&base_terms.equals("Y")) || (!total_terms.equals("Y")&&!base_terms.equals("Y")))
	{
		total_terms = "Y";	base_terms= "N" ;
	}

	boolean applyDiscount = true;
	if (oid.equalsIgnoreCase("WPC08P"))
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String paymentDueDateString = formatter.format(invoiceHeader.getPaymentDueDate());
		Integer	daysAfter = new Integer(0);
		daysAfter = new Integer(Dates.getDaysAfter("", paymentDueDateString, 0));
		if(!(invoiceHeader.getTermsDiscdays().intValue() < daysAfter.intValue()))
		{
			applyDiscount = false;
		}
	}

	String	invoiceExceptionErrorMsg = (String) request.getAttribute("errorMsg");

	List lineitems = (List) request.getAttribute("invoiceLineList");

	boolean isInvCreatedFromPo = false;
	BigDecimal subtotal = new BigDecimal(0);
	BigDecimal discountAmount = new BigDecimal(0);
	BigDecimal taxAmount = new BigDecimal(0);
	BigDecimal useTaxAmount = new BigDecimal(0);
	BigDecimal shippingTax = new BigDecimal(0);
	BigDecimal otherTax = new BigDecimal(0);
	BigDecimal total = new BigDecimal(0);
	String poSubType = "";
	if (invoiceHeader.getIcPoHeader().compareTo(new BigDecimal(0)) > 0)
	{
		isInvCreatedFromPo = true;
		PoHeader poHeader = PoManager.getInstance().getPoHeader(oid, invoiceHeader.getIcPoHeader().toString());

		subtotal = poHeader.getSubtotal();
		discountAmount = poHeader.getDiscountAmount();
		taxAmount = poHeader.getTaxAmount();
		useTaxAmount = poHeader.getUseTaxAmount();
		shippingTax = poHeader.getShippingCharges();
		otherTax = poHeader.getOtherCharges();
		total = poHeader.getTotal();
		poSubType = poHeader.getSubType();
	}
%>
<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=invoiceHeader.getStatus()%>"/>
<tsa:hidden name="InvoiceHeader_termsDiscperc" value="<%=invoiceHeader.getTermsDiscperc()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="originalAccount_icLine" value=""/>
<tsa:hidden name="DocComment_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="formType" value="IVH"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="lineNumber" value=""/>
<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Items</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Invoice #:</b></td>
			<td width=125px><%=invoiceHeader.getInvoiceNumber()%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=125px><%=DocumentStatus.toString(invoiceHeader.getStatus(), oid)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
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
<%@ include file="/invoice/iv_info.jsp" %>
<br>

<table width="640px">
	<tr>
		<td width="75%" align="right">
			<%	if ((invoiceHeader.getIcPoHeader().compareTo(new BigDecimal(0)) > 0) && (invoiceHeader.getStatus().compareTo("6000") <= 0)) { %>
				<input type="button" value="Pay Balance" onclick="payBalance();">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<%	} %>
		</td>
		<td width="25%" align="right" id="allDetails">
		<%	if (lineitems != null && lineitems.size() > 0) { %>
			<a href="javascript: showAllDetails(); void(0);"><img id="showAllDetailsImg" src="<%=contextPath%>/images/cmd_maximize.gif" border="0"></a>
			<a href="javascript: showAllDetails(); void(0);">Expand All</a>
		<%	} %>
		</td>
	</tr>
</table>

<%	if (!HiltonUtility.isEmpty(invoiceExceptionErrorMsg)) { %>
<table border=0 cellpadding=0 cellspacing=0>
<tr>
	<td width="5px">&nbsp;</td>
	<td valign=top class="error">
		<%=invoiceExceptionErrorMsg%>
	</td>
</tr>
</table>

<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center valign=top>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width="520px" align="center" class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRowHdr>
				<tr>
					<td width=5% class=browseHdrDk nowrap align=center>&nbsp;</td>
					<td width=25% class=browseHdrDk nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-itempart", "Part Number",false)%></td>
					<td width=15% class=browseHdrDk nowrap><a href="javascript: browseLookup('InvoiceLine_umCode', 'unitofmeasure'); void(0);" class=sortedColumn style="text-decoration: underline;"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-uom", "UOM",false)%></a></td>
					<td width = 15% class=browseHdrDk nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-qty", "Invoice Qty.",false)%></td>
					<td width=15% class=browseHdrDk nowrap align=center>Unit Cost</td>
					<td width=20% class=browseHdrDk nowrap align=center>Ext. Cost</td>
					<td width=5% class=browseHdrDk nowrap>&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<table id="items" border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
<%
	boolean bCreatedFromPo = false;
	String	s_set_row = "";
	if (lineitems != null && lineitems.size() > 0)
	{
		String className = "summary";
		for (int i = 0; i < lineitems.size(); i++)
		{
			s_set_row = "ONFOCUS='setCurrentRow("+i+");'";
			if (className == "summary")
			{
				className="";
			}
			else
			{
				className = "summary";
			}
			InvoiceLine invoiceLine = (InvoiceLine) lineitems.get(i);
			bCreatedFromPo = false;
			if ((invoiceLine.getIcPoLine().compareTo(new BigDecimal(0)) > 0))
			{
				bCreatedFromPo = true;
			}
			boolean bAllowEdit = false;
			if (s_allow_itemdesc_edit.equalsIgnoreCase("Y") || !bCreatedFromPo)
			{
				bAllowEdit = true;
			}
			BigDecimal bdQuantity = invoiceLine.getQuantity();
			BigDecimal bdUnitPrice = invoiceLine.getUnitPrice();
			BigDecimal bdUmFactor = invoiceLine.getUmFactor();
			if (bdUmFactor.compareTo(new BigDecimal(0)) == 0)
			{
				bdUmFactor = new BigDecimal(1);
			}
			BigDecimal bdExtCost = HiltonUtility.getFormattedDollar(bdQuantity.multiply(bdUnitPrice).multiply(bdUmFactor), oid);
			if (s_curr_code.equals("JPY"))
			{
				bdExtCost = HiltonUtility.getFormattedDollar(bdExtCost.setScale(0, BigDecimal.ROUND_HALF_UP), oid);
			}
			String s_taxable = invoiceLine.getTaxable();
			String s_asset = invoiceLine.getAsset();
			
			boolean allowEditItem = true;
			
			if(awardedPO.equalsIgnoreCase("N") && invoiceLine.getQtyReceived().compareTo(new BigDecimal(0)) <= 0 && bCreatedFromPo) {
				allowEditItem = false;
			}
%>
				<tr><% if(i != (lineitems.size()-1)){ %>
					<td style="border-bottom-color:#666666; border-bottom-style:solid; border-width:2px;" >
					<% }else{ %>
					<td>
					<% } %>
						<table class="<%=className%>" >
						<tr height="25px">
							<td class="<%=className%>" align=right valign="bottom">
								<%=(i + 1)%>.
								<tsa:hidden name="InvoiceLine_invoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
								<tsa:hidden name="InvoiceLine_icIvcLine" value="<%=invoiceLine.getIcIvcLine()%>"/>
								<tsa:hidden name="InvoiceLine_icPoHeader" value="<%=invoiceLine.getIcPoHeader()%>"/>
								<tsa:hidden name="InvoiceLine_icPoLine" value="<%=invoiceLine.getIcPoLine()%>"/>
								<tsa:hidden name="InvoiceLine_icRelPoLine" value="<%= invoiceLine.getIcRelPoLine() %>"/>
							</td>
							<td class="<%=className%>" valign="bottom">
								<input type="text" name="InvoiceLine_itemNumber" value="<%=invoiceLine.getItemNumber()%>" size="20" maxlength="30" <% if (bCreatedFromPo) { %>READONLY DISABLED<% } %> onchange="upperCase(this);">
							</td>
							<td class="<%=className%>" valign="bottom">
								<input type="text" name="InvoiceLine_umCode" value="<%=invoiceLine.getUmCode()%>" size="10" maxlength="15" onblur="checkUMCode(<%=i%>)" onchange="upperCase(this); updateUMFactor(<%=i%>);" <%=s_set_row%> <%if(!allowEditItem) {%>READONLY DISABLED<%} %>>
								<tsa:hidden name="InvoiceLine_umFactor" value="<%=bdUmFactor%>"/>
							</td>
							<td class="<%=className%>" valign="bottom"><input type="text" name="InvoiceLine_quantity" value="<%=HiltonUtility.getFormattedQuantity(invoiceLine.getQuantity(), oid)%>" size="15" style="text-align:right" onchange="addUp(<%=i%>); checkQty(<%=i%>);" <%if(!allowEditItem) {%>READONLY DISABLED<%} %>></td>
							<td class="<%=className%>" valign="bottom"><input type="text" name="InvoiceLine_unitPrice" value="<%=HiltonUtility.getFormattedPrice(invoiceLine.getUnitPrice(), oid)%>" size="15" style="text-align:right" onchange="addUp(<%=i%>);" <%if(!allowEditItem) {%>READONLY DISABLED<%} %>></td>
							<td class="<%=className%>" valign="bottom"><input type="text" name="InvoiceLine_lineTotal" value="<%=bdExtCost%>" size="15" style="text-align:right" READONLY DISABLED tabIndex="-1"></td>
<%		if (bCreatedFromPo) { %>
							<td class="<%=className%>" width="18px" align="center" valign="bottom"><a href="javascript: displayArea('quantities_<%=i%>'); hideArea('qtyDetails_<%=i%>'); void(0);"><img id="qtyDetails_<%=i%>" src="<%=contextPath%>/images/cmd_maximize.gif" border=0 alt="More Details" tabIndex="-1"></a></td>
<%		} else { %>
							<td class="<%=className%>" width="18px" align="center" valign="middle"><a href="javascript: deleteMe(<%=i%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" border=0 alt="Delete This Item" tabIndex="-1"></a></td>
<%		} %>
						</tr>
						<tr height="25px">
							<td class="<%=className%>">&nbsp;</td>
							<td class="<%=className%>" colspan="4" valign="top">
								<input type="text" name="InvoiceLine_description" value="<%=HiltonUtility.encodeHtml(invoiceLine.getDescription())%>" size="74" maxlength="255" <% if (!bAllowEdit) { %>READONLY DISABLED<% } %> >
							</td>
							<td class="<%=className%>" colspan="2" rowspan="2">
								<table border=0 cellspacing=0 cellpadding=0 class="<%=className%>">
								<tr>
									<td>
										<input type="checkbox" name="c_checkbox" <% if (s_taxable.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(frm.InvoiceLine_taxable_<%=i%>, <%=i%>);">
										<tsa:hidden name="InvoiceLine_taxable_<%=i%>" value="<%=s_taxable%>"/>
									</td>
									<td>Tax</td>
									<td>
										<input type="checkbox" name="c_checkbox2" <% if (s_asset.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox2(frm.InvoiceLine_asset_<%=i%>, <%=i%>);">
										<tsa:hidden name="InvoiceLine_asset_<%=i%>" value="<%=s_asset%>"/>
									</td>
									<td>Asset</td>
									<td>&nbsp;</td>
									<td>
										<a href="javascript: retrieveAccount('<%=invoiceLine.getIcIvcLine()%>', <%=i + 1%>);" class="<%=className%>"><img src="<%=contextPath%>/images/cmd_edit.gif" border="0" tabIndex="-1" alt="Account Allocations"></a>
									</td>
									<td>
										<a href="javascript: retrieveAccount('<%=invoiceLine.getIcIvcLine()%>', <%=i + 1%>);" class="<%=className%>" title="Account Allocations" style="text-decoration: underline;">Accounts</a>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr height="25px">
						  <td class="<%=className%>">&nbsp;</td>
                          <td colspan="4" valign="top" class="<%=className%>">
							  <table class="<%=className%>">
	                            <tr <%=HtmlWriter.isVisible(oid, "ivc-udf1")%>>
	                              <td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-udf1", "Contracted",false)%></td>
	                              <td><input type="text" name="InvoiceLine_udf1Code" value="<%=HiltonUtility.encodeHtml(invoiceLine.getUdf1Code())%>" size="25" maxlength="15" <% if (!bAllowEdit) { %>READONLY DISABLED<% } %> ></td>
	                            </tr>
	                          </table>
						  </td>
                         </tr>
						</table>
<%			if (oid.equals("CTB08P")) { %>
						<div id="quantities_<%=i%>" style="visibility:visible; display:block;">
<%			} else { %>
						<div id="quantities_<%=i%>" style="visibility:hiden; display:none;">
<%			} %>

						<table border=0 cellspacing=0 cellpadding=0 width=100% class="<%=className%>">
						<tr "<%=className%>">
							<td width=100% height=18px class="<%=className%>">
								<table border=0 cellspacing=0 cellpadding=1 width=95% class="<%=className%>" align="center">
								<tr>
									<td nowrap class="<%=className%>">&nbsp;<b>Ordered</b></td>
									<td nowrap class="<%=className%>">&nbsp;<b>Unit Cost</b></td>
									<td nowrap class="<%=className%>">&nbsp;<b>Order Total</b></td>
									<td nowrap class="<%=className%>">&nbsp;<b>Received</b></td>
									<td nowrap class="<%=className%>">&nbsp;<b>Invoiced</b></td>
									<td nowrap class="<%=className%>">&nbsp;<b>Unit Cost</b></td>
									<td nowrap class="<%=className%>">&nbsp;<b>Total Invoiced</b></td>
									<td class="<%=className%>" align=right valign=bottom><a href="javascript: hideArea('quantities_<%=i%>'); displayArea('qtyDetails_<%=i%>'); void(0);"><img src="<%=contextPath%>/images/bar_close.gif" border="0" alt="Hide Details" tabindex="-1"></a></td>
								</tr>
								<tr>
									<td nowrap class="<%=className%>" align="center"><%=HiltonUtility.getFormattedQuantity(invoiceLine.getQtyOrdered(), oid)%></td>
									<td nowrap class="<%=className%>" align="center"><%=HiltonUtility.getFormattedPriceCurrency(invoiceLine.getOrderUnitCost(), s_curr_code, oid)%></td>
									<td nowrap class="<%=className%>" align="center"><%=HiltonUtility.getFormattedCurrency(HiltonUtility.getFormattedDollar(invoiceLine.getAmountOrdered(), oid), s_curr_code, oid)%></td>
									<td nowrap class="<%=className%>" align="center"><%=HiltonUtility.getFormattedQuantity(invoiceLine.getQtyReceived(), oid)%></td>
									<td nowrap class="<%=className%>" align="center"><%=HiltonUtility.getFormattedQuantity(invoiceLine.getQtyInvoiced(), oid)%></td>
									<td nowrap class="<%=className%>" align="center"><%=HiltonUtility.getFormattedPriceCurrency(invoiceLine.getInvoiceUnitCost(), s_curr_code, oid)%></td>
									<td nowrap class="<%=className%>" align="center"><%=HiltonUtility.getFormattedCurrency(HiltonUtility.getFormattedDollar(invoiceLine.getAmountInvoiced(), oid), s_curr_code, oid)%></td>
									<td class="<%=className%>">
										&nbsp;
										<tsa:hidden name="qtyOrdered" value="<%=HiltonUtility.getFormattedQuantity(invoiceLine.getQtyOrdered(), oid)%>"/>
										<tsa:hidden name="qtyReceived" value="<%=HiltonUtility.getFormattedQuantity(invoiceLine.getQtyReceived(), oid)%>"/>
										<tsa:hidden name="qtyInvoiced" value="<%=HiltonUtility.getFormattedQuantity(invoiceLine.getQtyInvoiced(), oid)%>"/>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						</table>
						</div>
					</td>
				</tr>
<%		} //end for
		} //end if
%>
				</table>
			</td>
		</tr>
		</table>
		<br>
		<table id=totalsTable border=0 cellspacing=0 cellpadding=0 width="520px" class=browseRowHdr>
		<tr>
			<td valign="top">
<%	if ((invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_INPROGRESS) == 0 || 
		invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_RECALLED) == 0) && !bCreatedFromPo) { %>
				<table border=0 cellspacing=1 cellpadding=1>
				<tr>
					<td nowrap><a href="javascript: addNew();"><img src="<%=contextPath%>/images/cmd_add_item.gif" border=0 alt="Add Item"></a></td>
					<td nowrap><a href="javascript: addNew();"><font class="reset"><b>Add New</b></font></a></td>
				</tr>
				</table>
<%	} %>
			</td>
			<td>
				<table border=0 cellspacing=0 cellpadding=0>
				<tr>
				<%	if (isInvCreatedFromPo) { %>
					<td width="25%" align="right"><b>PO Subtotal:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="poInvoiceSubtotal" value="<%=HiltonUtility.getCurrency(subtotal, s_curr_code, oid)%>" style="text-align:right" size="15" disabled></td>
				<%	} %>
					<td align="right"><b>Subtotal:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="InvoiceHeader_invoiceSubtotal" value="<%=HiltonUtility.getCurrency(invoiceHeader.getInvoiceSubtotal(), s_curr_code, oid)%>" style="text-align:right" size="15" READONLY></td>
					<td width="5%">&nbsp;</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-totalAmount")%>>
				<%	if (isInvCreatedFromPo) { %>
					<td width="25%" align="right"><b>PO Discount:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="poInvoiceDiscount" value="<%=HiltonUtility.getCurrency(discountAmount, s_curr_code, oid)%>" style="text-align:right" size="15" disabled></td>
				<%	} %>
					<% if (oid.equalsIgnoreCase("WPC08P")) { %>
					<td align="right">
						Apply Invoice Total Terms Discount:<input type="checkbox" name="c_checkbox3" value="Y" <% if (total_terms.equals("Y")) { %> CHECKED <% } %> onclick="setCheckbox34(this)">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<tsa:hidden name="InvoiceHeader_udf9Code" value="<%=total_terms %>"/>
						<b>Discount:&nbsp;</b>
					</td>
					<% } else { %>
					<td align="right"><b>Discount:&nbsp;</b></td>
					<% } %>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="InvoiceHeader_invoiceDiscount" value="<%=HiltonUtility.getCurrency(invoiceHeader.getInvoiceDiscount(), s_curr_code, oid)%>" style="text-align:right" size="15" onchange="calculateDiscount(); updateBalance();"><tsa:hidden name="discountPercent" value=""/></td>
					<td width="5%">&nbsp;</td>
				</tr>
			<% if (oid.equalsIgnoreCase("WPC08P")) { %>
				<td align="right">
					Apply Invoice Base Terms Discount:<input type="checkbox" name="c_checkbox4" value="Y" <% if (base_terms.equals("Y")) { %> CHECKED <% } %> onclick="setCheckbox34(this)">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<tsa:hidden name="InvoiceHeader_udf10Code" value="<%=base_terms %>"/>
					<b>Adjustment:&nbsp;</b>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="InvoiceHeader_invoiceAdjustment" value="<%=HiltonUtility.getCurrency(invoiceHeader.getInvoiceAdjustment(), s_curr_code, oid)%>" style="text-align:right" size="15" onchange="updateBalance();"></td>
					<td width="5%">&nbsp;</td>
				</td>
			<% } %>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-taxAmount")%>>
				<%	if (isInvCreatedFromPo) { %>
					<td width="25%" align="right"><b>PO Tax:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="poInvoiceTax" value="<%=HiltonUtility.getCurrency(taxAmount, s_curr_code, oid)%>" style="text-align:right" size="15" disabled></td>
				<%	} %>
					<td align="right"><b>Tax:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="InvoiceHeader_invoiceTax" value="<%=HiltonUtility.getCurrency(invoiceHeader.getInvoiceTax(), s_curr_code, oid)%>" style="text-align:right" size="15" onchange="updateBalance();"></td>
					<td width="5%">&nbsp;</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-useTax")%>>
				<%	if (isInvCreatedFromPo) { %>
					<td width="25%" align="right"><b>PO <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-useTax", "Use Tax", true)%>:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="poUseTax" value="<%=HiltonUtility.getCurrency(useTaxAmount, s_curr_code, oid)%>" style="text-align:right" size="15" disabled></td>
				<%	} %>
					<td align="right"><b>Use Tax:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="InvoiceHeader_useTax" value="<%=HiltonUtility.getCurrency(invoiceHeader.getUseTax(), s_curr_code, oid)%>" style="text-align:right" size="15" onchange="updateBalance();"></td>
					<td width="5%">&nbsp;</td>
				</tr>
				<tr>
				<%	if (isInvCreatedFromPo) { %>
					<td width="25%" align="right"><b>PO Shipping:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="poInvoiceShipping" value="<%=HiltonUtility.getCurrency(shippingTax, s_curr_code, oid)%>" style="text-align:right" size="15" disabled></td>
				<%	} %>
					<td align="right"><b>Shipping:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="InvoiceHeader_invoiceShipping" value="<%=HiltonUtility.getCurrency(invoiceHeader.getInvoiceShipping(), s_curr_code, oid)%>" style="text-align:right" size="15" onchange="updateBalance();"></td>
					<td width="5%">&nbsp;</td>
				</tr>
				<tr>
				<%	if (isInvCreatedFromPo) { %>
					<td width="25%" align="right"><b>PO Other:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="poInvoiceOther" value="<%=HiltonUtility.getCurrency(otherTax, s_curr_code, oid)%>" style="text-align:right" size="15" disabled></td>
				<%	} %>
					<td align="right"><b>Other:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="InvoiceHeader_invoiceOther" value="<%=HiltonUtility.getCurrency(invoiceHeader.getInvoiceOther(), s_curr_code, oid)%>" style="text-align:right" size="15" onchange="updateBalance();"></td>
					<td width="5%">&nbsp;</td>
				</tr>
				<tr>
				<%	if (isInvCreatedFromPo) { %>
					<td width="25%" align="right"><b>PO <%=DictionaryManager.getLabel(oid, "ivc-total", "Total")%>:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="poInvoiceTotal" value="<%=HiltonUtility.getCurrency(total, s_curr_code, oid)%>" style="text-align:right" size="15" disabled></td>
				<%	} %>
					<td align="right"><b><%=DictionaryManager.getLabel(oid, "ivc-total", "Total")%>:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="invoiceTotal" value="<%=HiltonUtility.getCurrency(invoiceHeader.getInvoiceTotal(), s_curr_code, oid)%>" style="text-align:right" size="15" onchange="updateBalance();"></td>
					<td width="5%">&nbsp;</td>
				</tr>
				<tr>
				<%	if (isInvCreatedFromPo) { %>
					<td colspan="5" align="right"><hr width="375px" align="right"></td>
				<%	} else { %>
					<td colspan="3" align="right"><hr width="375px" align="right"></td>
				<%	} %>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "invoiceHeader-reasoncode")%>>
				<%	if (isInvCreatedFromPo) { %>
					<td width="25%">&nbsp;</td>
				<%	} %>
					<td align="right" <% if (isInvCreatedFromPo) { %> colspan="2" <% } %>>
						<a href="javascript: browseStd('InvoiceHeader_reasonCode', 'RCOD'); void(0);"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-reason", "Reason") %>:</b></a>&nbsp;
						<input type="text" name="InvoiceHeader_reasonCode" value="<%=invoiceHeader.getReasonCode()%>" DISABLED READONLY size="20">
						&nbsp;&nbsp;<b>Rejecting:&nbsp;</b>
					</td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="InvoiceHeader_invoiceRejecting" value="<%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceRejecting(), oid)%>" style="text-align:right" size="15" onchange="updateBalance();"></td>
					<td width="5%">&nbsp;</td>
				</tr>
				<tr>
				<%	if (isInvCreatedFromPo) { %>
					<td width="25%">&nbsp;</td>
					<td width="15%">&nbsp;</td>
				<%	} %>
					<td align="right" class="error"><b>INVOICE BALANCE:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="invoiceBalance" value="<%=HiltonUtility.getCurrency(invoiceHeader.getInvoiceTotal(), s_curr_code,oid)%>" style="text-align:right" size="15" DISABLED READONLY></td>
					<td width="5%">&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
	<td rowspan=2 align="right" valign="top"><%@ include file="/invoice/iv_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<div style="visibility: hidden; display: none;">
<table>
<tr id="deleteitems"></tr>
</table>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var info_window = null;
	var populated = false;
	var uomArray = new Array();
	var currentRow = 0;

	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var price_dec = <%=Integer.valueOf(s_price_decimals).intValue()%>;
	var dollar_dec = <%=Integer.valueOf(s_dollar_decimals).intValue()%>;
	var quantity_dec = <%=Integer.valueOf(s_quantity_decimals).intValue()%>;
	var invoiceTotal_generalInfo = <%=invoiceHeader.getInvoiceTotal()%>;

<%	if (lineitems == null || lineitems.size() <= 0) { %>
			addNew();
<%	} %>
		updateTotals();
		calculateDiscount();
		<%	
		if (invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_APPROVING) >= 0 && !invoiceHeader.getStatus().equals(DocumentStatus.IVC_REJECTED)){
			%>
			checkInputSettings();
			allowEdit = false;
			<%
		}%>
		
	function thisLoad()
	{
		f_StartIt();
<%	if (invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_RECALLED) > 0) { %>
	<%	if (invoiceHeader.getStatus().equals(DocumentStatus.IVC_REJECTED) && user.getVchApp().equalsIgnoreCase("N")) { %>
			checkInputSettings();
			allowEdit = false;
	<%	} %>
<%	} %>
	}

	function addUp(row)
	{
		var qty_dec = <%=Integer.valueOf(s_quantity_decimals).intValue()%>;

		myTable = document.getElementById("items");
		count = myTable.rows.length;

		if (count == 1)
		{
			var p = nformat(eval(nfilter(frm.InvoiceLine_unitPrice)), price_dec);
			var q = nformat(eval(nfilter(frm.InvoiceLine_quantity)), qty_dec);
			var f = eval(nfilter(frm.InvoiceLine_umFactor));

			if (f == 0) { f = 1; }

			frm.InvoiceLine_umFactor.value = f;
			frm.InvoiceLine_unitPrice.value = p;
			frm.InvoiceLine_quantity.value = q;

			frm.InvoiceLine_lineTotal.value = nformat( p * q * f, dollar_dec );
			if ("<%=s_curr_code%>" == "JPY")
			{
				frm.InvoiceLine_lineTotal.value = nformat(Math.round(frm.InvoiceLine_lineTotal.value), dollar_dec);
			}
			/*
			if ( p == q && q == 0) {
				frm.InvoiceLine_unitPrice.value = '';
				frm.InvoiceLine_quantity.value = '';
				frm.InvoiceLine_lineTotal.value = '';
			}
			*/

			frm.InvoiceHeader_invoiceSubtotal.value = frm.InvoiceLine_lineTotal.value;
		}
		else if (count > 1)
		{
			var p = nformat(eval(nfilter(frm.InvoiceLine_unitPrice[row])), price_dec);
			var q = nformat(eval(nfilter(frm.InvoiceLine_quantity[row])), qty_dec);
			var f = eval(nfilter(frm.InvoiceLine_umFactor[row]));

			if (f == 0) { f = 1; }

			frm.InvoiceLine_umFactor[row].value = f;
			frm.InvoiceLine_unitPrice[row].value = p;
			frm.InvoiceLine_quantity[row].value = q;

			frm.InvoiceLine_lineTotal[row].value = nformat( p * q * f, dollar_dec );
			if ("<%=s_curr_code%>" == "JPY")
			{
				frm.InvoiceLine_lineTotal[row].value = nformat(Math.round(frm.InvoiceLine_lineTotal[row].value), dollar_dec);
			}
			/*
			if ( p == q && q == 0) {
				frm.InvoiceLine_unitPrice[row].value = '';
				frm.InvoiceLine_quantity[row].value = '';
				frm.InvoiceLine_lineTotal[row].value = '';
			}
			*/

			var subtotal = 0;
			for (var i = 0; i < count; i++)
			{
				subtotal = eval(subtotal) + eval(frm.InvoiceLine_lineTotal[i].value);
			}
			frm.InvoiceHeader_invoiceSubtotal.value = nformat(subtotal, dollar_dec);
		}
		updateBalance();
	}

	function addNew()
	{
		frm.deleteall.value = "FALSE";
		var myTable = document.getElementById("items");
		var count = myTable.rows.length;
		//var myRow = myTable.insertRow(count);
		var myRow = createRow(myTable);
		var style = "border-top-color:#666666; border-top-style:solid; border-width:2px;";
		if (count % 2 == 0)
		{
			classname = "";
		}
		else
		{
			classname = "summary";
		}
		//myCell = myRow.insertCell();
		myCell = createCell(myRow);
		if(count > 0){
			myCell.style.cssText = style;
		}
		myCell.innerHTML = "<table class=" + classname + "><tr height=\"25px\"><td id\"lineitem_" + count + "\" align=\"right\" class=" + classname + ">" + (count + 1) + ".<input type=\"hidden\" name=\"InvoiceLine_invoiceNumber\" value=\"<%=invoiceHeader.getInvoiceNumber()%>\">" +
			"<input type=\"hidden\" name=\"InvoiceLine_icIvcLine\" value=\"\">" +
			"<input type=\"hidden\" name=\"InvoiceLine_icPoHeader\" value=\"\"><input type=\"hidden\" name=\"InvoiceLine_icPoLine\" value=\"\"><input type=\"hidden\" name=\"InvoiceLine_icRelPoLine\" value=\"\"></td>" +
			"<td id=\"itemnumber\"><input type=\"text\" name=\"InvoiceLine_itemNumber\" value=\"\" size=\"20\" maxlength=\"30\" onchange=\"upperCase(this);\"></td>" +
			"<td id=\"umCode\"><input type=\"text\" name=\"InvoiceLine_umCode\" value=\"EA\" size=\"10\" maxlength=\"15\" onblur=\"checkUMCode(" + count + ");\" onchange=\"upperCase(this); updateUMFactor(" + count + ");\" onfocus=\"setCurrentRow(" + count + ");\"><input type=\"hidden\" name=\"InvoiceLine_umFactor\" value=\"1\"></td>" +
			"<td id=\"qty\"><input type=\"text\" name=\"InvoiceLine_quantity\" value=\"0.00\" size=\"15\" style=\"text-align:right\" onchange=\"addUp(" + count + ");\"></td>" +
			"<td id=\"price\"><input type=\"text\" name=\"InvoiceLine_unitPrice\" value=\"0.0000\" size=\"15\" style=\"text-align:right\" onchange=\"addUp(" + count + ");\"></td>" +
			"<td id=\"total\"><input type=\"text\" name=\"InvoiceLine_lineTotal\" value=\"0.0000\" size=\"15\" style=\"text-align:right\" READONLY tabIndex=\"-1\"></td>" +
			"<td id=\"details\" width=\"18px\" align=\"center\"><a href=\"javascript: deleteMe(" + count + "); void(0);\"><img src=\"<%=contextPath%>/images/delete.gif\" border=0 alt=\"Delete This Item\" tabIndex=\"-1\"></a></td>" +
			"</tr><tr height=\"25px\"><td>&nbsp;</td><td id=\"description\" colSpan=\"4\"><input type=\"text\" name=\"InvoiceLine_description\" value=\"\" size=\"73\" maxlength=\"255\"></td>" +
			"<tr height=\"25px\" <% if(!DictionaryManager.isVisible(oid, "ivc-udf1")){%>style=\"visibility:hidden; display:none;\"<%}%>><td>&nbsp;</td><td id=\"udf1\" colSpan=\"4\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-udf1", "Contracted",false)%><input type=\"text\" name=\"InvoiceLine_udf1Code\" value=\"\" size=\"25\" maxlength=\"15\"></td>" +
			"<td colSpan=\"2\"><a href=\"javascript: retrieveAccount('new');\" class=" + classname + "><img src=\"<%=contextPath%>/images/cmd_edit.gif\" border=0 alt=\"Account Allocations\" tabIndex=\"-1\"></a></td></tr></table>";
	}

	function deleteMe(row)
	{
		var myRow = document.getElementById("deleteitems");
		myCell = myRow.insertCell();

		if (frm.InvoiceLine_icIvcLine.value)
		{
			var newInputField = "<input type='hidden' name='deleteLine_lineIc' value='" + frm.InvoiceLine_icIvcLine.value + "'>";
		}
		else
		{
			var newInputField = "<input type='hidden' name='deleteLine_lineIc' value='" + frm.InvoiceLine_icIvcLine[row].value + "'>";
		}

		myCell.innerHTML = newInputField;

		var myTable = document.getElementById("items");
		var count = myTable.rows.length - 1;

		if (count > 0)
		{
			for ( var i = row; i < count; i++)
			{
				frm.InvoiceLine_icIvcLine[i].value = frm.InvoiceLine_icIvcLine[i + 1].value;
				frm.InvoiceLine_itemNumber[i].value = frm.InvoiceLine_itemNumber[i + 1].value;
				frm.InvoiceLine_umCode[i].value = frm.InvoiceLine_umCode[i + 1].value;
				frm.InvoiceLine_umFactor[i].value = frm.InvoiceLine_umFactor[i + 1].value;
				frm.InvoiceLine_quantity[i].value = frm.InvoiceLine_quantity[i + 1].value;
				frm.InvoiceLine_unitPrice[i].value = frm.InvoiceLine_unitPrice[i + 1].value;
				frm.InvoiceLine_lineTotal[i].value = frm.InvoiceLine_lineTotal[i + 1].value;
				frm.InvoiceLine_description[i].value = frm.InvoiceLine_description[i + 1].value;
				frm.InvoiceLine_udf1Code[i].value = frm.InvoiceLine_udf1Code[i + 1].value;
			}
		}
		myTable.deleteRow(count);
		if (count <= 0)
		{
			frm.deleteall.value = "TRUE";
		}
		updateTotals();
	}

	function updateBalance()
	{
		var myTable = document.getElementById("items");
		var count = myTable.rows.length;
		var invoiceTotal = <%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceTotal(), oid)%>;

		if (count == 0)
		{
			frm.InvoiceHeader_invoiceSubtotal.value = "0.00";
			frm.InvoiceHeader_invoiceDiscount.value = "0.00";
			frm.InvoiceHeader_invoiceTax.value = "0.00";
			if (frm.InvoiceHeader_useTax)
			{
				frm.InvoiceHeader_useTax.value = "0.00";
			}
			if (frm.InvoiceHeader_invoiceAdjustment)
			{
				frm.InvoiceHeader_invoiceAdjustment.value = "0.00";
			}
			frm.InvoiceHeader_invoiceShipping.value = "0.00";
			frm.InvoiceHeader_invoiceOther.value = "0.00";
			frm.InvoiceHeader_invoiceRejecting.value = "0.00";
			frm.invoiceTotal.value = "0.00";
			frm.invoiceBalance.value = invoiceTotal;
		}
		else
		{

			var subtotal = nformat(eval(nfilter(frm.InvoiceHeader_invoiceSubtotal)),dollar_dec);
			var discount = nformat(eval(nfilter(frm.InvoiceHeader_invoiceDiscount)),dollar_dec);
			var tax = nformat(eval(nfilter(frm.InvoiceHeader_invoiceTax)),dollar_dec);
			var usetax = 0;
			var adjustment = 0;
			if (frm.InvoiceHeader_useTax)
			{
				usetax = nformat(eval(nfilter(frm.InvoiceHeader_useTax)),dollar_dec);
			}
			if (frm.InvoiceHeader_invoiceAdjustment)
			{
				adjustment = nformat(eval(nfilter(frm.InvoiceHeader_invoiceAdjustment)),dollar_dec);
			}
			var shipping = nformat(eval(nfilter(frm.InvoiceHeader_invoiceShipping)),dollar_dec);
			var other = nformat(eval(nfilter(frm.InvoiceHeader_invoiceOther)),dollar_dec);
			var rejecting = nformat(eval(nfilter(frm.InvoiceHeader_invoiceRejecting)),dollar_dec);

			frm.InvoiceHeader_invoiceDiscount.value = discount;
			frm.InvoiceHeader_invoiceTax.value = tax;
			if (frm.InvoiceHeader_useTax)
			{
				frm.InvoiceHeader_useTax.value = usetax;
			}
			frm.InvoiceHeader_invoiceShipping.value = shipping;
			frm.InvoiceHeader_invoiceOther.value = other;
			frm.InvoiceHeader_invoiceRejecting.value = rejecting;
			if (frm.InvoiceHeader_invoiceAdjustment)
			{
				frm.InvoiceHeader_invoiceAdjustment.value = adjustment;
			}

			//frm.invoiceTotal.value = nformat( (eval(subtotal) - eval(discount) + eval(tax) + eval(usetax) + eval(shipping) + eval(other)), dollar_dec );
			frm.invoiceTotal.value = nformat( (eval(subtotal) - eval(discount) + eval(tax) + eval(usetax) + eval(shipping) + eval(other)), dollar_dec );

			if ( orgId == "WPC08P" )
			{
				frm.invoiceTotal.value = nformat( (eval(subtotal) - eval(discount) + eval(tax) + eval(usetax) + eval(shipping) + eval(other)), dollar_dec );
				rejecting = nformat( frm.invoiceTotal.value - eval(usetax) - eval(invoiceTotal), dollar_dec );
				frm.InvoiceHeader_invoiceRejecting.value = rejecting;
			}
			frm.invoiceBalance.value = nformat( eval(invoiceTotal) - ( frm.invoiceTotal.value ) - eval(rejecting), dollar_dec);
		}
	}

	function updateTotals()
	{
		var myTable = document.getElementById("items");
		var count = myTable.rows.length;
		var subtotal = 0;

		if (count == 1)
		{
			if (frm.InvoiceLine_lineTotal[0])
			{
				frm.InvoiceHeader_invoiceSubtotal.value = frm.InvoiceLine_lineTotal[0].value;
			}
			else
			{
				frm.InvoiceHeader_invoiceSubtotal.value = frm.InvoiceLine_lineTotal.value;
			}
		}
		else if (count > 1)
		{
			for (var i = 0; i < count; i++)
			{
				subtotal = eval(subtotal) + (eval(frm.InvoiceLine_lineTotal[i].value));
			}
			frm.InvoiceHeader_invoiceSubtotal.value = nformat(subtotal, dollar_dec);
		}

		if ( frm.c_checkbox3 && frm.c_checkbox3.checked )
		{
			frm.InvoiceHeader_invoiceDiscount.value = (frm.InvoiceHeader_termsDiscperc.value * invoiceTotal_generalInfo)/100 ;
		}
		if ( frm.c_checkbox4 && frm.c_checkbox4.checked )
		{
			frm.InvoiceHeader_invoiceDiscount.value = (frm.InvoiceHeader_termsDiscperc.value * frm.InvoiceHeader_invoiceSubtotal.value)/100 ;
		}
		if(!<%=applyDiscount%>)
		{
			frm.InvoiceHeader_invoiceDiscount.value = 0;
		}

		updateBalance();
	}

	function validateForm()
	{
		var myTable = document.getElementById("items");
		var count = myTable.rows.length;

		if(!checkUMAllCode()) return false;

		if (count > 1)
		{
			for (var i = count; i > 0; i--)
			{
				if (i == 1 && !frm.InvoiceLine_itemNumber[0])
				{
					if (frm.InvoiceLine_itemNumber.value.length <= 0 && frm.InvoiceLine_description.value.length <= 0)
					{
						deleteMe(i - 1);
					}
				}
				else
				{
					if (frm.InvoiceLine_itemNumber[i - 1].value.length <= 0 && frm.InvoiceLine_description[i - 1].value.length <= 0)
					{
						deleteMe(i - 1);
					}
				}
			}
		}
		return true;
	}

	function retrieveAccount(ic, row)
	{
		if (ic == "new")
		{
			alert("You must save before you can add Account Information to this item.");
		}
		else
		{
			frm.originalAccount_icLine.value = ic;
			frm.lineNumber.value = row;
			frm.formType.value = "IVL";

			doSubmit("/invoice/iv_accounts_ln.jsp", "InvoiceLineRetrieveAccount");
		}
	}

	function checkQty(row)
	{
		var myTable = document.getElementById("items");
		var count = myTable.rows.length;
		var qtyOrdered = 0;
		var qtyInvoiced = 0;
		var qtyRemaining = 0;
		var quantity = 0;
		var subType = "<%=poSubType%>";

		if (count == 1)
		{
			qtyOrdered = frm.qtyOrdered.value;
			if (eval(qtyOrdered > 0))
			{
				quantity = frm.InvoiceLine_quantity.value;
				if ((subType == '21' || subType == '01') && eval(quantity) > (eval(qtyOrdered) * 1.1))
				{
					alert("Please note that the quantity you entered is greater than the 10% tolerance for the quantity ordered.");
					return;
				}
				else if((subType != '21' && subType != '01') && eval(quantity) > eval(qtyOrdered))
				{
					alert("Please note that the quantity you entered is greater than the quantity ordered.");
					return;
				}
				qtyInvoiced = frm.qtyInvoiced.value;
				qtyRemaining = eval(qtyOrdered) - eval(qtyInvoiced);

				if ((subType != '21' && subType != '01') && eval(quantity) > eval(qtyRemaining))
				{
					alert("Please note that the quantity you entered is greater than the remaining balance.");
				}
			}
		}
		else if (count > 1)
		{
			qtyOrdered = frm.qtyOrdered[row].value;
			if (eval(qtyOrdered > 0))
			{
				quantity = frm.InvoiceLine_quantity[row].value;
				if ((subType != "21"  && subType != "01") && eval(quantity) > (eval(qtyOrdered) * 1.1))
				{
					alert("Please note that the quantity you entered is greater than the 10% tolerance for the quantity ordered.");
					return;
				}
				else if(eval(quantity) > eval(qtyOrdered))
				{
					alert("Please note that the quantity you entered is greater than the quantity ordered.");
					return;
				}
				qtyInvoiced = frm.qtyInvoiced[row].value;
				qtyRemaining = eval(qtyOrdered) - eval(qtyInvoiced);
				if ((subType != '21' && subType != '01') && eval(quantity) > eval(qtyRemaining))
				{
					alert("Please note that the quantity you entered is greater than the remaining balance.");
				}
			}
		}
	}

	function updateUMFactor(row)
	{
		var open = true;
		var browser = browserTest();
		var factor = "";
		var code = "";

		if (frm.InvoiceLine_umCode[row])
		{
			frm.InvoiceLine_umCode[row].value = trim(frm.InvoiceLine_umCode[row]);
		}
		else
		{
			frm.InvoiceLine_umCode.value = trim(frm.InvoiceLine_umCode);
		}

		if (info_window != null) {
			if (browser == "Netscape") {
				if (info_window.closed == false) {
					info_window.setUomCode("InvoiceLine_");
					open = false;
				}
			}
			else {
				info_window.setUomCode("InvoiceLine_");
				open = false;
			}
		}

		if (open == true)
		{
			if (uomArray.length > 0 || populated)
			{
				if (frm.InvoiceLine_umCode[row])
				{
					code = frm.InvoiceLine_umCode[row].value;
				}
				else
				{
					code = frm.InvoiceLine_umCode.value;
				}
				for (var i = 0; i < uomArray.length; i++)
				{
					if (code == (uomArray[i][0]).toString())
					{
						factor = uomArray[i][1];
						break;
					}
				}

				setUmFactor(factor, row);
			}
			else
			{
				popupParameters = "as_prefix=InvoiceLine_;as_row=" + row;

				setLookupParameters('/base/get_uom_info.jsp', 'UnitOfMeasureRetrieveAll');
				document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
			}
		}
	}

	function setUmFactor(factor, row)
	{
		if (frm.InvoiceLine_umFactor[row])
		{
			frm.InvoiceLine_umFactor[row].value = factor;
		}
		else
		{
			frm.InvoiceLine_umFactor.value = factor;
		}
		addUp(row);
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

	function setCurrentRow(row) {
		currentRow = row;
	}

	function payBalance() {
<%	if (lineitems != null) {%>
			var count = <%=lineitems.size()%>;
			var qtyOrdered = 0;
			var qtyReceived = 0;
			var qtyInvoiced = 0;
			var qtyRemaining = 0;
			var bReceiving = false;

<%		if (propertiesManager.isModuleActive("RECEIVING")) { %>
				bReceiving = true;
<%		} %>

			if (count == 1)
			{
				if (frm.InvoiceLine_quantity[0])
				{
					qtyOrdered = frm.qtyOrdered.value;
					qtyReceived = frm.qtyReceived.value;
					qtyInvoiced = frm.qtyInvoiced.value;
					if (bReceiving)
					{
						qtyRemaining = eval(qtyReceived) - eval(qtyInvoiced);
					}
					else
					{
						qtyRemaining = eval(qtyOrdered) - eval(qtyInvoiced);
					}
					<% if (!oid.equalsIgnoreCase("HOY08P")) { %>
						if (eval(qtyRemaining) < 0)
						{
							qtyRemaining = 0;
						}
					<% } %>
					frm.InvoiceLine_quantity[0].value = nformat(qtyRemaining, quantity_dec);
					addUp(0);
				}
				else
				{
					qtyOrdered = frm.qtyOrdered.value;
					qtyReceived = frm.qtyReceived.value;
					qtyInvoiced = frm.qtyInvoiced.value;
					if (bReceiving)
					{
						qtyRemaining = eval(qtyReceived) - eval(qtyInvoiced);
					}
					else
					{
						qtyRemaining = eval(qtyOrdered) - eval(qtyInvoiced);
					}
					<% if (!oid.equalsIgnoreCase("HOY08P")) { %>
						if (eval(qtyRemaining) < 0)
						{
							qtyRemaining = 0;
						}
					<% } %>
					frm.InvoiceLine_quantity.value = nformat(qtyRemaining, quantity_dec);
					addUp(0);
				}
			}
			else if (count > 1)
			{
				for (var i = 0; i < count; i++)
				{
					qtyOrdered = frm.qtyOrdered[i].value;
					qtyReceived = frm.qtyReceived[i].value;
					qtyInvoiced = frm.qtyInvoiced[i].value;
					if (bReceiving)
					{
						qtyRemaining = eval(qtyReceived) - eval(qtyInvoiced);
					}
					else
					{
						qtyRemaining = eval(qtyOrdered) - eval(qtyInvoiced);
					}
					<% if (!oid.equalsIgnoreCase("HOY08P")) { %>
						if (eval(qtyRemaining) < 0)
						{
							qtyRemaining = 0;
						}
					<% } %>
					frm.InvoiceLine_quantity[i].value = nformat(qtyRemaining, quantity_dec);
					addUp(i);
				}
			}
<%	} %>
	}

	function calculateDiscount()
	{
		var subtotal = eval(nfilter(frm.InvoiceHeader_invoiceSubtotal));
		var discount = eval(nfilter(frm.InvoiceHeader_invoiceDiscount));
		var percent = 0;
		if (subtotal != 0)
		{
			percent = discount / subtotal;
		}
		frm.discountPercent.value = percent;
	}

	function setCheckbox2(fld,x)
	{
		fld.value = 'N';
		if (x == 0 && !frm.c_checkbox2[x])
		{
			if ( frm.c_checkbox2.checked )
			{
				fld.value = 'Y';
			}
		}
		else if ( frm.c_checkbox2[x].checked )
		{
			fld.value = 'Y';
		}
		return true;
	}

	function setCheckbox34(x)
	{
		if (x.name == 'c_checkbox3')
		{
			if ( frm.c_checkbox3 && frm.c_checkbox3.checked )
			{
				if (frm.c_checkbox4) { frm.c_checkbox4.checked = false; }
				frm.InvoiceHeader_udf9Code.value = 'Y';
				frm.InvoiceHeader_udf10Code.value = 'N';
			}
		}
		if (x.name == 'c_checkbox4')
		{
			if ( frm.c_checkbox4 && frm.c_checkbox4.checked )
			{
				if (frm.c_checkbox3) { frm.c_checkbox3.checked = false; }
				frm.InvoiceHeader_udf9Code.value = 'N';
				frm.InvoiceHeader_udf10Code.value = 'Y';
			}
		}
		updateTotals();
	}

	function checkUMAllCode()
	{
		if (frm.InvoiceLine_umCode && frm.InvoiceLine_umCode.value == '') {
			alert('UM should not be blank');
			return false;
		}
		else if (frm.InvoiceLine_umCode.length > 1){
			for( var i=0; i<frm.InvoiceLine_umCode.length;i++)
			{
				if(frm.InvoiceLine_umCode[i] && frm.InvoiceLine_umCode[i].value == '')
				{
					alert('UM should not be blank');
					return false;
				}
			}
		}
		return true;
	}

	function checkUMCode(row)
	{
		if (frm.InvoiceLine_umCode[row] && frm.InvoiceLine_umCode[row].value == '') {
			alert('UM should not be blank');
		} else if (frm.InvoiceLine_umCode && frm.InvoiceLine_umCode.value == '') {
			alert('UM should not be blank');
		}


	}

	function showAllDetails()
	{
		setInnerHTML("allDetails",
			"<a href=\"javascript: hideAllDetails(); void(0);\"><img id=\"showAllDetailsImg\" border=\"0\"></a>" +
			"<a href=\"javascript: hideAllDetails(); void(0);\">Reduce All</a>");
		document.getElementById("showAllDetailsImg").src = "<%=contextPath%>/images/cmd_minimize.gif";

		var count = <%=lineitems.size()%>;

		for (var i = 0; i < count; i++) {
			displayArea('quantities_' + i); hideArea('qtyDetails_' + i);
		}
	}

	function hideAllDetails()
	{
		setInnerHTML("allDetails",
			"<a href=\"javascript: showAllDetails(); void(0);\"><img id=\"showAllDetailsImg\" border=\"0\"></a>" +
			"<a href=\"javascript: showAllDetails(); void(0);\">Expand All</a>");
		document.getElementById("showAllDetailsImg").src = "<%=contextPath%>/images/cmd_maximize.gif";

		var count = <%=lineitems.size()%>;

		for (var i = 0; i < count; i++) {
			hideArea('quantities_' + i); displayArea('qtyDetails_' + i);
		}
	}

// End Hide script -->
</SCRIPT>