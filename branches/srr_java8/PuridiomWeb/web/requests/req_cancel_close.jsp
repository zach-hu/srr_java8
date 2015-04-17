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
<%@ page import="com.tsa.puridiom.jasperreports.ReportUtils" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.tsa.puridiom.steporder.*" %>
<%
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
	String	s_vendor_id = requisitionHeader.getVendorId();
	String 	s_userRejectAccess = (String)request.getAttribute("userRejectAccess");

	String oidR = (String) request.getAttribute("organizationId");
	String uidR = (String)request.getAttribute("userId");

	String icHeaderEdit = requisitionHeader.getIcReqHeader().toString();
	String icHeaderHistoryEdit = requisitionHeader.getIcHeaderHistory().toString();

	String s_reqaction = (String) request.getAttribute("reqaction");

	if(HiltonUtility.isEmpty(s_userRejectAccess)){	s_userRejectAccess = "false";	}

	if (HiltonUtility.isEmpty(s_req_number))	{	s_req_number = "N/A";	}

	List lineList = (List) requisitionHeader.getRequisitionLineList();
	int	i_line_count = 0;

	if (lineList != null)
	{
		i_line_count = lineList.size();
	}
	int iBeforeItem = 0;
	int iAfterItem = 0;
	//pageContext.setAttribute("oid", oid);
%>
<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/scheduleArrays.js"></script>
<!-- <script language='Javascript1.2' src="<=contextPath%>/menu/reqOptionArrays.js"></script> -->
<%@ include file="/system/header.jsp" %>

<%

String cancelAccess = String.valueOf(uid.equalsIgnoreCase(requisitionHeader.getOwner()) || uid.equalsIgnoreCase(requisitionHeader.getBuyer()));
//String recallAccess = String.valueOf(uid.equalsIgnoreCase(requisitionHeader.getOwner()) || uid.equalsIgnoreCase(requisitionHeader.getBuyer()));
String  closeAccess = String.valueOf(uid.equalsIgnoreCase(requisitionHeader.getOwner()) || uid.equalsIgnoreCase(requisitionHeader.getBuyer()));
PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

UserRole roleR = UserManager.getInstance().getUserRole(oid,uid);

String uploadItemsAccess = propertiesManager.getProperty("UPLOADITEMS","ENABLED","N");
String uploadItemsRole = "";
if (roleR.getAccessRights("UPLOADITEMS")<1) {
	uploadItemsRole = "disabled";
}
else{
	uploadItemsRole = Integer.toString(roleR.getAccessRights("UPLOADITEMS"));
}

%>

<SCRIPT language='Javascript1.2' type="text/javascript">
<!--

	viewType = "<%=s_view%>";
	reqNumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
	reqType = "<%=s_req_type%>";
	reqStatus = "<%=s_req_status%>";
	reqLineCount = <%=i_line_count%>;
	reqInProgress = "<%=DocumentStatus.REQ_INPROGRESS%>";
	reqRejected = "<%=DocumentStatus.REQ_REJECTED%>";
	reqRecalled = "<%=DocumentStatus.REQ_RECALLED%>";
	reqForwarded = "<%=DocumentStatus.REQ_FORWARDED%>";
	reqApproving = "<%=DocumentStatus.REQ_APPROVING%>";
	reqApproved = "<%=DocumentStatus.REQ_APPROVED%>";
	reqAmmended = "<%=DocumentStatus.REQ_AMMENDED%>";
	rfqPriced = "<%=DocumentStatus.RFQ_PRICED%>";
	poAwarded = "<%=DocumentStatus.PO_AWARDED%>";
	invInProgress = "<%=DocumentStatus.INV_INPROGRESS%>";
	invBackordered = "<%=DocumentStatus.INV_BACKORDERED%>";
	cancelStatus = "<%=DocumentStatus.CANCELLED%>";
	cancelAccess = "<%=cancelAccess%>";
	closeAccess = "<%=closeAccess%>";
	userRejectAccess = "<%=s_userRejectAccess%>";
	uploadItemsAccess = "<%=uploadItemsAccess%>";
	uploadItemsRole = "<%=uploadItemsRole%>";

<%
	 //propertiesManager = PropertiesManager.getInstance(oid);
	if (propertiesManager.getProperty("MODULES", "ACCOUNT BUDGET", "N").equalsIgnoreCase("Y")) { %>
		budgetActive = true ;
<% } else { %>
		budgetActive = false ;
<% } %>

//	Array91= createReqOptionsMenu(Array91[0]);

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
	String buyerId = requisitionHeader.getAssignedBuyer();
	String icHeader = b_req_ic_header.toString();

