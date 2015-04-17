<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="org.owasp.esapi.Encoder" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<%
	String itemNumber = HiltonUtility.ckNull((String) request.getAttribute("InvItem_itemNumber"));
	String description = HiltonUtility.ckNull((String) request.getAttribute("InvItem_description"));

	int size = 0;
	HashMap productsMap = new HashMap();
	List invFormProductList = (List) request.getAttribute("invFormProductList");
	if (invFormProductList != null)
	{
		size = invFormProductList.size();
	}
%>

<tsa:hidden name="InvFormProduct_itemNumber" value="<%=itemNumber%>"/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Inventory Item Products</div>
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
	<td align=center valign=top width=240px>
		<table border=1 cellspacing=0 cellpadding=0 width=200px class=browseHdr>
		<tr>
			<td width=100% height=18px class=browseHdr>&nbsp;Valid Products</td>
		</tr>
		<tr>
			<td>
				<table id=products border=0 cellspacing=2 cellpadding=2 width=100% class=browseRow>
<%
		for (int i = 0; i < size; i++)
		{
			InvFormProduct invFormProduct = (InvFormProduct) invFormProductList.get(i);
			InvFormProductPK invFormProductPK = (InvFormProductPK) invFormProduct.getComp_id();
			productsMap.put(String.valueOf(i), invFormProductPK.getProductId());
		} %>
				</table>
				<div id="noProducts" style="visibility: hidden; display: none;">
				<table>
				<tr>
					<td colspan=2 align=center class=browseHdrSpace>
						<b>This item is currently not assigned to any products.</b>
					</td>
				</tr>
				</table>
				</div>
			</td>
		</tr>
		</table>
		<table border=0 cellspacing=0 cellpadding=0 width=200px class=browseRow>
		<tr>
			<td height=18px align=right nowrap><a href="javascript: deleteAll(); void(0);"><b>Delete All</b></a></td>
		</tr>
		</table>
	</td>
	<td width=440px valign=top align=right>
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

					sb.append("<input type=hidden name=" + '"' + "colname" + '"' + " value=" + '"' + headerEncoder.encodeForHTMLAttribute(colname) + '"' + ">");
					sb.append("<input type=hidden name=" + '"' + "filter_txt" + '"' + " value=" + '"' + headerEncoder.encodeForHTMLAttribute(filter.getValue()) + '"' + ">");
					sb.append("<input type=hidden name=" + '"' + "operator" + '"' + " value=" + '"' + headerEncoder.encodeForHTMLAttribute(filter.getOperator()) + '"' + ">");
					sb.append("<input type=hidden name=" + '"' + "logicalOperator" + '"' + " value=" + '"' + headerEncoder.encodeForHTML(filter.getLogicalOperator()) + '"' + ">");
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
									sbOriginalFilterText.append(headerEncoder.encodeForHTML(filter.getLogicalOperator()) + " ");
								}
								sbOriginalFilterText.append(headerEncoder.encodeForHTML((String)labelsMap.get(colname)) + " " + headerEncoder.encodeForHTML(filter.getOperator()) + " '" + headerEncoder.encodeForHTML(filter.getValue()) + "' ");
							}
						}
					} else {
						sb.append("<input type=hidden name=" + '"' + "originalFilter" + '"' + " value=" + '"' + "N" + '"' + ">");

						sbFilterFields.append(sb);

						if (filter.getValue() != null && filter.getValue().length() > 0) {
							if (labelsMap.containsKey(colname) && !HiltonUtility.isEmpty((String) labelsMap.get(colname))) {
								if (sbFilterText.length() > 0) {
									sbFilterText.append(headerEncoder.encodeForHTML(filter.getLogicalOperator()) + " ");
								}
								sbFilterText.append(headerEncoder.encodeForHTML((String)labelsMap.get(colname)) + " " + headerEncoder.encodeForHTML(filter.getOperator()) + " '" + headerEncoder.encodeForHTML(filter.getValue()) + "' ");
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

		<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=440px>
		<tr>
			<td width=5px>&nbsp;</td>
			<td width=430px class=browseHdrDk align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=425px>
				<tr>
					<td nowrap height=18px class=browseHdrDk width=25%><a href="javascript: sortMe('StdTable_id_tableKey'); void(0);" class=browseHdrDk>Product ID</a>&nbsp;</td>
					<td nowrap height=18px class=browseHdrDk width=75%><a href="javascript: sortMe('StdTable_description'); void(0);" class=browseHdrDk>Description</a>&nbsp;</td>
				</tr>
				</table>
				<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 435px; height: 292px; align:center; overflow-y:auto; overflow-x:visible;">
				<div id="noRecords" style="visibility: hidden; display: none;">
				<table border=0 cellspacing=0 cellpadding=2 width=425px class=browseRow>
				<tr><td><b>There were no records found in the database matching your criteria.</b></td></tr>
				</table>
				</div>

				<table id=browseRows border=0 cellspacing=0 cellpadding=2 width=425px class=browseRow>
<%		for (int il = 0; il < list.size(); il++) {
				Object object = list.get(il);
				List	detailList = new ArrayList();
				int	itd = 0;
				BrowseColumn column = browseColumns[1];
				Object result = BrowseUtility.getTestColumnValue(column, object, oid, browseColumns, browse.getBrowseId(), uid);
%>
				<tr id="<%=result%>">

<%				if (column.isHiddenInput()) {
						sbHiddenFields.append("<input type=hidden name=\\" + '"' + column.getColumnName() + "\\" + '"' + " value=\\" + '"' + result + "\\" + '"' + ">");
					}

%>
					<td id=<%=result%> height=18px class=browseRow align=left width=25%>
<%					if (column.getLink() != null && column.getLink().length() > 0 && allowBrowse.equalsIgnoreCase("true")) {%>
							<a href="<%=BrowseUtility.testPopulateLinkParameters(column, object, browseColumns)%>"  onclick="javascript: rowSelect='<%=il%>';" onMouseOver="highlightRow(<%=il%>);" onMouseOut="removeHighlight(<%=il%>);">
							<%=result%>
							</a>
<%					} else {%>
							<%=result%>
<%					}%>
					</td>
<%
				BrowseColumn column2 = browseColumns[2];
				Object result2 = BrowseUtility.getTestColumnValue(column2, object, oid, browseColumns, browse.getBrowseId(), uid);
%>
					<td id=<%=result2%> height=18px class=browseRow align=left width=75%>
						<%=result2%>
						<input type=hidden name="<%=result%>_description" value="<%=result2%>">
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
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvFormProductUpdate;InvItemRetrieveById'); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById'); void(0);">Return</a></div></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	setNavCookie("/inventory/inv_item_products.jsp", "DoNothing", "Inventory Item Products");
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

	var myTable = document.getElementById("products");
	var count = myTable.rows.length;
	var rowSelect;
	var size = <%=size%>;

	if (size <= 0)
	{
		displayArea("noProducts");
	}

	if (totalRows <= 0)
	{
		displayArea("noRecords");
	}

<%
	for (int i = 0; i < productsMap.size(); i++)
	{
		String product = (String) productsMap.get(String.valueOf(i));
%>
		returnMe("<%=product%>");
<%} %>

	function returnMe(productId)
	{
		if (size <= 0)
		{
			hideArea("noProducts");
		}

		myRow = myTable.insertRow(count);
		myRow.id = "invFormProduct_" + productId;
		myCell = myRow.insertCell();
		myCell.align = "left";
		myCell.width = "25%";
		myCell.innerHTML = productId + "<INPUT TYPE=\"HIDDEN\" NAME=\"InvFormProduct_productId\" value=" + productId + ">";
		myCell = myRow.insertCell();
		myCell.align = "left";
		myCell.width = "65%";
		myCell.innerHTML = document.getElementById(productId + "_description").value;
		myCell = myRow.insertCell();
		myCell.width = "10%";
		myCell.innerHTML = "<A href=\"javascript:deleteMe('" + productId + "'); void(0)\"><IMG SRC=\"<%=contextPath%>/images/delete.gif\" ALT=\"Delete\" BORDER=\"0\" TABINDEX=\"999\"></A>";
		count++;

		hideArea(productId);

	}

	function deleteMe(productId)
	{
		if (confirm("Are you sure you wish to delete this Product?"))
		{
			hideArea("invFormProduct_" + productId);
			displayArea(productId);
			count--;

			if (count == 0)
			{
				displayArea("noProducts");
				frm.deleteall.value = "TRUE";
			}
		}
	}

	function deleteAll()
	{
		if (confirm("Are you sure you wish to delete all valid Products?"))
		{
			var rows=myTable.rows;
			for (var i = 0; i < rows.length; i++)
			{
				myString = rows[i].id;
				hideArea(myString);

				if (myString.indexOf("invFormProduct_") >= 0)
				{
					productId = myString.substring(15);
					displayArea(productId);
				}
			}

			count = 0;
			displayArea("noProducts");
			frm.deleteall.value = "TRUE";

/*
			for (i = (count -1); i >= 0; i--)
			{
				myString = myRow.id;
				myTable.deleteRow(i);

				if (myString.indexOf("invFormProduct_") >= 0)
				{
					productId = myString.substring(15);
					displayArea(productId);
				}
			}
*/
		}
	}

// End Hide script -->
</SCRIPT>