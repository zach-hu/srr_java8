<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/inventory/inv_property_checklist_fields.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/browse.js"></SCRIPT>

<%
	String	s_dsb_ic_header = (String) request.getAttribute("DisbHeader_icDsbHeader");
	String	s_dsb_number = (String) request.getAttribute("DisbHeader_disbNumber");
	String	s_dsb_status = (String) request.getAttribute("DisbHeader_status");
	String	s_dsb_type = (String) request.getAttribute("DisbHeader_disbType");
	String	s_fiscal_year = (String) request.getAttribute("DisbHeader_fiscalYear");
	String	s_item_location = "";

	String	s_current_process = "SHOPCART";
	String	s_current_page = "/inventory/dsb_items.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";

	List	disbLineList = (List) request.getAttribute("disbLineList");
	int	i_linecount = 0;

	if (disbLineList != null)
	{
		i_linecount = disbLineList.size();

		if (i_linecount > 0)
		{
			DisbLine disbLine = (DisbLine) disbLineList.get(0);
			s_item_location = HiltonUtility.ckNull(disbLine.getItemLocation());
		}
	}

	if (s_dsb_status == null)	{ s_dsb_status = "5000";	}
%>

<tsa:hidden name="DisbHeader_icDsbHeader" value="<%=s_dsb_ic_header%>"/>
<tsa:hidden name="DisbLine_icDsbHeader" value="<%=s_dsb_ic_header%>"/>
<tsa:hidden name="DisbLine_icDsbLine" value=""/>
<tsa:hidden name="DisbHeader_disbNumber" value="<%=s_dsb_number%>"/>
<tsa:hidden name="DisbHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="DisbHeader_status" value="<%=s_dsb_status%>"/>
<tsa:hidden name="DisbHeader_disbType" value="<%=s_dsb_type%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_dsb_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="DBH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_dsb_ic_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="lineCount" value="<%=i_linecount%>"/>
<tsa:hidden name="formtype" value="DSB"/>
<tsa:hidden name="frompage" value="shopcart"/>

<%-- commodity id to retrieve the commodity for the dsb_item page; set value in javascript --%>
<tsa:hidden name="Commodity_commodity" value=""/>

<%
		if (Utility.isEmpty(s_dsb_number))	{	s_dsb_number = "N/A";	}
%>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dsb-shoppingcart" , "Shopping Cart")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Disbursement #:</b></td>
			<td width=125px><%=headerEncoder.encodeForHTML(s_dsb_number)%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=125px><%=DocumentStatus.toString(s_dsb_status, oid)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center valign=top width=720px>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=680px class=browseHdrDk>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdrDk>
				<tr>
					<td width=8% class=browseHdr nowrap>&nbsp;Line #</td>
					<td width=18% class=browseHdr nowrap>&nbsp;Item/Part #</td>
					<td width=5% class=browseHdr nowrap>&nbsp;Aisle</td>
					<td <%=HtmlWriter.isVisible(oid, "dsb-row")%>  width=5% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dsb-row" , "Row")%></td>
					<td <%=HtmlWriter.isVisible(oid, "dsb-tier")%> width=5% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dsb-tier", "Tier")%></td>
					<td <%=HtmlWriter.isVisible(oid, "dsb-bin")%>  width=5% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dsb-bin" , "Bin")%></td>
					<td <%=HtmlWriter.isVisible(oid, "dsb-uom")%>  width=5% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dsb-uom" , "UI")%></td>
					<td width=10% class=browseHdr nowrap align=right>&nbsp;Quantity</td>
					<td width=25% class=browseHdr nowrap align=right>&nbsp;Price</td>
					<td width=14% class=browseHdr nowrap align=right>&nbsp;Line Total</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<table id=itemRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (i_linecount <= 0) { %>
				<tr>
					<td align=center><br><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noitems" , "There are currently no items in your shopping cart.")%></b><br><br></td>
				</tr>
<%		}
			for (int i = 0; i < i_linecount; i++)
			{
				DisbLine disbLine = (DisbLine) disbLineList.get(i);
				pageContext.setAttribute("i", i);
%>
				<tsa:hidden name="<%= \"commodityCode_\" + i %>" id="<%= \"commodityCode_\" + i%>" value="<%=disbLine.getCommodityCode() %>"/>

				<tr>
					<td width=8% height=18px class=browseRow nowrap align=right><a href="javascript: viewItem(<%=i%>); void(0);" title="Click here to View/Edit Item Details."><%=i+1%></a></td>
					<td width=18% height=18px class=browseRow nowrap>
						<%=HiltonUtility.ckNull(disbLine.getItemNumber())%>
						<tsa:hidden id="<%= \"icDsbLine_\" + i %>" name="<%= \"icDsbLine_\" + i %>" value="<%=disbLine.getIcDsbLine()%>"/>
					</td>
					<td width=5% height=18px class=browseRow nowrap><%=HiltonUtility.ckNull(disbLine.getAisle())%></td>
					<td <%=HtmlWriter.isVisible(oid, "dsb-row")%>  width=5% height=18px class=browseRow nowrap><%=HiltonUtility.ckNull(disbLine.getLocrow())%></td>
					<td <%=HtmlWriter.isVisible(oid, "dsb-tier")%> width=5% height=18px class=browseRow nowrap><%=HiltonUtility.ckNull(disbLine.getTier())%></td>
					<td <%=HtmlWriter.isVisible(oid, "dsb-bin")%>  width=5% height=18px class=browseRow nowrap><%=HiltonUtility.ckNull(disbLine.getBin())%></td>
					<td <%=HtmlWriter.isVisible(oid, "dsb-uom")%>  width=5% height=18px class=browseRow nowrap><%=HiltonUtility.ckNull(disbLine.getUmCode())%></td>
					<td width=10% height=18px class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(disbLine.getQuantity(), oid)%></td>
					<td width=25% height=18px class=browseRow nowrap align=right><%=HiltonUtility.getFormattedPrice(disbLine.getUnitPrice(), oid)%></td>
					<td width=14% height=18px class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(disbLine.getLineTotal(), oid)%></td>
				</tr>
				<tr>
					<td height=18px class=browseRow>&nbsp;</td>
					<td colspan=9 height=18px class=browseRow><%=HiltonUtility.ckNull(disbLine.getDescription())%></td>
				</tr>
<%	} %>
				</table>
			</td>

		</tr>
		</table>
