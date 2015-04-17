<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_type = request.getParameter("as_type");
	String	s_next  = request.getParameter("as_next");
	String	s_pcard = "";

	if (s_type == null) {	s_type = "";	}
	if (s_next == null) {	s_next = "";	}

	Address address = (Address) request.getAttribute("address");
	Contact contact = (Contact) request.getAttribute("contact");
	Vendor vendor = (Vendor) request.getAttribute("vendor");
	String	s_address_line_1 = "";
	String	s_address_line_2 = "";
	String	s_address_line_3 = "";
	String	s_address_line_4 = "";
	String	s_city = "";
	String	s_state = "";
	String	s_postal_code = "";
	String	s_country = "";
	String	s_contact_code = "";
	String	s_contact_faxNumber = "";
	String	s_contact_emailAddress = "";
	String	originalContactCode = (String) request.getAttribute("Contact_contactCode");

	if (vendor == null) {
		vendor = new Vendor();
	}
	if (address == null) {
		address = new Address();
		AddressPK addressPk = new AddressPK();
		addressPk.setAddressCode("");
		addressPk.setAddressType(vendor.getVendorId());
		address.setComp_id(addressPk);
	}
	if (contact == null) {
		contact = new Contact();
		ContactPK contactPk = new ContactPK();
		contactPk.setContactCode("");
		contactPk.setVendorId(vendor.getVendorId());
		contact.setComp_id(contactPk);
	}

	if (address != null)
	{
		s_address_line_1 = address.getAddressLine1();
		s_address_line_2 = address.getAddressLine2();
		s_address_line_3 = address.getAddressLine3();
		s_address_line_4 = address.getAddressLine4();
		s_city = address.getCity();
		s_state = address.getState();
		s_postal_code = address.getPostalCode();
		s_country = address.getCountry();
	}
	if (contact != null)
	{
		s_contact_code = contact.getComp_id().getContactCode();
		s_contact_faxNumber = contact.getFaxNumber();
		s_contact_emailAddress = contact.getEmailAddr();
	}
/*
	if (retval == 1)
	{
		s_pcard = oO.getOutValue("pcard_code");
	}
*/
%>
<HTML>
<HEAD>
</HEAD>

<BODY>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	if (parent.frm.Address_addressLine1)
		parent.frm.Address_addressLine1.value = "<%=s_address_line_1%>";
	if (parent.frm.Address_addressLine2)
		parent.frm.Address_addressLine2.value = "<%=s_address_line_2%>";
	if (parent.frm.Address_addressLine3)
		parent.frm.Address_addressLine3.value = "<%=s_address_line_3%>";
	if (parent.frm.Address_addressLine4)
		parent.frm.Address_addressLine4.value = "<%=s_address_line_4%>";
	<%	if (oid.equalsIgnoreCase("vse06p")) { %>
	if (parent.frm.Address_addrFld10)
		parent.frm.Address_addrFld10.value = "<%=s_contact_faxNumber%>";
	if (parent.frm.Address_addrFld11)
		parent.frm.Address_addrFld11.value = "<%=s_contact_emailAddress%>";
	<%	} %>
	if (parent.frm.Address_city)
		parent.frm.Address_city.value = "<%=s_city%>";
	if (parent.frm.Address_state)
		parent.frm.Address_state.value = "<%=s_state%>";
	if (parent.frm.Address_postalCode)
		parent.frm.Address_postalCode.value = "<%=s_postal_code%>";
	if (parent.frm.Address_country)
		parent.frm.Address_country.value = "<%=s_country%>";

