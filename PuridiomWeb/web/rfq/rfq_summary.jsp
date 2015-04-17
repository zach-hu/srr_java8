<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.ReferenceInfo" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.common.documents.StdQuestionRatingMethod" %>
<%@ page import="com.tsa.puridiom.common.documents.StdQuestionResponseType" %>
<%@ page import="com.tsa.puridiom.steporder.*" %>

<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>
<script language='Javascript1.2' src='<%=contextPath%>/scripts/rfq_review.js' type='text/javascript'></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/scheduleArrays.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/rfqOptionArrays.js"></script>
<%@ include file="/system/header.jsp" %>

<%
	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	int i;
	int b;

	BigDecimal bd_icRfqHeader = rfqHeader.getIcRfqHeader();
	String s_rfqStatus = rfqHeader.getStatus();
	String s_rfqType = rfqHeader.getRfqType();
	String s_rfqNumber = rfqHeader.getRfqNumber();
	String s_rfqAmendment = rfqHeader.getRfqAmendment();
	String	errorMsg = (String) request.getAttribute("errorMsg");

	if (HiltonUtility.isEmpty(s_rfqNumber)){ s_rfqNumber = "N/A"; }

	//General Information
	String s_buyer = rfqHeader.getBuyer();
	String s_dueDate = "";
	Date d_dueDate = rfqHeader.getDueDate();
	if (d_dueDate != null){
		s_dueDate = HiltonUtility.getFormattedDate(d_dueDate, oid, userDateFormat);
	}
	String s_fiscalYear = rfqHeader.getFiscalYear();
	String s_currency = rfqHeader.getCurrencyCode();

	List lineList = (List) rfqHeader.getRfqLineList();
	int	i_lineCount = 0;
	if (lineList != null) {
		i_lineCount = lineList.size();
	}
	List vendorList = (List) rfqHeader.getRfqVendorList();

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

<script language='Javascript1.2' type="text/javascript">
<!--

	viewType = "<%=s_view%>";
	rfqNumber = "<%=s_rfqNumber%>";
	rfqStatus = "<%=s_rfqStatus%>";
	rfqVendorCount = <%=vendorList.size()%>;
	rfqInProgress = "<%=DocumentStatus.RFQ_INPROGRESS%>";
	rfqOpenSolicitation = "<%=DocumentStatus.RFQ_OPENSOLICITATION%>";
	rfqPurchasing = "<%=DocumentStatus.RFQ_PURCHASING%>";

	Array91= createRfqOptionsMenu(Array91[0]);

//-->
</SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");

	ProcessSteps steps = new ProcessSteps("rfqsteps_" + s_rfqType.toLowerCase(), oid);
	HashMap processMap = new HashMap();

	for (int ip = 0; ip < steps.getSize(); ip++) {
		processMap.put(steps.getProcess(ip), "TRUE");
	}

	String	s_current_page = "/rfq/rfq_summary.jsp";

	boolean solicitationActive = propertiesManager.getProperty("RFQ OPTIONS", "SOLICITATIONS", "N").equalsIgnoreCase("Y");
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=RfqType.toString(s_rfqType)%> Information</div>
			</td>
			<td nowrap class=hdr12 valign=middle>
<%	if (propertiesManager.isModuleActive("DOCUMENTS")) {%>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><a href="javascript: viewAttachments(); void(0);"><img src="<%=contextPath%>/images/clip.gif" border=0 alt="Attachments"></a></div>
<%	}%>
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

