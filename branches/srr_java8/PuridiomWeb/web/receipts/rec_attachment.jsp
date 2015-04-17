<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/attachments.js"></script>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");

	String	s_ic_rec_header = (String)request.getAttribute("ReceiptHeader_icRecHeader");
	String	s_rec_number = (String)request.getAttribute("ReceiptHeader_receiptNumber");
	String	s_rec_type = (String)request.getAttribute("ReceiptHeader_receiptType");
	String	s_rec_status = (String)request.getAttribute("ReceiptHeader_receiptStatus");
	String	s_rec_fiscal_year = (String)request.getAttribute("ReceiptHeader_fiscalYear");
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

	String	receiptMethod = HiltonUtility.ckNull((String)request.getAttribute("receiptMethod"));
	String	s_current_process = "HEADER_ATTACHMENTS";
	String	s_current_page = "/receipts/rec_attachment.jsp";
	String	s_current_method = "DocAttachmentUpdateByHeader";
	String	s_current_process_method = "";

	BigDecimal	bd_zero = new BigDecimal(0);

	boolean allowEdit = false;
	if ((receiptMethod.equals("FinalizeReceipt") && s_rec_status.compareTo(DocumentStatus.RCV_PENDINGFINALIZATION) == 0) ||
		(receiptMethod.equals("ReceiveFromNothing") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
		(receiptMethod.equals("ReceiveByPackage") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
		(receiptMethod.equals("ReceiveByOrder") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0))
		allowEdit = true;

	String	returnPage = "";
	if (s_view.equals("WIZARD")) {
		returnPage = "/receipts/rec_review.jsp";
	} else {
		returnPage = "/receipts/rec_summary.jsp";
	}
%>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=s_rec_number%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="<%=s_rec_type%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=s_rec_status%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=s_rec_fiscal_year%>"/>
<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RCH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="formType" value="REC"/>
<tsa:hidden name="allowBrowse" value="true"/>

<tsa:hidden name="DocAttachment_edit_docIc" value="FALSE"/>
<tsa:hidden name="DocAttachment_edit_docTitle" value="FALSE"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="Default_referenceType" value="RCH"/>
<tsa:hidden name="DocAttachment_delete" value="FALSE"/>
<tsa:hidden name="hasDocs" value="NO"/>
<tsa:hidden name="returnPage" value="/receipts/rec_attachment.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "attachments", "Attachments")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td>
		<%	if (!HiltonUtility.isEmpty(s_po_number)) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Order #:</b>&nbsp;<%=headerEncoder.encodeForHTML(s_po_number)%>
		<%	} %>
			</td>
			<td align=right><b>Receipt #:</b></td>
		<%	if (HiltonUtility.isEmpty(s_rec_number)) { %>
			<td width=100px>N/A</td>
		<%	} else { %>
			<td width=100px><%=headerEncoder.encodeForHTML(s_rec_number)%></td>
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
<%@ include file="/receipts/rec_info.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
	<tr>
		<td valign=top align=center>
		<%
		List list = (List) request.getAttribute("docAttachmentList");
		if (list == null || list.size() == 0) {
			%>
			<br><b>There are currently no documents attached.</b><br><br>
	<% 			} else {%>
			<div id="attachmentList" style="visibility: visible; display: block;">
				<table id="attachments" border=0 cellpadding=1 cellspacing=0 width=500px>
					<tr>
						<td width=5%>&nbsp;</td>
						<td width=50%><b>Document Title</b></td>
						<td width=5%>&nbsp;</td>
	<%		if (allowEdit) { %>
					<td width=11% align=center><b>Replace</b></td>
	<%		} %>
					<td width=10% align=center><b>Print</b></td>
	<%		if (allowEdit) { %>
					<td width=10% align=center><b>Delete</b></td>
	<%		} else {%>
					<td width=10% align=center>&nbsp;</td>
	<%		}%>
					</tr>
					<!--tr>
						<td colspan=8 vAlign=top>
							<div id="noAttachmentsMsg" style="visibility: hidden; display: none;">
							<table border=0 cellpadding=1 cellspacing=0 width=500px>
							<tr><td width=100% align=center><br>There are currently no documents attached.<br></td></tr>
							</table>
							</div>
							<table id="attachments" border=0 cellpadding=1 cellspacing=0 width=500px-->
		<%
//			List list = (List) request.getAttribute("docAttachmentList");

			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					DocAttachment docAttachment = (DocAttachment) list.get(i);
					DocAttachmentPK docAttachmentPK = (DocAttachmentPK) docAttachment.getComp_id();
					String	filename = docAttachment.getDocFilename();
					String	ext = filename.substring(filename.lastIndexOf(".") + 1);
		%>
					<tr>
						<td width=5% align=right id="doc_num_<%=i%>">&nbsp;<%=i+1%>.&nbsp;<tsa:hidden name="docIc" value="<%=docAttachment.getComp_id().getDocIc()%>"/><tsa:hidden name="icHeader" value="<%=docAttachment.getComp_id().getIcHeader()%>"/></td>
						<td width=65%><input type=text name="docTitle" value = "<%=docAttachment.getDocTitle()%>" maxLength=60 size=60></td>
						<td width=5% valign=middle align=center>
							<tsa:hidden name="docFilename" value = "<%=filename%>"/>
<%		if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("EML")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/email.gif" border=0 alt="Open Attached Email"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT") || ext.equalsIgnoreCase("PPTX")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
						</td>
<%		if (allowEdit) { %>
						<td width=9% align=center><a href="javascript: editAttachment(<%=i%>); void(0);"><img src="<%=contextPath%>/images/cmd_edit.gif" alt="Click here to select another document to replace the existing attachment." border=0></a></td>
<%		} %>
						<td width=10% align=center>
							<input type="checkbox" name="cboxPrint" <% if (docAttachment.getDocPrint().indexOf("Y")>= 0) {%>CHECKED<%}%> value="Y" ONCLICK="setPrint(<%=i%>);">
							<tsa:hidden name="docPrint" value="<%=docAttachment.getDocPrint()%>"/>
						</td>
<%		if (allowEdit) { %>
						<td width=15% id="doc_del_<%=i%>" align=center><a href="javascript: if (verifyAction('Are you sure you wish to delete Attachment line <%=(i+1)%>?')) { deleteMe(<%=i%>); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
<%		} else { %>
						<td width=15%>&nbsp;</td>
<%		} %>
					</tr>
		<%	}
			} %>
							<!--/table>
						</td>
					</tr-->
				</table>
				<br>
			</div>
	<%} %>
			<table border=0 cellpadding=0 cellspacing=0 width=500px>
			<%	if (allowEdit) { %>
			<tr>
				<td nowrap align=center width=50%><a href="javascript: addAttachment(); void(0)"><font class=reset><b>Add new</b></font></a></td>
				<td nowrap align=center width=50%><a href="javascript: addStandardAttachment(); void(0)"><span class="reset"><b>Add standard form</b></span></a></td>
			</tr>
			<%	} %>
			<%	if (s_rec_type.equalsIgnoreCase("CT") && oid.equalsIgnoreCase("bsc04p")) {	%>
			<tr>
				<td colspan="2" align="center" >
					<table align="center">
					<tr>
						<td class="red">
						<br><br>Add pertinent documents to the contract record as attachments.
						</td>
					</tr>
					</table>
				</td>
			</tr>
<%	}	%>
			</table>
		</td>
	<%	if (s_view.equals("WIZARD")) { %>
	      <td rowspan=2 align="right" valign="top"><%@ include file="/receipts/rec_sidebar.jsp" %></td>
	<%	} %>
	</tr>
