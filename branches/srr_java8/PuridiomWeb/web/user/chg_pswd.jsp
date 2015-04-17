<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String errorMsg = HiltonUtility.ckNull((String) request.getAttribute("errorMsg"));
	String warningMsg = HiltonUtility.ckNull((String) request.getAttribute("warningMsg"));
	String confirmationMsg = HiltonUtility.ckNull((String) request.getAttribute("confirmationMsg"));
	String confirmationInfo = HiltonUtility.ckNull((String) request.getAttribute("confirmationInfo"));
	String returnPage = (String) request.getAttribute("returnPage");
	boolean warningOnly = false;

	if (HiltonUtility.isEmpty(returnPage)) {
		returnPage = "menu/main_menu.jsp";
	}
	if (!HiltonUtility.isEmpty(warningMsg)) {
		warningOnly = true;
		if (!HiltonUtility.isEmpty(errorMsg)) {
			errorMsg = errorMsg + "<br>" +  warningMsg;
		} else {
			errorMsg = warningMsg;
		}
	}
	else if (HiltonUtility.ckNull((String) request.getAttribute("warningOnly")).equalsIgnoreCase("true")) {
		warningOnly = true;
	}
%>

<tsa:hidden name="returnPage" value="<%=headerEncoder.encodeForHTMLAttribute(returnPage)%>"/>
<tsa:hidden name="resetFailurePage" value="user/chg_pswd.jsp"/>
<tsa:hidden name="reviewProfilePage" value="user/user_profile.jsp"/>
<tsa:hidden name="warningOnly" value="<%=warningOnly%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Change User Password</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr>
			<td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=2 width=<%=formEntryWidth%>>
<tr>
	<td class=error align=center>
		<table border=0 cellpadding=1 cellspacing=0 width=90%>
		<tr><td><%=errorMsg%></td></tr>
<%	if (!HiltonUtility.isEmpty(confirmationInfo)) {%>
		<tr><td class=error><%=confirmationMsg%></td></tr>
<%	}%>
<%	if (!HiltonUtility.isEmpty(confirmationInfo)) {%>
		<tr><td><br><%=confirmationInfo%><br></td></tr>
<%	}%>
		</table>
	</td>
</tr>
</table>
<br>
<br>
<%@ include file="/user/chg_pswd_form.jsp" %>
<br>
<br>
<table border=0 cellspacing=0 cellpadding=2 width=<%=formEntryWidth%>>
<tr>
	<td align=center colspan=2>
		<table border=0 width="<%=formEntryWidth%>">
		<tr>
			<td width="50%" align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=headerEncoder.encodeForJavaScript(returnPage)%>', 'AuditTrailSetup;UserChangePassword'); void(0);" tabindex="3">Save</a></div></td>
<%	if (authenticated && (!returnPage.equals("menu/main_menu.jsp") || warningOnly)) {%>
			<td width="50%" align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=headerEncoder.encodeForJavaScript(returnPage)%>', 'DoNothing'); void(0);" border=0 tabindex="4">Cancel</a></div></td>
<%	}%>
		</tr>
		</table>
	</td>
</tr>
<tr><td colspan=2><br><tsa:hidden name="temp" value=""/></td></tr>
</table>
<%@ include file="/system/footer.jsp" %>