<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>

<%@ page import="com.tsa.puridiom.supplierportal.menu.MenuManager" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqInfo" %>
<script language='Javascript1.2' src="<%=contextPath%>/supplierportal/scripts/dynamicTables.js"></script>
<%
		List	bidSolicitations = MenuManager.getRecentlyBidSolicitations(oid, user.getVendorId(), user.isQualified());
		List	closedSolicitations = MenuManager.getRecentlyClosedSolicitations(oid, user.getVendorId());
		List matchingCommodities = MenuManager.getSolicitationsByCommodities(oid, user.getVendorId(), user.isQualified());
		String fiscalYear = HiltonUtility.getFiscalYear(oid, userTimeZone);
		int rfqCount = 0;
%>

<script language='Javascript1.2' type="text/javascript">
<!--
	var recentlybidsolicitations = "<%=DictionaryManager.getLabel(oid, "recentlybidsolicitations", "Solicitations I Have Bid On", false)%>";
	var recentlyclosedsolicitations = "<%=DictionaryManager.getLabel(oid, "recentlyclosedsolicitations", "Recently Closed Solicitations", false)%>";
	var matchingcommodities = "<%=DictionaryManager.getLabel(oid, "matchingcommodities", "Solicitations Matching My Commodities", false)%>";

	var mysolicitations = "<%=DictionaryManager.getLabel(oid, "mysolicitations", "My Solicitations", false)%>";
	var searchsolicitations = "<%=DictionaryManager.getLabel(oid, "searchsolicitations", "Search and review solicitations", false)%>";
	var searchmatchingcommodities = "<%=DictionaryManager.getLabel(oid, "searchmatchingcommodities", "Solicitations matching my commodities", false)%>";
	var searchrecentlybidsolicitations = "<%=DictionaryManager.getLabel(oid, "searchrecentlybidsolicitations", "Search solicitations I have bid on", false)%>";
	var searchrecentlyclosedsolicitations = "<%=DictionaryManager.getLabel(oid, "searchrecentlyclosedsolicitations", "Search recently closed solicitations", false)%>";

	var myorders = "<%=DictionaryManager.getLabel(oid, "myorders", "My Orders", false)%>";
	var searchorders = "<%=DictionaryManager.getLabel(oid, "searchorders", "Search and review orders", false)%>";
	var orderspendingack = "<%=DictionaryManager.getLabel(oid, "myorderspendingack", "My Orders Pending Acknowledgement", false)%>";
	var ordersbystatus = "<%=DictionaryManager.getLabel(oid, "ordersbystatus", "Purchase Orders", false)%>";
	var solicitationsbystatus = "<%=DictionaryManager.getLabel(oid, "solicitationsbystatus", "Solicitations", false)%>";

	var myinvoices = "<%=DictionaryManager.getLabel(oid, "myinvoices", "My Invoices", false)%>";
	var newinvoice = "<%=DictionaryManager.getLabel(oid, "newinvoice", "Create a new invoice", false)%>";
	var searchinvoices = "<%=DictionaryManager.getLabel(oid, "searchinvoices", "Search and review invoices", false)%>";

	var otheroptions = "<%=DictionaryManager.getLabel(oid, "otheroptions", "Other Options", false)%>";
	var myprofile = "<%=DictionaryManager.getLabel(oid, "myprofile", "View My Profile", false)%>";
	var prequalify = "<%=DictionaryManager.getLabel(oid, "prequalify", "Pre-Qualification Process", false)%>";
	var downloaddocs = "<%=DictionaryManager.getLabel(oid, "downloaddocs", "Download Documents", false)%>";
	var changepswd = "<%=DictionaryManager.getLabel(oid, "changepswd", "Change My Password", false)%>";

	var surplusoptions = "<%=DictionaryManager.getLabel(oid, "surplusoptions", "Surplus Items", false)%>";
	var searchsurplus = "<%=DictionaryManager.getLabel(oid, "searchsurplus", "Search and review surplus auctions", false)%>";

	var context = "<%=contextPath.substring(1)%>";

	document.write("<scr" + "ipt language='Javascript1.2' src='<%=contextPath%>/supplierportal/menu/main_menu_arrays.js' type='text/javascript'><\/scr" + "ipt>");
	document.write("<scr" + "ipt language='Javascript1.2' src='<%=contextPath%>/supplierportal/menu/main_menu_options.js' type='text/javascript'><\/scr" + "ipt>");
//-->
</script>

