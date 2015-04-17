<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager"%>

<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.lang.reflect.*" %>

<%
    String entityName=null;

	Field [] entityAttribute =	null;

    if (request.getAttribute("entityAtributesReflect")!=null)
    {
    	entityAttribute =(Field [])request.getAttribute("entityAtributesReflect");
    	System.out.print("entityAttribute.length= "+ entityAttribute.length);
     	for (int z =0 ; z < entityAttribute.length ; z ++)
     	{
     	System.out.print(entityAttribute[z].getName()+ " ; ");
     	}

    }

%>
<table border=3 cellpadding=2 cellspacing=0 width=680px >
<tr>
<td align="center">
<table border=3 cellpadding=2 cellspacing=0 width=680px >

<tr>

	<td align="center">
		<table border=3 cellpadding=2 cellspacing=0 width="650px" >
		<tr bgcolor="gainsboro">
			<th>
				<table border=0 cellpadding=0 cellspacing=0 width="100%">
				<tr bgcolor="gainsboro">
					<td>
						<th align="left"><a href="javascript: void(0);">Send Options</a></th>
				   </td>

				</tr>

				</table>
				<tr>
					<td>
					<select name="entityName" selected size=1" onchange="selectEntity(entityName.value); void(0);">
						  <option value="InvoiceHeader"  selected>InvoiceHeader</option>
						  <option value="InvoiceLine"  selected>Invoice Line </option>
  						  <option value="RequisitionHeader"  selected>Requisition Header</option>
 						  <option value="RequisitionLine"  selected>Requisition Line</option>
						  <option value="CatalogItem"  selected>Catalog Item</option>
					  </select>&nbsp;
					</td>
					<td width=50% align=center><a href="javascript: processRule(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Return"></a></td>
				</tr>
			</th>

	</tr>

	<tr>
	<table border=3 cellpadding=2 cellspacing=0 width="650px" >
 	<%@ include file="/admin/validate_management/validate_rules_filter_options_entityNames.jsp"%>
	</table>
	</tr>

	</table>
</td>

</tr>


<tr><td align="center">
<table border=3 cellpadding=2 cellspacing=0 width=680px >
<tr><td align="center">
<table border=1 cellpadding=2 cellspacing=0 width=650px>
<tr bgcolor="gainsboro"><th align="left">Filter Options</th></tr>
<tr><td align="center">
<table border=0 cellpadding=2 cellspacing=0>
  <tr>
    <td colspan=5 align=center>
       <table>
          <tr>
          	<td valign="middle"><b>Format:&nbsp;</b></td>
            <td valign="middle"><input type="radio" name="ReportQueue_type" value="PDF"checked >pdf&nbsp;</td>
            <td valign="middle"><input type="radio" name="ReportQueue_type" value="HTM">html&nbsp;</td>
            <td valign="middle"><input type="radio" name="ReportQueue_type" value="XLS">xls&nbsp;</td>
            <td valign="middle"><input type="radio" name="ReportQueue_type" value="CSV">csv&nbsp;</td>
            <td width="50px">&nbsp;</td>
           	<td valign="middle" align="right"><b>: </b><input type="text" name="ReportQueue_alias" value="" size="40" maxlength="255"/>
           </td>
          </tr>
    </table>
    </td>
  </tr>
  <tr>
  <td align=center>
      <table id="filterOptions" border=1 cellpadding=2 cellspacing=0 width=650px align="center">
      <tr>
      <td>Column Name</td>
      <td>Operator</td>
      <td>Filter Expression(*)</td>
      <td>Logical</td>
      <td>&nbsp;</td>
      <td>Sort Option</td>
    </tr>
    <tr>
      <td>
      <select name="colname" size=1 >
          <option value=" abc" selected>abc</option>
		  <option value="xyz"> </option>
	  </select>&nbsp;
    </td>

    <td align=right>
          <tsa:hidden name="originalFilter" value="Y"/>
          <select name="operator" size=1>
      		<option value="="abc selected> =</option>
      		<option value=">" selected > > </option>
      		<option value="<" selected> < </option>
     		<option value=">="  selected> >= </option>
      		<option value="<="  selected> <= </option>
      		<option value="<>"  selected > <> </option>
          </select>&nbsp;
    </td>
    <td>
          <table border="0" cellpadding="0" cellspacing="0">
					<tr><td><div id="DateOptionA"><input name="filter_txt" size=30 type=text onChange="correctMe();"></div></td>
					<td><div id="DateOptionB" style="visibility:hidden; display:none;"></div></td>
					</tr>
				</table>
		        <div id="DateRangeOption" style="visibility:hidden; display:none;">
		        	<select name="dateRange" size=1 onchange="assignValue()">
						<option value=":today" selected="selected">today</option>
						<option value=":thisweek">this week</option>
						<option value=":thismonth">this month</option>
						<option value=":thisyear">this year</option>
				    </select>
				   <!--  <a href="javascript: showDateOption('2'); void(0);"><img src="<% //=contextPath%>/images/date.gif" alt="Show." valign="bottom" border="0"></a>-->
		        </div>
        </td>
     <td>

          <select name="logicalOperator" size=1>
          <option value=""> </option>
          <option value="AND" selected >AND</option>
          <option value="OR"  selected >OR</option>
          </select>
		  		   <tsa:hidden name="logicalOperator" value=""/>

        &nbsp;
      </td>
      <td>&nbsp;</td>
      <td>
        <select name="sort" size=1>
          <option value="N"> </option>
          <option value="ASC">Ascending</option>
          <option value="DESC">Descending</option>
        </select>&nbsp;
      </td>

    </tr>


    <br><br>
    <tr>
      <td colspan=10 align=right>
        <table border=0 cellpadding=0 cellspacing=0 width=100%>
        <tr>
          <td align=center CLASS="small">(*)Enter Dates in YYYY-MM-DD format.</td>
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

