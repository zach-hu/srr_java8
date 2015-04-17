<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ include file="/supplierportal/rfq/bid_instructions.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.*" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(user.getOrganizationId());
	String	s_lic = "";
	String	s_monitor = (String) request.getAttribute("monitor");
	boolean b_exists = false;

	if (HiltonUtility.isEmpty(s_monitor)) {s_monitor = "N";}

	RfqHeader	rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	// BIDHISTORY CODE
	List rfqBidHistoryList = (List) request.getAttribute("rfqBidHistoryList");
	String action = "old";
	if (rfqBidHistoryList == null || rfqBidHistoryList.isEmpty()) {
		rfqBidHistoryList = new ArrayList();
		action = "new";
	}

	RfqVendor	rfqVendor = new RfqVendor();
	List	rfqQuestionList = rfqHeader.getRfqQuestionList();
	List	rfqAttachmentList = rfqHeader.getDocAttachmentList();
	List	rfqCommentList = rfqHeader.getDocCommentList();
	List	rfqSupplierList = (List) request.getAttribute("rfqVendorList");

	String	s_webpost	= rfqHeader.getWebpost();
	String	s_bid_access	= rfqHeader.getBidAccess();
	String	s_bid_item_options = rfqHeader.getBidItemOptions();
	String	s_rfq_type = rfqHeader.getRfqType();
	boolean	b_submit_access	= true;
	boolean	b_graph_access	= false;
	boolean	b_download_access	= true;
	boolean	b_require_all	= false;
	boolean	b_openauction	= false;
	boolean	b_require_entry	= false;
	boolean	b_show_low_amt	= false;
	boolean	b_low_subtotal	= false;
	boolean	b_bids_entered = false;
	boolean	b_show_submit_bid = false;
	boolean b_allow_monitor = false;
	boolean b_allow_email = true;
	int	i_questions = 0;
	int	i_attachments = 0;
	int	i_supplierRows = 0;

	if (rfqQuestionList != null) {
		i_questions = rfqQuestionList.size();
	}
	if (rfqAttachmentList != null) {
		i_attachments = rfqAttachmentList.size();
	}
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

	String	eventType = "Opening Bid";
	String	dueDate = HiltonUtility.getFormattedDate(rfqHeader.getBidEndDate(), user.getOrganizationId());
	String	dueTime = rfqHeader.getBidEndTime();
	String	dueDateTimeString = HiltonUtility.getFullDateTimeString(rfqHeader.getBidEndDate(), rfqHeader.getBidEndTime(), rfqHeader.getTimeZone(), user.getOrganizationId());
	String	s_due = dueDateTimeString;
%>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/bids.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/date_check.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/timer.js"></SCRIPT>
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

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=rfqHeader.getStatus()%>"/>
<tsa:hidden name="RfqHeader_auctionType" value="<%=rfqHeader.getAuctionType()%>"/>
<tsa:hidden name="BidSequence" value="<%=rfqHeader.getStatus()%>"/>

<%@ include file="/supplierportal/rfq/rfq_event_header.jsp" %>

<table border=0 width=680px cellpadding=0 cellspacing=0 valign=top>
<tr>
	<td align=center>
		<!-- RFQ INSTRUCTIONS -->
		<table border=0 cellspacing=0 cellpadding=0 height=100% align=center width=90%>
		<tr>
			<td width=90% align=center>
<%	if ( user.isGuest() ) {%><!--[Guest Instructions Here]-->
				<%=s_guest_instr%>
<%	} else if (!b_submit_access) {%><!--[Restricted Instructions Here]-->
				<%=s_rstr_instr%>
<%	} else if (s_rfq_type.equals("RI")) {%><!--[RFI Instructions Here]-->
				<%=s_rfi_instr%>
<%	} else if (s_rfq_type.equals("RP")) {%><!--[RFP Instructions Here]-->
				<%=s_rfp_instr%>
<%	} else if (s_rfq_type.equals("IB")) {%><!--[Invitation To Bid Instructions Here]-->
				<%=s_itb_instr%>
<%	} else {%>
				<%=s_rfq_instr%>
<%	}%>
			</td>
		</tr>
		</table>
		<!-- RFQ INSTRUCTIONS END -->
		<br>
		<table border=0 cellspacing=0 cellpadding=0 height=100% align=center>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 height=100% align=center>
				<tr>
					<td class=summary align=center>
						<table border=0 cellpadding=2 cellspacing=0 class=summary width=650px>
						<%@ include file="/supplierportal/rfq/rfq_hdr_summary.jsp" %>
						<%@ include file="/supplierportal/rfq/rfq_comments.jsp" %>
						<%@ include file="/supplierportal/rfq/rfq_attachments.jsp" %>
						</table>
					</td>
				</tr>
				<tr><td><br></td></tr>
				<tr>
					<td class=summary align=center>
						<%@ include file="/supplierportal/rfq/rfq_bids.jsp" %>
