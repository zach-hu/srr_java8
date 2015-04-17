<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/shipto.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	String	s_quantity_decimals = PropertiesManager.getInstance(oid).getProperty("MISC", "QtyDecimals", "2");
	String	s_icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String	s_rfqNumber = (String) request.getAttribute("RfqHeader_rfqNumber");
	String	s_rfqAmendment = (String) request.getAttribute("RfqHeader_rfqAmendment");
	String	s_rfqStatus = (String) request.getAttribute("RfqHeader_status");
	String	s_fiscalYear = (String) request.getAttribute("RfqHeader_fiscalYear");
	String	s_icRfqLine = (String) request.getAttribute("RfqLine_icRfqLine");
	String	s_lineNumber = (String) request.getAttribute("RfqLine_rfqLine");
	String	s_itemQty = (String) request.getAttribute("RfqLine_quantity");
	String	s_lineCount = (String) request.getAttribute("lineCount");
	String	s_fromPage = HiltonUtility.ckNull((String) request.getAttribute("frompage"));
	String	s_set_row = "";
	Date		d_required_by = (Date) request.getAttribute("requiredBy");

	if (d_required_by == null) {
		d_required_by = d_today;
	}
	String	s_required_date = HiltonUtility.getFormattedDate(d_required_by, oid, userDateFormat);

	if (HiltonUtility.isEmpty(s_itemQty))		{	s_itemQty = "0";	}
	BigDecimal	bd_itemQty = new BigDecimal(s_itemQty);

	boolean editMode = false;

	if (role.getAccessRights("RFQ") > 1 && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENAMENDMENT) == 0)) {
		editMode = true;
	}
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_fiscalYear" value="<%=s_fiscalYear%>"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqLine_icRfqLine" value="<%=s_icRfqLine%>"/>
<tsa:hidden name="ShipTo_icHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="ShipTo_icLine" value="<%=s_icRfqLine%>"/>
<tsa:hidden name="RfqLine_rfqLine" value="<%=s_lineNumber%>"/>
<tsa:hidden name="lineCount" value="<%=s_lineCount%>"/>
<tsa:hidden name="frompage" value="<%=s_fromPage%>"/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Line Item <%=s_lineNumber%> - Shipping Schedule</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/rfq/rfq_status_title.jsp" %>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
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
<%@ include file="/rfq/rfq_info.jsp" %>
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
			<td width=17% ALIGN="CENTER" NOWRAP colspan=2><A HREF="javascript: browseSchedule('ShipTo_shipToCode', 'ship_to'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-shipToCode", "Ship To")%> for this item or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-shipToCode", "Ship To")%> in the box below."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-shipToCode", "Ship To", true)%></a></td>
			<td width=22% ALIGN="CENTER"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-shp-addressLine1", "Address 1")%></td>
			<td width=14% ALIGN="CENTER"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-quantity", "Quantity")%></td>
			<td width=22% ALIGN="CENTER"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-shp-attention", "Attention")%></td>
			<td width=16% ALIGN="CENTER" NOWRAP><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-requiredDate", "Required By", true)%></td>
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
					<input type=text name=ShipTo_shipDate tabindex=<%=(i*4) + 4%> size=15 maxlength=10 value="<%=HiltonUtility.getFormattedDate(shipTo.getShipDate(), oid, userDateFormat)%>" <%=s_set_row%>>
				</td>
				<td ID=shp_cal valign=middle><a href="javascript: getDateField(<%=i%>); void(0);"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." border=0></a></td>
<%		if (editMode) { %>
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
<% } %>
		<tr>
			<td colspan=9 align=right><br></td>
		</tr>
		<tr valign=top>
			<td colspan=3 align=right valign=middle><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-totalShipQuantity", "Total Ship Quantity")%>:  </B></td>
			<td>
				<input type=text name="as_tot_qty" size=15 maxlength=15 value="" style="text-align:right" onfocus="this.blur();" disabled>
				<tsa:hidden name="as_line_qty" value="<%=bd_itemQty%>"/>
			</td>
			<td align=right valign=middle nowrap><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-totalLineQuantity", "Total Line Quantity")%>:  </B></td>
			<td valign=middle>&nbsp;<%=bd_itemQty%></td>
			<td colspan=2 width=50px>&nbsp;</td>
		</tr>
<%	if (editMode) { %>
		<tr valign=top>
			<td colspan=2>&nbsp;<a href="javascript: addNew(); void(0)"><font class="reset"><B>Add new</B></font></a></td>
			<td colspan=3>&nbsp;</td>
			<td colspan=3 align=right NOWRAP><a href="javascript:  deleteAll();void(0)"><font class="reset"><B>Delete all</B></font></a>&nbsp;</td>
		</tr>
<% } %>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (editMode) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('rfq/rfq_item.jsp', 'ShipToUpdateByLine;RfqLineRetrieve'); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('rfq/rfq_item.jsp', 'RfqLineRetrieve'); void(0);">Return</a></div></td>
<%	} else {%>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: doSubmit('rfq/rfq_item.jsp', 'RfqLineRetrieve'); void(0);">Return</a></div></td>
<%	}%>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm=document.purchaseform;
	var maxRows = frm.as_maxrows.value;
	var defaultQty = <%=bd_itemQty%>;
	var defaultDate = "<%=s_required_date%>";
	var qtyDecimals = <%=Integer.valueOf(s_quantity_decimals).intValue()%>;
	var fldObject = null;
	var fldObject2 = null;
	var fldFromObject = null;
	var vWinCal = null;
	var currentRow = 0;
	var myTable = document.getElementById("shp_schedule");
	var displayReqdDate = <%=DictionaryManager.getLabelsInstance(oid, language).isVisible(oid, "rfq-requiredDate")%>;

	addUp();

	function setFieldFocus()
	{
		if (myTable.rows.length <= 0)
		{
			addNew();
		}
	}

	function thisLoad()
	{
		f_StartIt();
<%	if (!editMode) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
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

// End Hide script -->
</SCRIPT>
