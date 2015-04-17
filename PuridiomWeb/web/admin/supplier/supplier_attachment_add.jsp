<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%
	String	icHeader = "0";
	String	vendorId = (String) request.getAttribute("VendorDocument_vendorId");
	String	docTitle = (String) request.getAttribute("VendorDocument_docTitle");
	String	docFilename = (String) request.getAttribute("VendorDocument_docFilename");
	String	returnPage = (String) request.getAttribute("returnPage");
	String	returnHandler = (String) request.getAttribute("returnHandler");
	String	allowEdit = (String) request.getAttribute("allowEdit");
	String	contNumber = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_contNumber"));
	String icPoHeader = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_icPoHeader"));  //used for temp vendor add from PO process
%>
<tsa:hidden name="VendorDocument_icRfqHeader" value="<%=icHeader%>"/>
<tsa:hidden name="VendorDocument_docIc" value=""/>
<tsa:hidden name="VendorDocument_vendorId" value="<%=vendorId%>"/>
<tsa:hidden name="VendorDocument_docTitle" value="<%=HiltonUtility.ckNull(docTitle)%>"/>
<tsa:hidden name="VendorDocument_docFilename" value="<%=HiltonUtility.ckNull(docFilename)%>"/>
<tsa:hidden name="VendorInsurance_icPoHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=vendorId%>"/>
<tsa:hidden name="VendorInsurance_contNumber" value="<%=contNumber%>"/>
<tsa:hidden name="allowEdit" value="<%=allowEdit%>"/>
<tsa:hidden name="returnPage" value="<%=headerEncoder.encodeForHTMLAttribute(returnPage)%>"/>
<tsa:hidden name="returnHandler" value="<%=returnHandler%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=icPoHeader%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Adding Supplier Profile Document</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif"height="31" /></td>
	<td valign=bottom align=right height=30px>
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
<tr><td width=100% align=center valign=top><br><b>Processing... Please wait.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td></tr>
</table>
<br>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	doSubmit("/admin/supplier/supplier_attachments.jsp", "VendorDocumentAdd;VendorDocumentRetrieveByVendor");

// end hiding contents -->
</SCRIPT>
<%@ include file="/system/footer.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>