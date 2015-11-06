<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.common.utility.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%
	String	vendorId = (String) request.getAttribute("Vendor_vendorId");
	String	vendorName = (String) request.getAttribute("Vendor_vendorName");
	String	contactType = (String) request.getAttribute("Contact_contactType");
	String	contactCode = (String) request.getAttribute("Contact_contactCode");
	String	contactName = (String) request.getAttribute("contactName");
	String icPoHeader = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_icPoHeader"));  //used for temp vendor add from PO process
	boolean newAddress = false;

	contactType = HiltonUtility.ckNull(contactType);
	contactName = HiltonUtility.ckNull(contactName);
	vendorId = HiltonUtility.ckNull(vendorId);

	Address address = (Address) request.getAttribute("address");
	AddressPK addressPK;

	if (address == null)
	{
		newAddress = true;
		address = new Address();
		addressPK = new AddressPK();

		addressPK.setAddressCode((String) request.getAttribute("Address_addressCode"));
		addressPK.setAddressType(vendorId);
		address.setComp_id(addressPK);
		address.setAddressLine1(vendorName);

		if (HiltonUtility.ckNull(addressPK.getAddressCode()).equals("REMITTO")) {
			address.setAddrFld17("Y");
		} else {
			address.setAddrFld16("Y");
		}
	}
	else {
		addressPK = (AddressPK) address.getComp_id();
	}
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_audit_trail = propertiesManager.getProperty("AUDITTRAIL", "SUPPLIERMANAGEMENT", "N");
%>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<tsa:hidden name="Vendor_vendorId" value="<%=vendorId%>"/>
<tsa:hidden name="Vendor_vendorName" value="<%=vendorName%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=vendorId%>"/>
<tsa:hidden name="Contact_vendorId" value="<%=vendorId%>"/>
<tsa:hidden name="Contact_contactCode" value="<%=contactCode%>"/>
<tsa:hidden name="Contact_contactType" value="<%=contactType%>"/>
<tsa:hidden name="Contact_addressCode" value="<%=request.getAttribute(\"Contact_addressCode\")%>"/>
<tsa:hidden name="Address_addressCode" value="<%=request.getAttribute(\"Address_addressCode\")%>"/>
<tsa:hidden name="Address_addressType" value="<%=addressPK.getAddressType()%>"/>
<tsa:hidden name="StdTable_tableType" value="VSBA"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<% if (!newAddress) { %>
<tsa:hidden name="auditSaveAdd" value="<%=s_audit_trail%>"/>
<% } %>
<tsa:hidden name="changeFields" value="N"/>
<tsa:hidden name="Vendor_lastChange" value=""/>
<tsa:hidden name="Vendor_lastChangedBy" value=""/>
<tsa:hidden name="Vendor_apBatchid" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addressInformation", "Address Information")%></div>
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
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=1 cellspacing=0 width=680px>
<tr>
	<td colspan=4 align=center>
<%	if (HiltonUtility.ckNull(addressPK.getAddressCode()).equals("DEFAULT")) {%>
		<font class=error><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "default", "Default")%></font>
<%	} else if (HiltonUtility.ckNull(addressPK.getAddressCode()).equals("REMITTO")) {%>
		<font class=error><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "remitTo", "Remit To")%></font>
<%	}%>
		<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addresssforSupplier", "Address for Supplier")%> '<%=addressPK.getAddressType()%>'</b>
<%	if (!HiltonUtility.isEmpty(contactName)) {%>
		<b> (<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "contact", "Contact")%> '<%=contactName%>')</b>
<%	}%>
	</td>
