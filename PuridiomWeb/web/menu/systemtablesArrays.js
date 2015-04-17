/***************************************************************************
							Version 1 System Tables ARRAYS
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

//Account UDFs Menu for admin/systemtables/system_tables.jsp
Array3 = [
[125,		// menu width
135,		// left_position
,		// top_position
font_color,	// font_color
mouseover_font_color,	// mouseover_font_color
background_color,	// background_color
mouseover_background_color,	// mouseover_background_color
border_color,	// border_color
separator_color,	// separator_color
top_is_permanent,		// top_is_permanent
top_is_horizontal,		// top_is_horizontal
tree_is_horizontal,		// tree_is_horizontal
position_under,		// position_under
top_more_images_visible,		// top_more_images_visible
tree_more_images_visible,		// tree_more_images_visible
evaluate_upon_tree_show,		// evaluate_upon_tree_show
evaluate_upon_tree_hide,		// evaluate_upon_tree_hide
right-to-left],		//right-to-left
["Account UDF 1","javascript: browseStdTable('AC01');",1,0,0],
["Account UDF 2","javascript: browseStdTable('AC02');",1,0,0],
["Account UDF 3","javascript: browseStdTable('AC03');",1,0,0],
["Account UDF 4","javascript: browseStdTable('AC04');",1,0,0],
["Account UDF 5","javascript: browseStdTable('AC05');",1,0,0],
["Account UDF 6","javascript: browseStdTable('AC06');",1,0,0]
]

//Asset Tracking UDFs Menu for admin/systemtables/system_tables.jsp
Array4 = [
[125,		// menu width
135,		// left_position
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
right-to-left],
["Asset UDF 1","javascript: browseStdTable('AUD1');",1,0,0],
["Asset UDF 2","javascript: browseStdTable('AUD2');",1,0,0],
["Asset UDF 3","javascript: browseStdTable('AUD3');",1,0,0],
["Asset UDF 4","javascript: browseStdTable('AUD4');",1,0,0],
["Asset UDF 5","javascript: browseStdTable('AUD5');",1,0,0]
]

//Catalog/Stock/Additional Inventory UDFs Menu for admin/systemtables/system_tables.jsp
Array5 = [
[125,		// menu width
135,		// left_position
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
right-to-left],
["Catalog/Stock UDF 1","javascript: browseStdTable('CT01');",1,0,0],
["Catalog/Stock UDF 2","javascript: browseStdTable('CT02');",1,0,0],
["Catalog/Stock UDF 3","javascript: browseStdTable('CT03');",1,0,0],
["Catalog/Stock UDF 4","javascript: browseStdTable('CT04');",1,0,0],
["Catalog/Stock UDF 5","javascript: browseStdTable('CT05');",1,0,0],
["Inventory UDF 6","javascript: browseStdTable('CT06');",1,0,0],
["Inventory UDF 7","javascript: browseStdTable('CT07');",1,0,0],
["Inventory UDF 8","javascript: browseStdTable('CT08');",1,0,0],
["Inventory UDF 9","javascript: browseStdTable('CT09');",1,0,0],
["Inventory UDF 10","javascript: browseStdTable('CT10');",1,0,0],
["Inventory UDF 11","javascript: browseStdTable('CT11');",1,0,0],
["Inventory UDF 12","javascript: browseStdTable('CT12');",1,0,0],
["Inventory UDF 13","javascript: browseStdTable('CT13');",1,0,0],
["Inventory UDF 14","javascript: browseStdTable('CT14');",1,0,0],
["Inventory UDF 15","javascript: browseStdTable('CT15');",1,0,0]
]

//Inventory Bin UDFs Menu for admin/systemtables/system_tables.jsp
Array6 = [
[125,		// menu width
135,		// left_position
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
right-to-left],
["Inventory Bin UDF 1","javascript: browseStdTable('BN01');",1,0,0],
["Inventory Bin UDF 2","javascript: browseStdTable('BN02');",1,0,0],
["Inventory Bin UDF 3","javascript: browseStdTable('BN03');",1,0,0],
["Inventory Bin UDF 4","javascript: browseStdTable('BN04');",1,0,0],
["Inventory Bin UDF 5","javascript: browseStdTable('BN05');",1,0,0]
]

//Inventory Form UDFs Menu for admin/systemtables/system_tables.jsp
Array7 = [
[125,		// menu width
135,		// left_position
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
right-to-left],
["Form Artwork","javascript: browseStdTable('FART');",1,0,0],
["Form Binding","javascript: browseStdTable('FBND');",1,0,0],
["Form Blockout","javascript: browseStdTable('FBLK');",1,0,0],
["Form Classes","javascript: browseStdTable('FCLS');",1,0,0],
["Form Colors","javascript: browseStdTable('FCOL');",1,0,0],
["Form Folds","javascript: browseStdTable('FFLD');",1,0,0],
["Form Fonts","javascript: browseStdTable('FFNT');",1,0,0],
["Form Ink","javascript: browseStdTable('FINK');",1,0,0],
["Form Packaging","javascript: browseStdTable('FPKG');",1,0,0],
["Form Paper / Cover Stock","javascript: browseStdTable('FPPR');",1,0,0],
["Form Paper Colors","javascript: browseStdTable('FPCO');",1,0,0],
["Form Paper Grades","javascript: browseStdTable('FGRD');",1,0,0],
["Form Sizes","javascript: browseStdTable('FSIZ');",1,0,0],
["Form Spec. UDF 1","javascript: browseStdTable('FSP1');",1,0,0],
["Form Spec. UDF 2","javascript: browseStdTable('FSP2');",1,0,0],
["Form Spec. UDF 3","javascript: browseStdTable('FSP3');",1,0,0],
["Form Types","javascript: browseStdTable('FTYP');",1,0,0],
["Form UDF 1","javascript: browseStdTable('FUF1');",1,0,0],
["Form UDF 2","javascript: browseStdTable('FUF2');",1,0,0],
["Form Weight","javascript: browseStdTable('FWGT');",1,0,0]
]

//Inventory UDFs Menu for admin/systemtables/system_tables.jsp
Array8 = [
[125,		// menu width
135,		// left_position
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
right-to-left],
["Inventory UDF 1","javascript: browseStdTable('IN01');",1,0,0],
["Inventory UDF 2","javascript: browseStdTable('IN02');",1,0,0],
["Inventory UDF 3","javascript: browseStdTable('IN03');",1,0,0],
["Inventory UDF 4","javascript: browseStdTable('IN04');",1,0,0],
["Inventory UDF 5","javascript: browseStdTable('IN05');",1,0,0]
]

//Line UDFs Menu for admin/systemtables/system_tables.jsp
Array9 = [
[125,		// menu width
135,		// left_position
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
right-to-left],
["Line UDF 1","javascript: browseStdTable('LN01');",1,0,0],
["Line UDF 2","javascript: browseStdTable('LN02');",1,0,0],
["Line UDF 3","javascript: browseStdTable('LN03');",1,0,0],
["Line UDF 4","javascript: browseStdTable('LN04');",1,0,0],
["Line UDF 5","javascript: browseStdTable('LN05');",1,0,0],
["Line UDF 6","javascript: browseStdTable('LN06');",1,0,0],
["Line UDF 7","javascript: browseStdTable('LN07');",1,0,0],
["Line UDF 8","javascript: browseStdTable('LN08');",1,0,0],
["Line UDF 9","javascript: browseStdTable('LN09');",1,0,0],
["Line UDF 10","javascript: browseStdTable('LN10');",1,0,0]
]

//Purchase Order UDFs Menu for admin/systemtables/system_tables.jsp
Array10 = [
[125,		// menu width
135,		// left_position
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
right-to-left],
["Purchase Order UDF 1","javascript: browseStdTable('PO01');",1,0,0],
["Purchase Order UDF 2","javascript: browseStdTable('PO02');",1,0,0],
["Purchase Order UDF 3","javascript: browseStdTable('PO03');",1,0,0],
["Purchase Order UDF 4","javascript: browseStdTable('PO04');",1,0,0],
["Purchase Order UDF 5","javascript: browseStdTable('PO05');",1,0,0],
["Purchase Order UDF 6","javascript: browseStdTable('PO06');",1,0,0],
["Purchase Order UDF 7","javascript: browseStdTable('PO07');",1,0,0],
["Purchase Order UDF 8","javascript: browseStdTable('PO08');",1,0,0],
["Purchase Order UDF 9","javascript: browseStdTable('PO09');",1,0,0],
["Purchase Order UDF 10","javascript: browseStdTable('PO10');",1,0,0]
]

//Receiving UDFs Menu for admin/systemtables/system_tables.jsp
Array11 = [
[125,		// menu width
135,		// left_position
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
right-to-left],
["Receiving UDF 1","javascript: browseStdTable('RC01');",1,0,0],
["Receiving UDF 2","javascript: browseStdTable('RC02');",1,0,0],
["Receiving UDF 3","javascript: browseStdTable('RC03');",1,0,0],
["Receiving UDF 4","javascript: browseStdTable('RC04');",1,0,0],
["Receiving UDF 5","javascript: browseStdTable('RC05');",1,0,0]
]

//Requisition UDFs Menu for admin/systemtables/system_tables.jsp
Array12 = [
[125,		// menu width
135,		// left_position
,		// top_position
"black",	// font_color
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
right-to-left],
["Requisition UDF 1","javascript: browseStdTable('RQ01');",1,0,0],
["Requisition UDF 2","javascript: browseStdTable('RQ02');",1,0,0],
["Requisition UDF 3","javascript: browseStdTable('RQ03');",1,0,0],
["Requisition UDF 4","javascript: browseStdTable('RQ04');",1,0,0],
["Requisition UDF 5","javascript: browseStdTable('RQ05');",1,0,0],
["Requisition UDF 6","javascript: browseStdTable('RQ06');",1,0,0],
["Requisition UDF 7","javascript: browseStdTable('RQ07');",1,0,0],
["Requisition UDF 8","javascript: browseStdTable('RQ08');",1,0,0],
["Requisition UDF 9","javascript: browseStdTable('RQ09');",1,0,0],
["Requisition UDF 10","javascript: browseStdTable('RQ10');",1,0,0]
]

//RFQ UDFs Menu for admin/systemtables/system_tables.jsp
Array13 = [
[125,		// menu width
135,		// left_position
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
right-to-left],
["RFQ UDF 1","javascript: browseStdTable('RF01');",1,0,0],
["RFQ UDF 2","javascript: browseStdTable('RF02');",1,0,0],
["RFQ UDF 3","javascript: browseStdTable('RF03');",1,0,0],
["RFQ UDF 4","javascript: browseStdTable('RF04');",1,0,0],
["RFQ UDF 5","javascript: browseStdTable('RF05');",1,0,0],
["RFQ UDF 6","javascript: browseStdTable('RF06');",1,0,0],
["RFQ UDF 7","javascript: browseStdTable('RF07');",1,0,0],
["RFQ UDF 8","javascript: browseStdTable('RF08');",1,0,0],
["RFQ UDF 9","javascript: browseStdTable('RF09');",1,0,0],
["RFQ UDF 10","javascript: browseStdTable('RF10');",1,0,0]
]

//Supplier Compliance UDFs Menu for admin/systemtables/system_tables.jsp
Array14 = [
[125,		// menu width
135,		// left_position
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
right-to-left],
["Supplier Compliance UDF 1","javascript: browseStdTable('SCR1');",1,0,0],
["Supplier Compliance UDF 2","javascript: browseStdTable('SCR2');",1,0,0],
["Supplier Compliance UDF 3","javascript: browseStdTable('SCR3');",1,0,0],
["Supplier Compliance UDF 4","javascript: browseStdTable('SCR4');",1,0,0],
["Supplier Compliance UDF 5","javascript: browseStdTable('SCR5');",1,0,0]
]

//Supplier Contract UDFs Menu for admin/systemtables/system_tables.jsp
Array15 = [
[125,		// menu width
135,		// left_position
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
right-to-left],
["Supplier Contract UDF 1","javascript: browseStdTable('SCC1');",1,0,0],
["Supplier Contract UDF 2","javascript: browseStdTable('SCC2');",1,0,0],
["Supplier Contract UDF 3","javascript: browseStdTable('SCC3');",1,0,0],
["Supplier Contract UDF 4","javascript: browseStdTable('SCC4');",1,0,0],
["Supplier Contract UDF 5","javascript: browseStdTable('SCC5');",1,0,0]
]

//Supplier UDFs Menu for admin/systemtables/system_tables.jsp
Array16 = [
[125,		// menu width
135,		// left_position
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
right-to-left],
["Supplier UDF 1","javascript: browseStdTable('VN01');",1,0,0],
["Supplier UDF 2","javascript: browseStdTable('VN02');",1,0,0],
["Supplier UDF 3","javascript: browseStdTable('VN03');",1,0,0],
["Supplier UDF 4","javascript: browseStdTable('VN04');",1,0,0],
["Supplier UDF 5","javascript: browseStdTable('VN05');",1,0,0],
["Supplier UDF 6","javascript: browseStdTable('VN06');",1,0,0],
["Supplier UDF 7","javascript: browseStdTable('VN07');",1,0,0],
["Supplier UDF 8","javascript: browseStdTable('VN08');",1,0,0],
["Supplier UDF 9","javascript: browseStdTable('VN09');",1,0,0],
["Supplier UDF 10","javascript: browseStdTable('VN10');",1,0,0]
]

//Voucher UDFs Menu for admin/systemtables/system_tables.jsp
Array17 = [
[125,		// menu width
135,		// left_position
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
right-to-left],
["Voucher UDF 1","javascript: browseStdTable('PV01');",1,0,0],
["Voucher UDF 2","javascript: browseStdTable('PV02');",1,0,0],
["Voucher UDF 3","javascript: browseStdTable('PV03');",1,0,0],
["Voucher UDF 4","javascript: browseStdTable('PV04');",1,0,0],
["Voucher UDF 5","javascript: browseStdTable('PV05');",1,0,0]
]

//Inspection UDFs Menu for admin/systemtables/system_tables.jsp
Array18 = [
[125,		// menu width
135,		// left_position
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
right-to-left],
["Inspection UDF 1","javascript: browseStdTable('IP01');",1,0,0],
["Inspection UDF 2","javascript: browseStdTable('IP02');",1,0,0],
["Inspection UDF 3","javascript: browseStdTable('IP03');",1,0,0],
["Inspection UDF 4","javascript: browseStdTable('IP04');",1,0,0],
["Inspection UDF 5","javascript: browseStdTable('IP05');",1,0,0],
]
