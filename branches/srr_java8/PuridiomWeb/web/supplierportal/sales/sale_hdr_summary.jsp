<!-- RFQ HEADER SUMMARY -->
<%
	/*declare and set ship to address variables*/
	Address shipto = saleHeader.getShipToAddress();
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

	UserProfile buyer = com.tsa.puridiom.usermanager.UserManager.getInstance().getUser(oid, saleHeader.getContact());
	if (buyer == null) {
		buyer = new UserProfile();
	}
%>
<tr height=28px>
	<td  height=28px width=15% align=right nowrap><b>Posted Date:</b> </td>
	<td  width=15% nowrap><%=HiltonUtility.getFormattedDate(saleHeader.getAppDate(), user.getOrganizationId())%></td>
	<td  width=12% align=right nowrap><b>Bids Due:</b> </td>
	<td  width=18% nowrap><%=s_due%></td>
	<td  width=15% align=right>
		<table border=0 cellpadding=0 cellspacing=0 class=summary>
		<tr>
			<td align=right>
	<%	if (!HiltonUtility.isEmpty(buyer.getMailId()) && (!HiltonUtility.isEmpty(propertiesManager.getProperty("MAILEVENTS", "AdminEmailAddr","")))) {%>
			<a href="javascript:emailBuyer(); void(0);">
				<img src="<%=contextPath%>/supplierportal/images/email.gif" align=right border=0 alt="Click here to email the contact for this sale.">
			</a>
	<%	} else {%>&nbsp;
	<%	}%>
			</td>
			<td><b>Contact:</b></td>
		</tr>
		</table>
	</td>
	<td  width=25% nowrap>&nbsp;<%=buyer.getDisplayName()%>
		<tsa:hidden name="buyer_code" value="<%=saleHeader.getBuyer()%>"/>
		<tsa:hidden name="buyer_name" value="<%=buyer.getDisplayName()%>"/>
		<tsa:hidden name="buyer_email" value="<%=buyer.getMailId()%>"/>
	</td>
</tr>
<tr height=28px>
	<td  valign=top align=right nowrap><b>Item Location:</b></td>
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

<!-- RFQ HEADER SUMMARY END -->

