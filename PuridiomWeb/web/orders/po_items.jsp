<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.menu.MenuManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.entity.PoHeader" %>
<%@ page import="com.tsa.puridiom.catalogsecurity.tasks.CatalogSecurityManager" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/browse.js"></SCRIPT>
<%
	Encoder encoder = DefaultEncoder.getInstance();
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	String	s_ic_po_header = (String) request.getAttribute("PoHeader_icPoHeader");
	String	s_po_number = "";
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(0);
	BigDecimal	bd_revision_number = new BigDecimal(0);
	String	s_release_number = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_releaseNumber"));
	String	s_revision_number = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_revisionNumber"));
	if (!HiltonUtility.isEmpty(s_release_number))
	{
		bd_release_number = new BigDecimal(s_release_number);
	}
	if (!HiltonUtility.isEmpty(s_revision_number))
	{
		bd_revision_number = new BigDecimal(s_revision_number);
	}
	String	s_po_status = (String) request.getAttribute("PoHeader_status");
	String	s_po_type = (String) request.getAttribute("PoHeader_poType");
	String	s_fiscal_year = (String) request.getAttribute("PoHeader_fiscalYear");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_itemLocation"));
	String s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_buyerCode"));
	String s_vendor_id = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_vendorId"));

	String	s_current_process = "SHOPCART";
	String	s_current_page = "/orders/po_items.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";

	List	poLineList = (List) request.getAttribute("poLineList");
	int	i_linecount = 0;

	if (poLineList != null)
	{
		i_linecount = poLineList.size();

		if (i_linecount > 0)
		{
			PoLine poLine = (PoLine) poLineList.get(0);
			s_po_number = poLine.getPoNumber();
		}
	}

	if (HiltonUtility.isEmpty(s_po_type))	{	s_po_type = "PO";				}
	if (HiltonUtility.isEmpty(s_po_number))
	{
		s_po_number = (String)request.getAttribute("PoHeader_poNumber");
		if (HiltonUtility.isEmpty(s_po_number))
		{
			s_po_number = "N/A";
		}
	}
	if (HiltonUtility.isEmpty(s_po_status))	{	s_po_status = DocumentStatus.PO_INPROGRESS;			}


	String FilenameXls = null;
	int control=0;

    if(request.getAttribute("FilenameXls")!= null)
    {
    FilenameXls = (String) request.getAttribute("FilenameXls");
    }

    PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
    String s_curr_code = poHeader.getCurrencyCode();

	boolean isCatalogSecurityActive = true;
	String catalogSecurityActive = PropertiesManager.getInstance(oid).getProperty("CATALOG SECURITY DEFAULTS", "CATALOGSECURITYACTIVE","N");

	//isCatalogSecurityActive = catalogSecurityActive.equalsIgnoreCase("Y") && CatalogSecurityManager.getInstance().isEnabledPunchoutCatalog(oid,user);

