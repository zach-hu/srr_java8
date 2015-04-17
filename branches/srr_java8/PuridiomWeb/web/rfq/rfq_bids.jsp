<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	BigDecimal b_icRfqHeader = rfqHeader.getIcRfqHeader();
	String s_rfqNumber = rfqHeader.getRfqNumber();
	String s_rfqAmendment = rfqHeader.getRfqAmendment();
	String s_rfqStatus = rfqHeader.getStatus();
	String s_rfqType = rfqHeader.getRfqType();

	List lineList = (List) rfqHeader.getRfqLineList();
	int	i_line_count = 0;
	if (lineList!=null){
		i_line_count = lineList.size();
	}

	String s_returnPage = "";
	if (s_view.equalsIgnoreCase("CLASSIC"))
	{
		s_returnPage = "/rfq/rfq_summary.jsp";
	}
	else
	{
		s_returnPage = "/rfq/rfq_review.jsp";
	}
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="RfqBid_icRfqHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="lineCount" value="<%=i_line_count%>"/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Solicitation Bids</div>
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
<br>

<%
	String s_response = "";
	int colspan = 1;
	List questionList = (List) rfqHeader.getRfqQuestionList();
	if (questionList != null && questionList.size() > 0)
	{
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
<%
			for (int i = 0; i < questionList.size(); i++)
			{
				RfqQuestion rfqQuestion = (RfqQuestion) questionList.get(i);
				RfqQuestionPK rfqQuestionPK = rfqQuestion.getComp_id();
				BigDecimal bd_icQuestion = rfqQuestionPK.getIcQuestion();
%>
				<tr height=18px>
					<td width="5px">&nbsp;</td>
					<td colspan=3><b><%=rfqQuestion.getQuestionText()%></b></td>
				</tr>


<%
			List responseList = (List) request.getAttribute("vendorResponseList");
			if (responseList != null && responseList.size() > 0)
			{
				for (int j = 0; j < responseList.size(); j++)
				{
					VendorResponse vendorResponse = (VendorResponse) responseList.get(j);
					VendorResponsePK vendorResponsePK = vendorResponse.getComp_id();
					if (bd_icQuestion.compareTo(vendorResponsePK.getIcQuestion()) == 0)
					{
						s_response = "";
						icolspan = 1;
						if (vendorResponse.getResponse().equals("Y"))
						{
							s_response = "Yes";
						}
						else if (vendorResponse.getResponse().equals("N"))
						{
							s_response = "No";
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
	<td align=center width=680px>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
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
		<tr>
			<td width=100% align=center valign=top>
				<tsa:hidden name="RfqBid_icRfqLine" value=""/>
				<tsa:hidden name="RfqLine_icRfqLine" value=""/>
				<table id=itemRow border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td>
<% 	//Rfq Lines
	if (lineList != null)
	{
		for (int i = 0; i < lineList.size(); i++)
		{
			RfqLine rfqLine = (RfqLine)lineList.get(i);
			BigDecimal b_icRfqLine = rfqLine.getIcRfqLine();
			String s_ItemNumber = rfqLine.getItemNumber();
			if (s_ItemNumber==null){s_ItemNumber = "";}
			String s_commodity = rfqLine.getCommodity();
			if (s_commodity==null){s_commodity = "";}

			String s_uom = rfqLine.getUmCode();
			if (s_uom==null){s_uom = "";}
			String s_lineStatus = rfqLine.getStatus();
			if (s_lineStatus==null){s_lineStatus = "";}
			String s_description = rfqLine.getDescription();
			if (s_description==null){s_description = "";}

			BigDecimal b_umFactor = rfqLine.getUmFactor();
			if (b_umFactor==null  || b_umFactor.compareTo(new BigDecimal(0)) <= 0) {b_umFactor = new BigDecimal(1);}
			List bidList = (List) rfqLine.getRfqBidList();
%>

						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
						<tr>
							<td valign=top width=60%>
								<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow valign=top>
								<tr>
									<td <%=HtmlWriter.isVisible(oid, "rfq-lineNumber")%> width=7% class=browseRow nowrap align=right><%=i+1%>&nbsp;</td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-itemNumber")%> width=15% class=browseRow nowrap><%=s_ItemNumber%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-commodity")%> width=12% class=browseRow nowrap><%=s_commodity%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-lineStatus")%> width=11% class=browseRow nowrap><%=DocumentStatus.toString(s_lineStatus, oid)%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-quantity")%> width=12% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), oid)%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-uom")%> width=8% class=browseRow nowrap><%=s_uom%></td>
								</tr>
								<tr>
									<td class=browseRow nowrap>&nbsp;</td>
									<td colspan=5 class=browseRow><%=s_description%></td>
								</tr>
								</table>
							</td>
							<td width=40% valign=top>

<%		if (bidList != null)
			{
				for (int b = 0; b < bidList.size(); b++)
				{
					RfqBid rfqBid = (RfqBid)bidList.get(b);
					RfqBidPK rfqBidPK = (RfqBidPK) rfqBid.getComp_id();
					String s_bidVendorId = rfqBidPK.getVendorId();
					BigDecimal bd_bidQuantity = HiltonUtility.getFormattedQuantity(rfqBid.getQuantity(), oid);

					BigDecimal bd_bidUnitPrice = HiltonUtility.getFormattedPriceCurrency(rfqBid.getUnitPrice(), s_curr_code, oid, false);
					BigDecimal bd_bidExtendedPrice = new BigDecimal(0);
//					bd_bidExtendedPrice = ( (bd_bidQuantity.multiply(bd_bidUnitPrice)).multiply(b_umFactor) ).setScale(Integer.valueOf(s_dollar_decimals).intValue(),BigDecimal.ROUND_HALF_UP);
					bd_bidExtendedPrice = HiltonUtility.getFormattedCurrency( (bd_bidQuantity.multiply(bd_bidUnitPrice)).multiply(b_umFactor) ), s_curr_code, oid, false);
%>
								<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow valign=top>
								<tr>
									<td <%=HtmlWriter.isVisible(oid, "rfq-supplier")%>  width=14% class=browseRow valign=top><%=s_bidVendorId%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-unitCost")%>  width=13% class=browseRow align=right valign=top><%=dbd_bidUnitPrice%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-extendedCost")%>  width=13% class=browseRow align=right valign=top><%=HiltonUtility.getFormattedDollar(bd_bidExtendedPrice, oid)%></td>
								</tr>
								</table>
<%			}
			}
			else
			{%>
								<table border=1 cellspacing=0 cellpadding=4 width=100% class=browseRow>
								<tr>
									<td  width=14% class=browseRow></td>
									<td  width=13% class=browseRow align=right></td>
									<td  width=13% class=browseRow align=right></td>
								</tr>
								</table>
<% 		} %>
							</td>
						</tr>
						</table>
<%		if (i != lineList.size() - 1) { %>
						<hr size=0 width=90%>
<%		}
		}
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
	<td width=50% align=center id="buttons"><a href="javascript: printMe(); void(0);"><img class=button src="<%=contextPath%>/images/button_print.gif" border=0 alt="Print"></a></td>
	<td width=50% align=center id="buttons"><a href="javascript: doSubmit('<%=s_returnPage%>', 'RfqRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>

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

	function displayButtons() {
		displayArea("buttons");
	}

// End Hide script -->
</SCRIPT>