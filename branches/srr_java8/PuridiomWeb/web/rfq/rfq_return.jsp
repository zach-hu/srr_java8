<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>

<%
	List vendorList = (List) request.getAttribute("rfqVendorList");
	String s_icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String s_rfqNumber = (String) request.getAttribute("RfqHeader_rfqNumber");
	String s_rfqAmendment = (String) request.getAttribute("RfqHeader_rfqAmendment");
	String s_rfqStatus = (String) request.getAttribute("RfqHeader_status");
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqVendor_vendorId" value=""/>
<tsa:hidden name="RfqVendor_shippingCharges" value=""/>
<tsa:hidden name="RfqVendor_taxAmount" value=""/>
<tsa:hidden name="vendorCode" value=""/>
<tsa:hidden name="Vendor_vendorId" value=""/>

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
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
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
					<td width=23% height=18px class=browseHdr>&nbsp;<b>Vendor</b></td>
					<td width=15% height=18px class=browseHdr align=center>&nbsp;<b>Bid Response</b></td>
					<td width=15% height=18px class=browseHdr align=center>&nbsp;<b>Total</b></td>
					<td width=13% height=18px class=browseHdr align=center>&nbsp;<b>Promise Date</b></td>
					<td width=14% height=18px class=browseHdr align=center>&nbsp;<b>Response Date</b></td>
					<td width=12% height=18px class=browseHdr align=center>&nbsp;<b>Bid Valid To</b></td>
					<td width=8% height=18px class=browseHdr align=center>&nbsp;<b>Terms</b></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%	for (int i = 0; i < vendorList.size(); i++)
		{
			RfqVendor vendor = (RfqVendor) vendorList.get(i);
			RfqVendorPK vendorPK = vendor.getComp_id();
			BigDecimal bd_shippingCharges = HiltonUtility.getFormattedDollar(vendor.getShippingCharges(), oid);
			BigDecimal bd_taxAmount = HiltonUtility.getFormattedDollar(vendor.getTaxAmount(), oid);
			String s_vendorName = VendorManager.getInstance().getVendorName(oid, vendorPK.getVendorId());
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=26% class=browseRow>
						&nbsp;<a href="javascript: setSelectedVendor('<%=vendorPK.getVendorId()%>', '<%=bd_shippingCharges%>', '<%=bd_taxAmount%>'); rfqReturn('<%=s_vendorName%>'); void(0);"><%=s_vendorName%></a>
					</td>
					<td width=15% class=browseRow align=center valign=top><%=vendor.getBidResponseCode()%></td>
					<td width=15% class=browseRow align=center valign=top>$<%=HiltonUtility.getFormattedDollar(vendor.getBidTotal(), oid)%></td>
					<td width=12% class=browseRow align=center valign=top><%=HiltonUtility.getFormattedDate(vendor.getDatePromised(), oid, userDateFormat)%></td>
					<td width=12% class=browseRow align=center valign=top><%=HiltonUtility.getFormattedDate(vendor.getDateResponseRecv(), oid, userDateFormat)%></td>
					<td width=12% class=browseRow align=center valign=top><%=HiltonUtility.getFormattedDate(vendor.getBidValidTo(), oid, userDateFormat)%></td>
					<td width=8% class=browseRow align=center valign=top><%=vendor.getPaymentTerms()%></td>
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
<%	if (s_view.equals("WIZARD")) { %>
		<div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_review.jsp', 'RfqRetrieve'); void(0);">Cancel</a></div>
<%	} else { %>
		<div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqRetrieve'); void(0);">Cancel</a></div>
<%	} %>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function setSelectedVendor(vendor, shipping, tax)
	{
		frm.vendorCode.value = vendor;
		frm.RfqVendor_vendorId.value = vendor;
		frm.RfqVendor_shippingCharges.value = shipping;
		frm.RfqVendor_taxAmount.value = tax;
		frm.Vendor_vendorId.value = vendor;
	}

	function rfqReturn(vendorName)
	{
		if (confirm("Select " + vendorName + " as the winning vendor?"))
		{
			if (frm.viewType.value == "WIZARD")
			{
				doSubmit('/rfq/rfq_review.jsp', 'RfqForward;RfqRetrieve');
			}
			else
			{
				doSubmit('/rfq/rfq_summary.jsp', 'RfqForward;RfqRetrieve');
			}
		}
	}

// End Hide script -->
</SCRIPT>