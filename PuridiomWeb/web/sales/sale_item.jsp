<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%
	SaleLine saleLine = (SaleLine) request.getAttribute("saleLine");
	String	s_quantity_decimals = PropertiesManager.getInstance(oid).getProperty("MISC", "QtyDecimals", "2");
	int i;
	String s_icSaleHeader = saleLine.getIcSaleHeader().toString();
	BigDecimal bd_icSaleLine = saleLine.getIcSaleLine();
	BigDecimal bd_umFactor = saleLine.getUmFactor();
	String s_saleNumber = saleLine.getSaleNumber();
	String s_amendment = HiltonUtility.ckNull((String) request.getAttribute("SaleHeader_amendment"));
	String s_status = (String) request.getAttribute("SaleHeader_status");
	String s_fiscalYear = (String) request.getAttribute("SaleHeader_fiscalYear");
	String s_line_count = (String) request.getAttribute("lineCount");
	String s_from_page = (String) request.getAttribute("frompage");
	String s_return_page = "";
	String s_return_method = "SaleRetrieve";
	String	lookupStatus = (String) request.getAttribute("lookupStatus");

	if (lookupStatus == null) {
		lookupStatus = "";
	}

	if (HiltonUtility.isEmpty(s_status))	{	s_status = DocumentStatus.SALE_INPROGRESS;		}
	if (HiltonUtility.isEmpty(s_line_count))	{	s_line_count = "1";			}
	if (HiltonUtility.isEmpty(s_from_page))	{	s_from_page = "shopcart";	}

	if (bd_umFactor == null || bd_umFactor.compareTo(new BigDecimal(0)) <= 0) {
		bd_umFactor = new BigDecimal(1);
	}

	if (s_view.equalsIgnoreCase("CLASSIC")) {
		s_return_page = "/sales/sale_summary.jsp";
	} else {
		s_return_page = "/sales/sale_review.jsp";
	}
	String	s_current_process = "SHOPCART";
	String	s_current_page = "/sales/sale_item.jsp";
	String	s_current_method = "SaleLineUpdateById";
	String	s_current_process_method = "";
%>
<%@ include file="/sales/sale_hidden_fields.jsp" %>

<tsa:hidden name="SaleBid_icSaleHeader" value="<%=s_icSaleHeader%>"/>
<tsa:hidden name="SaleBid_icSaleLine" value="<%=bd_icSaleLine%>"/>
<tsa:hidden name="SaleLine_icSaleLine" value="<%=bd_icSaleLine%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="as_action" value="true"/>
<tsa:hidden name="as_redirect" value="true"/>
<tsa:hidden name="lineUpdated" value="false"/>
<tsa:hidden name="lineToRetrieve" value=""/>

<br>
<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Item to Sell</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/sales/sale_status_title.jsp" %>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center valign=top width=520px>
		<br>
		<table border=0 cellpadding=0 cellspacing=0 height=100%>
		<tr>
			<td valign=top>
<%
	String s_asset = saleLine.getAsset();
	String s_taxable = saleLine.getTaxable();
	if (HiltonUtility.isEmpty(s_asset)) { s_asset="N"; }
	if (HiltonUtility.isEmpty(s_taxable)) { s_taxable="N"; }
	BigDecimal b_quantity = saleLine.getQuantity().setScale(Integer.valueOf(s_quantity_decimals).intValue(), BigDecimal.ROUND_HALF_UP);;
