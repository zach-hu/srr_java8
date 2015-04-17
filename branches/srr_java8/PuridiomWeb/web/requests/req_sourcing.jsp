<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<%@ page import="com.tsa.puridiom.menu.MenuManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.catalogsecurity.tasks.CatalogSecurityManager" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_req_ic_header = (String) request.getAttribute("RequisitionHeader_icReqHeader");
	String	s_req_number = "";
	String	s_req_status = (String) request.getAttribute("RequisitionHeader_status");
	String	s_req_type = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_requisitionType"));
	String	s_req_subtype = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_rqSubType"));
	String	s_fiscal_year = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_fiscalYear"));
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_itemLocation"));
	String	s_external_website = HiltonUtility.ckNull((String) PropertiesManager.getInstance(oid).getProperty("REQ DEFAULTS", "EXTERNALWEBSITE","N"));
	Encoder encoder = DefaultEncoder.getInstance();

	String s_reqline_header = (String) request.getAttribute("RequisitionLine_icReqHeader");
	if (s_reqline_header == null) s_reqline_header = "0";

	String	s_current_process = "SOURCING";
	String	s_current_page = "/requests/req_sourcing.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";

	List	reqLineList = (List) request.getAttribute("requisitionLineList");
	List	reqHeaderResultList = new ArrayList();
	List	poHeaderResultList = new ArrayList();
	List	recHeaderResultList = new ArrayList();
	List	invHeaderResultList = new ArrayList();

	try {
		reqHeaderResultList = (List) request.getAttribute("requisitionLineHistoryReqList");
		poHeaderResultList = (List) request.getAttribute("requisitionLineHistoryPoList");
		recHeaderResultList = (List) request.getAttribute("requisitionLineHistoryRecList");
		invHeaderResultList = (List) request.getAttribute("requisitionLineHistoryInvList");
	}catch (Exception e) {
	}
	int	i_linecount = 0;

	if (reqLineList != null)
	{
		i_linecount = reqLineList.size();

		if (i_linecount > 0)
		{
			RequisitionLine reqLine = (RequisitionLine) reqLineList.get(0);
			s_req_number = reqLine.getRequisitionNumber();
		}
	}

	if (HiltonUtility.isEmpty(s_req_number))
	{
		s_req_number = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_requisitionNumber"));
	}

	if (HiltonUtility.isEmpty(s_req_number))	{	s_req_number = "N/A";	}
	if (HiltonUtility.isEmpty(s_req_status))	{	s_req_status = "0535";			}
	if (HiltonUtility.isEmpty(s_req_type))		{	s_req_type = "M";				}

    RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
    String s_curr_code = requisitionHeader.getCurrencyCode();

    boolean isCatalogSecurityActive = true;
	String catalogSecurityActive = PropertiesManager.getInstance(oid).getProperty("CATALOG SECURITY DEFAULTS", "CATALOGSECURITYACTIVE", "N");

	//isCatalogSecurityActive = catalogSecurityActive.equalsIgnoreCase("Y") && CatalogSecurityManager.getInstance().isEnabledPunchoutCatalog(oid,user);

	boolean bAllowEdit = true;
%>

<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>
<script language="javascript" src="gwt/MSRRequest/MSRRequest.nocache.js"></script>
<LINK href="system/styles/orange.css" rel="stylesheet" type="text/css">

