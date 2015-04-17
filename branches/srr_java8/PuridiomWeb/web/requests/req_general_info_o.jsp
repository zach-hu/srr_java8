<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	BigDecimal bd_ic_req_header = requisitionHeader.getIcReqHeader();
	String	s_req_number = requisitionHeader.getRequisitionNumber();
	String	s_req_type = requisitionHeader.getRequisitionType();
	String	s_req_subtype = requisitionHeader.getRqSubType();
	String	s_req_status = requisitionHeader.getStatus();
	String	s_fiscal_year = requisitionHeader.getFiscalYear();
	String	s_requisitioner_code = requisitionHeader.getRequisitionerCode();
	UserProfile requisitioner = UserManager.getInstance().getUser(oid, s_requisitioner_code);
	String	s_department_code = requisitionHeader.getDepartmentCode();
	String	s_authorization_code = requisitionHeader.getAuthorizationCode();
	UserProfile authby = UserManager.getInstance().getUser(oid, s_authorization_code);
	UserProfile buyer = UserManager.getInstance().getUser(oid, requisitionHeader.getBuyer());
	String	s_language = requisitionHeader.getLanguage();
	String	s_receipt_required = requisitionHeader.getReceiptRequired();

	if (HiltonUtility.isEmpty(s_req_number)){s_req_number = "N/A";}

	String	s_current_process = "HEADER_GENERAL_INFO";
	String	s_current_page = "/requests/req_general_info_o.jsp";
	String	s_current_method = "RequisitionHeaderUpdate";
	String	s_current_process_method = "";
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=requisitionHeader.getRequisitionNumber()%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionHeader_shipToCode" value="<%=requisitionHeader.getShipToCode()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RQH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="stdTable_tableType" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="resetReceipt" value=""/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="general_information" defaultString="General Information" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b><tsa:label labelName="requisitionNumber" defaultString="Requisition #" />:</b></td>
			<td width=125px><%=headerEncoder.encodeForHTML(s_req_number)%></td>
		</tr>
		<tr>
			<td align=right><b><tsa:label labelName="status" defaultString="Status" />:</b></td>
			<td width=125px><%=DocumentStatus.toString(s_req_status, oid)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center>
		<table border=0 cellspacing=0 cellpadding=0 height=100%>
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=250px align=center>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "requestDate")%> width=150px align=right nowrap><tsa:label labelName="requestDate" defaultString="Request Date" checkRequired="true" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "requestDate")%> >
						<input type=text title="Enter Requisition Date" name="RequisitionHeader_requisitionDate" tabindex=5 size=15 value="<%=HiltonUtility.getFormattedDate(requisitionHeader.getRequisitionDate(), oid, userDateFormat)%>">
						<a href="javascript: show_calendar('RequisitionHeader_requisitionDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</td>
				</tr>
				<tr>
					<td width=150px align=right <%=HtmlWriter.isVisible(oid, "req-fiscalYear")%> nowrap><tsa:label labelName="req-fiscalYear" defaultString="Fiscal Year" checkRequired="true" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-fiscalYear")%> ><input type=text title="Enter Fiscal Year of the requisition" name="RequisitionHeader_fiscalYear" tabindex=6 size=15 maxlength=4 value="<%=s_fiscal_year%>"></td>
				</tr>
				<tr>
					<td width=150px align=right <%=HtmlWriter.isVisible(oid, "req-requisitioner")%> nowrap><a href="javascript: browseLookup('RequisitionHeader_requisitionerCode', 'requisitioner'); void(0);" title="Click here to choose the Requisitioner or enter the Requisitioner ID in the box on the right."><tsa:label labelName="req-requisitioner" defaultString="Requisitioner" checkRequired="true" />:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-requisitioner")%> ><input type=text title="Enter the User ID of the Requisitioner" name="RequisitionHeader_requisitionerCode" tabindex=7 size=15 maxlength=15 value="<%=s_requisitioner_code%>" onchange="upperCase(this); ckReqBy(); getNewInfo('requisitioner', this);"></td>
				</tr>
				<tr>
					<td width=150px align=right <%=HtmlWriter.isVisible(oid, "req-requisitionerName")%> nowrap><tsa:label labelName="req-requisitionerName" defaultString="Requisitioner Name" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-requisitionerName")%> ><input type=text name="as_requisitionerName" size=24 value="<%=requisitioner.getDisplayName()%>" onfocus="this.blur();" disabled></td>
				</tr>
				<tr>
					<td width=150px align=right <%=HtmlWriter.isVisible(oid, "req-department")%> nowrap><a href="javascript: browseLookup('RequisitionHeader_departmentCode', 'department'); void(0);" title="Click here to choose the <tsa:label labelName="department" defaultString="Department" /> Code for this requisition or enter the Deptartment Code in the box on the right."><tsa:label labelName="req-department" defaultString="Department" checkRequired="true" />:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-department")%> ><input type=text title="Enter the <tsa:label labelName="department" defaultString="Department" /> Code of the Requisition" name="RequisitionHeader_departmentCode" tabindex=9 size=15 maxlength=15 value="<%=s_department_code%>" onchange="upperCase(this);"></td>
				</tr>
				<tr>
					<td width=150px align=right <%=HtmlWriter.isVisible(oid, "req-authorizedBy", s_req_type)%> nowrap><a href="javascript: browseLookup('RequisitionHeader_authorizationCode', 'user'); void(0);" title="Click here to choose the Authorizer ID for this requisition or enter the Authorizer ID in the box on the right."><tsa:label labelName="req-authorizedBy" defaultString="Authorized By:" />:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-authorizedBy", s_req_type)%> ><input type=text title="Enter the user id of the contact person for this project" name="RequisitionHeader_authorizationCode" tabindex=10 size=15 maxlength=15 value="<%=s_authorization_code%>" onchange="upperCase(this); getNewInfo('authby', this);"></td>
				</tr>
				<tr>
					<td width=150px align=right <%=HtmlWriter.isVisible(oid, "req-authorizedByName", s_req_type)%> nowrap><tsa:label labelName="req-authorizedByName" defaultString="Authorized By Name" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-authorizedByName", s_req_type)%> ><input type=text name="as_authbyName" size=24 maxlength=24 value="<%=authby.getDisplayName()%>" onfocus="this.blur();" disabled></td>
				</tr>

				</table>
			</td>
			<td>&nbsp;</td>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=240px align=center>
