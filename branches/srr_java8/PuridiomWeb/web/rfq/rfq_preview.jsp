<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<%
	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	int i;
	BigDecimal b_icRfqHeader = rfqHeader.getIcRfqHeader();
	String s_rfqStatus = rfqHeader.getStatus();
	String s_rfqNumber = rfqHeader.getRfqNumber();
	String s_rfqAmendment = rfqHeader.getRfqAmendment();

	//General Information
	String s_buyer = rfqHeader.getBuyer();
	String s_fiscalYear = rfqHeader.getFiscalYear();
	String s_currency = rfqHeader.getCurrencyCode();

	List lineList = (List) rfqHeader.getRfqLineList();
	int	i_lineCount = 0;
	if (lineList!=null){
		i_lineCount = lineList.size();
	}
	List vendorList = (List) rfqHeader.getRfqVendorList();

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
%>

<%@ include file="/rfq/rfq_process_steps.jsp" %>

<tsa:hidden name="formtype" value="RFQ"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=HiltonUtility.ckNull(rfqHeader.getRfqNumber())%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="${esapi:encodeForHTMLAttribute(rfqHeader.rfqType)}"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="RfqBid_icRfqHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="RfqQuestion_icRfqHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="RfqVendor_icRfqHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="RfqVendor_vendorId" value=""/>
<tsa:hidden name="lineCount" value="<%=i_lineCount%>"/>

<table width=660px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Solicitation Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/rfq/rfq_status_title.jsp" %>
		<table cellpadding="0" cellspacing="0" border=0>
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

<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<td width=660px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=660px>
		<tr>
<%	if (processMap.containsKey("HEADER_GENERAL_INFO"))	{ %>
			<td width=50% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "general_information", "General Information")%></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-buyerName")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-buyerName", "Buyer Name")%>:</b>&nbsp;</td>
							<td nowrap><%=UserManager.getInstance().getUser(oid, s_buyer).getDisplayName()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-solicitationDate")%>>
							<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-solicitationDate", "Solicitation Date")%>:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getFormattedDate(rfqHeader.getRfqDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-fiscalYear")%>>
							<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-fiscalYear", "Fiscal Year")%>:&nbsp;</b></td>
							<td nowrap><%=s_fiscalYear%></td>
						</tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-dueDate")%>>
							<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-dueDate", "Due Date")%>:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getFormattedDate(rfqHeader.getDueDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-currency")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-currency", "Currency")%>:&nbsp;</b></td>
							<td nowrap><%=s_currency%></td>
						</tr>
						<tsa:tr field="rfq-estCost" >
							<tsa:td cssClass="processOn" field="rfq-estCost" noWrap="nowrap" align="right"><b><tsa:label labelName="rfq-estCost" defaultString="Total" checkRequired="false" />:</b></tsa:td>
							<tsa:td cssClass="browseRow" field="rfq-estCost" noWrap="nowrap" align="left"><%=HiltonUtility.getFormattedDollar(rfqHeader.getEstimatedCost(), oid)%></tsa:td>
							<td class=browseRow nowrap align=right>&nbsp;</td>
						</tsa:tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