</table>

<br>
<br>

<div id="classicNavigation">
	<%	if (s_view.equalsIgnoreCase("CLASSIC") ) { %>
	<table border=0 cellpadding=0 cellspacing=0 width=680px>
	<tr>
		<td width=50% align=center><a href="javascript: saveAttachments(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
		<td width=50% align=center><a href="javascript: doSubmit('<%=returnPage%>', 'ReceiptRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
	</tr>
	</table>
	<%	} %>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var editNewPage = "/receipts/rec_edit_attachment_new.jsp";

	var recNumber = "<%=headerEncoder.encodeForJavaScript(s_rec_number)%>";
	var fiscalyear = "<%=headerEncoder.encodeForJavaScript(s_rec_fiscal_year)%>";
	var currentPage = "<%=s_current_page%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	var returnPage = "<%=returnPage%>";
	var returnHandler = "PoRetrieve";
<%	if (!(list == null || list.size() == 0)) {%>
	var myTable = document.getElementById("attachments");
	var totalRows = myTable.rows.length - 1;
<%	}%>

	function thisLoad()
	{
		f_StartIt();
	<%	if ( !allowEdit ) {	%>
		checkInputSettings();
	<%	}	%>
	}

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
		} else {
		}
		setHiddenFields(newInputFields);
		return true ;
	}

	function addAttachment() {
		doSubmit('/receipts/rec_attachment_new.jsp', 'DoNothing');
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>