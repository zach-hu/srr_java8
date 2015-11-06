<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<%
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String poStatus = "";
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

	if (HiltonUtility.isEmpty(s_req_number)){s_req_number = "N/A";}

	String	s_current_process = "HEADER_GENERAL_INFO";
	String	s_current_page = "/requests/req_general_info.jsp";
	String	s_current_method = "RequisitionHeaderUpdate";
	String	s_current_process_method = "";
	String	invalidFields = (String) request.getAttribute("invalidFields");
	boolean fdcsEnabled = propertiesManager.getProperty("FDCS","Enabled","N").equalsIgnoreCase("Y") ;
	boolean fpeUser = user.isAFpe() ;
	boolean engineerUser = user.isAEngineer() ;
	boolean msrEngineer = user.isAnAdministeredBy();

	while(!HiltonUtility.isEmpty(invalidFields) && invalidFields.startsWith(";")) {
		if (invalidFields.length() > 1) {
			invalidFields = invalidFields.substring(1);
		} else {
			invalidFields = "";
		}
	}

	boolean bAllowEdit = true;
	boolean bAllowChangeReqEdit = true;
	boolean bAllowPlannedDateEdit = true;
	
	if ((s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVING) >= 0 && s_req_type.equals("M") && s_requisitioner_code.equals(uid))  || s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0) {
		bAllowEdit = false;
	}else if (s_req_type.equals("M") && ( fpeUser || msrEngineer )) {
		if  (s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) >= 0)  {
			bAllowEdit  = false ;
		}
	}
	if(s_req_type.equals("P") && s_req_status.compareTo(DocumentStatus.REQ_REJECTED) == 0 && !fpeUser){
		bAllowEdit = false;
	}
	if (s_req_status.compareTo(DocumentStatus.REQ_INPROGRESS) >= 0 && s_req_type.equals("C")
				&& s_udf_1_code != null && s_udf_1_code.contains("RESALE")
				&& s_udf_14_code != null && s_udf_14_code.equals("DBS") ) {
		bAllowChangeReqEdit = false;
	}
	List transactionTypeList = (List) request.getAttribute("transactionTypeList");
	List resaleTypeList = (List) request.getAttribute("resaleTypeList");
	List chargeCodeList = (List) request.getAttribute("chargeCodeList");

	if(transactionTypeList == null) transactionTypeList = new ArrayList();
	if(resaleTypeList == null) resaleTypeList = new ArrayList();
	if(chargeCodeList == null) chargeCodeList = new ArrayList();

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
<tsa:hidden name="resetUdf2Code" value="false"/>
<tsa:hidden name="udf_field_depencies" value="<%=udf_field_depencies%>"/>
<tsa:hidden name="updateAllDepartmentCode" value="N"/>
<tsa:hidden name="icHeaderHistoryEdit" value="<%=icHeaderHistoryEdit%>"/>
<tsa:hidden name="editFieldsApprover" value=""/>

<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tr>
			<td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
	<% if (s_req_type.equals("M")) { %>
		<tr>
			<td noWrap="nowrap" class="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="req-type" defaultString="Req Type"/>&nbsp;<tsa:label labelName="req-general-information" defaultString="General Information"></tsa:label></div>
			</td>
		</tr>
	<% } else { %>
		<tr>
			<td noWrap="nowrap" class="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="req-general-information" defaultString="General Information"></tsa:label></div>
			</td>
		</tr>
	<% } %>
		</table>
	</td>
	<td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign="bottom" height="30px" width="*">
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
		<tr>
			<tsa:td align="right"><b><tsa:label labelName="req-requisition" defaultString="Requisition #"></tsa:label>:</b></tsa:td>
			<tsa:td width="150px"><%=headerEncoder.encodeForHTML(s_req_number)%></tsa:td>
		</tr>
		<tr>
			<tsa:td align="right"><b><tsa:label labelName="req-status" defaultString="Status"></tsa:label>:</b></tsa:td>
			<tsa:td width="150px"><%=DocumentStatus.toString(requisitionHeader.getStatus())%></tsa:td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td width="100%" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height="2px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<%@ include file="/requests/req_info.jsp" %>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
