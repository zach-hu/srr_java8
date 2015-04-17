<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_date_format = propertiesManager.getProperty("MISC", "DateFormat", "MM-dd-yyyy");
	List userLogList = (List)request.getAttribute("userLogList");
	String userId = (String)request.getAttribute("UserLog_userId");
	String organizationId = (String)request.getAttribute("organizationId");
	UserProfile	userProfile = UserManager.getInstance().getUser(organizationId, userId);
%>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=userProfile.getDisplayName()%>&nbsp;(<%=userProfile.getUserId()%>)</div>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table id=itemTable border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "user-mailId")%> width=25% class=browseHdr align=center nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-mailId", "Mail ID")%></td>
							<td <%=HtmlWriter.isVisible(oid, "user-dateLog")%> width=10% class=browseHdr align=center nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-dateLog", "Date Log")%></td>
							<td <%=HtmlWriter.isVisible(oid, "user-timeLog")%> width=10% class=browseHdr align=center nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-timeLog", "Time Log")%></td>
							<td <%=HtmlWriter.isVisible(oid, "user-ipLog")%> width=15% class=browseHdr align=center nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-ipLog", "IP Address")%></td>
							<td <%=HtmlWriter.isVisible(oid, "user-status")%> width=40% class=browseHdr align=center nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-status", "Status")%></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
<%
	if (userLogList != null && userLogList.size() > 0)
	{
		for (int i=0; i<userLogList.size(); i++)
		{
			UserLog userLog = (UserLog)userLogList.get(i);

			String s_status = DocumentStatus.toString(userLog.getStatus(), oid);
			if (userLog.getStatus().compareTo(DocumentStatus.USERLOG_INVALID_PASSWORD) == 0)
				s_status += " using " + userLog.getPassCode();
%>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "user-mailId")%> width=25% class=browseRow align=left nowrap><%=userLog.getMailId()%></td>
							<td <%=HtmlWriter.isVisible(oid, "user-dateLog")%> width=10% class=browseRow align=left nowrap><%=HiltonUtility.getFormattedDate(userLog.getDateLog(), oid, s_date_format)%></td>
							<td <%=HtmlWriter.isVisible(oid, "user-timeLog")%> width=10% class=browseRow align=left nowrap><%=userLog.getTimeLog()%></td>
							<td <%=HtmlWriter.isVisible(oid, "user-ipLog")%> width=15% class=browseRow align=left nowrap><%=userLog.getIpLog()%></td>
							<td <%=HtmlWriter.isVisible(oid, "user-status")%> width=40% class=browseRow align=left nowrap><%=s_status%></td>
						</tr>
<%
		}
	}
	else
	{
%>
						<tr>
							<td align=center colspan=7>&nbsp;</td>
						</tr>
						<tr>
							<td align=center colspan=7><b>There are currently 0 User Logs records for this user.</b></td>
						</tr>
<%
	}
%>
						</table>
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
	<td align=center><a href="javascript: browseFilter('admin-userlog'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("/admin/user/user_log_audittrail.jsp", "DoNothing", "User Log");

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>
