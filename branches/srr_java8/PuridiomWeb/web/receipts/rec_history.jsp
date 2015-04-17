<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.Dates" %>
<%@ page import="java.math.BigDecimal" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<% pageContext.setAttribute("oid", oid);
   pageContext.setAttribute("language", language);%>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String uom = "";

	List	reqLineList = (List) request.getAttribute("receiptLineList");
	String	s_req_number = (String) request.getAttribute("receiptNumber");
	String icReqHeader = (String) request.getAttribute("ReceiptLine_icRecHeader");
	int	i_line_count = 0;
	boolean b_itemHistory = false;

	if (reqLineList != null && reqLineList.size() > 0)
	{
		ReceiptLine reqLine = (ReceiptLine) reqLineList.get(0);
		s_req_number = reqLine.getReceiptNumber();
		icReqHeader = reqLine.getIcRecHeader().toString();
		i_line_count = reqLineList.size();
	}
	String HistoryLog_icHeaderHistory = (String)request.getAttribute("HistoryLog_icHeaderHistory");
%>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
<tsa:tr><tsa:td><br></tsa:td></tsa:tr>
<tsa:tr>
	<tsa:td width="100%">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tsa:tr>
			<tsa:td valign="top" width="50px" height="30px">
				<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
				<tsa:tr>
					<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
						<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="receiptHistory" defaultString="Receipt History"/></div>
					</tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
			<tsa:td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
			<tsa:td valign="bottom" align="right" height="30px" width="100%">
				<table cellpadding="0" cellspacing="0" border=0 width=100%>
				<tsa:tr>
					<tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
<tsa:tr><tsa:td><br></tsa:td></tsa:tr>
<tsa:tr>
	<tsa:td align="center">
		<span class=formType><tsa:label labelName="receipt" defaultString="Receipt"/> </span><span class="hdr12">#<%=s_req_number%></span>
	</tsa:td>