%>

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
<tsa:hidden name="budgetAction" value=""/>
<tsa:hidden name="budgetMake"  value=""/>
<tsa:hidden name="budgetType" value=""/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=RequisitionType.toString(s_req_type, oid)%> <tsa:label labelName="cancellation" defaultString="Cancellation" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b><tsa:label labelName="requisitionNumber" defaultString="Requisition #" />:</b></td>
			<td width=125px><%=headerEncoder.encodeForHTML(s_req_number)%></td>
		</tr>
		<tr>
			<td align=right><b><tsa:label labelName="status" defaultString="Status" />:</b></td>
			<td width=125px><%=DocumentStatus.toString(requisitionHeader.getStatus())%></td>
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
							<td class=browseHdr height=18px nowrap>&nbsp;<tsa:label labelName="general_information" defaultString="General Information" /></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>						
						<tsa:tr field="requestDate">
							<td nowrap align=right valign=top><b><tsa:label labelName="requestDate" defaultString="Request Date" />:</b></td>
							<td nowrap valign=top><%=HiltonUtility.getFormattedDate(requisitionHeader.getRequisitionDate(), oid, userDateFormat)%></td>
						</tsa:tr>						
						<tsa:tr field="req-fiscalYear">
							<td nowrap align=right valign=top><b><tsa:label labelName="req-fiscalYear" defaultString="Fiscal Year" />:</b></td>
							<td nowrap valign=top>
								<%=requisitionHeader.getFiscalYear()%>
								<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=requisitionHeader.getFiscalYear()%>"/>
							</td>
						</tsa:tr>						
						<tsa:tr field="req-requisitionerName">
							<td nowrap align=right valign=top width=40%><b><tsa:label labelName="req-requisitionerName" defaultString="Requisitioner" />:</b></td>
							<td valign=top><%=requisitioner.getDisplayName()%></td>
						</tsa:tr>						
						<tsa:tr field="req-requisitionerPhone">
							<td nowrap align=right valign=top width=40%><b><tsa:label labelName="req-requisitionerPhone" defaultString="Phone Number" />:</b></td>
							<td valign=top><%=requisitioner.getPhoneNumber()%></td>
						</tsa:tr>						
						<tsa:tr field="req-department">
							<td nowrap align=right valign=top width=40%><b><tsa:label labelName="req-department" defaultString="Department" />:</b></td>
							<td nowrap valign=top><%=requisitionHeader.getDepartmentCode()%></td>
						</tsa:tr>
<%	if (!s_req_type.equals("K")&& !s_req_type.equals("O")) {%>						
						<tsa:tr field="req-buyerName">
							<td nowrap align=right valign=top><b><tsa:label labelName="req-buyerName" defaultString="Buyer" />:</b></td>
							<td valign=top><%=buyer.getDisplayName()%></td>
						</tsa:tr>
<%	}
		if (oid.equalsIgnoreCase("bsc04p")) {
		String	s_designatedapprover_code = requisitionHeader.getUdf9Code();
		UserProfile designatedApprover = UserManager.getInstance().getUser(oid, s_designatedapprover_code);
%>						
						<tsa:tr field="req-RQ09">
							<td nowrap align=right valign=top><b><tsa:label labelName="req-RQ09" defaultString="UDF9" />:</b></td>
							<td valign=top><%=designatedApprover.getDisplayName()%></td>
						</tsa:tr>
<%	} %>						
						<tsa:tr field="req-authorizedByName" docType="s_req_type">
							<td nowrap align=right valign=top><b><tsa:label labelName="req-authorizedByName" defaultString="Authorized By" />:</b></td>
							<td valign=top><%=authorizedBy.getDisplayName()%></td>
						</tsa:tr>						
						<tsa:tr field="req-authorizedByPhone" docType="s_req_type">
							<td nowrap align=right valign=top><b><tsa:label labelName="req-authorizedByPhone" defaultString="Authorized By Phone" />:</b></td>
							<td valign=top><%=authorizedBy.getPhoneNumber()%></td>
						</tsa:tr>
<%
	if (s_assign_by_line.equalsIgnoreCase("N") && s_req_status.compareTo(DocumentStatus.REQ_APPROVED) >= 0) {
		String s_assigned_buyer = requisitionHeader.getAssignedBuyer();
		UserProfile assignedBuyer = UserManager.getInstance().getUser(oid, s_assigned_buyer);%>						
						<tsa:tr field="req-assignedBuyer">
							<td nowrap align=right valign=top><b><tsa:label labelName="req-assignedBuyer" defaultString="Assigned To" />:</b></td>
							<td valign=top><%=assignedBuyer.getDisplayName()%></td>
						</tsa:tr>
<%	}

			List rfqHeaderInfoList = (List) requisitionHeader.getRfqInfoList();
			if (rfqHeaderInfoList != null)
			{
				for (int ix = 0; ix < rfqHeaderInfoList.size(); ix++)
				{
					ReferenceInfo rfqInfo = (ReferenceInfo) rfqHeaderInfoList.get(ix);
					String rfqAmendment = rfqInfo.getRfqAmendment();
%>						
						<tsa:tr field="req-rfqNumber">
							<td nowrap align=right valign=top><b><tsa:label labelName="req-rfqNumber" defaultString="Solicitation #" />:</b></td>
							<td nowrap valign=top>
								<a href="javascript: rfqPreview('<%=rfqInfo.getIcHeader()%>'); void(0);" title="View Solicitation"><%=rfqInfo.getFormNumber()%></a>
<%				if (!HiltonUtility.isEmpty(rfqAmendment) && !rfqAmendment.equals("0")) { %>
								&nbsp;/&nbsp;<%=rfqAmendment%>
<%				} %>
							</td>
						</tsa:tr>
<%			}
			}

			List poHeaderInfoList = (List) requisitionHeader.getPoInfoList();
			if (poHeaderInfoList != null)
			{
				for (int ix = 0; ix < poHeaderInfoList.size(); ix++)
				{
					ReferenceInfo poInfo = (ReferenceInfo) poHeaderInfoList.get(ix);
					BigDecimal releaseNumber = poInfo.getReleaseNumber();
%>						<tsa:tr field="req-poNumber">
							<td nowrap align=right valign=top><b><tsa:label labelName="req-poNumber" defaultString="Order #" />:</b></td>
							<td valign=top>
								<a href="javascript: orderPreview('<%=poInfo.getIcHeader()%>'); void(0);" title="View Order"><%=poInfo.getFormNumber()%></a>
<%				if (releaseNumber.compareTo(new BigDecimal(0)) > 0) { %>
								&nbsp;/&nbsp;<%=releaseNumber%>
<%				} %>
							</td>
						</tsa:tr>
<%				}
			}
