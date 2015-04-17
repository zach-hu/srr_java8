<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<%
	Vendor vendor = (Vendor) request.getAttribute("vendor");

	String vendorName = (String) request.getAttribute("Vendor_vendorName");
	String vendor_buyer = HiltonUtility.ckNull((String) request.getAttribute("Vendor_buyer"));
	List list = (List)request.getAttribute("contactAddressList");
	HashMap addresses = new HashMap();
	HashMap contacts = new HashMap();
	List contactList = new ArrayList();
	boolean defaultContactSet = false;
	boolean defaultAddressSet = false;
	boolean remitToAddressSet = false;

	for (int i=0; i < list.size(); i++)
	{
		Object objects[] = (Object[]) list.get(i);
		Contact contact = (Contact) objects[0];
		Address address = (Address) objects[1];
		ContactPK contactPK = (ContactPK) contact.getComp_id();
		AddressPK addressPK = (AddressPK) address.getComp_id();
		String	contactCode = contactPK.getContactCode();
		String	contactType = contactPK.getContactType();
		String	addressCode = addressPK.getAddressCode();

		if (contactType.equals("DEFAULT")) {
			defaultContactSet = true;

			//set default Contact in contacts map w/ DEFAULT as the key
			contactCode = contactType;
		}
		if (addressCode.equals("DEFAULT")) {
			defaultAddressSet = true;
		}
		if (addressCode.equals("REMITTO")) {
			remitToAddressSet = true;
		}

		contactList.add(contactCode);
		contacts.put(contactCode, contact);
		addresses.put(addressCode, address);
	}

	if (!remitToAddressSet) {
		Address remitToAddress = (Address) request.getAttribute("remitToAddress");

		if (remitToAddress != null) {
			if ( !HiltonUtility.isEmpty(remitToAddress.getAddressLine1()))
			{
				remitToAddressSet = true;
				addresses.put("REMITTO", remitToAddress);
			}
		}
	}

	String	s_current_process = "CONTACTS_ADDRESSES";
	String	s_current_page = "/admin/supplier/supplier_contact_list.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";

	String defaultRfqOnly = PropertiesManager.getInstance(oid).getProperty("VENDOR", "DEFAULTRFQONLY", "N");
	String defaultOwner = PropertiesManager.getInstance(oid).getProperty("VENDOR", "DEFAULTOWNER", "EXCALIBUR");
	boolean disabledEditOwner = false;
	if (defaultRfqOnly.equalsIgnoreCase("Y") && vendor != null && defaultOwner.equalsIgnoreCase(vendor.getOwner())) {
		disabledEditOwner = true;
	}
%>

<tsa:hidden name="contactName" value=""/>
<tsa:hidden name="Contact_contactCode" value=""/>
<tsa:hidden name="Contact_contactType" value=""/>
<tsa:hidden name="Contact_addressCode" value=""/>
<tsa:hidden name="VendorAccount_vendorId" value="${Vendor_vendorId}"/>
<tsa:hidden name="VendorAffiliate_vendorId" value="${Vendor_vendorId}"/>
<tsa:hidden name="VendorInsurance_vendorId" value="${Vendor_vendorId}"/>
<tsa:hidden name="Address_addressCode" value=""/>
<tsa:hidden name="addressType" value=""/>
<tsa:hidden name="contactType" value=""/>
<%@ include file="/admin/supplier/supplier_hidden_fields.jsp" %>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=vendorName%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>

<%	if (role.getAccessRights("VEND") > 2) {	%>
	<td valign=bottom align=middle>
		<table border=0 cellpadding=2 cellspacing=0>
		<tr>
			<td align="right"><img src="<%=contextPath%>/images/add.gif" border="0"></td>
			<td align=center><% if (!defaultContactSet) {%><a href="javascript: addContact('DEFAULT'); void(0);">Add Default Contact</a><%	}%></td>
			<td align=center><% if (defaultContactSet) {%><a href="javascript: addContact('MAIN'); void(0);">Add Alternate Contact</a><% } %></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
<%	}	%>

</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top>
		<table border=0 width=100%>
		<tr>
			<td width=50% valign=top align=center>
				<table border=1 cellspacing=0 cellpadding=0 width=225px class=browseHdr>
				<tr>
					<td class=browseHdr height=18px nowrap colspan=2>&nbsp;Default Address</td>
				</tr>
				<tr>
					<td class=browseRow>
