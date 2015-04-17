<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsagate.properties.DictionaryManager" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>
<%
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
			StringBuffer sbSortOptions = new StringBuffer();
			StringBuffer sbFilterOptions = new StringBuffer();
			String	sortedColumn = "";
			String	sortedOrder = "";
			String	findString = (String) request.getAttribute("finder");
			List filters = browseObject.getBrowseFilters();
			Map labelsMap = browseObject.getColumnLabels();
			BrowseColumn browseColumns[] = browseObject.getBrowseColumns();
			boolean	lbFirst = true;

			for (int i=0; i < labels.length; i++) {
				BrowseColumn column = labels[i];
				if (!HiltonUtility.isEmpty(column.getLabel(oid, language))) {
//					labelsMap.put(column.getColumnName(), column.getLabel());
					if (!column.getType().equalsIgnoreCase("Input") && !column.getClassName().equalsIgnoreCase("Input") && !column.getType().equalsIgnoreCase("Checkbox"))  {
						if (column.getColumnName().equals(sortedColumn)) {
							sbSortOptions.append("<option value=\"" + column.getColumnName() +  "\" selected>" + column.getLabel(oid, language) + "</option>");
						} else {
							sbSortOptions.append("<option value=\"" + column.getColumnName() + "\">" + column.getLabel(oid, language) + "</option>");
						}
						if (lbFirst) {
							lbFirst = false ;
							sbFilterOptions.append("<option value=\"" + column.getColumnName() +  "\" selected>" + column.getLabel(oid, language) + "</option>");
						} else {
							sbFilterOptions.append("<option value=\"" + column.getColumnName() + "\">" + column.getLabel(oid, language) + "</option>");
						}
					}
				}
			}

			if (filters != null) {
				for (int ix=0; ix < filters.size(); ix++) {
					BrowseFilter filter = (BrowseFilter) filters.get(ix);
					String	colname = filter.getColumnName().replace('.', '_');
					String	sort = filter.getSort();
					StringBuffer sb = new StringBuffer("");
					String	logicalOperator = "AND";

					if ((ix + 1) < filters.size()) {
						BrowseFilter nextFilter = (BrowseFilter) filters.get(ix + 1);
						logicalOperator = nextFilter.getLogicalOperator();
					}

					sb.append("<input type=hidden name=\"colname\" value=\"" + colname + "\">");
					sb.append("<input type=hidden name=\"filter_txt\" value=\"" + filter.getValue() + "\">");
					sb.append("<input type=hidden name=\"operator\" value=\"" + filter.getOperator() + "\">");
					sb.append("<input type=hidden name=\"logicalOperator\" value=\"" + logicalOperator + "\">");
					sb.append("<input type=hidden name=\"sort\" value=\"" + filter.getSort() + "\">");

					if (sort != null && !sort.equalsIgnoreCase("N") && sortedColumn.length() == 0) {
						sortedColumn = colname;
						sortedOrder = sort;
					}
					if (filter.isOriginalFilter()) {
						sb.append("<input type=hidden name=\"originalFilter\" value=\"Y\">");

						sbOriginalFilterFields.append(sb);

						if (filter.getValue() != null && filter.getValue().length() > 0) {
							if (labelsMap.containsKey(colname) && !HiltonUtility.isEmpty((String) labelsMap.get(colname))) {
								if (sbOriginalFilterText.length() > 0) {
									sbOriginalFilterText.append(filter.getLogicalOperator() + " ");
								}
								sbOriginalFilterText.append(labelsMap.get(colname) + " " + filter.getOperator() + " '" + filter.getValue() + "' ");
							}
						}
					} else {
						sb.append("<input type=hidden name=\"originalFilter\" value=\"N\">");
						sbFilterFields.append(sb);

						if (filter.getValue() != null && filter.getValue().length() > 0) {
							if (labelsMap.containsKey(colname) && !HiltonUtility.isEmpty((String) labelsMap.get(colname))) {
								if (sbFilterText.length() > 0) {
									sbFilterText.append(filter.getLogicalOperator() + " ");
								}
								sbFilterText.append(labelsMap.get(colname) + " " + filter.getOperator() + " '" + filter.getValue() + "' ");
							}
						}
					}
				}
			}

			String	allowBrowse = (String) request.getAttribute("allowBrowse");
			if (allowBrowse == null) {
				allowBrowse = "true";
			}
			String filterType = (String) request.getAttribute("filterType");
			if (HiltonUtility.isEmpty(filterType)) {
				filterType = "ADVANCED";
			}


			String	s_req_type = (String) request.getAttribute("RequisitionHeader_requisitionType");
			String	s_po_type = (String) request.getAttribute("PoHeader_poType");
			if (s_req_type == null)	{	s_req_type = "";	}
			if (s_po_type == null)	{	s_po_type = "";	}

			long start = System.currentTimeMillis();

			/*	added for System Table */
			String tableType = HiltonUtility.ckNull((String) request.getAttribute("tableType"));
			if (!HiltonUtility.isEmpty(tableType) && browseObject.getBrowseName().indexOf("stdtable") >= 0)
			{
				String title = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, tableType, "");
				if (!HiltonUtility.isEmpty(title))
				{
					browseObject.setTitle(title + " Browse");
				}
			}
			if (browseObject.getBrowseName().equals("catalogitem-admin"))
			{
				String catId = HiltonUtility.ckNull((String) request.getAttribute("Catalog_catalogId"));
				browseObject.setTitle(catId + " Catalog Browse");
			}
