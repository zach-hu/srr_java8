<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@page import="com.tsa.puridiom.common.documents.ScheduleType"%>
<%@ page import="java.math.BigDecimal" %>
<%pageContext.setAttribute("oid", oid); %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<%
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	BigDecimal b_req_ic_header = requisitionHeader.getIcReqHeader();
	String	s_status_code =requisitionHeader.getStatus();
	String	s_req_type = requisitionHeader.getRequisitionType();
	String	s_req_number = requisitionHeader.getRequisitionNumber();
	String	s_msr_number = requisitionHeader.getMsrNumber();
	String	s_requisitioner_code = requisitionHeader.getRequisitionerCode();
	UserProfile requisitioner = UserManager.getInstance().getUser(oid, s_requisitioner_code);
	UserProfile authby = UserManager.getInstance().getUser(oid, requisitionHeader.getAuthorizationCode());
	UserProfile buyer = UserManager.getInstance().getUser(oid, requisitionHeader.getBuyer());

	if (HiltonUtility.isEmpty(s_req_number))	{	s_req_number = "N/A";			}

	List lineList = (List) requisitionHeader.getRequisitionLineList();
	int	i_line_count = 0;

	if (lineList != null) {
		i_line_count = lineList.size();
	}

	int iBefore = 0;
	int iAfter = 0;
	int iBeforeItem = 0;
	int iAfterItem = 0;

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	String	s_view_prices = propertiesManager.getProperty("REQ OPTIONS", "ViewPrices", "Y");
	pageContext.setAttribute("oid", oid);
	pageContext.setAttribute("language",language);
%>

<%@ include file="/requests/req_process_steps.jsp" %>

