<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.browse.*" %>
<%
	Encoder encoder = DefaultEncoder.getInstance();
	String	formtype = (String) request.getAttribute("formtype");
	String	itemtype = (String) request.getAttribute("as_item_type");
	String	invbrowse = HiltonUtility.ckNull((String) request.getAttribute("invbrowse"));
	String	fromPage = HiltonUtility.ckNull((String) request.getAttribute("fromPage"));
	String	icHeaderName = "";
	String	fiscalyearName = "";
	String	createtypeName = "";
	String	createsubtypeName = "";
	String	formnumberName = "";
	String	amendmentNumber = "";
	String buyerCode = "";
	String	revisionNumber = "0";
	String	releaseNumber = "";

	if (itemtype != null) {
		if (itemtype.equals("ALL")) {
			itemtype = "REQ";
		}
	}
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
			buyerCode = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_buyerCode"));
			revisionNumber = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_revisionNumber"));
			releaseNumber = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_releaseNumber"));
		} else if (formtype.equals("DSB")) {
			icHeaderName = "DisbHeader_icDsbHeader";
			fiscalyearName = "DisbHeader_fiscalYear";
			createtypeName = "DisbHeader_disbType";
			formnumberName = "DisbHeader_disbNumber";
		} else if (formtype.equals("CAT")){
			revisionNumber = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_revisionNumber"));
		}
	}
	if (HiltonUtility.isEmpty(revisionNumber)){
		revisionNumber = "0";
	}
	if (icHeaderName.length() == 0) {
		icHeaderName = "icHeader";
	}

	String	icHeader = (String) request.getAttribute(icHeaderName);
	String	fiscalyear = (String) request.getAttribute(fiscalyearName);
	String	createtype = (String) request.getAttribute(createtypeName);
	String	createsubtype = (String) request.getAttribute(createsubtypeName);
	String	formnumber = (String) request.getAttribute(formnumberName);
	String	listinactive = HiltonUtility.ckNull((String) request.getAttribute("item_inactive"));


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

	labelsMap.put("CatalogItem_udf1Code", "cat-LN01");
	labelsMap.put("CatalogItem_udf2Code", "cat-LN02");
	labelsMap.put("CatalogItem_udf3Code", "cat-LN03");
	labelsMap.put("CatalogItem_udf4Code", "cat-LN04");
	labelsMap.put("CatalogItem_udf5Code", "cat-LN05");

	if (filters != null && filters.size() > 0) {
		BrowseColumn[] cols = browseObject.getBrowseColumns();

		for (int ix=0; ix < filters.size(); ix++) {
			BrowseFilter filter = (BrowseFilter) filters.get(ix);
			String	colname = filter.getColumnName().replace('.', '_');
			String	sort = filter.getSort();
			StringBuffer sb = new StringBuffer("");

			sb.append("<input type=hidden name=" + '"' + "colname" + '"' + " value=" + '"' + colname + '"' + ">");
			sb.append("<input type=hidden name=" + '"' + "filter_txt" + '"' + " value=" + '"' + headerEncoder.encodeForJavaScript(filter.getValue()) + '"' + ">");
			sb.append("<input type=hidden name=" + '"' + "operator" + '"' + " value=" + '"' + filter.getOperator() + '"' + ">");
			sb.append("<input type=hidden name=" + '"' + "logicalOperator" + '"' + " value=" + '"' + filter.getLogicalOperator() + '"' + ">");
			sb.append("<input type=hidden name=" + '"' + "sort" + '"' + " value=" + '"' + filter.getSort() + '"' + ">");

			if (sort != null && !sort.equalsIgnoreCase("N") && sortedColumn.length() == 0) {
				sortedColumn = colname;
				sortedOrder = sort;
			}
			if (filter.isOriginalFilter()) {
				sb.append("<input type=hidden name=" + '"' + "originalFilter" + '"' + " value=" + '"' + "Y" + '"' + ">");

				sbOriginalFilterFields.append(sb);

				if (filter.getValue() != null && filter.getValue().length() > 0) {
					if (sbOriginalFilterText.length() > 0) {
						sbOriginalFilterText.append(filter.getLogicalOperator() + " ");
					}
					sbOriginalFilterText.append(DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, (String) labelsMap.get(colname), "") + " " + filter.getOperator() + " '" + filter.getValue() + "' ");
				}
			} else {
				sb.append("<input type=hidden name=" + '"' + "originalFilter" + '"' + " value=" + '"' + "N" + '"' + ">");

				sbFilterFields.append(sb);

				if (filter.getValue() != null && filter.getValue().length() > 0) {
					if (sbFilterText.length() > 0) {
						sbFilterText.append(filter.getLogicalOperator() + " ");
					}
					sbFilterText.append(DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, (String) labelsMap.get(colname), "") + " " + filter.getOperator() + " '" + filter.getValue() + "' ");
				}
			}
		}
	}

	for (int i=0; i < labels.length; i++) {
		BrowseColumn column = labels[i];
		String columGetLabel = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, column.getLabel(), "");

		if ( !columGetLabel.equalsIgnoreCase("") && column.getLabel() != null && column.getLabel().length() > 0 && !column.getType().equals("Input") && !column.getClassName().equals("Input") && !column.getType().equals("Checkbox")) {
			if (column.getColumnName().equals(sortedColumn)) {
				sbSortOptions.append("<option value=" + '"' + column.getColumnName() + '"' + " selected" + ">" + columGetLabel + "</option>");
			} else {
				sbSortOptions.append("<option value=" + '"' + column.getColumnName() + '"' + ">" + columGetLabel + "</option>");
			}
		}
	}
