
<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/billto.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_ic_po_header = (String) request.getAttribute("PoHeader_icPoHeader");
	String	s_po_number = (String) request.getAttribute("PoHeader_poNumber");
	String	s_release_number = (String) request.getAttribute("PoHeader_releaseNumber");
	String	s_revision_number = (String) request.getAttribute("PoHeader_revisionNumber");
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	String	s_fiscal_year = (String) request.getAttribute("PoHeader_fiscalYear");
	String	s_po_status = (String) request.getAttribute("PoLine_status");
	String	s_po_type = (String) request.getAttribute("PoHeader_poType");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_itemLocation"));
	String	s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_buyerCode"));
	String	s_ic_po_line = (String) request.getAttribute("PoLine_icPoLine");
	String	s_line_number = (String) request.getAttribute("PoLine_lineNumber");
	String	s_item_qty = (String) request.getAttribute("PoLine_quantity");

	if (s_po_number == null)	{	s_po_number = "N/A";	}
	if (s_item_qty == null)		{	s_item_qty = "0";		}

	BigDecimal	bd_item_qty = new BigDecimal(s_item_qty);
	String	s_set_row = "";

	boolean bAllowEdit = true;
	if ( role.getAccessRights("PO") < 2 || (!bAdminChanges && s_po_status.compareTo(DocumentStatus.PO_REJECTED) > 0)  || (s_po_status.compareTo(DocumentStatus.CANCELLED) >= 0) || s_po_status.compareTo(DocumentStatus.PO_AMENDED) == 0)
	{
		bAllowEdit = false;
	}
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer())
	{
		bAllowEdit = false;
	}
%>

<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="PoHeader_status" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"PoHeader_status\"))%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="PoHeader_buyerCode" value="<%=s_buyer_code%>"/>
<tsa:hidden name="PoLine_icPoLine" value="<%=s_ic_po_line%>"/>
<tsa:hidden name="BillTo_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="BillTo_icLine" value="<%=s_ic_po_line%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Line Item <%=s_line_number%> - Billing Schedule</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
<%	int i_colspan = 1; %>
		<tr>
			<td nowrap align=right><b>Order #:</b></td>
			<td width=100px><%=s_po_number%></td>
<%	if (bd_release_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b>Release #:</b></td>
			<td width=100px><%=bd_release_number%></td>
<%	}
		if (bd_revision_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b>Revision #:</b></td>
			<td width=100px><%=bd_revision_number%></td>
<%	} %>
		</tr>
		<tr>
			<td colspan=<%=i_colspan%> nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=100px><%=DocumentStatus.toString(s_po_status, oid)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<%@ include file="/orders/po_info.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center valign=top>
		<table border=0 width=618px cellpadding=2 cellspacing=0>
		<tr valign=top>
				<td nowrap width=100px>&nbsp;<A HREF="javascript: browseSchedule('BillTo_billToCode', 'bill_to'); void(0);" title="Click here to choose the Bill To Code for this item or Enter the Bill To Code in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ln-po-billToCode", "Bill To", true)%></td>
				<td nowrap width=200px>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-bil-addressLine1", "Address")%></td>
				<td nowrap width=100px>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-quantity", "Quantity")%></td>
				<td nowrap width=200px>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-bil-attention", "Attention", true)%></td>
				<td width=10px><img src="<%=contextPath%>/images/none.gif" border=0 width=10px height=1px></td>
		</tr>
		</table>

		<table ID=bil_schedule border=0 width=618px cellpadding=2 cellspacing=0>
