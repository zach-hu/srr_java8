<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	InvTask invTask = (InvTask) request.getAttribute("invTask");
	boolean newInvTask = false;

	if (invTask == null)
	{
		invTask = new InvTask();
		invTask.setTaskId("") ;
		invTask.setDescription("") ;
		invTask.setTaskType("") ;
		invTask.setNotes("") ;
		invTask.setDateEntered(d_today);
		invTask.setDateExpires(d_today);
		invTask.setOwner(uid);
		invTask.setStatus("02");
		newInvTask = true;
	}
	else
	{
	    String newInvTaskString = HiltonUtility.ckNull((String) request.getAttribute("newInvTask"));
		if (newInvTaskString.equals("Y"))
		{
	      newInvTask = true;
	    }
	}

	UserProfile owner = UserManager.getInstance().getUser(oid, invTask.getOwner());
	String duplicateInvTaskErrorMsg = HiltonUtility.ckNull((String)request.getAttribute("duplicateInvTaskErrorMsg"));
%>

<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="duplicateInvTaskFailurePage" value="/admin/systemtables/invtask.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Routing Task</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
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

<%	if (!HiltonUtility.isEmpty(duplicateInvTaskErrorMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
  <td width=100% align=center class=error><%=duplicateInvTaskErrorMsg%></td>
</tr>
</table>
<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=450px align=center>
		<tr>
			<td width=140px align=right nowrap height=18px>Task Id:&nbsp;</td>
			<td><input type="text" name="InvTask_taskId" value="<%=invTask.getTaskId()%>" size=25 maxlength=15 <% if (! newInvTask) { %> disabled <% } %> onChange="alphaNumericCheck(this);"></td>
			<td nowrap align=right>
<%	if (! newInvTask) { %>
				<a href="javascript: if (verifyAction('Delete this Task Id?')) { doSubmit('/browse/browse_sys_tables.jsp', 'InvTaskDelete;BrowseRetrieve'); void(0); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete Task</a>
<%	} %>
			</td>
		</tr>
		<tr>
			<td width=140px align=right nowrap>Description:&nbsp;</td>
			<td colspan=2><input type="text" name="InvTask_description" value="<%=invTask.getDescription()%>" size=50 maxlength=50></td>
		</tr>
		<tr>
			<td width=140px align=right nowrap>Task Type:&nbsp;</td>
			<td colspan=2><input type="text" name="InvTask_taskType" value="<%=invTask.getTaskType()%>" size=15 maxlength=15 onchange="upperCase(this);"></td>
		</tr>
		<tr>
			<td width=140px align=right nowrap>Notes:&nbsp;</td>
			<td colspan=2>
				<textarea name="InvTask_notes" cols=60 rows=4 onKeyDown="textCounter(this, 60);" onKeyUp="textCounter(this, 60);">${esapi:encodeForHTML(invTask.notes)}</textarea>
			</td>
		</tr>
		</table>

		<br>
		<hr width=475px align=center class=browseHR>
		<br>

		<table border=0 cellpadding=1 cellspacing=0 width=475px height=75px align=center>
		<tr>
			<td width=100% valign=top>
				<table border=0>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:&nbsp;</td>
					<td>
						<select name="InvTask_status" onchange="setStatus();">
							<option value="01" <% if (invTask.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (invTask.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (invTask.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="InvTask_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(invTask.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr>
					<td id="dateExpires" align=right>Date Expires:&nbsp;</td>
					<td id="dateExpires">
						<input type=text name="InvTask_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(invTask.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
						<a href="javascript: show_calendar('InvTask_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('InvTask_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="InvTask_owner" tabindex=51 size=30 maxlength=15 value="<%=invTask.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
					</td>
				</tr>
				<tr>
					<td nowrap align=right>Owner Name:&nbsp;</td>
					<td>
						<input type=text name="as_ownerName" size=30 value="<%=owner.getDisplayName()%>" disabled>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center>
<%	if (! newInvTask) { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'InvTaskUpdate;BrowseRetrieve'); void(0);">
<%	} else { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'InvTaskAdd'); void(0);">
<%	} %>
		<img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a>
	</td>
	<td width=50% align=center><a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 alt="Cancel"></a></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.browseName.value = "invtask-admin";
	setNavCookie("/admin/systemtables/invtask.jsp", "InvTaskRetrieveById", "Task Id <%=invTask.getTaskId()%>");

	var status = "<%=invTask.getStatus()%>";
	setStatus();

	function setFieldFocus()
	{
<%	if (! newInvTask) { %>
			frm.InvTask_description.focus();
<%	} else { %>
			frm.InvTask_taskId.focus();
<%	} %>
	}

	function validateForm() {
	  var alertMessage = '';
      var handler = frm.handler.value;

      if (handler.indexOf("InvTaskAdd") >= 0) {
	      if (isEmpty(frm.InvTask_taskId.value))
	        	alertMessage += 'Task Id is a required entry.\n';

	      if (alertMessage.length > 0) {
	            alert ('Please fix the following problems:\n\n' + alertMessage);
	            return false;
		  }
	  }

	  return true;
    }
	function setStatus()
	{
		//take notice of var status and how setStatusFields is called at beginning;
		status = frm.InvTask_status[frm.InvTask_status.selectedIndex].value;

		if ((status == "01") || (status == "03"))
		{
			displayArea("dateExpires");
		}
		else
		{
			hideArea("dateExpires");
		}
	}

// End Hide script -->
</SCRIPT>