<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	List vendorList = (List) request.getAttribute("rfqVendorList");
	String s_icRfqHeader = (String) request.getAttribute("RfqVendor_icRfqHeader");
	String	poType = (String) request.getAttribute("PoHeader_poType");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	if (HiltonUtility.isEmpty(poType)) {
		poType = "PO";
	}
%>

<tsa:hidden name="PoHeader_poType" value="<%=poType%>"/>
<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqVendor_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqVendor_vendorId" value=""/>
<tsa:hidden name="formtype" value="PO"/>
<tsa:hidden name="createdfrom" value="RFQ"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Bidders List</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=center nowrap>Please select the winning vendor from the list below.</td>
		</tr>
		</table>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td width=100%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=15% height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-supplier", "Supplier")%></b></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-bidResponse")%> width=15% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-bidResponse", "Bid Response")%></b></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-total")%> width=15% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-total", "Total")%></b></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-promiseDate")%> width=15% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-promiseDate", "Promise Date")%></b></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-responseDate")%> width=15% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-responseDate", "Response Date")%></b></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-bidValidTo")%> width=15% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-bidValidTo", "Bid Valid To")%></b></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-paymentTerms")%> width=10% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-paymentTerms", "Terms")%></b></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
		for (int i = 0; i < vendorList.size(); i++)
		{
			RfqVendor vendor = (RfqVendor) vendorList.get(i);
			RfqVendorPK vendorPK = vendor.getComp_id();
			BigDecimal bd_shippingCharges = HiltonUtility.getFormattedDollar(vendor.getShippingCharges(), oid);
			BigDecimal bd_otherCharges = HiltonUtility.getFormattedDollar(vendor.getOtherCharges(), oid);
			BigDecimal bd_taxAmount = HiltonUtility.getFormattedDollar(vendor.getTaxAmount(), oid);
			BigDecimal bd_total = new BigDecimal(0);
			bd_total = bd_total.add(bd_shippingCharges);
			bd_total = bd_total.add(bd_otherCharges);
			bd_total = bd_total.add(bd_taxAmount);

			List bidList = vendor.getRfqBidList();
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
					<td width=15% class=browseRow>
						&nbsp;<a href="javascript: setSelectedVendor('<%=vendorPK.getVendorId()%>'); createOrder(); void(0);"><%=vendorPK.getVendorId()%></a>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-bidResponse")%> width=15% class=browseRow align=center valign=top><%=vendor.getBidResponseCode()%></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-total")%> width=15% class=browseRow align=center valign=top>$<%=HiltonUtility.getFormattedDollar(bd_total, oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-promiseDate")%> width=15% class=browseRow align=center valign=top><%=HiltonUtility.getFormattedDate(vendor.getDatePromised(), oid, userDateFormat)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-responseDate")%> width=15% class=browseRow align=center valign=top><%=HiltonUtility.getFormattedDate(vendor.getDateResponseRecv(), oid, userDateFormat)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-bidValidTo")%> width=15% class=browseRow align=center valign=top><%=HiltonUtility.getFormattedDate(vendor.getBidValidTo(), oid, userDateFormat)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rfq-paymentTerms")%> width=15% class=browseRow align=center valign=top><%=vendor.getPaymentTerms()%></td>
				</tr>
				</table>
<%	} %>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center>
		<a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 alt="Cancel"></a>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var userId = '${esapi:encodeForJavaScript(userId)}';
<%String	s_assigned_only = propertiesManager.getProperty("MISC", "AssignedOnly", "N");
String	s_now = HiltonUtility.getFormattedDate(d_today, oid, userDateFormat);
%>

	function setSelectedVendor(vendor)
	{
		frm.RfqVendor_vendorId.value = vendor;
	}

	function createOrder()
	{
		if (confirm("Select " + frm.RfqVendor_vendorId.value + " as the winning vendor?"))
		{
			var type = frm.PoHeader_poType.value;
			var today = '<%=s_now%>';
			if (type == "RO")
			{
				setOriginalFilter("PoHeader_poType", "=", "BO");
				setOriginalFilter("PoHeader_effectiveDate", "<=", today);
				setOriginalFilter("PoHeader_expirationDate", ">=", today);
				setOriginalFilter("PoHeader_vendorId", "=", frm.RfqVendor_vendorId.value);

				browse('po_blanketbrowse');
			}
			else if (frm.viewType.value == "WIZARD")
			{
				doSubmit('/orders/po_review.jsp', 'PoCreateFromRfq;PoRetrieve');
			}
			else
			{
				doSubmit('/orders/po_summary.jsp', 'PoCreateFromRfq;PoRetrieve');
			}
		}
	}


// End Hide script -->
</SCRIPT>