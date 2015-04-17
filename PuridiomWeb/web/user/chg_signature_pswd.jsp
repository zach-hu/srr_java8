<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%
	String errorMsg = (String) request.getAttribute("errorMsg");

	if (errorMsg == null) {
		errorMsg = "";
	}
%>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<tsa:hidden name="resetFailurePage" value="user/chg_signature_pswd.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Change Signature Password</div>
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

<table border=0 cellspacing=0 cellpadding=2 width=680px>
<tr><td colspan=2 align=center class="error"><%=errorMsg%></td></tr>
<tr>	<td colspan=2><br></td></tr>
<tr>
	<td width="50%" align=right><b>Enter Current Signature Password:<b></td>
	<td width="50%"><input type=password name="password" autocomplete="off" value="" tabindex="1" size="15" maxlength="12" taborder="1" ONCHANGE="this.value=this.value.toUpperCase();"></td>
</tr>
<tr><td colspan=2><br></td></tr>
<tr>
	<td align=right><b>Enter New Signature Password:</b></td>
	<td><input type=password name="newPassword" autocomplete="off" value="" tabindex="2" size="15" maxlength="12" taborder="2" ONCHANGE="this.value=this.value.toUpperCase();"></td>
</tr>
<tr><td colspan=2><br></td></tr>
<tr>
	<td align=right><b>Confirm New Signature Password:</b></td>
	<td><input type=password name="confirmPassword" autocomplete="off" value="" tabindex="3" size="15" maxlength="12" taborder="2" ONCHANGE="this.value=this.value.toUpperCase();"></td>
</tr>
<tr><td colspan=2><br><br></td></tr>
<tr>
	<td align=center colspan=2>
		<table border=0 width="600px">
		<tr>
			<% if (oid.equalsIgnoreCase("vse06p")) { %>
			<td width="50%" align=center><div id="pxbutton"><a href="javascript: doSubmit('user/user_profile.jsp', 'UserProfileValidateRetrieve;UserChangeSignaturePassword'); void(0);" tabindex="3">Save</a></div></td>
			<% } else { %>
			<td width="50%" align=center><div id="pxbutton"><a href="javascript: doSubmit('user/user_profile.jsp', 'UserChangeSignaturePassword'); void(0);" tabindex="3">Save</a></div></td>
			<% } %>
			<td width="50%" align=center><div id="pxbutton"><a href="javascript: doSubmit('user/user_profile.jsp', 'DoNothing'); void(0);" border=0 tabindex="4">Cancel</a></div></td>
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
		var handler = frm.handler.value;
		var w;

		if (handler.indexOf("UserChangeSignaturePassword") >= 0) {
			w = frm.password.value;
			if ( isEmpty( w ) )
				alertMessage += 'Current Signature Password is required.\n';
			w = frm.newPassword.value;
			if ( isEmpty( w ) )
				alertMessage += 'New Signature Password is required.\n';
			w = frm.confirmPassword.value;
			if ( isEmpty( w ) ) {
				alertMessage += 'Please enter the Confirmation for the Signature Password. Re-type your password.\n';
			} else if ( w != frm.newPassword.value )
				alertMessage += 'Password Confirmation does not match New Signature Password.\n';

			if ( alertMessage.length > 0 ) {
			    	alert ( 'Please fix the following problems:\n\n'+alertMessage );
			    	return false;
			}
		}
		return true;
	}

//-->
</SCRIPT>
