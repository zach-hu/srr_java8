<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.util.List.*" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></SCRIPT>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Open Inventory Location</div>
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
<br>

<table border=1 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
					<TABLE CELLPADDING=2 CELLSPACING=0 BORDER=0 WIDTH=350px>
						<TR>
							<TD align='left' nowrap width=15%>Inventory Location Name:</TD>
							<TD width=15%><input type="text" NAME="InvLocation_itemLocation" value="">
							</TD>
						</TR>
						</TABLE>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center>
		<a href="javascript: openLocation(); void(0);"><img class=button src="<%=contextPath%>/images/button_search.gif" tabindex=17 border=0 alt="Search"></a>
	</td>
	<td width=50% align=center>
		<a href="javascript: doSubmit('inventory/inv_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" tabindex=17 border=0 alt="Return"></a>
	</td>
</tr>
</table>

<br>
 
<hr size=0 color=black width=680px align=left>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td Valign=bottom align=left class=copyright>
		<br>&nbsp;Copyright &copy; 2000-2003 <a href="http://www.tsagate.com">Technical Services Associates Inc. All rights Reserved</a>
		<br>&nbsp;Release 1.00.00
	</td>
</tr>
</table>

</FORM>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	
	hideArea("resetOriginal");

	function openLocation(){
		doSubmit('/inventory/inv_locations_list.jsp', 'InvLocationRetrieveBy');
	}
// End Hide script -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>