<script>
	var prevSelectedIndex = 0;
	<%
		List<String> item_number = new ArrayList<String>();
		List<String> supplier_id = new ArrayList<String>();
		List<String> catalog = new ArrayList<String>();
		List<String> proc_level = new ArrayList<String>();
		List<String> commodity = new ArrayList<String>();
		List<String> ind_class = new ArrayList<String>();
		List<String> quantity = new ArrayList<String>();
		List<String> unit_price = new ArrayList<String>();
		List<String> um_factor = new ArrayList<String>();
		List<String> extended_cost = new ArrayList<String>();
		List<String> ic_history = new ArrayList<String>();
		List<String> ic_req_line = new ArrayList<String>();
		List<String> description = new ArrayList<String>();
		List<String> item_location = new ArrayList<String>();
		List<String> blanket_order = new ArrayList<String>();
		List<String> req_number = new ArrayList<String>();
		List<String> req_status = new ArrayList<String>();
		List<String> req_location = new ArrayList<String>();
		List<String> po_number = new ArrayList<String>();
		List<String> po_status = new ArrayList<String>();
		List<String> rec_number = new ArrayList<String>();
		List<String> rec_status = new ArrayList<String>();
		List<String> inv_number = new ArrayList<String>();
		List<String> inv_status = new ArrayList<String>();
		List<String> item_source = new ArrayList<String>();
		List<String> line_number = new ArrayList<String>();
		List<String> rad_nuclear = new ArrayList<String>();
		List<String> mark_tag = new ArrayList<String>();
		List<String> traceability = new ArrayList<String>();
		List<String> header = new ArrayList<String>();
		List<String> ic_po_line = new ArrayList<String>();

		for (int i = 0; i < i_linecount; i++)
		{
			RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);

			try{
				List reqHeaderList = (List) reqHeaderResultList.get(i);
				RequisitionHeader reqHeader = (RequisitionHeader) reqHeaderList.get(0);
				req_number.add("\"" + encoder.encodeForJavaScript(reqHeader.getRequisitionNumber().replaceAll("\\r|\\n", "")) + "\"");
				req_status.add("\"" + encoder.encodeForJavaScript(DocumentStatus.toString(reqHeader.getStatus(), oid).replaceAll("\\r|\\n", "")) + "\"");
				req_location.add("\"" + encoder.encodeForJavaScript(reqHeader.getItemLocation().replaceAll("\\r|\\n", "")) + "\"");

			} catch (Exception e) {
				req_number.add("\"\"");
				req_status.add("\"\"");
			}
			try{
				List poHeaderList = (List) poHeaderResultList.get(i);
				PoHeader poHeader = (PoHeader) poHeaderList.get(0);
				po_number.add("\"" + encoder.encodeForJavaScript(poHeader.getPoNumber().replaceAll("\\r|\\n", "")) + "\"");
				po_status.add("\"" + encoder.encodeForJavaScript(DocumentStatus.toString(poHeader.getStatus(), oid).replaceAll("\\r|\\n", "")) + "\"");
			} catch (Exception e){
				po_number.add("\"\"");
				po_status.add("\"\"");
			}
			try{
				List recHeaderList = (List) recHeaderResultList.get(i);
				ReceiptHeader recHeader = (ReceiptHeader) recHeaderList.get(0);
				rec_number.add("\"" + encoder.encodeForJavaScript(recHeader.getReceiptNumber().replaceAll("\\r|\\n", "")) + "\"");
				rec_status.add("\"" + encoder.encodeForJavaScript(DocumentStatus.toString(recHeader.getReceiptStatus(), oid).replaceAll("\\r|\\n", "")) + "\"");
			} catch (Exception e){
				rec_number.add("\"\"");
				rec_status.add("\"\"");
			}
			try{
				List invHeaderList = (List) invHeaderResultList.get(i);
				InvoiceHeader invHeader = (InvoiceHeader) invHeaderList.get(0);
				inv_number.add("\"" + encoder.encodeForJavaScript(invHeader.getInvoiceNumber().replaceAll("\\r|\\n", "")) + "\"");
				inv_status.add("\"" + encoder.encodeForJavaScript(DocumentStatus.toString(invHeader.getStatus(), oid).replaceAll("\\r|\\n", "")) + "\"");
			} catch (Exception e){
				inv_number.add("\"\"");
				inv_status.add("\"\"");
			}

			BigDecimal bd_line_number = HiltonUtility.getFormattedQuantity(reqLine.getLineNumber(), oid);
			line_number.add("\"" + encoder.encodeForJavaScript(Integer.toString(bd_line_number.intValue()).replaceAll("\\r|\\n", "")) + "\"");

			item_number.add("\"" + encoder.encodeForJavaScript(reqLine.getItemNumber().replaceAll("\\r|\\n", "")) + "\"");
			supplier_id.add("\"" + encoder.encodeForJavaScript(reqLine.getVendorId().replaceAll("\\r|\\n", "")) + "\"");
			catalog.add("\"" + encoder.encodeForJavaScript(reqLine.getCatalogId().replaceAll("\\r|\\n", "")) + "\"");
			proc_level.add("\"" + encoder.encodeForJavaScript(reqLine.getUdf2Code().replaceAll("\\r|\\n", "")) + "\"");
			commodity.add("\"" + encoder.encodeForJavaScript(reqLine.getCommodityCode().replaceAll("\\r|\\n", "")) + "\"");
			ind_class.add("\"" + encoder.encodeForJavaScript(reqLine.getUdf4Code().replaceAll("\\r|\\n", "")) + "\"");

			BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid);
			BigDecimal bd_unit_price = HiltonUtility.getFormattedPrice(reqLine.getUnitPrice(), oid);
			BigDecimal bd_um_factor = HiltonUtility.getFormattedDollar(reqLine.getUmFactor(), oid);
			BigDecimal bd_extended_cost = HiltonUtility.getFormattedDollar(bd_quantity.multiply(bd_unit_price).multiply(bd_um_factor), oid);

			quantity.add("\"" + encoder.encodeForJavaScript(bd_quantity.toString().replaceAll("\\r|\\n", "")) + "\"");
			unit_price.add("\"" + encoder.encodeForJavaScript(bd_unit_price.toString().replaceAll("\\r|\\n", "")) + "\"");
			um_factor.add("\"" + encoder.encodeForJavaScript(bd_um_factor.toString().replaceAll("\\r|\\n", "")) + "\"");
			extended_cost.add("\"" + encoder.encodeForJavaScript(bd_extended_cost.toString().replaceAll("\\r|\\n", "")) + "\"");
			ic_history.add("\"" + encoder.encodeForJavaScript(reqLine.getIcLineHistory().toString().replaceAll("\\r|\\n", "")) + "\"");
			ic_req_line.add("\"" + encoder.encodeForJavaScript(reqLine.getIcReqLine().toString().replaceAll("\\r|\\n", "")) + "\"");
			description.add("\"" + encoder.encodeForJavaScript(reqLine.getDescription().toString().replaceAll("\\r|\\n", "")) + "\"");
			item_location.add("\"" + encoder.encodeForJavaScript(reqLine.getItemLocation().toString().replaceAll("\\r|\\n", "")) + "\"");
			item_source.add("\"" + encoder.encodeForJavaScript(reqLine.getItemSource().toString().replaceAll("\\r|\\n", "")) + "\"");
			blanket_order.add("\"" + encoder.encodeForJavaScript(reqLine.getBlanketOrder().toString().replaceAll("\\r|\\n", "")) + "\"");
			rad_nuclear.add("\"" + encoder.encodeForJavaScript(reqLine.getUdf3Code().toString().replaceAll("\\r|\\n", "")) + "\"");

			mark_tag.add("\"" + encoder.encodeForJavaScript(reqLine.getAsset().toString().replaceAll("\\r|\\n", "")) + "\"");
			traceability.add("\"" + encoder.encodeForJavaScript(reqLine.getUdf5Code().toString().replaceAll("\\r|\\n", "")) + "\"");
			header.add("\"" + encoder.encodeForJavaScript(reqLine.getUdf4Code().toString().replaceAll("\\r|\\n", "")) + "\"");
			ic_po_line.add("\"" + encoder.encodeForJavaScript(reqLine.getIcPoLine().toString().replaceAll("\\r|\\n", "")) + "\"");
		}
	%>
	function getSourceType() {
		return "R";
	}

	function getColumns() {
		<% if (s_req_status.equals("0535") || s_req_status.equals("0540")){ %>
		//var myColumns=["Item #/Desc.", "Supplier Id", "Catalog/Proc Level", "Commodity/Ind Class", "Quantity", "U/M", "Unit Cost", "Ext. Cost"];
		var myColumns=["Item #/Desc.", "Catalog/Proc Level", "Quantity", "U/M", "Unit Cost", "Ext. Cost"];
		<%} else { %>
		var myColumns=["Item #/Desc.", "Catalog/Proc Level", "Quantity", "U/M", "Unit Cost", "Ext. Cost", "Requisition #", "Status", "Kit/Inventory Location", "PO #", "Status", "Receipt #", "Status", "Invoice #", "Status"];
		<% } %>
		return myColumns;
	}

	function getItemNumbers() {
		var myColumns=<%=item_number %>;
		return myColumns;
	}

	function getSupplierIds() {
		var myColumns=<%= supplier_id %>;
		return myColumns;
	}

	function getCatalogs() {
		var myColumns=<%= catalog %>;
		return myColumns;
	}

	function getProcLevels() {
		var myColumns=<%= proc_level %>;
		return myColumns;
	}

	function getCommodities() {
		var myColumns=<%= commodity %>;
		return myColumns;
	}

	function getIndClasses() {
		var myColumns=<%= ind_class %>;
		return myColumns;
	}

	function getUorms() {
		var myColumns=<%= um_factor %>;
		return myColumns;
	}

	function getQuantities() {
		var myColumns=<%= quantity %>;
		return myColumns;
	}

	function getUnitCosts() {
		var myColumns=<%= unit_price %>;
		return myColumns;
	}

	function getExtCosts() {
		var myColumns=<%= extended_cost %>;
		return myColumns;
	}

	function getIcHistories() {
		var myColumns=<%= ic_history %>;
		return myColumns;
	}

	function getIcReqLines() {
		var myColumns=<%= ic_req_line %>;
		return myColumns;
	}

	function getDescriptions() {
		var myColumns=<%= description %>;
		return myColumns;
	}

	function getItemLocations() {
		var myColumns=<%= item_location %>;
		return myColumns;
	}

	function getItemSources() {
		var myColumns=<%= item_source %>;
		return myColumns;
	}

	function getBlanketOrders() {
		var myColumns=<%= blanket_order %>;
		return myColumns;
	}

	function getReqNumber() {
		var myColumns=<%= req_number %>;
		return myColumns;
	}

	function getReqStatus() {
		var myColumns=<%= req_status %>;
		return myColumns;
	}

	function getReqLocation() {
		var myColumns=<%= req_location %>;
		return myColumns;
	}

	function getPoNumber() {
		var myColumns=<%= po_number %>;
		return myColumns;
	}

	function getPoStatus() {
		var myColumns=<%= po_status %>;
		return myColumns;
	}

	function getRecNumber() {
		var myColumns=<%= rec_number %>;
		return myColumns;
	}

	function getRecStatus() {
		var myColumns=<%= rec_status %>;
		return myColumns;
	}

	function getInvNumber() {
		var myColumns=<%= inv_number %>;
		return myColumns;
	}

	function getInvStatus() {
		var myColumns=<%= inv_status %>;
		return myColumns;
	}

	function getLineNumbers() {
		var myColumns=<%= line_number %>;
		return myColumns;
	}

	function getRadNuclears() {
		var myColumns=<%= rad_nuclear %>;
		return myColumns;
	}

	function getMarkTags() {
		var myColumns=<%= mark_tag %>;
		return myColumns;
	}

	function getTraceabilities() {
		var myColumns=<%= traceability %>;
		return myColumns;
	}

	function getHeaders() {
		var myColumns=<%= header %>;
		return myColumns;
	}

	function getIcPoLines() {
		var myColumns=<%= ic_po_line %>;
		return myColumns;
	}

	function getMsrNumber() {
		var myColumn="<%= headerEncoder.encodeForJavaScript(s_req_number) %>";
		return myColumn;
	}

	function isPopup() {
		var myColumn="N";
		return myColumn;
	}

	function submitForm() {
		doSubmit("/requests/req_intelligent_review.jsp","RequisitionSourcing;RequisitionRetrieve");
	}
