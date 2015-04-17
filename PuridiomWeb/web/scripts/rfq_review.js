
	function viewItem(row) {
		var num = document.getElementById("icRfqLine_" + row);
		frm.RfqLine_icRfqLine.value = num.value;
		doSubmit('/rfq/rfq_item.jsp', 'RfqLineRetrieve');
	}

	function viewSupplier(vendorId){
		frm.currentVendorId.value = vendorId;
		doSubmit('rfq/rfq_supplier_summary.jsp', 'RfqRetrieve');
	}

	function addItem() {
		var line_count = frm.lineCount.value;
		line_count++;
		frm.lineCount.value = line_count;
		if (frm.RfqLine_itemNumber.value.length > 0) {
			doSubmit('/rfq/rfq_item.jsp','RfqLineItemLookup');
		}
		else {
			doSubmit('/rfq/rfq_item.jsp','RfqLineCreate');
		}
	}

	function rfqSaveAs() {
		popupParameters = "formtype=RFQ;formnumber=" + rfqnumber + ";fiscalyear=" + fiscalyear + ";action=saveas";
		doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
	}

	function highlightItem(row) {
/*		var myRow;

		if (row == 0) {
			myRow = document.all.itemRow.rows(row);
		}
		else {
			myRow = document.all.itemRows.rows(row - 1);
		}

		setRowClassName(myRow, "selectedRow");
*/	}

	function removeItemHighlight(row) {
/*		var myRow;

		if (row == 0) {
			myRow = document.all.itemRow.rows(row);
		}
		else {
			myRow = document.all.itemRows.rows(row - 1);
		}

		setRowClassName(myRow, "browseRow");
*/	}

	function rfqForward() {
		var line_count = frm.lineCount.value;

		if (line_count < 0) {
			alert("You must add items before forwarding a Solicitation!");
		}
		else if(vendors <= 0) {
			alert("You must have suppliers before forwarding to Bids Pending!");
		}
		else if (synopsisEmpty && solicitationActive == "Y" && webpost.indexOf("D") >= 0) {
				if (confirm("A " + synopsisLabel + " is required for a posted solicitation.  This text is used by suppliers searching through solicitations to bid on, therefore be as descriptive as possible.  Would you like to enter a synopsis now?")) {
					doSubmit('/rfq/rfq_general_info.jsp', 'RfqHeaderRetrieveById');
				}
		}
		else if ( (bidAccess == 'I' || bidAccess == 'R' ) && vendors <= 0) {
			alert("You must select at least one supplier when posting solicitations that are Restricted or By Invitation Only.");
		}
		else if (rfqStatus == rfqOpenSolicitation && vendors <= 0) {
			alert("You must select at least one supplier before forwarding the solicitation to Purchasing.");
		}
		else {
			validateRfq("FORWARD");
		}
	}

	function rfqReturn() {
		doSubmit('/rfq/rfq_return.jsp', 'RfqBidRetrieveByHeader;RfqVendorRetrieveByHeader');
	}

	function rfqPreview() {
		popupParameters = "RfqHeader_icRfqHeader=" + frm.RfqHeader_icRfqHeader.value;
		doSubmitToPopup('/rfq/rfq_preview.jsp', 'RfqRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
	}

	function viewHistory() {
		popupParameters = "HistoryLog_icHeaderHistory=" + frm.RfqHeader_icHeaderHistory.value + ";formtype=RFQ;RfqLine_icRfqHeader=" + frm.RfqHeader_icRfqHeader.value + ";rfqNumber=" + rfqNumber;
		doSubmitToPopup('/rfq/rfq_history.jsp', 'HistoryLogRetrieveByHistoryHeader', 'width=675px', 'height=450px');
	}

	function viewAttachments() {
		doSubmit('/rfq/rfq_attachments.jsp', 'DocAttachmentRetrieveByHeader');
	}

	function openDocument(row) {
		var filename = "";

		if (document.all("docFilename").length > 1) {
			filename = frm.docFilename[row].value;
		}
		else {
			filename = frm.docFilename.value;
		}
		openAttachment(filename);
	}

	function viewNotes(lineIc, vendorId) {
		popupParameters = "RfqNote_icHeader=" + frm.RfqHeader_icRfqHeader.value +  ";RfqNote_icLine=" + lineIc + ";RfqNote_vendorId=" + vendorId;
		doSubmitToPopup('/rfq/rfq_supplier_notes.jsp', 'RfqNoteRetrieveById', 'height=200px', 'width=350px');
	}

	function validateRfq(action) {
		if (action == 'FORWARD') {
			hideAreaWithBlock('forward_link');
		}
		frm.rfqaction.value = action;
		popupParameters = "RfqHeader_icRfqHeader=" + frm.RfqHeader_icRfqHeader.value;

		doSubmit('/rfq/rfq_validate.jsp', 'RfqValidate');
	}

	function pauseEvent() {
		doSubmit(findToPage(), 'RfqPauseEvent;RfqRetrieve');
	}

	function restartEvent() {
		doSubmit(findToPage(), 'RfqRestartEvent;RfqRetrieve');
	}

	function printPdf() {
    	doSubmit('/rfq/rfq_print_options.jsp', 'RfqVendorRetrieveByHeader');
  	}

	function printWorkSheetPdf() {
		frm.printtype.value="Wrk";
    	doSubmit('/rfq/rfq_print_pdf.jsp', 'DoNothing');
	}

	function printIntentAwardPdf() {
		frm.printtype.value="Ita";
    	doSubmit('/rfq/print_invitationbid.jsp', 'RfqVendorRetrieveByHeader');
	}

	function createAmendment() {
		var currDate;
		var currTime;
		var date=new Date;
		var day	    = date.getDate();
    	var month	= date.getMonth()+1;
    	if (month<10)
    	{
    		month= "0"+month;
    	}
	    var year	= date.getYear();
	    var hour	= date.getHours();
	    var min  	= date.getMinutes();
		currTime    = hour+":"+min;
		currDate    = year+"-"+month+"-"+day;
    	var Temp;
    	if (auctionEvent == "Y" && currDate>=auctionStartDate && currDate<=auctionEndDate)
    	{
	       alert("You can't make an amendment because there is an open auction");
    	}
    	else if (verifyAction('Create an Amendment to Solicitation # ' + rfqnumber + ' ?'))
    	{
   			doSubmit(currentpage, 'RfqAmendment;RfqRetrieve');
   			return true;
    	}
		return false;
	}
	function bidsReceived() {
		if (verifyAction('Mark Solicitation # ' + rfqnumber + ' as Bids Received?')) {
   			doSubmit(currentpage, 'RfqUpdateToBidsRecieved;RfqRetrieve');
   			return true;
    	}
		return false;
	}