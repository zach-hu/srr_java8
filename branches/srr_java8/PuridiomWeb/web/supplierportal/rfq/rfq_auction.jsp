<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ include file="/supplierportal/rfq/bid_instructions.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.Dates" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Date" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/dynamicTables.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/timer.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(user.getOrganizationId());
	RfqHeader	rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	RfqVendor	rfqVendor = new RfqVendor();
	List	rfqQuestionList = rfqHeader.getRfqQuestionList();
	List	rfqAttachmentList = rfqHeader.getDocAttachmentList();
	List	rfqCommentList = rfqHeader.getDocCommentList();
	List	rfqSupplierList = (List) request.getAttribute("rfqVendorList");
	String	s_original_due_time = (String) request.getAttribute("originalDueTime");
	String	s_auction_extended = (String) request.getAttribute("auctionExtended");
	long	dueDateTime = Dates.getDate(rfqHeader.getDueDate() + " " + rfqHeader.getBidDueTime()).getTime();
	long	originalDueTime = 0;
	boolean b_exists = false;
	boolean b_auction_extended = false;

	try {
		originalDueTime = (new BigDecimal(s_original_due_time)).longValue();
	} catch(Exception e) {
	}

	String	s_rfq_number = rfqHeader.getRfqNumber();
	String	s_rfq_status = rfqHeader.getStatus();
	String	s_rfq_amendment = rfqHeader.getRfqAmendment();
	String	s_webpost	= rfqHeader.getWebpost();
	String	s_bid_access	= rfqHeader.getBidAccess();
	String	s_bid_item_options = rfqHeader.getBidItemOptions();
	String	s_rfq_type = rfqHeader.getRfqType();
	boolean	b_submit_access	= true;
	boolean	b_download_access	= true;
	boolean	b_require_all	= false;
	boolean	b_openauction	= false;
	boolean	b_require_entry	= false;
	boolean	b_show_low_amt	= false;
	boolean	b_low_subtotal	= false;
	boolean	b_bids_entered = false;
	boolean	b_show_submit_bid = false;
	boolean	b_show_bid_edit = true;
	int	i_questions = 0;
	int	i_attachments = 0;
	int	i_supplierRows = 0;
	Map vendorBidsEntered = new HashMap();

	if (rfqQuestionList != null) {
		i_questions = rfqQuestionList.size();
	}
	if (rfqAttachmentList != null) {
		i_attachments = rfqAttachmentList.size();
	}
	if (rfqSupplierList != null) {
		i_supplierRows = rfqSupplierList.size();
	}
	if (originalDueTime > 0 && dueDateTime > originalDueTime) {
		b_auction_extended = true;
	} else if (HiltonUtility.ckNull(s_auction_extended).equals("true")) {
		b_auction_extended = true;
	}

	for (int isr=0; isr < i_supplierRows; isr++) {
		RfqVendor vendor = (RfqVendor) rfqSupplierList.get(isr);
		if (vendor.getComp_id().getVendorId().equalsIgnoreCase(user.getVendorId())) {
			rfqVendor = vendor;
			b_exists = true;
		}
		vendorBidsEntered.put(vendor.getComp_id().getVendorId(), vendor.getBidsEntered());
	}

	if ( s_webpost.indexOf("B") < 0) {
		b_submit_access = false;	// No online bidding for this solicitation
	}
	if (user.isGuest()) {
		b_submit_access = false;	// Guests do not have access to submit bids
		b_download_access = false;// Guests do not have access to download solicitations
	}
	if ( s_bid_access.equals("I") && (!b_exists) ) {
		b_submit_access = false;	// Restricted to listed suppliers only
		b_download_access = false;	// Restricted to listed suppliers only
	}

	b_require_all = s_bid_item_options.equals("A");
	b_openauction = rfqHeader.getAuctionType().trim().equals("O");

	if (b_openauction) {
		b_require_entry = rfqHeader.getLowestBidReq().trim().equals("Y");
		b_low_subtotal = rfqHeader.getLowestBidSource().trim().equals("S");
		b_show_low_amt = rfqHeader.getLowestDisplay().trim().equals("A");
	}

	String	s_retrieve = "RFQ";
	String	s_supplier_code = "";
	String	s_line_number = "";
	String	s_monitor = "N";
	BigDecimal bd_quantity	 = new BigDecimal(0.00);
	BigDecimal bd_unit_price = new BigDecimal(0.00);
	BigDecimal bd_ext_price	 = new BigDecimal(0.00);
	BigDecimal bd_uom_factor = new BigDecimal(1.00);

	List rfqLineList = rfqHeader.getRfqLineList();
	int	iLineRows = 0;
	int	iTotalBidRows = 0;

	if (rfqLineList != null) {
		iLineRows = rfqLineList.size();
	}

	String	eventType = "Reverse Auction";
	String	dueDate = HiltonUtility.getFormattedDate(rfqHeader.getAuctionEndDate(), user.getOrganizationId());
	String	dueTime = rfqHeader.getAuctionEndTime();
	String	dueDateTimeString = HiltonUtility.getFullDateTimeString(rfqHeader.getAuctionEndDate(), rfqHeader.getAuctionEndTime(), rfqHeader.getTimeZone(), user.getOrganizationId());
