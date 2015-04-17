<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String	errorMsg = HiltonUtility.ckNull((String) request.getAttribute("errorMsg"));
	String	warningMsg = HiltonUtility.ckNull((String) request.getAttribute("warningMsg"));
	String	instructions = HiltonUtility.ckNull((String) request.getAttribute("instructions"));
	String	confirmationInfo = (String) request.getAttribute("confirmationInfo");
	String	confirmationInfo2 = (String) request.getAttribute("confirmationInfo2");
	String	confirmationInfo3 = (String) request.getAttribute("confirmationInfo3");
	String	confirmationInfo4 = (String) request.getAttribute("confirmationInfo4");
	String	puridiomSessionId = HiltonUtility.ckNull((String) request.getAttribute("puridiomSessionId"));
	Date dExpires = new Date(d_today.getYear(), d_today.getMonth(), d_today.getDate()+30);
	String	expiresDate = HiltonUtility.getFormattedDate(dExpires, oid, "MMMM d, yyyy");

	if (HiltonUtility.isEmpty(errorMsg)) {
		errorMsg = "Congratulations!  You have successfully activated your account for your FREE 30 day trial of Puridiom Xpress!<br>";
		if (HiltonUtility.isEmpty(confirmationInfo)) {
			confirmationInfo = "Your 30 day trial begins today, " + HiltonUtility.getFormattedDate(d_today, oid, "MMMM d, yyyy") + " and will expire on " + expiresDate + ".";
			confirmationInfo2 = "If you wish to continue using Puridiom Xpress after the 30 day trial period, do nothing!" +
				"  You will be automatically billed with the information you provided upon registration.  Billing information can be updated in your User Profile." +
				"  You can cancel your trial service at any time by contacting support@puridiom.com." +
				"  For more information about annual subscriptions, <a href='javascript: subscriptionInfo(); void(0);'>click here.</a>";
		}
	}
	if (HiltonUtility.isEmpty(instructions)) {
		instructions = "Please change your password now to begin the setup process.";
	}
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
<tsa:hidden name="resetFailurePage" value="user/xpress_user_activated.jsp"/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Account Setup - Step 1 of 3</div>
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
	<td class=error align=center colspan=3>
		<table border=0 cellpadding=1 cellspacing=0 width=90%>
		<tr><td class=error><%=errorMsg%></td></tr>
<%	if (!HiltonUtility.isEmpty(confirmationInfo)) {%>
		<tr><td><br><%=confirmationInfo%><br></td></tr>
<%	}
	if (!HiltonUtility.isEmpty(confirmationInfo2)) {%>
		<tr><td><br><%=confirmationInfo2%><br></td></tr>
<%	}
	if (!HiltonUtility.isEmpty(confirmationInfo3)) {%>
		<tr><td><br><%=confirmationInfo3%><br></td></tr>
<%	}
	if (!HiltonUtility.isEmpty(confirmationInfo4)) {%>
		<tr><td><br><%=confirmationInfo4%><br></td></tr>
<%	}
	if (!HiltonUtility.isEmpty(instructions)) {%>
		<tr><td><br><%=instructions%><br></td></tr>
<%	}%>
		</table>
		<br>
	</td>
</tr>
<tr>
	<td width="50%" align=right><b>Enter Current Password:<b></td>
	<td width="50%"><input type=password name="password" autocomplete="off" value="" tabindex="1" size="15" maxlength="12" taborder="1"></td>
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
		<table border=0 width="<%=formEntryWidth%>">
		<tr>
			<td width="50%" align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=headerEncoder.encodeForJavaScript(returnPage)%>', 'UserChangePassword'); void(0);" tabindex="3">Continue</a></div></td>
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

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	helpTopic = "<%=contextPath%>/help/intro/login.htm#chg_pswd";

	function validateForm() {
		if (frm.handler.value.indexOf("UserChangePassword") >= 0) {
			var alertMessage = "";
			var w;

			w = frm.password.value;
			if ( isEmpty( w ) )
				alertMessage += 'Current Password is required.\n';
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