<%
	if (defaultAddressSet)
	{
		Address address = (Address) addresses.get("DEFAULT");
		AddressPK addressPK = (AddressPK) address.getComp_id();

		if ((HiltonUtility.isEmpty(address.getAddressLine1()) || address.getAddressLine1().equals(vendorName)) && HiltonUtility.isEmpty(address.getAddressLine2() + address.getAddressLine3() + address.getAddressLine4() + address.getCity() + address.getState() + address.getPostalCode()))
		{
%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr><td class=browseRow><br></td></tr>
						<tr>
							<td class=browseRow align=right><a href="javascript: viewAddress('','','','DEFAULT', '${esapi:encodeForJavaScript(Vendor_vendorId)}'); void(0);">Add Default Address</a></td>
						</tr>
						</table>
<%	} else {%>
						<table border=0 cellspacing=0 cellpadding=2 width=80% class=browseRow align="center">
						<tr <%=HtmlWriter.isVisible(oid, "sup-addressLine1")%>>
							<td width="5px">&nbsp;</td>
							<td class=browseRow height=12px nowrap><a href="javascript: viewAddress('','','','<%=addressPK.getAddressCode()%>','<%=addressPK.getAddressType()%>');void(0);"><%=address.getAddressLine1()%>&nbsp;</a></td>
						</tr>
<%		if ( !HiltonUtility.isEmpty(address.getAddressLine2()) ) {	%>
						<tr <%=HtmlWriter.isVisible(oid, "sup-addressLine2")%>>
							<td width="5px">&nbsp;</td>
							<td nowrap class=browseRow><%=address.getAddressLine2()%></td>
						</tr>
<%		}
			if ( !HiltonUtility.isEmpty(address.getAddressLine3()) ) {	%>
						<tr <%=HtmlWriter.isVisible(oid, "sup-addressLine3")%>>
							<td width="5px">&nbsp;</td>
							<td nowrap class=browseRow><%=address.getAddressLine3()%></td>
						</tr>
<%		}
			if ( !HiltonUtility.isEmpty(address.getAddressLine4()) ) {	%>
						<tr <%=HtmlWriter.isVisible(oid, "sup-addressLine4")%>>
							<td width="5px">&nbsp;</td>
							<td nowrap class=browseRow><%=address.getAddressLine4()%></td>
						</tr>
<%		}
			if ( !HiltonUtility.isEmpty(address.getCityStatePostal()) ) {	%>
						<tr <%=HtmlWriter.isVisible(oid, "sup-cityStateZip")%>>
							<td width="5px">&nbsp;</td>
							<td nowrap class=browseRow><%=address.getCityStatePostal()%></td>
						</tr>
<%		}%>
				<tr><td colspan=2>&nbsp;</td></tr>
<%		if ( address.getAddrFld16().equals("Y") ) {	%>
						<tr <%=HtmlWriter.isVisible(oid, "sup-udf16")%>>
							<td width="5px">&nbsp;</td>
							<td nowrap class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf16", "UDF 16", true)%></td>
						</tr>
<%		}
			if ( address.getAddrFld17().equals("Y") ) {	%>
						<tr <%=HtmlWriter.isVisible(oid, "sup-udf17")%>>
							<td width="5px">&nbsp;</td>
							<td nowrap class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf17", "UDF 17", true)%></td>
						</tr>
<%		}
			if ( address.getAddrFld18().equals("Y") ) {	%>
						<tr <%=HtmlWriter.isVisible(oid, "sup-udf18")%>>
							<td width="5px">&nbsp;</td>
							<td nowrap class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf18", "UDF 18", true)%></td>
						</tr>
<%		}	%>
						</table>
<%		}
		} else {%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr><td class=browseRow><br></td></tr>
						<tr>
							<td class=browseRow align=right><a href="javascript: addAddress('','','','DEFAULT', '${esapi:encodeForJavaScript(Vendor_vendorId)}'); void(0);">Add Default Address</a></td>
						</tr>
						</table>
<%	}%>
					</td>
				</tr>
				</table>
			</td>
			<td width=50% valign=top align=center>
				<table border=1 cellspacing=0 cellpadding=0 width=225px class=browseHdr>
				<tr>
					<td class=browseHdr height=18px nowrap colspan=2>&nbsp;Remit To Address</td>
				</tr>
				<tr>
					<td class=browseRow>
