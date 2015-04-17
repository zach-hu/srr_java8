<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String	invalidPoNumberErrorMsg = (String) request.getAttribute("invalidPoNumberErrorMsg");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	awardedPO = propertiesManager.getProperty("VOUCHER", "AWARDEDPO", "N");
%>
<input type=hidden name="invalidPoNumberFailurePage" value="/invoice/invoice_create.jsp">

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Invoice</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
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
			<td width=50% align=center><div id="pxbutton"><a href="javascript: createInvoice(); void(0);">Create</a></div></td>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
		</tr>
		</table>
	</td>
	<td valign=top>
		<table>

	<%	if (!oid.equalsIgnoreCase("akdoc")) { %>
		<tr height="20px" <%=HtmlWriter.isVisible(oid, "NOTORDERNUMBER")%>>
			<td><a href="javascript: doSubmit('/invoice/iv_general_info.jsp', 'InvoiceCreate');"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "NOTORDERNUMBER", "I do not have an order number.", true)%></a></td>
		</tr>
	<%	} 	
		if(awardedPO.equalsIgnoreCase("Y")) {%>
			<tr height="20px">
				<td><a href="javascript: browseFilter('invoicecreate');">I would like to search for an order.</a></td>
			</tr>
	<%	}
		else
		{ %>
			<tr height="20px">
				<td><a href="javascript: browseFilter('invoicecreatewithoutAward');">I would like to search for an order.</a></td>
			</tr>
	<%	}%>
		</table>
	</td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.viewType.value = "WIZARD";

	setNavCookie("/invoice/invoice_create.jsp", "DoNothing", "Create New Invoice", true);

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

// End Hide script -->
</SCRIPT>