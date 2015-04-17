<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	displayPrintOption = propertiesManager.getProperty("ATTACHMENTS", "DisplayPrintOption", "Y");
	String	s_req_ic_header = (String) request.getAttribute("DocAttachment_icHeader");
	String	s_req_number = (String) request.getAttribute("RequisitionHeader_requisitionNumber");
	String	s_req_status = (String) request.getAttribute("RequisitionHeader_status");
	String	s_fiscal_year = (String) request.getAttribute("RequisitionHeader_fiscalYear");
	String	s_req_type = (String) request.getAttribute("RequisitionHeader_requisitionType");
	String	s_req_subtype = (String) request.getAttribute("RequisitionHeader_rqSubType");
	String	return_page = "";
	boolean editMode = false;

	if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0) {
		editMode = true;
	}
	if (s_view.equals("WIZARD")) {
		return_page = "/requests/req_review.jsp";
	} else {
		return_page = "/requests/req_summary.jsp";
	}

	String icHeaderEdit = HiltonUtility.ckNull((String) request.getAttribute("icHeaderEdit"));
	String icHeaderHistoryEdit = HiltonUtility.ckNull((String) request.getAttribute("icHeaderHistoryEdit"));
	String formtype = HiltonUtility.ckNull((String) request.getAttribute("formtype"));
%>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="DocAttachment_docIc" value=""/>
<tsa:hidden name="DocAttachment_docSource" value="RQH"/>
<tsa:hidden name="DocAttachment_docPrint" value="N"/>
<tsa:hidden name="return_page" value="/requests/req_attachments.jsp"/>
<tsa:hidden name="return_handler" value="DocAttachmentRetrieveByHeader"/>
<tsa:hidden name="icHeaderEdit" value="<%=icHeaderEdit%>"/>
<tsa:hidden name="icHeaderHistoryEdit" value="<%=icHeaderHistoryEdit%>"/>
<tsa:hidden name="formtype" value="<%=formtype%>"/>

<%
	if (HiltonUtility.isEmpty(s_req_number)) {
		s_req_number = "N/A";
	}
%>

<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12">				
				<tsa:label labelName="attachments" defaultString="Attachments"/>
				</div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
		<tsa:tr>
			<tsa:td align="right"><b>			
			<tsa:label labelName="req-requisition" defaultString="Requisition #"></tsa:label>:</b>
			</tsa:td>
			<tsa:td width="150px"><%=headerEncoder.encodeForHTML(s_req_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b>
			
			<tsa:label labelName="status" defaultString="status" />:
			</b></tsa:td>
			<tsa:td width="150px"><%=DocumentStatus.toString(s_req_status, oid)%></tsa:td>
		</tsa:tr>
		</table>
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tsa:tr>
			<tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br><br><br>

<table border="0" cellpadding="0" cellspacing="0" width="680px" height="200px">
<tsa:tr>
	<tsa:td valign="top" align="center" width="100%">
		<table border="0" cellpadding="2" cellspacing="0">
		<tsa:tr>
			<tsa:td align="right">
			<tsa:label labelName="title" defaultString="Title" />:
			</tsa:td>
			<tsa:td><tsa:input type="text" name="DocAttachment_docTitle" size="60" maxLength="60" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right">
			<tsa:label labelName="fileToAtach" defaultString="File to Attach" />:
			</tsa:td>
			<tsa:td><input type="file" name="file3" size="45"></tsa:td>
		</tsa:tr>
		<tsa:tr><tsa:td colspan="2"><br></tsa:td></tsa:tr>
<%	if (displayPrintOption.equalsIgnoreCase("Y")) {	%>
		<tsa:tr>
			<tsa:td colspan="2" align="center"><tsa:input type="checkbox" name="ckboxPrint" value="Y" />&nbsp;<b>
			<tsa:label labelName="req-print" defaultString="Print with Requisition" />:				
			</b></tsa:td>
		</tsa:tr>
<%	} else {	%>
			<tsa:hidden name="ckboxPrint" value="Y"/>
<%	}	%>
		</table>

		<br><br>

		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tsa:tr>
			<tsa:td align="center" width="50%"><div id="pxbutton"><a href="javascript: option=0; doSubmit('/requests/req_attachment_add.jsp', '--'); void(0);"><tsa:label labelName="req-Add" defaultString="Add" /></a></div></tsa:td>
			<tsa:td align="center" width="50%"><div id="pxbutton"><a href="javascript: option=1; doSubmit('/requests/req_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);"><tsa:label labelName="req-Return" defaultString="Return" /></a></div></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	var oldAction = frm.action;
	frm.action =" javascript: validateForm();";
	var reqnumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
	var fiscalyear = "<%=headerEncoder.encodeForJavaScript(s_fiscal_year)%>";
	var option = -1;
	validFileTypes = "<%=PropertiesManager.getInstance(oid).getProperty("DOCUMENTS", "ValidFileTypes", "")%>";

	frm.file3.contentEditable  = false;

	function validateForm() {
		if(option == 0){
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
		}else if(option == 1){
				frm.action = oldAction;
				frm.submit();
			}
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>