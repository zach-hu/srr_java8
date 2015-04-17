<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<% Encoder encoder = DefaultEncoder.getInstance(); %>
<br>
<script type="text/javascript">
	function setTypeNames(typeName) {
		document.getElementById('typeNames').innerHTML += '<input type=hidden id="typeName" name="typeName" value="' + typeName + '">';
	}
</script>
<div id="typeNames"></div>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr><td align="center">
<table border=1 cellpadding=2 cellspacing=0 width=650px>
<tr bgcolor="gainsboro"><th align="left">Filter Options</th></tr>
<tr><td align="center">
<table border=0 cellpadding=2 cellspacing=0>
  <tr>
    <td colspan=5 align=center>
    <% String opReportQueueType = reportQueue.getType();
       String opReportQueuePublic = reportQueue.getPublicView();
        if (HiltonUtility.isEmpty(opReportQueueType)) { opReportQueueType = "PDF"; }
        if (HiltonUtility.isEmpty(opReportQueuePublic)) { opReportQueuePublic = propertiesManager.getProperty("REPORT OPTIONS", "PUBLIC ACCESS", "Y"); }
    %>
    <table>
          <tr>
          	<td valign="middle"><b>Format:&nbsp;</b></td>
          	<% if (propertiesManager.getProperty("REPORT OPTIONS", "TYPES ALLOWED", "").toUpperCase().indexOf("PDF") >= 0) { %>
            <td valign="middle"><input type="radio" name="ReportQueue_type" value="PDF" <% if (opReportQueueType.equalsIgnoreCase("PDF")) { %>checked<%}%> >pdf&nbsp;</td>
            <% } %>
            <% if (propertiesManager.getProperty("REPORT OPTIONS", "TYPES ALLOWED", "").toUpperCase().indexOf("HTML") >= 0) { %>
            <td valign="middle"><input type="radio" name="ReportQueue_type" value="HTML" <% if (opReportQueueType.equalsIgnoreCase("HTM")) { %>checked<%}%> >html&nbsp;</td>
            <% } %>
            <% if (propertiesManager.getProperty("REPORT OPTIONS", "TYPES ALLOWED", "").toUpperCase().indexOf("XLS") >= 0) { %>
            <td valign="middle"><input type="radio" name="ReportQueue_type" value="XLS" <% if (opReportQueueType.equalsIgnoreCase("XLS")) { %>checked<%}%> >xls&nbsp;</td>
            <% } %>
            <% if (propertiesManager.getProperty("REPORT OPTIONS", "TYPES ALLOWED", "").toUpperCase().indexOf("CSV") >= 0) { %>
     		<td valign="middle"><input type="radio" name="ReportQueue_type" value="CSV" <% if (opReportQueueType.equalsIgnoreCase("CSV")) { %>checked<%}%> >csv&nbsp;</td>
            <% } %>
            <td width="50px">&nbsp;</td>
           	<td valign="middle" align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-reporttitle", "Report Title")%>: </b><input type="text" name="ReportQueue_alias" value="<%=headerEncoder.encodeForHTMLAttribute(alias)%>" size="40" maxlength="255"/></td>
          </tr>
          <tr>
            <td valign="middle" align="right" colspan=2><b>Make it Public:&nbsp;</b><input type="checkbox" name="ReportPublic" value="Y" <% if (opReportQueuePublic.equalsIgnoreCase("Y")) { %>checked<%}%> ></td>
          </tr>
    </table>
    </td>
  </tr>
  <tr>
  <td align=center>
      <table id="filterOptions" border=0 width="100%" cellpadding=2 cellspacing=0>
    <tr>
      <td width="15%">Column Name</td>
      <td width="11%">Operator</td>
      <td>Filter Expression(*)</td>
      <td width="12%">Logical</td>
      <td width="15%">Sort Option</td>
    </tr>
