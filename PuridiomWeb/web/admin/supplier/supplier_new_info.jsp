<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dates.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dynamicTables.js"></SCRIPT>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_date_format = propertiesManager.getProperty("MISC", "DateFormat", "MM-dd-yyyy");
	String save_supplier = null;
	save_supplier = (String)request.getAttribute("save_suplier");

	Vendor vendor = (Vendor) request.getAttribute("vendor");
	boolean newVendor = false;

	if (vendor == null) {
		vendor = new Vendor();
		vendor.setDateEntered(d_today);
		vendor.setLastChange(d_today);
		vendor.setLastActive(d_today);
		vendor.setOwner(uid);
//		vendor.setFobId(propertiesManager.getProperty("VENDOR DEFAULTS","FobId",""));

		Map supplierDefaults = PropertiesManager.getInstance(oid).getSection("VENDOR DEFAULTS");
		vendor.setCurrencyCode((String)supplierDefaults.get("CURRENCYCODE"));
		vendor.setInspectionReqd((String)supplierDefaults.get("INSPECTIONREQD"));
		vendor.setShipVia((String)supplierDefaults.get("SHIPVIA"));
		vendor.setVendTerms((String)supplierDefaults.get("VENDTERMS"));
		vendor.setFobId((String)supplierDefaults.get("FOBID"));
		vendor.setPrintFaxCode((String)supplierDefaults.get("PRINTFAXCODE"));
		vendor.setVendUdf1((String)supplierDefaults.get("VENDUDF1"));
		vendor.setVendUdf2((String)supplierDefaults.get("VENDUDF2"));
		vendor.setVendUdf3((String)supplierDefaults.get("VENDUDF3"));
		vendor.setVendUdf4((String)supplierDefaults.get("VENDUDF4"));
		vendor.setVendUdf5((String)supplierDefaults.get("VENDUDF5"));
		vendor.setVendUdf6((String)supplierDefaults.get("VENDUDF6"));
		vendor.setVendUdf7((String)supplierDefaults.get("VENDUDF7"));
		vendor.setVendUdf8((String)supplierDefaults.get("VENDUDF8"));
		vendor.setVendUdf9((String)supplierDefaults.get("VENDUDF9"));
		vendor.setVendUdf10((String)supplierDefaults.get("VENDUDF10"));

		if (oid.equalsIgnoreCase("MSG07P")) {
			vendor.setStatus("05");
		}

		newVendor = true;
	} else {
		String	newVendorString = (String) request.getAttribute("newVendor");

		newVendorString = HiltonUtility.ckNull(newVendorString);
		if (newVendorString.equals("Y")) {
			newVendor = true;
		}
	}

	UserProfile owner = UserManager.getInstance().getUser(oid, vendor.getOwner());
	String vendorId = vendor.getVendorId();
	String vendorName = vendor.getVendorName();
	String printPrices = vendor.getPrintPrices();
	String s_title = vendorName;
	String	s_current_process = "SUPPLIER_INFO";
	String	s_current_page = "/admin/supplier/supplier_info.jsp";
	String	s_current_method = "VendorUpdate;VendorValidate";
	String	s_current_process_method = "";
	if (newVendor)
	{
		s_current_method = "VendorAdd;VendorValidate";
		s_title = "New Supplier Information";
		printPrices = "Y";	//print prices should be set to Y by default
	}
	String icPoHeader = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_icPoHeader"));
	String tempVendorId = HiltonUtility.ckNull((String) request.getAttribute("tempVendorId"));
	if (HiltonUtility.isEmpty(vendorName))
	{
		String tempVendorName = HiltonUtility.ckNull((String) request.getAttribute("tempVendorName"));
		if (!HiltonUtility.isEmpty(tempVendorName))
		{
			vendorName = tempVendorName;
		}
	}

	String	errorMsg = (String) request.getAttribute("errorMsg");
	String deleteVendorErrorMsg = 	HiltonUtility.ckNull((String)request.getAttribute("deleteVendorErrorMsg"));
	String s_audit_trail = propertiesManager.getProperty("AUDITTRAIL", "SUPPLIERMANAGEMENT", "N");

	String vendorEINSize = "10";

	if (oid.equalsIgnoreCase("MSG07P")) {
		vendorEINSize = "11";
	}
	if (oid.equalsIgnoreCase("WPC08P")) {
		vendorEINSize = "15";
	}

	/*if (vendor.isVendorValidated()) {
		vendor.setStatus("02");
	} else  {
		vendor.setStatus("05");
	}*/
	BrowseObject browseObject = (BrowseObject) request.getAttribute("browseObject");
	BrowseColumn browseColumns[] = browseObject.getBrowseColumns();
	String s_icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String s_rfqNumber = (String) request.getAttribute("RfqHeader_rfqNumber");
	String s_rfqAmendment = (String) request.getAttribute("RfqHeader_rfqAmendment");
	String s_rfqStatus = (String) request.getAttribute("RfqHeader_status");
	String s_rfqType = (String) request.getAttribute("RfqHeader_rfqType");
	String s_fiscalYear = (String) request.getAttribute("RfqHeader_fiscalYear");
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="${esapi:encodeForHTMLAttribute(RfqHeader_rfqType)}"/>
<tsa:hidden name="RfqHeader_fiscalYear" value="<%=s_fiscalYear%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=vendorId%>"/>
<tsa:hidden name="VendorAccount_vendorId" value="<%=vendorId%>"/>
<tsa:hidden name="Contact_vendorId" value="<%=vendorId%>"/>
<tsa:hidden name="Address_addressType" value="<%=vendorId%>"/>
<tsa:hidden name="Address_addressCode" value=""/>
<tsa:hidden name="StdTable_tableType" value="VSBA"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="pageFrom" value="supplier"/>
<tsa:hidden name="currentPage" value="/admin/supplier/supplier_info.jsp"/>
<tsa:hidden name="deleteVendorFailurePage" value="/admin/supplier/supplier_info.jsp"/>
<tsa:hidden name="VendorDocument_icRfqHeader" value="0"/>
<tsa:hidden name="VendorDocument_vendorId" value="<%=vendorId%>"/>
<tsa:hidden name="allowEdit" value="Y">
<tsa:hidden name="returnPage" value="/admin/supplier/supplier_info.jsp"/>
<tsa:hidden name="returnHandler" value="VendorRetrieveById"/>
<tsa:hidden name="tempVendorId" value="<%=tempVendorId%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="fromSave" value="Y"/>
<% if (!newVendor) { %>
<tsa:hidden name="auditSaveAdd" value="<%=s_audit_trail%>"/>
<% } %>
<tsa:hidden name="validateEIN" value=""/>
<tsa:hidden name="Vendor_lastChangedBy" value="<%=vendor.getLastChangedBy()%>"/>
<tsa:hidden name="Catalog_status" value=""/>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=s_title%></div>
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

