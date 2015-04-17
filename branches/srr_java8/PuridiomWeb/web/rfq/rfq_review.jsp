<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.ReferenceInfo" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.common.documents.StdQuestionRatingMethod" %>
<%@ page import="com.tsa.puridiom.common.documents.StdQuestionResponseType" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>
<%@ page import="com.tsagate.properties.DictionaryManager" %>
<%
	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	int i;
	int b;
	int temp;
	BigDecimal bd_icRfqHeader = rfqHeader.getIcRfqHeader();
	String s_rfqStatus = rfqHeader.getStatus();
	String s_rfqType = rfqHeader.getRfqType();
	String s_rfqNumber = rfqHeader.getRfqNumber();
	String s_rfqAmendment = rfqHeader.getRfqAmendment();
	String s_fiscalYear = rfqHeader.getFiscalYear();
	String	s_currCode = rfqHeader.getCurrencyCode();
	String	errorMsg = (String) request.getAttribute("errorMsg");
	String s_curr_code = rfqHeader.getCurrencyCode();

	List lineList = (List) rfqHeader.getRfqLineList();
	int	i_lineCount = 0;
	if (lineList != null) {
		i_lineCount = lineList.size();
	}
	List vendorList = (List) rfqHeader.getRfqVendorList();
	if (vendorList == null) {
		vendorList = new ArrayList();
	}

	String inspectionDiv = "inspectionDiv" + 0;

	//Webpost Options
	String s_webpost = rfqHeader.getWebpost();
	if (HiltonUtility.isEmpty(s_webpost)) {s_webpost = "N";}
	String s_bidAccess = rfqHeader.getBidAccess();
	if (HiltonUtility.isEmpty(s_bidAccess)) {s_bidAccess = "U";}
	String s_auctionType = rfqHeader.getAuctionType();
	if (HiltonUtility.isEmpty(s_auctionType)) {s_auctionType = "O";}
	String s_bidItemOptions = rfqHeader.getBidItemOptions();
	if (HiltonUtility.isEmpty(s_bidItemOptions)) {s_bidItemOptions = "N";}
	String s_lowBidReq = rfqHeader.getLowestBidReq();
	if (HiltonUtility.isEmpty(s_lowBidReq)) {s_lowBidReq = "N";}
	String s_lowBidSource = rfqHeader.getLowestBidSource();
	if (HiltonUtility.isEmpty(s_lowBidSource)) {s_lowBidSource = "L";}
	String s_lowestDisplay = rfqHeader.getLowestDisplay();
	if (HiltonUtility.isEmpty(s_lowestDisplay)) {s_lowestDisplay = "A";}

%>

<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>
<script language='Javascript1.2' src='<%=contextPath%>/scripts/rfq_review.js' type='text/javascript'></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/scheduleArrays.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/rfqOptionArrays.js"></script>
<%@ include file="/system/header.jsp" %>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String uploadItemsAccess = propertiesManager.getProperty("UPLOADITEMS","ENABLED","N");
	String uploadItemsRole = "";

	if (role.getAccessRights("UPLOADITEMS") < 1) {
		uploadItemsRole = "disabled";
	}
	else{
		uploadItemsRole = Integer.toString(role.getAccessRights("UPLOADITEMS"));
	}

	boolean solicitationActive = propertiesManager.getProperty("RFQ OPTIONS", "SOLICITATIONS", "N").equalsIgnoreCase("Y");
%>

<script language='Javascript1.2' type="text/javascript">
<!--

	viewType = "<%=headerEncoder.encodeForJavaScript(s_view)%>";
	rfqNumber = "<%= headerEncoder.encodeForJavaScript(s_rfqNumber) %>";		
	rfqStatus = "<%=s_rfqStatus%>";
	rfqVendorCount = <%=vendorList.size()%>;
	rfqInProgress = "<%=DocumentStatus.RFQ_INPROGRESS%>";
	rfqOpenSolicitation = "<%=DocumentStatus.RFQ_OPENSOLICITATION%>";
	rfqPurchasing = "<%=DocumentStatus.RFQ_PURCHASING%>";
	eventPaused = <%=extRfqsActive && rfqHeader.getEventPaused().equalsIgnoreCase("Y")%>;
	rfqPosted = <%=(s_webpost.indexOf("D")>= 0 || s_webpost.indexOf("V")>= 0 || s_webpost.indexOf("VB")>= 0 || s_webpost.indexOf("DB")>= 0)%>;
	uploadItemsAccess = "<%=uploadItemsAccess%>";
	uploadItemsRole = "<%=uploadItemsRole%>";
	markBidsReceived = "<%=propertiesManager.getProperty("RFQ OPTIONS","MARKBIDSRECEIVED","Y")%>";
	Array91= createRfqOptionsMenu(Array91[0]);

//-->
</SCRIPT>
<%
	//PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");

	String	s_current_process = "HEADER_REVIEW";
	String	s_current_page = "/rfq/rfq_review.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";
    String  formType = "RFQ";

    String FilenameXls = null;

    if(request.getAttribute("FilenameXls")!= null)
    {
    FilenameXls = (String) request.getAttribute("FilenameXls");
    }

     String icHeader = bd_icRfqHeader.toString();
     String ownerId=rfqHeader.getOwner();
%>

<%@ include file="/rfq/rfq_hidden_fields.jsp" %>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=RfqType.toString(s_rfqType)%> <tsa:label labelName="rfq-Information" defaultString="Information" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/rfq/rfq_status_title.jsp" %>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>



