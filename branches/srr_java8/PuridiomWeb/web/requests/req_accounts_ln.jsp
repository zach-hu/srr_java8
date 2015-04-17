<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/accounts.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_from_page = HiltonUtility.ckNull((String) request.getAttribute("frompage"));
	String	s_req_ic_header = (String) request.getAttribute("RequisitionHeader_icReqHeader");
	String	s_req_number = (String) request.getAttribute("RequisitionHeader_requisitionNumber");
	String	s_req_status = (String) request.getAttribute("RequisitionHeader_status");
	String	s_fiscal_year = (String) request.getAttribute("RequisitionHeader_fiscalYear");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_itemLocation"));
	String	s_req_ic_line = (String) request.getAttribute("RequisitionLine_icReqLine");
	String	s_line_number = (String) request.getAttribute("RequisitionLine_lineNumber");
	String	s_item_number = (String) request.getAttribute("RequisitionLine_itemNumber");
	String	s_item_qty = (String) request.getAttribute("RequisitionLine_quantity");
	String	s_commodity = (String) request.getAttribute("RequisitionLine_commodityCode") ;
	String  s_vendor_line_id = HiltonUtility.ckNull((String) request.getAttribute("RequisitionLine_vendorId"));
	String defaultByVendor = propertiesManager.getProperty("VENDOR","DEFAULTACCOUNTLINE","N");
	if ( defaultByVendor.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(s_vendor_line_id))
	{
		vendorValueDefault = HiltonUtility.ckNull(((Vendor)VendorManager.getInstance().getVendor(oid, s_vendor_line_id)).getVendUdf2());
	}
	String	s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_requisitionerCode"));
	String	labelPrefix = "req";
	String	accountType = "RQL";
	String tableType = "AC";
	String	s_req_subtype = "";
	String	s_vendor_id = "";
	String	s_dept_code = "";
	String labViewGroup = "";

	if (s_commodity == null) s_commodity = "" ;
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	if (requisitionHeader != null)
	{
		s_vendor_id = requisitionHeader.getVendorId();
		s_req_subtype = requisitionHeader.getRqSubType();
		s_req_udf10 = requisitionHeader.getUdf10Code();
		s_req_departmentCode = requisitionHeader.getDepartmentCode();

		if (oid.equalsIgnoreCase("qri06p"))
		{
			String location = "ACCOUNT_" + requisitionHeader.getUdf1Code();
			tableType = propertiesManager.getProperty("BROWSE", location, "AC");
		}
		if (oid.equalsIgnoreCase("vse06p"))
		{
			s_dept_code = requisitionHeader.getDepartmentCode();
		}
	}
	boolean allowEdit = false;

	if (HiltonUtility.isEmpty(s_req_ic_header)) {
		s_req_ic_header = (String) request.getAttribute("Account_icHeader");
	}
	if (HiltonUtility.isEmpty(s_req_ic_line)) {
		s_req_ic_line = (String) request.getAttribute("Account_icLine");
	}
	if (s_req_number == null) {
		s_req_number = (String) request.getAttribute("formNumber");
	}
	if (HiltonUtility.isEmpty(s_req_number))	{
		s_req_number = "N/A";
	}
	if (s_req_status == null) {
		s_req_status = (String) request.getAttribute("formStatus");
	}
	if (s_item_qty == null)	{
		s_item_qty = "0";
	}
	if (s_fiscal_year == null) {
		s_fiscal_year = (String) request.getAttribute("fiscalYear");
	}
	BigDecimal	bd_item_qty = new BigDecimal(s_item_qty);
	boolean	fpeUser = user.isAFpe() ;
	boolean engineerUser = user.isAEngineer() ;

	if (moduleType.equals("M") && (fpeUser || engineerUser) &&  (s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) < 0) ) {
		allowEdit = true ;
	} else 	if (!(s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVING) >= 0 && moduleType.equals("M"))  || s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0) {
		allowEdit = true;
	}

	request.setAttribute("GLAccount_ROnly","0");
%>

