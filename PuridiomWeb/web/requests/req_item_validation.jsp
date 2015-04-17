<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<%
	Encoder encoder = DefaultEncoder.getInstance();
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	ValidationRules rules = (ValidationRules)request.getAttribute("rules");
	RequisitionHeader requisitionHeader = (RequisitionHeader)request.getAttribute("header");
	List requisitionLineList = (List)request.getAttribute("lineitems");
	BigDecimal icReqline = new BigDecimal(-1);
	if(requisitionLineList != null && requisitionLineList.size()==1){
		icReqline = ((RequisitionLine)requisitionLineList.get(0)).getIcReqLine();
	}
	String	s_req_status = requisitionHeader.getStatus();
	String	s_req_type = requisitionHeader.getRequisitionType();
	String	s_req_subtype = requisitionHeader.getRqSubType();
	String 	s_fiscal_year = requisitionHeader.getFiscalYear();
	String	s_curr_code = requisitionHeader.getCurrencyCode();
	String	s_req_number = requisitionHeader.getRequisitionNumber();
	String	s_vendor_id = requisitionHeader.getVendorId();
	String	s_current_process = "LINE_VALIDATION";
	String	s_current_page = "/requests/req_item_validation.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "DoNothing";
	String extraSubjectFYI = HiltonUtility.ckNull((String) request.getAttribute("extraSubjectFYI"));
	boolean bForward = false;
	String reqAction = (String)request.getAttribute("reqaction");
	String	pricingAction = "";
	if (reqAction == null){ reqAction = "VALIDATE";	}

	if (reqAction.equalsIgnoreCase("REPRICING")) {
		pricingAction = reqAction;
		reqAction = "FORWARD";
	}

	if (s_req_type.equals("M") && (s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVED) == 0 || s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCING) == 0 || s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) == 0 ))
	{
		s_current_process = "SOURCING";
	}

	List lineList = (List) requisitionHeader.getRequisitionLineList();
	int	i_line_count = 0;

	if (lineList != null)
	{
		i_line_count = lineList.size();
	}
%>

<tsa:hidden name="formtype" value="REQ"/>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=requisitionHeader.getIcReqHeader()%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=requisitionHeader.getIcReqHeader()%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value="<%=icReqline%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=requisitionHeader.getRequisitionNumber()%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=requisitionHeader.getStatus()%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_vendorId" value="<%=s_vendor_id%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=requisitionHeader.getItemLocation()%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=requisitionHeader.getFiscalYear()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=requisitionHeader.getIcReqHeader()%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RQH"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=requisitionHeader.getIcReqHeader()%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=requisitionHeader.getIcReqHeader()%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="lineCount" value="<%=i_line_count%>"/>
<tsa:hidden name="reqaction" value=""/>
<tsa:hidden name="frompage" value="validation"/>
<tsa:hidden name="pricingAction" value="<%=pricingAction%>"/>
<tsa:hidden name="icHeaderEdit" value="<%=requisitionHeader.getIcReqHeader()%>"/>
<tsa:hidden name="icHeaderHistoryEdit" value="<%=requisitionHeader.getIcHeaderHistory()%>"/>
<tsa:hidden name="HistoryLog_icHeader" value="<%=requisitionHeader.getIcReqHeader()%>"/>
<tsa:hidden name="budgetAction" value=""/>
<tsa:hidden name="budgetMake"  value=""/>
<tsa:hidden name="budgetType" value=""/>
<tsa:hidden name="budgetYear" value="<%=requisitionHeader.getFiscalYear()%>"/>
<tsa:hidden name="newReqType" value=""/>
<tsa:hidden name="extraSubjectFYI" value="<%=extraSubjectFYI%>"/>


<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
	<% if (s_req_type.equals("M")) { %>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="req-type" defaultString="Req Type"/>&nbsp;<tsa:label labelName="validationResults" defaultString="Validation Results"/></div>
			</tsa:td>
		</tsa:tr>
	<% } else { %>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="validationResults" defaultString="Validation Results"/></div>
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

<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
<tsa:tr><tsa:td height="36px">&nbsp;</tsa:td></tsa:tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
<tsa:tr>
	<tsa:td valign="top" align="center">
	<%@ include file="/base/validation_rules.jsp" %>
	</tsa:td>
		<!-- tsa:td align="right" valign="top"--><%//@ include file="/requests/req_sidebar.jsp" %><!-- /tsa:td-->
</tsa:tr>
<tr height=15px>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/requests/req_item.jsp', 'RequisitionLineRetrieve'); void(0);">Return</a></div></td>
</tr>
</table>

<br>
<br>
<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

  	var navMenu = getNavCookie("navArray");
	var reqnumber = "<%=encoder.encodeForJavaScript(s_req_number)%>";
	var fiscalyear = "<%=encoder.encodeForJavaScript(s_fiscal_year)%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var reqType = "<%=encoder.encodeForJavaScript(s_req_type)%>" ;
	var autoDisb = '<%=propertiesManager.getProperty("INVENTORY","AutoCreateDisb","N")%>' ;

	if (navMenu.indexOf("<%=RequisitionType.toString(s_req_type, oid)%>") < 0)
	{
		currentprocessmethod = currentprocessmethod.replace(";",":");
		setNavCookie(currentpage, currentprocessmethod, "<%=RequisitionType.toString(s_req_type, oid)%>");
		currentprocessmethod = currentprocessmethod.replace(":",";");
	}

<%	if (rules.getResult() == 1) {%>
	displayArea('novalidationrules');
	<% if (s_req_type.equals("M") && (s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVED) == 0 || s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCING) == 0 || 
			s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) == 0 || s_req_status.compareTo(DocumentStatus.REQ_APPROVED) == 0 || s_req_status.compareTo(DocumentStatus.REQ_ORDERED) == 0 || 
			s_req_status.compareTo(DocumentStatus.REQ_RECEIVED) == 0 || s_req_status.compareTo(DocumentStatus.REQ_KITTED) == 0 || s_req_status.compareTo(DocumentStatus.REQ_ISSUED) == 0 || 
			s_req_status.compareTo(DocumentStatus.REQ_CLOSED) == 0) && user.isAFpe()) {%>
			displayArea('sourcing_link');
			hideArea('forward_link');
	<% } else {%>
		hideArea('sourcing_link');
		displayArea('forward_link');
	<% } %>
