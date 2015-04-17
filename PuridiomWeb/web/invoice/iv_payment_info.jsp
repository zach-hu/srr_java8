<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/actb.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/common.js"></SCRIPT>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
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

	//List	vendorNameList = (List) request.getAttribute("vendorNameList");
	boolean bCreatedFromPo = false;
	if ((invoiceHeader.getIcPoHeader().compareTo(new BigDecimal(0)) > 0))
	{
		bCreatedFromPo = true;
	}
	String	s_ivc_number = invoiceHeader.getInvoiceNumber();
	String	s_ivc_status = invoiceHeader.getStatus();
	String	s_pcardNumber = (String) HiltonUtility.ckNull(invoiceHeader.getPcardNumber());
	String	s_pcardHolder = (String) HiltonUtility.ckNull(invoiceHeader.getPcardHolder());
	String	s_subPcardNumber = "";
		if (s_pcardNumber.length() > 4) {
			s_subPcardNumber = s_pcardNumber.substring(s_pcardNumber.length() - 4, s_pcardNumber.length());
		} else {
			s_subPcardNumber = s_pcardNumber;
		}
		if (s_subPcardNumber.length() > 0) {
			s_subPcardNumber = "************" + s_subPcardNumber;
		}
		String	s_expYear = "";
		String	s_expMonth = "";
		if (invoiceHeader.getPcardExpires().length() > 0) {
			s_expYear = invoiceHeader.getPcardExpires().substring(3,7);
			s_expMonth = invoiceHeader.getPcardExpires().substring(0,3);
		}

	String	duplicateVendorErrorMsg = (String) request.getAttribute("duplicateVendorErrorMsg");
	String	s_current_process = "PAYMENT_INFO";
	String	s_current_page = "/invoice/iv_payment_info.jsp";
	String	s_current_method = "InvoiceVendorUpdate";
	String	s_current_process_method = "";

	String	notAllowPaymentInfoEdit = (String)propertiesManager.getProperty("VOUCHER OPTIONS","NOTALLOWPAYMENTINFORMATIONEDIT", "N");
%>
<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=invoiceHeader.getStatus()%>"/>
<tsa:hidden name="InvoiceHeader_invoiceDate_2" value="<%=invoiceHeader.getInvoiceDate()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="formType" value="IVH"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="currentPage" value="/invoice/iv_payment_info.jsp"/>
<tsa:hidden name="duplicateVendorFailurePage" value="/invoice/iv_payment_info.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Payment Information</div>
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

<%	if (!HiltonUtility.isEmpty(duplicateVendorErrorMsg)) { %>
<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top align=center class="error">
		<%=duplicateVendorErrorMsg%>
	</td>
</tr>
</table>

<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top align=center>
		<table border=0 cellspacing=0 cellpadding=0 height=100% width="100%">
		<tr>
			<td valign="top">
				<table border=0 cellpadding=0 cellspacing=0 width="100%">
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-supplier")%> nowrap>
						<%	if (!bCreatedFromPo) { %><a href="javascript: browseSupplier(); void(0);" title="Click here to choose the Supplier for this invoice or enter the Supplier ID in the box on the right."><% } %><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-supplier", "Supplier", true)%><%	if (!bCreatedFromPo) { %></a><% } %>:&nbsp;
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
						<a href="javascript: browseContactAddress('InvoiceAddress_addressLine1', frm.InvoiceHeader_vendorId.value); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-remitToAddress", "Remit To Address")%> for this order or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-sup-addressLine1", "Remit To Address")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-remitToAddress", "Remit To Address", true)%></a>:&nbsp;
					</td>
					<td><input type="text" name="InvoiceAddress_addressLine1" tabindex="5"  size="20" maxlength="40" value="<%=invoiceAddress.getAddressLine1()%>" onchange="clearVendorId(); setVendorName();" <% if (bCreatedFromPo) { %>READONLY DISABLED<% } %> ></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine2")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-sup-addressLine2", "Address 2")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceAddress_addressLine2" tabindex="7"  size="20" maxlength="40" value="<%=invoiceAddress.getAddressLine2()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine3")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-sup-addressLine3", "Address 3")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceAddress_addressLine3" tabindex="9"  size="20" maxlength="40" value="<%=invoiceAddress.getAddressLine3()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine4")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-sup-addressLine4", "Address 4")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceAddress_addressLine4" tabindex="11"  size="20" maxlength="40" value="<%=invoiceAddress.getAddressLine4()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-city")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-sup-city", "City")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceAddress_city" tabindex="13"  size="20" maxlength="40" value="<%=invoiceAddress.getCity()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-state")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-sup-state", "State")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceAddress_state" tabindex="15"  size="20" maxlength="40" value="<%=invoiceAddress.getState()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-zip")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-sup-zip", "Zip/Postal Code")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceAddress_postalCode" tabindex="17"  size="20" maxlength="40" value="<%=invoiceAddress.getPostalCode()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-country")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-sup-country", "Country")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceAddress_country" tabindex="19"  size="20" maxlength="40" value="<%=invoiceAddress.getCountry()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-apreference")%>>
					<tsa:td field="ivc-apreference" width="" align="right" noWrap="nowrap" >
					<tsa:label labelName="ivc-apreference" defaultString="AP Reference" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="ivc-apreference" >
					<tsa:input type="midtext" name="InvoiceHeader_apReference" tabIndex="21" maxLength="20" value="<%=invoiceHeader.getApReference()%>" onchange="upperCase(this); setApReference();"></tsa:input>
					<tsa:hidden name="InvoiceVendor_apReference" value="<%=invoiceVendor.getApReference()%>"/>
					</tsa:td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-vendoraccount")%>>
					<td align="right" nowrap>
