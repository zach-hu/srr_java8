<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.util.Date.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/shipto.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	String	s_quantity_decimals = PropertiesManager.getInstance(oid).getProperty("MISC", "QtyDecimals", "2");
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_from_page = HiltonUtility.ckNull((String) request.getAttribute("frompage"));
	String	s_req_ic_header = (String) request.getAttribute("RequisitionHeader_icReqHeader");
	String	s_req_number = (String) request.getAttribute("RequisitionHeader_requisitionNumber");
	String 	s_req_type = (String) request.getAttribute("RequisitionHeader_requisitionType");
	String	s_req_status = (String) request.getAttribute("RequisitionHeader_status");
	Date		d_required_by = (Date) request.getAttribute("requiredBy");
	String	s_required_by = HiltonUtility.getFormattedDate(d_required_by, oid, userDateFormat);
	String	s_req_ic_line = (String) request.getAttribute("RequisitionLine_icReqLine");
	String	s_line_number = (String) request.getAttribute("RequisitionLine_lineNumber");
	String	s_item_qty = (String) request.getAttribute("RequisitionLine_quantity");
	String	s_fiscal_year = (String) request.getAttribute("RequisitionHeader_fiscalYear");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_itemLocation"));
	String	s_req_subtype = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_rqSubType"));

	if (HiltonUtility.isEmpty(s_req_number))	{	s_req_number = "N/A";	}
	if (s_item_qty == null)							{	s_item_qty = "0";		}
	if (s_fiscal_year == null)							{	s_fiscal_year = (String) request.getAttribute("fiscalYear");		}

	BigDecimal	b_item_qty = new BigDecimal(s_item_qty);
	String	s_set_row = "";
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value='<%=HiltonUtility.ckNull((String) request.getAttribute(\"RequisitionHeader_requisitionNumber\"))%>'/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value="<%=s_req_ic_line%>"/>
<tsa:hidden name="ShipTo_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="ShipTo_icLine" value="<%=s_req_ic_line%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="fiscalYear" value="<%=s_fiscal_year%>"/>

