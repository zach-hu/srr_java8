<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%
	String contextPath = (String) request.getParameter("contextPath");
	String requestURLPath = contextPath + "/user/an_registration_response.jsp";
	String x_email = (String) request.getParameter("x_email");
	String x_cust_id = (String) request.getParameter("x_cust_id");
	String x_last_name = (String) request.getParameter("x_last_name");
	String x_first_name = (String) request.getParameter("x_first_name");
	String x_company = (String) request.getParameter("x_company");	
	String x_address = (String) request.getParameter("x_address");
	String x_city = (String) request.getParameter("x_city");
	String x_state = (String) request.getParameter("x_state");
	String x_country = (String) request.getParameter("x_country");
	String x_zip = (String) request.getParameter("x_zip");
	String x_phone = (String) request.getParameter("x_phone");
	String x_fax = (String) request.getParameter("x_fax");
	String x_response_subcode = (String) request.getParameter("x_response_subcode");
	String x_response_reason_code = (String) request.getParameter("x_response_reason_code");
	String x_test_request = (String) request.getParameter("x_test_request");
	String x_description = (String) request.getParameter("x_description");
	String x_invoice_num = (String) request.getParameter("x_invoice_num"); 
	String x_trans_id = (String) request.getParameter("x_trans_id");
	String x_auth_code = (String) request.getParameter("x_auth_code");
	String x_amount = (String) request.getParameter("x_amount");
	String x_response_code = (String) request.getParameter("x_response_code");
	String packageName = (String) request.getParameter("packageName");
	String packagePrice = (String) request.getParameter("packagePrice");
	String icPackage = (String) request.getParameter("icPackage");
	String icOrgPackage = (String) request.getParameter("OrganizationPackage_icOrgPackage");
	String ccResult = "";
%>
<script language='Javascript1.2' type="text/javascript">
<!--
	var contextPath = "<%=contextPath%>";
//-->
</script>

<%@ include file="/system/header.jsp" %>
<%
	if (HiltonUtility.isEmpty(oid)) {
		oid = HiltonUtility.ckNull(x_cust_id);
	}
	if (HiltonUtility.isEmpty(mid)) {
		mid = HiltonUtility.ckNull(x_email);
	}
	if (HiltonUtility.isEmpty(uid)) {
		uid = HiltonUtility.ckNull((String) request.getParameter("UserProfile_userId"));
	}
	if (HiltonUtility.isEmpty(packageName)) {
		packageName = x_description;
	}
	if (HiltonUtility.isEmpty(packagePrice)) {
		packagePrice = x_amount;
	}
	if (HiltonUtility.isEmpty(x_response_code)) {
		x_response_code = "3";
	}
	if (x_response_code.equals("1")) {
		ccResult = "APPROVED" ;
	} else if (x_response_code.equals("2")) {
		ccResult = "DECLINED" ;
	} else if (x_response_code.equals("3")) {
		ccResult = "ERROR" ;
	} else if (x_response_code.equals("4")) {
		ccResult = "HELD FOR REVIEW" ;
	}
%>

<tsa:hidden name="loginFailurePage" value="index.jsp"/>
<tsa:hidden name="registrationFailurePage" value="/user/an_registration.jsp"/>
<tsa:hidden name="loginId" value="<%=mid%>"/>
<tsa:hidden name="UserProfile_organizationId" value="<%=oid%>"/>
<tsa:hidden name="UserProfile_userId" value="${userId}"/>
<tsa:hidden name="OrganizationPackage_icOrgPackage" value="<%=icOrgPackage%>"/>
<tsa:hidden name="icPackage" value="<%=icPackage%>"/>
<tsa:hidden name="packageName" value="<%=packageName%>"/>
<tsa:hidden name="packagePrice" value="<%=packagePrice%>"/>
<tsa:hidden name="ccResult" value="<%=ccResult%>"/>
<tsa:hidden name="ccMessage" value="<%=x_response_reason_code%>"/>
<tsa:hidden name="ccTranId" value = "<%=x_trans_id%>"/>

<tsa:hidden name="x_email" value = "<%=x_email%>"/>
<tsa:hidden name="x_cust_id" value = "<%=x_cust_id%>"/>
<tsa:hidden name="x_last_name" value = "<%=x_last_name%>"/>
<tsa:hidden name="x_first_name" value = "<%=x_first_name%>"/>
<tsa:hidden name="x_company" value = "<%=x_company%>"/>
<tsa:hidden name="x_address" value = "<%=x_address%>"/>
<tsa:hidden name="x_city" value = "<%=x_city%>"/>
<tsa:hidden name="x_state" value = "<%=x_state%>"/>
<tsa:hidden name="x_country" value = "<%=x_country%>"/>
<tsa:hidden name="x_zip" value = "<%=x_zip%>"/>
<tsa:hidden name="x_phone" value = "<%=x_phone%>"/>
<tsa:hidden name="x_fax" value = "<%=x_fax%>"/>
<tsa:hidden name="x_response_subcode" value = "<%=x_response_subcode%>"/>
<tsa:hidden name="x_response_reason_code" value = "<%=x_response_reason_code%>"/>
<tsa:hidden name="x_test_request" value = "<%=x_test_request%>"/>
<tsa:hidden name="x_description" value = "<%=x_description%>"/>
<tsa:hidden name="x_invoice_num" value = "<%=x_invoice_num%>"/> 
<tsa:hidden name="x_trans_id" value = "<%=x_trans_id%>"/>
<tsa:hidden name="x_auth_code" value = "<%=x_auth_code%>"/>
<tsa:hidden name="x_amount" value = "<%=x_amount%>"/>
<tsa:hidden name="x_response_code" value = "<%=x_response_code%>"/>
<tsa:hidden name="authenticated" value = "false"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Puridiom Xpress Registration</td>
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

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	
	frm.action = "<%=contextPath%>/procure";
	frm.userId.value = "${esapi:encodeForJavaScript(userId)}";
	frm.organizationId.value = "<%=oid%>";
	
	doSubmit('user/xpress_user_registration4.jsp', 'UserFinalizeRegistration');

//-->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>