<%@page import="com.tsa.puridiom.requisitionmanager.RequisitionManager"%>
<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.ReferenceInfo" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.entity.RequisitionHeader" %>
<%@ page import="com.tsa.puridiom.entity.UserProfile" %>
<%@ page import="com.tsa.puridiom.usermanager.*" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.encryptor.*" %>
<%@ page import="com.tsagate.properties.*" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.pomanager.*" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>
<%@ page import="com.tsa.puridiom.jasperreports.ReportUtils" %>
<%@ page import="com.tsa.puridiom.common.documents.ScheduleType"%>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tsa.puridiom.stdtable.tasks.StdTableManager" %>
<%
	Encoder encoder = DefaultEncoder.getInstance();
	int i_size;
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	List cmtList = (List) requisitionHeader.getDocCommentList();
	List attachmentList = (List) requisitionHeader.getDocAttachmentList();
	BigDecimal b_req_ic_header = requisitionHeader.getIcReqHeader();
	String	s_req_status = requisitionHeader.getStatus();
	String	s_req_type = requisitionHeader.getRequisitionType();
	String	s_req_subtype = requisitionHeader.getRqSubType();
	String 	s_fiscal_year = requisitionHeader.getFiscalYear();
	String	s_curr_code = requisitionHeader.getCurrencyCode();
	String	s_req_number = requisitionHeader.getRequisitionNumber();
	String	s_msr_number = requisitionHeader.getMsrNumber();
	String	s_vendor_id = requisitionHeader.getVendorId();
	String 	s_bid_waiver = requisitionHeader.getBidWaiver();
	String 	s_udf7Code = requisitionHeader.getUdf7Code();
	String 	s_userRejectAccess = (String)request.getAttribute("userRejectAccess");

	String inspectionDiv = "inspectionDiv" + 0;

	String oidR = (String) request.getAttribute("organizationId");
	PropertiesManager propertiesManagerR = PropertiesManager.getInstance(oidR);
	String uidR = (String)request.getAttribute("userId");
	SimpleDateFormat formatter	= new SimpleDateFormat(propertiesManagerR.getProperty("MISC", "DateFormat", "MM-dd-yyyy"));

	String icHeaderEdit = requisitionHeader.getIcReqHeader().toString();
	String icHeaderHistoryEdit = requisitionHeader.getIcHeaderHistory().toString();
	String errorMsg = HiltonUtility.ckNull((String) request.getAttribute("errorMsg"));

	String	s_po_status = "";
	if(s_req_type.equalsIgnoreCase("C")){
		s_po_status = "4010";
	}

	if(HiltonUtility.isEmpty(s_userRejectAccess)){	s_userRejectAccess = "false";	}

	if (HiltonUtility.isEmpty(s_req_number))	{	s_req_number = "N/A";	}

	List lineList = requisitionHeader.getRequisitionLineList();
	int	i_line_count = 0;

	if (lineList != null)
	{
		i_line_count = lineList.size();
	}
	int iBeforeItem = 0;
	int iAfterItem = 0;

	String lines_identical_vendor = "Y";
	String	showMultiplesSupplier = HiltonUtility.ckNull((String)propertiesManagerR.getProperty("REQ OPTIONS", "MULTIPLESSUPPLIER", "N"));
	if (showMultiplesSupplier.equalsIgnoreCase("Y"))
	{
		for (int i = 1; i < i_line_count; i++)
		{
			RequisitionLine reqLine = (RequisitionLine) lineList.get(i);
			RequisitionLine reqLinePrevious = (RequisitionLine) lineList.get(i-1);

			if (!reqLine.getVendorId().equals(reqLinePrevious.getVendorId()))
			{
				lines_identical_vendor = "N";
				break;
			}
		}
	}

	String displaySolicitationLink = propertiesManagerR.getProperty("REQ OPTIONS", "DISPLAYSOLICITATIONLINK", "N");
%>

<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/scheduleArrays.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/reqOptionArrays.js"></script>
<%@ include file="/system/header.jsp" %>

<%

String cancelAccessOwnReq = String.valueOf(uid.equalsIgnoreCase(requisitionHeader.getOwner()) || uid.equalsIgnoreCase(requisitionHeader.getRequisitionerCode()));
String cancelAccessAssBuy = String.valueOf(uid.equalsIgnoreCase(requisitionHeader.getAssignedBuyer()));
String recallAccess = String.valueOf(uid.equalsIgnoreCase(requisitionHeader.getOwner()));// || uid.equalsIgnoreCase(requisitionHeader.getRequisitionerCode()));
String closeAccess = String.valueOf(uid.equalsIgnoreCase(requisitionHeader.getAssignedBuyer()));
String convertAccess = String.valueOf(uid.equalsIgnoreCase(requisitionHeader.getOwner()) || uid.equalsIgnoreCase(requisitionHeader.getBuyer()) || uid.equalsIgnoreCase(requisitionHeader.getRequisitionerCode()));
String deleteAccess = String.valueOf(uid.equalsIgnoreCase(requisitionHeader.getOwner()));
boolean showForcastedDate = false;
if (s_req_type.equals("M")) {
	closeAccess = "true" ;
	showForcastedDate = true;
}

PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
String displayViewType = propertiesManager.getProperty("REQ OPTIONS", "DISPLAYVIEWTYPE", "N");

UserRole roleR = UserManager.getInstance().getUserRole(oid,uid);

String uploadItemsAccess = propertiesManager.getProperty("UPLOADITEMS","ENABLED","N");
String uploadItemsRole = "";
if (roleR.getAccessRights("UPLOADITEMS")<1) {
	uploadItemsRole = "disabled";
}
else{
	uploadItemsRole = Integer.toString(roleR.getAccessRights("UPLOADITEMS"));
}

if (user.getRequisitioner().equals("Y"))
{
	recallAccess = "true";
}

// FPE Source access (allow updating of sourcing options)
String sourceAccess = String.valueOf(roleR.getAccessRights("REQMFPE") > 0) ;

String buyerId = requisitionHeader.getAssignedBuyer();
%>

<SCRIPT language='Javascript1.2' type="text/javascript">
<!--
	var oid = "<%=oid%>";
	var uid = "${esapi:encodeForJavaScript(userId)}";
	viewType = "<%=encoder.encodeForJavaScript(s_view)%>";
	displayViewType = "<%=encoder.encodeForJavaScript(displayViewType)%>";
	reqNumber = "<%=encoder.encodeForJavaScript(s_req_number)%>";
	reqType = "<%=encoder.encodeForJavaScript(s_req_type)%>";
	reqStatus = "<%=encoder.encodeForJavaScript(s_req_status)%>";
	reqLineCount = <%=i_line_count%>;

	reqPlanning = "<%=DocumentStatus.REQ_PLANNING%>";
	reqPlanningRejected = "<%=DocumentStatus.REQ_PLANNING_REJECTED%>";
	reqPlanningRecalled = "<%=DocumentStatus.REQ_PLANNING_RECALLED%>";
	reqPlanningApproving = "<%=DocumentStatus.REQ_PLANNING_APPROVING%>";
	reqPlanningApproved = "<%=DocumentStatus.REQ_PLANNING_APPROVED%>";
	reqPlanningSourcing = "<%=DocumentStatus.REQ_PLANNING_SOURCING%>";
	reqPlanningSourced = "<%=DocumentStatus.REQ_PLANNING_SOURCED%>";

	reqInProgress = "<%=DocumentStatus.REQ_INPROGRESS%>";
	reqRejected = "<%=DocumentStatus.REQ_REJECTED%>";
	reqRecalled = "<%=DocumentStatus.REQ_RECALLED%>";
	reqForwarded = "<%=DocumentStatus.REQ_FORWARDED%>";
	reqApproving = "<%=DocumentStatus.REQ_APPROVING%>";
	reqApproved = "<%=DocumentStatus.REQ_APPROVED%>";
	reqAmmended = "<%=DocumentStatus.REQ_AMMENDED%>";
	reqAwarded = "<%=DocumentStatus.PO_AWARDED%>";
	rfqPriced = "<%=DocumentStatus.RFQ_PRICED%>";
	poAwarded = "<%=DocumentStatus.PO_AWARDED%>";
	invInProgress = "<%=DocumentStatus.INV_INPROGRESS%>";
	invBackordered = "<%=DocumentStatus.INV_BACKORDERED%>";
	historyStatus = "<%=DocumentStatus.HISTORY%>";
	cancelStatus = "<%=DocumentStatus.CANCELLED%>";
	closeStatus = "<%=DocumentStatus.CLOSED%>";
	templateStatus = "<%=DocumentStatus.TEMPLATE%>";
	cancelAccessOwnReq = "<%=cancelAccessOwnReq%>";
	cancelAccessAssBuy = "<%=cancelAccessAssBuy%>";
	recallAccess = "<%=recallAccess%>";
	closeAccess = "<%=closeAccess%>";
	convertAccess = "<%=convertAccess%>";
	deleteAccess = "<%=deleteAccess%>";
	userRejectAccess = "<%=s_userRejectAccess%>";
	uploadItemsAccess = "<%=uploadItemsAccess%>";
	uploadItemsRole = "<%=uploadItemsRole%>";
	assignedBuyer = "<%=buyerId%>";
	allowApprovalEscalation = <%=propertiesManager.getProperty("APPROVALS", "AllowApprovalEscalation", "N").equals("Y")%>;
	isABuyer = "<%=user.isABuyer()%>";
	fpeUser = <%=user.isAFpe()%>;
	checkRequestExtractActive = <%=propertiesManager.getProperty("SUN", "CheckRequestExtract", "N").equalsIgnoreCase("Y")%>;

	flagConvertPurchaseReq = false;
	if('<%=requisitionHeader.getAssignedBuyer()%>' == '<%=encoder.encodeForJavaScript(user.getUserId())%>'){
		flagConvertPurchaseReq = true;
	}
<%
	 //propertiesManager = PropertiesManager.getInstance(oid);
	if (propertiesManager.getProperty("MODULES", "ACCOUNT BUDGET", "N").equalsIgnoreCase("Y")) { %>
		budgetActive = true ;
<% } else { %>
		budgetActive = false ;
<% } %>

	Array91= createReqOptionsMenu(Array91[0]);

//-->
</SCRIPT>

<%
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	String	s_view_prices = propertiesManager.getProperty("REQ OPTIONS", "ViewPrices", "Y");
	String	s_assign_by_line = propertiesManager.getProperty("ASSIGNMENT","AssignByLine","N");

    String	s_current_process = "HEADER_REVIEW";
    String	s_current_page = "/requests/req_review.jsp";
    String	s_current_method = "DoNothing";
    String	s_current_process_method = "";

    String tableType = "AC";
	if (oid.equalsIgnoreCase("qri06p"))
	{
		String location = "ACCOUNT_" + requisitionHeader.getUdf1Code();
		tableType = propertiesManager.getProperty("BROWSE", location, "AC");
	}

	String formType = "REQ";
	String icHeader = b_req_ic_header.toString();

	String shipViaDescription = "";
	if (oid.equalsIgnoreCase("bly07p")) {
		shipViaDescription = (String) StdTableManager.getInstance(oid).getStdTableDescription(oid, "SHP", requisitionHeader.getShippingCode());
	}
	RequisitionManager reqManager = RequisitionManager.getInstance();
	s_req_appBy = reqManager.getCurrentApprover(oid, icHeader);
%>

<tsa:hidden name="InspectionHeader_icInspNo" value="-1"/>
<tsa:hidden name="InspectionHeader_icMsrLine" value=""/>
<tsa:hidden name="formtype" value="REQ"/>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=requisitionHeader.getRequisitionNumber()%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=requisitionHeader.getStatus()%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_vendorId" value="<%=s_vendor_id%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=requisitionHeader.getItemLocation()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RQH"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="lineCount" value="<%=i_line_count%>"/>
<tsa:hidden name="reqaction" value=""/>
<tsa:hidden name="frompage" value="review"/>
<tsa:hidden name="pricingAction" value=""/>
<tsa:hidden name="icHeaderEdit" value="<%=icHeaderEdit%>"/>
<tsa:hidden name="icHeaderHistoryEdit" value="<%=icHeaderHistoryEdit%>"/>
<tsa:hidden name="HistoryLog_icHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="budgetAction" value=""/>
<tsa:hidden name="budgetMake" value=""/>
<tsa:hidden name="budgetType" value=""/>
<tsa:hidden name="newReqType" value=""/>
<tsa:hidden name="reqHeaderUdf1" value="<%= HiltonUtility.ckNull(requisitionHeader.getUdf1Code()) %>"/>
<tsa:hidden name="allowBrowse" value="true"/>

<%if(s_req_type.equalsIgnoreCase("C")){ %>
	<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<%} %>

