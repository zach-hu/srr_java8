<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String vendorName = (String) request.getAttribute("Vendor_vendorName");
	String vendor_buyer = HiltonUtility.ckNull((String) request.getAttribute("Vendor_buyer"));
	List	vendorCommRelList = (List) request.getAttribute("vendorCommRelList");
	if (vendorCommRelList == null) {
		vendorCommRelList = new ArrayList();
	}
	List	commodityList = new ArrayList();
	for (int i=0; i < vendorCommRelList.size(); i++) {
		VendorCommRel vendorCommRel = (VendorCommRel) vendorCommRelList.get(i);
		commodityList.add(vendorCommRel.getComp_id().getCommodityCode());
	}
	if (commodityList.size() < 10) {
		for (int i=commodityList.size(); i < 10; i++) {
			commodityList.add("");
		}
	}

	String	s_current_process = "COMMODITIES";
	String	s_current_page = "/admin/supplier/supplier_commodities.jsp";
	String	s_current_method = "VendorCommRelUpdate";
	String	s_current_process_method = "";
%>

<tsa:hidden name="VendorCommRel_vendorId" value="${Vendor_vendorId}"/>
<%@ include file="/admin/supplier/supplier_hidden_fields.jsp" %>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=vendorName%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
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
<%@ include file="/admin/admin_info.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top>
		<table border=0  cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td align=right nowrap valign=top><a href="javascript: void(0);" onClick="pruebaCommodity();"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-commodity", "Commodity", true)%></a>:&nbsp;</td>
			<td colspan=2 valign="top">
				<table border=0 cellpadding=2 cellspacing=0 id="commodityTable">
				</table>
				</td>
			<td valign=top align="right"><br><%@ include file="/admin/supplier/supplier_sidebar.jsp" %></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var currentRow = 0;
	var maxRows = 0;

	setCommodities();

	function pruebaCommodity(){
		browseCommodities('VendorCommRel_commodityCode', 'commodity');
	}

	function thisLoad()
	{
		f_StartIt();
<%	if (role.getAccessRights("VEND") < 2) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function setCommodities() {
<% for (int ic=0; ic < commodityList.size(); ic++) {
		String commodityCode = (String) commodityList.get(ic);
		String description = "";
		String iclLevel = "";
		Commodity commodity = CommodityManager.getInstance().getCommodity(oid, commodityCode);
		if (commodity != null) {
			description = commodity.getDescription();
			iclLevel = commodity.getIclLevel().toString();
		} %>
		addCommodity("<%=commodityCode%>","<%=description%>","<%=iclLevel%>");
<% }%>
	}

	function browseCommodities(fld, xml) {
		var commodities = frm.elements.item("VendorCommRel_commodityCode");
		var rowSet = false;
		var commodityCnt = 0;

		if (commodities != undefined) {
			commodityCnt = commodities.length;
			if (commodities.length > 1 ) {
				for (var i=0; i < commodities.length; i++) {
					var commodity = frm.VendorCommRel_commodityCode[i].value;
					if (isEmpty(commodity)) {
						currentRow = i;
						rowSet  = true;
						break;
					}
				}
			}
		}
		if (!rowSet) {
			addCommodity("","","");
			currentRow = commodityCnt;
			commodityCnt++;
		}
		maxRows = commodityCnt;
//		browseSchedule(fld, xml);

		<% if (oid.equalsIgnoreCase("msg07p")) { %>
		popupParameters = "browseName=commodity";
		popupParameters = popupParameters + ";formField=" + fld + ";allowBrowse=" + frm.allowBrowse.value + ";index=" + currentRow;
		popupParameters = popupParameters + ";colname=Commodity_commodityType;operator==;filter_txt=BO;logicalOperator=OR;originalFilter=Y;sort=N";
		popupParameters = popupParameters + ";colname=Commodity_commodityType;operator==;filter_txt=SU;logicalOperator=AND;originalFilter=Y;sort=N";
		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
		<% } else { %>
		browseCommodity(fld, 'commodity', '<%=propertiesManager.getProperty("MISC", "COMMODITYTYPE", "")%>');
		<% } %>
	}

	function browseCommodity(formField, xml, commodityType) {
		if (xml == "subcommodity") {
			popupParameters = "formField=" + formField +";browseName=" + xml + ";index=" + currentRow + ";allowBrowse=" + frm.allowBrowse.value;
			doSubmitToPopup('browse/browse_subcommodity_tree.jsp', 'SubCommodityRetrieveTree', 'WIDTH=700px', 'HEIGHT=500px');
		} else if (!isEmpty(commodityType)) {
			popupParameters = "formField=" + formField +";browseName=" + xml + ";index=" + currentRow + ";allowBrowse=" + frm.allowBrowse.value;
			doSubmitToPopup('browse/browse_commodity_tree.jsp', 'CommodityRetrieveTree', 'WIDTH=700px', 'HEIGHT=500px');
		} else {
			browseSchedule(formField, xml);
		}
	}

	function addCommodity(commodityCode, desc, iclLevel)
	{
		var myTable = document.getElementById("commodityTable");
		var myRow = createRow(myTable);

		currentRow = myTable.rows.length - 1;

		var myCell = createCell(myRow);
		myCell.innerHTML = "<input type=text id=\"VendorCommRel_commodityCode\" name=\"VendorCommRel_commodityCode\" size=15 value=\"" + commodityCode + "\" onfocus=\"this.blur();\" class=disabledTxtBox readOnly>";

		myCell = createCell(myRow);
		myCell.innerHTML = "<input type=text name=\"as_commodityName\" value=\"" + desc + "\" size=50 onfocus=\"this.blur();\" class=disabledTxtBox readOnly>";

		myCell = createCell(myRow);
		myCell.innerHTML = "<input type=text id=\"as_commodityIclLevel\" name=\"as_commodityIclLevel\" value=\"" + iclLevel + "\" size=5 onfocus=\"this.blur();\" class=disabledTxtBox readOnly>";

<%	if (role.getAccessRights("VEND") == 99) { %>
		myCell = createCell(myRow);
		myCell.innerHTML = "<a href=\"javascript: deleteCommodity(" + currentRow + "); void(0);\"><img src=\"<%=contextPath%>\\images\\delete.gif\" border=0></a>";
<%	} %>
	}

	function deleteCommodity(row) {
		var myTable = document.getElementById("commodityTable");
		var rows = myTable.rows.length;

		if (rows == 0) {
			return;
		}
		else if (rows > 1) {
			for (var i = row; i < rows; i++) {
				var commodityCode = "";
				var commodityName = "";
				var commodityIclLevel = "";

				if ((i+1) < rows) {
					commodityCode = frm.VendorCommRel_commodityCode[i + 1].value;
					commodityName = frm.as_commodityName[i + 1].value;
					commodityIclLevel = frm.as_commodityIclLevel[i + 1].value;
				}

				frm.VendorCommRel_commodityCode[i].value = commodityCode;
				frm.as_commodityName[i].value = commodityName;
				frm.as_commodityIclLevel[i].value = commodityIclLevel;
			}
		}
		else {
			frm.VendorCommRel_commodityCode.value = "";
			frm.as_commodityName.value = "";
			frm.as_commodityIclLevel.value = "";
		}
	}

	function setCommodityCodes() {
		var myTable = document.getElementById("commodityTable");
		var rows = myTable.rows.length;

		// make sure there are no duplicates
		if (rows > 1) {
			var codesEntered = "";
			for (var i=rows - 1; i >= 0; i--) {
				var tempCode = frm.VendorCommRel_commodityCode[i].value;
				if (isEmpty(tempCode) || codesEntered.indexOf(tempCode + ";") >= 0) {
					deleteCommodity(i);
				} else {
					codesEntered = codesEntered + tempCode + ";";
				}
			}
		}
	}

	function updateSupplierCommodities() {
		//setCommodityCodes();
		doSubmit('/admin/supplier/supplier_info.jsp', 'VendorCommRelUpdate;VendorRetrieveById');
	}

	function validateForm()
	{
		setCommodityCodes();

		//  if user clicks on Purchase History step
		if (frm.handler.value.indexOf("BrowseRetrieve") > 0)
		{
			setOriginalFilter("PoHeader_vendorId", "=", '${esapi:encodeForJavaScript(Vendor_vendorId)}');
			var newInputField = "<input type=hidden name='PurchaseHistory_vendorId' value=${esapi:encodeForJavaScript(Vendor_vendorId)}>";
			setHiddenFields(newInputField);
			frm.browseName.value = "supplierorders";
		}

		return true;
	}

// End Hide script -->
</SCRIPT>