<%	if (!s_req_type.equals("K") && !s_req_type.equals("O")) { %>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "req-buyer")%> nowrap><a href="javascript: browseLookup('RequisitionHeader_buyer', 'buyer'); void(0);" title="Click here to choose the Buyer Name for this requisition or enter the Buyer ID in the box on the right."><tsa:label labelName="req-buyer" defaultString="Buyer" checkRequired="true" />:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-buyer")%> ><input type=text  title="Enter Buyer ID" name="RequisitionHeader_buyer" tabindex=18 size=15 maxlength=15 value="<%=requisitionHeader.getBuyer()%>" onchange="upperCase(this); getNewInfo('buyer', this);"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "req-buyerName")%> nowrap><tsa:label labelName="req-buyerName" defaultString="Buyer Name" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-buyerName")%> ><input type=text name="as_buyerName" size=24 maxlength=24 value="<%=buyer.getDisplayName()%>" onfocus="this.blur();" disabled></td>
				</tr>
<%	} %>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "req-currency")%> nowrap><a href="javascript: browseLookup('RequisitionHeader_currencyCode', 'curr_code'); void(0);" title="Click here to choose the Currency Type for this requisition or enter the Currency Type in the box on the right."><tsa:label labelName="req-currency" defaultString="Currency" checkRequired="true" />:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-currency")%> ><input type=text  title="Enter type of Currency" name="RequisitionHeader_currencyCode" tabindex=19 size=15 maxlength=15 value="<%=requisitionHeader.getCurrencyCode()%>" onchange="upperCase(this);"></td>
				</tr>
