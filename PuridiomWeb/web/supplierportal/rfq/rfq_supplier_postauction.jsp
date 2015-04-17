<%	if (b_exists) {%>
			<table border=0 cellpadding=2 cellspacing=0 class=summary width=100%>
			<tr height=28px>
				<td class=summary width=16% align=right><b>Promise Date:</b></td>
				<td class=summary width=17%><%=HiltonUtility.getFormattedDate(rfqVendor.getDatePromised(), oid)%></td>
				<td class=summary width=17% align=right><b>Shipping Terms</b>:</td>
				<td class=summary width=17%><%=rfqVendor.getFob()%></td>
				<td class=summary width=16% align=right><b>Ship Amt:</td>
				<td class=summary width=17%><%=HiltonUtility.getFormattedDollar(rfqVendor.getShippingCharges(),oid)%></td>
			</tr>
			<tr height=28px>
				<td class=summary align=right><b>Bid Valid To:</td>
				<td class=summary><%=HiltonUtility.getFormattedDate(rfqVendor.getBidValidTo(),oid)%></td>
				<td class=summary align=right><b>Terms:</b></td>
				<td class=summary><%=rfqVendor.getPaymentTerms()%></td>
				<td class=summary align=right><b>Tax Amt:</b></td>
				<td class=summary><%=HiltonUtility.getFormattedDollar(rfqVendor.getTaxAmount(),oid)%></td>
			</tr>
			</table>

			<tsa:hidden name="RfqVendor_vendorId" value="<%=user.getVendorId()%>"/>
			<tsa:hidden name="RfqVendor_icRfqHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>
			<tsa:hidden name="allowBrowse" value="false"/>
<%	}%>