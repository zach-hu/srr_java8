<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/accounts.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	boolean allowEdit = false;
	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_ic_po_header = (String) request.getAttribute("PoHeader_icPoHeader");
	String	s_po_number = (String) request.getAttribute("PoHeader_poNumber");
	BigDecimal	bd_zero = new BigDecimal(0);
	String	s_release_number = (String) request.getAttribute("PoHeader_releaseNumber");
	String	s_revision_number = (String) request.getAttribute("PoHeader_revisionNumber");
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	String	s_po_type = (String) request.getAttribute("PoHeader_poType");
	String	s_fiscal_year = (String) request.getAttribute("PoHeader_fiscalYear");
	String	s_po_status = (String) request.getAttribute("PoLine_status");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_itemLocation"));
	String	s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_buyerCode"));
	String	s_ic_po_line = (String) request.getAttribute("PoLine_icPoLine");
	String	s_line_number = (String) request.getAttribute("PoLine_lineNumber");
	String	s_item_number = (String) request.getAttribute("PoLine_itemNumber");
	String	s_item_qty = (String) request.getAttribute("PoLine_quantity");
	String	s_commodity = (String) request.getAttribute("PoLine_commodity");
	String	labelPrefix = "po";
	String	accountType = "POL";
	String tableType = "AC";
	String	s_dept_code = "";
	String labViewGroup = "";

	if (s_commodity == null) s_commodity = "" ;

	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	if (poHeader != null)
	{
		s_req_udf10 = poHeader.getUdf10Code();
		if (oid.equalsIgnoreCase("qri06p"))
		{
			String location = "ACCOUNT_" + poHeader.getUdf1Code();
			tableType = propertiesManager.getProperty("BROWSE", location, "AC");
		}

		if (oid.equalsIgnoreCase("vse06p"))
		{
			s_dept_code = poHeader.getDepartmentCode();
		}
	}

	if (s_po_number == null)	{	s_po_number = "N/A";		}
	if (s_item_qty == null)		{	s_item_qty = "0";		}
	BigDecimal	bd_item_qty = new BigDecimal(s_item_qty);

	if ( (role.getAccessRights("PO") > 1) && (s_po_status.compareTo(DocumentStatus.PO_REJECTED) <= 0  || (bAdminChanges && s_po_status.compareTo(DocumentStatus.CANCELLED) < 0 && s_po_status.compareTo(DocumentStatus.PO_AMENDED) != 0)) )  {
		allowEdit = true;
	}
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer())
	{
		allowEdit = false;
	}
%>

<tsa:hidden name="PoLine_icPoLine" value="<%=s_ic_po_line%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="PoHeader_status" value="<%=(String) request.getAttribute(\"PoHeader_status\")%>"/>
<tsa:hidden name="PoHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="PoHeader_buyerCode" value="<%=s_buyer_code%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Account_icLine" value="<%=s_ic_po_line%>"/>
<tsa:hidden name="Default_referenceType" value="<%=accountType%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="formNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_po_status%>"/>
<tsa:hidden name="formType" value="<%=accountType%>"/>

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
		<%@ include file="/orders/po_display_number.jsp" %>
	</td>
</tr>
</table>

<br>
<%@ include file="/orders/po_info.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top>
		<%@ include file="/base/account_form_rows.jsp" %>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if ( allowEdit )  { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: saveAccounts(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/orders/po_item.jsp', 'PoLineRetrieve'); void(0);">Return</a></div></td>
<%	} else {%>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: doSubmit('/orders/po_item.jsp', 'PoLineRetrieve'); void(0);">Return</a></div></td>
<%	}%>
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

<%	if ( allowEdit )  { %>
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

	function saveAccounts()
	{
		if( orgId == "BLY07P" )
		{
			setStatus();
		}
		doSubmit('/orders/po_item.jsp', 'AccountUpdate;PoLineRetrieve');
	}
// End Hide script -->
</SCRIPT>