<table width="660px" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="formtype" valign="middle">
				&nbsp;&nbsp;<%=RequisitionType.toString(s_req_type, oid)%>
				<tsa:label labelName="information" defaultString="Information" />
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" align="right" height="30px">
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="requisitionNumber" defaultString="Requisition #" />:</b></tsa:td>
			<tsa:td width="100px"><%=headerEncoder.encodeForHTML(s_req_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="status" defaultString="Status" />:</b></tsa:td>
			<tsa:td width="100px"><%=DocumentStatus.toString(requisitionHeader.getStatus())%></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="660px">
<tsa:tr>
	<tsa:td width="660px" align="center">
		<table border="0" cellpadding="0" cellspacing="0" width="660px">
		<tsa:tr>
<%	if (processMap.containsKey("HEADER_GENERAL_INFO"))	{ %>
			<tsa:td width="50%" align="center" valign="top">
				<table border="0" cellspacing="0" cellpadding="0" width="320px" class="browseHdr">
				<tsa:tr>
					<tsa:td>
						<table border="1" cellspacing="0" cellpadding="0" width="320px" class="browseHdr">
						<tsa:tr>
							<tsa:td cssClass="browseHdr" height="18px" noWrap="nowrap">&nbsp;
							<tsa:label labelName="general_information" defaultString="General Information" />
							</tsa:td>
						</tsa:tr>
						</table>
					</tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td cssClass="browseRow">
						<table border="0" cellspacing="0" cellpadding="2" width="100%">
						<tsa:tr field="requestDate">
							<tsa:td noWrap="nowrap" align="right"><b><tsa:label labelName="requestDate" defaultString="Request Date" />:</b>&nbsp;</tsa:td>
							<tsa:td noWrap="nowrap"><%=HiltonUtility.getFormattedDate(requisitionHeader.getRequisitionDate(), oid, userDateFormat)%></tsa:td>
						</tsa:tr>
						<tsa:tr field="req-fiscalYear">
							<tsa:td noWrap="nowrap" align="right"><b><tsa:label labelName="req-fiscalYear" defaultString="Fiscal Year" />:&nbsp;</b></tsa:td>
							<tsa:td noWrap="nowrap">
								<%=requisitionHeader.getFiscalYear()%>
								<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=requisitionHeader.getFiscalYear()%>"/>
							</tsa:td>
						</tsa:tr>
						<%	if (s_req_type.equals("P")) {%>
						<tr <%=HtmlWriter.isVisible(oid, "req-msrnumber")%>>
							<td align=right valign=top width=40%><b><tsa:label labelName="req-msrnumber" defaultString="MSR #" />:</b></td>
							<td nowrap valign=top><%=s_msr_number%></td>
						</tr>
						<%  } %>
						<tsa:tr field="req-requisitionerName">
							<tsa:td noWrap="nowrap" align="right" width="40%"><b><tsa:label labelName="req-requisitionerName" defaultString="Requisitioner" />:</b>&nbsp;</tsa:td>
							<tsa:td noWrap="nowrap"><%=requisitioner.getDisplayName()%></tsa:td>
						</tsa:tr>
						<tsa:tr field="req-department">
							<tsa:td noWrap="nowrap" align="right" width="40%"><b><tsa:label labelName="req-department" defaultString="Department" />:&nbsp;</b></tsa:td>
							<tsa:td noWrap="nowrap"><%=requisitionHeader.getDepartmentCode()%></tsa:td>
						</tsa:tr>
						<tsa:tr field="req-buyerName">
							<tsa:td noWrap="nowrap" align="right"><b><tsa:label labelName="req-buyerName" defaultString="Buyer" />:&nbsp;</b></tsa:td>
							<tsa:td noWrap="nowrap"><%=buyer.getDisplayName()%></tsa:td>
						</tsa:tr>
						<tsa:tr field="req-authorizedByName">
							<tsa:td noWrap="nowrap" align="right"><b><tsa:label labelName="req-authorizedByName" defaultString="Authorized By Name" />:&nbsp;</b></tsa:td>
							<tsa:td noWrap="nowrap"><%=authby.getDisplayName()%></tsa:td>
						</tsa:tr>
<%	if (propertiesManager.getProperty("REQ OPTIONS", "DISPLAYRFUDFS", "N").equals("Y")) { %>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ01")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ01" defaultString="UDF1" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf1Code()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ02")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ02" defaultString="UDF2" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf2Code()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ03")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ03" defaultString="UDF3" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf3Code()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ04")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ04" defaultString="UDF4" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf4Code()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ05")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ05" defaultString="UDF5" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf5Code()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ06")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ06" defaultString="UDF6" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf6Code()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ07")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ07" defaultString="UDF7" checkRequired="false" />:</b></td>
							<td valign=top><%=requisitionHeader.getUdf7Code()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ08")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ08" defaultString="UDF8" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf8Code()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ09")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ09" defaultString="UDF9" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf9Code()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ10")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ10" defaultString="UDF10" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf10Code()%></td>
						</tr>
<%	} %>
						</table>
					</tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
<%	}
		if (processMap.containsKey("HEADER_SHIPPING"))
		{
			String	s_ship_address_line_1 = "";
			String	s_ship_address_line_2 = "";
			String	s_ship_address_line_3 = "";
			String	s_ship_address_line_4 = "";
			String	s_ship_city = "";
			String	s_ship_state = "";
			String	s_ship_postal_code = "";
			String	s_ship_country = "";

			Address shipTo = (Address) requisitionHeader.getShipToAddress();
			if (shipTo != null)
			{
				s_ship_address_line_1 = shipTo.getAddressLine1();
				s_ship_address_line_2 = shipTo.getAddressLine2();
				s_ship_address_line_3 = shipTo.getAddressLine3();
				s_ship_address_line_4 = shipTo.getAddressLine4();
				s_ship_city = shipTo.getCity();
				s_ship_state = shipTo.getState();
				s_ship_postal_code = shipTo.getPostalCode();
				s_ship_country = shipTo.getCountry();
			}
%>
			<tsa:td width="50%" align="center" valign="top">
				<table border="0" cellspacing="0" cellpadding="0" width="320px" class="browseHdr">
				<tsa:tr>
					<tsa:td>
						<table border="1" cellspacing="0" cellpadding="0" width="320px" class="browseHdr">
						<tsa:tr>
							<tsa:td cssClass="browseHdr" height="18px" noWrap="nowrap">&nbsp;<tsa:label labelName="shipping_information" defaultString="Shipping Information" /></tsa:td>
						</tsa:tr>
						</table>
					</tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td cssClass="browseRow">
						<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
						<tsa:tr field="req-shipToCode">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><b><%=requisitionHeader.getShipToCode()%></b></tsa:td>
						</tsa:tr>
						<tsa:tr field="req-shp-addressLine1">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_ship_address_line_1%></tsa:td>
						</tsa:tr>
<%		if (!HiltonUtility.isEmpty(s_ship_address_line_2))
			{ %>
						<tsa:tr field="req-shp-addressLine2">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_ship_address_line_2%></tsa:td>
						</tsa:tr>
<%		}
			if (!HiltonUtility.isEmpty(s_ship_address_line_3))
			{ %>
						<tsa:tr field="req-shp-addressLine3">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_ship_address_line_3%></tsa:td>
						</tsa:tr>
<%		}
			if (!HiltonUtility.isEmpty(s_ship_address_line_4))
			{ %>
						<tsa:tr field="req-shp-addressLine4">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_ship_address_line_4%></tsa:td>
						</tsa:tr>
<%		}%>
						<tsa:tr><tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></tsa:td></tsa:tr>
						<tsa:tr field="req-shp-country">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_ship_country%></tsa:td>
						</tsa:tr>
<%		if (!HiltonUtility.isEmpty(requisitionHeader.getShipAttn())) { %>
						<tsa:tr field="req-shp-attention">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><tsa:label labelName="req-shp-attention" defaultString="Attn" />:&nbsp<%=requisitionHeader.getShipAttn()%></tsa:td>
						</tsa:tr>
<%		} %>

						<tsa:tr field="req-requiredDate">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><tsa:label labelName="req-requiredDate" defaultString="Required Date" checkRequired="false" />:&nbsp<%=HiltonUtility.getFormattedDate(requisitionHeader.getRequiredDate(), oid, userDateFormat)%></tsa:td>
						</tsa:tr>
						<tsa:tr field="req-priority">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><tsa:label labelName="req-priority" defaultString="Priority" checkRequired="false"/>:&nbsp<%=requisitionHeader.getPriorityCode()%></tsa:td>
						</tsa:tr>
						<tsa:tr field="req-shipVia">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><tsa:label labelName="req-shipVia" defaultString="Ship Via" checkRequired="false"/>:&nbsp<%=requisitionHeader.getShippingCode()%></tsa:td>
						</tsa:tr>
						<tsa:tr field="req-routing">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><tsa:label labelName="req-routing" defaultString="Routing" checkRequired="false"/>:&nbsp;<%=requisitionHeader.getRouting()%></tsa:td>
						</tsa:tr>
						</table>
					</tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
<%	} %>
		</tsa:tr>
		<tsa:tr><tsa:td colspan="2">&nbsp;</tsa:td></tsa:tr>
		<tsa:tr>
<%	if (processMap.containsKey("HEADER_SUPPLIER"))
		{
			String	s_vend_address_line_1 = "";
			String	s_vend_address_line_2 = "";
			String	s_vend_address_line_3 = "";
			String	s_vend_address_line_4 = "";
			String	s_vend_city = "";
			String	s_vend_state = "";
			String	s_vend_postal_code = "";
			String	s_vend_country = "";

			Address vendor = (Address) requisitionHeader.getVendorAddress();
			if (vendor != null)
			{
				s_vend_address_line_1 = vendor.getAddressLine1();
				s_vend_address_line_2 = vendor.getAddressLine2();
				s_vend_address_line_3 = vendor.getAddressLine3();
				s_vend_address_line_4 = vendor.getAddressLine4();
				s_vend_city = vendor.getCity();
				s_vend_state = vendor.getState();
				s_vend_postal_code = vendor.getPostalCode();
				s_vend_country = vendor.getCountry();
			}
%>
			<tsa:td width="50%" align="center" valign="top">
				<table id="supplierTable" border="0" cellspacing="0" cellpadding="0" width="320px" class="browseHdr">
				<tsa:tr>
					<tsa:td>
						<table border="1" cellspacing="0" cellpadding="0" width="320px" class="browseHdr">
						<tsa:tr>
							<tsa:td cssClass="browseHdr" height="18px" noWrap="nowrap">&nbsp;<tsa:label labelName="supplier_information" defaultString="Supplier Information" /></tsa:td>
						</tsa:tr>
						</table>
					</tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td cssClass="browseRow" id="supplierRows">
						<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
						<tsa:tr field="req-supplier">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><b><%=requisitionHeader.getVendorId()%></b></tsa:td>
						</tsa:tr>
						<tsa:tr field="req-sup-addressLine1">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_vend_address_line_1%></tsa:td>
						</tsa:tr>
<%		if (!HiltonUtility.isEmpty(s_vend_address_line_2))
			{ %>
						<tsa:tr field="req-sup-addressLine2">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_vend_address_line_2%></tsa:td>
						</tsa:tr>
<%		}
			if (!HiltonUtility.isEmpty(s_vend_address_line_3))
			{ %>
						<tsa:tr field="req-sup-addressLine3">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_vend_address_line_3%></tsa:td>
						</tsa:tr>
<%		}
			if (!HiltonUtility.isEmpty(s_vend_address_line_4))
			{ %>
						<tsa:tr field="req-sup-addressLine4">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_vend_address_line_4%></tsa:td>
						</tsa:tr>
<%		}%>
						<tsa:tr><tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_vend_city%>&nbsp;<%=s_vend_state%>&nbsp;<%=s_vend_postal_code%></tsa:td></tsa:tr>
						<tsa:tr field="req-sup-country">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_vend_country%></tsa:td>
						</tsa:tr>
<%		if (!HiltonUtility.isEmpty(requisitionHeader.getVendorAttn())) { %>
						<tsa:tr field="req-sup-attention">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><tsa:label labelName="req-sup-attention" defaultString="Attn" />: <%=requisitionHeader.getVendorAttn()%></tsa:td>
						</tsa:tr>
<%		} %>
						</table>
					</tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
<%	}
		if (processMap.containsKey("HEADER_BILLING"))
		{
			String	s_bill_address_line_1 = "";
			String	s_bill_address_line_2 = "";
			String	s_bill_address_line_3 = "";
			String	s_bill_address_line_4 = "";
			String	s_bill_city = "";
			String	s_bill_state = "";
			String	s_bill_postal_code = "";
			String	s_bill_country = "";

			Address billTo = (Address) requisitionHeader.getBillToAddress();
			if (billTo != null)
			{
				s_bill_address_line_1 = billTo.getAddressLine1();
				s_bill_address_line_2 = billTo.getAddressLine2();
				s_bill_address_line_3 = billTo.getAddressLine3();
				s_bill_address_line_4 = billTo.getAddressLine4();
				s_bill_city = billTo.getCity();
				s_bill_state = billTo.getState();
				s_bill_postal_code = billTo.getPostalCode();
				s_bill_country = billTo.getCountry();
			}
%>
			<tsa:td width="50%" align="center" valign="top">
				<table id="billingTable" border="0" cellspacing="0" cellpadding="0" width="320px" class="browseHdr">
				<tsa:tr>
					<tsa:td>
						<table id="billingTable" border="1" cellspacing="0" cellpadding="0" width="320px" class="browseHdr">
						<tsa:tr>
							<tsa:td cssClass="browseHdr" height="18px" noWrap="nowrap">&nbsp;<tsa:label labelName="billing_information" defaultString="Billing Information" /></tsa:td>
						</tsa:tr>
						</table>
					</tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td cssClass="browseRow" id="billingRows">
						<table border="0" cellspacing="0" cellpadding="2" width="210px" class="browseRow">
						<tsa:tr field="req-billToCode">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><b><%=requisitionHeader.getBillToCode()%></b></tsa:td>
						</tsa:tr>
						<tsa:tr field="req-bil-addressLine1">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_bill_address_line_1%></tsa:td>
						</tsa:tr>
<%		if (!HiltonUtility.isEmpty(s_bill_address_line_2))
			{ %>
						<tsa:tr field="req-bil-addressLine2">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_bill_address_line_2%></tsa:td>
						</tsa:tr>
<%		}
			if (!HiltonUtility.isEmpty(s_bill_address_line_3))
			{ %>
						<tsa:tr field="req-bil-addressLine3">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_bill_address_line_3%></tsa:td>
						</tsa:tr>
<%		}
			if (!HiltonUtility.isEmpty(s_bill_address_line_4))
			{ %>
						<tsa:tr field="req-bil-addressLine4">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_bill_address_line_4%></tsa:td>
						</tsa:tr>
<%		} %>
						<tsa:tr><tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_bill_city%>&nbsp;<%=s_bill_state%>&nbsp;<%=s_bill_postal_code%></tsa:td></tsa:tr>
						<tsa:tr field="req-bil-country">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><%=s_bill_country%></tsa:td>
						</tsa:tr>
<%		if (!HiltonUtility.isEmpty(requisitionHeader.getBillToContact())) { %>
						<tsa:tr field="req-bil-attention">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><tsa:label labelName="req-bil-attention" defaultString="Attn" />:&nbsp<%=requisitionHeader.getBillToContact()%></tsa:td>
						</tsa:tr>
<%		}
			if (!HiltonUtility.isEmpty(requisitionHeader.getTaxCode())) { %>
						<tsa:tr field="req-taxCode">
						<tsa:td cssClass="browseRow" height="12px" noWrap="nowrap"><tsa:label labelName="req-taxCode" defaultString="Tax Code" />:&nbsp<%=requisitionHeader.getTaxCode()%></tsa:td>
						</tsa:tr>
<%		} %>
						</table>
					</tsa:td>
				</tsa:tr>
				</table>
				</tsa:td>
<%	} %>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>

<%	if (processMap.containsKey("HEADER_ACCOUNTS"))
		{
			List accList = (List) requisitionHeader.getAccountList();
			BigDecimal bd_total_perc = new BigDecimal(0.00);
			BigDecimal bd_total_amt = new BigDecimal(0.00);
			if (accList != null && accList.size() > 0)
			{
%>
<table border="0" cellpadding="0" cellspacing="0" width="660px">
<tsa:tr>
	<tsa:td align="center" width="660px">
		<table border="0" cellspacing="0" cellpadding="0" width="650px" class="browseHdr">
		<tsa:tr>
			<tsa:td>
				<table border="1" cellspacing="0" cellpadding="0" width="650px" class="browseHdr">
				<tsa:tr>
					<tsa:td>
						<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdr">
						<tsa:tr>
							<tsa:td width="70%" height="18px" cssClass="browseHdr">&nbsp;<b><tsa:label labelName="account" defaultString="Account" /></b></tsa:td>
							<tsa:td width="15%" height="18px" cssClass="browseHdr" align="right">&nbsp;<b><tsa:label labelName="percentalloc" defaultString="Percent Alloc" />.</b></tsa:td>
							<tsa:td width="15%" height="18px" cssClass="browseHdr" align="right">&nbsp;<b><tsa:label labelName="amountAlloc" defaultString="Amount Alloc" />.</b></tsa:td>
						</tsa:tr>
						</table>
					</tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td>
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">

<%
				for (int i = 0;i < accList.size(); i++)
				{
					Account account = (Account) accList.get(i);

					BigDecimal bd_alloc_perc = account.getAllocPercent();
					BigDecimal bd_alloc_amt = account.getAllocAmount();

					if (bd_alloc_perc == null)	{	bd_alloc_perc = new BigDecimal(0);	}
					if (bd_alloc_amt == null)	{	bd_alloc_amt = new BigDecimal(0);	}

					bd_total_perc = bd_total_perc.add(bd_alloc_perc);
					bd_total_amt = bd_total_amt.add(bd_alloc_amt);

					String	s_account = "";
					String	s_accArray[] = new String[15];

					s_accArray[0] = account.getFld1();
					s_accArray[1] = account.getFld2();
					s_accArray[2] = account.getFld3();
					s_accArray[3] = account.getFld4();
					s_accArray[4] = account.getFld5();
					s_accArray[5] = account.getFld6();
					s_accArray[6] = account.getFld7();
					s_accArray[7] = account.getFld8();
					s_accArray[8] = account.getFld9();
					s_accArray[9] = account.getFld10();
					s_accArray[10] = account.getFld11();
					s_accArray[11] = account.getFld12();
					s_accArray[12] = account.getFld13();
					s_accArray[13] = account.getFld14();
					s_accArray[14] = account.getFld15();

					for (int j = 0; j < 15; j++)
					{
						if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
						{
							if (s_account.length() > 0)
							{
								s_account = s_account + s_account_separator + s_accArray[j];
							}
							else
							{
								s_account = s_accArray[j];
							}
						}
					}
%>
				<tsa:tr>
					<tsa:td width="70%" cssClass="browseRow"><%=s_account%></tsa:td>
					<tsa:td width="15%" cssClass="browseRow" align="right"><%=HiltonUtility.getBigDecimalFormatted(account.getAllocPercent(), 2)%>%</tsa:td>
					<tsa:td width="15%" cssClass="browseRow" align="right"><%=HiltonUtility.getFormattedDollar(account.getAllocAmount(), oid)%></tsa:td>
				</tsa:tr>
<%			} %>
				<tsa:tr>
					<tsa:td cssClass="browseRow">&nbsp;</tsa:td>
					<tsa:td cssClass="browseRow" colspan="2" valign="top"><hr size="0" align="right" colspan="2"></tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td width="70%" cssClass="browseRow">&nbsp;</tsa:td>
					<tsa:td width="15%" cssClass="browseRow" align="right"><%=HiltonUtility.getBigDecimalFormatted(bd_total_perc, 2)%>%</tsa:td>
					<tsa:td width="15%" cssClass="browseRow" align="right"><%=HiltonUtility.getFormattedDollar(bd_total_amt, oid)%></tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>
<%		}
		} %>

<br>

<%	if (processMap.containsKey("HEADER_NOTES")) { %>
<div id="commentBefore" style="display: none;">
<table border="0" cellpadding="0" cellspacing="0" width="660px">
<tsa:tr>
	<tsa:td align="center" width="660px">
		<table border="0" cellspacing="0" cellpadding="0" width="650px" class="browseHdr">
		<tsa:tr>
			<tsa:td>
				<table border="1" cellspacing="0" cellpadding="0" width="650px" class="browseHdr">
				<tsa:tr>
					<tsa:td>
						<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdr">
						<tsa:tr>
							<tsa:td width="100%" colspan="2" height="18px" cssClass="browseHdr">&nbsp;<b><tsa:label labelName="req-comment" defaultString="Comment"></tsa:label></b></tsa:td>
						</tsa:tr>
						</table>
					</tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td>
<%
			List cmtList = (List) requisitionHeader.getDocCommentList();
			if (cmtList != null)
			{
				for(int i = 0; i < cmtList.size(); i++)
				{
					DocComment docComment = (DocComment) cmtList.get(i);
					DocText docText = docComment.getDocText();

					String s_cmt_title = docComment.getCommentTitle();
					String s_cmt_print = docComment.getCommentPrint();
					String s_cmt_bold = docComment.getCommentBold();
					String s_cmt_place = docComment.getCommentPlace();
					String s_cmt_text = docText.getStdText();

					if (s_cmt_place.equals("A"))
					{
						continue;
					}
					/*if (s_cmt_print.equals("N"))
					{
						continue;
					}*/
					iBefore++;
%>
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
				<tsa:tr>
					<tsa:td width="100%"  colspan="2" cssClass="browseRow">
<%				if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_title%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td width="75px">&nbsp;</tsa:td>
					<tsa:td width="100%">
<%				if (s_cmt_bold.equals("Y")) 	{ %>	<b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</tsa:td>
				</tsa:tr>
				</table>
<%			}
			}
			List atcList = (List) requisitionHeader.getDocAttachmentList();
			if (atcList != null)
			{ %>
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
				<tsa:tr><tsa:td colspan="3"><hr size="0"></tsa:td></tsa:tr>

				<tsa:tr>
					<tsa:td width="30%" cssClass="browseRow">&nbsp;<b><tsa:label labelName="req-attachment-title" defaultString="Attachment Title"></tsa:label></b></tsa:td>
					<tsa:td width="60%" cssClass="browseRow">&nbsp;<b><tsa:label labelName="req-attachment-description" defaultString="Attachment Description"></tsa:label></b></tsa:td>
					<tsa:td width="10%" cssClass="browseRow">&nbsp;</tsa:td>
				</tsa:tr>

				<tsa:tr>
					<tsa:td>
<%
				for(int i = 0; i < atcList.size(); i++)
				{
					DocAttachment docAttachment = (DocAttachment) atcList.get(i);
					String s_atc_title = docAttachment.getDocTitle();
					String filename = docAttachment.getDocFilename();
					String ext = filename.substring(filename.lastIndexOf(".") + 1);
%>
						<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
						<tsa:tr>
							<td width=25px align=center valign=middle>
<%		if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT") || ext.equalsIgnoreCase("PPTX")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
							</td>
							<tsa:td cssClass="browseRow">
								<a href="javascript: openDocument(<%=i%>); void(0);" title="Click here to open the <%=docAttachment.getDocTitle()%> attachment."><%=s_atc_title%></a>
								<tsa:hidden name="docFilename" value="<%=filename%>"/>
							</tsa:td>
						</tsa:tr>
						</table>
<%				}%>
					</tsa:td>
				</tsa:tr>
				</table>
			<%} %>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>
</div>
<%	} %>

<br>

<%	if (processMap.containsKey("SHOPCART")) { %>
<table border="0" cellpadding="0" cellspacing="0" width="660px">
<tsa:tr>
	<tsa:td align="center" width="660px">
		<table id="itemTable" border="0" cellspacing="0" cellpadding="0" width="650px" class="browseHdr">
		<tsa:tr>
			<tsa:td>
				<table border="1" cellspacing="0" cellpadding="0" width="650px" class="browseHdr">
				<tsa:tr>
					<tsa:td width="100%" align="center" valign="top">
						<table border="0" cellspacing="0" cellpadding="4" width="100%" class="browseRow">
						<tsa:tr>
							<tsa:td field="req-lineNumber" width="7%" cssClass="browseHdr" noWrap="nowrap" >
							&nbsp;<tsa:label labelName="req-hdg-lineNumber" defaultString="Line #" checkRequired="false"/>
							</tsa:td>
							<tsa:td field="req-itemNumber" width="18%" cssClass="browseHdr" noWrap="nowrap" >
							&nbsp;<tsa:label labelName="req-hdg-itemNumber" defaultString="Item/Part #" checkRequired="false"/>
							</tsa:td>
							<tsa:td field="req-commodity" width="15%" cssClass="browseHdr" noWrap="nowrap" >
							&nbsp;<tsa:label labelName="req-hdg-commodity" defaultString="Commodity" checkRequired="false"/>
							</tsa:td>
							<tsa:td field="req-quantity" width="10%" align="right" noWrap="nowrap" cssClass="browseHdr">
							&nbsp;<tsa:label labelName="req-hdg-quantity" defaultString="Quantity" checkRequired="false"/>
							</tsa:td>
							<tsa:td field="req-uom" width="10%" cssClass="browseHdr" noWrap="nowrap" >
							&nbsp;<tsa:label labelName="req-hdg-uom" defaultString="UOM" checkRequired="false"/>
							</tsa:td>
							<tsa:td field="req-extendedCost" width="15%" align="right" noWrap="nowrap" cssClass="browseHdr" >
							&nbsp;<tsa:label labelName="req-hdg-extendedCost" defaultString="Ext Cost" checkRequired="false"/>
							</tsa:td>
							<tsa:td field="req-discountAmount" width="12%" align="right" noWrap="nowrap" cssClass="browseHdr" >
							&nbsp;<tsa:label labelName="req-hdg-discountAmount" defaultString="Discount" checkRequired="false"/>
							</tsa:td>
							<tsa:td field="req-lineStatus" width="13%" cssClass="browseHdr" noWrap="nowrap" >
							&nbsp;<tsa:label labelName="req-hdg-lineStatus" defaultString="Line Status" checkRequired="false"/>
							</tsa:td>
						</tsa:tr>
						</table>
					</tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td width="100%" align="center" valign="top">
				<table border="0" cellspacing="0" cellpadding="0" width="100%" class="browseRow">
				<tsa:tr>
					<tsa:td>
<%
			int iRow = 0;
			for (int i = 0; i < i_line_count; i++)
			{
				RequisitionLine reqLine = (RequisitionLine) lineList.get(i);

				BigDecimal b_quantity = HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid);
				BigDecimal b_unit_price = HiltonUtility.getFormattedDollar(reqLine.getUnitPrice(), oid);
				BigDecimal b_um_factor = HiltonUtility.getFormattedDollar(reqLine.getUmFactor(), oid);
				BigDecimal b_extended_cost = HiltonUtility.getFormattedDollar(b_quantity.multiply(b_unit_price).multiply(b_um_factor), oid);

				List shipToList = (List) reqLine.getShipToList();
				List billToList = (List) reqLine.getBillToList();
				List accountList = (List) reqLine.getAccountList();
				List commentList = (List) reqLine.getDocCommentList();
%>
						<table border="0" cellspacing="0" cellpadding="4" width="100%" class="browseRow">
<%			if (commentList != null)
				{
					for (int ix = 0; ix < commentList.size(); ix++)
					{
						DocComment docComment = (DocComment) commentList.get(ix);
						DocText docText = docComment.getDocText();

						String s_cmt_title = docComment.getCommentTitle();
						String s_cmt_print = docComment.getCommentPrint();
						String s_cmt_bold = docComment.getCommentBold();
						String s_cmt_place = docComment.getCommentPlace();
						String s_cmt_text = docText.getStdText();

						if (s_cmt_place.equals("A"))
						{
							continue;
						}
						/*if (s_cmt_print.equals("N"))
						{
							continue;
						}*/
						iBeforeItem++;
%>
						<tsa:tr>
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow">
								<b><tsa:label labelName="req-catalogItemComment" defaultString="Comment" />:&nbsp;</b>
								<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
							</tsa:td>
						</tsa:tr>
<%				}
				} %>
						<tsa:tr>
							<tsa:td field="req-lineNumber" width="7%" align="right" noWrap="nowrap" cssClass="browseRow" ><%=i+1%></tsa:td>
							<tsa:td field="req-itemNumber" width="18%"  noWrap="nowrap" cssClass="browseRow"><%=reqLine.getItemNumber()%></tsa:td>
							<tsa:td field="req-commodity" width="15%"  noWrap="nowrap" cssClass="browseRow"><%=reqLine.getCommodityCode()%></tsa:td>
							<tsa:td field="req-quantity" width="10%" align="right" noWrap="nowrap" cssClass="browseRow"><%=HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid)%></tsa:td>
							<tsa:td field="req-uom" width="10%"  noWrap="nowrap" cssClass="browseRow"><%=reqLine.getUmCode()%></tsa:td>
							<tsa:td field="req-extendedCost" width="15%" align="right" noWrap="nowrap" cssClass="browseRow"><%=b_extended_cost%></tsa:td>
							<tsa:td field="req-discountAmount" width="12%" align="right" noWrap="nowrap" cssClass="browseRow"><%=HiltonUtility.getFormattedDollar(reqLine.getDiscountAmount(), oid)%></tsa:td>
							<tsa:td field="req-lineStatus" width="13%" noWrap="nowrap" cssClass="browseRow" ><%=DocumentStatus.toString(reqLine.getStatus(), oid)%></tsa:td>
						</tsa:tr>
						<tsa:tr>
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><%=reqLine.getDescription()%></tsa:td>
						</tsa:tr>
<%
            List attachmentItemList = (List) reqLine.getDocAttachmentList();
            boolean flagAttachmentItem = false;
            if (attachmentItemList != null)
			{
					for (int ix = 0; ix < attachmentItemList.size(); ix++)
					{
						DocAttachment docAttachmentItem = (DocAttachment) attachmentItemList.get(ix);
						String	filename = docAttachmentItem.getDocFilename();
						String	ext = filename.substring(filename.lastIndexOf(".") + 1);
						%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td class=browseRow align="right">
							<%if(!flagAttachmentItem){%>
								<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-catalogItemAttachment", "Attachments")%>:&nbsp;</b>
								<%flagAttachmentItem=true;
							}%>

							<input align="left" type=hidden name="docFilename" value = "<%=filename%>">
<%		if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX")) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>');void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX")) {%>
							<a href="javascript: oopenAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("EML")) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/email.gif" border=0 alt="Open Attached Email"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT") || ext.equalsIgnoreCase("PPTX")) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
						</td>
							<td nowrap>
								<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);" title="Click here to Open the file"><%=docAttachmentItem.getDocTitle()%></a>
							</td>
						</tr>
<%
					}
			} %>
