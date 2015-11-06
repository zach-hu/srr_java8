<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.GeneralStatus" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String labelAction = (String)request.getAttribute("labelAction");
	String currentProcess = (String) request.getAttribute("currentProcess");
	currentProcess = HiltonUtility.ckNull(currentProcess);

	Labels label = (Labels) request.getAttribute("label");
	if (label == null)
	{
		label = (Labels) request.getAttribute("labels");
	}
	if (label == null)
	{
		label = new Labels();
		label.setOwner(uid);
		label.setAllowEdit("Y");
		label.setValidationLink("javascript: void(0);");
		if (labelAction == null) {
			labelAction = "addLabel";
		}

	} else 	if (labelAction == null) {
		labelAction = "saveLabel";
	}


	String errorMsg = (String)request.getAttribute("errorMsg");
	errorMsg = HiltonUtility.ckNull(errorMsg);
	if (errorMsg.length() > 0) {
		errorMsg = errorMsg + "<br>";
	}
%>
<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels", "Labels")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom width=* align=right height=30px>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b></b></td>
			<td width=125px></td>
		</tr>
		<tr>
			<td align=right><b></b></td>
			<td width=125px></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
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
<table border="0" cellpadding="0" cellspacing="0" width="<%=formWidth%>">
<tr>
	<td class=error align=center>
		<table border=0 cellpadding=1 cellspacing=0 width=90%>
		<tr><td class=error align=center><%=errorMsg%><br></td></tr>
		</table>
	</td>
