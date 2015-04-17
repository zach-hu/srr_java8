<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/attachments.js"></script>
<%@ page import="java.math.*" %>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.catalog.tasks.CatalogItemManager" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<%
	int i;
	int i_size;
	PoLine poLine = (PoLine) request.getAttribute("poLine");
	BigDecimal catalogItemDifference = new BigDecimal(0);
	if(request.getAttribute("catalogItemDifference") != null)
	{
		catalogItemDifference = (BigDecimal) request.getAttribute("catalogItemDifference");
	}

	PoHeader poHeader = (PoHeader)request.getAttribute("poHeader");
	String orgReqType = "";
	if ( poHeader != null)
	{
		orgReqType = poHeader.getOriginalReqType();
	}
	String readonly = "";
	String disabled = "";
	if (orgReqType.equalsIgnoreCase("M") || !HiltonUtility.isEmpty(poHeader.getMsrNumber()))
	{
		readonly = "readonly";
		disabled = "true";
	}
	String	s_po_type = (String) request.getAttribute("PoHeader_poType");
	String	s_po_status = poLine.getStatus();
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_minmax_restrict = propertiesManager.getProperty("REQ OPTIONS", "MinMaxRestrict", "N");
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	String	s_budgetActive = propertiesManager.getProperty("MODULES", "ACCOUNT BUDGET", "N");
	boolean bAdminChanges = false;
	boolean fdcsEnabled = propertiesManager.getProperty("FDCS","Enabled","N").equalsIgnoreCase("Y") ;
	boolean defaultAccountsByCommodity = propertiesManager.getProperty("ACCOUNTS","DEFAULTBYCOMMODITY","N").equals("Y");
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	BigDecimal bd_ic_po_header = poLine.getIcPoHeader();
	BigDecimal bd_ic_po_line = poLine.getIcPoLine();
	String	s_po_number = poLine.getPoNumber();
	String	s_release_number = (String) request.getAttribute("PoHeader_releaseNumber");
	String	s_revision_number = (String) request.getAttribute("PoHeader_revisionNumber");
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	String  defaultByCommodity = propertiesManager.getProperty("ACCOUNTS", "DEFAULTBYCOMMODITY", "N");
	String	s_fiscal_year = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_fiscalYear"));
	if (HiltonUtility.isEmpty(s_fiscal_year))
	{
		s_fiscal_year = HiltonUtility.getFiscalYear(oid, userTimeZone);
	}
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_itemLocation"));
	String s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_buyerCode"));
	s_flag = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_flagChange"));
	String s_required_date = HiltonUtility.getFormattedDate(poLine.getRequiredDate(), oid, userDateFormat);
	BigDecimal bd_line_number = HiltonUtility.getBigDecimalFormatted(poLine.getLineNumber(), 0);
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_asset = poLine.getAsset();
	String	s_taxable = poLine.getTaxable();
	String s_udf1= poLine.getUdf1Code();
	String s_udf6 = poLine.getUdf6Code();
	String s_udf7 = poLine.getUdf7Code();
	String	s_um_code = poLine.getUmCode();
	BigDecimal	bd_um_factor = poLine.getUmFactor();
	String	s_receipt_required = poLine.getReceiptRequired();
	BigDecimal	s_po_total = poLine.getLineTotal();
	String s_udf10Code = HiltonUtility.ckNull((String)poLine.getUdf10Code());
	String s_shelfLife	= poLine.getShelfLifeRqd();
	String maxLength = "15";
	if (s_udf1.length() > 15)
	{
		maxLength = "30";
	}

	BigDecimal bd_subtotal = HiltonUtility.getFormattedDollar(poLine.getQuantity().multiply(poLine.getUnitPrice()).multiply(bd_um_factor), oid);

	String s_ic_po_header = bd_ic_po_header.toString();
	List catalogPriceBrkList = (List) request.getAttribute("catalogPriceBrkList");
	int breakCount = 0;

	if (catalogPriceBrkList == null) {
		catalogPriceBrkList = new ArrayList();
	} else {
		breakCount = catalogPriceBrkList.size();
	}

	if (HiltonUtility.isEmpty(s_po_number))	{	s_po_number = "N/A";		}
	if (s_receipt_required == null)		{	s_receipt_required = "";	}

	String	s_from_page = HiltonUtility.ckNull((String) request.getAttribute("frompage"));
	String	s_return_page = "";
	String	s_return_method = "";
	String	lookupStatus = (String) request.getAttribute("lookupStatus");
	String	shipToTaxCodeSet = (String) request.getAttribute("shipToTaxCodeSet");

	if (lookupStatus == null) {
		lookupStatus = "";
	}
	if (shipToTaxCodeSet == null) {
		shipToTaxCodeSet = "";
	}

	if(s_from_page.equalsIgnoreCase("poitemscancel")){
		s_return_page = "/orders/po_items_cancel_options.jsp";
		s_return_method = "PoCancel";
	}
	else if (s_from_page.equalsIgnoreCase("shopcart"))
	{
		s_return_page = "/orders/po_items.jsp";
		s_return_method = "PoLineRetrieveByHeader";
	}
	else
	{
		if (s_view.equals("CLASSIC")) {
			s_return_page = "/orders/po_summary.jsp";
		} else {
			s_return_page = "/orders/po_review.jsp";
		}
		s_return_method = "PoRetrieve";
	}
	if (poLine.getRequiredDate() == null)
	{
		s_required_date = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_requiredDate"));
	}
	if(HiltonUtility.isEmpty(s_required_date))
	{
		s_required_date = (String) HiltonUtility.getFormattedDate(d_today, oid);
	}
	String showLinkGetItemInfo = (String)request.getAttribute("showLinkGetItemInfo");
	if(showLinkGetItemInfo == null)
		showLinkGetItemInfo = "N";

	boolean b_udf3 = false;
	if (oid.equalsIgnoreCase("hoy08p") && poLine.getUdf3Code().equalsIgnoreCase("Y"))
		b_udf3 = true;

	String	s_use_subcommodity = propertiesManager.getProperty("MISC", "USE SUBCOMMODITY", "N");

	String default_taxable_by_udf1 = propertiesManager.getProperty("REQ OPTIONS", "DEFAULT TAXABLE BY UDF1", "");
	if (poHeader != null) {
		String udf1Code = poHeader.getUdf1Code();
		if (!HiltonUtility.isEmpty(default_taxable_by_udf1)) {
			if (s_po_status.equals(DocumentStatus.PO_INPROGRESS) && !HiltonUtility.isEmpty(udf1Code)) {
				if (udf1Code.equals(default_taxable_by_udf1)) {
					s_taxable = "Y";
				} else {
					s_taxable = "N";
				}
			}
			else {
				s_taxable = "N";
			}
		}
	}

	List inspectionList = (List) request.getAttribute("inspectionList");
	if(inspectionList == null)
		inspectionList = new ArrayList();

	List chargeCodeList = (List) request.getAttribute("chargeCodeList");
	if(chargeCodeList == null)
		chargeCodeList = new ArrayList();
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=poLine.getPoNumber()%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="PoHeader_status" value="<%=(String) request.getAttribute(\"PoHeader_status\")%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="PoHeader_buyerCode" value="<%=s_buyer_code%>"/>
<tsa:hidden name="PoHeader_flagChange" value="<%=s_flag%>"/>
<tsa:hidden name="PoLine_icPoLine" value="<%=bd_ic_po_line%>"/>
<tsa:hidden name="PoLine_icRelKey" value="<%=poLine.getIcRelKey()%>"/>
<tsa:hidden name="PoLine_icReqLine" value="<%=poLine.getIcReqLine()%>"/>
<tsa:hidden name="PoLine_icReqLineOld" value="<%=poLine.getIcReqLine()%>"/>
<tsa:hidden name="PoLine_icRfqLine" value="<%=poLine.getIcRfqLine()%>"/>
<tsa:hidden name="PoLine_lineNumber" value="<%=bd_line_number%>"/>
<tsa:hidden name="PoLine_status" value="<%=s_po_status%>"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="Account_icLine" value="<%=bd_ic_po_line%>"/>
<tsa:hidden name="Account_accountType" value="POL"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="DocComment_icLine" value="<%=bd_ic_po_line%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="stdTable_tableType" value=""/>
<tsa:hidden name="formtype" value="PO"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>
<tsa:hidden name="lineUpdated" value="false"/>
<tsa:hidden name="lineToRetrieve" value=""/>
<tsa:hidden name="catalogId" value="<%=poLine.getCatalogId()%>"/>
<tsa:hidden name="writehistory" value="N"/>
<tsa:hidden name="originalQuantity" value=""/>
<tsa:hidden name="originalPrice" value=""/>
<tsa:hidden name="poaction" value=""/>
<tsa:hidden name="fromPoItemPage" value="Y"/>
<tsa:hidden name="showLinkGetItemInfo" value="<%=showLinkGetItemInfo%>"/>
<tsa:hidden name="docType" value="PO"/>
<tsa:hidden name="updateUdf10" value="N"/>
<tsa:hidden name="commodityUpdateAccount" value=""/>
<tsa:hidden name="newAccount_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="newAccount_icLine" value="<%=bd_ic_po_line%>"/>
<tsa:hidden name="Commodity_commodity" value=""/>
<tsa:hidden name="originalCommodityCode" value="<%=poLine.getCommodity()%>"/>
<tsa:hidden name="currentPage" value="item"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="lineItemOf" defaultString="<%= \"Line Item \" + bd_line_number + \" of  \" + headerEncoder.encodeForHTMLAttribute(s_line_count)%>" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/orders/po_display_number.jsp" %>
	</td>
</tr>
</table>