<%				if (accountList != null)
				{
					BigDecimal bd_total_perc = new BigDecimal(0.00);
					BigDecimal bd_total_amt = new BigDecimal(0.00);

					for (int ix = 0; ix < accountList.size(); ix++)
					{
						Account account = (Account) accountList.get(ix);

						BigDecimal bd_alloc_perc = account.getAllocPercent();
						BigDecimal bd_alloc_amt = account.getAllocAmount();

						if (bd_alloc_perc == null)	{	bd_alloc_perc = new BigDecimal(0);	}
						if (bd_alloc_amt == null)	{	bd_alloc_amt = new BigDecimal(0);	}

						bd_total_perc = bd_total_perc.add(bd_alloc_perc);
						bd_total_amt = bd_total_amt.add(bd_alloc_amt);

						String	s_account = "";
						String	s_accArray[] = new String[15];

						s_accArray[0] = account.getFld1();
						s_accArray[1] = account.getFld2();
						s_accArray[2] = account.getFld3();
						s_accArray[3] = account.getFld4();
						s_accArray[4] = account.getFld5();
						s_accArray[5] = account.getFld6();
						s_accArray[6] = account.getFld7();
						s_accArray[7] = account.getFld8();
						s_accArray[8] = account.getFld9();
						s_accArray[9] = account.getFld10();
						s_accArray[10] = account.getFld11();
						s_accArray[11] = account.getFld12();
						s_accArray[12] = account.getFld13();
						s_accArray[13] = account.getFld14();
						s_accArray[14] = account.getFld15();

						for (int j = 0; j <15; j++)
						{
							if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
							{
								if (s_account.length() > 0)
								{
									s_account = s_account + s_account_separator + s_accArray[j];
								}
								else
								{
									s_account = s_accArray[j];
								}
							}
						}
%>
						<tsa:tr>
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><b><tsa:label labelName="account" defaultString="Account" />:</b>&nbsp;<%=s_account%>&nbsp;&nbsp;&nbsp;$<%=HiltonUtility.getFormattedDollar(bd_alloc_amt, oid)%></tsa:td>
						</tsa:tr>
<%					}
				}

				if (!(reqLine.getRequisitionNumber().equalsIgnoreCase(requisitionHeader.getRequisitionNumber())) || (!reqLine.getRequisitionerCode().equals(requisitionHeader.getRequisitionerCode()) && !HiltonUtility.isEmpty(reqLine.getRequisitionerCode())))
				{	%>
		            <tsa:tr>
		              <tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
<%					if (!reqLine.getRequisitionNumber().equals(requisitionHeader.getRequisitionNumber())) {%>
		              <tsa:td field="ln-req-requisitionNumber" colspan="4" cssClass="browseRow" >
		              <b><tsa:label labelName="ln-req-requisitionNumber" defaultString="Requisition #" />:</b>
		              &nbsp;<%=headerEncoder.encodeForHTML(reqLine.getRequisitionNumber())%>
		              </tsa:td>
<%					}
					if (!reqLine.getRequisitionerCode().equals(requisitionHeader.getRequisitionerCode()) && !HiltonUtility.isEmpty(reqLine.getRequisitionerCode())) {%>
		              <tsa:td field="ln-req-requisitionerName" colspan="4" cssClass="browseRow" >
		              <b><tsa:label labelName="ln-req-requisitionerName" defaultString="Requisitioner Name" />:</b>&
		              nbsp;<%=UserManager.getInstance().getUser(oid, reqLine.getRequisitionerCode()).getDisplayName()%>
		                </tsa:td>
<%					}%>
		            </tsa:tr>
<%				}
				if (reqLine != null) {
					List inspList = reqLine.getInspectionList();
					if (inspList != null && !inspList.isEmpty()) {
					%>
					<tr>
							<tsa:td colspan="7" height="18px">
								<table cellpadding="0" cellspacing="0" border="0"  width="100%">

								<td>
								<table cellspacing="0">
								<ul type="square">
							<%
								for (int ix = 0; ix < inspList.size(); ix++) {
								InspectionHeader insp = (InspectionHeader) inspList.get(ix);
								%>
								    <% String inspType = insp.getInspectType() ;
								    		String inspDesc = "Receipt Inspection" ;
								    		if (inspType == null) inspType = "RI" ;
								    		if (inspType.equals("FI"))   {
								    			inspDesc = "Field Inspection" ;
								    		} else if (inspType.equals("GI"))   {
								    			inspDesc = "General Inspection" ;
								    		} else if (inspType.equals("CG"))   {
								    			inspDesc = "CGD Inspection" ;
								    		}
								    %>
									<li>
										<%=HiltonUtility.ckNull(inspDesc) %>&nbsp;&nbsp;&nbsp; <%=HiltonUtility.ckNull(insp.getStandardCode())%>
									</li>
										<ul>
									<%	if (insp != null)
										{
											List inspLineLi	= (List) insp.getInspectionLineList();
											if (inspLineLi != null) {
												for (int j = 0; j < inspLineLi.size(); j++)
												{
														InspectionLine inspLine = (InspectionLine) inspLineLi.get(j) ;
									%>
														<li>&nbsp;<%=HiltonUtility.ckNull(inspLine.getCritNo())%>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=HiltonUtility.ckNull(inspLine.getInspectCode())%>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=HiltonUtility.ckNull(inspLine.getCritDescription())%></li>
														<br></br>
														<br>
									<%			}
											}
										}
										if (insp.getInspectionLineList() != null && insp.getInspectionLineList().size() > 0) {
										} else {%>
													<li><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noInspectionsItem", "There are no inspection records for this item")%>.</b></li>
													<br></br>
													<br>
										<%}%>
										</ul>
								<% }%>
								</ul>
								</table>
								</td>
								</table>
						</tsa:td>
					</tr>
					<% 		}
				}
					iRow++;

				if (commentList != null)
				{
					for (int ix = 0; ix < commentList.size(); ix++)
					{
						DocComment docComment = (DocComment) commentList.get(ix);
						DocText docText = docComment.getDocText();

						String s_cmt_title = docComment.getCommentTitle();
						String s_cmt_print = docComment.getCommentPrint();
						String s_cmt_bold = docComment.getCommentBold();
						String s_cmt_place = docComment.getCommentPlace();
						String s_cmt_text = docText.getStdText();

						if (s_cmt_place.equals("B"))
						{
							continue;
						}
						/*if (s_cmt_print.equals("N"))
						{
							continue;
						}*/
						iAfterItem++;
%>
						<tsa:tr>
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow">
								<b><tsa:label labelName="req-catalogItemComment" defaultString="Comment" />:&nbsp;</b>
								<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
							</tsa:td>
						</tsa:tr>
<%				}
				}

				if (shipToList != null)
				{
					for (int ix = 0; ix < shipToList.size(); ix++)
					{
						ShipTo shipTo = (ShipTo) shipToList.get(ix);
						ShipToPK shipToPK = shipTo.getComp_id();
						String s_shp_attn = shipTo.getAttention();

						String	s_ship_address_line_1 = "";
						String	s_ship_address_line_2 = "";
						String	s_ship_address_line_3 = "";
						String	s_ship_address_line_4 = "";
						String	s_ship_city = "";
						String	s_ship_state = "";
						String	s_ship_postal_code = "";
						String	s_ship_country = "";

						Address shipToAddress = (Address) shipTo.getShipToAddress();
						if (shipToAddress != null)
						{
							s_ship_address_line_1 = shipToAddress.getAddressLine1();
							s_ship_address_line_2 = shipToAddress.getAddressLine2();
							s_ship_address_line_3 = shipToAddress.getAddressLine3();
							s_ship_address_line_4 = shipToAddress.getAddressLine4();
							s_ship_city = shipToAddress.getCity();
							s_ship_state = shipToAddress.getState();
							s_ship_postal_code = shipToAddress.getPostalCode();
							s_ship_country = shipToAddress.getCountry();
						}
%>
						<tsa:tr><tsa:td colspan="8"><br></tsa:td></tsa:tr>
						<tsa:tr>
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><tsa:label labelName="ship" defaultString="Ship" /><b><%=HiltonUtility.getFormattedQuantity(shipTo.getQuantity(), oid)%></b><tsa:label labelName="to" defaultString="to" />:</tsa:td>
						</tsa:tr>
						<tsa:tr field="req-shipToCode">
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><b><%=shipToPK.getShipToCode()%></b></tsa:td>
						</tsa:tr>
<%					if (!HiltonUtility.isEmpty(s_ship_address_line_1))
						{ %>
						<tsa:tr field="req-shp-addressLine1">
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><%=s_ship_address_line_1%></tsa:td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_ship_address_line_2))
						{ %>
						<tsa:tr field="req-shp-addressLine2">
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><%=s_ship_address_line_2%></tsa:td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_ship_address_line_3))
						{ %>
						<tsa:tr field="req-shp-addressLine3">
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><%=s_ship_address_line_3%></tsa:td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_ship_address_line_4))
						{ %>
						<tsa:tr field="req-shp-addressLine4">
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><%=s_ship_address_line_4%></tsa:td>
						</tsa:tr>
<%					}%>
						<tsa:tr>
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></tsa:td>
						</tsa:tr>
