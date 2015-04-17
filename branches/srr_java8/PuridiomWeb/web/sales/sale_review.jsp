<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	SaleHeader saleHeader = (SaleHeader) request.getAttribute("saleHeader");
	int i;
	int b;

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
%>

<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/scheduleArrays.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/saleOptionArrays.js"></script>
<%@ include file="/system/header.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--

	viewType = "<%=s_view%>";
	saleNumber = "<%=s_saleNumber%>";
	saleStatus = "<%=s_status%>";
	buyerCount = <%=buyerList.size()%>;
	saleInProgress = "<%=DocumentStatus.SALE_INPROGRESS%>";

	Array91= createSaleOptionsMenu(Array91[0]);

//-->
</SCRIPT>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");

	String	s_current_process = "HEADER_REVIEW";
	String	s_current_page = "/sales/sale_review.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";
%>
<%@ include file="/sales/sale_hidden_fields.jsp" %>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Surplus Transaction Information</div>
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

<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
<%	if ( (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) < 0 || s_status.compareTo(DocumentStatus.SALE_OPENAMENDMENT) == 0) ) {
			if (lineSet && !s_saleNumber.equals("N/A")) {
%>
	<td align=center><a href="javascript: salePost(); void(0);">Post Surplus</a></td>
<%		}
		}
		if ( (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) == 0)) { %>
	<td align=center><a href="javascript: doSubmit('/sales/sale_review.jsp', 'SaleAmendment;SaleRetrieve'); void(0);" ONCLICK="return verifyAction('Create an Amendment to Surplus # <%=saleHeader.getSaleNumber()%> ?');">Amendment</A></TD>
<%	} %>
	<td align=right nowrap><a href="javascript: void(0);" onMouseOut="popDown('Menu91')" onMouseOver="popUp('Menu91',event)">More Options</a></td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>

		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=300px align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
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
						<tr <%=HtmlWriter.isVisible(oid, "sale-currency")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-currency", "Currency")%>:&nbsp;</b></td>
							<td nowrap><%=saleHeader.getCurrency()%></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
			<td width=300px align=center valign=top rowspan=3>
			</td>
			<td rowspan=3 align=right valign=top><%@ include file="/sales/sale_sidebar.jsp" %></td>
		</tr>
		<tr><td>&nbsp;</td></tr>

<%
	//Webpost Options
	String s_webpost = saleHeader.getWebpost();
	String s_bidAccess = saleHeader.getBidAccess();
	String s_auctionType = saleHeader.getAuctionType();
	String s_lowBidReq = saleHeader.getHighestBidReq();
	String s_lowBidSource = saleHeader.getHighestBidSource();
	String s_lowestDisplay = saleHeader.getHighestDisplay();
%>
		<tr>
			<td width=300px align=center valign=top>
				<table id=webpostTable border=0 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;Post Options</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow id=webpostRows>
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
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
						<tr>
							<td width=37% height=18px class=browseHdr>&nbsp;<b>Buyers</b></td>
							<td width=14% height=18px class=browseHdr>&nbsp;<b>Total</b></td>
							<td width=10% height=18px class=browseHdr>&nbsp;<b>Terms</b></td>
							<td width=14% height=18px class=browseHdr>&nbsp;<b>Response Date</b></td>
							<td width=13% height=18px class=browseHdr>&nbsp;<b>Promise Date</b></td>
							<td width=12% height=18px class=browseHdr>&nbsp;<b>Bid Valid To</b></td>
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
	String s_buyerCode = "";
	String s_bidResponse = "";

	int i_size = 0;
