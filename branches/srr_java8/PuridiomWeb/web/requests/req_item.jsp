<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/attachments.js"></script>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>

<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%@ page import="com.tsa.puridiom.catalog.tasks.CatalogItemManager" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<%
	int i;
	int i_size;
	RequisitionLine reqLine = (RequisitionLine) request.getAttribute("requisitionLine");
	String s_req_line_status = reqLine.getStatus();
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
	String 	s_duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
	String  defaultByCommodity = propertiesManager.getProperty("ACCOUNTS", "DEFAULTBYCOMMODITY", "N");
	String  s_multiple_browse_item = propertiesManager.getProperty("REQ OPTIONS", "MULTIPLEBROWSEITEM", "N");
	boolean fpeUser = user.isAFpe() ;
	boolean engineerUser = user.isAEngineer() ;
	boolean msrEngineer = user.isAnAdministeredBy();
	String maxLength = "";

	String  isLockedFields = "";
	String	isChemicalLocked = "";
	if ( s_req_type.equalsIgnoreCase("M")) {
		isLockedFields = "N";
		if (reqLine.getItemSource().equals("INV") || (reqLine.getItemSource().equals("CAT"))) {
			isLockedFields = "Y";
		}
		if (reqLine.getItemSource().equals("CAT")) {
			isChemicalLocked = "Y";
		}
	}

	String  isLockedFielsFromInvItem = "N";
	if (s_req_type.equalsIgnoreCase("R") || s_req_type.equalsIgnoreCase("S")
			|| s_req_type.equalsIgnoreCase("T") || s_req_type.equalsIgnoreCase("U")) {
		if (reqLine.getItemSource().equals("INV")) {
			isLockedFielsFromInvItem = "Y";
		}
	}

	BigDecimal b_req_ic_header = reqLine.getIcReqHeader();
	BigDecimal b_req_ic_line = reqLine.getIcReqLine();
	BigDecimal bd_line_number = HiltonUtility.getBigDecimalFormatted(reqLine.getLineNumber(), 0);
	BigDecimal b_ic_msr_line = reqLine.getIcLineHistory() ;
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_from_page = (String) request.getAttribute("frompage");
	String	s_from_step = (String) request.getAttribute("fromstep");

	String	s_return_page = "";
	String	s_return_method = "";
	BigDecimal bd_item_qty = HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid);
	BigDecimal bd_duomQuantity = HiltonUtility.getFormattedQuantity(reqLine.getDuomQuantity(), oid);
	String s_item_asset = reqLine.getAsset();
	String s_item_taxable = reqLine.getTaxable();
	String s_udf1 = reqLine.getUdf1Code();
	String s_shelfLife	= reqLine.getShelfLifeRqd();
	//BigDecimal b_item_unitprice = HiltonUtility.getFormattedPrice(reqLine.getUnitPrice(), oid);
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");

    String s_curr_code = requisitionHeader.getCurrencyCode();

	String b_item_unitprice = HiltonUtility.getCurrencyNS(reqLine.getUnitPrice(), s_curr_code, oid);

	BigDecimal	bd_um_factor = reqLine.getUmFactor();
	String s_receipt_required = reqLine.getReceiptRequired();
	String s_req_number = reqLine.getRequisitionNumber();
	String	lookupStatus = (String) request.getAttribute("lookupStatus");
	String	shipToTaxCodeSet = (String) request.getAttribute("shipToTaxCodeSet");
	List catalogPriceBrkList = (List) request.getAttribute("catalogPriceBrkList");
	String catalogItemImageFile = HiltonUtility.ckNull((String) request.getAttribute("catalogItemImageFile"));
	int breakCount = 0;
	String s_udf6 = reqLine.getUdf6Code();
	String s_udf7 = reqLine.getUdf7Code();
	String catalogId = HiltonUtility.ckNull( (String)reqLine.getCatalogId() );
	BigDecimal	s_req_total = reqLine.getLineTotal();
	BigDecimal b_ic_po_line = reqLine.getIcPoLine();
	String s_udf10Code = HiltonUtility.ckNull((String)reqLine.getUdf10Code());
	boolean fdcsEnabled = propertiesManager.getProperty("FDCS","Enabled","N").equalsIgnoreCase("Y") ;
	boolean defaultAccountsByCommodity = propertiesManager.getProperty("ACCOUNTS","DEFAULTBYCOMMODITY","N").equals("Y");
	boolean hidden_delete_option = false;

	String s_udf_1_code = requisitionHeader.getUdf1Code();
	String s_udf_14_code = requisitionHeader.getUdf14Code();

	if (catalogPriceBrkList == null) {
		catalogPriceBrkList = new ArrayList();
	} else {
		breakCount = catalogPriceBrkList.size();
	}

	if (lookupStatus == null) {
		lookupStatus = "";
	}
	if (shipToTaxCodeSet == null) {
		shipToTaxCodeSet = "";
	}

	if (s_udf10Code == null || s_udf10Code == ""){
		s_udf10Code = "no";
	}

	BigDecimal b_subtotal = HiltonUtility.getFormattedDollar(reqLine.getQuantity().multiply(reqLine.getUnitPrice()).multiply(bd_um_factor), oid);
	String b_subtotalString =  HiltonUtility.getCurrencyNS(b_subtotal, s_curr_code, oid);

	if (HiltonUtility.isEmpty(s_req_number))	{	s_req_number = "N/A";			}
	if (HiltonUtility.isEmpty(s_req_status))		{	s_req_status = "1000";			}
	if (HiltonUtility.isEmpty(s_line_count))		{	s_line_count = "1";				}
	if (HiltonUtility.isEmpty(s_from_page))		{	s_from_page = "shopcart";		}

	if (s_from_page.equalsIgnoreCase("shopcart"))
	{
		s_return_page = "/requests/req_items.jsp";
		s_return_method = "RequisitionLineRetrieveByHeader";
	}
	else if (s_from_page.equalsIgnoreCase("sourcing"))
	{
		s_return_page = "/requests/req_sourcing.jsp";
		s_return_method = "RequisitionLineRetrieveByHeader";
	} else
	{
		s_return_page = "/requests/req_review.jsp";
		s_return_method = "RequisitionRetrieve";
	}

	String reqHeaderUdf1 = HiltonUtility.ckNull((String)request.getAttribute("reqHeaderUdf1"));

	String showLinkGetItemInfo = (String)request.getAttribute("showLinkGetItemInfo");
	if(showLinkGetItemInfo == null)
		showLinkGetItemInfo = "N";

	if( oid.equalsIgnoreCase("wpc08p") && HiltonUtility.isEmpty(s_receipt_required))
	{
		s_receipt_required = "R";
	}

	boolean b_udf3 = false;
	if (oid.equalsIgnoreCase("hoy08p") && reqLine.getUdf3Code().equalsIgnoreCase("Y"))
		b_udf3 = true;

	boolean labViewGroup = false;
	List groupList = user.getUserRoles();
	for (Iterator it = groupList.iterator(); it.hasNext(); )
    {
		if (((String)it.next()).indexOf("LAB VIEW") >= 0)
		{
			labViewGroup = true;
			break;
		}
	}

	String	s_use_subcommodity = propertiesManager.getProperty("MISC", "USE SUBCOMMODITY", "N");

	String default_taxable_by_udf1 = propertiesManager.getProperty("REQ OPTIONS", "DEFAULT TAXABLE BY UDF1", "");
	String udf1Code = requisitionHeader.getUdf1Code();
	if (!HiltonUtility.isEmpty(default_taxable_by_udf1)) {
		if (s_req_status.equals(DocumentStatus.REQ_INPROGRESS) && !HiltonUtility.isEmpty(udf1Code)) {
			if (udf1Code.equals(default_taxable_by_udf1)) {
				s_item_taxable = "Y";
			} else {
				s_item_taxable = "N";
			}
		}
		else {
			s_item_taxable = "N";
		}
	}

	List chargeCodeList = (List) request.getAttribute("chargeCodeList");
	if(chargeCodeList == null)
		chargeCodeList = new ArrayList();

	List inspectionList = (List) request.getAttribute("inspectionList");
	if(inspectionList == null)
		inspectionList = new ArrayList();

	Iterator typeIterator = null;
	Map securityTypes = null;
	boolean isCurrentApprover = false;
	String s_requisitioner = requisitionHeader.getRequisitionerCode();
	if (requisitionHeader != null)
	{
		isCurrentApprover = requisitionHeader.getAppBy().equalsIgnoreCase(uid) && requisitionHeader.getStatus().compareTo(DocumentStatus.REQ_APPROVING) == 0;
		if (isCurrentApprover)
		{
			securityTypes = propertiesManager.getSection("$ADMIN APP REQ");
			if (securityTypes != null) {
				typeIterator = securityTypes.keySet().iterator();
			}
		}
	}
	if (!(s_req_status.compareTo(DocumentStatus.REQ_INPROGRESS) >= 0 && s_udf_1_code != null && s_udf_1_code.contains("RESALE") && s_udf_14_code != null && s_udf_14_code.equals("DBS") && b_ic_po_line.compareTo(new BigDecimal("0")) > 0) || (s_req_type.equals("M") && s_req_status.equals(DocumentStatus.REQ_PLANNING))) {
		hidden_delete_option = true;
	}
	if(b_ic_po_line.compareTo(new BigDecimal("0")) > 0 && s_req_type.equals("C"))
	{
		hidden_delete_option = false;
	}
	
	
	boolean editInspections = false;
	
switch(s_req_status){
case DocumentStatus.REQ_PLANNING:
	if(HiltonUtility.ckNull(reqLine.getRequisitionerCode()).equals(uid))
		disableEdit = false;
	break;
case DocumentStatus.REQ_PLANNING_RECALLED:
	if(HiltonUtility.ckNull(reqLine.getRequisitionerCode()).equals(uid) || fpeUser || msrEngineer)
		disableEdit = false;
	break;
case DocumentStatus.REQ_PLANNING_REJECTED:
	if(HiltonUtility.ckNull(reqLine.getRequisitionerCode()).equals(uid) || fpeUser || msrEngineer)	
		disableEdit = false;
	break;
case DocumentStatus.REQ_PLANNING_APPROVING:
	if(fpeUser || msrEngineer)
		disableEdit = false;
	break;
}
%>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=reqLine.getRequisitionNumber()%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionLine_status" value="<%=s_req_line_status%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value="<%=b_req_ic_line%>"/>
<tsa:hidden name="RequisitionLine_lineNumber" value="<%=bd_line_number%>"/>
<tsa:hidden name="RequisitionLine_itemSource" value="<%=reqLine.getItemSource()%>"/>
<tsa:hidden name="RequisitionLine_blanketOrder" value="<%=reqLine.getBlanketOrder()%>"/>
<tsa:hidden name="RequisitionHeader_requiredBy" value="<%=request.getAttribute(\"RequisitionHeader_requiredBy\")%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="RequisitionHeader_requisitionerCode" value="<%=reqLine.getRequisitionerCode()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="<%=b_req_ic_line%>"/>
<tsa:hidden name="Account_accountType" value="RQL"/>
<tsa:hidden name="DocComment_icHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="DocComment_icLine" value="<%=b_req_ic_line%>"/>
<tsa:hidden name="InspectionHeader_icMsrLine" value="<%=b_ic_msr_line%>"/>
<tsa:hidden name="InspectionHeader_icInspNo" value="-1"/>
<tsa:hidden name="InspectionHeader_inspectType" value=""/>
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
<tsa:hidden name="reqaction" value=""/>
<tsa:hidden name="duplicateItem" value="FALSE"/>
<tsa:hidden name="showLinkGetItemInfo" value="<%=showLinkGetItemInfo%>"/>
<tsa:hidden name="Commodity_commodity" value=""/>
<tsa:hidden name="accountFLD2" id="accountFLD2" value=""/>
<tsa:hidden name="updateUdf10" value="N"/>
<tsa:hidden name="commodityUpdateAccount" value=""/>
<tsa:hidden name="newAccount_icHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="newAccount_icLine" value="<%=b_req_ic_line%>"/>
<tsa:hidden name="originalCommodityCode" value="<%=reqLine.getCommodityCode()%>"/>
<tsa:hidden name="currentPage" value="item"/>
<tsa:hidden name="editFieldsApprover" value=""/>