%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoLine_icPoLine" value=""/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoSecurity_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_status" value="${poHeader.status}"/>
<tsa:hidden name="PoHeader_poType" value="${poHeader.poType}"/>
<tsa:hidden name="PoHeader_fiscalYear" value="${poHeader.fiscalYear}"/>
<tsa:hidden name="PoHeader_itemLocation" value="${poHeader.itemLocation}"/>
<tsa:hidden name="PoHeader_buyerCode" value="${poHeader.buyerCode}"/>
<tsa:hidden name="PoHeader_flagChange" value="<%//=HiltonUtility.ckNull(poHeader.getFlagChange())%>"/>
<tsa:hidden name="PoHeader_requiredDate" value="<%=HiltonUtility.getFormattedDate(poHeader.getRequiredDate(), oid, userDateFormat)%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="POH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="lineCount" value="<%=i_linecount%>"/>
<tsa:hidden name="formtype" value="PO"/>
<tsa:hidden name="frompage" value="shopcart"/>
<tsa:hidden name="punchOutReturnHandler" value=""/>
<tsa:hidden name="punchOutReturnSuccessPage" value=""/>
<tsa:hidden name="icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Asset_purchaseOrder" value="<%=s_po_number%>"/>
<tsa:hidden name="VendorInsurance_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="VendorInsurance_contNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=s_vendor_id%>"/>
<tsa:hidden name="showLinkGetItemInfo" value="N"/>
<tsa:hidden name="punchOutAddAccount" value="N"/>
<% if (oid.equalsIgnoreCase("wpc08p")) { %>
<tsa:hidden name="userNameUdf1" value="<%=user.getDepartment()%>"/>
<tsa:hidden name="userNameUdf2" value="<%=user.getNameUdf2()%>"/>
<tsa:hidden name="userNameUdf3" value="1770"/>
<% } %>
<tsa:hidden name="poIcReqHeader" value="<%= poHeader.getIcReqHeader().toString() %>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="shopping_cart" defaultString="Shopping Cart" /></div>
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
<br>
<%@ include file="/system/error_msg.jsp" %>
<br>

<div style="width:<%=formEntryWidth%>;align:center;">
	<div style="width:76%;valign:top;float:left">
		<!-- start rounded corners -->
		<div id="container" style="width: 100%; align:center; margin:10;">
		<b class="rtop">
		  <b class="r1"></b> <b class="r2"></b> <b class="r3"></b> <b class="r4"></b>
		</b>
		<table cellpadding=0 cellspacing=2 border=0 class=browseHdr width=100%>
		<tr>
			<td>
				<table border=0 cellpadding=2 cellspacing=0 width=100%>
				<tr>
					<td width=7% height=18px class=browseHdr nowrap><tsa:label labelName="po-hdg-lineNumber" defaultString="Line #" /></td>
					<tsa:td field="po-itemNumber" width="18%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="po-hdg-itemNumber" defaultString="Item/Part #" checkRequired="FALSE"/></tsa:td>
					<tsa:td field="po-commodity" width="15%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="po-hdg-commodity" defaultString="Commodity" checkRequired="FALSE"/></tsa:td>
					<tsa:td field="po-quantity" width="10%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="po-hdg-quantity" defaultString="Quantity" checkRequired="FALSE"/></tsa:td>
					<tsa:td field="po-uom" width="10%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="po-hdg-uom" defaultString="UOM" checkRequired="FALSE"/></tsa:td>
					<tsa:td field="po-unitCost" width="10%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="po-hdg-unitCost" defaultString="Unit Cost" checkRequired="FALSE"/></tsa:td>
					<tsa:td field="po-extendedCost" width="15%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="po-hdg-extendedCost" defaultString="Extended Cost" checkRequired="FALSE"/></tsa:td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align="center" valign="top">
				<table id=itemRows border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
<%		if (i_linecount <= 0) { %>
				<tr>
					<td align="center"><br><b><tsa:label labelName="noitems" defaultString="There are currently no items in your shopping cart." /></b><br><br></td>
				</tr>
<%		}
			for (int i = 0; i < i_linecount; i++)
			{
				PoLine poLine = (PoLine) poLineList.get(i);
				BigDecimal iclinekey = poLine.getIcLineKey();
				BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid);
				BigDecimal bd_unit_price = HiltonUtility.getFormattedPrice(poLine.getUnitPrice(), oid);
				BigDecimal bd_um_factor = HiltonUtility.getFormattedDollar(poLine.getUmFactor(), oid);
				if (bd_um_factor.compareTo(new BigDecimal(0)) == 0)
				{
					bd_um_factor = new BigDecimal(1);
				}
				BigDecimal bd_extended_cost = HiltonUtility.getFormattedDollar(bd_quantity.multiply(bd_unit_price).multiply(bd_um_factor), oid);
