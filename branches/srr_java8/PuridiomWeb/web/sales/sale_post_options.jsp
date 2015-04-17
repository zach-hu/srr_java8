<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	SaleHeader saleHeader = (SaleHeader) request.getAttribute("saleHeader");
	int i;

	String s_icSaleHeader = saleHeader.getIcSaleHeader().toString();
	String s_status = saleHeader.getStatus();
	String s_saleNumber = saleHeader.getSaleNumber();
	String s_amendment = saleHeader.getAmendment();
	String s_fiscalYear = saleHeader.getFiscalYear();

	//Web Post Options
	String s_webpost = saleHeader.getWebpost();
	String s_bidAccess = saleHeader.getBidAccess();
	String s_auctionType = saleHeader.getAuctionType();
	String s_lowBidReq = saleHeader.getHighestBidReq();
	String s_lowBidSource = saleHeader.getHighestBidSource();
	String s_highestDisplay = saleHeader.getHighestDisplay();

	boolean	b_post = false;

	s_webpost = "VBD";
	if ( (s_webpost.indexOf("V") >= 0) || (s_webpost.indexOf("D") >= 0) || (s_webpost.indexOf("P") >= 0) )
	{
		b_post = true;
	}

	String	s_current_process = "WEBPOST_OPTIONS";
	String	s_current_page = "/sales/sale_post_options.jsp";
	String	s_current_method = "SaleHeaderUpdateById";
	String	s_current_process_method = "";
%>
<%@ include file="/sales/sale_hidden_fields.jsp" %>

