<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.math.*" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%pageContext.setAttribute("oid", oid); %>
<%pageContext.setAttribute("language", language); %>
<iframe id="getInfoFrame" name="getInfoFrame" src="<%=contextPath%>/system/processing.jsp" frameborder="0" marginheight="0" marginwidth="0" style="position: absolute; border: 1px solid #C0C0C0; display: none; visibility: hidden;"></iframe>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dynamicTables.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/req_totals.js"></SCRIPT>

<%
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	refresh = (String) request.getAttribute("refreshOpener");
	String	s_req_status = requisitionHeader.getStatus();
	BigDecimal	bd_subtotal = requisitionHeader.getSubtotal().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	BigDecimal	bd_total = requisitionHeader.getTotal().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	BigDecimal	bd_tax_percent = requisitionHeader.getTaxPercent().setScale(5, BigDecimal.ROUND_HALF_UP);
	BigDecimal	bd_tax_amount = requisitionHeader.getTaxAmount().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	BigDecimal	bd_shipping_charges = requisitionHeader.getShippingCharges().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	BigDecimal	bd_shipping_tax_amt = requisitionHeader.getShippingTaxAmt().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	BigDecimal	bd_other_charges = requisitionHeader.getOtherCharges().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	BigDecimal	bd_other_tax_amount = requisitionHeader.getOtherTaxAmount().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	BigDecimal	bd_discount_percent = requisitionHeader.getDiscountPercent().setScale(2, BigDecimal.ROUND_HALF_UP);
	BigDecimal	bd_discount_amount = requisitionHeader.getDiscountAmount().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	String	s_tax_code = requisitionHeader.getTaxCode();
	String	s_tax_shipping = requisitionHeader.getTaxShipping();
	String	s_tax_other = requisitionHeader.getTaxOther();
	String	s_other_charg_desc = requisitionHeader.getOtherChargDesc();

	if (refresh == null)
		refresh = "N";

	//String  s_reason = request.getParameter("reason");
	String  s_reason = (String) request.getAttribute("reason");
	String  s_title = "";

	if (s_reason == null) {	s_reason = "";	}

	if (s_reason.equalsIgnoreCase("discount"))
	{
		s_title = "Discount Amounts";
	}
	else if (s_reason.equalsIgnoreCase("tax"))
	{
		s_title = "Tax Amounts";
	}
	else if (s_reason.equalsIgnoreCase("shipping"))
	{
		s_title = "Shipping Charges";
	}
	else if (s_reason.equalsIgnoreCase("other"))
	{
		s_title = "Other Charges";
	}
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=requisitionHeader.getIcReqHeader()%>"/>
<tsa:hidden name="refreshOpener" value="<%=refresh%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<%  int i_rowcount = 0;

	BigDecimal	bd_line_quantity;
	BigDecimal	bd_line_unit_price;
	BigDecimal	bd_line_subtotal;
	BigDecimal	bd_line_total;
	BigDecimal	bd_line_um_factor; 
	List lineList = (List) requisitionHeader.getRequisitionLineList();
%>
<table cellpadding="0" cellspacing="0" border="0">
<tsa:tr><tsa:td><br></tsa:td></tsa:tr>
<tsa:tr>
	<tsa:td width="100%">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tsa:tr>
			<tsa:td valign="top" width="50px" height="30px">
				<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
				<tsa:tr>
					<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
						<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><%=s_title%></div>
					</tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
			<tsa:td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
			<tsa:td valign="bottom" align="right" height="30px" width="100%">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tsa:tr>
					<tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
<tsa:tr>
	<tsa:td>
		<table border="0" cellpadding="1" cellspacing="2" width="600">
		<tsa:tr>
			<tsa:td colspan="2">&nbsp;</tsa:td>

<% 	if (s_reason.equals("discount")) { %>
						<tsa:td field="req-discountPercent" cssClass="mnav" height="18px" noWrap="nowrap" align="center"><tsa:label labelName="req-discountPercent" defaultString="Discount %"/></tsa:td>
						<tsa:td field="req-discountAmount" cssClass="mnav" height="18px" noWrap="nowrap" align="center"><tsa:label labelName="req-discountAmount" defaultString="Discount"/></tsa:td>
<%	}
		if (s_reason.equals("tax")) { %>
						<tsa:td field="req-taxCode" cssClass="mnav" height="18px" noWrap="nowrap" align="center"><tsa:label labelName="req-taxCode" defaultString="Tax Code"/></tsa:td>
						<tsa:td field="req-taxPercent" cssClass="mnav" height="18px" noWrap="nowrap" align="center"><tsa:label labelName="req-taxPercent" defaultString="Tax %"/></tsa:td>
						<tsa:td field="req-taxAmount" cssClass="mnav" height="18px" noWrap="nowrap" align="center"><tsa:label labelName="req-taxAmount" defaultString="Tax"/></tsa:td>
<%	}
		if (s_reason.equals("shipping")) { %>
						<tsa:td field="req-shippingCharges"  cssClass="mnav" height="18px" noWrap="nowrap" align="center"><tsa:label labelName="req-shippingCharges" defaultString="Shipping"/></tsa:td>
						<tsa:td field="req-shippingTaxAmount" cssClass="mnav" height="18px" noWrap="nowrap" align="center"><tsa:label labelName="req-shippingTaxAmount" defaultString="Shipping Tax"/></tsa:td>
<%	}
		if (s_reason.equals("other")) { %>
						<tsa:td field="req-otherCharges" cssClass="mnav" height="18px" noWrap="nowrap" align="center"><tsa:label labelName="req-otherCharges" defaultString="Other"/></tsa:td>
						<tsa:td field="req-otherTaxAmount" cssClass="mnav" height="18px" noWrap="nowrap" align="center"><tsa:label labelName="req-otherTaxAmount" defaultString="Other Tax"/></tsa:td>
						<tsa:td field="req-otherDescription" cssClass="mnav" height="18px" noWrap="nowrap" align="center"><tsa:label labelName="req-otherDescription" defaultString="Other Description"/></tsa:td>
<%	} %>
		</tsa:tr>
		<tsa:tr>
				<tsa:td colspan="2">
					<TABLE CELLPADDING="0" CELLSPACING="0">