<%	if (s_next.length() > 0) { %>
		if (parent.frm.<%=s_next%>) {
			if ( parent.frm.<%=s_next%>.type != 'hidden') {
				parent.frm.<%=s_next%>.focus();
			}
		}
<%	} %>

	if (parent.frm.RequisitionHeader_vendContactCode) {
<%	if (HiltonUtility.isEmpty(s_contact_code) && !HiltonUtility.isEmpty(originalContactCode)) {%>
		parent.invalidContact();
<%	} else {%>
		parent.frm.RequisitionHeader_vendContactCode.value = "<%=s_contact_code%>";
<%	}%>
	}

	if (parent.frm.RequisitionHeader_vendorAttn) {
		parent.frm.RequisitionHeader_vendorAttn.value = "<%=contact.getDisplayName()%>";
	}
	if (parent.frm.RequisitionHeader_contactPhone) {
		parent.frm.RequisitionHeader_contactPhone.value = "<%=contact.getPhoneNumber()%>";
	}
	if (parent.frm.RequisitionHeader_contactMobilePhone) {
		parent.frm.RequisitionHeader_contactMobilePhone.value = "<%=contact.getMobileNumber()%>";
	}

	if (parent.frm.Contact_displayName) {
		parent.frm.Contact_displayName.value = "<%= contact.getDisplayName() %>";
	}

	if (parent.frm.Contact_faxNumber) {
		parent.frm.Contact_faxNumber.value = "<%= contact.getFaxNumber() %>";
	}

	if (parent.frm.Contact_emailAddr) {
		parent.frm.Contact_emailAddr.value = "<%= contact.getEmailAddr() %>";
	}

	if (parent.frm.Contact_phoneNumber) {
		parent.frm.Contact_phoneNumber.value = "<%= contact.getPhoneNumber() %>";
	}

	if (parent.frm.Contact_mobileNumber) {
		parent.frm.Contact_mobileNumber.value = "<%= contact.getMobileNumber() %>";
	}

	if (parent.frm.Contact_info1) {
		parent.frm.Contact_info1.value = "<%= contact.getInfo1() %>";
	}

	if (parent.frm.Contact_info2) {
		parent.frm.Contact_info2.value = "<%= contact.getInfo2() %>";
	}
	
	if (parent.frm.PoHeader_vendContactCode) {
<%	if (HiltonUtility.isEmpty(s_contact_code) && !HiltonUtility.isEmpty(originalContactCode)) {%>
		parent.invalidContact();
<%	} else {%>
		parent.frm.PoHeader_vendContactCode.value = "<%=s_contact_code%>";
<%	}%>
	}
	if (parent.frm.PoHeader_contactName) {
		parent.frm.PoHeader_contactName.value = "<%=contact.getDisplayName()%>";
	}
	if (parent.frm.PoHeader_contactPhone) {
		parent.frm.PoHeader_contactPhone.value = "<%=contact.getPhoneNumber()%>";
	}
	if (parent.frm.PoHeader_contactMobilePhone) {
		parent.frm.PoHeader_contactMobilePhone.value = "<%=contact.getMobileNumber()%>";
	}

	if (parent.frm.PoHeader_email) {
		parent.frm.PoHeader_email.value = "<%= contact.getEmailAddr() %>";
	}