%>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<tsa:hidden name="browsePage" value="/browse/item_browse.jsp"/>
<tsa:hidden name="browseId" value="<%=browse.getBrowseId()%>"/>
<tsa:hidden name="newPage" value="1"/>
<tsa:hidden name="pageSize" value=""/>
<tsa:hidden name="go_to_pg" value=""/>
<tsa:hidden name="as_item_type" value="<%=itemtype%>"/>
<tsa:hidden name="Item_Inactive" value="<%=listinactive%>"/>
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
<tsa:hidden name="PoHeader_buyerCode" value="<%=buyerCode%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=revisionNumber%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=releaseNumber%>"/>
<tsa:hidden name="createAction" value="SAVE"/>
<tsa:hidden name="invbrowse" value="<%=invbrowse%>"/>
<tsa:hidden name="fromPage" value="<%=fromPage%>"/>
<% if (oid.equalsIgnoreCase("bly07p")) {%>
<tsa:hidden name="userNameUdf1" value="<%=user.getDepartment()%>"/>
<% } %>
<% if (oid.equalsIgnoreCase("hoy08p")) {%>
<tsa:hidden name="userNameUdf1" value="<%=user.getNameUdf2()%>"/>
<tsa:hidden name="userNameUdf2" value="<%=user.getNameUdf3()%>"/>
<tsa:hidden name="userNameUdf3" value="<%=user.getNameUdf4()%>"/>
<% } %>
<% if (oid.equalsIgnoreCase("wpc08p")) {%>
<tsa:hidden name="userNameUdf1" value="<%=user.getDepartment()%>"/>
<tsa:hidden name="userNameUdf2" value="<%=user.getNameUdf2()%>"/>
<tsa:hidden name="userNameUdf4" value="0000"/>
<% } %>
<%	if (oid.equalsIgnoreCase("ttr09p")) {	%>
<tsa:hidden name="userNameUdf2" value="<%=user.getShipToCode()%>"/>
<tsa:hidden name="userNameUdf3" value="<%=user.getCostCenter()%>"/>
<% } %>

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
	<div style="valign:top;width:<%=Integer.parseInt(formWidth.replace("px", "")) - 240%>px;float:left;"">
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

		if ( (createtype.equals("BO") || createtype.equals("SB")) && labels[i].getLabel().equals("brw-quantity"))
		{
			labels[i].setLabel("brw-select");
		}

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
				<% if(listinactive == "1")
				{%>
					<tr><td><b>We have excess inventory on this part; a purchase order can't be placed at this time.  For questions or concerns, please see John White or Bob Vrolijk.</b></td></tr>
				<%}%>
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
					sbHiddenFields.append("<input type=hidden name=\\" + '"' + column.getColumnName() + "\\" + '"' + " value=\\" + '"' + result + "\\" + '"' + ">");
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
	if(oid.equalsIgnoreCase("wpc08p")){
		if (column.getColumnName().equals(sortedColumn) && !HiltonUtility.isEmpty(findString) && ((String) result).indexOf(findString) >= 0) {
				findString = "";%>
			<font class=selectedRow><%=result%></font>
		<% }  else if ( createtype.equals("SB") && column.getColumnName().equals("Input_quantity")) {%>
			<div style="valign:top;"><input type="checkbox" name="c_checkbox"><tsa:hidden name="Input_quantity" value=""/></div>
		<% } else {%>
			<%=result%>
		<% }
	}
	else {
		if (column.getColumnName().equals(sortedColumn) && !HiltonUtility.isEmpty(findString) && ((String) result).indexOf(findString) >= 0) {
			findString = "";%>
			<font class=selectedRow><%=result%></font>
		<% }  else if ( (createtype.equals("BO") || createtype.equals("SB")) && column.getColumnName().equals("Input_quantity")) {%>
			<div style="valign:top;"><input type="checkbox" name="c_checkbox"><tsa:hidden name="Input_quantity" value=""/></div>
		<% } else {%>
			<%=result%>
		<% }
	}
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
			//details fields
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
                                          <% if(oid.equalsIgnoreCase("vse06p") &&
                                                columGetLabel.equalsIgnoreCase(DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "brw-qtyAvailable", "")) ) { %>
  						  <tsa:hidden name="QtyAvailable" value="<%=column.getValue()%>"/>
						<% } %>

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
	var typeIsInventoryRequest =  false;

