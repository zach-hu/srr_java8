<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.browse.*" %>
<%
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
	String catalogId = "";
	String itemNumber = "";

	if (formtype != null) {
		formtype = formtype.toUpperCase();
		if (formtype.equals("REQ")) {
			icHeaderName = "RequisitionHeader_icReqHeader";
			fiscalyearName = "RequisitionHeader_fiscalYear";
			createtypeName = "RequisitionHeader_requisitionType";
			createsubtypeName = "RequisitionHeader_rqSubType";
			formnumberName = "RequisitionHeader_requisitionNumber";
		} else if (formtype.equals("RFQ")) {
			icHeaderName = "RfqHeader_icRfqHeader";
			fiscalyearName = "RfqHeader_fiscalYear";
			createtypeName = "RfqHeader_rfqType";
			formnumberName = "RfqHeader_rfqNumber";
			amendmentNumber = HiltonUtility.ckNull((String) request.getAttribute("RfqHeader_rfqAmendment"));
		} else if (formtype.equals("PO")) {
			icHeaderName = "PoHeader_icPoHeader";
			fiscalyearName = "PoHeader_fiscalYear";
			createtypeName = "PoHeader_poType";
			formnumberName = "PoHeader_poNumber";
			revisionNumber = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_revisionNumber"));
			releaseNumber = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_releaseNumber"));
		} else if (formtype.equals("DSB")) {
			icHeaderName = "DisbHeader_icDsbHeader";
			fiscalyearName = "DisbHeader_fiscalYear";
			createtypeName = "DisbHeader_disbType";
			formnumberName = "DisbHeader_disbNumber";
		} else if (formtype.equals("CAT")) {
			catalogId = HiltonUtility.ckNull((String) request.getAttribute("CatalogItem_catalogId"));
			itemNumber = HiltonUtility.ckNull((String) request.getAttribute("CatalogItem_itemNumber"));
		}
	}
	if (icHeaderName.length() == 0) {
		icHeaderName = "icHeader";
	}

	String	icHeader = (String) request.getAttribute(icHeaderName);
	String	fiscalyear = (String) request.getAttribute(fiscalyearName);
	//String	createtype = (String) request.getAttribute(createtypeName);
	String createtype = "";
	String	createsubtype = (String) request.getAttribute(createsubtypeName);
	String	formnumber = (String) request.getAttribute(formnumberName);

	Browse browse = (Browse) request.getAttribute("browse");
	BrowseObject browseObject = browse.getBrowseObject();
	BrowseColumn[] labels = browseObject.getLabels();
	List list = browse.getPageResults();
	int	ipageCount = browse.getPageCount();
	int	ipageSize = browse.getPageSize();
	int	irows = browse.getRowCount();

	StringBuffer sbHiddenFields = new StringBuffer();
	StringBuffer sbOriginalFilterFields = new StringBuffer();
	StringBuffer sbFilterFields = new StringBuffer();
	StringBuffer sbOriginalFilterText = new StringBuffer();
	StringBuffer sbFilterText = new StringBuffer();
	String	sortedColumn = "";
	String	sortedOrder = "";
	String	findString = (String) request.getAttribute("finder");
	List filters = browseObject.getBrowseFilters();
	BrowseColumn browseColumns[] = browseObject.getBrowseColumns();
	Map labelsMap = browseObject.getColumnLabels();
	StringBuffer sbSortOptions = new StringBuffer();

	if (filters != null && filters.size() > 0) {
		BrowseColumn[] cols = browseObject.getBrowseColumns();

		for (int ix=0; ix < filters.size(); ix++) {
			BrowseFilter filter = (BrowseFilter) filters.get(ix);
			String	colname = filter.getColumnName().replace('.', '_');
			String	sort = filter.getSort();
			StringBuffer sb = new StringBuffer("");

			sb.append("<tsa:hidden name=" + '"' + "colname" + '"' + " value=" + '"' + colname + '"' + "/>");
			sb.append("<tsa:hidden name=" + '"' + "filter_txt" + '"' + " value=" + '"' + filter.getValue() + '"' + "/>");
			sb.append("<tsa:hidden name=" + '"' + "operator" + '"' + " value=" + '"' + filter.getOperator() + '"' + "/>");
			sb.append("<tsa:hidden name=" + '"' + "logicalOperator" + '"' + " value=" + '"' + filter.getLogicalOperator() + '"' + "/>");
			sb.append("<tsa:hidden name=" + '"' + "sort" + '"' + " value=" + '"' + filter.getSort() + '"' + "/>");

			if (sort != null && !sort.equalsIgnoreCase("N") && sortedColumn.length() == 0) {
				sortedColumn = colname;
				sortedOrder = sort;
			}
			if (filter.isOriginalFilter()) {
				sb.append("<tsa:hidden name=" + '"' + "originalFilter" + '"' + " value=" + '"' + "Y" + '"' + "/>");

				sbOriginalFilterFields.append(sb);

				if (filter.getValue() != null && filter.getValue().length() > 0) {
					if (sbOriginalFilterText.length() > 0) {
						sbOriginalFilterText.append(filter.getLogicalOperator() + " ");
					}
					sbOriginalFilterText.append(labelsMap.get(colname) + " " + filter.getOperator() + " '" + filter.getValue() + "' ");
				}
			} else {
				sb.append("<tsa:hidden name=" + '"' + "originalFilter" + '"' + " value=" + '"' + "N" + '"' + "/>");

				sbFilterFields.append(sb);

				if (filter.getValue() != null && filter.getValue().length() > 0) {
					if (sbFilterText.length() > 0) {
						sbFilterText.append(filter.getLogicalOperator() + " ");
					}
					sbFilterText.append(labelsMap.get(colname) + " " + filter.getOperator() + " '" + filter.getValue() + "' ");
				}
			}
		}
	}

	for (int i=0; i < labels.length; i++) {
		BrowseColumn column = labels[i];

		if (column.getLabel() != null && column.getLabel().length() > 0 && !column.getType().equals("Input") && !column.getClassName().equals("Input") && !column.getType().equals("Checkbox")) {
			if (column.getColumnName().equals(sortedColumn)) {
				sbSortOptions.append("<option value=" + '"' + column.getColumnName() + '"' + " selected" + ">" + column.getLabel() + "</option>");
			} else {
				sbSortOptions.append("<option value=" + '"' + column.getColumnName() + '"' + ">" + column.getLabel() + "</option>");
			}
		}
	}
