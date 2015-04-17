<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>
<%@ page import="com.tsa.puridiom.contact.ContactManager"%>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/menu/rfq_bidArrays.js"></SCRIPT>
<%@ include file="/system/header.jsp" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_lic = "";
	String	currentVendorId = (String) request.getAttribute("currentVendorId");
	String	s_monitor = (String) request.getAttribute("monitor");
	boolean b_exists = false;

	if (HiltonUtility.isEmpty(s_monitor)) {s_monitor = "N";}

	RfqHeader	rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	RfqVendor	rfqVendor = new RfqVendor();
	List	rfqQuestionList = rfqHeader.getRfqQuestionList();
	List	rfqSupplierList = (List) request.getAttribute("rfqVendorList");

	String s_icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String s_rfqNumber = (String) request.getAttribute("RfqHeader_rfqNumber");
	String s_rfqAmendment = (String) request.getAttribute("RfqHeader_rfqAmendment");
	String s_rfqStatus = (String) request.getAttribute("RfqHeader_status");
	String s_fiscalYear = (String) request.getAttribute("RfqHeader_fiscalYear");

	if (HiltonUtility.isEmpty(s_rfqNumber))			{ s_rfqNumber = "N/A";			}

	String	s_webpost	= rfqHeader.getWebpost();
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
	boolean	b_allow_email = true;
	boolean	b_allow_monitor = true;
	boolean	locked = false;
	boolean	lockedDate = false;
	int	i_questions = 0;
	int	i_attachments = 0;
	int	i_supplierRows = 0;

	if (rfqQuestionList != null) {
		i_questions = rfqQuestionList.size();
	}
	if (rfqSupplierList != null) {
		i_supplierRows = rfqSupplierList.size();
	}

	for (int isr=0; isr < i_supplierRows; isr++) {
		RfqVendor vendor = (RfqVendor) rfqSupplierList.get(isr);
		if (vendor.getComp_id().getVendorId().equalsIgnoreCase(currentVendorId)) {
			rfqVendor = vendor;
			b_exists = true;
			break;
		}
	}
	for (int isr=0; isr < i_supplierRows; isr++) {
		RfqVendor vendor = (RfqVendor) rfqSupplierList.get(isr);
		BigDecimal differenceDates = HiltonUtility.getDateDifference(vendor.getBidValidTo(), new Date());
		if (differenceDates.compareTo(new BigDecimal(0)) < 0) {
			lockedDate = true;
			break;
		}
	}

	b_require_all = s_bid_item_options.equals("A");
	b_openauction = rfqHeader.getAuctionType().trim().equals("O");

	if (b_openauction) {
		b_require_entry = rfqHeader.getLowestBidReq().trim().equals("Y");
		b_low_subtotal = rfqHeader.getLowestBidSource().trim().equals("S");
		b_show_low_amt = rfqHeader.getLowestDisplay().trim().equals("A");
	}
	boolean editMode = false;

	if ( oid.equalsIgnoreCase("qri06p") )
	{
		if( role.getAccessRights("RFQ") > 1 && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) <= 0)){
			editMode = true;
		}
	}
	else
	{
		if (role.getAccessRights("RFQ") > 1 && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) <= 0 || s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENAMENDMENT) == 0)) {
			editMode = true;
		}
	}

	String	returnPage = (String) request.getAttribute("returnPage");
	String	returnMethod = (String) request.getAttribute("returnMethod");
	if (HiltonUtility.isEmpty(returnPage) || HiltonUtility.isEmpty(returnMethod)) {
		returnPage = "/rfq/rfq_review.jsp";
		returnMethod = "RfqRetrieve";
	}
	String mobileNumber = "";
	String emailAddr = "";
	Object contact = ContactManager.getInstance().getContact(oid, rfqVendor.getContactId(), rfqVendor.getAddressCode(), currentVendorId);
	if (contact != null)
	{
		mobileNumber = ((Contact)contact).getMobileNumber();
		emailAddr =	((Contact)contact).getEmailAddr();
	}
%>
<SCRIPT language='Javascript1.2' type="text/javascript">
<!--
	reqType = "<%=s_rfq_type%>";
//-->
</SCRIPT>
<SCRIPT LANGUAGE="Javascript1.2" SRC="<%=contextPath%>/scripts/dynamicTables.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/bids.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/timer.js"></SCRIPT>

