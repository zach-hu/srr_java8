<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>

<%
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	BigDecimal bd_ic_req_header = requisitionHeader.getIcReqHeader();
	String s_req_number = requisitionHeader.getRequisitionNumber();
	if (HiltonUtility.isEmpty(s_req_number)){s_req_number = "N/A";}
	String s_req_type = requisitionHeader.getRequisitionType();
	String s_req_subtype = requisitionHeader.getRqSubType();
	String s_fiscal_year = requisitionHeader.getFiscalYear();
	String s_req_status = requisitionHeader.getStatus();
	String s_tax_shipping = requisitionHeader.getTaxShipping();
	String s_tax_other = requisitionHeader.getTaxOther();
	String s_other_charg_desc = requisitionHeader.getOtherChargDesc();
	String s_tax_code = HiltonUtility.ckNull(requisitionHeader.getTaxCode());
	String s_curr_code = requisitionHeader.getCurrencyCode();
	String s_udf_1_code = requisitionHeader.getUdf1Code();
	String s_udf_14_code = requisitionHeader.getUdf14Code();
	String  suppress_nonconsumable_charges = propertiesManager.getProperty("REQ OPTIONS", "SUPPRESSNONCONSUMABLECHARGES", "N");
	boolean suppressForNonConsumables = false;

	if (suppress_nonconsumable_charges.equals("Y") && !requisitionHeader.getUdf1Code().equalsIgnoreCase("CONSUMABLES")) {
		suppressForNonConsumables = true;
	}

	boolean allowEdit = true;
	if (s_req_status.compareTo(DocumentStatus.REQ_INPROGRESS) >= 0 && s_req_type.equals("C")
			&& s_udf_1_code != null && s_udf_1_code.contains("RESALE")
			&& s_udf_14_code != null && s_udf_14_code.equals("DBS") ) {
		allowEdit = false;
	}

	if (oid.equalsIgnoreCase("msg07p") && HiltonUtility.ckNull(requisitionHeader.getTaxCode()).equals("")) {
		s_tax_code = "Non Selected";
	}

	BigDecimal b_sub_amt = requisitionHeader.getSubtotal().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	BigDecimal b_tax_amt = requisitionHeader.getTaxAmount().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	BigDecimal b_use_tax_amt = requisitionHeader.getUseTaxAmount().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	BigDecimal b_ship_amt = requisitionHeader.getShippingCharges().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	BigDecimal b_disc_amt = requisitionHeader.getDiscountAmount().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	BigDecimal b_other_amt = requisitionHeader.getOtherCharges().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	BigDecimal b_other_tax_amt = requisitionHeader.getOtherTaxAmount().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	BigDecimal b_total_amt = requisitionHeader.getTotal().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
	BigDecimal b_estimated_cost = requisitionHeader.getEstimatedCost().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);

	//what is this stuff doing?!?
	String	s_discount = "";
	String	s_tax = "";
	String	s_shipping = "";
	String	s_other = "";

	String	s_current_process = "HEADER_TOTALS";
	String	s_current_page = "/requests/req_totals.jsp";
	String	s_current_method = "EstimatedCostUpdate";
	String	s_current_process_method = "";
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=requisitionHeader.getRequisitionNumber()%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=requisitionHeader.getItemLocation()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RQH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="formtype" value="REQ"/>