/*	if (buyerList != null)
	{
		i_size = buyerList.size();

		for (i = 0;i < i_size; i++)
		{
			SaleBuyer saleBuyer = (SaleBuyer) buyerList.get(i);
			RfqVendorPK rfqVendorPK = saleBuyer.getComp_id();
			s_vendorId = rfqVendorPK.getVendorId();

			BigDecimal bd_shippingCharges = HiltonUtility.getFormattedDollar(saleBuyer.getShippingCharges(), oid);
			BigDecimal bd_taxAmount = HiltonUtility.getFormattedDollar(saleBuyer.getTaxAmount(), oid);
			BigDecimal bd_total = new BigDecimal(0);
			bd_total = bd_total.add(bd_shippingCharges);
			bd_total = bd_total.add(bd_taxAmount);

			List bidList = saleBuyer.getBidList();
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
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=37% class=browseRow valign=top><a href="javascript: viewBuyer('<%=s_vendorId%>'); void(0);" title="Click here to View Buyer Details."><%=s_vendorId%> - <%=VendorManager.getInstance().getVendorName(oid, s_vendorId)%></a></td>
					<td width=14% class=browseRow valign=top>$<%=HiltonUtility.getFormattedDollar(bd_total, oid)%></td>
					<td width=10% class=browseRow valign=top><%=rfqVendor.getPaymentTerms()%></td>
					<td width=14% class=browseRow valign=top><%=HiltonUtility.getFormattedDate(rfqVendor.getDateResponseRecv(), oid, userDateFormat)%></td>
					<td width=13% class=browseRow valign=top><%=HiltonUtility.getFormattedDate(rfqVendor.getDatePromised(), oid, userDateFormat)%></td>
					<td width=12% class=browseRow valign=top><%=HiltonUtility.getFormattedDate(rfqVendor.getBidValidTo(), oid, userDateFormat)%></td>
				</tr>
				</table>
<%		}
		}
*/
		if (i_size == 0) { %>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td class=browseRow valign=top>There are no buyers associated with this surplus.</td>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
						<tr>
							<td width=80% height=18px class=browseHdr>&nbsp;<b>Buyer Question</b></td>
							<td width=20% height=18px class=browseHdr>&nbsp;<b>Response Type</b></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
						<tr>
							<td width=68% height=18px class=browseHdr>&nbsp;<b>Comment</b></td>
							<td width=7% height=18px class=browseHdr align=center>&nbsp;<b>Post</b></td>
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
			String	s_cmtPost = docComment.getCommentWebpost();
			String	s_cmtPrint = docComment.getCommentPrint();
			String	s_cmtBold = docComment.getCommentBold();
			String	s_cmtPlace = docComment.getCommentPlace();
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
					<td width=68% class=browseRow><%=s_cmtTitle%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_cmtPost%></td>
					<td width=8% class=browseRow align=center valign=top><%=s_cmtPrint%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_cmtBold%></td>
					<td width=10% class=browseRow align=center valign=top><%=s_placeString%></td>
				</tr>
				</table>
<%	}
	}
	if (cmtList.size() <= 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow>There are no comments associated with this surplus.</td></tr>
				</table>
<%}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=4 width=665px class=browseHdr>
						<tr>
							<td width=3% height=18px class=browseHdr nowrap>&nbsp;</td>
							<td width=13% height=18px class=browseHdr nowrap align=center>Item/Part #</td>
							<td width=16% height=18px class=browseHdr nowrap align=center>Commodity</td>
							<td width=16% height=18px class=browseHdr nowrap align=center>Line Status</td>
							<td width=9% height=18px class=browseHdr nowrap align=center>Quantity</td>
							<td width=6% height=18px class=browseHdr nowrap align=center>UOM</td>
							<td width=11% height=18px class=browseHdr nowrap align=center>Buyer Id</td>
							<td width=9% height=18px class=browseHdr nowrap align=center>Unit Price</td>
							<td width=17% height=18px class=browseHdr nowrap align=center>Extended Price</td>

						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<tsa:hidden name="SaleLine_icSaleLine" value=""/>
				<table id=itemRows border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td>
