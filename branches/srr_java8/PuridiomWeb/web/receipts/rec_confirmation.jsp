<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<%
	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");

	String	receiptMethod = (String) request.getAttribute("receiptMethod");
	List	returnReceiptHeaderList = (List) request.getAttribute("returnReceiptHeaderList");
	List	receiptHeaderList = (List) request.getAttribute("receiptHeaderList");
	String icRecHeader = "";
	String receiptNumber = "";
	String receiptType = "";
	String	poStatus = "";
	String	poNumber = "";
	String	reqStatus = "";
	String	reqNumber = "";
	String  packingSlip = "";

	BigDecimal	releaseNumber = new BigDecimal(0);
	BigDecimal	revisionNumber = new BigDecimal(0);

	if (receiptHeader != null) {
		icRecHeader = receiptHeader.getIcRecHeader().toString();
		receiptNumber = receiptHeader.getReceiptNumber();
		receiptType = receiptHeader.getReceiptType();
	} else {
		icRecHeader = (String) request.getAttribute("ReceiptHeader_icRecHeader");
		receiptNumber = (String) request.getAttribute("ReceiptHeader_receiptNumber");
		receiptType = (String) request.getAttribute("ReceiptHeader_receiptType");
	}
	if (HiltonUtility.isEmpty(receiptMethod)) {
		receiptMethod = "ReceiveByOrder";
	}
	System.out.println(receiptMethod);
	if (poHeader != null) {
		poStatus = poHeader.getStatus();
		poNumber = poHeader.getPoNumber();
		releaseNumber = poHeader.getReleaseNumber();
		revisionNumber = poHeader.getRevisionNumber();
	} else {
		poStatus = (String) request.getAttribute("PoHeader_poStatus");
		poNumber = (String) request.getAttribute("PoHeader_poNumber");
		String tempNo = (String) request.getAttribute("PoHeader_releaseNumber");
		try {
			releaseNumber = new BigDecimal(tempNo);
		} catch (Exception e) {
		}
		tempNo = (String) request.getAttribute("PoHeader_revisionNumber");
		try {
			revisionNumber = new BigDecimal(tempNo);
		} catch (Exception e) {
		}
	}
	if (requisitionHeader != null) {
		reqStatus = requisitionHeader.getStatus();
		reqNumber = requisitionHeader.getRequisitionNumber();
	} else {
		reqStatus = (String) request.getAttribute("RequisitionHeader_Status");
		reqNumber = (String) request.getAttribute("RequisitionHeader_requisitionNumber");
	}