%>
<tsa:hidden name="allowBrowse" value="<%=allowBrowse%>"/>
<tsa:hidden name="browseId" value="<%=browse.getBrowseId()%>"/>
<tsa:hidden name="newPage" value="1"/>
<tsa:hidden name="pageSize" value=""/>
<tsa:hidden name="filterType" value="<%=filterType%>"/>
<tsa:hidden name="attributeSet" value="<%=browseObject.isAttributeSet()%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12><%=browseObject.getTitle()%></div></td></tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="100%" /></td>
	<td valign=bottom align=right>
		<div id="filterTextDisplay">
		<table border=0 cellspacing=0 cellpadding=0 border=0>
		<tr><td align=right valign=top nowrap>&nbsp;<b>Original Filter:</b></td><td>&nbsp;<%=sbOriginalFilterText%></td></tr>
		<tr><td align=right valign=top nowrap>&nbsp;<b>Current Filter:</b></td><td>&nbsp;<%=sbFilterText%></td></tr>
		</table>
		</div>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>


<div id="instructions" style="visibility:hidden; display:none; align:center;"></div>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td nowrap>&nbsp;&nbsp;<b>Showing Records <span id="beginRecord"><%=browse.getRowStart()%></span> - <span id="endRecord"><%=browse.getRowEnd()%></span> of <%=browse.getRowCount()%></b></td>
	<td nowrap height=20px>
		<div id="pageNavigation" style="border:solid 0px; padding: 2px; height: 0px; align:center; display:none; visibility:hidden;">
		<table border=0 cellpadding=2 cellspacing=1 align=center>
		<tr>
			<td align=right><b>Go To Page: </b><input type=text name="go_to_pg" size=5 value=""> <b>of <%=ipageCount%></b></td>
			<td><a href="javascript: if (!isEmpty(nfilter(frm.go_to_pg))) { pageMe(nfilter(frm.go_to_pg)); } void(0);"><img class=button src="<%=contextPath%>/images/button_go_sm.gif" border=0 valign=bottom></a></td>
		</tr>
		</table>
		</div>
	</td>
</tr>
</table>
<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=5px>&nbsp;</td>
	<td width=670px class=browseHdrDk align=center valign=top>
		<table border=0 cellspacing=0 cellpadding=2 width=665px>
		<tr>
