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


<script>
function showMenu(menu, mouseover)
{
	menu = (document.getElementById)? document.getElementById(menu): (document.all)? document.all[menu]: (document.layers)? document.layers[menu]: null;
	mouseover = (document.getElementById)? document.getElementById(mouseover): (document.all)? document.all[mouseover]: (document.layers)? document.layers[mouseover]: null;

	menu.innerHTML = mouseover.innerHTML;
}
</script>


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

<table cellpadding=3 cellspacing=1>
<tr>
<td id=menu colspan=3 style="position:absolute; left:8px; top:79px">&nbsp;</td>
</tr>
</table>

<!-- Sub Menus -->
<div id=submenu1 style="visibility:hidden;display:none">
<table cellpadding=3 cellspacing=1 bgcolor="#ffffff" bordercolor="#9933CC" border=1>
<tr>
<td bgcolor="#ffffff"><a style="color:black; text-decoration:none; font-family:arial; font-size:8pt" href="#">Link A</a></td>
<td bgcolor="#ffffff"><a style="color:black; text-decoration:none; font-family:arial; font-size:8pt" href="#">Link B</a></td>
</tr>
</table>
</div>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
				<tr>
					<td width=5% class=browseHdr nowrap>&nbsp;Line #</td>
					<td width=14% class=browseHdr nowrap>&nbsp;Item/Part #</td>
					<td width=11% class=browseHdr nowrap>Commodity</td>
					<td width=11% class=browseHdr nowrap>Line Status</td>
					<td width=11% class=browseHdr nowrap align=right>Quantity</td>
					<td width=8% class=browseHdr nowrap>UOM&nbsp;&nbsp;&nbsp;&nbsp;|</td>
					<td width=14% class=browseHdr nowrap>Vendor Id</td>
					<td width=13% class=browseHdr nowrap align=right>Unit Price</td>
					<td width=13% class=browseHdr nowrap align=right>Extended Price</td>
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
									<td width=7% class=browseRow nowrap align=right><%=i+1%>&nbsp;</td>
									<td width=15% class=browseRow nowrap><%=s_ItemNumber%></td>
									<td width=12% class=browseRow nowrap><%=s_commodity%></td>
									<td width=11% class=browseRow nowrap><%=DocumentStatus.toString(s_lineStatus, oid)%></td>
									<td width=12% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), oid)%></td>
									<td width=8% class=browseRow nowrap><%=s_uom%></td>
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

					BigDecimal bd_bidUnitPrice = HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid);
					BigDecimal bd_bidExtendedPrice = new BigDecimal(0);
					bd_bidExtendedPrice = ( (bd_bidQuantity.multiply(bd_bidUnitPrice)).multiply(b_umFactor) ).setScale(Integer.valueOf(s_dollar_decimals).intValue(),BigDecimal.ROUND_HALF_UP);
%>
								<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow valign=top>
								<tr>
									<td  width=14% class=browseRow valign=top><%=s_bidVendorId%></td>
									<td  width=13% class=browseRow align=right valign=top><%=bd_bidUnitPrice%></td>
									<td  width=13% class=browseRow align=right valign=top><%=HiltonUtility.getFormattedDollar(bd_bidExtendedPrice, oid)%></td>
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