<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.entity.CostRange" %>
		<!-- Sidebar Filter -->

		<table border=0 cellspacing=1 cellpadding=0 width=100% id="currentFilterList">
		</table>

		<table cellspacing=0 cellpadding=0 width=100% border=0>
		<tr><td height=5px><img src="<%=contextPath%>/images/none.gif" height=5px border=0></td></tr>
		</table>
<%
		String priceFilter = "";
		List groupFilters = browse.getGroupFilterList();
		BrowseColumn groupFilter = new BrowseColumn(oid, language);

		if (groupFilters != null && groupFilters.size() > 0) {
			for (int ix=0; ix < groupFilters.size(); ix++) {
				BrowseGroupFilter filter = (BrowseGroupFilter) groupFilters.get(ix);
				String	colname = filter.getColumnName().replace('.', '_');

				labelsMap.put(colname, filter.getLabel());
			}
		}

		if (groupFilters != null && groupFilters.size() > 0) {
			for (int ix=0; ix < groupFilters.size(); ix++) {
				BrowseGroupFilter filter = (BrowseGroupFilter) groupFilters.get(ix);
				String	colname = filter.getColumnName().replace('.', '_');
				List	selectionValues = filter.getSelectionValues();

				if (irows > 0 && (selectionValues != null && selectionValues.size() > 1 || (filter.getType() != null && filter.getType().equals("Keywords")))) {
%>
		<div style="width:98%">
			<!-- start rounded corners -->
			<div id="container" style="width: 100%; align:left; margin:5;">
			<b class="rtop">
			  <b class="r1"></b> <b class="r2"></b> <b class="r3"></b> <b class="r4"></b>
			</b>
			<table cellpadding=0 cellspacing=2 border=0 class=browseHdr width=100%>
			<tr>
				<td>
					<table border=0 cellpadding=2 cellspacing=0 class=browseHdr width=100%>
					<tr>
						<td valign=middle class=filterbg><FONT color="yellow"><b>FILTER</b></FONT> By <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, filter.getLabel(), "")%></td>
						<td valign=middle align=right class=filterbg>
<%				if (selectionValues.size() > 5) {%>
							<a href='javascript:toggleRemainingSelections("<%=colname%>"); void(0);'><image id='<%=colname%>_toggleImg' valign='baseline' align='right' src='<%=contextPath%>/images/plus.gif' border=0 alt="View More" class="processOnImg"></a>
<%				}%>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td id="<%=colname%>FilterOptions">
					<table border=0 cellpadding=2 cellspacing=0 class=browseRow width=100%>
<%				if (filter.getType() != null && filter.getType().equals("Keywords")) {%>
					<tr>
						<td colspan=2 valign=middle align=left class=browseRow nowrap>
							Search within your results
						</td>
					</tr>
					<tr class=browseRow>
						<td valign=middle align=left class=browseRow nowrap>
							<input name="as_keywords" type=text maxlength=50 size=30 onchange="upperCase(this);">
						</td>
						<td valign=middle align=right class=browseRow><a href="javascript: filterKeywords(); void(0);"><img class=button src="<%=contextPath%>/images/button_go_sm.gif" border=0></a></td>
					</tr>
<%				}
					for (int iz=0; iz < selectionValues.size(); iz++) {
						Object selectionOption[] = (Object[]) selectionValues.get(iz);
						String	filterOption = (String) selectionOption[0];
						String	filterOptionDisplay = filterOption;
						String  filterTitle = "";
						Long count = (Long) selectionOption[1];

						if( selectionOption.length == 4){
							filterTitle = (String) selectionOption[2];
						}

						if (filter.getType().equalsIgnoreCase("Commodity")) {
							filterOptionDisplay = CommodityManager.getInstance().getCommodityDescription(oid, filterOption);
						}
						if (iz == 5) {
%>
					</table>
					<div id="<%=colname%>_remainingSelections" style="display:none; visibility:hidden;">
					<table border=0>
<%						}%>
					<tr>
						<td valign=top align='left'>&#149;</td>
						<td align='left' width='100%'><a href='javascript: groupFilter("<%=filter.getType()%>", "<%=colname%>", "<%=filterOption%>");void(0);' <% if(selectionOption.length == 4) {%>title="<%=filterTitle %>"<%} %>><%=filterOptionDisplay%></a><span>&nbsp;(<%=count%>)</span></td>
					</tr>
<%					}%>
					</table>
<%				if (selectionValues.size() >= 5) {%>
					</div>
<%				}%>
				</td>
			</tr>
			</table>
			<b class="rbottom">
			  <b class="r4"></b> <b class="r3"></b> <b class="r2"></b> <b class="r1"></b>
			</b>
			</div>
			<!-- end rounded corners -->

		</div>

		<table cellspacing=0 cellpadding=0 width=100% border=0>
		<tr><td height=5px><img src="<%=contextPath%>/images/none.gif" height=5px border=0></td></tr>
		</table>
<%			}
			}
		}%>

		<br>
