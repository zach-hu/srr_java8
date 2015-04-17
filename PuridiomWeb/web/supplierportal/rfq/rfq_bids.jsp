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
			else if (rfqHeader.getLowestVendorId().equalsIgnoreCase(user.getVendorId())) {
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
								<td class=browseHdrDk><%=s_line_number%>.&nbsp;ITEM # <%=rfqLine.getItemNumber()%></td>
<%		if (b_submit_access) { %>
								<td nowrap class=browseHdrDk align=right nowrap>
									<table border=0 cellpadding=0 cellspacing=0 class=browseHdrDk>
									<tr>
<%			if (b_graph_access) { %>
										<td align=right valign=top><a href="javascript:stopMonitor(); viewBidHistoryItemAll('<%=rfqLine.getIcRfqLine()%>','<%=rfqLine.getItemNumber()%>'); void(0);" class=browseHdrDk><img src="<%=contextPath%>/supplierportal/images/history.gif" border=0></a></td>
										<td width=5px></td>
										<td valign=middle><a href="javascript: stopMonitor(); viewBidHistoryItemAll('<%=rfqLine.getIcRfqLine()%>','<%=rfqLine.getItemNumber()%>'); void(0);" class=browseHdrDk>View Bid History (Totals)</a></td>
										<td width=5px></td>
										<td align=right valign=top><a href="javascript:stopMonitor(); viewBidHistoryItem('<%=rfqLine.getIcRfqLine()%>','<%=rfqLine.getItemNumber()%>'); void(0);" class=browseHdrDk><img src="<%=contextPath%>/supplierportal/images/history.gif" border=0></a></td>
										<td width=5px></td>
										<td valign=middle><a href="javascript: stopMonitor(); viewBidHistoryItem('<%=rfqLine.getIcRfqLine()%>','<%=rfqLine.getItemNumber()%>'); void(0);" class=browseHdrDk>View Bid History</a></td>
										<td width=5px></td>
<%			} %>
										<td align=right valign=top><a href="javascript: stopMonitor(); openNotes('<%=rfqLine.getIcRfqLine()%>', '<%=s_line_number%>', 'Y'); void(0);" class=browseHdrDk><img src="<%=contextPath%>/supplierportal/images/notes_ln.gif" border=0></a></td>
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
							<%@ include file="/supplierportal/rfq/rfq_line_comments.jsp" %>
							<tr>
								<td class=itemSummary align=right><b>Commodity:</b></td>
								<td class=itemSummary><%=rfqLine.getCommodity()%>&nbsp;</td>
							</tr>
							<tr>
								<td class=itemSummary vAlign=top width=75px align=right><b>Description:</b></td>
								<td class=itemSummary vAlign=top><%=s_description%>&nbsp;</td>
							</tr>
<%		if (!HiltonUtility.isEmpty(s_after_lncomment)) {%>
							<tr>
								<td class=itemSummary vAlign=top align=right><b>Comments:</b></td>
								<td class=itemSummary vAlign=top><%=s_after_lncomment%></td>
							</tr>
<%		} %>
							<tr>
								<td class=itemSummary valign=top colspan=2>
									<table border=0 cellspacing=0 cellpadding=1 class=itemSummary>
<%
				List shipToList = (List) rfqLine.getShipToList();
				if (shipToList != null)
				{
					boolean	b_display_ship_to  = false;
					for (int i = 0;i < shipToList.size(); i++)
					{
						ShipTo shipTo = (ShipTo) shipToList.get(i);
						ShipToPK shipToPK = shipTo.getComp_id();
						String	s_shp_code = shipToPK.getShipToCode();
						String	s_ship_address_line_1 = "";
						String	s_ship_address_line_2 = "";
						String	s_ship_city = "";
						String	s_ship_state = "";
						String	s_ship_postal_code = "";
						String	s_ship_country = "";

						Address shipToAddress = (Address) shipTo.getShipToAddress();
						if (shipToAddress != null)
						{
							s_ship_address_line_1 = shipToAddress.getAddressLine1();
							s_ship_address_line_2 = shipToAddress.getAddressLine2();
							s_ship_city = shipToAddress.getCity();
							s_ship_state = shipToAddress.getState();
							s_ship_postal_code = shipToAddress.getPostalCode();
							s_ship_country = shipToAddress.getCountry();
						}
%>
				<%	if (!b_display_ship_to) {
							b_display_ship_to = true;	%>
									<tr><td class=itemSummary colspan=3>&nbsp;</td></tr>
									<tr><td class=itemSummary colspan=3><b><u>Shipping Schedule</u></b></td></tr>
				<%	} %>
									<tr>
										<td class=itemSummary valign=top>
				<%	if (!HiltonUtility.isEmpty(s_ship_address_line_1)) { %><b><%=s_ship_address_line_1%></b><br><% } %>
				<%	if (!HiltonUtility.isEmpty(s_ship_address_line_2)) { %><%=s_ship_address_line_2%><br><% } %>
				<%	if (!HiltonUtility.isEmpty(s_ship_city)) { %><%=s_ship_city%>, <% } %>
				<%	if (!HiltonUtility.isEmpty(s_ship_state)) { %><%=s_ship_state%> <% } %>
				<%	if (!HiltonUtility.isEmpty(s_ship_postal_code)) { %><%=s_ship_postal_code%> <% } %>
				<%	if (!HiltonUtility.isEmpty(s_ship_country)) { %><%=s_ship_country%><% } %>
										</td>
										<td class=itemSummary valign=top width=15px>&nbsp;</td>
										<td class=itemSummary valign=top>
											<b>Quantity:</b> <%=HiltonUtility.getFormattedQuantity(shipTo.getQuantity(), user.getOrganizationId())%><br>
											<b>Required Date:</b> <%=HiltonUtility.getFormattedDate(shipTo.getShipDate(), user.getOrganizationId())%>
										</td>
									</tr>
<%				}
				}
%>
									</table>
								</td>
							</tr>
							</table>
						</td>
						<td width=42% class=itemSummary valign=top>
							<table border=0 class=itemSummary width=100% cellpadding=0 cellspacing=3>
							<tr>
								<td nowrap class=itemSummary align=right><b>Quantity: </b></td>
								<td class=itemSummary>
									<%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), user.getOrganizationId())%>&nbsp;<% if (!HiltonUtility.isEmpty(s_uom_code)) { %>(<%=s_uom_code%>)<% } %>
									<tsa:hidden name="qty" value="<%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), user.getOrganizationId())%>"/>
									<tsa:hidden name="um_factor" value="<%=rfqLine.getUmFactor()%>"/>
								</td>
							</tr>
							<%@ include file="/supplierportal/rfq/rfq_bid_rows.jsp" %>
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
		doSubmitToLookup('/rfq/supplier_bidHistory.jsp', 'DoNothing');
}
</script>