<%	if (s_reason.equals("shipping")) { %>
					<tsa:tr field="req-shippingTaxable">
						<tsa:td noWrap="nowrap" cssClass="otab">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tsa:label labelName="req-shippingTaxable" defaultString="Shipping Taxable"/>:<tsa:hidden name="RequisitionHeader_taxShipping" value="<%=s_tax_shipping%>"/>
						<%if (s_tax_shipping.equals("Y")) { %>
							<tsa:input type="checkbox" name="c_checkbox" checked="CHECKED" value="Y" onclick="setCheckbox(RequisitionHeader_taxShipping); calculateShipTax();"/>
						<%}else {%>
							<tsa:input type="checkbox" name="c_checkbox" onclick="setCheckbox(RequisitionHeader_taxShipping); calculateShipTax();"/>
						<%} %>
						</tsa:td>						
					</tsa:tr>
<%	}
		else
		{ %>
					<tsa:hidden name="RequisitionHeader_taxShipping" value="<%=s_tax_shipping%>"/>
<%	}
		if (s_reason.equals("other")) { %>
					<tsa:tr field="req-otherTaxable">
						<tsa:td noWrap="nowrap" cssClass="otab">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tsa:label labelName="req-otherTaxable" defaultString="Other Taxable"/>:<tsa:hidden name="RequisitionHeader_taxOther" value="<%=s_tax_other%>"/>
						<%if (s_tax_other.equals("Y")) { %>
							<tsa:input type="checkbox" name="c_checkbox" checked="CHECKED" value="Y" onclick="setCheckbox(RequisitionHeader_taxOther); calculateOtherTax();"/>
						<%}else { %>
							<tsa:input type="checkbox" name="c_checkbox" onclick="setCheckbox(RequisitionHeader_taxOther); calculateOtherTax();"/>
						<%} %>
						</tsa:td>
					</tsa:tr>
<%	}
		else
		{ %>
					<tsa:hidden name="RequisitionHeader_taxOther" value="<%=s_tax_other%>"/>
<%	} %>
					<tsa:tr field="req-subtotal" cssClass="otab" height="18px">
						<tsa:td cssClass="otab"><B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tsa:label labelName="req-subtotal" defaultString="Subtotal"/>: </B><%=bd_subtotal%><tsa:hidden name="RequisitionHeader_subtotal" value="<%=bd_subtotal%>"/></tsa:td>
					</tsa:tr>
					</TABLE>
				</tsa:td>
