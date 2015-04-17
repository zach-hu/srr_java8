<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Date" %>
<%
/*************	TEMPORARY DISPLAY VARIABLES	*************/
	boolean b_display_currency = true;
/**********	END TEMPORARY DISPLAY VARIABLES	***********/

	PropertiesManager propertiesManager = PropertiesManager.getInstance(user.getOrganizationId());
	String	s_lic = "";
	String	s_monitor = (String) request.getAttribute("monitor");
	String	ss_doc_url = "C:\\Hilton\\SystemDocuments\\";
	String	ss_email_addr = "";
	boolean b_exists = false;

	if (s_monitor == null) {s_monitor = "N";}

	RfqHeader	rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	RfqVendor	rfqVendor = new RfqVendor();
	List	rfqCommentList = rfqHeader.getDocCommentList();
	List	rfqSupplierList = (List) request.getAttribute("rfqVendorList");

	String	s_rfq_number = rfqHeader.getRfqNumber();
	String	s_rfq_status = rfqHeader.getStatus();
	String	s_rfq_amendment = rfqHeader.getRfqAmendment();
	String	s_webpost	= rfqHeader.getWebpost();
	String	s_bid_access	= rfqHeader.getBidAccess();
	String	s_bid_item_options = rfqHeader.getBidItemOptions();
	String	s_rfq_type = rfqHeader.getRfqType();
	boolean	b_submit_access	= true;
	boolean	b_graph_access	= true;
	boolean	b_download_access	= true;
	boolean	b_require_all	= false;
	boolean	b_openauction	= false;
	boolean	b_require_entry	= false;
	boolean	b_show_low_amt	= false;
	boolean	b_low_subtotal	= false;
	boolean	b_bids_entered = false;
	boolean	b_show_submit_bid = false;
	int	i_supplierRows = 0;

	if (rfqSupplierList != null) {
		i_supplierRows = rfqSupplierList.size();
	}

	for (int isr=0; isr < i_supplierRows; isr++) {
		RfqVendor vendor = (RfqVendor) rfqSupplierList.get(isr);
		if (vendor.getComp_id().getVendorId().equalsIgnoreCase(user.getVendorId())) {
			rfqVendor = vendor;
			b_exists = true;
			break;
		}
	}

	if ( s_webpost.indexOf("B") < 0) {
		b_submit_access = false;	// No online bidding for this solicitation
	}
	if (user.isGuest()) {
		b_submit_access = false;	// Guests do not have access to submit bids
		b_download_access = false;// Guests do not have access to download solicitations
	}
	if ( s_bid_access.equals("I") && (!b_exists) ) {
		b_submit_access = false;	// Restricted to listed suppliers only
		b_download_access = false;	// Restricted to listed suppliers only
	}

	b_require_all = s_bid_item_options.equals("A");
	b_openauction = rfqHeader.getAuctionType().trim().equals("O");

	if (b_openauction) {
		b_require_entry = rfqHeader.getLowestBidReq().trim().equals("Y");
		b_low_subtotal = rfqHeader.getLowestBidSource().trim().equals("S");
		b_show_low_amt = rfqHeader.getLowestDisplay().trim().equals("A");
	}
%>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/bids.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" TYPE="text/javascript">
<!--
	DOM = (document.getElementById) ? true : false;
	NS4 = (document.layers) ? true : false;
	NS4old = (NS4 && (parseFloat(navigator.appVersion) < 4.02));
	IE = (document.all) ? true : false;
	IE4 = IE && !DOM;
	Mac = (navigator.appVersion.indexOf("Mac") != -1);
	IE4M = IE4 && Mac;
	IsMenu = (DOM || (NS4 && !NS4old) || (IE && !IE4M));

	if(window.event + "" == "undefined") event = null;
	function f_PopUp(){return};
	function f_PopDown(){return};
	popUp = f_PopUp;
	popDown = f_PopDown;

	if(IsMenu) {
		document.write("<SCR" + "IPT LANGUAGE='JavaScript1.2' SRC='<%=contextPath%>/supplierportal/scripts/rfq_bidArrays.js' TYPE='text/javascript'><\/SCR" + "IPT>");
		document.write("<SCR" + "IPT LANGUAGE='JavaScript1.2' SRC='<%=contextPath%>/supplierportal/scripts/hierMenusBids.js' TYPE='text/javascript'><\/SCR" + "IPT>");
	}