%>						
						<tsa:tr field="req-currency">
							<td nowrap align=right valign=top><b><tsa:label labelName="req-currency" defaultString="Currency" />:</b></td>
							<td valign=top><%=requisitionHeader.getCurrencyCode()%></td>
						</tsa:tr>
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
							<td class=browseHdr height=18px nowrap>&nbsp;<tsa:label labelName="shipping_information" defaultString="Shipping Information" /></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>						
						<tsa:tr field="req-shipToCode">
						<td class=browseRow height=12px nowrap><b><%=requisitionHeader.getShipToCode()%></b></td>
						</tsa:tr>						
						<tsa:tr field="req-shp-addressLine1">
						<td class=browseRow height=12px nowrap><%=s_ship_address_line_1%></td>
						</tsa:tr>
						</table>

						<table id=supplierRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (!HiltonUtility.isEmpty(s_ship_address_line_2))
			{ %>						
						<tsa:tr field="req-shp-addressLine2">
						<td class=browseRow height=12px nowrap><%=s_ship_address_line_2%></td>
						</tsa:tr>
<%		}
			if (!HiltonUtility.isEmpty(s_ship_address_line_3))
			{ %>						
						<tsa:tr field="req-shp-addressLine3">
						<td class=browseRow height=12px nowrap><%=s_ship_address_line_3%></td>
						</tsa:tr>
<%		}
			if (!HiltonUtility.isEmpty(s_ship_address_line_4))
			{ %>						
						<tsa:tr field="req-shp-addressLine4">
						<td class=browseRow height=12px nowrap><%=s_ship_address_line_4%></td>
						</tsa:tr>
<%		}%>
						<tr><td class=browseRow height=12px nowrap><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></td></tr>						
						<tsa:tr field="req-shp-country">
						<td class=browseRow height=12px nowrap><%=s_ship_country%></td>
						</tsa:tr>
						</table>

<%		if (!HiltonUtility.isEmpty(requisitionHeader.getShipAttn())) { %>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>						
						<tsa:tr field="req-shp-attention">
						<td class=browseRow height=12px nowrap>						
						<tsa:label labelName="req-shp-attention" defaultString="Attn" />:&nbsp;<%=requisitionHeader.getShipAttn()%></td>
						</tsa:tr>
						</table>
<%		} %>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>						
						<tsa:tr field="req-requiredDate">
							<td class=browseRow height=12px nowrap>
							<tsa:label labelName="req-requiredDate" defaultString="Required Date" />:&nbsp;<%=HiltonUtility.getFormattedDate(requisitionHeader.getRequiredDate(), oid, userDateFormat)%>
								<tsa:hidden name="RequisitionHeader_requiredBy" value="<%=HiltonUtility.getFormattedDate(requisitionHeader.getRequiredDate(), oid, userDateFormat)%>"/>
							</td>
						</tsa:tr>						
						<tsa:tr field="req-priority">
						<td class=browseRow height=12px nowrap>
						<tsa:label labelName="req-priority" defaultString="Priority" />:&nbsp;<%=requisitionHeader.getPriorityCode()%></td>
						</tsa:tr>						
						<tsa:tr field="req-shipVia">
						<td class=browseRow height=12px nowrap><tsa:label labelName="req-shipVia" defaultString="Ship Via" />:&nbsp;<%=requisitionHeader.getShippingCode()%></td>
						</tsa:tr>						
						<tsa:tr field="req-routing">
						<td class=browseRow height=12px nowrap><tsa:label labelName="req-routing" defaultString="Routing" />:&nbsp;<%=requisitionHeader.getRouting()%></td>
						</tsa:tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
<%	} %>
			<td rowspan=3 align=right valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;<tsa:label labelName="cancellationReason" defaultString="Cancellation Reason" /></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr><td class=browseRow height=12px><tsa:label labelName="reasonCancellationRequisition" defaultString="Include a reason to make the cancellation of this Requisition" />.</td></tr>
						<tr><td><textarea name="RequisitionHeader_noteCancel" cols="38" rows="12"></textarea></td></tr>
						<% if (s_reqaction.compareTo("cancel") == 0) { %>
						<tr><td align="center"><div id="pxbutton"><a href="javascript: cancelReq(); void(0);">Cancel Request</a></div></td></tr>
						<% } else if (s_reqaction.compareTo("close") == 0) { %>
						<tr><td align="center"><div id="pxbutton"><a href="javascript: closeReq(); void(0);">Close Request</a></div></td></tr>
						<% } %>
						<tr><td align="center"><div id="pxbutton"><a href="javascript: returnReq(); void(0);">Return Request</a></div></td></tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr><td colspan=2>&nbsp;</td></tr>
<%

	HashMap processMap = new HashMap();
	String	s_process_code = "";
	String	s_reqsteps = "";
	ProcessSteps steps = null;

	if (!HiltonUtility.isEmpty(s_req_type)) {
	    if (s_req_subtype.equals("X")) {
	      s_reqsteps = "reqsteps_" + s_req_subtype.toLowerCase();
	    } else {
	      s_reqsteps = "reqsteps_" + s_req_type.toLowerCase();
	    }
	  }

	steps = new ProcessSteps(s_reqsteps, oid);

	for (int ip = 0; ip < steps.getSize(); ip++)
	{
		s_process_code = steps.getProcess(ip);
		processMap.put(s_process_code, "TRUE");
	}

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
							<td class=browseHdr height=18px nowrap>&nbsp;<tsa:label labelName="supplier_information" defaultString="Supplier Information" /></td>
