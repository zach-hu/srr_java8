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

Array99 = [
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

var reqHeader = '';
var number = '';
var type = '';
var oid = "";
var uid = "";

function createTemplateReqOptionsMenu(originalArray) {
	var options = new Array();
	var i = 1;

	options[0] = originalArray;
	options[1] = new Array("Create Requisition", "javascript: createReqFromTemplate(reqHeader); void(0);",1,0,0);
	options[2] = new Array("Edit Template", "javascript: openReq(reqHeader); void(0);",1,0,0);
	options[3] = new Array("View Template PDF","javascript: viewReqPdf(reqHeader,number,type); void(0);",1,0,0);

	return options;
}

function Template(icReqHeader, reqNumber, reqType)
{
	reqHeader = icReqHeader;
	number = reqNumber;
	type = reqType;
}

function createReqFromTemplate(ic)
{
	var fiscalYear = frm.fiscalYearTemplate.value;
	if(frm.RequisitionHeader_icReqHeader)
	{
		frm.RequisitionHeader_icReqHeader.value = ic;
		var newInputField = "<input type='hidden' name='RequisitionHeader_fiscalYear' value='" + fiscalYear + "'>";
		setHiddenFields(newInputField);
	}
	else
	{
		var newInputField = "<input type='hidden' name='RequisitionHeader_icReqHeader' value='" + ic + "'>";
		newInputField = newInputField + "<input type='hidden' name='RequisitionHeader_fiscalYear' value='" + fiscalYear + "'>";
		setHiddenFields(newInputField);
	}
	frm.viewType.value = "WIZARD";
	doSubmit('/requests/req_review.jsp', 'RequisitionSaveas;RequisitionRetrieve');
}
function openReq(ic)
{
	if(frm.RequisitionHeader_icReqHeader)
	{
		frm.RequisitionHeader_icReqHeader.value = ic;
	}
	else
	{
		var newInputField = "<input type='hidden' name='RequisitionHeader_icReqHeader' value='" + ic + "'>";
		setHiddenFields(newInputField);
	}
   	frm.viewType.value = "WIZARD";
   	doSubmit('/requests/req_review.jsp', 'RequisitionRetrieve');
}
function viewReqPdf(ic, number, type) {
	popupParameters = "RequisitionHeader_icReqHeader=" + ic;
	popupParameters = popupParameters + ";uid=oid";
	popupParameters = popupParameters + ";oid=uid";
	popupParameters = popupParameters + ";viewNow=Y";
	doSubmitToLookup('', 'PrintPdf', 'width=775px', 'height=850px');
}