</script>
<style type="text/css">
.main-pager {
	border: 1px solid #CCCCCC;
	height: 600px;
	text-align: left;
	width: 600px;
	margin: 10px;
}

.pager {
	border: 1px solid #CCCCCC;
	height: 50px;
	text-align: left;
	width: 200px;
	margin: 10px;
}

.gwt-Label{
	margin-left: 10px;
	margin-top: 2px;
	font-family: Verdana;
	font-size: 10px;
	color: black;
	font-weight: bold;
}

.pagerWrapper {
	float:left;
}

.yellow-border div.pager {
    border: 1px solid #FFE45C;
}

.orange-border div.pager {
	border: 1px solid #F6A828;
    background-color:#C1DEFD;
}
</style>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value=""/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="RequisitionHeader_requiredBy" value='<%=request.getAttribute("RequisitionHeader_requiredBy")%>'/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RQH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="lineCount" value="<%=i_linecount%>"/>
<tsa:hidden name="formtype" value="REQ"/>
<tsa:hidden name="frompage" value="sourcing"/>
<tsa:hidden name="punchOutReturnHandler" value=""/>
<tsa:hidden name="punchOutReturnSuccessPage" value=""/>
<tsa:hidden name="icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="showLinkGetItemInfo" value="N"/>
<tsa:hidden name="punchOutAddAccount" value="N"/>
<tsa:hidden name="nonStandardItem" value="Y"/>
<% if (oid.equalsIgnoreCase("wpc08p")) { %>
<input type="hidden" name="userNameUdf1" value="<%=user.getDepartment()%>">
<input type="hidden" name="userNameUdf2" value="<%=user.getNameUdf2()%>">
<input type="hidden" name="userNameUdf3" value="1770">
<% } %>
<br>
<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="req-type" defaultString="Req Type"/>&nbsp;<tsa:label labelName="sourcing" defaultString="Sourcing"></tsa:label></div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
		<tsa:tr>
			<%if (s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVED) >= 0 && s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) < 0) { %>
			<tsa:td>&nbsp;</tsa:td>
			<% } %>
			<tsa:td align="right"><b><tsa:label labelName="req-requisition" defaultString="Requisition #"></tsa:label>:</b></tsa:td>
			<tsa:td width="125px"><%=headerEncoder.encodeForHTML(s_req_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<%if (s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVED) >= 0 && s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) < 0) { %>
			<tsa:td align="right"><a href="javascript: viewSourced()" title="Sourced">View Sourced Items</a></tsa:td>
			<% } %>
			<tsa:td align="right"><b><tsa:label labelName="status" defaultString="Status"></tsa:label>:</b></tsa:td>
			<tsa:td width="125px"><%=DocumentStatus.toString(s_req_status, oid)%></tsa:td>
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