<% if (irows > 0) {%>
		<table cellspacing=0 cellpadding=0 width=100% border=0>
		<tr><td align=center id="allDetailsLink"><a href="javascript: viewAllDetails(); void(0);">View All Details</a></td></tr>
		</table>
<%	}%>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var costRangeArray = new Array();
	var 	commodityType = "<%//PropertiesManager.getInstance(oid).getProperty("MISC", "CommodityType", "")%>";

<%	List costRangeList = browse.getCostRangeList();
		if (costRangeList != null) {
			for (int i=0; i < costRangeList.size(); i++) {
				CostRange costRange = (CostRange) costRangeList.get(i);
%>
	costRangeArray[<%=i%>] = new Array();
	costRangeArray[<%=i%>][0] = "<%=costRange.getDescription()%>";
	costRangeArray[<%=i%>][1] = "<%=costRange.getMinimumCost()%>";
	costRangeArray[<%=i%>][2] = "<%=costRange.getMaximumCost()%>";
<%			}
		}
%>

	setCurrentFilterList();

	function filterKeywords() {
		var keywords = frm.as_keywords.value;
		var itemType = frm.as_item_type.value;

		if ( !isEmpty(keywords) ) {
			keywords = "%" + keywords + "%";
		}

		keywords = replaceForKeywords(keywords);

		if (itemType == "CAT") {
			setAdvancedFilter("CatalogItem_id_itemNumber", "LIKE", keywords, "OR", "N", "N");
			setAdvancedFilter("CatalogItem_description", "LIKE", keywords, "AND", "N", "N");
			//setFilter("CatalogItem_description", "LIKE", keywords);
		} else if (itemType == "INV") {
			setAdvancedFilter("InvItem_itemNumber", "LIKE", keywords, "OR", "N", "N");
			setAdvancedFilter("InvItem_description", "LIKE", keywords, "AND", "N", "N");
			//setFilter("InvItem_description", "LIKE", keywords);
		}

		frm.browseName.value = browseName;
		doSubmit(frm.browsePage.value, 'BrowseFilterSort');
		//browse(browseName);
	}

	function groupFilter(type, colname, filterValue) {
		filterValue = filterValue.trim();
		if (type == "CostRange") {
			for (var i=0; i < costRangeArray.length; i++) {
				if (costRangeArray[i][0] == filterValue) {
					setFilter(colname, ">=", costRangeArray[i][1]);
					setFilter(colname, "<=", costRangeArray[i][2]);

					frm.browseName.value = browseName;
					doSubmit(frm.browsePage.value, 'BrowseFilterSort');
					//browse(browseName);
					return;
				}
			}
		} else if (type == "Commodity") {
			removeFilter(colname);
			var commodity = filterValue;
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
			if (commodity.indexOf("%") >= 0) {
				setFilter(colname, "LIKE", commodity);
			} else {
				setFilter(colname, "=", commodity);
			}
			frm.browseName.value = browseName;
			doSubmit(frm.browsePage.value, 'BrowseFilterSort');
		//	browse(browseName);
		} else {
			setFilter(colname, "=", filterValue);

			frm.browseName.value = browseName;
			doSubmit(frm.browsePage.value, 'BrowseFilterSort');
//			browse(browseName);
		}
	}

	function setCurrentFilterList() {
		var minCost;
		var maxCost;
<%
	if (filters != null && filters.size() > 0) {
		String	costColname = "";
		for (int ix=0; ix < filters.size(); ix++) {
			BrowseFilter filter = (BrowseFilter) filters.get(ix);
			String	colname = filter.getColumnName().replace('.', '_');
			String	value = filter.getValue();

			if (value != null && value.length() > 0) {
				/*
				if (filter.getColumnName().indexOf(".commodity") > 0 && PropertiesManager.getInstance(oid).getProperty("MISC", "CommodityType", "").equalsIgnoreCase("UNSPSC")) {
					if (value.indexOf("%") > 0) {
						value = value.replace('%','0');
						while (value.length() < 8) {
							value = value + "0";
						}
					}
					value = CommodityManager.getInstance().getCommodityDescription(oid, value);
				}
				*/
				value = value.replace('%',' ');
				value = value.replaceAll("\"","&quot");
				value = value.replaceAll("\'\'","\'");

				labelsMap.put("CatalogItem_udf1Code", "cat-LN01");
				labelsMap.put("CatalogItem_udf2Code", "cat-LN02");
				labelsMap.put("CatalogItem_udf3Code", "cat-LN03");
				labelsMap.put("CatalogItem_udf4Code", "cat-LN04");
				labelsMap.put("CatalogItem_udf5Code", "cat-LN05");

				if (labelsMap.containsKey(colname)) {
					if (colname.equals("CatalogItem_cost") || colname.equals("InvItem_issueCost")) {
						costColname = colname;
						if (filter.getOperator().equals(">=")) {
%>
							minCost = "<%=value%>";
<%						} else if (filter.getOperator().equals("<=")) {
%>
							maxCost = "<%=value%>";
<%						}
					} else {
						if ("brw-keyword(s)".equals(labelsMap.get(colname)) && "SRR10P".equals(oid)) {
						%>
							setFilterDisplay("<%=colname%>", "Keyword(s)", "<%=headerEncoder.encodeForJavaScript(value)%>");
						<%} else {%>
							setFilterDisplay("<%=colname%>", "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, (String) labelsMap.get(colname), "")%>", "<%=headerEncoder.encodeForJavaScript(value)%>");
<%					} }
				} else {
%>
			setFilterDisplay("<%=colname%>", "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, (String) labelsMap.get(colname), "")%>", "<%=value%>");
<%				}
			}
		}%>
		if (minCost != null && maxCost != null) {
			var costRange = getCostRangeDescription(minCost,maxCost);
			setFilterDisplay("<%=costColname%>", "<%=labelsMap.get(costColname)%>", costRange);
		}
<%	}%>
	}

	function getCostRangeDescription(minCost, maxCost) {
		var description = "$" + minCost + " - $" + maxCost;

		for (var i=0; i < costRangeArray.length; i++) {
			if (costRangeArray[i][1] == minCost && costRangeArray[i][2] == maxCost) {
				description = costRangeArray[i][0];
			}
		}

		return description;
	}

	function setFilterDisplay(colname, label, value) {
		var myTable = document.getElementById("currentFilterList");
		var myArea;

		if (browser =="NS6") {
			myArea = document.getElementById(colname + "_filterText");
		} else {
			myArea = document.all(colname + "_filterText");
		}

		if (myArea != undefined) {
			myArea.innerText = myArea.innerText + " " + value;
		} else {
			myRow = createRow(myTable);
			myCell = createCell(myRow);
			myCell.vAlign = "top";
			myCell.innerHTML = "<a href=\"javascript:void(0); removeFilter('" + colname + "'); void(0);\"><img src=\"<%=contextPath%>/images/delete.gif\" border=0 alt=\"Remove " + label + " criteria\"></a>";
			myCell = createCell(myRow);
			myCell.vAlign = "top";
			myCell.innerHTML = "<b>" + label + ":</b>&nbsp;<span id=\"" + colname + "_filterText\">" + value + "</span>&nbsp;";
		}
	}

	function toggleRemainingSelections(colname) {
		var myImg = document.getElementById(colname + "_toggleImg");
		if (myImg.src.indexOf("<%=contextPath%>/images/plus.gif") >= 0) {
			myImg.src = "<%=contextPath%>/images/minus.gif";
			myImg.alt = "View Less";
			displayArea(colname + "_remainingSelections");
		} else {
			myImg.src = "<%=contextPath%>/images/plus.gif";
			myImg.alt = "View More";
			hideArea(colname + "_remainingSelections");
		}
	}

	function viewAllDetails() {
		//set cursor to hourglass while the system is processing
		document.body.style.cursor = "wait";

		setInnerHTML("allDetailsLink", "<a href=\"javascript: hideAllDetails(); void(0);\">Hide All Details</a>");

		for (var i=0; i < currentRows; i++) {
			viewItemDetails(i);
		}

		//reset cursor - processing complete
		document.body.style.cursor = "";
	}

	function hideAllDetails() {
		//set cursor to hourglass while the system is processing
		document.body.style.cursor = "wait";

		setInnerHTML("allDetailsLink", "<a href=\"javascript: viewAllDetails(); void(0);\">View All Details</a>");

		for (var i=0; i < currentRows; i++) {
			hideItemDetails(i);
		}

		//reset cursor - processing complete
		document.body.style.cursor = "";
	}

// End Hide script -->
</SCRIPT>

		<!-- End Sidebar Filter -->