<%	String className = "";
	for (int i=0; i < labels.length; i++) {
			String alignment = "left";
			int	iwidth = 10;

			if (labels[i].getType().indexOf("Decimal") >= 0) {	alignment = "right";	}

			if (labels[i].getSize() > 0) {	iwidth = labels[i].getSize();	}
			if (HiltonUtility.isEmpty(sortedColumn))
			{
				if (labels[i].getSort().length() > 0)
				{
					sortedColumn = labels[i].getColumnName();
					if (labels[i].getSort().equalsIgnoreCase("A"))
					{
						sortedOrder = "ASC";
					}
					else
					{
						sortedOrder = "DESC";
					}
				}
			}
			if (labels[i].getColumnName().equalsIgnoreCase(sortedColumn))
			{
				className="sortedColumn";
			}
			else
			{
				className="browseHdrDk";
			} %>
			<td nowrap height=18px class=<%=className%> width=<%=iwidth%>% align=<%=alignment%>>
				<a href="javascript: sortMe('<%=labels[i].getColumnName()%>'); void(0);" class=<%=className%>>
				<%=labels[i].getLabel(oid, language)%>

				<% if (labels[i].getColumnName().equalsIgnoreCase(sortedColumn)) {
						if (sortedOrder.equalsIgnoreCase("ASC")) { %>
					<img src="<%=contextPath%>/images/arrow_up.gif" border=0>
					<% } else { %>
					<img src="<%=contextPath%>/images/arrow_down.gif" border=0>
					<% }
				} %>
				</a>&nbsp;
			</td>
<%	}%>
			<tsa:hidden name="sortedOrder" value="<%=sortedOrder%>"/>
		</tr>
		</table>
		<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 675px; height: <%=((ipageSize) * 18) + 20%>px; align:center; overflow-y:visible; overflow-x:auto;">
		<div id="noRecords" style="visibility: hidden; display: none;">
		<table border=0 cellspacing=0 cellpadding=2 width=665px class=browseRow>
		<tr><td><b>There were no records found in the database matching your criteria.</b></td></tr>
		</table>
		</div>
		<div id="noAttributeSet" style="visibility: hidden; display: none;">
		<table border=0 cellspacing=0 cellpadding=2 width=665px class=browseRow>
		<tr><td><b><%=browseObject.getNoAttributeErrorMsg()%></b></td></tr>
		</table>
		</div>

		<table id=browseRows border=0 cellspacing=0 cellpadding=1 width=665px class=browseRow>
<%		String rowClass = "browseRow";
		for (int il = 0; il < list.size(); il++) {
				Object object = list.get(il);
				List	detailList = new ArrayList();
				int	itd = 0;
%>
		<tr>
			<td class=<%=rowClass%>>
				<table border=0 cellspacing=0 cellpadding=2 width=665px class=<%=rowClass%>>
				<tr class="<%=rowClass%>">
<%			for (int i = 0; i < browseColumns.length; i++) {
					BrowseColumn column = browseColumns[i];
					Object result = null;
					String	resultString = "";
					String alignment = "left";
					int	iwidth = 10;

					result = BrowseUtility.getTestColumnValue(column, object, oid, browseColumns, browse.getBrowseId(), uid);

					if (column.isHiddenInput()) {
						String resultStr = String.valueOf(result);
						resultStr = resultStr .replace('"', '~');
						resultStr = resultStr .replaceAll("~", "'");
						resultStr = resultStr .replaceAll("\r\n", " ");
						resultStr = resultStr .replaceAll("\r", " ");
						resultStr = resultStr .replaceAll("\n", " ");

						sbHiddenFields.append( "<input type=hidden name=\\" + '"' + column.getColumnName() + "\\\" value=\\\"" + resultStr + "\\\">");
						if (il == (list.size() - 1))
						{
							sbHiddenFields.append( "<input type=hidden name=\\" + '"' + column.getColumnName() + "\\\" value=\\\"\\\">");
						}
					}
					if (column.isDetail()) {
						BrowseColumn detailColumn = BrowseUtility.getBrowseColumnCopy(column);
						detailColumn.setValue(result);
						detailList.add(detailColumn);
					}

					if (result instanceof String) {
						resultString = (String) result;
					} else if (result instanceof BigDecimal) {
						resultString = ((BigDecimal) result).toString();
					}

					if (!column.isHidden() && !column.isDetail()) {%>
					<td height=18px class="<%=rowClass%>" align=<%=column.getAlignment()%> width=<%=column.getIWidth()%>% valign=top>
<%					if (column.getLink() != null && column.getLink().length() > 0 && allowBrowse.equalsIgnoreCase("true")) {%>
						<a href="<%=BrowseUtility.testPopulateLinkParameters(column, object, browseColumns)%>"  onclick="javascript: rowSelect='<%=il%>';" onMouseOver="showDetails(<%=il%>);highlightRow(<%=il%>);" onMouseOut="removeHighlight(<%=il%>);hideDetails(<%=il%>);">
<%						if (column.getColumnName().equals(sortedColumn) && !HiltonUtility.isEmpty(findString) && resultString.toUpperCase().indexOf(findString) >= 0) {
								findString = "";%>
						<font class=selectedRow><%=result%></font>
<%						} else {%>
						<%=result%>
<%						}%>
						</a>
<%					} else {
							if (column.getColumnName().equals(sortedColumn) && !HiltonUtility.isEmpty(findString) && resultString.toUpperCase().indexOf(findString) >= 0) {
								findString = "";%>
						<font class=selectedRow><%=result%></font>
<%						} else {%>
						<%=result%>
<%						}
						}%>
					</td>
<%				}
			} %>
				</tr>
				</table>
<%		if (browseObject.isDetailVisible()) {%>
				<div  id="details<%=il%>" style="visibility:visible;display:block;" class=<%=rowClass%>>
<%		} else {%>
				<div  id="details<%=il%>" style="visibility:hidden;display:none;" class=<%=rowClass%>>
<%		}%>
				<table  id="detailRows" border=0 cellspacing=0 cellpadding=0 class=<%=rowClass%> width=95% align=right>
				<tr class=<%=rowClass%>>
<%		int itotalWidth = 0;
			for (int i=0; i < detailList.size(); i++) {
				BrowseColumn column = (BrowseColumn) detailList.get(i);
				int	iwidth = column.getIWidth();
				itotalWidth = itotalWidth + iwidth;

				if (iwidth >= 100 || itotalWidth > 100) {
					if (i > 0) {
%>
				</tr>
				<tr class=<%=rowClass%>>
<%				}%>
					<td colspan=<%=detailList.size()%> height=18px class=<%=rowClass%> width=<%=iwidth%>% valign=top>
<%				if (!HiltonUtility.isEmpty(column.getLabel(oid, language))) { %>
						<b><%=column.getLabel(oid, language)%>:</b>
<%				} %>
						&nbsp;<%=column.getValue()%>
					</td>
<%			} else {%>
					<td height=18px class=<%=rowClass%> width=<%=iwidth%>% valign=top>
<%				if (column.getLink() != null && column.getLink().length() > 0) {%>
						<a href="<%=column.getLink()%>"><%=column.getLabel(oid, language)%></a>
<%				} else {%>
<%					if (!HiltonUtility.isEmpty(column.getLabel(oid, language))) { %>
						<b><%=column.getLabel(oid, language)%>:</b>
<%					} %>
						&nbsp;<%=column.getValue()%>
<%				} %>
					</td>
<%			}
				if (itotalWidth >= 100 && (i+1) < detailList.size()) {
					itotalWidth = 0;
%>
				</tr>
				<tr class=<%=rowClass%>>
<%			}
			} %>
				</tr>
				</table>
				</div>
			</td>
		</tr>
<%
			if (rowClass.equals("browseRow"))
			{
				rowClass = "summary";
			} else {
				rowClass = "browseRow";
			}
		}
