<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/accounts.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_req_ic_header = (String) request.getAttribute("RequisitionHeader_icReqHeader");
	String	s_req_number = (String) request.getAttribute("RequisitionHeader_requisitionNumber");
	String	s_req_status = (String) request.getAttribute("RequisitionHeader_status");
	String	s_fiscal_year = (String) request.getAttribute("RequisitionHeader_fiscalYear");
	String	s_req_type = (String) request.getAttribute("RequisitionHeader_requisitionType");
	String	s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_requisitionerCode"));
	String	s_commodity = "" ;
	String	s_req_udf01 = "";
	String  s_req_udf14 = "";
	String  tableType = "AC";
	String	s_vendor_id = "";
	String	s_req_subtype = "";
	String  s_dept_code = "";
	String  labViewGroup = "";

	String checkAccountsOnSave = (String) request.getAttribute("checkAccountsOnSave");
	if(HiltonUtility.isEmpty(checkAccountsOnSave)){ checkAccountsOnSave = "N"; }

	RequisitionLine requisitionLine = (RequisitionLine) request.getAttribute("requisitionLine") ;
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	if (requisitionHeader != null)
	{
		s_vendor_id = requisitionHeader.getVendorId();
		s_req_subtype = requisitionHeader.getRqSubType();
		s_req_udf01 = requisitionHeader.getUdf1Code();
		s_req_udf10 = requisitionHeader.getUdf10Code();
		s_req_udf14 = requisitionHeader.getUdf14Code();
		s_req_departmentCode = requisitionHeader.getDepartmentCode();

		if (s_req_type.equals("O") || oid.equalsIgnoreCase("qri06p"))
		{
			if (oid.equalsIgnoreCase("qri06p"))
			{
				String location = "ACCOUNT_" + s_req_udf01;
				tableType = propertiesManager.getProperty("BROWSE", location, "AC");
			}
		}

		if (oid.equalsIgnoreCase("vse06p"))
		{
			s_dept_code = requisitionHeader.getDepartmentCode();
		}
	}

	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_itemLocation"));
	String	labelPrefix = "req";
	String	accountType = "RQH";
	boolean allowEdit = false;
	boolean fpeUser = user.isAFpe() ;
	boolean engineerUser = user.isAEngineer() ;
	boolean msrEngineer = user.isAnAdministeredBy();

	if (s_req_number == null) {
		s_req_number = (String) request.getAttribute("formNumber");
	}

	if (HiltonUtility.isEmpty(s_req_ic_header)) {
		s_req_ic_header = (String) request.getAttribute("Account_icHeader");
	}

	if (s_req_status == null) {
		s_req_status = (String) request.getAttribute("formStatus");
	}

	String	s_current_process = "HEADER_ACCOUNTS";
	String	s_current_page = "/requests/req_accounts.jsp";
	String	s_current_method = "AccountUpdate";
	String	s_current_process_method = "";

	if (s_req_type.equals("M") && (fpeUser || msrEngineer)  &&  (s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) < 0) ) {
		allowEdit = true ;
	} 	else if ((s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVING) >= 0 && s_req_type.equals("M"))  || s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0) {
		s_current_method = "DoNothing";
	} else if (s_req_status.compareTo(DocumentStatus.REQ_INPROGRESS) >= 0 && s_req_type.equals("C")
			&& s_req_udf01 != null && s_req_udf01.contains("RESALE")
			&& s_req_udf14 != null && s_req_udf14.equals("DBS") ) {
		s_current_method = "DoNothing";
	} else {
		allowEdit = true;
	}
	if(s_req_type.equals("P") && s_req_status.compareTo(DocumentStatus.REQ_REJECTED) == 0 && !fpeUser){
		allowEdit = false;
	}

%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="Default_referenceType" value="<%=accountType%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="formNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_req_status%>"/>
<tsa:hidden name="formType" value="<%=accountType%>"/>
<tsa:hidden name="RequisitionHeader_udf1Code" value="<%=s_req_udf01%>"/>

<tsa:hidden name="checkAccountsOnSave" value="<%=checkAccountsOnSave %>"/>

<%
	if (HiltonUtility.isEmpty(s_req_number))
	{
		s_req_number = "N/A";
	}
%>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
	<% if (s_req_type.equals("M")) {%>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="req-type" defaultString="Req Type"/>&nbsp;<tsa:label labelName="account_information" defaultString="Account Information" /></div>
			</tsa:td>
		</tsa:tr>
	<% } else { %>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="account_information" defaultString="Account Information" /></div>
			</tsa:td>
		</tsa:tr>
	<% } %>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<%@ include file="/requests/req_display_number.jsp" %>
	</tsa:td>
</tsa:tr>
</table>

<br>
<%@ include file="/requests/req_info.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tsa:tr>
	<tsa:td valign="top">
		<%@ include file="/base/account_form_rows.jsp" %>

