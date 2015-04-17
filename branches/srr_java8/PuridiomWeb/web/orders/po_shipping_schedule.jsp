<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.util.Date.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/shipto.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

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
	String	s_po_type = (String) request.getAttribute("PoHeader_poType");
	String	s_fiscal_year = (String) request.getAttribute("PoHeader_fiscalYear");
	String	s_po_status = (String) request.getAttribute("PoLine_status");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_itemLocation"));
	String	s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_buyerCode"));
	String	s_ic_po_line = (String) request.getAttribute("PoLine_icPoLine");
	String	s_line_number = (String) request.getAttribute("PoLine_lineNumber");
	String	s_item_qty = (String) request.getAttribute("PoLine_quantity");

	Date		d_required_by = (Date) request.getAttribute("requiredBy");
	String	s_required_date = HiltonUtility.getFormattedDate(d_required_by, oid, userDateFormat);

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
<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="PoHeader_status" value="<%=(String) request.getAttribute(\"PoHeader_status\")%>"/>
<tsa:hidden name="PoHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="PoHeader_buyerCode" value="<%=s_buyer_code%>"/>
<tsa:hidden name="PoLine_icPoLine" value="<%=s_ic_po_line%>"/>
<tsa:hidden name="ShipTo_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="ShipTo_icLine" value="<%=s_ic_po_line%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="deleteall" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Line Item <%=s_line_number%> - Shipping Schedule</div>
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
			<td width=200px><%=s_po_number%></td>
<%	if (bd_release_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b>Release #:</b></td>
			<td width=200px><%=bd_release_number%></td>
<%	}
		if (bd_revision_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b>Revision #:</b></td>
			<td width=200px><%=bd_revision_number%></td>
<%	} %>
		</tr>
		<tr>
			<td colspan=<%=i_colspan%> nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=200px><%=DocumentStatus.toString(s_po_status, oid)%></td>
		</tr>
		</table>
		<table cellpadding=0 cellspacing=0 border=0 width=100%>
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
	<td align=center valign=top width=520px>
		<table border=0 width=100%>
		<tr valign=middle>
			<td colspan=9>&nbsp;</td>
		</tr>
		<tr valign=middle>
			<!--td width=5%>&nbsp;</td-->
			<td width=17% ALIGN="CENTER" NOWRAP colspan=2><A HREF="javascript: browseSchedule('ShipTo_shipToCode', 'ship_to'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shipToCodeLn", "Ship To")%> for this item or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shipToCodeLn", "Ship To")%> in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shipToCodeLn", "Ship To", true)%></a></td>
			<td width=22% ALIGN="CENTER"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shp-addressLine1", "Address 1")%></td>
			<td width=14% ALIGN="CENTER"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-quantity", "Quantity")%></td>
			<td width=22%% ALIGN="CENTER"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shp-attentionln", "Attention", true)%></td>
			<td width=16% ALIGN="CENTER" NOWRAP><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-requiredDate", "Required By", true)%></td>
			<td colspan=3>&nbsp;</td>
		</tr>
		<tr valign=middle>
			<td colspan=9></td>
		</tr>
		<tr valign=middle>
			<td colspan=9>
			<table ID=shp_schedule border=0 width=100%>
