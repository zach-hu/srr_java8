<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%
	int i;
	RequisitionLine reqLine = (RequisitionLine) request.getAttribute("requisitionLine");
	String	s_req_status = (String) request.getAttribute("RequisitionHeader_status");
	String	s_fiscal_year = (String) request.getAttribute("RequisitionHeader_fiscalYear");
	String	s_req_subtype = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_rqSubType"));
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_itemLocation"));
	String	s_req_type = reqLine.getReqType();
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	String	s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_minmax_restrict = propertiesManager.getProperty("REQ OPTIONS", "MinMaxRestrict", "N");
	BigDecimal b_req_ic_header = reqLine.getIcReqHeader();
	BigDecimal b_req_ic_line = reqLine.getIcReqLine();
	BigDecimal bd_line_number = HiltonUtility.getBigDecimalFormatted(reqLine.getLineNumber(), 0);
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_from_page = (String) request.getAttribute("frompage");
	String	s_return_page = "";
	String	s_return_method = "";
	BigDecimal bd_item_qty = HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid);
	String s_item_asset = reqLine.getAsset();
	String s_item_taxable = reqLine.getTaxable();
	String s_udf1 = reqLine.getUdf1Code();
	BigDecimal b_item_unitprice = HiltonUtility.getFormattedPrice(reqLine.getUnitPrice(), oid);
	BigDecimal	bd_um_factor = reqLine.getUmFactor();
	String s_item_receipt = reqLine.getReceiptRequired();
	String s_req_number = reqLine.getRequisitionNumber();
	String	lookupStatus = (String) request.getAttribute("lookupStatus");
	List catalogPriceBrkList = (List) request.getAttribute("catalogPriceBrkList");
	String catalogItemImageFile = HiltonUtility.ckNull((String) request.getAttribute("catalogItemImageFile"));
	String s_reqaction = (String) request.getAttribute("reqaction");
	int breakCount = 0;

	if (catalogPriceBrkList == null) {
		catalogPriceBrkList = new ArrayList();
	} else {
		breakCount = catalogPriceBrkList.size();
	}

	if (lookupStatus == null) {
		lookupStatus = "";
	}

	BigDecimal b_subtotal = HiltonUtility.getFormattedDollar(reqLine.getQuantity().multiply(reqLine.getUnitPrice()).multiply(bd_um_factor), oid);


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
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=reqLine.getRequisitionNumber()%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value="<%=b_req_ic_line%>"/>
<tsa:hidden name="RequisitionLine_lineNumber" value="<%=bd_line_number%>"/>
<tsa:hidden name="RequisitionHeader_requiredBy" value="<%=request.getAttribute(\"RequisitionHeader_requiredBy\")%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="Account_icHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="<%=b_req_ic_line%>"/>
<tsa:hidden name="Account_accountType" value="RQL"/>
<tsa:hidden name="DocComment_icHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="DocComment_icLine" value="<%=b_req_ic_line%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="stdTable_tableType" value=""/>
<tsa:hidden name="formtype" value="REQ"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>
<tsa:hidden name="lineUpdated" value="false"/>
<tsa:hidden name="lineToRetrieve" value=""/>
<tsa:hidden name="catalogId" value="<%=reqLine.getCatalogId()%>"/>
<tsa:hidden name="writehistory" value="N"/>
<tsa:hidden name="originalQuantity" value=""/>
<tsa:hidden name="originalPrice" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="lineItem" defaultString="Line Item" /> <%=bd_line_number%> <tsa:label labelName="of" defaultString="of " />&nbsp;<%=s_line_count%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b><tsa:label labelName="requisitionNumber" defaultString="Requisition #" />:</b></td>
			<td width=125px><%=headerEncoder.encodeForHTML(s_req_number)%></td>
		</tr>
		<tr>
			<td align=right><b><tsa:label labelName="status" defaultString="Status" />:</b></td>
			<td width=125px><%=DocumentStatus.toString(s_req_status)%></td>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center valign=top width=460px>
		<br>
		<table border=0 cellpadding=0 cellspacing=0 height=100%>
		<tr>
			<td>
				<table border=0 cellpadding=1 cellspacing=0>
<%			if (reqLine.getStatus().compareTo(DocumentStatus.CANCELLED) == 0) { %>
				<tr>
					<td></td>
					<td class=error>* <tsa:label labelName="cancelledUpper" defaultString="CANCELLED" /> *</td>
					<td colspan=2></td>
				</tr>
<%			} else if (oid.equals("BSC04P") && (reqLine.getUmCode()).equals("RM") && (reqLine.getCatalogId()).equals("LETTERHEAD")) { %>
				<tr>
					<td></td>
					<td class=error colspan=3>*<tsa:label labelName="note" defaultString="Note" />: <tsa:label labelName="reamContainsSheetsPaper" defaultString="1 Ream (RM) contains 500 sheets of paper" />.</td>
				</tr>
<%			} else if (oid.equals("BSC04P") && (reqLine.getUmCode()).equals("BX") && (reqLine.getCatalogId()).equals("ENVELOPE")) { %>
				<tr>
					<td></td>
					<td class=error colspan=3>*<tsa:label labelName="note" defaultString="Note" />: <tsa:label labelName="minimunOrderBoxes" defaultString="Minimum Order 2 boxes. Each Box (BX) contains 500 envelopes" />.</td>
				</tr>
<%			} else {	%>
				<tr>
					<td colspan="4"><br></td>
				</tr>
<%			} %>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "req-itemNumber")%> nowrap align=right><tsa:label labelName="req-itemNumber" defaultString="Item/Part #" checkRequired="true" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-itemNumber")%>><input type=text name="RequisitionLine_itemNumber" tabindex=1 size=25 maxlength=30 value="<%=reqLine.getItemNumber()%>" onchange="upperCase(this); getItemInfo(); updated(); void(0);"></td>

				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "req-description")%>>
					<td nowrap align=right valign=top><tsa:label labelName="req-description" defaultString="Description" checkRequired="true" />:&nbsp;</td>
					<td colspan=4><textarea wrap="virtual" name="RequisitionLine_description" tabindex=2 rows=5 cols=60 onKeyDown="textCounter(this, 2000);" onKeyUp="textCounter(this,2000);" onchange="textCounter(this,2000);">
					${esapi:encodeForHTML(reqLine.description)}
					</textarea></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3">
<%
		String s_align="center";
		if (oid.equalsIgnoreCase("vse06p"))
		{
			s_align = "left";
		}
