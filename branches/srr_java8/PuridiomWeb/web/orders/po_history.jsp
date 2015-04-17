<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.math.BigDecimal" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String uom = "";

	List	poLineList = (List) request.getAttribute("poLineList");
	List	receiptHeaderList = (List) request.getAttribute("receiptHeaderList");
	List	receiptLineList = (List) request.getAttribute("receiptLineList");
	List	invoiceHeaderList = (List) request.getAttribute("invoiceHeaderList");
	List	invoiceLineList = (List) request.getAttribute("invoiceLineList");
	String	s_po_number = (String) request.getAttribute("poNumber");
	String icPoHeader = (String) request.getAttribute("PoLine_icPoHeader");
	int	i_line_count = 0;
	boolean b_itemHistory = false;

	if (poLineList != null && poLineList.size() > 0)
	{
		PoLine poLine = (PoLine) poLineList.get(0);
		s_po_number = poLine.getPoNumber();
		icPoHeader = poLine.getIcPoHeader().toString();
		i_line_count = poLineList.size();
	}
	String HistoryLog_icHeaderHistory = (String)request.getAttribute("HistoryLog_icHeaderHistory");
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
						<div style="margin-left:10px; margin-right:10px" class=hdr12>Order History</div>
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
		<font class=formType>Order </font><font class=hdr12>#<%=headerEncoder.encodeForHTML(s_po_number)%></font>
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
							<td <%=HtmlWriter.isVisible(oid, "po-lineNumber")%> nowrap width=35px class=mnav align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-lineNumber", "Line #")%>&nbsp;&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "po-itemNumber")%> nowrap width=175px class=mnav><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-itemNumber", "Item/Part #")%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-quantity")%> nowrap width=70px class=mnav align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-quantity", "Quantity")%></td>
							<td width=15px class=mnav>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "po-uom")%> nowrap width=70px class=mnav><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-uom", "UOM")%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-unitCost")%> nowrap width=70px class=mnav align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-unitCost", "Unit Cost")%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-extendedCost")%> nowrap width=70px class=mnav align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-extendedCost", "Ext Cost")%>&nbsp;</td>
							<td class="mnav" align="center">&nbsp;<%if(poLineList != null){%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "hdg-viewItemHistory", "Item History")%><%} %>&nbsp;</td>
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
<%	if (poLineList == null)
		{
			/**	LINE ITEM HISTORY	**/
			b_itemHistory = true;
			PoLine poLine = (PoLine) request.getAttribute("poLine");
			BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid);
			BigDecimal bd_unit_price = HiltonUtility.getFormattedDollar(poLine.getUnitPrice(), oid);
			BigDecimal bd_total = HiltonUtility.getFormattedDollar(bd_quantity.multiply(bd_unit_price), oid);

			uom = poLine.getUmCode();
%>
					<tr>
							<td <%=HtmlWriter.isVisible(oid, "po-lineNumber")%> width=35px align=right valign=top><%=HiltonUtility.getBigDecimalFormatted(poLine.getLineNumber(), 0)%>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "po-itemNumber")%> nowrap width=175px valign=top><b><%=poLine.getItemNumber()%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "po-quantity")%> width=70px valign=top align=right><%=bd_quantity%></td>
							<td width=15px >&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "po-uom")%> width=70px valign=top><%=uom%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-unitCost")%> width=70px valign=top align=right><%=bd_unit_price%></td>
							<td <%=HtmlWriter.isVisible(oid, "po-extendedCost")%> width=70px valign=top align=right><%=bd_total%></td>
							<td align="center">&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td colspan=6><%=poLine.getDescription()%></td>
						</tr>
