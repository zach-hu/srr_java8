<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>


<script language='Javascript1.2' src="<%=contextPath%>/scripts/filter.js"></script>
<%
	Encoder encoder = DefaultEncoder.getInstance();
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	formField = (String) request.getAttribute("formField");
	String	fromPage = (String) request.getAttribute("fromPage");
	Object rowObj = request.getAttribute("index");
	String row = "0";
	if (rowObj instanceof String[]) {
		String [] rows = (String[]) rowObj;
		row = rows[0];
	} else if (rowObj instanceof String) {
		row = (String) rowObj;
	}

	String	mails = (String) request.getAttribute("mails");
	if (formField == null)	{	formField = "";	}
	if (fromPage == null)	{	fromPage = "";	}
	if (mails == null)		{	mails = "";		}
%>

<div id=pageForm style="display:none;visibility:hidden;">
<%@ include file="/browse/browse_form.jsp" %>

<tsa:hidden name="browsePage" value="/browse/browse_popup.jsp"/>
<tsa:hidden name="browseName" value=""/>
<tsa:hidden name="formField" value="<%=formField%>"/>
<tsa:hidden name="ApprovalLog_icHeader" value=""/>
<tsa:hidden name="addUser" value=""/>
<tsa:hidden name="index" value="<%=row%>"/>
<tsa:hidden name="supplierCommodity" value=""/>
<tsa:hidden name="supplierClass" value=""/>
<tsa:hidden name="fromPage" value="<%=fromPage%>"/>
<tsa:hidden name="mails" value="<%=mails%>"/>
<tsa:hidden name="Account_fld1" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"Account_fld1\"))%>"/>
<tsa:hidden name="Account_fld2" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"Account_fld2\"))%>"/>
<tsa:hidden name="Account_fld3" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"Account_fld3\"))%>"/>
<tsa:hidden name="Account_fld4" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"Account_fld4\"))%>"/>
<tsa:hidden name="Account_fld5" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"Account_fld5\"))%>"/>
<tsa:hidden name="Account_fld6" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"Account_fld6\"))%>"/>
<tsa:hidden name="Account_fld7" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"Account_fld7\"))%>"/>
<tsa:hidden name="Account_fld8" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"Account_fld8\"))%>"/>
<tsa:hidden name="Account_fld9" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"Account_fld9\"))%>"/>
<tsa:hidden name="Account_fld10" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"Account_fld10\"))%>"/>
<tsa:hidden name="Account_fld11" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"Account_fld11\"))%>"/>
<tsa:hidden name="Account_fld12" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"Account_fld12\"))%>"/>
<tsa:hidden name="Account_fld13" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"Account_fld13\"))%>"/>
<tsa:hidden name="Account_fld14" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"Account_fld14\"))%>"/>
<tsa:hidden name="Account_fld15" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"Account_fld15\"))%>"/>

<table width=<%=formWidth%> border=0>
<tr>
	<td align=center>
<%	if (fromPage.equalsIgnoreCase("routinglist")) { %>
		<a href="javascript: cancelAddApprover(); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 alt="Cancel"></a>
<%	} else if (fromPage.equalsIgnoreCase("reportqueue")) { %>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td align="center"><div id="pxbutton"><a href="javascript: chgSendTo(); void(0);">Add</a></div></td>
				<td align="center"><div id="pxbutton"><a href="javascript: doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');">Close</a></div></td>
			</tr>
		</table>
		<tsa:hidden name="c_checkbox" value=""/>
<%	} else if (browseObject.getBrowseName().equalsIgnoreCase("paymentaccount")) { %>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td align="center"><div id="pxbutton"><a href="javascript: window.print(); void(0);">Print</a></div></td>
				<td align="center"><div id="pxbutton"><a href="javascript: doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');">Close</a></div></td>
			</tr>
		</table>
<%	} else if (browseObject.getBrowseName().equalsIgnoreCase("vendoraffiliate-subcontractor") && role.getAccessRights("PO") >= 2 && irows > 0) { %>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td align=center><div id="pxbutton"><a href="javascript: void(0); addSubContractorAffiliates();">Save</a></div></td>
				<td align=center><div id="pxbutton"><a href="javascript: doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');">Close</a></div></td>
			</tr>
		</table>
<%	} else { %>
		<div id="pxbutton"><a href="javascript: doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');">Close</a></div>
<%	} %>
	</td>
</tr>
</table>

</div>
<div id=pageLoading style="display:block;visibility:visible;">
<table border=0 cellspacing=0 cellpadding=0 width=100%>
<tr><td width=100% align=center valign=top><br><b>Loading Page... Please wait.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td></tr>
</table>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var rowSelect;
	var selectedData;
	var row = "<%=headerEncoder.encodeForJavaScript(row)%>";
	var mails = "<%=mails%>";
	
<%	if (browseObject.getBrowseName().equalsIgnoreCase("vendoraffiliate-subcontractor") && irows > 0) { %>
	displayArea("selectPrintDisplay");
<%	}%>

	hideArea("resetOriginalOption");
	hideArea("resetFilterOptions");

<% if (fromPage.equalsIgnoreCase("reportqueue"))
{ %>
	selectUsers();
<% } %>

	 if (filterSet) {
		displayArea("resetOption");
	} else {
		hideArea("resetOption");
	}
<%	if(browseObject.getBrowseName().equalsIgnoreCase("po_approver") || browseObject.getBrowseName().equalsIgnoreCase("approver")) {%>
		hideArea("resetOption");
<%	}
	else if(browseObject.getBrowseName().equalsIgnoreCase("rfq_approver") || browseObject.getBrowseName().indexOf("coda-") >= 0 || browseObject.getBrowseName().indexOf("fdc-") >= 0) {%>
		hideArea("filterOptions");
<%	}%>

