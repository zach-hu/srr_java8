<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.menu.MenuManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.catalogsecurity.tasks.CatalogSecurityManager" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/browse.js"></SCRIPT>
<%
	Encoder encoder = DefaultEncoder.getInstance();
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String	s_rfqNumber = "";
	String	s_rfqAmendment = HiltonUtility.ckNull((String) request.getAttribute("RfqHeader_rfqAmendment"));
	String	s_rfqStatus = (String) request.getAttribute("RfqHeader_status");
	String	s_rfqType = (String) request.getAttribute("RfqHeader_rfqType");
	String	s_fiscalYear = (String) request.getAttribute("RfqHeader_fiscalYear");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("RfqHeader_itemLocation"));
	
	RfqHeader rfqHeader = (RfqHeader)request.getAttribute("rfqHeader");

	String	s_current_process = "SHOPCART";
	String	s_current_page = "/rfq/rfq_items.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";

	List	rfqLineList = (List) request.getAttribute("rfqLineList");
	int	i_linecount = 0;

	if (rfqLineList != null)
	{
		i_linecount = rfqLineList.size();

		if (i_linecount > 0)
		{
			RfqLine rfqLine = (RfqLine) rfqLineList.get(0);
			s_rfqNumber = rfqLine.getRfqNumber();
		}
	}
	if (HiltonUtility.isEmpty(s_rfqNumber))
	{
		s_rfqNumber = (String) request.getAttribute("RfqHeader_rfqNumber");
	}
	if (HiltonUtility.isEmpty(s_rfqStatus))	{	s_rfqStatus = DocumentStatus.RFQ_INPROGRESS;			}
	if (HiltonUtility.isEmpty(s_rfqType))		{	s_rfqType = "RQ";				}


	String FilenameXls = null;
    if(request.getAttribute("FilenameXls")!= null)
    {
    FilenameXls = (String) request.getAttribute("FilenameXls");
    }

	boolean editMode = false;

	if (role.getAccessRights("RFQ") > 1 && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENAMENDMENT) == 0)) {
		editMode = true;
	}
    boolean isCatalogSecurityActive = true;
	String catalogSecurityActive = PropertiesManager.getInstance(oid).getProperty("CATALOG SECURITY DEFAULTS", "CATALOGSECURITYACTIVE","N");

	//isCatalogSecurityActive = catalogSecurityActive.equalsIgnoreCase("Y") && CatalogSecurityManager.getInstance().isEnabledPunchoutCatalog(oid,user);
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqLine_icRfqLine" value=""/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="${RfqHeader_rfqAmendment}"/>
<tsa:hidden name="RfqHeader_status" value="${RfqHeader_status}"/>
<tsa:hidden name="RfqHeader_rfqType" value="${RfqHeader_rfqType}"/>
<tsa:hidden name="RfqHeader_fiscalYear" value="${RfqHeader_fiscalYear}"/>
<tsa:hidden name="RfqHeader_requiredBy" value="<%=request.getAttribute(\"RfqHeader_requiredBy\")%>"/>
<tsa:hidden name="RfqQuestion_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="lineCount" value="<%=i_linecount%>"/>
<tsa:hidden name="formtype" value="RFQ"/>
<tsa:hidden name="frompage" value="shopcart"/>
<tsa:hidden name="punchOutReturnHandler" value=""/>
<tsa:hidden name="punchOutReturnSuccessPage" value=""/>
<tsa:hidden name="icHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="showLinkGetItemInfo" value="N"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="shopping_cart" defaultString="Shopping Cart" checkRequired="false" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/rfq/rfq_status_title.jsp" %>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
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
					<td width=7% height=18px class=browseHdr nowrap><tsa:label labelName="rfq-hdg-lineNumber" defaultString="Line #" /></td>
					<tsa:td field="rfq-itemNumber" width="18%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="rfq-hdg-itemNumber" defaultString="Item/Part #" /></tsa:td>
					<tsa:td field="rfq-commodity" width="15%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="rfq-hdg-commodity" defaultString="Commodity" /></tsa:td>
					<tsa:td field="rfq-quantity" width="10%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="rfq-hdg-quantity" defaultString="Quantity" /></tsa:td>
					<tsa:td field="rfq-uom" width="10%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="rfq-hdg-uom" defaultString="UOM" /></tsa:td>
					<tsa:td field="rfq-lineStatus" width="15%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="rfq-hdg-lineStatus" defaultString="Line Status" /></tsa:td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<table id=itemRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (i_linecount <= 0) { %>
				<tr>
					<td align=center><br><b><tsa:label labelName="noitems" defaultString="There are currently no items in your shopping cart." /></b><br><br></td>
				</tr>