<%	if (s_reason.equals("discount")) { %>
				<tsa:td field="req-discountPercent" valign="CENTER" align="CENTER">
					<tsa:input type="text" name="RequisitionHeader_discountPercent" value="<%=bd_discount_percent%>" style="text-align: right" size="8" onchange="changeHeaderAmount(RequisitionHeader_discountPercent);"/>%
				</tsa:td>
				<tsa:td field="req-discountAmount" valign="CENTER" align="CENTER">
					<tsa:input type="text" name="RequisitionHeader_discountAmount" value="<%=bd_discount_amount%>" style="text-align: right" size="10"  onchange="distributeDiscount();"/>
				</tsa:td>
<%	} else { %>
				<tsa:hidden name="RequisitionHeader_discountPercent" value="<%=bd_discount_percent%>"/>
				<tsa:hidden name="RequisitionHeader_discountAmount" value="<%=bd_discount_amount%>"/>
<% 	}
		if (s_reason.equals("tax")) { %>
				<tsa:td field="req-taxCode" valign="CENTER" align="CENTER" noWrap="nowrap">
					<A HREF="javascript: browseLookup('RequisitionHeader_taxCode', 'taxcode'); void(0);"><IMG SRC="<%=contextPath%>/images/browser.gif" BORDER="0" ALT="Tax Codes"></A>
					<tsa:input type="text" name="RequisitionHeader_taxCode" value="<%=s_tax_code%>" size="10" onchange="upperCase(this); browseMeTax(frm.RequisitionHeader_taxCode,'TAX', '0');"/>
				</tsa:td>
				<tsa:td field="req-taxPercent" valign="CENTER" align="CENTER"><tsa:input type="text" name="RequisitionHeader_taxPercent" value="<%=bd_tax_percent%>" style="text-align: right" size="9" onchange="changeHeaderAmount(RequisitionHeader_taxPercent);"/>%</tsa:td>
				<tsa:td field="req-taxAmount" valign="CENTER" align="CENTER"><tsa:input type="text" name="RequisitionHeader_taxAmount" value="<%=bd_tax_amount%>" style="text-align: right" size="10" onchange="distributeTax();" onfocus="setHeaderFocus(this);"/></tsa:td>
<%	} else { %>
				<tsa:hidden name="RequisitionHeader_taxCode" value="<%=s_tax_code%>"/>
				<tsa:hidden name="RequisitionHeader_taxPercent" value="<%=bd_tax_percent%>"/>
				<tsa:hidden name="RequisitionHeader_taxAmount" value="<%=bd_tax_amount%>"/>
<%	}
		if (s_reason.equals("shipping")) { %>
				<tsa:td field="req-shippingCharges" valign="CENTER" align="CENTER"><tsa:input type="text" name="RequisitionHeader_shippingCharges" value="<%=bd_shipping_charges%>" style="text-align: right" size="10" onchange="distributeShipping();"/></tsa:td>
				<tsa:td field="req-shippingTaxAmount" valign="CENTER" align="CENTER"><tsa:input type="text" name="RequisitionHeader_shippingTaxAmt" value="<%=bd_shipping_tax_amt%>" style="text-align: right" size="10" onchange="distributeShipTax();" onfocus="setHeaderFocus(this);"/></tsa:td>
<%	} else { %>
				<tsa:hidden name="RequisitionHeader_shippingCharges" value="<%=bd_shipping_charges%>"/>
				<tsa:hidden name="RequisitionHeader_shippingTaxAmt" value="<%=bd_shipping_tax_amt%>"/>
<%	}
		if (s_reason.equals("other")) { %>
				<tsa:td field="req-otherCharges" valign="CENTER" align="CENTER"><tsa:input type="text" name="RequisitionHeader_otherCharges" value="<%=bd_other_charges%>" style="text-align: right" size="10" onchange="distributeOther();"/></tsa:td>
				<tsa:td field="req-otherTaxAmount" valign="CENTER" align="CENTER"><tsa:input type="text" name="RequisitionHeader_otherTaxAmount" value="<%=bd_other_tax_amount%>" style="text-align: right" size="10" onchange="distributeOtherTax();" onfocus="setHeaderFocus(this);"/></tsa:td>
				<tsa:td field="req-otherDescription" valign="CENTER" align="CENTER"><tsa:input type="text" name="RequisitionHeader_otherChargDesc" value="<%=s_other_charg_desc%>" size="25" onchange="copyOtherDesc();" maxLength="30"/></tsa:td>
<%
		} else { %>
				<tsa:hidden name="RequisitionHeader_otherCharges" value="<%=bd_other_charges%>"/>
				<tsa:hidden name="RequisitionHeader_otherTaxAmount" value="<%=bd_other_tax_amount%>"/>
				<tsa:hidden name="RequisitionHeader_otherChargDesc" value="<%=s_other_charg_desc%>"/>
<%	} %>
				<tsa:hidden name="RequisitionHeader_total" value="<%=bd_total%>"/>
				<tsa:hidden name="RequisitionHeader_bidWaiver" value="<%//=s_waiver%>"/>
		</tsa:tr>
