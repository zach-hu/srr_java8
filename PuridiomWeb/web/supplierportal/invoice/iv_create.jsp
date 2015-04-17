<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%
	String	invalidPoNumberErrorMsg = (String) request.getAttribute("invalidPoNumberErrorMsg");
%>
<tsa:hidden name="invalidPoNumberFailurePage" value="/invoice/iv_create.jsp"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Invoice</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr>
			<td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<%	if (!HiltonUtility.isEmpty(invalidPoNumberErrorMsg)) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center class="error">
		<%=invalidPoNumberErrorMsg%>
	</td>
</tr>
</table>

<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center>
		<br>
		<table align="center">
		<tr height="20px">
			<td>Order Number:</td>
			<td><input type="text" name="PoHeader_poNumber" value="" size="15" onchange="upperCase(this);"></td>
			<td width="20px">&nbsp;</td>
			<td align="right">Release #:</td>
			<td><input type="text" name="PoHeader_releaseNumber" value="" size="7" onchange="upperCase(this);"></td>
		</tr>
		</table>
		<br>
		<br>
		<table align="center" width=75%>
		<tr>
			<td width=50% align=right><a href="javascript: createInvoice(); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_create.gif" border=0 alt="Create"></a></td>
			<td width=50% align=right><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_cancel.gif" border=0 alt="Cancel"></a></td>
		</tr>
		</table>
	</td>
	<td valign=top>
		<table>
		<tr height="20px">
			<td><a href="javascript: doSubmit('/invoice/iv_general_info.jsp', 'InvoiceCreate');">I do not have an order number.</a></td>
		</tr>
		<tr height="20px">
			<td><a href="javascript: searchOrders(); void(0);">I would like to search for an order.</a></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function createInvoice()
	{
		if (frm.PoHeader_poNumber.value.length > 0)
		{
			trim(frm.PoHeader_poNumber);
			doSubmit('/invoice/iv_general_info.jsp', 'InvoiceLookupPoNumber');
		}
		else
		{
			doSubmit('/invoice/iv_general_info.jsp', 'InvoiceCreate');
		}
	}

	function searchOrders()
	{
		setOriginalFilter("PoHeader_vendorId", "=", "<%=user.getVendorId()%>");
		browse('invoicecreate');
		//browseFilter('invoicecreate');
	}

// End Hide script -->
</SCRIPT>