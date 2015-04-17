<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.Utility" %>
<%@ page import="java.text.NumberFormat" %>

<!--SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dates.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dynamicTables.js"></SCRIPT-->
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	errorMsg = (String) request.getAttribute("errorMsg");
	String	renewalCost = "0.00";
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "registrationStep1", "Registration Step 1 of 3 - Package Selection", false)%></div>
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

<%	if (!HiltonUtility.isEmpty(errorMsg)) {%>
<table border=0 cellpadding=2 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=100% align=center class=error><%=errorMsg%></td>
</tr>
</table>
<%	}%>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=10px>&nbsp;</td>
	<td>
    	<h4><b>Introductory Pricing Expires 12/31/2009</b></h4>
		<p>Puridiom Xpress is an easy-to-use and cost-efficient purchasing solution designed to save your company money. Our pricing reflects that model. Pricing is based on the number of users and their functions. You decide on the number of users and their roles.  We have designed several packages to help you use your subscription most effectively. Choose the package that best fits your company's purchasing hierarchy, or you can choose to build your own package, by first registering as an Individual user.</p>
		<p>&nbsp;</p>
		<p><strong>Select your Puridiom Xpress package by 12/31/2009 and you will be guaranteed the same low introductory pricing for two additional annual subscriptions, should you decide to continue the service!</p>
		<p>&nbsp;</p>
	</td>
</tr>
</table>

<div style="width:<%=formEntryWidth%>;float:center">
	<div style="width:100%;valign:top;align:center;">
		<%@ include file="/user/package_options.jsp" %>
	</div>
</div>

<br>

<table border=0 cellpadding=2 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=50% align=center>
		<div id="pxbutton"><a href="javascript: submitSelection(); void(0);">Continue</a></div>
	</td>
	<td width=50% align=center>
		<div id="pxbutton"><a href="javascript: doSubmit('index.jsp', 'DoNothing'); void(0);">Cancel</a></div>
	</td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function submitSelection() {
		var packageSelection = getCheckedRadioButtonValue("packageSelection");
		
		frm.icPackage.value = frm.as_icPackage[packageSelection].value;
		frm.packageName.value = frm.as_packageName[packageSelection].value;
		frm.packagePrice.value = "$" + frm.as_packagePrice[packageSelection].value;
		
		doSubmit('user/xpress_user_registration.jsp', 'DoNothing');
	}


// End Hide script -->
</SCRIPT>
