<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>
<%@page import="com.tsa.puridiom.currcode.CurrencyManager"%>
<%@page import="com.tsa.puridiom.vendor.VendorManager"%>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");

	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	List rfqVendorList = rfqHeader.getRfqVendorList();
	List lineList = (List) rfqHeader.getRfqLineList();
	BigDecimal b_icRfqHeader = rfqHeader.getIcRfqHeader();
	String s_rfqNumber = rfqHeader.getRfqNumber();
	String s_rfqAmendment = rfqHeader.getRfqAmendment();
	String s_rfqStatus = rfqHeader.getStatus();
	String s_rfqType = rfqHeader.getRfqType();
	String s_returnPage = "";
	int	lineCount = 0;
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
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="RfqBid_icRfqHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="allowBrowse" value="true"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Supplier Selection</div>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center valign=top>
		<table border=0 cellspacing=0 cellpadding=2 width=98%>
		<tr>
			<td width="53%"></td>
			<td align=left>
				<select name="awardSelectionType" size="1" onchange="setAwardSelection();">
					<option selected="selected" value="byItem"><b>Select by item</b></option>
<%		if (rfqVendorList != null) {
			for (int iv = 0; iv < rfqVendorList.size(); iv++) {
				RfqVendor rfqVendor = (RfqVendor) rfqVendorList.get(iv);
				String	vendorId = rfqVendor.getComp_id().getVendorId();
				String vendorName = vendorId;
				Object vendorObj = VendorManager.getInstance().getVendor(oid, vendorId);
				if (vendorObj != null && vendorObj instanceof Vendor)
				{
					vendorName = ((Vendor)vendorObj).getVendorName();
				}
%>
					<option value="<%=vendorId%>"><b>Select <%=vendorName%> for all items</b></option>
<%			}
		}
%>
				</select>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<td align=center width=680px>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top class=browseRow>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td width=50% align=center valign=top class=browseRow>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-lineNumber")%> width=5% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-lineNumber", "Line #")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-itemNumber")%> width=15% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-itemNumber", "Item/Part #")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-quantity")%> width=11% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-quantity", "Quantity")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-uom")%> width=8% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-uom", "UOM")%></td>
						</table>
					</td>
					<td width=50% align=center valign=top class=browseRow>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
						<tr>
							<td width=6% class=browseHdr nowrap>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-supplier")%> width=36% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-supplier", "Supplier")%></td>
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
%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td valign=top width=50%>
								<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow valign=top>
								<tr>
									<td <%=HtmlWriter.isVisible(oid, "rfq-lineNumber")%> width=5% class=browseRow nowrap align=right><%=i+1%>&nbsp;</td>
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
<%		if (bidList != null)
			{
%>
								<tr>
									<td width=6% class=browseRow valign=middle>&nbsp;</td>
									<td width=36% class=browseRow valign=middle>
										<select name="winningVendor<%=i%>" size="1" onChange="changePrices('<%=i%>',this);" onFocus="changePrices('<%=i%>',this);">
<%
				BigDecimal bd_bidExtendedPriceFinal = new BigDecimal("0");
				BigDecimal bd_bidUnitPriceFinal = new BigDecimal("0");
				for (int b = 0; b < bidList.size(); b++)
				{
					RfqBid rfqBid = (RfqBid)bidList.get(b);
					RfqBidPK rfqBidPK = (RfqBidPK) rfqBid.getComp_id();
					String vendorId = rfqBidPK.getVendorId();
					String vendorName = vendorId;
					Object vendorObj = VendorManager.getInstance().getVendor(oid, vendorId);
					if (vendorObj != null && vendorObj instanceof Vendor)
					{
						vendorName = ((Vendor)vendorObj).getVendorName();
					}
					BigDecimal bd_bidQuantity = HiltonUtility.getFormattedQuantity(rfqBid.getQuantity(), oid);

					//BigDecimal bd_bidUnitPrice = HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid);
					BigDecimal bd_bidUnitPrice = rfqBid.getUnitPrice();
					BigDecimal bd_bidExtendedPrice = new BigDecimal(0);
					bd_bidExtendedPrice = ( (bd_bidQuantity.multiply(bd_bidUnitPrice)).multiply(b_umFactor) ).setScale(Integer.valueOf(s_dollar_decimals).intValue(),BigDecimal.ROUND_HALF_UP);
					if (lowVendor.equals(vendorId)) {
						bd_bidExtendedPriceFinal = bd_bidExtendedPrice;
						bd_bidUnitPriceFinal = HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid);
					}
%>
<% 							if(oid.equalsIgnoreCase("DTN07P")) {%>
											<option value="<%=vendorId%>" <% if (lowVendor.equals(vendorId)) {%>selected<%}%>><%=vendorName%> (<%=HiltonUtility.getFormattedCurrency(bd_bidUnitPrice, s_curr_code,oid,true,5)%>)</option>
<%							} else {%>
											<option value="<%=vendorId%>" <% if (lowVendor.equals(vendorId)) {%>selected<%}%>><%=vendorName%> (<%=bd_bidUnitPrice%>)</option>
<%} %>
<%				}
%>
										</select>
									</td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-extendedCost")%>  width=29% class=browseRow align=right>
