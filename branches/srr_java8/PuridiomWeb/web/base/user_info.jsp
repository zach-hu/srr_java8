<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.usermanager.*" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>
<%@ page import="com.tsa.puridiom.entity.UserProfile" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	String	displayUid = request.getParameter("uid");
	UserProfile displayUser = UserManager.getInstance().getUser(oid, displayUid);
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	boolean allowApprovalEscalation = propertiesManager.getProperty("APPROVALS", "AllowApprovalEscalation", "N").equals("Y");
%>

<table width=550px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>User Info</td>
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

<br><br>

<table border=0 cellspacing=0 cellpadding=0 width=550px>
<tr>
	<td>
		<table border=0 cellspacing=0 cellpadding=2 width=100%>
		<tr>
			<td width=200px align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-userId", "User ID", false)%>:</td>
			<td><%=displayUser.getUserId()%></td>
		</tr>
		<tr>
			<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-name", "Name", false)%>:</td>
			<td><%=displayUser.getDisplayName()%></td>
		</tr>
		<tr>
			<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "mailId", "Email Address", false)%>:</td>
			<td><%=displayUser.getMailId()%></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "user-title")%>>
			<td align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-title", "Title", false)%>:</td>
			<td><%=displayUser.getTitle()%></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "user-department")%>>
			<td align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-department", "Department", false)%>:</td>
			<td><%=displayUser.getDepartment()%></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "user-telephoneNumber")%>>
			<td align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-telephoneNumber", "Phone", false)%>:</td>
			<td><%=displayUser.getPhoneNumber()%></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "user-faxNumber")%>>
			<td align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-faxNumber", "Fax", false)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "user-faxNumber")%>><%=displayUser.getFaxNumber()%></td>
		</tr>
<%	if (displayUser.isAnApprover()) { %>
		<tr <%=HtmlWriter.isVisible(oid, "user-callForward")%>>
			<td align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routing-callForward", "Call Forward")%>:</td>
			<td><%=UserManager.getInstance().getUser(oid, displayUser.getCallForward()).getDisplayName()%></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "callForward-endDate")%>>
			<td align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routing-callForwardEnd", "Call Forward End")%>:</td>
			<td nowrap>
<%		if (!HiltonUtility.isEmpty(displayUser.getCallForward()) && displayUser.getForwardOffDate() != null && displayUser.getForwardOffDate().after(d_today)) {%>
				<%=HiltonUtility.getFormattedDate(displayUser.getForwardOffDate(), oid, userDateFormat)%>
<%		} else { %>
				&nbsp;
<%		} %>
			</td>
		</tr>
<%	if (allowApprovalEscalation) {%>
				<tr <%=HtmlWriter.isVisible(oid, "user-backupApprover")%>>
					<td align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-backupApprover", "Backup Approver", false)%>:</td>
					<td><%=UserManager.getInstance().getUser(oid, displayUser.getBackupApprover()).getDisplayName()%></td>
				</tr>
<%	} %>
<%	} %>
		</table>

		<br><br>

		<table border=0 width=100%>
		<tr><td align=center><div id="pxbutton"><a href="javascript: window.top.hidePopWin(); void(0);" tabindex=100>Close</a></div></td></tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer_popup.jsp" %>

<script language="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

// end hiding contents -->
</script>