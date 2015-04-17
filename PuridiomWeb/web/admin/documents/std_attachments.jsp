<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>

<tsa:hidden name="filename" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "stdattachments", "Standard Form Documents", false)%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center>
		<div id="attachmentList" style="visibility: visible; display: block'">
			<table border=0 cellpadding=1 cellspacing=0 width=500px>
			<tr>
				<td width=5%>&nbsp;</td>
				<td width=65%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "documentTitle", "Document Title")%></b></td>
				<td width=5%>&nbsp;</td>
				<td width=10% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "print", "Print")%></b></td>
				<td width=15% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "delete", "Delete")%></b></td>
			</tr>
			<tr>
				<td colspan=8 vAlign=top>
					<div id="noAttachmentsMsg" style="visibility: hidden; display: none;">
					<table border=0 cellpadding=1 cellspacing=0 width=500px>
					<tr><td width=100% align=center><br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "stdnoattachments", "There are currently no standard form documents available.", false)%><br></td></tr>
					</table>
					</div>
					<table id="attachments" border=0 cellpadding=1 cellspacing=0 width=500px>
<%
	List list = (List) request.getAttribute("stdAttachmentList");

	if (list != null) {
		for (int i = 0; i < list.size(); i++) {
			StdAttachment stdAttachment = (StdAttachment) list.get(i);
			String	filename = stdAttachment.getDocFilename();
			String	ext = filename.substring(filename.lastIndexOf(".") + 1);
%>
					<tr>
						<td width=5% align=right id="doc_num_<%=i%>">&nbsp;<%=i+1%>.&nbsp;<tsa:hidden name="docIc" value="<%=stdAttachment.getDocIc()%>"/></td>
						<td width=65%><input type=text name="docTitle" value = "<%=stdAttachment.getDocTitle()%>" maxLength=60 size=60></td>
						<td width=5% valign=middle align=center>
							<tsa:hidden name="docFilename" value = "<%=filename%>"/>
<%		if (ext.equalsIgnoreCase("DOC")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
						</td>
						<td width=10% align=center><input type="checkbox" name="cboxPrint" <% if (stdAttachment.getDocPrint().indexOf("Y")>= 0){%>CHECKED<%}%> value="Y" ONCLICK="setPrint(<%=i%>);">
							<tsa:hidden name="docPrint" value="<%=stdAttachment.getDocPrint()%>"/>
						</td>
						<td width=15% id="doc_del_<%=i%>" align=center><a href="javascript: if (verifyAction('Are you sure you wish to delete <%=stdAttachment.getDocTitle()%>?')) { deleteMe(<%=i%>); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
					</tr>
<%	}
	} %>
					</table>
				</td>
			</tr>
			</table>
			<br>
			<table border=0 cellpadding=0 cellspacing=0 width=500px>
			<tr><td nowrap align=center><a href="javascript: doSubmit('/admin/documents/std_attachment_new.jsp', 'DoNothing'); void(0)"><span class="reset"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addNew", "Add new")%></b></span></a></td></tr>
			</table>
		</div>
	</td>
</tr>
</table>

<br>
<br>

<div id="classicNavigation">
	<table border=0 cellpadding=0 cellspacing=0 width=680px>
	<tr>
		<td width=50% align=center><div id="pxbutton"><a href="javascript: saveAttachments(); void(0);">Save</a></div></td>
		<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div></td>
	</tr>
	</table>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var myTable = document.getElementById("attachments");
	var totalRows = myTable.rows.length;
	var currentPage = "/admin/documents/std_attachments.jsp";
	var returnPage = "/menu/main_menu.jsp";
	var returnHandler = "DoNothing";

	function thisLoad() {
		f_StartIt();
		setDisplay();
	}

	function openDocument(row) {
		var filename = "";

		if (totalRows > 1) {
			filename = frm.docFilename[row].value;
		} else {
			filename = frm.docFilename.value;
		}

		openStdAttachment(filename);
	}

	function openStdAttachment(filename) {
		popupUrl = "/system/popupDocAttachment.jsp";
		popupHandler = "StdAttachmentDownloadFile";
		popupUserId = frm.userId.value;
		popupOrganizationId = frm.organizationId.value;
		popupParameters = "filename=" + filename;

		if (theFocus == null) { theFocus = 'document_window'; }

		var winspecs = "WIDTH=680,HEIGHT=680,resizable=1,scrollbars=1,menubar=1,location=0,top=0,left=0";
		document_window = window.open("<%=contextPath%>/system/popup_html.jsp", "document_window", winspecs);

		if (theFocus == 'main') {
			self.focus();
		}
		else {
			document_window.focus();
		}

		if (document_window.opener == null) document_window.opener = window;
		self.name = "main";
	}

	function setDisplay() {
		if (totalRows <= 0)
		{
			var noAttachments = document.getElementById("noAttachmentsMsg");
			noAttachments.style.visibility = "visible";
			noAttachments.style.display = "block";
		}
	}

	function deleteMe(row) {
		var docIc = "";
		if (totalRows > 1) {
			docIc = frm.docIc[row].value;
		} else {
			docIc = frm.docIc.value;
		}

		var newInputField = "<input type='hidden' name='StdAttachment_docIc' value='" + docIc + "'>";
		setHiddenFields(newInputField);

		doSubmit(currentPage, 'StdAttachmentDelete;StdAttachmentRetrieveAll');
	}

	function setPrint(row) {
		if (totalRows > 1) {
			if (frm.cboxPrint[row].checked) {
				frm.docPrint[row].value = "Y";
			} else {
				frm.docPrint[row].value = "N";
			}
		} else {
			if (frm.cboxPrint.checked) {
				frm.docPrint.value = "Y";
			} else {
				frm.docPrint.value = "N";
			}
		}
	}

	function saveAttachments() {
		var newInputFields = "";
		var handlers = "StdAttachmentUpdateAll;" + returnHandler;

		if (totalRows > 1) {
			for (var i=0; i < totalRows; i++) {
				var docIc = frm.docIc[i].value;
				var docTitle = frm.docTitle[i].value;
				var docPrint = frm.docPrint[i].value;
				var docPost = "N";

				if (frm.docPost != null && frm.docPost != undefined) {
					docPost = frm.docPost[i].value;
				}

				newInputFields = newInputFields + "<input type='hidden' name='StdAttachment_docIc' value='" + docIc + "'>";
				newInputFields = newInputFields + "<input type='hidden' name='StdAttachment_docTitle' value='" + docTitle + "'>";
				newInputFields = newInputFields + "<input type='hidden' name='StdAttachment_docPrint' value='" + docPrint + "'>";
			}
		} else if (totalRows == 1) {
			newInputFields = "<input type='hidden' name='StdAttachment_docIc' value='" + frm.docIc.value + "'>";
			newInputFields = newInputFields + "<input type='hidden' name='StdAttachment_docTitle' value='" + frm.docTitle.value + "'>";
			newInputFields = newInputFields + "<input type='hidden' name='StdAttachment_docPrint' value='" + frm.docPrint.value + "'>";
		} else {
			handlers = returnHandler;
		}

		setHiddenFields(newInputFields);
		doSubmit(returnPage, handlers);
	}
// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>