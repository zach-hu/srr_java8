<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Date" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(user.getOrganizationId());
	String	s_lic = "";
	String	s_monitor = "N";
	boolean b_exists = false;

	RfqHeader	rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	RfqVendor	rfqVendor = new RfqVendor();
	List	rfqQuestionList = rfqHeader.getRfqQuestionList();
	List	rfqAttachmentList = rfqHeader.getDocAttachmentList();
	List	rfqCommentList = rfqHeader.getDocCommentList();
	List	rfqSupplierList = (List) request.getAttribute("rfqVendorList");

	String	s_webpost	= rfqHeader.getWebpost();
	String	s_bid_item_options = rfqHeader.getBidItemOptions().trim();
	String	s_due = HiltonUtility.getFullDateTimeString(rfqHeader.getDueDate(), rfqHeader.getBidDueTime(), rfqHeader.getTimeZone(), user.getOrganizationId());
	boolean	b_submit_access	= false;		// Solicitation is closed - no access to submit bids
	boolean	b_download_access	= false;		// Solicitation is closed - no access to download
	boolean	b_require_all	= false;
	boolean	b_openauction	= false;
	boolean	b_require_entry	= false;
	boolean	b_show_low_amt	= false;
	boolean	b_low_subtotal	= false;
	boolean	b_bids_entered = false;
	boolean	b_show_submit_bid = false;
	boolean	b_winning_vendor = false;
	boolean	b_allow_monitor = false;
	boolean b_allow_email = false;
	int	i_questions = 0;
	int	i_attachments = 0;
	int	i_supplierRows = 0;
	
	if (rfqQuestionList != null) {
		i_questions = rfqQuestionList.size();
	}
	if (rfqAttachmentList != null) {
		i_attachments = rfqAttachmentList.size();
	}
	if (rfqSupplierList != null) {
		i_supplierRows = rfqSupplierList.size();
	}
	
	for (int isr=0; isr < i_supplierRows; isr++) {
		RfqVendor vendor = (RfqVendor) rfqSupplierList.get(isr);
		if (vendor.getComp_id().getVendorId().equalsIgnoreCase(user.getVendorId())) {
			rfqVendor = vendor;
			b_exists = true;
			break;
		}
	}
	
	b_require_all = s_bid_item_options.equals("A");
	b_openauction = rfqHeader.getAuctionType().trim().equals("O");
	b_bids_entered = rfqVendor.getBidsEntered().equals("Y");

	if (b_openauction) {
		b_require_entry = rfqHeader.getLowestBidReq().trim().equals("Y");
		b_low_subtotal = rfqHeader.getLowestBidSource().trim().equals("S");
		b_show_low_amt = rfqHeader.getCaLowestAmount().trim().equals("Y");
		b_winning_vendor = rfqHeader.getCaWinningVendor().trim().equals("Y");
	}
%>


<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/date_check.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/calendar.js"></SCRIPT>

<table border=0 width=680px cellpadding=0 cellspacing=0 valign=top>
<tr>
	<td align=center>
		<!-- RFQ HEADER -->
		<table border=0 cellspacing=0 cellpadding=0 height=100%>
		<tr>
			<td valign=middle class=hdr12 height=20px align=center>
				<%=RfqType.toString(rfqHeader.getRfqType(), oid)%> # <%=rfqHeader.getRfqNumber()%>
<%	if (!HiltonUtility.isEmpty(rfqHeader.getRfqAmendment()) && !rfqHeader.getRfqAmendment().equals("0")) {%>
				<br>Amendment # <%=rfqHeader.getRfqAmendment()%>
<%	}%>
			</td>
		</tr>
		</table>
		<table border=0 cellspacing=0 cellpadding=2 height=100%>
		<tr>
			<td class=formType><%	if (b_openauction) {%>Auction<% } else {%>Sealed Bid<%}%> Closed on <%=HiltonUtility.getFullDateTimeString(rfqHeader.getDueDate(), rfqHeader.getBidDueTime(), rfqHeader.getTimeZone(), user.getOrganizationId())%></td>
		</tr>
		</table>
		
		<!-- RFQ HEADER END -->
		<br>

		<table border=0 cellspacing=0 cellpadding=0 height=100% align=center>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 height=100% align=center>
				<tr>
					<td class=summary align=center>
						<table border=0 cellpadding=2 cellspacing=0 class=summary width=650px>
						<%@ include file="/supplierportal/rfq/rfq_hdr_summary.jsp" %>
						<%@ include file="/supplierportal/rfq/rfq_comments.jsp" %>
						<%@ include file="/supplierportal/rfq/rfq_attachments.jsp" %>
						</table>
					</td>
				</tr>
				<tr><td><br></td></tr>
				<tr>
					<td class=summary align=center>
						<%@ include file="/supplierportal/rfq/rfq_bids_postauction.jsp" %>