<br>
<%@ include file="/orders/po_info.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center valign=top width=75%>
		<br>
		<table border=0 cellpadding=0 cellspacing=0 height=100%>
		<tr>
			<td>
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "po-itemNumber")%> nowrap align=right><tsa:label labelName="po-itemNumber" defaultString="Item/Part #" checkRequired="true" />:</td>
					<td <%=HtmlWriter.isVisible(oid, "po-itemNumber")%>><input type="text" name="PoLine_itemNumber" tabIndex="1" size="25" maxLength="30" value="<%=poLine.getItemNumber()%>" onchange="upperCase(this); setFieldsToDisplayByItem(); <% if (showLinkGetItemInfo.equalsIgnoreCase("N")) { %> getItemInfo(); <% } %> updated(); void(0);" /></td>
				<% if (showLinkGetItemInfo.equalsIgnoreCase("Y")) { %>
					<td <%=HtmlWriter.isVisible(oid, "po-itemNumber")%> nowrap align=right><a href="javascript: getItemInfo(); void(0);" title="Click here to Get Item Info">Get Item # Info</a></td>
				<% } %>


				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-description")%>>
					<td nowrap align=right valign=top><tsa:label labelName="po-description" defaultString="Description" checkRequired="true" />:</td>
					<td colspan=4><tsa:input type="textarea" wrap="virtual" name="PoLine_description" tabIndex="3" rows="5" cols="60" onkeydown="textCounter(this, 2000);" onkeyup="textCounter(this,2000);" onchange="textCounter(this,2000); updated();"><%=poLine.getDescription()%></tsa:input></td>
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
						<table border="0" cellpadding="0" cellspacing="2">
						<tr>
						<% if (!oid.equals("SRR10P")) { %>
							<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-Asset")%> nowrap align="<%=s_align%>">
								<tsa:label labelName="OrderLineItem-Asset" defaultString="Asset" />:
								<input type=checkbox name="c_checkbox" tabindex="5" <% if (s_asset.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(frm.PoLine_asset,0); updated();" <%=HtmlWriter.onChangeUpdated()%>>&nbsp;
								<tsa:hidden name="PoLine_asset" value="<%=s_asset%>"/>
							</td>
						<% } %>
							<td <%=HtmlWriter.isVisible(oid, "po-taxable")%> nowrap align="<%=s_align%>">
								<tsa:label labelName="po-taxable" defaultString="Taxable" />:
								<input type=checkbox name="c_checkbox" tabindex="7" <% if (s_taxable.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(PoLine_taxable, 1); updated();" <%=HtmlWriter.onChangeUpdated()%>>
								<tsa:hidden name="PoLine_taxable" value="<%=s_taxable%>"/>
							</td>
<%	boolean shelfLife	= false;
	if (oid.equalsIgnoreCase("vse06p")) {	%>
							<td <%=HtmlWriter.isVisible(oid, "po-LN01")%> align="<%=s_align%>">
								<tsa:label labelName="po-LN01" defaultString="Line UDF1" />:
								<input type=checkbox name="c_checkbox" tabindex="9" <% if (s_udf1.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(PoLine_udf1Code, 2); updated();">
								<tsa:hidden name="PoLine_udf1Code" value="<%=s_udf1%>"/>
							</td>
<%	shelfLife = true;}	%>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "po-quantity")%> nowrap align=right><tsa:label labelName="po-quantity" defaultString="Quantity" checkRequired="true" />:</td>
					<td <%=HtmlWriter.isVisible(oid, "po-quantity")%>><tsa:input type="midtext" tabIndex="11" name="PoLine_quantity" maxLength="25" value="<%=HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid)%>" style="text-align:right" onchange="getPriceBreak(); addUp(); checkQty(); updated();" /><% if (breakCount > 0) {%>&nbsp;&nbsp;<a href="javascript: viewPriceBreaks(); void(0);" alt="View Price Breaks"><b>$</b></a><%}%></td>

					<td <%=HtmlWriter.isVisible(oid, "po-catalog")%> align=right nowrap><tsa:label labelName="po-catalog" defaultString="Catalog" checkRequired="true" />:</td>
					<td <%=HtmlWriter.isVisible(oid, "po-catalog")%>><tsa:input type="mintext" name="PoLine_catalogId" value="<%=poLine.getCatalogId()%>" readonly="true" disabled="true" /></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "po-uom", s_po_type)%> align=right nowrap><A HREF="javascript: setAllowBrowse(); browseLookup('PoLine_umCode', 'unitofmeasure'); void(0);" title="Click here to choose the <tsa:label labelName="po-uom" defaultString="UOM" /> for this item or enter the <tsa:label labelName="po-uom" defaultString="UOM" /> in the box on the right."><tsa:label labelName="po-uom" defaultString="UOM" checkRequired="true" /></a>:</td>
					<td <%=HtmlWriter.isVisible(oid, "po-uom", s_po_type)%>>
						<tsa:input type="midtext" name="PoLine_umCode" tabIndex="13" maxLength="15" value="<%=s_um_code%>" onchange="upperCase(this); updateUMFactor(); updated();" />
						<tsa:hidden name="PoLine_umFactor" value="<%=bd_um_factor%>"/>
					</td>

					<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-ModelNumber")%> align=right nowrap>
						<tsa:label labelName="OrderLineItem-ModelNumber" defaultString="Model Number" checkRequired="true" />:
					</td>
					<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-ModelNumber")%>><tsa:input type="mintext" name="PoLine_modelNumber" tabIndex="30" maxLength="20" value="<%=poLine.getModelNumber()%>" onchange="upperCase(this); updated();" /></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "po-unitCost")%> align=right nowrap><tsa:label labelName="po-unitCost" defaultString="Unit Cost" checkRequired="true" />:</td>
					<td <%=HtmlWriter.isVisible(oid, "po-unitCost")%>><input type=text name="PoLine_unitPrice" tabindex="15" size="20" maxlength=25 value="<%=HiltonUtility.getFormattedPrice(poLine.getUnitPrice(), oid)%>" style="text-align:right" onchange="addUp(); updated();" onkeyup="checkDecimals(this, <%=s_price_decimals %>);" <% if (b_udf3) { %>disabled<% } %>>
					    <% if( catalogItemDifference.compareTo(new BigDecimal(0)) != 0  ){ %>
					  	<a href="javascript:showCatalogItemDifference('<%=catalogItemDifference.abs()%>');"  >
							<img src="<%=contextPath%>/images/alert.gif" border=0 width="17" height="15" ></img>
					  	</a>
					  <% }%>
					</td>

					<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-Manufacturer")%> align=right nowrap>
						<tsa:label labelName="OrderLineItem-Manufacturer" defaultString="Manufacturer" checkRequired="true" />:
					</td>
					<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-Manufacturer")%>><tsa:input type="mintext" name="PoLine_mfgName" tabIndex="33" maxLength="25" value="<%=poLine.getMfgName()%>" onchange="upperCase(this); updated();" /></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "po-extendedCost")%> align=right nowrap><tsa:label labelName="po-extendedCost" defaultString="Extended Cost" />:</td>
					<td <%=HtmlWriter.isVisible(oid, "po-extendedCost")%>><tsa:input type="midtext" name="computed_subtotal" value="<%=bd_subtotal%>" style="text-align:right" onchange="addUp(0);" disabled="true" /></td>
					<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-LineUDF01")%> align=right nowrap>
					<% if (DictionaryManager.isLink(oid, "OrderLineItem-LineUDF01")) { %>
						<a href="javascript: <% if (s_use_subcommodity.equalsIgnoreCase("Y")) {%>browseCommodity('PoLine_udf1Code','subcommodity','<%=propertiesManager.getProperty("MISC", "SubCommodityType", "")%>');<% } else {%> browseStd('PoLine_udf1Code', 'LN01');<%}%> void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "OrderLineItem-LineUDF01", "Line UDF 1")%> for this item or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "OrderLineItem-LineUDF01", "Line UDF 1", true)%></a>:</td>
					<% } else { %>
						<tsa:label labelName="OrderLineItem-LineUDF01" defaultString="Line UDF 1" checkRequired="true" />:
					<% } %>
					<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-LineUDF01")%>><tsa:input type="mintext" name="PoLine_udf1Code" id="PoLine_udf1Code" tabIndex="35" size="30" maxLength="30" value="<%=s_udf1%>" onchange="upperCase(this); updated();" /></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "po-commodity")%> align=right nowrap><A HREF="javascript: <% if (s_use_subcommodity.equalsIgnoreCase("Y")) {%>browseCommodity('PoLine_commodity','subcommodity','<%=propertiesManager.getProperty("MISC", "SubCommodityType", "")%>');<% } else {%>browseCommodityByType(); <%}%>void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-commodity", "Commodity")%> for this item or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-commodity", "Commodity", true)%></a>:</td>
					<td <%=HtmlWriter.isVisible(oid, "po-commodity")%>><input type=text name="PoLine_commodity" id="PoLine_commodity" tabindex="17" size="25" maxlength=15 value="<%=poLine.getCommodity()%>" onchange="upperCase(this); updated(); getNewInfo('commodity', this); <% if(defaultByCommodity.equalsIgnoreCase("Y")) { %> updateReqLineAccount();<% } %>"></td>
<%	if (oid.equalsIgnoreCase("vse06p")) { %>
					<td <%=HtmlWriter.isVisible(oid, "po-LN02")%> align=right nowrap><tsa:label labelName="po-LN02" defaultString="Line UDF2" checkRequired="true" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "po-LN02")%> >
						<select tabindex="37" name="PoLine_udf2Code" size=1>
<%
		if (HiltonUtility.isEmpty(poLine.getUdf2Code()))
		{
			poLine.setUdf2Code(DictionaryManager.getInstance("inspection-level", oid).getLabelProperty("DEFAULT", ""));
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
						<option value="<%=inspectionLevelCode%>" <%if (poLine.getUdf2Code().equalsIgnoreCase(inspectionLevelCode)) {%>SELECTED<%}%>><%=inspectionLevelName%></value>
<%	}	%>
						</select>
					</td>
<%	} else { %>
					<tsa:td field="po-LN02" align="right" noWrap="nowrap">
					<% if (DictionaryManager.isLink(oid, "OrderLineItem-LineUDF02")) { %>
								<% if (fdcsEnabled) { %>
									<a href="javascript: browseFdcsWOElements('FdcWo_PoLine_udf2Code'); void(0);" title="Click here to choose the value for <tsa:label labelName="po-LN02" defaultString="Line UDF 2" /> for this item or enter the value in the box on the right."><tsa:label labelName="po-LN02" defaultString="Line UDF 2" checkRequired="true" /></a>:
								<% } else { %>
								<% if (orgReqType.equalsIgnoreCase("M")) { %>
									<tsa:label labelName="po-LN02" defaultString="Line UDF 2" checkRequired="true" />:
								<%} else { %>
									<a href="javascript: browseStd('PoLine_udf2Code', 'LN02'); void(0);" title="Click here to choose the value for <tsa:label labelName="po-LN02" defaultString="Line UDF 2" /> for this item or enter the value in the box on the right."><tsa:label labelName="po-LN02" defaultString="Line UDF 2" checkRequired="true" /></a>:
								<% }
								}%>
					<% } else { %>
						<tsa:label labelName="po-LN02" defaultString="Line UDF 2" checkRequired="true" />:
					<% } %>
					</tsa:td>
					<tsa:td field="po-LN02"><tsa:input type="mintext" name="PoLine_udf2Code" tabIndex="37" maxLength="15" value="<%=poLine.getUdf2Code()%>" readOnly="<%=readonly%>" disabled="<%=disabled%>" onchange="upperCase(this); updated();" /></tsa:td>
<%	}	%>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "po-commodityName")%> align=right nowrap><tsa:label labelName="po-commodityName" defaultString="Commodity Name" />:</a>&nbsp;</td>
					<%if (oid.equalsIgnoreCase("dtn07p")){%>
					<td <%=HtmlWriter.isVisible(oid, "po-commodityName")%> colspan="3">
					<tsa:input type="text" id="as_commodityName" name="as_commodityName" size="70" value="<%=CommodityManager.getInstance().getCommodityDescription(oid, poLine.getCommodity())%>" onfocus="this.blur();" disabled="true" /></td>
					<% }else{%>
					<td <%=HtmlWriter.isVisible(oid, "po-commodityName")%>>
					<tsa:input type="text" id="as_commodityName" name="as_commodityName" size="25" value="<%=CommodityManager.getInstance().getCommodityDescription(oid, poLine.getCommodity())%>" onfocus="this.blur();" disabled="true" /></td>
					<%}%>
					<td <%=HtmlWriter.isVisible(oid, "po-LN03")%> align=right nowrap>
					<% if (DictionaryManager.isLink(oid, "po-LN03")) { %>
								<% if (fdcsEnabled) { %>
									<a href="javascript: browseFdcsWOElements('FdcWo_PoLine_udf3Code'); void(0);" title="Click here to choose the value for <tsa:label labelName="po-LN03" defaultString="Line UDF 3" /> for this item or enter the value in the box on the right."><tsa:label labelName="po-LN03" defaultString="Line UDF 3" checkRequired="true" /></a>:
								<% } else { %>
									<% if(!orgReqType.equalsIgnoreCase("M")) { %>
									<a href="javascript: browseStdUdf3(); void(0);" title="Click here to choose the value for <tsa:label labelName="po-LN03" defaultString="Line UDF 3" /> for this item or enter the value in the box on the right."><tsa:label labelName="po-LN03" defaultString="Line UDF 3" checkRequired="true" /></a>:
									<%} else { %>
									<tsa:label labelName="po-LN03" defaultString="Line UDF 3" checkRequired="true" />:&nbsp;
								<% }
								}%>
					<% } else { %>
						<tsa:label labelName="po-LN03" defaultString="Line UDF 3" checkRequired="true" />:
					<% } %>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "po-LN03")%>><tsa:input type="mintext" name="PoLine_udf3Code" tabIndex="39" maxLength="15" value="<%=poLine.getUdf3Code()%>" readOnly="<%=readonly%>" disabled="<%=disabled%>" onchange="upperCase(this); updated();" /></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-InventoryLocation")%> align=right nowrap><a href="javascript: browseLookup('PoLine_itemLocation', 'item_location'); void(0);" title="Click here to choose the value for <tsa:label labelName="OrderLineItem-InventoryLocation" defaultString="Inventory Location" /> for this item or enter the value in the box on the right."><tsa:label labelName="OrderLineItem-InventoryLocation" defaultString="Inventory Location" checkRequired="true" />:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-InventoryLocation")%>><tsa:input type="text" name="PoLine_itemLocation" tabIndex="19" size="25" maxLength="15" value="<%=poLine.getItemLocation()%>" onchange="upperCase(this); updated();" /></td>

					<td <%=HtmlWriter.isVisible(oid, "po-LN04")%> align=right nowrap>
								<% if (fdcsEnabled) { %>
									<a href="javascript: browseFdcsWOElements('FdcWo_PoLine_udf4Code'); void(0);" title="Click here to choose the value for <tsa:label labelName="po-LN04" defaultString="Line UDF 4" /> for this item or enter the value in the box on the right."><tsa:label labelName="po-LN04" defaultString="Line UDF 4" checkRequired="true" /></a>:</td>
								<% } else { %>
									<a href="javascript: browseStd('PoLine_udf4Code', 'LN04'); void(0);" title="Click here to choose the value for <tsa:label labelName="po-LN04" defaultString="Line UDF 4" /> for this item or enter the value in the box on the right."><tsa:label labelName="po-LN04" defaultString="Line UDF 4" checkRequired="true" /></a>:</td>
								<% } %>
					<td <%=HtmlWriter.isVisible(oid, "po-LN04")%>><tsa:input type="mintext" name="PoLine_udf4Code" tabIndex="41" maxLength="15" value="<%=poLine.getUdf4Code()%>" onchange="upperCase(this); updated();" /></td>
				</tr>
				<tr>

					<td <%=HtmlWriter.isVisible(oid, "po-LN06")%> align=right nowrap>
					<div id="po-LN06">
					<% if(oid.equals("TTR09P")){ %>
						<tsa:label labelName="po-LN06" defaultString="Line UDF6" checkRequired="true" />:&nbsp;
					<%	}else{ %>
						<% if (!orgReqType.equalsIgnoreCase("M")) { %>
					<a href="javascript: browseUdf6Code(); void(0);" title="Click here to choose the value for <tsa:label labelName="po-LN06" defaultString="Line UDF6" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="po-LN06" defaultString="Line UDF6" checkRequired="true" /></a>:&nbsp;
						<%} else { %>
						<tsa:label labelName="po-LN06" defaultString="Line UDF6" checkRequired="true" />
					<%	}
					}%>
					</div>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "po-LN06")%>>
					<div id="po-LN06-fld">
					<% if(oid.equals("TTR09P")){ %>
						<select name="PoLine_udf6Code" tabIndex="17" style="width: 115px" value="<%=poLine.getUdf1Code()%>" onchange="upperCase(this);updated();">
							<option value=""></option>
          					<% for (int il = 0; il < chargeCodeList.size(); il++) {
								StdTable stdTable = (StdTable) chargeCodeList.get(il);
								StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
								<option value="<%=stdTablePK.getTableKey()%>" <%if (poLine.getUdf10Code().equals(stdTablePK.getTableKey())) {%>selected<%}%>><%=stdTable.getTableKey()%></option>
          					<% } %>
          				</select>
					<%	}else{ %>
						<input type=text name="PoLine_udf6Code" tabindex=17 size=15 maxlength=15 value="<%=poLine.getUdf6Code()%>" readOnly="<%=readonly%>" disabled="<%=disabled%>" onchange="upperCase(this); updated();" <%=HtmlWriter.isReadOnly(oid, "po-LN06") %>>
					<%	}	%>
					</div>
					</td>

				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "po-receiptOptions")%> align=right nowrap><tsa:label labelName="po-receiptOptions" defaultString="Receipt Options" checkRequired="true" />:</td>
					<td <%=HtmlWriter.isVisible(oid, "po-receiptOptions")%>>
						<select tabindex="21" name="PoLine_receiptRequired" size=1>