%>
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td nowrap align=right>Item/Part No.:&nbsp;</td>
					<td><input type=text name="SaleLine_itemNumber" tabindex=1 size=20 maxlength=30 value="<%=saleLine.getItemNumber()%>" onchange="upperCase(this); getItemInfo(); updated(); void(0);"></td>
					<td align=center>
						Taxable:<input type=checkbox name="c_checkbox" tabindex=3 <% if (s_taxable.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(frm.SaleLine_taxable, 0); updated();">
					</td>
					<tsa:hidden name="SaleLine_taxable" value="<%=s_taxable%>"/>
				</tr>
				<tr>
					<td nowrap align=right valign=top>Description:&nbsp;</td>
					<td colspan=4><textarea wrap="virtual" name="SaleLine_description" tabindex=4 rows=5 cols=60>${esapi:encodeForHTML(saleLine.description)}</textarea></td>
				</tr>
				<tr>
					<td></td>
					<td colspan=3>
						<table border=0 cellpadding=1 cellspacing=0 width=100%>
						<tr>
							<td><input type=radio name="SaleLine_source" tabindex=5  value="S" <% if (HiltonUtility.isEmpty(saleLine.getSource()) || saleLine.getSource().equals("S")) {%>checked<%}%>>&nbsp;Surplus Item</td>
							<td><input type=radio name="SaleLine_source" tabindex=5  value="I" <% if (saleLine.getSource().equals("I")) {%>checked<%}%>>&nbsp;Impounded Item</td>
							<td><input type=radio name="SaleLine_source" tabindex=5  value="N" <% if (saleLine.getSource().equals("N")) {%>checked<%}%>>&nbsp;New Item</td>
						</tr>
						</table>
				</tr>
				<tr>
					<td align=right nowrap>Quantity:&nbsp;</td>
					<td><input type=text name="SaleLine_quantity" tabindex=6 size=15 maxlength=15 value="<%=b_quantity%>" style="text-align:right" onchange="setQty(); updated();"></td>

					<td align=right nowrap><a href="javascript: browseStd('SaleLine_udf1Code','LN01');"  title="Click here to choose the value for Line UDF1 for this item or Enter the value in box on the right.">Condition</a>:&nbsp;</td>
					<td><input type=text name="SaleLine_udf1Code" tabindex=20 size=15 maxlength=15 value="<%=saleLine.getUdf1Code()%>" onchange="upperCase(this); updated();"   ></td>
				</tr>
				<tr>
					<td align=right nowrap><a href="javascript: browseLookup('SaleLine_umCode','unitofmeasure'); void(0);" title="Click here to choose the UOM for this item or Enter the UOM in the box on the left.">UOM</a>:&nbsp;</td>
					<td>
						<input type=text name="SaleLine_umCode" tabindex=7 size=15 maxlength=15 value="<%=saleLine.getUmCode()%>" onchange="upperCase(this); updateUMFactor(); updated();">
						<tsa:hidden name="SaleLine_umFactor" value="<%=bd_umFactor%>"/>
					</td>
					<td align=right nowrap><a href="javascript: browseStd('SaleLine_udf2Code','LN02');" title="Click here to choose the value for Line UDF2 for this item or Enter the value in box on the right." >Line UDF2</a>:&nbsp;</td>
					<td><input type=text name="SaleLine_udf2Code" tabindex=21 size=15 maxlength=15 value="<%=saleLine.getUdf2Code()%>" onchange="upperCase(this); updated();"></td>
				</tr>
				<tr>
					<td align=right nowrap><a href="javascript: browseCommodity('SaleLine_commodity', 'commodity', '<%=PropertiesManager.getInstance(oid).getProperty("MISC", "COMMODITYTYPE", "")%>'); void(0);" title="Click here to choose the Commodity Code for this item or Enter the Commodity Code in box on the right.">Commodity</a>:&nbsp;</td>
					<td><input type=text name="SaleLine_commodity" tabindex=8 size=15 maxlength=15 value="<%=saleLine.getCommodity()%>" onchange="upperCase(this); updated(); getNewInfo('commodity', this);"></td>
					<td align=right nowrap><a href="javascript: browseStd('SaleLine_udf3Code','LN03');" title="Click here to choose the value for Line UDF3 for this item or Enter the value in box on the right.">Line UDF3</a>:&nbsp;</td>
					<td><input type=text name="SaleLine_udf3Code" tabindex=22 size=15 maxlength=15 value="<%=saleLine.getUdf3Code()%>" onchange=" upperCase(this); updated();"></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rfq-commodityName")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-commodityName", "Commodity Name")%>:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-commodityName")%> colspan="3">
					<%if (oid.equalsIgnoreCase("dtn07p")){%>
					<input type=text name="as_commodityName" size=70 value="<%=CommodityManager.getInstance().getCommodityDescription(oid, saleLine.getCommodity())%>" onfocus="this.blur();" disabled></td>
					<% }else{%>
					<input type=text name="as_commodityName" size=25 value="<%=CommodityManager.getInstance().getCommodityDescription(oid, saleLine.getCommodity())%>" onfocus="this.blur();" disabled></td>
					<%}%>
				</tr>
				<tr>
					<td align=right nowrap>Model Number:&nbsp;</td>
					<td><input type=text name="SaleLine_modelNumber" tabindex=9 size=15 maxlength=20 value="<%=saleLine.getModelNumber()%>" onchange="upperCase(this); updated();"></td>
					<td align=right nowrap><a href="javascript: browseStd('SaleLine_udf4Code','LN04');" title="Click here to choose the value for Line UDF4 for this item or Enter the value in box on the right.">Line UDF4</a>:&nbsp;</td>
					<td><input type=text name="SaleLine_udf4Code" tabindex=23 size=15 maxlength=15 value="<%=saleLine.getUdf4Code()%>" onchange="upperCase(this); updated();"></td>
				</tr>
				<tr>
					<td align=right nowrap>Manufacturer:&nbsp;</td>
					<td><input type=text name="SaleLine_mfgName" tabindex=10 size=15 maxlength=25 value="<%=saleLine.getMfgName()%>" onchange="upperCase(this); updated();"></td>
					<td align=right nowrap><a href="javascript: browseStd('SaleLine_udf5Code','LN05');" title="Click here to choose the value for Line UDF5 for this item or Enter the value in box on the right.">Line UDF5</a>:&nbsp;</td>
					<td><input type=text name="SaleLine_udf5Code" tabindex=24 size=15 maxlength=15 value="<%=saleLine.getUdf5Code()%>" onchange="upperCase(this); updated();"></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
		<br>
		<table border=1 cellpadding=1 cellspacing=0>
		<tr><td class=browseHdr>Item Search Options</td></tr>
		<tr>
			<td class=browseRow>