<tsa:hidden name="budgetYear" value="<%=requisitionHeader.getFiscalYear()%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=RequisitionType.toString(s_req_type, oid)%> <tsa:label labelName="information" defaultString="Information" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b><tsa:label labelName="req-requisition" defaultString="Requisition #" />:</b></td>
			<td width=150px><%=headerEncoder.encodeForHTML(s_req_number)%></td>
		</tr>
		<tr>
			<td align=right><b><tsa:label labelName="status" defaultString="Status" />:</b></td>
			<td width=150px><%=DocumentStatus.toString(requisitionHeader.getStatus())%></td>
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

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=middle width=100% height=34px>
		<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
		<tr>
			<td align=center width="50%" class="error" height=26px><%=errorMsg%></td>
			<td width="10%">&nbsp;</td>
			<td width="10%">&nbsp;</td>
<%	if (!s_req_type.equals("K") && !oid.equalsIgnoreCase("vse06p") && !oid.equalsIgnoreCase("qri06p")) { %>
			<td width="10%" align="center" nowrap><a href="javascript: void(0);" onMouseOut="popDown('Menu98')" onMouseOver="popUp('Menu98',event)"><tsa:label labelName="schedules" defaultString="Schedules" /></a></td>
<%	} else {%>
			<td width="10%" nowrap>&nbsp;</td>
<%	}%>
			<td width="20%" align="center" nowrap><a href="javascript: void(0);" onMouseOut="popDown('Menu91')" onMouseOver="popUp('Menu91',event)"><tsa:label labelName="moreOptions" defaultString="More Options" /></a></td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%
	String	s_requisitioner_code = requisitionHeader.getRequisitionerCode();
	UserProfile requisitioner = UserManager.getInstance().getUser(oid, s_requisitioner_code);
	String	s_buyer_code = requisitionHeader.getBuyer();
	UserProfile buyer = UserManager.getInstance().getUser(oid, s_buyer_code);
	String	s_authorizedby_code = requisitionHeader.getAuthorizationCode();
	UserProfile authorizedBy = UserManager.getInstance().getUser(oid, s_authorizedby_code);
%>
<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=100% align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td width=50% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=90%>
				<tr>
					<td align=center>
						<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;<tsa:label labelName="generalInformation" defaultString="General Information" /></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr <%=HtmlWriter.isVisible(oid, "requestDate")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="requestDate" defaultString="Request Date" />:</b></td>
							<td nowrap valign=top><%=HiltonUtility.getFormattedDate(requisitionHeader.getRequisitionDate(), oid, userDateFormat)%></td>
						</tr>
						<tr>
							<tsa:td align="right" valign="top" field="req-plannedDate"><b><tsa:label labelName="req-plannedDate" defaultString="Planned Date"/>:</b></tsa:td>
							<td nowrap valign=top><%=HiltonUtility.getFormattedDate(requisitionHeader.getPlannedDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-fiscalYear")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-fiscalYear" defaultString="Fiscal Year" />:</b></td>
							<td nowrap valign=top>
								<%=requisitionHeader.getFiscalYear()%>
								<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=requisitionHeader.getFiscalYear()%>"/>
							</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-requisitionerName")%>>
							<td nowrap align=right valign=top width=40%><b><tsa:label labelName="req-requisitionerName" defaultString="Requisitioner" />:</b></td>
							<td valign=top><%=requisitioner.getDisplayName()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-requisitionerPhone")%>>
							<td nowrap align=right valign=top width=40%><b><tsa:label labelName="req-requisitionerPhone" defaultString="Phone Number" />:</b></td>
							<td valign=top><%=requisitioner.getPhoneNumber()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-department")%>>
							<td align=right valign=top width=40%><b><tsa:label labelName="req-department" defaultString="Department" />:</b></td>
							<td nowrap valign=top><%=requisitionHeader.getDepartmentCode()%></td>
						</tr>
<%	if (!s_req_type.equals("K")) {%>
						<tr <%=HtmlWriter.isVisible(oid, "req-msrnumber")%>>
							<td align=right valign=top width=40%><b><tsa:label labelName="req-msrnumber" defaultString="MSR #" />:</b></td>
							<td nowrap valign=top><%=s_msr_number%></td>
						</tr>
<%  } %>
<%	if (!s_req_type.equals("K")&& !s_req_type.equals("O")) {%>
						<tr <%=HtmlWriter.isVisible(oid, "req-buyerName")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-buyerName" defaultString="Buyer" />:</b></td>
							<td valign=top><%=buyer.getDisplayName()%></td>
						</tr>
<%	}
		if (oid.equalsIgnoreCase("bsc04p")) {
		String	s_designatedapprover_code = requisitionHeader.getUdf9Code();
		UserProfile designatedApprover = UserManager.getInstance().getUser(oid, s_designatedapprover_code);
%>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ09")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ09" defaultString="UDF9" />:</b></td>
							<td valign=top><%=designatedApprover.getDisplayName()%></td>
						</tr>
<%	} %>
						<tr <%=HtmlWriter.isVisible(oid, "req-authorizedByName", s_req_type)%>>
							<td nowrap align=right valign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-authorizedByName", "Authorized By", s_req_type)%>:</b></td>
							<td valign=top><%=authorizedBy.getDisplayName()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-authorizedByPhone", s_req_type)%>>
							<td nowrap align=right valign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-authorizedByPhone", "Authorized By Phone", s_req_type)%>:</b></td>
							<td valign=top><%=authorizedBy.getPhoneNumber()%></td>
						</tr>
<%
	if (s_assign_by_line.equalsIgnoreCase("N") && s_req_status.compareTo(DocumentStatus.REQ_APPROVED) >= 0) {
		String s_assigned_buyer = requisitionHeader.getAssignedBuyer();
		UserProfile assignedBuyer = UserManager.getInstance().getUser(oid, s_assigned_buyer);%>
						<tr <%=HtmlWriter.isVisible(oid, "req-assignedBuyer")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-assignedBuyer" defaultString="Assigned To" />:</b></td>
							<td valign=top><%=assignedBuyer.getDisplayName()%></td>
						</tr>
	<%	}

			List rfqHeaderInfoList = (List) requisitionHeader.getRfqInfoList();

			boolean fpeUser = user.isAFpe() ;
			boolean buyerUser = user.isABuyer() ;

			if (rfqHeaderInfoList != null && displaySolicitationLink.equals("Y") && (fpeUser || buyerUser) )
			{
				for (int ix = 0; ix < rfqHeaderInfoList.size(); ix++)
				{
					ReferenceInfo rfqInfo = (ReferenceInfo) rfqHeaderInfoList.get(ix);
					String rfqAmendment = rfqInfo.getRfqAmendment();
%>
						<tr <%=HtmlWriter.isVisible(oid, "req-rfqNumber")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-rfqNumber" defaultString="Solicitation #" />:</b></td>
							<td nowrap valign=top>
								<a href="javascript: rfqPreview('<%=rfqInfo.getIcHeader()%>'); void(0);" title="View Solicitation"><%=rfqInfo.getFormNumber()%></a>
<%				if (!HiltonUtility.isEmpty(rfqAmendment) && !rfqAmendment.equals("0")) { %>
								&nbsp;/&nbsp;<%=rfqAmendment%>
<%				} %>
							</td>
						</tr>
<%			}
			}

			List poHeaderInfoList = (List) requisitionHeader.getPoInfoList();
			if (poHeaderInfoList != null)
			{
				for (int ix = 0; ix < poHeaderInfoList.size(); ix++)
				{
					ReferenceInfo poInfo = (ReferenceInfo) poHeaderInfoList.get(ix);
					BigDecimal releaseNumber = poInfo.getReleaseNumber();
%>
						<tr <%=HtmlWriter.isVisible(oid, "req-poNumber")%>>
							<% PoHeader poHeader = PoManager.getInstance().getPoHeader(oidR, poInfo.getIcHeader().toString()); %>
							<% if (poHeader.getPoType().equalsIgnoreCase("CT")) { %>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-poContractNumber" defaultString="Contract Order #" />:</b></td>
							<% } else { %>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-poNumber" defaultString="Order #" />:</b></td>
							<% } %>
							<td valign=top>
								<a href="javascript: orderPreview('<%=poInfo.getIcHeader()%>'); void(0);" title="View Order"><%=poInfo.getFormNumber()%></a>
<%				if (releaseNumber.compareTo(new BigDecimal(0)) > 0) { %>
								&nbsp;/&nbsp;<%=releaseNumber%>
<%				} %>
<%				if (!(poHeader.getStatus().equals("9020") && oid.equalsIgnoreCase("DTN07P"))) { %>
							<BR><%=DocumentStatus.toString(poHeader.getPyStatus(), oid)%>
<%				} %>
							</td>
						</tr>
<%				}
			}
%>
<%	if (propertiesManager.getProperty("REQ OPTIONS", "DISPLAYRFUDFS", "N").equals("Y")) { %>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ01")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ01" defaultString="UDF1" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf1Code()%></td>
						</tr>
						<% if(!(requisitionHeader.getRequisitionType().equals("C") || requisitionHeader.getRequisitionType().equals("E") || requisitionHeader.getRequisitionType().equals("M") || requisitionHeader.getRequisitionType().equals("P") || requisitionHeader.getRequisitionType().equals("S") || requisitionHeader.getRequisitionType().equals("T"))) {%>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ02")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ02" defaultString="UDF2" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf2Code()%></td>
						</tr>
						<%} %>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ03")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ03" defaultString="UDF3" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf3Code()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ04")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ04" defaultString="UDF4" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf4Code()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ05")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ05" defaultString="UDF5" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf5Code()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ06")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ06" defaultString="UDF6" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf6Code()%></td>
						</tr>
						<% if(!(requisitionHeader.getRequisitionType().equals("C") || requisitionHeader.getRequisitionType().equals("E") || requisitionHeader.getRequisitionType().equals("M") || requisitionHeader.getRequisitionType().equals("P") || requisitionHeader.getRequisitionType().equals("S") || requisitionHeader.getRequisitionType().equals("T"))) {%>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ07")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ07" defaultString="UDF7" checkRequired="false" />:</b></td>
							<td valign=top><%=requisitionHeader.getUdf7Code()%></td>
						</tr>
						<%} %>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ08")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ08" defaultString="UDF8" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf8Code()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ09")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ09" defaultString="UDF9" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf9Code()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-RQ10")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ10" defaultString="UDF10" checkRequired="false"/>:</b></td>
							<td valign=top><%=requisitionHeader.getUdf10Code()%></td>
						</tr>
<%	} %>
<%	if (propertiesManager.getProperty("REQ OPTIONS", "DISPLAYRBIDREQCAT", "N").equalsIgnoreCase("Y")) {%>
						<% if(!(requisitionHeader.getRequisitionType().equals("C") || requisitionHeader.getRequisitionType().equals("E") || requisitionHeader.getRequisitionType().equals("M") || requisitionHeader.getRequisitionType().equals("P") || requisitionHeader.getRequisitionType().equals("S") || requisitionHeader.getRequisitionType().equals("T"))) {%>
						<tr>
							<tsa:td align="right" noWrap="nowrap"><b><tsa:label labelName="req-bidWaiver" defaultString="Bid Waiver" checkRequired="false"/>:</b></tsa:td>
							<tsa:td valign="top"><%=requisitionHeader.getBidWaiver()%></tsa:td>
						</tr>
						<%} %>
						<tr>
							<tsa:td align="right" noWrap="nowrap"><b><tsa:label labelName="req-reqCat" defaultString="Request Category" checkRequired="false"/>:</b></tsa:td>
							<tsa:td valign="top"><%=requisitionHeader.getRequestCat()%></tsa:td>
						</tr>
<%	} %>
						<tr <%=HtmlWriter.isVisible(oid, "req-currency")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-currency" defaultString="Currency" />:</b></td>
							<td valign=top><%=requisitionHeader.getCurrencyCode()%></td>
						</tr>
						<% if(!(requisitionHeader.getRequisitionType().equals("C") || requisitionHeader.getRequisitionType().equals("E") || requisitionHeader.getRequisitionType().equals("M") || requisitionHeader.getRequisitionType().equals("P") || requisitionHeader.getRequisitionType().equals("S") || requisitionHeader.getRequisitionType().equals("T"))) {%>						
						<tr <%=HtmlWriter.isVisible(oid, "req-timezone")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-timezone" defaultString="Time Zone" />:</b></td>
							<td valign=top><%=HiltonUtility.getFormattedTimeZone(requisitionHeader.getTimeZone())%></td>
						</tr>
						<%} %>
						<% if((requisitionHeader.getRequisitionType().equals("C") || requisitionHeader.getRequisitionType().equals("E") || requisitionHeader.getRequisitionType().equals("M") || requisitionHeader.getRequisitionType().equals("P") || requisitionHeader.getRequisitionType().equals("S") || requisitionHeader.getRequisitionType().equals("T"))) {%>						
						<tr <%=HtmlWriter.isVisible(oid, "req-work-order")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-workOrder" defaultString="Work Order Number" />:</b></td>
							<td valign=top><%=requisitionHeader.getWorkOrder()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-owner")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-owner" defaultString="FPE Name" checkRequired="false" />:</b></td>
							<td valign=top><%=UserManager.getInstance().getUserDisplayName(oid, requisitionHeader.getOwner())%> </td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-udf7Code")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-udf7Code" defaultString="Functional Class " />:</b></td>
							<td valign=top><%=StdTableManager.getInstance(oid).getStdTableDescription(oid, "RQ07", requisitionHeader.getUdf7Code())%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-itemLocation")%>>
							<td nowrap align=right valign=top><b><tsa:label labelName="req-itemLocation" defaultString="Kit Location" />:</b></td>
							<td valign=top><%=requisitionHeader.getItemLocation()%></td>
						</tr>
						<%} %>
						
					</table>
					</td>
				</tr>
				</table>
			</td>
<%
	String	s_ship_address_line_1 = "";
	String	s_ship_address_line_2 = "";
	String	s_ship_address_line_3 = "";
	String	s_ship_address_line_4 = "";
	String	s_ship_city = "";
	String	s_ship_state = "";
	String	s_ship_postal_code = "";
	String	s_ship_country = "";

	Address shipTo = (Address) requisitionHeader.getShipToAddress();
	if (shipTo != null)
	{
		s_ship_address_line_1 = shipTo.getAddressLine1();
		s_ship_address_line_2 = shipTo.getAddressLine2();
		s_ship_address_line_3 = shipTo.getAddressLine3();
		s_ship_address_line_4 = shipTo.getAddressLine4();
		s_ship_city = shipTo.getCity();
		s_ship_state = shipTo.getState();
		s_ship_postal_code = shipTo.getPostalCode();
		s_ship_country = shipTo.getCountry();
	}


    String FilenameXls = null;

    if(request.getAttribute("FilenameXls")!= null)
    {
    FilenameXls = (String) request.getAttribute("FilenameXls");
    }

	if (!s_req_type.equals("K")&& !s_req_type.equals("O")) {
%>
			<td width=50% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=90% class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;<tsa:label labelName="shippingInformation" defaultString="Shipping Information" /></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "req-shipToCode")%>><td class=browseRow height=12px nowrap><b><%=requisitionHeader.getShipToCode()%></b></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine1")%>><td class=browseRow height=12px nowrap><%=s_ship_address_line_1%></td></tr>
						</table>

						<table id=supplierRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (!HiltonUtility.isEmpty(s_ship_address_line_2))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine2")%>><td class=browseRow height=12px nowrap><%=s_ship_address_line_2%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_ship_address_line_3))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine3")%>><td class=browseRow height=12px nowrap><%=s_ship_address_line_3%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_ship_address_line_4))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine4")%>><td class=browseRow height=12px nowrap><%=s_ship_address_line_4%></td></tr>
