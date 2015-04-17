<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.math.BigDecimal" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(user.getOrganizationId());
	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	if (rfqHeader == null) {
		request.setAttribute("errorMsg", "RfqHeader was not found.");
		getServletContext().getRequestDispatcher("/system/error.jsp").forward(request, response);
		return;
	}
	RfqVendor rfqVendor = new RfqVendor();
	BigDecimal bd_lowbid = new BigDecimal(0);
	BigDecimal bd_thisbid = new BigDecimal(0);
	boolean	b_lowestbid = false;
	boolean	b_sealedbid = false;
	boolean	b_low_subtotal	= false;
	boolean	b_show_low_amt	= false;
	boolean b_require_entry = false;
	boolean b_bids_entered = true;
	boolean b_submit_access = true;
	boolean b_openauction = 	rfqHeader.getAuctionType().trim().equals("O");
	int	i_lowRows	= 0;
	
	if (request.getAttribute("rfqVendor") != null) {
		rfqVendor = (RfqVendor) request.getAttribute("rfqVendor");
	} else {
		List rfqVendorList = (List) request.getAttribute("rfqVendorList");
		if (rfqVendorList != null) {
			for (int is = 0; is < rfqVendorList.size(); is++) {
				RfqVendor vendor = (RfqVendor) rfqVendorList.get(is);
				if (vendor.getComp_id().getVendorId().equals(user.getVendorId())) {
					rfqVendor = vendor;
				}
			}
		}
	}

	if ( rfqHeader.getLowestBidSource().equals("S") ) {
		b_low_subtotal = true;
		if (rfqHeader.getLowestVendorId().equalsIgnoreCase(user.getVendorId())) {
			b_lowestbid = true;
		}
	}
	if ( rfqHeader.getLowestDisplay().equals("A") ) {
		b_show_low_amt = true;
	}
	if ( rfqHeader.getAuctionType().equals("S") ) {
		b_sealedbid = true;
	}
%>

<br>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td width=100% align=center>
		<table width=100% cellpadding=0 cellspacing=0 border=0>	
		<tr>
			<td valign=middle rowspan=2 class=formType align=center>
				Thank you!<br>Your bids have been submitted for Solicitation # <%=rfqHeader.getRfqNumber()%>.
			</td>
		</tr>
		<tr><td><br></td></tr>
<%	if (b_low_subtotal && !b_show_low_amt) {%>
		<tr><td align=center class=error>
			You are currently <% if (!b_lowestbid) {%>NOT <%}%>the lowest bidder for this solicitation.</td></tr>
		<tr><td><br></td></tr>
<%	}%>
		<tr><td align=center><%@ include file="/supplierportal/rfq/rfq_bids_confirm.jsp"%></td></tr>
		</table>
		<br>
<% if (	b_openauction && b_lowestbid && !b_sealedbid ) {%>
		<table border=0 cellpadding=0 cellspacing=0>
		<tr>
			<td align=right><input type=checkbox name="c_notify" value="A" onclick="setNotification();" <% if (rfqVendor.getNotificationCode().equals("A")) {%>checked<%}%>>&nbsp;</td>
			<td nowrap><b>Email me if I am no longer the lowest bidder.</b></td>
		</tr>
		</table>
		<br>
<%}%>
	</td>
</tr>
<tr><td align=center><a href="javascript: openSolicitation(); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_open_solicitation.gif" class=button border=0></a></td></tr>
</table>

<tsa:hidden name="as_action" value=""/>
<tsa:hidden name="as_redirect" value="<%=contextPath%>/supplierportal/menu/menu_pg.jsp"/>
<tsa:hidden name="RfqVendor_notificationCode" value="<%=rfqVendor.getNotificationCode()%>"/>
<tsa:hidden name="RfqVendor_icRfqHeader" value="<%=rfqVendor.getComp_id().getIcRfqHeader()%>"/>
<tsa:hidden name="RfqVendor_bidsEntered" value="<%=rfqVendor.getBidsEntered()%>"/>
<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	var myBids = document.all("myBid");
	var dollar_dec = <%=propertiesManager.getProperty("MISC", "DollarDecimals", "2")%>;
	
	for (var ir = 0; ir < <%=i_lineRows%>; ir++) {
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

		if (myTotalBids != null && myTotalBids != undefined && myTotalBids.length > 1) {
			myTotalBids(row).innerHTML = frm.ext_price[row].value;
		} else if (row == 0) {
			myTotalBids.innerHTML = frm.ext_price[row].value;
		}
	}
	
	function formatPrice(x) {
		var p = nformat(eval(nfilter(x)),dollar_dec);

		x.value = p;
	}
	
	function setNotification() {
		if ( frm.c_notify.checked ) {
			frm.RfqVendor_notificationCode.value = "A";
		}
		else {
			frm.RfqVendor_notificationCode.value = "N";
		}
		doSubmit("/rfq/bid_confirm.jsp", "RfqVendorUpdateById;RfqRetrieve");
	}

	function openSolicitation() {
		doSubmit('', 'RfqRetrieve;SetEventPage');
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/supplierportal/system/prevent_caching.jsp" %>