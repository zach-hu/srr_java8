<%@ page language="java" errorPage="/system/error.jsp"%>
<%@ include file="/system/prevent_caching.jsp"%>
<%@ include file="/system/context_path.jsp"%>
<%@ include file="/system/header.jsp"%>
<%@ page import="com.tsa.puridiom.property.PropertiesManager"%>
<%
	String source = "CAT";
	String id = (String) request.getAttribute("AltText_id");
	String itemNumber = (String) request.getAttribute("AltText_itemNumber");
	String icLine = "0";
	List altTextList = HiltonUtility.ckNull((List) request.getAttribute("altTextList"));
	int rowCount = 0;
	if (altTextList!= null){
		rowCount = altTextList.size();
	}

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	boolean bAllowEdit = true;
%>
<tsa:hidden name="catalogId" value="<%=id%>"/>
<tsa:hidden name="itemNumber" value="<%=itemNumber%>"/>
<tsa:hidden name="Catalog_catalogId" value="<%=id%>"/>
<tsa:hidden name="CatalogItem_catalogId" value="<%=id%>"/>
<tsa:hidden name="CatalogItem_itemNumber" value="<%=itemNumber%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="deleteall" value=""/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
			<tr>
				<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
			</tr>
			<tr>
				<td nowrap class=hdr12 valign=middle>
				<div style="margin-left: 10px; margin-right: 10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "catalogItem", "Catalog Item")%> - <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "alternateLanguageDescriptions", "Alternate Language Descriptions")%></div>
				</td>
			</tr>
		</table>
		</td>
		<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
		<td valign=bottom align=right height=30px>
			<table border=0 cellspacing=0 cellpadding=1 width=100%>
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

<%@ include file="/base/alttext_rows.jsp"%>
<%@ include file="/system/footer.jsp"%>


<table border=0 cellpadding=0 cellspacing=0 width=680px>
</table>


<SCRIPT value=JavaScript>
<!-- Hide script

	function saveAltTextList() {
	 		doSubmit('/admin/catalog/catalog_item.jsp', 'AltTextUpdateByItem;CatalogItemRetrieveById');
	}

	function returnAbort() {
	 		doSubmit('/admin/catalog/catalog_item.jsp', 'CatalogItemRetrieveById');
	}

// End Hide script -->
</SCRIPT>