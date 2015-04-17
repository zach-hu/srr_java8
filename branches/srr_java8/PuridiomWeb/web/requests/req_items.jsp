<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<%@ page import="com.tsa.puridiom.menu.MenuManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.catalogsecurity.tasks.CatalogSecurityManager" %>
<%
	Encoder encoder = DefaultEncoder.getInstance();
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_req_ic_header = (String) request.getAttribute("RequisitionHeader_icReqHeader");
	String	s_req_number = "";
	String	s_req_status = (String) request.getAttribute("RequisitionHeader_status");
	String	s_req_type = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_requisitionType"));
	String	s_req_subtype = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_rqSubType"));
	String	s_external_website = HiltonUtility.ckNull((String) PropertiesManager.getInstance(oid).getProperty("REQ DEFAULTS", "EXTERNALWEBSITE","N"));
	String	s_fiscal_year = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_fiscalYear"));
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_itemLocation"));

	String	s_current_process = "SHOPCART";
	String	s_current_page = "/requests/req_items.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";

	boolean print_bsc_rm_msg = false;
	boolean print_bsc_bx_msg = false;
	boolean warningNonStandard = false;

	List	reqLineList = (List) request.getAttribute("requisitionLineList");
	int	i_linecount = 0;

	if (reqLineList != null)
	{
		i_linecount = reqLineList.size();

		if (i_linecount > 0)
		{
			RequisitionLine reqLine = (RequisitionLine) reqLineList.get(0);
			s_req_number = reqLine.getRequisitionNumber();
		}

		for (Iterator it = reqLineList.iterator(); it.hasNext(); )
	    {
			RequisitionLine rql = (RequisitionLine) it.next();
			if ( HiltonUtility.isEmpty(rql.getItemSource()) && !HiltonUtility.isEmpty(rql.getItemNumber()))
			{
				warningNonStandard = true;
			}
	    }

	}
	if (HiltonUtility.isEmpty(s_req_number))
	{
		s_req_number = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_requisitionNumber"));
	}

	if (HiltonUtility.isEmpty(s_req_number))	{	s_req_number = "N/A";	}
	if (HiltonUtility.isEmpty(s_req_status))	{	s_req_status = "1000";			}
	if (HiltonUtility.isEmpty(s_req_type))		{	s_req_type = "P";				}
	if (s_req_type.equals("M") && s_req_status.equals("1000")) s_req_status = "0500" ;


	String FilenameXls = null;
    if (request.getAttribute("FilenameXls") != null)
    {
    FilenameXls = (String) request.getAttribute("FilenameXls");
    }

    RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
    String s_curr_code = requisitionHeader.getCurrencyCode();

    boolean isCatalogSecurityActive = true;
	String catalogSecurityActive = PropertiesManager.getInstance(oid).getProperty("CATALOG SECURITY DEFAULTS", "CATALOGSECURITYACTIVE", "N");

	//isCatalogSecurityActive = catalogSecurityActive.equalsIgnoreCase("Y") && CatalogSecurityManager.getInstance().isEnabledPunchoutCatalog(oid,user);

	boolean bAllowEdit = true;
	if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0) {
		bAllowEdit = false;
	}

	if (s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) <= 0 && s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVING) >= 0)
	{
		bAllowEdit = false;
	}
	
	if(s_req_type.equals("P") && s_req_status.compareTo(DocumentStatus.REQ_REJECTED) == 0 && !user.isAFpe()){
		bAllowEdit = false;
	}
%>

<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value=""/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="${requisitionHeader.requisitionNumber}"/>
<tsa:hidden name="RequisitionHeader_status" value="${requisitionHeader.status}"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="${requisitionHeader.requisitionType}"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="${requisitionHeader.rqSubType}"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="${requisitionHeader.fiscalYear}"/>
<tsa:hidden name="RequisitionHeader_requiredBy" value="${RequisitionHeader_requiredBy}"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="${requisitionHeader.itemLocation}"/>
<tsa:hidden name="Account_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RQH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="lineCount" value="<%=i_linecount%>"/>
<tsa:hidden name="formtype" value="REQ"/>
<tsa:hidden name="frompage" value="shopcart"/>
<tsa:hidden name="punchOutReturnHandler" value=""/>
<tsa:hidden name="punchOutReturnSuccessPage" value=""/>
<tsa:hidden name="icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="showLinkGetItemInfo" value="N"/>
<tsa:hidden name="punchOutAddAccount" value="N"/>
<tsa:hidden name="nonStandardItem" value="Y"/>
<% if (oid.equalsIgnoreCase("wpc08p")) { %>
<tsa:hidden name="userNameUdf1" value="<%=user.getDepartment()%>"/>
<tsa:hidden name="userNameUdf2" value="<%=user.getNameUdf2()%>"/>
<tsa:hidden name="userNameUdf3" value="1770"/>
<% } %>
<tsa:hidden name="moduleType" value="${moduleType}"/>

