<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsa.puridiom.entity.*" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<%
	String itemNumber = HiltonUtility.ckNull((String) request.getAttribute("InvItem_itemNumber"));
	String description = HiltonUtility.ckNull((String) request.getAttribute("InvItem_description"));
	
	int size = 0;
	HashMap catalogsMap = new HashMap();
	List invFormCatalogList = (List) request.getAttribute("invFormCatalogList");
	if (invFormCatalogList != null)
	{
		size = invFormCatalogList.size();
	}
%>

<tsa:hidden name="InvFormCatalog_itemNumber" value="<%=itemNumber%>"/>
<tsa:hidden name="InvItem_itemNumber" value="<%=itemNumber%>"/>
<tsa:hidden name="itemAction" value="UPDATE"/>
<tsa:hidden name="deleteall" value=""/>


<table border=0 width=680px>
<tr>
	<td width=200px align=right nowrap><b>Item Number:</b>&nbsp;</td>
	<td nowrap><%=itemNumber%></td>
</tr>
<tr>
	<td width=200px align=right><b>Description:</b>&nbsp;</td>
	<td><%=description%></td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Inventory Item Catalogs</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr>
			<td width=600px height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center valign=top width=280px>
		<table border=1 cellspacing=0 cellpadding=0 width=240px class=browseHdr>
		<tr>
			<td width=100% height=18px class=browseHdr>&nbsp;Valid Catalogs</td>
		</tr>
		<tr>
			<td>
				<table id=catalogs border=0 cellspacing=2 cellpadding=2 width=100% class=browseRow>
<%
		for (int i = 0; i < size; i++)
		{
			InvFormCatalog invFormCatalog = (InvFormCatalog) invFormCatalogList.get(i);
			InvFormCatalogPK invFormCatalogPK = (InvFormCatalogPK) invFormCatalog.getComp_id();
			catalogsMap.put(String.valueOf(i), invFormCatalogPK.getInvCatid());
		} %>
				</table>
				<div id="noCatalogs" style="visibility: hidden; display: none;">
				<table>
				<tr>
					<td colspan=2 align=center class=browseHdrSpace>
						<b>This item is not currently listed in any catalogs.</b>
					</td>
				</tr>
				</table>
				</div>
			</td>
		</tr>
		</table>
		<table border=0 cellspacing=0 cellpadding=0 width=240px class=browseRow>
		<tr>
			<td height=18px align=right nowrap><a href="javascript: deleteAll(); void(0);"><b>Delete All</b></a></td>
		</tr>
		</table>
	</td>
	<td width=400px valign=top align=right>
