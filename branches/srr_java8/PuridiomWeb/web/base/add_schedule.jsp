<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>

<%@page import="java.math.BigDecimal"%><SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	String	s_ic_header = (String) request.getAttribute("Schedule_icHeader");
	String	s_type = (String) request.getAttribute("Schedule_scheduleType");
	String	s_doc_type = (String) request.getAttribute("Schedule_documentType");
	String	s_number = (String) request.getAttribute("Schedule_lineNumber");
	String	s_action = (String) request.getAttribute("formAction");
	String s_process_link = (String) request.getAttribute("s_process_link");
	String s_current_method = (String) request.getAttribute("s_current_method");
	String s_process_method = (String) request.getAttribute("s_process_method");
	String	s_title = "";
	String	s_schedule_date = "";
	String	s_revised_date = "";
	String	s_description = "";
	String	s_completion_date = "";
	BigDecimal	s_amount = new BigDecimal(0);
	String	s_today = HiltonUtility.getFormattedDate(d_today, oid,  "MM-dd-yyyy  HH:mm:ss");
	String	quantityDecimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");

	Calendar rightNow = Calendar.getInstance();
	rightNow.add(rightNow.DAY_OF_MONTH, + 1);

	String s_tomorrow = HiltonUtility.getFormattedDate(rightNow.getTime(), oid, userDateFormat);

	if (s_type.equals("R"))	{	s_title = "Performance";	}
	if (s_type.equals("D"))	{	s_title = "Delivery";			}
	if (s_type.equals("M"))	{	s_title = "Milestone";		}
	if (s_type.equals("P"))	{	s_title = "Payment";			}
	if (s_type.equals("O"))	{	s_title = "Other";				}

	s_title = "New " + s_title + " Schedule";

	if (s_action == null)
	{
		s_action = "CREATE";
	}

	if (s_number == null)
	{
		s_number = "";
	}

	if (s_action.equalsIgnoreCase("CREATE"))
	{
		s_schedule_date = s_tomorrow;
	}
	else
	{
		Schedule schedule = (Schedule) request.getAttribute("schedule");

		if (schedule != null)
		{
			s_schedule_date = HiltonUtility.getFormattedDate(schedule.getScheduleDate(), oid, userDateFormat);
			s_revised_date = HiltonUtility.getFormattedDate(schedule.getRevisedDate(), oid, userDateFormat);
			s_description = schedule.getDescription();
			s_completion_date = HiltonUtility.getFormattedDate(schedule.getCompletionDate(), oid, userDateFormat);
			s_amount = HiltonUtility.getFormattedDollar(schedule.getScheduleAmount(), oid);
		}
	}

%>

<tsa:hidden name="Schedule_scheduleType" value="<%=s_type%>"/>
<tsa:hidden name="Schedule_documentType" value="<%=s_doc_type%>"/>
<tsa:hidden name="Schedule_icHeader" value="<%=s_ic_header%>"/>
<tsa:hidden name="Schedule_lineNumber" value="<%=s_number%>"/>
<tsa:hidden name="formAction" value="<%=s_action%>"/>
<tsa:hidden name="todayDate" value="<%=s_today%>"/>
<tsa:hidden name="browseName" value="schedule"/>

<tsa:hidden name="s_process_link" value="<%=s_process_link%>"/>
<tsa:hidden name="s_current_method" value="<%=s_current_method%>"/>
<tsa:hidden name="s_process_method" value="<%=s_process_method%>"/>

<TABLE>
<tr>
	<td width=680px>
		<table cellpadding=0 cellspacing=0 border=0 width=100%>
		<tr>
			<td valign=top width=50px height=30px>
				<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
				<tr>
					<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
				</tr>
				<tr>
					<td nowrap class=hdr12 valign=middle>
						<div style="margin-left:10px; margin-right:10px" class=hdr12><%=s_title%></div>
					</td>
				</tr>
				</table>
			</td>
			<td valign=bottom align=left><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
			<td valign=bottom align=right height=30px width=100%>
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
	</td>
