<!-- RFQ HEADER SUMMARY -->
<%
	/*declare and set ship to address variables*/
	Address shipto = rfqHeader.getShipToAddress();
	if (shipto == null) {
		shipto = new Address();
	}
	StringBuffer	sbAddress = new StringBuffer();
	if ( !HiltonUtility.isEmpty(shipto.getAddressLine1()) ) {
		sbAddress.append(shipto.getAddressLine1() + "<br>");
	}
	if ( !HiltonUtility.isEmpty(shipto.getAddressLine2()) ) {
		sbAddress.append(shipto.getAddressLine2() + "<br>");
	}
	if ( !HiltonUtility.isEmpty(shipto.getCity()) ) {
		if ( !HiltonUtility.isEmpty(shipto.getState()) ) {
			sbAddress.append(shipto.getCity() + ", " + shipto.getState() + "  " + shipto.getPostalCode());
		} else {
			sbAddress.append(shipto.getCity() + "  " + shipto.getPostalCode());
		}
	}
	else if ( !HiltonUtility.isEmpty(shipto.getState()) || !HiltonUtility.isEmpty(shipto.getPostalCode()) ) {
		sbAddress.append(shipto.getState() + "  " + shipto.getPostalCode());
	}
	if ( !HiltonUtility.isEmpty(shipto.getCountry()) ) {
		sbAddress.append( "<br>" + shipto.getCountry());
	}

	UserProfile buyer = com.tsa.puridiom.usermanager.UserManager.getInstance().getUser(oid, rfqHeader.getBuyer());
	if (buyer == null) {
		buyer = new UserProfile();
	}
%>
<tr height=28px>
	<td  height=28px width=15% align=right nowrap><b>Posted Date:</b> </td>
	<td  width=15% nowrap><%=HiltonUtility.getFormattedDate(rfqHeader.getAppDate(), user.getOrganizationId())%></td>
	<td  width=12% align=right nowrap><b>Bids Due:</b> </td>
	<td  width=18% nowrap><%=s_due%></td>
	<td  width=15% align=right>
		<table border=0 cellpadding=0 cellspacing=0 class=summary>
		<tr>
			<td align=right>
	<%	if (b_allow_email && !HiltonUtility.isEmpty(buyer.getMailId()) && (!HiltonUtility.isEmpty(propertiesManager.getProperty("MAILEVENTS", "AdminEmailAddr","")))) {%>
			<a href="javascript:emailBuyer(); void(0);">
				<img src="<%=contextPath%>/supplierportal/images/email.gif" align=right border=0 alt="Click here to email the buyer.">
			</a>
	<%	} else {%>&nbsp;
	<%	}%>
			</td>
			<td><b>Buyer:</b></td>
		</tr>
		</table>
	</td>
	<td  width=25% nowrap>&nbsp;<%=buyer.getDisplayName()%>
		<tsa:hidden name="buyer_code" value="<%=rfqHeader.getBuyer()%>"/>
		<tsa:hidden name="buyer_name" value="<%=buyer.getDisplayName()%>"/>
		<tsa:hidden name="buyer_email" value="<%=buyer.getMailId()%>"/>
	</td>
</tr>
<tr height=28px>
	<td  valign=top align=right><b>Ship To:</b></td>
	<td  valign=top colspan=3><%=sbAddress%></td>
	<td  valign=top align=right>
		<table border=0 cellpadding=0 cellspacing=0 class=summary  valign=top>
		<tr height=20px><td valign=top align=right><b>Phone:</b></td></tr>
		<tr height=20px><td valign=top align=right><b>Fax:</b></td></tr>
		</table>
	</td>
	<td  valign=top nowrap>
		<table border=0 cellpadding=0 cellspacing=0 class=summary  valign=top>
		<tr height=20px><td valign=top align=right><%=buyer.getPhoneNumber()%></td></tr>
		<tr height=20px><td valign=top align=right><%=buyer.getFaxNumber()%></td></tr>
		</table>
	</td>
