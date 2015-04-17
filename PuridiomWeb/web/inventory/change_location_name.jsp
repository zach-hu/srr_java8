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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Change Location Name</div>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=right nowrap>Current Location Name:&nbsp;</td>
	<td width=50%>
		<SELECT NAME="oldItemLocation">
<%
	List locList = (List) request.getAttribute("invAddressList");
	if (locList != null)
	{
		for (int i = 0;i < locList.size(); i++)
		{
			Address invLocation = (Address) locList.get(i);
			AddressPK invLocationPK = (AddressPK) invLocation.getComp_id();
			String locationName = invLocationPK.getAddressCode();
			
%>
				<OPTION value="<%=locationName%>"><%=locationName%></OPTION>
<%		}
	} %>
		</SELECT>
	</td>
</tr>
<tr>
	<td align=right width=50% nowrap>New Location Name:&nbsp;</td>
	<td width=50%><INPUT TYPE="TEXT" NAME="newItemLocation" SIZE=15 MAXLENGTH=30 value="" ONCHANGE="upperCase(this);"></td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center>
		<a href="javascript: submitThis(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" tabindex=17 border=0 alt="Save"></a>
	</td>
	<td width=50% align=center>
		<a href="javascript: doSubmit('/inventory/inv_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" tabindex=17 border=0 alt="Cancel"></a>
	</td>
</tr>
</table>

<br>
 
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	
	function submitThis()
	{
		if (frm.newItemLocation.value.length <= 0)
		{
			alert("You must enter a New Location Name!");
		}
		else
		{
			doSubmit('/inventory/inv_menu.jsp', 'InvLocationChangeName'); 
		}
	}


// End Hide script -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>