<%@ include file="/supplierportal/menu/graph-data.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--

	var bIsQualified = <%=user.isQualified()%>;
	var bIsGuest = <%=user.isGuest()%>;
	var rfqsActive = <%=rfqsActive%>;
	var extRfqsActive = <%=extRfqsActive%>;
	var posActive = <%=posActive%>;
	var vouchersActive = <%=vouchersActive%>;
	var salesActive = <%=salesActive%>;

	if (rfqsActive || extRfqsActive) {
		MenuArray0 = createSolicitationsByCommodities(MenuArray0[0]);
		MenuArray1 = createRecentlyBidSolicitations(MenuArray1[0]);
		MenuArray2 = createRecentlyClosedSolicitations(MenuArray2[0]);
		MenuArray3 = createMySolicitationOptions(MenuArray3[0]);
		//MenuArray9 = createRfqByStatusGraph(MenuArray9[0], rfqByStatusGraph);
	}
	if (posActive) {
		MenuArray4 = createMyOrderOptions(MenuArray4[0]);
		MenuArray8 = createOrdersByStatusGraph(MenuArray8[0], poStatusGraph);
	}
	if (vouchersActive) {
		MenuArray5 = createMyInvoiceOptions(MenuArray5[0]);
	}
	if (salesActive) {
		MenuArray6 = createSurplusOptions(MenuArray6[0]);
	}
	MenuArray7 = createOtherOptions(MenuArray7[0]);

	function createRecentlyBidSolicitations(originalArray)
	{
		var options = new Array();
		var i = 1;
		options[0] = originalArray;

<%	if (bidSolicitations != null)
		{
			rfqCount = bidSolicitations.size();
			if (rfqCount > 10)
			{
				rfqCount = 10;
			}
			for (int i = 0; i < rfqCount; i++)
			{
				RfqInfo rfqInfo = (RfqInfo) bidSolicitations.get(i);
				String rfqNumber = rfqInfo.getRfqNumber();
				String rfqAmendment = rfqInfo.getRfqAmendment();
				String rfqDesc = rfqInfo.getRfqDescription();

				rfqDesc = rfqDesc.replace('"', '~');
				rfqDesc = rfqDesc.replaceAll("~", "'");
				rfqDesc = rfqDesc.replaceAll("\r\n", " ");
				rfqDesc = rfqDesc.replaceAll("\r", " ");
				rfqDesc = rfqDesc.replaceAll("\n", " ");

				if (!HiltonUtility.isEmpty(rfqDesc))
				{
					rfqDesc = " - " + rfqDesc;
				}

				if (!HiltonUtility.isEmpty(rfqAmendment) && !rfqAmendment.equals("0000") && !rfqAmendment.equals("0"))
				{
					rfqNumber = rfqNumber + " - " + String.valueOf(rfqAmendment);
				}
%>
					options[<%=i+1%>] = new Array("<%=rfqNumber%>", "javascript: viewRfq('<%=rfqInfo.getIcRfqHeader()%>'); void(0);","<span class=raquo>&raquo;</span>", "<span style='text-overflow: ellipsis; overflow: hidden; white-space: nowrap; width:300px; position: absolute;'>&nbsp;(<%=DocumentStatus.toString(rfqInfo.getStatus())%>)<%=rfqDesc%></span>");
					i++;
<%		}
		}	%>
			return options;
	}

	function createRecentlyClosedSolicitations(originalArray)
	{
		var options = new Array();
		var i = 1;
		options[0] = originalArray;

<%	if (closedSolicitations != null)
		{
			rfqCount = closedSolicitations.size();
			if (rfqCount > 10)
			{
				rfqCount = 10;
			}
			for (int i = 0; i < rfqCount; i++)
			{
				RfqInfo rfqInfo = (RfqInfo) closedSolicitations.get(i);
				String rfqNumber = rfqInfo.getRfqNumber();
				String rfqAmendment = rfqInfo.getRfqAmendment();
				String rfqDesc = rfqInfo.getRfqDescription();

				rfqDesc = rfqDesc.replace('"', '~');
				rfqDesc = rfqDesc.replaceAll("~", "'");
				rfqDesc = rfqDesc.replaceAll("\r\n", " ");
				rfqDesc = rfqDesc.replaceAll("\r", " ");
				rfqDesc = rfqDesc.replaceAll("\n", " ");

				if (!HiltonUtility.isEmpty(rfqDesc))
				{
					rfqDesc = " - " + rfqDesc;
				}

				if (!HiltonUtility.isEmpty(rfqAmendment) && !rfqAmendment.equals("0000") && !rfqAmendment.equals("0"))
				{
					rfqNumber = rfqNumber + " - " + String.valueOf(rfqAmendment);
				}
%>
					options[<%=i+1%>] = new Array("<%=rfqNumber%>", "javascript: viewClosedRfq('<%=rfqInfo.getIcRfqHeader()%>'); void(0);","<span class=raquo>&raquo;</span>", "<span style='text-overflow: ellipsis; overflow: hidden; white-space: nowrap; width:300px; position: absolute;'>&nbsp;(<%=DocumentStatus.toString(rfqInfo.getStatus())%>)<%=rfqDesc%></span>");
					i++;
<%		}
		}	%>
					return options;
	}

	function createSolicitationsByCommodities(originalArray)
	{
		var options = new Array();
		var i = 1;
		options[0] = originalArray;
<%	if (matchingCommodities != null)
		{
			rfqCount = matchingCommodities.size();
			if (rfqCount > 10)
			{
				rfqCount = 10;
			}
			for (int i = 0; i < rfqCount; i++)
			{
				RfqInfo rfqInfo = (RfqInfo) matchingCommodities.get(i);
				String rfqNumber = rfqInfo.getRfqNumber();
				String rfqAmendment = rfqInfo.getRfqAmendment();
				String rfqDesc = rfqInfo.getRfqDescription();

				rfqDesc = rfqDesc.replace('"', '~');
				rfqDesc = rfqDesc.replaceAll("~", "'");
				rfqDesc = rfqDesc.replaceAll("\r\n", " ");
				rfqDesc = rfqDesc.replaceAll("\r", " ");
				rfqDesc = rfqDesc.replaceAll("\n", " ");

				if (!HiltonUtility.isEmpty(rfqDesc))
				{
					rfqDesc = " - " + rfqDesc;
				}

				if (!HiltonUtility.isEmpty(rfqAmendment) && !rfqAmendment.equals("0000") && !rfqAmendment.equals("0"))
				{
					rfqNumber = rfqNumber + " - " + String.valueOf(rfqAmendment);
				}
%>
				options[<%=i+1%>] = new Array("<%=rfqNumber%>", "javascript: viewRfq('<%=rfqInfo.getIcRfqHeader()%>'); void(0);","<span class=raquo>&raquo;</span>", "<span style='text-overflow: ellipsis; overflow: hidden; white-space: nowrap; width:300px; position: absolute;'>&nbsp;(<%=DocumentStatus.toString(rfqInfo.getStatus())%>)<%=rfqDesc%></span>");
				i++;
<%		}
		}	%>
			return options;
	}