%>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<tsa:hidden name="browsePage" value="/admin/catalog/kit_item_browse.jsp"/>
<tsa:hidden name="browseId" value="<%=browse.getBrowseId()%>"/>
<tsa:hidden name="newPage" value="1"/>
<tsa:hidden name="pageSize" value=""/>
<tsa:hidden name="go_to_pg" value=""/>
<tsa:hidden name="as_item_type" value="<%=itemtype%>"/>
<tsa:hidden name="formtype" value="<%=formtype%>"/>
<tsa:hidden name="CatalogItem_catalogId" value="<%=catalogId%>"/>
<tsa:hidden name="CatalogItem_itemNumber" value="<%=itemNumber%>"/>
<tsa:hidden name="KitItem_parentCatalogId" value="<%=catalogId%>"/>
<tsa:hidden name="KitItem_parentItemNumber" value="<%=itemNumber%>"/>
<tsa:hidden name="createAction" value="SAVE"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12><%=browseObject.getTitle()%></div></td></tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
		<table cellpadding=0 cellspacing=0 border=0 width=100%>
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=100%>
				<tr>
					<td align=right>
						<b>Sort By:</b>&nbsp;
						<select name="sortcolumn" size=1 onchange="setSort();">
<%	if (HiltonUtility.isEmpty(sortedColumn)) {%>
						<option value="" selected></option>
<%	}%>
						<%=sbSortOptions%>
						</select>
						<select name="sortorder" size=1 onchange="setSort();">
<%	if (HiltonUtility.isEmpty(sortedOrder)) {%>
						<option value="" selected></option>
<%	}%>
						<option value="ASC" <% if (sortedOrder.equals("ASC")) {%>selected<%}%>>ASC</option>
						<option value="DESC" <%	if (sortedOrder.equals("DESC")) {%>selected<%}%>>DESC</option>
						</select>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<div style="width:<%=formWidth%>">
	<div style="valign:top;width:240px;float:left;"><%@ include file="/browse/group_filter_options.jsp" %></div>
	<div style="valign:top;width:*;float:left;"">
		<%@ include file="/browse/item_browse_page_nav.jsp" %>
		<table border=0 cellspacing=0 cellpadding=0 width=100%>
		<tr><td valign=top height=10px><img src="<%=contextPath%>/images/none.gif" border=0 style="background-color:darkgray;" width=100% height=1px></td></tr>
		</table>

		<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=100%>
		<tr>
			<td class=browseHdr align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
				<tr>
