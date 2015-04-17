<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.properties.*" %>
<%@ page import="java.math.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_onetime_shipto = propertiesManager.getProperty("PO OPTIONS", "ALLOW ONETIME SHIPTO", "N");
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	BigDecimal bd_ic_po_header = poHeader.getIcPoHeader();
	String	s_po_number = poHeader.getPoNumber();
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = poHeader.getReleaseNumber();
	BigDecimal	bd_revision_number = poHeader.getRevisionNumber();
	String	s_po_type = poHeader.getPoType();
	String	s_po_status = poHeader.getStatus();
	String	s_fiscal_year = poHeader.getFiscalYear();
	String s_buyer_code = poHeader.getBuyerCode();

	String	s_address_line_1 = "";
	String	s_address_line_2 = "";
	String	s_address_line_3 = "";
	String	s_address_line_4 = "";
	String	s_city = "";
	String	s_state = "";
	String	s_postal_code = "";
	String	s_country = "";
	String	s_inventory = "";

	Address shipTo = (Address) poHeader.getShipToAddress();
	if (shipTo != null)
	{
		s_address_line_1 = shipTo.getAddressLine1();
		s_address_line_2 = shipTo.getAddressLine2();
		s_address_line_3 = shipTo.getAddressLine3();
		s_address_line_4 = shipTo.getAddressLine4();
		s_city = shipTo.getCity();
		s_state = shipTo.getState();
		s_postal_code = shipTo.getPostalCode();
		s_country = shipTo.getCountry();
		s_inventory = shipTo.getInventory();
	}

	if (s_po_number == null)			{	s_po_number = "N/A";		}
	if (s_po_status == null )			{	s_po_status = "3000";		}

	if (s_address_line_1 == null)		{	s_address_line_1 = "";		}
	if (s_address_line_2 == null)		{	s_address_line_2 = "";		}
	if (s_address_line_3 == null)		{	s_address_line_3 = "";		}
	if (s_address_line_4 == null)		{	s_address_line_4 = "";		}
	if (s_city == null)					{	s_city = "";				}
	if (s_state == null)				{	s_state = "";				}
	if (s_postal_code == null)			{	s_postal_code = "";			}
	if (s_country == null)				{	s_country = "";				}
	if (s_inventory == null)			{	s_inventory = "";			}

	boolean bAllowEdit = true;
	if ( (role.getAccessRights("PO") < 2) || (!bAdminChanges && s_po_status.compareTo(DocumentStatus.PO_REJECTED) > 0)  || (s_po_status.compareTo(DocumentStatus.CANCELLED) >= 0) || s_po_status.compareTo(DocumentStatus.PO_AMENDED) == 0)
	{
		bAllowEdit = false;
	}
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer())
	{
		bAllowEdit = false;
	}

	String	s_current_process = "HEADER_SHIPPING";
	String	s_current_page = "/orders/po_shipping.jsp";
	String	s_current_method = "PoHeaderUpdate";
	String	invalidFields = (String) request.getAttribute("invalidFields");

	while(!HiltonUtility.isEmpty(invalidFields) && invalidFields.startsWith(";")) {
		if (invalidFields.length() > 1) {
			invalidFields = invalidFields.substring(1);
		} else {
			invalidFields = "";
		}
	}

	String	s_updateShipTo = propertiesManager.getProperty("PO DEFAULTS", "TAXCODEFROMSHIPTO", "N");
	if(s_updateShipTo.equalsIgnoreCase("Y")){
		s_current_method = "PoHeaderUpdateShipTo";
	}
	String	s_current_process_method = "";
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=headerEncoder.encodeForHTMLAttribute(poHeader.getPoNumber())%>"/>
<tsa:hidden name="PoSecurity_poNumber" value="<%=poHeader.getPoNumber()%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=poHeader.getReleaseNumber()%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=poHeader.getRevisionNumber()%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="PoHeader_itemLocation" value="<%=poHeader.getItemLocation()%>"/>
<tsa:hidden name="PoHeader_buyerCode" value="<%=poHeader.getBuyerCode()%>"/>
<tsa:hidden name="PoHeader_shipToInv" value="<%=poHeader.getShipToInv()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="POH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="VendorInsurance_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="VendorInsurance_contNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=poHeader.getVendorId()%>"/>
<tsa:hidden name="country" value="<%=poHeader.getUdf1Code()%>"/>
<tsa:hidden name="Address_shipTo" value="Y"/>
<tsa:hidden name="updateAddress" value="N"/>
<tsa:hidden name="poIcReqHeader" value="<%= poHeader.getIcReqHeader().toString() %>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="shippingInformation" defaultString="Shipping Information" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/orders/po_display_number.jsp" %>
	</td>
</tr>
</table>

