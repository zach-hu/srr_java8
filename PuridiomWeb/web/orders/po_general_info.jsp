<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.vendorinsurancedefault.VendorInsuranceDefaultManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	String  udf_field_depencies = propertiesManager.getProperty("REQ OPTIONS", "GENERAL INFO FIELD DEPENDENCIES", "N");
	String s_audit_trail = propertiesManager.getProperty("AUDITTRAIL", "POGENERALINFO", "N");
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	boolean fdcsEnabled = propertiesManager.getProperty("FDCS","Enabled","N").equalsIgnoreCase("Y") ;

	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	BigDecimal bd_ic_po_header = poHeader.getIcPoHeader();
	String	s_po_number = poHeader.getPoNumber();
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = poHeader.getReleaseNumber();
	BigDecimal	bd_revision_number = poHeader.getRevisionNumber();
	String	s_po_type = poHeader.getPoType();
	String	s_po_status = poHeader.getStatus();
	String	s_fiscal_year = poHeader.getFiscalYear();
	String	s_buyer_code = poHeader.getBuyerCode();
	String	s_requisitioner_code = poHeader.getRequisitionerCode();
	String	s_authorized_by_code = poHeader.getAuthorizationCode();
	String   s_cxmlAction = poHeader.getEdiOrder();
	BigDecimal	s_po_total = poHeader.getTotal();
	String s_gfp_gfm = poHeader.getGfpGfm();
	String s_request_cat = poHeader.getRequestCat();
	String s_kit = poHeader.getKit();
	String s_sub_type = poHeader.getSubType();
	String s_insurance = poHeader.getInsuranceRqd();
	if(HiltonUtility.isEmpty(s_cxmlAction))	{		s_cxmlAction = "C";		}
	BigDecimal b_estimated_cost = poHeader.getEstimatedCost().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);

	UserProfile buyer = UserManager.getInstance().getUser(oid, s_buyer_code);
	UserProfile requisitioner = UserManager.getInstance().getUser(oid, s_requisitioner_code);
	UserProfile authorizedBy = UserManager.getInstance().getUser(oid, s_authorized_by_code);
	String	s_language = poHeader.getLanguage();
	String	s_receipt_required = poHeader.getReceiptRequired();
	String	s_corrosion_eval = poHeader.getCorrosionEval();

	if (HiltonUtility.isEmpty(s_po_number))	{	s_po_number = "N/A";	}

	boolean bAllowEdit = true;
	if ((role.getAccessRights("PO") < 2) || ((s_po_admin.equalsIgnoreCase("N") || role.getAccessRights("PO") < 99) && s_po_status.compareTo(DocumentStatus.PO_REJECTED) > 0)  || (s_po_status.compareTo(DocumentStatus.CANCELLED) >= 0) || s_po_status.compareTo(DocumentStatus.PO_AMENDED) == 0) {
		bAllowEdit = false;
	}

	String	s_current_process = "HEADER_GENERAL_INFO";
	String	s_current_page = "/orders/po_general_info.jsp";
	String	s_current_method = "PoHeaderUpdate";
	String	s_current_process_method = "";
	String	invalidFields = (String) request.getAttribute("invalidFields");

	while(!HiltonUtility.isEmpty(invalidFields) && invalidFields.startsWith(";")) {
		if (invalidFields.length() > 1) {
			invalidFields = invalidFields.substring(1);
		} else {
			invalidFields = "";
		}
	}
	List lineList = (List) poHeader.getPoLineList();
	int	i_line_count = 0;

	if (lineList != null)
	{
		i_line_count = lineList.size();
	}

	String s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");
	String insCategoryLevelActive = propertiesManager.getProperty("VENDOR", "INSCATLVLACTIVE", "N");

	String vendorInsuranceDefaultMsg = "";
	if (insCategoryLevelActive.equalsIgnoreCase("Y") && s_po_type.equals("CT") && !HiltonUtility.isEmpty(poHeader.getVendorId()))
	{
		VendorInsuranceDefault vendorInsuranceDefault = VendorInsuranceDefaultManager.getInstance().getVendorInsuranceDefault(oid, poHeader.getVendorId());
		if (vendorInsuranceDefault != null && vendorInsuranceDefault.getInsuranceOverride().equalsIgnoreCase("Y"))
		{
			vendorInsuranceDefaultMsg = "Ins. Override ON";
		}
	}

	List transactionTypeList = (List) request.getAttribute("transactionTypeList");
	List resaleTypeList = (List) request.getAttribute("resaleTypeList");
	List chargeCodeList = (List) request.getAttribute("chargeCodeList");

	if(transactionTypeList == null) transactionTypeList = new ArrayList();
	if(resaleTypeList == null) resaleTypeList = new ArrayList();
	if(chargeCodeList == null) chargeCodeList = new ArrayList();
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=poHeader.getPoNumber()%>"/>
<tsa:hidden name="PoSecurity_poNumber" value="<%=poHeader.getPoNumber()%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=poHeader.getReleaseNumber()%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=poHeader.getRevisionNumber()%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_flagChange" value="<%//HiltonUtility.ckNull(poHeader.getFlagChange())%>"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="POH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="StdTable_tableType" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="resetReceipt" value="false"/>
<tsa:hidden name="Schedule_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="Schedule_documentType" value="POH"/>
<tsa:hidden name="VendorInsurance_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="VendorInsurance_contNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=poHeader.getVendorId()%>"/>
<tsa:hidden name="resetUdf2Code" value="false"/>
<tsa:hidden name="poIcReqHeader" value="<%= poHeader.getIcReqHeader().toString() %>"/>
<tsa:hidden name="udf_field_depencies" value="<%=udf_field_depencies%>"/>
<% if (s_po_number != "N/A") { %>
<tsa:hidden name="auditSaveAdd" value="<%=s_audit_trail%>"/>
<% } %>
<table width=<%=formWidth%> cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign="top" width=135px height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100"%>
		<tr>
			<td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="general_information" defaultString="General Information" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign="bottom" width=3px><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign="bottom" align="right" height="30px">
		<%@ include file="/orders/po_display_number.jsp" %>
	</td>
</tr>
</table>

<br>
<%@ include file="/orders/po_info.jsp" %>
<br>
<%@ include file="/system/error_msg.jsp" %>

<table border="0" cellpadding="0" cellspacing="0" width=<%=formEntryWidth%>>
<tr>
	<td valign="top" align=center>
		<table border="0" cellspacing="0" cellpadding="0" height=100%>
		<tr>
			<td width=45% align=center>
				<table border="0" cellpadding="0" cellspacing="0" align=center>