<br>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width=1px height=1px /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Post Options</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height=31px /></td>
	<td valign=bottom align=right height=30px>
		<%@ include file="/sales/sale_status_title.jsp" %>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top>
		<table border=0 cellpadding=0 cellspacing=0>
		<tr>
			<td width=5px>&nbsp;</td>
			<td valign=top width=250px height=100%>
				<div id="postOption1"></div>
				<div id="postOption2"></div>
				<tsa:hidden name="SaleHeader_webpost" value="<%=s_webpost%>"/>
				<div  id="bidType">
				<table border=0 valign=bottom cellpadding=1 cellspacing=0>
				<tr>
					<td colspan=3 valign=bottom nowrap><b>Bid Type:</b>
						<tsa:hidden name="SaleHeader_auctionType" value="<%=s_auctionType%>"/>
					</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
						<input type=radio name="rfh_auction_type" value="S" tabindex="36" onclick="if (checkWebPost(this, 'Y')) { setAuctionType('S'); setAuctionOptions(); }" <% if (s_auctionType.indexOf("S") == 0) { %>CHECKED<% } %>>
					</td>
					<td nowrap>Sealed Bid</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
						<input type=radio name="rfh_auction_type" value="O" tabindex="36" onclick="if (checkWebPost(this, 'Y')) { setAuctionType('O'); setAuctionOptions(); }" <% if (s_auctionType.indexOf("O") == 0) { %>CHECKED<% } %>>
					</td>
					<td nowrap>Open Auction</td>
				</tr>
				</table>
				<br>
				</div>

				<div  id="highestBids">
				<tsa:hidden name="SaleHeader_highestBidSource" value="<%=s_lowBidSource%>"/>
				</div>

				<div  id="supplierAccess">
						<tsa:hidden name="SaleHeader_bidAccess" value="<%=s_bidAccess%>"/>
				</div>
				<div id="highestRequirement"></div>
				<div  id="bidRequirements">
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td colspan=3 valign=bottom nowrap><b>Bid Requirements:</b></td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td nowrap>Reserve Amount</td>
					<td align=right>
						<input type=text name="SaleHeader_reserve" value="">
					</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td valign=middle>Bid Increment</td>
					<td align=right valign=top>
						<input type=text name="SaleHeader_bidIncrement" value="">
					</td>
				</tr>
				</table>
				<br>
				</div>

				<div  id="highestDisplay">
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td colspan=3 valign=bottom nowrap><b>Bid Display Options:</b>
						<tsa:hidden name="SaleHeader_highestDisplay" value="<%=s_highestDisplay%>"/>
					</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
						<input type=checkbox name="rfh_highest_display" value="A" <%if (s_highestDisplay.indexOf("A") == 0) { %>CHECKED<%}%> tabindex=42 onclick="if (checkWebPost(this, 'Y')) { setHighestDisplay('A'); return setAuctionOptions() } else { return false; }" >
					</td>
					<td nowrap>Display highest bid dollar amount.</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
						<input type=checkbox name="rfh_highest_display" value="N" <%if (s_highestDisplay.indexOf("N") == 0) { %>CHECKED<%}%> tabindex=43 onclick="if (checkWebPost(this, 'Y')) { setHighestDisplay('N'); return setAuctionOptions() } else { return false; }" >
					</td>
					<td nowrap>Display number of bids.</td>
				</tr>
				</table>
				</div>

				<br>

				<div  id="auctionSettings">
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td colspan=3 valign=bottom nowrap><b>Auction Settings:</b></td>
				</tr>
				<tr id="extendMinutes">
					<td width=10px>&nbsp;</td>
					<td><input type=text name="SaleHeader_extendMinutes" value="<%=saleHeader.getExtendMinutes()%>" size=3 onchange="nfilter(this);" style="text-align:right"></td>
					<td valign=middle nowrap>Minute Auto. Extension</td>
				</tr>
				<!--tr><td colspan=3>If a bid is submit within X minutes of the auction closing, the auction will automatically be extended by X minutes.</td></tr-->
				<tr id="bidVariance">
					<td width=10px>&nbsp;</td>
					<td><input type=text name="SaleHeader_bidVariance" value="<%=saleHeader.getBidVariance()%>" size=3 onchange="nfilter(this);" style="text-align:right"></td>
					<td valign=middle nowrap>% Bid Variance</td>
				</tr>
				</table>
				<br>
				</div>

				<div id="postAuctionOptions">
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td colspan=3 valign=bottom nowrap><b>Post Auction Display Options:</b></td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
						<input type=checkbox name="c_ca_indicate_highest" value="Y" <%if (saleHeader.getCaIndicateHighest().equals("Y")) { %>checked<% } %> tabindex=30 onclick="if (checkWebPost(this, 'Y')) { return setPostAuctionOptions(); } else { return false; }" >
						<tsa:hidden name="SaleHeader_caIndicateHighest" value="<%=saleHeader.getCaIndicateHighest()%>"/>
					</td>
					<td nowrap>Highest Bid Notification</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
						<input type=checkbox name="c_ca_highest_amount" value="Y" <%if (saleHeader.getCaHighestAmount().equals("Y")) { %>checked<% } %> tabindex=30 onclick="if (checkWebPost(this, 'Y')) { return setPostAuctionOptions(); } else { return false; }" >
						<tsa:hidden name="SaleHeader_caHighestAmount" value="<%=saleHeader.getCaHighestAmount()%>"/>
					</td>
					<td nowrap>Highest Bid Dollar Amount</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
						<input type=checkbox name="c_ca_winning_vendor" value="Y" <%if (saleHeader.getCaWinningBuyer().equals("Y")) { %>checked<% } %> tabindex=32 onclick="if (checkWebPost(this, 'Y')) { return setPostAuctionOptions(); } else { return false; }" >
						<tsa:hidden name="SaleHeader_caWinningVendor" value="<%=saleHeader.getCaWinningBuyer()%>"/>
					</td>
					<td nowrap>Winning Bidder</td>
				</tr>
				</table>
				</div>
			</td>
			<td valign=top height=100% valign=top>
				<div  id="auctionEvents">
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td colspan=3 valign=bottom nowrap><b>Auction Events:</b></td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
						<input type=checkbox name="c_qa_event" value="Y" <%if (saleHeader.getQaEvent().equals("Y")) { %>CHECKED<% } %> tabindex=30 onclick="if (checkWebPost(this, 'Y')) { return setEventOptions(); } else { return false; }" >
						<tsa:hidden name="SaleHeader_qaEvent" value="<%=saleHeader.getQaEvent()%>"/>
					</td>
					<td nowrap>Online Question & Answer Event</td>
				</tr>
				<tr id="qaEventTime">
					<td colspan=2>&nbsp;</td>
					<td>
						<table border=0 cellpadding=1 cellspacing=0>
						<tr>
							<td align=right nowrap>Start Date:</td>
							<td>
								<input type=text name="SaleHeader_qaStartDate" tabindex=5 size=12 maxlength=10 value="<%=HiltonUtility.getFormattedDate(saleHeader.getQaStartDate(), oid, userDateFormat)%>">
								<a href="javascript: show_calendar('SaleHeader_qaStartDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
							</td>
						</tr>
						<tr>
							<td align=right>Start Time:</td>
							<td>
								<input type=text name="qa_start_time" size=6 maxlength=5 value="<%=saleHeader.getQaStartTime()%>" onchange="setTime(frm.qa_start_time, frm.qa_start_timeofday, frm.SaleHeader_qaStartTime);">
								<select name="qa_start_timeofday" onchange="setTime(frm.qa_start_time, frm.qa_start_timeofday, frm.SaleHeader_qaStartTime);">
									<option value="pm">P.M.</option>
									<option option="am">A.M.</option>
								</select>
								<%=saleHeader.getTimeZone()%>
								<tsa:hidden name="SaleHeader_qaStartTime" value="<%=saleHeader.getQaStartTime()%>"/>
							</td>
						</tr>
						<tr>
							<td align=right nowrap>End Date:</td>
							<td>
								<input type=text name="SaleHeader_qaEndDate" tabindex=5 size=12 maxlength=10 value="<%=HiltonUtility.getFormattedDate(saleHeader.getQaEndDate(), oid, userDateFormat)%>">
								<a href="javascript: show_calendar('SaleHeader_qaEndDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
							</td>
						</tr>
						<tr>
							<td align=right>End Time:</td>
							<td>
								<input type=tex name="qa_end_time" size=6 maxlength=5 value="<%=saleHeader.getQaEndTime()%>" onchange="setTime(frm.qa_end_time, frm.qa_end_timeofday, frm.SaleHeader_qaEndTime);">
								<select name="qa_end_timeofday" onchange="setTime(frm.qa_end_time, frm.qa_end_timeofday, frm.SaleHeader_qaEndTime);">
									<option value="pm">P.M.</option>
									<option option="am">A.M.</option>
								</select>
								<%=saleHeader.getTimeZone()%>
								<tsa:hidden name="SaleHeader_qaEndTime" value="<%=saleHeader.getQaEndTime()%>"/>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
						<input type=checkbox name="c_bid_event" value="Y" <%if (saleHeader.getBidEvent().equals("Y")) { %>CHECKED<% } %> tabindex=30 onclick="if (checkWebPost(this, 'Y')) { return setEventOptions(); } else { return false; }" >
						<tsa:hidden name="SaleHeader_bidEvent" value="<%=saleHeader.getBidEvent()%>"/>
					</td>
					<td nowrap>Opening Bid Event</td>
				</tr>
				<tr id="bidEventTime">
					<td colspan=2>&nbsp;</td>
					<td>
						<table border=0 cellpadding=1 cellspacing=0>
						<tr>
							<td align=right nowrap>Start Date:</td>
							<td>
								<input type=text name="SaleHeader_bidStartDate" tabindex=5 size=12 maxlength=10 value="<%=HiltonUtility.getFormattedDate(saleHeader.getBidStartDate(), oid, userDateFormat)%>">
								<a href="javascript: show_calendar('SaleHeader_bidStartDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
							</td>
						</tr>
						<tr>
							<td align=right>Start Time:</td>
							<td>
								<input type=tex name="bid_start_time" size=6 maxlength=5 value="<%=saleHeader.getBidStartTime()%>" onchange="setTime(frm.bid_start_time, frm.bid_start_timeofday, frm.SaleHeader_bidStartTime);">
								<select name="bid_start_timeofday" onchange="setTime(frm.bid_start_time, frm.bid_start_timeofday, frm.SaleHeader_bidStartTime);">
									<option value="pm">P.M.</option>
									<option option="am">A.M.</option>
								</select>
								<%=saleHeader.getTimeZone()%>
								<tsa:hidden name="SaleHeader_bidStartTime" value="<%=saleHeader.getBidStartTime()%>"/>
							</td>
						</tr>
						<tr>
							<td align=right nowrap>End Date:</td>
							<td>
								<input type=text name="SaleHeader_bidEndDate" tabindex=5 size=12 maxlength=10 value="<%=HiltonUtility.getFormattedDate(saleHeader.getBidEndDate(), oid, userDateFormat)%>">
								<a href="javascript: show_calendar('SaleHeader_bidEndDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
							</td>
						</tr>
						<tr>
							<td align=right>End Time:</td>
							<td>
								<input type=tex name="bid_end_time" size=6 maxlength=5 value="<%=saleHeader.getBidEndTime()%>" onchange="setTime(frm.bid_end_time, frm.bid_end_timeofday, frm.SaleHeader_bidEndTime);;">
								<select name="bid_end_timeofday" onchange="setTime(frm.bid_end_time, frm.bid_end_timeofday, frm.SaleHeader_bidEndTime);">
									<option value="pm">P.M.</option>
									<option option="am">A.M.</option>
								</select>
								<%=saleHeader.getTimeZone()%>
								<tsa:hidden name="SaleHeader_bidEndTime" value="<%=saleHeader.getBidEndTime()%>"/>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width=10px>&nbsp;</td>
					<td align=right>
						<input type=checkbox name="c_auction_event" value="Y" <%if (saleHeader.getAuctionEvent().equals("Y")) { %>CHECKED<% } %> tabindex=32 onclick="if (checkWebPost(this, 'Y')) { return setEventOptions(); } else { return false; }" >
						<tsa:hidden name="SaleHeader_auctionEvent" value="<%=saleHeader.getAuctionEvent()%>"/>
					</td>
					<td nowrap>Open Auction Event</td>
				</tr>
				<tr id="auctionEventTime">
					<td colspan=2>&nbsp;</td>
					<td>
						<table border=0 cellpadding=1 cellspacing=0>
						<tr>
							<td align=right nowrap>Start Date:</td>
							<td>
								<input type=text name="SaleHeader_auctionStartDate" tabindex=5 size=12 maxlength=10 value="<%=HiltonUtility.getFormattedDate(saleHeader.getAuctionStartDate(), oid, userDateFormat)%>">
								<a href="javascript: show_calendar('SaleHeader_auctionStartDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
							</td>
						</tr>
						<tr>
							<td align=right>Start Time:</td>
							<td>
								<input type=tex name="auction_start_time" size=6 maxlength=5 value="<%=saleHeader.getAuctionStartTime()%>" onchange="setTime(frm.auction_start_time, frm.auction_start_timeofday, frm.SaleHeader_auctionStartTime);">
								<select name="auction_start_timeofday" onchange="setTime(frm.auction_start_time, frm.auction_start_timeofday, frm.SaleHeader_auctionStartTime);">
									<option value="pm">P.M.</option>
									<option option="am">A.M.</option>
								</select>
								<%=saleHeader.getTimeZone()%>
								<tsa:hidden name="SaleHeader_auctionStartTime" value="<%=saleHeader.getAuctionStartTime()%>"/>
							</td>
						</tr>
						<tr>
							<td align=right nowrap>End Date:</td>
							<td>
								<input type=text name="SaleHeader_auctionEndDate" tabindex=5 size=12 maxlength=10 value="<%=HiltonUtility.getFormattedDate(saleHeader.getAuctionEndDate(), oid, userDateFormat)%>">
								<a href="javascript: show_calendar('SaleHeader_auctionEndDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
							</td>
						</tr>
						<tr>
							<td align=right>End Time:</td>
							<td>
								<input type=tex name="auction_end_time" size=6 maxlength=5 value="<%=saleHeader.getAuctionEndTime()%>" onchange="setTime(frm.auction_end_time, frm.auction_end_timeofday, frm.SaleHeader_auctionEndTime);">
								<select name="auction_end_timeofday" onchange="setTime(frm.auction_end_time, frm.auction_end_timeofday, frm.SaleHeader_auctionEndTime);">
									<option value="pm">P.M.</option>
									<option option="am">A.M.</option>
								</select>
								<%=saleHeader.getTimeZone()%>
								<tsa:hidden name="SaleHeader_auctionEndTime" value="<%=saleHeader.getAuctionEndTime()%>"/>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
				<br>
				</div>
			</td>
		</tr>

		</table>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
	<td align=right valign=top><%@ include file="/sales/sale_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>

