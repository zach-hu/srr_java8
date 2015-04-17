<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/accounts.js"></SCRIPT>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_ivc_ic_header = HiltonUtility.ckNull((String) request.getAttribute("InvoiceHeader_icIvcHeader"));
	String	s_ivc_number = HiltonUtility.ckNull((String) request.getAttribute("InvoiceHeader_invoiceNumber"));
	String	s_ivc_status = HiltonUtility.ckNull((String) request.getAttribute("InvoiceHeader_status"));
	String	s_fiscal_year = (String) request.getAttribute("InvoiceHeader_fiscalYear");
	String	s_line_ic = (String) request.getAttribute("originalAccount_icLine");
	String	s_line_number = (String) request.getAttribute("lineNumber");
	String	s_commodity = (String) request.getAttribute("InvoiceLine_commodity");
	if (s_commodity == null) s_commodity = "" ;
	String labViewGroup = "";

	String	labelPrefix = "ivc";
	String accountType = "IVL";
	String	tableType = "AC";
	String	s_dept_code = "";
	String  s_buyer_code="";
	boolean allowEdit = false;
	if (s_ivc_status.compareTo(DocumentStatus.IVC_INPROGRESS) == 0 || s_ivc_status.compareTo(DocumentStatus.IVC_APPROVING) == 0)
	{
		allowEdit = true;
	}
	else if (oid.equalsIgnoreCase("msg07p") && s_ivc_status.equals(DocumentStatus.IVC_REJECTED))
	{
		allowEdit = true;
	}

	String override = HiltonUtility.ckNull((String) request.getAttribute("override"));
%>
<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="InvoiceLine_icIvcLine" value="<%=s_line_ic%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=s_ivc_number%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=s_ivc_status%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="<%=s_line_ic%>"/>
<tsa:hidden name="ApprovalLog_icHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="formType" value="IVL"/>
<tsa:hidden name="override" value="<%=override%>"/>

<tsa:hidden name="originalAccount_icLine" value="<%=s_line_ic%>"/>
<tsa:hidden name="lineNumber" value="<%=s_line_number%>"/>
<tsa:hidden name="fromSave" value=""/>

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
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
	<td valign=bottom align=middle width=*>
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
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

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
<%	if (s_ivc_status.compareTo(DocumentStatus.IVC_INPROGRESS) == 0 || s_ivc_status.compareTo(DocumentStatus.IVC_APPROVED) == 0 || s_ivc_status.compareTo(DocumentStatus.IVC_APPROVING) == 0 ) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis('/invoice/iv_accounts_ln_approvals_validation.jsp', 'AccountUpdate;InvoiceApprovalsValidate'); void(0);">Save</a></div></td>
<%	} else if (oid.equalsIgnoreCase("msg07p") && s_ivc_status.compareTo(DocumentStatus.IVC_REJECTED) == 0) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/invoice/iv_approval.jsp', 'AccountUpdate;InvoiceApprovalRetrieve'); void(0);">Save</a></div></td>
<%	} %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/invoice/iv_approval.jsp', 'InvoiceApprovalRetrieve'); void(0);">Return</a></div></td>
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
	var accountType = "IVL";
	var amtToAllocate = <%=bd_amount_to_allocate%>;

<%	if (s_ivc_status.compareTo(DocumentStatus.IVC_INPROGRESS) == 0) { %>
			if (TotalRows == 0)
			{
				addNew();
			}
			if (frm.alloc_method[1].checked == true)
			{
				setMethod();
			}
<%	} else if (oid.equalsIgnoreCase("msg07p") && s_ivc_status.compareTo(DocumentStatus.IVC_REJECTED) == 0) { %>
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
<%	if (s_ivc_status.compareTo(DocumentStatus.IVC_APPROVED) > 0) { %>
	<%	if (oid.equalsIgnoreCase("msg07p")) {
			if (!s_ivc_status.equals(DocumentStatus.IVC_REJECTED)) { %>
				checkInputSettings();
		<%	}
		} else { %>
			checkInputSettings();
	<%	} %>
<%	} %>
	}

	function submitThis(page, handlers)
	{
		frm.fromSave.value = "Y";
		doSubmit(page, handlers);
	}

// End Hide script -->
</SCRIPT>