<%	}
		if (processMap.containsKey("HEADER_SHIPPING"))
		{
				//Shipping Information
			String s_shipToCode = rfqHeader.getShipToCode();
			if (s_shipToCode==null){s_shipToCode = "";}
			Address address = rfqHeader.getShipToAddress();
			if(address==null){
				address = new Address();
			}
			String s_addressLine1 = address.getAddressLine1();
			if (s_addressLine1==null){s_addressLine1 = "";}
			String s_addressLine2 = address.getAddressLine2();
			String s_addressLine3 = address.getAddressLine3();
			String s_addressLine4 = address.getAddressLine4();
			String s_city = address.getCity();
			if (s_city==null){s_city = "";}
			String s_state = address.getState();
			if (s_state==null){s_state = "";}
			String s_postalCode = address.getPostalCode();
			if (s_postalCode==null){s_postalCode = "";}
			String s_country = address.getCountry();
			if (s_country==null){s_country = "";}
			String s_attention = rfqHeader.getShipToContact();
			if (s_attention==null){s_attention = "";}
			String s_priority = rfqHeader.getPriorityCode();
			if (s_priority==null){s_priority = "";}
			String s_shipVia = rfqHeader.getShippingCode();
			if (s_shipVia==null){s_shipVia = "";}
			String s_routing = rfqHeader.getRouting();
			if (s_routing==null){s_routing = "";}
%>
			<td width=50% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "shipping_information", "Shipping Information")%></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shipToCode")%>><td class=browseRow height=12px nowrap><b><%=s_shipToCode%></b></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shp-addressLine1")%>><td class=browseRow height=12px nowrap><%=s_addressLine1%></td></tr>
						</table>

						<table id=shippingRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (!HiltonUtility.isEmpty(s_addressLine2)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shp-addressLine2")%>><td class=browseRow height=12px nowrap><%=s_addressLine2%></td></tr>
<%	}
		if(!HiltonUtility.isEmpty(s_addressLine3)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shp-addressLine3")%>><td class=browseRow height=12px nowrap><%=s_addressLine3%></td></tr>
<%	}
		if(!HiltonUtility.isEmpty(s_addressLine4)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shp-addressLine4")%>><td class=browseRow height=12px nowrap><%=s_addressLine4%></td></tr>
<%	} %>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shp-city")%>><td class=browseRow height=12px nowrap><%=s_city%></td></tr>
						<tr><td class=browseRow height=12px nowrap><%=s_state%> <%=s_postalCode%>  <%=s_country%></td></tr>
						</table>
						</div>
<%	if (!HiltonUtility.isEmpty(rfqHeader.getShipToContact())) { %>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shp-attention")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-shp-attention", "Attention")%>: <%=s_attention%></td></tr>
						</table>
<%	} %>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-requiredDate")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-requiredDate", "Required By")%>: <%=HiltonUtility.getFormattedDate(rfqHeader.getRequiredDate(), oid)%></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-priority")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-priority", "Priority")%>: <%=s_priority%></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shipVia")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-shipVia", "Ship Via")%>: <%=s_shipVia%></td></tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
<%	} %>
		</tr>
		<tr><td colspan=2>&nbsp;</td></tr>
