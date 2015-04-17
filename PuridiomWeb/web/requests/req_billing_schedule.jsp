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
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_from_page = HiltonUtility.ckNull((String) request.getAttribute("frompage"));
	String	s_req_ic_header = (String) request.getAttribute("RequisitionHeader_icReqHeader");
	String	s_req_number = (String) request.getAttribute("RequisitionHeader_requisitionNumber");
	String	s_req_status = (String) request.getAttribute("RequisitionHeader_status");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_itemLocation"));
	String	s_req_ic_line = (String) request.getAttribute("RequisitionLine_icReqLine");
	String	s_line_number = (String) request.getAttribute("RequisitionLine_lineNumber");
	String	s_item_qty = (String) request.getAttribute("RequisitionLine_quantity");
	String	s_fiscal_year = (String) request.getAttribute("RequisitionHeader_fiscalYear");
	String	s_req_subtype = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_rqSubType"));

	if (HiltonUtility.isEmpty(s_req_number))	{	s_req_number = "N/A";	}
	if (s_item_qty == null)							{	s_item_qty = "0";		}
	if (s_fiscal_year == null)							{	s_fiscal_year = (String) request.getAttribute("fiscalYear");		}
	BigDecimal	b_item_qty = new BigDecimal(s_item_qty);
	String	s_set_row = "";
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"RequisitionHeader_requisitionNumber\"))%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value="<%=s_req_ic_line%>"/>
<tsa:hidden name="BillTo_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="BillTo_icLine" value="<%=s_req_ic_line%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="fiscalYear" value="<%=s_fiscal_year%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class=hdr12> - <tsa:label labelName="lineItem" defaultString="Line Item" /><%=s_line_number%><tsa:label labelName="billingSchedule" defaultString="Billing Schedule" /></div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="requisitionNumber" defaultString="Requisition #" />:</b></tsa:td>
			<tsa:td width="125px"><%=headerEncoder.encodeForHTML(s_req_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="status" defaultString="Status" />:</b></tsa:td>
			<tsa:td width="125px"><%=DocumentStatus.toString(s_req_status, oid)%></tsa:td>
		</tsa:tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
		<tsa:tr>
			<tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>
<%@ include file="/requests/req_info.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tsa:tr>
	<tsa:td align="center" valign="top">
		<table border=0 width=618px cellpadding=2 cellspacing=0>
		<tsa:tr valign="top">
				<tsa:td noWrap="nowrap" width="100px">&nbsp;<A HREF="javascript: browseSchedule('BillTo_billToCode', 'bill_to'); void(0);" title="Click here to choose the Bill To Code for this item or enter the Bill To Code in the box below."><tsa:label labelName="ln-req-billToCode" defaultString="Bill To" checkRequired="true"/></tsa:td>
				<tsa:td noWrap="nowrap" width="200px">&nbsp;<tsa:label labelName="req-bil-addressLine1" defaultString="Address" /></tsa:td>
				<tsa:td noWrap="nowrap" width="100px">&nbsp;<tsa:label labelName="req-quantity" defaultString="Quantity" /></tsa:td>
				<tsa:td noWrap="nowrap" width="200px">&nbsp;<tsa:label labelName="req-bil-attention" defaultString="Attention" checkRequired="true"/></tsa:td>
				<tsa:td width="10px"><img src="<%=contextPath%>/images/none.gif" border=0 width=10px height=1px></tsa:td>
		</tsa:tr>
		</table>

		<table ID=bil_schedule border=0 width=618px cellpadding=2 cellspacing=0>
<%	int i_rowcount = 0;
		List billToList = (List) request.getAttribute("billToList");

		if (billToList != null) {
			for (int i = 0;i < billToList.size(); i++)
			{
				s_set_row = "setCurrentRow("+i+");";
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
		<tsa:tr>
			<tsa:td width="100px" id="bil_code"><tsa:input type="mintext" name="BillTo_billToCode" tabIndex="<%=\"\"+((i*4) + 1)%>" maxLength="15" value="<%=s_bil_code%>" onchange="upperCase(this); getNewInfo('BillTo_billToCode', this, <%=i%>);" onfocus="<%=s_set_row%>"/></tsa:td>
			<tsa:td width="200px" id="bil_addr"><tsa:input type="text" name="Address_addressLine1" size="35" maxLength="30" value="<%=s_address_line_1%>" onfocus="this.blur();" disabled="disabled"/></tsa:td>
			<tsa:td width="100px" id="bil_qty"><tsa:input type="mintext" name="BillTo_quantity" tabIndex="<%=\"\"+((i*4) + 2)%>" maxLength="25" value="<%=b_bil_qty%>" style="text-align:right" onchange="addUp(<%=i%>);" onfocus="<%=s_set_row%>"/></tsa:td>
			<tsa:td width="200px" id="bil_attn"><tsa:input type="text" name="BillTo_attention" tabIndex="<%=\"\"+((i*4) + 3)%>" size="30" maxLength="40" value="<%=s_bil_attn%>" onfocus="<%=s_set_row%>"/></tsa:td>
<%			if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0) { %>
			<tsa:td width="10px" id="bil_del_0" valign="middle"><a href="javascript: deleteMe(<%=i%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0"></a></tsa:td>
<%			} %>
		</tsa:tr>
<%		}
		}%>
		</table>

		<tsa:hidden name="as_maxrows" value="<%=billToList.size()%>"/>
		<br>

		<table border=0  width=616px cellpadding=2 cellspacing=0>
		<tsa:tr valign="top">
				<tsa:td noWrap="nowrap" width="302px" align="right"><b><tsa:label labelName="req-totalBillQuantity" defaultString="Total Bill To Quantity" />: </b></tsa:td>
				<tsa:td noWrap="nowrap" width="100px">
					<tsa:input type="mintext" name="as_tot_qty" maxLength="15" value="" style="text-align:right" onfocus="this.blur();" disabled="true"/>
					<tsa:hidden name="as_line_qty" value="<%=b_item_qty%>"/>
				</tsa:td>
				<tsa:td noWrap="nowrap" width="135px" align="right"><b><tsa:label labelName="req-totalLineQuantity" defaultString="Total Line Quantity" />: </b></tsa:td>
				<tsa:td width="75px"><%=b_item_qty%></tsa:td>
		</tsa:tr>