<%	} %>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow id=supplierRows>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>						
						<tsa:tr field="req-supplier">
						<td class=browseRow height=12px nowrap><b><%=requisitionHeader.getVendorId()%></b></td>
						</tsa:tr>						
						<tsa:tr field="req-sup-addressLine1">
						<td class=browseRow height=12px nowrap><%=s_vend_address_line_1%></td>
						</tsa:tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (!HiltonUtility.isEmpty(s_vend_address_line_2))
			{ %>						
						<tsa:tr field="req-sup-addressLine2">
						<td class=browseRow height=12px nowrap><%=s_vend_address_line_2%></td>
						</tsa:tr>
<%		}
			if (!HiltonUtility.isEmpty(s_vend_address_line_3))
			{ %>						
						<tsa:tr field="req-sup-addressLine3">
						<td class=browseRow height=12px nowrap><%=s_vend_address_line_3%></td>
						</tsa:tr>
<%		}
			if (!HiltonUtility.isEmpty(s_vend_address_line_4))
			{ %>						
						<tsa:tr field="req-sup-addressLine4">
						<td class=browseRow height=12px nowrap><%=s_vend_address_line_4%></td>
						</tsa:tr>
<%		}%>
						<tr><td class=browseRow height=12px nowrap><%=s_vend_city%>&nbsp;<%=s_vend_state%>&nbsp;<%=s_vend_postal_code%></td></tr>						
						<tsa:tr field="req-sup-country">
						<td class=browseRow height=12px nowrap><%=s_vend_country%></td>
						</tsa:tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (!HiltonUtility.isEmpty(requisitionHeader.getVendorAttn())) { %>						
						<tsa:tr field="req-sup-attention">
						<td class=browseRow height=12px nowrap>						
						<tsa:label labelName="req-sup-attention" defaultString="Attn" />:&nbsp;<%=requisitionHeader.getVendorAttn()%></td>
						</tsa:tr>
<%		} %>
<%		if (!HiltonUtility.isEmpty(requisitionHeader.getContactPhone())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "req-sup-phone")%>>
						<tsa:tr field="req-sup-phone">
						<td class=browseRow height=12px nowrap>						
						<tsa:label labelName="req-sup-phone" defaultString="Phone" />:&nbsp;<%=requisitionHeader.getContactPhone()%></td>
						</tsa:tr>
<%		} %>
						</table>
					</td>
				</tr>
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
							<td class=browseHdr height=18px nowrap>&nbsp;
							<tsa:label labelName="billing_information" defaultString="Billing Information" /></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow id=billingRows>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>						
						<tsa:tr field="req-billToCode">
						<td class=browseRow height=12px nowrap><b><%=requisitionHeader.getBillToCode()%></b></td>
						</tsa:tr>						
						<tsa:tr field="req-bil-addressLine1">
						<td class=browseRow height=12px nowrap><%=s_bill_address_line_1%></td>
						</tsa:tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (!HiltonUtility.isEmpty(s_bill_address_line_2))
			{ %>						
						<tsa:tr field="req-bil-addressLine2">
						<td class=browseRow height=12px nowrap><%=s_bill_address_line_2%></td>
						</tsa:tr>
<%		}
			if (!HiltonUtility.isEmpty(s_bill_address_line_3))
			{ %>						
						<tsa:tr field="req-bil-addressLine3">
						<td class=browseRow height=12px nowrap><%=s_bill_address_line_3%></td>
						</tsa:tr>
<%		}
			if (!HiltonUtility.isEmpty(s_bill_address_line_4))
			{ %>						
						<tsa:tr field="req-bil-addressLine4">
						<td class=browseRow height=12px nowrap><%=s_bill_address_line_4%></td>
						</tsa:tr>
<%		} %>
						<tr><td class=browseRow height=12px nowrap><%=s_bill_city%>&nbsp;<%=s_bill_state%>&nbsp;<%=s_bill_postal_code%></td></tr>						
						<tsa:tr field="req-bil-country">
						<td class=browseRow height=12px nowrap><%=s_bill_country%></td>
						</tsa:tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (!HiltonUtility.isEmpty(requisitionHeader.getBillToContact())) { %>						
						<tsa:tr field="req-bil-attention">
						<td class=browseRow height=12px nowrap>
						<tsa:label labelName="req-bil-attention" defaultString="Attn" />:&nbsp;<%=requisitionHeader.getBillToContact()%></td>
						</tsa:tr>
<%		}
			if (!HiltonUtility.isEmpty(requisitionHeader.getTaxCode())) { %>						
						<tsa:tr field="req-taxCode">
						<td class=browseRow height=12px nowrap>
						<tsa:label labelName="req-taxCode" defaultString="Tax Code" />:&nbsp;<%=requisitionHeader.getTaxCode()%></td>
						</tsa:tr>
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
	<td align=center width=90%>
		<table id=itemTable border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
						<tr>
							<td width=3% class=browseHdr nowrap>&nbsp;</td>
<%		if (s_req_type.equals("K") || s_req_type.equals("O")) { %>
				<td width=80% class=browseHdr nowrap><tsa:label labelName="req-hdg-checkDesc" defaultString="Description" />
				</td>
				<td width=15% class=browseHdr nowrap>Dollar Value</td>
<%		} else { %>							
							<tsa:td field="req-itemNumber" width="13%" align="center" noWrap="nowrap" cssClass="browseHdr">
							<tsa:label labelName="req-hdg-itemNumber" defaultString="Item/Part #" />
							</tsa:td>
<%				if ( s_req_type.equals("S") || s_req_type.equals("T") || s_req_type.equals("I") ) { %>							
							<tsa:td field="req-inventoryLocation" width="10%" align="center" noWrap="nowrap" cssClass="browseHdr">
							<tsa:label labelName="req-hdg-inventoryLocation" defaultString="Location" />
							</tsa:td>
<%			} else {%>							
							<tsa:td field="req-catalog" width="10%" align="center" noWrap="nowrap" cssClass="browseHdr">
							<tsa:label labelName="req-hdg-catalog" defaultString="Catalog" />
							</tsa:td>
<%			}%>							
							<tsa:td field="req-commodity" width="19%" align="center" noWrap="nowrap" cssClass="browseHdr">
							<tsa:label labelName="req-hdg-commodity" defaultString="Commodity" />
							</tsa:td>							
							<tsa:td field="req-quantity" width="8%" align="center" noWrap="nowrap" cssClass="browseHdr">
							<tsa:label labelName="req-hdg-quantity" defaultString="Quantity" />
							</tsa:td>
							<tsa:td field="req-uom" width="8%" align="center" noWrap="nowrap" cssClass="browseHdr">
							<tsa:label labelName="req-hdg-uom" defaultString="UOM" />
							</tsa:td>							
							<tsa:td field="req-unitCost" width="13%" align="center" noWrap="nowrap" cssClass="browseHdr">
							<tsa:label labelName="req-hdg-unitCost" defaultString="Unit Cost" />
							</tsa:td>							
							<tsa:td field="req-extendedCost" width="13%" align="center" noWrap="nowrap" cssClass="browseHdr">
							<tsa:label labelName="req-hdg-extendedCost" defaultString="Ext Cost" />
							</tsa:td>							
							<tsa:td field="req-lineStatus" width="20%" align="center" noWrap="nowrap" cssClass="browseHdr">
							<tsa:label labelName="req-hdg-lineStatus" defaultString="Line Status" />
							</tsa:td>
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
						if (s_cmt_print.equals("N"))
						{
							continue;
						}
						iBeforeItem++;
%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %></td>
						</tr>
<%				}
				} %>
