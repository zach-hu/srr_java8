<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility"%>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%

	String	s_catItem_Id = (String) request.getAttribute("CatalogItem_id_catalogId");
	String	s_catItem_Id_itemNumber = (String) request.getAttribute("CatalogItem_id_itemNumber");

	String	catItem_Id_Add = (String) request.getAttribute("CatalogItem_catalogId");
	String	catItem_Id_itemNumber_Add = (String) request.getAttribute("CatalogItem_itemNumber");

	String	errorMsg = (String) request.getAttribute("errorMsg");


%>
<tsa:hidden name="CatalogItem_catalogId" value="<%=catItem_Id_Add%>"/>
<tsa:hidden name="CatalogItem_itemNumber" value="<%=catItem_Id_itemNumber_Add%>"/>
<tsa:hidden name="CatalogItem_id_catalogId" value="<%=catItem_Id_Add%>"/>
<tsa:hidden name="CatalogItem_id_itemNumber" value="<%=catItem_Id_itemNumber_Add%>"/>
<tsa:hidden name="catalogId" value="<%=s_catItem_Id%>"/>
<tsa:hidden name="itemNum" value="<%=s_catItem_Id_itemNumber%>"/>
<tsa:hidden name="errorMsg" value="<%=errorMsg%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "itemImage", "Item Image")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
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

<br><br><br>

<table border=0 cellpadding=0 cellspacing=0 width=680px height=200px>
<tr>
	<td valign=top align=center width=100%>
		<table border=0 cellpadding=2 cellspacing=0>
		<tr>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "filetoattacha", "File to Attach")%>:</td>
			<td><input type=file name=file3 size=45></td>
		</tr>
		<tr><td colspan=2><br></td></tr>


		</table>

		<br><br>

		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td align=center width=50%><a href="javascript: doSubmit('/admin/catalog/catalog_item_image_add.jsp','--'); void(0);"><img src="<%=contextPath%>/images/button_add.gif" border=0 class=button></a></td>
			<td align=center width=50%> <a href="javascript: returnToCatalogItem(); void(0);"><img src="<%=contextPath%>/images/button_return.gif" border=0 class=button></a></td>
		</tr>
		</table>
	</td>
</tr>
</table>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	validFileTypes = '<%=".GIF, .PNG, .JPG, .BMP"%>';
	//validFileTypes = ".BMP.GIF.JPG.PNG";

	function validateForm() {
		var handler = frm.handler.value;

		if (handler.indexOf("--") >= 0) {
			var doc = frm.file3.value;
			var alertMessage = "";

			if (isEmpty(doc)) {
				alert('Please select a Image to upload.');
				return false;
			}
			else if (!isAttachmentExtValid(doc)) {
				return false;
			}
			else {
				frm.action =" <%=contextPath%>/HiltonCatalogItemImageUploadServlet";
				frm.action = frm.action + "?" + frm.organizationId.value;
				frm.action = frm.action + ";" + frm.CatalogItem_id_catalogId.value;
				frm.enctype = "multipart/form-data";
				frm.encoding = "multipart/form-data";
			//	frm.submit();
			}
		}
		return true;
	}

	function returnToCatalogItem()
	{
		doSubmit('/admin/catalog/catalog_item.jsp', 'CatalogItemRetrieveById');
	}


	function returnTo()
	{
	doSubmit('/admin/catalog/catalog_item.jsp', 'DoNothing');
	}

<%
if (!HiltonUtility.isEmpty(errorMsg)) {%>
	returnTo();
<%}
%>


// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>