<table border=0 cellpadding=2 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
<%
	if ( !(s_rfqType.equals("PR") && s_webpost.equals("N")) && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENAMENDMENT) == 0) || (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) == 0 && !s_rfqType.equals("PR") && !s_rfqType.equals("PU")) )
		{
			if (i_lineCount > 0 && !s_rfqNumber.equals("N/A"))
			{
	%>
	<!--td align=center id="forward_link"><a href="javascript: rfqForward(); void(0);">Forward</a></td-->
	<%}
		}
		else if (s_rfqType.equals("PR") && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) <= 0)) { %>
	<!--td align=center><a href="javascript: rfqReturn(); void(0);">Return to Requisitioner</a></td-->
<%	}
		if (extRfqsActive && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) >= 0) && rfqHeader.getQaEvent().equalsIgnoreCase("Y"))
		{ %>
			<td align=center><a href="javascript: doSubmit('/rfq/rfq_vendor_questions.jsp', 'VendorQuestionRetrieveByRfq'); void(0);"><tsa:label labelName="rfq-Vendor-Questions" defaultString="Vendor Questions" /></a></td>
		<%	}
		%>
		<td align=right nowrap><a href="javascript: void(0);" onMouseOut="popDown('Menu91')" onMouseOver="popUp('Menu91',event)"><tsa:label labelName="rfq-More-Options" defaultString="More Options" /></a></td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=<%=formEntryWidth%> align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
		<tr>
			<td width=50% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=90% class=browseHdr>
				<tr>
					<td>
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
						<tsa:tr field="rfq-buyerName">
							<td nowrap align=right width=40%><b><tsa:label labelName="rfq-buyerName" defaultString="Buyer" checkRequired="false"/>:</b>&nbsp;</td>
							<td nowrap><%=UserManager.getInstance().getUser(oid, rfqHeader.getBuyer()).getDisplayName()%></td>
						</tsa:tr>
						<tsa:tr field="rfq-solicitationDate">
							<td nowrap align=right><b><tsa:label labelName="rfq-solicitationDate" defaultString="Solicitation Date" checkRequired="false"/>:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getFormattedDate(rfqHeader.getRfqDate(), oid, userDateFormat)%></td>
						</tsa:tr>
						<tsa:tr field="rfq-fiscalYear">
							<td nowrap align=right><b><tsa:label labelName="rfq-fiscalYear" defaultString="Fiscal Year" checkRequired="false"/>:&nbsp;</b></td>
							<td nowrap><%=s_fiscalYear%></td>
						</tsa:tr>
						<tsa:tr field="rfq-dueDate">
							<td nowrap align=right><b><tsa:label labelName="rfq-dueDate" defaultString="Due Date" checkRequired="false"/>:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getFormattedDate(rfqHeader.getDueDate(), oid, userDateFormat)%></td>
						</tsa:tr>
						<tsa:tr field="rfq-currency">
							<td nowrap align=right width=40%><b><tsa:label labelName="rfq-currency" defaultString="Currency" checkRequired="false"/>:&nbsp;</b></td>
							<td nowrap><%=rfqHeader.getCurrencyCode()%></td>
						</tsa:tr>
<%		List requisitionInfoList = (List) rfqHeader.getRequisitionInfoList();
			if (requisitionInfoList != null)
			{
				for (int ix = 0; ix < requisitionInfoList.size(); ix++)
				{
					ReferenceInfo requisitionInfo = (ReferenceInfo) requisitionInfoList.get(ix);
%>
						<tsa:tr field="rfq-requisitionNumber">
							<td nowrap align=right valign=top><b><tsa:label labelName="rfq-requisitionNumber" defaultString="Requisition #" checkRequired="false"/>:</b></td>
							<td nowrap valign=top><a href="javascript: reqPreview('<%=requisitionInfo.getIcHeader()%>'); void(0);" title="View Requisition"><%=requisitionInfo.getFormNumber()%></a></td>
						</tsa:tr>
<%			}
			}

			List poHeaderInfoList = (List) rfqHeader.getPoInfoList();
			if (poHeaderInfoList != null)
			{
				for (int ix = 0; ix < poHeaderInfoList.size(); ix++)
				{
					ReferenceInfo poInfo = (ReferenceInfo) poHeaderInfoList.get(ix);
%>
						<tsa:tr field="rfq-poNumber">
							<td nowrap align=right valign=top><b><tsa:label labelName="rfq-poNumber" defaultString="Order #" checkRequired="false"/>:</b></td>
							<td valign=top>
								<a href="javascript: orderPreview('<%=poInfo.getIcHeader()%>'); void(0);" title="View Order"><%=poInfo.getFormNumber()%></a>
<%				if ((poInfo.getReleaseNumber()).compareTo(new BigDecimal(0)) > 0) { %>
								&nbsp;/&nbsp;<%=poInfo.getReleaseNumber()%>
<%				} %>
						</tsa:tr>
<%				}
			}
%>
						<tsa:tr field="rfq-purpose">
							<td nowrap align=right valign=top><b><tsa:label labelName="rfq-purpose" defaultString="Purpose" checkRequired="false"/>:</b></td>
							<td valign=top><%=HiltonUtility.cutWords(rfqHeader.getRfqDescription(),12,0)%></td>
						</tsa:tr>
						<tsa:tr field="rfq-estCost" >
							<tsa:td cssClass="processOn" field="rfq-estCost" noWrap="nowrap" align="right"><b><tsa:label labelName="rfq-estCost" defaultString="Total" checkRequired="false" />:</b></tsa:td>
							<tsa:td cssClass="browseRow" field="rfq-estCost" noWrap="nowrap"><%=HiltonUtility.getCurrency(rfqHeader.getEstimatedCost(), s_curr_code, oid)%></tsa:td>
							<td class=browseRow nowrap align=right>&nbsp;</td>
						</tsa:tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
<%
	//Shipping Information
	String s_shipToCode = rfqHeader.getShipToCode();
	if (s_shipToCode==null){s_shipToCode = "";}
	Address address = rfqHeader.getShipToAddress();
	if(address==null){
		address = new Address();
	}


	String s_addressLine1 = address.getAddressLine1();
	String s_addressLine2 = address.getAddressLine2();
	String s_addressLine3 = address.getAddressLine3();
	String s_addressLine4 = address.getAddressLine4();
	String s_city = address.getCity();
	String s_state = address.getState();
	String s_postalCode = address.getPostalCode();
	String s_country = address.getCountry();
	String s_attention = rfqHeader.getShipToContact();
	String s_priority = rfqHeader.getPriorityCode();
	String s_shipVia = rfqHeader.getShippingCode();
	String s_routing = rfqHeader.getRouting();