<%	if ( !newVendor ) {	%>
	<td valign=bottom align=middle>
		<table border=0 cellpadding=2 cellspacing=0>
		<tr>
<%		if (vendor.isVendorValidated()) {	%>
			<td align="center">&nbsp;<img src="<%=contextPath%>/images/checkmark.gif" border=0>&nbsp;Validation Passed</td>
<%		} else if (role.getAccessRights("VEND") > 1) {	%>
			 <td align=right>&nbsp;<a href="javascript: validateVendor(); void(0);"><img src="<%=contextPath%>/images/alert.gif" border=0></a></td>
			 <td><a href="javascript: validateVendor(); void(0);">Validate Information</a></td>
<%		}	%>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
		</div>
	</td>

<%		if (role.getAccessRights("VEND") == 99) {	%>
	<td valign=bottom align=middle>
		<table border=0 cellpadding=2 cellspacing=0>
		<tr height="20px">
			 <td align=right valign=top><img src="<%=contextPath%>/images/delete.gif" border=0>&nbsp;<a href="javascript: doSubmit('admin/admin_menu.jsp', 'VendorDelete'); void(0);">Delete Supplier</a></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
		</div>
	</td>
<%		}
		}	%>



</tr>
</table>

<br>
<%@ include file="/admin/admin_info.jsp" %>
<br>

<%	if (!HiltonUtility.isEmpty(errorMsg)) {%>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
	<td width=100% align=center class=error><%=errorMsg%></td>
</tr>
</table>
<%	}%>

<br>

<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=2 cellspacing=0 width=100% align="center">
		<tr>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-supplier", "Supplier", true)%>:</td>
