<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	int i;

	BigDecimal bd_icRfqHeader = rfqHeader.getIcRfqHeader();
	String s_rfqStatus = rfqHeader.getStatus();
	String s_rfqNumber = rfqHeader.getRfqNumber();
	if (HiltonUtility.isEmpty(s_rfqNumber)){ s_rfqNumber = "N/A"; }
	String s_rfqAmendment = rfqHeader.getRfqAmendment();

	//Post Options
	String s_webpost = rfqHeader.getWebpost();
	String s_bidAccess = rfqHeader.getBidAccess();
	String s_auctionType = rfqHeader.getAuctionType();
	String s_bidItemOptions = rfqHeader.getBidItemOptions();
	String s_lowBidReq = rfqHeader.getLowestBidReq();
	String s_lowBidSource = rfqHeader.getLowestBidSource();
	String s_lowestDisplay = rfqHeader.getLowestDisplay();

	boolean	b_post = false;
	boolean editMode = false;

	if (role.getAccessRights("RFQ") > 1 && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENAMENDMENT) == 0)) {
		editMode = true;
	}

	if ( (s_webpost.indexOf("V") >= 0) || (s_webpost.indexOf("D") >= 0) || (s_webpost.indexOf("P") >= 0) )
	{
		b_post = true;
	}

	String	s_current_process = "WEBPOST_OPTIONS";
	String	s_current_page = "/rfq/rfq_webpost_options.jsp";
	String	s_current_method = "RfqHeaderUpdateById";
	String	s_current_process_method = "";
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="RfqQuestion_icRfqHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="${rfqHeader.rfqType}"/>
<tsa:hidden name="RfqHeader_fiscalYear" value="<%=rfqHeader.getFiscalYear()%>"/>
<tsa:hidden name="RfqHeader_dueDate" value="<%=HiltonUtility.getFormattedDate(rfqHeader.getDueDate(), oid, userDateFormat)%>"/>
<tsa:hidden name="RfqHeader_bidDueTime" value="<%=rfqHeader.getBidDueTime()%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="allowBrowse" value="true"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width=1px height=1px /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="rfq-Post-Options" defaultString="Post Options" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height=31px /></td>
	<td valign=bottom align=right height=30px width=100%>
		<%@ include file="/rfq/rfq_status_title.jsp" %>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top>
		<table border=0 cellpadding=0 cellspacing=0>
		<tr>
			<td width=5px>&nbsp;</td>
			<td valign=top width=250px height=100%>
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td colspan=3 nowrap><b><tsa:label labelName="rfq-Web-Post-Options" defaultString="Web Post Options" />:</b>
						<tsa:hidden name="RfqHeader_webpost" value="<%=s_webpost%>"/>
					</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
					<% if ( b_post ) {%>
						<b><tsa:input type="checkbox" name="c_webpost" value="N" tabIndex="18" onclick="editWebPost(true);" checked="true" /></b>
					<% } else { %>
						<b><tsa:input type="checkbox" name="c_webpost" value="N" tabIndex="18" onclick="editWebPost(true);" /></b>
					<% } %>
					</td>
					<td>Post Solicitation</td>
				</tr>
				<tr id="postOption1">
					<td width=10px>&nbsp;</td>
					<td align=right>
					<% if (s_webpost.indexOf("B") >= 0) { %>
						<b><tsa:input type="checkbox" name="c_webpost" value="V" tabIndex="20" onclick="if (checkWebPost(this, 'Y')) { return editWebPost(true); } else { return false; }" checked="checked" /></b>
					<% } else { %>
						<b><tsa:input type="checkbox" name="c_webpost" value="V" tabIndex="20" onclick="if (checkWebPost(this, 'Y')) { return editWebPost(true); } else { return false; }" /></b>
					<% } %>
					</td>
					<td><tsa:label labelName="rfq-Online-Bidding" defaultString="Online Bidding" /></td>
				</tr>
				<tr id="postOption2">
					<td width=10px>&nbsp;</td>
					<td align=right>
					<% if (s_webpost.indexOf("D") >= 0) { %>
						<b><tsa:input type="checkbox" name="c_webpost" value="D" tabIndex="20" onclick="if (checkWebPost(this, 'Y')) { return editWebPost(true); } else { return false; }" checked="true" /></b>
					<% } else { %>
						<b><tsa:input type="checkbox" name="c_webpost" value="D" tabIndex="20" onclick="if (checkWebPost(this, 'Y')) { return editWebPost(true); } else { return false; }" /></b>
					<% } %>
					</td>
					<td><tsa:label labelName="rfq-Downloadable" defaultString="Downloadable" /></td>
				</tr>
				</table>

				<br>

				<div  id="bidType">
				<table border=0 valign=bottom cellpadding=1 cellspacing=0>
				<tr>
					<td colspan=3 valign=bottom nowrap><b><tsa:label labelName="rfq-Bid-Type" defaultString="Bid Type" />:</b>
						<tsa:hidden name="RfqHeader_auctionType" value="<%=s_auctionType%>"/>
					</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
					<% if (s_auctionType.indexOf("S") == 0) { %>
						<tsa:input type="radio" name="rfh_auction_type" value="S" tabIndex="36" onclick="if (checkWebPost(this, 'Y')) { setAuctionType('S'); setAuctionOptions(true); }" checked="true" />
					<% } else { %>
						<tsa:input type="radio" name="rfh_auction_type" value="S" tabIndex="36" onclick="if (checkWebPost(this, 'Y')) { setAuctionType('S'); setAuctionOptions(true); }" />
					<% } %>
					</td>
					<td nowrap><tsa:label labelName="rfq-Sealed-Bid" defaultString="Sealed Bid" /></td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
					<% if (s_auctionType.indexOf("O") == 0) { %>
						<tsa:input type="radio" name="rfh_auction_type" value="O" tabIndex="36" onclick="if (checkWebPost(this, 'Y')) { setAuctionType('O'); setAuctionOptions(true); }" checked="true" />
					<% } else { %>
						<tsa:input type="radio" name="rfh_auction_type" value="O" tabIndex="36" onclick="if (checkWebPost(this, 'Y')) { setAuctionType('O'); setAuctionOptions(true); }" />
					<% } %>
					</td>
					<td nowrap><tsa:label labelName="rfq-Open-Auction" defaultString="Open Auction" /></td>
				</tr>
				</table>
				<br>
				</div>

				<div  id="lowestBids">
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td colspan=3 valign=bottom nowrap><b><tsa:label labelName="rfq-Base-Lowest-Bids-on" defaultString="Base Lowest Bids on" />:</b>
						<tsa:hidden name="RfqHeader_lowestBidSource" value="<%=s_lowBidSource%>"/>
					</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
					<% if (s_lowBidSource.indexOf("L") == 0) { %>
						<tsa:input type="radio" name="rfh_lowest_bid_source" value="L" checked="true" tabIndex="40" onclick="if (checkWebPost(this, 'Y')) { setLowBidSource('L'); return setAuctionOptions(true); } else { return false; }" />
					<% } else { %>
						<tsa:input type="radio" name="rfh_lowest_bid_source" value="L" tabIndex="40" onclick="if (checkWebPost(this, 'Y')) { setLowBidSource('L'); return setAuctionOptions(true); } else { return false; }" />
					<% } %>
					</td>
					<td nowrap><tsa:label labelName="rfq-Line-Item-Amounts" defaultString="Line Item Amounts" /></td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
					<% if (s_lowBidSource.indexOf("S") == 0){%>
						<tsa:input labelName="rfq-Subtotal_Amount" type="radio" name="rfh_lowest_bid_source" value="S" checked="true" tabIndex="41" onclick="if (checkWebPost(this, 'Y')) { setLowBidSource('S'); return setAuctionOptions(true); } else { return false; }" />
					<% } else { %>
						<tsa:input labelName="rfq-Subtotal_Amount" type="radio" name="rfh_lowest_bid_source" value="S" tabIndex="41" onclick="if (checkWebPost(this, 'Y')) { setLowBidSource('S'); return setAuctionOptions(true); } else { return false; }" />
					<% } %>
					</td>
					<td nowrap><tsa:label labelName="rfq-Subtotal_Amount" defaultString="Subtotal Amount" /></td>
				</tr>
				</table>
				<br>
				</div>

