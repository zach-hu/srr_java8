<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	Vendor	vendor = (Vendor) request.getAttribute("vendor");
	Contact	contact = null;
	Contact	altContact = null;
	Contact	altContact2 = null;
	List contactList = (List) request.getAttribute("contactList");
	if (contactList != null && contactList.size() > 0) {
		contact = (Contact) contactList.get(0);
		if (contactList.size() > 1) {
			altContact = (Contact) contactList.get(1);
		}
		if (contactList.size() > 2) {
			altContact2 = (Contact) contactList.get(2);
		}
	}
	Address	address = (Address) request.getAttribute("address");
	Address	remitToAddress = (Address) request.getAttribute("remitToAddress");
	List	vendorCommRelList = (List) request.getAttribute("vendorCommRelList");
	String	message = HiltonUtility.ckNull((String) request.getAttribute("message"));
	String	s_use_subcommodity = propertiesManager.getProperty("MISC", "USE SUBCOMMODITY", "N");

	if (vendorCommRelList == null) {
		vendorCommRelList = new ArrayList();
	}
	List	commodityList = new ArrayList();
	for (int i=0; i < vendorCommRelList.size(); i++) {
		VendorCommRel vendorCommRel = (VendorCommRel) vendorCommRelList.get(i);
		commodityList.add(vendorCommRel.getComp_id().getCommodityCode());
	}
	if (commodityList.size() < 10) {
		for (int i=commodityList.size(); i < 10; i++) {
			commodityList.add("");
		}
	}
	if (vendor == null) {
		vendor = new Vendor();
	}
	if (contact == null) {
		contact = new Contact();
		ContactPK pk = new ContactPK();
		pk.setVendorId(user.getVendorId());
		pk.setContactType("MAIN");
		pk.setContactCode("001");
		contact.setComp_id(pk);
	}
	if (altContact == null) {
		altContact = new Contact();
		ContactPK pk = new ContactPK();
		pk.setVendorId(user.getVendorId());
		pk.setContactType("ALTERNATE");
		pk.setContactCode("002");
		altContact.setComp_id(pk);
	}
	if (altContact2 == null) {
		altContact2 = new Contact();
		ContactPK pk = new ContactPK();
		pk.setVendorId(user.getVendorId());
		pk.setContactType("ALTERNATE");
		pk.setContactCode("003");
		altContact2.setComp_id(pk);
	}
	if (address == null) {
		address = new Address();
		AddressPK pk = new AddressPK();
		pk.setAddressType(user.getVendorId());
		pk.setAddressCode("DEFAULT");
		address.setComp_id(pk);
	}
	if (remitToAddress == null) {
		remitToAddress = new Address();
		AddressPK pk = new AddressPK();
		pk.setAddressType(user.getVendorId());
		pk.setAddressCode("REMITTO");
		remitToAddress.setComp_id(pk);
	}
	boolean changeVendor1099 = propertiesManager.getProperty("SUPPLIER PORTAL", "CHANGE VENDOR1099", "N").equalsIgnoreCase("Y");
	boolean uploadCertifiedDocuments = propertiesManager.getProperty("SUPPLIER PORTAL", "UPLOAD CERTIFIED DOCUMENTS", "N").equalsIgnoreCase("Y");
	String		poSendCodes = propertiesManager.getProperty("SUPPLIER PORTAL", "POSENDCODES", "PFEMX");
	String		vendorTermsOverride = propertiesManager.getProperty("SUPPLIER PORTAL", "VENDORTERMSOVERRIDE", "");
	String		vendorTerms = vendor.getVendTerms() ;
	if (! HiltonUtility.isEmpty(vendorTermsOverride)) {
		vendorTerms = vendorTermsOverride ;
	}

%>
<script language="JavaScript1.2" src="<%=contextPath%>/scripts/dynamicTables.js"></script>
<script language="JavaScript1.2" src="<%=contextPath%>/scripts/date_check.js"></script>
<script language="JavaScript1.2" src="<%=contextPath%>/scripts/calendar.js"></script>

<tsa:hidden name="Vendor_vendorId" value="<%=user.getVendorId()%>"/>
<tsa:hidden name="Contact_vendorId" value="<%=user.getVendorId()%>"/>
<tsa:hidden name="Contact_contactCode" value="<%=contact.getComp_id().getContactCode()%>"/>
<tsa:hidden name="Contact_contactType" value="<%=contact.getComp_id().getContactType()%>"/>
<tsa:hidden name="Contact_vendorId" value="<%=user.getVendorId()%>"/>
<tsa:hidden name="Contact_contactCode" value="<%=altContact.getComp_id().getContactCode()%>"/>
<tsa:hidden name="Contact_contactType" value="<%=altContact.getComp_id().getContactType()%>"/>
<tsa:hidden name="Contact_vendorId" value="<%=user.getVendorId()%>"/>
<tsa:hidden name="Contact_contactCode" value="<%=altContact2.getComp_id().getContactCode()%>"/>
<tsa:hidden name="Contact_contactType" value="<%=altContact2.getComp_id().getContactType()%>"/>
<tsa:hidden name="Address_addressCode" value="<%=address.getComp_id().getAddressCode()%>"/>
<tsa:hidden name="Address_addressType" value="<%=address.getComp_id().getAddressType()%>"/>
<tsa:hidden name="RemitToAddress_addressCode" value="<%=remitToAddress.getComp_id().getAddressCode()%>"/>
<tsa:hidden name="RemitToAddress_addressType" value="<%=remitToAddress.getComp_id().getAddressType()%>"/>
<tsa:hidden name="VendorCommRel_vendorId" value="<%=user.getVendorId()%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="vendorCommodityUpdate" value="Y"/>
<tsa:hidden name="temp_commodity_code" value="" onchange="setCommodityCode();"/>
<tsa:hidden name="temp_commodity_desc" value=""/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td vAlign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/supplierportal/images/none.gif" width=1px height=1px /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 vAlign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Profile Information</div>
			</td>
			<td nowrap class=hdr12 vAlign=middle>
				<a href="javascript: uploadDocs(); void(0);"><img name="img_attach" src="<%=contextPath%>/supplierportal/images/clip_lg.gif" border=0 valign=top alt="Click here to upload documents"></a>&nbsp;
			</td>
		</tr>
		</table>
	</td>
	<td width=30px vAlign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" width=30px height=31px /></td>
	<td vAlign=bottom align=right height=30px>
		<table cellpadding=0 cellspacing=0 border=0>
		<tr>
			<td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width=1px height=1px /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width=1px height=2px /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr><td align=center>&nbsp;<b><%=message%></b></td></tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=5px>&nbsp;</td>
	<td width=125px valign=top><b>General Information</b></td>
	<td width=5px>&nbsp;</td>
	<td width=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" order=0 width=2px height=100% class=darkShadow></td>
	<td width=5px>&nbsp;</td>
	<td>
		<table border=0 cellspacing=0 cellpadding=2 <%=HtmlWriter.isVisible(oid, "bb-businessType")%>>
<%
	List vendorTypeList = (List) request.getAttribute("vendorTypeList");
	if (vendorTypeList != null && vendorTypeList.size() > 0) { %>
		<tr>
			<td nowrap class=label><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-businessType", "Business Type", true)%></td>
		</tr>
		</table>
		<table border=0 cellspacing=0 cellpadding=0 width=100%>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="Vendor_businessType" value="<%=vendor.getBusinessType()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorTypeList.size(); il++) {
				StdTable stdTable = (StdTable) vendorTypeList.get(il);
				if (stdTable.getStatus().equalsIgnoreCase("02"))
				{
					if (stdTable.getStatus().equalsIgnoreCase("02"))
					{
					StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_businessType" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendor.getBusinessType().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%					fl = il;
					if (vendorTypeList.size()/(fl + 1) <= 2 && b_new_column) {
						b_new_column = false;%>
						</table>
					</td>
					<td width=45% valign=top>
					<table width=100% border=0 cellpadding=0 cellspacing=0>
<%					}
					}
				}
			}%>
					</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
