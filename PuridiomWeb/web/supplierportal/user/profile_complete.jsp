<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td vAlign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width=1px height=1px /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 vAlign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Profile Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td width=30px vAlign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" width=30px height=31px /></td>
	<td vAlign=bottom align=right height=30px>
		<table cellpadding=0 cellspacing=0 border=0>
		<tr>
			<td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width=1px height=1px /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width=1px height=2px /></td>
		</tr>
		</table>
	</td>
</tr>
</table>


<br>

<table border=0 width=680px cellspacing=0 cellpadding=0>
<tr>
	<td align=center>
		Thank you.  The profile information you provided has
		   been updated.  You may update this information again at	
		   any time.  To return to the Supplier Options Menu, click 
		   the "Continue" button.
	</td>
</tr>
<tr><td><br><br></td></tr>
<tr><td align=center><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_continue.gif" border=0 alt=Continue></a></td></tr>
</table>

<%@ include file="/supplierportal/system/footer.jsp" %>
<script value=JavaScript>
<!--Hide Script

	frm = document.purchaseform;

//-->
</script>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>