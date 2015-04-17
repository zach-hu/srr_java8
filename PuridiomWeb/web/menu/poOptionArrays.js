/***************************************************************************
							Version 4 Menu ARRAYS
***************************************************************************/
var	font_color = "black";
var	mouseover_font_color = "#FFFFFF";
var	background_color = "#FFFFFF";
var	mouseover_background_color = "#FF7500";
var	border_color = "#FF7500";
var	separator_color = "#FF7500";
var	top_is_permanent = 0;
var	top_is_horizontal = 0;
var	tree_is_horizontal = 0;
var	position_under = 1;
var	top_more_images_visible = 1;
var	tree_more_images_visible = 1;
var	evaluate_upon_tree_show = "null";
var	evaluate_upon_tree_hide = "null";
var	right_to_left;

//Purchase Order action options - created onload
Array91 = [
[125,		// menu width
715,		// left_position
160,		// top_position
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
[]
]

var viewType = "";
var poNumber = "";
var poStatus = "";
var pyStatus = "";
var poType = "";
var poRequisitioner = "";
var poBuyer = "";
var poDepartment = "";
var evaluationIncomplete = "";
var ctInProgress = "";
var ctAwarded = "";
var poInProgress = "";
var poAwarded = "";
var poReceived = "";
var poClosed = "";
var poAmended = "";
var pyFullyInvoiced = "";
var poAccess = 0;
var revisionNumber = 0;
var viewPaymentHistory = false;
var supevalaccess = 0;
var uploadItemsAccess = "";
var uploadItemsRole = "";
var allowEdit = "";
var allowSaveAs = "";
var allowAdmin = "";
var isRequisitionOrder = "";
var lastRevision = "";
var oid = "";
var budgetActive = false ;
var changeReqAccess = "";


function createPoOptionsMenu(originalArray) {
	var options = new Array();
	var i = 1;

	options[0] = originalArray;

	if (poNumber != "N/A" && poAccess > 2 && allowSaveAs && !(oid == "HOY08P" && !allowAdmin)) {
		if(oid=="VSE06P"){
			options[i] = new Array("Save As","javascript: orderSaveAsCheckAccount(); void(0);",1,0,0);
			i++;
		}
		else if (!(oid == "MSG07P" && revisionNumber > 0))
		{
			options[i] = new Array("Save As","javascript: orderSaveAs(); void(0);",1,0,0);
			i++;
		}
	}

	if (oid=="DTN07P"){
		if (poStatus == poInProgress && revisionNumber == 0 && poAccess == 99 && allowEdit) {
			options[i] = new Array("Delete","javascript: deleteOrder(); void(0);",1,0,0);
			i++;
		}
	}
	else if (oid!="BSC04P"){
		if (!(oid == "HOY08P" && !allowAdmin)) {
			if (poNumber != "N/A" && poAccess > 1 && (poStatus <= poAwarded && poStatus >= poInProgress || poStatus == poInProgress && revisionNumber > 0) && allowEdit) {
				options[i] = new Array("Cancel","javascript: orderCancelClose('cancel'); void(0);",1,0,0);
				i++;
			}
			else if (poNumber != "N/A" && poAccess > 1 && (poStatus <= ctAwarded && poStatus >= ctInProgress && revisionNumber > 0) && poType == "CT" && allowEdit) {
				options[i] = new Array("Cancel Contract","javascript: orderCancelClose('cancel'); void(0);",1,0,0);
				i++;
			}
		}
	}else {
		if(poStatus != "2930"){ //contract awarded and awarded
			if (poType != "CT" && poNumber != "N/A" && poAccess > 1 && (poStatus <= poAwarded && poStatus >= poInProgress || poStatus == poInProgress && revisionNumber > 0) && allowEdit) {
				options[i] = new Array("Cancel","javascript: orderCancelClose('cancel'); void(0);",1,0,0);
				i++;
			}
			else if (poStatus != "3030" && poNumber != "N/A" && poAccess > 1 && (poStatus <= ctAwarded && poStatus >= ctInProgress && revisionNumber > 0) && poType == "CT" && allowEdit) {
				options[i] = new Array("Cancel Contract","javascript: orderCancelClose('cancel'); void(0);",1,0,0);
				i++;
			}
		}
	}

	if (oid!="DTN07P" && !(oid == "HOY08P" && !allowAdmin))	{
			if (poNumber != "N/A" && poAccess > 1 && poStatus >= poAwarded && poStatus < poClosed && allowEdit) {
				options[i] = new Array("Close","javascript: orderCancelClose('close'); void(0);",1,0,0);
				i++;
			}
			else if (poNumber != "N/A" && poAccess > 1 && poStatus >= ctAwarded && poStatus < poClosed && poType == "CT" && allowEdit) {
				options[i] = new Array("Close Contract","javascript: orderCancelClose('close'); void(0);",1,0,0);
				i++;
			}
	}
	if (revisionNumber > 0 && lastRevision == "C") {
		options[i] = new Array("Order Revisions","javascript: BrowseOrderRevision(); void(0);",1,0,0);
		i++;
	}

	if(poStatus >= poClosed){
 		options[i] = new Array("Reset Close Status","javascript: resetCloseStatus(); void(0);",1,0,0);
		i++;
	}

	if (!(oid == "HOY08P" && !allowAdmin)) {
		if (poNumber != "N/A" && poAccess > 1 && poStatus >= poAwarded && poStatus < poClosed && lastRevision == "C" && allowEdit) {
			options[i] = new Array("Revision","javascript: createRevision(); void(0);",1,0,0);
			i++;
		}
		else if (poNumber != "N/A" && poAccess > 1 && poStatus >= ctAwarded && poStatus < poClosed && lastRevision == "C" && poType == "CT" && allowEdit) {
			options[i] = new Array("Contract Revision","javascript: createRevision(); void(0);",1,0,0);
			i++;
		}
	}

	if (changeReqAccess >= 3 && allowCreateChangeRequest !="false" && poNumber != "N/A" && lastRevision == "C" && poStatus >= poAwarded && poStatus < poClosed && lastRevision == "C" && (isEmpty(pyStatus) || pyStatus < pyFullyInvoiced)) {
		if ((isAnAdminBuyer && MasterUser >=3) || (poRequisitioner == userCode || poBuyer == userCode || (!isEmpty(userDepartment) && userDepartment == poDepartment))) {
			options[i] = new Array(changeReqTxt, "javascript: createChangeRequest(); void(0);",1,0,0);
			i++;
		}
	}
	if (poNumber != "N/A" && poStatus >= poAwarded && poStatus < poClosed && lastRevision == "C") {
		if (singlePageReceipt == "Y") {
			if (rcvByItemAccess >= 3 || rcvByEndUserAccess >= 3 && (poRequisitioner == userCode || poBuyer == userCode)) {
				options[i] = new Array(receiptOptions[4], "javascript: receiveItemsForOrder(); void(0);",1,0,0);
				i++;
			}
		}
	}
	if (invoiceAccess >= 2 && poNumber != "N/A" && lastRevision == 'C' && poStatus >= poAwarded && poStatus < poInvoiced && (isEmpty(pyStatus) || pyStatus < pyFullyInvoiced) && poType != 'BO' && poType != 'DO' && poType != 'SB') {
		options[i] = new Array(invoiceTxt, "javascript: doSubmit('/invoice/iv_general_info.jsp', 'InvoiceCreateFromOrder'); void(0);",1,0,0);
		i++;
	}
	if (budgetActive && poStatus < poAwarded)
	{
		options[i] = new Array("Budget Check","javascript: getBudgetCheck('BUDGETCHECK','2','PO','A'); void(0);",1,0,0);
		i++;
	}
	if (budgetActive && (poStatus >= poAwarded)) {
		options[i] = new Array("Budget Review","javascript: getBudgetReview(); void(0);",1,0,0);
		i++;
	}

	if (poStatus == poInProgress || poStatus == ctInProgress)
	{
		options[i] = new Array("Validate","javascript: poValidate('VALIDATE'); void(0);",1,0,0);
		i++;
	}
	if (oid=="HOY08P")	{
		if (poNumber != "N/A") {
			options[i] = new Array("Transmit Document","javascript: printPdf(); void(0);",1,0,0);
			i++;
			options[i] = new Array("History","javascript: viewHistory(); void(0);",1,0,0);
			i++;
			//options[i] = new Array("Print Email","javascript: emailApp(); void(0);",1,0,0);
			//i++;
		}
	}
	else{
		if (poNumber != "N/A") {
			options[i] = new Array(printPdfLabel,"javascript: printPdf(); void(0);",1,0,0);
			i++;
			options[i] = new Array("History","javascript: viewHistory(); void(0);",1,0,0);
			i++;
			//options[i] = new Array("Print Email","javascript: emailApp(); void(0);",1,0,0);
			//i++;
		}
	}

	if (poStatus < poClosed)
	{
		if (oid!="BSC04P")
		{
			options[i] = new Array("Procurement Remarks", "javascript: doSubmit('/orders/po_buyer_remarks.jsp', 'PoRetrieveBuyerRemarks'); void(0);", 1, 0, 0);
			i++;
		}
		else if (oid=="BSC04P" && poType=="CT")
		{
			options[i] = new Array("Procurement Remarks", "javascript: doSubmit('/orders/po_buyer_remarks.jsp', 'PoRetrieveBuyerRemarks'); void(0);", 1, 0, 0);
			i++;
		}
	}

	if (poStatus > poInProgress || (poStatus > ctInProgress && poStatus < poInProgress))
	{
		options[i] = new Array("Approval Notes","javascript: viewAppNotes(); void(0);",1,0,0);
		i++;
	}
	/*if (poStatus <= poReceived && (poType == "BO" || poType == "SB" || poType == "DO")) {
		if (poNumber != "N/A") {
			options[i] = new Array("Blanket Security","javascript: doSubmit('/orders/po_security.jsp', 'PoSecurityRetrieveBy'); void(0);",1,0,0);
			i++;
		} else {
			options[i] = new Array("Blanket Security","javascript: alert('The order must be saved prior to setting up Blanket Security!'); void(0);",1,0,0);
			i++;
		}
	}*/
	if (viewPaymentHistory && poType != "BO" && poType != "SB" && poType != "DO" && poStatus > poAwarded && poStatus < poCancelled) {
		options[i] = new Array("Payment History","javascript: viewPaymentHistory(); void(0);",1,0,0);
		i++;
	}
/*	if (poType == "BO" || poType == "SB" || poType == "DO") {
		options[i] = new Array("View Release Payments","javascript: viewReleasePayments(); void(0);",1,0,0);
		i++;
	}
	else {
		options[i] = new Array("View Payments","javascript: viewPayments(); void(0);",1,0,0);
		i++;
	}
*/
	if(supevalaccess >0)
	{
		options[i] = new Array("Supplier Evaluation","javascript: viewSupplierEvaluation(); void(0);",1,0,0);
		i++;
	}
	if (poStatus > poInProgress && poType == "CT") {
		options[i] = new Array("View Associated Orders","javascript: viewContractOrders(); void(0);",1,0,0);
		i++;
	}
	options[i] = new Array("Print Preview","javascript: orderPreview(); void(0);",1,0,0);
	i++;
	if (viewType == "WIZARD")
	{
		options[i] = new Array("Switch to Classic","javascript: switchView(); void(0);",1,0,0);
		i++;
	}
	else
	{
		options[i] = new Array("Switch to Wizard","javascript: switchView(); void(0);",1,0,0);
		i++;
	}

	 if (poNumber != "N/A" && (poStatus == poInProgress || poStatus == ctInProgress) && uploadItemsRole!= "disabled" && uploadItemsAccess == "Y" && allowEdit) {
 		options[i] = new Array("Upload Items","javascript: RfqReqPoAddItems(); void(0);",1,0,0);
		i++;
	}

	 if ((poStatus == poInProgress || poStatus == ctInProgress) && revisionNumber > 0) {
 		options[i] = new Array("Delete Revision","javascript: poDeleteRevision(); void(0);",1,0,0);
		i++;
	}

	return options;
}