<%	} else {%>
		<tr>
			<td align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-businessType", "Business Type", true)%></td>
			<td><input type=text name="Vendor_businessType" value="<%=vendor.getBusinessType()%>">
		</tr>
<%	}%>
		</table>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100%>
		<tr <%=HtmlWriter.isVisible(oid, "bb-supplierName")%>>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-supplierName", "Name", true)%></td>
			<td colspan=2><input name="Vendor_vendorName" size=50 maxLength=40 value="<%=vendor.getVendorName()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-addressLine2")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-addressLine2", "Address 2", true)%></td>
			<td colspan=2><input name="Address_addressLine2" size=35 maxLength=40 value="<%=address.getAddressLine2()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-addressLine3")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-addressLine3", "Address 3", true)%></td>
			<td colspan=2><input name="Address_addressLine3" size=35 maxLength=40 value="<%=address.getAddressLine3()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-addressLine4")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-addressLine4", "Address 4", true)%></td>
			<td colspan=2><input name="Address_addressLine4" size=35 maxLength=40 value="<%=address.getAddressLine4()%>"></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-city")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-city", "City", true)%></td>
			<td colspan=2>
				<table border=0 cellpadding=1 cellspacing=0 width=100%>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "bb-city")%>><input name="Address_city" size=35 maxLength=30 value="<%=address.getCity()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-state")%> align=right nowrap><a href="javascript: browseState('Address_state'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-state", "State", true)%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-state")%>><input name="Address_state" size=5 maxLength=15 value="<%=address.getState()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-zip")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-zip", "Postal Code", true)%></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-zip")%>><input name="Address_postalCode" size=12 maxLength=15 value="<%=address.getPostalCode()%>"></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-country")%>>
			<td align=right nowrap><a href="javascript: browseLookup('Address_country', 'country'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-country", "Country", true)%></a></td>
			<td colspan=2><input name="Address_country" size=35 maxLength=25 value="<%=address.getCountry()%>" ONCHANGE="upperCase(this);"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-einNumber")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-einNumber", "EIN", true)%></td>
			<td colspan=2 <%=HtmlWriter.isVisible(oid, "bb-einNumber")%>><input name="Vendor_vendorEin" maxLength=10 value="<%=vendor.getVendorEin()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-sicCode")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-sicCode", "SIC Code", true)%></td>
			<td><input name="Vendor_vendorSic" size=15 maxLength=15 value="<%=vendor.getVendorSic()%>"></td>
			<td><i>** <%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-sicInstructions", "Standard Industrial Classification (SIC) codes are described at", false)%> <b><a  href="<%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-sicLink", "http://www.osha.gov/cgi-bin/sic/sicser5", false)%>" id="A1" target="_blank"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-sicLink", "http://www.osha.gov/cgi-bin/sic/sicser5", false)%></a></b>&nbsp;**</i></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-naicsCode")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-naicsCode", "NAICS Code(s)", true)%></td>
			<td colspan=2><input name="Vendor_vendorNaics" size=15 maxLength=4 value="<%=vendor.getVendorNaics()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-dunsNumber")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-dunsNumber", "DUNS Number", true)%></td>
			<td><input name="Vendor_vendorDuns" size=15 maxLength=11 value="<%=vendor.getVendorDuns()%>"></td>
			<td><i>**  You can look this number up at <b><a  href="http://www.dnb.com/" id="A1" target="_blank">http://www.dnb.com/</a>&nbsp; **</i></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-fob")%>>
			<td align=right nowrap><a href="javascript: browseStd('Vendor_fobId', 'FOB'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-fob", "FOB", true)%></a></td>
			<td colspan=2><input name="Vendor_fobId" size=15 maxLength=15 value="<%=vendor.getFobId()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-shipVia")%>>
			<td align=right><a href="javascript: browseStd('Vendor_shipVia', 'SHP'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-shipVia", "Ship Via", true)%></a></td>
			<td colspan=2><input type=text name="Vendor_shipVia" tabindex=16 size=15 maxlength=15 value="<%=vendor.getShipVia()%>" onchange="upperCase(this);"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-orderLeadtime")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-orderLeadtime", "Order Leadtime", true)%></td>
			<td colspan=2><input type=text name="Vendor_leadDays" tabindex=17 size=15 maxlength=15 value="<%=vendor.getLeadDays()%>" onchange="nfilter(this);"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-poSendMethod")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-poSendMethod", "PO Send Method")%></td>
			<td colspan=2>
				<select name="Vendor_printFaxCode" tabIndex=19>
				<% if (poSendCodes.indexOf("P") >= 0) {  %>
					<option value="P" <% if ((vendor.getPrintFaxCode()).indexOf("P")>= 0){ out.println("SELECTED"); }%>>Print PO</option>
				<% } %>
				<% if (poSendCodes.indexOf("F") >= 0) {  %>
					<option value="F" <% if ((vendor.getPrintFaxCode()).indexOf("F")>= 0){ out.println("SELECTED"); }%>>Fax PO</option>
				<% } %>
				<% if (poSendCodes.indexOf("E") >= 0) {  %>
					<option value="E" <% if ((vendor.getPrintFaxCode()).indexOf("E")>= 0){ out.println("SELECTED"); }%>>EDI PO</option>
				<% } %>
				<% if (poSendCodes.indexOf("M") >= 0) {  %>
					<option value="M" <% if ((vendor.getPrintFaxCode()).indexOf("M")>= 0){ out.println("SELECTED"); }%>>E-mail PO</option>
				<% } %>
				<% if (poSendCodes.indexOf("X") >= 0) {  %>
					<option value="X" <% if ((vendor.getPrintFaxCode()).indexOf("X")>= 0){ out.println("SELECTED"); }%>>XML Order</option>
				<% } %>
				</select>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-emailAddress")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-emailAddress", "Email Address", true)%></td>
			<td colspan=2><input type=text name="Vendor_emailAddress" tabindex=23 size=40 maxlength=40 value="<%=vendor.getEmailAddress()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-faxNumber")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-faxNumber", "Fax Number", true)%></td>
			<td colspan=2><input name="Vendor_faxNumber" size=16 maxLenth=25 width=85px value="<%=vendor.getFaxNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-paymentTerms")%>>
			<td align=right nowrap>
			<%	if (DictionaryManager.isLink(oid, "bb-paymentTerms")) { %>
				<a href="javascript: void(0);" onClick="browseLookup('Vendor_vendTerms', 'payment-terms');"><%=DictionaryManager.getLabel(oid, "bb-paymentTerms", "Terms", true)%></a>
			<%	} else { %>
				<%=DictionaryManager.getLabel(oid, "bb-paymentTerms", "Terms", true)%>
			<%	} %>
			</td>
			<td colspan=2><input name="Vendor_vendTerms" maxLength=10 value="<%=vendorTerms%>" <%=HtmlWriter.isReadOnly(oid, "bb-paymentTerms")%>></td>
		</tr>

		<tr <%=HtmlWriter.isVisible(oid, "bb-urlAddress")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-urlAddress", "Web Address", true)%></td>
			<td colspan=2><input name="Vendor_webAddress" maxLength=60 value="<%=vendor.getWebAddress()%>" size=50></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-commodity")%>>
			<td align=right nowrap valign=top><a href="javascript: void(0);" onClick="browseCommodities();"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-commodity", "Commodity", true)%></a></td>
			<td colspan=2>
				<table border=0 cellpadding=2 cellspacing=0 id="commodityTable">
				</table>
			</td>
		</tr>
		</table>
		<br>
	</td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "bb-remitto")%>><td colspan=6 class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
<tr <%=HtmlWriter.isVisible(oid, "bb-remitto")%>>
	<td width=5px>&nbsp;</td>
	<td width=125px valign=top><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-remitto", "Remit To Address", true)%></b></td>
	<td width=5px>&nbsp;</td>
	<td width=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" order=0 width=2px height=100% class=darkShadow></td>
	<td width=5px>&nbsp;</td>
	<td>
		<br>
		<table border=0 cellspacing=0 cellpadding=2>
		<tr <%=HtmlWriter.isVisible(oid, "bb-rt-defaultGeneralInfo")%>>
			<td align=right nowrap width=175px><%=DictionaryManager.getLabel(oid, "bb-rt-defaultGeneralInfo", "Default General Information", true)%></td>
			<td colspan=2>
				<input type="checkbox" name="c_checkbox_defaultGeneralInfo" onclick="defaultGeneralInfo(this);">
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-rt-addressLine1")%>>
			<td align=right nowrap width=175px><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-rt-nameAddress", "Name / Address", true)%></td>
			<td colspan=2><input name="RemitToAddress_addressLine1" size=35 maxLength=40 value="<%=remitToAddress.getAddressLine1()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-rt-addressLine2")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-rt-addressLine2", "Address 2", true)%></td>
			<td colspan=2><input name="RemitToAddress_addressLine2" size=35 maxLength=40 value="<%=remitToAddress.getAddressLine2()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-rt-addressLine3")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-rt-addressLine3", "Address 3", true)%></td>
			<td colspan=2><input name="RemitToAddress_addressLine3" size=35 maxLength=40 value="<%=remitToAddress.getAddressLine3()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-rt-addressLine4")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-rt-addressLine4", "Address 4", true)%></td>
			<td colspan=2><input name="RemitToAddress_addressLine4" size=35 maxLength=40 value="<%=remitToAddress.getAddressLine4()%>"></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-rt-city")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-rt-city", "City", true)%></td>
			<td colspan=2>
				<table border=0 cellpadding=1 cellspacing=0 width=100%>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "bb-rt-city")%>><input name="RemitToAddress_city" size=35 maxLength=30 value="<%=remitToAddress.getCity()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-rt-state")%> align=right nowrap><a href="javascript: browseState('RemitToAddress_state'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-rt-state", "State", true)%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-rt-state")%>><input name="RemitToAddress_state" size=5 maxLength=15 value="<%=remitToAddress.getState()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-rt-zip")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-rt-zip", "Postal Code", true)%></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-rt-zip")%>><input name="RemitToAddress_postalCode" size=12 maxLength=15 value="<%=remitToAddress.getPostalCode()%>"></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-rt-country")%>>
			<td align=right nowrap><a href="javascript: browseLookup('RemitToAddress_country', 'country'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-rt-country", "Country", true)%></a></td>
			<td colspan=2><input name="RemitToAddress_country" size=35 maxLength=25 value="<%=remitToAddress.getCountry()%>" onchange="upperCase(this);"></td>
		</tr>
		</table>
		<br>
	</td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "bb-eft")%>><td colspan=6 class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
