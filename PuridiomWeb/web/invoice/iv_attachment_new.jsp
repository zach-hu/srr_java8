<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	String	s_ivc_ic_header = (String) request.getAttribute("DocAttachment_icHeader");
	String	s_ivc_number = (String) request.getAttribute("InvoiceHeader_invoiceNumber");
	String	s_ivc_status = (String) request.getAttribute("InvoiceHeader_status");
	String	return_page = "";
	boolean editMode = false;

	if (s_ivc_status.compareTo(DocumentStatus.IVC_APPROVING) < 0) {
		editMode = true;
	}
	if (s_view.equals("WIZARD")) {
		return_page = "/invoice/iv_review.jsp";
	} else {
		return_page = "/invoice/iv_summary.jsp";
	}

	String	s_current_process = "HEADER_ATTACHMENTS";
	String	s_current_page = "/invoice/iv_attachment_new.jsp";
	String	s_current_process_method = "";
%>

<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=s_ivc_number%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=s_ivc_status%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="DocAttachment_docIc" value=""/>
<tsa:hidden name="DocAttachment_docSource" value="IVH"/>
<tsa:hidden name="DocAttachment_docPrint" value="N"/>
<tsa:hidden name="return_page" value="/invoice/iv_attachments.jsp"/>
<tsa:hidden name="return_handler" value="DocAttachmentRetrieveByHeader"/>
<tsa:hidden name="formNumber" value="<%=s_ivc_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_ivc_status%>"/>
<tsa:hidden name="currentPage" value="/invoice/iv_attachments_new.jsp"/>
<tsa:hidden name="duplicateNumberFailurePage" value="/invoice/iv_attachments_new.jsp"/>
<%
	if (HiltonUtility.isEmpty(s_ivc_number)) {
		s_ivc_number = "N/A";
	}
%>

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
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Invoice #:</b></td>
			<td width=125px><%=s_ivc_number%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=125px><%=DocumentStatus.toString(s_ivc_status, oid)%></td>
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

<br><br><br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%> height=200px>
<tr>
	<td valign=top align=center width=100%>
		<table border=0 cellpadding=2 cellspacing=0>
		<tr>
			<td align=right>Title:</td>
			<td><input type=text name="DocAttachment_docTitle" size=60 maxLength=60></td>
		</tr>
		<tr>
			<td align=right>File to Attach:</td>
			<td><input type=file name=file3 size=45></td>
		</tr>
		<tr><td colspan=2><br></td></tr>
		<tr>
			<td colspan=2 align=center><input type=checkbox name=ckboxPrint value="Y">&nbsp;<b>Print with Invoice</b></td>
<!--			<td align=center><b>Print with Invoice</b></td>-->
		</tr>
		</table>

		<br><br>

		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td align=center width=50%><div id="pxbutton"><a href="javascript: doSubmit('/invoice/iv_attachment_add.jsp', '--'); void(0);">Add</a></div></td>
			<td align=center width=50%><div id="pxbutton"><a href="javascript: doSubmit('/invoice/iv_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);">Return</a></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	validFileTypes = "<%=PropertiesManager.getInstance(oid).getProperty("DOCUMENTS", "ValidFileTypes", "")%>";

	frm.file3.contentEditable  = false;

	function validateForm() {
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
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>