<%		}
			for (int i = 0; i < i_linecount; i++)
			{
				RfqLine rfqLine = (RfqLine) rfqLineList.get(i);
				BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), oid);

				BigDecimal bd_um_factor = HiltonUtility.getFormattedDollar(rfqLine.getUmFactor(), oid);
				pageContext.setAttribute("i", i);
%>
				<tr>
					<td width=7% height=18px class=browseRow nowrap align=left>&nbsp;&nbsp;<a href="javascript: viewItem(<%=i%>); void(0);" title="Click here to View/Modify Item Details." onMouseOver="highlightRow(<%=i%>);" onMouseOut="removeHighlight(<%=i%>);" title="Click here to View/Modify Item Details"><%=i + 1%></a>&nbsp;&nbsp;</td>
					<tsa:td field="rfq-itemNumber" width="18%" height="18px" cssClass="browseRow" noWrap="nowrap">
						<%=HiltonUtility.ckNull(rfqLine.getItemNumber())%>
						<tsa:hidden id="icRfqLine_${i}" name="icRfqLine_${i}" value="<%=rfqLine.getIcRfqLine() %>"/>
					</tsa:td>
					<tsa:td field="rfq-commodity" width="15%" height="18px" cssClass="browseRow" noWrap="nowrap"><%=rfqLine.getCommodity()%>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="rfq-quantity" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap" ><%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), oid)%>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="rfq-uom" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap"><%=rfqLine.getUmCode()%></tsa:td>
					<tsa:td field="rfq-lineStatus" width="15%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right"><%=DocumentStatus.toString(rfqLine.getStatus())%>&nbsp;&nbsp;</tsa:td>
				</tr>
				<tr>
					<td width=5% height=18px class=browseRow nowrap>&nbsp;</td>
					<td colspan=5 height=18px class=browseRow><%=rfqLine.getDescription()%></td>
				</tr>
<%	} %>
				</table>
			</td>
		</tr>
		</table>
		<b class="rbottom">
		  <b class="r4"></b> <b class="r3"></b> <b class="r2"></b> <b class="r1"></b>
		</b>
		</div>
		<!-- end rounded corners -->