<tr <%=HtmlWriter.isVisible(oid, "bb-eft")%>>
	<td width=5px>&nbsp;</td>
	<td width=125px valign=top><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-eft", "EFT", true)%></b></td>
	<td width=5px>&nbsp;</td>
	<td width=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" order=0 width=1px height=100% class=darkShadow></td>
	<td width=5px>&nbsp;</td>
	<td>
		<br>
		<table border=0 cellspacing=0 cellpadding=2>
			<tr <%=HtmlWriter.isVisible(oid, "bb-rt-paymentType")%>>
				<td align=right nowrap><a href="javascript: browseStd('Vendor_vendPaymentType', 'PYTY'); void(0);"><%=DictionaryManager.getLabel(oid, "bb-rt-paymentType", "Payment Type", true)%></a></td>
				<td colspan=2><input name="Vendor_vendPaymentType" size=35 maxLength=25 value="<%=vendor.getVendPaymentType()%>" onchange="upperCase(this);"></td>
			</tr>
			<tr <%=HtmlWriter.isVisible(oid, "bb-bankName")%>>
				<td align=right nowrap width=175px><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-bankName", "Bank Name", true)%></td>
				<td colspan=2><input name="VendorRegister_eftBankName" size=30 maxLength=20 value="<%=vendor.getEftBankName()%>"></td>
			</tr>
			<tr <%=HtmlWriter.isVisible(oid, "bb-routingnumber")%>>
				<td align=right nowrap width=175px><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-routingnumber", "Routing Number", true)%></td>
				<td colspan=2><input name="VendorRegister_eftRoutingNumber" size=30 maxLength=20 value="<%=vendor.getEftRoutingNumber()%>"></td>
			</tr>
			<tr <%=HtmlWriter.isVisible(oid, "bb-accountNumber")%>>
				<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-accountNumber", "Account Number", true)%></td>
				<td colspan=2><input name="VendorRegister_eftAccountNumber" size=30 maxLength=20 value="<%=vendor.getEftAccountNumber()%>"></td>
			</tr>
			<tr <%=HtmlWriter.isVisible(oid, "bb-personName")%>>
				<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-personName", "Person Name", true)%></td>
				<td colspan=2><input name="VendorRegister_eftPersonName" size=30 maxLength=20 value="<%=vendor.getEftPersonName()%>"></td>
			</tr>
			<tr <%=HtmlWriter.isVisible(oid, "bb-wireAccount")%>>
				<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-wireAccount", "Wire Account", true)%></td>
				<td colspan=2><input name="VendorRegister_eftWireAccount" size=30 maxLength=20 value="<%=vendor.getEftWireAccount()%>"></td>
			</tr>
			<tr <%=HtmlWriter.isVisible(oid, "bb-accountType")%>>
				<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-accountType", "Account Type", true)%></td>
				<td colspan=2>
					<select name="VendorRegister_eftAccountType">
						<option value="S" <% if ((vendor.getEftAccountType()).equals("S")){%> SELECTED <%}%>>Savings</option>
						<option value="C" <% if ((vendor.getEftAccountType()).equals("C")){%> SELECTED <%}%>>Checking</option>
					</select>
				</td>
			</tr>
		</table>
		<br>
	</td>
