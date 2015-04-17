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

<tsa:hidden name="resetFailurePage" value="user/chg_security_profile.jsp"/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Change Password Security</div>
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

<%@ include file="/user/security_profile_form.jsp"%>

<br>
<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td align=center colspan=2>
		<table border=0 width=100%>
		<tr>
			<td align=center><div id="pxbutton"><a href="javascript: doSubmit('menu/main_menu.jsp', 'UserChangeSecurityProfile'); void(0);" tabindex=10>Save</a></div></td>
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
