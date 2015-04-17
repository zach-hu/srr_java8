/***************************************************************************
							Version 4 Menu ARRAYS
***************************************************************************/

var	font_color = getStyleSheetAttribute("popupMenu", "color");
var	mouseover_font_color = getStyleSheetAttribute("popupMenuOver", "color");
var	background_color = getStyleSheetAttribute("popupMenu", "backgroundColor");
var	mouseover_background_color = getStyleSheetAttribute("popupMenuOver", "backgroundColor");
var	border_color = getStyleSheetAttribute("popupMenu", "borderColor");
var	separator_color = getStyleSheetAttribute("popupMenu", "borderColor");
var	top_is_permanent = 0;
var	top_is_horizontal = 0;
var	tree_is_horizontal = 0;
var	position_under = 1;
var	top_more_images_visible = 1;
var	tree_more_images_visible = 1;
var	evaluate_upon_tree_show = "null";
var	evaluate_upon_tree_hide = "null";
var	right_to_left;

var currentMenuWidth = .1;

//RfqBid Arrays
Array1 = [
[145,		// menu width
300,		// left_position
,		// top_position
font_color,
mouseover_font_color,
background_color,
mouseover_background_color,
border_color,
separator_color,
top_is_permanent,
top_is_horizontal,
tree_is_horizontal,
position_under,
top_more_images_visible,
tree_more_images_visible,
evaluate_upon_tree_show,
evaluate_upon_tree_hide,
right_to_left],
["No Bid","javascript: setBidCode('NB');",1,0,0],
["No Charge","javascript: setBidCode('NC');",1,0,0],
["Sealed","javascript: setBidCode('SE');",1,0,0],
["See Notes","javascript: setBidCode('SN');",1,0,0],
["Not Separately Priced","javascript: setBidCode('NSP');",1,0,0]
]

//Menu options will be created onload

//Requisition Module
Array50 = [[],[]]

//Solicitation Module
Array51 = [[],[]]

//Purchase Order Module
Array52 = [[],[]]

//Receipts Module
Array60 = [[],[]]

//Invoice Voucher Module
Array61 = [[],[]]

//Inventory Module
Array55 = [[],[]]

//Disbursements
Array56 = [[],[]]

//Reports
Array57 = [[],[]]

//Sales Module
Array58 = [[],[]]

//Assets Module
Array62 = [[],[]]

function createDefaultDisplayArray(menuWidth) {
	var defaultDisplay =
		[menuWidth,		// menu width
		.1,		// left_position
		menuLocation,		// top_position
		font_color,
		mouseover_font_color,
		background_color,
		mouseover_background_color,
		border_color,
		separator_color,
		top_is_permanent,
		top_is_horizontal,
		tree_is_horizontal,
		position_under,
		top_more_images_visible,
		tree_more_images_visible,
		evaluate_upon_tree_show,
		evaluate_upon_tree_hide,
		right_to_left]

		defaultDisplay[1] = currentMenuWidth;

		currentMenuWidth = currentMenuWidth + 85;

		return defaultDisplay;
}

function createRequisitionOptions() {
	var options = new Array();
	var i = 1;

	if (reqAccess >= 2) {
		if (isXpress) {
			options[i] = new Array(newTxt, "javascript: createRequisition('P'); void(0);",1,0,0);
		} else {
			options[i] = new Array(newTxt,"javascript: doSubmit('/requests/req_select_type.jsp', 'DoNothing'); void(0);",1,0,0);
		}
		i++;
	}
	if (reqAccess > 0) {
		options[i] = new Array(browseTxt, "javascript:browseFilter('requisitionheader');void(0);",1,0,0);
		i++;

		options[i] = new Array(browseItem,"javascript:browseFilter('requisitionline');void(0);",1,0,0);
		i++;

		options[i] = new Array(browseAccount,"javascript:browseFilter('requisitionline-account');void(0);",1,0,0);
		i++;
	}
	if (reqAccess >= 2) {
		if (! isXpress) {
			options[i] = new Array(newMsrTxt, "javascript: createRequisition('M'); void(0);",1,0,0);
			i++;
		}
	}
	if (reqAccess > 0) {
		options[i] = new Array(browseMsrTxt, "javascript:browseFilter('requisitionheader-msr');void(0);",1,0,0);
		i++;
	}

	if (i > 1) {
		options[0] = createDefaultDisplayArray(111);
	}

	return options;
}

function createSolicitationOptions() {
	var options = new Array();
	var i = 1;

	if (rfqAccess >= 3) {
		options[i] = new Array(newTxt,"javascript: doSubmit('/rfq/rfq_select_type.jsp', 'DoNothing'); void(0);",1,0,0);
		i++;
	}
	if (rfqAccess > 0) {
		options[i] = new Array(browseTxt,"javascript:browseFilter('rfqheader');void(0);",1,0,0);
		i++;
	}

	if (i > 1) {
		options[0] = createDefaultDisplayArray(97);
	}
	return options;
}

