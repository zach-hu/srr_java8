<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.entity.RequisitionHeader" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<%
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
   	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_onetime_supplier = propertiesManager.getProperty("REQ OPTIONS", "ALLOW ONETIME SUPPLIER", "N");
	String s_edit_supplier_releaserequest = propertiesManager.getProperty("REQ OPTIONS", "ALLOW EDIT SUPPLIER RELEASE REQUEST", "Y");
	BigDecimal bd_ic_req_header = requisitionHeader.getIcReqHeader();
	String	s_req_number = requisitionHeader.getRequisitionNumber();
	if (HiltonUtility.isEmpty(s_req_number)){s_req_number = "N/A";}
	String	s_req_status = requisitionHeader.getStatus();
	String	s_req_type = requisitionHeader.getRequisitionType();
	String	s_req_subtype = requisitionHeader.getRqSubType();
	String	s_fiscal_year = requisitionHeader.getFiscalYear();
	String	s_vendor_id = requisitionHeader.getVendorId();
	String	s_vend_contact_code = requisitionHeader.getVendContactCode();
	String	s_vendor_attn = requisitionHeader.getVendorAttn();
	String	s_shipping_code = requisitionHeader.getShippingCode();

	String	s_address_line_1 = "";
	String	s_address_line_2 = "";
	String	s_address_line_3 = "";
	String	s_address_line_4 = "";
	String	s_city = "";
	String	s_state = "";
	String	s_postal_code = "";
	String	s_country = "";
	String  s_address_addrFld10 = "";
	String  s_address_addrFld11 = "";
	String  s_address_addrFld12 = "";
	String  s_address_addrFld13 = "";

	Address vendor = (Address) requisitionHeader.getVendorAddress();
	if (vendor != null)
	{
		s_address_line_1 = vendor.getAddressLine1();
		s_address_line_2 = vendor.getAddressLine2();
		s_address_line_3 = vendor.getAddressLine3();
		s_address_line_4 = vendor.getAddressLine4();
		s_address_addrFld10 = vendor.getAddrFld10();
		s_address_addrFld11 = vendor.getAddrFld11();
		s_address_addrFld12 = vendor.getAddrFld12();
		s_address_addrFld13 = vendor.getAddrFld13();

		s_city = vendor.getCity();
		s_state = vendor.getState();
		s_postal_code = vendor.getPostalCode();
		s_country = vendor.getCountry();
	}

	String	s_current_process = "HEADER_SUPPLIER";
	String	s_current_page = "/requests/req_supplier.jsp";
	String	s_current_method = "RequisitionHeaderUpdate";
	String	s_current_process_method = "";

	String	s_title = "Supplier Information";
	if (s_req_type.equals("K"))
	{
		s_title = "Remit To Address";
	}

	Contact contact = (Contact) request.getAttribute("contact");
	if (contact == null) {
		contact = new Contact();
	}
	ContactPK contactPK = (ContactPK) contact.getComp_id();
	if (contactPK == null) {
		contactPK = new ContactPK();
		contactPK.setContactCode("001");
		contactPK.setContactType("DEFAULT");
		contactPK.setVendorId(s_vendor_id);
		contact.setComp_id(contactPK);
	}
	boolean requiredSupplier = true;

	if (oid.equalsIgnoreCase("msg07p") && s_req_type.equals("N")) {
		requiredSupplier = false;
	}

	boolean requiredFields = false;
	if (oid.equalsIgnoreCase("hoy08p")) {
		requiredFields = true;
	}

	Iterator typeIterator = null;
	Map securityTypes = null;
	boolean isCurrentApprover = false;
	if (requisitionHeader != null)
	{
		isCurrentApprover = requisitionHeader.getAppBy().equalsIgnoreCase(uid) && requisitionHeader.getStatus().compareTo(DocumentStatus.REQ_APPROVING) == 0;
		if (isCurrentApprover)
		{
			securityTypes = propertiesManager.getSection("$ADMIN APP REQ");
			if (securityTypes != null) {
				typeIterator = securityTypes.keySet().iterator();
			}
		}
	}
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=requisitionHeader.getItemLocation()%>"/>
<tsa:hidden name="RequisitionHeader_currencyCode" value="<%=requisitionHeader.getCurrencyCode()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RQH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="Address_addressType" value="VENDOR"/>
<tsa:hidden name="Address_addressCode" value="<%=s_req_number%>"/>
<tsa:hidden name="Address_status" value=""/>
<tsa:hidden name="updateAddress" value="N"/>
<tsa:hidden name="RequisitionHeader_billToCode" value="<%=requisitionHeader.getBillToCode()%>"/>
<tsa:hidden name="editFieldsApprover" value=""/>