</tr>
</TABLE>
<BR>
<TABLE ALIGN="CENTER">
	<TR>
		<TD align=right>Schedule Date:</TD>
		<TD>
			<INPUT TYPE="TEXT" NAME="Schedule_scheduleDate" value="<%=s_schedule_date%>" ONCHANGE="dateCheck(this);">
			<a href="javascript: show_calendar('Schedule_scheduleDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
		</TD>
	</TR>
	<TR <%=HtmlWriter.isVisible(oid, "schedulesRevisedDate")%>>
		<TD align=right><%=DictionaryManager.getLabelsInstance(oid, language)
					.getLabel(oid, "schedulesRevisedDate", "Revised Date")%>:</TD>
		<TD>
			<INPUT TYPE="TEXT" NAME="Schedule_revisedDate" value="<%=s_revised_date%>" ONCHANGE="dateCheck(this);">
			<a href="javascript: show_calendar('Schedule_revisedDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
		</TD>
	</TR>
	<TR>
		<TD align=right valign=top>Description:</TD>
		<TD>
			<TEXTAREA BACKGROUND-COLOR="white" WRAP="NONVIRTUAL" NAME="Schedule_description" COLS="40" ROWS="2" ONKEYDOWN="limit(frm.Schedule_description, 50);" ONKEYUP="limit(frm.Schedule_description, 50);">${esapi:encodeForHTML(schedule.description)}</TEXTAREA>
		</TD>
	</TR>
	<TR <%=HtmlWriter.isVisible(oid, "schedulesCompletionDate")%>>
		<TD align=right><%=DictionaryManager.getLabelsInstance(oid, language)
					.getLabel(oid, "schedulesCompletionDate", "Completion Date")%>:</TD>
		<TD>
			<INPUT TYPE="TEXT" NAME="Schedule_completionDate" value="<%=s_completion_date%>" ONCHANGE="dateCheck(this);">
			<a href="javascript: show_calendar('Schedule_completionDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
		</TD>
	</TR>
	<TR <%=HtmlWriter.isVisible(oid, "schedulesAmount")%>>
		<TD align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "schedulesAmount", "Schedule Amount")%>:</TD>
		<TD>
			<INPUT TYPE="TEXT" NAME="Schedule_scheduleAmount" value="<%=HiltonUtility.getFormattedQuantity(s_amount,oid)%>" onchange="scheduleAmount();">
		</TD>
	</TR>
</TABLE>

<BR>

<TABLE ALIGN="CENTER" WIDTH="80%">
	<TR>
		<TD ALIGN="CENTER"><div id="pxbutton"><A HREF="javascript: submitThis(); void(0);" BORDER="0">Save</a></div></TD>
		<TD ALIGN="CENTER"><div id="pxbutton"><A HREF="javascript: returnAbort(); void(0);">Cancel</A></div></TD>
	</TR>
</TABLE>

<%@ include file="/system/footer.jsp" %>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	var qtyDecimals  = <%=Integer.valueOf(quantityDecimals).intValue()%>;

	function limit(field, maxlimit)
	{
		if (field.value.length > maxlimit)
		{
			// field is too long...trim it!
			field.value = field.value.substring(0, maxlimit);
		}
	}

	function validateForm()
	{
		if (frm.handler.value.indexOf("ScheduleUpdate") >= 0 || frm.handler.value.indexOf("ScheduleAdd") >= 0)
		{
			if (chkdate(frm.Schedule_scheduleDate) == false)
			{
				alert("Please enter a valid Schedule Date.");
				return false;
			}
			else if (chkdate(frm.Schedule_revisedDate) == false)
			{
				alert("Please enter a valid Revised Date.");
				return false;
			}
			else if (isEmpty(frm.Schedule_description.value))
			{
				alert("Please enter a Description.");
				return false;
			}
			else if (chkdate(frm.Schedule_completionDate) == false)
			{
				alert("Please enter a valid Completion Date.");
				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			return true;
		}
	}

	function submitThis()
	{
		if (frm.formAction.value == "CREATE")
		{
			doSubmit('base/schedules.jsp', 'ScheduleAdd;ScheduleRetrieveAllLinesBy');
		}
		else
		{
			doSubmit('base/schedules.jsp', 'ScheduleUpdate;ScheduleRetrieveAllLinesBy');
		}
	}

	function dateCheck(x)
	{
		var schedule_date = "<%=s_schedule_date%>";
		var revised_date = "<%=s_revised_date%>";
		var completion_date = "<%=s_completion_date%>";

		if (compareDate(x, frm.todayDate) == false)
		{
			alert("Dates cannot be earlier than or equal to Today!");

			if (x == frm.Schedule_scheduleDate)
			{
				x.value = schedule_date;
			}
			else if (x == frm.Schedule_revisedDate)
			{
				x.value = revised_date;
			}
			else if (x == frm.Schedule_completionDate)
			{
				x.value = completion_date;
			}

			this.focus();
		}
	}

	function compareDate(laterdate, earlierdate)
	{
		var ldate = laterdate.value;
		var edate = earlierdate.value;

		parseDate(ldate);
		var laterdate = new Date(strYear, strMonth - 1, strDay);

		parseDate(edate);
		var earlierdate = new Date(strYear, strMonth - 1, strDay);

		var difference = laterdate.getTime() - earlierdate.getTime();

		if (difference <= 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	function returnAbort()
	{
		doSubmit('base/schedules.jsp', 'ScheduleRetrieveAllLinesBy');
	}

	function scheduleAmount()
	{
		var scheduleAmount = nformat(frm.Schedule_scheduleAmount.value, qtyDecimals);
		frm.Schedule_scheduleAmount.value = scheduleAmount;
	}

// end hiding contents -->
</SCRIPT>