<tsa:hidden name="inspectionAction" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
	<%if (s_req_type.equals("M")) {%>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="req-type" defaultString="Req Type"/>&nbsp;<tsa:label labelName="req-line-item" defaultString="Line Item" />  <%=bd_line_number%> <tsa:label labelName="of" defaultString="of" /> <%=headerEncoder.encodeForHTML(s_line_count)%></div>
			</td>
		</tr>
	<% } else { %>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="req-line-item" defaultString="Line Item" />  <%=bd_line_number%> <tsa:label labelName="of" defaultString="of" /> <%=headerEncoder.encodeForHTML(s_line_count)%></div>
			</td>
		</tr>
	<% } %>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b><tsa:label labelName="req-requisition" defaultString="Requisition #" />:</b></td>
			<td width=150px><%=headerEncoder.encodeForHTML(s_req_number)%></td>
		</tr>
		<tr>
			<td align=right><b><tsa:label labelName="status" defaultString="Status" />:</b></td>
			<td width=150px><%=DocumentStatus.toString(s_req_status, oid)%></td>
		</tr>
		</table>
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
<%@ include file="/requests/req_info.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center valign=top>
		<table border=0 cellpadding=0 cellspacing=0 height=100% width=100%>
		<tr>
			<td>
				<table border=0 cellpadding=1 cellspacing=0 width=100%>
<%	if (reqLine.getStatus().compareTo(DocumentStatus.CANCELLED) == 0) { %>
				<tr>
					<td></td>
					<td class=error>* CANCELLED *</td>
					<td colspan=2></td>
				</tr>
<%	} else {	%>
				<tr>
					<td colspan="4"><br></td>
				</tr>
<%	} %>
				<tr>
					<td width=175px <%=HtmlWriter.isVisible(oid, "req-itemNumber")%> nowrap align=right><tsa:label labelName="req-itemNumber" defaultString="Item/Part #" checkRequired="true" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "req-itemNumber")%>><input type=text name="RequisitionLine_itemNumber" tabindex=1 size=25 maxlength=30 value="<%=reqLine.getItemNumber()%>" onchange="upperCase(this); setFieldsToDisplayByItem(); <% if (showLinkGetItemInfo.equalsIgnoreCase("N") && !oid.equals("SRR10P")) { %> getItemInfo(); <% } %> updated(); void(0);"></td>
				<% if (showLinkGetItemInfo.equalsIgnoreCase("Y") || (s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) < 0)) { %>
					<td <%=HtmlWriter.isVisible(oid, "req-itemNumber")%> nowrap><a href="javascript: getItemInfo(); void(0);" title="Click here to Get Item Info"><tsa:label labelName="getiteminfo" defaultString="Get Item # Info" checkRequired="true" /></a></td>
				<% } %>
					<td id="resetItem" <%=HtmlWriter.isVisible(oid, "req-itemNumber")%> nowrap><a href="javascript: resetLine(); void(0);" title="Click here to Reset the Line"><tsa:label labelName="resetLine" defaultString="Reset Item" checkRequired="true" /></a></td>
				</tr>
				<tr>
				<tr <%=HtmlWriter.isVisible(oid, "req-description")%>>
					<td width=175px nowrap align=right valign=top><tsa:label labelName="req-description" defaultString="Description" checkRequired="true" />:&nbsp;</td>
					<td colspan=4>
					<tsa:input type="textarea" name="RequisitionLine_description" tabIndex="2" rows="5" cols="60" onkeydown="textCounter(this, 2000);" onkeyup="textCounter(this,2000);" onchange="textCounter(this,2000);">${esapi:encodeForHTML(requisitionLine.description)}</tsa:input>
					</td>
				</tr>
				<tr>
					<td colspan="2" valign=top>
						<!-- left side -->
						<table border="0" cellpadding="0" cellspacing="1" width=100%>
						<% if (!oid.equals("SRR10P")) { %>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "req-asset")%> nowrap align=right><tsa:label labelName="req-asset" defaultString="Asset" />:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-asset")%>>
								<input type=checkbox name="c_checkbox" id="checkasset" tabindex=3 <% if (s_item_asset.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(frm.RequisitionLine_asset,0); updated();">
								<tsa:hidden name="RequisitionLine_asset" value="<%=s_item_asset%>"/>
							</td>
						</tr>
						<% } %>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "req-quantity")%> align=right nowrap><tsa:label labelName="req-quantity" defaultString="Quantity" checkRequired="true" />:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-quantity")%>><input type=text name="RequisitionLine_quantity" tabindex=5 size="20" maxlength=25 value="<%=bd_item_qty%>" style="text-align:right" onchange="getPriceBreak(); addUp(); checkQty(); updated();"><% if (breakCount > 0) {%>&nbsp;&nbsp;<a href="javascript: viewPriceBreaks(); void(0);" alt="View Price Breaks"><b>$</b></a><%}%></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-uom")%> align=right nowrap><A HREF="javascript: browseLookup('RequisitionLine_umCode', 'unitofmeasure'); void(0);" title="Click here to choose the <tsa:label labelName="req-uom" defaultString="UOM" /> for this item or enter the <tsa:label labelName="req-uom" defaultString="UOM" /> in box on the right." ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-uom", "UOM", s_req_type,true)%></a>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-uom")%>>
								<input type=text name="RequisitionLine_umCode" tabindex=6 size="20" maxlength=15 value="<%=reqLine.getUmCode()%>" onchange="upperCase(this); updateUMFactor(); updated();">
								<tsa:hidden name="RequisitionLine_umFactor" value="<%=bd_um_factor%>"/>
							</td>
						</tr>
<%				if ((s_req_type.equals("R") || s_req_type.equals("T")) && s_duomRequired.equalsIgnoreCase("Y")) { %>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "req-duomQuantity")%> align=right nowrap><tsa:label labelName="req-duomQuantity" defaultString="Secondary Quantity" checkRequired="true" />:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-duomQuantity")%>><input type=text name="RequisitionLine_duomQuantity" tabindex=5 size="20" maxlength=25 value="<%=bd_duomQuantity%>" style="text-align:right" onchange="addUp(); checkQty(); updated();"></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-duomUmCode")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-duomUmCode", "Secondary UOM", s_req_type,true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-duomUmCode")%>>
								<input type=text name="RequisitionLine_duomUmCode" tabindex=6 size="20" maxlength=15 value="<%=reqLine.getDuomUmCode()%>" onchange="upperCase(this); updated();" disabled>
							</td>
						</tr>
