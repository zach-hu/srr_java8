<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("RequisitionHeader");
	String	s_req_ic_header = (String) request.getAttribute("RequisitionHeader_icReqHeader");
	String	s_req_number = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_requisitionNumber"));
	String	s_req_status = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_status"));
	String	s_req_type = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_requisitionType"));
	String	s_req_subtype = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_rqSubType"));
	String	s_fiscal_year = (String) request.getAttribute("RequisitionHeader_fiscalYear");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_itemLocation"));
	String	refresh = (String) request.getAttribute("refreshOpener");
	if (refresh == null)
	{
		refresh = "N";
	}

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_minmax_restrict = propertiesManager.getProperty("REQ OPTIONS", "MinMaxRestrict", "N");
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_from_page = (String) request.getAttribute("frompage");
	String	s_return_page = "";
	String	s_return_method = "";

	List	reqLineList = (List) request.getAttribute("requisitionLineList");
	RequisitionLine reqLine;
	if (reqLineList != null && reqLineList.size() > 0)
	{
		//kat - this may need to change if list not sorted!
		reqLine = (RequisitionLine) reqLineList.get(0);
	}
	else
	{
		reqLine = new RequisitionLine();
		if (s_req_type.equals("O"))
		{
			reqLine.setCommodityCode("CONTRACTS");
		}
	}
	BigDecimal bd_item_unitprice = HiltonUtility.getFormattedDollar(reqLine.getUnitPrice(), oid);
	String	lookupStatus = (String) request.getAttribute("lookupStatus");

	if (lookupStatus == null) {
		lookupStatus = "";
	}
