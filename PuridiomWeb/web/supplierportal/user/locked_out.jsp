<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.Organization" %>
<%@ page import="com.tsa.puridiom.organization.OrganizationManager" %>
<%
	String errorName = "System Administrator";
	String errorMsg = (String) request.getAttribute("errorMsg");
	String returnPage = (String) request.getAttribute("returnPage");
	if (errorMsg == null) {
		errorMsg = "";
	}
	if (HiltonUtility.isEmpty(returnPage)) {
		returnPage = "index.jsp";
	}
	List organizationList = OrganizationManager.getInstance().getOrganizationList();
	if (organizationList == null) {
		organizationList = new ArrayList();
	}
	if (organizationList.size() <= 0) {
		Organization organization = new Organization();
		organization.setOrganizationId("HILTON");
		organization.setOrganizationName("Hilton");
		organizationList.add(organization);
	}
	if (HiltonUtility.isEmpty(oid)) {
		oid = HiltonUtility.ckNull((String) request.getAttribute("oid"));
	}

	for (int i = 0; i < organizationList.size(); i++) {
		Organization organization = (Organization) organizationList.get(i);
		if (oid.equals(organization.getOrganizationId()))
			errorName = organization.getOrganizationName();
	}
	String	message = "Please contact your " + errorName + " representative for assisstance.";
%>

<tsa:hidden name="loginPage" value="index.jsp"/>
<tsa:hidden name="returnPage" value="<%=returnPage%>"/>
<tsa:hidden name="resetFailurePage" value="user/locked_out.jsp"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Account Locked</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr>
			<td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=2 width=680px>
<tr>
	<td colspan=2 align=center class="error"><%=errorMsg%></td>
</tr>
<tr>
	<td colspan=2 align=center class="error"><%=message%></td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td align=center colspan=2>
		<table border=0 width="600">
		<tr>
			<td width="50%" align=center><a href="javascript: doSubmit('<%=returnPage%>', 'DoNothing'); void(0);" tabindex="4"><img class=button src="<%=contextPath%>/supplierportal/images/button_return.gif" border=0></a></td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function validateForm() {
		return true;
	}

//-->
</SCRIPT>