<% } %>

						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-unitCost")%> align=right nowrap title="Enter price. If unknown, enter estimate."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-unitCost", "Unit Cost", s_req_type,true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-unitCost")%>><input type=text name="RequisitionLine_unitPrice" tabindex=7 size="20" value="<%=b_item_unitprice%>" style="text-align:right" onchange="addUp(); updated();" onkeyup="checkDecimals(this, <%=s_price_decimals %>);" onblur="checkPrice(this);" <% if (b_udf3) { %>disabled<% } %>></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-extendedCost")%> align=right nowrap><tsa:label labelName="req-extendedCost" defaultString="Extended Cost" />:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-extendedCost")%>><input type=text name="computed_subtotal" tabindex=8 size="20" value="<%=b_subtotalString%>" style="text-align:right" DISABLED></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-commodity")%> align=right nowrap><a id="commodityItemBrowse" HREF="javascript: <% if (s_use_subcommodity.equalsIgnoreCase("Y")) {%>browseCommodity('RequisitionLine_commodityCode','subcommodity','<%=propertiesManager.getProperty("MISC", "SubCommodityType", "")%>'); <% } else {%>browseCommodityByType(); <%} %>void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-commodity", "Commodity")%> for this item or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-commodity", "Commodity")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-commodity", "Commodity", true)%></a>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-commodity")%>><input type=text name="RequisitionLine_commodityCode" id="RequisitionLine_commodityCode" tabindex=9 size=25 maxlength=15 value="<%=reqLine.getCommodityCode()%>" onchange="upperCase(this); updated(); getNewInfo('commodity', this); <% if(defaultByCommodity.equalsIgnoreCase("Y")) { %> updateReqLineAccount();<% } %>"></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-commodityName")%> align=right nowrap><tsa:label labelName="req-commodityName" defaultString="Commodity Name" />:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-commodityName")%>><input type=text name="as_commodityName" tabindex=10 size=25 maxlength=15 value="<%=CommodityManager.getInstance().getCommodityDescription(oid, reqLine.getCommodityCode())%>" onfocus="this.blur();" disabled></td>
						</tr>
						<%maxLength ="15";
						if (oid.equals("SRR10P")) {
						maxLength = "30";%>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-LN01")%> align=right nowrap>
								<% if (DictionaryManager.isLink(oid, "req-LN01")) { %>
									<a href="javascript: browseStd('RequisitionLine_udf1Code', 'LN01'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN01" defaultString="Line UDF 1" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN01" defaultString="Line UDF 1" checkRequired="true" /></a>:&nbsp;</td>
								<% } else { %>
									<tsa:label labelName="req-LN01" defaultString="Line UDF 1" checkRequired="false" />:&nbsp;
								<% } %>
							<td <%=HtmlWriter.isVisible(oid, "req-LN01")%>><input type=text id="RequisitionLine_udf1Code" name="RequisitionLine_udf1Code" tabindex=14 size="<%=maxLength%>" maxlength="<%=maxLength%>" value="<%=s_udf1%>" <%=HtmlWriter.isReadOnly(oid, "req-LN01") %> onchange="upperCase(this); updated();"></td>
						</tr>
						<% }%>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-ln-inventoryLocation")%> align=right nowrap><a href="javascript: browseLookup('RequisitionLine_itemLocation', 'item_location'); void(0);" title="Click here to choose the <tsa:label labelName="req-ln-inventoryLocation" defaultString="Inventory Location" /> for this item or enter the <tsa:label labelName="req-ln-inventoryLocation" defaultString="Inventory Location" /> in the box on the right."><tsa:label labelName="req-ln-inventoryLocation" defaultString="Inventory Location" checkRequired="true" />:</a>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-ln-inventoryLocation")%>><input type=text name="RequisitionLine_itemLocation" tabindex=10 size=25 maxlength=15 value="<%=headerEncoder.encodeForHTMLAttribute(reqLine.getItemLocation())%>" onchange="upperCase(this); updated();"></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "ln-req-receiptOptions")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-receiptOptions", "Receipt Options",true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ln-req-receiptOptions")%>>
								<select id="RequisitionLine_receiptRequired" tabindex=10 name="RequisitionLine_receiptRequired" size=1>
		<%	if (propertiesManager.getProperty("REC SELECTIONS", "X", "Y").equals("Y")) {%>
									<option <% if (s_receipt_required.equals("X") || HiltonUtility.isEmpty(s_receipt_required)) {%> SELECTED <%}%> value=""></option>
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
						</tr>
						<% if (!oid.equals("SRR10P")) {  %>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-requisitionerln")%> align=right nowrap><a href="javascript: browseRequisitionerCode(); void(0);" title="Click here to choose the <tsa:label labelName="req-requisitionerln" defaultString="Requisitioner" /> this item or enter the <tsa:label labelName="req-requisitionerln" defaultString="Requisitioner" /> in the box on the right."><tsa:label labelName="req-requisitionerln" defaultString="Requisitioner" checkRequired="true" />:</a>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-requisitionerln")%>><input type=text name="RequisitionLine_requisitionerCode" tabindex=11 size=25 maxlength=15 value="<%=reqLine.getRequisitionerCode()%>" onchange="upperCase(this); updated(); getNewInfo('requisitioner', this);"></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "ln-req-requisitionerName")%> align=right nowrap><tsa:label labelName="ln-req-requisitionerName" defaultString="Requisitioner Name" />:</a>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ln-req-requisitionerName")%>><input type=text name="as_requisitionerName" tabindex=10 size=25 maxlength=15 value="<%=UserManager.getInstance().getUser(oid, reqLine.getRequisitionerCode()).getDisplayName()%>" onfocus="this.blur();" disabled></td>
						</tr>
						<tr>
						<% if (DictionaryManager.isLink(oid, "req-departmentln")) { %>
							<td <%=HtmlWriter.isVisible(oid, "req-departmentln",s_req_type)%> align=right nowrap><a href="javascript: browseDepartmentCode(); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-departmentln", "Department",s_req_type, false)%> for this item or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-departmentln", "Department", s_req_type, true)%></a>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-departmentln", s_req_type)%>><input type=text name="RequisitionLine_departmentCode" tabindex=18 size=15 maxlength=15 value="<%=reqLine.getDepartmentCode()%>" onchange="upperCase(this); updated();" <%=HtmlWriter.isReadOnly(oid, "req-departmentln", s_req_type)%>></td>
						<% } else { %>
							<td <%=HtmlWriter.isVisible(oid, "req-departmentln",s_req_type)%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-departmentln", "Department", s_req_type, true)%></a>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-departmentln", s_req_type)%>><input type=text name="RequisitionLine_departmentCode" tabindex=18 size=15 maxlength=15 value="<%=reqLine.getDepartmentCode()%>" onchange="upperCase(this); updated();" <%=HtmlWriter.isReadOnly(oid, "req-departmentln", s_req_type)%>></td>
						<% } %>
				        </tr>
				        <tr>
							<td <%=HtmlWriter.isVisible(oid, "ln-req-requiredDate")%> align=right nowrap><tsa:label labelName="requiredDate" defaultString="Required Date" />:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ln-req-requiredDate")%>>
								<input type="text" name="RequisitionLine_requiredDate" tabindex=13 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(reqLine.getRequiredDate(), oid, userDateFormat)%>">
								<a href="javascript: show_calendar('RequisitionLine_requiredDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
							</td>
				        </tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-LN06")%> align=right nowrap>
							<div id="req-LN06">
							<% if(oid.equals("TTR09P")){ %>
								<tsa:label labelName="req-LN06" defaultString="Line UDF6" checkRequired="false" />:&nbsp;
							<%	}else{ %>
								<a href="javascript: browseUdf6Code(); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN06" defaultString="Line UDF6" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN06" defaultString="Line UDF6" checkRequired="true" /></a>:&nbsp;
							<%	}	%>
							</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "req-LN06")%>><div id="req-LN06-fld">
							<% if(oid.equals("TTR09P")){ %>
								<select name="RequisitionLine_udf6Code" tabIndex="17" style="width: 115px" value="<%=reqLine.getUdf1Code()%>" onchange="upperCase(this);updated();">
									<option value=""></option>
		          					<% for (int il = 0; il < chargeCodeList.size(); il++) {
										StdTable stdTable = (StdTable) chargeCodeList.get(il);
										StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
										<option value="<%=stdTablePK.getTableKey()%>" <%if (reqLine.getUdf6Code().equals(stdTablePK.getTableKey())) {%>selected<%}%>><%=stdTable.getTableKey()%></option>
		          					<% } %>
		          				</select>
							<%	}else{ %>
								<input type=text name="RequisitionLine_udf6Code" tabindex=17 size=15 maxlength=15 value="<%=reqLine.getUdf6Code()%>" onchange="upperCase(this); updated();">
							<%	}	%>
							</div></td>
						</tr>
				        <tr>
							<td <%=HtmlWriter.isVisible(oid, "req-LN08")%> align=right nowrap><a href="javascript: browseStd('RequisitionLine_udf8Code', 'LN08'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN08" defaultString="Line UDF8" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN08" defaultString="Line UDF8" checkRequired="true" /></a>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-LN08")%>><input type=text name="RequisitionLine_udf8Code" tabindex=17 size=15 maxlength=15 value="<%=reqLine.getUdf8Code()%>" onchange="upperCase(this); updated();"></td>
				        </tr>
				        <tr>
							<td <%=HtmlWriter.isVisible(oid, "req-LN10")%> align=right nowrap><a href="javascript: browseStd('RequisitionLine_udf10Code', 'LN10'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN10" defaultString="Line UDF10" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN10" defaultString="Line UDF10" checkRequired="true" /></a>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-LN10")%>><input type=text name="RequisitionLine_udf10Code" tabindex=17 size=15 maxlength=15 value="<%=reqLine.getUdf10Code()%>" onchange="upperCase(this); updated();"></td>
				        </tr>

				        <tr>
				        	<td <%=HtmlWriter.isVisible(oid, "req-shelfLife")%> nowrap align=right valign=middle><tsa:label labelName="req-shelfLife" defaultString="Shelf Life Required" />:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-shelfLife")%>>
								<input type=checkbox name="c_checkbox" tabindex=4 <% if (s_shelfLife.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(RequisitionLine_shelfLifeRqd, 1); updated();">
								<tsa:hidden name="RequisitionLine_shelfLifeRqd" value="<%=s_shelfLife%>"/>
							</td>
				        </tr>
				        <% } else {%>
				        <tr>
							<tsa:td id="ln10LabelRow" field="req-LN10" align="right" noWrap="nowrap">
			        		<tsa:label labelName="req-LN10" defaultString="Line UDF 10" checkRequired="true" noinstance="yes"/>:&nbsp;
			        		</tsa:td>
			       		 	<tsa:td id="ln10FieldRow" field="req-LN10">
			        		<tsa:input type="yesnoradio" title="" name="RequisitionLine_udf10Code" value="<%=s_udf10Code%>" disabled="disabled"/>
			        		</tsa:td>
			        	</tr>
				        <tr>
							<td <%=HtmlWriter.isVisible(oid, "req-supplierln")%> align=right nowrap><A HREF="javascript: browseSupplier('RequisitionLine_vendorId');" title="Click here to choose the value for <tsa:label labelName="req-supplierln" defaultString="Supplier" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-supplierln" defaultString="Supplier" checkRequired="true" /></a>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-supplierln")%>>
								<input type=text name="RequisitionLine_vendorId" tabindex="17" size="25" maxlength=15 value="<%=reqLine.getVendorId()%>" <% if (oid.equalsIgnoreCase("bly07p")) { %>onchange="upperCase(this); updated(); getNewInfo('vendor', this);"<% } else { %><%=HtmlWriter.isReadOnly(oid, "req_supplierln")%><% } %>>
							</td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "ln-req-vendorName")%> align=right nowrap><tsa:label labelName="req-vendorName" defaultString="Vendor Name" checkRequired="true" />:</a>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ln-req-vendorName")%>>
								<input type=text name="RequisitionLine_vendorName" size=25 value="<%=reqLine.getVendorName()%>" onfocus="this.blur();" disabled>
							</td>
						</tr>
			<!-- 	        <tr>
							<td <%=HtmlWriter.isVisible(oid, "ln-req-requiredDate")%> align=right nowrap><tsa:label labelName="requiredDate" defaultString="Required Date" />:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ln-req-requiredDate")%>>
								<input type="text" name="RequisitionLine_requiredDate" tabindex=13 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(reqLine.getRequiredDate(), oid, userDateFormat)%>">
								<a href="javascript: show_calendar('RequisitionLine_requiredDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
							</td>
				        </tr>
			-->	        <% } %>
				        <tr <%=HtmlWriter.isVisible(oid, "req-chemicalnumberln")%>>
							<td <%=HtmlWriter.isVisible(oid, "req-chemicalnumberln")%> align=right nowrap>
								<tsa:label labelName="req-chemicalnumberln" defaultString="Chemical item Number" checkRequired="true" />:&nbsp;
							</td>
							<td <%=HtmlWriter.isVisible(oid, "req-chemicalnumberln")%>><input type=text name="RequisitionLine_chemicalItemNumber" tabindex=12 size=15 maxlength=30 value="<%=reqLine.getChemicalItemNumber()%>" onchange="upperCase(this); updated();"></td>
				        </tr>
						</table>
						<!-- left side end -->
					</td>
					<td colspan="2" valign=top>
						<!-- right side -->
						<table border="0" cellpadding="0" cellspacing="1" width=100%>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-taxable")%> nowrap align=right valign=middle><tsa:label labelName="req-taxable" defaultString="Taxable" />:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-taxable")%>>
								<input type=checkbox name="c_checkbox" tabindex=4 <% if (s_item_taxable.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(RequisitionLine_taxable, 2); updated();">
								<tsa:hidden name="RequisitionLine_taxable" value="<%=s_item_taxable%>"/>
							</td>
						</tr>
						<% if (!oid.equals("SRR10P")) {  %>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-supplierln")%> align=right nowrap><A HREF="javascript: browseSupplier('RequisitionLine_vendorId');" title="Click here to choose the value for <tsa:label labelName="req-supplierln" defaultString="Supplier" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-supplierln" defaultString="Supplier" checkRequired="true" /></a>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-supplierln")%>>
								<input type=text name="RequisitionLine_vendorId" tabindex="17" size="25" maxlength=15 value="<%=reqLine.getVendorId()%>" <% if (oid.equalsIgnoreCase("bly07p")) { %>onchange="upperCase(this); updated(); getNewInfo('vendor', this);"<% } else { %><%=HtmlWriter.isReadOnly(oid, "req_supplierln")%><% } %>>
							</td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "ln-req-vendorName")%> align=right nowrap><tsa:label labelName="req-vendorName" defaultString="Vendor Name" checkRequired="true" />:</a>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ln-req-vendorName")%>>
								<input type=text name="RequisitionLine_vendorName" size=25 value="<%=reqLine.getVendorName()%>" onfocus="this.blur();" disabled>
							</td>
						</tr>
						<tr>
		   					<td <%=HtmlWriter.isVisible(oid, "req-ln-supplier")%> align=right nowrap><A HREF="javascript: browseContactAddress('RequisitionLine_vendContactCode', frm.RequisitionLine_vendorId.value);" title="Click here to choose the value for <tsa:label labelName="req-ln-supplier" defaultString="Supplier Contact" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-ln-supplier" defaultString="Supplier Contact" checkRequired="true" /></a>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-ln-supplier")%>>
								<input type=text name="RequisitionLine_vendContactCode" size=25 value="<%=reqLine.getVendContactCode()%>" <%=HtmlWriter.isReadOnly(oid, "req-ln-supplier")%>>
							</td>
						</tr>
						<% } %>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-catalog")%> align=right nowrap><tsa:label labelName="req-catalog" defaultString="Catalog" checkRequired="true" />:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-catalog")%>><input type=text name="RequisitionLine_catalogId" size=15 value="<%=reqLine.getCatalogId()%>" READONLY DISABLED></td>
						</tr>
						<% if (oid.equals("SRR10P")) { %>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-modelNumber")%> align=right nowrap>
								<tsa:label labelName="req-modelNumber" defaultString="Model Number" checkRequired="true" />:&nbsp;
							</td>
							<td <%=HtmlWriter.isVisible(oid, "req-modelNumber")%>><input type=text name="RequisitionLine_modelNumber" tabindex=12 size=15 maxlength=20 value="<%=reqLine.getModelNumber()%>" onchange="upperCase(this); updated();"></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-manufacturer")%> align=right nowrap>
								<tsa:label labelName="req-manufacturer" defaultString="Manufacturer" checkRequired="true" />:&nbsp;
							</td>
							<td <%=HtmlWriter.isVisible(oid, "req-manufacturer")%>><input type=text name="RequisitionLine_mfgName" tabindex=13 size=15 maxlength=25 value="<%=reqLine.getMfgName()%>" onchange="upperCase(this); updated();"></td>
						</tr>
						<% } else { %>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-manufacturer")%> align=right nowrap>
								<tsa:label labelName="req-manufacturer" defaultString="Manufacturer" checkRequired="true" />:&nbsp;
							</td>
							<td <%=HtmlWriter.isVisible(oid, "req-manufacturer")%>><input type=text name="RequisitionLine_mfgName" tabindex=13 size=15 maxlength=25 value="<%=reqLine.getMfgName()%>" onchange="upperCase(this); updated();"></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-modelNumber")%> align=right nowrap>
								<tsa:label labelName="req-modelNumber" defaultString="Model Number" checkRequired="true" />:&nbsp;
							</td>
							<td <%=HtmlWriter.isVisible(oid, "req-modelNumber")%>><input type=text name="RequisitionLine_modelNumber" tabindex=12 size=15 maxlength=20 value="<%=reqLine.getModelNumber()%>" onchange="upperCase(this); updated();"></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-LN01")%> align=right nowrap>
								<% if (DictionaryManager.isLink(oid, "req-LN01")) { %>
									<a href="javascript: browseStd('RequisitionLine_udf1Code', 'LN01'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN01" defaultString="Line UDF 1" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN01" defaultString="Line UDF 1" checkRequired="true" /></a>:&nbsp;</td>
								<% } else { %>
									<tsa:label labelName="req-LN01" defaultString="Line UDF 1" checkRequired="false" />:&nbsp;
								<% } %>
							<td <%=HtmlWriter.isVisible(oid, "req-LN01")%>><input type=text id="RequisitionLine_udf1Code" name="RequisitionLine_udf1Code" tabindex=14 size=15 maxlength=15 value="<%=s_udf1%>" <%=HtmlWriter.isReadOnly(oid, "req-LN01") %> onchange="upperCase(this); updated();"></td>
						</tr>
						<% }  %>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-LN02")%> align=right nowrap>
							<% if (DictionaryManager.isLink(oid, "req-LN02")) { %>
								<% if (fdcsEnabled) { %>
									<a href="javascript: browseFdcsWOElements('FdcWo_RequisitionLine_udf2Code'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN02" defaultString="Line UDF2" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN02" defaultString="Line UDF2" checkRequired="true" /></a>:&nbsp;
								<% } else if(oid.equals("SRR10P")){ %>
									<a href="javascript: browseStd('RequisitionLine_udf2Code', 'PROCLVL'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN02" defaultString="Line UDF2" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN02" defaultString="Line UDF2" checkRequired="true" /></a>:&nbsp;
								<% } else { %>
									<a href="javascript: browseStd('RequisitionLine_udf2Code', 'LN02'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN02" defaultString="Line UDF2" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN02" defaultString="Line UDF2" checkRequired="true" /></a>:&nbsp;
								<% } %>
							<% } else { %>
								<tsa:label labelName="req-LN02" defaultString="Line UDF 2" checkRequired="false" />:&nbsp;
							<% } %>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "req-LN02")%>><input type=text name="RequisitionLine_udf2Code" tabindex=15 size=15 maxlength=15 value="<%=reqLine.getUdf2Code()%>" onchange="upperCase(this); updated();" /> </td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-LN03")%> align=right nowrap>
							<% if (DictionaryManager.isLink(oid, "req-LN03")) { %>
								<% if (fdcsEnabled) { %>
									<a href="javascript: browseFdcsWOElements('FdcWo_RequisitionLine_udf3Code'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN03" defaultString="Line UDF3" checkRequired="true" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN03" defaultString="Line UDF3" checkRequired="true" /></a>:&nbsp;
								<% } else { %>
									<a href="javascript: browseStdUdf3(); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN03" defaultString="Line UDF3" checkRequired="true" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN03" defaultString="Line UDF3" checkRequired="true" /></a>:&nbsp;
								<% } %>
							<% } else { %>
								<tsa:label labelName="req-LN03" defaultString="Line UDF 3" checkRequired="true" />:&nbsp;
							<% } %>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "req-LN03")%>><tsa:input type="dropdown" name="RequisitionLine_udf3Code"  size="15" value="<%=reqLine.getUdf3Code()%>" labelName="req-LN03" onchange="upperCase(this); updated();"/></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "req-LN04")%> align="right" nowrap>
								<% if (fdcsEnabled) { %>
								<a href="javascript: browseFdcsWOElements('FdcWo_RequisitionLine_udf4Code'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN04" defaultString="Line UDF4" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN04" defaultString="Line UDF4" checkRequired="true" /></a>:&nbsp;</td>
								<% } else { %>
								<a href="javascript: browseStd('RequisitionLine_udf4Code', 'LN04'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN04" defaultString="Line UDF4" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN04" defaultString="Line UDF4" checkRequired="true" /></a>:&nbsp;</td>
								<% } %>

							<td <%=HtmlWriter.isVisible(oid, "req-LN04")%> ><input type=text name="RequisitionLine_udf4Code" tabindex=17 size=15 maxlength=15 value="<%=reqLine.getUdf4Code()%>" onchange="upperCase(this); updated();"></td>
				        </tr>
				        <tr>
							<tsa:td field="req_LN05" align="right" noWrap="nowrap"><tsa:label labelName="req-LN05" defaultString="Line UDF5" checkRequired="true" />:&nbsp;</tsa:td>
							<tsa:td field="req-LN05"><div id="req-LN05-fld"><tsa:input type="dropdown" name="RequisitionLine_udf5Code" size="15" value="<%=reqLine.getUdf5Code()%>" labelName="req-LN05" onchange="upperCase(this); updated();"/></div></tsa:td>
				        </tr>
				        <% if (oid.equals("SRR10P")) { %>
				        <tr>
							<td <%=HtmlWriter.isVisible(oid, "req-LN06")%> align=right nowrap>
							<div id="req-LN06">
							<% if (DictionaryManager.isLink(oid, "req-LN06")) { %>
								<a href="javascript: browseUdf6Code(); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN06" defaultString="Line UDF6" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN06" defaultString="Line UDF6" checkRequired="true" /></a>:&nbsp;
							<% } else { %>
								<tsa:label labelName="req-LN06" defaultString="Line UDF6" checkRequired="true" />:&nbsp;
							<% } %>
							</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "req-LN06")%>><div id="req-LN06-fld">
								<tsa:input type="dropdown" name="RequisitionLine_udf6Code"  size="15" value="<%=reqLine.getUdf6Code()%>" labelName="req-LN06" onchange="upperCase(this); updated();"/>
							</div></td>
						</tr>
						<% } %>
						<tr>
						<% if (DictionaryManager.isLink(oid,"req-LN07")) { %>
							<td <%=HtmlWriter.isVisible(oid, "req-LN07")%> align=right nowrap><a href="javascript: browseStd('RequisitionLine_udf7Code', 'LN07'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN07" defaultString="Line UDF7" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN07" defaultString="Line UDF7" checkRequired="true" /></a>:&nbsp;</td>
						<% } else { %>
						<td align="right" nowrap><tsa:label labelName="req-LN07" defaultString="Line UDF7" checkRequired="true" />:&nbsp;</td>
						<% } %>
							<td <%=HtmlWriter.isVisible(oid, "req-LN07")%>><tsa:input type="dropdown" name="RequisitionLine_udf7Code" size="15" value="<%=reqLine.getUdf7Code()%>" labelName="req-LN07" onchange="upperCase(this); updated();"/></td>
						</tr>
						<% if (oid.equals("SRR10P")) { %>
							<tr>
					        <% if (DictionaryManager.isLink(oid,"req-LN08")) { %>
								<td <%=HtmlWriter.isVisible(oid, "req-LN08")%> align=right nowrap><a href="javascript: browseStd('RequisitionLine_udf8Code', 'LN08'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN08" defaultString="Line UDF8" checkRequired="false" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN08" defaultString="Line UDF8" checkRequired="true" /></a>:&nbsp;</td>
							<% } else { %>
								<td <%=HtmlWriter.isVisible(oid, "req-LN08")%> align=right nowrap>
								<tsa:label labelName="req-LN08" defaultString="Line UDF8" checkRequired="true" />
								:&nbsp;</td>
							<% } %>
								<td <%=HtmlWriter.isVisible(oid, "req-LN08")%>><div id="req-LN08-fld">
									<tsa:input type="dropdown" name="RequisitionLine_udf8Code"  size="15" value="<%=reqLine.getUdf8Code()%>" labelName="req-LN08" onchange="upperCase(this); updated();"/>
								</div></td>
							</tr>
						<% } %>
				        <tr>
							<tsa:td field="req-LN09" align="right" noWrap="nowrap">
							<tsa:label labelName="req-LN09" defaultString="Line UDF9" checkRequired="true" />
							:&nbsp;</tsa:td>
							<tsa:td field="req-LN09" >
								<tsa:input type="mintext" name="RequisitionLine_udf9Code" tabIndex="17" maxLength="15" value="<%=reqLine.getUdf9Code()%>" onchange="upperCase(this); updated();"/>
							</tsa:td>
				        </tr>
				        <% if (oid.equals("SRR10P")) { %>
				        <tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "req-asset")%> nowrap align=right><tsa:label labelName="req-asset" defaultString="Asset" />:&nbsp;</td>
							<tsa:td field="req-asset" >
								<tsa:input type="dropdown" name="RequisitionLine_asset" tabIndex="3" maxLength="15" value="<%=reqLine.getAsset()%>" labelName="req-Asset" onchange="upperCase(this); updated();"/>
							</tsa:td>
						</tr>
						<tr>
				        	<td <%=HtmlWriter.isVisible(oid, "req-shelfLife")%> nowrap align=right valign=middle><tsa:label labelName="req-shelfLife" defaultString="Shelf Life Required" />:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-shelfLife")%>>
								<input type=checkbox name="c_checkbox" id="checkshelfLifeRqd" tabindex=4 <% if (s_shelfLife.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(RequisitionLine_shelfLifeRqd, 1); updated();">
								<tsa:hidden name="RequisitionLine_shelfLifeRqd" value="<%=s_shelfLife%>"/>
							</td>
				        </tr>
				        <% } %>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan=4>
						<table border=0 cellpadding=1 cellspacing=0>
						<% if (reqLine.getStatus().compareTo(DocumentStatus.CANCELLED) == 0) { %>
						<tr>
							<td></td>
							<td class=error>* CANCELLED *</td>
							<td colspan=2></td>
						</tr>
						<% } else if (oid.equals("BSC04P") && (reqLine.getUmCode()).equals("RM") && (reqLine.getCatalogId()).equals("LETTERHEAD")) { %>
						<tr>
							<td></td>
							<td class=error colspan=3>*Note: 1 Ream (RM) contains 500 sheets of paper.</td>
						</tr>
		<%			} else if (oid.equals("BSC04P") && (reqLine.getUmCode()).equals("BX") && (reqLine.getCatalogId()).equals("ENVELOPE")) { %>
						<tr>
							<td></td>
							<td class=error colspan=3>*Note: Minimum Order 2 boxes. Each Box (BX) contains 500 envelopes.</td>
						</tr>
		<%			} else {	%>
						<tr>
							<td colspan="4"><br></td>
						</tr>
		<%			} %>
						</table>
						<!-- right side end -->
					</td>
				</tr>
				</table>
		  </td>
		</tr>
		</table>
	</td>
	<td align=right width=220px valign=top>
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
				&nbsp;<a href="javascript: retrieveLine(<%=i%>); void(0);" ><%=i%></a>