<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
	<% if (s_req_type.equals("M")) {%>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="req-type" defaultString="Req Type"/>&nbsp;<tsa:label labelName="totals" defaultString="Totals"/></div>
			</tsa:td>
		</tsa:tr>
	<% } else { %>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="totals" defaultString="Totals"/></div>
			</tsa:td>
		</tsa:tr>
	<% } %>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="req-requisition" defaultString="Requisition #"/>:</b></tsa:td>
			<tsa:td width="150px"><%=headerEncoder.encodeForHTML(s_req_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="status" defaultString="Status"/>:</b></tsa:td>
			<tsa:td width="150px"><%=DocumentStatus.toString(s_req_status, oid)%></tsa:td>
		</tsa:tr>
		</table>
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

<br>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
<tsa:tr>
	<tsa:td align="center" valign="top" width="100%">
		<table align="center" width="90%">
		<tsa:tr>
			<tsa:td align="CENTER" valign="top">
				<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="1" CLASS="basic" width="100%">
				<tsa:tr>
					<tsa:td width="20px">&nbsp;</tsa:td>
					<tsa:td></tsa:td>
					<tsa:td></tsa:td>
					<tsa:td></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-subtotal">
					<tsa:td colspan="2" >&nbsp;</tsa:td>
					<tsa:td align="RIGHT" cssClass="basic" colspan="2"><tsa:label labelName="req-subtotal" defaultString="Subtotal"/>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="mintext" name="subtotal" value="<%=HiltonUtility.getCurrency(b_sub_amt, s_curr_code, oid)%>" style="text-align: right" onfocus="this.blur();"/></tsa:td>
					<tsa:hidden name="RequisitionHeader_subtotal" value="<%=b_sub_amt%>"/>
				</tsa:tr>
				<tsa:tr field="req-discountAmount">
					<tsa:td colspan="2" >&nbsp;</tsa:td>
					<tsa:td noWrap="nowrap" align="RIGHT" cssClass="basic">
						<%	if (allowEdit) {	%>
							<A HREF="javascript: void(0);" title="Click here to add the Discount Percentage for this requisition." ONCLICK="setUrl('discount');"><tsa:label labelName="req-discountAmount" defaultString="Discount"/></A>:&nbsp;
						<%	} else {	%>
							<tsa:label labelName="req-discountAmount" defaultString="Discount"/>
						<%	}	%>
					</tsa:td>
					<tsa:td field="req-discountPercent" valign="CENTER"><tsa:input type="text" name="discountPercent" value="<%=Utility.getBigDecimalFormatted(requisitionHeader.getDiscountPercent(), 2).toString()%>" style="text-align: right" size="9" onchange="changeHeaderAmount(RequisitionHeader_discountPercent);" onfocus="this.blur();"/>%</tsa:td>
					<tsa:td valign="CENTER"><tsa:input type="mintext" name="discountAmount" value="<%=HiltonUtility.getCurrency(b_disc_amt.toString(), s_curr_code, oid)%>" style="text-align: right" onchange="distributeDiscount();" onfocus="this.blur();"/><%if ( s_discount.equals("Y") )  {%><A HREF="javascript: void(0);" ONCLICK="setUrl('discount');"><IMG SRC="/puridiom/xchange/images/alert.gif" align="BOTTOM" BORDER="0"></A><%}%></tsa:td>
					<tsa:hidden name="RequisitionHeader_discountPercent" value="<%=requisitionHeader.getDiscountPercent()%>"/>
					<tsa:hidden name="RequisitionHeader_discountAmount" value="<%=b_disc_amt%>"/>
				</tsa:tr>
				<tsa:tr field="req-taxAmount">
				<%
				String taxLabel = "Tax Amount";
				if(!HiltonUtility.isQriCanadian(oid, requisitionHeader.getUdf1Code()))
				{
					taxLabel = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-taxAmount", "Tax Amount");
				}
				else
				{
					taxLabel = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-canTaxAmount", "GST");
				}%>
					<tsa:td align="right" cssClass="basic" field="req-taxCode"><tsa:label labelName="req-taxCode" defaultString="Tax Code"/>:&nbsp;</tsa:td>
					<tsa:td cssClass="basic" field="req-taxCode" width="150px"><%=s_tax_code%></tsa:td>
					<tsa:td align="RIGHT" cssClass="basic">
						<%	if (allowEdit) {	%>
							<A HREF="javascript: void(0);" title='<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-taxAmount-instructions", "Click here to add the Tax Rate for this requisition.")%>' ONCLICK="setUrl('tax');"><%=taxLabel %></A>:&nbsp;
						<%	} else {	%>
							<%=taxLabel %>
						<%	}	%>

					</tsa:td>
					<tsa:td field="req-taxPercent"><tsa:input type="text" name="taxPercent" value="<%=Utility.getBigDecimalFormatted(requisitionHeader.getTaxPercent(), 5)%>" style="text-align: right" size="9" onchange="changeHeaderAmount(RequisitionHeader_taxPercent);" onfocus="this.blur();"/>%</tsa:td>
					<tsa:td><tsa:input type="mintext" name="taxAmount" value="<%=HiltonUtility.getCurrency(b_tax_amt, s_curr_code, oid)%>" style="text-align: right" onchange="distributeTax();" onfocus="this.blur();"/><%if ( s_tax.equals("Y") ) {%><A HREF="javascript: void(0);" ONCLICK="setUrl('tax');"><IMG SRC="/puridiom/xchange/images/alert.gif" align="BOTTOM" BORDER="0"></A><%}%></tsa:td>
					<tsa:hidden name="RequisitionHeader_taxPercent" value="<%=requisitionHeader.getTaxPercent()%>"/>
					<tsa:hidden name="RequisitionHeader_taxAmount" value="<%=b_tax_amt%>"/>
				</tsa:tr>
				<%if(HiltonUtility.isQriCanadian(oid, requisitionHeader.getUdf1Code())){%>
					<tsa:tr field="req-useTaxAmount">
						<tsa:td colspan="2" >&nbsp;</tsa:td>
						<tsa:td align="RIGHT" cssClass="basic"><tsa:label labelName="req-useTaxAmount" defaultString="Use Tax Amount"/>:&nbsp;</tsa:td>
						<tsa:td field="req-useTaxPercent"><tsa:input type="text" name="useTaxPercent" value="<%=Utility.getBigDecimalFormatted(requisitionHeader.getUseTaxPercent(), 5)%>" style="text-align: right" size="9" onchange="changeHeaderAmount(RequisitionHeader_useTaxPercent);" onfocus="this.blur();"/>%</tsa:td>
						<tsa:td><tsa:input type="mintext" name="useTaxAmount" value="<%=HiltonUtility.getCurrency(b_use_tax_amt, s_curr_code, oid)%>" style="text-align: right" onchange="distributeTax();" onfocus="this.blur();"/><%if ( s_tax.equals("Y") ) {%><A HREF="javascript: void(0);" ONCLICK="setUrl('tax');"><IMG SRC="/puridiom/xchange/images/alert.gif" align="BOTTOM" BORDER="0"></A><%}%></tsa:td>
						<tsa:hidden name="RequisitionHeader_ustTaxPercent" value="<%=requisitionHeader.getUseTaxPercent()%>"/>
						<tsa:hidden name="RequisitionHeader_useTaxAmount" value="<%=b_use_tax_amt%>"/>
					</tsa:tr>
				<%} %>
				<tsa:tr>
					<tsa:td align="right">
						<TABLE border="0">
						<tsa:tr field="req-otherTaxable" docType="s_req_type">
							<tsa:td align="RIGHT" cssClass="basic" field="req-otherTaxable" docType="s_req_type"><tsa:label labelName="req-otherTaxable" defaultString="Other Taxable" docType="s_req_type"/>:&nbsp;<tsa:hidden name="RequisitionHeader_taxOther" value="<%=s_tax_other%>"/></tsa:td>
						</tsa:tr>
						</TABLE>
					</tsa:td>
					<tsa:td>
						<TABLE border="0">
						<tsa:tr field="req-otherTaxable" docType="s_req_type">
							<tsa:td cssClass="basic" field="req-otherTaxable" docType="s_req_type"><% if (s_tax_other.equalsIgnoreCase("Y")) { %><img src="<%=contextPath%>/images/check.gif"><% } %></tsa:td>
						</tsa:tr>
						</TABLE>
					</tsa:td>
					<% if (!suppressForNonConsumables) {%>
					<tsa:td align="RIGHT" cssClass="basic" colspan="2" field="req-shippingCharges">
						<%	if (allowEdit) {	%>
							<A HREF="javascript: void(0);" title="Click here to choose the Shipping Amount for this requisition." ONCLICK="setUrl('shipping');"><tsa:label labelName="req-shippingCharges" defaultString="Shipping"/></A>:&nbsp;
						<%	} else {	%>
							<tsa:label labelName="req-shippingCharges" defaultString="Shipping"/>
						<%	}	%>
					</tsa:td>
					<tsa:td field="req-shippingCharges"><tsa:input type="mintext" name="shippingCharges" value="<%=HiltonUtility.getCurrency(b_ship_amt, s_curr_code, oid)%>" style="text-align: right" onchange="distributeShipping();" onfocus="this.blur();"/><%if ( s_shipping.equals("Y") ) {%><A HREF="javascript: void(0);" ONCLICK="setUrl('shipping');"><IMG SRC="/puridiom/xchange/images/alert.gif" align="BOTTOM" BORDER="0"></A><%}%></tsa:td>
					<tsa:hidden name="RequisitionHeader_shippingCharges" value="<%=b_ship_amt%>"/>
					<% }%>
				</tsa:tr>
				<% if (!suppressForNonConsumables) {%>
				<tsa:tr>
					<tsa:td colspan="2" field="req-shippingTaxAmount">&nbsp;</tsa:td>
					<tsa:td align="RIGHT" cssClass="basic" colspan="2" field="req-shippingTaxAmount"><tsa:label labelName="req-shippingTaxAmount" defaultString="Shipping Tax"/>:&nbsp;</tsa:td>
					<tsa:td valign="CENTER" field="req-shippingTaxAmount"><tsa:input type="mintext" name="shippingTaxAmt" style="text-align: right" value="<%=HiltonUtility.getCurrency(requisitionHeader.getShippingTaxAmt(), s_curr_code, oid)%>" onchange="distributeShipTax();" onfocus="this.blur();"/></tsa:td>
					<tsa:hidden name="RequisitionHeader_shippingTaxAmt" value="<%=requisitionHeader.getShippingTaxAmt()%>"/>
				</tsa:tr>
				<% }%>
				<tsa:tr>
					<tsa:td rowspan="3" align="RIGHT" valign="TOP" cssClass="basic"><div <%=HtmlWriter.isVisible(oid, "req-otherDescription")%>><br> <tsa:label labelName="req-otherDescription" defaultString="Other Description"/>:</div>&nbsp;</tsa:td>
					<tsa:td rowspan="3" cssClass="basic"><div  <%=HtmlWriter.isVisible(oid, "req-otherDescription")%>><tsa:input type="textarea" cols="27" rows="4" readonly="READONLY" disabled="DISABLED">${esapi:encodeForHTML(s_other_charg_desc)}</tsa:input></div></tsa:td>
					<% if (!suppressForNonConsumables) { %>
					<tsa:td align="RIGHT" cssClass="basic" colspan="2" field="req-otherCharges">
						<%	if (allowEdit) {	%>
							<A HREF="javascript: void(0);" title="Click here to add Other Requisition Expenses for this requisition."ONCLICK="setUrl('other');"><tsa:label labelName="req-otherCharges" defaultString="Other"/></A>:&nbsp;
						<%	} else {	%>
							<tsa:label labelName="req-otherCharges" defaultString="Other"/>
						<%	}	%>
					</tsa:td>
					<tsa:td field="req-otherCharges"><tsa:input type="mintext" name="otherCharges" value="<%=HiltonUtility.getCurrency(b_other_amt, s_curr_code, oid)%>" style="text-align: right" onchange="distributeOther();" onfocus="this.blur();"/><%if ( s_other.equals("Y") ) {%><A HREF="javascript: void(0);" ONCLICK="setUrl('other');"><IMG SRC="/puridiom/xchange/images/alert.gif" align="BOTTOM" BORDER="0"></A><%}%></tsa:td>
					<tsa:hidden name="RequisitionHeader_otherCharges" value="<%=b_other_amt%>"/>
					<% } %>
				</tsa:tr>
				<% if (!suppressForNonConsumables) {%>
				<tsa:tr field="req-otherTaxAmount">
					<tsa:td align="RIGHT" cssClass="basic" colspan="2"><tsa:label labelName="req-otherTaxAmount" defaultString="Other Tax"/>:&nbsp;</tsa:td>
					<tsa:td valign="CENTER"><tsa:input type="mintext" name="otherTaxAmount" value="<%=HiltonUtility.getCurrency(b_other_tax_amt, s_curr_code, oid)%>" style="text-align: right" onchange="distributeOtherTax();" onfocus="this.blur();"/></tsa:td>
					<tsa:hidden name="RequisitionHeader_otherTaxAmount" value="<%=b_other_tax_amt%>"/>
				</tsa:tr>
				<% }%>
				<tsa:tr field="req-total">
					<tsa:td align="RIGHT" cssClass="processOn" colspan="2"><b><tsa:label labelName="req-total" defaultString="Total"/>:</b>&nbsp;</tsa:td>
					<tsa:td><tsa:input type="mintext" name="total" value="<%=HiltonUtility.getCurrency(b_total_amt, s_curr_code, oid)%>" style="text-align: right" onfocus="this.blur();"/></tsa:td>
					<tsa:hidden name="RequisitionHeader_total" value="<%=b_total_amt%>"></tsa:hidden>
				</tsa:tr>
				<tsa:tr field="req-estCost">
					<tsa:td field="req-estCost" align="right" colspan="4"><b><tsa:label labelName="req-estCost" defaultString="Est. Cost" checkRequired="true"/>:</b>&nbsp;</tsa:td>
					<tsa:td field="req-estCost"><tsa:input type="mintext" name="RequisitionHeader_estimatedCost" value="<%=b_estimated_cost%>" onchange="formatMe(this);" style="text-align: right"/></tsa:td>
				</tsa:tr>
				</TABLE>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
<%	if (s_view.equalsIgnoreCase("WIZARD")) { %>
	<tsa:td rowspan="2" align="right" valign="top"><%@ include file="/requests/req_sidebar.jsp" %></tsa:td>
<%	} %>
</tsa:tr>
</table>

<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
	<tsa:td width="50%" align="center"><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionHeaderUpdate;RequisitionRetrieve'); void(0);"><img class="button" src="<%=contextPath%>/images/button_save.gif" border="0"></a></tsa:td>
	<tsa:td width="50%" align="center"><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionRetrieve'); void(0);"><img class="button" src="<%=contextPath%>/images/button_return.gif" border="0"></a></tsa:td>
</tsa:tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value="JavaScript">
<!-- Hide script

	frm = document.purchaseform;
	var reqnumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
	var fiscalyear = "<%=s_fiscal_year%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var estimatedCost;
	if (frm.RequisitionHeader_estimatedCost != null || frm.RequisitionHeader_estimatedCost != undefined){
		estimatedCost = frm.RequisitionHeader_estimatedCost;
	}else{
		estimatedCost = null;
	}


	function setUrl(x)
	{
		popupParameters = "RequisitionHeader_icReqHeader=<%=bd_ic_req_header%>;reason=" + x;
		doSubmitToPopup('requests/req_totals_breakdown.jsp', 'RequisitionRetrieve', 'width=650', 'height=800');
/*
		var browser = browserTest();
		var url = "/puridiom/xchange/requests/req_totals_breakdown.jsp?reason=" + x;
		var waiver = trim(frm.rqh_bid_waiver.value);

		url = url + "&waiver=" + waiver;
		if (browser == "Netscape")
		{
			if (x=="other") {
				openWindow(url,"WIDTH=625","HEIGHT=500");
			} else if (x=="tax"){
				openTaxWindow(url,"WIDTH=500","HEIGHT=500");
			} else {
				openWindow(url,"WIDTH=450","HEIGHT=500");
			}
		}
		else
		{
			if (x=="other") {
				openWindow(url,"WIDTH=450","HEIGHT=500");
			} else if (x=="tax") {
				openTaxWindow(url,"WIDTH=450","HEIGHT=500");
			} else {
				openWindow(url,"WIDTH=400","HEIGHT=500");
			}
		}
*/
	}

	function formatMe(x)
	{
		var dollarDecimals = <%=s_dollar_decimals%>;


			if (frm.RequisitionHeader_estimatedCost[x])
			{
				var amount = eval(nfilter(frm.RequisitionHeader_estimatedCost[x]));
				if (frm.RequisitionHeader_estimatedCost[x].value != '')
				{
					frm.RequisitionHeader_estimatedCost[x].value = nformat(amount, dollarDecimals);
				}
			}
			else
			{
				var amount = eval(nfilter(frm.RequisitionHeader_estimatedCost));
				if (frm.RequisitionHeader_estimatedCost.value != '')
				{
					frm.RequisitionHeader_estimatedCost.value = nformat(amount, dollarDecimals);
				}
			}
	}

	function thisLoad()
	{
		f_StartIt();
<%		if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0) { %>
			checkInputSettings();
			if (estimatedCost != null)
			{
				estimatedCost.contentEditable = false;
				estimatedCost.disabled = true;
			}
<%		}  else if (allowEdit) {  %>
			checkInputSettings();
<%		}%>	}
// End Hide script -->
</SCRIPT>