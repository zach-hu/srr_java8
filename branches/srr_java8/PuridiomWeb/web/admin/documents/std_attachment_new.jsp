<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String	returnPage = "/admin/documents/std_attachments.jsp";
%>
<tsa:hidden name="StdAttachment_docIc" value=""/>
<tsa:hidden name="StdAttachment_docPrint" value="N"/>
<tsa:hidden name="return_page" value="<%=headerEncoder.encodeForHTMLAttribute(returnPage)%>"/>
<tsa:hidden name="return_handler" value="StdAttachmentRetrieveAll"/>

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

<br><br><br>

<table border=0 cellpadding=0 cellspacing=0 width=680px height=200px>
<tr>
	<td valign=top align=center width=100%>
		<table border=0 cellpadding=2 cellspacing=0>
		<tr>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "title", "Title")%>:</td>
			<td><input type=text name="StdAttachment_docTitle" size=60 maxLength=60></td>
		</tr>
		<tr>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fileToAttach", "File to Attach")%>:</td>
			<td><input type=file name=file3 size=45></td>
		</tr>
		<tr><td colspan=2><br></td></tr>
		<tr>
			<td colspan=2 align=center><input type=checkbox name=ckboxPrint value="Y">&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "defaultPrintOption", "Default Print Option")%></b></td>
		</tr>
		</table>

		<br><br>

		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td align=center width=50%><div id="pxbutton"><a href="javascript: doSubmit('/admin/documents/std_attachment_add.jsp', '--'); void(0);">Add</a></div></td>
			<td align=center width=50%><div id="pxbutton"><a href="javascript: doSubmit('//admin/documents/std_attachments.jsp', 'StdAttachmentRetrieveAll'); void(0);">Return</a></div></td>
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
			var title = frm.StdAttachment_docTitle.value;
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
					frm.StdAttachment_docPrint.value = "Y";
				} else {
					frm.StdAttachment_docPrint.value = "N";
				}
				frm.action =" <%=contextPath%>/HiltonStandardFormUploadServlet";
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