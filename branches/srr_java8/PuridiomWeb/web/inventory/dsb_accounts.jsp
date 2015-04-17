<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/inventory/inv_property_checklist_fields.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/accounts.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_icDsbHeader = (String) request.getAttribute("DisbHeader_icDsbHeader");
	String	s_dsb_number = (String) request.getAttribute("DisbHeader_disbNumber");
	String	s_dsb_type = (String) request.getAttribute("DisbHeader_disbType");
	String	s_dsb_status = (String) request.getAttribute("DisbHeader_status");

	String	s_fiscal_year = (String) request.getAttribute("DisbHeader_fiscalYear");

	String	s_commodity = "";
	String	labelPrefix = "dsb";
	String	accountType = "DBH";
	String	tableType = "AC";
	String	s_dept_code = "";
	String  s_buyer_code="";
	String  labViewGroup = "";
	boolean allowEdit = false;

	if (s_dsb_number == null) {
		s_dsb_number = (String) request.getAttribute("formNumber");
	}
	if (HiltonUtility.isEmpty(s_icDsbHeader)) {
		s_icDsbHeader = (String) request.getAttribute("Account_icHeader");
	}
	if (s_dsb_status == null) {
		s_dsb_status = (String) request.getAttribute("formStatus");
	}

	String	s_current_process = "HEADER_ACCOUNTS";
	String	s_current_page = "/inventory/dsb_accounts.jsp";
	String	s_current_method = "AccountUpdate";
	String	s_current_process_method = "";

	if (s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) == 0) {
		allowEdit = true;
	}
%>

<tsa:hidden name="DisbHeader_icDsbHeader" value="<%=s_icDsbHeader%>"/>
<tsa:hidden name="DisbHeader_disbNumber" value="<%=s_dsb_number%>"/>
<tsa:hidden name="DisbHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="DisbHeader_disbType" value="<%=s_dsb_type%>"/>
<tsa:hidden name="DisbHeader_status" value="<%=s_dsb_status%>"/>
<tsa:hidden name="DisbLine_icDsbHeader" value="<%=s_icDsbHeader%>"/>
<tsa:hidden name="Default_referenceType" value="<%=accountType%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_icDsbHeader%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_icDsbHeader%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="formNumber" value="<%=s_dsb_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_dsb_status%>"/>
<tsa:hidden name="formType" value="<%=accountType%>"/>

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
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "account_information", "Account Information")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Disbursement #:</b></td>
			<td width=125px><%=headerEncoder.encodeForHTML(s_dsb_number)%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=125px><%=DocumentStatus.toString(s_dsb_status, oid)%></td>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top>
		<%@ include file="/base/account_form_rows.jsp" %>
	</td>
<%	if (s_view.equalsIgnoreCase("WIZARD")) { %>
	<td rowspan=2 align=right valign=top><%@ include file="/inventory/dsb_sidebar.jsp" %></td>
<%	}%>
</tr>
</table>

<br>
<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=500px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/dsb_summary.jsp', 'DisbSetProperty;AccountUpdate;DisbursementRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/dsb_summary.jsp', 'DisbSetProperty;DisbursementRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var disbnumber = "<%=headerEncoder.encodeForJavaScript(s_dsb_number)%>";
	var fiscalyear = "<%=headerEncoder.encodeForJavaScript(s_fiscal_year)%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	var myTable = document.getElementById("accounts");
	var TotalRows = myTable.rows.length - 1;

	var accRows = frm.as_rows.value;
	var maxRows = frm.as_maxrows.value;
	var allocMethod = frm.as_alloc_method.value;
	var qtyDec  = <%=s_quantity_decimals%>;
	var type = "HDR";  /*used for setting allocMethod in accounts.js*/
	var accountType = "<%=accountType%>";
	var amtToAllocate = <%=bd_amount_to_allocate%>;

<%	if (s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) == 0) { %>
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
<%	if (s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) > 0) { %>
			checkInputSettings();
<%	} %>
	}

// End Hide script -->
</SCRIPT>