<%		}%>
						<tr><td class=browseRow height=12px nowrap><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-shp-country")%>><td class=browseRow height=12px nowrap><%=s_ship_country%></td></tr>
						</table>

<%		if (!HiltonUtility.isEmpty(requisitionHeader.getShipAttn())) { %>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "req-shp-attention")%>><td class=browseRow height=12px nowrap><tsa:label labelName="req-shp-attention" defaultString="Attn" />:&nbsp;<%=requisitionHeader.getShipAttn()%></td></tr>
						</table>
<%		} %>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "req-requiredDate")%>>
							<td colspan=2 class=browseRow height=12px nowrap><tsa:label labelName="req-requiredDate" defaultString="Required Date" checkRequired="false"/>:&nbsp;<%=HiltonUtility.getFormattedDate(requisitionHeader.getRequiredDate(), oid, userDateFormat)%>
								<tsa:hidden name="RequisitionHeader_requiredBy" value="<%=HiltonUtility.getFormattedDate(requisitionHeader.getRequiredDate(), oid, userDateFormat)%>"/>
							</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-priority")%>><td colspan=2 class=browseRow height=12px nowrap><tsa:label labelName="req-priority" defaultString="Priority" checkRequired="false"/>:&nbsp;<%=requisitionHeader.getPriorityCode()%></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-shipVia")%>>
							<td class=browseRow width=10% height=12px nowrap><tsa:label labelName="req-shipVia" defaultString="Ship Via" />:</td>
							<td class=browseRow width=90% height=12px nowrap><%=requisitionHeader.getShippingCode()%></td>
						</tr>
						<% if (!HiltonUtility.isEmpty(shipViaDescription)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "req-shipVia")%>>
							<td class=browseRow width=10% height=12px nowrap>&nbsp;</td>
							<td class=browseRow width=90% height=12px nowrap><%=shipViaDescription%></td>
						</tr>
						<% } %>
						<tsa:tr field="req-routing">
							<tsa:td field="req-routing" colspan="2" height="12px" noWrap="nowrap" >
							<tsa:label labelName="req-routing" defaultString="Routing" checkRequired="false" noinstance="yes"/>:&nbsp;<%=requisitionHeader.getRouting()%>
							</tsa:td>
						</tsa:tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
<%	} %>
			<td rowspan=3 align=right valign=top><%@ include file="/requests/req_sidebar.jsp" %></td>
		</tr>
	<%if (!s_req_type.equals("K")) { %>
	<tr><td colspan="2">
	<table style="margin-left: 87px" border="0">
	  <tr <%=HtmlWriter.isVisible(oid, "req-purpose")%>>
	  	<td valign=top align="right"><b><tsa:label labelName="req-purpose" defaultString="Purpose" checkRequired="false"/>:</b></td>
	    <td> <textarea readonly class="browseRow" style="border: 0" style="overflow:hidden" cols="84" rows="5" >${esapi:encodeForHTML(requisitionHeader.internalComments)}</textarea></td>
	  </tr>
	</table>
	</td></tr>
	<%}%>

<%
	if (processMap.containsKey("HEADER_SUPPLIER"))
	{
		String	s_vend_address_line_1 = "";
		String	s_vend_address_line_2 = "";
		String	s_vend_address_line_3 = "";
		String	s_vend_address_line_4 = "";
		String	s_vend_city = "";
		String	s_vend_state = "";
		String	s_vend_postal_code = "";
		String	s_vend_country = "";
		String	s_vend_name = HiltonUtility.ckNull(requisitionHeader.getVendorName());

		Address vendor = (Address) requisitionHeader.getVendorAddress();
		if (vendor != null)
		{
			s_vend_address_line_1 = vendor.getAddressLine1();
			s_vend_address_line_2 = vendor.getAddressLine2();
			s_vend_address_line_3 = vendor.getAddressLine3();
			s_vend_address_line_4 = vendor.getAddressLine4();
			s_vend_city = vendor.getCity();
			s_vend_state = vendor.getState();
			s_vend_postal_code = vendor.getPostalCode();
			s_vend_country = vendor.getCountry();
		}
%>
		<tr>
			<td width=50% align=center valign=top>
				<table id=supplierTable border=0 cellspacing=0 cellpadding=0 width=90% class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
						<tr>
<%	if (s_req_type.equals("K")) { %>
				<td class=browseHdr height=18px nowrap>&nbsp;<tsa:label labelName="remitAddress" defaultString="Remit To Address" /></td>
<%	} else { %>
							<td class=browseHdr height=18px nowrap>&nbsp;<tsa:label labelName="supplierInformation" defaultString="Supplier Information" /></td>
<%	} %>
						</tr>
						</table>
					</td>
				</tr>

<% if (lines_identical_vendor.equals("Y")){ %>
				<tr>
					<td class=browseRow id=supplierRows>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "req-supplier")%>><td class=browseRow height=12px nowrap><b><%=requisitionHeader.getVendorId()%></b></td></tr>
						<%	if (!s_vend_name.equalsIgnoreCase(s_vend_address_line_1)){ %>
							<tr <%=HtmlWriter.isVisible(oid, "req-supplier")%>><td class=browseRow height=12px nowrap><%=s_vend_name%></td></tr><%} %>
						<tr <%=HtmlWriter.isVisible(oid, "req-sup-addressLine1")%>><td class=browseRow height=12px nowrap><%=s_vend_address_line_1%></td></tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (!HiltonUtility.isEmpty(s_vend_address_line_2))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-sup-addressLine2")%>><td class=browseRow height=12px nowrap><%=s_vend_address_line_2%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_vend_address_line_3))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-sup-addressLine3")%>><td class=browseRow height=12px nowrap><%=s_vend_address_line_3%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_vend_address_line_4))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-sup-addressLine4")%>><td class=browseRow height=12px nowrap><%=s_vend_address_line_4%></td></tr>
<%		}%>
						<tr><td class=browseRow height=12px nowrap><%=s_vend_city%>&nbsp;<%=s_vend_state%>&nbsp;<%=s_vend_postal_code%></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-sup-country")%>><td class=browseRow height=12px nowrap><%=s_vend_country%></td></tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (!HiltonUtility.isEmpty(requisitionHeader.getVendorAttn())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "req-sup-attention")%>><td class=browseRow height=12px nowrap><tsa:label labelName="req-sup-attention" defaultString="Attn" />:&nbsp;<%=requisitionHeader.getVendorAttn()%></td></tr>
<%		} %>
<%		if (!HiltonUtility.isEmpty(requisitionHeader.getContactPhone())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "req-sup-phone")%>><td class=browseRow height=12px nowrap><tsa:label labelName="req-sup-phone" defaultString="Phone" />:&nbsp;<%=requisitionHeader.getContactPhone()%></td></tr>
<%		} %>
<%		if (!HiltonUtility.isEmpty(requisitionHeader.getContactMobilePhone())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "req-sup-mobilePhone")%>><td class=browseRow height=12px nowrap><tsa:label labelName="req-sup-mobilePhone" defaultString="Mobile Phone" />:&nbsp;<%=requisitionHeader.getContactMobilePhone()%></td></tr>
<%		} %>
						</table>
					</td>
				</tr>
	<% } else { %>
				<tr>
					<td class=browseRow><tsa:label labelName="seeSuppliersBelow" defaultString="See Suppliers Listed Below" /></td>
				</tr>
	<% } %>
				</table>
			</td>
<%
	}
	if (processMap.containsKey("HEADER_BILLING"))
	{
		String	s_bill_address_line_1 = "";
		String	s_bill_address_line_2 = "";
		String	s_bill_address_line_3 = "";
		String	s_bill_address_line_4 = "";
		String	s_bill_city = "";
		String	s_bill_state = "";
		String	s_bill_postal_code = "";
		String	s_bill_country = "";

		Address billTo = (Address) requisitionHeader.getBillToAddress();
		if (billTo != null)
		{
			s_bill_address_line_1 = billTo.getAddressLine1();
			s_bill_address_line_2 = billTo.getAddressLine2();
			s_bill_address_line_3 = billTo.getAddressLine3();
			s_bill_address_line_4 = billTo.getAddressLine4();
			s_bill_city = billTo.getCity();
			s_bill_state = billTo.getState();
			s_bill_postal_code = billTo.getPostalCode();
			s_bill_country = billTo.getCountry();
		}
%>
			<td width=50% align=center valign=top>
				<table id=billingTable border=0 cellspacing=0 cellpadding=0 width=90% class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;<tsa:label labelName="billingInformation" defaultString="Billing Information" /></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow id=billingRows>
						<table border=0 cellspacing=0 cellpadding=2 width=210px class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "req-billToCode")%>><td class=browseRow height=12px nowrap><b><%=requisitionHeader.getBillToCode()%></b></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-bil-addressLine1")%>><td class=browseRow height=12px nowrap><%=s_bill_address_line_1%></td></tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (!HiltonUtility.isEmpty(s_bill_address_line_2))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-bil-addressLine2")%>><td class=browseRow height=12px nowrap><%=s_bill_address_line_2%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_bill_address_line_3))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-bil-addressLine3")%>><td class=browseRow height=12px nowrap><%=s_bill_address_line_3%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(s_bill_address_line_4))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-bil-addressLine4")%>><td class=browseRow height=12px nowrap><%=s_bill_address_line_4%></td></tr>