<%	if (editMode) { %>
		<div style="margin:10;valign:top">
		<br><br>
		<tsa:hidden name="createAction" value="SAVE"/>
		<table  border=0 cellpadding=0 cellspacing=0>
		<tr>
			<td valign=middle><a href="javascript: addItem();"><img src="<%=contextPath%>/images/cmd_add_item.gif" border=0 alt="<tsa:label labelName="addnonstditem" defaultString="Add Non-Standard Item" />"><font style="text-decoration: none;"></font></a>&nbsp;</td>
			<td valign=middle><a href="javascript: addItem();" title="All services and any goods that are not listed in a catalog."><tsa:label labelName="addnonstditem" defaultString="Add Non-Standard Item" /></a></td>
			<td width=60px></td>
		</tr>
		</table>
			<br>
				<!-- Start fieldset -->
				<fieldset style="width:450px"><legend><tsa:label labelName="searchOptions" defaultString="Search Options" /></legend>
				<table border=0 cellspacing=0 cellpadding=2 width=100% align=center class=browseRow>
				<tr>
					<td valign=top class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2>
						<tr><td valign=middle nowrap><tsa:label labelName="keyword" defaultString="Keyword(s)" />:</td></tr>
						<tr>
							<td valign=middle><tsa:input labelName="keyword" type="text" title="Enter descriptive keyword(s) about your product to narrow your search" name="as_keywords" value="" onkeypress="if (event.keyCode == 13) event.returnValue = false;" onblur="document.all('searchbutton').focus();return false;" size="60" /></td>
						</tr>
						</table>
						<br>
						<table border=0 cellpadding=0 cellspacing=0>
						<tr>
							<td valign=middle><a id="itemsearchbutton" href="javascript: doSubmit('/browse/item_filter_options.jsp', 'CatalogRetrieveActive'); void(0);"><img src="<%=contextPath%>/images/cmd_search_item.gif" border=0 alt="Advanced Item Search"></a>&nbsp;</td>
							<td valign=middle><a href="javascript: doSubmit('/browse/item_filter_options.jsp', 'CatalogRetrieveActive'); void(0);" title="<tsa:label labelName="rfq-advancedItemSearch-instructions" defaultString="Click here to perform an Advanced Item Search." />" ><tsa:label labelName="advancedItemSearch" defaultString="Advanced Item Search" /></a></td>
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
						<tr><td colspan=2><div id="pxmediumbutton"><a href="javascript: itemSearch(); void(0);" id="searchbutton" onblur="document.all('itemsearchbutton').focus();return false;"">Search</a></div></td></tr>
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
		%>
						<tr>
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

<%	} %>
</div>
<%	if (s_view.equals("WIZARD")) { %>
	<div style="align: right;"><%@ include file="/rfq/rfq_sidebar.jsp" %></div>
<%	} %>
</div>
<br>
<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=550px>
<tr>
<%	if (editMode) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqHeaderUpdateById;RfqRetrieve'); void(0);"><tsa:label labelName="rfq-Save" defaultString="Save" /></a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqRetrieve'); void(0);"><tsa:label labelName="rfq-Return" defaultString="Return" /></a></div></td>
<%	} else {%>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqRetrieve'); void(0);"><tsa:label labelName="rfq-Return" defaultString="Return" /></a></div></td>
<%	}%>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var rfqnumber = "<%=encoder.encodeForJavaScript(s_rfqNumber)%>";
	var fiscalyear = "<%=encoder.encodeForJavaScript(s_fiscalYear)%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var itemLocation = "<%=encoder.encodeForJavaScript(s_item_location)%>";

	var browser = browserCheck();

//	setTableHeights();

	var navMenu = getNavCookie("navArray");
	if (navMenu.indexOf("<%=RfqType.toString(s_rfqType, oid)%>") < 0)
	{
		setNavCookie("/rfq/rfq_items.jsp", "RfqRetrieve", "<%=RfqType.toString(s_rfqType, oid)%>");
	}

    function ToReview(){
    doSubmit("/rfq/rfq_review.jsp", "RfqRetrieve");
    }


   function setTableHeights() {
		setTableHeight("itemTable", "itemRows", 18);
	}

	function setTableHeight(hdrTableName, rowsTableName, rowHeight) {
		var browseTable = document.getElementById(hdrTableName);
		var rowsTable = document.getElementById(rowsTableName);

		var tableHeight = ((rowsTable.rows.length) * rowHeight) + 20;

		browseTable.style.height = tableHeight + "px";
	}

	function viewItem(row)
	{
		var num = document.getElementById("icRfqLine_" + row);
		frm.RfqLine_icRfqLine.value = num.value;
		doSubmit('/rfq/rfq_item.jsp','RfqLineRetrieve');
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

		doSubmit('/rfq/rfq_item.jsp','RfqLineCreate');
	}

	function browseExternalCatalog(catalog) {
		var punchoutHandlers = "BrowseExternalWebCatalog";
		var handlers = "RfqRetrieve";
<%	if (s_view.equals("CLASSIC")) { %>
		var pg = "/rfq/rfq_summary.jsp";
<%	} else if (s_view.equals("WIZARD")) { %>
		var pg = "/rfq/rfq_review.jsp";
<%	}%>
		var hiddenFields = "<input type=hidden name=\"Catalog_catalogId\" value=\"" + catalog + "\">";
		setHiddenFields(hiddenFields);

		frm.punchOutReturnHandler.value = "ExternalItemLookup;" + handlers;
		frm.punchOutReturnSuccessPage.value = pg;

		doSubmit('/system/error.jsp', punchoutHandlers);
	}

	function browseInternalCatalog(catalogId) {
		frm.browseName.value = "catalogitem";
		setOriginalFilter("CatalogItem_id_catalogId", "=", catalogId);
		doSubmit('browse/item_browse.jsp', 'BrowseRetrieve');
	}

	function setFirstFocus() {
		if (frm.as_itemNumber != undefined) {
			frm.as_itemNumber.focus();
		}
    }

// End Hide script -->
</SCRIPT>