<%	if (propertiesManager.getProperty("REC SELECTIONS", "X", "Y").equals("Y")) {%>
							<option <% if (s_receipt_required.equals("X") || HiltonUtility.isEmpty(s_receipt_required)) {%> SELECTED <%}%> value=""> - Select an Option - </option>
<%	}
		if (propertiesManager.getProperty("REC SELECTIONS", "R", "Y").equals("Y")) {%>
							<option <% if (s_receipt_required.equals("R")) {%> SELECTED <%}%> value="R"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiptrequired", "Receipt Required", false)%></option>
<%	}
		if (propertiesManager.getProperty("REC SELECTIONS", "P", "Y").equals("Y")) {%>
							<option <% if (s_receipt_required.equals("P")) {%> SELECTED <%}%> value="P"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "previouslyreceived", "Previously Received", false)%></option>
<%	}
		if (propertiesManager.getProperty("REC SELECTIONS", "N", "Y").equals("Y")) {%>
							<option <% if (s_receipt_required.equals("N")) {%> SELECTED <%}%> value="N"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noreceiptrequired", "No Receipt Required", false)%></option>
<%	}
		if (propertiesManager.getProperty("REC SELECTIONS", "E", "Y").equals("Y")) {%>
							<option <% if (s_receipt_required.equals("E")) {%> SELECTED <%}%> value="E"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enduserreceipt", "End User Receipt", false)%></option>
<%	}%>
						</select>
					</td>

				<%if (!oid.equalsIgnoreCase("vse06p")) { %>
					<% if (oid.equalsIgnoreCase("bly07p")) { %>
					<td <%=HtmlWriter.isVisible(oid, "po-LN05")%> align=right nowrap><div id="po-LN05"><a href="javascript: browseWarehouse('PoLine_udf5Code'); void(0);" title="Click here to choose the value for <tsa:label labelName="po-LN05" defaultString="Line UDF 5" />"><tsa:label labelName="po-LN05" defaultString="Line UDF 5" checkRequired="true" /></a>:</div></td>
					<% } else { %>
						<%if (!orgReqType.equalsIgnoreCase("M")){ %>
					<tsa:td field="po-LN05" align="right" noWrap="nowrap"><a href="javascript: browseStd('PoLine_udf5Code', 'LN05'); void(0);" title="Click here to choose the value for <tsa:label labelName="po-LN05" defaultString="Line UDF 5" /> for this item or enter the value in the box on the right."><tsa:label labelName="po-LN05" defaultString="Line UDF 5" checkRequired="true" /></a>:</tsa:td>
					<%} else { %>
					<tsa:td field="po-LN05" align="right" noWrap="nowrap"><tsa:label labelName="po-LN05" defaultString="UDF 5" checkRequired="true"/>:&nbsp;</tsa:td>
					<% }
					}%>
		          	<tsa:td field="po-LN05" noWrap="nowrap"><div id="po-LN05-fld"><tsa:input type="mintext" name="PoLine_udf5Code" tabIndex="43" maxLength="15" value="<%=poLine.getUdf5Code()%>" readOnly="<%=readonly%>" disabled="<%=disabled%>" onchange="upperCase(this); updated();" /></div></tsa:td>
				<% } %>
				</tr>
		        <tr>
		          <td <%=HtmlWriter.isVisible(oid, "ln-po-requisitioner")%> align=right nowrap><a href="javascript: browseRequisitioner(); void(0);" title="Click here to choose the <tsa:label labelName="po-requisitioner" defaultString="Requisitioner" /> for this order or enter the <tsa:label labelName="po-requisitioner" defaultString="Requisitioner" /> in the box on the right."><tsa:label labelName="po-requisitioner" defaultString="Requisitioner" checkRequired="true" />:</a></td>
		          <td <%=HtmlWriter.isVisible(oid, "ln-po-requisitioner")%>><tsa:input type="text" name="PoLine_requisitionerCode" tabIndex="23" size="25" maxLength="20" value="<%=poLine.getRequisitionerCode()%>" onchange="upperCase(this); updated(); getNewInfo('requisitioner', this);" /></td>
		          <td <%=HtmlWriter.isVisible(oid, "po-ln-requisitionNumber")%> align=right nowrap><tsa:label labelName="po-ln-requisitionNumber" defaultString="Requisition #" />:</td>
		          <td <%=HtmlWriter.isVisible(oid, "po-ln-requisitionNumber")%> nowrap><input type=text name="PoLine_requisitionNumber" tabindex="45" size=15 maxlength="20" value="<%=headerEncoder.encodeForHTMLAttribute(poLine.getRequisitionNumber())%>" onchange="upperCase(this); updated();" <%=HtmlWriter.isReadOnly(oid, "po-ln-requisitionNumber") %>></td>
		        </tr>
		        <tr>
		          <td <%=HtmlWriter.isVisible(oid, "ln-po-requisitionerName")%> align=right nowrap><tsa:label labelName="po-requisitionerName" defaultString="Requisitioner Name" />:</td>
		          <td <%=HtmlWriter.isVisible(oid, "ln-po-requisitionerName")%> nowrap><tsa:input type="text" name="as_requisitionerName" size="25" value="<%=UserManager.getInstance().getUser(oid, poLine.getRequisitionerCode()).getDisplayName()%>" onfocus="this.blur();" disabled="true" /></td>
		          <% if (DictionaryManager.isLink(oid, "po-ln-department")) { %>
		          <td <%=HtmlWriter.isVisible(oid, "po-ln-department")%> align=right nowrap><a href="javascript: browseLookup('PoLine_departmentCode', 'department'); void(0);" title="Click here to choose the <tsa:label labelName="po-ln-department" defaultString="Department" /> for this order or enter the <tsa:label labelName="po-ln-department" defaultString="Department" /> in the box on the right."><tsa:label labelName="po-ln-department" defaultString="Department" checkRequired="true" />:</a></td>
		          <td <%=HtmlWriter.isVisible(oid, "po-ln-department")%>><input type=text name="PoLine_departmentCode" tabindex="47" size=15 maxlength=20 value="<%=poLine.getDepartmentCode()%>" onchange="upperCase(this); updated();" <%=HtmlWriter.isReadOnly(oid, "po-ln-department") %>></td>
		          <% } else { %>
		          <td <%=HtmlWriter.isVisible(oid, "po-ln-department")%> align=right nowrap><tsa:label labelName="po-ln-department" defaultString="Department" checkRequired="true" />:</td>
		          <td <%=HtmlWriter.isVisible(oid, "po-ln-department")%>><input type=text name="PoLine_departmentCode" tabindex="47" size=15 maxlength=20 value="<%=poLine.getDepartmentCode()%>" onchange="upperCase(this); updated();" <%=HtmlWriter.isReadOnly(oid, "po-ln-department") %>></td>
		          <% } %>
		          <tsa:td field="po-LN08" align="right" noWrap="nowrap"><tsa:label labelName="po-LN08" defaultString="UDF 8" checkRequired="true"/>:&nbsp;</tsa:td>
		          <tsa:td field="po-LN08" noWrap="nowrap"><tsa:input type="dropdown" name="PoLine_udf8Code" value="<%=poLine.getUdf8Code()%>" labelName="po-LN08"/></tsa:td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "po-ln-supplier")%>>
		          <td <%=HtmlWriter.isVisible(oid, "po-ln-supplier")%> align=right nowrap><a href="javascript: browseSupplier('PoLine_vendorId'); void(0);" title="Click here to choose the <tsa:label labelName="po-ln-supplier" defaultString="Supplier" /> for this order or enter the <tsa:label labelName="po-ln-supplier" defaultString="Supplier" /> in the box on the right."><tsa:label labelName="po-ln-supplier" defaultString="Supplier" checkRequired="true" />:</a></td>
		          <td <%=HtmlWriter.isVisible(oid, "po-ln-supplier")%>><tsa:input type="text" name="PoLine_vendorId" tabIndex="23" size="25" maxLength="20" value="<%=poLine.getVendorId()%>" onchange="upperCase(this); updated(); getNewInfo('vendor', this);" disabled="true" /></td>
		          <td>&nbsp;</td>
		          <td>&nbsp;</td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "po-ln-vendorName")%>>
		          <td <%=HtmlWriter.isVisible(oid, "po-ln-vendorName")%> align=right nowrap><tsa:label labelName="po-ln-vendorName" defaultString="Vendor Name" />:</td>
		          <td <%=HtmlWriter.isVisible(oid, "po-ln-vendorName")%> nowrap><tsa:input type="text" name="PoLine_vendorName" size="25" value="<%=poLine.getVendorName()%>" onfocus="this.blur();" disabled="true" /></td>
		          <td>&nbsp;</td>
		          <td>&nbsp;</td>
		        </tr>
		        <tr>
		          <td <%=HtmlWriter.isVisible(oid, "po-ln-requiredDate")%> align=right nowrap><tsa:label labelName="po-ln-requiredDate" defaultString="Required Date" />:</td>
		          <td <%=HtmlWriter.isVisible(oid, "po-ln-requiredDate")%>>
					<tsa:input type="mintext" name="PoLine_requiredDate" tabIndex="25" maxLength="15" value="<%=s_required_date%>" readOnly="<%=readonly%>" disabled="<%=disabled%>"/>
					<a href="javascript: show_calendar('PoLine_requiredDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
		          </td>

		        </tr>
		        <tr>
              		<td <%=HtmlWriter.isVisible(oid, "ln-po-poPromised")%> align=right nowrap><tsa:label labelName="ln-po-poPromised" defaultString="Promised Date" />:&nbsp;</td>
              		<td <%=HtmlWriter.isVisible(oid, "ln-po-poPromised")%>>
              			<tsa:input type="mintext" name="PoLine_poPromised" tabIndex="13" maxLength="15" value="<%=HiltonUtility.getFormattedDate(poLine.getPoPromised(), oid, userDateFormat)%>" />
			  			<a href="javascript: show_calendar('PoLine_poPromised', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
		          	</td>
		          	<% if (oid.equalsIgnoreCase("bly07p")){
					%>
					<td <%=HtmlWriter.isVisible(oid, "po-LN09")%> align=right nowrap><tsa:label labelName="PO09" defaultString="Line UDF9" checkRequired="true" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "po-LN09")%>><tsa:input type="mintext" name="PoLine_udf9Code" tabIndex="17" maxLength="15" value="<%=poLine.getUdf9Code()%>" onchange="upperCase(this); updated();" /></td>
				  <% } else { %>
				  	<td>&nbsp;</td>
		          	<td>&nbsp;</td>
				  <% } %>
		        </tr>
		        <tr>
					<tsa:td id="poLn07LabelRow" field="po-LN07" align="right" noWrap="nowrap" >
					<tsa:label labelName="po-LN07" defaultString="Line UDF07" checkRequired="true" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td id="poLn07FieldRow" field="po-LN07" >
						<tsa:input type="dropdown" tabIndex="23" name="PoLine_udf7Code" id="PoLine_udf7Code" size="1" value="<%=poLine.getUdf7Code()%>" labelName="po-LN07" readOnly="<%=readonly%>" disabled="<%=disabled%>"/>
			        </tsa:td>
				</tr>
		        <%	if (oid.equalsIgnoreCase("vse06p")) { %>
				<tr>
					<td colspan="2"><table align="center">
					<tr>
					<td><input type="radio" name="udf10Code" value="M" onclick="setUdf10();" <% if(s_udf10Code.equalsIgnoreCase("M")) { %>checked<% } %>>Materials</td>
					<td><input type="radio" name="udf10Code" value="S" onclick="setUdf10();" <% if(s_udf10Code.equalsIgnoreCase("S")) { %>checked<% } %>>Services</td>
					<td><input type="radio" name="udf10Code" value="O" onclick="setUdf10();" <% if(s_udf10Code.equalsIgnoreCase("O")) { %>checked<% } %>>Other</td>
					</tr>
					<tr><td><tsa:hidden name="PoLine_udf10Code" value="<%=poLine.getUdf10Code()%>"/>&nbsp;</td></tr>
					</table></td>
				</tr>
				<%	} %>
			<% if (oid.equals("SRR10P")) { %>
				 <tr>
					<td width=175px <%=HtmlWriter.isVisible(oid, "po-asset")%> nowrap align=right><tsa:label labelName="po-asset" defaultString="Asset" />:&nbsp;</td>
					<tsa:td field="req-asset" >
					<tsa:input type="dropdown" name="PoLine_asset" tabIndex="3" maxLength="15" value="<%=poLine.getAsset()%>" labelName="po-Asset" onchange="upperCase(this); updated();"/>
					</tsa:td>
				</tr>
			<% } %>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-ShelfLifeRequired")%> nowrap align=right valign=middle>
						<tsa:label labelName="OrderLineItem-ShelfLifeRequired" defaultString="Shelf Life Required" />:&nbsp;
					</td>
					<td <%=HtmlWriter.isVisible(oid, "OrderLineItem-ShelfLifeRequired")%>>
						<input type=checkbox name="c_checkbox" tabindex="11" <% if (s_shelfLife.equals("Y")) { %> CHECKED <% } %> value="Y" <% if (shelfLife) { %> onclick="setCheckbox(PoLine_shelfLifeRqd, 3); updated();" <% } else { %>onclick="setCheckbox(PoLine_shelfLifeRqd, 2); updated();" <%}%>>
						<tsa:hidden name="PoLine_shelfLifeRqd" value="<%=s_shelfLife%>"/>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-chemicalnumberln")%>>
					<td <%=HtmlWriter.isVisible(oid, "po-chemicalnumberln")%> align=right nowrap>
						<tsa:label labelName="po-chemicalnumberln" defaultString="Chemical item Number" checkRequired="true" />:&nbsp;
					</td>
					<td <%=HtmlWriter.isVisible(oid, "po-chemicalnumberln")%>><input type=text name="PoLine_chemicalItemNumber" tabindex=12 size=15 maxlength=30 value="<%=poLine.getChemicalItemNumber()%>" onchange="upperCase(this); updated();"></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
	<td align=right width=25% valign=top>
