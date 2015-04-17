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

//Recent Requisition action options - created onload
Array90 = [
[120,		// menu width
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
Array91 = [Array90[0],[]]
Array92 = [Array90[0],[]]
Array93 = [Array90[0],[]]
Array94 = [Array90[0],[]]
Array95 = [Array90[0],[]]
Array96 = [Array90[0],[]]
Array97 = [Array90[0],[]]
Array98 = [Array90[0],[]]
Array99 = [Array90[0],[]]

var reqInProgress = "";
var reqRejected = "";
var reqRecalled = "";
var reqForwarded = "";
var reqApproving = "";
var reqApproved = "";
var reqAmmended = "";
var rfqPriced = "";
var invInProgress = "";
var invBackordered = "";
var reqReturning = "";
var reqReturned = "";
var rfqInProgress = "";
var rfqOpenSolicitation = "";
var poInProgress =  "";
var poApproving = "";

function createRQMenu(originalArray, opc) {
	var options = new Array();
	var i = 1;

	options[0] = originalArray;
		if(opc==1) {
			if (repReqAccess > 2) {
			options[i] = new Array(request,"javascript: setReportsQueue('REPREQ');void(0);",1,0,0);
			i++;
			}
			if (repRfqAccess > 2) {
			options[i] = new Array(solicitation,"javascript: setReportsQueue('REPRFQ');void(0);",1,0,0);
			i++;
			}
			if (repPoAccess > 2) {
			options[i] = new Array(order,"javascript: setReportsQueue('REPPO');void(0);",1,0,0);
			i++;
			}
			if (repVndAccess > 2) {
			options[i] = new Array(supplier,"javascript: setReportsQueue('REPSUP');void(0);",1,0,0);
			i++;
			}
			if (repVchAccess > 0) {
				options[i] = new Array("Invoice", "javascript: setReportsQueue('REPVCH'); void(0);",1,0,0);
				i++;
			}
			if (repAdminAccess > 0) {
				options[i] = new Array("Administrative", "javascript: setReportsQueue('REPADM'); void(0);",1,0,0);
				i++;
			}
			if (rcvByPkgAccess > 0 || rcvByItemAccess > 0 || rcvFinalizeAccess > 0 || rcvAdjAccess > 0 || rcvReturnAccess > 0 || rcvHistoryAccess > 0) {
				options[i] = new Array("Receipts", "javascript: setReportsQueue('REPRCV'); void(0);",1,0,0);
				i++;
			}
			if (repInvAccess > 0) {
				options[i] = new Array("Inventory", "javascript: setReportsQueue('REPINV'); void(0);",1,0,0);
				i++;
			}
			if (repBdgAccess > 0) {
				options[i] = new Array("Budget", "javascript: setReportsQueue('REPBDG'); void(0);",1,0,0);
				i++;
			}
		}
		else if(opc==2) {
			options[i] = new Array(reporttoday,"javascript: viewReportQueueDueThisToday(); void(0);",1,0,0);
			i++;
			options[i] = new Array(reportweek,"javascript: viewReportQueueDueThisWeek(); void(0);",1,0,0);
			i++;
			options[i] = new Array(reportmonth,"javascript: viewReportQueueDueThisMonth(); void(0);",1,0,0);
			i++;
			options[i] = new Array(reportquarter,"javascript: viewReportQueueDueThisQuarter(); void(0);",1,0,0);
			i++;
			options[i] = new Array(reportyear,"javascript: viewReportQueueDueThisYear(); void(0);",1,0,0);
			i++;
		}
	return options;
}