<%		} %>
						<tr><td class=browseRow height=12px nowrap><%=s_bill_city%>&nbsp;<%=s_bill_state%>&nbsp;<%=s_bill_postal_code%></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-bil-country")%>><td class=browseRow height=12px nowrap><%=s_bill_country%></td></tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (!HiltonUtility.isEmpty(requisitionHeader.getBillToContact())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "req-bil-attention")%>><td class=browseRow height=12px nowrap><tsa:label labelName="req-bil-attention" defaultString="Attn" />:&nbsp;<%=requisitionHeader.getBillToContact()%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(requisitionHeader.getTaxCode())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "req-taxCode")%>><td class=browseRow height=12px nowrap><tsa:label labelName="req-taxCode" defaultString="Tax Code" />:&nbsp;<%=requisitionHeader.getTaxCode()%></td></tr>
<%		} %>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
<%	} %>
		</table>
	</td>
</tr>
</table>

<br>

<%	if (processMap.containsKey("SHOPCART") || s_req_type.equals("K") || s_req_type.equals("O")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center width=100%>
		<table id=itemTable border=0 cellspacing=0 cellpadding=0 width=95% class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
						<tr>
							<td width=3% class=browseHdr nowrap></td>
<%		if (s_req_type.equals("K") || s_req_type.equals("O")) { %>
				<td width=80% class=browseHdr nowrap><tsa:label labelName="req-hdg-checkDesc" defaultString="Description" /></td>
				<td width=15% class=browseHdr nowrap>Dollar Value</td>
<%		} else { %>
							<td <%=HtmlWriter.isVisible(oid, "req-itemNumber")%> width=13% class=browseHdr align=left nowrap><tsa:label labelName="req-hdg-itemNumber" defaultString="Item/Part #" /></td>
<%				if ( s_req_type.equals("S") || s_req_type.equals("T") || s_req_type.equals("I") ) { %>
							<td <%=HtmlWriter.isVisible(oid, "req-inventoryLocation")%> width=10% class=browseHdr align=center nowrap><tsa:label labelName="req-hdg-inventoryLocation" defaultString="Location" /></td>
<%			} else {%>
							<td <%=HtmlWriter.isVisible(oid, "req-catalog")%> width=10% class=browseHdr align=center nowrap><tsa:label labelName="req-hdg-catalog" defaultString="Catalog" /></td>
<%			}%>
							<td <%=HtmlWriter.isVisible(oid, "req-commodity")%> width=19% class=browseHdr align=center nowrap><tsa:label labelName="req-hdg-commodity" defaultString="Commodity" checkRequired="false"/></td>
							<td <%=HtmlWriter.isVisible(oid, "req-quantity")%> width=10% class=browseHdr align=right nowrap align=center><tsa:label labelName="req-hdg-quantity" defaultString="Quantity" checkRequired="false"/></td>
							<td <%=HtmlWriter.isVisible(oid, "req-uom")%> width=8% class=browseHdr align=right nowrap><tsa:label labelName="req-hdg-uom" defaultString="UOM" checkRequired="false"/></td>
							<td <%=HtmlWriter.isVisible(oid, "req-unitCost")%> width=13% class=browseHdr align=center nowrap><tsa:label labelName="req-hdg-unitCost" defaultString="Unit Cost" /></td>
						<% if((requisitionHeader.getRequisitionType().equals("C") || requisitionHeader.getRequisitionType().equals("E") || requisitionHeader.getRequisitionType().equals("M") || requisitionHeader.getRequisitionType().equals("P") || requisitionHeader.getRequisitionType().equals("S") || requisitionHeader.getRequisitionType().equals("T"))) {%>
							<td <%=HtmlWriter.isVisible(oid, "req-LN03")%> class=browseHdr align=right nowrap><tsa:label labelName="req-hdg-ln03" defaultString="LN03" checkRequired="false"/></td>
						<%} %>
						<% if((requisitionHeader.getRequisitionType().equals("C") || requisitionHeader.getRequisitionType().equals("E") || requisitionHeader.getRequisitionType().equals("M") || requisitionHeader.getRequisitionType().equals("P") || requisitionHeader.getRequisitionType().equals("S") || requisitionHeader.getRequisitionType().equals("T"))) {%>
							<td <%=HtmlWriter.isVisible(oid, "req-LN02")%> class=browseHdr align=right nowrap><tsa:label labelName="req-hdg-ln02" defaultString="PL" checkRequired="false"/></td>
						<%} %>
							<td <%=HtmlWriter.isVisible(oid, "req-extendedCost")%> width=13% class=browseHdr align=right nowrap align=right><tsa:label labelName="req-hdg-extendedCost" defaultString="Ext Cost" /></td>
							<td <%=HtmlWriter.isVisible(oid, "req-lineStatus")%> width=15% class=browseHdr align=right nowrap><tsa:label labelName="req-hdg-lineStatus" defaultString="Line Status" /></td>
							<td width=3% class=browseHdr>&nbsp;</td>
<%		} %>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
			<tsa:hidden name="RequisitionLine_icReqLine" value=""/>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
<%
			int iRow = 0;
			for (int i = 0; i < i_line_count; i++)
			{
				RequisitionLine reqLine = (RequisitionLine) lineList.get(i);

				BigDecimal b_quantity = HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid);
				BigDecimal b_unit_price = reqLine.getUnitPrice();
				BigDecimal b_um_factor = reqLine.getUmFactor();
				BigDecimal b_extended_cost = b_quantity.multiply(b_unit_price).multiply(b_um_factor);

				List shipToList = (List) reqLine.getShipToList();
				List billToList = (List) reqLine.getBillToList();
				List accountList = (List) reqLine.getAccountList();
				List commentList = (List) reqLine.getDocCommentList();
				AltText altText = reqLine.getAltText();

				if (commentList != null)
				{
					for (int ix = 0; ix < commentList.size(); ix++)
					{
						DocComment docComment = (DocComment) commentList.get(ix);
						DocText docText = docComment.getDocText();

						String s_cmt_title = docComment.getCommentTitle();
						String s_cmt_print = docComment.getCommentPrint();
						String s_cmt_bold = docComment.getCommentBold();
						String s_cmt_place = docComment.getCommentPlace();
						String s_cmt_text = docText.getStdText();

						if (s_cmt_place.equals("A"))
						{
							continue;
						}
						/*if (s_cmt_print.equals("N"))
						{
							continue;
						}*/
						iBeforeItem++;
%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow>
								<b><tsa:label labelName="req-catalogItemComment" defaultString="Comment" />:&nbsp;</b>
								<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
							</td>
						</tr>
<%				}
				} %>
<%		if (s_req_type.equals("K") || s_req_type.equals("O")) { %>
						<tr>
							<td width=5% class=browseRow>&nbsp;</td>
							<td width=75% class=browseRow nowrap><%=reqLine.getDescription()%></td>
							<td width=15% class=browseRow nowrap align="right"><%=HiltonUtility.getCurrency(reqLine.getUnitPrice(), s_curr_code, oid, true)%></td>
							<td width=5% class=browseRow>&nbsp;</td>
						</tr>
<%			if (i == 0 && i_line_count > 1) {	%>
						<tr><td colspan="4" align="center"><hr width="95%"></td></tr>
<%			}	%>

<%		} else { %>
						<tr id=itemRows>
							<td width=3% class=browseRow nowrap align=right><a href="javascript: viewItem(<%=i%>); void(0);" title="Click here to View/Modify Item Details." onMouseOver="highlightRow(<%=i%>);" onMouseOut="removeHighlight(<%=i%>);"><%=i+1%></a></td>
							<td <%=HtmlWriter.isVisible(oid, "req-itemNumber")%> width=13% class=browseRow nowrap>
								<%=reqLine.getItemNumber()%>
								<tsa:hidden id="<%= \"icReqLine_\" + i%>" name="<%=\"icReqLine_\" + i%>" value="<%=reqLine.getIcReqLine()%>"/>
							</td>
<%			if ( s_req_type.equals("S") || s_req_type.equals("T") || s_req_type.equals("I") ) { %>
							<td <%=HtmlWriter.isVisible(oid, "req-inventoryLocation")%> width=10% class=browseRow align=center nowrap><%=reqLine.getItemLocation()%></td>
<%			} else if (s_req_type.equals("P") && propertiesManager.getProperty("REQUISITIONS","INTELLIGENTPURCHASEREQ","N").equalsIgnoreCase("Y") && reqLine.getItemSource().equals("INV")) {%>
							<td <%=HtmlWriter.isVisible(oid, "req-foundininventory")%> width=10% class=browseRow align=center nowrap><b><tsa:label labelName="req-foundininventory" defaultString="Found in Inventory" />&nbsp;</b></td>
<%			} else {%>
							<td <%=HtmlWriter.isVisible(oid, "req-catalog")%> width=10% class=browseRow align=center nowrap><%=reqLine.getCatalogId()%></td>
<%			}%>
							<td <%=HtmlWriter.isVisible(oid, "req-commodity")%> width=19% class=browseRow align=center nowrap><%=reqLine.getCommodityCode()%></td>
							<td <%=HtmlWriter.isVisible(oid, "req-quantity")%> width=8% class=browseRow nowrap align=center><%=HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "req-uom")%> width=8% class=browseRow align=right nowrap><%=reqLine.getUmCode()%></td>
							<td <%=HtmlWriter.isVisible(oid, "req-unitCost")%> width=13% class=browseRow nowrap align=center><%=HiltonUtility.getCurrency(b_unit_price, s_curr_code, oid, false)%></td>
						<% if((requisitionHeader.getRequisitionType().equals("C") || requisitionHeader.getRequisitionType().equals("E") || requisitionHeader.getRequisitionType().equals("M") || requisitionHeader.getRequisitionType().equals("P") || requisitionHeader.getRequisitionType().equals("S") || requisitionHeader.getRequisitionType().equals("T"))) {%>
							<td <%=HtmlWriter.isVisible(oid, "req-LN03")%> width=8% class=browseRow align=center nowrap><%=reqLine.getUdf3Code()%></td>
						<%} %>
						<% if((requisitionHeader.getRequisitionType().equals("C") || requisitionHeader.getRequisitionType().equals("E") || requisitionHeader.getRequisitionType().equals("M") || requisitionHeader.getRequisitionType().equals("P") || requisitionHeader.getRequisitionType().equals("S") || requisitionHeader.getRequisitionType().equals("T"))) {%>
							<td <%=HtmlWriter.isVisible(oid, "req-LN02")%> width=8% class=browseRow align=center nowrap><%=reqLine.getUdf2Code()%></td>
						<%} %>
							<td <%=HtmlWriter.isVisible(oid, "req-extendedCost")%> width=13% class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(b_extended_cost, s_curr_code, oid, false)%></td>
							<td <%=HtmlWriter.isVisible(oid, "req-lineStatus")%> width=15% class=browseRow align=right nowrap><%=DocumentStatus.toString(reqLine.getStatus(), oid)%></td>
							<td width=3% class=browseRow>&nbsp;</td>
							<%if (s_req_type.equals("M")) {%>
							<td width="2%" class="browseRow" nowrap align="right">
            					<a href="javascript: showMsrDetails('<%=reqLine.getIcReqLine() %>','<%=reqLine.getIcReqHeader()%>','<%=reqLine.getIcLineHistory()%>','<%=i%>'); ">
              						<img src="<%=contextPath%>/images/dropdown.gif" border=0 >
           						</a>
              					<div align="right">
									<iframe id="recframe_<%=i%>" name="recframe_<%=i%>" marginheight="0" hspace="0" frameborder="0" scrolling="no" src="" style="position: absolute; left:670px ;border:none; overflow-y:hidden; overflow-x:hidden; display:none;"></iframe>
			  					</div>
			  				</td>
			  				<%} %>
						</tr>
						<tr id=itemRows>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=9 class=browseRow><%=reqLine.getDescription()%></td>
						</tr>
						<% if((requisitionHeader.getRequisitionType().equals("C") || requisitionHeader.getRequisitionType().equals("E") || requisitionHeader.getRequisitionType().equals("M") || requisitionHeader.getRequisitionType().equals("P") || requisitionHeader.getRequisitionType().equals("S") || requisitionHeader.getRequisitionType().equals("T"))) {%>
						<tr id=itemRows>
							<td class=browseRow width=3% nowrap></td>
							<td class=browseRow width=13% nowrap>&nbsp;</td>
							<td class=browseRow width=10% nowrap>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "req-asset")%> width=19% class=browseRow align=center nowrap><tsa:label labelName="req-asset" defaultString="Mark/Tag" />&nbsp;:<%=StdTableManager.getInstance(oid).getStdTableDescription(oid, "ASSET", reqLine.getAsset())%></td>
							<td <%=HtmlWriter.isVisible(oid, "req-mfgName")%> width=19% class=browseRow align=center nowrap><tsa:label labelName="req-mfgName" defaultString="Manufacturer" />&nbsp;:<%=reqLine.getMfgName()%></td>
							<td <%=HtmlWriter.isVisible(oid, "req-modelNumber")%> width=19% class=browseRow align=center nowrap><tsa:label labelName="req-modelNumber" defaultString="Model#" />&nbsp;:<%=reqLine.getModelNumber()%></td>
						</tr>
						<%} %>