<%		}
		}
	} %>
		<table border=0 cellpadding=2 cellspacing=0>
		<tr>
			<td>
				<table border=0 cellpadding=2 cellspacing=0>
				<tr>
					<td nowrap><b><tsa:label labelName="itemStatus" defaultString="Item Status" />:</b></td>
					<td nowrap><%=DocumentStatus.toString(reqLine.getStatus(), oid)%></td>
				</tr>
				</table>
		<table border=1 cellpadding=0 cellspacing=0>
		<tr><td class=browseHdr><tsa:label labelName="itemOptions" defaultString="Item Options" /></td></tr>
		<tr>
			<td class=browseRow>
				<table border=0 cellpadding=0 cellspacing=0 width=225px>
<%		if (s_req_type.equals("C") || (s_req_status.equals(DocumentStatus.REQ_INPROGRESS) || s_req_status.equals(DocumentStatus.REQ_REJECTED) ||
			s_req_status.equals(DocumentStatus.TEMPLATE) || s_req_status.equals(DocumentStatus.TEMPLATE) || s_req_status.equals(DocumentStatus.REQ_RECALLED) || s_req_status.equals(DocumentStatus.REQ_PLANNING))) {%>
				<tr height=15px><td nowrap align=center><a href="javascript: spell('frm.RequisitionLine_description.value'); void(0);"><tsa:label labelName="checkSpelling" defaultString="Check Spelling" /></a></td></tr>
	<% 		if (!s_req_status.equals(DocumentStatus.REQ_INPROGRESS)) {
				if (hidden_delete_option) { %>
					<tr height=15px>
					<td nowrap align=center>
<%					if (s_view.equalsIgnoreCase("CLASSIC")) { %>
						<a href="javascript: doSubmit('/requests/req_summary.jsp', 'RequisitionLineDelete;RequisitionRetrieve');" ONCLICK="if (verifyAction('Are you sure you want to delete this item?')) { return totalsAlert(); } else { return false; }">
<%					} else if (s_view.equalsIgnoreCase("WIZARD")) { %>
						<a href="javascript: doSubmit('<%=s_return_page%>', 'RequisitionLineDelete;<%=s_return_method%>');" ONCLICK="if (verifyAction('Are you sure you want to delete this item?')) { return totalsAlert(); } else { return false; }">
<%					} %>
						<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0>&nbsp;<tsa:label labelName="deleteItem" defaultString="Delete Item" />
						</a>
					</td>
					</tr>
	<% 			}
			} %>

<%		}
if (s_req_type.equals("M")) {
	if ((reqLine.getStatus().compareTo(DocumentStatus.REQ_PLANNING_APPROVING) < 0)  || (reqLine.getStatus().compareTo(DocumentStatus.REQ_PLANNING_SOURCED) < 0 && fpeUser)) {
%>
				<tr height=15px>
					<td nowrap align=center>
						<a href="javascript: frm.reqaction.value='cancel'; doSubmit('/requests/req_item_cancel_close.jsp','RequisitionLineRetrieve');" ONCLICK="return verifyAction('Cancel line <%=bd_line_number%>?');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cancelItem", "Cancel Item")%></a>
					</td>
				</tr>
<%}
} else {
 if ( (reqLine.getStatus().compareTo(DocumentStatus.REQ_APPROVED) <= 0) || (s_req_type.equals("C") && reqLine.getStatus().compareTo(DocumentStatus.REQ_INPROGRESS) == 0) ) {
%>
				<tr height=15px>
					<td nowrap align=center>
						<a href="javascript: frm.reqaction.value='cancel'; doSubmit('/requests/req_item_cancel_close.jsp','RequisitionLineRetrieve');" ONCLICK="return verifyAction('Cancel line <%=bd_line_number%>?');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cancelItem", "Cancel Item")%></a>
					</td>
				</tr>
<%	}
}
		if (!s_req_number.equals("N/A") && !s_req_number.equals("TEMPLATE")) { %>
				<tr height=15px>
					<td nowrap align=center>&nbsp;<A HREF="javascript: viewItemHistory(); void(0);"><tsa:label labelName="itemHistory" defaultString="Item History" /></A></TD>
				</tr>
<%	} %>
				<tr height=15px <%=HtmlWriter.isVisible(oid, "req-itemdescription-alttext")%> >
					<td nowrap align=center>&nbsp;<a href="javascript: viewAltLanguages(); void(0);"><tsa:label labelName="req-itemdescription-alttext" defaultString="Alternate Language Descriptions" checkRequired="true" /></a>&nbsp;</td>
				</tr>
