<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.common.documents.*" %>
<%
	  BrowseObject browseObject = (BrowseObject) request.getAttribute("browseObject");
	  BrowseColumn browseColumns[] = browseObject.getBrowseColumns();
	  String reportModule = (String)request.getAttribute("reportModule");
	  String fromPage = HiltonUtility.ckNull((String)request.getAttribute("fromPage"));
	  PropertiesManager	propertiesManager 	= PropertiesManager.getInstance(oid);
	  String s_date_format 			= propertiesManager.getProperty("MISC", "DateFormat", "MM/dd/yyyy");
	  ReportQueue reportQueue = (ReportQueue)request.getAttribute("reportQueue");
	  List reportUserList = (List)request.getAttribute("reportUserList");
	  UserProfile owner = UserManager.getInstance().getUser(oid, uid);
	  String	userMailId = owner.getMailId();
	  List MailUserAll=(List)request.getAttribute("userProfile");
	  List whereClauseList = new ArrayList();
	  String filter = HiltonUtility.ckNull((String) request.getAttribute("tableType"));

	  String action = "new";

	  if(reportQueue != null) {
	    action = "old";
	    whereClauseList = (List) request.getAttribute("whereClauseList");
	  } else {
	    reportQueue = new ReportQueue();
	    reportQueue.setStartDate(d_today);
	    reportQueue.setEndDate(d_today);
	  }

	  int reportListSize = 0;

	  if(reportUserList != null) {
		  reportListSize = reportUserList.size();
	  }

	  String moduleRepQueue = reportModule;

	/*	added for System Table */
	String tableType = HiltonUtility.ckNull((String) request.getAttribute("StdTable_id_tableType"));
	if (!HiltonUtility.isEmpty(tableType) && browseObject.getBrowseName().indexOf("std-table") >= 0)
	{
		String title = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, tableType, "");
		if (!HiltonUtility.isEmpty(title)){		browseObject.setTitle(title + " Browse");		}
	}
	String jasperFile = (String)request.getAttribute("jasperFile");
	if (HiltonUtility.isEmpty(jasperFile)){		jasperFile = browseObject.getBrowseName();		}

	String alias = reportQueue.getAlias();
	if (HiltonUtility.isEmpty(alias))
	{
		String reportTitleName = (String)request.getAttribute("reportTitleName");
		if (HiltonUtility.isEmpty(reportTitleName)){		reportTitleName = browseObject.getTitle();		}
		//out.println("<!-- reportTitleName: " + reportTitleName + "-->");
		alias = 	 reportTitleName;
		//out.println("<!-- alias: " + alias + "-->");
	}

%>

<br>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<tsa:hidden name="fromPage" value="<%=fromPage%>"/>

<tsa:hidden name="ReportQueue_icReportQueue"	value="<%=reportQueue.getIcReportQueue()%>"/>
<tsa:hidden name="ReportQueue_module"			value="<%=reportModule%>"/>
<tsa:hidden name="ReportQueue_owner"			value="${userId}"/>
<tsa:hidden name="ReportQueue_sendFrom"		value="<%=owner.getMailId()%>"/>
<tsa:hidden name="ReportQueue_name"			value="<%=browseObject.getBrowseName()%>"/>
<tsa:hidden name="ReportQueue_title"			value="<%=browseObject.getTitle()%>"/>
<tsa:hidden name="ReportQueue_publicView"		value="<%=reportQueue.getPublicView()%>"/>
<tsa:hidden name="allowBrowse" 				value="true"/>
<tsa:hidden name="tableType" value="<%=tableType%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
  <td valign=top width=150px height=30px>
    <table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
    <tr>
      <td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
    </tr>
    <tr>
      <td nowrap class=hdr12 valign=middle>
        <div style="margin-left:10px; margin-right:10px" class="hdr12"><%=browseObject.getTitle()%> Options</div>
      </td>
    </tr>
    </table>
  </td>
  <td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
  <td valign=bottom align=right width=*>
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

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td align="center" class="error">* We strongly suggest entering filter criteria to reduce filling time for generating reports.</td>
</tr>
</table>

<%@ include file="/browse/browse_filter_reportqueue_options_form.jsp" %>

