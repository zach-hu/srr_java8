<table border=0 width=100% cellpadding=0 cellspacing=0>
	<tr>
		<td width=50% valign=top>
		<table border=0 width=100% cellpadding=0 cellspacing=0>
			<tr>
				<tsa:td field="rfq-bidResponse" align="right"><a href="javascript: browseStd('RfqVendor_bidResponseCode', 'RESP'); void(0);" title="Click here to choose the <tsa:label labelName="rfq-bidResponse" defaultString="Bid Response" /> for this supplier or enter the <tsa:label labelName="rfq-bidResponse" defaultString="Bid Response" /> in the box on the right."><tsa:label labelName="rfq-bidResponse" defaultString="Bid Response" checkRequired="true" /></a>:&nbsp;</tsa:td>
				<tsa:td field="rfq-bidResponse"><tsa:input type="mintext" name="RfqVendor_bidResponseCode" maxLength="15" tabIndex="2" value="<%=rfqVendor.getBidResponseCode()%>" onchange="upperCase(this);" /></tsa:td>
			</tr>
			<tr>
				<tsa:td field="rfq-fob" align="right"><A HREF="javascript: browseStd('RfqVendor_fob', 'FOB'); void(0);" title="Click here to choose the <tsa:label labelName="rfq-fob" defaultString="FOB" /> for this supplier or enter the <tsa:label labelName="rfq-fob" defaultString="FOB" /> in the box on the right."><tsa:label labelName="rfq-fob" defaultString="FOB" checkRequired="true" /></a>:&nbsp;</tsa:td>
				<tsa:td field="rfq-fob"><tsa:input type="mintext" name="RfqVendor_fob" maxLength="15" tabIndex="4" value="<%=rfqVendor.getFob()%>" onchange="upperCase(this);" /></tsa:td>
			</tr>
			<tr>
				<tsa:td field="rfq-paymentTerms" align="right"><A HREF="javascript: browseLookup('RfqVendor_paymentTerms', 'payment-terms'); void(0);" title="Click here to choose the <tsa:label labelName="rfq-paymentTerms" defaultString="Terms" /> for this supplier or enter the <tsa:label labelName="rfq-paymentTerms" defaultString="Terms" /> in the box on the right."><tsa:label labelName="rfq-paymentTerms" defaultString="Terms" checkRequired="true" /></a>:&nbsp;</tsa:td>
				<tsa:td field="rfq-paymentTerms"><tsa:input type="mintext" name="RfqVendor_paymentTerms" maxLength="15" tabIndex="6" value="<%=rfqVendor.getPaymentTerms()%>" onchange="upperCase(this);" /></tsa:td>
			</tr>
			<tr>
				<tsa:td field="rfq-contact" align="right"><A HREF="javascript: browseContactAddress('RfqVendor_contactId',frm.RfqVendor_vendorId.value);" title="Click here to choose the <tsa:label labelName="rfq-contact" defaultString="Contact" /> for this supplier or enter the <tsa:label labelName="rfq-contact" defaultString="Contact" /> in the box on the right."><tsa:label labelName="rfq-contact" defaultString="Contact" checkRequired="true" /></a>:&nbsp;</tsa:td>
				<tsa:td field="rfq-contact"><tsa:input type="mintext" name="RfqVendor_contactId" maxLength="15" tabIndex="8" value="<%=rfqVendor.getContactId()%>" onchange="upperCase(this);" /><tsa:hidden name="RfqVendor_contactName" value="<%=rfqVendor.getContactName()%>"/></tsa:td>
			</tr>
			<tr>
				<tsa:td field="rfq-address" align="right"><A HREF="javascript: browseContactAddress('RfqVendor_contactId',frm.RfqVendor_vendorId.value);" title="Click here to choose the <tsa:label labelName="rfq-address" defaultString="Address" /> for the supplier contact or enter the <tsa:label labelName="rfq-address" defaultString="Address" /> in the box on the right."><tsa:label labelName="rfq-address" defaultString="Address" checkRequired="true" /></a>:&nbsp;</tsa:td>
				<tsa:td field="rfq-address"><tsa:input type="mintext" name="RfqVendor_addressCode" maxLength="15" tabIndex="10" value="<%=rfqVendor.getAddressCode()%>" onchange="upperCase(this);" /></tsa:td>
			</tr>
			<tr>
				<tsa:td field="rfq-edi" align="right"><tsa:label labelName="rfq-edi" defaultString="EDI" />:&nbsp;</tsa:td>
				<tsa:td field="rfq-edi"><input TYPE="CHECKBOX" name="c_checkbox" size=15 tabindex=12 <% if (rfqVendor.getEdiRfq().indexOf("Y")>= 0){ %> CHECKED <%}%> value="" onclick="setEdi();"> <tsa:hidden name="RfqVendor_ediRfq" value="<%=rfqVendor.getEdiRfq()%>"/></tsa:td>
			</tr>
			<tr>
				<tsa:td field="rfq-emailAddress" align="right"><tsa:label labelName="rfq-emailAddress" defaultString="Email Address" checkRequired="true" /></a>:&nbsp;</tsa:td>
				<tsa:td field="rfq-emailAddress"><tsa:input type="mintext" name="RfqContact_emailAddr" maxLength="15" tabIndex="6" value="<%=emailAddr%>" /></tsa:td>
			</tr>
			<tr>
				<tsa:td field="rfq-mobileNumber" align="right"><tsa:label labelName="rfq-mobileNumber" defaultString="Mobile Number" checkRequired="true" /></a>:&nbsp;</tsa:td>
				<tsa:td field="rfq-mobileNumber"><tsa:input type="mintext" name="RfqContact_mobileNumber" maxLength="15" tabIndex="6" value="<%=mobileNumber%>" /></tsa:td>
			</tr>
		</table>
		</td>
		<td width=50% valign=top>
		<table border=0 width=100% cellpadding=0 cellspacing=0>
			<tr>
				<tsa:td field="rfq-currencyln" align="right"><a href="javascript: browseLookup('RfqVendor_vendCurrency', 'curr_code'); void(0);" title="Click here to choose the <tsa:label labelName="rfq-currencyln" defaultString="Currency" /> for this supplier or enter the <tsa:label labelName="rfq-currencyln" defaultString="Currency" /> in the box on the right."><tsa:label labelName="rfq-currencyln" defaultString="Currency" checkRequired="true" /></a>:&nbsp;</tsa:td>
				<tsa:td field="rfq-currencyln"><tsa:input type="mintext" name="RfqVendor_vendCurrency" maxLength="15" tabIndex="14" value="<%=rfqVendor.getVendCurrency()%>" onchange="upperCase(this);" /></tsa:td>
			</tr>
			<tr>
				<tsa:td field="rfq-shippingCharges" align="right"><tsa:label labelName="rfq-shippingCharges" defaultString="Shipping" />:&nbsp;</tsa:td>
				<tsa:td field="rfq-shippingCharges"><input type="text" name="RfqVendor_shippingCharges" size=15 tabindex=16 value=<%if(locked) { %> "" <%} else { %>"<%=HiltonUtility.getFormattedDollar(rfqVendor.getShippingCharges(), oid)%>"<% } %> style="text-align: right" onchange="formatPrice(this); " <%if(locked) { %> disabled <% } %>></tsa:td>
			</tr>
			<tr>
				<tsa:td field="rfq-otherCharges" align="right"><tsa:label labelName="rfq-otherCharges" defaultString="Other" />:&nbsp;</tsa:td>
				<tsa:td field="rfq-otherCharges"><input type=text name="RfqVendor_otherCharges" size=15 tabindex=17 value=<%if(locked) { %> "" <%} else { %>"<%=HiltonUtility.getFormattedDollar(rfqVendor.getOtherCharges(), oid)%>"<% } %> style="text-align: right" onchange="formatPrice(this);" <%if(locked) { %> disabled <% } %>></tsa:td>
			</tr>
			<tr>
				<tsa:td field="rfq-taxAmount" align="right"><tsa:label labelName="rfq-taxAmount" defaultString="Tax" />:&nbsp;</tsa:td>
				<tsa:td field="rfq-taxAmount"><input type=text name="RfqVendor_taxAmount" size=15 tabindex=18 value=<%if(locked) { %> "" <%} else { %>"<%=HiltonUtility.getFormattedDollar(rfqVendor.getTaxAmount(), oid)%>"<% } %> style="text-align: right" onchange="formatPrice(this);" <%if(locked) { %> disableds <% } %>></tsa:td>
			</tr>
			<tr>
				<tsa:td field="rfq-responseDate" align="right"><tsa:label labelName="rfq-responseDate" defaultString="Response Date" checkRequired="true" />:&nbsp;</tsa:td>
				<tsa:td field="rfq-responseDate"><input type=text name="RfqVendor_dateResponseRecv" size=15 tabindex=20 value="<%=HiltonUtility.getFormattedDate(rfqVendor.getDateResponseRecv(), oid, userDateFormat)%>" onchange="checkDate(this);"> <a href="javascript: show_calendar('RfqVendor_dateResponseRecv', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to find a Date or enter a Date in the box on the left." valign=bottom border=0></a></tsa:td>
			</tr>
			<tr>
				<tsa:td field="rfq-promisedDate" align="right"><tsa:label labelName="rfq-promisedDate" defaultString="Promise Date" checkRequired="true" />:&nbsp;</tsa:td>
				<tsa:td field="rfq-promisedDate">
				<%
					if (rfqVendor.getDatePromised() == null)
					{
				%> <tsa:input type="mintext" name="RfqVendor_datePromised" maxLength="15" tabIndex="22" value="<%=HiltonUtility.getFormattedDate(rfqHeader.getDueDate(), oid, userDateFormat)%>" onchange="checkDate(this);" /> <%
 	} else
 	{
 %> <tsa:input type="mintext" name="RfqVendor_datePromised" maxLength="15" tabIndex="22" value="<%=HiltonUtility.getFormattedDate(rfqVendor.getDatePromised(), oid, userDateFormat)%>" onchange="checkDate(this);" /> <%
 	}
 %> <a href="javascript: show_calendar('RfqVendor_datePromised', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to find a Date or enter a Date in the box on the left." valign=bottom border=0></a></tsa:td>
			</tr>
			<tr>
				<tsa:td field="rfq-bidValidTo" align="right"><tsa:label labelName="rfq-bidValidTo" defaultString="Bid Valid To" checkRequired="true" />:&nbsp;</tsa:td>
				<tsa:td field="rfq-bidValidTo">
				<%
					if (rfqVendor.getBidValidTo() == null)
					{
				%> <tsa:input type="mintext" name="RfqVendor_bidValidTo" maxLength="15" tabIndex="22" value="<%=HiltonUtility.getFormattedDate(rfqHeader.getDueDate(), oid, userDateFormat)%>" onchange="checkDate(this);" /> <%
 	} else
 	{
 %> <tsa:input type="mintext" name="RfqVendor_bidValidTo" maxLength="15" tabIndex="24" value="<%=HiltonUtility.getFormattedDate(rfqVendor.getBidValidTo(), oid, userDateFormat)%>" onchange="checkDate(this);" /> <%
 	}
 %> <a href="javascript: show_calendar('RfqVendor_bidValidTo', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to find a Date or enter a Date in the box on the left." valign=bottom border=0></a>
 				</tsa:td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<tsa:hidden name="RfqVendor_bidsEntered" value="<%=rfqVendor.getBidsEntered()%>"/>
<tsa:hidden name="allowBrowse" value="true"/>