</tr>
<tr><td colspan=6 class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
<tr>
	<td>&nbsp;</td>
	<td valign=top><br><b>Contact Information</b></td>
	<td>&nbsp;</td>
	<td width=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" order=0 width=1px height=100% class=darkShadow></td>
	<td>&nbsp;</td>
	<td>
		<br>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr <%=HtmlWriter.isVisible(oid, "bb-keycontact")%>>
			<td align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-keycontact", "Primary Contact Name", true)%></td>
			<td>
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-salutation")%>>
						<select name="Contact_salutation">
							<option value=""></option>
							<option value="Mr." <% if (contact.getSalutation().equals("Mr.")) {%>selected<%}%>>Mr.</option>
							<option value="Ms." <% if (contact.getSalutation().equals("Ms.")) {%>selected<%}%>>Ms.</option>
							<option value="Mrs." <% if (contact.getSalutation().equals("Mrs.")) {%>selected<%}%>>Mrs.</option>
							<option value="Miss" <% if (contact.getSalutation().equals("Miss")) {%>selected<%}%>>Miss</option>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-firstName")%>><input name="Contact_firstName" size=27 maxLength=20 value="<%=contact.getFirstName()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-middleInitial")%>><input  name="Contact_middleInit" size=3 maxLength=2 value="<%=contact.getMiddleInit()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-lastName")%>><input name="Contact_lastName" size=27 maxLength=20 value="<%=contact.getLastName()%>"></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-title")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-title", "Title", true)%></td>
			<td><input name="Contact_contactTitle" size=27 maxLength=30 value="<%=contact.getContactTitle()%>"></td>
		</tr>
		</table>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber")%> align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-telephoneNumber", "Phone Number", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber")%>><input name="Contact_phoneNumber" size=18 maxLength=30" value="<%=contact.getPhoneNumber()%>"></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-faxNumber")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-faxNumber", "Fax Number", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-faxNumber")%>><input name="Contact_faxNumber" size=18 maxLenth=25 width=85px value="<%=contact.getFaxNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-emailAddress")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-emailAddress", "Email Address", true)%></td>
			<td><input name="Contact_emailAddr" size=45 maxLength=50 value="<%=contact.getEmailAddr()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-mobileNumber")%>>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-mobileNumber", "Mobile Number", true)%>:</td>
			<td><input name="Contact_mobileNumber" tabindex=6 size=30 maxlength=30 value="<%=contact.getMobileNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info1")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info1", "Info 1", true)%></td>
			<td><input name="Contact_info1" size=45 maxLength=50 value="<%=contact.getInfo1()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info2")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info2", "Info 2", true)%></td>
			<td><input name="Contact_info2" size=45 maxLength=50 value="<%=contact.getInfo2()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info3")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info3", "Info 3", true)%></td>
			<td><input name="Contact_info3" size=45 maxLength=50 value="<%=contact.getInfo3()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info4")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info4", "Info 4", true)%></td>
			<td><input name="Contact_info4" size=45 maxLength=50 value="<%=contact.getInfo4()%>"> </td>
		</tr>
		</table>
		<br>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr <%=HtmlWriter.isVisible(oid, "bb-altcontact")%>>
			<td align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-altcontact", "Alternate Contact Name", true)%></td>
			<td>
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-salutation")%>>
						<select name="Contact_salutation">
							<option value=""></option>
							<option value="Mr." <% if (altContact.getSalutation().equals("Mr.")) {%>selected<%}%>>Mr.</option>
							<option value="Ms." <% if (altContact.getSalutation().equals("Ms.")) {%>selected<%}%>>Ms.</option>
							<option value="Mrs." <% if (altContact.getSalutation().equals("Mrs.")) {%>selected<%}%>>Mrs.</option>
							<option value="Miss" <% if (altContact.getSalutation().equals("Miss")) {%>selected<%}%>>Miss</option>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-firstName")%>><input name="Contact_firstName" size=27 maxLength=20 value="<%=altContact.getFirstName()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-middleInitial")%>><input  name="Contact_middleInit" size=3 maxLength=2 value="<%=altContact.getMiddleInit()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-lastName")%>><input name="Contact_lastName" size=27 maxLength=20 value="<%=altContact.getLastName()%>"></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-title")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-title", "Title", true)%></td>
			<td><input name="Contact_contactTitle" size=27 maxLength=30 value="<%=altContact.getContactTitle()%>"></td>
		</tr>
		</table>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber")%> align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-telephoneNumber", "Phone Number", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber")%>><input name="Contact_phoneNumber" size=18 maxLength=30" value="<%=altContact.getPhoneNumber()%>"></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-faxNumber")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-faxNumber", "Fax Number", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-faxNumber")%>><input name="Contact_faxNumber" size=18 maxLenth=25 width=85px value="<%=altContact.getFaxNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-emailAddress")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-emailAddress", "Email Address", true)%></td>
			<td><input name="Contact_emailAddr" size=45 maxLength=50 value="<%=altContact.getEmailAddr()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-mobileNumber")%>>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-mobileNumber", "Mobile Number", true)%>:</td>
			<td><input name="Contact_mobileNumber" tabindex=6 size=30 maxlength=30 value="<%=altContact.getMobileNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info1")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info1", "Info 1", true)%></td>
			<td><input name="Contact_info1" size=45 maxLength=50 value="<%=altContact.getInfo1()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info2")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info2", "Info 2", true)%></td>
			<td><input name="Contact_info2" size=45 maxLength=50 value="<%=altContact.getInfo2()%>"> </td>
		</tr>

		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info3")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info3", "Info 3", true)%></td>
			<td><input name="Contact_info3" size=45 maxLength=50 value="<%=altContact.getInfo3()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info4")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info4", "Info 4", true)%></td>
			<td><input name="Contact_info4" size=45 maxLength=50 value="<%=altContact.getInfo4()%>"> </td>
		</tr>
		</table>
		<br>
		<br>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr <%=HtmlWriter.isVisible(oid, "bb-altcontact2")%>>
			<td align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-altcontact2", "Alternate Contact", true)%></td>
			<td>
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-salutation")%>>
						<select name="Contact_salutation">
							<option value=""></option>
							<option value="Mr." <% if (altContact2.getSalutation().equals("Mr.")) {%>selected<%}%>>Mr.</option>
							<option value="Ms." <% if (altContact2.getSalutation().equals("Ms.")) {%>selected<%}%>>Ms.</option>
							<option value="Mrs." <% if (altContact2.getSalutation().equals("Mrs.")) {%>selected<%}%>>Mrs.</option>
							<option value="Miss" <% if (altContact2.getSalutation().equals("Miss")) {%>selected<%}%>>Miss</option>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-firstName")%>><input name="Contact_firstName" size=27 maxLength=20 value="<%=altContact2.getFirstName()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-middleInitial")%>><input  name="Contact_middleInit" size=3 maxLength=2 value="<%=altContact2.getMiddleInit()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-lastName")%>><input name="Contact_lastName" size=27 maxLength=20 value="<%=altContact2.getLastName()%>"></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-title")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-title", "Title", true)%></td>
			<td><input name="Contact_contactTitle" size=27 maxLength=30 value="<%=altContact2.getContactTitle()%>"></td>
		</tr>
		</table>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber")%> align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-telephoneNumber", "Phone Number", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber")%>><input name="Contact_phoneNumber" size=18 maxLength=30" value="<%=altContact2.getPhoneNumber()%>"></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-faxNumber")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-faxNumber", "Fax Number", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-faxNumber")%>><input name="Contact_faxNumber" size=18 maxLenth=25 width=85px value="<%=altContact2.getFaxNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-emailAddress")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-emailAddress", "Email Address", true)%></td>
			<td><input name="Contact_emailAddr" size=45 maxLength=50 value="<%=altContact2.getEmailAddr()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-mobileNumber")%>>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-mobileNumber", "Mobile Number", true)%>:</td>
			<td><input name="Contact_mobileNumber" tabindex=6 size=30 maxlength=30 value="<%=altContact2.getMobileNumber()%>"></td>
		</tr>

		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info1")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info1", "Info 1", true)%></td>
			<td><input name="Contact_info1" size=45 maxLength=50 value="<%=altContact2.getInfo1()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info2")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info2", "Info 2", true)%></td>
			<td><input name="Contact_info2" size=45 maxLength=50 value="<%=altContact2.getInfo2()%>"> </td>
		</tr>

		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info3")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info3", "Info 3", true)%></td>
			<td><input name="Contact_info3" size=45 maxLength=50 value="<%=altContact2.getInfo3()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info4")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info4", "Info 4", true)%></td>
			<td><input name="Contact_info4" size=45 maxLength=50 value="<%=altContact2.getInfo4()%>"> </td>
		</tr>
		</table>
	</td>
</tr>
<tr><td colspan=6 class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
<tr>
	<td>&nbsp;</td>
	<td valign=top><br><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-additionalInformation", "Additional Information", true)%></b></td>
	<td>&nbsp;</td>
	<td width=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" order=0 width=1px height=100% class=darkShadow></td>
	<td>&nbsp;</td>
	<td>
<%
	List vendorUdf1List = (List) request.getAttribute("vendorUdf1List");
	if (vendorUdf1List != null && vendorUdf1List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "sup-VN01")%>>
		<tr>
			<td nowrap><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-VN01", "Supplier UDF 01", true)%> - <%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-VN01CheckAll", "Check all that apply", true)%></b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="Vendor_vendUdf1" value="<%=vendor.getVendUdf1()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf1List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf1List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorUdf1" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendor.getVendUdf1().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
				if (vendorUdf1List.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;%>
						</table>
					</td>
					<td width=45% valign=top>
					<table width=100% border=0 cellpadding=0 cellspacing=0>
<%			}
			}%>
					</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-VN01")%> width=100%>
		<tr>
			<td align=right nowrap width=150px><a href="javascript: browseStd('Vendor_vendUdf1','VN01'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN01", "Supplier UDF 01", true, true, "Vendor_vendUdf1")%></a></td>
			<td><input type=text name="Vendor_vendUdf1" value="<%=vendor.getVendUdf1()%>"></td>
		</tr>
		</table>
<%	}

	List vendorUdf2List = (List) request.getAttribute("vendorUdf2List");
	if (vendorUdf2List != null && vendorUdf2List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-VN02")%>>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN02", "Supplier UDF 02", true)%> - <%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN02CheckAll", "Check all that apply", true)%></b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="Vendor_vendUdf2" value="<%=vendor.getVendUdf2()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf2List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf2List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorUdf2" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendor.getVendUdf2().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
				if (vendorUdf2List.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;%>
						</table>
					</td>
					<td width=45% valign=top>
					<table width=100% border=0 cellpadding=0 cellspacing=0>
<%			}
			}%>
					</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-VN02")%> width=100%>
		<tr>
			<td align=right nowrap width=150px><a href="javascript: browseStd('Vendor_vendUdf2','VN02'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN02", "Supplier UDF 02", true, true, "Vendor_vendUdf2")%></a></td>
			<td><input type=text name="Vendor_vendUdf2" value="<%=vendor.getVendUdf2()%>"></td>
		</tr>
		</table>
<%	}
	List vendorUdf3List = (List) request.getAttribute("vendorUdf3List");
	if (vendorUdf3List != null && vendorUdf3List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-VN03")%>>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN03", "Supplier UDF 03", true)%> - <%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN03CheckAll", "Check all that apply", true)%></b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="Vendor_vendUdf3" value="<%=vendor.getVendUdf3()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf3List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf3List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorUdf3" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendor.getVendUdf3().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
				if (vendorUdf3List.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;%>
						</table>
					</td>
					<td width=45% valign=top>
					<table width=100% border=0 cellpadding=0 cellspacing=0>
<%			}
			}%>
					</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
		<br>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-VN03")%> width=100%>
		<tr>
			<td align=right nowrap width=150px>
			<%	if (DictionaryManager.isLink(oid, "bb-VN03")) { %>
				<a href="javascript: browseStd('Vendor_vendorUdf3','VN03'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN03", "Supplier UDF 03", true, true, "Vendor_vendorUdf3")%></a>
			<%	} else { %>
				<%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN03", "Supplier UDF 03", true, true, "Vendor_vendorUdf3")%>
			<%	} %>
			</td>
			<td><input type=text name="Vendor_vendUdf3" value="<%=vendor.getVendUdf3()%>"></td>
		</tr>
		</table>