</tr>
<%	if (!HiltonUtility.isEmpty(rfqHeader.getShippingCode() + rfqHeader.getCurrencyCode())) {%>
<tr height=28px>
	<%	if (!HiltonUtility.isEmpty(rfqHeader.getShippingCode())) {%>
	<td  valign=top align=right><b>Ship Via:</b></td>
	<td  valign=top colspan=3><%=rfqHeader.getShippingCode()%></td>
	<%	} else {%>
	<td  colspan=4>&nbsp;</td>
	<%	}
			if (!HiltonUtility.isEmpty(rfqHeader.getCurrencyCode())) {%>
	<td  valign=top align=right><b>Currency:</b></td>
	<td  valign=top><%=rfqHeader.getCurrencyCode()%></td>
	<%	} else {%>
	<td  colspan=2>&nbsp;</td>
	<%	}%>
</tr>
<%	}
		String	s_synopsis = rfqHeader.getRfqDescription();
		int	i_idx = s_synopsis.indexOf("\n");

		while (i_idx >= 0)
		{
			s_synopsis = s_synopsis.substring(0, i_idx) + "<br>" + s_synopsis.substring(i_idx + 1, s_synopsis.length());
			i_idx = s_synopsis.indexOf("\n");
		}
%>
<tr height=28px>
	<td  height=28px align=right valign=top><b>Description:</b> </td>
	<td  colspan=6 valign=top>
		<table border=0 cellpadding=0 cellspacing=0 width=100% class=summary>
		<tr>
			<td valign=top><%=s_synopsis%></td>
			<td valign=top  align=right>
				<table border=0 class=summary>
				<tr>
					<td  align=center valign=bottom class=summary>
<%	if (b_submit_access && propertiesManager.getProperty("RFQ OPTIONS", "SupplierDocs", "N").equals("Y")) {%>
						<a href="javascript: uploadDocs(); void(0);"><img name="img_attach" src="<%=contextPath%>/supplierportal/images/clip_lg.gif" border=0 valign=top alt="Upload Response Documents"><br><b>&nbsp;Upload&nbsp;</b></a>
<%	}%>
					</td>
<%	if (b_submit_access && i_questions > 0) {%>
					<td >&nbsp;</td>
					<td  align=center valign=bottom>
						<a href="javascript: questions(); void(0);"><img name="img_question" src="<%=contextPath%>/supplierportal/images/img_question.gif" border=0 valign=top><br><b>Questions</b></a>
					</td>
<%	}
		if (b_allow_monitor && b_submit_access && b_openauction) {%>
					<td >&nbsp;</td>
					<td  align=center valign=bottom>
						<a href="javascript: if (canMonitor()) {startMonitor();} void(0);"><img name="img_monitor" <%if (s_monitor.equals("Y")){%>src="<%=contextPath%>/supplierportal/images/stop_monitor.gif"<%}else{%>src="<%=contextPath%>/supplierportal/images/start_monitor.gif"<%}%> border=0 valign=top><br><b>Monitor</b></a>
					</td>
<%	}
		if ( (b_download_access) && (s_webpost.indexOf("D") >= 0) && !HiltonUtility.isEmpty(rfqHeader.getPostFilename()) ) {%>
					<td >&nbsp;</td>
					<td  align=center valign=bottom>
						<a href="javascript: stopMonitor(); download('<%=rfqHeader.getPostFilename()%>'); void(0);"><img src="<%=contextPath%>/supplierportal/images/download.gif" border=0><br><b>Download</b></a>
					</td>
<%	}
		if (b_submit_access) {%>
					<td >&nbsp;</td>
					<td  align=center valign=bottom>
						<a href="javascript: stopMonitor(); openNotes('0', '', 'Y'); void(0);"><img src="<%=contextPath%>/supplierportal/images/notes.gif" border=0 valign=top><br><b>Notes</b></a>
					</td>
<%	}
		if (b_submit_access) {%>
					<!--td >&nbsp;</td>
					<td  align=center valign=bottom>
						<a href="javascript: stopMonitor(); viewBidHistory('0', '', 'Y'); void(0);"><img src="<%=contextPath%>/supplierportal/images/notes.gif" border=0 valign=top><br><b>History</b></a>
					</td-->
<%	}%>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>

<!-- RFQ HEADER SUMMARY END -->