</td>
</tr>
</table>

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
				<a href="javascript: sendModuleShowHide(1); void(0);"><img src="<%//= contextPath%>/images/mail_reply.png" alt="Show." valign="bottom" border="0"></a>
				</div>
				<div id="SendOptions">
				<a href="javascript: sendModuleShowHide(0); void(0);"><img src="<% //=contextPath%>/images/bar_close.gif" alt="Close." valign="bottom" border="0"></a>
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
		<a href="javascript: showUsers(); void(0);" title="Click here to choose the value for xxx for this item or enter the value in the box on the right."></a>
	</td>
	<td nowrap>
		<textarea name="ReportQueue_sendTo" cols="100" rows="2" onChange="checkemail();"></textarea>
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
				<a href="javascript: scheduleModuleShowHide(1); void(0);"><img src="<%//=contextPath%>/images/clock.gif" alt="Show." valign="bottom" border="0"></a>
				</div>
				<div id="ScheduleOptions" style="visibility:hidden; display:none;">
				<a href="javascript: scheduleModuleShowHide(0); void(0);"><img src="<%//=contextPath%>/images/bar_close.gif" alt="Close." valign="bottom" border="0"></a>
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

<tr>
	<td align="right" width="90px"> <b></b></td>
	<td nowrap colspan="5">
      <table border=0 cellpadding=0 cellspacing=0 width=100%>
        <tr>
          <td width="15%">
          <table cellpadding="0" cellspacing="0" border="0">
          	<tr>
          		<td><input type="radio" name="ReportQueue_frequency" value="O"  checked  onClick="setFrequencyInterface(0);"></td>
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
          		<td><input type="radio" name="ReportQueue_frequency" value="D" checked  onClick="setFrequencyInterface(1);"></td>
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
          		<td><input type="radio" name="ReportQueue_frequency" value="W" checked  onClick="setFrequencyInterface(2);"></td>
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
          		<td><input type="radio" name="ReportQueue_frequency" value="M" checked  onClick="setFrequencyInterface(3);"></td>
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
          		<td><input type="radio" name="ReportQueue_frequency" value="Q"checked onClick="setFrequencyInterface(4);"></td>
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
          		<td><input type="radio" name="ReportQueue_frequency" value="A" hecked  onClick="setFrequencyInterface(5);"></td>
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
		<b><%//=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-startdate", "Start Date")%></b>
	</td>
	<td nowrap>
		<input type="text" name="ReportQueue_startDate" size="15" maxlength="10" value="AGJH" onChange="controlStartDate();" onFocus="controlStartDate();">
		<a href="javascript: show_calendar('ReportQueue_startDate', '');"><img src="<% //=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
	</td>
	<!-- A -->
	<td align="right">
		<b><% //=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-enddate", "End Date")%></b>
	</td>
	<td nowrap>
		<input type="text" name="ReportQueue_endDate" size="15" maxlength="10" value="<% //=HiltonUtility.getFormattedDate(HiltonUtility.ckNull(reportQueue.getEndDate()),oid, userDateFormat)%>" onChange="controlEndDate();" onFocus="controlEndDate();">
		<a href="javascript: show_calendar('ReportQueue_endDate', '<%//=userDateFormat%>');"><img src="<%//=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
	</td>
	<td align="right">
		<b><%//= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-time", "Time")%></b>
	</td>
	<td nowrap>
		<input type="text" name="ReportQueue_deliveryTime" size="10" maxlength="10" value="<% //if(action.equalsIgnoreCase("old")) { %><%//=reportQueue.getDeliveryTime()%><%// } else { %>00:05<%//}%>" onChange="formatTime(this);">
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
		<b><%//=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportqueue-day", "Day")%></b>
	</div>
	</td>
	<td nowrap colspan=5>
	<div id="ReportQueueWeeklyFrequency" style="visibility:hidden; display:none;">
	  <%// String opReportQueueDeliveryDay = reportQueue.getDeliveryDay();
	  	// if (opReportQueueDeliveryDay==null) { opReportQueueDeliveryDay=""; }
	  %>
		<table border=0 cellpadding=0 cellspacing=0 width="100%">
		<tr>
			<td width="11%"><input type="radio" name="ReportQueue_deliveryDay" value="Sunday"	 SUNDAY checked onClick="setStartDate(0);">Sunday</td>
			<td width="11%"><input type="radio" name="ReportQueue_deliveryDay" value="Monday"	 MONDAY checked   onClick="setStartDate(1);">Monday</td>
			<td width="11%"><input type="radio" name="ReportQueue_deliveryDay" value="Tuesday"	 TUESDAY onClick="setStartDate(2);">Tuesday</td>
			<td width="11%"><input type="radio" name="ReportQueue_deliveryDay" value="Wednesday" WENSDAY checked onClick="setStartDate(3);">Wednesday</td>
			<td width="11%"><input type="radio" name="ReportQueue_deliveryDay" value="Thursday"	 THURSDAY checked  onClick="setStartDate(4);">Thursday</td>
			<td width="11%"><input type="radio" name="ReportQueue_deliveryDay" value="Friday"	 FRIDAY checked onClick="setStartDate(5);">Friday</td>
			<td width="11%"><input type="radio" name="ReportQueue_deliveryDay" value="Saturday"	 SATURDAY checked  onClick="setStartDate(6);">Saturday</td>
			<td width="23%">&nbsp;</td>
		</tr>
		</table>
	</div>
	</td>
