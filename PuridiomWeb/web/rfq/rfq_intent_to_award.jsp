<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ page import="com.tsa.puridiom.currcode.CurrencyManager"%>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>
<%@ page import="java.math.*" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");

	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	List rfqVendorList = rfqHeader.getRfqVendorList();
	List lineList = (List) rfqHeader.getRfqLineList();
	BigDecimal b_icRfqHeader = rfqHeader.getIcRfqHeader();
	BigDecimal b_rfqHeader_icReqHeader = rfqHeader.getIcReqHeader();
	String s_rfqNumber = rfqHeader.getRfqNumber();
	String s_rfqAmendment = rfqHeader.getRfqAmendment();
	String s_rfqStatus = rfqHeader.getStatus();
	String s_rfqType = rfqHeader.getRfqType();
	String s_returnPage = "";
	int	lineCount = 0;
//	BigDecimal bd_bidUnitPrice = new BigDecimal("0");

	String s_curr_code = CurrencyManager.getInstance(oid).getCurrCode("").getCurrencyCode();

	if (s_view.equalsIgnoreCase("CLASSIC")) {
		s_returnPage = "/rfq/rfq_summary.jsp";
	}
	else {
		s_returnPage = "/rfq/rfq_review.jsp";
	}
	if (lineList != null) {
		lineCount = lineList.size();
	}

	boolean isRfqNotPurchasing = true;
	if (s_rfqStatus.compareTo(DocumentStatus.RFQ_PURCHASING) == 0) {
		isRfqNotPurchasing = false;
	}
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="RfqBid_icRfqHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_icReqHeader" value="<%=b_rfqHeader_icReqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="fromPage" value="rfq_intent_to_award.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Supplier Award</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/rfq/rfq_status_title.jsp" %>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<%
	String s_response = "";
	int colspan = 1;
	List questionList = (List) rfqHeader.getRfqQuestionList();
	if (questionList != null && questionList.size() > 0) {
%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table id=questions border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
				<tr>
					<td width=5% class=browseHdr nowrap>&nbsp;Supplier Questions</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table id=itemRow border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
<%		for (int i = 0; i < questionList.size(); i++) {
				RfqQuestion rfqQuestion = (RfqQuestion) questionList.get(i);
				RfqQuestionPK rfqQuestionPK = rfqQuestion.getComp_id();
				BigDecimal bd_icQuestion = rfqQuestionPK.getIcQuestion();
				Map responseValueMap = rfqQuestion.getResponseValueMap();
%>
				<tr height=18px>
					<td width="5px">&nbsp;</td>
					<td colspan=3><b><%=rfqQuestion.getQuestionText()%></b></td>
				</tr>
<%		List responseList = (List) request.getAttribute("vendorResponseList");
			if (responseList != null && responseList.size() > 0) {
				for (int j = 0; j < responseList.size(); j++) {
					VendorResponse vendorResponse = (VendorResponse) responseList.get(j);
					VendorResponsePK vendorResponsePK = vendorResponse.getComp_id();
					if (bd_icQuestion.compareTo(vendorResponsePK.getIcQuestion()) == 0) {
						s_response = "";
						if (responseValueMap != null) {
							ResponseValue responseValue = (ResponseValue) responseValueMap.get(vendorResponse.getResponse());
							if (responseValue != null) {
								s_response = responseValue.getResponseText();
							}
						}
						icolspan = 1;
						if (HiltonUtility.isEmpty(s_response)) {
							if (vendorResponse.getResponse().equals("Y")) {
								s_response = "Yes";
							}
							else if (vendorResponse.getResponse().equals("N")) {
								s_response = "No";
							}
						}
%>
				<tr height=18px>
					<td width="5px">&nbsp;</td>
					<td width="20%" nowrap><%=vendorResponsePK.getVendorId()%></td>
					<%	if (s_response.length() > 0) {%>
					<td align="left" nowrap><%=s_response%></td>
					<%	} else {
								icolspan = 2;
							}  %>
					<td colspan="<%=icolspan%>"><%=vendorResponse.getTextResponse()%></td>
				</tr>
<%				}
				}
			}
%>
				<tr><td colspan=3><br></td></tr>
<%		} %>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	} %>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center valign=top>
		<table border=0 cellspacing=0 cellpadding=2 width=98%>
			<tr>
				<td><b>PO Type</b></td>
				<td><input type="radio" name="poTypeFromRfq" value="PO" checked><%=OrderType.toString(OrderType.PURCHASE_ORDER , oid)%></td>
				<td><input type="radio" name="poTypeFromRfq" value="SO"><%=OrderType.toString(OrderType.SERVICE_ORDER, oid)%></td>
				<td><input type="radio" name="poTypeFromRfq" value="CT"><%=OrderType.toString(OrderType.CONTRACT, oid)%></td>
			<tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="radio" name="poTypeFromRfq" value="BO"><%=OrderType.toString(OrderType.BLANKET_ORDER, oid)%></td>
				<td><input type="radio" name="poTypeFromRfq" value="DO"><%=OrderType.toString(OrderType.DELIVERY_ORDER, oid)%></td>
				<td>&nbsp;</td>
			<tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center valign=top>
		<table border=0 cellspacing=0 cellpadding=2 width=98%>
		<tr>
			<td width="53%">
			<td align=left>