<tr>
	<td valign="top" align="center">
		<table border="0" cellspacing="0" cellpadding="0" height="100%" width="90%">
		<tr>
			<td>
			<!--				Page Inside Begin						-->
				<table border="0" cellpadding="0" cellspacing="0" align="center">
				<tr>
					<tsa:td field="requisitionDate" width="" align="right" noWrap="nowrap">
						<tsa:label labelName="requisitionDate" defaultString="Request Date" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="requisitionDate" noWrap="nowrap" >
						<tsa:input type="mintext" title="Enter Requisition Date" name="RequisitionHeader_requisitionDate" tabIndex="5" value="<%=HiltonUtility.getFormattedDate(requisitionHeader.getRequisitionDate(), oid)%>"/>
						<a href="javascript: show_calendar('RequisitionHeader_requisitionDate', '<%=s_date_format%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign="bottom" border="0"></a>
					</tsa:td>
					<!--				Medium						-->
					<%	if (s_req_type.equals("M")) { %>
					<tsa:td field="req-buyer" align="right" noWrap="nowrap" >
					<a href="javascript: browseReqEngineer(); void(0);" title="Click here to choose the <tsa:label labelName='req-buyer' defaultString='Engineer' noinstance='yes'/> for this requisition or enter the <tsa:label labelName='req-buyer' defaultString='Engineer' noinstance='yes'/>ID in the box on the right."> <tsa:label labelName="req-buyer" defaultString="Engineer" noinstance="yes" checkRequired="true" />:</a>&nbsp;
					</tsa:td>
					<tsa:td field="req-buyer" >
					<tsa:input type="mintext" title="Enter Engineer ID" name="RequisitionHeader_buyer" tabIndex="18" maxLength="15" value="<%=requisitionHeader.getBuyer()%>" onchange="upperCase(this); getNewInfo('engineer', this);"  disabled="disabled" />
					</tsa:td>
					<%}  else if (!s_req_type.equals("K")) { %>
					<tsa:td field="req-buyer" align="right" noWrap="nowrap" >
					<a href="javascript: browseReqBuyer(); void(0);" title="Click here to choose the <tsa:label labelName='req-buyer' defaultString='Buyer' noinstance='yes'/> for this requisition or enter the <tsa:label labelName='req-buyer' defaultString='Buyer' noinstance='yes'/>ID in the box on the right."> <tsa:label labelName="req-buyer" defaultString="Buyer" noinstance="yes" checkRequired="true" />:</a>&nbsp;
					</tsa:td>
					<tsa:td field="req-buyer" >
					<tsa:input type="mintext" title="Enter Buyer ID" name="RequisitionHeader_buyer" tabIndex="18" maxLength="15" value="<%=requisitionHeader.getBuyer()%>" onchange="upperCase(this); getNewInfo('buyer', this);"/>
					</tsa:td>
					<%} %>
				</tr>
				<tr>
				<%	if (s_req_type.equals("C") && (s_req_status.equals(DocumentStatus.REQ_INPROGRESS) || s_req_status.equals(DocumentStatus.REQ_REJECTED) || s_req_status.equals(DocumentStatus.REQ_RECALLED)) && !fpeUser) { %>
					<tsa:td field="req-requisitioner" width="" align="right" noWrap="nowrap" >
					<tsa:label labelName="req-requisitioner" defaultString="Requisitioner Name" noinstance="yes" checkRequired="true" />:&nbsp;
					</tsa:td>
					<tsa:td field="req-requisitioner" >
					<tsa:input type="mintext" title="Enter the User ID of the Requisitioner" name="RequisitionHeader_requisitionerCode" tabIndex="7" maxLength="15" value="<%=s_requisitioner_code%>" onchange="upperCase(this); ckReqBy(); getNewInfo('requisitioner', this);" readOnly="true"/>
					</tsa:td>
				<%	} else { %>
					<tsa:td field="req-requisitioner" width="" align="right" noWrap="nowrap" ><!-- pase de (2) -->
					<a href="javascript: browseLookup('RequisitionHeader_requisitionerCode', 'user'); void(0);" title="Click here to change the <tsa:label labelName='req-requisitioner' defaultString='Requisitioner Name' noinstance='yes'/>"> <tsa:label labelName="req-requisitioner" defaultString="Requisitioner Name" noinstance="yes" checkRequired="true" />:</a>&nbsp;
					</tsa:td>
					<tsa:td field="req-requisitioner" >
					<tsa:input type="mintext" title="Enter the User ID of the Requisitioner" name="RequisitionHeader_requisitionerCode" tabIndex="7" maxLength="15" value="<%=s_requisitioner_code%>" onchange="upperCase(this); ckReqBy(); getNewInfo('requisitioner', this);"/>
					</tsa:td>
				<%	} %>
				<%	if (!s_req_type.equals("K")) { %><!-- pase de (1) -->
					<tsa:td field="req-buyerName" align="right" noWrap="nowrap" >
						<tsa:label labelName="req-buyerName" defaultString="Buyer Name" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="req-buyerName" >
					<tsa:input type="midtext" name="as_buyerName" maxLength="24" value="<%=buyer.getDisplayName()%>" onfocus="this.blur();" disabled="disabled" />
					</tsa:td>
					<%} %>
				</tr>
				<tr>
<%	if (bAllowEdit && bAllowChangeReqEdit) {%>
					<tsa:td field="req-fiscalYear" width="120px" align="right" noWrap="nowrap" >
					<tsa:label labelName="req-fiscalYear" defaultString="Fiscal Year" noinstance="yes" checkRequired="true" />:&nbsp;
					</tsa:td>
					<tsa:td field="req-fiscalYear" >
					<select title="Select Fiscal Year of the requisition" name="RequisitionHeader_fiscalYear" tabindex="6">
					<%=HiltonUtility.getFiscalYearOptions(oid, userTimeZone, "REQ", s_fiscal_year)%>
					</select>
					</tsa:td>
<%	} else {%>
					<tsa:td field="req-fiscalYear" width="120px" align="right" noWrap="nowrap" >
					<a href="javascript: browseFiscalYear(); void(0);" title="Select the value for <tsa:label labelName='req-fiscalYear' defaultString='Fiscal Year' noinstance='yes'/>"><tsa:label labelName="req-fiscalYear" defaultString="Fiscal Year" noinstance="yes" checkRequired="true" />:&nbsp;
					</tsa:td>
					<tsa:td field="req-fiscalYear" >
					<tsa:input type="mintext" title="Fiscal Year of the requisition" name="RequisitionHeader_fiscalYear" tabIndex="6" maxLength="4" value="<%=s_fiscal_year%>"  onfocus="this.blur();" disabled="disabled" />
					</tsa:td>
<%	} %>
				</tr>
				<tsa:tr field="req-plannedDate">
					<tsa:td field="req-plannedDate"  docType="yes" align="right" noWrap="nowrap">
						<tsa:label labelName="req-plannedDate" defaultString="Planned Date" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td  field="req-plannedDate"	>
						<tsa:input id="req-plannedDate" type="mintext" title="Enter Requisition Date" name="RequisitionHeader_plannedDate" tabIndex="5" value="<%=HiltonUtility.getFormattedDate(requisitionHeader.getPlannedDate(), oid)%>"/>
						<a href="javascript: show_calendar('RequisitionHeader_plannedDate', '<%=s_date_format%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign="bottom" border="0"></a>
					</tsa:td>
				</tsa:tr>
				<tr>
					<tsa:td field="req-requisitionerName" width="" align="right" noWrap="nowrap" >
					<tsa:label labelName="req-requisitionerName" defaultString="Requisitioner Name" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="req-requisitionerName" >
					<tsa:input type="midtext" name="as_requisitionerName" value="<%=requisitioner.getDisplayName()%>" onfocus="this.blur();" disabled="disabled"/>
					</tsa:td>
				<%	if (auto_award_allreq.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(auto_award_typepo)) { %>
						<tsa:td align="right" field="req-RQ01" noWrap="nowrap">
							<tsa:label labelName="req-RQ01" defaultString="UDF1" checkRequired="true" noinstance="yes"/>:&nbsp;
						</tsa:td>
						<tsa:td field="req-RQ01">
							<select tabindex="6" name="RequisitionHeader_udf1Code" size="1">
								<option <% if (s_udf_1_code.equals("PO")) {%> SELECTED <%}%> value="PO"><tsa:label labelName="req-purchase-order" defaultString="Purchase Order"></tsa:label></option>
								<option <% if (s_udf_1_code.equals("RO")) {%> SELECTED <%}%> value="RO">Blanket Release Order</option>
							</select>
						</tsa:td>
					<% } else { %>
					<%	if (!s_req_type.equals("H")) { %>
					<tsa:td field="req-RQ01" align="right" noWrap="nowrap" >
					<%	if (DictionaryManager.isLink(oid, "req-RQ01")) {	%>
						<% if(oid.equals("TTR09P")){ %>
							<tsa:label labelName="req-RQ01" defaultString="UDF1" noinstance="true" checkRequired="true"/>:&nbsp;
						<%	}else{ %>
							<a href="javascript: browseStdFieldsUDF('RequisitionHeader_udf1Code', 'RQ01'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ01' defaultString='UDF1' noinstance='true' /> for this requisition or enter the value in the box on the right."> <tsa:label labelName="req-RQ01" defaultString="UDF1" noinstance="true" checkRequired="true"/>:</a>&nbsp;
						<%	}	%>
					<%	} else {	%>
						<tsa:label labelName="req-RQ01" defaultString="UDF1" checkRequired="true" noinstance="yes"/>:&nbsp;
					<%	}	%>
					</tsa:td>
          			<tsa:td id="rq01FieldRow" field="req-RQ01" colspan="3" >
          			<% if(oid.equals("TTR09P")){ %>
          				<select name="RequisitionHeader_udf1Code" tabIndex="11" style="width: 115px" value="<%=requisitionHeader.getUdf1Code()%>" onchange="upperCase(this);configureFieldsUDF();">
							<option value=""></option>
          				<% for (int il = 0; il < transactionTypeList.size(); il++) {
							StdTable stdTable = (StdTable) transactionTypeList.get(il);
							StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
							<option value="<%=stdTablePK.getTableKey()%>" <%if (requisitionHeader.getUdf1Code().equals(stdTablePK.getTableKey())) {%>selected<%}%>><%=stdTable.getTableKey()%></option>
          				<%	}	%>
          				</select>
          			<%	}else{ %>
          				<tsa:input labelName="req-RQ01" type="yesnoradio" title="" name="RequisitionHeader_udf1Code" value="<%=requisitionHeader.getUdf1Code() %>" onchange="upperCase(this);configureFieldsUDF();"/>
          			<%	}	%>
          			</tsa:td>
          			<%	}	%>
				<% } %>
				</tr>
				<tr>
					<%	if (s_req_type.equals("M")) { %>
					<tsa:td field="req-OWNER" align="right" noWrap="nowrap" >
					<a href="javascript: browseReqFpe(); void(0);" title="Click here to choose the <tsa:label labelName='req-OWNER' defaultString='Entered by' noinstance='yes'/> for this requisition or enter the <tsa:label labelName='req-OWNER' defaultString='FPE' noinstance='yes'/>ID in the box on the right."> <tsa:label labelName="req-OWNER" defaultString="FPE" noinstance="yes" checkRequired="true" />:</a>&nbsp;
					</tsa:td>
					<tsa:td field="req-OWNER" >
					<tsa:input type="mintext" title="Enter FPE ID" name="RequisitionHeader_owner" tabIndex="18" maxLength="15" value="<%=s_owner_code%>" onchange="upperCase(this); getNewInfo('fpe', this);" readOnly="true"/>
					</tsa:td>
					<% } else { %>
					<tsa:td field="req-OWNER" align="right" noWrap="nowrap">

					<tsa:label labelName="req-OWNER" defaultString="Entered By" checkLink="false" checkRequired="true" noinstance="yes" fieldName="as_enteredBy"/>:&nbsp;
					</tsa:td>
					<tsa:td field="req-OWNER" >
					<tsa:input type="mintext" title="Enter Engineer ID" name="as_enteredBy" tabIndex="18" maxLength="15" value="<%=s_owner_code%>" onchange="upperCase(this); getNewInfo('user', this);"/>
					</tsa:td>
					<% } %>
