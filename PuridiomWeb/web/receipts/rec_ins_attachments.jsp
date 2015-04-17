<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/attachments.js"></script>
<%

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	
	
	ReceiptHeader  receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader") ;
	if (receiptHeader ==null) receiptHeader = new ReceiptHeader() ;
	
	ReceiptLine receiptLine = (ReceiptLine) request.getAttribute("receiptLine") ;
	if (receiptLine ==null) receiptLine = new ReceiptLine() ;
	
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal bd_line_number = receiptLine.getReceiptLine() ;
	
	
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
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	if (poHeader == null) {
		poHeader = new PoHeader();
	}
	
	String	receiptMethod = HiltonUtility.ckNull((String)request.getAttribute("receiptMethod"));

	String	s_line_count = (String) request.getAttribute("lineCount");
	String	return_page = "";

	boolean editMode = true;

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

	return_page = "/receipts/rec_insspection_detail.jsp";

	String	s_current_process = "STEP1";
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
<tsa:hidden name="DocAttachment_icLine" value="0"/>
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

<%
	if (HiltonUtility.isEmpty(s_rec_number)) {
		s_req_number = "N/A";
	}
%>
<br>
<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
		  <tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="attachments" defaultString="Attachments"></tsa:label></div>
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
<br>
<% List list = (List) request.getAttribute("docAttachmentList"); %>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
	<tsa:td align="center" valign="top">
		<table border="0" cellspacing="0" cellpadding="0" width="100%">
		<tsa:tr>
			<tsa:td width="100%" align="center" valign="top">
<%
	if (list == null || list.size() == 0) {
%>
				<br><b><tsa:label labelName="noDocumentsAttached" defaultString="There are currently no documents attached"></tsa:label>.</b><br><br>
<% 	} else {%>
				<div id="attachmentList" style="visibility: visible; display: block; width: 100%">
				<table id="attachments" border="0" cellpadding="1" cellspacing="0" width="90%">
				<tsa:tr>
					<tsa:td width="5%">&nbsp;</tsa:td>
					<tsa:td width="50%"><b><tsa:label labelName="documentTitle" defaultString="Document Title"></tsa:label></b></tsa:td>
					<tsa:td width="5%">&nbsp;</tsa:td>
<%		if (editAttachmentsAccessBool) { %>
					<tsa:td width="10%" align="center"><b><tsa:label labelName="replace" defaultString="Replace"></tsa:label></b></tsa:td>
<%		}
			if (displayPrintOption.equalsIgnoreCase("Y")) {	%>
					<tsa:td width="10%" align="center"><b><tsa:label labelName="print" defaultString="Print"></tsa:label></b></tsa:td>
<%		}
			if (editMode) { %>
					<tsa:td width="10%" align="center"><b><tsa:label labelName="delete" defaultString="Delete"></tsa:label></b></tsa:td>
<%		} else {%>
					<tsa:td width="10%" align="center">&nbsp;</tsa:td>
<%		}%>
				</tsa:tr>
<%
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				DocAttachment docAttachment = (DocAttachment) list.get(i);
				DocAttachmentPK docAttachmentPK = (DocAttachmentPK) docAttachment.getComp_id();
				String	filename = docAttachment.getDocFilename();
				String	ext = filename.substring(filename.lastIndexOf(".") + 1);
%>
				<tsa:tr>
					<tsa:td width="5%" align="right" id="<%= \"doc_num_\" + i%>">&nbsp;<%=i+1%>.&nbsp;<tsa:hidden name="docIc" value="<%=docAttachment.getComp_id().getDocIc()%>"/><tsa:hidden name="s_req_ic_header" value="<%=docAttachment.getComp_id().getIcHeader()%>"/></tsa:td>
					<tsa:td width="50%"><tsa:input type="text" name="docTitle" value = "<%=docAttachment.getDocTitle()%>" maxLength="60" size="60" /></tsa:td>
					<tsa:td width="5%" valign="middle" align="center">
						<tsa:hidden name="docFilename" value = "<%=filename%>"/>
		<%		if (ext.equalsIgnoreCase("DOC")) {%>
						<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border="0" alt="Open Attached MS Word Document" title="Open Attached MS Word Document"></a>
		<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
						<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border="0" alt="Open Attached Adobe PDF Document" title="Open Attached Adobe PDF Document"></a>
		<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
						<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border="0" alt="Open Attached MS Excel Document" title="Open Attached MS Excel Document"></a>
		<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
						<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border="0" alt="Open Attached MS Project Document" title="Open Attached MS Project Document"></a>
		<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
						<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border="0" alt="Open Attached MS Powerpoint Document" title="Open Attached MS Powerpoint Document"></a>
		<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
						<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border="0" alt="Open Attached Image" title="Open Attached Image"></a>
		<%		} else {%>
						<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border="0" alt="Open Attached Document" title="Open Attached Document"></a>
		<%		}%>
					</tsa:td>
		<%		if (editAttachmentsAccessBool) { %>
					<tsa:td width="10%" align="center">
						<a href="javascript: editAttachment(<%=i%>); void(0);"><img src="<%=contextPath%>/images/cmd_edit.gif" alt="Edit" title="Edit" border="0"></a>					</tsa:td>
		<%		}
				if (displayPrintOption.equalsIgnoreCase("Y")) {	%>
					<tsa:td width="10%" align="center">
						<% if (docAttachment.getDocPrint().indexOf("Y")>= 0){%>
							<tsa:input type="checkbox" name="cboxPrint" checked="checked" value="Y" onclick="setPrint(<%=i%>);" title="Print" />
						<%}else { %>
							<tsa:input type="checkbox" name="cboxPrint" value="Y" onclick="<%=\"setPrint(\" + i + \");\"%>" title="Print" />
						<%} %>
						<tsa:hidden name="docPrint" value="<%=docAttachment.getDocPrint()%>"/>					</tsa:td>
		<%		} else {	%>
						<tsa:hidden name="docPrint" value="Y"/>
		<%		}
				if (editMode) { %>
					<tsa:td width="10%" id="doc_del_<%=i%>" align="center">
						<a href="javascript: if (verifyAction('Are you sure you wish to delete Attachment line <%=(i+1)%>?')) { deleteMe(<%=i%>); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" title="Delete" border="0"></a>					</tsa:td>
		<%		} else { %>
					<tsa:td width="10%">&nbsp;</tsa:td>
		<%		} %>
				</tsa:tr>
		<%	}
		} %>
				</table>
				<br>
				</div>