<%	if (s_view.equals("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) < 0 || s_status.compareTo(DocumentStatus.SALE_OPENAMENDMENT) == 0) { %>
	<td width=50% align=center><a href="javascript: doSubmit('/sales/sale_summary.jsp', 'SaleHeaderUpdateById;SaleRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/sales/sale_summary.jsp', 'SaleRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
<%	} else {%>
	<td width=100% align=center><a href="javascript: doSubmit('/sales/sale_summary.jsp', 'SaleRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
<%	}%>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var salenumber = "<%=s_saleNumber%>";
	var fiscalyear = "<%=saleHeader.getFiscalYear()%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var webpost = "<%=s_webpost%>";

	hideArea("navTable");

	function thisLoad()
	{
		f_StartIt();
		setAuctionOptions();
		editWebPost();
		setEventOptions();
		setTimeDisplay(frm.qa_start_time, frm.qa_start_timeofday);
		setTimeDisplay(frm.qa_end_time, frm.qa_end_timeofday);
		setTimeDisplay(frm.bid_start_time, frm.bid_start_timeofday);
		setTimeDisplay(frm.bid_end_time, frm.bid_end_timeofday);
		setTimeDisplay(frm.auction_start_time, frm.auction_start_timeofday);
		setTimeDisplay(frm.auction_end_time, frm.auction_end_timeofday);

<%	if (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) >= 0 && s_status.compareTo(DocumentStatus.SALE_OPENAMENDMENT) != 0) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function setWebpost()
	{
		if (frm.rfh_post_solicitation.checked==true && frm.rfh_online_bidding.checked==true && frm.rfh_downloadable.checked==true)
		{
			frm.SaleHeader_webpost.value = "DB";
		}
		else if (frm.rfh_post_solicitation.checked==true && frm.rfh_online_bidding.checked==true)
		{
			frm.SaleHeader_webpost.value = "VB";
		}
		else if (frm.rfh_post_solicitation.checked==true && frm.rfh_downloadable.checked==true)
		{
			frm.SaleHeader_webpost.value = "D";
		}
		else if (frm.rfh_post_solicitation.checked==true)
		{
			frm.SaleHeader_webpost.value = "V";
		}
		else
		{
			frm.SaleHeader_webpost.value = "N";
		}
	}

	function setBidAccess(value)
	{
		frm.SaleHeader_bidAccess.value = value;
	}

	function setAuctionType(value)
	{
		frm.SaleHeader_auctionType.value = value;
	}

	function setBidItemOptions(value)
	{
			frm.SaleHeader_bidItemOptions.value = "N";
	}

	function setLowBidReq(value)
	{
		if (frm.c_highest_bid_req.checked==true)
		{
			frm.SaleHeader_highestBidReq.value = "Y";
		}
		else
		{
			frm.SaleHeader_highestBidReq.value = "N";
		}
	}

	function setLowBidSource(value)
	{
		frm.SaleHeader_highestBidSource.value = value;
	}

	function setHighestDisplay(value)
	{
		frm.SaleHeader_highestDisplay.value = value;
	}

	function setAuctionOptions()
	{
		var openAuction = false;
		if (frm.rfh_auction_type[1].checked)
		{
			openAuction = true;
		}
		if (openAuction)
		{
			displayArea("auctionSettings");
			displayArea("highestBids");
			displayArea("highestDisplay");
			displayArea("highestRequirement");
<%		if (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) < 0 || s_status.compareTo(DocumentStatus.SALE_OPENAMENDMENT) == 0)
			{ %>
				if (!frm.rfh_highest_display[0].checked && !frm.rfh_highest_display[1].checked)
				{
					frm.rfh_highest_display[0].checked = true;
					setHighestDisplay(frm.rfh_highest_display[0].value);
				}
<%		} %>
		}
		else
		{
			hideArea("auctionSettings");
			hideArea("highestBids");
			hideArea("highestDisplay");
			hideArea("highestRequirement");

//			frm.SaleHeader_extendMinutes.value = "0";
//			frm.SaleHeader_bidVariance.value = "0";
			frm.c_highest_bid_req.checked = false;
			frm.c_highest_bid_req.value = "N";
			frm.rfh_highest_display[0].checked = false;
			frm.rfh_highest_display[1].checked = false;
			setHighestDisplay("");

			return false;
		}
		return true;
	}