<%
	if (processMap.containsKey("WEBPOST_OPTIONS"))
	{
		//Webpost Options
		String s_webpost = rfqHeader.getWebpost();
		if (s_webpost==null){s_webpost = "N";}
		String s_bidAccess = rfqHeader.getBidAccess();
		if (s_bidAccess==null){s_bidAccess = "U";}
		String s_auctionType = rfqHeader.getAuctionType();
		if (s_auctionType==null){s_auctionType = "O";}
		String s_bidItemOptions = rfqHeader.getBidItemOptions();
		if (s_bidItemOptions==null){s_bidItemOptions = "N";}
		String s_lowBidReq = rfqHeader.getLowestBidReq();
		if (s_lowBidReq==null){s_lowBidReq = "N";}
		String s_lowBidSource = rfqHeader.getLowestBidSource();
		if (s_lowBidSource==null){s_lowBidSource = "L";}
		String s_lowestDisplay = rfqHeader.getLowestDisplay();
		if (s_lowestDisplay==null){s_lowestDisplay = "A";}
%>
		<tr>
			<td width=50% align=center valign=top>
				<table id=webpostTable border=0 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;Webpost Options</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow id=webpostRows>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%
	if (s_webpost.indexOf("N")>= 0)
	{ %>
						<tr><td class=browseRow height=12px nowrap>Non-Web Solicitation</td></tr>
<%	}
	if (s_webpost.indexOf("D")>= 0 || s_webpost.indexOf("V")>= 0 || s_webpost.indexOf("VB")>= 0 || s_webpost.indexOf("DB")>= 0)
	{ %>
						<tr><td class=browseRow height=12px nowrap>Post Solicitation</td></tr>
<%	}
	if (s_webpost.indexOf("VB")>= 0 || s_webpost.indexOf("DB")>= 0)
	{ %>
						<tr><td class=browseRow height=12px nowrap>Online Bidding</td></tr>
<%	}
	if (s_webpost.indexOf("D")>= 0 || s_webpost.indexOf("DB")>= 0)
	{ %>
						<tr><td class=browseRow height=12px nowrap>Downloadable</td></tr>
<%	} %>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%
	if (s_bidAccess.indexOf("U")>= 0)
	{ %>
						<tr><td class=browseRow height=12px nowrap>Supplier Access: Unrestricted</td></tr>
<%	}
	if (s_bidAccess.indexOf("I")>= 0)
	{ %>
						<tr><td class=browseRow height=12px nowrap>Supplier Access: By Invitation Only</td></tr>
<%	}
	if (s_bidAccess.indexOf("R")>= 0)
	{ %>
						<tr><td class=browseRow height=12px nowrap>Supplier Access: Restricted Suppliers</td></tr>
<%	}
	if (s_auctionType.indexOf("S")>= 0)
	{ %>
						<tr><td class=browseRow height=12px nowrap>Sealed Bid</td></tr>
<%	}
	if (s_auctionType.indexOf("O")>= 0)
	{ %>
						<tr><td class=browseRow height=12px nowrap>Open Auction</td></tr>
<%	}
	if (s_bidItemOptions.indexOf("N")>= 0)
	{ %>
						<tr><td class=browseRow height=12px nowrap>Not Required to Bid All Items</td></tr>
<%	}
	if (s_bidItemOptions.indexOf("A")>= 0)
	{ %>
						<tr><td class=browseRow height=12px nowrap>Must Bid on All Items</td></tr>
<%	}
	if (s_lowBidReq.indexOf("Y")>= 0){
	%>
						<tr><td class=browseRow height=12px nowrap>Must Enter Bids Before Viewing Lowest Bid</td></tr>
<%	}
	if (s_lowBidSource.indexOf("L")>= 0){
	%>
						<tr><td class=browseRow height=12px nowrap>Base Lowest Bids on: Line Item Amounts</td></tr>
<%	}
	if (s_lowBidSource.indexOf("S")>= 0){
	%>
						<tr><td class=browseRow height=12px nowrap>Base Lowest Bids on: Subtotal Amount</td></tr>
<%	}
	if (s_lowestDisplay.indexOf("A")>= 0){
	%>
						<tr><td class=browseRow height=12px nowrap>Display the lowest bid dollar amount.</td></tr>
<%	}
	if (s_lowestDisplay.indexOf("N")>= 0){
	%>
						<tr><td class=browseRow height=12px nowrap>Lowest Bid Display Option: Display a lowest bid notification.</td></tr>
<%	} %>
						</table>
					</td>
				</tr>
				</table>
			</td>
<%	}
		if (processMap.containsKey("HEADER_REQUEST_INFO"))
		{
			String s_reqNumber = rfqHeader.getRequisitionNumber();
			if (s_reqNumber==null){s_reqNumber = "";}
			String s_reqAttention = rfqHeader.getBillToContact();
			if (s_reqAttention==null){s_reqAttention = "";}
			String s_taxCode = rfqHeader.getTaxCode();
			if (s_taxCode==null){s_taxCode = "";}
%>
			<td width=50% align=center valign=top>
				<table id=requestTable border=0 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=320px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;Request Information</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow id=requestRows>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-requisitionNumber")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-requisitionNumber", "Requisition #")%>:&nbsp;</b></td>
							<td nowrap><%=headerEncoder.encodeForHTML(rfqHeader.getRequisitionNumber())%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-requisitioner")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-requisitioner", "Requisitioner")%>:&nbsp;</b></td>
							<td nowrap><%=rfqHeader.getRequisitionerCode()%></td>
						</tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-department")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-department", "Department")%>:&nbsp;</b></td>
							<td nowrap><%=rfqHeader.getDepartmentCode()%></td>
						</tr>
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