<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
<%	if ( !(s_rfqType.equals("PR") && s_webpost.equals("N")) && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENAMENDMENT) == 0) || (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) == 0 && !s_rfqType.equals("PR") && !s_rfqType.equals("PU")) )
		{
			if (i_lineCount > 0 && !s_rfqNumber.equals("N/A"))
			{
%>
	<td align=center id="forward_link"><a href="javascript: rfqForward(); void(0);">Forward</a></td>
<%		}
		}
		else if (s_rfqType.equals("PR") && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) <= 0)) { %>
	<td align=center><a href="javascript: rfqReturn(); void(0);">Return to Requisitioner</a></td>
<%	}%>
	<td align=right nowrap><a href="javascript: void(0);" onMouseOut="popDown('Menu91')" onMouseOver="popUp('Menu91',event)">More Options</a></td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=50% align=center valign=top>
				<table id=generalTable border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=BrowseHdr>
						<tr>
							<td width=4% class=browseHdr height=18px nowrap><a href="javascript: doSubmit('/rfq/rfq_general_info.jsp', 'RfqHeaderRetrieveById'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
							<td width=93% class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "general_information", "General Information")%></td>
							<td width=3% class=browseHdr height=18px nowrap><a href="javascript: toggleDetailsDisplay('generalDetails', 'Details'); void(0);"><img id='generalDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-buyerName")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-buyerName", "Buyer Name")%>:</b></td>
							<td nowrap><%=UserManager.getInstance().getUser(oid, s_buyer).getDisplayName()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-solicitationDate")%>>
							<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-solicitationDate", "Solicitation Date")%>:</b></td>
							<td nowrap><%=HiltonUtility.getFormattedDate(rfqHeader.getRfqDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-fiscalYear")%>>
							<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-fiscalYear", "Fiscal Year")%>:</b></td>
							<td nowrap><%=s_fiscalYear%></td>
						</tr>
						</table>
						<div id="generalDetails" style="display:none;">
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-dueDate")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-dueDate", "Due Date")%>:</b></td>
							<td nowrap><%=s_dueDate%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-currency")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-currency", "Currency")%>:</b></td>
							<td nowrap><%=s_currency%></td>
						</tr>
<%		List requisitionInfoList = (List) rfqHeader.getRequisitionInfoList();
			if (requisitionInfoList != null)
			{
				for (int ix = 0; ix < requisitionInfoList.size(); ix++)
				{
					ReferenceInfo requisitionInfo = (ReferenceInfo) requisitionInfoList.get(ix);
%>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-requisitionNumber")%>>
							<td nowrap align=right valign=top width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-requisitionNumber", "Requisition #")%>:</b></td>
							<td nowrap valign=top><a href="javascript: reqPreview('<%=requisitionInfo.getIcHeader()%>'); void(0);" title="View Requisition"><%=requisitionInfo.getFormNumber()%></a></td>
						</tr>
<%			}
			}

			List poHeaderInfoList = (List) rfqHeader.getPoInfoList();
			if (poHeaderInfoList != null)
			{
				for (int ix = 0; ix < poHeaderInfoList.size(); ix++)
				{
					ReferenceInfo poInfo = (ReferenceInfo) poHeaderInfoList.get(ix);
%>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-poNumber")%>>
							<td nowrap align=right valign=top width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-poNumber", "Order #")%>:</b></td>
							<td valign=top>
								<a href="javascript: orderPreview('<%=poInfo.getIcHeader()%>'); void(0);" title="View Order"><%=poInfo.getFormNumber()%></a>
<%				if ((poInfo.getReleaseNumber()).compareTo(new BigDecimal(0)) > 0) { %>
								&nbsp;/&nbsp;<%=poInfo.getReleaseNumber()%>
<%				} %>
						</tr>
<%				}
			}
%>
						</table>
						</div>
					</td>
				</tr>
				</table>
			</td>
			<td width=50% align=center valign=top>
			<table border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
						<tr>
							<td width=4% class=browseHdr height=18px nowrap><a href="javascript: doSubmit('/rfq/rfq_shipping.jsp', 'RfqHeaderShipToRetrieveById'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
							<td width=93% class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "shipping_information", "Shipping Information")%></td>
							<td width=3% class=browseHdr height=18px nowrap><a href="javascript: toggleDetailsDisplay('shippingDetails', 'Details'); void(0);"><img id='shippingDetailsImg' valign='baseline 'src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a></td>
						</tr>
						</table>
					</td>
				</tr>
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
	String s_routing = rfqHeader.getRouting();
