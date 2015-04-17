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

//Requisition action options - created onload
Array91 = [
[150,		// menu width
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

var recnumber = "";
var recType = "";
var recStatus = "";
var recLineCount = 0;
var recInProgress = "";
var recPendingFinalization = "";

var recallAccess = "";
var receiptMethod = "";

function findToPage()
{
  if (viewType == "WIZARD")
  {
  	return '/receipts/rec_review.jsp';
  }
  else
  {
  	return '/receipts/rec_summary.jsp';
  }
}

function createRecOptionsMenu(originalArray) {
	var options = new Array();
	var i = 1;
	var toPage = '';

	options[0] = originalArray;

	/*if (reqNumber != "N/A" && reqType != "Q" && reqType != "C") {
		if(oid=="VSE06P"){
			options[i] = new Array("Save As","javascript: reqSaveAsCheckAccount(); void(0);",1,0,0);
		}
		else{
			options[i] = new Array("Save As","javascript: reqSaveAs(); void(0);",1,0,0);
		}
		i++;
		if (reqNumber != "TEMPLATE")
		{
			options[i] = new Array("Save As Template","javascript: doSubmit('/requests/req_template_options.jsp', 'RequisitionHeaderRetrieveById'); void(0);", 1, 0, 0);
			i++;
		}
	}*/


	//if (reqStatus == reqInProgress || reqStatus == reqRejected || reqStatus == reqRecalled) {
	/*if (reqStatus == templateStatus && deleteAccess == "true")
	{
		options[i] = new Array("Delete","javascript: if (verifyAction('Delete this Requisition?')) { doSubmit('/menu/main_menu.jsp', 'RequisitionDelete'); } void(0);",1,0,0);
		i++;
	}
	if(oid == "WPC08P")
	{
		if(uid == "Y")
		{
			options[i] = new Array("Cancel","javascript: frm.reqaction.value='cancel'; doSubmit('/requests/req_cancel_close.jsp', 'RequisitionRetrieve'); void(0);",1,0,0);
			i++;
		}
		if ((uid != "Y") && (reqNumber != "N/A") && ((cancelAccessOwnReq == "true" && reqStatus <= reqApproved) || (cancelAccessAssBuy == "true" && reqStatus >= reqApproved && reqStatus < poAwarded)))
		{
			options[i] = new Array("Cancel","javascript: frm.reqaction.value='cancel'; doSubmit('/requests/req_cancel_close.jsp', 'RequisitionRetrieve'); void(0);",1,0,0);
			i++;
		}
	}*/
	/*if ((oid != "WPC08P") && (reqNumber != "N/A") && ((cancelAccessOwnReq == "true" && reqStatus <= reqApproved) || (cancelAccessAssBuy == "true" && reqStatus >= reqApproved && reqStatus < poAwarded)))
	{*/
		/*if (reqStatus <= reqApproved || reqStatus == rfqPriced || reqStatus == in	vInProgress || reqStatus == invBackordered) {
		options[i] = new Array("Cancel","javascript: if (verifyAction('Cancel this Requisition?')) { doSubmit(findToPage(), 'RequisitionCancel;RequisitionRetrieve'); } void(0);",1,0,0);
			i++;
		}*/
		/*options[i] = new Array("Cancel","javascript: frm.reqaction.value='cancel'; doSubmit('/requests/req_cancel_close.jsp', 'RequisitionRetrieve'); void(0);",1,0,0);
		i++;*/
		// VERSION WHEN THE RFQ AND PO LINES CANCELED TOO
		/*if (reqStatus >= reqApproving && reqStatus < cancelStatus) {
			options[i] = new Array("Cancel","javascript: if (verifyAction('Cancel this Requisition?')) { doSubmit(findToPage(), 'RequisitionCancelAll;RequisitionRetrieve'); } void(0);",1,0,0);
			i++;
		}*/
		/*else if(reqStatus == reqReturning)
		{
		 options[i] = new Array("Cancel","javascript: if (verifyAction('Cancel this Requisition?')) { doSubmit(findToPage(), 'ReturnRequisitionCancel;RequisitionRetrieve'); } void(0);",1,0,0);
		 i++;
		}*/
	/*}
	if(recallAccess == "true" && (reqStatus >= reqApproving && reqStatus < reqApproved) )
	{
		options[i] = new Array("Recall","javascript: if (verifyAction('Recall this Requisition?')) { doSubmit(findToPage(), 'RequisitionRecall;RequisitionRetrieve'); } void(0);",1,0,0);
		i++;
	}
	if(closeAccess == "true")
	{
		if (reqStatus >= reqAwarded && reqStatus < historyStatus) {
			options[i] = new Array("Close","javascript: frm.reqaction.value='close'; doSubmit('/requests/req_cancel_close.jsp', 'RequisitionRetrieve'); void(0);",1,0,0);
			i++;
		}
	}
	if(userRejectAccess == "true" && reqStatus == reqApproved)
	{
		options[i] = new Array("Reject","javascript: if (verifyAction('Reject this Requisition?')) { doSubmit('/requests/req_reject_by_buyer.jsp', 'DoNothing'); } void(0);",1,0,0);
		 i++;
		options[i] = new Array("Create PO","javascript: createPurchaseOrderFromReq(); void(0);",1,0,0);
		 i++;
	}
	if (budgetActive && (reqStatus == reqInProgress || reqStatus == reqRejected || reqStatus == reqRecalled)) {
		options[i] = new Array("Budget Check","javascript: getBudgetCheck('BUDGETCHECK','1','REQ','1'); void(0);",1,0,0);
		i++;
	}*/
	if((rcvAdjAccess >= 3 && (rcvByItemAccess >= 3 || rcvByEndUserAccess >= 3)) && receiptMethod == "FinalizeReceipt" && recStatus == recPendingFinalization)
	{
		options[i] = new Array(adjustReceipt,"javascript: adjustRec(); void(0);",1,0,0);
		i++;
	}
	if(rcvReturnAccess >= 3 && receiptMethod == "FinalizeReceipt" && recStatus == recPendingFinalization)
	{
		options[i] = new Array(returnReceivedItems,"javascript: returnRec(); void(0);",1,0,0);
		i++;
	}
	if(recallAccess == "true" && recnumber != "" && recnumber != "N/A" && recStatus == recInProgress)
	{
		options[i] = new Array(recall,"javascript: recallRec(); void(0);",1,0,0);
		i++;
	}
	if (recStatus == recInProgress || (receiptMethod == "FinalizeReceipt" && recStatus == recPendingFinalization))
	{
		options[i] = new Array(validate,"javascript: validateRec('VALIDATE'); void(0);",1,0,0);
		i++;
	}
	if (recnumber != "" && recnumber != "N/A")
	{
		options[i] = new Array(viewhistory,"javascript: viewHistory(); void(0);",1,0,0);
		i++;
		options[i] = new Array(printPdfLabel,"javascript: printPdf(); void(0);",1,0,0);
		i++;
		options[i] = new Array(printLabelPdf,"javascript: printLabels(); void(0);",1,0,0);
		i++;
	}
	/*if (reqType != "N")
	{
		if (oid == "HOY08P")
		{
		    options[i] = new Array("Approval List","javascript: getRoutingList('ROUTING'); void(0);",1,0,0);
		    i++;
		 }
		else
		{
		    options[i] = new Array("Routing List","javascript: getRoutingList('ROUTING'); void(0);",1,0,0);
		    i++;
		}
	}
	if (reqStatus == reqApproving) {
		options[i] = new Array("Resend Email To Current Approver","javascript: resendReqToApprover(); void(0);",1,0,0);
		i++;
	}
	if (reqStatus == reqApproving && allowApprovalEscalation) {
		options[i] = new Array("Escalate Approval","javascript: escalateApproval(); void(0);",1,0,0);
		i++;
	}
	if (reqNumber != "N/A") {
		options[i] = new Array("History","javascript: viewHistory(); void(0);",1,0,0);
		i++;
		options[i] = new Array(printPdfLabel,"javascript: printPdf(); void(0);",1,0,0);
		i++;
	}
	if (reqLineCount > 0 && reqType == "S" && reqStatus < reqForwarded) {
//		options[i] = new Array("Preview Split","javascript: unavailable(); void(0);",1,0,0);
//		i++;
	}
	if (reqStatus > reqInProgress && oid != "BLY07P" )
	{
		options[i] = new Array("Approval / Rejection Notes","javascript: viewAppNotes(); void(0);",1,0,0);
		i++;
	}
	options[i] = new Array("Print Preview","javascript: reqPreview(); void(0);",1,0,0);
	i++;

	if (convertAccess == "true")
	{
		if ( reqType == "N" && reqStatus < reqApproved )
		{
			options[i] = new Array("Convert to Purchase Req","javascript: convertToPurchaseReq(); void(0);",1,0,0);
			i++;
		}
	}
	if (convertAccess == "true")
	{
		if ( (reqStatus < reqForwarded) && (reqType == "P"||reqType == "E"||reqType == "N"||reqType == "S" ) && reqNumber != "N/A")
		{
			options[i] = new Array("Change Request Type","javascript: convertToOtherType(); void(0);",1,0,0);
			i++;
		}
	}

	if (reqStatus >= reqApproved)
	{
		options[i] = new Array("Procurement Remarks","javascript: doSubmit('/requests/req_buyer_remarks.jsp', 'RequisitionHeaderRetrieveById;HistoryLogRetrieveBuyerRemarks'); void(0);", 1, 0, 0);
		i++;
	}

	if (displayViewType == "Y")
	{
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
	}

    if (reqNumber != "N/A" && reqStatus == reqInProgress && uploadItemsRole!= "disabled" && uploadItemsAccess == "Y") {
 		options[i] = new Array("Upload Items","javascript: RfqReqPoAddItems(); void(0);",1,0,0);
		i++;
	}*/

	return options;
}