<% if (vendor != null) {%>

	<% if(oid.equalsIgnoreCase("BLY07P")) { %>
		if (parent.frm.RequisitionHeader_shippingCode ) {
				parent.frm.RequisitionHeader_shippingCode.value = "<%=vendor.getShipVia()%>";
		}
		if (parent.frm.RequisitionHeader_currencyCode) {
				parent.frm.RequisitionHeader_currencyCode.value = "<%=vendor.getCurrencyCode()%>";
		}
		if (parent.frm.PoHeader_currencyCode) {
				parent.frm.PoHeader_currencyCode.value = "<%=vendor.getCurrencyCode()%>";
		}
	<%} %>

		if (parent.frm.Vendor_vendTerms) {
				parent.frm.Vendor_vendTerms.value = "<%=vendor.getVendTerms()%>";
		}

	<%	String s_print_fax_code = "P";
		if (!HiltonUtility.isEmpty(vendor.getPrintFaxCode()))
			s_print_fax_code = vendor.getPrintFaxCode(); %>
	if (parent.frm.PoHeader_ediOrder_option) {
		parent.frm.PoHeader_ediOrder_option.value = "<%=s_print_fax_code%>";
	}
	if (parent.frm.PoHeader_ediOrder) {
		parent.frm.PoHeader_ediOrder.value = "<%=s_print_fax_code%>";
	}
	if (parent.frm.PoHeader_faxNumber && parent.document.getElementById("showFaxNumber"))
	{
		parent.frm.PoHeader_faxNumber.value = "<%=vendor.getFaxNumber()%>";
		if (parent.frm.PoHeader_ediOrder.value == "F")
		{
			parent.document.getElementById("showFaxNumber").style.visibility = "visible";
			parent.document.getElementById("showFaxNumber").style.display    = "block";
			parent.document.getElementById("showFaxNumber").style.height     = 22;
		}
		else
		{
			parent.document.getElementById("showFaxNumber").style.visibility = "hidden";
			parent.document.getElementById("showFaxNumber").style.display    = "none";
			parent.document.getElementById("showFaxNumber").style.height     = 0;
		}
	}
	if (parent.frm.PoHeader_shipViaCode ) {
		<% if(oid.equalsIgnoreCase("BLY07P")) { %>
			parent.frm.PoHeader_shipViaCode.value = "<%=vendor.getShipVia()%>";
		<%} else {%>
			if (parent.frm.PoHeader_shipViaCode.value == "") {
				parent.frm.PoHeader_shipViaCode.value = "<%=vendor.getShipVia()%>";
			}
		<%} %>
		}
	if (parent.frm.PoHeader_fobCode) {
		<% if(oid.equalsIgnoreCase("BLY07P")) { %>
			parent.frm.PoHeader_fobCode.value = "<%=vendor.getFobId()%>";
		<%} else {%>
		if (parent.frm.PoHeader_fobCode.value == "") {
			parent.frm.PoHeader_fobCode.value = "<%=vendor.getFobId()%>";
		}
		<%} %>
	}
	if (parent.frm.PoHeader_termsCode) {
		<% if(oid.equalsIgnoreCase("BLY07P")) { %>
			parent.frm.PoHeader_termsCode.value = "<%=vendor.getVendTerms()%>";
		<%} else {%>
		if (parent.frm.PoHeader_termsCode.value == "") {
			parent.frm.PoHeader_termsCode.value = "<%=vendor.getVendTerms()%>";
		}
		<%} %>
	}
	if (parent.frm.PoHeader_vendorClass) {
		parent.frm.PoHeader_vendorClass.value = "<%=vendor.getVendorClass()%>";
	}
	if (parent.frm.VendorInsurance_vendorId) {
		parent.frm.VendorInsurance_vendorId.value = "<%=vendor.getVendorId()%>";
	}
	if (parent.frm.PoHeader_inspectionReqd) {
		parent.frm.PoHeader_inspectionReqd.value = "<%=vendor.getInspectionReqd()%>";
	}
	if (parent.frm.RequisitionLine_vendorName) {
		parent.frm.RequisitionLine_vendorName.value = "<%=vendor.getVendorName()%>";
	}
	if (parent.frm.RequisitionLine_vendContactCode) {
		parent.frm.RequisitionLine_vendContactCode.value = "<%=s_contact_code%>";
	}
	if (parent.frm.ReceiptHeader_vendorName) {
		parent.frm.ReceiptHeader_vendorName.value = "<%=vendor.getVendorName()%>";
	}
	<% if(oid.equalsIgnoreCase("tdc10p")) { %>
		if (parent.frm.RequisitionHeader_billToCode) {
			if("<%=vendor.getVendUdf3()%>" == ""){
				parent.frm.RequisitionHeader_billToCode.value = "<%=propertiesManager.getProperty("REQ DEFAULTS", "BillToCode", "")%>";
			}else{
				parent.frm.RequisitionHeader_billToCode.value = "<%=vendor.getVendUdf3()%>";	
			}					
		}				
	
		if (parent.frm.PoHeader_billToCode) {					
			if("<%=vendor.getVendUdf3()%>" == ""){
				parent.frm.PoHeader_billToCode.value = "<%=propertiesManager.getProperty("PO DEFAULTS", "BillToCode", "")%>";
			}else{
				parent.frm.PoHeader_billToCode.value = "<%=vendor.getVendUdf3()%>";	
			}					
		}				
	<% } %>
<%	}%>

	parent.hideArea("getInfoFrame");

// end hiding contents -->
</SCRIPT>
</BODY>
</HTML>

<%@ include file="/system/prevent_caching.jsp" %>