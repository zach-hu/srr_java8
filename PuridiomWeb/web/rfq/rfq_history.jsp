<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.math.BigDecimal" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String uom = "";

	List	rfqLineList = (List) request.getAttribute("rfqLineList");
	List	receiptHeaderList = (List) request.getAttribute("receiptHeaderList");
	List	receiptLineList = (List) request.getAttribute("receiptLineList");
	String	s_rfq_number = (String) request.getAttribute("rfqNumber");
	int	i_line_count = 0;

	if (rfqLineList != null && rfqLineList.size() > 0)
	{
		RfqLine rfqLine = (RfqLine) rfqLineList.get(0);
		s_rfq_number = rfqLine.getRfqNumber();
		i_line_count = rfqLineList.size();
	}

%>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

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
						<div style="margin-left:10px; margin-right:10px" class=hdr12>Solicitation History</div>
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
		<font class=formType>Solicitation </font><font class=hdr12>#<%=headerEncoder.encodeForHTML(s_rfq_number)%></font>
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
							<td <%=HtmlWriter.isVisible(oid, "rfq-lineNumber")%> nowrap width=35px class=mnav align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-hdg-lineNumber", "Line #")%>&nbsp;&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-itemNumber")%> nowrap width=175px class=mnav><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-hdg-itemNumber", "Item/Part #")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-quantity")%> nowrap width=70px class=mnav align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-hdg-quantity", "Quantity")%></td>
							<td width=15px class=mnav>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-uom")%> nowrap width=70px class=mnav><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-hdg-uom", "UOM")%></td>
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
<%	if (rfqLineList == null)
		{
			/**	LINE ITEM HISTORY	**/
			RfqLine rfqLine = (RfqLine) request.getAttribute("rfqLine");
			BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), oid);

			uom = rfqLine.getUmCode();
%>
					<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-lineNumber")%> width=35px align=right valign=top><%=HiltonUtility.getBigDecimalFormatted(rfqLine.getRfqLine(), 0)%>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-itemNumber")%> nowrap width=175px valign=top><b><%=rfqLine.getItemNumber()%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-quantity")%> width=70px valign=top align=right><%=bd_quantity%></td>
							<td width=15px >&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-uom")%> width=70px valign=top><%=uom%></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td colspan=6><%=rfqLine.getDescription()%></td>
						</tr>
<%
		}
		else
		{
			/**	ALL SOLICITATION HISTORY		**/
			for (int i = 0; i < i_line_count; i++)
			{
				RfqLine rfqLine = (RfqLine) rfqLineList.get(i);
				String	s_item_number = rfqLine.getItemNumber();
				String	s_item_description = rfqLine.getDescription();
				String s_item_umcode = rfqLine.getUmCode();
				BigDecimal bd_line_number = rfqLine.getRfqLine();
%>
						<tr>
							<td width=35px align=right valign=top><%=HiltonUtility.getBigDecimalFormatted(rfqLine.getRfqLine(), 0)%>&nbsp;</td>
							<td nowrap width=175px valign=top><b><%=s_item_number%></b></td>
							<td width=70px valign=top align=right><%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), oid)%></td>
							<td width=15px >&nbsp;</td>
							<td width=70px valign=top><%=s_item_umcode%></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td colspan=6><%=s_item_description%></td>
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
			<td nowrap class=mnav>Date/Time</td>
			<td width=15px class=mnav>&nbsp;</td>
			<td nowrap class=mnav>User</td>
			<td width=15px class=mnav>&nbsp;</td>
			<td nowrap class=mnav>Description</td>
			<td width="15px" class="mnav">&nbsp;</td>
			<td nowrap class="mnav">IP Address</td>
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
			String	s_ipAddress = history.getIpAddress();

			if (s_user == null)			{	s_user = "";			}
			if (s_date == null)			{	s_date = "";			}
			if (s_time == null)			{	s_time = "";			}
			if (s_description == null)	{	s_description = "";	}

			UserProfile userProfile = UserManager.getInstance().getUser(oid, s_user);
%>
			<tr height=18px>
				<td nowrap valign=top><%=s_date%>&nbsp;<%=s_time%></td>
				<td width=15px>&nbsp;</td>
				<td nowrap valign=top><%=userProfile.getDisplayName()%></td>
				<td width=15px>&nbsp;</td>
				<td valign=top><%=s_description%></td>
				<td width="15px">&nbsp;</td>
				<td valign="top"><%=s_ipAddress%></td>
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
<%@ include file="/base/receipt_history.jsp"%>
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
				<td valign=top colspan=5>
				<div id="message<%=i%>" style="visibility:hidden; display:none;" onmouseover="javascript: shMessage('1','<%=i%>'); void(0);" onmouseout="javascript: shMessage('0','<%=i%>'); void(0);">
					<table cellspacing="0" cellpadding="0" border="0">
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
	<td align=center><div id="pxbutton"><a href="javascript: window.top.hidePopWin(); void(0);">Close</a></div></td>
</tr>

</table>

<%@ include file="/system/footer.jsp" %>
<script value=JavaScript>
<!-- Hide script

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
</script>