<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	List	catalogList = (List) request.getAttribute("catalogList");
	List	invCatalogList = (List) request.getAttribute("invCatalogList");
	int	categoryCount = (new Integer(propertiesManager.getProperty("MISC", "CommoditySections", "0"))).intValue();
	String	commodityType = HiltonUtility.ckNull(propertiesManager.getProperty("MISC", "CommodityType", ""));
	String	formtype = (String) request.getAttribute("formtype");
	String	itemtype = (String) request.getAttribute("as_item_type");
	String	invbrowse = HiltonUtility.ckNull((String) request.getAttribute("invbrowse"));
	String	icHeaderName = "";
	String	fiscalyearName = "";
	String	createtypeName = "";
	String createsubtypeName = "";
	String	formnumberName = "";
	String	amendmentNumber = "";
	String buyerCode = "";
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
			buyerCode = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_buyerCode"));
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

	int	categoryStartPos[] = {0,2,4,6};
	int	categoryEndPos[] = {2,4,6,8};
	for (int cnt = 0; cnt < categoryCount; cnt++ ) {
		String posTemp = propertiesManager.getProperty("MISC","CommodityLevel" + Integer.toString(cnt + 1) + "Pos","") ;

		// Remove Quotes
		posTemp = posTemp.replaceAll("\"","");

		if (!HiltonUtility.isEmpty(posTemp)) {
	        //Pick out the parameters
	        StringTokenizer st = new StringTokenizer(posTemp,",");
	        for (int ix = 1;st.hasMoreElements();ix++) {
	        	categoryStartPos[cnt] = Integer.valueOf(st.nextToken()).intValue();
	        	categoryEndPos[cnt] = Integer.valueOf(st.nextToken()).intValue();
	        	if (ix == 2) { break ; }
	        }
	    } else {
	    	categoryStartPos[cnt] = 0;
	    	categoryEndPos[cnt] = 0;
	    }
	}

	String	icHeader = (String) request.getAttribute(icHeaderName);
	String	fiscalyear = (String) request.getAttribute(fiscalyearName);
	String	createtype = (String) request.getAttribute(createtypeName);
	String	createsubtype = (String) request.getAttribute(createsubtypeName);
	String	formnumber = (String) request.getAttribute(formnumberName);
	pageContext.setAttribute("oid", oid);
	pageContext.setAttribute("moduleType", moduleType);
%>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<tsa:hidden name="formtype" value="<%=formtype%>"/>
<tsa:hidden name="fromPage" value="advitemsearch"/>
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
<tsa:hidden name="PoHeader_buyerCode" value="<%=buyerCode%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=revisionNumber%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=releaseNumber%>"/>
<tsa:hidden name="createAction" value="SAVE"/>
<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="StdTable_tableType" value=""/>
<tsa:hidden name="invbrowse" value="<%=invbrowse%>"/>


<!-- style="margin-left:0px; margin-right:0px; height:30px;width: 200px;" class=hdr12>
<div id="tabCorner" style="width: 200px; height:2px; align:left;margin:0;">
<b class="rtop">
  <b class="rt1"></b> <b class="rt2"></b> <b class="rt3"></b> <b class="rt4"></b>
</b>
</div>
<div style="margin-left:10px; margin-right:0px; height:30px; valign:middle;" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "item-advancedItemSearch", "Advanced Item Search")%></div>
</div>
<div style="margin-left:200px; margin-right:0px; height:30px; valign:middle;" class=browseRow>
<b class="rbottom">
  <b class="r4"></b><b class="rt4"></b>
