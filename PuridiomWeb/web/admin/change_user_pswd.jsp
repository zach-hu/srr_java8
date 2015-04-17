<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String	errorMsg = (String) request.getAttribute("errorMsg");
	String	selectedUid = (String) request.getAttribute("User_userId");

	if (errorMsg == null) {
		errorMsg = "";
	}
	if (selectedUid == null || selectedUid.trim().length() == 0) {
		errorMsg = "The user id was not found.  Please click the cancel button and try again.";
	}
%>

<br>

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
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=2 width=100%>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-userId", "User ID")%>:</b></td>
			<td width=75px><%=selectedUid%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
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
<tr>	<td colspan=2>
		<br><tsa:hidden name="User_userId" value="<%=selectedUid%>"/>
	</td>
</tr>
<tr>
	<td width="50%" align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enterUserPassword", "Enter User Password")%>:<b></td>
	<td width="50%"><input type=password name="User_userPassword" autocomplete="off" value="" tabindex="1" size="15" maxlength="12" taborder="1" ONCHANGE="this.value=this.value.toUpperCase();"></td>
</tr>
<tr><td colspan=2><br></td></tr>
<tr>
	<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enterSignaturePassword", "Enter Signature Password")%>:</b></td>
	<td><input type=password name="User_signaturePassword" autocomplete="off" value="" tabindex="2" size="15" maxlength="12" taborder="2" ONCHANGE="this.value=this.value.toUpperCase();"></td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('admin/admin_menu.jsp', 'UserResetPasswords'); void(0);">Save</a></div></td>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('admin/admin_menu.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
		</tr>

		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>