%>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shipToCode")%>><td class=browseRow height=12px nowrap><b><%=s_shipToCode%></b></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shp-addressLine1")%>><td class=browseRow height=12px nowrap><%=s_addressLine1%></td></tr>
						</table>

						<div id="shippingDetails" style="display:none;">
						<table id=shippingRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (!HiltonUtility.isEmpty(s_addressLine2)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shp-addressLine2")%>><td class=browseRow height=12px nowrap><%=s_addressLine2%></td></tr>
<%	}
		if(!HiltonUtility.isEmpty(s_addressLine3)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shp-addressLine3")%>><td class=browseRow height=12px nowrap><%=s_addressLine3%></td></tr>
<%	}
		if(!HiltonUtility.isEmpty(s_addressLine4)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shp-addressLine4")%>><td class=browseRow height=12px nowrap><%=s_addressLine4%></td></tr>
<%	}
		if(!HiltonUtility.isEmpty(s_city + s_state + s_postalCode + s_country)) {
			if(!HiltonUtility.isEmpty(s_city) && !HiltonUtility.isEmpty(s_state)) {%>
						<tr><td class=browseRow height=12px nowrap><%=s_city%>, <%=s_state%>   <%=s_postalCode%>  <%=s_country%></td></tr>
<%		} else {%>
						<tr><td class=browseRow height=12px nowrap><%=s_city%> <%=s_state%>   <%=s_postalCode%>  <%=s_country%></td></tr>
<%		}
		}%>
						</table>
						</div>
<%	if (!HiltonUtility.isEmpty(rfqHeader.getShipToContact())) { %>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shp-attention")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-shp-attention", "Attention")%>: <%=s_attention%></td></tr>
						</table>
<%	} %>
						<div id="shippingDetails" style="display:none;">
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-requiredDate")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-requiredDate", "Required By")%>: <%=HiltonUtility.getFormattedDate(rfqHeader.getRequiredDate(), oid, userDateFormat)%></td></tr>
<%  if (!HiltonUtility.isEmpty(rfqHeader.getPriorityCode())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-priority")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-priority", "Priority")%>: <%=rfqHeader.getPriorityCode()%></td></tr>
<%	} %>
<%  if (!HiltonUtility.isEmpty(rfqHeader.getShippingCode())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shipVia")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-shipVia", "Ship Via")%>: <%=rfqHeader.getShippingCode()%></td></tr>
<%	} %>
						</table>
						</div>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
<%	if (!s_rfqType.equals("PU") && processMap.containsKey("WEBPOST_OPTIONS")) { %>
			<td width=50% align=center valign=top>
				<table id=webpostTable border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
						<tr>
							<td width=4% class=browseHdr height=18px nowrap><a href="javascript: doSubmit('/rfq/rfq_webpost_options.jsp', 'RfqHeaderRetrieveById'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
							<td width=93% class=browseHdr height=18px nowrap>&nbsp;Webpost Options</td>
	<% if (!s_webpost.equals("N")) {%>
							<td width=3% class=browseHdr height=18px nowrap><a href="javascript: toggleDetailsDisplay('webpostDetails', 'Details'); void(0);"><img id='webpostDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;</td>
	<% }%>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow id=webpostRows>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (extRfqsActive && rfqHeader.getEventPaused().equalsIgnoreCase("Y")) {%>
						<tr><td class=error height=12px nowrap>Current Bid Event Paused!</td></tr>
<%	}
		if (s_webpost.equals("N")) { %>
						<tr><td class=browseRow height=12px nowrap>Non-Web Solicitation</td></tr>
<%	} else	if (s_webpost.indexOf("D")>= 0 || s_webpost.indexOf("V")>= 0 || s_webpost.indexOf("VB")>= 0 || s_webpost.indexOf("DB")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Post Solicitation</td></tr>
<%		if (s_webpost.indexOf("VB")>= 0 || s_webpost.indexOf("DB")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Online Bidding</td></tr>
<%		}
			if (s_webpost.indexOf("D")>= 0 || s_webpost.indexOf("DB")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Downloadable</td></tr>
<%		} %>
<%		if (s_auctionType.indexOf("S")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Sealed Bid</td></tr>
<%		}
			if (s_auctionType.indexOf("O")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Open Auction</td></tr>
<%		}
			if (s_bidAccess.indexOf("U")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Supplier Access: Unrestricted</td></tr>
<%		}
			if (s_bidAccess.indexOf("I")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Supplier Access: By Invitation Only</td></tr>
<%		}
			if (s_bidAccess.indexOf("R")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Supplier Access: Restricted Suppliers</td></tr>
<%		}
			if (s_bidItemOptions.indexOf("A")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Must Bid on All Items</td></tr>
<%		}
			if (s_auctionType.indexOf("O")>= 0 && s_lowBidReq.indexOf("Y")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Must Enter Bids Before Viewing Lowest Bid</td></tr>
<%		}
			if (s_lowBidSource.indexOf("L")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Base Lowest Bids on: Line Item Amounts</td></tr>
<%		}
			if (s_lowBidSource.indexOf("S")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Base Lowest Bids on: Subtotal Amount</td></tr>
<%		}
			if (s_auctionType.indexOf("O")>= 0 && s_lowestDisplay.indexOf("A")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Display the lowest bid dollar amount.</td></tr>
<%		}
			if (s_lowestDisplay.indexOf("N")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Display a lowest bid notification.</td></tr>
<%		}
			if (extRfqsActive && s_auctionType.indexOf("O")>= 0 && rfqHeader.getExtendMinutes().compareTo(new BigDecimal(0)) > 0) { %>
						<tr><td class=browseRow height=12px nowrap>Extend Auction By <%=rfqHeader.getExtendMinutes()%> Minutes.</td></tr>
<%		}
		}%>
						</table>
						</div>
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
<%
int i_size = 0;
i_size = vendorList.size();
%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td width=100%>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td width=2% height=18px class=browseHdr align=center nowrap><a href="javascript: doSubmit('/rfq/rfq_suppliers.jsp', 'RfqRetrieve'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-supplier")%> width=20% height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-supplier", "Supplier")%></b></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-total")%> width=15% height=18px class=browseHdr align=right>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-total", "Total")%></b></td>
<%	if (extRfqsActive) {%>
							<td <%=HtmlWriter.isVisible(oid, "rfq-totalScore")%> width=15% height=18px class=browseHdr align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-totalScore", "Score")%></b></td>
<%	}%>
							<td <%=HtmlWriter.isVisible(oid, "rfq-paymentTerms")%> width=15% height=18px class=browseHdr align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-paymentTerms", "Terms")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-responseDate")%> width=15% height=18px class=browseHdr align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-responseDate", "Response Date")%></b></td>
