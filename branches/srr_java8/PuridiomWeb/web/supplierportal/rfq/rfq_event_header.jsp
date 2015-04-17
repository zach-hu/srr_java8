<!-- RFQ EVENT HEADER -->
<table border=0 width=680px cellpadding=0 cellspacing=0 valign=top>
<tr>
	<td align=center colspan=4>
		<table border=0 cellspacing=0 cellpadding=1 height=100%>
		<tr><td align=center class=hdr12><%=eventType%> Event</td></tr>
		</table>
		<table border=0 cellspacing=0 cellpadding=2 height=100%>
		<tr><td class=formType>Closes on <%=dueDateTimeString%></td></tr>
		</table>
	</td>
</tr>
<tr><td></td><td colspan=2><hr size=0 width=100% class=browseHR></td><td></td></tr>
<tr>
	<td width=15px>&nbsp;</td>
	<td valign=top>
		<table border=0 cellspacing=0 cellpadding=1 height=100%>
		<tr>
			<td valign=middle class=menuSubTitle height=20px align=right><%=RfqType.toString(rfqHeader.getRfqType(), oid)%> #</td>
			<td class=formType><%=rfqHeader.getRfqNumber()%></td>
		</tr>
<%	if (!HiltonUtility.isEmpty(rfqHeader.getRfqAmendment()) && !rfqHeader.getRfqAmendment().equals("0")) {%>
		<tr>
			<td valign=middle class=menuSubTitle height=20px align=right>Amendment #</td>
			<td class=formType><%=rfqHeader.getRfqAmendment()%></td>
		</tr>
<%	}%>
		</table>
	</td>
	<td align=right valign=top>
		<table border=0 cellspacing=0 cellpadding=2>
		<tr>
			<td class=formType id="auctionCountdownText" align=right>Event Time Remaining:</td>
			<td><%@ include file="/supplierportal/rfq/rfq_qa_time_remaining.jsp"%></td>
		</tr>
		</table>
	</td>
	<td width=15px>&nbsp;</td>
</tr>
<tr><td></td><td colspan=2><hr size=0 width=100% class=browseHR></td><td></td></tr>
</table>
<!-- RFQ EVENT HEADER END -->