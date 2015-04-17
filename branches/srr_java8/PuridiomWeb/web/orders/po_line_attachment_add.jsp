<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
try{
	String	s_ic_po_header = (String) request.getAttribute("PoHeader_icPoHeader");
	String	s_ic_po_line = (String) request.getAttribute("PoLine_icPoLine");

	String	s_po_number = (String) request.getAttribute("PoHeader_poNumber");

	String	s_release_number = (String) request.getAttribute("PoHeader_releaseNumber");
	String	s_revision_number = (String) request.getAttribute("PoHeader_revisionNumber");
	String	s_po_type = (String) request.getAttribute("PoHeader_poType");
	String	s_po_status = (String) request.getAttribute("PoHeader_status");
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	String	icHeader = (String) request.getAttribute("DocAttachment_icHeader");
	String	icLine = (String) request.getAttribute("DocAttachment_icLine");

	String	docType = (String) request.getAttribute("DocAttachment_docType");
	String	docTitle = (String) request.getAttribute("DocAttachment_docTitle");
	String	docFilename = (String) request.getAttribute("DocAttachment_docFilename");
	String	docPost = (String) request.getAttribute("DocAttachment_docPost");
	String	docPrint = (String) request.getAttribute("DocAttachment_docPrint");
	String	docSource = (String) request.getAttribute("DocAttachment_docSource");
	String	returnPage = (String) request.getAttribute("returnPage");
	String	returnHandler = (String) request.getAttribute("returnHandler");
	String s_vendor_id = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_vendorId"));
	String s_line_number = HiltonUtility.ckNull((String) request.getAttribute("PoLine_lineNumber"));

	String	s_line_count = (String) request.getAttribute("lineCount");

	if (HiltonUtility.isEmpty(icHeader)) {
		throw new Exception("The ic header was not found.");
	}

	String bd_ic_po_line_key = HiltonUtility.ckNull((String) request.getAttribute("PoLine_icRelKey"));
	String bd_ic_po_line_req = HiltonUtility.ckNull((String) request.getAttribute("PoLine_icReqLine"));
	String bd_ic_po_line_rfq = HiltonUtility.ckNull((String) request.getAttribute("PoLine_icRfqLine"));
	String s_fiscal_year = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_fiscalYear"));
	String s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_buyerCode"));
%>
<input type="hidden" name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>">
<input type="hidden" name="PoLine_icPoHeader" value="<%=s_ic_po_header%>">
<input type="hidden" name="PoLine_icPoLine" value="<%=s_ic_po_line%>"><!-- aki -->
<input type="hidden" name="PoHeader_poNumber" value="<%=s_po_number%>">
<input type="hidden" name="PoHeader_poType" value="<%=s_po_type%>">
<input type="hidden" name="PoHeader_releaseNumber" value="<%=s_release_number%>">
<input type="hidden" name="PoHeader_revisionNumber" value="<%=s_revision_number%>">
<input type="hidden" name="PoHeader_status" value="<%=s_po_status%>">
<input type=hidden name=PoLine_icRelKey value="<%=bd_ic_po_line_key%>">
<input type=hidden name=PoLine_icReqLine value="<%=bd_ic_po_line_req%>">
<input type=hidden name=PoLine_icReqLineOld value="<%=bd_ic_po_line_req%>">
<input type=hidden name=PoLine_icRfqLine value="<%=bd_ic_po_line_rfq%>">
<input type="hidden" name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>">
<input type="hidden" name="PoHeader_buyerCode" value="<%=s_buyer_code%>">
<input type="hidden" name="Account_icHeader" value="<%=s_ic_po_header%>">
<input type="hidden" name="Account_icLine" value="0">
<input type="hidden" name="Account_accountType" value="POH">
<input type="hidden" name="DocAttachment_icHeader" value="<%=icHeader%>">
<input type="hidden" name="DocAttachment_icLine" value="<%=icLine%>">
<input type="hidden" name="DocAttachment_docType" value="<%=HiltonUtility.ckNull(docType)%>">
<input type="hidden" name="DocAttachment_docTitle" value="<%=HiltonUtility.ckNull(docTitle)%>">
<input type="hidden" name="DocAttachment_docFilename" value="<%=HiltonUtility.ckNull(docFilename)%>">
<input type="hidden" name="DocAttachment_docPost" value="<%=HiltonUtility.ckNull(docPost)%>">
<input type="hidden" name="DocAttachment_docPrint" value="<%=HiltonUtility.ckNull(docPrint)%>">
<input type="hidden" name="DocAttachment_docSource" value="<%=HiltonUtility.ckNull(docSource)%>">
<input type="hidden" name="VendorInsurance_vendorId" value="<%=s_vendor_id%>">
<input type=hidden name=formtype value="PO">
<input type=hidden name=lineCount value="<%=s_line_count%>">
<input type=hidden name=PoLine_lineNumber value="<%=s_line_number%>">
<input type=hidden name=PoLine_status value="<%=s_po_status%>">
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Notes & Attachments</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
<%	int i_colspan = 1;%>
		<tr>
			<td nowrap align=right><b>Order #:</b></td>
			<td width=100px><%=s_po_number%></td>
<%	if (bd_release_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b>Release #:</b></td>
			<td width=100px><%=bd_release_number%></td>
<%	}
		if (bd_revision_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b>Revision #:</b></td>
			<td width=100px><%=bd_revision_number%></td>
<%	} %>
		</tr>
		<tr>
			<td colspan=<%=i_colspan%> nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
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

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr><td width=100% align=center valign=top><br><b>Processing... Please wait.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td></tr>
</table>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	doSubmit('<%= headerEncoder.encodeForJavaScript(returnPage) %>', 'DocAttachmentAdd;<%= headerEncoder.encodeForJavaScript(returnHandler) %>');

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>

<%}catch(Exception e){}

%>