<%
						if (!HiltonUtility.isEmpty(s_ship_country))
						{ %>
						<tsa:tr field="req-shp-country">
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><%=s_ship_country%></tsa:td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_shp_attn))
						{ %>
						<tsa:tr field="req-shp-attention">
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><tsa:label labelName="shp-attention" defaultString="Attn" />: <%=s_shp_attn%></tsa:td>
						</tsa:tr>
<%					} %>

						<tsa:tr field="req-requiredDate">
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><tsa:label labelName="req-requiredDate" defaultString="Required Date" />: <%=HiltonUtility.getFormattedDate(shipTo.getShipDate(), oid, userDateFormat)%></tsa:td>
						</tsa:tr>
<%				}
				}

				if (billToList != null)
				{
					for (int ix = 0; ix < billToList.size(); ix++)
					{
						BillTo billTo = (BillTo) billToList.get(ix);
						BillToPK billToPK = billTo.getComp_id();
						String s_bill_attn = billTo.getAttention();

						String	s_bill_address_line_1 = "";
						String	s_bill_address_line_2 = "";
						String	s_bill_address_line_3 = "";
						String	s_bill_address_line_4 = "";
						String	s_bill_city = "";
						String	s_bill_state = "";
						String	s_bill_postal_code = "";
						String	s_bill_country = "";

						Address billToAddress = (Address) billTo.getBillToAddress();
						if (billToAddress != null)
						{
							s_bill_address_line_1 = billToAddress.getAddressLine1();
							s_bill_address_line_2 = billToAddress.getAddressLine2();
							s_bill_address_line_3 = billToAddress.getAddressLine3();
							s_bill_city = billToAddress.getCity();
							s_bill_state = billToAddress.getState();
							s_bill_postal_code = billToAddress.getPostalCode();
							s_bill_country = billToAddress.getCountry();
						}
%>
						<tsa:tr><tsa:td colspan="8"><br></tsa:td></tsa:tr>
						<tsa:tr>
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><tsa:label labelName="bill" defaultString="Bill" /><b><%=HiltonUtility.getFormattedQuantity(billTo.getQuantity(), oid)%></b><tsa:label labelName="to" defaultString="to" /> :</tsa:td>
						</tsa:tr>
						<tsa:tr field="req-billToCode">
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><b><%=billToPK.getBillToCode()%></b></tsa:td>
						</tsa:tr>
<%					if (!HiltonUtility.isEmpty(s_bill_address_line_1))
						{ %>
						<tsa:tr field="req-bil-addressLine1">
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><%=s_bill_address_line_1%></tsa:td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_bill_address_line_2))
						{ %>
						<tsa:tr field="req-bil-addressLine1bil-addressLine2">
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><%=s_bill_address_line_2%></tsa:td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_bill_address_line_3))
						{ %>
						<tsa:tr field="req-bil-addressLine3">
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><%=s_bill_address_line_3%></tsa:td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_bill_address_line_4))
						{ %>
						<tsa:tr field="req-bil-addressLine4">
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><%=s_bill_address_line_4%></tsa:td>
						</tsa:tr>
<%					}%>
						<tsa:tr>
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><%=s_bill_city%>&nbsp;<%=s_bill_state%>&nbsp;<%=s_bill_postal_code%></tsa:td>
						</tsa:tr>