%>

<%@ include file="/supplierportal/rfq/rfq_event_header.jsp" %>

<table border=0 width=680px valign=top>
<tr>
	<td>
		<table border=0 cellspacing=0 cellpadding=0 align=center width=90%>
		<tr>
			<td nowrap width=33%><a href="javascript: reviewRfq(); void(0);">Review Solicitation</td>
			<td align=center nowrap width=34%><b>This page will refresh every 30 seconds!</b></td>
			<td nowrap width=33% align=right>
				<table border=0 cellspacing=0 cellpadding=2>
				<tr>
<%	if (rfqHeader.getQaEvent().equalsIgnoreCase("Y")) {%>
					<td align=right nowrap width=33%><a href="javascript: viewQALog(); void(0);">View Q & A Log</a></td>
<%	}%>
					<!--td align=right nowrap><a href="javascript: viewBidHistoryTotalAll(); void(0);"><img src="<%=contextPath%>/supplierportal/images/bid_history_total.gif" border=0 valign=top alt="Total prices in history for all Suppliers"></a></td-->
					<!--td align=right nowrap><a href="javascript: viewBidHistoryTotal(); void(0);"><img src="<%=contextPath%>/supplierportal/images/bid_history_unit.gif" border=0 valign=top alt="Total prices in history"></a></td-->
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
<%	if (rfqHeader.getExtendMinutes().compareTo(new BigDecimal(0)) > 0) {%>
<tr>
	<td align=center>
		<table border=0 cellspacing=0 cellpadding=2 align=center width=95%>
		<tr><td valign=top><font color=red>**NOTE:</font></td><td valign=top> If a bid is placed during the last <%=rfqHeader.getExtendMinutes()%> minutes of this auction, the auction will be automatically extended for an additional <%=rfqHeader.getExtendMinutes()%> minutes from the time of the latest bid. This auction will close once all bidding activity has stopped for a period of <%=rfqHeader.getExtendMinutes()%> minutes.</td></tr>
		</table>
	</td>
</tr>
<%	}
		if (iLineRows > 0) {%>
<tr valign=top >
	<td  align=center width=100%>
		<div id="itemBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 0px; width: 654px; align:center; overflow-y:visible; overflow-x:visible;">
		<table border=0 width=650px  cellpadding=0 cellspacing=0>
		<%@ include file="/supplierportal/rfq/rfq_bid_monitor_rows.jsp" %>
		</table>
		</div>
	</td>
</tr>
<%	}
		if (iLineRows == 0) {%>
<tr ><td >&nbsp;</td></tr>
<tr ><td  align=center>No bids have been entered for this solicitation.</td></tr>
<tr ><td >&nbsp;</td></tr>
<%	}%>
<tr ><td  align=center valign=top><br><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);" border=0><img tabIndex=30 class=button src="<%=contextPath%>/supplierportal/images/button_cancel.gif" border=0></a></td></tr>
<tr ><td >&nbsp;</td></tr>
</table>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=rfqHeader.getRfqNumber()%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=rfqHeader.getRfqAmendment()%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="<%=rfqHeader.getRfqType()%>"/>
<tsa:hidden name="RfqHeader_auctionType" value="<%=rfqHeader.getAuctionType()%>"/>
<tsa:hidden name="originalDueTime" value="<%=dueDateTime%>"/>
<tsa:hidden name="auctionExtended" value="<%=b_auction_extended%>"/>
<tsa:hidden name="openAuctionPage" value="/rfq/rfq_auction.jsp"/>
<tsa:hidden name="closedAuctionPage" value="/rfq/rfq_summary_postauction.jsp"/>
<tsa:hidden name="RfqVendor_icRfqHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>