%>
						<table border="0" cellpadding="0" cellspacing="2" width="100%">
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-asset")%> align="<%=s_align%>">
								<tsa:label labelName="req-asset" defaultString="Asset" />:
								<input type=checkbox name="c_checkbox" tabindex=3 <% if (s_item_asset.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(frm.RequisitionLine_asset,0); updated();">
								<tsa:hidden name="RequisitionLine_asset" value="<%=s_item_asset%>"/>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "req-taxable")%> align="<%=s_align%>">
								<tsa:label labelName="req-taxable" defaultString="Taxable" />:
								<input type=checkbox name="c_checkbox" tabindex=4 <% if (s_item_taxable.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(RequisitionLine_taxable, 1); updated();">
								<tsa:hidden name="RequisitionLine_taxable" value="<%=s_item_taxable%>"/>
							</td>
<%	if (oid.equalsIgnoreCase("vse06p")) {	%>
							<td <%=HtmlWriter.isVisible(oid, "req-LN01")%> align="<%=s_align%>">
								<tsa:label labelName="req-LN01" defaultString="Line UDF1" />:
								<input type=checkbox name="c_checkbox" tabindex=4 <% if (s_udf1.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(RequisitionLine_udf1Code, 2); updated();">
								<tsa:hidden name="RequisitionLine_udf1Code" value="<%=s_udf1%>"/>
							</td>
<%	}	%>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "req-quantity")%> align=right nowrap><tsa:label labelName="req-quantity" defaultString="Quantity" checkRequired="true" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-quantity")%>><input type=text name="RequisitionLine_quantity" tabindex=5 size="20" maxlength=25 value="<%=bd_item_qty%>" style="text-align:right" onchange="getPriceBreak(); addUp(); checkQty(); updated();"><% if (breakCount > 0) {%>&nbsp;&nbsp;<a href="javascript: viewPriceBreaks(); void(0);" alt="View Price Breaks"><b>$</b></a><%}%></td>

					<td <%=HtmlWriter.isVisible(oid, "req-catalog")%> align=right nowrap><tsa:label labelName="req-catalog" defaultString="Catalog" checkRequired="true" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-catalog")%>><input type=text name="RequisitionLine_catalogId" size=15 value="<%=reqLine.getCatalogId()%>" READONLY DISABLED></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "req-uom")%> align=right nowrap><A HREF="javascript: browseLookup('RequisitionLine_umCode', 'unitofmeasure'); void(0);" title="Click here to choose the UOM for this item or enter the UOM in box on the right." ><tsa:label labelName="req-uom" defaultString="UOM" checkRequired="true" /></a>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-uom")%>>
						<input type=text name="RequisitionLine_umCode" tabindex=6 size="20" maxlength=15 value="<%=reqLine.getUmCode()%>" onchange="upperCase(this); updateUMFactor(); updated();">
						<tsa:hidden name="RequisitionLine_umFactor" value="<%=bd_um_factor%>"/>
					</td>

					<td <%=HtmlWriter.isVisible(oid, "req-modelNumber")%> align=right nowrap>
<%	if (DictionaryManager.isLink(oid, "req-modelNumber")) {	%>
						<a href="javascript: browseLookup('RequisitionLine_modelNumber', 'requisitionline-modelnumber'); void(0);"><tsa:label labelName="req-modelNumber" defaultString="Model Number" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="req-modelNumber" defaultString="Model Number" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "req-modelNumber")%>><input type=text name="RequisitionLine_modelNumber" tabindex=12 size=15 maxlength=20 value="<%=reqLine.getModelNumber()%>" onchange="upperCase(this); updated();"></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "req-unitCost")%> align=right nowrap><tsa:label labelName="req-unitCost" defaultString="Unit Cost" checkRequired="true" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-unitCost")%>><input type=text name="RequisitionLine_unitPrice" tabindex=7 size="20" value="<%=b_item_unitprice%>" style="text-align:right" onchange="addUp(); updated();"></td>

					<td <%=HtmlWriter.isVisible(oid, "req-manufacturer")%> align=right nowrap>
<%	if (DictionaryManager.isLink(oid, "req-manufacturer")) {	%>
						<a href="javascript: browseLookup('RequisitionLine_mfgName', 'requisitionline-manufacturer'); void(0);"><tsa:label labelName="req-manufacturer" defaultString="Manufacturer" checkRequired="true" />:</a>&nbsp;
<%	} else {	%>
						<tsa:label labelName="req-manufacturer" defaultString="Manufacturer" checkRequired="true" />:&nbsp;
<%	}	%>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "req-manufacturer")%>><input type=text name="RequisitionLine_mfgName" tabindex=13 size=15 maxlength=25 value="<%=reqLine.getMfgName()%>" onchange="upperCase(this); updated();"></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "req-extendedCost")%> align=right nowrap><tsa:label labelName="req-extendedCost" defaultString="Extended Cost" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-extendedCost")%>><input type=text name="computed_subtotal" tabindex=8 size="20" value="<%=b_subtotal%>" style="text-align:right" DISABLED></td>
<%	if ( !oid.equalsIgnoreCase("vse06p") ) {	%>
					<td <%=HtmlWriter.isVisible(oid, "req-LN01")%> align=right nowrap><a href="javascript: browseStd('RequisitionLine_udf1Code', 'LN01'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN01" defaultString="Line UDF1" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN01" defaultString="Line UDF1" checkRequired="true" /></a>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-LN01")%>><input type=text name="RequisitionLine_udf1Code" tabindex=14 size=15 maxlength=15 value="<%=s_udf1%>" onchange="upperCase(this); updated();"></td>