function createOrderOptions() {
	var options = new Array();
	var i = 1;

	if (poAccess >= 3) {
		if (isXpress) {
			options[i] = new Array(newTxt,"javascript: createOrder('PO'); void(0);",1,0,0);
		} else {
			options[i] = new Array(newTxt,"javascript: doSubmit('/orders/po_select_type.jsp', 'DoNothing'); void(0);",1,0,0);
		}
		i++;
	}
	if (poAccess >= 3 && reqsActive && isXpress) {
		options[i] = new Array(newFromReqTxt,"javascript: createOrderFromReq('PO'); void(0);",1,0,0);
		i++;
	}
	if (poAccess > 0) {
		options[i] = new Array(browseTxt,"javascript:browseFilter('poheader');void(0);",1,0,0);
		i++;
		options[i] = new Array("Browse By Item","javascript:browseFilter('poline');void(0);",1,0,0);
		i++;
		options[i] = new Array("Browse By Account","javascript:browseFilter('poline-account');void(0);",1,0,0);
		i++;
		options[i] = new Array("Browse By Document","javascript:browseFilter('po-attachment');void(0);",1,0,0);
		i++;
	}

	if (i > 1) {
		options[0] = createDefaultDisplayArray(150);
	}
	return options;
}

function createReceiptMenu(optionsTxt,oid, doubleStepReceiving, singlePageReceipt)
{
	var options = new Array();
	var i = 1;
	if (singlePageReceipt != "Y") {
		if (rcvAccess >= 3) {
			options[i] = new Array(optionsTxt[8], "javascript: doSubmit('/receipts/rec_select_type.jsp', 'DoNothing'); void(0);",1,0,0);
			i++;
		}
		if (rcvAccess >= 3) {
			options[i] = new Array(optionsTxt[9], "javascript: browseFilter('receipt-package'); void(0);",1,0,0);
			i++;
		}
		if (rcvAccess >= 3) {
			options[i] = new Array(optionsTxt[10], "javascript: browseFilter('receiptheader'); void(0);",1,0,0);
			i++;
		}
	}

	if (doubleStepReceiving == 'Y') {
		if (rcvByPkgAccess >= 3) {
			options[i] = new Array(optionsTxt[1], "javascript: browseFilter('receipt-order-pkg'); void(0);",1,0,0);
			i++;
		} else if (rcvByPkgAccess == 2) {
			options[i] = new Array(optionsTxt[1], "javascript: browseFilter('receipt-order-pkg-by-dept'); void(0);",1,0,0);
			i++;
		} else if (rcvByPkgAccess == 1) {
			options[i] = new Array(optionsTxt[1], "javascript: browseFilter('receipt-order-pkg-enduser'); void(0);",1,0,0);
			i++;
		}

		if (rcvFinalizeAccess >= 3) {
			options[i] = new Array(optionsTxt[2], "javascript:finalizeReceipts();void(0);",1,0,0);
			i++;
		}
	}

	/* ******************* */

	if (singlePageReceipt == "Y") {
		if(oid!="VSE06P"){
			if (rcvFromNothingAccess > 0) {
				options[i] = new Array(optionsTxt[3], "javascript: receiveFromNothing();void(0);",1,0,0);
				i++;
			}
			if (rcvByItemAccess >= 3 || rcvByEndUserAccess >= 3) {
				options[i] = new Array(optionsTxt[4], "javascript: resetBrowseFilter();browse('receipt-by-item-enduser-s');void(0);",1,0,0);
				i++;
			}
		}

		if ((rcvAdjAccess > 0 && oid == "HOY08P") || (rcvAdjAccess >= 3 && (rcvByItemAccess >= 3 || rcvByEndUserAccess >= 3))) {
			options[i] = new Array(optionsTxt[5],"javascript:adjustReceipts();void(0);",1,0,0);
			i++;
		}
		if (rcvReturnAccess >= 3) {
			options[i] = new Array(optionsTxt[6],"javascript:returnAgainstReceipts();void(0);",1,0,0);
			i++;
		}

		if (rcvHistoryAccess > 0) {
			options[i] = new Array(optionsTxt[10], "javascript: browseFilter('receiptheader'); void(0);",1,0,0);
			i++;
		}
	}
	/* ************************ */
	if (rcvHistoryAccess > 0) {
		options[i] = new Array(optionsTxt[7], "javascript:viewReceiptHistory();void(0);",1,0,0);
		i++;
	}

	if (quickRcvAccess > 0) {
		options[i] = new Array(optionsTxt[14], "javascript:quickRcv();void(0);",1,0,0);
		i++;
	}

	if (i > 1) {
		options[0] = createDefaultDisplayArray(150);
	}
	return options;
}

