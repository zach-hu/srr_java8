<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<%@ page import="com.tsa.puridiom.common.documents.InvoiceType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.math.BigDecimal" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String s_uom = "";

	List	invoiceLineList = (List) request.getAttribute("invoiceLineList");
	//List	receiptHeaderList = (List) request.getAttribute("receiptHeaderList");
	//List	receiptLineList = (List) request.getAttribute("receiptLineList");
	String	s_ivc_number = (String) request.getAttribute("invoiceNumber");
	String s_ivc_header = (String) request.getAttribute("InvoiceLine_icIvcHeader");
	int	i_line_count = 0;

	if (invoiceLineList != null && invoiceLineList.size() > 0)
	{
		InvoiceLine invoiceLine = (InvoiceLine) invoiceLineList.get(0);
		s_ivc_number = invoiceLine.getInvoiceNumber();
		s_ivc_header = invoiceLine.getIcIvcHeader().toString();
		i_line_count = invoiceLineList.size();
	}
	String HistoryLog_icHeaderHistory = (String)request.getAttribute("HistoryLog_icHeaderHistory");
%>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
<tr><td><br></td></tr>
<tr>
	<td width="100%">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td valign="top" width="50px" height="30px">
				<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
				<tr>
					<td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
				</tr>
				<tr>
					<td nowrap class="hdr12" valign="middle">
						<div style="margin-left:10px; margin-right:10px" class=hdr12>Invoice History</div>
					</td>
				</tr>
				</table>
			</td>
			<td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
			<td valign="bottom" align="right" height="30px" width="100%">
				<table cellpadding="0" cellspacing="0" border=0 width=100%>
				<tr>
					<td width="100%" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
				</tr>
				<tr>
					<td height="2px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
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
		<span class=formType>Invoice </span><span class="hdr12">#<%=s_ivc_number%></span>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td align="center">
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%" align="center" valign="top">
					<table border="0" cellspacing="0" cellpadding="2" width="521px">
						<tr class="mnav">
							<td <%=HtmlWriter.isVisible(oid, "ivc-lineNumber")%> nowrap width="35px" class="mnav" align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-hdg-lineNumber", "Line #")%>&nbsp;&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-itemNumber")%> nowrap width="175px" class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-hdg-itemNumber", "Item/Part #")%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-quantity")%> nowrap width="70px" class="mnav" align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-hdg-quantity", "Quantity")%></td>
							<td width="15px" class="mnav">&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-uom")%> nowrap width="70px" class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-hdg-uom", "UOM")%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-unitCost")%> nowrap width="70px" class="mnav" align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-hdg-unitCost", "Unit Cost")%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-extendedCost")%> nowrap width="70px" class="mnav" align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-hdg-extendedCost", "Ext Cost")%>&nbsp;</td>
							<td class="mnav" align="center">&nbsp;<%if(invoiceLineList != null){%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "hdg-viewItemHistory", "Item History")%><%} %>&nbsp;</td>
						</tr>