<%	if (processMap.containsKey("HEADER_SUPPLIERS")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<td align=center width=665px>
		<table border=0 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
				<tr>
					<td width=100%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-supplier")%> width=20% height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-supplier", "Supplier")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-total")%> width=15% height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-total", "Total")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-paymentTerms")%> width=10% height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-paymentTerms", "Terms")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-responseDate")%> width=15% height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-responseDate", "Response Date")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-promiseDate")%> width=15% height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-promiseDate", "Promise Date")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-bidValidTo")%> width=15% height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-bidValidTo", "Bid Valid To")%></b></td>
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
	String s_vendorId = "";
	String s_bidResponse = "";


	int i_size = 0;
	if (vendorList != null)
	{
		i_size = vendorList.size();

		for(i = 0;i < i_size; i++)
		{
			RfqVendor rfqVendor = (RfqVendor)vendorList.get(i);
			RfqVendorPK rfqVendorPK = rfqVendor.getComp_id();
			s_vendorId = rfqVendorPK.getVendorId();
			String s_vendorName = VendorManager.getInstance().getVendorName(oid, s_vendorId);
			if (s_vendorName.length() > 20)
			{
				s_vendorName = s_vendorName.substring(0, 20);
			}

			Date d_responseDate = rfqVendor.getDateResponseRecv();
			Date d_promiseDate = rfqVendor.getDatePromised();
			Date d_bidValidTo = rfqVendor.getBidValidTo();
			String s_responseDate = "";
			String s_promiseDate = "";
			String s_bidValidTo = "";

%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<%if(oid.equalsIgnoreCase("WPC08P")){ %>
						<td <%=HtmlWriter.isVisible(oid, "rfq-supplier")%> width=20% class=browseRow valign=top><%=s_vendorName%></a></td>
					<%}
					else{%>
						<td <%=HtmlWriter.isVisible(oid, "rfq-supplier")%> width=20% class=browseRow valign=top><%=s_vendorId%></td>
					<%}%>
					<td <%=HtmlWriter.isVisible(oid, "rfq-total")%> width=15% class=browseRow valign=top>$<%=HiltonUtility.getFormattedDollar(rfqVendor.getBidTotal(), oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-paymentTerms")%> width=10% class=browseRow valign=top><%=rfqVendor.getPaymentTerms()%></td>

					<%if(d_responseDate != null){ %>
					<td <%=HtmlWriter.isVisible(oid, "rfq-responseDate")%> width=15% class=browseRow valign=top><%= d_responseDate %></td>
					<%}else{ %>
					<td <%=HtmlWriter.isVisible(oid, "rfq-responseDate")%> width=15% class=browseRow valign=top><%= s_responseDate %></td>
					<%} %>

					<%if(d_promiseDate != null){ %>
					<td <%=HtmlWriter.isVisible(oid, "rfq-promiseDate")%> width=15% class=browseRow valign=top><%= d_promiseDate %></td>
					<%}else{ %>
					<td <%=HtmlWriter.isVisible(oid, "rfq-promiseDate")%> width=15% class=browseRow valign=top><%= s_promiseDate %></td>
					<%} %>

					<%if(d_bidValidTo != null){ %>
					<td <%=HtmlWriter.isVisible(oid, "rfq-bidValidTo")%> width=15% class=browseRow valign=top><%= d_bidValidTo %> </td>
					<%}else{ %>
					<td <%=HtmlWriter.isVisible(oid, "rfq-bidValidTo")%> width=15% class=browseRow valign=top><%= s_bidValidTo %> </td>
					<%} %>

				</tr>
				</table>
<%	} } %>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	} %>

<br>