<%	if (!extRfqsActive) {%>
							<td <%=HtmlWriter.isVisible(oid, "rfq-promiseDate")%> width=15% height=18px class=browseHdr align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-promiseDate", "Promise Date")%></b></td>
<%	}%>
					<td <%=HtmlWriter.isVisible(oid, "rfq-bidValidTo")%> width=15% height=18px class=browseHdr align=right>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-bidValidTo", "Bid Valid To")%></b></td>
					<%	if (i_size > 1) { %>
					<td width=3% height=18px class=browseHdr align=center><a href="javascript: toggleDetailsDisplay('supplierDetails', 'Details'); void(0);"><img id='supplierDetailsImg' valign='baseline 'src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a></td>
					<%	} %>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
	String s_vendorId = "";
	String s_bidResponse = "";

	if (vendorList != null)
	{
		for(i = 0;i < i_size; i++)
		{
			RfqVendor rfqVendor = (RfqVendor)vendorList.get(i);
			RfqVendorPK rfqVendorPK = rfqVendor.getComp_id();
			s_vendorId = rfqVendorPK.getVendorId();

			String vendorTerms = "";
			String dateResponseRecv = "";
			String	datePromised = "";
			String	bidValidTo = "";
			String	totalBid = "";
			String	totalScore = "";

			if (rfqVendor.getBidsEntered().equals("Y")) {
				vendorTerms = rfqVendor.getPaymentTerms();
				dateResponseRecv = HiltonUtility.getFormattedDate(rfqVendor.getDateResponseRecv(), oid, userDateFormat);
				datePromised = HiltonUtility.getFormattedDate(rfqVendor.getDatePromised(), oid, userDateFormat);
				bidValidTo = HiltonUtility.getFormattedDate(rfqVendor.getBidValidTo(), oid, userDateFormat);
				totalBid = "$" + String.valueOf(HiltonUtility.getFormattedDollar(rfqVendor.getBidTotal(), oid));

				if (HiltonUtility.isEmpty(rfqVendor.getEvaluationStatus()) || rfqVendor.getEvaluationStatus().equals(DocumentStatus.EVALUATION_INCOMPLETE)) {
					totalScore = "(Pending) " + String.valueOf(rfqVendor.getTotalScore());
				} else {
					totalScore = String.valueOf(rfqVendor.getTotalScore());
				}
			}
			else {
				totalBid = "No Bids Submitted";
			}

			if (i==1)
			{
%>
				<div id="supplierDetails" style="display:none;">
<%		} %>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=2% class=browseRow valign=top align=center>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-supplier")%> width=20% class=browseRow valign=top><a href="javascript: viewSupplier('<%=s_vendorId%>'); void(0);" title="Click here to View Supplier Details."><%=s_vendorId%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-total")%> width=15% class=browseRow valign=top align=right><%=totalBid%></td>
<%	if (extRfqsActive) {%>
					<td <%=HtmlWriter.isVisible(oid, "rfq-evaluationScore")%> width=15% class=browseRow valign=top align=right><%=totalScore%></td>
<%	}%>
					<td <%=HtmlWriter.isVisible(oid, "rfq-paymentTerms")%> width=15% class=browseRow valign=top align=right><%=vendorTerms%></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-responseDate")%> width=15% class=browseRow valign=top align=right><%=dateResponseRecv%></td>
<%	if (!extRfqsActive) {%>
					<td <%=HtmlWriter.isVisible(oid, "rfq-promiseDate")%> width=15% class=browseRow valign=top align=right><%=datePromised%></td>
<%	}%>
					<td <%=HtmlWriter.isVisible(oid, "rfq-bidValidTo")%> width=15% class=browseRow valign=top align=right><%=bidValidTo%></td>
					<td width=3% class=browseRow>&nbsp;</td>
				</tr>
				</table>
<%	} } %>
				</div>