<%	if (!s_req_type.equals("K") && !s_req_type.equals("O")) { %>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "req-language")%> nowrap><tsa:label labelName="req-language" defaultString="Language" checkRequired="true" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-language")%> >
						<select name="RequisitionHeader_language" tabindex=20>
						<option value="(Default)" <% if ( s_language.equals("(Default)") ) { %>SELECTED<%}%> >(Default)</option>
						<option value="" <% if ( s_language.equals("") ) { %>SELECTED<%}%> ></option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "req-inventoryLocation")%> nowrap><a href="javascript: browseLookup('RequisitionHeader_itemLocation', 'item_location'); void(0);" title="Click here to choose the Inventory Location for this requisition or enter the Inventory Location in the box on the right."><tsa:label labelName="req-inventoryLocation" defaultString="Inventory Location" checkRequired="true" />:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-inventoryLocation")%> ><input type=text  title="Enter Inventory Location" name="RequisitionHeader_itemLocation" tabindex=21 size=15 maxlength=15 value="<%=requisitionHeader.getItemLocation()%>" onchange="upperCase(this);"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "req-receiptOptions")%> nowrap><tsa:label labelName="req-receiptOptions" defaultString="Receipt Options" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-receiptOptions")%> >
						<select tabindex=22 name="RequisitionHeader_receiptRequired" size=1 onchange="setReceiptOption();">
							<option <% if (s_receipt_required.equals("R")) {%> SELECTED <%}%> value="R">Receipt Required</option>
							<option <% if (s_receipt_required.equals("P")) {%> SELECTED <%}%> value="P">Previously Received</option>
		<%	if (!oid.equals("MSG07P")) {%>
							<option <% if (s_receipt_required.equals("N")) {%> SELECTED <%}%> value="N">No Receipt Required</option>
		<%	}%>
							<option <% if (s_receipt_required.equals("E")) {%> SELECTED <%}%> value="E">End User Receipt</option>
						</select>
					</td>
				</tr>
<%	} %>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "req-RQ01")%> nowrap><a href="javascript: browseStd('RequisitionHeader_udf1Code', 'AC04'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-RQ01" defaultString="UDF1" checkRequired="false" /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ01" defaultString="UDF1" checkRequired="true" />:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-RQ01")%> ><input type=text title="Enter <tsa:label labelName="req-RQ01" defaultString="UDF1" checkRequired="false" />" name="RequisitionHeader_udf1Code" tabindex="23" size=15 maxlength=15 value="<%=requisitionHeader.getUdf1Code()%>" onchange="upperCase(this); displayArea('projectDetails');"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "req-RQ02")%> nowrap><a href="javascript: browseStd('RequisitionHeader_udf2Code', 'RQ02'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-RQ02" defaultString="UDF2" checkRequired="false" /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ02" defaultString="UDF2" checkRequired="true" />:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-RQ02")%> ><input type=text title="Enter <tsa:label labelName="req-RQ02" defaultString="UDF2" checkRequired="false" />" name="RequisitionHeader_udf2Code" tabindex="24" size=15 maxlength=15 value="<%=requisitionHeader.getUdf2Code()%>" onchange="upperCase(this);"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "req-RQ03")%> nowrap><a href="javascript: browseStd('RequisitionHeader_udf3Code', 'RQ03'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-RQ03" defaultString="UDF3" checkRequired="false" /> for this requisition or enter the value in the box on the right."><tsa:label labelName="req-RQ03" defaultString="UDF3" checkRequired="true" />:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-RQ03")%> ><input type=text title="Enter <tsa:label labelName="req-RQ03" defaultString="UDF3" checkRequired="false" />" name="RequisitionHeader_udf3Code" tabindex="25" size=15 maxlength=15 value="<%=requisitionHeader.getUdf3Code()%>" onchange="upperCase(this);"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "req-RQ09")%> nowrap><a href="javascript: <% if (oid.equalsIgnoreCase("bsc04p")) {%>browseLookup('RequisitionHeader_udf9Code', 'approver');<%} else {%>browseStd('RequisitionHeader_udf9Code', 'RQ09');<%}%> void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ09", "UDF9", false)%> for this requisition or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ09", "UDF9", true)%>:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-RQ09")%> ><input type=text  title="Enter <tsa:label labelName="req-RQ09" defaultString="UDF9" checkRequired="false" />" name="RequisitionHeader_udf9Code" tabindex="26" size=15 maxlength=15 value="<%=requisitionHeader.getUdf9Code()%>" onchange="upperCase(this);"></td>
				</tr>
				</table>
			</td>
		</tr>