/*
	if (bd_um_factor.compareTo(new BigDecimal(0)) == 0) {
		bd_um_factor = new BigDecimal(1);
	}

	BigDecimal b_subtotal = HiltonUtility.getFormattedDollar(bd_item_qty.multiply(bd_item_unitprice).multiply(bd_um_factor), oid);
*/

	if (HiltonUtility.isEmpty(s_req_number))	{	s_req_number = "N/A";			}
	if (HiltonUtility.isEmpty(s_req_status))		{	s_req_status = "1000";			}
	if (HiltonUtility.isEmpty(s_line_count))		{	s_line_count = "1";				}
	if (HiltonUtility.isEmpty(s_from_page))		{	s_from_page = "shopcart";		}

	if (s_from_page.equalsIgnoreCase("shopcart"))
	{
		s_return_page = "/requests/req_items.jsp";
		s_return_method = "RequisitionLineRetrieveByHeader";
	}
	else
	{
		s_return_page = "/requests/req_review.jsp";
		s_return_method = "RequisitionRetrieve";
	}

	String	s_current_process = "CHECK_ITEM";
	String	s_current_page = "/requests/req_check_item.jsp";
	String	s_current_method = "RequisitionLineUpdateByLine";
	String	s_current_process_method = "";

	String	s_title = "Check Detail";
	if (s_req_type.equals("O"))
	{
		s_title = "Description";
	}

	String	s_use_subcommodity = propertiesManager.getProperty("MISC", "USE SUBCOMMODITY", "N");
	
	Encoder encoder = DefaultEncoder.getInstance();
	boolean bHasBreakdown = false;
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value="<%=reqLine.getIcReqLine()%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionLine_lineNumber" value="<%//=bd_line_number%>"/>
<tsa:hidden name="RequisitionLine_reqType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_requiredBy" value="<%=request.getAttribute(\"RequisitionHeader_requiredBy\")%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RQH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="stdTable_tableType" value=""/>
<tsa:hidden name="formtype" value="REQ"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>
<tsa:hidden name="lineUpdated" value="false"/>
<tsa:hidden name="lineToRetrieve" value=""/>
<tsa:hidden name="RequisitionHeader_internalComments" value="<%=reqLine.getDescription()%>"/>
<tsa:hidden name="checkItem" value="Y"/>
<tsa:hidden name="refreshOpener" value="<%=refresh%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=s_title%></div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="requisitionNumber" defaultString="Requisition #"/>:</b></tsa:td>
			<tsa:td width="125px"><%=headerEncoder.encodeForHTML(s_req_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="status" defaultString="Status"/>:</b></tsa:td>
			<tsa:td width="125px"><%=DocumentStatus.toString(s_req_status, oid)%></tsa:td>
		</tsa:tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tsa:tr>
	<tsa:td align="center" valign="top">
		<table border=0 cellpadding=0 cellspacing=0 height=100% width=525px>
		<tsa:tr field="req-project-instructions">
			<tsa:td align="center">
				<b><tsa:label labelName="req-project-instructions" defaultString="Please provide a detailed description of this supplier engagement."/></b>
			</tsa:td>
		</tsa:tr>
		</table>

		<br>

		<table border=0 cellpadding=0 cellspacing=0 height=100% width=525px>
		<tsa:tr>
			<tsa:td>
				<table border=0 cellpadding=1 cellspacing=1 width="100%">
<%			if (reqLine.getStatus().compareTo(DocumentStatus.CANCELLED) == 0) { %>
				<tsa:tr>
					<tsa:td></tsa:td>
					<tsa:td cssClass="error">* <tsa:label labelName="cancelledUpper" defaultString="CANCELLED"/> *</tsa:td>
					<tsa:td colspan="2"></tsa:td>
				</tsa:tr>
<%			} %>
				<tsa:tr field="req-description">
					<tsa:td noWrap="nowrap" align="right" valign="top"><tsa:label labelName="req-description" defaultString="Description" checkRequired="true"/>:&nbsp;</tsa:td>
					<tsa:td colspan="4">
						<tsa:input type="textarea" wrap="virtual" name="RequisitionLine_description" tabIndex="4" rows="5" cols="60" onkeydown="textCounter(this, 2000);" onkeyup="textCounter(this,2000);" onchange="textCounter(this,2000); setInternalComments();"><%= encoder.encodeForHTML(reqLine.getDescription())%></tsa:input>
					</tsa:td>
				</tsa:tr>
<%	if (s_req_type.equals("K")) { %>
				<tsa:tr>
					<tsa:td field="req-unitCost" docType="s_req_type" align="right" noWrap="nowrap"><tsa:label labelName="req-unitCost" defaultString="Unit Cost" checkRequired="true" docType="s_req_type"/>:&nbsp;</tsa:td>
							<tsa:td field="req-unitCost" docType="s_req_type">
								<tsa:input type="mintext" name="RequisitionLine_unitPrice" tabIndex="7" maxLength="25" value="<%=bd_item_unitprice%>" style="text-align:right" onchange="formatMe(this); setTotals();"/>&nbsp;
							</tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td field="req-commodity" align="right" noWrap="nowrap"><A HREF="javascript: <% if (s_use_subcommodity.equalsIgnoreCase("Y")) {%>browseCommodity('RequisitionLine_commodityCode','subcommodity','<%=propertiesManager.getProperty("MISC", "SubCommodityType", "")%>'); <% } else {%>browseCommodityByType(); <%}%>void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-commodity", "Commodity")%> for this item or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-commodity", "Commodity")%> in the box on the right."><tsa:label labelName="req-commodity" defaultString="Commodity" checkRequired="true"/></a>:&nbsp;</tsa:td>
					<tsa:td field="req-commodity"><tsa:input type="text" name="RequisitionLine_commodityCode" id="RequisitionLine_commodityCode" tabIndex="9" size="25" maxLength="15" value="<%=reqLine.getCommodityCode()%>" onchange="upperCase(this); updated(); getNewInfo('commodity', this);"/></tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td field="req-commodityName" align="right" noWrap="nowrap"><tsa:label labelName="req-commodityName" defaultString="Commodity Name"/>:</a>&nbsp;</tsa:td>
					<tsa:td field="req-commodityName"><tsa:input type="text" name="as_commodityName" tabIndex="10" size="25" maxLength="15" value="<%=CommodityManager.getInstance().getCommodityDescription(oid, reqLine.getCommodityCode())%>" onfocus="this.blur();" disabled="disabled"/></tsa:td>
				</tsa:tr>
<%	} else if (s_req_type.equals("O")) { %>
				<tsa:tr height="18px">
					<tsa:td field="req-LN01" docType="s_req_type" align="right" noWrap="nowrap"><tsa:label labelName="req-LN01" defaultString="Line UDF1" docType="s_req_type" checkRequired="true"/>:&nbsp;</tsa:td>
					<tsa:td field="req-LN01" docType="s_req_type">
						<tsa:input type="mintext" name="RequisitionLine_udf1Code" tabIndex="5" maxLength="15" value="<%=reqLine.getUdf1Code()%>" onchange="upperCase(this);"/>
						<a href="javascript: show_calendar('RequisitionLine_udf1Code', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</tsa:td>
					<tsa:td>
						<div id="expenseDetails" name="expenseDetails" cellpadding="1" cellspacing="0" border="0">
						<table border=0 cellpadding=0 cellspacing=0>
						<tsa:tr>
							<tsa:td field="req-unitCost" docType="s_req_type" align="right" noWrap="nowrap"><tsa:label labelName="req-unitCost" defaultString="Unit Cost" docType="s_req_type" checkRequired="true"/>:&nbsp;</tsa:td>
							<tsa:td field="req-unitCost" docType="s_req_type">
								<tsa:input type="mintext" name="RequisitionLine_unitPrice" tabIndex="7" maxLength="25" value="<%=bd_item_unitprice%>" style="text-align:right" onchange="formatMe(this); setTotals();"/>&nbsp;
								<tsa:hidden name="RequisitionLine_lineTotal" value="<%=bd_item_unitprice%>"/>
							</tsa:td>
							<tsa:td>
								<a href="javascript: toggleDetailsDisplay('expenseDetails', 'Details'); void(0);"><img id='expenseDetailsImg' valign='baseline' src="<%=contextPath%>/images/down_arrows.gif" class="processOnImg" border="0" alt="View Details"></a>
							</tsa:td>
						</tsa:tr>
						</table>
						</div>
					</tsa:td>
				</tsa:tr>
				<tsa:tr height="18px">
					<tsa:td field="req-LN02" docType="s_req_type" align="right" noWrap="nowrap"><tsa:label labelName="req-LN02" defaultString="Line UDF2" docType="s_req_type" checkRequired="true"/>:&nbsp;</tsa:td>
					<tsa:td field="req-LN02" docType="s_req_type">
						<tsa:input type="mintext" name="RequisitionLine_udf2Code" tabIndex="6" maxLength="15" value="<%=reqLine.getUdf2Code()%>" onchange="upperCase(this);"/>
						<a href="javascript: show_calendar('RequisitionLine_udf2Code', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</tsa:td>
				</tsa:tr>
<%	} %>
				</table>

				<br>

				<div id="expenseDetails" name="expenseDetails" style="display:none;">
				<table border=0 cellpadding=2 cellspacing=2 align="right">
