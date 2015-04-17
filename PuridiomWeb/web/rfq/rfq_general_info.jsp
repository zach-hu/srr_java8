<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	int i;

	BigDecimal bd_icRfqHeader = rfqHeader.getIcRfqHeader();
	String s_rfqStatus = rfqHeader.getStatus();
	String s_rfqNumber = rfqHeader.getRfqNumber();
	String s_rfqAmendment = rfqHeader.getRfqAmendment();
	String s_language = rfqHeader.getLanguage();
	String s_buyer = rfqHeader.getBuyer();
	String s_requisitionerCode = rfqHeader.getRequisitionerCode();
	String s_authorizedBy = rfqHeader.getAuthorizationCode();
	String s_corrosion_eval = rfqHeader.getCorrosionEval();
	UserProfile buyer = UserManager.getInstance().getUser(oid, s_buyer);
	UserProfile requisitioner = UserManager.getInstance().getUser(oid, s_requisitionerCode);
	UserProfile authorizedBy = UserManager.getInstance().getUser(oid, s_authorizedBy);
	boolean editMode = false;
	boolean bAllowEdit = true;
  	String	invalidFields = (String) request.getAttribute("invalidFields");

	while(!HiltonUtility.isEmpty(invalidFields) && invalidFields.startsWith(";")) {
		if (invalidFields.length() > 1) {
			invalidFields = invalidFields.substring(1);
		} else {
			invalidFields = "";
		}
	}

	if (role.getAccessRights("RFQ") > 1 && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENAMENDMENT) == 0)) {
		editMode = true;
	}

	String	s_current_process = "HEADER_GENERAL_INFO";
	String	s_current_page = "/rfq/rfq_general_info.jsp";
	String	s_current_method = "RfqHeaderUpdateById";
	String	s_current_process_method = "";
	String s_gfp_gfm = rfqHeader.getGfpGfm();
	String s_request_cat = rfqHeader.getRequestCat();
	String s_kit = rfqHeader.getKit();

	String s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="${rfqHeader.rfqType}"/>
<tsa:hidden name="RfqQuestion_icRfqHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="stdTable_tableType" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="resetUdf2Code" value="false"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
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
		<%@ include file="/rfq/rfq_status_title.jsp" %>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<%@ include file="/rfq/rfq_info.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top>
		<table border=0 cellspacing=0 cellpadding=0 height=100% width=90%>
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=250px align=center>
				<tsa:tr field="rfq-solicitationDate">
					<td align=right nowrap><tsa:label labelName="rfq-solicitationDate" defaultString="Solicitation Date" checkRequired="true" />:&nbsp;</td>
					<td>
						<tsa:input labelName="rfq-solicitationDate" type="mintext" name="RfqHeader_rfqDate" tabIndex="1" maxLength="10" value="<%=HiltonUtility.getFormattedDate(rfqHeader.getRfqDate(), oid, userDateFormat)%>" />
						<a href="javascript: show_calendar('RfqHeader_rfqDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or enter a Date in the box on the left."valign=bottom border=0></a>
					</td>
				</tsa:tr>
				<tsa:tr field="rfq-fiscalYear">
					<td width=105px align=right nowrap><tsa:label labelName="rfq-fiscalYear" defaultString="Fiscal Year" checkRequired="true" />:&nbsp;</td>
					<td>
