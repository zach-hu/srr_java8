<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	List requisitionLineList = (List) request.getAttribute("requisitionLineList");
	BigDecimal b_req_ic_header = requisitionHeader.getIcReqHeader();
	String	s_req_number = requisitionHeader.getRequisitionNumber();
	String	s_req_type = requisitionHeader.getRequisitionType();
	String	s_req_subtype = requisitionHeader.getRqSubType();
	String	s_vendor_id = requisitionHeader.getVendorId();
	String	currencyCode = requisitionHeader.getCurrencyCode() ;
	String  noApprovalNeed = HiltonUtility.ckNull((String)request.getAttribute("NoApprovalNeed"));
	String	s_req_title = "";
	String	s_fiscal_year = requisitionHeader.getFiscalYear();
	String	s_status_code = requisitionHeader.getStatus();
	String reqAction = HiltonUtility.ckNull((String) request.getAttribute("reqaction"));
	String createdFrom = (String) request.getAttribute("createdFrom") ;
	if (createdFrom == null) createdFrom = "" ;
	boolean continueForward = false;
	if (s_req_type == null) {
		s_req_title = "Requisition ";
	}
	else {
		s_req_title = RequisitionType.toString(s_req_type, oid);
	}

	String s_forwardedto = HiltonUtility.ckNull((String) request.getAttribute("forwardedTo"));
	List reqSplitList = (List) request.getAttribute("splitRequisitionList");
	boolean changeReqType = HiltonUtility.ckNull((String) request.getAttribute("changeReqType")).equals("Y");
	List lineList = (List) requisitionHeader.getRequisitionLineList();
	int	i_line_count = 0;

	if (lineList != null)
	{
		i_line_count = lineList.size();
	}
%>
<tsa:hidden name="formtype" value="REQ"/>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=requisitionHeader.getStatus()%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
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
<tsa:hidden name="icHeaderEdit" value="<%=requisitionHeader.getIcReqHeader()%>"/>
<tsa:hidden name="icHeaderHistoryEdit" value="<%=requisitionHeader.getIcHeaderHistory()%>"/>
<tsa:hidden name="HistoryLog_icHeader" value="<%=requisitionHeader.getIcReqHeader()%>"/>
<tsa:hidden name="budgetAction" value=""/>
<tsa:hidden name="budgetMake"  value=""/>
<tsa:hidden name="budgetType" value=""/>
<tsa:hidden name="budgetYear" value="<%=requisitionHeader.getFiscalYear()%>"/>
<tsa:hidden name="newReqType" value=""/>


<table border="0" cellpadding="0" cellspacing="0" width=<%=formWidth%>>
<tsa:tr><tsa:td><br></tsa:td></tsa:tr>
<tsa:tr>
  <tsa:td width="100%">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tsa:tr>
      <tsa:td valign="top" width="50px" height="30px">
        <table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
        <tsa:tr>
          <tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
        </tsa:tr>
        <tsa:tr>
          <tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
	<% if (createdFrom.equalsIgnoreCase("SOURCING")) {  %>
            <div style="margin-left:10px; margin-right:10px" class="hdr12"><tsa:label labelName="req-type" defaultString="Req Type"/>&nbsp;<tsa:label labelName="req-sourcingResults" defaultString="Sourcing Results"/></div>
	<% } else { %>
            <div style="margin-left:10px; margin-right:10px" class="hdr12"><tsa:label labelName="req-forwardProcess" defaultString="Requisition Forward"/></div>
     <% } %>
          </tsa:td>
        </tsa:tr>
        </table>
      </tsa:td>
      <tsa:td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
      <tsa:td valign="bottom" align="right" height="30px" width="100%">
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tsa:tr>
          <tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td>
        </tsa:tr>
        <tsa:tr>
          <tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
        </tsa:tr>
        </table>
      </tsa:td>
    </tsa:tr>
    </table>
  </tsa:td>
</tsa:tr>
<tsa:tr><tsa:td><br><br></tsa:td></tsa:tr>
<tsa:tr>
	<tsa:td width="100%" align="center" valign="top">