<%					if (HiltonUtility.isEmpty(s_udf2_for_type) || s_udf2_for_type.equalsIgnoreCase(requisitionHeader.getRequisitionType())) { %>
						<tsa:td field="req-RQ02" id="rq02LabelRow" align="right" noWrap="nowrap">
						<%	if (DictionaryManager.isLink(oid, "req-RQ02")) {	%>
							<a href="javascript: browseStd('RequisitionHeader_udf2Code', 'RQ02'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ02' noinstance='true' defaultString='UDF2' docType='ture'/> for this requisition or enter the value in the box on the right."> <tsa:label labelName="req-RQ02" noinstance="true" defaultString="UDF2" docType="true" checkRequired="true" />:</a>&nbsp;
						<%	} else {	%>
							<tsa:label labelName="req-RQ02" defaultString="UDF2" checkRequired="true" noinstance="yes"/>:&nbsp;
						<%	}	%>
						</tsa:td>
						<%	if (oid.equalsIgnoreCase("vse06p")) { %>
						<tsa:td field="req-RQ02" >
							<select tabindex="23" name="RequisitionHeader_udf2Code" size="1" onchange="setUdf2Code();">
	<%
							if (HiltonUtility.isEmpty(requisitionHeader.getUdf2Code())) {
								requisitionHeader.setUdf2Code(DictionaryManager.getInstance("inspection-level", oid).getLabelProperty("DEFAULT", ""));
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
							<option value="<%=inspectionLevelCode%>" <%if (requisitionHeader.getUdf2Code().equalsIgnoreCase(inspectionLevelCode)) {%>SELECTED<%}%>><%=inspectionLevelName%></option>
	<%						}	%>
							</select>
						</tsa:td>
	<%					} else { %>
						<tsa:td id="rq02FieldRow" field="req-RQ02" >
						<tsa:input type="mintext" title="" name="RequisitionHeader_udf2Code" tabIndex="23" maxLength="15" value="<%=requisitionHeader.getUdf2Code()%>" onchange="upperCase(this);"/>
						</tsa:td>
	<%					}
					}   %>
				</tr>
				<tr>
					<tsa:td field="req-OWNERFULLNAME" align="right" noWrap="nowrap">
					<tsa:label labelName="req-OWNERFULLNAME" defaultString="Entered By" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="req-OWNERFULLNAME" >
						<tsa:input type="midtext" name="as_enteredByName" value="<%= owner.getDisplayName() %>" disabled="disabled" />
					</tsa:td>

					<tsa:td id="rq03LabelRow" field="req-RQ03" align="right" noWrap="nowrap" >
					<%	if (DictionaryManager.isLink(oid, "req-RQ03")) {	%>
						<% if(oid.equals("TTR09P")){ %>
							<tsa:label labelName="req-RQ03" defaultString="UDF3" noinstance="true" checkRequired="true"/>:&nbsp;
						<%	}else{ %>
							<a href="javascript: browseStdFieldsUDF('RequisitionHeader_udf3Code', 'RQ03'); void(0);" title="Click here to choose the value for <tsa:label labelName='req-RQ03' defaultString='UDF3' noinstance='true' /> for this requisition or enter the value in the box on the right."> <tsa:label labelName="req-RQ03" defaultString="UDF3" noinstance="true" checkRequired="true"/>:</a>&nbsp;
						<%	}	%>
					<%	} else {	%>
						<tsa:label labelName="req-RQ03" defaultString="UDF3" checkRequired="true" noinstance="yes"/>:&nbsp;
					<%	}	%>
					</tsa:td>
					<tsa:td id="rq03FieldRow" field="req-RQ03" >
						<% if(oid.equals("TTR09P")){ %>
							<select name="RequisitionHeader_udf3Code" tabIndex="12" style="width: 115px" value="<%=requisitionHeader.getUdf3Code()%>" onchange="upperCase(this);configureFieldsUDF();">
								<option value=""></option>
	          				<% for (int il = 0; il < resaleTypeList.size(); il++) {
								StdTable stdTable = (StdTable) resaleTypeList.get(il);
								StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
								<option value="<%=stdTablePK.getTableKey()%>" <%if (requisitionHeader.getUdf3Code().equals(stdTablePK.getTableKey())) {%>selected<%}%>><%=stdTable.getTableKey()%></option>
	          				<%	}	%>
	          				</select>
	          			<% } else if (oid.equals("SRR10P")) { %>
							<tsa:input type="mintext" tabIndex="12" name="RequisitionHeader_udf3Code" id="RequisitionHeader_udf3Code" size="1" value="<%=requisitionHeader.getUdf3Code()%>" labelName="req-RQ03"/>
						<%	}else{ %>
							<tsa:input type="mintext" title="" name="RequisitionHeader_udf3Code" tabIndex="12" maxLength="15" value="<%=requisitionHeader.getUdf3Code()%>" onchange="upperCase(this);configureFieldsUDF();"/>
						<%	}	%>
					</tsa:td>
				</tr>

				<tr>
        			<tsa:td field="req-department" align="right" noWrap="nowrap" >
						<tsa:label labelName='req-department' defaultString='Department' noinstance='true' fieldName="RequisitionHeader_departmentCode"/>:&nbsp;
					</tsa:td>
					<tsa:td field="req-department" >
						<tsa:input type="midtext" name="RequisitionHeader_departmentCode" tabIndex="9" maxLength="15" value="<%=s_department_code%>" onchange="upperCase(this); getNewInfo('department', this);"/>
					</tsa:td>

					<tsa:td id="req-gfpGfmLabelRow" field="req-gfpGfm" align="right" noWrap="nowrap" >
						<tsa:label labelName="req-gfpGfm" defaultString="GFP/GFM item" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td id="req-gfpGfmFieldRow" field="req-gfpGfm" noWrap="nowrap">
					<% String gfpchecked = "";
					if (s_gfp_gfm.equalsIgnoreCase("Y")) {
						gfpchecked = "Y"; }%>
						<input type="checkbox" title="Check for GFP/GFM item" name="c_checkbox" tabIndex="4" value="Y" <% if (s_gfp_gfm.equals("Y")) { %> CHECKED <% } %> onclick="setCheckbox(frm.RequisitionHeader_gfpGfm, 0);" >
						<tsa:hidden name="RequisitionHeader_gfpGfm" value="<%=requisitionHeader.getGfpGfm()%>"/>
					</tsa:td>
				</tr>
				<% if (oid.equals("SRR10P")) { %>
					<jsp:include page="/requests/req_srr_general_info.jsp" />
				<% } else { %>
					<jsp:include page="/requests/req_general_info_include.jsp" />
				<% } %>
				</table>
