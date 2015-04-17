<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.math.BigDecimal" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<%
	SaleHeader saleHeader = (SaleHeader) request.getAttribute("saleHeader");
	int i;
	String s_icSaleHeader = saleHeader.getIcSaleHeader().toString();
	String s_status = saleHeader.getStatus();
	String s_saleNumber = saleHeader.getSaleNumber();
	String s_amendment = saleHeader.getAmendment();
	String s_fiscalYear = saleHeader.getFiscalYear();
	boolean lineSet = false;

	List buyerList = (List) saleHeader.getSaleBuyerList();
	if (buyerList == null) {
		buyerList = new ArrayList();
	}
	SaleLine saleLine = saleHeader.getSaleLine();
	if (saleLine != null && !HiltonUtility.isEmpty(saleLine.getItemNumber() + saleLine.getDescription())) {
		lineSet = true;
	}

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_current_process = "PREVIEW";
	String	s_current_page = "/sales/sale_preview.jsp";
%>

<%@ include file="/sales/sale_process_steps.jsp" %>
<%@ include file="/sales/sale_hidden_fields.jsp" %>

<table width=660px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Surplus Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/sales/sale_status_title.jsp" %>
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
						<tr<%=HtmlWriter.isVisible(oid, "sale-contactName")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-contactName", "Contact")%>:</b>&nbsp;</td>
							<td nowrap><%=UserManager.getInstance().getUser(oid, saleHeader.getContact()).getDisplayName()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sale-saleDate")%>>
							<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-saleDate", "Surplus Date")%>:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getFormattedDate(saleHeader.getSaleDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sale-fiscalYear")%>>
							<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-fiscalYear", "Fiscal Year")%>:&nbsp;</b></td>
							<td nowrap><%=s_fiscalYear%></td>
						</tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "sale-dueDate")%>>
							<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-auctionEndDate", "Due Date")%>:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getFormattedDate(saleHeader.getAuctionEndDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sale-currency")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-currency", "Currency")%>:&nbsp;</b></td>
							<td nowrap><%=saleHeader.getCurrency()%></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
<%	}%>
		</tr>
		<tr><td colspan=2>&nbsp;</td></tr>
