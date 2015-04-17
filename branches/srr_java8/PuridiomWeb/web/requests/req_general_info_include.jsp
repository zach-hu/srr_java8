<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>
<%@ page import="com.tsa.puridiom.common.utility.TokenProcessor" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.organization.OrganizationManager" %>
<%@ page import="com.tsa.puridiom.usermanager.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.Dates" %>
<%@ page import="com.tsagate.html.*" %>
<%@ page import="com.tsagate.properties.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%
String	oid = (String) request.getAttribute("organizationId");
pageContext.setAttribute("oid", oid);
RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
String poStatus = "";
PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
if(poHeader == null)
{
	poHeader = new PoHeader();
}
else
{
	poStatus = HiltonUtility.ckNull(poHeader.getStatus());
}
String	s_date_format = propertiesManager.getProperty("MISC", "DateFormat", "MM-dd-yyyy");
String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
String  s_udf2_for_type = propertiesManager.getProperty("REQ OPTIONS", "ALLOW FOR TYPE UDF2", "");
String  udf_field_depencies = propertiesManager.getProperty("REQ OPTIONS", "GENERAL INFO FIELD DEPENDENCIES", "N");
String  auto_award_allreq = propertiesManager.getProperty("AUTOAWARD","AUTOAWARDALLREQ","N");
String  auto_award_typepo = propertiesManager.getProperty("REQ OPTIONS","AUTOAWARDTYPEPO","");
BigDecimal bd_ic_req_header = requisitionHeader.getIcReqHeader();
String icHeaderHistoryEdit = requisitionHeader.getIcHeaderHistory().toString();
boolean fdcsEnabled = propertiesManager.getProperty("FDCS","Enabled","N").equalsIgnoreCase("Y") ;