</tr>
<tr><td colspan=4><br></td></tr>
<tr <%=HtmlWriter.isVisible(oid, "sup-addressLine1")%>>
	<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-addressLine1", "Name / Address", true)%>:&nbsp;</td>
	<td colspan=3>
		<input type=text name="Address_addressLine1" tabindex=1 size=40 maxlength=40 value="<%=HiltonUtility.ckNull(address.getAddressLine1())%>">
	</td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "sup-addressLine2")%>>
	<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-addressLine2", "Address Line 1", true)%>:&nbsp;</td>
	<td colspan=3>
		<input type=text name="Address_addressLine2" tabindex=2 size=40 maxlength=40 value="<%=HiltonUtility.ckNull(address.getAddressLine2())%>">
	</td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "sup-addressLine3")%>>
	<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-addressLine3", "Address Line 2", true)%>:&nbsp;</td>
	<td colspan=3>
		<input type=text name="Address_addressLine3" tabindex=3 size=40 maxlength=40 value="<%=HiltonUtility.ckNull(address.getAddressLine3())%>">
	</td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "sup-addressLine4")%>>
	<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-addressLine4", "Address Line 3", true)%>:&nbsp;</td>
	<td colspan=3>
		<input type=text name="Address_addressLine4" tabindex=4 size=40 maxlength=40 value="<%=HiltonUtility.ckNull(address.getAddressLine4())%>">
	</td>
</tr>
<tr>
	<td <%=HtmlWriter.isVisible(oid, "sup-city")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-city", "City", true)%>:&nbsp;</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-city")%>>
		<input type=text name="Address_city" tabindex=5 size=25 maxlength=30 value="<%=HiltonUtility.ckNull(address.getCity())%>"></td>
	<td <%=HtmlWriter.isVisible(oid, "sup-state")%> align=right><a href="javascript: browseState(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-state", "State", true)%></a>:&nbsp;</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-state")%>>
		<input type=text name="Address_state" tabindex=6 size=5 maxlength=15 value="<%=HiltonUtility.ckNull(address.getState())%>" onChange="upperCase(this);">
	</td>
</tr>
<tr>
	<td <%=HtmlWriter.isVisible(oid, "sup-zip")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-zip", "Postal Code", true)%>:&nbsp;</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-zip")%>>
		<input type=text name="Address_postalCode" tabindex=7 size=15 maxlength=15 value="<%=HiltonUtility.ckNull(address.getPostalCode())%>"></td>
	<td <%=HtmlWriter.isVisible(oid, "sup-country")%> align=right><a href="javascript: browseLookup('Address_country','country'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-country", "Country", true)%>:</a>&nbsp;</td>

	<%	if (HiltonUtility.ckNull(address.getCountry()).length()>0) {%>
	<td <%=HtmlWriter.isVisible(oid, "sup-country")%>><input type=text name="Address_country" tabindex=8 size=20 maxlength=25 value="<%=HiltonUtility.ckNull(address.getCountry())%>" onChange="upperCase(this);">
	</td>
	<%	} else {%>
	<td <%=HtmlWriter.isVisible(oid, "sup-country")%>><input type=text name="Address_country" tabindex=8 size=20 maxlength=25 value="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-defaultCountry", "")%>" onChange="upperCase(this);">
	</td>
	<%	}%>

</tr>
<tr <%=HtmlWriter.isVisible(oid, "sup-status")%>>
	<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-status", "Status", true)%>:&nbsp;</td>
	<td colspan=3>
		<select name="Address_status" tabindex=9>
<% if (!(HiltonUtility.isEmpty(address.getStatus()))) { %>
			<option value="01" <% if ((address.getStatus()).indexOf("01")>= 0){ out.println("SELECTED"); }%>>Temporary</option>
			<option value="02" <% if ((address.getStatus()).indexOf("02")>= 0){ out.println("SELECTED"); }%>>Permanent</option>
			<option value="03" <% if ((address.getStatus()).indexOf("03")>= 0){ out.println("SELECTED"); }%>>Inactive</option>
<% } else { %>
			<option value="01" >Temporary</option>
			<option value="02" SELECTED>Permanent</option>
			<option value="03" >Inactive</option>
<% } %>
		</select>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf10")%> align=right width=35%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf10", "UDF 10", true)%>:&nbsp;</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf10")%> width=5%>
		<input type=text name="Address_addrFld10" tabindex=10 size=25 maxlength=15 value="<%=HiltonUtility.ckNull(address.getAddrFld10())%>">
	</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf13")%> align=right width=25%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf13", "UDF 13", true)%>:&nbsp;</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf13")%> width=35%>
		<input type=text name="Address_addrFld13" tabindex=11 size=25 maxlength=15 value="<%=HiltonUtility.ckNull(address.getAddrFld13())%>">
	</td>