%>
				<tr>

					<td width=7% height="18px" class=browseRow nowrap align="right">
					<%
					if (assetsActive && poLine.getAsset().equals("Y") && Integer.valueOf(s_po_status).intValue() >= 3030 )
					{
					%><a href="javascript: viewAssetRelated('<%=iclinekey%>'); "><img src="<%=contextPath%>/images/asset2.GIF" border=0></a><%
					}
					%>
					<a href="javascript: viewItem(<%=i%>); void(0);" title="Click here to View/Modify Item Details." onMouseOver="highlightRow(<%=i%>);" onMouseOut="removeHighlight(<%=i%>);"><%=i+1%></a>
					&nbsp;&nbsp;</td>


					<tsa:td field="po-itemNumber" width="18%" height="18px" cssClass="browseRow" noWrap="nowrap">
						<%=poLine.getItemNumber()%>
						<input type="hidden" id="icPoLine_<%=i %>" name="icPoLine_<%=i %>" value="<%=poLine.getIcPoLine() %>">
					</tsa:td>
					<tsa:td field="po-commodity" width="15%" height="18px" cssClass="browseRow" noWrap="nowrap"><%=poLine.getCommodity()%>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="po-quantity" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid)%>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="po-uom" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap"><%=poLine.getUmCode()%></tsa:td>
					<tsa:td field="po-unitCost" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getCurrency(bd_unit_price, s_curr_code, oid)%>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="po-extendedCost" width="15%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getCurrency(bd_extended_cost, s_curr_code, oid)%>&nbsp;&nbsp;</tsa:td>
				</tr>
				<tr>
					<td width=5% height="18px" class="browseRow" nowrap>&nbsp;</td>
					<td colspan=6 height="18px" class="browseRow"><%=poLine.getDescription()%></td>
				</tr>
				<%if(oid.equalsIgnoreCase("bly07p")) {%>
				<tr>
					<td>&nbsp;</td>
					<td width=5% height="18px" class="browseRow" nowrap><tsa:label labelName="PO09" defaultString="Line UDF9" checkRequired="true" />&nbsp;</td>
					<td colspan=4 height="18px" class="browseRow"><%=poLine.getUdf9Code()%></td>
				</tr>
				<%}%>
<%		} %>
				</table>
			</td>
		</tr>
		</table>
		<b class="rbottom">
		  <b class="r4"></b> <b class="r3"></b> <b class="r2"></b> <b class="r1"></b>
		</b>
		</div>
		<!-- end rounded corners -->

		<br><br>

