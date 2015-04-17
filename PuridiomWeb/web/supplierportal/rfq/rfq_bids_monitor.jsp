<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.math.BigDecimal" %>
<%
	String	s_retrieve = "RFQ";
	String	s_supplier_code = "";
	String	s_line_number = "";
	String	s_header_ic = (String) request.getAttribute("RfqLine_icRfqHeader");
	BigDecimal bd_quantity	 = new BigDecimal(0.00);
	BigDecimal bd_unit_price = new BigDecimal(0.00);
	BigDecimal bd_ext_price	 = new BigDecimal(0.00);
	BigDecimal bd_uom_factor = new BigDecimal(1.00);
	boolean	b_show_bid_edit = false;
	boolean b_bids_entered	= false;
	List rfqLineList = (List) request.getAttribute("rfqLineList");
	int	iLineRows = 0;
	int	iTotalBidRows = 0;
	
	if (rfqLineList != null) {
		iLineRows = rfqLineList.size();
	}
	
	for (int i=0; i < iLineRows; i++) {
		RfqLine rfqLine = (RfqLine) rfqLineList.get(i);
		List rfqBidList = (List) rfqLine.getRfqBidList();
		int iBidRows = 0;
			
		if (rfqBidList != null) {
			iBidRows = rfqBidList.size();
		}
		
		for (int ib = 0; ib < iBidRows; ib++) {
			RfqBid rfqBid = (RfqBid) rfqBidList.get(ib);
			BigDecimal	bdBid = rfqBid.getUnitPrice().setScale(5);
			String	bidCode = rfqBid.getBidCode();
		
			if (bdBid != null && bidCode != null && (bdBid.compareTo(new BigDecimal("0.00001")) >= 0 || (bidCode.length() > 0 && !bidCode.equals("NE"))) ) {
				b_bids_entered = true;
				break;
			}
		}
		
		if (b_bids_entered) {
			break;
		}
	}
%>

<table border=0 width=100% valign=top>
<tr ><td align=center><b>This page will refresh every 30 seconds!</b></td></tr>
<%	if (iLineRows > 1) {%>
<tr valign=top >
	<td  align=center width=100%>
		<div id="itemBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 0px; width: 454px; align:center; overflow-y:visible; overflow-x:visible;">
		<table border=0 width=450px  cellpadding=0 cellspacing=0>
		<%@ include file="/supplierportal/rfq/rfq_bid_monitor_rows.jsp" %>
		</table>
		</div>
	</td>
</tr>
<%	}
		if (iTotalBidRows == 0 || iLineRows == 0) {%>
<tr ><td >&nbsp;</td></tr>
<tr ><td  align=center>No bids have been entered for this solicitation.</td></tr>
<tr ><td >&nbsp;</td></tr>
<%	}%>
<tr ><td  align=center valign=top><br><a href="javascript: stopMonitor(); void(0);" border=0><img tabIndex=30 class=button src="<%=contextPath%>/supplierportal/images/button_close.gif" border=0></a></td></tr>
<tr ><td >&nbsp;</td></tr>
</table>

<tsa:hidden name="RfqLine_icRfqHeader" value="<%=s_header_ic%>"/>
</form>

<!-- JavaScripts for Entry Validation -->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	var frm = document.purchaseform;
	var b_timer = false;
	var bid_timer = null;

	startMonitor();

	function startMonitor() {  /* called when page is refreshed */
		bid_timer = setTimeout('doSubmit("/rfq/rfq_bids_monitor.jsp", "RfqLineBidsRetrieveByHeader");',30000);
	}

	function stopMonitor() {
		clearTimeout(bid_timer);

		opener.b_timer = false;
		opener.frm.img_monitor.src = "<%=contextPath%>/supplierportal/images/start_monitor.gif";
		opener.frm.img_monitor.alt = "Monitor";
		opener.frm.monitor.value = "N";

		window.close();
	}

// end hiding contents -->
</SCRIPT>

</body>
</html>