<%	if (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) < 0 || s_status.compareTo(DocumentStatus.SALE_OPENAMENDMENT) == 0) { %>
				<div id="addItem" style="display:visible;">
				<table border=0 width=225px cellpadding=0>
				<tr>
					<td align=right>
						<table border=0 cellspacing=0 cellpadding=1>
						<tr>
							<td valign=middle align=right nowrap><b>Item/Part #:</b></td>
							<td valign=middle><input type=text name=as_itemNumber value="" size=15 tabindex=30></td>
						<!--/tr>
						<tr-->
							<td valign=middle align=right nowrap><b>Keyword(s):</b></td>
							<td valign=middle><input type=text name="as_keywords" value="" size=23 tabindex=31></td>
							<td valign=middle><a href="javascript: itemSearch(); void(0);"><img src="<%=contextPath%>/images/button_go_sm.gif" border=0 alt="Item Search" tabindex=32 class=button></a></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align=center>
						<table border=0 cellpadding=0 cellspacing=0>
						<tr>
						<td valign="middle"><a href="javascript: doSubmit('/browse/item_filter_options.jsp', 'CatalogRetrieveActive'); void(0);"><img src="<%=contextPath%>/images/cmd_search_item.gif" alt="Advanced Item Search" border=0><font style="text-decoration:none;">&nbsp;</font></a></td>
							<td valign=middle><a href="javascript: doSubmit('/browse/item_filter_options.jsp', 'CatalogRetrieveActive'); void(0);">Advanced Item Search</a></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
				</div>

<%	} %>

			</td>
		</tr>
		</table>

	</td>
	<td rowspan=2 align=right valign=top><%@ include file="/sales/sale_sidebar.jsp" %></td>
	<!--td align=right width=225px valign=top>
 Item search options
	</td-->
</tr>
</table>