<%
	if (saleLine != null) {
		BigDecimal bd_icSaleLine = saleLine.getIcSaleLine();
		BigDecimal bd_umFactor = saleLine.getUmFactor();
		if (bd_umFactor.compareTo(new BigDecimal(0)) <= 0)
		{
			bd_umFactor = new BigDecimal(1);
		}
%>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
						<tr>
							<td valign=top width=63%>
								<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow valign=top>
								<tr>
									<td width=3% class=browseRow nowrap align=center><a href="javascript: viewItem(); void(0);" title="Click here to Vew/Modify Item Details.">1</a>&nbsp;</td>
									<td width=13% class=browseRow nowrap align=center><%=saleLine.getItemNumber()%></td>
									<td width=16% class=browseRow nowrap align=center><%=saleLine.getCommodity()%></td>
									<td width=16% class=browseRow nowrap align=center><%=DocumentStatus.toString(saleLine.getStatus(), oid)%></td>
									<td width=9% class=browseRow nowrap align=center><%=HiltonUtility.getFormattedQuantity(saleLine.getQuantity(), oid)%></td>
									<td width=6% class=browseRow nowrap align=center><%=saleLine.getUmCode()%></td>
								</tr>
								<tr>
									<td class=browseRow nowrap>&nbsp;</td>
									<td colspan=5 class=browseRow><%=saleLine.getDescription()%></td>
								</tr>
								</table>
							</td>
							<td width=37%>

<%/*	if(!(bidList.equals(null))) {
		for(b=0;b<bidList.size();b++)
		{
		RfqBid rfqBid = (RfqBid)bidList.get(b);
		RfqBidPK rfqBidPK = (RfqBidPK) rfqBid.getComp_id();
		String s_bidVendorId = rfqBidPK.getVendorId();
		String s_bidUmCode = rfqBid.getUmCode();
		if (s_bidUmCode==null){s_bidUmCode = "EA";}
		BigDecimal b_bidQuantity = HiltonUtility.getFormattedQuantity(rfqBid.getQuantity(), oid);
		BigDecimal bd_bidUnitPrice = HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid);
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

		BigDecimal bd_bidExtendedPrice = new BigDecimal(0);
		bd_bidExtendedPrice = ( (b_bidQuantity.multiply(bd_bidUnitPrice)).multiply(bd_umFactor) ).setScale(Integer.valueOf(s_dollar_decimals).intValue(),BigDecimal.ROUND_HALF_UP);
%>
								<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
									<tr>
										<td width=11% class=browseRow align=center><%=s_bidVendorId%></td>
										<td width=9% class=browseRow align=center>
<%	if (s_bidUnitPrice.equals("See Notes")) {%>
											<a href="javascript: viewNotes('<%=bd_icRfqLine%>', '<%=s_bidVendorId%>'); void(0);"><%=s_bidUnitPrice%></a>
<%	} else { %>
											<%=s_bidUnitPrice%>
<%	} %>
										</td>
										<td width=17% class=browseRow align=center><%=bd_bidExtendedPrice%></td>

									</tr>
								</table>
<%		}
	}
	else
	{
*/%>
								<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
										<tr>
											<td  width=14% class=browseRow></td>
											<td  width=13% class=browseRow align=right></td>
											<td  width=13% class=browseRow align=right></td>
										</tr>
								</table>
<%// } %>
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

<%	if (propertiesManager.isModuleActive("DOCUMENTS")) {%>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=70% height=18px class=browseHdr>&nbsp;<b>Attachment</b></td>
							<td width=10% height=18px class=browseHdr align=center>&nbsp;<b>Print</b></td>
							<td width=10% height=18px class=browseHdr align=center>&nbsp;<b>Post</b></td>
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
		List attachmentList = (List) saleHeader.getDocAttachmentList();
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
					<td width=10% class=browseRow align=center valign=top><%=docAttachment.getDocPost()%></td>
					<td width=10% class=browseRow align=center valign=top></td>
				</tr>
				</table>

	<% 		}
			}
			if (ai == 0) {%>
				<table border=0 cellpadding=2 cellspacing=0 width=100% class=browseRow>
				<tr><td class=browseRow>There are no attachments associated with this surplus.</td></tr>
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

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var saleNumber = "<%=s_saleNumber%>";
	var fiscalyear = "<%=s_fiscalYear%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

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

	function viewItem() {
		doSubmit('/sales/sale_item.jsp', 'SaleLineRetrieveByHeader');
	}

	function saleSaveAs()
	{
		popupParameters = "formtype=SALE;formnumber=<%=s_saleNumber%>;fiscalyear=" + fiscalyear + ";action=saveas";
		doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
	}

	function switchView()
	{
		frm.viewType.value = "CLASSIC";
		doSubmit('/sales/sale_summary.jsp', 'SaleRetrieve');
	}

	function salePost()
	{
		var lineSet = <%=lineSet%>;
		var synopsis = "<%=saleHeader.getDescription().replaceAll("\r\n", "~~")%>";
		var webpost = "<%=saleHeader.getWebpost()%>";
		var bidAccess = "<%=s_bidAccess%>";
		var buyers = <%=buyerList.size()%>;

		synopsis = synopsis.replace('~~', '\n');

		if (!lineSet)
		{
			alert("You must add an item to sell before posting a Sale!");
		}
		else
		{
			frm.SaleHeader_status.value = "<%=DocumentStatus.SALE_BIDS_PENDING%>";
			if (frm.viewType.value == "WIZARD")
			{
				doSubmit('/sales/sale_review.jsp', 'SaleHeaderUpdateById;SaleRetrieve');
			}
			else
			{
				doSubmit('/sales/sale_summary.jsp', 'SaleHeaderUpdateById;SaleRetrieve');
			}
		}
	}

	function salePreview()
	{
		popupParameters = "SaleHeader_icSaleHeader=" + frm.SaleHeader_icSaleHeader.value;
		doSubmitToPopup('/sales/sale_preview.jsp', 'SaleRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
	}

	function viewHistory()
	{
		popupParameters = "HistoryLog_icHeaderHistory=<%=saleHeader.getIcHeaderHistory()%>;formtype=SALE;SaleLine_icSaleHeader=" + frm.SaleHeader_icSaleHeader.value;
		doSubmitToPopup('/sales/sale_history.jsp', 'HistoryLogRetrieveByHistoryHeader', 'width=675px', 'height=450px');
	}

	function viewAttachments() {
		doSubmit('/sales/sale_attachments.jsp', 'DocAttachmentRetrieveByHeader');
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
<%
	request = null;
%>