<tsa:hidden name="formtype" value="RFQ"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=rfqHeader.getRfqNumber()%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_fiscalYear" value="<%=s_fiscalYear%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="${RfqHeader_rfqType}"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_icHeaderHistory" value="<%=rfqHeader.getIcHeaderHistory()%>"/>
<tsa:hidden name="RfqHeader_vendorAwarded" value="<%=rfqHeader.getVendorAwarded()%>"/>
<tsa:hidden name="RfqHeader_auctionType" value="<%=rfqHeader.getAuctionType()%>"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<!--input type="hidden" name="RfqBid_icRfqHeader" value="<%=s_icRfqHeader%>"-->
<tsa:hidden name="RfqQuestion_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqVendor_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqVendor_vendorId" value="<%=currentVendorId%>"/>
<tsa:hidden name="VendorDocument_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="VendorDocument_vendorId" value="currentVendorId"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="VendorQuestion_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="rfqaction" value=""/>
<tsa:hidden name="printtype" value="Rfq"/>
<tsa:hidden name="required_date" value="<%=HiltonUtility.getFormattedDate(rfqHeader.getRequiredDate(), oid)%>"/>
<tsa:hidden name="due_date" value="<%=HiltonUtility.getFormattedDate(rfqHeader.getDueDate(), oid)%>"/>
<tsa:hidden name="s_current_date" value="<%=HiltonUtility.getFormattedDate(d_today, oid)%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12 id="pageHeading"><%=rfqVendor.getComp_id().getVendorId()%> Bid</div>
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

<table border=0 width=680px cellpadding=0 cellspacing=0 valign=top>
<tr>
	<td align=center>
		<table border=0 cellspacing=0 cellpadding=0 height=100% align=center>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 height=100% align=center>
				<tr>
					<td class=summary>
						<table border=0 class=summary width="75%">
						<tr>
							<td>&nbsp;<b><%=VendorManager.getInstance().getVendorName(oid, rfqVendor.getComp_id().getVendorId()) %></b></td>
							<td  align=center valign=bottom class=summary>
		<%	if (b_submit_access && propertiesManager.getProperty("RFQ OPTIONS", "SupplierDocs", "N").equals("Y")) {%>
								<a href="javascript: uploadDocs(); void(0);"><img name="img_attach" src="<%=contextPath%>/images/clip.gif" border=0 valign=top alt="Upload Response Documents"><br><b>Upload</b></a>
		<%	}%>
							</td>
		<%	if (b_submit_access && i_questions > 0) {%>
							<td >&nbsp;</td>
							<td  align=center valign=bottom>
								<a href="javascript: questions(); void(0);"><img name="img_question" src="<%=contextPath%>/images/img_question.gif" border=0 valign=top><br><b>Questions</b></a>
							</td>
		<%	}
				if (b_submit_access) {%>
							<td >&nbsp;</td>
							<td  align=center valign=bottom>
								<a href="javascript: stopMonitor(); openNotes('0', '', 'Y'); void(0);"><img src="<%=contextPath%>/images/notes.gif" border=0 valign=top><br><b>Notes</b></a>
							</td>
		<%	}%>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=summary align=center>
						<%@ include file="/rfq/rfq_supplier_bids_edit.jsp" %>
					</td>
				</tr>
				<tr><td><br></td></tr>
				<tr>
					<td class=summary align=center>
						<br>
						<%@ include file="/rfq/rfq_supplier_info_edit.jsp" %>
						<br>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr><td><br></td></tr>
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=100%>
				<tr>
<%
		if ( editMode )

		{ %>
					<td width=50% align=center>
						<div id="pxbutton"><a href="javascript: submitBids(); void(0);"><tsa:label labelName="rfq-Submit" defaultString="Submit" /></a></div>
					</td>
<%		}%>
					<td width=50% align=center>
						<div id="pxbutton"><a href="javascript: returnAbort(); void(0);"><tsa:label labelName="rfq-Return" defaultString="Return" /></a></div>
					</td>
				</tr>
				</table>
			</td>
		</tr>
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

<%@ include file="/system/footer.jsp" %>

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
	var price_dec = <%=propertiesManager.getProperty("MISC", "PriceDecimals", "2")%>;
	var dollar_dec = <%=propertiesManager.getProperty("MISC", "DollarDecimals", "2")%>;
	var	lineCount = <%=i_lineRows%>;
	var currentpage = "/rfq/rfq_supplier_summary.jsp";

	bidsEntered = <%=b_bids_entered%>;
	requireAllBids = <%=b_require_all%>;
	bidVariance = <%=rfqHeader.getBidVariance()%>;

