<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	InvMachine invMachine = (InvMachine) request.getAttribute("invMachine");
	boolean newInvMachine = false;

	if (invMachine == null)
	{
		invMachine = new InvMachine();
		invMachine.setMachineId("") ;
		invMachine.setDescription("") ;
		invMachine.setWorkCenterId("") ;
		invMachine.setAssetId("");
		invMachine.setNotes("") ;
		invMachine.setDateEntered(d_today);
		invMachine.setDateExpires(d_today);
		invMachine.setOwner(uid);
		invMachine.setStatus("02");
		newInvMachine = true;
	}
	else
	{
	    String newInvMachineString = HiltonUtility.ckNull((String) request.getAttribute("newInvMachine"));
		if (newInvMachineString.equals("Y"))
		{
	      newInvMachine = true;
	    }
	}

	UserProfile owner = UserManager.getInstance().getUser(oid, invMachine.getOwner());
	String duplicateInvMachineErrorMsg = HiltonUtility.ckNull((String)request.getAttribute("duplicateInvMachineErrorMsg"));
%>

<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="duplicateInvMachineFailurePage" value="/admin/systemtables/invmachine.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Machine</div>
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

<%	if (!HiltonUtility.isEmpty(duplicateInvMachineErrorMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
  <td width=100% align=center class=error><%=duplicateInvMachineErrorMsg%></td>
</tr>
</table>
<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=450px align=center>
		<tr>
			<td width=140px align=right nowrap height=18px>Machine Id:&nbsp;</td>
			<td><input type="text" name="InvMachine_machineId" value="<%=invMachine.getMachineId()%>" size=25 maxlength=15 <% if (! newInvMachine) { %> disabled <% } %> onChange="alphaNumericCheck(this);"></td>
			<td nowrap align=right>
<%	if (! newInvMachine) { %>
				<a href="javascript: if (verifyAction('Delete this Machine Id?')) { doSubmit('/browse/browse_sys_tables.jsp', 'InvMachineDelete;BrowseRetrieve'); void(0); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete Machine</a>
<%	} %>
			</td>
		</tr>
		<tr>
			<td width=140px align=right nowrap>Description:&nbsp;</td>
			<td colspan=2><input type="text" name="InvMachine_description" value="<%=invMachine.getDescription()%>" size=50 maxlength=50></td>
		</tr>
		<tr>
			<td width=140px align=right nowrap><a href="javascript: browseLookup('InvMachine_workCenterId', 'invworkcenter'); void(0);" tabindex=-1>Work Center:&nbsp;</a></td>
			<td colspan=2><input type="text" name="InvMachine_workCenterId" value="<%=invMachine.getWorkCenterId()%>" size=15 maxlength=15 onchange="upperCase(this);"></td>
		</tr>
		<tr>
			<td width=140px align=right nowrap>Asset Id:&nbsp;</td>
			<td colspan=2><input type="text" name="InvMachine_assetId" value="<%=invMachine.getAssetId()%>" size=20 maxlength=20 onchange="upperCase(this);"></td>
		</tr>
		<tr>
			<td width=140px align=right nowrap>Notes:&nbsp;</td>
			<td colspan=2>
				<textarea name="InvMachine_notes" cols=60 rows=4 onKeyDown="textCounter(this, 60);" onKeyUp="textCounter(this, 60);">${esapi:encodeForHTML(invMachine.notes)}</textarea>
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
						<select name="InvMachine_status" onchange="setStatus();">
							<option value="01" <% if (invMachine.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (invMachine.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (invMachine.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="InvMachine_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(invMachine.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr>
					<td id="dateExpires" align=right>Date Expires:&nbsp;</td>
					<td id="dateExpires">
						<input type=text name="InvMachine_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(invMachine.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
						<a href="javascript: show_calendar('InvMachine_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('InvMachine_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="InvMachine_owner" tabindex=51 size=30 maxlength=15 value="<%=invMachine.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
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
<%	if (! newInvMachine) { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'InvMachineUpdate;BrowseRetrieve'); void(0);">
<%	} else { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'InvMachineAdd'); void(0);">
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
	frm.browseName.value = "invmachine-admin";
	setNavCookie("/admin/systemtables/invmachine.jsp", "InvMachineRetrieveById", "Machine Id <%=invMachine.getMachineId()%>");

	var status = "<%=invMachine.getStatus()%>";
	setStatus();

	function setFieldFocus()
	{
<%	if (! newInvMachine) { %>
			frm.InvMachine_description.focus();
<%	} else { %>
			frm.InvMachine_machineId.focus();
<%	} %>
	}

	function validateForm() {
	  var alertMessage = '';
      var handler = frm.handler.value;

      if (handler.indexOf("InvMachineAdd") >= 0) {
	      if (isEmpty(frm.InvMachine_machineId.value))
	        	alertMessage += 'Machine Id is a required entry.\n';

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
		status = frm.InvMachine_status[frm.InvMachine_status.selectedIndex].value;

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