<%
		}
		else
		{
			/**	ALL PO HISTORY		**/
			for (int i = 0; i < i_line_count; i++)
			{
				PoLine poLine = (PoLine) poLineList.get(i);
				String	s_item_number = poLine.getItemNumber();
				String	s_item_description = poLine.getDescription();
				String s_item_umcode = poLine.getUmCode();
				BigDecimal bd_line_number = poLine.getLineNumber();
				BigDecimal bd_quantity = poLine.getQuantity().setScale(Integer.valueOf(s_quantity_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
				BigDecimal bd_unit_price = poLine.getUnitPrice().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);

				if (s_item_number == null)		{	s_item_number = "";			}
				if (s_item_description == null)	{	s_item_description = "";	}
				if (s_item_umcode == null)		{	s_item_umcode = "";		}

				BigDecimal bd_total = (bd_quantity.multiply(bd_unit_price)).setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
%>
						<tr>
							<td width=35px align=right valign=top><%=HiltonUtility.getBigDecimalFormatted(poLine.getLineNumber(), 0)%>&nbsp;</td>
							<td nowrap width=175px valign=top><b><%=s_item_number%></b></td>
							<td width=70px valign=top align=right><%=bd_quantity%></td>
							<td width=15px >&nbsp;</td>
							<td width=70px valign=top><%=s_item_umcode%></td>
							<td width=70px valign=top align=right><%=bd_unit_price%></td>
							<td width=70px valign=top align=right><%=bd_total%></td>
							<td align="center">&nbsp;<a href="javascript: viewItemHistory('<%=poLine.getIcLineHistory()%>', '<%=poLine.getIcPoLine()%>'); void(0);"><img src="<%=contextPath%>/images/icon_history.gif" width="16px" height="16px" alt="Item History" border="0"/></a></td>
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
			String	s_timeZone = history.getTimeZone();
			String	s_description = history.getDescription();
			String	s_ipAddress = history.getIpAddress();

			if (s_user == null)			{	s_user = "";			}
			if (s_date == null)			{	s_date = "";			}
			if (s_time == null)			{	s_time = "";			}
			if (s_description == null)	{	s_description = "";	}

			UserProfile userProfile = UserManager.getInstance().getUser(oid, s_user);
%>
			<tr height=18px>
				<td nowrap valign=top><%=s_date%>&nbsp;<%=s_time%>&nbsp;<%=HiltonUtility.getFormattedTimeZone(s_timeZone)%></td>
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

<%@ include file="/base/receipt_history.jsp" %>

<%@ include file="/base/invoice_history.jsp" %>

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
				String  s_timeZone = propertiesManager.getProperty("MISC","TIMEZONE","");

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
				<td nowrap valign=top width=120px><%=s_dates%>&nbsp;<%=s_times%>&nbsp;<%=s_timeZone%></td>
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
	<td align="center">
		<table border="0" cellpadding="0" cellspacing="0" width="645px">
			<tr>
				<td align="center"><div id="pxbutton"><a href="javascript: window.print(); void(0);">Print</a></div></td>
<%	if (b_itemHistory) {	%>
				<td align="center"><div id="pxbutton"><a href="javascript: viewHistory(); void(0);">Return</a></div></td>
<%	}	%>
				<td align=center><div id="pxbutton"><a href="javascript: window.top.hidePopWin(); void(0);">Close</a></div></td>
			</tr>
		</table>
	</td>
</tr>

</table>

<tsa:hidden name="HistoryLog_icHeaderHistory" value="<%=HistoryLog_icHeaderHistory %>"/>
<tsa:hidden name="HistoryLog_icLineHistory" value=""/>
<tsa:hidden name="formtype" value="PO"/>
<tsa:hidden name="PoLine_icPoLine" value=""/>
<tsa:hidden name="poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=icPoHeader %>"/>

<%@ include file="/system/footer.jsp" %>
<SCRIPT value=JavaScript>
<!-- Hide script
	frm = document.purchaseform;

	function viewItemHistory(icLineHistory, icPoLine)
	{
		frm.HistoryLog_icLineHistory.value = icLineHistory;
		frm.PoLine_icPoLine.value = icPoLine;
		doSubmit('/orders/po_history.jsp', 'HistoryLogRetrieveByHistoryLine');
	}

	 function viewHistory()
	  {
	    doSubmit('/orders/po_history.jsp', 'HistoryLogRetrieveByHistoryHeader');
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

	function rcvShowEmail(ic, type, number)
	{
		popupUrl = '/orders/print.jsp';
		popupHandler = 'DoNothing';
		popupUserId = '${esapi:encodeForJavaScript(userId)}';
		popupMailId = '${esapi:encodeForJavaScript(mailId)}';
		popupOrganizationId = '<%= oid %>';

		popupParameters = 'ic=' + ic + ';type=' + type + ';number=' + number + ';PoHeader_poNumber=<%=headerEncoder.encodeForJavaScript(s_po_number)%>';
		document.getElementById('recframe').src = '<%= contextPath %>/system/iframe_html.jsp';

		displayArea('recframe');
	}

	function viewRecNow1(ic)
	{
		popupParameters = "ReceiptHeader_icRecHeader=" + ic;
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		popupParameters = popupParameters + ";viewNow=Y";
		doSubmitToPopup('', 'PrintRecPdf', 'width=775px', 'height=850px');
	}

	function viewRecNow(ic)
	{
	  popupUrl = "";
	  popupHandler = "PrintRecPdf";
	  popupUserId = "${esapi:encodeForJavaScript(userId)}";
	  popupOrganizationId = "<%=oid%>";
	  popupParameters = "ReceiptHeader_icRecHeader=" + ic;

	  if (theFocus == null) { theFocus = 'document_window'; }

	  var winspecs = "WIDTH=680,HEIGHT=680,resizable=1,scrollbars=1,menubar=1,location=0,top=0,left=0";
	  document_window = window.open("<%=contextPath%>/system/popup_html.jsp", "document_window", winspecs);

	  if (theFocus == 'main') {
	    self.focus();
	  }
	  else {
	    document_window.focus();
	  }

	  if (document_window.opener == null) document_window.opener = window;
	  self.name = "main";
	}

	function viewInvNow(ic)
	{
	  popupUrl = "";
	  popupHandler = "InvoicePdf";
	  popupUserId = "${esapi:encodeForJavaScript(userId)}";
	  popupOrganizationId = "<%=oid%>";
	  popupParameters = "InvoiceHeader_icIvcHeader=" + ic;

	  if (theFocus == null) { theFocus = 'document_window'; }

	  var winspecs = "WIDTH=680,HEIGHT=680,resizable=1,scrollbars=1,menubar=1,location=0,top=0,left=0";
	  document_window = window.open("<%=contextPath%>/system/popup_html.jsp", "document_window", winspecs);

	  if (theFocus == 'main') {
	    self.focus();
	  }
	  else {
	    document_window.focus();
	  }

	  if (document_window.opener == null) document_window.opener = window;
	  self.name = "main";
	}


	bringSelectedIframeToTop(true);

// End Hide script -->
</SCRIPT>
