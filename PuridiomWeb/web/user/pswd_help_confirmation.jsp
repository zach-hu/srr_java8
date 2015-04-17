<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String confirmationMsg = (String) request.getAttribute("confirmationMsg");
	if (confirmationMsg == null) {
		confirmationMsg = "";
	}
	String errorMsg = (String) request.getAttribute("errorMsg");
	if (errorMsg == null) {
		errorMsg = "";
	}
%>

<tsa:hidden name="resetFailurePage" value="user/pswd_help.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Password Help</td>
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

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%> height=100px>
<tr><td align=center class=error><%=errorMsg%></td></tr>
<tr><td align=center><b><%=confirmationMsg%></b></td></tr>
<tr><td align=center>To return to the login page, click the "Return" button below.</td></tr>
</table>

<br><br>

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr><td align=center><div id="pxbutton"><a href="javascript: doSubmit('index.jsp', 'DoNothing'); void(0);">Return</a></div></td></tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function thisLoad() {
		return;
	}
//-->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>