/*	function thisLoad() {
	alert("start");
		start();
		f_StartIt();
	}

	if (monitor == "Y") {
		startMonitor();
	}
*/

<%	if (b_submit_access) {%>
	setBidFromBidCode();
//	defaultDates();
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

	function submitBids() {
		var b_entered = setBidsForUpdate();

		//Temporarily set to true so question responses are not required for buyer to submit bids for vendors
		frm.as_survey_complete.value = "true";

		//if ( checkRequired() ) {
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
						doSubmit("/rfq/rfq_suppliers.jsp", "RfqVendorUpdateById;RfqBidUpdate;RfqVendorRetrieveByHeader");
					} else {
						doSubmit("/rfq/rfq_suppliers.jsp", "RfqVendorUpdateById;RfqVendorRetrieveByHeader");
					}
				}
			} else {
				stopMonitor();
			}
		/*} else {
			stopMonitor();
		}*/
	}

	function startMonitor() {  /* called when monitor button is clicked */
		if (b_timer) {
			stopMonitor();
		} else {
			frm.img_monitor.src = "<%=contextPath%>/images/stop_monitor.gif";

			if ( (frm.as_action.value == "UPDATE") || (frm.as_rfb_action.value == "UPDATE")) {
				if (confirm("Do you wish to submit changes made to your bid before monitoring?")) {
					submitBids();
					frm.img_monitor.src = "<%=contextPath%>/images/start_monitor.gif";
					return false;
				} else {
					b_timer = true;
					popupParameters = "RfqLine_icRfqHeader=<%=rfqHeader.getIcRfqHeader()%>";
					doSubmitToPopup("/rfq/rfq_bids_monitor.jsp", "RfqLineBidsRetrieveByHeader");
				}
			} else {
				b_timer = true;
				popupParameters = "RfqLine_icRfqHeader=<%=rfqHeader.getIcRfqHeader()%>";
				doSubmitToPopup("/rfq/rfq_bids_monitor.jsp", "RfqLineBidsRetrieveByHeader");
			}
		}
	}

	function stopMonitor() {
//		if (b_timer) {
//			clearTimeout(bid_timer);
//		}
//		b_timer = false;
<%	if (b_submit_access && b_openauction && (b_bids_entered || !b_require_entry) ) {%>
//		frm.img_monitor.src = "<%=contextPath%>/images/start_monitor.gif";
<%	}%>
		frm.monitor.value = "N";
		closeOpenWindows();
	}

	function uploadDocs()
	{
		popupParameters = "RfqHeader_status=" + frm.RfqHeader_status.value + ";VendorDocument_icRfqHeader=<%=rfqHeader.getIcRfqHeader()%>;VendorDocument_vendorId=<%=currentVendorId%>";
		doSubmitToPopup('/rfq/supplier_attachments.jsp', 'VendorDocumentRetrieveByVendor', 'width=700', 'height=350');
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
		popupParameters = "RfqNote_icHeader=<%=rfqHeader.getIcRfqHeader()%>;RfqNote_icLine=" + lic + ";RfqNote_vendorId=<%=currentVendorId%>;ln=" + line + ";edit=" + edit;
		doSubmitToPopup('/rfq/supplier_notes.jsp', 'RfqNoteRetrieveById', 'WIDTH=450','HEIGHT=300');
	}

	function viewBidHistory(lic, line, edit) {
		popupParameters = "RfqNote_icHeader=<%=rfqHeader.getIcRfqHeader()%>;RfqNote_icLine=" + lic + ";RfqNote_vendorId=<%=currentVendorId%>;ln=" + line + ";edit=" + edit;
		doSubmitToPopup('/rfq/supplier_bidHistory.jsp', 'RfqNoteRetrieveById', 'WIDTH=450','HEIGHT=300');
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
		document_window = window.open("<%=contextPath%>/system/popup_html.jsp", "document_window", winspecs);

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
		doSubmit('<%=returnPage%>','<%=returnMethod%>');
	}

	function setEdi()
	{
		if (frm.c_checkbox.checked==true)
		{
			frm.RfqVendor_ediRfq.value = "Y";
		}
		else
		{
			frm.RfqVendor_ediRfq.value = "N";
		}
	}

// end hiding contents -->
</SCRIPT>