<!--				Page Inside End						-->
			</td>
			<td>&nbsp;</td>
			<td valign="top">
			</td>
		</tr>
<%		if (!s_req_type.equals("K")) { %>
		<tr>
			<td colspan="2">
				<table border="0" cellpadding="2" cellspacing="0">
				<tsa:tr field="req-purpose">
					<tsa:td width="136px" noWrap="nowrap" align="right" valign="top">
					<tsa:label labelName="req-purpose" defaultString="Purpose"  docType="s_req_type" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<td>
					<tsa:input type="textarea" title='<%=DictionaryManager.getInstance(\"labels\", oid).getLabelProperty(\"req-purpose\" + \"-alt\", \"\")%>' name="RequisitionHeader_internalComments" tabIndex="30"  rows="6" cols="64" maxLength="255" onkeydown="textCounter(this, 255);" onkeyup="textCounter(this,255);" onchange="textCounter(this,255); upperCase(this);">${esapi:encodeForHTML(requisitionHeader.internalComments)}</tsa:input>
					</td>
				</tsa:tr>
				</table>
			</td>
		</tr>
<%	}	%>
		</table>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
		<td rowspan="2" valign="top" align="right"><%@ include file="/requests/req_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>
<br>
<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td width="50%" align="center"><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionHeaderUpdate;RequisitionRetrieve'); void(0);"><img class="button" src="<%=contextPath%>/images/button_save.gif" border="0" alt="Save"></a></td>
	<td width="50%" align="center"><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionRetrieve'); void(0);"><img class="button" src="<%=contextPath%>/images/button_return.gif" border="0" alt="Return"></a></td>
