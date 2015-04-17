<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/browse/browse_form.jsp" %>

<tsa:hidden name="browsePage" value="/browse/browse_std_attachment.jsp"/>
<%
	String	s_ic_rec_header = HiltonUtility.ckNull((String)request.getAttribute("ReceiptHeader_icRecHeader"));
	String	s_ic_rec_line = (String)request.getAttribute("ReceiptLine_icRecLine");
	String	s_rec_number = (String)request.getAttribute("ReceiptHeader_receiptNumber");
	String	s_rec_type = (String)request.getAttribute("ReceiptHeader_receiptType");
	String	s_rec_status = (String)request.getAttribute("ReceiptHeader_receiptStatus");
	String	s_rec_fiscal_year = (String)request.getAttribute("ReceiptHeader_fiscalYear");
	if (HiltonUtility.isEmpty(s_rec_number)) {
		s_rec_number = "N/A";
	}
	if (HiltonUtility.isEmpty(s_rec_status)) {
		s_rec_status = DocumentStatus.RCV_INPROGRESS;
	}

	String	s_ic_po_header = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_icPoHeader"));
	String	s_po_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_poNumber"));
	String	s_revision_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_revisionNumber"));
	String	s_release_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_releaseNumber"));
	if (HiltonUtility.isEmpty(s_revision_number)) {
		s_revision_number = "0";
	}
	if (HiltonUtility.isEmpty(s_release_number)) {
		s_release_number = "0";
	}
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);

	String	s_item_number = (String) request.getAttribute("InvItem_itemNumber");
	String	inv_status = (String) request.getAttribute("InvItem_status");
	String	s_inv_type = (String) request.getAttribute("InvItem_itemType");

	String	receiptMethod = HiltonUtility.ckNull((String)request.getAttribute("receiptMethod"));

	String	s_attachment_type = HiltonUtility.ckNull((String) request.getAttribute("Default_referenceType"));
	String	s_ic_header = (String) request.getAttribute("DocAttachment_icHeader");
	String	s_ic_line = (String) request.getAttribute("DocAttachment_icLine");
	String	s_form_number = (String) request.getAttribute("formNumber");
	String	s_form_status = (String) request.getAttribute("formStatus");
	String	s_fiscal_year = HiltonUtility.ckNull((String) request.getAttribute("fiscalYear"));
	String	s_from_page = HiltonUtility.ckNull((String) request.getAttribute("frompage"));
	String	s_item_location = "";
	String	s_buyer_code = "";
	String	s_line_number = "";
	String	s_line_count = "";
	String	s_amendment_number = "";
	String	formtypeName = "";
	String	subFormtypeName = "";

	String	s_icInspNo = (String) request.getAttribute("InspectionHeader_icInspNo");
	String	s_icMsrLine = (String) request.getAttribute("InspectionHeader_icMsrLine");
	String	s_inspectionStatus = (String) request.getAttribute("InspectionHeader_status");

	if (s_attachment_type.equalsIgnoreCase("RQH") || s_attachment_type.equalsIgnoreCase("RQL")) {
		formtypeName = "RequisitionHeader_requisitionType";
		subFormtypeName = "RequisitionHeader_rqSubType";
	} else if (s_attachment_type.equals("RFH") || s_attachment_type.equalsIgnoreCase("RFL")) {
		formtypeName = "RfqHeader_rfqType";
	} else if (s_attachment_type.equals("POH") || s_attachment_type.equalsIgnoreCase("POL")) {
		formtypeName = "PoHeader_poType";
	} else if (s_attachment_type.equals("DBH")) {
		formtypeName = "DisbHeader_disbType";
	} else if (s_attachment_type.equals("SLH")) {
		formtypeName = "SaleHeader_saleType";
	} else if (s_attachment_type.equals("INV")) {
		formtypeName = "InvItem_itemType";
	} else if (s_attachment_type.equals("IVH")) {
		formtypeName = "InvoiceHeader_invoiceType";
	} else if (s_attachment_type.equals("RCH")) {
		formtypeName = "ReceiptHeader_receiptType";
	}

	String	formtype = "";
	String	subFormtype = "";

	if (!HiltonUtility.isEmpty(formtypeName)) {
		formtype = HiltonUtility.ckNull((String) request.getAttribute(formtypeName));
	}
	if (!HiltonUtility.isEmpty(subFormtypeName)) {
		subFormtype = HiltonUtility.ckNull((String) request.getAttribute(subFormtypeName));
	}

	String s_ic_req_header = HiltonUtility.ckNull((String) request.getAttribute("RequisitionLine_icReqHeader"));
	String s_ic_req_line = HiltonUtility.ckNull((String) request.getAttribute("RequisitionLine_icReqLine"));
	String s_ic_req_line_old = HiltonUtility.ckNull((String) request.getAttribute("RequisitionLine_icReqLineOld"));
	s_line_number = HiltonUtility.ckNull((String) request.getAttribute("RequisitionLine_lineNumber"));
	String s_req_status = HiltonUtility.ckNull((String) request.getAttribute("RequisitionLine_status"));
	String s_lineCount = HiltonUtility.ckNull((String) request.getAttribute("lineCount"));
	String icHeaderEdit = HiltonUtility.ckNull((String) request.getAttribute("icHeaderEdit"));
	String icHeaderHistoryEdit = HiltonUtility.ckNull((String) request.getAttribute("icHeaderHistoryEdit"));

	String bd_ic_po_line = HiltonUtility.ckNull((String) request.getAttribute("PoLine_icPoLine"));
	String bd_ic_po_line_key = HiltonUtility.ckNull((String) request.getAttribute("PoLine_icRelKey"));
	String bd_ic_po_line_req = HiltonUtility.ckNull((String) request.getAttribute("PoLine_icReqLine"));
	String bd_ic_po_line_rfq = HiltonUtility.ckNull((String) request.getAttribute("PoLine_icRfqLine"));
	String bd_line_number = HiltonUtility.ckNull((String) request.getAttribute("PoLine_lineNumber"));
	String s_po_line_status = HiltonUtility.ckNull((String) request.getAttribute("PoLine_status"));

	String bd_ic_rfq_line = HiltonUtility.ckNull((String) request.getAttribute("RfqLine_icRfqLine"));
	String bd_ic_rfq_line_rfq = HiltonUtility.ckNull((String) request.getAttribute("RfqLine_icRfqLine"));
	String bd_item_number = HiltonUtility.ckNull((String) request.getAttribute("RfqLine_itemNumber"));
	String itemAction = HiltonUtility.ckNull((String) request.getAttribute("itemAction"));
	String actionStep = HiltonUtility.ckNull((String)request.getAttribute("actionStep"));

	if (HiltonUtility.isEmpty(s_po_number))
	{
		s_po_number = HiltonUtility.ckNull((String) request.getAttribute("InvItem_poNumber"));
	}
