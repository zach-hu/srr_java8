<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String	docFilename = (String) request.getAttribute("StdDocument_fileName");

	if (HiltonUtility.isEmpty(docFilename)) {
		throw new Exception("The filename was not found.");
	}
%>
<tsa:hidden name="StdDocument_fileName" value="<%=docFilename%>"/>
<tsa:hidden name="StdDocument_docType" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"StdDocument_docType\"))%>"/>
<tsa:hidden name="StdDocument_title" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"StdDocument_title\"))%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "standardBidboardDocuments", "Standard Bidboard Documents")%></div>
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
<tr><td width=100% align=center valign=top><br><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "", "Processing")%>... <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "pleaseWait", "Please wait")%>.</b><br><br><br></td>
</tr>
</table>
<img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px><br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	doSubmit('/admin/documents/std_documents.jsp', 'StdDocumentAdd;StdDocumentRetrieveAll');

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>