<%--
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
					<tsa:td field="req-itemNumber" width="18%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="req-hdg-sourcingItemNumber" defaultString="Item #/Supplier Id"></tsa:label></tsa:td>
					<tsa:td field="req-catalog" width="18%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="req-hdg-sourcingCatalog" defaultString="Catalog/Proc Level"></tsa:label></tsa:td>
					<tsa:td field="req-commodity" width="15%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="req-hdg-sourcingCommodity" defaultString="Commodity/Ind Class"></tsa:label></tsa:td>
					<tsa:td field="req-quantity" width="10%" height="18px" cssClass="browseHdr" noWrap="nowrap" align="right"><tsa:label labelName="req-hdg-quantity" defaultString="Quantity"></tsa:label></tsa:td>
					<tsa:td field="req-uom" width="10%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="req-hdg-uom" defaultString="UOM" docType="s_req_type"></tsa:label></tsa:td>
					<tsa:td field="req-unitCost" width="10%" height="18px" cssClass="browseHdr" noWrap="nowrap" align="right"><tsa:label labelName="req-hdg-unitCost" defaultString="Unit Cost"></tsa:label></tsa:td>
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
						<input type="hidden" id="icReqLine_<%=i%>" value="<%=reqLine.getIcReqLine()%>">
					</tsa:td>
					<tsa:td field="req-catalog" width="18%" height="18px" cssClass="browseRow" noWrap="nowrap"><%=reqLine.getCatalogId()%>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="req-commodity" width="15%" height="18px" cssClass="browseRow" noWrap="nowrap"><%=reqLine.getCommodityCode()%>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="req-quantity" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid)%>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="req-uom" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap"><%=reqLine.getUmCode()%></tsa:td>
					<tsa:td field="req-unitCost" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getCurrency(bd_unit_price, s_curr_code, oid)%>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="req-extendedCost" width="15%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getCurrency(bd_extended_cost, s_curr_code, oid)%>&nbsp;&nbsp;</tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td width="7%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;&nbsp;</tsa:td>
					<tsa:td field="req-suggestedSupplier" width="18%" height="18px" cssClass="browseRow" noWrap="nowrap">
						<%=reqLine.getVendorId()%>
					</tsa:td>
					<tsa:td field="req-LN02" width="18%" height="18px" cssClass="browseRow" noWrap="nowrap" align="center"><%=reqLine.getUdf2Code()%>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="req-LN04" width="15%" height="18px" cssClass="browseRow" noWrap="nowrap" align="center"><%=reqLine.getUdf4Code()%>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="req-LN10" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap" align="center">&nbsp;&nbsp;</tsa:td>
					<tsa:td field="req-uom" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap"></tsa:td>
					<tsa:td field="req-unitCost" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;&nbsp;</tsa:td>
					<tsa:td field="req-extendedCost" width="15%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right">&nbsp;&nbsp;</tsa:td>
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
<%		} %>
				</table>
			</tsa:td>
		</tsa:tr>
		</table>
		<b class="rbottom">
		  <b class="r4"></b> <b class="r3"></b> <b class="r2"></b> <b class="r1"></b>
		</b>
		</div>
	</div>