<%	for (int i=0; i < labels.length; i++) {
		String alignment = "left";
		int	iwidth = 10;

		if (labels[i].getType().indexOf("Decimal") >= 0) {	alignment = "right";	}

		if (labels[i].getSize() > 0) {	iwidth = labels[i].getSize();	}
%>
					<td height=18px class=browseHdr width=<%=iwidth%>% align=<%=alignment%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labels[i].getLabel(), "")%>&nbsp;</td>
<%	}%>
				</tr>
				</table>

				<div id="noRecords" style="visibility: hidden; display: none;">
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td><b>There were no records found in the database matching your criteria.</b></td></tr>
				</table>
				</div>
<%		for (int il = 0; il < list.size(); il++) {
			Object object = list.get(il);
			List	detailList = new ArrayList();
			List	detailDisplayList = new ArrayList(); // holds details that will always be displayed in a new row (if detail=Y && size=100)
			int	itd = 0;
%>
				<table border=0 cellspacing=0 cellpadding=1 width=100%>
				<tr><td width=100% align=center><img src="<%=contextPath%>/images/none.gif" border=0 height=5px width=100%></td></tr>
				</table>
				<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow id=itemTable<%=il%>>
				<tr>
<%		for (int i = 0; i < browseColumns.length; i++) {
				BrowseColumn column = BrowseUtility.getBrowseColumnCopy(browseColumns[i]);
				Object result = BrowseUtility.getTestColumnValue(column, object, oid, browseColumns, browse.getBrowseId(), uid);

				String alignment = "left";
				int	iwidth = 10;

				if (column.getType().indexOf("Decimal") >= 0) {	alignment = "right";	}
				if (column.getSize() > 0) {	iwidth = column.getSize();	}

				if (column.isHiddenInput()) {
					sbHiddenFields.append("<input type=hidden name=\\" + '"' + column.getColumnName() + "\\" + '"' + " value=\\" + '"' + result + "\\" + '"' + "/>");
				}
				if (column.getLink() != null && column.getLink().length() > 0 && column.getLink().indexOf("#") >= 0) {
					column.setLink(BrowseUtility.testPopulateLinkParameters(column, object, browseColumns));
				}
				if (column.isDetail()) {
					BrowseColumn detailColumn = BrowseUtility.getBrowseColumnCopy(column);
					detailColumn.setValue(result);
					if (iwidth == 100 || detailColumn.getType().equals("ConditionalLink")) {
						detailDisplayList.add(detailColumn);
					} else {
						detailList.add(detailColumn);
					}
				}
				if (!column.isHidden() && !column.isDetail()) {
%>
					<td height=18px class=browseRow align=<%=alignment%> width=<%=iwidth%>% valign=top>
<%					if (column.getLink() != null && column.getLink().length() > 0) {%>
						<div id="<%=column.getColumnName()%>_<%=i%>"><a href="<%=column.getLink()%>"  onclick="javascript: rowSelect='<%=il%>';">
<%						if (column.getColumnName().equals(sortedColumn) && !HiltonUtility.isEmpty(findString) && ((String) result).indexOf(findString) >= 0) {
								findString = "";%>
						<font class=selectedRow><%=column.getLabel()%></font>
<%						} else if (column.getType().equals("ImageLink")) {%>
							<img src="<%=contextPath%>/images/none.gif" id="<%=column.getColumnName()%>_IMG_<%=il%>" border=0 width=<%=(iwidth*450)/100%>px height=<%=(iwidth*450)/100%>px>
<%						} else {%>
							<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, column.getLabel(), "")%>
<%						}%>
						</a></div>
<%					} else {
						if (column.getColumnName().equals(sortedColumn) && !HiltonUtility.isEmpty(findString) && ((String) result).indexOf(findString) >= 0) {
								findString = "";%>
							<font class=selectedRow><%=result%></font>
<%					}  else if ( (createtype.equals("BO") || createtype.equals("SB")) && column.getColumnName().equals("Input_quantity")) {%>
							<div style="valign:top;"><input type="checkbox" name="c_checkbox"><tsa:hidden name="Input_quantity" value=""/></div>
<%					} else {%>
							<%=result%>
<%					}
					}%>
					</td>
<%					itd++;
				}
			}%>
				</tr>
