<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%
	String	rfqStatus = (String) request.getAttribute("RfqHeader_status");
	String	icHeader = (String) request.getAttribute("VendorDocument_icRfqHeader");
	String	vendorId = (String) request.getAttribute("VendorDocument_vendorId");
	String	docTitle = (String) request.getAttribute("VendorDocument_docTitle");
	String	docFilename = (String) request.getAttribute("VendorDocument_docFilename");
	String	returnPage = (String) request.getAttribute("returnPage");
	String	returnHandler = (String) request.getAttribute("returnHandler");
	
	if (HiltonUtility.isEmpty(icHeader)) {
		throw new Exception("VendorDocument_icRfqHeader was not found - /rfq/supplier_attachment_add.jsp");
	}
%>
<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=icHeader%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=rfqStatus%>"/>
<tsa:hidden name="VendorDocument_icRfqHeader" value="<%=icHeader%>"/>
<tsa:hidden name="VendorDocument_docIc" value=""/>
<tsa:hidden name="VendorDocument_vendorId" value="<%=vendorId%>"/>
<tsa:hidden name="VendorDocument_docTitle" value="<%=HiltonUtility.ckNull(docTitle)%>"/>
<tsa:hidden name="VendorDocument_docFilename" value="<%=HiltonUtility.ckNull(docFilename)%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td></tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Response Documents</div>
			</td>
		</tr>
		</table>
	</td>
	<td width=30px valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" width="30" height="31" /></td>
	<td valign=bottom align=right height=30px>
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

	doSubmit("<%=returnPage%>", "VendorDocumentAdd;<%=returnHandler%>");

// end hiding contents -->
</SCRIPT>

<%@ include file="/supplierportal/system/prevent_caching.jsp" %>