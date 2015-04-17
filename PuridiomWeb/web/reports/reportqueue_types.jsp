<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%List reportsList = (List)request.getAttribute("reportsList");
  String reportModule = (String)request.getAttribute("Report_reportModule");
  String showReportsPreview = PropertiesManager.getInstance(oid).getProperty("REPORTS", "ShowReportsPreview", "N") ;
%>

<% if(showReportsPreview.equalsIgnoreCase("Y")){ %>
	<script language='Javascript1.2' type="text/javascript">
	<!--
		function preloadImages(){
			var ar = new Array();
			<%for(int i = 0; i < reportsList.size(); i++){
				Report report = (Report)reportsList.get(i);%>
					ar[<%=i%>] = new Image();							
					ar[<%=i%>].src='<%=DictionaryManager.getInstance("host", oid).getProperty("report-preview-image-url", "/")%>/<%=report.getReportXml()%>.jpg';
			<%}%>
		};
		preloadImages();
	//-->
	</SCRIPT>
<% } %>

<tsa:hidden name="fromPage" value="/reports/reportqueue_types.jsp"/>
<tsa:hidden name="jasperFile" value=""/>
<tsa:hidden name="reportTitleName" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Report Selection</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right></td>
			<td width=100px></td>
		</tr>
		<tr>
			<td align=right></td>
			<td width=100px></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>
<% if(showReportsPreview.equalsIgnoreCase("Y")){ %>
	<table width=960px cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td width="320" valign="top" >
			<table cellpadding=0 cellspacing=0 border=0 align=center>
				<%for(int i = 0; i < reportsList.size(); i++)
				{
					Report report = (Report)reportsList.get(i);%>
					<tr height=18px>
						<%String temp = report.getReportPrompt();
						if(temp.equalsIgnoreCase("N"))
						{%>
	        				<td nowrap><a href="javascript: viewReport('<%=report.getComp_id().getReportTitle()%>');"><%=report.getReportDescription()%></a></td>
	        			<%}
	        			else
	        			{%>
							<td nowrap><a href="javascript: reportQueueFilter('<%=report.getReportDatawindow()%>', '<%=report.getReportXml()%>', '<%=report.getReportDescription()%>');"  onmouseover="reportXmlPreview.src='<%=DictionaryManager.getInstance("host", oid).getProperty("report-preview-image-url", "/")%>/<%=report.getReportXml()%>.jpg';" ><%=report.getReportDescription()%></a></td>       			
	        				         				
	        			<%}%>
	        		</tr>
				<%}%>
			</table>
			<br />
			<table width=310px cellpadding=0 cellspacing=0 border=0>
			<tr>
			<td align=center><div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing');">Cancel</a></div></td>
			</tr>
			</table>
		</td>
		<td width="640">
			<img name="reportXmlPreview" src="<%=DictionaryManager.getInstance("host", oid).getProperty("report-preview-image-url", "/")%>/imagesblank.gif" border="0" />	
		</td>	
	</tr>
	</table>
	<br>
	<br>
	<tsa:hidden name="reportModule" value="<%=reportModule%>"/>
<% }else{ %>	
	<table width=680px cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td>
			<table cellpadding=0 cellspacing=0 border=0 align=center>
				<%for(int i = 0; i < reportsList.size(); i++)
				{
					Report report = (Report)reportsList.get(i);%>
					<tr height=18px>
						<%String temp = report.getReportPrompt();
						if(temp.equalsIgnoreCase("N"))
						{%>
	        				<td nowrap><a href="javascript: viewReport('<%=report.getComp_id().getReportTitle()%>');"><%=report.getReportDescription()%></a></td>
	        			<%}
	        			else
	        			{%>
	        				<td nowrap><a href="javascript: reportQueueFilter('<%=report.getReportDatawindow()%>', '<%=report.getReportXml()%>', '<%=report.getReportDescription()%>');"><%=report.getReportDescription()%></a></td>
	        			<%}%>
	        		</tr>
				<%}%>
			</table>
		</td>
	</tr>
	</table>
	<br>
	<br>
	<tsa:hidden name="reportModule" value="<%=reportModule%>"/>
	<table width=680px cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td align=center><div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing');">Cancel</a></div></td>
	</tr>
	</table>
<% }%>	

<%@ include file="/system/footer.jsp" %>


<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("/reports/report_types.jsp", "ReportRetrieveByModule", "Reports", true);


	function viewReport(reportName)
	{
		frm.browseName.value = reportName;

		popupParameters = "browseName=" + reportName;
		popupParameters = popupParameters + ";" + "reportName=" + reportName;
		popupParameters = popupParameters + ";noParams=Y"
		doSubmitToPopup('', 'ReportExecuteNoParams', 500, 400);

	}

// End Hide script -->
</SCRIPT>