%>
			<td width=50% align=center valign=top rowspan=3>
				<table border=0 cellspacing=0 cellpadding=0 width=90% class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;<tsa:label labelName="shipping_information" defaultString="Shipping Information" checkRequired="false"/></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tsa:tr field="rfq-shipToCode"><td class=browseRow height=12px nowrap><b><%=s_shipToCode%></b></td></tsa:tr>
						<tsa:tr field="rfq-shp-addressLine1"><td class=browseRow height=12px nowrap><%=s_addressLine1%></td></tsa:tr>
						</table>

						<table id=shippingRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (!HiltonUtility.isEmpty(s_addressLine2)) { %>
						<tsa:tr field="rfq-shp-addressLine2"><td class=browseRow height=12px nowrap><%=s_addressLine2%></td></tsa:tr>
<%	}
		if(!HiltonUtility.isEmpty(s_addressLine3)) { %>
						<tsa:tr field="rfq-shp-addressLine3"><td class=browseRow height=12px nowrap><%=s_addressLine3%></td></tsa:tr>
<%	}
		if(!HiltonUtility.isEmpty(s_addressLine4)) { %>
						<tsa:tr field="rfq-shp-addressLine4"><td class=browseRow height=12px nowrap><%=s_addressLine4%></td></tsa:tr>
<%	}
		if(!HiltonUtility.isEmpty(s_city + s_state + s_postalCode + s_country)) {
			if(!HiltonUtility.isEmpty(s_city) && !HiltonUtility.isEmpty(s_state)) {%>
						<tr><td class=browseRow height=12px nowrap><%=s_city%>, <%=s_state%>   <%=s_postalCode%>  <%=s_country%></td></tr>
<%		} else {%>
						<tr><td class=browseRow height=12px nowrap><%=s_city%> <%=s_state%>   <%=s_postalCode%>  <%=s_country%></td></tr>
<%		}
		}%>
						</table>

<%	if (!HiltonUtility.isEmpty(rfqHeader.getShipToContact())) { %>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tsa:tr field="rfq-shp-attention"><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-shp-attention" defaultString="Attention" checkRequired="false"/>: <%=s_attention%></td></tsa:tr>
						</table>
<%	} %>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tsa:tr field="rfq-requiredDate"><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-requiredDate" defaultString="Required By" checkRequired="false"/>: <%=HiltonUtility.getFormattedDate(rfqHeader.getRequiredDate(), oid, userDateFormat)%></td></tsa:tr>
						<tsa:tr field="rfq-priority"><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-priority" defaultString="Priority" checkRequired="false"/>: <%=s_priority%></td></tsa:tr>
						<tsa:tr field="rfq-shipVia"><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-shipVia" defaultString="Ship Via" checkRequired="false"/>: <%=s_shipVia%></td></tsa:tr>
						<tsa:tr field="rfq-routing"><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-routing" defaultString="Routing" checkRequired="false"/>:&nbsp;<%=rfqHeader.getRouting()%></td></tsa:tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
			<td rowspan=3 align=right valign=top><%@ include file="/rfq/rfq_sidebar.jsp" %></td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
<%	if (!s_rfqType.equals("PU") && processMap.containsKey("WEBPOST_OPTIONS")) { %>
			<td width=50% align=center valign=top>
				<table id=webpostTable border=0 cellspacing=0 cellpadding=0 width=90% class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;<tsa:label labelName="rfq-Webpost-Options" defaultString="Webpost Options" /></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow id=webpostRows>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (extRfqsActive && rfqHeader.getEventPaused().equalsIgnoreCase("Y")) {%>
						<tr><td class=error height=12px nowrap><tsa:label labelName="rfq-Current-Bid" defaultString="Current Bid Event Paused!" /></td></tr>
<%	}
		if (s_webpost.equals("N")) { %>
						<tr><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-Non-Web-Solicitation" defaultString="Non-Web Solicitation" /></td></tr>
<%	} else	if (s_webpost.indexOf("D")>= 0 || s_webpost.indexOf("V")>= 0 || s_webpost.indexOf("VB")>= 0 || s_webpost.indexOf("DB")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-Post-Solicitation" defaultString="Post Solicitation" /></td></tr>
<%		if (s_webpost.indexOf("VB")>= 0 || s_webpost.indexOf("DB")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-Online-Bidding" defaultString="Online Bidding" /></td></tr>
<%		}
			if (s_webpost.indexOf("D")>= 0 || s_webpost.indexOf("DB")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-Downloadable" defaultString="Downloadable" /></td></tr>
<%		} %>
<%		if (s_auctionType.indexOf("S")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-Sealed-Bid" defaultString="Sealed Bid" /></td></tr>
<%		}
			if (s_auctionType.indexOf("O")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-Open-Auction" defaultString="Open Auction" /></td></tr>
<%		}
			if (s_bidAccess.indexOf("U")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-Unrestricted" defaultString="Supplier Access: Unrestricted" /></td></tr>
<%		}
			if (s_bidAccess.indexOf("I")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-By-Invitation-Only" defaultString="Supplier Access: By Invitation Only" /></td></tr>
<%		}
			if (s_bidAccess.indexOf("R")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-Restricted-Suppliers" defaultString="Supplier Access: Restricted Suppliers" /></td></tr>
<%		}
			if (s_bidItemOptions.indexOf("A")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-Must-Bid" defaultString="Must Bid on All Items" /></td></tr>
<%		}
			if (s_auctionType.indexOf("O")>= 0 && s_lowBidReq.indexOf("Y")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-Must-Enter-Bids" defaultString="Must Enter Bids Before Viewing Lowest Bid" /></td></tr>
<%		}
			if (s_lowBidSource.indexOf("L")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-Line-Item-Amounts" defaultString="Base Lowest Bids on: Line Item Amounts" /></td></tr>
<%		}
			if (s_lowBidSource.indexOf("S")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-Subtotal-Amount" defaultString="Base Lowest Bids on: Subtotal Amount" /></td></tr>
<%		}
			if (s_auctionType.indexOf("O")>= 0 && s_lowestDisplay.indexOf("A")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-lowest-bid-dollar-amount" defaultString="Display the lowest bid dollar amount" />.</td></tr>
<%		}
			if (s_lowestDisplay.indexOf("N")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap><tsa:label labelName="rfq-lowest-bid-notification" defaultString="Display a lowest bid notification" />.</td></tr>
<%		}
			if (extRfqsActive && s_auctionType.indexOf("O")>= 0 && rfqHeader.getExtendMinutes().compareTo(new BigDecimal(0)) > 0) { %>
						<tr><td class=browseRow height=12px nowrap>Extend Auction By <%=rfqHeader.getExtendMinutes()%> Minutes.</td></tr>
<%		}
		}%>
						</table>
					</td>
				</tr>
				</table>
			</td>
<%	} %>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center width=100%>
		<table border=0 cellspacing=0 cellpadding=0 width=90% class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=100%>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseHdr>
						<tr>
							<tsa:td field="rfq-supplier" width="20%" height="18px" cssClass="browseHdr">&nbsp;<b><tsa:label labelName="rfq-supplier" defaultString="Supplier" /></b></tsa:td>
							<tsa:td field="rfq-total" width="15%" height="18px" cssClass="browseHdr" align="right"><b><tsa:label labelName="rfq-total" defaultString="Total" /></b></tsa:td>