<%		if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0) { %>
		<tsa:tr valign="top">
			<tsa:td colspan="2">&nbsp;<a href="javascript: addNew(); void(0)"><font class="reset"><b><tsa:label labelName="addNew" defaultString="Add new" /></B></font></a></tsa:td>
			<tsa:td>&nbsp;</tsa:td>
			<tsa:td align="right" noWrap="nowrap"><a href="javascript: deleteAll(); void(0)"><font class="reset"><b><tsa:label labelName="deleteAll" defaultString="delete all" /></B></font></a>&nbsp;</tsa:td>
		</tsa:tr>
<%	} %>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tsa:tr>
<%	if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0) { %>
	<tsa:td width="50%" align="center"><a href="javascript: doSubmit('requests/req_item.jsp', 'BillToUpdateByLine;RequisitionLineRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 tabindex=100></a></tsa:td>
	<tsa:td width="50%" align="center"><a href="javascript: doSubmit('requests/req_item.jsp', 'RequisitionLineRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 tabindex=101></a></tsa:td>
<%	} else {%>
	<tsa:td width="100%" align="center"><a href="javascript: doSubmit('requests/req_item.jsp', 'RequisitionLineRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 tabindex=101></a></tsa:td>
<%	}%>
</tsa:tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var maxRows = frm.as_maxrows.value;
	var qtyDecimals = <%=Integer.valueOf(s_quantity_decimals).intValue()%>;
	var defaultQty = <%=b_item_qty%>;
	var fldObject = null;
	var fldObject2 = null;
	var fldFromObject = null;
	var currentRow = 0;
	var myTable = document.getElementById("bil_schedule");

	addUp();

	function setFieldFocus()
	{
		if (myTable.rows.length == undefined || myTable.rows.length <= 0)
		{
			addNew();
		}
	}

	function thisLoad()
	{
		f_StartIt();
<%		if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0) { %>
			checkInputSettings();
<%	} %>
	}

// End Hide script -->
</SCRIPT>