<%	if (altText != null && DictionaryManager.isVisible(oid, "req-description-alttext")) {
			DocText docText = altText.getDocText();
			if (docText != null) {%>
						<tr id=itemRows>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=9 class=browseRow><%=docText.getStdText()%></td>
						</tr>
<%		}
		}
		if (!HiltonUtility.isEmpty(reqLine.getVendorId()) && lines_identical_vendor.equals("N")) { %>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=1 class=browseRow nowrap><b><tsa:label labelName="req-supplier" defaultString="Supplier" />:&nbsp;</b></td>
							<td colspan=8 class=browseRow><%=reqLine.getVendorId()%></td>
						</tr>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=1 class=browseRow nowrap><b><tsa:label labelName="req-vendorName" defaultString="Vendor Name" />:&nbsp;</b></td>
							<td colspan=8 class=browseRow><%=reqLine.getVendorName()%></td>
						</tr>
<% } %>
<%		} %>
<%		if (s_assign_by_line.equalsIgnoreCase("Y") && s_req_status.compareTo(DocumentStatus.REQ_APPROVED) >= 0) {%>
						<tr <%=HtmlWriter.isVisible(oid, "req-assignedBuyer")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><b><tsa:label labelName="req-assignedBuyer" defaultString="Assigned To" />:</b>&nbsp;<%=UserManager.getInstance().getUser(oid, reqLine.getAssignedBuyer()).getDisplayName()%></td>
						</tr>
<%		}

			List rfqInfoList = (List) reqLine.getRfqInfoList();
			if (rfqInfoList != null)
			{
				for (int ix = 0; ix < rfqInfoList.size(); ix++)
				{
					ReferenceInfo rfqInfo = (ReferenceInfo) rfqInfoList.get(ix);
					String rfqAmendment = rfqInfo.getRfqAmendment();
%>
						<tr <%=HtmlWriter.isVisible(oid, "req-rfqNumber")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow>
								<b><tsa:label labelName="req-rfqNumber" defaultString="Solicitation #" />:</b>&nbsp;<a href="javascript: rfqPreview('<%=rfqInfo.getIcHeader()%>'); void(0);" title="View Solicitation"><%=rfqInfo.getFormNumber()%></a>
<%				if (!HiltonUtility.isEmpty(rfqAmendment) && !rfqAmendment.equals("0")) { %>
								&nbsp;&nbsp;&nbsp;
								<b><tsa:label labelName="req-rfqAmendment" defaultString="Amendment #" />:</b>&nbsp;<%=rfqAmendment%>
<%				} %>
							</td>
						</tr>
<%			}
		}
		if (reqLine != null) {
			List inspList = reqLine.getInspectionList();
			if (inspList != null && !inspList.isEmpty()) {
			%>
			<tr>
					<tsa:td colspan="7" height="18px">
						<table cellpadding="0" cellspacing="0" border="0"  width="100%">
						<tsa:tr>
				          <tsa:td align="left"><a href="javascript: inspection('<%=iRow%>')" title="Inspection"><img id='inspectionImg<%=iRow%>' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Inspections"></a>
				          	<a href="javascript: inspection('<%=iRow%>')" title="Inspection">&nbsp;Receipt Inspection Details</a></tsa:td>
				        </tsa:tr>
						<td>
						<table cellspacing="0">
						<div id="inspectionDiv" value="<%=iRow%>" style="display: none">
						<ol>
					<%
						for (int ix = 0; ix < inspList.size(); ix++) {
						InspectionHeader insp = (InspectionHeader) inspList.get(ix);
						InspectionStd inspStd = insp.getInspectionStd();
						if(inspStd == null){inspStd = new InspectionStd();}
						%>
						    <% String inspType = insp.getInspectType() ;
						    		String inspDesc = "Receipt Inspection" ;
						    		if (inspType == null) inspType = "RI" ;
						    		if (inspType.equals("FI"))   {
						    			inspDesc = "Field Inspection" ;
						    		} else if (inspType.equals("GI"))   {
						    			inspDesc = "General Inspection" ;
						    		} else if (inspType.equals("CG"))   {
						    			inspDesc = "CGD Inspection" ;
						    		}
						    %>
							<li>
								<a href="javascript: viewInspection('<%=reqLine.getIcReqLine()%>','<%=insp.getComp_id().getIcInspNo()%>',<%=insp.getComp_id().getIcMsrLine() %>); void(0);" title="Click here to View/Modify Inspection Details." ><%=HiltonUtility.ckNull(inspDesc) %></a>&nbsp;&nbsp;&nbsp; <%=HiltonUtility.ckNull(insp.getStandardCode())%>&nbsp;&nbsp;:&nbsp;&nbsp;<%=HiltonUtility.ckNull(inspStd.getDescription()) %>
								<br>
								&nbsp;&nbsp;&nbsp;CGD No : <%=HiltonUtility.ckNull(insp.getCgdNo())%>&nbsp;&nbsp;CGD Rev : <%=HiltonUtility.ckNull(insp.getCgdRev())%>								
							</li>
								<ul>
							<%	if (insp != null)
								{
									List inspLineLi	= (List) insp.getInspectionLineList();
									if (inspLineLi != null) {
										for (int j = 0; j < inspLineLi.size(); j++)
										{
												InspectionLine inspLine = (InspectionLine) inspLineLi.get(j) ;
							%>
												<li>&nbsp;<%=HiltonUtility.ckNull(inspLine.getCritNo())%>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=HiltonUtility.ckNull(inspLine.getInspectCode())%>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=HiltonUtility.ckNull(inspLine.getCritDescription())%></li>
												<br></br>
												<br>
							<%			}
									}
								}
								if (insp.getInspectionLineList() != null && insp.getInspectionLineList().size() > 0) {
								} else {%>
											<li><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noInspectionsItem", "There are no inspection records for this item")%>.</b></li>
											<br></br>
											<br>
								<%}%>
								</ul>
						<% }%>
						</ol>
						</div>
						</table>
						</td>
						</table>
				</tsa:td>
			</tr>
			<% 		}
				}
			iRow++; %>
            <!-- Desde aqui es el add para el Attachment --><%
            List attachmentItemList = (List) reqLine.getDocAttachmentList();
            boolean flagAttachmentItem = false;
            if (attachmentItemList != null)
			{
					for (int ix = 0; ix < attachmentItemList.size(); ix++)
					{
						DocAttachment docAttachmentItem = (DocAttachment) attachmentItemList.get(ix);
						String	filename = docAttachmentItem.getDocFilename();
						String	ext = filename.substring(filename.lastIndexOf(".") + 1);
						%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td class=browseRow align="right">
							<%if(!flagAttachmentItem){%>
								<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-catalogItemAttachment", "Attachments")%>:&nbsp;</b>
								<%flagAttachmentItem=true;
							}%>

<%		if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX")) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>');void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX")) {%>
							<a href="javascript: oopenAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("EML")) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/email.gif" border=0 alt="Open Attached Email"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT") || ext.equalsIgnoreCase("PPTX")) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
						</td>
							<td nowrap>
								<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);" title="Click here to Open the file"><%=docAttachmentItem.getDocTitle()%></a>
							</td>
						</tr>
<%
					}
			}
            %>
<!-- Hasta aqui es el add para el Attachment -->
            <%




			List poInfoList = (List) reqLine.getPoInfoList();
			if (poInfoList != null)
			{
				for (int ix = 0; ix < poInfoList.size(); ix++)
				{
					ReferenceInfo poInfo = (ReferenceInfo) poInfoList.get(ix);
					BigDecimal releaseNumber = poInfo.getReleaseNumber();
					PoHeader poHeader = PoManager.getInstance().getPoHeader(oidR, poInfo.getIcHeader().toString());
%>
						<tr <%=HtmlWriter.isVisible(oid, "req-poNumber")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow>
								<b><tsa:label labelName="req-poNumber" defaultString="Order #" />:</b>&nbsp;<a href="javascript: orderPreview('<%=poInfo.getIcHeader()%>'); void(0);" title="View Order"><%=poInfo.getFormNumber()%></a>
<%				if (releaseNumber.compareTo(new BigDecimal(0)) > 0) { %>
								&nbsp;&nbsp;&nbsp;<b><tsa:label labelName="req-releaseNumber" defaultString="Release #" />:</b>&nbsp;<%=releaseNumber%>
<%				} %>
<%				if (!(poHeader.getStatus().equals("9020") && oid.equalsIgnoreCase("DTN07P"))) { %>
							&nbsp;&nbsp;&nbsp;<b>Invoice Status:</b>&nbsp;<%=DocumentStatus.toString(poHeader.getPyStatus(), oid)%>
<%				} %>
							</td>
						</tr>
<%				}
			}

			if (accountList != null)
				{
					BigDecimal bd_total_perc = new BigDecimal(0.00);
					BigDecimal bd_total_amt = new BigDecimal(0.00);

					for (int ix = 0; ix < accountList.size(); ix++)
					{
						Account account = (Account) accountList.get(ix);

						BigDecimal bd_alloc_perc = account.getAllocPercent();
						BigDecimal bd_alloc_amt = account.getAllocAmount();

						if (bd_alloc_perc == null)	{	bd_alloc_perc = new BigDecimal(0);	}
						if (bd_alloc_amt == null)	{	bd_alloc_amt = new BigDecimal(0);	}

						bd_total_perc = bd_total_perc.add(bd_alloc_perc);
						bd_total_amt = bd_total_amt.add(bd_alloc_amt);

						String	s_account = "";
						String s_fld4 = "";
						String s_fld5 = "";
						String	s_accArray[] = new String[15];

						s_accArray[0] = account.getFld1();
						s_accArray[1] = account.getFld2();
						s_accArray[2] = account.getFld3();
						s_accArray[3] = account.getFld4();
						s_accArray[4] = account.getFld5();
						s_accArray[5] = account.getFld6();
						s_accArray[6] = account.getFld7();
						s_accArray[7] = account.getFld8();
						s_accArray[8] = account.getFld9();
						s_accArray[9] = account.getFld10();
						s_accArray[10] = account.getFld11();
						s_accArray[11] = account.getFld12();
						s_accArray[12] = account.getFld13();
						s_accArray[13] = account.getFld14();
						s_accArray[14] = account.getFld15();

						for (int j = 0; j <15; j++)
						{
							if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
							{
								if (s_account.length() > 0)
								{
									s_account = s_account + s_account_separator + s_accArray[j];
								}
								else
								{
									s_account = s_accArray[j];
								}
							}
						}
						if ( !HiltonUtility.isEmpty(s_accArray[3]))
						{
							s_fld4 = s_accArray[3];
						}
						if ( !HiltonUtility.isEmpty(s_accArray[4]))
						{
							s_fld5 = s_accArray[4];
						}
%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7>
								<table border=0 width=100% cellpadding=2 cellspacing=0>
									<tr>
										<td class=browseRow align=right><b><tsa:label labelName="req-allocationString" defaultString="Account" />:</b></td>
										<td><%=s_account%>&nbsp;&nbsp;&nbsp;<%=HiltonUtility.getCurrency(bd_alloc_amt, s_curr_code, oid)%></td>
									</tr>
<%					if (!HiltonUtility.isEmpty(account.getAllocationDescription())) {	%>
									<tr>
										<td class=browseRow align=right width=75px><b><tsa:label labelName="req-allocationDescription" defaultString="Description" />:</b></td>
										<td class=browseRow><%=account.getAllocationDescription()%></td>
									</tr>
<%					}%>
								</table>
							</td>
						</tr>
<%					if (oid.equalsIgnoreCase("qri06p")) {	%>
						<tr>
							<td colspan="8">
								<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
<%						if ( !HiltonUtility.isEmpty(s_fld4)) {	%>
								<tr>
									<td width="50px">&nbsp;</td>
									<td><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC04", "UDF4")%>:&nbsp;<%=s_fld4%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "04", s_fld4)%></td>
								</tr>
<%						}
							if ( !HiltonUtility.isEmpty(s_fld5)) {	%>
								<tr>
									<td width="50px">&nbsp;</td>
									<td><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC05", "UDF5")%>:&nbsp;<%=s_fld5%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "05", s_fld5)%></td>
								</tr>
<%						}	%>
								</table>
							</td>
						</tr>
<%					}
					}
				}
				if (commentList != null)
				{
					for (int ix = 0; ix < commentList.size(); ix++)
					{
						DocComment docComment = (DocComment) commentList.get(ix);
						DocText docText = docComment.getDocText();

						String s_cmt_title = docComment.getCommentTitle();
						String s_cmt_print = docComment.getCommentPrint();
						String s_cmt_bold = docComment.getCommentBold();
						String s_cmt_place = docComment.getCommentPlace();
						String s_cmt_text = docText.getStdText();

						if (s_cmt_place.equals("B"))
						{
							continue;
						}
						/*if (s_cmt_print.equals("N"))
						{
							continue;
						}*/
						iAfterItem++;
%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow>
								<b><tsa:label labelName="req-catalogItemComment" defaultString="Comment" />:&nbsp;</b>
								<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
							</td>
						</tr>
<%					}
				} %>