<%	if (itemtype.equals("CAT")) {%>
	var lookupHandler = "BrowseSetInputRequestValues;CatalogItemLookup";
<%	} else if (itemtype.equals("INV")) {%>
	var lookupHandler = "BrowseSetInputRequestValues;InvItemLookup";
<%	} else if (itemtype.equals("REQ")) {%>
	var lookupHandler = "BrowseSetInputRequestValues;RequisitionItemLookup";
<%	} else if (itemtype.equals("DO")) {%>
	var lookupHandler = "BrowseSetInputRequestValues;PoItemLookup";
<%	} else if (itemtype.equals("CON")) {%>
	var lookupHandler = "BrowseSetInputRequestValues;ConsolidatedItemLookup";
<%	}
	if (formtype.equals("REQ") && s_view.equals("CLASSIC")) {%>
	var pg = "/requests/req_summary.jsp";
	var handlers = lookupHandler + ";RequisitionRetrieve";
<%	} else if (formtype.equals("REQ") && s_view.equals("WIZARD")) {%>
	var pg = "/requests/req_items.jsp";
	var handlers = lookupHandler + ";RequisitionLineRetrieveByHeader";
<%	} else if (formtype.equals("RFQ") && s_view.equals("CLASSIC")) {%>
	var pg = "/rfq/rfq_summary.jsp";
	var handlers = lookupHandler + ";RfqRetrieve";
<%	} else if (formtype.equals("RFQ") && s_view.equals("WIZARD")) {%>
	var pg = "/rfq/rfq_items.jsp";
	var handlers = lookupHandler + ";RfqLineRetrieveByHeader";
<%	} else if (formtype.equals("PO") && s_view.equals("CLASSIC")) { %>
	var pg = "/orders/po_summary.jsp";
	var handlers = lookupHandler + ";PoRetrieve";
<%	} else if (formtype.equals("PO") && s_view.equals("WIZARD")) { %>
	var pg = "/orders/po_items.jsp";
	var handlers = lookupHandler + ";PoLineRetrieveByHeader";
<%	} else if (formtype.equals("DSB") && s_view.equals("CLASSIC")) { %>
	var pg = "/inventory/dsb_summary.jsp";
	var handlers = lookupHandler + ";DisbursementRetrieve";
<%	} else if (formtype.equals("DSB") && s_view.equals("WIZARD")) { %>
	var pg = "/inventory/dsb_items.jsp";
	var handlers = lookupHandler + ";DisbLineRetrieveByHeader";
<%	} %>

	currentPage = <%=browse.getCurrentPage()%>;

	setHiddenFields("<%=sbHiddenFields%>");
	browseDisplaySetup();
	setSelectedSort();
	setConditionalLinks();

	hideArea("resetOriginalOption");

	function browseDisplaySetup() {
		if (totalRows <= 0) {
			var iteminactive = frm.Item_Inactive.value;
			displayArea("noRecords");
			hideArea("pageLink1");
			if(iteminactive == "1")
			{
				alert("We have excess inventory on this part; a purchase order can't be placed at this time.  For questions or concerns, please see John White or Bob Vrolijk");
			}
		} else {
			if (pageCount > 1) {
				var temp = new String(currentPage / 5);
				var ind = temp.indexOf(".");
				var group = 1;
				if (ind >= 0) {
					temp = temp.substring(0, ind);
					group = parseInt(temp) + 1;
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
			var setCatItemImages = false;
			var setConsolidatedItemImages = false;
			var links = null;

			if (browser == "NS6") {
				links = document.getElementById("CatalogItem_viewImage_IMG_0");
			} else {
				links = document.all("CatalogItem_viewImage_IMG_0");
			}
			if (links != undefined && links != null) {
				setCatItemImages = true;
			} else {
				if (browser == "NS6") {
					links = document.getElementById("ConsolidatedItem_viewImage_IMG_0");
				} else {
					links = document.all("ConsolidatedItem_viewImage_IMG_0");
				}
				if (links != undefined && links != null) {
					setConsolidatedItemImages = true;
				}
			}

			for (var i = 0; i < currentRows; i++)
			{
				if (totalRows > i)
				{
					checkConditionalLinks(i);
					if (setCatItemImages) {
						displayCatalogItemImages(i);
					} else if (setConsolidatedItemImages) {
						displayConsolidatedItemImages(i);
					}
				}
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
			//var qtyElements = frm.elements.item("Input_quantity");
			var qtyElements = document.getElementsByName("Input_quantity");
			//var chkboxElements = frm.elements.item("c_checkbox");
			var chkboxElements = document.getElementsByName("c_checkbox");

			if (qtyElements.length > 1) {
				for (var i=0; i < qtyElements.length; i++) {
					var qty = qtyElements[i].value;

					if (chkboxElements) {
						if (chkboxElements.length > 1)
						{
							if (chkboxElements[i].checked)
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
						var catalogId = document.getElementsByName("CatalogItem_id_catalogId")[i].value;
						var itemNumber = document.getElementsByName("CatalogItem_id_itemNumber")[i].value;
						var revision = <%=headerEncoder.encodeForJavaScript(revisionNumber)%>;
						<% if (oid.equalsIgnoreCase("bly07p")) {%>
							var ware = document.getElementsByName("XrefCombo_code1")[i].value;
							hiddenFields = hiddenFields + "<input type=hidden name=\"XrefCombo_code1\" value=\"" + ware + "\">";
						<% } %>

						hiddenFields = hiddenFields + "<input type=hidden name=\"PoLine_lineRevNo\" value=\"" + revision + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_catalogId\" value=\"" + catalogId + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_itemNumber\" value=\"" + itemNumber + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
	<%			} else if (itemtype.equals("INV")) { %>
						var itemLocation = document.getElementsByName("InvLocation_id_itemLocation")[i].value;
						var itemNumber = document.getElementsByName("InvItem_itemNumber")[i].value;

						hiddenFields = hiddenFields + "<input type=hidden name=\"InvLocation_itemLocation\" value=\"" + itemLocation + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"InvItem_itemNumber\" value=\"" + itemNumber + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";

	<%				if (formtype.equals("DSB")) { %>
							var binIc = document.getElementsByName("InvBinLocation_icRc")[i].value;
							hiddenFields = hiddenFields + "<input type=hidden name=\"InvBinLocation_icRc\" value=\"" + binIc + "\">";
	<%				}
				} else if (itemtype.equals("DO")) { %>
						var icPoLine = document.getElementsByName("PoLine_icPoLine")[i].value;

						hiddenFields = hiddenFields + "<input type=hidden name=\"PoLine_icPoLine\" value=\"" + icPoLine + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
	<%			} else if (itemtype.equals("CON")) { %>
						var catalogId = document.getElementsByName("ConsolidatedItem_id_catalogId")[i].value;
						var itemLocation = document.getElementsByName("ConsolidatedItem_id_inventoryLocation")[i].value;
						var itemNumber = document.getElementsByName("ConsolidatedItem_id_itemNumber")[i].value;
						var revision = <%=headerEncoder.encodeForJavaScript(revisionNumber)%>;

						hiddenFields = hiddenFields + "<input type=hidden name=\"PoLine_lineRevNo\" value=\"" + revision + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_catalogId\" value=\"" + catalogId + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_itemNumber\" value=\"" + itemNumber + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"InvLocation_itemLocation\" value=\"" + itemLocation + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"InvItem_itemNumber\" value=\"" + itemNumber + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
<%				}	%>
					}
				}
			} else {
				var qty;
				if(isArray(qtyElements)){
					qty = qtyElements[0].value;
				}else{
					qty = qtyElements.value;
				}

	//			if(is_checkbox) {
	//				qty = "0";
	//			}
				if (qty > 0 || is_checkbox) {
	<%			if (itemtype.equals("CAT")) { %>
					var catalogId;
					if(isArray(document.getElementsByName("CatalogItem_id_catalogId"))){
						catalogId = document.getElementsByName("CatalogItem_id_catalogId")[0].value;
					}else{
						catalogId = document.getElementsByName("CatalogItem_id_catalogId").value;
					}
					var itemNumber;
					if(isArray(document.getElementsByName("CatalogItem_id_itemNumber"))){
						itemNumber = document.getElementsByName("CatalogItem_id_itemNumber")[0].value;
					}else{
						itemNumber = document.getElementsByName("CatalogItem_id_itemNumber").value;
					}

					var revision = <%=headerEncoder.encodeForJavaScript(revisionNumber)%>;
					<% if (oid.equalsIgnoreCase("bly07p")) {%>
						var ware;
						if(isArray(document.getElementsByName("XrefCombo_code1"))){
							ware = document.getElementsByName("XrefCombo_code1")[0].value;
						}else{
							ware = document.getElementsByName("XrefCombo_code1").value;
						}
						hiddenFields = hiddenFields + "<input type=hidden name=\"XrefCombo_code1\" value=\"" + ware + "\">";
					<% } %>

					hiddenFields = hiddenFields + "<input type=hidden name=\"PoLine_lineRevNo\" value=\"" + revision + "\">";
					hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_catalogId\" value=\"" + catalogId + "\">";
					hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_itemNumber\" value=\"" + itemNumber + "\">";
					hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
	<%			} else if (itemtype.equals("INV")) { %>
					var itemLocation;
					if(isArray(document.getElementsByName("InvLocation_id_itemLocation"))){
						itemLocation = document.getElementsByName("InvLocation_id_itemLocation")[0].value;
					}else{
						itemLocation = document.getElementsByName("InvLocation_id_itemLocation").value;
					}
					var itemNumber;
					if(isArray(document.getElementsByName("InvItem_itemNumber"))){
						itemNumber = document.getElementsByName("InvItem_itemNumber")[0].value;
					}else{
						itemNumber = document.getElementsByName("InvItem_itemNumber").value;
					}
					hiddenFields = hiddenFields + "<input type=hidden name=\"InvLocation_itemLocation\" value=\"" + itemLocation + "\">";
					hiddenFields = hiddenFields + "<input type=hidden name=\"InvItem_itemNumber\" value=\"" + itemNumber + "\">";
					hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";

	<%				if (formtype.equals("DSB")) { %>
							var binIc = $('[name="InvBinLocation_icRc"]').val();
							hiddenFields = hiddenFields + "<input type=hidden name=\"InvBinLocation_icRc\" value=\"" + binIc + "\">";
	<%				}
					} else if (itemtype.equals("DO")) { %>
					var icPoLine;
					if(isArray(document.getElementsByName("PoLine_icPoLine"))){
						icPoLine = document.getElementsByName("PoLine_icPoLine")[0].value;
					}else{
						icPoLine = document.getElementsByName("PoLine_icPoLine").value;
					}
					hiddenFields = hiddenFields + "<input type=hidden name=\"PoLine_icPoLine\" value=\"" + icPoLine + "\">";
					hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
	<%			} else if (itemtype.equals("CON")) { %>
					var catalogId;
					if(isArray(document.getElementsByName("ConsolidatedItem_id_catalogId"))){
						catalogId = document.getElementsByName("ConsolidatedItem_id_catalogId")[0].value;
					}else{
						catalogId = document.getElementsByName("ConsolidatedItem_id_catalogId").value;
					}
					var itemLocation;
					if(isArray(document.getElementsByName("ConsolidatedItem_id_inventoryLocation"))){
						itemLocation = document.getElementsByName("ConsolidatedItem_id_inventoryLocation")[0].value;
					}else{
						itemLocation = document.getElementsByName("ConsolidatedItem_id_inventoryLocation").value;
					}
					var itemNumber;
					if(isArray(document.getElementsByName("ConsolidatedItem_id_itemNumber"))){
						itemNumber = document.getElementsByName("ConsolidatedItem_id_itemNumber")[0].value;
					}else{
						itemNumber = document.getElementsByName("ConsolidatedItem_id_itemNumber").value;
					}
					var revision = <%=headerEncoder.encodeForJavaScript(revisionNumber)%>;

					hiddenFields = hiddenFields + "<input type=hidden name=\"PoLine_lineRevNo\" value=\"" + revision + "\">";
					hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_catalogId\" value=\"" + catalogId + "\">";
					hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_itemNumber\" value=\"" + itemNumber + "\">";
					hiddenFields = hiddenFields + "<input type=hidden name=\"InvLocation_itemLocation\" value=\"" + itemLocation + "\">";
					hiddenFields = hiddenFields + "<input type=hidden name=\"InvItem_itemNumber\" value=\"" + itemNumber + "\">";
					hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
	<%			} %>
				}
			}

	<%		if (formtype.equals("REQ")) { %>
			hiddenFields = hiddenFields + "<input type=hidden name=\"RequisitionLine_icReqHeader\" value=\"<%=encoder.encodeForHTMLAttribute(icHeader)%>\">";
	<%		} else if (formtype.equals("PO")) { %>
			hiddenFields = hiddenFields + "<input type=hidden name=\"PoLine_icPoHeader\" value=\"<%=encoder.encodeForHTMLAttribute(icHeader)%>\">";
	<%      } else if (formtype.equals("RFQ") ) { %>
			hiddenFields = hiddenFields + "<input type=hidden name=\"RfqLine_icRfqHeader\" value=\"<%=encoder.encodeForHTMLAttribute(icHeader)%>\">";
	<%		} %>

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

	function returnNextItems(xpg)
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
<%			if (itemtype.equals("REQ")) {%>
				returnReqItems();
<%			} else {%>

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
<%					if (itemtype.equals("CAT")) { %>
						var catalogId = frm.elements.item("CatalogItem_id_catalogId")(i).value;
						var itemNumber = frm.elements.item("CatalogItem_id_itemNumber")(i).value;

						var revision = <%=headerEncoder.encodeForJavaScript(revisionNumber)%>;
						<% if (oid.equalsIgnoreCase("bly07p")) {%>
							var ware = frm.elements.item("XrefCombo_code1")(i).value;
							hiddenFields = hiddenFields + "<input type=hidden name=\"XrefCombo_code1\" value=\"" + ware + "\">";
						<% } %>

						hiddenFields = hiddenFields + "<input type=hidden name=\"PoLine_lineRevNo\" value=\"" + revision + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_catalogId\" value=\"" + catalogId + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_itemNumber\" value=\"" + itemNumber + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
<%					} else if (itemtype.equals("INV")) { %>
						var itemLocation = frm.elements.item("InvLocation_id_itemLocation")(i).value;
						var itemNumber = frm.elements.item("InvItem_itemNumber")(i).value;

						hiddenFields = hiddenFields + "<input type=hidden name=\"InvLocation_itemLocation\" value=\"" + itemLocation + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"InvItem_itemNumber\" value=\"" + itemNumber + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";

<%						if (formtype.equals("DSB")) { %>
							var binIc = frm.elements.item("InvBinLocation_icRc")(i).value;
							hiddenFields = hiddenFields + "<input type=hidden name=\"InvBinLocation_icRc\" value=\"" + binIc + "\">";
<%						}
					} else if (itemtype.equals("DO")) { %>
						var icPoLine = frm.elements.item("PoLine_icPoLine")(i).value;

						hiddenFields = hiddenFields + "<input type=hidden name=\"PoLine_icPoLine\" value=\"" + icPoLine + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
<%					} else if (itemtype.equals("CAT")) { %>
						var catalogId = frm.elements.item("ConsolidatedItem_id_catalogId")(i).value;
						var itemLocation = frm.elements.item("ConsolidatedItem_id_itemLocation")(i).value;
						var itemNumber = frm.elements.item("ConsolidatedItem_id_itemNumber")(i).value;
						var revision = <%=headerEncoder.encodeForJavaScript(revisionNumber)%>;

						hiddenFields = hiddenFields + "<input type=hidden name=\"PoLine_lineRevNo\" value=\"" + revision + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_catalogId\" value=\"" + catalogId + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_itemNumber\" value=\"" + itemNumber + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"InvLocation_itemLocation\" value=\"" + itemLocation + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"InvItem_itemNumber\" value=\"" + itemNumber + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
<%					} %>
					}
				}
			} else {
				var qty = qtyElements.value;
				if(is_checkbox)
					qty = "0";

				if (qty > 0 || is_checkbox) {
	<%				if (itemtype.equals("CAT")) { %>
						var catalogId = frm.elements.item("CatalogItem_id_catalogId").value;
						var itemNumber = (frm.elements.item("CatalogItem_id_itemNumber").value).replace("\"", "&quot");
						var revision = <%=headerEncoder.encodeForJavaScript(revisionNumber)%>;
						<% if (oid.equalsIgnoreCase("bly07p")) {%>
							var ware = frm.elements.item("XrefCombo_code1").value;
							hiddenFields = hiddenFields + "<input type=hidden name=\"XrefCombo_code1\" value=\"" + ware + "\">";
						<% } %>
						hiddenFields = hiddenFields + "<input type=hidden name=\"PoLine_lineRevNo\" value=\"" + revision + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_catalogId\" value=\"" + catalogId + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_itemNumber\" value=\"" + itemNumber + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
	<%				} else if (itemtype.equals("INV")) { %>
						var itemLocation = frm.elements.item("InvLocation_id_itemLocation").value;
						var itemNumber = (frm.elements.item("InvItem_itemNumber").value).replace("\"", "&quot");
						hiddenFields = hiddenFields + "<input type=hidden name=\"InvLocation_itemLocation\" value=\"" + itemLocation + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"InvItem_itemNumber\" value=\"" + itemNumber + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";

	<%					if (formtype.equals("DSB")) { %>
							var binIc = frm.elements.item("InvBinLocation_icRc").value;
							hiddenFields = hiddenFields + "<input type=hidden name=\"InvBinLocation_icRc\" value=\"" + binIc + "\">";
	<%					}
					} else if (itemtype.equals("DO")) { %>
					var icPoLine = frm.elements.item("PoLine_icPoLine").value;

					hiddenFields = hiddenFields + "<input type=hidden name=\"PoLine_icPoLine\" value=\"" + icPoLine + "\">";
					hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
	<%				} else if (itemtype.equals("CON")) { %>
						var catalogId = frm.elements.item("ConsolidatedItem_id_catalogId").value;
						var itemLocation = frm.elements.item("ConsolidatedItem_id_inventoryLocation").value;
						var itemNumber = (frm.elements.item("ConsolidatedItem_id_itemNumber").value).replace("\"", "&quot");
						var revision = <%=headerEncoder.encodeForJavaScript(revisionNumber)%>;

						hiddenFields = hiddenFields + "<input type=hidden name=\"PoLine_lineRevNo\" value=\"" + revision + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_catalogId\" value=\"" + catalogId + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_itemNumber\" value=\"" + itemNumber + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"InvLocation_itemLocation\" value=\"" + itemLocation + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"InvItem_itemNumber\" value=\"" + itemNumber + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
	<%				}%>
				}
			}

	<%		if (formtype.equals("REQ")) { %>
			hiddenFields = hiddenFields + "<input type=hidden name=\"RequisitionLine_icReqHeader\" value=\"<%=encoder.encodeForHTMLAttribute(icHeader)%>\">";
	<%		} else if (formtype.equals("PO")) { %>
			hiddenFields = hiddenFields + "<input type=hidden name=\"PoLine_icPoHeader\" value=\"<%=encoder.encodeForHTMLAttribute(icHeader)%>\">";
	<%      } else if (formtype.equals("RFQ") ) { %>
			hiddenFields = hiddenFields + "<input type=hidden name=\"RfqLine_icRfqHeader\" value=\"<%=encoder.encodeForHTMLAttribute(icHeader)%>\">";
	<%		} %>
			setHiddenFields(hiddenFields);
			doSubmit(xpg, lookupHandler + ';BrowseGetNextPageFromDB');
			//doSubmit(xpg, lookupHandler + ';BrowseSetPage');
	<%		}%>
		}else{
			doSubmit(xpg, 'BrowseGetNextPageFromDB');
			//doSubmit(xpg, 'BrowseSetPage');
		}
	}

	function returnReqItems() {
		if (browseName == "requisitionline-for-all") {
			returnReqItemsForReq();
		} else {
		var checkboxes;
		var hiddenFields = "";

		if (browser == "NS6") {
			checkboxes = document.getElementById("c_checkbox");
		} else {
			checkboxes = document.all("c_checkbox");
		}

		if (checkboxes != null) {
			if (checkboxes.length > 1) {
				for (i = 0; i < checkboxes.length; i++) {
					var cbox = checkboxes(i);

					if (cbox.checked == true) {
						var ic = frm.elements.item("RequisitionLine_icReqLine")(i).value;
						var qty = frm.elements.item("RequisitionLine_quantity")(i).value;

						hiddenFields = hiddenFields + "<input type=hidden name=\"RequisitionLine_icReqLine\" value=\"" + ic + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
					}
				}
			} else {
				var cbox = checkboxes;

				if (cbox.checked == true) {
					var ic = frm.elements.item("RequisitionLine_icReqLine").value;
					var qty = frm.elements.item("RequisitionLine_quantity").value;

					hiddenFields = hiddenFields + "<input type=hidden name=\"RequisitionLine_icReqLine\" value=\"" + ic + "\">";
					hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
				}
			}
		}
			if (browseName == "requisitionline-for-purchasing") {
				hiddenFields = hiddenFields + "<input type=hidden name=\"RfqLine_icRfqHeader\" value=\"<%=encoder.encodeForHTMLAttribute(icHeader)%>\">";
			}
			setHiddenFields(hiddenFields);
			doSubmit(pg, handlers + ';BrowseCleanupByBrowseId');
		}
	}

	function returnReqItemsForReq() {
		var qtyElements = frm.elements.item("Input_quantity");
		var hiddenFields = "";

		if (qtyElements.length > 1) {
			for (var i=0; i < qtyElements.length; i++) {
				var qty = qtyElements(i).value;

				if (qty > 0) {
					var ic = frm.elements.item("RequisitionLine_icReqLine")(i).value;

					hiddenFields = hiddenFields + "<input type=hidden name=\"RequisitionLine_icReqLine\" value=\"" + ic + "\">";
					hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
				}
			}
		}
		else {
			var qty = qtyElements.value;

			if (qty > 0) {
				var ic = frm.elements.item("RequisitionLine_icReqLine").value;

				hiddenFields = hiddenFields + "<input type=hidden name=\"RequisitionLine_icReqLine\" value=\"" + ic + "\">";
				hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
			}
		}

		if (browseName == "requisitionline-for-purchasing") {
			hiddenFields = hiddenFields + "<input type=hidden name=\"RfqLine_icRfqHeader\" value=\"<%=encoder.encodeForHTMLAttribute(icHeader)%>\">";
		}

		setHiddenFields(hiddenFields);
		doSubmit(pg, handlers + ';BrowseCleanupByBrowseId');
	}

	function returnAbort() {
		var hiddenFields = "";
<%	if (fromPage.equals("/menu/main_menu.jsp")) {	%>
			var pg = "/menu/main_menu.jsp";
			var handlers = "RequisitionDelete";
<%	}
		else if (formtype.equals("REQ"))
		{
			if (s_view.equals("CLASSIC"))
			{ %>
				var pg = "/requests/req_summary.jsp";
				var handlers = "RequisitionRetrieve";
<%		}
			else if (s_view.equals("WIZARD"))
			{ %>
				var pg = "/requests/req_items.jsp";
				var handlers = "RequisitionLineRetrieveByHeader";
				hiddenFields = "<input type=hidden name=\"RequisitionLine_icReqHeader\" value=\"<%=encoder.encodeForHTMLAttribute(icHeader)%>\">";
<%		}
		}
		else if (formtype.equals("RFQ"))
		{
			if (s_view.equals("CLASSIC"))
			{ %>
				var pg = "/rfq/rfq_summary.jsp";
				var handlers = "RfqRetrieve";
<%		}
			else if (s_view.equals("WIZARD"))
			{ %>
				var pg = "/rfq/rfq_items.jsp";
				var handlers = "RfqLineRetrieveByHeader";
				hiddenFields = "<input type=hidden name=\"RfqLine_icRfqHeader\" value=\"<%=encoder.encodeForHTMLAttribute(icHeader)%>\">";
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
				hiddenFields = "<input type=hidden name=\"PoLine_icPoHeader\" value=\"<%=encoder.encodeForHTMLAttribute(icHeader)%>\">";
<%		}
		} %>

		setHiddenFields(hiddenFields);
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
		if( colname )
		{
			sortMeOrder(colname, sortOrder);
		}
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
			links = document.getElementById("browse_ConsolidatedItem_viewCatalogKitItems");
		} else {
			links = document.all("browse_ConsolidatedItem_viewCatalogKitItems");
		}
		if (links != undefined && links != null) {
			checkConsolidatedKitItemLinks(links, x);
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

		if (browser == "NS6") {
			links = document.getElementById("browse_ConsolidatedItem_viewCatalogImage");
		} else {
			links = document.all("browse_ConsolidatedItem_viewCatalogImage");
		}
		if (links != undefined && links != null) {
			checkConsolidatedItemImageLinks(links, x);
		}

		if (hideConditionalLinksRow == true) {
			var linkRows;
			if (browser == "NS6") {
				linkRows = document.getElementById("conditionalLinksRow");
			} else {
				linkRows = document.all("conditionalLinksRow");
			}

			if (linkRows != undefined) {
				if (linkRows.length > 1) {
					linkRows[x].style.visibility = "hidden";
					linkRows[x].style.display = "none";
				}
				else {
					linkRows.style.visibility = "hidden";
					linkRows.style.display = "none";
				}
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


	function checkConsolidatedKitItemLinks(links, x) {
		var kit = "N";
		var link;

		if (links[x]) {
			link = links[x];
		} else {
			link = links;
		}
		if (frm.ConsolidatedItem_kit[x]) {
			kit = frm.ConsolidatedItem_kit[x].value;
		} else if (frm.ConsolidatedItem_kit) {
			kit = frm.ConsolidatedItem_kit.value;
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
<%	if (itemtype.equals("CAT")) {%>
		if (frm.CatalogItem_priceBrk[x]) {
			priceBrk = frm.CatalogItem_priceBrk[x].value;
		} else if (frm.CatalogItem_priceBrk) {
			priceBrk = frm.CatalogItem_priceBrk.value;
		}
<%	} else if (itemtype.equals("CON")) {%>
		if (frm.ConsolidatedItem_priceBrk[x]) {
			priceBrk = frm.ConsolidatedItem_priceBrk[x].value;
		} else if (frm.ConsolidatedItem_priceBrk) {
			priceBrk = frm.ConsolidatedItem_priceBrk.value;
		}
<%	}%>
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
		var items;
		var catalogs;
		var kitBrowseName = "";

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

		if (type == "INV") {
			items = document.getElementsByName("InvItem_itemNumber");
		} else if (type == "CON") {
			var sourceFlds = document.getElementsByName("ConsolidatedItem_source");

			if (sourceFlds.length > 1) {
				source = sourceFlds[rowSelect].value;
			} else {
				source = sourceFlds.value;
			}
			if (source == "INV") {
				kitBrowseName = "inv-kititem";
				type == "INV"
			} else {
				kitBrowseName = "catalog-kititem";
				type == "CAT"
			}

			catalogs = document.getElementsByName("ConsolidatedItem_id_catalogId");
			items = document.getElementsByName("ConsolidatedItem_id_itemNumber");
		} else {
			catalogs = document.getElementsByName("CatalogItem_id_catalogId");
			items = document.getElementsByName("CatalogItem_id_itemNumber");
		}

		if (type == "INV") {
			catid = "INV";
			kitBrowseName = "inv-kititem";
		} else {
			kitBrowseName = "catalog-kititem";
			if (catalogs.length > 1) {
				catid = catalogs[rowSelect].value;
			} else {
				catid = catalogs.value;
			}
		}

		if (items.length > 1) {
			itemNumber = items[rowSelect].value;
		} else {
			itemNumber = items.value;
		}

		popupParameters = "browseName=" + kitBrowseName;
		popupParameters = popupParameters + ";colname=KitItem_id_parentCatalogId;operator==;filter_txt=" + catid + ";logicalOperator=AND;originalFilter=Y;sort=N";
		popupParameters = popupParameters + ";colname=KitItem_id_parentItemNumber;operator==;filter_txt=" + itemNumber + ";logicalOperator=AND;originalFilter=Y;sort=N;";

		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}


	function getInnerHTML(areaName) {
		var text = "";
		if (browser == "NS6") {
			var d = document.getElementById(areaName);
			if (d.length > 1) {
				for (var i=0; i < d.length; i++) {
					text = d[i].innerHTML;
				}
			} else {
				text = d.innerHTML;
			}
		} else {
			var d = document.all(areaName);

			if (d.length > 1) {
				for (var i=0; i < d.length; i++) {
					text = d(i).innerHTML;
				}
			} else {
				text = d.innerHTML;
			}
		}
		return text;
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

	function checkConsolidatedItemImageLinks(links, x) {
		var img = "";
		var link;

		if (links[x]) {
			link = links[x];
		} else {
			link = links;
		}
		if (frm.ConsolidatedItem_imageFile[x]) {
			img = frm.ConsolidatedItem_imageFile[x].value;
		} else if (frm.ConsolidatedItem_imageFile) {
			img = frm.ConsolidatedItem_imageFile.value;
		}

		if (isEmpty(img)) {
			hideLink(link);
		}
		else {
			hideConditionalLinksRow = false;
		}
	}

	function displayCatalogItemImages(x) {
		var imagesUrl = "<%=DictionaryManager.getInstance("host", oid).getProperty("item-image-url", "/")%>";
	    var imagesUrlFolder = "<%=DictionaryManager.getInstance("host", oid).getProperty("item-image-url-folder", "/")%>";
	    //This is not necessary since IE need full path an not only the folder
		//if (browser == "IE") {
			//imagesUrl = imagesUrlFolder;
		//}

		if (imagesUrl.substring(imagesUrl.length - 1) != "/") {
			imagesUrl = imagesUrl + "/";
		}


		var catalogNames = document.getElementsByName("CatalogItem_id_catalogId");
		directoryName = catalogNames[x].value;
		imageFiles = document.getElementsByName("CatalogItem_imageFile");
		imagesUrl = imagesUrl + directoryName + "/";
		imgFileName = imageFiles[x].value;

		if (imgFileName.indexOf("http://") >= 0) {
			if (directoryName == "BOISE" || directoryName == "BOISE-WEB" || directoryName == "RESTRICTED") {
				var udf2Codes = document.getElementsByName("CatalogItem_udf2Code");
				var udf2 = udf2Codes[x].value;
				imgFileName = "http://www.officemaxsolutions.com/cif/viewItem.html?nid=" + udf2;
			}
		}
		else if (isEmpty(imgFileName)) {
			imgFileName = "<%=contextPath%>/images/no_image_available.gif";
		}
		else {
			imgFileName = imagesUrl + imgFileName;
		}
		var areaName = "CatalogItem_viewImage_IMG_" + x;
		document.getElementById(areaName).src = imgFileName;
	}

	function displayConsolidatedItemImages(x) {
		var imagesUrl = "<%=DictionaryManager.getInstance("host", oid).getProperty("item-image-url", "/")%>";
	    var imagesUrlFolder = "<%=DictionaryManager.getInstance("host", oid).getProperty("item-image-url-folder", "/")%>";
	    //This is not necessary since IE need full path an not only the folder
		//if (browser == "IE") {
		//	imagesUrl = imagesUrlFolder;
		//}

		if (imagesUrl.substring(imagesUrl.length - 1) != "/") {
			imagesUrl = imagesUrl + "/";
		}

		var catalogNames = document.getElementsByName("ConsolidatedItem_id_catalogId");
		directoryName = catalogNames[x].value;
		imageFiles = document.getElementsByName("ConsolidatedItem_imageFile");
		imagesUrl = imagesUrl + directoryName + "/";
		imgFileName = imageFiles[x].value;

		if (isEmpty(imgFileName)) {
			imgFileName = "<%=contextPath%>/images/no_image_available.gif";
		}
		else {
			imgFileName = imagesUrl + imgFileName;
		}
		var areaName = "ConsolidatedItem_viewImage_IMG_" + x;

		document.getElementById(areaName).src = imgFileName;
	}

	function viewImage(type) {
		var imgFileName = "";
		var imageFiles;
		var directoryName;
		var items = document.getElementsByName("CatalogItem_id_itemNumber");
		var imagesUrl = "<%=DictionaryManager.getInstance("host", oid).getProperty("item-image-url", "/")%>";

		if (imagesUrl.substring(imagesUrl.length - 1) != "/") {
			imagesUrl = imagesUrl + "/";
		}

		if (type == "INV") {
			directoryName = "inventory";
			imageFiles = document.getElementsByName("InvItem_imageFile");
		} else if (type == "CON") {
			var sourceFlds = document.getElementsByName("ConsolidatedItem_source");
			var source = sourceFlds[rowSelect].value;
			if (source == "INV") {
				directoryName = "inventory";
			} else {
				var catalogNames = document.getElementsByName("ConsolidatedItem_id_catalogId");
				directoryName = catalogNames[rowSelect].value;
			}
			imageFiles = document.getElementsByName("ConsolidatedItem_imageFile");
		} else {
			var catalogNames = document.getElementsByName("CatalogItem_id_catalogId");
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
		} else if (type == "CON") {
			var catalogs = document.getElementsByName("ConsolidatedItem_id_catalogId");
			var items = document.getElementsByName("ConsolidatedItem_id_itemNumber");
			var sourceFlds = document.getElementsByName("ConsolidatedItem_source");
			var source = "";

			if (items.length > 1) {
				catid = catalogs[rowSelect].value;
				itemNumber = items[rowSelect].value;
				source = sourceFlds[rowSelect].value;
			} else {
				catid = catalogs.value;
				itemNumber = items.value;
				source = sourceFlds.value;
			}
			if (source == "INV") {
				catid = "INV";
				popupParameters = "browseName=inv-kititem";
			} else {
				popupParameters = "browseName=catalog-pricebrk";
			}
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

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menuBarSpacer");
	}

	function checkOnblur(x)
	{
		//alert(rowSelect);alert(x.value);alert(frm.QtyAvailable[rowSelect].value);
        var quantitySelected;

        <% if( createtype.equals("S") ) {%>
			typeIsInventoryRequest = true;
	    <%} %>

        if(typeIsInventoryRequest == true)
        {
	        if(frm.QtyAvailable[rowSelect] == null)
	        {
	           quantitySelected = frm.QtyAvailable.value;
	        }
	        else
	        {
	        	quantitySelected = frm.QtyAvailable[rowSelect].value;
	        }

			if( x.value > quantitySelected)
			{
				alert('The quantity requested will result in a backorder status.');
				return false;
			}
		}
	}

	function viewAdditionalInfo(imageFile, itemNumber) {
		popupParameters = "CatalogItem_imageFile=" + imageFile;
		popupParameters = popupParameters + ";CatalogItem_id_itemNumber=" + itemNumber;
		doSubmitToPopup('admin/catalog/catalog_item_info.jsp', 'DoNothing', 'WIDTH=700px', 'HEIGHT=500px');
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>
