	var bidVariance = 25;
	var requireAllBids = false;

	function setBidFromBidCode() {
		for (var ir = 0; ir < lineCount; ir++) {
			if (frm.RfqBid_bidCode[ir].value == "NC") {
				frm.unit_price[ir].value = "No Charge";
			}
			else if (frm.RfqBid_bidCode[ir].value == "SE") {
				frm.unit_price[ir].value = "Sealed";
			}
			else if (frm.RfqBid_bidCode[ir].value == "NSP") {
				frm.unit_price[ir].value = "Not Separately Priced";
			}
			else if (frm.RfqBid_bidCode[ir].value == "SN") {
				frm.unit_price[ir].value = "See Notes";
			}
			else if (frm.RfqBid_bidCode[ir].value == "NB") {
				frm.unit_price[ir].value = "No Bid";
			}
			else if (frm.RfqBid_bidCode[ir].value == "00") {
				frm.unit_price[ir].value = frm.RfqBid_unitPrice[ir].value;
			}
			else {
				frm.RfqBid_bidCode[ir].value = "NE";
				frm.unit_price[ir].value = "";
				frm.RfqBid_unitPrice[ir].value = "0.00";
			}
			total(ir);
		}
	}

	function total(row) {
		var bid_code = frm.RfqBid_bidCode[row].value;
		var p = 0.00;
		var q = eval(nfilter(frm.qty[row]));
		var f = eval(nfilter(frm.um_factor[row]));
		var tot = 0.00;

		if (f == 0) { f = 1; }

		if ( (bid_code != "NB") && (bid_code != "NC") && (bid_code != "NSP") && (bid_code != "SN") && (bid_code != "SE")) {
			p = nformat(eval(nfilter(frm.unit_price[row])),price_dec);
		}

		tot = eval(p * q * f);
		frm.ext_price[row].value = tot;
		formatPrice(frm.ext_price[row]);
	}

	function setBidCode(x) {
		if (x == "NB") {
			frm.unit_price[currentRow].value = "No Bid";
			frm.ext_price[currentRow].value = "0.00";
		}
		else if (x == "NE") {
			frm.unit_price[currentRow].value = " - ";
			frm.ext_price[currentRow].value = "0.00";
		}
		else if (x == "NC") {
			frm.unit_price[currentRow].value = "No Charge";
			frm.ext_price[currentRow].value = "0.00";
		}
		else if (x == "SE") {
			frm.unit_price[currentRow].value = "Sealed";
			frm.ext_price[currentRow].value = "0.00";
		}
		else if (x == "SN") {
			frm.unit_price[currentRow].value = "See Notes";
			frm.ext_price[currentRow].value = "0.00";
		}
		else if (x == "NSP") {
			frm.unit_price[currentRow].value = "Not Separately Priced";
			frm.ext_price[currentRow].value = "0.00";
		}
		else {
			frm.RfqBid_unitPrice[currentRow].value = frm.unit_price.value;
		}
		setAction('bid', 'UPDATE');
		setItemSelected(currentRow);
	}


	function defaultDates() {
		if ( isEmpty(frm.RfqVendor_datePromised.value) ) {
			if ( compareDate(frm.required_date,frm.due_date) ) {
				frm.RfqVendor_datePromised.value = frm.required_date.value;
			}
			else {
				frm.RfqVendor_datePromised.value = frm.due_date.value;
			}
		}
		if ( isEmpty(frm.RfqVendor_bidValidTo.value) ) {
			if ( compareDate(frm.default_validto,frm.due_date) ) {
				frm.RfqVendor_bidValidTo.value = frm.default_validto.value;
			}
			else {
				frm.RfqVendor_bidValidTo.value = frm.due_date.value;
			}
		}
	}


	function formatPrice(x) {
		var p = nformat(eval(nfilter(x)),dollar_dec);

		x.value = p;
	}

	function formatBidPrice(x, ir) {
		var p = nformat(eval(nfilter(x)),price_dec);

		x.value = p;

		frm.RfqBid_unitPrice[ir].value = p;
		resetBidCode(ir);
	}

	function setBidsForUpdate() {
		var b_entered = false;

		for (var ir = 0; ir < lineCount; ir++) {
			var unit_price = frm.unit_price[ir].value;

			if (unit_price == "No Bid") {
				frm.RfqBid_bidCode[ir].value = "NB";
				frm.RfqBid_unitPrice[ir].value = "0.00";
			} else if (unit_price == "No Charge") {
				frm.RfqBid_bidCode[ir].value = "NC";
				frm.RfqBid_unitPrice[ir].value = "0.00";
			} else if (unit_price == "Sealed") {
				frm.RfqBid_bidCode[ir].value = "SE";
				frm.RfqBid_unitPrice[ir].value = "0.00";
			} else if (unit_price == "Not Separately Priced") {
				frm.RfqBid_bidCode[ir].value = "NSP";
				frm.RfqBid_unitPrice[ir].value = "0.00";
			} else if (unit_price == "See Notes") {
				frm.RfqBid_bidCode[ir].value = "SN";
				frm.RfqBid_unitPrice[ir].value = "0.00";
			} else if (eval(unit_price) > 0) {
				frm.RfqBid_bidCode[ir].value = "00";
				frm.RfqBid_unitPrice[ir].value = frm.unit_price[ir].value;
				b_entered = true;
			} else {
				frm.RfqBid_bidCode[ir].value = "NE";
				frm.RfqBid_unitPrice[ir].value = "";
			}
		}

		return b_entered;
	}

	function checkRequiredBids() {
		if (requireAllBids) {
			for (var ir = 0; ir < lineCount; ir++) {
				if (frm.RfqBid_bidCode[ir].value == "NE") {
					alert("You must enter bids for all items!");
					return false;
				}
			}
		}
		return true;
	}


	function setItemSelected(row) {
		frm.rfb_item_selected[row].value = "Y";
	}

	function selectBidCodes(row, event) {
		currentRow = row;
		if(reqType == "IB")
		{
			popUp('Menu3',event);
		}
		else
		{
			popUp('Menu2',event);
		}
	}

	function resetBidCode(ir) {
		frm.RfqBid_bidCode[ir].value = "00";
	}

	function ckLowestBid(row) {
		return;
	}

	function updateBids(row) {
		var bid_code = frm.RfqBid_bidCode[row].value;
		var p = 0.00;
		var q = eval(nfilter(frm.qty[row]));
		var f = eval(nfilter(frm.um_factor[row]));
		var tot = 0.00;

		if (f == 0) { f = 1; }

		if ((bid_code == "NB") || (bid_code == "NC") || (bid_code == "NSP") || (bid_code == "NE") || (bid_code == "SN") || (bid_code == "SE")) {
			checkBidVariance(row);
			formatBidPrice(frm.unit_price[row], row);
			ckLowestBid(row);
			setItemSelected(row);
			setAction('bid', 'UPDATE');
		}
		else if ( false && bidsEntered && eval(nfiltervalue(frm.unit_price[row].value)) > eval(nfiltervalue(frm.db_unit_price[row].value)) && (nformat(eval(frm.db_unit_price[row].value), price_dec) != nformat(eval(0), price_dec)) ) {
			alert("Please enter an amount lower than your original bid.");
			frm.unit_price[row].value = frm.db_unit_price[row].value;
		}
		else {
			checkBidVariance(row);
			formatBidPrice(frm.unit_price[row], row);
			ckLowestBid(row);
			setItemSelected(row);
			setAction('bid', 'UPDATE');
		}
		total(row);
	}

	function setAction(x, val) {
		if (x == "bid") {
			frm.as_rfb_action.value = val;
		}
		else {
			frm.as_action.value = val;
		}
	}

	function checkBidVariance(row) {
		var auctionType = frm.RfqHeader_auctionType.value;
		if (auctionType != "S") {
			var avgBid = eval(nfiltervalue(frm.average_bid[row].value));
			var thisBid = eval(nfiltervalue(frm.unit_price[row].value));
			var thisVariance =  ((avgBid - thisBid) / avgBid) * 100;
			if (thisVariance < 0) {
				thisVariance = thisVariance * -1;
			}
			if (avgBid > 0 && bidVariance > 0 && thisVariance > bidVariance) {
				if (!confirm("Warning:  This bid is more than " + bidVariance + "% higher or lower than the average bid.  Would you like to continue?")) {
					frm.unit_price[row].value = frm.db_unit_price[row].value;
				}
			}
		}
	}

	function checkDates() {
		var alertMessage = "";
		if (chkdate(frm.RfqVendor_dateResponseRecv) == false) {
			alertMessage += "Please enter a valid Response Date.\n";
		}
		if (chkdate(frm.RfqVendor_datePromised) == false) {
			alertMessage += "Please enter a valid Promise Date.\n";
		}
		if (chkdate(frm.RfqVendor_bidValidTo) == false) {
			alertMessage += "Please enter a valid Bid Valid To Date.\n";
		}
		if (alertMessage.length > 0) {
			alert("Please fix the following problems:\n\n" + alertMessage);
			return false;
		}

		/*
		if (compareDateGreaterThan(frm.RfqVendor_dateResponseRecv,frm.s_current_date) == false) {
			alertMessage += "Response Date must be greater than Current Date [" + frm.s_current_date.value + "].\n";
		}
		*/
		if ((frm.organizationId.value != 'MSG07P') && (compareDate(frm.RfqVendor_datePromised, frm.RfqVendor_dateResponseRecv) == false)) {
			alertMessage += "Promise Date must be greater than or equal to Response Date [" + frm.RfqVendor_dateResponseRecv.value + "].\n";
		}
		if (compareDate(frm.RfqVendor_bidValidTo, frm.RfqVendor_dateResponseRecv) == false) {
			alertMessage += "Bid Valid To Date must be greater or equal to Response Date [" + frm.RfqVendor_dateResponseRecv.value + "].\n";
		}
		
		if (alertMessage.length > 0) {
			alert(alertMessage);
			return false;
		}

		return true;
	}

	function checkRequired() {
		var alertMessage = "";

		if (isEmpty(frm.RfqVendor_dateResponseRecv.value)) {
			alertMessage += "Response Date is required.\n";
		}
		if (isEmpty(frm.RfqVendor_datePromised.value)) {
			alertMessage += "Promise Date is required.\n";
		}
		if (isEmpty(frm.RfqVendor_bidValidTo.value)) {
			alertMessage += "Bid Valid To Date is required.\n";
		}
		if (alertMessage.length > 0) {
			alert("Please fix the following problems:\n\n" + alertMessage);
			return false;
		}
		if (!checkRequiredBids()) {
			return false;
		}
		return true;
	}

	function questions() {
		var icHeader = frm.RfqHeader_icRfqHeader.value;
		var vendorId = frm.RfqVendor_vendorId.value;

		popupParameters = "RfqQuestion_icRfqHeader=" + icHeader + ";VendorResponse_icRfqHeader=" + icHeader + ";VendorResponse_vendorId=" + vendorId;
		doSubmitToPopup('/rfq/questions.jsp', 'RfqQuestionRetrieveByHeader;VendorResponseRetrieveByVendor', 'width=700', 'height=500');
	}