<%	if (vendorList == null || vendorList.size() <= 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow>There are no suppliers associated with this solicitation.</td></tr>
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
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td width=100%>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td width=2% height=18px class=browseHdr><a href="javascript: doSubmit('/rfq/rfq_questions.jsp', 'RfqQuestionRetrieveByHeader'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
					<td width=53% height=18px class=browseHdr>&nbsp;<b>Supplier Question</b></td>
					<td width=25% height=18px class=browseHdr>&nbsp;<b>Response Type</b></td>
					<td width=20% height=18px class=browseHdr>&nbsp;<b>Rating Method</b></td>
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
					<td width=2% class=browseRow valign=top nowrap>&nbsp;<%=rfqQuestion.getSequence().setScale(0, BigDecimal.ROUND_HALF_UP)%></td>
					<td width=53% class=browseRow valign=top><%=s_question%></td>
					<td width=25% class=browseRow valign=top><%=s_responseType%></td>
					<td width=20% class=browseRow valign=top><%=s_ratingMethod%></td>

				</tr>
<%	}
	} %>
				</table>
<%	if (questionList == null || questionList.size() <= 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow>There are no supplier questions associated with this solicitation.</td></tr>
				</table>
<%	}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<%	}
		if (processMap.containsKey("HEADER_NOTES")) {
			List cmtList = (List) rfqHeader.getDocCommentList(); %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height=18px class=browseHdr><a href="javascript: doSubmit('/requests/req_notes.jsp', 'DocCommentRetrieveByLine'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a></td>
					<td width=66% height=18px class=browseHdr>&nbsp;<b>Comment</b></td>
<%		if (solicitationActive) {%>
					<td width=7% height=18px class=browseHdr align=center>&nbsp;<b>Post</b></td>
<%		} else {%>
					<td width=7% height=18px class=browseHdr align=center>&nbsp;</td>
<%		}%>
					<td width=7% height=18px class=browseHdr align=center>&nbsp;<b>Print</b></td>
					<td width=7% height=18px class=browseHdr align=center>&nbsp;<b>Bold</b></td>
					<td width=10% height=18px class=browseHdr align=center>&nbsp;<b>Placement</b></td>
					<%	if (cmtList != null && cmtList.size() > 1) {%>
					<td width=2% height=18px class=browseHdr nowrap><a href="javascript: toggleDetailsDisplay('commentDetails', 'Details'); void(0);"><img id='commentDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Notes"></a></td>
					<%	}%>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
	if (cmtList != null) {
		for(int ic = 0; ic < cmtList.size(); ic++) {
			DocComment docComment = (DocComment) cmtList.get(ic);
			DocText docText = docComment.getDocText();
	  		String s_cmt_title = docComment.getCommentTitle();
			String s_cmt_post = docComment.getCommentWebpost();
			String s_cmt_print = docComment.getCommentPrint();
			String s_cmt_bold = docComment.getCommentBold();
			String s_cmt_place = docComment.getCommentPlace();
			String s_cmt_text = docText.getStdText();

			if (s_cmt_place.equals("B")) {
				s_cmt_place = "Before";
			} else {
				s_cmt_place = "After";
			}
			if (HiltonUtility.isEmpty(s_cmt_post)) {
				s_cmt_post = "N";
			}
			if (HiltonUtility.isEmpty(s_cmt_print)) {
				s_cmt_print = "N";
			}
			if (HiltonUtility.isEmpty(s_cmt_bold)) {
				s_cmt_bold = "N";
			}
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=67% class=browseRow>&nbsp;<%=s_cmt_title%></td>
<%		if (solicitationActive) {%>
					<td width=7% class=browseRow align=center valign=top><%=s_cmt_post%></td>
<%		} else {%>
					<td width=7% class=browseRow align=center valign=top>&nbsp;</td>
<%		} %>
					<td width=7% class=browseRow align=center valign=top><%=s_cmt_print%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_cmt_bold%></td>
					<td width=10% class=browseRow align=center valign=top><%=s_cmt_place%></td>
					<td width=2% class=browseRow align=center valign=top>&nbsp;</td>
				</tr>
				</table>
				<div id="commentDetails" style="display:none;">
				<table>
				<tr>
					<td width=75px>&nbsp;</td>
					<td width=100%><%=s_cmt_text%></td>
				</tr>
				</table>
				</div>
<% 	}
	}
	if (cmtList == null || cmtList.size() == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow>There are no comments associated with this solicitation.</td></tr>
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
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td width=3% class=browseHdr height=18px nowrap align=center><a href="javascript: doSubmit('/rfq/rfq_items.jsp', 'RfqHeaderUpdateById;RfqLineRetrieveByHeader'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-itemNumber")%> width=13% class=browseHdr height=18px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-itemNumber", "Item/Part #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-commodity")%> width=16% class=browseHdr height=18px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-commodity", "Commodity")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-lineStatus")%> width=16% class=browseHdr height=18px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-lineStatus", "Line Status")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-quantity")%> width=9% class=browseHdr height=18px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-quantity", "Quantity")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-uom")%> width=6% class=browseHdr height=18px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-uom", "UOM")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-supplier")%> width=11% class=browseHdr height=18px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-supplier", "Supplier")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-unitCost")%> width=9% class=browseHdr height=18px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-unitCost", "Unit Cost")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-extendedCost")%> width=15% class=browseHdr height=18px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-extendedCost", "Extended Cost")%></td>
					<%	if (i_lineCount > 1) {%>
					<td width=2% class=browseHdr height=18px nowrap align=center><a href="javascript: toggleDetailsDisplay('itemDetails', 'Items'); void(0);"><img id='itemDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;
					<%	} %>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<tsa:hidden name="RfqLine_icRfqLine" value=""/>
				<table id=itemRow border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td>
<% 	//Rfq Lines
	if (lineList != null) {
		for(i=0;i<lineList.size();i++) {
			RfqLine rfqLine = (RfqLine)lineList.get(i);
			BigDecimal bd_icRfqLine = rfqLine.getIcRfqLine();
			BigDecimal bd_umFactor = rfqLine.getUmFactor();

			if (bd_umFactor.compareTo(new BigDecimal(0)) <= 0) {
				bd_umFactor = new BigDecimal(1);
			}
			List bidList = (List) rfqLine.getRfqBidList();
			if (i == 1) {
%>
						<div id="itemDetails" style="display:none;">
<%		}%>
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
						<tr>
							<td width=63% valign=top>
								<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
								<tr>
									<td width=3% class=browseRow nowrap align=center><a href="javascript: viewItem(<%=i%>); void(0);" onMouseOver="highlightItem(0);" onMouseOut="removeItemHighlight(0);"><%=i+1%></a></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-itemNumber")%> width=13% class=browseRow nowrap align=center><%=rfqLine.getItemNumber()%><tsa:hidden id="icRfqLine_<%=i%>" name="icRfqLine_<%=i%>" value="<%=bd_icRfqLine%>"/></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-commodity")%> width=16% class=browseRow nowrap align=center><%=rfqLine.getCommodity()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-lineStatus")%> width=16% class=browseRow nowrap align=center><%=DocumentStatus.toString(rfqLine.getStatus(), oid)%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-quantity")%> width=9% class=browseRow nowrap align=center><%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), oid)%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-uom")%> width=6% class=browseRow nowrap align=right><%=rfqLine.getUmCode()%></td>
								</tr>
								<tr>
									<td class=browseRow nowrap align=center>&nbsp;</td>
									<td colspan=5 class=browseRow>
										<%=rfqLine.getDescription()%><% if (!HiltonUtility.isEmpty(rfqLine.getCatalogId())) { %>&nbsp;&nbsp;(<b>Catalog:&nbsp;</b><%=rfqLine.getCatalogId()%>)<%	} %>
									</td>
								</tr>
