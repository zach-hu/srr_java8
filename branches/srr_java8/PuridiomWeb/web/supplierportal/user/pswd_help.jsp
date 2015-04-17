<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.Organization" %>
<%@ page import="com.tsa.puridiom.organization.OrganizationManager" %>
<%
	String errorMsg = (String) request.getAttribute("errorMsg");
	String returnPage = (String) request.getAttribute("returnPage");
	if (errorMsg == null) {
		errorMsg = "";
	}
	if (HiltonUtility.isEmpty(returnPage)) {
		returnPage = "index.jsp";
	}
	if (HiltonUtility.isEmpty(userId)) {
		userId = HiltonUtility.ckNull((String) request.getAttribute("uid"));
	}
	if (userId.equalsIgnoreCase("BB-GUEST")) {
		userId = "";
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
%>

<tsa:hidden name="loginPage" value="index.jsp"/>
<tsa:hidden name="returnPage" value="<%=returnPage%>"/>
<tsa:hidden name="resetFailurePage" value="user/pswd_help.jsp"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Password Help - Step 1</div>
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
<tr><td><br></td></tr>
<tr><td colspan=2 align=center nowrap>To reset your password, enter your Login ID below and click on the "Continue" button.</td></tr>
<tr>	<td colspan=2><br></td></tr>
<tr>
<%	if (organizationList.size() > 1) {%>
	<td align=right><B>Organization Id:</B></td>
	<td>
		<select name=oidOptions tabindex=1>
<%		for (int i = 0; i < organizationList.size(); i++) {
				Organization organization = (Organization) organizationList.get(i);%>
			<option value="<%=organization.getOrganizationId()%>" <% if (oid.equals(organization.getOrganizationId())) {%>selected<%}%>><%=organization.getOrganizationName()%></option>
<%		}%>
		<tsa:hidden name="oid" value="<%=oid%>"/></td>
	</td>
<%	} else {
	Organization organization = (Organization) organizationList.get(0);
%>
	<td colspan=2><tsa:hidden name="oid" value="<%=organization.getOrganizationId()%>"/></td>
<%	}%>
</tr>
<tr>
	<td width="50%" align=right><b>Login Id:<b></td>
	<td width="50%"><input type=text name="uid" value="<%=userId%>" tabindex=2 size=35 maxLength=100 onchange="this.value=this.value.toLowerCase();"></td>
</tr>
<tr><td colspan=2><br><br></td></tr>
<tr>
	<td align=center colspan=2>
		<table border=0 width="600">
		<tr>
			<td width="50%" align=center><a href="javascript: doSubmit('user/pswd_help_confirmation.jsp', 'RegisterUserPasswordHelp'); void(0);" tabindex="3"><img class=button src="<%=contextPath%>/supplierportal/images/button_submit.gif" border=0></a></td>
			<td width="50%" align=center><a href="javascript: doSubmit('<%=returnPage%>', 'DoNothing'); void(0);" tabindex="4"><img class=button src="<%=contextPath%>/supplierportal/images/button_cancel.gif" border=0></a></td>
		</tr>
		</table>
	</td>
</tr>
<tr><td colspan=2><br><tsa:hidden name="temp" value=""/></td></tr>
</table>
<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	frm.userId.value = frm.uid.value;

	function validateForm() {
		if (frm.handler.value.indexOf("RegisterUserPasswordHelp") >= 0) {
			if (isEmpty(frm.uid.value)) {
				alert("You must enter your Login Id!");
				frm.uid.focus();
				return false;
			} else {
				frm.userId.value = frm.uid.value;
				if (frm.oidOptions) {
					 frm.oid.value = frm.oidOptions[frm.oidOptions.selectedIndex].value.toUpperCase();
				}
				frm.organizationId.value = frm.oid.value;
			}
		}
		return true;
	}

//-->
</SCRIPT>