<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
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
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="requisitionNumber" defaultString="Requisition #"></tsa:label>:</b></tsa:td>
			<tsa:td width="125px"><%=headerEncoder.encodeForHTML(s_req_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="status" defaultString="Status"></tsa:label>:</b></tsa:td>
			<tsa:td width="125px"><%=DocumentStatus.toString(s_req_status, oid)%></tsa:td>
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
<%@ include file="/requests/req_info.jsp" %>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
<tsa:tr>
	<tsa:td valign="top" align="center">
		<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0" align="center">
		<tsa:tr field="req-supplier" docType="s_req_type">
			<tsa:td align="RIGHT">
				<A HREF="javascript: browseVendorAddress(); void(0);" title='<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-supplier-instructions", "Click here to choose the Supplier for this requisition or enter the Supplier in the box on the right.", s_req_type)%>'>
<%	if (s_req_type.equals("K")) {%>
				<tsa:label labelName="remitTo" defaultString="Remit To" docType="s_req_type"></tsa:label></A>:&nbsp;
<%	} else { %>
				<tsa:label labelName="req-supplier" defaultString="Supplier" docType="s_req_type"></tsa:label></A>:&nbsp;
<%	} %>
			</tsa:td>
			<tsa:td noWrap="nowrap" valign="BOTTOM">
				<tsa:input type="midtext" title="Enter the suggested supplier that you would like to fulfill this requisition" name="RequisitionHeader_vendorId" tabIndex="1" maxLength="15" value="<%=s_vendor_id%>" onchange="upperCase(this); getNewInfo('vendor', this); verifySoleSource();" />
<%	if (s_onetime_supplier.equalsIgnoreCase("Y")) {
		if(s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0){%>
			<a href="javascript: enterAddress(); void(0);"><img src="<%=contextPath%>/images/add.gif" alt="Click here to add a non-standard supplier" border="0"></a>
		<%	}
	} %>
			</tsa:td>
			<tsa:td align="right" noWrap="nowrap">
				<div id="editJustification" style="visibility: hidden">
				<table border="0">
				<tsa:tr>
					<tsa:td><a href="javascript: solesource(); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border="0"></a></tsa:td>
					<tsa:td><a href="javascript: solesource(); void(0);"><tsa:label labelName="editJustification" defaultString="Edit Justification"></tsa:label></a></tsa:td>
				</tsa:tr>
				</table>
				</div>
			</tsa:td>
		</tsa:tr>
		<tsa:tr field="req-sup-addressLine1">
			<tsa:td align="RIGHT"><tsa:label labelName="req-sup-addressLine1" defaultString="Address 1" checkRequired="<%=\"\"+requiredFields %>"></tsa:label>:&nbsp;</tsa:td>
			<tsa:td><tsa:input type="text" name="Address_addressLine1" size="35" maxLength="40" value="<%=s_address_line_1%>" readonly="readonly" disabled="disabled" /></tsa:td>
		</tsa:tr>
		<tsa:tr field="req-sup-addressLine2">
			<tsa:td align="RIGHT"><tsa:label labelName="req-sup-addressLine2" defaultString="Address 2" checkRequired="<%=\"\"+requiredFields %>"></tsa:label>:&nbsp;</tsa:td>
			<tsa:td><tsa:input type="text" name="Address_addressLine2" size="35" maxLength="40" value="<%=s_address_line_2%>" readonly="readonly" disabled="disabled" /></tsa:td>
		</tsa:tr>

		<tsa:tr field="req-sup-addressLine3">
			<tsa:td align="RIGHT"><tsa:label labelName="req-sup-addressLine3" defaultString="Address 3"></tsa:label>:&nbsp;</tsa:td>
			<tsa:td><tsa:input type="text" name="Address_addressLine3" size="35" maxLength="40" value="<%=s_address_line_3%>" readonly="readonly" disabled="disabled" /></tsa:td>
		</tsa:tr>

		<tsa:tr field="req-sup-addressLine4">
			<tsa:td align="RIGHT"><tsa:label labelName="req-sup-addressLine4" defaultString="Address 4"></tsa:label>:&nbsp;</tsa:td>
			<tsa:td><tsa:input type="text" name="Address_addressLine4" size="35" maxLength="40" value="<%=s_address_line_4%>" readonly="readonly" disabled="disabled" /></tsa:td>
		</tsa:tr>

		<tsa:tr field="req-sup-city">
			<tsa:td align="RIGHT"><tsa:label labelName="req-sup-city" defaultString="City" checkRequired="<%=\"\"+requiredFields %>"></tsa:label>:&nbsp;</tsa:td>
			<tsa:td><tsa:input type="midtext" name="Address_city" maxLength="30" value="<%=s_city%>" readonly="readonly" disabled="disabled" /></tsa:td>
		</tsa:tr>

		<tsa:tr field="req-sup-state">
			<tsa:td align="RIGHT"><tsa:label labelName="req-sup-state" defaultString="State" checkRequired="<%=\"\"+requiredFields %>"></tsa:label>:&nbsp;</tsa:td>
			<tsa:td><tsa:input type="midtext" name="Address_state" maxLength="15" value="<%=s_state%>" readonly="readonly" disabled="disabled" /></tsa:td>
		</tsa:tr>

		<tsa:tr field="req-sup-zip">
			<tsa:td align="RIGHT"><tsa:label labelName="req-sup-zip" defaultString="Zip/Postal Code" checkRequired="<%=\"\"+requiredFields %>"></tsa:label>:&nbsp;</tsa:td>
			<tsa:td><tsa:input type="midtext" name="Address_postalCode"  maxLength="15" value="<%=s_postal_code%>" readonly="readonly" disabled="disabled" /></tsa:td>
		</tsa:tr>

		<tsa:tr field="req-sup-country">
			<tsa:td align="RIGHT"><tsa:label labelName="req-sup-country" defaultString="Country"></tsa:label>:&nbsp;</tsa:td>
			<tsa:td><tsa:input type="midtext" name="Address_country" maxLength="25" value="<%=s_country%>" readonly="readonly" disabled="disabled" /></tsa:td>
		</tsa:tr>

		<tsa:tr field="req-sup-attention">
			<tsa:td align="RIGHT"><A HREF="javascript: browseContactAddress('RequisitionHeader_vendContactCode',frm.RequisitionHeader_vendorId.value); void(0);" title="Click here to choose the Primary Supplier Contact for this requisition or enter a Primary Supplier Contact in the box on the right."><tsa:label labelName="req-sup-attention" defaultString="Supplier Contact" checkRequired="<%=\"\"+requiredSupplier %>"></tsa:label></A>:&nbsp;</tsa:td>
			<tsa:td>
				<tsa:input type="midtext" title="Enter the Primary Supplier Contact for this requisition" name="RequisitionHeader_vendContactCode" tabIndex="2" maxLength="15" value="<%=s_vend_contact_code%>" onchange="getNewInfo('contact', this);" />
				<tsa:hidden name="RequisitionHeader_contactAddr" value="<%=requisitionHeader.getContactAddr()%>"/>
			</tsa:td>
		</tsa:tr>
		<% if(oid.equalsIgnoreCase("QRI06P")){ %>
			<tsa:tr field="req-sup-contact">
		         <tsa:td align="right"><tsa:label labelName="req-sup-contactName" defaultString="Contact Email"></tsa:label>:&nbsp;</tsa:td>
		         <tsa:td><tsa:input type="midtext" name="RequisitionHeader_udf4Code" maxLength="40" value="<%=requisitionHeader.getUdf4Code()%>" /></tsa:td>
		    </tsa:tr>
	    <%} %>
		<tsa:tr>
<% if(oid.equalsIgnoreCase("MSG07P")||oid.equalsIgnoreCase("QRI06P")||oid.equalsIgnoreCase("WPC08P")){ %>
			<tsa:td align="RIGHT"><tsa:label labelName="req-sup-contact-name" defaultString="req-sup-contact-name"></tsa:label>:&nbsp;</tsa:td>
<%}else { %>
			<tsa:td field="req-sup-contact-name" align="RIGHT"><tsa:label labelName="req-sup-contact-name" defaultString="Contact Name" checkRequired="true" noinstance="yes"></tsa:label>:&nbsp;</tsa:td>
<% } %>
		<tsa:td field="req-sup-contact-name"><tsa:input type="midtext" name="RequisitionHeader_vendorAttn" maxLength="30" value="<%=s_vendor_attn%>" readonly="readonly" disabled="disabled" /></tsa:td>
		</tsa:tr>
<% if(!oid.equalsIgnoreCase("WPC08P")){ %>
		<tsa:tr field="req-sup-phone">
			<tsa:td align="RIGHT"><tsa:label labelName="req-sup-phone" defaultString="Phone" checkRequired="<%=\"\"+requiredFields %>"></tsa:label>:&nbsp;</tsa:td>
			<tsa:td><tsa:input type="midtext" name="RequisitionHeader_contactPhone" maxLength="25" value="<%=requisitionHeader.getContactPhone()%>" readonly="readonly" disabled="disabled" /></tsa:td>
		</tsa:tr>
		<tsa:tr field="req-sup-mobilePhone">
			<tsa:td align="RIGHT"><tsa:label labelName="req-sup-mobilePhone" defaultString="Mobile Phone"></tsa:label>:&nbsp;</tsa:td>
			<tsa:td><tsa:input type="midtext" name="RequisitionHeader_contactMobilePhone" maxLength="25" value="<%=requisitionHeader.getContactMobilePhone()%>" readonly="readonly" disabled="disabled" /></tsa:td>
		</tsa:tr>
		<%} %>
		<%
		Vendor vendorR = null;
 		vendorR = (Vendor) request.getAttribute("vendor");
		%>
		<% if( ( oid.equalsIgnoreCase("HOY08P") &&  !s_req_type.equalsIgnoreCase("K") ) ||  !oid.equalsIgnoreCase("HOY08P") ){%>
		<tsa:tr field="req-sup-paymentTerms">
			<tsa:td align="right"><tsa:label labelName="req-sup-paymentTerms" defaultString="Terms"></tsa:label>:&nbsp;</tsa:td>
			<%if(vendorR != null) { %>
				<tsa:td> <tsa:input type="midtext" name="Vendor_vendTerms" maxLength="20" value="<%=vendorR.getVendTerms()%>" readonly="readonly" disabled="disabled" /> </tsa:td>
			<%} else { %>
				<tsa:td> <tsa:input type="midtext" name="Vendor_vendTerms" maxLength="20" value="" readonly="readonly" disabled="disabled" /> </tsa:td>
			<%} %>
			<% if(oid.equalsIgnoreCase("BLY07P")) { %>
			<tsa:td><tsa:hidden name="RequisitionHeader_shippingCode" value="<%=s_shipping_code %>"/></tsa:td>
			<%} %>
		</tsa:tr>
		<%} %>
		<tsa:tr field="req-sup-cnt-faxNumber">
			<tsa:td align="right"><tsa:label labelName="req-sup-cnt-faxNumber" defaultString="Fax Number" checkRequired="true"></tsa:label>:</tsa:td>
			<tsa:td  colspan="3"><tsa:input type="text" name="Address_addrFld10" tabIndex="7" size="30" maxLength="30" value="<%=contact.getFaxNumber()%>" disabled="<%=HtmlWriter.isReadOnly(oid, \"req-sup-cnt-faxNumber\") %>" /></tsa:td>
         </tsa:tr>
		<tsa:tr field="req-sup-cnt-emailAddress">
			<tsa:td align="right"><tsa:label labelName="req-sup-cnt-emailAddress" defaultString="Email Address" checkRequired="true"></tsa:label>:</tsa:td>
			<tsa:td  colspan="3"><tsa:input type="text" name="Address_addrFld11" tabIndex="8" size="50" maxLength="50" value="<%=contact.getEmailAddr()%>" disabled="<%=HtmlWriter.isReadOnly(oid, \"req-sup-cnt-emailAddress\") %>"  /></tsa:td>
		</tsa:tr>
		<tsa:tr field="req-sup-cnt-info1">
			<tsa:td align="right"><tsa:label labelName="req-sup-cnt-info1" defaultString="Info 1" checkRequired="true"></tsa:label>:</tsa:td>
			<tsa:td  colspan="3"><tsa:input type="text" name="Address_addrFld12" tabIndex="9" size="50" maxLength="50" value="<%=s_address_addrFld12%>" readonly="readonly" disabled="disabled" /></tsa:td>
		</tsa:tr>
		<tsa:tr field="req-sup-cnt-info2">
			<tsa:td align="right"><tsa:label labelName="req-sup-cnt-info2" defaultString="Info 2" checkRequired="true"></tsa:label>:</tsa:td>
			<tsa:td  colspan="3"><tsa:input type="text" name="Address_addrFld13" tabIndex="10" size="50" maxLength="50" value="<%=s_address_addrFld13%>" readonly="readonly" disabled="disabled" /></tsa:td>
		</tsa:tr>
		<tsa:tr><tsa:td colspan="2"><BR></tsa:td></tsa:tr>
		</TABLE>
	</tsa:td>
<%	if (s_view.equalsIgnoreCase("WIZARD")) { %>
	<tsa:td rowspan="3" align="right" valign="top"><%@ include file="/requests/req_sidebar.jsp" %></tsa:td>
<%	} %>
</tsa:tr>
</table>

<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
	<tsa:td width="50%" align="center"><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionHeaderUpdate;RequisitionRetrieve'); void(0);"><img class="button" src="<%=contextPath%>/images/button_save.gif" border="0" alt="Save"></a></tsa:td>
	<tsa:td width="50%" align="center"><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionRetrieve'); void(0);"><img class="button" src="<%=contextPath%>/images/button_return.gif" border="0" alt="Return"></a></tsa:td>
</tsa:tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<script value="JavaScript">
<!-- Hide script

	frm = document.purchaseform;
	var reqnumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
	var fiscalyear = "<%=requisitionHeader.getFiscalYear()%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	function thisLoad()
	{
		f_StartIt();
<%		if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0) { %>
			checkInputSettings();
<%	}
		if (s_req_type.equals("O") && (requisitionHeader.getTotal().compareTo(new BigDecimal(25000)) > 0) && !HiltonUtility.isEmpty(requisitionHeader.getVendorId()) && !requisitionHeader.getVendorId().equals("*TBD*")) {%>
			displayArea("editJustification");
<%	}
		if(s_edit_supplier_releaserequest.equalsIgnoreCase("N") && s_req_type.equalsIgnoreCase("E")){ %>

		  checkInputSettings();

		  frm.allowBrowse.value = "false";
	  <%}
%>

<%		if (typeIterator != null && securityTypes != null) { %>
		allowEditApprover();
<%		} 
		if(s_req_type.equals("P") && s_req_status.compareTo(DocumentStatus.REQ_REJECTED) == 0 && !user.isAFpe()){%>
			checkInputSettings();
<%		} %>
	}

	function invalidContact() {
		alert("The contact code entered currently does not exist for this supplier.  Please click on the link to select a valid contact code from the list.");
		frm.RequisitionHeader_vendContactCode.value = "";
	}

	function solesource()
	{
		popupParameters = "DocComment_icHeader=" + frm.DocComment_icHeader.value + ";DocComment_icLine=0;DocComment_commentId=SOLESOURCE";
		doSubmitToPopup('/requests/sole_source.jsp', 'DocCommentRetrieveByIcAndCommentId', 'WIDTH=500px', 'HEIGHT=350px');
		displayArea("editJustification");
	}

	function verifySoleSource()
	{
<%	if (s_req_type.equalsIgnoreCase("O") && requisitionHeader.getTotal().compareTo(new BigDecimal(25000)) > 0) {	%>
			var vendorId = trim(frm.RequisitionHeader_vendorId);
			if (vendorId.length > 0 && vendorId != "*TBD*")
			{
				setTimeout("solesource();", 1500);
			}
			if (vendorId == "*TBD*" || vendorId == "*SEE COMMENTS*")
			{
				setTimeout("frm.RequisitionHeader_vendContactCode.value = '';", 800);
			}
<%	}	%>
	}

	function enterAddress()
	{
		if (reqnumber == "N/A")
		{
			alert("You must save your requisition before entering a new address!");
			return false;
		}
		else
		{
			frm.updateAddress.value = "TRUE";

			frm.Address_addressLine1.value = "";
			frm.Address_addressLine2.value = "";
			frm.Address_addressLine3.value = "";
			frm.Address_addressLine4.value = "";
			frm.Address_city.value = "";
			frm.Address_state.value = "";
			frm.Address_postalCode.value = "";
			frm.Address_country.value = "";
			frm.Address_status.value = '02';

			frm.RequisitionHeader_vendorAttn.value = "";
			frm.RequisitionHeader_contactPhone.value = "";
			frm.RequisitionHeader_contactMobilePhone.value = "";

			frm.RequisitionHeader_vendorId.value = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
			frm.RequisitionHeader_contactAddr.value = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
			frm.RequisitionHeader_vendContactCode.value = "001";

			if(frm.RequisitionHeader_udf4Code!=undefined){
				frm.RequisitionHeader_udf4Code.value = "<%=requisitionHeader.getUdf4Code()%>";
				allowInputEdit(frm.RequisitionHeader_udf4Code, true);
			}

			allowInputEdit(frm.RequisitionHeader_vendorId, false);
			allowInputEdit(frm.RequisitionHeader_vendContactCode, false);

			allowInputEdit(frm.Address_addressLine1, true);
			allowInputEdit(frm.Address_addressLine2, true);
			allowInputEdit(frm.Address_addressLine3, true);
			allowInputEdit(frm.Address_addressLine4, true);
			allowInputEdit(frm.Address_city, true);
			allowInputEdit(frm.Address_state, true);
			allowInputEdit(frm.Address_postalCode, true);
			allowInputEdit(frm.Address_country, true);

			allowInputEdit(frm.RequisitionHeader_vendorAttn, true);
			allowInputEdit(frm.RequisitionHeader_contactPhone, true);
			allowInputEdit(frm.RequisitionHeader_contactMobilePhone, true);

			<% if( oid.equalsIgnoreCase("P4TEST") &&  s_req_type.equalsIgnoreCase("K") ){%>
			allowInputEdit(frm.Vendor_vendTerms, true);
			allowInputEdit(frm.Address_addrFld10, true);
			allowInputEdit(frm.Address_addrFld11, true);
			allowInputEdit(frm.Address_addrFld12, true);
			allowInputEdit(frm.Address_addrFld13, true);
			<% }%>
		}
	}

	function browseVendorAddress()
	{
		frm.updateAddress.value = "N";
		allowInputEdit(frm.RequisitionHeader_vendorId, false);

	<%	if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0) { %>
		allowInputEdit(frm.RequisitionHeader_vendorId, true);
	<%	} %>

		var formField = 'RequisitionHeader_vendorId';

		if (frm.organizationId.value == 'DTN07P' || frm.organizationId.value == 'SRR10P') {
			popupParameters = popupParameters + "formField=" + formField +";browseName=vendor;allowBrowse=" + frm.allowBrowse.value;
			doSubmitToPopup('/browse/supplier_filter_options.jsp', 'VendorRetrieveBrowseOptions', 'WIDTH=700px', 'HEIGHT=500px');
		} else {
			browseLookup(formField, 'vendor');
		}
	}

	function setAuditTables()
	{
		frm.auditTables.value = "RequisitionHeader";
	}
	function getFields(el)
	{
		if(el.type != "hidden" && el.name.indexOf("RequisitionHeader_") > -1)
		{
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
    }
	function getFieldsJquery()
	{
		var jQuerySelector = ":input:not([type=hidden])[name^='RequisitionHeader_']";
		var fieldsToAuditJquery = $(jQuerySelector);
		return fieldsToAuditJquery;
    }
    function buildAuditIc()
	{
		return frm.RequisitionHeader_icReqHeader.value;
	}

	function allowEditApprover()
	{
		<%	if (typeIterator != null && securityTypes != null)
		{
			String accessType = "";
			while (typeIterator.hasNext())
			{
				accessType = (String) typeIterator.next();
				if (accessType.toUpperCase().startsWith("RH-") && role.getAccessRights(accessType.toUpperCase()) > 0)
				{
					if (accessType.length() > 4)
					{
						accessType = accessType.substring(3).toLowerCase();
						String[] accessTypeArray = accessType.split("_");
						String accessTypeField = "";
						for (int ind = 0; ind < accessTypeArray.length; ind++)
						{
							if (accessTypeArray[ind].length() > 0)
							{
								if (ind == 0) {
									accessTypeField = accessTypeArray[ind];
								} else {
									accessTypeField = accessTypeField + accessTypeArray[ind].substring(0,1).toUpperCase() + accessTypeArray[ind].substring(1);
								}
							}
						} %>
						if (frm.RequisitionHeader_<%=accessTypeField%>) {
							allowInputEdit(frm.RequisitionHeader_<%=accessTypeField%>, true);
							frm.editFieldsApprover.value = frm.editFieldsApprover.value + "RequisitionHeader_<%=accessTypeField%>";
						}
				<%	}
				}
			}
		} %>
	}

// End Hide script -->
</script>