<tsa:hidden name="RequisitionLine_icReqLine" value="<%=s_req_ic_line%>"/>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionHeader_vendorId" value="<%=s_vendor_id%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="<%=s_req_ic_line%>"/>
<tsa:hidden name="Default_referenceType" value="RQL"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="formNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_req_status%>"/>
<tsa:hidden name="formType" value="RQL"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="fiscalYear" value="<%=s_fiscal_year%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
	<% if (moduleType.equals("M")) { %>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class=hdr12>
			<tsa:label labelName="req-type" defaultString="Req Type"/>&nbsp;
			<tsa:label labelName="lineItem" defaultString="Line Item" /><%=s_line_number%>
			<tsa:label labelName="accountInformation" defaultString="- Account Information" /></div>
			</tsa:td>
		</tsa:tr>
	<% } else { %>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class=hdr12>

			<tsa:label labelName="lineItem" defaultString="Line Item" /><%=s_line_number%>
			<tsa:label labelName="accountInformation" defaultString="- Account Information" /></div>
			</tsa:td>
		</tsa:tr>
	<% } %>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<%@ include file="/requests/req_display_number.jsp" %>
	</tsa:td>
</tsa:tr>
</table>

<br>
<%@ include file="/requests/req_info.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tsa:tr>
	<tsa:td valign="top">
		<%@ include file="/base/account_form_rows.jsp" %>

<%	if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 && ((String)request.getAttribute("GLAccount_ROnly")).equals("0") ) {
		String s_recent_accounts = propertiesManager.getProperty("REQ OPTIONS", "HIDE FAVORITE ACCOUNTS", "N");
		if (s_recent_accounts.equalsIgnoreCase("N")) {%>

		<%@ include file="/base/recentaccounts.jsp" %>

<%		}
	} 	%>
	</tsa:td>
</tsa:tr>
<%	if (s_req_subtype.equals("X") && s_vendor_id.equals("06440") && oid.equals("BSC04P")) {%>
<tsa:tr>
	<tsa:td align="center">
		<br><br><br>
		<table border=0 cellpadding=0 cellspacing=0 width=500px>
		<tsa:tr><tsa:td cssClass="red"><b><u>
		<tsa:label labelName="notice" defaultString="Notice" />:</u></b>
		<tsa:label labelName="desc-notice" defaultString="OfficeMax's systems can only charge one cost center per line item.  If you allocate to multiple Cost Centers you will have to have Blue Shield Accounts Payable handle the cost center allocation on the back end." /></tsa:td></tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
<%	}%>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tsa:tr>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: saveAccounts(); void(0);"><tsa:label labelName="req-save" defaultString="Save"/></a></div></tsa:td>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('requests/req_item.jsp', 'RequisitionLineRetrieve'); void(0);"><tsa:label labelName="req-return" defaultString="Return"/></a></div></tsa:td>
</tsa:tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var myTable = document.getElementById("accounts");
	var TotalRows = myTable.rows.length - 1;

	var accRows = frm.as_rows.value;
	var maxRows = frm.as_maxrows.value;
	var allocMethod = frm.as_alloc_method.value;
	var qtyDec  = <%=s_quantity_decimals%>;
	var type = "LIN";  /*used for setting allocMethod in accounts.js*/
	var accountType = "RQL";
	var amtToAllocate = <%=bd_amount_to_allocate%>;


<%	if (allowEdit) { %>
			if (TotalRows == 0)
			{
				addNew();
			}
			if (frm.alloc_method[1].checked == true)
			{
				setMethod();
			}
<%	}%>

<%	if (s_account_action.equalsIgnoreCase("returnAllocation")) { %>
			total();
<%	} %>

	function thisLoad()
	{
		f_StartIt();
<%	if (!allowEdit) {  %>
			checkInputSettings();
<%	} %>

	}

	function saveAccounts()
	{
		if( orgId == "BLY07P" )
		{
			setStatus();
		}
		doSubmit('requests/req_item.jsp', 'AccountUpdate;RequisitionLineRetrieve');
	}

// End Hide script -->
</SCRIPT>