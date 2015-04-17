<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<table border=0 cellpadding=3 cellspacing=0 width=680px>
<tr>
	<td align=right><b>Password to decrypt:</b></td>
	<td><input type=text name="encryptedPassword" value="" onchange="upperCase(this);"></td>
</tr>
<tr><td colspan=2><br></td></tr>
<tr>
	<td colspan=2 align=center><a href="javascript: submitThis(); void(0);"><img src="<%=contextPath%>/images/button_ok.gif" border=0 class=button></a></td>
</tr>
</table>

<br><br><br><br>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;

	function submitThis() {
		frm.action = "<%=contextPath%>/tsaadmin/decrypt_password.jsp";
		frm.submit();
	}
//-->
</script>