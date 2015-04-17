<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	StdComment stdComment = (StdComment) request.getAttribute("stdComment");
	DocText docText = (DocText) request.getAttribute("docText");
	boolean newComment = false;

	if (stdComment == null)
	{
		stdComment = new StdComment();
		docText = new DocText();
		stdComment.setDateEntered(d_today);
		stdComment.setDateExpires(d_today);
		stdComment.setOwner(uid);
		stdComment.setStatus("02");
		newComment = true;
	}

	UserProfile owner = UserManager.getInstance().getUser(oid, stdComment.getOwner());
	String duplicateRecordErrorMsg = HiltonUtility.ckNull((String)request.getAttribute("duplicateRecordErrorMsg"));
%>

<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="duplicateRecordFailurePage" value="/admin/systemtables/stdcomment.jsp"/>

<br>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Standard Comment</div>
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

<%	if (!HiltonUtility.isEmpty(duplicateRecordErrorMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
  <td width=100% align=center class=error><%=duplicateRecordErrorMsg%></td>
</tr>
</table>
<br>
<%	} %>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=450px align=center>
		<tr>
			<td width=100px align=right nowrap height=18px>Comment Id:&nbsp;</td>
			<td><input type="text" name="StdComment_commentId" value="<%=stdComment.getCommentId()%>" size=25 maxlength=15 <%	if (! newComment) { %> disabled <% } %> onChange="alphaNumericCheck(this);"></td>
			<td nowrap align=right>
<%	if (! newComment) { %>
				<a href="javascript: if (verifyAction('Delete this comment?')) { doSubmit('browse/browse_sys_tables.jsp', 'StdCommentDelete;BrowseRetrieve'); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete Comment</a>
<%	} %>
			</td>
		</tr>
		<tr>
			<td width=100px align=right nowrap>Title:&nbsp;</td>
			<td colspan=2><input type="text" name="StdComment_commentTitle" value="<%=stdComment.getCommentTitle()%>" size=70 maxlength=60 onChange="upperCase(this);"></td>
		</tr>
		<tr>
			<td width=100px valign=top align=right nowrap>Comment Text:&nbsp;</td>
			<td colspan=2>
				<textarea name="DocText_stdText" cols=69 rows=5>${esapi:encodeForHTML(docText.stdText)}</textarea>
				<tsa:hidden name="DocText_icText" value="<%=stdComment.getIcText().toString()%>"/>
				<tsa:hidden name="StdComment_icText" value="<%=stdComment.getIcText().toString()%>"/>
			</td>
		</tr>
		</table>
		<br>
		<table border=0 cellpadding=1 cellspacing=0 width=250px align=center>
		<tr>
			<td width=75px align=right nowrap>
				<input type=checkbox name="c_checkbox" value="Y" <% if (stdComment.getCommentPublic().equals("Y")) { %> CHECKED <% } %> onClick="setCheckbox(frm.StdComment_commentPublic, 0);">
				<tsa:hidden name="StdComment_commentPublic" value="<%=stdComment.getCommentPublic()%>"/>
			</td>
			<td>Public</td>
			<td width=75px align=right nowrap>
				<input type=checkbox name="c_checkbox" value="Y" <% if (stdComment.getCommentPrint().equals("Y")) { %> CHECKED <% } %> onClick="setCheckbox(frm.StdComment_commentPrint, 1);">
				<tsa:hidden name="StdComment_commentPrint" value="<%=stdComment.getCommentPrint()%>"/>
			</td>
			<td>Print</td>
			<td width=75px align=right nowrap>
				<input type=checkbox name="c_checkbox" value="Y" <% if (stdComment.getCommentBold().equals("Y")) { %> CHECKED <% } %> onClick="setCheckbox(frm.StdComment_commentBold, 2);">
				<tsa:hidden name="StdComment_commentBold" value="<%=stdComment.getCommentBold()%>"/>
			</td>
			<td>Bold</td>
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
						<select name="StdComment_status" onchange="setStatus();">
							<option value="01" <% if (stdComment.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (stdComment.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (stdComment.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="StdComment_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(stdComment.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr>
					<td id="dateExpires" align=right>Date Expires:&nbsp;</td>
					<td id="dateExpires">
						<input type=text name="StdComment_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(stdComment.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
						<a href="javascript: show_calendar('StdComment_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('StdComment_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="StdComment_owner" tabindex=51 size=30 maxlength=15 value="<%=stdComment.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
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

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=33% align=center>
<%	if (! newComment) { %>
		<div id="pxbutton"><a href="javascript:  doSubmit('/browse/browse_sys_tables.jsp', 'StdCommentUpdate;BrowseRetrieve'); void(0);">Save</a></div>
<%	} else { %>
		<div id="pxbutton"><a href="javascript:  doSubmit('/browse/browse_sys_tables.jsp', 'StdCommentAdd'); void(0);">Save</a></div>
<%	} %>
	</td>
	<td width=33% align=center><div id="pxbutton"><a href="javascript: browse('stdcomment-admin'); void(0);">Cancel</a></div></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.browseName.value = "stdcomment-admin";
	setNavCookie("/admin/systemtables/stdcomment.jsp", "StdCommentRetrieveById", "Comment <%=stdComment.getCommentId()%>");

	var status = "<%=stdComment.getStatus()%>";
	setStatus();

	function setFieldFocus()
	{
<%	if (! newComment) { %>
			frm.StdComment_commentTitle.focus();
<%	} else { %>
			frm.StdComment_commentId.focus();
<%	} %>
	}

	function browse(x)
	{
		frm.browseName.value = x;

		doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
	}

	function setStatus()
	{
		status = frm.StdComment_status[frm.StdComment_status.selectedIndex].value;
		if ((status == "01") || (status == "03"))
		{
			displayArea("dateExpires");
		}
		else
		{
			hideArea("dateExpires");
		}
	}

	function setCheckbox(fld, x)
	{
		fld.value = 'N';
		if ( frm.c_checkbox[x].checked )
		{
			fld.value = 'Y';
		}
		return true;
	}

	function validateForm()
	{
		if (frm.handler.value.indexOf("StdCommentAdd") >= 0)
		{
			if (frm.StdComment_commentId.value.trim().length <= 0)
			{
				alert("A Comment ID is required!");
				return false;
			}
		}
		return true;
	}

// End Hide script -->
</SCRIPT>