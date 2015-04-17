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

//Solicitation action options - created onload
Array91 = [
[150,		// menu width
772,		// left_position
162,		// top_position
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
var rfqNumber = "";
var rfqStatus = "";
var rfqVendorCount = 0;
var rfqInProgress = "";
var rfqPurchasing = "";
var uploadItemsAccess = "";
var uploadItemsRole = "";

function findToPage()
{
  if (viewType == "WIZARD")
  {
  	return '/rfq/rfq_review.jsp';
  }
  else
  {
  	return '/rfq/rfq_summary.jsp';
  }
}

function createRfqOptionsMenu(originalArray) {
	var options = new Array();
	var i = 1;

	options[0] = originalArray;

	if (rfqNumber != "N/A" && rfqAccess >= 3) {
		options[i] = new Array("Save As","javascript: rfqSaveAs(); void(0);",1,0,0);
		i++;
	}
	/*
	if (rfqStatus == rfqInProgress && rfqAccess == 99) {
		options[i] = new Array("Delete","javascript: if (verifyAction('Delete this Solicitation?')) { doSubmit('/menu/main_menu.jsp', 'RfqDelete'); }  void(0);",1,0,0);
		i++;
	} else
	*/
	if (rfqStatus <= rfqPurchasing && rfqAccess >= 2) {
		options[i] = new Array("Cancel","javascript: if (verifyAction('Cancel this Solicitation?')) { doSubmit(findToPage(), 'RfqCancel;RfqRetrieve'); } void(0);",1,0,0);
		i++;
	}
	if (rfqNumber != "N/A") {
		options[i] = new Array("History","javascript: viewHistory(); void(0);",1,0,0);
		i++;
		options[i] = new Array(printPdfLabel,"javascript: printPdf(); void(0);",1,0,0);
		i++;
	}
	if (rfqStatus < rfqPurchasing) {
		options[i] = new Array("Validate","javascript: validateRfq('VALIDATE'); void(0);",1,0,0);
		i++;
	}
	if (rfqStatus >= rfqOpenSolicitation) {
		options[i] = new Array(rountinglist,"javascript: getRoutingList('ROUTING'); void(0);",1,0,0);
		i++;
	}
	if (rfqVendorCount > 0) {
		//options[i] = new Array("Bid Worksheet", "javascript: printWorkSheetPdf(); void(0);",1,0,0);
		//i++;
		options[i] = new Array("Intent To Award", "javascript: printIntentAwardPdf(); void(0);",1,0,0);
		i++;
	}
	if ((rfqStatus == rfqOpenSolicitation) ||  (rfqStatus == rfqPurchasing)) {
		options[i] = new Array("Amendment", "javascript: createAmendment(); void(0);",1,0,0);
		i++;
	}
	if ((rfqStatus == rfqOpenSolicitation) && markBidsReceived == 'Y' ) {
		options[i] = new Array("Mark as Bids Received", "javascript: bidsReceived(); void(0);",1,0,0);
		i++;
	}
	options[i] = new Array("Print Preview","javascript: rfqPreview(); void(0);",1,0,0);
	i++;
	options[i] = new Array("Procurement Remarks","javascript: doSubmit('/rfq/rfq_buyer_remarks.jsp', 'RfqRetrieveBuyerRemarks'); void(0);", 1, 0, 0);
	i++;
	/*if (viewType == "WIZARD")
	{
		options[i] = new Array("Switch to Classic","javascript: switchView(); void(0);",1,0,0);
		i++;
	}
	else
	{
		options[i] = new Array("Switch to Wizard","javascript: switchView(); void(0);",1,0,0);
		i++;
	}*/

	if (rfqNumber != "N/A" && rfqStatus == rfqInProgress && uploadItemsRole!= "disabled" && uploadItemsAccess == "Y" && rfqAccess >= 2) {
 		options[i] = new Array("Upload Items","javascript: RfqReqPoAddItems(); void(0);",1,0,0);
		i++;
	}

	return options;
}