<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
	<% if (s_req_type.equals("M")) {%>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="req-type" defaultString="Req Type"/>&nbsp;<tsa:label labelName="shopping_cart" defaultString="Shopping Cart"></tsa:label></div>
			</tsa:td>
		</tsa:tr>
	<% } else { %>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="shopping_cart" defaultString="Shopping Cart"></tsa:label></div>
			</tsa:td>
		</tsa:tr>
	<% } %>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="req-requisition" defaultString="Requisition #"></tsa:label>:</b></tsa:td>
			<tsa:td width="150px"><%=encoder.encodeForHTML(s_req_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="status" defaultString="Status"></tsa:label>:</b></tsa:td>
			<tsa:td width="150px">
				<% try {
					int rfqStatusNumber = Integer.parseInt(s_req_status);
					if (s_req_status.length() < 5) {%>
						<%=DocumentStatus.toString(s_req_status, oid)%>
					<% } %>
					<% } catch (Exception e) {} %>
			</tsa:td>
		</tsa:tr>
		</table>
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
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
<% if(s_external_website.equalsIgnoreCase("Y")){ %>
<table width="<%=formEntryWidth%>" cellpadding="0" cellspacing="0" border="0">
	<tsa:tr>
		<tsa:td align="right" cssClass="browseRow"><a href="javascript: window.open('https://login.ihserc.com/login/erc'); void(0);"><img src="<%=contextPath%>/images/cmd_search_item.gif" border="0" alt="Go to Haystack Login Page"></a>&nbsp;
		<a href="javascript: window.open('https://login.ihserc.com/login/erc'); void(0);" title="Click here go Haystack Login Page."><tsa:label labelName="haystackLoginPage" defaultString="Haystack Login Page"></tsa:label></a></tsa:td>
	</tsa:tr>
</table>
<br>
<% }%>

<div style="width: <%= formEntryWidth %>; align: center;">
	<div style="width: 76%; valign: top; float: left;">
		<!-- start rounded corners -->
		<div id="container" style="width: 100%; align: center; margin: 10;">
		<b class="rtop">
		  <b class="r1"></b> <b class="r2"></b> <b class="r3"></b> <b class="r4"></b>
		</b>
		<table cellpadding="0" cellspacing="2" border="0" class="browseHdr" width="100%">
		<tsa:tr>
			<tsa:td>
				<table border="0" cellpadding="2" cellspacing="0" width="100%">
				<tsa:tr>
					<tsa:td width="7%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="req-hdg-lineNumber" defaultString="Line #"></tsa:label></tsa:td>
					<tsa:td field="req-itemNumber" width="18%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="req-hdg-itemNumber" defaultString="Item/Part #"></tsa:label></tsa:td>
					<tsa:td field="req-commodity" width="15%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="req-hdg-commodity" defaultString="Commodity" checkRequired= "false"></tsa:label></tsa:td>
					<tsa:td field="req-quantity" width="10%" height="18px" cssClass="browseHdr" noWrap="nowrap" align="right"><tsa:label labelName="req-hdg-quantity" defaultString="Quantity"></tsa:label></tsa:td>
					<tsa:td field="req-uom" width="10%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="req-hdg-uom" defaultString="UOM" docType="s_req_type" checkRequired= "false"></tsa:label></tsa:td>
					<tsa:td field="req-unitCost" width="10%" height="18px" cssClass="browseHdr" noWrap="nowrap" align="right"><tsa:label labelName="req-hdg-unitCost" defaultString="Unit Cost" checkRequired= "false"></tsa:label></tsa:td>
					<tsa:td field="req-extendedCost" width="15%" height="18px" cssClass="browseHdr" noWrap="nowrap" align="right"><tsa:label labelName="req-hdg-extendedCost" defaultString="Extended Cost"></tsa:label></tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td width="100%" align="center" valign="top">
				<table id="itemRows" border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
<%		if (i_linecount <= 0) { %>
				<tsa:tr>
					<tsa:td align="center" colspan="7"><br><b><tsa:label labelName="noitems" defaultString="There are currently no items in your shopping cart." docType="s_req_type"></tsa:label></b><br><br></tsa:td>
				</tsa:tr>
<%		}
			for (int i = 0; i < i_linecount; i++)
			{
				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);
				BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid);
				BigDecimal bd_unit_price = HiltonUtility.getFormattedPrice(reqLine.getUnitPrice(), oid);
				BigDecimal bd_um_factor = HiltonUtility.getFormattedDollar(reqLine.getUmFactor(), oid);
				BigDecimal bd_extended_cost = HiltonUtility.getFormattedDollar(bd_quantity.multiply(bd_unit_price).multiply(bd_um_factor), oid);