<% 							if(!oid.equalsIgnoreCase("BSC04P")) {%>
				<tr <%=HtmlWriter.isVisible(oid, "po-contract",s_po_type)%>>
					<td width=50% align="right" nowrap><tsa:label labelName="po-contract" defaultString="Contract #" checkRequired="true" />:&nbsp;</td>
					<td><tsa:input type="mintext" name="PoHeader_contractNo" tabIndex="1" maxLength="25" value="<%=poHeader.getContractNo()%>" /></td>
				</tr>
<%} %>
				<tr <%=HtmlWriter.isVisible(oid, "po-orderDate",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-orderDate" defaultString="Order Date" checkRequired="true" />:&nbsp;</td>
					<td nowrap>
						<tsa:input type="mintext" name="PoHeader_poDate" tabIndex="5" maxLength="15" value="<%=HiltonUtility.getFormattedDate(poHeader.getPoDate(), oid, userDateFormat)%>" />
						<a href="javascript: show_calendar('PoHeader_poDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-fiscalYear",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-fiscalYear" defaultString="Fiscal Year" checkRequired="true" />:&nbsp;</td>
					<td>
<%	if (bAllowEdit) {%>
						<select title="<tsa:label labelName="po-fiscalYear" defaultString="Fiscal Year" checkRequired="true" />" name="PoHeader_fiscalYear" tabindex=7>
						<%=HiltonUtility.getFiscalYearOptions(oid, userTimeZone, "PO", s_fiscal_year)%>
						</select>
<%	} else {%>
						<tsa:input type="mintext" name="PoHeader_fiscalYear" tabIndex="7" maxLength="4" value="<%=s_fiscal_year%>"  onfocus="this.blur();" disabled="true" />
<%	} %>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-effectiveDate",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-effectiveDate" defaultString="Effective Date" checkRequired="true" />:&nbsp;</td>
					<td>
						<tsa:input type="mintext" name="PoHeader_effectiveDate" tabIndex="9" maxLength="15" value="<%=HiltonUtility.getFormattedDate(poHeader.getEffectiveDate(), oid, userDateFormat)%>" />
						<a href="javascript: show_calendar('PoHeader_effectiveDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-expirationDate", s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-expirationDate" defaultString="Expiration Date" checkRequired="true" />:&nbsp;</td>
					<td>
						<tsa:input type="mintext" name="PoHeader_expirationDate" tabIndex="10" maxLength="15" value="<%=HiltonUtility.getFormattedDate(poHeader.getExpirationDate(), oid, userDateFormat)%>" />
						<a href="javascript: show_calendar('PoHeader_expirationDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-promisedDate",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-promisedDate" defaultString="Promised Date" checkRequired="true" />:&nbsp;</td>
					<td>
						<tsa:input type="mintext" name="PoHeader_promisedDate" tabIndex="10" maxLength="15" value="<%=HiltonUtility.getFormattedDate(poHeader.getPromisedDate(), oid, userDateFormat)%>" />
						<a href="javascript: show_calendar('PoHeader_promisedDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-requiredDate",s_po_type)%>>
						<td align="right" nowrap><tsa:label labelName="po-requiredDate" defaultString="Required By" checkRequired="true" />:&nbsp;</td>
						<td>
							<tsa:input type="mintext" name="PoHeader_requiredDate" tabIndex="13" maxLength="15" value="<%=HiltonUtility.getFormattedDate(poHeader.getRequiredDate(), oid, userDateFormat)%>" />
							<a href="javascript: show_calendar('PoHeader_requiredDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
						</td>
					</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-buyer",s_po_type)%>>
					<td align="right" nowrap><a href="javascript: browseLookup('PoHeader_buyerCode', 'buyer'); void(0);" title="Click here to choose a <tsa:label labelName="po-buyer" defaultString="Buyer" /> for this order or enter the <tsa:label labelName="po-buyer" defaultString="Buyer" /> in the box on the right."><tsa:label labelName="po-buyer" defaultString="Buyer" checkRequired="true" />:</a>&nbsp;</td>
					<td><tsa:input type="mintext" name="PoHeader_buyerCode" tabIndex="17" maxLength="15" value="<%=s_buyer_code%>" onchange="upperCase(this); getNewInfo('buyer', this);" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-buyerName",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-buyerName" defaultString="Buyer Name" />:&nbsp;</td>
					<td><tsa:input type="text" name="as_buyerName" size="35" maxLength="43" value="<%=buyer.getDisplayName()%>" onfocus="this.blur();" disabled="true" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-confirming",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-confirming" defaultString="Confirming" />:</td>
					<td>
						<input type=checkbox name="c_checkbox" tabindex=19 value="Y" <% if (poHeader.getConfirming().equals("Y")) { %> CHECKED <% } %> onclick="setCheckbox(frm.PoHeader_confirming, 0);">
						<tsa:hidden name="PoHeader_confirming" value="<%=poHeader.getConfirming()%>"/>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-confirmDate",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-confirmDate" defaultString="Confirm Date" checkRequired="true" />:&nbsp;</td>
					<td>
						<tsa:input type="mintext" name="PoHeader_confirmDate" tabIndex="21" maxLength="15" value="<%=HiltonUtility.getFormattedDate(poHeader.getConfirmDate(), oid, userDateFormat)%>" />
						<a href="javascript: show_calendar('PoHeader_confirmDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-confirmName",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-confirmName" defaultString="Confirm Name" checkRequired="true" />:&nbsp;</td>
					<td><tsa:input type="mintext" name="PoHeader_confirmNameCode" tabIndex="23" maxLength="30" value="<%=poHeader.getConfirmNameCode()%>" /></td>
				</tr>
