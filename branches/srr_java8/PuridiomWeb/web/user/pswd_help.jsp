<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	loginId = (String) request.getAttribute("loginId");
	if (loginId == null) {
		loginId = "";
	}
	String errorMsg = (String) request.getAttribute("errorMsg");
	if (errorMsg == null) {
		errorMsg = "";
	}
%>

<tsa:hidden name="resetFailurePage" value="user/pswd_help.jsp"/>
<tsa:hidden name="userVerificationPage" value="user/pswd_help_verification.jsp"/>
<tsa:hidden name="userVerificationSet" value="false"/>
<tsa:hidden name="loginPage" value="index.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Password Help - Step 1</td>
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

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr><td align=center class=error>&nbsp;<%=errorMsg%></td></tr>
<tr><td><br></td></tr>
<tr><td align=center>To reset your password, enter your Login ID below and click on the "Continue" button.
<tr><td><br></td></tr>
<tr>
	<td width=100% valign=top align=center>
		<table border=0 border=0 height=100px>
		<tr>
			<td align=right><B>Login ID (Email Address):<B></td>
			<td><input type=text name="loginId" value="<%=headerEncoder.encodeForHTMLAttribute(loginId)%>" tabindex=1 size=35 maxlength=65 onchange="lowerCase(this); trim(this);"></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br><br>

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr>
	<td width=50% align=center>
		<div id="pxbutton"><a href="javascript: getForgottenPassword(); void(0);">Continue</a></div>
	</td>
	<td width=50% align=center>
		<div id="pxbutton"><a href="javascript: doSubmit('index.jsp', 'DoNothing'); void(0);">Cancel</a></div>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function validateForm() {
		if (frm.handler.value.indexOf("UserProfilePasswordHelp") >= 0) {
			if (isEmpty(frm.loginId.value)) {
				alert("You must enter your Login ID!");
				frm.loginId.focus();
				return false;
			} else {
				frm.mailId.value = frm.loginId.value;
			}
		}
		return true;
	}

	function getForgottenPassword() {
		doSubmit('user/pswd_help_confirmation.jsp', 'UserProfilePasswordHelp');
	}

	function thisLoad() {
		return;
	}
//-->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>