<br>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var info_window = null;
	var populated = false;
	var uomArray = new Array();
	var itemLocation = "<%=saleLine.getItemLocation()%>";
	var lookupStatus = "<%=lookupStatus%>";
	var saleNumber = "<%=s_saleNumber%>";
	var fiscalyear = "<%=s_fiscalYear%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var browser = browserCheck();

	hideArea("navTable");
	displayArea("menuBarSpacer");

	function thisLoad()
	{
		f_StartIt();
<%	if (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) >= 0 && !s_status.equals(DocumentStatus.SALE_OPENAMENDMENT)) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function setFieldFocus() {
		if (lookupStatus == "NOTFOUND" && frm.SaleLine_description) {
			frm.SaleLine_description.focus();
		} else if (lookupStatus == "FOUND" && frm.SaleLine_quantity) {
			frm.SaleLine_quantity.focus();
		} else {
			setFirstFocus();
		}
	}

	function toggleDetailsDisplay(areaName, type) {
		var myAreas = document.getElementsByName(areaName);
		var myArea;
		var myImg = document.getElementById(areaName + "Img");
		var hideArea = false;

		for (i = 0; i < myAreas.length; i++) {
			myArea = myAreas(i);


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

		if (type != "addItemOptions")
		{
			/*	don't want to do this for the add item options	*/
			if (hideArea) {
				myImg.src = "<%=contextPath%>/images/arrows_down.gif";
				myImg.alt = "View" + type;

			}
			else {
				myImg.src = "<%=contextPath%>/images/arrows_up.gif";
				myImg.alt = "Hide " + type;

			}
		}
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

	function updateUMFactor()
	{
		var open = true;
		var browser = browserTest();
		var factor = "";
		var code = "";

		frm.SaleLine_umCode.value = trim(frm.SaleLine_umCode);

		if (info_window != null) {
			if (browser == "Netscape") {
				if (info_window.closed == false) {
					info_window.setUomCode("SaleLine_");
					open = false;
				}
			}
			else {
				info_window.setUomCode("SaleLine_");
				open = false;
			}
		}

		if (open == true)
		{
			if (uomArray.length > 0 || populated)
			{
				code = frm.SaleLine_umCode.value;
				for (var i = 0; i < uomArray.length; i++)
				{
					if (code == (uomArray[i][0]).toString())
					{
						factor = uomArray[i][1];
						break;
					}
				}

				setUmFactor(factor)
			}
			else
			{
				popupParameters = "as_prefix=SaleLine_";

				setLookupParameters('/base/get_uom_info.jsp', 'UnitOfMeasureRetrieveAll');
				document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
			}
		}
	}

	function setUmFactor(factor) {
		frm.SaleLine_umFactor.value = factor;
//		addUp();
	}


	function setQty()
	{
		var qty_dec = <%=s_quantity_decimals%>;
		var qty = nformat(eval(nfilter(frm.SaleLine_quantity)),qty_dec);
		frm.SaleLine_quantity.value = qty;
	}

	function viewItemHistory()
	{
		popupParameters = "HistoryLog_icLineHistory=<%=saleLine.getIcLineHistory()%>;formtype=SALE;SaleLine_icSaleLine=<%=saleLine.getIcSaleLine()%>;saleNumber=<%=saleLine.getSaleNumber()%>";
		doSubmitToPopup('/sales/sale_history.jsp', 'HistoryLogRetrieveByHistoryLine', 'width=675px', 'height=350px');
	}

	function itemSearch() {
		itemSearchWithUpdate('SaleLineUpdateById');
	}

	function getItemInfo() {
		// No need to call the server if the item number is empty
		if (!isEmpty(frm.SaleLine_itemNumber.value)) {
			doSubmit('/sales/sale_item.jsp', 'SaleLineItemLookup');
		}
	}

	function validateItem() {
		if (isEmpty(frm.SaleLine_itemNumber.value) && isEmpty(frm.SaleLine_description.value)) {
			alert("You must enter either an item number or description for this item.");
			return false;
		}
		return true;
	}

	function validateForm() {
		var handlers = frm.handler.value;
		var originalItemNumber = "<%=saleLine.getItemNumber()%>";
		var originalDescription = "<%=saleLine.getDescription().replace('"', '~').replaceAll("\r\n", "~~")%>";

		if (isEmpty(frm.SaleLine_itemNumber.value) && isEmpty(frm.SaleLine_description.value) && handlers.indexOf("<%=s_current_method%>") >= 0) {
			alert("You must enter an item number and/or item description.");
			return false;
		} else {
			return true;
		}
	}

	function saleSave() {
		if (saleNumber == "N/A") {
			popupParameters = "formtype=SALE;formnumber=" + saleNumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentmethod + "/" + currentprocessmethod;
			doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
		} else {
			doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
		}
	}

// End Hide script -->
</SCRIPT>