<%	int pt = 0 ;
    int filterRows = 5;
    for (int ii = 0; ii < filterRows; ii++) {
      int columnCount = 0;

      String colComp = "";
      String opeComp = "";
      String filComp = "";
      String labComp = "";
      String logComp = "";

      if (whereClauseList.size() > ii) {
      	List statement = (List) whereClauseList.get(ii);
      	colComp = (String) statement.get(0);
      	opeComp = (String) statement.get(1);
      	filComp = (String) statement.get(2);
      	labComp = (String) statement.get(3);
      	logComp = (String) statement.get(4);
      }
%>
    <tr>
      <td valign="middle">
      <select name="colname" size=1 onchange="javascript: updateFilterStatus(<%=ii%>);">
        <%
          for (int ix=0; ix < browseColumns.length; ix++) {
            BrowseColumn column = browseColumns[ix];
              if (column.isHidden()) {
                if (pt == ix) pt++ ;
                continue ;
              }
              if (pt == ix) { %>
              <option value="<%=column.getColumnName()%>" selected><%=column.getLabel(oid, language)%></option>
           <% } else { %>
              <option value="<%=column.getColumnName()%>" <% if(column.getColumnName().equalsIgnoreCase(colComp)) { %> selected<% } %>><%=column.getLabel(oid, language)%></option>
           <% }
              if (ii == 0) {
            	  %>
            	  <script type="text/javascript">
            	  	this.setTypeNames('<%= column.getType() %>');
            	  </script>
           <% }
            columnCount++;
          }
          if (ii == 0) {
            if (columnCount < filterRows) {
              filterRows = columnCount;
            }
          }
          pt++ ;
        %>

          </select>
    </td>

    <td align=right valign="middle">
          <tsa:hidden name="originalFilter" value="Y"/>
          <select name="operator" size=1>
<%          	if ( browseObject.getBrowseName().equalsIgnoreCase("r-pos-sm-mab") || browseObject.getBrowseName().equalsIgnoreCase("r-pos-d-mab"))
					{	%>
							<option value="=">=</option>
							<option value=">">></option>
							<option	value="<"><</option>
							<option value=">=" <% if(ii==1){%> selected <%} %> >>=</option>
							<option value="<=" <% if(ii==2){%> selected <%} %> ><=</option>
							<option value="<>"><></option>
<%					} else { %>
	    					<option value="=" <% if(opeComp.equalsIgnoreCase("=")) { %> selected<% } %>>=</option>
      						<option value=">" <% if(opeComp.equalsIgnoreCase(">")) { %> selected<% } %>>></option>
      						<option value="<" <% if(opeComp.equalsIgnoreCase("<")) { %> selected<% } %>><</option>
     						<option value=">=" <% if(opeComp.equalsIgnoreCase(">=")) { %> selected<% } %>>>=</option>
      						<option value="<=" <% if(opeComp.equalsIgnoreCase("<=")) { %> selected<% } %>><=</option>
      						<option value="<>" <% if(opeComp.equalsIgnoreCase("<>")) { %> selected<% } %>><></option>
<%					}  %>
          </select>
    </td>
    <td valign="top">
    	<div id="defaultOption<%=ii%>" style="float: left; padding-top: 0px;"><input id="filter_txt" name="filter_txt" size=30 type=text value="<%=filComp%>" onChange="correctMe(<%=ii%>);" onFocus="javascript:reviewField(<%= ii %>);"></div>
        <div id="typeOption<%=ii%>" style="visibility:hidden; display:none; float: left;"><input name="filterLabel_txt" size=30 type=text value="<%= labComp %>" onFocus="javascript:reviewField(<%= ii %>);" readonly></div>
     </td>
     <td>
<%		if (ii < (filterRows -1)) {%>
          <select name="logicalOperator" size=1>
          <option value=""> </option>
          <option value="AND" <% if(logComp.equalsIgnoreCase("AND")) { %> selected<% } %>>AND</option>
          <option value="OR" <% if(logComp.equalsIgnoreCase("OR")) { %> selected<% } %>>OR</option>
          </select>
<%		} else {%><!-- The last logical operator is not used --><tsa:hidden name="logicalOperator" value=""/>
<%		}%>
      </td>
      <td valign="middle">
        <select name="sort" size=1>
          <option value="N"> </option>
          <option value="ASC">Ascending</option>
          <option value="DESC">Descending</option>
        </select>
      </td>

    </tr>

    <%}%>
    <br><br>
    <tr>
      <td colspan=10 align=right>
        <table border=0 cellpadding=0 cellspacing=0 width=100%>
        <tr>
          <td align=center CLASS="small">(*)Enter Dates in <%=userDateFormat.toUpperCase()%> format.
          <br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browse-filterWarning", "For an Exact Search surround your value with single quotes.  Ex. 'VALUE'")%>
          <br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browse-filterWarning-wildcard", "Use % to indicate Wildcard Search")%>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
</td></tr>
</table>
</td></tr>
</table>

<br>

<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr><td align="center">
<table border=1 cellpadding=2 cellspacing=0 width="650px">
<tr bgcolor="gainsboro">
	<th>
		<table border=0 cellpadding=0 cellspacing=0 width="100%">
		<tr bgcolor="gainsboro">
			<th align="left"><a href="javascript: sendModuleShowHide(1); void(0);">Send Options</a></th>
			<th align="right">
				<div id="showSend" style="visibility:hidden; display:none;">
				<a href="javascript: sendModuleShowHide(1); void(0);"><img src="<%=contextPath%>/images/mail_reply.png" alt="Show." valign="bottom" border="0"></a>
				</div>
				<div id="SendOptions">
				<a href="javascript: sendModuleShowHide(0); void(0);"><img src="<%=contextPath%>/images/bar_close.gif" alt="Close." valign="bottom" border="0"></a>
				</div>
			</th>
		</tr>
		</table>
	</th>
</tr>
<tr>
	<td colspan=2 align="center">
<div id="SendOptions">
<table border=0 cellspacing=0 cellpadding=2>
<tr>
	<td colspan=2>&nbsp;</td>
</tr>
<tr>
	<td align="right">
		<a href="javascript: showUsers(); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-sendto", "Send To")%> for this item or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-sendto", "Send To")%></a>
	</td>
	<td nowrap>
		<textarea name="ReportQueue_sendTo" cols="100" rows="2" onChange="checkemail();"><%if(action.equalsIgnoreCase("new")) {%><%= encoder.encodeForHTML(owner.getMailId()) %><%} else {%>${esapi:encodeForHTML(reportQueue.sendTo)}<%}%></textarea>
	</td>
</tr>
<tr>
	<td align="center" colspan=2 nowrap>Separate Email Addresses with ";"</td>
</tr>
</table>

</div>
</td></tr>
</table>
</td></tr>
</table>

<br>

<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr><td align="center">
<table border=1 cellpadding=2 cellspacing=0 width="650px">
<tr bgcolor="gainsboro">
	<th>
		<table border=0 cellpadding=0 cellspacing=0 width="100%">
		<tr bgcolor="gainsboro">
			<th align="left"><a href="javascript: scheduleModuleShowHide(1); void(0);">Schedule Options</a></th>
			<th align="right">
				<div id="showSchedule">
				<a href="javascript: scheduleModuleShowHide(1); void(0);"><img src="<%=contextPath%>/images/clock.gif" alt="Show." valign="bottom" border="0"></a>
				</div>
				<div id="ScheduleOptions" style="visibility:hidden; display:none;">
				<a href="javascript: scheduleModuleShowHide(0); void(0);"><img src="<%=contextPath%>/images/bar_close.gif" alt="Close." valign="bottom" border="0"></a>
				</div>
			</th>
		</tr>
		</table>
	</th>
</tr>
<tr>
	<td colspan=2 align="center">
