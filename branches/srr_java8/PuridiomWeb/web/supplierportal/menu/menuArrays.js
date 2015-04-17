/***************************************************************************
							Version 4 Menu ARRAYS
***************************************************************************/
//RfqBid Arrays
Array1 = [
[125,		// menu width
135,		// left_position
,		// top_position
"black",	// font_color
"white",	// mouseover_font_color
"white",	// background_color
"#004080",	// mouseover_background_color
"black",	// border_color
"black",	// separator_color
0,		// top_is_permanent
0,		// top_is_horizontal
0,		// tree_is_horizontal
1,		// position_under
1,		// top_more_images_visible
1,		// tree_more_images_visible
"null",		// evaluate_upon_tree_show
"null",		// evaluate_upon_tree_hide
],		//right-to-left
["No Bid","javascript: setBidCode('NB');",1,0,0],
["No Charge","javascript: setBidCode('NC');",1,0,0],
["Sealed","javascript: setBidCode('SE');",1,0,0],
["See Notes","javascript: setBidCode('SN');",1,0,0],
["Not Separately Priced","javascript: setBidCode('NSP');",1,0,0]
]

//Requisition Module
Array50 = [
[97,		// menu width
.1,		// left_position
44,		// top_position
"black",	// font_color
"#FFFFFF",	// mouseover_font_color
"#FFFFFF",	// background_color
"#FF6666",	// mouseover_background_color
"#9933CC",	// border_color
"#9933CC",	// separator_color
0,		// top_is_permanent
0,		// top_is_horizontal
0,		// tree_is_horizontal
1,		// position_under
1,		// top_more_images_visible
1,		// tree_more_images_visible
"null",		// evaluate_upon_tree_show
"null",		// evaluate_upon_tree_hide
],		//right-to-left
["Create New","javascript: doSubmit('/requests/req_select_type.jsp', 'DoNothing'); void(0);",1,0,0],
["Browse","javascript: browseFilter('requisitionheader'); void(0);",1,0,0]
]


//Solicitation Module
Array51 = [
[97,
98,
44,
"black",
"#FFFFFF",
"#FFFFFF",
"#FF6666",
"#9933CC",
"#9933CC",
0,
0,
0,
1,
1,
1,
"null",
"null",
],
["Create New","javascript: createSolicitation(); void(0);",1,0,0],
//["Browse","javascript: doSubmit('/browse/browse_options.jsp?tb=rfq_header', 'DoNothing'); void(0);",1,0,0]
//["Browse","javascript: doSubmit('/rfq/rfq_req_search.jsp', 'DoNothing'); void(0);",1,0,0]
["Browse","javascript: browseFilter('rfqheader');void(0);",1,0,0]
]


//Purchase Order Module
Array52 = [
[97,
195,
44,
"black",
"#FFFFFF",
"#FFFFFF",
"#FF6666",
"#9933CC",
"#9933CC",
0,
0,
0,
1,
1,
1,
"null",
"null",
],
["Create New","javascript: doSubmit('/orders/po_select_type.jsp', 'DoNothing'); void(0);",1,0,0],
["Browse","javascript:browseFilter('poheader');void(0);",1,0,0]
]


//End User Receipts
Array53 = [
[110,
292,
44,
"black",
"#FFFFFF",
"#FFFFFF",
"#FF6666",
"#9933CC",
"#9933CC",
0,
0,
0,
1,
1,
1,
"null",
"null",
],
["End User Receipts","javascript: doSubmit('/rec/end_receipt_retrieve.jsp', 'DoNothing'); void(0);",1,0,0]
]


//Receipts Module
Array54 = [
[97,
389,
44,
"black",
"#FFFFFF",
"#FFFFFF",
"#FF6666",
"#9933CC",
"#9933CC",
0,
0,
0,
1,
1,
1,
"null",
"null",
],
["Create New","javascript: doSubmit('/rec/createReceipt.jsp', 'DoNothing'); void(0);",1,0,0],
["Browse","javascript: doSubmit('/browse/browse_options.jsp?tb=rc', 'DoNothing'); void(0);",1,0,0]
]


//Inventory Module
Array55 = [
[97,
487,
44,
"black",
"#FFFFFF",
"#FFFFFF",
"#FF6666",
"#9933CC",
"#9933CC",
0,
0,
0,
1,
1,
1,
"null",
"null",
],
["Administrative","javascript: doSubmit('/inventory/inv_menu.jsp', 'DoNothing'); void(0);",1,0,0],
["Create New","javascript: doSubmit('/inventory/dsb_select_type.jsp', 'DoNothing'); void(0);",1,0,0],
["Browse","javascript: doSubmit('/inventory/dsb_browse.jsp', 'DisbHeaderRetrieveAll'); void(0);",1,0,0]
]


//Reports
Array56 = [
[98,		// menu width
582,		// left_position
44,		// top_position
"black",
"#FFFFFF",
"#FFFFFF",
"#FF6666",
"#9933CC",
"#9933CC",
0,
0,
0,
1,
1,
1,
"null",
"null",
],
["Administrative","javascript: setReports('ADMREP'); void(0);",1,0,0],
["Requisition","javascript: setReports('REQREP'); void(0);",1,0,0],
["Solicitation","javascript: setReports('RFQREP'); void(0);",1,0,0],
["PO","javascript: setReports('POREP'); void(0);",1,0,0],
["Receipt","javascript: setReports('RECREP'); void(0);",1,0,0],
["Voucher","javascript: setReports('VCHREP'); void(0);",1,0,0],
["Inventory","javascript: setReports('INVREP'); void(0);",1,0,0]
]