//-->
</script>

<%
	Calendar rightNow = Calendar.getInstance();
	String	s_last30 = new String();

	rightNow.add(rightNow.DAY_OF_MONTH, - 30);

	String errorMsg = (String) request.getAttribute("warningMsg");
	if (errorMsg == null) {
		errorMsg = "";
	}
%>
<br>
<tsa:hidden name="warningPage" value="menu/main_menu.jsp"/>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr><td align="center"><b> Welcome to the Puridiom Supplier Portal!</b></td></tr>
<tr><td><br></td></tr>
</table>


<table border="0" cellspacing="0" cellpadding="2" width="680px">
<tr>
	<td colspan=2 align=center class="error"><%=errorMsg%></td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td width="62%" valign="top" align="center" id="leftMenu">&nbsp;</td>
	<td>&nbsp;</td>
	<td width="34%" valign="top" align="center" id="rightMenu">&nbsp;</td>
	<td>&nbsp;</td>
</tr>
</table>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function setSearch(x)
	{
		if (x == "MyCommodities") {
<%	if (user.isGuest()) {%>
			alert("As a 'Guest' this option is not available.");
			return false;
<%	} else if (user.isQualified()) {%>
			//setOriginalFilter("RfqHeader_dueDate", ">=", "<%=HiltonUtility.getFormattedDate(new Date(), oid, "yyyy-MM-dd")%>");
			setOriginalFilter("VendorCommRel_id_vendorId", "=", "<%=user.getVendorId()%>");
			setOriginalFilter("RfqHeader_status", "=", "2005");
			browse("rfq-bidboard-posts-by-vendorcomm");
<%	} else {%>
			//setOriginalFilter("RfqHeader_dueDate", ">=", "<%=HiltonUtility.getFormattedDate(new Date(), oid, "yyyy-MM-dd")%>");
			setOriginalFilter("VendorRegCommRel_id_vendorId", "=", "<%=user.getVendorId()%>");
			setOriginalFilter("RfqHeader_status", "=", "2005");
			browse("rfq-bidboard-posts-by-vendorregcomm");
<%	}%>
		}
		else if (x == "MyLocation") {

		}
		else if (x == "MyBids") {
<%	if (user.isGuest()) {%>
			alert("As a 'Guest' this option is not available.");
			return false;
<%	} else if (user.isQualified()) {%>
			//setOriginalFilter("RfqHeader_dueDate", ">=", "<%=HiltonUtility.getFormattedDate(new Date(), oid, "yyyy-MM-dd")%>");
			setOriginalFilter("RfqVendor_id_vendorId", "=", "<%=user.getVendorId()%>");
			browse("rfq-bidboard-posts-by-vendor");
<%	} else {%>
			//setOriginalFilter("RfqHeader_dueDate", ">=", "<%=HiltonUtility.getFormattedDate(new Date(), oid, "yyyy-MM-dd")%>");
			setOriginalFilter("RfqVendor_id_vendorId", "=", "<%=user.getVendorId()%>");
			browse("rfq-bidboard-posts-by-vendorreg");
<%	}%>
		}
		else if (x == "Open") {
			setOriginalFilter("RfqHeader_status", "=", "2005");
			setOriginalFilter("RfqHeader_dueDate", ">=", "<%=HiltonUtility.getFormattedDate(new Date(), oid, "yyyy-MM-dd")%>");
			browse("rfq-bidboard-posts");
		}
		else if (x == "Closed") {
			setOriginalFilter("RfqHeader_dueDate", ">=", "<%=HiltonUtility.getFormattedDate(rightNow.getTime(), oid, "yyyy-MM-dd")%>");
			setOriginalFilterWithLogicalOper("RfqHeader_dueDate", "<", "<%=HiltonUtility.getFormattedDate(new Date(), oid, "yyyy-MM-dd")%>", "OR");
//			setOriginalFilter("RfqHeader_status", "=", "33", "", "N");
//			setOriginalFilter("RfqHeader_status", ">", "2015");
			browse("rfq-bidboard-posts-closed");
		}
	}

	function uploadDocs(type) {
		popupParameters = "VendorDocument_icRfqHeader=0;VendorDocument_vendorId=<%=user.getVendorId()%>;VendorDocument_docType=" + type;
		doSubmitToLookup('/supplierportal/user/supplier_attachments.jsp', 'VendorDocumentRetrieveByVendor', 'width=700', 'height=350');
	}

	function viewClosedRfq(ic)
	{
		var myCell = document.getElementById("hiddenFields");
		var newInputField = "<input type='hidden' name='RfqHeader_icRfqHeader' value='" + ic + "'>";
		newInputField = newInputField +  "<input type='hidden' name='VendorQuestion_icRfqHeader' value='" + ic + "'>";
		newInputField = newInputField +  "<input type='hidden' name='RfqBid_vendorId' value='<%=user.getVendorId()%>'>";

		myCell.innerHTML = newInputField;

		doSubmit('/rfq/rfq_summary_postauction.jsp','RfqRetrieve');
	}

	function viewRfq(ic)
	{
		var myCell = document.getElementById("hiddenFields");
		var newInputField = "<input type='hidden' name='RfqHeader_icRfqHeader' value='" + ic + "'>";
		newInputField = newInputField +  "<input type='hidden' name='VendorQuestion_icRfqHeader' value='" + ic + "'>";
		newInputField = newInputField +  "<input type='hidden' name='RfqBid_vendorId' value='<%=user.getVendorId()%>'>";

		myCell.innerHTML = newInputField;

		doSubmit('','RfqRetrieve;SetEventPage');
	}

	function checkGraphData()
	{
<%	if ( !poStatusGraphHasData ) { %>
			toggleDisplay(8);
<%	}
		if ( !rfqByStatusGraphHasData ) { %>
			toggleDisplay(9);
<%	} %>
	}

	function viewOrdersByStatus(status)
	{
		setOriginalFilter("PoHeader_status", "=", status);
		setOriginalFilter("PoHeader_fiscalYear", "=", "<%=fiscalYear%>");
		setOriginalFilter("PoHeader_vendorId", "=", frm.vendorId.value);
		browse('poheader');
	}

	function viewRfqsByStatus(status)
	{
		setOriginalFilter("RfqHeader_status", "=", status);
		setOriginalFilter("RfqHeader_fiscalYear", "=", "<%=fiscalYear%>");
		browse('myrfqs');
	}

//-->
</SCRIPT>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>