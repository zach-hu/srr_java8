<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/dynamicTables.js"></SCRIPT>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");

	String	s_current_process = "ITEMS";
	String	s_current_page = "/invoice/iv_items.jsp";
	String	s_current_method = "InvoiceLineUpdateAll";
	String	s_current_process_method = "";
%>
<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=invoiceHeader.getStatus()%>"/>
<tsa:hidden name="formType" value="IVH"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="lineNumber" value=""/>
<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Items</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Invoice #:</b></td>
			<td width=150px><%=invoiceHeader.getInvoiceNumber()%></td>
		</tr>
		<tr>
			<td align=right><b>Status:</b></td>
			<td width=150px><%=DocumentStatus.toString(invoiceHeader.getStatus(), oid)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr>
			<td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center valign=top>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width="520px" align="center" class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRowHdr>
				<tr>
					<td width=5% class=browseHdrDk nowrap align=center>&nbsp;</td>
					<td width=25% class=browseHdrDk nowrap>Item/Part #</td>
					<td width=15% class=browseHdrDk nowrap><a href="javascript: browseLookup('InvoiceLine_umCode', 'unitofmeasure'); void(0);" class=sortedColumn style="text-decoration: underline;">UOM</a></td>
					<td width=15% class=browseHdrDk nowrap>Qty.</td>
					<td width=15% class=browseHdrDk nowrap align=center>Unit Cost</td>
					<td width=20% class=browseHdrDk nowrap align=center>Ext. Cost</td>
					<td width=5% class=browseHdrDk nowrap>&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<table id="items" border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