<%
	if (remitToAddressSet)
	{
		Address address = (Address) addresses.get("REMITTO");
		AddressPK addressPK = (AddressPK) address.getComp_id();
%>
						<table border=0 cellspacing=0 cellpadding=2 width=80% class=browseRow align="center">
						<tr <%=HtmlWriter.isVisible(oid, "sup-addressLine1")%>>
							<td width="5px">&nbsp;</td>
							<td class=browseRow nowrap>
							<%	if (!disabledEditOwner) { %>
								<a href="javascript: viewAddress('','','','<%=addressPK.getAddressCode()%>','<%=addressPK.getAddressType()%>');void(0);"><%=address.getAddressLine1()%>&nbsp;</a>
							<%	} else { %>
								<%=address.getAddressLine1()%>&nbsp;
							<%	} %>
							</td>
						</tr>
<%		if ( !HiltonUtility.isEmpty(address.getAddressLine2()) ) {	%>
						<tr <%=HtmlWriter.isVisible(oid, "sup-addressLine2")%>>
							<td width="5px">&nbsp;</td>
							<td class=browseRow nowrap><%=address.getAddressLine2()%></td>
						</tr>
<%		}
			if ( !HiltonUtility.isEmpty(address.getAddressLine3()) ) {	%>
						<tr <%=HtmlWriter.isVisible(oid, "sup-addressLine3")%>>
							<td width="5px">&nbsp;</td>
							<td class=browseRow nowrap><%=address.getAddressLine3()%></td>
						</tr>
<%		}
			if ( !HiltonUtility.isEmpty(address.getAddressLine4()) ) {	%>
						<tr <%=HtmlWriter.isVisible(oid, "sup-addressLine4")%>>
							<td width="5px">&nbsp;</td>
							<td class=browseRow nowrap><%=address.getAddressLine4()%></td>
						</tr>
<%		}
			if ( !HiltonUtility.isEmpty(address.getCityStatePostal()) ) {	%>
						<tr <%=HtmlWriter.isVisible(oid, "sup-cityStateZip")%>>
							<td width="5px">&nbsp;</td>
							<td class=browseRow nowrap><%=address.getCityStatePostal()%></td>
						</tr>
<%		}%>
				<tr><td colspan=2>&nbsp;</td></tr>
<%	if ( address.getAddrFld16().equals("Y") ) {	%>
						<tr <%=HtmlWriter.isVisible(oid, "sup-udf16")%>>
							<td width="5px">&nbsp;</td>
							<td nowrap class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf16", "UDF 16", true)%></td>
						</tr>
<%		}
			if ( address.getAddrFld17().equals("Y") ) {	%>
						<tr <%=HtmlWriter.isVisible(oid, "sup-udf17")%>>
							<td width="5px">&nbsp;</td>
							<td nowrap class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf17", "UDF 17", true)%></td>
						</tr>
<%		}
			if ( address.getAddrFld18().equals("Y") ) {	%>
						<tr <%=HtmlWriter.isVisible(oid, "sup-udf18")%>>
							<td width="5px">&nbsp;</td>
							<td nowrap class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf18", "UDF 18", true)%></td>
						</tr>
<%		}	%>
						</table>
<%	} else {%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr><td class=browseRow><br></td></tr>
						<tr>
							<td class=browseRow align=right>
								<%if(!disabledEditOwner){%><a href="javascript: addAddress('','','','REMITTO','${esapi:encodeForHTMLAttribute(Vendor_vendorId)}'); void(0);">Add Remit To Address</a><%}%>
							</td>
						</tr>
						</table>
<%	}%>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr><td colspan=2><br></td></tr>
<%
	for (int i=0; i < contactList.size(); i++)
	{
		String contactCode = (String) contactList.get(i);
		Contact contact = (Contact) contacts.get(contactCode);
		ContactPK contactPK = (ContactPK) contact.getComp_id();
		String addressCode = contact.getAddressCode();
		String contactName = contact.getDisplayName();

		if (i > 0) {
%>
		<tr><td colspan=2><hr size=0 width=90% class="browseHR"></td></tr>
<%	}	%>
		<tr>
			<td width=50% valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=100%>
<%	if (contactPK.getContactType().equals("DEFAULT")) {%>
				<tr>
					<td nowrap align=right width=120px><b>Default Contact</b></td>
					<td>&nbsp;</td>
				</tr>
<%		if (HiltonUtility.isEmpty(contactName)) {
				contactName = "(Edit Default Contact)";
		}
	} else if (contactPK.getContactType().equals("INSURANCE")) { %>
				<tr>
					<td nowrap align=right width=120px><b>Insurance Contact</b></td>
					<td>&nbsp;</td>
				</tr>
<%	} else if (contactPK.getContactType().equals("COMPLIANCE")) { %>
				<tr>
					<td nowrap align=right width=120px><b>Compliance Contact</b></td>
					<td>&nbsp;</td>
				</tr>
<%	} %>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-name")%>>
					<td nowrap align=right width=120px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-name", "Contact Name")%>:</td>
					<td nowrap>
						<a href="javascript: viewContact('<%=contactPK.getContactCode()%>','<%=contactPK.getContactType()%>','<%=contactPK.getVendorId()%>');void(0);"><%=contactName%></a>
						&nbsp;&nbsp;
<%		if (!contactPK.getContactType().equals("DEFAULT") && role.getAccessRights("VEND") == 99) {%>
						<a href="javascript: deleteContact('<%=contactPK.getContactCode()%>','<%=contactPK.getContactType()%>','<%=contactPK.getVendorId()%>', '<%=contactName%>');void(0);"><IMG SRC="<%=contextPath%>/images/delete.gif" BORDER="0" ALT="Delete Contact"></a>
<%		}%>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-title")%>>
				
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-title", "Title")%>:</td>
					<td nowrap><%=contact.getContactTitle()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-telephoneNumber")%>>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-telephoneNumber", "Phone Number")%>:</td>
					<td nowrap><%=contact.getPhoneNumber()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-mobileNumber")%>>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-mobileNumber", "Mobile Number")%>:</td>
					<td nowrap><%=contact.getMobileNumber()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-faxNumber")%>>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-faxNumber", "Fax Number")%>:</td>
					<td nowrap><%=contact.getFaxNumber()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-emailAddress")%>>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-emailAddress", "Email Address")%>:</td>
					<td nowrap><%=contact.getEmailAddr()%></td>
				</tr>
				</table>
			</td>
			<td width=50% valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=100%>