<%
						if (!HiltonUtility.isEmpty(s_bill_country))
						{ %>
						<tsa:tr field="req-bil-country">
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><%=s_bill_country%></tsa:td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_bill_attn))
						{ %>
						<tsa:tr field="req-bil-attention">
							<tsa:td cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
							<tsa:td colspan="7" cssClass="browseRow"><tsa:label labelName="req-bil-attention" defaultString="Attn" />: <%=s_bill_attn%></tsa:td>
						</tsa:tr>
<%					} %>
<%				}
				} %>
						<tsa:tr><tsa:td colspan="8"><hr></tsa:td></tsa:tr>
						</table>
<%		} %>
					</tsa:td>
				</tsa:tr>
				</table>

<%			if (processMap.containsKey("HEADER_TOTALS")) { %>
				<table border="0" cellspacing="0" cellpadding="0" width="100%" class="browseRow">
				<tsa:tr>
					<tsa:td>
						<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">

						<tsa:tr field="req-subtotal">
							<tsa:td width="72%" cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;<tsa:label labelName="req-subtotal" defaultString="Subtotal" />:</tsa:td>
							<tsa:td width="13%" cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getFormattedDollar(requisitionHeader.getSubtotal(), oid)%></tsa:td>
							<tsa:td width="15%" cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;</tsa:td>
						</tsa:tr>

						<tsa:tr field="req-discountAmount">
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;<tsa:label labelName="req-discountAmount" defaultString="Discount" />:</tsa:td>
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getFormattedDollar(requisitionHeader.getDiscountAmount(), oid)%></tsa:td>
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;</tsa:td>
						</tsa:tr>

						<tsa:tr field="req-taxAmount">
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;<tsa:label labelName="req-taxAmount" defaultString="Tax" />:</tsa:td>
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getFormattedDollar(requisitionHeader.getTaxAmount(), oid)%></tsa:td>
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;</tsa:td>
						</tsa:tr>

						<tsa:tr field="req-shippingCharges">
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;<tsa:label labelName="req-shippingCharges" defaultString="Shippint" />:</tsa:td>
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getFormattedDollar(requisitionHeader.getShippingCharges(), oid)%></tsa:td>
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;</tsa:td>
						</tsa:tr>

						<tsa:tr field="req-shippingTaxAmount">
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;<tsa:label labelName="req-shippingTaxAmount" defaultString="Shipping Tax" />:</tsa:td>
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getFormattedDollar(requisitionHeader.getShippingTaxAmt(), oid)%></tsa:td>
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;</tsa:td>
						</tsa:tr>

						<tsa:tr field="req-otherCharges">
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;<tsa:label labelName="req-otherCharges" defaultString="Other" />:</tsa:td>
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getFormattedDollar(requisitionHeader.getOtherCharges(), oid)%></tsa:td>
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;</tsa:td>
						</tsa:tr>

						<tsa:tr field="req-otherTaxAmount">
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;<tsa:label labelName="req-otherTaxAmount" defaultString="Other Tax" />:</tsa:td>
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getFormattedDollar(requisitionHeader.getOtherTaxAmount(), oid)%></tsa:td>
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;</tsa:td>
						</tsa:tr>

						<tsa:tr field="req-total">
							<tsa:td cssClass="processOn" noWrap="nowrap" align="right"><b><tsa:label labelName="req-total" defaultString="Total" />:</b></tsa:td>
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getFormattedDollar(requisitionHeader.getTotal(), oid)%></tsa:td>
							<tsa:td cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;</tsa:td>
						</tsa:tr>

						<tsa:tr field="req-estCost" >
							<tsa:td cssClass="processOn" field="req-estCost" noWrap="nowrap" align="right"><b><tsa:label labelName="req-estCost" defaultString="Total" checkRequired="false" />:</b></tsa:td>
							<tsa:td cssClass="browseRow" field="req-estCost" noWrap="nowrap" align="right"><%=HiltonUtility.getFormattedDollar(requisitionHeader.getEstimatedCost(), oid)%></tsa:td>
							<td class=browseRow nowrap align=right>&nbsp;</td>
						</tsa:tr>
						</table>
					</tsa:td>
				</tsa:tr>
				</table>
<%			} %>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>
<%	} %>

