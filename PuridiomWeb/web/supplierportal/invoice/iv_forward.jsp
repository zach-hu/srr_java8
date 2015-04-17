<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
%>

<br>


<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td align="center">
		Invoice&nbsp;<font class="hdr12">#<%=invoiceHeader.getInvoiceNumber()%></font>&nbsp;has been submitted!
	</td>
</tr>
</table>


<br><br>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td>
		<table width="400px" align="center">
		<tr><td colspan="2" align="center">Would you like to create another Invoice?</td></tr>
		<tr>
			<td width="50%" align="center"><a href="javascript: doSubmit('/invoice/iv_create.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_yes.gif" border="0" alt="Yes"></a></td>
			<td width="50%" align="center"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_no.gif" border="0" alt="No"></a></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

// End Hide script -->
</SCRIPT>