<%			if (shipToList != null)
				{
					for (int ix = 0; ix < shipToList.size(); ix++)
					{
						ShipTo lineShipTo = (ShipTo) shipToList.get(ix);
						ShipToPK shipToPK = lineShipTo.getComp_id();
						String s_shp_attn = lineShipTo.getAttention();

						Address shipToAddress = (Address) lineShipTo.getShipToAddress();
						if (shipToAddress != null)
						{
							s_ship_address_line_1 = shipToAddress.getAddressLine1();
							s_ship_address_line_2 = shipToAddress.getAddressLine2();
							s_ship_address_line_3 = shipToAddress.getAddressLine3();
							s_ship_address_line_4 = shipToAddress.getAddressLine4();
							s_ship_city = shipToAddress.getCity();
							s_ship_state = shipToAddress.getState();
							s_ship_postal_code = shipToAddress.getPostalCode();
							s_ship_country = shipToAddress.getCountry();
						}
						else
						{
							s_ship_address_line_1 = "";
							s_ship_address_line_2 = "";
							s_ship_address_line_3 = "";
							s_ship_address_line_4 = "";
							s_ship_city = "";
							s_ship_state = "";
							s_ship_postal_code = "";
							s_ship_country = "";
						}
%>
						<tr><td colspan=8><br></td></tr>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow>Ship <b><%=HiltonUtility.getFormattedQuantity(lineShipTo.getQuantity(), oid)%></b> to:</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-shipToCode")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><b><%=shipToPK.getShipToCode()%></b></td>
						</tr>
<%					if (!HiltonUtility.isEmpty(s_ship_address_line_1))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine1")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_address_line_1%></td>
						</tr>
<%					}
						if (!HiltonUtility.isEmpty(s_ship_address_line_2))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine2")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_address_line_2%></td>
						</tr>
<%					}
						if (!HiltonUtility.isEmpty(s_ship_address_line_3))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine3")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_address_line_3%></td>
						</tr>
<%					}
						if (!HiltonUtility.isEmpty(s_ship_address_line_4))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine4")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_address_line_4%></td>
						</tr>
<%					}%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></td>
						</tr>
<%
						if (!HiltonUtility.isEmpty(s_ship_country))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-shp-country")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_country%></td>
						</tr>
<%					}
						if (!HiltonUtility.isEmpty(s_shp_attn))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-shp-attention")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><tsa:label labelName="req-shp-attention" defaultString="Attn" />: <%=s_shp_attn%></td>
						</tr>
<%					} %>
						<tr <%=HtmlWriter.isVisible(oid, "req-requiredDate")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><tsa:label labelName="req-requiredDate" defaultString="Required By" />: <%=HiltonUtility.getFormattedDate(lineShipTo.getShipDate(), oid, userDateFormat)%></td>
						</tr>
<%				}
				}
				if (billToList != null)
				{
					for (int ix = 0; ix < billToList.size(); ix++)
					{
						BillTo billTo = (BillTo) billToList.get(ix);
						BillToPK billToPK = billTo.getComp_id();
						String s_bill_attn = billTo.getAttention();

						String	s_bill_address_line_1 = "";
						String	s_bill_address_line_2 = "";
						String	s_bill_address_line_3 = "";
						String	s_bill_address_line_4 = "";
						String	s_bill_city = "";
						String	s_bill_state = "";
						String	s_bill_postal_code = "";
						String	s_bill_country = "";

						Address billToAddress = (Address) billTo.getBillToAddress();
						if (billToAddress != null)
						{
							s_bill_address_line_1 = billToAddress.getAddressLine1();
							s_bill_address_line_2 = billToAddress.getAddressLine2();
							s_bill_address_line_3 = billToAddress.getAddressLine3();
							s_bill_city = billToAddress.getCity();
							s_bill_state = billToAddress.getState();
							s_bill_postal_code = billToAddress.getPostalCode();
							s_bill_country = billToAddress.getCountry();
						}
%>
						<tr><td colspan=8><br></td></tr>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow>Bill <b><%=HiltonUtility.getFormattedQuantity(billTo.getQuantity(), oid)%></b> to:</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "req-billToCode")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><b><%=billToPK.getBillToCode()%></b></td>
						</tr>
<%					if (!HiltonUtility.isEmpty(s_bill_address_line_1))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-bil-addressLine1")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_address_line_1%></td>
						</tr>
<%					}
						if (!HiltonUtility.isEmpty(s_bill_address_line_2))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-bil-addressLine2")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_address_line_2%></td>
						</tr>
<%					}
						if (!HiltonUtility.isEmpty(s_bill_address_line_3))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-bil-addressLine3")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_address_line_3%></td>
						</tr>
<%					}
						if (!HiltonUtility.isEmpty(s_bill_address_line_4))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-bil-addressLine4")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_address_line_4%></td>
						</tr>
<%					}%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_city%>&nbsp;<%=s_bill_state%>&nbsp;<%=s_bill_postal_code%></td>
						</tr>
<%
						if (!HiltonUtility.isEmpty(s_bill_country))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-bil-country")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_country%></td>
						</tr>
<%					}
						if (!HiltonUtility.isEmpty(s_bill_attn))
						{ %>
						<tr <%=HtmlWriter.isVisible(oid, "req-bil-attention")%>>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><tsa:label labelName="req-bil-attention" defaultString="Attn" />: <%=s_bill_attn%></td>
						</tr>
<%					}
					}
				}
		if (processMap.containsKey("HEADER_TOTALS")) { %>
						<tr><td colspan=10><hr></td></tr>
<%		}
	} %>
						</table>
					</td>
				</tr>
				</table>

<%			if (processMap.containsKey("HEADER_TOTALS")) { %>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr <%=HtmlWriter.isVisible(oid, "req-subtotal")%>>
					<td width=72% class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-subtotal" defaultString="Subtotal" />:</td>
					<td width=13% class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(requisitionHeader.getSubtotal(), s_curr_code, oid)%></td>
					<td width=15% class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "req-discountAmount")%>>
					<td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-discountAmount" defaultString="Discount" />:</td>
					<td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(requisitionHeader.getDiscountAmount(), s_curr_code, oid)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<TR <%=HtmlWriter.isVisible(oid, "req-taxAmount")%>>
				<%String taxLabel = "Tax Amount";
				if(!HiltonUtility.isQriCanadian(oid, requisitionHeader.getUdf1Code()))
				{
					taxLabel = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-taxAmount", "Tax Amount");
				}
				else
				{
					taxLabel = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-canTaxAmount", "GST");
				}%>
					<td class=browseRow nowrap align=right>&nbsp;<%=taxLabel%>:</td>
					<td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(requisitionHeader.getTaxAmount(), s_curr_code, oid)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<%if(HiltonUtility.isQriCanadian(oid, requisitionHeader.getUdf1Code()))
				{%>
					<TR <%=HtmlWriter.isVisible(oid, "req-useTaxAmount")%>>
						<td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-useTaxAmount" defaultString="Tax" />:</td>
						<td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(requisitionHeader.getUseTaxAmount(), s_curr_code, oid, false)%></td>
						<td class=browseRow nowrap align=right>&nbsp;</td>
					</tr>
				<%} %>
				<tr <%=HtmlWriter.isVisible(oid, "req-shippingCharges")%>>
					<td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-shippingCharges" defaultString="Shipping" />:</td>
					<td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(requisitionHeader.getShippingCharges(), s_curr_code, oid, false)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "req-shippingTaxAmount")%>>
					<td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-shippingTaxAmount" defaultString="Shipping Tax" />:</td>
					<td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(requisitionHeader.getShippingTaxAmt(), s_curr_code, oid, false)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "req-otherCharges")%>>
					<td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-otherCharges" defaultString="Other" />:</td>
					<td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(requisitionHeader.getOtherCharges(), s_curr_code, oid, false)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "req-otherTaxAmount")%>>
					<td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-otherTaxAmount" defaultString="Other Tax" />:</td>
					<td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(requisitionHeader.getOtherTaxAmount(), s_curr_code, oid, false)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "req-total")%>>
					<td class=processOn nowrap align=right><b><tsa:label labelName="req-total" defaultString="Total" />:</b></td>
					<td class=browseRow nowrap align=right><b><%=HiltonUtility.getCurrency(requisitionHeader.getTotal(), s_curr_code, oid)%></b></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<tsa:tr field="req-estCost" >
					<tsa:td cssClass="processOn" field="req-estCost" noWrap="nowrap" align="right"><b><tsa:label labelName="req-estCost" defaultString="Total" checkRequired="false" />:</b></tsa:td>
					<tsa:td cssClass="browseRow" field="req-estCost" noWrap="nowrap" align="right"><b><%=HiltonUtility.getCurrency(requisitionHeader.getEstimatedCost(), s_curr_code, oid)%></b></tsa:td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tsa:tr>
				</table>
<%			} %>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	} %>

<br>

<%	if (processMap.containsKey("HEADER_ACCOUNTS")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center width=100%>
		<table border=0 cellspacing=0 cellpadding=0 width=95% class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=75% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="account" defaultString="Account" /></b></td>
							<td width=25% height=18px class=browseHdr align=right>&nbsp;<b><tsa:label labelName="amountAlloc" defaultString="Amount Alloc" />.</b></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
			List accList = (List) request.getAttribute("accountAllocAmountList");
			BigDecimal bd_total_amt = new BigDecimal(0.00);
			if (accList != null)
			{
				for (int i = 0; i < accList.size(); i++)
				{
					Object obj[] = (Object[]) accList.get(i);
					BigDecimal bd_alloc_amt = (BigDecimal) obj[0];
					String	s_account = "";
					String allocDesc = "";

					if (obj.length > 16)
					{
						allocDesc = (String) obj[16];
					}

					String udf = "";
					String fld4 = "";
					String fld5 = "";
					for (int j = 1; j < 16; j ++)
					{
						udf = (String) obj[j];
						if (!HiltonUtility.isEmpty(udf))
						{
							if (s_account.length() > 0)
							{
								s_account = s_account + s_account_separator + udf;
							}
							else
							{
								s_account = udf;
							}
						}

						if (j == 4 && !HiltonUtility.isEmpty(udf))
						{
							fld4 = udf;
						}
						if (j == 5 && !HiltonUtility.isEmpty(udf))
						{
							fld5 = udf;
						}
					}
					bd_total_amt = bd_total_amt.add(bd_alloc_amt);
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=75% class=browseRow><%=s_account%></td>
					<td width=25% class=browseRow align=right><%=HiltonUtility.getCurrency(bd_alloc_amt, s_curr_code, oid, false)%></td>
				</tr>
<%				if (!HiltonUtility.isEmpty(allocDesc)) {	%>
				<tr>
					<td>
						<table border=0 cellpadding=2 cellspacing=0 width=100%>
						<tr>
							<td class=browseRow align=right width=75px><b><tsa:label labelName="req-allocationDescription" defaultString="Description" />:</b></td>
							<td class=browseRow><%=allocDesc%></td>
						</tr>
						</table>
					</td>
					<td>&nbsp;</td>
				</tr>
<%				}
					if (oid.equalsIgnoreCase("qri06p")) {%>
				<tr>
					<td colspan="2">
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
<%					if ( !HiltonUtility.isEmpty(fld4)) {	%>
						<tr>
							<td width="50px">&nbsp;</td>
							<td><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC04", "UDF4")%>:&nbsp;<%=fld4%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "04", fld4)%></td>
						</tr>
<%					}
						if ( !HiltonUtility.isEmpty(fld5)) {	%>
						<tr>
							<td width="50px">&nbsp;</td>
							<td><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC05", "UDF5")%>:&nbsp;<%=fld5%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "05", fld5)%></td>
						</tr>
<%					}	%>
						</table>
					</td>
				</tr>
<%				}	%>
				</table>
<%			}
				if (accList.size() > 0) {	%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td class=browseRow>&nbsp;</td>
					<td class=browseRow colspan=2 valign=top><hr size=0 align=right colspan=2></td>
				</tr>
				<tr>
					<td width=75% class=browseRow>&nbsp;</td>
					<td width=25% class=browseRow align=right><%=HiltonUtility.getCurrency(bd_total_amt, s_curr_code, oid)%></td>
				</tr>
				</table>
<%			}
			}
			if (accList == null || accList.size() == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow><tsa:label labelName="noAccountsAssociatedRequisition" defaultString="There are no accounts associated with this requisition" />.</td></tr>
				</table>
<%		}	%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	} %>

