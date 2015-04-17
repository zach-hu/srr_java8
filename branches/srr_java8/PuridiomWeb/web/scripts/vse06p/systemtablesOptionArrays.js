
//var leftMenuOptions = ["0", "7","4","3"]
//var rightMenuOptions = ["1","5", "2","6"]
var leftMenuOptions = ["0"]
var rightMenuOptions = ["1","2", "4", "3"]

MenuArray0 = [
["General System Tables",	// menu_title
1,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
]]

MenuArray1 = [
["Asset Tables",		// menu_title
1,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
]]

MenuArray2 = [
["Inventory Tables",		// menu_title
1,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
]]

MenuArray3 = [
["Supplier Tables",		// menu_title
1,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
]]

MenuArray4 = [
["MRP Tables",		// menu_title
1,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
]]

// SUBMENUS
MenuArray101 = [
["AccountUdfs",		// submenu_id - must be unique
0,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
],
[AC01,"javascript: browseStdTable('AC01'); void(0);","","",""],
[AC02,"javascript: browseStdTable('AC02'); void(0);","","",""],
[AC03,"javascript: browseStdTable('AC03'); void(0);","","",""],
[AC04,"javascript: browseStdTable('AC04'); void(0);","","",""],
[AC05,"javascript: browseStdTable('AC05'); void(0);","","",""],
[AC06,"javascript: browseStdTable('AC06'); void(0);","","",""]
]

MenuArray103 = [
["LineUdfs",	// submenu_id - must be unique
0,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
],
[LN01,"javascript: browseStdTable('LN01'); void(0);","","",""],
[LN02,"javascript: browseStdTable('LN02'); void(0);","","",""],
[LN03,"javascript: browseStdTable('LN03'); void(0);","","",""],
[LN04,"javascript: browseStdTable('LN04'); void(0);","","",""],
[LN05,"javascript: browseStdTable('LN05'); void(0);","","",""]
]

MenuArray105 = [
["PurchaseOrderUdfs",	// submenu_id - must be unique
0,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
],
[PO01,"javascript: browseStdTable('PO01'); void(0);","","",""],
[PO02,"javascript: browseStdTable('PO02'); void(0);","","",""],
[PO03,"javascript: browseStdTable('PO03'); void(0);","","",""],
[PO04,"javascript: browseStdTable('PO04'); void(0);","","",""],
[PO05,"javascript: browseStdTable('PO05'); void(0);","","",""]
]

MenuArray107 = [
["ReceivingUdfs",	// submenu_id - must be unique
0,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
],
[RC01,"javascript: browseStdTable('RC01'); void(0);","","",""],
[RC02,"javascript: browseStdTable('RC02'); void(0);","","",""],
[RC03,"javascript: browseStdTable('RC03'); void(0);","","",""],
[RC04,"javascript: browseStdTable('RC04'); void(0);","","",""],
[RC05,"javascript: browseStdTable('RC05'); void(0);","","",""]
]

MenuArray109 = [
["RequisitionUdfs",	// submenu_id - must be unique
0,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
],
[RQ01,"javascript: browseStdTable('RQ01'); void(0);","","",""],
[RQ02,"javascript: browseStdTable('RQ02'); void(0);","","",""],
[RQ03,"javascript: browseStdTable('RQ03'); void(0);","","",""],
[RQ04,"javascript: browseStdTable('RQ04'); void(0);","","",""],
[RQ05,"javascript: browseStdTable('RQ05'); void(0);","","",""]
]

MenuArray111 = [	// originally these were RFQ UDFS, thats where the RF comes from
["SolicitationUdfs",	// submenu_id - must be unique
0,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
],
[RF01,"javascript: browseStdTable('RF01'); void(0);","","",""],
[RF02,"javascript: browseStdTable('RF02'); void(0);","","",""],
[RF03,"javascript: browseStdTable('RF03'); void(0);","","",""],
[RF04,"javascript: browseStdTable('RF04'); void(0);","","",""],
[RF05,"javascript: browseStdTable('RF05'); void(0);","","",""]
]

MenuArray113 = [
["VoucherUdfs",	// submenu_id - must be unique
0,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
],
[IV01,"javascript: browseStdTable('IV01'); void(0);","","",""],
[IV02,"javascript: browseStdTable('IV02'); void(0);","","",""],
[IV03,"javascript: browseStdTable('IV03'); void(0);","","",""],
[IV04,"javascript: browseStdTable('IV04'); void(0);","","",""],
[IV05,"javascript: browseStdTable('IV05'); void(0);","","",""],
[IV06,"javascript: browseStdTable('IV06'); void(0);","","",""],
[IV07,"javascript: browseStdTable('IV07'); void(0);","","",""],
[IV08,"javascript: browseStdTable('IV08'); void(0);","","",""],
[IV09,"javascript: browseStdTable('IV09'); void(0);","","",""],
[IV10,"javascript: browseStdTable('IV10'); void(0);","","",""]
]

MenuArray201 = [
["AssetTrackingUdfs",	// submenu_id - must be unique
0,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
],
[AUD1,"javascript: browseStdTable('AUD1'); void(0);","","",""],
[AUD2,"javascript: browseStdTable('AUD2'); void(0);","","",""],
[AUD3,"javascript: browseStdTable('AUD3'); void(0);","","",""],
[AUD4,"javascript: browseStdTable('AUD4'); void(0);","","",""],
[AUD5,"javascript: browseStdTable('AUD5'); void(0);","","",""]
]