<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
	<%if (s_req_type.equals("M")) { %>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="req-type" defaultString="Req Type"/>&nbsp;<tsa:label labelName="lineItem" defaultString="Line Item"/> <%=s_line_number%> - <tsa:label labelName="shippingSchedule" defaultString="Shipping Schedule"/></div>
			</tsa:td>
		</tsa:tr>
	<% } else { %>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="lineItem" defaultString="Line Item"/> <%=s_line_number%> - <tsa:label labelName="shippingSchedule" defaultString="Shipping Schedule"/></div>
			</tsa:td>
		</tsa:tr>
	<% } %>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tsa:tr>
			<tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" align="middle" width="*">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tsa:tr>
			 <tsa:td align="right" width="70%"><b><tsa:label labelName="req-requisition" defaultString="Requisition #"/>:</b>&nbsp;</tsa:td>
			 <tsa:td align="left" width="30%"><%=headerEncoder.encodeForHTML(s_req_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			 <tsa:td align="right" width="70%"><b><tsa:label labelName="status" defaultString="Status"/>:</b>&nbsp;</tsa:td>
			 <tsa:td align="left" width="30%"><%=DocumentStatus.toString(s_req_status, oid)%></tsa:td>
		</tsa:tr>
		</table>
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tsa:tr><tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td></tsa:tr>
			<tsa:tr><tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td></tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>
<%@ include file="/requests/req_info.jsp" %>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
	<tsa:td align="center" valign="top" width="520px">
		<table border="0" width="100%">
		<tsa:tr valign="middle">
			<tsa:td colspan="9">&nbsp;</tsa:td>
		</tsa:tr>
		<tsa:tr valign="middle">
			<!--td width=5%>&nbsp;</td-->
			<tsa:td width="17%" align="CENTER" noWrap="nowrap" colspan="2"><A HREF="javascript: browseSchedule('ShipTo_shipToCode', 'ship_to'); void(0);" title='Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-shipToCodeLn", "Ship To")%> for this item or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-shipToCodeLn", "Ship To")%> in the box below.'><tsa:label labelName="req-shipToCodeLn" defaultString="Ship To" checkRequired="true"/></A></tsa:td>
			<tsa:td width="22%" align="CENTER"><tsa:label labelName="req-shp-addressLine1" defaultString="Address 1"/></tsa:td>
			<tsa:td width="14%" align="CENTER"><tsa:label labelName="req-quantity" defaultString="Quantity"/></tsa:td>
			<tsa:td width="22%%" align="CENTER"><tsa:label labelName="req-shp-attentionln" defaultString="Attention" checkRequired="true"/></tsa:td>
			<tsa:td width="16%" align="CENTER" noWrap="nowrap" field="req-requiredDateLn"><tsa:label labelName="req-requiredDateln" defaultString="Required By" checkRequired="true"/></tsa:td>
			<tsa:td colspan="3">&nbsp;</tsa:td>
		</tsa:tr>
		<tsa:tr valign="middle">
			<tsa:td colspan="9"></tsa:td>
		</tsa:tr>
<%
	int i_rowcount = 0;

	List shipToList = (List) request.getAttribute("shipToList");
	if (shipToList != null)
	{%>
	<tsa:tr valign="middle">
		<tsa:td colspan="9">
			<table ID="shp_schedule" border="0" width="100%">
		<% for (int i = 0;i < shipToList.size(); i++)
		{
			s_set_row = "setCurrentRow("+i+");";
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
			<tsa:tr valign="top">
				<tsa:td>&nbsp;</tsa:td>
				<tsa:td width="15%" id="shp_code"><tsa:input type="mintext" name="ShipTo_shipToCode" tabIndex="<%=\"\"+((i*4) + 1)%>" maxLength="15" value="<%=s_ship_code%>" onchange="upperCase(this); getNewInfo('ShipTo_shipToCode', this, <%=i%>);" onfocus="<%=s_set_row%>"/></tsa:td>
				<tsa:td width="25%" id="shp_addr"><tsa:input type="text" name="Address_addressLine1" size="25" maxLength="30" value="<%=s_address_line_1%>" onfocus="this.blur();" disabled="disabled"/></tsa:td>
				<tsa:td width="15%" id="shp_qty"><tsa:input type="mintext" name="ShipTo_quantity" tabIndex="<%=\"\"+((i*4) + 2)%>" maxLength="25" value="<%=b_ship_qty%>" style="text-align: right" onchange="addUp();" onfocus="<%=s_set_row%>"/></tsa:td>
				<tsa:td width="25%" id="shp_attn"><tsa:input type="text" name="ShipTo_attention" tabIndex="<%=\"\"+((i*4) + 3)%>" size="25" maxLength="40" value="<%=HiltonUtility.ckNull(s_ship_attn)%>" onfocus="<%=s_set_row%>"/></tsa:td>
				<tsa:td id="shp_info" field="req-requiredDateLn">
					<tsa:input type="mintext" name="ShipTo_shipDate" tabIndex="<%=\"\"+((i*4) + 4)%>" maxLength="10" value="<%=HiltonUtility.getFormattedDate(shipTo.getShipDate(), oid, userDateFormat)%>" onfocus="<%=s_set_row%>"/>
				</tsa:td>
				<tsa:td id="shp_cal" valign="middle" field="req-requiredDateLn"><a href="javascript: getDateField(<%=i%>); void(0);"><img src="<%=contextPath%>/images/calendar.gif" alt="CLick here to choose a date or enter a date in the box on the left." border="0"></a></tsa:td>
<%		if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0) { %>
				<tsa:td id="shp_del_<%=i%>" valign="middle"><a href="javascript: deleteMe(<%=i%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0"></a></tsa:td>
<%		} %>
				<tsa:td id="shp_info">&nbsp;
					<tsa:hidden name="ShipTo_shipToPriority" value="<%=s_ship_priority%>"/>
				</tsa:td>
			</tsa:tr>
<%
		} %>
					<tsa:hidden name="as_maxrows" value="<%=shipToList.size()%>"/>
			</table>
			</tsa:td>
		</tsa:tr>
<%
	}
	else
	{
%>
		<tsa:tr valign="middle">
			<tsa:td colspan="9">
			<table ID="shp_schedule" border="0" width="100%">
			<tsa:tr valign="top">
				<tsa:td width="4%">&nbsp;</tsa:td>
				<tsa:td width="10%" id="shp_code"></tsa:td>
				<tsa:td width="15%" id="shp_addr"></tsa:td>
				<tsa:td width="10%" id="shp_qty"></tsa:td>
				<tsa:td width="20%" id="shp_attn"></tsa:td>
				<tsa:td width="10%" id="shp_info"></tsa:td>
				<tsa:td width="11%" id="shp_cal" valign="middle" field="req-requiredDateLn"></tsa:td>
				<tsa:td width="11%" id="shp_del_0" valign="middle"></tsa:td>
				<tsa:td width="9%">&nbsp;</tsa:td>
			</tsa:tr>
					<tsa:hidden name="as_maxrows" value="0"/>
			</table>
			</tsa:td>
		</tsa:tr>
<%} %>
		<tsa:tr>
			<tsa:td colspan="9" align="right"><br></tsa:td>
		</tsa:tr>
		<tsa:tr valign="top">
			<tsa:td colspan="3" align="right" valign="middle"><B><tsa:label labelName="req-totalShipQuantity" defaultString="Total Ship Quantity"/>:  </B></tsa:td>
			<tsa:td>
				<tsa:input type="mintext" name="as_tot_qty" maxLength="15" value="" style="text-align: right" onfocus="this.blur();" disabled="disabled"/>
				<tsa:hidden name="as_line_qty" value="<%=b_item_qty%>"/>
			</tsa:td>
			<tsa:td align="right" valign="middle" noWrap="nowrap"><B><tsa:label labelName="req-totalLineQuantity" defaultString="Total Line Quantity"/>:  </B></tsa:td>
			<tsa:td valign="middle">&nbsp;<%=b_item_qty%></tsa:td>
			<tsa:td colspan="2" width="50px">&nbsp;</tsa:td>
		</tsa:tr>