<%	}	%>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "req-commodity")%> align=right nowrap><A HREF="javascript: browseCommodity('RequisitionLine_commodityCode', 'commodity', '<%=propertiesManager.getProperty("MISC", "COMMODITYTYPE", "")%>'); void(0);" title="Click here to choose the Commodity Code for this item or enter the Commodity Code in the box on the right."><tsa:label labelName="req-commodity" defaultString="Commodity" checkRequired="true" /></a>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-commodity")%>><input type=text name="RequisitionLine_commodityCode" tabindex=9 size=25 maxlength=15 value="<%=reqLine.getCommodityCode()%>" onchange="upperCase(this); updated(); getNewInfo('commodity', this);"></td>
<%	if (oid.equalsIgnoreCase("vse06p")) { %>
					<td <%=HtmlWriter.isVisible(oid, "req-LN02")%> align=right nowrap><tsa:label labelName="req-LN02" defaultString="Line UDF2" checkRequired="true" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-LN02")%> >
						<select tabindex=23 name="RequisitionLine_udf2Code" size=1>
<%
		if (HiltonUtility.isEmpty(reqLine.getUdf2Code()))
		{
			reqLine.setUdf2Code(DictionaryManager.getInstance("inspection-level", oid).getLabelProperty("DEFAULT", ""));
		}
		Map inspectionLevels = new TreeMap(DictionaryManager.getInstance("inspection-level", oid).getPropertyMap());
		Iterator inspectionIterator = inspectionLevels.keySet().iterator();
		String	inspectionLevelCode = "";
		String inspectionLevelName = "";
		while (inspectionIterator.hasNext())
		{
			inspectionLevelCode = (String) inspectionIterator.next();
			inspectionLevelName = (String) inspectionLevels.get(inspectionLevelCode);

			if (inspectionLevelCode.equals("DEFAULT"))
			{
				continue;
			}
%>
						<option value="<%=inspectionLevelCode%>" <%if (reqLine.getUdf2Code().equalsIgnoreCase(inspectionLevelCode)) {%>SELECTED<%}%>><%=inspectionLevelName%></value>
<%	}	%>
						</select>
					</td>
<%	} else { %>
					<td <%=HtmlWriter.isVisible(oid, "req-LN02")%> align=right nowrap><a href="javascript: browseStd('RequisitionLine_udf2Code', 'LN02'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN02" defaultString="Line UDF2" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN02" defaultString="Line UDF2" checkRequired="true" /></a>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-LN02")%>><input type=text name="RequisitionLine_udf2Code" tabindex=15 size=15 maxlength=15 value="<%=reqLine.getUdf2Code()%>" onchange="upperCase(this); updated();"></td>
<%	} %>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "req-commodityName")%> align=right nowrap><tsa:label labelName="req-commodityName" defaultString="Commodity Name" />:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-commodityName")%>><input type=text name="as_commodityName" tabindex10 size=25 maxlength=15 value="<%=CommodityManager.getInstance().getCommodityDescription(oid, reqLine.getCommodityCode())%>" onfocus="this.blur();" disabled></td>

					<td <%=HtmlWriter.isVisible(oid, "req-LN03")%> align=right nowrap><a href="javascript: browseStd('RequisitionLine_udf3Code', 'LN03'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN03" defaultString="Line UDF3" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN03" defaultString="Line UDF3" checkRequired="true" /></a>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-LN03")%>><input type=text name="RequisitionLine_udf3Code" tabindex=16 size=15 maxlength=15 value="<%=reqLine.getUdf3Code()%>" onchange="upperCase(this); updated();"></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "req-inventoryLocation")%> align=right nowrap><a href="javascript: browseLookup('RequisitionLine_itemLocation', 'item_location'); void(0);" title="Click here to choose the Inventory Location for this item or enter the Inventory Location in the box on the right."><tsa:label labelName="req-inventoryLocation" defaultString="Inventory Location" checkRequired="true" />:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-inventoryLocation")%>><input type=text name="RequisitionLine_itemLocation" tabindex=10 size=25 maxlength=15 value="<%=reqLine.getItemLocation()%>" onchange="upperCase(this); updated();"></td>

					<td <%=HtmlWriter.isVisible(oid, "req-LN04")%>align=right nowrap><a href="javascript: browseStd('RequisitionLine_udf4Code', 'LN04'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN04" defaultString="Line UDF4" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN04" defaultString="Line UDF4" checkRequired="true" /></a>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-LN04")%>><input type=text name="RequisitionLine_udf4Code" tabindex=17 size=15 maxlength=15 value="<%=reqLine.getUdf4Code()%>" onchange="upperCase(this); updated();"></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "req-receiptOptions")%> align=right nowrap><tsa:label labelName="req-receiptOptions" defaultString="Receipt Options" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-receiptOptions")%>>
						<select tabindex=10 name="RequisitionLine_receiptRequired" size=1>
							<option <% if (s_item_receipt.equals("R")) { %> selected <% } %> value="R">Receipt Required</option>
							<option <% if (s_item_receipt.equals("P")) { %> selected <% } %> value="P">Previously Received</option>
		<%	if (!oid.equals("MSG07P")) {%>
							<option <% if (s_item_receipt.equals("N")) { %> selected <% } %> value="N">No Receipt Required</option>
		<%	}%>
							<option <% if (s_item_receipt.equals("E")) { %> selected <% } %> value="E">End User Receipt</option>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "req-LN05")%> align=right nowrap><a href="javascript: browseStd('RequisitionLine_udf5Code', 'LN05'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN05" defaultString="Line UDF5" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN05" defaultString="Line UDF5" checkRequired="true" /></a>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-LN05")%>><input type=text name="RequisitionLine_udf5Code" tabindex=17 size=15 maxlength=15 value="<%=reqLine.getUdf5Code()%>" onchange="upperCase(this); updated();"></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "ln-req-requisitioner")%> align=right nowrap><a href="javascript: browseLookup('RequisitionLine_requisitionerCode', 'requisitioner'); void(0);" title="Click here to choose the <tsa:label labelName="ln-req-requisitioner" defaultString="Requisitioner" /> for this item or enter the <tsa:label labelName="ln-req-requisitioner" defaultString="Requisitioner" /> in the box on the right."><tsa:label labelName="ln-req-requisitioner" defaultString="Requisitioner" checkRequired="true" />:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ln-req-requisitioner")%>><input type=text name="RequisitionLine_requisitionerCode" tabindex=11 size=25 maxlength=15 value="<%=reqLine.getRequisitionerCode()%>" onchange="upperCase(this); updated(); getNewInfo('requisitioner', this);"></td>
					<td <%=HtmlWriter.isVisible(oid, "ln-req-department")%> align=right nowrap><a href="javascript: browseLookup('RequisitionLine_departmentCode', 'department'); void(0);" title="Click here to choose the value for <tsa:label labelName="ln-req-department" defaultString="Department" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="ln-req-department" defaultString="Department" checkRequired="true" /></a>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ln-req-department")%>><input type=text name="RequisitionLine_departmentCode" tabindex=18 size=15 maxlength=15 value="<%=reqLine.getDepartmentCode()%>" onchange="upperCase(this); updated();"></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "ln-req-requisitionerName")%> align=right nowrap><tsa:label labelName="ln-req-requisitionerName" defaultString="Requisitioner Name" />:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ln-req-requisitionerName")%>><input type=text name="as_requisitionerName" tabindex10 size=25 maxlength=15 value="<%=UserManager.getInstance().getUser(oid, reqLine.getRequisitionerCode()).getDisplayName()%>" onfocus="this.blur();" disabled></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
	<td align=right width=220px valign=top>
		<br>
		<br>
