<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	UserProfile userProfile = (UserProfile) request.getAttribute("userProfile");
	if (userProfile == null) {
		userProfile = new UserProfile();
	}
	String	errorMsg = (String) request.getAttribute("errorMsg");
	String	finalLoginAttempt = (String) request.getAttribute("finalLoginAttempt");
	if (!HiltonUtility.ckNull(finalLoginAttempt).equals("true")) {
		errorMsg = "";
	}
%>

<tsa:hidden name="resetFailurePage" value="user/pswd_help_confirmation.jsp"/>
<tsa:hidden name="UserProfile_mailId" value="<%=userProfile.getMailId()%>"/>
<tsa:hidden name="userVerificationSet" value="true"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Password Help - Step 2</td>
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

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<%	if (!HiltonUtility.isEmpty(errorMsg)) {%>
<tr><td colspan=3 class=error align=center>&nbsp;<%=errorMsg%></td></tr>
<tr><td><br></td></tr>
<%	}%>
<tr><td align=center>You must correctly answer your security question below to verify your identity.<br>  Your password will then be sent to your email address.</td></tr>
<tr><td><br></td></tr>
<tr>
	<td width=100% valign=top align=center>
		<table border=0 border=0>
		<tr>
			<td align=right><B>Email Address (Login ID):<B></td>
			<td><%=userProfile.getMailId()%></td>
		</tr>
		<tr>
			<td align=right><B>User Name:<B></td>
			<td><%=userProfile.getDisplayName()%></td>
		</tr>
		</table>
	</td>
</tr>
<tr><td><br><br></td></tr>
<tr>
	<td width=100% valign=top align=center>
		<table border=0 border=0 height=50px>
		<tr>
			<td><b><%=userProfile.getSecurityQuestion()%></b></td>
			<td><input type=text name="securityAnswer" value="" size=25 maxLength=40></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: getForgottenPassword(); void(0);">Submit</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('index.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function validateForm() {
		var handler = frm.handler.value;
		if (handler.indexOf("UserProfilePasswordHelp") >= 0) {
			if (isEmpty(frm.securityAnswer.value)) {
				alert("You must answer your security question.");
				return false;
			}
		}
		return true;
	}

	function getForgottenPassword() {
		doSubmit('user/pswd_help_confirmation.jsp', 'UserProfilePasswordHelp');
	}

	function thisLoad() {
		return;
	}
//-->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>