<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.menu.MenuManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/browse.js"></SCRIPT>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_ic_po_header = (String) request.getAttribute("PoHeader_icPoHeader");
	String	s_po_number = "";
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(0);
	BigDecimal	bd_revision_number = new BigDecimal(0);
	String	s_release_number = HiltonUtility.ckNull(request.getAttribute("PoHeader_releaseNumber")).toString();
	String	s_revision_number = HiltonUtility.ckNull(request.getAttribute("PoHeader_revisionNumber")).toString();
	String	s_budgetActive = propertiesManager.getProperty("MODULES","ACCOUNT BUDGET","N") ;
	if (s_budgetActive == null) {
		s_budgetActive = "N" ;
	}

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


	List	poLineList = (List) request.getAttribute("poItemsFromReq");
	if (poLineList == null)
	{
		poLineList = (List) request.getAttribute("poItemsFromRfq");
	}

	Integer numPoItemsFromReq = HiltonUtility.ckNull((Integer) request.getAttribute("numPoItemsFromReq"));
	Integer numPoItemsFromRfq = HiltonUtility.ckNull((Integer) request.getAttribute("numPoItemsFromRfq"));
	int	i_linecount = 0;

	if (poLineList != null)
	{
		i_linecount = poLineList.size();

		if (i_linecount > 0)
		{
			PoLine poLine = (PoLine) poLineList.get(0);
			s_po_number = poLine.getPoNumber();
		}

		if ((i_linecount > 1) || (i_linecount == 0))
		{
			s_from_header = "Y";
		}

	}

	if (HiltonUtility.isEmpty(s_po_type))	{	s_po_type = "PO";				}
	if (HiltonUtility.isEmpty(s_po_status))	{	s_po_status = DocumentStatus.PO_INPROGRESS;			}

	String cancelOptions = propertiesManager.getProperty("PO OPTIONS","ITEMS CANCEL OPTIONS", "OPEN");
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
<tsa:hidden name="skipcancelcheck" value="Y"/>
<tsa:hidden name="poaction" value="<%= s_po_action %>"/>
<tsa:hidden name="frompage" value="poitemscancel"/>
<tsa:hidden name="oldStatus" value="<%= s_po_status %>"/>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tr>
			<td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%= (s_po_action.equals("cancel")) ? DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "canceloptions", "Cancel Options", false) : DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "closeoptions", "Close Options", false)%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
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
									<th align="left" valign="top" width="520px" class="browseHdrP"> Please enter a Reason for <%= (s_po_action.equals("cancel")) ? "canceling" : "closing"%> Order:</th>
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

<div id=itemArea style="display:block;visibility:visible;">
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
					<% if ( ( (numPoItemsFromReq.intValue() > 0) || (numPoItemsFromRfq.intValue() > 0) ) && (!s_po_action.equals("close")) )
					{ %>
						<td class="browseHdrDk" nowrap>Open</td>
						<td class="browseHdrDk" nowrap>Close</td>
						<td class="browseHdrDk" nowrap>Cancel</td>
					<% } %>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align="center" valign="top">
				<table id=itemRows border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
<%		if (i_linecount <= 0) { %>
				<tr>
					<td align="center"><br><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noitems", "There are currently no items in your shopping cart.")%></b><br><br></td>
				</tr>
<%		}
			for (int i = 0; i < i_linecount; i++)
			{
				PoLine poLine = (PoLine) poLineList.get(i);
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
					<td width=7% height="18px" class=browseRow nowrap align="right"><a href="javascript: viewItem(<%=poLine.getIcPoLine()%>); void(0);" title="Click here to View Item Details." onMouseOver="highlightRow(<%=i%>);" onMouseOut="removeHighlight(<%=i%>);"><%=poLine.getLineNumber()%></a>&nbsp;&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "po-itemNumber")%> width=18% height="18px" class=browseRow nowrap>
						<%=poLine.getItemNumber() %>
						<tsa:hidden name="<%= \"icPoLine_\" + i %>" id="<%= \"icPoLine_\" + i %>" value="<%=poLine.getIcPoLine()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "po-commodity")%> width=15% height="18px" class=browseRow nowrap><%=poLine.getCommodity()%>&nbsp;&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "po-quantity")%> width=10% height="18px" class=browseRow nowrap align="right"><%=HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid)%>&nbsp;&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "po-extendedCost")%> width=15% height="18px" class=browseRow nowrap align="right"><%=bd_extended_cost%>&nbsp;&nbsp;</td>
					<% if (!s_po_action.equals("close")) {
							if ( ( poLine.getIcReqLine().compareTo(new BigDecimal(0)) > 0 ) || ( poLine.getIcRfqLine().compareTo(new BigDecimal(0)) > 0 ) )
							{ %>
					<td> <input type="radio" name="cancel_option_<%=line%>" <%if(cancelOptions.equalsIgnoreCase("OPEN")){%>checked<%}%>></td>
					<td> <input type="radio" name="cancel_option_<%=line%>" <%if(cancelOptions.equalsIgnoreCase("CLOSE")){%>checked<%}%>></td>
					<td> <input type="radio" name="cancel_option_<%=line%>" <%if(cancelOptions.equalsIgnoreCase("CANCEL")){%>checked<%}%>></td>
							<%} else if ( (numPoItemsFromReq.intValue() > 0) || (numPoItemsFromRfq.intValue() > 0) )
							{ %>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
							<%}
						}%>
				</tr>
				<tr>
					<td width="5%" height="18px" class="browseRow" nowrap>&nbsp;</td>
					<td colspan="8" height="18px" class="browseRow"><%=poLine.getDescription()%></td>
				</tr>
				<%-- tr>
					<td width="5%" height="18px" class="browseRow" nowrap>&nbsp;</td>
					<td colspan="8" height="18px" class="browseRow"><a href="javascript: toggleReasonDisplay('linereason_<%=line%>'); void(0);"><img src="<%=contextPath%>/images/arrows_down.gif" id="linereason_<%=line%>Img" height="11" border="0"/></a>&nbsp;Reason</td>
				</tr>
				<tr id="linereason_<%=line%>">
					<td width="5%" height="18px" class="browseRow" nowrap>&nbsp;</td>
					<td colspan="8" height="18px" class="browseRow"><textarea wrap="virtual" name="historyreason_<%=line%>" tabindex="4" rows="5" cols="60" onKeyDown="textCounter(this, 2000);" onKeyUp="textCounter(this,2000);" onchange="textCounter(this,2000);"></textarea></td>
				</tr--%>
				<tsa:hidden name="<%= \"RequisitionLine_status_\" + line %>" value=""/>
				<tsa:hidden name="<%= \"RfqLine_status_\" + line %>" value=""/>
				<tsa:hidden name="<%= \"PoLine_icPoLine_\" + line%>" value="<%=poLine.getIcPoLine()%>"/>