<%		List requisitionLineInfoList = (List) rfqLine.getRequisitionLineInfoList();
			if (requisitionLineInfoList != null)
			{
				for (int ix = 0; ix < requisitionLineInfoList.size(); ix++)
				{
					ReferenceInfo requisitionInfo = (ReferenceInfo) requisitionLineInfoList.get(ix);
%>
								<tr <%=HtmlWriter.isVisible(oid, "rfq-requisitionNumber")%>>
									<td>&nbsp;</td>
									<td nowrap align=right valign=top><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-requisitionNumber", "Requisition #")%>:</td>
									<td nowrap valign=top><a href="javascript: reqPreview('<%=requisitionInfo.getIcHeader()%>'); void(0);" title="View Requisition"><%=requisitionInfo.getFormNumber()%></a></td>
								</tr>
<%			}
			}

			List poLineInfoList = (List) rfqLine.getPoLineInfoList();
			if (poLineInfoList != null)
			{
				for (int ix = 0; ix < poLineInfoList.size(); ix++)
				{
					ReferenceInfo poInfo = (ReferenceInfo) poLineInfoList.get(ix);
%>
								<tr <%=HtmlWriter.isVisible(oid, "rfq-poNumber")%>>
									<td>&nbsp;</td>
									<td nowrap align=right valign=top><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-poNumber", "Order #")%>:</td>
									<td valign=top>
										<a href="javascript: orderPreview('<%=poInfo.getIcHeader()%>'); void(0);" title="View Order"><%=poInfo.getFormNumber()%></a>
<%				if ((poInfo.getReleaseNumber()).compareTo(new BigDecimal(0)) > 0) { %>
								&nbsp;/&nbsp;<%=poInfo.getReleaseNumber()%>
<%				} %>
								</tr>
<%				}
			}