</tsa:tr>
<tsa:tr><tsa:td><br></tsa:td></tsa:tr>
<tsa:tr>
	<tsa:td align="center">
		<table border="0" cellpadding="0" cellspacing="0">
			<tsa:tr>
				<tsa:td width="100%" align="center" valign="top">
					<table border="0" cellspacing="0" cellpadding="2" width="521px">
						<tsa:tr cssClass="mnav">
							<tsa:td field="req-lineNumber" noWrap="nowrap" width="35px" cssClass="mnav" align="center"><tsa:label labelName="rec_insCode" defaultString="Ins. Code"/>&nbsp;&nbsp;</tsa:td>
							<tsa:td field="req-itemNumber" noWrap="nowrap" width="75px" cssClass="mnav"><tsa:label labelName="rec_type" defaultString="Type"/></tsa:td>
							<tsa:td field="req-quantity" noWrap="nowrap" width="100px" cssClass="mnav" align="center"><tsa:label labelName="rec_notes" defaultString="Notes"/></tsa:td>
							<tsa:td width="15px" cssClass="mnav">&nbsp;</tsa:td>
							<tsa:td field="req-uom" noWrap="nowrap" width="90px" cssClass="mnav"><tsa:label labelName="req-hdg-uom" defaultString="UOM"/></tsa:td>
							<tsa:td field="req-unitCost" noWrap="nowrap" width="90px" cssClass="mnav" align="center"><tsa:label labelName="rec_inspector" defaultString="Inspector"/></tsa:td>
							<tsa:td field="req-extendedCost" noWrap="nowrap" width="90px" cssClass="mnav" align="center"><tsa:label labelName="rec_engineer" defaultString="Engineer"/>&nbsp;</tsa:td>
							<tsa:td cssClass="mnav" align="center">&nbsp;<%if(reqLineList != null){%><tsa:label labelName="hdg-viewItemHistory" defaultString="Item History"/><%} %>&nbsp;</tsa:td>
						</tsa:tr>
			<%	if (reqLineList == null)
					{
						/**	LINE ITEM HISTORY	**/
						b_itemHistory = true;
						ReceiptLine reqLine = (ReceiptLine) request.getAttribute("receiptLine");

						uom = reqLine.getUmCode();%>
						<tsa:tr>
							<tsa:td field="req-lineNumber" width="35px" align="right" valign="top"><%=HiltonUtility.getBigDecimalFormatted(reqLine.getInspectionCode(), 0)%>&nbsp;</tsa:td>
							<tsa:td field="req-itemNumber" noWrap="nowrap" width="75px" valign="top"><%=reqLine.getReceiptType()%></tsa:td>
							<tsa:td field="req-quantity" noWrap="nowrap" width="100px" valign="top"><%=reqLine.getReceiptNotes()%></tsa:td>
							<tsa:td width="15px" >&nbsp;</tsa:td>
							<tsa:td field="req-uom" width="90px" valign="top"><%=uom%></tsa:td>
							<tsa:td field="req-unitCost" noWrap="nowrap" width="90px" valign="top"><%=reqLine.getInspectorAssigned()%></tsa:td>
							<tsa:td field="req-extendedCost" noWrap="nowrap" width="90px" valign="top"><%=reqLine.getEngineerAssigned()%></tsa:td>
							<tsa:td align="center">&nbsp;</tsa:td>
						</tsa:tr>
						<tsa:tr>
							<tsa:td>&nbsp;</tsa:td>
							<tsa:td colspan="6"><%=reqLine.getReceiptNotes()%></tsa:td>
						</tsa:tr>
					<%}
					else
					{
						/**	ALL REQUISITION HISTORY		**/
						for (int i = 0; i < i_line_count; i++)
						{
							ReceiptLine reqLine = (ReceiptLine) reqLineList.get(i);
							BigDecimal bd_line_number = HiltonUtility.getBigDecimalFormatted(reqLine.getReceiptNumber(),0);

							uom = reqLine.getUmCode();%>
						<tsa:tr>
						<tsa:td field="req-lineNumber" width="35px" align="right" valign="top"><%=HiltonUtility.getBigDecimalFormatted(reqLine.getInspectionCode(), 0)%>&nbsp;</tsa:td>
							<tsa:td field="req-itemNumber" noWrap="nowrap" width="75px" valign="top"><%=reqLine.getReceiptType()%></tsa:td>
							<tsa:td field="req-quantity" noWrap="nowrap" width="100px" valign="top"><%=reqLine.getReceiptNotes()%></tsa:td>
							<tsa:td width="15px" >&nbsp;</tsa:td>
							<tsa:td field="req-uom" width="90px" valign="top"><%=uom%></tsa:td>
							<tsa:td field="req-unitCost" noWrap="nowrap" width="90px" valign="top"><%=reqLine.getInspectorAssigned()%></tsa:td>
							<tsa:td field="req-extendedCost" noWrap="nowrap" width="90px" valign="top"><%=reqLine.getEngineerAssigned()%></tsa:td>
							<tsa:td align="center">&nbsp;<a href="javascript: viewItemHistory('<%=reqLine.getIcLineHistory()%>', '<%=reqLine.getIcRecLine()%>'); void(0);"><img src="<%=contextPath%>/images/icon_history.gif" width="16px" height="16px" alt="Item History" border="0"/></a></tsa:td>
						</tsa:tr>
						<tsa:tr>
							<tsa:td>&nbsp;</tsa:td>
							<tsa:td colspan="6"><%=reqLine.getReceiptNotes()%></tsa:td>
						</tsa:tr>
						<%}
					}%>
						</table>
					</tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
		</tsa:tr>
		<tsa:tr><tsa:td><br><br><br></tsa:td></tsa:tr>
		<tsa:tr>
			<tsa:td width="100%" align="center" valign="top">
				<table border="0" cellpadding="0" cellspacing="0" width="645px">
				<tsa:tr cssClass="mnav" height="18px">
					<tsa:td noWrap="nowrap" cssClass="mnav"><tsa:label labelName="dateTime" defaultString="Date/Time"/></tsa:td>
					<tsa:td width="15px" cssClass="mnav">&nbsp;</tsa:td>
					<tsa:td noWrap="nowrap" cssClass="mnav"><tsa:label labelName="user" defaultString="User"/></tsa:td>
					<tsa:td width="15px" cssClass="mnav">&nbsp;</tsa:td>
					<tsa:td noWrap="nowrap" cssClass="mnav"><tsa:label labelName="description" defaultString="Description"/></tsa:td>
					<tsa:td width="15px" cssClass="mnav">&nbsp;</tsa:td>
					<tsa:td noWrap="nowrap" cssClass="mnav"><tsa:label labelName="ipAddress" defaultString="IP Address"/></tsa:td>
				</tsa:tr>
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
				<tsa:tr height="18px">
					<tsa:td noWrap="nowrap" valign="top"><%=s_date%>&nbsp;<%=s_time%>&nbsp;<%=HiltonUtility.getFormattedTimeZone(s_timeZone)%></tsa:td>
					<tsa:td width="15px">&nbsp;</tsa:td>
					<tsa:td noWrap="nowrap" valign="top"><%=userProfile.getDisplayName()%></tsa:td>
					<tsa:td width="15px">&nbsp;</tsa:td>
					<tsa:td valign="top"><%=s_description%></tsa:td>
					<tsa:td width="15px">&nbsp;</tsa:td>
					<tsa:td valign="top"><%=s_ipAddress%></tsa:td>
				</tsa:tr>