<%		if (isRfqNotPurchasing) { %>
				<select name="awardSelectionType" size="1" onchange="setAwardSelection();">
					<option selected="selected" value="byItem"><b>Award by item</b></option>
<%			if (rfqVendorList != null) {
				for (int iv = 0; iv < rfqVendorList.size(); iv++) {
					RfqVendor rfqVendor = (RfqVendor) rfqVendorList.get(iv);
					String	vendorId = rfqVendor.getComp_id().getVendorId();
%>
					<option value="<%=vendorId%>"><b>Award all items to <%=VendorManager.getInstance().getVendorName(oid,vendorId)%></b></option>
<%				}
			}
%>
				</select>
<%		}
%>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<td align=center width=680px>
		<table border=0 cellpaddng=2 cellspacing=0 width=665px>
		<tr>
			<td width=5% align=center><input type=checkbox name="selectAll" value="Y" onClick="toggleSelectAll();"></td>
			<td><b>Select All Items</b></td>
		</tr>
		</table>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top class=browseRow>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td width=50% align=center valign=top class=browseRow>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-lineNumber")%> width=5% class=browseHdr nowrap align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-lineNumber", "Line #")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-itemNumber")%> width=15% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-itemNumber", "Item/Part #")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-quantity")%> width=11% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-quantity", "Quantity")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-uom")%> width=8% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-uom", "UOM")%></td>
						</table>
					</td>
					<td width=50% align=center valign=top class=browseRow>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
						<tr>
							<td width=6% class=browseHdr nowrap>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-supplier")%> width=36% class=browseHdr nowrap>
							<% if (isRfqNotPurchasing) { %>
								<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-supplier", "Supplier")%>
							<% } else  { %>
								<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-awardedto", "Awarded To")%>
							<% } %>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-unitCost")%> width=29% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-unitCost", "Unit Cost")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-extendedCost")%> width=29% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-extendedCost", "Extended Cost")%></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan=2 align=center valign=top>
				<tsa:hidden name="RfqBid_icRfqLine" value=""/>
				<tsa:hidden name="RfqLine_icRfqLine" value=""/>
				<table id=itemRow border=1 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td>
<% 	//Rfq Lines
		for (int i = 0; i < lineCount; i++)
		{
			RfqLine rfqLine = (RfqLine)lineList.get(i);
			BigDecimal b_icRfqLine = rfqLine.getIcRfqLine();
			BigDecimal b_umFactor = rfqLine.getUmFactor();
			if (b_umFactor==null  || b_umFactor.compareTo(new BigDecimal(0)) <= 0) {b_umFactor = new BigDecimal(1);}
			List bidList = (List) rfqLine.getRfqBidList();
			String lowVendor = rfqLine.getLowestVendorId();
			String s_rfqLineStatus = rfqLine.getStatus();
			if (s_rfqLineStatus.compareTo(DocumentStatus.RFQ_PURCHASING) == 0) {
				lowVendor = rfqLine.getVendorAwarded();
			}
%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td valign=top width=50%>
								<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow valign=top>
								<tr>
									<td <%=HtmlWriter.isVisible(oid, "rfq-lineNumber")%> width=5% class=browseRow nowrap align=right>
										<% if (s_rfqLineStatus.compareTo(DocumentStatus.RFQ_PURCHASING) == 0) { %>
											<input type="checkbox" name="itemCheck" onClick="setItemSelectedValue(this)" value="<%=i%>" style="vertical-align: middle; float: left;">
										<% } %>
									<span><%=i+1%></span>&nbsp;
									</td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-itemNumber")%> width=15% class=browseRow nowrap><%=rfqLine.getItemNumber()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-quantity")%> width=11% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), oid)%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-uom")%> width=8% class=browseRow nowrap><%=rfqLine.getUmCode()%></td>
								</tr>
								<tr>
									<td class=browseRow nowrap>&nbsp;</td>
									<td colspan=4 class=browseRow><%=rfqLine.getDescription()%></td>
								</tr>
								</table>
							</td>
							<td width=50% valign=top>
								<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow valign=top>
