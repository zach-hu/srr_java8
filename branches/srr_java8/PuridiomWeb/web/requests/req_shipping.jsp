<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/req-audit.js"></SCRIPT>
<%
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_onetime_shipto = propertiesManager.getProperty("REQ OPTIONS", "ALLOW ONETIME SHIPTO", "N");
	BigDecimal bd_ic_req_header = requisitionHeader.getIcReqHeader();
	String	s_req_number = requisitionHeader.getRequisitionNumber();
	String	s_req_type = requisitionHeader.getRequisitionType();
	String	s_req_subtype = requisitionHeader.getRqSubType();
	String	s_fiscal_year = requisitionHeader.getFiscalYear();
	String	s_req_status = requisitionHeader.getStatus();
	String	s_ship_to_code = requisitionHeader.getShipToCode();
	String	s_ship_attn = requisitionHeader.getShipAttn();
	String	s_priority_code = requisitionHeader.getPriorityCode();
	String	s_shipping_code = requisitionHeader.getShippingCode();
	String	s_routing = requisitionHeader.getRouting();
	String  s_udf1code = requisitionHeader.getUdf1Code();
	String  s_udf10code = requisitionHeader.getUdf10Code();
	String  s_udf11code = requisitionHeader.getUdf11Code();
	boolean fpeUser = user.isAFpe() ;
	boolean engineerUser = user.isAEngineer() ;
	boolean msrEngineer = user.isAnAdministeredBy();

	if (HiltonUtility.isEmpty(s_req_number)){s_req_number = "N/A";}

	String	s_address_line_1 = "";
	String	s_address_line_2 = "";
	String	s_address_line_3 = "";
	String	s_address_line_4 = "";
	String	s_city = "";
	String	s_state = "";
	String	s_postal_code = "";
	String	s_country = "";

	Address shipTo = (Address) requisitionHeader.getShipToAddress();
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
	}

	String	s_current_process = "HEADER_SHIPPING";
	String	s_current_page = "/requests/req_shipping.jsp";
	String	s_current_method = "RequisitionHeaderUpdate";
	String	invalidFields = (String) request.getAttribute("invalidFields");

	while(!HiltonUtility.isEmpty(invalidFields) && invalidFields.startsWith(";")) {
		if (invalidFields.length() > 1) {
			invalidFields = invalidFields.substring(1);
		} else {
			invalidFields = "";
		}
	}
	String	s_updateShipTo = propertiesManager.getProperty("REQ DEFAULTS", "TAXCODEFROMSHIPTO", "N");
	if(s_updateShipTo.equalsIgnoreCase("Y")){
		s_current_method = "RequisitionHeaderUpdateShipTo";
	}
	String	s_current_process_method = "";

	boolean labViewGroup = false;
	List groupList = user.getUserRoles();
	for (Iterator it = groupList.iterator(); it.hasNext(); )
    {
		if (((String)it.next()).indexOf("LAB VIEW") >= 0)
		{
			labViewGroup = true;
			break;
		}
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
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=requisitionHeader.getRequisitionNumber()%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=requisitionHeader.getItemLocation()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RQH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="editFieldsApprover" value=""/>

<tsa:hidden name="country" value="<%=requisitionHeader.getUdf1Code()%>"/>
<tsa:hidden name="Address_addressType" value="SHIPTO"/>
<tsa:hidden name="Address_addressCode" value="<%=s_req_number%>"/>
<tsa:hidden name="Address_shipTo" value="Y"/>
<tsa:hidden name="updateAddress" value="N"/>

<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
	<% if (s_req_type.equals("M")) {%>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="req-type" defaultString="Req Type"/>&nbsp;<tsa:label labelName="shipping_information" defaultString="Shipping Information"></tsa:label></div>
			</tsa:td>
		</tsa:tr>
	<% } else { %>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="shipping_information" defaultString="Shipping Information"></tsa:label></div>
			</tsa:td>
		</tsa:tr>
	<% } %>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="req-requisition" defaultString="Requisition #"></tsa:label>:</b></tsa:td>
			<tsa:td width="150px"><%=headerEncoder.encodeForHTML(s_req_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="status" defaultString="Status"></tsa:label>:</b></tsa:td>
			<tsa:td width="150px"><%=DocumentStatus.toString(s_req_status, oid)%></tsa:td>
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
		<table border="0" cellspacing="0" cellpadding="0" width="90%">
		<tsa:tr>
			<tsa:td width="50%" align="center" valign="top">
				<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
				<tsa:tr field="req-shipToCode"  docType="s_req_type">
					<tsa:td align="RIGHT" noWrap="nowrap" width="50%"><A HREF="javascript: browseShipTo(); void(0);" title="Click here to choose a Ship To Code for this requisition or enter a Ship To Code in the box on the right."><tsa:label labelName="req-shipToCode" defaultString="Ship To Code" docType="s_req_type" checkRequired="true"></tsa:label></A>:&nbsp;</tsa:td>
					<tsa:td width="50%" noWrap="nowrap">
						<tsa:input type="midtext" name="RequisitionHeader_shipToCode"  tabIndex="1" maxLength="15" value="<%=s_ship_to_code%>" onchange="upperCase(this); getNewInfo('shipTo', this);" />
<%	if (s_onetime_shipto.equalsIgnoreCase("Y") &&
		!RequisitionType.INVENTORY_RETURN.equals(requisitionHeader.getRequisitionType())) { %>
						<a href="javascript: enterAddress(); void(0);"><img src="<%=contextPath%>/images/add.gif" alt="Click here to add a non-standard shipto address" border="0"></a>
<%	} %>
					</tsa:td>
				</tsa:tr>
				<tsa:tr field="req-shp-addressLine1">
					<tsa:td align="RIGHT" noWrap="nowrap"><tsa:label labelName="req-shp-addressLine1" defaultString="Address 1"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td colspan="2"><tsa:input type="text" name="Address_addressLine1" tabIndex="3" size="35" maxLength="30" value="<%=s_address_line_1%>" readonly="readonly" disabled="disabled" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-shp-addressLine2">
					<tsa:td align="RIGHT" noWrap="nowrap"><tsa:label labelName="req-shp-addressLine2" defaultString="Address 2"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td colspan="2"><tsa:input type="text" name="Address_addressLine2" tabIndex="5" size="35" maxLength="30" value="<%=s_address_line_2%>" readonly="readonly" disabled="disabled" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-shp-addressLine3">
					<tsa:td align="RIGHT" noWrap="nowrap"><tsa:label labelName="req-shp-addressLine3" defaultString="Address 3"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td colspan="2"><tsa:input type="text" name="Address_addressLine3" tabIndex="7" size="35" maxLength="30" value="<%=s_address_line_3%>" readonly="readonly" disabled="disabled" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-shp-addressLine4">
					<tsa:td align="RIGHT" noWrap="nowrap"><tsa:label labelName="req-shp-addressLine4" defaultString="Address 4"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td colspan="2"><tsa:input type="text" name="Address_addressLine4" tabIndex="9" size="35" maxLength="30" value="<%=s_address_line_4%>" readonly="readonly" disabled="disabled" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-shp-city">
					<tsa:td align="RIGHT" noWrap="nowrap"><tsa:label labelName="req-shp-city" defaultString="City"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="midtext" name="Address_city" tabIndex="11" maxLength="30" value="<%=s_city%>" readonly="readonly" disabled="disabled" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-shp-state">
					<% if(oid.equalsIgnoreCase("BLY07P")) {%>
					<tsa:td align="RIGHT" noWrap="nowrap"><tsa:label labelName="req-shp-state" defaultString="State"></tsa:label>:&nbsp;</tsa:td>
					<% } else {%>
					<tsa:td align="RIGHT" noWrap="nowrap"><a href="javascript: browseState(); void(0);"><tsa:label labelName="req-shp-state" defaultString="State" checkRequired="true"></tsa:label></a>:&nbsp;</tsa:td>
					<% } %>
					<tsa:td><tsa:input type="midtext" name="Address_state" tabIndex="13" maxLength="30" value="<%=s_state%>" readonly="readonly" disabled="disabled" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-shp-zip">
					<tsa:td align="RIGHT" noWrap="nowrap"><tsa:label labelName="req-shp-zip" defaultString="Zip/Postal Code"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="midtext" name="Address_postalCode" tabIndex="15" maxLength="30" value="<%=s_postal_code%>" readonly="readonly" disabled="disabled" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-shp-country">
					<% if(oid.equalsIgnoreCase("BLY07P")) {%>
					<tsa:td align="RIGHT" noWrap="nowrap"><a href="javascript:  updateAddress(); browseLookup('Address_country','country'); void(0);"><tsa:label labelName="req-shp-country" defaultString="Country" checkRequired="true"></tsa:label>:&nbsp;</tsa:td>
					<% } else {%>
					<tsa:td align="RIGHT" noWrap="nowrap"><tsa:label labelName="req-shp-country" defaultString="Country" checkRequired="true"></tsa:label>:&nbsp;</tsa:td>
					<% }%>
					<tsa:td><tsa:input type="midtext" name="Address_country" tabIndex="17" maxLength="25" value="<%=s_country%>" readonly="readonly" disabled="disabled" /></tsa:td>
				</tsa:tr>
			<% if (!oid.equals("SRR10P")) { %>
				<tsa:tr field="req-shp-attention">
					<tsa:td align="RIGHT" noWrap="nowrap"><tsa:label labelName="req-shp-attention" defaultString="Attention" checkRequired="true"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="midtext" name="RequisitionHeader_shipAttn" tabIndex="19" maxLength="40" value="<%=s_ship_attn%>" /></tsa:td>
				</tsa:tr>
			<% } else { %>
				<tsa:tr field="req-requiredDate">
					<tsa:td align="RIGHT" noWrap="nowrap"><tsa:label labelName="req-requiredDate" defaultString="Required By" checkRequired="true"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td noWrap="nowrap">
						<tsa:input type="mintext" name="RequisitionHeader_requiredDate" tabIndex="30" maxLength="10" value="<%=HiltonUtility.getFormattedDate(requisitionHeader.getRequiredDate(), oid, userDateFormat)%>" />
						<A HREF="javascript: show_calendar('RequisitionHeader_requiredDate', '<%=userDateFormat%>');"><IMG src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." VALIGN="BOTTOM" BORDER="0"></A>
					</tsa:td>
				</tsa:tr>
			<% } %>
				 <% if (s_req_type.equals("H"))	{ %>
			    <tsa:tr field="req-shp-cube">
					<tsa:td align="RIGHT" noWrap="nowrap"><tsa:label labelName="req-shp-cube" defaultString="Cube#" checkRequired="true"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="midtext" name="RequisitionHeader_udf1Code" tabIndex="19" maxLength="40" value="<%=s_udf1code%>" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-shp-assettag">
					<tsa:td align="RIGHT" noWrap="nowrap"><tsa:label labelName="req-shp-assettag" defaultString="Asset Tag#" checkRequired="true"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="midtext" name="RequisitionHeader_udf10Code" tabIndex="19" maxLength="40" value="<%=s_udf10code%>" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-shp-networkid">
					<tsa:td align="RIGHT" noWrap="nowrap"><tsa:label labelName="req-shp-networkid" defaultString="Network ID#" checkRequired="true"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="midtext" name="RequisitionHeader_udf11Code" tabIndex="19" maxLength="40" value="<%=s_udf11code%>" /></tsa:td>
				</tsa:tr>
				<%	} %>
				<tsa:tr><tsa:td colspan="2"><br><br></tsa:td></tsa:tr>
				<tsa:tr>
					<tsa:td colspan="3" align="center"><b><tsa:label labelName="req-requiredby14days" defaultString=""></tsa:label></b></tsa:td>
				</tsa:tr>
				</TABLE>
			</tsa:td>
			<tsa:td>
				<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0" width="100%">
			<% if (!oid.equals("SRR10P")) { %>
				<tsa:tr field="req-requiredDate">
					<tsa:td align="RIGHT" noWrap="nowrap"><tsa:label labelName="req-requiredDate" defaultString="Required By" checkRequired="true"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td noWrap="nowrap">
						<tsa:input type="mintext" name="RequisitionHeader_requiredDate" tabIndex="30" maxLength="10" value="<%=HiltonUtility.getFormattedDate(requisitionHeader.getRequiredDate(), oid, userDateFormat)%>" />
						<A HREF="javascript: show_calendar('RequisitionHeader_requiredDate', '<%=userDateFormat%>');"><IMG src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." VALIGN="BOTTOM" BORDER="0"></A>
					</tsa:td>
				</tsa:tr>
				<tsa:tr field="req-shp-rq10">
					<tsa:td align="right" noWrap="nowrap"><a href="javascript: browseStd('RequisitionHeader_udf10Code', 'RQ10'); void(0);">
					<tsa:label labelName="req-shp-rq10" defaultString="SHP-RQ10" checkRequired="true"></tsa:label></a>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="mintext" name="RequisitionHeader_udf10Code" tabIndex="31" maxLength="15" value="<%=s_udf10code%>"/></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-shipVia">
					<tsa:td align="RIGHT" noWrap="nowrap"><A HREF="javascript: browseLookup('RequisitionHeader_shippingCode', 'shipvia', !frm.RequisitionHeader_shippingCode.disabled); void(0);" title="Click here to choose the method in which you would like to receive this requisition or enter the method in the box on the right."><tsa:label labelName="req-shipVia" defaultString="Ship Via" checkRequired="true"></tsa:label></A>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="mintext" name="RequisitionHeader_shippingCode" tabIndex="32"  maxLength="15" value="<%=s_shipping_code%>" onchange="upperCase(this);" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-routing">
					<tsa:td align="RIGHT" noWrap="nowrap"><tsa:label labelName="req-routing" defaultString="Routing" checkRequired="true"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="mintext" name="RequisitionHeader_routing" tabIndex="36" maxLength="25" value="<%=s_routing%>" onchange="upperCase(this);" /></tsa:td>
				</tsa:tr>
			<% } else { %>
				<tr>
					<tsa:td align="RIGHT" noWrap="nowrap"><tsa:label labelName="req-shp-attention" defaultString="Attention" checkRequired="true"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="midtext" name="RequisitionHeader_shipAttn" tabIndex="19" maxLength="40" value="<%=s_ship_attn%>" /></tsa:td>
				</tr>
				<tsa:tr field="req-shprq10">
					<tsa:td align="right" noWrap="nowrap"><a href="javascript: browseStd('RequisitionHeader_udf10Code', 'RQ10'); void(0);">
					<tsa:label labelName="req-shprq10" defaultString="SHPRQ10" checkRequired="true"></tsa:label></a>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="mintext" name="RequisitionHeader_udf10Code" tabIndex="31" maxLength="15" field="req-shprq10" value="<%=s_udf10code%>"/></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-routing">
					<tsa:td align="RIGHT" noWrap="nowrap"><tsa:label labelName="req-routing" defaultString="Routing" checkRequired="true"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="mintext" name="RequisitionHeader_routing" tabIndex="36" maxLength="25" value="<%=s_routing%>" onchange="upperCase(this);" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-shipVia">
					<tsa:td align="RIGHT" noWrap="nowrap"><A HREF="javascript: browseLookup('RequisitionHeader_shippingCode', 'shipvia', !frm.RequisitionHeader_shippingCode.disabled); void(0);" title="Click here to choose the method in which you would like to receive this requisition or enter the method in the box on the right."><tsa:label labelName="req-shipVia" defaultString="Ship Via" checkRequired="true"></tsa:label></A>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="mintext" name="RequisitionHeader_shippingCode" tabIndex="32"  maxLength="15" value="<%=s_shipping_code%>" onchange="upperCase(this);" /></tsa:td>
				</tsa:tr>
			<% } %>
				</TABLE>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
<%	if (s_view.equalsIgnoreCase("WIZARD")) { %>
	<tsa:td rowspan="3" align="right" valign="top"><%@ include file="/requests/req_sidebar.jsp" %></tsa:td>
<%	} %>
</tsa:tr>
</table>

<% if(s_req_type.equalsIgnoreCase("H") && oid.equalsIgnoreCase("bsc04p")){ %>
<table border="0" cellspacing="0" cellpadding="2" width="680px">
<tsa:tr>
	<tsa:td colspan="2" align="center" cssClass="processOn"><b><tsa:label labelName="allItemsShippedLocal" defaultString="All items will be shipped to your local technician(s) for installation"></tsa:label>.</b></tsa:td>
</tsa:tr>
</table>
<% } %>

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

<SCRIPT value="JavaScript">
<!-- Hide script

	frm = document.purchaseform;
	var reqnumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
	var fiscalyear = "<%=requisitionHeader.getFiscalYear()%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var allowEdit;

function thisLoad()
{
	f_StartIt();
<%	if (s_req_type.equals("M") && ( fpeUser || msrEngineer ))  { %>

<%			if  (s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) >= 0)  { %>
				allowEdit = false  ;
	<%		}  %>
<%	} 	else	if ((s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVING) >= 0 && s_req_type.equals("M"))  || s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0) { %>
				checkInputSettings();
		allowEdit = false;

<%	} else if(labViewGroup==true) { %>
			checkInputSettings();
		//allowEdit = true;
		//frm.allowBrowse.value = true;
		allowInputEdit(frm.RequisitionHeader_shippingCode, true);
		allowInputEdit(frm.RequisitionHeader_requiredDate, true);
		allowInputEdit(frm.RequisitionHeader_shipAttn, true);
		setInvalidFields(<%=invalidFields%>);
<%	} else {%>
		setInvalidFields("<%=invalidFields%>");
<%	} %>
<%	if (typeIterator != null && securityTypes != null) { %>
		allowEditApprover();
<%	} 
	if(s_req_type.equals("P") && s_req_status.compareTo(DocumentStatus.REQ_REJECTED) == 0 && !user.isAFpe()){%>
		checkInputSettings();
		allowEdit = false;
<%	} %>
}

	function validateForm() {
		var handlers = frm.handler.value;
		if (handlers.indexOf("RequisitionHeaderUpdate") >= 0) {
			var alertMessage = "";

			if (frm.RequisitionHeader_requiredDate && !chkdate(frm.RequisitionHeader_requiredDate)) {
				alertMessage += "\n<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-requiredDate", "Required By")%> is not a valid date.";
			}

			if (alertMessage.length > 0) {
				alert("Please fix the following problems:\n" + alertMessage);
				return false;
			}
		}
		return true;
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
			frm.RequisitionHeader_shipAttn.value = "";

			frm.RequisitionHeader_shipToCode.value = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
			allowInputEdit(frm.RequisitionHeader_shipToCode, false);

			allowInputEdit(frm.Address_addressLine1, true);
			allowInputEdit(frm.Address_addressLine2, true);
			allowInputEdit(frm.Address_addressLine3, true);
			allowInputEdit(frm.Address_addressLine4, true);
			allowInputEdit(frm.Address_city, true);
			allowInputEdit(frm.Address_state, true);
			allowInputEdit(frm.Address_postalCode, true);
			allowInputEdit(frm.Address_country, true);
		}
	}

	function updateAddress()
	{
		if (reqnumber == "N/A")
		{
			alert("You must save your requisition before entering a new address!");
			return false;
		}
		else
		{
			frm.updateAddress.value = "TRUE";
		}
	}

	function browseState()
	{
		popupParameters = popupParameters + "colname=StdTable_id_tableType;operator==;filter_txt=STAT;logicalOperator=AND;originalFilter=Y;sort=N;"
		browseLookup("Address_state", "stdtable");
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
</SCRIPT>