<%	}
	List vendorUdf4List = (List) request.getAttribute("vendorUdf4List");
	if (vendorUdf4List != null && vendorUdf4List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-VN04")%>>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN04", "Supplier UDF 04", true)%> - <%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN04CheckAll", "Check all that apply", true)%></b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="Vendor_vendUdf4" value="<%=vendor.getVendUdf4()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf4List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf4List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorUdf4" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendor.getVendUdf4().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
				if (vendorUdf4List.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;%>
						</table>
					</td>
					<td width=45% valign=top>
					<table width=100% border=0 cellpadding=0 cellspacing=0>
<%			}
			}%>
					</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
		<br>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-VN04")%> width=100%>
		<tr>
			<td align=right nowrap width=150px>
			<%	if (DictionaryManager.isLink(oid, "bb-VN04")) { %>
				<a href="javascript: browseStd('Vendor_vendorUdf4','VN04'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN04", "Supplier UDF 04", true, true, "Vendor_vendorUdf4")%></a>
			<%	} else { %>
				<%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN04", "Supplier UDF 04", true, true, "Vendor_vendorUdf4")%>
			<%	} %>
			</td>

			<td><input type=text name="Vendor_vendUdf4" value="<%=vendor.getVendUdf4()%>"></td>
		</tr>
		</table>
<%	}

	List vendorUdf5List = (List) request.getAttribute("vendorUdf5List");
	if (vendorUdf5List != null && vendorUdf5List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-VN05")%>>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN05", "Supplier UDF 05", true)%> - <%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN05CheckAll", "Check all that apply", true)%></b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="Vendor_vendUdf5" value="<%=vendor.getVendUdf5()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf5List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf5List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorUdf5" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendor.getVendUdf5().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
				if (vendorUdf5List.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;%>
						</table>
					</td>
					<td width=45% valign=top>
					<table width=100% border=0 cellpadding=0 cellspacing=0>
<%			}
			}%>
					</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
		<br>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-VN05")%> width=100%>
		<tr>
			<td align=right nowrap width=150px><a href="javascript: browseStd('Vendor_vendUdf5','VN05'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN05", "Supplier UDF 05", true, true, "Vendor_vendUdf5")%></a></td>
			<td><input type=text name="Vendor_vendUdf5" value="<%=vendor.getVendUdf5()%>"></td>
		</tr>
		</table>
<%	}%>
<br><br>
		<table border=0 cellspacing=1 cellpadding=1 width=100%>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN06")%> align=right width=150px><a href="javascript: browseStd('Vendor_vendUdf6','VN06'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN06", "Supplier UDF 06", true, true, "Vendor_vendUdf6")%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN06")%>><input type=text name="Vendor_vendUdf6" tabindex=7 maxlength=15 value="<%=HiltonUtility.ckNull(vendor.getVendUdf6())%>"></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN07")%> align=right width=150px><a href="javascript: browseStd('Vendor_vendUdf7','VN07'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN07", "Supplier UDF 07", true, true, "Vendor_vendUdf7")%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN07")%>><input type=text name="Vendor_vendUdf7" tabindex=8 maxlength=15 value="<%=HiltonUtility.ckNull(vendor.getVendUdf7())%>"></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN08")%> align=right width=150px><a href="javascript: browseStd('Vendor_vendUdf8','VN08'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN08", "Supplier UDF 08", true, true, "Vendor_vendUdf8")%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN08")%>><input type=text name="Vendor_vendUdf8" tabindex=9 maxlength=15 value="<%=HiltonUtility.ckNull(vendor.getVendUdf8())%>"></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN09")%> align=right width=150px><a href="javascript: browseStd('Vendor_vendUdf9','VN09'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN09", "Supplier UDF 09", true, true, "Vendor_vendUdf9")%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN09")%>><input type=text name="Vendor_vendUdf9" tabindex=10 maxlength=15 value="<%=HiltonUtility.ckNull(vendor.getVendUdf9())%>"></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN10")%> align=right width=150px><a href="javascript: browseStd('Vendor_vendUdf10','VN10'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN10", "Supplier UDF 10", true, true, "Vendor_vendUdf10")%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN10")%>><input type=text name="Vendor_vendUdf10" tabindex=11 maxlength=15 value="<%=HiltonUtility.ckNull(vendor.getVendUdf10())%>"></td>
		</tr>
		</table>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100%>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-yearsInBusiness")%> align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-yearsInBusiness", "Years In Business", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-yearsInBusiness")%>><input name="Vendor_yearsInBusiness" value="<%=vendor.getYearsInBusiness()%>" size = 10 maxLength=15 onchange="nfilter(this);" style="text-align: right"></td>
		</tr>
		</table>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-refInformation")%>>
		<tr <%=HtmlWriter.isVisible(oid, "bb-refInformation")%>><td colspan=2><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-refInformation", "Current / Previous Client Reference", false)%></b></td></tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-refCompanyName")%>>
			<td align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-refCompanyName", "Company Name", true)%></td>
			<td><input type=text name="Vendor_refCompanyName" size=75 maxLength=255 value="<%=vendor.getRefCompanyName()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-refCompanyName")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-refPhoneNumber", "Phone Number", true)%></td>
			<td><input type=text name="Vendor_refPhoneNumber" size=75 maxLength=255 value="<%=vendor.getRefPhoneNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-refContactName")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-refContactName", "Contact Name", true)%></td>
			<td><input type=text name="Vendor_refContactName" size=75 maxLength=255 value="<%=vendor.getRefContactName()%>"></td>
		</tr>
		</table>
		<br>
	</td>
</tr>
<tr><td colspan=6 class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
<tr>
	<td>&nbsp;</td>
	<td valign=top><br><b>Business Ownership Information</b></td>
	<td>&nbsp;</td>
	<td width=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" order=0 width=1px height=100% class=darkShadow></td>
	<td>&nbsp;</td>
	<td>
<%
	List ownershipTypesList = (List) request.getAttribute("ownershipTypeList");
	if (ownershipTypesList != null && ownershipTypesList.size() > 0){ %>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-ownershipType")%>>
		<tr><td><br></td></tr>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-ownershipType", "Ownership Type", true)%> / Check all that apply</b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
					<table width=100% border=0 cellpadding=0 cellspacing=0>
					<tsa:hidden name="Vendor_ownershipType" value="<%=vendor.getOwnershipType()%>"/>
<%	boolean b_new_column = true;
		float fl = 0;
		for (int il = 0; il < ownershipTypesList.size(); il++) {
			StdTable stdTable = (StdTable) ownershipTypesList.get(il);
			if (stdTable.getStatus().equalsIgnoreCase("02"))
			{
			StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
					<tr><td><input name="as_ownershipType" type=checkbox value="<%=stdTablePK.getTableKey()%>" <%if (vendor.getOwnershipType().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%> ONCLICK="setOwnershipType(<%=il%>);">&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
			if (ownershipTypesList.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;%>
					</table>
					</td>
					<td width=45% valign=top>
					<table width=100% border=0 cellpadding=0 cellspacing=0>
<%			}
			}
		}%>
					</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "ownershipType")%>>
		<tr><td><br></td></tr>
		<tr>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-ownershipType", "Ownership Type", true)%></td>
			<td align=right><input type=text name="Vendor_ownershipType" value="<%=vendor.getOwnershipType()%>"></td>
		</tr>
		</table>
<%	}

	List classificationTypeList = (List) request.getAttribute("classificationTypeList");
	if (classificationTypeList != null && classificationTypeList.size() > 0) { %>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-vendorClass")%>>
		<tr><td><br></td></tr>
		<tr>
			<td nowrap><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-vendorClass", "Vendor Class", true)%> / Check all that apply</b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
						<tsa:hidden name="Vendor_vendorClass" value="<%=vendor.getVendorClass()%>"/>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < classificationTypeList.size(); il++) {
				StdTable stdTable = (StdTable) classificationTypeList.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorClass" type=checkbox value="<%=stdTablePK.getTableKey()%>" <%if (vendor.getVendorClass().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
				if (classificationTypeList.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;%>
						</table>
					</td>
					<td width=45% valign=top>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%			}
			}%>
						</table>
					</td>
				</tr>
				<%	if(changeVendor1099) { %>
				<tr>
					<td width="10%">&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-form1099Required")%>>
						<input type="checkbox" name="c_checkbox_vendor1099" <%if ((vendor.getVendor1099()).equals("Y")) { %>checked <% } %> value="Y" tabIndex=5 disabled>&nbsp;<%=DictionaryManager.getLabel(oid, "sup-form1099Required", "Form 1099 Required")%>
						<tsa:hidden name="VendorRegister_vendor1099" value="<%=vendor.getVendor1099()%>"/>
					</td>
				</tr>
				<%	} %>
				</table>
			</td>
		</tr>
		</table>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-vendorClass")%>>
		<tr><td><br></td></tr>
		<tr>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-vendorClass", "Vendor Class", true)%></td>
			<td align=right><input type=text name="Vendor_vendorClass" value="<%=vendor.getVendorClass()%>"></td>
		</tr>
		</table>