%>
								</table>
							</td>
							<td width=37% valign=top>

<%		if (bidList != null) {
				for(b=0;b<bidList.size();b++)
				{
					RfqBid rfqBid = (RfqBid)bidList.get(b);
					RfqBidPK rfqBidPK = (RfqBidPK) rfqBid.getComp_id();
					String s_bidVendorId = rfqBidPK.getVendorId();
					String s_bidUmCode = rfqBid.getUmCode();
					if (s_bidUmCode==null){s_bidUmCode = "EA";}
					BigDecimal b_bidQuantity = HiltonUtility.getFormattedQuantity(rfqBid.getQuantity(), oid);
					BigDecimal bd_bidUnitPrice = HiltonUtility.getFormattedPrice(rfqBid.getUnitPrice(), oid);
					String s_bidUnitPrice = bd_bidUnitPrice.toString();
					String s_bidCode = rfqBid.getBidCode();
					if (s_bidCode.equalsIgnoreCase("NC"))
					{
						s_bidUnitPrice = "No Charge";
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

					BigDecimal b_bidExtendedPrice = new BigDecimal(0);
					b_bidExtendedPrice = ( (b_bidQuantity.multiply(bd_bidUnitPrice)).multiply(bd_umFactor) ).setScale(Integer.valueOf(s_dollar_decimals).intValue(),BigDecimal.ROUND_HALF_UP);
%>
								<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
									<tr>
										<td <%=HtmlWriter.isVisible(oid, "rfq-supplier")%> width=11% class=browseRow valign=top align=center><%=s_bidVendorId%></td>
										<td <%=HtmlWriter.isVisible(oid, "rfq-unitCost")%> width=9% class=browseRow align=center>
			<%	if (s_bidUnitPrice.equals("See Notes")) {%>
											<a href="javascript: viewNotes('<%=bd_icRfqLine%>', '<%=s_bidVendorId%>'); void(0);"><%=s_bidUnitPrice%></a>
			<%	} else { %>
											<%=s_bidUnitPrice%>
			<%	} %>
										</td>
										<td <%=HtmlWriter.isVisible(oid, "rfq-extendedCost")%> width=15% class=browseRow align=center><%=b_bidExtendedPrice%></td>
										<td width=2% class=browseRow>&nbsp;</td>
									</tr>
								</table>
<%			}
			} %>
							</td>
						</tr>
						</table>
<%	}
	}
		if (lineList != null && lineList.size() > 1) {%>
						</div>
<%	}%>
					</td>
				</tr>
				</table>