<%
			if (detailDisplayList.size() > 0) {%>
				<tr>
					<td height=18px class=browseRow valign=top colspan=<%=itd%>>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%			for (int idd=0; idd < detailDisplayList.size(); idd++) {
					BrowseColumn column = (BrowseColumn) detailDisplayList.get(idd);
					String columGetLabel = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, column.getLabel(), column.getLabel());
					if (column.getType().equals("ConditionalLink"))
					{
						continue;
					}
					else
					{
%>
						<tr>
<%					if (browseObject.isDetailIncluded()) {
							if (idd > 0) {%>
							<td height=11px width=15px>&nbsp;</td>
<%						} else {%>
							<td height=11px width=15px valign=middle>
								<div id="detailLink<%=il%>"><a href="javascript: viewItemDetails(<%=il%>); void(0);" tabIndex=-1><img src="<%=contextPath%>/images/plus.gif" border=0 class=processOnImg alt="View More Item Details"></a></div>
							</td>
<%						}
						}	%>
							<td height=18px class=browseRow valign=top>
								<b><%=columGetLabel%>:</b>&nbsp;<%=column.getValue()%>
							</td>
						</tr>
<%			}
			}	%>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr id="conditionalLinksRow">
							<td height=11px width=15px>&nbsp;</td>
<%			for (int idd=0; idd < detailDisplayList.size(); idd++) {
					BrowseColumn column = (BrowseColumn) detailDisplayList.get(idd);
					String columGetLabel = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, column.getLabel(), column.getLabel());
					if ( !column.getType().equals("ConditionalLink") )
					{
						continue;
					}
					else
					{
%>
							<td height=18px class=browseRow valign=top>
								<%					if (column.getLink() != null && column.getLink().length() > 0) {
						if (column.getType().equals("ConditionalLink")) {%>
						<div id="browse_<%=column.getColumnName()%>"><a href="<%=column.getLink()%>" onclick="rowSelect='<%=il%>';"><%=columGetLabel%></a></div>
<%						} else {%>
						<a href="<%=column.getLink()%>"><%=columGetLabel%></a>
<%						}
					} else {%>
						<b><%=columGetLabel%>:</b>&nbsp;<%=column.getValue()%>
<%					}%>
							</td>

<%			}
			}	%>
						</tr>
						</table>
					</td>
				</tr>
<%		} else if (browseObject.isDetailIncluded()) {%>
				<tr>
					<td height=11px valign=bottom>
						<div id="detailLink<%=il%>"><a href="javascript: viewItemDetails(<%=il%>); void(0);" tabIndex=-1><img src="<%=contextPath%>/images/plus.gif" border=0 class=processOnImg alt="View More Item Details"></a></div>
					</td>
				</tr>
<%		}%>
				</table>

				<div id="itemDetails<%=il%>" style="visibility:hidden; display:none; width:100%;" class=browseRow>
				<hr size=0 width=90%>
					<table border=0 cellspacing=0 cellpadding=0 class=browseRow width=90%>
					<tr class=browseRow>
<%			int itotalWidth = 0;
			for (int i=0; i < detailList.size(); i++) {
				BrowseColumn column = (BrowseColumn) detailList.get(i);
				String columGetLabel = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, column.getLabel(), column.getLabel());
				int	iwidth = 10;

				if (column.getSize() > 0) {	iwidth = column.getSize();	}

				itotalWidth = itotalWidth + iwidth;

				if (iwidth >= 100) {
					itotalWidth = 0;
%>
					</tr>
					<tr class=browseRow>
						<td colspan=<%=detailList.size()%> height=18px class=browseRow width=<%=iwidth%>% valign=top>
						<b><%=columGetLabel%>:</b>&nbsp;<%=column.getValue()%>
						</td>
					</tr>
					<tr class=browseRow>
<%				} else {%>
						<td height=18px class=browseRow width=<%=iwidth%>% valign=top>
<%					if (column.getLink() != null && column.getLink().length() > 0) {
						if (column.getType().equals("ConditionalLink")) {%>
						<div id="browse_<%=column.getColumnName()%>"><a href="<%=column.getLink()%>" onclick="rowSelect='<%=il%>';"><%=columGetLabel%></a></div>
<%						} else {%>
						<a href="<%=column.getLink()%>"><%=columGetLabel%></a>
<%						}
					} else {%>
						<b><%=columGetLabel%>:</b>&nbsp;<%=column.getValue()%>
<%					}%>
						</td>
<%				}
				if (itotalWidth >= 100) {
					itotalWidth = 0;
%>
					</tr>
					<tr class=browseRow>
<%				}
			}%>
					</tr>
					</table>
					</div>
					<div class=browseRow style="height:1px">
					<!--<hr class=browseHR>-->
						<table border=0 cellpadding=0 cellspacing=0 width=100%>
						<tr><td width=100% align=center><img src="<%=contextPath%>/images/none.gif" border=0 class=browseHdr height=2px width=100%></td></tr>
						</table>
					</div>