<%	if (extRfqsActive) {%>
							<tsa:td field="rfq-totalScore" width="15%" height="18px" cssClass="browseHdr" align="right"><b><tsa:label labelName="rfq-totalScore" defaultString="Score" /> (out of <%=rfqHeader.getMaxPoints()%>)</b></tsa:td>
<%	}%>
							<tsa:td field="rfq-paymentTerms" width="14%" height="18px" cssClass="browseHdr" align="right"><b><tsa:label labelName="rfq-paymentTerms" defaultString="Terms" /></b></tsa:td>
							<tsa:td field="rfq-responseDate" width="12%" height="18px" cssClass="browseHdr" align="right"><b><tsa:label labelName="rfq-responseDate" defaultString="Response Date" /></b></tsa:td>
<%	if (!extRfqsActive) {%>
							<tsa:td field="rfq-promiseDate" width="12%" height="18px" cssClass="browseHdr" align="right"><b><tsa:label labelName="rfq-promiseDate" defaultString="Promise Date" /></b></tsa:td>
<%	}%>
							<tsa:td field="rfq-bidValidTo" width="12%" height="18px" cssClass="browseHdr" align="right"><b><tsa:label labelName="rfq-bidValidTo" defaultString="Bid Valid To" /></b></tsa:td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<tsa:hidden name="currentVendorId" value=""/>