<%	if (s_reason.equals("tax") || s_reason.equals("other"))
		{ %>
		<tsa:tr cssClass="htable"><tsa:td colspan="5"><IMG SRC="<%=contextPath%>/images/none.GIF"  HEIGHT="5" ALIGN="TOP" BORDER="0"></tsa:td></tsa:tr>
<%	}
		else
		{ %>
		<tsa:tr cssClass="htable"><tsa:td colspan="4"><IMG SRC="<%=contextPath%>/images/none.GIF" HEIGHT="5" ALIGN="TOP" BORDER="0"></tsa:td></tsa:tr>
<%	}	
	if (lineList != null)
	{
		for (int i = 0; i < lineList.size(); i++)
		{
			RequisitionLine reqLine = (RequisitionLine) lineList.get(i);

			String	s_item_number = reqLine.getItemNumber();
			String	s_description = reqLine.getDescription();
			String	s_line_taxable = reqLine.getTaxable();
			String	s_line_tax_code = reqLine.getTaxCode();
			String	s_tax_ovr = reqLine.getTaxOvr();
			String	s_disc_ovr = reqLine.getDiscOvr();
			String	s_ship_ovr = reqLine.getShipOvr();
			String	s_other_ovr = reqLine.getOtherOvr();
			String	s_status = reqLine.getStatus();
			String	s_line_other_description = reqLine.getOtherDescription();

			BigDecimal	bd_ic_req_line = reqLine.getIcReqLine();
			BigDecimal	bd_line_number = reqLine.getLineNumber().setScale(1, BigDecimal.ROUND_HALF_UP);
			bd_line_quantity = reqLine.getQuantity();
			bd_line_unit_price = reqLine.getUnitPrice();
			bd_line_subtotal = HiltonUtility.getFormattedDollar(bd_line_quantity.multiply(bd_line_unit_price), oid);

			bd_line_total = reqLine.getLineTotal();
			BigDecimal	bd_line_tax_percent = reqLine.getTaxPercent().setScale(5, BigDecimal.ROUND_HALF_UP);
			BigDecimal	bd_line_tax_amount = reqLine.getTaxAmount().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
			BigDecimal	bd_line_shipping_charges = reqLine.getShippingCharges().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
			BigDecimal	bd_line_shipping_tax_amt = reqLine.getShippingTaxAmt().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
			BigDecimal	bd_line_discount_percent = reqLine.getDiscountPercent().setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal	bd_line_discount_amount = reqLine.getDiscountAmount().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
			BigDecimal	bd_line_other_charges = reqLine.getOtherCharges().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
			BigDecimal	bd_line_other_tax_amount = reqLine.getOtherTaxAmount().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);

			if (s_item_number == null)				{	s_item_number = "";					}
			if (s_description == null)					{	s_description = "";					}
			if (s_line_tax_code == null)				{	s_line_tax_code = "";				}
			if (s_line_other_description == null)	{	s_line_other_description = "";		}
			if (s_status == null)						{	s_status = "";							}
%>
		<tsa:tr cssClass="otab">
			<tsa:td cssClass="otab" valign="top">
				<FONT FACE="Arial Black"><%=bd_line_number%></FONT>
				<tsa:hidden name="RequisitionLine_icReqLine" value="<%=bd_ic_req_line%>"/>
			</tsa:td>
			<tsa:td>
				<TABLE CELLPADDING="0" CELLSPACING="0">
				<tsa:tr>
					<tsa:td cssClass="otab"><%=s_item_number%></tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td width="150px" cssClass="otab" noWrap="nowrap"><%=s_description%></tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td cssClass="otab">
<%		if (s_status.compareTo(DocumentStatus.CANCELLED) == 0)
			{ %>
						<FONT COLOR="red"><tsa:label labelName="cancelled" defaultString="Cancelled"/></FONT>
<%		}
			else
			{
				if (s_line_taxable.equals("Y"))
				{ %>
						<tsa:label labelName="taxable" defaultString="Taxable"/>
<%			}
				else
				{ %>
						<tsa:label labelName="nonTaxable" defaultString="Non-Taxable"/>
<%			} %>
						&nbsp;&nbsp;&nbsp;&nbsp;<%=bd_line_subtotal%>
<%		} %>
						<tsa:hidden name="RequisitionLine_taxable" value="<%=s_line_taxable%>"/>
						<tsa:hidden name="computed_subtotal" value="<%=bd_line_subtotal%>"/>
						<tsa:hidden name="RequisitionLine_taxOvr" value="<%=s_tax_ovr%>"/>
						<tsa:hidden name="RequisitionLine_discOvr" value="<%=s_disc_ovr%>"/>
						<tsa:hidden name="RequisitionLine_shipOvr" value="<%=s_ship_ovr%>"/>
						<tsa:hidden name="RequisitionLine_otherOvr" value="<%=s_other_ovr%>"/>
						<tsa:hidden name="RequisitionLine_status" value="<%=s_status%>"/>
					</tsa:td>
				</tsa:tr>
				</TABLE>
			</tsa:td>
<%	if (s_reason.equals("discount")) { %>
			<tsa:td field="req-discountPercent" valign="CENTER" align="CENTER"><tsa:input type="text" name="RequisitionLine_discountPercent" value="<%=bd_line_discount_percent%>" style="text-align: right" size="8" onchange="changeLineAmount(RequisitionLine_discountPercent,<%=i_rowcount%>);" onfocus="setLineFocus(RequisitionLine_discountPercent,<%=i_rowcount%>);"/>%</tsa:td>
			<tsa:td field="req-discountAmount" valign="CENTER" align="CENTER"><tsa:input type="text" name="RequisitionLine_discountAmount" value="<%=bd_line_discount_amount%>" style="text-align: right" size="10" onchange="changeLineAmount(RequisitionLine_discountAmount,<%=i_rowcount%>);" onfocus="setLineFocus(RequisitionLine_discountAmount,<%=i_rowcount%>);"/></tsa:td>
<%	} else { %>
			<tsa:hidden name="RequisitionLine_discountPercent" value="<%=bd_line_discount_percent%>"/>
			<tsa:hidden name="RequisitionLine_discountAmount" value="<%=bd_line_discount_amount%>"/>
<%	}
		if (s_reason.equals("tax"))
		{ %>
			<tsa:td field="req-taxCode" valign="CENTER" align="CENTER" noWrap="nowrap">
<%
			int	i_ind = 0;
			//s_taxcode_opt = "";

			if (s_line_taxable.equalsIgnoreCase("Y"))
			{
%>			<A HREF="javascript: void(0);" ONCLICK="browseLookup('RequisitionLine_taxCode[<%=i_rowcount%>]', 'taxcode'); setRow(<%=i_rowcount%>);"><IMG SRC="<%=contextPath%>/images/browser.gif" BORDER="0" ALT="Tax Codes"></A>
<%		} else { %>
				&nbsp;&nbsp;
<%		} %>
				<%if (!s_line_taxable.equals("Y")){ %>
					<tsa:input type="text" name="RequisitionLine_taxCode" value="<%=s_line_tax_code%>" size="10" onchange="upperCase(this); browseMeTax(frm.RequisitionLine_taxCode[<%=i_rowcount%>],'TAX', '<%=i_rowcount%>');" onfocus="this.blur();" disabled="disabled"/>
				<%}else { %>
					<tsa:input type="text" name="RequisitionLine_taxCode" value="<%=s_line_tax_code%>" size="10" onchange="upperCase(this); browseMeTax(frm.RequisitionLine_taxCode[<%=i_rowcount%>],'TAX', '<%=i_rowcount%>');"/>
				<%} %>				
			</tsa:td>
			<tsa:td field="req-taxPercent" valign="CENTER" align="CENTER" noWrap="nowrap">
			<%if (!s_line_taxable.equals("Y")) { %>
				<tsa:input type="text" name="RequisitionLine_taxPercent" value="<%=bd_line_tax_percent%>" style="text-align: right" size="9" onchange="changeLineAmount(RequisitionLine_taxPercent,<%=i_rowcount%>);" onfocus="this.blur();" disabled="disabled"/>%
			<% } else { %>
				<tsa:input type="text" name="RequisitionLine_taxPercent" value="<%=bd_line_tax_percent%>" style="text-align: right" size="9" onchange="changeLineAmount(RequisitionLine_taxPercent,<%=i_rowcount%>);" onfocus="setLineFocus(RequisitionLine_taxPercent,<%=i_rowcount%>);"/>%
			<%} %>	
			</tsa:td>		
			<tsa:td field="req-taxAmount" valign="CENTER" align="CENTER" noWrap="nowrap">
			<%if (!s_line_taxable.equals("Y")) { %>
				<tsa:input type="text" name="RequisitionLine_taxAmount" value="<%=bd_line_tax_amount%>" style="text-align: right" size="10" onchange="changeLineAmount(RequisitionLine_taxAmount,<%=i_rowcount%>);" onfocus="this.blur();" disabled="disabled"/>
			<% } else { %>
				<tsa:input type="text" name="RequisitionLine_taxAmount" value="<%=bd_line_tax_amount%>" style="text-align: right" size="10" onchange="changeLineAmount(RequisitionLine_taxAmount,<%=i_rowcount%>);" onfocus="setLineFocus(RequisitionLine_taxAmount,<%=i_rowcount%>);"/>
			<%} %>			
			</tsa:td>
<%	} else { %>
			<tsa:hidden name="RequisitionLine_taxCode" value="<%=s_line_tax_code%>"/>
			<tsa:hidden name="RequisitionLine_taxPercent" value="<%=bd_line_tax_percent%>"/>
			<tsa:hidden name="RequisitionLine_taxAmount" value="<%=bd_line_tax_amount%>"/>
<%	}
		if (s_reason.equals("shipping")) { %>
			<tsa:td field="req-shippingCharges" valign="CENTER" align="CENTER"><tsa:input type="text" name="RequisitionLine_shippingCharges" value="<%=bd_line_shipping_charges%>" style="text-align: right" size="10" onchange="changeLineAmount(RequisitionLine_shippingCharges,<%=i_rowcount%>);" onfocus="setLineFocus(RequisitionLine_shippingCharges,<%=i_rowcount%>);"/></tsa:td>
			<tsa:td field="req-shippingTaxAmount" valign="CENTER" align="CENTER"><tsa:input type="TEXT" name="RequisitionLine_shippingTaxAmt" value="<%=bd_line_shipping_tax_amt%>" style="text-align: right" size="10" onchange="changeLineAmount(RequisitionLine_shippingTaxAmt,<%=i_rowcount%>);" onfocus="setLineFocus(RequisitionLine_shippingTaxAmt,<%=i_rowcount%>);"/> </tsa:td>
<%	} else { %>
			<tsa:hidden name="RequisitionLine_shippingCharges" value="<%=bd_line_shipping_charges%>"/>
			<tsa:hidden name="RequisitionLine_shippingTaxAmt" value="<%=bd_line_shipping_tax_amt%>"/>
<%	}
		if (s_reason.equals("other")) { %>
			<tsa:td field="req-otherCharges" valign="CENTER" align="CENTER"><tsa:input type="TEXT" name="RequisitionLine_otherCharges" value="<%=bd_line_other_charges%>" style="text-align: right" size="10" onchange="changeLineAmount(RequisitionLine_otherCharges,<%=i_rowcount%>);" onfocus="setLineFocus(RequisitionLine_otherCharges,<%=i_rowcount%>);"/></tsa:td>
			<tsa:td field="req-otherTaxAmount" valign="CENTER" align="CENTER"><tsa:input type="TEXT" name="RequisitionLine_otherTaxAmount" value="<%=bd_line_other_tax_amount%>" style="text-align: right" size="10" onchange="changeLineAmount(RequisitionLine_otherTaxAmount,<%=i_rowcount%>);" onfocus="setLineFocus(RequisitionLine_otherTaxAmount,<%=i_rowcount%>);"/></tsa:td>
			<tsa:td field="req-otherDescription" valign="CENTER" align="CENTER"><tsa:input type="TEXT" name="RequisitionLine_otherDescription" value="<%=s_line_other_description%>" size="25" onfocus="setLineFocus(RequisitionLine_otherDescription,<%=i_rowcount%>);" maxLength="30"/></tsa:td>
<%	} else { %>
			<tsa:hidden name="RequisitionLine_otherCharges" value="<%=bd_line_other_charges%>"/>
			<tsa:hidden name="RequisitionLine_otherTaxAmount" value="<%=bd_line_other_tax_amount%>"/>
			<tsa:hidden name="RequisitionLine_otherDescription" value="<%=s_line_other_description%>"/>
<% }%>
			<tsa:hidden name="RequisitionLine_lineTotal" value="<%=bd_line_total%>"/>
		</tsa:tr>

<%		i_rowcount++;
		}

	}
		if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0) { %>
		<tsa:tr >
			<tsa:td colspan="5" align="RIGHT">
				<TABLE BORDER="0" >
				<tsa:tr>
					<tsa:td align="RIGHT" >
						<A HREF="javascript: if (verifyAction('Clear all fields on this form?')) { resetMe(); } void(0);"><FONT CLASS="reset"><B><tsa:label labelName="req-clear" defaultString="clear"/></B></FONT></A>&nbsp;
					</tsa:td>
				</tsa:tr>
				</TABLE>
			</tsa:td>
		</tsa:tr>		
		<%	} %>
		</table>
	</tsa:td>
