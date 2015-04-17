<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<script language="JavaScript1.2" src="<%=contextPath%>/scripts/calendar.js"></script>
<script language="JavaScript1.2" src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	Catalog catalog = (Catalog) request.getAttribute("catalog");
	List itemList = (List) request.getAttribute("catalogItemList");
	boolean newCatalog = false;
	boolean hasItems = false;

	if (catalog == null)
	{
		catalog = new Catalog();
		catalog.setDateEntered(d_today);
		catalog.setDateExpires(d_today);
		catalog.setOwner(uid);
		catalog.setStatus("02");
		newCatalog = true;
	}

	if (itemList != null)
	{
		if (itemList.size() > 0)
		{
			hasItems = true;
		}
	}
%>

<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="Catalog_source" value="<%=catalog.getSource()%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "catalogInformation", "Catalog Information")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
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
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td align=center>
				<table border=0 cellspacing=0 cellpadding=1>
				<tr>
					<td nowrap align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "catalogID", "Catalog ID")%>:</td>
					<td>&nbsp;<%=catalog.getCatalogId()%><tsa:hidden name="Catalog_catalogId" value="<%=catalog.getCatalogId()%>"/></td>
				</tr>
				<tr>
					<td nowrap align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "catalogTitle", "Catalog Title")%>:</td>
				  <td>&nbsp;<%=catalog.getTitle()%></td>
				</tr>
				<tr><td colspan=2><br></td></tr>
				<tr>
					<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fileToImport", "File to Import")%>:</td>
				  <td><input type=file name=file3 size=40></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center>
		<a href="javascript: importCatalogItems(); void(0);">
		<img class=button src="<%=contextPath%>/images/button_import.gif" border=0 alt="Import"></a>
	</td>
	<td width=50% align=center><a href="javascript: cancelMe(); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 alt="Cancel"></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.browseName.value = 'catalog';

	function importCatalogItems() {
		doSubmit('/admin/catalog/catalog.jsp', 'CatalogRetrieveById;CatalogItemRetrieveCountByCatalog');
	}

	function cancelMe() {
		doSubmit('/admin/catalog/catalog.jsp', 'CatalogRetrieveById;CatalogItemRetrieveCountByCatalog');
	}

// End Hide script -->
</SCRIPT>