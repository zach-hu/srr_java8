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

//Sale action options - created onload
Array91 = [
[125,		// menu width
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


var viewType = "";
var saleNumber = "";
var saleStatus = "";
var saleBuyerCount = 0;
var saleInProgress = "";
var saleBidsPending = "";
var saleBidsReceived = "";

function findToPage()
{
  if (viewType == "WIZARD")
  {
  	return '/sales/sale_review.jsp';
  }
  else
  {
  	return '/sales/sale_summary.jsp';
  }
}

function createSaleOptionsMenu(originalArray) {
	var options = new Array();
	var i = 1;

	options[0] = originalArray;

	if (saleNumber != "N/A") {
		options[i] = new Array("Save As","javascript: saleSaveAs(); void(0);",1,0,0);
		i++;
	}
	/*
	if (saleStatus == saleInProgress) {
		options[i] = new Array("Delete","javascript: doSubmit('/menu/main_menu.jsp', 'SaleDelete'); void(0);",1,0,0);
		i++;
	} else
	*/
	if (saleStatus <= saleBidsReceived) {
		options[i] = new Array("Cancel","javascript: if (verifyAction('Cancel this Surplus Sale?')) { doSubmit(findToPage(), 'SaleCancel;SaleRetrieve'); } void(0);",1,0,0);
		i++;
	} else if (saleStatus == saleBidsPending) {
		options[i] = new Array("Pause Bid Event","javascript: if (verifyAction('Pause the current bid even for this surplus sale?')) { pauseEvent(); } void(0);",1,0,0);
		i++;
	}
	if (saleNumber != "N/A") {
		options[i] = new Array("History","javascript: viewHistory(); void(0);",1,0,0);
		i++;
		options[i] = new Array(printPdfLabel,"javascript: printPdf(); void(0);",1,0,0);
		i++;
	}
	if (saleStatus < saleBidsPending) {
		options[i] = new Array("Validate","javascript: validateSale('VALIDATE'); void(0);",1,0,0);
		i++;
	}
	if (saleBuyerCount > 0) {
		options[i] = new Array("Bid Worksheet","javascript: doSubmit('/sales/sale_bids.jsp','SaleRetrieve'); void(0);",1,0,0);
		i++;
	}
	options[i] = new Array("Print Preview","javascript: salePreview(); void(0);",1,0,0);
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
	return options;
}