<%
	String s_vendorId = "";
	String s_bidResponse = "";

	int i_size = 0;
	if (vendorList != null)
	{
		i_size = vendorList.size();

		for (i = 0;i < i_size; i++)
		{
			RfqVendor rfqVendor = (RfqVendor) vendorList.get(i);
			RfqVendorPK rfqVendorPK = rfqVendor.getComp_id();
			s_vendorId = rfqVendorPK.getVendorId();
			String s_vendorName = VendorManager.getInstance().getVendorName(oid, s_vendorId);
			if (s_vendorName.length() > 20)
			{
				s_vendorName = s_vendorName.substring(0, 20);
			}

			String vendorTerms = "";
			String dateResponseRecv = "";
			String	datePromised = "";
			String	bidValidTo = "";
			String	totalBid = "";
			String	totalScore = "";

			//BigDecimal differenceDates = HiltonUtility.getDateDifference(rfqVendor.getBidValidTo(), new Date());
			BigDecimal differenceDates = new BigDecimal(0);
			if (rfqVendor.getBidValidTo() != null) {
				differenceDates = HiltonUtility.getDateDifference(rfqVendor.getBidValidTo(), new Date());
			}
			if (differenceDates.compareTo(new BigDecimal(0)) < 0 && rfqVendor.getBidCode().equalsIgnoreCase("SE"))
			{
				vendorTerms = rfqVendor.getPaymentTerms();
				dateResponseRecv = HiltonUtility.getFormattedDate(rfqVendor.getDateResponseRecv(), oid, userDateFormat);
				datePromised = HiltonUtility.getFormattedDate(rfqVendor.getDatePromised(), oid, userDateFormat);
				bidValidTo = HiltonUtility.getFormattedDate(rfqVendor.getBidValidTo(), oid, userDateFormat);
				totalBid = "Sealed";

				if (HiltonUtility.isEmpty(rfqVendor.getEvaluationStatus()) || rfqVendor.getEvaluationStatus().equals(DocumentStatus.EVALUATION_INCOMPLETE)) {
					totalScore = "(Pending) " + String.valueOf(rfqVendor.getTotalScore());
				} else {
					totalScore = String.valueOf(rfqVendor.getTotalScore());
				}
			}
			else
			{
				if (rfqVendor.getBidsEntered().equals("Y")) {
					vendorTerms = rfqVendor.getPaymentTerms();
					dateResponseRecv = HiltonUtility.getFormattedDate(rfqVendor.getDateResponseRecv(), oid, userDateFormat);
					datePromised = HiltonUtility.getFormattedDate(rfqVendor.getDatePromised(), oid, userDateFormat);
					bidValidTo = HiltonUtility.getFormattedDate(rfqVendor.getBidValidTo(), oid, userDateFormat);
					totalBid = String.valueOf(HiltonUtility.getFormattedCurrency(rfqVendor.getBidTotal(), s_currCode, oid, true));

					if (HiltonUtility.isEmpty(rfqVendor.getEvaluationStatus()) || rfqVendor.getEvaluationStatus().equals(DocumentStatus.EVALUATION_INCOMPLETE)) {
						totalScore = "(Pending) " + String.valueOf(rfqVendor.getTotalScore());
					} else {
						totalScore = String.valueOf(rfqVendor.getTotalScore());
					}
				}
				else {
					totalBid = "No Bids Submitted";
				}
			}
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<tsa:td field="rfq-supplier" width="20%" cssClass="browseRow" valign="top"><a href="javascript: viewSupplier('<%=s_vendorId%>'); void(0);" title="Click here to View Supplier Details."><%=s_vendorName%></a></tsa:td>
					<tsa:td field="rfq-total" width="15%" cssClass="browseRow" valign="top" align="right"><%=totalBid%></tsa:td>
<%	if (extRfqsActive) {%>
							<tsa:td field="rfq-evaluationScore" width="15%" cssClass="browseRow" valign="top" align="right"><%=totalScore%></tsa:td>
<%	}%>
					<tsa:td field="rfq-paymentTerms" width="14%" cssClass="browseRow" valign="top" align="right"><%=vendorTerms%></tsa:td>
					<tsa:td field="rfq-responseDate" width="12%" cssClass="browseRow" valign="top" align="right"><%=dateResponseRecv%></tsa:td>
<%	if (!extRfqsActive) {%>
					<tsa:td field="rfq-promiseDate" width="12%" cssClass="browseRow" valign="top" align="right"><%=datePromised%></tsa:td>
<%	}%>
					<tsa:td field="rfq-bidValidTo" width="12%" cssClass="browseRow" valign="top" align="right"><%=bidValidTo%></tsa:td>
				</tr>
				</table>
<%		}
		}
		if (i_size == 0) { %>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td class=browseRow valign=top><tsa:label labelName="rfq-no-suppliers-associated" defaultString="There are no suppliers associated with this solicitation" />.</td>
				</tr>
				</table>
<%	}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<%	if (processMap.containsKey("HEADER_QUESTIONS")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center width=100%>
		<table border=0 cellspacing=0 cellpadding=0 width=90% class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=100%>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseHdr>
						<tr>
							<td width=50% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="rfq-Supplier-Question" defaultString="Supplier Question" /></b></td>
							<td width=20% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="rfq-Response-Type" defaultString="Response Type" /></b></td>
							<td width=20% height=18px class=browseHdr <%	if (!extRfqsActive) {%> style="visibility:hidden;"<%}%>>&nbsp;<b><tsa:label labelName="rfq-Rating-Method" defaultString="Rating Method" /></b></td>
							<td width=10% height=18px class=browseHdr <%	if (!extRfqsActive) {%> style="visibility:hidden;"<%}%> align=right>&nbsp;<b><tsa:label labelName="rfq-Max-Points" defaultString="Max Points" /></b></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%
	List questionList = (List) rfqHeader.getRfqQuestionList();
	String s_question = "";
	String s_responseType = "";
	String s_ratingMethod = "";

	if (questionList != null)
	{
		for(i = 0; i < questionList.size(); i++)
		{
			RfqQuestion rfqQuestion = (RfqQuestion) questionList.get(i);
			RfqQuestionPK rfqQuestionPK = rfqQuestion.getComp_id();
			BigDecimal b_icQuestion = rfqQuestionPK.getIcQuestion();

			s_question = rfqQuestion.getQuestionText();
			s_responseType = StdQuestionResponseType.toString(rfqQuestion.getResponseType(), oid);
			s_ratingMethod = StdQuestionRatingMethod.toString(rfqQuestion.getRatingMethod(), oid);
%>

				<tr>
					<td width=5% class=browseRow align=right valign=top nowrap><%=HiltonUtility.getBigDecimalFormatted(rfqQuestion.getSequence(), 0)%>&nbsp;&nbsp;</td>
					<td width=45% class=browseRow valign=top><%=s_question%></td>
					<td width=20% class=browseRow valign=top><%=s_responseType%></td>
					<td width=20% class=browseRow valign=top <%	if (!extRfqsActive) {%> style="visibility:hidden;"<%}%>><%=s_ratingMethod%></td>
					<td width=10% class=browseRow valign=top align=right <%	if (!extRfqsActive) {%> style="visibility:hidden;"<%}%>><%=rfqQuestion.getMaxPoints()%></td>
				</tr>
<%	}
	}
	if (questionList.size() <= 0) {%>
				<tr>
					<td colspan=3 class=browseRow valign=top><tsa:label labelName="rfq-no-questions-associated" defaultString="There are no questions associated with this solicitation" />.</td>
				</tr>
<%}%>

				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<%	}
		if (processMap.containsKey("HEADER_NOTES")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center width=100%>
		<table border=0 cellspacing=0 cellpadding=0 width=90% class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=100%>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseHdr>
						<tr>
							<td width=68% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="rfq-Comment" defaultString="Comment" /></b></td>
<%		if (solicitationActive) {%>
							<td width=7% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="rfq-Post" defaultString="Post" /></b></td>
<%		} else {%>
							<td width=7% height=18px class=browseHdr align=center>&nbsp;</td>
<%		}%>
							<td width=8% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="rfq-Print" defaultString="Print" /></b></td>
							<td width=7% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="rfq-Bold" defaultString="Bold" /></b></td>
							<td width=10% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="rfq-Placement" defaultString="Placement" /></b></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100%>
<%
	List cmtList = (List) rfqHeader.getDocCommentList();
	String s_placeString = "";

	if (cmtList!=null)
	{
		for(i=0;i<cmtList.size();i++)
		{
			DocComment docComment = (DocComment)cmtList.get(i);
			DocCommentPK docCommentPK = docComment.getComp_id();
			DocText docText = docComment.getDocText();
			if (docText == null)
			{
				docText = new DocText();
			}
			BigDecimal b_icCmtHeader = docCommentPK.getIcHeader();
			BigDecimal b_icCmtLine = docCommentPK.getIcLine();
			String	s_commentId = docComment.getCommentId();
			String	s_cmtTitle = docComment.getCommentTitle();
			String	s_cmtPost = docComment.getCommentWebpost();
			String	s_cmtPrint = docComment.getCommentPrint();
			String	s_cmtBold = docComment.getCommentBold();
			String	s_cmtPlace = docComment.getCommentPlace();
			String s_cmtText = docText.getStdText();
			if (s_cmtPlace.equals("A")){
				s_placeString = "After";
			}
			else if (s_cmtPlace.equals("B")){
				s_placeString = "Before";
			}
			if (HiltonUtility.isEmpty(s_cmtPost)) {	s_cmtPost = "N";	}
			if (HiltonUtility.isEmpty(s_cmtPrint)) {	s_cmtPrint = "N";	}
			if (HiltonUtility.isEmpty(s_cmtBold)) {	s_cmtBold = "N";	}
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=68% class=browseRow>
						<% if (s_cmtBold.equals("Y")) { %><b><% } %><%=s_cmtTitle%><% if (s_cmtBold.equals("Y")) { %></b><% } %>
					</td>
<%		if (solicitationActive) {%>
					<td width=7% class=browseRow align=center valign=top><%=s_cmtPost%></td>
<%		} else {%>
					<td width=7% class=browseRow align=center valign=top>&nbsp;</td>
<%		} %>
					<td width=8% class=browseRow align=center valign=top><%=s_cmtPrint%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_cmtBold%></td>
					<td width=10% class=browseRow align=center valign=top><%=s_placeString%></td>
				</tr>
				</table>

				<table class=browseRow>
				<tr>
					<td width=50px>&nbsp;</td>
					<td width=100%>
						<% if (s_cmtBold.equals("Y")) { %><b><% } %><%=s_cmtText%><% if (s_cmtBold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				</table>
<%	}
	}
	if (cmtList.size() <= 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow><tsa:label labelName="rfq-no-comments-associated" defaultString="There are no comments associated with this solicitation" />.</td></tr>
				</table>
<%}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<%	}
		if (processMap.containsKey("SHOPCART")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center width=100%>
		<table border=0 cellspacing=0 cellpadding=0 width=90% class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=55%>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseHdr>
						<tr>
							<tsa:td field="rfq-itemNumber" width="30%" height="18px" noWrap="nowrap" cssClass="browseHdr" align="left"><tsa:label labelName="rfq-itemNumber" defaultString="Item/Part #" checkRequired="false"/></tsa:td>
							<tsa:td field="rfq-commodity" width="20%" height="18px" noWrap="nowrap" cssClass="browseHdr" align="left"><tsa:label labelName="rfq-commodity" defaultString="Commodity" checkRequired="false"/></tsa:td>
							<tsa:td field="rfq-lineStatus" width="20%" height="18px" noWrap="nowrap" cssClass="browseHdr" align="left"><tsa:label labelName="rfq-lineStatus" defaultString="Line Status" checkRequired="false"/></tsa:td>
							<tsa:td field="rfq-quantity" width="20%" height="18px" noWrap="nowrap" cssClass="browseHdr" align="right"><tsa:label labelName="rfq-quantity" defaultString="Quantity" checkRequired="false"/></tsa:td>
							<tsa:td field="rfq-uom" width="10%" height="18px" noWrap="nowrap" cssClass="browseHdr" align="left"><tsa:label labelName="rfq-uom" defaultString="UOM" checkRequired="false"/></tsa:td>
						</tr>
						</table>
					</td>
					<td width=45%>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseHdr>
						<tr>
							<tsa:td field="rfq-supplier" width="30%" height="18px" noWrap="nowrap" cssClass="browseHdr" align="left"><tsa:label labelName="rfq-supplier" defaultString="Supplier" checkRequired="false"/></tsa:td>
							<tsa:td field="rfq-unitCost" width="30%" height="18px" noWrap="nowrap" cssClass="browseHdr" align="right"><tsa:label labelName="rfq-unitCost" defaultString="Unit Cost" checkRequired="false"/></tsa:td>
							<tsa:td field="rfq-extendedCost" width="30%" height="18px" noWrap="nowrap" cssClass="browseHdr" align="right"><tsa:label labelName="rfq-extendedCost" defaultString="Extended Cost" checkRequired="false"/></tsa:td>
							<tsa:td field="rfq-seeNotes" width="10%" height="18px" noWrap="nowrap" cssClass="browseHdr" align="center"><tsa:label labelName="rfq-seeNotes" defaultString="Notes" checkRequired="false"/></tsa:td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<tsa:hidden name="RfqLine_icRfqLine" value=""/>
				<table id=itemRows border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td>
<% 	//Rfq Lines
	int iRow = 0;
	if (lineList!=null){
		for(i=0;i<lineList.size();i++)
		{
		RfqLine rfqLine = (RfqLine)lineList.get(i);
		BigDecimal bd_icRfqLine = rfqLine.getIcRfqLine();
		BigDecimal bd_umFactor = rfqLine.getUmFactor();
		if (bd_umFactor.compareTo(new BigDecimal(0)) <= 0)
		{
			bd_umFactor = new BigDecimal(1);
		}
		List bidList = (List) rfqLine.getRfqBidList();
		pageContext.setAttribute("i", i);
%>
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
<% 			List commentList = (List) rfqLine.getDocCommentList();
			if (commentList != null && commentList.size() > 0)
			{ %>
						<tr>
							<td valign=top width=55%>
							<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow valign=top>

<%				for (int ix = 0; ix < commentList.size(); ix++)
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
					}	%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td class=browseRow>
								<b><tsa:label labelName="req-catalogItemComment" defaultString="Comment" />:&nbsp;</b>
								<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
							</td>
						</tr>
<%				}	%>
							</table>
							</td>
						</tr>
<%			}	%>
						<tr>
							<td valign=top width=55%>
								<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow valign=top>
								<tr>
									<td width=5% class=browseRow nowrap align=center><a href="javascript: viewItem(<%=i%>); void(0);" title="Click here to Vew/Modify Item Details." onMouseOver="highlightItem(<%=i%>);" onMouseOut="removeItemHighlight(<%=i%>);"><%=i+1%></a>&nbsp;</td>
									<tsa:td field="rfq-itemNumber" width="25%" cssClass="browseRow" noWrap="nowrap"><%=rfqLine.getItemNumber()%><tsa:hidden id="icRfqLine_${i}" name="icRfqLine_${i}" value="<%=bd_icRfqLine%>"/></tsa:td>
									<tsa:td field="rfq-commodity" width="20%" cssClass="browseRow" noWrap="nowrap"><%=rfqLine.getCommodity()%></tsa:td>
									<tsa:td field="rfq-lineStatus" width="20%" cssClass="browseRow" noWrap="nowrap"><%=DocumentStatus.toString(rfqLine.getStatus(), oid)%></tsa:td>
									<tsa:td field="rfq-quantity" width="20%" cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), oid)%></tsa:td>
									<tsa:td field="rfq-uom" width="10%" cssClass="browseRow" noWrap="nowrap" align="left"><%=rfqLine.getUmCode()%></tsa:td>
								</tr>
								<tr>
									<td class=browseRow nowrap>&nbsp;</td>
									<td colspan=5 class=browseRow>
										<%=rfqLine.getDescription()%>
										<% if (!HiltonUtility.isEmpty(rfqLine.getCatalogId())) { %>&nbsp;&nbsp;(<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-catalog", "Catalog")%>:&nbsp;</b><%=rfqLine.getCatalogId()%>)<% } %>
									</td>
								</tr>
<%		List requisitionLineInfoList = (List) rfqLine.getRequisitionLineInfoList();
			if (requisitionLineInfoList != null)
			{
				for (int ix = 0; ix < requisitionLineInfoList.size(); ix++)
				{
					ReferenceInfo requisitionInfo = (ReferenceInfo) requisitionLineInfoList.get(ix);
%>
								<tsa:tr field="rfq-requisitionNumber">
									<td>&nbsp;</td>
									<td nowrap align=right valign=top><tsa:label labelName="rfq-requisitionNumber" defaultString="Requisition #" />:</td>
									<td nowrap valign=top><a href="javascript: reqPreview('<%=requisitionInfo.getIcHeader()%>'); void(0);" title="View Requisition"><%=requisitionInfo.getFormNumber()%></a></td>
									<td colspan="3">&nbsp;</td>
								</tsa:tr>
<%			}
			}

			List poLineInfoList = (List) rfqLine.getPoLineInfoList();
			if (poLineInfoList != null)
			{
				for (int ix = 0; ix < poLineInfoList.size(); ix++)
				{
					ReferenceInfo poInfo = (ReferenceInfo) poLineInfoList.get(ix);
%>
								<tsa:tr field="rfq-poNumber">
									<td>&nbsp;</td>
									<td nowrap align=right valign=top><tsa:label labelName="rfq-poNumber" defaultString="Order #" />:</td>
									<td valign=top>
										<a href="javascript: orderPreview('<%=poInfo.getIcHeader()%>'); void(0);" title="View Order"><%=poInfo.getFormNumber()%></a>
<%				if ((poInfo.getReleaseNumber()).compareTo(new BigDecimal(0)) > 0) { %>
								&nbsp;/&nbsp;<%=poInfo.getReleaseNumber()%>
<%				} %>
									</td>
								</tsa:tr>
<%				}
			}
%>
								</table>
							</td>
							<td width=45%>

<%	if (bidList != null) {
		for(b=0;b<bidList.size();b++)
		{
		RfqBid rfqBid = (RfqBid)bidList.get(b);
		RfqBidPK rfqBidPK = (RfqBidPK) rfqBid.getComp_id();
		String s_bidVendorId = rfqBidPK.getVendorId();
		String s_vendorName = VendorManager.getInstance().getVendorName(oid, s_bidVendorId);
		String s_bidUmCode = rfqBid.getUmCode();
		if (s_bidUmCode==null){s_bidUmCode = "EA";}
		BigDecimal b_bidQuantity = HiltonUtility.getFormattedQuantity(rfqBid.getQuantity(), oid);
		BigDecimal bd_bidUnitPrice = HiltonUtility.getFormattedPrice(rfqBid.getUnitPrice(), oid);
		String s_bidUnitPrice = HiltonUtility.getFormattedPriceCurrency(bd_bidUnitPrice, s_currCode, oid, false).toString();
		String s_bidCode = rfqBid.getBidCode();
		if (s_bidCode.equalsIgnoreCase("NC"))
		{
			s_bidUnitPrice = "No Charge";
		}
		if (s_bidCode.equalsIgnoreCase("SE")) {
			s_bidUnitPrice = "Sealed";
		}
		else if (s_bidCode.equalsIgnoreCase("NSP"))
		{
			s_bidUnitPrice = "Not Separately Priced";
		}
		else if (s_bidCode.equalsIgnoreCase("SN"))
		{
			s_bidUnitPrice = "See Notes";
		}
		else if (s_bidCode.equalsIgnoreCase("NB"))
		{
			s_bidUnitPrice = "No Bid";
		}
		else if (s_bidCode.equalsIgnoreCase("NE"))
		{
			s_bidUnitPrice = "None Entered";
		}

		BigDecimal bd_bidExtendedPrice = new BigDecimal(0);
		bd_bidExtendedPrice = ( (b_bidQuantity.multiply(bd_bidUnitPrice)).multiply(bd_umFactor) ).setScale(Integer.valueOf(s_dollar_decimals).intValue(),BigDecimal.ROUND_HALF_UP);
%>
								<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
								<tr>
									<tsa:td field="rfq-supplier" width="30%" cssClass="browseRow" align="left" title="${s_bidVendorId}:&nbsp;${s_vendorName}"><%=s_bidVendorId%></tsa:td>
									<tsa:td field="rfq-unitCost" width="30%" cssClass="browseRow" align="right">
<%
if (s_bidUnitPrice.equals("See Notes")) {%>
										<a href="javascript: viewNotes('<%=bd_icRfqLine%>', '<%=s_bidVendorId%>'); void(0);"><%=s_bidUnitPrice%></a>
<%	} else { %>
										<%=s_bidUnitPrice%>
<%	} %>
									</tsa:td>
									<tsa:td field="rfq-extendedCost" width="30%" cssClass="browseRow" align="right"><%=HiltonUtility.getFormattedCurrency(bd_bidExtendedPrice, s_currCode, oid)%></tsa:td>
									<td width=10% class=browseRow nowrap align=right><a href="javascript: viewNotes('<%=bd_icRfqLine%>', '<%=s_bidVendorId%>');" title="Click here to View Supplier Notes."> <img src="<%=contextPath%>/images/notes_line.gif" border=0></a>&nbsp;</td>
								</tr>
								<tr height="18px" valign="top"><td colspan="4"><%=s_vendorName%></td></tr>
								</table>
<%		}
	}%>
							</td>
						</tr>
		<%
			if (rfqLine != null) {
    			List inspList = rfqLine.getInspectionList();
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
    								<%=HiltonUtility.ckNull(inspDesc) %>&nbsp;&nbsp;&nbsp; <%=HiltonUtility.ckNull(insp.getStandardCode())%>
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

<!-- Desde aqui es el add para el Attachment -->
						<tr>
							<td colspan="2">
								<table border=0 cellspacing=0 cellpadding=0  class=browseRow>



<%
            List attachmentList = (List) rfqLine.getDocAttachmentList();
            boolean flagAttachment = false;
            if (attachmentList != null)
			{
					for (int ix = 0; ix < attachmentList.size(); ix++)
					{
						DocAttachment docAttachment = (DocAttachment) attachmentList.get(ix);
						String	filename = docAttachment.getDocFilename();
						String	ext = filename.substring(filename.lastIndexOf(".") + 1);
						%>
						<tr>
							<td width=3% class=browseRow nowrap>&nbsp;</td>
							<td width=13% class=browseRow nowrap align="right">
							<%if(!flagAttachment){%>
								<b><tsa:label labelName="po-catalogItemAttachment" defaultString="Attachment" />:&nbsp;</b>
								<%flagAttachment=true;
							}%>

<%		if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>');void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("EML")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/email.gif" border=0 alt="Open Attached Email"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT") || ext.equalsIgnoreCase("PPTX")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
						</td>
							<td width=50% class=browseRow nowrap>
								<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);" title="Click here to Open the file"><%=docAttachment.getDocTitle()%></a>
							</td>
							<td width="34">&nbsp;</td>

						</tr><%
					}
			}
            %>
<!-- Hasta aqui es el add para el Attachment -->



								</table>
							</td>
						</tr>



<% 			if (commentList != null && commentList.size() > 0)
			{ %>
						<tr>
							<td valign=top width=55%>
							<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow valign=top>

<%				for (int ix = 0; ix < commentList.size(); ix++)
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
					}	%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td class=browseRow>
								<b><tsa:label labelName="req-catalogItemComment" defaultString="Comment" />:&nbsp;</b>
								<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
							</td>
						</tr>
<%				}	%>
							</table>
							</td>
						</tr>
<%			}	%>
						<tr><td colspan=10><hr></td></tr>
						</table>
