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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Consolidate Locations</div>
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
					<TABLE CELLPADDING=2 CELLSPACING=0 BORDER=0 WIDTH=500px>
						<TR>
							<TD align='left' nowrap width=15%>Locations:<br><i>Hold CTRL to click multiple locations.</i></TD>
							<TD width=15%>
<%
	int size = 0;
	List locList = (List) request.getAttribute("invAddressList");
	if (locList != null)
	{
		size = locList.size();
	}
%>
								<SELECT NAME="originalLocation" SIZE=<%=size%> multiple="multiple">
<%
	for (int i = 0; i < locList.size(); i++)
	{
		Address invLocation = (Address) locList.get(i);
		AddressPK invLocationPK = (AddressPK) invLocation.getComp_id();
		String locationName = invLocationPK.getAddressCode();
%>
									<OPTION value="<%=locationName%>"><%=locationName%></OPTION>
<%	} %>
								</SELECT>
							</TD>
						</TR>
						<TR>
							<TD align='left' width=15% nowrap>New Consolidated Location:</TD>
							<TD width=15%><INPUT TYPE="TEXT" NAME="InvLocation_itemLocation" SIZE=15 MAXLENGTH=30 value="" ONCHANGE="upperCase(this);"></TD>
						</TR>
						</TABLE>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center>
		<a href="javascript: submitThis(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" tabindex=17 border=0 alt="Save"></a>
	</td>
	<td width=50% align=center>
		<a href="javascript: doSubmit('inventory/inv_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" tabindex=17 border=0 alt="Cancel"></a>
	</td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	setNavCookie("/inventory/consolidate_locations.jsp", "AddressRetrieveAllInventory", "Consolidate Locations");
	frm = document.purchaseform;

	function submitThis()
	{
		if (frm.InvLocation_itemLocation.value.length <= 0)
		{
			alert("You must enter a New Location Name!");
		}
		else
		{
			unavailable();
			//doSubmit('inventory/inv_menu.jsp', 'DoNothing');
		}
	}

// End Hide script -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>