<%
	List lineitems = (List) request.getAttribute("invoiceLineList");
	boolean bCreatedFromPo = false;
	String	s_set_row = "";
	if (lineitems != null && lineitems.size() > 0)
	{
		String className = "itemSummary";
		for (int i = 0; i < lineitems.size(); i++)
		{
			s_set_row = "ONFOCUS='setCurrentRow("+i+");'";
			if (className == "itemSummary")
			{
				className="";
			}
			else
			{
				className = "itemSummary";
			}
			InvoiceLine invoiceLine = (InvoiceLine) lineitems.get(i);
			if ((invoiceLine.getIcPoLine().compareTo(new BigDecimal(0)) > 0))
			{
				bCreatedFromPo = true;
			}
			BigDecimal bdQuantity = invoiceLine.getQuantity();
			BigDecimal bdUnitPrice = invoiceLine.getUnitPrice();
			BigDecimal bdUmFactor = invoiceLine.getUmFactor();
			if (bdUmFactor.compareTo(new BigDecimal(0)) == 0)
			{
				bdUmFactor = new BigDecimal(1);
			}
			BigDecimal bdExtCost = HiltonUtility.getFormattedDollar(bdQuantity.multiply(bdUnitPrice).multiply(bdUmFactor), oid);
%>
				<tr>
					<td>
						<table class="<%=className%>">
						<tr height="25px">
							<td class="<%=className%>" align=right valign="bottom">
								<%=(i + 1)%>.
								<tsa:hidden name="InvoiceLine_invoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
								<tsa:hidden name="InvoiceLine_icIvcLine" value="<%=invoiceLine.getIcIvcLine()%>"/>
								<tsa:hidden name="InvoiceLine_icPoHeader" value="<%=invoiceLine.getIcPoHeader()%>"/>
								<tsa:hidden name="InvoiceLine_icPoLine" value="<%=invoiceLine.getIcPoLine()%>"/>
							</td>
							<td class="<%=className%>" valign="bottom">
								<input type="text" name="InvoiceLine_itemNumber" value="<%=invoiceLine.getItemNumber()%>" size="20" maxlength="30" <% if (bCreatedFromPo) { %>READONLY DISABLED<% } %> onchange="upperCase(this);">
							</td>
							<td class="<%=className%>" valign="bottom">
								<input type="text" name="InvoiceLine_umCode" value="<%=invoiceLine.getUmCode()%>" size="10" maxlength="15" onchange="upperCase(this); updateUMFactor(<%=i%>);" <%=s_set_row%>>
								<tsa:hidden name="InvoiceLine_umFactor" value="<%=bdUmFactor%>"/>
							</td>
							<td class="<%=className%>" valign="bottom"><input type="text" name="InvoiceLine_quantity" value="<%=HiltonUtility.getFormattedQuantity(invoiceLine.getQuantity(), oid)%>" size="15" style="text-align:right" onchange="addUp(<%=i%>); checkQty(<%=i%>);"></td>
							<td class="<%=className%>" valign="bottom"><input type="text" name="InvoiceLine_unitPrice" value="<%=HiltonUtility.getFormattedDollar(invoiceLine.getUnitPrice(), oid)%>" size="15" style="text-align:right" onchange="addUp(<%=i%>);"></td>
							<td class="<%=className%>" valign="bottom"><input type="text" name="InvoiceLine_lineTotal" value="<%=bdExtCost%>" size="15" style="text-align:right" READONLY tabIndex="-1"></td>
<%		if (bCreatedFromPo) { %>
							<td class="<%=className%>" width="18px" align="center" valign="bottom"><a href="javascript: displayArea('quantities_<%=i%>'); hideArea('qtyDetails_<%=i%>'); void(0);"><img id="qtyDetails_<%=i%>" src="<%=contextPath%>/supplierportal/images/cmd_maximize.gif" border=0 alt="More Details" tabIndex="-1"></a></td>
<%		} else { %>
							<td class="<%=className%>" width="18px" align="center" valign="middle"><a href="javascript: deleteMe(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/delete.gif" border=0 alt="Delete This Item" tabIndex="-1"></a></td>
<%		} %>
						</tr>
						<tr height="25px">
							<td class="<%=className%>">&nbsp;</td>
							<td class="<%=className%>" colspan="4" valign="top">
								<input type="text" name="InvoiceLine_description" value="<%=invoiceLine.getDescription()%>" size="73" maxlength="255" <% if (bCreatedFromPo) { %>READONLY DISABLED<% } %> >
							</td>
						</tr>
						</table>

						<div id="quantities_<%=i%>" style="visibility:hidden; display:none;">
						<table border=0 cellspacing=0 cellpadding=0 width=100% class="<%=className%>">
						<tr "<%=className%>">
							<td width=100% height=18px class="<%=className%>">
								<table border=0 cellspacing=0 cellpadding=1 width=95% class="<%=className%>" align="center">
								<tr>
									<td nowrap class="<%=className%>">&nbsp;<b>Ordered</b></td>
									<td nowrap class="<%=className%>">&nbsp;<b>Unit Cost</b></td>
									<td nowrap class="<%=className%>">&nbsp;<b>Order Total</b></td>
									<td nowrap class="<%=className%>">&nbsp;<b>Received</b></td>
									<td nowrap class="<%=className%>">&nbsp;<b>Invoiced</b></td>
									<td nowrap class="<%=className%>">&nbsp;<b>Unit Cost</b></td>
									<td nowrap class="<%=className%>">&nbsp;<b>Total Invoiced</b></td>
									<td class="<%=className%>" align=right valign=bottom><a href="javascript: hideArea('quantities_<%=i%>'); displayArea('qtyDetails_<%=i%>'); void(0);"><img src="<%=contextPath%>/supplierportal/images/bar_close.gif" border="0" alt="Hide Details" tabindex="-1"></a></td>
								</tr>
								<tr>
									<td nowrap class="<%=className%>" align="center"><%=HiltonUtility.getFormattedQuantity(invoiceLine.getQtyOrdered(), oid)%></td>
									<td nowrap class="<%=className%>" align="center">$<%=HiltonUtility.getFormattedDollar(invoiceLine.getOrderUnitCost(), oid)%></td>
									<td nowrap class="<%=className%>" align="center">$<%=HiltonUtility.getFormattedDollar(invoiceLine.getAmountOrdered(), oid)%></td>
									<td nowrap class="<%=className%>" align="center"><%=HiltonUtility.getFormattedQuantity(invoiceLine.getQtyReceived(), oid)%></td>
									<td nowrap class="<%=className%>" align="center"><%=HiltonUtility.getFormattedQuantity(invoiceLine.getQtyInvoiced(), oid)%></td>
									<td nowrap class="<%=className%>" align="center">$<%=HiltonUtility.getFormattedDollar(invoiceLine.getInvoiceUnitCost(), oid)%></td>
									<td nowrap class="<%=className%>" align="center">$<%=HiltonUtility.getFormattedDollar(invoiceLine.getAmountInvoiced(), oid)%></td>
									<td class="<%=className%>">
										&nbsp;
										<tsa:hidden name="qtyOrdered" value="<%=HiltonUtility.getFormattedQuantity(invoiceLine.getQtyOrdered(), oid)%>"/>
										<tsa:hidden name="qtyReceived" value="<%=HiltonUtility.getFormattedQuantity(invoiceLine.getQtyReceived(), oid)%>"/>
										<tsa:hidden name="qtyInvoiced" value="<%=HiltonUtility.getFormattedQuantity(invoiceLine.getQtyInvoiced(), oid)%>"/>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						</table>
						</div>
					</td>
				</tr>
<%		} //end for
		} //end if
