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

//Purchase Order action options - created onload
Array91 = [
[100,		// menu width
535,		// left_position
158,		// top_position
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

var poType = "";

function createPoOptionsMenu(originalArray) {
	var options = new Array();
	var i = 1;

	options[0] = originalArray;

	if (poType == "BO" || poType == "SB" || poType == "DO") {
		options[i] = new Array("View Payments","javascript: viewReleasePayments(); void(0);",1,0,0);
		i++;
	}
	else {
		options[i] = new Array("View Payments","javascript: viewPayments(); void(0);",1,0,0);
		i++;
	}
	options[i] = new Array("Flip to Invoice","javascript: createInvoice(); void(0);",1,0,0);
	i++;

	return options;
}