<%	} } %>
					</td>
				</tr>
				</table>
<%	if (lineList == null || lineList.size() <= 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow><tsa:label labelName="rfq-no-item-associated" defaultString="There are no items associated with this solicitation" />.</td></tr>
				</table>
<%	}%>
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
		<table border=0 cellspacing=0 cellpadding=0 width=90% class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=100%>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseHdr>
						<tr>
							<td width=70% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="rfq-Attachment" defaultString="Attachment" /></b></td>
							<td width=10% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="rfq-Print" defaultString="Print" /></b></td>
<%		if (solicitationActive) {%>
							<td width=10% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="rfq-Post" defaultString="Post" /></b></td>
<%		} else {%>
							<td width=10% height=18px class=browseHdr align=center>&nbsp;</td>
<%		}%>
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
		List attachmentList = (List) rfqHeader.getDocAttachmentList();
			int ai = 0;
			if (attachmentList != null) {
				for(i = 0; i < attachmentList.size(); i++) {
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
					<td width=70% class=browseRow>
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
<%		if (solicitationActive) {%>
					<td width=10% class=browseRow align=center valign=top><%=docAttachment.getDocPost()%></td>
<%		} else {%>
					<td width=10% class=browseRow align=center valign=top>&nbsp;</td>
<%		}%>
					<td width=10% class=browseRow align=center valign=top></td>
				</tr>
				</table>

	<% 		}
			}
			if (ai == 0) {%>
				<table border=0 cellpadding=2 cellspacing=0 width=100% class=browseRow>
				<tr><td class=browseRow><tsa:label labelName="rfq-no-attachments-associated" defaultString="There are no attachments associated with this solicitation" />.</td></tr>
				</table>
<%		}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	}%>

