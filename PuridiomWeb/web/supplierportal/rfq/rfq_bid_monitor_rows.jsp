<%
	String	s_unit_price	= "";
	String	s_bid_code	= "";
	String	s_bidder	= "";
	String	s_list = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String	rowClass = "''";
	Map tempBidders = new HashMap();
	
	for (int ilr=0; ilr < iLineRows; ilr++) {
		RfqLine rfqLine = (RfqLine) rfqLineList.get(ilr);
		List	rfqBidList = (List) rfqLine.getRfqBidList();
		int	iBidRows = 0;
		int	iBidsDisplayed = 0;
		int	ind = 0;
		
		if (rfqBidList != null) {
			iBidRows = rfqBidList.size();
			iTotalBidRows = iTotalBidRows + iBidRows;
		} else {
			rfqBidList = new ArrayList();
		}
		if (ilr == 0) {
%>
					<tr class=summary>
						<td class=summary width=100% align=center>
							<table border=0 cellpadding=2 cellspacing=0 width=100%>
							<tr>
								<td nowrap class=browseHdrDk>Line</td>
								<td nowrap class=browseHdrDk>Item Number</td>
								<td nowrap class=browseHdrDk width=300px>Description</td>
								<td nowrap align=right class=browseHdrDk>Unit Price</td>
								<td class=browseHdrDk width=5px>&nbsp;</td>
								<td nowrap align=center class=browseHdrDk>Bidder</td>
							</tr>
<%	} else {%>
							</tr><!-- ilr > 0 -->
							<tr><td colspan=6 height=1px><img src="<%=contextPath%>/supplierportal/images/none.gif" border=0 height=1px width=100% class=browseHdrDK></td></tr>
<%	}
		if (rowClass.equals("itemSummary")) {
			rowClass = "summary";
		} else {
			rowClass = "''";
		}%>
							<tr class=<%=rowClass%>>
								<td nowrap class=<%=rowClass%> align=right><%=rfqLine.getRfqLine().setScale(0, BigDecimal.ROUND_HALF_UP)%>.&nbsp;</td>
								<td nowrap class=<%=rowClass%>><%=rfqLine.getItemNumber()%>&nbsp;<tsa:hidden name="rfqItemNumber" value="<%=rfqLine.getItemNumber()%>"><tsa:hidden name="rfqItemDescription" value="<%=rfqLine.getDescription()%>"/></td>
								<td nowrap class=<%=rowClass%>><div style="width:300px; text-overflow:ellipsis; overflow:hidden;'"><%=rfqLine.getDescription()%>&nbsp;</td>
<%
		boolean lineInfoDisplayed = false;
		boolean currentVendorBidsDisplayed = false;

//		if (!vendorBidsEntered.containsKey(user.getVendorId()) && !rfqHeader.getBidEvent().equalsIgnoreCase("Y")) {
		if (!vendorBidsEntered.containsKey(user.getVendorId())) {
			RfqBid tempRfqBid = new RfqBid();
			RfqBidPK tempRfqBidPK = new RfqBidPK();
			tempRfqBidPK.setIcRfqHeader(rfqHeader.getIcRfqHeader());
			tempRfqBidPK.setIcRfqLine(rfqLine.getIcRfqLine());
			tempRfqBidPK.setVendorId(user.getVendorId());
			tempRfqBid.setComp_id(tempRfqBidPK);
			
			rfqBidList.add(tempRfqBid);
			iBidRows++;
		}

		for (int ib = 0; ib < iBidRows; ib++) {
			RfqBid rfqBid = (RfqBid) rfqBidList.get(ib);
			String	bidderVendorId = rfqBid.getComp_id().getVendorId();

//			if ( user.getVendorId().equalsIgnoreCase(bidderVendorId) && ((vendorBidsEntered.containsKey(bidderVendorId) && vendorBidsEntered.get(bidderVendorId).equals("Y")) || !rfqHeader.getBidEvent().equalsIgnoreCase("Y"))) {
			if ( user.getVendorId().equalsIgnoreCase(bidderVendorId)) {
				currentVendorBidsDisplayed = true;
				s_bidder = user.getVendorName();
				if (HiltonUtility.isEmpty(s_bidder)) {
					s_bidder = bidderVendorId;
				}
			} else {
				if (!vendorBidsEntered.get(bidderVendorId).equals("Y")) {
					// do not show records for vendors that have not yet entered bids
					continue;
				}
				if (tempBidders.containsKey(bidderVendorId)) {
					s_bidder = (String) tempBidders.get(bidderVendorId);
				} else {
					if (ind > 26) {
						s_bidder = s_list.substring(ind - 27, (ind - 27) + 1);
						s_bidder = s_bidder + s_bidder;
					} else {
						s_bidder = s_list.substring(ind, ind + 1);
					}
					ind++;
					
					tempBidders.put(bidderVendorId, s_bidder);
				}
			}
			
			if ((ib + 1) == iBidRows && !currentVendorBidsDisplayed) {
				RfqBid tempRfqBid = new RfqBid();
				RfqBidPK tempRfqBidPK = new RfqBidPK();
				tempRfqBidPK.setIcRfqHeader(rfqHeader.getIcRfqHeader());
				tempRfqBidPK.setIcRfqLine(rfqLine.getIcRfqLine());
				tempRfqBidPK.setVendorId(user.getVendorId());
				tempRfqBid.setComp_id(tempRfqBidPK);
				
				rfqBidList.add(tempRfqBid);
				iBidRows++;
			}
			
			s_bid_code = rfqBid.getBidCode();

			if (s_bid_code.equals("NC")) {
				s_unit_price = "No Charge";
			}
			else if (s_bid_code.equals("NSP")) {
				s_unit_price = "Not Separately Priced";
			}
			else if (s_bid_code.equals("NB")) {
				s_unit_price = "No Bid";
			}
			else if (s_bid_code.equals("SN")) {
				s_unit_price = "See Notes";
			}
			else if (s_bid_code.equals("NE")) {
				s_unit_price = "None Entered";
			}
			else {
				bd_quantity	= HiltonUtility.getFormattedQuantity(rfqBid.getQuantity(), oid);
				bd_unit_price	=  HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid);
				bd_uom_factor	= new BigDecimal (1.00);
//				bd_ext_price	= ( (bd_quantity.multiply(bd_unit_price)).multiply(bd_uom_factor) ).setScale(Integer.valueOf(ss_dollar_decimals).intValue());

				if (bd_unit_price.compareTo(new BigDecimal(0)) == 0) {
					s_unit_price = "None Entered";
				} else {
					s_unit_price = bd_unit_price.toString();
				}
			}
%>
<%		if (iBidsDisplayed > 0) {%>
							</tr><!-- ib > 0 -->
							<tr class=<%=rowClass%>>
								<td class=<%=rowClass%> colspan=3>&nbsp;</td>
<%		}%>
								<td nowrap class=<%=rowClass%> align=right><% if (s_bidder.equals(bidderVendorId)) {%><b><%}%><%=s_unit_price%><% if (s_bidder.equals(bidderVendorId)) {%></b><%}%></td>
<%		if (bidderVendorId.equals(user.getVendorId()) && b_show_bid_edit) {%>
								<td nowrap class=<%=rowClass%>><a href="javascript: editBid(<%=ilr%>); void(0);"><img src="<%=contextPath%>/supplierportal//images/cmd_edit.gif" border=0></a></td>
<%		} else {%>
								<td nowrap class=<%=rowClass%>>&nbsp;</td>
<%		}%>
								<td nowrap class=<%=rowClass%> align=center><%=s_bidder%>&nbsp;</td>
<%		iBidsDisplayed++;
		}
	}%>
		
<%	if (iTotalBidRows > 0) {%>
							</tr><!-- end table -->
							</table>
						</td>
					</tr>
<%	}%>