<%	}

	List diverseCertifiedOrgList = (List) request.getAttribute("diverseCertifiedOrgList");
	if (diverseCertifiedOrgList != null && diverseCertifiedOrgList.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-diverseCertOrgs")%>>
		<tr><td><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-diverseCertOrgs", "Diverse Certified Organizations", true)%>  Check all that apply.</td></tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="Vendor_diverseCertOrgs" value="<%=vendor.getDiverseCertOrgs()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < diverseCertifiedOrgList.size(); il++) {
				StdTable stdTable = (StdTable) diverseCertifiedOrgList.get(il);
				if (stdTable.getStatus().equalsIgnoreCase("02"))
				{
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_diverseCertOrg" type=checkbox value="<%=stdTablePK.getTableKey()%>" <%if(uploadCertifiedDocuments){%>onclick="loadAttachTypeC();"<%}%> <%if (vendor.getDiverseCertOrgs().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
				if (diverseCertifiedOrgList.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;%>
						</table>
					</td>
					<td width=45% valign=top>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%			}
				}
			}%>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
<%	} else {%>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-diverseCertOrgs")%>>
		<tr <%=HtmlWriter.isVisible(oid, "bb-diverseCertOrgs")%>>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-diverseCertOrgs", "Diverse Certified Organizations", true)%></td>
			<td align=right><input type=text name="Vendor_diverseCertOrgs" value="<%=vendor.getDiverseCertOrgs()%>"></td>
		</tr>
		</table>
<%	}%>
		<br>
		<%	if (uploadCertifiedDocuments) { %>
		<table id="attachTypeC" border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-attachTypeC")%>>
		<tr>
			<td align=left width="50px">&nbsp;</td>
			<td align=left><%=DictionaryManager.getLabel(oid, "bb-attachTypeC", "attachTypeC", true)%>:</td>
			<td>
				<a href="javascript: uploadDocs('C'); void(0);" title="Upload Document"><img src="<%=contextPath%>/supplierportal/images/clip_lg.gif" border="0" alt="Upload Document"></a>
			</td>
		</tr>
		</table>
		<table id="attachTypeS" border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-attachTypeS")%>>
		<tr>
			<td align=left width="50px">&nbsp;</td>
			<td align=left><%=DictionaryManager.getLabel(oid, "bb-attachTypeS", "attachTypeS", true)%>:</td>
			<td>
				<a href="javascript: uploadDocs('S'); void(0);" title="Upload Document"><img src="<%=contextPath%>/supplierportal/images/clip_lg.gif" border="0" alt="Upload Document"></a>
			</td>
		</tr>
		</table>
		<table id="attachTypeW" border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-attachTypeW")%>>
		<tr>
			<td align=left width="50px">&nbsp;</td>
			<td align=left><%=DictionaryManager.getLabel(oid, "bb-attachTypeW", "attachTypeW", true)%>:</td>
			<td>
				<a href="javascript: uploadDocs('W'); void(0);" title="Upload Document"><img src="<%=contextPath%>/supplierportal/images/clip_lg.gif" border="0" alt="Upload Document"></a>
			</td>
		</tr>
		</table>
		<br>
		<%	} %>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-vendorDiversityProgram")%>>
		<tr <%=HtmlWriter.isVisible(oid, "bb-vendorDiversityProgram")%>>
			<td>Do you have a Supplier Diversity Program?</td>
			<td align=right width=25px><input type=radio name="Vendor_diversityProgram" value="Y" <% if (vendor.getDiversityProgram().equalsIgnoreCase("Y")) {%>checked<%}%>></td>
			<td>Yes</td>
			<td align=right width=25px><input type=radio name="Vendor_diversityProgram" value="N" <% if (vendor.getDiversityProgram().equalsIgnoreCase("N")) {%>checked<%}%>></td>
			<td>No</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-vendorSubcontract")%>>
			<td>Do you subcontract with diverse suppliers?</td>
			<td align=right width=25px><input type=radio name="Vendor_subcontract" value="Y" <% if (vendor.getSubcontract().equalsIgnoreCase("Y")) {%>checked<%}%>></td>
			<td>Yes</td>
			<td align=right width=25px><input type=radio name="Vendor_subcontract" value="N" <% if (vendor.getSubcontract().equalsIgnoreCase("N")) {%>checked<%}%>></td>
			<td>No</td>
		</tr>
		</table>
	</td>
</tr>
<tr  <%=HtmlWriter.isVisible(oid, "bb-certificationSection")%>><td colspan=6 class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
<tr  <%=HtmlWriter.isVisible(oid, "bb-certificationSection")%>>
	<td>&nbsp;</td>
	<td valign=top><br><b>Certification Statement</b></td>
	<td>&nbsp;</td>
	<td width=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" order=0 width=1px height=100% class=darkShadow></td>
	<td>&nbsp;</td>
	<td>
		<br>
		<table border=0 cellspacing=1 cellpadding=1  <%=HtmlWriter.isVisible(oid, "bb-certificationStatement")%>>
		<tr>
			<td><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-certificationStatement", "", true)%></td>
		</tr>
		</table>
<%	if (oid.equals("BSC04P")) {%>
		<br>
		<table border=0 cellspacing=0 cellpadding=2>
		<tr>
			<td valign=top align=right><input type=checkbox name="as_vendorUdf8" value="Y" <% if (vendor.getVendUdf8().equals("Y")) {%>checked<%}%>><tsa:hidden name="Vendor_vendUdf8" value="<%=vendor.getVendUdf8()%>"/></td>
			<td>My company has not been excluded from federal procurement and non-procurement programs.</td>
		</tr>
		</table>
<%	}%>
		<br>
		<table border=0 cellspacing=0 cellpadding=2 class="requiredLabelHighlight"  <%=HtmlWriter.isVisible(oid, "bb-certificationCkboxStmt")%>>
		<tr>
			<td class="requiredLabelHighlight" valign=top align=right><input type=checkbox name="as_digitalSig" value="Y" <% if (vendor.getDigitalSig().equals("Y")) {%>checked<%}%>><tsa:hidden name="Vendor_digitalSig" value="<%=vendor.getDigitalSig()%>"/></td>
			<td class="requiredLabelHighlight"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-certificationCkboxStmt", "I HEREBY CERTIFY ALL THE INFORMATION PROVIDED ON THIS FORM TO BE TRUE AND ACCURATE.", true)%></td>
		</tr>
		</tr>
		</table>
		<br>
	</td>
</tr>
<tr><td colspan=6 class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
</table>

<br>

<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
	<td align=center width=50%>
		<a href="javascript: validateProfileInformation('VendorValidate'); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_submit.gif" border=0></a>
	</td>
	<td align=center width=50%>
		<a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_cancel.gif" border=0></a>
	</td>
</tr>
</table>

<%@ include file="/supplierportal/system/footer.jsp" %>

