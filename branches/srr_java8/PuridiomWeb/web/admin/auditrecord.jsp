<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.AuditRecord" %>
<%@ page import="java.math.BigDecimal" %>

<%
	AuditRecord	auditRecord = (AuditRecord) request.getAttribute("auditRecord");
	BigDecimal	ic = auditRecord.getIc();
%>

<tsa:hidden name="AuditRecord_ic" value="<%=ic%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12>Audit Record</div></td></tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=0 width=640px>
<tr>
	<td width=25px>&nbsp;</td>
	<td width=600px align=center valign=top>
		<table border=0 cellspacing=0 cellpadding=2 width=600px>
		<tr>
			<td><%=auditRecord.getEntity()%></td>
		</tr>
		</table>
	</td>
	<td width=5px>&nbsp;</td>
</tr>
</table>

<table width=100% border=0>
<tr>
	<td align=center><br><div id="pxbutton"><a href="javascript: void(0); window.top.hidePopWin();">Close</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var browser = browserCheck();

// End Hide script -->
</SCRIPT>