</tr>
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
	var previousDepartmentCode = "<%=requisitionHeader.getDepartmentCode()%>";
    var currentRow = 0;

	var navMenu = getNavCookie("navArray");
	if (navMenu.indexOf("<%=RequisitionType.toString(s_req_type, oid)%>") < 0)
	{
		setNavCookie("/requests/req_general_info.jsp", 'RequisitionCreate', "<%=RequisitionType.toString(s_req_type, oid)%>");
	}

	<%if(s_req_type.equalsIgnoreCase("M")){%>
		hideRightPanel();
	<%} %>

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
<%		if (oid.equalsIgnoreCase("bsc04p")) {
			if (!s_req_type.equals("H"))
			{ %>
				setDisplayOptions();
<%			}
		}

		if (!bAllowEdit || !bAllowChangeReqEdit) { %>
			checkInputSettings();
			allowEdit = false;

<% 		if (bAllowPlannedDateEdit) {%>
				$('#req-plannedDate').removeAttr("disabled");
				allowInputEdit(frm.RequisitionHeader_plannedDate, true);

<%		}
if (bAllowEdit) { %>
				allowInputEdit(frm.RequisitionHeader_internalComments, true);
				setInvalidFields("<%=headerEncoder.encodeForJavaScript(invalidFields)%>");
<%			}
		} else { %>
			setInvalidFields("<%=headerEncoder.encodeForJavaScript(invalidFields)%>");
			hideRightPanel('hidden');
			<% if (udf_field_depencies.equals("Y")) {%>
				configureFieldsUDF();
			<% } %>
<%		}%>
<%		if (typeIterator != null && securityTypes != null) { %>
			allowEditApprover();
<%		} %>
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

	function setUdf2Code()
	{
		if (verifyAction("Change all line items to match requisition header inspection level?"))
		{
			frm.resetUdf2Code.value = true;
		}
		else
		{
			frm.resetUdf2Code.value = false;
		}
	}

	function validateForm() {
		var handlers = frm.handler.value;

		if (handlers.indexOf("RequisitionHeaderUpdate") >= 0) {
			var alertMessage = "";

			if (frm.RequisitionHeader_requisitionDate && !chkdate(frm.RequisitionHeader_requisitionDate)) {
				alertMessage += "\n<%=DictionaryManager.getLabel(oid, "requisitionDate", "Requisition Date")%> is not a valid date.";
			}

			if(frm.RequisitionHeader_plannedDate && !chkdate(frm.RequisitionHeader_plannedDate)){
				alertMessage +="\n<%=DictionaryManager.getLabel(oid,"plannedDate", "Planned Date")%> is not a valid date.";
			}

			if (alertMessage.length > 0) {
				alert("Please fix the following problems:\n" + alertMessage);
				return false;
			}
		}
		return true;
	}
	function browseReqBuyer()
	{
		popupParameters = "browseName=buyer" + ";allowBrowse=" + frm.allowBrowse.value;
		popupParameters = popupParameters + ";formField=RequisitionHeader_buyer";
		<%if (oid.equalsIgnoreCase("QRI06P")){	%>
			popupParameters = popupParameters + ";colname=UserProfile_locale;operator==;filter_txt=" + frm.RequisitionHeader_udf1Code.value + ";logicalOperator=AND;originalFilter=N;sort=N;"
		<%}else{%>
			<% if (propertiesManager.getProperty("ASSIGNMENT","ReqBuyerWarrantTotal","N").equalsIgnoreCase("Y"))
			{%>
				popupParameters = popupParameters + ";colname=UserProfile_warrantAmount;operator=>;filter_txt=<%=requisitionHeader.getTotal() %>;logicalOperator=AND;originalFilter=N;sort=N;"
			<%}%>
		<%}%>
		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=650', 'HEIGHT=500');
	}
	function browseReqEngineer()
	{
		popupParameters = "browseName=msr-engineer" + ";allowBrowse=" + frm.allowBrowse.value;
		popupParameters = popupParameters + ";formField=RequisitionHeader_buyer";
		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=650', 'HEIGHT=500');
	}
	function browseReqFpe()
	{
		popupParameters = "browseName=fpe" + ";allowBrowse=" + frm.allowBrowse.value;
		popupParameters = popupParameters + ";formField=RequisitionHeader_owner";
		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=650', 'HEIGHT=500');
	}

	function browseFiscalYear()
	{
		popupParameters = "browseName=fiscal-year";
		popupParameters = popupParameters + ";formField=RequisitionHeader_fiscalYear";
		popupParameters = popupParameters + ";colname=AutoGen_id_documentType;operator==;filter_txt=REQ;logicalOperator=AND;originalFilter=N;sort=N;"
		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=650', 'HEIGHT=500');
	}
	function setDisplayOptions()
	{
		var udf4Code = frm.RequisitionHeader_udf4Code.value;
		var yesNo1 = frm.yesno_1.value;
		var yesNo2 = frm.yesno_2.value;
		var yesNo3 = frm.yesno_3.value;
		var yesNo4 = frm.yesno_4.value;
		var servicesStartLabel = "<%=DictionaryManager.getLabel(oid, "req-servicesStartDate", "Services Start Date", s_req_type, false)%>:&nbsp;";
		if (udf4Code == "SW" || udf4Code == "GO" || udf4Code == "")
		{
			frm.RequisitionHeader_udf4Code[0].selected = true;
			//hideArea("udf5Row");
     		hideArea("udf13Row");
			hideArea("contratcNumberRow");
			hideArea("yesNo1Row");
			hideArea("yesNo2Row");
			hideArea("servicesStartRow");
			hideArea("servicesEndRow");
			hideArea("yesNo3Row");
			hideArea("yesNo4Row");
		}
		if (udf4Code == "NC")
		{
			hideArea("udf5Row");
      		hideArea("udf13Row");
			displayArea("contratcNumberRow");
			displayArea("yesNo1Row");
			displayArea("yesNo2Row");
			displayArea("servicesStartRow");
			setInnerHTML("servicesStartDate", servicesStartLabel);
			displayArea("servicesEndRow");
			displayArea("yesNo3Row");
			displayArea("yesNo4Row");
		}
		if (udf4Code == "CE")
		{
			displayArea("udf5Row");
      		hideArea("udf13Row");
			displayArea("yesNo1Row");
			displayArea("yesNo2Row");
			displayArea("servicesStartRow");
			setInnerHTML("servicesStartDate", servicesStartLabel);
			displayArea("servicesEndRow");
			displayArea("yesNo3Row");
			displayArea("yesNo4Row");
		}
		if (udf4Code == "RP" || udf4Code == "RI")
		{
			//displayArea("udf5Row");
			hideArea("udf5Row");
      		displayArea("udf13Row");
			hideArea("contratcNumberRow");
			hideArea("yesNo1Row");
			displayArea("yesNo2Row");
			hideArea("servicesStartRow");
			hideArea("servicesEndRow");
			displayArea("yesNo3Row");
			displayArea("yesNo4Row");
		}
		if (udf4Code == "ND" || udf4Code == "NA")
		{
			displayArea("udf5Row");
      		hideArea("udf13Row");
			hideArea("yesNo1Row");
			displayArea("yesNo2Row");
			hideArea("servicesStartRow");
			hideArea("servicesEndRow");
			displayArea("yesNo3Row");
			displayArea("yesNo4Row");
		}
		if (udf4Code == "IP" || udf4Code == "ID")
		{
			displayArea("udf5Row");
      		hideArea("udf13Row");
			displayArea("yesNo1Row");
			displayArea("yesNo2Row");
			hideArea("servicesStartRow");
			hideArea("servicesEndRow");
			displayArea("yesNo3Row");
			displayArea("yesNo4Row");
		}
		if (udf4Code == "PO")
		{
			//hideArea("udf5Row");
      		hideArea("udf13Row");
			hideArea("contratcNumberRow");
			displayArea("yesNo1Row");
			//displayArea("yesNo2Row");
			hideArea("yesNo2Row");
			displayArea("servicesStartRow");
			<% if (oid.equalsIgnoreCase("BSC04P")) { %>
			setInnerHTML("servicesStartDate", "<font class=requiredLabelHighLight>Needed By Date</font>: ");
			<% } else { %>
			setInnerHTML("servicesStartDate", "Needed By Date: ");
			<% } %>
			hideArea("servicesEndRow");
			hideArea("yesNo3Row");
			hideArea("yesNo4Row");
		}
		if (yesNo1 == "Y") {
			frm.yesno1[0].checked = true;
		}
		else if (yesNo1 == "N") {
			frm.yesno1[1].checked = true;
		}
		if (yesNo2 == "Y") {
			frm.yesno2[0].checked = true;
		}
		else if (yesNo2 == "N") {
			frm.yesno2[1].checked = true;
		}
		if (yesNo3 == "Y") {
			frm.yesno3[0].checked = true;
		}
		else if (yesNo3 == "N") {
			frm.yesno3[1].checked = true;
		}
		if (yesNo4 == "Y") {
			frm.yesno4[0].checked = true;
		}
		else if (yesNo4 == "N") {
			frm.yesno4[1].checked = true;
		}
	}


	function setYesNo1()
	{
		if (frm.yesno1[0].checked)
		{
			frm.yesno_1.value = "Y";
		}
		else if (frm.yesno1[1].checked)
		{
			frm.yesno_1.value = "N";
		}
		setYesNoFields();
	}

	function setYesNo2()
	{
		if (frm.yesno2[0].checked)
		{
			document.getElementById('to_hidden').style.visibility="visible";
			frm.yesno_2.value = "Y";
		}
		else if (frm.yesno2[1].checked)
		{
			if(document.getElementById('mytext').value != ""){
				var x=window.confirm("Will delete the contents of the field?")
				if(x){
					document.getElementById('mytext').value = "";
					document.getElementById('to_hidden').style.visibility="hidden";

				}else{
					frm.yesno2[0].checked = true;
					document.getElementById('to_hidden').style.visibility="visible";
					return;
				}
			}else{document.getElementById('to_hidden').style.visibility="hidden";}

			frm.yesno_2.value = "N";
		}
		setYesNoFields();
	}

	function setYesNo3()
	{
		if (frm.yesno3[0].checked)
		{
			frm.yesno_3.value = "Y";
			alert("Warning! 30-Day Advance notification and approval are required on all purchases to be used for BSC's Federal Employee Program where the purchase amount (and/or total spend of the contract) is expected to exceed $650,000 AND represents 25 percent or more of the total contract cost.  Please contact your Procurement SPOC immediately!");
		}
		else if (frm.yesno3[1].checked)
		{
			frm.yesno_3.value = "N";
		}
		setYesNoFields();
	}

	function setYesNo4()
	{
		if (frm.yesno4[0].checked)
		{
			frm.yesno_4.value = "Y";
		}
		else if (frm.yesno4[1].checked)
		{
			frm.yesno_4.value = "N";
		}
		setYesNoFields();
	}

	function setYesNoFields()
	{
		var YN1 = frm.yesno_1.value;
		var YN2 = frm.yesno_2.value;
		var YN3 = frm.yesno_3.value;
		var YN4 = frm.yesno_4.value;

		frm.RequisitionHeader_udf6Code.value = YN1 + YN2 + YN3 + YN4;
	}

	function setAuditTables()
	{
		frm.auditTables.value = "RequisitionHeader";
	}
	function getFields(el)
	{
		if(el.type != "hidden" && el.name.indexOf("RequisitionHeader_") > -1)
		{
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
    }
	function getFieldsJquery()
	{
		var jQuerySelector = ":input:not([type=hidden])[name^='RequisitionHeader_']";
		var fieldsToAuditJquery = $(jQuerySelector);
		return fieldsToAuditJquery;
    }
    function buildAuditIc()
	{
		return frm.RequisitionHeader_icReqHeader.value;
	}

    function browseFdcsWOElements(fldName) {

    	popupParameters = "workNumber="+ frm.RequisitionHeader_udf7Code.value +";";
       	popupParameters = popupParameters + "segNumber="+ frm.RequisitionHeader_udf8Code.value +";";
       	popupParameters = popupParameters + "opNumber="+ frm.RequisitionHeader_udf9Code.value +";";
       	popupParameters = popupParameters + "custNumber="+ frm.RequisitionHeader_udf11Code.value +";";

    	browseLookup( fldName, 'fdc-wo-elements' ) ;
    }
    function browseCommodityByType(frmField)
	{
		var currentAllowBrowse = frm.allowBrowse.value;
		var commodityBrowse = 'commodity';

		<%	if (s_req_type.equalsIgnoreCase("H")) { %>
			commodityBrowse = 'commodity-it';
		<% } %>

		browseCommodity(frmField, commodityBrowse, '<%=propertiesManager.getProperty("MISC", "COMMODITYTYPE", "")%>');

		frm.allowBrowse.value = currentAllowBrowse;
	}

	function browseStdFieldsUDF(field, udf) {
        <% if (udf_field_depencies.equals("Y")) {%>
    	    var udf1 = frm.RequisitionHeader_udf1Code.value;
    	    var udf3 = frm.RequisitionHeader_udf3Code.value;
    	    var udf7 = frm.RequisitionHeader_udf7Code.value;
            var udf8 = frm.RequisitionHeader_udf8Code.value;
            if ((udf1 == 'RESALECUST' || udf1 == 'RESALEEXP') && udf3 == 'OSL' && udf7 != '' && udf8 != '') {
            	var currentAllowBrowse = frm.allowBrowse.value;
            	frm.allowBrowse.value = "false";
            	browseStd(field, udf);
            	frm.allowBrowse.value = currentAllowBrowse;
            } else {
            	browseStd(field, udf);
            }
        <% } else { %>
        	browseStd(field, udf);
        <% } %>
    }

	function hideRightPanel(flag) {
		var left = $('#RequisitionHeader_requestCat').val();


			if (flag == 'hidden' || left =="" ) {

				hideArea("rq05LabelRow");	hideArea("rq05FieldRow");
				hideArea("SSD_label");		hideArea("SSD_input");
				hideArea("SED_label");		hideArea("SED_input");

			}
			if (left =="SPA" ||
					left =="SA" ||
					left =="SL12" ||
					left =="SL3") {

				displayArea("rq05LabelRow");	displayArea("rq05FieldRow");
				displayArea("SSD_label");		displayArea("SSD_input");
				displayArea("SED_label");		displayArea("SED_input");

			} else {

				hideArea("rq05LabelRow");	hideArea("rq05FieldRow");
				hideArea("SSD_label");		hideArea("SSD_input");
				hideArea("SED_label");		hideArea("SED_input");
			}

	}

	function configureFieldsUDF() {

		<% if (udf_field_depencies.equals("Y")) {%>
			var udf1 = frm.RequisitionHeader_udf1Code.value;
			var udf3 = frm.RequisitionHeader_udf3Code.value;
			//alert('udf1: ' + udf1 + ' - ' + 'udf3: ' + udf3);
			if ((udf1 == 'RESALECUST' || udf1 == 'RESALEEXP') && (udf3 == null || udf3 == '')) {
				var options = frm.RequisitionHeader_udf3Code.options;

				for (var i = 0; i < options.length; i++) {
					if (options[i].value == 'OSL') {
						udf3 = 'OSL';
						frm.RequisitionHeader_udf3Code.selectedIndex = i;
						break;
					}
				}
			}
			if (udf1 == 'CONSUMABLES' || udf1 == 'USEDPARTS' || udf1 == 'INVENTORY')
			{
				frm.RequisitionHeader_udf2Code.value = "";
				frm.RequisitionHeader_udf3Code.value = "";
				frm.RequisitionHeader_udf5Code.value = "";
				frm.RequisitionHeader_udf7Code.value = "";
				frm.RequisitionHeader_udf8Code.value = "";
				frm.RequisitionHeader_udf9Code.value = "";
				frm.RequisitionHeader_udf10Code.value = "";
				frm.RequisitionHeader_udf11Code.value = "";
				frm.RequisitionHeader_udf12Code.value = "";
				//frm.RequisitionHeader_udf13Code.value = "";

				hideArea("rq03LabelRow"); document.getElementById('rq03FieldRow').style.display = 'none';//hideArea("rq03FieldRow");
				hideArea("rq02LabelRow"); hideArea("rq02FieldRow");
				hideArea("rq05LabelRow"); hideArea("rq05FieldRow");
				hideArea("rq07LabelRow"); hideArea("rq07FieldRow");
				hideArea("rq08LabelRow"); hideArea("rq08FieldRow");
				hideArea("rq09LabelRow"); hideArea("rq09FieldRow");
				hideArea("rq10LabelRow"); hideArea("rq10FieldRow");
				hideArea("rq11LabelRow"); hideArea("rq11FieldRow");
				hideArea("rq12LabelRow"); hideArea("rq12FieldRow");

				allowInputEdit(frm.RequisitionHeader_udf1Code, true);
				allowInputEdit(frm.RequisitionHeader_udf3Code, true);
				allowInputEdit(frm.RequisitionHeader_udf13Code, true);
			}
			else if ((udf1 == 'RESALECUST' || udf1 == 'RESALEEXP') && udf3 == 'PARTS')
			{
				//frm.RequisitionHeader_udf13Code.value = "";

				displayArea("rq03LabelRow"); document.getElementById('rq03FieldRow').style.display = '';//displayArea("rq03FieldRow");
				displayArea("rq05LabelRow"); displayArea("rq05FieldRow");

				hideArea("rq02LabelRow"); hideArea("rq02FieldRow");
				hideArea("rq07LabelRow"); hideArea("rq07FieldRow");
				hideArea("rq08LabelRow"); hideArea("rq08FieldRow");
				hideArea("rq09LabelRow"); hideArea("rq09FieldRow");
				hideArea("rq10LabelRow"); hideArea("rq10FieldRow");
				hideArea("rq11LabelRow"); hideArea("rq11FieldRow");
				hideArea("rq12LabelRow"); hideArea("rq12FieldRow");

				allowInputEdit(frm.RequisitionHeader_udf1Code, true);
				allowInputEdit(frm.RequisitionHeader_udf3Code, true);
				allowInputEdit(frm.RequisitionHeader_udf13Code, true);

				frm.RequisitionHeader_udf2Code.value = "";
				frm.RequisitionHeader_udf9Code.value = "";
				frm.RequisitionHeader_udf10Code.value = "";
				frm.RequisitionHeader_udf12Code.value = "";
			}
			else if ( (udf1 == 'RESALECUST' || udf1 == 'RESALEEXP') && udf3 == 'OSL')
			{
				var udf7 = frm.RequisitionHeader_udf7Code.value;
				var udf8 = frm.RequisitionHeader_udf8Code.value;
				if (udf7 == '' && udf8 == '') {

					if (udf1 == 'RESALECUST') {
						frm.RequisitionHeader_udf13Code.value = "718100";
					} else {
						frm.RequisitionHeader_udf13Code.value = "718200";
					}

					displayArea("rq03LabelRow"); document.getElementById('rq03FieldRow').style.display = '';//displayArea("rq03FieldRow");
					hideArea("rq05LabelRow"); hideArea("rq05FieldRow");
					displayArea("rq07LabelRow"); displayArea("rq07FieldRow");
					displayArea("rq08LabelRow"); displayArea("rq08FieldRow");

					hideArea("rq02LabelRow"); hideArea("rq02FieldRow");
					hideArea("rq09LabelRow"); hideArea("rq09FieldRow");
					hideArea("rq10LabelRow"); hideArea("rq10FieldRow");
					hideArea("rq11LabelRow"); hideArea("rq11FieldRow");
					hideArea("rq12LabelRow"); hideArea("rq12FieldRow");

					allowInputEdit(frm.RequisitionHeader_udf1Code, true);
					allowInputEdit(frm.RequisitionHeader_udf3Code, true);
					allowInputEdit(frm.RequisitionHeader_udf8Code, false);
					allowInputEdit(frm.RequisitionHeader_udf13Code, true);

				} else {

					if (udf1 == 'RESALECUST') {
						frm.RequisitionHeader_udf13Code.value = "718100";
					} else {
						frm.RequisitionHeader_udf13Code.value = "718200";
					}

					var msg = 'Work Order #' + udf7
					msg = msg + ' - Segment #' + udf8;
					if (frm.RequisitionHeader_internalComments.value == '') {
						frm.RequisitionHeader_internalComments.value = msg;
					}

					frm.RequisitionHeader_udf5Code.value = '';

					hideArea("rq05LabelRow"); hideArea("rq05FieldRow");
					displayArea("rq02LabelRow"); displayArea("rq02FieldRow");
					displayArea("rq09LabelRow"); displayArea("rq09FieldRow");
					displayArea("rq10LabelRow"); displayArea("rq10FieldRow");
					displayArea("rq11LabelRow"); displayArea("rq11FieldRow");
					displayArea("rq12LabelRow"); displayArea("rq12FieldRow");

					allowInputEdit(frm.RequisitionHeader_udf1Code, false);
					allowInputEdit(frm.RequisitionHeader_udf3Code, false);
					allowInputEdit(frm.RequisitionHeader_udf13Code, false);
				}
			}
			else if ((udf1 == 'RESALECUST' || udf1 == 'RESALEEXP') && udf3 == 'PARTS')
			{
				//frm.RequisitionHeader_udf13Code.value = "";

				displayArea("rq03LabelRow"); document.getElementById('rq03FieldRow').style.display = '';//displayArea("rq03FieldRow");
				displayArea("rq05LabelRow"); displayArea("rq05FieldRow");

				hideArea("rq02LabelRow"); hideArea("rq02FieldRow");
				hideArea("rq07LabelRow"); hideArea("rq07FieldRow");
				hideArea("rq08LabelRow"); hideArea("rq08FieldRow");
				hideArea("rq09LabelRow"); hideArea("rq09FieldRow");
				hideArea("rq10LabelRow"); hideArea("rq10FieldRow");
				hideArea("rq11LabelRow"); hideArea("rq11FieldRow");
				hideArea("rq12LabelRow"); hideArea("rq12FieldRow");

				allowInputEdit(frm.RequisitionHeader_udf1Code, true);
				allowInputEdit(frm.RequisitionHeader_udf3Code, true);
				allowInputEdit(frm.RequisitionHeader_udf13Code, true);

				frm.RequisitionHeader_udf2Code.value = "";
				frm.RequisitionHeader_udf9Code.value = "";
				frm.RequisitionHeader_udf10Code.value = "";
				frm.RequisitionHeader_udf12Code.value = "";
			}
			else
			{
				//frm.RequisitionHeader_udf13Code.value = "";
				allowInputEdit(frm.RequisitionHeader_udf8Code, true);

				displayArea("rq03LabelRow"); document.getElementById('rq03FieldRow').style.display = '';//displayArea("rq03FieldRow");
				hideArea("rq02LabelRow"); hideArea("rq02FieldRow");
				hideArea("rq05LabelRow"); hideArea("rq05FieldRow");
				hideArea("rq07LabelRow"); hideArea("rq07FieldRow");
				hideArea("rq08LabelRow"); hideArea("rq08FieldRow");
				hideArea("rq09LabelRow"); hideArea("rq09FieldRow");
				hideArea("rq10LabelRow"); hideArea("rq10FieldRow");
				hideArea("rq11LabelRow"); hideArea("rq11FieldRow");
				hideArea("rq12LabelRow"); hideArea("rq12FieldRow");
			}
		<% } %>
		function updateDepartmentCode()
		{
			var message = "By changing the <%=DictionaryManager.getLabel(oid, "req-department", "Department")%>" +
				", it will overwrite all existing <%=DictionaryManager.getLabel(oid, "req-department", "Department")%>s " +
				"within the Account Information. Do you want to continue?";
			if (confirm(message))
			{
				frm.updateAllDepartmentCode.value = "Y";
				if (frm.RequisitionHeader_departmentCode)
				{
					previousDepartmentCode = frm.RequisitionHeader_departmentCode.value;
				}
			}
			else
			{
				frm.updateAllDepartmentCode.value = "N";
				if (frm.RequisitionHeader_departmentCode)
				{
					frm.RequisitionHeader_departmentCode.value = previousDepartmentCode;
				}
			}
		}
	function updateDepartmentCode()
	{
		var message = "By changing the <%=DictionaryManager.getLabel(oid, "req-department", "Department")%>" +
			", it will overwrite all existing <%=DictionaryManager.getLabel(oid, "req-department", "Department")%>s " +
			"within the Account Information. Do you want to continue?";
		if (confirm(message))
		{
			frm.updateAllDepartmentCode.value = "Y";
			if (frm.RequisitionHeader_departmentCode)
			{
				previousDepartmentCode = frm.RequisitionHeader_departmentCode.value;
			}
		}
		else
		{
			frm.updateAllDepartmentCode.value = "N";
			if (frm.RequisitionHeader_departmentCode)
			{
				frm.RequisitionHeader_departmentCode.value = previousDepartmentCode;
			}
		}
	}

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

	function openWorkorderInfo()
	{
		popupParameters = "ElementForm_formId=WORKORDER" ;
		popupParameters = popupParameters + ";ElementData_formId=WORKORDER";
		popupParameters = popupParameters + ";ElementData_icHeader=<%=icHeaderHistoryEdit%>";
		popupParameters = popupParameters + ";ElementData_icLine=0";
		popupParameters = popupParameters + ";srrWorkorderNo=" + frm.RequisitionHeader_workOrder.value  ;
		popupParameters = popupParameters + ";readOnly=true" ;
		popupParameters = popupParameters + ";formTitle=Work Order Information" ;
		doSubmitToPopup('base/element_form_popup.jsp', 'SrrWorkorderFormOpen', 'WIDTH=700', 'HEIGHT=400');

	}

	function browseSrrWorkorder()
	{
		popupParameters = "browseName=srr-workorder-browse";
		popupParameters = popupParameters + ";formField=RequisitionHeader_workOrder";
//		popupParameters = popupParameters + ";colname=AutoGen_id_documentType;operator==;filter_txt=REQ;logicalOperator=AND;originalFilter=N;sort=N;"
		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=650', 'HEIGHT=500');
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

	function setRadioValue(fldName, s) {
		var options = frm.elements.item(fldName);
		var value = "";

		if (options != undefined && options.length != undefined) {
			var ind = 0;
	        for (var i = 0; i < options.length; i++) {
	            if (options[i].checked) {
    	            ind = i;
        	        break;
            	}
	        }
			value = options[ind].value;
		}

	frm.RequisitionHeader_udf15Code.value = value;
	}



// End Hide script -->
</SCRIPT>