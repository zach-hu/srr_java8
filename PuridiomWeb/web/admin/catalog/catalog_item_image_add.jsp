<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String return_page = "/admin/catalog/catalog_item.jsp";
	String  return_handler = "CatalogItemRetrieveById";

	String	catItem_Id_Add = (String) request.getAttribute("CatalogItem_catalogId");
	String	catItem_Id_itemNumber_Add = (String) request.getAttribute("CatalogItem_itemNumber");
	String	catalogItem_imageFile = (String) request.getAttribute("CatalogItem_imageFile");

%>


<tsa:hidden name="CatalogItem_id_catalogId" value="<%=catItem_Id_Add%>"/>
<tsa:hidden name="CatalogItem_id_itemNumber" value="<%=catItem_Id_itemNumber_Add%>"/>
<tsa:hidden name="CatalogItem_imageFile" value="<%=catalogItem_imageFile%>"/>
<tsa:hidden name="CatalogItem_catalogId" value="<%=catItem_Id_Add%>"/>
<tsa:hidden name="CatalogItem_itemNumber" value="<%=catItem_Id_itemNumber_Add%>"/>


<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "uploadItemImage", "Upload Item Image")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>

		</table>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr><td width=100% align=center valign=top><br><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "processing", "Processing")%>... <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "pleaseWait", "Please wait")%>.</b><br>
<br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td></tr>
</table>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	//doSubmit("<%=return_page%>", "CatalogItemUpdate");CatalogItemUpdateImage
	doSubmit('/admin/catalog/catalog_item.jsp', 'CatalogItemUpdate;CatalogItemRetrieveById');

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>