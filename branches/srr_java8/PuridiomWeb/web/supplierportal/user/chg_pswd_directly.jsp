<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp"%>
<%
	String icLink = (String) request.getAttribute("ResetPasswordLink_icLink");

	String errorMsg = HiltonUtility.ckNull((String) request.getAttribute("errorMsg"));
	String warningMsg = HiltonUtility.ckNull((String) request.getAttribute("warningMsg"));
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
	} else if (HiltonUtility.ckNull((String) request.getAttribute("warningOnly")).equalsIgnoreCase("true")) {
		warningOnly = true;
	}

	String resetPasswordLinkActive = HiltonUtility.ckNull((String) request.getAttribute("resetPasswordLinkActive"));

	errorMsg = errorMsg + "<br>The password should be more than " + PropertiesManager.getInstance(oid).getProperty("MISC","PASSLENGTH","1") + " characters long.";

	String noteMsg = "NOTE: Password should be at least " + PropertiesManager.getInstance(oid).getProperty("MISC","PASSLENGTH","6") +" characters in length and must include: ";
	String alphaCharacter = PropertiesManager.getInstance(oid).getProperty("MISC","MINPASSLOWER","1") + " lower case alpha character";
	String numericCharacter = PropertiesManager.getInstance(oid).getProperty("MISC","MINDIGIT","1") + " numeric character";
	String specialCharacter = PropertiesManager.getInstance(oid).getProperty("MISC","MINNONALPHA","1") + " special character !@#$%^&*()_+";
%>

<tsa:hidden name="returnPage" value="<%=returnPage%>"/>
<tsa:hidden name="resetFailurePage" value="user/chg_pswd_directly.jsp"/>
<tsa:hidden name="reviewProfilePage" value="user/user_profile.jsp"/>
<tsa:hidden name="warningOnly" value="<%=warningOnly%>"/>
<tsa:hidden name="ResetPasswordLink_icLink" value="<%=icLink.toString()%>"/>
<tsa:hidden name="ResetPasswordLink_used" value="Y"/>
<tsa:hidden name="UserProfile_lockLogin" value="N"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
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
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr>
			<td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<%	if (resetPasswordLinkActive.equalsIgnoreCase("N")) { %>

<table border=0	cellspacing=0 cellpadding=2 width=680px>
<tr>
	<td align=center class="error">This link has already been used or it has expired.</td>
</tr>
</table>

<% } else { %>

<table border=0	cellspacing=0 cellpadding=2 width=680px>
<% if(oid.equalsIgnoreCase("bsc04p")){ %>
<tr>
	<td colspan=2 align=center class="error">
		<table border=0	cellspacing=0 cellpadding=2>
		<tr>
		<td width=28%></td>
		<td colspan=2 width=60% align=left class="error"><%=noteMsg%></td>
		<td width=12%></td>
		</tr>
		<tr>
		<td colspan=4></td>
		</tr>
		<tr>
		<td colspan=4></td>
		</tr>
		<tr>
		<td width=28%></td>
		<td width=4%></td>
		<td width=56% align="left" class="error">&nbsp;&nbsp;&nbsp;*&nbsp;<%=alphaCharacter%></td>
		<td width=12%></td>
		</tr>
		<tr>
		<td width=28%></td>
		<td width=4%></td>
		<td width=56% align=left class="error">&nbsp;&nbsp;&nbsp;*&nbsp;<%=numericCharacter%></td>
		<td width=12%></td>
		</tr>
		<tr>
		<td width=28%></td>
		<td width=4%></td>
		<td width=56% align=left class="error">&nbsp;&nbsp;&nbsp;*&nbsp;<%=specialCharacter%></td>
		<td width=12%></td>
		</tr>
		</table>
	</td>
</tr>
<% }else{ %>
<tr>
	<td colspan=2 align=center class="error"><%=errorMsg%></td>
</tr>
<%} %>
<tr>	<td colspan=2><br></td></tr>
<tr>
	<td width="50%" align=right><b><b></td>
	<td width="50%"><tsa:hidden name="password" value=""/></td>
</tr>
<tr><td colspan=2><br></td></tr>
<tr>
	<td align=right><b>Enter New Password:</b></td>
	<td><input type=password name="newPassword" autocomplete="off" value="" tabindex="2" size="15" maxlength="12" taborder="2"></td>
</tr>
<tr><td colspan=2><br></td></tr>
<tr>
	<td align=right><b>Confirm New Password:</b></td>
	<td><input type=password name="confirmPassword" autocomplete="off" value="" tabindex="3" size="15" maxlength="12" taborder="2"></td>
</tr>
<tr><td colspan=2><br><br></td></tr>
<tr>
	<td align=center colspan=2>
		<table border=0 width="600">
		<tr>
			<td width="50%" align=center><a href="javascript: doSubmit('<%=returnPage%>', 'RegisterUserChangePasswordDirectly'); void(0);" tabindex="3"><img class=button src="<%=contextPath%>/supplierportal/images/button_save.gif" border=0></a></td>
<%	if (user.isAuthenticated() && !returnPage.equals("menu/main_menu.jsp")) {%>
			<td width="50%" align=center><a href="javascript: doSubmit('<%=returnPage%>', 'DoNothing'); void(0);" border=0 tabindex="4"><img class=button src="<%=contextPath%>/supplierportal/images/button_cancel.gif" border=0></a></td>
<%	}%>
		</tr>
		</table>
	</td>
</tr>
<tr><td colspan=2><br><tsa:hidden name="temp" value=""/></td></tr>
</table>

<% } %>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	helpTopic = "<%=contextPath%>/help/intro/login.htm#chg_pswd";

	function validateForm() {
		if (frm.handler.value.indexOf("RegisterUserChangePasswordDirectly") >= 0) {
			var alertMessage = "";
			var w;

//			w = frm.password.value;
//			if ( isEmpty( w ) )
//				alertMessage += 'Current Password is required.\n';
			w = frm.newPassword.value;
			if ( isEmpty( w ) )
				alertMessage += 'New Password is required.\n';
			w = frm.confirmPassword.value;
			if ( isEmpty( w ) ) {
				alertMessage += 'Please enter the Confirmation for the User Password. Re-type your password.\n';
			} else if ( w != frm.newPassword.value ) {
				alertMessage += 'Password Confirmation does not match New User Password.\n';
			}

			if ( alertMessage.length > 0 ) {
			    	alert ( 'Please fix the following problems:\n\n'+alertMessage );
			    	return false;
			}
		}
		return true;
	}

//-->
</SCRIPT>
