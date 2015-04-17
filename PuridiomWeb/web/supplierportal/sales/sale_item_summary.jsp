<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ include file="/supplierportal/rfq/bid_instructions.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Date" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(user.getOrganizationId());
	String	s_lic = "";
	String	s_monitor = (String) request.getAttribute("monitor");
	boolean b_exists = false;

	if (HiltonUtility.isEmpty(s_monitor)) {s_monitor = "N";}

	SaleHeader	saleHeader = (SaleHeader) request.getAttribute("saleHeader");
	RfqVendor	rfqVendor = new RfqVendor();
	List questionList = new ArrayList();
	List	attachmentList = saleHeader.getDocAttachmentList();
	List	commentList = saleHeader.getDocCommentList();
	List	buyerList = (List) request.getAttribute("saleBuyerList");

	String	s_webpost	= saleHeader.getWebpost();
	String	s_bid_access	= saleHeader.getBidAccess();
	boolean	b_submit_access	= true;
	boolean	b_download_access	= true;
	boolean	b_require_all	= false;
	boolean	b_openauction	= false;
	boolean	b_require_entry	= false;
	boolean	b_show_low_amt	= false;
	boolean	b_low_subtotal	= false;
	boolean	b_bids_entered = false;
	boolean	b_show_submit_bid = false;
	int	i_questions = 0;
	int	i_attachments = 0;
	int	i_supplierRows = 0;
	
	if (questionList != null) {
		i_questions = questionList.size();
	}
	if (attachmentList != null) {
		i_attachments = attachmentList.size();
	}
	if (buyerList != null) {
		i_supplierRows = buyerList.size();
	}
	
	for (int isr=0; isr < i_supplierRows; isr++) {
		RfqVendor vendor = (RfqVendor) buyerList.get(isr);
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

	b_require_all = true;
	b_openauction = saleHeader.getAuctionType().trim().equals("O");

	if (b_openauction) {
		b_require_entry = saleHeader.getHighestBidReq().trim().equals("Y");
		b_low_subtotal = saleHeader.getHighestBidSource().trim().equals("S");
		b_show_low_amt = saleHeader.getHighestDisplay().trim().equals("A");
	}
	
	SaleLine saleLine = (SaleLine) request.getAttribute("saleLine");
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

<tsa:hidden name="SaleHeader_status" value="<%=saleHeader.getStatus()%>"/>

<table border=0 width=680px cellpadding=0 cellspacing=0 valign=top>
<tr>
	<td align=center>
		<!-- RFQ HEADER -->
		<table border=0 cellspacing=0 cellpadding=0 height=100%>
		<tr>
			<td valign=middle class=hdr12 height=20px align=center>
				Sale Transaction # <%=saleHeader.getSaleNumber()%>
<%	if (!HiltonUtility.isEmpty(saleHeader.getAmendment())) {%>
				<br>Amendment # <%=saleHeader.getAmendment()%>
<%	}%>
			</td>
		</tr>
		</table>
		<table border=0 cellspacing=0 cellpadding=2 height=100%>
		<tr>
			<td class=formType id="auctionCountdownText" align=right><%	if (b_openauction) {%>Open<% } else {%>Sealed<%}%> Auction Closes In</td>
			<td><%@ include file="/supplierportal/sales/sale_time_remaining.jsp"%></td>
		</tr>
		</table>
		<br>
		<table border=0 cellspacing=0 cellpadding=0 height=100% align=center width=90%>
		<tr>
			<td width=90% align=center>
<%	if ( user.isGuest() ) {%><!--[Guest Instructions Here]-->
				<%=s_guest_instr%>
<%	} else {%>
				<%=s_sale_instr%>
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
						<table border=0 cellpadding=2 cellspacing=0 class=summary width=650px>
						<%@ include file="/supplierportal/sales/sale_hdr_summary.jsp" %>
						<%@ include file="/supplierportal/sales/sale_comments.jsp" %>
						<%@ include file="/supplierportal/sales/sale_attachments.jsp" %>
						</table>

						<%@ include file="/supplierportal/sales/sale_item_bid.jsp" %>
<%	if (!HiltonUtility.isEmpty(s_after_comment)) { %>
						<table border=0 cellpadding=0 cellspacing=0 class=summary width=650px>
						<tr><td colspan=2><br></td></tr>
						<tr>
							<td width=75px valign=top align=right><b>Comments:</b></td>
							<td valign=top>&nbsp;<%=s_after_comment%></td>
						</tr>
						</table>
<%	} %>
						<br>
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
					<td width=50% align=center><a href="javascript: returnAbort(); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_cancel.gif" class=button border=0 tabIndex=999></a></td>
					<td width=50% align=center><a href="javascript: submitBids(); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_submit.gif" class=button border=0 tabIndex=110 alt="submit"></a></td>
				</tr>
				</table>
			</td>
		</tr>
<%	}%>
		<tr>
			<td>
				<tsa:hidden name="SaleHeader_icSaleHeader" value="<%=saleHeader.getIcSaleHeader()%>"/>
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
	bidVariance = <%=saleHeader.getBidVariance()%>;	

	function thisLoad() {
		start();
		f_StartIt();
	}

	if (monitor == "Y") {
		startMonitor();
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
		var buyer = "<%=saleHeader.getBuyer()%>";
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

	function checkRequired() {
		if (!checkRequiredBids()) {
			return false;
		}
		return true;
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
						//remove dummy fields before submitting
						document.getElementById("dummyFields").innerHTML = "";

						if (b_entered) {
							frm.RfqVendor_bidsEntered.value = "Y";
						} else {
							frm.RfqVendor_bidsEntered.value = "N";
						}

						if (frm.as_rfb_action.value == "UPDATE") {
							doSubmit("/rfq/bid_confirm.jsp", "RfqVendorUpdateById;RfqBidUpdate;RfqRetrieve");
						} else {
							doSubmit("/rfq/bid_confirm.jsp", "RfqVendorUpdateById;RfqRetrieve");
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

	function checkDates() {
		return true;
	}

	function startMonitor() {  /* called when monitor button is clicked */
		if (b_timer) {
			stopMonitor();
		} else {
			frm.img_monitor.src = "<%=contextPath%>/supplierportal/images/stop_monitor.gif";

			if ( (frm.as_action.value == "UPDATE") || (frm.as_rfb_action.value == "UPDATE")) {
				if (confirm("Do you wish to submit changes made to your bid before monitoring?")) {
					submitBids();
					frm.img_monitor.src = "<%=contextPath%>/supplierportal/images/start_monitor.gif";
					return false;
				} else {
					b_timer = true;
					popupParameters = "SaleLine_icSaleHeader=<%=saleHeader.getIcSaleHeader()%>";
					doSubmitToLookup("/rfq/rfq_bids_monitor.jsp", "SaleLineBidsRetrieveByHeader");
				}
			} else {
				b_timer = true;
				popupParameters = "SaleLine_icSaleHeader=<%=saleHeader.getIcSaleHeader()%>";
				doSubmitToLookup("/rfq/rfq_bids_monitor.jsp", "SaleLineBidsRetrieveByHeader");
			}
		}
	}

	function stopMonitor() {
		if (b_timer) {
			clearTimeout(bid_timer);
		}
		b_timer = false;
<%	if (b_submit_access && b_openauction && (b_bids_entered || !b_require_entry) ) {%>
		frm.img_monitor.src = "<%=contextPath%>/supplierportal/images/start_monitor.gif";
<%	}%>
		frm.monitor.value = "N";
		closeOpenWindows();
	}

	function questions()
	{
		popupParameters = "RfqQuestion_icSaleHeader=<%=saleHeader.getIcSaleHeader()%>;VendorResponse_icSaleHeader=<%=saleHeader.getIcSaleHeader()%>;VendorResponse_vendorId=<%=user.getVendorId()%>";
		doSubmitToLookup('/rfq/questions.jsp', 'RfqQuestionRetrieveByHeader;VendorResponseRetrieveByVendor', 'width=700', 'height=500');
	}

	function uploadDocs()
	{
		popupParameters = "SaleHeader_status=" + frm.SaleHeader_status.value + ";VendorDocument_icSaleHeader=<%=saleHeader.getIcSaleHeader()%>;VendorDocument_vendorId=<%=user.getVendorId()%>";
		doSubmitToLookup('/rfq/supplier_attachments.jsp', 'VendorDocumentRetrieveByVendor', 'width=700', 'height=350');
	}

	function canMonitor() {
		var bidsEntered = <%=b_bids_entered%>;
		var requireEntry = <%=b_require_entry%>;
		
		if (bidsEntered || !requireEntry) {
			return true;
		} else {
			alert("You must submit bid entries before monitoring bids.");
			return false;
		}
	}

	function openNotes(lic, line, edit) {
		popupParameters = "RfqNote_icHeader=<%=saleHeader.getIcSaleHeader()%>;RfqNote_icLine=" + lic + ";RfqNote_vendorId=<%=user.getVendorId()%>;ln=" + line + ";edit=" + edit;
		doSubmitToLookup('/rfq/supplier_notes.jsp', 'RfqNoteRetrieveById', 'WIDTH=450','HEIGHT=300');
	}

	function openAttachment(row) {
		var filename = "";
		var totalRows = document.getElementById("attachments").rows.length
	
		if (totalRows > 1) {
			filename = frm.docFilename[row].value;
		} else {
			filename = frm.docFilename.value;
		}

		popupUrl = "";
		popupHandler = "StdDocumentDownloadFile";
		popupUserId = frm.userId.value;
		popupOrganizationId = frm.organizationId.value;
		popupParameters = "filename=" + filename;
			
		if (theFocus == null) { theFocus = 'document_window'; }
		
		var winspecs = "WIDTH=680,HEIGHT=680,resizable=1,scrollbars=1,menubar=0,location=0,top=0,left=0";		
		document_window = window.open("<%=contextPath%>/supplierportal/system/popup.html", "document_window", winspecs);
	
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

	function defaultDates() {
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/supplierportal/system/prevent_caching.jsp" %>