<%	if (s_view.equals("WIZARD")) { %>
	<div style="align: right;"><%@ include file="/requests/req_sourcing_bar.jsp" %></div>
<%	} %>
</div>
--%>

<% //display sourcing boxes if status is planning
if (s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVED) >= 0 && s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) < 0) {
%>
<table width="100%">
	<tr>
		<td valign="top" width="75%">
			<div id="mainLabel" class="divcls"></div>
			<div id="cell"></div>
		</td>
		<td valign="top" width="25%">
			<table>
				<tr>
					<td align="right"><div id="link"></div></td>
				</tr>
				<tr>
					<td>
						<div id="label1"></div>
						<div id="listBox1" style="padding-left: 10px"></div>
						<div id="order1"></div>
					</td>
				</tr>
				<tr>
					<td>
						<div id="label2"></div>
						<div id="listBox2" style="padding-left: 10px"></div>
						<div id="order2"></div>
					</td>
				</tr>
				<tr>
					<td>
						<div id="label3"></div>
						<div id="listBox3" style="padding-left: 10px"></div>
						<div id="order3"></div>
					</td>
				</tr>
				<tr>
					<td>
						<div id="label4"></div>
						<div id="listBox4" style="padding-left: 10px"></div>
						<div id="order4"></div>
					</td>
				</tr>
				<tr>
					<td>
						<div id="label5"></div>
						<div id="listBox5" style="padding-left: 10px"></div>
						<div id="order5"></div>
					</td>
				</tr>
				<tr>
					<td>
						<div id="label6"></div>
						<div id="listBox6" style="padding-left: 10px"></div>
						<div id="order6"></div>
					</td>
				</tr>
				<tr>
					<td align="center"><div id="button"></div></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<div id="hidden"></div>
<br />
<div style="clear: left"></div>
<% } else {%>
	<table width="100%">
	<tr>
		<td valign="top" width="75%">
			<div id="mainLabel" class="divcls"></div>
			<div id="cell"></div>
		</td>
	</tr>
</table>
<div id="hidden"></div>
<br />
<div style="clear: left"></div>
<% } %>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('requests/req_review.jsp', 'RequisitionRetrieve'); void(0);">Return</a></div></tsa:td>
	<%if (s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVED) >= 0 && s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) < 0) {%>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript:void(0);" id="submitButton">Submit</a></div></tsa:td>
	<% } %>