<%	if (processMap.containsKey("HEADER_QUESTIONS")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<td align=center width=660px>
		<table border=0 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
				<tr>
					<td width=100%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td width=5% height=18px class=browseHdr>&nbsp;</td>
							<td width=75% height=18px class=browseHdr>&nbsp;<b>Supplier Question</b></td>
							<td width=20% height=18px class=browseHdr>&nbsp;<b>Response Type</b></td>
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

	if (questionList != null)
	{
		for(i = 0; i < questionList.size(); i++)
		{
			RfqQuestion rfqQuestion = (RfqQuestion) questionList.get(i);
			RfqQuestionPK rfqQuestionPK = rfqQuestion.getComp_id();
			BigDecimal b_icQuestion = rfqQuestionPK.getIcQuestion();

			s_question = rfqQuestion.getQuestionText();
			if (s_question==null){s_question = "";}
			s_responseType = rfqQuestion.getResponseType();
			if (s_responseType.equalsIgnoreCase("YN"))
			{
				s_responseType = "Yes/No";
			}
			else if (s_responseType.equalsIgnoreCase("TEXT"))
			{
				s_responseType = "Open Text";
			}
%>

				<tr>
					<td width=5% class=browseRow valign=top nowrap>&nbsp;&nbsp;<%=Utility.getBigDecimalFormatted(rfqQuestion.getSequence(), 0)%></td>
					<td width=75% class=browseRow valign=top><%=s_question%></td>
					<td width=20% class=browseRow valign=top><%=s_responseType%></td>
				</tr>
<%	}
	} %>

				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	} %>

<br>

