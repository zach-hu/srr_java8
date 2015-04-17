<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.StdDocumentType" %>

<tsa:hidden name="StdDocument_fileName" value=""/>

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

<br><br><br>

<table border=0 cellpadding=0 cellspacing=0 width=680px height=200px>
<tr>
	<td valign=top align=center width=100%>
		<table border=0 cellpadding=2 cellspacing=0>
		<tr>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "title", "Title")%>:</td>
			<td><input type=text name="StdDocument_title" size=60 maxLength=60></td>
		</tr>
		<tr>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "documentType", "Document Type")%>:</td>
			<td>
				<select name="StdDocument_docType">
					<option value="<%=StdDocumentType.STANDARD_DOCUMENT%>" SELECTED><%=StdDocumentType.toString(StdDocumentType.STANDARD_DOCUMENT, oid)%></option>
					<option value="<%=StdDocumentType.OTHER_DOCUMENT%>"><%=StdDocumentType.toString(StdDocumentType.OTHER_DOCUMENT, oid)%></option>
				</select>
			</td>
		</tr>
		<tr>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fileToAdd", "File to Add")%>:</td>
			<td><input type=file name=file3 size=40></td>
		</tr>
		<tr><td colspan=2><br></td></tr>
		</table>

		<br><br>

		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td align=center width=50%><a href="javascript: doSubmit('/admin/documents/std_document_add.jsp', '--'); void(0);"><img src="<%=contextPath%>/images/button_add.gif" border=0 class=button></a></td>
			<td align=center width=50%><a href="javascript: doSubmit('/admin/documents/std_documents.jsp', 'StdDocumentRetrieveAll'); void(0);"><img src="<%=contextPath%>/images/button_return.gif" border=0 class=button></a></td>
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
	setNavCookie("/admin/documents/std_document_new.jsp", "DoNothing", "New Document");

	function validateForm() {
		var handler = frm.handler.value;

		if (handler.indexOf("--") >= 0) {
			var doc = frm.file3.value;
			var title = frm.StdDocument_title.value;
			var alertMessage = "";

			if ( isEmpty(title) && isEmpty(doc) ) {
				alert("Please enter a Title and select a File to Add.");
				return false;
			}
			else if (isEmpty(doc)) {
				alert('Please select a File to Add.');
				return false;
			}
			else if (isEmpty(title)) {
				alert('Please enter a Document Title.');
				return false;
			}
			else {
				frm.action =" <%=contextPath%>/HiltonDocumentUploadServlet";
				frm.action = frm.action + "?" + frm.organizationId.value;
				frm.enctype = "multipart/form-data";
				frm.encoding = "multipart/form-data";
			}
		}
		return true;
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>