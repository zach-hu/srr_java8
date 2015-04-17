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
	boolean	b_indicate_lowest = rfqHeader.getCaIndicateLowest().equals("Y");
	boolean	b_display_comment = false;
	boolean	b_display_ship_to  = false;
	BigDecimal bd_low_bid = new BigDecimal(0);
	
	if (b_low_subtotal && (b_bids_entered || !b_require_entry ) && b_indicate_lowest) {
		if (b_show_low_amt) {
			bd_low_bid = HiltonUtility.getFormattedDollar(rfqHeader.getLowestBidSubtotal(), oid);
			if (bd_low_bid.compareTo(new BigDecimal("0.00000")) == 1) {
				s_msg = "Lowest Subtotal Bid: " + bd_low_bid.toString();
			}
		}
		else if (rfqHeader.getLowestVendorId().equalsIgnoreCase(user.getVendorId())) {
			s_msg = "You were the lowest bidder for this solicitation!";
		}
		else {
			s_msg = "You were NOT the lowest bidder for this solicitation!";
		}
	}

	if (b_winning_vendor && b_low_subtotal) {
		if (s_msg.length() > 0) {
			s_msg = "Winning Vendor:  " + VendorManager.getInstance().getVendorName(oid, rfqHeader.getLowestVendorId()) + "<br>" + s_msg;
		} else {
			s_msg = "Winning Vendor:  " + VendorManager.getInstance().getVendorName(oid, rfqHeader.getLowestVendorId());
		}
	}

	if (s_msg.length() > 0) {
%>
	<tr><td><br></td></tr>
	<tr><td align=center><b><%=s_msg%></b></td></tr>
<%	}%>
	<tr><td><br></td></tr>
	<tr>
		<td align=center>
			<table border=0 cellpadding=0 cellspacing=0>
			<tr>
				<td class=summary width=5%>&nbsp;</td>
				<td class=summary>
<%
		int	rows = i_lineRows;

		for ( int ir = 0; ir < i_lineRows; ir++ )
		{
			RfqLine rfqLine = (RfqLine) lineList.get(ir);
			s_description = rfqLine.getDescription();
			
			int idx = s_description.indexOf("\n");
			while (idx >= 0) {
				s_description = s_description.substring(0, idx) + "<br>" + s_description.substring(idx + 1, s_description.length());
				idx = s_description.indexOf("\n");
			}
%>
					<table border=1 width=650px>
					<tr>
						<td class=browseHdrDk colspan=2><%=rfqLine.getRfqLine().setScale(0, BigDecimal.ROUND_HALF_UP)%>.&nbsp;
						ITEM # <%=rfqLine.getItemNumber()%></td>
					</tr>
					<tr class=itemSummary>
						<td width=55% valign=top class=itemSummary>
							<table border=0 class=itemSummary width=100% cellpadding=1 cellspacing=0>
							<%@ include file="/supplierportal/rfq/rfq_line_comments.jsp" %>
							<tr>
								<td width=75px class=itemSummary align=right><b>Commodity:</b></td>
								<td class=itemSummary><%=rfqLine.getCommodity()%>&nbsp;</td>
							</tr>
							<tr>
								<td class=itemSummary vAlign=top width=75px align=right><b>Description:</b></td>
								<td class=itemSummary vAlign=top><%=s_description%>&nbsp;</td>
							</tr>
<%		if (!HiltonUtility.isEmpty(s_after_lncomment)) {%>
							<tr><td class=itemSummary colspan=2><br></td></tr>
							<tr>
								<td class=itemSummary vAlign=top width=80px align=right><b>Comments:</b></td>
								<td class=itemSummary vAlign=top><%=s_after_lncomment%></td>
							</tr>
							<tr><td class=itemSummary colspan=2><br></td></tr>
<%		}%>
							<tr>
								<td class=itemSummary valign=top colspan=2>
									<table border=0 class=itemSummary>
<%
				List shipToList = (List) rfqLine.getShipToList();
				if (shipToList != null)
				{
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
									<tr>
										<td class=itemSummary valign=top>
<%					if (!b_display_ship_to){%>
											<b>Shipping Schedule:</b>
<%					}%>
					<%if (b_display_ship_to){%><br><%}%><br>
					<%if (!HiltonUtility.isEmpty(s_ship_address_line_1)) {%><%=s_ship_address_line_1%><br><%}%>
					<%if (!HiltonUtility.isEmpty(s_ship_address_line_2)) {%><%=s_ship_address_line_2%><br><%}%>
					<%if (!HiltonUtility.isEmpty(s_ship_city)) {%><%=s_ship_city%>, <%}%>
					<%if (!HiltonUtility.isEmpty(s_ship_state)) {%><%=s_ship_state%> <%}%>
					<%if (!HiltonUtility.isEmpty(s_ship_postal_code)) {%><%=s_ship_postal_code%> <%}%>
					<%if (!HiltonUtility.isEmpty(s_ship_country)) {%><%=s_ship_country%><%}%>
										</td>
										<td class=itemSummary valign=top WIDTH="15px">&nbsp;</td>
										<td class=itemSummary valign=top>
											<%if (b_display_ship_to){%><br><%}%><br><b>Quantity:</b> <%=HiltonUtility.getFormattedQuantity(shipTo.getQuantity(), user.getOrganizationId())%><br>
											<b>Required Date:</b> <%=HiltonUtility.getFormattedDate(shipTo.getShipDate(), user.getOrganizationId())%>
										</td>
									</tr>
<%					b_display_ship_to = true;
				}
			}

			b_display_ship_to = false;
%>
									</table>
								</td>
							</tr>
							</table>
						</td>
						<td width=45% class=itemSummary valign=top>
							<table border=0 class=itemSummary width=100% cellpadding=1 cellspacing=2>
							<tr>
								<td with=35% nowrap class=itemSummary align=right><b>Quantity: </b></td>
								<td class=itemSummary>
									<%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), user.getOrganizationId())%>&nbsp;<% if (!HiltonUtility.isEmpty(rfqLine.getUmCode())) {%>(<%=rfqLine.getUmCode()%>)<%}%>
									<tsa:hidden name="qty" value="<%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), user.getOrganizationId())%>"/>
									<tsa:hidden name="um_factor" value="<%=rfqLine.getUmFactor()%>"/>
								</td>
							</tr>
							<%@ include file="/supplierportal/rfq/rfq_bid_rows_postauction.jsp" %>
							</table>
						</td>
					</tr>
					</table>
<%			if (ir < rows - 1) {%>
						<br>
<%			}
		}%>
					<div id="dummyFields" style="visibility: hidden; display: none;">
					<tsa:hidden name="qty" value=""/>
					<tsa:hidden name="um_factor" value=""/>
					<tsa:hidden name="unit_price" value=""/>
					<tsa:hidden name="ext_price" value=""/>
					<tsa:hidden name="RfqBid_unitPrice" value=""/>
					<tsa:hidden name="db_unit_price" value=""/>
					<tsa:hidden name="RfqBid_bidCode" value=""/>
					<tsa:hidden name="rfb_item_selected" value=""/>
					</div>
				</td>
				<td class=summary width=5%>&nbsp;</td>
			</tr>
			<tr><td class=summary colspan=3></td></tr>
			</table>
		</td>
	</tr>
<%	if (s_msg.length() > 0) {%>
	<tr><td ><br></td></tr>
	<tr><td align=center><b><%=s_msg%></b></td></tr>
<%	}%>
	<tr><td><br></td></tr>
	</table>
<!-- RFQ BIDS END -->