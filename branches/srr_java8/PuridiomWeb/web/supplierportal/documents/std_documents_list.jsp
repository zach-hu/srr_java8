		<div id="documentList" style="visibility: visible; display: block;'">
			<table border=0 cellpadding=1 cellspacing=0 width=500px>
			<tr>
				<td width=5%>&nbsp;</td>
				<td width=50%><b>Document Title</b></td>
				<td width=30%><b>Type</b></td>
				<td width=15% align=center><b>Date Posted</b></td>
			</tr>
			<tr>
				<td colspan=8 vAlign=top>
					<div id="noDocumentsMsg" style="visibility: hidden; display: none;">
					<table border=0 cellpadding=2 cellspacing=0 width=100%>
					<tr><td width=100% align=center><br>There are currently no documents available for download.<br></td></tr>
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
						<td width=5% valign=middle align=center>
							<tsa:hidden name="docFilename" value = "<%=filename%>"/>
<%		if (ext.equalsIgnoreCase("DOC")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
						</td>
						<td width=50%><a href="javascript: openDocument(<%=i%>); void(0);"><%=stdDocument.getTitle()%></a></td>
						<td width=30%><%=StdDocumentType.toString(docType, oid)%></td>
						<td width=15% align=center><%=HiltonUtility.getFormattedDate(stdDocument.getDatePosted(), oid)%></td>
<%	}
	} %>
					</table>
				</td>
			</tr>
			</table>
		</div>
		

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var myTable = document.getElementById("documents");
	var totalRows = myTable.rows.length

	function thisLoad() {
		if (totalRows <= 0)
		{
			var noDocuments = document.getElementById("noDocumentsMsg");
			noDocuments.style.visibility = "visible";
			noDocuments.style.display = "block";
		}
	}

	function openDocument(row) {
		var filename = "";

		if (totalRows > 1) {
			filename = frm.docFilename[row].value;
		} else {
			filename = frm.docFilename.value;
		}

		popupUrl = "/supplierportal/system/popupDocAttachment.jsp";
		popupHandler = "StdDocumentDownloadFile";
		popupUserId = frm.userId.value;
		popupOrganizationId = frm.organizationId.value;
		popupParameters = "filename=" + filename;

		if (theFocus == null) { theFocus = 'document_window'; }

		var winspecs = "WIDTH=680,HEIGHT=680,resizable=1,scrollbars=1,menubar=0,location=0,top=0,left=0";
		document_window = window.open("<%=contextPath%>/supplierportal/system/popup_html.jsp", "document_window", winspecs);

		if (theFocus == 'main') {
			self.focus();
		}
		else {
			document_window.focus();
		}

		if (document_window.opener == null) document_window.opener = window;
		self.name = "main";
	}

// End Hide script -->
</SCRIPT>