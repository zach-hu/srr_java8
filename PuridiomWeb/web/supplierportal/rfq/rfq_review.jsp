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

	RfqHeader	rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	RfqVendor	rfqVendor = new RfqVendor();
	List	rfqQuestionList = rfqHeader.getRfqQuestionList();
	List	rfqAttachmentList = rfqHeader.getDocAttachmentList();
	List	rfqCommentList = rfqHeader.getDocCommentList();
	List	rfqSupplierList = (List) request.getAttribute("rfqVendorList");

	String	s_webpost	= rfqHeader.getWebpost();
	String	s_bid_access	= rfqHeader.getBidAccess();
	String	s_bid_item_options = rfqHeader.getBidItemOptions();
	String	s_rfq_type = rfqHeader.getRfqType();
	boolean	b_submit_access = false;
	boolean	b_graph_access	= false;
	boolean	b_download_access = false;
	boolean	b_require_all	= false;
	boolean	b_openauction	= false;
	boolean	b_require_entry	= false;
	boolean	b_show_low_amt	= false;
	boolean	b_low_subtotal	= false;
	boolean	b_bids_entered = false;
	boolean	b_show_submit_bid = false;
	boolean	b_allow_monitor = false;
	boolean	b_allow_email = false;
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
	
	b_require_all = s_bid_item_options.equals("A");
	b_openauction = rfqHeader.getAuctionType().trim().equals("O");

	if (b_openauction) {
		b_require_entry = rfqHeader.getLowestBidReq().trim().equals("Y");
		b_low_subtotal = rfqHeader.getLowestBidSource().trim().equals("S");
		b_show_low_amt = rfqHeader.getLowestDisplay().trim().equals("A");
	}
	
	String	s_due = HiltonUtility.getFullDateTimeString(rfqHeader.getDueDate(), rfqHeader.getBidDueTime(), rfqHeader.getTimeZone(), user.getOrganizationId());
%>
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

<tsa:hidden name="RfqHeader_status" value="<%=rfqHeader.getStatus()%>"/>
<tsa:hidden name="RfqHeader_auctionType" value="<%=rfqHeader.getAuctionType()%>"/>

<table border=0 width=680px cellpadding=0 cellspacing=0 valign=top>
<tr>
	<td align=center>
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
				</table>
			</td>
		</tr>
		<tr><td><br></td></tr>
		<tr><td align=center><a href="javascript: returnMe(); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_return.gif" class=button border=0 tabIndex=999></a></td></tr>
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
	var lookup_window = null;

	function questions() {
		popupParameters = "RfqQuestion_icRfqHeader=<%=rfqHeader.getIcRfqHeader()%>;VendorResponse_icRfqHeader=<%=rfqHeader.getIcRfqHeader()%>;VendorResponse_vendorId=<%=user.getVendorId()%>";
		doSubmitToLookup('/rfq/questions.jsp', 'RfqQuestionRetrieveByHeader;VendorResponseRetrieveByVendor', 'width=700', 'height=500');
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

	function returnMe() {
		doSubmit('','RfqRetrieve;SetEventPage');
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/supplierportal/system/prevent_caching.jsp" %>