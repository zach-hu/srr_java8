<table border=0 cellspacing=0 cellpadding=2 width=<%=formEntryWidth%>>
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
</table>

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
