<%@ page import="com.tsa.puridiom.entity.Report" %>
<%List reportList = (List)MenuManager.getSystemReports(oid, uid);%>
<script language='Javascript1.2' type="text/javascript">
<!--
var reportName = '<%=requisitionLineReport%>';
function getMyReports(originalArray, reportName) 
{
		var options = new Array();

		options[0] = originalArray;
		options[1] = new Array(reportName, "reports/" + reportName, "", "");
		<%int index = 2;
		for(int i = 0; i < reportList.size(); i++)
		{		
            Report report = (Report)reportList.get(i);%>
			options[<%=index%>] = new Array("<%=report.getComp_id().getReportTitle()%>", "javascript: viewReport('<%=report.getReportDatawindow()%>'); void(0);", "", "");
			
        <%index++;
		}%>
		return options;
	}
	
	function viewReport(reportName)
	{
		//popupParameters = "reportName=" + reportName;
		//doSubmitToPopup('reports/view_report.jsp', 'ReportExecute', 500, 500);
		//doSubmitToPopup('reports/view_report.jsp', 'DoNothing', 500, 500);
		
		reportFilter(reportName);
	}
	
//-->
</script>