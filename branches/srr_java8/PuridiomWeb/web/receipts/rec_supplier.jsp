<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String allowSupplierChange =
		propertiesManager.getProperty("REC SELECTIONS","ALLOWSUPPLIERCHANGE","N");

	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	BigDecimal bd_ic_rec_header = receiptHeader.getIcRecHeader();
	String	s_rec_number = receiptHeader.getReceiptNumber();
	String	s_rec_type = receiptHeader.getReceiptType();
	String	s_rec_status = receiptHeader.getReceiptStatus();
	String	s_rec_fiscal_year = receiptHeader.getFiscalYear();
	if (HiltonUtility.isEmpty(s_rec_status)) {
		s_rec_status = DocumentStatus.RCV_INPROGRESS;
	}

	String	s_po_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_poNumber"));
	String	s_revision_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_revisionNumber"));
	String	s_release_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_releaseNumber"));
	if (HiltonUtility.isEmpty(s_revision_number)) {
		s_revision_number = "0";
	}
	if (HiltonUtility.isEmpty(s_release_number)) {
		s_release_number = "0";
	}
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);

	String	s_address_line_1 = "";
	String	s_address_line_2 = "";
	String	s_address_line_3 = "";
	String	s_address_line_4 = "";
	String	s_city = "";
	String	s_state = "";
	String	s_postal_code = "";
	String	s_country = "";
	Address vendor = (Address)request.getAttribute("vendorAddress");
	if (vendor != null) {
		s_address_line_1 = vendor.getAddressLine1();
		s_address_line_2 = vendor.getAddressLine2();
		s_address_line_3 = vendor.getAddressLine3();
		s_address_line_4 = vendor.getAddressLine4();
		s_city = vendor.getCity();
		s_state = vendor.getState();
		s_postal_code = vendor.getPostalCode();
		s_country = vendor.getCountry();
	}

	String	s_displayName = "";
	String	s_emailAddr = "";
	String	s_phoneNumber = "";
	String	s_mobileNumber = "";
	String	s_faxNumber = "";
	Contact contact = (Contact) request.getAttribute("contact");
	if(contact != null) {
		s_displayName = contact.getDisplayName();
		s_emailAddr = contact.getEmailAddr();
		s_phoneNumber = contact.getPhoneNumber();
		s_mobileNumber = contact.getMobileNumber();
		s_faxNumber = contact.getFaxNumber();
	}

	String	receiptMethod = HiltonUtility.ckNull((String)request.getAttribute("receiptMethod"));
	String	s_current_process = "HEADER_SUPPLIER";
	String	s_current_page = "/receipts/rec_supplier.jsp";
	String	s_current_method = "ReceiptHeaderUpdate";
	String	s_current_process_method = "";

	BigDecimal	bd_zero = new BigDecimal(0);

	boolean allowEdit = false;
	if ((receiptMethod.equals("FinalizeReceipt") && s_rec_status.compareTo(DocumentStatus.RCV_PENDINGFINALIZATION) == 0) ||
		(receiptMethod.equals("ReceiveFromNothing") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
		(receiptMethod.equals("ReceiveByOrder") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0))
		allowEdit = true;
%>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=s_rec_number%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="<%=s_rec_type%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=s_rec_status%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=s_rec_fiscal_year%>"/>
<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
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

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "supplier_information", "Supplier Information")%></div>
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
<%@ include file="/receipts/rec_info.jsp" %>
<br>

