<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(user.getOrganizationId());
	RfqHeader	rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	List	questionList = rfqHeader.getVendorQuestionList();
	int i_qstrows = 0;

	if (questionList != null) {
		i_qstrows = questionList.size();
	}
	
	String	dueDateTimeString = HiltonUtility.getFullDateTimeString(rfqHeader.getDueDate(), rfqHeader.getBidDueTime(), rfqHeader.getTimeZone(), user.getOrganizationId());
	String	qaStartEvent = HiltonUtility.getFullDateTimeString(rfqHeader.getQaStartDate(), rfqHeader.getQaStartTime(), rfqHeader.getTimeZone(), user.getOrganizationId());
	String	qaEndEvent = HiltonUtility.getFullDateTimeString(rfqHeader.getQaEndDate(), rfqHeader.getQaEndTime(), rfqHeader.getTimeZone(), user.getOrganizationId());
	String	bidStartEvent = HiltonUtility.getFullDateTimeString(rfqHeader.getBidStartDate(), rfqHeader.getBidStartTime(), rfqHeader.getTimeZone(), user.getOrganizationId());
	String	bidEndEvent = HiltonUtility.getFullDateTimeString(rfqHeader.getBidEndDate(), rfqHeader.getBidEndTime(), rfqHeader.getTimeZone(), user.getOrganizationId());
	String	auctionStartEvent = HiltonUtility.getFullDateTimeString(rfqHeader.getAuctionStartDate(), rfqHeader.getAuctionStartTime(), rfqHeader.getTimeZone(), user.getOrganizationId());
	String	auctionEndEvent = HiltonUtility.getFullDateTimeString(rfqHeader.getAuctionEndDate(), rfqHeader.getAuctionEndTime(), rfqHeader.getTimeZone(), user.getOrganizationId());
	
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>

<!-- RFQ EVENT SCHEDULE HEADER -->
<table border=0 width=680px cellpadding=0 cellspacing=0 valign=top>
<tr>
	<td align=center colspan=4>
		<table border=0 cellspacing=0 cellpadding=1 height=100%>
		<tr><td align=center class=hdr12>Solicitation Event Schedule</td></tr>
		</table>
		<table border=0 cellspacing=0 cellpadding=2 height=100%>
		<tr><td class=formType>Solicitation Closes on <%=dueDateTimeString%></td></tr>
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

	</td>
	<td width=15px>&nbsp;</td>
</tr>
<tr><td></td><td colspan=2><hr size=0 width=100% class=browseHR></td><td></td></tr>
</table>
<!-- RFQ EVENT SCHEDULE HEADER END -->

<table border=0 width=680px valign=top>
<tr>
	<td valign=top align=center>
		<table border=0 cellpadding=2 cellspacing=0>
<%	if (rfqHeader.getEventPaused().equalsIgnoreCase("Y")) {%>
		<tr><td colspan=2><br></td></tr>
		<tr>
			<td align=center class=error colspan=2>The current event for this solicitation has been paused.<br>You will be notified once the event is restarted.</td>
		</tr>
<%	}
		if (rfqHeader.getQaEvent().equalsIgnoreCase("Y")) {%>
		<tr><td colspan=2><br></td></tr>
		<tr>
			<td align=right class=formType>Question & Answer Event:</td>
			<td>From&nbsp;<b><%=qaStartEvent%></b> to <b><%=qaEndEvent%></b></td>
		</tr>
<%	}
		if (rfqHeader.getBidEvent().equalsIgnoreCase("Y")) {%>
		<tr><td colspan=2><br></td></tr>
		<tr>
			<td align=right class=formType>Opening Bid Event:</td>
			<td>From&nbsp;<b><%=bidStartEvent%></b> to <b><%=bidEndEvent%></b></td>
		</tr>
<%		RfqVendor currentRfqVendor = (RfqVendor) request.getAttribute("currentVendor");
			if (currentRfqVendor != null && currentRfqVendor.getBidsEntered().equalsIgnoreCase("Y")) {
			%>
		<tr>
			<td>&nbsp;</td>
			<td>Opening bid has been received.  Thank you!</td>
		</tr>
<%		}
		}
		if (rfqHeader.getAuctionEvent().equalsIgnoreCase("Y")) {%>
		<tr><td colspan=2><br></td></tr>
		<tr>
			<td align=right class=formType>Open Auction Event:</td>
			<td>From&nbsp;<b><%=auctionStartEvent%></b> to <b><%=auctionEndEvent%></b></td>
		</tr>
<%	}%>
		</table>
		<br>
		<table border=0 width=100%>
		<tr>
			<td align=center width=50%>
				<a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><img tabindex=31 src="<%=contextPath%>/supplierportal/images/button_cancel.gif" class=button border=0></a>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	
// end hiding contents -->
</SCRIPT>

</body>
</html>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>