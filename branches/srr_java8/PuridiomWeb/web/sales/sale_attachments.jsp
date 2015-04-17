<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/attachments.js"></script>
<%
	String	icHeader = (String) request.getAttribute("DocAttachment_icHeader");
	String s_icSaleHeader = (String) request.getAttribute("SaleHeader_icSaleHeader");
	String s_saleNumber = (String) request.getAttribute("SaleHeader_saleNumber");
	String s_amendment = (String) request.getAttribute("SaleHeader_amendment");
	String s_status = (String) request.getAttribute("SaleHeader_status");
	String s_fiscalYear = (String) request.getAttribute("SaleHeader_fiscalYear");
	String	returnPage = "";
	boolean editMode = false;
			
	if (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) < 0 || s_status.compareTo(DocumentStatus.SALE_OPENAMENDMENT) == 0) {
		editMode = true;
	}
	if (s_view.equals("WIZARD")) {
		returnPage = "/sales/sale_review.jsp";
	} else {
		returnPage = "/sales/sale_summary.jsp";
	}
	String	s_current_process = "HEADER_ATTACHMENTS";
	String	s_current_page = "/sales/sale_attacments.jsp";
	String	s_current_method = "DocAttachmentUpdateByHeader";
	String	s_current_process_method = "";
%>
<%@ include file="/sales/sale_hidden_fields.jsp" %>
<tsa:hidden name="filename" value=""/>
<br>
<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Attachments</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/sales/sale_status_title.jsp" %>
		<table cellpadding="0" cellspacing="0" border=0>
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
			<table border=0 cellpadding=1 cellspacing=0 width=525px>
			<tr>
				<td width=5%>&nbsp;</td>
				<td width=60%><b>Document Title</b></td>
				<td width=5%>&nbsp;</td>
				<td width=10% align=center><b>Print</b></td>
				<td width=10% align=center><b>Post</b></td>
<%		if (editMode) { %>
				<td width=10% align=center><b>Delete</b></td>
<%		} else {%>
				<td width=10% align=center><b>&nbsp;</b></td>
<%		}%>
			</tr>
			<tr>
				<td colspan=6 vAlign=top>
					<div id="noAttachmentsMsg" style="visibility: hidden; display: none;">
					<table border=0 cellpadding=1 cellspacing=0 width=525px>
					<tr><td width=100% align=center><br>There are currently no documents attached.<br></td></tr>
					</table>
					</div>
					<table id="attachments" border=0 cellpadding=1 cellspacing=0 width=525px>
<%
	List list = (List) request.getAttribute("docAttachmentList");
	if (list != null) {
		for (int i = 0; i < list.size(); i++) {
			DocAttachment docAttachment = (DocAttachment) list.get(i);
			if (docAttachment == null) {
				continue;
			}
			DocAttachmentPK docAttachmentPK = (DocAttachmentPK) docAttachment.getComp_id();
			String	filename = docAttachment.getDocFilename();
			String	ext = filename.substring(filename.lastIndexOf(".") + 1);
%>
					<tr>
						<td width=5% align=right id="doc_num_<%=i%>">&nbsp;<%=i+1%>.&nbsp;<tsa:hidden name="docIc" value="<%=docAttachment.getComp_id().getDocIc()%>"/><tsa:hidden name="icHeader" value="<%=docAttachment.getComp_id().getIcHeader()%>"/></td>
						<td width=60%><input type=text name="docTitle" value = "<%=docAttachment.getDocTitle()%>" maxLength=60 size=60></td>
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
						<td width=10% align=center><input type="checkbox" name="cboxPrint" <% if (docAttachment.getDocPrint().indexOf("Y")>= 0){ %>CHECKED<%}%> value="Y" ONCLICK="setPrint(<%=i%>);">
							<tsa:hidden name="docPrint" value="<%=docAttachment.getDocPrint()%>"/>
						</td>
						<td width=10% align=center><input type="checkbox" name="cboxPost" <% if (docAttachment.getDocPost().indexOf("Y")>= 0){ %>CHECKED<%}%> value="Y" ONCLICK="setPost(<%=i%>);">
							<tsa:hidden name="docPost" value="<%=docAttachment.getDocPost()%>"/>
						</td>
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
			<br>
			<table border=0 cellpadding=0 cellspacing=0 width=500px>
			<tr><td nowrap align=center><a href="javascript: doSubmit('/sales/sale_attachment_new.jsp', 'DoNothing');  void(0)"><font class=reset><b>Add new</b></font></a></td></tr>
			</table>
		</div>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: saveAttachments(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('<%=returnPage%>', 'SaleRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var saleNumber = "<%=s_saleNumber%>";
	var fiscalyear = "<%=s_fiscalYear%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	
	hideArea("navTable");
	
	inEditMode = <%=editMode%>;
	currentPage = "/sales/sale_attachments.jsp";
	returnPage = "<%=returnPage%>";
	returnHandler = "SaleRetrieve";
	myTable = document.getElementById("attachments");
	totalRows = myTable.rows.length;
	
// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>