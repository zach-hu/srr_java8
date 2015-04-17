<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page language="java" errorPage="/system/error.jsp"%>
<%@ include file="/system/context_path.jsp"%>
<%@ include file="/system/prevent_caching.jsp"%>

<%--
uid = (String) request.getAttribute("userId");
oid = (String) request.getAttribute("organizationId");
--%>

<br>
<br>
<div style="border:1px solid black; background-color:#eeeef8; width: 630px; padding: 10px 10px 10px 10px; margin: 5px 15px 5px 15px; font-size: 1.1em" >
	<div>
		<img src="<%=contextPath%>/images/error.gif" style="float: left; padding: 5px 5px 5px 5px;"/>
		<p class="error" style="font-size: 1.3em; padding-left: 5px;">This page may have moved or is no longer available</p>
	</div>
	<div style="margin-top: 20px;">
		<p><strong>Please, try one of the following options:
		</strong>
		<ul>
		  <li>Check the Web address you entered to make sure if it's correct. </li>
		  <li>Try to access the page directly from the&nbsp;Puridiom 4.0 <a href="http://www.puridiom.com" target="_blank">Home page</a> instead of using a   bookmark. If the page has moved, reset your bookmark. </li>
		  <li><a href="javascript: reportError(); void(0);">Report this technical issue</a>. </li>
		  <li><a href="<%=contextPath%>/">Log in to the application</a>. </li>
		</ul>
		</p>
	</div>
</div>

<SCRIPT value=JavaScript>
<!-- Hide script
	frm = document.purchaseform;
	hideArea("navTable");

	function reportError() {
		doSubmit('/error_form.jsp', 'DoNothing');
	}
//	-->
</SCRIPT>
