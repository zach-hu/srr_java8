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

//Inventory Return action options - created onload
Array91 = [
[115,		// menu width
735,		// left_position
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

var viewAction = "";
var disbNumber = "";
var disbStatus = "";
var disbursed = "";
var backorder = "";

function createDisbOptionsMenu(originalArray) {
	var options = new Array();
	var i = 1;

	options[0] = originalArray;

	if (disbStatus < backorder)
	{
		options[i] = new Array("Validate","javascript: validateDisb('VALIDATE'); void(0);",1,0,0);
		i++;
	}
	if (!isNA(disbNumber))
	{
		options[i] = new Array("History", "javascript: viewHistory(); void(0);", 1, 0, 0);
		i++;
		options[i] = new Array(printPdfLabel,"javascript: printPdf(); void(0);",1,0,0);
		i++;
	}

	options[i] = new Array("Print Preview", "javascript: printPreview(); void(0);", 1, 0, 0);
	i++;

	options[i] = new Array("Switch to Classic", "javascript: switchView(); void(0);", 1, 0, 0);
	i++;

	return options;
}
