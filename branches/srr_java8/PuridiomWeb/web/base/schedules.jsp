<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.Schedule" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/puridiom.js"></SCRIPT>
<%
	List list = (List) request.getAttribute("scheduleList");
	String	allowBrowse = (String) request.getAttribute("allowBrowse");
	if (allowBrowse == null) {
		allowBrowse = "true";
	}

	String	s_document_type = (String) request.getAttribute("Schedule_documentType");
	String	s_type = (String) request.getAttribute("Schedule_scheduleType");
	String	s_ic_header = (String) request.getAttribute("Schedule_icHeader");
	String	s_title = "";

	String formAction = (String) request.getAttribute("formAction");



	if (s_type.equals("R"))	{	s_title = "Performance";	}
	if (s_type.equals("D"))	{	s_title = "Delivery";			}
	if (s_type.equals("M"))	{	s_title = "Milestone";		}
	if (s_type.equals("P"))	{	s_title = "Payment";			}
	if (s_type.equals("O"))	{	s_title = "Other";				}
%>

<tsa:hidden name="Schedule_scheduleType" value="<%=s_type%>"/>
<tsa:hidden name="Schedule_documentType" value="<%=s_document_type%>"/>
<tsa:hidden name="Schedule_icHeader" value="<%=s_ic_header%>"/>
<tsa:hidden name="Schedule_lineNumber" value=""/>
<tsa:hidden name="allowBrowse" value="<%=allowBrowse%>"/>
<tsa:hidden name="formAction" value="<%=formAction%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12><%=s_title%>&nbsp;Schedules</div></td></tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<table id=scheduleList border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=5px>&nbsp;</td>
	<td width=670px class=browseHdrDk align=center valign=top>
		<table border=0 cellspacing=0 cellpadding=2 width=665px>
		<tr>
			<td nowrap height=18px class=browseHdrDk width=15%>Schedule Date</td>
			<td <%=HtmlWriter.isVisible(oid, "schedulesRevisedDate")%> nowrap height=18px class=browseHdrDk	width=15%>
				<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "schedulesRevisedDate", "Revised Date")%>
			</td>
			<td nowrap height=18px class=browseHdrDk width=35%>Description</td>
			<td nowrap height=18px class=browseHdrDk width=15% align="center">Amount</td>
			<td <%=HtmlWriter.isVisible(oid, "schedulesCompletionDate")%> nowrap height=18px
				class=browseHdrDk width=15%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "schedulesCompletionDate", "Completion Date")%>
			</td>
			<td nowrap height=18px class=browseHdrDk width=5%>Delete</td>
		</tr>
		</table>
		<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 675px; height: <%=((list.size()) * 18) + 20%>px; align:center; overflow-y:visible; overflow-x:auto;">
		<div id="noRecords" style="visibility: hidden; display: none;">
		<table border=0 cellspacing=0 cellpadding=2 width=665px class=browseRow>
		<tr><td><b>There are currently no <%=s_title%>&nbsp;Schedules.</b></td></tr>
		</table>
		</div>
		<table id=browseRows border=0 cellspacing=0 cellpadding=1 width=665px class=browseRow>
<%		for (int il = 0; il < list.size(); il++) {
				Schedule schedule = (Schedule) list.get(il);
%>
		<tr>
			<td height=18px class=browseRow width=15%><%=HiltonUtility.getFormattedDate(schedule.getScheduleDate(), oid, userDateFormat)%></td>
			<td <%=HtmlWriter.isVisible(oid, "schedulesRevisedDate")%> height=18px class=browseRow width=15%><%=HiltonUtility.getFormattedDate(schedule.getRevisedDate(), oid, userDateFormat)%></td>
			<td height=18px class=browseRow width=30%><a href="javascript: updateSchedule('<%=schedule.getComp_id().getLineNumber()%>'); void(0);" title="Click here to View/Modify Descripition Details."><%=schedule.getDescription()%></a></td>
			<td height=18px class=browseRow width=10% align="right" ><%=HiltonUtility.getFormattedQuantity(schedule.getScheduleAmount(), oid)%></td>
			<td <%=HtmlWriter.isVisible(oid, "schedulesCompletionDate")%> height=18px class=browseRow width=15% align="right"><%=HiltonUtility.getFormattedDate(schedule.getCompletionDate(), oid, userDateFormat)%></td>
			<td align="right" valign=top width=8%><a href="javascript: deleteSchedule(<%=schedule.getComp_id().getLineNumber()%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" border=0 alt="Delete"></a>&nbsp;&nbsp;&nbsp;</td>
		</tr>
<%		}%>
		</table>
		</div>
		</div>
	</td>
	<td width=5px>&nbsp;</td>
</tr>
</table>

<table width=100% border=0>
<tr>
<%	if (s_document_type.equalsIgnoreCase("POH") && role.getAccessRights("PO") < 2) { %>
	<td align=center><br><div id="pxbutton"><a href="javascript: void(0); scheduleRefreshPopup();">Close</a></div></td>
<%	} else { %>
	<td align=center><br><div id="pxbutton"><a href="javascript: void(0); addSchedule();">Add</a></div></td>
	<td align=center><br><div id="pxbutton"><a href="javascript: void(0); scheduleRefreshPopup();">Close</a></div></td>
<%	} %>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	var rowSelect;
	frm = document.purchaseform;
	var browser = browserCheck();
	var totalRows = <%=list.size()%>;

	if (totalRows <= 0) {
		document.getElementById("noRecords").style.visibility = "visible";
		document.getElementById("noRecords").style.display = "block";
		document.getElementById("browseBorder").style.height = "25px";
	}

	function thisLoadPopup() {
		/*if (frm.formAction.value == "CREATE" || frm.formAction.value == "UPDATE" || frm.formAction.value == "DELETE") {
			self.window.parent.scheduleRefresh();
		}*/
	}

	function addSchedule()
	{
		setHiddenFields("");

		frm.formAction.value = "CREATE";

		doSubmit('/base/add_schedule.jsp', 'DoNothing');
	}

	function updateSchedule(line_number)
	{
		setHiddenFields("");

		frm.formAction.value = "UPDATE";
		frm.Schedule_lineNumber.value = line_number;
		doSubmit('/base/add_schedule.jsp', 'ScheduleRetrieveById');
	}

	function deleteSchedule(line_number)
	{
		if (verifyAction("Delete this schedule?"))
		{
			frm.Schedule_lineNumber.value = line_number;
			frm.formAction.value = "DELETE";

			doSubmit('base/schedules.jsp', 'ScheduleDeleteById;ScheduleRetrieveAllLinesBy');
		}
		else
		{
			return false;
		}
	}

	function scheduleRefreshPopup() {
		self.window.parent.scheduleRefresh();
		window.top.hidePopWin();
	}

// End Hide script -->
</SCRIPT>


