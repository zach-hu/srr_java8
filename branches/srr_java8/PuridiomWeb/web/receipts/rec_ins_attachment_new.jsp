<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	
	BigDecimal	bd_zero = new BigDecimal(0);
	
	String	s_ic_rec_header = (String)request.getAttribute("ReceiptHeader_icRecHeader");
	String	s_rec_number = (String)request.getAttribute("ReceiptHeader_receiptNumber");
	String	s_rec_type = (String)request.getAttribute("ReceiptHeader_receiptType");
	String	s_rec_status = (String)request.getAttribute("ReceiptHeader_receiptStatus");
	String	s_rec_fiscal_year = (String)request.getAttribute("ReceiptHeader_fiscalYear");
	
	
	if (HiltonUtility.isEmpty(s_rec_status)) {
		s_rec_status = DocumentStatus.RCV_INPROGRESS;
	}
	
	String	s_doc_ic_header = (String) request.getAttribute("DocAttachment_icHeader");
	String	s_ic_po_header = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_icPoHeader"));
	String	s_ic_req_header = HiltonUtility.ckNull((String)request.getAttribute("RequisitionHeader_icReqHeader"));
	String	s_req_number = HiltonUtility.ckNull((String)request.getAttribute("RequisitionHeader_requisitionNumber"));
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
	
	String	receiptMethod = HiltonUtility.ckNull((String)request.getAttribute("receiptMethod"));

	String	s_line_count = (String) request.getAttribute("lineCount");
	String	return_page = "";

	boolean editMode = false;

	String	s_icInspNo = (String) request.getAttribute("InspectionHeader_icInspNo");
	String	s_icMsrLine = (String) request.getAttribute("InspectionHeader_icMsrLine");
	String	s_inspectionStatus = (String) request.getAttribute("InspectionHeader_status");


	String	icHeaderEdit = "";
	if(request.getAttribute("icHeaderEdit")!=null){icHeaderEdit = (String)request.getAttribute("icHeaderEdit");}
	String	icHeaderHistoryEdit = "";
	if(request.getAttribute("icHeaderHistoryEdit")!=null){icHeaderHistoryEdit = (String)request.getAttribute("icHeaderHistoryEdit");}

	String editAttachmentsAccess = propertiesManager.getProperty("EDITATTACHMENTS","ENABLED","N");
	String	displayPrintOption = "N";//propertiesManager.getProperty("ATTACHMENTS", "DisplayPrintOption", "Y");

	boolean editAttachmentsAccessBool = false;

	if (editAttachmentsAccess.equalsIgnoreCase("Y")) {
		editAttachmentsAccessBool = true;
	}
	if (s_req_number == null) {
		s_rec_number = (String) request.getAttribute("formNumber");
	}
	if (s_ic_rec_header == null) {
		s_ic_rec_header = (String) request.getAttribute("DocAttachment_icHeader");
	}
	if (s_rec_status == null) {
		s_rec_status = (String) request.getAttribute("formStatus");
	}

//	if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0) {
//		editMode = true;
//	}

	return_page = "/requests/inspection_detail.jsp";

	String	s_current_process = "HEADER_ATTACHMENTS";
	String	s_current_page = "/receipts/rec_ins_attachments.jsp";
	String	s_current_method = "DocAttachmentUpdateByHeader";
	String	s_current_process_method = "";
%>
<input type=hidden name="ReceiptHeader_icRecHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="ReceiptHeader_receiptNumber" value="<%=s_rec_number%>">
<input type=hidden name="ReceiptHeader_receiptType" value="<%=s_rec_type%>">
<input type=hidden name="ReceiptHeader_receiptStatus" value="<%=s_rec_status%>">
<input type=hidden name="ReceiptHeader_fiscalYear" value="<%=s_rec_fiscal_year%>">
<input type=hidden name="ReceiptLine_icRecHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>">
<input type=hidden name="PoHeader_poNumber" value="<%=s_po_number%>">
<input type=hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>">
<input type=hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>">
<input type=hidden name="PoLine_icPoHeader" value="<%=s_ic_po_header%>">
<input type=hidden name="RequisitionHeader_icReqHeader" value="<%=s_ic_req_header%>">
<input type=hidden name="RequisitionLine_icReqHeader" value="<%=s_ic_req_header%>">
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>

