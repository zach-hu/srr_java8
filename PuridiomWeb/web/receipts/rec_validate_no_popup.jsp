<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<%@ page import="com.tsagate.foundation.rule.ValidationRule" %>
<%@ page import="com.tsagate.foundation.rule.ValidationRules" %>
<%@ page import="java.math.BigDecimal" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	BigDecimal bd_ic_rec_header = receiptHeader.getIcRecHeader();
	String	s_rec_number = receiptHeader.getReceiptNumber();
	String	s_rec_type = receiptHeader.getReceiptType();
	String	s_rec_status = receiptHeader.getReceiptStatus();
	String	s_rec_fiscal_year = receiptHeader.getFiscalYear();
	if (HiltonUtility.isEmpty(s_rec_status)) {
		s_rec_status = DocumentStatus.RCV_INPROGRESS;
	}

	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	if (poHeader == null) {
		poHeader = new PoHeader();
	}
	String	s_po_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_poNumber"));
	if (HiltonUtility.isEmpty(s_po_number)) {
		s_po_number = poHeader.getPoNumber();
	}
	BigDecimal	bd_revision_number = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(0);
	Object	o_revision_number = (Object)request.getAttribute("PoHeader_revisionNumber");
	Object	o_release_number = (Object)request.getAttribute("PoHeader_releaseNumber");
	if (o_revision_number != null) {
		bd_revision_number = new BigDecimal(o_revision_number.toString());
	}
	if (o_release_number != null) {
		bd_release_number = new BigDecimal(o_release_number.toString());
	}
	if (bd_revision_number.compareTo(new BigDecimal(0)) == 0) {
		bd_revision_number = poHeader.getRevisionNumber();
	}
	if (bd_release_number.compareTo(new BigDecimal(0)) == 0) {
		bd_release_number = poHeader.getReleaseNumber();
	}

	String	receiptMethod = HiltonUtility.ckNull((String)request.getAttribute("receiptMethod"));
	BigDecimal	bd_zero = new BigDecimal(0);

	ValidationRules rules = (ValidationRules) request.getAttribute("rules");
	String receiptAction = (String) request.getAttribute("receiptAction");
	if (HiltonUtility.isEmpty(receiptAction)) {
		receiptAction = "VALIDATE";
	}
%>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dynamicTables.js"></SCRIPT>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=s_rec_number%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="<%=s_rec_type%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=s_rec_status%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=s_rec_fiscal_year%>"/>
<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RCH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="formType" value="REC"/>
<tsa:hidden name="allowBrowse" value="true"/>

<tsa:hidden name="receiptAction" value="<%=receiptAction%>"/>
<tsa:hidden name="forwardedTo" value="<%=receiptHeader.getForwardTo()%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "validationResults", "Validation Results")%></div>
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
		<table cellpadding="0" cellspacing="0" border=0>
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
<table width=680px cellpadding=0 cellspacing=0 border=0>
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

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
  <td width="100%" align="center" valign="top">
    <%@ include file="/base/validation-rules.jsp" %>
  </td>
</tr>
</table>

<br>
<br>

<table width=655px cellpadding=0 cellspacing=0 border=0 valign=bottom>
<tr>
<%	if ( rules.getResult() != 1 ) {
			if (rules.getResult() > -1 && receiptAction.equalsIgnoreCase("FORWARD")) { %>
	<td align=center><a href="javascript: receiptForward(); void(0);"><img class="button" src="<%=contextPath%>/images/button_forward.gif" border=0 alt="Forward"></a></TD>
	<td align=center><a href="javascript: returnMe(); void(0);"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
<%		}
		} %>
</tr>
<tr>
	<td align=center>
<%	if (rules.getResult() == 1 && receiptAction.equalsIgnoreCase("FORWARD")) { %>
		<div id="novalidationrules" style="display:none;">
			<table align=center>
			<tr>
				<td valign=middle><img src="<%=contextPath%>/images/alert.gif" valign=middle border=0></td>
				<td valign=middle class="basic"><b>Please wait while we validate your receipt.</b></td>
			 </tr>
			</table>
		</div>
<%	}
		if (rules.getResult() == -1 || receiptAction.equalsIgnoreCase("VALIDATE")) {%>
		<a href="javascript: returnMe();"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Requisition"></a>
<%	}%>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();

<%	if (rules.getResult() == 1 && (receiptAction.equalsIgnoreCase("FORWARD") || receiptAction.equalsIgnoreCase("FINALIZE"))) { %>
	displayArea('novalidationrules');
	receiptForward();
<%	} else {%>
	displayArea('validationrules');
<%	}%>

	function receiptForward()
	{
		if (frm.receiptMethod.value == "ReceiveByPackage") {
			frm.ReceiptHeader_receiptStatus.value = "<%=DocumentStatus.RCV_PENDINGFINALIZATION%>";
			doSubmit("/receipts/rec_forward.jsp", "ReceiptForward");
		} else if (frm.receiptMethod.value == "FinalizeReceipt" || frm.receiptMethod.value == "ReceiveByOrder") {
			frm.ReceiptHeader_receiptStatus.value = "<%=DocumentStatus.RCV_RECEIVED%>";
			doSubmit('receipts/rec_confirmation.jsp', 'ReceiptFinalize');
		} else if (frm.receiptMethod.value == "Return" || frm.receiptMethod.value == "ReceiveByTransfer") {
			doSubmit("/receipts/rec_confirmation.jsp", "ReceiptUpdate");
		} else if (frm.receiptMethod.value == "ReceiveFromNothing") {
			frm.receiptMethod.value = "ReceiveFromNothing";
			doSubmit("/receipts/rec_confirmation.jsp", "ReceiptCreateFromNothing");
		}
	}

	function returnMe()
	{
		<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
		doSubmit('receipts/rec_summary.jsp', 'ReceiptRetrieve');
		<%	} else { %>
		doSubmit('receipts/rec_review.jsp', 'ReceiptRetrieve');
		<%	} %>
	}

// end hiding contents -->
</SCRIPT>