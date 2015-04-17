<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.InvoiceType" %>

<%
    PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
	String invoiceAction = HiltonUtility.ckNull((String) request.getAttribute("invoiceaction"));
	String display_other_inv = propertiesManager.getProperty("INVOICE OPTIONS","OTHERINVTOAPPROVE","N");
	String s_status = invoiceHeader.getStatus();

	boolean forwardOptionAvailable = true;

	String classType = "summary";

	String fromEmail = (String) request.getAttribute("fromEmail");
	if (fromEmail == null)
	{
		 fromEmail = "N";
	}

	String alreadyApprovedMsg = HiltonUtility.ckNull((String)request.getAttribute("alreadyApprovedErrorMsg"));
%>

<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>

<%	if (!HiltonUtility.isEmpty(alreadyApprovedMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
  <td width=100% align=center class=error><%=alreadyApprovedMsg%></td>
</tr>
</table>
<br>
<%	}  else { %>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td align=center>
		<br><br>
		<font class="formType">Invoice #</font><font class=hdr12><%=invoiceHeader.getInvoiceNumber().toString()%></font>&nbsp;
<%	//if (s_status.equals(DocumentStatus.IVC_APPROVING))
		if (s_status.compareTo(DocumentStatus.IVC_APPROVING) >= 0 && s_status.compareTo(DocumentStatus.IVC_APPROVED) < 0)
		{
			String s_forwardedto = HiltonUtility.ckNull ((String) request.getAttribute("forwardedTo"));%>
			has been forwarded to <%=UserManager.getInstance().getUser(oid, s_forwardedto).getDisplayName()%>!
<%	}
		else if (invoiceAction.equalsIgnoreCase("REJECT"))
		{ %>
			has been Rejected!
<%	}
		else
		{ %>
			has been Approved!
<%	} %>
	</td>
</tr>
</table>

<br>
<br>
<%	if (display_other_inv.equalsIgnoreCase("Y"))
	{ %>
		<table width=680px cellpadding=0 cellspacing=0 border=0>
		<tr><td align=center><%@ include file="/invoice/iv_waiting_approval_others.jsp" %></td></tr>
		</table>
<% } %>

<br>

<!--
<%	if (fromEmail.equals("N")) { %>
<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td width="100px">&nbsp;</td>
	<td>
<%		if ( !invoiceAction.equals("FORWARD"))
			{	%>
		<table width="400px" align="center">
		<tr><td colspan="2" align="center">Would you like to approve another Invoice?</td></tr>
		<tr>
			<td width="50%" align="center">
				<a href="javascript: approveAnother(); void(0);"><img class=button src="<%=contextPath%>/images/button_yes.gif" border=0 alt="Yes"></a>
			</td>
			<td width="50%" align="center">
				<a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_no.gif" border=0 alt="No"></a>
			</td>
		</tr>
		</table>
<%		}	%>
	</td>
</tr>
</table>
-->
<br>
<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td align=center>
		<table border=0 cellpadding=2 cellspacing=4>
		<tr>
<%		if (invoiceAction.equals("FORWARD")) { %>
			<td align=center valign=top>
				<table border=0 cellpadding=2 cellspacing=0>
				<tr><td align=right width=50px><img src="<%=contextPath%>/images/add.gif"></td><td nowrap><a href="javascript: createAnotherInvoice(); void(0);">Create another Invoice</a></td>
				<tr><td colspan=2 align=center><br></td></tr>
				<tr><td align=right width=50px><img src="<%=contextPath%>/images/print.gif"></td><td nowrap><a href="javascript: printPdf(); void(0);">Print Invoice #<%=invoiceHeader.getInvoiceNumber().toString()%></a></td></tr>
				</table>
			</td>
			<td align=center valign=top>
				<table border=0 cellpadding=2 cellspacing=0>
				<tr><td align=right width=50px><img src="<%=contextPath%>/images/returnto.gif"></td><td nowrap><a href="javascript: openInvoice(); void(0);">Return to Invoice #<%=invoiceHeader.getInvoiceNumber().toString()%></a></td></tr>
				<tr><td colspan=2 align=center><br></td></tr>
				<tr><td align=right width=50px><img src="<%=contextPath%>/images/pwac_sm.gif"></td><td nowrap><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return to the Procurement Workload Activity Center</a></td></tr>
				</table>
			</td>
<%		}	%>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	}
}%>


<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menubarSpacer");
	}

	function approveAnother()
	{
		var userId = "${esapi:encodeForJavaScript(userId)}";
		setOriginalFilter("ApprovalLog_callForward", "=", userId);
		browse("invoice-approval-list");
	}

	function createAnotherInvoice()
	{
		doSubmit('/invoice/invoice_create.jsp', 'DoNothing');
	}

	function retrieveIvcApproval(ic)
	{
		var newInputField = "<input type='hidden' name='ApprovalLog_icHeader' value='" + ic + "'>";
		frm.InvoiceHeader_icIvcHeader.value = ic;
	    setHiddenFields(newInputField);
	    doSubmit('/invoice/iv_approval.jsp', 'InvoiceApprovalRetrieve');
	}

	function openInvoice() {
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
		doSubmit('/invoice/iv_summary.jsp', 'InvoiceRetrieve');
<%	} else {%>
		doSubmit('/invoice/iv_review.jsp', 'InvoiceRetrieve');
<%	}%>
	}

	function printPdf()
	{
		doSubmit('/invoice/iv_print_pdf.jsp', 'DoNothing');
	}

// End Hide script -->
</SCRIPT>