<%		if (s_req_type.equals("K") || s_req_type.equals("O")) { %>
						<tr>
				<td width=5% class=browseRow>&nbsp;</td>
				<td width=75% class=browseRow nowrap><%=reqLine.getDescription()%></td>
				<td width=15% class=browseRow nowrap align="right"><%=HiltonUtility.getFormattedCurrency(reqLine.getUnitPrice(), s_curr_code, oid, true)%></td>
				<td width=5% class=browseRow>&nbsp;</td>
			</tr>
<%			if (i == 0 && i_line_count > 1) {	%>
			<tr><td colspan="4" align="center"><hr width="<%=formEntryWidth%>"></td></tr>
<%			}	%>

<%		} else { %>
			<tr id=itemRows>
							<td width=3% class=browseRow nowrap align=right><a href="javascript: void(0);" onMouseOver="highlightRow(<%=i%>);" onMouseOut="removeHighlight(<%=i%>);"><%=i+1%></a></td>							
							<tsa:td field="req-itemNumber" width="13%" cssClass="browseRow" noWrap="nowrap" >
								<%=reqLine.getItemNumber()%>
								<tsa:hidden id="icReqLine_<%=i%>" name="icReqLine_<%=i%>" value="<%=reqLine.getIcReqLine()%>"/>
							</tsa:td>
<%			if ( s_req_type.equals("S") || s_req_type.equals("T") || s_req_type.equals("I") ) { %>							
							<tsa:td field="req-inventoryLocation" width="10%" cssClass="browseRow" noWrap="nowrap" align="center">
							<%=reqLine.getItemLocation()%>
							</tsa:td>
<%			} else {%>							
							<tsa:td field="req-catalog" width="10%" cssClass="browseRow" noWrap="nowrap" align="center">
							<%=reqLine.getCatalogId()%>
							</tsa:td>
<%			}%>							
							<tsa:td field="req-commodity" width="19%" cssClass="browseRow" noWrap="nowrap" align="center">
							<%=reqLine.getCommodityCode()%>
							</tsa:td>							
							<tsa:td field="req-quantity" width="8%" cssClass="browseRow" noWrap="nowrap" align="right">
							<%=HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid)%>
							</tsa:td>							
							<tsa:td field="req-uom" width="8%" cssClass="browseRow" noWrap="nowrap" align="center">
							<%=reqLine.getUmCode()%>
							</tsa:td>							
							<tsa:td field="req-unitCost" width="13%" cssClass="browseRow" noWrap="nowrap" align="right">
							<%=HiltonUtility.getFormattedPriceCurrency(b_unit_price, s_curr_code, oid, false)%>
							</tsa:td>							
							<tsa:td field="req-extendedCost" width="13%" cssClass="browseRow" noWrap="nowrap" align="right">
							<%=HiltonUtility.getFormattedCurrency(b_extended_cost, s_curr_code, oid, false)%>
							</tsa:td>							
							<tsa:td field="req-lineStatus" width="20%" cssClass="browseRow" noWrap="nowrap" align="center">
							<%=DocumentStatus.toString(reqLine.getStatus(), oid)%>
							</tsa:td>
							<td width=3% class=browseRow>&nbsp;</td>
						</tr>
						<tr id=itemRows>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=9 class=browseRow><%=reqLine.getDescription()%></td>
						</tr>