%>

<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=s_ic_req_header%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value="<%=s_ic_req_line%>"/>
<tsa:hidden name="RequisitionLine_icReqLineOld" value="<%=s_ic_req_line_old%>"/>
<tsa:hidden name="RequisitionLine_lineNumber" value="<%=s_line_number%>"/>
<tsa:hidden name="RequisitionLine_status" value="<%=s_req_status%>"/>
<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="ReceiptLine_icRecLine" value="<%=s_ic_rec_line%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=s_rec_number%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=s_rec_status%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=s_rec_fiscal_year%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RCH"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="formType" value="<%=formtype%>"/>


<tsa:hidden name="InvItem_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="InvItem_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="InvItem_status" value="<%=inv_status%>"/>
<tsa:hidden name="InvItem_itemType" value="<%=s_inv_type%>"/>

<tsa:hidden name="Default_referenceType" value="<%=s_attachment_type%>"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_ic_header%>"/>
<tsa:hidden name="DocAttachment_icLine" value="<%=s_ic_line%>"/>
<tsa:hidden name="formNumber" value="<%=s_form_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_form_status%>"/>
<tsa:hidden name="fiscalYear" value="<%=s_fiscal_year%>"/>
<%	if (!HiltonUtility.isEmpty(formtypeName) && s_attachment_type.indexOf("INV") < 0 && s_attachment_type.indexOf("PO") < 0 && !HiltonUtility.isEmpty(formtype)){ %>
<tsa:hidden name="<%=formtypeName%>" value="<%=formtype%>"/>
<%	}%>
<%	if (!HiltonUtility.isEmpty(subFormtypeName)) { %>
<tsa:hidden name="<%=subFormtypeName%>" value="<%=subFormtype%>"/>
<%	}%>
<tsa:hidden name="lineNumber" value=""/>
<tsa:hidden name="lineCount" value="<%=s_lineCount%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value=""/>
<tsa:hidden name="RfqHeader_rfqAmendment" value=""/>
<tsa:hidden name="PoHeader_itemLocation" value=""/>
<tsa:hidden name="PoHeader_buyerCode" value=""/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>