<%if (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) < 0 || s_status.compareTo(DocumentStatus.SALE_OPENAMENDMENT) == 0)
	{ %>
		function editWebPost()
		{
			var x	     = "";
			var post     = true;
			var bid	     = true;
			var download = true;
			//var time     = frm.due_time.value;
			if ( !post )
			{
				x = "N";
				resetOptions("ALL");
				hideArea("supplierAccess");
				hideArea("bidType");
				hideArea("bidRequirements");
				hideArea("auctionSettings");
				hideArea("highestBids");
				hideArea("highestDisplay");
				hideArea("highestRequirement");
				hideArea("postOption1");
				hideArea("postOption2");
				hideArea("auctionEvents");
				hideArea("postAuctionOptions");
			}
			else
			{
				displayArea("supplierAccess");
				displayArea("postOption1");
				displayArea("postOption2");
				displayArea("auctionEvents");
				displayArea("postAuctionOptions");

				if ( !bid )
				{
					x = "";
					hideArea("bidType");
					hideArea("bidRequirements");
					hideArea("auctionSettings");
					hideArea("highestBids");
					hideArea("highestDisplay");
					hideArea("highestRequirement");

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
					displayArea("bidType");
					displayArea("bidRequirements");

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
						setBidAccess("U");
						frm.rfh_auction_type[0].checked = true;
						setAuctionType(frm.rfh_auction_type[0].value);
						setBidItemOptions("N");
					}

					setAuctionOptions();
				}
				if ( webpost == "N" )
				{
					setBidAccess("U");
				}
			}

			webpost = x;
			frm.SaleHeader_webpost.value = x;

			return post;
		}