%>
				</table>
			</td>
		</tr>
		</table>
		<br>
		<table id=totalsTable border=0 cellspacing=0 cellpadding=0 width="520px" class=browseRowHdr>
		<tr>
			<td valign="top">
<%	if (invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_INPROGRESS) == 0 && !bCreatedFromPo) { %>
				<table border=0 cellspacing=1 cellpadding=1>
				<tr>
					<td><a href="javascript: addNew();"><img src="<%=contextPath%>/supplierportal/images/cmd_add_item.gif" border=0 alt="Add Item"></a></td>
					<td><a href="javascript: addNew();"><font class="reset"><b>Add New</b></font></a></td>
				</tr>
				</table>
<%	} %>
			</td>
			<td>
				<table border=0 cellspacing=0 cellpadding=0>
				<tr>
					<td align="right">
<%	if ((invoiceHeader.getIcPoHeader().compareTo(new BigDecimal(0)) > 0)) { %>
						<input type="button" value="Invoice Balance" onclick="payBalance();">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%	} %>
						<b>Subtotal:&nbsp;</b>
					</td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="InvoiceHeader_invoiceSubtotal" value="<%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceSubtotal(), oid)%>" style="text-align:right" size="15" READONLY></td>
					<td width="5%">&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><b>Discount:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="InvoiceHeader_invoiceDiscount" value="<%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceDiscount(), oid)%>" style="text-align:right" size="15" onchange="updateBalance(<%//=ipl%>);"></td>
					<td width="5%">&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><b>Tax:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="InvoiceHeader_invoiceTax" value="<%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceTax(), oid)%>" style="text-align:right" size="15" onchange="updateBalance(<%//=ipl%>);"></td>
					<td width="5%">&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><b>Shipping:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="InvoiceHeader_invoiceShipping" value="<%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceShipping(), oid)%>" style="text-align:right" size="15" onchange="updateBalance(<%//=ipl%>);"></td>
					<td width="5%">&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><b>Other:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="InvoiceHeader_invoiceOther" value="<%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceOther(), oid)%>" style="text-align:right" size="15" onchange="updateBalance(<%//=ipl%>);"></td>
					<td width="5%">&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><b>Total:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="invoiceTotal" value="<%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceTotal(), oid)%>" style="text-align:right" size="15" onchange="updateBalance(<%//=ipl%>);"></td>
					<td width="5%">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" align="right"><hr width="350px"></td>
				</tr>
<%	if (invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_INPROGRESS) > 0) {%>
				<tr>
					<td align="right">
						<a href="javascript: browseStd('InvoiceHeader_reasonCode', 'RCOD'); void(0);"><b>Reason:</b></a>&nbsp;
						<input type="text" name="InvoiceHeader_reasonCode" value="<%=invoiceHeader.getReasonCode()%>" DISABLED READONLY size="20">
						&nbsp;&nbsp;<b>Rejecting:&nbsp;</b>
					</td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="InvoiceHeader_invoiceRejecting" value="<%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceRejecting(), oid)%>" style="text-align:right" size="15" onchange="updateBalance(<%//=ipl%>);"></td>
					<td width="5%">&nbsp;</td>
				</tr>
<%	} else { %>
<tsa:hidden name="InvoiceHeader_invoiceRejecting" value="<%=invoiceHeader.getInvoiceRejecting()%>"/>
<%	}%>
				<tr>
					<td align="right" class="error"><b>INVOICE BALANCE:&nbsp;</b></td>
					<td width="15%" class=browseRow nowrap align=center><input type=text name="invoiceBalance" value="<%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceTotal(), oid)%>" style="text-align:right" size="15" DISABLED READONLY></td>
					<td width="5%">&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
	<td rowspan=2 align="right" valign="top"><%@ include file="/supplierportal/invoice/iv_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<div style="visibility: hidden; display: none;">