</tr>
<tr>
	<td align="center" colspan=6></td>
</tr>
</table>
</div>
</td></tr>
</table>
</td></tr>
</table>



<div id="mess" style="visibility:hidden; display:none;">
<br><font color="red">* Invalid email address in the Send To field.</font>
</div>

<br>

</td>
</tr>
</table>

<tsa:hidden name="ReportQueue_sendToCode" value="sendToCode"/>
<tsa:hidden name="ReportQueue_dateSent" value="dateSent"/>
<tsa:hidden name="ReportQueue_timeSent" value="timeSent"/>
<tsa:hidden name="ReportQueue_dateAdded" value="dateAdded"/>
<tsa:hidden name="ReportQueue_timeAdded" value="timeAdded"/>
<tsa:hidden name="ReportQueue_status" value="status"/>
<tsa:hidden name="ReportQueue_whereClause" value="whereClause"/>
<tsa:hidden name="ReportQueue_nextRun" value="nextRun"/>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
frm = document.purchaseform;

var entityName=frm.entityName.value;

function selectEntity(entityName) {
	var entityFullName = "com.tsa.puridiom.entity."+entityName;
	var newInputField = "<input type='hidden' name='entityFullName' value='" + entityFullName + "'>";
	setHiddenFields(newInputField);
	doSubmit('/admin/validate_management/validate_rules_filter_options_form.jsp', 'EntityAtributesReflect');
}

function processRule() {
	alert(entityName);
	<!--
	//var entityFullName = "com.tsa.puridiom.entity."+entityName;
	//var newInputField = "<input type='hidden' name='entityFullName' value='" + entityFullName + "'>";
	//setHiddenFields(newInputField);
	//doSubmit('/admin/validate_management/validate_rules_filter_options_form.jsp', 'ValidateRulesGenerator');
	-->
}

</script>