<%	if (!HiltonUtility.isEmpty(s_after_comment)) { %>
						<table border=0 cellpadding=0 cellspacing=0 class=summary width=650px>
						<tr><td colspan=2><br></td></tr>
						<tr>
							<td width=75px valign=top align=right><b>Comments:</b></td>
							<td valign=top>&nbsp;<%=s_after_comment%></td>
						</tr>
						</table>
<%	} %>
					</td>
				</tr>
<%	if (b_submit_access) {%>
				<tr><td><br></td></tr>
				<tr>
					<td class=summary align=center>
						<br>
						<%@ include file="/supplierportal/rfq/rfq_supplier.jsp" %>
						<br>
					</td>
				</tr>
<%	}%>
				</table>
			</td>
		</tr>
		<tr><td><br></td></tr>
<%	if (b_submit_access) {%>
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=100%>
				<tr>
<%		if (b_bids_entered) {%>
					<td width=25% align=center><a href="javascript: stopMonitor(); withdrawBids(); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_withdraw_bids.gif" class=button border=0 tabIndex=999 alt="withdraw bids"></a></td>
					<td width=25% align=center><a href="javascript: returnAbort();  void(0);"><img src="<%=contextPath%>/supplierportal/images/button_cancel.gif" class=button border=0 tabIndex=999></a></td>
					<td width=25% align=center><a href="javascript: saveBidHistory(); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_save.gif" class=button border=0 tabIndex=110 alt="save bids"></a></td>
					<td width=25% align=center><a href="javascript: submitBids(); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_submit.gif" class=button border=0 tabIndex=110 alt="update bids"></a></td>
<%		} else {%>
					<td width=33% align=center><a href="javascript: returnAbort(); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_cancel.gif" class=button border=0 tabIndex=999></a></td>
					<td width=33% align=center><a href="javascript: saveBidHistory(); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_save.gif" class=button border=0 tabIndex=110 alt="save bids"></a></td>
					<td width=34% align=center><a href="javascript: submitBids(); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_submit.gif" class=button border=0 tabIndex=110 alt="submit"></a></td>
<%		}%>
				</tr>
				</table>
			</td>
		</tr>
<%	}%>
		<tr>
			<td>
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

	function thisLoad() {
		start();
		f_StartIt();
	}

