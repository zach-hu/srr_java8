<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>
<%@ page import="com.tsa.puridiom.taxcode.TaxCodeManager" %>


<%

	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	TaxCodeManager taxCodeManager = TaxCodeManager.getInstance();
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	BigDecimal	bd_ic_po_header = poHeader.getIcPoHeader();
	BigDecimal	bd_release_number = poHeader.getReleaseNumber();
	BigDecimal	bd_revision_number = poHeader.getRevisionNumber();
	BigDecimal	bd_zero = new BigDecimal(0);
	String	s_curr_code = poHeader.getCurrencyCode();
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	boolean disableEstCost = false;

	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	String	s_po_number = poHeader.getPoNumber();
	String	s_po_status = poHeader.getStatus();
	String	s_po_type = poHeader.getPoType();
	String s_buyer_code = poHeader.getBuyerCode();
	String s_e_tax = HiltonUtility.ckNull(poHeader.getTaxCode());
	String s_e_tax_desc = "";
	BigDecimal bd_estCost = poHeader.getEstimatedCost().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);

	if( !s_e_tax.equalsIgnoreCase("") ){
		TaxCode taxCode = (TaxCode) taxCodeManager.getTaxCode(oid,s_e_tax);

		if (taxCode != null) {
			s_e_tax_desc = HiltonUtility.ckNull(taxCode.getDescription());
		}
	}

	if ( HiltonUtility.ckNull(poHeader.getTaxCode()).equals("")) {
		s_e_tax = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noneselected", "None Selected");
	}

	if (!Utility.isEmpty(poHeader.getRequisitionNumber()) || !Utility.isEmpty(poHeader.getRfqNumber())) {
		disableEstCost = true;
	}

	if (Utility.isEmpty(s_po_number))	{	s_po_number = "N/A";									}
	if (Utility.isEmpty(s_po_status))		{	s_po_status = "3000";									}
	if (bd_release_number == null)		{	bd_release_number = new BigDecimal(0000);	}
	if (bd_revision_number == null)		{	bd_revision_number = new BigDecimal(0000);	}

	//what is this stuff doing?!?
	String	s_discount = "";
	String	s_tax = "";
	String	s_shipping = "";
	String	s_other = "";

	String	s_current_process = "HEADER_TOTALS";
	String	s_current_page = "/orders/po_totals.jsp";
	String	s_current_method = "EstimatedCostUpdate";
	String	s_current_process_method = "";
	String	invalidFields = (String) request.getAttribute("invalidFields");

	while(!HiltonUtility.isEmpty(invalidFields) && invalidFields.startsWith(";")) {
		if (invalidFields.length() > 1) {
			invalidFields = invalidFields.substring(1);
		} else {
			invalidFields = "";
		}
	}
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=poHeader.getPoNumber()%>"/>
<tsa:hidden name="PoSecurity_poNumber" value="<%=poHeader.getPoNumber()%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=poHeader.getReleaseNumber()%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=poHeader.getRevisionNumber()%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=poHeader.getFiscalYear()%>"/>
<tsa:hidden name="PoHeader_itemLocation" value="<%=poHeader.getItemLocation()%>"/>
<tsa:hidden name="PoHeader_buyerCode" value="<%=poHeader.getBuyerCode()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="POH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="VendorInsurance_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="VendorInsurance_contNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=poHeader.getVendorId()%>"/>
<tsa:hidden name="poIcReqHeader" value="<%= poHeader.getIcReqHeader().toString() %>"/>
<tsa:hidden name="formtype" value="PO"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="po-totals" defaultString="Totals" /></div>
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
<br>
<%@ include file="/system/error_msg.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center valign=top width=100%>
		<table align=center width=600px>
		<TR>
			<TD align=center>
				<TABLE BORDER="0" CELLPADDING="2" CELLSPACING="1" CLASS="basic">
				<tsa:tr field="po-taxCode">
					<TD ALIGN="RIGHT" CLASS="basic"><tsa:label labelName="po-taxCode" defaultString="Tax Code" checkRequired="true" />:&nbsp;</TD>
					<TD CLASS="basic"><%=s_e_tax%></TD>
				</tsa:tr>
				<tsa:tr field="po-taxDescription">
					<TD ALIGN="RIGHT" CLASS="basic"><tsa:label labelName="po-taxDescription" defaultString="Tax Description" checkRequired="true" />:&nbsp;</TD>
					<TD CLASS="basic"><%=s_e_tax_desc%></TD>
				</tsa:tr>
				<tsa:tr field="po-shippingTaxable">
					<TD ALIGN="RIGHT" CLASS="basic"><tsa:label labelName="po-shippingTaxable" defaultString="Shipping Taxable" checkRequired="true" />:&nbsp;<tsa:hidden name="PoHeader_shippingTaxable" value="<%=HiltonUtility.ckNull(poHeader.getShippingTaxable())%>"/></TD>
					<TD CLASS="basic"><% if (HiltonUtility.ckNull(poHeader.getShippingTaxable()).equals("Y")) { %><img src="<%=contextPath%>/images/check.gif"><% } %></TD>
				</tsa:tr>
				<tsa:tr field="po-otherTaxable">
					<TD ALIGN="RIGHT" CLASS="basic"><tsa:label labelName="po-otherTaxable" defaultString="Other Taxable" checkRequired="true" />:&nbsp;<tsa:hidden name="PoHeader_otherTaxable" value="<%=HiltonUtility.ckNull(poHeader.getOtherTaxable())%>"/></TD>
					<TD CLASS="basic"><% if (HiltonUtility.ckNull(poHeader.getOtherTaxable()).equals("Y")) { %><img src="<%=contextPath%>/images/check.gif"><% } %></TD>
				</tsa:tr>
				<tsa:tr field="po-otherDescription">
					<TD ALIGN="RIGHT" CLASS="basic"><tsa:label labelName="po-otherDescription" defaultString="Other Description" checkRequired="true" />:&nbsp;</TD>
					<TD CLASS="basic"><%=HiltonUtility.ckNull(poHeader.getOtherDescription())%></TD>
				</tsa:tr>
				</TABLE>
			</TD>
			<td align=center valign=top>
				<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="1" CLASS="basic">
				<tsa:tr field="po-subtotal">
					<TD ALIGN="RIGHT" CLASS="basic" COLSPAN="2"><tsa:label labelName="po-subtotal" defaultString="Subtotal" />:&nbsp;</TD>
					<TD><tsa:input labelName="po-subtotal" type="mintext" name="subtotal" value="<%=HiltonUtility.getCurrency(poHeader.getSubtotal(), s_curr_code, oid)%>" style="text-align:right" onfocus="this.blur();" disabled="true" /></TD>
					<tsa:hidden name="PoHeader_subTotal" value="<%=poHeader.getSubtotal()%>"></tsa:hidden>
				</tsa:tr>
				<tsa:tr field="po-discountAmount">
					<TD NOWRAP ALIGN="RIGHT" CLASS="basic"><A HREF="javascript: void(0);" title="Click here to add the Discount Percentage for this order." ONCLICK="setUrl('discount');"><tsa:label labelName="po-discountAmountt" defaultString="Discount" checkRequired="true" /></A>:&nbsp;</TD>
					<TD VALIGN="CENTER"><tsa:input type="text" name="discountPercent" value="<%=Utility.getBigDecimalFormatted(poHeader.getDiscountPercent(), 2)%>" style="text-align:right" size="9" onchange="changeHeaderAmount(PoHeader_discountPercent);" onfocus="this.blur();" />%</TD>
					<tsa:hidden name="PoHeader_discountPercent" value="<%=poHeader.getDiscountPercent()%>"></tsa:hidden>
					<TD VALIGN="CENTER"><tsa:input labelName="po-discountAmountt" type="mintext" name="discountAmount" value="<%=HiltonUtility.getCurrency(poHeader.getDiscountAmount(), s_curr_code, oid)%>" style="text-align:right" onchange="distributeDiscount();" onfocus="this.blur();" /><%if ( s_discount.equals("Y") )  {%><A HREF="javascript: void(0);" ONCLICK="setUrl('discount');"><IMG SRC="/puridiom/xchange/images/alert.gif" ALIGN="BOTTOM" BORDER="0"></A><%}%></TD>
					<tsa:hidden name="PoHeader_discountAmount" value="<%=poHeader.getDiscountAmount()%>"/>
				</tsa:tr>
				<tsa:tr field="po-taxAmount">
				<%String taxLabel = "Tax Amount";
				if(!HiltonUtility.isQriCanadian(oid, poHeader.getUdf1Code()))
				{
					taxLabel = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-taxAmount", "Tax Amount");
				}
				else
				{
					taxLabel = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-canTaxAmount", "GST");
				}%>
					<TD ALIGN="RIGHT" CLASS="basic"><A HREF="javascript: void(0);" title="Click here to add the Tax Rate for this order." ONCLICK="setUrl('tax');"><%=taxLabel%></A>:&nbsp;</TD>
					<TD><tsa:input type="text" name="PoHeader_taxPercent" value="<%=Utility.getBigDecimalFormatted(poHeader.getTaxPercent(), 5)%>" style="text-align:right" size="9" onchange="changeHeaderAmount(PoHeader_taxPercent);" onfocus="this.blur();" />%</TD>
					<TD><tsa:input type="mintext" name="taxAmount" value="<%=HiltonUtility.getCurrency(poHeader.getTaxAmount(), s_curr_code, oid)%>" style="text-align:right" onchange="distributeTax();" onfocus="this.blur();" /><%if ( s_tax.equals("Y") ) {%><A HREF="javascript: void(0);" ONCLICK="setUrl('tax');"><IMG SRC="/puridiom/xchange/images/alert.gif" ALIGN="BOTTOM" BORDER="0"></A><%}%></TD>
					<tsa:hidden name="PoHeader_taxAmount" value="<%=poHeader.getTaxAmount()%>"/>
				</tsa:tr>
				<%if(HiltonUtility.isQriCanadian(oid, poHeader.getUdf1Code())){%>
					<tsa:tr field="po-useTaxAmount">
						<TD ALIGN="RIGHT" CLASS="basic"><tsa:label labelName="po-useTaxAmount" defaultString="Use Tax Amount" />:&nbsp;</TD>
						<tsa:td field="po-useTaxPercent"><tsa:input type="text" name="PoHeader_useTaxPercent" value="<%=Utility.getBigDecimalFormatted(poHeader.getUseTaxPercent(), 5)%>" style="text-align:right" size="9" onchange="changeHeaderAmount(PoHeader_useTaxPercent);" onfocus="this.blur();" />%</tsa:td>
						<TD><tsa:input labelName="po-useTaxAmount" type="mintext" name="useTaxAmount" value="<%=HiltonUtility.getCurrency(poHeader.getUseTaxAmount(), s_curr_code, oid)%>" style="text-align:right" onchange="distributeTax();" onfocus="this.blur();" /><%if ( s_tax.equals("Y") ) {%><A HREF="javascript: void(0);" ONCLICK="setUrl('tax');"><IMG SRC="/puridiom/xchange/images/alert.gif" ALIGN="BOTTOM" BORDER="0"></A><%}%></TD>
						<tsa:hidden name="PoHeader_useTaxAmount" value="<%=poHeader.getUseTaxAmount()%>"></tsa:hidden>
					</tsa:tr>
				<%} %>
				<tsa:tr field="po-shippingCharges">
					<TD ALIGN="RIGHT" CLASS="basic" COLSPAN="2"><A HREF="javascript: void(0);" title="Click here to add the Shipping Amount for this order." ONCLICK="setUrl('shipping');"><tsa:label labelName="po-shippingCharges" defaultString="Shipping" checkRequired="true" /></A>:&nbsp;</TD>
					<TD><tsa:input labelName="po-shippingCharges" type="mintext" name="shippingCharges" value="<%=HiltonUtility.getCurrency(poHeader.getShippingCharges(), s_curr_code, oid)%>" style="text-align:right" onchange="distributeShipping();" onfocus="this.blur();" /><%if ( s_shipping.equals("Y") ) {%><A HREF="javascript: void(0);" ONCLICK="setUrl('shipping');"><IMG SRC="/puridiom/xchange/images/alert.gif" ALIGN="BOTTOM" BORDER="0"></A><%}%></TD>
					<tsa:hidden name="PoHeader_shippingCharges" value="<%=poHeader.getShippingCharges()%>"></tsa:hidden>
				</tsa:tr>
				<tsa:tr field="po-shippingTaxAmount">
					<TD ALIGN="RIGHT" CLASS="basic" COLSPAN="2"><tsa:label labelName="po-shippingTaxAmount" defaultString="Shipping Tax" checkRequired="true" />:&nbsp;</TD>
					<TD VALIGN="CENTER"><tsa:input labelName="po-shippingTaxAmount" type="mintext" name="shippingTax" value="<%=HiltonUtility.getCurrency(poHeader.getShippingTax(), s_curr_code, oid)%>" style="text-align:right" onchange="distributeShipTax();" onfocus="this.blur();" /></TD>
					<tsa:hidden name="PoHeader_shippingTax" value="<%=poHeader.getShippingTax()%>"></tsa:hidden>
				</tsa:tr>
				<tsa:tr field="po-otherCharges">
					<TD ALIGN="RIGHT" CLASS="basic" COLSPAN="2"><A HREF="javascript: void(0);" title="Click here to add Other Order Expenses for this order." ONCLICK="setUrl('other');"><tsa:label labelName="po-otherCharges" defaultString="Other" checkRequired="true" /></A>:&nbsp;</TD>
					<TD><tsa:input labelName="po-otherCharges" type="mintext" name="otherCharges" value="<%=HiltonUtility.getCurrency(poHeader.getOtherCharges(), s_curr_code, oid)%>" style="text-align:right" onchange="distributeOther();" onfocus="this.blur();" /><%if ( s_other.equals("Y") ) {%><A HREF="javascript: void(0);" ONCLICK="setUrl('other');"><IMG SRC="/puridiom/xchange/images/alert.gif" ALIGN="BOTTOM" BORDER="0"></A><%}%></TD>
					<tsa:hidden name="PoHeader_otherCharges" value="<%=poHeader.getOtherCharges()%>"></tsa:hidden>
				</tsa:tr>
				<tsa:tr field="po-otherTaxAmount">
					<TD ALIGN="RIGHT" CLASS="basic" COLSPAN="2"><tsa:label labelName="po-otherTaxAmount" defaultString="Other Tax" checkRequired="true" />:&nbsp;</TD>
					<TD VALIGN="CENTER"><tsa:input labelName="po-otherTaxAmount" type="mintext" name="otherTax" value="<%=HiltonUtility.getCurrency(poHeader.getOtherTax(), s_curr_code, oid)%>" style="text-align:right" onchange="distributeOtherTax();" onfocus="this.blur();" /></TD>
					<tsa:hidden name="PoHeader_otherTax" value="<%=poHeader.getOtherTax()%>"></tsa:hidden>
				</tsa:tr>
				<tsa:tr field="po-total">
					<TD ALIGN=RIGHT CLASS="processOn" COLSPAN="2"><b><tsa:label labelName="po-total" defaultString="Total" checkRequired="true" />:</b>&nbsp;</TD>
					<TD><tsa:input labelName="po-total" type="mintext" name="total" value="<%=HiltonUtility.getCurrency(poHeader.getTotal(), s_curr_code, oid)%>" style="text-align:right" onfocus="this.blur();" /></TD>
					<tsa:hidden name="PoHeader_total" value="<%=poHeader.getTotal()%>"></tsa:hidden>
				</tsa:tr>
				<tsa:tr field="po-estCost">
					<tsa:td align="right" cssClass="processOn" colspan="2"><b><tsa:label labelName="po-estCost" defaultString="Est. Cost"/>:</b>&nbsp;</tsa:td>
					<tsa:td><tsa:input type="mintext" name="PoHeader_estimatedCost" value="<%=bd_estCost%>" onchange="formatMe(this);"  style="text-align: right"/></tsa:td>
				</tsa:tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