%>
				<tsa:tr>
					<tsa:td width="7%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right"><a href="javascript: viewItem(<%=i%>); void(0);" title="Click here to View/Modify Item Details." onMouseOver="highlightRow(<%=i%>);" onMouseOut="removeHighlight(<%=i%>);"><%=i+1%></a>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="req-itemNumber" width="18%" height="18px" cssClass="browseRow" noWrap="nowrap">
						<%=reqLine.getItemNumber()%>
						<input type="hidden" id="icReqLine_<%=i %>" name="icReqLine_<%=i %>" value="<%=reqLine.getIcReqLine()%>">
					</tsa:td>
					<tsa:td field="req-commodity" width="15%" height="18px" cssClass="browseRow" noWrap="nowrap"><%=reqLine.getCommodityCode()%>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="req-quantity" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid)%>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="req-uom" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap"><%=reqLine.getUmCode()%></tsa:td>
					<tsa:td field="req-unitCost" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getCurrency(bd_unit_price, s_curr_code, oid)%>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="req-extendedCost" width="15%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getCurrency(bd_extended_cost, s_curr_code, oid)%>&nbsp;&nbsp;</tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td width="5%" height="18px" cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
					<%if(reqLine.getDescription() != null) {
						String desc = HiltonUtility.ckNull((String) reqLine.getDescription());
						String descAux = "";
						String cad[] = desc.split(" ");
						int a = 85, cont = 1;
						if(cad.length < 2)
						{
							for (int c = 0; c < desc.length(); c++)
							{
				        			if(c > a)
				        			{
				        				descAux += " " + desc.charAt(c);
				        				cont ++;
										a = 85;
				        				a = a * cont;
				        			}
				        			else
				        			{
				        				descAux += desc.charAt(c);
				        			}
							}
						} else {
							descAux = desc;
						} %>
						<tsa:td colspan="6" height="18px" cssClass="browseRow"><%=descAux%></tsa:td>
					<% } %>
				</tsa:tr>
				<%if(oid.equalsIgnoreCase("bly07p")) {%>
				<tsa:tr>
					<tsa:td>&nbsp;</tsa:td>
					<tsa:td width="5%" height="18px" cssClass="browseRow" noWrap="nowrap"><tsa:label labelName="req-LN09" defaultString="Line UDF9" checkRequired="true"></tsa:label>&nbsp;</tsa:td>
					<tsa:td colspan="4" height="18px" cssClass="browseRow"><%=reqLine.getUdf9Code()%></tsa:td>
				</tsa:tr>
				<%}%>
<%
				if((reqLine.getUmCode()).equals("RM") && (reqLine.getCatalogId()).equals("LETTERHEAD")){
					print_bsc_rm_msg = true;
				}
				if((reqLine.getUmCode()).equals("BX") && (reqLine.getCatalogId()).equals("ENVELOPE")){
					print_bsc_bx_msg = true;
				}		%>
<%		} %>
				</table>
			</tsa:td>
		</tsa:tr>
		</table>
		<b class="rbottom">
		  <b class="r4"></b> <b class="r3"></b> <b class="r2"></b> <b class="r1"></b>
		</b>
		</div>
		<!-- end rounded corners -->