<%	if (newVendor) {	%>
			<td nowrap>
				<input type=text name="Vendor_vendorId" value="<%=vendorId%>" maxLength=15 tabIndex=1 onchange="alphaNumericCheck(this);">
				<img src="<%=contextPath%>/images/question_blue.gif" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-idhelp", "Please enter a unique Supplier ID")%>" valign=bottom border=0>
			</td>
<%	} else {	%>
			<td nowrap><b><%=vendorId%></b><tsa:hidden name="Vendor_vendorId" value="<%=vendorId%>"/></td>
<%	}	%>
		</tr>
		<tr>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "supplierName", "Name", true)%>: </td>
			<td colspan="3"><input type=text name="Vendor_vendorName" value="<%=vendorName%>" size=50 maxLength=40 tabIndex=2></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "dunsNumber")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dunsNumber", "DUNS Number", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "dunsNumber")%>><input type=text name="Vendor_vendorDuns" tabindex=3 size=15 maxlength=11 value="<%=vendor.getVendorDuns()%>" onchange="upperCase(this);"   ></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "einNumber")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "einNumber", "EIN", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "einNumber")%>><input type=text name="Vendor_vendorEin" tabindex=4 size=15 maxlength="<%= vendorEINSize %>" value="<%=vendor.getVendorEin()%>" onchange="upperCase(this);setValidateEINNumber();"></td>

			<td <%=HtmlWriter.isVisible(oid, "form1099Required")%> nowrap align=right width="100%"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "form1099Required", "Form 1099 Required")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "form1099Required")%>><INPUT TYPE="checkbox" NAME="c_checkbox" <%if ((vendor.getVendor1099()).equals("Y")) { %>CHECKED <% } %> value="Y"  ONCLICK="setCheckbox(frm.Vendor_vendor1099, 0)" tabIndex=5>
				<tsa:hidden name="Vendor_vendor1099" maxlength=15 value="<%=vendor.getVendor1099()%>"/>
			</td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "sicCode")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sicCode", "SIC Code", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sicCode")%>>
				<input type=text name="Vendor_vendorSic" tabindex=6 size=15 maxlength=15 value="<%=vendor.getVendorSic()%>" onchange="upperCase(this);"   >
			</td>

			<td <%=HtmlWriter.isVisible(oid, "inspectionRequired")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inspectionRequired", "Inspection Required")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "inspectionRequired")%>><INPUT TYPE="checkbox" NAME="c_checkbox" <%if ((vendor.getInspectionReqd()).equals("Y")) { %>CHECKED <% } %> value="Y"  ONCLICK="setCheckbox(frm.Vendor_inspectionReqd, 1)" tabIndex=7>
				<tsa:hidden name="Vendor_inspectionReqd" maxlength=15 value="<%=vendor.getInspectionReqd()%>"/>
			</td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "apReference")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "apReference", "AP Reference", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "apReference")%> colspan="3"><input type=text name="Vendor_apReference" tabindex=8 size=15 maxlength=15 value="<%=vendor.getApReference()%>" onchange="upperCase(this);"   ></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "ourAccountNumber")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ourAccountNumber", "Our Account Number", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "ourAccountNumber")%> width=150px colspan="3"><input type=text name="Vendor_vendorAccount" tabindex=9 size=15 maxlength=15 value="<%=vendor.getVendorAccount()%>"></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "sup-shipVia")%> align=right><a href="javascript: browseLookup('Vendor_shipVia', 'shipvia'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-shipVia", "Ship Via", true)%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-shipVia")%> colspan="3">
			<% if( save_supplier != null ) {
			%>
				<input type=text name="Vendor_shipVia" tabindex=10 size=15 maxlength=15 value="<%=propertiesManager.getProperty("VENDOR DEFAULTS","ShipVia","")%>" onchange="upperCase(this);"   >
			<% }
			   else {%>
				<input type=text name="Vendor_shipVia" tabindex=11 size=15 maxlength=15 value="<%=vendor.getShipVia()%>" onchange="upperCase(this);"   >
			<%} %>
			</td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "sup-fob")%> align=right><a href="javascript: browseStd('Vendor_fobId', 'FOB'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-fob", "FOB", true)%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-fob")%>>
				<input type=text name="Vendor_fobId" tabindex=12 size=15 maxlength=15 value="<%=vendor.getFobId()%>" onchange="upperCase(this);"   >
			</td>

			<td <%=HtmlWriter.isVisible(oid, "sup-paymentTerms")%> align=right><a href="javascript: browseLookup('Vendor_vendTerms', 'payment-terms'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-paymentTerms", "Terms", true)%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-paymentTerms")%> width="125px">
			<% if( save_supplier != null ) {
			%>
			<input type=text name="Vendor_vendTerms" tabindex=13 size=15 maxlength=15 value="<%=propertiesManager.getProperty("VENDOR DEFAULTS","VendTerms","")%>" onchange="upperCase(this);" >
			<% }
			   else {%>
			<input type=text name="Vendor_vendTerms" tabindex=14 size=15 maxlength=15 value="<%=vendor.getVendTerms()%>" onchange="upperCase(this);" >
			<%} %>
			</td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "sup-currency")%> align=right><a href="javascript: browseLookup('Vendor_currencyCode', 'curr_code'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-currency", "Currency", true)%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-currency")%>><input type=text name="Vendor_currencyCode" tabindex=15 size=15 maxlength=15 value="<%=vendor.getCurrencyCode()%>" onchange="upperCase(this);"   ></td>

			<td <%=HtmlWriter.isVisible(oid, "sup-taxCode")%> align=right><a href="javascript: browseLookup('Vendor_taxCode', 'taxcode'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-taxCode", "Tax Code", true)%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-taxCode")%>><input type=text name="Vendor_taxCode" tabindex=16 size=15 maxlength=15 value="<%=vendor.getTaxCode()%>" onchange="upperCase(this);"   ></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "orderLeadtime")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "orderLeadtime", "Order Leadtime", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "orderLeadtime")%>>
				<input type=text name="Vendor_leadDays" tabindex=17 size=15 maxlength=15 value="<%=vendor.getLeadDays()%>" onchange="upperCase(this);"   >
			</td>

			<td <%=HtmlWriter.isVisible(oid, "printPOPrices")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "printPOPrices", "Print PO Prices")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "printPOPrices")%>><INPUT TYPE="checkbox" NAME="c_checkbox" <%if (printPrices.equals("Y")) { %>CHECKED <% } %> value="Y"  ONCLICK="setCheckbox(frm.Vendor_printPrices, 2)"  tabIndex=18>
				<tsa:hidden name="Vendor_printPrices" size=15 maxlength=15 value="<%=printPrices%>"/>
			</td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "sup-poSendMethod")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-poSendMethod", "PO Send Method")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-poSendMethod")%>>
			<%if(vendor.getPrintFaxCode() != null)
			{%>
				<select name="Vendor_printFaxCode" tabIndex=19>
					<option value="P" <% if ((vendor.getPrintFaxCode()).indexOf("P")>= 0){ out.println("SELECTED"); }%>>Print PO</option>
					<option value="F" <% if ((vendor.getPrintFaxCode()).indexOf("F")>= 0){ out.println("SELECTED"); }%>>Fax PO</option>
					<option value="E" <% if ((vendor.getPrintFaxCode()).indexOf("E")>= 0){ out.println("SELECTED"); }%>>EDI PO</option>
					<option value="M" <% if ((vendor.getPrintFaxCode()).indexOf("M")>= 0){ out.println("SELECTED"); }%>>E-mail</option>
					<option value="X" <% if ((vendor.getPrintFaxCode()).indexOf("X")>= 0){ out.println("SELECTED"); }%>>XML Order</option>
				</select>
			<%}
			else
			{%>
				<select name="Vendor_printFaxCode" tabIndex=20>
					<option value="P">Print PO</option>
					<option value="F">Fax PO</option>
					<option value="E">EDI PO</option>
					<option value="M">E-mail</option>
					<option value="X">XML Order</option>
				</select>
			<%}%>
			</td>

			<td <%=HtmlWriter.isVisible(oid, "printCopies")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "printCopies", "Print Copies", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "printCopies")%>>
				<input type="text" name="Vendor_printCopies" tabindex=21 size=5 value="<%=vendor.getPoCopies()%>" onchange="upperCase(this);"   >
			</td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "sup-faxNumber")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-faxNumber", "Fax Number", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-faxNumber")%> colspan="3">
				<input type=text name="Vendor_faxNumber" tabindex=22 size=15 maxlength=15 value="<%=vendor.getFaxNumber()%>" onchange="upperCase(this);"   >
			</td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "sup-emailAddress")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "emailAddress", "Email Address", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-emailAddress")%> colspan="3"><input type=text name="Vendor_emailAddress" tabindex=23 size=40 maxlength=40 value="<%=vendor.getEmailAddress()%>"></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "urlAddress")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "urlAddress", "URL Address", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "urlAddress")%> colspan="3"><input type=text name="Vendor_webAddress" tabindex=24 size=40 maxlength=60 value="<%=vendor.getWebAddress()%>"></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "ediVendor")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ediVendor", "EDI Vendor", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "ediVendor")%>>
				<input type=text name="Vendor_ediVendor" tabindex=25 size=15 maxlength=15 value="<%=vendor.getEdiVendor()%>" onchange="upperCase(this);"   >
			</td>
			<td <%=HtmlWriter.isVisible(oid, "ediAddress")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "eidAddress", "EDI Address", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "ediAddress")%>>
				<input type=text name="Vendor_ediAddress" tabindex=26 size=15 maxlength=15 value="<%=vendor.getEdiAddress()%>" onchange="upperCase(this);"   >
			</td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "yearsInBusiness")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "yearsInBusiness", "Years In Business", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "yearsInBusiness")%>>
				<input type=text name="Vendor_yearsInBusiness" tabindex=27 size=15 maxlength=15 value="<%=vendor.getYearsInBusiness()%>" onchange="upperCase(this);"   >
			</td>
			<td <%=HtmlWriter.isVisible(oid, "yearsAsSupplier")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "yearsAsSupplier", "Years As Supplier", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "yearsAsSupplier")%>>
				<input type=text name="Vendor_yearsAsVendor" tabindex=28 size=15 maxlength=15 value="<%=vendor.getYearsAsVendor()%>" onchange="upperCase(this);"   >
			</td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "sup-owner")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-owner", "Owner")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-owner")%>><input type=text name="Vendor_owner" tabindex=29 size=15 maxlength=15 value="<%=vendor.getOwner()%>" onchange="upperCase(this);" DISABLED></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-status")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-status", "Status")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-status")%>>
				<select name="Vendor_status" onchange="setStatusFields();" tabIndex=30 <%=(oid.equalsIgnoreCase("MSG07P") && ((vendor.getStatus()).indexOf("05")>= 0)) ? "DISABLED" : "" %>>
			<% if (!(HiltonUtility.isEmpty(vendor.getStatus()))) { %>
					<% if (oid.equalsIgnoreCase("MSG07P")) { %>
					<option value="05" <% if ((vendor.getStatus()).indexOf("05")>= 0){ out.println("SELECTED"); }%>>Incomplete</option>
					<% }%>
					<option value="01" <% if ((vendor.getStatus()).indexOf("01")>= 0){ out.println("SELECTED"); }%>>Temporary</option>
					<option value="02" <% if ((vendor.getStatus()).indexOf("02")>= 0){ out.println("SELECTED"); }%>>Permanent</option>
					<option value="03" <% if ((vendor.getStatus()).indexOf("03")>= 0){ out.println("SELECTED"); }%>>Inactive</option>
					<option value="04" <% if ((vendor.getStatus()).indexOf("04")>= 0){ out.println("SELECTED"); }%>>RFQ Only</option>
					<option value="09" <% if ((vendor.getStatus()).indexOf("09")>= 0){ out.println("SELECTED"); }%>>On Hold</option>
			<% } else { %>
					<% if (oid.equalsIgnoreCase("MSG07P")) { %>
					<option value="05" >Incomplete</option>
					<% }%>
					<option value="01" >Temporary</option>
					<option value="02" SELECTED>Permanent</option>
					<option value="03" >Inactive</option>
					<option value="04" >RFQ Only</option>
					<option value="09" >On Hold</option>
			<% } %>
				</select>
			</td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "sup-owner_name")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-owner_name", "Owner Name")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-owner_name")%>><input type=text name="sup-owner_name" tabindex=29 size=15 maxlength=15 value="<%=owner.getDisplayName()%>" onchange="upperCase(this);" DISABLED></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "lastActive")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "lastActive", "Last Active")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "lastActive")%>><input type=text name="Vendor_lastActive" tabindex=31 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(vendor.getLastActive(), oid)%>" DISABLED></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-dateEntered")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-dateEntered", "Date Entered")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-dateEntered")%> nowrap width="130px"><input type=text name="Vendor_dateEntered" tabindex=32 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(vendor.getDateEntered(), oid)%>" DISABLED></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "lastChanged")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "lastChanged", "Last Changed")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "lastChanged")%>><input type=text name="Vendor_lastChange" tabindex=33 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(vendor.getLastChange(), oid)%>" DISABLED></td>
			<td <%=HtmlWriter.isVisible(oid, "dateExpires")%> align=right><div id="dateExpiresLabel"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-dateExpires", "Date Expires")%>:</div></td>
			<td <%=HtmlWriter.isVisible(oid, "dateExpires")%> nowrap width="130px">
				<div id="dateExpiresField"><input type=text name="Vendor_dateExpires" tabindex=34 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(vendor.getDateExpires(), oid)%>" onchange="checkDate(this);">&nbsp;<a href="javascript: show_calendar('Vendor_dateExpires', '<%=s_date_format%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a></div>
			</td>
		</tr>
		<% if (oid.equalsIgnoreCase("MSG07P")) { %>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "dateExported")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dateExported", "Date Exported")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "dateExported")%>><input type=text name="Vendor_dateExported" tabindex=35 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(vendor.getDateExported(), oid)%>" disabled></td>
			<td <%=HtmlWriter.isVisible(oid, "batchId")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "batchId", "Batch ID")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "batchId")%>><input type=text name="Vendor_apBatchid" tabindex=36 size=15 maxlength=15 value="<%=vendor.getApBatchid()%>" disabled></td>
		</tr>
		<% } %>
		</table>
	</td>
