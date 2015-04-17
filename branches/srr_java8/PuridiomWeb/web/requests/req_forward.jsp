<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<%@ page import="com.tsa.puridiom.menu.MenuManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	List requisitionLineList = (List) request.getAttribute("requisitionLineList");
	UserProfile requisitioner = UserManager.getInstance().getUser(oid, requisitionHeader.getRequisitionerCode());
	BigDecimal b_req_ic_header = requisitionHeader.getIcReqHeader();
	String	s_req_number = requisitionHeader.getRequisitionNumber();
	String	s_req_type = requisitionHeader.getRequisitionType();
	String	currencyCode = requisitionHeader.getCurrencyCode() ;
	String  noApprovalNeed = HiltonUtility.ckNull((String)request.getAttribute("NoApprovalNeed"));
	String	s_req_title = "";
	String	s_fiscal_year = requisitionHeader.getFiscalYear();
	String	s_status_code = requisitionHeader.getStatus();
	String reqAction = HiltonUtility.ckNull((String) request.getAttribute("reqaction"));
	if (s_req_type == null)
	{
		s_req_title = "Requisition ";

	}
	else
	{
		s_req_title = RequisitionType.toString(s_req_type, oid);
	}

	String s_forwardedto = HiltonUtility.ckNull((String) request.getAttribute("forwardedTo"));
	String emailAction = (String)request.getAttribute("emailAction");
	if(emailAction == null){		emailAction = "NONE";		}
	List reqSplitList = (List) request.getAttribute("splitRequisitionList");
	boolean purchaseReq = false;
	boolean changeReqType = HiltonUtility.ckNull((String) request.getAttribute("changeReqType")).equals("Y");
	if (reqSplitList != null && reqSplitList.size() == 1) {
		// must have been created into a purchase req
		purchaseReq = true;
	}
	StringBuffer forwardedToNames = new StringBuffer("");

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

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=requisitionHeader.getStatus()%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<br>

<%	if (s_status_code.equals(DocumentStatus.HISTORY)) {%>
<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td align="center" cssClass="message">
<%	if (purchaseReq) {%>
		<b><tsa:label labelName="requisitionNumber" defaultString="Requisition #"/><%=headerEncoder.encodeForHTML(s_req_number)%><tsa:label labelName="hasBeenSplitPurchaseRequisition" defaultString="has been split into a purchase requisition"/> .</b>
<%	} else if (changeReqType && s_req_type.equals("S")) {%>
		<b><tsa:label labelName="requisitionNumber" defaultString="Requisition #"/><%=headerEncoder.encodeForHTML(s_req_number)%><tsa:label labelName="convertedToSupply" defaultString="has been converted to a Supply Request"/> .</b>
<%	} else {%>
		<b><tsa:label labelName="requisition" defaultString="Requisition"/> #<%=headerEncoder.encodeForHTML(s_req_number)%> <tsa:label labelName="hasBeenSplitMultipleRequisitions" defaultString="has been split into multiple requisitions"/>.</b>
<%	}%>
	</tsa:td>
</tsa:tr>
</table>

<br>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td width="5%">&nbsp;</tsa:td>
	<tsa:td width="90%" align="center" valign="top">
<%
			for (int ih=0; ih < reqSplitList.size(); ih++) {
				RequisitionHeader splitRequisition = (RequisitionHeader) reqSplitList.get(ih);
				String	splitReqType = splitRequisition.getRequisitionType();
				String	buyerCode = splitRequisition.getAssignedBuyer();

				s_forwardedto = splitRequisition.getForwardedTo();
%>
		<table border="1" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
		<tsa:tr>
			<tsa:td height="18px" cssClass="browseHdr">
				<%=RequisitionType.toString(splitReqType, oid)%>&nbsp;#<%=headerEncoder.encodeForHTML(splitRequisition.getRequisitionNumber())%> (<%=DocumentStatus.toString(splitRequisition.getStatus(), oid)%>)
<%	if (splitRequisition.getStatus().equals(DocumentStatus.REQ_APPROVING) && !HiltonUtility.isEmpty(s_forwardedto)) { %>
				<tsa:label labelName="wasForwardedTo" defaultString="was Forwarded to"/> <%=forwardedToNames.toString()%>!
<%	} else if (!HiltonUtility.isEmpty(buyerCode)) {
			if (splitReqType.equalsIgnoreCase("S") || splitReqType.equalsIgnoreCase("T") || splitReqType.equalsIgnoreCase("R") || splitReqType.equalsIgnoreCase("I")) {%>
				<tsa:label labelName="wasForwardedTo" defaultString="was Forwarded to"/> <%=UserManager.getInstance().getUser(oid, buyerCode).getDisplayName(splitReqType)%>!
<%		} else {%>
				<tsa:label labelName="wasAssignedTo" defaultString="was Assigned to"/> <%=UserManager.getInstance().getUser(oid, buyerCode).getDisplayName("")%>!
<%		}
		}%>
			</tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td>
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
				<tsa:tr>
					<tsa:td width="25%" height="18px" noWrap="nowrap"><u><tsa:label labelName="item/part" defaultString="Item/Part"/> #</u></tsa:td>
					<tsa:td width="45%" height="18px" noWrap="nowrap"><u><tsa:label labelName="itemDescription" defaultString="Item Description"/></u></tsa:td>
					<tsa:td width="15%" height="18px" noWrap="nowrap"><u><tsa:label labelName="location" defaultString="Location"/></u></tsa:td>
					<tsa:td width="10%" height="18px" noWrap="nowrap" align="right"><u><tsa:label labelName="quantity" defaultString="Quantity"/></u></tsa:td>
					<tsa:td width="5%" height="18px" noWrap="nowrap"><u><tsa:label labelName="uom" defaultString="UOM"/></u></tsa:td>
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
<%		}%>
				</table>
			</tsa:td>
		</tsa:tr>
		</table>
		<br>
<%	} %>
	</tsa:td>
	<tsa:td width="5%">&nbsp;</tsa:td>