<%
	int linecount = Integer.valueOf(s_line_count).intValue();
	if (linecount > 1)
	{ %>
		<tsa:label labelName="viewItem" defaultString="View Item" />:
<%	for (i = 1; i <= linecount; i++)
		{
			if (i == bd_line_number.intValue())
			{ %>
				&nbsp;<%=i%>
<%		}
			else
			{ %>
				&nbsp;<a href="javascript: retrieveLine(<%=i%>); void(0);" ><%=i%></a>
<%		}
		}
	} %>
		<br>
		<table border=1 cellpadding=0 cellspacing=0>
		<tr><td class=browseHdr><tsa:label labelName="cancelNote" defaultString="Cancel Note" /></td></tr>
		<tr><td align="center"><textarea name="RequisitionLine_noteCancel" cols="38" rows="12"></textarea><br><br>
		<% if (s_reqaction.compareTo("cancel") == 0) { %>
		<div id="pxbutton"><a href="javascript: cancelReqLine(); void(0);">Cancel Line</a></div>
		<% } else if (s_reqaction.compareTo("close") == 0) { %>
		<div id="pxbutton"><a href="javascript: closeReqLine(); void(0);">Close</a></div>
		<% } %>
		&nbsp;
		<div id="pxbutton"><a href="javascript: returnReqLine(); void(0);">Return</a><div>
		</td></tr>
		</table>

<%	if (!HiltonUtility.isEmpty(reqLine.getCatalogId()) && !HiltonUtility.isEmpty(catalogItemImageFile)) {	%>
		<br>
		<table border=0 cellspacing=0 cellpadding=1 width=220px>
<%		if (catalogItemImageFile.length() > 5) {
				if (!(catalogItemImageFile.substring(0,5)).equals("http:")) {	%>
		  	<tr>
				<td align="center">
					<br><img src="<%=DictionaryManager.getInstance("host", oid).getProperty("item-image-url", "/")%>/<%=reqLine.getCatalogId()%>/<%=catalogItemImageFile%>" alt="Item Image" border="0" width="95px" height="95px">
				</td>
		  	</tr>
<%			}
			}	%>
		  	<tr>
				<td align="center" valign="top">
			  	<div id="browse_CatalogItem_viewCatalogImage"><a href="javascript: viewImage('CAT'); void(0);"><tsa:label labelName="viewImage" defaultString="View Image" /></a></div>
				</td>
		  	</tr>
		</table>
<%	}	%>
	</td>
</tr>
</table>

<br>
<br>
<%List shipToList = (List) reqLine.getShipToList();%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=50% align=center valign=top>
				<table border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
						<tr>
							<td width=4% class=browseHdr height=18px nowrap><a href ="javascript: if (validateItem()) { doSubmit('/requests/req_shipping_schedule.jsp', 'RequisitionLineUpdate;ShipToRetrieveByLine'); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
							<td width=93% class=browseHdr height=18px nowrap>&nbsp;<tsa:label labelName="itemShippingSchedule" defaultString="Item Shipping Schedule" /></td>
							<%	if (shipToList != null && shipToList.size() > 0){%>
							<td width=3% class=browseHdr height=18px nowrap><a href="javascript: toggleDetailsDisplay('shippingDetails', 'Details'); void(0);"><img id='shippingDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;</td>
							<%	}%>
							<tsa:hidden name="ShipTo_icHeader" value="<%=b_req_ic_header%>"/>
							<tsa:hidden name="ShipTo_icLine" value="<%=b_req_ic_line%>"/>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
