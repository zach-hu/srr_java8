	var poFormMultiLanguajes;

	function viewItem(row) {
		var num = document.getElementById("icPoLine_" + row);
		frm.PoLine_icPoLine.value = num.value;
		doSubmit('/orders/po_item.jsp','PoLineRetrieve');
	}

	function addItem() {
		var line_count = frm.lineCount.value;
		line_count++;
		frm.lineCount.value = line_count;

		if (frm.PoLine_itemNumber.value.length > 0) {
			doSubmit('/orders/po_item.jsp','PoLineItemLookup');
		}
		else {
			doSubmit('/orders/po_item.jsp','PoLineCreate');
		}
	}

	function orderSave() {
		if (ponumber == "N/A") {
			popupParameters = "formtype=PO;formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=save";
			doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
		}
	}

	function orderSaveAs() {
		popupParameters = "formtype=PO;potype=" + frm.PoHeader_poType.value + ";formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=saveas";
		doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
	}

	function createContractedPo() {
		popupParameters = "formtype=PO;newType=" + frm.newType.value + ";formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=saveas";
		doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
	}

	function orderSaveAsCheckAccount() {
		popupParameters = "formtype=PO;potype=" + frm.PoHeader_poType.value + ";formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=saveas"+ ";Account_icHeader=" + frm.PoHeader_icPoHeader.value;
		doSubmitToPopup('/base/save_as_acc_check.jsp', 'AccountRetrieveByHeader', 'WIDTH=350', 'HEIGHT=165');
	}

	function orderPreview() {
		popupParameters = "PoHeader_icPoHeader=" + frm.PoHeader_icPoHeader.value;
		doSubmitToPopup('/orders/po_preview.jsp', 'PoRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
	}

	function emailApp() {
		doSubmit('/orders/po_email_approvals.jsp', 'DoNothing');
	}

	function printPdf() {
		doSubmit('/orders/po_print_pdf.jsp', 'DoNothing');
	}

	function poMXP()
	{
		if (verifyAction('Send this order to MXP?')) {
			hideMxpButton();
		}
		else
		{
			return;
		}
		frm.PoHeader_flagChange.value = 'N';
		doSubmit('/orders/po_mxp_options.jsp', 'PoSendMxp');
	}

	function poValidate(action) {
		var message;
		if (action == "FORWARD") {
			var po_type = frm.PoHeader_poType.value;
			var po_date = frm.PoHeader_poDate.value;
			var po_require = frm.PoHeader_requireDate.value;
			var po_date = po_date.replace(/-/g,"/");
			var po_require = po_require.replace(/-/g,"/");

			var fecha1 = Date.parse(po_date);
			var fecha2 = Date.parse(po_require);

			if( fecha1 > fecha2 ) {
				frm.Flag.value = "A";
			}

			var ftoday = frm.today_date.value;
			var podate = frm.PoHeader_poDate.value;
	  		var anyo = podate.substring(6,10);
			if(ftoday != anyo)
	  		{
	  			frm.Current_year.value = "A";
	  		}

			var line_count = frm.lineCount.value;

			if (line_count <= 0 && po_type != "BO" && po_type != "CT") {
				alert("You must add items before forwarding an Order!");
				return;
			}
			var vendorId = frm.PoHeader_vendorId.value;
			message = ((oid == 'BSC04P') && (po_type == 'CT')) ? 'Award' : 'Forward';
			if (isEmpty(vendorId)) {
				alert('Cannot ' + message + ' without a Vendor!');
				return;
			}
			if (verifyAction(message + ' this order?')) {
				hideForwardButton();
			} else {
				return;
			}
		}

		frm.poaction.value = action;

		if (orgId == "BLY07P")
		{
			doSubmit('/orders/po_validate_no_popup.jsp', 'AccountNoValidDelete;PoValidate');
		}
		else
		{
			doSubmit('/orders/po_validate_no_popup.jsp', 'AccountDeleteZeroDollars;PoValidate');
		}
	}

	function viewHistory() {
		popupParameters = "HistoryLog_icHeaderHistory=" + frm.PoHeader_icHeaderHistory.value + ";formtype=PO;PoLine_icPoHeader=" + frm.PoHeader_icPoHeader.value + ";poNumber=" + ponumber + ";poRelease=" + porelease + ";icHeaderKey=" + frm.PoHeader_icHeaderKey.value ;
		doSubmitToPopup('/orders/po_history.jsp', 'HistoryLogRetrieveByHistoryHeader', 'width=675px', 'height=450px');
	}

	function browseSchedules(type, xml) {
		popupParameters = "Schedule_documentType=POH;Schedule_scheduleType=" + type + ";Schedule_icHeader=" + frm.PoHeader_icPoHeader.value;
		doSubmitToPopup('base/schedules.jsp', 'ScheduleRetrieveAllLinesBy', 'WIDTH=700px', 'HEIGHT=300px');
	}

	function viewAttachments() {
		doSubmit('/orders/po_attachments.jsp', 'DocAttachmentRetrieveByHeader');
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

	function orderCancelClose(action) {
		if (verifyAction(action.charAt(0).toUpperCase() + action.substring(1, action.length) + ' this Order?')) {
			frm.poaction.value = action;
			doSubmit('/orders/po_review.jsp', 'PoCancel');
		}
	}

	function hideForwardButton() {
		hideAreaWithBlock('forward_link');
	}

	function hideMxpButton() {
		hideAreaWithBlock('mxp_link');
	}

	function viewPaymentHistory() {
		var releaseNumber = frm.PoHeader_releaseNumber.value;
		if (releaseNumber == "0") {
			releaseNumber = "";
		}
		popupParameters = popupParameters + "PaymentAccount_poNumber=" + frm.PoHeader_poNumber.value + ";";
		popupParameters = popupParameters + "PaymentAccount_releaseNumber=" + releaseNumber + ";";
		popupParameters = popupParameters + "poTotal=" + frm.poTotal.value + ";";
		popupParameters = popupParameters + "currencyCode=" + frm.currencyCode.value + ";";
		popupParameters = popupParameters + "originalRetrieve=Y;";
		browseLookup('', 'paymentaccount'); void(0);
	}

	function viewPayments() {
		var releaseNumber = frm.PoHeader_releaseNumber.value;

		popupParameters = popupParameters + "colname=PaymentAccount_poNumber;operator==;filter_txt=" + frm.PoHeader_poNumber.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
		popupParameters = popupParameters + "colname=PaymentAccount_releaseNumber;operator=;filter_txt=" + releaseNumber +  ";logicalOperator=AND;originalFilter=Y;sort=N;";
		popupParameters = popupParameters + "PaymentAccount_poNumber=" + frm.PoHeader_poNumber.value + ";";
		popupParameters = popupParameters + "PaymentAccount_releaseNumber=" + releaseNumber + ";";
		popupParameters = popupParameters + "poTotal=" + frm.poTotal.value + ";";
		popupParameters = popupParameters + "currencyCode=" + frm.currencyCode.value + ";";
		popupParameters = popupParameters + "originalRetrieve=Y;";
	    popupParameters =  popupParameters + "browseName=paymentaccount;allowBrowse=" + frm.allowBrowse.value;
		doSubmitToPopup('browse/browse_popup.jsp', 'PaymentAccountRetrieveSumByPoNumber;BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}

	function viewReleasePayments() {
		popupParameters = popupParameters + "colname=PaymentAccount_poNumber;operator==;filter_txt=" + frm.PoHeader_poNumber.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
		popupParameters = popupParameters + "PaymentAccount_poNumber=" + frm.PoHeader_poNumber.value + ";";
		popupParameters = popupParameters + "poTotal=" + frm.poTotal.value + ";";
		popupParameters = popupParameters + "currencyCode=" + frm.currencyCode.value + ";";
		popupParameters = popupParameters + "originalRetrieve=Y;";
	    popupParameters =  popupParameters + "browseName=paymentaccount;allowBrowse=" + frm.allowBrowse.value;
		doSubmitToPopup('browse/browse_popup.jsp', 'PaymentAccountRetrieveSumByPoNumber;BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}

	function viewSupplierEvaluation()
	  {
	  		var newInputField = "<input type='hidden' name='PerformanceDetail_icPoHeader' value='"+ frm.PoHeader_icPoHeader.value +"'>"+
	    	"<input type=hidden name='returnPage' value='/orders/po_review.jsp'>" +
	    	"<input type=hidden name='poNumber' value='" + frm.PoHeader_poNumber.value + "'>" +
	    	"<input type=hidden name='returnHandler' value='PoRetrieve'>";
	    	setHiddenFields(newInputField);

	  		doSubmit("/orders/po_supplier_eval.jsp", "VendorPerformanceRetrieveByOrder");
	  }

	function viewAppNotes()
	{
		var icHeader = frm.PoHeader_icPoHeader.value;
		popupParameters = "PoHeader_icPoHeader=" + icHeader;
		popupParameters = popupParameters + ";ApprovalLog_icHeader=" + icHeader;
		popupParameters = popupParameters + ";PoHeader_poNumber=" + frm.PoHeader_poNumber.value;
		popupParameters = popupParameters + ";formtype=PO";
		doSubmitToPopup('/orders/po_approval_notes.jsp', 'ApprovalLogRetrieveByHeader', 'width=675px', 'height=450px');
	}

	function viewContractOrders() {
		popupParameters = "PoHeader_contractNo=" + frm.PoHeader_poNumber.value + ";browseName=po-contract-orders;allowBrowse=true";

		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}

	function createRevision() {
		if (verifyAction("Create a revision to this order?")) {
			frm.checkRestoreRevision.value = 'N';
			if (viewType == "WIZARD") {
				if (poType == 'CT' && oid == 'BSC04P') {
				   	var newInputField = "<input type='hidden' name='fromRevision' value='Y'>" ;
					setHiddenFields(newInputField);
					doSubmit('/orders/ct_contract_naming.jsp', 'PoRevision;PoRetrieve');
				}
				else {
					doSubmit('/orders/po_review.jsp', 'PoRevision;PoRetrieve');
				}
			} else {
				doSubmit('/orders/po_summary.jsp', 'PoRevision;PoRetrieve');
			}
		} else {
			return;
		}
	}
	function createChangeRequest() {
		frm.viewType.value = "WIZARD";
 		var newInputField = "<input type='hidden' name='RequisitionHeader_requisitionType' value='C'>";
		setHiddenFields(newInputField);

		doSubmit('/requests/req_review.jsp', 'RequisitionCreateFromPo;RequisitionRetrieve');
	}
	
	function receivePackagesForOrder() {
		var newInputField = "<input type='hidden' name='ReceiptHeader_icPoHeader' value=" + frm.PoHeader_icPoHeader.value + ">";
	    newInputField = newInputField + "<input type='hidden' name='receiptMethod' value='ReceiveByOrder'>";
	    setHiddenFields(newInputField);

		doSubmit('/receipts/rec_package_entry.jsp','ReceiptCreateRetrieve');
	}

	function receiveItemsForOrder() {
		var newInputField = "<input type='hidden' name='ReceiptHeader_icPoHeader' value=" + frm.PoHeader_icPoHeader.value + ">";
	    newInputField = newInputField + "<input type='hidden' name='receiptMethod' value='ReceiveByOrder'>";
	    setHiddenFields(newInputField);
 
		doSubmit('/receipts/rec_item_entry.jsp','ReceiptCreateRetrieve');
	}
	function deleteOrder() {
		if (verifyAction('Delete this Order?')) {
			doSubmit('/menu/main_menu.jsp', 'PoDelete');
		} else {
			return;
		}
	}
	