<%	bHasBreakdown = false;
		for (int i = 1; i < 6; i++)
		{
			RequisitionLine breakdownLine;
			if (reqLineList.size() > i)
			{
				breakdownLine = (RequisitionLine) reqLineList.get(i);
				bHasBreakdown = true;
			}
			else
			{
				breakdownLine = new RequisitionLine();
			}
%>
				<tsa:tr>
					<tsa:td>
						<%=i%>.&nbsp;
						<tsa:input type="text" name="RequisitionLine_description" value="<%=breakdownLine.getDescription()%>" size="56" maxLength="65" tabIndex="25" />
						<tsa:hidden name="RequisitionLine_icReqLine" value="<%=breakdownLine.getIcReqLine()%>"/>
					</tsa:td>
					<tsa:td>
						&nbsp;$<tsa:input type="mintext" name="RequisitionLine_unitPrice" value="<%=HiltonUtility.getFormattedDollar(breakdownLine.getUnitPrice(), oid)%>" maxLength="25" tabIndex="25" style="text-align:right" onchange="formatMe(this); addUp();"/>
						<tsa:hidden name="RequisitionLine_lineTotal" value="<%=breakdownLine.getUnitPrice()%>"/>
						&nbsp;<a href="javascript: deleteMe(<%=i%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0"></a>
					</tsa:td>
				</tsa:tr>
<%	}	%>
				<tsa:tr>
					<tsa:td field="req-unitCost" align="right" noWrap="nowrap">
						<a href="javascript: toggleDetailsDisplay('expenseDetails', 'Details'); void(0);"><img id="expenseDetailsImg" valign="baseline" src="<%=contextPath%>/images/up_arrows.gif" class="processOnImg" border="0" alt="Hide Details"></a>
						<tsa:label labelName="req-total-dollar-value" defaultString="Total Dollar Value"/>:
					</tsa:td>
					<tsa:td field="req-unitCost">
						&nbsp;$<tsa:input type="mintext" name="computed_subtotal" tabIndex="27" maxLength="25" value="<%=bd_item_unitprice%>" style="text-align:right" onchange="formatMe(this); addUp();"/>
						<tsa:hidden name="RequisitionHeader_total" value="<%=bd_item_unitprice%>"/>
						<tsa:hidden name="allocateTotal" value="<%=bd_item_unitprice%>"/>
					</tsa:td>
				</tsa:tr>
				</table>
				</div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>

	<%	if (s_view.equals("WIZARD")) { %>
			<tsa:td rowspan="2" valign="top"><%@ include file="/requests/req_sidebar.jsp" %></tsa:td>
<%	} %>
</tsa:tr>
</table>