<div id="ScheduleOptions" style="visibility:hidden; display:none;">
<table border=0 cellspacing=0 cellpadding=2>
<tr>
	<td colspan=6>&nbsp;</td>
</tr>
<!--<tr>
	<td width="100">&nbsp;</td>
	<td width="160">&nbsp;</td>
	<td width="150">&nbsp;</td>
	<td width="65">&nbsp;</td>
	<td width="205">&nbsp;</td>
</tr>-->
<tr>
	<td align="right" width="90px"> <b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-frequency", "Frequency")%></b></td>
	<td nowrap colspan="5">
      <%	String opReportQueueFrequency = reportQueue.getFrequency();
        if (HiltonUtility.isEmpty(opReportQueueFrequency)) { opReportQueueFrequency="O"; }
      %>
      <table border=0 cellpadding=0 cellspacing=0 width=100%>
        <tr>
          <td width="15%">
          <table cellpadding="0" cellspacing="0" border="0">
          	<tr>
          		<td><input type="radio" name="ReportQueue_frequency" value="O" <% if (opReportQueueFrequency.equalsIgnoreCase("O")) { %>checked<% }%> onClick="setFrequencyInterface(0);"></td>
          		<td>
	          	<table cellpadding="0" cellspacing="0" border="0">
    	      		<tr><td><div id="fb0" style="visibility:hidden; display:none;"><b>Once</b></div></td></tr>
     	     		<tr><td><div id="fn0">Once</div></td></tr>
    	      	</table>
        	  	</td>
	          </tr>
          </table>
          </td>
          <td width="15%">
          <table cellpadding="0" cellspacing="0" border="0">
          	<tr>
          		<td><input type="radio" name="ReportQueue_frequency" value="D" <% if (opReportQueueFrequency.equalsIgnoreCase("D")) { %>checked<% }%> onClick="setFrequencyInterface(1);"></td>
          		<td>
	          	<table cellpadding="0" cellspacing="0" border="0">
    	      		<tr><td><div id="fb1" style="visibility:hidden; display:none;"><b>Daily</b></div></td></tr>
     	     		<tr><td><div id="fn1">Daily</div></td></tr>
    	      	</table>
        	  	</td>
	          </tr>
          </table>
          </td>
          <td width="15%">
          <table cellpadding="0" cellspacing="0" border="0">
          	<tr>
          		<td><input type="radio" name="ReportQueue_frequency" value="W" <% if (opReportQueueFrequency.equalsIgnoreCase("W")) { %>checked<% }%> onClick="setFrequencyInterface(2);"></td>
          		<td>
	          	<table cellpadding="0" cellspacing="0" border="0">
    	      		<tr><td><div id="fb2" style="visibility:hidden; display:none;"><b>Weekly</b></div></td></tr>
     	     		<tr><td><div id="fn2">Weekly</div></td></tr>
    	      	</table>
        	  	</td>
	          </tr>
          </table>
          </td>
          <td width="15%">
          <table cellpadding="0" cellspacing="0" border="0">
          	<tr>
          		<td><input type="radio" name="ReportQueue_frequency" value="M" <% if (opReportQueueFrequency.equalsIgnoreCase("M")) { %>checked<% }%> onClick="setFrequencyInterface(3);"></td>
          		<td>
	          	<table cellpadding="0" cellspacing="0" border="0">
    	      		<tr><td><div id="fb3" style="visibility:hidden; display:none;"><b>Monthly</b></div></td></tr>
     	     		<tr><td><div id="fn3">Monthly</div></td></tr>
    	      	</table>
        	  	</td>
	          </tr>
          </table>
          </td>
          <td width="15%">
          <table cellpadding="0" cellspacing="0" border="0">
          	<tr>
          		<td><input type="radio" name="ReportQueue_frequency" value="Q" <% if (opReportQueueFrequency.equalsIgnoreCase("Q")) { %>checked<% }%> onClick="setFrequencyInterface(4);"></td>
          		<td>
	          	<table cellpadding="0" cellspacing="0" border="0">
    	      		<tr><td><div id="fb4" style="visibility:hidden; display:none;"><b>Quarterly</b></div></td></tr>
     	     		<tr><td><div id="fn4">Quarterly</div></td></tr>
    	      	</table>
        	  	</td>
	          </tr>
          </table>
          </td>
          <td width="15%">
          <table cellpadding="0" cellspacing="0" border="0">
          	<tr>
          		<td><input type="radio" name="ReportQueue_frequency" value="A" <% if (opReportQueueFrequency.equalsIgnoreCase("A")) { %>checked<% }%> onClick="setFrequencyInterface(5);"></td>
          		<td>
	          	<table cellpadding="0" cellspacing="0" border="0">
    	      		<tr><td><div id="fb5" style="visibility:hidden; display:none;"><b>Yearly</b></div></td></tr>
     	     		<tr><td><div id="fn5">Yearly</div></td></tr>
    	      	</table>
        	  	</td>
	          </tr>
          </table>
          </td>
        </tr>
      </table>
	</td>
</tr>
<tr>
	<td align="right">
		<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-startdate", "Start Date")%></b>
	</td>
	<td nowrap>
		<input type="text" name="ReportQueue_startDate" size="15" maxlength="10" value="<%=HiltonUtility.getFormattedDate(reportQueue.getStartDate(),oid, userDateFormat)%>" onChange="controlStartDate();" onFocus="controlStartDate();">
		<a href="javascript: show_calendar('ReportQueue_startDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
	</td>
	<!-- A -->
	<td align="right">
		<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-enddate", "End Date")%></b>
	</td>
	<td nowrap>
		<input type="text" name="ReportQueue_endDate" size="15" maxlength="10" value="<%=HiltonUtility.getFormattedDate(reportQueue.getEndDate(),oid, userDateFormat)%>" onChange="controlEndDate();" onFocus="controlEndDate();">
		<a href="javascript: show_calendar('ReportQueue_endDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
	</td>
	<td align="right">
		<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-time", "Time")%></b>
	</td>
	<td nowrap>
		<input type="text" name="ReportQueue_deliveryTime" size="10" maxlength="10" value="<% if(action.equalsIgnoreCase("old")) { %><%=reportQueue.getDeliveryTime()%><% } else { %>00:05<%}%>" onChange="formatTime(this);">
		<select name="ReportQueue_mt" size="1">
			<option value="0" selected>AM</option>
			<option value="1">PM</option>
		</select>
		EST
	</td>