</tsa:tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value="JavaScript">
<!-- Hide script

	frm = document.purchaseform;
	var reqnumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
	var fiscalyear = "<%=s_fiscal_year%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var itemLocation = "<%=s_item_location%>";

//	setTableHeights();

	var navMenu = getNavCookie("navArray");
	if (navMenu.indexOf("<%=RequisitionType.toString(s_req_type, oid)%>") < 0)
	{
		setNavCookie("/requests/req_items.jsp", "RequisitionRetrieve", "<%=RequisitionType.toString(s_req_type, oid)%>");
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


	function setFirstFocus() {
		if (frm.as_itemNumber != undefined) {
			frm.as_itemNumber.focus();
		}
    }

    function validateForm()
	{
		return true;
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

	function viewSourced()
	{
		popupParameters = "formtype=REQ;RequisitionLine_icReqHeader=<%=s_reqline_header%>;RequisitionHeader_icReqHeader=<%=s_req_ic_header%>;";
		doSubmitToPopup('/requests/req_sourced_popup.jsp', 'RequisitionLineSourcedRetrieveByHeader', 'width=850px', 'height=450px');
	}

	function showInvBinLocation(itemNum, itemLoc){
		popupParameters = "InvItem_itemNumber=" + itemNum + 
						";InvBinLocation_itemNumber=" + itemNum +
						";InvBinLocation_itemLocation=" + itemLoc;
						
		doSubmitToPopup('requests/req_inv_bin_locations.jsp', 'InvItemRetrieveById;InvBinLocationRetrieveByItem', 'WIDTH=800px', 'HEIGHT=500px');
	}


// End Hide script -->
</SCRIPT>