<%
		}
	}
	if (historyLogList == null || historyLogList.size() <= 0)
	{
%>
				<tsa:tr height="18px">
					<tsa:td colspan="5" align="center"><b><tsa:label labelName="there-is-no-history" defaultString="There is no history available at this time"/>!</b></tsa:td>
				</tsa:tr>
<%
		}
%>
				</table>

				<table border=0 cellpadding=0 cellspacing=0 width=645px>
				<tsa:tr height="18px">
					<tsa:td colspan="5" align="center">&nbsp;</tsa:td>
				</tsa:tr>
				</table>
<%
	if (PropertiesManager.getInstance(oid).getProperty("HISTORY","SENDQUEUE","N").equalsIgnoreCase("Y")) {
%>
				<table border=0 cellpadding=0 cellspacing=0 width=645px>
				<tsa:tr cssClass="mnav" height="18px">
					<tsa:td noWrap="nowrap" cssClass="mnav" width="120px"><tsa:label labelName="req-date-time" defaultString="Date/Time"/></tsa:td><!--  width=120px -->
					<tsa:td width="10px" cssClass="mnav">&nbsp;</tsa:td>
					<tsa:td noWrap="nowrap" cssClass="mnav" width="90px"><tsa:label labelName="req-user" defaultString="User"/></tsa:td><!--  width=90px -->
					<tsa:td width="10px" cssClass="mnav">&nbsp;</tsa:td>
					<tsa:td noWrap="nowrap" cssClass="mnav" width="335px"><tsa:label labelName="req-action" defaultString="Action"/></tsa:td><!--  width=335px -->
					<tsa:td width="10px" cssClass="mnav">&nbsp;</tsa:td>
					<tsa:td noWrap="nowrap" cssClass="mnav" width="70px"><tsa:label labelName="status" defaultString="Status"/></tsa:td>
				</tsa:tr>
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
				<tsa:tr height="18px">
					<tsa:td noWrap="nowrap" valign="top"><%=s_dates%>&nbsp;<%=s_times%></tsa:td>
					<tsa:td width="10px">&nbsp;</tsa:td>
					<tsa:td noWrap="nowrap" valign="top"><%=userProfile.getDisplayName()%></tsa:td>
					<tsa:td width="10px">&nbsp;</tsa:td>
					<tsa:td valign="top">
						<a href="javascript: void(0);" onmouseover="javascript: shMessage('1','<%=i%>'); void(0);" onmouseout="javascript: shMessage('0','<%=i%>'); void(0);"><%=s_subject%>&nbsp;[<%=s_sendto%>]</a>
					</tsa:td>
					<tsa:td width="10px">&nbsp;</tsa:td>
					<tsa:td noWrap="nowrap" valign="top" width="70px"><%= DictionaryManager.getInstance("queue-status", oid).getProperty(sendQueue.getStatus(), "") %></tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td valign="top" colspan="5">
					<div id="message<%=i%>" style="visibility:hidden; display:none;">
						<table cellspacing="0" cellpadding="0" border="0" width="600px">
						<tsa:tr>
						<tsa:td width="135px">&nbsp</tsa:td>
						<tsa:td width="510px"><%=s_message%>
						<!--<% if (!s_error.equals("")) { %>&nbsp;<a href="javascript: void(0);" onmouseover="javascript: shError('1','<%=i%>'); void(0);" onmouseout="javascript: shError('0','<%=i%>'); void(0);">error</a><% } %>-->
						</tsa:td></tsa:tr>
						<tsa:tr><tsa:td>
						<div id="error<%=i%>" style="visibility:hidden; display:none;">
							<br><%=s_error%>
						</div>
						</tsa:td></tsa:tr>
						<tsa:tr><tsa:td colspan="2">&nbsp;</tsa:td></tsa:tr>
						</table>
					</div>
					</tsa:td>
				</tsa:tr>
<%
			}
		}
		if (sendQueueList == null || sendQueueList.size() <= 0)
		{
%>
				<tsa:tr height="18px">
					<tsa:td colspan="5" align="center"><b><tsa:label labelName="there-is-no-sendqueue-information" defaultString="There is no send queue Information available at this time"/>!</b></tsa:td>
				</tsa:tr>
<%
		}