<%	if (bAllowEdit) { %>
		<div style="margin: 10; valign: top">
	<%	if (propertiesManager.getProperty("REQ OPTIONS", "RestrictNonStandardItem", "N").equalsIgnoreCase("N")) { %>
		<% if ( ! (s_req_type.equalsIgnoreCase("S") || s_req_type.equalsIgnoreCase("T") || s_req_type.equalsIgnoreCase("R") || s_req_type.equalsIgnoreCase("I"))) { %>
			<tsa:hidden name="createAction" value="SAVE"/>
			<table  border="0" cellpadding="0" cellspacing="0">
				<tsa:tr>
					<tsa:td valign="middle"><a href="javascript: addItem();"><img src="<%=contextPath%>/images/cmd_add_item.gif" border="0" alt='<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addnonstditem", "Add Non-Standard Item")%>'><font style="text-decoration: none;"></font></a>&nbsp;</tsa:td>
					<tsa:td valign="middle"><a href="javascript: addItem();" title="All services and any goods that are not listed in a catalog."><tsa:label labelName="addnonstditem" defaultString="Add Non-Standard Item"></tsa:label></a></tsa:td>
					<tsa:td width="60px"></tsa:td>
				</tsa:tr>
			</table>
	<%	}%>
	<%	}%>
			<br>
				<!-- Start fieldset -->
				<fieldset style="width: 450px"><legend><tsa:label labelName="searchOptions" defaultString="Search Options"></tsa:label></legend>
				<table border="0" cellspacing="0" cellpadding="2" width="100%" align="center" class="browseRow">
				<tsa:tr>
					<tsa:td valign="top" cssClass="browseRow">
						<table border="0" cellspacing="0" cellpadding="2">
						<tsa:tr><tsa:td valign="middle" noWrap="nowrap"><tsa:label labelName="keyword" defaultString="Keyword(s)"></tsa:label>:</tsa:td></tsa:tr>
						<tsa:tr>
							<tsa:td valign="middle"><tsa:input type="text" title="Enter descriptive keyword(s) about your product to narrow your search" name="as_keywords" value="" onkeypress="if (event.keyCode == 13) event.returnValue = false;" onblur="document.all('searchbutton').focus();return false;" size="60" /></tsa:td>
						</tsa:tr>
						</table>
						<br>
						<table border="0" cellpadding="0" cellspacing="0">
						<tsa:tr>
							<tsa:td valign="middle"><a id="itemsearchbutton" href="javascript: doSubmit('/browse/item_filter_options.jsp', 'CatalogRetrieveActive'); void(0);"><img src="<%=contextPath%>/images/cmd_search_item.gif" border="0" alt="Advanced Item Search"></a>&nbsp;</tsa:td>
							<tsa:td valign="middle"><a href="javascript: doSubmit('/browse/item_filter_options.jsp', 'CatalogRetrieveActive'); void(0);" title='<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-advancedItemSearch-instructions", "Click here to perform an Advanced Item Search.")%>' ><tsa:label labelName="advancedItemSearch" defaultString="Advanced Item Search"></tsa:label></a></tsa:td>
						</tsa:tr>
						</table>
					</tsa:td>
					<tsa:td valign="top" cssClass="browseRow">
						<div id="itemtype" style="visibility: visible; display: block;">
					<% if (s_req_type.equals("P") && propertiesManager.getProperty("REQUISITIONS","INTELLIGENTPURCHASEREQ","N").equalsIgnoreCase("Y")) {%>
						<br><tsa:hidden name="as_item_type" value="CON"/>
					<% } else if ( ! (s_req_type.equalsIgnoreCase("S") || s_req_type.equalsIgnoreCase("T") || s_req_type.equalsIgnoreCase("R") || s_req_type.equalsIgnoreCase("I"))) { %>
						<br><tsa:hidden name="as_item_type" value="CAT"/>
					<% } else { %>
						<br><tsa:hidden name="as_item_type" value="INV"/>
					<% } %>
						<table border="0" cellspacing="0" cellpadding="1">
						<!--tr>
							<td valign=middle align=right><input type=radio name="as_item_type" tabindex=10 value="CAT" CHECKED></td>
							<td valign=middle nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "puridiom", "Puridiom")%></td>
						</tr>
						<tr>
							<td valign=middle align=right><input type=radio name="as_item_type" tabindex=12 value="INTERNET"></td>
							<td valign=middle nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "internet", "Internet")%></td>
						</tr-->
						<tsa:tr><tsa:td colspan="2"><div id="pxmediumbutton"><a href="javascript: itemSearch(); void(0);" id="searchbutton" onblur="document.all('itemsearchbutton').focus();return false;""><tsa:label labelName="req-search" defaultString="Search"></tsa:label></a></div></tsa:td></tsa:tr>
						</table>
						</div>
					</tsa:td>
				</tsa:tr>
				</table>
				</fieldset>
				<br><br>
				<!-- Start fieldset -->
		<% if ( ! (s_req_type.equalsIgnoreCase("S") || s_req_type.equalsIgnoreCase("T") || s_req_type.equalsIgnoreCase("R") || s_req_type.equalsIgnoreCase("I") || s_req_type.equalsIgnoreCase("N"))) { %>
				<fieldset style="width: 225px; valign: top;"><legend><tsa:label labelName="puridiomMarketPlace" defaultString="Puridiom Marketplace"></tsa:label></legend>
				<table border="0" cellspacing="0" cellpadding="2" width="100%" align="center" class="browseRow">
				<tsa:tr>
					<tsa:td valign="top" cssClass="browseRow" align="center">
						<table border="0" cellpadding="2" cellspacing="0">
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
						<tsa:tr>
							<tsa:td valign="middle"><a href="javascript: browseExternalCatalog('<%=catalog.getCatalogId()%>'); void(0);"><img src="<%=contextPath%>/images/marketplace/<%=catalog.getCatalogId().toLowerCase()%>.gif" border="1" alt="Search <%=catalog.getTitle()%> Catalog" width="140px" height="34px"></a>&nbsp;</tsa:td>
						</tsa:tr>
		<%			}
			}
			} %>
						</table>
					</tsa:td>
				</tsa:tr>
				</table>
				</fieldset>

				<!-- Start fieldset -->
				<fieldset style="width: 350px; valign: top; align: center;"><legend><tsa:label labelName="puridiomOnlineCatalogs" defaultString="Puridiom Online Catalogs"></tsa:label></legend>
				<table border="0" cellspacing="0" cellpadding="2" width="100%" align="center" class="browseRow">
				<tsa:tr>
					<tsa:td valign="top" cssClass="browseRow">
						<div class="browseHdrDk" style="border: solid 0px; background: #FFFFFF; padding: 2px; width: 350px; height: 125px; align: center; overflow-y: auto">
						<table border="0" cellpadding="2" cellspacing="0">
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

					String catalogActive = "";
					if (catalogSecurityActive.equalsIgnoreCase("Y"))
					{
						catalogActive = CatalogSecurityManager.getInstance().isEnabledOthersCatalog(oid, user, (String)catalog.getCatalogId());
					}
					else
					{
						catalogActive = "F";
					}

					if (catalogActive.equalsIgnoreCase("Y"))
					{
		%>
						<tsa:tr>
							<tsa:td valign="middle"><a href="javascript: browseInternalCatalogUnAssigned('<%=catalog.getCatalogId()%>', '<%=user.getUserId() %>'); void(0);" title="Click here to Search the <%=catalog.getTitle()%> Catalog."><%=catalog.getCatalogId()%></a> - <%=catalog.getTitle()%></tsa:td>
						</tsa:tr>
		<%			} else if (catalogActive.equalsIgnoreCase("F")) { %>

						<tsa:tr>
							<tsa:td valign="middle"><a href="javascript: browseInternalCatalog('<%=catalog.getCatalogId()%>'); void(0);" title="Click here to Search the <%=catalog.getTitle()%> Catalog."><%=catalog.getCatalogId()%></a> - <%=catalog.getTitle()%></tsa:td>
						</tsa:tr>
		<%
					}
				}
			}
		%>
						</table>
						</div>
					</tsa:td>
				</tsa:tr>
				</table>
				</fieldset>