</tr>
<!-- B -->
<tr>
	<td align="right">
	<div id="ReportQueueWeeklyFrequency" style="visibility:hidden; display:none;">
		<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-day", "Day")%></b>
	</div>
	</td>
	<td nowrap colspan=5>
	<div id="ReportQueueWeeklyFrequency" style="visibility:hidden; display:none;">
	  <% String opReportQueueDeliveryDay = reportQueue.getDeliveryDay();
	  	 if (opReportQueueDeliveryDay==null) { opReportQueueDeliveryDay=""; }
	  %>
		<table border=0 cellpadding=0 cellspacing=0 width="100%">
		<tr>
			<td width="11%"><input type="radio" name="ReportQueue_deliveryDay" value="Sunday"	 <% if (opReportQueueDeliveryDay.equalsIgnoreCase("Sunday"))	{ %>checked<% }%> onClick="setStartDate(0);">Sunday</td>
			<td width="11%"><input type="radio" name="ReportQueue_deliveryDay" value="Monday"	 <% if (opReportQueueDeliveryDay.equalsIgnoreCase("Monday"))	{ %>checked<% }%> onClick="setStartDate(1);">Monday</td>
			<td width="11%"><input type="radio" name="ReportQueue_deliveryDay" value="Tuesday"	 <% if (opReportQueueDeliveryDay.equalsIgnoreCase("Tuesday"))	{ %>checked<% }%> onClick="setStartDate(2);">Tuesday</td>
			<td width="11%"><input type="radio" name="ReportQueue_deliveryDay" value="Wednesday" <% if (opReportQueueDeliveryDay.equalsIgnoreCase("Wednesday"))	{ %>checked<% }%> onClick="setStartDate(3);">Wednesday</td>
			<td width="11%"><input type="radio" name="ReportQueue_deliveryDay" value="Thursday"	 <% if (opReportQueueDeliveryDay.equalsIgnoreCase("Thursday"))	{ %>checked<% }%> onClick="setStartDate(4);">Thursday</td>
			<td width="11%"><input type="radio" name="ReportQueue_deliveryDay" value="Friday"	 <% if (opReportQueueDeliveryDay.equalsIgnoreCase("Friday"))	{ %>checked<% }%> onClick="setStartDate(5);">Friday</td>
			<td width="11%"><input type="radio" name="ReportQueue_deliveryDay" value="Saturday"	 <% if (opReportQueueDeliveryDay.equalsIgnoreCase("Saturday"))	{ %>checked<% }%> onClick="setStartDate(6);">Saturday</td>
			<td width="23%">&nbsp;</td>
		</tr>
		</table>
	</div>
	</td>
</tr>
<tr>
	<td align="center" colspan=6><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-time-message", "Reports are scheduled to run at 00:005 AM EST. by default")%></td>
</tr>
<tr>
  <td align="center" colspan=6>(*)Enter Dates in <%=userDateFormat.toUpperCase()%> format.</td>
</tr>
</table>
</div>
</td></tr>
</table>
</td></tr>
</table>

<br>

<% if (reportListSize > 0) { %>

<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr><td align="center">
<table border=1 cellpadding=2 cellspacing=0 width="650px">
<tr bgcolor="gainsboro">
	<th>
		<table border=0 cellpadding=0 cellspacing=0 width="100%">
		<tr bgcolor="gainsboro">
			<th align="left">Reports Executed</th>
		</tr>
		</table>
	</th>
</tr>
<tr>
	<td colspan=2 align="center">
		<table border=0 cellspacing=0 cellpadding=5>
			<tr>
				<td nowrap align="center"><b>Report</b></td>
				<td nowrap align="center"><b>Date Sent</b></td>
				<td nowrap align="center"><b>Time Sent</b></td>
				<td nowrap align="center"><b>Report Name</b></td>
				<td nowrap align="center"><b>Send To</b></td>
				<td nowrap align="center"><b>Public</b></td>
			</tr>
			<% for (int x = 0; x < reportListSize; x++) {
				ReportUser reportUser = (ReportUser) reportUserList.get(x);
				String format = "";
				if (reportUser.getDocFilename().toUpperCase().indexOf(".PDF")>0) { format = "adobe_pdf.gif"; }
				if (reportUser.getDocFilename().toUpperCase().indexOf(".HTML")>0) { format = "doc_html.gif"; }
				if (reportUser.getDocFilename().toUpperCase().indexOf(".XLS")>0) { format = "ms_excel.gif"; }
				if (reportUser.getDocFilename().toUpperCase().indexOf(".CSV")>0) { format = "doc_attached.gif"; }
				if (reportUser.getDocFilename().toUpperCase().indexOf(".ZIP")>0) { format = "zip.gif"; }
			%>
			<tr>
				<td nowrap align="left"><a href="javascript: doSubmitToLookup('<%=reportUser.getDocFilename()%>','DoNothing', 'width=775px', 'height=850px'); void(0);"><img src="<%=contextPath%>/images/<%=format%>" alt="Show Report" valign="bottom" border="0" height="16" width="16"></a></td>
				<td nowrap align="left"><%=reportUser.getDateSent()%></td>
				<td nowrap align="left"><%=reportUser.getTimeSent()%></td>
				<td nowrap align="left"><%=reportUser.getDocTitle()%></td>
				<td nowrap align="left"><%=UserManager.getInstance().getUser(oid, reportUser.getUserId()).getDisplayName()%></td>
				<td nowrap align="left">
				<%	if (action=="old") {
						if (reportQueue.getOwner().equalsIgnoreCase(uid) || reportUser.getUserId().equalsIgnoreCase(uid)) {
							String s_public = reportUser.getPublicView() ;
							if (s_public == null || !"Y".equals(s_public)) s_public = "N" ;
						%>
							<input type="checkbox" name="ReportUserAccess"<% if (s_public.equalsIgnoreCase("Y")) {%> checked<%}%>>
							<tsa:hidden name="ReportUserPublic" value="<%=s_public%>"/>
				<%		}
					} %>
				</td>
			</tr>
			<% } %>
		</table>
	</td>
</tr>
</table>
</td></tr>
</table>

<% } %>