<%
	int i_rowcount = 0;
	List shipToList = (List) request.getAttribute("shipToList");
	if (shipToList != null)
	{
		for (int i = 0;i < shipToList.size(); i++)
		{
			s_set_row = "ONFOCUS='setCurrentRow("+i+");'";
			ShipTo shipTo = (ShipTo) shipToList.get(i);
			String s_ship_attn = shipTo.getAttention();
			BigDecimal b_ship_qty = shipTo.getQuantity();
			Date	d_ship_date = shipTo.getShipDate();
			String	s_ship_date = "";
			if (d_ship_date != null)
			{
				s_ship_date = HiltonUtility.getFormattedDate(d_ship_date, oid, userDateFormat);
			}
			ShipToPK shipToPK = shipTo.getComp_id();
			String s_ship_code = shipToPK.getShipToCode();
			String s_ship_priority = shipToPK.getShipToPriority();
			BigDecimal b_ship_icHeader = shipToPK.getIcHeader();
			BigDecimal b_ship_icLine = shipToPK.getIcLine();
			String	s_address_line_1 = "";

			if (s_ship_attn == null)	{	s_ship_attn = "";	}

			Address shipToAddress = (Address) shipTo.getShipToAddress();
			if (shipToAddress != null)
			{
				s_address_line_1 = shipToAddress.getAddressLine1();
			}
%>
			<tr valign=top>
				<td>&nbsp;</td>
				<td width=15% ID=shp_code><input type=text name="ShipTo_shipToCode" tabindex=<%=(i*4) + 1%> size=15 maxlength=15 value="<%=s_ship_code%>" onchange="upperCase(this); getNewInfo('ShipTo_shipToCode', this, <%=i%>);" <%=s_set_row%>></td>
				<td width=25% ID=shp_addr><input type=text name="Address_addressLine1" size=25 maxlength=30 value="<%=s_address_line_1%>" onfocus="this.blur();" disabled></td>
				<td width=15% ID=shp_qty><input type=text name="ShipTo_quantity" tabindex=<%=(i*4) + 2%> size=15 maxlength=25 value="<%=b_ship_qty%>" style="text-align:right" onchange="addUp();" <%=s_set_row%>></td>
				<td width=25% ID=shp_attn><input type=text name="ShipTo_attention" tabindex=<%=(i*4) + 3%> size=25 maxlength=40 value="<%=s_ship_attn%>" <%=s_set_row%>></td>
				<td ID=shp_info>
					<input type=text name=ShipTo_shipDate tabindex=<%=(i*4) + 4%> size=15 maxlength=10 value="<%=s_ship_date%>" onchange="checkDate(this);" <%=s_set_row%>>
				</td>
				<td ID=shp_cal valign=middle><a href="javascript: getDateField(<%=i%>); void(0);"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." border=0></a></td>
<%	if (s_po_status.compareTo(DocumentStatus.PO_REJECTED) <= 0 && bAllowEdit) { %>
				<td ID=shp_del_<%=i%> valign=middle><a href="javascript: deleteMe(<%=i%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
<%		} %>
				<td ID=shp_info>&nbsp;
					<tsa:hidden name="ShipTo_shipToPriority" value="<%=s_ship_priority%>"/>
				</td>
			</tr>
<%
		} %>
					<tsa:hidden name="as_maxrows" value="<%=shipToList.size()%>"/>
			</table>
			</td>
		</tr>
<%
	}
	else
	{
%>
		<tr valign=middle>
			<td colspan=9>
			<table ID=shp_schedule border=0 width=100%>
			<tr valign=top>
				<td width=4%>&nbsp;</td>
				<td width=10% ID=shp_code></td>
				<td width=15% ID=shp_addr></td>
				<td width=10% ID=shp_qty></td>
				<td width=20% ID=shp_attn></td>
				<td width=10% ID=shp_info></td>
				<td width=11% ID=shp_cal valign=middle></td>
				<td width=11% ID=shp_del_0 valign=middle></td>
				<td width=9%>&nbsp;</td>
			</tr>
					<tsa:hidden name="as_maxrows" value="0"/>
			</table>
			</td>
	</tr>
<%} %>
		<tr>
			<td colspan=9 align=right><br></td>
		</tr>
		<tr valign=top>
			<td colspan=3 align=right valign=middle><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-ln-shipToQty", "Total Ship Quantity") %>:  </B></td>
			<td>
				<input type=text name="as_tot_qty" size=15 maxlength=15 value="" style="text-align:right" onfocus="this.blur();" disabled>
				<tsa:hidden name="as_line_qty" value="<%=bd_item_qty%>"/>
			</td>
			<td align=right valign=middle nowrap><B>Total Line Quantity:  </B></td>
			<td valign=middle>&nbsp;<%=bd_item_qty%></td>
			<td colspan=2 width=50px>&nbsp;</td>
		</tr>
<%	if ( bAllowEdit )  { %>
		<tr valign=top>
			<td colspan=2>&nbsp;<a href="javascript: addNew(); void(0)"><font class="reset"><B>Add new</B></font></a></td>
			<td colspan=3>&nbsp;</td>
			<td colspan=3 align=right NOWRAP><a href="javascript:  deleteAll();void(0)"><font class="reset"><B>delete all</B></font></a>&nbsp;</td>
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
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/orders/po_item.jsp', 'ShipToUpdateByLine;PoLineRetrieve'); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/orders/po_item.jsp', 'PoLineRetrieve'); void(0);">Return</a></div></td>
<%	} else {%>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: doSubmit('/orders/po_item.jsp', 'PoLineRetrieve'); void(0);">Return</a></div></td>
<%	}%>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm=document.purchaseform;
	var maxRows = frm.as_maxrows.value;
	var defaultQty = <%=bd_item_qty%>;
	var defaultDate = "<%=s_required_date%>";
	var qtyDecimals = <%=Integer.valueOf(s_quantity_decimals).intValue()%>;
	var fldObject = null;
	var fldObject2 = null;
	var fldFromObject = null;
	var vWinCal = null;
	var currentRow = 0;
	var allowEdit;
	var myTable = document.getElementById("shp_schedule");
	var displayReqdDate = <%=DictionaryManager.getLabelsInstance(oid, language).isVisible(oid, "po-requiredDate")%>;

	addUp();

	function setFieldFocus()
	{
<%	if ( bAllowEdit )  { %>
			if (myTable.rows.length <= 0)
			{
				addNew();
			}
<%	}%>
	}

	function getDateField(row)
	{
		if (row == 0 && maxRows == 1)
		{
			show_calendar('ShipTo_shipDate', '<%=userDateFormat%>');
		}
		else
		{
			show_calendar('ShipTo_shipDate[' + row + ']', '<%=userDateFormat%>');
		}
	}

	function thisLoad()
	{
		f_StartIt();
<%	if ( !bAllowEdit ) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

// End Hide script -->
</SCRIPT>