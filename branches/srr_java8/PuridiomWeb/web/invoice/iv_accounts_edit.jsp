<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/accounts.js"></SCRIPT>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_ivc_ic_header = HiltonUtility.ckNull((String) request.getAttribute("InvoiceHeader_icIvcHeader"));
	String	s_ivc_number = HiltonUtility.ckNull((String) request.getAttribute("InvoiceHeader_invoiceNumber"));
	String	s_ivc_status = HiltonUtility.ckNull((String) request.getAttribute("InvoiceHeader_status"));
	String	s_fiscal_year = (String) request.getAttribute("InvoiceHeader_fiscalYear");
	String	s_commodity = "" ;
	String	accountType = HiltonUtility.ckNull((String) request.getAttribute("formType"));
	String	tableType = "AC";
	String	s_dept_code = "";
	String  s_buyer_code="";
	String	icLine = "0";
	String labViewGroup = "";

	if (accountType.equals("IVT"))
	{
		icLine = "1";
	}
	else if (accountType.equals("IVS"))
	{
		icLine = "2";
	}
	else if (accountType.equals("IVO"))
	{
		icLine = "3";
	}
	else if (accountType.equals("IVU"))
	{
		icLine = "4";
	}
	String	labelPrefix = "ivc";
	boolean allowEdit = false;

	String	s_current_process = "ACCOUNTS";
	String	s_current_page = "/invoice/iv_accounts.jsp";
	String	s_current_method = "AccountUpdate";
	String	s_current_process_method = "";

	if (s_ivc_status.compareTo(DocumentStatus.IVC_RECALLED) > 0)
	{
		if ( (s_ivc_status.equals(DocumentStatus.IVC_REJECTED) && user.getVchApp().equalsIgnoreCase("Y")) || s_ivc_status.equals(DocumentStatus.IVC_APPROVING)){
			allowEdit = true;
		}
		s_current_method = "";
	}
	else
	{
		allowEdit = true;
	}

	List invoiceLineList = (List)request.getAttribute("invoiceLineList");
	String lineItemDistribution = propertiesManager.getProperty("VOUCHER OPTIONS", "LINE ITEM DISTRIBUTION", "N");
	String allowEditFld = propertiesManager.getProperty("VOUCHER OPTIONS","ALLOWEDITACCOUNT", "N");
	boolean allowAccountTaxes = true;
	if (oid.equals("SRR10P")){
		if (accountType.equals("IVO") && allowEditFld.equalsIgnoreCase("N"))
		{
			if (lineItemDistribution.equalsIgnoreCase("Y") && invoiceLineList != null && invoiceLineList.size() > 0)
			{
				//allowAccountTaxes = false;
				//allowEdit = false;
			}
		}
	}
	else {
		if ((accountType.equals("IVT") || accountType.equals("IVS") || accountType.equals("IVO")) && allowEditFld.equalsIgnoreCase("N"))
		{
			if (lineItemDistribution.equalsIgnoreCase("Y") && invoiceLineList != null && invoiceLineList.size() > 0)
			{
				allowAccountTaxes = false;
				allowEdit = false;
			}
		}
	}
%>

<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=s_ivc_number%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=s_ivc_status%>"/>
<tsa:hidden name="InvoiceHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="<%=icLine%>"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "account_information", "Account Information")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Invoice #:</b></td>
			<td width=125px><%=s_ivc_number%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=125px><%=DocumentStatus.toString(s_ivc_status, oid)%></td>
		</tr>
		</table>
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
<%@ include file="/invoice/iv_info.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center>
		<%@ include file="/base/account_form_rows.jsp" %>
	</TD>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if ((s_ivc_status.compareTo(DocumentStatus.IVC_INPROGRESS) == 0 || s_ivc_status.equals(DocumentStatus.IVC_APPROVING) || 
		s_ivc_status.compareTo(DocumentStatus.IVC_APPROVED) == 0 || s_ivc_status.compareTo(DocumentStatus.IVC_RECALLED) == 0) && allowAccountTaxes) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/invoice/iv_accounts.jsp', 'AccountUpdate;AccountRetrieveByLine'); void(0);">Save</a></div></td>
<%	} else if ((s_ivc_status.compareTo(DocumentStatus.IVC_REJECTED) == 0 && user.getVchApp().equalsIgnoreCase("Y"))  && allowAccountTaxes) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/invoice/iv_accounts.jsp', 'AccountUpdate;AccountRetrieveByLine'); void(0);">Save</a></div></td>
<%	} %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: retrieveAccounts(); void(0);">Return</a></div></td>
</tr>
</table>

<br>
<br>

<div id="hiddenFields" style="visibility:hidden; display:none;"></div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
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

<%	if ((s_ivc_status.compareTo(DocumentStatus.IVC_INPROGRESS) == 0 || s_ivc_status.compareTo(DocumentStatus.IVC_RECALLED) == 0) && allowAccountTaxes) { %>
			if (TotalRows == 0)
			{
				addNew();
			}
			if (frm.alloc_method[1].checked == true)
			{
				setMethod();
			}
			if (accountType == "IVU" && TotalRows == 1)
			{
				myCell = document.getElementById("allocPercent_0");
				if (myCell.value == eval(0))
				{
					myCell.value = "100.00";

					myCell = document.getElementById("allocAmount_0");
					myCell.value = "<%=bd_amount_to_allocate%>";
				}
			}
<%	} else if (s_ivc_status.compareTo(DocumentStatus.IVC_REJECTED) == 0  && allowAccountTaxes) { %>
			if (TotalRows == 0)
			{
				addNew();
			}
			if (frm.alloc_method[1].checked == true)
			{
				setMethod();
			}
			if (accountType == "IVU" && TotalRows == 1)
			{
				myCell = document.getElementById("allocPercent_0");
				if (myCell.value == eval(0))
				{
					myCell.value = "100.00";

					myCell = document.getElementById("allocAmount_0");
					myCell.value = "<%=bd_amount_to_allocate%>";
				}
			}
<%	}%>

<%	if (s_account_action.equalsIgnoreCase("returnAllocation")) { %>
			total();
<%	} %>

	function thisLoad()
	{
		f_StartIt();
<%	if ((s_ivc_status.compareTo(DocumentStatus.IVC_APPROVED) > 0)) {
		if((s_ivc_status.equals(DocumentStatus.IVC_REJECTED) && user.getVchApp().equalsIgnoreCase("N"))) {  %>
			checkInputSettings();
<%		} 
	}%>
<%	if (!allowAccountTaxes && !(s_ivc_status.compareTo(DocumentStatus.IVC_APPROVED) == 0)) { %>
			checkInputSettings();
<%	} %>
	}

	function retrieveAccounts()
	{
		frm.formType.value = 'IVH';
		doSubmit('/invoice/iv_accounts.jsp', 'AccountRetrieveByLine');
	}

// End Hide script -->
</SCRIPT>
