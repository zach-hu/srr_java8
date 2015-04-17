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

//Invoice action options - created onload
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

var invoiceNumber = "";
var invoiceStatus = "";
var ivcInProgress = "";
var ivcRecalled = "";
var ivcApproving = "";
var ivcApproved = "";
var ivcRejected = "";
var ivcPaid = "";
var ivcAccess = 0;
var invoiceExtractActive = false;
var recallAccess = false;

function createInvoiceOptionsMenu(originalArray) {
	var options = new Array();
	var i = 1;
	var toPage = '';

	options[0] = originalArray;

	/*
	if (invoiceStatus == ivcInProgress && invoiceNumber != "N/A") {
		options[i] = new Array("Delete","javascript: if (verifyAction('Delete this Invoice?')) { doSubmit('/menu/main_menu.jsp', 'InvoiceDelete'); } void(0);",1,0,0);
		i++;
	}
	*/
	if (ivcAccess > 1 && ((invoiceStatus >= ivcInProgress && invoiceStatus < ivcApproved) || invoiceStatus == ivcRejected)) {
		options[i] = new Array("Cancel","javascript: invoiceCancel(); void(0);",1,0,0);
		i++;
	}
	if (recallAccess && invoiceStatus <= ivcApproving && invoiceStatus != ivcRecalled) {
		options[i] = new Array("Recall","javascript: if (verifyAction('Are you sure?')) { doSubmit('/invoice/iv_review.jsp', 'InvoiceRecall;InvoiceRetrieve'); } void(0);",1,0,0);
		i++;
	}
	if (invoiceStatus == ivcInProgress || invoiceStatus == ivcRecalled) {
		options[i] = new Array("Validate","javascript: doSubmit('/invoice/iv_validation.jsp', 'InvoiceValidate'); void(0);",1,0,0);
		i++;
	}
	if (invoiceStatus > ivcInProgress) {
		options[i] = new Array("Routing List","javascript: getRoutingList('ROUTING'); void(0);",1,0,0);
		i++;
	}
	if (invoiceStatus == ivcApproving) {
		options[i] = new Array("Resend Email To Current Approver","javascript: resendInvoiceToApprover(); void(0);",1,0,0);
		i++;
	}
	if (!isNA(invoiceNumber)) {
		options[i] = new Array("History","javascript: viewHistory(); void(0);",1,0,0);
		i++;
	}
	if (invoiceExtractActive && !isNA(invoiceNumber) && invoiceStatus >= ivcApproved) {
		options[i] = new Array("Extract Invoice","javascript: doSubmit('/invoice/iv_extract_confirm.jsp', 'SungardInvoiceExtract'); void(0);",1,0,0);
		i++;
	}
	if (invoiceNumber != "N/A") {
		options[i] = new Array(printPdfLabel,"javascript: doSubmit('/invoice/iv_print_pdf.jsp', 'DoNothing'); void(0);",1,0,0);
		i++;
	}
	if (invoiceStatus >= ivcApproved) {
		options[i] = new Array("Payments","javascript: viewPayments(); void(0);",1,0,0);
		i++;
	}

	return options;
}
