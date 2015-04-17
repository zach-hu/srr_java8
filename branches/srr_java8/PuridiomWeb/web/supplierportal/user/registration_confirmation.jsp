<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.organization.OrganizationManager" %>
<%@ page import="com.tsa.puridiom.common.documents.StdDocumentType" %>
<%
	String	errorMsg = (String) request.getAttribute("errorMsg");
	String	password = (String) request.getAttribute("confirm_password");
	String	organizationName = OrganizationManager.getInstance().getOrganization(oid).getOrganizationName();
	String	supplierType = "registered";
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	boolean termsRequired = propertiesManager.getProperty("BB-APPLICATION", "TERMSACCEPTANCEREQUIRED", "N").equalsIgnoreCase("Y");

	if (user.isQualified()) {
		supplierType = "qualified";
	}
	if (HiltonUtility.isEmpty(userId)) {
		userId = (String) request.getAttribute("RegisterUser_emailAddress");
	}
%>

<tsa:hidden name="oid" value="<%=oid%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td vAlign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 vAlign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Self Registration</div>
			</td>
		</tr>
		</table>
	</td>
	<td width=30px vAlign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" width="30" height="31" /></td>
	<td vAlign=bottom align=right height=30px>
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

<br><br>
<%	if (termsRequired) { %>
<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=100% valign=top align=center>
		<table border=0 cellSpacing=0 cellPadding=0 width=80%>
		<tr><td align=center><b>Welcome to the <%=organizationName%> Supplier Portal!</td></tr>
		<tr><td><br></td></tr>
		<tr><td align=center>Your registration information has been submitted.&nbsp; You are now a <%=supplierType%> supplier.</td></tr>
		<tr><td><br></td></tr>
		<tr><td><br></td></tr>
		<tr>
			<td align=center>
				<table width=90%>
				<tr>
					<td>In order to use this website you must read and agree to the Terms & Conditions
						set forth by <%=organizationName%>.  You will then be able to search solicitations, submit bids, search orders, acknowledge orders, submit invoices,
						and update your profile information.<br>
					</td>
				</tr>
				</table>
			</td></tr>
		<tr><td><br><br></td></tr>
		<tr>
			<td align=center>

				<table border=0 cellpadding=0 cellspacing=0 width=680px>
				<tr>
					<td valign=top align=center>
					<%@ include file="/supplierportal/documents/std_documents_list.jsp" %>
					</td>
				</tr>
				</table>

				<br><br><br>

				<table border=0 cellspacing=0 cellpadding=2>
				<tr>
					<td><input type=checkbox name="termsAccepted" value="Y" tabIndex=10></td>
					<td>I agree to the Terms & Conditions as listed above.<font color="#0000ff">*</font></td>
				</tr>
				</table>
				<tsa:hidden name="password" value="<%=password%>"/>
			</td>
		</tr>
		<tr><td><br><br></td></tr>
		</table>
	</td>
</tr>
</table>
<%	} else {%>
<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=100% valign=top align=center>
		<table border=0 cellSpacing=0 cellPadding=0 width=80%>
		<tr><td align=center><b>Welcome to the Solicitation System!</td></tr>
		<tr><td><br></td></tr>
		<tr><td align=center>Your registration information has been submitted.&nbsp;</td></tr>
		<tr><td align=center>You are now a <%=supplierType%> supplier. You can search solicitations, submit bids, view standard terms and conditions, and change your profile information.<br>&nbsp;</td></tr>
		<tr><td><br><br></td></tr>
		<tr>
			<td align=center>
				Click the continue button below to continue to the menu page.
				<tsa:hidden name="password" value="<%=password%>"/>
			</td>
		</tr>
		<tr><td><br><br></td></tr>
		</table>
	</td>
</tr>
</table>
<%	} %>
<br>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td align=center>
		<a href="javascript: doSubmit('menu/main_menu.jsp', 'AcceptTerms;VendorLogin');"><img class=button src="<%=contextPath%>/supplierportal/images/button_continue.gif" border=0 tabindex=26 alt=Continue to the Menu Page></a>
	</td>
</tr>
</table>

<br>

<%@ include file="/supplierportal/system/footer.jsp" %>

</FORM>

</BODY>
</HTML>
<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var termsRequired = <%=termsRequired%>;

	frm.userId.value = "<%=userId%>";

	function cancel() {
		doSubmit('index.jsp', 'DoNothing');
	}

	function validateForm() {
		if (termsRequired && !frm.termsAccepted.checked) {
			alert('You must review and agree to the Terms of Service before continuing.');
			return false;
		} else {
			return true;
		}
	}
//-->
</SCRIPT>