<%	if (!extRfqsActive) {%>
			</td>
			<td valign=top height=100% valign=top>
<%	}%>
				<div  id="supplierAccess">
				<table border=0 cellpadding=1 cellspacing=0>
				<tr height=22px>
					<td colspan=3 nowrap><b><tsa:label labelName="rfq-Supplier-Access" defaultString="Supplier Access" />:</b>
						<tsa:hidden name="RfqHeader_bidAccess" value="<%=s_bidAccess%>"/>
					</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right valign=top>
					<% if (s_bidAccess.indexOf("U") == 0) { %>
						<tsa:input type="radio" name="rfh_bid_access" value="U" tabIndex="24" onclick="if (checkWebPost(this, 'Y')) { setBidAccess('U'); } else { return false; }" checked="true" />
					<% } else { %>
						<tsa:input type="radio" name="rfh_bid_access" value="U" tabIndex="24" onclick="if (checkWebPost(this, 'Y')) { setBidAccess('U'); } else { return false; }" />
					<% } %>
					</td>
					<td>
						<table border=0 cellpadding=0 cellspacing=0>
						<tr>
							<td nowrap>
								Unrestricted&nbsp;<img src="<%=contextPath%>/images/browser.gif" border=0 title="Unrestricted - Anyone can view, registered suppliers can bid">
							</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right valign=top>
					<% if (s_bidAccess.indexOf("I") == 0) { %>
						<tsa:input type="radio" name="rfh_bid_access" value="I" tabIndex="26" onclick="if (checkWebPost(this, 'Y')) { setBidAccess('I'); } else { return false; }" checked="true" />
					<% } else { %>
						<tsa:input type="radio" name="rfh_bid_access" value="I" tabIndex="26" onclick="if (checkWebPost(this, 'Y')) { setBidAccess('I'); } else { return false; }" />
					<% } %>
					</td>
					<td>
						<table height=10px cellpadding=1 cellspacing=0>
						<tr>
							<td>
								By Invitation Only&nbsp;<img src="<%=contextPath%>/images/browser.gif" border=0 title="By Invitation Only - Registered suppliers can view, only selected suppliers can bid">
							</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right  valign=top>
					<% if (s_bidAccess.indexOf("R") == 0) { %>
						<tsa:input type="radio" name="rfh_bid_access" value="R" tabIndex="28" onclick="if (checkWebPost(this, 'Y')) { setBidAccess('R'); } else { return false; }" checked="true" />
					<% } else { %>
						<tsa:input type="radio" name="rfh_bid_access" value="R" tabIndex="28" onclick="if (checkWebPost(this, 'Y')) { setBidAccess('R'); } else { return false; }" />
					<% } %>
					</td>
					<td>
						<table height=10px>
						<tr>
							<td nowrap>
								Restrict Suppliers&nbsp;<img src="<%=contextPath%>/images/browser.gif" border=0 title="Restrict Suppliers - Only selected suppliers can view or bid">
							</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
				<br>
				</div>

				<div  id="bidRequirements">
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td colspan=3 valign=bottom nowrap><b><tsa:label labelName="rfq-Bid-Requirements" defaultString="Bid Requirements" />:</b></td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
					<%if (s_bidItemOptions.indexOf("A") == 0) { %>
						<tsa:input type="checkbox" name="c_bid_item_options" value="A" checked="true" tabIndex="30" onclick="if (checkWebPost(this, 'Y')) { return setBidReq(); } else { return false; }" />
					<% } else { %>
						<tsa:input type="checkbox" name="c_bid_item_options" value="A" tabIndex="30" onclick="if (checkWebPost(this, 'Y')) { return setBidReq(); } else { return false; }" />
					<% } %>
						<tsa:hidden name="RfqHeader_bidItemOptions" value="<%=s_bidItemOptions%>"/>
					</td>
					<td nowrap><tsa:label labelName="rfq-Must-Bid" defaultString="Must Bid on All Items" /></td>
				</tr>
				<tr id="lowestRequirement" height=32px>
					<td width=10px>&nbsp;</td>
					<td align=right valign=top>
					<%if (s_lowBidReq.indexOf("Y") == 0) { %>
						<tsa:input type="checkbox" name="c_lowest_bid_req" value="Y" checked="true" tabIndex="32" onclick="if (checkWebPost(this, 'Y')) { if (setAuctionOptions(true)) { return setBidReq(); } } else { return false; }" />
					<% } else { %>
						<tsa:input type="checkbox" name="c_lowest_bid_req" value="Y" tabIndex="32" onclick="if (checkWebPost(this, 'Y')) { if (setAuctionOptions(true)) { return setBidReq(); } } else { return false; }" />
					<% } %>
						<tsa:hidden name="RfqHeader_lowestBidReq" value="<%=s_lowBidReq%>"/>
					</td>
					<td valign=middle>Must Enter Bids Before Viewing <br> Lowest Bid</td>
				</tr>
				<tr id="proxySetting" height=32px>
					<td width=10px>&nbsp;</td>
					<td align=right>
					<%if (rfqHeader.getAllowProxyBids().indexOf("Y") == 0) { %>
						<tsa:input type="checkbox" name="c_proxy_setting" value="Y" checked="true" tabIndex="33" onclick="if (checkWebPost(this, 'Y')) { if (setAuctionOptions(true)) { return setBidReq(); } } else { return false; }" />
					<% } else { %>
						<tsa:input type="checkbox" name="c_proxy_setting" value="Y" tabIndex="33" onclick="if (checkWebPost(this, 'Y')) { if (setAuctionOptions(true)) { return setBidReq(); } } else { return false; }" />
					<% } %>
						<tsa:hidden name="RfqHeader_allowProxyBids" value="<%=rfqHeader.getAllowProxyBids()%>"/>
					</td>
					<td><tsa:label labelName="rfq-Allow-Proxy-Bids" defaultString="Allow Proxy Bids" /></td>
				</tr>
				</table>
				<br>
				</div>

				<div  id="lowestDisplay">
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td colspan=3 valign=bottom nowrap><b><tsa:label labelName="rfq-Lowest-Bid-Display-Options" defaultString="Lowest Bid Display Options" />:</b>
						<tsa:hidden name="RfqHeader_lowestDisplay" value="<%=s_lowestDisplay%>"/>
					</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
					<% if (s_lowestDisplay.indexOf("A") == 0) { %>
						<tsa:input type="radio" name="rfh_lowest_display" value="A" checked="true" tabIndex="42" onclick="if (checkWebPost(this, 'Y')) { setLowestDisplay('A'); return setAuctionOptions(true) } else { return false; }" />
					<% } else { %>
						<tsa:input type="radio" name="rfh_lowest_display" value="A" tabIndex="42" onclick="if (checkWebPost(this, 'Y')) { setLowestDisplay('A'); return setAuctionOptions(true) } else { return false; }" />
					<% } %>
					</td>
					<td nowrap><tsa:label labelName="msg-lowest-bid-dollar_amount" defaultString="Display lowest bid dollar amount" />.</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
					<% if (s_lowestDisplay.indexOf("N") == 0) { %>
						<tsa:input type="radio" name="rfh_lowest_display" value="N" checked="true" tabIndex="43" onclick="if (checkWebPost(this, 'Y')) { setLowestDisplay('N'); return setAuctionOptions(true) } else { return false; }" />
					<% } else { %>
						<tsa:input type="radio" name="rfh_lowest_display" value="N" tabIndex="43" onclick="if (checkWebPost(this, 'Y')) { setLowestDisplay('N'); return setAuctionOptions(true) } else { return false; }" />
					<% } %>
					</td>
					<td nowrap><tsa:label labelName="rfq-lowest-bid-notification" defaultString="Display a lowest bid notification" />.</td>
				</tr>
				</table>
				</div>
			</td>
			<td valign=top height=100% valign=top>
				<div  id="auctionEvents" style="display:none; visibility:visible">
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td colspan=3 valign=bottom nowrap><b><tsa:label labelName="rfq-Bid-Events" defaultString="Bid Events" />:</b></td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
					<%if (rfqHeader.getQaEvent().equals("Y")) { %>
						<tsa:input type="checkbox" name="c_qa_event" value="Y" checked="true" tabIndex="30" onclick="if (checkWebPost(this, 'Y')) { return setEventOptions(true); } else { return false; }" />
					<% } else { %>
						<tsa:input type="checkbox" name="c_qa_event" value="Y" tabIndex="30" onclick="if (checkWebPost(this, 'Y')) { return setEventOptions(true); } else { return false; }" />
					<% } %>
						<tsa:hidden name="RfqHeader_qaEvent" value="<%=rfqHeader.getQaEvent()%>"/>
					</td>
					<td nowrap><tsa:label labelName="rfq-Question-Answer" defaultString="Online Question & Answer Event" /></td>
				</tr>
				<tr id="qaEventTime">
					<td colspan=2>&nbsp;</td>
					<td>
						<table border=0 cellpadding=1 cellspacing=0>
						<tr>
							<td align=right nowrap><tsa:label labelName="rfq-Start_Date" defaultString="Start Date" />:</td>
							<td>
								<tsa:input type="text" name="RfqHeader_qaStartDate" tabIndex="5" size="12" maxLength="10" value="<%=HiltonUtility.getFormattedDate(rfqHeader.getQaStartDate(), oid, userDateFormat)%>" onchange="controlStartDate(this);" />
								<a href="javascript: show_calendar('RfqHeader_qaStartDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
							</td>
						</tr>
						<tr>
							<td align=right><tsa:label labelName="rfq-Start-Time" defaultString="Start Time" />:</td>
							<td>
								<tsa:input type="text" name="qa_start_time" size="6" maxLength="5" value="<%=rfqHeader.getQaStartTime()%>" onchange="setTime(frm.qa_start_time, frm.qa_start_timeofday, frm.RfqHeader_qaStartTime);" />
								<select name="qa_start_timeofday" onchange="setTime(frm.qa_start_time, frm.qa_start_timeofday, frm.RfqHeader_qaStartTime);">
									<option value="pm">P.M.</option>
									<option option="am">A.M.</option>
								</select>
								<%=HiltonUtility.getFormattedTimeZone(rfqHeader.getTimeZone())%>
								<tsa:hidden name="RfqHeader_qaStartTime" value="<%=rfqHeader.getQaStartTime()%>"/>
							</td>
						</tr>
						<tr>
							<td align=right nowrap><tsa:label labelName="rfq-End_Date" defaultString="End Date" />:</td>
							<td>
								<tsa:input type="text" name="RfqHeader_qaEndDate" tabIndex="5" size="12" maxLength="10" value="<%=HiltonUtility.getFormattedDate(rfqHeader.getQaEndDate(), oid, userDateFormat)%>" onchange="controlEndDate(this, frm.RfqHeader_qaStartDate);" />
								<a href="javascript: show_calendar('RfqHeader_qaEndDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
							</td>
						</tr>
						<tr>
							<td align=right><tsa:label labelName="rfq-End-Time" defaultString="End Time" />:</td>
							<td>
								<tsa:input type="text" name="qa_end_time" size="6" maxLength="5" value="<%=rfqHeader.getQaEndTime()%>" onchange="setTime(frm.qa_end_time, frm.qa_end_timeofday, frm.RfqHeader_qaEndTime);" />
								<select name="qa_end_timeofday" onchange="setTime(frm.qa_end_time, frm.qa_end_timeofday, frm.RfqHeader_qaEndTime);">
									<option value="pm">P.M.</option>
									<option option="am">A.M.</option>
								</select>
								<%=HiltonUtility.getFormattedTimeZone(rfqHeader.getTimeZone())%>
								<tsa:hidden name="RfqHeader_qaEndTime" value="<%=rfqHeader.getQaEndTime()%>"/>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
				<div  id="bidEvent" style="display:none; visibility:visible">
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
					<%if (rfqHeader.getBidEvent().equals("Y")) { %>
						<tsa:input type="checkbox" name="c_bid_event" value="Y" checked="true" tabIndex="30" onclick="if (checkWebPost(this, 'Y')) { return setEventOptions(true); } else { return false; }" />
					<% } else { %>
						<tsa:input type="checkbox" name="c_bid_event" value="Y" tabIndex="30" onclick="if (checkWebPost(this, 'Y')) { return setEventOptions(true); } else { return false; }" />
					<% } %>
						<tsa:hidden name="RfqHeader_bidEvent" value="<%=rfqHeader.getBidEvent()%>"/>
					</td>
					<td nowrap><tsa:label labelName="rfq-Opening-Bid-Event" defaultString="Opening Bid Event" /></td>
				</tr>
				<tr id="bidEventTime">
					<td colspan=2>&nbsp;</td>
					<td>
						<table border=0 cellpadding=1 cellspacing=0>
						<tr>
							<td align=right nowrap><tsa:label labelName="rfq-Start_Date" defaultString="Start Date" />:</td>
							<td>
								<tsa:input type="text" name="RfqHeader_bidStartDate" tabIndex="5" size="12" maxLength="10" value="<%=HiltonUtility.getFormattedDate(rfqHeader.getBidStartDate(), oid, userDateFormat)%>" onchange="controlStartDate(this);" />
								<a href="javascript: show_calendar('RfqHeader_bidStartDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
							</td>
						</tr>
						<tr>
							<td align=right><tsa:label labelName="rfq-Start-Time" defaultString="Start Time" />:</td>
							<td>
								<tsa:input type="text" name="bid_start_time" size="6" maxLength="5" value="<%=rfqHeader.getBidStartTime()%>" onchange="setTime(frm.bid_start_time, frm.bid_start_timeofday, frm.RfqHeader_bidStartTime);" />
								<select name="bid_start_timeofday" onchange="setTime(frm.bid_start_time, frm.bid_start_timeofday, frm.RfqHeader_bidStartTime);">
									<option value="pm">P.M.</option>
									<option option="am">A.M.</option>
								</select>
								<%=HiltonUtility.getFormattedTimeZone(rfqHeader.getTimeZone())%>
								<tsa:hidden name="RfqHeader_bidStartTime" value="<%=rfqHeader.getBidStartTime()%>"/>
							</td>
						</tr>
						<tr>
							<td align=right nowrap><tsa:label labelName="rfq-End_Date" defaultString="End Date" />:</td>
							<td>
								<tsa:input type="text" name="RfqHeader_bidEndDate" tabIndex="5" size="12" maxLength="10" value="<%=HiltonUtility.getFormattedDate(rfqHeader.getBidEndDate(), oid, userDateFormat)%>" onchange="controlEndDate(this, frm.RfqHeader_bidStartDate);" />
								<a href="javascript: show_calendar('RfqHeader_bidEndDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
							</td>
						</tr>
						<tr>
							<td align=right><tsa:label labelName="rfq-End-Time" defaultString="End Time" />:</td>
							<td>
								<tsa:input type="text" name="bid_end_time" size="6" maxLength="5" value="<%=rfqHeader.getBidEndTime()%>" onchange="setTime(frm.bid_end_time, frm.bid_end_timeofday, frm.RfqHeader_bidEndTime);;" />
								<select name="bid_end_timeofday" onchange="setTime(frm.bid_end_time, frm.bid_end_timeofday, frm.RfqHeader_bidEndTime);">
									<option value="pm">P.M.</option>
									<option option="am">A.M.</option>
								</select>
								<%=HiltonUtility.getFormattedTimeZone(rfqHeader.getTimeZone())%>
								<tsa:hidden name="RfqHeader_bidEndTime" value="<%=rfqHeader.getBidEndTime()%>"/>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
				<div  id="openAuctionEvent" style="display:none; visibility:visible">
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
					<%if (rfqHeader.getAuctionEvent().equals("Y")) { %>
						<tsa:input type="checkbox" name="c_auction_event" value="Y" checked="true" tabIndex="32" onclick="if (checkWebPost(this, 'Y')) { return setEventOptions(true); } else { return false; }" />
					<% } else { %>
						<tsa:input type="checkbox" name="c_auction_event" value="Y" tabIndex="32" onclick="if (checkWebPost(this, 'Y')) { return setEventOptions(true); } else { return false; }" />
					<% } %>
						<tsa:hidden name="RfqHeader_auctionEvent" value="<%=rfqHeader.getAuctionEvent()%>"/>
					</td>
					<td nowrap><tsa:label labelName="rfq-Open-Auction-Event" defaultString="Open Auction Event" /></td>
				</tr>
				<tr id="auctionEventTime">
					<td colspan=2>&nbsp;</td>
					<td>
						<table border=0 cellpadding=1 cellspacing=0>
						<tr>
							<td align=right nowrap><tsa:label labelName="rfq-Start_Date" defaultString="Start Date" />:</td>
							<td>
								<tsa:input type="text" name="RfqHeader_auctionStartDate" tabIndex="5" size="12" maxLength="10" value="<%=HiltonUtility.getFormattedDate(rfqHeader.getAuctionStartDate(), oid, userDateFormat)%>" onchange="controlStartDate(this);" />
								<a href="javascript: show_calendar('RfqHeader_auctionStartDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
							</td>
						</tr>
						<tr>
							<td align=right><tsa:label labelName="rfq-Start-Time" defaultString="Start Time" />:</td>
							<td>
								<tsa:input type="text" name="auction_start_time" size="6" maxLength="5" value="<%=rfqHeader.getAuctionStartTime()%>" onchange="setTime(frm.auction_start_time, frm.auction_start_timeofday, frm.RfqHeader_auctionStartTime);" />
								<select name="auction_start_timeofday" onchange="setTime(frm.auction_start_time, frm.auction_start_timeofday, frm.RfqHeader_auctionStartTime);">
									<option value="pm">P.M.</option>
									<option option="am">A.M.</option>
								</select>
								<%=HiltonUtility.getFormattedTimeZone(rfqHeader.getTimeZone())%>
								<tsa:hidden name="RfqHeader_auctionStartTime" value="<%=rfqHeader.getAuctionStartTime()%>"/>
							</td>
						</tr>
						<tr>
							<td align=right nowrap><tsa:label labelName="rfq-End_Date" defaultString="End Date" />:</td>
							<td>
								<tsa:input type="text" name="RfqHeader_auctionEndDate" tabIndex="5" size="12" maxLength="10" value="<%=HiltonUtility.getFormattedDate(rfqHeader.getAuctionEndDate(), oid, userDateFormat)%>" onchange="controlEndDate(this, frm.RfqHeader_auctionStartDate);" />
								<a href="javascript: show_calendar('RfqHeader_auctionEndDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
							</td>
						</tr>
						<tr>
							<td align=right><tsa:label labelName="rfq-End-Time" defaultString="End Time" />:</td>
							<td>
								<tsa:input type="text" name="auction_end_time" size="6" maxLength="5" value="<%=rfqHeader.getAuctionEndTime()%>" onchange="setTime(frm.auction_end_time, frm.auction_end_timeofday, frm.RfqHeader_auctionEndTime);" />
								<select name="auction_end_timeofday" onchange="setTime(frm.auction_end_time, frm.auction_end_timeofday, frm.RfqHeader_auctionEndTime);">
									<option value="pm">P.M.</option>
									<option option="am">A.M.</option>
								</select>
								<%=HiltonUtility.getFormattedTimeZone(rfqHeader.getTimeZone())%>
								<tsa:hidden name="RfqHeader_auctionEndTime" value="<%=rfqHeader.getAuctionEndTime()%>"/>
							</td>
						</tr>
						</table>
						</div>
					</td>
				</tr>
				</table>
				<br>
				</div>

				<div  id="auctionSettings" style="display:none; visibility:visible">
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td colspan=3 valign=bottom nowrap><b><tsa:label labelName="rfq-Auction-Settings" defaultString="Auction Settings" />:</b></td>
				</tr>
				<tr id="extendMinutes">
					<td width=10px>&nbsp;</td>
					<td><tsa:input type="text" name="RfqHeader_extendMinutes" value="<%=rfqHeader.getExtendMinutes()%>" size="3" onchange="nfilter(this);" style="text-align:right" /></td>
					<td valign=middle nowrap><tsa:label labelName="rfq-Minute-Auto-Extension" defaultString="Minute Auto. Extension" /></td>
				</tr>
				<!--tr><td colspan=3>If a bid is submit within X minutes of the auction closing, the auction will automatically be extended by X minutes.</td></tr-->
				<tr id="bidVariance">
					<td width=10px>&nbsp;</td>
					<td><tsa:input type="text" name="RfqHeader_bidVariance" value="<%=rfqHeader.getBidVariance()%>" size="3" onchange="nfilter(this);" style="text-align:right" /></td>
					<td valign=middle nowrap>% Bid Variance</td>
				</tr>
				<tr id="bidDecrement">
					<td width=10px>&nbsp;</td>
					<td><tsa:input type="text" name="RfqHeader_bidDecrement" value="5" size="3" onchange="nfilter(this);" style="text-align:right" /></td>
					<td valign=middle nowrap>% Bid Decrement</td>
				</tr>
				</table>
				<br>
				</div>

				<div id="postAuctionOptions" style="display:none; visibility:visible">
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td colspan=3 valign=bottom nowrap><b><tsa:label labelName="msg-Post-Auction-Options" defaultString="Post Auction Display Options" />:</b></td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
					<%if (rfqHeader.getCaIndicateLowest().equals("Y")) { %>
						<tsa:input type="checkbox" name="c_ca_indicate_lowest" value="Y" checked="true" tabIndex="30" onclick="if (checkWebPost(this, 'Y')) { return setPostAuctionOptions(); } else { return false; }" />
					<% } else { %>
						<tsa:input type="checkbox" name="c_ca_indicate_lowest" value="Y" tabIndex="30" onclick="if (checkWebPost(this, 'Y')) { return setPostAuctionOptions(); } else { return false; }" />
					<% } %>
						<tsa:hidden name="RfqHeader_caIndicateLowest" value="<%=rfqHeader.getCaIndicateLowest()%>"/>
					</td>
					<td nowrap>Lowest Bid Notification</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
					<%if (rfqHeader.getCaLowestAmount().equals("Y")) { %>
						<tsa:input type="checkbox" name="c_ca_lowest_amount" value="Y" checked="true" tabIndex="30" onclick="if (checkWebPost(this, 'Y')) { return setPostAuctionOptions(); } else { return false; }" />
					<% } else { %>
						<tsa:input type="checkbox" name="c_ca_lowest_amount" value="Y" tabIndex="30" onclick="if (checkWebPost(this, 'Y')) { return setPostAuctionOptions(); } else { return false; }" />
					<% } %>
						<tsa:hidden name="RfqHeader_caLowestAmount" value="<%=rfqHeader.getCaLowestAmount()%>"/>
					</td>
					<td nowrap>Lowest Bid Dollar Amount</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
					<%if (rfqHeader.getCaWinningVendor().equals("Y")) { %>
						<tsa:input type="checkbox" name="c_ca_winning_vendor" value="Y" checked="true" tabIndex="32" onclick="if (checkWebPost(this, 'Y')) { return setPostAuctionOptions(); } else { return false; }" />
					<% } else { %>
						<tsa:input type="checkbox" name="c_ca_winning_vendor" value="Y" tabIndex="32" onclick="if (checkWebPost(this, 'Y')) { return setPostAuctionOptions(); } else { return false; }" />
					<% } %>
						<tsa:hidden name="RfqHeader_caWinningVendor" value="<%=rfqHeader.getCaWinningVendor()%>"/>
					</td>
					<td nowrap>Winning Supplier</td>
				</tr>
				</table>
				</div>
			</td>
		</tr>

		</table>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
	<td align=right valign=top><%@ include file="/rfq/rfq_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>