<%	if (!HiltonUtility.isEmpty(s_after_comment)) { %>
						<table border=0 cellpadding=0 cellspacing=0 class=summary width=650px>
						<tr><td colspan=2><br></td></tr>
						<tr>
							<td width=75px valign=top align=right><b>Comments:</b></td>
							<td valign=top>&nbsp;<%=s_after_comment%></td>
						</tr>
						</table>
<%	} %>
					</td>
				</tr>
<%	if (b_bids_entered) {%>
				<tr><td><br></td></tr>
				<tr>
					<td class=summary align=center>
						<br>
						<%@ include file="/supplierportal/rfq/rfq_supplier_postauction.jsp" %>
						<br>
					</td>
				</tr>
<%	}%>
				</table>
			</td>
		</tr>
		<tr><td><br></td></tr>
		<tr>
			<td>
				<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>
				<tsa:hidden name="as_action" value="REDIRECT"/>
				<tsa:hidden name="as_rfb_action" value="REDIRECT"/>
				<tsa:hidden name="monitor" value="N"/>
				<tsa:hidden name="as_info_changed" value="N"/>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	var allowEdit = true;
	var fldObject = null;
	var fldFromObject = null;
	var fldToObject = null;
	var currentRow = 0;
	var bid_timer = null;
	var b_timer = false;
	var monitor = frm.monitor.value;
	var lookup_window = null;
	var submitToMonitor = "N";
	var dollar_dec = <%=propertiesManager.getProperty("MISC", "DollarDecimals", "2")%>;

<%	if (b_bids_entered) {%>
	var myBids = document.all("myBid");

	for (var ir = 0; ir < <%=i_lineRows%>; ir++) {
		if (frm.RfqBid_bidCode[ir].value == "NC") {
			myBids[ir].innerHTML = "No Charge";
		}
		else if (frm.RfqBid_bidCode[ir].value == "SE") {
			myBids[ir].innerHTML = "Sealed";
		}
		else if (frm.RfqBid_bidCode[ir].value == "NSP") {
			myBids[ir].innerHTML = "Not Separately Priced";
		}
		else if (frm.RfqBid_bidCode[ir].value == "SN") {
			myBids[ir].innerHTML = "See Notes";
		}
		else if (frm.RfqBid_bidCode[ir].value == "NB" || frm.RfqBid_bidCode[ir].value == "NE") {
			myBids[ir].innerHTML = "No Bid";
			continue;
		}
		else if (frm.RfqBid_bidCode[ir].value == "00") {
			myBids[ir].innerHTML = frm.RfqBid_unitPrice[ir].value;
		}
		total(ir);
	}

	function total(row) {
		var myTotalBids = document.all("myTotalBid");
		var bid_code = frm.RfqBid_bidCode[row].value;
		var p = 0.00;
		var q = eval(nfilter(frm.qty[row]));
		var f = eval(nfilter(frm.um_factor[row]));
		var tot = 0.00;

		if (f == 0) { f = 1; }

		if ( (bid_code != "NB") && (bid_code != "NC") && (bid_code != "NSP") && (bid_code != "NE") && (bid_code != "SN")) {
			p = nformat(eval(nfilter(frm.unit_price[row])),dollar_dec);	
		}

		tot = eval(p * q * f);
		frm.ext_price[row].value = tot;
		formatPrice(frm.ext_price[row]);

		myTotalBids(row).innerHTML = frm.ext_price[row].value;
	}
	
	function formatPrice(x) {
		var p = nformat(eval(nfilter(x)),dollar_dec);

		x.value = p;
	}
<%	}%>

	function setAction(x, val) {
		if (x == "bid") {
			frm.as_rfb_action.value = val;
		}
		else {
			frm.as_action.value = val;
		}
	}


// end hiding contents -->
</SCRIPT>

<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
