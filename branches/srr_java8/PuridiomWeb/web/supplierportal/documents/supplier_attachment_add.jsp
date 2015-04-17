<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%
	String	reqNumber = (String) request.getAttribute("RequisitionHeader_requisitionNumber");
	String	reqStatus = (String) request.getAttribute("RequisitionHeader_status");
	String	icHeader = (String) request.getAttribute("DocAttachment_icHeader");
	String	docType = (String) request.getAttribute("DocAttachment_docType");
	String	docTitle = (String) request.getAttribute("DocAttachment_docTitle");
	String	docFilename = (String) request.getAttribute("DocAttachment_docFilename");
	String	docPost = (String) request.getAttribute("DocAttachment_docPost");
	String	docPrint = (String) request.getAttribute("DocAttachment_docPrint");
	String	docSource = (String) request.getAttribute("DocAttachment_docSource");
	String	returnPage = (String) request.getAttribute("returnPage");
	String	returnHandler = (String) request.getAttribute("returnHandler");
	
	if (HiltonUtility.isEmpty(icHeader)) {
		throw new Exception("The ic header was not found.");
	}
%>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=icHeader%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=reqNumber%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=reqStatus%>"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=icHeader%>"/>
<tsa:hidden name="DocAttachment_docOrder" value="<%=HiltonUtility.ckNull(docOrder)%>"/>
<tsa:hidden name="DocAttachment_docType" value="<%=HiltonUtility.ckNull(docType)%>"/>
<tsa:hidden name="DocAttachment_docTitle" value="<%=HiltonUtility.ckNull(docTitle)%>"/>
<tsa:hidden name="DocAttachment_docFilename" value="<%=HiltonUtility.ckNull(docFilename)%>"/>
<tsa:hidden name="DocAttachment_docPost" value="<%=HiltonUtility.ckNull(docPost)%>"/>
<tsa:hidden name="DocAttachment_docPrint" value="<%=HiltonUtility.ckNull(docPrint)%>"/>
<tsa:hidden name="DocAttachment_docSource" value="<%=HiltonUtility.ckNull(docSource)%>"/>
<%
	if (HiltonUtility.isEmpty(reqNumber)) {
		reqNumber = "N/A";
	}
%>
<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td></tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Attachments</div>
			</td>
		</tr>
		</table>
	</td>
	<td width=30px valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" width="30" height="31" /></td>
	<td valign=bottom align=right height=30px>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Requisition #:</b></td>
			<td width=100px><%=headerEncoder.encodeForHTML(reqNumber)%></td>
		</tr>
		<tr>
			<td align=right><b>Status:</b></td>
			<td width=100px><%=DocumentStatus.toString(reqStatus, oid)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr><td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr><td width=100% align=center valign=top><br><b>Processing... Please wait.</b><br><br><br><img src="<%=contextPath%>/supplierportal/images/processing.gif" border=1 width=200px height=15px></td></tr>
</table>
<br>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	doSubmit("<%=returnPage%>", "DocAttachmentAdd;<%=returnHandler%>");

// end hiding contents -->
</SCRIPT>

<%@ include file="/supplierportal/system/prevent_caching.jsp" %>