</tr>
<tr>
	<td align="center" valign="top">
		<table border="0" cellspacing="0" cellpadding="0" width="100%">
		<tr>
			<td align=center valign=top>
				<table border=0 cellspacing="0" cellpadding="2" width="100%">
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-code", "Label Code")%>:</td>
					<td>
						<input type="text" title="" name="Labels_labelCode" size="40" maxLength="60" value="<%=label.getLabelCode() %>" onChange="this.value=this.value.toUpperCase();" tabIndex="1">
						Field Length
						<input type="text" name="Labels_fieldLength" size="3" maxLength="3" value="<%= label.getFieldLength() != null && !"null".equals(label.getFieldLength()) ? label.getFieldLength() : ""%>"/>
					</td>
					<td rowspan=6 valign=top align=center>
						<table border=0 cellspacing="0" cellpadding="2">
						<tr>
							<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-moduleAccess", "Module Access")%>:</td>
							<td>
								<select name="Labels_moduleAccess" tabIndex="31">
									<option value="ADMIN" <% if (label.getModuleAccess().equals("ADMIN")) {%>selected<%}%>>ADMIN</option>
									<option value="APPROVALS" <% if (label.getModuleAccess().equals("APPROVALS")) {%>selected<%}%>>APPROVALS</option>
									<option value="ASSET" <% if (label.getModuleAccess().equals("ASSET")) {%>selected<%}%>>ASSET</option>
									<option value="CATALOG ITEMS" <% if (label.getModuleAccess().equals("CATALOG ITEMS")) {%>selected<%}%>>CATALOG ITEMS</option>
									<option value="CONTACT" <% if (label.getModuleAccess().equals("CONTACT")) {%>selected<%}%>>CONTACT</option>
									<option value="DOCUMENTS" <% if (label.getModuleAccess().equals("DOCUMENTS")) {%>selected<%}%>>DOCUMENTS</option>
									<option value="GENERAL" <% if (label.getModuleAccess().equals("GENERAL")) {%>selected<%}%>>GENERAL</option>
									<option value="INVENTORY" <% if (label.getModuleAccess().equals("INVENTORY")) {%>selected<%}%>>INVENTORY</option>
									<option value="DISBURSEMENT" <% if (label.getModuleAccess().equals("DISBURSEMENT")) {%>selected<%}%>>DISBURSEMENT</option>
									<option value="INVBINLOCATION" <% if (label.getModuleAccess().equals("INVBINLOCATION")) {%>selected<%}%>>INV BIN LOCATION</option>
									<option value="INVOICE" <% if (label.getModuleAccess().equals("INVOICE")) {%>selected<%}%>>INVOICE</option>
									<option value="LOGIN" <% if (label.getModuleAccess().equals("LOGIN")) {%>selected<%}%>>LOGIN</option>
									<option value="PURCHASE ORDERS" <% if (label.getModuleAccess().equals("PURCHASE ORDERS")) {%>selected<%}%>>PURCHASE ORDERS</option>
									<option value="PURIDIOMX ADMIN" <% if (label.getModuleAccess().equals("PURIDIOMX ADMIN")) {%>selected<%}%>>PURIDIOMX ADMIN</option>
									<option value="RECEIVING" <% if (label.getModuleAccess().equals("RECEIVING")) {%>selected<%}%>>RECEIVING</option>
									<option value="REQUISITIONS" <% if (label.getModuleAccess().equals("REQUISITIONS")) {%>selected<%}%>>REQUISITIONS</option>
									<option value="REQUEST FOR QUOTES" <% if (label.getModuleAccess().equals("REQUEST FOR QUOTES")) {%>selected<%}%>>REQUEST FOR QUOTES</option>
									<option value="SCHEDULES" <% if (label.getModuleAccess().equals("SCHEDULES")) {%>selected<%}%>>SCHEDULES</option>
									<option value="SUPPLIER" <% if (label.getModuleAccess().equals("SUPPLIER")) {%>selected<%}%>>SUPPLIER</option>
									<option value="CONTACT" <% if (label.getModuleAccess().equals("CONTACT")) {%>selected<%}%>>CONTACT</option>
									<option value="SUPPLIERPORTAL" <% if (label.getModuleAccess().equals("SUPPLIERPORTAL")) {%>selected<%}%>>SUPPLIERPORTAL</option>
									<option value="SUPPLIERPORTALCONTACT" <% if (label.getModuleAccess().equals("SUPPLIERPORTALCONTACT")) {%>selected<%}%>>SUPPLIERPORTALCONTACT</option>
									<option value="TOTALS" <% if (label.getModuleAccess().equals("TOTALS")) {%>selected<%}%>>TOTALS</option>
									<option value="USER" <% if (label.getModuleAccess().equals("USER")) {%>selected<%}%>>USER</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-module", "Module")%>:</td>
							<td>
								<select name="Labels_module" tabIndex="32">
									<option value="" <% if (HiltonUtility.isEmpty(label.getModule())) {%>selected<%}%>>&nbsp;</option>
									<option value="asset" <% if (label.getModule().equals("asset")) {%>selected<%}%>>asset</option>
									<option value="assetlocation" <% if (label.getModule().equals("assetlocation")) {%>selected<%}%>>assetlocation</option>
									<option value="bb" <% if (label.getModule().equals("bb")) {%>selected<%}%>>bb</option>
									<option value="cat" <% if (label.getModule().equals("cat")) {%>selected<%}%>>cat</option>
									<option value="cnt" <% if (label.getModule().equals("cnt")) {%>selected<%}%>>cnt</option>
									<option value="dept" <% if (label.getModule().equals("dept")) {%>selected<%}%>>dept</option>
									<option value="deptbuyer" <% if (label.getModule().equals("deptbuyer")) {%>selected<%}%>>deptbuyer</option>
									<option value="inv" <% if (label.getModule().equals("inv")) {%>selected<%}%>>inv</option>
									<option value="iv" <% if (label.getModule().equals("iv")) {%>selected<%}%>>iv</option>
									<option value="ivc" <% if (label.getModule().equals("ivc")) {%>selected<%}%>>ivc</option>
									<option value="dsb" <% if (label.getModule().equals("dsb")) {%>selected<%}%>>dsb</option>
									<option value="pdf" <% if (label.getModule().equals("pdf")) {%>selected<%}%>>pdf</option>
									<option value="po" <% if (label.getModule().equals("po")) {%>selected<%}%>>po</option>
									<option value="rec" <% if (label.getModule().equals("rec")) {%>selected<%}%>>rec</option>
									<option value="req" <% if (label.getModule().equals("req")) {%>selected<%}%>>req</option>
									<option value="rfq" <% if (label.getModule().equals("rfq")) {%>selected<%}%>>rfq</option>
									<option value="routing" <% if (label.getModule().equals("routing")) {%>selected<%}%>>routing</option>
									<option value="sup" <% if (label.getModule().equals("sup")) {%>selected<%}%>>sup</option>
									<option value="user" <% if (label.getModule().equals("user")) {%>selected<%}%>>user</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-moduleType", "Module Type")%>:</td>
							<td><input type="text" title="" name="Labels_moduleType" size="15" maxLegth="15" value="<%=label.getModuleType() %>"  tabIndex="33"></td>
						</tr>
						<tr>
							<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-language", "Language")%>:</td>
							<td>
								<select name="Labels_language" tabIndex="34">
									<option value="EN" <% if (label.getLanguage().equals("EN")) {%>selected<%}%>>English</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-status", "Status")%>:</td>
							<td>
								<select name="Labels_status" tabIndex="35">
									<option value="<%=GeneralStatus.STATUS_PERMANENT%>" <%if(label.getStatus().equals(GeneralStatus.STATUS_PERMANENT)){ %> selected<%}%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "permanent", "Permanent")%></option>
									<option value="<%=GeneralStatus.STATUS_TEMPORARY%>" <%if(label.getStatus().equals(GeneralStatus.STATUS_TEMPORARY)){ %> selected<%}%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "temporary", "Temporary")%></option>
									<option value="<%=GeneralStatus.STATUS_INACTIVE%>" <%if(label.getStatus().equals(GeneralStatus.STATUS_INACTIVE)){ %> selected<%}%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inactive", "Inactive")%></option>
								</select>
							</td>
						</tr>
						</table>
					</td>

				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-value", "Label Value")%>:</td>
					<td><input type="text" title="" name="Labels_labelValue" size="80" maxLegth="2000" value="<%=label.getLabelValue() %>"  tabIndex="2"></td>
				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-abbreviation", "Abbreviation")%>:</td>
					<td><input type="text" title="" name="Labels_abbreviation" size="40" maxLegth="60" value="<%=label.getAbbreviation() %>"  tabIndex="3"></td>
				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-hoverHelp", "Hover Help")%>:</td>
					<td><input type="text" title="" name="Labels_hoverHelp" size="80" maxLegth="255" value="<%=label.getHoverHelp() %>"  tabIndex="4"></td>
				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-validationMessage", "Validation Message")%>:</td>
					<td><input type="text" title="" name="Labels_validationMessage" size="80" maxLegth="255" value="<%=label.getValidationMessage() %>"  tabIndex="5"></td>
				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-validationSeverity", "Validation Severity")%>:</td>
					<td>
						<select name="Labels_validationSeverity" tabIndex="6">
							<option value="E" <% if (label.getValidationSeverity().equals("E")) {%>selected<%}%>>Error</option>
							<option value="W" <% if (label.getValidationSeverity().equals("W")) {%>selected<%}%>>Warning</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-validationLink", "Validation Link")%>:</td>
					<td colspan=2>
						<select name="as_validationLink" onChange="setValidationLinkSelection();" tabIndex="7">
							<option value="OTHER">N/A or Other (enter below)</option>
							<option value="javascript: doSubmit('requests/req_general_info.jsp', 'RequisitionHeaderRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('requests/req_general_info.jsp', 'RequisitionHeaderRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('requests/req_general_info.jsp', 'RequisitionHeaderRetrieveById'); void(0);</option>
							<option value="javascript: doSubmit('requests/req_items.jsp', 'RequisitionLineRetrieveByHeader'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('requests/req_items.jsp', 'RequisitionLineRetrieveByHeader'); void(0);")) {%>selected<%}%>>javascript: doSubmit('requests/req_items.jsp', 'RequisitionLineRetrieveByHeader'); void(0);</option>
							<option value="javascript: doSubmit('requests/req_supplier.jsp', 'RequisitionHeaderVendorRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('requests/req_supplier.jsp', 'RequisitionHeaderVendorRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('requests/req_supplier.jsp', 'RequisitionHeaderVendorRetrieveById'); void(0);</option>
							<option value="javascript: doSubmit('requests/req_shipping.jsp', 'RequisitionHeaderShipToRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('requests/req_shipping.jsp', 'RequisitionHeaderShipToRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('requests/req_shipping.jsp', 'RequisitionHeaderShipToRetrieveById'); void(0);</option>
							<option value="javascript: doSubmit('requests/req_billing.jsp', 'RequisitionHeaderBillToRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('requests/req_billing.jsp', 'RequisitionHeaderBillToRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('requests/req_billing.jsp', 'RequisitionHeaderBillToRetrieveById'); void(0);</option>
							<option value="javascript: doSubmit('requests/req_notes.jsp', 'DocCommentRetrieveByLine'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('requests/req_notes.jsp', 'DocCommentRetrieveByLine'); void(0);")) {%>selected<%}%>>javascript: doSubmit('requests/req_notes.jsp', 'DocCommentRetrieveByLine'); void(0);</option>
							<option value="javascript: doSubmit('requests/req_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('requests/req_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);")) {%>selected<%}%>>javascript: doSubmit('requests/req_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);</option>
							<option value="javascript: doSubmit('requests/req_accounts.jsp', 'AccountRetrieveByLine'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('requests/req_accounts.jsp', 'AccountRetrieveByLine'); void(0);")) {%>selected<%}%>>javascript: doSubmit('requests/req_accounts.jsp', 'AccountRetrieveByLine'); void(0);</option>
							<option value="javascript: doSubmit('requests/req_totals.jsp', 'RequisitionHeaderRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('requests/req_totals.jsp', 'RequisitionHeaderRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('requests/req_totals.jsp', 'RequisitionHeaderRetrieveById'); void(0);</option>
							<option value="javascript: doSubmit('requests/req_review.jsp', 'RequisitionRetrieve'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('requests/req_review.jsp', 'RequisitionRetrieve'); void(0);")) {%>selected<%}%>>javascript: doSubmit('requests/req_review.jsp', 'RequisitionRetrieve'); void(0);</option>
							<option value="javascript: doSubmit('requests/req_supplier.jsp', 'RequisitionHeaderVendorRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('requests/req_supplier.jsp', 'RequisitionHeaderVendorRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('requests/req_supplier.jsp', 'RequisitionHeaderVendorRetrieveById'); void(0);</option>
							<option value="javascript: doSubmit('rfq/rfq_general_info.jsp', 'RfqHeaderRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('rfq/rfq_general_info.jsp', 'RfqHeaderRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('rfq/rfq_general_info.jsp', 'RfqHeaderRetrieveById'); void(0);</option>
							<option value="javascript: doSubmit('rfq/rfq_items.jsp', 'RfqLineRetrieveByHeader'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('rfq/rfq_items.jsp', 'RfqLineRetrieveByHeader'); void(0);")) {%>selected<%}%>>javascript: doSubmit('rfq/rfq_items.jsp', 'RfqLineRetrieveByHeader'); void(0);</option>
							<option value="javascript: doSubmit('rfq/rfq_webpost_options.jsp', 'RfqHeaderRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('rfq/rfq_webpost_options.jsp', 'RfqHeaderRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('rfq/rfq_webpost_options.jsp', 'RfqHeaderRetrieveById'); void(0);</option>
							<option value="javascript: doSubmit('rfq/rfq_suppliers.jsp', 'RfqVendorRetrieveByHeader'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('rfq/rfq_suppliers.jsp', 'RfqVendorRetrieveByHeader'); void(0);")) {%>selected<%}%>>javascript: doSubmit('rfq/rfq_suppliers.jsp', 'RfqVendorRetrieveByHeader'); void(0);</option>
							<option value="javascript: doSubmit('rfq/rfq_questions.jsp', 'RfqQuestionRetrieveByHeader'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('rfq/rfq_questions.jsp', 'RfqQuestionRetrieveByHeader'); void(0);")) {%>selected<%}%>>javascript: doSubmit('rfq/rfq_questions.jsp', 'RfqQuestionRetrieveByHeader'); void(0);</option>
							<option value="javascript: doSubmit('rfq/rfq_shipping.jsp', 'RfqHeaderShipToRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('rfq/rfq_shipping.jsp', 'RfqHeaderShipToRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('rfq/rfq_shipping.jsp', 'RfqHeaderShipToRetrieveById'); void(0);</option>
							<option value="javascript: doSubmit('rfq/rfq_notes.jsp', 'DocCommentRetrieveByLine'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('rfq/rfq_notes.jsp', 'DocCommentRetrieveByLine'); void(0);")) {%>selected<%}%>>javascript: doSubmit('rfq/rfq_notes.jsp', 'DocCommentRetrieveByLine'); void(0);</option>
							<option value="javascript: doSubmit('rfq/rfq_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('rfq/rfq_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);")) {%>selected<%}%>>javascript: doSubmit('rfq/rfq_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);</option>
							<option value="javascript: doSubmit('rfq/rfq_review.jsp', 'RfqRetrieve'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('rfq/rfq_review.jsp', 'RfqRetrieve'); void(0);")) {%>selected<%}%>>javascript: doSubmit('rfq/rfq_review.jsp', 'RfqRetrieve'); void(0);</option>
							<option value="javascript: doSubmit('orders/po_general_info.jsp', 'PoHeaderRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('orders/po_general_info.jsp', 'PoHeaderRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('orders/po_general_info.jsp', 'PoHeaderRetrieveById'); void(0);</option>
							<option value="javascript: doSubmit('orders/po_items.jsp', 'PoLineRetrieveByHeader'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('orders/po_items.jsp', 'PoLineRetrieveByHeader'); void(0);")) {%>selected<%}%>>javascript: doSubmit('orders/po_items.jsp', 'PoLineRetrieveByHeader'); void(0);</option>
							<option value="javascript: doSubmit('orders/po_blanket.jsp', 'PoGetBlanketInfo'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('orders/po_blanket.jsp', 'PoGetBlanketInfo'); void(0);")) {%>selected<%}%>>javascript: doSubmit('orders/po_blanket.jsp', 'PoGetBlanketInfo'); void(0);</option>
							<option value="javascript: doSubmit('orders/po_security.jsp', 'PoSecurityRetrieveBy'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('orders/po_security.jsp', 'PoSecurityRetrieveBy'); void(0);")) {%>selected<%}%>>javascript: doSubmit('orders/po_security.jsp', 'PoSecurityRetrieveBy'); void(0);</option>
							<option value="javascript: doSubmit('orders/po_insurance.jsp', 'VendorInsuranceRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('orders/po_insurance.jsp', 'VendorInsuranceRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('orders/po_insurance.jsp', 'VendorInsuranceRetrieveById'); void(0);</option>
							<option value="javascript: doSubmit('orders/po_supplier.jsp', 'PoHeaderVendorRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('orders/po_supplier.jsp', 'PoHeaderVendorRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('orders/po_supplier.jsp', 'PoHeaderVendorRetrieveById'); void(0);</option>
							<option value="javascript: doSubmit('orders/po_shipping.jsp', 'PoHeaderShipToRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('orders/po_shipping.jsp', 'PoHeaderShipToRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('orders/po_shipping.jsp', 'PoHeaderShipToRetrieveById'); void(0);</option>
							<option value="javascript: doSubmit('orders/po_billing.jsp', 'PoHeaderBillToRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('orders/po_billing.jsp', 'PoHeaderBillToRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('orders/po_billing.jsp', 'PoHeaderBillToRetrieveById'); void(0);</option>
							<option value="javascript: doSubmit('orders/po_notes.jsp', 'DocCommentRetrieveByLine'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('orders/po_notes.jsp', 'DocCommentRetrieveByLine'); void(0);")) {%>selected<%}%>>javascript: doSubmit('orders/po_notes.jsp', 'DocCommentRetrieveByLine'); void(0);</option>
							<option value="javascript: doSubmit('orders/po_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('orders/po_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);")) {%>selected<%}%>>javascript: doSubmit('orders/po_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);</option>
							<option value="javascript: doSubmit('orders/po_accounts.jsp', 'AccountRetrieveByLine'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('orders/po_accounts.jsp', 'AccountRetrieveByLine'); void(0);")) {%>selected<%}%>>javascript: doSubmit('orders/po_accounts.jsp', 'AccountRetrieveByLine'); void(0);</option>
							<option value="javascript: doSubmit('orders/po_totals.jsp', 'PoHeaderRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('orders/po_totals.jsp', 'PoHeaderRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('orders/po_totals.jsp', 'PoHeaderRetrieveById'); void(0);</option>
							<option value="javascript: doSubmit('invoice/iv_general_info.jsp', 'InvoiceHeaderShipToRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('invoice/iv_general_info.jsp', 'InvoiceHeaderShipToRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('invoice/iv_general_info.jsp', 'InvoiceHeaderShipToRetrieveById'); void(0);</option>
							<option value="javascript: doSubmit('invoice/iv_payment_info.jsp', 'InvoiceHeaderVendorRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('invoice/iv_payment_info.jsp', 'InvoiceHeaderVendorRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('invoice/iv_payment_info.jsp', 'InvoiceHeaderVendorRetrieveById'); void(0);</option>
							<option value="javascript: doSubmit('invoice/iv_items.jsp', 'InvoiceHeaderRetrieveById;InvoiceLineRetrieveByHeader'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('invoice/iv_items.jsp', 'InvoiceHeaderRetrieveById;InvoiceLineRetrieveByHeader'); void(0);")) {%>selected<%}%>>javascript: doSubmit('invoice/iv_items.jsp', 'InvoiceHeaderRetrieveById;InvoiceLineRetrieveByHeader'); void(0);</option>
							<option value="javascript: doSubmit('invoice/iv_notes.jsp', 'DocCommentRetrieveByLine'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('invoice/iv_notes.jsp', 'DocCommentRetrieveByLine'); void(0);")) {%>selected<%}%>>javascript: doSubmit('invoice/iv_notes.jsp', 'DocCommentRetrieveByLine'); void(0);</option>
							<option value="javascript: doSubmit('invoice/iv_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('invoice/iv_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);")) {%>selected<%}%>>javascript: doSubmit('invoice/iv_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);</option>
							<option value="javascript: doSubmit('invoice/iv_accounts.jsp', 'AccountRetrieveByLine'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('invoice/iv_accounts.jsp', 'AccountRetrieveByLine'); void(0);")) {%>selected<%}%>>javascript: doSubmit('invoice/iv_accounts.jsp', 'AccountRetrieveByLine'); void(0);</option>
							<option value="javascript: doSubmit('invoice/iv_review.jsp', 'InvoiceRetrieve'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('invoice/iv_review.jsp', 'InvoiceRetrieve'); void(0);")) {%>selected<%}%>>javascript: doSubmit('invoice/iv_review.jsp', 'InvoiceRetrieve'); void(0);</option>
							<option value="javascript: doSubmit('admin/supplier/supplier_info.jsp', 'VendorRetrieveById'); void(0);" <% if (label.getValidationLink().equals("javascript: doSubmit('admin/supplier/supplier_info.jsp', 'VendorRetrieveById'); void(0);")) {%>selected<%}%>>javascript: doSubmit('admin/supplier/supplier_info.jsp', 'VendorRetrieveById'); void(0);</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="text" title="" name="Labels_validationLink_text" size="80" maxLegth="255" value="<%=label.getValidationLink() %>"  tabIndex="8"></td>
					<td><input type="hidden" title="" name="Labels_validationLink" size="80" maxLegth="255" value="<%=label.getValidationLink() %>"  tabIndex="8"></td>
				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-fieldname", "Field Name")%>:</td>
					<td><input type="text" title="" name="Labels_fieldname" size="40" maxLegth="60" value="<%=label.getFieldname() %>"  tabIndex="9"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<input type="checkbox" name="c_checkbox" <%if (label.getAllowLink().equals("Y")) { %>checked <% } %> onClick="setCheckbox(frm.Labels_allowLink, 0)" tabIndex="21">
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-allowLink", "Link")%>
						<tsa:hidden name="Labels_allowLink" value="<%=label.getAllowLink()%>"/>
					</td>
				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-browseName", "Browse Name")%>:</td>
					<td><input type="text" title="" name="Labels_browseName" size="40" maxLegth="60" value="<%=label.getBrowseName() %>"  tabIndex="10"></td>
				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-browseType", "Browse Type")%>:</td>
					<td><input type="text" title="" name="Labels_udf1Code" size="40" maxLegth="60" value="<%=label.getUdf1Code() %>"  tabIndex="10"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<table border=0 cellpadding=2 cellspacing=0>
						<tr>
							<td>
								<input type="checkbox" name="c_checkbox" <%if (label.getAllowEdit().equals("Y")) { %>checked <% } %> onClick="setCheckbox(frm.Labels_allowEdit, 1)" tabIndex="21">
								<tsa:hidden name="Labels_allowEdit" value="<%=label.getAllowEdit()%>"/>
							</td>
							<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-allowEdit", "Allow Edit")%></td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" name="c_checkbox" <%if (label.getVisible().equals("Y")) { %>checked <% } %> onClick="setCheckbox(frm.Labels_visible, 2)" tabIndex="22">
								<tsa:hidden name="Labels_visible" value="<%=label.getVisible()%>"/>
							</td>
							<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "label-visible", "Visible")%></td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" name="c_checkbox" <%if (label.getReadOnly().equals("Y")) { %>checked <% } %> onClick="setCheckbox(frm.Labels_readOnly, 3)" tabIndex="23">
								<tsa:hidden name="Labels_readOnly" value="<%=label.getReadOnly()%>"/>
							</td>
							<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-labelreadonly", "Read Only")%></td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" name="c_checkbox" <%if (label.getRequired().equals("Y")) { %>checked <% } %> onClick="setCheckbox(frm.Labels_required, 4)" tabIndex="24">
								<tsa:hidden name="Labels_required" value="<%=label.getRequired()%>"/>
							</td>
							<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-labelrequired", "Required")%></td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" name="c_checkbox" <%if (label.getValidation().equals("Y")) { %>checked <% } %> onClick="setCheckbox(frm.Labels_validation, 5)" tabIndex="25">
								<tsa:hidden name="Labels_validation" value="<%=label.getValidation()%>"/>
							</td>
							<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-validation", "Valid")%></td>
						</tr>
						</table>
					</td>
					<td>
						<table border=0 cellpadding=2 cellspacing=0>
				        <tr>
							<td <%=HtmlWriter.isVisible(oid, "labels-owner")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-owner", "Owner")%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "labels-owner")%>><input type=text name="Labels_owner" value="<%=label.getOwner()%>" size=15 maxLegth=10 disabled></td>
				        </tr>
				        <tr>
							<td <%=HtmlWriter.isVisible(oid, "labels-lastChangeBy")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-lastChangeBy", "Last Changed By")%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "labels-lastChangeBy")%>><input type=text name="Labels_lastChangeBy" value="<%=label.getLastChangeBy()%>" size=15 maxLegth=10 disabled></td>
				        </tr>
				        <tr>
							<td <%=HtmlWriter.isVisible(oid, "labels-lastChangeDate")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "labels-lastChangeDate", "Last Change Date")%></td>
							<td <%=HtmlWriter.isVisible(oid, "labels-lastChangeDate")%>><input type=text name="Labels_lastChangeDate" value="<%=HiltonUtility.getFormattedDate(label.getLastChangeDate(), oid)%>" size=15 maxLegth=10 disabled></td>
						</tr>
						</table>

					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
		<br>
		<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
		<tr>
			<td width="50%" align="center"><div id="pxbutton"><a href="javascript: saveMe(); void(0);" tabIndex="41">Save</a></div></td>
			<td width="50%" align="center"><div id="pxbutton"><a href="javascript: returnMe(); void(0);" tabIndex="42">Return</a></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>