</b>
</div>
<br-->
<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=200px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>
				<tsa:label labelName="item-advancedItemSearch" defaultString="Advanced Item Search" />
				</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right width=100%>
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

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td colspan=2 align=center>
		<table border=0 cellpadding=2 cellspacing=0 width=100%>
		<tr>
			<td align=center>
				<table border=0 cellpadding=2 cellspacing=0 width=95%>
				<tr>
					<td valign=top width=95%>
					<tsa:label labelName="item-searchOnline" defaultString="Search the online database of items. To help narrow your results and find the item(s) you are looking for use the search criteria below. You can select a catalog and enter one or more item description keywords and/or part of the item number. You can also use the criteria options in combination." />
					</td>
				</tr>
				</table>
				<br>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

			<!-- start rounded corners -->
			<div id="container" style="width: <%=formEntryWidth%>; align:left;margin:20;">
				<b class="rtop">
				  <b class="r1"></b> <b class="r2"></b> <b class="r3"></b> <b class="r4"></b>
				</b>

				<table border=0 cellpadding=2 cellspacing=0 width=100% class=browseHdr>
				<tr>
					<td>
						<table cellpadding=0 cellspacing=0 border=0 class=browseHdr width=100%>
						<tr>
							<td>
								<table border=0 cellpadding=0 cellspacing=0 class=browseHdr>
								<tr>
						<%	if (!invbrowse.equalsIgnoreCase("TRUE")) {%>
									<td align=right class=browseHdr>
									<% if (itemtype.equals("CAT")) {%>
									<tsa:input type="radio" name="as_item_type" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, \"req-searchCatItems\", \"Search Catalog Items\", false)%>" tabIndex="100" value="CAT" onclick="displayCatalogOptions()" checked="checked"></tsa:input>
									<%}else{ %>
									<tsa:input type="radio" name="as_item_type" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, \"req-searchCatItems\", \"Search Catalog Items\", false)%>" tabIndex="100" value="CAT" onclick="displayCatalogOptions()"></tsa:input>
									<%} %>
									</td>
									<td nowrap class=browseHdr><b>Catalog Items</b>&nbsp;</td>
						<%	}
								if (propertiesManager.isModuleActive("Extended Inventory") || propertiesManager.isModuleActive("Standard Inventory")) {%>
									<td align=right class=browseHdr>
									<% if (itemtype.equals("INV")) {%>
									<tsa:input type="radio" name="as_item_type" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, \"req-searchInvItems\", \"Search Inventory Items\", false)%>" tabIndex="100" value="INV" onclick="displayInventoryOptions()" checked="checked"></tsa:input>
									<%}else{ %>
									<tsa:input type="radio" name="as_item_type" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, \"req-searchInvItems\", \"Search Inventory Items\", false)%>" tabIndex="100" value="INV" onclick="displayInventoryOptions()"></tsa:input>
									<%} %>
									</td>
									<td nowrap class=browseHdr><b>Inventory Items&nbsp;</b></td>
						<%	}
								if (!invbrowse.equalsIgnoreCase("TRUE") && propertiesManager.isModuleActive("REQUISITIONS")) {%>
									<%if((!(formtype.equalsIgnoreCase("DSB") && createtype.equalsIgnoreCase("O")))){%>
									<td align=right class=browseHdr>
									<% if (itemtype.equals("REQ")) {%>
									<tsa:input type="radio" name="as_item_type" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, \"req-searchReqItems\", \"Search Requisition Items\", false)%>" tabIndex="100" value="INV" onclick="displayRequisitionOptions()" checked="checked"></tsa:input>
									<%}else{ %>
									<tsa:input type="radio" name="as_item_type" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, \"req-searchReqItems\", \"Search Requisition Items\", false)%>" tabIndex="100" value="INV" onclick="displayRequisitionOptions()"></tsa:input>
									<%} %>
									</td>
									<td nowrap class=browseHdr><b>Requisition Items&nbsp;</b></td>
									<%} %>
								<%	if ( !formtype.equalsIgnoreCase("REQ") && propertiesManager.isModuleActive("REQUISITIONS")) {%>
									<td align=right class=browseHdr>
									<% if (itemtype.equals("REQ")) {%>
									<tsa:input type="radio" name="as_item_type" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, \"req-searchAllItems\", \"Search All Items\", false)%>" tabIndex="100" value="INV" onclick="displayRequisitionOptions()" checked="checked"></tsa:input>
									<%}else{ %>
									<tsa:input type="radio" name="as_item_type" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, \"req-searchAllItems\", \"Search All Items\", false)%>" tabIndex="100" value="INV" onclick="displayRequisitionOptions()"></tsa:input>
									<%} %>
									</td>
									<td nowrap class=browseHdr><b>Assigned Items&nbsp;</b></td>
						<%			}
								}	%>
								</tr>
								</table>
							</td>
							<td width="15px">&nbsp;</td>
							<td align=right class=browseHdr>
								<table border=0 cellpadding=1 cellspacing=0 class=browseHdr>
								<tr>
									<td align=right nowrap class=browseHdr>Sort By</td>
									<td nowrap class=browseHdr>
									<div id="catalogSort" style="visibility: visible; display: block;">
										<select name="as_catalog_sort" tabindex=100 onchange="setCatalogSortOptions();">
											<option value=""></OPTION>
											<option value="CatalogItem_id_itemNumber" selected>Item Number</option>
											<option value="CatalogItem_cost">Cost</option>
<%	if (DictionaryManager.isVisible(oid, "commodity")) {%>
											<option value="CatalogItem_commodity">Commodity</option>
<%	}	%>
											<option value="CatalogItem_id_catalogId">Catalog Id</option>
											<!--option value="CatalogItem_mfgName">Manufacturer</option>
											<option value="CatalogItem_modelNumber">Model Number</option-->
										</select>
										<select name="as_catalog_sort_opt" tabindex=100>
											<option value=""></option>
											<option value="ASC" SELECTED>A</option>
											<option value="DESC">D</option>
										</select>
									</div>
									<div id="inventorySort" style="visibility: hidden; display: none;">
										<select name="as_inventory_sort" tabindex=100 onchange="setInventorySortOptions();">
											<option value=""></OPTION>
											<option value="InvItem_itemNumber" selected>Item Number</option>
											<option value="InvItem_issueCost">Cost</option>
<%	if (DictionaryManager.isVisible(oid, "commodity")) {%>
											<option value="InvItem_commodity">Commodity</option>
<%	}%>
											<option value="InvLocation_id_itemLocation">Location</option>
											<!--option value="InvItem_udf1Code">Udf 1</option>
											<option value="InvLocation_qtyOnHand">Qty. Available</option-->
										</select>
										<select name="as_inventory_sort_opt" tabindex=100>
											<option value=""></option>
											<option value="ASC" SELECTED>A</option>
											<option value="DESC">D</option>
										</select>
									</div>
									<div id="requisitionSort" style="visibility: hidden; display: none;">
										<select name="as_requisition_sort" tabindex=100 onchange="setRequisitionSortOptions();">
											<option value=""></OPTION>
											<option value="RequisitionLine_requisitionNumber" selected>
											<tsa:label labelName="requisitionNumber" defaultString="Requisition #" />
											</option>
											<option value="RequisitionLine_itemNumber">Item Number</option>
											<option value="RequisitionLine_unitPrice">Cost</option>