<script value=JavaScript>
<!--Hide Script
	frm = document.purchaseform;

	var currentRow = 0;
	var maxRows = 0;
	var certificationCheckRequired = <%=DictionaryManager.isVisible(oid, "bb-certificationCkboxStmt") && DictionaryManager.isVisible(oid, "bb-certificationSection")%>;

	setCommodities();

	function setCommodities() {
<% for (int ic=0; ic < commodityList.size(); ic++) {
		String commodityCode = (String) commodityList.get(ic);
%>
		addCommodity("<%=commodityList.get(ic)%>","<%=CommodityManager.getInstance().getCommodityDescription(oid, commodityCode)%>");
<% }%>
	}

	function browseCommodities () {
		var selected = "";
		var selectCnt = 0;
		var args = "table=commodities";

		for (var i = 0; i < 10; i++) {
			if ( !isEmpty(frm.VendorCommRel_commodityCode[i].value) ) {
				if ( selected.length > 0 ) {
					selected = selected + "\u0008" + frm.VendorCommRel_commodityCode[i].value;
				}
				else {
					selected = frm.VendorCommRel_commodityCode[i].value;
				}
				selectCnt++;
			}
		}
		args = args + "&selected=" + selected + "&selectCnt=" + selectCnt;
<% if (propertiesManager.getProperty("MISC", "USE SUBCOMMODITY", "N").equalsIgnoreCase("Y")) {%>
		browseCommodity('temp_commodity_code','subcommodity','<%=propertiesManager.getProperty("MISC", "SubCommodityType", "")%>');
<% } else {%>
		browseCommodity('temp_commodity_code', 'commodity', '<%=propertiesManager.getProperty("MISC", "COMMODITYTYPE", "")%>');
<% }%>
	}

	function browseCommodity(formField, xml, commodityType) {
		if (xml == "subcommodity") {
			var currentCode = document.getElementById(formField).value;
			popupParameters =  popupParameters + "formField=" + formField +";browseName=" + xml + ";allowBrowse=" + frm.allowBrowse.value + ";selectedCode=" + currentCode + ";Commodity_commodity=" + currentCode;
			doSubmitToLookup('/supplierportal/browse/browse_subcommodity_tree.jsp', 'SubCommodityRetrieveTree', 'WIDTH=700px', 'HEIGHT=500px');
		} else if (!isEmpty(commodityType)) {
			var currentCode = document.getElementById(formField).value;
			popupParameters =  popupParameters + "formField=" + formField +";browseName=" + xml + ";allowBrowse=" + frm.allowBrowse.value + ";selectedCode=" + currentCode + ";Commodity_commodity=" + currentCode;
			doSubmitToLookup('/supplierportal/browse/browse_commodity_tree.jsp', 'CommodityRetrieveTree', 'WIDTH=700px', 'HEIGHT=500px');
		} else {
			browseLookup(formField, xml);
		}
	}

	function addCommodity(commodityCode, desc)
	{
		var myTable = document.getElementById("commodityTable");
		var myRow = createRow(myTable);

		currentRow = myTable.rows.length - 1;

		var myCell = createCell(myRow);
//		myCell.innerHTML = "<input name=\"VendorCommRel_commodityCode\" size=15 value=\"" + commodityCode + "\" onfocus=\"this.blur();\" class=disabledTxtBox readOnly>";
		myCell.innerHTML = "<input name=\"VendorCommRel_commodityCode\" size=15 value=\"" + commodityCode + "\" onfocus=\"this.blur();\" class=disabledTxtBox readOnly><input type=text name=\"as_commodityDesc\" value=\"" + desc + "\" size=50 onfocus=\"this.blur();\" disabled>";

		myCell = createCell(myRow);
		myCell.innerHTML = "<a href=\"javascript: deleteCommodity(" + currentRow + "); void(0);\"><img src=\"<%=contextPath%>\\images\\delete.gif\" border=0></a>";
	}

	function deleteCommodity(row) {
		var myTable = document.getElementById("commodityTable");
		var rows = myTable.rows.length;

		if (rows == 0) {
			return;
		}
		else if (rows > 1) {
			for (var i = row; i < rows; i++) {
				var commodityCode = "";
				var commodityDesc =  "";

				if ((i+1) < rows) {
					commodityCode = frm.VendorCommRel_commodityCode[i + 1].value;
					commodityDesc = frm.as_commodityDesc[i + 1].value;
				}

				frm.VendorCommRel_commodityCode[i].value = commodityCode;
				frm.as_commodityDesc[i].value = commodityDesc;
			}
			frm.VendorCommRel_commodityCode[rows - 1].value = "";
			frm.as_commodityDesc[rows - 1].value = "";
		}
		else {
			frm.VendorCommRel_commodityCode.value = "";
			frm.as_commodityDesc[rows - 1].value = "";
		}
	}

	function setCommodityCode() {
		for (var i=0; i < 10; i++) {
			if (isEmpty(frm.VendorCommRel_commodityCode[i].value)) {
				frm.VendorCommRel_commodityCode[i].value = frm.temp_commodity_code.value;
				frm.as_commodityDesc[i].value = frm.temp_commodity_desc.value;
				frm.temp_commodity_code.value = "";
				frm.temp_commodity_desc.value = "";
				break;
			}
		}
		if (!isEmpty(frm.temp_commodity_code.value)) {
				frm.VendorCommRel_commodityCode[0].value = frm.temp_commodity_code.value;
				frm.as_commodityDesc[i].value = frm.temp_commodity_desc.value;
				frm.temp_commodity_code.value = "";
				frm.temp_commodity_desc.value = "";
		}
	}
	function setCommodityCodes() {
		var myTable = document.getElementById("commodityTable");
		var rows = myTable.rows.length;

		// make sure there are no duplicates
		if (rows > 1) {
			var codesEntered = "";
			for (var i=rows - 1; i >= 0; i--) {
				var tempCode = frm.VendorCommRel_commodityCode[i].value;

				if (isEmpty(tempCode) || codesEntered.indexOf("[" + tempCode + "]") >= 0) {
					deleteCommodity(i);
				} else {
					codesEntered = codesEntered + "[" + tempCode + "]";
				}
			}
		}
	}

	function setBusinessType() {
		if (frm.Vendor_businessType) {
			var businessTypes = frm.elements.item("as_businessType");
			if (businessTypes != undefined) {
				if (businessTypes.length > 1 ) {
					frm.Vendor_businessType.value = "";
					for (var i=0; i < frm.elements.item("as_businessType").length; i++){
						if (frm.as_businessType[i].checked) {
							frm.Vendor_businessType.value = frm.Vendor_businessType.value + frm.as_businessType[i].value;
						}
					}
				} else {
					if (frm.as_businessType.checked) {
						frm.Vendor_businessType.value = frm.as_businessType.value;
					}
				}
			}
		}
	}

	function setVendorClass() {
		if (frm.Vendor_vendorClass) {
			var vendorClasses = frm.elements.item("as_vendorClass");
			if (vendorClasses != undefined) {
				if (vendorClasses.length > 1 ) {
					frm.Vendor_vendorClass.value = "";
					for (var i=0; i < frm.elements.item("as_vendorClass").length; i++){
						if (frm.as_vendorClass[i].checked) {
							frm.Vendor_vendorClass.value = frm.Vendor_vendorClass.value + frm.as_vendorClass[i].value;
						}
					}
				} else {
					if (frm.as_vendorClass.checked) {
						frm.Vendor_vendorClass.value = frm.as_vendorClass.value;
					}
				}
			}
		}
	}

	function setOwnershipType() {
		if (frm.Vendor_ownershipType) {
			var ownershipTypes = frm.elements.item("as_ownershipType");
			if (ownershipTypes != undefined) {
				if (ownershipTypes.length > 1 ) {
					frm.Vendor_ownershipType.value = "";
					for (var i=0; i < frm.elements.item("as_ownershipType").length; i++){
						if (frm.as_ownershipType[i].checked) {
							frm.Vendor_ownershipType.value = frm.Vendor_ownershipType.value + frm.as_ownershipType[i].value;
						}
					}
				} else {
					if (frm.as_ownershipType.checked) {
						frm.Vendor_ownershipType.value = frm.as_ownershipType.value;
					}
				}
			}
		}
	}


	function setDiverseCertOrgs() {
		if (frm.Vendor_diverseCertOrgs) {
			var diverseCertOrgs = frm.elements.item("as_diverseCertOrg");
			if (diverseCertOrgs != undefined) {
				if (diverseCertOrgs.length > 1) {
					frm.Vendor_diverseCertOrgs.value = "";
					for (var i=0; i < frm.elements.item("as_diverseCertOrg").length; i++){
						if (frm.as_diverseCertOrg[i].checked) {
							frm.Vendor_diverseCertOrgs.value = frm.Vendor_diverseCertOrgs.value + frm.as_diverseCertOrg[i].value;
						}
					}
				} else {
					if (frm.as_diverseCertOrg.checked) {
						frm.Vendor_diverseCertOrgs.value = frm.as_diverseCertOrg.value;
					}
				}
			}
		}
	}

	function setVendorUdf1() {
		if (frm.Vendor_vendUdf1) {
			var vendorUdfs = frm.elements.item("as_vendorUdf1");
			if (vendorUdfs != undefined) {
				if (vendorUdfs.length > 1 ) {
					frm.Vendor_vendUdf1.value = "";
					for (var i=0; i < frm.elements.item("as_vendorUdf1").length; i++){
						if (frm.as_vendorUdf1[i].checked) {
							frm.Vendor_vendUdf1.value + frm.as_vendorUdf1[i].value;
						}
					}
				} else {
					if (frm.as_vendorUdf1.checked) {
						frm.Vendor_vendUdf1.value = frm.as_vendorUdf1.value;
					}
				}
			}
		}
	}

	function setVendorUdf2() {
		if (frm.Vendor_vendUdf2) {
			var vendorUdfs = frm.elements.item("as_vendorUdf2");
			if (vendorUdfs != undefined) {
				if (vendorUdfs.length > 1 ) {
					frm.Vendor_vendUdf2.value = "";
					for (var i=0; i < frm.elements.item("as_vendorUdf2").length; i++){
						if (frm.as_vendorUdf2[i].checked) {
							frm.Vendor_vendUdf2.value = frm.Vendor_vendUdf2.value + frm.as_vendorUdf2[i].value;
						}
					}
				} else {
					if (frm.as_vendorUdf2.checked) {
						frm.Vendor_vendUdf2.value = frm.as_vendorUdf2.value;
					}
				}
			}
		}
	}

	function setVendorUdf3() {
		if (frm.Vendor_vendUdf3) {
			var vendorUdfs = frm.elements.item("as_vendorUdf3");
			if (vendorUdfs != undefined) {
				if (vendorUdfs.length > 1 ) {
					frm.Vendor_vendUdf3.value = "";
					for (var i=0; i < frm.elements.item("as_vendorUdf3").length; i++){
						if (frm.as_vendorUdf3[i].checked) {
							frm.Vendor_vendUdf3.value = frm.Vendor_vendUdf3.value + frm.as_vendorUdf3[i].value;
						}
					}
				} else {
					if (frm.as_vendorUdf3.checked) {
						frm.Vendor_vendUdf3.value = frm.as_vendorUdf3.value;
					}
				}
			}
		}
	}

	function setVendorUdf4() {
		if (frm.Vendor_vendUdf4) {
			var vendorUdfs = frm.elements.item("as_vendorUdf4");
			if (vendorUdfs != undefined) {
				if (vendorUdfs.length > 1 ) {
					frm.Vendor_vendUdf4.value = "";
					for (var i=0; i < frm.elements.item("as_vendorUdf4").length; i++){
						if (frm.as_vendorUdf4[i].checked) {
							frm.Vendor_vendUdf4.value = frm.Vendor_vendUdf4.value + frm.as_vendorUdf4[i].value;
						}
					}
				} else {
					if (frm.as_vendorUdf4.checked) {
						frm.Vendor_vendUdf4.value = frm.as_vendorUdf4.value;
					}
				}
			}
		}
	}

	function setVendorUdf5() {
		if (frm.Vendor_vendUdf5) {
			var vendorUdfs = frm.elements.item("as_vendorUdf5");
			if (vendorUdfs != undefined) {
				if (vendorUdfs.length > 1 ) {
					frm.Vendor_vendUdf5.value = "";
					for (var i=0; i < frm.elements.item("as_vendorUdf5").length; i++){
						if (frm.as_vendorUdf5[i].checked) {
							frm.Vendor_vendUdf5.value = frm.Vendor_vendUdf5.value + frm.as_vendorUdf5[i].value;
						}
					}
				} else {
					if (frm.as_vendorUdf5.checked) {
						frm.Vendor_vendUdf5.value = frm.as_vendorUdf5.value;
					}
				}
			}
		}
	}

	function setVendorUdf8() {
		if (frm.Vendor_vendUdf8) {
			var vendorUdfs = frm.elements.item("as_vendorUdf8");
			if (vendorUdfs != undefined) {
				if (vendorUdfs.length > 1 ) {
					frm.Vendor_vendUdf8.value = "";
					for (var i=0; i < frm.elements.item("as_vendorUdf8").length; i++){
						if (frm.as_vendorUdf8[i].checked) {
							frm.Vendor_vendUdf8.value = frm.Vendor_vendUdf8.value + frm.as_vendorUdf8[i].value;
						}
					}
				} else {
					if (frm.as_vendorUdf8.checked) {
						frm.Vendor_vendUdf8.value = frm.as_vendorUdf8.value;
					}
				}
			}
		}
	}

	function setDigitalSig() {
		if (frm.Vendor_digitalSig) {
			if (frm.elements.item("as_digitalSig").length > 1 ) {
				frm.Vendor_digitalSig.value = "";
				for (var i=0; i < frm.elements.item("as_digitalSig").length; i++){
					if (frm.as_digitalSig[i].checked) {
						frm.Vendor_digitalSig.value = frm.Vendor_digitalSig.value + frm.as_digitalSig[i].value;
					}
				}
			} else {
				if (frm.as_digitalSig.checked) {
					frm.Vendor_digitalSig.value = frm.as_digitalSig.value;
				}
			}
		}
	}

	function uploadDocs() {
		popupParameters = "VendorDocument_icRfqHeader=0;VendorDocument_vendorId=<%=user.getVendorId()%>";
		doSubmitToLookup('/supplierportal/user/supplier_attachments.jsp', 'VendorDocumentRetrieveByVendor', 'width=700', 'height=350');
	}

	function submitProfileInformation() {
		doSubmit('/supplierportal/user/profile_complete.jsp', 'VendorUpdateByEmail');
	}