<%	if (formField.indexOf("ApprovalLog_") == 0) {%>
		frm.ApprovalLog_icHeader.value = "<%=(String) request.getAttribute("RequisitionHeader_icReqHeader")%>";
		frm.RequisitionHeader_icReqHeader.value = "<%=(String) request.getAttribute("RequisitionHeader_icReqHeader")%>";
<%	}%>

	function viewReq(ic, type) {
		var myCell = document.getElementById("hiddenFields");
		myCell.innerHTML = "";
		var newInputField = "<input type='hidden' name='RequisitionHeader_icReqHeader' value='" + ic + "'>";

		myCell.innerHTML = newInputField;
		doSubmit('/requests/req_summary.jsp', 'RequisitionRetrieve');
	}

	function poApproverAdd(x, y)
	{
		var fld = "<%=encoder.encodeForJavaScript(formField)%>";
		var user = x;
		var userName = y;

		if (fld.indexOf("poApprovers") >= 0)
		{
			window.parent.addApprover(user, userName);
		}

		doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');
	}

	function returnMe(x) {
		var fld = "<%=encoder.encodeForJavaScript(formField)%>";
		selectedData = x;

		<%	if (browseObject.getBrowseName().equalsIgnoreCase("print-check-invoices-popup")) {%>
			window.parent.icIvcHeader = frm.InvoiceHeader_icIvcHeader[rowSelect].value;
			window.parent.supplierName = frm.InvoiceHeader_vendorName[rowSelect].value;
			window.parent.remitAddress = frm.InvoiceAddress_city[rowSelect].value;
			window.parent.invoiceNumber = frm.InvoiceHeader_invoiceNumber[rowSelect].value;
			window.parent.invoiceDate = frm.InvoiceHeader_invoiceDate[rowSelect].value;
			window.parent.netDate = frm.InvoiceHeader_paymentDueDate[rowSelect].value;
			window.parent.invoiceAmount = frm.InvoiceHeader_invoiceTotal[rowSelect].value;
			window.parent.discountAmount = frm.InvoiceHeader_invoiceDiscount[rowSelect].value;
			window.parent.adjustment = frm.InvoiceHeader_invoiceAdjustment[rowSelect].value;
			window.parent.paidAmount = frm.InvoiceHeader_invoicePaid[rowSelect].value;
			window.parent.terms = frm.InvoiceHeader_termsCode[rowSelect].value;
			window.parent.subTotal = frm.InvoiceHeader_invoiceSubtotal[rowSelect].value;
			window.parent.udf9Code = frm.InvoiceHeader_udf9Code[rowSelect].value;
			window.parent.udf10Code = frm.InvoiceHeader_udf10Code[rowSelect].value;

			window.parent.remitToSite = frm.InvoiceAddress_addressLine1[rowSelect].value;
			window.parent.address1 = frm.InvoiceAddress_addressLine2[rowSelect].value;
			window.parent.address2 = frm.InvoiceAddress_addressLine3[rowSelect].value;
			window.parent.state = frm.InvoiceAddress_state[rowSelect].value;
			window.parent.country = frm.InvoiceAddress_country[rowSelect].value;
			window.parent.termsDescription = frm.PaymentTerm_termDescription[rowSelect].value;
			window.parent.discount = frm.InvoiceHeader_termsDiscperc[rowSelect].value;
			window.parent.termsDays = frm.InvoiceHeader_termsDiscdays[rowSelect].value;

			window.parent.addInvoiceRow();
			window.top.hidePopWin();
			return;
		<% } else if (browse.getBrowseName().equalsIgnoreCase("itemlookup") || browse.getBrowseName().equalsIgnoreCase("req-itemlookup")) {%>
			var columns = frm.elements.item("ItemLookup_catalogId");
			var req_type = window.parent.frm.RequisitionHeader_requisitionType.value;
			if (columns.length==undefined)
			{
				window.parent.frm.RequisitionLine_catalogId.value = frm.ItemLookup_catalogId.value;
				window.parent.frm.RequisitionLine_itemNumber.value = frm.ItemLookup_itemNumber.value;
				if((frm.ItemLookup_source.value == "XML" && frm.ItemLookup_catalogId.value == "HAGEMEYER") || frm.ItemLookup_source.value != "XML"){
					window.parent.frm.RequisitionLine_commodityCode.value = frm.ItemLookup_commodity.value;
				}
				window.parent.frm.RequisitionLine_description.value = frm.ItemLookup_description.value;
				window.parent.frm.RequisitionLine_itemLocation.value = frm.ItemLookup_location.value;
				window.parent.frm.RequisitionLine_itemSource.value = frm.ItemLookup_source.value;
				window.parent.frm.RequisitionLine_receiptRequired.value = frm.ItemLookup_receiptRequired.value;
				if(req_type == "S" && frm.ItemLookup_source.value == "INV" && frm.ItemLookup_actionCode.value != "B"){
					window.parent.frm.RequisitionLine_umCode.value = frm.ItemLookup_unitOfIssue.value;
					window.parent.frm.RequisitionLine_umFactor.value = frm.ItemLookup_unitOfIssueFactor.value;
				<%if (propertiesManager.getProperty("MISC","UseAverage","N").equals("Y")) {%>
					window.parent.frm.RequisitionLine_unitPrice.value = frm.ItemLookup_avgCost.value;
				<%} else {%>
					window.parent.frm.RequisitionLine_unitPrice.value = frm.ItemLookup_issueCost.value;
				<%}%>
				} else {
					window.parent.frm.RequisitionLine_umCode.value = frm.ItemLookup_unitOfOrder.value;
					window.parent.frm.RequisitionLine_umFactor.value = frm.ItemLookup_umFactor.value;
					window.parent.frm.RequisitionLine_unitPrice.value = frm.ItemLookup_orderCost.value;
				}

				//***window.parent.frm.RequisitionLine_duomUmCode.value = frm.ItemLookup_duomUmCode.value;
				window.parent.frm.RequisitionLine_udf1Code.value = frm.ItemLookup_udf01.value;
				window.parent.frm.RequisitionLine_udf2Code.value = frm.ItemLookup_udf02.value;
				window.parent.frm.RequisitionLine_udf3Code.value = frm.ItemLookup_udf03.value;
				window.parent.frm.RequisitionLine_udf4Code.value = frm.ItemLookup_udf04.value;
				window.parent.frm.RequisitionLine_udf5Code.value = frm.ItemLookup_udf05.value;
				window.parent.frm.RequisitionLine_udf6Code.value = frm.ItemLookup_udf06.value;
				window.parent.frm.RequisitionLine_udf7Code.value = frm.ItemLookup_udf07.value;
				window.parent.frm.RequisitionLine_udf8Code.value = frm.ItemLookup_udf08.value;
				window.parent.frm.RequisitionLine_udf9Code.value = frm.ItemLookup_udf09.value;
				window.parent.frm.RequisitionLine_udf10Code.value = frm.ItemLookup_udf10.value;

				window.parent.frm.RequisitionLine_mfgName.value = frm.ItemLookup_mfgName.value;
				window.parent.frm.RequisitionLine_taxable.value = frm.ItemLookup_taxable.value;
				window.parent.frm.RequisitionLine_modelNumber.value = frm.ItemLookup_model.value;
				//***window.parent.frm.RequisitionLine_dateEntered.value = '';
				//***window.parent.frm.RequisitionLine_blanketOrder.value = frm.ItemLookup_blanketOrder.value;
				window.parent.frm.RequisitionLine_shelfLifeRqd.value = frm.ItemLookup_shelfLifeRqd.value;
				//***window.parent.frm.RequisitionLine_memoLine.value = frm.ItemLookup_memoLine.value;
				if(frm.ItemLookup_vendorId.value != null && frm.ItemLookup_catalogId.value.indexOf('SRNS') != 0){
					window.parent.frm.RequisitionLine_vendorId.value = frm.ItemLookup_vendorId.value;
				}
				if(frm.ItemLookup_catalogId.value.indexOf('SRNS') == 0) {
					window.parent.frm.RequisitionLine_vendorId.value = '';
					window.parent.frm.RequisitionLine_vendorName.value = '';
				}
				if(frm.ItemLookup_vendorName.value != null && frm.ItemLookup_catalogId.value.indexOf('SRNS') != 0){
					window.parent.frm.RequisitionLine_vendorName.value = frm.ItemLookup_vendorName.value;
				}
				if(frm.ItemLookup_blanketOrder.value != null){
					window.parent.frm.RequisitionLine_blanketOrder.value = frm.ItemLookup_blanketOrder.value;
				}
				//***window.parent.frm.RequisitionLine_lineTotal.value = "0";
				window.parent.frm.RequisitionLine_quantity.value = frm.ItemLookup_quantity.value;
				window.parent.frm.RequisitionLine_asset.value = frm.ItemLookup_asset.value;
				if(frm.ItemLookup_catalogId.value == "CHEMICAL"){
					window.parent.frm.RequisitionLine_chemicalItemNumber.value = frm.ItemLookup_itemNumber.value;
				}

				if (frm.ItemLookup_source.value ==  'CAT' && frm.ItemLookup_catalogId.value.indexOf('SRNS') == 0)
				{

					if(window.parent.frm.RequisitionLine_itemNumber) allowInputEdit(window.parent.frm.RequisitionLine_itemNumber, false);
					if(window.parent.frm.RequisitionLine_description) allowInputEdit(window.parent.frm.RequisitionLine_description, false);
					if(window.parent.frm.RequisitionLine_umCode) allowInputEdit(window.parent.frm.RequisitionLine_umCode, false);
					if(window.parent.frm.RequisitionLine_unitPrice) allowInputEdit(window.parent.frm.RequisitionLine_unitPrice, false);
					if(window.parent.frm.RequisitionLine_commodityCode) allowInputEdit(window.parent.frm.RequisitionLine_commodityCode, true);
					if(window.parent.frm.RequisitionLine_vendorId) allowInputEdit(window.parent.frm.RequisitionLine_vendorId, false);
					if(window.parent.frm.RequisitionLine_modelNumber) allowInputEdit(window.parent.frm.RequisitionLine_modelNumber, false);
					if(window.parent.frm.RequisitionLine_mfgName) allowInputEdit(window.parent.frm.RequisitionLine_mfgName, false);
					if(window.parent.frm.RequisitionLine_asset) allowInputEdit(window.parent.frm.RequisitionLine_asset, false);
					if(window.parent.frm.RequisitionLine_udf1Code) allowInputEdit(window.parent.frm.RequisitionLine_udf1Code, false);
					if(window.parent.frm.RequisitionLine_udf2Code) allowInputEdit(window.parent.frm.RequisitionLine_udf2Code, false);
					if(window.parent.frm.RequisitionLine_udf3Code) allowInputEdit(window.parent.frm.RequisitionLine_udf3Code, true);
					if(window.parent.frm.RequisitionLine_udf4Code) allowInputEdit(window.parent.frm.RequisitionLine_udf4Code, false);
					if(window.parent.frm.RequisitionLine_udf5Code) allowInputEdit(window.parent.frm.RequisitionLine_udf5Code, false);
					if(window.parent.frm.RequisitionLine_udf6Code) allowInputEdit(window.parent.frm.RequisitionLine_udf6Code, false);
					if(window.parent.frm.RequisitionLine_udf7Code) allowInputEdit(window.parent.frm.RequisitionLine_udf7Code, false);
					if(window.parent.frm.RequisitionLine_udf8Code) allowInputEdit(window.parent.frm.RequisitionLine_udf8Code, false);
					if(window.parent.frm.RequisitionLine_udf9Code) allowInputEdit(window.parent.frm.RequisitionLine_udf9Code, false);

					if(window.parent.frm.checkshelfLifeRqd) allowInputEdit(window.parent.frm.checkshelfLifeRqd, false);
					if(window.parent.frm.checkasset) allowInputEdit(window.parent.frm.checkasset, false);

					parent.$("#resetItem").show();
				}

			} else {

				window.parent.frm.RequisitionLine_catalogId.value = frm.ItemLookup_catalogId[rowSelect].value;
				window.parent.frm.RequisitionLine_itemNumber.value = frm.ItemLookup_itemNumber[rowSelect].value;
				if((frm.ItemLookup_source[rowSelect].value == "XML" && frm.ItemLookup_catalogId[rowSelect].value == "HAGEMEYER") || frm.ItemLookup_source[rowSelect].value != "XML"){
					window.parent.frm.RequisitionLine_commodityCode.value = frm.ItemLookup_commodity[rowSelect].value;
				}
				window.parent.frm.RequisitionLine_description.value = frm.ItemLookup_description[rowSelect].value;
				window.parent.frm.RequisitionLine_itemLocation.value = frm.ItemLookup_location[rowSelect].value;
				window.parent.frm.RequisitionLine_itemSource.value = frm.ItemLookup_source[rowSelect].value;
				window.parent.frm.RequisitionLine_receiptRequired.value = frm.ItemLookup_receiptRequired[rowSelect].value;
				if(req_type == "S" && frm.ItemLookup_source[rowSelect].value == "INV" && frm.ItemLookup_actionCode[rowSelect].value != "B"){
					window.parent.frm.RequisitionLine_umCode.value = frm.ItemLookup_unitOfIssue[rowSelect].value;
					window.parent.frm.RequisitionLine_umFactor.value = frm.ItemLookup_unitOfIssueFactor[rowSelect].value;
				<%if (propertiesManager.getProperty("MISC","UseAverage","N").equals("Y")) {%>
					window.parent.frm.RequisitionLine_unitPrice.value = frm.ItemLookup_avgCost[rowSelect].value;
				<%} else {%>
					window.parent.frm.RequisitionLine_unitPrice.value = frm.ItemLookup_issueCost[rowSelect].value;
				<%}%>
				} else {
					window.parent.frm.RequisitionLine_umCode.value = frm.ItemLookup_unitOfOrder[rowSelect].value;
					window.parent.frm.RequisitionLine_umFactor.value = frm.ItemLookup_umFactor[rowSelect].value;
					window.parent.frm.RequisitionLine_unitPrice.value = frm.ItemLookup_orderCost[rowSelect].value;
				}

				//***window.parent.frm.RequisitionLine_duomUmCode.value = frm.ItemLookup_duomUmCode[rowSelect].value;
				window.parent.frm.RequisitionLine_udf1Code.value = frm.ItemLookup_udf01[rowSelect].value;
				window.parent.frm.RequisitionLine_udf2Code.value = frm.ItemLookup_udf02[rowSelect].value;
				window.parent.frm.RequisitionLine_udf3Code.value = frm.ItemLookup_udf03[rowSelect].value;
				window.parent.frm.RequisitionLine_udf4Code.value = frm.ItemLookup_udf04[rowSelect].value;
				window.parent.frm.RequisitionLine_udf5Code.value = frm.ItemLookup_udf05[rowSelect].value;
				window.parent.frm.RequisitionLine_udf6Code.value = frm.ItemLookup_udf06[rowSelect].value;
				window.parent.frm.RequisitionLine_udf7Code.value = frm.ItemLookup_udf07[rowSelect].value;
				window.parent.frm.RequisitionLine_udf8Code.value = frm.ItemLookup_udf08[rowSelect].value;
				window.parent.frm.RequisitionLine_udf9Code.value = frm.ItemLookup_udf09[rowSelect].value;
				window.parent.frm.RequisitionLine_udf10Code.value = frm.ItemLookup_udf10[rowSelect].value;

				window.parent.frm.RequisitionLine_mfgName.value = frm.ItemLookup_mfgName[rowSelect].value;
				window.parent.frm.RequisitionLine_taxable.value = frm.ItemLookup_taxable[rowSelect].value;
				window.parent.frm.RequisitionLine_modelNumber.value = frm.ItemLookup_model[rowSelect].value;
				//***window.parent.frm.RequisitionLine_dateEntered.value = '';
				//***window.parent.frm.RequisitionLine_blanketOrder.value = frm.ItemLookup_blanketOrder[rowSelect].value;
				window.parent.frm.RequisitionLine_shelfLifeRqd.value = frm.ItemLookup_shelfLifeRqd[rowSelect].value;
				//***window.parent.frm.RequisitionLine_memoLine.value = frm.ItemLookup_memoLine[rowSelect].value;
				if(frm.ItemLookup_vendorId[rowSelect].value != null && frm.ItemLookup_catalogId[rowSelect].value.indexOf('SRNS') != 0){
					window.parent.frm.RequisitionLine_vendorId.value = frm.ItemLookup_vendorId[rowSelect].value;
				}
				if(frm.ItemLookup_vendorName[rowSelect].value != null && frm.ItemLookup_catalogId[rowSelect].value.indexOf('SRNS') != 0){
					window.parent.frm.RequisitionLine_vendorName.value = frm.ItemLookup_vendorName[rowSelect].value;
				}
				if(frm.ItemLookup_catalogId[rowSelect].value.indexOf('SRNS') == 0) {
					window.parent.frm.RequisitionLine_vendorId.value = '';
					window.parent.frm.RequisitionLine_vendorName.value = '';
				}
				if(frm.ItemLookup_blanketOrder[rowSelect].value != null){
					window.parent.frm.RequisitionLine_blanketOrder.value = frm.ItemLookup_blanketOrder[rowSelect].value;
				}
				//***window.parent.frm.RequisitionLine_lineTotal.value = "0";
				window.parent.frm.RequisitionLine_quantity.value = frm.ItemLookup_quantity[rowSelect].value;
				window.parent.frm.RequisitionLine_asset.value = frm.ItemLookup_asset[rowSelect].value;
				if(frm.ItemLookup_catalogId[rowSelect].value == "CHEMICAL"){
					window.parent.frm.RequisitionLine_chemicalItemNumber.value = frm.ItemLookup_itemNumber[rowSelect].value;
				}

				if (frm.ItemLookup_source[rowSelect].value ==  'CAT'  && frm.ItemLookup_catalogId[rowSelect].value.indexOf('SRNS') == 0)
				{

					if(window.parent.frm.RequisitionLine_itemNumber) allowInputEdit(window.parent.frm.RequisitionLine_itemNumber, false);
					if(window.parent.frm.RequisitionLine_description) allowInputEdit(window.parent.frm.RequisitionLine_description, false);
					if(window.parent.frm.RequisitionLine_umCode) allowInputEdit(window.parent.frm.RequisitionLine_umCode, false);
					if(window.parent.frm.RequisitionLine_unitPrice) allowInputEdit(window.parent.frm.RequisitionLine_unitPrice, false);
					if(window.parent.frm.RequisitionLine_commodityCode) allowInputEdit(window.parent.frm.RequisitionLine_commodityCode, true);
					if(window.parent.frm.RequisitionLine_vendorId) allowInputEdit(window.parent.frm.RequisitionLine_vendorId, false);
					if(window.parent.frm.RequisitionLine_modelNumber) allowInputEdit(window.parent.frm.RequisitionLine_modelNumber, false);
					if(window.parent.frm.RequisitionLine_mfgName) allowInputEdit(window.parent.frm.RequisitionLine_mfgName, false);
					if(window.parent.frm.RequisitionLine_asset) allowInputEdit(window.parent.frm.RequisitionLine_asset, false);
					if(window.parent.frm.RequisitionLine_udf1Code) allowInputEdit(window.parent.frm.RequisitionLine_udf1Code, false);
					if(window.parent.frm.RequisitionLine_udf2Code) allowInputEdit(window.parent.frm.RequisitionLine_udf2Code, false);
					if(window.parent.frm.RequisitionLine_udf3Code) allowInputEdit(window.parent.frm.RequisitionLine_udf3Code, true);
					if(window.parent.frm.RequisitionLine_udf4Code) allowInputEdit(window.parent.frm.RequisitionLine_udf4Code, false);
					if(window.parent.frm.RequisitionLine_udf5Code) allowInputEdit(window.parent.frm.RequisitionLine_udf5Code, false);
					if(window.parent.frm.RequisitionLine_udf6Code) allowInputEdit(window.parent.frm.RequisitionLine_udf6Code, false);
					if(window.parent.frm.RequisitionLine_udf7Code) allowInputEdit(window.parent.frm.RequisitionLine_udf7Code, false);
					if(window.parent.frm.RequisitionLine_udf8Code) allowInputEdit(window.parent.frm.RequisitionLine_udf8Code, false);
					if(window.parent.frm.RequisitionLine_udf9Code) allowInputEdit(window.parent.frm.RequisitionLine_udf9Code, false);

					if(window.parent.frm.checkshelfLifeRqd) allowInputEdit(window.parent.frm.checkshelfLifeRqd, false);
					if(window.parent.frm.checkasset) allowInputEdit(window.parent.frm.checkasset, false);

					parent.$("#resetItem").show();
				}
			}


			window.top.hidePopWin();
			return;
		<% } %>

		if (fld.indexOf("ApprovalLog_") == 0)
		{
			window.parent.frm.addUser.value = x;
			window.parent.doSubmit('/requests/req_routinglist_no_popup.jsp', 'ApprovalLogAdd', 'width=505px', 'height=450px');
			doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');
//			window.top.hidePopWin();
		}
		else if (fld.indexOf("_supplierCommodity") > 0)
		{
			frm.supplierCommodity.value = x;
			frm.formField.value = "<%=encoder.encodeForJavaScript(fromPage)%>";
			doSubmit('/browse/supplier_filter_options.jsp', 'DoNothing');
			return;
		}
		else if (fld.indexOf("_supplierClass") > 0)
		{
			frm.supplierClass.value = x;
			frm.formField.value = "<%=encoder.encodeForJavaScript(fromPage)%>";
			doSubmit('/browse/supplier_filter_options.jsp', 'DoNothing');
			return;
		}
		else if (fld.indexOf("InvFormPart_") >= 0 || fld.indexOf("DepartmentBuyer_") >= 0 || fld.indexOf("AppPooluser_") >= 0 || fld == "InvoiceLine_umCode" || fld.indexOf("AppRule_userId") >= 0)
		{
			var Ocolumns = window.parent.frm.elements.item(fld);
			if (Ocolumns.length==undefined)
			{
				window.parent.frm.elements(fld).value = x;
			}
			else
			{
				window.parent.frm.elements(fld)[row].value = x;
			}

			if (fld.indexOf("DepartmentBuyer_") >= 0 || fld.indexOf("AppPooluser_") >= 0 || fld == "InvoiceLine_umCode" || fld.indexOf("AppRule_userId") >= 0 )
			{
				returnOthers(fld);
			}
		}
		else if ((fld.indexOf("ShipTo_shipToCode") >= 0) || (fld.indexOf("BillTo_billToCode") >= 0) || (fld.indexOf("RequisitionLine_taxCode") >= 0) || (fld.indexOf("PoLine_taxCode") >= 0))
		{
			returnOthers(fld);
		}

		else if(fld.indexOf("RequisitionHeader_udf1Code") >= 0 && frm.organizationId.value == "BSC04P")
		{
			window.parent.frm.elements[fld].value = x;
			window.parent.displayArea("projectDetails");
		}
		else if( (fld.indexOf("RequisitionHeader_udf1Code") >= 0 || fld.indexOf("RequisitionHeader_udf3Code") >= 0)
				&& window.parent.frm.elements['udf_field_depencies'] && window.parent.frm.elements['udf_field_depencies'].value == "Y")
		{
			window.parent.frm.elements[fld].value = x;
			window.parent.configureFieldsUDF();
		}
		else if(fld.indexOf("RequisitionHeader_udf10Code") >= 0 && frm.organizationId.value == "BSC04P")
		{
			window.parent.frm.elements[fld].value = x;
			if (x == "YES")
			{
				alert("If yes, attach justification document in Step 7 \"Attachments\".");
			}
		}
		else if ((fld.indexOf("PoSecurity_accessId") >= 0) || fld == "Catalog_vendorId" || fld.indexOf("RequisitionHeader_buyerRemarks") >= 0)
		{
			returnOthers(fld);
		}
		else if(fld.indexOf("FdcWo_") >= 0)
		{
			var columns = frm.elements.item("WoElements_custNumber");
			if (columns.length==undefined)
			{
				if (fld.indexOf("RequisitionHeader") > 0) {
					window.parent.frm.RequisitionHeader_udf8Code.value = frm.WoElements_segNumber.value;
					window.parent.frm.RequisitionHeader_udf9Code.value = frm.WoElements_opNumber.value;
					window.parent.frm.RequisitionHeader_udf11Code.value = frm.WoElements_custNumber.value;
					window.parent.frm.RequisitionHeader_udf1Code.value = frm.WoElements_tranType.value;

					if(window.parent.frm.elements['udf_field_depencies'] && window.parent.frm.elements['udf_field_depencies'].value == "Y")
					{
						window.parent.configureFieldsUDF();
					}

				} else if (fld.indexOf("RequisitionLine") > 0) {
					window.parent.frm.RequisitionLine_udf2Code.value = frm.WoElements_segNumber.value;
					window.parent.frm.RequisitionLine_udf3Code.value = frm.WoElements_opNumber.value;
					window.parent.frm.RequisitionLine_udf4Code.value = frm.WoElements_custNumber.value;
				} else if (fld.indexOf("PoHeader") > 0) {
					window.parent.frm.PoHeader_udf8Code.value = frm.WoElements_segNumber.value;
					window.parent.frm.PoHeader_udf9Code.value = frm.WoElements_opNumber.value;
					window.parent.frm.PoHeader_udf11Code.value = frm.WoElements_custNumber.value;
					window.parent.frm.PoHeader_udf1Code.value = frm.WoElements_tranType.value;

					if(window.parent.frm.elements['udf_field_depencies'] && window.parent.frm.elements['udf_field_depencies'].value == "Y")
					{
						window.parent.configureFieldsUDF();
					}

				} else if (fld.indexOf("PoLine") > 0) {
					window.parent.frm.PoLine_udf2Code.value = frm.WoElements_segNumber.value;
					window.parent.frm.PoLine_udf3Code.value = frm.WoElements_opNumber.value;
					window.parent.frm.PoLine_udf4Code.value = frm.WoElements_custNumber.value;
				}
			} else 	{
				if (fld.indexOf("RequisitionHeader") > 0) {
					window.parent.frm.RequisitionHeader_udf8Code.value = frm.WoElements_segNumber[rowSelect].value;
					window.parent.frm.RequisitionHeader_udf9Code.value = frm.WoElements_opNumber[rowSelect].value;
					window.parent.frm.RequisitionHeader_udf11Code.value = frm.WoElements_custNumber[rowSelect].value;
					window.parent.frm.RequisitionHeader_udf1Code.value = frm.WoElements_tranType[rowSelect].value;

					if(window.parent.frm.elements['udf_field_depencies'] && window.parent.frm.elements['udf_field_depencies'].value == "Y")
					{
						window.parent.configureFieldsUDF();
					}

				} else if (fld.indexOf("RequisitionLine") > 0) {
					window.parent.frm.RequisitionLine_udf2Code.value = frm.WoElements_segNumber[rowSelect].value;
					window.parent.frm.RequisitionLine_udf2Code.value = frm.WoElements_segNumber[rowSelect].value;
					window.parent.frm.RequisitionLine_udf3Code.value = frm.WoElements_opNumber[rowSelect].value;
					window.parent.frm.RequisitionLine_udf4Code.value = frm.WoElements_custNumber[rowSelect].value;
				} else if (fld.indexOf("PoHeader") > 0) {
					window.parent.frm.PoHeader_udf8Code.value = frm.WoElements_segNumber[rowSelect].value;
					window.parent.frm.PoHeader_udf9Code.value = frm.WoElements_opNumber[rowSelect].value;
					window.parent.frm.PoHeader_udf11Code.value = frm.WoElements_custNumber[rowSelect].value;
					window.parent.frm.PoHeader_udf1Code.value = frm.WoElements_tranType[rowSelect].value;

					if(window.parent.frm.elements['udf_field_depencies'] && window.parent.frm.elements['udf_field_depencies'].value == "Y")
					{
						window.parent.configureFieldsUDF();
					}

				} else if (fld.indexOf("PoLine") > 0) {
					window.parent.frm.PoLine_udf2Code.value = frm.WoElements_segNumber[rowSelect].value;
					window.parent.frm.PoLine_udf3Code.value = frm.WoElements_opNumber[rowSelect].value;
					window.parent.frm.PoLine_udf4Code.value = frm.WoElements_custNumber[rowSelect].value;
				}
			}
		}
		else if(fld.indexOf("Pdf_PoHeader_vendorId") >= 0)
		{
			window.parent.frm.elements(fld).value = selectedData;
			var columns = frm.elements.item("Contact_id_contactCode");
			if (columns.length==undefined)
			{
				window.parent.frm.PoHeader_vendContactCode.value = frm.Contact_id_contactCode.value;
				window.parent.frm.PoHeader_vendorName.value = frm.Vendor_vendorName.value;
			}
			else
			{
				window.parent.frm.PoHeader_vendContactCode.value = frm.Contact_id_contactCode[rowSelect].value;
				window.parent.frm.PoHeader_vendorName.value = frm.Vendor_vendorName[rowSelect].value;
			}
		}
		else if(fld.indexOf("RfqHeader_requisitionNumber") >= 0)
		{
			var columns = frm.elements.item("RequisitionHeader_requisitionNumber");
			if (columns.length==undefined)
			{
				window.parent.frm.RfqHeader_requisitionNumber.value = frm.RequisitionHeader_requisitionNumber.value;
			}
			else
			{
				window.parent.frm.RfqHeader_requisitionNumber.value = frm.RequisitionHeader_requisitionNumber[rowSelect].value;
			}
		}

		else if (fld.indexOf("poApprovers") >= 0 || fld.indexOf("invoiceApprovers") >= 0 || fld.indexOf("rfqApprovers") >= 0 || fld.indexOf("invApprovers") >= 0)
		{
			if (frm.UserProfile_displayName.length == undefined) {
				window.parent.addApprover(x, frm.UserProfile_displayName.value);
			} else {
				window.parent.addApprover(x, frm.UserProfile_displayName[rowSelect].value);
			}
		}
		else if (fld == "Account_fld2" && (frm.organizationId.value == "QRI06P" || frm.organizationId.value == "VSE06P"))
		{
			if (window.parent.maxRows == 1) {
				window.parent.frm.elements[fld].value = selectedData;
			} else {
				window.parent.frm.elements(fld)[row].value = selectedData;
			}
			window.parent.getQxrefInfo(row);
			//return;
		}
		else if (fld.indexOf("_requisitioner") > 0)
		{
			var Xcolumns = window.parent.frm.elements.item(fld);
			if (Xcolumns.length != undefined && Xcolumns.length > 0 && window.parent.currentRow != undefined && window.parent.currentRow > -1) {
				window.parent.frm.elements(fld)[window.parent.currentRow].value = selectedData;
			} else {
				window.parent.frm.elements[fld].value = selectedData;
			}
			returnOthers(fld);
		}
		else if (fld.indexOf("InspectionDiscrep_inspectCode") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(fld);
			if (Xcolumns.length != undefined && Xcolumns.length > 0 && window.parent.currentRow != undefined && window.parent.currentRow > -1) {
				window.parent.frm.elements(fld)[window.parent.currentRow].value = selectedData;
			} else {
				window.parent.frm.elements[fld].value = selectedData;
			}
			returnOthers(fld);
		}
		else if (fld.indexOf("InspectionLine_inspectCode") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(fld);
			if (Xcolumns.length != undefined && Xcolumns.length > 0 && window.parent.currentRow != undefined && window.parent.currentRow > -1) {
				window.parent.frm.elements(fld)[window.parent.currentRow].value = selectedData;
			} else {
				window.parent.frm.elements[fld].value = selectedData;
			}
			returnOthers(fld);
		}
		else if (fld.indexOf("InspectionDiscrep_inspectCode") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(fld);
			if (Xcolumns.length != undefined && Xcolumns.length > 0 && window.parent.currentRow != undefined && window.parent.currentRow > -1) {
				window.parent.frm.elements(fld)[window.parent.currentRow].value = selectedData;
			} else {
				window.parent.frm.elements[fld].value = selectedData;
			}
			returnOthers(fld);
		}
		else if (fld.indexOf("InspectionMte_mteNo") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(fld);
			if (Xcolumns.length != undefined && Xcolumns.length > 0 && window.parent.currentRow != undefined && window.parent.currentRow > -1) {
				window.parent.frm.elements(fld)[window.parent.currentRow].value = selectedData;
			} else {
				window.parent.frm.elements[fld].value = selectedData;
			}
			returnOthers(fld);
		}
		else if (fld.indexOf("InspectionLine_critNo") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(fld);
			if (Xcolumns.length != undefined && Xcolumns.length > 0 && window.parent.currentRow != undefined && window.parent.currentRow > -1) {
				window.parent.frm.elements(fld)[window.parent.currentRow].value = selectedData;
			} else {
				window.parent.frm.elements[fld].value = selectedData;
			}
			returnOthers(fld);
		}
		else if (fld.indexOf("InspectionStd_critNo") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(fld);
			if (Xcolumns.length != undefined && Xcolumns.length > 0 && window.parent.currentRow != undefined && window.parent.currentRow > -1) {
				window.parent.frm.elements(fld)[window.parent.currentRow].value = selectedData;
			} else {
				window.parent.frm.elements[fld].value = selectedData;
			}
			returnOthers(fld);
		}
		else if (fld.indexOf("remoteInspId") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(fld);
			if (Xcolumns.length != undefined && Xcolumns.length > 0 && window.parent.currentRow != undefined && window.parent.currentRow > -1) {
				window.parent.frm.elements(fld)[window.parent.currentRow].value = selectedData;
			} else {
				window.parent.frm.elements[fld].value = selectedData;
			}
			returnOthers(fld);
		}
<%	if (browseObject.getBrowseName().equalsIgnoreCase("itemcrossref")) {%>
		else if (fld.indexOf("PoLine_itemNumber") >= 0) {
			var originalItemNumber = window.parent.frm.PoLine_itemNumber[row].value;

			window.parent.frm.PoLine_itemNumber[row].value = x;
			window.parent.frm.PoLine_altItemNumber[row].value = frm.ItemCrossRef_itemNumber[rowSelect].value;

			if (originalItemNumber != x) {
				var icPoHeader = window.parent.frm.PoHeader_icPoHeader.value;
				var icPoLine = window.parent.frm.PoLine_icPoLine[row].value;
				var itemNumber = frm.ItemCrossRef_itemNumber[rowSelect].value;
				var shipToInv = window.parent.frm.ReceiptHeader_shipToInv.value;
				var hiddenFields = "<input type=hidden name=\"PoHeader_icPoHeader\" value=\"" + icPoHeader + "\">" +
												  "<input type=hidden name=\"PoLine_icPoHeader\" value=\"" + icPoHeader + "\">" +
												  "<input type=hidden name=\"PoLine_icPoLine\" value=\"" + icPoLine + "\">" +
												  "<input type=hidden name=\"PoLine_itemNumber\" value=\"" + itemNumber + "\">" +
												  "<input type=hidden name=\"currentRow\" value=\"" + row + "\">" +
												  "<input type=hidden name=\"shipToInv\" value=\"" + shipToInv + "\">";
				setHiddenFields(hiddenFields);

				doSubmit('/receipts/rec_get_poline_info.jsp', 'BrowseCleanupByBrowseId;ReceiptPoLineItemLookup', 'WIDTH=1', 'HEIGHT=1');
				return;
			}
		}
<%	}%>
		else
		{
			var Ocolumns = window.parent.document.getElementsByName(fld);
			if (Ocolumns.length==undefined) {
				window.parent.frm.elements[fld].value = selectedData;
			} else {
				window.parent.document.getElementsByName(fld)[row].value = x;
			}
				returnOthers(fld);
		}

		doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');
//		window.top.hidePopWin();
	}

	function returnOthers(formField) {
		if ((formField=="PoHeader_shipToCode") && (window.parent.frm.receiptMethod != undefined) ) {
		   if ( window.parent.frm.receiptMethod.value == "ReceiveByOrder" ){
			window.parent.frm.asShipToAddress_addressLine1.value = frm.Address_addressLine1[rowSelect].value;
			window.parent.frm.asShipToAddress_addressLine2.value = frm.Address_addressLine2[rowSelect].value;
			window.parent.frm.asShipToAddress_addressLine3.value = frm.Address_addressLine3[rowSelect].value;
			window.parent.frm.asShipToAddress_addressLine4.value = frm.Address_addressLine4[rowSelect].value;
			window.parent.frm.asShipToAddress_city.value = frm.Address_city[rowSelect].value;
			window.parent.frm.asShipToAddress_state.value = frm.Address_state[rowSelect].value;
			window.parent.frm.asShipToAddress_postalCode.value = frm.Address_postalCode[rowSelect].value;
			window.parent.frm.asShipToAddress_country.value = frm.Address_country[rowSelect].value;
			window.parent.frm.ReceiptHeader_shipToCode.value = frm.Address_id_addressCode[rowSelect].value;
			window.parent.frm.ReceiptHeader_shipToInv.value = frm.Address_inventory[rowSelect].value;
			window.parent.frm.PoHeader_shipToInv.value = frm.Address_inventory[rowSelect].value;
		     }
		}
		else if ((formField=="RequisitionHeader_shipToCode") || (formField=="RfqHeader_shipToCode") || (formField=="PoHeader_shipToCode") || (formField=="InvoiceHeader_shipToCode")) {
			if (frm.Address_addressLine1[rowSelect]) {
				window.parent.frm.Address_addressLine1.value = frm.Address_addressLine1[rowSelect].value;
				window.parent.frm.Address_addressLine2.value = frm.Address_addressLine2[rowSelect].value;
				window.parent.frm.Address_addressLine3.value = frm.Address_addressLine3[rowSelect].value;
				window.parent.frm.Address_addressLine4.value = frm.Address_addressLine4[rowSelect].value;
				window.parent.frm.Address_city.value = frm.Address_city[rowSelect].value;
				window.parent.frm.Address_state.value = frm.Address_state[rowSelect].value;
				window.parent.frm.Address_postalCode.value = frm.Address_postalCode[rowSelect].value;
				window.parent.frm.Address_country.value = frm.Address_country[rowSelect].value;

				if (formField == "PoHeader_shipToCode") {
				    window.parent.frm.PoHeader_shipToInv.value = frm.Address_inventory[rowSelect].value;
			    }

			} else {
				window.parent.frm.Address_addressLine1.value = frm.Address_addressLine1.value;
				window.parent.frm.Address_addressLine2.value = frm.Address_addressLine2.value;
				window.parent.frm.Address_addressLine3.value = frm.Address_addressLine3.value;
				window.parent.frm.Address_addressLine4.value = frm.Address_addressLine4.value;
				window.parent.frm.Address_city.value = frm.Address_city.value;
				window.parent.frm.Address_state.value = frm.Address_state.value;
				window.parent.frm.Address_postalCode.value = frm.Address_postalCode.value;
				window.parent.frm.Address_country.value = frm.Address_country.value;

				if (formField == "PoHeader_shipToCode") {
				    window.parent.frm.PoHeader_shipToInv.value = frm.Address_inventory.value;
			    }
			}

			if (window.parent.frm.updateAddress) {
				window.parent.frm.updateAddress.value = "N";
			}
		}
		else if ((formField=="RequisitionHeader_billToCode") || (formField=="RfqHeader_billToCode") || (formField=="PoHeader_billToCode")){
			window.parent.frm.Address_addressLine1.value = frm.Address_addressLine1[rowSelect].value;
			window.parent.frm.Address_addressLine2.value = frm.Address_addressLine2[rowSelect].value;
			window.parent.frm.Address_addressLine3.value = frm.Address_addressLine3[rowSelect].value;
			window.parent.frm.Address_addressLine4.value = frm.Address_addressLine4[rowSelect].value;
			window.parent.frm.Address_city.value = frm.Address_city[rowSelect].value;
			window.parent.frm.Address_state.value = frm.Address_state[rowSelect].value;
			window.parent.frm.Address_postalCode.value = frm.Address_postalCode[rowSelect].value;
			window.parent.frm.Address_country.value = frm.Address_country[rowSelect].value;
		}
		else if(formField=="RequisitionHeader_vendContactCode" || formField=="PoHeader_vendContactCode"){
			var columns = frm.elements.item("Contact_id_contactCode");
			if (columns.length==undefined)
			{
				var fullName = frm.Contact_firstName.value + " ";
				if (!(frm.Contact_middleInit.value==null)){
					fullName = fullName + frm.Contact_middleInit.value + " ";
				}
				fullName = fullName + frm.Contact_lastName.value;
				if (formField.indexOf("RequisitionHeader_") >= 0)
				{
					window.parent.frm.RequisitionHeader_vendorAttn.value = fullName;
					window.parent.frm.RequisitionHeader_contactAddr.value = frm.Contact_addressCode.value;
					window.parent.frm.RequisitionHeader_vendorId.value = frm.Contact_id_vendorId.value;

					if (window.parent.frm.RequisitionHeader_contactPhone && frm.Contact_phoneNumber)
						window.parent.frm.RequisitionHeader_contactPhone.value = frm.Contact_phoneNumber.value;

					if (window.parent.frm.RequisitionHeader_contactMobilePhone && frm.Contact_mobileNumber)
						window.parent.frm.RequisitionHeader_contactMobilePhone.value = frm.Contact_mobileNumber.value;

					if (window.parent.frm.Address_addrFld10 && frm.Contact_faxNumber)
						window.parent.frm.Address_addrFld10.value = frm.Contact_faxNumber.value;

					if (window.parent.frm.Address_addrFld11 && frm.Contact_emailAddr)
						window.parent.frm.Address_addrFld11.value = frm.Contact_emailAddr.value;

					if (window.parent.frm.Address_addrFld12 && frm.Contact_info1)
						window.parent.frm.Address_addrFld12.value = frm.Contact_info1.value;

					if (window.parent.frm.Address_addrFld13 && frm.Contact_info2)
						window.parent.frm.Address_addrFld13.value = frm.Contact_info2.value;
				}
				else if (formField.indexOf("PoHeader_") >= 0)
				{
					if (window.parent.frm.PoHeader_contactName)
						window.parent.frm.PoHeader_contactName.value = fullName;

					if (window.parent.frm.PoHeader_contactAddr && frm.Contact_addressCode)
						window.parent.frm.PoHeader_contactAddr.value = frm.Contact_addressCode.value;

					if (window.parent.frm.PoHeader_vendorId && frm.Contact_id_vendorId)
						window.parent.frm.PoHeader_vendorId.value = frm.Contact_id_vendorId.value;

					if (window.parent.frm.PoHeader_email && frm.Contact_emailAddr)
						window.parent.frm.PoHeader_email.value = frm.Contact_emailAddr.value;

					if (window.parent.frm.VendorInsurance_vendorId && frm.Contact_id_vendorId)
						window.parent.frm.VendorInsurance_vendorId.value = frm.Contact_id_vendorId.value;

					if (window.parent.frm.PoHeader_contactPhone && frm.Contact_phoneNumber.value )
						window.parent.frm.PoHeader_contactPhone.value = frm.Contact_phoneNumber.value;

					if (window.parent.frm.PoHeader_contactMobilePhone && frm.Contact_mobileNumber)
						window.parent.frm.PoHeader_contactMobilePhone.value = frm.Contact_mobileNumber.value;

					if (window.parent.frm.Contact_info1 && frm.Contact_info1)
						window.parent.frm.Contact_info1.value = frm.Contact_info1.value;

					if (window.parent.frm.Contact_info2 && frm.Contact_info2)
						window.parent.frm.Contact_info2.value = frm.Contact_info2.value;

					if (window.parent.frm.Contact_faxNumber) {
						window.parent.frm.Contact_faxNumber.value = frm.Contact_faxNumber.value;
					}
				}
				window.parent.frm.Address_addressLine1.value = frm.Address_addressLine1.value;
				window.parent.frm.Address_addressLine2.value = frm.Address_addressLine2.value;
				window.parent.frm.Address_addressLine3.value = frm.Address_addressLine3.value;
				window.parent.frm.Address_addressLine4.value = frm.Address_addressLine4.value;
				window.parent.frm.Address_city.value = frm.Address_city.value;
				window.parent.frm.Address_state.value = frm.Address_state.value;
				window.parent.frm.Address_postalCode.value = frm.Address_postalCode.value;
				window.parent.frm.Address_country.value = frm.Address_country.value;
			}
			else
			{
				var fullName = frm.Contact_firstName[rowSelect].value + " ";
				if (!(frm.Contact_middleInit.value==null)){
					fullName = fullName + frm.Contact_middleInit[rowSelect].value + " ";
				}
				fullName = fullName + frm.Contact_lastName[rowSelect].value;
				if (formField.indexOf("RequisitionHeader_") >= 0)
				{
					window.parent.frm.RequisitionHeader_vendorAttn.value = fullName;
					window.parent.frm.RequisitionHeader_contactAddr.value = frm.Contact_addressCode[rowSelect].value;
					window.parent.frm.RequisitionHeader_vendorId.value = frm.Contact_id_vendorId[rowSelect].value;

					if (window.parent.frm.RequisitionHeader_contactPhone && frm.Contact_phoneNumber)
					window.parent.frm.RequisitionHeader_contactPhone.value = frm.Contact_phoneNumber[rowSelect].value;

					if (window.parent.frm.RequisitionHeader_contactMobilePhone && frm.Contact_mobileNumber)
					window.parent.frm.RequisitionHeader_contactMobilePhone.value = frm.Contact_mobileNumber[rowSelect].value;

					window.parent.frm.Address_addrFld10.value = frm.Contact_faxNumber[rowSelect].value;
					window.parent.frm.Address_addrFld11.value = frm.Contact_emailAddr[rowSelect].value;
					window.parent.frm.Address_addrFld12.value = frm.Contact_info1[rowSelect].value;
					window.parent.frm.Address_addrFld13.value = frm.Contact_info2[rowSelect].value;
				}
				else if (formField.indexOf("PoHeader_") >= 0)
				{
					window.parent.frm.PoHeader_contactName.value = fullName;
					window.parent.frm.PoHeader_contactAddr.value = frm.Contact_addressCode[rowSelect].value;
					window.parent.frm.PoHeader_vendorId.value = frm.Contact_id_vendorId[rowSelect].value;
					window.parent.frm.PoHeader_email.value = frm.Contact_emailAddr[rowSelect].value;
					window.parent.frm.VendorInsurance_vendorId.value = frm.Contact_id_vendorId[rowSelect].value;

					if (window.parent.frm.PoHeader_contactPhone && frm.Contact_phoneNumber )
					window.parent.frm.PoHeader_contactPhone.value = frm.Contact_phoneNumber[rowSelect].value;

					if (window.parent.frm.PoHeader_contactMobilePhone && frm.Contact_mobileNumber)
					window.parent.frm.PoHeader_contactMobilePhone.value = frm.Contact_mobileNumber[rowSelect].value;

					window.parent.frm.Contact_info1.value = frm.Contact_info1[rowSelect].value;
					window.parent.frm.Contact_info2.value = frm.Contact_info2[rowSelect].value;
					if (window.parent.frm.Contact_faxNumber) {
						window.parent.frm.Contact_faxNumber.value = frm.Contact_faxNumber[rowSelect].value;
					}
				}
				window.parent.frm.Address_addressLine1.value = frm.Address_addressLine1[rowSelect].value;
				window.parent.frm.Address_addressLine2.value = frm.Address_addressLine2[rowSelect].value;
				window.parent.frm.Address_addressLine3.value = frm.Address_addressLine3[rowSelect].value;
				window.parent.frm.Address_addressLine4.value = frm.Address_addressLine4[rowSelect].value;
				window.parent.frm.Address_city.value = frm.Address_city[rowSelect].value;
				window.parent.frm.Address_state.value = frm.Address_state[rowSelect].value;
				window.parent.frm.Address_postalCode.value = frm.Address_postalCode[rowSelect].value;
				window.parent.frm.Address_country.value = frm.Address_country[rowSelect].value;
			}
		}
		else if(formField=="RequisitionHeader_vendorId" || formField=="PoHeader_vendorId")
		{
			var columns = frm.elements.item("Contact_id_vendorId");

			if (columns && columns.length==undefined)
			{
				var fullName = frm.Contact_firstName.value + " ";
				if (!(frm.Contact_middleInit.value==null))
				{
					fullName = fullName + frm.Contact_middleInit.value + " ";
				}
				fullName = fullName + frm.Contact_lastName.value;
				if (formField.indexOf("RequisitionHeader_") >= 0)
				{
					window.parent.frm.RequisitionHeader_vendContactCode.value = frm.Contact_id_contactCode.value;
					window.parent.frm.RequisitionHeader_vendorAttn.value = fullName;
					window.parent.frm.RequisitionHeader_contactAddr.value = frm.Contact_addressCode.value;

					if (window.parent.frm.RequisitionHeader_contactPhone && frm.Contact_phoneNumber ) {
					    window.parent.frm.RequisitionHeader_contactPhone.value = frm.Contact_phoneNumber.value;
					}
					if (window.parent.frm.RequisitionHeader_contactMobilePhone && frm.Contact_mobileNumber) {
					    window.parent.frm.RequisitionHeader_contactMobilePhone.value = frm.Contact_mobileNumber.value;
					}
					<% if(oid.equalsIgnoreCase("BLY07P")) { %>
					window.parent.frm.RequisitionHeader_shippingCode.value = frm.Vendor_shipVia.value;
					window.parent.frm.Vendor_vendTerms.value = frm.Vendor_vendTerms.value;
					window.parent.frm.RequisitionHeader_currencyCode.value = frm.Vendor_currencyCode.value;
					<%} %>

					if (window.parent.frm.Contact_faxNumber) {
						window.parent.frm.Contact_faxNumber.value = frm.Contact_faxNumber.value;
					}

					if (window.parent.frm.Contact_emailAddr) {
						window.parent.frm.Contact_emailAddr.value = frm.Contact_emailAddr.value;
					}

	                if (window.parent.frm.Address_addrFld10) {
	                  window.parent.frm.Address_addrFld10.value = frm.Contact_faxNumber.value;
	                }
	                if (window.parent.frm.Address_addrFld11) {
				        window.parent.frm.Address_addrFld11.value = frm.Contact_emailAddr.value;
	                }
					if (window.parent.frm.Contact_info1) {
						window.parent.frm.Contact_info1.value = frm.Contact_info1.value;
					}

					if (window.parent.frm.Contact_info2) {
						window.parent.frm.Contact_info2.value = frm.Contact_info2.value;
					}
					<% if(oid.equalsIgnoreCase("tdc10p")) { %>
						if (window.parent.frm.RequisitionHeader_billToCode && frm.Vendor_vendUdf3) {
							if(frm.Vendor_vendUdf3.value == ""){
								window.parent.frm.RequisitionHeader_billToCode.value = "<%=propertiesManager.getProperty("REQ DEFAULTS", "BillToCode", "")%>";
							}else{
								window.parent.frm.RequisitionHeader_billToCode.value = frm.Vendor_vendUdf3.value;
							}
						}
					<% } %>
					window.parent.verifySoleSource();
				}
				else if (formField.indexOf("PoHeader_") >= 0)
				{
					window.parent.frm.PoHeader_vendContactCode.value = frm.Contact_id_contactCode.value;
					window.parent.frm.PoHeader_contactName.value = fullName;

					if (window.parent.frm.PoHeader_contactAddr && frm.Contact_addressCode)
						window.parent.frm.PoHeader_contactAddr.value = frm.Contact_addressCode.value;

					if (window.parent.frm.PoHeader_ediOrder_option && frm.Vendor_printFaxCode)
						window.parent.frm.PoHeader_ediOrder_option.value = frm.Vendor_printFaxCode.value;

					if (window.parent.frm.PoHeader_ediOrder && frm.Vendor_printFaxCode)
						window.parent.frm.PoHeader_ediOrder.value = frm.Vendor_printFaxCode.value;

					if (window.parent.frm.PoHeader_inspectionReqd && frm.Vendor_inspectionReqd)
						window.parent.frm.PoHeader_inspectionReqd.value = frm.Vendor_inspectionReqd.value;

					if (window.parent.frm.PoHeader_contactPhone && frm.Contact_phoneNumber)
						window.parent.frm.PoHeader_contactPhone.value = frm.Contact_phoneNumber.value;

					if (window.parent.frm.PoHeader_contactMobilePhone && frm.Contact_mobileNumber)
						window.parent.frm.PoHeader_contactMobilePhone.value = frm.Contact_mobileNumber.value;

					if (window.parent.frm.PoHeader_email && frm.Contact_emailAddr)
						window.parent.frm.PoHeader_email.value = frm.Contact_emailAddr.value;

					if (window.parent.frm.Contact_info1 && frm.Contact_info1)
						window.parent.frm.Contact_info1.value = frm.Contact_info1.value;

					if (window.parent.frm.Contact_info2 && frm.Contact_info2)
						window.parent.frm.Contact_info2.value = frm.Contact_info2.value;

					if (window.parent.frm.Contact_faxNumber && frm.Contact_faxNumber)
						window.parent.frm.Contact_faxNumber.value = frm.Contact_faxNumber.value;

					<% if(oid.equalsIgnoreCase("BLY07P")) { %>
						window.parent.frm.PoHeader_shipViaCode.value = frm.Vendor_shipVia.value;
						window.parent.frm.PoHeader_fobCode.value = frm.Vendor_fobId.value;
						window.parent.frm.PoHeader_termsCode.value = frm.Vendor_vendTerms.value;
						window.parent.frm.PoHeader_currencyCode.value = frm.Vendor_currencyCode.value;
					<%} else {%>
					if (window.parent.frm.PoHeader_shipViaCode) {
						window.parent.frm.PoHeader_shipViaCode.value = frm.Vendor_shipVia.value;
					}
					<%} %>
					if (window.parent.frm.PoHeader_fobCode) {
						window.parent.frm.PoHeader_fobCode.value = frm.Vendor_fobId.value;
					}
					if (window.parent.frm.PoHeader_termsCode) {
						window.parent.frm.PoHeader_termsCode.value = frm.Vendor_vendTerms.value;
					}
					if (window.parent.frm.PoHeader_vendorClass && frm.Vendor_vendorClass)
						window.parent.frm.PoHeader_vendorClass.value = frm.Vendor_vendorClass.value;

					if (window.parent.frm.VendorInsurance_vendorId && frm.Contact_id_vendorId)
						window.parent.frm.VendorInsurance_vendorId.value = frm.Contact_id_vendorId.value;

					if (window.parent.frm.updatePromisedDateFromVendor)
					{
						window.parent.frm.updatePromisedDateFromVendor.value = "Y";
					}
					<% if(oid.equalsIgnoreCase("tdc10p")) { %>
						if (window.parent.frm.PoHeader_billToCode && frm.Vendor_vendUdf3) {
							if(frm.Vendor_vendUdf3.value == ""){
								window.parent.frm.PoHeader_billToCode.value = "<%=propertiesManager.getProperty("PO DEFAULTS", "BillToCode", "")%>";
							}else{
								window.parent.frm.PoHeader_billToCode.value = frm.Vendor_vendUdf3.value;
							}
						}
					<% } %>
				}
				window.parent.frm.Address_addressLine1.value = frm.Address_addressLine1.value;
				window.parent.frm.Address_addressLine2.value = frm.Address_addressLine2.value;
				window.parent.frm.Address_addressLine3.value = frm.Address_addressLine3.value;
				window.parent.frm.Address_addressLine4.value = frm.Address_addressLine4.value;
				window.parent.frm.Address_city.value = frm.Address_city.value;
				window.parent.frm.Address_state.value = frm.Address_state.value;
				window.parent.frm.Address_postalCode.value = frm.Address_postalCode.value;
				window.parent.frm.Address_country.value = frm.Address_country.value;
			}
			else
			{
				var fullName = frm.Contact_firstName[rowSelect].value + " ";
				if (!(frm.Contact_middleInit.value==null))
				{
					fullName = fullName + frm.Contact_middleInit[rowSelect].value + " ";
				}
				fullName = fullName + frm.Contact_lastName[rowSelect].value;
				if (formField.indexOf("RequisitionHeader_") >= 0)
				{
					if (window.parent.frm.RequisitionHeader_vendContactCode && frm.Contact_id_contactCode)
					window.parent.frm.RequisitionHeader_vendContactCode.value = frm.Contact_id_contactCode[rowSelect].value;

					if (window.parent.frm.RequisitionHeader_vendorAttn)
					window.parent.frm.RequisitionHeader_vendorAttn.value = fullName;

					if (window.parent.frm.RequisitionHeader_contactAddr && frm.Contact_addressCode)
					window.parent.frm.RequisitionHeader_contactAddr.value = frm.Contact_addressCode[rowSelect].value;

					if (window.parent.frm.RequisitionHeader_contactPhone && frm.Contact_phoneNumber) {
					    window.parent.frm.RequisitionHeader_contactPhone.value = frm.Contact_phoneNumber[rowSelect].value;
					    }
					if (window.parent.frm.RequisitionHeader_contactMobilePhone && frm.Contact_mobileNumber) {
					    window.parent.frm.RequisitionHeader_contactMobilePhone.value = frm.Contact_mobileNumber[rowSelect].value;
					   }
					<% if(oid.equalsIgnoreCase("BLY07P")) { %>
					window.parent.frm.RequisitionHeader_shippingCode.value = frm.Vendor_shipVia[rowSelect].value;
					window.parent.frm.Vendor_vendTerms.value = frm.Vendor_vendTerms[rowSelect].value;
					window.parent.frm.RequisitionHeader_currencyCode.value = frm.Vendor_currencyCode[rowSelect].value;
					<%} %>

					if (window.parent.frm.Contact_faxNumber && frm.Contact_faxNumber) {
						window.parent.frm.Contact_faxNumber.value = frm.Contact_faxNumber[rowSelect].value;
					}

					if (window.parent.frm.Contact_emailAddr && frm.Contact_emailAddr) {
						window.parent.frm.Contact_emailAddr.value = frm.Contact_emailAddr[rowSelect].value;
					}

	                if (window.parent.frm.Address_addrFld10 && frm.Contact_faxNumber) {
	                  window.parent.frm.Address_addrFld10.value = frm.Contact_faxNumber[rowSelect].value;
	                }
	                if (window.parent.frm.Address_addrFld11 && frm.Contact_emailAddr) {
				        window.parent.frm.Address_addrFld11.value = frm.Contact_emailAddr[rowSelect].value;
	                }
					if (window.parent.frm.Contact_info1 && frm.Contact_info1) {
						window.parent.frm.Contact_info1.value = frm.Contact_info1[rowSelect].value;
					}

					if (window.parent.frm.Contact_info2 && frm.Contact_info2) {
						window.parent.frm.Contact_info2.value = frm.Contact_info2[rowSelect].value;
					}
					<% if(oid.equalsIgnoreCase("tdc10p")) { %>
						if (window.parent.frm.RequisitionHeader_billToCode && frm.Vendor_vendUdf3 && frm.Vendor_vendUdf3[rowSelect]) {
							if(frm.Vendor_vendUdf3[rowSelect].value == ""){
								window.parent.frm.RequisitionHeader_billToCode.value = "<%=propertiesManager.getProperty("REQ DEFAULTS", "BillToCode", "")%>";
							}else{
								window.parent.frm.RequisitionHeader_billToCode.value = frm.Vendor_vendUdf3[rowSelect].value;
							}
						}
					<% } %>
					window.parent.verifySoleSource();
				}
				else if (formField.indexOf("PoHeader_") >= 0)
				{
					if (window.parent.frm.PoHeader_vendContactCode && frm.Contact_id_contactCode)
					window.parent.frm.PoHeader_vendContactCode.value = frm.Contact_id_contactCode[rowSelect].value;

					if (window.parent.frm.PoHeader_contactName)
					window.parent.frm.PoHeader_contactName.value = fullName;

					if (window.parent.frm.PoHeader_contactAddr && frm.Contact_addressCode)
					window.parent.frm.PoHeader_contactAddr.value = frm.Contact_addressCode[rowSelect].value;

					if (window.parent.frm.PoHeader_ediOrder_option && frm.Vendor_printFaxCode)
					window.parent.frm.PoHeader_ediOrder_option.value = frm.Vendor_printFaxCode[rowSelect].value;

					if (window.parent.frm.PoHeader_ediOrder && frm.Vendor_printFaxCode)
					window.parent.frm.PoHeader_ediOrder.value = frm.Vendor_printFaxCode[rowSelect].value;

					if (window.parent.frm.PoHeader_inspectionReqd && frm.Vendor_inspectionReqd)
					window.parent.frm.PoHeader_inspectionReqd.value = frm.Vendor_inspectionReqd[rowSelect].value;

					if ( window.parent.frm.PoHeader_contactPhone && frm.Contact_phoneNumber )
					window.parent.frm.PoHeader_contactPhone.value = frm.Contact_phoneNumber[rowSelect].value;

					if ( window.parent.frm.PoHeader_contactMobilePhone && frm.Contact_phoneNumber )
					window.parent.frm.PoHeader_contactMobilePhone.value = frm.Contact_mobileNumber[rowSelect].value;

					if (window.parent.frm.PoHeader_email && frm.Contact_emailAddr)
					window.parent.frm.PoHeader_email.value = frm.Contact_emailAddr[rowSelect].value;

					if (window.parent.frm.Contact_info1 && frm.Contact_info1)
					window.parent.frm.Contact_info1.value = frm.Contact_info1[rowSelect].value;

					if (window.parent.frm.Contact_info2 && frm.Contact_info2)
					window.parent.frm.Contact_info2.value = frm.Contact_info2[rowSelect].value;

					if (window.parent.frm.Contact_faxNumber && frm.Contact_faxNumber) {
						window.parent.frm.Contact_faxNumber.value = frm.Contact_faxNumber[rowSelect].value;
					}

					if (window.parent.frm.PoHeader_faxNumber && window.parent.document.getElementById("showFaxNumber"))
					{
						window.parent.frm.PoHeader_faxNumber.value = frm.Vendor_faxNumber[rowSelect].value;
						if (window.parent.frm.PoHeader_ediOrder.value == "F")
						{
							window.parent.document.getElementById("showFaxNumber").style.visibility = "visible";
							window.parent.document.getElementById("showFaxNumber").style.display    = "block";
							window.parent.document.getElementById("showFaxNumber").style.height     = 22;
						}
						else
						{
							window.parent.document.getElementById("showFaxNumber").style.visibility = "hidden";
							window.parent.document.getElementById("showFaxNumber").style.display    = "none";
							window.parent.document.getElementById("showFaxNumber").style.height     = 0;
						}
					}

					<% if(oid.equalsIgnoreCase("BLY07P")) { %>
						window.parent.frm.PoHeader_shipViaCode.value = frm.Vendor_shipVia[rowSelect].value;
						window.parent.frm.PoHeader_fobCode.value = frm.Vendor_fobId[rowSelect].value;
						window.parent.frm.PoHeader_termsCode.value = frm.Vendor_vendTerms[rowSelect].value;
						window.parent.frm.PoHeader_currencyCode.value = frm.Vendor_currencyCode[rowSelect].value;
					<%} else {%>
					if (window.parent.frm.PoHeader_shipViaCode && frm.Vendor_shipVia) {
						window.parent.frm.PoHeader_shipViaCode.value = frm.Vendor_shipVia[rowSelect].value;
					}
					<%} %>
					if (window.parent.frm.PoHeader_fobCode && frm.Vendor_fobId) {
						window.parent.frm.PoHeader_fobCode.value = frm.Vendor_fobId[rowSelect].value;
					}
					if (window.parent.frm.PoHeader_termsCode && frm.Vendor_vendTerms) {
						window.parent.frm.PoHeader_termsCode.value = frm.Vendor_vendTerms[rowSelect].value;
					}
					if (window.parent.frm.PoHeader_vendorClass && frm.Vendor_vendorClass)
						window.parent.frm.PoHeader_vendorClass.value = frm.Vendor_vendorClass[rowSelect].value;

					if (window.parent.frm.VendorInsurance_vendorId && frm.Contact_id_vendorId)
						window.parent.frm.VendorInsurance_vendorId.value = frm.Contact_id_vendorId[rowSelect].value;

					if (window.parent.frm.updatePromisedDateFromVendor)
						window.parent.frm.updatePromisedDateFromVendor.value = "Y";


					<% if(oid.equalsIgnoreCase("tdc10p")) { %>
						if (window.parent.frm.PoHeader_billToCode && frm.Vendor_vendUdf3 && frm.Vendor_vendUdf3[rowSelect]) {
							if (frm.Vendor_vendUdf3[rowSelect].value == "") {
								window.parent.frm.PoHeader_billToCode.value = "<%=propertiesManager.getProperty("PO DEFAULTS", "BillToCode", "")%>";
							} else {
								window.parent.frm.PoHeader_billToCode.value = frm.Vendor_vendUdf3[rowSelect].value;
							}
						}
					<% } %>
				}
				window.parent.frm.Address_addressLine1.value = frm.Address_addressLine1[rowSelect].value;
				window.parent.frm.Address_addressLine2.value = frm.Address_addressLine2[rowSelect].value;
				window.parent.frm.Address_addressLine3.value = frm.Address_addressLine3[rowSelect].value;
				window.parent.frm.Address_addressLine4.value = frm.Address_addressLine4[rowSelect].value;
				window.parent.frm.Address_city.value = frm.Address_city[rowSelect].value;
				window.parent.frm.Address_state.value = frm.Address_state[rowSelect].value;
				window.parent.frm.Address_postalCode.value = frm.Address_postalCode[rowSelect].value;
				window.parent.frm.Address_country.value = frm.Address_country[rowSelect].value;
			}

		}
		else if(formField=="InvoiceVendor_vendorId")
		{
			window.parent.frm.InvoiceHeader_vendorId.value = window.parent.frm.InvoiceVendor_vendorId.value;
			var columns = frm.elements.item("ConsolidatedVendor_vendorName");
			if (columns.length==undefined)
			{
				window.parent.frm.InvoiceHeader_vendorName.value = frm.ConsolidatedVendor_vendorName.value;
				window.parent.frm.InvoiceVendor_vendorName.value = frm.ConsolidatedVendor_vendorName.value;
				window.parent.frm.InvoiceAddress_addressLine1.value = frm.ConsolidatedVendor_addressLine1.value;
				window.parent.frm.InvoiceAddress_addressLine2.value = frm.ConsolidatedVendor_addressLine2.value;
				window.parent.frm.InvoiceAddress_addressLine3.value = frm.ConsolidatedVendor_addressLine3.value;
				window.parent.frm.InvoiceAddress_addressLine4.value = frm.ConsolidatedVendor_addressLine4.value;
				window.parent.frm.InvoiceAddress_city.value = frm.ConsolidatedVendor_city.value;
				window.parent.frm.InvoiceAddress_state.value = frm.ConsolidatedVendor_state.value;
				window.parent.frm.InvoiceAddress_postalCode.value = frm.ConsolidatedVendor_postalCode.value;
				window.parent.frm.InvoiceAddress_country.value = frm.ConsolidatedVendor_country.value;
				window.parent.frm.InvoiceHeader_apReference.value = frm.ConsolidatedVendor_apReference.value;
				window.parent.frm.InvoiceVendor_apReference.value = frm.ConsolidatedVendor_apReference.value;
				window.parent.frm.InvoiceAddress_addressCode.value = frm.ConsolidatedVendor_addressCode.value;
				window.parent.frm.InvoiceHeader_vendorAddrCode.value = frm.ConsolidatedVendor_addressCode.value;
				window.parent.frm.InvoiceHeader_termsCode.value = frm.Vendor_vendTerms.value;
			} else {
				window.parent.frm.InvoiceHeader_vendorName.value = frm.ConsolidatedVendor_vendorName[rowSelect].value;
				window.parent.frm.InvoiceVendor_vendorName.value = frm.ConsolidatedVendor_vendorName[rowSelect].value;
				window.parent.frm.InvoiceAddress_addressLine1.value = frm.ConsolidatedVendor_addressLine1[rowSelect].value;
				window.parent.frm.InvoiceAddress_addressLine2.value = frm.ConsolidatedVendor_addressLine2[rowSelect].value;
				window.parent.frm.InvoiceAddress_addressLine3.value = frm.ConsolidatedVendor_addressLine3[rowSelect].value;
				window.parent.frm.InvoiceAddress_addressLine4.value = frm.ConsolidatedVendor_addressLine4[rowSelect].value;
				window.parent.frm.InvoiceAddress_city.value = frm.ConsolidatedVendor_city[rowSelect].value;
				window.parent.frm.InvoiceAddress_state.value = frm.ConsolidatedVendor_state[rowSelect].value;
				window.parent.frm.InvoiceAddress_postalCode.value = frm.ConsolidatedVendor_postalCode[rowSelect].value;
				window.parent.frm.InvoiceAddress_country.value = frm.ConsolidatedVendor_country[rowSelect].value;
				window.parent.frm.InvoiceHeader_apReference.value = frm.ConsolidatedVendor_apReference[rowSelect].value;
				window.parent.frm.InvoiceVendor_apReference.value = frm.ConsolidatedVendor_apReference[rowSelect].value;
				window.parent.frm.InvoiceAddress_addressCode.value = frm.ConsolidatedVendor_addressCode[rowSelect].value;
				window.parent.frm.InvoiceHeader_vendorAddrCode.value = frm.ConsolidatedVendor_addressCode[rowSelect].value;
				window.parent.frm.InvoiceHeader_termsCode.value = frm.Vendor_vendTerms[rowSelect].value;
			}
			window.parent.doSubmit('/invoice/iv_payment_info.jsp', 'InvoiceValidateVendor');
		}
		else if (formField == "InvoiceHeader_orderByName")
		{
			window.parent.frm.InvoiceHeader_orderByName.value = frm.UserProfile_displayName[rowSelect].value;
			window.parent.frm.InvoiceHeader_orderByEmail.value = frm.UserProfile_mailId[rowSelect].value;
			window.parent.frm.InvoiceHeader_orderByPhone.value = frm.UserProfile_phoneNumber[rowSelect].value;
		}
		else if (formField == "InvoiceHeader_termsCode")
		{
			window.parent.frm.InvoiceHeader_termsDiscperc.value = frm.PaymentTerm_discount[rowSelect].value;
			window.parent.frm.InvoiceHeader_termsDiscdays.value = frm.PaymentTerm_discountDays[rowSelect].value;

			<% if (oid.equalsIgnoreCase("wpc08p")) { %>
			var type = frm.PaymentTerm_termTypeFlag[rowSelect].value;
			if ( isEmpty(window.parent.frm.InvoiceHeader_termsDiscdays.value)|| window.parent.frm.InvoiceHeader_termsDiscdays.value=='0' )
			{
				window.parent.frm.InvoiceHeader_discountDate.value = '';
			}
			else if (window.parent.frm.InvoiceHeader_invoiceDate_2 && !isEmpty(window.parent.frm.InvoiceHeader_termsDiscdays.value))
			{
				if(type=='0')
				{
					var currentDate = window.parent.frm.InvoiceHeader_invoiceDate_2.value;
					currentDate = currentDate.replace('-','/');
					var today = new Date(currentDate);
					today.setTime(today.getTime() + frm.PaymentTerm_discountDays[rowSelect].value * 24 * 60 * 60 * 1000);
					var month = today.getMonth() + 1;
					if (month < 9) month = '0' + month;
					var day = today.getDate();
					if (day < 9) day = '0' + day;
					window.parent.frm.InvoiceHeader_discountDate.value = month + '-' + day + '-' + today.getFullYear();
				}
				else if (type=='1')
				{
					var currentDate = window.parent.frm.InvoiceHeader_invoiceDate_2.value;
					currentDate = currentDate.replace('-','/');
					var today = new Date(currentDate);
					today.setTime(today.getTime() - today.getDate() * 24 * 60 * 60 * 1000);
					today.setTime(today.getTime() + frm.PaymentTerm_discountDays[rowSelect].value * 24 * 60 * 60 * 1000);
					var year = today.getFullYear();
					var month = today.getMonth() + 2;
					if (month == 13)
					{
						month = 1;
						year = year + 1;
					}
					if (month < 9) month = '0' + month;
					var day = today.getDate();
					if (day < 9) day = '0' + day;
					window.parent.frm.InvoiceHeader_discountDate.value = month + '-' + day + '-' + year;
				}
			}
			<% } %>

		}
		else if(formField=="RfqVendor_contactId"){
			var columns = frm.elements.item("Contact_id_contactCode");
			var fullName;

			if (columns.length==undefined)
			{
				window.parent.frm.RfqVendor_addressCode.value = frm.Contact_addressCode.value;
				window.parent.frm.RfqVendor_contactName.value = frm.Contact_displayName.value;
			}
			else{
				window.parent.frm.RfqVendor_addressCode.value = frm.Contact_addressCode[rowSelect].value;
				window.parent.frm.RfqVendor_contactName.value = frm.Contact_displayName[rowSelect].value;
			}
		}

		else if(formField=="ShipTo_shipToCode"){
			var rows = window.parent.maxRows;
			var fullName;

			if (window.parent.frm.ShipTo_shipToCode[row] == undefined && window.parent.frm.ShipTo_shipToCode == undefined) {
				window.parent.addNew();
			}

			if (rows == 1) {
				window.parent.setQty(0);
				window.parent.frm.ShipTo_shipToCode.value = frm.Address_id_addressCode[rowSelect].value;
				window.parent.frm.Address_addressLine1.value = frm.Address_addressLine1[rowSelect].value;
				window.parent.frm.ShipTo_quantity.select();
				window.parent.frm.ShipTo_quantity.focus();
				if (browser != "NS6") {
					window.parent.frm.ShipTo_quantity.fireEvent("onfocus");
				}
			}
			else{
				window.parent.setQty(row);
				window.parent.frm.ShipTo_shipToCode[row].value = frm.Address_id_addressCode[rowSelect].value;
				window.parent.frm.Address_addressLine1[row].value = frm.Address_addressLine1[rowSelect].value;
				window.parent.frm.ShipTo_quantity[row].select();
				window.parent.frm.ShipTo_quantity[row].focus();
				if (browser != "NS6") {
					window.parent.frm.ShipTo_quantity[row].fireEvent("onfocus");
				}
			}
		}
		else if(formField=="BillTo_billToCode") {
			var rows = window.parent.maxRows;
			var fullName;

			if (window.parent.frm.BillTo_billToCode[row] == undefined && window.parent.frm.BillTo_billToCode == undefined) {
				window.parent.addNew();
			}

			if (rows == 1) {
				window.parent.setQty(0);
				window.parent.frm.BillTo_billToCode.value = frm.Address_id_addressCode[rowSelect].value;
				window.parent.frm.Address_addressLine1.value = frm.Address_addressLine1[rowSelect].value;
				window.parent.frm.BillTo_quantity.select();
				window.parent.frm.BillTo_quantity.focus();
				if (browser != "NS6") {
					window.parent.frm.BillTo_quantity.fireEvent("onfocus");
				}
			}
			else {
				window.parent.setQty(row);
				window.parent.frm.BillTo_billToCode[row].value = frm.Address_id_addressCode[rowSelect].value;
				window.parent.frm.Address_addressLine1[row].value = frm.Address_addressLine1[rowSelect].value;
				window.parent.frm.BillTo_quantity[row].select();
				window.parent.frm.BillTo_quantity[row].focus();
				if (browser != "NS6") {
					window.parent.frm.BillTo_quantity[row].fireEvent("onfocus");
				}
			}
		}
		else if (formField == "RequisitionHeader_taxCode")
		{
			if ("<%=fromPage%>" == "Totals")
			{
				var columns = frm.elements.item("TaxCode_taxRate");
				if (columns.length==undefined)
				{
					window.parent.frm.RequisitionHeader_taxPercent.value = frm.TaxCode_taxRate.value;

					if (window.parent.rowcount > 1)
					{
						for (var i = 0; i < window.parent.rowcount; i++)
						{
							window.parent.frm.RequisitionLine_taxCode.value = selectedData;
							window.parent.frm.RequisitionLine_taxPercent.value = frm.TaxCode_taxRate.value;
						}
					}
					else if (window.parent.rowcount == 1)
					{
						window.parent.frm.RequisitionLine_taxCode.value = selectedData;
						window.parent.frm.RequisitionLine_taxPercent.value = frm.TaxCode_taxRate.value;
					}

					window.parent.changeHeaderAmount(window.parent.frm.RequisitionHeader_taxPercent);
				}
				else
				{
					window.parent.frm.RequisitionHeader_taxPercent.value = frm.TaxCode_taxRate[rowSelect].value;

					if (window.parent.rowcount > 1)
					{
						for (var i = 0; i < window.parent.rowcount; i++)
						{
							window.parent.frm.RequisitionLine_taxCode[i].value = selectedData;
							window.parent.frm.RequisitionLine_taxPercent[i].value = frm.TaxCode_taxRate[rowSelect].value;
						}
					}
					else if (window.parent.rowcount == 1)
					{
						window.parent.frm.RequisitionLine_taxCode.value = selectedData;
						window.parent.frm.RequisitionLine_taxPercent.value = frm.TaxCode_taxRate[rowSelect].value;
					}

					window.parent.changeHeaderAmount(window.parent.frm.RequisitionHeader_taxPercent);
				}
			}
		}
		else if (formField.indexOf("RequisitionLine_taxCode") >= 0)
		{
			var columns = frm.elements.item("RequisitionLine_taxCode");
			if (columns.length==undefined)
			{
				if (window.parent.rowcount > 1)
				{
				window.parent.frm.RequisitionLine_taxCode.value = selectedData;
				window.parent.frm.RequisitionLine_taxPercent.value = frm.TaxCode_taxRate.value;
				}
				else
				{
					window.parent.frm.RequisitionLine_taxCode.value = selectedData;
					window.parent.frm.RequisitionLine_taxPercent.value = frm.TaxCode_taxRate.value;
				}
				window.parent.changeLineAmount(window.parent.frm.RequisitionLine_taxPercent);
			}
			else
			{
				if (window.parent.rowcount > 1)
				{
				window.parent.frm.RequisitionLine_taxCode[window.parent.currentRow].value = selectedData;
				window.parent.frm.RequisitionLine_taxPercent[window.parent.currentRow].value = frm.TaxCode_taxRate[rowSelect].value;
				}
				else
				{
					window.parent.frm.RequisitionLine_taxCode.value = selectedData;
					window.parent.frm.RequisitionLine_taxPercent.value = frm.TaxCode_taxRate[rowSelect].value;
				}
				window.parent.changeLineAmount(window.parent.frm.RequisitionLine_taxPercent, window.parent.currentRow);
			}
		}
		else if (formField == "PoHeader_taxCode")
		{
			if ("<%=fromPage%>" == "Totals")
			{
				window.parent.frm.PoHeader_taxPercent.value = frm.TaxCode_taxRate[rowSelect].value;

				if (window.parent.rowcount > 1)
				{
					for (var i = 0; i < window.parent.rowcount; i++)
					{
						window.parent.frm.PoLine_taxCode[i].value = selectedData;
						window.parent.frm.PoLine_taxPercent[i].value = frm.TaxCode_taxRate[rowSelect].value;
					}
				}
				else if (window.parent.rowcount == 1)
				{
					window.parent.frm.PoLine_taxCode.value = selectedData;
					window.parent.frm.PoLine_taxPercent.value = frm.TaxCode_taxRate[rowSelect].value;
				}

				window.parent.changeHeaderAmount(window.parent.frm.PoHeader_taxPercent);
			}
		}
		else if (formField.indexOf("PoLine_taxCode") >= 0)
		{
			if (window.parent.rowcount > 1)
			{
			window.parent.frm.PoLine_taxCode[window.parent.currentRow].value = selectedData;
			window.parent.frm.PoLine_taxPercent[window.parent.currentRow].value = frm.TaxCode_taxRate[rowSelect].value;
			}
			else
			{
				window.parent.frm.PoLine_taxCode.value = selectedData;
				window.parent.frm.PoLine_taxPercent.value = frm.TaxCode_taxRate[rowSelect].value;
			}
			window.parent.changeLineAmount(window.parent.frm.PoLine_taxPercent, window.parent.currentRow);
		}
		else if (formField == "InvBinLocation_reasonCode")
		{
			window.parent.frm.InvBinLocation_reasonDescription.value = frm.StdTable_description[rowSelect].value;
		}
		else if (formField == "InvFormData_micrCode")
		{
			window.parent.frm.InvFormData_micrDesc.value = frm.StdTable_description[rowSelect].value;
		}
		else if (formField == "InvFormData_ocrCode")
		{
			window.parent.frm.InvFormData_ocrDesc.value = frm.StdTable_description[rowSelect].value;
		}
		else if (formField == "toBin")
		{
		 	var Xcolumns = frm.elements.item("InvBinLocation_aisle");
			if( Xcolumns.length == undefined)
			{
			 	window.parent.frm.newAisle.value = frm.InvBinLocation_aisle.value;
	  			window.parent.frm.newRow.value = frm.InvBinLocation_locrow.value;
	  			window.parent.frm.newTier.value = frm.InvBinLocation_tier.value;
	  			window.parent.frm.newBin.value = frm.InvBinLocation_bin.value;
	  			if (window.parent.frm.newUdf1Code && frm.InvBinLocation_udf1Code) {
	  				window.parent.frm.newUdf1Code.value = frm.InvBinLocation_udf1Code.value;
	  			}
			}
			else
			{
	  			window.parent.frm.newAisle.value = frm.InvBinLocation_aisle[rowSelect].value;
	  			window.parent.frm.newRow.value = frm.InvBinLocation_locrow[rowSelect].value;
	  			window.parent.frm.newTier.value = frm.InvBinLocation_tier[rowSelect].value;
	  			window.parent.frm.newBin.value = frm.InvBinLocation_bin[rowSelect].value;
	  			if (window.parent.frm.newUdf1Code && frm.InvBinLocation_udf1Code[rowSelect]) {
	  				window.parent.frm.newUdf1Code.value = frm.InvBinLocation_udf1Code[rowSelect].value;
	  			}
			}
		}
		else if (formField == "DisbLine_icRc")
		{
		 	var Xcolumns = frm.elements.item("InvBinLocation_aisle");
			if( Xcolumns.length == undefined)
			{
			 	window.parent.frm.DisbLine_aisle.value = frm.InvBinLocation_aisle.value;
	  			window.parent.frm.DisbLine_locrow.value = frm.InvBinLocation_locrow.value;
	  			window.parent.frm.DisbLine_tier.value = frm.InvBinLocation_tier.value;
	  			window.parent.frm.DisbLine_bin.value = frm.InvBinLocation_bin.value;
	  			if (window.parent.frm.DisbLine_udf1Code && frm.InvBinLocation_udf1Code) {
	  				window.parent.frm.DisbLine_udf1Code.value = frm.InvBinLocation_udf1Code.value;
	  			}
	  			window.parent.frm.DisbLine_icRc.value = frm.InvBinLocation_icRc.value;
	  			window.parent.frm.InvBinLocation_icRc.value = frm.InvBinLocation_icRc.value;
			}
			else
			{
	  			window.parent.frm.DisbLine_aisle.value = frm.InvBinLocation_aisle[rowSelect].value;
	  			window.parent.frm.DisbLine_locrow.value = frm.InvBinLocation_locrow[rowSelect].value;
	  			window.parent.frm.DisbLine_tier.value = frm.InvBinLocation_tier[rowSelect].value;
	  			window.parent.frm.DisbLine_bin.value = frm.InvBinLocation_bin[rowSelect].value;
	  			if (window.parent.frm.DisbLine_udf1Code && frm.InvBinLocation_udf1Code[rowSelect]) {
	  				window.parent.frm.DisbLine_udf1Code.value = frm.InvBinLocation_udf1Code[rowSelect].value;
	  			}
	  			window.parent.frm.DisbLine_icRc.value = frm.InvBinLocation_icRc[rowSelect].value;
	  			window.parent.frm.InvBinLocation_icRc.value = frm.InvBinLocation_icRc[rowSelect].value;
			}
		}
		else if (formField == "RequisitionLine_vendorId")
		{
			var columns = frm.elements.item("Contact_id_vendorId");
			if (columns.length==undefined)
			{
				if(window.parent.frm.RequisitionLine_vendorName && frm.Vendor_vendorName.value)
				window.parent.frm.RequisitionLine_vendorName.value = frm.Vendor_vendorName.value;
			}
			else
			{	if(window.parent.frm.RequisitionLine_vendorName && frm.Vendor_vendorName[rowSelect].value)
				window.parent.frm.RequisitionLine_vendorName.value = frm.Vendor_vendorName[rowSelect].value;
			}

			<% if (oid.equalsIgnoreCase("bly07p")) { %>
			if (window.parent.frm.RequisitionLine_requiredDate)
			{
				var currentDate = window.parent.frm.RequisitionLine_requiredDate.value;
				currentDate = currentDate.replace('-','/');
				var today = new Date(currentDate);
				today.setTime(today.getTime() + frm.Vendor_leadDays[rowSelect].value * 24 * 60 * 60 * 1000);
				var month = today.getMonth() + 1;
				if (month < 9) month = '0' + month;
				var day = today.getDate();
				if (day < 9) day = '0' + day;
				window.parent.frm.RequisitionLine_requiredDate.value = month + '-' + day + '-' + today.getFullYear();
			}
			<% } %>
		}
		else if (formField == "PoLine_vendorId")
		{
			if (frm.Vendor_vendorName.length==undefined){
				window.parent.frm.PoLine_vendorName.value = frm.Vendor_vendorName.value;
			}else{
				window.parent.frm.PoLine_vendorName.value = frm.Vendor_vendorName[rowSelect].value;
			}
		}
		else if (formField.indexOf("InspectionDiscrep_inspectCode") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(formField);
			if( Xcolumns.length != undefined && window.parent.currentRow != undefined && window.parent.currentRow > -1)
			{
				if (window.parent.frm.InspectionDiscrep_inspRequirements) {
					window.parent.frm.InspectionDiscrep_inspRequirements[window.parent.currentRow].value = frm.StdTable_description[rowSelect].value;
				}
			}
			else
			{
				if (window.parent.frm.InspectionDiscrep_inspRequirements)
				{
					if (frm.StdTable_description.length == undefined)
					{
						window.parent.frm.InspectionDiscrep_inspRequirements.value = frm.StdTable_description.value;
					}
					else
					{
						window.parent.frm.InspectionDiscrep_inspRequirements.value = frm.StdTable_description[rowSelect].value;
					}
				}
			}
		}
		else if (formField.indexOf("InspectionLine_inspectCode") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(formField);
			if( Xcolumns.length != undefined && window.parent.currentRow != undefined && window.parent.currentRow > -1)
			{
				if (window.parent.frm.InspectionCode_description) {
					window.parent.frm.InspectionCode_description[window.parent.currentRow].value = frm.StdTable_description[rowSelect].value;
				}
			}
			else
			{
				if (window.parent.frm.InspectionCode_description)
				{
					if (frm.StdTable_description.length == undefined)
					{
						window.parent.frm.InspectionCode_description.value = frm.StdTable_description.value;
					}
					else
					{
						window.parent.frm.InspectionCode_description.value = frm.StdTable_description[rowSelect].value;
					}
				}
			}
		}
		else if (formField.indexOf("InspectionHeader_standardCode") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(formField);
			if( Xcolumns.length != undefined && window.parent.currentRow != undefined && window.parent.currentRow > -1)
			{
				if (window.parent.frm.standardCodeDescription) {
					window.parent.frm.standardCodeDescription[window.parent.currentRow].value = frm.InspectionStd_description[rowSelect].value;
					window.parent.loadStdInspections(formField) ;
				}
			}
			else
			{
				if (window.parent.frm.standardCodeDescription)
				{
					if (frm.InspectionStd_description.length == undefined)
					{
						window.parent.frm.standardCodeDescription.value = frm.InspectionStd_description.value;
						window.parent.loadStdInspections(formField) ;
					}
					else
					{
						window.parent.frm.standardCodeDescription.value = frm.InspectionStd_description[rowSelect].value;
						window.parent.loadStdInspections(formField) ;
					}
				}
			}
		}
		else if (formField.indexOf("InspectionDispos_dispType") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(formField);
			if( Xcolumns.length != undefined && window.parent.currentRow != undefined && window.parent.currentRow > -1)
			{
				if (window.parent.frm.InspectionDispos_disposition) {
					window.parent.frm.InspectionDispos_disposition[window.parent.currentRow].value = frm.StdTable_description[rowSelect].value;
				}
			}
			else
			{
				if (window.parent.frm.InspectionDispos_disposition)
				{
					if (frm.StdTable_description.length == undefined)
					{
						window.parent.frm.InspectionDispos_disposition.value = frm.StdTable_description.value;
					}
					else
					{
						window.parent.frm.InspectionDispos_disposition.value = frm.StdTable_description[rowSelect].value;
					}
				}
			}
		}
		else if (formField.indexOf("InspectionMte_mteNo") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(formField);
			if( Xcolumns.length != undefined && window.parent.currentRow != undefined && window.parent.currentRow > -1)
			{
				if (window.parent.frm.InspectionMte_description) {
					if (frm.VSRRFmtsMte_description.length == undefined){
						window.parent.frm.InspectionMte_description[window.parent.currentRow].value = frm.VSRRFmtsMte_description.value;
						window.parent.frm.InspectionMte_calDate[window.parent.currentRow].value = frm.VSRRFmtsMte_calDueDate.value;
					}else{
						window.parent.frm.InspectionMte_description[window.parent.currentRow].value = frm.VSRRFmtsMte_description[rowSelect].value;
						window.parent.frm.InspectionMte_calDate[window.parent.currentRow].value = frm.VSRRFmtsMte_calDueDate[rowSelect].value;
					}
				}
			}
			else
			{
				if (window.parent.frm.InspectionMte_description)
				{
					if (frm.VSRRFmtsMte_description.length == undefined)
					{
						window.parent.frm.InspectionMte_description.value = frm.VSRRFmtsMte_description.value;
						window.parent.frm.InspectionMte_calDate.value = frm.VSRRFmtsMte_calDueDate.value;
					}
					else
					{
						window.parent.frm.InspectionMte_description.value = frm.VSRRFmtsMte_description[rowSelect].value;
						window.parent.frm.InspectionMte_calDate.value = frm.VSRRFmtsMte_calDueDate[rowSelect].value;
					}
				}
			}
		}
		else if (formField.indexOf("InspectionMfr_documentType") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(formField);
			if( Xcolumns.length != undefined && window.parent.currentRow != undefined && window.parent.currentRow > -1)
			{
				if (window.parent.frm.InspectionMfr_documentType) {
					window.parent.frm.InspectionMfr_documentType[window.parent.currentRow].value = frm.StdTable_id_tableKey[rowSelect].value;
				}
			}
			else
			{
				if (window.parent.frm.InspectionMfr_documentType)
				{
					if (frm.StdTable_id_tableKey.length == undefined)
					{
						window.parent.frm.InspectionMfr_documentType.value = frm.StdTable_id_tableKey.value;
					}
					else
					{
						window.parent.frm.InspectionMfr_documentType.value = frm.StdTable_id_tableKey[rowSelect].value;
					}
				}
			}
		}
		else if (formField.indexOf("InspectionLine_critNo") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(formField);
			if( Xcolumns.length != undefined && window.parent.currentRow != undefined && window.parent.currentRow > -1)
			{
				if (window.parent.frm.InspectionLine_critDescription) {
					if (frm.InspectionCrit_description.length == undefined){
						window.parent.frm.InspectionLine_critDescription[window.parent.currentRow].value = frm.InspectionCrit_description.value;
					}else{
						window.parent.frm.InspectionLine_critDescription[window.parent.currentRow].value = frm.InspectionCrit_description[rowSelect].value;
					}
				}
			}
			else
			{
				if (window.parent.frm.InspectionLine_critDescription)
				{
					if (frm.InspectionCrit_description.length == undefined)
					{
						window.parent.frm.InspectionLine_critDescription.value = frm.InspectionCrit_description.value;
					}
					else
					{
						window.parent.frm.InspectionLine_critDescription.value = frm.InspectionCrit_description[rowSelect].value;
					}
				}
			}

		}
		else if (formField.indexOf("InspectionStd_critNo") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(formField);
			if( Xcolumns.length != undefined && window.parent.currentRow != undefined && window.parent.currentRow > -1)
			{
				if (window.parent.frm.InspectionStd_critText) {
					if (frm.InspectionCrit_description.length == undefined){
						window.parent.frm.InspectionStd_critText[window.parent.currentRow].value = frm.InspectionCrit_description.value;
						window.parent.frm.InspectionStd_defaultFlag[window.parent.currentRow].value = "Y" ;
					}else{
						window.parent.frm.InspectionStd_critText[window.parent.currentRow].value = frm.InspectionCrit_description[rowSelect].value;
						window.parent.frm.InspectionStd_defaultFlag[window.parent.currentRow].value = "Y" ;
					}
				}
			}
			else
			{
				if (window.parent.frm.InspectionStd_critText)
				{
					if (frm.InspectionCrit_description.length == undefined)
					{
						window.parent.frm.InspectionStd_critText.value = frm.InspectionCrit_description.value;
						window.parent.frm.InspectionStd_defaultFlag.value = "Y";
					}
					else
					{
						window.parent.frm.InspectionStd_critText.value = frm.InspectionCrit_description[rowSelect].value;
						window.parent.frm.InspectionStd_defaultFlag.value = "Y";
					}
				}
			}

		}
		else if (formField.indexOf("remoteInspId") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(formField);
			if( Xcolumns.length != undefined && window.parent.currentRow != undefined && window.parent.currentRow > -1)
			{
				if (window.parent.frm.remoteInspName) {
					window.parent.frm.remoteInspName[window.parent.currentRow].value = frm.UserProfile_displayName[rowSelect].value;
				}
			}
			else
			{
				if (window.parent.frm.remoteInspName) {
					if (frm.UserProfile_displayName.length == undefined) {
						window.parent.frm.remoteInspName.value = frm.UserProfile_displayName.value;
					} else {
						window.parent.frm.remoteInspName.value = frm.UserProfile_displayName[rowSelect].value;
					}
				}
			}
		}
		else if (formField.indexOf("engineerId") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(formField);
			if( Xcolumns.length != undefined && window.parent.currentRow != undefined && window.parent.currentRow > -1)
			{
				if (window.parent.frm.engineerName) {
					window.parent.frm.engineerName[window.parent.currentRow].value = frm.UserProfile_displayName[rowSelect].value;
				}
			}
			else
			{
				if (window.parent.frm.engineerName) {
					if (frm.UserProfile_displayName.length == undefined) {
						window.parent.frm.engineerName.value = frm.UserProfile_displayName.value;
					} else {
						window.parent.frm.engineerName.value = frm.UserProfile_displayName[rowSelect].value;
					}
				}
			}
		}
		else if (formField.indexOf("RequisitionHeader_owner") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(formField);
			if( Xcolumns.length != undefined && window.parent.currentRow != undefined && window.parent.currentRow > -1)
			{
				if (window.parent.frm.as_enteredByName) {
					window.parent.frm.as_enteredByName[window.parent.currentRow].value = frm.UserProfile_displayName[rowSelect].value;
				}
			}
			else
			{
				if (window.parent.frm.as_enteredByName) {
					if (frm.UserProfile_displayName.length == undefined) {
						window.parent.frm.as_enteredByName.value = frm.UserProfile_displayName.value;
					} else {
						window.parent.frm.as_enteredByName.value = frm.UserProfile_displayName[rowSelect].value;
					}
				}
			}
		}
		else if (formField.indexOf("as_enteredBy") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(formField);
			if( Xcolumns.length != undefined && window.parent.currentRow != undefined && window.parent.currentRow > -1)
			{
				if (window.parent.frm.as_enteredByName) {
					window.parent.frm.as_enteredByName[window.parent.currentRow].value = frm.UserProfile_displayName[rowSelect].value;
				}
			}
			else
			{
				if (window.parent.frm.as_enteredByName) {
					if (frm.UserProfile_displayName.length == undefined) {
						window.parent.frm.as_enteredByName.value = frm.UserProfile_displayName.value;
					} else {
						window.parent.frm.as_enteredByName.value = frm.UserProfile_displayName[rowSelect].value;
					}
				}
			}
		}
		else if (formField.indexOf("inspectorId") >= 0)
		{
			var Xcolumns = window.parent.frm.elements.item(formField);
			if( Xcolumns.length != undefined && window.parent.currentRow != undefined && window.parent.currentRow > -1)
			{
				if (window.parent.frm.inspectorName) {
					window.parent.frm.inspectorName[window.parent.currentRow].value = frm.UserProfile_displayName[rowSelect].value;
				}
			}
			else
			{
				if (window.parent.frm.inspectorName) {
					if (frm.UserProfile_displayName.length == undefined) {
						window.parent.frm.inspectorName.value = frm.UserProfile_displayName.value;
					} else {
						window.parent.frm.inspectorName.value = frm.UserProfile_displayName[rowSelect].value;
					}
				}
			}
		}
		else if (formField.indexOf("_requisitioner") > 0)
		{
			var Xcolumns = window.parent.frm.elements.item(formField);
			if( Xcolumns.length != undefined && window.parent.currentRow != undefined && window.parent.currentRow > -1)
			{
				if (window.parent.frm.as_requisitionerName) {
					window.parent.frm.as_requisitionerName[window.parent.currentRow].value = frm.UserProfile_displayName[rowSelect].value;
				}
				if (window.parent.frm.PoLine_departmentCode) {
					window.parent.frm.PoLine_departmentCode[window.parent.currentRow].value = frm.UserProfile_department[rowSelect].value;
				}
			}
			else
			{
				if (window.parent.frm.as_requisitionerName) {
					if (frm.UserProfile_displayName.length == undefined) {
						window.parent.frm.as_requisitionerName.value = frm.UserProfile_displayName.value;
					} else {
						window.parent.frm.as_requisitionerName.value = frm.UserProfile_displayName[rowSelect].value;
					}
				}
				if (window.parent.frm.PoLine_departmentCode) {
					if (frm.UserProfile_department.length == undefined) {
						window.parent.frm.PoLine_departmentCode.value = frm.UserProfile_department.value;
					} else {
						window.parent.frm.PoLine_departmentCode.value = frm.UserProfile_department[rowSelect].value;
					}
				}
			}

			if (frm.UserProfile_department.length == undefined) {
				if (window.parent.frm.RequisitionHeader_departmentCode && !isEmpty(frm.UserProfile_department.value)) {
					window.parent.frm.RequisitionHeader_departmentCode.value = frm.UserProfile_department.value;
				}
			} else {
				if (window.parent.frm.RequisitionHeader_departmentCode && !isEmpty(frm.UserProfile_department[rowSelect].value)) {
					window.parent.frm.RequisitionHeader_departmentCode.value = frm.UserProfile_department[rowSelect].value;
				}
			}
			if (window.parent.frm.RequisitionLine_departmentCode) {
				window.parent.frm.RequisitionLine_departmentCode.value = frm.UserProfile_department[rowSelect].value;
			}
			if (window.parent.frm.RfqHeader_departmentCode) {
				window.parent.frm.RfqHeader_departmentCode.value = frm.UserProfile_department[rowSelect].value;
			}
			if (window.parent.frm.PoHeader_departmentCode) {
				if (frm.UserProfile_department.length != undefined) {
					window.parent.frm.PoHeader_departmentCode.value = frm.UserProfile_department[rowSelect].value;
				} else {
					window.parent.frm.PoHeader_departmentCode.value = frm.UserProfile_department.value;
				}
			}
		}
		else if (formField == "RequisitionHeader_buyerRemarks")
		{
			var columns = window.parent.frm.elements.item("RequisitionHeader_buyerRemarks");

			if (columns.length==undefined)
			{
				window.parent.frm.RequisitionHeader_buyerRemarks.value = frm.StdTable_description[rowSelect].value;
			}
			else
			{
				window.parent.frm.RequisitionHeader_buyerRemarks[window.parent.currentRow].value = frm.StdTable_description[rowSelect].value;
			}
		}
		else if (formField.indexOf("_buyer") > 0 || formField == "DepartmentBuyer_userId")
		{
			if (formField == "PoHeader_buyerRemarks")
			{
				var columns = window.parent.frm.elements.item("PoHeader_buyerRemarks");

				if (columns.length==undefined)
				{
					window.parent.frm.PoHeader_buyerRemarks.value = frm.StdTable_description[rowSelect].value;
				}
				else
				{
					window.parent.frm.PoHeader_buyerRemarks[window.parent.currentRow].value = frm.StdTable_description[rowSelect].value;
				}
			}
			if(window.parent.frm.Commodity_buyerName) {
				var columns = window.parent.frm.elements.item("Commodity_buyerCode");

				if (columns.length==undefined)
				{
					window.parent.frm.Commodity_buyerName.value = frm.UserProfile_displayName[rowSelect].value;
				}
				else
				{
					window.parent.frm.Commodity_buyerName[window.parent.currentRow].value = frm.UserProfile_displayName[rowSelect].value;
				}
			}
			else
			{
				if (window.parent.frm.as_buyerName)
				{
					if (rowSelect && rowSelect > 0) {
						window.parent.frm.as_buyerName.value = frm.UserProfile_displayName[rowSelect].value;
					} else {
						window.parent.frm.as_buyerName.value = frm.UserProfile_displayName.value;
					}
				}

				var Ocolumns = window.parent.frm.elements.item("DepartmentBuyer_userId");
				if (Ocolumns != null && Ocolumns.length != undefined)
				{
					if (rowSelect && rowSelect > 0) {
						window.parent.frm.as_buyerName[row].value = frm.UserProfile_displayName[rowSelect].value;
					} else {
						window.parent.frm.as_buyerName[row].value = frm.UserProfile_displayName.value;
					}
				}
			}
		}
		else if (formField.indexOf("_owner") > 0)
		{
			if (window.parent.frm.as_ownerName)
			{
		    	var str = new String;
		    	str = selectedData.substring(0,1);
		        if(str == '@'){
		        	if (rowSelect && rowSelect > 0) {
						window.parent.frm.as_ownerName.value = frm.AppPool_pooldesc[rowSelect].value;
					} else if(frm.AppPool_pooldesc){
						window.parent.frm.as_ownerName.value = frm.AppPool_pooldesc.value;
					}
		        }else{
			        if (rowSelect && rowSelect > 0) {
						window.parent.frm.as_ownerName.value = frm.UserProfile_displayName[rowSelect].value;
					} else if(frm.UserProfile_displayName){
							window.parent.frm.as_ownerName.value = frm.UserProfile_displayName.value;
					}
		        }
			}
		}
		else if (formField.indexOf("_deptManager") > 0)
		{
			if (window.parent.frm.as_managerName)
			{
				window.parent.frm.as_managerName.value = frm.UserProfile_displayName[rowSelect].value;
			}
		}
		else if (formField.indexOf("_authorizationCode") > 0)
		{
			if (window.parent.frm.as_authbyName)
			{
				if (frm.UserProfile_displayName.length == undefined) {
					window.parent.frm.as_authbyName.value = frm.UserProfile_displayName.value;
				} else {
					window.parent.frm.as_authbyName.value = frm.UserProfile_displayName[rowSelect].value;
				}
			}
		}
		else if (formField.indexOf("_forwardTo") > 0)
		{
			if (window.parent.frm.as_forwardTo)
			{
				window.parent.frm.as_forwardTo.value = selectedData;
			}
			if (window.parent.frm.as_forwardToName)
			{
				window.parent.frm.as_forwardToName.value = frm.UserProfile_displayName[rowSelect].value;
			}
		}
		else if (formField =="routTo")
		{
			window.parent.frm.routTo.select();
			window.parent.frm.routTo.focus();
		}
		else if ((formField.indexOf("AppPooluser_") >= 0) || (formField.indexOf("AppRule_userId") >= 0))
		{
//			var Ocolumns = window.parent.frm.elements.item("AppPooluser_userId");
			var Ocolumns = window.parent.frm.elements.item(formField);
			var amount = eval(nfilter(frm.UserProfile_approvalAmount[rowSelect]));
			amount = nformat(amount, 2);
			var excludeLess = nformat(frm.UserProfile_excludeLess[rowSelect].value, 2);
			var approveByLine = frm.UserProfile_approveByLine[rowSelect].value;
			if (Ocolumns.length == undefined)
			{
				if (window.parent.frm.as_approverName) {
					window.parent.frm.as_approverName.value = frm.UserProfile_displayName[rowSelect].value;
				}
				if (window.parent.frm.as_title) {
					window.parent.frm.as_title.value = frm.UserProfile_title[rowSelect].value;
				}
				if (window.parent.frm.as_approvalAmount) {
					window.parent.frm.as_approvalAmount.value = frm.UserProfile_approvalAmount[rowSelect].value;
				}
				if (window.parent.frm.UserProfile_approvalAmount) {
					window.parent.frm.UserProfile_approvalAmount.value = amount;
					window.parent.frm.UserProfile_approvalAmount.style.textAlign = "right";
					if (approveByLine != "Y") {
						if (window.parent.frm.AppRule_amount) {
							window.parent.frm.AppRule_amount.value = amount;
						}
					}
				}
				if (window.parent.frm.UserProfile_excludeLess) {
					window.parent.frm.UserProfile_excludeLess.value = excludeLess;
					window.parent.frm.UserProfile_excludeLess.style.textAlign = "right";
					if (approveByLine != "Y") {
						if (window.parent.frm.AppRule_excludeLess) {
							window.parent.frm.AppRule_excludeLess.value = excludeLess;
						}
					}
				}
				if (window.parent.frm.UserProfile_approveByLine) {
					window.parent.frm.UserProfile_approveByLine.value = approveByLine;
				}
				if (window.parent.frm.UserProfile_userId) {
					window.parent.frm.UserProfile_userId.value = window.parent.frm.AppRule_userId.value;
				}
				if (window.parent.frm.as_currencyCode && frm.elements("compute_currencyCode")) {
					window.parent.frm.as_currencyCode.value = frm.compute_currencyCode[rowSelect].value;
				}
			}
			else
			{
				if (window.parent.frm.elements("as_approverName")) {
					window.parent.frm.as_approverName[row].value = frm.UserProfile_displayName[rowSelect].value;
				}
				if (window.parent.frm.elements("as_title")) {
					window.parent.frm.as_title[row].value = frm.UserProfile_title[rowSelect].value;
				}
				if (window.parent.frm.elements("as_approvalAmount")) {
					window.parent.frm.as_approvalAmount[row].value = frm.UserProfile_approvalAmount[rowSelect].value;
				}
				if (window.parent.frm.elements("UserProfile_approvalAmount")) {
					window.parent.frm.UserProfile_approvalAmount[row].value = amount;
					window.parent.frm.UserProfile_approvalAmount[row].style.textAlign = "right";
					if (approveByLine != "Y") {
						if (window.parent.frm.elements("AppRule_amount")) {
							window.parent.frm.AppRule_amount[row].value = amount;
						}
					}
				}
				if (window.parent.frm.elements("UserProfile_excludeLess")) {
					window.parent.frm.UserProfile_excludeLess[row].value = excludeLess;
					window.parent.frm.UserProfile_excludeLess[row].style.textAlign = "right";
					if (approveByLine != "Y") {
						if (window.parent.frm.elements("AppRule_excludeLess")) {
							window.parent.frm.AppRule_excludeLess[row].value = excludeLess;
						}
					}
				}
				if (window.parent.frm.elements("UserProfile_approveByLine")) {
					window.parent.frm.UserProfile_approveByLine[row].value = approveByLine;
				}
				if (window.parent.frm.elements("UserProfile_userId")) {
					window.parent.frm.UserProfile_userId[row].value = window.parent.frm.AppRule_userId[row].value;
				}
				if (window.parent.frm.elements("as_currencyCode") && frm.elements("compute_currencyCode")) {
					window.parent.frm.as_currencyCode[row].value = frm.compute_currencyCode[rowSelect].value;
				}
			}
		}
		else if(formField.indexOf("_umCode") >= 0)
		{
			var columns = frm.elements.item("UnitOfMeasure_factor");
			if (columns.length==undefined)
			{
				if (formField == "InvoiceLine_umCode")
				{
					//for InvoiceLine, must pass the row number since all the line items are on the same page
					if (window.parent.setUmFactor) {
						window.parent.setUmFactor(frm.UnitOfMeasure_factor.value);
					}
				}
				else if (formField != "CatalogItem_umCode")
				{
					if (window.parent.setUmFactor) {
						window.parent.setUmFactor(frm.UnitOfMeasure_factor.value);
					}
				}
			}
			else
			{
				if (formField == "InvoiceLine_umCode")
				{
					//for InvoiceLine, must pass the row number since all the line items are on the same page
					if (window.parent.setUmFactor) {
						window.parent.setUmFactor(frm.UnitOfMeasure_factor[rowSelect].value, row);
					}
				}
				else if (formField != "CatalogItem_umCode")
				{
					if (window.parent.setUmFactor) {
						window.parent.setUmFactor(frm.UnitOfMeasure_factor[rowSelect].value);
					}
				}
			}
		}
		else if (formField == "PoSecurity_accessId")
		{
			var Ocolumns = window.parent.frm.elements.item("asAccessName");
			var accessType;
			if( window.parent.count >1 )
			{
			  accessType = window.parent.frm.PoSecurity_accessType[row].value;
			 }
			else
			{
			  accessType = window.parent.frm.PoSecurity_accessType.value;
			 }

			if (Ocolumns.length == undefined)
			{
				if (accessType == "U")
				{
					if(totalRows == 1){
						window.parent.frm.PoSecurity_accessId.value = frm.UserProfile_userId.value;
						window.parent.frm.asAccessName.value = frm.UserProfile_displayName.value;
					}else{
						window.parent.frm.PoSecurity_accessId.value = frm.UserProfile_userId[rowSelect].value;
						window.parent.frm.asAccessName.value = frm.UserProfile_displayName[rowSelect].value;
					}
				}
				else
				{
					if(rowSelect ==  0){
						window.parent.frm.PoSecurity_accessId.value = frm.Department_departmentCode.value;
						window.parent.frm.asAccessName.value = frm.Department_departmentName.value;
					}else{
						window.parent.frm.PoSecurity_accessId.value = frm.Department_departmentCode[rowSelect].value;
						window.parent.frm.asAccessName.value = frm.Department_departmentName[rowSelect].value;
					}
				}
			}
			else
			{
				if (accessType == "U")
				{
					if(rowSelect == 0 && Ocolumns.length == undefined)
					{
						window.parent.frm.PoSecurity_accessId.value = frm.UserProfile_userId.value;
						window.parent.frm.asAccessName.value = frm.UserProfile_displayName.value;
					}else if (rowSelect == 0){
						window.parent.frm.PoSecurity_accessId[row].value = frm.UserProfile_userId.value;
						window.parent.frm.asAccessName[row].value = frm.UserProfile_displayName.value;
					}else{
						window.parent.frm.PoSecurity_accessId[row].value = frm.UserProfile_userId[rowSelect].value;
						window.parent.frm.asAccessName[row].value = frm.UserProfile_displayName[rowSelect].value;
					}
				}
				else
				{
					if (rowSelect == 0 && Ocolumns.length == undefined)
					{
						window.parent.frm.PoSecurity_accessId.value = frm.Department_departmentCode.value;
						window.parent.frm.asAccessName.value = frm.Department_departmentName.value;
					}else if (rowSelect == 0){
						window.parent.frm.PoSecurity_accessId[row].value = frm.Department_departmentCode.value;
						window.parent.frm.asAccessName[row].value = frm.Department_departmentName.value;
					}else{
						window.parent.frm.PoSecurity_accessId[row].value = frm.Department_departmentCode[rowSelect].value;
						window.parent.frm.asAccessName[row].value = frm.Department_departmentName[rowSelect].value;
					}
				}
			}
		}

		else if (formField == "Catalog_vendorId")
		{
			<% if(oid.equalsIgnoreCase("BLY07P")) { %>
			window.parent.frm.Catalog_currencyCode.value = frm.Vendor_currencyCode[rowSelect].value;
			<% } %>
			if (window.parent.frm.as_vendorId.value.length > 0)
			{
				var tmp_Contact_id_vendorId = "";
				if(rowSelect > 0)
					tmp_Contact_id_vendorId = frm.Contact_id_vendorId[rowSelect].value;
				else
					tmp_Contact_id_vendorId = frm.Contact_id_vendorId.value;

				if (window.parent.frm.as_vendorId.value != tmp_Contact_id_vendorId)
				{
					if (confirm("The vendor you selected does not match the vendor on the Order you have selected.  Would you like to continue?"))
					{
						window.parent.frm.Catalog_vendorId.value = tmp_Contact_id_vendorId;
						window.parent.frm.as_vendorId.value = "";
						window.parent.frm.Catalog_poNumber.value = "";
					}
				}
			}
			else
			{
				if (rowSelect > 0) {
					window.parent.frm.Catalog_vendorId.value = frm.Contact_id_vendorId[rowSelect].value;
				} else {
					window.parent.frm.Catalog_vendorId.value = frm.Contact_id_vendorId.value;
				}
			}
		}
		else if (formField.indexOf("_commodity") > 0)
		{
			var columns = frm.elements.item("Commodity_description");
			if (columns.length==undefined)
			{
				if (window.parent.frm.as_commodityName) {
					window.parent.frm.as_commodityName.value = frm.Commodity_description.value;

					var openerNameElements = window.parent.frm.as_commodityName;

					if (openerNameElements != undefined) {
						if (openerNameElements.length > 1) {
							window.parent.frm.as_commodityName[window.parent.currentRow].value = frm.Commodity_description.value;
						} else {
							window.parent.frm.as_commodityName.value = frm.Commodity_description.value;
						}
					}

					var openerIclLevelElements = window.parent.frm.as_commodityIclLevel;
					if (openerIclLevelElements != undefined) {
						var commodityIclLevel = (trim(frm.Commodity_iclLevel) == "") ? "0" : frm.Commodity_iclLevel.value;
						if (openerIclLevelElements.length > 1) {
							window.parent.frm.as_commodityIclLevel[window.parent.currentRow].value = commodityIclLevel;
						} else {
							window.parent.frm.as_commodityIclLevel.value = commodityIclLevel;
						}
					}
				}
				else
				{
					//window.parent.frm.CatalogItem_commodity.value = frm.Commodity_description.value;
				}
				if (window.parent.frm.RequisitionLine_taxable) {
					var taxableCommodity = frm.Commodity_taxable.value;
					if (taxableCommodity == "N") {
						window.parent.frm.c_checkbox[2].checked = false;
						window.parent.frm.c_checkbox[2].fireEvent("onclick");
					} else if (taxableCommodity == "Y") {
						window.parent.frm.c_checkbox[2].checked = true;
						window.parent.frm.c_checkbox[2].fireEvent("onclick");
					}
				} else if (window.parent.frm.PoLine_taxable) {
					var taxableCommodity = frm.Commodity_taxable.value;
					if (taxableCommodity == "N") {
						window.parent.frm.c_checkbox[1].checked = false;
						window.parent.frm.c_checkbox[1].fireEvent("onclick");
					} else if (taxableCommodity == "Y") {
						window.parent.frm.c_checkbox[1].checked = true;
						window.parent.frm.c_checkbox[1].fireEvent("onclick");
					}
				}
			}
			else
			{
				if (frm.Commodity_description[rowSelect].value != undefined) {
					//var openerNameElements = window.parent.frm.elements("as_commodityName");
					var openerNameElements = window.parent.frm.as_commodityName;

					if (openerNameElements != undefined) {
						if (totalRows > 0) {
							if (openerNameElements.length > 1) {
								window.parent.frm.as_commodityName[window.parent.currentRow].value = frm.Commodity_description[rowSelect].value;
							} else {
								window.parent.frm.as_commodityName.value = frm.Commodity_description[rowSelect].value;
							}
						} else {
							if (openerNameElements.length > 1) {
								window.parent.frm.as_commodityName[window.parent.currentRow].value = frm.Commodity_description.value;
							} else {
								window.parent.frm.as_commodityName.value = frm.Commodity_description.value;
							}
						}
					}

					var openerIclLevelElements = window.parent.frm.as_commodityIclLevel;

					if (openerIclLevelElements != undefined) {
						if (totalRows > 0) {
							var commodityIclLevel = (trim(frm.Commodity_iclLevel[rowSelect]) == "") ? "0" : frm.Commodity_iclLevel[rowSelect].value;
							if (openerIclLevelElements.length > 1) {
								window.parent.frm.as_commodityIclLevel[window.parent.currentRow].value = commodityIclLevel;
							} else {
								window.parent.frm.as_commodityIclLevel.value = commodityIclLevel;
							}
						} else {
							var commodityIclLevel = (trim(frm.Commodity_iclLevel) == "") ? "0" : frm.Commodity_iclLevel.value;
							if (openerIclLevelElements.length > 1) {
								window.parent.frm.as_commodityIclLevel[window.parent.currentRow].value = commodityIclLevel;
							} else {
								window.parent.frm.as_commodityIclLevel.value = commodityIclLevel;
							}
						}
					}
					var setReqTaxable = false;
					var setPOTaxable = false;
				<%	if (propertiesManager.getProperty("REQ OPTIONS", "TAXABLEFROMCOMMODITY", "N").equals("Y")) {%>
						setReqTaxable = true;
				<%	}
					if (propertiesManager.getProperty("PO OPTIONS", "TAXABLEFROMCOMMODITY", "N").equals("Y")) {%>
						setPOTaxable = true;
				<%	}%>
					if (setReqTaxable && window.parent.frm.RequisitionLine_taxable != undefined) {
						var taxableCommodity = frm.Commodity_taxable[rowSelect].value
						if (taxableCommodity == "N") {
							window.parent.frm.c_checkbox[2].checked = false;
							window.parent.frm.c_checkbox[2].fireEvent("onclick");
						} else if (taxableCommodity == "Y") {
							window.parent.frm.c_checkbox[2].checked = true;
							window.parent.frm.c_checkbox[2].fireEvent("onclick");
						}
					} else if (setPOTaxable && window.parent.frm.PoLine_taxable != undefined) {
						var taxableCommodity = frm.Commodity_taxable[rowSelect].value;
						if (taxableCommodity == "N") {
							window.parent.frm.c_checkbox[1].checked = false;
							window.parent.frm.c_checkbox[1].fireEvent("onclick");
						} else if (taxableCommodity == "Y") {
							window.parent.frm.c_checkbox[1].checked = true;
							window.parent.frm.c_checkbox[1].fireEvent("onclick");
						}
					}
				}
			}
			if (window.parent.updateReqLineAccount) {
				window.parent.updateReqLineAccount();
			}
		}
		else if (formField.indexOf("_commodityCode") > 0)
		{
			var columns = frm.elements.item("Commodity_description");
			if (columns.length==undefined)
			{
				if (window.parent.frm.as_authbyName)
				{
					window.parent.frm.as_authbyName.value = frm.UserProfile_displayName.value;
				}
			}
			else
			{
				if (window.parent.frm.as_authbyName)
				{
					window.parent.frm.as_authbyName.value = frm.UserProfile_displayName[rowSelect].value;
				}
			}
		}
		else if (formField == "UserProfile_callForward")
		{
			if (window.parent.frm.as_approverName) {
				if (frm.UserProfile_displayName.length == undefined) {
					window.parent.frm.as_approverName.value = frm.UserProfile_displayName.value;
				} else {
					window.parent.frm.as_approverName.value = frm.UserProfile_displayName[rowSelect].value;
				}
				if (browser != "NS6") {
					window.parent.frm.UserProfile_callForward.onchange();
				}
			}
			window.parent.checkCallForwardOptions();
		}
		else if (formField == "UserProfile_nameUdf5")
		{
			if (window.parent.frm.as_ownerName) {
				window.parent.frm.as_ownerName.value = frm.UserProfile_displayName[rowSelect].value;
			}
			window.parent.checkCallForwardOptions();
		}
		else if (formField == "UserProfile_backupApprover")
		{
			if (window.parent.frm.as_backupApproverName) {
				window.parent.frm.as_backupApproverName.value = frm.UserProfile_displayName[rowSelect].value;
			}
			window.parent.checkBackupApproverOptions();
		}
		else if (formField == "AssetLocation_userId")
		{

			if (window.parent.frm.as_ownerName) {
				window.parent.frm.as_ownerName.value = frm.UserProfile_displayName[rowSelect].value;
			}
			if (window.parent.frm.as_ownerEmail) {
				window.parent.frm.as_ownerEmail.value = frm.UserProfile_mailId[rowSelect].value;
			}
			if (window.parent.frm.as_ownerTelephone) {
				window.parent.frm.as_ownerTelephone.value = frm.UserProfile_phoneNumber[rowSelect].value;
			}
			if (window.parent.frm.as_ownerRouting) {
				window.parent.frm.as_ownerRouting.value = frm.UserProfile_routing[rowSelect].value;
			}
			if (window.parent.frm.as_shipTo) {
				window.parent.frm.as_shipTo.value = frm.UserProfile_shipToCode[rowSelect].value;
			}
		}
		else if (formField == "PerformanceDetail_evaluator")
		{
			var columns = window.parent.frm.elements.item("PerformanceDetail_evaluator");
			if (columns.length==undefined)
			{
				window.parent.frm.as_name.value = frm.UserProfile_displayName[rowSelect].value;
				window.parent.frm.PerformanceDetail_dateAssigned.value = new Date();
				window.parent.frm.PerformanceDetail_assignedBy.value = '${esapi:encodeForJavaScript(userId)}';
				window.parent.frm.PerformanceDetail_status.value = '<%=DocumentStatus.SUP_PERFORMANCE_INPROGRESS%>';
				window.parent.setInnerText("statusText_" + row, "<%=DocumentStatus.toString(DocumentStatus.SUP_PERFORMANCE_INPROGRESS, oid)%>");
			}
			else
			{
				window.parent.frm.as_name[row].value = frm.UserProfile_displayName[rowSelect].value;
				window.parent.frm.PerformanceDetail_dateAssigned[row].value = new Date();
				window.parent.frm.PerformanceDetail_assignedBy[row].value = '${esapi:encodeForJavaScript(userId)}';
				window.parent.frm.PerformanceDetail_status[row].value = '<%=DocumentStatus.SUP_PERFORMANCE_INPROGRESS%>';
				window.parent.setInnerText("statusText_" + row, "<%=DocumentStatus.toString(DocumentStatus.SUP_PERFORMANCE_INPROGRESS, oid)%>");
			}

		}
		else if (formField == "ReportQueue_sendToCode")
		{
			if (window.parent.frm.ReportQueue_sendTo) {
				if (window.parent.frm.ReportQueue_sendTo.value == "") {
					window.parent.frm.ReportQueue_sendTo.value = frm.UserProfile_mailId[rowSelect].value;
				} else  {
					window.parent.frm.ReportQueue_sendTo.value = window.parent.frm.ReportQueue_sendTo.value + ";" + frm.UserProfile_mailId[rowSelect].value;
				}
			}
		}
		else if (formField == "Account_fld4")
		{
				window.parent.setProjectCode(selectedData);
				<% if (browseObject.getBrowseName().equalsIgnoreCase("car_project")) { %>
					if( isArray(window.parent.frm.Account_fld5) && isArray(window.parent.frm.Account_fld6))
					{
						window.parent.frm.Account_fld4[window.parent.currentRow].value = frm.XrefCombo_code3[rowSelect].value;
						window.parent.frm.Account_fld5[window.parent.currentRow].value = frm.XrefCombo_code2[rowSelect].value;
						window.parent.frm.Account_fld6[window.parent.currentRow].value = frm.XrefCombo_code4[rowSelect].value;
					}
					else
					{
						window.parent.frm.Account_fld4.value = frm.XrefCombo_code3[rowSelect].value;
						window.parent.frm.Account_fld5.value = frm.XrefCombo_code2[rowSelect].value;
						window.parent.frm.Account_fld6.value = frm.XrefCombo_code4[rowSelect].value;
					}
				<% } %>
		}
		else if (formField == "sendFrom")
		{
			if (window.parent.frm.as_ownerName) {
				window.parent.frm.as_ownerName.value = frm.UserProfile_displayName[rowSelect].value;
			}
			if (window.parent.frm.as_ownerEmail) {
				window.parent.frm.as_ownerEmail.value = frm.UserProfile_mailId[rowSelect].value;
			}
			if (window.parent.frm.as_ownerTelephone) {
				window.parent.frm.as_ownerTelephone.value = frm.UserProfile_phoneNumber[rowSelect].value;
			}
			if (window.parent.frm.as_ownerRouting) {
				window.parent.frm.as_ownerRouting.value = frm.UserProfile_routing[rowSelect].value;
			}
			if (window.parent.frm.as_shipTo) {
				window.parent.frm.as_shipTo.value = frm.UserProfile_shipToCode[rowSelect].value;
			}
		}
		else if( formField == "PoHeader_linkPriorOrder")
		{
			var PoHeader_icPoHeader;
			var PoHeader_poNumber;
			var PoHeader_releaseNumber;
			var PoHeader_revisionNumber;

			if(frm.PoHeader_icPoHeader.length == null)
			{
				PoHeader_icPoHeader = frm.PoHeader_icPoHeader.value;
				PoHeader_poNumber = frm.PoHeader_poNumber.value;
				PoHeader_releaseNumber = frm.PoHeader_releaseNumber.value;
				PoHeader_revisionNumber = frm.PoHeader_revisionNumber.value;
			}
			else
			{
				PoHeader_icPoHeader = frm.PoHeader_icPoHeader[rowSelect].value;
				PoHeader_poNumber = frm.PoHeader_poNumber[rowSelect].value;
				PoHeader_releaseNumber = frm.PoHeader_releaseNumber[rowSelect].value;
				PoHeader_revisionNumber = frm.PoHeader_revisionNumber[rowSelect].value;
			}

			window.parent.frm.PoHeader_linkPriorOrder_icPoHeader.value = PoHeader_icPoHeader;
			window.parent.frm.PoHeader_linkPriorOrder.value = 'Order '+ PoHeader_poNumber
				+ '-' + PoHeader_releaseNumber
				+ ' Rev ' + PoHeader_revisionNumber;
		}
		else if( formField == "PoHeader_linkNextOrder")
		{
			var PoHeader_icPoHeader;
			var PoHeader_poNumber;
			var PoHeader_releaseNumber;
			var PoHeader_revisionNumber;

			if(frm.PoHeader_icPoHeader.length == null)
			{
				PoHeader_icPoHeader = frm.PoHeader_icPoHeader.value;
				PoHeader_poNumber = frm.PoHeader_poNumber.value;
				PoHeader_releaseNumber = frm.PoHeader_releaseNumber.value;
				PoHeader_revisionNumber = frm.PoHeader_revisionNumber.value;
			}
			else
			{
				PoHeader_icPoHeader = frm.PoHeader_icPoHeader[rowSelect].value;
				PoHeader_poNumber = frm.PoHeader_poNumber[rowSelect].value;
				PoHeader_releaseNumber = frm.PoHeader_releaseNumber[rowSelect].value;
				PoHeader_revisionNumber = frm.PoHeader_revisionNumber[rowSelect].value;
			}

			window.parent.frm.PoHeader_linkNextOrder_icPoHeader.value = PoHeader_icPoHeader;
			window.parent.frm.PoHeader_linkNextOrder.value = 'Order '+ PoHeader_poNumber
				+ '-' + PoHeader_releaseNumber
				+ ' Rev ' + PoHeader_revisionNumber;
		}
		else if( formField == "PoLine_requisitionNumber")
		{
			window.parent.frm.PoLine_icReqLine.value = frm.RequisitionLine_icReqLine[rowSelect].value;
		}
		else if (formField == "RequisitionHeader_departmentCode")
		{
			if (window.parent.frm.RequisitionHeader_buyer && frm.DepartmentBuyer_id_userId) {
				window.parent.frm.RequisitionHeader_buyer.value = frm.DepartmentBuyer_id_userId[rowSelect].value;
			}
			if ("<%=propertiesManager.getProperty("REQ OPTIONS", "UPDATEALLDEPARTMENTCODE", "N")%>" == "Y")
			{
				window.parent.updateDepartmentCode();
			}
		}
		else if(formField=="InvoiceHeader_vendorAddrCode") {
			window.parent.frm.InvoiceAddress_addressLine1.value = frm.Address_addressLine1[rowSelect].value;
			window.parent.frm.InvoiceAddress_addressLine2.value = frm.Address_addressLine2[rowSelect].value;
			window.parent.frm.InvoiceAddress_addressLine3.value = frm.Address_addressLine3[rowSelect].value;
			window.parent.frm.InvoiceAddress_addressLine4.value = frm.Address_addressLine4[rowSelect].value;
			window.parent.frm.InvoiceAddress_city.value = frm.Address_city[rowSelect].value;
			window.parent.frm.InvoiceAddress_state.value = frm.Address_state[rowSelect].value;
			window.parent.frm.InvoiceAddress_postalCode.value = frm.Address_postalCode[rowSelect].value;
			window.parent.frm.InvoiceAddress_country.value = frm.Address_country[rowSelect].value;
		}
		else if(formField=="InvoiceAddress_addressLine1") {
			if (rowSelect > 0) {
				window.parent.frm.InvoiceAddress_addressLine1.value = frm.Address_addressLine1[rowSelect].value;
				window.parent.frm.InvoiceAddress_addressLine2.value = frm.Address_addressLine2[rowSelect].value;
				window.parent.frm.InvoiceAddress_addressLine3.value = frm.Address_addressLine3[rowSelect].value;
				window.parent.frm.InvoiceAddress_addressLine4.value = frm.Address_addressLine4[rowSelect].value;
				window.parent.frm.InvoiceAddress_city.value = frm.Address_city[rowSelect].value;
				window.parent.frm.InvoiceAddress_state.value = frm.Address_state[rowSelect].value;
				window.parent.frm.InvoiceAddress_postalCode.value = frm.Address_postalCode[rowSelect].value;
				window.parent.frm.InvoiceAddress_country.value = frm.Address_country[rowSelect].value;
			} else {
				window.parent.frm.InvoiceAddress_addressLine1.value = frm.Address_addressLine1.value;
				window.parent.frm.InvoiceAddress_addressLine2.value = frm.Address_addressLine2.value;
				window.parent.frm.InvoiceAddress_addressLine3.value = frm.Address_addressLine3.value;
				window.parent.frm.InvoiceAddress_addressLine4.value = frm.Address_addressLine4.value;
				window.parent.frm.InvoiceAddress_city.value = frm.Address_city.value;
				window.parent.frm.InvoiceAddress_state.value = frm.Address_state.value;
				window.parent.frm.InvoiceAddress_postalCode.value = frm.Address_postalCode.value;
				window.parent.frm.InvoiceAddress_country.value = frm.Address_country.value;
			}
		}
		else if (formField == "AppRulesExt_routto")
		{
			if (window.parent.frm.as_approverName) {
				window.parent.frm.as_approverName[window.parent.currentRow].value = frm.UserProfile_displayName[rowSelect].value;
			}
		}
		else if (formField == "Account_fld5")
		{
			if (frm.XrefCombo_xrefAmt)
			{
				if (frm.XrefCombo_xrefAmt.lenght > 1)
				{
					window.parent.frm.Account_fld1.value = frm.XrefCombo_xrefAmt[rowSelect].value;
				}
				else
				{
					 window.parent.frm.Account_fld1.value = frm.XrefCombo_xrefAmt.value;
				}
			}
		}
		else if (formField == "Account_fld1")
		{
			if (browseName == "glaccount_xref") {
				if (row == 0) {
					if (rowSelect == 0) {
						window.parent.frm.Account_fld2.value = frm.XrefCombo_code2.value;
						window.parent.frm.Account_fld3.value = frm.XrefCombo_code3.value;
						window.parent.frm.Account_fld4.value = frm.XrefCombo_code4.value;
					} else {
						window.parent.frm.Account_fld2.value = frm.XrefCombo_code2[rowSelect].value;
						window.parent.frm.Account_fld3.value = frm.XrefCombo_code3[rowSelect].value;
						window.parent.frm.Account_fld4.value = frm.XrefCombo_code4[rowSelect].value;
					}
				} else {
					if (rowSelect == 0) {
						window.parent.frm.Account_fld2[row].value = frm.XrefCombo_code2.value;
						window.parent.frm.Account_fld3[row].value = frm.XrefCombo_code3.value;
						window.parent.frm.Account_fld4[row].value = frm.XrefCombo_code4.value;
					} else {
						window.parent.frm.Account_fld2[row].value = frm.XrefCombo_code2[rowSelect].value;
						window.parent.frm.Account_fld3[row].value = frm.XrefCombo_code3[rowSelect].value;
						window.parent.frm.Account_fld4[row].value = frm.XrefCombo_code4[rowSelect].value;
					}
				}
			}
		}
		else if (formField == "CatalogSecurity_accessId")
		{
			if (browseName == 'user' || browseName == 'requisitioner')
			{
				if (window.parent.frm.CatalogSecurity_accessDescription && frm.UserProfile_displayName)
				{
					if (isArray(window.parent.frm.CatalogSecurity_accessDescription)) {
						window.parent.frm.CatalogSecurity_accessDescription[window.parent.currentRow].value = frm.UserProfile_displayName[rowSelect].value;
					} else {
						window.parent.frm.CatalogSecurity_accessDescription.value = frm.UserProfile_displayName[rowSelect].value;
					}
				}
			}
			if (browseName == 'department')
			{
				if (window.parent.frm.CatalogSecurity_accessDescription && frm.Department_departmentName)
				{
					if (isArray(window.parent.frm.CatalogSecurity_accessDescription)) {
						window.parent.frm.CatalogSecurity_accessDescription[window.parent.currentRow].value = frm.Department_departmentName[rowSelect].value;
					} else {
						window.parent.frm.CatalogSecurity_accessDescription.value = frm.Department_departmentName[rowSelect].value;
					}
				}
			}
			if (browseName == 'ship_to')
			{
				if (window.parent.frm.CatalogSecurity_accessDescription && frm.Address_addressLine1)
				{
					if (isArray(window.parent.frm.CatalogSecurity_accessDescription)) {
						window.parent.frm.CatalogSecurity_accessDescription[window.parent.currentRow].value = frm.Address_addressLine1[rowSelect].value;
					} else {
						window.parent.frm.CatalogSecurity_accessDescription.value = frm.Address_addressLine1[rowSelect].value;
					}
				}
			}
		}
		else if (formField == "RequisitionHeader_udf11Code")
		{
			if ("<%=propertiesManager.getProperty("REQ OPTIONS", "UDF11CODE SEL DESC", "N")%>" == "Y") {
				if (window.parent.frm.RequisitionHeader_udf11Code && frm.StdTable_description[rowSelect]) {
					window.parent.frm.RequisitionHeader_udf11Code.value = frm.StdTable_description[rowSelect].value;
				}
			}
		}
		else if (formField == "PoHeader_udf11Code")
		{
			if ("<%=propertiesManager.getProperty("PO OPTIONS", "UDF11CODE SEL DESC", "N")%>" == "Y") {
				if (window.parent.frm.PoHeader_udf11Code && frm.StdTable_description[rowSelect]) {
					window.parent.frm.PoHeader_udf11Code.value = frm.StdTable_description[rowSelect].value;
				}
			}
		}
		else if (formField == "InvReturn_itemNumber")
		{
			if (frm.InvItem_itemNumber)
			{
				if (frm.InvItem_itemNumber.length == undefined) {
					if (window.parent.createInvReturnOtc && frm.InvItem_itemNumber) {
						window.parent.createInvReturnOtc(frm.InvItem_itemNumber.value);
					}
				} else {
					if (window.parent.createInvReturnOtc && frm.InvItem_itemNumber) {
						window.parent.createInvReturnOtc(frm.InvItem_itemNumber[rowSelect].value);
					}
				}
			}
		}
		else if (formField == "InvReturn_itemLocation")
		{
			if (frm.InvLocation_id_itemLocation)
			{
				if (frm.InvLocation_id_itemLocation.length == undefined) {
					if (window.parent.frm.InvBinLocation_itemLocation) {
						window.parent.frm.InvBinLocation_itemLocation.value = frm.InvLocation_id_itemLocation.value;
					}
					if (window.parent.frm.InvLocation_itemLocation) {
						window.parent.frm.InvLocation_itemLocation.value = frm.InvLocation_id_itemLocation.value;
					}
				} else {
					if (window.parent.frm.InvBinLocation_itemLocation) {
						window.parent.frm.InvBinLocation_itemLocation.value = frm.InvLocation_id_itemLocation[rowSelect].value;
					}
					if (window.parent.frm.InvLocation_itemLocation) {
						window.parent.frm.InvLocation_itemLocation.value = frm.InvLocation_id_itemLocation[rowSelect].value;
					}
				}
			}
		}
		else if (formField.indexOf("InvBinLocation_udf3Code") >= 0)
		{
			var columns = frm.elements.item("RequisitionHeader_icReqHeader");
			if (columns.length==undefined)
			{
				window.parent.frm.RequisitionHeader_icReqHeader.value = frm.RequisitionHeader_icReqHeader.value;
			}
			else
			{
				window.parent.frm.RequisitionHeader_icReqHeader.value = frm.RequisitionHeader_icReqHeader[rowSelect].value;
			}
		}
		else {
			if (browser == "IE") {
				if (window.parent.frm.elements(formField).onchange) {
					window.parent.frm.elements(formField).fireEvent("onchange");
				}
			}
		}
		<% if(browseObject.getBrowseName().equalsIgnoreCase("mxp-warehouse")) { %>
			if(formField=="RequisitionLine_udf5Code") {
				window.parent.frm.accountFLD2.value = frm.XrefCombo_code4[rowSelect].value;
				updateAccount();
			}
		<% } %>
	}

	function setAssetOrder(poNumber, icPoHeader)
	{
		window.parent.frm.Asset_purchaseOrder.value = poNumber;
		doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');
	}

	function setAssetCostOrder(poNumber, icPoHeader)
	{
		window.parent.frm.AssetCost_poNumber.value = poNumber;
		doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');
	}

	function setCatalogOrder(poNumber, icPoHeader, vendorId)
	{
		var columns = frm.elements.item("PoHeader_vendorId");
		var poVendorId = "";

		if (columns.length==undefined){
			poVendorId = frm.PoHeader_vendorId.value;
		} else {
			poVendorId = frm.PoHeader_vendorId[rowSelect].value;
		}

		if (!isEmpty(window.parent.frm.as_vendorId.value))
		{
			if (window.parent.frm.as_vendorId.value != vendorId)
			{
				if (confirm("Selecting this Order will associate this catalog with a different vendor.  Continue?"))
				{
					window.parent.frm.Catalog_icPoHeader.value = icPoHeader;
					window.parent.frm.Catalog_poNumber.value = poNumber;
					window.parent.frm.Catalog_vendorId.value = poVendorId;
					window.parent.frm.as_vendorId.value = poVendorId;
				}
			}
			else
			{
				window.parent.frm.Catalog_icPoHeader.value = icPoHeader;
				window.parent.frm.Catalog_poNumber.value = poNumber;
				window.parent.frm.Catalog_vendorId.value = poVendorId;
				window.parent.frm.as_vendorId.value = poVendorId;
			}
		}
		else
		{
			window.parent.frm.Catalog_icPoHeader.value = icPoHeader;
			window.parent.frm.Catalog_poNumber.value = poNumber;
			window.parent.frm.Catalog_vendorId.value = poVendorId;
			window.parent.frm.as_vendorId.value = poVendorId;
		}

		doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');
//		window.top.hidePopWin();
	}

	function browse(x) {
		frm.browseName.value = x;

		doSubmit('/browse/browse_popup.jsp', 'BrowseRetrieve');
	}

	function cancelAddApprover()
	{
		<%if(request.getAttribute("RequisitionHeader_icReqHeader") instanceof String){%>
		frm.ApprovalLog_icHeader.value = "<%=(String) request.getAttribute("RequisitionHeader_icReqHeader")%>";
		frm.RequisitionHeader_icReqHeader.value = "<%=(String) request.getAttribute("RequisitionHeader_icReqHeader")%>";

		doSubmit('/requests/req_routinglist.jsp', 'ApprovalLogRetrieveByHeader');
		<%}%>
	}

	function viewPoPreview(ic)
	{
		var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";
		setHiddenFields(newInputField);

		doSubmit('/orders/po_preview.jsp', 'PoRetrieve');
	}

	function viewBudget(budgetId)
	{
		var newInputField = "<input type='hidden' name='BudgetCenter_budgetId' value='" + budgetId + "'>";
		setHiddenFields(newInputField);
		doSubmit("/budget/budget_review.jsp", "BudgetCenterRetrieveById");
	}

	function updateAccount()
	{
		var accountString = "";

		if( window.parent.document.getElementById("accountFLD2").value != "" )
		{
			if ( window.parent.document.getElementById("accFld1")!=null &&  window.parent.document.getElementById("accFld2")!=null &&
				 window.parent.document.getElementById("accFld3")!=null )
			{
				window.parent.document.getElementById("accFld2").value = window.parent.document.getElementById("accountFLD2").value ;
				accountString = window.parent.document.getElementById("accFld1").value + "/" +
							window.parent.document.getElementById("accFld2").value + "/" +
							window.parent.document.getElementById("accFld3").value;

				window.parent.document.getElementById("accountLine").innerHTML = accountString;
			}
		}

	}

	function addSubContractorAffiliates() {
		frm.refreshOpener.value = "Y";
		var myCell = document.getElementById("hiddenFields");
		var checkboxes = document.all("c_checkbox");
		var ids = document.all("Contact_id_vendorId");
		var newInputField = "";
		var isAtLeastOneSelected = false;
		if (checkboxes.length > 1)
		{
			for (var i = 0; i < checkboxes.length; i++)
			{
				var cbox = checkboxes[i];
				if (cbox.checked == true)
				{
					var id = ids[i].value;
					newInputField =  newInputField + "<INPUT TYPE=\"HIDDEN\" NAME=\"SubContractor_affiliateId\" value=\"" + id + "\">";
					isAtLeastOneSelected = true;
				}
			}
		}
		else
		{
			var cbox = checkboxes;
			if (cbox.checked == true)
			{
				var id = ids.value;
				newInputField =  newInputField + "<INPUT TYPE=\"HIDDEN\" NAME=\"SubContractor_affiliateId\" value=\"" + id + "\">";
				isAtLeastOneSelected = true;
			}
		}

		if (isAtLeastOneSelected)
		{
			newInputField = newInputField + "<input type='hidden' name='PoHeader_icPoHeader' value='<%=HiltonUtility.ckNull(request.getAttribute("PoHeader_icPoHeader") != null ? ((String[]) request.getAttribute("PoHeader_icPoHeader"))[0] : "")%>'>";
			newInputField = newInputField + "<input type='hidden' name='PoHeader_poNumber' value='<%=HiltonUtility.ckNull(request.getAttribute("PoHeader_poNumber") != null ? ((String[]) request.getAttribute("PoHeader_poNumber"))[0] : "")%>'>";
			newInputField = newInputField + "<input type='hidden' name='PoHeader_releaseNumber' value='<%=HiltonUtility.ckNull(request.getAttribute("PoHeader_releaseNumber") != null ? ((String[]) request.getAttribute("PoHeader_releaseNumber"))[0] : "")%>'>";
			setHiddenFields(getHiddenFields() + newInputField);

			doSubmit('/orders/po_sub_contractor.jsp', 'BrowseSetInputRequestValues;SubContractorAddFromAffiliateList;');
		}
		else
		{
			alert("Please select at least one affiliate for this contract!");
		}
	}

	hideArea("pageLoading");
	displayArea("pageForm");
	
	function getFieldsJquery()
	{
		return undefined;
    }

// End Hide script -->
</SCRIPT>