%>
		</table>
		</div>
	</td>
	<td width=5px>&nbsp;</td>
</tr>
</table>
</div>

<div id="originalFilterFields"><%=sbOriginalFilterFields%></div>
<div id="currentFilterFields"><%=sbFilterFields%></div>

<table border=0 width=680px>
<tr>
	<td align=center><%@ include file="/browse/browse_pageform.jsp" %></td>
	<td align=center><%@ include file="/browse/browse_page_nav.jsp" %></td>
</tr>
</table>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var browser = browserCheck();
	var browseName = "${esapi:encodeForJavaScript(browseName)}";
	var browseId = "<%=browse.getBrowseId()%>";
	var totalRows = <%=irows%>;
	var pageSize = <%=ipageSize%>;
	var pageCount = <%=ipageCount%>;
	var sortedColumn = "<%=sortedColumn%>";
<%	if (sbFilterText != null && sbFilterText.length() > 0) {%>
	var filterSet = true;
<%	} else {%>
	var filterSet = false;
<%	}
	if (sbOriginalFilterText != null && sbOriginalFilterText.length() > 0) {%>
	var originalFilterSet = true;
<%	} else {%>
	var originalFilterSet = false;
<%	}%>

	currentPage = <%=browse.getCurrentPage()%>;

	setHiddenFields("<%=sbHiddenFields%>");

	if (document.getElementById("filterFields") != null && document.getElementById("filterFields") != undefined) {
		document.getElementById("filterFields").innerHTML = document.getElementById("currentFilterFields").innerHTML;
		document.getElementById("currentFilterFields").innerHTML = "";
	} else {
		document.getElementById("currentFilterFields").id = "filterFields";
	}

	browseDisplaySetup();

	function showDetails(row) {
		displayArea("details" + row);
	}
	function hideDetails(row) {
		hideArea("details" + row);
	}


// End Hide script -->
</SCRIPT>