<%
	if (processMap.containsKey("WEBPOST_OPTIONS"))
	{
		//Webpost Options
		String s_webpost = saleHeader.getWebpost();
		String s_bidAccess = saleHeader.getBidAccess();
		String s_auctionType = saleHeader.getAuctionType();
		String s_lowBidReq = saleHeader.getHighestBidReq();
		String s_lowBidSource = saleHeader.getHighestBidSource();
		String s_lowestDisplay = saleHeader.getHighestDisplay();
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
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (s_webpost.indexOf("N")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Non-Web Surplus</td></tr>
<%	}
		if (s_webpost.indexOf("D")>= 0 || s_webpost.indexOf("V")>= 0 || s_webpost.indexOf("VB")>= 0 || s_webpost.indexOf("DB")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Post Surplus</td></tr>
<%	}
		if (s_webpost.indexOf("VB")>= 0 || s_webpost.indexOf("DB")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Online Bidding</td></tr>
<%	}
		if (s_webpost.indexOf("D")>= 0 || s_webpost.indexOf("DB")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Downloadable</td></tr>
<%	} %>
						</table>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (s_bidAccess.indexOf("U")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Buyer Access: Unrestricted</td></tr>
<%	}
		if (s_bidAccess.indexOf("I")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Buyer Access: By Invitation Only</td></tr>
<%	}
		if (s_bidAccess.indexOf("R")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Buyer Access: Restrict Buyers</td></tr>
<%	}
		if (s_auctionType.indexOf("S")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Sealed Bid</td></tr>
<%	}
		if (s_auctionType.indexOf("O")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Open Auction</td></tr>
<%	}
		if (s_lowestDisplay.indexOf("A")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Display the highest bid dollar amount.</td></tr>
<%	}
		if (s_lowestDisplay.indexOf("N")>= 0) { %>
						<tr><td class=browseRow height=12px nowrap>Display highest bid notification.</td></tr>
<%	}
		if (saleHeader.getExtendMinutes().compareTo(new BigDecimal(0)) > 0) { %>
						<tr><td class=browseRow height=12px nowrap>Extend Auction By <%=saleHeader.getExtendMinutes()%> Minutes.</td></tr>
<%	} %>
						</table>
					</td>
				</tr>
				</table>
			</td>
<%	}%>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<%	if (processMap.containsKey("HEADER_BUYERS")) { %>
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
							<td <%=HtmlWriter.isVisible(oid, "sale-buyer")%> width=20% height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-buyer", "Buyer")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "sale-total")%> width=15% height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-total", "Total")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "sale-paymentTerms")%> width=10% height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-paymentTerms", "Terms")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "sale-responseDate")%> width=15% height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-responseDate", "Response Date")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "sale-bidValidTo")%> width=15% height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-bidValidTo", "Bid Valid To")%></b></td>
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
/*	String s_buyerId = "";
	String s_bidResponse = "";

	int i_size = 0;
	if (buyerList != null)
	{
		i_size = buyerList.size();
		for(i = 0;i < i_size; i++)
		{
			SaleBuyer saleBuyer = (SaleBuyer) buyerList.get(i);
			SaleBuyerPK rfqVendorPK = saleBuyer.getComp_id();
			s_buyerId = saleBuyerPK.getBuyerId();

			BigDecimal bd_shippingCharges = HiltonUtility.getFormattedDollar(rfqVendor.getShippingCharges(), oid);
			BigDecimal bd_taxAmount = HiltonUtility.getFormattedDollar(rfqVendor.getTaxAmount(), oid);
			BigDecimal bd_total = new BigDecimal(0);
			bd_total = bd_total.add(bd_shippingCharges);
			bd_total = bd_total.add(bd_taxAmount);

			List bidList = rfqVendor.getRfqBidList();
			if (bidList != null)
			{
				for (int j = 0; j < bidList.size(); j++)
				{
					RfqBid rfqBid = (RfqBid) bidList.get(j);
					BigDecimal bd_quantity = rfqBid.getQuantity();
					BigDecimal bd_unitprice = rfqBid.getUnitPrice();
					BigDecimal bd_umfactor = rfqBid.getUmFactor();
					if (bd_umfactor.compareTo(new BigDecimal(0)) == 0)
					{
						bd_umfactor = new BigDecimal(1);
					}
					BigDecimal bd_extprice = (bd_quantity.multiply(bd_unitprice)).multiply(bd_umfactor);
					bd_total = bd_total.add(bd_extprice);
				}
			}

			Date d_responseDate = rfqVendor.getDateResponseRecv();
			Date d_promiseDate = rfqVendor.getDatePromised();
			Date d_bidValidTo = rfqVendor.getBidValidTo();
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "sale-supplier")%> width=20% class=browseRow valign=top><%=s_vendorId%></td>
					<td <%=HtmlWriter.isVisible(oid, "sale-total")%> width=15% class=browseRow valign=top>$<%=HiltonUtility.getFormattedDollar(bd_total, oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "sale-paymentTerms")%> width=10% class=browseRow valign=top><%=rfqVendor.getPaymentTerms()%></td>
					<td <%=HtmlWriter.isVisible(oid, "sale-responseDate")%> width=15% class=browseRow valign=top><%=d_responseDate%></td>
					<td <%=HtmlWriter.isVisible(oid, "sale-promiseDate")%> width=15% class=browseRow valign=top><%=d_promiseDate%></td>
					<td <%=HtmlWriter.isVisible(oid, "sale-bidValidTo")%> width=15% class=browseRow valign=top><%=d_bidValidTo%></td>
				</tr>
				</table>
<%	} }
*/%>
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
	List cmtList = (List) saleHeader.getDocCommentList();
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
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=75% class=browseRow><%=s_cmtTitle%></td>
					<td width=8% class=browseRow align=center valign=top><%=s_cmtPrint%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_cmtBold%></td>
					<td width=10% class=browseRow align=center valign=top><%=s_placeString%></td>
				</tr>
				</table>
<%	}
	} %>

				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td colspan=3><hr size=0></td></tr>
				<tr>
					<td width=30% class=browseRow>&nbsp;<b>Attachment Title</b></td>
					<td width=60% class=browseRow>&nbsp;<b>Attachment Description</b></td>
					<td width=10% class=browseRow>&nbsp;</td>
				</tr>