<%	if (addressCode.equals("DEFAULT")) {%>
				<tr>
					<td nowrap align=right width=100px><b>Default Address</b></td>
					<td> - see above</td>
				</tr>
<%		if (!contactPK.getContactType().equals("DEFAULT")) {%>
				<tr>
					<td nowrap align=right width=100px><a href="javascript: viewAddress('<%=contactPK.getContactCode()%>','<%=contactPK.getContactType()%>','<%=contact.getDisplayName()%>','','<%=contactPK.getVendorId()%>'); void(0);">Edit Address</td>
					<td> for <%=contactName%></td>
				</tr>
<%		}
		} else {
			Address address = (Address) addresses.get(addressCode);
			AddressPK addressPK = (AddressPK) address.getComp_id();
%>
				<tr <%=HtmlWriter.isVisible(oid, "sup-addressLine1")%>>
					<td nowrap align=right width=100px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-nameAddress", "Name / Address")%>:</td>
					<td nowrap><a href="javascript: viewAddress('<%=contactPK.getContactCode()%>','<%=contactPK.getContactType()%>','<%=contactName%>','<%=addressPK.getAddressCode()%>','<%=contactPK.getVendorId()%>');void(0);"><%=address.getAddressLine1()%>&nbsp;</a></td>
				</tr>
<%		if ( !HiltonUtility.isEmpty(address.getAddressLine2()) ) {	%>
				<tr <%=HtmlWriter.isVisible(oid, "sup-addressLine2")%>>
					<td nowrap align=right>&nbsp;</td>
					<td nowrap><%=address.getAddressLine2()%></td>
				</tr>
<%		}
			if ( !HiltonUtility.isEmpty(address.getAddressLine3()) ) {	%>
				<tr <%=HtmlWriter.isVisible(oid, "sup-addressLine3")%>>
					<td nowrap align=right>&nbsp;</td>
					<td nowrap><%=address.getAddressLine3()%></td>
				</tr>
<%		}
			if ( !HiltonUtility.isEmpty(address.getAddressLine4()) ) {	%>
				<tr <%=HtmlWriter.isVisible(oid, "sup-addressLine4")%>>
					<td nowrap align=right>&nbsp;</td>
					<td nowrap><%=address.getAddressLine4()%></td>
				</tr>
<%		}
			if ( !HiltonUtility.isEmpty(address.getCityStatePostal()) ) {	%>
				<tr <%=HtmlWriter.isVisible(oid, "sup-cityStateZip")%>>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-cityStateZip", "City, State Zip")%>:</td>
					<td nowrap><%=address.getCityStatePostal()%></td>
				</tr>
<%		}%>
				<tr><td colspan=2>&nbsp;</td></tr>
<%		if ( address.getAddrFld16().equals("Y") ) {	%>
						<tr <%=HtmlWriter.isVisible(oid, "sup-udf16")%>>
							<td width="5px">&nbsp;</td>
							<td nowrap class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf16", "UDF 16", true)%></td>
						</tr>
<%		}
			if ( address.getAddrFld17().equals("Y") ) {	%>
						<tr <%=HtmlWriter.isVisible(oid, "sup-udf17")%>>
							<td width="5px">&nbsp;</td>
							<td nowrap class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf17", "UDF 17", true)%></td>
						</tr>
<%		}
			if ( address.getAddrFld18().equals("Y") ) {	%>
						<tr <%=HtmlWriter.isVisible(oid, "sup-udf18")%>>
							<td width="5px">&nbsp;</td>
							<td nowrap class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf18", "UDF 18", true)%></td>
						</tr>
<%		}
		}	%>
				</table>
			</td>
		</tr>
