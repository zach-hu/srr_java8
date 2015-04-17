<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.ValidationRule" %>
<%@ page import="com.tsagate.foundation.rule.ValidationRules" %>
<%@ page import="java.math.BigDecimal" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<%
	ValidationRules rules = (ValidationRules) request.getAttribute("rules");
	String fromSave =  HiltonUtility.ckNull((String) request.getAttribute("fromSave"));
	System.out.println(rules.getResult());
%>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dynamicTables.js"></SCRIPT>

<tsa:hidden name="User_validated" value=""/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<% if (user.isQualified()) { %>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Profile Validation Results</div>
				<% } else { %>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Pre-Qualification Validation Results</div>
				<% } %>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
  <td width="100%" align="center" valign="top">
    <%@ include file="/supplierportal/base/validation-rules.jsp" %>
  </td>
</tr>
</table>

<br>
<br>

<table width=655px cellpadding=0 cellspacing=0 border=0 valign=bottom>
<tr>
	<td align=center>

<%	if (rules.getResult() == 1) { %>
		<div id="novalidationrules" style="display:none;">
			<table align=center>
			<tr>
				<td valign=middle><img src="<%=contextPath%>/images/alert.gif" valign=middle border=0></td>
				<% if (user.isQualified()) { %>
				<td valign=middle class="basic"><b>The Profile information has passed validation.</b></td>
				<% } else { %>
				<td valign=middle class="basic"><b>The Pre-Qualification information has passed validation.</b></td>
				<% } %>
			 </tr>
			</table>

		</div>

<%	}%>

	<div id="forward_link"><a href="javascript: returnToPreQualificationInformation(); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_return.gif" border=0></a></div>
	</td>
</tr>
</table>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

<%	if (rules.getResult() == 1) { %>
	displayArea('novalidationrules');
	<%	if (fromSave.equalsIgnoreCase("Y")) { %>
					doSubmit('user/prequalification_complete.jsp', 'DoNothing');
	<%	} %>
<%	} else { %>
	displayArea('validationrules');
<%	} %>

	function returnToPreQualificationInformation() {

		doSubmit('/user/prequalification.jsp', 'VendorRegisterRetrieveByEmail;VendorOptionsRetrieve');
	}

// end hiding contents -->
</SCRIPT>