%>
				</table>
<%
	}
%>
			</tsa:td>
		</tsa:tr>
		<tsa:tr><tsa:td><br><br></tsa:td></tsa:tr>
		<tsa:tr>
			<tsa:td align="center">
				<table border="0" cellpadding="0" cellspacing="0" width="645px">
					<tsa:tr>
						<tsa:td align="center"><div id="pxbutton"><a href="javascript: window.print(); void(0);"><tsa:label labelName="req-print" defaultString="Print"/></a></div></tsa:td>
<%	if (b_itemHistory) {	%>
						<tsa:td align="center"><div id="pxbutton"><a href="javascript: viewHistory(); void(0);"><tsa:label labelName="req-return" defaultString="Return"/></a></div></tsa:td>
<%	}	%>
						<tsa:td align="center"><div id="pxbutton"><a href="javascript: window.top.hidePopWin(); void(0);"><tsa:label labelName="req-close" defaultString="Close"/></a></div></tsa:td>
					</tsa:tr>
				</table>
			</tsa:td>
		</tsa:tr>
		</table>
<tsa:hidden name="HistoryLog_icHeaderHistory" value="<%=HistoryLog_icHeaderHistory %>"/>
<tsa:hidden name="HistoryLog_icLineHistory" value=""/>
<tsa:hidden name="formtype" value="REC"/>
<tsa:hidden name="ReceiptLine_icRecLine" value=""/>
<tsa:hidden name="receiptNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=icReqHeader %>"/>

<%@ include file="/system/footer.jsp" %>
<SCRIPT value=JavaScript>
<!-- Hide script
	frm = document.purchaseform;

	function viewItemHistory(icLineHistory, icReqLine)
	{
		frm.HistoryLog_icLineHistory.value = icLineHistory;
		frm.ReceiptLine_icRecLine.value = icReqLine;
		doSubmit('/receipts/rec_history.jsp', 'HistoryLogRetrieveByHistoryLine');
	}

	 function viewHistory()
	  {
	    doSubmit('/receipts/rec_history.jsp', 'HistoryLogRetrieveByHistoryHeader');
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

		popupParameters = 'ic=' + ic + ';type=' + type + ';number=' + number;
		document.getElementById('recframe').src = '<%= contextPath %>/system/iframe_html.jsp';

		displayArea('recframe');
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

// End Hide script -->
</SCRIPT>