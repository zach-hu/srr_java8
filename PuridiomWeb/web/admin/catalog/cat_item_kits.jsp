<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>


<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_alloc_by_qty = propertiesManager.getProperty("MISC", "ALLOCBYQTY", "N");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_catalog_id = (String) request.getAttribute("KitItem_parentCatalogId");
	String	s_item_number = (String) request.getAttribute("KitItem_parentItemNumber");
	UserRole roleR = UserManager.getInstance().getUserRole(oid,uid);
%>

<tsa:hidden name="KitItem_parentCatalogId" value="<%=s_catalog_id%>"/>
<tsa:hidden name="KitItem_parentItemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="CatalogItem_catalogId" value="<%=s_catalog_id%>"/>
<tsa:hidden name="CatalogItem_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="CatalogItem_kit" value=""/>
<tsa:hidden name="formType" value="CAT"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="as_item_type" value="CAT"/>
<tsa:hidden name="formtype" value="CAT"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Kit Items</div>
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
	<td valign=bottom align=middle width=*>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			 <td align=right width="60%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "item", "Item")%> #:</b>&nbsp;</td>
			 <td align=left width="40%"><%=s_item_number%></td>
		</tr>
		<tr>
			 <td align=right width="60%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "catalog", "Catalog")%>:</b>&nbsp;</td>
			 <td align=left width="40%"><%=s_catalog_id%></td>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top>

		<br>
		<br>

		<table border=0 cellpadding=0 cellspacing=0 width=465px align=center>
		<tr>
			<td align=center width=465px>
				<table border=1 cellspacing=0 cellpadding=0 width=465px class=browseHdr>

				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr "height=18px">
							<td width="15%" class=browseHdr><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "item", "Item")%> #</td>
							<td width="65%" class=browseHdr><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "description", "Description")%></td>
						  <td width="10%" class=browseHdr><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "quantity", "Quantity")%> </b></td>
							<td width="10%" class=browseHdr><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "delete", "Delete")%> </b></td>
						</tr>
						</table>
					</td>
				</tr>

				<tr>
					<td>
						<table id="kititems" name="kititems" border=0 cellpadding=0 cellspacing=2 width=465px align=center>
<%
		List kitItemList = (List) request.getAttribute("kitItemList");
		if (kitItemList != null)
		{
			for (int i = 0; i < kitItemList.size(); i++)
			{
				KitItem kitItem = (KitItem) kitItemList.get(i);
%>
						<tr height="18px"  class="browseRow">
							<td id="itemno" width="15%" valign="top">
								<%=kitItem.getComp_id().getChildItemNumber()%>
								<tsa:hidden name="KitItem_childItemNumber" value="<%=kitItem.getComp_id().getChildItemNumber()%>"/>
								<tsa:hidden name="KitItem_childCatalogId" value="<%=kitItem.getComp_id().getChildCatalogId()%>"/>
							</td>
							<td id="desc" width="65%" valign="top"><%=kitItem.getChildDescription()%></td>
							<td id="qty" width="5%" align="right" valign="top">
								<%=HiltonUtility.getFormattedQuantity(kitItem.getChildQuantity(), oid)%>
								<tsa:hidden name="KitItem_childQuantity" value="<%=kitItem.getChildQuantity()%>"/>
							</td>
							<td id="spacer" width="5%">&nbsp;</td>
							<td id="delete_<%=i%>" width="10%" align="center" valign="top"><a href="javascript: deleteMe(<%=i%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0"></a></td>
						</tr>
<%
			}
		}
		if ( kitItemList == null || kitItemList.size() <= 0 )
		{
%>
						<tr height="18px"><td align="center"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "thereAreNoItems", "There are no items in this kit")%>.</b></td>
						</tr>
<%
		}
%>

						<tr>
							<td colspan=5>
								<br>
								<% if (roleR.getAccessRights("CATITEM") >= 3) { %>
								<table border=0 cellspacing=0 cellpadding=2 width=450px align=center>
								<tr>
									<td nowrap>&nbsp;<a href="javascript: addItems(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addItems", "Add items")%></B></font></a></td>
									<td>&nbsp;</td>
									<td align=right nowrap><a href="javascript: deleteAll(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deleteAll", "Delete all")%></B></font></a>&nbsp;</td>
								</tr>
								</table>
								<% } %>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>

		<br>

		</td>
	</tr>
</table>

<br>
<br>

<div id="hiddenFields" style="visibility:hidden; display:none;"></div>

<table border=0 cellpadding=0 cellspacing=0 width=650px>
<tr>
	<td width=50% align=center>
	<% if (roleR.getAccessRights("CATITEM") >= 3) { %>
		<div id="pxbutton"><a href="javascript: doSubmit('/admin/catalog/catalog_item.jsp', 'KitItemUpdateByParent;CatalogItemRetrieveById'); void(0);">Save</a></div>
	<% } %>
	</td>
	<td width=50% align=center>
		<div id="pxbutton"><a href="javascript: doSubmit('/admin/catalog/catalog_item.jsp', 'CatalogItemRetrieveById'); void(0);">Return</a></div>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	frm = document.purchaseform;

	hideArea("navTable");
	displayArea("menuBarSpacer");

	var currentRow = 0;
	var myTable = document.getElementById("kititems");
	var count = myTable.rows.length;
	var totalRows = myTable.rows.length;

	if (totalRows > 1)
	{
		frm.CatalogItem_kit.value = "Y";
	}

	function deleteMe(row)
	{
		if (confirm("Are you sure you wish to delete this kit item?"))
		{
			myTable = document.getElementById("kititems");
			var currentRows = myTable.rows.length -1;

			if (row < currentRows)
			{
				for (var i = row; i < (currentRows - 1); i++)
				{
					var myCell = document.getElementById("delete_" + i);
					var myHTML = myCell.innerHTML;

					var newCell = document.getElementById("delete_" + (i+1));
					newCell.innerHTML = myHTML;
				}
			}

			myTable.deleteRow(row);

			if (currentRows <= 1)
			{
				frm.deleteall.value = "TRUE";
			}
			count--;

			if (count == 1)
			{
				myRow = myTable.insertRow(0);
				myRow.height = "18px";

			  	myCell = createCell(myRow);
				myCell.align = "center";
				myCell.innerHTML = "<b>There are no items in this kit.</b>";

				frm.CatalogItem_kit.value = "N";
			}
		}
	}

	function deleteAll()
	{
		if (confirm("Are you sure you wish to delete all items from this kit?"))
		{
			frm.deleteall.value = "TRUE";
			myTable = document.getElementById("kititems");
			count = myTable.rows.length-1;
			for (i = 0;i < count; i++)
			{
				myTable.deleteRow(0);
			}
			count = 0;

			myRow = myTable.insertRow(0);
			myRow.height = "18px";

		  	myCell = createCell(myRow);
			myCell.align = "center";
			myCell.innerHTML = "<b>There are no items in this kit.</b>";

			frm.CatalogItem_kit.value = "N";
		}
	}

	function addItems()
	{
		var itemType = "CAT";
		var types = document.getElementsByName("as_item_type");

		frm.browseName.value = "catalogitem";
		setOriginalFilter("CatalogItem_id_catalogId", "=", "<%=s_catalog_id%>");
		setOriginalFilter("CatalogItem_id_itemNumber", "<>", "<%=s_item_number%>");

		doSubmit('/admin/catalog/kit_item_browse.jsp', 'KitItemUpdateByParent;BrowseRetrieve');
	}


// End Hide script -->
</SCRIPT>
