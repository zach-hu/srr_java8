<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%
	String poNumber = (String)request.getAttribute("errorMessagePoNumber");
	String poStatus = (String)request.getAttribute("errorMessagePoStatus");
	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	if (receiptHeader == null) {
		receiptHeader = new ReceiptHeader();
	}
	if (poHeader == null) {
		poHeader = new PoHeader();
	}
	String	receiptNumber = receiptHeader.getReceiptNumber();
	if (HiltonUtility.isEmpty(receiptNumber)) {
		receiptNumber = "N/A";
	}
%>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Receipt Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td nowrap align=right><b>Order #:</b></td>
			<td width=100px><%=poHeader.getPoNumber()%></td>
<%	if (poHeader.getReleaseNumber().compareTo(new BigDecimal(0)) > 0)
		{%>
			<td nowrap align=right><b>Release #:</b></td>
			<td width=100px><%=poHeader.getReleaseNumber()%></td>
<%	}
		if (poHeader.getRevisionNumber().compareTo(new BigDecimal(0)) > 0)
		{%>
			<td nowrap align=right><b>Revision #:</b></td>
			<td width=100px><%=poHeader.getRevisionNumber()%></td>
<%	} %>
		</tr>
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
<tr><td><br></td></tr>
<tr>
	<td align="center" colspan=3>
		<font class="formType">Order </font><font class="hdr12">#<%=poNumber%></font>
		<font class="formType">has been already changed to status </font><font class="hdr12"><%=DocumentStatus.toString(poStatus, oid)%></font>
	</td>
</tr>
<tr><td><br></td></tr>
</table>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
	<tr>
		<td align="center">
			<a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);">
				<img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Order">
			</a>
		</td>
	</tr>
</table>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers
	frm = document.purchaseform;

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/footer.jsp" %>