function createInvoiceOptions() {
	var options = new Array();
	var i = 1;

	if (invoiceAccess >= 2) {
		options[i] = new Array(newTxt,"javascript: doSubmit('/invoice/invoice_create.jsp', 'DoNothing'); void(0);",1,0,0);
		i++;
	}
	if (invoiceAccess > 0) {
		options[i] = new Array(browseTxt,"javascript:browseFilter('invoiceheader');void(0);",1,0,0);
		i++;
	}
	if (invoiceAccess > 0) {
		options[i] = new Array("Browse Import","javascript:browseFilter('invoiceheaderimport');void(0);",1,0,0);
		i++;
	}


//if (invoiceExtractActive && invoiceNumber != "N/A") {
	/*if (invoiceAccess > 0 && orgId == 'MSG07P' ) {
		options[i] = new Array("Invoice Extract","javascript: doSubmit('/invoice/inv_schedule_run.jsp', 'DoNothing'); void(0);",1,0,0);
		i++;
	}*/
	if (i > 1) {
		options[0] = createDefaultDisplayArray(97);
	}

	return options;
}

function createInventoryOptions(inventoryOptions)
{
	var options = new Array();
	var i = 1;

	if (invAccess >= 3) {
		options[i] = new Array(inventoryOptions[1], "javascript: createNewItem(); void(0);",1,0,0);
		i++;
	}
	if (invAccess > 0) {
		options[i] = new Array(inventoryOptions[2], "javascript: browseFilter('invitem-admin'); void(0);",1,0,0);
		i++;
	}
	if (invAccess > 0) {
		options[i] = new Array(inventoryOptions[3], "javascript: browseFilter('backorder-notifications'); void(0);",1,0,0);
		i++;
	}
	if (invAccess > 3) {
		options[i] = new Array(inventoryOptions[4], "javascript: unavailable(); void(0);",1,0,0);
		i++;
	}
	if (invAccess >= 3) {
		options[i] = new Array(inventoryOptions[5], "javascript: doSubmit('/inventory/inv_menu.jsp', 'DoNothing'); void(0);",1,0,0);
		i++;
	}
	if (returnAccess >= 3) {
		options[i] = new Array(inventoryOptions[6], "javascript: browseFilter('inv-returns-reqline'); void(0);",1,0,0);
		i++;
	}
	if (returnAccess >= 3) {
		options[i] = new Array(inventoryOptions[11], "javascript: setHiddenFields('<input type=\"hidden\" name=\"viewAction\" value=\"save\"><input type=\"hidden\" name=\"returnMethod\" value=\"OTC\">'); doSubmit('/inventory/inv_return_summary.jsp', 'DoNothing'); void(0);",1,0,0);
		i++;
	}
	if (returnAccess > 0) {
		options[i] = new Array(inventoryOptions[7], "javascript: browseFilter('inventory-returns'); void(0);",1,0,0);
		i++;
	}
	if (disbAccess >= 3) {
		options[i] = new Array(inventoryOptions[8], "javascript: doSubmit('/inventory/dsb_select_type.jsp', 'DoNothing'); void(0);",1,0,0);
		i++;
	}
	if (disbAccess > 0) {
		options[i] = new Array(inventoryOptions[9], "javascript:browseFilter('disbursementheader');void(0);",1,0,0);
		i++;
	}
	if (bomAccess >= 3) {
		options[i] = new Array(inventoryOptions[10], "javascript: browseFilter('bomitem'); void(0);",1,0,0);
		i++;
	}

	if (i > 1) {
		options[0] = createDefaultDisplayArray(180);
	}

	return options;
}

function createDisbursmentOptions() {
	var options = new Array();
	var i = 1;

	if (dsbAccess >= 3) {
		options[i] = new Array(newTxt,"javascript: doSubmit('/inventory/dsb_select_type.jsp', 'DoNothing'); void(0);",1,0,0);
		i++;
	}
	if (dsbAccess > 0) {
		options[i] = new Array(browseTxt,"javascript:browseFilter('disbursementheader');void(0);",1,0,0);
		i++;
	}

	if (i > 1) {
		options[0] = createDefaultDisplayArray(97);
	}
	return options;
}

