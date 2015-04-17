<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>
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
String 	s_assigned_buyer_code = requisitionHeader.getAssignedBuyer();
UserProfile assignedBuyer = UserManager.getInstance().getUser(oid, s_assigned_buyer_code);
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
String s_udf_11_code = requisitionHeader.getUdf11Code();
String s_corrosion_eval = requisitionHeader.getCorrosionEval();
BigDecimal b_estimated_cost = requisitionHeader.getEstimatedCost().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
%>
<div name="req_srr_general_info" style="visibility: visible;">
				<tr>
<%if (s_req_type.equals("M")) {%>
					<tsa:td id="rq14LabelRow" field="req-RQ14" align="right" noWrap="nowrap">
					<tsa:label labelName="req-RQ14" defaultString="UDF 14" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td id="rq14FieldRow" field="req-RQ14">
					<tsa:input type="mintext" name="RequisitionHeader_udf14Code" maxLength="15" value="<%=requisitionHeader.getUdf14Code()%>" labelName="req-RQ14"/>
					</tsa:td>
<% } %>
					<tsa:td id="corrosionEvalLabelRow" field="req-corrosionEval" align="right" noWrap="nowrap">
			        <tsa:label labelName="req-corrosionEval" defaultString="Corrosion Evaluation" checkRequired="true" noinstance="yes"/>:&nbsp;
			        </tsa:td>
			        <tsa:td id="corrosionEvalFieldRow" field="req-corrosionEval">
			        <tsa:input type="yesnoradio" title="" name="RequisitionHeader_corrosionEval" value="<%=s_corrosion_eval%>"/>
			        </tsa:td>
				</tr>
				<tr>
					<tsa:td field="req-assignedBuyer" width="" align="right" noWrap="nowrap" >
					<tsa:label labelName="req-assignedBuyer" defaultString="Assigned Buyer" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="req-assignedBuyer" >
					<tsa:input type="midtext" name="RequisitionHeader_assignedBuyer" value="<%=s_assigned_buyer_code%>" onfocus="this.blur();" disabled="disabled"/>
					</tsa:td>
				</tr>
				<tr>
					<tsa:td field="req-assignedBuyerName" width="" align="right" noWrap="nowrap" >
					<tsa:label labelName="req-assignedBuyerName" defaultString="Assigned Buyer Name" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="req-assignedBuyer" >
					<tsa:input type="midtext" name="as_assignedBuyer" value="<%=assignedBuyer.getDisplayName()%>" onfocus="this.blur();" disabled="disabled"/>
					</tsa:td>
				</tr>
				<tr>
					<tsa:td id="rqPriority" field="req-priority" align="right" noWrap="nowrap">
					<a href="javascript: browseLookup('RequisitionHeader_priorityCode', 'procurementlevel'); void(0);" title="Click here to choose the <tsa:label labelName='req-priority' defaultString='Priority' noinstance='true'/> code."><tsa:label labelName="req-priority" defaultString="Priority" noinstance="true" checkRequired="true"/>:</a>&nbsp;
					</tsa:td>
					<tsa:td field="req-priority">
					<tsa:input type="mintext" name="RequisitionHeader_priorityCode" maxLength="15" value="<%=requisitionHeader.getPriorityCode()%>" onchange="upperCase(this);"/>
					</tsa:td>
				</tr>

				<tr>
					<tsa:td id="rq13LabelRow" field="req-RQ13" align="right" noWrap="nowrap" >
					<a href="javascript: browseLookup('RequisitionHeader_udf13Code', 'msr-type'); void(0);"><tsa:label labelName="req-RQ13" defaultString="UDF13" checkRequired="true" noinstance="yes"/></a>:&nbsp;
					</tsa:td>
					<tsa:td id="rq13FieldRow" field="req-RQ13" >
						<tsa:input type="text" tabIndex="23" name="RequisitionHeader_udf13Code" id="RequisitionHeader_udf13Code"  value="<%=requisitionHeader.getUdf13Code()%>" labelName="req-RQ13"/>
			        </tsa:td>
			    <% if (s_req_type.equals("P")) { %>
			    	<tsa:td id="dummyspace"></tsa:td>
			    	<tsa:td id="dummyspace"></tsa:td>
			    <% } %>
			        <tsa:td id="rq04LabelRow" field="req-RQ04" align="right" noWrap="nowrap">
			        <tsa:label labelName="req-RQ04" defaultString="UDF04" checkRequired="true" noinstance="yes"/>:&nbsp;
			        </tsa:td>
			        <tsa:td id="rq04FieldRow" field="req-RQ04" >
						<tsa:input type="mintext" tabIndex="24" name="RequisitionHeader_udf4Code" id="RequisitionHeader_udf4Code" size="1" value="<%=requisitionHeader.getUdf4Code()%>" labelName="req-RQ04"/>
			        </tsa:td>
				</tr>
				<tr>
		<%	if (!s_req_type.equals("K")) { %>
					<tsa:td id="rqReqCatLabelRow" field="req-REQCAT" align="right" noWrap="nowrap" >
<%		if (DictionaryManager.isLink(oid, "req-RQ05")) {	%>
						<a href="javascript: browseStd('RequisitionHeader_udf5Code', 'RQ05'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ05' defaultString='UDF5' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ05" defaultString="UDF5" noinstance="true" checkRequired="true"/>:</a>&nbsp;
<%		} else {	%>
<%			if (!oid.equals("SRR10P")){ %>
						<tsa:label labelName="req-RQ05" defaultString="UDF5" checkRequired="true" noinstance="yes"/>:&nbsp;
<%			} else { %>
						<tsa:label labelName="req-REQCAT" defaultString="Request Category" checkRequired="true" noinstance="yes"/>:&nbsp;
<%			} %>
<%		}	%>
					</tsa:td>
          			<tsa:td id="rqReqCatFieldRow" field="req-REQCAT" >

					<% if (!oid.equals("SRR10P")) { %>
						<tsa:input id="leftPanel" type="mintext" title="" name="RequisitionHeader_udf5Code" tabIndex="13" maxLength="15" value="<%=requisitionHeader.getUdf5Code()%>" onchange="upperCase(this);hideRightPanel();"/>
					<% } else { %>
						<tsa:input type="dropdown" title="" name="RequisitionHeader_requestCat" value="<%=requisitionHeader.getRequestCat()%>" labelName="req-REQCAT" onchange="upperCase(this);"/>
					<%} %>
          			</tsa:td>
				<%} %>
					 <tsa:td id="rq05LabelRow" field="req-RQ05" align="right" noWrap="nowrap">
			        <tsa:label labelName="req-RQ05" defaultString="UDF5" checkRequired="true" noinstance="yes"/>:&nbsp;
			        </tsa:td>
			        <tsa:td id="rq05FieldRow" field="req-RQ05">
			        <tsa:input type="yesnoradio" title="" name="RequisitionHeader_udf5Code" value="<%=requisitionHeader.getUdf5Code()%>"/>
			        </tsa:td>
				</tr>
				<tr>
			<%	if (!s_req_type.equals("K")) { %>
			        <tsa:td id="rq07LabelRow" field="req-RQ07" align="right" noWrap="nowrap" >
			<%		if (DictionaryManager.isLink(oid, "req-RQ07")) {	%>
							<a href="javascript: browseStd('RequisitionHeader_udf7Code', 'RQ07'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ07' defaultString='UDF7' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ07" defaultString="UDF7" noinstance="true" checkRequired="true"/>:</a>&nbsp;
			<%		} else {	%>
							<tsa:label labelName="req-RQ07" defaultString="UDF7" checkRequired="true" noinstance="yes"/>:&nbsp;
			<%		}	%>
					</tsa:td>
					<tsa:td id="rq07FieldRow" field="req-RQ07" >
			        <tsa:input type="dropdown" title="" name="RequisitionHeader_udf7Code" value="<%=requisitionHeader.getUdf7Code()%>" labelName="req-RQ07" onchange="upperCase(this);"/>
			        </tsa:td>
			<%  } %>
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
					<tsa:td id="SSD_label" field="req-servicesStartDate" align="right" noWrap="nowrap" >
					<tsa:label labelName="req-servicesStartDate" defaultString="Services Start Date"  docType="s_req_type" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td id="SSD_input" field="req-servicesStartDate" noWrap="nowrap">
						<tsa:input type="mintext" title="Enter Services Start Date" name="RequisitionHeader_servicesStartDate" tabIndex="29" value="<%=HiltonUtility.getFormattedDate(requisitionHeader.getServicesStartDate(), oid)%>"/>
						<a href="javascript: show_calendar('RequisitionHeader_servicesStartDate', '<%=s_date_format%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign="bottom" border="0"></a>
					</tsa:td>
				</tr>
				<tr>
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
					<tsa:input type="maxtext" title=""  name="RequisitionHeader_udf8Code" tabIndex="24" maxLength="40" value="<%=requisitionHeader.getUdf8Code()%>" onchange="upperCase(this);configureFieldsUDF();"/>
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
					<tsa:td field="req-RQ12" align="right" noWrap="nowrap" >
						<%	if (DictionaryManager.isLink(oid, "req-RQ12")) {	%>
						<a href="javascript: browseStd('RequisitionHeader_udf12Code', 'RQ12'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ12' defaultString='UDF12' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ12" defaultString="UDF12" noinstance="true" checkRequired="true"/>:</a>&nbsp;
						<%	} else {	%>
						<tsa:label labelName="req-RQ12" defaultString="UDF12" checkRequired="true" noinstance="yes"/>:&nbsp;
						<%	}	%>
					</tsa:td>
					<tsa:td field="req-RQ12" >
					<tsa:input type="mintext" title="" name="RequisitionHeader_udf12Code" tabIndex="24" maxLength="15" value="<%=requisitionHeader.getUdf12Code()%>" onchange="upperCase(this);"/>
					</tsa:td>
					<tsa:td field="req-RQ15" align="right" noWrap="nowrap" >
						<tsa:label labelName="req-RQ15" defaultString="UDF15" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="req-RQ15" >
					<input type="radio" name="CPR" value="yes" <%if (requisitionHeader.getUdf15Code().equalsIgnoreCase("YES")) {%>checked<%}%> onclick="setRadioValue('CPR','RequisitionHeader_udf15Code')" />&nbsp;Yes
					<input type="radio" name="CPR" value="no" <%if (requisitionHeader.getUdf15Code().equalsIgnoreCase("NO")) {%>checked<%}%> onclick="setRadioValue('CPR','RequisitionHeader_udf15Code')" />&nbsp;No
					<tsa:hidden name="RequisitionHeader_udf15Code" value="<%=requisitionHeader.getUdf15Code()%>"></tsa:hidden>
					</tsa:td>
				</tr>
				<%	if (!s_req_type.equals("K")) { %>
				<tr>
					<tsa:td field="req-inventoryLocation" docType="yes" align="right" noWrap="nowrap">
					<a href="javascript: browseLookup('RequisitionHeader_itemLocation', 'item_location'); void(0);" title="Click here to choose the  <tsa:label labelName='req-inventoryLocation' defaultString='Inventory Location' noinstance='true' /> for this requisition or enter the <tsa:label labelName='req-inventoryLocation' defaultString='Inventory Location' noinstance='true' /> in the box on the right."> <tsa:label labelName="req-inventoryLocation" defaultString="Inventory Location" noinstance="true" checkRequired="true"/>:</a>&nbsp;
					</tsa:td>
					<tsa:td field="req-inventoryLocation" docType="yes">
					<tsa:input type="mintext" title="Enter Inventory Location" name="RequisitionHeader_itemLocation" tabIndex="21" maxLength="15" value="<%=requisitionHeader.getItemLocation()%>" onchange="upperCase(this);"/>
					</tsa:td>
					<tsa:td>&nbsp;</tsa:td>
					<tsa:td>&nbsp;</tsa:td>
				</tr>
				<%	}	%>
				<tr>
					<tsa:td id="rq11LabelRow" field="req-RQ11" align="right" noWrap="nowrap" >
						<%	if (!DictionaryManager.isLink(oid, "req-RQ11")) {	%>
							<a href="javascript: browseStd('RequisitionHeader_udf11Code', 'RQ11'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ11' defaultString='UDF11' noinstance='true' /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ11" defaultString="UDF11" noinstance="true" checkRequired="true"/>:</a>&nbsp;
						<%	} else {	%>
						<tsa:label labelName="req-RQ11" defaultString="UDF11" checkRequired="true" noinstance="yes"/>:&nbsp;
						<%	}	%>
					</tsa:td>
					<tsa:td id="rq11FieldRow" field="req-RQ11" >
					<input type="checkbox" title="" name="c_checkbox" tabIndex="24"  value="Y" <% if (s_udf_11_code.equals("Y")) {%>checked<%}%> onclick="setCheckbox(frm.RequisitionHeader_udf11Code, 1);" />
					<tsa:hidden name="RequisitionHeader_udf11Code" value="<%=requisitionHeader.getUdf11Code()%>"/>
					</tsa:td>
					<tsa:td>&nbsp;</tsa:td>
					<tsa:td>&nbsp;</tsa:td>
				</tr>
				<tr>
					<tsa:td id="req-kitLabelRow" field="req-kit" align="right" noWrap="nowrap" >
					<tsa:label labelName="req-kit" defaultString="Kit" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td id= "req-kitFieldRow" field="req-kit" noWrap="nowrap">
					<% String kitchecked = "";
					if (s_kit.equalsIgnoreCase("Y")) { kitchecked = "Y"; }%>
						<input type="checkbox" title="Check for kit" name="c_checkbox" tabIndex="4" value="Y" <% if (s_kit.equals("Y")) { %> CHECKED <% } %>  onclick="setCheckbox(frm.RequisitionHeader_kit, 2);" >
						<tsa:hidden name="RequisitionHeader_kit" value="<%=requisitionHeader.getKit()%>"/>
					</tsa:td>
					<tsa:td>&nbsp;</tsa:td>
					<tsa:td>&nbsp;</tsa:td>
				</tr>
</div>