<%if (s_req_type.equals("M")) {
	if ((reqLine.getStatus().compareTo(DocumentStatus.REQ_PLANNING_APPROVING) < 0)  || (reqLine.getStatus().compareTo(DocumentStatus.REQ_PLANNING_SOURCED) < 0 && fpeUser)) {
%>
				<tr height=15px>
					<td nowrap align=center>
						<a href="javascript: frm.reqaction.value='validateLine'; doSubmit('/requests/req_item_validation.jsp','RequisitionLineUpdate;RequisitionLineValidate');" ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "validateItem", "Validate Item")%></a>
					</td>
				</tr>
<%}
} %>
				</table>
<%	if (!s_req_type.equals("R")) {
			if ((s_req_type.compareTo("M") != 0 && s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0 || s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVING) < 0)) {
				if (!s_req_type.equals("S") && !s_req_type.equals("I") && !s_req_type.equals("T")) {%>
				<table border=0 cellpadding=0 cellspacing=0 width=225px>
				<tr height=15px>
					<td nowrap align=center>
						<table border=0 cellpadding=0 cellspacing=0>
				<%  if (propertiesManager.getProperty("REQ OPTIONS", "RestrictNonStandardItem", "N").equalsIgnoreCase("N")) { %>
						<tr>
							<td align=right><a href="javascript: addItem(); void(0);"><img src="<%=contextPath%>/images/cmd_add_item.gif" border=0 alt="Add Non-Standard/Non-Catalog Item"></a></td>
							<td nowrap>&nbsp;<a href="javascript: addItem(); void(0);" title="All services and any goods that are not listed in a catalog"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addnonstditem", "Add Non-Standard Item", s_req_type, false)%></a></td>
						</tr>
				<%	}%>
						<tr>
							<td nowrap colspan="2" align="center">&nbsp;<a href="javascript: addItem('duplicate'); void(0);" title="Click here to add a duplicate of this item"><tsa:label labelName="req-duplicateItem" defaultString="Duplicate Item" checkRequired="false" /></a></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
<%			}%>
				<table width=100%>
				<tr height=15px>
					<td nowrap align=center>&nbsp;<a href="javascript: toggleDetailsDisplay('addItem', 'addItemOptions'); void(0);"><tsa:label labelName="itemSearchOptions" defaultString="Item Search Options" /></a></td>
				</tr>
				</table>

				<div id="addItem" name="addItem" style="display:none;">
				<table border=0 width=225px cellpadding=1>
				<tr>
					<td align=right>
						<tsa:hidden name="createAction" value="SAVE"/>
						<table border=0 cellspacing=0 cellpadding=1>
						<tr <%=HtmlWriter.isVisible(oid, "req-itemNumber")%>>
							<td valign=middle align=right nowrap><b><tsa:label labelName="req-itemNumber" defaultString="Item/Part #" />:</b></td>
							<td valign=middle><input type=text name=as_itemNumber value="" size=15 tabindex=20></td>
						</tr>
						<tr>
							<td valign=middle align=right nowrap><b>Keyword(s):</b></td>
							<td valign=middle><input type=text name="as_keywords" value="" size=17 tabindex=21></td>
							<td valign=middle><div id="pxmediumbutton"><a href="javascript: itemSearch(); void(0);">Search</a></div></td>
						</tr>
						</table>
						<%	if (propertiesManager.isModuleActive("Extended Inventory") || propertiesManager.isModuleActive("Standard Inventory")) {%>
						<div id="itemtype" style="visibility:visible; display:block;">
						<table border=0 cellspacing=0 cellpadding=1>
						<tr>
							<td valign=middle align=right><input type=radio name="as_item_type" tabindex=23 value="CAT" CHECKED></td>
							<td valign=middle nowrap>Catalog</td>
							<td valign=middle align=right><input type=radio name="as_item_type" tabindex=24 value="INV"></td>
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
						<td valign="middle"><a href="javascript: doSubmit('/browse/item_filter_options.jsp', 'RequisitionLineUpdate;CatalogRetrieveActive'); void(0);"><img src="<%=contextPath%>/images/cmd_search_item.gif" alt="Advanced Item Search" border=0></a>&nbsp;</td>
						<td valign=middle><a href="javascript: doSubmit('/browse/item_filter_options.jsp', 'RequisitionLineUpdate;CatalogRetrieveActive'); void(0);">Advanced Item Search</a></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
				</div>
				<% if (oid.equalsIgnoreCase("bly07p")){%>
					<table border=0 cellpadding=0 cellspacing=0 width=60% align=center>
					<tr height=15px>
						<tsa:hidden name="docType" value="RQ"/>
						<td><a href="javascript: doSubmit('/requests/req_item.jsp', 'RequisitionLineUpdate;CatalogItemUpdateFromLine'); void(0);"><img src="<%=contextPath%>/images/save.gif" alt="Update Vendors Price" border=0></a></td>
						<td><a href="javascript: doSubmit('/requests/req_item.jsp', 'RequisitionLineUpdate;CatalogItemUpdateFromLine'); void(0);"><tsa:label labelName="updateCatalogItem" defaultString="Update Catalog Item" /></a></td>
					</tr>
					</table>
				<%} %>

			<% }
		} %>
			</td>
		</tr>
		</table>

		</tr>
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
<%
if (oid.equalsIgnoreCase("vse06p"))
{
%>
<br>
<table <%=HtmlWriter.isVisible(oid, "req-bav-details")%> border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<td>
	&nbsp;&nbsp;<a href="javascript: toggleDetailsDisplay('bavDetailInfo', 'bavDetailOptions'); void(0);" tabIndex="-1"><tsa:label labelName="bavDetails" defaultString="BAV (Only) Details" /></a>
</td>
</tr>
</table>

<div id="bavDetailInfo" name="bavDetailInfo" style="visibility:hidden; display:none">
<br>
<table <%=HtmlWriter.isVisible(oid, "req-additional-info")%> border=0 cellpadding=0 cellspacing=0 width=680px>
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
				<tr <%=HtmlWriter.isVisible(oid, "req-LNMEMO")%>>
					<td nowrap align=left valign=top><tsa:label labelName="req-LNMEMO" defaultString="Notes" checkRequired="true" />:</td>
				</tr>

				<tr <%=HtmlWriter.isVisible(oid, "req-LNMEMO")%>>
					<td colspan=4><textarea wrap="virtual" name="RequsitionLine_memoLine" tabindex="48" rows=4 cols=60 onKeyDown="textCounter(this, 255);" onKeyUp="textCounter(this,255);" onchange="upperCase(this); textCounter(this,255); updated();">
					${esapi:encodeForHTML(reqLine.memoLine)}
					</textarea></td>
				</tr>
			</table>
		</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="2" width="50%">
			<tr>
					<td <%=HtmlWriter.isVisible(oid, "req-LN06")%> align=right nowrap>
						<% if (DictionaryManager.isLink(oid, "req-LN06")) { %>
							<a href="javascript: browseStd('RequisitionLine_udf6Code', 'LN06'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN06" defaultString="Line UDF 6" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN06" defaultString="Line UDF 6" checkRequired="true" /></a>:
						<% } else { %>
							<tsa:label labelName="req-LN06" defaultString="Line UDF 6" checkRequired="true" />:
						<% } %>
					</td>

					<td <%=HtmlWriter.isVisible(oid, "req-LN06")%>>
						<select tabindex="49" name="RequisitionLine_udf6Code" size=1>
							<option <% if (s_udf6.equals("N")) {%> SELECTED <%}%> value=""></option>
							<option <% if (s_udf6.equals("O")) { %> selected <% } %> value="O">OUTFITTING</option>
							<option <% if (s_udf6.equals("I")) { %> selected <% } %> value="I">INDUSTRIAL</option>
						</select>
					</td>
			</tr>
			<tr>
					<td <%=HtmlWriter.isVisible(oid, "req-LN07")%> align=right nowrap>
					<% if (DictionaryManager.isLink(oid, "req-LN07")) { %>
						<a href="javascript: browseStd('RequisitionLine_udf7Code', 'LN07'); void(0);" title="Click here to choose the value for <tsa:label labelName="req-LN07" defaultString="Line UDF 7" /> for this item or enter the value in the box on the right."><tsa:label labelName="req-LN07" defaultString="Line UDF 7" checkRequired="true" /></a>:
					<% } else { %>
						<tsa:label labelName="req-LN07" defaultString="Line UDF 7" checkRequired="true" />:
					<% } %>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "req-LN07")%>><input type=text name="RequisitionLine_udf7Code" tabindex="50" size=15 maxlength=15 value="<%=reqLine.getUdf7Code()%>" onchange="upperCase(this); updated();"></td>		</tr>
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
<%List shipToList = (List) reqLine.getShipToList();%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=50% align=center valign=top>
			  <table <%=HtmlWriter.isVisible(oid, "itemShippingSchedule")%> border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
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
			  <table <%=HtmlWriter.isVisible(oid, "req-itemBillingSchedule")%> id="billingTable" border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
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
						<%-- table border=0 cellspacing=0 cellpadding=2 width=210px class=browseRow>
    						<tr>
    							<td class=browseRow height=12px nowrap><b><%=s_bil_code%></b></td>
    							<td class=browseRow width=75px nowrap><b>QTY:</b>&nbsp;<%=HiltonUtility.getFormattedQuantity(billTo.getQuantity(), oid)%></td>
    						</tr>
						</table--%>
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
<tr><td><br></td></tr>
</table>