<%		} %>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
</div>
<br>
<br>

<table border=0 cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td width=50% align="center"><div id="pxbutton"><a href="javascript: cancelMe(); void(0);">Ok</a></div></td>
	<td width=50% align="center"><div id="pxbutton"><a href="javascript: doSubmit('/orders/po_review.jsp', 'PoRetrieve'); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var ponumber = "<%= headerEncoder.encodeForJavaScript(s_po_number) %>";
	var fiscalyear = "<%= headerEncoder.encodeForJavaScript(s_fiscal_year) %>";
	var budgetActive = "<%= headerEncoder.encodeForJavaScript(s_budgetActive) %>" ;


	var browser = browserCheck();

	setTableHeights();

	if (budgetActive == "CX") {
		hideArea("itemArea") ;
	}
	// ***** ADD CODE HERE TO PROCESS BUDGET TRANSACTION. ALEXANDER
	
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

	function viewItem(ic)
	{
		var newInputField = "<input type=\"hidden\" name=\"PoLine_icPoLine\" value=" + ic + ">";
	 	setHiddenFields(newInputField);
		doSubmit('/orders/po_item.jsp','PoLineRetrieve');
	}

	function cancelMe()
	{
		cancelAll();
	}

	function cancelAll()
	{
		var newInputField;
		var selectValue = '<%= DocumentStatus.CLOSED %>';
		var selectValueRfq = '<%= DocumentStatus.CLOSED %>';
		var budgetActive = '<%= headerEncoder.encodeForJavaScript(s_budgetActive) %>' ;

		<%for (int i = 0; i < i_linecount; i++)
		{
			PoLine poLine = (PoLine) poLineList.get(i);

			if (poLine.getIcReqLine().compareTo(new BigDecimal(0)) > 0 || poLine.getIcRfqLine().compareTo(new BigDecimal(0)) > 0)
			{
			BigDecimal line = HiltonUtility.getBigDecimalFormatted(poLine.getLineNumber(), 0);
				if (!s_po_action.equals("close"))
				{
			%>

			if(frm.cancel_option_<%=line%>[0].checked)
			{
				<% if ( numPoItemsFromReq.intValue() > 0) {%>
					selectValue = '<%=DocumentStatus.REQ_APPROVED%>';
				<%} else {	%>
					selectValueRfq = '<%=DocumentStatus.RFQ_PURCHASING%>';
				<%}%>
			}
			else if(frm.cancel_option_<%=line%>[1].checked)
			{//close
				selectValue = '<%=DocumentStatus.CLOSED%>';
				selectValueRfq = '<%=DocumentStatus.CLOSED%>';
			}
			else if(frm.cancel_option_<%=line%>[2].checked)
			{//cancel
				selectValue = '<%=DocumentStatus.CANCELLED%>';
				selectValueRfq = '<%=DocumentStatus.CANCELLED%>';
			}
			<% 	} %>

			frm.RequisitionLine_status_<%=line%>.value = selectValue;
			frm.RfqLine_status_<%=line%>.value = selectValueRfq;

		<%	}
			if (!s_from_header.equalsIgnoreCase("Y"))
			{%>
			newInputField = "<input type=\"hidden\" name=\"PoLine_icPoLine\" value=" + <%=poLine.getIcPoLine()%>+ ">";

			if (selectValue != undefined) {
				newInputField = newInputField + "<input type=\"hidden\" name=\"RequisitionLine_status\" value=" + selectValue + ">";
			}
			if (selectValueRfq != undefined) {
				newInputField = newInputField + "<input type=\"hidden\" name=\"RfqLine_status\" value=" + selectValueRfq + ">";
			}
			setHiddenFields(newInputField);
			<%}
		}
		%>
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
			alert('Please enter a reason why are you <%= (s_po_action.equals("cancel")) ? "canceling" : "closing"%> this Order.');
		}
		else
		{
			<%if(s_from_header.equalsIgnoreCase("Y"))
			{%>
				doSubmit('/orders/po_review.jsp', 'PoCancel;PoRetrieve');
			<%}
			else
			{%>
				doSubmit('/orders/po_review.jsp', 'PoLineCancel;PoRetrieve');
			<%}%>
		}
		return false;
	}

// End Hide script -->
</SCRIPT>