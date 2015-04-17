<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.ValidationRule" %>
<%@ page import="com.tsagate.foundation.rule.ValidationRules" %>
<%@ page import="java.math.BigDecimal" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<%
	ValidationRules rules = (ValidationRules) request.getAttribute("rules");
	String fromSave =  HiltonUtility.ckNull((String) request.getAttribute("fromSave"));
	String userId = HiltonUtility.ckNull((String) request.getAttribute("User_userId"));

	String	s_ivc_ic_header = HiltonUtility.ckNull((String) request.getAttribute("InvoiceHeader_icIvcHeader"));
	String	s_ivc_number = HiltonUtility.ckNull((String) request.getAttribute("InvoiceHeader_invoiceNumber"));
	String	s_ivc_status = HiltonUtility.ckNull((String) request.getAttribute("InvoiceHeader_status"));
	String	s_line_ic = (String) request.getAttribute("originalAccount_icLine");
	String	s_line_number = (String) request.getAttribute("lineNumber");
	String override = HiltonUtility.ckNull((String) request.getAttribute("override"));
%>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dynamicTables.js"></SCRIPT>

<tsa:hidden name="User_validated" value=""/>
<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=s_ivc_number%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=s_ivc_status%>"/>
<tsa:hidden name="ApprovalLog_icHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="formType" value="IVL"/>
<tsa:hidden name="originalAccount_icLine" value="<%=s_line_ic%>"/>
<tsa:hidden name="lineNumber" value="<%=s_line_number%>"/>
<tsa:hidden name="override" value="<%=override%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Accounts Validation Results</div>
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

<br>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
  <td width="100%" align="center" valign="top">
    <%@ include file="/base/validation_rules.jsp" %>
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
				<td valign=middle class="basic"><b>The Accounts information have passed validation.</b></td>
			 </tr>
			</table>

		</div>

<%	}%>


	<div id="pxbutton"><a tabindex=50 href="javascript: returnTo(); void(0);">Return</a></div>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

<%	if (rules.getResult() == 1) { %>
	displayArea('novalidationrules');
	<%	if (fromSave.equalsIgnoreCase("Y")) { %>
	doSubmit('/invoice/iv_approval.jsp', 'InvoiceApprovalRetrieve');
	<%	} %>
<%	} else { %>
	displayArea('validationrules');
<%	} %>

	function returnTo()
	{
		doSubmit("/invoice/iv_accounts_ln_approvals.jsp", "InvoiceLineRetrieveAccountApprovals");
	}

// end hiding contents -->
</SCRIPT>