function validateProfileInformation(validationHandler) {
	var digitalSigs = frm.elements.item("as_digitalSig");
	var digitialSigSet = true;
	if (digitalSigs != undefined) {
		if (digitalSigs.length > 1 ) {
			for (var i=0; i < frm.elements.item("as_digitalSig").length; i++){
				if (!frm.as_digitalSig[i].checked) {
					digitialSigSet = false;
				}
			}
		} else {
			if (!frm.as_digitalSig.checked) {
				digitialSigSet = false;
			}
		}
	}
	if (!digitialSigSet && certificationCheckRequired) {
		alert("You must 'CERTIFY ALL THE INFORMATION PROVIDED ON THIS FORM TO BE TRUE AND ACCURATE' before submitting.");
		return false;
	}

	setBusinessType();
	setVendorClass();
	setOwnershipType();
	setDiverseCertOrgs();
	setVendorUdf1();
	setVendorUdf2();
	setVendorUdf3();
	setVendorUdf4();
	setVendorUdf5();
	setVendorUdf8();
	setDigitalSig();
	setCommodityCodes();

	frm.failurePage.value = "/supplierportal/system/error_popup.jsp";
	if (doSubmitToNewTarget('user/profile_validation.jsp', validationHandler, 'lookup_window')) {
		hideAreaWithBlock('submit_link');
	}
	frm.failurePage.value = "/system/error.jsp";
}


function doSubmitToNewTarget(page, handlerList, target) {
	setupHandlers(handlerList);

	frm.successPage.value = page;

	if (validateForm()) {
		var dummyFields = document.getElementById("dummyFields");
		var dummyFieldsHTML = "";
		if (dummyFields != null && dummyFields != undefined) {
			dummyFieldsHTML = dummyFields.innerHTML;
		}
//		resetDisabledFlds();
		resetDummyFields();

		frm.target = target;
		frm.submit();

		if (dummyFields != null && dummyFields != undefined) {
			dummyFields.innerHTML = dummyFieldsHTML;
		}
		return true;
	} else {
		return false;
	}
}

	function browseState(fld)
	{
		popupParameters = popupParameters + "colname=StdTable_id_tableType;operator==;filter_txt=STAT;logicalOperator=AND;originalFilter=Y;sort=N;"
		browseLookup(fld, "stdtable");
	}

	function defaultGeneralInfo(fld)
	{
		if (fld.checked)
		{
			if (frm.Vendor_vendorName && frm.RemitToAddress_addressLine1)
				frm.RemitToAddress_addressLine1.value = frm.Vendor_vendorName.value;
			if (frm.Address_addressLine2 && frm.RemitToAddress_addressLine2)
				frm.RemitToAddress_addressLine2.value = frm.Address_addressLine2.value;
			if (frm.Address_addressLine3 && frm.RemitToAddress_addressLine3)
				frm.RemitToAddress_addressLine3.value = frm.Address_addressLine3.value;
			if (frm.Address_addressLine4 && frm.RemitToAddress_addressLine4)
				frm.RemitToAddress_addressLine4.value = frm.Address_addressLine4.value;
			if (frm.Address_addressCity && frm.RemitToAddress_addressCity)
				frm.RemitToAddress_addressCity.value = frm.Address_addressCity.value;
			if (frm.Address_addressState && frm.RemitToAddress_addressState)
				frm.RemitToAddress_addressState.value = frm.Address_addressState.value;
			if (frm.Address_addressZipCode && frm.RemitToAddress_addressZip)
				frm.RemitToAddress_addressZip.value = frm.Address_addressZipCode.value;
			if (frm.Address_addressCountry && frm.RemitToAddress_addressCountry)
				frm.RemitToAddress_addressCountry.value = frm.Address_addressCountry.value;
		}
		else
		{
			if (frm.RemitToAddress_addressLine1)
				frm.RemitToAddress_addressLine1.value = "";
			if (frm.RemitToAddress_addressLine2)
				frm.RemitToAddress_addressLine2.value = "";
			if (frm.RemitToAddress_addressLine3)
				frm.RemitToAddress_addressLine3.value = "";
			if (frm.RemitToAddress_addressLine4)
				frm.RemitToAddress_addressLine4.value = "";
			if (frm.RemitToAddress_addressCity)
				frm.RemitToAddress_addressCity.value = "";
			if (frm.RemitToAddress_addressState)
				frm.RemitToAddress_addressState.value = "";
			if (frm.RemitToAddress_addressZip)
				frm.RemitToAddress_addressZip.value = "";
			if (frm.RemitToAddress_addressCountry)
				frm.RemitToAddress_addressCountry.value = "";
		}
	}

//-->
</script>

<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
