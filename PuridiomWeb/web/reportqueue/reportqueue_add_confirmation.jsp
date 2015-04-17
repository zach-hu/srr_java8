<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<%
	ReportQueue reportQueue  = (ReportQueue) request.getAttribute("reportQueue");
	if (reportQueue==null) {
		reportQueue = new ReportQueue();
	}
	String reportModule = reportQueue.getModule();
	String fromPage = (String)request.getAttribute("fromPage");
	UserProfile owner = new UserProfile();
	owner = UserManager.getInstance().getUser(oid, uid);
	String ReportQueue_title = (String) request.getAttribute("ReportQueue_title");
	String time = reportQueue.getDeliveryTime();
	String timeArray[] = time.split(":");
	String mt = "AM";
	if (Integer.valueOf(timeArray[0]).intValue() > 12) {
		mt="PM";
		timeArray[0]=String.valueOf(Integer.valueOf(timeArray[0]).intValue()-12);
	}
	time=timeArray[0]+":"+timeArray[1];
%>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<tsa:hidden name="fromPage" value="<%=fromPage%>"/>
<tsa:hidden name="ReportQueue_icReportQueue" value="<%=reportQueue.getIcReportQueue()%>"/>
<tsa:hidden name="ReportQueue_module" value="<%=reportModule%>"/>
<tsa:hidden name="reportModule" value="<%=reportModule%>"/>

<!--<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
  <td valign=top width=150px height=30px>
    <table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
    <tr>
      <td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
    </tr>
    <tr>
      <td nowrap class=hdr12 valign=middle>
        <div style="margin-left:10px; margin-right:10px" class=hdr12><%=ReportQueue_title%> Options</div>
      </td>
    </tr>
    </table>
  </td>
  <td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
  <td valign=bottom align=right>
    <table cellpadding="0" cellspacing="0" border=0>
      <tr>
        <td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
      </tr>
      <tr>
        <td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
      </tr>
    </table>
  </td>
</tr>
</table>-->

<br>

<table border=0 cellspacing=0 cellpadding=2 width="680px">
<tr>
	<td colspan=5 align="center"><font class=hdr12><%=reportQueue.getAlias()%>&nbsp;has been scheduled.</font></td>
</tr>
<tr>
	<td colspan=5>&nbsp;</td>
</tr>
<tr>
	<td align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-sendto", "Send To")%></b></td>
	<td colspan=4 nowrap><%=reportQueue.getSendTo()%></td>
</tr>
<tr>
	<td colspan=5>&nbsp;</td>
</tr>
<tr>
	<td align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-frequency", "Frequency")%></b></td>
	<td nowrap>
	  <%
	  	if (reportQueue.getFrequency().equalsIgnoreCase("D")) { %>Daily<% }
        if (reportQueue.getFrequency().equalsIgnoreCase("W")) { %>Weekly<% }
        if (reportQueue.getFrequency().equalsIgnoreCase("M")) { %>Monthly<% }
        if (reportQueue.getFrequency().equalsIgnoreCase("Q")) { %>Quarterly<% }
        if (reportQueue.getFrequency().equalsIgnoreCase("A")) { %>Yearly<% }
        if (reportQueue.getFrequency().equalsIgnoreCase("O")) { %>Once<% }
      %>
	</td>
	<td>&nbsp;</td>
	<td align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-startdate", "Start Date")%></b></td>
	<td nowrap><%=HiltonUtility.getFormattedDate(reportQueue.getStartDate(),oid, userDateFormat)%></td>
</tr>
<tr>
	<td align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-time", "Time")%></b></td>
	<td nowrap><%=time%>&nbsp;<%=mt%>&nbsp;EST</td>
	<td>&nbsp;</td>
	<td align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-enddate", "End Date")%></b></td>
	<td nowrap><%=HiltonUtility.getFormattedDate(reportQueue.getEndDate(),oid, userDateFormat)%></td>
</tr>
<tr>
	<td align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-publicview", "Visibility")%></b></td>
	<td nowrap><% if (reportQueue.getPublicView().equalsIgnoreCase("Y")) {%>Public<% } else { %>Private<% } %></td>
	<td>&nbsp;</td>
	<td align="right">
	<div id="ReportQueueWeeklyFrequency" style="visibility:hidden; display:none;">
		<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-day", "Day")%></b>
	</div>
	</td>
	<td>
	<div id="ReportQueueWeeklyFrequency" style="visibility:hidden; display:none;">
	  <%=reportQueue.getDeliveryDay()%>
	</div>
	</td>
</tr>
</table>

<table border=0 cellspacing=0 cellpadding=2 width="680px">
<tr>
		<td align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td align="center">
				<a href="javascript: returnToReportQueue(); void(0);">
				<img class=button src="<%=contextPath%>/images/button_edit.gif" border=0>
				</a>
			</td>
			<td align="center">
				<a href="javascript: setReportsQueue('<%=reportModule%>'); void(0);">
				<img class=button src="<%=contextPath%>/images/button_return.gif" border=0>
				</a>
			</td>
		</tr>
		</table>
		</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<script value=JavaScript>
<!-- Hide script

frm = document.purchaseform;

<%
    if (reportQueue.getFrequency().equalsIgnoreCase("W")) { %>
    displayArea('ReportQueueWeeklyFrequency');
<% }
    if (reportQueue.getFrequency().equalsIgnoreCase("M")) { %>
	displayArea('ReportQueueMonthlyFrequency');
<% }
    if (reportQueue.getFrequency().equalsIgnoreCase("Q")) { %>
    displayArea('ReportQueueQuarterlyFrequency');
<% }
    if (reportQueue.getFrequency().equalsIgnoreCase("A") || reportQueue.getFrequency().equalsIgnoreCase("O")) { %>
    displayArea('ReportQueueYearlyFrequency');
<% } %>

function returnToReportQueue() {
	frm.browseName.value = "<%=reportQueue.getName()%>";
	popupParameters = "browseName=" + "<%=reportQueue.getName()%>";
	doSubmit("/browse/browse_filter_reportqueue_options.jsp", "ReportQueueRetrieveById");
}

// End Hide script -->
</script>