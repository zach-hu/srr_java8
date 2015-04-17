<!-- RFQ BIDS -->
	<table border=0 cellpadding=0 cellspacing=0 width=650px>
<%
		List lineList = (List) rfqHeader.getRfqLineList();
		int	i_lineRows = 0;
		
		if (lineList!=null){
			i_lineRows = lineList.size();
		}
			
		String	s_msg = "";
		String	s_description = "";
		BigDecimal bd_low_bid = new BigDecimal(0);
		
		if (b_low_subtotal && b_show_low_amt) {
			if (b_show_low_amt) {
				bd_low_bid = HiltonUtility.getFormattedDollar(rfqHeader.getLowestBidSubtotal(), oid);
				if (bd_low_bid.compareTo(new BigDecimal("0.00000")) == 1) {
					s_msg = "Lowest Subtotal Bid: " + bd_low_bid.toString();
				}
			}
			else if (HiltonUtility.ckNull(rfqHeader.getLowestVendorId()).equalsIgnoreCase(user.getVendorId())) {
				s_msg = "You are currently the lowest bidder for this solicitation!";
			}
		}

		if (s_msg.length() > 0) {
%>
	<tr><td><br></td></tr>
	<tr><td align=center class=error><%=s_msg%></td></tr>
<%	}%>
	<tr><td><br></td></tr>
	<tr>
		<td align=center>	
<%	for ( int ir = 0; ir < i_lineRows; ir++ ) {
			RfqLine rfqLine = (RfqLine) lineList.get(ir);
			String	s_uom_code = rfqLine.getUmCode();
			bd_lowbid  = HiltonUtility.getFormattedDollar(rfqLine.getLowestBid(), oid);
			
			if (HiltonUtility.isEmpty(s_uom_code)) {
				s_uom_code = "EACH";
			}
			
			s_description = rfqLine.getDescription();
			
			int idx = s_description.indexOf("\n");
			while (idx >= 0) {
				s_description = s_description.substring(0, idx) + "<br>" + s_description.substring(idx + 1, s_description.length());
				idx = s_description.indexOf("\n");
			}
%>
			<table border=1 cellpadding=2 cellspacing=1 width=650px>
			<tr>
				<td class=browseHdrDk colspan=2><%=rfqLine.getRfqLine().setScale(0, BigDecimal.ROUND_HALF_UP)%>.&nbsp;
				ITEM # <%=rfqLine.getItemNumber()%></td>
			</tr>
			<tr class=itemSummary>
				<td width=50% valign=top class=itemSummary>
					<table border=0 class=itemSummary cellpadding=1 cellspacing=2 vAlign=middle>
					<tr><td class=itemSummary align=right valign=top><b>Commodity:</b></td><td valign=top><%=rfqLine.getCommodity()%></td></tr>
					<tr><td class=itemSummary align=right valign=top><b>Description:</b></td><td valign=top><%=s_description%></td></tr>
					</table>
				</td>
				<td width=50% vAlign=top class=itemSummary>
					<table border=0 class=itemSummary width=100% cellpadding=1 cellspacing=2 vAlign=middle>
					<tr>
						<td width=30% nowrap class=itemSummary align=right><b>Quantity: </b></td>
						<td width=35% class=itemSummary>
							<%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), user.getOrganizationId())%>&nbsp;(<%=s_uom_code%>)
							<tsa:hidden name="qty" value="<%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), user.getOrganizationId())%>"/>
							<tsa:hidden name="um_factor" value="<%=rfqLine.getUmFactor()%>"/>
						</td>
						<td width=35%></td>
					</tr>
				<!--	RFQ BID ROWS	-->
				<%
						List	rfqBidList = rfqLine.getRfqBidList();
						String	bidCode = "";
						int	i_bidRows = 0;
					
						if (rfqBidList != null) {
							i_bidRows = rfqBidList.size();
						}%>
					<tr>
						<td nowrap class=itemSummary align=right><b>My Bid: </b></td>
						<td class=itemSummary nowrap>
			<%		for (int ibr = 0; ibr < i_bidRows; ibr++) {
							RfqBid rfqBid = (RfqBid) rfqBidList.get(ibr);
							if (user.getVendorId().equalsIgnoreCase(rfqBid.getComp_id().getVendorId())) {
								bd_thisbid = HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid);
								bidCode = rfqBid.getBidCode();
								
								if (bidCode.equals("NC")) {%>No Charge
						<%	} else if (bidCode.equals("SN")) {%>See Notes
						<%	} else if (bidCode.equals("NSP")) {%>Not Separately Priced
						<%	} else if (bidCode.equals("NB")) {%>No Bid
						<%	} else if (bidCode.equals("NE")) {%>&nbsp;-
						<%	} else {%><%=bd_thisbid%>
						<%	}%>
							<tsa:hidden name="unit_price" value="<%=rfqBid.getUnitPrice()%>"/>
						<%	break;
							}
						}%>
						</td>
						<td align=right nowrap>
			<%		if (b_openauction && !b_low_subtotal) {
							if (bd_lowbid.equals(bd_thisbid) && ( bd_lowbid.compareTo(new BigDecimal(0)) > 0 ) ) {
								b_lowestbid = true; %>
							<font class=error><b>Lowest Bid</b></font>
			<%			} else {%>
							<font class=error><b>Not Lowest Bid</b></font>					
			<%			}
						}%>
						</td>
					</tr>
					<tr>
						<td nowrap class=itemSummary align=right><b>My Total Bid:</b></td>
						<td class=itemSummary><div id="myTotalBid">&nbsp;</div>
							<tsa:hidden name="RfqBid_bidCode" value="<%=bidCode%>"/>
							<tsa:hidden name="ext_price" value=""/>
						</td>
					</tr>
				<%	if (!HiltonUtility.isEmpty(rfqLine.getRfqNote())) {%>
					<tr>
						<td nowrap class=itemSummary align=right valign=top><b>My Item Notes: </b></td>
						<td class=itemSummary colspan=2 valign=top><%=rfqLine.getRfqNote()%></td>
					</tr>
				<%	}%>
				<!--	RFQ BID ROWS END	-->
					</table>
				</td>
			</tr>
			</table>
<%	}%>
		</td>
	</tr>
	</table>
	<div id="dummyFields" style="visibility: hidden; display: none;">
	<tsa:hidden name="qty" value=""/>
	<tsa:hidden name="um_factor" value=""/>
	<tsa:hidden name="unit_price" value=""/>
	<tsa:hidden name="ext_price" value=""/>
	<tsa:hidden name="RfqBid_unitPrice" value=""/>
	<tsa:hidden name="RfqBid_bidCode" value=""/>
	</div>
<!-- RFQ BIDS END -->