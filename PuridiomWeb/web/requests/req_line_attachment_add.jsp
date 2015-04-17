<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String	s_req_ic_header = (String) request.getAttribute("DocAttachment_icHeader");
	String	s_ic_req_header = (String) request.getAttribute("RequisitionLine_icReqHeader");
	String	s_ic_req_line = (String) request.getAttribute("RequisitionLine_icReqLine");

	String	icLine = (String) request.getAttribute("DocAttachment_icLine");
	String	s_line_count = (String) request.getAttribute("lineCount");

	String	s_req_number = (String) request.getAttribute("RequisitionHeader_requisitionNumber");
	String	s_req_status = (String) request.getAttribute("RequisitionHeader_status");
	String	s_req_type = (String) request.getAttribute("RequisitionHeader_requisitionType");
	String	s_req_subtype = (String) request.getAttribute("RequisitionHeader_rqSubType");
	String	s_fiscal_year = (String) request.getAttribute("RequisitionHeader_fiscalYear");
	String	docType = (String) request.getAttribute("DocAttachment_docType");
	String	docTitle = (String) request.getAttribute("DocAttachment_docTitle");
	String	docFilename = (String) request.getAttribute("DocAttachment_docFilename");
	String	docPost = (String) request.getAttribute("DocAttachment_docPost");
	String	docPrint = (String) request.getAttribute("DocAttachment_docPrint");
	String	docSource = (String) request.getAttribute("DocAttachment_docSource");
	String	return_page = (String) request.getAttribute("return_page");
	String	return_handler = (String) request.getAttribute("return_handler");
	String s_line_number = HiltonUtility.ckNull((String) request.getAttribute("RequisitionLine_lineNumber"));

	if (HiltonUtility.isEmpty(s_req_ic_header)) {
		throw new Exception("The ic header was not found.");
	}
	pageContext.setAttribute("oid", oid);
%>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=s_ic_req_header%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value="<%=s_ic_req_line%>"/><!-- aki -->

<tsa:hidden name="DocAttachment_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="DocAttachment_icLine" value="<%=icLine%>"/>
<tsa:hidden name="DocAttachment_docType" value="<%=HiltonUtility.ckNull(docType)%>"/>
<tsa:hidden name="DocAttachment_docTitle" value="<%=HiltonUtility.ckNull(docTitle)%>"/>
<tsa:hidden name="DocAttachment_docFilename" value="<%=HiltonUtility.ckNull(docFilename)%>"/>
<tsa:hidden name="DocAttachment_docPost" value="<%=HiltonUtility.ckNull(docPost)%>"/>
<tsa:hidden name="DocAttachment_docPrint" value="<%=HiltonUtility.ckNull(docPrint)%>"/>
<tsa:hidden name="DocAttachment_docSource" value="<%=HiltonUtility.ckNull(docSource)%>"/>
<tsa:hidden name="formtype" value="REQ"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="RequisitionLine_lineNumber" value="<%=s_line_number%>"/>
<tsa:hidden name="RequisitionLine_status" value="<%=s_req_status%>"/>

<%
	if (HiltonUtility.isEmpty(s_req_number)) {
		s_req_number = "N/A";
	}
%>
<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr><tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td></tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12">

<tsa:label labelName="attachments" defaultString="Attachments" /></div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
		<tsa:tr>
			<tsa:td align="right"><b>
<tsa:label labelName="requisitionNumber" defaultString="Requisition #" />:</b></tsa:td>
			<tsa:td width="100px"><%=headerEncoder.encodeForHTML(s_req_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b>
<tsa:label labelName="status" defaultString="Status" />:</b></tsa:td>
			<tsa:td width="100px"><%=DocumentStatus.toString(s_req_status, oid)%></tsa:td>
		</tsa:tr>
		</table>
		<table cellpadding="0" cellspacing="0" border="0">
		<tsa:tr><tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td></tsa:tr>
		<tsa:tr><tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td></tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr><tsa:td width="100%" align="center" valign="top"><br><b>
<tsa:label labelName="processing" defaultString="Processing... Please wait" />.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border="1" width="200px" height="15px"></tsa:td></tsa:tr>
</table>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	var reqnumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
	var fiscalyear = "<%=headerEncoder.encodeForJavaScript(s_fiscal_year)%>";

	doSubmit('<%=return_page%>', 'DocAttachmentAdd;<%=return_handler%>');

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>