<%
	int linecount = Integer.valueOf(s_line_count).intValue();
	if (linecount > 1)
	{ %>
		View Item:
<%	for (i = 1; i <= linecount; i++)
		{
			if (i == bd_line_number.intValue())
			{ %>
				&nbsp;<%=i%>
<%		}
			else
			{ %>
				&nbsp;<a href="javascript: retrieveLine(<%=i%>);"><%=i%></a>
<%		}
		}
	} %>
		<br>

		<table border=1 cellpadding=0 cellspacing=0>
		<tr><td class=browseHdr>Item Options</td></tr>
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=220px>

<%	if (role.getAccessRights("PO") >1 && s_po_status.compareTo(DocumentStatus.PO_INPROGRESS) == 0 && bd_revision_number.compareTo(poLine.getLineRevisionNumber()) == 0 )
		{
			if (!oid.equalsIgnoreCase("akdoc") || user.getUserId().equalsIgnoreCase(s_buyer_code))
			{
%>
			<% if (!(oid.equalsIgnoreCase("bly07p") && bd_revision_number.compareTo(new BigDecimal(0)) > 0)) { %>
				<tr height=15px>
					<td colspan=3 nowrap align=center width=10%>
<%		if (s_view.equals("WIZARD")) { %>
						<a href="javascript: doSubmit('<%=s_return_page%>', 'PoLineDelete;<%=s_return_method%>');" ONCLICK="if ( verifyAction('Delete this item?') ) { return totalsAlert(); } else { return false; }">
<%		} else { %>
						<a href="javascript: doSubmit('<%=s_return_page%>', 'PoLineDelete;PoRetrieve');" ONCLICK="if ( verifyAction('Delete this item?') ) { return totalsAlert(); } else { return false; }">
<%		} %>
							<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0>&nbsp;<tsa:label labelName="deleteItem" defaultString="Delete Item" />
						</a>
					</td>
				</tr>
			<% } %>
<%		}
		}
		if (role.getAccessRights("PO") > 1) {
			String compStatus = DocumentStatus.PO_SHIPPED ;
			if (s_budgetActive.equalsIgnoreCase("Y")) {
				compStatus = DocumentStatus.PO_AWARDED ;
			}
			if (s_po_status.compareTo(compStatus) < 0 && (s_po_status.compareTo(DocumentStatus.PO_INPROGRESS) != 0 || bd_revision_number.compareTo(bd_zero) > 0) ) { %>
				<% if (oid.equalsIgnoreCase("bly07p")) { %>
				<% if (bd_revision_number.compareTo(new BigDecimal(0)) > 0 && s_po_status.compareTo(DocumentStatus.PO_INPROGRESS) == 0) { %>
				<tr height=15px>
					<td colspan=3 nowrap align=center width=10%>
						<a href="javascript: frm.poaction.value = 'cancel'; doSubmit('<%=s_return_page%>', 'PoLineCancel;<%=s_return_method%>');" ONCLICK="return verifyAction('Cancel line <%=bd_line_number%>?');">Cancel Item
						</a>
					</td>
				</tr>
				<% } %>
				<% } else { %>
				<tr height=15px>
					<td colspan=3 nowrap align=center width=10%>
						<a href="javascript: frm.poaction.value = 'cancel'; doSubmit('<%=s_return_page%>', 'PoLineCancel;<%=s_return_method%>');" ONCLICK="return verifyAction('Cancel line <%=bd_line_number%>?');">Cancel Item
						</a>
					</td>
				</tr>
				<% } %>
				<tr height=15px>
					<td colspan=3 nowrap align=center width=10%>
						<a href="javascript: frm.poaction.value = 'close'; doSubmit('<%=s_return_page%>', 'PoLineCancel;<%=s_return_method%>');" ONCLICK="return verifyAction('Close line <%=bd_line_number%>?');">Close Item
						</a>
					</td>
				</tr>
<%		}
		}
		BigDecimal iclinekey = poLine.getIcLineKey();
		String iclinekeyString = (iclinekey != null ? String.valueOf(iclinekey): "");
		if (!s_po_number.equalsIgnoreCase("N/A")) { %>
				<tr height=15px>
					<td colspan="3" nowrap align=center width=10%>&nbsp;&nbsp;<A HREF="javascript: viewItemHistory(); void(0);">Item History</A></TD>

				</tr>
				<tr height=15px <%=HtmlWriter.isVisible(oid, "po-itemdescription-alttext")%> >
					<td nowrap align=center>&nbsp;<a href="javascript: viewAltLanguages(); void(0);"><%=DictionaryManager.getLabel(oid, "po-itemdescription-alttext", "Alternate Language Descriptions", true)%></a>&nbsp;</td>
				</tr>
<%		if (assetsActive && s_asset.equalsIgnoreCase("Y") && Integer.valueOf(s_po_status).intValue() >= 3030 )
			{ %>
				<tr height=17px>
					<td colspan="3" nowrap align=center width=10%>
					&nbsp;&nbsp;<a href="javascript: viewAssetRelated('<%= headerEncoder.encodeForJavaScript(iclinekeyString) %>'); ">Asset</a>
				</td>
				</tr>
<%		}
		}
		if (s_po_type.equalsIgnoreCase("DR")) { %>
				<tr height=15px>
					<td colspan="3" nowrap align=center width=10%>&nbsp;&nbsp;<A HREF="javascript: viewDeliveryInfo(); void(0);">Delivery Info</A></TD>
				</tr>
<%	} %>
				</table>
