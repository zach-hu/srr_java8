<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%

	String errorMsg = 	(String)request.getAttribute("errorMsg");
	String icPackage = (String) request.getAttribute("icPackage");
	String packageName = (String) request.getAttribute("packageName");
	String packagePrice = (String) request.getAttribute("packagePrice");
	String icOrgPackage = (String) request.getAttribute("OrganizationPackage_icOrgPackage");
	UserProfile userProfile = (UserProfile) request.getAttribute("userProfile");
	
	errorMsg = HiltonUtility.ckNull(errorMsg);
	if (errorMsg.length() > 0) {
		errorMsg = errorMsg + "<br>";
	}
	
	if (userProfile == null) {
		userProfile = new UserProfile();
		oid = HiltonUtility.ckNull((String) request.getAttribute("UserProfile_organizationId"));
		uid = HiltonUtility.ckNull((String) request.getAttribute("UserProfile_userId"));	
	} else {
		oid = userProfile.getOrganizationId();
		uid = userProfile.getUserId();
	}
	
	if (HiltonUtility.isEmpty(oid)) {
		oid = (String) request.getAttribute("UserProfile_organizationId");
	}
	if (HiltonUtility.isEmpty(uid)) {
		uid = (String) request.getAttribute("UserProfile_userId");
	}
	if (HiltonUtility.isEmpty(icOrgPackage)) {
		OrganizationPackage op = (OrganizationPackage) request.getAttribute("organizationPackage");
		if (op != null) {
			icOrgPackage = String.valueOf(op.getIcOrgPackage());
		}
	}
	
	String anUrl = (String) request.getAttribute("anUrl");
	String fullContextPath = (String) request.getAttribute("contextPath");
	String x_login = (String) request.getAttribute("x_login");
	String x_type = (String) request.getAttribute("x_type");
	String x_amount = (String) request.getAttribute("x_amount");
	String x_description = (String) request.getAttribute("x_description");
	String x_invoice_num = (String) request.getAttribute("x_invoice_num");
	String x_fp_sequence = (String) request.getAttribute("x_fp_sequence");
	String x_fp_timestamp = (String) request.getAttribute("x_fp_timestamp");
	String x_fp_hash = (String) request.getAttribute("x_fp_hash");
	String x_show_form = (String) request.getAttribute("x_show_form");
	String x_relay_response = (String) request.getAttribute("x_relay_response");
	String x_logo_url = (String) request.getAttribute("x_logo_url");
	String x_relay_url = (String) request.getAttribute("x_relay_url");
	String x_color_background = (String) request.getAttribute("x_color_background");
	String x_test_request = (String) request.getAttribute("x_test_request");
	String x_first_name = (String) request.getAttribute("x_first_name");
	String x_last_name = (String) request.getAttribute("x_last_name");
	String x_company = (String) request.getAttribute("x_company");
	String x_email = (String) request.getAttribute("x_email");
	String x_cust_id = (String) request.getAttribute("x_cust_id");
%>
<!--
	Input fields containing necessary SIM post values
	Additional fields can be added here as outlined in the SIM integration guide 
	at: http://developer.authorize.net
-->
<tsa:hidden name="x_login" value="<%=x_login%>"/>
<tsa:hidden name="x_type" value="<%=x_type%>"/>
<tsa:hidden name="x_amount" value="<%=x_amount%>"/>
<tsa:hidden name="x_description" value="<%=x_description%>"/>
<tsa:hidden name="x_invoice_num" value="<%=x_invoice_num%>"/>
<tsa:hidden name="x_fp_sequence" value="<%=x_fp_sequence%>"/>
<tsa:hidden name="x_fp_timestamp" value="<%=x_fp_timestamp%>"/>
<tsa:hidden name="x_fp_hash" value="<%=x_fp_hash%>"/>
<tsa:hidden name="x_test_request" value="<%=x_test_request%>"/>
<tsa:hidden name="x_show_form" value="<%=x_show_form%>"/>
<tsa:hidden name="x_relay_response" value="<%=x_relay_response%>"/>
<tsa:hidden name="x_relay_url" value="<%=x_relay_url%>"/>
<tsa:hidden name="x_logo_url" value="<%=x_logo_url%>"/>
<tsa:hidden name="x_color_background" value="<%=x_color_background%>"/>
<tsa:hidden name="x_cust_id" value="<%=x_cust_id%>"/>

