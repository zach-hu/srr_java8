<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String errorMsg = (String) request.getAttribute("errorMsg");
	String icPackage = (String) request.getAttribute("icPackage");
	String packageName = (String) request.getAttribute("packageName");
	String packagePrice = (String) request.getAttribute("packagePrice");

	if (HiltonUtility.isEmpty(errorMsg)) {
		errorMsg = "<br>";
	}
	UserProfile userProfile = (UserProfile) request.getAttribute("userProfile");
	String companyName = HiltonUtility.ckNull((String) request.getAttribute("companyName"));
	if (userProfile == null) {
		userProfile = new UserProfile();
	}
%>

<tsa:hidden name="loginFailurePage" value="index.jsp"/>
<tsa:hidden name="registrationFailurePage" value="/user/xpress_user_registration.jsp"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="UserProfile_organizationId" value="<%= oid %>"/>
<tsa:hidden name="PackagePricing_icPackage" value="<%=icPackage%>"/>
<tsa:hidden name="icPackage" value="<%=icPackage%>"/>
<tsa:hidden name="packageName" value="<%=packageName%>"/>
<tsa:hidden name="packagePrice" value="<%=packagePrice%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
								<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "registrationStep2", "Registration Step 2 of 3 - Administrator Information", false)%></div>
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

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr>
	<td width=100% valign=top align=center>
		<table border=0 cellSpacing=0 cellPadding=0 width=80%>
		<tr><td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-registrationInstructions", "To register for your <font class=error>" + packageName.toUpperCase() + "</font>, please complete the information below and select the &quot;Continue&quot; button. Login information will be emailed to you with further instructions once the registration process is complete.")%></td></tr>
		<tr><td class=error><br><%=errorMsg%></td></tr>
		</table>

		<br>

		<table border=0 cellSpacing=0 cellPadding=0 width=80%>
		<tr>
			<td width=100%>
				<table border=0 cellSpacing=0 cellPadding=0 width=500px>
				<tr>
					<td><b>Company Name<font color="#0000ff">* </font></b></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan=3><input type=text tabindex=1 name="companyName" size=40 maxlength=40 value="<%=companyName%>"></td>
				</tr>
				<tr>
					<td><b>First Name<font color="#0000ff">* </font></b></td>
					<td width=100px align=center><b>MI</b></td>
					<td><b>Last Name<font color="#0000ff">* </font></b> </td>
				</tr>
				<tr>
					<td><input type=text tabindex=2 name="UserProfile_firstName" size=40 maxlength=20 value="<%=userProfile.getFirstName()%>"></td>
					<td align=center><input type=text tabindex=3 name="UserProfile_middleInit" size=3 maxlength=2 value="<%=userProfile.getMiddleInit()%>"></td>
					<td><input type=text tabindex=4 name="UserProfile_lastName" size=40 maxlength=20 value="<%=userProfile.getLastName()%>"></td>
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
					<td vAlign=top><input type=text tabindex=5 name="UserProfile_mailId" size=40 maxlength=50 value="<%=userProfile.getMailId()%>"></td>
					<td>&nbsp;</td>
					<td vAlign=top><input type=text tabindex=6 name="confirmMailId" size=40 maxlength=50 value="<%=userProfile.getMailId()%>"></td>
				</tr>
				<tr>
					<td><b>Phone<font color="#0000ff">* </font></b></td>
					<td>&nbsp;</td>
					<td><b>Fax</b></td>
				</tr>
				<tr>
					<td><input type=text tabindex=7 name="UserProfile_phoneNumber" size=25 maxlength=30 value="<%=userProfile.getPhoneNumber()%>"></td>
					<td>&nbsp;</td>
					<td><input type=text tabindex=8 name="UserProfile_faxNumber" size=25 maxlength=30 value="<%=userProfile.getFaxNumber()%>"></td>
				</tr>
				</table>

				<br>

				<table border=0 cellspacing=0 cellpadding=2>
				<tr>
					<td><input type=checkbox name="termsAccepted" value="Y" tabIndex=10></td>
					<td><b>I agree to the <a href="http://www.puridiom.com/terms.asp" target='_blank'>Terms of Service</a>.</b><font color="#0000ff">*</font></td>
				</tr>
				</table>

				<br>

				<table border=0 cellSpacing=0 cellPadding=0>
				<tr>
					<td>
							<font color="#0000ff">*</font>
							<b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-requiredInformation", "REQUIRED INFORMATION")%>.</b>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>

		<br>

		<table border=0 cellSpacing=0 cellPadding=0 width=80%>
		<tr>
			<td valign=top><img src="<%=contextPath%>/images/privacy_sq.gif" border=0 width=14px height=12px>
			<%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-secureNotes", "Your email address and personal information are confidential and will never be disclosed, shared or sold to any outside company for any purpose.")%>  See our <a href="http://www.puridiom.com/privacy.asp" target='_blank'>Privacy Policy</a> for more details.
			</td>
		</tr>
		</table>

		<table border=0 cellSpacing=0 cellPadding=0 width=80%>
		<tr><td valign=top><%@ include file="/user/recaptcha.jsp" %></td></tr>
		</table>

		<table border=0 cellspacing=0 cellpadding=0 width=100%>
		<tr>
			<td width=50% align=center>
				<div id="pxbutton"><a href="javascript: register(); void(0);">Continue</a></div>
			</td>
			<td width=50% align=center>
				<div id="pxbutton"><a href="javascript: doSubmit('index.jsp', 'DoNothing'); void(0);">Cancel</a></div>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 ccellpadding=0 cellspacing=0 width=<%=formWidth%>>
<tr><td align=right><a href="http://www.instantssl.com">
<img src="<%=contextPath%>/images/horz_master_100pixels.gif" alt="Instant SSL Certificate Secure Site" width="100" height="60" style="border: 0px;"><br>Instant SSL Certificate Secured</a>
</td></tr>
</table>

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
			w = frm.UserProfile_lastName.value;
			if ( !frm.termsAccepted.checked )
				alertMessage += 'You must review and agree to the Terms of Service before continuing.\n';
			if ( alertMessage.length > 0 ) {
			    	alert ( 'Please fix the following problems:\n\n'+alertMessage );
			    	return false;
			}
		}
		return true;
	}

	function register() {
		doSubmit('user/an_registration.jsp', 'XpressSelfRegistration');
	}

	function thisLoad() {
		return;
	}
//-->
</SCRIPT>

<!--script language="javascript" type="text/javascript">
//<![CDATA[
	var cot_loc0=(window.location.protocol == "https:")? "https://secure.comodo.net/trustlogo/javascript/cot.js" :
	"http://www.trustlogo.com/trustlogo/javascript/cot.js";
	document.writeln('<scr' + 'ipt language="JavaScript" src="'+cot_loc0+'" type="text\/javascript">' + '<\/scr' + 'ipt>');
//]]>
</script>

<a href="http://www.instantssl.com" id="comodoTL">SSL</a>

<script language="JavaScript" type="text/javascript">
	COT("<%=contextPath%>/images/secure_site.gif", "SC2", "none");
</script-->

<%@ include file="/system/prevent_caching.jsp" %>