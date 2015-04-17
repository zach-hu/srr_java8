<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="java.math.BigDecimal" %>
<%
	RfqHeader	rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>
<tsa:hidden name="qaEventPage" value="/rfq/rfq_qa_event.jsp"/>
<tsa:hidden name="bidEventPage" value="/rfq/rfq_summary.jsp"/>
<tsa:hidden name="auctionEventPage" value="/rfq/rfq_auction.jsp"/>
<tsa:hidden name="closedEventPage" value="/rfq/rfq_summary_postauction.jsp"/>

<table border=0 width=680px cellpadding=0 cellspacing=0 valign=top>
<tr>
	<td align=center>
		<!-- RFQ HEADER -->
		<table border=0 cellspacing=0 cellpadding=0 height=100%>
		<tr>
			<td valign=middle class=hdr12 height=20px align=center>
				<%=RfqType.toString(rfqHeader.getRfqType(), oid)%> # <%=rfqHeader.getRfqNumber()%>
<%	if (!HiltonUtility.isEmpty(rfqHeader.getRfqAmendment())) {%>
				<br>Amendment # <%=rfqHeader.getRfqAmendment()%>
<%	}%>
			</td>
		</tr>
		</table>
		<table border=0 cellspacing=0 cellpadding=2 height=100%>
		<tr>
			<td class=formType>Online Question & Answer Event Time Remaining <%=HiltonUtility.getFullDateTimeString(rfqHeader.getQaEndDate(), rfqHeader.getQaEndTime(), rfqHeader.getTimeZone(), user.getOrganizationId())%></td>
		</tr>
		</table>
		<!-- RFQ HEADER END -->
	</td>
</tr>
</table>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Question Entry Form</div>
			</td>
		</tr>
		</table>
	</td>
	<td width=30px valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" width="30" height="31" /></td>
	<td valign=bottom align=right height=30px>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr>
			<td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table cellpadding=0 cellspacing=0 border=0 width=680px>
<tr>
	<td align=center valign=center>
		<table cellpadding=2 cellspacing=0 border=0 align=center>
		<!--tr>
			<td align=right valign=top class=label>Question Title:</td>
			<td><input type=text name="VendorQuestion_questionTitle" value="" size=55 maxLength=60>
		</tr-->
		<tr>
			<td align=right valign=top class=label>Question:</td>
			<td>
				<textarea name="VendorQuestion_questionText" wrap="virtual" rows=8 cols=55></textarea>
				<tsa:hidden name="VendorQuestion_icRfqHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>
				<tsa:hidden name="VendorQuestion_vendorId" value="<%=user.getVendorId()%>"/>
			</td>
		</tr>
		</table>
		<br>
		<br>
		<table border=0 width=100%>
		<tr>
			<td align=center width=50%>
				<a href="javascript: doSubmit('', 'VendorQuestionAdd;RfqRetrieve;SetEventPage'); returnToQaEvent(); void(0);"><img tabindex=30 src="<%=contextPath%>/supplierportal/images/button_submit.gif" class=button border=0></a>
			</td>
			<td align=center width=50%>
				<a href="javascript: returnToQaEvent(); void(0);"><img tabindex=31 src="<%=contextPath%>/supplierportal/images/button_cancel.gif" class=button border=0></a>
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

	function validateForm() {
		return true;
	}

	function returnToQaEvent() {
		doSubmit('','RfqRetrieve;SetEventPage');
	}

// end hiding contents -->
</SCRIPT>

</body>
</html>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>