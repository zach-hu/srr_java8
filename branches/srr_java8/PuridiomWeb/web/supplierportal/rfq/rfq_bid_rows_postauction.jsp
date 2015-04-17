<!--	RFQ BID ROWS	-->
<%
		List	rfqBidList = rfqLine.getRfqBidList();
		int	i_bidRows = 0;
	
		if (rfqBidList != null) {
			i_bidRows = rfqBidList.size();
		}

		if (b_bids_entered) {
%>
	<tr>
		<td nowrap class=itemSummary align=right width=32%><b>My Bid: </b></td>
		<td class=itemSummary nowrap>
<%		boolean bidsEntered = false;
			for (int ibr = 0; ibr < i_bidRows; ibr++) {
				RfqBid rfqBid = (RfqBid) rfqBidList.get(ibr);
				if (user.getVendorId().equalsIgnoreCase(rfqBid.getComp_id().getVendorId())) {
					bidsEntered = true;
%>
					<div id="myBid"><%=HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid)%></div>
					<tsa:hidden name="db_unit_price" value="<%=HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid)%>"/>
					<tsa:hidden name="RfqBid_unitPrice" value="<%=HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid)%>"/>
					<tsa:hidden name="RfqBid_bidCode" value="<%=rfqBid.getBidCode()%>"/>
					<tsa:hidden name="unit_price" value="<%=HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid)%>"/>
<%			}
			}
			
			if (!bidsEntered) {%>
					<div id="myBid">No Bid</div>
					<tsa:hidden name="db_unit_price" value="0.00"/>
					<tsa:hidden name="RfqBid_unitPrice" value="0.00"/>
					<tsa:hidden name="RfqBid_bidCode" value="NE"/>
					<tsa:hidden name="unit_price" value="0.00"/>
<%		} else {
				b_bids_entered = true;
			}%>

			<tsa:hidden name="RfqBid_icRfqHeader" value="<%=rfqLine.getIcRfqHeader()%>"/>
			<tsa:hidden name="RfqBid_icRfqLine" value="<%=rfqLine.getIcRfqLine()%>"/>
			<tsa:hidden name="RfqBid_vendorId" value="<%=user.getVendorId()%>"/>
			<tsa:hidden name="rfb_item_selected" value=""/>
			<tsa:hidden name="lowest_bid" value="<%=rfqLine.getLowestBid()%>"/>
		</td>
	</tr>
	<tr>
		<td nowrap class=itemSummary align=right><b>My Total Bid:</b></td>
		<td class=itemSummary><tsa:hidden name="ext_price" value=""/><div id="myTotalBid">&nbsp;</div></td>
	</tr>
	<%	if (!HiltonUtility.isEmpty(rfqLine.getRfqNote())) {%>
		<tr>
			<td nowrap class=itemSummary align=right valign=top><b>My Item Notes: </b></td>
			<td class=itemSummary colspan=2 valign=top><%=rfqLine.getRfqNote()%></td>
		</tr>
	<%	}
			if (b_openauction && b_show_low_amt && !b_low_subtotal && (b_bids_entered || !b_require_entry)) {
				bd_low_bid = HiltonUtility.getFormattedDollar(rfqLine.getLowestBid(), oid);
	%>
	<tr>
		<td nowrap class=itemSummary align=right><B>Lowest Bid:</B></td>
		<td class=itemSummary>
		<%	if (bd_low_bid.compareTo(new BigDecimal("0.00000")) <= 0) {%>
				No Bids
		<%	} else {%>
				<%=bd_low_bid%>&nbsp;
		<%	}%>
		</td>
	</tr>
	<%	} else if (b_openauction && !b_low_subtotal && (b_bids_entered || !b_require_entry) && b_indicate_lowest) {%>
	<tr><td></td><td nowrap class=itemSummary><font class=error><% if (!rfqLine.getLowestVendorId().equalsIgnoreCase(user.getVendorId())) {%>Not <%}%>Lowest Bid</font></td></tr>
	<%	}
		}%>
<%	if (!b_low_subtotal && b_winning_vendor) {%>
	<tr>
		<td nowrap class=itemSummary align=right><B>Winning Vendor:</B></td>
		<td class=itemSummary><%=VendorManager.getInstance().getVendorName(oid,rfqLine.getLowestVendorId())%></td>
	</tr>
<%	}%>
<!--	RFQ BID ROWS END	-->