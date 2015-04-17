<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/attachments.js"></script>
<%
	boolean editMode = false;
%>
<tsa:hidden name="StdDocument_filename" value=""/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Standard Bidboard Documents</div>
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
				<td width=50%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "documentTitle", "Document Title")%></b></td>
				<td width=5%>&nbsp;</td>
				<td width=10% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "type", "Type")%></b></td>
				<td width=10% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "datePosted", "Date Posted")%></b></td>
				<td width=10% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "hits", "Hits")%></b></td>
<%		if (editMode) { %>
				<td width=10% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "delete", "Delete")%></b></td>
<%		} else {%>
				<td width=10% align=center>&nbsp;</td>
<%		}%>
			</tr>
			<tr>
				<td colspan=8 vAlign=top>
					<div id="noAttachmentsMsg" style="visibility: hidden; display: none;">
					<table border=0 cellpadding=1 cellspacing=0 width=500px>
					<tr><td width=100% align=center><br>There are currently no documents attached.<br></td></tr>
					</table>
					</div>
					<table id="attachments" border=0 cellpadding=1 cellspacing=0 width=500px>
<%
	List list = (List) request.getAttribute("stdDocumentList");

	if (list != null) {
		for (int i = 0; i < list.size(); i++) {
			StdDocument stdDocument = (StdDocument) list.get(i);
			String	filename = stdDocument.getFileName();
			String	ext = filename.substring(filename.lastIndexOf(".") + 1);
%>
					<tr>
						<td width=5% align=right id="doc_num_<%=i%>">&nbsp;<%=i+1%>.&nbsp;<tsa:hidden name="docIc" value="<%=docAttachment.getComp_id().getDocIc()%>"/><tsa:hidden name="icHeader" value="<%=docAttachment.getComp_id().getIcHeader()%>"/></td>
						<td width=50%><input type=text name="docTitle" value = "<%=docAttachment.getDocTitle()%>" maxLength=60 size=60></td>
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
						<td width=10% align=center><%=stdDocument.getDatePosted()%></td>
<%		if (editMode) { %>
						<td width=10% id="doc_del_<%=i%>" align=center><a href="javascript: if (verifyAction('Are you sure you wish to delete Attachment line <%=(i+1)%>?')) { deleteMe(<%=i%>); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
<%		} else { %>
						<td width=10%>&nbsp;</td>
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
	<td width=50% align=center><div id="pxbutton"><a href="javascript: saveAttachments(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=headerEncoder.encodeForHTMLAttribute(returnPage)%>', 'RequisitionRetrieve'); void(0);">Return</a></div></td>
<%		} else {%>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=headerEncoder.encodeForHTMLAttribute(returnPage)%>', 'RequisitionRetrieve'); void(0);">Return</a><div></td>
<%		}%>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	myTable = document.getElementById("attachments");
	totalRows = myTable.rows.length
	inEditMode = <%=editMode%>;
	currentPage = "/requests/req_attachments.jsp";
	returnPage = "<%=headerEncoder.encodeForJavaScript(returnPage)%>";
	returnHandler = "RequisitionRetrieve";
	myTable = document.getElementById("attachments");
	totalRows = myTable.rows.length;

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>