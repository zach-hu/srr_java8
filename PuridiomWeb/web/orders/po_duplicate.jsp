<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%
	String poNumber = (String)request.getAttribute("errorMessagePoNumber");
	String reqNumber = (String)request.getAttribute("errorMessageReqNumber");
%>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
<tr><td><br></td></tr>
<tr>
	<td width="100%">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td valign="top" width="50px" height="30px">
				<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
				<tr>
					<td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
				</tr>
				<tr>
					<td nowrap class="hdr12" valign="middle">
						<div style="margin-left:10px; margin-right:10px" class="hdr12">Duplicate Purchase Order Assignment</div>
					</td>
				</tr>
				</table>
			</td>
			<td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
			<td valign="bottom" align="right" height="30px" width="100%">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td width="100%" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
				</tr>
				<tr>
					<td height="2px" class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td align="center">
		<font class="formType">Requisition </font><font class="hdr12">#<%=reqNumber%></font>
		<font class="formType">has been already placed on Order </font><font class="hdr12">#<%=poNumber%></font>
	</td>
</tr>
<tr><td><br></td></tr>
</table>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
	<tr>
		<td align="center">
			<a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);">
				<img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Order">
			</a>
		</td>
	</tr>
</table>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers
	frm = document.purchaseform;

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/footer.jsp" %>