<%	}%>
			</td>
		</tr>
		<tr>
			<td id="originalFilterFields"><%=sbOriginalFilterFields%></td>
			<td id="filterFields"><%=sbFilterFields%></td>
		</tr>
		</table>

		<hr size=0>

		<%@ include file="/browse/item_browse_page_nav.jsp" %>

		<br>
			<div id="resetOriginalOption" style="visibility: visible; display=block;">
			<table align=center border=0 cellpadding=2 cellspacing=1>
				<tr>
					<td nowrap><input id="resetOriginalButton" type=button name="reset" value="Reset Original" onClick="resetOriginal();">&nbsp;</td>
				</tr>
			</table>
			</div>
			<div id="resetOption" style="visibility: hidden; display=none;">
			<table align=center border=0 cellpadding=2 cellspacing=1>
				<tr>
					<td nowrap><input id="resetButton" type=button name="reset" value="Reset" onClick="resetMe();">&nbsp;</td>
				</tr>
			</table>
			</div>
		<br>

		<table cellSpacing=0 cellPadding=0 width=100% border=0>
		<tr>
<%	if (irows > 0) {%>
			<td align=center><div id="pxbutton"><a href="javascript: returnItems(); void(0);" border=0 tabIndex=900>Ok</a></div></td>
<%	}%>
			<td align=center><div id="pxbutton"><a href="javascript: returnAbort(); void(0);" border=0 tabIndex=910>Cancel</a></td>
		</tr>
		</table>

	</div>
</div>

<div id="resetOption222" style="visibility: hidden; display=none;"></div>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var browser = browserCheck();
	var browseName = "${esapi:encodeForJavaScript(browse.browseName)}";
	var totalRows = <%=irows%>;
	var currentRows = <%=(browse.getRowEnd() - browse.getRowStart()) + 1%>;
	var pageSize = <%=ipageSize%>;
	var pageCount = <%=ipageCount%>;
	var sortedColumn = "<%=sortedColumn%>";
	var totalGroups = nformat(eval((pageCount/5) + .4), 0);
	var rowSelect = 0;
	var hideConditionalLinksRow = true;