<table  border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<% if (s_req_type.equals("M")) { %>
					<td width=2% height=18px class=browseHdr><a href="javascript: if (validateItem()) { createInspectionDetail(); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></td></a>
					<% } %>
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
			  <td width=33% class=browseRow id="inspectionLine"><a href="javascript: openInspectionDetail('<%=ih.getComp_id().getIcInspNo() %>'); void(0);" title="Click here to Get Item Info"><%=inspTypeDesc%></a></td>
				<td width=20% class=browseRow align=right></td>
				<td width=20% class=browseRow align=right></td>
				<td width=20% class=browseRow align=right><%=DocumentStatus.toString(s_req_line_status , oid)%></td>
				<% if(editInspections) { %>
				<td width=5% align=center height=18px class=browseRow><a href="javascript: if (validateItem()) { deleteInspectionDetail('<%=ih.getComp_id().getIcInspNo() %>','<%=inspTypeDesc %>'); } void(0);"><img id='delete' valign='baseline' src='<%=contextPath%>/images/delete.gif'  border=0 alt="Delete"></a></td>
				<% } else { %>
				<td width=5% height=18px class=browseRow>&nbsp;</td>
				<% } %>
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

<table <%=HtmlWriter.isVisible(oid, "req-itemAccounts")%> border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height=18px class=browseHdr><a href="javascript: if (validateItem()) { retrieveAccounts(); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></td></a>
					<td width=68% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="account" defaultString="Account" /></b></td>
					<td width=15% height=18px class=browseHdr align=right>&nbsp;<b><tsa:label labelName="percentAlloc" defaultString="Percent Alloc" />.</b></td>
					<td width=15% height=18px class=browseHdr align=right>&nbsp;<b><tsa:label labelName="amountAlloc" defaultString="Amount Alloc" />.</b></td>
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

			int index = 0;
			for (int j = 0; j <15; j++)
			{
				if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
				{
					if (s_account.length() > 0)
					{
						s_account = s_account + s_account_separator + s_accArray[j];
						%>
						<tsa:hidden name="accFld<%=index + 1 %>" value="<%= s_accArray[j]%>"/>
						<%
					}
					else
					{
						s_account = s_accArray[j];
						%>
						<tsa:hidden name="accFld<%=index + 1 %>" value="<%= s_accArray[j]%>"/>
						<%
					}
					index++;
				}
			}
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
				  <td width=70% class=browseRow id="accountLine"><%=headerEncoder.encodeForHTML(s_account)%></td>
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
<tr><td><br></td></tr>
</table>

<%List commentList = (List) reqLine.getDocCommentList();%>
<table <%=HtmlWriter.isVisible(oid, "req-itemComments")%> border=0 cellpadding=0 cellspacing=0 width=680px>
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
					<td width=66% class=browseRow><%=headerEncoder.encodeForHTML(s_title)%></td>
					<td width=8% class=browseRow align=center valign=top><%=headerEncoder.encodeForHTML(s_print)%></td>
					<td width=7% class=browseRow align=center valign=top><%=headerEncoder.encodeForHTML(s_bold)%></td>
					<td width=16% class=browseRow align=center valign=top><%=headerEncoder.encodeForHTML(s_place)%></td>
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
				<tr><td><b><tsa:label labelName="thereAreNoAccountsRequisition" defaultString="There are no comments for this item" />.</b></td></tr>
				</table>
<%}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr><td><br></td></tr>
</table>


<!-- Desde aqui es el add para el Attachment -->
<%
List attachmentList = (List) request.getAttribute("docAttachmentList");
%>

<table <%=HtmlWriter.isVisible(oid, "req-itemAttachments")%> border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height=18px class=browseHdr><a href="javascript: if (validateItem()) { doSubmit('/requests/req_line_attachments.jsp', 'RequisitionLineUpdate;DocAttachmentRetrieveByLine'); } void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
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

						<input type="hidden" name="docTitle" value = "<%=docAttachment.getDocFilename()%>" maxLength=60 size=60>
						<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);" title="Click here to Open the file"><%=docAttachment.getDocTitle()%></a>
						</td>
						<td width=5% valign=middle align=center>
							<input type=hidden name="docFilename" value = "<%=filename%>">
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


<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionLineUpdate;RequisitionRetrieve'); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionRetrieve'); void(0);">Return</a></div></td>
<%	}
		else if (s_view.equalsIgnoreCase("WIZARD"))
		{
%>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=s_return_page%>', '<%=s_return_method%>'); void(0);">Return</a></div></td>
<%	} %>
</tr>
<tr><td><br></td></tr>
</table>

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
	var oid = "<%=oid%>";
	var itemLocation = "<%=headerEncoder.encodeForJavaScript(s_item_location)%>";
	var lookupStatus = "<%=lookupStatus%>";

<%	if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0) {
			if (s_req_type.equals("S") || s_req_type.equals("T") || s_req_type.equals("I")) { %>
				hideArea('itemtype');
				frm.as_item_type[1].checked = "TRUE";
<%		}
		}%>
	function setFieldFocus() {
		if (lookupStatus == "NOTFOUND" && frm.RequisitionLine_description) {
			frm.RequisitionLine_description.focus();
		} else if (lookupStatus == "FOUND" && frm.RequisitionLine_quantity) {
			frm.RequisitionLine_quantity.focus();
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
		var hideArea = false;

		for (i = 0; i < myAreas.length; i++) {
			myArea = myAreas[i];

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

		if(!validateFloatCommas(frm.RequisitionLine_unitPrice)){
			return;
		}
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
	    if (!/^(\d+)\.?(\d+)$/.test(frm.RequisitionLine_unitPrice)){
	    	frm.RequisitionLine_unitPrice.value = addCommas(frm.RequisitionLine_unitPrice.value);
		}
	    if (!/^(\d+)\.?(\d+)$/.test(frm.computed_subtotal)){
	    	frm.computed_subtotal.value = addCommas(frm.computed_subtotal.value);
		}
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

	function addItem(createtype)
	{
		if (createtype == "duplicate")
		{
			frm.duplicateItem.value = "TRUE";
		}
		else
		{
			frm.duplicateItem.value = "FALSE";
		}
		var line_count = frm.lineCount.value;
		line_count++;
		frm.lineCount.value = line_count;

		var newInputField = "<input type='hidden' name='nonStandardItem' value='Y'>";
		setHiddenFields(newInputField);

		doSubmit('/requests/req_item.jsp', 'RequisitionLineUpdate;RequisitionLineCreate');
	}

	function thisLoad()
	{
		f_StartIt();
		if(oid == "BLY07p"){
			hideArea('req-LN06');
		}
		if(frm.organizationId.value == "DTN07P") {
			document.getElementById('billingTable').style.display='none';
			var element = document.getElementById('RequisitionLine_receiptRequired');
			element.remove(0);
		}

<%
	boolean disableEdit = false ;

    if (s_req_type.equals("M")) {
    		if (s_requisitioner.equals(uid)) {
				disableEdit = 
(s_req_line_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVING) >= 
0) ;
			} else if (fpeUser) {
				disableEdit = 
s_req_line_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) > 0 ;
			} else 	if (msrEngineer)  {
    			disableEdit = 
(s_req_line_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) >= 0) 
;
    		} else {
    			disableEdit = 
(s_req_line_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVED) > 0) 
;
	    		if (! disableEdit) 	disableEdit = (s_req_line_status.equals(DocumentStatus.REQ_PLANNING_APPROVING ) && role.getAccessRights("REQMAPP") < 2)   ;
    		}
    } else {
    	disableEdit = (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0) ;
    }
    if(s_req_type.equals("P") && s_req_status.compareTo(DocumentStatus.REQ_REJECTED) == 0 && !user.isAFpe()){
    	disableEdit = true;
	}

	if (disableEdit ) {%>
		checkInputSettings();
<%
	} else if (reqLine.getItemSource().equals("XML") && (!isLockedFields.equalsIgnoreCase("N") && HiltonUtility.isEmpty(requisitionHeader.getMsrNumber()))) {%>
		checkInputSettings();
		<%	if (oid.equalsIgnoreCase("wpc08p")){ %>
			allowInputEdit(document.getElementById('checkasset'),true);
		<%	} %>
		allowInputEdit(frm.RequisitionLine_quantity, true);
		allowInputEdit(frm.RequisitionLine_udf1Code, true);
		allowInputEdit(frm.RequisitionLine_udf2Code, true);
		allowInputEdit(frm.RequisitionLine_udf3Code, true);
		allowInputEdit(frm.RequisitionLine_udf4Code, true);
		allowInputEdit(frm.RequisitionLine_udf5Code, true);
		allowInputEdit(frm.RequisitionLine_itemLocation, true);
		if (frm.RequisitionLine_receiptRequired != undefined) {
			allowInputEdit(frm.RequisitionLine_receiptRequired, true);
		}
		allowInputEdit(frm.RequisitionLine_requisitionerCode, true);
		allowInputEdit(frm.RequisitionLine_departmentCode, true);
		allowInputEdit(frm.RequisitionLine_commodityCode, true);
<%	} else if (labViewGroup) {%>
		allowInputEdit(frm.RequisitionLine_departmentCode, false);
<%	}%>
<% if (reqLine.getItemSource().equals("CAT") && !reqLine.getItemNumber().startsWith("*") && oid.equalsIgnoreCase("bly07p")) {%>
		allowInputEdit(frm.RequisitionLine_commodityCode, false);
<%	}%>
<% if (oid.equalsIgnoreCase("hoy08p")) { %>
		if (frm.RequisitionLine_udf3Code)
			allowInputEdit(frm.RequisitionLine_udf3Code, false);
<% } %>

<%		if (s_req_status.compareTo(DocumentStatus.REQ_INPROGRESS) >= 0
				&& s_req_type.equals("C")
				&& s_udf_1_code != null && s_udf_1_code.contains("RESALE")
				&& s_udf_14_code != null && s_udf_14_code.equals("DBS")
				&& b_ic_po_line.compareTo(new BigDecimal("0")) > 0) { %>
			allowInputEdit(frm.RequisitionLine_quantity, false);
			allowInputEdit(frm.RequisitionLine_itemNumber, false);
			allowInputEdit(frm.RequisitionLine_description, false);
			allowInputEdit(frm.RequisitionLine_unitPrice, false);
<%		} %>

		setFieldsToDisplayByItem();

