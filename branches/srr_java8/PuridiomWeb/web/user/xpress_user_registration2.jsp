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
	String registeredMailId = (String) request.getAttribute("UserProfile_mailId");

	/******	TEMPORARY FOR PROTOTYPE  ******/
	confirmationMsg = "Your Puridiom Xpress trial account has been created.  A confirmation has been sent to the email address shown below.  If this email address is incorrect, <a href=" + '"' + "javascript: changeMailId(); void(0);" + '"' + ">correct it now</a>.";
	/******	TEMPORARY FOR PROTOTYPE  ******/
%>

<tsa:hidden name="resetFailurePage" value="user/pswd_help.jsp"/>
<tsa:hidden name="UserProfile_mailId" value="<%=registeredMailId%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>FREE Trial - Create Password</td>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
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

<table border=0 cellspacing=0 cellpadding=0 width=680px height=100px>
<tr>
	<td align=center>
		<table border=0 cellspacing=0 cellpadding=0 width=90% height=100px>
		<tr><td class=error><%=errorMsg%></td></tr>
		<tr><td><b><%=confirmationMsg%>  To continue with your Puridiom Xpress account setup, you must create a password below.</b></td></tr>
		<tr><td class=error><b><%=registeredMailId%></b></td></tr>
		</table>

		<br><br>

		<table border=0 cellspacing=0 cellpadding=0 width=90%>
		<tr class=mrow>
			<td width=200px><b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-password", "Password") %></b>&nbsp;</td>
			<td width=200px><b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-confirmPassword", "Confirm Password") %></b>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr class=mrow>
			<td><input type=password name="confirmPassword" autocomplete="off" maxLength=12 size=20 onchange="upperCase(this)"></td>
			<td><input type=password name="UserProfile_userPassword" autocomplete="off" maxLength=12 size=20 onchange="upperCase(this)"></td>
			<td>&nbsp;</td>
		</tr>
		</table>

		<br><br><br>

		<table border=0 cellspacing=0 cellpadding=0 width=90%>
		<tr>
			<td width=125px align=center><a href="javascript: createPassword(); void(0);"><img class=button src="<%=contextPath%>/images/button_submit.gif" tabindex=3 border=0></a></td>
			<td width=75px>&nbsp;</td>
			<td width=125px align=center><a href="javascript: doSubmit('index.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" tabindex=3 border=0></a></td>
			<td>&nbsp;</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br><br><br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function thisLoad() {
		return;
	}

	function createPassword() {
		doSubmit('/user/xpress_user_registration3.jsp', 'DoNothing');
	}
//-->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>