<%	if (editMode) {%>
						<select title="<tsa:label labelName="rfq-fiscalYear" defaultString="Fiscal Year" />" name="RfqHeader_fiscalYear" tabindex=3>
						<%=HiltonUtility.getFiscalYearOptions(oid, userTimeZone, "RFQ", rfqHeader.getFiscalYear())%>
						</select>
<%	} else {%>
						<tsa:input labelName="rfq-fiscalYear" type="mintext" name="RfqHeader_fiscalYear" tabIndex="3" maxLength="4" value="<%=rfqHeader.getFiscalYear()%>" onfocus="this.blur();" disabled="true" />
<%	}%>
					</td>
				</tsa:tr>
				<tsa:tr field="rfq-dueDate">
					<td align=right nowrap><tsa:label labelName="rfq-dueDate" defaultString="Due Date" checkRequired="true" />:&nbsp;</td>
					<td>
						<tsa:input labelName="rfq-dueDate" type="text" name="RfqHeader_dueDate" tabIndex="5" maxLength="10" value="<%=HiltonUtility.getFormattedDate(rfqHeader.getDueDate(), oid, userDateFormat)%>" />
						<a href="javascript: show_calendar('RfqHeader_dueDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or enter a Date in the box on the left." valign=bottom border=0></a>
					</td>
				</tsa:tr>
				<tsa:tr field="rfq-dueTime">
					<td align=right><tsa:label labelName="rfq-dueTime" defaultString="Due Time" checkRequired="true" />:&nbsp;</TD>
					<td>
						<tsa:input labelName="rfq-dueTime" type="text" name="due_time" tabIndex="7" size="6" maxLength="5" value="<%=rfqHeader.getBidDueTime()%>" onchange="setTime();" />
						<select name="timeofday" tabindex=9 onchange="setTime();">
							<option value="pm">P.M.</option>
							<option value="am">A.M.</option>
						</select>
						<%=headerEncoder.encodeForHTML(rfqHeader.getTimeZone())%>
						<tsa:hidden name="RfqHeader_bidDueTime" value="<%=rfqHeader.getBidDueTime()%>"/>
						<tsa:hidden name="RfqHeader_timeZone" value="<%=rfqHeader.getTimeZone()%>"/>
					</td>
				</tsa:tr>
				<tsa:tr field="rfq-buyer">
					<td align=right nowrap><a href="javascript: browseLookup('RfqHeader_buyer', 'buyer'); void(0);" title="Click here to choose the <tsa:label labelName="rfq-buyer" defaultString="Buyer" /> for this solicitation or enter the <tsa:label labelName="rfq-buyer" defaultString="Buyer" /> in the box on the right." ><tsa:label labelName="rfq-buyer" defaultString="Buyer" checkRequired="true" />:</a>&nbsp;</td>
					<td><tsa:input labelName="rfq-buyer" type="mintext" name="RfqHeader_buyer" tabIndex="13" maxLength="15" value="<%=s_buyer%>" onchange="upperCase(this); getNewInfo('buyer', this);" /></td>
				</tsa:tr>
				<tsa:tr field="rfq-buyerName">
					<td align=right nowrap><tsa:label labelName="rfq-buyerName" defaultString="Buyer Name" />:&nbsp;</td>
					<td><tsa:input labelName="rfq-buyerName" type="text" name="as_buyerName" size="24" maxLength="24" value="<%=buyer.getDisplayName()%>" onfocus="this.blur();" disabled="true" /></td>
				</tsa:tr>
				<tsa:tr field="rfq-currency">
					<td align=right nowrap><a href="javascript: browseLookup('RfqHeader_currencyCode', 'curr_code'); void(0);" title="Click here to choose the <tsa:label labelName="rfq-currency" defaultString="Currency" /> for this solicitation or enter the <tsa:label labelName="rfq-currency" defaultString="Currency" /> in the box on the right."><tsa:label labelName="rfq-currency" defaultString="Currency" checkRequired="true" />:</a>&nbsp;</td>
					<td><tsa:input labelName="rfq-currency" type="mintext" name="RfqHeader_currencyCode" tabIndex="17" maxLength="30" value="<%=rfqHeader.getCurrencyCode()%>" onchange="upperCase(this); currencyChangeWarning(this.value);" /></td>
				</tsa:tr>
				<tsa:tr field="rfq-language">
					<td align=right nowrap><tsa:label labelName="rfq-language" defaultString="Language" checkRequired="true" />:&nbsp;</td>
					<td>
						<select name="RfHeader_language" tabindex=19>
						<option value="(Default)" <% if ( s_language.equals("(Default)") ) { %>selected<%}%> >(Default)</option>
						<option value="" <% if ( s_language.equals("") ) { %>selected<%}%> ></option>
						</select>
					</td>
				</tsa:tr>
				<tsa:tr field="rfq-RF01">
					<td align=right nowrap>