MenuArray301 = [
["InventoryBinUdfs",		// submenu_id - must be unique
0,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
],
[BN01,"javascript: browseStdTable('BN01'); void(0);","","",""],
[BN02,"javascript: browseStdTable('BN02'); void(0);","","",""],
[BN03,"javascript: browseStdTable('BN03'); void(0);","","",""],
[BN04,"javascript: browseStdTable('BN04'); void(0);","","",""],
[BN05,"javascript: browseStdTable('BN05'); void(0);","","",""]
]

MenuArray303 = [
["InventoryFormTables",		// submenu_id - must be unique
0,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
],
[FART,"javascript: browseStdTable('FART'); void(0);","","",""],
[FBND,"javascript: browseStdTable('FBND'); void(0);","","",""],
[FBLK,"javascript: browseStdTable('FBLK'); void(0);","","",""],
[FCLS,"javascript: browseStdTable('FCLS'); void(0);","","",""],
[FCOL,"javascript: browseStdTable('FCOL'); void(0);","","",""],
[FFLD,"javascript: browseStdTable('FFLD'); void(0);","","",""],
[FFNT,"javascript: browseStdTable('FFNT'); void(0);","","",""],
[FINK,"javascript: browseStdTable('FINK'); void(0);","","",""],
[FPKG,"javascript: browseStdTable('FPKG'); void(0);","","",""],
[FPAD,"javascript: browseStdTable('FPAD'); void(0);","","",""],
[FPCO,"javascript: browseStdTable('FPCO'); void(0);","","",""],
[FPPR,"javascript: browseStdTable('FPPR'); void(0);","","",""],
[FGRD,"javascript: browseStdTable('FGRD'); void(0);","","",""],
[FPRT,"javascript: browseStdTable('FPRT'); void(0);","","",""],
[FSIZ,"javascript: browseStdTable('FSIZ'); void(0);","","",""],
[FSP1,"javascript: browseStdTable('FSP1'); void(0);","","",""],
[FSP2,"javascript: browseStdTable('FSP2'); void(0);","","",""],
[FSP3,"javascript: browseStdTable('FSP3'); void(0);","","",""],
[FTYP,"javascript: browseStdTable('FTYP'); void(0);","","",""],
[FUF1,"javascript: browseStdTable('FUF1'); void(0);","","",""],
[FUF2,"javascript: browseStdTable('FUF2'); void(0);","","",""],
[FWGT,"javascript: browseStdTable('FWGT'); void(0);","","",""],
[LOB,"javascript: browseStdTable('LOB'); void(0);","","",""],
[PPTP,"javascript: browseStdTable('PPTP'); void(0);","","",""],
[FPTT,"javascript: browseStdTable('FPTT'); void(0);","","",""]
]

MenuArray305 = [
["InventoryUdfs",		// submenu_id - must be unique
0,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
],
[IN01,"javascript: browseStdTable('IN01'); void(0);","","",""],
[IN01,"javascript: browseStdTable('IN02'); void(0);","","",""],
[IN03,"javascript: browseStdTable('IN03'); void(0);","","",""],
[IN04,"javascript: browseStdTable('IN04'); void(0);","","",""],
[IN05,"javascript: browseStdTable('IN05'); void(0);","","",""]
]

MenuArray401 = [
["SupplierComplianceUdfs",		// submenu_id - must be unique
0,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
],
[SCR1,"javascript: browseStdTable('SCR1'); void(0);","","",""],
[SCR2,"javascript: browseStdTable('SCR2'); void(0);","","",""],
[SCR3,"javascript: browseStdTable('SCR3'); void(0);","","",""],
[SCR4,"javascript: browseStdTable('SCR4'); void(0);","","",""],
[SCR5,"javascript: browseStdTable('SCR5'); void(0);","","",""]
]

MenuArray402 = [
["SupplierContractUdfs",		// submenu_id - must be unique
0				// display value (1-display, 0-hide)
],
[SCC1,"javascript: browseStdTable('SCC1'); void(0);","","",""],
[SCC2,"javascript: browseStdTable('SCC2'); void(0);","","",""],
[SCC3,"javascript: browseStdTable('SCC3'); void(0);","","",""],
[SCC4,"javascript: browseStdTable('SCC4'); void(0);","","",""],
[SCC5,"javascript: browseStdTable('SCC5'); void(0);","","",""]
]

MenuArray403 = [
["SupplierUdfs",		// submenu_id - must be unique
0				// display value (1-display, 0-hide)
],
[VN01,"javascript: browseStdTable('VN01'); void(0);","","",""],
[VN02,"javascript: browseStdTable('VN02'); void(0);","","",""],
[VN03,"javascript: browseStdTable('VN03'); void(0);","","",""]
]

MenuArray450 = [
["InspUdfs",	// submenu_id - must be unique
0,				// display value (1-display, 0-hide)
"mtitleopen",			// title/background class when options are displayed
"mtitleclosed",			// title/background class when options are hidden
],
[IP01,"javascript: browseStdTable('IP01'); void(0);","","",""],
[IP02,"javascript: browseStdTable('IP02'); void(0);","","",""],
[IP03,"javascript: browseStdTable('IP03'); void(0);","","",""],
[IP04,"javascript: browseStdTable('IP04'); void(0);","","",""],
[IP05,"javascript: browseStdTable('IP05'); void(0);","","",""]
]