<%	if (!oid.equalsIgnoreCase("MSG07P")) { %>
				<tr <%=HtmlWriter.isVisible(oid, "po-PO01",s_po_type)%>>
					<td align="right" nowrap>
<%	if (DictionaryManager.isLink(oid, "po-PO01")) {	%>


						<% if(oid.equals("TTR09P")){ %>
							<tsa:label labelName="po-PO01" defaultString="UDF1" checkRequired="true" />:&nbsp;
						<%	}else{ %>
							<a href="javascript: browseStd('PoHeader_udf1Code', 'PO01'); void(0);" title="Click here to choose the value for <tsa:label labelName="po-PO01" defaultString="UDF1" /> for this order or enter the value in the box on the right."><tsa:label labelName="po-PO01" defaultString="UDF1" checkRequired="true" />:</a>&nbsp;
						<%	}	%>
<%	} else {	%>
						<tsa:label labelName="po-PO01" defaultString="UDF1" checkRequired="true" />:&nbsp;
<%	} %>
					</td>
					<td>

						<% try { if(oid.equals("TTR09P")){ %>
	          				<select name="PoHeader_udf1Code" tabIndex="25" style="width: 115px" onchange="upperCase(this);configureFieldsUDF();">
								<option value=""></option>
	          				<% for (int il = 0; il < transactionTypeList.size(); il++) {
								StdTable stdTable = (StdTable) transactionTypeList.get(il);
								StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
								<option value="<%=stdTablePK.getTableKey()%>" <%if (poHeader.getUdf1Code().equals(stdTablePK.getTableKey())) {%>selected<%}%>><%=stdTable.getTableKey()%></option>
	          				<%	}	%>
	          				</select>
	          			<%	}else{ %>
		          			<tsa:input type="mintext" name="PoHeader_udf1Code" tabIndex="25" maxLength="15" value="<%=poHeader.getUdf1Code()%>" onchange="upperCase(this);" />
          				<%	} } catch (Exception e) { e.printStackTrace(); } %>
					</td>
				</tr>
<%	} else {	%>
				<tr <%=HtmlWriter.isVisible(oid, "po-PO01",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-PO01" defaultString="UDF1" checkRequired="true" />:</td>
					<td>
						<input type=checkbox name="ckbox" tabindex=19 value="Y" <% if (poHeader.getUdf1Code().equals("Y")) { %> CHECKED <% } %> ONCLICK="setFlagFromCkbox(this,frm.PoHeader_udf1Code);">
						<tsa:hidden name="PoHeader_udf1Code" value="<%=poHeader.getUdf1Code()%>"/>
					</td>
				</tr>
<%	} %>
				<tr <%=HtmlWriter.isVisible(oid, "OrderGeneralInf-OrderUDF3",s_po_type)%>>
					<td id="po03LabelRow" align="right" nowrap>
<%	if (DictionaryManager.isLink(oid, "OrderGeneralInf-OrderUDF3")) { %>
						<% if(oid.equals("TTR09P")){ %>
							<tsa:label labelName="OrderGeneralInf-OrderUDF3" defaultString="UDF3" checkRequired="true" />:&nbsp;
						<%	}else{ %>
							<a href="javascript: browseStd('PoHeader_udf3Code', 'PO03'); void(0);" title="Click here to choose the <tsa:label labelName="OrderGeneralInf-OrderUDF3" defaultString="UDF3" /> for this order or enter the value in the box on the right."><tsa:label labelName="OrderGeneralInf-OrderUDF3" defaultString="UDF3" checkRequired="true" />:</a>&nbsp;
						<%	}	%>
<%	} else {	%>
						<tsa:label labelName="OrderGeneralInf-OrderUDF3" defaultString="UDF3" checkRequired="true" />:&nbsp;
<%	} %>
					</td>
					<td id="po03FieldRow">

						<% if(oid.equals("TTR09P")){ %>
							<select name="PoHeader_udf3Code" tabIndex="25" style="width: 115px" value="<%=poHeader.getUdf3Code()%>" onchange="upperCase(this);configureFieldsUDF();">
								<option value=""></option>
	          				<% for (int il = 0; il < resaleTypeList.size(); il++) {
								StdTable stdTable = (StdTable) resaleTypeList.get(il);
								StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
								<option value="<%=stdTablePK.getTableKey()%>" <%if (poHeader.getUdf3Code().equals(stdTablePK.getTableKey())) {%>selected<%}%>><%=stdTable.getTableKey()%></option>
	          				<%	}	%>
	          				</select>
						<%	}else{ %>
							<tsa:input type="mintext" name="PoHeader_udf3Code" tabIndex="25" maxLength="15" value="<%=poHeader.getUdf3Code()%>" onchange="upperCase(this);" />
						<%	} %>
					</td>
				</tr>
<%	if (!oid.equalsIgnoreCase("MSG07P")) { %>
				<tr <%=HtmlWriter.isVisible(oid, "po-PO05",s_po_type)%>>
					<td id="po05LabelRow" align="right" nowrap>
<%	if (DictionaryManager.isLink(oid, "po-PO05")) { %>
						<a href="javascript: setPopupParameters(); browseStd('PoHeader_udf5Code', 'PO05'); void(0);" title="Click here to choose the <tsa:label labelName="po-PO05" defaultString="UDF5" /> or enter the value in the box on the right."><tsa:label labelName="po-PO05" defaultString="UDF5" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="po-PO05" defaultString="UDF5" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
					<td id="po05FieldRow"><tsa:input type="mintext" name="PoHeader_udf5Code" tabIndex="25" maxLength="15" value="<%=poHeader.getUdf5Code()%>" onchange="upperCase(this);" /></td>
				</tr>
<% } else { %>
			<tr <%=HtmlWriter.isVisible(oid, "po-PO05",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-PO05" defaultString="UDF5" checkRequired="true" />:</td>
					<td>
						<input type=checkbox name="ckbox" tabindex=19 value="Y" <% if (poHeader.getUdf5Code().equals("Y")) { %> CHECKED <% } %> ONCLICK="setFlagFromCkbox(this,frm.PoHeader_udf5Code);">
						<tsa:hidden name="PoHeader_udf5Code" value="<%=poHeader.getUdf5Code()%>"/>
					</td>
				</tr>
<%	} %>
				<tr <%=HtmlWriter.isVisible(oid, "po-PO07",s_po_type)%>>
					<td id="po07LabelRow" align="right" nowrap>
<%	if (DictionaryManager.isLink(oid, "po-PO07")) { %>
						<a href="javascript: browseStdUdf7Code();void(0);" title="Click here to choose the value for <tsa:label labelName="po-PO07" defaultString="UDF7" /> for this order or enter the value in the box on the right."><tsa:label labelName="po-PO07" defaultString="UDF7" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="po-PO07" defaultString="UDF7" checkRequired="true" />:&nbsp;
<%	} %>
					</td>
					<td id="po07FieldRow"><tsa:input type="mintext" name="PoHeader_udf7Code" tabIndex="25" maxLength="15" value="<%=poHeader.getUdf7Code()%>" onchange="upperCase(this);" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-PO09",s_po_type)%>>
					<td id="po09LabelRow" align="right" nowrap>
<%	if (DictionaryManager.isLink(oid, "po-PO09")) { %>
								<% if (fdcsEnabled) { %>
									<a href="javascript: browseFdcsWOElements('FdcWo_PoHeader_udf9Code'); void(0);" title="Click here to choose the value for <tsa:label labelName="po-PO09" defaultString="UDF9" /> for this order or enter the value in the box on the right."><tsa:label labelName="po-PO09" defaultString="UDF9" checkRequired="true" />:</a>&nbsp;
								<% } else { %>
									<a href="javascript: browseStd('PoHeader_udf9Code', 'PO09'); void(0);" title="Click here to choose the value for <tsa:label labelName="po-PO09" defaultString="UDF9" /> for this order or enter the value in the box on the right."><tsa:label labelName="po-PO09" defaultString="UDF9" checkRequired="true" />:</a>&nbsp;
								<% } %>
<%	} else {	%>
						<tsa:label labelName="po-PO09" defaultString="UDF9" checkRequired="true" />:&nbsp;
<%	} %>
					</td>
					<td id="po09FieldRow"><tsa:input type="mintext" name="PoHeader_udf9Code" tabIndex="25" maxLength="15" value="<%=poHeader.getUdf9Code()%>" onchange="upperCase(this);" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-PO11",s_po_type)%>>
					<td id="po11LabelRow" align="right" nowrap>
<%	if (DictionaryManager.isLink(oid, "po-PO11")) {	%>
								<% if (fdcsEnabled) { %>
									<a href="javascript: browseFdcsWOElements('FdcWo_PoHeader_udf11Code'); void(0);" title="Click here to choose a value for <tsa:label labelName="po-PO11" defaultString="UDF11" /> for this order or enter a value in the box on the right."><tsa:label labelName="po-PO11" defaultString="UDF11" checkRequired="true" />:</a>&nbsp;
								<% } else { %>
									<a href="javascript: browseStd('PoHeader_udf11Code', 'PO11'); void(0);" title="Click here to choose a value for <tsa:label labelName="po-PO11" defaultString="UDF11" /> for this order or enter a value in the box on the right."><tsa:label labelName="po-PO11" defaultString="UDF11" checkRequired="true" />:</a>&nbsp;
								<% } %>
<%	} else {	%>
						<tsa:label labelName="po-PO11" defaultString="UDF11" checkRequired="true" />:&nbsp;
<%	} %>
					</td>
					<td id="po11FieldRow"><tsa:input type="mintext" name="PoHeader_udf11Code" tabIndex="45" maxLength="15" value="<%=poHeader.getUdf11Code()%>" onchange="upperCase(this);" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-PO13",s_po_type)%>>
					<td align="right" nowrap>
<%	if (DictionaryManager.isLink(oid, "po-PO13")) {	%>
						<a href="javascript: browseStd('PoHeader_udf13Code', 'PO13'); void(0);"><tsa:label labelName="po-PO13" defaultString="UDF13" checkRequired="true" />:</a>&nbsp;</td>
<%	} else {	%>
						<tsa:label labelName="po-PO13" defaultString="UDF13" checkRequired="true" />:&nbsp;
<%	} %>
					</td>
					<td><tsa:input type="mintext" name="PoHeader_udf13Code" tabIndex="45" maxLength="15" value="<%=poHeader.getUdf13Code()%>" onchange="upperCase(this);" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-PO15",s_po_type)%>>
					<td align="right" nowrap>
<%	if (DictionaryManager.isLink(oid, "po-PO15")) {	%>
						<a href="javascript: browseStd('PoHeader_udf15Code', 'PO15'); void(0);" title="Click here to choose a value for <tsa:label labelName="po-PO15" defaultString="UDF15" /> for this order or enter a value in the box on the right."><tsa:label labelName="po-PO15" defaultString="UDF15" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="po-PO15" defaultString="UDF15" checkRequired="true" />:&nbsp;
<%	} %>
					</td>
					<td><tsa:input type="mintext" name="PoHeader_udf15Code" tabIndex="45" maxLength="15" value="<%=poHeader.getUdf15Code()%>" onchange="upperCase(this);" /></td>
				</tr>
				<%	if (insCategoryLevelActive.equalsIgnoreCase("Y") && s_po_type.equals("CT")) { %>
				<tr <%=HtmlWriter.isVisible(oid, "po-iclLevel", s_po_type)%>>
					<td align="right" nowrap><a href="javascript: browseLookup('PoHeader_iclLevel', 'inscategorylevel'); void(0);" title="Click here to choose the <tsa:label labelName="po-iclLevel" defaultString="Insurance Category Level" /> for this order or enter the <tsa:label labelName="po-iclLevel" defaultString="Insurance Category Level" /> in the box on the right."><tsa:label labelName="po-iclLevel" defaultString="Insurance Category Level" checkRequired="true" />:</a>&nbsp;</td>
					<td><tsa:input type="text" name="PoHeader_iclLevel" value="<%=poHeader.getIclLevel()%>" size="4" maxLength="4" onchange="alphaNumericCheck(this); addUp(this, 0);" />&nbsp;<label class="error"><%=vendorInsuranceDefaultMsg%></label></td>
				</tr>
				<%	} %>

				<tr>
					<tsa:td id="po-GfpGfmLabelRow" field="po-POGFPGFM" align="right" noWrap="nowrap" >
					<tsa:label labelName="po-POGFPGFM" defaultString="GFP/GFM Item" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="po-POGFPGFM" noWrap="nowrap">
					<% String gfpchecked = null;
					if (s_gfp_gfm.equalsIgnoreCase("Y")) {
						gfpchecked = "checked"; }%>
						<tsa:input type="checkbox" title="Check for GFP/GFM item" name="c_checkbox" tabIndex="4" value="Y" checked="<%=gfpchecked%>" onclick="setCheckbox(frm.PoHeader_gfpGfm, 1);" />
						<tsa:hidden name="PoHeader_gfpGfm" value="<%=poHeader.getGfpGfm()%>"/>
					</tsa:td>
				</tr>
				<tr>
					<tsa:td id="po-WorkOrderLabelRow" field="po-POWORKORDER" align="right" noWrap="nowrap" >
						<tsa:label labelName="po-POWORKORDER" defaultString="Work Order" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="po-POWORKORDER" >
					<tsa:input type="mintext" title="" name="PoHeader_workOrder" tabIndex="24" maxLength="15" value="<%=poHeader.getWorkOrder()%>" onchange="upperCase(this);"/>
					</tsa:td>
				</tr>
				<tr>
					<tsa:td id="po-NaicsLabelRow" field="po-PONAICS" align="right" noWrap="nowrap" >
						<tsa:label labelName="po-PONAICS" defaultString="NAICS Code" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="po-PONAICS" >
					<tsa:input type="mintext" title="" name="PoHeader_naics" tabIndex="24" maxLength="15" value="<%=poHeader.getNaics()%>" onchange="upperCase(this);"/>
					</tsa:td>
				</tr>
				<tr>
					<tsa:td id="po-InsuranceLabelRow" field="po-POINSURANCE" align="right" noWrap="nowrap" >
					<tsa:label labelName="po-POINSURANCE" defaultString="Insurance Required" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="po-POINSURANCE" noWrap="nowrap">
						<input type=checkbox title="Check for required insurance" name="c_checkbox" tabindex=4 value="Y" <% if (s_insurance.equals("Y")) { %> CHECKED <% } %> onclick="setCheckbox(frm.PoHeader_insuranceRqd, 3);">
						<tsa:hidden name="PoHeader_insuranceRqd" value="<%=poHeader.getInsuranceRqd()%>"/>
					</tsa:td>
				</tr>
				</table>
			</td>
			<td width=5%>&nbsp;</td>
			<td valign="top" width=45% align=center>
				<table border="0" cellpadding="0" cellspacing="0" width=100% align=center>
				<% if(poHeader.getIcReqHeader().intValue() == 0){ %>
				<tr <%=HtmlWriter.isVisible(oid, "po-requisitionNumber",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-requisitionNumber" defaultString="Requisition Number" />:&nbsp;</td>
					<td><tsa:input type="mintext" name="PoHeader_requisitionNumber" tabIndex="27" maxLength="20" value="<%=poHeader.getRequisitionNumber()%>" /></td>
				</tr>
				<%	}else{ %>
				<tr <%=HtmlWriter.isVisible(oid, "po-requisitionNumber",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-requisitionNumber" defaultString="Requisition Number" checkRequired="true" />:&nbsp;</td>
					<td><input type="text" name="PoHeader_requisitionNumber" tabindex=27 size=15 maxlength=20 value="<%=poHeader.getRequisitionNumber()%>" <%=HtmlWriter.isReadOnly(oid, "po-requisitionNumber")%>></td>
				</tr>
				<%	} %>
				<tsa:tr field="po-requisitioner" >
					<tsa:td field="po-requisitioner" width="" align="right" noWrap="nowrap" >
					<a href="javascript: browseLookup('PoHeader_requisitionerCode', 'user'); void(0);" title="Click here to change the <tsa:label labelName='po-requisitioner' defaultString='Requisitioner' noinstance='yes'/>"> <tsa:label labelName="po-requisitioner" defaultString="Requisitioner" noinstance="yes" checkRequired="true" />:</a>&nbsp;
					</tsa:td>
					<tsa:td field="po-requisitioner" >
					<tsa:input type="mintext" title="Enter the User ID of the Requisitioner" name="PoHeader_requisitionerCode" tabIndex="7" maxLength="15" value="<%=s_requisitioner_code%>" onchange="upperCase(this); getNewInfo('requisitioner', this);"/>
					</tsa:td>
				</tsa:tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-requisitionerName",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-requisitionerName" defaultString="Requisitioner Name" />:&nbsp;</td>
					<td nowrap><tsa:input type="text" name="as_requisitionerName" size="35" maxLength="43" value="<%=requisitioner.getDisplayName()%>" onfocus="this.blur();" disabled="true" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-department",s_po_type)%>>
				<%	String s_dept_instr="";
					if(oid.equalsIgnoreCase("BSC04P"))
					{
						s_dept_instr= "Click here to choose the " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-department", "Department",s_po_type) + " Name for this order or enter the " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-department", "Department",s_po_type) + " Name in the box on the right.";
					}
					else
					{
						s_dept_instr= "Click here to choose the " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-department", "Department",s_po_type) + " Code for this order or enter the " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-department", "Department",s_po_type) + " Code in the box on the right.";
					}
				%>
					<td align="right" nowrap>
						<% if (DictionaryManager.getLabelsInstance(oid, language).isLink(oid, "po-department")) { %>
							<a href="javascript: browseLookup('PoHeader_departmentCode', 'department'); void(0);"
						   	   title="<%=DictionaryManager.getLabelsInstance(oid, language).
						   	   getLabel(oid,  "po-department-instr", s_dept_instr, s_po_type)%>">
						   		<tsa:label labelName="po-department" defaultString="Department" checkRequired="true" />:
							</a>
						<% } else { %>
								<tsa:label labelName="po-department" defaultString="Department" checkRequired="true" />:
						<% } %>
							&nbsp;
					</td>
<%                  if (oid.equalsIgnoreCase("MSG07P")){ %>
					<td><tsa:input type="mintext" name="PoHeader_departmentCode" tabIndex="31" maxLength="15" value="<%=poHeader.getDepartmentCode()%>" readonly="true" /></td>
<% 					}else { %>
					<td><input field="po-department" type="text" name="PoHeader_departmentCode" tabIndex="31" size="15" maxLength="15" value="${poHeader.departmentCode}"  onchange="upperCase(this);" <%=HtmlWriter.isReadOnly(oid, "po-department")%>/></td>
<% 					} %>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-authorizedBy",s_po_type)%>>
					<td align="right" nowrap><a href="javascript: browseLookup('PoHeader_authorizationCode', 'authorization'); void(0);" title="Click here to choose the <tsa:label labelName="po-authorizedBy" defaultString="Authorized By" /> for this order or enter the <tsa:label labelName="po-authorizedBy" defaultString="Authorized By" /> in the box on the right."><tsa:label labelName="po-authorizedBy" defaultString="Authorized By" checkRequired="true" />:</a>&nbsp;</td>
					<td><tsa:input type="mintext" name="PoHeader_authorizationCode" tabIndex="33" maxLength="15" value="<%=s_authorized_by_code%>"  onchange="upperCase(this); getNewInfo('authby', this);" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-authorizedByName",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-authorizedByName" defaultString="Authorized By Name" />:&nbsp;</td>
					<td><tsa:input type="text" name="as_authbyName" size="35" maxLength="43" value="<%=authorizedBy.getDisplayName()%>" onfocus="this.blur();" disabled="true" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-solicitationNumber",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-solicitationNumber" defaultString="Solicitation Number" checkRequired="true" />:&nbsp;</td>
<%                  if (oid.equalsIgnoreCase("MSG07P")){ %>
					<td><input type="text" name="PoHeader_rfqNumber" tabindex=35 size=15 maxlength=15 value="<%=poHeader.getRfqNumber()%>" title="<tsa:label labelName="po-rfqNumber-instr" defaultString="Solicitation Number" />" READONLY></td>
<% 					}else { %>
					<td><input type="text" name="PoHeader_rfqNumber" tabindex=35 size=15 maxlength=15 value="<%=poHeader.getRfqNumber()%>" title="<tsa:label labelName="po-rfqNumber-instr" defaultString="Solicitation Number" />"></td>
<% 					} %>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-currency",s_po_type)%>>
					<td align="right" nowrap><a href="javascript: browseLookup('PoHeader_currencyCode', 'curr_code'); void(0);" title="Click here to choose the <tsa:label labelName="po-currency" defaultString="Currency" /> for this order or enter the <tsa:label labelName="po-currency" defaultString="Currency" /> in the box on the right."><tsa:label labelName="po-currency" defaultString="Currency" checkRequired="true" /></a>:&nbsp;</td>
					<td><tsa:input type="mintext" name="PoHeader_currencyCode" tabIndex="37" maxLength="15" value="<%=poHeader.getCurrencyCode()%>" onchange="upperCase(this); currencyChangeWarning(this.value);" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-language",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-language" defaultString="Language" checkRequired="true" />:&nbsp;</td>
					<td>
						<select name="PoHeader_language" tabindex=39>
						<option value="(Default)" <% if ( s_language.equals("(Default)") ) { %> SELECTED <% } %> >(Default)</option>
						<option value="" <% if ( s_language.equals("") ) { %> SELECTED <%}%> ></option>
						</select>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-inventoryLocation",s_po_type)%>>
					<td align="right" nowrap><a href="javascript: browseLookup('PoHeader_itemLocation', 'item_location'); void(0);" title="Click here to choose the <tsa:label labelName="po-inventoryLocation" defaultString="Inventory Location" /> for this order or enter the <tsa:label labelName="po-inventoryLocation" defaultString="Inventory Location" /> in the box on the right."><tsa:label labelName="po-inventoryLocation" defaultString="Inventory Location" checkRequired="true" />:</a>&nbsp;</td>
					<td><tsa:input type="mintext" name="PoHeader_itemLocation" tabIndex="41" maxLength="15" value="<%=poHeader.getItemLocation()%>" /></td>
				</tr>
				<tr>
					<tsa:td id="poPriority" field="po_priority" align="right" noWrap="nowrap">
					<a href="javascript: browseLookup('PoHeader_priorityCode', 'procurementlevel'); void(0);" title="Click here to choose the <tsa:label labelName='po-priority' defaultString='Priority' noinstance='true'/> code."><tsa:label labelName="po-priority" defaultString="Priority" noinstance="true" checkRequired="true"/>:</a>&nbsp;
					</tsa:td>
					<tsa:td field="po-priority">
					<tsa:input type="mintext" name="PoHeader_priorityCode" maxLength="15" value="<%=poHeader.getPriorityCode()%>" onchange="upperCase(this);"/>
					</tsa:td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-receiptOptions",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-receiptOptions" defaultString="Receipt Required" checkRequired="true" />:&nbsp;</td>
					<td>
						<select tabindex=43 name="PoHeader_receiptRequired" size=1 onchange="setReceiptOption();" <% if (!bAllowEdit) { %>READONLY DISABLED<% } %>>
<%	if (propertiesManager.getProperty("REC SELECTIONS", "X", "Y").equals("Y")) {%>
							<option <% if (s_receipt_required.equals("X") || HiltonUtility.isEmpty(s_receipt_required)) {%> SELECTED <%}%> value=""> - Select an Option - </option>
<%	}
		if (propertiesManager.getProperty("REC SELECTIONS", "R", "Y").equals("Y")) {%>
							<option <% if (s_receipt_required.equals("R")) {%> SELECTED <%}%> value="R"><tsa:label labelName="receiptrequired" defaultString="Receipt Required" checkRequired="false" /></option>
<%	}
		if (propertiesManager.getProperty("REC SELECTIONS", "P", "Y").equals("Y")) {%>
							<option <% if (s_receipt_required.equals("P")) {%> SELECTED <%}%> value="P"><tsa:label labelName="previouslyreceived" defaultString="Previously Received" checkRequired="false" /></option>
<%	}
		if (propertiesManager.getProperty("REC SELECTIONS", "N", "Y").equals("Y")) {%>
							<option <% if (s_receipt_required.equals("N")) {%> SELECTED <%}%> value="N"><tsa:label labelName="noreceiptrequired" defaultString="No Receipt Required" checkRequired="false" /></option>
<%	}
		if (propertiesManager.getProperty("REC SELECTIONS", "E", "Y").equals("Y")) {%>
							<option <% if (s_receipt_required.equals("E")) {%> SELECTED <%}%> value="E"><tsa:label labelName="enduserreceipt" defaultString="End User Receipt" checkRequired="false" /></option>
<%	}%>
						</select>
					</td>
				</tr>

				<tr id="po02LabelRow" <%=HtmlWriter.isVisible(oid, "po-PO02",s_po_type)%>>
					<td align="right" nowrap>
<%	if (DictionaryManager.isLink(oid, "po-PO02")) {
			if (oid.equals("MSG07P") || oid.equals("BLY07P")) {%>
						<a href="javascript: browseStd('PoHeader_udf2Code', 'BIDW'); void(0);" title="Click here to choose a value for <tsa:label labelName="po-PO02" defaultString="UDF2" /> for this order or enter a value in the box on the right."><tsa:label labelName="po-PO02" defaultString="UDF2" checkRequired="true" />:</a>&nbsp;
<%		} else {%>
						<a href="javascript: browseStd('PoHeader_udf2Code', 'PO02'); void(0);" title="Click here to choose a value for <tsa:label labelName="po-PO02" defaultString="UDF2" /> for this order or enter a value in the box on the right."><tsa:label labelName="po-PO02" defaultString="UDF2" checkRequired="true" />:</a>&nbsp;
<%		}
		} else {	%>
						<tsa:label labelName="po-PO02" defaultString="UDF2" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
<%	if (oid.equalsIgnoreCase("vse06p")) { %>
					<td <%=HtmlWriter.isVisible(oid, "po-PO02")%> >
						<select tabindex=23 name="PoHeader_udf2Code" size=1 onchange="setUdf2Code();">
<%
		if (HiltonUtility.isEmpty(poHeader.getUdf2Code())) {
			poHeader.setUdf2Code(DictionaryManager.getInstance("inspection-level", oid).getLabelProperty("DEFAULT", ""));
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
						<option value="<%=inspectionLevelCode%>" <%if (poHeader.getUdf2Code().equalsIgnoreCase(inspectionLevelCode)) {%>SELECTED<%}%>><%=inspectionLevelName%></value>
<%	}	%>
						</select>
					</td>
<%	} else { %>
					<td id="po02FieldRow" ><tsa:input type="mintext" name="PoHeader_udf2Code" tabIndex="45" maxLength="15" value="<%=poHeader.getUdf2Code()%>" onchange="upperCase(this);" /></td>
<%	}	%>
				</tr>
<%	if (!oid.equalsIgnoreCase("MSG07P")) { %>
				<tr <%=HtmlWriter.isVisible(oid, "po-PO04",s_po_type)%>>
					<td align="right" nowrap>
<%	if (DictionaryManager.isLink(oid, "po-PO04")) {	%>
						<a href="javascript: browseStd('PoHeader_udf4Code', 'PO04'); void(0);" title="Click here to choose a value for <tsa:label labelName="po-PO04" defaultString="UDF4" /> for this order or enter a value in the box on the right."><tsa:label labelName="po-PO04" defaultString="UDF4" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="po-PO04" defaultString="UDF4" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
					<td><tsa:input type="mintext" name="PoHeader_udf4Code" tabIndex="45" maxLength="15" value="<%=poHeader.getUdf4Code()%>" onchange="upperCase(this);" /></td>
				</tr>
<% } else { %>
			<tr <%=HtmlWriter.isVisible(oid, "po-PO04",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-PO04" defaultString="UDF4" checkRequired="true" />:</td>
					<td>
						<input type=checkbox name="ckbox" tabindex=19 value="Y" <% if (poHeader.getUdf4Code().equals("Y")) { %> CHECKED <% } %> ONCLICK="setFlagFromCkbox(this,frm.PoHeader_udf4Code);">
						<tsa:hidden name="PoHeader_udf4Code" value="<%=poHeader.getUdf4Code()%>"/>
					</td>
				</tr>
<% } %>
<%	if (!oid.equalsIgnoreCase("MSG07P")) { %>
				<tr <%=HtmlWriter.isVisible(oid, "po-PO06",s_po_type)%>>
					<td align="right" nowrap>
<%	if (DictionaryManager.isLink(oid, "po-PO06")) {	%>
						<a href="javascript: browseStd('PoHeader_udf6Code', 'PO06'); void(0);" title="Click here to choose a value for <tsa:label labelName="po-PO06" defaultString="UDF6" /> for this order or enter a value in the box on the right."><tsa:label labelName="po-PO06" defaultString="UDF6" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="po-PO06" defaultString="UDF6" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
					<td><tsa:input type="mintext" name="PoHeader_udf6Code" tabIndex="45" maxLength="15" value="<%=poHeader.getUdf6Code()%>" onchange="upperCase(this);" /></td>
				</tr>
<% } else { %>
			<tr <%=HtmlWriter.isVisible(oid, "po-PO06",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-PO06" defaultString="UDF6" checkRequired="true" />:</td>
					<td>
						<input type=checkbox name="ckbox" tabindex=19 value="Y" <% if (poHeader.getUdf6Code().equals("Y")) { %> CHECKED <% } %> ONCLICK="setFlagFromCkbox(this,frm.PoHeader_udf6Code);">
						<tsa:hidden name="PoHeader_udf6Code" value="<%=poHeader.getUdf6Code()%>"/>
					</td>
				</tr>
<% } %>
<%	if (!oid.equalsIgnoreCase("MSG07P")) { %>
				<tr <%=HtmlWriter.isVisible(oid, "po-PO08",s_po_type)%>>
					<td id="po08LabelRow" align="right" nowrap>
						<% String browseUdf8=(oid.equalsIgnoreCase("bly07p"))?"RQ01":"PO08"; %>
<%	if (DictionaryManager.isLink(oid, "po-PO08")) {	%>
								<% if (fdcsEnabled) { %>
									<a href="javascript: browseFdcsWOElements('FdcWo_PoHeader_udf8Code'); void(0);"title="Click here to choose a value for <tsa:label labelName="po-PO08" defaultString="UDF8" /> for this order or enter a value in the box on the right."><tsa:label labelName="po-PO08" defaultString="UDF8" checkRequired="true" />:</a>&nbsp;
								<% } else { %>
									<a href="javascript: browseStd('PoHeader_udf8Code', '<%=browseUdf8 %>'); void(0);"title="Click here to choose a value for <tsa:label labelName="po-PO08" defaultString="UDF8" /> for this order or enter a value in the box on the right."><tsa:label labelName="po-PO08" defaultString="UDF8" checkRequired="true" />:</a>&nbsp;
								<% } %>
<%	} else {	%>
						<tsa:label labelName="po-PO08" defaultString="UDF8" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
					<td id="po08FieldRow"><tsa:input type="mintext" name="PoHeader_udf8Code" tabIndex="45" maxLength="15" value="<%=poHeader.getUdf8Code()%>" onchange="upperCase(this);configureFieldsUDF();" /></td>
				</tr>
<% } else { %>
			<tr <%=HtmlWriter.isVisible(oid, "po-PO08",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-PO08" defaultString="UDF8" checkRequired="true" />:</td>
					<td>
						<input type=checkbox name="ckbox" tabindex=19 value="Y" <% if (poHeader.getUdf8Code().equals("Y")) { %> CHECKED <% } %> ONCLICK="setFlagFromCkbox(this,frm.PoHeader_udf8Code);">
						<tsa:hidden name="PoHeader_udf8Code" value="<%=poHeader.getUdf8Code()%>"/>
					</td>
				</tr>
<% } %>
				<tr <%=HtmlWriter.isVisible(oid, "po-PO12",s_po_type)%>>
					<td id="po12LabelRow" align="right" nowrap>
<%	if (DictionaryManager.isLink(oid, "po-PO12")) {	%>
						<a href="javascript: browseStd('PoHeader_udf12Code', 'PO12'); void(0);" title="Click here to choose a value for <tsa:label labelName="po-PO12" defaultString="UDF12" /> for this order or enter a value in the box on the right."><tsa:label labelName="po-PO12" defaultString="UDF12" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="po-PO12" defaultString="UDF12" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
					<td id="po12FieldRow"><tsa:input type="mintext" name="PoHeader_udf12Code" tabIndex="45" maxLength="15" value="<%=poHeader.getUdf12Code()%>" onchange="upperCase(this);" /></td>
				</tr>

				<tr <%=HtmlWriter.isVisible(oid, "po-PO14",s_po_type)%>>
					<td align="right" nowrap>
<%	if (DictionaryManager.isLink(oid, "po-PO14")) {	%>
						<a href="javascript: browseStd('PoHeader_udf14Code', 'PO14'); void(0);" title="Click here to choose a value for <tsa:label labelName="po-PO14" defaultString="UDF14" /> for this order or enter a value in the box on the right."><tsa:label labelName="po-PO14" defaultString="UDF14" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="po-PO14" defaultString="UDF14" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
					<td><tsa:input type="mintext" name="PoHeader_udf14Code" tabIndex="45" maxLength="15" value="<%=poHeader.getUdf14Code()%>" onchange="upperCase(this);" /></td>
				</tr>

				<%
				String savingsString = "";
				if( oid.equalsIgnoreCase("BSC04P") && poHeader.getSavings().compareTo(new BigDecimal(0))== 0 )
				{
					savingsString = "";
				}
				else
				{
					savingsString = HiltonUtility.getFormattedDollar(poHeader.getSavings(), oid).toString();
				}
				%>
				<tr <%=HtmlWriter.isVisible(oid, "po-savings",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-savings" defaultString="Savings $" checkRequired="false" />:</a>&nbsp;</td>
					<td><tsa:input type="mintext" name="PoHeader_savings" tabIndex="45" maxLength="15" value="<%=savingsString %>" onchange="formatAmt(this);" /></td>
				</tr>
				 <tr <%=HtmlWriter.isVisible(oid, "po-savingsReason",s_po_type)%>>
					<td align="right" nowrap><a href="javascript: setPopupParameters();browseStd('PoHeader_savingsReason', 'SAVE'); void(0);" title="Click here to choose a value for <tsa:label labelName="po-savingsReason" defaultString="Savings Reason" /> for this order or enter a value in the box on the right."><tsa:label labelName="po-savingsReason" defaultString="Savings Reason" checkRequired="false" />:</a>&nbsp;</td>
					<td><tsa:input type="mintext" name="PoHeader_savingsReason" tabIndex="45" maxLength="15" value="<%=poHeader.getSavingsReason()%>" onchange="upperCase(this);" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-bidWaiver",s_po_type)%>>
					<td align="right" nowrap>
						<a href="javascript: browseStd('PoHeader_bidWaiver', 'BIDW'); void(0);" title="Click here to choose a value for <tsa:label labelName="po-bidWaiver" defaultString="Bid Waiver" /> for this order or enter a value in the box on the right."><tsa:label labelName="po-bidWaiver" defaultString="Bid Waiver" checkRequired="true" />:</a>&nbsp;
					</td>
					<td><tsa:input type="mintext" name="PoHeader_bidWaiver" tabIndex="46" value="<%=poHeader.getBidWaiver()%>" /></td>
				</tr>
				<% if (s_po_type.equals("CT")) { %>
				<tr <%=HtmlWriter.isVisible(oid, "po-otherCharges",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-otherCharges" defaultString="Taxable Amount" checkRequired="false" />:</a>&nbsp;</td>
					<td><tsa:input type="mintext" name="PoHeader_otherCharges" tabIndex="45" maxLength="15" value="<%=HiltonUtility.getFormattedDollar(poHeader.getOtherCharges(), oid)%>" onchange="formatAmt(this);" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-total",s_po_type)%>>
					<td align="right" nowrap><tsa:label labelName="po-total" defaultString="Value In Dollars" checkRequired="false" />:</a>&nbsp;</td>
					<td><tsa:input type="mintext" name="PoHeader_total" tabIndex="45" maxLength="15" value="<%=HiltonUtility.getFormattedDollar(poHeader.getTotal(), oid)%>" onchange="formatAmt(this);" /></td>
				</tr>
				<% } %>
				<tr>
				<tsa:td id="po-KitLabelRow" field="po-POKIT" align="right" noWrap="nowrap" >
				<tsa:label labelName="po-POKIT" defaultString="Kit" noinstance="yes"/>:&nbsp;
				</tsa:td>
				<tsa:td field="po-POKIT" noWrap="nowrap">
				<% String kitchecked = null;
				if (s_kit.equalsIgnoreCase("Y")) {
					kitchecked = "checked"; }%>
					<tsa:input type="checkbox" title="Check for kit" name="c_checkbox" tabIndex="4" value="Y" checked="<%=kitchecked%>"  onclick="setCheckbox(frm.PoHeader_kit, 2);" />
					<tsa:hidden name="PoHeader_kit" value="<%=poHeader.getKit()%>"/>
				</tsa:td>
				</tr>
				<tr>
				<tsa:td id="po-CategoryLabelRow" field="po-POCATEGORY" align="right" noWrap="nowrap" >
					<tsa:label labelName="po-POCATEGORY" defaultString="Request Category" checkRequired="true" noinstance="yes"/>:&nbsp;
				</tsa:td>
				<tsa:td field="po-POCATEGORY" >
					<tsa:input type="dropdown" title="" name="PoHeader_requestCat" value="<%=s_request_cat%>" labelName="po-POCATEGORY" onchange="upperCase(this);"/>
				</tsa:td>
				</tr>
				<tr>
				<tsa:td id="po-SubTypeLabelRow" field="po-POSUBTYPE" align="right" noWrap="nowrap" >
					<tsa:label labelName="po-POSUBTYPE" defaultString="Order Sub Type" checkRequired="true" noinstance="yes"/>:&nbsp;
				</tsa:td>
				<tsa:td field="po-POSUBTYPE" >
					<tsa:input type="mintext" tabIndex="23" name="PoHeader_subType" id="PoHeader_subType" size="1" value="<%=poHeader.getSubType()%>" labelName="po-POSUBTYPE"/>
				</tsa:td>
				</tr>
				<tr>
					<tsa:td id="corrosionEvalLabelRow" field="po-corrosionEval" align="right" noWrap="nowrap">
			        <tsa:label labelName="po-corrosionEval" defaultString="Corrosion Evaluation" checkRequired="true" noinstance="yes"/>:&nbsp;
			        </tsa:td>
			        <tsa:td id="corrosionEvalFieldRow" field="po-corrosionEval">
			        <tsa:input type="yesnoradio" title="" name="PoHeader_corrosionEval" value="<%=s_corrosion_eval%>"/>
			        </tsa:td>
				</tr>
				</table>
			</td>
			<td width=5%>&nbsp;</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "po-purpose",s_po_type)%>>
			<td colspan=4>
				<table border="0" cellpadding="2" cellspacing="0" width=100%>
				<tr>
					<td width=22.5% nowrap align="right" valign=top><tsa:label labelName="po-purpose" defaultString="Purpose" checkRequired="true" />:&nbsp;</td>
					<td><textarea title="Enter the purpose for this order" name="PoHeader_internalComments" tabindex=26  rows="6" cols="64" wrap="nonvirtual" maxlength=255 onKeyDown="textCounter(this, 255);" onKeyUp="textCounter(this,255);" onchange="textCounter(this,255); upperCase(this);">${esapi:encodeForHTML(poHeader.internalComments)}</textarea></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
			<td rowspan=2 align="right" valign="top"><%@ include file="/orders/po_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>
<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border="0" cellpadding="0" cellspacing="0" width="880px">
<tr>
	<td width=50% align=center><a href="javascript: poSaveClassic(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border="0" alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border="0" alt="Return"></a></td>
</tr>
</table>
<%	} %>

<br>
<% if (s_po_number != "N/A" && s_audit_trail.equalsIgnoreCase("Y")) { %>
<table border=0 cellpadding=2 cellspacing=0 width=880px>
	<tr>
		<td align=center>
			<a href="javascript: viewAuditTrail('<%=bd_ic_po_header%>');" title="Audit Trail"><img src="<%=contextPath%>/images/asset3.gif" border="0" alt="Audit Trail"></a>
			<a href="javascript: viewAuditTrail('<%=bd_ic_po_header%>');" title="Audit Trail"><tsa:label labelName="audit-trail" defaultString="Audit Trail" /></a>
		</td>
	</tr>
</table>
<% } %>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var ponumber = "<%=s_po_number%>";
	var fiscalyear = "<%=HiltonUtility.ckNull(poHeader.getFiscalYear())%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var allowEdit;
	var currentRow = 0;

	function thisLoad()
	{
		f_StartIt();
	<%	if ( !bAllowEdit) { %>
			checkInputSettings();
			allowEdit = false;
	<%	} else { %>
		setInvalidFields("<%=invalidFields%>");
		configureFieldsUDF();
<%		}

	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer() && !user.getUserId().equals(poHeader.getBuyerCode())) { %>
			checkInputSettings();
			allowEdit = false;
	<%	} %>

	<% if (oid.equalsIgnoreCase("msg07p"))  {%>
		if (frm.PoHeader_requisitionerCode)
			allowInputEdit(frm.PoHeader_requisitionerCode, false);
	<%	}%>

	<% if (oid.equalsIgnoreCase("msg07p") && (s_po_status.compareTo(DocumentStatus.PO_AWARDED) >= 0)) {%>
		if (frm.PoHeader_requisitionerCode)
			allowInputEdit(frm.PoHeader_receiptRequired, false);
	<%	}%>

	}

	function	 setReceiptOption()
	{
		if (verifyAction("Change all line items to match order header receipt option?"))
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
		if (handlers.indexOf("PoHeaderUpdate") >= 0) {
			var alertMessage = "";

			if (frm.PoHeader_poDate && !chkdate(frm.PoHeader_poDate)) {
				alertMessage += "\n<tsa:label labelName='orderDate' defaultString='Order Date' />" + " is not a valid date.";
			}
			if (frm.PoHeader_requiredDate && !chkdate(frm.PoHeader_requiredDate)) {
				alertMessage += "\n<tsa:label labelName='po-requiredDate' defaultString='Required By' />" + " is not a valid date.";
			}
			if (frm.PoHeader_effectiveDate && !chkdate(frm.PoHeader_effectiveDate)) {
				alertMessage += "\n<tsa:label labelName='effectiveDate' defaultString='Effective Date' />" + " is not a valid date.";
			}
			if (frm.PoHeader_promisedDate && !chkdate(frm.PoHeader_promisedDate)) {
				alertMessage += "\n<tsa:label labelName='promisedDate' defaultString='Promised Date' />" + " is not a valid date.";
			}
			if (frm.PoHeader_confirmDate && !chkdate(frm.PoHeader_confirmDate)) {
				alertMessage += "\n<tsa:label labelName='confirmDate' defaultString='Confirm Date' />" + " is not a valid date.";
			}

			if (alertMessage.length > 0) {
				alert("Please fix the following problems:\n" + alertMessage);
				return false;
			}
		}
		return true;
	}

	function setCheckbox(fld)
	{
		fld.value = 'N';
		if ( frm.c_checkbox.checked )
		{
			fld.value = 'Y';
		}
		return true;
	}

	function checkBlanketAction()
		{
			frm.PoHeader_ediOrder.value = frm.PoHeader_ediOrder_option.value;
		}

	function formatAmt(fld) {
		fld.value = nformat(nfilter(fld), 2);
	}

	function setUdf2Code()
	{
		if (verifyAction("Change all line items to match po header inspection level?"))
		{
			frm.resetUdf2Code.value = true;
		}
		else
		{
			frm.resetUdf2Code.value = false;
		}
	}

	function setPopupParameters()
	{
		popupParameters += 'PoHeader_poType=' + frm.PoHeader_poType.value + ';';
	}

	function setAuditTables()
	{
		frm.auditTables.value = "PoHeader";
	}
	function getFields(el)
	{
		if( (el.type != "hidden" && el.name.indexOf("PoHeader_promisedDate") > -1) )
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
		var jQuerySelector = ":input:not([type=hidden])[name^='PoHeader_']";
		var fieldsToAuditJquery = $(jQuerySelector);
		return fieldsToAuditJquery;
    }
    function buildAuditIc()
	{
		return frm.PoHeader_icPoHeader.value;
	}
	function viewAuditTrail(icPoHeader)
	{
		popupParameters = popupParameters + "browseName=audittrail";
		popupParameters = popupParameters + ";colname=AuditRecord_entity1;operator==;filter_txt=" + icPoHeader + ";logicalOperator=AND;originalFilter=N;sort=N;"
		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}
	function browseStdUdf7Code()
	{
		var currentAllowBrowse = frm.allowBrowse.value;
	<% if (oid.equalsIgnoreCase("bly07p") && !HiltonUtility.isEmpty(poHeader.getRequisitionNumber())) { %>
		frm.allowBrowse.value = "false";
	<% } %>
		browseStd('PoHeader_udf7Code', 'RQ07');
		frm.allowBrowse.value = currentAllowBrowse;
	}

	function poSaveClassic()
	{
		var autoPoNumberClassic = '<%=propertiesManager.getProperty("AUTONUMBER", "AUTOPO", "N")%>';
		var showAutoPoNumberClassic = '<%=propertiesManager.getProperty("AUTONUMBER", "SHOWAUTOPO", "Y")%>';
		if (isNA(ponumber))
		{
			if (autoPoNumberClassic && !showAutoPoNumberClassic) {
				frm.PoHeader_poNumber.value = '';
				doSubmit('/orders/po_summary.jsp', "PoSave;PoHeaderUpdate;PoRetrieve");
			} else {
				popupParameters = "formtype=PO;formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=/orders/po_summary.jsp;currentprocessmethod=PoHeaderUpdate;PoRetrieve";
				//popupParameters = "formtype=PO;formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=save;";
				doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
			}
		}
		else
		{
			doSubmit('/orders/po_summary.jsp', "PoHeaderUpdate;PoRetrieve");
		}
	}

	function poSave()
	{
		if (frm.organizationId.value == "BLY07P" && frm.PoHeader_flagChange.value == "N")
		{
			var poDate = ('<%=HiltonUtility.getFormattedDate(poHeader.getPoDate(), oid)%>' != frm.PoHeader_poDate.value)? true:false;
			var fiscalYear = ('<%=s_fiscal_year%>' != frm.PoHeader_fiscalYear.value)? true:false;
			var dateRequired = ('<%=HiltonUtility.getFormattedDate(poHeader.getRequiredDate(), oid)%>' != frm.PoHeader_requiredDate.value)? true:false;
			var datePromised = ('<%=HiltonUtility.getFormattedDate(poHeader.getPromisedDate(), oid)%>' != frm.PoHeader_promisedDate.value)? true:false;
			var dateConfirm = ('<%=HiltonUtility.getFormattedDate(poHeader.getConfirmDate(), oid)%>' != frm.PoHeader_confirmDate.value)? true:false;

			if (poDate||fiscalYear||dateConfirm||dateRequired||datePromised)
			{
				frm.PoHeader_flagChange.value = 'Y';
			}
			else
			{
				frm.PoHeader_flagChange.value = 'N';
			}
		}
	    if (isNA(ponumber))
		{
			if (autoPoNumber && !showAutoPoNumber) {
				frm.PoHeader_poNumber.value = '';
				doSubmit(currentpage, "PoSave;" + currentprocessmethod);
			} else {
				popupParameters = "formtype=PO;formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentprocessmethod;
				//popupParameters = "formtype=PO;formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=save;";
				doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
			}
		}
		else
		{
			doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
		}
	}

    function browseFdcsWOElements(fldName) {

    	popupParameters = "workNumber="+ frm.PoHeader_udf7Code.value +";";
       	popupParameters = popupParameters + "segNumber="+ frm.PoHeader_udf8Code.value +";";
       	popupParameters = popupParameters + "opNumber="+ frm.PoHeader_udf9Code.value +";";
       	popupParameters = popupParameters + "custNumber="+ frm.PoHeader_udf11Code.value +";";

    	browseLookup( fldName, 'fdc-wo-elements' ) ;
    }

    function addUp(field, decimals)
	{
		var price_dec = decimals;
		if (price_dec == undefined) {
			price_dec = <%=Integer.valueOf(s_price_decimals).intValue()%>;
		}
		var p = nformat(eval(nfilter(field)), price_dec);
		field.value = p;
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

    function configureFieldsUDF() {
		<% if (udf_field_depencies.equals("Y")) {%>
			var udf1 = frm.PoHeader_udf1Code.value;
			var udf3 = frm.PoHeader_udf3Code.value;

			if ((udf1 == 'RESALECUST' || udf1 == 'RESALEEXP') && (udf3 == null || udf3 == '')) {
				var options = frm.PoHeader_udf3Code.options;

				for (var i = 0; i < options.length; i++) {
					if (options[i].value == 'OSL') {
						udf3 = 'OSL';
						frm.PoHeader_udf3Code.selectedIndex = i;
						break;
					}
				}
			}
			if (udf1 == 'CONSUMABLES' || udf1 == 'USEDPARTS' || udf1 == 'INVENTORY')
			{
				frm.PoHeader_udf2Code.value = "";
				frm.PoHeader_udf3Code.value = "";
				frm.PoHeader_udf5Code.value = "";
				frm.PoHeader_udf7Code.value = "";
				frm.PoHeader_udf8Code.value = "";
				frm.PoHeader_udf9Code.value = "";
				frm.PoHeader_udf10Code.value = "";
				frm.PoHeader_udf11Code.value = "";
				frm.PoHeader_udf12Code.value = "";

				hideArea("po03LabelRow"); document.getElementById('po03FieldRow').style.display = 'none'; //hideArea("po03FieldRow");
				//hideArea("po02LabelRow"); hideArea("po02FieldRow");
				hideArea("po05LabelRow"); hideArea("po05FieldRow");
				hideArea("po07LabelRow"); hideArea("po07FieldRow");
				hideArea("po08LabelRow"); hideArea("po08FieldRow");
				hideArea("po09LabelRow"); hideArea("po09FieldRow");
				hideArea("po10LabelRow"); hideArea("po10FieldRow");
				hideArea("po11LabelRow"); hideArea("po11FieldRow");
				hideArea("po12LabelRow"); hideArea("po12FieldRow");

				allowInputEdit(frm.PoHeader_udf1Code, true);
				allowInputEdit(frm.PoHeader_udf3Code, true);
				allowInputEdit(frm.PoHeader_udf13Code, true);
			}
			else if ((udf1 == 'RESALECUST' || udf1 == 'RESALEEXP') && udf3 == 'PARTS')
			{
				displayArea("po03LabelRow"); document.getElementById('po03FieldRow').style.display = ''; //displayArea("po03FieldRow");
				displayArea("po05LabelRow"); displayArea("po05FieldRow");

				//hideArea("po02LabelRow"); hideArea("po02FieldRow");
				hideArea("po07LabelRow"); hideArea("po07FieldRow");
				hideArea("po08LabelRow"); hideArea("po08FieldRow");
				hideArea("po09LabelRow"); hideArea("po09FieldRow");
				hideArea("po10LabelRow"); hideArea("po10FieldRow");
				hideArea("po11LabelRow"); hideArea("po11FieldRow");
				hideArea("po12LabelRow"); hideArea("po12FieldRow");

				allowInputEdit(frm.PoHeader_udf1Code, true);
				allowInputEdit(frm.PoHeader_udf3Code, true);
				allowInputEdit(frm.PoHeader_udf13Code, true);

				frm.PoHeader_udf2Code.value = "";
				frm.PoHeader_udf9Code.value = "";
				frm.PoHeader_udf10Code.value = "";
				frm.PoHeader_udf12Code.value = "";
			}
			else if ( (udf1 == 'RESALECUST' || udf1 == 'RESALEEXP') && udf3 == 'OSL')
			{
				var udf7 = frm.PoHeader_udf7Code.value;
				var udf8 = frm.PoHeader_udf8Code.value;
				if (udf7 == '' && udf8 == '') {

					if (udf1 == 'RESALECUST') {
						frm.PoHeader_udf13Code.value = "718100";
					} else {
						frm.PoHeader_udf13Code.value = "718200";
					}

					displayArea("po03LabelRow"); document.getElementById('po03FieldRow').style.display = ''; //displayArea("po03FieldRow");
					hideArea("po05LabelRow"); hideArea("po05FieldRow");
					displayArea("po07LabelRow"); displayArea("po07FieldRow");
					displayArea("po08LabelRow"); displayArea("po08FieldRow");

					//hideArea("rq02LabelRow"); hideArea("rq02FieldRow");
					hideArea("po09LabelRow"); hideArea("po09FieldRow");
					hideArea("po10LabelRow"); hideArea("po10FieldRow");
					hideArea("po11LabelRow"); hideArea("po11FieldRow");
					hideArea("po12LabelRow"); hideArea("po12FieldRow");

					allowInputEdit(frm.PoHeader_udf1Code, true);
					allowInputEdit(frm.PoHeader_udf3Code, true);
					allowInputEdit(frm.PoHeader_udf8Code, false);
					allowInputEdit(frm.PoHeader_udf13Code, true);

				} else {

					if (udf1 == 'RESALECUST') {
						frm.PoHeader_udf13Code.value = "718100";
					} else {
						frm.PoHeader_udf13Code.value = "718200";
					}

					var msg = 'Work Order #' + udf7
					msg = msg + ' - Segment #' + udf8;
					if (frm.PoHeader_internalComments.value == '') {
						frm.PoHeader_internalComments.value = msg;
					}

					frm.PoHeader_udf5Code.value = '';

					hideArea("po05LabelRow"); hideArea("po05FieldRow");
					//displayArea("rq02LabelRow"); displayArea("rq02FieldRow");
					displayArea("po09LabelRow"); displayArea("po09FieldRow");
					displayArea("po10LabelRow"); displayArea("po10FieldRow");
					displayArea("po11LabelRow"); displayArea("po11FieldRow");
					displayArea("po12LabelRow"); displayArea("po12FieldRow");

					allowInputEdit(frm.PoHeader_udf1Code, false);
					allowInputEdit(frm.PoHeader_udf3Code, false);
					allowInputEdit(frm.PoHeader_udf13Code, false);
				}
			}
			else if ((udf1 == 'RESALECUST' || udf1 == 'RESALEEXP') && udf3 == 'PARTS')
			{
				//frm.PoHeader_udf13Code.value = "";

				displayArea("po03LabelRow"); document.getElementById('po03FieldRow').style.display = '';//displayArea("po03FieldRow");
				displayArea("po05LabelRow"); displayArea("po05FieldRow");

				//hideArea("rq02LabelRow"); hideArea("rq02FieldRow");
				hideArea("po07LabelRow"); hideArea("po07FieldRow");
				hideArea("po08LabelRow"); hideArea("po08FieldRow");
				hideArea("po09LabelRow"); hideArea("po09FieldRow");
				hideArea("po10LabelRow"); hideArea("po10FieldRow");
				hideArea("po11LabelRow"); hideArea("po11FieldRow");
				hideArea("po12LabelRow"); hideArea("po12FieldRow");

				allowInputEdit(frm.PoHeader_udf1Code, true);
				allowInputEdit(frm.PoHeader_udf3Code, true);
				allowInputEdit(frm.PoHeader_udf13Code, true);

				frm.PoHeader_udf2Code.value = "";
				frm.PoHeader_udf9Code.value = "";
				frm.PoHeader_udf10Code.value = "";
				frm.PoHeader_udf12Code.value = "";
			}
			else
			{
				//frm.PoHeader_udf13Code.value = "";
				allowInputEdit(frm.PoHeader_udf8Code, true);

				displayArea("po03LabelRow"); document.getElementById('po03FieldRow').style.display = ''; //displayArea("po03FieldRow");
				//hideArea("po02LabelRow"); hideArea("po02FieldRow");
				hideArea("po05LabelRow"); hideArea("po05FieldRow");
				hideArea("po07LabelRow"); hideArea("po07FieldRow");
				hideArea("po08LabelRow"); hideArea("po08FieldRow");
				hideArea("po09LabelRow"); hideArea("po09FieldRow");
				hideArea("po10LabelRow"); hideArea("po10FieldRow");
				hideArea("po11LabelRow"); hideArea("po11FieldRow");
				hideArea("po12LabelRow"); hideArea("po12FieldRow");
			}
		<% } %>


    }


// End Hide script -->
</SCRIPT>