<%	if (changeReqType && s_req_type.equals("S")) {%>
		<table width="<%=formEntryWidth%>" cellpadding="0" cellspacing="0" border="0">
		<tsa:tr>
			<tsa:td align="center" cssClass="message">
				<b><tsa:label labelName="req-requisition" defaultString="Requisition #"/>:&nbsp;<%=headerEncoder.encodeForHTML(s_req_number)%>&nbsp;<tsa:label labelName="convertedToSupply" defaultString="has been converted to a Supply Request"/> .</b>
				<br>
				<tsa:label labelName="continueForward" defaultString="Click the continue button below to forward this request."/>
			</tsa:td>
		</tsa:tr>
		</table>
<%	} else if (reqSplitList != null && reqSplitList.size() > 0) {%>
		<table width="<%=formEntryWidth%>" cellpadding="0" cellspacing="0" border="0">
		<tsa:tr>
			<tsa:td align="center">
				<% if (createdFrom.equalsIgnoreCase("SOURCING")) {  %>
					<b><%=RequisitionType.toString(s_req_type, oid)%>#:&nbsp;<%=headerEncoder.encodeForHTML(s_req_number)%>&nbsp;has been split as shown below. </b>
				<% } else { %>
					<b><%=RequisitionType.toString(s_req_type, oid)%>#:&nbsp;<%=headerEncoder.encodeForHTML(s_req_number)%>&nbsp;<tsa:label labelName="purchaseReqSplit" defaultString="has been split as shown below.  Click the 'Continue' button below to continue forwarding the original request"/>.</b>
				<% } %>
			</tsa:td>
		</tsa:tr>
		</table>
<%	} else {
		continueForward = true;
%>
		<table width="<%=formEntryWidth%>" cellpadding="0" cellspacing="0" border="0">
		<tsa:tr>
			<tsa:td width="100%" align="center" valign="middle" noWrap="nowrap">
				<br><b><b>&nbsp;<tsa:label labelName="processing" defaultString="Processing... Please wait."/>.</b>
			</tsa:td>
		</tsa:tr>
		</table>
<%	}%>
	</tsa:td>
</tsa:tr>
</table>

<br>

<table width="<%=formEntryWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td width="5%">&nbsp;</tsa:td>
	<tsa:td width="90%" align="center" valign="top">
<%	if (reqSplitList != null && reqSplitList.size() > 0) {
		for (int ih=0; ih < reqSplitList.size(); ih++) {
			RequisitionHeader splitRequisition = (RequisitionHeader) reqSplitList.get(ih);
			String	splitReqType = splitRequisition.getRequisitionType();
			String	buyerCode = splitRequisition.getAssignedBuyer();
			StringBuffer forwardedToNames = new StringBuffer("");

			s_forwardedto = splitRequisition.getForwardedTo();

			if (!HiltonUtility.isEmpty(s_forwardedto)) {
				String forwardedToArray[] = s_forwardedto.split(";");
				for (int ift=0; ift < forwardedToArray.length; ift++) {
					forwardedToNames.append(UserManager.getInstance().getUser(oid, forwardedToArray[ift]).getDisplayName(s_req_type) + ", ");
				}
				if (forwardedToNames.length() > 2) {
					forwardedToNames.setLength(forwardedToNames.length() - 2);
				}
			}
%>
		<table border="1" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
		<tsa:tr>
			<tsa:td height="18px" cssClass="browseHdr">
				<%=RequisitionType.toString(splitReqType, oid)%>&nbsp;#<%=headerEncoder.encodeForHTML(splitRequisition.getRequisitionNumber())%> (<%=DocumentStatus.toString(splitRequisition.getStatus(), oid)%>)
<%			if (splitRequisition.getStatus().equals(DocumentStatus.REQ_APPROVING) && !HiltonUtility.isEmpty(s_forwardedto)) { %>
				<tsa:label labelName="wasForwardedTo" defaultString="was Forwarded to"/> <%=forwardedToNames.toString()%>!
<%			} else if (!HiltonUtility.isEmpty(buyerCode)) {
				if (splitReqType.equalsIgnoreCase("S") || splitReqType.equalsIgnoreCase("T") || splitReqType.equalsIgnoreCase("R") || splitReqType.equalsIgnoreCase("I")) {%>
				<tsa:label labelName="wasForwardedTo" defaultString="was Forwarded to"/> <%=UserManager.getInstance().getUser(oid, buyerCode).getDisplayName(splitReqType)%>!
<%				} else {%>
				<tsa:label labelName="wasAssignedTo" defaultString="was Assigned to"/> <%=UserManager.getInstance().getUser(oid, buyerCode).getDisplayName("")%>!
<%				}
			}%>
			</tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td>
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
				<tsa:tr>
					<tsa:td width="25%" height="18px" noWrap="nowrap"><u><tsa:label labelName="item/part" defaultString="Item/Part" checkRequired="false"/> #</u></tsa:td>
					<tsa:td width="45%" height="18px" noWrap="nowrap"><u><tsa:label labelName="itemDescription" defaultString="Item Description" checkRequired="false"/></u></tsa:td>
					<tsa:td width="15%" height="18px" noWrap="nowrap"><u><tsa:label labelName="location" defaultString="Location" checkRequired="false"/></u></tsa:td>
					<tsa:td width="10%" height="18px" noWrap="nowrap" align="right"><u><tsa:label labelName="quantity" defaultString="Quantity" checkRequired="false"/></u></tsa:td>
					<tsa:td width="5%" height="18px" noWrap="nowrap"><u><tsa:label labelName="uom" defaultString="UOM" checkRequired="false"/></u></tsa:td>
				</tsa:tr>
<%			List	reqLineList = splitRequisition.getRequisitionLineList();
			for (int i = 0; i < reqLineList.size(); i++) {
				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);
				BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid);
				BigDecimal bd_um_factor = HiltonUtility.getFormattedDollar(reqLine.getUmFactor(), oid);
%>
				<tsa:tr>
					<tsa:td width="25%" height="18px" cssClass="browseRow" valign="top" noWrap="nowrap"><%=reqLine.getItemNumber()%>&nbsp;</tsa:td>
					<tsa:td width="45%" height="18px" cssClass="browseRow" valign="top"><%=reqLine.getDescription()%></tsa:td>
					<tsa:td width="15%" height="18px" cssClass="browseRow" valign="top" noWrap="nowrap"><%=reqLine.getItemLocation()%>&nbsp;</tsa:td>
					<tsa:td width="10%" height="18px" cssClass="browseRow" valign="top" noWrap="nowrap" align="right"><%=HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid)%>&nbsp;</tsa:td>
					<tsa:td width="5%" height="18px" cssClass="browseRow" valign="top" noWrap="nowrap"><%=reqLine.getUmCode()%>&nbsp;</tsa:td>
				</tsa:tr>