<%	if (s_view.equalsIgnoreCase("WIZARD")) { %>
	<td rowspan=2 align=right valign=top><%@ include file="/orders/po_sidebar.jsp" %></td>
</tr>
<%	} %>
</tr>
</table>

<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoHeaderUpdate;PoRetrieve'); void(0);"><tsa:label labelName="save" defaultString="Save" /></a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoRetrieve'); void(0);"><tsa:label labelName="return" defaultString="Return" /></a></div></td>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var ponumber = "<%=s_po_number%>";
	var fiscalyear = "<%=poHeader.getFiscalYear()%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var disableEstCost = "<%=disableEstCost%>";

	function setUrl(x)
	{
		popupParameters = "PoHeader_icPoHeader=<%=bd_ic_po_header%>;reason=" + x;
		doSubmitToPopup('/orders/po_totals_breakdown.jsp', 'PoRetrieve', 'width=450', 'height=500');
	}

	function thisLoad()
	{
		f_StartIt();
<%	if (s_po_status.compareTo(DocumentStatus.PO_REJECTED) > 0 || (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer()) ) { %>
			checkInputSettings();
<%	} else {%>
		setInvalidFields("<%=invalidFields%>");
<%	}
	if (s_po_status.compareTo(DocumentStatus.RCV_RECEIVED) <= 0) { %>
		frm.allowBrowse.value = true;
<%  } %>
		if (disableEstCost == "true"){
			frm.PoHeader_estimatedCost.readonly = true;
			frm.PoHeader_estimatedCost.disabled = true;
			frm.PoHeader_estimatedCost.contentEditable = true;
		}
	}

	function formatMe(x)
	{
		var dollarDecimals = <%=s_dollar_decimals%>;


			if (frm.PoHeader_estimatedCost[x])
			{
				var amount = eval(nfilter(frm.PoHeader_estimatedCost[x]));
				if (frm.PoHeader_estimatedCost[x].value != '')
				{
					frm.PoHeader_estimatedCost[x].value = nformat(amount, dollarDecimals);
				}
			}
			else
			{
				var amount = eval(nfilter(frm.PoHeader_estimatedCost));
				if (frm.PoHeader_estimatedCost.value != '')
				{
					frm.PoHeader_estimatedCost.value = nformat(amount, dollarDecimals);
				}
			}
		}




// End Hide script -->
</SCRIPT>