<div id="mess" style="visibility:hidden; display:none;">
<br><font color="red">* Invalid email address in the Send To field.</font>
</div>

<br>

<tsa:hidden name="ReportQueue_sendToCode" value="${userId}"/>
<tsa:hidden name="ReportQueue_dateSent" value="<% if(action.equalsIgnoreCase(\"old\")) { %><%=reportQueue.getDateSent()%><%}%>"/>
<tsa:hidden name="ReportQueue_timeSent" value="<% if(action.equalsIgnoreCase(\"old\")) { %><%=reportQueue.getTimeSent()%><%}%>"/>
<tsa:hidden name="ReportQueue_dateAdded" value="<% if(action.equalsIgnoreCase(\"old\")) { %><%=reportQueue.getDateAdded()%><%}%>"/>
<tsa:hidden name="ReportQueue_timeAdded" value="<% if(action.equalsIgnoreCase(\"old\")) { %><%=reportQueue.getTimeAdded()%><%}%>"/>
<tsa:hidden name="ReportQueue_status" value="<% if(action.equalsIgnoreCase(\"new\") || (action.equalsIgnoreCase(\"old\") && reportQueue.getFrequency().equalsIgnoreCase(\"O\") ) ) { %>00<%} else {%><%=reportQueue.getStatus()%><%}%>"/>
<tsa:hidden name="ReportQueue_whereClause" value="<% if(action.equalsIgnoreCase(\"old\")) { %><%=reportQueue.getWhereClause()%><%}%>"/>
<tsa:hidden name="ReportQueue_nextRun" value="<% if(action.equalsIgnoreCase(\"old\")) { %><%=HiltonUtility.getFormattedDate(reportQueue.getNextRun(),oid, userDateFormat)%><%}%>"/>
<tsa:hidden name="action" value="<%= action %>"/>
<tsa:hidden name="tempStartDate" value=""/>
<tsa:hidden name="tempEndDate" value=""/>


<iframe id="typesFrame" name="typesFrame" src="javascript: void(0);" frameborder="0" marginheight="0" marginwidth="0" style="position: absolute; border: 1px solid #C0C0C0; display: none;"></iframe>

<script value=JavaScript>
<!-- Hide script

if(frm.ReportQueue_frequency[0].checked) frm.ReportQueue_endDate.disabled=true;

frm = document.purchaseform;

var currentTypeOptions = '';
var currentIndex = '';
var filterStatus = new Array();

var startDate = new Date(<%=d_today.getTime()%>);
var todaysDate  = new Date(<%=d_today.getTime()%>);
var dayStartDate	= startDate.getDate();
var monthStartDate	= startDate.getMonth();
var yearStartDate	= startDate.getYear();

var myDateFormat = '<%=userDateFormat%>';

  var bReset = true;