<%	if ((s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 && s_req_status.compareTo(DocumentStatus.REQ_REJECTED) != 0) || s_req_type.equals("P") && s_req_status.compareTo(DocumentStatus.REQ_REJECTED) == 0 && user.isAFpe()) {
		String s_recent_accounts = propertiesManager.getProperty("REQ OPTIONS", "HIDE FAVORITE ACCOUNTS", "N");
		if (s_recent_accounts.equalsIgnoreCase("N")) { %>

		<%@ include file="/base/recentaccounts.jsp" %>

<%		}
	} 	%>

<%	if (s_req_subtype.equals("X") && s_vendor_id.equals("06440") && oid.equals("BSC04P")) {%>
		<br><br><br>
		<table border=0 cellpadding=0 cellspacing=0 width=500px>
		<tsa:tr><tsa:td cssClass="red"><b><u><tsa:label labelName="notice" defaultString="Notice" /></u></b> <tsa:label labelName="desc-notice" defaultString="OfficeMax's systems can only charge one cost center per line item.  If you allocate to multiple Cost Centers you will have to have Blue Shield Accounts Payable handle the cost center allocation on the back end." /></tsa:td></tsa:tr>
		</table>
<%	}%>
	</tsa:td>
<%	if (s_view.equalsIgnoreCase("WIZARD")) { %>
	<tsa:td rowspan="2" align="right" valign="top"><%@ include file="/requests/req_sidebar.jsp" %></tsa:td>
<%	}%>
</tsa:tr>
</table>

<br>
<br>

<div id="hiddenFields" style="visibility: hidden; display: none;"></div>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tsa:tr>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: saveAccounts(); void(0);"><tsa:label labelName="req-save" defaultString="Save"></tsa:label></a></div></tsa:td>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('/requests/req_summary.jsp', 'RequisitionRetrieve'); void(0);"><tsa:label labelName="req-return" defaultString="Return"></tsa:label></a></div></tsa:td>
</tsa:tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var reqIcHeader = "<%=headerEncoder.encodeForJavaScript(s_req_ic_header)%>";
	var reqnumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
	var reqtype = "<%=headerEncoder.encodeForJavaScript(s_req_type)%>";
	var fiscalyear = "<%=headerEncoder.encodeForJavaScript(s_fiscal_year)%>";
	var currentpage = "<%=headerEncoder.encodeForJavaScript(s_current_page)%>";
	var currentmethod = "<%=headerEncoder.encodeForJavaScript(s_current_method)%>";
	var currentprocessmethod = "<%=headerEncoder.encodeForJavaScript(s_current_process_method)%>";

	var myTable = document.getElementById("accounts");
	var TotalRows = myTable.rows.length - 1;
	var accRows = frm.as_rows.value;
	var maxRows = frm.as_maxrows.value;
	var allocMethod = frm.as_alloc_method.value;
	var qtyDec  = <%=headerEncoder.encodeForJavaScript(s_quantity_decimals)%>;
	var type = "HDR";  /*used for setting allocMethod in accounts.js*/
	var accountType = "<%=headerEncoder.encodeForJavaScript(accountType)%>";

	var amtToAllocate = <%=bd_amount_to_allocate%>;

	if(frm.checkAccountsOnSave.value == "Y"){ reqSaveCheckAccount(); }

<%	if (allowEdit) { %>
		if (TotalRows == 0) {
			addNew();
		}
		if (frm.alloc_method[1].checked == true) {
				setMethod();
		}
<%	}%>

<%	if (s_account_action.equalsIgnoreCase("returnAllocation")) { %>
		total();
<%	} %>

<%	if (s_req_type.equalsIgnoreCase("O") && s_req_udf01.length() > 0) { %>
			if (frm.Account_fld4)
			{
				frm.Account_fld4.value = "<%=s_req_udf01%>";
			}
<%	} %>

	function thisLoad() {
		f_StartIt();
<%		if (!allowEdit) {  %>
			checkInputSettings();
<%		} %>
    }

	function setProjectCode(code)
	{
		if (reqtype == "O")
		{
			frm.RequisitionHeader_udf1Code.value = code;
			if (maxRows > 1)
			{
				for (var i = 0; i < maxRows; i++)
				{
					frm.Account_fld4[i].value = code;
				}
			}
		}
	}


	function reqSaveCheckAccount()
	{
	  if (isNA(reqnumber))
	  {
	  	if(frm.checkAccountsOnSave.value == "Y")
		{
			popupParameters = "formtype=REQ;formnumber=" + reqnumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentprocessmethod + ";Account_icHeader=" + frm.RequisitionHeader_icReqHeader.value;
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
		doSubmit('/requests/req_summary.jsp', '<%=s_current_method%>;RequisitionRetrieve');
	}


// End Hide script -->
</SCRIPT>