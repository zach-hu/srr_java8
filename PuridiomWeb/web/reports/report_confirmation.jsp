<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%	String queueID = (String)request.getAttribute("SendQueue_queueid");%>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Report Confirmation</div>
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
</table>

<br>
<br>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td align=center class=message><font classhdr12>Your report will be emailed to you shortly.<br><br>Your Report ID is <%=queueID%>.</font></td>
</tr>
</table>
<br>
<br>


<%@ include file="/system/footer.jsp" %>


<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
//	setNavCookie("/reports/report_types.jsp", "ReportRetrieveByModule", "Reports", true);

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