String	s_req_number = requisitionHeader.getRequisitionNumber();
String	s_req_type = requisitionHeader.getRequisitionType();
String	s_req_subtype = requisitionHeader.getRqSubType();
String	s_req_status = requisitionHeader.getStatus();
String	s_fiscal_year = requisitionHeader.getFiscalYear();
String	s_requisitioner_code = requisitionHeader.getRequisitionerCode();
String	s_owner_code = requisitionHeader.getOwner();
UserProfile requisitioner = UserManager.getInstance().getUser(oid, s_requisitioner_code);
UserProfile owner = UserManager.getInstance().getUser(oid, s_owner_code);
String	s_department_code = requisitionHeader.getDepartmentCode();
String	s_authorization_code = requisitionHeader.getAuthorizationCode();
UserProfile authby = UserManager.getInstance().getUser(oid, s_authorization_code);
UserProfile buyer = UserManager.getInstance().getUser(oid, requisitionHeader.getBuyer());
String	s_language = requisitionHeader.getLanguage();
String	s_receipt_required = requisitionHeader.getReceiptRequired();
String s_udf_1_code = requisitionHeader.getUdf1Code();
String s_udf_14_code = requisitionHeader.getUdf14Code();
String s_gfp_gfm = requisitionHeader.getGfpGfm();
String s_request_cat = requisitionHeader.getRequestCat();
String s_kit = requisitionHeader.getKit();
List chargeCodeList = (List) request.getAttribute("chargeCodeList");
%>
<div name="req_general_info_include" style="visibility: visible;">
				<tr>
		<%	if (!s_req_type.equals("K")) { %>
					<tsa:td id="rq05LabelRow" field="req-RQ05" align="right" noWrap="nowrap" >
		<%		if (DictionaryManager.isLink(oid, "req-RQ05")) {	%>
								<a href="javascript: browseStd('RequisitionHeader_udf5Code', 'RQ05'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ05' defaultString='UDF5' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ05" defaultString="UDF5" noinstance="true" checkRequired="true"/>:</a>&nbsp;
		<%		} else {	%>
								<tsa:label labelName="req-RQ05" defaultString="UDF5" checkRequired="true" noinstance="yes"/>:&nbsp;
		<%		}	%>
					</tsa:td>
          			<tsa:td id="rq05FieldRow" field="req-RQ05" >
						<tsa:input id="leftPanel" type="mintext" title="" name="RequisitionHeader_udf5Code" tabIndex="13" maxLength="15" value="<%=requisitionHeader.getUdf5Code()%>" onchange="upperCase(this);hideRightPanel();"/>
          			</tsa:td>
          			<% } %>
					<tsa:td id="OSVR_label" field="req-RQ06" align="right" noWrap="nowrap">
			<%		if (DictionaryManager.isLink(oid, "req-RQ06")) {	%>
									<a href="javascript: browseStd('RequisitionHeader_udf6Code', 'RQ06'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ06' defaultString='UDF6' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ06" defaultString="UDF6" noinstance="true" checkRequired="true"/>:</a>&nbsp;
			<%		} else {	%>
					<tsa:label labelName="req-RQ06" defaultString="UDF6" checkRequired="true" noinstance="yes"/>:&nbsp;
			<%		}	%>
					</tsa:td>
					<%	if (!s_req_type.equals("K")) { %>
					<tsa:td id="OSVR_input" field="req-RQ06">
					<tsa:input type="mintext" title=""  name="RequisitionHeader_udf6Code" tabIndex="23" maxLength="60" value="<%=requisitionHeader.getUdf6Code()%>" onchange="upperCase(this);"/>
					</tsa:td>
					<%		}	%>
				</tr>
				<tr>
          <tsa:td field="req-priority" align="right" noWrap="nowrap">
          	<tsa:label labelName="req-priority" defaultString="priorityCode" checkRequired="true" noinstance="yes" fieldName="RequisitionHeader_priorityCode"/>:&nbsp;
          </tsa:td>
          <tsa:td field="req-priority" >
          	<tsa:input type="mintext" name="RequisitionHeader_priorityCode" value="<%=requisitionHeader.getPriorityCode()%>" />
          </tsa:td>
          <%	if (!s_req_type.equals("K")) { %>
          <tsa:td id="rq07LabelRow" field="req-RQ07" align="right" noWrap="nowrap" >
<%		if (DictionaryManager.isLink(oid, "req-RQ07")) {	%>
						<a href="javascript: browseStd('RequisitionHeader_udf7Code', 'RQ07'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ07' defaultString='UDF7' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ07" defaultString="UDF7" noinstance="true" checkRequired="true"/>:</a>&nbsp;
<%		} else {	%>
						<tsa:label labelName="req-RQ07" defaultString="UDF7" checkRequired="true" noinstance="yes"/>:&nbsp;
<%		}	%>
					</tsa:td>
					<tsa:td id="rq07FieldRow" field="req-RQ07" >
			        <tsa:input type="yesnoradio" title="" name="RequisitionHeader_udf7Code" value="<%=requisitionHeader.getUdf7Code()%>" onchange="upperCase(this);"/>
			        </tsa:td>
			        <%} %>
				</tr>

				<tr>
					<%	if (!s_req_type.equals("K")) { %>
					<tsa:td field="req-bidWaiver" align="right" noWrap="nowrap" >
					<a href="javascript: browseStd('RequisitionHeader_bidWaiver', 'BIDW'); void(0);" title="Click here to choose the <tsa:label labelName='req-bidWaiver' defaultString='Bid Waiver' noinstance='true' /> code for this requisition or enter the <tsa:label labelName='req-bidWaiver' defaultString='Bid Waiver' noinstance='true' /> code in the box on the right."> <tsa:label labelName="req-bidWaiver" defaultString="Bid Waiver" noinstance="true" checkRequired="true" />:</a>&nbsp;
					</tsa:td>
					<tsa:td field="req-bidWaiver" >
					<tsa:input type="mintext" title="Enter bid waiver code" name="RequisitionHeader_bidWaiver" tabIndex="19" maxLength="15" value="<%=requisitionHeader.getBidWaiver()%>" onchange="upperCase(this);"/>
					</tsa:td>
					<%} if (s_req_type.equals("M")) { %>
			          <tsa:td colspan="2" noWrap="nowrap">&nbsp;</tsa:td>
			        <% } %>
					<%	if (!s_req_type.equals("K")) { %>
					<tsa:td field="req-currency" align="right" noWrap="nowrap" >
					<a href="javascript: browseLookup('RequisitionHeader_currencyCode', 'curr_code'); void(0);" title="Click here to choose the <tsa:label labelName='req-currency' defaultString='Currency' noinstance='true' /> for this requisition or enter the <tsa:label labelName='req-currency' defaultString='Currency' noinstance='true' /> in the box on the right."> <tsa:label labelName="req-currency" defaultString="Currency" noinstance="true" checkRequired="true" />:</a>&nbsp;
					</tsa:td>
					<tsa:td field="req-currency" >
					<tsa:input type="mintext" title="Enter type of Currency" name="RequisitionHeader_currencyCode" tabIndex="19" maxLength="15" value="<%=requisitionHeader.getCurrencyCode()%>" onchange="upperCase(this); currencyChangeWarning(this.value);"/>
					</tsa:td>
				<%	} %>
					<tsa:td id="rq08LabelRow" field="req-RQ08" align="right" noWrap="nowrap" >
						<%		if (DictionaryManager.isLink(oid, "req-RQ08")) {	%>
								<% if (fdcsEnabled) { %>
									<a href="javascript: browseFdcsWOElements('FdcWo_RequisitionHeader_udf8Code'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ08' defaultString='UDF8' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ08" defaultString="UDF8" noinstance="true" checkRequired="true"/>:</a>&nbsp;
								<% } else { %>
									<a href="javascript: browseStd('RequisitionHeader_udf8Code', 'RQ08'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ08' defaultString='UDF8' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ08" defaultString="UDF8" noinstance="true" checkRequired="true"/>:</a>&nbsp;
								<% } %>
						<%		} else {	%>
						<tsa:label labelName="req-RQ08" defaultString="UDF8" checkRequired="true" noinstance="yes"/>:&nbsp;
						<%		}	%>
					</tsa:td>

					<tsa:td id="rq08FieldRow" field="req-RQ08" >
					<tsa:input type="mintext" title=""  name="RequisitionHeader_udf8Code" tabIndex="24" maxLength="15" value="<%=requisitionHeader.getUdf8Code()%>" onchange="upperCase(this);configureFieldsUDF();"/>
					</tsa:td>
				</tr>

				<tr>
					<tsa:td id="rq09LabelRow" field="req-RQ09" align="right" noWrap="nowrap" >
					<%	if (DictionaryManager.isLink(oid, "req-RQ09")) {	%>
						<% if (fdcsEnabled) { %>
						<a href="javascript: browseFdcsWOElements('FdcWo_RequisitionHeader_udf9Code'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ09' defaultString='UDF9' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ09" defaultString="UDF9" noinstance="true" checkRequired="true"/>:</a>&nbsp;
						<% } else { %>
						<a href="javascript: <% if (oid.equalsIgnoreCase("bsc04p")) {%>browseLookup('RequisitionHeader_udf9Code', 'approver');<%} else {%>browseStd('RequisitionHeader_udf9Code', 'RQ09');<%}%> void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ09' defaultString='UDF9' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ09" defaultString="UDF9" noinstance="true" checkRequired="true"/>:</a>&nbsp;
						<% } %>
					<%	} else {	%>
					<tsa:label labelName="req-RQ09" defaultString="UDF9" checkRequired="true" noinstance="yes"/>:&nbsp;
					<%	}	%>
					</tsa:td>

					<tsa:td id="rq09FieldRow" field="req-RQ09" >
					<tsa:input type="mintext" title="" name="RequisitionHeader_udf9Code" tabIndex="15" maxLength="15" value="<%=requisitionHeader.getUdf9Code()%>" onchange="upperCase(this);"/>
					</tsa:td>

					<% if (s_req_type.equals("M")) { %>
			        	<tsa:td colspan="2" noWrap="nowrap">&nbsp;</tsa:td>
			        <% } %>
					<tsa:td id="SSD_label" field="req-servicesStartDate" align="right" noWrap="nowrap" >
					<tsa:label labelName="req-servicesStartDate" defaultString="Services Start Date"  docType="s_req_type" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td id="SSD_input" field="req-servicesStartDate" noWrap="nowrap">
						<tsa:input type="mintext" title="Enter Services Start Date" name="RequisitionHeader_servicesStartDate" tabIndex="29" value="<%=HiltonUtility.getFormattedDate(requisitionHeader.getServicesStartDate(), oid)%>"/>
						<a href="javascript: show_calendar('RequisitionHeader_servicesStartDate', '<%=s_date_format%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign="bottom" border="0"></a>
					</tsa:td>
				</tr>

				<tr>
					<tsa:td id="rq11LabelRow" field="req-RQ11" align="right" noWrap="nowrap" >
						<%	if (DictionaryManager.isLink(oid, "req-RQ11")) {	%>
								<% if (fdcsEnabled) { %>
									<a href="javascript: browseFdcsWOElements('FdcWo_RequisitionHeader_udf11Code'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ011' defaultString='UDF11' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ11" defaultString="UDF11" noinstance="true" checkRequired="true"/>:</a>&nbsp;
								<% } else { %>
									<a href="javascript: browseStd('RequisitionHeader_udf11Code', 'RQ11'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ11' defaultString='UDF11' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ11" defaultString="UDF11" noinstance="true" checkRequired="true"/>:</a>&nbsp;
								<% } %>
						<%	} else {	%>
						<tsa:label labelName="req-RQ11" defaultString="UDF11" checkRequired="true" noinstance="yes"/>:&nbsp;
						<%	}	%>
					</tsa:td>
					<tsa:td id="rq11FieldRow" field="req-RQ11" >
					<tsa:input type="mintext" title="" name="RequisitionHeader_udf11Code" tabIndex="24" maxLength="15" value="<%=requisitionHeader.getUdf11Code()%>" onchange="upperCase(this);"/>
					</tsa:td>

					<tsa:td id="SED_label" field="req-servicesEndDate" align="right" noWrap="nowrap" >
					<tsa:label labelName="req-servicesEndDate" defaultString="Services End Date"  docType="s_req_type" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td id="SED_input" field="req-servicesEndDate" noWrap="nowrap">
						<tsa:input type="mintext" title="Enter Services End Date" name="RequisitionHeader_servicesEndDate" tabIndex="29" value="<%=HiltonUtility.getFormattedDate(requisitionHeader.getServicesEndDate(), oid)%>"/>
						<a href="javascript: show_calendar('RequisitionHeader_servicesEndDate', '<%=s_date_format%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign="bottom" border="0"></a>
					</tsa:td>
				</tr>

				<tr>
					<tsa:td id="rq12LabelRow" field="req-RQ12" align="right" noWrap="nowrap" >
						<%	if (DictionaryManager.isLink(oid, "req-RQ12")) {	%>
						<a href="javascript: browseStd('RequisitionHeader_udf12Code', 'RQ12'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ12' defaultString='UDF12' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ12" defaultString="UDF12" noinstance="true" checkRequired="true"/>:</a>&nbsp;
						<%	} else {	%>
						<tsa:label labelName="req-RQ12" defaultString="UDF12" checkRequired="true" noinstance="yes"/>:&nbsp;
						<%	}	%>
					</tsa:td>

          			<tsa:td id="rq12FieldRow" field="req-rq12" >
          			<tsa:input type="mintext" title="" name="RequisitionHeader_udf12Code" tabIndex="15" maxLength="15" value="<%=requisitionHeader.getUdf12Code()%>" onchange="upperCase(this);"/>
          			</tsa:td>
				</tr>

				<tr>
					<tsa:td field="req-authorizedByName" align="right" noWrap="nowrap" >
					<tsa:label labelName="req-authorizedByName" defaultString="Authorized By Name" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="req-authorizedByName" >
					<tsa:input type="midtext" name="as_authbyName" maxLength="24" value="<%=authby.getDisplayName()%>" onfocus="this.blur();" disabled="disabled"/>
					</tsa:td>
					<%	if (!s_req_type.equals("K")) { %>
					<tsa:td field="req-inventoryLocation" docType="yes" align="right" noWrap="nowrap">
					<a href="javascript: browseLookup('RequisitionHeader_itemLocation', 'item_location'); void(0);" title="Click here to choose the  <tsa:label labelName='req-inventoryLocation' defaultString='Inventory Location' noinstance='true' /> for this requisition or enter the <tsa:label labelName='req-inventoryLocation' defaultString='Inventory Location' noinstance='true' /> in the box on the right."> <tsa:label labelName="req-inventoryLocation" defaultString="Inventory Location" noinstance="true" checkRequired="true"/>:</a>&nbsp;
					</tsa:td>
					<tsa:td field="req-inventoryLocation" docType="yes">
					<tsa:input type="mintext" title="Enter Inventory Location" name="RequisitionHeader_itemLocation" tabIndex="21" maxLength="15" value="<%=requisitionHeader.getItemLocation()%>" onchange="upperCase(this);"/>
					</tsa:td>
					<%	}	%>
				</tr>

				<tr>
					<tsa:td id="rq13LabelRow" field="req-RQ13" align="right" noWrap="nowrap" >
						<%	if (DictionaryManager.isLink(oid, "req-RQ13")) {	%>
						<a href="javascript: browseStdFieldsUDF('RequisitionHeader_udf13Code', 'RQ13'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ13' defaultString='UDF13' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ13" defaultString="UDF13" noinstance="true" checkRequired="true"/>:</a>&nbsp;
						<%	} else {	%>
						<tsa:label labelName="req-RQ13" defaultString="UDF13" checkRequired="true" noinstance="yes"/>:&nbsp;
						<%	}	%>
					</tsa:td>

					<tsa:td id="rq13FieldRow" field="req-RQ13" >
					<tsa:input type="mintext" title="" name="RequisitionHeader_udf13Code" tabIndex="24" maxLength="15" value="<%=requisitionHeader.getUdf13Code()%>" onchange="upperCase(this);"/>
					</tsa:td>

					<tsa:td field="req-RQ14" align="right" noWrap="nowrap" >
						<%	if (DictionaryManager.isLink(oid, "req-RQ14")) {	%>
						<a href="javascript: browseStd('RequisitionHeader_udf14Code', 'RQ14'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ14' defaultString='UDF14' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ14" defaultString="UDF14" noinstance="true" checkRequired="true"/>:</a>&nbsp;
						<%	} else {	%>
						<tsa:label labelName="req-RQ14" defaultString="UDF14" checkRequired="true" noinstance="yes"/>:&nbsp;
						<%	}	%>
					</tsa:td>

          			<tsa:td field="req-RQ14" >
          			<tsa:input type="mintext" title="" name="RequisitionHeader_udf14Code" tabIndex="15" maxLength="15" value="<%=requisitionHeader.getUdf14Code()%>" onchange="upperCase(this);"/>
          			</tsa:td>
				</tr>

				<tr>
					<tsa:td id="rq10LabelRow" field="req-RQ10" align="right" noWrap="nowrap" >
          			<% if(oid.equals("TTR09P")){ %>
							<tsa:label labelName="req-RQ10" defaultString="UDF10" noinstance="true" checkRequired="true"/>:&nbsp;
					<%	}else{ %>
						<%	String rq10Title = "Click here to choose the value for " + DictionaryManager.getLabel(oid, "req-RQ10", "UDF10", false) + " for this requisition or enter the value in the box on the right.";
							if (DictionaryManager.isLink(oid, "req-RQ10")) {	%>
							<a href="javascript: browseStd('RequisitionHeader_udf10Code', 'RQ10'); void(0);" title=" <tsa:label labelName='RQ10-instructions' defaultString='' docType='true'/>"> <tsa:label labelName="req-RQ10" defaultString="" docType="true" checkRequired="true" />:</a>&nbsp;
							<%	} else {	%>
							<tsa:label labelName="req-RQ10" defaultString="" checkRequired="true" noinstance="yes"/>:&nbsp;
						<%	}	%>
					<%	} %>

					</tsa:td>
          			<tsa:td id="rq10FieldRow" field="req-RQ10" >
          			<% if(oid.equals("TTR09P")){ %>
						<select name="RequisitionHeader_udf10Code" tabIndex="11" style="width: 115px" value="<%=requisitionHeader.getUdf1Code()%>" onchange="upperCase(this);configureFieldsUDF();">
							<option value=""></option>
          					<% for (int il = 0; il < chargeCodeList.size(); il++) {
								StdTable stdTable = (StdTable) chargeCodeList.get(il);
								StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
								<option value="<%=stdTablePK.getTableKey()%>" <%if (requisitionHeader.getUdf10Code().equals(stdTablePK.getTableKey())) {%>selected<%}%>><%=stdTable.getTableKey()%></option>
          					<% } %>
          				</select>
					<%	}else{ %>
						<tsa:input type="midtext" title="" name="RequisitionHeader_udf10Code" tabIndex="25" maxLength="15" value="<%=requisitionHeader.getUdf10Code()%>" onchange="upperCase(this);"/>
					<%	}	%>
          			</tsa:td>


					<%	if ( !oid.equalsIgnoreCase("bsc04p") && !oid.equalsIgnoreCase("wpc08p") && !oid.equalsIgnoreCase("SRR10P")){ %>
					<tsa:td field="req-RQ04" align="right" noWrap="nowrap" >
					<%	if (DictionaryManager.isLink(oid, "req-RQ04")) {%>
						<a href="javascript: browseStd('RequisitionHeader_udf4Code', 'RQ04'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ04' defaultString='UDF4' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ04" defaultString="UDF4" noinstance="true" checkRequired="true"/>:</a>&nbsp;
					<%	} else {	%>
						<tsa:label labelName="req-RQ04" defaultString="UDF4" checkRequired="true" noinstance="yes"/>:&nbsp;
					<%	}%>
					</tsa:td>
					<tsa:td id="rq04FieldRow" field="req-RQ04" >
						<tsa:input type="mintext" title="" name="RequisitionHeader_udf4Code" tabIndex="23" maxLength="15" value="<%=requisitionHeader.getUdf4Code()%>" onchange="upperCase(this);"/>
					</tsa:td>
				</tr>

				<tr>
					<tsa:td field="req-receiptOptions" align="right" noWrap="nowrap" >
						<tsa:label labelName="req-receiptOptions" defaultString="Receipt Option" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="req-receiptOptions" >
						<select tabindex="22" name="RequisitionHeader_receiptRequired" size="1" onchange="setReceiptOption();">
							<%if (oid.equalsIgnoreCase("MSG07P")){	%>
							<option <% if (s_receipt_required.equals("E")) {%> SELECTED <%}%> value="E"><tsa:label labelName="req-end-user-receipt" defaultString="End User Receipt"></tsa:label></option>
							<option <% if (s_receipt_required.equals("P")) {%> SELECTED <%}%> value="P"><tsa:label labelName="req-previously-received" defaultString="Previously Received"></tsa:label></option>
							<%-- option <% if (s_receipt_required.equals("R")) {%> SELECTED <%}%> value="R">Receipt Required</option --%>
							<%}
							else {%>
							<%	if (propertiesManager.getProperty("REC SELECTIONS", "X", "Y").equals("Y")) {%>
									<option <% if (s_receipt_required.equals("X") || HiltonUtility.isEmpty(s_receipt_required)) {%> SELECTED <%}%> value=""></option>
							<%	}
								if (propertiesManager.getProperty("REC SELECTIONS", "R", "Y").equals("Y")) {%>
									<option <% if (s_receipt_required.equals("R")) {%> SELECTED <%}%> value="R"><tsa:label labelName="receiptrequired" defaultString="Receipt Required"/></option>
							<%	}
								if (propertiesManager.getProperty("REC SELECTIONS", "P", "Y").equals("Y")) {%>
									<option <% if (s_receipt_required.equals("P")) {%> SELECTED <%}%> value="P"><tsa:label labelName="previouslyreceived" defaultString="Previously Received"/></option>
							<%	}
								if (propertiesManager.getProperty("REC SELECTIONS", "N", "Y").equals("Y")) {%>
									<option <% if (s_receipt_required.equals("N")) {%> SELECTED <%}%> value="N"><tsa:label labelName="noreceiptrequired" defaultString="No Receipt Required"/></option>
							<%	}
								if (propertiesManager.getProperty("REC SELECTIONS", "E", "Y").equals("Y")) {%>
									<option <% if (s_receipt_required.equals("E")) {%> SELECTED <%}%> value="E"><tsa:label labelName="enduserreceipt" defaultString="End User Receipt"/></option>
							<%	}%>
							<%} %>

						</select>
					</tsa:td>
				</tr>
				<tr>
					<%	if (!s_req_type.equals("H")) { %>
					<tsa:td field="req-authorizedBy" align="right" noWrap="nowrap" >
					<%	if (DictionaryManager.isLink(oid, "req-authorizedBy")) {	%>
						<a href="javascript: browseLookup('RequisitionHeader_authorizationCode', 'approver'); void(0);" title="Click here to choose the <tsa:label labelName='req-authorizedBy' defaultString='Authorized By' noinstance='true' /> for this requisition or enter the <tsa:label labelName='req-authorizedBy' defaultString='Authorized By' noinstance='true' /> in the box on the right."> <tsa:label labelName="req-authorizedBy" defaultString="Authorized By" noinstance="true" checkRequired="true" />:</a>&nbsp;
					<%	} else {	%>
						<tsa:label labelName="req-authorizedBy" defaultString="Authorized By" checkRequired="true" noinstance="yes"/>:&nbsp;
					<%	}	%>
					</tsa:td>
					<tsa:td field="req-authorizedBy" >
					<tsa:input type="mintext" title="Enter the name of the person who authorized the requisition" name="RequisitionHeader_authorizationCode" tabIndex="10" maxLength="15" value="<%=s_authorization_code%>" onchange="upperCase(this); getNewInfo('authby', this);"/>
					</tsa:td>
					<%	if (!s_req_type.equals("K")) { %>
					<tsa:td field="req-language" align="right" noWrap="nowrap" >
					<tsa:label labelName="req-language" defaultString="Language" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="req-language" >
						<select name="RequisitionHeader_language" tabindex="20">
						<option value="(Default)" <% if ( s_language.equals("(Default)") ) { %>SELECTED<%}%> ><tsa:label labelName="req-default" defaultString="(Default)"></tsa:label></option>
						<option value="" <% if ( s_language.equals("") ) { %>SELECTED<%}%> ></option>
						</select>
					</tsa:td>
					<%	} %>
					<%	}else { %>
					<tsa:td colspan="4">&nbsp;</tsa:td>
					<%	} %>
				</tr>
<%	}%>
				<tr>
					<tsa:td field="req-RQ15" align="right" noWrap="nowrap" >
						<%	if (DictionaryManager.isLink(oid, "req-RQ15")) {	%>
						<a href="javascript: browseStd('RequisitionHeader_udf15Code', 'RQ15'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ15' defaultString='UDF15' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ15" defaultString="UDF15" noinstance="true" checkRequired="true"/>:</a>&nbsp;
						<%	} else {	%>
						<tsa:label labelName="req-RQ15" defaultString="UDF15" checkRequired="true" noinstance="yes"/>:&nbsp;
						<%	}	%>
					</tsa:td>
					<tsa:td field="req-RQ15" >
					<tsa:input type="mintext" title="" name="RequisitionHeader_udf15Code" tabIndex="24" maxLength="15" value="<%=requisitionHeader.getUdf15Code()%>" onchange="upperCase(this);"/>
					</tsa:td>
				</tr>
			<% if (!HiltonUtility.isEmpty(poStatus) && !s_req_type.equals("K"))
				{	%>
				<tr>
					<tsa:td field="req-poStatus" align="right" noWrap="nowrap" >
						<tsa:label labelName="req-poStatus" defaultString="Po Status" noinstance="yes"/> :&nbsp;
					</tsa:td>
					<tsa:td field="req-poStatus" >
					<tsa:input type="midtext" name="req_poStatus" maxLength="24" value="<%=DocumentStatus.toString(poStatus, oid)%>" onfocus="this.blur();" disabled="disabled" />
					</tsa:td>
				</tr>
			<%	}  %>

				<tr>
					<tsa:td id="req-kitLabelRow" field="req-kit" align="right" noWrap="nowrap" >
					<tsa:label labelName="req-kit" defaultString="Kit" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td id= "req-kitFieldRow" field="req-kit" noWrap="nowrap">
					<% String kitchecked = "";
					if (s_kit.equalsIgnoreCase("Y")) {
						kitchecked = "Y"; }%>
						<input type="checkbox" title="Check for kit" name="c_checkbox" tabIndex="4" value="Y" <% if (s_gfp_gfm.equals("Y")) { %> CHECKED <% } %>  onclick="setCheckbox(frm.RequisitionHeader_kit, 1);" >
						<tsa:hidden name="RequisitionHeader_kit" value="<%=requisitionHeader.getKit()%>"/>
					</tsa:td>
				</tr>
				<tr>
					<tsa:td id="req-workOrderLabelRow" field="req-workOrder" align="right" noWrap="nowrap" >
						<%	if (DictionaryManager.isLink(oid, "req-workOrder")) {	%>
						<a href="javascript: browseSrrWorkorder(); void(0);" title="Click here to choose the value for <tsa:label labelName='req-workOrder' defaultString='Work Order' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-workOrder" defaultString="Work Order" noinstance="true" checkRequired="true"/>:</a>&nbsp;
						<%	} else {	%>
						<tsa:label labelName="req-workOrder" defaultString="Work Order" checkRequired="true" noinstance="yes"/>:&nbsp;
						<%	}	%>
					</tsa:td>
					<tsa:td id="req-workOrderFieldRow" field="req-workOrder" >
					<tsa:input type="mintext" title="" name="RequisitionHeader_workOrder" tabIndex="24" maxLength="15" value="<%=requisitionHeader.getWorkOrder()%>" onchange="upperCase(this);"/>
					<a href="javascript:  openWorkorderInfo()"><img src="<%=contextPath%>/images/question.gif" alt="Click here to enter work order details." valign="bottom" border="0"></a>
					</tsa:td>
					<tsa:td id="req-categoryLabelRow" field="req-category" align="right" noWrap="nowrap" >
						<tsa:label labelName="req-category" defaultString="Request Category" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td id="req-categoryFieldRow" field="req-category" >
					<select title="Select a Category" name="RequisitionHeader_requestCat" tabIndex="24">
						<option value="" <%if (s_request_cat == null) out.print("selected");%>><tsa:label labelName="req-select-an-option" defaultString="Select an Option"></tsa:label></option>
						<option value="Materials" <%if (s_request_cat != null && s_request_cat.equals("M")) out.print("selected");%>><tsa:label labelName="REQ-Materials" defaultString="Materials"></tsa:label></option>
						<option value="SubContract" <%if (s_request_cat != null && s_request_cat.equals("S")) out.print("selected");%>><tsa:label labelName="req-subContract" defaultString="SubContract"></tsa:label></option>
					</select>
					</tsa:td>
				</tr>
</div>