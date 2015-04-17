<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String	s_req_ic_header = (String) request.getAttribute("DocAttachment_icHeader");
	String	s_req_number = (String) request.getAttribute("RequisitionHeader_requisitionNumber");
	String	s_req_status = (String) request.getAttribute("RequisitionHeader_status");
	String	s_req_type = (String) request.getAttribute("RequisitionHeader_requisitionType");
	String	s_fiscal_year = (String) request.getAttribute("RequisitionHeader_fiscalYear");
	String	docType = (String) request.getAttribute("DocAttachment_docType");
	String	docTitle = (String) request.getAttribute("DocAttachment_docTitle");
	String	docFilename = (String) request.getAttribute("DocAttachment_docFilename");
	String	docPost = (String) request.getAttribute("DocAttachment_docPost");
	String	docPrint = (String) request.getAttribute("DocAttachment_docPrint");
	String	docSource = (String) request.getAttribute("DocAttachment_docSource");
	String	return_page = (String) request.getAttribute("return_page");
	String	return_handler = (String) request.getAttribute("return_handler");
	//String queueId = (String)request.getAttribute("queueId");
	String icHeader = s_req_ic_header;


	if (HiltonUtility.isEmpty(s_req_ic_header)) {
		throw new Exception("The ic header was not found.");
	}
%>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="DocAttachment_docType" value="<%=HiltonUtility.ckNull(docType)%>"/>
<tsa:hidden name="DocAttachment_docTitle" value="<%=HiltonUtility.ckNull(docTitle)%>"/>
<tsa:hidden name="DocAttachment_docFilename" value="<%=HiltonUtility.ckNull(docFilename)%>"/>
<tsa:hidden name="DocAttachment_docPost" value="<%=HiltonUtility.ckNull(docPost)%>"/>
<tsa:hidden name="DocAttachment_docPrint" value="<%=HiltonUtility.ckNull(docPrint)%>"/>
<tsa:hidden name="DocAttachment_docSource" value="<%=HiltonUtility.ckNull(docSource)%>"/>
<%
	if (HiltonUtility.isEmpty(s_req_number)) {
		s_req_number = "N/A";
	}


	String FilenameXls = HiltonUtility.ckNull(docFilename);
	String	ext = FilenameXls.substring(FilenameXls.lastIndexOf("."));
	int pos= FilenameXls.lastIndexOf(".");
	String FnameXls = FilenameXls.substring(0,pos);
	String Flag="V";
	System.out.println("FilenameXls= "+ FilenameXls);
	System.out.println("ext= "+ ext);
	System.out.println("FnameXls= "+ FnameXls);
	System.out.println("requisition_xls_upload_items_add := FilenameXLS= "+ FilenameXls);


%>

<tsa:hidden name="FilenameXls" value="<%=FnameXls%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="uploadXLS" defaultString="Upload XLS File" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b><tsa:label labelName="requisitionNumber" defaultString="Requisition #" />:</b></td>
			<td width=100px><%=headerEncoder.encodeForHTML(s_req_number)%></td>
		</tr>
		<tr>
			<td align=right><b><tsa:label labelName="status" defaultString="Status" />:</b></td>
			<td width=100px><%=DocumentStatus.toString(s_req_status, oid)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>

<td width=100% align=center valign=top><br><b><tsa:label labelName="processing" defaultString="Processing... Please wait" />.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td>
</tr>

<tr>
<td> </td>
</tr>
</table>


<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	var reqnumber = "<%=header.encodeForJavaScript(s_req_number)%>";
	var fiscalyear = "<%=s_fiscal_year%>";
	doSubmit('requests/req_review.jsp', 'RequisitionRetrieve');
	//doSubmit("<%//=return_page%>", "DocAttachmentAdd;<%//=return_handler%>");


// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>