<%
	if (shipToList != null)
	{
		for (i = 0;i < shipToList.size(); i++)
		{
			ShipTo shipTo = (ShipTo) shipToList.get(i);
			String s_shp_attn = shipTo.getAttention();
			BigDecimal b_shp_qty = shipTo.getQuantity();
			if (b_shp_qty == null)	{	b_shp_qty = new BigDecimal(0);	}
			if (s_shp_attn == null)	{	s_shp_attn = "";							}

			ShipToPK shipToPK = shipTo.getComp_id();
			String s_shp_code = shipToPK.getShipToCode();

			String	s_ship_address_line_1 = "";
			String	s_ship_address_line_2 = "";
			String	s_ship_address_line_3 = "";
			String	s_ship_address_line_4 = "";
			String	s_ship_city = "";
			String	s_ship_state = "";
			String	s_ship_postal_code = "";
			String	s_ship_country = "";

			Address shipToAddress = (Address) shipTo.getShipToAddress();
			if (shipToAddress != null)
			{
				s_ship_address_line_1 = shipToAddress.getAddressLine1();
				s_ship_address_line_2 = shipToAddress.getAddressLine2();
				s_ship_address_line_3 = shipToAddress.getAddressLine3();
				s_ship_address_line_4 = shipToAddress.getAddressLine4();
				s_ship_city = shipToAddress.getCity();
				s_ship_state = shipToAddress.getState();
				s_ship_postal_code = shipToAddress.getPostalCode();
				s_ship_country = shipToAddress.getCountry();
			}
%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td class=browseRow height=12px nowrap><b><%=s_shp_code%></b></td>
							<td class=browseRow width=70px nowrap><b><tsa:label labelName="qty" defaultString="QTY" />:</b>&nbsp;<%=HiltonUtility.getFormattedQuantity(shipTo.getQuantity(), oid)%></td>
							<td class=browseRow width=135px align=right nowrap><b><tsa:label labelName="requiredBy" defaultString="Required By" />:</b> <%=HiltonUtility.getFormattedDate(shipTo.getShipDate(), oid, userDateFormat)%></td>
						</tr>
						</table>

						<div id="shippingDetails" name="shippingDetails" style="display:none;">
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (!HiltonUtility.isEmpty(s_ship_address_line_1))
		{ %>
						<tr><td class=browseRow height=12px nowrap><%=s_ship_address_line_1%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(s_ship_address_line_2))
		{ %>
						<tr><td class=browseRow height=12px nowrap><%=s_ship_address_line_2%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(s_ship_address_line_3))
		{ %>
						<tr><td class=browseRow height=12px nowrap><%=s_ship_address_line_3%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(s_ship_address_line_4))
		{ %>
						<tr><td class=browseRow height=12px nowrap><%=s_ship_address_line_4%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(s_ship_city + s_ship_state + s_ship_postal_code))
		{ %>
						<tr><td class=browseRow height=12px nowrap><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(s_ship_country))
		{ %>
						<tr><td class=browseRow height=12px nowrap><%=s_ship_country%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(s_shp_attn))
		{ %>
						<tr><td class=browseRow height=12px nowrap>Attn: <%=s_shp_attn%></td></tr>
<%	}%>
						</table>
						</div>
<%	}
	}
	if (shipToList == null || shipToList.size() == 0) {%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr><td><b><tsa:label labelName="there-is-no-ShippingSchedule" defaultString="There is no shipping schedule for this item" />.</b></td></tr>
						</table>
<%}%>
					</td>
				</tr>
				</table>
			</td>
			<%List billToList = (List) reqLine.getBillToList();%>
			<td width=50% align=center valign=top>
				<%if (!s_req_type.equals("S") && !s_req_type.equals("I") && !s_req_type.equals("T")) {%>
				<table id="billingTable" border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
							<td width=4% class=browseHdr height=18px nowarap><a href="javascript: if (validateItem()) { doSubmit('/requests/req_billing_schedule.jsp','RequisitionLineUpdate;BillToRetrieveByLine'); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
							<td width=93% class=browseHdr height=18px nowrap>&nbsp;<tsa:label labelName="itemBillingInformation" defaultString="Item Billing Information" /></td>
							<%	if (billToList != null && billToList.size() > 0) {%>
							<td width=3% class=browseHdr height=18px nowrap><a href="javascript: toggleDetailsDisplay('billingDetails', 'Details'); void(0);"><img id='billingDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;</td>
							<%	}%>
							<tsa:hidden name="BillTo_icHeader" value="<%=b_req_ic_header%>"/>
							<tsa:hidden name="BillTo_icLine" value="<%=b_req_ic_line%>"/>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow id=billingRows>

<%
	if (billToList != null)
	{
		for (i = 0;i < billToList.size(); i++)
		{
			BillTo billTo = (BillTo) billToList.get(i);
			String s_bil_attn = billTo.getAttention();
			BigDecimal b_bil_qty = billTo.getQuantity();

			if (b_bil_qty == null)	{	b_bil_qty = new BigDecimal(0);	}
			if (s_bil_attn == null)	{	s_bil_attn = "";							}

			BillToPK billToPK = billTo.getComp_id();
			String s_bil_code = billToPK.getBillToCode();
			String	s_bill_address_line_1 = "";
			String	s_bill_address_line_2 = "";
			String	s_bill_address_line_3 = "";
			String	s_bill_address_line_4 = "";
			String	s_bill_city = "";
			String	s_bill_state = "";
			String	s_bill_postal_code = "";
			String	s_bill_country = "";

			Address billToAddress = (Address) billTo.getBillToAddress();
			if (billToAddress != null)
			{
				s_bill_address_line_1 = billToAddress.getAddressLine1();
				s_bill_address_line_2 = billToAddress.getAddressLine2();
				s_bill_address_line_3 = billToAddress.getAddressLine3();
				s_bill_city = billToAddress.getCity();
				s_bill_state = billToAddress.getState();
				s_bill_postal_code = billToAddress.getPostalCode();
				s_bill_country = billToAddress.getCountry();
			}
%>
						<table border=0 cellspacing=0 cellpadding=2 width=210px class=browseRow>
    						<tr>
    							<td class=browseRow height=12px nowrap><b><%=s_bil_code%></b></td>
    							<td class=browseRow width=75px nowrap><b><tsa:label labelName="qty" defaultString="QTY" />:</b>&nbsp;<%=HiltonUtility.getFormattedQuantity(billTo.getQuantity(), oid)%></td>
    						</tr>
						</table>
						<div id="billingDetails" name="billingDetails" style="display:none;">
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (!HiltonUtility.isEmpty(s_bill_address_line_1))
			{ %>
                         <tr><td class=browseRow height=12px nowrap><%=s_bill_address_line_1%></td>
<%		}
			if (!HiltonUtility.isEmpty(s_bill_address_line_2))
			{ %>
						<tr><td class=browseRow height=12px nowrap><%=s_bill_address_line_2%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_bill_address_line_3))
			{ %>
						<tr><td class=browseRow height=12px nowrap><%=s_bill_address_line_3%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_bill_address_line_4))
			{ %>
						<tr><td class=browseRow height=12px nowrap><%=s_bill_address_line_4%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_bill_city + s_bill_state + s_bill_postal_code))
			{ %>
						<tr><td class=browseRow height=12px nowrap><%=s_bill_city%>&nbsp;<%=s_bill_state%>&nbsp;<%=s_bill_postal_code%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_bill_country))
			{ %>
						<tr><td class=browseRow height=12px nowrap><%=s_bill_country%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_bil_attn))
			{ %>
						<tr><td class=browseRow height=12px nowrap>Attn:&nbsp;<%=s_bil_attn%></td></tr>
<%		}%>
						</table>
						</div>
<% 	}
	}
	if (billToList == null || billToList.size() == 0) {%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr><td><b><tsa:label labelName="there-is-no-BillingSchedule" defaultString="There is no billing schedule for this item" />.</b></td></tr>
						</table>
<%}%>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
		<%}%>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height=18px class=browseHdr><a href="javascript: if (validateItem()) { doSubmit('/requests/req_accounts_ln.jsp','RequisitionLineUpdate;RequisitionLineRetrieveAccount'); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></td></a>
					<td width=68% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="account" defaultString="Account" /></b></td>
					<td width=15% height=18px class=browseHdr align=right>&nbsp;<b><tsa:label labelName="percentAlloc" defaultString="Percent Alloc" />.</b></td>
					<td width=15% height=18px class=browseHdr align=right>&nbsp;<b><tsa:label labelName="percentAlloc" defaultString="Percent Alloc" />.</b></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>

