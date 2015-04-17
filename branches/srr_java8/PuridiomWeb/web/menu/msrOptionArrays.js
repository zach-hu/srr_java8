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
var 	checkRequestExtractActive = false;


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

var viewType = "";
var displayViewType = "";
var reqNumber = "";
var reqType = "";
var reqStatus = "";
var reqLineCount = 0;
var reqInProgress = "";
var reqRejected = "";
var reqRecalled = "";
var reqForwarded = "";
var reqApproving = "";
var reqApproved = "";
var reqAmmended = "";
var rfqPriced = "";
var cancelStatus = "";
var templateStatus = "";
var invInProgress = "";
var invBackordered = "";
var reqReturning = "";
var reqReturned = "";
var recallAccess = "";
var cancelAccess = "";
var convertAccess = "";
var deleteAccess = "";
var userRejectAccess = "";
var uploadItemsAccess = "";
var uploadItemsRole = "";
var budgetActive = false;
var allowApprovalEscalation = false;
var oid = "";
var uid = "";
var isABuyer = "";

function findToPage()
{
  if (viewType == "WIZARD")
  {
  	return '/requests/req_review.jsp';
  }
  else
  {
  	return '/requests/req_summary.jsp';
  }
}

function createMsrOptionsMenu(originalArray) {
	var options = new Array();
	var i = 1;
	var toPage = '';

	options[0] = originalArray;

	if (reqNumber != "N/A" && reqType != "Q" && reqType != "C") {
		options[i] = new Array(saveas,"javascript: reqSaveAs(); void(0);",1,0,0);
		i++;
	}

	if(recallAccess == "true" && (reqStatus >= reqApproving && reqStatus < reqApproved) )
	{
		options[i] = new Array(recall,"javascript: if (verifyAction('Recall this Requisition?')) { doSubmit(findToPage(), 'RequisitionRecall;RequisitionRetrieve'); } void(0);",1,0,0);
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
	if (reqStatus == reqApproving) {
		options[i] = new Array("Resend Email To Current Approver","javascript: resendReqToApprover(); void(0);",1,0,0);
		i++;
	}
	if (reqNumber != "N/A") {
		options[i] = new Array(viewhistory,"javascript: viewHistory(); void(0);",1,0,0);
		i++;
		options[i] = new Array(printPdfLabel,"javascript: printPdf(); void(0);",1,0,0);
		i++;
	}
	options[i] = new Array(printpreview,"javascript: reqPreview(); void(0);",1,0,0);
	i++;


	return options;
}