<% if (isLockedFields.equalsIgnoreCase("Y") && (s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) < 0)) { %>
		$("#resetItem").show();
<%}else{%>
		$("#resetItem").hide();
<%}%>
<%		if (typeIterator != null && securityTypes != null) { %>
			allowEditApprover();
<%		}
		if (isLockedFielsFromInvItem.equalsIgnoreCase("Y")) { %>
			lockedFieldsFromInvItem();
<%		}
		if (isLockedFields.equalsIgnoreCase("Y")) { %>
			lockFields();
<%		} else if (isLockedFields.equalsIgnoreCase("N")) { %>
			lockAfterReset();
<%		} else {
			if (s_req_status.equals("0500") || s_req_status.equals("0505") || s_req_status.equals("1000") || s_req_status.equals("1010") || s_req_status.equals("1045")) {%>
			$('input[name*="RequisitionLine_udf10Code"]').attr('disabled', '');
<%			}
		}
		if (isChemicalLocked.equalsIgnoreCase("Y")) { %>
			lockChemical();
<%		}	%>
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
		if (!validateFloatCommas(frm.RequisitionLine_unitPrice)) {
			 return;
		}
		frm.lineToRetrieve.value = linenumber;
		doSubmit('/requests/req_item.jsp', 'RequisitionLineUpdate;RequisitionLineRetrieveByLineNumber');
	}

	function viewItemHistory()
	{
		popupParameters = "HistoryLog_icHeaderHistory=<%=requisitionHeader.getIcHeaderHistory()%>;HistoryLog_icLineHistory=<%=reqLine.getIcLineHistory()%>;formtype=REQ;RequisitionLine_icReqHeader=<%=reqLine.getIcReqHeader()%>;RequisitionLine_icReqLine=<%=reqLine.getIcReqLine()%>;requisitionNumber=<%=headerEncoder.encodeForJavaScript(reqLine.getRequisitionNumber())%>;requisitionType=<%=requisitionHeader.getRequisitionType()%>";
		doSubmitToPopup('/requests/req_history.jsp', 'HistoryLogRetrieveByHistoryLine', 'width=675px', 'height=350px');
	}

	function itemSearch() {
		itemSearchWithUpdate('RequisitionLineUpdate');
	}

	function getItemInfo() {
		// No need to call the server if the item number is empty
		if (!isEmpty(frm.RequisitionLine_itemNumber.value)) {
		<%if(s_multiple_browse_item.equalsIgnoreCase("Y")){%>
			popupParameters = "browseName=req-itemlookup;";
			popupParameters = popupParameters + "multipleBrowse=Y;";
			popupParameters = popupParameters + "RequisitionHeader_requisitionType="+frm.RequisitionHeader_requisitionType.value+";";
			popupParameters = popupParameters + "RequisitionLine_itemNumber="+frm.RequisitionLine_itemNumber.value+";";
			doSubmitToPopup('browse/browse_popup.jsp', 'RequisitionLineItemLookupBrowse', 'WIDTH=900px', 'HEIGHT=600px');
		<%}else{%>
			doSubmit('requests/req_item.jsp', 'RequisitionLineItemLookup;RequisitionLineRetrieve');
		<%}%>
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

		if ( handlers.indexOf("RequisitionLine") >= 0 && frm.organizationId.value.toUpperCase() == 'BLY07P' && !isEmpty(frm.RequisitionLine_udf2Code.value) )
		{
			var udf2 = "<tsa:label labelName="req-LN02" defaultString="Line UDF2" />";
			if (!isNumeric(frm.RequisitionLine_udf2Code.value)) {
				alert("Please enter a numeric value for " + udf2 + ".");
				return false;
			}
		}

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

	function retrieveAccounts()
	{
		<% if (oid.equalsIgnoreCase("bly07p") || defaultAccountsByCommodity) { %>
		frm.Commodity_commodity.value = frm.RequisitionLine_commodityCode.value;
		doSubmit('/requests/req_accounts_ln.jsp','RequisitionLineUpdate;RequisitionLineRetrieveAccount;CommodityRetrieveAccount');
		<% } else { %>
		doSubmit('/requests/req_accounts_ln.jsp','RequisitionLineUpdate;RequisitionLineRetrieveAccount');
		<% } %>
	}

	function submitThis()
	{
		if (!validateFloatCommas(frm.RequisitionLine_unitPrice)) {
			 return;
		}

		frm.RequisitionLine_unitPrice.value = removeCommas(frm.RequisitionLine_unitPrice.value);

		frm.computed_subtotal.value = removeCommas(frm.computed_subtotal.value);
		<%	if (reqLine.getItemSource().equals("CAT"))
			{
				BigDecimal minReqQty = new BigDecimal(0);
				Object catalogItem = CatalogItemManager.getInstance().getCatalogItem(oid, reqLine.getCatalogId(), reqLine.getItemNumber());
				if (catalogItem != null)
				{
					minReqQty = ((CatalogItem)catalogItem).getMinReqQty();
					if (minReqQty.compareTo(new BigDecimal(0)) > 0)
					{%>
						if (frm.RequisitionLine_quantity.value < <%=minReqQty%> && frm.RequisitionLine_quantity.value >= 0)
						{
							alert("The minimum requisition quantity for this Item is <%=minReqQty%>.\nYou must enter a quantity greater or equal than <%=minReqQty%>");
							return;
						}
		<%			}
				}
			}	%>
		doSubmit('<%=s_return_page%>', 'RequisitionLineUpdate;<%=s_return_method%>');
	}

	function viewPriceBreaks(type) {
		var catid = frm.catalogId.value;
		var itemNumber = frm.RequisitionLine_itemNumber.value;

		popupParameters = "browseName=catalog-pricebrk";
		popupParameters = popupParameters + ";colname=CatalogPriceBrk_id_catalogId;operator==;filter_txt=" + catid + ";logicalOperator=AND;originalFilter=Y;sort=N";
		popupParameters = popupParameters + ";colname=CatalogPriceBrk_id_itemNumber;operator==;filter_txt=" + itemNumber + ";logicalOperator=AND;originalFilter=Y;sort=N;";

		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}

	function viewItem(row) {
		var num = document.getElementById("icReqLine_" + row);
		frm.RequisitionLine_icReqLine.value = num.value;
		doSubmit('/requests/req_item_cancel_close.jsp','RequisitionLineRetrieve');
	}

	function browseRequisitionerCode() {
		var currentAllowBrowse = frm.allowBrowse.value;
		<%	if (reqLine.getItemSource().equals("XML") && !(s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0)) { %>
				frm.allowBrowse.value="true";
		<%	} %>
		browseLookup('RequisitionLine_requisitionerCode', 'requisitioner');
		frm.allowBrowse.value = currentAllowBrowse;
	}

	function browseDepartmentCode() {
		var currentAllowBrowse = frm.allowBrowse.value;
		<%	if (reqLine.getItemSource().equals("XML") && !(s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0)) { %>
				frm.allowBrowse.value="true";
		<%	} %>

		<%	if (HtmlWriter.isReadOnly(oid, "req-departmentln").equalsIgnoreCase("disabled")) { %>
				frm.allowBrowse.value = "false";
		<%	} %>

		<%	if(labViewGroup) { %>
			browseLookup('RequisitionLine_departmentCode', 'department', false);
		<%	} else { %>
			browseLookup('RequisitionLine_departmentCode', 'department');
		<%	} %>
		frm.allowBrowse.value = currentAllowBrowse;
	}

	function browseLookup1(formField)
	{
		var itemNumber = frm.RequisitionLine_itemNumber.value;
		var o = "<%=oid.toUpperCase()%>";
		popupParameters = popupParameters + "colname=PoLine_itemNumber;operator==;filter_txt=" + frm.RequisitionLine_itemNumber.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
		browseLookup(formField, 'pricepaid');
	}

	function browseSupplier(formField)
	{
		var itemNumber = frm.RequisitionLine_itemNumber.value;
		var o = "<%=oid.toUpperCase()%>";
		if (o == "BLY07P" && !isEmpty(itemNumber)) {
			popupParameters = popupParameters + "colname=XrefCombo_code2;operator==;filter_txt=" + frm.RequisitionLine_itemNumber.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
			browseLookup(formField, 'mxp-vendor');
		} else {
			popupParameters = popupParameters + "formField=" + formField +";browseName=vendor;allowBrowse=" + frm.allowBrowse.value;
			doSubmitToPopup('/browse/supplier_filter_options.jsp', 'VendorRetrieveBrowseOptions', 'WIDTH=700px', 'HEIGHT=500px');
		}
	}

	function browseCommodityByType()
	{
		var currentAllowBrowse = frm.allowBrowse.value;
		var commodityBrowse = 'commodity';
		var deleteItem = false;
<% 		if(defaultByCommodity.equalsIgnoreCase("Y")) { %>
/************************************************************************************************************************
Removed Item # validation.  Some customers do not require an item # (allows for either item # OR description).
This validation will be perfermed upon submitting the form to determine if the item changes should be
discarded and record(s) delete from the database.
- KK 02-02-2011 (P4 #401:  Requisition:  Remove Item # Requirement when clicking on Commodity link)
*************************************************************************************************************************/
			<%	if (s_req_type.equalsIgnoreCase("H")) { %>
					commodityBrowse = 'commodity-it';
			<%	} %>

			<% 	if (reqLine.getItemSource().equals("CAT") && !reqLine.getItemNumber().startsWith("*") && oid.equalsIgnoreCase("dtn07p")) { %>
				<% 	if( !reqLine.getCatalogId().equalsIgnoreCase("") ){%>
						frm.allowBrowse.value="true";
				<%	}else{%>
						frm.allowBrowse.value="false";
				<%	}%>
			<%	} %>

			<%	if (reqLine.getItemSource().equals("XML") && !(s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0)) { %>
					frm.allowBrowse.value = "true" ;
			<% 	} %>

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
<% 		}
		else
		{ %>
			<%	if (s_req_type.equalsIgnoreCase("H")) { %>
				commodityBrowse = 'commodity-it';
			<% 	} %>

		<% 		if (reqLine.getItemSource().equals("CAT") && !reqLine.getItemNumber().startsWith("*") && oid.equalsIgnoreCase("dtn07p")) { %>
				<%	if( !reqLine.getCatalogId().equalsIgnoreCase("") ){%>
					frm.allowBrowse.value="true";
				<%}	else{%>
					frm.allowBrowse.value="false";
				<%}%>
		<%		} %>

		<%		if (reqLine.getItemSource().equals("XML") && !(s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0)) { %>
					frm.allowBrowse.value = "true" ;
		<% 		} %>

		<% 		if (oid.equalsIgnoreCase("msg07p")) { %>
					popupParameters = "browseName=commodity";
					popupParameters = popupParameters + ";formField=RequisitionLine_commodityCode;allowBrowse=" + frm.allowBrowse.value;
					popupParameters = popupParameters + ";colname=Commodity_commodityType;operator==;filter_txt=BO;logicalOperator=OR;originalFilter=Y;sort=N";
					popupParameters = popupParameters + ";colname=Commodity_commodityType;operator==;filter_txt=SP;logicalOperator=AND;originalFilter=Y;sort=N";
					doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
		<% 		} else { %>
					browseCommodity('RequisitionLine_commodityCode', commodityBrowse, '<%=propertiesManager.getProperty("MISC", "COMMODITYTYPE", "")%>');
		<% 		} %>

			frm.allowBrowse.value = currentAllowBrowse;
	<% 	} %>
	}

	function browseWarehouse(formField)
	{
		var itemNumber = frm.RequisitionLine_itemNumber.value;
		if (isEmpty(itemNumber)) {
			browseStd('RequisitionLine_udf5Code', 'LN05');
		} else {
			popupParameters = popupParameters + "colname=XrefCombo_code2;operator==;filter_txt=" + frm.RequisitionLine_itemNumber.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
			browseLookup(formField, 'mxp-warehouse');
		}
	}

	function browseUdf6Code()
	{
		<% if (oid.equalsIgnoreCase("bly07p")) { %>
		browseStd('RequisitionLine_udf6Code', 'RQ06');
		<% } else { %>
		browseStd('RequisitionLine_udf6Code', 'LN06');
		<% } %>
	}

	function browseStdUdf3()
	{
		var currentAllowBrowse = frm.allowBrowse.value;
		<% if (oid.equalsIgnoreCase("hoy08p")) { %>
		frm.allowBrowse.value = "false";
		<% } %>
		browseStd('RequisitionLine_udf3Code', 'LN03');
		frm.allowBrowse.value = currentAllowBrowse;
	}

	function viewAltLanguages() {
		if (validateItem()) {
			doSubmit('/requests/req_item_altlanguages.jsp', 'RequisitionLineUpdate;AltTextRetrieveByItem');
		}
	}

<%	if (oid.equalsIgnoreCase("vse06p")) { %>
	function setUdf10()
	{
		if (frm.udf10Code[0].checked)
		{
			frm.RequisitionLine_udf10Code.value = frm.udf10Code[0].value;
			frm.updateUdf10.value = "TRUE";
		}
		else if (frm.udf10Code[1].checked)
		{
			frm.RequisitionLine_udf10Code.value = frm.udf10Code[1].value;
			frm.updateUdf10.value = "TRUE";
		}
		else if (frm.udf10Code[2].checked)
		{
			frm.RequisitionLine_udf10Code.value = frm.udf10Code[2].value;
			frm.updateUdf10.value = "TRUE";
		}
	}
<%} %>

	function setFieldsToDisplayByItem() {
		var itemNumber = frm.RequisitionLine_itemNumber.value;

		<% if (oid.equalsIgnoreCase("bly07p")) { %>
			hideArea('req-LN06');
			if (itemNumber.indexOf('*') == 0){
				hideArea('req-LN06-highlighted');
				displayArea('req-LN06');
				displayArea('req-LN06-fld');
			}
			else if (isEmpty(itemNumber))
			{
				displayArea('req-LN06-highlighted');
				displayArea('req-LN06-fld');
			} else {
				hideArea('req-LN06');
				hideArea('req-LN06-highlighted');
				hideArea('req-LN06-fld');
			}
		<% } %>
	}

    function browseFdcsWOElements(fldName) {

    	popupParameters = "workNumber="+ frm.RequisitionLine_udf1Code.value +";";
       	popupParameters = popupParameters + "segNumber="+ frm.RequisitionLine_udf2Code.value +";";
       	popupParameters = popupParameters + "opNumber="+ frm.RequisitionLine_udf3Code.value +";";
       	popupParameters = popupParameters + "custNumber="+ frm.RequisitionLine_udf4Code.value +";";

    	browseLookup( fldName, 'fdc-wo-elements' ) ;
    }
    function updateReqLineAccount()
    {
    	var deleteItem = false;
    	if (isEmpty(frm.RequisitionLine_itemNumber.value)) {
			// no need to do this check if we are already deleting the line item or if changes are going to be discarded and an item number or description was previously entered
	<%		if ( !HiltonUtility.isEmpty(reqLine.getItemNumber())) {	%>
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
	    		frm.Commodity_commodity.value = frm.RequisitionLine_commodityCode.value;
	    		frm.commodityUpdateAccount.value = "Y";

				doSubmit('/requests/req_item.jsp', 'RequisitionLineUpdate;RequisitionLineRetrieve');
	    	}
<%		} %>
    }

    function validateFloat(obj){
        var number = obj.value;
    	if (!/^([0-9])*[.]?[0-9]*$/.test(number)){
        	alert("The " + number + " value is not a number");
    		obj.value = '0.00';
    		frm.computed_subtotal.value = '0.00';
    		return false;
    	}else{
        	return true;
        }
    }

    function validateFloatCommas(obj){
        var number = obj.value;
    	//if (!/^((\d{1,3}\,){0,2})*(\d{1,3})\.?(\d+)$/.test(number)){
    	//if (!/^(\d+)\.?(\d+)$/.test(number)){
    	if(/^\d+$/.test(number) || /^(\d+|(\d{1,3}\,(\d{3}\,){0,1}\d{3}))$/.test(number)) {
        	 number = number + '.00';
    	}

    	if(/^\.?(\d+)$/.test(number)) {
       	 number = '0' + number;
   		}

    	if (!/^(\d+|(\d{1,3}\,(\d{3}\,){0,1}\d{3}))\.?(\d+)$/.test(number)){
        	alert("The " + number + " value is not a number");
    		obj.value = '0.00';
    		frm.computed_subtotal.value = '0.00';
    		return false;
    	} else{
        	return true;
        }
    }

	function createInspectionDetail()
	{
		frm.inspectionAction.value = "CREATE";
		doSubmit('/requests/inspection_detail.jsp','RequisitionLineUpdate;InspectionRetrieveDetail');
	}

	function deleteInspectionDetail(inspNo, inspType)
	{
		if (confirm("Are you sure you wish to delete (" + inspType + ")?")) {
			frm.InspectionHeader_icInspNo.value = inspNo ;
			doSubmit('/requests/req_item.jsp','RequisitionLineUpdate;InspectionDetailDelete;RequisitionLineRetrieve');
		}
	}

    function openInspectionDetail(inspNo) {
		frm.InspectionHeader_icInspNo.value = inspNo ;
		doSubmit('/requests/inspection_detail.jsp','RequisitionLineUpdate;InspectionRetrieveDetail');
    }

    function allowEditApprover()
	{
		<%	if (typeIterator != null && securityTypes != null)
		{
			String accessType = "";
			while (typeIterator.hasNext())
			{
				accessType = (String) typeIterator.next();
				if (accessType.toUpperCase().startsWith("RL-") && role.getAccessRights(accessType.toUpperCase()) > 0)
				{
					if (accessType.length() > 4)
					{
						accessType = accessType.substring(3).toLowerCase();
						String[] accessTypeArray = accessType.split("_");
						String accessTypeField = "";
						for (int ind = 0; ind < accessTypeArray.length; ind++)
						{
							if (accessTypeArray[ind].length() > 0)
							{
								if (ind == 0) {
									accessTypeField = accessTypeArray[ind];
								} else {
									accessTypeField = accessTypeField + accessTypeArray[ind].substring(0,1).toUpperCase() + accessTypeArray[ind].substring(1);
								}
							}
						} %>
						if (frm.RequisitionLine_<%=accessTypeField%>) {
							allowInputEdit(frm.RequisitionLine_<%=accessTypeField%>, true);
							frm.editFieldsApprover.value = frm.editFieldsApprover.value + "RequisitionLine_<%=accessTypeField%>";
						}
				<%	}
				}
			}
		} %>
	}

    function lockedFieldsFromInvItem() {

		if(frm.RequisitionLine_itemNumber) allowInputEdit(frm.RequisitionLine_itemNumber, false);
		if(frm.RequisitionLine_description) allowInputEdit(frm.RequisitionLine_description, false);
		if(frm.RequisitionLine_udf1Code) allowInputEdit(frm.RequisitionLine_udf1Code, false);
		if(frm.RequisitionLine_udf2Code) allowInputEdit(frm.RequisitionLine_udf2Code, false);
		if(frm.RequisitionLine_udf3Code) allowInputEdit(frm.RequisitionLine_udf3Code, false);
		if(frm.RequisitionLine_udf4Code) allowInputEdit(frm.RequisitionLine_udf4Code, false);
		if(frm.RequisitionLine_udf5Code) allowInputEdit(frm.RequisitionLine_udf5Code, false);
		if(frm.RequisitionLine_udf6Code) allowInputEdit(frm.RequisitionLine_udf6Code, false);
		if(frm.RequisitionLine_udf7Code) allowInputEdit(frm.RequisitionLine_udf7Code, false);

		if(frm.checkshelfLifeRqd) allowInputEdit(frm.checkshelfLifeRqd, false);
		if(frm.checkasset) allowInputEdit(frm.checkasset, false);
	}

	function lockFields()
	{
		if(frm.RequisitionLine_itemNumber) allowInputEdit(frm.RequisitionLine_itemNumber, false);
		if(frm.RequisitionLine_description) allowInputEdit(frm.RequisitionLine_description, false);
		if(frm.RequisitionLine_umCode) allowInputEdit(frm.RequisitionLine_umCode, false);
		if(frm.RequisitionLine_unitPrice) allowInputEdit(frm.RequisitionLine_unitPrice, false);
		if(frm.RequisitionLine_commodityCode) allowInputEdit(frm.RequisitionLine_commodityCode, false);
		if(frm.RequisitionLine_vendorId) allowInputEdit(frm.RequisitionLine_vendorId, false);
		if(frm.RequisitionLine_modelNumber) allowInputEdit(frm.RequisitionLine_modelNumber, false);
		if(frm.RequisitionLine_mfgName) allowInputEdit(frm.RequisitionLine_mfgName, false);
		if(frm.RequisitionLine_asset) allowInputEdit(frm.RequisitionLine_asset, false);
		if(frm.RequisitionLine_udf1Code) allowInputEdit(frm.RequisitionLine_udf1Code, false);
		if(frm.RequisitionLine_udf2Code) allowInputEdit(frm.RequisitionLine_udf2Code, false);
		if(frm.RequisitionLine_udf3Code) allowInputEdit(frm.RequisitionLine_udf3Code, false);
		if(frm.RequisitionLine_udf4Code) allowInputEdit(frm.RequisitionLine_udf4Code, false);
		if(frm.RequisitionLine_udf5Code) allowInputEdit(frm.RequisitionLine_udf5Code, false);
		if(frm.RequisitionLine_udf6Code) allowInputEdit(frm.RequisitionLine_udf6Code, false);
		if(frm.RequisitionLine_udf7Code) allowInputEdit(frm.RequisitionLine_udf7Code, false);
		if(frm.RequisitionLine_udf8Code) allowInputEdit(frm.RequisitionLine_udf8Code, false);
		if(frm.RequisitionLine_udf9Code) allowInputEdit(frm.RequisitionLine_udf9Code, false);

		if(frm.checkshelfLifeRqd) allowInputEdit(frm.checkshelfLifeRqd, false);
		if(frm.checkasset) allowInputEdit(frm.checkasset, false);
<%	if (!reqLine.getItemSource().equals("CAT") ) {%>
		$('a[href*="javascript: browse"]').removeAttr('href');
<%	} else if(s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) < 0){%>
		var commhref= $('#commodityItemBrowse').attr('href');
		$('a[href*="javascript: browse"]').removeAttr('href');
		$('#commodityItemBrowse').attr('href',commhref);

<%	} else {%>
		$('a[href*="javascript: browse"]').removeAttr('href');
<%	} %>
	}

	function lockAfterReset()
	{
	//	if(frm.RequisitionLine_chemicalItemNumber) allowInputEdit(frm.RequisitionLine_chemicalItemNumber, false);
		if(frm.RequisitionLine_udf9Code) allowInputEdit(frm.RequisitionLine_udf9Code, false);
		$('input[name*="RequisitionLine_udf10Code"]').attr('disabled', '');
	}

	function resetLine()
	{
		frm.RequisitionLine_itemNumber.value = "";
		frm.RequisitionLine_itemSource.value = "";
		frm.RequisitionLine_catalogId.value = "";
		frm.RequisitionLine_blanketOrder.value = "";
		doSubmit('/requests/req_item.jsp', 'RequisitionLineUpdate;RequisitionLineRetrieve');
	}

	function lockChemical()
	{
		if(frm.RequisitionLine_chemicalItemNumber) allowInputEdit(frm.RequisitionLine_chemicalItemNumber, false);
	<% if((s_req_line_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVING) < 0) || (s_req_line_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) < 0 && fpeUser)){%>
		if(frm.RequisitionLine_udf3Code) allowInputEdit(frm.RequisitionLine_udf3Code, true);
		if(frm.RequisitionLine_commodityCode) allowInputEdit(frm.RequisitionLine_commodityCode, true);
	<% }%>
	}

	function checkPrice(field) {
		var number = field.value;
		if(/^\d+$/.test(number)) {
			frm.RequisitionLine_unitPrice.value = number + '.0000';
   		}
	}

// End Hide script -->
</SCRIPT>
