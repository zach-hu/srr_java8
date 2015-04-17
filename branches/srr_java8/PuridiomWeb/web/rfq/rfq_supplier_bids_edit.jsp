<!-- RFQ BIDS -->
	<table border=0 cellpadding=0 cellspacing=0 class=summary width=650px>
<%

		List lineList = (List) rfqHeader.getRfqLineList();
		int	i_lineRows = 0;

		if (lineList!=null){
			i_lineRows = lineList.size();
		}

		String	s_msg = "";
		String	s_description = "";
		BigDecimal bd_low_bid = new BigDecimal(0);

		if (b_low_subtotal && (b_bids_entered || !b_require_entry )) {
			if (b_show_low_amt) {
				bd_low_bid = HiltonUtility.getFormattedDollar(rfqHeader.getLowestBidSubtotal(), oid);
				if (bd_low_bid.compareTo(new BigDecimal("0.00000")) == 1) {
					s_msg = "Lowest Subtotal Bid: " + bd_low_bid.toString();
				}
			}
			else if (rfqHeader.getLowestVendorId().equalsIgnoreCase(currentVendorId)) {
				s_msg = "You are currently the lowest bidder for this solicitation!";
			}
			else {
				s_msg = "You are currently NOT the lowest bidder for this solicitation!";
			}
		}

		if (s_msg.length() > 0) {
%>
	<tr><td><br></td></tr>
	<tr><td align=center><b><%=s_msg%></b></td></tr>
<%	} %>
	<tr><td><br></td></tr>
	<tr>
		<td align=center>
			<table border=0 cellpadding=0 cellspacing=0>
			<tr>
				<td class=summary width=5%>&nbsp;</td>
				<td class=summary>