<tsa:hidden name="InspectionHeader_icInspNo" value="<%=s_icInspNo%>"/>
<tsa:hidden name="InspectionHeader_icMsrLine" value="<%=s_icMsrLine%>"/>
<tsa:hidden name="InspectionHeader_status" value="<%=s_inspectionStatus%>"/>

<tsa:hidden name="icHeaderEdit" value="<%=icHeaderEdit%>"/>
<tsa:hidden name="icHeaderHistoryEdit" value="<%=icHeaderHistoryEdit%>"/>

<input type=hidden name=PoLine_icPoLine value="<%=bd_ic_po_line%>">
<input type=hidden name=PoLine_icRelKey value="<%=bd_ic_po_line_key%>">
<input type=hidden name=PoLine_icReqLine value="<%=bd_ic_po_line_req%>">
<input type=hidden name=PoLine_icReqLineOld value="<%=bd_ic_po_line_req%>">
<input type=hidden name=PoLine_icRfqLine value="<%=bd_ic_po_line_rfq%>">
<input type=hidden name=PoLine_lineNumber value="<%=bd_line_number%>">
<input type=hidden name=PoLine_status value="<%=s_po_line_status%>">

<input type=hidden name=RfqLine_icRfqLine value="<%=bd_ic_rfq_line%>">
<input type=hidden name=RfqLine_icReqLineOld value="<%=bd_ic_rfq_line_rfq%>">
<input type=hidden name=RfqLine_itemNumber value="<%=bd_item_number%>">

