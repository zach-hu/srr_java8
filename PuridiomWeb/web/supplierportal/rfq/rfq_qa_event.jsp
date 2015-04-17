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
	
	String	eventType = "Question & Answer";
	String	dueDate = HiltonUtility.getFormattedDate(rfqHeader.getQaEndDate(), user.getOrganizationId());
	String	dueTime = rfqHeader.getQaEndTime();
	String	dueDateTimeString = HiltonUtility.getFullDateTimeString(rfqHeader.getQaEndDate(), rfqHeader.getQaEndTime(), rfqHeader.getTimeZone(), user.getOrganizationId());
%>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/timer.js"></SCRIPT>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>

<%@ include file="/supplierportal/rfq/rfq_event_header.jsp" %>

<table border=0 width=680px valign=top>
<tr>
	<td valign=top align=center>
		<table border=0 width=100%>
		<tr><td align=center><a href="javascript: reviewRfq(); void(0);">Review Solicitation</td></tr>
		</table>
		<%@ include file="/supplierportal/rfq/rfq_qa_list.jsp" %>
		<br>
		<table border=0 width=100%>
		<tr>
			<td align=center width=50%>
				<a href="javascript: doSubmit('/rfq/supplier_question_form.jsp', 'RfqRetrieve'); void(0);"><img tabindex=30 src="<%=contextPath%>/supplierportal/images/button_add.gif" class=button border=0></a>
			</td>
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

	var qstRows = <%=i_qstrows%>;
	
	function thisLoad() {
		start();
	}
	
	function reviewRfq() {
		doSubmit("/rfq/rfq_review.jsp", "RfqRetrieve");
	}

// end hiding contents -->
</SCRIPT>

</body>
</html>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>