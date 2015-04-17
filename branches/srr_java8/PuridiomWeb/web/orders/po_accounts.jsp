<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/accounts.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	boolean bAdminChanges = false;
	boolean allowEdit = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99) {
		bAdminChanges = true;
	}
	String	s_ic_po_header = (String) request.getAttribute("PoHeader_icPoHeader");
	String	s_po_number = (String) request.getAttribute("PoHeader_poNumber");
	BigDecimal	bd_zero = new BigDecimal(0);
	String	s_release_number = (String) request.getAttribute("PoHeader_releaseNumber");
	String	s_revision_number = (String) request.getAttribute("PoHeader_revisionNumber");
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	String	s_po_type = (String) request.getAttribute("PoHeader_poType");
	String	s_po_status = (String) request.getAttribute("PoHeader_status");
	String	s_fiscal_year = (String) request.getAttribute("PoHeader_fiscalYear");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_itemLocation"));
	String	s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_buyerCode"));
	String	s_commodity = "" ;
	String s_vendor_id = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_vendorId"));
	String	labelPrefix = "po";
	String	accountType = "POH";
	String tableType = "AC";
	String	s_dept_code = "";
	String checkAccountsOnSave = (String) request.getAttribute("checkAccountsOnSave");
	if(HiltonUtility.isEmpty(checkAccountsOnSave)){ checkAccountsOnSave = "N"; }
	String labViewGroup = "";

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

	String	s_current_process = "HEADER_ACCOUNTS";
	String	s_current_page = "/orders/po_accounts.jsp";
	String	s_current_method = "AccountUpdate";
	String	s_current_process_method = "";

	if ( (role.getAccessRights("PO") > 1) && (s_po_status.compareTo(DocumentStatus.PO_REJECTED) <= 0  || (bAdminChanges && s_po_status.compareTo(DocumentStatus.CANCELLED) < 0 && s_po_status.compareTo(DocumentStatus.PO_AMENDED) != 0)) )
	{
		allowEdit = true;
	}
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer())
	{
		allowEdit = false;
	}
	boolean bAllowEdit = true;
  	if ( (role.getAccessRights("PO") < 2) || (!bAdminChanges && s_po_status.compareTo(DocumentStatus.PO_REJECTED) > 0)  || (s_po_status.compareTo(DocumentStatus.CANCELLED) >= 0) || s_po_status.compareTo(DocumentStatus.PO_AMENDED) == 0) {
	    bAllowEdit = false;
	}
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer() && !user.getUserId().equals(s_buyer_code)) {
		bAllowEdit = false;
	}
	String	invalidFields = (String) request.getAttribute("invalidFields");

	while(!HiltonUtility.isEmpty(invalidFields) && invalidFields.startsWith(";")) {
		if (invalidFields.length() > 1) {
			invalidFields = invalidFields.substring(1);
		} else {
			invalidFields = "";
		}
	}
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoSecurity_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="PoHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="PoHeader_buyerCode" value="<%=s_buyer_code%>"/>
<tsa:hidden name="Default_referenceType" value="<%=accountType%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="formNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_po_status%>"/>
<tsa:hidden name="formType" value="<%=accountType%>"/>
<tsa:hidden name="Schedule_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Schedule_documentType" value="POH"/>
<tsa:hidden name="VendorInsurance_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="VendorInsurance_contNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=s_vendor_id%>"/>
<tsa:hidden name="poIcReqHeader" value="<%= poHeader.getIcReqHeader().toString() %>"/>
<tsa:hidden name="formtype" value="PO"/>

<tsa:hidden name="checkAccountsOnSave" value="<%=checkAccountsOnSave %>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="account_information" defaultString="Account Information" /></div>
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
<%@ include file="/system/error_msg.jsp" %>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top>
		<%@ include file="/base/account_form_rows.jsp" %>
	</td>
<%	if (s_view.equalsIgnoreCase("WIZARD")) { %>
	<td rowspan=2 align=right valign=top><%@ include file="/orders/po_sidebar.jsp" %></td>
<%	}%>
</tr>
</table>

<br>
<br>

<div id="hiddenFields" style="visibility:hidden; display:none;"></div>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
<%		if ( (role.getAccessRights("PO") > 1) && ((s_po_status.compareTo(DocumentStatus.PO_REJECTED) <= 0)  || (bAdminChanges && s_po_status.compareTo(DocumentStatus.CANCELLED) < 0 && s_po_status.compareTo(DocumentStatus.PO_AMENDED) != 0)) )  { %>
	<td width=50% align=center><a href="javascript: saveAccounts(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
<%		} else { %>
	<td width=80% align=center><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
<%		} %>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<br>
<br>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var poIcHeader = "<%=s_ic_po_header%>";
	var ponumber = "<%=headerEncoder.encodeForJavaScript(s_po_number)%>";
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

if(frm.checkAccountsOnSave.value == "Y"){ poSaveCheckAccount(); }

<%	if (s_po_status.compareTo(DocumentStatus.PO_REJECTED) <= 0) { %>
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
	<%	if (!bAllowEdit) { %>
		checkInputSettings();
	<%	} else if(bAllowEdit) { %>
			setInvalidFields("<%=invalidFields%>");
	<%	}%>
	}

	function poValidateForm()
	{
		if (maxRows >= 1 && frm.handler.value.indexOf("AccountUpdate") >= 0)
		{
			for (var i = maxRows; i > 0; i--)
			{
				if (isRowEmpty(i - 1))
				{
					deleteMe(i - 1);
				}
			}

			if (!checkAllocated())
			{
				return false;
			}
		}

		setDefaultType();
		return true;
	}

	function poSaveCheckAccount()	{

		  if (isNA(ponumber == "N/A"))
		  {
			  if(frm.checkAccountsOnSave.value == "Y")
			  {
			        popupParameters = "formtype=PO;formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentprocessmethod + ";Account_icHeader=" + frm.PoHeader_icPoHeader.value;
			    	doSubmitToPopup('/base/save_as_acc_check.jsp', 'AccountRetrieveByHeader', 'WIDTH=350', 'HEIGHT=165');
			  }
			  else
			    {
				    frm.checkAccountsOnSave.value = "Y";
					doSubmit(currentpage, 'AccountUpdate;AccountRetrieveByLine');
			    }
		  }
		  else
		  {
		    doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
		  }
	}

	function saveAccounts()
	{
		if( orgId == "BLY07P" )
		{
			setStatus();
		}
		doSubmit('/orders/po_summary.jsp', 'AccountUpdate;PoRetrieve');
	}

// End Hide script -->
</SCRIPT>