<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	String s_dollar_decimals = PropertiesManager.getInstance(oid).getProperty("MISC", "DollarDecimals", "2");
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
	String invoiceNumber = (String) request.getAttribute("PaymentAccount_invoiceNumber");
	String vendorId = (String) request.getAttribute("PaymentAccount_vendorId");

	boolean newPaymentAccount = false;
	PaymentAccount paymentAccount = (PaymentAccount) request.getAttribute("paymentAccount");
	if (paymentAccount == null)
	{
		paymentAccount = new PaymentAccount();
		paymentAccount.setDateEntered(d_today);
		paymentAccount.setOwner(uid);
		newPaymentAccount = true;
	}

	String	refresh = (String) request.getAttribute("refreshOpener");
	if (refresh == null)
	{
		refresh = "N";
	}
%>

<tsa:hidden name="PaymentAccount_icPayment" value="<%=paymentAccount.getIcPayment()%>"/>
<tsa:hidden name="PaymentAccount_dateEntered" value="<%=HiltonUtility.getFormattedDate(paymentAccount.getDateEntered(), oid, userDateFormat)%>"/>
<tsa:hidden name="PaymentAccount_owner" value="<%=paymentAccount.getOwner()%>"/>
<tsa:hidden name="PaymentAccount_invoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
<tsa:hidden name="PaymentAccount_vendorId" value="<%=invoiceHeader.getVendorId()%>"/>
<tsa:hidden name="PaymentAccount_icPoHeader" value="<%=invoiceHeader.getIcPoHeader()%>"/>
<tsa:hidden name="PaymentAccount_poNumber" value="<%=invoiceHeader.getPoNumber()%>"/>
<tsa:hidden name="PaymentAccount_releaseNumber" value="<%=invoiceHeader.getPoRelease()%>"/>
<tsa:hidden name="PaymentAccount_invoiceDate" value="<%=invoiceHeader.getInvoiceDate()%>"/>
<tsa:hidden name="PaymentAccount_invoiceAmount" value="<%=invoiceHeader.getInvoiceTotal()%>"/>
<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="refreshOpener" value="<%=refresh%>"/>

<table>
<tr>
	<td width=680px>
		<table cellpadding=0 cellspacing=0 border=0 width=100%>
		<tr>
			<td valign=top width=50px height=30px>
				<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
				<tr>
					<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
				</tr>
				<tr>
					<td nowrap class=hdr12 valign=middle>
						<div style="margin-left:10px; margin-right:10px" class=hdr12>Payment Info</div>
					</td>
				</tr>
				</table>
			</td>
			<td valign=bottom align=left><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
			<td valign=bottom align=right height=30px width=100%>
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
	</td>
</tr>
</TABLE>
<BR>
<table align="center">
	<tr>
		<td align=right>Check #:</td>
		<td>
			<input type="text" name="PaymentAccount_checkNumber" value="<%=paymentAccount.getCheckNumber()%>">
		</td>
	</tr>
	<tr>
		<td align=right>Check Amount:</td>
		<td>
			<input type="text" name="PaymentAccount_checkAmount" value="<%=HiltonUtility.getFormattedDollar(paymentAccount.getCheckAmount(), oid)%>" style="text-align:right" onChange="formatMe(this);">
		</td>
	</tr>
	<tr>
		<td align=right>Check Date:</td>
		<td>
			<input type="text" name="PaymentAccount_checkDate" value="<%=HiltonUtility.getFormattedDate(paymentAccount.getCheckDate(), oid, userDateFormat)%>">
			<a href="javascript: show_calendar('PaymentAccount_checkDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
		</td>
	</tr>
	<tr>
		<td align=right>Cancel Date:</td>
		<td>
			<input type="text" name="PaymentAccount_cancelDate" value="">
			<a href="javascript: show_calendar('PaymentAccount_cancelDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
		</td>
	</tr>
</table>

<BR>

<TABLE ALIGN="CENTER" WIDTH="80%">
	<TR>
		<TD ALIGN="CENTER"><A HREF="javascript: submitThis(); void(0);" BORDER="0"><IMG class=button src="<%=contextPath%>/images/button_save.gif" BORDER="0" ALT="Save"></TD>
		<TD ALIGN="CENTER"><A HREF="javascript: returnAbort(); void(0);"><IMG class=button src="<%=contextPath%>/images/button_cancel.gif" BORDER="0" ALT="Cancel"></A></TD>
	</TR>
</TABLE>

<%@ include file="/system/footer.jsp" %>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	function submitThis()
	{
<%	if (newPaymentAccount)
		{ %>
			frm.refreshOpener.value = "Y";
			doSubmit('/invoice/payments.jsp', 'PaymentAccountAdd;PaymentAccountRetrieveByInvoice');
<%	}
		else
		{ %>
			doSubmit('/invoice/payments.jsp', 'PaymentAccountUpdate;PaymentAccountRetrieveByInvoice');
<%	} %>
	}

	function returnAbort()
	{
		doSubmit('/invoice/payments.jsp', 'PaymentAccountRetrieveByInvoice');
	}

	function formatMe(x)
	{
		var dollar_dec = <%=Integer.valueOf(s_dollar_decimals).intValue()%>;
		x.value = nformat(eval(nfilter(x)),dollar_dec);
	}

// end hiding contents -->
</SCRIPT>