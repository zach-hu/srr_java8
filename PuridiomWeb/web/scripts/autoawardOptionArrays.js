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

Array77 = [
[150,		// menu width
0,		// left_position
0,		// top_position
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

var reqActive = '';
var reqTypeActive = '';

function createAutoawardMenu(originalArray) {
	var options = new Array();
	options[0] = originalArray;
	options[1] = new Array("Open Requisition","javascript: viewReq(reqActive,reqTypeActive); void(0);",1,0,0);
	options[2] = new Array("Award Requisition","javascript: awardReq(reqActive,reqTypeActive); void(0);",1,0,0);
	return options;
}

function setReqActive(icReqHeader,reqType) {
	reqActive = icReqHeader;
	reqTypeActive = reqType;
}

function awardReq(icReqHeader,reqType) {
	if (reqType!='P')
	{
		alert('Just Purchase Requests can be autoawarded');
		return;
	}
	if(frm.RequisitionHeader_icReqHeader)
	{
		frm.RequisitionHeader_icReqHeader.value = icReqHeader;
	}
	else
	{
	    var newInputField = "<input type='hidden' name='RequisitionHeader_icReqHeader' value='" + icReqHeader + "'>";
        setHiddenFields(newInputField);
    }
    if(frm.RequisitionLine_icReqHeader)
	{
		frm.RequisitionLine_icReqHeader.value = icReqHeader;
	}
	else
	{
	    var newInputField = "<input type='hidden' name='RequisitionLine_icReqHeader' value='" + icReqHeader + "'>";
        setHiddenFields(newInputField);
    }
	doSubmit('requests/req_autoaward_options.jsp', 'DoNothing;RequisitionLineRetrieveByHeader');
}