<br>
<br>

<div style="visibility: hidden; display: none;">
<table>
<tsa:tr id="deleteitems"></tsa:tr>
</table>
</div>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tsa:tr>
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
	<tsa:td width="50%" align="center"><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionLineUpdateByLine;RequisitionRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></tsa:td>
	<tsa:td width="50%" align="center"><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></tsa:td>
<%	} %>
</tsa:tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var info_window = null;
	var populated = false;
	var uomArray = new Array();
	var itemLocation = "<%=s_item_location%>";
	var lookupStatus = "<%=lookupStatus%>";
	var reqnumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
	var reqtype = "<%=s_req_type%>";
	var fiscalyear = "<%=s_fiscal_year%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var allowEdit;
	var dollar_dec = <%=Integer.valueOf(s_dollar_decimals).intValue()%>;

	if (frm.refreshOpener.value == "Y")
	{
		//set cursor to hourglass while the system is processing
		//frm.body.style.cursor = "wait";
		popupParameters = "formtype=REQ;formnumber=" + reqnumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentprocessmethod;
		doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
		frm.refreshOpener.value = "N";
	}

	function setFieldFocus() {
		if (lookupStatus == "NOTFOUND" && frm.RequisitionLine_description) {
			frm.RequisitionLine_description.focus();
		} else if (lookupStatus == "FOUND" && frm.RequisitionLine_quantity) {
			frm.RequisitionLine_quantity.focus();
		} else {
			setFirstFocus();
		}
	}

	function toggleDetailsDisplay(areaName, type) {
		var myAreas = document.getElementsByName(areaName);
		var myArea;
		var myImg = document.getElementById(areaName + "Img");
		var hideArea = false;

		for (var i = 0; i < myAreas.length; i++) {
			myArea = myAreas[i];

			if (myArea.style.display == "none") {
				myArea.style.display = "block";
			}
			else {
				hideArea = true;
				myArea.style.display = "none";
			}
		}

		if (type == undefined) {
			type = "Details";
		}
	}

	function setTotals()
	{
		var dollarValue = frm.RequisitionLine_unitPrice[0].value;
		frm.RequisitionLine_lineTotal[0].value = dollarValue;
		frm.computed_subtotal.value = nformat(dollarValue, dollar_dec);
		frm.allocateTotal.value = dollarValue;
		frm.RequisitionHeader_total.value = dollarValue;
	}

	function addUp()
	{
		var subtotal = 0;
		for (var i = 1; i < 6; i++)
		{
			var price = frm.RequisitionLine_unitPrice[i].value;
			frm.RequisitionLine_lineTotal[i].value = price;
			subtotal = eval(subtotal) + eval(price);
		}
		frm.computed_subtotal.value = nformat(subtotal, dollar_dec);
		frm.allocateTotal.value = subtotal;
		frm.RequisitionHeader_total.value = subtotal;
		frm.RequisitionLine_unitPrice[0].value = nformat(subtotal, dollar_dec);
	}

	function thisLoad()
	{
		f_StartIt();
<%	if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0) {	%>
			checkInputSettings();
<%	}
		if (bHasBreakdown) {	%>
			toggleDetailsDisplay('expenseDetails', 'Details'); void(0);
<%	}	%>
	}

	function setInternalComments() {
		frm.RequisitionHeader_internalComments.value = frm.RequisitionLine_description.value;
	}

	function formatMe(x)
	{
		var dollar_dec = <%=Integer.valueOf(s_dollar_decimals).intValue()%>;
		x.value = nformat(eval(nfilter(x)),dollar_dec);
	}

	function deleteMe(row)
	{
		var myRow = document.getElementById("deleteitems");
		myCell = createCell(myRow);

		var newInputField = "<input type='hidden' name='deleteLine_lineIc' value='" + frm.RequisitionLine_icReqLine[row].value + "'>";
		myCell.innerHTML = newInputField;

		for ( var i = row; i < 5; i++)
		{
			frm.RequisitionLine_icReqLine[i].value = frm.RequisitionLine_icReqLine[i + 1].value;
			frm.RequisitionLine_description[i].value = frm.RequisitionLine_description[i + 1].value;
			frm.RequisitionLine_unitPrice[i].value = frm.RequisitionLine_unitPrice[i + 1].value;
		}

		frm.RequisitionLine_description[5].value = "";
		frm.RequisitionLine_unitPrice[5].value = "0.00";

		addUp();
	}

	function validateForm()
	{
		if (frm.handler.value.indexOf("RequisitionLineUpdate") >= 0 && reqtype == "O")
		{
			var subtotal = 0;
			for (var i = 1; i < 6; i++)
			{
				var price = frm.RequisitionLine_unitPrice[i].value;
				subtotal = eval(subtotal) + eval(price);
			}

			if (subtotal > 0 && frm.RequisitionLine_unitPrice[0].value  != subtotal)
			{
				alert("Totals do not match!");
				return false;
			}
		}

		var newInputField = "<input type='hidden' name='RequisitionLine_commodityCode' value='" + frm.RequisitionLine_commodityCode.value + "'>";
		setHiddenFields(newInputField);

		return true;
	}

	/*function reqSave()
	{
		if (reqnumber == "N/A")
		{
			frm.refreshOpener.value = "Y";
			doSubmit("/requests/req_check_item.jsp", "RequisitionLineUpdateByLine;RequisitionLineRetrieveByHeader");
		}
		else
		{
			frm.refreshOpener.value = "N";
			doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
		}
	}*/

	function browseCommodityByType()
	{
		var currentAllowBrowse = frm.allowBrowse.value;
		var commodityBrowse = 'commodity';

		<%	if (s_req_type.equalsIgnoreCase("H")) { %>
			commodityBrowse = 'commodity-it';
		<% } %>

		<% if (reqLine.getItemSource().equals("CAT") && !reqLine.getItemNumber().startsWith("*") && oid.equalsIgnoreCase("dtn07p")) { %>
			<% if( !reqLine.getCatalogId().equalsIgnoreCase("") ){%>
				frm.allowBrowse.value="true";
			<%}else{%>
				frm.allowBrowse.value="false";
			<%}%>
		<%	} %>

		<%	if (reqLine.getItemSource().equals("XML") && !(s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0)) { %>
			frm.allowBrowse.value = "true" ;
		<% } %>

		<% if (oid.equalsIgnoreCase("msg07p")) { %>
		popupParameters = "browseName=commodity";
		popupParameters = popupParameters + ";formField=RequisitionLine_commodityCode;allowBrowse=" + frm.allowBrowse.value;
		popupParameters = popupParameters + ";colname=Commodity_commodityType;operator==;filter_txt=BO;logicalOperator=OR;originalFilter=Y;sort=N";
		popupParameters = popupParameters + ";colname=Commodity_commodityType;operator==;filter_txt=SP;logicalOperator=AND;originalFilter=Y;sort=N";
		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
		<% } else { %>
		browseCommodity('RequisitionLine_commodityCode', commodityBrowse, '<%=propertiesManager.getProperty("MISC", "COMMODITYTYPE", "")%>');
		<% } %>

		frm.allowBrowse.value = currentAllowBrowse;
	}

// End Hide script -->
</SCRIPT>