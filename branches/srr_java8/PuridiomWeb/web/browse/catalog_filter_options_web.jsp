<%@ page language="java" errorPage="/system/error.jsp"%>
<%@ include file="/system/prevent_caching.jsp"%>
<%@ include file="/system/context_path.jsp"%>
<%@ include file="/system/header.jsp"%>
<%@ page import="com.tsa.puridiom.property.PropertiesManager"%>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	List	catalogList = (List) request.getAttribute("catalogList");
	List	invCatalogList = (List) request.getAttribute("invCatalogList");
	String	commodityType = HiltonUtility.ckNull(propertiesManager.getProperty("MISC", "CommodityType", ""));
	String	formtype = (String) request.getAttribute("formtype");
	String	itemtype = (String) request.getAttribute("as_item_type");
	String	invbrowse = HiltonUtility.ckNull((String) request.getAttribute("invbrowse"));
	String	icHeaderName = "";
	String	fiscalyearName = "";
	String	createtypeName = "";
	String	createsubtypeName = "";
	String	formnumberName = "";
	String	amendmentNumber = "";
	String	revisionNumber = "";
	String	releaseNumber = "";
	String	formDescription = "";

	if (formtype != null) {
		formtype = formtype.toUpperCase();
		if (formtype.equals("REQ")) {
			formDescription = "requisition";
			icHeaderName = "RequisitionHeader_icReqHeader";
			fiscalyearName = "RequisitionHeader_fiscalYear";
			createtypeName = "RequisitionHeader_requisitionType";
			createsubtypeName = "RequisitionHeader_rqSubType";
			formnumberName = "RequisitionHeader_requisitionNumber";
			String reqtype = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_requisitionType"));
			if (reqtype.equals("S") || reqtype.equals("T") || reqtype.equals("I"))
			{
				invbrowse = "TRUE";
			}
		} else if (formtype.equals("RFQ")) {
			formDescription = "solicitation";
			icHeaderName = "RfqHeader_icRfqHeader";
			fiscalyearName = "RfqHeader_fiscalYear";
			createtypeName = "RfqHeader_rfqType";
			formnumberName = "RfqHeader_rfqNumber";
			amendmentNumber = HiltonUtility.ckNull((String) request.getAttribute("RfqHeader_rfqAmendment"));
		} else if (formtype.equals("PO")) {
			formDescription = "order";
			icHeaderName = "PoHeader_icPoHeader";
			fiscalyearName = "PoHeader_fiscalYear";
			createtypeName = "PoHeader_poType";
			formnumberName = "PoHeader_poNumber";
			revisionNumber = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_revisionNumber"));
			releaseNumber = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_releaseNumber"));
		} else if (formtype.equals("DSB")) {
			formDescription = "disbursement";
			icHeaderName = "DisbHeader_icDsbHeader";
			fiscalyearName = "DisbHeader_fiscalYear";
			createtypeName = "DisbHeader_disbType";
			formnumberName = "DisbHeader_disbNumber";
		}
	}
	if (itemtype == null || itemtype.length() <= 0) {
		if (invbrowse.equalsIgnoreCase("TRUE")) {
			itemtype = "INV";
		} else {
			itemtype = "CAT";
		}
	}
	if (icHeaderName.length() == 0) {
		icHeaderName = "icHeader";
	}

	String	icHeader = (String) request.getAttribute(icHeaderName);
	String	fiscalyear = (String) request.getAttribute(fiscalyearName);
	String	createtype = (String) request.getAttribute(createtypeName);
	String	createsubtype = (String) request.getAttribute(createsubtypeName);
	String	formnumber = (String) request.getAttribute(formnumberName);
