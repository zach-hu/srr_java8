<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<%@ page import="com.tsa.puridiom.menu.MenuManager" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<% 	
	pageContext.setAttribute("oid", oid);
	pageContext.setAttribute("language", language);

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

	List	reqLineList = (List) request.getAttribute("requisitionLineList");
	List	reqHeaderResultList = new ArrayList();
	List	poHeaderResultList = new ArrayList();
	List	recHeaderResultList = new ArrayList();
	List	invHeaderResultList = new ArrayList();
	List	newReqList = new ArrayList();

	for (int p = 0; p < reqLineList.size(); p++)
	{
		RequisitionLine line = (RequisitionLine) reqLineList.get(p);
		if (line.getStatus().compareTo(DocumentStatus.REQ_PLANNING_SOURCED) >= 0)
		{
			newReqList.add(line);
		}
	}
	try {
		reqHeaderResultList = (List) request.getAttribute("requisitionLineHistoryReqList");
		poHeaderResultList = (List) request.getAttribute("requisitionLineHistoryPoList");
		recHeaderResultList = (List) request.getAttribute("requisitionLineHistoryRecList");
		invHeaderResultList = (List) request.getAttribute("requisitionLineHistoryInvList");
	}catch (Exception e) {
	}
	int	i_linecount = 0;

	if (newReqList != null)
	{
		i_linecount = newReqList.size();

		if (i_linecount > 0)
		{
			RequisitionLine reqLine = (RequisitionLine) newReqList.get(0);
			s_req_number = reqLine.getRequisitionNumber();
		}
	}
	else
	{
		reqLineList = new ArrayList();
	}

	if (HiltonUtility.isEmpty(s_req_number))
	{
		s_req_number = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_requisitionNumber"));
	}

	if (HiltonUtility.isEmpty(s_req_number))	{	s_req_number = "N/A";	}
	if (HiltonUtility.isEmpty(s_req_status))	{	s_req_status = "0540";			}
	if (HiltonUtility.isEmpty(s_req_type))		{	s_req_type = "M";				}

    RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
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

		for (int i = 0; i < reqHeaderResultList.size(); i++)
		{
			RequisitionLine reqLine = (RequisitionLine) newReqList.get(i);
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
		var myColumns=["Item #/Desc.", "Supplier Id", "Catalog/Proc Level", "Commodity/Ind Class", "Quantity", "U/M", "Unit Cost", "Ext. Cost", "Requisition #", "Status", "Kit/Inventory Location", "PO #", "Status", "Receipt #", "Status", "Invoice #", "Status"];
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
		var myColumns="<%= headerEncoder.encodeForJavaScript(s_req_number) %>";
		return myColumns;
	}

	function isPopup() {
		var myColumn="Y";
		return myColumn;
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

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Items Sourced</div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tsa:tr>
			<tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

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

<table border="0" cellspacing="0" cellpadding="0" width="640px">
	<tsa:tr><tsa:td align="center"><div id="pxbutton"><a href="javascript: window.top.hidePopWin(); void(0);"><tsa:label labelName="req-close" defaultString="Close"/></a></div></tsa:td></tsa:tr>
</table>

<br>
<br>

</FORM>

<SCRIPT value="JavaScript">
<!-- Hide script

// End Hide script -->
</SCRIPT>

</BODY>
</HTML>