<%	} %>
				<table border="0" cellpadding="0" cellspacing="0" width="90%">
    			<tsa:tr>
	                <tsa:td noWrap="nowrap" align="center" width="50%"><a href="javascript: addAttachment(); void(0)"><span class="reset"><b><tsa:label labelName="addAttachment" defaultString="Add an attachment"></tsa:label></b></span></a></tsa:td>
	                <tsa:td noWrap="nowrap" field="ins-addStandardForm" align="center" width="50%"><a href="javascript: addStandardAttachment(); void(0)"><span class="reset"><b><tsa:label labelName="addStandard" defaultString="Add standard form"></tsa:label></b></span></a></tsa:td>
				</tsa:tr>
              <%	if (oid.equalsIgnoreCase("bsc04p")) {	%>
				<tsa:tr>
                	<tsa:td>
                		<table width="90%" align="center">
                    	<tsa:tr>
							<tsa:td cssClass="red"><br>
								<br>
								<tsa:label labelName="ins-attachment-instructions" defaultString="* Add an attachment, i.e. your sole source justification; your vendor proposal; your vendor quote, etc." docType="s_req_type"></tsa:label></tsa:td>
						</tsa:tr>
						</table>
					</tsa:td>
				</tsa:tr>
              <%	}	%>
				</table>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>
<br>

<div id="classicNavigation">
	<table border="0" cellpadding="0" cellspacing="0" width="680px">
	<tsa:tr>
		<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: saveAttachments(); void(0);"><tsa:label labelName="ins-save" defaultString="Save"></tsa:label></a></div></tsa:td>
		<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('/receipts/rec_inspection_detail.jsp', 'RecInspectionRetrieveDetail'); void(0);"><tsa:label labelName="ins-return" defaultString="Return"></tsa:label></a></div></tsa:td>
	</tsa:tr>
	</table>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value="JavaScript">
<!-- Hide script

	frm = document.purchaseform;
	var totalRows = 0;
<%
	if (!(list == null || list.size() == 0)) {%>
		var myTable = document.getElementById("attachments");
    	totalRows = myTable.rows.length - 1;
<%}%>
	var inEditMode = <%=editMode%>;
	var currentpage = "<%=s_current_page%>";
	var currentPage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var returnPage = "/receipts/rec_inspection_detail.jsp";
	var returnHandler = "RecInspectionRetrieveDetail";
	var editNewPage = "/receipts/rec_ins_edit_attachment_new.jsp";


	function validateForm() {
		if (frm.DocAttachment_delete.value == 'TRUE') { return true ; }
		var newInputFields = "";
		var handlers = "DocAttachmentUpdateByHeader;" + returnHandler;

		if (totalRows > 1) {
		    frm.hasDocs.value = "YES" ;
			for (var i=0; i < totalRows; i++) {
				var docIc = frm.docIc[i].value;
				var docTitle = frm.docTitle[i].value;
				var docPrint = frm.docPrint[i].value;
				var docPost = "N";

				if (frm.docPost != null && frm.docPost != undefined) {
					docPost = frm.docPost[i].value;
				}

				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docIc' value='" + docIc + "'>";
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docTitle' value='" + docTitle + "'>";
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPrint' value='" + docPrint + "'>";
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPost' value='" + docPost + "'>";
			}
		} else if (totalRows == 1) {
			newInputFields = "<input type='hidden' name='DocAttachment_docIc' value='" + frm.docIc.value + "'>";
			newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docTitle' value='" + frm.docTitle.value + "'>";
			newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPrint' value='" + frm.docPrint.value + "'>";
			if (frm.docPost != null && frm.docPost != undefined) {
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPost' value='" + frm.docPost.value + "'>";
			} else {
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPost' value='N'>";
			}
		}
		setHiddenFields(newInputFields);
		return true ;
	}

	function addAttachment() {
		doSubmit('/receipts/rec_ins_attachment_new.jsp', 'DoNothing');
	}



// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>