<% } %>
		</table>
	</td>
	<td valign=top align="right"><br><%@ include file="/admin/supplier/supplier_sidebar.jsp" %></td>
</tr>
</table>

<br>
<br>

<tsa:hidden name="pageFrom" value="contact"/>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	function viewContact(code,type,id) {
		frm.Contact_contactCode.value = code;
		frm.Contact_contactType.value = type;
		frm.Contact_vendorId.value = id;
		doSubmit('/admin/supplier/contact_details.jsp','ContactRetrieveById');
	}

	function deleteContact(code,type,id, contactName) {
		frm.Contact_contactCode.value = code;
		frm.Contact_contactType.value = type;
		frm.Contact_vendorId.value = id;
		if (confirm("Are you sure you want to delete \"" + contactName  + "\"?")){
			doSubmit('/admin/supplier/supplier_contact_list.jsp','ContactDeleteById;ContactAddressRetrieveBySupplier');
		}
	}

	function viewAddress(contactCode, contactType, contactName, addressCode, addressType) {
		frm.Contact_addressCode.value = addressCode;
		frm.Contact_contactCode.value = contactCode;
		frm.Contact_contactType.value = contactType;
		frm.contactName.value = contactName;
		frm.Address_addressCode.value = addressCode;
		frm.Address_addressType.value = addressType;

		doSubmit('/admin/supplier/address_details.jsp','AddressRetrieveById');
	}

	function addContact(contactType) {
		frm.contactType.value = contactType;

		doSubmit('/admin/supplier/contact_details.jsp', 'DoNothing');
	}

	function addAddress(contactCode, contactType, contactName, addressCode, addressType) {
		frm.Contact_addressCode.value = addressCode;
		frm.Contact_contactCode.value = contactCode;
		frm.Contact_contactType.value = contactType;
		frm.contactName.value = contactName;
		frm.Address_addressCode.value = addressCode;
		frm.Address_addressType.value = addressType;

		doSubmit('/admin/supplier/address_details.jsp','DoNothing');
	}

	function validateForm()
	{
		//  if user clicks on Purchase History step
		if (frm.handler.value.indexOf("BrowseRetrieve") > 0)
		{
			setOriginalFilter("PoHeader_vendorId", "=", '${esapi:encodeForJavaScript(Vendor_vendorId)}');
			var newInputField = "<input type=hidden name='PurchaseHistory_vendorId' value=${esapi:encodeForJavaScript(Vendor_vendorId)}>";
			setHiddenFields(newInputField);
			frm.browseName.value = "supplierorders";
		}
		return true;
	}

// End Hide script -->
</SCRIPT>