<%		} %>
<%		if (s_assign_by_line.equalsIgnoreCase("Y") && s_req_status.compareTo(DocumentStatus.REQ_APPROVED) >= 0) {%>											
						<tsa:tr field="req-assignedBuyer">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><b><tsa:label labelName="req-assignedBuyer" defaultString="Assigned To" />:</b>&nbsp;<%=UserManager.getInstance().getUser(oid, reqLine.getAssignedBuyer()).getDisplayName()%></td>
						</tsa:tr>
<%		}

			List rfqInfoList = (List) reqLine.getRfqInfoList();
			if (rfqInfoList != null)
			{
				for (int ix = 0; ix < rfqInfoList.size(); ix++)
				{
					ReferenceInfo rfqInfo = (ReferenceInfo) rfqInfoList.get(ix);
					String rfqAmendment = rfqInfo.getRfqAmendment();
%>						
						<tsa:tr field="req-rfqNumber">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow>
								<b><tsa:label labelName="req-rfqNumber" defaultString="Solicitation #" />:</b>&nbsp;<a href="javascript: rfqPreview('<%=rfqInfo.getIcHeader()%>'); void(0);" title="View Solicitation"><%=rfqInfo.getFormNumber()%></a>
<%				if (!HiltonUtility.isEmpty(rfqAmendment) && !rfqAmendment.equals("0")) { %>
								&nbsp;&nbsp;&nbsp;
								<b><tsa:label labelName="req-rfqAmendment" defaultString="Amendment #" />:</b>&nbsp;<%=rfqAmendment%>
<%				} %>
							</td>
						</tsa:tr>
<%			}
			}

			List poInfoList = (List) reqLine.getPoInfoList();
			if (poInfoList != null)
			{
				for (int ix = 0; ix < poInfoList.size(); ix++)
				{
					ReferenceInfo poInfo = (ReferenceInfo) poInfoList.get(ix);
					BigDecimal releaseNumber = poInfo.getReleaseNumber();
%>						
						<tsa:tr field="req-poNumber">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow>
								<b><tsa:label labelName="req-poNumber" defaultString="Order #" />:</b>&nbsp;<a href="javascript: orderPreview('<%=poInfo.getIcHeader()%>'); void(0);" title="View Order"><%=poInfo.getFormNumber()%></a>
<%				if (releaseNumber.compareTo(new BigDecimal(0)) > 0) { %>
								&nbsp;&nbsp;&nbsp;<b><tsa:label labelName="req-releaseNumber" defaultString="Release #" />:</b>&nbsp;<%=releaseNumber%>
<%				} %>
							</td>
						</tsa:tr>
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
							<td colspan=7 class=browseRow><b>Account:</b>&nbsp;<%=s_account%>&nbsp;&nbsp;&nbsp;<%=HiltonUtility.getFormattedCurrency(bd_alloc_amt, s_curr_code, oid)%></td>
						</tr>
<%					if (oid.equalsIgnoreCase("qri06p")) {	%>
						<tr>
							<td colspan="8">
								<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
<%						if ( !HiltonUtility.isEmpty(s_fld4)) {	%>
								<tr>
									<td width="50px">&nbsp;</td>
									<td><b><tsa:label labelName="AC04" defaultString="UDF4" />:&nbsp;<%=s_fld4%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "04", s_fld4)%></td>
								</tr>
<%						}
							if ( !HiltonUtility.isEmpty(s_fld5)) {	%>
								<tr>
									<td width="50px">&nbsp;</td>
									<td><b><tsa:label labelName="AC05" defaultString="UDF5" />:&nbsp;<%=s_fld5%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "05", s_fld5)%></td>
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
						if (s_cmt_print.equals("N"))
						{
							continue;
						}
						iAfterItem++;
%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %></td>
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

						/*
						String	s_ship_address_line_1 = "";
						String	s_ship_address_line_2 = "";
						String	s_ship_address_line_3 = "";
						String	s_ship_address_line_4 = "";
						String	s_ship_city = "";
						String	s_ship_state = "";
						String	s_ship_postal_code = "";
						String	s_ship_country = "";

						*/
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
%>
						<tr><td colspan=8><br></td></tr>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow>Ship <b><%=HiltonUtility.getFormattedQuantity(lineShipTo.getQuantity(), oid)%></b> to:</td>
						</tr>						
						<tsa:tr field="req-shipToCode">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><b><%=shipToPK.getShipToCode()%></b></td>
						</tsa:tr>
<%					if (!HiltonUtility.isEmpty(s_ship_address_line_1))
						{ %>						
						<tsa:tr field="req-shp-addressLine1">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_address_line_1%></td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_ship_address_line_2))
						{ %>						
						<tsa:tr field="req-shp-addressLine2">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_address_line_2%></td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_ship_address_line_3))
						{ %>						
						<tsa:tr field="req-shp-addressLine3">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_address_line_3%></td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_ship_address_line_4))
						{ %>						
						<tsa:tr field="req-shp-addressLine4">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_address_line_4%></td>
						</tsa:tr>
<%					}%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></td>
						</tr>
<%
						if (!HiltonUtility.isEmpty(s_ship_country))
						{ %>						
						<tsa:tr field="req-shp-country">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_country%></td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_shp_attn))
						{ %>						
						<tsa:tr field="req-shp-attention">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><tsa:label labelName="req-shp-attention" defaultString="Attn" />: <%=s_shp_attn%></td>
						</tsa:tr>
<%					} %>						
						<tsa:tr field="req-requiredDate">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><tsa:label labelName="req-requiredDate" defaultString="Required By" />: <%=HiltonUtility.getFormattedDate(lineShipTo.getShipDate(), oid, userDateFormat)%></td>
						</tsa:tr>
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
						<tsa:tr field="req-billToCode">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><b><%=billToPK.getBillToCode()%></b></td>
						</tsa:tr>
