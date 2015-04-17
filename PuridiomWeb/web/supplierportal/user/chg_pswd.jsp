<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%
	String errorMsg = (String) request.getAttribute("errorMsg");
	String returnPage = (String) request.getAttribute("returnPage");
	if (errorMsg == null) {
		errorMsg = "";
	}
	if (HiltonUtility.isEmpty(returnPage)) {
		returnPage = "menu/main_menu.jsp";
	}
%>
<tsa:hidden name="returnPage" value="<%=returnPage%>"/>
<tsa:hidden name="resetFailurePage" value="user/chg_pswd.jsp"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
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

<table border=0 cellspacing=0 cellpadding=2 width=680px>
<tr>
	<td colspan=2 align=center class="error"><%=errorMsg%></td>
</tr>
<tr>	<td colspan=2><br></td></tr>
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
		<table border=0 width="600">
		<tr>
			<td width="50%" align=center><a href="javascript: doSubmit('<%=returnPage%>', 'RegisterUserChangePassword'); void(0);" tabindex="3"><img class=button src="<%=contextPath%>/supplierportal/images/button_save.gif" border=0></a></td>
<%	if (user.isAuthenticated() && !returnPage.equals("menu/main_menu.jsp")) {%>
			<td width="50%" align=center><a href="javascript: doSubmit('<%=returnPage%>', 'DoNothing'); void(0);" border=0 tabindex="4"><img class=button src="<%=contextPath%>/supplierportal/images/button_cancel.gif" border=0></a></td>
<%	}%>
		</tr>
		</table>
	</td>
</tr>
<tr><td colspan=2><br><tsa:hidden name="temp" value=""/></td></tr>
</table>
<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function validateForm() {
		if (frm.handler.value.indexOf("ContactChangePassword") >= 0) {
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
				alertMessage += 'Please enter the Confirmation your Password. (Re-type your password).\n';
			} else if ( w != frm.newPassword.value ) {
				alertMessage += 'Password Confirmation does not match your New Password.\n';
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
