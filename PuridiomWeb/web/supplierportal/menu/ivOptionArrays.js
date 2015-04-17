/***************************************************************************
							Version 4 Menu ARRAYS
***************************************************************************/
var	font_color = "black";
var	mouseover_font_color = "#FFFFFF";
var	background_color = "#FFFFFF";
var	mouseover_background_color = "#7C8BCC";
var	border_color = "#7C8BCC";
var	separator_color = "#7C8BCC";
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
[115,		// menu width
550,		// left_position
150,		// top_position
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
var invoiceExtractActive = false;

function createInvoiceOptionsMenu(originalArray) {
	var options = new Array();
	var i = 1;
	var toPage = '';

	options[0] = originalArray;

	if (invoiceStatus == ivcInProgress && invoiceNumber != "N/A") {
		options[i] = new Array("Delete","javascript: if (verifyAction('Delete this Invoice?')) { doSubmit('/menu/main_menu.jsp', 'InvoiceDelete'); } void(0);",1,0,0);
		i++;
	}
	if (invoiceStatus == ivcInProgress) {
		options[i] = new Array("Validate","javascript: doSubmit('/invoice/iv_validate.jsp', 'InvoiceValidate'); void(0);",1,0,0);
		i++;
	}
	if (invoiceExtractActive && invoiceNumber != "N/A") {
		options[i] = new Array("Extract Invoice","javascript: doSubmit('/invoice/iv_extract_confirm.jsp', 'SungardInvoiceExtract'); void(0);",1,0,0);
		i++;
	}

	return options;
}
