<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page language="java" errorPage="/system/error.jsp"%>
<%@ include file="/system/context_path.jsp"%>
<%@ include file="/system/header.jsp"%>
<%@ include file="/system/prevent_caching.jsp"%>

<style type="text/css">
<!--
#contact-title {
	width: 630px;
	border: 1px solid black;
	background-color:#eeeef8;
	font-weight:
	bold; font-size: 1.4em;
	margin-left: 20px;
	padding: 7px 5px 7px 5px;
}
#steps ul li {
	font-size: 1.2em;
	width: 630px;
}
table#form {
	width: 640px;
	border: 1px solid #003399;
	margin-left: 20px;
}
table#form th  {background-color: #eeeef8; font-size: 1.1em;}
table#form td {background-color: #f5f5f5}
table#form td label {font-weight: bold; font-size: 1.1em;}
-->
</style>


<%
	uid = (String) request.getSession().getAttribute("ss_uid");
	oid = (String) request.getSession().getAttribute("ss_oid");
	System.out.println(">> " + uid + " - " + oid);
%>

<br><br>
<div id="contact-title">My Messages: Contact to Puridiom Support</div>
<div id="steps">
<ul>
	<li><strong>Step 1: </strong>Confirm that your email is about &quot;<strong>Page Not Found (Puridiom)</strong>&quot;.</li>
	<!-- li><strong>Step 2: </strong>If the &quot;Subject&quot; is incorrect, please <a href="http://pages.ebay.com/help/contact_us/_base/index.html">select a new subject</a> to ensure prompt and accurate processing.</li-->
	<li><strong>Step 2: </strong>After completing all fields in the form, press the <strong>Send</strong> button.</li>
</ul>
</div>

<table border="0" cellpadding="5" cellspacing="0" id="form">
	<tr>
		<th width="10%" scope="col" align="right">To:</th>
		<th width="90%" scope="col" align="left">Puridiom Support</th>
	</tr>
	<tr>
		<th align="right">Subject:</th>
		<th align="left">Page Not Found (Puridiom)<tsa:hidden name="error_subject" value="Page Not Found (Puridiom)"/></th>
	</tr>
	<tr>
		<td colspan="2"><label for="error_emailFrom">Enter your email address: </label><br />
		<input type="text" name="error_emailFrom" id="error_emailFrom" size="30" /></td>
	</tr>

	<tr>
		<td colspan="2"><label for="error_emailFrom2">Re-enter your email address:</label><br />
		<input type="text" name="error_emailFrom2" id="error_emailFrom2" size="30" /></td>
	</tr>

	<tr>
		<td colspan="2"><label for="error_message">Enter error message:</label><br />
		<textarea wrap="SOFT" rows="7" name="error_message" cols="80" id="error_message"></textarea></td>
	</tr>

	<tr>
		<td colspan="2"><label for="error_url">Enter URL: </label><br />
		<input type="text" name="error_url" id="error_url" size="70" /></td>
	</tr>

	<tr>
		<td colspan="2"><label for="error_question">Enter your question / concern: </label><br />
		<textarea wrap="soft" rows="7" name="error_question" cols="80" id="error_question"></textarea></td>
	</tr>
</table>

<tsa:hidden name="oid" value="<%= oid%>"/>
<tsa:hidden name="uid" value="${userId}"/>


<br><br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center id="buttons"><a href="javascript: submitErrorReport(); void(0);"><img class=button src="<%=contextPath%>/images/button_submit.gif" border=0 alt="Send"></a></td>
</tr>
</table>
<%@ include file="/system/footer.jsp"%>
<SCRIPT value=JavaScript>
<!-- Hide script
	frm = document.purchaseform;
	hideArea("navTable");

	function submitErrorReport() {
		if(checkEmail()) {
			doSubmit('/', 'ErrorReport');
		}
		else
		{
			alert('Please, be sure yor email address is correct');
		}
	}

	function checkEmail() {
		var email = frm.error_emailFrom.value;
		var email2 = frm.error_emailFrom2.value;

		if(email == email2)	return true;
		else return false;
	}
// -->
</SCRIPT>