<% if (DictionaryManager.isVisible(oid, "commodity")) {%>
											<option value="RequisitionLine_commodityCode">Commodity</option>
<%	}%>
										</select>
										<select name="as_requisition_sort_opt" tabindex=100>
											<option value=""></option>
											<option value="ASC" SELECTED>A</option>
											<option value="DESC">D</option>
										</select>
									</div>
									</td>
									<td width=1px></td>
								</tr>
								</table>
							</td>
						</tr>
						</table>


						<table cellpadding=1 cellspacing=0 border=0 width=100% align=center class=browseRow>
						<tr>
							<td>
								<br>
								<table cellpadding=1 cellspacing=0 border=0 width=98% align=center>
								<tr>
									<% if (formtype.equals("DSB")) { %>
									<td align=right nowrap>
									<tsa:label labelName="itemNumber" defaultString="Item/Part #" />&nbsp;
									</td>
									<td><input type=text name="as_item_number" title="Enter a specific Item/Part #" size=20 maxlength=30 tabindex=2 value="" onchange="upperCase(this);"></td>
									<% } else { %>
									<td align=right width=13% nowrap>
									<tsa:label labelName="item-keywords" defaultString="Keyword(s)" />&nbsp;
									</td>
									<td width=45%>
										<input type=text name="as_keywords" title="Enter descripitive keywords about the product to narrow your search."; size=35 maxlength=100 tabindex=1 value="" onchange="upperCase(this);">
										<tsa:hidden name="as_phrases" value=""/><tsa:hidden name="as_words" value=""/>
									</td>
									<% } %>
									<td align=right width=24%>
										<div id="catalog0" style="visibility:visible; display:block;">
							<%	if (catalogList != null && catalogList.size() < 500 && propertiesManager.getProperty("CATALOG OPTIONS", "USEDROPDOWN", "N").equalsIgnoreCase("Y")) {%>
											Catalog&nbsp;
							<%	} else {%>
											<a href="javascript: browseLookup('as_catalog', 'catalog-for-item-filter'); void(0);">Catalog</a>&nbsp;
							<%	} %>
										</div>
										<div id="inventory0" style="visibility:hidden; display:none;"><a href="javascript: browseLookup('as_inv_location', 'item_location'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inventoryLocation", "Inventory Location")%></a>&nbsp;</div>
										<div id="requisition0" style="visibility:hidden; display:none;">Requisition Number&nbsp;</div>
									</td>
									<td width=18%>
										<div id="catalog1" style="visibility:visible; display:block;">
							<%	if (catalogList != null && catalogList.size() < 500 && propertiesManager.getProperty("CATALOG OPTIONS", "USEDROPDOWN", "N").equalsIgnoreCase("Y")) {%>
											<select name="as_catalog" tabindex=6>
												<option value="" selected="selected" title="Choose a catalog.">ANY</option>
							<%		for (int i=0; i < catalogList.size(); i++) {
											Catalog catalog = (Catalog) catalogList.get(i);
											if (catalog.getOrdersOnly().equalsIgnoreCase("Y") && !user.isABuyer())
											{
												continue;
											}
											if (!catalog.getExternalCatalog().equalsIgnoreCase("Y") && !catalog.getWebCatalog().equalsIgnoreCase("N")) {
												String catalogSelection = catalog.getCatalogId();
												if (!HiltonUtility.isEmpty(catalog.getTitle())) {
													if (catalog.getTitle().length() > 50) {
														catalogSelection = catalogSelection + " - " + catalog.getTitle().substring(0, 50) + "...";
													} else {
														catalogSelection = catalogSelection + " - " + catalog.getTitle();
													}
												}
							%>
												<option value="<%=catalog.getCatalogId()%>"><%=catalogSelection%></option>
							<%			}
										}%>
											</select>
							<%	} else {%>
										<input type=text name="as_catalog" title="Enter a specific Catalog Id" size=15 maxlength=30 tabindex=8 value="" onchange="upperCase(this);">
							<%	} %>
										</div>
										<div id="inventory1" style="visibility:hidden; display:none;"><input type=text name="as_inv_location" title="Enter a specific Inventory Location" size=15 maxlength=30 tabindex=9 value="" onchange="upperCase(this);"></div>
										<div id="requisition1" style="visibility:hidden; display:none;"><input type=text name="as_requisition_number" title="Enter a specific Requisition Number" size=15 maxlength=30 tabindex=15 value="" onchange="upperCase(this);"></div>
									</td>
								</tr>
								<%
									String hideModel="", hideManufacturer="";
									if (oid.equalsIgnoreCase("hoy08p")) {
										hideModel = hideManufacturer = "style='visibility:hidden;'";
									}
								%>
								<tr>
									<% if (formtype.equals("DSB")) { %>
									<td align=right width=13% nowrap>
									<tsa:label labelName="item-keywords" defaultString="Keyword(s)" />&nbsp;
									</td>
									<td width=45%>
										<input type=text name="as_keywords" title="Enter descripitive keywords about the product to narrow your search."; size=35 maxlength=100 tabindex=1 value="" onchange="upperCase(this);">
										<tsa:hidden name="as_phrases" value=""/><tsa:hidden name="as_words" value=""/>
									</td>
									<% } else { %>
									<td align=right nowrap>
									<tsa:label labelName="itemNumber" defaultString="Item/Part #" />&nbsp;
									</td>
									<td><input type=text name="as_item_number" title="Enter a specific Item/Part #" size=20 maxlength=30 tabindex=2 value="" onchange="upperCase(this);"></td>
									<% } %>
									<td align=right nowrap <%=hideManufacturer%> >
										<div id="catalog2" style="visibility:visible; display:block;">
										<tsa:label labelName="manufacturer" defaultString="Manufacturer" />&nbsp;
										</div>
										<%	if ( !oid.equalsIgnoreCase("vse06p") ) {	%>
										<div id="inventory2" style="visibility:hidden; display:none;">
										<tsa:label labelName="IN01" defaultString="Inventory UDF 1" fieldName="as_inv_udf1" />
										&nbsp;</div>
										<% } %>
										<div id="requisition2" style="visibility:hidden; display:none;">Catalog&nbsp;</div>
									</td>
									<td>
										<div id="catalog3" style="visibility:visible; display:block;"><input <%=hideManufacturer%> type=text name="as_manufacturer" title="Enter a specific Manufacturer" size="15" maxlength="30" tabindex=7 value="" onchange="upperCase(this)"></div>
										<div id="inventory3" style="visibility:hidden; display:none;"><input type=text name="as_inv_udf1" title="Enter a specific UDF" size=15 maxlength=30 tabindex=10 value="" onchange="upperCase(this);"></div>
										<div id="requisition3" style="visibility:hidden; display:none;">
											<select name="as_reqcatalog" tabindex=7>
												<option value="" selected>ANY</option>
							<%	if (catalogList != null) {
										for (int i=0; i < catalogList.size(); i++) {
											Catalog catalog = (Catalog) catalogList.get(i);
											if (!catalog.getExternalCatalog().equalsIgnoreCase("Y")) {
							%>
												<option value="<%=catalog.getCatalogId()%>"><%=catalog.getCatalogId()%></option>
							<%			}
										}
									} %>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<td align=right nowrap>
									<tsa:label labelName="item-costRange" defaultString="Cost Range" />&nbsp;
									</td>
									<td nowrap>
										<input type=text name="as_minprice" title="Enter a Minimum Price" size=10 maxlength=15 tabindex=3 value="" onchange="formatPrice(this)">
										&nbsp;
										<tsa:label labelName="item-to" defaultString="to" />&nbsp;
										<input type=text name="as_maxprice" title="Enter a Maximum Price" size=10 maxlength=15 tabindex=4 value="" onchange="formatPrice(this)">
									</td>
									<td align=right nowrap <%=hideModel%> >
										<div id="catalog4" style="visibility:visible; display:block;">
										<tsa:label labelName="modelNumber" defaultString="Model No." />&nbsp;
										</div>
										<div id="inventory4" style="visibility:hidden; display:none;">
										<tsa:label labelName="chargeCode" defaultString="Charge Code"/></div>

										<div id="requisition4" style="visibility:hidden; display:none;">
										<tsa:label labelName="req-LN01" defaultString="Line UDF 1" />&nbsp;
										</div>
									</td>
									<td>
										<div id="catalog5" style="visibility:visible; display:block;"><input <%=hideModel%> type=text name="as_modelno" title="Enter a specific Model No." size=15 maxlength=30 tabindex=8 value="" onchange="upperCase(this);"></div>
										<div id="inventory5" style="visibility:hidden; display:none;"><input type=text name="as_chargeCode" size="15" maxlength="30" tabindex=8 value="" onchange="upperCase(this)"></div>
										<div id="requisition5" style="visibility:hidden; display:none;"><input type=text name="as_req_udf1" size="15" maxlength="30" tabindex=8 value="" onchange="upperCase(this)"></div>
									</td>
								</tr>
								<%
									String default_udf5Code = "";
 									if (oid.equalsIgnoreCase("bly07p"))
									{
										default_udf5Code = user.getNameUdf3();
									}
								%>
								<%
								String s_udf1Code = propertiesManager.getProperty("CATALOG ITEM", "SearchByUdf1Code", "N");
								if(s_udf1Code.equals("Y")) { %>
								<tr>
									<td align=right nowrap><a href="javascript: browseStd('as_udf1Code', 'PROCLVL'); void(0);">
									<tsa:label labelName="cat-LN01" defaultString="Udf 1" /></a>&nbsp;</td>
									<td><input type=text name="as_udf1Code" size="15" maxlength="15" tabindex=4 value="" onchange="upperCase(this);"></td>
								<% if (oid.equalsIgnoreCase("BLY07P")) {%>
									<td align=right nowrap><a href="javascript: browseStd('as_xRefCombo1', 'LN05');">

									<tsa:label labelName="cat-xrefcombo1" defaultString="Warehouse" /></a>&nbsp;
									</td>
									<td><input type=text name="as_xRefCombo1" size="15" maxlength="15" tabindex=4 value="<%=default_udf5Code%>" onchange="upperCase(this);"></td>
								<% } %>

								<% }
								String s_udf2Code = propertiesManager.getProperty("CATALOG ITEM", "SearchByUdf2Code", "N");
								if(s_udf2Code.equals("Y")) { %>
								<tr>
									<td align=right nowrap><a href="javascript: browseStd('as_udf2Code', 'LN02'); void(0);">
									<tsa:label labelName="cat-LN02" defaultString="Udf 2" /></a>&nbsp;</td>
									<td><input type=text name="as_udf2Code" size="15" maxlength="15" tabindex=4 value="" onchange="upperCase(this);"></td>
										<td align=right nowrap>
										<div id="inventory6" style="visibility:hidden; display:none;">
										<tsa:label labelName="BN03" defaultString="UDF 3"/></div>
									</td>
									<td>
										<div id="inventory7" style="visibility:hidden; display:none;">
										<input id="test" type=text name="as_udf3Code" size="15" maxlength="15" tabindex=4 value="" onchange="upperCase(this);">
										</div>
									</td>
								</tr>
								<% }
								String s_udf3Code = propertiesManager.getProperty("CATALOG ITEM", "SearchByUdf3Code", "N");
								if(s_udf3Code.equals("Y")) { %>
								<tr>
									<td align=right nowrap>
									<%	if (DictionaryManager.isLink(oid, "cat-LN03")) { %>
										<a href="javascript: browseStd('as_udf3Code', 'LN03'); void(0);"><tsa:label labelName="cat-LN03" defaultString="Udf 3" /></a>&nbsp;
									<%	} else { %>
										<tsa:label labelName="cat-LN03" defaultString="Udf 3" />&nbsp;
									<%	} %>
									</td>
									<td><tsa:input type="mintext" name="as_udf3Code" size="15" labelName="req-LN03" maxLength="15" tabIndex="4" value="" onchange="upperCase(this);"/></td>
								</tr>
								<% }
								String s_udf4Code = propertiesManager.getProperty("CATALOG ITEM", "SearchByUdf4Code", "N");
								if(s_udf4Code.equals("Y")) { %>
								<tr>
									<td align=right nowrap><a href="javascript: browseStd('as_udf4Code', 'LN04'); void(0);">
									<tsa:label labelName="cat-LN04" defaultString="Udf 4" /></a>&nbsp;</td>
									<td><input type=text name="as_udf4Code" size="15" maxlength="15" tabindex=4 value="" onchange="upperCase(this);"></td>
								</tr>
								<% }
								String s_udf5Code = propertiesManager.getProperty("CATALOG ITEM", "SearchByUdf5Code", "N");
								if(s_udf5Code.equals("Y")) { %>
								<tr>
									<td align=right nowrap>
									<%	if (DictionaryManager.isLink(oid, "cat-LN05")) { %>
										<a href="javascript: browseStd('as_udf5Code', 'LN05'); void(0);"><tsa:label labelName="cat-LN05" defaultString="Udf 5" /></a>&nbsp;
									<%	} else { %>
										<tsa:label labelName="cat-LN05" defaultString="Udf 5" />&nbsp;
									<%	} %>
									</td>
									<%--td><input type=text name="as_udf5Code" size="15" maxlength="15" tabindex=4 value="<%=default_udf5Code %>" onchange="upperCase(this);"></td--%>
									<td><tsa:input type="mintext" name="as_udf5Code"  size="15" value="" labelName="req-LN05" onchange="upperCase(this); updated();"/></td>
								</tr>
								<% } %>
								<% if (oid.equalsIgnoreCase("bly07p")) { %>
								<tr>
									<tsa:td field="cat-LN06" align="right" noWrap="nowrap" >
									<a href="javascript: browseStd('as_udf6Code', 'LN06'); void(0);">
									<tsa:label labelName="cat-LN06" defaultString="Udf 6" /></a>&nbsp;
									</tsa:td>
									<tsa:td field="cat-LN06" >
									<input type=text name="as_udf6Code" size="15" maxlength="15" tabindex=4 value="" onchange="upperCase(this);">
									</tsa:td>
								</tr>
								<tr>
									<tsa:td field="cat-LN07" align="right" noWrap="nowrap" >
									<a href="javascript: browseStd('as_udf7Code', 'LN07'); void(0);">
									<tsa:label labelName="cat-LN07" defaultString="Udf 7" /></a>&nbsp;
									</tsa:td>
									<tsa:td field="cat-LN07" >
									<input type=text name="as_udf7Code" size="15" maxlength="15" tabindex=4 value="" onchange="upperCase(this);">
									</tsa:td>
								</tr>
								<tr>
									<tsa:td field="cat-LN08" align="right" noWrap="nowrap" >
									<a href="javascript: browseStd('as_udf8Code', 'LN08'); void(0);">
									<tsa:label labelName="cat-LN08" defaultString="Udf 8" /></a>&nbsp;
									</tsa:td>
									<tsa:td field="cat-LN08" >
									<input type=text name="as_udf8Code" size="15" maxlength="15" tabindex=4 value="" onchange="upperCase(this);">
									</tsa:td>
								</tr>
								<tr>
									<tsa:td field="cat-LN09" align="right" noWrap="nowrap" >
									<a href="javascript: browseStd('as_udf9Code', 'LN09'); void(0);">
									<tsa:label labelName="cat-LN09" defaultString="Udf 9" /></a>&nbsp;
									</tsa:td>
									<tsa:td field="cat-LN09" >
									<input type=text name="as_udf9Code" size="15" maxlength="15" tabindex=4 value="" onchange="upperCase(this);">
									</tsa:td>
								</tr>
								<tr>
									<tsa:td field="cat-LN10" align="right" noWrap="nowrap" >
									<a href="javascript: browseStd('as_udf10Code', 'LN10'); void(0);">
									<tsa:label labelName="cat-LN10" defaultString="Udf 10" /></a>&nbsp;
									</tsa:td>
									<tsa:td field="cat-LN10" >
									<input type=text name="as_udf10Code" size="15" maxlength="15" tabindex=4 value="" onchange="upperCase(this);">
									</tsa:td>
								</tr>
								<% } %>
								<tr>
									<td align=right nowrap>
									<%	if (DictionaryManager.isLink(oid, "cat-umCode")) { %>
										<a href="javascript: browseLookup('as_umCode', 'unitofmeasure'); void(0);"><tsa:label labelName="cat-umCode" defaultString="U/M" /></a>&nbsp;
									<%	} else { %>
										<tsa:label labelName="cat-umCode" defaultString="U/M" />&nbsp;
									<%	} %>
									</td>
									<td><input type=text name="as_umCode" size="15" maxlength="15" tabindex=4 value="" onchange="upperCase(this);"></td>
								</tr>
								<tr>
									<tsa:td field="commodity" align="right" noWrap="nowrap" >
									<a href="javascript: browseCommodityByType(); void(0);"title="Click here to select a Commodity Code or enter a Commodity Code in the box on the right.">
									<tsa:label labelName="cat-commodity" defaultString="Commodity" /></a>&nbsp;
									</tsa:td>
									<tsa:td field="commodity" >
									<input type=text title="Enter a specific Commodity" name="as_commodity" size=20 maxlength=30 tabindex=5 value="" onchange="upperCase(this); setCommodityDescription();">
									</tsa:td>
									<td align=right nowrap>
									<%	if (invCatalogList != null && invCatalogList.size() > 0) {%>
										<div id="inventory8" style="visibility:hidden; display:none;">
										<tsa:label labelName="inventoryCatalog" defaultString="Inventory Catalog" />&nbsp;
										</div>
								<%	}%>
										<div id="requisition6" style="visibility:hidden; display:none;">
										<tsa:label labelName="req-LN02" defaultString="Line UDF 2" />&nbsp;</div>
									</td>
									<td>
									<%	if (invCatalogList != null && invCatalogList.size() > 0) {%>
										<div id="inventory9" style="visibility:hidden; display:none;">
											<select name="as_inv_catalog" tabindex=12>
												<option value="" selected>ANY</option>
									<%	for (int i=0; i < invCatalogList.size(); i++) {
												InvCatalog invCatalog = (InvCatalog) invCatalogList.get(i); %>
												<option value="<%=invCatalog.getInvCatid()%>"><%=invCatalog.getInvCatid()%></option>
									<%	} %>
											</select>
										</div>
								<%	}%>

										<div id="requisition7" style="visibility:hidden; display:none;"><input type=text name="as_req_udf2" title="Enter a specific UDF2" size="15" maxlength="30" tabindex=9 value="" onchange="upperCase(this)"></div>
									</td>
								</tr>
								<tr>
									<td align=right nowrap>
										<div id="inventory10" style="visibility:hidden; display:none;"><a href="javascript: browseStd('as_inv_product', 'PROD');">Product ID</a>&nbsp;</div>
									</td>
									<td>
										<div id="inventory11" style="visibility:hidden; display:none;"><input type=text name="as_inv_product" title="Enter a specific Product ID" size=15 maxlength=15 tabindex=13 value="" onchange="upperCase(this);"></div>
									</td>
								</tr>
								<tr>

									<tsa:td field="commodity" >	</tsa:td>
									<tsa:td field="commodity" >
									<div id="commodityDescription" style="visibility: hidden;display:block"><textarea name="as_commodityName" rows=3 cols=50 disabled></textarea></div>
									</tsa:td>
									<td align=right valign=top nowrap>
										<div id="inventory12" style="visibility:hidden; display:none;"><a href="javascript: browseStd('as_inv_state', 'STAT');">State</a>&nbsp;</div>
									</td>
									<td valign=top>
										<div id="inventory13" style="visibility:hidden; display:none;"><input type=text title="Enter a specific State" name="as_inv_state" size=5 maxlength=15 tabindex=14 value="" onchange="upperCase(this);"></div>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						</table>
					</td>
					<tsa:hidden name="as_type" value="REQ"/>
					<tsa:hidden name="as_cancel_page" value="/puridiom/xchange/shopcart/items_search.jsp"/>
				</tr>
				</table>
				<b class="rbottom">
				  <b class="r4"></b> <b class="r3"></b> <b class="r2"></b> <b class="r1"></b>
				</b>
			</div>
			<!-- end rounded corners -->

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center>
		<table border=0 cellpadding=1 cellspacing=0>

		<tr>
			<td align=right><tsa:label labelName="item-Display" defaultString="Display" /></td>
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
			<td><tsa:label labelName="item-resultsPerPage" defaultString="Results Per Page" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: search(); void(0);">Search</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: returnAbort(); void(0);">Return</a></div></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var 	commodityType = "<%=commodityType.toUpperCase()%>";
	var	displayCommodity = <%=DictionaryManager.isVisible(oid, "commodity")%>;
	var	itemType = "<%=headerEncoder.encodeForJavaScript(itemtype)%>";
	var test = document.getElementById("test");

	if (displayCommodity && (commodityType == "UNSPSC" || commodityType == "NIGP" || commodityType == "NIGP-11" || commodityType == "TREE")) {
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

<%		if (formtype.equals("DSB"))
		{ %>
				var pg = "/inventory/dsb_select_type.jsp";
				var handlers = "DoNothing";
<%		} %>

		setHiddenFields(hiddenFields);
		doSubmit(pg, handlers);
	}

	function search() {
<%	 if (formtype.equals("DSB")) {%>
			if (frm.as_inv_location.value.length <= 0 && (test.value == null || test.value == '' || test.value == undefined))
			{
				alert("You must specify an item location when creating an OTC disbursment.");
				return false;
			}
<%	} %>
		try {
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
		}
		catch (err)
		{
			alert("Must select a type of items to search.");
			return false;
		}

		if (!isCriteriaEntered()) {
			alert("Please specify at least one search criteria.  Ex: Keyword(s): letterhead.");
			return false;
		}

		if (itemType == "CAT") {
			if(orgId=="WPC08P")
			{
				newInputField = "<input type='hidden' name='isCatalogSecurityActive' value='Y'>";
			    setHiddenFields(newInputField);
			}
			frm.browseName.value = "catalogitem";
		} else if (itemType == "INV") {
			frm.browseName.value = "invitem-invlocation";

<%	 	if (formtype.equals("DSB")) {%>
				frm.browseName.value = "invitem-invbinlocation";
<%		}%>

		} else if (itemType == "REQ") {
			var formtype = frm.formtype.value;
			if (formtype == "REQ") {
				frm.browseName.value = "requisitionline-for-all";
			} else {
				frm.browseName.value = "requisitionline-for-purchasing";
			}
		} else if (itemType == "ALL") {
			//frm.browseName.value = "requisitionline-for-all";
			frm.browseName.value = "requisitionline-for-purchasing";
			itemType = "REQ";
		}
		setFilterOptions(itemType);
		setSort(itemType);

		doSubmit('browse/item_browse.jsp', 'BrowseRetrieve');
	}

	function setFilterOptions(itemType) {
		var keywords = "";
		var itemNumber = "";
		var udf1Code = "";
		var udf2Code = "";
		var udf3Code = "";
		var udf4Code = "";
		var udf5Code = "";
		var udf6Code = "";
		var udf7Code = "";
		var udf8Code = "";
		var udf9Code = "";
		var udf10Code = "";
		var umCode = "";
		var commodity = "";
		var minPrice = "";
		var maxPrice = "";
		var reqUdf1 = "";
		var reqUdf2 = "";
		var xRefCombo1 = "";
		var chargeCode = "";

		if (frm.as_keywords) {
			keywords = frm.as_keywords.value;
			keywords = replaceForKeywords(keywords);
		}
		if (frm.as_item_number) {
			itemNumber = frm.as_item_number.value;
			itemNumber = replaceForItemNumber(itemNumber);
		}
		if (frm.as_udf1Code) {
			udf1Code = frm.as_udf1Code.value;
		}
		if (frm.as_udf2Code) {
			udf2Code = frm.as_udf2Code.value;
		}
		if (frm.as_udf3Code) {
			udf3Code = frm.as_udf3Code.value;
		}
		if (frm.as_udf4Code) {
			udf4Code = frm.as_udf4Code.value;
		}
		if (frm.as_udf5Code) {
			udf5Code = frm.as_udf5Code.value;
		}
		if (frm.as_udf6Code) {
			udf6Code = frm.as_udf6Code.value;
		}
		if (frm.as_udf7Code) {
			udf7Code = frm.as_udf7Code.value;
		}
		if (frm.as_udf8Code) {
			udf8Code = frm.as_udf8Code.value;
		}
		if (frm.as_udf9Code) {
			udf9Code = frm.as_udf9Code.value;
		}
		if (frm.as_udf10Code) {
			udf10Code = frm.as_udf10Code.value;
		}
		if (frm.as_umCode) {
			umCode = frm.as_umCode.value;
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
		if (frm.as_req_udf1) {
			reqUdf1 = frm.as_req_udf1.value;
		}
		if (frm.as_req_udf2) {
			reqUdf2 = frm.as_req_udf2.value;
		}
		if (frm.as_xRefCombo1) {
			xRefCombo1 = frm.as_xRefCombo1.value;
		}
		if (frm.as_chargeCode) {
			chargeCode = frm.as_chargeCode.value;
		}
		if ( !isEmpty(keywords) ) {
			if( orgId=="BLY07P" && keywords.indexOf("%") >= 0 ) {
				keywords = keywords;
			}
			else {
				keywords = "%" + keywords + "%";
			}
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
		} else if (commodityType == "TREE") {
			var levels = new Array();
			var levelDefault = "0000000000000000000";
			var tempCommodity = "";
			var endPos = "<%=categoryEndPos%>";
			var startPos = "<%=categoryStartPos%>";

			for (var i=0; i < <%=categoryCount%>; i++)
			{
				var defaultString = levelDefault.substring(0, (endPos[i] - startPos[i]));

				levels[i] = commodity.substring(startPos[i], endPos[i]);

				if (levels[i] == defaultString) {
					for (var ix=0; ix<i; ix++) {
						tempCommodity = tempCommodity + levels[ix];
					}
					tempCommodity = tempCommodity + "%";
					i = <%=categoryCount%>;
				}
			}
		}

		if (itemType == "CAT") {
			setOriginalFilter("CatalogItem_description", "LIKE", keywords);
			setOriginalFilter("CatalogItem_id_itemNumber", "LIKE", itemNumber);
			setOriginalFilter("CatalogItem_umCode", "=", umCode);
			if (commodity.indexOf("%") >= 0) {
				setOriginalFilter("CatalogItem_commodity", "LIKE", commodity);
			} else {
				setOriginalFilter("CatalogItem_commodity", "=", commodity);
			}
			setOriginalFilter("CatalogItem_udf1Code", "=", udf1Code);
			setOriginalFilter("CatalogItem_udf2Code", "=", udf2Code);
			setOriginalFilter("CatalogItem_udf3Code", "=", udf3Code);
			setOriginalFilter("CatalogItem_udf4Code", "=", udf4Code);
			setOriginalFilter("CatalogItem_udf5Code", "=", udf5Code);
			setOriginalFilter("CatalogItem_udf6Code", "=", udf6Code);
			setOriginalFilter("CatalogItem_udf7Code", "=", udf7Code);
			setOriginalFilter("CatalogItem_udf8Code", "=", udf8Code);
			setOriginalFilter("CatalogItem_udf9Code", "=", udf9Code);
			setOriginalFilter("CatalogItem_udf10Code", "=", udf10Code);
<%	if (catalogList != null && catalogList.size() < 500 && propertiesManager.getProperty("CATALOG OPTIONS", "USEDROPDOWN", "N").equalsIgnoreCase("Y")) {%>
			setOriginalFilter("CatalogItem_id_catalogId", "=", frm.as_catalog[frm.as_catalog.selectedIndex].value);
<%	} else {%>
			setOriginalFilter("CatalogItem_id_catalogId", "=", frm.as_catalog.value);
<%	}%>
			setOriginalFilter("CatalogItem_cost", ">=", minPrice);
			setOriginalFilter("CatalogItem_cost", "<=", maxPrice);
			setOriginalFilter("CatalogItem_mfgName", "=", frm.as_manufacturer.value);
			setOriginalFilter("CatalogItem_modelNumber", "=", frm.as_modelno.value);
			setOriginalFilter("XrefCombo_code1", "=", xRefCombo1);
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
			setOriginalFilter("InvBinLocation_udf3Code", "=", udf3Code);
			setOriginalFilter("InvBinLocation_chargeCode", "=", chargeCode);
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
			setOriginalFilter("RequisitionLine_udf1Code", "=", reqUdf1);
			setOriginalFilter("RequisitionLine_udf2Code", "=", reqUdf2);
		}
	}

	function setSort(itemType) {
		var myCell = document.getElementById("filterFields");
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

		myCell.innerHTML = filterFields;
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
		var udf1Code = "";
		var udf2Code = "";
		var udf3Code = "";
		var udf4Code = "";
		var udf5Code = "";
		var udf6Code = "";
		var udf7Code = "";
		var udf8Code = "";
		var udf9Code = "";
		var udf10Code = "";
		var umCode = "";
		var commodity = "";
		var minPrice = "";
		var maxPrice = "";
		var catalogId = "";
		var extras = "";
		var reqUdf1 = "";
		var reqUdf2 = "";
		var xRefCombo1 = "";
		var chargeCode = "";

		if (frm.as_keywords) {
			keywords = frm.as_keywords.value;
			keywords = replaceForKeywords(keywords);
		}
		if (frm.as_item_number) {
			itemNumber = frm.as_item_number.value;
			itemNumber = replaceForItemNumber(itemNumber);
		}
		if (frm.as_udf1Code) {
			udf1Code = frm.as_udf1Code.value;
		}
		if (frm.as_udf2Code) {
			udf2Code = frm.as_udf2Code.value;
		}
		if (frm.as_udf3Code) {
			udf3Code = frm.as_udf3Code.value;
		}
		if (frm.as_udf4Code) {
			udf4Code = frm.as_udf4Code.value;
		}
		if (frm.as_udf5Code) {
			udf5Code = frm.as_udf5Code.value;
		}
		if (frm.as_udf6Code) {
			udf6Code = frm.as_udf6Code.value;
		}
		if (frm.as_udf7Code) {
			udf7Code = frm.as_udf7Code.value;
		}
		if (frm.as_udf8Code) {
			udf8Code = frm.as_udf8Code.value;
		}
		if (frm.as_udf9Code) {
			udf9Code = frm.as_udf9Code.value;
		}
		if (frm.as_udf10Code) {
			udf10Code = frm.as_udf10Code.value;
		}
		if (frm.as_umCode) {
			umCode = frm.as_umCode.value;
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
		if (frm.as_req_udf1) {
			reqUdf1 = frm.as_req_udf1.value;
		}
		if (frm.as_req_udf2) {
			reqUdf2 = frm.as_req_udf2.value;
		}
		if (frm.as_xRefCombo1) {
			xRefCombo1 = frm.as_xRefCombo1.value;
		}
		if (frm.as_chargeCode){
			chargeCode = frm.as_chargeCode.value;
		}

		if (itemType == "CAT") {
			catalogId = frm.as_catalog.value;
			extras = frm.as_manufacturer.value + frm.as_modelno.value;
		} else if (itemType == "REQ" || itemType == "ALL") {
			catalogId = frm.as_reqcatalog.value;
			extras = reqUdf1 + reqUdf2 + frm.as_requisition_number.value;
			//	check frm.as_requisitioner.value;
		} else if (itemType == "INV") {
			catalogId = frm.as_inv_location.value;
			extras = frm.as_inv_udf1.value + frm.as_inv_product.value + frm.as_inv_state.value;
			//	check frm.as_inv_udf2.value;
		}

		if ( isEmpty(replaceChars(keywords + itemNumber + udf1Code + udf2Code + udf3Code + udf4Code + udf5Code + udf6Code + udf7Code + udf8Code + udf9Code + udf10Code + xRefCombo1 + umCode + commodity + minPrice + maxPrice + catalogId + extras + chargeCode, "%", "")) ) {
			return false;
		}
		return true;
	}

	function browseCommodityByType()
	{
		<% if (oid.equalsIgnoreCase("msg07p")) { %>
		popupParameters = "browseName=commodity";
		popupParameters = popupParameters + ";formField=as_commodity;allowBrowse=" + frm.allowBrowse.value;
		popupParameters = popupParameters + ";colname=Commodity_commodityType;operator==;filter_txt=BO;logicalOperator=OR;originalFilter=Y;sort=N";
		popupParameters = popupParameters + ";colname=Commodity_commodityType;operator==;filter_txt=SP;logicalOperator=AND;originalFilter=Y;sort=N";
		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
		<% } else { %>
		browseCommodity('as_commodity', 'commodity','<%=commodityType%>');
		<% } %>
	}

// End Hide script -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>