<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>

<%
	String	vendorName = (String)  request.getAttribute("Vendor_vendorName");
	String	contNumber = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_contNumber"));
	String icPoHeader = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_icPoHeader"));  //used for temp vendor add from PO process
	String	returnPage = (String) request.getAttribute("returnPage");
	String	returnHandler = (String) request.getAttribute("returnHandler");
	String stdTableType = HiltonUtility.ckNull((String) request.getAttribute("StdTable_tableType"));
	String	allowEdit = (String) request.getAttribute("allowEdit");
	String	attachmentType = "Supplier Profile";
	boolean editMode = true;
%>

<tsa:hidden name="Vendor_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="Vendor_vendorName" value="<%=vendorName%>"/>
<tsa:hidden name="VendorDocument_icRfqHeader" value="0"/>
<tsa:hidden name="VendorDocument_docIc" value=""/>
<tsa:hidden name="VendorDocument_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="VendorInsurance_icPoHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="VendorInsurance_contNumber" value="<%=contNumber%>"/>
<tsa:hidden name="allowEdit" value="<%=allowEdit%>"/>
<tsa:hidden name="returnPage" value="<%=headerEncoder.encodeForHTMLAttribute(returnPage)%>"/>
<tsa:hidden name="returnHandler" value="<%=returnHandler%>"/>
<tsa:hidden name="StdTable_tableType" value="<%=stdTableType%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=icPoHeader%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Add <%=attachmentType%> Document</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif"height="31" /></td>
	<td valign=bottom align=right height=30px width=*>
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
			<td><input type=text name="VendorDocument_docTitle" size=60 maxLength=60></td>
		</tr>
		<tr>
			<td align=right>File to Attach:</td>
			<td><input type=file name=file3 size=40></td>
		</tr>
		</table>

		<br><br>

		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td align=center width=50%><div id="pxbutton"><a href="javascript: addDocument(); void(0);">Add</a></div></td>
			<td align=center width=50%><div id="pxbutton"><a href="javascript: doSubmit('/admin/supplier/supplier_attachments.jsp', 'VendorDocumentRetrieveByVendor'); void(0);">Return</a></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>
<br>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	function validateForm() {
		var handler = frm.handler.value;

		if (handler.indexOf("--") >= 0) {
			var doc = frm.file3.value;
			var title = frm.VendorDocument_docTitle.value;
			var alertMessage = "";

			if ( isEmpty(title) && isEmpty(doc) ) {
				alert("Please enter a Title and select a File to Attach.");
				return false;
			}
			else if (isEmpty(doc)) {
				alert('Please select a file to attach.');
				return false;
			}
			else if (isEmpty(title)) {
				alert('Please enter a Document Title.');
				return false;
			}
			else {
				frm.action =" <%=contextPath%>/HiltonExternalDocumentUploadServlet";
				frm.action = frm.action + "?" + frm.organizationId.value;
				frm.enctype = "multipart/form-data";
				frm.encoding = "multipart/form-data";
			}
		}
		return true;
	}

// end hiding contents -->
</SCRIPT>
<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	function addDocument() {
		doSubmit('/admin/supplier/supplier_attachment_add.jsp', '--');
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>