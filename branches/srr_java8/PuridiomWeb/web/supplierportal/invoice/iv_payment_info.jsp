<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
	InvoiceVendor invoiceVendor = (InvoiceVendor) request.getAttribute("invoiceVendor");
	if (invoiceVendor == null)
	{
		invoiceVendor = new InvoiceVendor();
	}
	InvoiceAddress invoiceAddress = (InvoiceAddress) request.getAttribute("invoiceAddress");
	if (invoiceAddress == null)
	{
		invoiceAddress = new InvoiceAddress();
		InvoiceAddressPK comp_id = new InvoiceAddressPK();
		invoiceAddress.setComp_id(comp_id);
	}

	List	vendorNameList = (List) request.getAttribute("vendorNameList");
	boolean bCreatedFromPo = false;
	if ((invoiceHeader.getIcPoHeader().compareTo(new BigDecimal(0)) > 0))
	{
		bCreatedFromPo = true;
	}
	String	s_current_process = "PAYMENT_INFO";
	String	s_current_page = "/invoice/iv_payment_info.jsp";
	String	s_current_method = "InvoiceVendorUpdate";
	String	s_current_process_method = "";

	String	duplicateVendorErrorMsg = (String) request.getAttribute("duplicateVendorErrorMsg");
%>
<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=invoiceHeader.getStatus()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="formType" value="IVH"/>
<tsa:hidden name="allowBrowse" value=""/>
<tsa:hidden name="currentPage" value="/invoice/iv_payment_info.jsp"/>
<tsa:hidden name="duplicateVendorFailurePage" value="/invoice/iv_payment_info.jsp"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Payment Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Invoice #:</b></td>
			<td width=150px><%=invoiceHeader.getInvoiceNumber()%></td>
		</tr>
		<tr>
			<td align=right><b>Status:</b></td>
			<td width=150px><%=DocumentStatus.toString(invoiceHeader.getStatus(), oid)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr>
			<td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<%	if (!HiltonUtility.isEmpty(duplicateVendorErrorMsg)) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center class="error">
		<%=duplicateVendorErrorMsg%>
	</td>
</tr>
</table>