</tsa:tr>
<tsa:tr><tsa:td><BR></tsa:td></tsa:tr>
<tsa:tr>
	<tsa:td colspan="5">
		<TABLE BORDER="0" WIDTH="100%">
		<tsa:tr>
			<tsa:td width="50%" align="CENTER">
				<div id="pxbutton"><A HREF="javascript: submitThis(); void(0);" BORDER="0">Save</A></div>
			</tsa:td>
			<tsa:td width="50%" align="CENTER">
				<div id="pxbutton"><A HREF="javascript: window.top.hidePopWin(); void(0);">Return</A></div>
			</tsa:td>
		</tsa:tr>
		</TABLE>
	</tsa:td>
</tsa:tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value="JavaScript">
<!-- Hide script

	var rowcount = <%=i_rowcount%>;
	var opencount = 0;
	var disc_ovr = "N";
	var tax_ovr = "N";
	var ship_ovr = "N";
	var other_ovr = "N";
	var currentRow = 0;

	frm = document.purchaseform;

	dollarDecimals = <%=s_dollar_decimals%>;

	setOverrideFlags();

	if (frm.refreshOpener.value == "Y") {
		//set cursor to hourglass while the system is processing
		window.parent.document.body.style.cursor = "wait";
		window.parent.doSubmit('requests/req_totals.jsp', 'RequisitionHeaderRetrieveById');
		window.top.hidePopWin();
	}

	function submitThis() {
		frm.refreshOpener.value = "Y";
		doSubmit('requests/req_totals_breakdown.jsp','RequisitionUpdateAll');
	}

	function setAlerts()
	{
	<% if (s_reason.equalsIgnoreCase("discount")) { %>
		window.parent.document.purchaseform.discount.value="N";
	<% } else if (s_reason.equalsIgnoreCase("tax")) { %>
		window.parent.document.purchaseform.tax.value="N";
	<% } else if (s_reason.equalsIgnoreCase("shipping")) { %>
		window.parent.document.purchaseform.shipping.value="N";
	<% } else if (s_reason.equalsIgnoreCase("other")) { %>
		window.parent.document.purchaseform.other.value="N";
	<% }%>
	}