<%	}
	else
	{ %>
		function editWebPost(x, i)
		{
			return false;
		}
<%	} %>


	function resetOptions (fld)
	{
		if (fld == "ALL")
		{
		//	frm.c_webpost[1].checked = false;
		//	frm.c_webpost[2].checked = false;

			setBidAccess("");
			frm.rfh_auction_type[0].checked = false;
			frm.rfh_auction_type[1].checked = false;
			setAuctionType("");
			setBidItemOptions("");

			frm.c_highest_bid_req.checked = false;
			frm.SaleHeader_highestBidReq.value = "N";
			frm.rfh_highest_display[0].checked = false;
			frm.rfh_highest_display[1].checked = false;
			setHighestDisplay("");
		}
		else if (fld == "bid_access" )
		{
			setBidAccess("");
<%	if ( saleHeader.getBidAccess().equals("U") ) {%>
			setBidAccess("U");
<%	}%>
		}
		else if (fld == "auction_type")
		{
			frm.rfh_auction_type[0].checked = false;
			frm.rfh_auction_type[1].checked = false;
			setAuctionType("");
<%	if ( saleHeader.getAuctionType().equals("S") ) {%>
			frm.rfh_auction_type[0].checked = true;
			setAuctionType(frm.rfh_auction_type[0].value);
<%	}
	else if ( saleHeader.getAuctionType().equals("O") ) {%>
			frm.rfh_auction_type[1].checked = true;
			setAuctionType(frm.rfh_auction_type[1].value);
<%	}%>
		}
		else if (fld == "bid_item_options")
		{
			if (i_bidItem >= 0)
			{
				frm.SaleHeader_bidItemOptions.value = "N";
			}
			else
			{
				setBidItemOptions("");
			}
		}
	}