<%		if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0) { %>
		<tsa:tr valign="top">
			<tsa:td colspan="2">&nbsp;<a href="javascript: addNew(); void(0)"><span class="reset"><B><tsa:label labelName="addNew" defaultString="Add new"/></B></span></a></tsa:td>
			<tsa:td colspan="3">&nbsp;</tsa:td>
			<tsa:td colspan="3" align="right" noWrap="nowrap"><a href="javascript:  deleteAll();void(0)"><span class="reset"><B><tsa:label labelName="deleteAll" defaultString="delete all"/></B></span></a>&nbsp;</tsa:td>
		</tsa:tr>
<%	} %>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
<%	if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0) { %>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('requests/req_item.jsp', 'ShipToUpdateByLine;RequisitionLineRetrieve'); void(0);"><tsa:label labelName="req-save" defaultString="Save"/></a></div></tsa:td>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('requests/req_item.jsp', 'RequisitionLineRetrieve'); void(0);"><tsa:label labelName="req-return" defaultString="Return"/></a></div></tsa:td>
<%	} else {%>
	<tsa:td width="100%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('requests/req_item.jsp', 'RequisitionLineRetrieve'); void(0);"><tsa:label labelName="req-return" defaultString="Return"/></a></div></tsa:td>
<%	}%>
</tsa:tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value="JavaScript">
<!-- Hide script

	frm=document.purchaseform;
	var maxRows = frm.as_maxrows.value;
	var defaultQty = <%=b_item_qty%>;
	var defaultDate = "<%=s_required_by%>";
	var qtyDecimals = <%=Integer.valueOf(s_quantity_decimals).intValue()%>;
	var fldObject = null;
	var fldObject2 = null;
	var fldFromObject = null;
	var vWinCal = null;
	var currentRow = 0;
	var allowEdit;
	var myTable = document.getElementById("shp_schedule");
	var displayReqdDate = <%=DictionaryManager.getLabelsInstance(oid, language).isVisible(oid, "req-requiredDateLn")%>;

	addUp();

	function setFieldFocus()
	{
		if (myTable.rows.length == undefined || myTable.rows.length <= 0)
		{
			addNew();
		}
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
<%		if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

// End Hide script -->
</SCRIPT>