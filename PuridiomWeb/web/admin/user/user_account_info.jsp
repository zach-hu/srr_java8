<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dates.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dynamicTables.js"></SCRIPT>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	errorMsg = (String) request.getAttribute("errorMsg");
	String	renewalCost = (String) request.getAttribute("organizationPackageTotal");
	String	currentGroupPackage = (String) request.getAttribute("currentGroupPackage");
	String	renewalMsg = "";
	String	proratedMsg = "";
	Date renewalDate = null;

	if (oid != null) {
		renewalDate = OrganizationManager.getInstance().getOrganization(oid).getDateExpires();
	}
	if (renewalDate == null) {
		renewalDate = new Date();
	}
	BigDecimal daysToRenewal = HiltonUtility.getDateDifference(new Date(), renewalDate);
%>

<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="pageFrom" value="useraccount"/>
<tsa:hidden name="currentPage" value="/admin/user/user_account_info.jsp"/>
<tsa:hidden name="allowEdit" value="Y"/>
<tsa:hidden name="returnPage" value="/admin/user/user_account_info.jsp"/>
<tsa:hidden name="returnHandler" value="PackagePricingRetrieveAll"/>
<tsa:hidden name="fromSave" value="Y"/>
<tsa:hidden name="icPackage" value=""/>
<tsa:hidden name="packageName" value=""/>
<tsa:hidden name="packagePrice" value=""/>

<table width=<%=formWidth%> cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "myaccount", "My Account", false)%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px width=*>
		<div id="userLicenseDisplay"><%@ include file="/admin/user/user_available_licenses.jsp" %></div>
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

<%	if (!HiltonUtility.isEmpty(errorMsg)) {%>
<table border=0 cellpadding=2 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=100% align=center class=error><%=errorMsg%></td>
</tr>
</table>
<%	}%>

<br>

<table border=0 cellpadding=2 cellspacing=0 width=70%>
<tr>
	<td width=10px>&nbsp</td>
	<td valign=top><div id="renewalMsg" style="font-weight:bold"></div></td>
</tr>
<tr>
	<td width=10px>&nbsp</td>
	<td valign=top><div id="proratedMsg"></div></td>
</tr>
</table>

<div style="width:<%=formWidth%>;align:center;">
	<div style="width:70%;valign:top;float:left">
		<%@ include file="/admin/user/package_options.jsp" %>
	</div>
	<div>
		<br>
		<table border=1 cellpadding=0 cellspacing=0>
		<tr><td class=browseHdr>&nbsp;My Account Options</td></tr>
		<tr>
			<td>
				<table border=0 cellpadding=2 cellspacing=2 width=100%>
				<!--tr><td nowrap><a href="javascript: purchaseOptions(); void(0);" title="Clicking here will allow you to purchase additional licenses for Requisitioners and/or Buyers."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "purchaseAdditionalInd", "Purchase Additional Requisitioners/Buyers")%></a></td></tr-->
				<tr><td nowrap><a href="javascript: doSubmit('/admin/company_information.jsp', 'DoNothing'); void(0);" title="Clicking here will allow you to change your Company Name and Address Information."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "companyNameAddress", "Company Name & Address")%></a></td></tr>
				<tr><td nowrap><a href="javascript: doSubmit('/admin/user/user_billing_info.jsp', 'DoNothing'); void(0);" title="Clicking here will allow you to change your Billing Information."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "billingInformation", "Billing Information")%></a></td></tr>
				<tr><td nowrap><a href="javascript: doSubmit('/admin/user/user_change_admin.jsp', 'DoNothing'); void(0);" title="Clicking here will allow you to change your Billing Information."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "changeAdministrator", "Change Administrator")%></a></td></tr>
<%	if (orgStatus.equals("01")) {%>
				<tr><td nowrap><a href="javascript: cancelTrial(); void(0);" title="Clicking here will cancel the remainder of your trial."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cancelTrial", "Cancel Trial")%></a></td></tr>
<%	} else if (orgStatus.equals("02")) {%>
				<tr><td nowrap><a href="javascript: cancelRenewal(); void(0);" title="Clicking here will cancel your subscription renewal."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cancelRenewal", "Cancel Renewal")%></a></td></tr>
<%	}%>
				</table>
			</td>
		</tr>
		</table>
	</div>