<br>

<tsa:hidden name="icHeader" value="<%=icHeader%>"/>
<tsa:hidden name="FilenameXls" value="<%=FilenameXls%>"/>
<tsa:hidden name="formType" value="<%=formType%>"/>
<tsa:hidden name="ownerId" value="<%=ownerId%>"/>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

/***** PLEASE ADD ANY JS FUNCTIONS THAT APPLY TO BOTH rfq_review.jsp and rfq_summary.jsp TO /scripts/rfq_review.js *****/

	frm = document.purchaseform;

	var rfqnumber = "<%= headerEncoder.encodeForJavaScript(s_rfqNumber) %>";
	var fiscalyear = "<%= headerEncoder.encodeForJavaScript(s_fiscalYear) %>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var synopsisEmpty = <%=HiltonUtility.isEmpty(rfqHeader.getRfqDescription())%>; <%-- synopsisEmpty is a boolean value, so not a problem for XSS --%>
	var synopsisLabel = "<tsa:label labelName="rfq-purpose" defaultString="Purpose" />";
	var webpost = "<%=rfqHeader.getWebpost()%>";
	var bidAccess = "<%=s_bidAccess%>";
	var vendors = <%=vendorList.size()%>;
	var solicitationActive = "<%=propertiesManager.getProperty("RFQ OPTIONS", "SOLICITATIONS", "N")%>";
	var auctionStartDate="<%=rfqHeader.getAuctionStartDate()%>";
	var auctionEndDate="<%=rfqHeader.getAuctionEndDate()%>";
	var auctionStartTime="<%=rfqHeader.getAuctionStartTime()%>";
	var auctionEndTime="<%=rfqHeader.getAuctionEndTime()%>";
	var auctionEvent = "<%=rfqHeader.getAuctionEvent()%>";

	var navMenu = getNavCookie("navArray");
	if (navMenu.indexOf("<%=RfqType.toString(s_rfqType, oid)%>") < 0)
	{
		setNavCookie(currentpage, currentprocessmethod, "<%=RfqType.toString(s_rfqType, oid)%>");
	}