<% 	//Notes & Attachments
	List attList = (List) saleHeader.getDocAttachmentList();
	if (attList!=null){
		for(i=0;i<attList.size();i++)
		{
		DocAttachment docAttachment = (DocAttachment)attList.get(i);
		DocAttachmentPK docAttachmentPK = docAttachment.getComp_id();
		BigDecimal b_icAttHeader = docAttachmentPK.getIcHeader();
		String s_docTitle = docAttachment.getDocTitle();
		if (i==1){
%>
				</table>

				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	} %>
				<tr>
					<td class=browseRow><%=s_docTitle%></td>
				</tr>
<%	} } %>
				<tr><td colspan=3>&nbsp;</td></tr>
				</table>

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
								<td <%=HtmlWriter.isVisible(oid, "sale-lineNumber")%> width=5% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-lineNumber", "Line #")%></td>
								<td <%=HtmlWriter.isVisible(oid, "sale-itemNumber")%> width=14% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-itemNumber", "Item/Part #")%></td>
								<td <%=HtmlWriter.isVisible(oid, "sale-commodity")%> width=11% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-commodity", "Commodity")%></td>
								<td <%=HtmlWriter.isVisible(oid, "sale-lineStatus")%> width=11% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-lineStatus", "Line Status")%></td>
								<td <%=HtmlWriter.isVisible(oid, "sale-quantity")%> width=11% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-quantity", "Quantity")%></td>
								<td <%=HtmlWriter.isVisible(oid, "sale-uom")%> width=8% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-uom", "UOM")%>&nbsp;&nbsp;&nbsp;&nbsp;|</td>
								<td <%=HtmlWriter.isVisible(oid, "sale-buyer")%> width=14% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-buyer", "Buyer")%></td>
								<td <%=HtmlWriter.isVisible(oid, "sale-unitCost")%> width=13% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-unitCost", "Unit Cost")%></td>
								<td <%=HtmlWriter.isVisible(oid, "sale-extendedCost")%> width=13% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-extendedCost", "Extended Cost")%></td>
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
<%
	if (saleLine != null) {
		BigDecimal b_icSaleLine = saleLine.getIcSaleLine();
		BigDecimal bd_umFactor = saleLine.getUmFactor();
		if (bd_umFactor.compareTo(new BigDecimal(0)) <= 0)
		{
			bd_umFactor = new BigDecimal(1);
		}
//		List bidList = (List) saleLine.getRfqBidList();
		List bidList = null;
%>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
						<tr>
							<td valign=top width=60%>
								<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow valign=top>
								<tr>
									<td <%=HtmlWriter.isVisible(oid, "sale-lineNumber")%> width=7% class=browseRow nowrap align=right>1</td>
									<td <%=HtmlWriter.isVisible(oid, "sale-itemNumber")%> width=15% class=browseRow nowrap><%=saleLine.getItemNumber()%><tsa:hidden id="icSaleLine_0" value="<%=b_icSaleLine%>"/></td>
									<td <%=HtmlWriter.isVisible(oid, "sale-commodity")%> width=12% class=browseRow nowrap><%=saleLine.getCommodity()%></td>
									<td <%=HtmlWriter.isVisible(oid, "sale-lineStatus")%> width=11% class=browseRow nowrap><%=DocumentStatus.toString(saleLine.getStatus())%></td>
									<td <%=HtmlWriter.isVisible(oid, "sale-quantity")%> width=12% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(saleLine.getQuantity(), oid)%></td>
									<td <%=HtmlWriter.isVisible(oid, "sale-uom")%> width=8% class=browseRow nowrap><%=saleLine.getUmCode()%></td>
								</tr>
								<tr>
									<td class=browseRow nowrap>&nbsp;</td>
									<td colspan=5 class=browseRow><%=saleLine.getDescription()%></td>
								</tr>
								</table>
							</td>
							<td width=40% valign=top>

<%
		if (bidList != null)
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
										<td <%=HtmlWriter.isVisible(oid, "sale-supplier")%> width=14% class=browseRow><%=s_bidVendorId%></td>
										<td <%=HtmlWriter.isVisible(oid, "sale-unitCost")%> width=13% class=browseRow align=right><%=b_bidUnitPrice%></td>
										<td <%=HtmlWriter.isVisible(oid, "sale-extendedCost")%> width=13% class=browseRow align=right><%=b_bidExtendedPrice%></td>
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
<%	} %>
							</td>
						</tr>
						</table>
<%	} %>
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
	<td width=50% align=center id="buttons"><a href="javascript: printMe(); void(0);"><img src="<%=contextPath%>/images/button_print.gif" border=0 class=button alt="Print"></a></td>
	<td width=50% align=center id="buttons"><a href="javascript: closeMe(); void(0);"><img src="<%=contextPath%>/images/button_close.gif" border=0 class=button alt="Close"></a></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

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

// End Hide script -->
</SCRIPT>