<% if ( browseObject.getBrowseName().indexOf("admin") >= 0 || browseObject.getBrowseName().equals("approver") || browseObject.getBrowseName().equals("supplier_mgt") || browseObject.getBrowseName().equals("catalog") ) { %>
            bReset = false;
<% } %>
<%	if (browseObject.getBrowseName().equalsIgnoreCase("std-table"))
		{
			tableType = HiltonUtility.ckNull((String) request.getAttribute("StdTable_id_tableType"));
		%>
			setOriginalFilter("StdTable_id_tableType", "=", "<%=tableType%>");
<%	} %>

	function isType(typeValue) {
		return (typeValue.indexOf('-TYPE') > 0) || (typeValue.indexOf('STATUS') >= 0) || (typeValue == 'DATE') || (typeValue == 'VSBA');
	}

	function updateFilterStatus(ix) {
		var typeValue = frm.typeName[frm.colname[ix].selectedIndex].value;

		setDefaultVisibility(ix);

		if (isType(typeValue)) {
			filterStatus[ix] = 'IP';
		} else {
			filterStatus[ix] = 'NR';
		}
	}

	function thisLoad() {
		f_StartIt();

		if (frm.action.value == 'old') {
			for (var i = 0; i < frm.colname.length; i++) {
				if (isType(frm.typeName[frm.colname[i].selectedIndex].value)) {
					if (!isEmpty(frm.filterLabel_txt[i].value)) {
						displayArea('typeOption' + i);
						hideArea('defaultOption' + i);
						filterStatus[i] = 'IP';
					} else if (!isEmpty(frm.filter_txt[i].value)) {
						filterStatus[i] = 'OP';
					}
				}
			}
		}
	}

	function reviewField(ix) {
		var colName = frm.colname[ix];
		var	columnValue = colName.value;
		var	typeValue = frm.typeName[colName.selectedIndex].value;

		if (filterStatus[ix] == 'IP' || (isType(typeValue) && filterStatus[ix] == null)) {

			if (currentTypeOptions != typeValue) {
				loadTypesFrame(typeValue, columnValue);
			} else {
				self.typesFrame.unloadValue();
			}
			displayArea('typeOption' + ix);
			hideArea('defaultOption' + ix);
			showTypesFrame(ix);
		} else {
			hideArea('typesFrame');
		}
	}

	function loadTypesFrame(type, columnValue) {
		var typeClass = type;
		popupUrl = '/browse/type_options.jsp';
		popupHandler = 'TypesListRetrieve';
		popupUserId = '${esapi:encodeForJavaScript(userId)}';
		popupMailId = '${esapi:encodeForJavaScript(mailId)}';
		popupOrganizationId = '<%= oid %>';
		currentTypeOptions = type;

		if (typeClass.indexOf('-TYPE') > 0) {
			typeClass = 'TYPE';
		}

		if (typeClass.indexOf('-STATUS') > 0) {
			typeClass = 'STATUS';
		}

		popupParameters = 'type=' + type + ';typeClass=' + typeClass + ';columnValue=' + columnValue;
		document.getElementById('typesFrame').src = '<%= contextPath %>/system/iframe_html.jsp';
	}

	function showTypesFrame(ix) {
		var inputBox = frm.filterLabel_txt[ix];
		var typesFrame = document.getElementById('typesFrame');
		currentIndex = ix;

		typesFrame.style.left = componentPosition_getPageOffsetLeft(inputBox) + 'px';
        typesFrame.style.top = componentPosition_getPageOffsetTop(inputBox) + 'px';
        displayArea('typesFrame');
	}

	function hideTypesFrame() {
		filterStatus[currentIndex] = 'OP';
		setDefaultVisibility(currentIndex);
		frm.filter_txt[currentIndex].focus();
	}

	function assignValue(optionValue, optionText) {
		frm.filter_txt[currentIndex].value = optionValue;
        frm.filterLabel_txt[currentIndex].value = optionText;
		hideArea('typesFrame');
	}

	function setDefaultVisibility(ix) {
		hideArea('typesFrame');
		displayArea('defaultOption' + ix);
		hideArea('typeOption' + ix);
		frm.filter_txt[ix].value = '';
		frm.filterLabel_txt[ix].value = '';
	}

  function correctMe(ix) {
    frmcolname = selectValue(frm.colname[ix]);
    frmFilter = frm.filter_txt[ix];

    trim(frmFilter);

    if (frmFilter.value == '%') {
    	frmFilter.value = '';
    }
    frm.filter_txt[ix].value = frmFilter.value.toUpperCase();
  }

  function selectValue ( lstObject ) {
    var list = lstObject;
    var frmcolname = list.options[list.selectedIndex].value;
    return frmcolname;
  }

  function setFilterOptions() {
    var myTable = document.getElementById("filterOptions");
    var filterFields = "";
    var previousLogicalOperator = "AND";

    for (var i=0; i < myTable.rows.length - 2; i++) {
      var filterColumn = frm.colname[i].value;
      var filterTxt = trim(frm.filter_txt[i]);
      var sort = frm.sort[i].value;

      if (!isEmpty(filterTxt) || sort != "N") {
        var operator = frm.operator[i].value;
        var logicalOperator = frm.logicalOperator[i].value;

        if (filterTxt.indexOf("%") >= 0) {
          if (operator == "<>") {
            frm.operator[i][frm.operator[i].selectedIndex].value = "NOT LIKE";
          } else {
            frm.operator[i][frm.operator[i].selectedIndex].value = "LIKE";
          }
        }

        filterFields = filterFields + "<input type=hidden name=\"" + filterColumn + "\" value=\"" + filterTxt + "\">";
        filterFields = filterFields + "<input type=hidden name=\"" + filterColumn + "_operator\" value=\"" + operator + "\">";
        filterFields = filterFields + "<input type=hidden name=\"" + filterColumn + "_logic\" value=\"" + previousLogicalOperator + "\">";
        filterFields = filterFields + "<input type=hidden name=\"" + filterColumn + "_original\" value=\"Y\">";
        filterFields = filterFields + "<input type=hidden name=\"" + filterColumn + "_sort\" value=\"" + sort + "\">";

        previousLogicalOperator = logicalOperator;

      }
    }
    return filterFields;
  }

function setStrDate(date,dayLater,monthLater,yearLater) {
	var dtDate = new Date;
	parseDate(date.value);
	dtDate.setYear(eval(strYear)+eval(yearLater));
	dtDate.setMonth(eval(strMonth)-1+eval(monthLater));
	dtDate.setDate(eval(strDay)+eval(dayLater));

	return dtDate;
}

function setDayOfWeek(dateFld) {
	if (frm.ReportQueue_frequency[2].checked) {
		var dDate = new Date;
		var dateValue = "";

		// If the start-date put is wrong format or if it doesn't have a date
  		if (chkdate(dateFld) && dateFld.value == "") {
	  		dDate = setStrDate(dateFld,0,0,0);
    	}
    	// Get the day of the week corresponding to the date
    	var dayOfWeek = dDate.getDay();
    	frm.ReportQueue_deliveryDay[dayOfWeek].click();
	}
}

function setStartDate() {
	if (frm.ReportQueue_frequency[5].checked)
		frm.ReportQueue_startRun.value = frm.ReportQueue_nextRun.value;
}

function setStartDate(dayOfWeek) {
	// Determination of day, month and year values initials
	var newDate = new Date;
	// Evaluation in the 7 days of the week to find the date close to the date determinated before
	for (i=0;i<7;i++) {
	    frm.tempStartDate.value = frm.ReportQueue_startDate.value;
		newDate = setStrDate(frm.tempStartDate,i,0,0);
		if (newDate.getDay()==dayOfWeek)
		frm.ReportQueue_startDate.value = formatDate(newDate);
	}
}