//-->
</SCRIPT>
<tsa:hidden name="RfqVendor_icRfqHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>
<tsa:hidden name="RfqVendor_vendorId" value="<%=user.getVendorId()%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfq_status%>"/>
<tsa:hidden name="RfqHeader_auctionType" value="<%=rfqHeader.getAuctionType()%>"/>

<table border=0 width=680px cellpadding=0 cellspacing=0 valign=top>
<tr>
	<td align=center>
		<!-- RFQ HEADER -->
		<table border=0 cellspacing=0 cellpadding=0 height=100%>
		<tr>
			<td valign=middle class=hdr12 height=20px align=center>
				<%=RfqType.toString(rfqHeader.getRfqType(), oid)%> # <%=s_rfq_number%>
<%	if (!HiltonUtility.isEmpty(s_rfq_amendment) && !rfqHeader.getRfqAmendment().equals("0")) {%>
				<br>Amendment # <%=s_rfq_amendment%>
<%	}%>
			</td>
		</tr>
		</table>
		<!-- RFQ HEADER END -->
		<br>
		<table border=0 cellspacing=0 cellpadding=0 height=100% align=center>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 height=100% align=center>
				<tr>
					<td class=summary align=center>
						<%	boolean	b_bold = false;
							boolean	commentDisplayed = false;%>
						<%@ include file="/supplierportal/rfq/rfq_bids.jsp" %>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr><td><br></td></tr>