<%	int i_rowcount = 0;
		List billToList = (List) request.getAttribute("billToList");

		if (billToList != null) {
		for (int i = 0;i < billToList.size(); i++)
		{
			s_set_row = "ONFOCUS='setCurrentRow("+i+");'";
			BillTo billTo = (BillTo) billToList.get(i);
			String s_bil_attn = billTo.getAttention();
			BigDecimal b_bil_qty = billTo.getQuantity();
			BillToPK billToPK = billTo.getComp_id();
			String s_bil_code = billToPK.getBillToCode();
			BigDecimal b_bil_icHeader = billToPK.getIcHeader();
			BigDecimal b_bil_icLine = billToPK.getIcLine();
			String	s_address_line_1 = "";

			Address billToAddress = (Address) billTo.getBillToAddress();
			if (billToAddress != null)
			{
				s_address_line_1 = billToAddress.getAddressLine1();
			}
%>
		<tr>
			<td width=100px ID=bil_code><input type=text name="BillTo_billToCode" size=15 maxlength=15 value="<%=s_bil_code%>" onchange="upperCase(this); getNewInfo('BillTo_billToCode', this, <%=i%>);" <%=s_set_row%>></td>
			<td width=200px ID=bil_addr><input type=text name="Address_addressLine1" size=35 maxlength=30 value="<%=s_address_line_1%>" onfocus="this.blur();" disabled></td>
			<td width=100px ID=bil_qty><input type=text name="BillTo_quantity" size=15 maxlength=25 value="<%=b_bil_qty%>" style="text-align:right" onchange="addUp(<%=i%>);" <%=s_set_row%>></td>
			<td width=200px ID=bil_attn><input type=text name="BillTo_attention" size=30 maxlength=40 value="<%=s_bil_attn%>" <%=s_set_row%>></td>
<%	if ( bAllowEdit )  { %>
			<td width=10px ID=bil_del_0 valign=middle><a href="javascript: deleteMe(<%=i%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0"></a></td>
<%		} %>
		</tr>
<%	}
	}%>
		</table>

		<tsa:hidden name="as_maxrows" value="<%=billToList.size()%>"/>
		<br>

		<table border=0  width=616px cellpadding=2 cellspacing=0>
		<tr valign=top>
				<td nowrap width=302px align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-totalBillQuantity", "Total Bill To Quantity")%>: </b></td>
				<td nowrap width=100px>
					<input type=text name="as_tot_qty" size=15 maxlength=15 value="" style="text-align:right" onfocus="this.blur();" disabled>
					<tsa:hidden name="as_line_qty" value="<%=bd_item_qty%>"/>
				</td>
				<td nowrap width=135px align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-totalLineQuantity", "Total Line Quantity")%>: </b></td>
				<td width=75px><%=bd_item_qty%></td>
		</tr>
<%	if ( bAllowEdit )  { %>
		<tr valign=top>
			<td colspan=2>&nbsp;<a href="javascript: addNew(); void(0)"><font class="reset"><b>Add new</B></font></a></td>
			<td>&nbsp;</td>
			<td align=right nowrap><a href="javascript: deleteAll(); void(0)"><font class="reset"><b>delete all</B></font></a>&nbsp;</td>
		</tr>
<%	} %>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if ( bAllowEdit )  { %>
	<td width=50% align=center><a href="javascript: doSubmit('/orders/po_item.jsp', 'BillToUpdateByLine;PoLineRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/orders/po_item.jsp', 'PoLineRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
<%	} else {%>
	<td width=100% align=center><a href="javascript: doSubmit('/orders/po_item.jsp', 'PoLineRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
<%	}%>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var maxRows = frm.as_maxrows.value;
	var qtyDecimals = <%=Integer.valueOf(s_quantity_decimals).intValue()%>;
	var defaultQty = <%=bd_item_qty%>;
	var fldObject = null;
	var fldObject2 = null;
	var fldFromObject = null;
	var currentRow = 0;
	var myTable = document.getElementById("bil_schedule");

	function setFieldFocus()
	{
<%	if ( bAllowEdit )  { %>
			if (myTable.rows.length <= 0)
			{
				addNew();
			}
<%	}%>
	}

	addUp();

	function thisLoad()
	{
		f_StartIt();
<%	if ( !bAllowEdit ) { %>
			checkInputSettings();
<%	} %>
	}

// End Hide script -->
</SCRIPT>