<%
	List accountList = (List) reqLine.getAccountList();
	BigDecimal bd_total_perc = new BigDecimal(0.00);
	BigDecimal bd_total_amt = new BigDecimal(0.00);

	if (accountList != null)
	{
		for (i = 0;i < accountList.size(); i++)
		{
			Account account = (Account) accountList.get(i);

			BigDecimal bd_alloc_perc = account.getAllocPercent();
			BigDecimal bd_alloc_amt = account.getAllocAmount();

			bd_total_perc = bd_total_perc.add(bd_alloc_perc);
			bd_total_amt = bd_total_amt.add(bd_alloc_amt);

			String	s_account = "";
			String	s_accArray[] = new String[15];
			s_accArray[0] = account.getFld1();
			s_accArray[1] = account.getFld2();
			s_accArray[2] = account.getFld3();
			s_accArray[3] = account.getFld4();
			s_accArray[4] = account.getFld5();
			s_accArray[5] = account.getFld6();
			s_accArray[6] = account.getFld7();
			s_accArray[7] = account.getFld8();
			s_accArray[8] = account.getFld9();
			s_accArray[9] = account.getFld10();
			s_accArray[10] = account.getFld11();
			s_accArray[11] = account.getFld12();
			s_accArray[12] = account.getFld13();
			s_accArray[13] = account.getFld14();
			s_accArray[14] = account.getFld15();

			for (int j = 0; j <15; j++)
			{
				if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
				{
					if (s_account.length() > 0)
					{
						s_account = s_account + s_account_separator + s_accArray[j];
					}
					else
					{
						s_account = s_accArray[j];
					}
				}
			}
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=70% class=browseRow><%=s_account%></td>
					<td width=15% class=browseRow align=right><%=HiltonUtility.getBigDecimalFormatted(bd_alloc_perc, 2)%>%</td>
					<td width=15% class=browseRow align=right><%=HiltonUtility.getFormattedDollar(account.getAllocAmount(), oid)%></td>
				</tr>
				</table>
<%	}
	}
	if (accountList != null && accountList.size() > 0) { %>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td class=browseRow>&nbsp;</td>
					<td class=browseRow colspan=2 valign=top><hr size=0 align=right colspan=2></td>
				</tr>
				<tr>
					<td width=70% class=browseRow>&nbsp;</td>
					<td width=15% class=browseRow align=right><%=HiltonUtility.getBigDecimalFormatted(bd_total_perc, 2)%>%</td>
					<td width=15% class=browseRow align=right><%=HiltonUtility.getFormattedDollar(bd_total_amt, oid)%></td>
				</tr>
				</table>
<%} else {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td><b><tsa:label labelName="noAccountsItem" defaultString="There are no accounts for this item" />.</b></td></tr>
				</table>
<%}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<%List commentList = (List) reqLine.getDocCommentList();%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height=18px class=browseHdr><a href="javascript: if (validateItem()) { doSubmit('/requests/req_notes_ln.jsp','RequisitionLineUpdate;DocCommentRetrieveByLine'); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></td></a>
					<td width=64% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="comment" defaultString="Comment" /></b></td>
					<td width=8% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="print" defaultString="Print" /></b></td>
					<td width=7% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="bold" defaultString="Bold" /></b></td>
					<td width=16% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="placement" defaultString="Placement" /></b></td>
					<%	if (commentList != null && commentList.size() > 0)
					{%>
					<td width=3% height=18px class=browseHdr align=center><a href="javascript: toggleDetailsDisplay('commentDetails', 'Details'); void(0);"><img id='commentDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Comments"></a></td>
					<%	}%>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
	String s_placeString = "";
	if (commentList != null)
	{
		for (i = 0;i < commentList.size(); i++)
		{
			DocComment docComment = (DocComment) commentList.get(i);
			DocText docText = docComment.getDocText();
			String s_title = docComment.getCommentTitle();
			String s_print = docComment.getCommentPrint();
			String s_bold = docComment.getCommentBold();
			String s_place = docComment.getCommentPlace();
			String s_text = docText.getStdText();

			if (s_place.equals("A"))
			{
				s_place = "After";
			}
			else if (s_place.equals("B"))
			{
				s_place = "Before";
			}
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=66% class=browseRow><%=s_title%></td>
					<td width=8% class=browseRow align=center valign=top><%=s_print%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_bold%></td>
					<td width=16% class=browseRow align=center valign=top><%=s_place%></td>
					<td width=3% class=browseRow align=center valign=top>&nbsp;</td>
				</tr>
				</table>
				<div id="commentDetails" name="commentDetails" style="display:none;">
				<table>
				<tr>
					<td width=75px>&nbsp;</td>
					<td width=100%>
<%				if (s_bold.equals("Y")) 	{ %>	<b><% } %><%=s_text%><% if (s_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				</table>
				</div>
<% 	}
	}
	if (commentList == null || commentList.size() == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td><b><tsa:label labelName="noCommentsItem" defaultString="There are no comments for this item" />.</b></td></tr>
				</table>
<%}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<!-- <table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
	<td width=50% align=center><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionLineUpdate;RequisitionRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
<%	}
		else if (s_view.equalsIgnoreCase("WIZARD"))
		{
%>
	<td width=50% align=center><a href="javascript: doSubmit('<%=s_return_page%>', 'RequisitionLineUpdate;<%=s_return_method%>'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('<%=s_return_page%>', '<%=s_return_method%>'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
<%	} %>
</tr>
</table> -->

<table>
<tr>
	<td>
		<tsa:hidden name="RequisitionLine_discOvr" value="<%=reqLine.getDiscOvr()%>"/>
		<tsa:hidden name="RequisitionLine_discountPercent" value="<%=HiltonUtility.getBigDecimalFormatted(reqLine.getDiscountPercent(), 2)%>"/>
		<tsa:hidden name="RequisitionLine_discountAmount" value="<%=HiltonUtility.getFormattedDollar(reqLine.getDiscountAmount(), oid)%>"/>
		<tsa:hidden name="RequisitionLine_taxOvr" value="<%=reqLine.getTaxOvr()%>"/>
		<tsa:hidden name="RequisitionLine_taxPercent" value="<%=HiltonUtility.getBigDecimalFormatted(reqLine.getTaxPercent(), 5)%>"/>
		<tsa:hidden name="RequisitionLine_taxAmount" value="<%=HiltonUtility.getFormattedDollar(reqLine.getTaxAmount(), oid)%>"/>
		<tsa:hidden name="RequisitionLine_shipOvr" value="<%=reqLine.getShipOvr()%>"/>
		<tsa:hidden name="RequisitionLine_shippingCharges" value="<%=HiltonUtility.getFormattedDollar(reqLine.getShippingCharges(), oid)%>"/>
		<tsa:hidden name="RequisitionLine_otherOvr" value="<%=reqLine.getOtherOvr()%>"/>
		<tsa:hidden name="RequisitionLine_otherCharges" value="<%=HiltonUtility.getFormattedDollar(reqLine.getOtherCharges(), oid)%>"/>
	</td>
</tr>
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