<%
			Browse browse = (Browse) request.getAttribute("browse");
			browse.setPageSize(browse.getRowCount());
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
			List filters = browseObject.getBrowseFilters();
			Map labelsMap = browseObject.getColumnLabels();
			BrowseColumn browseColumns[] = browseObject.getBrowseColumns();
			boolean	lbFirst = true;	

			for (int i=0; i < labels.length; i++) {
				BrowseColumn column = labels[i];
		
				if (column.getLabel() != null && column.getLabel().length() > 0 && !column.getType().equals("Input") && !column.getClassName().equals("Input") && !column.getType().equals("Checkbox")) {
					if (column.getColumnName().equals(sortedColumn)) {
						sbSortOptions.append("<option value=" + '"' + column.getColumnName() + '"' + " selected" + ">" + column.getLabel() + "</option>");
					} else {
						sbSortOptions.append("<option value=" + '"' + column.getColumnName() + '"' + ">" + column.getLabel() + "</option>");
					}
					if (lbFirst) {
						lbFirst = false ;
						sbFilterOptions.append("<option value=" + '"' + column.getColumnName() + '"' + " selected" + ">" + column.getLabel() + "</option>");
					} else {
						sbFilterOptions.append("<option value=" + '"' + column.getColumnName() + '"' + ">" + column.getLabel() + "</option>");
					} 
				}
			}

			if (filters != null) {
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
							if (labelsMap.containsKey(colname) && !HiltonUtility.isEmpty((String) labelsMap.get(colname))) {
								if (sbOriginalFilterText.length() > 0) {
									sbOriginalFilterText.append(filter.getLogicalOperator() + " ");
								}
								sbOriginalFilterText.append(labelsMap.get(colname) + " " + filter.getOperator() + " '" + filter.getValue() + "' ");
							}
						}
					} else {
						sb.append("<input type=hidden name=" + '"' + "originalFilter" + '"' + " value=" + '"' + "N" + '"' + ">");
						
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
			if (allowBrowse == null)
			{
				allowBrowse = "true";
			}
%>

		<tsa:hidden name="allowBrowse" value="<%=allowBrowse%>"/>
		
		<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=400px>
		<tr>
			<td width=5px>&nbsp;</td>
			<td width=390px class=browseHdrDk align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=385px>
				<tr>
					<td nowrap height=18px class=browseHdrDk width=25%><a href="javascript: sortMe('InvCatalog_catalogId'); void(0);" class=browseHdrDk>Catalog ID</a>&nbsp;</td>
					<td nowrap height=18px class=browseHdrDk width=75%><a href="javascript: sortMe('InvCatalog_description'); void(0);" class=browseHdrDk>Description</a>&nbsp;</td>
				</tr>
				</table>
				<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 385px; height: <%=((ipageSize) * 18) + 22%>px; align:center; overflow-y:auto; overflow-x:visible;">
				<div id="noRecords" style="visibility: hidden; display: none;">
				<table border=0 cellspacing=0 cellpadding=2 width=385px class=browseRow>
				<tr><td><b>There are currently no inventory catalogs listed in the database.</b></td></tr>
				</table>
				</div>

				<table id=browseRows border=0 cellspacing=0 cellpadding=2 width=385px class=browseRow>
<%		for (int il = 0; il < list.size(); il++) {
				Object object = list.get(il);
				List	detailList = new ArrayList();
				int	itd = 0;
				BrowseColumn column = browseColumns[0];
				Object result = BrowseUtility.getTestColumnValue(column, object, oid, browseColumns, browse.getBrowseId());
%>
				<tr id="<%=result%>">				
<%				if (column.isHiddenInput()) {
						sbHiddenFields.append("<input type=hidden name=\\" + '"' + column.getColumnName() + "\\" + '"' + " value=\\" + '"' + result + "\\" + '"' + ">");
					}
%>
					<td id=<%=result%> height=18px class=browseRow align=left width=25%>
<%				if (column.getLink() != null && column.getLink().length() > 0 && allowBrowse.equalsIgnoreCase("true")) {%>
						<a href="<%=BrowseUtility.testPopulateLinkParameters(column, object, browseColumns)%>"  onclick="javascript: rowSelect='<%=il%>';" onMouseOver="highlightRow(<%=il%>);" onMouseOut="removeHighlight(<%=il%>);">
						<%=result%>
						</a>
<%				} else {%>
						<%=result%>
<%				}%>
					</td>
<%			
				BrowseColumn column2 = browseColumns[1];
				Object result2 = BrowseUtility.getTestColumnValue(column2, object, oid, browseColumns, browse.getBrowseId());
%>
					<td id=<%=result2%> height=18px class=browseRow align=left width=75%>
<%				if (column.getColumnName().equals(sortedColumn)) {%>
						<%=result2%>
<%				}%>
						<tsa:hidden name="<%=result%>_description" value="<%=result2%>"/>
					</td>
				</tr>
<%		}%>
				</table>
				</div>
			</td>
			<td width=5px>&nbsp;</td>
		</tr>
		<tr>
			<td id="originalFilterFields"><%=sbOriginalFilterFields%></td>
			<td id="currentFilterFields"><%=sbFilterFields%></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvFormCatalogUpdate;InvItemRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	
	var browser = browserCheck();
	var browseName = "${esapi:encodeForJavaScript(browseName)}";
	var totalRows = <%=irows%>;
	var pageSize = <%=ipageSize%>;
	var pageCount = <%=ipageCount%>;
	var sortedColumn = "<%=sortedColumn%>";
<%	if (sbFilterFields != null && sbFilterFields.length() > 0) {%>
	var filterSet = true;
<%	} else {%>
	var filterSet = false;
<%	}
	if (sbOriginalFilterFields != null && sbOriginalFilterFields.length() > 0) {%>
	var originalFilterSet = true;
<%	} else {%>
	var originalFilterSet = false;
<%	}%>

	setHiddenFields("<%=sbHiddenFields%>");

	if (document.getElementById("filterFields") != null && document.getElementById("filterFields") != undefined && document.getElementById("filterFields").length > 0) {
		document.getElementById("filterFields").innerHTML = document.getElementById("currentFilterFields").innerHTML;
		document.getElementById("currentFilterFields").innerHTML = "";
	} else {
		document.getElementById("currentFilterFields").id = "filterFields";
	}

	var myTable = document.getElementById("catalogs");
	var count = myTable.rows.length;
	var rowSelect;
	var size = <%=size%>;
	
	if (size <= 0)
	{
		displayArea("noCatalogs");
	}
	
	if (totalRows <= 0)
	{
		displayArea("noRecords");
	}
	
<%
	for (int i = 0; i < catalogsMap.size(); i++) 
	{
		String catalog = (String) catalogsMap.get(String.valueOf(i));
%>
		returnMe("<%=catalog%>");
<%} %>
	
	function returnMe(invCatid)
	{
		if (size <= 0)
		{
			hideArea("noCatalogs");
		}
		
		myRow = myTable.insertRow(count);
		myRow.id = "invFormCatalog_" + invCatid;
		myCell = myRow.insertCell();
		myCell.align = "left";
		myCell.width = "25%";
		myCell.innerHTML = invCatid + "<INPUT TYPE=\"HIDDEN\" NAME=\"InvFormCatalog_invCatid\" value=" + invCatid + ">";
		myCell = myRow.insertCell();
		myCell.align = "left";
		myCell.width = "65%";
		myCell.innerHTML = document.getElementById(invCatid + "_description").value;
		myCell = myRow.insertCell();
		myCell.width = "10%";
		myCell.innerHTML = "<A href=\"javascript:deleteMe('" + invCatid + "'); void(0)\"><IMG SRC=\"<%=contextPath%>/images/delete.gif\" ALT=\"Delete\" BORDER=\"0\" TABINDEX=\"999\"></A>";
		count++;
		
		hideArea(invCatid);
		
	}
	
	function deleteMe(invCatid)
	{
		if (confirm("Are you sure you wish to delete this Catalog?"))
		{
			hideArea("invFormCatalog_" + invCatid);
			displayArea(invCatid);
			count--;
			
			if (count == 0)
			{
				displayArea("noCatalogs");
				frm.deleteall.value = "TRUE";
			}
		}
	}
	
	function deleteAll()
	{
		if (confirm("Are you sure you wish to delete all valid Catalogs?"))
		{
			var rows=myTable.rows;
			for (var i = 0; i < rows.length; i++)
			{
				myString = rows[i].id;
				hideArea(myString);
					
				if (myString.indexOf("invFormCatalog_") >= 0)
				{
					invCatid = myString.substring(15);
					displayArea(invCatid);
				}
			}
				
			count = 0;
			displayArea("noCatalogs");
			frm.deleteall.value = "TRUE";
		}
	}
	
// End Hide script -->
</SCRIPT>