<%			if (bidList != null && isRfqNotPurchasing)
			{
%>
								<tr>
									<td width=6% class=browseRow valign=middle>&nbsp;</td>
									<td width=36% class=browseRow valign=middle>
										<select name="winningVendor<%=i%>" size="1" onChange="changePrices('<%=i%>',this);" onFocus="changePrices('<%=i%>',this);">
<%
				BigDecimal bd_bidExtendedPriceFinal = new BigDecimal("0");
				BigDecimal bd_bidUnitPriceFinal = new BigDecimal("0");

				String vendorSelected = "";
				for (int b = 0; b < bidList.size(); b++)
				{
					RfqBid rfqBid = (RfqBid)bidList.get(b);
					RfqBidPK rfqBidPK = (RfqBidPK) rfqBid.getComp_id();
					String vendorId = rfqBidPK.getVendorId();
					BigDecimal bd_bidQuantity = HiltonUtility.getFormattedQuantity(rfqBid.getQuantity(), oid);

					//BigDecimal bd_bidUnitPrice = HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid);
					BigDecimal bd_bidUnitPrice = rfqBid.getUnitPrice();
					BigDecimal bd_bidExtendedPrice = new BigDecimal(0);
					bd_bidExtendedPrice = ( (bd_bidQuantity.multiply(bd_bidUnitPrice)).multiply(b_umFactor) ).setScale(Integer.valueOf(s_dollar_decimals).intValue(),BigDecimal.ROUND_HALF_UP);
					if (lowVendor.equals(vendorId)) {
						vendorSelected = vendorId;
						bd_bidExtendedPriceFinal = bd_bidExtendedPrice;
						bd_bidUnitPriceFinal = HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid);
					}
%>
<% 							if(oid.equalsIgnoreCase("DTN07P")) {%>
											<option value="<%=vendorId%>" <% if (lowVendor.equals(vendorId)) {%>selected<%}%>><%=vendorId%> (<%=HiltonUtility.getFormattedCurrency(bd_bidUnitPriceFinal, s_curr_code,oid,true,5)%>)</option>
<%							} else {%>
											<option value="<%=vendorId%>" <% if (lowVendor.equals(vendorId)) {%>selected<%}%>><%=vendorId%> (<%=bd_bidUnitPrice%>)</option>
<%} %>
										</select>
									</td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-extendedCost")%>  width=29% class=browseRow align=right>

<% 							if(oid.equalsIgnoreCase("DTN07P")) {
%>
										<input type="text" name="bd_unitPrice" value="<%=bd_bidUnitPrice %> <%=HiltonUtility.getFormattedCurrency(bd_bidUnitPrice, s_curr_code,oid,true,5)%>" style="text-align: right; border: none" size=10  readonly   onmousemove="convertMoney(this);"/>
<%							} else {%>
										<input type="text" name="bd_unitPrice" value="<%=HiltonUtility.getFormattedDollar(bd_bidUnitPriceFinal, oid)%>" style="text-align: right; border: none" size=10 readonly/>
<%} %>
<%			}
%>									</td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-extendedCost")%>  width=29% class=browseRow align=right>
										<input type="text" name="bd_extendedPrice" value="<%=HiltonUtility.getFormattedDollar(bd_bidExtendedPriceFinal, oid)%>" style="text-align: right; border: none" size=10 readonly/>
									</td>
								</tr>
								<tr>
									<td></td>
									<td colspan=3 class=browseRow>
										<input type="text" name="vendorName" value="<%=VendorManager.getInstance().getVendorName(oid,vendorSelected)%>" style="text-align: left; border: none" size=30 readonly/>
									</td>
								</tr>
								</table>
