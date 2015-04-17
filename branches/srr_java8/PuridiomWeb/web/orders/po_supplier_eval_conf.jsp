<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String	poNumberDisplay = (String) request.getAttribute("poNumberDisplay");
%>

<tsa:hidden name="allowBrowse" value="false"/>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tr>
			<td height="1px" class="darkShadow" colspan="2"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class="hdr12">Supplier Performance Evaluation</div>
			</td>
			<td nowrap class="hdr12" valign="middle">&nbsp;</td>
		</tr>
		</table>
	</td>
	<td valign="bottom"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px>
		<table cellpadding="0" cellspacing="0" border="0">
		<tr><td width=1000px height="1px" class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
	    <tr><td height=2px class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<table border="0" cellpadding="2" cellspacing="0" width="680px">
<tr>
	<td align=center height=200px valign=center>
	  	<table border=0 cellpadding=2 cellspacing=0>
	  	<tr><td align=center>Your evaluation has been submitted for <%=poNumberDisplay%>.  Thank you!</td></tr>
	  	</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

// End Hide script -->
</SCRIPT>
