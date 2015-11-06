<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility"%>
<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>


<%
	String isReportExtensive= HiltonUtility.ckNull((String)request.getAttribute("reportExtensive"));
	String name= HiltonUtility.ckNull((String)request.getAttribute("ReportQueue_name"));
	String title= HiltonUtility.ckNull((String)request.getAttribute("ReportQueue_title"));
	String publicView= HiltonUtility.ckNull((String)request.getAttribute("ReportQueue_publicView"));
	//
	String type= HiltonUtility.ckNull((String)request.getAttribute("ReportQueue_type"));
	String reportQueueModule= HiltonUtility.ckNull((String)request.getAttribute("ReportQueue_module"));
	String sendFrom= HiltonUtility.ckNull((String)request.getAttribute("ReportQueue_sendFrom"));
	String sendT= HiltonUtility.ckNull((String)request.getAttribute("ReportQueue_sendT"));
	String alias= HiltonUtility.ckNull((String)request.getAttribute("ReportQueue_alias"));
	
	String dataMaxSize= PropertiesManager.getInstance(oid).getProperty("REPORTS", "SCHEDULEMAXROWS", "10000");
	String url = "";
	Browse browse = (Browse) request.getAttribute("browse");

	url = (String) request.getAttribute("urlFileDownload");
	if (HiltonUtility.isEmpty(url)) {
		url = (String) request.getAttribute("report");
	}
%>

<tsa:hidden name="execute" value="N"/>
<tsa:hidden name="ReportQueue_name" value="<%=name%>"/>
<tsa:hidden name="ReportQueue_title" value="<%=title%>"/>
<tsa:hidden name="ReportQueue_type" value="<%=type%>"/>
<tsa:hidden name="ReportQueue_module"	value="<%=reportQueueModule%>"/>
<tsa:hidden name="ReportQueue_sendFrom" value="<%=sendFrom%>"/>
<tsa:hidden name="ReportQueue_sendT" value="<%=sendT%>"/>
<tsa:hidden name="ReportQueue_alias" value="<%=alias	%>"/>
<tsa:hidden name="ReportQueue_publicView" value="<%=publicView%>"/>
<tsa:hidden name="viewNow"	value="Y"/>
<tsa:hidden name="format"	value="<%=type%>"/>

<table border=0 cellspacing=0 cellpadding=0 width=100%>
<%
	if ( isReportExtensive.equalsIgnoreCase("Y") )
	{
%>
<table border="0">
	<tr>
		<td align="center">-  NOTICE  -  REPORT TOO EXTENSIVE - MUST SCHEDULE  -</td>
	</tr>
	<tr>
		<td align="justify">
Your report exceeds the maximum amount of data to be retrieved during normal business hours.<br>
Rather than impacting the Puridiom response time for all users, the report will be cut off at <%=dataMaxSize%> rows. In order to receive a report in full, you must "Schedule" it to run off-hours.<br><br>

To schedule:<br>
- Click on the "Schedule Options" link in lower section of the reports page.<br>
- Choose the Frequency, Start Date, End Date and Time the report should run. <br>
Time defaults to run after midnight {00:05 AM}. <br>
As long as the time falls between 12:00 AM and 5:00 AM, the full report will be generated and emailed to you.<br>
<br><br>
		</td>
	</tr>
	<tr>
		<td>
			<tsa:hidden name="browseId" value="<%=browse.getBrowseId()%>"/>
		</td>
	</tr>
</table>
<table width=100% border=0>
	<tr>
		<td align=center>
			<a href="javascript: doSubmit('/system/popupDocAttachment.jsp', 'ReportPrintPdfFromCache'); void(0);">
			<img class=button src="<%=contextPath%>/images/button_preview.gif" border=0 alt="Preview"></a>
		</td>
		<td>
			<a href="javascript: window.close(); void(0);">
			<img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a>
		</td>
	</tr>
</table>
<br>
<% } %>
<tr>
<% if(!isReportExtensive.equalsIgnoreCase("Y"))
	{ %>
	<td width=100% align=center valign=top><br><br><b>If you can not view the selected File. please click <a href="<%= url %>">here</a></b></td>
<% } %>
</tr>
</table>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	frm = document.purchaseform;
// 	alert(window.parent.frm['epmc'].value + "  " + frm.epmc);
	if (window.parent.frm['epmc'] && frm.epmc){
		window.parent.frm['epmc'].value = frm.epmc.value;
	}
// 	alert(window.parent.frm['epmc'].value );
<% if(!isReportExtensive.equalsIgnoreCase("Y")) { %>

	function thisLoadPopup() {
		window.location = '<%= url %>';
	}
<% } %>

// End Hide script -->
</script>