<%
			}
			else if (bidList != null && s_rfqStatus.compareTo(DocumentStatus.RFQ_PURCHASING) == 0) {
				BigDecimal bd_bidExtendedPriceFinal = new BigDecimal("0");
				String bd_bidUnitPriceFinal = "0.0000";
				BigDecimal bd_bidUnitPrice = new BigDecimal("0");
				for (int b = 0; b < bidList.size(); b++)
				{
					RfqBid rfqBid = (RfqBid)bidList.get(b);
					RfqBidPK rfqBidPK = (RfqBidPK) rfqBid.getComp_id();
					String vendorId = rfqBidPK.getVendorId();
					BigDecimal bd_bidQuantity = HiltonUtility.getFormattedQuantity(rfqBid.getQuantity(), oid);
					//BigDecimal bd_bidUnitPrice = HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid);
					bd_bidUnitPrice = rfqBid.getUnitPrice();

					BigDecimal bd_bidExtendedPrice = new BigDecimal(0);
					bd_bidExtendedPrice = ( (bd_bidQuantity.multiply(bd_bidUnitPrice)).multiply(b_umFactor) ).setScale(Integer.valueOf(s_dollar_decimals).intValue(),BigDecimal.ROUND_HALF_UP);
					if (lowVendor.equals(vendorId)) {
						bd_bidExtendedPriceFinal = bd_bidExtendedPrice;
						if(oid.equalsIgnoreCase("DTN07P")) {
							bd_bidUnitPriceFinal = HiltonUtility.getFormattedCurrency(bd_bidUnitPrice, s_curr_code,oid,true,5);
						}
						else{
							bd_bidUnitPriceFinal = HiltonUtility.getFormattedDollar(bd_bidUnitPrice, oid).toString();
						}
						%>
						<tr> 		<td width=6% class=browseRow valign=middle>&nbsp;</td>
									<td width=36% class=browseRow valign=middle>
										<%=VendorManager.getInstance().getVendorName(oid,rfqLine.getVendorAwarded())%>
									</td>
<%}	%>
<% }%>									<td <%=HtmlWriter.isVisible(oid, "rfq-extendedCost")%>  width=29% class=browseRow align=right>
										<%=bd_bidUnitPriceFinal%>
									</td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-extendedCost")%>  width=29% class=browseRow align=right>
										<%=HiltonUtility.getFormattedDollar(bd_bidExtendedPriceFinal, oid)%>
									</td>
								</tr><tr>
									<td></td>
									<td colspan=3 class=browseRow>
										<tsa:hidden name="itemSelectedValue" value="<%=rfqLine.getVendorAwarded() + \">PO>\" + rfqLine.getIcRfqLine()%>"/>
										<%-- if (s_rfqLineStatus.compareTo(DocumentStatus.RFQ_PURCHASING) == 0) { %>
										<input type="checkbox" name="itemCheck" onClick="setItemSelectedValue(this)" value="<%=i%>">
										<!-- <select name="itemSelected" size="1">
											<option value="NONE">Order Type</option>
											<option value="<%=rfqLine.getVendorAwarded()%>;PO;<%=rfqLine.getIcRfqLine()%>" selected>Purchase Order</option>
											<option value="<%=rfqLine.getVendorAwarded()%>;RO;<%=rfqLine.getIcRfqLine()%>">Blanket Release Order</option>
										</select> -->
										<% } --%>
									</td>
								</tr>
								</table>
<%			}
%>
								<tsa:hidden name="itemSelected" value="NONE"/>
							</td>
						</tr>
						</table>
<%		if (i != lineList.size() - 1) { %>
						<hr size=0 width=98%>
<%		}
		}%>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px id="buttons">
<tr>
	<% if (isRfqNotPurchasing) { %>
	<td align=center id="buttons"><div id="pxbutton"><a href="javascript: submitForwardForApprovals(); void(0);">Forward</a></div></td>
	<td align=center id="buttons"><div id="pxbutton"><a href="javascript: submitIntentToAward(); void(0);">Intent</a></div></td>
	<% } %>
	<td align=center id="buttons"><div id="pxbutton"><a href="javascript: submitAward(); void(0);">Award</a></div></td>
	<td align=center id="buttons"><div id="pxbutton"><a href="javascript: doSubmit('<%=s_returnPage%>', 'RfqRetrieve'); void(0);">Return</a></div></td>
