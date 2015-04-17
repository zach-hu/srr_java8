<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
	String	s_ivc_number = invoiceHeader.getInvoiceNumber();
	String	s_ivc_status = invoiceHeader.getStatus();
	String	s_current_process = "ITEMS";
	String	s_current_page = "/invoice/iv_items.jsp";
	String	s_current_method = "InvoiceLineUpdateAll";
	String	s_current_process_method = "";
	String	autoRoutingList = propertiesManager.getProperty("VOUCHER", "AUTOROUTINGLIST", "N");
	String	extendedRouting = propertiesManager.getProperty("VOUCHER", "ExtendedRouting", "N");

	String	invoiceExceptionErrorMsg = (String) request.getAttribute("errorMsg");
%>
<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=invoiceHeader.getStatus()%>"/>
<tsa:hidden name="invoiceaction" value="FORWARD"/>
<tsa:hidden name="fromExceptionsPage" value="YES"/>
<tsa:hidden name="formtype" value="IVC"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Invoice Matching Exception</div>
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
	<td valign=bottom align=middle width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Invoice #:</b></td>
			<td width=125px><%=invoiceHeader.getInvoiceNumber()%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=125px><%=DocumentStatus.toString(invoiceHeader.getStatus(), oid)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
	</table>
	</td>
</tr>
</table>

<br>
<br>

<%	if (!HiltonUtility.isEmpty(invoiceExceptionErrorMsg)) { %>
<table border=0 cellpadding=0 cellspacing=0>
<tr>
	<td width="5px">&nbsp;</td>
	<td valign=top class="error">
		<%=invoiceExceptionErrorMsg%>
	</td>
</tr>
</table>

<br>
<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formWidth%>>
<tr>
	<td align=center valign=top>
		<table cellpadding="0" cellspacing="0" width=<%=formWidth%>>
		<tr>
			<td width="50%" align="center" <%=HtmlWriter.isVisible(oid, "ivc-exceptionForward")%>><div id="pxbutton"><a href="javascript: forwardInvoice(); void(0);">Forward</a></div></td>
			<td width="50%" align="center" <%=HtmlWriter.isVisible(oid, "ivc-exceptionReturn")%>><div id="pxbutton"><a href="javascript: returnToInvoice(); void(0);">Return</a></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>



<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function returnToInvoice()
	{
		if (frm.viewType.value == "CLASSIC")
		{
			doSubmit('/invoice/iv_summary.jsp', 'InvoiceRetrieve')
		}
		else
		{
			doSubmit('/invoice/iv_review.jsp', 'InvoiceRetrieve')
		}
	}

	function forwardInvoice()
	{
<%	if (extendedRouting.equals("Y")) { %>
		doSubmit('/invoice/iv_routing_list.jsp', 'InvoiceGenerateRoutingList');
<%	} else if (autoRoutingList.equals("Y")) { %>
		doSubmit('/invoice/iv_auto_routing_list.jsp', 'InvoiceCheckExceptions');
<%	} else { %>
		doSubmit('/invoice/iv_routinglist.jsp', 'InvoiceCheckExceptions');
<%	} %>
	}

// End Hide script -->
</SCRIPT>