<%	if (s_view.equals("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (editMode) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqHeaderUpdateById;RfqRetrieve'); void(0);"><tsa:label labelName="rfq-Save" defaultString="Save" /></a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqRetrieve'); void(0);"><tsa:label labelName="rfq-Return" defaultString="Return" /></a></div></td>
<%	} else {%>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqRetrieve'); void(0);"><tsa:label labelName="rfq-Return" defaultString="Return" /></a></div></td>
<%	}%>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var rfqnumber = "<%=s_rfqNumber%>";
	var fiscalyear = "<%=rfqHeader.getFiscalYear()%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var webpost = "<%=s_webpost%>";
	var extRfqsActive = <%=extRfqsActive%>;

	function thisLoad()
	{
		f_StartIt();
		editWebPost(true);

		if (extRfqsActive)
		{
<%	if (editMode) { %>
			var qaEvent = frm.c_qa_event.checked;
			var bidEvent = frm.c_bid_event.checked;
			var auctionEvent = frm.c_auction_event.checked;

			if (qaEvent) {
				controlStartDate(frm.RfqHeader_qaStartDate);
				controlEndDate(frm.RfqHeader_qaEndDate, frm.RfqHeader_qaStartDate);
			}
			if (bidEvent) {
				controlStartDate(frm.RfqHeader_bidStartDate);
				controlEndDate(frm.RfqHeader_bidEndDate, frm.RfqHeader_bidStartDate);
			}
			if (auctionEvent) {
				controlStartDate(frm.RfqHeader_auctionStartDate);
				controlEndDate(frm.RfqHeader_auctionEndDate, frm.RfqHeader_auctionStartDate);
			}
<%	}%>
			setTimeDisplay(frm.qa_start_time, frm.qa_start_timeofday);
			setTimeDisplay(frm.qa_end_time, frm.qa_end_timeofday);
			setTimeDisplay(frm.bid_start_time, frm.bid_start_timeofday);
			setTimeDisplay(frm.bid_end_time, frm.bid_end_timeofday);
			setTimeDisplay(frm.auction_start_time, frm.auction_start_timeofday);
			setTimeDisplay(frm.auction_end_time, frm.auction_end_timeofday);
			setEventOptions(false);
		}

<%	if (!editMode) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function setBidAccess(value)
	{
		frm.RfqHeader_bidAccess.value = value;
	}

	function setAuctionType(value)
	{
		frm.RfqHeader_auctionType.value = value;
	}

	function setBidItemOptions(value)
	{
		if (frm.c_bid_item_options.checked==true)
		{
			frm.RfqHeader_bidItemOptions.value = "A";
		}
		else
		{
			frm.RfqHeader_bidItemOptions.value = "N";
		}
	}

	function setLowBidReq(value)
	{
		if (frm.c_lowest_bid_req.checked==true)
		{
			frm.RfqHeader_lowestBidReq.value = "Y";
		}
		else
		{
			frm.RfqHeader_lowestBidReq.value = "N";
		}
	}

	function setProxySetting(value)
	{
		if (frm.c_proxy_setting.checked==true)
		{
			frm.RfqHeader_allowProxyBids.value = "Y";
		}
		else
		{
			frm.RfqHeader_allowProxyBids.value = "N";
		}
	}

	function setLowBidSource(value)
	{
		frm.RfqHeader_lowestBidSource.value = value;
	}

	function setLowestDisplay(value)
	{
		frm.RfqHeader_lowestDisplay.value = value;
	}

	function setAuctionOptions(resetDisplay)
	{
		var openAuction = frm.rfh_auction_type[1].checked;

		if (extRfqsActive) {
			openAuction = frm.c_auction_event.checked;
		}

		if (openAuction)
		{
<%		if (editMode) { %>
				if (!frm.rfh_lowest_bid_source[0].checked && !frm.rfh_lowest_bid_source[1].checked)
				{
					frm.rfh_lowest_bid_source[0].checked = true;
				}
				if (!frm.rfh_lowest_display[0].checked && !frm.rfh_lowest_display[1].checked)
				{
					frm.rfh_lowest_display[0].checked = true;
					setLowestDisplay(frm.rfh_lowest_display[0].value);
				}
				if (frm.rfh_lowest_bid_source[1].checked)
				{
					frm.c_bid_item_options.checked = true;
					frm.RfqHeader_bidItemOptions.value = "A";
				}
<%		} %>
		}
		else
		{
			frm.rfh_lowest_bid_source[0].checked = false;
			frm.rfh_lowest_bid_source[1].checked = false;
			frm.c_lowest_bid_req.checked = false;
			frm.c_proxy_setting.checked = false;
			frm.c_lowest_bid_req.value = "N";
			frm.rfh_lowest_display[0].checked = false;
			frm.rfh_lowest_display[1].checked = false;
			setLowestDisplay("");
		}

		setEventOptions(resetDisplay);

		if (resetDisplay) {
			setDisplayOptions();
		}
		return openAuction;
	}

<%if (editMode) { %>
		function editWebPost(resetDisplay)
		{
			var x = "";
			var post = frm.c_webpost[0].checked;
			var bid = frm.c_webpost[1].checked;
			var download = frm.c_webpost[2].checked;
			var openAuction = frm.rfh_auction_type[1].checked;

			if (extRfqsActive) {
				openAuction = frm.c_auction_event.checked;
			}

			if ( !post )
			{
				x = "N";
				resetOptions("ALL");
			}
			else
			{
				if ( !bid )
				{
					x = "";
					if ( download )
					{
						x = "D";
					}
					else
					{
						x = "V";
					}
				}
				else
				{
					if ( download && bid )
					{
						x = "DB";
					}
					else if ( bid )
					{
						x = "VB";
					}
					else if ( download )
					{
						x = "D";
					}
					else
					{
						x = "V";
					}

					if ( webpost == "N" || webpost == "V")
					{
						frm.rfh_bid_access[0].checked = true;
						setBidAccess(frm.rfh_bid_access[0].value);
						frm.rfh_auction_type[0].checked = true;
						setAuctionType(frm.rfh_auction_type[0].value);
						frm.c_bid_item_options.checked = false;
						setBidItemOptions("N");
					}
				}
				if ( webpost == "N" )
				{
					frm.rfh_bid_access[0].checked = true;
					setBidAccess(frm.rfh_bid_access[0].value);
				}
				setAuctionOptions(resetDisplay);
			}

			webpost = x;
			frm.RfqHeader_webpost.value = x;

			if (resetDisplay) {
				setDisplayOptions();
			}

			return post;
		}
<%	}
	else
	{ %>
		function editWebPost(resetDisplay)
		{
			if (resetDisplay) {
				setDisplayOptions();
			}

			return false;
		}
<%	} %>


	function resetOptions (fld)
	{
		if (fld == "ALL")
		{
			frm.c_webpost[1].checked = false;
			frm.c_webpost[2].checked = false;

			frm.rfh_bid_access[0].checked = false;
			frm.rfh_bid_access[1].checked = false;
			frm.rfh_bid_access[2].checked = false;
			setBidAccess("");
			frm.rfh_auction_type[0].checked = false;
			frm.rfh_auction_type[1].checked = false;
			setAuctionType("");
			frm.c_qa_event.checked = false;
			frm.c_bid_event.checked = false;
			frm.c_auction_event.checked = false;
			setEventOptions(false);
			frm.c_bid_item_options.checked = false;
			setBidItemOptions("");

			frm.rfh_lowest_bid_source[0].checked = false;
			frm.rfh_lowest_bid_source[1].checked = false;
			frm.c_lowest_bid_req.checked = false;
			frm.c_proxy_setting.checked = false;
			frm.RfqHeader_lowestBidReq.value = "N";
			frm.RfqHeader_allowProxyBids.value = "N";
			frm.rfh_lowest_display[0].checked = false;
			frm.rfh_lowest_display[1].checked = false;
			setLowestDisplay("");
		}
		else if (fld == "bid_access" )
		{
			frm.rfh_bid_access[0].checked = false;
			frm.rfh_bid_access[1].checked = false;
			frm.rfh_bid_access[2].checked = false;
			setBidAccess("");
<%	if ( rfqHeader.getBidAccess().equals("U") ) {%>
			frm.rfh_bid_access[0].checked = true;
			setBidAccess(frm.rfh_bid_access[0].value);
<%	}
	else if ( rfqHeader.getBidAccess().equals("I") ) {%>
			frm.rfh_bid_access[1].checked = true;
			setBidAccess(frm.rfh_bid_access[1].value);
<%	}
	else if ( rfqHeader.getBidAccess().equals("R") ) {%>
			frm.rfh_bid_access[2].checked = true;
			setBidAccess(frm.rfh_bid_access[2].value);
<%	}%>
		}
		else if (fld == "auction_type")
		{
			frm.rfh_auction_type[0].checked = false;
			frm.rfh_auction_type[1].checked = false;
			setAuctionType("");
<%	if ( rfqHeader.getAuctionType().equals("S") ) {%>
			frm.rfh_auction_type[0].checked = true;
			setAuctionType(frm.rfh_auction_type[0].value);
<%	}
	else if ( rfqHeader.getAuctionType().equals("O") ) {%>
			frm.rfh_auction_type[1].checked = true;
			setAuctionType(frm.rfh_auction_type[1].value);
<%	}%>
		}
		else if (fld == "bid_item_options")
		{
			if (i_bidItem >= 0)
			{
				frm.c_bid_item_options.checked = true;
				frm.RfqHeader_bidItemOptions.value = "N";
			}
			else
			{
				frm.c_bid_item_options.checked = false;
				setBidItemOptions("");
			}
		}
	}

<%	if (!editMode) { %>
			function checkWebPost(fld,x)
			{
				resetOptions(fld.name.substring(4, fld.name.length));
				return false;
			}
<%	}
    	else { %>
			function checkWebPost(fld, x)
			{
				if (webpost == "N")
				{
					if (x == "Y")
					{
						alert("This is a Non Web Solicitation!");
					}

					if (browserCheck() != "NS")
					{
						resetOptions("ALL");
					}
					return false;
				}
				else if ( (fld.name.substring(4, 10) == "lowest") && (frm.rfh_auction_type[1].checked == false && frm.RfqHeader_auctionEvent.value != "Y") )
				{
					alert("This is not an Open Auction Solicitation!");
					return false;
				}
				else
				{
					return true;
				}
			}
<%   }%>

<%	if (!editMode)	{%>
			function setBidReq()
			{
				return false;
			}
<%	}
		else { %>
			function setBidReq()
			{
				if ( frm.c_bid_item_options.checked )
				{
					frm.RfqHeader_bidItemOptions.value = "A";
				}
				else if (frm.rfh_lowest_bid_source[1].checked)
				{
					alert("Bids are required for all items if the lowest bid is based on the subtotal amount.");
					frm.c_bid_item_options.checked = true;
					frm.RfqHeader_bidItemOptions.value = "A";
				}
				else
				{
					frm.RfqHeader_bidItemOptions.value = "N";
				}

				if ( frm.c_lowest_bid_req.checked ) {
					frm.RfqHeader_lowestBidReq.value = "Y";
				}
				else {
					frm.RfqHeader_lowestBidReq.value = "N";
				}

				if ( frm.c_proxy_setting.checked ) {
					frm.RfqHeader_allowProxyBids.value = "Y";
				}
				else {
					frm.RfqHeader_allowProxyBids.value = "N";
				}

				return true;
			}
<%   } %>

	function setPostAuctionOptions() {
		if ( frm.c_ca_indicate_lowest.checked ) {
			frm.RfqHeader_caIndicateLowest.value = "Y";
		}
		else {
			frm.RfqHeader_caIndicateLowest.value = "N";
		}
		if ( frm.c_ca_lowest_amount.checked ) {
			frm.RfqHeader_caLowestAmount.value = "Y";
		}
		else {
			frm.RfqHeader_caLowestAmount.value = "N";
		}
		if ( frm.c_ca_winning_vendor.checked ) {
			frm.RfqHeader_caWinningVendor.value = "Y";
		}
		else {
			frm.RfqHeader_caWinningVendor.value = "N";
		}
		return true;
	}

	function setEventOptions(resetDisplay) {
		var post  = frm.c_webpost[0].checked;
		var onlineBidding = frm.c_webpost[1].checked;
		var qaEvent = frm.c_qa_event.checked;
		var bidEvent = frm.c_bid_event.checked;
		var auctionEvent = frm.c_auction_event.checked;

		if ( post && qaEvent ) {
			frm.RfqHeader_qaEvent.value = "Y";
		} else {
			frm.c_qa_event.checked = false;
			frm.RfqHeader_qaEvent.value = "N";
		}
		if ( post && onlineBidding && bidEvent ) {
			frm.RfqHeader_bidEvent.value = "Y";
		} else {
			frm.c_bid_event.checked = false;
			frm.RfqHeader_bidEvent.value = "N";
		}
		if ( post && onlineBidding && auctionEvent ) {
			frm.RfqHeader_auctionEvent.value = "Y";
		} else {
			frm.c_auction_event.checked = false;
			frm.RfqHeader_auctionEvent.value = "N";
		}

		if (resetDisplay) {
			setDisplayOptions();
		}
	}

	function setTime(timeFld, timeofdayFld, fld) {
		var time = timeFld.value;
		var timeofday = timeofdayFld.options[timeofdayFld.selectedIndex].value

		if ( time.indexOf(":") <= 0 ) {
			if (time.length <= 2) {
				if (time.length > 0) {
					time = time + ":00";
				}
				else {
					timeFld.value = "";
					fld.value = "";
					return;
				}
			}
			else {
				alert("This is not a valid time!");
				timeFld.value = "";
				timeFld.focus();
				return;
			}
		}

		var hour = time.substring(0,time.indexOf(":"));
		var min	 = time.substring(time.indexOf(":") + 1, time.length);

		if (hour > 12) {
			alert("This is not a valid time!");
			timeFld.value = "";
			timeFld.focus();
			return;
		}
		if (min > 59) {
			alert("This is not a valid time!");
			timeFld.value = "";
			timeFld.focus();
			return;
		}

		timeFld.value = time;

		if (timeofday == "pm") {
			hour = eval(hour);
			if (hour < 12) {
				hour = eval(hour + 12);
			}
			time = hour + ":" + min;
		}
		else {
			hour = eval(hour);
			if (hour == 12) {
				hour = eval(00);
			}
			if (String(hour).length == 1) {
				time = "0" + hour + ":" + min;
			}
			else {
				time = hour + ":" + min;
			}
		}
		fld.value = time;
	}


	function setTimeDisplay(timeFld, timeofdayFld)
	{
		var time = timeFld.value;
		var hour = time.substring(0,time.indexOf(":"));
		var min	 = time.substring(time.indexOf(":") + 1, time.length);

		if (time.length == 0 || time == "") {
			timeofdayFld.selectedIndex = 0;
			timeFld.value = "";
			return;
		}
		hour = eval(hour);

		if (hour > 12) {
			timeofdayFld.selectedIndex = 0;
			hour = eval(hour - 12);
		}
		else if (hour == 12) {
			timeofdayFld.selectedIndex = 0;
		}
		else {
			if (hour == 00) {
				hour = eval(hour + 12);
			}
			timeofdayFld.selectedIndex = 1;
		}

		time = hour + ":" + min;
		timeFld.value = time;
	}

	function controlStartDate(box){
		var startDate 		= new Date;
		var dayStartDate	= startDate.getDate();
		var monthStartDate	= startDate.getMonth();
		var yearStartDate	= startDate.getYear();
		var i_sDate = box;
		var d_sDate = startDate;
		var d_sDateTemp = setStrDate(i_sDate,0,0,0);
		if (!checkDate(i_sDate) || i_sDate == "" || d_sDateTemp <= startDate){
			//frm.RfqHeader_qaStartDate.value = set2str(monthStartDate+1) + "-" + set2str(dayStartDate) + "-" + yearStartDate;
			box.value = set2str(monthStartDate+1) + "-" + set2str(dayStartDate) + "-" + yearStartDate;
		}
	}

	function controlEndDate(box, startFld) {
		var endDate 		= new Date;
		var dayEndDate	= endDate.getDate();
		var monthEndDate	= endDate.getMonth();
		var yearEndDate	= endDate.getYear();
		var i_eDate = box;
		var d_eDate = endDate;
		var d_eDateTemp = setStrDate(i_eDate,0,0,0);
		if (!checkDate(i_eDate) || i_eDate == "" || box.value <= startFld.value) {
			box.value = startFld.value;
		}
	}

	function set2str(i) {
		if (i<10) i="0"+i;
		return i
	}

	function setStrDate(date,dayLater,monthLater,yearLater) {
		var dtDate = new Date;
		parseDate(date.value);
		dtDate.setYear(eval(strYear)+eval(yearLater));
		dtDate.setMonth(eval(strMonth)-1+eval(monthLater));
		dtDate.setDate(eval(strDay)+eval(dayLater));
		return dtDate;
	}

	function setDisplayOptions() {
		<% if (oid.equalsIgnoreCase("MSG07P")) { %>
		var post  = true;
		var onlineBidding = false;
		var openAuction = false;
		var qaEvent = false;
		var bidEvent = false;
		var auctionEvent = false;
		<% } else { %>
		var post  = frm.c_webpost[0].checked;
		var onlineBidding = frm.c_webpost[1].checked;
		var openAuction = frm.rfh_auction_type[1].checked;
		var qaEvent = frm.c_qa_event.checked;
		var bidEvent = frm.c_bid_event.checked;
		var auctionEvent = frm.c_auction_event.checked;
		<% } %>

		if (extRfqsActive) {
			hideArea("bidType");
			openAuction = auctionEvent;
		} else {
			hideArea("proxySetting");
			hideArea("auctionEvents");
		}

		if (post) {
			displayArea("postOption1");
			displayArea("postOption2");
			displayArea("lowestBids");
			displayArea("supplierAccess");
			displayArea("postAuctionOptions");

			if (extRfqsActive) {
				displayArea("auctionEvents");
			} else {
				displayArea("bidType");
			}

			if (onlineBidding) {
				displayArea("bidRequirements");
				displayArea("bidEvent");
				displayArea("openAuctionEvent");

				if (openAuction) {
					displayArea("lowestRequirement");
					displayArea("lowestDisplay");

					if (extRfqsActive) {
						displayArea("proxySetting");
						displayArea("auctionEventTime");
						displayArea("auctionSettings");
						displayArea("extendMinutes");
						displayArea("bidVariance");
						displayArea("bidDecrement");
					}
				} else {
					hideArea("lowestBids");
					hideArea("lowestRequirement");
					hideArea("proxySetting");
					hideArea("lowestDisplay");
					hideArea("auctionEventTime");
					hideArea("auctionSettings");
					hideArea("extendMinutes");
					hideArea("bidVariance");
					hideArea("bidDecrement");
				}
				if (qaEvent) {
					displayArea("qaEventTime");
				} else {
					hideArea("qaEventTime");
				}
				if (bidEvent) {
					displayArea("bidEventTime");
				} else {
					hideArea("bidEventTime");
				}
			} else {
				hideArea("bidType");
				hideArea("lowestBids");
				hideArea("bidRequirements");
				hideArea("proxySetting");
				hideArea("lowestRequirement");
				hideArea("proxySetting");
				hideArea("lowestDisplay");
				hideArea("bidEvent");
				hideArea("bidEventTime");
				hideArea("openAuctionEvent");
				hideArea("auctionEventTime");
				hideArea("auctionSettings");
				hideArea("extendMinutes");
				hideArea("bidVariance");
				hideArea("bidDecrement");

				if (qaEvent) {
					displayArea("qaEventTime");
				} else {
					hideArea("qaEventTime");
				}
			}
		} else {
			hideArea("postOption1");
			hideArea("postOption2");
			hideArea("bidType");
			hideArea("lowestBids");
			hideArea("supplierAccess");
			hideArea("bidRequirements");
			hideArea("lowestRequirement");
			hideArea("proxySetting");
			hideArea("lowestDisplay");
			hideArea("auctionEvents");
			hideArea("auctionEventTime");
			hideArea("qaEventTime");
			hideArea("bidEvent");
			hideArea("bidEventTime");
			hideArea("openAuctionEvent");
			hideArea("auctionEventTime");
			hideArea("auctionSettings");
			hideArea("extendMinutes");
			hideArea("bidVariance");
			hideArea("bidDecrement");
			hideArea("postAuctionOptions");
		}
	}

	function validateForm() {
		if (frm.handler.value.indexOf(currentmethod) >= 0) {
			var alertMessage = "";
			var qaEvent = frm.c_qa_event.checked;
			var bidEvent = frm.c_bid_event.checked;
			var auctionEvent = frm.c_auction_event.checked;
			var qaStartTime = new Date(frm.RfqHeader_qaStartDate.value + " " + frm.RfqHeader_qaStartTime.value);
			var qaEndTime = new Date(frm.RfqHeader_qaEndDate.value + " " + frm.RfqHeader_qaEndTime.value);
			var bidStartTime =  new Date(frm.RfqHeader_bidStartDate.value + " " + frm.RfqHeader_bidStartTime.value);
			var bidEndTime =  new Date(frm.RfqHeader_bidEndDate.value + " " + frm.RfqHeader_bidEndTime.value);
			var auctionStartTime = new Date(frm.RfqHeader_auctionStartDate.value + " " + frm.RfqHeader_auctionStartTime.value);
			var auctionEndTime = new Date(frm.RfqHeader_auctionEndDate.value + " " + frm.RfqHeader_auctionEndTime.value);

			if (qaEvent) {
				if (qaEndTime <= qaStartTime) {
					//alertMessage += 'The start date and time for the Question & Answer Event must be before the end dateand time for the event.\n';
					alertMessage += 'On the Question & Answer Event, the Start Date/Time must come before the End Date/Time.\n';
				}
			}
			if (bidEvent) {
				if (bidEndTime <= bidStartTime) {
					alertMessage += 'The start date and time for the Opening Bid Event must be before the end date and time for the event.\n';
				}
			}
			if (auctionEvent) {
				if (auctionEndTime <= auctionStartTime) {
					//alertMessage += 'The start date and time for the Auction Event must be before the end date and time for the event.\n';
					alertMessage += 'On the Auction Event, the Start Date/Time must come before the End Date/Time.\n';
				}
			}
			if (qaEvent && bidEvent && bidStartTime < qaEndTime) {
				alertMessage += 'The Question & Answer Event must be before the Opening Bid Event.\n';
			}
			if (qaEvent && auctionEvent && auctionStartTime < qaEndTime) {
				alertMessage += 'The Question & Answer Event must be before the Auction Event.\n';
			}
			if (bidEvent && auctionEvent && auctionStartTime < bidEndTime) {
				alertMessage += 'The Opening Bid Event must be before the Auction Event.\n';
			}
			if (alertMessage.length > 0) {
		    	alert ( 'Please fix the following problems:\n\n'+alertMessage );
		    	return false;
			}

			if (auctionEvent ) {
				frm.RfqHeader_dueDate.value = frm.RfqHeader_auctionEndDate.value;
				frm.RfqHeader_bidDueTime.value = frm.RfqHeader_auctionEndTime.value;
			} else if (bidEvent) {
				frm.RfqHeader_dueDate.value = frm.RfqHeader_bidEndDate.value;
				frm.RfqHeader_bidDueTime.value = frm.RfqHeader_bidEndTime.value;
			} else if (qaEvent) {
				frm.RfqHeader_dueDate.value = frm.RfqHeader_qaEndDate.value;
				frm.RfqHeader_bidDueTime.value = frm.RfqHeader_qaEndTime.value;
			}
		}
		return true;
	}

// End Hide script -->
</SCRIPT>