<tsa:hidden name="loginId" value="<%=userProfile.getMailId()%>"/>
<tsa:hidden name="UserProfile_organizationId" value="<%=oid%>"/>
<tsa:hidden name="UserProfile_userId" value="${userId}"/>
<tsa:hidden name="icPackage" value="<%=icPackage%>"/>
<tsa:hidden name="packageName" value="<%=packageName%>"/>
<tsa:hidden name="packagePrice" value="<%=packagePrice%>"/>
<tsa:hidden name="OrganizationPackage_icOrgPackage" value="<%=icOrgPackage%>"/>
<tsa:hidden name="contextPath" value="<%=fullContextPath%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "registrationStep3", "Registration Step 3 of 3 - Billing Information", false)%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px width=*>
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

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr>
	<td width=100% valign=top>
		<table width=90% align=center border=0>
		<tr class=mrow>
			<td colspan="2">
				<br><br>
				
				<table border=0 width=100%>
				<tr>
					<td>
						<p><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-anBillingInformationInstructions", "To ensure your security, Puridiom Xpress does not store any of your credit card information.  Our registration and payment processes utilize Authorize.Net to ensure fast, reliable and secure transmission of payment transactions and credit card information.  By clicking the continue button below you authorize us to provide your credit card information to Authorize.Net.") %></p>
						<p>&nbsp;</p>
<%	if (packageName.toUpperCase().startsWith("FREE")) {%>
						<p class=error><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-trialBillingInformationNotes", "Billing Information provided will only be used AFTER your free trial has expired!") %></p>
						<p>&nbsp;</p>
						<p><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-trialBillingInformationInstructions", "Once your free trial has ended, your membership will automatically renew at the Individual subscription rate of US $ 500.00 so you can continue service without interruption.  <b>You will never be charged during the free trial, and you can cancel at any time.</b>") %></p>
<%	} else {%>
						<p><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-billingInformationInstructions", "Your membership will automatically renew each year at the " + packageName + " subscription rate so you can continue service without interruption.  <b>You can cancel at any time.</b>") %></p>
<%	}%>
				</tr>
				</table>

				<table border=0 width=100%>
				<tr><td class=error><%=errorMsg%></td></tr>
				<tr><td></td></tr>
				</table>

				<br><br>

				<table border=0 width=100%>
				<tr><td><a href="http://www.authorize.net/company/aboutus/" target="_blank">Click here to view more information about Authorize.Net</a></td></tr>
				</table>

				<br><br>
				
				<table border=0 cellspacing=0 cellpadding=0 width=80% align=left>
				<tr>
					<td width=50% align=center>
						<div id="pxbutton"><a href="javascript: continueToPaymentForm(); void(0);">Continue</a></div>
					</td>
					<td width=50% align=center>
						<div id="pxbutton"><a href="javascript: doSubmit('index.jsp', 'UserCancelSelfRegistration'); void(0);">Cancel</a></div>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formWidth%>>
<tr><td align=right><a href="http://www.instantssl.com">
<img src="<%=contextPath%>/images/horz_master_100pixels.gif" alt="Instant SSL Certificate Secure Site" width="100" height="60" style="border: 0px;"><br>Instant SSL Certificate Secured</a>
</td></tr>
</table>

<%@ include file="/system/footer.jsp" %>


<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	
	frm.userId.value = "${esapi:encodeForJavaScript(userId)}";
	frm.organizationId.value = "<%=oid%>";
	
	function continueToPaymentForm() {
		frm.action = "<%=anUrl%>";
		frm.submit();
	}
//-->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>