function controlStartDate() {
    frm.tempStartDate.value = frm.ReportQueue_startDate.value ;
    frm.tempEndDate.value = frm.ReportQueue_endDate.value ;

    i_sDate = frm.tempStartDate ;
    i_eDate = frm.tempEndDate;

	i_dDay = frm.ReportQueue_deliveryDay;
	// d_sDate (start date) setting value
	var d_sDate = startDate;
	if (!checkDate(i_sDate) || i_sDate.value == "") {
		var newd = new Date;
		i_sDate.value = formatDate(newd);
	}
    else {
		var d_sDateTemp = setStrDate(i_sDate,0,0,0);
		if (d_sDateTemp >= startDate) {
			d_sDate = d_sDateTemp;
			i_sDate.value = formatDate(d_sDate);
		}
	}
    // d_eDate (end date) setting value equal to start date
    var d_eDate = d_sDate;
    // d_eDate (end date) setting final value according to frequency
	if (frm.ReportQueue_frequency[0].checked) {
		frm.ReportQueue_endDate.disabled = true;
		d_eDate = new Date();
	}
	else {
		frm.ReportQueue_endDate.disabled = false;
		i_eDate.value="";

	    if (frm.ReportQueue_frequency[1].checked) {
	    	d_eDate = setStrDate(i_sDate,1,0,0);
		}
	    if (frm.ReportQueue_frequency[2].checked) {
	    	var dayOfWeek = d_sDate.getDay();
	    	i_dDay[dayOfWeek].click();
	    	d_eDate = setStrDate(i_sDate,7,0,0);
		}
		else if (frm.ReportQueue_frequency[3].checked) {
	    	d_eDate = setStrDate(i_sDate,0,1,0);
		}
		else if (frm.ReportQueue_frequency[4].checked) {
	    	d_eDate = setStrDate(i_sDate,0,3,0);
		}
		else if (frm.ReportQueue_frequency[5].checked) {
	    	d_eDate = setStrDate(i_sDate,0,0,1);
		}
	}

    frm.ReportQueue_endDate.value = formatDate(d_eDate);
}

function controlEndDate() {
    frm.tempStartDate.value = frm.ReportQueue_startDate.value ;
    frm.tempEndDate.value = frm.ReportQueue_endDate.value ;

    i_sDate = frm.tempStartDate ;
    i_eDate = frm.tempEndDate;
	i_dDay = frm.ReportQueue_deliveryDay;
	// d_sDate (start date) setting value
	var d_sDate = setStrDate(i_sDate,0,0,0);

	// d_sDate (start date) setting value
	var d_eDate = d_sDate;
	if (checkDate(i_eDate)) {
		var d_eDate = setStrDate(i_eDate,0,0,0);
	}
	if (!checkDate(i_eDate) || i_eDate.value == "" || d_eDate < d_sDate) {
	    // d_eDate (end date) setting value equal to start date
	    d_eDate = d_sDate;
	    // d_eDate (end date) setting final value according to frequency
	    if (frm.ReportQueue_frequency[1].checked) {
	    	var d_eDate = setStrDate(i_sDate,1,0,0);
		}

	    if (frm.ReportQueue_frequency[2].checked) {
	    	var dayOfWeek = d_sDate.getDay();
	    	i_dDay[dayOfWeek].click();
	    	var d_eDate = setStrDate(i_sDate,7,0,0);
		}
		if (frm.ReportQueue_frequency[3].checked) {
	    	var d_eDate = setStrDate(i_sDate,0,1,0);
		}
	    if (frm.ReportQueue_frequency[4].checked) {
	    	var d_eDate = setStrDate(i_sDate,0,3,0);
		}
		if (frm.ReportQueue_frequency[5].checked) {
	    	var d_eDate = setStrDate(i_sDate,0,0,1);
		}
		if (frm.ReportQueue_frequency[0].checked)
			frm.ReportQueue_endDate.disabled = true;
		else
			frm.ReportQueue_endDate.disabled = false;

		i_eDate.value = formatDate(d_eDate);
	}
    frm.ReportQueue_endDate.value =  formatDate(setStrDate(i_eDate,0,0,0)) ;
}

function setFrequencyInterface(n_frq) {
    frm.tempStartDate.value = frm.ReportQueue_startDate.value ;

    i_sDate = frm.tempStartDate ;
	i_frqcy = frm.ReportQueue_frequency;
	if (i_frqcy[2].checked) {
		weeklyModuleShowHide(1);
		setDayOfWeek(i_sDate);
	}
	else
		weeklyModuleShowHide(0);

	controlStartDate();
	for (i=0;i<6;i++) {
		hideArea("fb"+i);
		displayArea("fn"+i);
	}
	hideArea("fn"+n_frq);
	displayArea("fb"+n_frq);
}