<%					if (!HiltonUtility.isEmpty(s_bill_address_line_1))
						{ %>					
						<tsa:tr field="req-bil-addressLine1">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_address_line_1%></td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_bill_address_line_2))
						{ %>						
						<tsa:tr field="req-bil-addressLine2">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_address_line_2%></td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_bill_address_line_3))
						{ %>						
						<tsa:tr field="req-bil-addressLine3">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_address_line_3%></td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_bill_address_line_4))
						{ %>						
						<tsa:tr field="req-bil-addressLine4">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_address_line_4%></td>
						</tsa:tr>
<%					}%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_city%>&nbsp;<%=s_bill_state%>&nbsp;<%=s_bill_postal_code%></td>
						</tr>
<%
						if (!HiltonUtility.isEmpty(s_bill_country))
						{ %>						
						<tsa:tr field="req-bil-country">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_bill_country%></td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_bill_attn))
						{ %>						
						<tsa:tr field="req-bil-attention">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><tsa:label labelName="req-bil-attention" defaultString="Attn" />: <%=s_bill_attn%></td>
						</tsa:tr>
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
				<tsa:tr field="req-subtotal">
					<td width=72% class=browseRow nowrap align=right>&nbsp;
					<tsa:label labelName="req-subtotal" defaultString="Subtotal" />:</td>
					<td width=13% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedCurrency(requisitionHeader.getSubtotal(), s_curr_code, oid, false)%></td>
					<td width=15% class=browseRow nowrap align=right>&nbsp;</td>
				</tsa:tr>				
				<tsa:tr field="req-discountAmount">
					<td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-discountAmount" defaultString="Discount" />:</td>
					<td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedCurrency(requisitionHeader.getDiscountAmount(), s_curr_code, oid, false)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tsa:tr>				
				<tsa:tr field="req-taxAmount">
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
					<td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedCurrency(requisitionHeader.getTaxAmount(), s_curr_code, oid, false)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tsa:tr>
				<%if(HiltonUtility.isQriCanadian(oid, requisitionHeader.getUdf1Code()))
				{%>					
					<tsa:tr field="req-useTaxAmount">
						<td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-useTaxAmount" defaultString="Tax" />:</td>
						<td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedCurrency(requisitionHeader.getUseTaxAmount(), s_curr_code, oid, false)%></td>
						<td class=browseRow nowrap align=right>&nbsp;</td>
					</tsa:tr>
				<%} %>				
				<tsa:tr field="req-shippingCharges">
					<td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-shippingCharges" defaultString="Shipping" />:</td>
					<td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedCurrency(requisitionHeader.getShippingCharges(), s_curr_code, oid, false)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tsa:tr>				
				<tsa:tr field="req-shippingTaxAmount">
					<td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-shippingTaxAmount" defaultString="Shipping Tax" />:</td>
					<td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedCurrency(requisitionHeader.getShippingTaxAmt(), s_curr_code, oid, false)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tsa:tr>				
				<tsa:tr field="req-otherCharges">
					<td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-otherCharges" defaultString="Other" />:</td>
					<td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedCurrency(requisitionHeader.getOtherCharges(), s_curr_code, oid, false)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tsa:tr>				
				<tsa:tr field="req-otherTaxAmount">
					<td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-otherTaxAmount" defaultString="Other Tax" />:</td>
					<td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedCurrency(requisitionHeader.getOtherTaxAmount(), s_curr_code, oid, false)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tsa:tr>				
				<tsa:tr field="req-total">
					<td class=processOn nowrap align=right><b><tsa:label labelName="req-total" defaultString="Total" />:</b></td>
					<td class=browseRow nowrap align=right><b><%=HiltonUtility.getFormattedCurrency(requisitionHeader.getTotal(), s_curr_code, oid)%></b></td>
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
		<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=75% height=18px class=browseHdr>&nbsp;<b>
							<tsa:label labelName="account" defaultString="Account" /></b></td>
							<td width=25% height=18px class=browseHdr align=right>&nbsp;<b>
							<tsa:label labelName="amountAllocate" defaultString="Amount Alloc" />.</b></td>
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
					<td width=25% class=browseRow align=right><%=HiltonUtility.getFormattedCurrency(bd_alloc_amt, s_curr_code, oid, false)%></td>
				</tr>
<%				if (oid.equalsIgnoreCase("qri06p")) {%>
				<tr>
					<td colspan="2">
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
<%					if ( !HiltonUtility.isEmpty(fld4)) {	%>
						<tr>
							<td width="50px">&nbsp;</td>
							<td><b>
							<tsa:label labelName="AC04" defaultString="UDF4" />:&nbsp;<%=fld4%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "04", fld4)%></td>
						</tr>
<%					}
						if ( !HiltonUtility.isEmpty(fld5)) {	%>
						<tr>
							<td width="50px">&nbsp;</td>
							<td><b>
							<tsa:label labelName="AC05" defaultString="UDF5" />:&nbsp;<%=fld5%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "05", fld5)%></td>
						</tr>
<%					}	%>
						</table>
					</td>
				</tr>