<input type=hidden name="ReceiptLine_icRecLine" value="<%=request.getAttribute("ReceiptLine_icRecLine")%>">
<input type=hidden name="ReceiptHeader_shipToInv" value="<%=request.getAttribute("ReceiptHeader_shipToInv")%>">
<input type=hidden name="InvBinLocation_tempIc" value="<%=request.getAttribute("InvBinLocation_tempIc")%>">
<input type=hidden name="ReceiptHeader_icPoHeader" value="<%=request.getAttribute("ReceiptHeader_icPoHeader")%>">
<input type=hidden name="ReceiptLine_receiptLine" value="<%=request.getAttribute("ReceiptLine_receiptLine") %>">
<input type=hidden name="lineCount" value="<%=s_line_count%>">
<input type=hidden name="lineToRetrieve" value="">
<input type=hidden name="InvProperty_flag" value="I">

<input type=hidden name="Account_icHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="Account_icLine" value="0">
<input type=hidden name="Account_accountType" value="INH">

<tsa:hidden name="Default_referenceType" value="INH"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_doc_ic_header%>"/>
<tsa:hidden name="DocAttachment_docIc" value=""/>
<tsa:hidden name="DocAttachment_docSource" value="INH"/>
<tsa:hidden name="DocAttachment_docPrint" value="N"/>
<tsa:hidden name="hasDocs" value="NO"/>
<tsa:hidden name="DocAttachment_delete" value="FALSE"/>
<tsa:hidden name="DocAttachment_edit_docIc" value="FALSE"/>
<tsa:hidden name="DocAttachment_edit_docTitle" value="FALSE"/>
<tsa:hidden name="filename" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="formNumber" value="<%=s_rec_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_rec_status%>"/>
<tsa:hidden name="icHeaderEdit" value="<%=icHeaderEdit%>"/>
<tsa:hidden name="icHeaderHistoryEdit" value="<%=icHeaderHistoryEdit%>"/>
<tsa:hidden name="formtype" value="INS"/>
<tsa:hidden name="frompage" value="<%=(String) request.getAttribute(\"frompage\")%>"/>

<tsa:hidden name="InspectionHeader_icInspNo" value="<%=s_icInspNo%>"/>
<tsa:hidden name="InspectionHeader_icMsrLine" value="<%=s_icMsrLine%>"/>
<tsa:hidden name="InspectionHeader_status" value="<%=s_inspectionStatus%>"/>
<tsa:hidden name="return_page" value="/requests/rec_ins_attachments.jsp"/>
<tsa:hidden name="return_handler" value="DocAttachmentRetrieveByHeader"/>

<%
	if (HiltonUtility.isEmpty(s_rec_number)) {
		s_rec_number = "N/A";
	}
%>

<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12">
				<tsa:label labelName="attachments" defaultString="Attachments"/>
				</div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td>
		<%	if (!HiltonUtility.isEmpty(s_po_number)) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Order #:</b>&nbsp;<%=s_po_number%>
		<%	} else if (!HiltonUtility.isEmpty(s_req_number)) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Transfer Request #:</b>&nbsp;<%=headerEncoder.encodeForHTML(s_req_number)%>
		<%  } %>
			</td>
			<td align=right><b>Receipt #:</b></td>
		<%	if (HiltonUtility.isEmpty(s_rec_number)) { %>
			<td width=100px>N/A</td>
		<%	} else { %>
			<td width=100px><%=s_rec_number%></td>
		<%	} %>
		</tr>
		<tr>
			<td>
		<%	if (bd_revision_number.compareTo(bd_zero) > 0) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Revision #:</b>&nbsp;<%=bd_revision_number%>
		<%	} %>
		<%	if (bd_release_number.compareTo(bd_zero) > 0) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Release #:</b>&nbsp;<%=bd_release_number%>
		<%	} %>
			</td>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=120px><%=DocumentStatus.toString(s_rec_status, oid)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tsa:tr>