<%	if (b_submit_access) {%>
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=100%>
				<tr>
					<td width=34% align=center><a href="javascript: window.close();  void(0);"><img src="<%=contextPath%>/supplierportal/images/button_close.gif" class=button border=0 tabIndex=999></a></td>
					<td width=33% align=center><a href="javascript: submitBids(); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_submit.gif" class=button border=0 tabIndex=110 alt="update bids"></a></td>
				</tr>
				</table>
			</td>
		</tr>
<%	}%>
		<tr>
			<td>
				<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>
				<tsa:hidden name="as_action" value="REDIRECT"/>
				<tsa:hidden name="as_rfb_action" value="REDIRECT"/>
				<tsa:hidden name="monitor" value="<%=s_monitor%>"/>
				<tsa:hidden name="as_survey_complete" value="<%=b_exists%>"/>
				<tsa:hidden name="as_info_changed" value="N"/>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	var allowEdit = true;
	var fldObject = null;
	var fldFromObject = null;
	var fldToObject = null;
	var currentRow = 0;
	var bid_timer = null;
	var b_timer = false;
	var monitor = frm.monitor.value;
	var lookup_window = null;
	var submitToMonitor = "N";
	var dollar_dec = <%=propertiesManager.getProperty("MISC", "DollarDecimals", "2")%>;
	var	lineCount = <%=i_lineRows%>;

	bidsEntered = <%=b_bids_entered%>;
	requireAllBids = <%=b_require_all%>;
	bidVariance = <%=rfqHeader.getBidVariance()%>;

	setBidFromBidCode();

	function thisLoadPopup() {
		f_StartIt();
	}

	function submitBids() {
		var b_entered = setBidsForUpdate();

		if ( (frm.as_action.value == "UPDATE") || (frm.as_rfb_action.value == "UPDATE") || (frm.as_info_changed.value == "Y") ) {
		//	if ( checkRequired() ) {
				//remove dummy fields before submitting
				document.getElementById("dummyFields").innerHTML = "";

				setBidHistoryData();

				if (frm.as_rfb_action.value == "UPDATE") {
					opener.setTimeout("b_submit=false;",10);
					opener.setTimeout("refreshBids();",3000);

					if (b_entered) {
						frm.RfqVendor_bidsEntered.value = "Y";
					} else {
						frm.RfqVendor_bidsEntered.value = "N";
					}
					doSubmit("/system/close_window.jsp", "RfqBidHistorySubmit;RfqBidUpdate");
				} else {
					opener.b_submit = false;
					refreshBids();
					doSubmit("/system/close_window.jsp", "DoNothing");
				}
//			} else {
//				return false;
//			}
		}
		else {
<%	if (!b_exists) {%>
	    if (!checkRequiredBids()) {
			return false;
		}
	    else {
			alert("You have not entered any bids or information to submit.");
			submitToMonitor = "N";
			return false;
		}
<%  } else {%>
			alert("You have not entered any bids to submit.");
			return false;
<%	}%>
		}
	}

	function stopMonitor() {
		//do nothing
	}

	function openNotes(lic, line, edit) {
		setHiddenFields("<input type=hidden name=RfqHeader_icRfqHeader value=<%=rfqHeader.getIcRfqHeader()%>><input type=hidden name=RfqNote_icHeader value=<%=rfqHeader.getIcRfqHeader()%>><input type=hidden name=RfqNote_icLine value=" + lic + "><input type=hidden name=RfqNote_vendorId value=<%=user.getVendorId()%>><input type=hidden name=ln value=" + line + "><input type=hidden name=edit value=" + edit +"><input type=hidden name=returnPage value=/rfq/rfq_bids_edit.jsp><input type=hidden name=returnHandler value=RfqRetrieve>");
		doSubmit('/rfq/supplier_notes.jsp', 'RfqNoteRetrieveById');
	}

	function viewBidHistory(lic, item, quantity,desciption, commodity, variance, dueDate){
		var newInputField = "";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_icHeader' value='<%=rfqHeader.getIcRfqHeader()%>'>";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_icLine' value='"+lic+"'>";
		newInputField = newInputField + "<input type='hidden' name='bidItemNumber' value='"+item+"'>";
		newInputField = newInputField + "<input type='hidden' name='bidQuantity' value='"+quantity+"'>";
		newInputField = newInputField + "<input type='hidden' name='bidCommodity' value='"+commodity+"'>";
		newInputField = newInputField + "<input type='hidden' name='bidVariance' value='"+variance+"'>";
		newInputField = newInputField + "<input type='hidden' name='auctionDueDate' value='"+dueDate+"'>";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_vendorId' value='<%=user.getVendorId()%>'>";
		setHiddenFields(newInputField);
		doSubmit('/rfq/supplier_bidHistory.jsp', 'FChartBidHistoryRetrieve');
	}

	function viewBidHistoryItem(icRfqLine, itemNumber){
		var newInputField = "";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_icRfqHeader' value='<%=rfqHeader.getIcRfqHeader()%>'>";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_icRfqLine' value='"+icRfqLine+"'>";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_ItemNumber' value='"+itemNumber+"'>";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_vendorId' value='<%=user.getVendorId()%>'>";
		setHiddenFields(newInputField);
		doSubmit('/rfq/item_bidHistory.jsp', 'RfqBidHistoryUnitPriceListRetrieveByLineVendor');
	}

	function viewBidHistoryItemAll(icRfqLine, itemNumber){
		var newInputField = "";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_icRfqHeader' value='<%=rfqHeader.getIcRfqHeader()%>'>";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_icRfqLine' value='"+icRfqLine+"'>";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_ItemNumber' value='"+itemNumber+"'>";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_vendorId' value='<%=user.getVendorId()%>'>";
		setHiddenFields(newInputField);
		doSubmit('/rfq/item_bidHistory.jsp', 'RfqBidHistoryUnitPriceListRetrieveByLine');
	}

	function doSubmitToLookup (url, handler, w, h) {
		opener.popupUrl = url;
		opener.popupHandler = handler;
		opener.popupUserId = frm.userId.value;
		opener.popupOrganizationId = frm.organizationId.value;

		openWindow(url, w, h);
	}

	function setBidHistoryData() {
		var newInputField = "";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_icRfqHeader' value='<%=rfqHeader.getIcRfqHeader()%>'>";
		<% for (int ilr = 0; ilr < i_lineRows; ilr++) { %>
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_icRfqLine' value='"+frm.RfqBid_icRfqLine<% if (i_lineRows>1) {%>[<%=ilr%>]<%}%>.value+"'>";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_unitPrice' value='"+frm.unit_price[<%=ilr%>].value+"'>";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_quantity' value='"+frm.qty[<%=ilr%>].value+"'>";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_vendorId' value='"+frm.RfqBid_vendorId<% if (i_lineRows>1) {%>[<%=ilr%>]<%}%>.value+"'>";
		<% } %>
		if (!frm.RfqVendor_bidsEntered) {
			newInputField = newInputField + "<input type=hidden name='RfqVendor_bidsEntered' value='Y'>";
		}
		setHiddenFields(newInputField);
	}

///TEST
// end hiding contents -->
</SCRIPT>

<%@ include file="/supplierportal/system/prevent_caching.jsp" %>