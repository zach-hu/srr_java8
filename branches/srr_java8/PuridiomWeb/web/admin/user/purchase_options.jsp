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
	String	proratedMsg = "";
	Date renewalDate = null;

	if (oid != null) {
		renewalDate = OrganizationManager.getInstance().getOrganization(oid).getDateExpires();
	}
	if (renewalDate == null) {
		renewalDate = new Date();
	}
	BigDecimal daysToRenewal = HiltonUtility.getDateDifference(new Date(), renewalDate);
	String currentPackage = "";
%>

<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="pageFrom" value="useraccount"/>
<tsa:hidden name="currentPage" value="/admin/user/user_account_info.jsp"/>
<tsa:hidden name="allowEdit" value="Y"/>
<tsa:hidden name="returnPage" value="/admin/user/user_account_info.jsp"/>
<tsa:hidden name="returnHandler" value="PackagePricingRetrieveAll"/>
<tsa:hidden name="fromSave" value="Y"/>

<table width=<%=formWidth%> cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "additionaluseroptions", "Additional User Options", false)%></div>
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
	<td valign=top><div id="proratedMsg"></div></td>
</tr>
</table>

<br>

<div style="width:<%=formWidth%>;align:center;">
	<div style="width:80%;valign:top;float:left">
		<%@ include file="/admin/user/package_options.jsp" %>
	</div>
</div>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: submitPurchase(); void(0);">Purchase</a></div></td>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: viewMyAccount(); void(0);">Cancel</a></div></td>
		</tr>

		</table>
	</td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	setInnerHTML("proratedMsg", "<%=proratedMsg%>");
	setInnerHTML("packageOptionLabel", "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "packageAddOn", "Package Add On")%>");

	function submitPurchase() {

//		doSubmit('/admin/user/user_account_info.jsp', 'OrganizationPackagePurchaseAdditional');
		return;
	}

	function calcuateProratedTotal() {
		popupParameters = "as_type=" + x + ";as_row=" + row + ";as_code=" + code + ";as_code2=" + code2 +
						  ";as_next=" + next + ';as_supcode=' + sup + ';as_fld=' + setFld + ';as_fld2=' + setFld2;

		setLookupParameters('/base/get_info.jsp', "CalulateProratedPackageAddOn");
		displayArea('getInfoFrame');
		document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';

	}

// End Hide script -->
</SCRIPT>