function add(){
	for(var i=0; i<frm.select.length;i++)
	{
		if(frm.select.options[i].selected)
		{
		var pos = frm.ReportQueue_sendTo.value.indexOf(frm.select.options[i].value);
		if(pos == -1)
		{
			if(checkemail())
			{
				if(frm.ReportQueue_sendTo.value == "")
				{
					frm.ReportQueue_sendTo.value+=frm.select.options[i].value;
				}
				else
				{
					frm.ReportQueue_sendTo.value+=";"+frm.select.options[i].value;
				}
			}
		}
		else
		{
			var pos_end = frm.ReportQueue_sendTo.value.indexOf(";", pos);
			if(pos_end == -1)
			{
				var subcadena_0 = frm.ReportQueue_sendTo.value.substring(0,pos-1);
				frm.ReportQueue_sendTo.value="";
				frm.ReportQueue_sendTo.value+=subcadena_0;
			}
			else
			{
				var subcadena_0 = frm.ReportQueue_sendTo.value.substring(0,pos-1+1);
				var subcadena_1 = frm.ReportQueue_sendTo.value.substring(pos,pos_end+1);
				var subcadena_2 = frm.ReportQueue_sendTo.value.substring(pos_end+1,frm.ReportQueue_sendTo.value.length);
				frm.ReportQueue_sendTo.value="";
				frm.ReportQueue_sendTo.value+=subcadena_0+subcadena_2;
			}
		}
		}
	}
}

	function removeBlanks(str)
	{
		var emailArray = str.split(String.fromCharCode(32));
		str = "";
		for (x=0; x<emailArray.length; x++)
		{
			str+=emailArray[x];
		}
		return str;
	}

	function checkemail()
	{
		var str = frm.ReportQueue_sendTo.value;
		str = removeBlanks(str);
		frm.ReportQueue_sendTo.value=str;
		var emailArray = str.split(";");
		for (x=0; x<emailArray.length; x++)
		{
			if (checkOneEmail(emailArray[x]))
				hideArea("mess");
			else
			{
				displayArea("mess");
				return false;
			}
		}
		return true;
	}

	function checkOneEmail(email)
	{
		var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i
		if (!filter.test(email))
		{
			alert("Please enter a valid email address for: "+ email);
			return false;
		}
		return true;
	}

<% if (action.equalsIgnoreCase("old")) { %>
	var allTime = frm.ReportQueue_deliveryTime.value;
	var arrayAllTime = allTime.split(":");
	if (eval(arrayAllTime[0])>12) {
		frm.ReportQueue_deliveryTime.value = (eval(arrayAllTime[0])-12) + ":" + arrayAllTime[1];
		frm.ReportQueue_mt.selectedIndex = 1;
	}
<% } %>

	function isCriteriaEntered()
	{
		var myTable = document.getElementById("filterOptions");

		for (var i=0; i < myTable.rows.length - 2; i++)
		{
			var filterTxt = trim(frm.filter_txt[i]);

			if (!isEmpty(filterTxt))
			{
				return true;
			}
		}
		return false;
	}

	function resizeTypeOptions(optionsSize) {
		document.getElementById('typesFrame').style.height = (optionsSize + 4) + 'em';
	}

	function componentPosition_getPageOffsetLeft(el){
	    var ol=el.offsetLeft;
	    while((el=el.offsetParent) != null){
	        ol += el.offsetLeft;
	    }
	    return ol;
	}

	function componentPosition_getPageOffsetTop(el){
	    var ot=el.offsetTop;
	    while((el=el.offsetParent) != null){
	        ot += el.offsetTop;
	    }
	    return ot;
	}

	function checkTypeDate() {

		var myTable = document.getElementById("filterOptions");
		var result = true;

		for ( var i=0; i < myTable.rows.length - 2; i++ ) {
      		var filterTxt = frm.filter_txt[i];
      		var typeName = frm.typeName[i].value;
			if( filterTxt && frm.typeName[frm.colname[i].selectedIndex].value == "DATE" && !chkDateValid(frm.filter_txt[i])&& frm.filter_txt[i].value.indexOf(":") < 0) {
				result = false;
				break;
		     }
		}

		return result;
	}


	function chkDateValid(sDate)
	{
	  	var date = new String(sDate.value);
    	strSeparatorArray = new Array("-");
    	var cadena = date.split("-");
      if ((date.value != undefined)&&(cadena[0].length != "4")){
              return false;
           }

		if( date == "" || date.indexOf(":")==0){
			return true;
		}
		if(date.indexOf("/")>0){
			return false;
		}
		return chkdate(sDate);

	}

    function strRestoreFormat(sDate, sFormat) {
		if (!sDate) return sDate  ;
		if (sDate.length < 1) return sDate ;

		var dtSep = "-" ;
		var fmtSep = "-" ;
		var retDay = "" ;
		var retMon = "" ;
		var retYear = "" ;
		var stdFormat = "mm-dd-yyyy" ;
		var retDate = myDateFormat.toLowerCase() ;

		if (sDate.indexOf("/") >= 0) dtSep = "/" ;
		var dtArray = sDate.split(dtSep) ;
		var fmtArray = stdFormat.split("-") ;

		for (var x = 0; x < fmtArray.length; x++) {
			if (fmtArray[x].toLowerCase() == "dd") {
				retDay = dtArray[x] ;
			} else if (fmtArray[x].toLowerCase() == "mm") {
			    retMon = dtArray[x] ;
			} else {
			    retYear = dtArray[x] ;
			}
		}

		retDate = retDate.replace(/mm/g, retMon) ;
		retDate = retDate.replace(/dd/g, retDay) ;
		retDate = retDate.replace(/yyyy/g, retYear) ;

		return retDate ;
    }

	function strStdFormat(sDate)
	{
		// Format date based on date format back to std mm/dd/yyyy format.
		if (!sDate) return sDate  ;
		if (sDate.length < 1) return sDate ;

		var dtSep = "-" ;
		var fmtSep = "-" ;
		var retDay = "" ;
		var retMon = "" ;
		var retYear = "" ;
		var retDate = "" ;

		if (sDate.indexOf("/") >= 0) dtSep = "/" ;
		if (myDateFormat.indexOf("/") >= 0) fmtSep = "/" ;

		var dtArray = sDate.split(dtSep) ;
		var fmtArray = myDateFormat.split(fmtSep) ;

		for (var x = 0; x < fmtArray.length; x++) {
			if (fmtArray[x].toLowerCase() == "dd") {
				retDay = dtArray[x] ;
			} else if (fmtArray[x].toLowerCase() == "mm") {
			    retMon = dtArray[x] ;
			} else {
			    retYear = dtArray[x] ;
			}
		}

		retDate = retMon + "-" + retDay + "-" + retYear ;

		return retDate ;
	}

// End Hide script -->
</script>