<%	if (DictionaryManager.isLink(oid, "rfq-RF01")) {	%>
						<a href="javascript: browseStd('RfqHeader_udf1Code','RF01');" title="Click here to choose the value for <tsa:label labelName="rfq-RF01" defaultString="UDF 1" /> for this solicitation or enter the value in the box on the right." ><tsa:label labelName="rfq-RF01" defaultString="UDF 1" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="rfq-RF01" defaultString="UDF1" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
					<td><tsa:input labelName="rfq-RF01" type="mintext" name="RfqHeader_udf1Code" tabIndex="21" maxLength="15" value="<%=rfqHeader.getUdf1Code()%>" /></td>
				</tsa:tr>
				<tr>
					<tsa:td id="rfqPriorityCode" field="rfq-priority" align="right" noWrap="nowrap">
					<a href="javascript: browseLookup('RfqHeader_priorityCode', 'procurementlevel');  void(0);" title="Click here to choose the <tsa:label labelName='rfq-priority' defaultString='Priority' noinstance='true'/> code."><tsa:label labelName="rfq-priority" defaultString="Priority" noinstance="true" checkRequired="true"/>:</a>&nbsp;
					</tsa:td>
					<tsa:td field="rfq-priority">
					<tsa:input labelName="rfq-priority" type="mintext" name="RfqHeader_priorityCode" maxLength="15" value="<%=rfqHeader.getPriorityCode()%>" onchange="upperCase(this);"/>
					</tsa:td>
				</tr>
				<tsa:tr field="rfq-RF03">
					<td align=right nowrap>
<%	if (DictionaryManager.isLink(oid, "rfq-RF03")) {	%>
						<a href="javascript: browseStd('RfqHeader_udf3Code','RF03');" title="Click here to choose the value for <tsa:label labelName="rfq-RF03" defaultString="UDF 3" /> for this solicitation or enter the value in the box on the right." ><tsa:label labelName="rfq-RF03" defaultString="UDF 3" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="rfq-RF03" defaultString="UDF3" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
					<td><tsa:input labelName="rfq-RF03" type="mintext" name="RfqHeader_udf3Code" tabIndex="21" maxLength="15" value="<%=rfqHeader.getUdf3Code()%>" /></td>
				</tsa:tr>
				<tsa:tr field="rfq-RF05">
					<td align=right nowrap>
<%	if (DictionaryManager.isLink(oid, "rfq-RF05")) {	%>
						<a href="javascript: browseStd('RfqHeader_udf5Code','RF05');" title="Click here to choose the value for <tsa:label labelName="rfq-RF05" defaultString="UDF 5" /> for this solicitation or enter the value in the box on the right." ><tsa:label labelName="rfq-RF05" defaultString="UDF 5" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="rfq-RF05" defaultString="UDF5" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
					<td><tsa:input labelName="rfq-RF05" type="mintext" name="RfqHeader_udf5Code" tabIndex="21" maxLength="15" value="<%=rfqHeader.getUdf5Code()%>" /></td>
				</tsa:tr>
				<tsa:tr field="rfq-RF07">
					<td align=right nowrap>
<%	if (DictionaryManager.isLink(oid, "rfq-RF07")) {	%>
						<a href="javascript: browseStd('RfqHeader_udf7Code','RF07');" title="Click here to choose the value for <tsa:label labelName="rfq-RF07" defaultString="UDF 7" /> for this solicitation or enter the value in the box on the right." ><tsa:label labelName="rfq-RF07" defaultString="UDF 7" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="rfq-RF07" defaultString="UDF7" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
					<td><tsa:input labelName="rfq-RF07" type="mintext" name="RfqHeader_udf7Code" tabIndex="21" maxLength="15" value="<%=rfqHeader.getUdf7Code()%>" /></td>
				</tsa:tr>
				<tsa:tr field="rfq-RF09">
					<td align=right nowrap>
<%	if (DictionaryManager.isLink(oid, "rfq-RF09")) {	%>
						<a href="javascript: browseStd('RfqHeader_udf9Code','RF09');" title="Click here to choose the value for <tsa:label labelName="rfq-RF09" defaultString="UDF 9" /> for this solicitation or enter the value in the box on the right." ><tsa:label labelName="rfq-RF09" defaultString="UDF 9" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="rfq-RF09" defaultString="UDF9" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
					<td><tsa:input labelName="rfq-RF09" type="mintext" name="RfqHeader_udf9Code" tabIndex="21" maxLength="15" value="<%=rfqHeader.getUdf9Code()%>" /></td>
				</tsa:tr>
				<tr>
					<tsa:td id="rfq-GfpGfmLabelRow" field="rfq-RFQGFPGFM" align="right" noWrap="nowrap" >
					<tsa:label labelName="rfq-RFQGFPGFM" defaultString="GFP/GFM Item" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="rfq-RFQGFPGFM" noWrap="nowrap">
					<% String gfpchecked = null;
					if (s_gfp_gfm.equalsIgnoreCase("Y")) {
						gfpchecked = "Y"; }%>
						<tsa:input type="checkbox" title="Check for GFP/GFM item" name="c_checkbox" tabIndex="4" value="Y" checked="<%=gfpchecked%>" onclick="setCheckbox(frm.RfqHeader_gfpGfm, 0);" />
						<tsa:hidden name="RfqHeader_gfpGfm" value="<%=rfqHeader.getGfpGfm()%>"/>
					</tsa:td>
				</tr>
				<tr>
					<tsa:td id="rfq-WorkOrderLabelRow" field="rfq-RFQWORKORDER" align="right" noWrap="nowrap" >
						<tsa:label labelName="rfq-RFQWORKORDER" defaultString="Work Order" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="rfq-RFQWORKORDER" >
					<tsa:input type="mintext" title="" name="RfqHeader_workOrder" tabIndex="24" maxLength="15" value="<%=rfqHeader.getWorkOrder()%>" onchange="upperCase(this);"/>
					</tsa:td>
				</tr>
				<tr>
					<tsa:td id="rfq-NaicsLabelRow" field="rfq-RFQNAICS" align="right" noWrap="nowrap" >
						<tsa:label labelName="rfq-RFQNAICS" defaultString="NAICS Code" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="rfq-RFQNAICS" >
					<tsa:input type="mintext" title="" name="RfqHeader_naics" tabIndex="24" maxLength="15" value="<%=rfqHeader.getNaics()%>" onchange="upperCase(this);"/>
					</tsa:td>
				</tr>
				</table>
			</td>
			<td>&nbsp;</td>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=250px align=center>
<% 			if(s_rfqStatus.compareTo(DocumentStatus.RFQ_APPROVING) < 0) { %>
				<tsa:tr field="rfq-requisitionNumber">
					<td align=right nowrap><a href="javascript: browseLookup('RfqHeader_requisitionNumber', 'reqapprovedbrowse'); void(0);" title="Click here to choose the <tsa:label labelName="rfq-requisitionNumber" defaultString="Requisition #" /> for this solicitation or enter the <tsa:label labelName="rfq-requisitionNumber" defaultString="Requisition #" /> in the box on the right."><tsa:label labelName="rfq-requisitionNumber" defaultString="Requisition #" checkRequired="true" />:</a>&nbsp;</td>
					<td><tsa:input labelName="rfq-requisitionNumber" type="mintext" name="RfqHeader_requisitionNumber" maxLength="20" value="<%=rfqHeader.getRequisitionNumber()%>" disabled="true" /></td>
				</tsa:tr>
		<% 	} else { %>
				<tsa:tr field="rfq-requisitionNumber">
					<td align=right nowrap><tsa:label labelName="rfq-requisitionNumber" defaultString="Requisition #" checkRequired="true" />:&nbsp;</td>
					<td><tsa:input labelName="rfq-requisitionNumber" type="mintext" name="RfqHeader_requisitionNumber" maxLength="20" value="<%=rfqHeader.getRequisitionNumber()%>" disabled="true" /></td>
				</tsa:tr>
		<% 	} %>
				<tsa:tr field="rfq-requisitioner">
					<td align=right nowrap><a href="javascript: browseLookup('RfqHeader_requisitionerCode', 'requisitioner'); void(0);" title="Click here to choose the <tsa:label labelName="rfq-requisitioner" defaultString="Requisitioner" /> for this solicitation or enter the <tsa:label labelName="rfq-requisitioner" defaultString="Requisitioner" /> in the box on the right."><tsa:label labelName="rfq-requisitioner" defaultString="Requisitioner" checkRequired="true" />:</a>&nbsp;</td>
					<td><tsa:input labelName="rfq-requisitioner" type="mintext" name="RfqHeader_requisitionerCode" tabIndex="23" maxLength="15" value="<%=rfqHeader.getRequisitionerCode()%>" onchange="upperCase(this); getNewInfo('requisitioner', this);" /></td>
				</tsa:tr>
				<tsa:tr field="rfq-requisitionerName">
					<td align=right nowrap><tsa:label labelName="rfq-requisitionerName" defaultString="Requisitioner Name" />:&nbsp;</td>
					<td><tsa:input labelName="rfq-requisitionerName" type="text" name="as_requisitionerName" size="24" maxLength="24" value="<%=requisitioner.getDisplayName()%>" onfocus="this.blur();" disabled="true" /></td>
				</tsa:tr>
				<tsa:tr field="rfq-department">
					<% if (!DictionaryManager.isLink(oid, "rfq-department")) { %>
					<td align=right nowrap><tsa:label labelName="rfq-department" defaultString="Department" checkRequired="true" />:&nbsp;</td>
					<td><input type="text" name="RfqHeader_departmentCode" tabindex="25" size="15" maxlength="15" value="<%=rfqHeader.getDepartmentCode()%>" onchange="upperCase(this);" <%=HtmlWriter.isReadOnly(oid, "rfq-department")%> /></td>
				<% } else  { %>
					<td align=right nowrap><a href="javascript: browseDepartmentCode(); void(0);" title="Click here to choose the <tsa:label labelName="rfq-department" defaultString="Department" /> for this solicitation or enter the <tsa:label labelName="rfq-department" defaultString="Department" /> in the box on the right." ><tsa:label labelName="rfq-department" defaultString="Department" checkRequired="true" />:</a>&nbsp;</td>
					<td><input type="text" name="RfqHeader_departmentCode" tabindex="25" size="15" maxlength="15" value="<%=rfqHeader.getDepartmentCode()%>" onchange="upperCase(this);" <%=HtmlWriter.isReadOnly(oid, "rfq-department")%> /></td>
				<% } %>
				</tsa:tr>
				<tsa:tr field="rfq-authorizedBy">
					<td align=right nowrap><a href="javascript: browseLookup('RfqHeader_authorizationCode', 'authorization'); void(0);"title="Click here to choose the <tsa:label labelName="rfq-authorizedBy" defaultString="Authorized By" /> for this solicitation or enter the <tsa:label labelName="rfq-authorizedBy" defaultString="Authorized By" /> in the box on the right." ><tsa:label labelName="rfq-authorizedBy" defaultString="Authorized By" checkRequired="true" />:</a>&nbsp;</td>
					<td><tsa:input labelName="rfq-authorizedBy" type="mintext" name="RfqHeader_authorizationCode" tabIndex="27" maxLength="15" value="<%=s_authorizedBy%>"  onchange="upperCase(this); getNewInfo('authby', this);" /></td>
				</tsa:tr>
				<tsa:tr field="rfq-authorizedByName">
					<td align=right nowrap><tsa:label labelName="rfq-authorizedByName" defaultString="Authorized By Name" />:&nbsp;</td>
					<td><tsa:input labelName="rfq-authorizedByName" type="text" name="as_authbyName" size="24" maxLength="24" value="<%=authorizedBy.getDisplayName()%>" onfocus="this.blur();" disabled="true" /></td>
				</tsa:tr>
				<tsa:tr field="rfq-RF02">
					<td align=right nowrap>
<%	if (DictionaryManager.isLink(oid, "rfq-RF02")) {	%>
						<a href="javascript: browseStd('RfqHeader_udf2Code','RF02');" title="Click here to choose the value for <tsa:label labelName="rfq-RF02" defaultString="UDF 2" /> for this solicitation or enter the value in the box on the right."><tsa:label labelName="rfq-RF02" defaultString="UDF 2" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="rfq-RF02" defaultString="UDF2" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
<%	if (oid.equalsIgnoreCase("vse06p")) { %>
					<tsa:td field="rfq-RF02">
						<select tabindex=23 name="RfqHeader_udf2Code" size=1 onchange="setUdf2Code();">
<%
		if (HiltonUtility.isEmpty(rfqHeader.getUdf2Code())) {
			rfqHeader.setUdf2Code(DictionaryManager.getInstance("inspection-level", oid).getLabelProperty("DEFAULT", ""));
		}
		Map inspectionLevels = new TreeMap(DictionaryManager.getInstance("inspection-level", oid).getPropertyMap());
		Iterator inspectionIterator = inspectionLevels.keySet().iterator();
		String	inspectionLevelCode = "";
		String inspectionLevelName = "";
		while (inspectionIterator.hasNext())
		{
			inspectionLevelCode = (String) inspectionIterator.next();
			inspectionLevelName = (String) inspectionLevels.get(inspectionLevelCode);

			if (inspectionLevelCode.equals("DEFAULT")) {
				continue;
			}
%>
						<option value="<%=inspectionLevelCode%>" <%if (rfqHeader.getUdf2Code().equalsIgnoreCase(inspectionLevelCode)) {%>SELECTED<%}%>><%=inspectionLevelName%></value>
<%	}	%>
						</select>
					</tsa:td>
<%	} else { %>
					<td><tsa:input labelName="rfq-RF02" type="mintext" name="RfqHeader_udf2Code" tabIndex="29" maxLength="15" value="<%=rfqHeader.getUdf2Code()%>" /></td>
<%	}	%>
				</tsa:tr>
				<tsa:tr field="rfq-RF04">
					<td align=right nowrap>
<%	if (DictionaryManager.isLink(oid, "rfq-RF04")) {	%>
						<a href="javascript: browseStd('RfqHeader_udf4Code','RF04');" title="Click here to choose the value for <tsa:label labelName="rfq-RF04" defaultString="UDF 4" /> for this solicitation or enter the value in the box on the right."><tsa:label labelName="rfq-RF04" defaultString="UDF 4" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="rfq-RF04" defaultString="UDF4" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
					<td><tsa:input labelName="rfq-RF04" type="mintext" name="RfqHeader_udf4Code" tabIndex="29" maxLength="15" value="<%=rfqHeader.getUdf4Code()%>" /></td>
				</tsa:tr>
				<tsa:tr field="rfq-RF06">
					<td align=right nowrap>
<%	if (DictionaryManager.isLink(oid, "rfq-RF06")) {	%>
						<a href="javascript: browseStd('RfqHeader_udf6Code','RF06');" title="Click here to choose the value for <tsa:label labelName="rfq-RF06" defaultString="UDF 6" /> for this solicitation or enter the value in the box on the right."><tsa:label labelName="rfq-RF06" defaultString="UDF 6" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="rfq-RF06" defaultString="UDF6" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
					<td><tsa:input labelName="rfq-RF06" type="mintext" name="RfqHeader_udf6Code" tabIndex="29" maxLength="15" value="<%=rfqHeader.getUdf6Code()%>" /></td>
				</tsa:tr>
				<tsa:tr field="rfq-RF08">
					<td align=right nowrap>
<%	if (DictionaryManager.isLink(oid, "rfq-RF08")) {	%>
						<a href="javascript: browseStd('RfqHeader_udf8Code','RF08');" title="Click here to choose the value for <tsa:label labelName="rfq-RF08" defaultString="UDF 8" /> for this solicitation or enter the value in the box on the right."><tsa:label labelName="rfq-RF08" defaultString="UDF 8" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="rfq-RF08" defaultString="UDF8" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
					<td><tsa:input labelName="rfq-RF08" type="mintext" name="RfqHeader_udf8Code" tabIndex="29" maxLength="15" value="<%=rfqHeader.getUdf8Code()%>" /></td>
				</tsa:tr>
				<tsa:tr field="rfq-RF10">
					<td align=right nowrap>
<%	if (DictionaryManager.isLink(oid, "rfq-RF10")) {	%>
						<a href="javascript: browseStd('RfqHeader_udf10Code','RF10');" title="Click here to choose the value for <tsa:label labelName="rfq-RF10" defaultString="UDF 10" /> for this solicitation or enter the value in the box on the right."><tsa:label labelName="rfq-RF10" defaultString="UDF 10" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="rfq-RF10" defaultString="UDF10" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
					<td><tsa:input labelName="rfq-RF10" type="mintext" name="RfqHeader_udf10Code" tabIndex="29" maxLength="15" value="<%=rfqHeader.getUdf10Code()%>" /></td>
				</tsa:tr>
				<tr>
					<tsa:td id="rfq-KitLabelRow" field="rfq-RFQKIT" align="right" noWrap="nowrap" >
					<tsa:label labelName="rfq-RFQKIT" defaultString="Kit" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="rfq-RFQKIT" noWrap="nowrap">
					<% String kitchecked = null;
					if (s_kit.equalsIgnoreCase("Y")) {
						kitchecked = "checked"; }%>
						<tsa:input type="checkbox" title="Check for kit" name="c_checkbox" tabIndex="4" value="Y" checked="<%=kitchecked%>"  onclick="setCheckbox(frm.RfqHeader_kit, 1);" />
						<tsa:hidden name="RfqHeader_kit" value="<%=rfqHeader.getKit()%>"/>
					</tsa:td>
				</tr>
				<tr>
					<tsa:td id="rfq-CategoryLabelRow" field="rfq-RFQCATEGORY" align="right" noWrap="nowrap" >
						<tsa:label labelName="rfq-RFQCATEGORY" defaultString="Request Category" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="rfq-RFQCATEGORY" >
					<select title="Select a Category" name="RfqHeader_requestCat" tabIndex="24">
						<option <% if (rfqHeader.getRequestCat().equals("")) {%> SELECTED <%}%> value=""></option>
			              <option <% if (rfqHeader.getRequestCat().equals("SPA")) {%> SELECTED <%}%> value="SPA">SRR Partner Affiliate</option>
			              <option <% if (rfqHeader.getRequestCat().equals("SA")) {%> SELECTED <%}%> value="SA">Staff Augmentation</option>
			              <option <% if (rfqHeader.getRequestCat().equals("M")) {%> SELECTED <%}%> value="M">Material</option>
			              <option <% if (rfqHeader.getRequestCat().equals("SL12")) {%> SELECTED <%}%> value="SL12">Services Level 1 or 2</option>
			              <option <% if (rfqHeader.getRequestCat().equals("SL3")) {%> SELECTED <%}%> value="SL3">Services Level 3</option>
			              <option <% if (rfqHeader.getRequestCat().equals("SRNC")) {%> SELECTED <%}%> value="SRNC">SRNS Central Inventory</option>
			              <option <% if (rfqHeader.getRequestCat().equals("SRNE")) {%> SELECTED <%}%> value="SRNE">SRNS Excess Inventory</option>
			              <option <% if (rfqHeader.getRequestCat().equals("C")) {%> SELECTED <%}%> value="C">Chemical</option>
			              <option <% if (rfqHeader.getRequestCat().equals("L")) {%> SELECTED <%}%> value="L">Lease</option>
					</select>
					</tsa:td>
				</tr>
				<tr>
					<tsa:td id="rfq-SetAsideLabelRow" field="rfq-RFQSetAside" align="right" noWrap="nowrap" >
						<tsa:label labelName="rfq-RFQSetAside" defaultString="Set Aside Type" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="rfq-RFQSetAside" >
					<tsa:input type="mintext" title="" name="RfqHeader_setAside" tabIndex="24" maxLength="15" value="<%=rfqHeader.getSetAside()%>" onchange="upperCase(this);"/>
					</tsa:td>
				</tr>
				<tr>
					<tsa:td id="rfq-estCostLabelRow" field="rfq-estCost" align="right" noWrap="nowrap" >
						<tsa:label labelName="rfq-estCost" defaultString="Est. Cost" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="rfq-estCost" >
					<tsa:input type="mintext" title="" name="RfqHeader_estimatedCost" tabIndex="24" maxLength="15" value="<%=HiltonUtility.getFormattedDollar(rfqHeader.getEstimatedCost(), oid)%>" style="text-align:right" onchange="addUp(this);"/>
					</tsa:td>
				</tr>
				<tr>
					<tsa:td id="corrosionEvalLabelRow" field="rfq-corrosionEval" align="right" noWrap="nowrap">
			        <tsa:label labelName="rfq-corrosionEval" defaultString="Corrosion Evaluation" checkRequired="true" noinstance="yes"/>:&nbsp;
			        </tsa:td>
			        <tsa:td id="corrosionEvalFieldRow" field="rfq-corrosionEval">
			        <tsa:input type="yesnoradio" title="" name="RfqHeader_corrosionEval" value="<%=s_corrosion_eval%>"/>
			        </tsa:td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan=3>
				<table border=0 cellpadding=0 cellspacing=0>
				<tsa:tr field="rfq-purpose">
					<td width=105px nowrap align=right valign=top><tsa:label labelName="rfq-purpose" defaultString="Purpose" checkRequired="true" />:&nbsp;</td>
					<td>
						<tsa:input labelName="rfq-purpose" type="textarea" name="RfqHeader_rfqDescription" tabIndex="31" rows="6" cols="64" wrap="nonvirtual" maxLength="255" onchange="checkLength(); upperCase(this);">${esapi:encodeForHTML(rfqHeader.rfqDescription)}</tsa:input>
					</td>
				</tsa:tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
			<td rowspan=2 valign=top><%@ include file="/rfq/rfq_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>
<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=550px>
<tr>
<%	if (editMode) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqHeaderUpdateById;RfqRetrieve'); void(0);"><tsa:label labelName="rfq-Save" defaultString="Save" /></a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqRetrieve'); void(0);"><tsa:label labelName="rfq-Return" defaultString="Return" /></a></div></td>
<%	} else {%>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqRetrieve'); void(0);"><tsa:label labelName="rfq-Return" defaultString="Return" /></a></div></td>
<%	}%>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var rfqnumber = "<%=s_rfqNumber%>";
	var fiscalyear = "<%=rfqHeader.getFiscalYear()%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	setTimeDisplay();

	function thisLoad()
	{
		f_StartIt();
<%	if (!editMode) { %>
			checkInputSettings();
			allowEdit = false;
			frm.allowBrowse.value = "true";
<%	} else if(bAllowEdit) { %>
		setInvalidFields("<%=invalidFields%>");
<%	}%>

	}

	function checkLength()
	{
		var desc = frm.RfqHeader_rfqDescription.value;

		if ( desc.length > 250 ) {
			alert("This description field can only hold 250 characters.  The description will be truncated.");
			desc = desc.substring(0, 250);
			frm.RfqHeader_rfqDescription.value = desc;
		}
	}

	function setTime()
	{
		var time = frm.due_time.value;
		var timeofday = frm.timeofday.options[frm.timeofday.selectedIndex].value;

		if ( time.indexOf(":") <= 0 ) {
			if (time.length <= 2) {
				if (time.length > 0) {
					time = time + ":00";
				}
				else {
					frm.due_time.value = "";
					frm.RfqHeader_bidDueTime.value = "";
					return;
				}
			}
			else {
				alert("This is not a valid time!");
				frm.due_time.value = "";
				frm.due_time.focus();
				return;
			}
		}

		var hour = time.substring(0,time.indexOf(":"));
		var min	 = time.substring(time.indexOf(":") + 1, time.length);

		if (hour > 12) {
			alert("This is not a valid time!");
			frm.due_time.value = "";
			frm.due_time.focus();
			return;
		}
		if (min > 59) {
			alert("This is not a valid time!");
			frm.due_time.value = "";
			frm.due_time.focus();
			return;
		}

		frm.due_time.value = time;

		if (timeofday == "pm") {
			hour = eval(hour);
			if (hour < 12) {
				hour = eval(hour + 12);
			}
			time = hour + ":" + min;
		}
		else {
			hour = eval(hour);
			if (hour == 12) {
				hour = eval(00);
			}
			time = hour + ":" + min;
		}
		frm.RfqHeader_bidDueTime.value = time;
	}

	function setTimeDisplay()
	{
		var time = frm.due_time.value;
		var hour = time.substring(0,time.indexOf(":"));
		var min	 = time.substring(time.indexOf(":") + 1, time.length);

		if (time.length == 0 || time == "") {
			frm.timeofday.selectedIndex = 0;
			frm.due_time.value = "";
			return;
		}
		hour = eval(hour);

		if (hour > 12) {
			frm.timeofday.selectedIndex = 0;
			hour = eval(hour - 12);
		}
		else if (hour == 12) {
			frm.timeofday.selectedIndex = 0;
		}
		else {
			if (hour == 00) {
				hour = eval(hour + 12);
			}
			frm.timeofday.selectedIndex = 1;
		}

		time = hour + ":" + min;
		frm.due_time.value = time;
	}

	function validateForm() {
		var handlers = frm.handler.value;
		if (handlers.indexOf("RfqHeaderUpdateById") >= 0) {
			var alertMessage = "";

			if (frm.RfqHeader_rfqDate && !chkdate(frm.RfqHeader_rfqDate)) {
				alertMessage += "\n<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-solicitationDate", "Solicitation Date")%> is not a valid date.";
			}
			if (frm.RfqHeader_dueDate && !chkdate(frm.RfqHeader_dueDate)) {
				alertMessage += "\n<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-dueDate", "Due Date")%> is not a valid date.";
			}

			if (alertMessage.length > 0) {
				alert("Please fix the following problems:\n" + alertMessage);
				return false;
			}
		}
		return true;
	}

	function setUdf2Code()
	{
		if (verifyAction("Change all line items to match solicitation header inspection level?"))
		{
			frm.resetUdf2Code.value = true;
		}
		else
		{
			frm.resetUdf2Code.value = false;
		}
	}

	function browseDepartmentCode() {
		<% if(HtmlWriter.isReadOnly(oid, "rfq-department").equalsIgnoreCase("disabled")){%>
			var currentAllowBrowse = frm.allowBrowse.value;
			frm.allowBrowse.value = false;
			browseLookup('RfqHeader_departmentCode', 'department');
			frm.allowBrowse.value = currentAllowBrowse;
		<% }else{%>
			browseLookup('RfqHeader_departmentCode', 'department');
		<% }%>
	}

	function setCheckbox(fld, x)
	{
		fld.value = 'N';
		if ( frm.c_checkbox[x].checked )
		{
			fld.value = 'Y';
		}
		return true;
	}

	function addUp(field)
	{
		var price_dec = <%=Integer.valueOf(s_dollar_decimals).intValue()%>;
		var p = nformat(eval(nfilter(field)),price_dec);
		field.value = p;
	}

// End Hide script -->
</SCRIPT>