<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.browse.*" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<%
			BrowseObject browseObject = (BrowseObject) request.getAttribute("browseObject");
			BrowseColumn[] labels = browseObject.getLabels();
			List list = (List) request.getAttribute("browseList");
			int	ipageSize = browseObject.getPageSize();
			int	ipageCount = browseObject.getPageCount();
			int	ibegin = 0;
			int	iend = 0;
			int	irows = 0;

			if (ipageSize == 0) {
				ipageSize = 10;
			}
			if (list != null) {
				irows = list.size();
			}
			if (irows > 0) {
				ibegin = 1;
				iend = irows;
			}
			if (irows > ipageSize) {
				iend = ipageSize;
			}

			StringBuffer sbHiddenFields = new StringBuffer();
			StringBuffer sbOriginalFilterFields = new StringBuffer();
			StringBuffer sbFilterFields = new StringBuffer();
			StringBuffer sbOriginalFilterText = new StringBuffer();
			StringBuffer sbFilterText = new StringBuffer();
			String	sortedColumn = "";
			List filters = browseObject.getBrowseFilters();
			Map labelsMap = new HashMap();

			if (filters != null && filters.size() > 0) {
				for (int il = 0; il < labels.length; il++) {
					labelsMap.put(labels[il].getColumnName(), labels[il].getLabel());
				}
				for (int ix=0; ix < filters.size(); ix++) {
					BrowseFilter filter = (BrowseFilter) filters.get(ix);
					String	colname = filter.getColumnName().replace('.', '_');
					String	sort = filter.getSort();
					StringBuffer sb = new StringBuffer("");

					sb.append("<input type=hidden name=" + '"' + "colname" + '"' + " value=" + '"' + colname + '"' + ">");
					sb.append("<input type=hidden name=" + '"' + "filter_txt" + '"' + " value=" + '"' + filter.getValue() + '"' + ">");
					sb.append("<input type=hidden name=" + '"' + "operator" + '"' + " value=" + '"' + filter.getOperator() + '"' + ">");
					sb.append("<input type=hidden name=" + '"' + "logicalOperator" + '"' + " value=" + '"' + filter.getLogicalOperator() + '"' + ">");
					sb.append("<input type=hidden name=" + '"' + "sort" + '"' + " value=" + '"' + filter.getSort() + '"' + ">");

					if (sort != null && !sort.equalsIgnoreCase("N") && sortedColumn.length() == 0) {
						sortedColumn = colname;
					}
					if (filter.isOriginalFilter()) {
						sb.append("<input type=hidden name=" + '"' + "originalFilter" + '"' + " value=" + '"' + "Y" + '"' + ">");

						sbOriginalFilterFields.append(sb);

						if (filter.getValue() != null && filter.getValue().length() > 0) {
							if (sbOriginalFilterText.length() > 0) {
								sbOriginalFilterText.append(filter.getLogicalOperator() + " ");
							}
							sbOriginalFilterText.append(labelsMap.get(colname) + " " + filter.getOperator() + " '" + filter.getValue() + "' ");
						}
					} else {
						sb.append("<input type=hidden name=" + '"' + "originalFilter" + '"' + " value=" + '"' + "N" + '"' + ">");

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

	String	allowBrowse = (String) request.getAttribute("allowBrowse");
	if (allowBrowse == null)
	{
		allowBrowse = "true";
	}
%>

<tsa:hidden name="allowBrowse" value="<%=allowBrowse%>"/>
<tsa:hidden name="createAction" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12><%=browseObject.getTitle()%></div></td></tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
		<table border=0 cellspacing=0 cellpadding=0 border=0>
		<tr><td>&nbsp;<b>Original Filter:</b> <%=sbOriginalFilterText%></td></tr>
		<tr><td>&nbsp;<b>Current Filter:</b>  <%=sbFilterText%></td></tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
			<tr><td height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr><td nowrap>&nbsp;&nbsp;<b>Showing Records <span id="beginRecord"><%=ibegin%></span> - <span id="endRecord"><%=iend%></span> of <%=list.size()%></b></td></tr>
</table>
<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=5px>&nbsp;</td>
	<td width=670px class=browseHdrDk align=center valign=top>
		<table border=0 cellspacing=0 cellpadding=2 width=665px>
		<tr>
<%	for (int i=0; i < labels.length; i++) {
			int	iwidth = 10;

			if (labels[i].getSize() > 0) {	iwidth = labels[i].getSize();	}
%>
			<td nowrap height=18px class=browseHdrDk width=<%=iwidth%>%><a href="javascript: sortMe('<%=labels[i].getColumnName()%>'); void(0);" class=browseHdrDk><%=labels[i].getLabel()%></a></td>
<%	}%>
			<td nowrap height=18px class=browseHdrDk>Delete</td>
		</tr>
		</table>
		<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 675px; height: <%=((iend) * 18) + 20%>px; align:center; overflow-y:auto">
		<div id="noRecords" style="visibility: hidden; display: none;">
		<table border=0 cellspacing=0 cellpadding=2 width=665px class=browseRow>
		<tr><td><b>There were no records found in the database matching your criteria.</b></td></tr>
		</table>
		</div>
<%
		int icount = 0;
		int icurrentRow = 0;
		int icurrentPage = 1;

		while (icurrentPage <= ipageCount) {
			int inextEnd = icurrentRow + ipageSize;
			if (inextEnd > irows) {
				inextEnd = irows;
			}
%>
		<div id="page<%=icurrentPage%>" style="visibility: hidden; display: none;">
		<table id=browseRows border=0 cellspacing=0 cellpadding=2 width=665px class=browseRow>
<%		for (int il = icurrentRow; il < inextEnd; il++) {
				List values[] = (List[]) list.get(il);
				List displayList = (List) values[0];
				List hiddenList = (List) values[1];
				int	ipageRow = ipageSize-((icurrentPage*ipageSize) - il);
%>
		<tr>
<%			for (int i=0; i < labels.length; i++) {
					BrowseColumn column = (BrowseColumn) displayList.get(i);
					String alignment = "left";
					int	iwidth = 10;

					if (column.getType().equals("BigDecimal")) {	alignment = "right";	}
					if (column.getSize() > 0) {	iwidth = column.getSize();	}

					if (column.isHiddenInput()) {
						sbHiddenFields.append("<input type=hidden name=\\" + '"' + column.getColumnName() + "\\" + '"' + " value=\\" + '"' + column.getValue() + "\\" + '"' + ">");
					}
%>
			<td height=18px class=browseRow align=<%=alignment%> valign=top width=<%=iwidth%>%>
<%					if (column.getType().equals("Checkbox")) {
%>
					<input type="checkbox" name="c_checkbox">&nbsp;&nbsp;
<%					} %>
<%				if (column.getLink() != null && column.getLink().length() > 0 && allowBrowse.equalsIgnoreCase("true")) {%>
				<a href="<%=column.getLink()%>"  onclick="javascript: rowSelect='<%=ipageRow%>';" onMouseOver="highlightRow(<%=ipageRow%>);" onMouseOut="removeHighlight(<%=ipageRow%>);">
<%					if (column.getColumnName().equals(sortedColumn)) {%>
					<span id="sortedValue<%=icurrentPage%>-<%=ipageRow%>"><%=column.getValue()%></span>
<%					} else {%>
					<%=column.getValue()%>
<%					}%>
				</a>
<%				} else {
						if (column.getColumnName().equals(sortedColumn)) {%>
					<span id="sortedValue<%=icurrentPage%>-<%=ipageRow%>"><%=column.getValue()%></span>
<%					} else {%>
					<%=column.getValue()%>
<%					}
				}
%>
			</td>
<%			}

				for (int i=0; i < hiddenList.size(); i++) {
					BrowseColumn column = (BrowseColumn) hiddenList.get(i);
					sbHiddenFields.append("<input type=hidden name=icQuestion_" +  icount +  " value=" + '"' + column.getValue() + '"' + ">");
					icount++;
				}
%>
			</td>
			<td width=5%><a href="javascript: deleteMe(<%=icurrentRow%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" border=0></a></td>
		</tr>
<%			icurrentRow++;
			}
			inextEnd = icurrentRow + ipageSize;
			if (inextEnd > irows) {
				inextEnd = irows;
			}
			icurrentPage++;
%>
		</table>
		</div>
<%	}%>
		</div>
	</td>
	<td width=5px>&nbsp;<tsa:hidden name="StdQuestion_icQuestion" value=""/></td>
</tr>
<tr>
	<td id="originalFilterFields"><%=sbOriginalFilterFields%></td>
	<td id="filterFields"><%=sbFilterFields%></td>
</tr>
</table>

<%@ include file="/browse/browse_pageform.jsp" %>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: addNew(); void(0);">Add</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="menu/main_menu.jsp">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var browser = browserCheck();
	var browseName = "${esapi:encodeForJavaScript(browseName)}";
	var totalRows = <%=irows%>;
	var pageSize = <%=browseObject.getPageSize()%>;
	var pageCount = <%=browseObject.getPageCount()%>;
	var sortedColumn = "<%=sortedColumn%>";

	setHiddenFields("<%=sbHiddenFields%>");

	browseDisplaySetup();

	function thisLoad()
	{
		f_StartIt();
<%	if (Integer.valueOf(s_status).intValue() >= 2005 && Integer.valueOf(s_status).intValue() != 2015) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function editQuestion(x)
	{
		frm.StdQuestion_icQuestion.value = x;
		doSubmit('/rfq/std_question_edit.jsp', 'StdQuestionRetrieveById');
	}

	function deleteMe(row)
	{
		var ic = document.getElementById("icQuestion_" + row);

		frm.StdQuestion_ic.value = x;
		doSubmit('/rfq/rfq_std_questions', 'StdQuestionDelete');
	}

	function addNew()
	{
		frm.createAction.value = "SAVE";
		doSubmit('/rfq/std_question_edit.jsp', 'StdQuestionCreate');
	}

// End Hide script -->
</SCRIPT>