%>
<tsa:hidden name="DocAttachment_icHeader" value="<%=icRecHeader%>"/>
<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=icRecHeader%>"/>
<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=icRecHeader%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="<%=receiptType%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=receiptNumber%>"/>
<tsa:hidden name="PoHeader_poStatus" value="<%=poStatus%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=poNumber%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=releaseNumber%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=revisionNumber%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=reqStatus%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=reqNumber%>"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="allowEdit" value="Y"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="emailTo" value="N"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<%	if (receiptMethod.equalsIgnoreCase("ReceiveByOrder")|| receiptMethod.equalsIgnoreCase("ReceiveFromNothing")|| receiptMethod.equalsIgnoreCase("ReceiveByTransfer")) {%>
<tr><td align=center>Receipt #<font class=hdr12><%=receiptNumber%></font> has been created.</td></tr>
<%	if (receiptHeaderList != null)
	{
		for (int i = 0; i < receiptHeaderList.size(); i++)
		{
			ReceiptHeader oldReceiptHeader = (ReceiptHeader) receiptHeaderList.get(i);
			if (!HiltonUtility.isEmpty(oldReceiptHeader.getPackingSlip())) {
				if (oldReceiptHeader.getReceiptType().equalsIgnoreCase(ReceiptType.RETURN)) { %>
<tr><td align=center>RMA # <font class=hdr12><%=oldReceiptHeader.getPackingSlip()%></font> has been created.</td></tr>	
<%				} 
			}
		}
 	} 
		if (returnReceiptHeaderList != null && returnReceiptHeaderList.size() > 0) {
				for (int ir = 0; ir < returnReceiptHeaderList.size(); ir++) {
					ReceiptHeader returnReceiptHeader = (ReceiptHeader) returnReceiptHeaderList.get(ir);
					if (returnReceiptHeader != null) {
						List returnReceiptLineList = returnReceiptHeader.getReceiptLineList();
						if (returnReceiptLineList != null) {
							for (int irl = 0; irl < returnReceiptLineList.size(); irl++) {
								ReceiptLine returnReceiptLine = (ReceiptLine) returnReceiptLineList.get(irl);
								if (returnReceiptLine != null) {%>
<tr><td align=center>Return Receipt #<font class=hdr12><%=returnReceiptHeader.getReceiptNumber()%></font> has been created.</td></tr>
<%							}
							}
						}
					}
				}
			}%>
<tr><td><br><br></td></tr>
<%	if (receiptMethod.equalsIgnoreCase("ReceiveByTransfer")) {%>
<tr><td align=center>The current status for Requisition #<%=headerEncoder.encodeForHTML(reqNumber)%>
		is <%=DocumentStatus.toString(reqStatus)%>.
	</td>
</tr>
<% } else { %>
<tr><td align=center>The current status for Order #<%=poNumber%>
	<%	if (releaseNumber.compareTo(new BigDecimal(0)) > 0) {%> Release # <%=releaseNumber%><%}%>
		<%	if (revisionNumber.compareTo(new BigDecimal(0)) > 0) {%> Revision # <%=revisionNumber%><%}%>
		is <%=DocumentStatus.toString(poStatus)%>.
	</td>
</tr>
<% } %>

<tr><td><br><br></td></tr>
<%	if (PropertiesManager.getInstance(oid).isModuleActive("DOCUMENTS")) {%>
<tr>
	<td align=center>
		<table border=0 cellpadding=1 cellspacing=0>
		<tr>
			<td><a href="javascript: viewAttachments(); void(0);"><img src="<%=contextPath%>/images/clip.gif" border=0 alt="Attachments"></a></td>
			<td><a href="javascript: viewAttachments(); void(0);">Click here to attach documents to this receipt.</a></td>
		</tr>
		<tr>
		</tr>
		</table>
	</td>
</tr>
<%	}%>
<tr><td><br><br></td></tr>
<%@ include file="/receipts/rec_print_pdf_small.jsp" %>
<tr><td align=center>Would you like to receive more items?</td></tr>
<tr><td><br><br></td></tr>
<%	} else if (receiptMethod.equalsIgnoreCase("FinalizeReceipt")) {%>
<tr><td align=center>Receipt #<font class=hdr12><%=receiptNumber%></font> has been finalized.</td></tr>
<%		if (returnReceiptHeaderList != null && returnReceiptHeaderList.size() > 0) {
				for (int ir = 0; ir < returnReceiptHeaderList.size(); ir++) {
					ReceiptHeader returnReceiptHeader = (ReceiptHeader) returnReceiptHeaderList.get(ir);
					if (returnReceiptHeader != null) {
						List returnReceiptLineList = returnReceiptHeader.getReceiptLineList();
						if (returnReceiptLineList != null) {
							for (int irl = 0; irl < returnReceiptLineList.size(); irl++) {
								ReceiptLine returnReceiptLine = (ReceiptLine) returnReceiptLineList.get(irl);
								if (returnReceiptLine != null) {%>
<tr><td align=center>Return Receipt #<font class=hdr12><%=returnReceiptHeader.getReceiptNumber()%></font> has been created.</td></tr>
<%							}
							}
						}
					}
				}
			}%>
<tr><td align=center>The current status for Order #<%=poNumber%>
	<%	if (releaseNumber.compareTo(new BigDecimal(0)) > 0) {%> Release # <%=releaseNumber%><%}%>
		<%	if (revisionNumber.compareTo(new BigDecimal(0)) > 0) {%> Revision # <%=revisionNumber%><%}%>
		is <%=DocumentStatus.toString(poStatus)%>.
	</td>
</tr>
<tr><td><br><br></td></tr>
<%@ include file="/receipts/rec_print_pdf_small.jsp" %>
<tr><td align=center>Would you like to finalize another receipt?</td></tr>
<tr><td><br><br></td></tr>
<%	} else if (receiptMethod.equalsIgnoreCase("Adjustment")) {%>
<tr><td align=center>Receipt #<font class=hdr12><%=receiptNumber%></font> has been adjusted.</td></tr>
<tr><td align=center>The current status for Order #<%=poNumber%>
	<%	if (releaseNumber.compareTo(new BigDecimal(0)) > 0) {%> Release # <%=releaseNumber%><%}%>
		<%	if (revisionNumber.compareTo(new BigDecimal(0)) > 0) {%> Revision # <%=revisionNumber%><%}%>
		is <%=DocumentStatus.toString(poStatus)%>.
	</td>
</tr>
<tr><td><br><br></td></tr>
<%@ include file="/receipts/rec_print_pdf_small.jsp" %>
<tr><td align=center>Would you like to adjust another receipt?</td></tr>
<tr><td><br><br></td></tr>
<%	} else if (receiptMethod.equalsIgnoreCase("Return")) {%>
<tr><td align=center>Receipt #<font class=hdr12><%=receiptNumber%></font> has been created for a return.</td></tr>
<tr><td align=center>The current status for Order #<%=poNumber%>
	<%	if (releaseNumber.compareTo(new BigDecimal(0)) > 0) {%> Release # <%=releaseNumber%><%}%>
		<%	if (revisionNumber.compareTo(new BigDecimal(0)) > 0) {%> Revision # <%=revisionNumber%><%}%>
		is <%=DocumentStatus.toString(poStatus)%>.
	</td>
</tr>
<tr><td><br><br></td></tr>
<%@ include file="/receipts/rec_print_pdf_small.jsp" %>
<tr><td align=center>Would you like to create another return receipt?</td></tr>
<tr><td><br><br></td></tr>
<%	}%>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: continueReceiving(); void(0);">Yes</a></div></td>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);">No</a></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function continueReceiving() {
		var sendEmail = true;
		if (!isEmpty(frm.email.value))
		{
			sendEmail = false;
			popupParameters = "ReceiptHeader_icRecHeader=<%=icRecHeader%>";
			popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
			popupParameters = popupParameters + ";oid=<%=oid%>";
			if(checkemail())
			{
				sendEmail = true;
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				doSubmit('receipts/rec_confirmation_print.jsp', 'EmailRecPdf;DoNothing');
			}
		}
		if (sendEmail)
		{
<%	if (receiptMethod.equalsIgnoreCase("ReceiveByOrder")) {%>
		receiveByItem();
<%	} else if (receiptMethod.equalsIgnoreCase("ReceiveFromNothing")) {%>
		receiveFromNothing() ;
<%	} else if (receiptMethod.equalsIgnoreCase("FinalizeReceipt")) {
		if (oid.equalsIgnoreCase("vse06p")) { %>
		browse('receipt-finalize-order');
<%		} else { %>
		finalizeReceipts();
<%		} %>
<%	} else if (receiptMethod.equalsIgnoreCase("Adjustment")) {%>
		adjustReceipts();
<%	} else if (receiptMethod.equalsIgnoreCase("Return")) {%>
		returnAgainstReceipts();
<%	}%>
		}
	}

function resetData() {
		frm.emailTo.value = 'N';
		frm.DocAttachment_icHeader.value = "" ;
		frm.ReceiptHeader_icRecHeader.value = "" ;
		frm.ReceiptHeader_receiptNumber.value = "";
		frm.PoHeader_poStatus.value = "" ;
		frm.PoHeader_poNumber.value = "";
		frm.PoHeader_releaseNumber.value = "0" ;
		frm.PoHeader_revisionNumber.value = "0" ;
	}

function receiveFromNothing() {
	resetData() ;
	doSubmit('receipts/rec_entry.jsp', 'ReceiptCreateFromNothingSetup');
}

	function viewNow()
	{
		popupParameters = "ReceiptHeader_icRecHeader=<%=icRecHeader%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		frm.emailTo.value = 'N';
		popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
		doSubmitToPopup('', 'PrintRecPdf', 'width=775px', 'height=850px');
	}

	function emailPdf()
	{
		popupParameters = "ReceiptHeader_icRecHeader=<%=icRecHeader%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		if(checkemail())
		{
			frm.viewNow.value = 'N';
			frm.emailTo.value = 'Y';
			doSubmit('menu/main_menu.jsp', 'EmailRecPdf;DoNothing');
		}
	}

	function pdfOptions()
	{
		popupParameters = "ReceiptHeader_icRecHeader=<%=icRecHeader%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";

		if(frm.print_option[0].checked)
		{
			frm.emailTo.value = 'N';
			popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
			doSubmitToPopup('', 'PrintRecPdf', 'width=775px', 'height=850px');
		}
		else if(frm.print_option[1].checked)
		{
			if(checkemail())
			{
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				doSubmit('receipts/rec_print_pdf.jsp', 'EmailRecPdf;ReceiptRetrieve');
			}
		}
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

	function viewAttachments() {
		doSubmit('/receipts/rec_attachments.jsp', 'DocAttachmentRetrieveByHeader');
	}

	function printLabels() {
		doSubmit('/receipts/rec_labels.jsp', 'ReceiptHeaderLabelRetrieve');
	}

// End Hide script -->
</SCRIPT>