</tr>
<tr>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf11")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf11", "UDF 11", true)%>:&nbsp;</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf11")%>>
		<input type=text name="Address_addrFld11" tabindex=12 size=25 maxlength=15 value="<%=HiltonUtility.ckNull(address.getAddrFld11())%>">
	</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf14")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf14", "UDF 14", true)%>:&nbsp;</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf14")%>>
		<input type=text name="Address_addrFld14" tabindex=13 size=25 maxlength=15 value="<%=HiltonUtility.ckNull(address.getAddrFld14())%>">
	</td>
</tr>
<tr>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf12")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf12", "UDF 12", true)%>:&nbsp;</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf12")%>>
		<input type=text name="Address_addrFld12" tabindex=14 size=25 maxlength=15 value="<%=HiltonUtility.ckNull(address.getAddrFld12())%>">
	</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf15")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf15", "UDF 15", true)%>:&nbsp;</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf15")%>>
		<input type=text name="Address_addrFld15" tabindex=15 size=25 maxlength=15 value="<%=HiltonUtility.ckNull(address.getAddrFld15())%>">
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf16")%> align=right width=35%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf16", "UDF 16", true)%>:&nbsp;</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf16")%> width=5%>
		<input type="checkbox" NAME="c_checkbox" <%if ((HiltonUtility.ckNull(address.getAddrFld16())).equals("Y")) { %>CHECKED <% } %> ONCLICK="setCheckbox(frm.Address_addrFld16, 0)" tabindex=16>
		<tsa:hidden name="Address_addrFld16" value="<%=HiltonUtility.ckNull(address.getAddrFld16())%>"/>
	</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf19")%> align=right width=25%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf19", "UDF 19", true)%>:&nbsp;</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf19")%> width=35%>
		<input type="checkbox" NAME="c_checkbox" <%if ((HiltonUtility.ckNull(address.getAddrFld19())).equals("Y")) { %>CHECKED <% } %> ONCLICK="setCheckbox(frm.Address_addrFld19, 1)" tabindex=19>
		<tsa:hidden name="Address_addrFld19" value="<%=HiltonUtility.ckNull(address.getAddrFld19())%>"/>
	</td>
</tr>
<tr>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf17")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf17", "UDF 17", true)%>:&nbsp;</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf17")%>>
		<input type="checkbox" NAME="c_checkbox" <%if ((HiltonUtility.ckNull(address.getAddrFld17())).equals("Y")) { %>CHECKED <% } %> ONCLICK="setCheckbox(frm.Address_addrFld17, 2)" tabindex=17>
		<tsa:hidden name="Address_addrFld17" value="<%=HiltonUtility.ckNull(address.getAddrFld17())%>"/>
	</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf20")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf20", "UDF 20", true)%>:&nbsp;</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf20")%>>
		<input type="checkbox" NAME="c_checkbox" <%if ((HiltonUtility.ckNull(address.getAddrFld20())).equals("Y")) { %>CHECKED <% } %> ONCLICK="setCheckbox(frm.Address_addrFld20, 3)" tabindex=20>
		<tsa:hidden name="Address_addrFld20" value="<%=HiltonUtility.ckNull(address.getAddrFld20())%>"/>
	</td>
</tr>
<tr>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf18")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-udf18", "UDF 18", true)%>:&nbsp;</td>
	<td <%=HtmlWriter.isVisible(oid, "sup-udf18")%>>
		<input type="checkbox" NAME="c_checkbox" <%if ((HiltonUtility.ckNull(address.getAddrFld18())).equals("Y")) { %>CHECKED <% } %> ONCLICK="setCheckbox(frm.Address_addrFld18, 4)" tabindex=18>
		<tsa:hidden name="Address_addrFld18"  value="<%=HiltonUtility.ckNull(address.getAddrFld18())%>"/>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (role.getAccessRights("VEND") > 1) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: saveAddress(); void(0);">Save</a></div></td>