<br>

<%	if (processMap.containsKey("HEADER_NOTES")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center width=100%>
		<table border=0 cellspacing=0 cellpadding=0 width=95% class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=75% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="comment" defaultString="Comment" /></b></td>
							<td width=8% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="print" defaultString="Print" /></b></td>
							<td width=7% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="bold" defaultString="Bold" /></b></td>
							<td width=10% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="placement" defaultString="Placement" /></b></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%

			int ci = 0;
			if (cmtList != null)
			{
				for(int i = 0; i < cmtList.size(); i++)
				{
					DocComment docComment = (DocComment) cmtList.get(i);
					if (docComment == null) {
						continue;
					}
					DocText docText = docComment.getDocText();
					if (docText == null) {
						docText = new DocText();
					}
					String s_cmt_title = docComment.getCommentTitle();
					String s_cmt_print = docComment.getCommentPrint();
					String s_cmt_bold = docComment.getCommentBold();
					String s_cmt_place = docComment.getCommentPlace();
					String s_cmt_text = docText.getStdText();

					if (s_cmt_place.equals("A"))
					{
						s_cmt_place = "After";
					}
					else if (s_cmt_place.equals("B"))
					{
						s_cmt_place = "Before";
					}
					ci++;
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=75% class=browseRow>
						<%if(s_cmt_bold.equals("Y")){%><b><%}%><%=s_cmt_title%><%if(s_cmt_bold.equals("Y")){%></b><%}%>
					</td>
					<td width=8% class=browseRow align=center valign=top><%=s_cmt_print%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_cmt_bold%></td>
					<td width=10% class=browseRow align=center valign=top><%=s_cmt_place%></td>
				</tr>
				</table>

				<table class=browseRow>
				<tr>
					<td width=50px>&nbsp;</td>
					<td width=100%>
						<%if(s_cmt_bold.equals("Y")){%><b><%}%><%=s_cmt_text%><%if(s_cmt_bold.equals("Y")){%></b><%}%>
					</td>
				</tr>
				</table>
<% 			}
			}
			if (ci == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow><tsa:label labelName="thereAreNoAccountsRequisition" defaultString="There are no comments associated with this requisition" />.</td></tr>
				</table>
<%		}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	}
		if (propertiesManager.isModuleActive("DOCUMENTS")) {%>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center width=100%>
		<table border=0 cellspacing=0 cellpadding=0 width=95% class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=80% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="attachment" defaultString="Attachment" /></b></td>
							<td width=10% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="print" defaultString="Print" /></b></td>
							<td width=10% height=18px class=browseHdr align=center>&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%

			int ai = 0;
			if (attachmentList != null) {
				for(int i = 0; i < attachmentList.size(); i++) {
					DocAttachment docAttachment = (DocAttachment) attachmentList.get(i);
					if (docAttachment == null) {
						continue;
					}
					String	filename = docAttachment.getDocFilename();
					String	ext = filename.substring(filename.lastIndexOf(".") + 1);
					ai++;
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=80% class=browseRow>
						<table border=0 cellpadding=0 cellspacing=0 width=100% class=browseRow>
						<tr>
							<td width=25px align=center valign=middle>
<%		if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT") || ext.equalsIgnoreCase("PPTX")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
							</td>
							<td>
								<a href="javascript: openDocument(<%=i%>); void(0);" title="Click here to open the <%=docAttachment.getDocTitle()%> attachment."><%=docAttachment.getDocTitle()%></a>
								<tsa:hidden name="docFilename" value="<%=filename%>"/>
							</td>
						</tr>
						</table>
					</td>
					<td width=10% class=browseRow align=center valign=top><%=docAttachment.getDocPrint()%></td>
					<td width=10% class=browseRow align=center valign=top></td>
				</tr>
				</table>

	<% 		}
			}
			if (ai == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
                    <tr><td class=browseRow><tsa:label labelName="thereAreNoAttachmentsRequisition" defaultString="There are no attachments associated with this requisition" />.</td></tr>
				</table>
<%		}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	}%>


<!-- ADDED 2007/01/27 -->
<br><br>
<table border="0" cellpadding="0" cellspacing="0" width=<%=formEntryWidth%>>
<tr>
  <td align="center" width="100%">
<%
    List scheduleList = (List) requisitionHeader.getScheduleList();
      int si = 0;
      String typeOfSchedule= "";
      String lastTypeOfSchedule= "";
      if (scheduleList != null) {
        for(int i = 0; i < scheduleList.size(); i++) {
          Schedule schedule = (Schedule) scheduleList.get(i);
          typeOfSchedule = schedule.getComp_id().getScheduleType();

          //if (schedule == null) {
          //  continue;
          //}
          si++;
%>
    <table border="0" cellspacing="0" cellpadding="0" width="95%" class="browseHdr">
<%

          if(!typeOfSchedule.equals(lastTypeOfSchedule)) {
%>
    <tr>
      <td>
        <table border="1" cellspacing="0" cellpadding="0" width="100%" class="browseHdr">
        <tr>
          <td>
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdr">
            <tr>
              <td width="55%" height="18px" class="browseHdr">&nbsp;<b><%=ScheduleType.toString(typeOfSchedule, oid)%></b></td>
              <td width="15%" height="18px" class="browseHdr" align="center">&nbsp;<b><tsa:label labelName="po-scheduleDate" defaultString="Schedule" /></b></td>
              <td <%=HtmlWriter.isVisible(oid, "schedulesRevisedDate")%> width="15%" height="18px" class="browseHdr" align="center">&nbsp;<b><tsa:label labelName="po-revisedDate" defaultString="Revised" /></b></td>
              <td <%=HtmlWriter.isVisible(oid, "schedulesCompletionDate")%> width="15%" height="18px" class="browseHdr" align="center">&nbsp;<b><tsa:label labelName="po-completionDate" defaultString="Completion" /></b></td>
            </tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
<%
          }
%>
    <tr>
      <td>
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tr>
          <td width="55%" class="browseRow"><%=(schedule.getDescription() == null)?"":String.valueOf(schedule.getDescription())%></td>
          <td width="15%" class="browseRow" align="center" valign="top"><%=(schedule.getScheduleDate() == null)?"":formatter.format(schedule.getScheduleDate())%></td>
		  <td <%=HtmlWriter.isVisible(oid, "schedulesRevisedDate")%> width="15%" class="browseRow" align="center" valign="top"><%=(schedule.getRevisedDate() == null)?"":formatter.format(schedule.getRevisedDate())%></td>
          <td <%=HtmlWriter.isVisible(oid, "schedulesCompletionDate")%> width="15%" class="browseRow" align="center" valign="top"><%=(schedule.getCompletionDate() == null)?"":formatter.format(schedule.getCompletionDate())%></td>
        </tr>
        </table>
      </td>
    </tr>

    </table>

<%
		lastTypeOfSchedule = schedule.getComp_id().getScheduleType();
        }
      }
%>
  </td>
</tr>
</table>
<!-- END ADDED 2007/01/27 -->


<br><br>

<tsa:hidden name="icHeader" value="<%=icHeader%>"/>
<tsa:hidden name="FilenameXls" value="<%=FilenameXls%>"/>
<tsa:hidden name="formType" value="<%=formType%>"/>
<tsa:hidden name="buyerId" value="<%=buyerId%>"/>


