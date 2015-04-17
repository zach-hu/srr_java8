<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.tsa.puridiom.history.*" %>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");

	List	reqLineList = (List) request.getAttribute("requisitionLineList");
	String	s_req_number = (String) request.getAttribute("requisitionNumber");
	int	i_line_count = 0;

	if (reqLineList != null && reqLineList.size() > 0)
	{
		RequisitionLine reqLine = (RequisitionLine) reqLineList.get(0);
		s_req_number = reqLine.getRequisitionNumber();
		i_line_count = reqLineList.size();
	}
%>

<table border=0 cellpadding=0 cellspacing=0 width=655px>
<tr><td><br></td></tr>
<tr>
	<td width=100%>
		<table cellpadding=0 cellspacing=0 border=0 width=100%>
		<tr>
			<td valign=top width=50px height=30px>
				<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
				<tr>
					<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
				</tr>
				<tr>
					<td nowrap class=hdr12 valign=middle>
						<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="requisition-history" defaultString="Requisition History" /></div>
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
<tr><td><br></td></tr>
<tr>
	<td align=center>
		<font class=formType><tsa:label labelName="requisition" defaultString="Requisition" /> </font><font class=hdr12>#<%=headerEncoder.encodeForHTML(s_req_number)%></font>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td align=center>
		<table border=0 cellpadding=0 cellspacing=0>
			<tr>
				<td width=100% align=center valign=top>
					<table border=0 cellspacing=0 cellpadding=2 width=500px>
						<tr class=mnav>
							<td <%=HtmlWriter.isVisible(oid, "req-lineNumber")%> nowrap width=35px class=mnav align=right><tsa:label labelName="req-hdg-lineNumber" defaultString="Line #" />&nbsp;&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-itemNumber")%> nowrap width=175px class=mnav><tsa:label labelName="req-hdg-itemNumber" defaultString="Item/Part #" /></td>
							<td <%=HtmlWriter.isVisible(oid, "req-quantity")%> nowrap width=70px class=mnav align=right><tsa:label labelName="req-hdg-quantity" defaultString="Quantity" /></td>
							<td width=15px class=mnav>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-uom")%> nowrap width=70px class=mnav><tsa:label labelName="req-hdg-uom" defaultString="UOM" /></td>
							<td <%=HtmlWriter.isVisible(oid, "req-unitCost")%> nowrap width=70px class=mnav align=right><tsa:label labelName="req-hdg-unitCost" defaultString="Unit Cost" /></td>
							<td <%=HtmlWriter.isVisible(oid, "req-extendedCost")%> nowrap width=70px class=mnav align=right><tsa:label labelName="req-hdg-extendedCost" defaultString="Ext Cost" />&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr>
	<td align=center>
		<table border=0 cellpadding=0 cellspacing=0>
			<tr>
				<td width=100% align=center valign=top>
					<table border=0 cellspacing=0 cellpadding=2 width=500px>
<%	if (reqLineList == null)
		{
			/**	LINE ITEM HISTORY	**/
			RequisitionLine reqLine = (RequisitionLine) request.getAttribute("requisitionLine");
			BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid);
			BigDecimal bd_unit_price = HiltonUtility.getFormattedDollar(reqLine.getUnitPrice(), oid);
			BigDecimal bd_total = HiltonUtility.getFormattedDollar(bd_quantity.multiply(bd_unit_price), oid);
%>
					<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-lineNumber")%> width=35px align=right valign=top><%=HiltonUtility.getBigDecimalFormatted(reqLine.getLineNumber(), 0)%>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-itemNumber")%> nowrap width=175px valign=top><b><%=reqLine.getItemNumber()%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "req-quantity")%> width=70px valign=top align=right><%=bd_quantity%></td>
							<td width=15px >&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-uom")%> width=70px valign=top><%=reqLine.getUmCode()%></td>
							<td <%=HtmlWriter.isVisible(oid, "req-unitCost")%> width=70px valign=top align=right><%=bd_unit_price%></td>
							<td <%=HtmlWriter.isVisible(oid, "req-extendedCost")%> width=70px valign=top align=right><%=bd_total%></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td colspan=6><%=reqLine.getDescription()%></td>
						</tr>
<%
		}
		else
		{
			/**	ALL REQUISITION HISTORY		**/
			for (int i = 0; i < i_line_count; i++)
			{
				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);
				BigDecimal bd_line_number = reqLine.getLineNumber();
				BigDecimal bd_quantity = reqLine.getQuantity().setScale(Integer.valueOf(s_quantity_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
				BigDecimal bd_unit_price = reqLine.getUnitPrice().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
				BigDecimal bd_total = (bd_quantity.multiply(bd_unit_price)).setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
%>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-lineNumber")%> width=35px align=right valign=top><%=HiltonUtility.getBigDecimalFormatted(reqLine.getLineNumber(), 0)%>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-itemNumber")%> nowrap width=175px valign=top><b><%=reqLine.getItemNumber()%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "req-quantity")%> width=70px valign=top align=right><%=bd_quantity%></td>
							<td width=15px >&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-uom")%> width=70px valign=top><%=reqLine.getUmCode()%></td>
							<td <%=HtmlWriter.isVisible(oid, "req-unitCost")%> width=70px valign=top align=right><%=bd_unit_price%></td>
							<td <%=HtmlWriter.isVisible(oid, "req-extendedCost")%> width=70px valign=top align=right><%=bd_total%></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td colspan=6><%=reqLine.getDescription()%></td>
						</tr>
<%
			}
		}
%>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr><td><br><br><br></td></tr>
<tr>
	<td width=100% align=center valign=top>
		<table border=0 cellpadding=0 cellspacing=0 width=645px>
		<tr class=mnav height=18px>
			<td nowrap class=mnav><tsa:label labelName="req-datetime" defaultString="Date/Time" /></td>
			<td width=15px class=mnav>&nbsp;</td>
			<td nowrap class=mnav><tsa:label labelName="req-user" defaultString="User" /></td>
			<td width=15px class=mnav>&nbsp;</td>
			<td nowrap class=mnav><tsa:label labelName="req-description" defaultString="Description" /></td>
		</tr>
<%
	List	historyLogList = (List) request.getAttribute("historyList");
	if (historyLogList != null)
	{
		for (int i = 0; i < historyLogList.size(); i++)
		{
			HistoryText history = (HistoryText) historyLogList.get(i);

%>
			<tr height=18px>
				<td nowrap valign=top colspan="5"><%=history.getText()%>&nbsp;</td>
			</tr>
<%
		}
	}
	if (historyLogList == null || historyLogList.size() <= 0)
	{
%>
		<tr height=18px>
			<td colspan=5 align=center><b>There is no history available at this time!</b></td>
		</tr>
<%
		}
%>
		</table>
	</td>
</tr>
<tr><td><br><br></td></tr>
<tr>
	<td align=center><a href="javascript: window.top.hidePopWin(); void(0);"><img class=button src="<%=contextPath%>/images/button_close.gif" border=0></a></td>
</tr>

</table>