%>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<tsa:hidden name="formtype" value="<%=formtype%>"/>
<tsa:hidden name="icHeader" value="<%=icHeader%>"/>
<%	if (!icHeaderName.equals("icHeader")) {%>
<tsa:hidden name="<%=icHeaderName%>" value="<%=icHeader%>"/>
<%	}%>
<tsa:hidden name="<%=fiscalyearName%>" value="<%=fiscalyear%>"/>
<tsa:hidden name="<%=createtypeName%>" value="<%=createtype%>"/>
<% if (!HiltonUtility.isEmpty(createsubtypeName)) {%>
<tsa:hidden name="<%=createsubtypeName%>" value="<%=createsubtype%>"/>
<% }%>
<tsa:hidden name="<%=formnumberName%>" value="<%=formnumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=amendmentNumber%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=revisionNumber%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=releaseNumber%>"/>
<tsa:hidden name="createAction" value="SAVE"/>
<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="StdTable_tableType" value=""/>
<tsa:hidden name="invbrowse" value="<%=invbrowse%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
			<tr>
				<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
			</tr>
			<tr>
				<td nowrap class=hdr12 valign=middle>
				<div style="margin-left: 10px; margin-right: 10px" class=hdr12>Web Catalog Search - Openkapow</div>
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
		<td colspan=2>
		<table border=0 cellpadding=2 cellspacing=0 width=100%>
			<tr>
				<td valign=top>Openkapow.com is an open service platform, this means that you can build your own services (called robots) and run them from openkapow.com, all for free. These robots accesses web sites and allows you to use data, functionality and even the user interface of other web sites in a whole new way. You can then use those services from within your own mashups, code, Yahoo! Pipes, Google Gadgets etc.</td>
			</tr>
			<tr>
				<td align=center><br>
				<table border=0 cellpadding=2 cellspacing=0 width=90% class=mnav>
					<tr>
						<td>
						<table cellpadding=0 cellspacing=0 border=0 class=mnav width=100%>
							<tr>
								<td>
								<table border=0 cellpadding=1 cellspacing=0 class=mnav>
									<tr>
										<%	if (!invbrowse.equalsIgnoreCase("TRUE")) {%>
										<td align=right class=mnav><tsa:hidden name="as_item_type" value="CAT"/></td>
										<!--td nowrap class=mnav><b>Catalog Items</b>&nbsp;</td-->
										<%	}%>
									</tr>
								</table>
								</td>
								<td align=right class=mnav>
								<table border=0 cellpadding=1 cellspacing=0 class=mnav>
									<tr>
										<td align=right nowrap class=mnav>&nbsp;</td>
										<!--<td nowrap class=mnav>
										<div id="catalogSort" style="visibility: visible; display: block;"><select name="as_catalog_sort" tabindex=100 onchange="setCatalogSortOptions();">
											<option value=""></OPTION>
											<option value="CatalogItem_id_itemNumber" selected>Item Number</option>
											<option value="CatalogItem_cost">Cost</option>
											<%	if ( !oid.equalsIgnoreCase("qri06p")) {	%>
											<option value="CatalogItem_commodity">Commodity</option>
											<%	}	%>
											<option value="CatalogItem_id_catalogId">Catalog Id</option>
											<!--option value="CatalogItem_mfgName">Manufacturer</option>
											<option value="CatalogItem_modelNumber">Model Number</option-->
										<!--</select> <select name="as_catalog_sort_opt" tabindex=100>
											<option value=""></option>
											<option value="ASC" SELECTED>A</option>
											<option value="DESC">D</option>
										</select></div>
										<div id="inventorySort" style="visibility: hidden; display: none;"><select name="as_inventory_sort" tabindex=100 onchange="setInventorySortOptions();">
											<option value=""></OPTION>
											<option value="InvItem_itemNumber" selected>Item Number</option>
											<option value="InvItem_issueCost">Cost</option>
											<option value="InvItem_commodity">Commodity</option>
											<option value="InvLocation_id_itemLocation">Location</option>
											<!--option value="InvItem_udf1Code">Udf 1</option>
											<option value="InvLocation_qtyOnHand">Qty. Available</option-->
										<!--</select> <select name="as_inventory_sort_opt" tabindex=100>
											<option value=""></option>
											<option value="ASC" SELECTED>A</option>
											<option value="DESC">D</option>
										</select></div>
										<div id="requisitionSort" style="visibility: hidden; display: none;"><select name="as_requisition_sort" tabindex=100 onchange="setRequisitionSortOptions();">
											<option value=""></OPTION>
											<option value="RequisitionLine_requisitionNumber" selected><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitionNumber", "Requisition #")%></option>
											<option value="RequisitionLine_itemNumber">Item Number</option>
											<option value="RequisitionLine_unitPrice">Cost</option>
											<option value="RequisitionLine_commodityCode">Commodity</option>
										</select> <select name="as_requisition_sort_opt" tabindex=100>
											<option value=""></option>
											<option value="ASC" SELECTED>A</option>
											<option value="DESC">D</option>
										</select></div>
										</td>-->
										<td width=1px></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>

						<table cellpadding=1 cellspacing=0 border=0 width=100% align=center class=browseRow>
							<tr>
								<td><br>
								<table cellpadding=1 cellspacing=0 border=0 width=98% align=top>
									<tr>
										<td align=right valign=top width=40%>
										<table width=98% align=top>
											<tr>
												<td align=right valign=top width=40%>
												<div id="catalog0" style="visibility: visible; display: block;">Web Site&nbsp;</div>
												<div id="inventory0" style="visibility: hidden; display: none;"><a href="javascript: browseLookup('as_inv_location', 'item_location'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inventoryLocation", "Inventory Location")%></a>&nbsp;</div>
												<div id="requisition0" style="visibility: hidden; display: none;">Requisition Number&nbsp;</div>
												</td>
												<td width=55%>
												<div id="catalog1" style="visibility: visible; display: block;">

												<select multiple=multiple name="as_catalog" tabindex=6 size="<%=catalogList.size()%>">
												<%
												if (catalogList != null) {
												for (int i=0; i < catalogList.size(); i++) {
													if(i == 0) {%>
													<option value="<%=(String)catalogList.get(i)%>" title="Choose a catalog." selected="selected"><%=(String)catalogList.get(i)%></option>
												<% }else{ %>
													<option value="<%=(String)catalogList.get(i)%>" title="Choose a catalog."><%=(String)catalogList.get(i)%></option>
												<% }}} %>
												</select>
												<tsa:hidden name="as_catalog_multiple" value=""/>

												</div>
												<div id="inventory1" style="visibility: hidden; display: none;"><input type=text name="as_inv_location" title="Enter a specific Inventory Location" size=15 maxlength=30 tabindex=9 value="" onchange="upperCase(this);"></div>
												<div id="requisition1" style="visibility: hidden; display: none;"><input type=text name="as_requisition_number" title="Enter a specific Requisition Number" size=15 maxlength=30 tabindex=15 value="" onchange="upperCase(this);"></div>
												</td>
											</tr>
										</table>
										</td>
										<td>
										<table width=98% align=top>
											<tr>
												<td align=right width=15% nowrap>Keyword(s)&nbsp;</td>
												<td width=45%><input type=text name="as_keywords" title="Enter descripitive keywords about the product to narrow your search." ; size=35 maxlength=100 tabindex=1 value="" onchange="upperCase(this);"> <tsa:hidden name="as_phrases" value=""/><tsa:hidden name="as_words" value=""/></td>
											</tr>
										</table>
										</td>
										<tsa:hidden name="as_manufacturer" value=""/>
										<tsa:hidden name="as_modelno" value=""/>
										<tsa:hidden name="as_inv_udf1" value=""/>
									</tr>
								</table>
								<br>
								</td>
							</tr>
							</td>
							<tsa:hidden name="as_type" value="REQ"/>
							<tsa:hidden name="as_cancel_page" value="/puridiom/xchange/shopcart/items_search.jsp"/>
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
						<td>Results Per Web Site</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>

		<br>

		<table border=0 cellpadding=0 cellspacing=0 width=680px>
			<tr>
				<td width=50% align=center><a href="javascript: search(); void(0);"><img class=button src="<%=contextPath%>/images/button_search.gif" tabindex=20 border=0 alt="Search"></a></td>
				<td width=50% align=center><a href="javascript: returnAbort(); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" tabindex=21 border=0 alt="Return"></a></td>
			</tr>
		</table>

		<!--<a href="javascript:openServices('http://localhost:8080/puridiom/EjemploServlet')">EjemploServlet</a>-->
		<!--<a href="http://localhost:8080/puridiom/EjemploServlet" target="_blank">EjemploServlet</a>-->

		<br>

		<%@ include file="/system/footer.jsp"%> <SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var 	commodityType = "<%=commodityType.toUpperCase()%>";
	var itemType = "<%=itemtype%>";

	if (commodityType == "UNSPSC" || commodityType == "NIGP" || commodityType == "NIGP-11" || commodityType == "TREE") {
		displayArea("commodityDescription");
	}

	if (itemType == "CAT") {
		displayCatalogOptions();
	} else if (itemType == "INV") {
		displayInventoryOptions();
	} else if (itemType == "REQ") {
		displayRequisitionOptions();
	}

	function returnAbort()
	{
		var hiddenFields = "";
<%	if (formtype.equals("REQ"))
		{ %>
				var pg = "/requests/req_items.jsp";
				var handlers = "RequisitionLineRetrieveByHeader";
				hiddenFields = "<input type=hidden name=\"RequisitionLine_icReqHeader\" value=\"<%=icHeader%>\">";
<%	}
		else if (formtype.equals("RFQ"))
		{
			if (s_view.equals("CLASSIC")) { %>
				var pg = "/rfq/rfq_summary.jsp";
				var handlers = "RfqRetrieve";
<%		}
			else if (s_view.equals("WIZARD")) { %>
				var pg = "/rfq/rfq_items.jsp";
				var handlers = "RfqLineRetrieveByHeader";
				hiddenFields = "<input type=hidden name=\"RfqLine_icRfqHeader\" value=\"<%=icHeader%>\">";
<%		}
		}
		else if (formtype.equals("PO"))
		{
			if (s_view.equals("CLASSIC")) { %>
				var pg = "/orders/po_summary.jsp";
				var handlers = "PoRetrieve";
<%		} else if (s_view.equals("WIZARD")) { %>
				var pg = "/orders/po_items.jsp";
				var handlers = "PoLineRetrieveByHeader";
				hiddenFields = "<input type=hidden name=\"PoLine_icPoHeader\" value=\"<%=icHeader%>\">";
<%		}
		} %>

		setHiddenFields(hiddenFields);
		doSubmit(pg, handlers);
	}

	function search() {
<%	 if (formtype.equals("DSB")) {%>
			if (frm.as_inv_location.value.length <= 0)
			{
				alert("You must specify an item location when creating an OTC disbursment.");
				return false;
			}
<%	} %>

		if (frm.as_item_type.value != undefined) {
			itemType = frm.as_item_type.value;
		} else {
			var i = 0;
			while (frm.as_item_type[i].value != undefined) {
				if (frm.as_item_type[i].checked) {
					itemType = frm.as_item_type[i].value;
					break;
				}
				i++;
			}
		}

		if (!isCriteriaEntered()) {
			alert("Please specify at least one search criteria.  Ex: Keyword(s): letterhead.");
			return false;
		}

		if (itemType == "CAT") {
			frm.browseName.value = "catalogitem";
		} else if (itemType == "INV") {
			frm.browseName.value = "invitem-invlocation";

<%	 	if (formtype.equals("DSB")) {%>
				frm.browseName.value = "invitem-invbinlocation";
<%		}%>

		} else if (itemType == "REQ") {
			frm.browseName.value = "requisitionline-for-purchasing";
		}
		setFilterOptions(itemType);
		setSort(itemType);

		if(frm.as_keywords.value != "")
		{
			for (var i=0; i<frm.as_catalog.length; i++)
			{
				if (frm.as_catalog.options[i].selected)
					frm.as_catalog_multiple.value += frm.as_catalog.options[i].value + "#";
			}
			doSubmit('browse/item_browse_web.jsp', 'BrowseRetrieveWeb');
		}
		else
		{
			alert("Please specify a product on the Keyword(s) input text");
		}
	}

	/*function openServices(servlet)
	{
		var dirUrl = servlet + "?catalog=" + frm.as_catalog.selected + "&keyword=" + frm.as_keywords.value;
		alert(dirUrl);
		alert(frm.as_keywords.value);
		alert(frm.as_catalog.value);
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=508, height=365, top=85, left=140";
		window.open(dirUrl,"",opciones);

		alert(frm.as_catalog.options[frm.as_catalog.selectedIndex].value);
		alert(frm.as_keywords.value);
	}*/

	function setFilterOptions(itemType) {
		var keywords = "";
		var itemNumber = "";
		var commodity = "";
		var minPrice = "";
		var maxPrice = "";

		if (frm.as_keywords) {
			keywords = frm.as_keywords.value;
		}
		if (frm.as_item_number) {
			itemNumber = frm.as_item_number.value;
		}
		if (frm.as_commodity) {
			commodity = frm.as_commodity.value;
		}
		if (frm.as_minprice) {
			minPrice = frm.as_minprice.value;
		}
		if (frm.as_maxprice) {
			maxPrice = frm.as_maxprice.value;
		}

		if ( !isEmpty(keywords) ) {
			keywords = "%" + keywords + "%";
		}

		if (commodityType == "UNSPSC") {
			var	segment = commodity.substring(0,2);
			var	family = commodity.substring(2,4);
			var	group = commodity.substring(4,6);
			var	specificCommodity = commodity.substring(6,8);

			if (family == "00") {
				commodity = segment + "%";
			} else if (group == "00") {
				commodity = segment + family + "%";
			} else if (specificCommodity == "00") {
				commodity = segment + family + group + "%";
			}
		}
		else if (commodityType == "NIGP") {
			var	segment = commodity.substring(0,3);
			var	family = commodity.substring(3,5);

			if (family == "00") {
				commodity = segment + "%";
			}
		}
		else if (commodityType == "NIGP-11") {
			var	level1 = commodity.substring(0,3);
			var	level2 = commodity.substring(3,5);
			var	level3 = commodity.substring(5,7);
			var	level4 = commodity.substring(7,11);

			if (level2 == "00") {
				commodity = level1 + "%";
			} else if (level3 == "00") {
				commodity = level1 + level2 + "%";
			} else if (level4 == "0000") {
				commodity = level1 + level2 + level3 + "%";
			}
		}

		if (itemType == "CAT") {
			setOriginalFilter("CatalogItem_description", "LIKE", keywords);
			setOriginalFilter("CatalogItem_id_itemNumber", "LIKE", itemNumber);
			if (commodity.indexOf("%") >= 0) {
				setOriginalFilter("CatalogItem_commodity", "LIKE", commodity);
			} else {
				setOriginalFilter("CatalogItem_commodity", "=", commodity);
			}
			setOriginalFilter("CatalogItem_id_catalogId", "=", frm.as_catalog[frm.as_catalog.selectedIndex].value);
			setOriginalFilter("CatalogItem_cost", ">=", minPrice);
			setOriginalFilter("CatalogItem_cost", "<=", maxPrice);
			setOriginalFilter("CatalogItem_mfgName", "=", frm.as_manufacturer.value);
			setOriginalFilter("CatalogItem_modelNumber", "=", frm.as_modelno.value);
		} else if (itemType == "INV") {
			setOriginalFilter("InvItem_description", "LIKE", keywords);
			setOriginalFilter("InvItem_itemNumber", "LIKE", itemNumber);
			if (commodity.indexOf("%") >= 0) {
				setOriginalFilter("InvItem_commodity", "LIKE", commodity);
			} else {
				setOriginalFilter("InvItem_commodity", "=", commodity);
			}
			setOriginalFilter("InvLocation_id_itemLocation", "=", frm.as_inv_location.value);
			setOriginalFilter("InvItem_udf1Code", "=", frm.as_inv_udf1.value);
			//setOriginalFilter("InvItem_udf2Code", "=", frm.as_inv_udf2.value);
			setOriginalFilter("InvItem_issueCost", ">=", minPrice);
			setOriginalFilter("InvItem_issueCost", "<=", maxPrice);
		} else if (itemType == "REQ") {
			setOriginalFilter("RequisitionLine_description", "LIKE", keywords);
			setOriginalFilter("RequisitionLine_itemNumber", "LIKE", itemNumber);
			if (commodity.indexOf("%") >= 0) {
				setOriginalFilter("RequisitionLine_commodityCode", "LIKE", commodity);
			} else {
				setOriginalFilter("RequisitionLine_commodityCode", "=", commodity);
			}
			setOriginalFilter("RequisitionLine_catalogId", "=", frm.as_reqcatalog[frm.as_reqcatalog.selectedIndex].value);
			setOriginalFilter("RequisitionLine_unitPrice", ">=", minPrice);
			setOriginalFilter("RequisitionLine_unitPrice", "<=", maxPrice);
			setOriginalFilter("RequisitionLine_requisitionNumber", "=", frm.as_requisition_number.value);
//			setOriginalFilter("RequisitionLine_requisitionerCode", "=", frm.as_requisitioner.value);
			setOriginalFilter("RequisitionLine_udf1Code", "=", frm.as_req_udf1.value);
			setOriginalFilter("RequisitionLine_udf2Code", "=", frm.as_req_udf2.value);
		}
	}

	function setSort(itemType) {
		/*var myCell = document.getElementById("filterFields");
		var filterFields = myCell.innerHTML;
		var colname = "";
		var sortOrder = "ASC";

		if (itemType == "CAT") {
			colname = frm.as_catalog_sort[frm.as_catalog_sort.selectedIndex].value;
			sortOrder = frm.as_catalog_sort_opt[frm.as_catalog_sort_opt.selectedIndex].value;
		} else if (itemType == "INV") {
			colname = frm.as_inventory_sort[frm.as_inventory_sort.selectedIndex].value;
			sortOrder = frm.as_inventory_sort_opt[frm.as_inventory_sort_opt.selectedIndex].value;
		} else if (itemType == "REQ") {
			colname = frm.as_requisition_sort[frm.as_requisition_sort.selectedIndex].value;
			sortOrder = frm.as_requisition_sort_opt[frm.as_requisition_sort_opt.selectedIndex].value;
		}

		filterFields = filterFields + "<input type=hidden name=\"" + "colname" + "\" value=\"" + colname  + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "operator\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "filter_txt\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "logicalOperator\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "originalFilter\" value=\"Y\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "sort\" value=\"" + sortOrder + "\">";

		myCell.innerHTML = filterFields;*/
	}

	function displayCatalogOptions() {
		displayArea('catalog');
		displayArea('catalogSort');
		hideArea('inventory');
		hideArea('inventorySort');
		hideArea('requisition');
		hideArea('requisitionSort');
	}

	function displayInventoryOptions() {
		hideArea('catalog');
		hideArea('catalogSort');
		displayArea('inventory');
		displayArea('inventorySort');
		hideArea('requisition');
		hideArea('requisitionSort');
	}

	function displayRequisitionOptions() {
		hideArea('catalog');
		hideArea('catalogSort');
		hideArea('inventory');
		hideArea('inventorySort');
		displayArea('requisition');
		displayArea('requisitionSort');
	}

	function setCatalogSortOptions() {
		var colname = frm.as_catalog_sort[frm.as_catalog_sort.selectedIndex].value;

		if (colname.length <= 0) {
			frm.as_catalog_sort_opt.selectedIndex = 0;
		}
	}

	function setInventorySortOptions() {
		var colname = frm.as_inventory_sort[frm.as_inventory_sort.selectedIndex].value;

		if (colname.length <= 0) {
			frm.as_inventory_sort_opt.selectedIndex = 0;
		}
	}

	function formatPrice(fld) {
		fld.value = nformat(nfilter(fld), 2);
	}

	function setCommodityDescription() {
		if (frm.as_commodity && frm.as_commodityName && isEmpty(frm.as_commodity.value)) {
			frm.as_commodityName.value = "";
		}
	}

	function isCriteriaEntered() {
		var keywords = "";
		var itemNumber = "";
		var commodity = "";
		var minPrice = "";
		var maxPrice = "";
		var catalogId = "";
		var extras = "";

		if (frm.as_keywords) {
			keywords = frm.as_keywords.value;
		}
		if (frm.as_item_number) {
			itemNumber = frm.as_item_number.value;
		}
		if (frm.as_commodity) {
			commodity = frm.as_commodity.value;
		}
		if (frm.as_minprice) {
			minPrice = frm.as_minprice.value;
		}
		if (frm.as_maxprice) {
			maxPrice = frm.as_maxprice.value;
		}

		if (itemType == "CAT") {
			catalogId = frm.as_catalog.value;
			extras = frm.as_manufacturer.value + frm.as_modelno.value;
		} else if (itemType == "REQ") {
			catalogId = frm.as_reqcatalog.value;
			extras = frm.as_req_udf1.value + frm.as_req_udf2.value + frm.as_requisition_number.value;
			//	check frm.as_requisitioner.value;
		} else if (itemType == "INV") {
			catalogId = frm.as_inv_location.value;
			extras = frm.as_inv_udf1.value + frm.as_inv_product.value + frm.as_inv_state.value;
			//	check frm.as_inv_udf2.value;
		}

		if ( isEmpty(replaceChars(keywords + itemNumber + commodity + minPrice + maxPrice + catalogId + extras, "%", "")) ) {
			return false;
		}
		return true;
	}

// End Hide script -->
</SCRIPT> <%@ include file="/system/prevent_caching.jsp"%>