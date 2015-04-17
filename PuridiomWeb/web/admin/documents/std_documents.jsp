<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.StdDocumentType" %>
<%
	String origin = (String) request.getParameter("origin");
	boolean editMode = false;

//	if (reqStatus.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || reqStatus.compareTo(DocumentStatus.RFQ_PRICED) == 0) {
		editMode = true;
//	}
%>
<tsa:hidden name="filename" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "standardBidboardDocuments", "Standard Bidboard Documents")%></div>
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
		<div id="documentList" style="visibility: visible; display: block'">
			<table border=0 cellpadding=1 cellspacing=0 width=100%>
			<tr>
				<td width=4%>&nbsp;</td>
				<td width=47%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "documentTitle", "Document Title")%></b></td>
				<td width=5%>&nbsp;</td>
				<td width=30%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "type", "Type")%></b></td>
				<td width=7% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "hits", "Hits")%></b></td>
<%		if (editMode) { %>
				<td width=7% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "delete", "Delete")%></b></td>
<%		} else {%>
				<td width=7% align=center>&nbsp;</td>
<%		}%>
			</tr>
			<tr>
				<td colspan=8 vAlign=top>
					<div id="noDocumentsMsg" style="visibility: hidden; display: none;">
					<table border=0 cellpadding=2 cellspacing=0 width=100%>
					<tr><td width=100% align=center><br>There are currently no Standard Bidboard Documents listed.<br></td></tr>
					</table>
					</div>
					<table id="documents" border=0 cellpadding=1 cellspacing=0 width=100%>
<%
	List list = (List) request.getAttribute("stdDocumentList");

	if (list != null) {
		for (int i = 0; i < list.size(); i++) {
			StdDocument stdDocument = (StdDocument) list.get(i);
			String	filename = stdDocument.getFileName();
			String	ext = filename.substring(filename.lastIndexOf(".") + 1);
			String	docType = stdDocument.getDocType();
%>
					<tr>
						<td width=4% align=right id="doc_num_<%=i%>"><%=i+1%>.</td>
						<td width=47%><input type=text name="docTitle" value="<%=stdDocument.getTitle()%>" size=60 maxLength=60></td>
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
						<td width=30%>
							<select name="docType">
								<option value="<%=StdDocumentType.STANDARD_DOCUMENT%>" <%if (docType.trim().equalsIgnoreCase(StdDocumentType.STANDARD_DOCUMENT)) {%>SELECTED<%}%>><%=StdDocumentType.toString(StdDocumentType.STANDARD_DOCUMENT, oid)%></option>
								<option value="<%=StdDocumentType.OTHER_DOCUMENT%>" <%if (docType.trim().equalsIgnoreCase(StdDocumentType.OTHER_DOCUMENT)) {%>SELECTED<%}%>><%=StdDocumentType.toString(StdDocumentType.OTHER_DOCUMENT, oid)%></option>
							</select>
						</td>
						<td width=7% align=center><%=stdDocument.getHits()%></td>
<%		if (editMode) { %>
						<td width=7% id="doc_del_<%=i%>" align=center><a href="javascript: deleteMe(<%=i%>); "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
<%		} else { %>
						<td width=7%>&nbsp;</td>
<%		}
		}
	} %>
					</table>
				</td>
			</tr>
			</table>
<%		if (editMode) { %>
			<br>
			<table border=0 cellpadding=0 cellspacing=0 width=650px>
			<tr><td nowrap align=center><a href="javascript: doSubmit('/admin/documents/std_document_new.jsp', 'DoNothing'); void(0)"><font class=reset><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addNew", "Add new")%></b></font></a></td></tr>
			</table>
<%	}%>
		</div>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%		if (editMode) { %>

	<td width=50% align=center><a href="javascript: saveDocuments(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<%		if (origin != null && origin.equals("admin")) { %>
	<td width=50% align=center><a href="javascript: doSubmit('/admin/admin_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
	<%		} else {%>
	<td width=50% align=center><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
	<%		}%>

<%		} else {%>

	<%		if (origin != null && origin.equals("admin")) { %>
	<td width=100% align=center><a href="javascript: doSubmit('/admin/admin_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
	<%		} else {%>
	<td width=50% align=center><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
	<%		}%>

<%		}%>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var myTable = document.getElementById("documents");
	var totalRows = myTable.rows.length
	var inEditMode = <%=editMode%>;
	currentPage = "/admin/documents/std_documents.jsp";

	setNavCookie("/admin/documents/std_documents.jsp", "StdDocumentRetrieveAll", "Standard Bidboard Documents");

	function thisLoad() {
		f_StartIt();
		setDisplay();

		if (!inEditMode) {
			checkInputSettings();
		}
	}

	function setDisplay() {
		if (totalRows <= 0)
		{
			var noDocuments = document.getElementById("noDocumentsMsg");
			noDocuments.style.visibility = "visible";
			noDocuments.style.display = "block";
		}
	}

	function deleteMe(row) {
		var filename = "";
		var title = "";
		if (totalRows > 1) {
			filename = frm.docFilename[row].value;
			title = frm.docTitle[row].value
		} else {
			filename = frm.docFilename.value;
			title = frm.docTitle.value
		}

		if (verifyAction("Are you sure you wish to delete this document (" + title + ")?")) {
			var newInputField = "<input type='hidden' name='StdDocument_fileName' value='" + filename + "'>";
			setHiddenFields(newInputField);

			doSubmit(currentPage, 'StdDocumentDelete;StdDocumentRetrieveAll');
		}
	}

	function openDocument(row) {
		var filename = "";

		if (totalRows > 1) {
			filename = frm.docFilename[row].value;
		} else {
			filename = frm.docFilename.value;
		}

		openAttachment(filename);
	}

	function saveDocuments() {
		var newInputFields = "";
		var handlers = "StdDocumentUpdate";

		if (totalRows > 1) {
			for (var i=0; i < totalRows; i++) {
				var docFilename = frm.docFilename[i].value;
				var docTitle = frm.docTitle[i].value;
				var docType = frm.docType[i].value;

				newInputFields = newInputFields + "<input type='hidden' name='StdDocument_fileName' value='" + docFilename + "'>";
				newInputFields = newInputFields + "<input type='hidden' name='StdDocument_title' value=" + '"' + docTitle + '"' + ">";
				newInputFields = newInputFields + "<input type='hidden' name='StdDocument_docType' value='" + docType + "'>";
			}
		} else if (totalRows == 1) {
			newInputFields = newInputFields +"<input type='hidden' name='StdDocument_fileName' value='" + frm.docFilename.value + "'>";
			newInputFields = newInputFields + "<input type='hidden' name='StdDocument_title' value=" + '"' + frm.docTitle.value + '"' + ">";
			newInputFields = newInputFields + "<input type='hidden' name='StdDocument_docType' value='" + frm.docType.value + "'>";
		} else {
			handlers = "DoNothing";
		}

		setHiddenFields(newInputFields);
		doSubmit('/admin/admin_menu.jsp', handlers);
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>