<%			}%>
				</table>
			</tsa:td>
		</tsa:tr>
		</table>
<%		}
	}%>
		<br>
	</tsa:td>
	<tsa:td width="5%">&nbsp;</tsa:td>
</tsa:tr>
</table>
<br>
<br>

<%	if (!continueForward) {%>
<table width="<%=formEntryWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<% if (createdFrom.equalsIgnoreCase("SOURCING")) {  %>
	<tsa:td align="center" noWrap="nowrap" width="50%">
		<div id="pxbutton">
		<a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);"><tsa:label labelName="continue" defaultString="Menu"/></a>
		</div>
	</tsa:td>
	<% } else { %>
	<tsa:td align="center" noWrap="nowrap" width="50%">
		<div id="pxbutton">
		<a href="javascript: continueForward(); void(0);"><tsa:label labelName="continue" defaultString="Continue"/></a>
		</div>
	</tsa:td>
	<% } %>
	<tsa:td align="center" noWrap="nowrap" width="50%">
		<div id="pxbutton">
		<a href="javascript: returnMe(); void(0);"><tsa:label labelName="return" defaultString="Return"/></a>
		</div>
	</tsa:td>
</tsa:tr>
</table>
<br>
<br>
<%	}%>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

  	var navMenu = getNavCookie("navArray");
	var reqnumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
	var fiscalyear = "<%=requisitionHeader.getFiscalYear()%>";
	var reqType = '<%=request.getParameter("RequisitionHeader_requisitionType")%>' ;
	var autoDisb = '<%=propertiesManager.getProperty("INVENTORY","AutoCreateDisb","N")%>' ;

	hideArea("navTable");
	displayArea("menubarSpacer");

<%	if (continueForward) {%>
	continueForward();
<%	}%>

	function continueForward() {
	    var reqaction = '<%=reqAction%>';
	    frm.reqaction.value = reqaction;
<%	if ( (s_req_type.equals("S") || s_req_type.equals("T") || s_req_type.equals("I")) && ! propertiesManager.getProperty("APPROVALS","ApproveInvRequests","N").equalsIgnoreCase("Y") ) {%>
    	forward();
<%	} else {
		if (propertiesManager.getProperty("MODULES", "ACCOUNT BUDGET", "N").equalsIgnoreCase("Y")) {
			if (propertiesManager.getProperty("BUDGET", "BUDGETSERVICEACTIVE", "N").equalsIgnoreCase("Y")) { %>
		doSubmit('/requests/req_budget_service_check_no_popup.jsp', 'CheckAccountsForDocumentLine;BudgetServiceCheck');
<%			} else { %>
		doSubmit('/requests/req_budget_check_no_popup.jsp', 'RequisitionBudgetCheck');
<%			}
		} else { %>
		doSubmit('/requests/req_routinglist_no_popup.jsp', 'RequisitionGetRoutingList');
<%		}
	}%>
	}

	function forward() {
		frm.reqaction.value = "FORWARD";
		if (reqType == "S" && autoDisb == "Y") {
			doSubmit('/requests/req_forward.jsp', 'RequisitionForward;DisbursementCreate');
		} else {
			doSubmit('/requests/req_forward.jsp', 'RequisitionForward');
		}
	}

	function returnMe() {
		doSubmit('/requests/req_review.jsp', 'RequisitionRetrieve');
	}

// End Hide script -->
</SCRIPT>