<% 							if(oid.equalsIgnoreCase("DTN07P")) {%>
										<input type="text" name="bd_unitPrice" value="<%=HiltonUtility.getFormattedCurrency(bd_bidUnitPriceFinal, s_curr_code,oid,true,5)%>" style="text-align: right; border: none" size=10 readonly/>
<%							} else {%>
										<input type="text" name="bd_unitPrice" value="<%=HiltonUtility.getFormattedDollar(bd_bidUnitPriceFinal, oid)%>" style="text-align: right; border: none" size=10 readonly/>
<% } %>									</td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-extendedCost")%>  width=29% class=browseRow align=right>
										<input type="text" name="bd_extendedPrice" value="<%=HiltonUtility.getFormattedDollar(bd_bidExtendedPriceFinal, oid)%>" style="text-align: right; border: none" size=10 readonly/>
									</td>
								</tr>
								</table>
<%
			}
%>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center id="buttons"><div id="pxbutton"><a href="javascript: submitAward(); void(0);">Submit</a></div></td>
	<td width=50% align=center id="buttons"><div id="pxbutton"><a href="javascript: doSubmit('<%=s_returnPage%>', 'RfqRetrieve'); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	 var lineCount = <%=lineCount%>;

	function vendorSelected() {
		firstVendor = frm.winningVendor0.value;
		flagNum = 1;
		<%	if (lineCount > 0) {
				for (int i = 1; i < lineCount; i++) { %>
		if (frm.winningVendor<%=i%>.value == firstVendor)
			flagNum = 1;
		else
			flagNum *= 0;
		<%	}	} %>
		if (flagNum > 0) return firstVendor;
		else return "byItem";
	}

	function submitAward() {
		frm.awardSelectionType.value = vendorSelected();
		//alert(frm.awardSelectionType.value);
		doSubmit("/rfq/rfq_award_options.jsp", "RfqLineListUpdateVendor;RfqRetrieve");
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
		<%		}	}	}	} %>

			}
			else {
		<%		for (int i = 0; i < lineCount; i++) { %>
				frm.winningVendor<%=i%>.selectedIndex = ind-1;
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
				/*BigDecimal bd_bidUnitPrice = HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid);*/
				BigDecimal bd_bidUnitPrice = rfqBid.getUnitPrice();
				BigDecimal bd_bidExtendedPrice = new BigDecimal(0);
				bd_bidExtendedPrice = ( (bd_bidQuantity.multiply(bd_bidUnitPrice)).multiply(b_umFactor) ).setScale(Integer.valueOf(s_dollar_decimals).intValue(),BigDecimal.ROUND_HALF_UP);
%>
		if (supplier.value == "<%=vendorId%>" && ind == <%=i%>) {
			//frm.bd_unitPrice[ind].value = "<%--=bd_bidUnitPrice--%>";
			if (lineCount > 1) {
				frm.bd_unitPrice[ind].value = "<%=HiltonUtility.getFormattedCurrency(bd_bidUnitPrice, s_curr_code,oid,true,5)%>";
				frm.bd_extendedPrice[ind].value = "<%=bd_bidExtendedPrice%>";
			} else {
				frm.bd_unitPrice.value = "<%=HiltonUtility.getFormattedCurrency(bd_bidUnitPrice, s_curr_code,oid,true,5)%>";
				frm.bd_extendedPrice.value = "<%=bd_bidExtendedPrice%>";
			}
		}
<%
			}
		}
%>

	}

// End Hide script -->
</SCRIPT>