<%	if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0) {
			if (s_req_type.equals("S") || s_req_type.equals("T") || s_req_type.equals("I")) { %>
				hideArea('itemtype');
				frm.as_item_type[1].checked = "TRUE";
<%		}
		} %>

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

		for (i = 0; i < myAreas.length; i++) {
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

	function addUp ()
	{
		var price_dec = <%=Integer.valueOf(s_price_decimals).intValue()%>;
		var dollar_dec = <%=Integer.valueOf(s_dollar_decimals).intValue()%>;
		var qty_dec = <%=Integer.valueOf(s_quantity_decimals).intValue()%>;
		var p = nformat(eval(nfilter(frm.RequisitionLine_unitPrice)),price_dec);
		var q = nformat(eval(nfilter(frm.RequisitionLine_quantity)),qty_dec);
		var f = eval(nfilter(frm.RequisitionLine_umFactor));

		if (f == 0) { f = 1; }

		frm.RequisitionLine_umFactor.value = f;
		frm.RequisitionLine_unitPrice.value = p;
		frm.RequisitionLine_quantity.value = q;

		frm.computed_subtotal.value = nformat( p * q * f, dollar_dec );
		if ( p == q && q == 0) {
			frm.RequisitionLine_unitPrice.value = '';
			frm.RequisitionLine_quantity.value = '';
			frm.computed_subtotal.value = '';
		}

		//frm.ss_lnqty.value = frm.RequisitionLine_quantity.value;
	}

	function getPriceBreak() {
		var unitPrice = frm.RequisitionLine_unitPrice.value;
		var qty = frm.RequisitionLine_quantity.value;
<%	for (int ipb=0; ipb < breakCount; ipb++) {
			CatalogPriceBrk catalogPriceBrk = (CatalogPriceBrk) catalogPriceBrkList.get(ipb);
			if (ipb == 0) {%>
		if (qty >= <%=catalogPriceBrk.getQtyFrom()%> && qty <= <%=catalogPriceBrk.getQtyTo()%>) {
<%		} else {%>
		else if (qty >= <%=catalogPriceBrk.getQtyFrom()%> && qty <= <%=catalogPriceBrk.getQtyTo()%>) {
<%		}%>
			unitPrice = <%=catalogPriceBrk.getUnitPrice()%>;
		}
<%	}%>
		frm.RequisitionLine_unitPrice.value = unitPrice;
	}

	function checkQty (x)
	{
<%	if (s_minmax_restrict.equalsIgnoreCase("Y"))
		{ %>
			var qty_dec = <%=Integer.valueOf(s_quantity_decimals).intValue()%>;
			var qty = eval(nfilter(frm.RequisitionLine_quantity));
			var min = eval(nfilter(frm.min_qty));
			var max = eval(nfilter(frm.max_qty));

			if ( (qty < min) && (min >= 0) )
			{
				min = nformat(min, qty_dec);
				alert("The minimum requested quantity allowed for this item is " + min + ".");
				frm.RequisitionLine_quantity.value = min;
			}
			else if ( (qty > max) && (max > 0) )
			{
				max = nformat(max,qty_dec);
				alert("The maximum requested quantity allowed for this item is " + max + ".");
				frm.RequisitionLine_quantity.value = max;
			}
<%	}%>
		return;
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

		frm.RequisitionLine_umCode.value = trim(frm.RequisitionLine_umCode);

		if (info_window != null) {
			if (browser == "Netscape") {
				if (info_window.closed == false) {
					info_window.setUomCode("RequisitionLine_");
					open = false;
				}
			}
			else {
				info_window.setUomCode("RequisitionLine_");
				open = false;
			}
		}

		if (open == true)
		{
			if (uomArray.length > 0 || populated)
			{
				code = frm.RequisitionLine_umCode.value;
				for (var i = 0; i < uomArray.length; i++)
				{
					if (code == (uomArray[i][0]).toString())
					{
						factor = uomArray[i][1];
						break;
					}
				}

				setUmFactor(factor);
			}
			else
			{
				popupParameters = "as_prefix=RequisitionLine_";

				setLookupParameters('/base/get_uom_info.jsp', 'UnitOfMeasureRetrieveAll');
				document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
			}
		}
	}

	function setUmFactor(factor) {
		frm.RequisitionLine_umFactor.value = factor;
		addUp();
	}

	function addItem()
	{
		var line_count = frm.lineCount.value;
		line_count++;
		frm.lineCount.value = line_count;

		doSubmit('/requests/req_item.jsp', 'RequisitionLineUpdate;RequisitionLineCreate');
	}

	function thisLoad()
	{
		f_StartIt();
<%	if ((s_req_status.compareTo(DocumentStatus.REQ_INPROGRESS) == 0 || s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0) && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0) { %>
		checkInputSettings();
		allowInputEdit(frm.RequisitionLine_noteCancel, true);
<%	} else if (reqLine.getItemSource().equals("XML")) {%>
		checkInputSettings();
		allowInputEdit(frm.RequisitionLine_quantity, true);
		allowInputEdit(frm.RequisitionLine_udf1Code, true);
		allowInputEdit(frm.RequisitionLine_udf2Code, true);
		allowInputEdit(frm.RequisitionLine_udf3Code, true);
		allowInputEdit(frm.RequisitionLine_udf4Code, true);
		allowInputEdit(frm.RequisitionLine_udf5Code, true);
		allowInputEdit(frm.RequisitionLine_itemLocation, true);
		allowInputEdit(frm.RequisitionLine_receiptRequired, true);
<%	}
	if(reqLine.getCatalogId().compareToIgnoreCase("VINIMAYA") == 0){%>
		allowInputEdit(frm.RequisitionLine_noteCancel, true);
	<% } %>
	}

		function totalsAlert() {
		var alertMessage = "";

<%	if (Integer.valueOf(s_line_count).intValue() > 1) { %>
			if ((frm.RequisitionLine_discOvr.value == "N") && (eval(frm.RequisitionLine_discountPercent.value) == 0) && (eval(frm.RequisitionLine_discountAmount.value) > 0)) {
				alertMessage += "Discount will be redistributed across remaining line items.\n";
			}
			if ((frm.RequisitionLine_taxOvr.value == "N") && (eval(frm.RequisitionLine_taxPercent.value) == 0) && (frm.RequisitionLine_taxable.value == "Y") && (eval (frm.RequisitionLine_taxAmount.value) > 0)) {
				alertMessage += "Tax will be redistributed across remaining line items.\n";
			}
			if ((frm.RequisitionLine_shipOvr.value == "N") && (eval(frm.RequisitionLine_shippingCharges.value) > 0)) {
				alertMessage += "Shipping Charges will be redistributed across remaining line items.\n";
			}
			if ((frm.RequisitionLine_otherOvr.value == "N") && (eval(frm.RequisitionLine_otherCharges.value) > 0)) {
				alertMessage += "Other Charges will be redistributed across remaining line items.\n";
			}
			if ( alertMessage.length > 0 ) {
				alert ( 'Totals Update:\n'+alertMessage );
			}
<%	}%>
		return true;
	}

	function retrieveLine(linenumber)
	{
		frm.lineToRetrieve.value = linenumber;
		doSubmit('/requests/req_item.jsp', 'RequisitionLineUpdate;RequisitionLineRetrieveByLineNumber');
	}

	function viewItemHistory()
	{
		popupParameters = "HistoryLog_icLineHistory=<%=reqLine.getIcLineHistory()%>;formtype=REQ;RequisitionLine_icReqLine=<%=reqLine.getIcReqLine()%>;requisitionNumber=<%=headerEncoder.encodeForJavaScript(reqLine.getRequisitionNumber())%>";
		doSubmitToPopup('/requests/req_history.jsp', 'HistoryLogRetrieveByHistoryLine', 'width=675px', 'height=350px');
	}

	function itemSearch() {
		itemSearchWithUpdate('RequisitionLineUpdate');
	}

	function getItemInfo() {
		// No need to call the server if the item number is empty
		if (!isEmpty(frm.RequisitionLine_itemNumber.value)) {
			doSubmit('requests/req_item.jsp', 'RequisitionLineItemLookup;RequisitionLineRetrieve');
		}
	}

	function validateItem() {
		if (isEmpty(frm.RequisitionLine_itemNumber.value) && isEmpty(frm.RequisitionLine_description.value)) {
			alert("You must enter either an item number or description for this item.");
			return false;
		}
		return true;
	}

	function viewImage(type) {
		var imgFileName = "";
		var itemNumber = frm.RequisitionLine_itemNumber.value;
		var imageFile = "<%=catalogItemImageFile%>";
		var catalogName = frm.catalogId.value;
		var imageUrl = "<%=DictionaryManager.getInstance("host", oid).getProperty("item-image-url", "/")%>";

		if (imageUrl.substring(imageUrl.length - 1) != "/") {
			imageUrl = imageUrl + "/";
		}

		imageUrl = imageUrl + catalogName + "/";
		imgFileName = imageFile;

		if (imgFileName.indexOf("http://") >= 0)
		{
			if (catalogName == "BOISE" || catalogName == "BOISE-WEB" || catalogName == "RESTRICTED") {
				var udf2 = "<%=reqLine.getUdf2Code()%>";
				var imgFileName = "http://www.officemaxsolutions.com/cif/viewItem.html?nid=" + udf2;
			}
			doSubmitToPopup(imgFileName, 'DoNothing');
		}
		else
		{
			doSubmitToPopup(imageUrl + imgFileName, 'DoNothing');
		}
	}

	function validateForm() {
		var deleteItem = false;
		var handlers = frm.handler.value;
		var originalQuantity = "<%=reqLine.getQuantity()%>";
		var originalPrice = "<%=reqLine.getUnitPrice()%>";

		if (isEmpty(frm.RequisitionLine_itemNumber.value) && isEmpty(frm.RequisitionLine_description.value) && handlers.indexOf("RequisitionLineDelete") < 0) {
			// no need to do this check if we are already deleting the line item or if changes are going to be discarded and an item number or description was previously entered
			if (frm.lineUpdated.value == "true") {
				if (handlers == "RequisitionLineRetrieveByHeaderHandler;") {
<%				if ( !HiltonUtility.isEmpty(reqLine.getItemNumber()) || !HiltonUtility.isEmpty(reqLine.getDescription()) ) {	%>
						// no need to delete item if changes are going to be discarded and an item number or description was previously entered
						return true;
<%				} else {	%>
						deleteItem = true;
<%				}	%>
				}
				if (!deleteItem) {
					if (confirm("This is not a valid item because it has no item number and no description!  Would you like to discard changes?")) {
						deleteItem = true;
					} else {
						if (handlers.indexOf("RequisitionLineCreate") >= 0) {
							frm.lineCount.value = frm.lineCount.value - 1;
						}
						return false;
					}
				}
			} else {
				deleteItem = true;
			}

			if (deleteItem) {
				var ind = handlers.indexOf("RequisitionLineUpdateHandler;");
				if (ind >= 0) {
					handlers = handlers.substring(0, ind) + handlers.substring(ind + 29, handlers.length);
				} else {
					ind = handlers.indexOf("RequisitionLineUpdateHandler");
					if (ind >= 0) {
						handlers = handlers.substring(0, ind) + handlers.substring(ind + 28, handlers.length);
					} else {
						ind = handlers.indexOf("RequisitionLineUpdate;");
						if (ind >= 0) {
							handlers = handlers.substring(0, ind) + handlers.substring(ind + 22, handlers.length);
						} else {
							ind = handlers.indexOf("RequisitionLineUpdate");
							if (ind >= 0) {
								handlers = handlers.substring(0, ind) + handlers.substring(ind + 21, handlers.length);
							}
						}
					}
				}
				frm.lineCount.value = frm.lineCount.value - 1;

				if (frm.lineToRetrieve.value > frm.RequisitionLine_lineNumber.value) {
					frm.lineToRetrieve.value = frm.lineToRetrieve.value - 1;
				}
				frm.handler.value = "RequisitionLineDeleteHandler;" + handlers;
				return true;
			}
			else {
				if (frm.handlers.indexOf("RequisitionLineCreate") >= 0) {
					frm.lineCount.value = frm.lineCount.value - 1;
				}
				return false;
			}
		} else {
			if (eval(frm.RequisitionLine_quantity.value) != eval(originalQuantity))
			{
				frm.originalQuantity.value = originalQuantity;
				frm.writehistory.value = "Y";
			}
			if (eval(frm.RequisitionLine_unitPrice.value) != eval(originalPrice))
			{
				frm.originalPrice.value = originalPrice;
				frm.writehistory.value = "Y";
			}
			return true;
		}
	}

	function viewPriceBreaks(type) {
		var catid = frm.catalogId.value;
		var itemNumber = frm.RequisitionLine_itemNumber.value;

		popupParameters = "browseName=catalog-pricebrk";
		popupParameters = popupParameters + ";colname=CatalogPriceBrk_id_catalogId;operator==;filter_txt=" + catid + ";logicalOperator=AND;originalFilter=Y;sort=N";
		popupParameters = popupParameters + ";colname=CatalogPriceBrk_id_itemNumber;operator==;filter_txt=" + itemNumber + ";logicalOperator=AND;originalFilter=Y;sort=N;";

		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}

	function cancelReqLine() {
		if (frm.RequisitionLine_noteCancel.value <= 0) {
			alert("Please first enter notes to the requisitioner explaining why you are cancelling this requisition line.");
		}
		else {
			doSubmit('/requests/req_item.jsp','RequisitionLineCancel;RequisitionLineRetrieve');
		}
	}

	function closeReqLine() {
		if (frm.RequisitionLine_noteCancel.value <= 0) {
			alert("Please first enter notes to the requisitioner explaining why you are closing this requisition line.");
		}
		else {
			frm.reqaction.value = "close";
			doSubmit('/requests/req_item.jsp','RequisitionLineClose;RequisitionLineRetrieve');
		}
	}

	function returnReqLine() {
		doSubmit('/requests/req_item.jsp','RequisitionLineRetrieve');
	}

// End Hide script -->
</SCRIPT>
