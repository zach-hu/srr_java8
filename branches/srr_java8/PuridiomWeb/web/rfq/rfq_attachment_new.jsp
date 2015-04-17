<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	String	icHeader = (String) request.getAttribute("DocAttachment_icHeader");
	String s_icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String s_rfqNumber = (String) request.getAttribute("RfqHeader_rfqNumber");
	String s_rfqAmendment = (String) request.getAttribute("RfqHeader_rfqAmendment");
	String s_rfqStatus = (String) request.getAttribute("RfqHeader_status");
	String	returnPage = "";
	boolean solicitationActive = PropertiesManager.getInstance(oid).getProperty("RFQ OPTIONS", "SOLICITATIONS", "N").equalsIgnoreCase("Y");
	boolean editMode = false;

	if (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENAMENDMENT) == 0) {
		editMode = true;
	}
	if (s_view.equals("WIZARD")) {
		returnPage = "/rfq/rfq_review.jsp";
	} else {
		returnPage = "/rfq/rfq_summary.jsp";
	}
%>
<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="${RfqHeader_rfqType}"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=icHeader%>"/>
<tsa:hidden name="DocAttachment_docIc" value=""/>
<tsa:hidden name="DocAttachment_docSource" value="RQH"/>
<tsa:hidden name="DocAttachment_docPrint" value="N"/>
<tsa:hidden name="DocAttachment_docPost" value="N"/>
<tsa:hidden name="returnPage" value="/rfq/rfq_attachments.jsp"/>
<tsa:hidden name="returnHandler" value="DocAttachmentRetrieveByHeader"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Solicitation Attachments</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/rfq/rfq_status_title.jsp" %>
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

<br><br><br>

<table border=0 cellpadding=0 cellspacing=0 width=680px height=200px>
<tr>
	<td valign=top align=center width=100%>
		<table border=0 cellpadding=2 cellspacing=0>
		<tr>
			<td align=right>Title:</td>
			<td><tsa:input type="text" name="DocAttachment_docTitle" size="60" maxLength="60"/></td>
		</tr>
		<tr>
			<td align=right>File to Attach:</td>
			<td><input type=file name=file3 size=45></td>
		</tr>
		<tr><td colspan=2><br></td></tr>
		<tr>
			<td colspan=2 align=center>
				<table border=0 cellpadding=2 cellspacing=0>
				<tr>
					<td align=center valign=middle><input type=checkbox name=ckboxPrint value="Y"></td>
					<td><b>Print with Solicitation</b></td>
				</tr>
<%		if (solicitationActive) {%>
				<tr>
					<td align=center valign=middle><input type=checkbox name=ckboxPost value="Y"></td>
					<td><b>Post with Solicitation</b></td>
				</tr>
<%		}%>
				</table>
			</td>
		</tr>
		</table>

		<br><br>

		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_attachment_add.jsp', '--'); void(0);">Add</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);">Return</a></div></td>
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
				if (frm.ckboxPost && frm.ckboxPost.checked) {
					frm.DocAttachment_docPost.value = "Y";
				} else {
					frm.DocAttachment_docPost.value = "N";
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