</tr>
</table>



<iframe id="prefixFrame"
		name="prefixFrame"
		src="" height="110px"
		frameborder="0"
		marginheight="0"
		marginwidth="0"
		style="	position: absolute; bottom: 20%; left: 25%;
				border: 4px solid #C0C0C0;
				display: none;"></iframe>

<div id="poPrefixList">
</div>



<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	 var lineCount = <%=lineCount%>;

	function submitForwardForApprovals() {
		doSubmit("/rfq/rfq_award_options.jsp", "RfqUpdateVendor;RfqRetrieve");
	}

	function submitAward() {
		<% if (isRfqNotPurchasing) { %>
//			doSubmit("/rfq/rfq_review.jsp", "RfqUpdateVendor;PoMultipleCreateFromRfq;RfqForward;RfqHeaderUpdateByForward;RfqRetrieve");
			doSubmit("/orders/po_from_rfq.jsp", "RfqUpdateVendor;PoMultipleCreateFromRfq;RfqForward;RfqHeaderUpdateByForward;RfqRetrieve");
		<% } else if(oid.equalsIgnoreCase("vse06p") && b_rfqHeader_icReqHeader.longValue() <= 0) { %>
			loadPrefixFrame();
		<% } else {%>
//			doSubmit("/rfq/rfq_review.jsp", "PoMultipleCreateFromRfqPartial;RfqHeaderUpdateByForward;RfqRetrieve");
			doSubmit("/orders/po_from_rfq.jsp", "PoMultipleCreateFromRfqPartial;RfqHeaderUpdateByForward;RfqRetrieve");
		<% }%>
	}

	function submitIntentToAward() {
		doSubmit("/rfq/rfq_review.jsp", "RfqUpdateVendor;RfqForward;RfqRetrieve");
	}

	function setAwardSelection() {

		var options = frm.awardSelectionType.length;
		var award = "byItem";
		var ind = 0;

		if (options > 1) {
			for (var i = 0; i < options; i++) {
				if (frm.awardSelectionType.options[i].selected) {
					ind = i;
					break;
				}
			}
			if (ind==0) {
		<%		for (int i = 0; i < lineCount; i++) {
					RfqLine rfqLine = (RfqLine)lineList.get(i);
					List bidList = (List) rfqLine.getRfqBidList();
					String lowVendor = rfqLine.getLowestVendorId();
					if (bidList != null) {
						for (int b = 0; b < bidList.size(); b++)
						{
							RfqBid rfqBid = (RfqBid)bidList.get(b);
							RfqBidPK rfqBidPK = (RfqBidPK) rfqBid.getComp_id();
							String vendorId = rfqBidPK.getVendorId();
							if (lowVendor.equals(vendorId)) { %>
				frm.winningVendor<%=i%>.selectedIndex = <%=b%>;
				frm.winningVendor<%=i%>.disabled = false;
		<%		}	}	}	} %>

			}
			else {
		<%		for (int i = 0; i < lineCount; i++) { %>
				frm.winningVendor<%=i%>.selectedIndex = ind-1;
				frm.winningVendor<%=i%>.disabled = true;
		<%		} %>
			}
	    }
	    <%		for (int i = 0; i < lineCount; i++) { %>
		changePrices(<%=i%>,frm.winningVendor<%=i%>);
		<%		} %>
	}

	function changePrices(ind,supplier) {
<%
		for (int i = 0; i < lineCount; i++)
		{
			RfqLine rfqLine = (RfqLine)lineList.get(i);
			BigDecimal b_icRfqLine = rfqLine.getIcRfqLine();
			BigDecimal b_umFactor = rfqLine.getUmFactor();
			if (b_umFactor==null  || b_umFactor.compareTo(new BigDecimal(0)) <= 0) {b_umFactor = new BigDecimal(1);}
			List bidList = (List) rfqLine.getRfqBidList();
			for (int b = 0; b < bidList.size(); b++) {
				RfqBid rfqBid = (RfqBid)bidList.get(b);
				RfqBidPK rfqBidPK = (RfqBidPK) rfqBid.getComp_id();
				String vendorId = rfqBidPK.getVendorId();
				BigDecimal bd_bidQuantity = HiltonUtility.getFormattedQuantity(rfqBid.getQuantity(), oid);
				BigDecimal bd_bidUnitPrice = HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid);
				BigDecimal bd_bidExtendedPrice = new BigDecimal(0);
				bd_bidExtendedPrice = ( (bd_bidQuantity.multiply(bd_bidUnitPrice)).multiply(b_umFactor) ).setScale(Integer.valueOf(s_dollar_decimals).intValue(),BigDecimal.ROUND_HALF_UP);
%>
		if (supplier.value == "<%=vendorId%>" && ind == <%=i%>) {
			frm.bd_unitPrice[ind].value = "<%=bd_bidUnitPrice%>";
			frm.bd_extendedPrice[ind].value = "<%=bd_bidExtendedPrice%>";
			frm.vendorName[ind].value = "<%=VendorManager.getInstance().getVendorName(oid,vendorId)%>";
		}
<%
			}
		}