<%	if (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) >= 0 && s_status.compareTo(DocumentStatus.SALE_OPENAMENDMENT) != 0)
		{ %>
			function checkWebPost(fld,x)
			{
				resetOptions(fld.name.substring(4, fld.name.length));
				return false;
			}
<%	}
    	else
    	{ %>
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
				else if ( (fld.name.substring(4, 10) == "highest") && (frm.rfh_auction_type[1].checked == false) )
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


<%	if (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) >= 0 && s_status.compareTo(DocumentStatus.SALE_OPENAMENDMENT) != 0)
		{%>
			function setBidReq()
			{
				return false;
			}
<%	}
		else
		{ %>
			function setBidReq()
			{
				frm.SaleHeader_bidItemOptions.value = "N";

				if ( frm.c_highest_bid_req.checked ) {
					frm.SaleHeader_highestBidReq.value = "Y";
				}
				else {
					frm.SaleHeader_highestBidReq.value = "N";
				}

				return true;
			}
<%   } %>


	function setPostAuctionOptions() {
		if ( frm.c_ca_indicate_highest.checked ) {
			frm.SaleHeader_caIndicateHighest.value = "Y";
		}
		else {
			frm.SaleHeader_caIndicateHighest.value = "N";
		}
		if ( frm.c_ca__amount.checked ) {
			frm.SaleHeader_caHighestAmount.value = "Y";
		}
		else {
			frm.SaleHeader_caHighestAmount.value = "N";
		}
		if ( frm.c_ca_winning_vendor.checked ) {
			frm.SaleHeader_caWinningVendor.value = "Y";
		}
		else {
			frm.SaleHeader_caWinningVendor.value = "N";
		}
		return true;
	}

	function setEventOptions() {
		var qaEvent = frm.c_qa_event.checked;
		var bidEvent = frm.c_bid_event.checked;
		var auctionEvent = frm.c_auction_event.checked;

		if ( qaEvent ) {
			frm.SaleHeader_qaEvent.value = "Y";
			displayArea("qaEventTime");
		} else {
			frm.SaleHeader_qaEvent.value = "N";
			hideArea("qaEventTime");
		}
		if ( bidEvent ) {
			frm.SaleHeader_bidEvent.value = "Y";
			displayArea("bidEventTime");
		} else {
			frm.SaleHeader_bidEvent.value = "N";
			hideArea("bidEventTime");
		}
		if ( auctionEvent ) {
			frm.SaleHeader_auctionEvent.value = "Y";
			displayArea("auctionEventTime");
		} else {
			frm.SaleHeader_auctionEvent.value = "N";
			hideArea("auctionEventTime");
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
			time = hour + ":" + min;
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

// End Hide script -->
</SCRIPT>