<%	if (s_po_status.compareTo(DocumentStatus.PO_REJECTED) <= 0 && role.getAccessRights("PO") > 1) {%>
		<div style="margin:10;valign:top">
<%		if (propertiesManager.getProperty("PO OPTIONS", "RestrictNonStandardItem", "N").equalsIgnoreCase("N")) { %>
			<tsa:hidden name="createAction" value="SAVE"/>
			<table  border=0 cellpadding=0 cellspacing=0>
				<tr>
					<td valign=middle><a href="javascript: addItem();"><img src="<%=contextPath%>/images/cmd_add_item.gif" border=0 alt="<tsa:label labelName="addnonstditem" defaultString="Add Non-Standard Item" />"><font style="text-decoration: none;"></font></a>&nbsp;</td>
					<td valign=middle><a href="javascript: addItem();" title="All services and any goods that are not listed in a catalog."><tsa:label labelName="addnonstditem" defaultString="Add Non-Standard Item" /></a></td>
					<td width=60px></td>
				</tr>
			</table>
	<%	}%>
			<br>
				<!-- Start fieldset -->
				<fieldset style="width:450px"><legend><tsa:label labelName="searchOptions" defaultString="Search Options" /></legend>
				<table border=0 cellspacing=0 cellpadding=2 width=100% align=center class=browseRow>
				<tr>
					<td valign=top class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2>
						<tr><td valign=middle nowrap><tsa:label labelName="keyword" defaultString="Keyword(s)" />:</td></tr>
						<tr>
							<td valign=middle><tsa:input type="text" title="Enter descriptive keyword(s) about your product to narrow your search" name="as_keywords" value="" size="60" /></td>
						</tr>
						</table>
						<br>
						<table border=0 cellpadding=0 cellspacing=0>
						<tr>
							<td valign=middle><a href="javascript: doSubmit('/browse/item_filter_options.jsp', 'CatalogRetrieveActive'); void(0);"><img src="<%=contextPath%>/images/cmd_search_item.gif" border=0 alt="Advanced Item Search"></a>&nbsp;</td>
							<td valign=middle><a href="javascript: doSubmit('/browse/item_filter_options.jsp', 'CatalogRetrieveActive'); void(0);" title="<tsa:label labelName="req-advancedItemSearch-instructions" defaultString="Click here to perform an Advanced Item Search." />" ><tsa:label labelName="advancedItemSearch" defaultString="Advanced Item Search" /></a></td>
						</tr>
						</table>
					</td>
					<td valign=top class=browseRow>
						<div id="itemtype" style="visibility:visible; display:block;">
						<br><tsa:hidden name="as_item_type" value="CAT"/>
						<table border=0 cellspacing=0 cellpadding=1>
						<!--tr>
							<td valign=middle align=right><input type=radio name="as_item_type" tabindex=10 value="CAT" CHECKED></td>
							<td valign=middle nowrap><tsa:label labelName="puridiom" defaultString="Puridiom" /></td>
						</tr>
						<tr>
							<td valign=middle align=right><input type=radio name="as_item_type" tabindex=12 value="INTERNET"></td>
							<td valign=middle nowrap><tsa:label labelName="internet" defaultString="Internet" /></td>
						</tr-->
						<tr><td colspan=2><div id="pxmediumbutton"><a href="javascript: itemSearch(); void(0);">Search</a></div></td></tr>
						</table>
						</div>
					</td>
				</tr>
				</table>
				</fieldset>
				<br><br>
				<!-- Start fieldset -->
				<fieldset style="width:225px;valign:top;align;center"><legend><tsa:label labelName="puridiomMarketPlace" defaultString="Puridiom Marketplace" /></legend>
				<table border=0 cellspacing=0 cellpadding=2 width=100% align=center class=browseRow>
				<tr>
					<td valign=top class=browseRow align=center>
						<table border=0 cellpadding=2 cellspacing=0>
		<%
			List externalCatalogs = MenuManager.getExternalCatalogs(oid);
			if (externalCatalogs != null) {
				for (int i=0; i < externalCatalogs.size(); i++) {
					Catalog catalog = (Catalog) externalCatalogs.get(i);
					if (catalog.getOrdersOnly().equalsIgnoreCase("Y") && !user.isABuyer())
					{
						continue;
					} else if (catalog.getCatalogId().equals("YAHOO")) {
						continue;
					}

					if (catalogSecurityActive.equalsIgnoreCase("Y")) {
						if (CatalogSecurityManager.getInstance().isEnabledPunchoutCatalog(oid, user, (String)catalog.getCatalogId())) {
							isCatalogSecurityActive = true;
						} else {
							isCatalogSecurityActive = false;
						}
					}

					if (isCatalogSecurityActive)
					{
						BigDecimal punchoutId	= catalog.getIcPunchout();
		%>
						<tr>
							<td type="hidden" name="punchoutId" value="<%=punchoutId%>">
							<td valign=middle><a href="javascript: browseExternalCatalog('<%=catalog.getCatalogId()%>'); void(0);"><img src="<%=contextPath%>/images/marketplace/<%=catalog.getCatalogId().toLowerCase()%>.gif" border=1 alt="Search <%=catalog.getTitle()%> Catalog" width=140px height=34px></a>&nbsp;</td>
						</tr>
		<%			}
				}
			} %>
						</table>
					</td>
				</tr>
				</table>
				</fieldset>

				<!-- Start fieldset -->
				<fieldset style="width:350px;valign:top;align:center;"><legend><tsa:label labelName="puridiomOnlineCatalogs" defaultString="Puridiom Online Catalogs" /></legend>
				<table border=0 cellspacing=0 cellpadding=2 width=100% align=center class=browseRow>
				<tr>
					<td valign=top class=browseRow>
						<div class=browseHdrDk style="border:solid 0px; background: #FFFFFF; padding: 2px; width: 350px; height: 125px; align:center; overflow-y:auto">
						<table border=0 cellpadding=2 cellspacing=0>
		<%
			List internalCatalogs = MenuManager.getTopInternalCatalogs(oid, 8);
			if (internalCatalogs != null) {
				for (int i=0; i < internalCatalogs.size(); i++) {
					Catalog catalog = (Catalog) internalCatalogs.get(i);
					if (catalog.getOrdersOnly().equalsIgnoreCase("Y") && !user.isABuyer())
					{
						continue;
					} else if (catalog.getCatalogId().equals("YAHOO")) {
						continue;
					}

					if (catalogSecurityActive.equalsIgnoreCase("Y")) {
						if (CatalogSecurityManager.getInstance().isEnabledOthersCatalog(oid, user, (String)catalog.getCatalogId()).equals("Y")) {
							isCatalogSecurityActive = true;
						} else {
							isCatalogSecurityActive = false;
						}
					}

					if (isCatalogSecurityActive)
					{
		%>
						<tr>
							<td valign=middle><a href="javascript: browseInternalCatalog('<%=catalog.getCatalogId()%>'); void(0);" title="Click here to Search the <%=catalog.getTitle()%> Catalog."><%=catalog.getCatalogId()%></a> - <%=catalog.getTitle()%></td>
						</tr>
		<%			}
				}
			} %>
						</table>
						</div>
					</td>
				</tr>
				</table>
				</fieldset>
			</div>
<%		}%>
	</div>