%>

	}

	function createMultiplePo() {
		doSubmit('/rfq/.jsp', 'RfqApprove;PoMultipleCreateFromRfq');
	}

	function setItemSelectedValue(obj) {
		var ind = obj.value;
		if(document.getElementsByName('itemSelectedValue').length == 1) {
			if(typeof frm.itemSelected[ind] == 'object') {
				if (obj.checked)
					frm.itemSelected[ind].value = frm.itemSelectedValue.value;
				else
					frm.itemSelected[ind].value = "NONE";
			}
			else {
				if (obj.checked)
					frm.itemSelected.value = frm.itemSelectedValue.value;
				else
					frm.itemSelected.value = "NONE";
			}
		} else {
			if (obj.checked) {
				frm.itemSelected[ind].value = frm.itemSelectedValue[ind].value;
			} else {
				frm.itemSelected[ind].value = "NONE";
			}
		}
	}

	function loadPrefixFrame() {
		var lineCount = <%=lineCount%>;
		popupUrl = '/orders/po_prefix_options.jsp';
		popupHandler = 'PoPrefix';
		popupUserId = '${esapi:encodeForJavaScript(userId)}';
		popupOrganizationId = '<%= oid %>';

		popupParameters = '';

		if(typeof frm.itemSelected.value == 'string') {
			popupParameters += 'itemSelected=' + frm.itemSelected.value + ';';
		} else {
			for(var i = 0; i < lineCount; i++) {
				popupParameters += 'itemSelected=' + frm.itemSelected[i].value + ';';
			}
		}

		document.getElementById('buttons').style.display = 'none';
		document.getElementById('prefixFrame').src = '<%= contextPath %>/system/iframe_html.jsp';
		document.getElementById('prefixFrame').style.display = '';
	}

	function resizeTypeOptions(numberSuppliers) {
		var height = 12;
		height += numberSuppliers*2;
		document.getElementById('prefixFrame').style.height = height + 'em';
	}

	function assignPoList(poList) {
		var newInput = "";
		for(var i = 0; i < poList.length; i++) {
			newInput += "<input type='hidden' name='poDataList' value='" + poList[i].value + "'>";
		}
		document.getElementById('poPrefixList').innerHTML = newInput;
//		doSubmit("/rfq/rfq_review.jsp", "PoMultipleCreateFromRfqPartial;RfqHeaderUpdateByForward;RfqRetrieve");
		doSubmit("/orders/po_from_rfq.jsp", "PoMultipleCreateFromRfqPartial;RfqHeaderUpdateByForward;RfqRetrieve");
//		hidePopUpWindow();
	}

	function hidePopUpWindow() {
		document.getElementById('prefixFrame').style.display = 'none';
		document.getElementById('buttons').style.display = '';
	}

	function toggleSelectAll() {
		var items = frm.elements.item("itemCheck");
		var selectValue = frm.selectAll.checked;

		if (items != undefined) {
			if (items.length > 1) {
				for (var i=0; i < items.length; i++) {
					frm.itemCheck[i].checked = selectValue;
					frm.itemCheck[i].fireEvent("onClick");
				}
			} else if (items.length == 1) {
				frm.itemCheck.checked = selectValue;
				frm.itemCheck.fireEvent("onClick");
			} else {
				frm.itemCheck.checked = selectValue;
				frm.itemCheck.fireEvent("onClick");
			}
		}
	}

// End Hide script -->
</SCRIPT>
