<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<script language='Javascript1.2' src="/scripts/browse.js"></SCRIPT>

<tsa:hidden name="DisbHeader_disbType" value=""/>
<tsa:hidden name="formtype" value="DSB"/>
<tsa:hidden name="invbrowse" value="TRUE"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Disbursement Type Selection</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
		<table border=0 cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td width="100px">&nbsp;</td>
			<td align="right">&nbsp;<input type=radio name="as_view_type" value="CLASSIC"></td>
			<td width="100px" nowrap><b>Classic View</b></td>
			<td align="right"><input type=radio name="as_view_type" value="WIZARD" CHECKED></td>
			<td nowrap><b>Wizard View</b></td>
			<td width="10px">&nbsp;</td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
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
			<IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="30%" class=formType>
			<A HREF="javascript: createDsb('S'); void(0);" class=formType>From Supply Request</A></TD>
		<TD>Create Draw Down from Inventory Request items.</TD>
	</TR>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="10%">
			<IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="30%" class=formType>
			<A HREF="javascript: createDsb('T'); void(0);" class=formType>Transfer between Locations</A></TD>
		<TD>To move items from one inventory location to another. </TD>
	</TR>
	<%	if ( !oid.equalsIgnoreCase("vse06p") ) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="10%">
			<IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="30%" class=formType>
			<A HREF="javascript: createDsb('O'); void(0);" class=formType>Over-the-Counter</A></TD>
		<TD>Create an over-the-counter disbursement. </TD>
	</TR>
	<% } %>
	</TABLE>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	setNavCookie("/inventory/dsb_select_type.jsp", "DoNothing", "Create New Disbursement", true);

	frm = document.purchaseform;

	function createDsb(type)
	{
		setViewType();
		if (type == 'S')
		{
			browse('dsb_supply_reqbrowse');
		}
		else if (type == 'T')
		{
			browse('dsb_transfer_reqbrowse');
		}
		else if (type == 'O')
		{
			frm.DisbHeader_disbType.value = type;
			//doSubmit('browse/item_filter_options.jsp', 'OtcHeaderCreate;InvCatalogRetrieve');
			doSubmit('inventory/dsb_items.jsp', 'OtcHeaderCreate;DisbLineRetrieveByHeader');
		}
	}

	function setViewType()
	{
		var viewType = "";
		var types = frm.elements.item("as_view_type");

		for (var i = 0; i < types.length; i++)
		{
			if (frm.as_view_type[i].checked) {
				viewType = frm.as_view_type[i].value
			}
		}

		if (viewType == "CLASSIC")
		{
			frm.viewType.value = "CLASSIC";
		}
		else if (viewType == "WIZARD")
		{
			frm.viewType.value = "WIZARD";
		}
	}

// End Hide script -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>