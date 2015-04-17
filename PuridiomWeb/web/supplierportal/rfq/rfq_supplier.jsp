<%	String	dateFormat = propertiesManager.getProperty("MISC", "DateFormat", "yyyy-MM-dd");	%>

			<table border=0 cellpadding=0 cellspacing=0 class=summary width=100%>
			<tr height=28px>
				<td align=right class=summary>Promise Date:</td>
				<td>
					<input type=text name="RfqVendor_datePromised" tabindex=100 size=15 maxlength=15 value="" onChange="checkDate(this); setAction('sup','UPDATE');" onFocus="stopMonitor();">
					<a href="javascript: show_calendar('RfqVendor_datePromised', '<%=dateFormat%>');"><IMG src="<%=contextPath%>/supplierportal/images/calendar.gif" valign=bottom border=0></a>
				</td>
				<td align=right class=summary height=28px><a href="javascript: void(0);" onClick="browseStd('RfqVendor_fob', 'FOB');">Shipping Terms</a>:</td>
				<td><input type=text name="RfqVendor_fob" tabindex=102 size=15 maxlength=15 value="" onChange="upperCase(this); setAction('sup','UPDATE');" onFocus="stopMonitor();"></td>
				<td align=right class=summary>Ship Amt:</td>
				<td><input type=text name="RfqVendor_shippingCharges" tabindex=104 size=15 maxlength=15 value="" onChange="formatPrice(this); setAction('sup','UPDATE');" onFocus="stopMonitor();" style="text-align:right"></td>
			</tr>
			<tr height=28px>
				<td align=right class=summary>Bid Valid To:</td>
				<td>
					<input type=text name="RfqVendor_bidValidTo" tabindex=101 size=15 maxlength=15 value="" onChange="checkDate(this); setAction('sup','UPDATE');" onFocus="stopMonitor();">
					<a href="javascript: show_calendar('RfqVendor_bidValidTo', '<%=dateFormat%>');"><IMG src="<%=contextPath%>/supplierportal/images/calendar.gif" valign=bottom border=0></a>
				</td>
				<td align=right class=summary height=28px><a href="javascript: void(0);" onClick="browseLookup('RfqVendor_paymentTerms', 'payment-terms');">Payment Terms</a>:</td>
				<td><input type=text name="RfqVendor_paymentTerms" tabindex=103 size=15 maxlength=15 value="" onChange="upperCase(this); setAction('sup','UPDATE');" onFocus="stopMonitor();"></td>
				<td align=right class=summary>Tax Amt:</td>
				<td><input type=text name="RfqVendor_taxAmount" tabindex=105 size=15 maxlength=15 value="" onChange="formatPrice(this); setAction('sup','UPDATE');" onFocus="stopMonitor();" style="text-align:right"></td>
			</tr>
			</table>

			<tsa:hidden name="RfqVendor_vendorId" value="<%=user.getVendorId()%>"/>
			<tsa:hidden name="RfqVendor_contactId" value="<%=user.getContactCode()%>"/>
			<tsa:hidden name="RfqVendor_contactName" value="<%=user.getDisplayName()%>"/>
			<tsa:hidden name="RfqVendor_addressCode" value="<%=user.getAddressCode()%>"/>
			<tsa:hidden name="RfqVendor_icRfqHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>
			<tsa:hidden name="RfqVendor_bidsEntered" value="<%=rfqVendor.getBidsEntered()%>"/>
			<tsa:hidden name="allowBrowse" value="true"/>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

<%	if (b_exists) {%>
			frm.RfqVendor_datePromised.value="<%=HiltonUtility.getFormattedDate(rfqVendor.getDatePromised(), oid)%>";
			frm.RfqVendor_paymentTerms.value="<%=rfqVendor.getPaymentTerms()%>";
			frm.RfqVendor_bidValidTo.value="<%=HiltonUtility.getFormattedDate(rfqVendor.getBidValidTo(),oid)%>";
			frm.RfqVendor_shippingCharges.value="<%=HiltonUtility.getFormattedDollar(rfqVendor.getShippingCharges(),oid)%>";
			frm.RfqVendor_fob.value="<%=rfqVendor.getFob()%>";
			frm.RfqVendor_taxAmount.value="<%=HiltonUtility.getFormattedDollar(rfqVendor.getTaxAmount(),oid)%>";
<%	}%>
	// end hiding contents -->
</SCRIPT>