<%	if (s_po_status.compareTo(DocumentStatus.PO_REJECTED) <= 0 && role.getAccessRights("PO") > 1) {
			if (!oid.equalsIgnoreCase("akdoc") || user.isAnAdminBuyer()) { %>
				<table border=0 cellpadding=0 cellspacing=0 width=225px>
			<%	if (propertiesManager.getProperty("PO OPTIONS", "RestrictNonStandardItem", "N").equalsIgnoreCase("N")) { %>
				<tr height=15px>
					<td nowrap align=center>
						<table border=0 cellpadding=0 cellspacing=0>
						<tr>
							<td align=right><a href="javascript: addItem(); void(0);"><img src="<%=contextPath%>/images/cmd_add_item.gif" border=0 alt="<tsa:label labelName="addnonstditem" defaultString="Add Non-Standard Item" />"></a></td>
							<td>&nbsp;<a href="javascript: addItem(); void(0);"><tsa:label labelName="addNonStandardItem" defaultString="Add Non-Standard Item" /></a></td>
						</tr>
						</table>
					</td>
				</tr>
			<%	}%>
				</table>

				<table width=100%>
				<tr height=15px>
					<td nowrap align=center>&nbsp;<a href="javascript: toggleDetailsDisplay('addItem', 'addItemOptions'); void(0);">Item Search Options</a></td>
				</tr>
				</table>

				<div id="addItem" name="addItem" style="display:none;">
				<table border=0 width=225px cellpadding=1>
				<tr>
					<td align=right>
						<tsa:hidden name="createAction" value="SAVE"/>
						<table border=0 cellspacing=0 cellpadding=1>
						<tr <%=HtmlWriter.isVisible(oid, "po-itemNumber")%>>
							<td valign=middle align=right nowrap><b><tsa:label labelName="po-itemNumber" defaultString="Item/Part #" />:</b></td>
							<td valign=middle><tsa:input type="mintext" name="as_itemNumber" value="" tabIndex="20" /></td>
						</tr>
						<tr>
							<td valign=middle align=right nowrap><b>Keyword(s):</b></td>
							<td valign=middle><tsa:input type="text" name="as_keywords" value="" size="17" tabIndex="21" /></td>
							<td valign=middle><div id="pxmediumbutton"><a href="javascript: itemSearch(); void(0);">Search</a></div></td>
						</tr>
						</table>
<%	if (propertiesManager.isModuleActive("Extended Inventory") || propertiesManager.isModuleActive("Standard Inventory")) {%>
						<div id="itemtype" style="visibility:visible; display:block;">
						<table border=0 cellspacing=0 cellpadding=1>
						<tr>
							<td valign=middle align=right><tsa:input type="radio" name="as_item_type" tabIndex="23" value="CAT" checked="true" /></td>
							<td valign=middle nowrap>Catalog</td>
							<td valign=middle align=right><tsa:input type="radio" name="as_item_type" tabIndex="24" value="INV" /></td>
							<td valign=middle nowrap>Inventory</td>
						</tr>
						</table>
						</div>
<%	} else {%>
						<tsa:hidden name="as_item_type" value="CAT"/>
<%	}%>
					</td>
				</tr>
				<tr><td height=1px id="filterFields"></td></tr>
				<tr>
					<td align=center>
						<table border=0 cellpadding=0 cellspacing=0>
						<tr>
							<td valign="middle"><a href="javascript: doSubmit('/browse/item_filter_options.jsp', 'CatalogRetrieveActive'); void(0);"><img src="<%=contextPath%>/images/cmd_search_item.gif" alt="Advanced Item Search" border=0></a></td>
							<td valign=middle>&nbsp;<a href="javascript: doSubmit('/browse/item_filter_options.jsp', 'CatalogRetrieveActive'); void(0);">Advanced Item Search</a></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
				</div>

<% if( !poLine.getCatalogId().equalsIgnoreCase("") && oid.equalsIgnoreCase("BLY07P")) {%>
				<table border=0 cellpadding=0 cellspacing=0 width=60% align=center>
				<tr height=15px>
					<td><a href="javascript: doSubmit('/orders/po_item.jsp', 'PoLineUpdate;CatalogItemUpdateFromPoLine'); void(0);"><img src="<%=contextPath%>/images/save.gif" alt="Update Vendors Price" border=0></a></td>
					<td><a href="javascript: doSubmit('/orders/po_item.jsp', 'PoLineUpdate;CatalogItemUpdateFromPoLine'); void(0);">Update Catalog Item</a></td>
				</tr>
				</table>
<% } %>

<!--
				<table width=100%>
				<tr height=15px>
					<td nowrap align=center>&nbsp;<a href="javascript: toggleDetailsDisplay('addItem', 'addItemOptions'); void(0);">Add Item</a></TD>
				</tr>
				</table>

				<div id="addItem" style="display:none;">
				<table border=0 width=220px>
				<tr>
					<td align=right>
						<table border=0 cellspacing=0 cellpadding=2>
						<tr>
							<td colspan=3 nowrap align=center width=10%>&nbsp;</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "po-itemNumber")%>>
							<td valign=middle align=right nowrap><b><tsa:label labelName="po-itemNumber" defaultString="Item/Part #" />:</b></td>
							<td valign=middle align=center><input type=text name=itemNumber value="" size=12></td>
							<td valign=middle><a href="javascript: addItem(); void(0);"><IMG SRC="<%=contextPath%>/images/cmd_add_item.gif" border="0" ALT="Add Item"></a></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align=right>
						<table border=0 cellspacing=0 cellpadding=2>
						<tr>
							<td valign=middle align=right nowrap><b>Keyword(s):</b></td>
							<td valign=middle align=center><input type=text name="as_keywords" value="" size=18></td>
							<td valign=middle><a href="javascript: itemSearch(); void(0);"><IMG SRC="<%=contextPath%>/images/cmd_search_item.gif" border="0" ALT="Item Search"></a></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align=right>
						<table border=0 cellspacing=0 cellpadding=2>
						<tr>
							<td valign=middle align=right><input type=radio name="as_item_type" tabindex=10 value="CAT" CHECKED></td>
							<td valign=middle nowrap>Catalog</td>
							<td valign=middle align=right><input type=radio name="as_item_type" tabindex=12 value="INV"></td>
							<td valign=middle nowrap>Inventory</td>
							<td  id="filterFields"></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
-->
<%		}
		} %>

		<% if (poLine.getIcReqLine()== null || poLine.getIcReqLine().compareTo(new BigDecimal(0)) == 0 )
		   {
			   if ( s_po_status.compareTo(DocumentStatus.CANCELLED) != 0 )
			   {
	    %>
			<table width=100% <%=HtmlWriter.isVisible(oid, "po-linkrequisition")%>>
				<tr height=15px>
					<td nowrap align=center>&nbsp;<a href="javascript: browseLookup('PoLine_requisitionNumber', 'requisitionline-for-assignment'); void(0);">Link Requisition</a></td>
				</tr>
			</table>

		<%      }
		}%>

		</table>
	</td>
</tr>
</table>
<%
if (oid.equalsIgnoreCase("vse06p"))
{
%>
<br>
<table <%=HtmlWriter.isVisible(oid, "po-bav-details")%> border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<td>
	&nbsp;&nbsp;<a href="javascript: toggleDetailsDisplay('bavDetailInfo', 'bavDetailOptions'); void(0);" tabIndex="-1">BAV (Only) Details</a>
</td>
</tr>
</table>

<div id="bavDetailInfo" name="bavDetailInfo" style="visibility:hidden; display:none">
<br>
<table <%=HtmlWriter.isVisible(oid, "po-additional-info")%> border=0 cellpadding=0 cellspacing=0 width=680px>
<!--
<tr>
	<td align=center width=680px>
		<table border=0 cellspacing=0 cellpadding=0 width=665px>
		<tr>
		<td>
				<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=68% height=18px class=browseHdr>&nbsp;<b>BAV Details (Only)</b></td>
				</tr>
				</table>
		</td>
		</tr>
		</table>
	</td>
</tr>
-->
<tr>
	<td align=center width=680px>
		<table border=0 cellspacing=0 cellpadding=0 width=665px>
		<tr>
		<td>
			<table border="0" cellpadding="0" cellspacing="2" width="50%">
				<tr <%=HtmlWriter.isVisible(oid, "po-LNMEMO")%>>
					<td nowrap align=left valign=top><tsa:label labelName="po-LNMEMO" defaultString="Notes" checkRequired="true" />:</td>
				</tr>

				<tr <%=HtmlWriter.isVisible(oid, "po-LNMEMO")%>>
					<td colspan=4><textarea wrap="virtual" name="PoLine_memoLine" tabindex="48" rows=4 cols=60 onKeyDown="textCounter(this, 255);" onKeyUp="textCounter(this,255);" onchange="upperCase(this); textCounter(this,255); updated();"><%=HiltonUtility.encodeHtml(poLine.getMemoLine())%></textarea></td>
				</tr>
			</table>
		</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="2" width="50%">
			<tr>
					<td <%=HtmlWriter.isVisible(oid, "po-LN06")%> align=right nowrap>
					<% if (DictionaryManager.isLink(oid, "po-LN06")) { %>
						<a href="javascript: browseStd('PoLine_udf6Code', 'LN06'); void(0);" title="Click here to choose the value for <tsa:label labelName="po-LN06" defaultString="Line UDF 6" /> for this item or enter the value in the box on the right."><tsa:label labelName="po-LN06" defaultString="Line UDF 6" checkRequired="true" /></a>:
					<% } else { %>
						<tsa:label labelName="po-LN06" defaultString="Line UDF 6" checkRequired="true" />:
					<% } %>
					</td>

					<td <%=HtmlWriter.isVisible(oid, "po-LN06")%>>
						<select tabindex="49" name="PoLine_udf6Code" size=1>
							<option <% if (s_udf6.equals("N")) {%> SELECTED <%}%> value=""></option>
							<option <% if (s_udf6.equals("O")) { %> selected <% } %> value="O">OUTFITTING</option>
							<option <% if (s_udf6.equals("I")) { %> selected <% } %> value="I">INDUSTRIAL</option>
						</select>
					</td>
			</tr>
			<tr>
					<tsa:td field="po-LN07" align="right" noWrap="nowrap">
					<% if (DictionaryManager.isLink(oid, "po-LN07")) { %>
						<a href="javascript: browseStd('PoLine_udf7Code', 'LN07'); void(0);" title="Click here to choose the value for <tsa:label labelName="po-LN07" defaultString="Line UDF 7" /> for this item or enter the value in the box on the right."><tsa:label labelName="po-LN07" defaultString="Line UDF 7" checkRequired="true" /></a>:
					<% } else { %>
						<tsa:label labelName="po-LN07" defaultString="Line UDF 7" checkRequired="true" />:
					<% } %>
					</tsa:td>
					<td><tsa:input type="mintext" name="PoLine_udf7Code" tabIndex="50" maxLength="15" value="<%=poLine.getUdf7Code()%>" labelName="po-LN07" onchange="upperCase(this); updated();" /></td>		</tr>
			</tr>
			</table>
		</td>
		</table>
	</td>
</tr>
</table>
</div>
<% } %>
<br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<% List shipToList = (List) poLine.getShipToList(); %>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=50% align=center valign=top>
				<table <%=HtmlWriter.isVisible(oid, "po-itemShippingSchedule")%> border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
						<tr>
							<td width=4% class=browseHdr height=18px nowrap><a href ="javascript: if (validateItem()) { doSubmit('/orders/po_shipping_schedule.jsp', 'PoLineUpdate;ShipToRetrieveByLine'); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a>
							<td width=93% class=browseHdr height=18px nowrap>Item Shipping Schedule</td>
							<%	if (shipToList != null && shipToList.size() > 0){%>
							<td width=3% class=browseHdr height=18px nowrap><a href="javascript: toggleDetailsDisplay('shippingDetails', 'Details'); void(0);"><img id='shippingDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;</td>
							<%	}%>
							<tsa:hidden name="ShipTo_icHeader" value="<%=bd_ic_po_header%>"/>
							<tsa:hidden name="ShipTo_icLine" value="<%=bd_ic_po_line%>"/>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
