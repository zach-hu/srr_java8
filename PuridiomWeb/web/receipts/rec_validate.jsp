<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsagate.foundation.rule.ValidationRule" %>
<%@ page import="com.tsagate.foundation.rule.ValidationRules" %>
<%@ page import="java.math.BigDecimal" %>
<%
	ValidationRules rules = (ValidationRules) request.getAttribute("rules");
	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("header");
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	String receiptAction = (String) request.getAttribute("receiptAction");
	String receiptMethod = (String) request.getAttribute("receiptMethod");
	BigDecimal	bd_zero = new BigDecimal(0);

	String	receiptNumber = receiptHeader.getReceiptNumber();
	if (HiltonUtility.isEmpty(receiptNumber)) {
		receiptNumber = "N/A";
	}
	if (HiltonUtility.isEmpty(receiptAction)) {
		receiptAction = "VALIDATE";
	}
	if (poHeader==null) {
		poHeader = new PoHeader();
	}
	if (requisitionHeader==null) {
		requisitionHeader = new RequisitionHeader();
	}
%>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dynamicTables.js"></SCRIPT>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=receiptHeader.getIcRecHeader()%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=receiptHeader.getReceiptNumber()%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=receiptHeader.getReceiptStatus()%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=receiptHeader.getFiscalYear()%>"/>
<tsa:hidden name="ReceiptHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
<tsa:hidden name="ReceiptHeader_icReqHeader" value="<%=receiptHeader.getIcReqHeader()%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="<%=receiptHeader.getReceiptType()%>"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="receiptAction" value="<%=receiptAction%>"/>
<tsa:hidden name="formType" value="REC"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
  <td valign=top width=135px height=30px>
    <table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
    <tr>
      <td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
    </tr>
    <tr>
      <td nowrap class=hdr12 valign=middle>
        <div style="margin-left:10px; margin-right:10px" class=hdr12>Receipt Validation Results</div>
      </td>
    </tr>
    </table>
  </td>
  <td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
  <td valign=bottom align=right height=30px>
    <table border=0 cellspacing=0 cellpadding=1 width=100%>
<%	if (poHeader.getIcPoHeader().compareTo(bd_zero) > 0)
	{ %>
	<tr>
		<td nowrap align=right><b>Order #:</b></td>
		<td width=100px><%=poHeader.getPoNumber()%></td>
<%		if (poHeader.getReleaseNumber().compareTo(bd_zero) > 0)
		{	%>
		<td nowrap align=right><b>Release #:</b></td>
		<td width=100px><%=poHeader.getReleaseNumber()%></td>
<%		}
		if (poHeader.getRevisionNumber().compareTo(bd_zero) > 0)
		{%>
		<td nowrap align=right><b>Revision #:</b></td>
		<td width=100px><%=poHeader.getRevisionNumber()%></td>
<%		} %>
	</tr>
<%	} %>
<%	if (requisitionHeader.getIcReqHeader().compareTo(bd_zero) > 0)
	{ %>
	<tr>
		<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitionNumber", "Requisition #")%>:</b></td>
		<td width=100px><%=headerEncoder.encodeForHTML(requisitionHeader.getRequisitionNumber())%></td>
	</tr>
<%	} %>
    <tr>
      <td align=right><b>Receipt #:</b></td>
      <td width=100px><%=receiptNumber%></td>
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
	<td align=center><a href="javascript: window.top.hidePopWin(); void(0);"><img class="button" src="<%=contextPath%>/images/button_close.gif" border=0 alt="Close"></a></td>
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
		<div id="pxbutton"><a href="javascript: thisClosePopup(); void(0);">Close</a></div>
<%	}%>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	window.resizeTo('680', '500');

	self.focus();

	window.open('','_self','');

<%	if (rules.getResult() == 1 && receiptAction.equalsIgnoreCase("FORWARD")) { %>
	displayArea('novalidationrules');
	receiptForward();
<%	} else {%>
	displayArea('validationrules');
<%	}%>

	function checkHiddenMenu() {
		hideArea("navTable");
		displayArea("menubarSpacer");
	}

	function receiptForward() {
		if (frm.receiptMethod.value == "ReceiveByOrder" || frm.receiptMethod.value == "ReceiveByTransfer") {
			setTimeout("opener.doSubmit('receipts/rec_confirmation.jsp', 'ReceiptGetFormNumber;ReceiptUpdate');", 3000);
		} else if (frm.receiptMethod.value == "Return") {
			setTimeout("opener.doSubmit('receipts/rec_confirmation.jsp', 'ReceiptUpdate');", 3000);
		} else if (frm.receiptMethod.value == "ReceiveFromNothing") {
			frm.receiptMethod.value = "ReceiveFromNothing";
			opener.frm.receiptMethod.value = "ReceiveFromNothing";
			setTimeout("opener.doSubmit('receipts/rec_confirmation.jsp', 'ReceiptCreateFromNothing');", 3000);
		} else {
			setTimeout("opener.doSubmit('receipts/rec_forward.jsp', 'ReceiptCreateForward');", 3000);
		}
		setTimeout("window.close();", 3100);
	}

	function thisUnLoadPopup() {
		window.parent.displayArea('forward_link');
	}
	function thisClosePopup() {
		setTimeout("window.close();", 1000);
	}

// end hiding contents -->
</SCRIPT>