<%	if (lineList == null || lineList.size() <= 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow>There are no items associated with this solicitation.</td></tr>
				</table>
<%	}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<%	}
		if (propertiesManager.isModuleActive("DOCUMENTS")) {%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height=18px class=browseHdr nowrap><a href="javascript: doSubmit('/rfq/rfq_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></a></td>
					<td width=68% height=18px class=browseHdr>&nbsp;<b>Attachment</b></td>
					<td width=10% height=18px class=browseHdr align=center>&nbsp;<b>Print</b></td>
<%		if (solicitationActive) {%>
					<td width=10% height=18px class=browseHdr align=center>&nbsp;<b>Post</b></td>
<%		} else {%>
					<td width=10% height=18px class=browseHdr align=center>&nbsp;</td>
<%		}%>
					<td width=10% height=18px class=browseHdr align=center>&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
		List atcList = (List) rfqHeader.getDocAttachmentList();
		int	ai = 0;
		if (atcList != null)
		{
			for(i = 0; i < atcList.size(); i++)
			{
				DocAttachment docAttachment = (DocAttachment) atcList.get(i);
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
							<td width=25px>
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
									<a href="javascript: openDocument(<%=i%>); void(0);"><%=docAttachment.getDocTitle()%></a>
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
<%		}
		}
		if (ai == 0) {%>
					<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
					<tr><td class=browseRow>There are no attachments associated with this solicitation.</td></tr>
					</table>
<%	}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	}%>

<br>

<%	if (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENAMENDMENT) == 0) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%		if (s_rfqNumber == null || s_rfqNumber.equals("N/A")) { %>
		<td width=50% align=center><a href="javascript: rfqSave(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
<%		} else if (!(s_rfqType.equals("PR") && s_webpost.equals("N"))) { %>
		<td width=50% align=center><a href="javascript: rfqForward(); void(0);"><img class=button src="<%=contextPath%>/images/button_forward.gif" border=0 alt="Forward"></a></td>
<%		} %>
		<td width=50% align=center><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Main Menu"></a></td>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var rfqnumber = "<%=s_rfqNumber%>";
	var fiscalyear = "<%=s_fiscalYear%>";
	var currentpage = "<%=s_current_page%>";
	var currentprocessmethod = "RfqRetrieve";
	var synopsisEmpty = <%=HiltonUtility.isEmpty(rfqHeader.getRfqDescription())%>;
	var webpost = "<%=rfqHeader.getWebpost()%>";
	var bidAccess = "<%=s_bidAccess%>";
	var vendors = <%=vendorList.size()%>;
	var solicitationActive = "<%=propertiesManager.getProperty("RFQ OPTIONS", "SOLICITATIONS", "N")%>";
	var auctionStartDate="<%=rfqHeader.getAuctionStartDate()%>";
	var auctionEndDate="<%=rfqHeader.getAuctionEndDate()%>";
	var auctionStartTime="<%=rfqHeader.getAuctionStartTime()%>";
	var auctionEndTime="<%=rfqHeader.getAuctionEndTime()%>";

<%	if (!HiltonUtility.isEmpty(errorMsg)) {%>
	alert("<%=errorMsg%>");
<%	}%>

	function toggleDetailsDisplay(areaName, type) {
		var myAreas = document.getElementsByName(areaName);
		var myArea;
		var myImg = document.getElementById(areaName + "Img");
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
			myImg.src = "<%=contextPath%>/images/arrows_down.gif";
			myImg.alt = "View" + type;
		}
		else {
			myImg.src = "<%=contextPath%>/images/arrows_up.gif";
			myImg.alt = "Hide " + type;
		}
	}

	function switchView()
	{
		frm.viewType.value = "WIZARD";
		doSubmit('/rfq/rfq_review.jsp', 'RfqRetrieve');
	}

// End Hide script -->
</SCRIPT>
