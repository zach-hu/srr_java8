<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String errorMsg = (String) request.getAttribute("errorMsg");
	String returnPage = (String) request.getAttribute("returnPage");
	if (HiltonUtility.isEmpty(errorMsg)) {
		errorMsg = "<br>";
	}
	if (HiltonUtility.isEmpty(returnPage)) {
		returnPage = "menu/main_menu.jsp";
	}
%>

<tsa:hidden name="resetFailurePage" value="/user/security_profile.jsp"/>
<tsa:hidden name="reviewProfilePage" value="user/user_profile.jsp"/>
<tsa:hidden name="returnPage" value="<%=headerEncoder.encodeForHTMLAttribute(returnPage)%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Password Security</td>
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

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr><td align=center>This security question will be used to verify your identity in case if you forget or lose your password.</td></tr>
<%	if (user.getSecurityQuestion().trim().length() > 0) {%>
<tr><td align=center>To change your security question, you must first correctly answer your current security question.</td></tr>
<tr><td><br></td></tr>
<tr><td align=center class="error"><%=errorMsg%></td></tr>
<%	} else {%>
<tr><td align=center>Select your security question below and enter your answer.</td></tr>
<%	}%>
	</td>
</tr>
</table>

<br>
<%@ include file="/user/security_profile_form.jsp"%>

<br>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=headerEncoder.encodeForJavaScript(returnPage)%>', 'UserChangeSecurityProfile'); void(0);;">Save</a></div></td>
	<td width="50%" align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=headerEncoder.encodeForJavaScript(returnPage)%>', 'DoNothing'); void(0);" border=0 tabindex="4">Cancel</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function validateForm() {
		return validateUserSecurityForm();
	}

//-->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>