<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var reqnumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
	var fiscalyear = "<%=s_fiscal_year%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var itemLocation = "<%=requisitionHeader.getItemLocation()%>";
	var clicked = "false";
	var type = "<%=s_req_type%>";
	var todayDate = new Date();
	
	var boxDisplayed = new Array();
	
	if (fiscalyear < todayDate.getYear()) {
		fiscalyear = todayDate.getYear();
	}

	var navMenu = getNavCookie("navArray");
	if (navMenu.indexOf("<%=RequisitionType.toString(s_req_type, oid)%>") < 0)
	{
		currentprocessmethod = currentprocessmethod.replace(";",":");
		setNavCookie(currentpage, currentprocessmethod, "<%=RequisitionType.toString(s_req_type, oid)%>");
		currentprocessmethod = currentprocessmethod.replace(":",";");
	}
	
	function toggleDetailsDisplay(areaName, type) {
		var myAreas = document.getElementsByName(areaName);
		var myArea;
		var myImg = document.getElementById(areaName + "Img");
		var myText = document.getElementById(areaName + "Text");
		var hideArea = false;

		for (var i = 0; i < myAreas.length; i++) {
			myArea = myAreas(i);


			if (myArea.style.display == "none") {
				myArea.style.display = "block";
			}
			else {
				hideArea = true;
				myArea.style.display = "none";
			}
		}

		if (type == undefined) {
			type = "Details";
		}

		if (hideArea) {
			myImg.src = "<%=contextPath%>/images/plus.gif";
			myImg.alt = "View" + type;
			myText.innerText = "View " + type;
		}
		else {
			myImg.src = "<%=contextPath%>/images/minus.gif";
			myImg.alt = "Hide " + type;
			myText.innerText = "Hide " + type;
		}
	}

	function viewItem(row) {
		var num = document.getElementById("icReqLine_" + row);
		frm.RequisitionLine_icReqLine.value = num.value;
		doSubmit('/requests/req_item.jsp','RequisitionLineRetrieve');
	}

	function addItem()
	{
		var line_count = frm.lineCount.value;
		line_count++;
		frm.lineCount.value = line_count;
		if (frm.RequisitionLine_itemNumber.value.length > 0)
		{
			doSubmit('/requests/req_item.jsp','RequisitionLineItemLookup');
		}
		else
		{
			doSubmit('/requests/req_item.jsp','RequisitionLineCreate');
		}
	}

	function reqSaveAs()
	{
		if (autoReqNumber && !showAutoReqNumber)
		{
			if (type == "C"){
				if (confirm("Save this Change Request as a new Change Request?"))
				{
					frm.RequisitionHeader_requisitionNumber.value = "";
					frm.RequisitionHeader_fiscalYear.value = fiscalyear;
					doSubmit(currentpage, "RequisitionSaveas;RequisitionRetrieve");
				}
			}
			else if (confirm("Save this requisition as a new request?"))
			{
				frm.RequisitionHeader_requisitionNumber.value = "";
				frm.RequisitionHeader_fiscalYear.value = fiscalyear;
				doSubmit(currentpage, "RequisitionSaveas;RequisitionRetrieve");
			}
		}
		else
		{
			popupParameters = "formtype=REQ;formnumber=<%=headerEncoder.encodeForJavaScript(s_req_number)%>;fiscalyear=" + fiscalyear + ";action=saveas";
			doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
    	}
	}
	
	function reqClone()
	{
			if (confirm("Save this MSR requisition as a new MSR request?"))
			{
				frm.RequisitionHeader_requisitionNumber.value = "";
				frm.RequisitionHeader_fiscalYear.value = fiscalyear;
				doSubmit(currentpage, "RequisitionClone;RequisitionRetrieve");
			}
		
	}

	function reqSaveAsCheckAccount()
	{
		popupParameters = "formtype=REQ;formnumber=<%=headerEncoder.encodeForJavaScript(s_req_number)%>;fiscalyear=" + fiscalyear + ";action=saveas" + ";Account_icHeader=" + frm.RequisitionHeader_icReqHeader.value;
		doSubmitToPopup('/base/save_as_acc_check.jsp', 'AccountRetrieveByHeader', 'WIDTH=350', 'HEIGHT=165');
	}

	function reqForward()
	{
		var line_count = frm.lineCount.value;

		if (line_count > 0)
		{
			frm.reqaction.value = "FORWARD";
			doSubmit('/requests/req_forward.jsp', 'RequisitionForward');
		}
		else
		{
			alert("You must add items before forwarding a Requisition!");
		}
	}

	function reqPreview()
	{
		popupParameters = "RequisitionHeader_icReqHeader=<%=b_req_ic_header%>";
		doSubmitToPopup('/requests/req_preview.jsp', 'RequisitionRetrieve', 'WIDTH=680px', 'HEIGHT=540px');
	}

	function rejectByBuyer()
	{
		doSubmit('/requests/req_reject_by_buyer.jsp', 'DoNothing');
	}

	function validateReq(action)
	{
		if (action == 'FORWARD')
		{
			hideForwardButton();
		}
		frm.reqaction.value = action;
		var icHeader = "<%=requisitionHeader.getIcReqHeader()%>";
		popupParameters = "RequisitionHeader_icReqHeader=" + icHeader;

		<% if (oid.equalsIgnoreCase("bly07p")){ %>
			doSubmit('/requests/req_validate_no_popup.jsp', 'AccountNoValidDelete;RequisitionValidate');
		<%} else if (oid.equalsIgnoreCase("hoy08p")) {%>
			doSubmit('/requests/req_validate_no_popup.jsp', 'AccountNoValidDelete;RequisitionValidate');
		<%} else {%>
			doSubmit('/requests/req_validate_no_popup.jsp', 'AccountDeleteZeroDollars;RequisitionValidate');
		<% } %>
	}

	function viewHistory()
	{
		popupParameters = "HistoryLog_icHeaderHistory=<%=requisitionHeader.getIcHeaderHistory()%>;formtype=REQ;RequisitionLine_icReqHeader=<%=b_req_ic_header%>;requisitionNumber=<%=headerEncoder.encodeForJavaScript(s_req_number)%>;requisitionType=<%=s_req_type%>";
		doSubmitToPopup('/requests/req_history.jsp', 'HistoryLogRetrieveByHistoryHeader', 'width=675px', 'height=450px');
	}

	function browseSchedules(type, xml)
	{
		popupParameters = "Schedule_documentType=RQH;Schedule_scheduleType=" + type + ";Schedule_icHeader=<%=requisitionHeader.getIcReqHeader()%>";
		doSubmitToPopup('base/schedules.jsp', 'ScheduleRetrieveAllLinesBy', 'WIDTH=700px', 'HEIGHT=300px');
	}
	function oneClick()
	{
			if(clicked == 'false')
			{
				clicked = 'true';
				validateReq('FORWARD');
			}
	}
	
	function showMsrDetails(msrIcReqLine,msrIcReqHeader,msrIcReqLineHistory,lineDisplay)
	{
		
		if(!boxDisplayed[lineDisplay] || boxDisplayed[lineDisplay]=="false")
		{
			popupUrl = '/requests/showMsrDetails.jsp';
			popupHandler = 'MsrLineDetails';
			//popupHandler = 'DoNothing';
			popupUserId = '${esapi:encodeForJavaScript(userId)}';
			popupMailId = '${esapi:encodeForJavaScript(mailId)}';
			popupOrganizationId = '<%= oid %>';
			popupParameters = 'msrIcReqLine=' + msrIcReqLine + ';msrIcReqHeader=' + msrIcReqHeader + ';msrIcReqLineHistory=' + msrIcReqLineHistory + ';lineDisplay=' + lineDisplay;		
			recfrm ='recframe_'+(lineDisplay);
			boxDisplayed[lineDisplay] = "true";
			document.getElementById(recfrm).src = '<%= contextPath %>/system/iframe_html.jsp';
			displayArea(recfrm);	
		}else{
			boxDisplayed[lineDisplay] = "false";
			hideMsrDetails(lineDisplay);
		}
		
	}
	
	function hideMsrDetails(number)
	{
		hideArea('recframe_'+number);
	}

	function highlightRow(row)
	{
		row = row * 2;
		//var myRow = document.all.itemRows.rows(row);
		var myRow = document.all.itemRows(row);
		setRowClassName(myRow, "selectedRow");

		//myRow = document.all.itemRows.rows(row + 1);
		myRow = document.all.itemRows(row + 1);

		setRowClassName(myRow, "selectedRow");
	}

	function removeHighlight(row)
	{
		row = row * 2;
		//var myRow = document.all.itemRows.rows(row);
		var myRow = document.all.itemRows(row);

		setRowClassName(myRow, "browseRow");

		//myRow = document.all.itemRows.rows(row + 1);
		myRow = document.all.itemRows(row + 1);

		setRowClassName(myRow, "browseRow");
	}

	function switchView()
	{
		frm.viewType.value = "CLASSIC";
		doSubmit('/requests/req_summary.jsp', 'RequisitionRetrieve');
	}

	function viewAttachments() {
		doSubmit('/requests/req_attachments.jsp', 'DocAttachmentRetrieveByHeader');
	}

	function openDocument(row)
	{
		var filename = "";
		if (document.all("docFilename").length > 1)
		{
			filename = frm.docFilename[row].value;
		}
		else
		{
			filename = frm.docFilename.value;
		}
		openAttachment(filename);
	}

	function getRoutingList(action)
	{
		frm.reqaction.value = action;
		var icHeader = "<%=requisitionHeader.getIcReqHeader()%>";
		popupParameters = "RequisitionHeader_icReqHeader=" + icHeader;
		popupParameters = popupParameters + ";reqaction=" + action;
		popupParameters = popupParameters + ";RequisitionHeader_status=" + frm.RequisitionHeader_status.value;
		doSubmitToPopup('/requests/req_routinglist.jsp', 'RequisitionDisplayRoutingList', 'width=675px', 'height=450px');
	}

	function printPdf()
	{
		var icHeader = "<%=requisitionHeader.getIcReqHeader()%>";
		popupParameters = "RequisitionHeader_icReqHeader=" + icHeader;
		popupParameters = popupParameters + ";uid=" + '${esapi:encodeForJavaScript(userId)}';
		popupParameters = popupParameters + ";oid=" + '<%=oid%>';

		//doSubmitToPopup('', 'PrintPdf', 'width=675px', 'height=450px');
		doSubmit('/requests/req_print_pdf.jsp', 'DoNothing');
	}

	function viewAppNotes()
	{
		var icHeader = "<%=requisitionHeader.getIcReqHeader()%>";
		popupParameters = "RequisitionHeader_icReqHeader=" + icHeader;
		popupParameters = popupParameters + ";ApprovalLog_icHeader=" + icHeader;
		doSubmitToPopup('/requests/req_approval_notes.jsp', 'ApprovalLogRetrieveByHeader', 'width=675px', 'height=450px');
	}

	function hideForwardButton()
	{
		hideAreaWithBlock('forward_link');
	}

	function showForwardButton()
	{
		displayArea('forward_link');
	}

	function resendReqToApprover() {
		if (verifyAction("Resend this Requisition to the current approver?")) {
			var icHeader = "<%=requisitionHeader.getIcReqHeader()%>";
			popupParameters = "SendQueue_docic=" + icHeader;

			doSubmitToPopup('/requests/req_approver_email_resent.jsp', 'RequisitionResendApproverEmail', 'width=680px', 'height=350px');
		}
		else {
			return;
		}
	}

	function escalateApproval() {
		if (verifyAction("Escalate approval of  this Requisition to the backup approver?")) {
			var icHeader = "<%=requisitionHeader.getIcReqHeader()%>";
			popupParameters = "RequisitionHeader_icReqHeader=" + icHeader;
			popupParameters = popupParameters + ";ApprovalLog_icHeader=" + icHeader;

			doSubmitToPopup('/requests/req_approval_escalated.jsp', 'RequisitionEscalateToBackup', 'width=680px', 'height=350px');
		}
		else {
			return;
		}
	}
	function UploadItems(){

	//doSubmitToPopup('/requests/req_xls_upload_items_new.jsp', 'DoNothing', 'width=680px', 'height=350px');
	doSubmit('/requests/req_xls_upload_items_new.jsp', 'DoNothing');
	}

	function AddItemsXlsFile(){

	//doSubmitToPopup('/requests/req_xls_upload_items_new.jsp', 'DoNothing', 'width=680px', 'height=350px');
	doSubmit('requests/req_review.jsp', 'AddRfqReqPoItems;RequisitionRetrieve');
	}

 	function ReadXlsItems(){
 	doSubmit("/requests/req_items.jsp","RfqReqPoAddItems;RequisitionHeaderUpdate");
 	}

 	function ToReview(){
 	doSubmit("/requests/req_review.jsp", "RequisitionRetrieve");
 	}

	function getBudgetCheck(action, baction, btype, bmake)
	{
		frm.reqaction.value = action;
		frm.budgetAction.value = baction;
		frm.budgetType.value = btype;
		frm.budgetMake.value = bmake;
		frm.budgetYear.value = frm.RequisitionHeader_fiscalYear.value ;

		<%	if (propertiesManager.getProperty("BUDGET", "BUDGETSERVICEACTIVE", "N").equalsIgnoreCase("Y")) { %>
		//   doSubmit('/requests/req_budget_check_no_popup.jsp', 'CheckAccountsForDocumentLine;RequisitionBudgetCheck');
      	  doSubmit('/requests/req_budget_service_check_no_popup.jsp', 'CheckAccountsForDocumentLine;BudgetServiceCheck');
	      	<% } else { %>
		doSubmit('/requests/req_budget_check_no_popup.jsp', 'RequisitionBudgetCheck');
	      	<% } %>
	}
	function getBudgetReview()
	{
		popupParameters = popupParameters + "colname=BudgetAudit_icHeader;operator==;filter_txt=" + frm.RequisitionHeader_icReqHeader.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
		browseLookup('', 'budget_review');
	}
    function RfqReqPoAddItems(){

    	var uploadItemsRole = <%=uploadItemsRole%>
    	//alert(uploadItemsRole);

    	if (uploadItemsRole== "1"){
 			//alert("You Only Can See This Option");
 		}
   	 	if (uploadItemsRole!= "1"){
	  		doSubmit("/requests/req_xls_upload_items_new.jsp","DoNothing");
        }
    }

    function rfqPreview(icRfqHeader)
    {
		popupParameters = "RfqHeader_icRfqHeader=" + icRfqHeader;
		doSubmitToPopup('/rfq/rfq_preview.jsp', 'RfqRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
	}

    function orderPreview(icPoHeader)
    {
		popupParameters = "PoHeader_icPoHeader=" + icPoHeader;
		doSubmitToPopup('/orders/po_preview.jsp', 'PoRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
	}

	function createPurchaseOrderFromReq()
	{
		var msg = 'Create Purchase Order from this Requisition?';
		if (type == "E") {
			msg = 'Create Release Order from this Requisition?';
		} else if (type == "C") {
			msg = 'Create Revision from this Requisition?';
		}

		if (verifyAction(msg))
		{
			if (frm.viewType.value == "WIZARD")
			{
				doSubmit('/orders/po_review.jsp', 'PoCreateFromReq;PoRetrieve');
			}
			else
			{
				doSubmit('/orders/po_summary.jsp', 'PoCreateFromReq;PoRetrieve');
			}
		}
		else
		{
			return false;
		}
	}

	function convertToPurchaseReq()
    {
    	if (confirm("Convert this to a Purchase Requisition?"))
    	{
			doSubmit('/requests/req_review.jsp', 'PricingReqConvertToPurchaseReq;RequisitionRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
		}
	}

	function convertToOtherType()
    {
		popupParameters = "formtype=REQ;reqType=<%=s_req_type%>;formnumber=<%=headerEncoder.encodeForJavaScript(s_req_number)%>;fiscalyear=" + fiscalyear + ";action=saveas";
		doSubmitToPopup('/requests/req_switch_type.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=200');
	}

	function reqGoToApprove()
	{
		if (frm.ApprovalLog_icHeader) {
			frm.ApprovalLog_icHeader.value = frm.RequisitionHeader_icReqHeader.value;
		} else {
			var newInputField = "<input type='hidden' name='ApprovalLog_icHeader' value='" + frm.RequisitionHeader_icReqHeader.value + "'>";
			setHiddenFields(newInputField);
		}

		doSubmit('/requests/req_approval.jsp', 'RequisitionApprovalRetrieve');
	}

	function viewInspection(icReqLine, inspNo, icMsrLine)
	{
		frm.RequisitionLine_icReqLine.value =  icReqLine ;
		frm.InspectionHeader_icInspNo.value = inspNo ;
		frm.InspectionHeader_icMsrLine.value = icMsrLine ;

		doSubmit('/requests/inspection_detail.jsp','InspectionRetrieveDetail');
	}

	function inspection(iRow)
	{
		var myImg = document.getElementById("InspectionImg" + iRow);
		if ($('div[value*="'+ iRow + '"]').is(":hidden")) {
			$('div[value*="' + iRow + '"]').slideDown("slow");
			myImg.src = "<%=contextPath%>/images/arrows_up.gif";
			myImg.alt = "Hide Inspections";
		} else {
			$('div[value*="' + iRow + '"]').hide();
			myImg.src = "<%=contextPath%>/images/arrows_down.gif";
			myImg.alt = "View Inspections";
		}
	}
	
	function sourcing()
	{
		doSubmit('/requests/req_sourcing.jsp', 'RequisitionLineSourcingRetrieveByHeader');
	}

// End Hide script -->
</SCRIPT>