<%	for ( int ir = 0; ir < i_lineRows; ir++ ) {
			RfqLine rfqLine = (RfqLine)lineList.get(ir);
			String	s_uom_code = rfqLine.getUmCode();
			String	s_line_number = String.valueOf(rfqLine.getRfqLine().setScale(0, BigDecimal.ROUND_HALF_UP));

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
					<tsa:hidden name="rfqItemNumber" value="<%=rfqLine.getItemNumber()%>"/>
					<tsa:hidden name="rfqItemDescription" value="<%=rfqLine.getDescription()%>"/>

					<table border=1 width=650px>
					<tr>
						<td class=browseHdrDk colspan=2>
							<table border=0 cellpadding=0 cellspacing=0 width=100% class=browseHdrDk>
							<tr>
								<td class=browseHdrDk><%=s_line_number%>.&nbsp;<tsa:label labelName="rfq-Item_number" defaultString="ITEM #" /> <%=rfqLine.getItemNumber()%></td>
<%		if (b_submit_access) { %>
								<td nowrap class=browseHdrDk align=right nowrap>
									<table border=0 cellpadding=0 cellspacing=0 class=browseHdrDk>
									<tr>
<%			if (b_graph_access) { %>
										<td align=right valign=top><a href="javascript:stopMonitor(); viewBidHistoryItemAll('<%=rfqLine.getIcRfqLine()%>','<%=rfqLine.getItemNumber()%>'); void(0);" class=browseHdrDk><img src="<%=contextPath%>/images/history.gif" border=0></a></td>
										<td width=5px></td>
										<td valign=middle><a href="javascript: stopMonitor(); viewBidHistoryItemAll('<%=rfqLine.getIcRfqLine()%>','<%=rfqLine.getItemNumber()%>'); void(0);" class=browseHdrDk>View Bid History (Totals)</a></td>
										<td width=5px></td>
										<td align=right valign=top><a href="javascript:stopMonitor(); viewBidHistoryItem('<%=rfqLine.getIcRfqLine()%>','<%=rfqLine.getItemNumber()%>'); void(0);" class=browseHdrDk><img src="<%=contextPath%>/images/history.gif" border=0></a></td>
										<td width=5px></td>
										<td valign=middle><a href="javascript: stopMonitor(); viewBidHistoryItem('<%=rfqLine.getIcRfqLine()%>','<%=rfqLine.getItemNumber()%>'); void(0);" class=browseHdrDk>View Bid History</a></td>
										<td width=5px></td>
<%			} %>
										<td align=right valign=top><a href="javascript: stopMonitor(); openNotes('<%=rfqLine.getIcRfqLine()%>', '<%=s_line_number%>', 'Y'); void(0);" class=browseHdrDk><img src="<%=contextPath%>/images/notes_line.gif" border=0></a></td>
										<td width=5px></td>
										<td valign=middle><a href="javascript: stopMonitor(); openNotes('<%=rfqLine.getIcRfqLine()%>', '<%=s_line_number%>', 'Y'); void(0);" class=browseHdrDk>Add/Edit Notes</a></td>
									</tr>
									</table>
								</td>
<%		}%>
							</tr>
							</table>
						</td>
					</tr>
					<tr class=itemSummary>
						<td width=58% valign=top class=itemSummary>
							<table border=0 class=itemSummary width=100% cellpadding=1 cellspacing=0>
							<tr>
								<td class=itemSummary align=right><b><tsa:label labelName="rfq-Commodity" defaultString="Commodity" />:</b></td>
								<td class=itemSummary><%=rfqLine.getCommodity()%>&nbsp;</td>
							</tr>
							<tr>
								<td class=itemSummary vAlign=top width=75px align=right><b><tsa:label labelName="rfq_description" defaultString="Description" />:</b></td>
								<td class=itemSummary vAlign=top><%=s_description%>&nbsp;</td>
							</tr>
							</table>
						</td>
						<td width=42% class=itemSummary valign=top>
							<table border=0 class=itemSummary width=100% cellpadding=0 cellspacing=3>
							<tr>
								<td nowrap class=itemSummary align=right><b><tsa:label labelName="rfq-Quantity" defaultString="Quantity" />: </b></td>
								<td class=itemSummary>
									<%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), oid)%>&nbsp;<% if (!HiltonUtility.isEmpty(s_uom_code)) { %>(<%=s_uom_code%>)<% } %>
									<tsa:hidden name="qty" value="<%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), oid)%>"/>
									<tsa:hidden name="um_factor" value="<%=rfqLine.getUmFactor()%>"/>
								</td>
							</tr>
							<%@ include file="/rfq/rfq_bid_rows.jsp" %>
							</table>
						</td>
					</tr>
					</table>
<%	}%>
					<div id="dummyFields" style="visibility: hidden; display: none;">
					<tsa:hidden name="qty" value=""/>
					<tsa:hidden name="um_factor" value=""/>
					<tsa:hidden name="unit_price" value=""/>
					<tsa:hidden name="ext_price" value=""/>
					<tsa:hidden name="RfqBid_unitPrice" value=""/>
					<tsa:hidden name="db_unit_price" value=""/>
					<tsa:hidden name="RfqBid_bidCode" value=""/>
					<tsa:hidden name="rfb_item_selected" value=""/>
					<tsa:hidden name="average_bid" value=""/>
					<tsa:hidden name="RfqBid_icRfqLine" value=""/>
					<tsa:hidden name="RfqBid_vendorId" value=""/>
					</div>
					<div id="myBid" style="visibility:hidden; display: none;"></div>
					<div id="myTotalBid" style="visibility:hidden; display: none;"></div>
				</td>
				<td class=summary width=5%>&nbsp;</td>
			</tr>
			</table>
		</td>
	</tr>
<%	if (s_msg.length() > 0) {%>
	<tr><td ><br></td></tr>
	<tr><td align=center><b><%=s_msg%></b></td></tr>
<%	}%>
	</table>
<!-- RFQ BIDS END -->



<SCRIPT LANGUAGE="JavaScript1.2">

function viewBidHistory(item)
{
		popupParameters="bidItemNumber="+item;
		doSubmitToPopup('/rfq/supplier_bidHistory.jsp', 'DoNothing');
}
</script>