<%	if (!s_req_type.equals("K") && !s_req_type.equals("O")) { %>
		<tr>
			<td colspan=3>
				<table border=0 cellpadding=2 cellspacing=0>
				<tr <%=HtmlWriter.isVisible(oid, "req-purpose")%>>
					<td width=150px nowrap align=right valign=top><tsa:label labelName="req-purpose" defaultString="Purpose" checkRequired="true" />:&nbsp;</td>
					<td>
					<textarea title="Enter the purpose for this requisition" name="RequisitionHeader_internalComments" tabindex=27  rows="6" cols="64" wrap="nonvirtual" maxlength=255 onKeyDown="textCounter(this, 255);" onKeyUp="textCounter(this,255);" onchange="textCounter(this,255); upperCase(this);">					
					${esapi:encodeForHTML(requisitionHeader.internalComments)}
					</textarea></td>
				</tr>
				</table>
			</td>
		</tr>
<%	} %>
		<tr>
			<td colspan=3>
			<br><br>
				<table border=0 cellpadding=2 cellspacing=0 align="right">
				<tr>
					<td width=124px align=right nowrap><tsa:label labelName="requestFor" defaultString="This is a request for" />:</td>
					<td>
						<select tabindex="29" name="RequisitionHeader_udf5Code" size=1>
							<option <% if (requisitionHeader.getUdf5Code().equals("NC")) {%> SELECTED <%}%> value="NC">New Contract</option>
							<option <% if (requisitionHeader.getUdf5Code().equals("RI")) {%> SELECTED <%}%> value="RI">Request For Information</option>
							<option <% if (requisitionHeader.getUdf5Code().equals("RP")) {%> SELECTED <%}%> value="RP">Request For Proposal</option>
							<option <% if (requisitionHeader.getUdf5Code().equals("SS")) {%> SELECTED <%}%> value="SS">Sourcing Support</option>
							<option <% if (requisitionHeader.getUdf5Code().equals("CO")) {%> SELECTED <%}%> value="CO">Change Order, Addendum or Amendment to Existing Contract</option>
							<option <% if (requisitionHeader.getUdf5Code().equals("SW")) {%> SELECTED <%}%> value="SW">Statement of Work (SOW) Against Master Agreement</option>
							<option <% if (requisitionHeader.getUdf5Code().equals("SE")) {%> SELECTED <%}%> value="SE">Supplier Engagement (preliminary discussions w/ Supplier)</option>
						</select>
					</td>
				</tr>
				</table>
			</td>
		</tr>
<tsa:hidden name="RequisitionHeader_udf6Code" value="<%=requisitionHeader.getUdf6Code()%>"/>
<%
	if (HiltonUtility.isEmpty(requisitionHeader.getUdf6Code())  || requisitionHeader.getUdf6Code().length() < 4)
	{
		requisitionHeader.setUdf6Code("NNNN");
	}
	char[] yesNoArray = requisitionHeader.getUdf6Code().toCharArray();