<table border=0 cellspacing=0 cellpadding=2 width="680px">
<tr>
		<td align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<% if(action.equalsIgnoreCase("new")) {%>
			<td align="center">
				<!--% if(action.equalsIgnoreCase("new") && role.getAccessRights("REPORTS") > 2 && role.getAccessRights(moduleRepQueue) > 2) {%-->
				<div id="registerReport" style="visibility: visible;">
					<div id="pxbutton"><a href="javascript: confirmReportAction('C'); void(0);" >Submit</a></div>
				<div>
			</td>
			<% } else {%>
			<td align="center">
				<!--% if(action.equalsIgnoreCase("new") && role.getAccessRights("REPORTS") > 2 && role.getAccessRights(moduleRepQueue) > 2) {%-->
				<div id="registerReport" style="visibility: visible;">
					<div id="pxbutton"><a href="javascript: confirmReportAction('U'); void(0);">Submit</a></div>
				<div>
			</td>
			<% } %>
			<td align="center">
				<div id="previewReport" style="visibility: visible;">
					<div id="pxbutton"><a href="javascript: confirmReportAction('R'); void(0);" >Preview</a></div>
				</div>
			</td>
			<% if(!action.equalsIgnoreCase("new")) {%>
			<td align="center">
				<div id="deleteReport" style="visibility: visible;">
					<div id="pxbutton"><a href="javascript: confirmReportAction('D'); void(0);">Delete</a></div>
				</div>
			</td>
			<% } %>
			<td align="center">
				<div id="pxbutton"><a href="javascript: returnToList(); void(0);">Return</a></div>
			</td>
		</tr>
		</table>
		</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>
<script value=JavaScript>
<!-- Hide script

  var frm = document.purchaseform;

<% if(action.equalsIgnoreCase("old")) { %>
	if(!frm.ReportQueue_frequency[0].checked) {
		scheduleModuleShowHide(1);
	}
			<%	if (!reportQueue.getOwner().equalsIgnoreCase(uid)) { %>
			checkInputSettings();
			allowInputEdit(frm.ReportUserAccess, true);
			<%	} %>
<% } %>

  function registerReportQueue()
  {
    if(checkemail())
	{
    	frm.browseName.value = "${esapi:encodeForJavaScript(browseObject.browseName)}";
		popupParameters = "browseName=" + "${esapi:encodeForJavaScript(browseObject.browseName)}";
    	var newInputField = "<input type='hidden' name='execute' value='N'>";
		setHiddenFields(newInputField);
		setTime(frm.ReportQueue_deliveryTime);
		if (frm.ReportPublic.checked) {
			frm.ReportQueue_publicView.value = "Y";
		}
		else {
			frm.ReportQueue_publicView.value = "N";
		}
		if (frm.ReportQueue_alias.value == "") {
			frm.ReportQueue_alias.value = "<%=headerEncoder.encodeForJavaScript(browseObject.getTitle())%>";
		}
		if (isEmpty(frm.ReportQueue_endDate.value)) {
			alert("Please enter an End Date.");
		}
		else {
    		doSubmit('/reportqueue/reportqueue_add_confirmation.jsp','ReportQueueAdd');
    	}
    }
  }

  function updateReportQueue()
  {
    if(checkemail())
	{
    	frm.browseName.value = "${esapi:encodeForJavaScript(browseObject.browseName)}";
		popupParameters = "browseName=" + "${esapi:encodeForJavaScript(browseObject.browseName)}";
		var sReportUserList = "";
    	var newInputField = "<input type='hidden' name='execute' value='N'>";
    	<%	if (reportListSize > 0) {
				for (int x = 0; x < reportListSize; x++) {
					ReportUser reportUser = (ReportUser) reportUserList.get(x);	%>
		if (frm.ReportUserAccess<% if (reportListSize > 1) { %>[<%=x%>]<% } %>.checked && frm.ReportUserPublic<% if (reportListSize > 1) { %>[<%=x%>]<% } %>.value=="N") {
			sReportUserList = sReportUserList + "-<%=reportUser.getIcReportUser()%>;Y";
		}
		else
		if (!frm.ReportUserAccess<% if (reportListSize > 1) { %>[<%=x%>]<% } %>.checked && frm.ReportUserPublic<% if (reportListSize > 1) { %>[<%=x%>]<% } %>.value=="Y") {
			sReportUserList = sReportUserList + "-<%=reportUser.getIcReportUser()%>;N";
		}
		<%		}	%>
		sReportUserList = sReportUserList.substring(1, sReportUserList.length);
		<%	}	%>
		newInputField = newInputField + "<input type='hidden' name='sReportUserList' value='"+sReportUserList+"'>";
		setHiddenFields(newInputField);
		setTime(frm.ReportQueue_deliveryTime);
		if (frm.ReportPublic.checked) {
			frm.ReportQueue_publicView.value = "Y";
		}
		else {
			frm.ReportQueue_publicView.value = "N";
		}
		if (frm.ReportQueue_alias.value == "") {
			frm.ReportQueue_alias.value = "<%=headerEncoder.encodeForJavaScript(browseObject.getTitle())%>";
		}
		if (isEmpty(frm.ReportQueue_endDate.value)) {
			alert("Please enter an End Date.");
		}
		else {
    		doSubmit('/reportqueue/reportqueue_add_confirmation.jsp','ReportQueueUpdate');
    	}
    }
  }

  function deleteReportQueue()
  {
  	<% if (fromPage.indexOf("types")>0) { %>
    var newInputField = "<input type='hidden' name='Report_reportModule' value='<%=headerEncoder.encodeForJavaScript(reportModule)%>'>";
    setHiddenFields(newInputField);
    doSubmit('/reports/reportqueue_types.jsp', 'ReportQueueDelete;ReportRetrieveByModule');
    <% } else if (fromPage.indexOf("browse")>0) { %>
    clearFilters();
    setAdvancedFilter("ReportQueue_owner", "=", "${esapi:encodeForJavaScript(userId)}", "AND", "Y", "N");
	frm.browseName.value = 'report_queue';
	doSubmit('/browse/browse.jsp', 'ReportQueueDelete;BrowseRetrieve');
    <% } %>
  }

  function printPreview() {
  	/*
  	if (!isCriteriaEntered()) {
		alert("Please specify at least one search criteria.");
		return false;
	}
	*/
	popupParameters = "browseName=${esapi:encodeForJavaScript(browseObject.browseName)};execute=N";
	var rqtype = "";

	if(frm.ReportQueue_type.length != undefined){
		for (x=0; x<frm.ReportQueue_type.length; x++) {
			if (frm.ReportQueue_type[x].checked)
				rqtype = frm.ReportQueue_type[x].value;
				popupParameters = popupParameters + ";format=" + frm.ReportQueue_type[x].value;
		}
	}else{
		if (frm.ReportQueue_type.checked)
				rqtype = frm.ReportQueue_type.value;
				popupParameters = popupParameters + ";format=" + frm.ReportQueue_type.value;
	}
	popupParameters = popupParameters + ";ReportQueue_type=" + rqtype;
	popupParameters = popupParameters + ";ReportQueue_module=" + frm.ReportQueue_module.value;
	popupParameters = popupParameters + ";ReportQueue_name=${esapi:encodeForJavaScript(browseObjectbrowseName)}";
	popupParameters = popupParameters + ";ReportQueue_sendFrom=" + frm.ReportQueue_sendFrom.value;
	popupParameters = popupParameters + ";ReportQueue_sendT=" + frm.ReportQueue_sendTo.value;
	//popupParameters = popupParameters + ";ReportQueue_sendTo=" + frm.ReportQueue_sendTo.value; alert(frm.ReportQueue_sendTo.value);
	if (frm.ReportQueue_alias.value == "") {
		popupParameters = popupParameters + ";ReportQueue_alias=<%=headerEncoder.encodeForJavaScript(browseObject.getTitle())%>";
	}
	else {
		popupParameters = popupParameters + ";ReportQueue_alias=" + frm.ReportQueue_alias.value;
	}

  	popupParameters = popupParameters + ";viewNow=Y";
  	<% for (int i = 0; i < filterRows; i++) { %>
  		popupParameters = popupParameters + ";colname=" + frm.colname[<%=i%>].value;
  		popupParameters = popupParameters + ";filter_label=" +	frm.colname[<%=i%>].options[frm.colname[<%=i%>].selectedIndex].text;
  		popupParameters = popupParameters + ";filter_operator=" +	frm.operator[<%=i%>].options[frm.operator[<%=i%>].selectedIndex].text;

		if (frm.filter_txt[<%=i%>].value.indexOf("%") >= 0) {
	      if (frm.operator[<%=i%>].value == "<>") {
	        popupParameters = popupParameters + ";operator=NOT LIKE";
	      } else {
	        popupParameters = popupParameters + ";operator=LIKE";
	      }
	    }
	    else {
		  popupParameters = popupParameters + ";operator=" + frm.operator[<%=i%>].value;
		}
		popupParameters = popupParameters + ";filter_txt=" + frm.filter_txt[<%=i%>].value;
		popupParameters = popupParameters + ";logicalOperator=" + frm.logicalOperator[<%=i%>].value;
		popupParameters = popupParameters + ";originalFilter=" + frm.originalFilter[<%=i%>].value;
		popupParameters = popupParameters + ";sort=" + frm.sort[<%=i%>].value;
	<% } %>
	<% if (!HiltonUtility.isEmpty(filter))
	   {
	     if (filter.indexOf("autoacc")<0) { %> // (UserProfile.status <> '03')
		popupParameters = popupParameters + ";colname=StdTable_id_tableType";
		popupParameters = popupParameters + ";operator==";
		popupParameters = popupParameters + ";filter_txt=" + '<%=filter%>';
		popupParameters = popupParameters + ";logicalOperator=AND";
		popupParameters = popupParameters + ";originalFilter=Y";
		popupParameters = popupParameters + ";sort=ASC";
	<%   }
	   }
		if (propertiesManager.getProperty("REPORT OPTIONS","SHOW INACTIVE USERS","Y").equalsIgnoreCase("N") && (browseObject.getBrowseName().indexOf("users"))>0){ %>
			popupParameters = popupParameters + ";colname=StdTable_id_tableType";
			popupParameters = popupParameters + ";operator=" + '<>';
			popupParameters = popupParameters + ";filter_txt=" + '3';
			popupParameters = popupParameters + ";logicalOperator=AND";
			popupParameters = popupParameters + ";originalFilter=Y";
			popupParameters = popupParameters + ";sort=ASC";
	   <%}%>
	doSubmitToLookup('/system/popupDocAttachment.jsp', 'ReportGetNumberRows', 'width=775px', 'height=850px');
  }

  function returnToList(){
  	<% if (fromPage.indexOf("types")>0) { %>
    setReportsQueue('<%=headerEncoder.encodeForJavaScript(reportModule)%>');
    <% } else if (fromPage.indexOf("browse")>0) { %>
    clearFilters();
    viewReportQueueScheduled();
    <% }  else {%>
    	setOriginalFilter("StdTable_id_tableType", "=","<%=tableType%>");
    	frm.browseName.value = 'stdtable-admin';
    	doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
    <% } %>
  }

  function viewReportQueueScheduled() {
  	setAdvancedFilter("ReportQueue_owner", "=", "${esapi:encodeForJavaScript(userId)}", "AND", "Y", "N");
	browse('report_queue');
  }

  function showScheduleOptions(){
  	displayArea('ScheduleOptions');
  }


function scheduleModuleShowHide(flg){
	switch (flg) {
		case 0:
			hideArea('ScheduleOptions'); displayArea('showSchedule'); break;
		case 1:
			displayArea('ScheduleOptions'); hideArea('showSchedule'); break;
	}
}

function sendModuleShowHide(flg){
	switch (flg) {
		case 0:
			hideArea('SendOptions'); displayArea('showSend'); break;
		case 1:
			displayArea('SendOptions'); hideArea('showSend'); break;
	}
}

function weeklyModuleShowHide(flg) {
  	switch (flg) {
		case 0:
			hideArea('ReportQueueWeeklyFrequency'); break;
		case 1:
			displayArea('ReportQueueWeeklyFrequency'); break;
	}
}

function tformat (time)
{
    var x = time.indexOf(":");
    if (x<0)
    	x = time.length;
	var h = time.substring(0,x);
	time = time.substring(x+1,time.length);
	x = time.indexOf(":");
    if (x<0)
    	x = time.length;
    	m = time.substring(0,x);
	if (h > 12 || (h == 12 && m > 59)) {
		h = 12; m = 0; s = 0;
	}
	else if (m > 59) {
		h = eval(h)+1;
		m = eval(m)-60;
	}
    return set2str(eval(h))+":"+set2str(eval(m));
}

function tfilter( objectFld )
{
    var cmp = "0123456789:";
    var x = objectFld.value;
    var w = "";

    for ( var i = 0; i < x.length; i++) {
      tst = x.substring(i,i+1);
      if (cmp.indexOf(tst) >= 0) { w += tst; }
    }
    if ( w.length != x.length)
      objectFld.value = w;
    if ( w.length == 0 )
      w = "0";
    return w;
}

function formatTime(fld)
{
    fld.value = tformat(tfilter(fld));
}

function setTime(i_time)
{
    var time = i_time.value;
    var arrayTime = time.split(":");
    if (frm.ReportQueue_mt.value=="0" && arrayTime[0]=="12")
	{
		arrayTime[0]= "00";
	}
	else if (frm.ReportQueue_mt.value=="1" && arrayTime[0]!="12")
	{
    	arrayTime[0] = eval(arrayTime[0])+12;
	}
    i_time.value = arrayTime[0] + ":" + arrayTime[1];
}

function set2str(i) {
	if (i<10) i="0"+i;
	return i
}

  function intFilter( objectFld )
  {
    var cmp = "0123456789";
    var x = objectFld.value;
    var w = "";

    for ( var i = 0; i < x.length; i++) {
      tst = x.substring(i,i+1);
      if (cmp.indexOf(tst) >= 0) { w += tst; }
    }
    if ( w.length != x.length)
      objectFld.value = w;
    if ( w.length == 0 )
      w = "0";
    return w;
  }

<% if (opReportQueueFrequency.equalsIgnoreCase("W")) { %>
	weeklyModuleShowHide(1);
<%}%>

<% if (action.equalsIgnoreCase("new")) { %>
	displayArea("fb0");
	hideArea("fn0");
<%} else {
		if (opReportQueueFrequency.equalsIgnoreCase("D")) { %>
	displayArea("fb1");
	hideArea("fn1");
<%		}
		if (opReportQueueFrequency.equalsIgnoreCase("W")) { %>
	displayArea("fb2");
	hideArea("fn2");
<%		}
		if (opReportQueueFrequency.equalsIgnoreCase("M")) { %>
	displayArea("fb3");
	hideArea("fn3");
<%		}
		if (opReportQueueFrequency.equalsIgnoreCase("Q")) { %>
	displayArea("fb4");
	hideArea("fn4");
<%		}
		if (opReportQueueFrequency.equalsIgnoreCase("A")) { %>
	displayArea("fb5");
	hideArea("fn5");
<%		}
		if (opReportQueueFrequency.equalsIgnoreCase("O")) { %>
	displayArea("fb0");
	hideArea("fn0");
<%		}
	} %>

function showUsers() {
	popupParameters = "formField=ReportQueue_sendTo;browseName=user_multiple;allowBrowse=true;fromPage=reportqueue";
	mailArray = frm.ReportQueue_sendTo.value.split(";");
	mails=mailArray[0];
	for (x=1;x<mailArray.length;x++) mails=mails+","+mailArray[x];
	popupParameters = popupParameters + ";mails=" + mails;
	doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
}

function confirmReportAction(action)
{
	var timeout = 5000;
	var message = '';
	var functionToEval = '';
	var sentenceToExecute = '';
	var aDivs = new Array('previewReport', 'registerReport', 'deleteReport');

	if( ! checkTypeDate() )
	{
		alert('Enter Dates in <%=userDateFormat.toUpperCase()%> format.');
		return false;
	}

	switch(action)
	{
		case 'C':
			message = '<%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "schedule-report", "Schedule ") %>';
			functionToEval = 'registerReportQueue();';
			break;
		case 'U':
			message = '<%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "update-report", "Update ") %>';
			functionToEval = 'updateReportQueue();';
			break;
		case 'D':
			message = '<%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "delete-report", "Delete ") %>';
			functionToEval = 'deleteReportQueue();';
			break;
		default:
			message = '<%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "view-report", "View ") %>';
			functionToEval = 'printPreview();';
			break;
	}

	frm.style.cursor = 'wait';

	for (var i = 0; i < aDivs.length; i++)
	{
		if (document.getElementById(aDivs[i]) != null)
		{
			hideAreaWithBlock(aDivs[i]);
			sentenceToExecute += "displayArea('" + aDivs[i] + "'); ";
		}
	}

	if (verifyAction(message + ' <%= headerEncoder.encodeForJavaScript(browseObject.getTitle()) %>?'))
	{
		eval(functionToEval);

	} else {
		timeout = 250;
	}

	setTimeout(sentenceToExecute + "frm.style.cursor = 'default';", timeout);
}


// End Hide script -->
</script>