function createReportOptions(reportOptions)
{
	var options = new Array();
	var i = 1;

	if (repAdminAccess > 0) {
		options[i] = new Array(reportOptions[1], "javascript: setReports('ADMREP'); void(0);",1,0,0);
		i++;
	}
	if (repReqAccess > 0) {
		options[i] = new Array(reportOptions[2], "javascript: setReports('REQREP'); void(0);",1,0,0);
		i++;
	}
	if (repRfqAccess > 0) {
		options[i] = new Array(reportOptions[3], "javascript: setReports('RFQREP'); void(0);",1,0,0);
		i++;
	}
	if (repPoAccess > 0) {
		options[i] = new Array(reportOptions[4], "javascript: setReports('POREP'); void(0);",1,0,0);
		i++;
	}
	if (rcvByPkgAccess > 0 || rcvByItemAccess > 0 || rcvFinalizeAccess > 0 || rcvAdjAccess > 0 || rcvReturnAccess > 0 || rcvHistoryAccess > 0) {
		options[i] = new Array(reportOptions[5], "javascript: setReports('RCVREP'); void(0);",1,0,0);
		i++;
	}
	if (repVchAccess > 0) {
		options[i] = new Array(reportOptions[6], "javascript: setReports('VCHREP'); void(0);",1,0,0);
		i++;
	}
	if (repInvAccess > 0) {
		options[i] = new Array(reportOptions[7], "javascript: setReports('INVREP'); void(0);",1,0,0);
		i++;
	}
	//options[i] = new Array(reportOptions[8], "javascript: setReports('STDTABLEREP'); void(0);",1,0,0);
	//	i++;
	options[i] = new Array(reportOptions[9], "javascript: setReports('SUPREP'); void(0);",1,0,0);
	i++;

	if (i > 1) {
		options[0] = createDefaultDisplayArray(100);
	}
	return options;
}

function createReportQueueOptions(reportOptions)
{
	var options = new Array();
	var i = 1;

	if (repAdminAccess > 0) {
		options[i] = new Array(reportOptions[1], "javascript: setReportsQueue('REPADM'); void(0);",1,0,0);
		i++;
	}
	if (repReqAccess > 0) {
		options[i] = new Array(reportOptions[2], "javascript: setReportsQueue('REPREQ'); void(0);",1,0,0);
		i++;
	}
	if (repRfqAccess > 0) {
		options[i] = new Array(reportOptions[3], "javascript: setReportsQueue('REPRFQ'); void(0);",1,0,0);
		i++;
	}
	if (repPoAccess > 0) {
		options[i] = new Array(reportOptions[4], "javascript: setReportsQueue('REPPO'); void(0);",1,0,0);
		i++;
	}
	if (rcvByPkgAccess > 0 || rcvByItemAccess > 0 || rcvFinalizeAccess > 0 || rcvAdjAccess > 0 || rcvReturnAccess > 0 || rcvHistoryAccess > 0) {
		options[i] = new Array(reportOptions[5], "javascript: setReportsQueue('REPRCV'); void(0);",1,0,0);
		i++;
	}
	if (repVchAccess > 0) {
		options[i] = new Array(reportOptions[6], "javascript: setReportsQueue('REPVCH'); void(0);",1,0,0);
		i++;
	}
	if (repInvAccess > 0) {
		options[i] = new Array(reportOptions[7], "javascript: setReportsQueue('REPINV'); void(0);",1,0,0);
		i++;
	}
	if (repBdgAccess > 0) {
		options[i] = new Array(reportOptions[8], "javascript: setReportsQueue('REPBDG'); void(0);",1,0,0);
		i++;
	}
	options[i] = new Array(reportOptions[9], "javascript: setReportsQueue('REPSUP'); void(0);",1,0,0);
	i++;

	if (i > 1) {
		options[0] = createDefaultDisplayArray(100);
	}
	return options;
}

function createSaleOptions() {
	var options = new Array();
	var i = 1;

	if (salesAccess >= 3) {
		options[i] = new Array(newTxt,"javascript: doSubmit('/sales/sale_item.jsp', 'SaleCreate'); void(0);",1,0,0);
		i++;
	}
	if (salesAccess > 0) {
		options[i] = new Array(browseTxt,"javascript:browseFilter('saleheader');void(0);",1,0,0);
		i++;
	}

	if (i > 1) {
		options[0] = createDefaultDisplayArray(97);
	}
	return options;
}

function createAssetOptions()
  {
  	var options = new Array();
    var i = 1;

    if (assetAccess >= 3) {
		options[i] = new Array(newTxt,"javascript: setHiddenFields('<input type=\"hidden\" name=\"action\" value=\"itemnew\">'); doSubmit('/asset/asset_general.jsp', 'DoNothing'); void(0);",1,0,0);
		i++;
	}
	if (assetAccess > 0) {
		options[i] = new Array(browseTxt,"javascript:browseFilter('asset_main');void(0);",1,0,0);
		i++;
	}

	if (i > 1) {
		options[0] = createDefaultDisplayArray(97);
	}
	return options;
}
