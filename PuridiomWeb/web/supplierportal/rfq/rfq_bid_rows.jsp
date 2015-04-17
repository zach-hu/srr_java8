<!--	RFQ BID ROWS	-->
<%
		List	rfqBidList = rfqLine.getRfqBidList();
		int	i_bidRows = 0;

		if (rfqBidList != null) {
			i_bidRows = rfqBidList.size();
		} else {
			rfqBidList = new ArrayList();
		}

		if (b_submit_access || b_bids_entered) {
%>
	<tr>
		<td width=35% nowrap class=itemSummary align=right><b>My Bid:</b></td>
		<td class=itemSummary nowrap>
			<table border=0 class=itemSummary cellpadding=0 cellspacing=0>
			<tr>
				<td class=itemSummary nowrap>
<%		for (int ibr = 0; ibr < i_bidRows; ibr++) {
				RfqBid rfqBid = (RfqBid) rfqBidList.get(ibr);
				if (user.getVendorId().equalsIgnoreCase(rfqBid.getComp_id().getVendorId())) {
					b_bids_entered = true;
%>
					<tsa:hidden name="db_unit_price" value="<%=HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid)%>"/>
					<tsa:hidden name="RfqBid_unitPrice" value="<%=HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid)%>"/>
					<tsa:hidden name="RfqBid_bidCode" value="<%=rfqBid.getBidCode()%>"/>
					<tsa:hidden name="RfqBid_icRfqHeader" value="<%=rfqLine.getIcRfqHeader()%>"/>
					<tsa:hidden name="RfqBid_icRfqLine" value="<%=rfqLine.getIcRfqLine()%>"/>
					<tsa:hidden name="RfqBid_vendorId" value="<%=user.getVendorId()%>"/>
			<%	if (b_submit_access) {%>
					<input type=text name="unit_price" value="<%=HiltonUtility.getFormattedPrice(rfqBid.getUnitPrice(), oid)%>" size=20 tabIndex=<%=ir+1%> onChange="updateBids(<%=ir%>);" onFocus="stopMonitor();" style="text-align:right">
			<%	} else {%>
					<input type=text name="unit_price" value="<%=HiltonUtility.getFormattedPrice(rfqBid.getUnitPrice(), oid)%>" size=20 onChange="updateBids(<%=ir%>);" onFocus="stopMonitor();" style="text-align:right; border:none;'" disabled>
			<%	}
				break;
				}
			}

			if (!b_bids_entered) {%>
					<tsa:hidden name="db_unit_price" value="0.00"/>
					<tsa:hidden name="RfqBid_unitPrice" value="0.00"/>
					<tsa:hidden name="RfqBid_bidCode" value="NE"/>
					<tsa:hidden name="RfqBid_icRfqHeader" value="<%=rfqLine.getIcRfqHeader()%>"/>
					<tsa:hidden name="RfqBid_icRfqLine" value="<%=rfqLine.getIcRfqLine()%>"/>
					<tsa:hidden name="RfqBid_vendorId" value="<%=user.getVendorId()%>"/>
					<input type=text name="unit_price" value="0.00" size=20 tabIndex=<%=ir+1%> onChange="updateBids(<%=ir%>);" onFocus="stopMonitor();" style="text-align:right">
<%		}%>
				</td>
				<td class=itemSummary nowrap>
					<a href="javascript: void(0)" onClick="stopMonitor(); selectBidCodes(<%=ir%>,event)"><img src="<%=contextPath%>/supplierportal/images/dropdown.gif" border=0></a>&nbsp;
				</td>
			</tr>
			</table>
			<tsa:hidden name="rfb_item_selected" value=""/>
			<tsa:hidden name="lowest_bid" value="<%=rfqLine.getLowestBid()%>"/>
			<tsa:hidden name="average_bid" value="<%=rfqLine.getAverageBid()%>"/>
		</td>
	</tr>
	<tr>
		<td nowrap class=itemSummary align=right><b>My Total Bid:</b></td>
		<td class=itemSummary><input type=text name="ext_price" value="" size=20 disabled style="text-align:right"></td>
	</tr>
<%	if (rfqHeader.getAllowProxyBids().equalsIgnoreCase("Y")) {%>
	<tr>
		<td class=itemSummary align=right><input type=checkbox name="proxybid" value=""></td>
		<td class=itemSummary><b>Proxy Bid</b></td>
	</tr>
<%	}%>
<%		if (b_openauction && b_show_low_amt && !b_low_subtotal && (b_bids_entered || !b_require_entry)) {
				bd_low_bid = HiltonUtility.getFormattedDollar(rfqLine.getLowestBid(), oid);
%>
	<tr>
		<td nowrap class=itemSummary align=right><B>Lowest Bid:</B></td>
		<td class=itemSummary>
			<table border=0 cellpadding=0 cellspacing=0 width=100%>
			<tr>
<%			if (bd_low_bid.compareTo(new BigDecimal("0.00000")) <= 0) {%>
				<td class=itemSummary>No Bids</td>
<%			} else {%>
				<td class=itemSummary><%=bd_low_bid%>&nbsp;</td>
<%			}%>
			</tr>
			</table>
		</td>
	</tr>
<%		} else if (b_openauction && !b_low_subtotal && (b_bids_entered || !b_require_entry) && ( rfqLine.getLowestVendorId().equalsIgnoreCase(user.getVendorId()))) {%>
	<tr><td nowrap class=itemSummary><font class=error>Lowest Bid</font></td></tr>
<%		}
		}%>
<!--	RFQ BID ROWS END	-->