<%	if (!HiltonUtility.isEmpty(errorMsg)) {%>
	alert("<%=errorMsg%>");
<%	}%>

	function toggleDetailsDisplay(areaName, type) {
		var myAreas = document.getElementsByName(areaName);
		var myArea;
		var myImg = document.getElementById(areaName + "Img");
		var myText = document.getElementById(areaName + "Text");
		var hideArea = false;

		for (i = 0; i < myAreas.length; i++) {
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

	function switchView()
	{
		frm.viewType.value = "CLASSIC";
		doSubmit('/rfq/rfq_summary.jsp', 'RfqRetrieve');
	}

 	function ReadXlsItems(){
 		doSubmit("/rfq/rfq_items.jsp","RfqReqPoAddItems;RfqHeaderUpdateById");
 	}

 	function ToReview(){
 		doSubmit("/rfq/rfq_review.jsp", "RfqRetrieve");
 	}

    function RfqReqPoAddItems(){

    	var uploadItemsRole = <%=uploadItemsRole%>
    	//alert(uploadItemsRole);

    	if (uploadItemsRole== "1"){
 			//alert("You Only Can See This Option");
 			}
    	if (uploadItemsRole!= "1"){
	  		doSubmit("/rfq/rfq_xls_upload_items_new.jsp","DoNothing");
       	}
    }

	function reqPreview(icReqHeader) {
		popupParameters = "RequisitionHeader_icReqHeader=" + icReqHeader;
		doSubmitToPopup('/requests/req_preview.jsp', 'RequisitionRetrieve', 'WIDTH=<%=formEntryWidth%>', 'HEIGHT=700px');
	}

    function orderPreview(icPoHeader) {
		popupParameters = "PoHeader_icPoHeader=" + icPoHeader;
		doSubmitToPopup('/orders/po_preview.jsp', 'PoRetrieve', 'WIDTH=<%=formEntryWidth%>', 'HEIGHT=700px');
	}

    function getRoutingList()
	{
		var icHeader = "<%=rfqHeader.getIcRfqHeader()%>";
		popupParameters = "RfqHeader_icRfqHeader=" + icHeader;
		popupParameters = popupParameters + ";ApprovalLog_icHeader=" + icHeader;
		popupParameters = popupParameters + ";RfqHeader_status=" + rfqStatus;
		doSubmitToPopup('/rfq/rfq_routinglist.jsp', 'RfqHeaderRetrieveById;ApprovalLogRetrieveByHeader', 'width=700px', 'height=450px');
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

// End Hide script -->
</SCRIPT>