<%
	i_size = 0;
	if (shipToList != null)
	{
		i_size = shipToList.size();
		for (i = 0;i < shipToList.size(); i++)
		{
			ShipTo shipTo = (ShipTo) shipToList.get(i);
			String s_shp_attn = shipTo.getAttention();
			BigDecimal bd_shp_qty = shipTo.getQuantity();
			Date d_shp_date = shipTo.getShipDate();
			String s_shp_date = "";
			if (d_shp_date != null)
			{
				s_shp_date = HiltonUtility.getFormattedDate(d_shp_date, oid, userDateFormat);
			}
			if (bd_shp_qty == null)	{	bd_shp_qty = new BigDecimal(0);	}
			if (s_shp_attn == null)		{	s_shp_attn = "";								}

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
							<td class=browseRow width=70px nowrap><b>QTY:</b>&nbsp;<%=HiltonUtility.getFormattedQuantity(shipTo.getQuantity(), oid)%></td>
							<td class=browseRow width=135px align=right nowrap><b>Required By:</b> <%=s_shp_date%></td>
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
						<tr><td><b>There is no shipping schedule for this item.</b></td></tr>
						</table>
<%}%>
					</td>
				</tr>
				</table>
			</td>
			<%List billToList = (List) poLine.getBillToList();%>
			<td width=50% align=center valign=top>
				<table <%=HtmlWriter.isVisible(oid, "po-itemBillingSchedule")%> border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
							<td width=4% class=browseHdr height=18px nowarap><a href="javascript: if (validateItem()) { doSubmit('/orders/po_billing_schedule.jsp', 'PoLineUpdate;BillToRetrieveByLine'); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></td></a>
							<td width=93% class=browseHdr height=18px nowrap>&nbsp;Item Billing Information</td>
							<%	if (billToList != null && billToList.size() > 0) {%>
							<td width=3% class=browseHdr height=18px nowrap><a href="javascript: toggleDetailsDisplay('billingDetails', 'Details'); void(0);"><img id='billingDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;</td>
							<%	}%>
							<tsa:hidden name="BillTo_icHeader" value="<%=bd_ic_po_header%>"/>
							<tsa:hidden name="BillTo_icLine" value="<%=bd_ic_po_line%>"/>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow id=billingRows>

<%
	i_size = 0;

	if (billToList != null)
	{
		i_size = billToList.size();
		for (i = 0;i < billToList.size(); i++)
		{
			BillTo billTo = (BillTo) billToList.get(i);
			String s_bil_attn = billTo.getAttention();
			BigDecimal bd_bil_qty = billTo.getQuantity();

			if (bd_bil_qty == null)	{	bd_bil_qty = new BigDecimal(0);	}
			if (s_bil_attn == null)	{	s_bil_attn = "";							}

			BillToPK billToPK = billTo.getComp_id();
			String	s_bil_code = billToPK.getBillToCode();
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
							<td class=browseRow width=75px nowrap><b>QTY:</b>&nbsp;<%=HiltonUtility.getFormattedQuantity(billTo.getQuantity(), oid)%></td>
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
						<tr><td><b>There is no billing schedule for this item.</b></td></tr>
						</table>
<%}%>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table  border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=33% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="inspText" defaultString="Inspection" /></b></td>
					<td width=20% height=18px class=browseHdr align=right>&nbsp;<b><tsa:label labelName="inspAssignedTo" defaultString="Assigned To" />.</b></td>
					<td width=20% height=18px class=browseHdr align=right>&nbsp;<b><tsa:label labelName="inspAssignedDate" defaultString="Assigned Date" />.</b></td>
					<td width=20% height=18px class=browseHdr align=right>&nbsp;<b><tsa:label labelName="inspStatus" defaultString="Status" />.</b></td>
					<td width=5% height=18px class=browseHdr>&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>

<%
	if (inspectionList != null)
	{
		for (i = 0;i < inspectionList.size(); i++)
		{
			InspectionHeader ih = (InspectionHeader) inspectionList.get(i) ;

			%>
			<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
			<tr>
				<td width=2%></td>
				<%
				String inspTypeDesc = "Receipt Inspection" ;
				if (ih.getInspectType().equalsIgnoreCase("FI")) inspTypeDesc = "Field Inspection" ;
				if (ih.getInspectType().equalsIgnoreCase("GI")) inspTypeDesc = "General Inspection" ;
				if (ih.getInspectType().equalsIgnoreCase("CG")) inspTypeDesc = "CGD Inspection" ;
				%>
			  <td width=33% class=browseRow id="inspectionLine"><%=inspTypeDesc%></td>
				<td width=20% class=browseRow align=right></td>
				<td width=20% class=browseRow align=right></td>
				<td width=20% class=browseRow align=right><%=DocumentStatus.toString(s_po_status , oid)%></td>
				<td width=5% height=18px class=browseRow>&nbsp;</td>
			</tr>
			</table>
<%	}
	}
	if (inspectionList != null && inspectionList.size() > 0) { %>
<%} else {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td><b><tsa:label labelName="noInspectionsItem" defaultString="There are no inspection records for this item" />.</b></td></tr>
				</table>
<%}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr><td><br></td></tr>
</table>

<table <%=HtmlWriter.isVisible(oid, "po-itemAccounts")%> border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<% if (defaultAccountsByCommodity) { %>
						<td width=2% height=18px class=browseHdr align=center><a href="javascript: if (validateItem()) { doSubmit('/orders/po_accounts_ln.jsp', 'PoLineUpdate;PoLineRetrieveAccount;CommodityRetrieveAccount'); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></td></a>
					<% } else { %>
						<td width=2% height=18px class=browseHdr align=center><a href="javascript: if (validateItem()) { doSubmit('/orders/po_accounts_ln.jsp', 'PoLineUpdate;PoLineRetrieveAccount'); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></td></a>
					<% } if (oid.equalsIgnoreCase("bly07p")){%>
						<td width=68% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="account" defaultString="Account" /></b></td>
					<%}else{%>
						<td width=68% height=18px class=browseHdr>&nbsp;<b>Account</b></td>
					<%} %>
					<td width=15% height=18px class=browseHdr align=right>&nbsp;<b>Percent Alloc.</b></td>
					<td width=15% height=18px class=browseHdr align=right>&nbsp;<b>Amount Alloc.</b></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
	i_size = 0;
	List accountList = (List) poLine.getAccountList();
	BigDecimal bd_total_perc = new BigDecimal(0.00);
	BigDecimal bd_total_amt = new BigDecimal(0.00);

	if (accountList != null)
	{
		i_size = accountList.size();
		for (i = 0; i < accountList.size(); i++)
		{
			Account account = (Account) accountList.get(i);

			BigDecimal bd_alloc_perc = HiltonUtility.getBigDecimalFormatted(account.getAllocPercent(), 2);
			BigDecimal bd_alloc_amt = HiltonUtility.getFormattedDollar(account.getAllocAmount(), oid);

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

			for (int j = 0; j < 15; j++)
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
					<td width=15% class=browseRow align=right><%=bd_alloc_perc%>%</td>
					<td width=15% class=browseRow align=right><%=bd_alloc_amt%></td>
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
				<tr><td><b>There are no accounts for this item.</b></td></tr>
				</table>
<%}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<% List commentList = (List) poLine.getDocCommentList();%>
<table <%=HtmlWriter.isVisible(oid, "po-itemComments")%> border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height=18px class=browseHdr><a href="javascript: if (validateItem()) { doSubmit('/orders/po_notes_ln.jsp', 'PoLineUpdate;DocCommentRetrieveByLine'); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
					<td width=64% height=18px class=browseHdr>&nbsp;<b>Comment</b></td>
					<td width=8% height=18px class=browseHdr align=center>&nbsp;<b>Print</b></td>
					<td width=7% height=18px class=browseHdr align=center>&nbsp;<b>Bold</b></td>
					<td width=16% height=18px class=browseHdr align=center>&nbsp;<b>Placement</b></td>
					<%	if (commentList != null && commentList.size() > 0)
					{%>
					<td width=3% height=18px class=browseHdr align=center><a href="javascript: toggleDetailsDisplay('commentDetails', 'Details'); void(0);"><img id='commentDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Notes"></a>&nbsp;
					<%	}%>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
	i_size = 0;
	String s_placeString = "";
	if (commentList != null)
	{
		i_size = commentList.size();
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
				s_placeString = "After";
			}
			else if (s_place.equals("B"))
			{
				s_placeString = "Before";
			}
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=66% class=browseRow><%=headerEncoder.encodeForHTML(s_title)%></td>
					<td width=8% class=browseRow align=center valign=top><%=s_print%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_bold%></td>
					<td width=16% class=browseRow align=center valign=top><%=s_placeString%></td>
					<td width=3% class=browseRow align=center valign=top>&nbsp;</td>
				</tr>
				</table>
				<div id="commentDetails" name="commentDetails" style="display:none;">
				<table>
				<tr>
					<td width=75px>&nbsp;</td>
					<td width=100%>
<%				if (s_bold.equals("Y")) 	{ %>	<b><% } %><%=headerEncoder.encodeForHTML(s_text)%><% if (s_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				</table>
				</div>
<% 	}
	}
	if (commentList == null || commentList.size() == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td><b>There are no comments for this item.</b></td></tr>
				</table>
<%}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>




<!-- Desde aqui es el add para el Attachment -->
<%
List attachmentList = (List) request.getAttribute("docAttachmentList");
%>

<table <%=HtmlWriter.isVisible(oid, "po-itemAttachments")%> border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
<!--					<td width=2% height=18px class=browseHdr><a href="javascript: if (validateItem()) { doSubmit('/orders/po_item_attachment_new.jsp', 'PoLineUpdate'); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>-->
					<td width=2% height=18px class=browseHdr><a href="javascript: if (validateItem()) { doSubmit('/orders/po_line_attachments.jsp', 'PoLineUpdate;DocAttachmentRetrieveByLine'); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
					<td width=64% height=18px class=browseHdr>&nbsp;<b>Attachment</b></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
	i_size = 0;
	if (attachmentList != null)
	{
		i_size = attachmentList.size();
		for (i = 0;i < attachmentList.size(); i++)
		{
			DocAttachment docAttachment = (DocAttachment) attachmentList.get(i);
			String	filename = docAttachment.getDocFilename();
			String	ext = filename.substring(filename.lastIndexOf(".") + 1);
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=5% align=right id="doc_num_<%=i%>"> &nbsp;<%=i+1%>.&nbsp;	</td>
						<td width=65%>

						<tsa:hidden name="docTitle" value="<%=docAttachment.getDocFilename()%>" />
						<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);" title="Click here to Open the file"><%=docAttachment.getDocTitle()%></a>
						</td>
						<td width=5% valign=middle align=center>
							<tsa:hidden name="docFilename" value="<%=filename%>" />
<%		if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>');void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("EML")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/email.gif" border=0 alt="Open Attached Email"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT") || ext.equalsIgnoreCase("PPTX")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
						</td>
				</tr>
				</table>
				</div>
<% 	}
	}
	if (attachmentList == null || attachmentList.size() == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td><b>There are no Attachments for this item.</b></td></tr>
				</table>
<%}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<!-- AUn no tiene labels  -->
<!-- Hasta aqui es el add para el Attachment -->

<br>


<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=s_return_page%>', '<%=s_return_method%>'); void(0);">Return</a></div></td>
</tr>
</table>

<table>
<tr>
	<td>
		<tsa:hidden name="PoLine_discOvr" value="<%=poLine.getDiscOvr()%>"/>
		<tsa:hidden name="PoLine_discountPercent" value="<%=HiltonUtility.getBigDecimalFormatted(poLine.getDiscountPercent(), 2)%>"/>
		<tsa:hidden name="PoLine_discountAmount" value="<%=HiltonUtility.getFormattedDollar(poLine.getDiscountAmount(), oid)%>"/>
		<tsa:hidden name="PoLine_taxOvr" value="<%=poLine.getTaxOvr()%>"/>
		<tsa:hidden name="PoLine_taxPercent" value="<%=HiltonUtility.getBigDecimalFormatted(poLine.getTaxPercent(), 5)%>"/>
		<tsa:hidden name="PoLine_taxAmount" value="<%=HiltonUtility.getFormattedDollar(poLine.getTaxAmount(), oid)%>"/>
		<tsa:hidden name="PoLine_shipOvr" value="<%=poLine.getShipOvr()%>"/>
		<tsa:hidden name="PoLine_shippingCharges" value="<%=HiltonUtility.getFormattedDollar(poLine.getShippingCharges(), oid)%>"/>
		<tsa:hidden name="PoLine_otherOvr" value="<%=poLine.getOtherOvr()%>"/>
		<tsa:hidden name="PoLine_otherCharges" value="<%=HiltonUtility.getFormattedDollar(poLine.getOtherCharges(), oid)%>"/>
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
	var itemLocation = "<%= headerEncoder.encodeForJavaScript(s_item_location) %>";
	var lookupStatus = "<%= headerEncoder.encodeForJavaScript(lookupStatus) %>";


	function setFieldFocus() {
		if (lookupStatus == "NOTFOUND" && frm.PoLine_description) {
			frm.PoLine_description.focus();
		} else if (lookupStatus == "FOUND" && frm.PoLine_quantity) {
			frm.PoLine_quantity.focus();
		} else {
			setFirstFocus();
		}
<%	if (shipToTaxCodeSet.equals("Y")) {%>
			alert("<tsa:label labelName="req-shipToTaxCodeMsg" defaultString="The Tax code for one or more of the Ship To address(es) may differ from the Tax Code specified for this item." checkRequired="false" />");
<%	}%>
	}

	function toggleDetailsDisplay(areaName, type) {
		var myAreas = document.getElementsByName(areaName);
		var myArea;
		var myImg = document.getElementById(areaName + "Img");
		var myStyle;
		var hideArea = false;

		for (i = 0; i < myAreas.length; i++) {
			myArea = myAreas[i];
			myStyle = myArea.style.display ;
			if (myArea.style.display == "none") {
				myArea.style.display = "block";
				if (type == "bavDetailOptions") {
					myArea.style.visibility = "visible" ;
				}
			}
			else {
				hideArea = true;
				myArea.style.display = "none";
				if (type == "bavDetailOptions") {
					myArea.style.visibility = "hidden" ;
				}
			}
		}

		if (type == undefined) {
			type = "Details";
		}

		if (type != "addItemOptions" && type != "bavDetailOptions")
		{
			/*	don't want to do this for the add item options	*/
			if (hideArea)
			{
				myImg.src = "<%=contextPath%>/images/arrows_down.gif";
				myImg.alt = "View" + type;
			}
			else
			{
				myImg.src = "<%=contextPath%>/images/arrows_up.gif";
				myImg.alt = "Hide " + type;
			}
		}
	}

	function addUp ()
	{
		var dollar_dec = <%=Integer.valueOf(s_dollar_decimals).intValue()%>;
		var price_dec = <%=Integer.valueOf(s_price_decimals).intValue()%>;
		var qty_dec = <%=Integer.valueOf(s_quantity_decimals).intValue()%>;
		var p = nformat(eval(nfilter(frm.PoLine_unitPrice)),price_dec);
		var q = nformat(eval(nfilter(frm.PoLine_quantity)),qty_dec);
		var f = eval(nfilter(frm.PoLine_umFactor));

		if (f == 0) { f = 1; }

		frm.PoLine_umFactor.value = f;
		frm.PoLine_unitPrice.value = p;
		frm.PoLine_quantity.value = q;

		frm.computed_subtotal.value = nformat( p * q * f, dollar_dec );
		if ( p == q && q == 0) {
			frm.PoLine_unitPrice.value = '';
			frm.PoLine_quantity.value = '';
			frm.computed_subtotal.value = '';
		}

		//frm.ss_lnqty.value = frm.RequisitionLine_quantity.value;
	}

	function getPriceBreak() {
		var unitPrice = frm.PoLine_unitPrice.value;
		var qty = frm.PoLine_quantity.value;
		var itemNumber = frm.PoLine_itemNumber.value;

		if (itemNumber != "<%=poLine.getItemNumber()%>") {
			return;
		}
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
		frm.PoLine_unitPrice.value = unitPrice;
	}

	function checkQty (x)
	{
<%	if (s_minmax_restrict.equalsIgnoreCase("Y"))
		{ %>
			var qty_dec = <%=Integer.valueOf(s_quantity_decimals).intValue()%>;
			var qty = eval(nfilter(frm.PoLine_quantity));
			var min = eval(nfilter(frm.min_qty));
			var max = eval(nfilter(frm.max_qty));

			if ( (qty < min) && (min >= 0) )
			{
				min = nformat(min, qty_dec);
				alert("The minimum requested quantity allowed for this item is " + min + ".");
				frm.PoLine_quantity.value = min;
			}
			else if ( (qty > max) && (max > 0) )
			{
				max = nformat(max,qty_dec);
				alert("The maximum requested quantity allowed for this item is " + max + ".");
				frm.PoLine_quantity.value = max;
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

		frm.PoLine_umCode.value = trim(frm.PoLine_umCode);

		if (info_window != null) {
			if (browser == "Netscape") {
				if (info_window.closed == false) {
					info_window.setUomCode("PoLine_");
					open = false;
				}
			}
			else {
				info_window.setUomCode("PoLine_");
				open = false;
			}
		}

		if (open == true)
		{
			if (uomArray.length > 0 || populated)
			{
				code = frm.PoLine_umCode.value;
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
				popupParameters = "as_prefix=PoLine_";

				setLookupParameters('/base/get_uom_info.jsp', 'UnitOfMeasureRetrieveAll');
				document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
			}
		}
	}

	function setUmFactor(factor) {
		frm.PoLine_umFactor.value = factor;
		addUp();
	}
	/*
	function addItem()
	{
		var line_count = frm.lineCount.value;
		line_count++;
		frm.lineCount.value = line_count;
		if (frm.PoLine_itemNumber.value.length > 0)
		{
			doSubmit('/orders/po_item.jsp','PoLineItemLookup;PoLineRetrieve');
		}
		else
		{
			doSubmit('/orders/po_item.jsp','PoLineCreate');
		}
	}
	*/

	function addItem()
	{
		var line_count = frm.lineCount.value;
		line_count++;
		frm.lineCount.value = line_count;

		doSubmit('/orders/po_item.jsp', 'PoLineUpdate;PoLineCreate');
	}

	function thisLoad()
	{
		f_StartIt();
<%	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer())
		{ %>
			checkInputSettings();
<%	}
		else
		{
			if ( role.getAccessRights("PO") < 2 || (!bAdminChanges && s_po_status.compareTo(DocumentStatus.PO_REJECTED) > 0)  || (s_po_status.compareTo(DocumentStatus.CANCELLED) >= 0) || s_po_status.compareTo(DocumentStatus.PO_AMENDED) == 0) { %>
				checkInputSettings();
<%		}
			if ( bAdminChanges && s_po_status.compareTo(DocumentStatus.PO_REJECTED) > 0 && s_po_status.compareTo(DocumentStatus.CANCELLED) < 0 && s_po_status.compareTo(DocumentStatus.PO_AMENDED) != 0 )  { %>
				frm.PoLine_itemNumber.readOnly = true;
				frm.PoLine_itemNumber.contentEditable = false;
				frm.PoLine_itemNumber.disabled = true;
				frm.PoLine_quantity.readOnly = true;
				frm.PoLine_quantity.contentEditable = false;
				frm.PoLine_quantity.disabled = true;
				frm.PoLine_umCode.readOnly = true;
				frm.PoLine_umCode.contentEditable = false;
				frm.PoLine_umCode.disabled = true;
				frm.PoLine_unitPrice.readOnly = true;
				frm.PoLine_unitPrice.contentEditable = false;
				frm.PoLine_unitPrice.disabled = true;
<%		}
		} %>
<% if (poLine.getItemSource().equals("CAT") && !poLine.getItemNumber().startsWith("*") && oid.equalsIgnoreCase("bly07p")) {%>
		allowInputEdit(frm.PoLine_commodity, false);
<%	}%>
<% if (oid.equalsIgnoreCase("msg07p")) {%>
		allowInputEdit(frm.PoLine_requisitionerCode, false);
<%	}%>
<% if (oid.equalsIgnoreCase("hoy08p")) { %>
		if (frm.PoLine_udf3Code)
			allowInputEdit(frm.PoLine_udf3Code, false);
<% } %>
	<% if (oid.equalsIgnoreCase("msg07p") && (s_po_status.compareTo(DocumentStatus.PO_AWARDED) >= 0)) {%>

	if (frm.PoLine_receiptRequired)
		alert(<%=(s_po_status.compareTo(DocumentStatus.PO_AWARDED) >= 0)%>);
		allowInputEdit(frm.PoLine_receiptRequired, false);
	<%	}%>

	setFieldsToDisplayByItem();

}

	function totalsAlert()
	{
		var alertMessage = "";

<%	if (Integer.valueOf(s_line_count).intValue() > 1) { %>
			if ((frm.PoLine_discOvr.value == "N") && (eval(frm.PoLine_discountPercent.value) == 0) && (eval(frm.PoLine_discountAmount.value) > 0)) {
				alertMessage += "Discount will be redistributed across remaining line items.\n";
			}
			if ((frm.PoLine_taxOvr.value == "N") && (eval(frm.PoLine_taxPercent.value) == 0) && (frm.PoLine_taxable.value == "Y") && (eval (frm.PoLine_taxAmount.value) > 0)) {
				alertMessage += "Tax will be redistributed across remaining line items.\n";
			}
			if ((frm.PoLine_shipOvr.value == "N") && (eval(frm.PoLine_shippingCharges.value) > 0)) {
				alertMessage += "Shipping Charges will be redistributed across remaining line items.\n";
			}
			if ((frm.PoLine_otherOvr.value == "N") && (eval(frm.PoLine_otherCharges.value) > 0)) {
				alertMessage += "Other Charges will be redistributed across remaining line items.\n";
			}
			if ( alertMessage.length > 0 ) {
				alert ( 'Totals Update:\n'+alertMessage );
			}
<%	} %>
		return true;
	}

	function retrieveLine(linenumber)
	{
		frm.lineToRetrieve.value = linenumber;
		doSubmit('/orders/po_item.jsp', 'PoLineUpdate;PoLineRetrieveByLineNumber');
	}

	function viewItemHistory()
	{
		popupParameters = "HistoryLog_icHeaderHistory=<%=poHeader.getIcHeaderHistory()%>;HistoryLog_icLineHistory=<%=poLine.getIcLineHistory()%>;formtype=PO;PoLine_icPoHeader=<%=poLine.getIcPoHeader()%>;PoLine_icPoLine=<%=poLine.getIcPoLine()%>;poNumber=<%=poLine.getPoNumber()%>";
		doSubmitToPopup('/orders/po_history.jsp', 'HistoryLogRetrieveByHistoryLine', 'width=675px', 'height=350px');
	}

	function viewDeliveryInfo()
	{
		var e = frm.elements;

		for (i = 0; i < e.length; i++)
		{
			if (e.item(i).type != "hidden")
			{
				e.item(i).disabled = false;
			}
		}
		doSubmit('/orders/po_delivery_info.jsp', 'PoGetDeliveryReleaseInfo');
	}


	function getItemInfo() {
		// No need to call the server if the item number is empty
		if (!isEmpty(frm.PoLine_itemNumber.value)) {
			doSubmit('/orders/po_item.jsp', 'PoLineItemLookup;PoLineRetrieve');
		}
	}


	function validateItem() {
		if (isEmpty(frm.PoLine_itemNumber.value) && isEmpty(frm.PoLine_description.value)) {
			alert("You must enter either an item number or description for this item.");
			return false;
		}
		return true;
	}

	function validateForm() {
		var deleteItem = false;
		var handlers = frm.handler.value;
		var originalQuantity = "<%=poLine.getQuantity()%>";
		var originalPrice = "<%=poLine.getUnitPrice()%>";

		if (isEmpty(frm.PoLine_itemNumber.value) && isEmpty(frm.PoLine_description.value) && handlers.indexOf("PoLineDelete") < 0) {
			// no need to do this check if we are already deleting the line item or if changes are going to be discarded and an item number or description was previously entered
			if (frm.lineUpdated.value == "true") {
				if (handlers == "PoLineRetrieveByHeaderHandler;") {
<%				if ( !HiltonUtility.isEmpty(poLine.getItemNumber()) || !HiltonUtility.isEmpty(poLine.getDescription()) ) {	%>
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
						if (handlers.indexOf("PoLineCreate") >= 0) {
							frm.lineCount.value = frm.lineCount.value - 1;
						}
						return false;
					}
				}
			} else {
				deleteItem = true;
			}

			if (deleteItem) {
				var ind = handlers.indexOf("PoLineUpdateHandler;");
				if (ind >= 0) {
					handlers = handlers.substring(0, ind) + handlers.substring(ind + 20, handlers.length);
				} else {
					ind = handlers.indexOf("PoLineUpdateHandler");
					if (ind >= 0) {
						handlers = handlers.substring(0, ind) + handlers.substring(ind + 19, handlers.length);
					} else {
						ind = handlers.indexOf("PoLineUpdate;");
						if (ind >= 0) {
							handlers = handlers.substring(0, ind) + handlers.substring(ind + 13, handlers.length);
						} else {
							ind = handlers.indexOf("PoLineUpdate");
							if (ind >= 0) {
								handlers = handlers.substring(0, ind) + handlers.substring(ind + 12, handlers.length);
							}
						}
					}
				}
				frm.lineCount.value = frm.lineCount.value - 1;

				if (frm.lineToRetrieve.value > frm.PoLine_lineNumber.value) {
					frm.lineToRetrieve.value = frm.lineToRetrieve.value - 1;
				}

				frm.handler.value = "PoLineDeleteHandler;" + handlers;
				return true;
			}
			else {
				if (frm.handlers.indexOf("PoLineCreate") >= 0) {
					frm.lineCount.value = frm.lineCount.value - 1;
				}
				return false;
			}
		} else {
			if (eval(frm.PoLine_quantity.value) != eval(originalQuantity))
			{
				frm.originalQuantity.value = originalQuantity;
				frm.writehistory.value = "Y";
			}
			if (eval(frm.PoLine_unitPrice.value) != eval(originalPrice))
			{
				frm.originalPrice.value = originalPrice;
				frm.writehistory.value = "Y";
			}
			return true;
		}
	}

	function submitThis()
	{
		<%	if (poLine.getItemSource().equals("CAT"))
			{
				BigDecimal minReqQty = new BigDecimal(0);
				Object catalogItem = CatalogItemManager.getInstance().getCatalogItem(oid, poLine.getCatalogId(), poLine.getItemNumber());
				if (catalogItem != null)
				{
					minReqQty = ((CatalogItem)catalogItem).getMinReqQty();
					if (minReqQty.compareTo(new BigDecimal(0)) > 0)
					{%>
						if (frm.PoLine_quantity.value < <%=minReqQty%> && frm.PoLine_quantity.value >= 0)
						{
							alert("The minimum requisition quantity for this Item is <%=minReqQty%>.\nYou must enter a quantity greater or equal than <%=minReqQty%>");
							return;
						}
		<%			}
				}
			}	%>
		if (frm.organizationId.value == "BLY07P" && frm.PoHeader_flagChange.value == "N")
		{
			var department = (frm.PoHeader_description.value != frm.PoLine_description.value)? true:false;
			var udf5 = ('<%=poLine.getUdf5Code()%>' != frm.PoLine_udf5Code.value)? true:false;
			var udf6 = ('<%=poLine.getUdf6Code()%>' != frm.PoLine_udf6Code.value)? true:false;
			var dateRequired = ('<%=s_required_date%>' != frm.PoLine_requiredDate.value)? true:false;
			var datePromised = ('<%=HiltonUtility.getFormattedDate(poLine.getPoPromised(), oid)%>' != frm.PoLine_poPromised.value)? true:false;

			if (department||udf5||udf6||dateRequired||datePromised)
			{
				frm.PoHeader_flagChange.value = 'Y';
			}
			else
			{
				frm.PoHeader_flagChange.value = 'N';
			}
		}

		doSubmit('<%=s_return_page%>', 'PoLineUpdate;<%=s_return_method%>');
	}

	function setAllowBrowse()
	{
<%	if (role.getAccessRights("PO") < 2 || s_po_status.compareTo(DocumentStatus.PO_REJECTED) > 0) { %>
			popupParameters = popupParameters + "allowBrowse=false;";
<%	} %>
	}

	function viewAssetRelated(iclinekey)
	{
		frm.allowBrowse.value="true";
		var newInputField = "<input type='hidden' name='Asset_icLineKey' value='" + iclinekey + "'>";
			newInputField = newInputField + "<input type='hidden' name='icPoHeader' value='<%=s_ic_po_header%>'>";
			newInputField = newInputField + "<input type='hidden' name='action'     value='po-item'>";
			newInputField = newInputField + "<input type='hidden' name='process'    value='AssetRetrieveByIcLineKey'>";
			newInputField = newInputField + "<input type='hidden' name='urlret'     value='/orders/po_item'>";
		setHiddenFields(newInputField);
		doSubmit("/asset/asset_po_browse.jsp", "AssetRetrieveByIcLineKey");

	}

	function viewPriceBreaks(type) {
		var catid = frm.catalogId.value;
		var itemNumber = frm.PoLine_itemNumber.value;

		popupParameters = "browseName=catalog-pricebrk";
		popupParameters = popupParameters + ";colname=CatalogPriceBrk_id_catalogId;operator==;filter_txt=" + catid + ";logicalOperator=AND;originalFilter=Y;sort=N";
		popupParameters = popupParameters + ";colname=CatalogPriceBrk_id_itemNumber;operator==;filter_txt=" + itemNumber + ";logicalOperator=AND;originalFilter=Y;sort=N;";

		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}

	function showCatalogItemDifference(catalogItemDifference)
	{
			alert('<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "unitCostDifferentCatalogItem", "The Unit Cost is different than the Catalog Item Cost. Difference: ")%> '+ catalogItemDifference);
	}

	function browseSupplier(formField)
	{
		<% if (oid.equalsIgnoreCase("bly07p")) { %>
		popupParameters = popupParameters + "colname=XrefCombo_code2;operator==;filter_txt=" + frm.PoLine_itemNumber.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
		browseLookup(formField, 'mxp-vendor');
		<% } else { %>
		popupParameters = popupParameters + "formField=" + formField +";browseName=vendor;allowBrowse=" + frm.allowBrowse.value;
		doSubmitToPopup('/browse/supplier_filter_options.jsp', 'VendorRetrieveBrowseOptions', 'WIDTH=700px', 'HEIGHT=500px');
		<% } %>
	}

	function browseCommodityByType()
	{
		var currentAllowBrowse = frm.allowBrowse.value;
		var deleteItem = false;
<% 		if(defaultByCommodity.equalsIgnoreCase("Y")) { %>
			if (isEmpty(frm.PoLine_itemNumber.value)) {
			// no need to do this check if we are already deleting the line item or if changes are going to be discarded and an item number or description was previously entered
<%				if ( !HiltonUtility.isEmpty(poLine.getItemNumber())) {	%>
				// no need to delete item if changes are going to be discarded and an item number or description was previously entered
					deleteItem = true;
<%				} %>
			}
			else
			{
				deleteItem = true;
			}
			if (!deleteItem) {
				alert("This is not a valid item because it has no item number!  Would you like to discard changes?");
			}
			else
			{
			<%	if (poLine.getItemSource().equals("CAT") && !poLine.getItemNumber().startsWith("*") && oid.equalsIgnoreCase("bly07p")) { %>
					frm.allowBrowse.value="false";
			<%	} %>

			<% 	if (oid.equalsIgnoreCase("msg07p")) { %>
					popupParameters = "browseName=commodity";
					popupParameters = popupParameters + ";formField=PoLine_commodity;allowBrowse=" + frm.allowBrowse.value;
					popupParameters = popupParameters + ";colname=Commodity_commodityType;operator==;filter_txt=BO;logicalOperator=OR;originalFilter=Y;sort=N";
					popupParameters = popupParameters + ";colname=Commodity_commodityType;operator==;filter_txt=SP;logicalOperator=AND;originalFilter=Y;sort=N";
					doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
			<% 	} else { %>
					browseCommodity('PoLine_commodity', 'commodity', '<%=propertiesManager.getProperty("MISC", "COMMODITYTYPE", "")%>');
			<% 	} %>

				frm.allowBrowse.value = currentAllowBrowse;
			}
<% 		}
		else
		{ %>
	<% 		if (poLine.getItemSource().equals("CAT") && !poLine.getItemNumber().startsWith("*") && oid.equalsIgnoreCase("bly07p")) { %>
				frm.allowBrowse.value="false";
	<%		} %>

	<% 		if (oid.equalsIgnoreCase("msg07p")) { %>
				popupParameters = "browseName=commodity";
				popupParameters = popupParameters + ";formField=PoLine_commodity;allowBrowse=" + frm.allowBrowse.value;
				popupParameters = popupParameters + ";colname=Commodity_commodityType;operator==;filter_txt=BO;logicalOperator=OR;originalFilter=Y;sort=N";
				popupParameters = popupParameters + ";colname=Commodity_commodityType;operator==;filter_txt=SP;logicalOperator=AND;originalFilter=Y;sort=N";
				doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
		<% 	} else { %>
				browseCommodity('PoLine_commodity', 'commodity', '<%=propertiesManager.getProperty("MISC", "COMMODITYTYPE", "")%>');
		<% 	} %>

			frm.allowBrowse.value = currentAllowBrowse;
	<% 	} %>

	}

	function browseWarehouse(formField)
	{
		popupParameters = popupParameters + "colname=XrefCombo_code2;operator==;filter_txt=" + frm.PoLine_itemNumber.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
		browseLookup(formField, 'mxp-warehouse');
	}

	function browseRequisitioner()
	{
		var currentAllowBrowse = frm.allowBrowse.value;
	<% if (oid.equalsIgnoreCase("msg07p")) { %>
		frm.allowBrowse.value = "false";
	<% } %>
		browseLookup('PoLine_requisitionerCode', 'requisitioner');
		frm.allowBrowse.value = currentAllowBrowse;
	}

	function browseStdUdf3()
	{
		var currentAllowBrowse = frm.allowBrowse.value;
		<% if (oid.equalsIgnoreCase("hoy08p")) { %>
		frm.allowBrowse.value = "false";
		<% } %>
		browseStd('PoLine_udf3Code', 'LN03');
		frm.allowBrowse.value = currentAllowBrowse;
	}

	function browseUdf6Code()
	{
		<% if (oid.equalsIgnoreCase("bly07p")) { %>
		browseStd('PoLine_udf6Code', 'RQ06');
		<% } else { %>
		browseStd('PoLine_udf6Code', 'LN06');
		<% } %>
	}

	function viewAltLanguages() {
		doSubmit('/orders/po_item_altlanguages.jsp', 'PoLineUpdate;AltTextRetrieveByItem');
	}

	<%	if (oid.equalsIgnoreCase("vse06p")) { %>
	function setUdf10()
	{
		if (frm.udf10Code[0].checked)
		{
			frm.PoLine_udf10Code.value = frm.udf10Code[0].value;
			frm.updateUdf10.value = "TRUE";
		}
		else if (frm.udf10Code[1].checked)
		{
			frm.PoLine_udf10Code.value = frm.udf10Code[1].value;
			frm.updateUdf10.value = "TRUE";
		}
		else if (frm.udf10Code[2].checked)
		{
			frm.PoLine_udf10Code.value = frm.udf10Code[2].value;
			frm.updateUdf10.value = "TRUE";
		}
	}
<%} %>

	function setFieldsToDisplayByItem() {
		var itemNumber = frm.PoLine_itemNumber.value;

		<% if (oid.equalsIgnoreCase("bly07p")) { %>
		if (isEmpty(itemNumber) || (itemNumber.indexOf('*') == 0))
		{
			displayArea('po-LN06');
			displayArea('po-LN06-fld');
		} else {
			hideArea('po-LN06');
			hideArea('po-LN06-fld');
		}
		<% } %>
	}

    function browseFdcsWOElements(fldName) {

    	popupParameters = "workNumber="+ frm.PoLine_udf1Code.value +";";
       	popupParameters = popupParameters + "segNumber="+ frm.PoLine_udf2Code.value +";";
       	popupParameters = popupParameters + "opNumber="+ frm.PoLine_udf3Code.value +";";
       	popupParameters = popupParameters + "custNumber="+ frm.PoLine_udf4Code.value +";";

    	browseLookup( fldName, 'fdc-wo-elements' ) ;
	}

	function addAttachment() {
		doSubmit('/orders/po_item_attachment_new.jsp', 'DoNothing');
	}


    function updateReqLineAccount()
    {
    	var deleteItem = false;
    	if (isEmpty(frm.PoLine_itemNumber.value)) {
			// no need to do this check if we are already deleting the line item or if changes are going to be discarded and an item number or description was previously entered
	<%		if ( !HiltonUtility.isEmpty(poLine.getItemNumber())) {	%>
					// no need to delete item if changes are going to be discarded and an item number or description was previously entered
				deleteItem = true;
	<%		} %>
    	}
    	else
		{
			deleteItem = true;
		}
<% 		if(defaultByCommodity.equalsIgnoreCase("Y")) { %>
	    	if(deleteItem)
	    	{
	    		frm.Commodity_commodity.value = frm.PoLine_commodity.value;
	    		frm.commodityUpdateAccount.value = "Y";

				doSubmit('/orders/po_item.jsp', 'PoLineUpdate;PoLineRetrieve');
	    	}
<%		} %>
    }


// End Hide script -->
</SCRIPT>