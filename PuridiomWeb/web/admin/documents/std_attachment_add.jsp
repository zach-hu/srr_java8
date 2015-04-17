<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String	docType = (String) request.getAttribute("StdAttachment_docType");
	String	docTitle = (String) request.getAttribute("StdAttachment_docTitle");
	String	docFilename = (String) request.getAttribute("StdAttachment_docFilename");
	String	docPrint = (String) request.getAttribute("StdAttachment_docPrint");
	String	return_page = (String) request.getAttribute("return_page");
	String	return_handler = (String) request.getAttribute("return_handler");
%>
<tsa:hidden name="StdAttachment_docType" value="<%=HiltonUtility.ckNull(docType)%>"/>
<tsa:hidden name="StdAttachment_docTitle" value="<%=HiltonUtility.ckNull(docTitle)%>"/>
<tsa:hidden name="StdAttachment_docFilename" value="<%=HiltonUtility.ckNull(docFilename)%>"/>
<tsa:hidden name="StdAttachment_docPrint" value="<%=HiltonUtility.ckNull(docPrint)%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "stdattachments", "Standard Form Documents", false)%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
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
<tr><td width=100% align=center valign=top><br><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "Processing", "processing")%>... <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "pleaseWait", "Please wait")%>.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td></tr>
</table>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	doSubmit("<%=return_page%>", "StdAttachmentAdd;<%=return_handler%>");

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>