</div>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=70%>
		<tr>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: packageUpgrade(); void(0);">Upgrade Now</a></div></td>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
		</tr>

		</table>
	</td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<%	if (orgStatus.equals("02")) {
		if (daysToRenewal.compareTo(new BigDecimal(0)) > 0) {
			renewalMsg = "Your current Puridiom Xpress " + currentGroupPackage + " subscription is set to renew on " + HiltonUtility.getFormattedDate(renewalDate, oid, "MMM d, yyyy") + " at US $" + renewalCost + "/year.";
		} else if (renewalDate.equals(new Date())) {
			renewalMsg = "Your current Puridiom Xpress " + currentGroupPackage + " subscription is set to renew TODAY at US $" + renewalCost + "/year.";
		}
	} else {
		if (daysToRenewal.compareTo(new BigDecimal(0)) > 0) {
			if (defaultPackage != null) {
				renewalMsg = "On " + HiltonUtility.getFormattedDate(renewalDate, oid, "MMM d, yyyy") + " your free trial will automatically be converted to the " + defaultPackage.getPackageName() + " subscription.  " +
					"<br>The credit card you provided during the registration process will be charged US " + HiltonUtility.getCurrency(defaultPackage.getPackagePrice(), "", oid) + " at this time.";
			} else {
				renewalMsg = "Your FREE Trial will expire on " + HiltonUtility.getFormattedDate(renewalDate, oid, "MMM d, yyyy") + ".";
			}
		} else if (renewalDate.equals(new Date())) {
			if (defaultPackage != null) {
				renewalMsg = "Your free trial will automatically be converted to the " + defaultPackage.getPackageName() + " TODAY." +
					"<br>The credit card you provided during the registration process will be charged US " + HiltonUtility.getCurrency(defaultPackage.getPackagePrice(), "", oid) + ".";
			} else {
				renewalMsg = "Your FREE Trial expires today!";
			}
		}
	}
%>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var menuText = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "myaccount", "My Account", false)%>";

	setNavCookie("/admin/user/user_account_info.jsp", "DoNothing", menuText);

	setInnerHTML("renewalMsg", "<%=renewalMsg%>");
	setInnerHTML("proratedMsg", "<%=proratedMsg%>");
	setInnerHTML("packageOptionLabel", "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "packageoption", "Package Option")%>");

	function packageUpgrade() {
	alert("Please contact support@puridiom.com to upgrade your current subscription.");
	return;
		var packageSelection = getCheckedRadioButtonValue("packageSelection");

		if (packageSelection != undefined && !isEmpty(packageSelection) && packageSelection >= 0) {
			frm.icPackage.value = frm.as_icPackage[packageSelection].value;
			frm.packageName.value = frm.as_packageName[packageSelection].value;
			frm.packagePrice.value = "$" + frm.as_packagePrice[packageSelection].value;

			if (frm.icPackage.value == "0") {
				purchaseOptions();
				return;
			}

			if (confirm("Your credit card will be billed for " + frm.packagePrice.value + ".  Continue?")) {
				doSubmit('/menu/main_menu.jsp', 'OrganizationPackageUpgrade');
			}
		} else {
			alert("You must first select a package.");
		}
	}

	function purchaseOptions() {
		var newInputField = "<input type='hidden' name='PackagePricing_packageType' value='I'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/user/purchase_options.jsp', 'PackagePricingRetrieveBy');
	}

	function cancelTrial() {
		if (confirm('Are you sure you want to cancel the remainder of your FREE Trial?')) {
			doSubmit('http://www.puridiom.com', 'UserCancelSubscription;UserLogoff');
		}
	}

	function cancelRenewal() {
		if (confirm('Are you sure you want to cancel your subscription renewal?')) {
			doSubmit('http://www.puridiom.com', 'UserCancelSubscription;UserLogoff');
		}
	}

// End Hide script -->
</SCRIPT>
