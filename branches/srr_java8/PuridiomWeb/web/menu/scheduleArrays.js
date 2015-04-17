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

//Select Requisition Schedule Type
Array98 = [
[150,		// menu width
600,		// left_position
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
["Performance Schedule","javascript: void(0); browseSchedules('R', 'schedule');",1,0,0],
["Delivery Schedule","javascript: void(0); browseSchedules('D', 'schedule');",1,0,0],
["Milestone Schedule","javascript: void(0); browseSchedules('M', 'schedule');",1,0,0],
["Payment Schedule","javascript: void(0); browseSchedules('P', 'schedule');",1,0,0],
["Other Schedule","javascript: void(0); browseSchedules('O', 'schedule');",1,0,0]
]


//Select Purchase Order Schedule Type
Array99 = [
[150,
380,
150,
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
["Performance Schedule","javascript: void(0); browseSchedules('P', 'schedule');",1,0,0],
["Delivery Schedule","javascript: void(0); browseSchedules('D', 'schedule');",1,0,0],
["Milestone Schedule","javascript: void(0); browseSchedules('M', 'schedule');",1,0,0],
["Payment Schedule","javascript: void(0); browseSchedules('P', 'schedule');",1,0,0],
["Other Schedule","javascript: void(0); browseSchedules('O', 'schedule');",1,0,0]
]