<tsa:hidden name="itemAction" value="<%=itemAction%>"/>
<input type=hidden name="actionStep" value="<%=actionStep%>">

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formWidth%>>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: addStdAttachment(); void(0);">Add</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: returnAbort(); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	hideArea("filterTextDisplay");
	hideArea("resetOriginalOption");

	function addStdAttachment() {
		var isSelected = false;

		if (rowCount > 1) {
			for (i = 0; i < rowCount; i++) {
				if (frm.c_checkbox && frm.c_checkbox[i] && frm.Input_checkbox[i]) {
					if (frm.c_checkbox[i].checked) {
						isSelected = true;
					}
				}
			}
		} else {
			if (frm.c_checkbox && frm.Input_checkbox) {
				if (frm.c_checkbox.checked) {
					isSelected = true;
				}
			}
		}

		if (isSelected == true) {
<%	String additionalProcess = "";
	if (s_attachment_type.equals("RQL")) { %>
			pageName = "/requests/req_line_attachments.jsp";
<%	} else if (s_attachment_type.equals("RQH")) { %>
			pageName = "/requests/req_attachments.jsp";
<%	} else if (s_attachment_type.equals("POL")) { %>
			pageName =  "/orders/po_line_attachments.jsp";
<%	} else if (s_attachment_type.equals("POH")) {
			additionalProcess = "PoHeaderRetrieveById;"; %>
			pageName =  "/orders/po_attachments.jsp";
<%	} else if (s_attachment_type.equals("RFL")) { %>
			pageName =  "/rfq/rfq_line_attachments.jsp";
<%	} else if (s_attachment_type.equals("INV")) {
			additionalProcess = "InvItemRetrieveById;"; %>
			pageName =  "/inventory/inv_line_attachments.jsp";
<%	} else if (s_attachment_type.equals("RFH")) { %>
			pageName =  "/rfq/rfq_attachments.jsp";
<%	} else if (s_attachment_type.equals("DBL")) { %>
			pageName =  "/inventory/dsb_attachments_ln.jsp";
<%	} else if (s_attachment_type.equals("DBH")) { %>
			pageName =  "/inventory/dsb_attachments.jsp";
<%	} else if (s_attachment_type.equals("SLH")) { %>
			pageName =  "/sales/sale_attachments.jsp";
<%	} else if (s_attachment_type.equals("IVH")) { %>
			pageName =  "/invoice/iv_attachments.jsp";
<%	} else if (s_attachment_type.equals("RCH")) { %>
			pageName =  "/receipts/rec_attachment.jsp";
<%	} else if (s_attachment_type.equals("INH")) { %>
			pageName =  "/receipts/rec_ins_attachments.jsp";
<%	} %>
			doSubmit(pageName, '<%= additionalProcess %>BrowseSetInputRequestValues;DocAttachmentAddStandard;DocAttachmentRetrieveByLine');
		}
		else {
			alert("You have not selected any attachments!");
		}
	}

	function returnAbort() {
		var pageName = "";

<%	String additionalProcess2 = "";
if (s_attachment_type.equals("RQH")) { %>
			pageName = "/requests/req_attachments.jsp";
<%	} else if (s_attachment_type.equals("RQL")) { %>
			pageName = "/requests/req_line_attachments.jsp";
<%	} else if (s_attachment_type.equals("POH")) {
			additionalProcess2 = "PoHeaderRetrieveById;"; %>
			pageName =  "/orders/po_attachments.jsp";
<%	} else if (s_attachment_type.equals("POL")) { %>
			pageName =  "/orders/po_line_attachments.jsp";
<%	} else if (s_attachment_type.equals("INV")) {
			additionalProcess2 = "InvItemRetrieveById;"; %>
			pageName =  "/inventory/inv_line_attachments.jsp";
<%	} else if (s_attachment_type.equals("RFH")) { %>
			pageName =  "/rfq/rfq_attachments.jsp";
<%	} else if (s_attachment_type.equals("RFL")) { %>
			pageName =  "/rfq/rfq_line_attachments.jsp";
<%	} else if (s_attachment_type.equals("DBH")) { %>
			pageName =  "/inventory/dsb_attachments.jsp";
<%	} else if (s_attachment_type.equals("SLH")) { %>
			pageName =  "/sales/sale_attachments.jsp";
<%	} else if (s_attachment_type.equals("IVH")) { %>
			pageName =  "/invoice/iv_attachments.jsp";
<%	} else if (s_attachment_type.equals("RCH")) { %>
			pageName =  "/receipts/rec_attachment.jsp";
<%	} else if (s_attachment_type.equals("INH")) { %>
			pageName =  "/receipts/rec_ins_attachments.jsp";
<%	} %>

		doSubmit(pageName, '<%= additionalProcess2 %>DocAttachmentRetrieveByLine');
	}

	function thisLoad() {
		f_StartIt();
<%	if (s_attachment_type.indexOf("RF") >= 0) {
			s_amendment_number = HiltonUtility.ckNull((String) request.getAttribute("RfqHeader_rfqAmendment"));
%>
		frm.RfqHeader_rfqAmendment.value = "<%=s_amendment_number%>";
<%	}
		if (s_attachment_type.indexOf("PO") >= 0) {
			s_release_number = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_releaseNumber"));
			s_revision_number = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_revisionNumber"));
			s_item_location = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_itemLocation"));
			s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_buyerCode"));
%>
		frm.PoHeader_releaseNumber.value = "<%=s_release_number%>";
		frm.PoHeader_revisionNumber.value = "<%=s_revision_number%>";
		frm.PoHeader_itemLocation.value = "<%=s_item_location%>";
		frm.PoHeader_buyerCode.value = "<%=s_buyer_code%>";
<%	} %>
	}

// End Hide script -->
</SCRIPT>