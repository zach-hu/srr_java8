<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.PaymentAccount" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	notEditPaymentInfo = (String)propertiesManager.getProperty("VOUCHER OPTIONS","NOTALLOWPAYMENTINFORMATIONEDIT", "N");
	List paymentAccountList = (List) request.getAttribute("paymentAccountList");
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
	boolean codaEnabled = propertiesManager.getProperty("CODA","Enabled", "N").equalsIgnoreCase("Y");

	String	refresh = (String) request.getAttribute("refreshOpener");
	if (refresh == null)
	{
		refresh = "N";
	}
%>

<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="PaymentAccount_invoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
<tsa:hidden name="PaymentAccount_vendorId" value="<%=invoiceHeader.getVendorId()%>"/>
<tsa:hidden name="PaymentAccount_icPayment" value=""/>
<tsa:hidden name="refreshOpener" value="<%=refresh%>"/>
<tsa:hidden name="formAction" value=""/>


<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign="top" width=150px height=30px>
		<table cellpadding="0" cellspacing="0" border="0" height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12>Payments</div></td></tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
		<table cellpadding="0" cellspacing="0" border="0">
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>
<br>

<table id="scheduleList" border="0" cellspacing="0" cellpadding="0" width="540px" align="center">
<tr>
	<td width="5px">&nbsp;</td>
	<td width="540px" class="browseHdrDk" align="center" valign="top">
		<table border="0" cellspacing="0" cellpadding="2" width="540px">
		<tr>
		    <% if (codaEnabled) { System.out.println("Coda-Enabled=true") ; %>
			<td nowrap height="18px" class="browseHdrDk" width="8%">Type</td>
			<td nowrap height="18px" class="browseHdrDk" width="10%" align="right">Check #</td>
			<td class="browseHdrDk" width="5%">&nbsp;</td>
			<td nowrap height="18px" class="browseHdrDk" width="15%">Check Date</td>
			<td nowrap height="18px" class="browseHdrDk" width="15%">Status</td>
			<% } else { System.out.println("Coda-Enabled=false"); %>
			<td nowrap height="18px" class="browseHdrDk" width="8%">Check #</td>
			<td nowrap height="18px" class="browseHdrDk" width="10%" align="right">Check Amount</td>
			<td class="browseHdrDk" width="5%">&nbsp;</td>
			<td nowrap height="18px" class="browseHdrDk" width="15%">Check Date</td>
			<td nowrap height="18px" class="browseHdrDk" width="15%">Cancel Date</td>
			<% } %>
			<% if(notEditPaymentInfo.equalsIgnoreCase("N")){ %>
				<td nowrap height="18px" class="browseHdrDk" width="5%">Delete</td>
			<% } %>
		</tr>
		</table>
		<div id="browseBorder" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 540px; height: <%=((paymentAccountList.size()) * 18) + 20%>px; align:center; overflow-y:visible; overflow-x:auto;">
		<div id="noRecords" style="visibility: hidden; display: none;">
		<table border="0" cellspacing="0" cellpadding="2" width="525px" class="browseRow">
		<tr><td><b>There is currently no Payment Information for this Invoice.</b></td></tr>
		</table>
		</div>
		<table id=browseRows border="0" cellspacing="0" cellpadding=1 width="525px" class="browseRow">

<%
		if (! codaEnabled) {
			for (int i = 0; i < paymentAccountList.size(); i++) {
					PaymentAccount paymentAccount = (PaymentAccount) paymentAccountList.get(i);
%>
			<tr>
				<td height="18px" class="browseRow" width="8%"><a href="javascript: updatePayment('<%=paymentAccount.getIcPayment()%>'); void(0);" title="Click here to View/Modify Payment Details."><%=paymentAccount.getCheckNumber()%></a></td>
				<td height="18px" class="browseRow" width="10%" align="right"><%=HiltonUtility.getFormattedCurrency(paymentAccount.getCheckAmount(), invoiceHeader.getCurrencyCode(), oid)%></td>
				<td class="browseRow" width="5%">&nbsp;</td>
				<td height="18px" class="browseRow" width="15%"><%=HiltonUtility.getFormattedDate(paymentAccount.getCheckDate(), oid, userDateFormat)%></td>
				<td height="18px" class="browseRow" width="15%"><%=HiltonUtility.getFormattedDate(paymentAccount.getCancelDate(), oid, userDateFormat)%></td>
				<% if(notEditPaymentInfo.equalsIgnoreCase("N") && (!oid.equalsIgnoreCase("msg07p"))){ %>
					<td align="center" valign="top" width="5%"><a href="javascript: deletePayment('<%=paymentAccount.getIcPayment()%>'); void(0);"><img src="<%=contextPath%>/images/delete.gif" border="0" alt="Delete"></td>
				<% } %>
			</tr>
<%			}
		} else {
			for (int i = 0; i < paymentAccountList.size(); i++) {
				String data[] = (String[]) paymentAccountList.get(i) ;
				%>
			<tr>
				<td height="18px" class="browseRow" width="8%"><%=data[4]%></td>
				<td height="18px" class="browseRow" width="10%" align="right"><%=data[5]%></td>
				<td class="browseRow" width="5%">&nbsp;</td>
				<td height="18px" class="browseRow" width="15%"><%=data[6]%></td>
				<td height="18px" class="browseRow" width="15%"><%=data[3]%></td>
			</tr>
<%			}  %>
<%		} %>
		</table>
		</div>
		</div>
	</td>
	<td width="5px">&nbsp;</td>
</tr>
</table>

<table width=100% border="0">
<tr>
	<%if(! codaEnabled && notEditPaymentInfo.equalsIgnoreCase("N")){ %>
	<td align="center"><br><a href="javascript: void(0); addPayment();"><img class=button src="<%=contextPath%>/images/button_add.gif" border="0" alt="Add"></a></td>
	<% } %>
	<td align="center"><br><a href="javascript: void(0); closeMe();"><img class=button src="<%=contextPath%>/images/button_close.gif" border="0" alt="Close"></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var browser = browserCheck();
	var totalRows = <%=paymentAccountList.size()%>;

	if (totalRows <= 0)
	{
		document.getElementById("noRecords").style.visibility = "visible";
		document.getElementById("noRecords").style.display = "block";
		document.getElementById("browseBorder").style.height = "25px";
	}

	function addPayment()
	{
		//setHiddenFields("");
		frm.formAction.value = "CREATE";
		doSubmit('/invoice/add_payment.jsp', 'InvoiceHeaderRetrieveById');
	}

	function updatePayment(icPayment)
	{
		//setHiddenFields("");
		frm.formAction.value = "UPDATE";
		frm.PaymentAccount_icPayment.value = icPayment;
		doSubmit('/invoice/add_payment.jsp', 'InvoiceHeaderRetrieveById;PaymentAccountRetrieveById');
	}

	function deletePayment(icPayment)
	{
		if (verifyAction("Delete this payment?"))
		{
			frm.PaymentAccount_icPayment.value = icPayment;
			doSubmit('/invoice/payments.jsp', 'PaymentAccountDeleteById;PaymentAccountRetrieveByInvoice');
		}
		else
		{
			return false;
		}
	}

	function closeMe()
	{
		if (frm.refreshOpener.value == "Y")
		{
			//set cursor to hourglass while the system is processing
			window.parent.document.body.style.cursor = "wait";
			window.parent.doSubmit('/invoice/iv_review.jsp', 'InvoiceRetrieve');
		}
		window.top.hidePopWin();
	}


// End Hide script -->
</SCRIPT>