<%	if (b_submit_access) {%>
	setBidFromBidCode();
	defaultDates();
<%	}%>

	function withdrawBids() {
		if (verifyAction("Click 'OK' to withdraw from the bid process for this solicitation.")) {
			stopMonitor();
			doSubmit('/menu/main_menu.jsp', 'RfqVendorDelete');
		}
		else {
			return false;
		}
	}

	function emailBuyer() {
		var buyer = "<%=rfqHeader.getBuyer()%>";
		var name = trim(frm.buyer_name);
		var email = trim(frm.buyer_email);
		var url = "/rfq/email_buyer.jsp";
		var params = "";

		name = replaceChar(name, " ", "&nbsp;") ;

		if (email.length > 0) {
			params = params + "?email=" + email;
		}
		else {
			alert("The buyer's email address could not be found.");
			return false;
		}

		if (buyer.length > 0) {
			params = params + "&buyer=" + buyer
		}
		if (name.length > 0) {
			params = params + "&name=" + name;
		}

		url = url + params;

		openWindow(url, "width=475", "height=250");
	}

	function submitBids() {
		var b_entered = setBidsForUpdate();

		if ( (frm.as_action.value == "UPDATE") || (frm.as_rfb_action.value == "UPDATE") || (frm.as_info_changed.value == "Y") ) {
			if ( checkRequired() ) {
				if (checkDates()) {
					if (frm.as_survey_complete.value == "false" && <%=(i_questions > 0)%>) {
						alert("You must respond to all questions before submitting bids.");
						questions();
						submitToMonitor = "N";
						return false;
					}
					else {
						if (b_entered) {
							frm.RfqVendor_bidsEntered.value = "Y";
						} else {
							frm.RfqVendor_bidsEntered.value = "N";
						}

						setBidHistoryData();

						//remove dummy fields before submitting
						document.getElementById("dummyFields").innerHTML = "";

						if (frm.as_rfb_action.value == "UPDATE") {
							doSubmit("/rfq/bid_confirm.jsp", "RfqBidHistorySubmit;RfqVendorUpdateById;RfqBidUpdate;RfqRetrieve");
						} else {
							doSubmit("/rfq/bid_confirm.jsp", "RfqBidHistorySubmit;RfqVendorUpdateById;RfqRetrieve");
						}
					}
				} else {
					stopMonitor();
				}
			} else {
				stopMonitor();
			}
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
<%	} else {%>
			if (frm.as_survey_complete.value == "false") {
				alert("You must respond to all questions before submitting bids.");
				questions();
				submitToMonitor = "N";
				return false;
			}
			else {
				alert("You have not entered any bids or information to submit.");
				submitToMonitor = "N";
				return false;
			}
<%	}%>
		}
	}

	function stopMonitor() {
		closeOpenWindows();
	}

	function uploadDocs()
	{
		popupParameters = "RfqHeader_status=" + frm.RfqHeader_status.value + ";VendorDocument_icRfqHeader=<%=rfqHeader.getIcRfqHeader()%>;VendorDocument_vendorId=<%=user.getVendorId()%>";
		doSubmitToLookup('/rfq/supplier_attachments.jsp', 'VendorDocumentRetrieveByVendor', 'width=700', 'height=350');
	}

	function canMonitor() {
		return false;
	}

	function openNotes(lic, line, edit) {
		popupParameters = "RfqNote_icHeader=<%=rfqHeader.getIcRfqHeader()%>;RfqNote_icLine=" + lic + ";RfqNote_vendorId=<%=user.getVendorId()%>;ln=" + line + ";edit=" + edit;
		doSubmitToLookup('/rfq/supplier_notes.jsp', 'RfqNoteRetrieveById', 'WIDTH=450','HEIGHT=300');
	}

	function viewBidHistory(lic, item, quantity,commodity, variance, dueDate){
		popupParameters= "RfqBidHistory_icHeader=<%=rfqHeader.getIcRfqHeader()%>;RfqBidHistory_icLine=" + lic + ";bidItemNumber="+item+ ";bidQuantity="+quantity+ ";bidCommodity="+commodity+";bidVariance="+variance+";auctionDueDate="+dueDate;
		doSubmitToLookup('/rfq/supplier_bidHistory.jsp', 'FChartBidHistoryRetrieve','WIDTH=650','HEIGHT=500');
	}


	function openAttachment(row) {
		var filename = "";
		var totalRows = document.getElementById("attachments").rows.length;

		if (totalRows > 1) {
			filename = frm.docFilename[row].value;
		} else {
			filename = frm.docFilename.value;
		}

		popupUrl = "";
//		popupHandler = "StdDocumentDownloadFile";
		popupHandler = "DocAttachmentDownloadFile";
		popupUserId = frm.userId.value;
		popupOrganizationId = frm.organizationId.value;
		popupParameters = "filename=" + filename;

		if (theFocus == null) { theFocus = 'document_window'; }

		var winspecs = "WIDTH=680,HEIGHT=680,resizable=1,scrollbars=1,menubar=0,location=0,top=0,left=0";
		document_window = window.open("<%=contextPath%>/supplierportal/system/popup_html.jsp", "document_window", winspecs);

		if (theFocus == 'main') {
			self.focus();
		}
		else {
			document_window.focus();
		}

		if (document_window.opener == null) document_window.opener = window;
		self.name = "main";
	}

	function returnAbort() {
		if ( (frm.as_action.value == "UPDATE") || (frm.as_rfb_action.value == "UPDATE") || (frm.as_info_changed.value == "Y") ) {
			if (!confirm("Disgard changes?")) {
				return false;
			}
		}
		doSubmit('/menu/main_menu.jsp','DoNothing');
	}

	function saveBidHistory() {
		setBidHistoryData();
		doSubmit("/rfq/rfq_bid_event.jsp","RfqBidHistorySave;RfqRetrieve");
	}
	
	function setBidHistoryData() {
		var newInputField = "";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_icRfqHeader' value='<%=rfqHeader.getIcRfqHeader()%>'>";
		<% for (int ilr = 0; ilr < i_lineRows; ilr++) { %>
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_icRfqLine' value='"+frm.RfqBid_icRfqLine[<%=ilr%>].value+"'>";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_unitPrice' value='"+frm.unit_price[<%=ilr%>].value+"'>";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_quantity' value='"+frm.qty[<%=ilr%>].value+"'>";
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_vendorId' value='"+frm.RfqBid_vendorId[<%=ilr%>].value+"'>";
		<% } %>
		newInputField = newInputField + "<input type='hidden' name='RfqBidHistory_processName' value='<% if (action.equalsIgnoreCase("new")) { %>rfqbidhistory-add.xml<% } else { %>rfqbidhistory-update.xml<% } %>'>";
		setHiddenFields(newInputField);
	}

<% if (action.equalsIgnoreCase("old")) {
	 for (int y=0; y<rfqBidHistoryList.size();y++) {
	 	RfqBidHistory rfqBidHistory = (RfqBidHistory) rfqBidHistoryList.get(y);
%>
	frm.unit_price[<%=y%>].value = '<%=HiltonUtility.getFormattedDollar(rfqBidHistory.getUnitPrice(), oid)%>';
	updateBids(<%=y%>);
<%   }
   } %>

// end hiding contents -->
</SCRIPT>

<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