<% } %>
			</div>
<% } %>
	</div>

<%	if (s_view.equals("WIZARD")) { %>
	<div style="align: right;"><%@ include file="/requests/req_sidebar.jsp" %></div>
<%	} %>
</div>

<br>
<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
	<tsa:td width="50%" align="center"><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionHeaderUpdate;RequisitionRetrieve'); void(0);"><img class="button" src="<%=contextPath%>/images/button_save.gif" border="0" alt="Save"></a></tsa:td>
	<tsa:td width="50%" align="center"><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionRetrieve'); void(0);"><img class="button" src="<%=contextPath%>/images/button_return.gif" border="0" alt="Return"></a></tsa:td>
</tsa:tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value="JavaScript">
<!-- Hide script

	frm = document.purchaseform;
	var reqnumber = "<%=encoder.encodeForJavaScript(s_req_number)%>";
	var fiscalyear = "<%=encoder.encodeForJavaScript(s_fiscal_year)%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var itemLocation = "<%=encoder.encodeForJavaScript(s_item_location)%>";

//	setTableHeights();

	var navMenu = getNavCookie("navArray");
	if (navMenu.indexOf("<%=RequisitionType.toString(s_req_type, oid)%>") < 0)
	{
		setNavCookie("/requests/req_items.jsp", "RequisitionRetrieve", "<%=RequisitionType.toString(s_req_type, oid)%>");
	}

    function ToTotals(){
    doSubmit("/requests/req_totals.jsp", "RequisitionRetrieve");
    }


	function ToReview(){
    doSubmit("/requests/req_review.jsp", "RequisitionHeaderUpdate;RequisitionRetrieve");
    }


   // <%
   // if(FilenameXls!=null)
   // {
   // %>
   // //ToTotals();
   // ToReview();
   // <%
   // }
   // %>



	function thisLoad() {
		f_StartIt();
	}

	function setTableHeights() {
		setTableHeight("itemTable", "browseRows", 18);
	}

	function setTableHeight(hdrTableName, rowsTableName, rowHeight) {
		var browseTable = document.getElementById(hdrTableName);
		var rowsTable = document.getElementById(rowsTableName);

		var tableHeight = ((rowsTable.rows.length) * rowHeight) + 20;

		browseTable.style.height = tableHeight + "px";
	}

	function viewItem(row)
	{
		var num = document.getElementById("icReqLine_" + row);
		frm.RequisitionLine_icReqLine.value = num.value;
		doSubmit('/requests/req_item.jsp','RequisitionLineRetrieve');
	}

	function removeHighlight(row) {
		row = row * 2;
		var myTable = document.getElementById("browseRows");
		var myRow = myTable.rows[row];

		setRowClassName(myRow, "browseRow");

		myRow = myTable.rows[row + 1];

		setRowClassName(myRow, "browseRow");
	}

	function addItem()
	{
		var line_count = frm.lineCount.value;
		line_count++;
		frm.lineCount.value = line_count;
		frm.showLinkGetItemInfo.value = "Y";

		<% if (oid.equalsIgnoreCase("hoy08p")) { %>
			frm.nonStandardItem.value = "N";
		<% } %>
		doSubmit('/requests/req_item.jsp','RequisitionLineCreate');
	}

	function browseExternalCatalog(catalog) {
		var punchoutHandlers = "BrowseExternalWebCatalog";
		var handlers = "RequisitionRetrieve";
<% if (s_view.equals("CLASSIC")) { %>
		var pg = "/requests/req_summary.jsp";
<%	} else if (s_view.equals("WIZARD")) { %>
		var pg = "/requests/req_review.jsp";
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

		<%	if (PropertiesManager.getInstance(oid).getProperty("ACCOUNTS", "PUNCHOUTADDACCOUNT", "N").equalsIgnoreCase("Y")) { %>
		frm.punchOutAddAccount.value = "Y";
		<%	} %>

		if (isEmpty(frm.RequisitionHeader_requisitionNumber.value) || frm.RequisitionHeader_requisitionNumber.value == "N/A") {
			handlers = "RequisitionGetFormNumber;" + handlers;
		}

		frm.punchOutReturnHandler.value = "ExternalItemLookup;" + handlers;
		frm.punchOutReturnSuccessPage.value = pg;

		doSubmit('/system/error.jsp', punchoutHandlers);
	}

	function setFirstFocus() {
		if (frm.as_itemNumber != undefined) {
			frm.as_itemNumber.focus();
		}
    }

    function validateForm()
	{
		return true;
	}

	function browseInternalCatalogUnAssigned(catalogId, userId) {
		frm.browseName.value = "catalogitemUnAssigned";
		frm.as_item_type.value = "CAT";
		setOriginalFilter("CatalogItem_id_catalogId", "=", catalogId);
		setOriginalFilter("CatalogSecurity_accessId", "=", userId);
		doSubmit('browse/item_browse.jsp', 'BrowseRetrieve');
	}

	function browseInternalCatalog(catalogId) {
		frm.browseName.value = "catalogitem";
		frm.as_item_type.value = "CAT";
		setOriginalFilter("CatalogItem_id_catalogId", "=", catalogId);
		doSubmit('browse/item_browse.jsp', 'BrowseRetrieve');
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

// End Hide script -->
</SCRIPT>