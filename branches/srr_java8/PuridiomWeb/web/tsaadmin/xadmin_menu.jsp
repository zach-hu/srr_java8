<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<br>

<table border=0 cellpadding=3 cellspacing=0 width=680px>
<tr>
	<td align=center>
		<table border=0 cellpadding=3 cellspacing=0>
		<tr>
			<td align=right><b>Organization Id:</b></td>
			<td><input type=text name="as_log_oid" value="" onchange="upperCase(this);"></td>
			<td><a href="javascript: getUserLog(); void(0);"><b>DISPLAY USER LOG</b></a></td>
		</tr>
		<tr><td colspan=3><br><hr><br></td></tr>
		<tr>
			<td align=right><b>Password:</b></td>
			<td><input type=text name="encryptedPassword" value=""></td>
			<td><input type=checkbox name="decryptCaseSensitive" value="Y"><b>Case Sensitive</b>&nbsp;<a href="javascript: decryptPassword(); void(0);"><b>DECRYPT PASSWORD</b></a></td>
		</tr>
		<tr><td colspan=3><br><hr><br></td></tr>
		<tr>
			<td align=right><b>Password:</b></td>
			<td><input type=text name="decryptedPassword" value=""></td>
			<td><input type=checkbox name="encryptCaseSensitive" value="Y"><b>Case Sensitive</b>&nbsp;<a href="javascript: encryptPassword(); void(0);"><b>ENCRYPT PASSWORD</b></a></td>
		</tr>
		<tr><td colspan=3><br><hr><br></td></tr>
		<tr>
			<td align="right"><b>Organization Id:</b></td>
			<td><input type=text name="as_refreshlabels_oid" value="" onchange="upperCase(this);"></td>
			<td><a href="javascript: refreshLabels(); void(0);"><b>REFRESH LABELS DICTIONARY FROM DATABASE</b></a></td>
		</tr>
		<tr><td colspan=3><br><hr><br></td></tr>
		</table>
	</td>
</tr>
</table>

<br><br>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;

	document.title = "Puridiom 4.0 Admin Menu";

	function thisLoad() {
		return;
	}

	function decryptPassword() {
		if (!frm.decryptCaseSensitive.checked) {
			upperCase(frm.encryptedPassword);
		}
		frm.action = "<%=contextPath%>/tsaadmin/decrypt_password.jsp";
		frm.submit();
	}

	function encryptPassword() {
		if (!frm.encryptCaseSensitive.checked) {
			upperCase(frm.decryptedPassword);
		}
		frm.action = "<%=contextPath%>/tsaadmin/encrypt_password.jsp";
		frm.submit();
	}
	
	function refreshLabels() {
		frm.organizationId.value = frm.as_refreshlabels_oid.value;
		doSubmit('tsaadmin/update_db_labels.jsp', 'LabelsRefresh');
	}

//-->
</script>