<br>
<%@ include file="/orders/po_info.jsp" %>
<br>
<%@ include file="/system/error_msg.jsp" %>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td width=48% align=center valign=top>
				<table border=0 cellpadding=0 cellspacing=0 width=100%>
				<tsa:tr field="po-shipToCode">
					<td align=right nowrap width=50%><a href="javascript: browseLookup('PoHeader_shipToCode', 'ship_to'); void(0);" title="Click here to choose the <tsa:label labelName="po-shipToCode" defaultString="Ship To" /> for this order or enter the <tsa:label labelName="po-shipToCode" defaultString="Ship To" /> in the box on the right."><tsa:label labelName="po-shipToCode" defaultString="Ship To" checkRequired="true" /></a>:&nbsp;</td>
					<td width=50%>
						<tsa:input type="mintext" name="PoHeader_shipToCode" tabIndex="1" maxLength="15" value="<%=poHeader.getShipToCode()%>" onchange="upperCase(this); getNewInfo('shipTo', this);" />
						<% if (s_onetime_shipto.equalsIgnoreCase("Y")) { %>
						<a href="javascript: enterAddress(); void(0);"><img src="<%=contextPath%>/images/add.gif" alt="Click here to add a non-standard shipto address" border="0"></a>
						<% } %>
					</td>
				</tsa:tr>
				<tsa:tr field="po-addressLine1">
					<td align=right nowrap><tsa:label labelName="po-addressLine1" defaultString="Address 1" />:&nbsp;</td>
					<td width=50%><tsa:input labelName="po-addressLine1" type="midtext" name="Address_addressLine1" maxLength="40" value="<%=s_address_line_1%>" onfocus="this.blur()" disabled="true" /></td>
				</tsa:tr>
				<tsa:tr field="po-shp-addressLine2">
					<td align=right nowrap><tsa:label labelName="po-shp-addressLine2" defaultString="Address 2" />:&nbsp;</td>
					<td width=50%><tsa:input labelName="po-shp-addressLine2" type="midtext" name="Address_addressLine2" maxLength="40" value="<%=s_address_line_2%>" onfocus="this.blur()" disabled="true" /></td>
				</tsa:tr>
				<tsa:tr field="po-shp-addressLine3">
					<td align=right nowrap><tsa:label labelName="po-shp-addressLine3" defaultString="Address 3" />:&nbsp;</td>
					<td width=50%><tsa:input labelName="po-shp-addressLine3" type="midtext" name="Address_addressLine3" maxLength="40" value="<%=s_address_line_3%>" onfocus="this.blur()" disabled="true" /></td>
				</tsa:tr>
				<tsa:tr field="po-shp-addressLine4">
					<td align=right nowrap><tsa:label labelName="po-shp-addressLine4" defaultString="Address 4" />:&nbsp;</td>
					<td width=50%><tsa:input labelName="po-shp-addressLine4" type="midtext" name="Address_addressLine4" maxLength="40" value="<%=s_address_line_4%>" onfocus="this.blur()" disabled="true" /></td>
				</tsa:tr>
				<tsa:tr field="po-shp-city">
					<td align=right nowrap><tsa:label labelName="po-shp-city" defaultString="City" />:&nbsp;</td>
					<td width=50%><tsa:input labelName="po-shp-city" type="midtext" name="Address_city" maxLength="30" value="<%=s_city%>" onfocus="this.blur()" disabled="true" /></td>
				</tsa:tr>
				<tsa:tr field="po-shp-state">
					<td align=right nowrap><tsa:label labelName="po-shp-state" defaultString="State" />:&nbsp;</td>
					<td width=50%><tsa:input labelName="po-shp-state" type="midtext" name="Address_state" maxLength="15" value="<%=s_state%>" onfocus="this.blur()" disabled="true" /></td>
				</tsa:tr>
				<tsa:tr field="po-shp-zip">
					<td align=right nowrap><tsa:label labelName="po-shp-zip" defaultString="Zip" />:&nbsp;</td>
					<td width=50%><tsa:input labelName="po-shp-zip" type="midtext" name="Address_postalCode" maxLength="15" value="<%=s_postal_code%>" onfocus="this.blur()" disabled="true" /></td>
				</tsa:tr>
				<tsa:tr field="po-shp-country">
					<td align=right nowrap><tsa:label labelName="po-shp-country" defaultString="Country" />:&nbsp;</td>
					<td width=50%><tsa:input labelName="po-shp-country" type="midtext" name="Address_country" maxLength="25" value="<%=s_country%>" onfocus="this.blur()" disabled="true" /></td>
				</tsa:tr>
				<tr>
					<td align=right nowrap>&nbsp;</td>
					<td width=50%>
						<tsa:hidden name="Address_inventory_message" value="Inventory Location"/>
					</td>
				</tr>
				<tr><td colspan=2><br></td></tr>
				</table>
			</td>
			<td width=4%>&nbsp;</td>
			<td width=48% align=center valign=top>
				<table border=0 cellpadding=0 cellspacing=0 width=100%>
				<tr><td colspan=2><br><br><br><br></td></tr>
				<tsa:tr field="po-shp-attention">
					<td align=right nowrap><tsa:label labelName="po-shp-attention" defaultString="Attention" checkRequired="true" />:&nbsp;</TD>
					<td><tsa:input labelName="po-shp-attention"  type="mintext" name="PoHeader_shipToContact" tabIndex="3" maxLength="40" value="<%=poHeader.getShipToContact()%>" /></td>
				</tsa:tr>
				<tsa:tr field="po-shipVia">
					<td align=right nowrap><a href="javascript: browseLookup('PoHeader_shipViaCode', 'shipvia'); void(0);" title="Click here to choose the method in which you would like to recieve this order or enter the method in the box on the right."><tsa:label labelName="po-shipVia" defaultString="Ship Via" checkRequired="true" /></a>:&nbsp;</td>
					<td><tsa:input labelName="po-shipVia" type="mintext" name="PoHeader_shipViaCode" tabIndex="5" maxLength="15" value="<%=poHeader.getShipViaCode()%>" onchange="upperCase(this);" /></td>
				</tsa:tr>
				<tsa:tr field="po-shp-requiredDate">
					<TD ALIGN="RIGHT" nowrap><tsa:label labelName="po-shp-requiredDate" defaultString="Required By" checkRequired="true" noinstance="true" />:&nbsp;</TD>
					<TD nowrap><tsa:input labelName="po-shp-requiredDate" type="mintext" name="PoHeader_requiredDate" tabIndex="30" maxLength="10" value="<%=HiltonUtility.getFormattedDate(poHeader.getRequiredDate(), oid, userDateFormat)%>" />
				    <A HREF="javascript: show_calendar('PoHeader_requiredDate', '<%=userDateFormat%>');"><IMG src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." VALIGN="BOTTOM" BORDER=0></A>
				    </TD>
				</tsa:tr>
				<tsa:tr field="po-PO10">
					<TD ALIGN="RIGHT" nowrap>
				<%	if (DictionaryManager.isLink(oid, "po-PO10")) {	%>
						<a href="javascript: browseStd('PoHeader_udf10Code', 'PO10'); void(0);" title="Click here to choose a value for <tsa:label labelName="po-PO10" defaultString="UDF10" /> for this order or enter a value in the box on the right."><tsa:label labelName="po-PO10" defaultString="UDF10" checkRequired="true" />:</a>&nbsp;
				<%	} else {	%>
         				<tsa:label labelName="po-PO10" defaultString="UDF10" checkRequired="true" />:&nbsp;
				<% } %>
					</td>
					<td>
         				<tsa:input type="mintext" name="PoHeader_udf10Code" tabIndex="45" maxLength="15" value="<%=poHeader.getUdf10Code()%>" onchange="upperCase(this);" />
					</td>
				</tsa:tr>
				<tsa:tr field="po-routing">
					<TD ALIGN="RIGHT" NOWRAP><tsa:label labelName="po-routing" defaultString="Routing" checkRequired="true" />:&nbsp;</TD>
					<TD><tsa:input labelName="po-routing" type="mintext" name="PoHeader_routing" tabIndex="9" maxLength="25" value="<%=poHeader.getRouting()%>" /></TD>
				</tsa:tr>
				<tr><td colspan=2><br></td></tr>
				</table>
			</td>
