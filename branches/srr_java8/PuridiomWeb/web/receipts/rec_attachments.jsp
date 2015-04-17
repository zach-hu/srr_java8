<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/attachments.js"></script>
<%
	String	icHeader = (String) request.getAttribute("DocAttachment_icHeader");
	String	receiptNumber = (String) request.getAttribute("ReceiptHeader_receiptNumber");
	String	receiptType = (String) request.getAttribute("ReceiptHeader_receiptType");
	String	poStatus = (String) request.getAttribute("PoHeader_poStatus");
	String	poNumber = (String) request.getAttribute("PoHeader_poNumber");
	String	s_fiscal_year = (String) request.getAttribute("PoHeader_fiscalYear");
	String	releaseNumber = (String) request.getAttribute("PoHeader_releaseNumber");
	String	revisionNumber = (String) request.getAttribute("PoHeader_revisionNumber");
	String	allowEdit = (String) request.getAttribute("allowEdit");
	String	returnPage = (String) request.getAttribute("returnPage");
	String	receiptMethod = (String) request.getAttribute("receiptMethod");
	if (HiltonUtility.isEmpty(returnPage))
	{
		returnPage = "/receipts/rec_confirmation.jsp";
	}
	String	returnHandler = (String) request.getAttribute("returnHandler");
	if (HiltonUtility.isEmpty(returnHandler))
	{
		returnHandler = "DocAttachmentRetrieveByHeader";
	}
	String	browseId = HiltonUtility.ckNull((String) request.getAttribute("browseId"));
	boolean editMode = false;

	allowEdit = HiltonUtility.ckNull(allowEdit);

	if (allowEdit.equalsIgnoreCase("Y")) {
		editMode = true;
	}
%>
<tsa:hidden name="DocAttachment_icHeader" value="<%=icHeader%>"/>
<tsa:hidden name="DocAttachment_delete" value="FALSE"/>
<tsa:hidden name="hasDocs" value="NO"/>
<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=icHeader%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="<%=receiptType%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=receiptNumber%>"/>
<tsa:hidden name="PoHeader_poStatus" value="<%=poStatus%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=poNumber%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=releaseNumber%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=revisionNumber%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>

<tsa:hidden name="filename" value=""/>
<tsa:hidden name="returnPage" value="<%=returnPage%>"/>
<tsa:hidden name="returnHandler" value="<%=returnHandler%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="allowEdit" value="<%=allowEdit%>"/>
<tsa:hidden name="browseId" value="<%=browseId%>"/>
<%
	if (HiltonUtility.isEmpty(receiptNumber)) {
		receiptNumber = "N/A";
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Receipt Attachments</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Receipt #:</b></td>
			<td width=100px><%=receiptNumber%></td>
		</tr>
		<tr>
			<td align=right><b>Receipt Type:</b></td>
			<td width=100px><%=ReceiptType.toString(receiptType, oid)%></td>
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

<br>
<br>

<%@ include file="/base/attachment_list.jsp" %>
<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: saveAttachments(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('<%=returnPage%>', '<%=returnHandler%>'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var myTable = document.getElementById("attachments");
	var totalRows = myTable.rows.length
	var inEditMode = "<%=editMode%>";
	var fiscalyear = "<%=s_fiscal_year%>";
	var currentPage = "/receipts/rec_attachments.jsp";
	var returnPage = "<%=returnPage%>";
	var returnHandler = "<%=returnHandler%>";

	function validateForm() {
		if (frm.DocAttachment_delete.value == 'TRUE') { return true ; }
		var newInputFields = "";
		var handlers = "DocAttachmentUpdateByHeader;" + returnHandler;

		if (totalRows > 1) {
		    frm.hasDocs.value = "YES" ;
			for (var i=0; i < totalRows; i++) {
				var docIc = frm.docIc[i].value;
				var docTitle = frm.docTitle[i].value;
				var docPrint = frm.docPrint[i].value;
				var docPost = "N";

				if (frm.docPost != null && frm.docPost != undefined) {
					docPost = frm.docPost[i].value;
				}

				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docIc' value='" + docIc + "'>";
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docTitle' value='" + docTitle + "'>";
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPrint' value='" + docPrint + "'>";
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPost' value='" + docPost + "'>";
			}
		} else if (totalRows == 1) {
			newInputFields = "<input type='hidden' name='DocAttachment_docIc' value='" + frm.docIc.value + "'>";
			newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docTitle' value='" + frm.docTitle.value + "'>";
			newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPrint' value='" + frm.docPrint.value + "'>";
			if (frm.docPost != null && frm.docPost != undefined) {
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPost' value='" + frm.docPost.value + "'>";
			} else {
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPost' value='N'>";
			}
		} else {
		}
		setHiddenFields(newInputFields);
		return true ;
	}

	function addNewDocument() {
		doSubmit('/receipts/rec_attachment_new.jsp', 'DoNothing');
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>