<br>
<br>
<tsa:hidden name="Labels_icLabel" value="<%=label.getIcLabel() %>" />
<tsa:hidden name="currentProcess" value="<%=currentProcess%>"/>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	setValidationLinkSelection();

	function setValidationLinkSelection() {
		var linkValue = frm.as_validationLink[frm.as_validationLink.selectedIndex].value;
		if (linkValue == "OTHER") {
			displayArea("freeFormValidationLink");
			frm.Labels_validationLink_text.value = '';
			frm.Labels_validationLink.value = '';			
		} else {
			hideArea("freeformValidationLink");
			//frm.Labels_validationLink.value = linkValue.replace('javascript: doSubmit(','BDC@1');
			frm.Labels_validationLink.value = linkValue;
			frm.Labels_validationLink_text.value = linkValue;
		}
	}

	function saveMe() {
		frm.Labels_validationLink_text.value = '';
		frm.as_validationLink[frm.as_validationLink.selectedIndex].value = '';
		
<%	if (role.getAccessRights("LABELS") == 99 ) { %>
		frm.browseName.value = 'labels-admin';
<%	} else {%>
		frm.browseName.value = 'labels';
<%	}%>
		var moduleAccess = frm.Labels_moduleAccess[frm.Labels_moduleAccess.selectedIndex].value;
		frm.currentProcess.value = moduleAccess;
		setFilter("Labels_moduleAccess", "=", moduleAccess);

		frm.failurePage.value = "/admin/labels/labels_edit.jsp";
<%	if (labelAction.equals("addLabel")) {%>
		doSubmit('/admin/labels/labels.jsp', 'LabelsAddRefresh;BrowseRetrieve');
<%	} else {%>
		doSubmit('/admin/labels/labels.jsp', 'LabelsUpdateById;LabelsRefresh;BrowseRetrieve');
<%	}%>
	}

	function returnMe() {
		history.go(-1);
	}
// End Hide script -->
</SCRIPT>