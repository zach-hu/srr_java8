<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>
<SCRIPT LANGUAGE='JavaScript1.2' SRC="<%=contextPath%>/scripts/puridiom.js"></SCRIPT>

<tsa:hidden name="allowBrowse" value="true"/>

<!--CABECERA DE PAGINA-->

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Advanced Asset Search</div>
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


<!--FIN DE CABECERA DE PAGINA-->

<br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td colspan=2>
		<table border=0 cellpadding=2 cellspacing=0 width=100%>
		<tr>
			<td valign=top>Search the online database of asset.  To help narrow your results and find the asset(s) you are looking for use the search criteria below.  You can enter one or more item description po_vendor and/or part of the item number. You can also use the criteria options in combination. </td>
		</tr>
		<tr>
			<td align=center>
				<br>
				<!-- aqui esta la caja superior pequena-->

				<table border=0 cellpadding=2 cellspacing=0 width=90% class=mnav>
				<tr>
					<td>
						<table cellpadding=0 cellspacing=0 border=0 class=mnav width=100%>
						<tr>
							<td>
								<table border=0 cellpadding=1 cellspacing=0 class=mnav>
								<tr >
								<td><font color="white"> Asset Items </td>
								</tr>
								</table>
							</td>
							<td align=right class=mnav>
								<table border=0 cellpadding=1 cellspacing=0 class=mnav>
								<tr>
									<td align=right nowrap class=mnav>Sort By</td>
									<td nowrap class=mnav>
										<select name="as_asset_sort" tabindex=100 onchange="setAssetSortOptions();">
											<option value=""></OPTION>
											<option value="Asset_itemNumber" selected>Item Number</option>
											<option value="Asset_cost">Cost</option>
											<option value="Asset_commodity">Commodity</option>
											<option value="Asset_manufacturer">Manufacturer</option>
											<option value="Asset_model">Model Number</option>
										</select>
										<select name="as_asset_sort_opt" tabindex=100>
											<option value=""></option>
											<option value="ASC" SELECTED>A</option>
											<option value="DESC">D</option>
										</select>
									</td>
									<td width=1px></td>
								</tr>
								</table>
							</td>
						</tr>
						</table>
			<!-- caja inferior -->

						<table cellpadding=1 cellspacing=0 border=1 width=100% align=center class=browseRow>
						<tr>
							<td>
								<br>
								<table cellpadding=1 cellspacing=0 border=0 width=98% align=center>
								<tr>
									<td align=right width=13% nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "asset-purchasevendor", "Purchase Vendor")%>&nbsp;</td>
									<td width=45%>
										<input type=text name="as_poVendor" title="Enter descripitive po_vendor about the product to narrow your search."; size=30 maxlength=100 tabindex=1 value="" onchange="upperCase(this);">
									</td>
									<td align=right width=24% nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "asset-assetclass", "Asset Class")%>&nbsp;</td>
									<td width=30%>
										<select name="as_assetClass" tabindex=100 >
											<option value="" SELECTED>				</option>
											<option value="Tangible">	Tangible	</option>
											<option value="Intangible">	Intangible	</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "asset_itemNumber", "Item Number")%>&nbsp;</td>
									<td><input type=text name="as_itemNumber" title="Enter a specific Item Number" size=30 maxlength=30 tabindex=2 value="" onchange="upperCase(this);"></td>
									<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "asset-manufacturer", "Manufacturer")%>&nbsp;</td>
									<td><input type=text name="as_manufacturer" title="Enter a specific Manufacturer" size=15 maxlength=30 tabindex=8 value="" onchange="upperCase(this);">
								</tr>
								<tr>
									<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "asset-costRange", "Cost Range")%>&nbsp;</td>
									<td><input type=text name="as_minPrice" title="Enter a Minimum Price" size=10 maxlength=15 tabindex=3 value="" onchange="formatPrice(this)">
										&nbsp; to &nbsp;
										<input type=text name="as_maxPrice" title="Enter a Maximum Price" size=10 maxlength=15 tabindex=4 value="" onchange="formatPrice(this)"></td>
									<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "asset-model", "Model")%>&nbsp;</td>
									<td><input type=text name="as_model" title="Enter a specific Model " size=15 maxlength=30 tabindex=8 value="" onchange="upperCase(this);"></div>
								</tr>
								<tr>
									<td align=right nowrap><a href="javascript: browseLookup('as_commodity', 'Commodity'); void(0);" title="Click here to enter a Commodity Code or Enter a Commodity Code in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "asset-commodity", "Commodity")%></a>&nbsp;</td>
									<td><input type=text title="Enter a specific Commodity" name="as_commodity" size=30 maxlength=30 tabindex=5 value="" onchange="upperCase(this);"></td>
								</tr>
								</table>
								<br>
							</td>
						</tr>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center>
		<table border=0 cellpadding=1 cellspacing=0>
		<tr>
			<td align=right>Display</td>
			<td>
				<select name="pageSize">
					<option value="1">1</option>
					<option value="5">5</option>
					<option value="10" selected>10</option>
					<option value="15">15</option>
					<option value="20">20</option>
					<option value="25">25</option>
					<option value="50">50</option>
				</select>
			</td>
			<td>Results Per Page</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center>
		<a href="javascript: search(); void(0);"><img class=button src="<%=contextPath%>/images/button_search.gif" tabindex=20 border=0 alt="Search"></a>
	</td>
	<td width=50% align=center>
		<a href="javascript: doSubmit('/menu/main_menu.jsp','DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" tabindex=21 border=0 alt="Return"></a>
	</td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	var frm = document.purchaseform;

	function search() {
		setFilterOptions();
		setSort();
		browse('asset');
	}

	function setFilterOptions() {
		var poVendor = frm.as_poVendor.value;
		var itemNumber = frm.as_itemNumber.value;
		var commodity = frm.as_commodity.value;
		var minPrice = frm.as_minPrice.value;
		var maxPrice = frm.as_maxPrice.value;
		var assetClass = frm.as_assetClass.value;
		var model = frm.as_model.value;
		var manufacturer = frm.as_manufacturer.value;

		setOriginalFilter("Asset_poVendor", "LIKE", poVendor);
		setOriginalFilter("Asset_itemNumber", "LIKE", itemNumber);
		setOriginalFilter("Asset_commodity", "LIKE", commodity);
		setOriginalFilter("Asset_assetCost", ">=", minPrice);
		setOriginalFilter("Asset_assetCost", "<=", maxPrice);
		setOriginalFilter("Asset_assetClass", "=", assetClass);
		setOriginalFilter("Asset_model", "LIKE", model);
		setOriginalFilter("Asset_manufacturer", "LIKE", manufacturer);
	}

	function setSort() {
		var myCell = document.getElementById("filterFields");
		var filterFields = myCell.innerHTML;
		var colname = "";
		var sortOrder = "ASC";

		colname = frm.as_asset_sort[frm.as_asset_sort.selectedIndex].value;
		sortOrder = frm.as_asset_sort_opt[frm.as_asset_sort_opt.selectedIndex].value;

		filterFields = filterFields + "<input type=hidden name=\"" + "colname" + "\" value=\"" + colname  + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "operator\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "filter_txt\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "logicalOperator\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "originalFilter\" value=\"Y\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "sort\" value=\"" + sortOrder + "\">";

		myCell.innerHTML = filterFields;
	}

	function setAssetSortOptions() {
		var colname = frm.as_asset_sort[frm.as_asset_sort.selectedIndex].value;

		if (colname.length <= 0) {
			frm.as_asset_sort_opt.selectedIndex = 0;
		}
	}

	function formatPrice(fld) {
		fld.value = nformat(nfilter(fld), 2);
	}

// End Hide script -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>
