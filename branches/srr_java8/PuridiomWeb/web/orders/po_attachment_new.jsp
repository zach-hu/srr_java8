<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	String	icHeader = (String) request.getAttribute("DocAttachment_icHeader");
	String	s_ic_po_header = (String) request.getAttribute("PoHeader_icPoHeader");
	String	s_po_number = (String) request.getAttribute("PoHeader_poNumber");
	String	s_release_number = (String) request.getAttribute("PoHeader_releaseNumber");
	String	s_revision_number = (String) request.getAttribute("PoHeader_revisionNumber");
	String	s_po_type = (String) request.getAttribute("PoHeader_poType");
	String	s_po_status = (String) request.getAttribute("PoHeader_status");
	String s_vendor_id = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_vendorId"));
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);

	if (s_po_number == null)
	{
		s_po_number = (String) request.getAttribute("formNumber");
	}
	if (s_ic_po_header == null)
	{
		s_ic_po_header = (String) request.getAttribute("DocComment_icHeader");
	}
	if (s_po_status == null)
	{
		s_po_status = (String) request.getAttribute("formStatus");
	}
	String formtype = HiltonUtility.ckNull((String) request.getAttribute("formtype"));
	String s_fiscal_year = (String) request.getAttribute("PoHeader_fiscalYear");
	String s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_buyerCode"));
%>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=s_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=s_revision_number%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="PoHeader_buyerCode" value="<%=s_buyer_code%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="POH"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=icHeader%>"/>
<tsa:hidden name="DocAttachment_docIc" value=""/>
<tsa:hidden name="DocAttachment_docSource" value="POH"/>
<tsa:hidden name="DocAttachment_docPrint" value="N"/>
<tsa:hidden name="returnPage" value="/orders/po_attachments.jsp"/>
<tsa:hidden name="returnHandler" value="DocAttachmentRetrieveByHeader"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=s_vendor_id%>"/>
<tsa:hidden name="formtype" value="<%=formtype%>"/>
<%
	if (HiltonUtility.isEmpty(s_po_number)) {
		s_po_number = "N/A";
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="order-attachments" defaultString="Order Attachments" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
<%	int i_colspan = 1;%>
		<tr>
			<td nowrap align=right><b><tsa:label labelName="order-number" defaultString="Order #" />:</b></td>
			<td width=100px><%=headerEncoder.encodeForHTML(s_po_number)%></td>
<%	if (bd_release_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b><tsa:label labelName="release-number" defaultString="Release #" />:</b></td>
			<td width=100px><%=bd_release_number%></td>
<%	}
		if (bd_revision_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b><tsa:label labelName="revision-number" defaultString="Revision #" />:</b></td>
			<td width=100px><%=bd_revision_number%></td>
<%	} %>
		</tr>
		<tr>
			<td colspan=<%=i_colspan%> nowrap align=right><b><tsa:label labelName="status" defaultString="Status" />:</b></td>
			<td width=100px><%=DocumentStatus.toString(s_po_status, oid)%></td>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px height=200px>
<tr>
	<td valign=top align=center width=100%>
		<table border=0 cellpadding=2 cellspacing=0>
		<tr>
			<td align=right><tsa:label labelName="title" defaultString="Title" />:</td>
			<td><tsa:input type="text" name="DocAttachment_docTitle" size="60" maxLength="60" /></td>
		</tr>
		<tr>
			<td align=right><tsa:label labelName="file-to-attach" defaultString="File to Attach" />:</td>
			<td><input type=file name=file3 size=40></td>
		</tr>
		<tr><td colspan=2><br></td></tr>
		<tr>
			<td colspan=2 align=center><tsa:input type="checkbox" name="ckboxPrint" value="Y" />&nbsp;<b><tsa:label labelName="print-order-with" defaultString="Print with Order" /></b></td>
		</tr>
		</table>

		<br><br>

		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td align=center width=50%><div id="pxbutton"><a href="javascript: doSubmit('/orders/po_attachment_add.jsp', '--'); void(0);"><tsa:label labelName="add" defaultString="Add" /></a></div></td>
			<td align=center width=50%><div id="pxbutton"><a href="javascript: doSubmit('/orders/po_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);"><tsa:label labelName="return" defaultString="Return" /></a></div></td>
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
	var ponumber = "<%=headerEncoder.encodeForJavaScript(s_po_number)%>";

	validFileTypes = "<%=PropertiesManager.getInstance(oid).getProperty("DOCUMENTS", "ValidFileTypes", "")%>";

	frm.file3.contentEditable  = false;

	function validateForm() {
		var handler = frm.handler.value;

		if (handler.indexOf("--") >= 0) {
			var doc = frm.file3.value;
			var title = frm.DocAttachment_docTitle.value;
			var alertMessage = "";

			if ( isEmpty(title) && isEmpty(doc) ) {
				alert("<tsa:label labelName='title-and-file-msg' defaultString='Please enter a Title and select a File to Attach.' />");
				return false;
			}
			else if (isEmpty(doc)) {
				alert("<tsa:label labelName='file-msg' defaultString='Please select a file to attach.' />");
				return false;
			}
			else if (!isAttachmentExtValid(doc)) {
				return false;
			}
			else if (isEmpty(title)) {
				alert("<tsa:label labelName='title-msg' defaultString='Please enter a Document Title.' />");
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
			}
		}
		return true;
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>