<%	} %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/admin/supplier/supplier_contact_list.jsp', 'VendorRetrieveById;ContactAddressRetrieveBySupplier'); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var oid = '<%=oid %>';

	function thisLoad()
	{
		f_StartIt();
<%	if (role.getAccessRights("VEND") < 2 || (oid.equals("BLY07P") && (HiltonUtility.ckNull(addressPK.getAddressCode()).equals("REMITTO") || HiltonUtility.ckNull(addressPK.getAddressCode()).equals("DEFAULT")))) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function saveAddress() {
		var contactType = frm.Contact_contactType.value;
		var contactCode = frm.Contact_contactCode.value;
		var addressCode = frm.Address_addressCode.value;

		<% if (oid.equalsIgnoreCase("msg07p")) { %>
		setChangeFields();
		<% } %>

<%	if (newAddress) {%>
		if (contactCode.length > 0 && ((contactType != "DEFAULT" && addressCode == "DEFAULT") || addressCode.length == 0)) {
			frm.Address_addressCode.value = contactCode;
			frm.Contact_addressCode.value = contactCode;
			doSubmit('/admin/supplier/supplier_contact_list.jsp', 'AddressAdd;ContactUpdate;VendorRetrieveById;ContactAddressRetrieveBySupplier');
		} else {
			doSubmit('/admin/supplier/supplier_contact_list.jsp', 'AddressAdd;VendorRetrieveById;ContactAddressRetrieveBySupplier');
		}
<%	} else {%>
		if (contactType == "DEFAULT") {
			frm.Address_addressCode.value = "DEFAULT";
			doSubmit('/admin/supplier/supplier_contact_list.jsp', 'AddressUpdate;VendorRetrieveById;ContactAddressRetrieveBySupplier');

		} else {
			if (oid == 'MSG07P')
				doSubmit('/admin/supplier/supplier_contact_list.jsp', 'AddressUpdate;AddressRemittoCopyDefault;VendorRetrieveById;ContactAddressRetrieveBySupplier');
			else {
				doSubmit('/admin/supplier/supplier_contact_list.jsp', 'AddressUpdate;VendorRetrieveById;ContactAddressRetrieveBySupplier');
			}
		}
<%	}%>
	}

	//function browseStd(frmField, udf, multipleRows)
	function browseState()
	{
		popupParameters = popupParameters + "colname=StdTable_id_tableType;operator==;filter_txt=STAT;logicalOperator=AND;originalFilter=Y;sort=N;"
		browseLookup("Address_state", "stdtable");
	}

	function setChangeFields()
	{
		var addressLine1 = (frm.Address_addressLine1) ? ( (frm.Address_addressLine1.value != "<%=HiltonUtility.ckNull(address.getAddressLine1())%>" ) ? true : false ) : false;
		var addressLine2 = (frm.Address_addressLine2) ? ( (frm.Address_addressLine2.value != "<%=HiltonUtility.ckNull(address.getAddressLine2())%>" ) ? true : false ) : false;
		var addressLine3 = (frm.Address_addressLine3) ? ( (frm.Address_addressLine3.value != "<%=HiltonUtility.ckNull(address.getAddressLine3())%>" ) ? true : false ) : false;
		var addressLine4 = (frm.Address_addressLine4) ? ( (frm.Address_addressLine4.value != "<%=HiltonUtility.ckNull(address.getAddressLine4())%>" ) ? true : false ) : false;
		var city         = (frm.Address_city)         ? ( (frm.Address_city.value         != "<%=HiltonUtility.ckNull(address.getCity())%>" )         ? true : false ) : false;
		var state        = (frm.Address_state)        ? ( (frm.Address_state.value        != "<%=HiltonUtility.ckNull(address.getState())%>" )        ? true : false ) : false;
		var postalCode   = (frm.Address_postalCode)   ? ( (frm.Address_postalCode.value   != "<%=HiltonUtility.ckNull(address.getPostalCode())%>" )   ? true : false ) : false;
		var country      = (frm.Address_country)      ? ( (frm.Address_country.value      != "<%=HiltonUtility.ckNull(address.getCountry())%>" )      ? true : false ) : false;
		var status       = (frm.Address_status)       ? ( (frm.Address_status.value       != "<%=HiltonUtility.ckNull(address.getStatus())%>" )       ? true : false ) : false;
		var addrFld10    = (frm.Address_addrFld10)    ? ( (frm.Address_addrFld10.value    != "<%=HiltonUtility.ckNull(address.getAddrFld10())%>" )    ? true : false ) : false;
		var addrFld11    = (frm.Address_addrFld11)    ? ( (frm.Address_addrFld11.value    != "<%=HiltonUtility.ckNull(address.getAddrFld11())%>" )    ? true : false ) : false;
		var addrFld12    = (frm.Address_addrFld12)    ? ( (frm.Address_addrFld13.value    != "<%=HiltonUtility.ckNull(address.getAddrFld12())%>" )    ? true : false ) : false;
		var addrFld13    = (frm.Address_addrFld13)    ? ( (frm.Address_addrFld13.value    != "<%=HiltonUtility.ckNull(address.getAddrFld13())%>" )    ? true : false ) : false;
		var addrFld14    = (frm.Address_addrFld14)    ? ( (frm.Address_addrFld14.value    != "<%=HiltonUtility.ckNull(address.getAddrFld14())%>" )    ? true : false ) : false;
		var addrFld15    = (frm.Address_addrFld15)    ? ( (frm.Address_addrFld15.value    != "<%=HiltonUtility.ckNull(address.getAddrFld15())%>" )    ? true : false ) : false;
		var addrFld16    = (frm.Address_addrFld16)    ? ( (frm.Address_addrFld16.value    != "<%=HiltonUtility.ckNull(address.getAddrFld16())%>" )    ? true : false ) : false;
		var addrFld17    = (frm.Address_addrFld17)    ? ( (frm.Address_addrFld17.value    != "<%=HiltonUtility.ckNull(address.getAddrFld17())%>" )    ? true : false ) : false;
		var addrFld18    = (frm.Address_addrFld18)    ? ( (frm.Address_addrFld18.value    != "<%=HiltonUtility.ckNull(address.getAddrFld18())%>" )    ? true : false ) : false;
		var addrFld19    = (frm.Address_addrFld19)    ? ( (frm.Address_addrFld19.value    != "<%=HiltonUtility.ckNull(address.getAddrFld19())%>" )    ? true : false ) : false;
		var addrFld20    = (frm.Address_addrFld20)    ? ( (frm.Address_addrFld20.value    != "<%=HiltonUtility.ckNull(address.getAddrFld20())%>" )    ? true : false ) : false;

		if(addressLine1||addressLine2||addressLine3||addressLine4||city||state||postalCode||country||status||addrFld10||addrFld11||addrFld12||addrFld13||addrFld14||addrFld15||addrFld16||addrFld17||addrFld18||addrFld19||addrFld20)
		{
			frm.changeFields.value = "Y";
			frm.Vendor_lastChange.value = "<%=HiltonUtility.getFormattedDate(d_today, oid, userDateFormat)%>";
			frm.Vendor_lastChangedBy.value = "<%=user.getUserId()%>";
			frm.Vendor_apBatchid.value = "";
		}
	}
	
	function getFieldsJquery()
	{
		var fieldsToAuditJquery = $(":input:not([type=hidden])[name^='Address_'],:input:[name='Address_addrFld16'],:input:[name='Address_addrFld17'],:input:[name='Address_addrFld18'],:input:[name='Address_addrFld19'],:input:[name='Address_addrFld20']");
		return fieldsToAuditJquery;
    }

	function setAuditTables()
	{
		frm.auditTables.value = "Address";
	}
	function getFields(el)
	{
		if( (el.type != "hidden" && el.name.indexOf("Address_") > -1) ||
			(el.name == "Address_addrFld16") ||
			(el.name == "Address_addrFld17") ||
			(el.name == "Address_addrFld18") ||
			(el.name == "Address_addrFld19") ||
			(el.name == "Address_addrFld20"))
		{
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
    }
    function buildAuditIc()
	{
		return frm.Vendor_vendorId.value;
	}
	function viewAuditTrail(vendorId)
	{
		popupParameters = popupParameters + "browseName=audittrail";
		popupParameters = popupParameters + ";colname=AuditRecord_entity1;operator==;filter_txt=" + vendorId + ";logicalOperator=AND;originalFilter=N;sort=N;"
		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}

// End Hide script -->
</SCRIPT>