<%	if (s_view.equals("WIZARD")) { %>
	<div><%@ include file="/orders/po_sidebar.jsp" %></div>
<%	} %>
</div>

<br>
<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding="0" cellspacing="0" width=<%=formEntryWidth%>>
<tr>
	<td width=50% align=center><a href="javascript: poSaveClassic(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border="0" alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border="0" alt="Return"></a></td>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var ponumber = "<%=encoder.encodeForJavaScript(s_po_number)%>";
	var fiscalyear = "<%=encoder.encodeForJavaScript(s_fiscal_year)%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var itemLocation = "<%=encoder.encodeForJavaScript(s_item_location)%>";

	if (ponumber == "N/A")
	{
		var createfrom = "<%=HiltonUtility.ckNull((String) request.getAttribute("createdFrom"))%>"
		if (createfrom.length > 0)
		{
			setNavCookie("/orders/po_review.jsp", "PoRetrieve", "<%=OrderType.toString(s_po_type, oid)%>");
		}
	}

	var browser = browserCheck();

	function ToTotals(){
		doSubmit("/orders/po_totals.jsp", "PoRetrieve");
    }

    function ToReview(){
	    <%String FlagT="0"; %>

	    var newInputField = "<input type='hidden' name='Flag' value='<%=FlagT%>'";
		setHiddenFields(newInputField);
	    doSubmit("/orders/po_review.jsp", "PoHeaderUpdate;PoRetrieve");
    }

	function viewItem(row)
	{
		var num = document.getElementById("icPoLine_" + row);
		frm.PoLine_icPoLine.value = num.value;
		doSubmit('/orders/po_item.jsp','PoLineRetrieve');
	}

	function highlightRow(row) {
		row = row * 2;
		var myRow = document.all.itemRows.rows(row);

		setRowClassName(myRow, "selectedRow");

		myRow = document.all.itemRows.rows(row + 1);

		setRowClassName(myRow, "selectedRow");
	}

	function removeHighlight(row) {
		row = row * 2;
		var myRow = document.all.itemRows.rows(row);

		setRowClassName(myRow, "browseRow");

		myRow = document.all.itemRows.rows(row + 1);

		setRowClassName(myRow, "browseRow");
	}

	function addItem()
	{
		var line_count = frm.lineCount.value;
		line_count++;
		frm.lineCount.value = line_count;
		frm.showLinkGetItemInfo.value = "Y";

		doSubmit('/orders/po_item.jsp','PoLineCreate');
	}


	function browseExternalCatalog(catalog) {
		var punchoutHandlers = "BrowseExternalWebCatalog";
		var handlers = "PoRetrieve";
<%	if (s_view.equals("CLASSIC")) { %>
		var pg = "/orders/po_summary.jsp";
<%	} else if (s_view.equals("WIZARD")) { %>
		var pg = "/orders/po_review.jsp";
<%	}%>
		var hiddenFields = "<input type=hidden name=\"Catalog_catalogId\" value=\"" + catalog + "\">";
		setHiddenFields(hiddenFields);
		<% if (oid.equalsIgnoreCase("wpc08p")) { %>
		if (catalog == "CDW" && frm.userNameUdf3)
			frm.userNameUdf3.value = "1770";
		if (catalog == "CORP EXPRESS" && frm.userNameUdf3)
			frm.userNameUdf3.value = "8150";
		frm.punchOutAddAccount.value = "Y";
		<% } %>

		frm.punchOutReturnHandler.value = "ExternalItemLookup;" + handlers;
		frm.punchOutReturnSuccessPage.value = pg;

		doSubmit('/system/error.jsp', punchoutHandlers);
	}

	function viewAssetRelated(iclinekey)
  	{
		var newInputField = "<input type='hidden' name='Asset_icLineKey' value='" + iclinekey + "'>";
			newInputField = newInputField + "<input type='hidden' name='allowBrowse'	value='true'>";
			newInputField = newInputField + "<input type='hidden' name='icPoHeader'		value='<%=s_ic_po_header%>'>";
			newInputField = newInputField + "<input type='hidden' name='action'			value='po-items'>";
			newInputField = newInputField + "<input type='hidden' name='process'		value='AssetRetrieveByIcLineKey'>";
			newInputField = newInputField + "<input type='hidden' name='urlret'			value='/orders/po_items'>";
		setHiddenFields(newInputField);
		doSubmit("/asset/asset_po_browse.jsp", "AssetRetrieveByIcLineKey");
  	}

  	function setFirstFocus() {
		if (frm.as_itemNumber != undefined) {
			frm.as_itemNumber.focus();
		}
    }

	function browseInternalCatalog(catalogId) {
		frm.browseName.value = "catalogitem";
		setOriginalFilter("CatalogItem_id_catalogId", "=", catalogId);
		doSubmit('browse/item_browse.jsp', 'BrowseRetrieve');
	}

	function poSaveClassic()
	{
		var autoPoNumberClassic = '<%=propertiesManager.getProperty("AUTONUMBER", "AUTOPO", "N")%>';
		var showAutoPoNumberClassic = '<%=propertiesManager.getProperty("AUTONUMBER", "SHOWAUTOPO", "Y")%>';
		if (isNA(ponumber))
		{
			if (autoPoNumberClassic && !showAutoPoNumberClassic) {
				frm.PoHeader_poNumber.value = '';
				doSubmit('/orders/po_summary.jsp', "PoSave;PoHeaderUpdate;PoRetrieve");
			} else {
				popupParameters = "formtype=PO;formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=/orders/po_summary.jsp;currentprocessmethod=PoHeaderUpdate;PoRetrieve";
				//popupParameters = "formtype=PO;formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=save;";
				doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
			}
		}
		else
		{
			doSubmit('/orders/po_summary.jsp', "PoHeaderUpdate;PoRetrieve");
		}
	}

// End Hide script -->
</SCRIPT>