%>
		<tr>
			<td colspan=3>
				<table border=0 cellpadding=2 cellspacing=0>
				<tr>
					<td align=right nowrap width="300px"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bscHaveCurrentContract", "Does BSC have a current contract with this Supplier?")%>&nbsp;</td>
					<td nowrap>
						<input type="checkbox" name="c_checkbox" value="Y" <% if (yesNoArray[0] == 'Y') { %> CHECKED <% } %> onClick="setCheckbox(frm.yesNo_0, 0); setYesNoFields();">&nbsp;(<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "yesAttachCopy", "If yes, please attach a copy")%>)
						<tsa:hidden name="yesNo_0" value="<%=yesNoArray[0]%>"/>
					</td>
				</tr>
				<tr>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "haveSupplierProposal", "Do you have Supplier's proposal or pricing quote?")%>&nbsp;</td>
					<td nowrap>
						<input type="checkbox" name="c_checkbox" value="Y" <% if (yesNoArray[1] == 'Y') { %> CHECKED <% } %> onClick="setCheckbox(frm.yesNo_1, 1); setYesNoFields();">&nbsp;(<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "yesAttachCopy", "If yes, please attach a copy")%>)
						<tsa:hidden name="yesNo_1" value="<%=yesNoArray[1]%>"/>
					</td>
				</tr>
				</table>
				<div id="projectDetails" style="visibility:hidden; display:none;">
				<table border=0 cellpadding=2 cellspacing=0>
				<tr>
					<td align=right nowrap width="300px">Is this Project part of BSC's Priority Initiatives?&nbsp;</td>
					<td nowrap>
						<select tabindex=30 name="RequisitionHeader_udf7Code" size=1>
							<option <% if (requisitionHeader.getUdf7Code().equals("N")) {%> SELECTED <%}%> value="N">None</option>
							<option <% if (requisitionHeader.getUdf7Code().equals("C")) {%> SELECTED <%}%> value="C">Competitive Professional Costs</option>
							<option <% if (requisitionHeader.getUdf7Code().equals("R")) {%> SELECTED <%}%> value="R">Reduce Cost of Health Care Trend</option>
							<option <% if (requisitionHeader.getUdf7Code().equals("H")) {%> SELECTED <%}%> value="H">Health Care Analytics Capability</option>
							<option <% if (requisitionHeader.getUdf7Code().equals("Q")) {%> SELECTED <%}%> value="Q">Quote to Card Program</option>
							<option <% if (requisitionHeader.getUdf7Code().equals("P")) {%> SELECTED <%}%> value="P">Product Capability</option>
							<option <% if (requisitionHeader.getUdf7Code().equals("S")) {%> SELECTED <%}%> value="S">Shared Adjudication</option>
							<option <% if (requisitionHeader.getUdf7Code().equals("I")) {%> SELECTED <%}%> value="I">IT Transformation</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right nowrap>Has this Project been approved by PASS?&nbsp;</td>
					<td nowrap>
						<input type="checkbox" name="c_checkbox" value="Y" <% if (yesNoArray[2] == 'Y') { %> CHECKED <% } %> onClick="setCheckbox(frm.yesNo_2, 2); setYesNoFields();">
						<tsa:hidden name="yesNo_2" value="<%=yesNoArray[2]%>"/>
						PASS $ Value:&nbsp;<input type=text title="Enter <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ04", "UDF4", false)%>" name="RequisitionHeader_udf4Code" tabindex="31" size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDollar(requisitionHeader.getUdf4Code(), oid)%>" style="text-align:right;" onchange="formatMe(this);">
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td nowrap align="middle"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ08", "Requisition UDF 8", s_req_type)%>:&nbsp;<input type="text" name="RequisitionHeader_udf8Code" tabindex="33" size="15" maxlength="15" value="<%=requisitionHeader.getUdf8Code()%>" onchange="upperCase(this);">&nbsp;</td>
				</tr>
				<tr>
					<td align=right nowrap>Do you need a Budget set-up for this project?&nbsp;</td>
					<td nowrap>
						<input type="checkbox" name="c_checkbox" value="Y" <% if (yesNoArray[3] == 'Y') { %> CHECKED <% } %> onClick="setCheckbox(frm.yesNo_3, 3); setYesNoFields();">
						<tsa:hidden name="yesNo_3" value="<%=yesNoArray[3]%>"/>
					</td>
				</tr>
				</table>
				</div>
			</td>
		</tr>
		</table>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
			<td rowspan=2 valign=top><%@ include file="/requests/req_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>
<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionHeaderUpdate;RequisitionRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var reqnumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
	var fiscalyear = "<%=requisitionHeader.getFiscalYear()%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var allowEdit;

	function ckReqBy()
	{
<%	if (propertiesManager.getProperty("Name UDFS", "REQBYDFLT", "N").equalsIgnoreCase("Y")) {%>
			var code = frm.RequisitionHeader_requisitionerCode.value;
			var href = "<%=contextPath%>/requests/reqby_info.jsp?as_code=" + code;

			openInfoWindow(href, 'WIDTH=1', 'HEIGHT=1');
<%	} %>
	}

	function thisLoad()
	{
		f_StartIt();
<%		if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>

		if (frm.RequisitionHeader_udf1Code.value.length > 0)
		{
			displayArea('projectDetails');
		}
	}

	function setReceiptOption()
	{
		if (verifyAction("Change all line items to match requisition header receipt option?"))
		{
			frm.resetReceipt.value = true;
		}
		else
		{
			frm.resetReceipt.value = false;
		}
	}

	function validateForm() {
		var handlers = frm.handler.value;
		if (handlers.indexOf("RequisitionHeaderUpdate") >= 0) {
			var alertMessage = "";

			if (frm.RequisitionHeader_requisitionDate && !chkdate(frm.RequisitionHeader_requisitionDate)) {
				alertMessage += "\n<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requestDate", "Requisition Date")%> is not a valid date.";
			}

			if (alertMessage.length > 0) {
				alert("Please fix the following problems:\n" + alertMessage);
				return false;
			}
		}
		return true;
	}

	function setYesNoFields()
	{
		var YN0 = frm.yesNo_0.value;
		var YN1 = frm.yesNo_1.value;
		var YN2 = frm.yesNo_2.value;
		var YN3 = frm.yesNo_3.value;
		frm.RequisitionHeader_udf6Code.value = YN0 + YN1 + YN2 + YN3;
	}

	function formatMe(x)
	{
		var dollar_dec = <%=Integer.valueOf(s_dollar_decimals).intValue()%>;
		x.value = nformat(eval(nfilter(x)),dollar_dec);
	}

// End Hide script -->
</SCRIPT>
