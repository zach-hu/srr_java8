<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/attachments.js"></script>
<%
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	String	s_doc_ic_header = (String) request.getAttribute("DocAttachment_icHeader");
	String	s_req_ic_header = (String) request.getAttribute("RequisitionHeader_icReqHeader");

	String	s_req_number = (String) request.getAttribute("RequisitionHeader_requisitionNumber");
	String	s_req_status = (String) request.getAttribute("RequisitionHeader_status");
	String	s_fiscal_year = (String) request.getAttribute("RequisitionHeader_fiscalYear");
	String	s_req_type = (String) request.getAttribute("RequisitionHeader_requisitionType");
	String	s_req_subtype = (String) request.getAttribute("RequisitionHeader_rqSubType");
	String	s_req_ic_line = (String) request.getAttribute("RequisitionLine_icReqLine");
	String	s_line_number = (String) request.getAttribute("RequisitionLine_lineNumber");
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

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String editAttachmentsAccess = propertiesManager.getProperty("EDITATTACHMENTS","ENABLED","N");
	String	displayPrintOption = "N";//propertiesManager.getProperty("ATTACHMENTS", "DisplayPrintOption", "Y");

	boolean editAttachmentsAccessBool = false;

	if (editAttachmentsAccess.equalsIgnoreCase("Y")) {
		editAttachmentsAccessBool = true;
	}
	if (s_req_number == null) {
		s_req_number = (String) request.getAttribute("formNumber");
	}
	if (s_req_ic_header == null) {
		s_req_ic_header = (String) request.getAttribute("DocAttachment_icHeader");
	}
	if (s_req_status == null) {
		s_req_status = (String) request.getAttribute("formStatus");
	}
	if (s_fiscal_year == null) {
		s_fiscal_year = (String) request.getAttribute("fiscalYear");
	}
//	if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0) {
//		editMode = true;
//	}

	return_page = "/requests/insspection_detail.jsp";

	String	s_current_process = "HEADER_ATTACHMENTS";
	String	s_current_page = "/requests/ins_attachments.jsp";
	String	s_current_method = "DocAttachmentUpdateByHeader";
	String	s_current_process_method = "";
%>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value="<%=s_req_ic_line%>"/>
<tsa:hidden name="RequisitionLine_lineNumber" value="<%=s_line_number%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="INH"/>
<tsa:hidden name="Default_referenceType" value="INH"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_doc_ic_header%>"/>
<tsa:hidden name="hasDocs" value="NO"/>
<tsa:hidden name="DocAttachment_delete" value="FALSE"/>
<tsa:hidden name="DocAttachment_edit_docIc" value="FALSE"/>
<tsa:hidden name="DocAttachment_edit_docTitle" value="FALSE"/>
<tsa:hidden name="filename" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="formNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_req_status%>"/>
<tsa:hidden name="fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="icHeaderEdit" value="<%=icHeaderEdit%>"/>
<tsa:hidden name="icHeaderHistoryEdit" value="<%=icHeaderHistoryEdit%>"/>
<tsa:hidden name="formtype" value="INS"/>
<tsa:hidden name="frompage" value="<%=(String) request.getAttribute(\"frompage\")%>"/>

<tsa:hidden name="InspectionHeader_icInspNo" value="<%=s_icInspNo%>"/>
<tsa:hidden name="InspectionHeader_icMsrLine" value="<%=s_icMsrLine%>"/>
<tsa:hidden name="InspectionHeader_status" value="<%=s_inspectionStatus%>"/>

<%
	if (HiltonUtility.isEmpty(s_req_number)) {
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
	<tsa:td valign="bottom" height="30px" width="*">
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="requisitionNumber" defaultString="Requisition #"></tsa:label>:</b></tsa:td>
			<tsa:td width="100px"><%=headerEncoder.encodeForHTML(s_req_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="status" defaultString="Status"></tsa:label>:</b></tsa:td>
			<tsa:td width="100px"><%=DocumentStatus.toString(s_req_status, oid)%></tsa:td>
		</tsa:tr>
		</table>
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tsa:tr>
		  <tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
		  <tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>
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
	                <tsa:td noWrap="nowrap" field="req-addStandardForm" align="center" width="50%"><a href="javascript: addStandardAttachment(); void(0)"><span class="reset"><b><tsa:label labelName="addStandard" defaultString="Add standard form"></tsa:label></b></span></a></tsa:td>
				</tsa:tr>
              <%	if (oid.equalsIgnoreCase("bsc04p")) {	%>
				<tsa:tr>
                	<tsa:td>
                		<table width="90%" align="center">
                    	<tsa:tr>
							<tsa:td cssClass="red"><br>
								<br>
								<tsa:label labelName="req-attachment-instructions" defaultString="* Add an attachment, i.e. your sole source justification; your vendor proposal; your vendor quote, etc." docType="s_req_type"></tsa:label></tsa:td>
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
		<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('/requests/inspection_detail.jsp', 'InspectionRetrieveDetail'); void(0);"><tsa:label labelName="ins-return" defaultString="Return"></tsa:label></a></div></tsa:td>
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
	var fiscalyear = "<%=s_fiscal_year%>";
	var returnpage = "/requests/ins_attachments.jsp";
	var reqnumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
	var returnHandler = "InspectionRetrieveDetail";
	var editNewPage = "/requests/ins_edit_attachment_new.jsp";


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
		doSubmit('/requests/ins_attachment_new.jsp', 'DoNothing');
	}



// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>