<table border="0" cellpadding="0" cellspacing="0" width=<%=formEntryWidth%>>
<tr>
  <td valign="top">
    <table border="0" cellpadding="0" cellspacing="0" width=100%>
    <tr>
      <td width="250px" align="center" valign="top">
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr <%=HtmlWriter.isVisible(oid, "rec-supplier")%>>
          <td align="right" nowrap>
          	<% if ("Y".equals(allowSupplierChange)) { %>
          		<a href="javascript: browseSupplier('ReceiptHeader_vendorId'); void(0);"
          			title="Click here to choose the <%=DictionaryManager.getLabel(oid, "rec-supplier", "Supplier")%> for this order or enter the <%=DictionaryManager.getLabel(oid, "rec-supplier", "Supplier")%> in the box on the right.">
          			<%=DictionaryManager.getLabel(oid, "rec-supplier", "Supplier", true)%></a>
          		:&nbsp;
          	<% } else { %>
          		<%=DictionaryManager.getLabel(oid, "rec-supplier", "Supplier", true)%>:&nbsp;
          	<% } %>
          </td>
          <td nowrap>
          	<input type="text" name="ReceiptHeader_vendorId" tabindex="2" size="15" maxlength="15"
          		value="<%=receiptHeader.getVendorId()%>"
          		onchange="upperCase(this); getNewInfo('vendor', this); updatePromiseDate();"
          		<% if ("N".equals(allowSupplierChange)) { %>disabled<% } %>>
          </td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine1")%>>
          <td align="right" nowrap><%=DictionaryManager.getLabel(oid, "rec-sup-addressLine1", "Address 1")%>:&nbsp;</td>
          <td width="50%">
            <input type="text" name="ReceiptHeader_vendorName" size="25" maxlength="40" value="<%=receiptHeader.getVendorName()%>" onfocus="this.blur()" disabled>
            <tsa:hidden name="Address_addressLine1" value="<%=s_address_line_1%>"/>
          </td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine2")%>>
          <td align="right" nowrap><%=DictionaryManager.getLabel(oid, "rec-sup-addressLine2", "Address 1")%>:&nbsp;</td>
          <td width="50%"><input type="text" name="Address_addressLine1" size="25" maxlength="40" value="<%=s_address_line_1%>" onfocus="this.blur()" disabled></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine3")%>>
          <td align="right" nowrap><%=DictionaryManager.getLabel(oid, "rec-sup-addressLine3", "Address 2")%>:&nbsp;</td>
          <td width="50%"><input type="text" name="Address_addressLine2" size="25" maxlength="40" value="<%=s_address_line_2%>" onfocus="this.blur()" disabled></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine4")%>>
          <td align="right" nowrap><%=DictionaryManager.getLabel(oid, "rec-sup-addressLine4", "Address 3")%>:&nbsp;</td>
          <td width="50%"><input type="text" name="Address_addressLine3" size="25" maxlength="40" value="<%=s_address_line_3%>" onfocus="this.blur()" disabled></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "rec-sup-city")%>>
          <td align="right" nowrap><%=DictionaryManager.getLabel(oid, "rec-sup-city", "City")%>:&nbsp;</td>
          <td width="50%"><input type="text" name="Address_city" size="20" maxlength="30" value="<%=s_city%>" onfocus="this.blur()" disabled></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "rec-sup-state")%>>
          <td align="right" nowrap><%=DictionaryManager.getLabel(oid, "rec-sup-state", "State")%>:&nbsp;</td>
          <td width="50%"><input type="text" name="Address_state" size="20" maxlength="15" value="<%=s_state%>" onfocus="this.blur()" disabled></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "rec-sup-zip")%>>
          <td align="right" nowrap><%=DictionaryManager.getLabel(oid, "rec-sup-zip", "Postal Code")%>:&nbsp;</td>
          <td width="50%"><input type="text" name="Address_postalCode" size="20" maxlength="15" value="<%=s_postal_code%>" onfocus="this.blur()" disabled></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "rec-sup-country")%>>
          <td align="right" nowrap><%=DictionaryManager.getLabel(oid, "rec-sup-country", "Country")%>:&nbsp;</td>
          <td width="50%"><input type="text" name="Address_country" size="20" maxlength="25" value="<%=s_country%>" onfocus="this.blur()" disabled></td>
        </tr>
        </table>
      </td>
      <td width="250px" align="center" valign="top">
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr><td colspan="2"><br><br><br><br></td></tr>
        <tr <%=HtmlWriter.isVisible(oid, "cnt-displayName")%>>
          <td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-displayName", "Contact Name", true)%>:&nbsp;</td>
          <td width="50%"><input type="text" name="Contact_displayName" size="25" maxlength="50" value="<%=s_displayName%>" onfocus="this.blur()" disabled></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "cnt-emailAddress")%>>
          <td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-emailAddress", "Email Address", true)%>:&nbsp;</td>
          <td width="50%"><input type="text" name="Contact_emailAddr" size="25" maxlength="50" value="<%=s_emailAddr%>" onfocus="this.blur()" disabled></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "cnt-telephoneNumber")%>>
          <td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-telephoneNumber", "Phone Number", true)%>:&nbsp;</td>
          <td width="50%"><input type="text" name="Contact_phoneNumber" size="20" maxlength="50" value="<%=s_phoneNumber%>" onfocus="this.blur()" disabled></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "cnt-mobileNumber")%>>
          <td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-mobileNumber", "Mobile Number", true)%>:&nbsp;</td>
          <td width="50%"><input type="text" name="Contact_mobileNumber" size="20" maxlength="50" value="<%=s_mobileNumber%>" onfocus="this.blur()" disabled></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "cnt-faxNumber")%>>
          <td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-faxNumber", "Fax Number", true)%>:&nbsp;</td>
          <td width="50%"><input type="text" name="Contact_faxNumber" size="20" maxlength="50" value="<%=s_faxNumber%>" onfocus="this.blur()" disabled></td>
        </tr>
		</table>
      </td>
<%	if (s_view.equalsIgnoreCase("WIZARD")) { %>
  <td rowspan="3" align="right" valign="top"><%@ include file="/receipts/rec_sidebar.jsp" %></td>
<%	} %>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td width="50%" align="center"><a href="javascript: doSubmit('/receipts/rec_summary.jsp', 'ReceiptHeaderUpdate;ReceiptRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border="0" alt="Save"></a></td>
  <td width="50%" align="center"><a href="javascript: doSubmit('/receipts/rec_summary.jsp', 'ReceiptRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border="0" alt="Return"></a></td>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var recNumber = "<%=s_rec_number%>";
	var fiscalyear = "<%=s_rec_fiscal_year%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	function thisLoad()
	{
		f_StartIt();
	<%	if (!allowEdit) { %>
		checkInputSettings();
	<%	} %>
	}

	function browseSupplier(formField)
	{
		popupParameters = popupParameters + "formField=" + formField +";browseName=vendor;allowBrowse=" + frm.allowBrowse.value;
		doSubmitToPopup('/browse/supplier_filter_options.jsp', 'VendorRetrieveBrowseOptions', 'WIDTH=700px', 'HEIGHT=500px');
	}

	function updatePromiseDate()
	{
		popupParameters = popupParameters + ";updatePromisedDate=Y";
	}

// End Hide script -->
</SCRIPT>
