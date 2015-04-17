<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	InvMethod invMethod = (InvMethod) request.getAttribute("invMethod");
	boolean newInvMethod = false;

	if (invMethod == null)
	{
		invMethod = new InvMethod();
		invMethod.setMethodId("") ;
		invMethod.setDescription("") ;
		invMethod.setNotes("") ;
		invMethod.setDateEntered(d_today);
		invMethod.setDateExpires(d_today);
		invMethod.setOwner(uid);
		invMethod.setStatus("02");
		newInvMethod = true;
	}
	else
	{
	    String newInvMethodString = HiltonUtility.ckNull((String) request.getAttribute("newInvMethod"));
		if (newInvMethodString.equals("Y"))
		{
	      newInvMethod = true;
	    }
	}

	UserProfile owner = UserManager.getInstance().getUser(oid, invMethod.getOwner());
	String duplicateInvMethodErrorMsg = HiltonUtility.ckNull((String)request.getAttribute("duplicateInvMethodErrorMsg"));
%>

<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="duplicateInvMethodFailurePage" value="/admin/systemtables/invmethod.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Method</div>
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

<%	if (!HiltonUtility.isEmpty(duplicateInvMethodErrorMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
  <td width=100% align=center class=error><%=duplicateInvMethodErrorMsg%></td>
</tr>
</table>
<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=450px align=center>
		<tr>
			<td width=140px align=right nowrap height=18px>Method Id:&nbsp;</td>
			<td><input type="text" name="InvMethod_methodId" value="<%=invMethod.getMethodId()%>" size=25 maxlength=15 <% if (! newInvMethod) { %> disabled <% } %> onChange="alphaNumericCheck(this);"></td>
			<td nowrap align=right>
<%	if (! newInvMethod) { %>
				<a href="javascript: if (verifyAction('Delete this Method Id?')) { doSubmit('/browse/browse_sys_tables.jsp', 'InvMethodDelete;BrowseRetrieve'); void(0); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete Method</a>
<%	} %>
			</td>
		</tr>
		<tr>
			<td width=140px align=right nowrap>Description:&nbsp;</td>
			<td colspan=2><input type="text" name="InvMethod_description" value="<%=invMethod.getDescription()%>" size=50 maxlength=50></td>
		</tr>
		<tr>
			<td width=140px align=right nowrap>Notes:&nbsp;</td>
			<td colspan=2>
				<textarea name="InvMethod_notes" cols=60 rows=4 onKeyDown="textCounter(this, 60);" onKeyUp="textCounter(this, 60);">${esapi:encodeForHTML(invMethod.notes)}</textarea>
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
						<select name="InvMethod_status" onchange="setStatus();">
							<option value="01" <% if (invMethod.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (invMethod.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (invMethod.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="InvMethod_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(invMethod.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr>
					<td id="dateExpires" align=right>Date Expires:&nbsp;</td>
					<td id="dateExpires">
						<input type=text name="InvMethod_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(invMethod.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
						<a href="javascript: show_calendar('InvMethod_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('InvMethod_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="InvMethod_owner" tabindex=51 size=30 maxlength=15 value="<%=invMethod.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
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
<%	if (! newInvMethod) { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'InvMethodUpdate;BrowseRetrieve'); void(0);">
<%	} else { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'InvMethodAdd'); void(0);">
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
	frm.browseName.value = "invmethod-admin";
	setNavCookie("/admin/systemtables/invmethod.jsp", "InvMethodRetrieveById", "Method Id <%=invMethod.getMethodId()%>");

	var status = "<%=invMethod.getStatus()%>";
	setStatus();

	function setFieldFocus()
	{
<%	if (! newInvMethod) { %>
			frm.InvMethod_description.focus();
<%	} else { %>
			frm.InvMethod_methodId.focus();
<%	} %>
	}

	function validateForm() {
	  var alertMessage = '';
      var handler = frm.handler.value;

      if (handler.indexOf("InvMethodAdd") >= 0) {
	      if (isEmpty(frm.InvMethod_methodId.value))
	        	alertMessage += 'Method Id is a required entry.\n';

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
		status = frm.InvMethod_status[frm.InvMethod_status.selectedIndex].value;

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