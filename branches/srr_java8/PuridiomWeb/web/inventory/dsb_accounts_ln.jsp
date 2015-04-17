<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/accounts.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_from_page = HiltonUtility.ckNull((String) request.getAttribute("frompage"));
	String	s_ic_dsb_header = (String) request.getAttribute("DisbHeader_icDsbHeader");
	String	s_dsb_number = (String) request.getAttribute("DisbHeader_disbNumber");
	String	s_dsb_status = (String) request.getAttribute("DisbHeader_status");
	String	s_dsb_type = (String) request.getAttribute("DisbHeader_disbType");
	String	s_ic_dsb_line = (String) request.getAttribute("DisbLine_icDsbLine");
	String	s_line_number = (String) request.getAttribute("DisbLine_lineNumber");
	String	s_item_qty = (String) request.getAttribute("DisbLine_quantity");
	String	s_commodity = (String) request.getAttribute("DisbLine_commodityCode");
	String	s_fiscal_year = (String) request.getAttribute("DisbHeader_fiscalYear");
	String	labelPrefix = "dsb";
	String	accountType = "DBL";
	String	tableType = "AC";
	String	s_dept_code = "";
	String  s_buyer_code="";
	String labViewGroup = "";

	boolean allowEdit = false;

	if (s_commodity == null) s_commodity = "" ;

	if (HiltonUtility.isEmpty(s_ic_dsb_header)) {
		s_ic_dsb_header = (String) request.getAttribute("Account_icHeader");
	}
	if (HiltonUtility.isEmpty(s_ic_dsb_line)) {
		s_ic_dsb_line = (String) request.getAttribute("Account_icLine");
	}
	if (s_dsb_number == null) {
		s_dsb_number = (String) request.getAttribute("formNumber");
	}
	if (s_dsb_status == null) {
		s_dsb_status = (String) request.getAttribute("formStatus");
	}
	if (s_item_qty == null) {
		s_item_qty = "0";
	}

	BigDecimal	bd_item_qty = new BigDecimal(s_item_qty);

	if (s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) == 0) {
		allowEdit = true;
	}
%>

<tsa:hidden name="DisbLine_icDsbLine" value="<%=s_ic_dsb_line%>"/>
<tsa:hidden name="DisbHeader_icDsbHeader" value="<%=s_ic_dsb_header%>"/>
<tsa:hidden name="DisbHeader_disbNumber" value="<%=s_dsb_number%>"/>
<tsa:hidden name="DisbHeader_status" value="<%=s_dsb_status%>"/>
<tsa:hidden name="DisbHeader_disbType" value="<%=s_dsb_type%>"/>
<tsa:hidden name="Default_referenceType" value="<%=accountType%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ic_dsb_header%>"/>
<tsa:hidden name="Account_icLine" value="<%=s_ic_dsb_line%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="formNumber" value="<%=s_dsb_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_dsb_status%>"/>
<tsa:hidden name="formType" value="<%=accountType%>"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>

<%
	if (HiltonUtility.isEmpty(s_dsb_number))
	{
		s_dsb_number = "N/A";
	}
%>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Line Item <%=s_line_number%> - Account Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Disbursement #:</b></td>
			<td width=100px><%=headerEncoder.encodeForHTML(s_dsb_number)%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=100px><%=DocumentStatus.toString(s_dsb_status, oid)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top>
		<%@ include file="/base/account_form_rows.jsp" %>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=500px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/dsb_item.jsp', 'AccountUpdate;DisbLineRetrieve'); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/dsb_item.jsp', 'DisbLineRetrieve'); void(0);">Return</a></div></td>
</tr>
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
	var accountType = "<%=accountType%>";
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
<%	if (!allowEdit) { %>
			checkInputSettings();
<%	} %>
	}

// End Hide script -->
</SCRIPT>