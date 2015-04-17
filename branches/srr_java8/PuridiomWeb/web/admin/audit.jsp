<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%String host = request.getRemoteHost(); %>
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
						<div style="margin-left:10px; margin-right:10px" class=hdr12>Audit Log</div>
					</td>
				</tr>
				</table>
			</td>
			<td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
			<td valign="bottom" align="right" height="30px" width="100%">
				<table cellpadding="0" cellspacing="0" border=0 width=100%>
				<tr>
					<td width="100%" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
				</tr>
				<tr>
					<td height="2px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td width="100%">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr class="mnav">
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-userId", "User ID")%></td>
			<td width="1px" class="mnav">&nbsp;</td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ipAddress", "Ip Address")%></td>
			<td width="1px" class="mnav">&nbsp;</td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "date", "Date")%></td>
			<td width="1px" class="mnav">&nbsp;</td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "select", "Select")%></td>
		</tr>
	</td>
</tr>
<%@ include file="/admin/audittext.jsp" %>
</table>

<%@ include file="/system/footer.jsp"%>