<!-- JavaScripts for Entry Validation -->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	var frm = document.purchaseform;
	var b_timer = false;
	var bid_timer = null;
	var blink_speed=1000;
	var iblink=0;

	startMonitor();
<%	if (b_auction_extended) {%>
	blink("extendedAuctionMsg");
<%	}%>
	function thisLoad() {
		start();
	}

	function startMonitor() {  /* called when page is refreshed */
		bid_timer = setTimeout("refreshBids();",30000);
	}

	function refreshBids() {
		if (!b_submit) {
			doSubmit('', 'RfqRetrieve;SetEventPage');
		}
	}

	function stopMonitor() {
		clearTimeout(bid_timer);

		opener.b_timer = false;
		opener.frm.img_monitor.src = "<%=contextPath%>/supplierportal/images/start_monitor.gif";
		opener.frm.img_monitor.alt = "Monitor";
		opener.frm.monitor.value = "N";
	}

	function editBid(line) {
		b_submit = true;
		popupParameters = "rfqLine=" + line + ";RfqHeader_icRfqHeader=" + frm.RfqHeader_icRfqHeader.value;
		doSubmitToLookup("/rfq/rfq_bids_edit.jsp", "RfqRetrieve", 'WIDTH=680','HEIGHT=500');
	}

	function blink(areaName) {
		if (iblink%2==0) {
			displayArea(areaName);
		} else {
			hideAreaWithBlock(areaName);
		}
		 if (iblink<1) {
			iblink++;
			setTimeout("blink('"+areaName+"')",blink_speed);
		} else {
			iblink--;
			setTimeout("blink('"+areaName+"')",blink_speed/2);
		}
	}

	function viewQALog() {
		b_submit = true;
		popupParameters = "VendorQuestion_icRfqHeader=" + frm.RfqHeader_icRfqHeader.value + ";rfqNumber=" + frm.RfqHeader_rfqNumber.value + ";rfqAmendment=" + frm.RfqHeader_rfqAmendment.value + ";rfqType=" + frm.RfqHeader_rfqType.value;
		doSubmitToLookup("/rfq/rfq_qa_log.jsp", "VendorQuestionRetrieveByRfq", 'WIDTH=680','HEIGHT=500');
	}

	function reviewRfq() {
		doSubmit("/rfq/rfq_review.jsp", "RfqRetrieve");
	}

	function Vendor_BidGraph()
	{
		popupParameters = "dato=valor";
	<%
		List lineVendorList = (List) rfqHeader.getRfqLineList();
		RfqLine rfqVendorLine = null;
		for(int i=0;i<lineVendorList.size();i++) {
			rfqVendorLine = (RfqLine) lineVendorList.get(i);
			%>
				popupParameters = popupParameters + ";edit1<%=i%>=<%=rfqVendorLine.getIcRfqLine()%>";
			<%
		}
	%>
	popupParameters = popupParameters + ";RfqBidHistory_icHeader=<%=rfqVendorLine.getIcRfqHeader()%>;qty=<%=lineVendorList.size()%>";
	doSubmitToLookup('/rfq/bidhistory_supplier_chart.jsp', 'FChartBidVendorRetrieve','WIDTH=650','HEIGHT=500');
	}

	function viewBidHistoryTotalAll(){
		var newInputField = "";
		popupParameters = "RfqBidHistory_icRfqHeader=<%=rfqHeader.getIcRfqHeader()%>";
		popupParameters = popupParameters + ";RfqHeader_icRfqHeader=<%=rfqHeader.getIcRfqHeader()%>";		
		popupParameters = popupParameters + ";RfqBidHistory_vendorId=<%=user.getVendorId()%>";
		doSubmitToLookup('/rfq/bidhistory_supplier_chart.jsp', 'RfqBidHistoryTotalPriceListRetrieveByHeader','WIDTH=650','HEIGHT=500');
	}

	function viewBidHistoryTotal(){
		var newInputField = "";
		popupParameters = "RfqBidHistory_icRfqHeader=<%=rfqHeader.getIcRfqHeader()%>";
		popupParameters = popupParameters + ";RfqHeader_icRfqHeader=<%=rfqHeader.getIcRfqHeader()%>";		
		popupParameters = popupParameters + ";RfqBidHistory_vendorId=<%=user.getVendorId()%>";
		doSubmitToLookup('/rfq/bidhistory_supplier_chart.jsp', 'RfqBidHistoryTotalPriceListRetrieveByHeaderVendor','WIDTH=650','HEIGHT=500');
	}

// end hiding contents -->
</SCRIPT>