<%	if (DictionaryManager.isLink(oid, "ivc-vendoraccount")) { %>
						<a href="javascript: browseVendorAccount(); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-vendoraccount", "Vendor Account")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-vendoraccount", "Vendor Account", true)%>:</a>&nbsp;
<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-vendoraccount", "Vendor Account", true)%>:&nbsp;
<%	}	%>
					</td>
					<td><input type="text" name="InvoiceHeader_vendorAccount" tabindex="21"  size="20" maxlength="20" value="<%=invoiceHeader.getVendorAccount()%>" <%	if (DictionaryManager.isLink(oid, "ivc-vendoraccount")) { %>READONLY DISABLED<%}%>></td>
				</tr>
				<tr><td colspan="2"><br><br><b>&nbsp;SPECIAL INSTRUCTIONS</b></td></tr>
				<tr>
					<td colspan="2" align="right"><textarea wrap="virtual" name="InvoiceHeader_specialInst" tabindex="23" rows="8" cols="40" onKeyDown="textCounter(this, 255);" onKeyUp="textCounter(this,255);">${esapi:encodeForHTML(invoiceHeader.specialInst)}</textarea></td>
				</tr>
				</table>
			</td>
			<td width="100%">&nbsp;</td>
			<td valign="top">
				<table border=0 cellpadding=0 cellspacing=0 width="100%">
				<tr><td colspan="2" <%=HtmlWriter.isVisible(oid, "ivc-billingQuestions")%>><b>For billing questions please contact:</b></td></tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-contactName")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-contactName", "Name", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-contactName")%> ><input type=text title="Enter Contact Name" name="InvoiceHeader_contactName" tabindex="31" size="20" maxlength="40" value="<%=invoiceHeader.getContactName()%>"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-contactEmail")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-contactEmail", "Email", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-contactEmail")%> ><input type=text title="Enter Email Address" name="InvoiceHeader_contactEmail" tabindex="33" size="35" maxlength="60" value="<%=invoiceHeader.getContactEmail()%>">	</td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-contactPhone")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-contactPhone", "Phone", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-contactPhone")%> ><input type=text title="Enter Telephone Number" name="InvoiceHeader_contactPhone" tabindex="35" size="25" maxlength="14" value="<%=invoiceHeader.getContactPhone()%>"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-contactFax")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-contactFax", "Fax", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-contactFax")%> ><input type=text title="Enter Fax Number" name="InvoiceHeader_contactFax" tabindex="37" size="25" maxlength="14" value="<%=invoiceHeader.getContactFax()%>">	</td>
				</tr>
				<tr><td colspan="2"><br><b>PAYMENT TERMS</b></td></tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-prePaid")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-prePaid", "Pre-Paid", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-prePaid")%> >
						<input type="checkbox" name="c_checkbox" tabindex="41" <% if (invoiceHeader.getPrePaid().equals("Y")) { %>CHECKED<% } %> value="Y" onclick="setCheckbox(frm.InvoiceHeader_prePaid,0);">
						<tsa:hidden name="InvoiceHeader_prePaid" value="<%=invoiceHeader.getPrePaid()%>"/>
					</td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "invoiceTerms")%> nowrap>
						<a href="javascript: browseLookup('InvoiceHeader_termsCode', 'payment-terms'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-paymentTerms", "Terms")%> for this order or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-paymentTerms", "Terms")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-paymentTerms", "Terms", true)%></a>:&nbsp;
					</td>
					<td <%=HtmlWriter.isVisible(oid, "invoiceTerms")%> ><input type=text title="Enter Payment Terms" name="InvoiceHeader_termsCode" tabindex="41" size=15 value="<%=invoiceHeader.getTermsCode()%>" onchange="upperCase(this);"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "paymentDate")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "paymentDate", "Payment Date")%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "paymentDate")%> >
						<input type=text title="Enter Payment Date" name="InvoiceHeader_paymentDate" tabindex="25" size="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getPaymentDate(), oid, userDateFormat)%>">
					</td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-discount")%> nowrap>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-discount", "Discount", true)%>:&nbsp;
					</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-discount")%> nowrap>
						<input type=text name="InvoiceHeader_termsDiscperc" tabindex="43" size=5 value="<%=Utility.getBigDecimalFormatted(invoiceHeader.getTermsDiscperc(), 0)%>" style="text-align:right">%&nbsp;&nbsp;
						<input type=text name="InvoiceHeader_termsDiscdays" tabindex="45" size=5 value="<%=Utility.getBigDecimalFormatted(invoiceHeader.getTermsDiscdays(), 0)%>" style="text-align:right">
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-discountDays", "Days", true)%>
					</td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-discount2")%> nowrap>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-discount2", "Discount", true)%>:&nbsp;
					</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-discount2")%> nowrap>
						<input type=text name="InvoiceHeader_terms2Discperc" tabindex="47" size=5 value="<%=Utility.getBigDecimalFormatted(invoiceHeader.getTerms2Discperc(), 0)%>" style="text-align:right">%&nbsp;&nbsp;
						<input type=text name="InvoiceHeader_terms2Discdays" tabindex="49" size=5 value="<%=Utility.getBigDecimalFormatted(invoiceHeader.getTerms2Discdays(), 0)%>" style="text-align:right">
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-discountDays", "Days", true)%>
					</td>
				</tr>
				<tr>
					<tsa:td field="ivc-discountDate" width="" align="right" noWrap="nowrap" >
					<tsa:label labelName="ivc-discountDate" defaultString="Discount Date" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="ivc-discountDate" >
					<tsa:input type="midtext" title="Enter Discount Date" name="InvoiceHeader_discountDate" tabIndex="3" size="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getDiscountDate(), oid, userDateFormat)%>"></tsa:input>
					</tsa:td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-pcardName")%>><td colspan="2"><br><br><b>CREDIT CARD</b></td></tr>
				<!--tr>
					<td>
					<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0" CLASS="basic"-->
		<% if (oid.equals("SRR10P")){ %>
		<TR <%=HtmlWriter.isVisible(oid, "ivc-pcardName")%>>
					<TD ALIGN="RIGHT" CLASS="basic" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-pcardName", "Card Type")%>:&nbsp;&nbsp;</TD>
					<TD>
						<SELECT NAME="InvoiceHeader_pcardName" disabled>
						<OPTION value="" <%if (invoiceHeader.getPcardName().equalsIgnoreCase("")) {%>SELECTED<%}%>></VALUE>
<%
		Map paymentTypes = DictionaryManager.getInstance("payment-type", oid).getPropertyMap();
		Iterator typeIterator = paymentTypes.keySet().iterator();
		String pCardType = "";
		String pCardName = "";
		while (typeIterator.hasNext())
		{
			pCardType = (String) typeIterator.next();
			pCardName = (String) paymentTypes.get(pCardType);
%>
						<OPTION value="<%=pCardType%>" <%if (invoiceHeader.getPcardName().equalsIgnoreCase(pCardType)) {%>SELECTED<%}%>><%=pCardName%></VALUE>
<%	}	%>
						</SELECT>
					</TD>
				</TR>
				<TR <%=HtmlWriter.isVisible(oid, "ivc-pcardNumber")%>>
					<TD ALIGN="RIGHT" CLASS="basic"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-pcardNumber", "Card #", true)%>:&nbsp;</TD>
					<TD><INPUT TYPE="TEXT" NAME="as_pcardNumber" TABINDEX=1 SIZE="22" MAXLENGTH="20" value="<%=s_subPcardNumber%>" ONCHANGE="upperCase(this); setPcardNumber(this);" disabled>
						<tsa:hidden name="InvoiceHeader_pcardNumber" value="<%=s_pcardNumber%>"/>
					</TD>
				</TR>
				<TR <%=HtmlWriter.isVisible(oid, "ivc-pcardHolder")%>>
					<TD ALIGN="RIGHT" CLASS="basic" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-pcardHolder", "Name", true)%>:&nbsp;</TD>
					<TD><INPUT TYPE="TEXT" NAME="InvoiceHeader_pcardHolder" TABINDEX=1 SIZE="22" MAXLENGTH="45" value="<%=s_pcardHolder%>" ONCHANGE="upperCase(this); " disabled>
					</TD>
				</TR>
				<TR <%=HtmlWriter.isVisible(oid, "ivc-pcardExpirationDate")%>>
					<TD ALIGN="RIGHT" CLASS="basic" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-pcardExpirationDate", "Date Expires")%>:&nbsp;</TD>
					<TD><SELECT NAME="as_expMonth" ONCHANGE="setExpirationDate();" disabled>
							<OPTION value="" <%if (s_expMonth.equalsIgnoreCase("")) {%>SELECTED<%}%>></OPTION>
							<OPTION value="JAN" <%if (s_expMonth.equalsIgnoreCase("JAN")) {%>SELECTED<%}%>>Jan</OPTION>
							<OPTION value="FEB" <%if (s_expMonth.equalsIgnoreCase("FEB")) {%>SELECTED<%}%>>Feb</OPTION>
							<OPTION value="MAR" <%if (s_expMonth.equalsIgnoreCase("MAR")) {%>SELECTED<%}%>>Mar</OPTION>
							<OPTION value="APR" <%if (s_expMonth.equalsIgnoreCase("APR")) {%>SELECTED<%}%>>Apr</OPTION>
							<OPTION value="MAY" <%if (s_expMonth.equalsIgnoreCase("MAY")) {%>SELECTED<%}%>>May</OPTION>
							<OPTION value="JUN" <%if (s_expMonth.equalsIgnoreCase("JUN")) {%>SELECTED<%}%>>Jun</OPTION>
							<OPTION value="JUL" <%if (s_expMonth.equalsIgnoreCase("JUL")) {%>SELECTED<%}%>>Jul</OPTION>
							<OPTION value="AUG" <%if (s_expMonth.equalsIgnoreCase("AUG")) {%>SELECTED<%}%>>Aug</OPTION>
							<OPTION value="SEP" <%if (s_expMonth.equalsIgnoreCase("SEP")) {%>SELECTED<%}%>>Sep</OPTION>
							<OPTION value="OCT" <%if (s_expMonth.equalsIgnoreCase("OCT")) {%>SELECTED<%}%>>Oct</OPTION>
							<OPTION value="NOV" <%if (s_expMonth.equalsIgnoreCase("NOV")) {%>SELECTED<%}%>>Nov</OPTION>
							<OPTION value="DEC" <%if (s_expMonth.equalsIgnoreCase("DEC")) {%>SELECTED<%}%>>Dec</OPTION>
						</SELECT>
						<SELECT NAME="as_expYear" ONCHANGE="setExpirationDate();" disabled>
							<OPTION value="" <%if (s_expYear.equalsIgnoreCase("")) {%>SELECTED<%}%>></OPTION>
							<OPTION value="2005" <%if (s_expYear.equalsIgnoreCase("2005")) {%>SELECTED<%}%>>2005</OPTION>
							<OPTION value="2006" <%if (s_expYear.equalsIgnoreCase("2006")) {%>SELECTED<%}%>>2006</OPTION>
							<OPTION value="2007" <%if (s_expYear.equalsIgnoreCase("2007")) {%>SELECTED<%}%>>2007</OPTION>
							<OPTION value="2008" <%if (s_expYear.equalsIgnoreCase("2008")) {%>SELECTED<%}%>>2008</OPTION>
							<OPTION value="2009" <%if (s_expYear.equalsIgnoreCase("2009")) {%>SELECTED<%}%>>2009</OPTION>
							<OPTION value="2010" <%if (s_expYear.equalsIgnoreCase("2010")) {%>SELECTED<%}%>>2010</OPTION>
						</SELECT>
						<tsa:hidden name="InvoiceHeader_pcardExpires" value="<%=invoiceHeader.getPcardExpires()%>"/>
					</TD>
				</TR>
		<% } else { %>
				<TR <%=HtmlWriter.isVisible(oid, "ivc-pcardName")%>>
					<TD ALIGN="RIGHT" CLASS="basic" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-pcardName", "Card Type")%>:&nbsp;&nbsp;</TD>
					<TD>
						<SELECT NAME="InvoiceHeader_pcardName">
						<OPTION value="" <%if (invoiceHeader.getPcardName().equalsIgnoreCase("")) {%>SELECTED<%}%>></VALUE>
<%
		Map paymentTypes = DictionaryManager.getInstance("payment-type", oid).getPropertyMap();
		Iterator typeIterator = paymentTypes.keySet().iterator();
		String pCardType = "";
		String pCardName = "";
		while (typeIterator.hasNext())
		{
			pCardType = (String) typeIterator.next();
			pCardName = (String) paymentTypes.get(pCardType);
%>
						<OPTION value="<%=pCardType%>" <%if (invoiceHeader.getPcardName().equalsIgnoreCase(pCardType)) {%>SELECTED<%}%>><%=pCardName%></VALUE>
<%	}	%>
						</SELECT>
					</TD>
				</TR>
				<TR <%=HtmlWriter.isVisible(oid, "ivc-pcardNumber")%>>
					<TD ALIGN="RIGHT" CLASS="basic"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-pcardNumber", "Card #", true)%>:&nbsp;</TD>
					<TD><INPUT TYPE="TEXT" NAME="as_pcardNumber" TABINDEX=1 SIZE="22" MAXLENGTH="20" value="<%=s_subPcardNumber%>" ONCHANGE="upperCase(this); setPcardNumber(this);">
						<tsa:hidden name="InvoiceHeader_pcardNumber" value="<%=s_pcardNumber%>"/>
					</TD>
				</TR>
				<TR <%=HtmlWriter.isVisible(oid, "ivc-pcardHolder")%>>
					<TD ALIGN="RIGHT" CLASS="basic" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-pcardHolder", "Name", true)%>:&nbsp;</TD>
					<TD><INPUT TYPE="TEXT" NAME="InvoiceHeader_pcardHolder" TABINDEX=1 SIZE="22" MAXLENGTH="45" value="<%=s_pcardHolder%>" ONCHANGE="upperCase(this); ">
					</TD>
				</TR>
				<TR <%=HtmlWriter.isVisible(oid, "ivc-pcardExpirationDate")%>>
					<TD ALIGN="RIGHT" CLASS="basic" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-pcardExpirationDate", "Date Expires")%>:&nbsp;</TD>
					<TD><SELECT NAME="as_expMonth" ONCHANGE="setExpirationDate();">
							<OPTION value="" <%if (s_expMonth.equalsIgnoreCase("")) {%>SELECTED<%}%>></OPTION>
							<OPTION value="JAN" <%if (s_expMonth.equalsIgnoreCase("JAN")) {%>SELECTED<%}%>>Jan</OPTION>
							<OPTION value="FEB" <%if (s_expMonth.equalsIgnoreCase("FEB")) {%>SELECTED<%}%>>Feb</OPTION>
							<OPTION value="MAR" <%if (s_expMonth.equalsIgnoreCase("MAR")) {%>SELECTED<%}%>>Mar</OPTION>
							<OPTION value="APR" <%if (s_expMonth.equalsIgnoreCase("APR")) {%>SELECTED<%}%>>Apr</OPTION>
							<OPTION value="MAY" <%if (s_expMonth.equalsIgnoreCase("MAY")) {%>SELECTED<%}%>>May</OPTION>
							<OPTION value="JUN" <%if (s_expMonth.equalsIgnoreCase("JUN")) {%>SELECTED<%}%>>Jun</OPTION>
							<OPTION value="JUL" <%if (s_expMonth.equalsIgnoreCase("JUL")) {%>SELECTED<%}%>>Jul</OPTION>
							<OPTION value="AUG" <%if (s_expMonth.equalsIgnoreCase("AUG")) {%>SELECTED<%}%>>Aug</OPTION>
							<OPTION value="SEP" <%if (s_expMonth.equalsIgnoreCase("SEP")) {%>SELECTED<%}%>>Sep</OPTION>
							<OPTION value="OCT" <%if (s_expMonth.equalsIgnoreCase("OCT")) {%>SELECTED<%}%>>Oct</OPTION>
							<OPTION value="NOV" <%if (s_expMonth.equalsIgnoreCase("NOV")) {%>SELECTED<%}%>>Nov</OPTION>
							<OPTION value="DEC" <%if (s_expMonth.equalsIgnoreCase("DEC")) {%>SELECTED<%}%>>Dec</OPTION>
						</SELECT>
						<SELECT NAME="as_expYear" ONCHANGE="setExpirationDate();">
							<OPTION value="" <%if (s_expYear.equalsIgnoreCase("")) {%>SELECTED<%}%>></OPTION>
							<OPTION value="2005" <%if (s_expYear.equalsIgnoreCase("2005")) {%>SELECTED<%}%>>2005</OPTION>
							<OPTION value="2006" <%if (s_expYear.equalsIgnoreCase("2006")) {%>SELECTED<%}%>>2006</OPTION>
							<OPTION value="2007" <%if (s_expYear.equalsIgnoreCase("2007")) {%>SELECTED<%}%>>2007</OPTION>
							<OPTION value="2008" <%if (s_expYear.equalsIgnoreCase("2008")) {%>SELECTED<%}%>>2008</OPTION>
							<OPTION value="2009" <%if (s_expYear.equalsIgnoreCase("2009")) {%>SELECTED<%}%>>2009</OPTION>
							<OPTION value="2010" <%if (s_expYear.equalsIgnoreCase("2010")) {%>SELECTED<%}%>>2010</OPTION>
						</SELECT>
						<tsa:hidden name="InvoiceHeader_pcardExpires" value="<%=invoiceHeader.getPcardExpires()%>"/>
					</TD>
				</TR>
				<!--/TABLE>
					</td>
				</tr-->



				<tr><td colspan="2" <%=HtmlWriter.isVisible(oid, "ivc-eftInformation")%> ><br><br><b>EFT INFORMATION</b></td></tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-eftBankName")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bankName", "Bank Name", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-eftBankName")%> ><input type=text title="Enter Bank Name" name="InvoiceHeader_eftBankName" tabindex="51" size="35" maxlength="40" value="<%=invoiceHeader.getEftBankName()%>"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-eftBankAccount")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bankAccount", "Account #", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-eftBankAccount")%> ><input type=text title="Enter Bank Account #" name="InvoiceHeader_eftBankAccount" tabindex="53" size="35" maxlength="20" value="<%=invoiceHeader.getEftBankAccount()%>"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-eftAbaAch")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "eftAbaAch", "ACH ABA", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-eftAbaAch")%> ><input type=text title="Enter ACH ABA" name="InvoiceHeader_eftAbaAch" tabindex="55" size="20" maxlength="20" value="<%=invoiceHeader.getEftAbaAch()%>"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-eftAbaWire")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "eftAbaWire", "WIRE ABA", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-eftAbaWire")%> ><input type=text title="Enter Wire ABA" name="InvoiceHeader_eftAbaWire" tabindex="57" size="20" maxlength="20" value="<%=invoiceHeader.getEftAbaWire()%>"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-eftFederalId")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "eftFederalId", "Federal ID", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-eftFederalId")%> ><input type=text title="Enter Federal ID" name="InvoiceHeader_federalId" tabindex="59" size="20" maxlength="20" value="<%=invoiceHeader.getFederalId()%>"></td>
				</tr>

				<% } %>
				</table>
			</td>
		</tr>
		</table>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
	<td rowspan="2" align="right" valign="top"><%@ include file="/invoice/iv_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var remitToEditOption = "<%=propertiesManager.getProperty("VOUCHER OPTIONS","REMITTOEDIT","ENABLE")%>";
	var createdFromPo = <%=bCreatedFromPo%>;

	function thisLoad()
	{
		if((frm.organizationId.value == "DTN07P") || (remitToEditOption == "DISABLE") || (remitToEditOption == "ENABLEFROMNOTHING" && createdFromPo) ) {
			frm.InvoiceAddress_addressLine1.disabled = true;
			frm.InvoiceAddress_addressLine2.disabled = true;
			frm.InvoiceAddress_addressLine3.disabled = true;
			frm.InvoiceAddress_addressLine4.disabled = true;
			frm.InvoiceAddress_city.disabled = true;
			frm.InvoiceAddress_state.disabled = true;
			frm.InvoiceAddress_postalCode.disabled = true;
			frm.InvoiceAddress_country.disabled = true;
		}

		f_StartIt();

<%	if (invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_RECALLED) > 0 ) { %>
	<%	if (invoiceHeader.getStatus().equals(DocumentStatus.IVC_REJECTED)  && user.getVchApp().equalsIgnoreCase("N") ) { %>
			checkInputSettings();
			allowEdit = false;
	<%	} %>
<%	} %>
	}
	
	<%	
	if (invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_APPROVING) >= 0 && !invoiceHeader.getStatus().equals(DocumentStatus.IVC_REJECTED)){
		%>
		checkInputSettings();
		allowEdit = false;
		<%
	}%>

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

	function setPcardNumber(field)
	{
		frm.InvoiceHeader_pcardNumber.value = field.value;
	}

	function setExpirationDate()
	{
		frm.InvoiceHeader_pcardExpires.value = frm.as_expMonth.value + frm.as_expYear.value;
	}

	function browseVendorAccount()
	{
		var vendorId = frm.InvoiceVendor_vendorId.value.trim();
		if (vendorId.length > 0)
		{
			popupParameters = "colname=VendorAccount_id_vendorId;operator==;filter_txt=" + vendorId + ";logicalOperator=AND;originalFilter=N;sort=N;"
			browseLookup('InvoiceHeader_vendorAccount', 'vendoraccount');
		}
		else
		{
			alert("You must first select a valid vendor!");
		}
	}

	function browseSupplier()
	{
		var formField = 'InvoiceVendor_vendorId';

		<%	if (oid.equalsIgnoreCase("dtn07p")) { %>
			popupParameters = popupParameters + "formField=" + formField +";browseName=consolidatedvendor;allowBrowse=" + frm.allowBrowse.value;
			doSubmitToPopup('/browse/supplier_filter_options.jsp', 'VendorRetrieveBrowseOptions', 'WIDTH=700px', 'HEIGHT=500px');
		<% } else { %>
			browseLookup(formField, 'consolidatedvendor');
		<% } %>
	}

	function browseContactAddress(frmField,vendorId)
	{
		<% if (oid.equalsIgnoreCase("msg07p")) { %>
		popupParameters = "colname=Address_id_addressType;operator==;filter_txt=" + vendorId + ";logicalOperator=AND;originalFilter=N;sort=N;"
		browseLookup(frmField,'contact-address-invoice');
		<% } else { %>
		popupParameters = "colname=Contact_id_vendorId;operator==;filter_txt=" + vendorId + ";logicalOperator=AND;originalFilter=N;sort=N;"
		browseLookup(frmField,'contact-address');
		<% } %>
	}

	function validateForm()
	{

		if (frm.handler.value.indexOf("Invoice") == 0)
		{
			if ( frm.organizationId.value.toUpperCase() == 'TTR09P' && isEmpty(frm.InvoiceHeader_termsCode.value) )
			{
					alert("You must enter a <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-paymentTerms", "Terms")%>.");
					frm.InvoiceHeader_apReference.focus();
					return false;
			}
		}



		return true;
	}
// End Hide script -->
</SCRIPT>