<table>
<tr id="deleteitems"></tr>
</table>
</div>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var info_window = null;
	var populated = false;
	var uomArray = new Array();
	var currentRow = 0;

	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var dollar_dec = <%=Integer.valueOf(s_dollar_decimals).intValue()%>;

<%	if (lineitems == null || lineitems.size() <= 0) { %>
			addNew();
<%	} %>
		updateTotals();

	function thisLoad()
	{
<%	if (invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_INPROGRESS) > 0) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function addUp(row)
	{
		var qty_dec = <%=Integer.valueOf(s_quantity_decimals).intValue()%>;

		myTable = document.getElementById("items");
		count = myTable.rows.length;

		if (count == 1)
		{
			var p = nformat(eval(nfilter(frm.InvoiceLine_unitPrice)),dollar_dec);
			var q = nformat(eval(nfilter(frm.InvoiceLine_quantity)),qty_dec);
			var f = eval(nfilter(frm.InvoiceLine_umFactor));

			if (f == 0) { f = 1; }

			frm.InvoiceLine_umFactor.value = f;
			frm.InvoiceLine_unitPrice.value = p;
			frm.InvoiceLine_quantity.value = q;

			frm.InvoiceLine_lineTotal.value = nformat( p * q * f, dollar_dec );
			/*
			if ( p == q && q == 0) {
				frm.InvoiceLine_unitPrice.value = '';
				frm.InvoiceLine_quantity.value = '';
				frm.InvoiceLine_lineTotal.value = '';
			}
			*/

			frm.InvoiceHeader_invoiceSubtotal.value = frm.InvoiceLine_lineTotal.value;
		}
		else if (count > 1)
		{
			var p = nformat(eval(nfilter(frm.InvoiceLine_unitPrice[row])),dollar_dec);
			var q = nformat(eval(nfilter(frm.InvoiceLine_quantity[row])),qty_dec);
			var f = eval(nfilter(frm.InvoiceLine_umFactor[row]));

			if (f == 0) { f = 1; }

			frm.InvoiceLine_umFactor[row].value = f;
			frm.InvoiceLine_unitPrice[row].value = p;
			frm.InvoiceLine_quantity[row].value = q;

			frm.InvoiceLine_lineTotal[row].value = nformat( p * q * f, dollar_dec );
			/*
			if ( p == q && q == 0) {
				frm.InvoiceLine_unitPrice[row].value = '';
				frm.InvoiceLine_quantity[row].value = '';
				frm.InvoiceLine_lineTotal[row].value = '';
			}
			*/

			var subtotal = 0;
			for (var i = 0; i < count; i++)
			{
				subtotal = eval(subtotal) + eval(frm.InvoiceLine_lineTotal[i].value);
			}
			frm.InvoiceHeader_invoiceSubtotal.value = nformat(subtotal, dollar_dec);
		}
		updateBalance();
	}

	function addNew()
	{
		frm.deleteall.value = "FALSE";
		var myTable = document.getElementById("items");
		var count = myTable.rows.length;
		var myRow = myTable.insertRow(count);

		if (count % 2 == 0)
		{
			classname = "";
		}
		else
		{
			classname = "itemSummary";
		}
		myCell = myRow.insertCell();
		myCell.innerHTML = "<table class=" + classname + "><tr height=\"25px\"><td id\"lineitem_" + count + "\" align=\"right\" class=" + classname + ">" + (count + 1) + ".<input type=\"hidden\" name=\"InvoiceLine_invoiceNumber\" value=\"<%=invoiceHeader.getInvoiceNumber()%>\">" +
			"<input type=\"hidden\" name=\"InvoiceLine_icIvcLine\" value=\"\">" +
			"<input type=\"hidden\" name=\"InvoiceLine_icPoHeader\" value=\"\"><input type=\"hidden\" name=\"InvoiceLine_icPoLine\" value=\"\"></td>" +
			"<td id=\"itemnumber\"><input type=\"text\" name=\"InvoiceLine_itemNumber\" value=\"\" size=\"20\" maxlength=\"30\" onchange=\"upperCase(this);\"></td>" +
			"<td id=\"umCode\"><input type=\"text\" name=\"InvoiceLine_umCode\" value=\"EA\" size=\"10\" maxlength=\"15\" onchange=\"upperCase(this);\" onfocus=\"setCurrentRow(" + count + ");\"><input type=\"hidden\" name=\"InvoiceLine_umFactor\" value=\"1\"></td>" +
			"<td id=\"qty\"><input type=\"text\" name=\"InvoiceLine_quantity\" value=\"0.00\" size=\"15\" style=\"text-align:right\" onchange=\"addUp(" + count + ");\"></td>" +
			"<td id=\"price\"><input type=\"text\" name=\"InvoiceLine_unitPrice\" value=\"0.00\" size=\"15\" style=\"text-align:right\" onchange=\"addUp(" + count + ");\"></td>" +
			"<td id=\"total\"><input type=\"text\" name=\"InvoiceLine_lineTotal\" value=\"0.00\" size=\"15\" style=\"text-align:right\" READONLY tabIndex=\"-1\"></td>" +
			"<td id=\"details\" width=\"18px\" align=\"center\"><a href=\"javascript: deleteMe(" + count + "); void(0);\"><img src=\"<%=contextPath%>/supplierportal/images/delete.gif\" border=0 alt=\"Delete This Item\" tabIndex=\"-1\"></a></td>" +
			"</tr><tr height=\"25px\"><td>&nbsp;</td><td id=\"description\" colSpan=\"4\"><input type=\"text\" name=\"InvoiceLine_description\" value=\"\" size=\"73\" maxlength=\"255\"></td>" +
			"<td colSpan=\"2\">&nbsp;</td></tr></table>";
	}

	function deleteMe(row)
	{
		var myRow = document.getElementById("deleteitems");
		myCell = myRow.insertCell();

		if (frm.InvoiceLine_icIvcLine.value)
		{
			var newInputField = "<input type='hidden' name='deleteLine_lineIc' value='" + frm.InvoiceLine_icIvcLine.value + "'>";
		}
		else
		{
			var newInputField = "<input type='hidden' name='deleteLine_lineIc' value='" + frm.InvoiceLine_icIvcLine[row].value + "'>";
		}

		myCell.innerHTML = newInputField;

		var myTable = document.getElementById("items");
		var count = myTable.rows.length - 1;

		if (count > 0)
		{
			for ( var i = row; i < count; i++)
			{
				frm.InvoiceLine_icIvcLine[i].value = frm.InvoiceLine_icIvcLine[i + 1].value;
				frm.InvoiceLine_itemNumber[i].value = frm.InvoiceLine_itemNumber[i + 1].value;
				frm.InvoiceLine_umCode[i].value = frm.InvoiceLine_umCode[i + 1].value;
				frm.InvoiceLine_umFactor[i].value = frm.InvoiceLine_umFactor[i + 1].value;
				frm.InvoiceLine_quantity[i].value = frm.InvoiceLine_quantity[i + 1].value;
				frm.InvoiceLine_unitPrice[i].value = frm.InvoiceLine_unitPrice[i + 1].value;
				frm.InvoiceLine_lineTotal[i].value = frm.InvoiceLine_lineTotal[i + 1].value;
				frm.InvoiceLine_description[i].value = frm.InvoiceLine_description[i + 1].value;
			}
		}
		myTable.deleteRow(count);
		if (count <= 0)
		{
			frm.deleteall.value = "TRUE";
		}
		updateTotals();
	}

	function updateBalance()
	{
		var myTable = document.getElementById("items");
		var count = myTable.rows.length;
		var invoiceTotal = <%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceTotal(), oid)%>;

		if (count == 0)
		{
			frm.InvoiceHeader_invoiceSubtotal.value = "0.00";
			frm.InvoiceHeader_invoiceDiscount.value = "0.00";
			frm.InvoiceHeader_invoiceTax.value = "0.00";
			frm.InvoiceHeader_invoiceShipping.value = "0.00";
			frm.InvoiceHeader_invoiceOther.value = "0.00";
			frm.InvoiceHeader_invoiceRejecting.value = "0.00";
			frm.invoiceTotal.value = "0.00";
			frm.invoiceBalance.value = invoiceTotal;
		}
		else
		{

			var subtotal = nformat(eval(nfilter(frm.InvoiceHeader_invoiceSubtotal)),dollar_dec);
			var discount = nformat(eval(nfilter(frm.InvoiceHeader_invoiceDiscount)),dollar_dec);
			var tax = nformat(eval(nfilter(frm.InvoiceHeader_invoiceTax)),dollar_dec);
			var shipping = nformat(eval(nfilter(frm.InvoiceHeader_invoiceShipping)),dollar_dec);
			var other = nformat(eval(nfilter(frm.InvoiceHeader_invoiceOther)),dollar_dec);
			var rejecting = nformat(eval(nfilter(frm.InvoiceHeader_invoiceRejecting)),dollar_dec);

			frm.InvoiceHeader_invoiceDiscount.value = discount;
			frm.InvoiceHeader_invoiceTax.value = tax;
			frm.InvoiceHeader_invoiceShipping.value = shipping;
			frm.InvoiceHeader_invoiceOther.value = other;
			frm.InvoiceHeader_invoiceRejecting.value = rejecting;

			frm.invoiceTotal.value = nformat( (eval(subtotal) - eval(discount) + eval(tax) + eval(shipping) + eval(other)), dollar_dec );
			frm.invoiceBalance.value = nformat( eval(invoiceTotal - frm.invoiceTotal.value) - eval(rejecting), dollar_dec);
		}
	}

	function updateTotals()
	{
		var myTable = document.getElementById("items");
		var count = myTable.rows.length;
		var subtotal = 0;

		if (count == 1)
		{
			if (frm.InvoiceLine_lineTotal[0])
			{
				frm.InvoiceHeader_invoiceSubtotal.value = frm.InvoiceLine_lineTotal[0].value;
			}
			else
			{
				frm.InvoiceHeader_invoiceSubtotal.value = frm.InvoiceLine_lineTotal.value;
			}
		}
		else if (count > 1)
		{
			for (var i = 0; i < count; i++)
			{
				subtotal = eval(subtotal) + (eval(frm.InvoiceLine_lineTotal[i].value));
			}
			frm.InvoiceHeader_invoiceSubtotal.value = nformat(subtotal, dollar_dec);
		}
		updateBalance();
	}

	function validateForm()
	{
		var myTable = document.getElementById("items");
		var count = myTable.rows.length;

		if (count == 1)
		{
			if (frm.InvoiceLine_itemNumber.value.length <= 0 && frm.InvoiceLine_description.value.length <= 0)
			{
				frm.deleteall.value = "TRUE";
			}
		}
		else if (count > 1)
		{
			for (var i = count; i > 0; i--)
			{
				if (i == 1 && !frm.InvoiceLine_itemNumber[0])
				{
					if (frm.InvoiceLine_itemNumber.value.length <= 0 && frm.InvoiceLine_description.value.length <= 0)
					{
						deleteMe(i - 1);
					}
				}
				else
				{
					if (frm.InvoiceLine_itemNumber[i - 1].value.length <= 0 && frm.InvoiceLine_description[i - 1].value.length <= 0)
					{
						deleteMe(i - 1);
					}
				}
			}
		}
		return true;
	}

	function checkQty(row)
	{
		var myTable = document.getElementById("items");
		var count = myTable.rows.length;
		var qtyOrdered = 0;
		var qtyInvoiced = 0;
		var qtyRemaining = 0;
		var quantity = 0;

		if (count == 1)
		{
			qtyOrdered = frm.qtyOrdered.value;
			if (eval(qtyOrdered > 0))
			{
				quantity = frm.InvoiceLine_quantity.value;
				if (eval(quantity) > eval(qtyOrdered))
				{
					alert("Please note that the quantity you entered is greater than the quantity ordered.");
					return;
				}
				qtyInvoiced = frm.qtyInvoiced.value;
				qtyRemaining = eval(qtyOrdered) - eval(qtyInvoiced);
				if (eval(quantity) > eval(qtyRemaining))
				{
					alert("Please note that the quantity you entered is greater than the remaining balance.");
				}
			}
		}
		else if (count > 1)
		{
			qtyOrdered = frm.qtyOrdered[row].value;
			if (eval(qtyOrdered > 0))
			{
				quantity = frm.InvoiceLine_quantity[row].value;
				if (eval(quantity) > eval(qtyOrdered))
				{
					alert("Please note that the quantity you entered is greater than the quantity ordered.");
					return;
				}
				qtyInvoiced = frm.qtyInvoiced[row].value;
				qtyRemaining = eval(qtyOrdered) - eval(qtyInvoiced);
				if (eval(quantity) > eval(qtyRemaining))
				{
					alert("Please note that the quantity you entered is greater than the remaining balance.");
				}
			}
		}
	}

	function updateUMFactor(row)
	{
		var open = true;
		var browser = browserTest();
		var factor = "";
		var code = "";

		if (frm.InvoiceLine_umCode[row])
		{
			frm.InvoiceLine_umCode[row].value = trim(frm.InvoiceLine_umCode[row]);
		}
		else
		{
			frm.InvoiceLine_umCode.value = trim(frm.InvoiceLine_umCode);
		}

		if (info_window != null) {
			if (browser == "Netscape") {
				if (info_window.closed == false) {
					info_window.setUomCode("InvoiceLine_");
					open = false;
				}
			}
			else {
				info_window.setUomCode("InvoiceLine_");
				open = false;
			}
		}

		if (open == true)
		{
			if (uomArray.length > 0 || populated)
			{
				if (frm.InvoiceLine_umCode[row])
				{
					code = frm.InvoiceLine_umCode[row].value;
				}
				else
				{
					code = frm.InvoiceLine_umCode.value;
				}
				for (var i = 0; i < uomArray.length; i++)
				{
					if (code == (uomArray[i][0]).toString())
					{
						factor = uomArray[i][1];
						break;
					}
				}

				setUmFactor(factor, row);
			}
			else
			{
				popupParameters = "as_prefix=InvoiceLine_;as_row=" + row;
				doSubmitToLookup('/base/get_uom_info.jsp', 'UnitOfMeasureRetrieveAll', 'WIDTH=1', 'HEIGHT=1');
				self.focus();
			}
		}
	}

	function setUmFactor(factor, row)
	{
		if (frm.InvoiceLine_umFactor[row])
		{
			frm.InvoiceLine_umFactor[row].value = factor;
		}
		else
		{
			frm.InvoiceLine_umFactor.value = factor;
		}
		addUp(row);
	}

	function populateUOM(uoma)
	{
		var e = 0;

		for (var i = 0; i < uoma.length; i++) {
			uomArray[e] = new Array(uoma[i][0], uoma[i][1]);
			e++;
		}

		populated = true;
	}

	function setCurrentRow(row) {
		currentRow = row;
	}

	function payBalance() {
<%	if (lineitems != null) {%>
			var count = <%=lineitems.size()%>;
			var qtyOrdered = 0;
			var qtyInvoiced = 0;
			var qtyRemaining = 0;

			if (count == 1)
			{
				if (frm.InvoiceLine_quantity[0])
				{
					qtyOrdered = frm.qtyOrdered.value;
					qtyInvoiced = frm.qtyInvoiced.value;
					qtyRemaining = eval(qtyOrdered) - eval(qtyInvoiced);
					if (eval(qtyRemaining) < 0)
					{
						qtyRemaining = 0;
					}
					frm.InvoiceLine_quantity[0].value = nformat(qtyRemaining, dollar_dec);
					addUp(0);
				}
				else
				{
					qtyOrdered = frm.qtyOrdered.value;
					qtyInvoiced = frm.qtyInvoiced.value;
					qtyRemaining = eval(qtyOrdered) - eval(qtyInvoiced);
					if (eval(qtyRemaining) < 0)
					{
						qtyRemaining = 0;
					}
					frm.InvoiceLine_quantity.value = nformat(qtyRemaining, dollar_dec);
					addUp(0);
				}
			}
			else if (count > 1)
			{
				for (var i = 0; i < count; i++)
				{
					qtyOrdered = frm.qtyOrdered[i].value;
					qtyInvoiced = frm.qtyInvoiced[i].value;
					qtyRemaining = eval(qtyOrdered) - eval(qtyInvoiced);
					if (eval(qtyRemaining) < 0)
					{
						qtyRemaining = 0;
					}
					frm.InvoiceLine_quantity[i].value = nformat(qtyRemaining, dollar_dec);
					addUp(i);
				}
			}
<%	} %>
	}


// End Hide script -->
</SCRIPT>