<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center>
		<table border=0 cellspacing=0 cellpadding=0 height=100% width="100%">
		<tr>
			<td valign="top">
				<table border=0 cellpadding=0 cellspacing=0 width="100%">
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-supplier")%> nowrap>
						<%=DictionaryManager.getLabel(oid, "ivc-supplier", "Supplier", true)%>:&nbsp;
					</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-supplier")%> >
						<input type="text" title="Enter Supplier ID" name="InvoiceVendor_vendorId" tabindex="1" size="20" maxlength="15" value="<%=invoiceHeader.getVendorId()%>" READONLY DISABLED>
						<tsa:hidden name="InvoiceHeader_vendorId" value="<%=invoiceHeader.getVendorId()%>"/>
						<tsa:hidden name="InvoiceAddress_vendorId" value="<%=invoiceAddress.getComp_id().getVendorId()%>"/>
						<tsa:hidden name="InvoiceAddress_addressCode" value="<%=invoiceAddress.getComp_id().getAddressCode()%>"/>
						<tsa:hidden name="InvoiceHeader_vendorAddrCode" value="<%=invoiceHeader.getVendorAddrCode()%>"/>
						<tsa:hidden name="InvoiceHeader_vendorName" value="<%=invoiceHeader.getVendorName()%>"/>
						<tsa:hidden name="InvoiceVendor_vendorName" value="<%=invoiceVendor.getVendorName()%>"/>
						<tsa:hidden name="originalVendorId" value="<%=invoiceHeader.getVendorId()%>"/>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-remitToAddress")%>>
					<td align="right" nowrap>
						<!--<a href="javascript: browseContactAddress('InvoiceVendor_addressLine1', frm.InvoiceHeader_vendorId.value); void(0);" title="Click here to choose the <%=DictionaryManager.getLabel(oid, "ivc-remitToAddress", "Remit To Address")%> for this order or enter the <%=DictionaryManager.getLabel(oid, "ivc-sup-addressLine1", "Remit To Address")%> in the box on the right.">--><%=DictionaryManager.getLabel(oid, "ivc-remitToAddress", "Remit To Address")%></a>:&nbsp;
					</td>
					<td><input type="text" name="InvoiceAddress_addressLine1" tabindex="5"  size="20" maxlength="40" value="<%=invoiceAddress.getAddressLine1()%>" onchange="clearVendorId(); setVendorName();" READONLY DISABLED></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine2")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabel(oid, "ivc-sup-addressLine2", "Address 2")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceAddress_addressLine2" tabindex="7"  size="20" maxlength="40" value="<%=invoiceAddress.getAddressLine2()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine3")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabel(oid, "ivc-sup-addressLine3", "Address 3")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceAddress_addressLine3" tabindex="9"  size="20" maxlength="40" value="<%=invoiceAddress.getAddressLine3()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine4")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabel(oid, "ivc-sup-addressLine4", "Address 4")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceAddress_addressLine4" tabindex="11"  size="20" maxlength="40" value="<%=invoiceAddress.getAddressLine4()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-city")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabel(oid, "ivc-sup-city", "City")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceAddress_city" tabindex="13"  size="20" maxlength="40" value="<%=invoiceAddress.getCity()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-state")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabel(oid, "ivc-sup-state", "State")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceAddress_state" tabindex="15"  size="20" maxlength="40" value="<%=invoiceAddress.getState()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-zip")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabel(oid, "ivc-sup-zip", "Zip/Postal Code")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceAddress_postalCode" tabindex="17"  size="20" maxlength="40" value="<%=invoiceAddress.getPostalCode()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-country")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabel(oid, "ivc-sup-country", "Country")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceAddress_country" tabindex="19"  size="20" maxlength="40" value="<%=invoiceAddress.getCountry()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-ap-reference")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabel(oid, "ivc-ap-reference", "AP Reference")%>:&nbsp;</td>
					<td>
						<input type="text" name="InvoiceHeader_apReference" tabindex="21"  size="20" maxlength="20" value="<%=invoiceHeader.getApReference()%>" onchange="setApReference();">
						<tsa:hidden name="InvoiceVendor_apReference" value="<%=invoiceVendor.getApReference()%>"/>
					</td>
				</tr>
				<tr><td colspan="2"><br><br><b>&nbsp;SPECIAL INSTRUCTIONS</b></td></tr>
				<tr>
					<td colspan="2" align="right"><textarea wrap="virtual" name="InvoiceHeader_specialInst" tabindex="23" rows=8 cols=40>${esapi:encodeForHTML(invoiceHeader.specialInst)}</textarea></td>
				</tr>
				</table>
			</td>
			<td width="100%">&nbsp;</td>
			<td valign="top">
				<table border=0 cellpadding=0 cellspacing=0 width="100%">
				<tr><td colspan="2"><b>For billing questions please contact:</b></td></tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-contactName")%> nowrap><%=DictionaryManager.getLabel(oid, "ivc-contactName", "Name", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-contactName")%> ><input type=text title="Enter Contact Name" name="InvoiceHeader_contactName" tabindex="31" size="20" maxlength="40" value="<%=invoiceHeader.getContactName()%>"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-contactEmail")%> nowrap><%=DictionaryManager.getLabel(oid, "ivc-contactEmail", "Email", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-contactEmail")%> ><input type=text title="Enter Email Address" name="InvoiceHeader_contactEmail" tabindex="33" size="40" maxlength="60" value="<%=invoiceHeader.getContactEmail()%>">	</td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-contactPhone")%> nowrap><%=DictionaryManager.getLabel(oid, "ivc-contactPhone", "Phone", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-contactPhone")%> ><input type=text title="Enter Telephone Number" name="InvoiceHeader_contactPhone" tabindex="35" size="25" maxlength="14" value="<%=invoiceHeader.getContactPhone()%>"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-contactFax")%> nowrap><%=DictionaryManager.getLabel(oid, "ivc-contactFax", "Fax", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-contactFax")%> ><input type=text title="Enter Fax Number" name="InvoiceHeader_contactFax" tabindex="37" size="25" maxlength="14" value="<%=invoiceHeader.getContactFax()%>">	</td>
				</tr>
				<tr><td colspan="2"><br><b>PAYMENT TERMS</b></td></tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-prePaid")%> nowrap><%=DictionaryManager.getLabel(oid, "ivc-prePaid", "Pre-Paid", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-prePaid")%> >
						<input type="checkbox" name="c_checkbox" tabindex="41" <% if (invoiceHeader.getPrePaid().equals("Y")) { %>CHECKED<% } %> value="Y" onclick="setCheckbox(frm.InvoiceHeader_prePaid,0);">
						<tsa:hidden name="InvoiceHeader_prePaid" value="<%=invoiceHeader.getPrePaid()%>"/>
					</td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "invoiceTerms")%> nowrap>
						<a href="javascript: browseLookup('InvoiceHeader_termsCode', 'payment-terms'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabel(oid, "iv-paymentTerms", "Terms")%> for this order or enter the <%=DictionaryManager.getLabel(oid, "iv-paymentTerms", "Terms")%> in the box on the right."><%=DictionaryManager.getLabel(oid, "iv-paymentTerms", "Terms", true)%></a>:&nbsp;
					</td>
					<td <%=HtmlWriter.isVisible(oid, "invoiceTerms")%> ><input type=text title="Enter Payment Terms" name="InvoiceHeader_termsCode" tabindex="41" size=15 value="<%=invoiceHeader.getTermsCode()%>" onchange="upperCase(this);"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "iv-fob")%> nowrap>
						<%=DictionaryManager.getLabel(oid, "iv-discount", "Discount", true)%>:&nbsp;
					</td>
					<td <%=HtmlWriter.isVisible(oid, "iv-fob")%> >
						<input type=text title="Enter FOB" name="InvoiceHeader_termsDiscperc" tabindex="43" size=5 value="<%=Utility.getBigDecimalFormatted(invoiceHeader.getTermsDiscperc(), 0)%>" style="text-align:right">%&nbsp;&nbsp;
						<input type=text title="Enter FOB" name="InvoiceHeader_termsDiscdays" tabindex="45" size=5 value="<%=Utility.getBigDecimalFormatted(invoiceHeader.getTermsDiscdays(), 0)%>" style="text-align:right">
						<%=DictionaryManager.getLabel(oid, "iv-discount", "Days", true)%>
					</td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "iv-fob")%> nowrap>
						<%=DictionaryManager.getLabel(oid, "iv-discount", "Discount", true)%>:&nbsp;
					</td>
					<td <%=HtmlWriter.isVisible(oid, "iv-fob")%> >
						<input type=text title="Enter FOB" name="InvoiceHeader_terms2Discperc" tabindex="47" size=5 value="<%=Utility.getBigDecimalFormatted(invoiceHeader.getTerms2Discperc(), 0)%>" style="text-align:right">%&nbsp;&nbsp;
						<input type=text title="Enter FOB" name="InvoiceHeader_terms2Discdays" tabindex="49" size=5 value="<%=Utility.getBigDecimalFormatted(invoiceHeader.getTerms2Discdays(), 0)%>" style="text-align:right">
						<%=DictionaryManager.getLabel(oid, "iv-discount", "Days", true)%>
					</td>
				</tr>
				<tr><td colspan="2"><br><br><b>EFT INFORMATION</b></td></tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "bankName")%> nowrap><%=DictionaryManager.getLabel(oid, "bankName", "Bank Name", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "bankName")%> ><input type=text title="Enter Bank Name" name="InvoiceHeader_eftBankName" tabindex="51" size="35" maxlength="40" value="<%=invoiceHeader.getEftBankName()%>"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "bankAccount")%> nowrap><%=DictionaryManager.getLabel(oid, "bankAccount", "Account #", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "bankAccount")%> ><input type=text title="Enter Bank Account #" name="InvoiceHeader_eftBankAccount" tabindex="53" size="35" maxlength="20" value="<%=invoiceHeader.getEftBankAccount()%>"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "eftAbaAch")%> nowrap><%=DictionaryManager.getLabel(oid, "eftAbaAch", "ACH ABA", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "eftAbaAch")%> ><input type=text title="Enter ACH ABA" name="InvoiceHeader_eftAbaAch" tabindex="55" size="20" maxlength="20" value="<%=invoiceHeader.getEftAbaAch()%>"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "eftAbaWire")%> nowrap><%=DictionaryManager.getLabel(oid, "eftAbaWire", "WIRE ABA", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "eftAbaWire")%> ><input type=text title="Enter Wire ABA" name="InvoiceHeader_eftAbaWire" tabindex="57" size="20" maxlength="20" value="<%=invoiceHeader.getEftAbaWire()%>"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "federalId")%> nowrap><%=DictionaryManager.getLabel(oid, "federalId", "Federal ID", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "federalId")%> ><input type=text title="Enter Federal ID" name="InvoiceHeader_federalId" tabindex="59" size="20" maxlength="20" value="<%=invoiceHeader.getFederalId()%>"></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
	<td rowspan="2" align="right" valign="top"><%@ include file="/supplierportal/invoice/iv_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.allowBrowse.value = "true";

	function thisLoad()
	{
		checkInputSettings();
<%	if (invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_INPROGRESS) > 0) { %>
			allowEdit = false;
<%	} %>
	}

	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	function clearVendorId()
	{
		frm.InvoiceHeader_vendorId.value = "";
		frm.InvoiceHeader_vendorAddrCode.value = "";
		frm.InvoiceVendor_vendorId.value = "";
		frm.InvoiceAddress_vendorId.value = "";
		frm.InvoiceAddress_addressCode.value = "";
		frm.InvoiceHeader_apReference.value = "";
		frm.InvoiceVendor_apReference.value = "";
	}

	function setVendorName()
	{
		frm.InvoiceHeader_vendorName.value = frm.InvoiceAddress_addressLine1.value;
		frm.InvoiceVendor_vendorName.value = frm.InvoiceAddress_addressLine1.value;
	}

	function setApReference()
	{
		frm.InvoiceVendor_apReference.value = frm.InvoiceHeader_apReference.value;
	}

// End Hide script -->
</SCRIPT>