<br>

<%	if (processMap.containsKey("HEADER_NOTES")) { %>
<div id="commentAfter" style="display: none;">
<table border="0" cellpadding="0" cellspacing="0" width="660px">
<tsa:tr>
	<tsa:td align="center" width="660px">
		<table border="0" cellspacing="0" cellpadding="0" width="650px" class="browseHdr">
		<tsa:tr>
			<tsa:td>
				<table border="1" cellspacing="0" cellpadding="0" width="650px" class="browseHdr">
				<tsa:tr>
					<tsa:td>
						<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdr">
						<tsa:tr>
							<tsa:td width="100%" colspan="2" height="18px" cssClass="browseHdr">&nbsp;<b><tsa:label labelName="req-comment" defaultString="Comment"></tsa:label></b></tsa:td>
						</tsa:tr>
						</table>
					</tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td>
<%
			List cmtList = (List) requisitionHeader.getDocCommentList();
			if (cmtList != null)
			{
				for(int i = 0; i < cmtList.size(); i++)
				{
					DocComment docComment = (DocComment) cmtList.get(i);
					DocText docText = docComment.getDocText();

					String s_cmt_title = docComment.getCommentTitle();
					String s_cmt_print = docComment.getCommentPrint();
					String s_cmt_bold = docComment.getCommentBold();
					String s_cmt_place = docComment.getCommentPlace();
					String s_cmt_text = docText.getStdText();

					if (s_cmt_place.equals("B"))
					{
						continue;
					}
					/*if (s_cmt_print.equals("N"))
					{
						continue;
					}*/
					iAfter++;
%>
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
				<tsa:tr>
					<tsa:td width="100%"  colspan="2" cssClass="browseRow">
<%				if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_title%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td width="50px">&nbsp;</tsa:td>
					<tsa:td width="100%">
<%				if (s_cmt_bold.equals("Y")) 	{ %>	<b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</tsa:td>
				</tsa:tr>
				</table>
<%			}
			} %>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>
</div>
<%	} %>


<!-- ADDED 2007/01/27 -->
<br><br>
<table border="0" cellpadding="0" cellspacing="0" width="660px">
<tsa:tr>
  <tsa:td align="center" width="660px">
<%
    List scheduleList = (List) requisitionHeader.getScheduleList();
      int si = 0;
      String typeOfSchedule= "";
      String lastTypeOfSchedule= "";
      if (scheduleList != null) {
        for(int i = 0; i < scheduleList.size(); i++) {
          Schedule schedule = (Schedule) scheduleList.get(i);
          typeOfSchedule = schedule.getComp_id().getScheduleType();

          //if (schedule == null) {
          //  continue;
          //}
          si++;
%>
    <table border="0" cellspacing="0" cellpadding="0" width="650px" class="browseHdr">
<%

          if(!typeOfSchedule.equals(lastTypeOfSchedule)) {
%>
    <tsa:tr>
      <tsa:td>
        <table border="1" cellspacing="0" cellpadding="0" width="650px" class="browseHdr">
        <tsa:tr>
          <tsa:td>
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdr">
            <tsa:tr>
              <tsa:td width="55%" height="18px" cssClass="browseHdr">&nbsp;<b><%=ScheduleType.toString(typeOfSchedule, oid)%></b></tsa:td>
              <tsa:td width="15%" height="18px" cssClass="browseHdr" align="center">&nbsp;<b><tsa:label labelName="po-scheduleDate" defaultString="Schedule" /></b></tsa:td>
              <tsa:td width="15%" height="18px" cssClass="browseHdr" align="center">&nbsp;<b><tsa:label labelName="po-revisedDate" defaultString="Revised" /></b></tsa:td>
              <tsa:td width="15%" height="18px" cssClass="browseHdr" align="center">&nbsp;<b><tsa:label labelName="po-completionDate" defaultString="Completion" /></b></tsa:td>
            </tsa:tr>
            </table>
          </tsa:td>
        </tsa:tr>
        </table>
      </tsa:td>
    </tsa:tr>
<%
          }
%>
    <tsa:tr>
      <tsa:td>
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tsa:tr>
          <tsa:td width="55%" cssClass="browseRow"><%=(schedule.getDescription() == null)?"":String.valueOf(schedule.getDescription())%></tsa:td>
          <tsa:td width="15%" cssClass="browseRow" align="center" valign="top"><%=(schedule.getScheduleDate() == null)?"":String.valueOf(schedule.getScheduleDate())%></tsa:td>
		  <tsa:td width="15%" cssClass="browseRow" align="center" valign="top"><%=(schedule.getRevisedDate() == null)?"":String.valueOf(schedule.getRevisedDate())%></tsa:td>
          <tsa:td width="15%" cssClass="browseRow" align="center" valign="top"><%=(schedule.getCompletionDate() == null)?"":String.valueOf(schedule.getCompletionDate())%></tsa:td>
        </tsa:tr>
        </table>
      </tsa:td>
    </tsa:tr>

    </table>

<%
		lastTypeOfSchedule = schedule.getComp_id().getScheduleType();
        }
      }
%>
  </tsa:td>
</tsa:tr>
</table>
<!-- END ADDED 2007/01/27 -->

<br>

<table border="0" cellpadding="0" cellspacing="0" width="660px">
<tsa:tr>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: printMe(); void(0);">Print</a></div></tsa:td>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: closeMe(); void(0);">Close</a></div></tsa:td>
</tsa:tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value="JavaScript">
<!-- Hide script

	frm = document.purchaseform;
	var netscape  = "";
	var ver = navigator.appVersion;
	var len = ver.length;

	for(iln = 0; iln < len; iln++) {
		if (ver.charAt(iln) == "(") break;
	}
	netscape = (ver.charAt(iln+1).toUpperCase() != "C");

	window.onfocus = displayButtons; // displayButtons on window.onfocus

	if (netscape) window.captureEvents(window.onfocus);

<%	if (iBefore > 0) { %>
			displayArea('commentBefore');
<%	}
		if (iAfter > 0) { %>
			displayArea('commentAfter');
<%	} %>

	function printMe() {
		hideArea("buttons");

		this.print();
	}

	function closeMe() {
		window.top.hidePopWin();
	}

	function displayButtons() {
		displayArea("buttons");
	}

	function openDocument(row)
	{
		var filename = "";
		if (document.all("docFilename").length > 1)
		{
			filename = frm.docFilename[row].value;
		}
		else
		{
			filename = frm.docFilename.value;
		}
		openAttachment(filename);
	}

// End Hide script -->
</SCRIPT>