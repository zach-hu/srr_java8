<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>

<%
	String	s_ic_po_header = (String) request.getAttribute("PoHeader_icPoHeader");
	String	s_ic_po_line = (String) request.getAttribute("PoLine_icPoLine");
	String	s_po_number = (String) request.getAttribute("PoHeader_poNumber");
	String	s_release_number = (String) request.getAttribute("PoHeader_releaseNumber");
	String	s_revision_number = (String) request.getAttribute("PoHeader_revisionNumber");
	String	s_po_status = (String) request.getAttribute("PoHeader_status");
	String	s_po_type = (String) request.getAttribute("PoHeader_poType");
	String	s_fiscal_year = (String) request.getAttribute("PoHeader_fiscalYear");
	String	s_item_location = (String) request.getAttribute("PoHeader_itemLocation");
	String	s_item_number = (String) request.getAttribute("PoLine_itemNumber");
	String	s_description = (String) request.getAttribute("PoLine_description");
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_from_page = HiltonUtility.ckNull((String) request.getAttribute("frompage"));
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	BigDecimal	bd_total_released = (BigDecimal) request.getAttribute("totalReleased");
	BigDecimal	bd_order_qty = (BigDecimal) request.getAttribute("orderQty");
	BigDecimal	bd_balance = (BigDecimal) request.getAttribute("balance");

	List lineDetailList = (List) request.getAttribute("lineDetail");
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoLine_icPoLine" value="<%=s_ic_po_line%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="PoHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Release Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
<%	int i_colspan = 1; %>
		<tr>
			<td nowrap align=right><b>Order #:</b></td>
			<td width=100px><%=s_po_number%></td>
<%	if (bd_release_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b>Release #:</b></td>
			<td width=100px><%=bd_release_number%></td>
<%	}
		if (bd_revision_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b>Revision #:</b></td>
			<td width=100px><%=bd_revision_number%></td>
<%	} %>
		</tr>
		<tr>
			<td colspan=<%=i_colspan%> nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=100px><%=DocumentStatus.toString(s_po_status, oid)%></td>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr <%=HtmlWriter.isVisible(oid, "po-itemNumber")%> height=18px>
	<td width=25px>&nbsp;</td>
	<td width=100px nowrap align=right class=formtype><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-itemNumber", "Item/Part #")%>:&nbsp;</td>
	<td nowrap><b><%=s_item_number%></b></td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "po-description")%>>
	<td width=25px>&nbsp;</td>
	<td width=100px nowrap align=right valign=top class=formtype><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-description", "Description")%>:&nbsp;</td>
	<td><b><%=s_description%></b></td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr <%=HtmlWriter.isVisible(oid, "deliveryOrderQuantity")%>>
	<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deliveryOrderQuantity", "Delivery Order Quantity")%>:&nbsp;</td>
	<td><input type=text value="<%=HiltonUtility.getFormattedQuantity(bd_order_qty, oid)%>" disabled></td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "totalReleasedQuantity")%>>
	<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "totalReleasedQuantity", "Total Released Quantity")%>:&nbsp;</td>
	<td><input type=text value="<%=HiltonUtility.getFormattedQuantity(bd_total_released, oid)%>" disabled></td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "balanceRemaining")%>>
	<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "balanceRemaining", "Balance Remaining")%>:&nbsp;</td>
	<td><input type=text value="<%=HiltonUtility.getFormattedQuantity(bd_balance, oid)%>" disabled></td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=600px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
				<tr>
					<td width=5% class=browseHdr nowrap>&nbsp;</td>
					<td width=15% class=browseHdr nowrap>&nbsp;Delivery Order</td>
					<td width=10% class=browseHdr nowrap align=right>&nbsp;Release</td>
					<td width=10% class=browseHdr nowrap align=right>&nbsp;Revision</td>
					<td <%=HtmlWriter.isVisible(oid, "po-lineNumber")%> width=10% class=browseHdr nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "hdg-lineNumber", "Line #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "po-quantity")%> width=15% class=browseHdr nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "hdg-quantity", "Quantity")%></td>
					<td <%=HtmlWriter.isVisible(oid, "po-unitCost")%> width=15% class=browseHdr nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "hdg-unitCost", "Unit Cost")%></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
<%	if (lineDetailList != null && lineDetailList.size() > 0)
		{
			for (int i = 0; i < lineDetailList.size(); i++)
			{
				Object lineDetail[] = (Object[]) lineDetailList.get(i);
				String s_poNumber = (String) lineDetail[0];
				BigDecimal bd_releaseNumber = (BigDecimal) lineDetail[1];
				BigDecimal bd_revisionNumber = (BigDecimal) lineDetail[2];
				BigDecimal bd_lineNumber = (BigDecimal) lineDetail[3];
				BigDecimal bd_quantity = (BigDecimal) lineDetail[4];
				BigDecimal bd_unitPrice = (BigDecimal) lineDetail[5];
%>
				<tr>
					<td width=5% nowrap align=right><b><%=i + 1%>.&nbsp;&nbsp;</b></td>
					<td width=15% nowrap><%=s_poNumber%></td>
					<td width=10% nowrap align=right><%=bd_releaseNumber%></td>
					<td width=10% nowrap align=right><%=bd_revisionNumber%></td>
					<td <%=HtmlWriter.isVisible(oid, "po-lineNumber")%> width=10% nowrap align=right><%=Utility.getBigDecimalFormatted(bd_lineNumber, 0)%></td>
					<td <%=HtmlWriter.isVisible(oid, "po-quantity")%> width=15% nowrap align=right><%=HiltonUtility.getFormattedQuantity(bd_quantity, oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "po-unitCost")%> width=15% nowrap align=right><%=HiltonUtility.getFormattedDollar(bd_unitPrice, oid)%></td>
				</tr>
<%		}
		}
		else
		{ %>
				<tr>
					<td align=center><b>There is currently no line item delivery detail available.</b></td>
				</tr>
<%	} %>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center id="buttons"><a href="javascript: printMe();"><img class=button src="<%=contextPath%>/images/button_print.gif" border=0 alt="Print"></a></td>
	<td width=50% align=center id="buttons"><a href="javascript: doSubmit('/orders/po_item.jsp', 'PoLineRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var netscape  = "";
	var ver = navigator.appVersion;
	var len = ver.length;

	for(iln = 0; iln < len; iln++)
	{
		if (ver.charAt(iln) == "(") break;
	}
	netscape = (ver.charAt(iln+1).toUpperCase() != "C");

	window.onfocus = displayButtons; // displayButtons on window.onfocus

	if (netscape) window.captureEvents(window.onfocus);

	function printMe()
	{
		hideArea("buttons");
		this.print();
	}

	function displayButtons()
	{
		displayArea("buttons");
	}

// End Hide script -->
</SCRIPT>