<%	if (s_dsb_type.equals("O") && s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) == 0) { %>
		<br><br><br>

		<table border=0 cellspacing=0 cellpadding=2 width=450px class=browseRow align=center>
		<tr>
			<td width=1px id="filterFields">&nbsp;</td>
			<td width=100% valign=middle align=center>
				<table border=0 cellspacing=0 cellpadding=1 class=browseRow>
				<tr>
					<td valign=middle align=right nowrap>Keyword(s):</td>
					<td valign=middle align=center><input type=text name="as_keywords" value="" size=18></td>
					<tsa:td colspan="2"><div id="pxmediumbutton"><a href="javascript: itemSearch(); void(0);" id="searchbutton" onblur="document.all('itemsearchbutton').focus();return false;"">Search</a></div></tsa:td>
					<td valign=middle>
						<tsa:hidden name="as_item_type" value="INV"/>
						<tsa:hidden name="as_itemNumber" value=""/>
					</td>
				</tr>
				<tsa:tr>
					<tsa:td>&nbsp;</tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td valign="middle" align="right"><a id="itemsearchbutton" href="javascript: doSubmit('/browse/item_filter_options.jsp', 'InvCatalogRetrieve'); void(0);"><img src="<%=contextPath%>/images/cmd_search_item.gif" border="0" alt="Advanced Item Search"></a>&nbsp;&nbsp;</tsa:td>
					<tsa:td valign="middle"><a href="javascript: doSubmit('/browse/item_filter_options.jsp', 'InvCatalogRetrieve'); void(0);" title='<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-advancedItemSearch-instructions", "Click here to perform an Advanced Item Search.")%>' ><tsa:label labelName="advancedItemSearch" defaultString="Advanced Item Search"></tsa:label></a></tsa:td>
				</tsa:tr>
				</table>
			</td>
		</tr>
		</table>

<%	} %>
	</td>
	<td rowspan=2 align=right valign=top>
		<%@ include file="/inventory/dsb_sidebar.jsp" %>
	</td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var disbnumber = "<%=headerEncoder.encodeForJavaScript(s_dsb_number)%>";
	var fiscalyear = "<%=headerEncoder.encodeForJavaScript(s_fiscal_year)%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var itemLocation = "<%=s_item_location%>";

	var browser = browserCheck();

	setTableHeights();

	function setTableHeights() {
		setTableHeight("itemTable", "itemRows", 18);
	}

	function setTableHeight(hdrTableName, rowsTableName, rowHeight) {
		var browseTable = document.getElementById(hdrTableName);
		var rowsTable = document.getElementById(rowsTableName);

		var tableHeight = ((rowsTable.rows.length) * rowHeight) + 20;

		browseTable.style.height = tableHeight + "px";
	}

	function viewItem(row)
	{
		var num = document.getElementById("icDsbLine_" + row);
		frm.DisbLine_icDsbLine.value = num.value;

		var commodityCode = document.getElementById('commodityCode_' + row).value;
		frm.Commodity_commodity.value = commodityCode;

		doSubmit('/inventory/dsb_item.jsp','DisbSetProperty;DisbLineRetrieve;CommodityRetrieveById');
	}

	function highlightRow(row) {
		row = row * 2;
		var myRow = document.all.itemRows.rows(row);

		setRowClassName(myRow, "selectedRow");

		myRow = document.all.itemRows.rows(row + 1);

		setRowClassName(myRow, "selectedRow");
	}

	function removeHighlight(row) {
		row = row * 2;
		var myRow = document.all.itemRows.rows(row);

		setRowClassName(myRow, "browseRow");

		myRow = document.all.itemRows.rows(row + 1);

		setRowClassName(myRow, "browseRow");
	}

	function itemSearch()
	{
		var itemType = "INV";
		frm.browseName.value = "invitem-invbinlocation";
		setItemKeywordFilterOptions(itemType);
		doSubmit('browse/item_browse.jsp', 'BrowseRetrieve');
	}

// End Hide script -->
</SCRIPT>