<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String errorMsg = (String) request.getAttribute("errorMsg");
	if (errorMsg == null) {
		errorMsg = "";
	}
%>

<tsa:hidden name="resetFailurePage" value="user/chg_pswd.jsp"/>
<tsa:hidden name="reviewProfilePage" value="user/user_profile.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Change Password / Security</div>
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
<tr>
	<td colspan=2 align=center class="error"><%=errorMsg%></td>
</tr>
<tr>	<td colspan=2><br></td></tr>
<tr>
	<td width="50%" align=right><b>Enter Current Password:<b></td>
	<td width="50%"><input type=password name="password" autocomplete="off" value="" tabindex="1" size="15" maxlength="12" taborder="1" ONCHANGE="this.value=this.value.toUpperCase();"></td>
</tr>
<tr><td colspan=2><br></td></tr>
<tr>
	<td align=right><b>Enter New Password:</b></td>
	<td><input type=password name="newPassword" autocomplete="off" value="" tabindex="2" size="15" maxlength="12" taborder="2" ONCHANGE="this.value=this.value.toUpperCase();"></td>
</tr>
<tr><td colspan=2><br></td></tr>
<tr>
	<td align=right><b>Confirm New Password:</b></td>
	<td><input type=password name="confirmPassword" autocomplete="off" value="" tabindex="3" size="15" maxlength="12" taborder="2" ONCHANGE="this.value=this.value.toUpperCase();"></td>
</tr>
<tr><td colspan=2><br><br></td></tr>

<tr><td colspan=2 align=center><%@ include file="/user/security_profile_form.jsp"%></td></tr>

<tr><td colspan=2><br><br></td></tr>
<tr>
	<td align=center colspan=2>
		<table border=0>
		<tr>
			<td align=center><div id="pxbutton"><a href="javascript: doSubmit('menu/main_menu.jsp', 'AuditTrailSetup;UserChangeSecurityProfile;UserChangePassword'); void(0);" tabindex=10>Save</a></div></td>
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

	function validateForm() {
		return validateUserSecurityForm();
	}

//-->
</SCRIPT>
