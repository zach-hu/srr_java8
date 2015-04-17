<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String errorMsg = 	(String)request.getAttribute("errorMsg");
	errorMsg = HiltonUtility.ckNull(errorMsg);
	if (errorMsg.length() > 0) {
		errorMsg = errorMsg + "<br>";
	}
	UserProfile userProfile = (UserProfile) request.getAttribute("userProfile");
	if (userProfile == null) {
		userProfile = new UserProfile();
	}
%>

<tsa:hidden name="loginFailurePage" value="index.jsp"/>
<tsa:hidden name="registrationFailurePage" value="/user/xpress_user_registration.jsp"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="password" value=""/>
<tsa:hidden name="newPassword" value=""/>
<tsa:hidden name="UserProfile_organizationId" value="<%= oid %>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>FREE Trial - Account Registration</td>
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

<br>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=100% valign=top align=center>
		<table border=0 cellSpacing=0 cellPadding=0 width=500px>
		<tr>
			<td colspan=3 class=error align=center>&nbsp;</td>
		</tr>
		<tr>
			<td colspan=3>
				<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-registrationInstructions", "To register for your <font class=error>FREE 30 DAY TRIAL</font>, please complete the information below and select the &quot;Register&quot; button. A password will be emailed to you with further instructions.")%>
			</td> 
		</tr>
		</table>

		<br><br>

		<table border=0 cellSpacing=0 cellPadding=0 width=500px>
		<tr>
			<td width=100%>
				<br>

				<table border=0 cellSpacing=0 cellPadding=0 width=100%>
				<tr>
					<td><b>Company Name<font color="#0000ff">* </font></b></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan=3><input type=text tabindex=1 name="UserProfile_organizationName" size=40 maxlength=40 value=""></td>
				</tr>
				<tr>
					<td><b>First Name<font color="#0000ff">* </font></b></td>
					<td width=100px align=center><b>MI</b></td>
					<td><b>Last Name<font color="#0000ff">* </font></b> </td>
				</tr>
				<tr>
					<td><input type=text tabindex=2 name="UserProfile_firstName" size=40 maxlength=20 value=""></td>
					<td align=center><input type=text tabindex=3 name="UserProfile_middleInitial" size=3 maxlength=2 value=""></td>
					<td><input type=text tabindex=4 name="UserProfile_lastName" size=40 maxlength=20 value=""></td>
				</tr>
				<tr>
					<td>
						<B>Email Address </B> 
						<font color="#0000ff">*</font>
					</td>
					<td>&nbsp;</td>
					<td>
						<B>Confirm Email Address </B> 
						<font color="#0000ff">*</font>
					</td>
				</tr>
				<tr>
					<td vAlign=top><input type=text tabindex=5 name="UserProfile_mailId" size=40 maxlength=50 value=""></td>
					<td>&nbsp;</td>
					<td vAlign=top><input type=text tabindex=6 name="confirmMailId" size=40 maxlength=50 value=""></td>
				</tr>
				<tr>
					<td><b>Phone<font color="#0000ff">* </font></b></td>
					<td>&nbsp;</td>
					<td><b>Fax</b></td>
				</tr>
				<tr>
					<td><input type=text tabindex=7 name="UserProfile_phoneNumber" size=25 maxlength=30 value=""></td>
					<td>&nbsp;</td>
					<td><input type=text tabindex=8 name="UserProfile_faxNumber" size=25 maxlength=30 value=""></td>
				</tr>
				</table>

				<br>

				<table border=0 cellspacing=0 cellpadding=2>
				<tr>
					<td><input type=checkbox name="c_checkbox" value="Y" tabIndex=10></td>
					<td><b>I agree to the <a href="javascript: getTermsOfService(); void(0);">Terms of Service</a>.</b><font color="#0000ff">*</font></td>
				</tr>
				</table>

				<br><br>

				<table border=0 cellSpacing=0 cellPadding=0 width=100%>
				<tr align=middle>
					<td>
						<center>
							<font color="#0000ff">*</font>
							<b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-requiredInformation", "REQUIRED INFORMATION")%>.</b> 
						</center>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>

		<br><br>

		<table border=0 cellSpacing=0 cellPadding=0 width=500px>
		<tr>
			<td valign=top><img src="<%=contextPath%>/images/privacy_sq.gif" border=0 width=14px height=12px>
			<%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-secureNotes", "Your email address and personal information are confidential and will never be disclosed, shared or sold to any outside company for any purpose.")%>  See our <a href="javascript: getPrivacyPolicy(); void(0);">Privacy Policy</a> for more details.<br></td>
		</tr>
		</table>

		<br><br><br>

		<table border=0 cellspacing=0 cellpadding=0 width=100%>
		<tr>
			<td width=50% align=center>
				<a href="javascript: register(); void(0);">
					<img class=button src="<%=contextPath%>/images/button_register.gif" border=0 tabIndex=12>
				</a>
			</td>
			<td width=50% align=center>
				<a href="javascript: doSubmit('index.jsp', 'DoNothing'); void(0);">
					<img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 tabIndex=14>
				</a>
			</td>
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

	frm.userId.value = "";

	function validateForm() {
		if (frm.handler.value.indexOf("UserSelfRegistration") >= 0) {
			var alertMessage = "";
			var w;

			w = frm.UserProfile_mailId.value;
			if ( isEmpty( w ) )
				alertMessage += 'Email address is required for your account.\n';
			w = frm.confirmMailId.value;
			if ( isEmpty( w ) )
				alertMessage += 'Please re-type your email address to confirm it has been entered correctly.\n';
			if ( w != frm.UserProfile_mailId.value )
				alertMessage += 'Email confirmation does not match Email Address.\n';
			w = frm.UserProfile_firstName.value;
			if ( isEmpty( w ) )
				alertMessage += 'First Name is required.\n';
			w = frm.UserProfile_lastName.value;
			if ( isEmpty( w ) )
				alertMessage += 'Last Name is required.\n';
			if ( alertMessage.length > 0 ) {
			    	alert ( 'Please fix the following problems:\n\n'+alertMessage );
			    	return false;
			}
		}
		return true;
	}

	function register() {
		doSubmit('user/xpress_user_registration2.jsp', 'DoNothing');
	}

	function thisLoad() {
		return;
	}

	function getPrivacyPolicy() {
		return;
	}
//-->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>