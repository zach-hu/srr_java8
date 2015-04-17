<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.entity.VendorDocument" %>
<%@ page import="com.tsa.puridiom.entity.VendorDocumentPK" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/attachments.js"></script>
<%
	String	vendorId = (String) request.getAttribute("VendorDocument_vendorId");
	String	icHeader = (String) request.getAttribute("VendorDocument_icRfqHeader");
	String	rfqStatus = (String) request.getAttribute("RfqHeader_status");
	String	returnPage = "";
	boolean editMode = false;

	if (rfqStatus.compareTo(DocumentStatus.RFQ_PURCHASING) <= 0) {
		editMode = true;
	}
%>
<tsa:hidden name="VendorDocument_icRfqHeader" value="<%=icHeader%>"/>
<tsa:hidden name="VendorDocument_vendorId" value="<%=vendorId%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=rfqStatus%>"/>
<tsa:hidden name="filename" value=""/>
<tsa:hidden name="allowBrowse" value="false"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Response Documents</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
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
			<table border=0 cellpadding=1 cellspacing=0>
			<tr>
				<td width=10%>&nbsp;</td>
				<td width=65%><b>Document Title</b></td>
				<td width=10%>&nbsp;</td>
<%		if (editMode) { %>
				<td width=15% align=center><b>Delete</b></td>
<%		} else {%>
				<td width=15% align=center>&nbsp;</td>
<%		}%>
			</tr>
			<tr>
				<td colspan=4 valign=top>
					<div id="noAttachmentsMsg" style="visibility: hidden; display: none;">
					<table border=0 cellpadding=1 cellspacing=0 width=500px>
					<tr><td width=100% align=center><br>There are currently no response documents attached.<br></td></tr>
					</table>
					</div>
					<table id="attachments" border=0 cellpadding=1 cellspacing=0 width=500px>
<%
	List list = (List) request.getAttribute("vendorDocumentList");

	if (list != null) {
		for (int i = 0; i < list.size(); i++) {
			VendorDocument vendorDocument = (VendorDocument) list.get(i);
			VendorDocumentPK vendorDocumentPK = vendorDocument.getComp_id();
			String	filename = vendorDocument.getDocFilename();
			String	ext = filename.substring(filename.lastIndexOf(".") + 1);
%>
					<tr>
						<td width=10% align=right id="doc_num_<%=i%>">&nbsp;<%=i+1%>.&nbsp;<tsa:hidden name="docIc" value="<%=vendorDocument.getComp_id().getDocIc()%>"/><tsa:hidden name="icHeader" value="<%=vendorDocument.getComp_id().getIcRfqHeader()%>"/></td>
						<td width=65%><input type=text name="docTitle" value = "<%=vendorDocument.getDocTitle()%>" maxLength=60 size=60></td>
						<td width=10% valign=middle>
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
<%		if (editMode) { %>
						<td width=15% id="doc_del_<%=i%>" align=center><a href="javascript: deleteMe(<%=i%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
<%		} else { %>
						<td width=15%>&nbsp;</td>
<%		} %>
					</tr>
<%	}
	} %>
					</table>
				</td>
			</tr>
			</table>
<%		if (editMode) { %>
			<br>
			<table border=0 cellpadding=0 cellspacing=0 width=500px>
			<tr><td nowrap align=center><a href="javascript: doSubmit('/rfq/supplier_attachment_new.jsp', 'DoNothing'); void(0)"><font class=reset><b>Add new</b></font></a></td></tr>
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
	<td width=50% align=center><div id="pxbutton"><a href="javascript: saveAttachments(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: this.close(); void(0);">Close</a></div></td>
<%		} else {%>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: this.close(); void(0);">Close</a></div></td>
<%		}%>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var myTable = document.getElementById("attachments");
	var totalRows = myTable.rows.length;
	var inEditMode = <%=editMode%>;
	var currentPage = "/rfq/supplier_attachments.jsp";

	function thisLoadPopup() {
		setDisplay();

		if (!inEditMode) {
			checkInputSettings();
		}
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
		var filename = "";

		if (totalRows > 1) {
			docIc = frm.docIc[row].value;
			filename = frm.docFilename[row].value;
		} else {
			docIc = frm.docIc.value;
			filename = frm.docFilename.value;
		}

		var newInputFields = "<input type='hidden' name='VendorDocument_docIc' value='" + docIc + "'>";
		newInputFields = newInputFields + "<input type='hidden' name='VendorDocument_docFilename' value='" + filename + "'>";
		setHiddenFields(newInputFields);

		doSubmit(currentPage, 'VendorDocumentDelete;VendorDocumentRetrieveByVendor');
	}

	function openDocument(row) {
		var filename = "";

		if (totalRows > 1) {
			filename = frm.docFilename[row].value;
		} else {
			filename = frm.docFilename.value;
		}

		popupUrl = '/system/popupDocAttachment.jsp';
		popupHandler = "VendorDocumentDownloadFile";
		popupUserId = frm.userId.value;
		popupOrganizationId = frm.organizationId.value;
		popupParameters = "filename=" + filename;

		if (theFocus == null) { theFocus = 'document_window'; }

		var winspecs = "WIDTH=680,HEIGHT=680,resizable=1,scrollbars=1,menubar=0,location=0,top=0,left=0";
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

	function saveAttachments() {
		var newInputFields = "";
		var returnHandler = "VendorDocumentRetrieveByVendor";
		var handlers = "VendorDocumentUpdateByVendor;" + returnHandler;

		if (totalRows > 1) {
			for (var i=0; i < totalRows; i++) {
				var docIc = frm.docIc[i].value;
				var docTitle = frm.docTitle[i].value;

				newInputFields = newInputFields + "<input type=hidden name=\"VendorDocument_docIc\" value=\"" + docIc + "\">";
				newInputFields = newInputFields + "<input type=hidden name=\"VendorDocument_docTitle\" value=\"" + docTitle +"\">";
			}
		} else if (totalRows == 1) {
			newInputFields = "<input type=hidden name=\"VendorDocument_docIc\" value=\"" + frm.docIc.value + "\">";
			newInputFields = newInputFields + "<input type=hidden name=\"VendorDocument_docTitle\" value=\"" + frm.docTitle.value + "\">";
		} else {
			handlers = returnHandler;
		}

		setHiddenFields(newInputFields);
		doSubmit("rfq/supplier_attachments.jsp", handlers);
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>