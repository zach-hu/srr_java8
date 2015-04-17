<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.menu.MenuManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.tsa.puridiom.common.utility.HiltonUtility"%>
<%@page import="com.tsa.puridiom.entity.PoLine"%>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/browse.js"></SCRIPT>
<%

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_ic_po_header = (String) request.getAttribute("PoHeader_icPoHeader");

	String poLineFromRFQ = (String) request.getAttribute("poLineFromRFQ");

	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(0);
	BigDecimal	bd_revision_number = new BigDecimal(0);
	String	s_release_number = HiltonUtility.ckNull(request.getAttribute("PoHeader_releaseNumber")).toString();
	String	s_revision_number = HiltonUtility.ckNull(request.getAttribute("PoHeader_revisionNumber")).toString();
	if (!HiltonUtility.isEmpty(s_release_number))
	{
		bd_release_number = new BigDecimal(s_release_number);
	}
	if (!HiltonUtility.isEmpty(s_revision_number))
	{
		bd_revision_number = new BigDecimal(s_revision_number);
	}
	String	s_po_status = (String) request.getAttribute("PoHeader_status");
	String	s_po_type = (String) request.getAttribute("PoHeader_poType");
	String	s_fiscal_year = (String) request.getAttribute("PoHeader_fiscalYear");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_itemLocation"));
	String	s_from_header = "N";
	String	s_po_action = (String) request.getAttribute("poaction");


	//List	poLineList = (List) request.getAttribute("poItemsFromReq");
	PoLine poLine = (PoLine) request.getAttribute("poLine");

	BigDecimal bd_ic_po_header = poLine.getIcPoHeader();
	BigDecimal bd_ic_po_line = poLine.getIcPoLine();
	String	s_po_number = poLine.getPoNumber();

	Integer numPoItemsFromReq = (Integer) request.getAttribute("numPoItemsFromReq");
	int	i_linecount = 0;


	if (HiltonUtility.isEmpty(s_po_type))	{	s_po_type = "PO";				}
	if (HiltonUtility.isEmpty(s_po_status))	{	s_po_status = DocumentStatus.PO_INPROGRESS;			}

%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="PoHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="lineCount" value="<%=i_linecount%>"/>
<tsa:hidden name="formtype" value="PO"/>
<tsa:hidden name="icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="skipdeletecheck" value="Y"/>
<tsa:hidden name="poaction" value="<%= s_po_action %>"/>

<tsa:hidden name="PoLine_icPoLine" value="<%=bd_ic_po_line%>"/>


<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tr>
			<td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deleteoptions", "Delete Options", false)%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign="bottom"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign="bottom" align="right" height="30px">
		<%@ include file="/orders/po_display_number.jsp" %>
	</td>
</tr>
</table>

<br>
<br>
	<table border="0" cellpadding="0" cellspacing="0" width="680px" height="100px">
		<tr>
			<td align="center" valign="top" width="520px">
				<table border="0" cellspacing="0" cellpadding="0" width="450px">
					<tr>
						<td width="100%" align="center" valign="top">
							<table border="0" cellspacing="0" cellpadding="2" width="100%">
								<tr>
									<th align="left" valign="top" width="520px" class="browseHdrP"> Please enter a Reason for deleting Item:</th>
								<tr>
								<tr>
									<td align="left" valign="top" width="520px" class="browseRow"><textarea wrap="virtual" name="order_historyreason" tabindex="4" rows="5" cols="80" onKeyDown="textCounter(this, 2000);" onKeyUp="textCounter(this,2000);" onchange="textCounter(this,2000);"></textarea><td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td align="center" valign="top" width="520px">
		<table id="itemTable" border="1" cellspacing="0" cellpadding="0" width="450px">
		<tr>
			<td width="100%" align="center" valign="top">
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdrDk">
				<tr>
					<td width=7% height=18px class="browseHdrDk" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-lineNumber", "Line #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "po-itemNumber")%> width="18%" height="18px" class="browseHdrDk" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-itemNumber", "Item/Part #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "po-commodity")%> width="15%" height="18px" class="browseHdrDk" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-commodity", "Commodity")%></td>
					<td <%=HtmlWriter.isVisible(oid, "po-quantity")%> width="10%" height="18px" class="browseHdrDk" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-quantity", "Quantity")%></td>
					<td <%=HtmlWriter.isVisible(oid, "po-extendedCost")%> width="15%" height="18px" class="browseHdrDk" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-extendedCost", "Extended Cost")%></td>
						<td class="browseHdrDk" nowrap>Open</td>
						<td class="browseHdrDk" nowrap>Close</td>
						<td class="browseHdrDk" nowrap>Cancel</td>

				</tr>
				</table>

			</td>
		</tr>
		<tr>
			<td width=100% align="center" valign="top">
				<table id=itemRows border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">

		<%

				BigDecimal line = HiltonUtility.getBigDecimalFormatted(poLine.getLineNumber(), 0);
				BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid);
				BigDecimal bd_unit_price = HiltonUtility.getFormattedDollar(poLine.getUnitPrice(), oid);
				BigDecimal bd_um_factor = HiltonUtility.getFormattedDollar(poLine.getUmFactor(), oid);
				if (bd_um_factor.compareTo(new BigDecimal(0)) == 0)
				{
					bd_um_factor = new BigDecimal(1);
				}
				BigDecimal bd_extended_cost = HiltonUtility.getFormattedDollar(bd_quantity.multiply(bd_unit_price).multiply(bd_um_factor), oid);