</tsa:tr>
<!--tr><tsa:td colspan="3" align="center"><img class="button" src="<%=contextPath%>/images/button_print.gif" border=0 alt="Print"></tsa:td></tr-->
</table>
<% } else {%>
<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td align="center">
		<%=(oid.equalsIgnoreCase("dtn07p"))?"Requisition":s_req_title %> <span class="hdr12"><%if(emailAction.equalsIgnoreCase("NONE")){ %><a href="javascript: openRequisition(); void(0);">#<%=headerEncoder.encodeForHTML(s_req_number)%></a><%}else{%>#<%=headerEncoder.encodeForHTML(s_req_number)%><%}%></span>
<%		if (reqAction.equals("REJECT")) { %>
		<tsa:label labelName="hasBeenRejected" defaultString="has been rejected"/>!
<%		}
		else {
			if(oid.equalsIgnoreCase("bly07p")){
				s_forwardedto = s_forwardedto.replaceAll(";","");
			}
		%>
		<tsa:label labelName="hasBeenForwarded" defaultString="has been forwarded to"/> <%=forwardedToNames.toString()%>!
		
<%		if (propertiesManager.getProperty("REQ OPTIONS", "PO-NUMBER IN REQUISITION AWARD", "N").equals("Y")
				&& requisitionHeader.getStatus().equals(DocumentStatus.REQ_APPROVED)
				&& requisitionHeader.getRequisitionType().equals("P")
				&& requisitionHeader.getUdf1Code().indexOf("RESALE") > -1) { %>
			<tsa:label labelName=".PO" defaultString=". PO "/> <%=requisitionHeader.getUdf15Code()%> <tsa:label labelName="created" defaultString=" created"/>
<%		} %>
		
<%
				String poFromRelease = (String)request.getAttribute("poFromRelease");
				String poFromRevision = (String)request.getAttribute("poFromRevision");
				if (poFromRelease == null){	poFromRelease = "";}
				if (poFromRevision == null){	poFromRevision = "";}

				if (poFromRevision.equals("Y"))
				{
					PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
					if (poHeader != null) {
						String poNumber = poHeader.getPoNumber();
						String releaseNumber = poHeader.getReleaseNumber().toString();
						String revisionNumber = poHeader.getRevisionNumber().toString();

						if (!HiltonUtility.isEmpty(releaseNumber) && !releaseNumber.equals("0")) {
							poNumber = poNumber + "-" + releaseNumber;
						}
						if (!HiltonUtility.isEmpty(revisionNumber) && !revisionNumber.equals("0")) {
							poNumber = poNumber + "&nbsp;Revision " + revisionNumber;
						}
%>
	                    <br>&nbsp;<br>
						 <tsa:label labelName="itemsHaveBeenPlacedOn" defaultString="Items for this Requisition have been placed on"/> <%=OrderType.toString(poHeader.getPoType(), oid)%>&nbsp;<span class="hdr12"> #<%=poNumber%></span>
						 <br>
<%				}
				}
				else if (poFromRelease.equals("Y"))
				{
					List releases = (List)request.getAttribute("releases");
					for(int relIndex = 0; relIndex < releases.size(); relIndex++)
					{
						PoHeader poHeader = (PoHeader)releases.get(relIndex);
						String poNumber = poHeader.getPoNumber();
						String releaseNumber = poHeader.getReleaseNumber().toString();%>
	                    <br>&nbsp;<br>

						 <tsa:label labelName="itemsHaveBeenPlacedReleaseOrder" defaultString="Items for this Requisition have been placed on Release Order"/>&nbsp;<span class="hdr12"> #<%=poNumber%>-<%=releaseNumber%></span>
						 <br>
					<%}
				}
				else if (!requisitionHeader.getRequisitionType().equals("N")) {%>
				<div id="routingListDisplay" style="visibility:hidden; display:none;">
					<br><tsa:label labelName="belowFinalRoutingListRequisition" defaultString="Below is the final routing list for this requisition"/>.<br>
<%				Map approvalNotes = new HashMap();
					String backgroundClass = "basic";

					if (propertiesManager.getProperty("APPROVALS", "RESORTBY", "").equals("RULE")) {%>
<%@include file="/requests/req_approval_routing_list.jsp" %>
<%				} else { %>
<%@include file="/requests/req_approval_routing_list_amt.jsp" %>
<%				}%>
				</div>
<%			}
			}%>
	</tsa:td>
</tsa:tr>
</table>
<%	}%>
<br>
<br>
<%	if (!noApprovalNeed.equalsIgnoreCase("Y"))
	{ %>
		<table width=680px cellpadding=0 cellspacing=0 border=0>
		<tsa:tr><tsa:td align="center"><%@ include file="/requests/req_waiting_approval_others.jsp" %></tsa:td></tsa:tr>
		</table>
<% } %>

<% if (oid.equalsIgnoreCase("bsc04p") && s_req_type.equalsIgnoreCase("H")){ %>
<table width="340px" cellpadding="0" cellspacing="0" border="0">
	<tsa:tr>
		<tsa:td align="center" noWrap="nowrap">
			<a href="javascript: createRequisitionPrintersAccessories(); void(0);"><tsa:label labelName="printersAccessories" defaultString="Printers & Accessories"/></a>
		</tsa:td>
	</tsa:tr>
</table>
<br>
<% } %>

<%if(emailAction.equalsIgnoreCase("NONE"))
{%>
<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td align="center">
		<table border=0 cellpadding=2 cellspacing=4>
		<tsa:tr>			
	<%	if (reqAction.equalsIgnoreCase("FORWARD")) {
				boolean createOptionDisplayed = false;
				%><tsa:td valign="top"><%
				if (requisitionHeader.getRqSubType().equals("X")) {
					List externalCatalogs = MenuManager.getExternalCatalogs(oid);
					if (externalCatalogs != null && externalCatalogs.size() > 0) {
						if (requisitionLineList != null && requisitionLineList.size() > 0) {
							Map lineItemCatalogs = new HashMap();
							for (int irql = 0; irql < requisitionLineList.size(); irql++) {
								RequisitionLine rql = (RequisitionLine) requisitionLineList.get(irql);
								lineItemCatalogs.put(rql.getCatalogId(), "Y");
							}
							for (int ixc = 0; ixc < externalCatalogs.size(); ixc++) {
								Catalog catalog = (Catalog) externalCatalogs.get(ixc);
								if (!lineItemCatalogs.containsKey(catalog.getCatalogId()) || (catalog.getOrdersOnly().equalsIgnoreCase("Y") && !user.isABuyer())){
									continue;
								}
								createOptionDisplayed = true;
		%>
					<table border=0 cellpadding=2 cellspacing=0 width=100%>
					<tsa:tr><tsa:td align="right" width="50px"><img src="<%=contextPath%>/images/shopping_cart.gif"></tsa:td><tsa:td><a href="javascript: createPunchoutRequest('<%=catalog.getCatalogId()%>'); void(0);"><tsa:label labelName="requestMore" defaultString="Request more"/> <%=catalog.getTitle()%> <tsa:label labelName="items" defaultString="Items"/>.</a></tsa:td></tsa:tr>
					<tsa:tr><tsa:td colspan="2" align="center"><br></tsa:td></tsa:tr>
					</table>
		<%				} //end for (int ixc = 0; ixc < externalCatalogs.size(); ixc++) {
						} // end if (requisitionLineList != null && requisitionLineList.size() > 0) {
					} // end if (externalCatalogs != null && externalCatalogs.size() > 0) {
				} // end if (requisitionHeader.getRqSubType().equals("X")) {
%>
					<table border=0 cellpadding=2 cellspacing=0>
					<tsa:tr><tsa:td align="right" width="50px"><img src="<%=contextPath%>/images/add.gif"></tsa:td><tsa:td noWrap="nowrap"><a href="javascript: createAnotherRequisition(); void(0);"><tsa:label labelName="createAnotherRequisition" defaultString="Create another requisition"/></a></tsa:td></tsa:tr>
					<tsa:tr><tsa:td colspan="2" align="center"><br></tsa:td></tsa:tr>
					<tsa:tr><tsa:td align="right" width="50px"><img src="<%=contextPath%>/images/print.gif"></tsa:td><tsa:td noWrap="nowrap"><a href="javascript: printPdf(); void(0);"><tsa:label labelName="print" defaultString="Print"/> <%=s_req_title%> #<%=headerEncoder.encodeForHTML(s_req_number)%></a></tsa:td></tsa:tr>
					</table>
				</tsa:td>
				<tsa:td align="center" valign="top">
					<table border=0 cellpadding=2 cellspacing=0>
					<tsa:tr><tsa:td align="right" width="50px"><img src="<%=contextPath%>/images/returnto.gif"></tsa:td><tsa:td noWrap="nowrap"><a href="javascript: openRequisition(); void(0);"><tsa:label labelName="returnTo" defaultString="Return to"/> <%=s_req_title%> #<%=headerEncoder.encodeForHTML(s_req_number)%></a></tsa:td></tsa:tr>
					<tsa:tr><tsa:td colspan="2" align="center"><br></tsa:td></tsa:tr>
					<tsa:tr><tsa:td align="right" width="50px"><img src="<%=contextPath%>/images/pwac_sm.gif"></tsa:td><tsa:td noWrap="nowrap"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><tsa:label labelName="returnProcurementWorkloadActivityCenter" defaultString="Return to the Procurement Workload Activity Center"/></a></tsa:td></tsa:tr>
					</table>
				</tsa:td>			
<%		}%>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>
<%}%>
<br>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	hideArea("navTable");
	displayArea("menubarSpacer");
	setRoutingDisplay();

	function approveAnother()
	{
<%	if (s_req_type.equals("K")) {%>
		browse('req-check-approval-list');
<%	} else {%>
		browse('req-approval-list');
<%	}%>
	}

	function createAnotherRequisition() {
		doSubmit('/requests/req_select_type.jsp', 'DoNothing');
	}

	function retrieveReqApproval(ic)
  	{
		frm.RequisitionHeader_icReqHeader.value = ic;
		var newInputField = "<input type='hidden' name='ApprovalLog_icHeader' value='" + ic + "'>";
		newInputField = newInputField + "<input type='hidden' name='DocAttachment_icHeader' value='" + ic + "'>";
		newInputField = newInputField + "<input type='hidden' name='emailAction' value='<%=emailAction%>'>";
		setHiddenFields(newInputField);
		doSubmit('/requests/req_approval.jsp', 'RequisitionApprovalRetrieve');
	}

	function setRoutingDisplay()
	{
		var myTable = document.all("approvers0");
		if (myTable != undefined && myTable.rows != undefined && myTable.rows.length > 0)
		{
			displayArea("routingListDisplay");
		}
	}

	function openRequisition() {
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
		doSubmit('requests/req_summary.jsp', 'RequisitionRetrieve');
<%	} else {%>
		doSubmit('requests/req_review.jsp', 'RequisitionRetrieve');
<%	}%>
	}

	function printPdf() {
		doSubmit('/requests/req_print_pdf.jsp', 'DoNothing');
	}

	function createRequisitionPrintersAccessories()
	{
		var itemType = "CAT";
		var newInputField = "<input type='hidden' name='formtype' value='REQ'>";
		newInputField = newInputField + "<input type='hidden' name='as_item_type' value='CAT'>";
		setHiddenFields(newInputField);

		frm.browseName.value = "catalogitem";
		setAdvancedFilter("CatalogItem_id_catalogId", "=", "PCACCESSORIES", "OR", "Y", "N");

//		setSort();
		doSubmit('browse/item_browse.jsp', 'RequisitionCreate;BrowseRetrieve');
	}

// End Hide script -->
</SCRIPT>