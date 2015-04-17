<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<br></br>	


<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Electronic Invoice Presentation & Payment</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
		<table cellpadding="0" cellspacing="0" border=0>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" WIDTH="650" border="0" ALIGN="CENTER">
		<TR>
			<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="10%">
				<IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;
			</TD>
			<TD NOWRAP WIDTH="30%" class=formType>
				<a HREF="javascript: doSubmit('/invoice/invoice.jsp', 'DoNothing');" class=formType>Submit an Invoice</A>
			</TD>
		</TR>
		<TR>
			<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="10%">
				<IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;
			</TD>
			<TD NOWRAP WIDTH="30%" class=formType>
				<a HREF="javascript: doSubmit('/invoice/invoice_menu.jsp', 'DoNothing');" class=formType>Review Submitted Invoices</A>
			</TD>
		</TR>
		</TABLE>
	</td>
</tr>
</table>	
	
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	
// End Hide script -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>