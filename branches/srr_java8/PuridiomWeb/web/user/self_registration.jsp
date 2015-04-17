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
<tsa:hidden name="registrationFailurePage" value="/user/self_registration.jsp"/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Self Registration</td>
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

<table border=0 cellspacing=0 cellpadding=0 width=<%=formWidth%>>
<tr>
	<td width=100% valign=top>
		<table width=100% align=center border=0>
		<tr class=mrow>
			<td align="center" colspan="2">
				<table width="100%">
				<tr><td colspan=2 class=error align=center><br><%=errorMsg%></td></tr>
				<tr>
					<td width=10% align=center valign=top><img src="<%=contextPath%>/images/step1off.gif" border="0"></td>
					<td>
					<%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-authenticationInformationNotes", "Enter your email address.  You will use this email address as your login id.  Enter your user id, the code you will be known by to the system (i.e. Network ID, Employee ID, etc), as well as your password. Passwords are case-insensitive. Other restrictions might apply.") %>
					<br>&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr class=mrow>
			<td align="right"><b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-emailAddress", "E-mail address (Puridiom Login ID)") %>:</b>&nbsp;</td>
			<td><input type=text name="UserProfile_mailId"  maxLength=50 value="<%=headerEncoder.encodeForHTMLAttribute(userProfile.getMailId())%>" size=30 onchange="lowerCase(this); trim(this);"></td>
		</tr>
		<tr class=mrow>
			<td align="right" width="45%"><b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-userId", "System User ID") %>:</b>&nbsp;</td>
			<td>
				<input type=text name="UserProfile_userId"  maxLength=15 value="<%=headerEncoder.encodeForHTMLAttribute(userProfile.getUserId())%>" size=20 onchange="upperCase(this); trim(this);">
			</td>
		</tr>
		<tr class=mrow>
			<td align="right"><b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-password", "Password") %>:</b>&nbsp;</td>
			<td><input type=password name="UserProfile_userPassword" autocomplete="off"  maxLength=12 size=20 onchange="upperCase(this)"></td>
		</tr>
		<tr class=mrow>
			<td align="right"><b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-confirmPassword", "Confirm Password") %>:</b>&nbsp;</td>
			<td><input type=password name="confirmPassword" autocomplete="off"  maxLength=12 size=20 onchange="upperCase(this)"></td>
		</tr>
		<tr class=mrow>
			<td align="right"><b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-supervisor", "Supervisor / Manager Name") %>:</b>&nbsp;</td>
			<td><input type=text name="UserProfile_supervisor"  maxLength=50 value="" size=30 onchange="trim(this);"></td>
		</tr>
		<tr class=mrow>
			<td align="right"><b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-supervisorEmail", "Supervisor / Manager E-mail Address") %>:</b>&nbsp;</td>
			<td><input type=text name="UserProfile_supervisorEmail"  maxLength=50 value="" size=30 onchange="lowerCase(this); trim(this);"></td>
		</tr>
		<tr class=mrow>
			<td align=center valign=top colspan=2>
				<table width="100%">
				<tr><td colspan=2><br></td></tr>
				<tr>
					<td width="10%" align=center valign=top><img src="<%=contextPath%>/images/step2off.gif" border="0"></td>
					<td><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-personnelInformationNotes", "Enter your personnel information. Use your official title.<br>Use your Department Code not the Name. Include the phone number Area Code and Extension.") %><br>&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr class=mrow>
			<td align="right"><b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-firstName", "First Name") %>:</b>&nbsp;</td>
			<td><input type=text name="UserProfile_firstName"  maxLength=20 value="<%=headerEncoder.encodeForHTMLAttribute(userProfile.getFirstName())%>" size=30></td>
		</tr>
		<tr class=mrow>
			<td align="right"><b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-middleInitial", "Middle Initial") %>:</b>&nbsp;</td>
			<td><input type=text name="UserProfile_middleInit"  maxLength=1 value="<%=headerEncoder.encodeForHTMLAttribute(userProfile.getMiddleInit())%>" size=2></td>
		</tr>
		<tr class=mrow>
			<td align="right"><b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-lastName", "Last Name") %>:</b>&nbsp;</td>
			<td><input type=text name="UserProfile_lastName"  maxLength=20 value="<%=headerEncoder.encodeForHTMLAttribute(userProfile.getLastName())%>" size=30></td>
		</tr>
		<tr class=mrow>
			<td align="right"><b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-title", "Title") %>:</b>&nbsp;</td>
			<td><input type=text name="UserProfile_title"  maxLength=30 value="<%=headerEncoder.encodeForHTMLAttribute(userProfile.getTitle())%>" size=30></td>
		</tr>
		<tr class=mrow>
			<td align="right"><a href="javascript: browseDepartments('UserProfile_department', 'department'); void(0);"><b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-department", "Department") %>:</b></a>&nbsp;</td>
			<td><input type=text name="UserProfile_department"  maxLength=20 value="<%=headerEncoder.encodeForHTMLAttribute(userProfile.getDepartment())%>" size=30 onchange="upperCase(this)"></td>
		</tr>
		<tr class=mrow>
			<td align="right"><b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-phone", "Phone") %>:</b>&nbsp;</td>
			<td><input type=text name="UserProfile_phoneNumber"  maxLength=30 value="<%=headerEncoder.encodeForHTMLAttribute(userProfile.getPhoneNumber())%>" size=30></td>
		</tr>
		<tr class=mrow>
			<td align="right"><b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-fax", "Fax") %>:</b>&nbsp;</td>
			<td><input type=text name="UserProfile_faxNumber"  maxLength=30 value="<%=headerEncoder.encodeForHTMLAttribute(userProfile.getFaxNumber())%>" size=30></td>
		</tr>
		<tr class=mrow>
			<td align="center" colspan="2">
				<table width="100%">
				<tr><td colspan=2><br></td></tr>
				<tr>
					<td width="10%" align=center valign=top><img src="<%=contextPath%>/images/step3off.gif" border="0"></td>
					<td><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-securityInformationNotes", "Enter security information.  This security information will be used to allow you to retrieve a forgotten password and ensure security.<br>Select a security question and enter your answer in the space provided.") %> &nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr class=mrow>
			<td align=right><b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-securityQuestion", "Security Question") %>:</b></td>
			<td>
				<select name="UserProfile_securityQuestion">
					<option value="" selected>-- Please select your security question. </option>
					<option value="What year were you born in?">What year were you born in?</option>
					<option value="What city/town were you born in?">What city/town were you born in?</option>
					<option value="What is your favorite color?">What is your favorite color?</option>
					<option value="What is your mother's maiden name?">What is your mother's maiden name?</option>
					<option value="What is your pet's name?">What is your pet's name?</option>
				</select>
			</td>
		</tr>
		<tr class=mrow>
			<td align=right><b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-securityAnswer", "Security Answer") %>:</b></td>
			<td><input type=text name="UserProfile_securityAnswer" value="<%=headerEncoder.encodeForHTMLAttribute(userProfile.getSecurityAnswer())%>" size=25 maxLength=40></td>
		</tr>
		</table>

		<table border=0 cellSpacing=0 cellPadding=0 width=80%>
		<tr><td valign=top align=center><%@ include file="/user/recaptcha.jsp" %></td></tr>
		</table>

	</td>
</tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=0 width=<%=formWidth%>>
<tr>
	<td width=50% align=center>
		<div id="pxbutton"><a href="javascript: register(); void(0);">Register</a></div>
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

	frm.userId.value = "";

	var securityQuestion = "<%=headerEncoder.encodeForJavaScript(userProfile.getSecurityQuestion())%>";
	setSecurityQuestion(securityQuestion);

	function validateForm() {
		if (frm.handler.value.indexOf("UserSelfRegistration") >= 0) {
			var alertMessage = "";
			var w;

			w = frm.UserProfile_organizationId.value;
			if ( isEmpty( w ) )
				alertMessage += 'Organization Id is required.\n';
			w = frm.UserProfile_userId.value;
			if ( isEmpty( w ) )
				alertMessage += 'User ID is required.\n';
			w = frm.UserProfile_userPassword.value;
			if ( isEmpty( w ) )
				alertMessage += 'Password is required.\n';
			w = frm.confirmPassword.value;
			if ( isEmpty( w ) )
				alertMessage += 'Please enter the Password Confirmation. Re-type your password.\n';
			if ( w != frm.UserProfile_userPassword.value )
				alertMessage += 'Password Confirmation does not match Password.\n';
			w = frm.UserProfile_firstName.value;
			if ( isEmpty( w ) )
				alertMessage += 'First Name is required.\n';
			w = frm.UserProfile_lastName.value;
			if ( isEmpty( w ) )
				alertMessage += 'Last Name is required.\n';
			w = frm.UserProfile_mailId.value;
			if ( isEmpty( w ) )
				alertMessage += 'Email address is required for your login id.\n';
			w = frm.UserProfile_securityQuestion[frm.UserProfile_securityQuestion.selectedIndex].value;
			if ( isEmpty( w ) ) {
				w = frm.UserProfile_securityAnswer.value;
				if ( isEmpty( w ) ) {
					alertMessage += 'Security Question and Answer are required.\n';
				} else {
					alertMessage += 'A Security Question is required.\n';
				}
			} else {
				w = frm.UserProfile_securityAnswer.value;
				if ( isEmpty( w ) )
					alertMessage += 'An Answer to your Security Question is required.\n';
			}

			if ( alertMessage.length > 0 ) {
			    	alert ( 'Please fix the following problems:\n\n'+alertMessage );
			    	return false;
			}
		}
		return true;
	}

	function register() {
		doSubmit('menu/main_menu.jsp', 'UserSelfRegistration');
	}

	function thisLoad() {
		return;
	}

	function browseDepartments(fld, browseName) {
		var	oid = frm.UserProfile_organizationId.value;
		if ( isEmpty( oid ) ) {
			alert("Organization Id is required before browsing departments.");
			return false;
		}
		frm.organizationId.value = oid;
		browseLookup(fld, browseName);
	}

	function setSecurityQuestion(x) {
		var questionOptions = frm.UserProfile_securityQuestion.options;
		for (var i=0; i < questionOptions.length; i++) {
			if (frm.UserProfile_securityQuestion[i].value == x) {
				frm.UserProfile_securityQuestion.selectedIndex = i;
				break;
			}
		}
	}
//-->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>