<%	} else {%>
	displayArea('validationrules');
	<%	if (rules.getResult() == -1) { %>
	hideArea('forward_link');
	hideArea('sourcing_link');
<%		}
	}%>

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menuBarSpacer");
	}

  function reqForward()
  {
    var reqaction = "<%=encoder.encodeForJavaScript(reqAction)%>";
    frm.reqaction.value = reqaction;
<%	if ( pricingAction.equalsIgnoreCase("repricing") ||
		( s_req_type.equals("N") && requisitionHeader.getStatus().equals(DocumentStatus.REQ_INPROGRESS) ) ||
		( (s_req_type.equals("S") || s_req_type.equals("T") || s_req_type.equals("I")) && propertiesManager.getProperty("APPROVALS","ApproveInvRequests","N").equalsIgnoreCase("Y") ) ) {%>
    	forward();
<%	} else {
		if (propertiesManager.getProperty("MODULES", "ACCOUNT BUDGET", "N").equalsIgnoreCase("Y")) {
			if (propertiesManager.getProperty("BUDGET", "BUDGETSERVICEACTIVE", "N").equalsIgnoreCase("Y")) { %>
//      	  doSubmit('/requests/req_budget_check_no_popup.jsp', 'CheckAccountsForDocumentLine;RequisitionBudgetCheck');
      	  doSubmit('/requests/req_budget_service_check_no_popup.jsp', 'CheckAccountsForDocumentLine;BudgetServiceCheck');
      	  //doSubmit('/requests/req_routinglist_no_popup.jsp', 'RequisitionGetRoutingList');
<%			} else { %>
      	  doSubmit('/requests/req_budget_check_no_popup.jsp', 'RequisitionBudgetCheck');
<%			}
		} else { %>
	      doSubmit('/requests/req_routinglist_no_popup.jsp', 'RequisitionGetRoutingList');
<%		}
	}%>
  }
  function forward()
  {
      frm.reqaction.value = "FORWARD";
      if ((reqType == "S" || reqType == "T") && autoDisb == "Y") {
	      doSubmit('/requests/req_forward.jsp', 'RequisitionForward;DisbursementCreate');
      } else {
			doSubmit('/requests/req_forward.jsp', 'RequisitionForward');
	  }
  }

  function returnMe()
  {
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
	doSubmit('requests/req_summary.jsp', 'RequisitionRetrieve');
<%	} else { %>
	doSubmit('requests/req_review.jsp', 'RequisitionRetrieve');
<%	} %>
  }

  function thisUnLoadPopup()
  {
      window.parent.showForwardButton();
  }

  function forwardReq()
  {
	frm.reqaction.value = "FORWARD";
<%	if (s_req_type.equals("P") && propertiesManager.getProperty("REQUISITIONS","INTELLIGENTPURCHASEREQ","N").equalsIgnoreCase("Y")) {%>
		forwardIntelligentPurchaseReq();
<%	} else if (pricingAction.equalsIgnoreCase("repricing") ||  s_req_type.equals("I") ||
		(s_req_type.equals("N") && requisitionHeader.getStatus().equals(DocumentStatus.REQ_INPROGRESS) ) ||
		((s_req_type.equals("S") || s_req_type.equals("T")) && ! propertiesManager.getProperty("APPROVALS","ApproveInvRequests","N").equalsIgnoreCase("Y") ) ) {%>
		forward();
<%	} else if (s_req_type.equals("N") && requisitionHeader.getStatus().equals(DocumentStatus.RFQ_PRICED) &&
				propertiesManager.getProperty("REQ OPTIONS","CHANGE REQ TYPE DIRECTLY","N").equalsIgnoreCase("Y")) { %>
		frm.newReqType.value = "P";
		doSubmit('/requests/req_review.jsp', 'ReqChangeType;RequisitionRetrieve');
<%	} else {
		if (propertiesManager.getProperty("MODULES", "ACCOUNT BUDGET", "N").equalsIgnoreCase("Y")) {
			if (propertiesManager.getProperty("BUDGET", "BUDGETSERVICEACTIVE", "N").equalsIgnoreCase("Y")) { %>
			doSubmit('/requests/req_budget_service_check_no_popup.jsp', 'CheckAccountsForDocumentLine;BudgetServiceCheck');
		 <% } else { %>
	  		doSubmit('/requests/req_budget_check_no_popup.jsp', 'RequisitionBudgetCheck');
    	<%  }
		} else { %>
			doSubmit('/requests/req_routinglist_no_popup.jsp', 'RequisitionGetRoutingList');
<%	 	}
	}%>
  }

	function forwardIntelligentPurchaseReq() {
		doSubmit('/requests/req_intelligent_review.jsp', 'RequisitionForwardIntelligentPurchase');
	}

	function sourcing()
	{
		doSubmit('/requests/req_sourcing.jsp', 'RequisitionLineSourcingRetrieveByHeader');
	}

// end hiding contents -->
</SCRIPT>