<%	if (itemtype.equals("CAT")) {%>
	//var lookupHandler = "BrowseSetInputRequestValues;CatalogItemLookup";
	var lookupHandler = "BrowseSetInputRequestValues;KitItemLookupByCatalog";
<%	} else if (itemtype.equals("INV")) {%>
	var lookupHandler = "BrowseSetInputRequestValues;InvItemLookup";
<%	} else if (itemtype.equals("REQ")) {%>
	var lookupHandler = "BrowseSetInputRequestValues;RequisitionItemLookup";
<%	} else if (itemtype.equals("DO")) {%>
	var lookupHandler = "BrowseSetInputRequestValues;PoItemLookup";
<%	} %>
	var pg = "/admin/catalog/cat_item_kits.jsp";
	var handlers = lookupHandler + ";KitItemRetrieveByParent";

	currentPage = <%=browse.getCurrentPage()%>;

	setHiddenFields("<%=sbHiddenFields%>");
	browseDisplaySetup();
	setSelectedSort();
	setConditionalLinks();

	hideArea("resetOriginalOption");

	function browseDisplaySetup() {
		if (totalRows <= 0) {
			displayArea("noRecords");
			hideArea("pageLink1");
		} else {
			if (pageCount > 1) {
				var temp = new String(currentPage / 5);
				var ind = temp.indexOf(".");
				var group = 1;
				if (ind >= 0) {
					temp = temp.substring(0, ind);
					group = eval(temp) + 1;
				} else {
					group = eval(temp);
				}
				navigateGroup(new String(group));
				if (currentPage != 1) {
					var pg = currentPage;
					var pageLink = "<a href=\"javascript: pageMe(1);\">1</a>";
					var ibegin = 1 + ((pg - 1) * pageSize);
					var iend = (pg * pageSize);

					setInnerText("beginRecord", ibegin);
					setInnerText("endRecord", iend);
					setInnerHTML("pageLink1", pageLink);
					setInnerHTML("pageLink" + pg, "<b>" + pg + "</b>");

					frm.go_to_pg.value = "";

					currentPage = pg;
					setNewPageOptions();
				}
			} else {
				hideArea("pageLink1");
			}
		}
	}

	function setSelectedSort() {
		if (sortedColumn.length > 0) {
			frm.sortcolumn.value = sortedColumn;
		}
	}

	function setConditionalLinks() {
		if (totalRows > 0)
		{
			for (var i = 0; i < currentRows; i++)
			{
				checkConditionalLinks(i);
			}
		}
	}

	function hideItemDetails(x) {
		setInnerHTML("detailLink" + x, "<a href='javascript: viewItemDetails(" + x + "); void(0);'><img src=\"<%=contextPath%>/images/plus.gif\" border=0 class=processOnImg alt=\"View More Item Details\"></a>");
		hideArea("itemDetails" + x);
	}

	function viewItemDetails(x) {
		setInnerHTML("detailLink" + x, "<a href='javascript: hideItemDetails(" + x + "); void(0);'><img src=\"<%=contextPath%>/images/minus.gif\" border=0 class=processOnImg alt=\"Hide Item Details\"></a>");
		//checkConditionalLinks(x);
		displayArea("itemDetails" + x);
	}

	function returnItems()
	{
		var all_ok = false;
		var is_checkbox = false;

		for (var a = 0; a < frm.elements.length; a++) {

			if ((frm.elements[a].name == 'Input_quantity') && (frm.elements[a].type == 'text') && (frm.elements[a].value != '')) {
				all_ok = true;
			} else{ if ((frm.elements[a].name == 'c_checkbox') && (frm.elements[a].checked))
				all_ok = true;
				is_checkbox = true;
			}

			if (all_ok) {
				break;
			}
		}

		if(all_ok){
	<%	if (itemtype.equals("REQ")) {%>
			returnReqItems();
	<%	} else {%>

		var hiddenFields = "";
		var qtyElements = frm.elements.item("Input_quantity");
		var chkboxElements = frm.elements.item("c_checkbox");

		if (qtyElements.length > 1) {
			for (var i=0; i < qtyElements.length; i++) {
				var qty = qtyElements(i).value;

				if (chkboxElements) {
					if (chkboxElements.length > 1)
					{
						if (chkboxElements(i).checked)
						{
							qty = "Y";
						}
					}
				}

				if (qty > 0 || qty == "Y") {
					if (qty == "Y") {
						qty = "0";
					}
<%			if (itemtype.equals("CAT")) { %>
					var catalogId = frm.elements.item("CatalogItem_id_catalogId")(i).value;
					var itemNumber = frm.elements.item("CatalogItem_id_itemNumber")(i).value;

					hiddenFields = hiddenFields + "<input type=hidden name=\"KitItem_childCatalogId\" value=\"" + catalogId + "\"/>";
					hiddenFields = hiddenFields + "<input type=hidden name=\"KitItem_childItemNumber\" value=\"" + itemNumber + "\"/>";
					hiddenFields = hiddenFields + "<input type=hidden name=\"KitItem_childQuantity\" value=\"" + qty + "\"/>";
<%			} %>
				}
			}
		} else {
			var qty = qtyElements.value;

			if (qty > 0) {
<%			if (itemtype.equals("CAT")) { %>
				var catalogId = frm.elements.item("CatalogItem_id_catalogId").value;
				var itemNumber = frm.elements.item("CatalogItem_id_itemNumber").value;

				hiddenFields = hiddenFields + "<input type=hidden name=\"KitItem_childCatalogId\" value=\"" + catalogId + "\"/>";
				hiddenFields = hiddenFields + "<input type=hidden name=\"KitItem_childItemNumber\" value=\"" + itemNumber + "\"/>";
				hiddenFields = hiddenFields + "<input type=hidden name=\"KitItem_childQuantity\" value=\"" + qty + "\"/>";
<%			} %>
			}
		}

		setHiddenFields(hiddenFields);
		doSubmit(pg, handlers + ';BrowseCleanupByBrowseId');

	<%	}%>
		}else{
			if(is_checkbox){
				alert("Please select an item.");
			}else{
				alert("Please enter the number of items.");
			}
		}

	}

	function returnAbort() {
		var hiddenFields = "";

		var pg = "/admin/catalog/cat_item_kits.jsp";
		var handlers = "KitItemRetrieveByParent";
		doSubmit(pg, handlers + ';BrowseCleanupByBrowseId');
	}

	function resetOriginal() {
		doSubmit('browse/item_filter_options.jsp', 'CatalogRetrieveActive');
	}

	function browse(x) {
		frm.browseName.value = x;

		doSubmit('browse/item_browse.jsp', 'BrowseRetrieve');
	}

	function setSort() {
		var colname = frm.sortcolumn[frm.sortcolumn.selectedIndex].value;
		var sortOrder = frm.sortorder[frm.sortorder.selectedIndex].value;

		sortMeOrder(colname, sortOrder);
	}

	function checkConditionalLinks(x) {
		hideConditionalLinksRow = true;
		var links;

		if (browser == "NS6") {
			links = document.getElementById("browse_InvItem_viewInvKitItems");
		} else {
			links = document.all("browse_InvItem_viewInvKitItems");
		}
		if (links != undefined && links != null) {
			checkInvKitItemLinks(links, x);
		}

		if (browser == "NS6") {
			links = document.getElementById("browse_CatalogItem_viewCatalogKitItems");
		} else {
			links = document.all("browse_CatalogItem_viewCatalogKitItems");
		}
		if (links != undefined && links != null) {
			checkCatalogKitItemLinks(links, x);
		}

		if (browser == "NS6") {
			links = document.getElementById("browse_Link_viewPriceBreaks");
		} else {
			links = document.all("browse_Link_viewPriceBreaks");
		}
		if (links != undefined && links != null) {
			checkPriceBreakLinks(links, x);
		}

		if (browser == "NS6") {
			links = document.getElementById("browse_CatalogItem_viewCatalogImage");
		} else {
			links = document.all("browse_CatalogItem_viewCatalogImage");
		}
		if (links != undefined && links != null) {
			checkCatalogItemImageLinks(links, x);
		}

		if (hideConditionalLinksRow == true)
		{
			var linkRows;
			if (browser == "NS6") {
				linkRows = document.getElementById("conditionalLinksRow");
			} else {
				linkRows = document.all("conditionalLinksRow");
			}

			if (linkRows.length >1)
			{
				linkRows[x].style.visibility = "hidden";
				linkRows[x].style.display = "none";
			}
			else
			{
				linkRows.style.visibility = "hidden";
				linkRows.style.display = "none";
			}
		}
	}

	function checkInvKitItemLinks(links, x) {
		if (links.length > 1) {
			var link = links[x];
			var kit = frm.InvItem_kit[x].value;

			if (kit != "Y") {
				hideLink(link);
			}
			else
			{
				hideConditionalLinksRow = false;
			}
		} else {
			var link = links;
			var kit = frm.InvItem_kit.value;

			if (kit != "Y") {
				hideLink(link);
			}
			else
			{
				hideConditionalLinksRow = false;
			}
		}
	}

	function checkCatalogKitItemLinks(links, x) {
		var kit = "N";
		var link;

		if (links[x]) {
			link = links[x];
		} else {
			link = links;
		}
		if (frm.CatalogItem_kit[x]) {
			kit = frm.CatalogItem_kit[x].value;
		} else if (frm.CatalogItem_kit) {
			kit = frm.CatalogItem_kit.value;
		}

		if (kit != "Y") {
			hideLink(link);
		}
		else
		{
			hideConditionalLinksRow = false;
		}
	}


	function checkPriceBreakLinks(links, x) {
		var priceBrk = "N";
		var link;

		if (links[x]) {
			link = links[x];
		} else {
			link = links;
		}
		if (frm.CatalogItem_priceBrk[x]) {
			priceBrk = frm.CatalogItem_priceBrk[x].value;
		} else if (frm.CatalogItem_priceBrk) {
			priceBrk = frm.CatalogItem_priceBrk.value;
		}

		if (priceBrk != "Y") {
			hideLink(link);
		}
		else
		{
			hideConditionalLinksRow = false;
		}
	}

	function hideLink(link) {
		if (browser == "NS") {
			//The option to hide rows is currently not working in this version of Netscape.
		} else if ( (browser == "IE") || (browser == "NS6") ) {
			link.style.visibility="hidden";
			link.style.display="none";
		} else {
			eval(link + ".style.visibility='hidden'");
			eval(link + ".style.display='none'");
		}
	}

	function viewKitItems(type) {
		var catid = "";
		var itemNumber = "";

		if (type == "INV") {
			var items = document.getElementsByName("InvItem_itemNumber");
			catid = "INV";

			if (items.length > 1) {
				itemNumber = items[rowSelect].value;
			} else {
				itemNumber = items.value;
			}
			popupParameters = "browseName=inv-kititem";
		} else {
			var catalogs = document.getElementsByName("CatalogItem_id_catalogId");
			var items = document.getElementsByName("CatalogItem_id_itemNumber");

			if (items.length > 1) {
				catid = catalogs[rowSelect].value;
				itemNumber = items[rowSelect].value;
			} else {
				catid = catalogs.value;
				itemNumber = items.value;
			}
			popupParameters = "browseName=catalog-kititem";
		}

		popupParameters = popupParameters + ";colname=KitItem_id_parentCatalogId;operator==;filter_txt=" + catid + ";logicalOperator=AND;originalFilter=Y;sort=N";
		popupParameters = popupParameters + ";colname=KitItem_id_parentItemNumber;operator==;filter_txt=" + itemNumber + ";logicalOperator=AND;originalFilter=Y;sort=N;";

		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}

	function checkCatalogItemImageLinks(links, x) {
		var img = "";
		var link;

		if (links[x]) {
			link = links[x];
		} else {
			link = links;
		}
		if (frm.CatalogItem_imageFile[x]) {
			img = frm.CatalogItem_imageFile[x].value;
		} else if (frm.CatalogItem_imageFile) {
			img = frm.CatalogItem_imageFile.value;
		}

		if (isEmpty(img)) {
			hideLink(link);
		}
		else
		{
			hideConditionalLinksRow = false;
		}
	}

	function viewImage(type) {
		var imgFileName = "";
		var imageFiles;
		var directoryName;
		var items = document.getElementsByName("CatalogItem_itemNumber");
		var imagesUrl = "<%=DictionaryManager.getInstance("host", oid).getProperty("item-image-url", "/")%>";

		if (imagesUrl.substring(imagesUrl.length - 1) != "/") {
			imagesUrl = imagesUrl + "/";
		}

		if (type == "INV") {
			directoryName = "inventory";
			imageFiles = document.getElementsByName("InvItem_imageFile");
		} else {
			var catalogNames = document.getElementsByName("CatalogItem_catalogId");
			directoryName = catalogNames[rowSelect].value;
			imageFiles = document.getElementsByName("CatalogItem_imageFile");
		}
		imagesUrl = imagesUrl + directoryName + "/";
		imgFileName = imageFiles[rowSelect].value;

		if (imgFileName.indexOf("http://") >= 0)
		{
			if (directoryName == "BOISE" || directoryName == "BOISE-WEB" || directoryName == "RESTRICTED")
			{
				var udf2Codes = document.getElementsByName("CatalogItem_udf2Code");
				var udf2 = udf2Codes[rowSelect].value;
				var imgFileName = "http://www.officemaxsolutions.com/cif/viewItem.html?nid=" + udf2;
			}
			doSubmitToPopup(imgFileName, 'DoNothing');
		}
		else
		{
			doSubmitToPopup(imagesUrl + imgFileName, 'DoNothing');
		}
	}

	function viewPriceBreaks(type) {
		var catid = "";
		var itemNumber = "";

		if (type == "INV") {
			var items = document.getElementsByName("InvItem_itemNumber");
			catid = "INV";

			if (items.length > 1) {
				itemNumber = items[rowSelect].value;
			} else {
				itemNumber = items.value;
			}
			popupParameters = "browseName=inv-kititem";
		} else {
			var catalogs = document.getElementsByName("CatalogItem_id_catalogId");
			var items = document.getElementsByName("CatalogItem_id_itemNumber");

			if (items.length > 1) {
				catid = catalogs[rowSelect].value;
				itemNumber = items[rowSelect].value;
			} else {
				catid = catalogs[rowSelect].value;
				itemNumber = items[rowSelect].value;
			}
			popupParameters = "browseName=catalog-pricebrk";
		}

		popupParameters = popupParameters + ";colname=CatalogPriceBrk_id_catalogId;operator==;filter_txt=" + catid + ";logicalOperator=AND;originalFilter=Y;sort=N";
		popupParameters = popupParameters + ";colname=CatalogPriceBrk_id_itemNumber;operator==;filter_txt=" + itemNumber + ";logicalOperator=AND;originalFilter=Y;sort=N;";

		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}

	function resetDisabledFlds() {
		// overwrite resetDisabledFlds on puridiom.js - this functions takes too much time to loop through all fields if there are a lot of records
		//	so for now do not change display
	}

	function viewAdditionalInfo(imageFile, itemNumber) {
		popupParameters = "CatalogItem_imageFile=" + imageFile;
		popupParameters = popupParameters + ";CatalogItem_id_itemNumber=" + itemNumber;
		doSubmitToPopup('admin/catalog/catalog_item_info.jsp', 'DoNothing', 'WIDTH=700px', 'HEIGHT=500px');
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>
