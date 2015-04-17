<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></SCRIPT>

<form name="purchaseform" target="_parent" action="/procure" method="POST">

<tsa:hidden name="requisitionType" value=""/>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="478808700000"/>

<br>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Browse Requisition Options</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
		<table border=0 cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td width="100px">&nbsp;</td>
			<td align="right">&nbsp;<input type=radio name="createTool" value="CLASSIC" CHECKED></td>
			<td width="100px" nowrap><b>Classic View</b></td>
			<td align="right"><input type=radio name="createTool" value="CLASSIC"></td>
			<td nowrap><b>Wizard View</b></td>
			<td width="10px">&nbsp;</td>
		</tr>
		</table>
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
			<IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="30%" class=formType>
			<A HREF="javascript: doSubmit('requests/req_summary.jsp','RequisitionRetrieve');" class=formType>Requisition Summary</A></TD>
		<TD>To move items from one inventory location to another. </TD>
	</TR>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="10%">
			<IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="30%" class=formType>
			<A HREF="javascript: doSubmit('inventory/inv_menu.jsp','DoNothing');" class=formType>Item Inventory Browse</A></TD>
		<TD>Request to return items to an inventory location.</TD>
	</TR>

	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="10%">
			<IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="30%" class=formType>
			<A HREF="javascript: doSubmit('catalog/catalogs.jsp','CatalogRetrieve');" class=formType>Catalog Management</A></TD>
		<TD>Request to return items to a supplier.</TD>
	</TR>

	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="10%">
			<IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="30%" class=formType>
			<A HREF="javascript: createReq('C'); void(0);" class=formType>Change Request</A></TD>
		<TD>Request to change an existing requisition.</TD>
	</TR>

	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="10%">
			<IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="30%" class=formType>
			<A HREF="javascript: createReq('E'); void(0);" class=formType>Release Request</A></TD>
		<TD>Request for a release against a Blanket Order.</TD>
	</TR>

	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="10%">
			<IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="30%" class=formType>
			<A HREF="javascript: createReq('N'); void(0);" class=formType>Pricing Requisition</A></TD>
		<TD>Request for prices of items from Purchasing or request an RFQ from Purchasing to suppliers for prices. </TD>
	</TR>
	</TABLE>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function createReq() {
//		frm.requisitionType.value = type;
		frm.RequisitionHeader_icRfqHeader.value = '000123';
//		unavailable();
		doSubmit('requests/req_supplier.jsp','RequisitionHeaderRetrieveById');
	}

// End Hide script -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>