<%
								if (invoiceLineList == null)
								{
									/**	LINE ITEM HISTORY	**/
									InvoiceLine invoiceLine = (InvoiceLine) request.getAttribute("invoiceLine");
									BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(invoiceLine.getQuantity(), oid);
									BigDecimal bd_unit_price = HiltonUtility.getFormattedDollar(invoiceLine.getUnitPrice(), oid);
									BigDecimal bd_total = HiltonUtility.getFormattedDollar(bd_quantity.multiply(bd_unit_price), oid);
									s_uom = invoiceLine.getUmCode();
%>
									<tr>
											<td <%=HtmlWriter.isVisible(oid, "ivc-lineNumber")%> width="35px" align="right" valign="top"><%=HiltonUtility.getBigDecimalFormatted(invoiceLine.getLineNumber(), 0)%>&nbsp;</td>
											<td <%=HtmlWriter.isVisible(oid, "ivc-itemNumber")%> nowrap width="175px" valign="top"><b><%=invoiceLine.getItemNumber()%></b></td>
											<td <%=HtmlWriter.isVisible(oid, "ivc-quantity")%> width="70px" valign="top" align="right"><%=bd_quantity%></td>
											<td width="15px" >&nbsp;</td>
											<td <%=HtmlWriter.isVisible(oid, "ivc-uom")%> width="70px" valign="top"><%=s_uom%></td>
											<td <%=HtmlWriter.isVisible(oid, "ivc-unitCost")%> width="70px" valign="top" align="right"><%=bd_unit_price%></td>
											<td <%=HtmlWriter.isVisible(oid, "ivc-extendedCost")%> width="70px" valign="top" align="right"><%=bd_total%></td>
											<td align="center">&nbsp;</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td colspan="6"><%=invoiceLine.getDescription()%></td>
										</tr>
								<%}
								else
								{
									/**	ALL REQUISITION HISTORY		**/
									for (int i = 0; i < i_line_count; i++)
									{
										InvoiceLine invoiceLine = (InvoiceLine) invoiceLineList.get(i);
										BigDecimal bd_line_number = invoiceLine.getLineNumber();
										BigDecimal bd_quantity = invoiceLine.getQuantity().setScale(Integer.valueOf(s_quantity_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
										BigDecimal bd_unit_price = invoiceLine.getUnitPrice().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
										BigDecimal bd_total = (bd_quantity.multiply(bd_unit_price)).setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
										s_uom = invoiceLine.getUmCode();
%>
										<tr>
											<td <%=HtmlWriter.isVisible(oid, "ivc-lineNumber")%> width="35px" align="right" valign="top"><%=HiltonUtility.getBigDecimalFormatted(invoiceLine.getLineNumber(), 0)%>&nbsp;</td>
											<td <%=HtmlWriter.isVisible(oid, "ivc-itemNumber")%> nowrap width=175px valign="top"><b><%=invoiceLine.getItemNumber()%></b></td>
											<td <%=HtmlWriter.isVisible(oid, "ivc-quantity")%> width="70px" valign="top" align="right"><%=bd_quantity%></td>
											<td width="15px" >&nbsp;</td>
											<td <%=HtmlWriter.isVisible(oid, "ivc-uom")%> width="70px" valign="top"><%=s_uom%></td>
											<td <%=HtmlWriter.isVisible(oid, "ivc-unitCost")%> width="70px" valign="top" align="right"><%=bd_unit_price%></td>
											<td <%=HtmlWriter.isVisible(oid, "ivc-extendedCost")%> width="70px" valign="top" align="right"><%=bd_total%></td>
											<td align="center">&nbsp;<a href="javascript: viewItemHistory('<%=invoiceLine.getIcLineHistory()%>', '<%=invoiceLine.getIcIvcLine()%>'); void(0);"><img src="<%=contextPath%>/images/icon_history.gif" width="16px" height="16px" alt="Item History" border="0"/></a></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td colspan="6"><%=invoiceLine.getDescription()%></td>
										</tr>
									<%}
								}%>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr><td><br><br><br></td></tr>
<tr>
	<td width="100%" align="center" valign="top">
		<table border="0" cellpadding="0" cellspacing="0" width="645px">
		<tr class="mnav" height="18px">
			<td nowrap class="mnav">Date/Time</td>
			<td width="15px" class="mnav">&nbsp;</td>
			<td nowrap class="mnav">User</td>
			<td width="15px" class="mnav">&nbsp;</td>
			<td nowrap class="mnav">Description</td>
		</tr>
<%
	List	historyLogList = (List) request.getAttribute("historyLogList");
	if (historyLogList != null)
	{
		for (int i = 0; i < historyLogList.size(); i++)
		{
			HistoryLog history = (HistoryLog) historyLogList.get(i);
			String	s_user = history.getUserid();
			String	s_date = HiltonUtility.getFormattedDate(history.getLogDate(), oid, userDateFormat);
			String	s_time = history.getLogTime();
			String	s_description = history.getDescription();

			if (s_user == null)			{	s_user = "";			}
			if (s_date == null)			{	s_date = "";			}
			if (s_time == null)			{	s_time = "";			}
			if (s_description == null)	{	s_description = "";	}

			UserProfile userProfile = UserManager.getInstance().getUser(oid, s_user);
%>
			<tr height="18px">
				<td nowrap valign="top"><%=s_date%>&nbsp;<%=s_time%></td>
				<td width="15px">&nbsp;</td>
				<td nowrap valign="top"><%=userProfile.getDisplayName()%></td>
				<td width="15px">&nbsp;</td>
				<td valign="top"><%=s_description%></td>
			</tr>
<%
		}
	}
%>
<%//@ include file="/base/receipt_history.jsp"%>
<%
	//if ((historyLogList == null || historyLogList.size() <= 0) && (receiptHeaderList == null || receiptHeaderList.size() <= 0)&& (receiptLineList == null || receiptLineList.size() <= 0))
	if ((historyLogList == null || historyLogList.size() <= 0))
	{
%>
		<tr height="18px">
			<td colspan="5" align="center"><b>There is no history available at this time!</b></td>
		</tr>
<%
		}
%>
		</table>
		<table border=0 cellpadding=0 cellspacing=0 width=645px>
		<tr height=18px>
			<td colspan=5 align=center>&nbsp;</td>
		</tr>
		</table>
<%
	if (PropertiesManager.getInstance(oid).getProperty("HISTORY","SENDQUEUE","N").equalsIgnoreCase("Y")) {
%>
		<table border=0 cellpadding=0 cellspacing=0 width=645px>
		<tr class=mnav height=18px>
			<td nowrap class=mnav width=120px>Date/Time</td>
			<td width=10px class=mnav>&nbsp;</td>
			<td nowrap class=mnav width=90px>User</td>
			<td width=10px class=mnav>&nbsp;</td>
			<td nowrap class=mnav width=335px>Action</td>
			<td width=10px class=mnav>&nbsp;</td>
			<td nowrap class=mnav width=70px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%></td>
		</tr>
<%
		List	sendQueueList = (List) request.getAttribute("sendQueueList");
		if (sendQueueList != null)
		{
			for (int i = 0; i < sendQueueList.size(); i++)
			{
				SendQueue sendQueue = (SendQueue) sendQueueList.get(i);
				String	s_user = sendQueue.getOwner();
				String	s_datea = sendQueue.getDateadded();
				String	s_timea = sendQueue.getTimeadded();
				String	s_dates = HiltonUtility.getFormattedDate(sendQueue.getDatesent(), oid, userDateFormat);
				String	s_times = sendQueue.getTimesent();
				String	s_subject = sendQueue.getSubject();
				String	s_message = sendQueue.getMessagetext();
				String	s_error = sendQueue.getErrorText();
				String	s_sendto = sendQueue.getSendto();

				if (s_user == null)			{	s_user = "";	}
				if (s_datea == null)		{	s_datea = "";	}
				if (s_timea == null)		{	s_timea = "";	}
				if (s_dates == null)		{	s_dates = "";	}
				if (s_times == null)		{	s_times = "";	}
				if (s_subject == null)		{	s_subject = "";	}
				if (s_message == null)		{	s_message = "";	}
				if (s_error == null)		{	s_error = "";	}
				if (s_sendto == null)		{	s_sendto = "";	}

				UserProfile userProfile = UserManager.getInstance().getUser(oid, s_user);
%>
			<tr height=18px>
				<td nowrap valign=top width=120px><%=s_dates%>&nbsp;<%=s_times%></td>
				<td width=10px>&nbsp;</td>
				<td nowrap valign=top width=90px><%=userProfile.getDisplayName()%></td>
				<td width=10px>&nbsp;</td>
				<td valign=top width=335px>
					<a href="javascript: void(0);" onmouseover="javascript: shMessage('1','<%=i%>'); void(0);" onmouseout="javascript: shMessage('0','<%=i%>'); void(0);"><%=s_subject%>&nbsp;[<%=s_sendto%>]</a>
				</td>
				<td width=10px>&nbsp;</td>
				<td nowrap valign=top width=70px><%= DictionaryManager.getInstance("queue-status", oid).getProperty(sendQueue.getStatus(), "") %></td>
			</tr>
			<tr>
				<td nowrap valign=top colspan=5>
				<div id="message<%=i%>" style="visibility:hidden; display:none;">
					<table cellspacing="0" cellpadding="0" border="0" width="600px">
					<tr>
					<td width="135px">&nbsp</td>
					<td width="510px"><%=s_message%>
					<!--<% if (!s_error.equals("")) { %>&nbsp;<a href="javascript: void(0);" onmouseover="javascript: shError('1','<%=i%>'); void(0);" onmouseout="javascript: shError('0','<%=i%>'); void(0);">error</a><% } %>-->
					</td></tr>
					<tr><td>
					<div id="error<%=i%>" style="visibility:hidden; display:none;">
						<br><%=s_error%>
					</div>
					</td></tr>
					<tr><td colspan=2>&nbsp;</td></tr>
					</table>
				</div>
				</td>
			</tr>
<%
			}
		}
		if (sendQueueList == null || sendQueueList.size() <= 0)
		{
%>
		<tr height=18px>
			<td colspan=5 align=center><b>There is no send queue Information available at this time!</b></td>
		</tr>
<%
		}
%>
		</table>
<%
	}
%>
	</td>
</tr>
<tr><td><br><br></td></tr>
<tr>
	<td align="center">
		<table border="0" cellpadding="0" cellspacing="0" width="645px">
			<tr>
				<td align="center"><a href="javascript: viewHistory(); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border="0"></a></td>
				<td align="center"><a href="javascript: window.top.hidePopWin(); void(0);"><img class=button src="<%=contextPath%>/images/button_close.gif" border="0"></a></td>
			</tr>
		</table>
	</td>
</tr>
</table>

<tsa:hidden name="HistoryLog_icHeaderHistory" value="<%=HistoryLog_icHeaderHistory %>"/>
<tsa:hidden name="HistoryLog_icLineHistory" value=""/>
<tsa:hidden name="formtype" value="IVC"/>
<tsa:hidden name="InvoiceLine_icIvcLine" value=""/>
<tsa:hidden name="invoiceNumber" value="<%=s_ivc_number%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=s_ivc_header %>"/>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	frm = document.purchaseform;

	function viewItemHistory(icLineHistory, icIvcLine)
	{
		frm.HistoryLog_icLineHistory.value = icLineHistory;
		frm.InvoiceLine_icIvcLine.value = icIvcLine;
		doSubmit('/invoice/iv_history.jsp', 'HistoryLogRetrieveByHistoryLine');
	}

	function viewHistory()
	{
		doSubmit('/invoice/iv_history.jsp', 'HistoryLogRetrieveByHistoryHeader');
	}

	function shMessage(flag,divNumber) {
		if (flag=="1")
			displayArea('message'+divNumber);
		else
			hideArea('message'+divNumber);
	}

	function shError(flag,divNumber) {
		if (flag=="1")
			displayArea('error'+divNumber);
		else
			hideArea('error'+divNumber);
	}

// End Hide script -->
</SCRIPT>