%>
				<tr>
					<td width=7% height="18px" class=browseRow nowrap align="right"><a href="javascript: viewItem(); void(0);" title="Click here to View Item Details." ><%=poLine.getLineNumber()%></a>&nbsp;&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "po-itemNumber")%> width=18% height="18px" class=browseRow nowrap>
						<%=poLine.getItemNumber()%>

					</td>
					<td <%=HtmlWriter.isVisible(oid, "po-commodity")%> width=15% height="18px" class=browseRow nowrap><%=poLine.getCommodity()%>&nbsp;&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "po-quantity")%> width=10% height="18px" class=browseRow nowrap align="right"><%=HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid)%>&nbsp;&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "po-extendedCost")%> width=15% height="18px" class=browseRow nowrap align="right"><%=bd_extended_cost%>&nbsp;&nbsp;</td>

					<%if (poLineFromRFQ.equals("1")) {
					%>
						<td> <input type="radio" name="delete_option" value="<%=DocumentStatus.RFQ_PURCHASING%>" checked></td>
					<% }
					else{%>
						<td> <input type="radio" name="delete_option" value="<%=DocumentStatus.REQ_APPROVED%>" checked></td>
					<% }%>
					<td> <input type="radio" name="delete_option" value="<%=DocumentStatus.CLOSED%>"> </td>
					<td> <input type="radio" name="delete_option" value="<%=DocumentStatus.CANCELLED%>"> </td>

				</tr>
				<tr>
					<td width="5%" height="18px" class="browseRow" nowrap>&nbsp;</td>
					<td colspan="8" height="18px" class="browseRow"><%=poLine.getDescription()%></td>
				</tr>

				<tsa:hidden name="RequisitionLine_status_<%=line%>" value=""/>

<%		 %>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td width=50% align="center"><a href="javascript: cancelMe(); void(0);"><img class=button src="<%=contextPath%>/images/button_ok.gif" border=0 alt="Save"></a></td>
	<td width=50% align="center"><a href="javascript: doSubmit('/orders/po_review.jsp', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var ponumber = "<%= headerEncoder.encodeForJavaScript(s_po_number) %>";
	var fiscalyear = "<%= headerEncoder.encodeForJavaScript(s_fiscal_year) %>";

	var browser = browserCheck();

	setTableHeights();
	//hideLineReasons();

	function setTableHeights()
	{
		setTableHeight("itemTable", "itemRows", 18);
	}

	function setTableHeight(hdrTableName, rowsTableName, rowHeight)
	{
		var browseTable = document.getElementById(hdrTableName);
		var rowsTable = document.getElementById(rowsTableName);

		var tableHeight = ((rowsTable.rows.length) * rowHeight) + 20;

		browseTable.style.height = tableHeight + "px";
	}


	function highlightRow(row)
	{
		row = row * 3;
		var myRow = document.all.itemRows.rows(row);

		setRowClassName(myRow, "selectedRow");

		myRow = document.all.itemRows.rows(row + 1);

		setRowClassName(myRow, "selectedRow");
	}

	function removeHighlight(row)
	{
		row = row * 3;
		var myRow = document.all.itemRows.rows(row);

		setRowClassName(myRow, "browseRow");

		myRow = document.all.itemRows.rows(row + 1);

		setRowClassName(myRow, "browseRow");
	}

	function viewItem()
	{
		doSubmit('/orders/po_item.jsp','PoLineRetrieve');
	}

	function cancelMe()
	{
		checkHeaderReason();
	}

	function viewBox(area)
	{
		displayArea(area);
	}

	function checkHeaderReason()
	{
		if (isEmpty(frm.order_historyreason.value))
		{
			alert('Please enter a reason why are you deleting this Item.');
		}
		else
		{
			doSubmit('/orders/po_review.jsp', 'PoLineDelete;PoRetrieve');
		}
		return false;
	}

// End Hide script -->
</SCRIPT>