</table>

<br>
<div style="display:none;">
<%@ include file="/receipts/rec_inspection_info.jsp" %>
</div>
<br><br>

<table border="0" cellpadding="0" cellspacing="0" width="680px" height="200px">
<tsa:tr>
	<tsa:td valign="top" align="center" width="100%">
		<table border="0" cellpadding="2" cellspacing="0">
		<tsa:tr>
			<tsa:td align="right">
			<tsa:label labelName="title" defaultString="Title" />:
			</tsa:td>
			<tsa:td><tsa:input type="text" name="DocAttachment_docTitle" size="60" maxLength="60" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right">
			<tsa:label labelName="fileToAtach" defaultString="File to Attach" />:
			</tsa:td>
			<tsa:td><input type="file" name="file3" size="45"></tsa:td>
		</tsa:tr>
		<tsa:tr><tsa:td colspan="2"><br></tsa:td></tsa:tr>
<%	if (displayPrintOption.equalsIgnoreCase("Y")) {	%>
		<tsa:tr>
			<tsa:td colspan="2" align="center"><tsa:input type="checkbox" name="ckboxPrint" value="Y" />&nbsp;<b>
			<tsa:label labelName="req-print" defaultString="Print with Inspection" />:
			</b></tsa:td>
		</tsa:tr>
<%	} else {	%>
			<tsa:hidden name="ckboxPrint" value="Y"/>
<%	}	%>
		</table>

		<br><br>

		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tsa:tr>
			<tsa:td align="center" width="50%"><div id="pxbutton"><a href="javascript: option=0; doSubmit('/receipts/rec_ins_attachment_add.jsp', '--'); void(0);"><tsa:label labelName="rec-Add" defaultString="Add" /></a></div></tsa:td>
			<tsa:td align="center" width="50%"><div id="pxbutton"><a href="javascript: option=1; doSubmit('/receipts/rec_ins_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);"><tsa:label labelName="req-Return" defaultString="Return" /></a></div></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	var oldAction = frm.action;
	frm.action =" javascript: validateForm();";
	var option = -1;
	validFileTypes = "<%=PropertiesManager.getInstance(oid).getProperty("DOCUMENTS", "ValidFileTypes", "")%>";

	frm.file3.contentEditable  = false;

	function validateForm() {
		if(option == 0){
			var handler = frm.handler.value;

			if (handler.indexOf("--") >= 0) {
				var doc = frm.file3.value;
				var title = frm.DocAttachment_docTitle.value;
				var alertMessage = "";

				if ( isEmpty(title) && isEmpty(doc) ) {
					alert("Please enter a Title and select a File to Attach.");
					return false;
				}
				else if (isEmpty(doc)) {
					alert('Please select a file to attach.');
					return false;
				}
				else if (!isAttachmentExtValid(doc)) {
					return false;
				}
				else if (isEmpty(title)) {
					alert('Please enter a Document Title.');
					return false;
				}
				else {
					if (frm.ckboxPrint.checked) {
						frm.DocAttachment_docPrint.value = "Y";
					} else {
						frm.DocAttachment_docPrint.value = "N";
					}
					frm.action =" <%=contextPath%>/HiltonDocumentUploadServlet";
					frm.action = frm.action + "?" + frm.organizationId.value;
					frm.enctype = "multipart/form-data";
					frm.encoding = "multipart/form-data";
				//	frm.submit();
				}
			}
			return true;
		}else if(option == 1){
				frm.action = oldAction;
				frm.submit();
			}
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>