</tr>
</table>

<br>
<% if (!newVendor && s_audit_trail.equalsIgnoreCase("Y")) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
	<tr>
		<td align=center>
			<a href="javascript: viewAuditTrail('<%=vendorId%>');" title="Audit Trail"><img src="<%=contextPath%>/images/asset3.gif" border="0" alt="Audit Trail"></a>
			<a href="javascript: viewAuditTrail('<%=vendorId%>');" title="Audit Trail">Audit Trail</a>
		</td>
	</tr>
</table>
<% } %>
<br>
<tr>
	<table border=0 cellpadding=0 cellspacing=0 width=680 px>
		<td align=center><a tabindex=50 href="javascript: vendorSave(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	</table>
</tr>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	var status;
	setStatusFields();

	var menuText;
<%	if (newVendor) { %>
	menuText = "Add New Supplier";
	frm.browseName.value="supplierorders";

<%	} else { %>
	menuText = "Supplier <%=vendorId%>";


	currentpage = '/admin/supplier/supplier_validation.jsp';
	currentmethod = 'VendorUpdate';
	currentprocessmethod = 'VendorValidate';

<%	}%>

<%	if (deleteVendorErrorMsg.length() > 0)
		{ %>
			alert("<%=deleteVendorErrorMsg%>");
<%	} %>

	function thisLoad()
	{
		f_StartIt();
<%	if (role.getAccessRights("VEND") < 2 || oid.equals("BLY07P")) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
		frm.Catalog_status.value = frm.Vendor_status.value;
	}

	function setStatusFields()
	{
		status = frm.Vendor_status[frm.Vendor_status.selectedIndex].value;
		if ((status == "01") || (status == "03") || (status == "04") || (status == "09")){
			displayArea("dateExpiresLabel");
			displayArea("dateExpiresField");
		}
		else{
			hideArea("dateExpiresLabel");
			hideArea("dateExpiresField");
		}
		frm.Catalog_status.value = frm.Vendor_status.value;
	}

	function uploadDocs() {
			var newInputField = "<input type=hidden name='VendorDocument_icRfqHeader' value=0>" +
				"<input type=hidden name='VendorDocument_vendorId' value=<%=vendorId%>>" +
				"<input type=hidden name=allowEdit value=Y>" +
				"<input type=hidden name=returnPage value='/admin/supplier/supplier_info.jsp'>" +
				"<input type=hidden name=returnHandler value='VendorRetrieveById'>";
			setHiddenFields(newInputField);
		doSubmit('/admin/supplier/supplier_attachments.jsp', 'VendorUpdate;VendorDocumentRetrieveByVendor', 'width=700', 'height=350');
	}

	function viewSupplierOrders()
	{
			setOriginalFilter("PoHeader_vendorId", "=", '<%=vendorId%>');
			browse('supplierorders');
	}

		function validateForm()
		{
			if ( (frm.handler.value.indexOf("VendorAdd") >= 0) || (frm.handler.value.indexOf("VendorUpdate") >= 0) )
			{
				//frm.Vendor_status.value = "02";
				updateLastChanged ();
			}

			if (frm.handler.value.indexOf("VendorAdd") >= 0)
			{
				var alertMessage = "";
				var w;

				w = frm.Vendor_vendorId.value;
				if ( isEmpty( w ) )
					alertMessage += '<%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-supplier", "Supplier") %> is required.\n';
				w = frm.Vendor_vendorName.value;
				if ( isEmpty( w ) )
					alertMessage += '<%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "supplierName", "Name") %> is required.\n';

				if ( alertMessage.length > 0 ) {
							alert ( 'Please fix the following problems:\n\n'+alertMessage );
							return false;
				}
			}
			//  if user clicks on Purchase History step
			else if (frm.handler.value.indexOf("BrowseRetrieve") > 0)
			{
				setOriginalFilter("PoHeader_vendorId", "=", '<%=vendorId%>');
				var newInputField = "<tsa:hidden name='PurchaseHistory_vendorId' value=<%=vendorId%>/>";
				setHiddenFields(newInputField);
				frm.browseName.value = "supplierorders";
			}
			validateVendorEin();
			return true;
		}

		function validateVendor() {
			frm.fromSave.value = 'N';

			<% if (oid.equalsIgnoreCase("MSG07P")) { %>
			frm.Vendor_status.value = "05";
			<% } %>

			validateVendorEin();
			doSubmit('/admin/supplier/supplier_validation.jsp', 'VendorUpdate;VendorValidate');
		}

		function validateVendorEin()
		{
			<% if (oid.equalsIgnoreCase("MSG07P")) { %>

			if( frm.Vendor_vendUdf4.value == "INDIVIDUAL" ||
				frm.Vendor_vendUdf4.value == "PARTNER" ||
				frm.Vendor_vendUdf4.value == "SOLE" ||
				frm.Vendor_vendUdf4.value == "LLCORP" ||
				frm.Vendor_vendUdf4.value == "LLCOMPANY" ||
				frm.Vendor_vendUdf4.value == "CORP")
			{
				frm.validateEIN.value = "Y";
			}
			else
			{
				frm.validateEIN.value = "N";
			}
			<% } else { %>
			frm.validateEIN.value = "N";
			<% } %>
		}

		function upperCase (formField) {
		  var x = formField.value;
		  formField.value = x.toUpperCase();
		}

	function updateLastChanged ()
	{
		var vendor1099 = ('<%=vendor.getVendor1099()%>' != frm.Vendor_vendor1099.value)? true:false;
		var inspectionReqd = ('<%=vendor.getInspectionReqd()%>' != frm.Vendor_inspectionReqd.value)? true:false;
		var apReference = ('<%=vendor.getApReference()%>' != frm.Vendor_apReference.value)? true:false;
		var vendorAccount = ('<%=vendor.getVendorAccount()%>' != frm.Vendor_vendorAccount.value)? true:false;
		var shipVia = ('<%=vendor.getShipVia()%>' != frm.Vendor_shipVia.value)? true:false;
		var fobId = ('<%=vendor.getFobId()%>' != frm.Vendor_fobId.value)? true:false;
		var vendTerms = ('<%=vendor.getVendTerms()%>' != frm.Vendor_vendTerms.value)? true:false;
		var currencyCode = ('<%=vendor.getCurrencyCode()%>' != frm.Vendor_currencyCode.value)? true:false;
		var taxCode = ('<%=vendor.getTaxCode()%>' != frm.Vendor_taxCode.value)? true:false;
		var leadDays = ('<%=vendor.getLeadDays()%>' != frm.Vendor_leadDays.value)? true:false;
		var printPrices = ('<%=printPrices%>' != frm.Vendor_printPrices.value)? true:false;
		var printFaxCode = ('<%=vendor.getPrintFaxCode()%>' != frm.Vendor_printFaxCode.value)? true:false;
		var printCopies = ('<%=vendor.getPoCopies()%>' != frm.Vendor_printCopies.value)? true:false;
		var faxNumber = ('<%=vendor.getFaxNumber()%>' != frm.Vendor_faxNumber.value)? true:false;
		var emailAddress = ('<%=vendor.getEmailAddress()%>' != frm.Vendor_emailAddress.value)? true:false;
		var webAddress = ('<%=vendor.getWebAddress()%>' != frm.Vendor_webAddress.value)? true:false;
		var ediVendor = ('<%=vendor.getEdiVendor()%>' != frm.Vendor_ediVendor.value)? true:false;
		var ediAddress = ('<%=vendor.getEdiAddress()%>' != frm.Vendor_ediAddress.value)? true:false;
		var yearsInBusiness = ('<%=vendor.getYearsInBusiness()%>' != frm.Vendor_yearsInBusiness.value)? true:false;
		var yearsAsVendor = ('<%=vendor.getYearsAsVendor()%>' != frm.Vendor_yearsAsVendor.value)? true:false;
		var status = ('<%=vendor.getStatus()%>' != frm.Vendor_status.value)? true:false;
		var dateExpires = ('<%=HiltonUtility.getFormattedDate(vendor.getDateExpires(), oid)%>' != frm.Vendor_dateExpires.value)? true:false;

		if (vendor1099||inspectionReqd||apReference||vendorAccount||shipVia||fobId||vendTerms||currencyCode||taxCode
			||leadDays||printPrices||printFaxCode||printCopies||faxNumber||emailAddress||webAddress
			||ediVendor||ediAddress||yearsInBusiness||yearsAsVendor||status||dateExpires)
		{
			frm.Vendor_lastChange.value = '<%=HiltonUtility.getFormattedDate(d_today, oid)%>';
			frm.Vendor_lastChangedBy.value = '<%=user.getUserId()%>';
			if(frm.Vendor_apBatchid) { frm.Vendor_apBatchid.value = ""; }
		}

		if ('<%=oid%>' == 'MSG07P')
		{
			var notes = (frm.Vendor_notes_original.value != frm.Vendor_notes.value)? true:false;
			var vendUdf1 = ('<%=HiltonUtility.ckNull(vendor.getVendUdf1())%>' != frm.Vendor_vendUdf1.value)? true:false;
			var vendUdf2 = ('<%=HiltonUtility.ckNull(vendor.getVendUdf2())%>' != frm.Vendor_vendUdf2.value)? true:false;
			var vendUdf3 = ('<%=HiltonUtility.ckNull(vendor.getVendUdf3())%>' != frm.Vendor_vendUdf3.value)? true:false;
			var vendUdf4 = ('<%=HiltonUtility.ckNull(vendor.getVendUdf4())%>' != frm.Vendor_vendUdf4.value)? true:false;
			var vendUdf5 = ('<%=HiltonUtility.ckNull(vendor.getVendUdf5())%>' != frm.Vendor_vendUdf5.value)? true:false;
			var vendUdf6 = ('<%=HiltonUtility.ckNull(vendor.getVendUdf6())%>' != frm.Vendor_vendUdf6.value)? true:false;
			var vendUdf7 = ('<%=HiltonUtility.ckNull(vendor.getVendUdf7())%>' != frm.Vendor_vendUdf7.value)? true:false;
			var vendUdf8 = ('<%=HiltonUtility.ckNull(vendor.getVendUdf8())%>' != frm.Vendor_vendUdf8.value)? true:false;
			var vendUdf9 = ('<%=HiltonUtility.ckNull(vendor.getVendUdf9())%>' != frm.Vendor_vendUdf9.value)? true:false;
			var vendUdf10 = ('<%=HiltonUtility.ckNull(vendor.getVendUdf10())%>' != frm.Vendor_vendUdf10.value)? true:false;
			if (notes||vendUdf1||vendUdf2||vendUdf3||vendUdf4||vendUdf5||vendUdf6||vendUdf7||vendUdf8||vendUdf9||vendUdf10)
			{
				frm.Vendor_lastChange.value = '<%=HiltonUtility.getFormattedDate(d_today, oid)%>';
				frm.Vendor_lastChangedBy.value = '<%=user.getUserId()%>';
				if(frm.Vendor_apBatchid) { frm.Vendor_apBatchid.value = ""; }
			}
		}
	}

	function setValidateEINNumber()
	{
		var einNumber = frm.Vendor_vendorEin.value;
		var index_1 = 1;
		var index_2 = 2;
		var regExpSS = new RegExp('^\\d{3}-\\d{2}-\\d{4}$');
		var oid = '<%= oid %>';
		var errorMessage = '';
		var isValid = true;

		if (oid == 'MSG07P' || oid == 'WPC08P') {
			index_1 = 2;
			index_2 = 3;
		}

		if(einNumber.indexOf("-") == index_1 || einNumber.indexOf("-") == index_2)
		{
			var leftNumber  = einNumber.substring(0,einNumber.indexOf("-"));
			var rightNumber = einNumber.substring(einNumber.indexOf("-") + 1, einNumber.length);

			for(var i=0; i<leftNumber.length; i++)
			{
				if(isNaN(parseInt(leftNumber.charAt(i))))
				{
					isValid = false;
					break;
				}
			}
			for(var i=0; i<rightNumber.length; i++)
			{
				if(isNaN(parseInt(rightNumber.charAt(i))))
				{
					isValid = false;
					break;
				}
			}

			if (!isValid) {
				errorMessage = "This is not a valid Tax ID Number. Must have numbers!";
			}
		}
		else
		{
			isValid = false;
			errorMessage = "This is not a valid Tax ID Number. Must have '-' in character " + (index_1 + 1) + " or " + (index_2 + 1) + " !";
		}

		if ((oid == 'MSG07P' || oid == 'WPC08P') && (!isValid)) {
			if (!regExpSS.test(einNumber)) {
				errorMessage = "Please enter a valid Tax ID Number (format XX-XXXXXXX)\n";
				errorMessage += "or a valid SS# (format XXX-XX-XXXX).";
			} else {
				isValid = true;
			}
		}

		if (!isValid) {
			alert(errorMessage);
			frm.Vendor_vendorEin.value = "";
			frm.Vendor_vendorEin.focus();
		}

		return;
	}

	function setAuditTables()
	{
		frm.auditTables.value = "Vendor";
	}
	function getFields(el)
	{
		if( (el.type != "hidden" && el.name.indexOf("Vendor_") > -1) ||
			(el.name == "Vendor_vendor1099") ||
			(el.name == "Vendor_inspectionReqd") ||
			(el.name == "Vendor_printPrices") ||
			(el.name == "Vendor_vendUdf1"))
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
	function vendorSave()
	{
		if( frm.Vendor_status.value == '02' )
		{
			frm.Vendor_status.value = '05';
		}
		doSubmit('/rfq/rfq_suppliers.jsp', 'VendorAdd;VendorRetrieveById');
	}

// End Hide script -->
</SCRIPT>