<%	if (s_view.equalsIgnoreCase("WIZARD")) { %>
			<td rowspan=3 align=right valign=top><%@ include file="/orders/po_sidebar.jsp" %></td>
<%	} %>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoHeaderUpdate;PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var ponumber = "<%=s_po_number%>";
	var fiscalyear = "<%=poHeader.getFiscalYear()%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	function thisLoad()
	{
		f_StartIt();
<%	if (!bAllowEdit) { %>
			checkInputSettings();
<%	} else {%>
		setInvalidFields("<%= headerEncoder.encodeForJavaScript(invalidFields) %>");
<%	}%>
	}

	function enterAddress()
	{
		if (ponumber == "N/A")
		{
			alert("You must save your order before entering a new address!");
			return false;
		}
		else
		{
			frm.updateAddress.value = "TRUE";

			frm.PoHeader_shipToCode.value = "<%=s_po_number%>";
			allowInputEdit(frm.PoHeader_shipToCode, false);

			if (frm.Address_addressLine1) {
				frm.Address_addressLine1.value = "";
				allowInputEdit(frm.Address_addressLine1, true);
			}
			if (frm.Address_addressLine2) {
				frm.Address_addressLine2.value = "";
				allowInputEdit(frm.Address_addressLine2, true);
			}
			if (frm.Address_addressLine3) {
				frm.Address_addressLine3.value = "";
				allowInputEdit(frm.Address_addressLine3, true);
			}
			if (frm.Address_addressLine4) {
				frm.Address_addressLine4.value = "";
				allowInputEdit(frm.Address_addressLine4, true);
			}
			if (frm.Address_city) {
				frm.Address_city.value = "";
				allowInputEdit(frm.Address_city, true);
			}
			if (frm.Address_state) {
				frm.Address_state.value = "";
				allowInputEdit(frm.Address_state, true);
			}
			if (frm.Address_postalCode) {
				frm.Address_postalCode.value = "";
				allowInputEdit(frm.Address_postalCode, true);
			}
			if (frm.Address_country) {
				frm.Address_country.value = "";
				allowInputEdit(frm.Address_country, true);
			}
		}
	}

// End Hide script -->
</SCRIPT>