<%				}	%>
				</table>
                <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td class=browseRow><%			}
				if (accList.size() > 0) {	%></td>
					<td class=browseRow colspan=2 valign=top><hr size=0 align=right colspan=2></td>
				</tr>
				<tr>
					<td width=75% class=browseRow>&nbsp;</td>
					<td width=25% class=browseRow align=right><%=HiltonUtility.getFormattedCurrency(bd_total_amt, s_curr_code, oid)%></td>
				</tr>
				</table>
<%			}
			}
			if (accList == null || accList.size() == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow><tsa:label labelName="thereAreNoAccountsRequisition" defaultString="There are no accounts associated with this requisition" />.</td></tr>
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
	<td align=center width=90%>
		<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
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
					<td width=75% class=browseRow><%=s_cmt_title%></td>
					<td width=8% class=browseRow align=center valign=top><%=s_cmt_print%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_cmt_bold%></td>
					<td width=10% class=browseRow align=center valign=top><%=s_cmt_place%></td>
				</tr>
				</table>

				<table class=browseRow>
				<tr>
					<td width=50px>&nbsp;</td>
					<td width=100%><%=s_cmt_text%>	</td>
				</tr>
				</table>
<% 			}
			}
			if (ci == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow><tsa:label labelName="thereAreNoCommentsRequisition" defaultString="There are no comments associated with this requisition" />.</td></tr>
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
	<td align=center width=90%>
		<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=80% height=18px class=browseHdr>&nbsp;<b>
							<tsa:label labelName="attachment" defaultString="Attachment" /></b></td>
							<td width=10% height=18px class=browseHdr align=center>&nbsp;<b><b>
							<tsa:label labelName="print" defaultString="Print" /></b></b></td>
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
<%		if (ext.equalsIgnoreCase("DOC")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
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
				<tr><td class=browseRow>
				<tsa:label labelName="thereAreNoAttachmentsRequisition" defaultString="There are no attachments associated with this requisition" />.</td></tr>
				</table>
<%		}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	}%>

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

	var navMenu = getNavCookie("navArray");
	if (navMenu.indexOf("<%=RequisitionType.toString(s_req_type, oid)%>") < 0)
	{
		setNavCookie(currentpage, currentprocessmethod, "<%=RequisitionType.toString(s_req_type, oid)%>");
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
		popupParameters = "formtype=REQ;formnumber=<%=headerEncoder.encodeForJavaScript(s_req_number)%>;fiscalyear=" + fiscalyear + ";action=saveas";
		doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
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
		doSubmitToPopup('/requests/req_preview.jsp', 'RequisitionRetrieve', 'WIDTH=<%=formEntryWidth%>', 'HEIGHT=540px');
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
		//popupParameters = popupParameters + ";reqaction=" + action;

		//doSubmitToPopup('/requests/req_validate.jsp', 'RequisitionValidate', 'width=675px', 'height=450px');
		doSubmit('/requests/req_validate_no_popup.jsp', 'RequisitionValidate');
	}

	function viewHistory()
	{
		popupParameters = "HistoryLog_icHeaderHistory=<%=requisitionHeader.getIcHeaderHistory()%>;formtype=REQ;RequisitionLine_icReqHeader=<%=b_req_ic_header%>;requisitionNumber=<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
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
		doSubmitToPopup('/requests/req_routinglist.jsp', 'RequisitionGetRoutingList', 'width=675px', 'height=450px');
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

			doSubmitToPopup('/requests/req_approver_email_resent.jsp', 'RequisitionResendApproverEmail', 'width=<%=formEntryWidth%>', 'height=350px');
		}
		else {
			return;
		}
	}

	function UploadItems(){

	//doSubmitToPopup('/requests/req_xls_upload_items_new.jsp', 'DoNothing', 'width=<%=formEntryWidth%>', 'height=350px');
	doSubmit('/requests/req_xls_upload_items_new.jsp', 'DoNothing');
	}

	function AddItemsXlsFile(){

	//doSubmitToPopup('/requests/req_xls_upload_items_new.jsp', 'DoNothing', 'width=<%=formEntryWidth%>', 'height=350px');
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

		doSubmit('/requests/req_budget_check_no_popup.jsp', 'RequisitionBudgetCheck');
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
		doSubmitToPopup('/rfq/rfq_preview.jsp', 'RfqRetrieve', 'WIDTH=<%=formEntryWidth%>', 'HEIGHT=700px');
	}

    function orderPreview(icPoHeader)
    {
		popupParameters = "PoHeader_icPoHeader=" + icPoHeader;
		doSubmitToPopup('/orders/po_preview.jsp', 'PoRetrieve', 'WIDTH=<%=formEntryWidth%>', 'HEIGHT=700px');
	}

	function createPurchaseOrderFromReq()
	{
		if (verifyAction('Create Purchase Order from this Requisition?'))
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

	function cancelReq() {
		if (frm.RequisitionHeader_noteCancel.value <= 0) {
			alert("Please first enter notes to the requisitioner explaining why you are cancelling this requisition.");
		}
		else {
			frm.reqaction.value = "cancel";
			doSubmit('/requests/req_review.jsp','RequisitionCancelClose;RequisitionRetrieve');
		}
	}

	function closeReq() {
		if (frm.RequisitionHeader_noteCancel.value <= 0) {
			alert("Please first enter notes to the requisitioner explaining why you are closing this requisition.");
		}
		else {
			frm.reqaction.value = "close";
			doSubmit('/requests/req_review.jsp','RequisitionCancelClose;RequisitionRetrieve');
		}
	}

	function returnReq() {
		doSubmit('/requests/req_review.jsp','RequisitionRetrieve');
	}

// End Hide script -->
</SCRIPT>