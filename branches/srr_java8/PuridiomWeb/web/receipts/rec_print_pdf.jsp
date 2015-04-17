<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	String	labels = (String)request.getAttribute("labels");
	if(labels == null){
		labels = "N";
	}
	String	s_ic_rec_header = HiltonUtility.ckNull((String)request.getAttribute("ReceiptHeader_icRecHeader"));
	String	s_rec_number = HiltonUtility.ckNull((String)request.getAttribute("ReceiptHeader_receiptNumber"));
	String	s_rec_type = HiltonUtility.ckNull((String)request.getAttribute("ReceiptHeader_receiptType"));
	String	s_rec_status = HiltonUtility.ckNull((String)request.getAttribute("ReceiptHeader_receiptStatus"));
	String	s_rec_fiscal_year = HiltonUtility.ckNull((String)request.getAttribute("ReceiptHeader_fiscalYear"));
	if (HiltonUtility.isEmpty(s_rec_status)) {
		s_rec_status = DocumentStatus.RCV_INPROGRESS;
	}

	String	s_ic_po_header = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_icPoHeader"));
	String	s_po_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_poNumber"));
	String	s_revision_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_revisionNumber"));
	String	s_release_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_releaseNumber"));
	if (HiltonUtility.isEmpty(s_revision_number)) {
		s_revision_number = "0";
	}
	if (HiltonUtility.isEmpty(s_release_number)) {
		s_release_number = "0";
	}
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);

	String	receiptMethod = HiltonUtility.ckNull((String)request.getAttribute("receiptMethod"));
	BigDecimal	bd_zero = new BigDecimal(0);
	
	String	s_ic_req_header = HiltonUtility.ckNull((String)request.getAttribute("RequisitionHeader_icReqHeader"));
%>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=s_rec_number%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="<%=s_rec_type%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=s_rec_status%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=s_rec_fiscal_year%>"/>
<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RCH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="formType" value="REC"/>
<tsa:hidden name="allowBrowse" value="true"/>

<tsa:hidden name="emailTo" value="N"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receipt", "Receipt")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td>
		<%	if (!HiltonUtility.isEmpty(s_po_number)) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Order #:</b>&nbsp;<%=s_po_number%>
		<%	} %>
			</td>
			<td align=right><b>Receipt #:</b></td>
		<%	if (HiltonUtility.isEmpty(s_rec_number)) { %>
			<td width=100px>N/A</td>
		<%	} else { %>
			<td width=100px><%=s_rec_number%></td>
		<%	} %>
		</tr>
		<tr>
			<td>
		<%	if (bd_revision_number.compareTo(bd_zero) > 0) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Revision #:</b>&nbsp;<%=bd_revision_number%>
		<%	} %>
		<%	if (bd_release_number.compareTo(bd_zero) > 0) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Release #:</b>&nbsp;<%=bd_release_number%>
		<%	} %>
			</td>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=120px><%=DocumentStatus.toString(s_rec_status, oid)%></td>
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
<table width=505px cellpadding=0 cellspacing=0 border=0>
<tr>
<%	if (HiltonUtility.isEmpty(s_rec_number))
		s_rec_number = "N/A";
%>
  <td align="center">
	<%	if (receiptMethod.equals("ReceiveByPackage")) { %>
		<span class="formType"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiveByPackage", "Receipt Package")%> </span><span class="hdr12">#<%=s_rec_number%></span>
	<%	} else if (receiptMethod.equals("FinalizeReceipt")) { %>
		<span class="formType"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "finalizeReceipt", "Finalize Receipt")%> </span><span class="hdr12">#<%=s_rec_number%></span>
	<%	} else if (receiptMethod.equals("ReceiveByOrder")) { %>
		<span class="formType"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiveByOrder", "Full Receipt From Order")%> </span><span class="hdr12">#<%=s_rec_number%></span>
	<%	} else if (receiptMethod.equals("ReceiveFromNothing")) { %>
		<span class="formType"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiveFromNothing", "Full Receipt From Nothing")%> </span><span class="hdr12">#<%=s_rec_number%></span>
	<%	} else { %>
		<span class="formType"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receipt", "Receipt")%> </span><span class="hdr12">#<%=s_rec_number%></span>
	<%	} %>
  </td>
</tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="505px">
<%@ include file="/print/print_options_general.jsp" %>
</table>
<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="455px">
	<TR>
		<td align="center"><div id="pxbutton"><a href="javascript: <%if(labels.equals("Y")) {%>printLabels();<% }else{%>pdfOptions();<%} %>">Print</a></div></td>
		<TD align="CENTER"><div id="pxbutton"><a href="javascript: doSubmit('receipts/rec_review.jsp', 'DoNothing;ReceiptRetrieve');">Return</a></div></TD>
	</TR>
</TABLE>
<br/>
<%@ include file="/system/footer.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();

	function selectEmailTo()
	{
		frm.print_option[1].checked = true;
	}

	function viewNow()
	{
		popupParameters = "ReceiptHeader_icRecHeader=<%=s_ic_rec_header%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		frm.emailTo.value = 'N';
		popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
		doSubmitToLookup('', 'PrintRecPdf', 'width=775px', 'height=850px');
	}

	function emailPdf()
	{
		popupParameters = "ReceiptHeader_icRecHeader=<%=s_ic_rec_header%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		if(checkemail())
		{
			frm.viewNow.value = 'N';
			frm.emailTo.value = 'Y';
			doSubmit('receipts/rec_print_pdf.jsp', 'EmailRecPdf;ReceiptRetrieve');
		}
	}

	function pdfOptions()
	{
		popupParameters = "ReceiptHeader_icRecHeader=<%=s_ic_rec_header%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";

		if(frm.print_option[0].checked)
		{
			frm.emailTo.value = 'N';
			popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
			doSubmitToLookup('', 'PrintRecPdf', 'width=775px', 'height=850px');
		}
		else if(frm.print_option[1].checked)
		{
			if(checkemail())
			{
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				doSubmit('receipts/rec_review.jsp', 'EmailRecPdf;ReceiptRetrieve');
			}
		}
	}

	function printLabels()
	{
		popupParameters = "ReceiptHeader_icRecHeader=<%=s_ic_rec_header%>";
		popupParameters = popupParameters + ";PoHeader_icPoHeader=<%=s_ic_po_header%>";
		popupParameters = popupParameters + ";RequisitionHeader_icReqHeader=<%=s_ic_req_header%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		popupParameters = popupParameters + ";viewNow=Y";
		
		doSubmitToPopup('/system/popupDocAttachment.jsp', 'PrintRecLabelsPdf', 'width=775px', 'height=850px');
	}
	
	function checkemail()
	{
		var str = frm.email.value;
		var emailArray = str.split(";");

		for (x=0; x < emailArray.length; x++)
		{
			if (checkOneEmail(emailArray[x]))
			{
				//return true;
			}
			else
			{
				frm.email.select();
				return false;
			}
		}

		return true;
	}
	function checkOneEmail(email)
	{
		var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i

		if (filter.test(email))
		{
			return true;
		}
		else
		{
			alert("Please input a valid email address!");
			return false;
		}
	}

// end hiding contents -->
</SCRIPT>