/*
	function closeThis()
	{
		setAlerts();
		if (window.parent.document.purchaseform.alert.value == "Y") {
			window.parent.document.purchaseform.action = "req_totals_summary.jsp";
			window.parent.document.purchaseform.submit();
		}

		var browser = browserTest();
		if (browser == "Netscape") {
			window.parent.lookup_window = undefined;
		} else {
			window.parent.lookup_window = null;
		}
		parent.close();
	}
*/
	function setRow(row)
	{
		currentRow = row;
	}

	function setHeaderFocus(formField)
	{
		/*blocks header shipping/other tax fields*/
		if (formField == frm.RequisitionHeader_shippingTaxAmt)
		{
			if ((frm.RequisitionHeader_taxShipping.value == "N") || (frm.RequisitionHeader_shippingCharges.value == "0.00") || ((eval(frm.RequisitionHeader_taxPercent.value) > 0) && (tax_ovr == "N")))
			{
				formField.blur();
			}
		}
		if (formField == frm.RequisitionHeader_otherTaxAmount)
		{
			if ((frm.RequisitionHeader_taxOther.value == "N") || (frm.RequisitionHeader_otherCharges.value == "0.00") || ((eval(frm.RequisitionHeader_taxPercent.value) > 0) && (tax_ovr == "N")))
			{
				formField.blur();
			}
		}
		if (formField == frm.RequisitionHeader_taxAmount)
		{
			if (rowcount < 1)
			{
				return;
			}
			else if (rowcount > 1)
			{
				for (var i = 0; i < rowcount; i++)
				{
					if (frm.RequisitionLine_taxable[i].value == "Y")
					{
						return;
					}
				}
			}
			else if (rowcount == 1)
			{
				if (frm.RequisitionLine_taxable.value == "Y")
				{
					return;
				}
			}
		}
	}

	function setLineFocus(formField,x)
	{
		if (rowcount > 1)
		{
			/*blocks line shipping/other tax fields or all fields if the status is canceled*/
			if (frm.RequisitionLine_status[x].value == "<%=DocumentStatus.CANCELLED%>")
			{
				formField[x].blur();
				return;
			}
			if (formField == frm.RequisitionLine_shippingTaxAmt)
			{
				if ((frm.RequisitionHeader_taxShipping.value == "N") || (frm.RequisitionLine_shippingCharges[x].value == "0.00") || ((eval(frm.RequisitionLine_taxPercent[x].value) > 0) && (frm.RequisitionLine_taxOvr[x].value == "N")))
				{
					formField[x].blur();
				}
			}
			if (formField == frm.RequisitionLine_otherTaxAmount)
			{
				if ((frm.RequisitionHeader_taxOther.value == "N") || (frm.RequisitionLine_otherCharges[x].value == "0.00") || ((eval(frm.RequisitionLine_taxPercent[x].value) > 0) && (frm.RequisitionLine_taxOvr[x].value == "N")))
				{
					formField[x].blur();
				}
			}
		}
		else
		{
			if (frm.RequisitionLine_status.value == "<%=DocumentStatus.CANCELLED%>")
			{
				formField.blur();
				return;
			}
			if (formField == frm.RequisitionLine_shippingTaxAmt)
			{
				if ((frm.RequisitionHeader_taxShipping.value == "N") || (frm.RequisitionLine_shippingCharges.value == "0.00") || ((eval(frm.RequisitionLine_taxPercent.value) > 0) && (frm.RequisitionLine_taxOvr.value == "N")))
				{
					formField.blur();
				}
			}
			if (formField == frm.RequisitionLine_otherTaxAmount)
			{
				if ((frm.RequisitionHeader_taxOther.value == "N") || (frm.RequisitionLine_otherCharges.value == "0.00") || ((eval(frm.RequisitionLine_taxPercent.value) > 0) && (frm.RequisitionLine_taxOvr.value == "N")))
				{
					formField.blur();
				}
			}
		}
	}

	function setCheckbox(fld) {
		fld.value = 'N';
		if ( frm.c_checkbox.checked ) {
			fld.value = 'Y';
		}
	}

	function resetCheckbox(fld) {
		frm.c_checkbox.checked = false;
		if ( fld.value == 'Y')
		{
			frm.c_checkbox.checked = true;
		}
		return false;
	}

	function resetMe()
	{
<%	/* Requisition Header */
		bd_subtotal = requisitionHeader.getSubtotal();
		bd_total = requisitionHeader.getTotal();
%>
		frm.RequisitionHeader_subtotal.value	= "<%=bd_subtotal%>";
		frm.RequisitionHeader_total.value	= "<%=bd_total%>";

<%	if (s_reason.equals("shipping")) { %>
			frm.RequisitionHeader_taxShipping.value = "N";
			frm.c_checkbox.checked = false;
			frm.RequisitionHeader_shippingCharges.value = "0.00";
			frm.RequisitionHeader_shippingTaxAmt.value = "0.00";
<%	}
		else if (s_reason.equals("other")) { %>
			frm.RequisitionHeader_taxOther.value = "N";
			frm.c_checkbox.checked = false;
			frm.RequisitionHeader_otherCharges.value = "0.00";
			frm.RequisitionHeader_otherTaxAmount.value = "0.00";
			frm.RequisitionHeader_otherChargDesc.value	= "";
<%	}
		else if (s_reason.equals("discount")) { %>
			frm.RequisitionHeader_discountPercent.value = "0.00";
			frm.RequisitionHeader_discountAmount.value = "0.00";
<%	}
		else if (s_reason.equals("tax")) { %>
			frm.RequisitionHeader_taxCode.value = "";
			frm.RequisitionHeader_taxPercent.value = "0.00";
			frm.RequisitionHeader_taxAmount.value = "0.00";
<%	}
		/* Requisition Line */
		String	s_fldArray = "";
		if (lineList != null)
		{
			for (int i = 0; i < lineList.size(); i++)
			{
				RequisitionLine reqLine = (RequisitionLine) lineList.get(i);

				bd_line_unit_price = reqLine.getUnitPrice();
				bd_line_quantity = reqLine.getQuantity();
				bd_line_um_factor = reqLine.getUmFactor();

				if (bd_line_unit_price == null)	{	bd_line_unit_price = new BigDecimal(0);	}
				if (bd_line_quantity == null)		{	bd_line_quantity = new BigDecimal(0);		}
				if (bd_line_um_factor == null)	{	bd_line_um_factor = new BigDecimal(1);	}

				bd_line_subtotal = bd_line_unit_price.multiply(bd_line_quantity);
				bd_line_subtotal = bd_line_subtotal.multiply(bd_line_um_factor);
				bd_line_subtotal = bd_line_subtotal.setScale(2, BigDecimal.ROUND_HALF_UP);

				bd_line_total = reqLine.getLineTotal();

				if (lineList.size() > 1) {
					s_fldArray = "[" + String.valueOf(i) + "]";
				}
%>
				frm.computed_subtotal<%=s_fldArray%>.value = "<%=bd_line_subtotal%>";
				frm.RequisitionLine_lineTotal<%=s_fldArray%>.value = "<%=bd_line_total%>";

<%			if (s_reason.equals("tax")) { %>
					frm.RequisitionLine_taxOvr<%=s_fldArray%>.value = "N";
					frm.RequisitionLine_taxAmount<%=s_fldArray%>.value = "0.00";
					frm.RequisitionLine_taxCode<%=s_fldArray%>.value = "";
					frm.RequisitionLine_taxPercent<%=s_fldArray%>.value = "0.00";

<%				if (lineList.size() > 1)
					{
						s_fldArray = "[" + String.valueOf(i) + "]";
					}
					else
					{
						s_fldArray = "";
					}
				}
				else if (s_reason.equals("discount")) { %>
					frm.RequisitionLine_discOvr<%=s_fldArray%>.value = "N";
					frm.RequisitionLine_discountPercent<%=s_fldArray%>.value = "0.00";
					frm.RequisitionLine_discountAmount<%=s_fldArray%>.value = "0.00";
<%			}
				else if (s_reason.equals("shipping")) { %>
					frm.RequisitionLine_shipOvr<%=s_fldArray%>.value = "N";
					frm.RequisitionLine_shippingCharges<%=s_fldArray%>.value = "0.00";
					frm.RequisitionLine_shippingTaxAmt<%=s_fldArray%>.value = "0.00";
<%			}
				else if (s_reason.equals("other")) { %>
					frm.RequisitionLine_otherOvr<%=s_fldArray%>.value = "N";
					frm.RequisitionLine_otherCharges<%=s_fldArray%>.value = "0.00";
					frm.RequisitionLine_otherTaxAmount<%=s_fldArray%>.value = "0.00";
					frm.RequisitionLine_otherDescription<%=s_fldArray%>.value = "";
<%			}
				i_rowcount++;
			}
 		}
 		if (s_reason.equals("tax")) { %>
			calculateShipTax();
			calculateOtherTax();
			headerTotal();
<%	}%>
		if (rowcount > 0)
		{
			lineTotal();
		}
	}

	function thisLoadPopup()
	{
<%		if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0) { %>
			checkInputSettings();
<%	} %>
	}


	function browseLookup(formField, xml)
	{
		popupParameters =  "fromPage=Totals;formField=" + formField +";browseName=" + xml + ";allowBrowse=" + frm.allowBrowse.value;

		doSubmitToPopup('/browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=800px', 'HEIGHT=900px');
	}

	function openWindow (url, w, h) {
		if (browser == "Netscape") {
			if (w == undefined) { w = 'WIDTH=500'; }
			if (h == undefined) { h = 'HEIGHT=300'; }
			if (theFocus == undefined) { theFocus = 'detail'; }
		}
		else {
			if (w == null) { w = 'WIDTH=500'; }
			if (h == null) { h = 'HEIGHT=300'; }
			if (theFocus == null) { theFocus = 'detail'; }
		}
		var winspecs = w +","+ h +",resizable=1,scrollbars=1,menubar=0,location=0,top=0,left=0";

		detail_window = window.open("<%=contextPath%>/system/popup_html.jsp", "detail_window", winspecs);

		if (theFocus == 'main') {
			self.focus();
		}
		else {
			detail_window.focus();
		}

		if (detail_window.opener == null) detail_window.opener = window;
		self.name = "main";
	}

	function browseMeTax (formField, tableType, row )
	{
		//set cursor to hourglass while the system is processing
		document.body.style.cursor = "wait";

		fldObject = formField;
		fldFromObject = null;

		var taxCode = formField.value;

		popupParameters = "TaxCode_taxCode=" + taxCode + ";formField=" + formField.name + ";row=" + row;

		setLookupParameters('/base/get_tax_info.jsp', 'TaxCodeRetrieveById');
		displayArea('getInfoFrame');
		document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
	}

// End Hide script -->
</SCRIPT>