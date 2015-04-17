<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%

String ic = (String) request.getAttribute("ic");
String number = (String) request.getAttribute("number");
String type = (String) request.getAttribute("type");
String closeMe = (String) request.getAttribute("closeMe");
String s_po_number = (String) request.getAttribute("PoHeader_poNumber");

if (closeMe == null) {	closeMe = "N";	}
%>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>
<div id="peOptions" style="
border:none;position: absolute;
width: 100%; align:center; valign:center; overflow-y:hidden; overflow-x:hidden; height: 100%">
<table border="0" align="center" id="printOptions" width="100%" height="100%"
style="border-collapse: collapse; padding:none;background-color:#FF0000;">
	<tr class="mnav">
		<td colspan="2">&nbsp;Receipt #&nbsp;<%=number%>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;</td>
		<td nowrap id="emailto">Email To:&nbsp;<input type="text" name="email" value="<%=mid%>" />&nbsp;&nbsp;<a href="javascript: emailPdf();"><img src="<%=contextPath%>/images/mail_reply.gif" border="0" alt="Email" ></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><div id="pxbutton"><a href="javascript:closeiframe()">Close</a></div></td>
	</tr>
</table>
</div>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="viewNow" value="Y" />
<tsa:hidden name="emailTo" value="N" />
<tsa:hidden name="closeMe" value="N" />
<%@ include file="/system/footer_popup.jsp" %>
<SCRIPT value=JavaScript>
<!-- Hide script
	frm = document.purchaseform;
	frm.email.focus();
	<%if(closeMe.equalsIgnoreCase("Y"))
		{%>
			closeMe();
		<%}%>
	function emailPdf()
	{
		frm.organizationId.value = "<%=oid%>";
		frm.userId.value = "${esapi:encodeForJavaScript(userId)}";

		if(checkemail(frm.email))
		{
			var newInputField = "<input type='hidden' name='ReceiptHeader_icRecHeader' value='<%=ic%>'>";
			newInputField = newInputField + "<input type='hidden' name='ReceiptHeader_receiptNumber' value='<%=number%>'>";
			setHiddenFields(newInputField);

			frm.viewNow.value = 'N';
			frm.emailTo.value = 'Y';
			frm.closeMe.value = 'Y';
			hideArea('printOptions');
			doSubmit('orders/print.jsp', 'EmailRecPdf');
		}
	}

	function closeiframe()
	{
		parent.document.getElementById('recframe').style.display="none";
	}
	function thisLoadPopup()
	{
  		addHandle(document.getElementsByTagName('body').item(0), window);
	}

	function closeMe()
	{
		parent.document.getElementById('recframe').style.display="none";
	}
	// End Hide script -->
</SCRIPT>