<%	if (processMap.containsKey("HEADER_NOTES")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<td align=center width=660px>
		<table border=0 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
				<tr>
					<td width=100%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td width=75% height=18px class=browseHdr>&nbsp;<b>Comment</b></td>
							<td width=8% height=18px class=browseHdr align=center>&nbsp;<b>Print</b></td>
							<td width=7% height=18px class=browseHdr align=center>&nbsp;<b>Bold</b></td>
							<td width=10% height=18px class=browseHdr align=center>&nbsp;<b>Placement</b></td>
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
	//Notes & Attachments
	List cmtList = (List) rfqHeader.getDocCommentList();
	String s_placeString = "";
	if (cmtList!=null)
	{
		for(i=0;i<cmtList.size();i++)
		{
			DocComment docComment = (DocComment)cmtList.get(i);
			DocCommentPK docCommentPK = docComment.getComp_id();
			BigDecimal b_icCmtHeader = docCommentPK.getIcHeader();
			BigDecimal b_icCmtLine = docCommentPK.getIcLine();
			String	s_commentId = docComment.getCommentId();
			String	s_cmtTitle = docComment.getCommentTitle();
			if (s_cmtTitle==null){s_cmtTitle = "n/a";}
			String	s_cmtPrint = docComment.getCommentPrint();
			if (s_cmtPrint==null){s_cmtPrint = "N";}
			String	s_cmtBold = docComment.getCommentBold();
			if (s_cmtBold==null){s_cmtBold = "N";}
			String	s_cmtPlace = docComment.getCommentPlace();
			if (s_cmtPlace==null){s_cmtPlace = "A";}
			if (s_cmtPlace.equals("A")){
				s_placeString = "After";
			}
			else if (s_cmtPlace.equals("B")){
				s_placeString = "Before";
			}
			DocText docText = docComment.getDocText();
			String s_cmt_text = docText.getStdText();
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=75% class=browseRow>
						<%if(s_cmtBold.equals("Y")){%><b><%}%><%=s_cmtTitle%><%if(s_cmtBold.equals("Y")){%></b><%}%>
					</td>
					<td width=8% class=browseRow align=center valign=top><%=s_cmtPrint%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_cmtBold%></td>
					<td width=10% class=browseRow align=center valign=top><%=s_placeString%></td>
				</tr>
				</table>

				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
				<tsa:tr>
					<tsa:td width="75px">&nbsp;</tsa:td>
					<tsa:td width="100%">
<%				if (s_cmtBold.equals("Y")) 	{ %>	<b><% } %><%=s_cmt_text%><% if (s_cmtBold.equals("Y")) { %></b><% } %>
					</tsa:td>
				</tsa:tr>
				</table>
<%	}
	}

		List atcList = (List) rfqHeader.getDocAttachmentList();
		if (atcList != null && atcList.size() > 0)
		{	%>

				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td colspan=3><hr size=0></td></tr>
				<tr>
					<td width=30% class=browseRow>&nbsp;<b>Attachment Title</b></td>
					<td width=60% class=browseRow>&nbsp;<b>Attachment Description</b></td>
					<td width=10% class=browseRow>&nbsp;</td>
				</tr>
				<tsa:tr>
					<tsa:td>
<%
				for(int ix = 0; ix < atcList.size(); ix++)
				{
					DocAttachment docAttachment = (DocAttachment) atcList.get(ix);
					String s_atc_title = docAttachment.getDocTitle();
					String filename = docAttachment.getDocFilename();
					String ext = filename.substring(filename.lastIndexOf(".") + 1);
%>
						<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
						<tsa:tr>
							<td width=25px align=center valign=middle>
<%		if (ext.equalsIgnoreCase("DOC")) {%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
							</td>
							<tsa:td cssClass="browseRow">
								<a href="javascript: openDocument(<%=ix%>); void(0);" title="Click here to open the <%=docAttachment.getDocTitle()%> attachment."><%=s_atc_title%></a>
								<tsa:hidden name="docFilename" value="<%=filename%>"/>
							</tsa:td>
						</tsa:tr>
						</table>
<%				}%>
					</tsa:td>
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

<%	if (processMap.containsKey("SHOPCART")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<td align=center width=660px>
		<table id=itemTable border=0 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=650px class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
							<tr>
								<td <%=HtmlWriter.isVisible(oid, "rfq-lineNumber")%> width=5% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-lineNumber", "Line #")%></td>
								<td <%=HtmlWriter.isVisible(oid, "rfq-itemNumber")%> width=14% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-itemNumber", "Item/Part #")%></td>
								<td <%=HtmlWriter.isVisible(oid, "rfq-commodity")%> width=11% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-commodity", "Commodity")%></td>
								<td <%=HtmlWriter.isVisible(oid, "rfq-lineStatus")%> width=11% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-lineStatus", "Line Status")%></td>
								<td <%=HtmlWriter.isVisible(oid, "rfq-quantity")%> width=11% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-quantity", "Quantity")%></td>
								<td <%=HtmlWriter.isVisible(oid, "rfq-uom")%> width=8% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-uom", "UOM")%>&nbsp;&nbsp;&nbsp;&nbsp;|</td>
								<td <%=HtmlWriter.isVisible(oid, "rfq-supplier")%> width=14% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-supplier", "Supplier")%></td>
								<td <%=HtmlWriter.isVisible(oid, "rfq-unitCost")%> width=13% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-unitCost", "Unit Cost")%></td>
								<td <%=HtmlWriter.isVisible(oid, "rfq-extendedCost")%> width=13% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-extendedCost", "Extended Cost")%></td>
							</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<table id=itemRow border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td>
<% 	//Rfq Lines
	if (lineList!=null){
		for(i=0;i<lineList.size();i++)
		{
		RfqLine rfqLine = (RfqLine)lineList.get(i);
		BigDecimal b_icRfqLine = rfqLine.getIcRfqLine();
		BigDecimal bd_umFactor = rfqLine.getUmFactor();
		if (bd_umFactor.compareTo(new BigDecimal(0)) <= 0)
		{
			bd_umFactor = new BigDecimal(1);
		}
		List bidList = (List) rfqLine.getRfqBidList();
%>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
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
							<td valign=top width=60%>
								<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow valign=top>
								<tr>
									<td <%=HtmlWriter.isVisible(oid, "rfq-lineNumber")%> width=7% class=browseRow nowrap align=right><%=i+1%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-itemNumber")%> width=15% class=browseRow nowrap><%=rfqLine.getItemNumber()%><tsa:hidden id="icRfqLine_<%=i%>" name="icRfqLine_<%=i%>" value="<%=b_icRfqLine%>"/></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-commodity")%> width=12% class=browseRow nowrap><%=rfqLine.getCommodity()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-lineStatus")%> width=11% class=browseRow nowrap><%=DocumentStatus.toString(rfqLine.getStatus())%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-quantity")%> width=12% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), oid)%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-uom")%> width=8% class=browseRow nowrap><%=rfqLine.getUmCode()%></td>
								</tr>
								<tr>
									<td class=browseRow nowrap>&nbsp;</td>
									<td colspan=5 class=browseRow><%=rfqLine.getDescription()%></td>
								</tr>
								</table>
							</td>
							<td width=40% valign=top>

<%	if (bidList != null)
		{
			for(int b = 0; b < bidList.size(); b++)
			{
				RfqBid rfqBid = (RfqBid)bidList.get(b);
				RfqBidPK rfqBidPK = (RfqBidPK) rfqBid.getComp_id();
				String s_bidVendorId = rfqBidPK.getVendorId();
				String s_bidUmCode = rfqBid.getUmCode();
				if (s_bidUmCode==null){s_bidUmCode = "EA";}
				BigDecimal b_bidQuantity = HiltonUtility.getFormattedQuantity(rfqBid.getQuantity(), oid);
				BigDecimal b_bidUnitPrice = HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid);

				BigDecimal b_bidExtendedPrice = new BigDecimal(0);
				b_bidExtendedPrice = ( (b_bidQuantity.multiply(b_bidUnitPrice)).multiply(bd_umFactor) ).setScale(Integer.valueOf(s_dollar_decimals).intValue(),BigDecimal.ROUND_HALF_UP);
%>
								<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
									<tr>
										<td <%=HtmlWriter.isVisible(oid, "rfq-supplier")%> width=14% class=browseRow><%=s_bidVendorId%></td>
										<td <%=HtmlWriter.isVisible(oid, "rfq-unitCost")%> width=13% class=browseRow align=right><%=b_bidUnitPrice%></td>
										<td <%=HtmlWriter.isVisible(oid, "rfq-extendedCost")%> width=13% class=browseRow align=right><%=b_bidExtendedPrice%></td>
									</tr>
								</table>
<%		}
	}
	else {%>
								<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
										<tr>
											<td  width=14% class=browseRow></td>
											<td  width=13% class=browseRow align=right></td>
											<td  width=13% class=browseRow align=right></td>
										</tr>
								</table>
<% } %>
							</td>
						</tr>
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

							<input align="left" type=hidden name="docFilename" value = "<%=filename%>">
<%		if (ext.equalsIgnoreCase("DOC")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>');void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
							<a href="javascript: oopenAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("EML")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/email.gif" border=0 alt="Open Attached Email"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
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
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	} %>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=660px>
<tr>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: printMe(); void(0);">Print</a></div></tsa:td>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: closeMe(); void(0);">Close</a></div></tsa:td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var netscape  = "";
	var ver = navigator.appVersion;
	var len = ver.length;

	for(iln = 0; iln < len; iln++) {
		if (ver.charAt(iln) == "(") break;
	}
	netscape = (ver.charAt(iln+1).toUpperCase() != "C");

	window.onfocus = displayButtons; // displayButtons on window.onfocus

	if (netscape) window.captureEvents(window.onfocus);

	function printMe() {
		hideArea("buttons");

		this.print();
	}

	function closeMe() {
		window.top.hidePopWin();
	}

	function displayButtons() {
		displayArea("buttons");
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

// End Hide script -->
</SCRIPT>