<script language='Javascript1.2' type="text/javascript">
<!--

	/*  Account UDFs  */
	var AC01 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC01", "Account UDF 01")%>";
	var AC02 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC02", "Account UDF 02")%>";
	var AC03 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC03", "Account UDF 02")%>";
	var AC04 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC04", "Account UDF 04")%>";
	var AC05 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC05", "Account UDF 05")%>";
	var AC06 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC06", "Account UDF 06")%>";
	var AC07 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC07", "Account UDF 07")%>";
	var AC08 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC08", "Account UDF 08")%>";
	var AC09 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC09", "Account UDF 09")%>";
	var AC10 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC10", "Account UDF 10")%>";
	var AC11 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC11", "Account UDF 11")%>";
	var AC12 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC12", "Account UDF 12")%>";
	var AC13 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC13", "Account UDF 13")%>";
	var AC14 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC14", "Account UDF 14")%>";
	var AC15 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC15", "Account UDF 15")%>";

	/*  Catalog UDFs */
	var CT01 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "CT01", "Catalog UDF 01")%>";
	var CT02 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "CT02", "Catalog UDF 02")%>";
	var CT03 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "CT03", "Catalog UDF 03")%>";
	var CT04 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "CT04", "Catalog UDF 04")%>";
	var CT05 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "CT05", "Catalog UDF 04")%>";

	/*  Line UDFs */
	var LN01 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "LN01", "Line UDF 01")%>";
	var LN02 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "LN02", "Line UDF 02")%>";
	var LN03 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "LN03", "Line UDF 03")%>";
	var LN04 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "LN04", "Line UDF 04")%>";
	var LN05 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "LN05", "Line UDF 05")%>";
	var LN06 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "LN06", "Line UDF 06")%>";
	var LN07 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "LN07", "Line UDF 07")%>";
	var LN08 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "LN08", "Line UDF 08")%>";
	var LN09 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "LN09", "Line UDF 09")%>";
	var LN10 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "LN10", "Line UDF 10")%>";

	/*  Order UDFs */
	var PO01 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO01", "PO UDF 01")%>";
	var PO02 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO02", "PO UDF 02")%>";
	var PO03 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO03", "PO UDF 03")%>";
	var PO04 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO04", "PO UDF 04")%>";
	var PO05 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO05", "PO UDF 05")%>";
	var PO06 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO06", "PO UDF 06")%>";
	var PO07 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO07", "PO UDF 07")%>";
	var PO08 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO08", "PO UDF 08")%>";
	var PO09 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO09", "PO UDF 09")%>";
	var PO10 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO10", "PO UDF 10")%>";
	var PO11 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO11", "PO UDF 11")%>";
	var PO12 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO12", "PO UDF 12")%>";
	var PO13 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO13", "PO UDF 13")%>";
	var PO14 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO14", "PO UDF 14")%>";
	var PO15 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO15", "PO UDF 15")%>";

	/*  Receiving UDFs  */
	var RC01 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RC01", "Receipt UDF 01")%>";
	var RC02 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RC02", "Receipt UDF 02")%>";
	var RC03 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RC03", "Receipt UDF 03")%>";
	var RC04 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RC04", "Receipt UDF 04")%>";
	var RC05 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RC05", "Receipt UDF 05")%>";

	/*  Requisition UDFs  */
	var RQ01 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ01", "Requisition UDF 01")%>";
	var RQ02 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ02", "Requisition UDF 02")%>";
	var RQ03 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ03", "Requisition UDF 03")%>";
	var RQ04 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ04", "Requisition UDF 04")%>";
	var RQ05 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ05", "Requisition UDF 05")%>";
	var RQ06 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ06", "Requisition UDF 06")%>";
	var RQ07 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ07", "Requisition UDF 07")%>";
	var RQ08 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ08", "Requisition UDF 08")%>";
	var RQ09 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ09", "Requisition UDF 09")%>";
	var RQ10 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ10", "Requisition UDF 10")%>";
	var RQ11 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ11", "Requisition UDF 11")%>";
	var RQ12 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ12", "Requisition UDF 12")%>";
	var RQ13 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ13", "Requisition UDF 13")%>";
	var RQ14 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ14", "Requisition UDF 14")%>";
	var RQ15 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ15", "Requisition UDF 15")%>";

	/*  Solicitation UDFs  */
	var RF01 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RF01", "Solicitation UDF 01")%>";
	var RF02 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RF02", "Solicitation UDF 02")%>";
	var RF03 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RF03", "Solicitation UDF 03")%>";
	var RF04 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RF04", "Solicitation UDF 04")%>";
	var RF05 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RF05", "Solicitation UDF 05")%>";
	var RF06 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RF06", "Solicitation UDF 06")%>";
	var RF07 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RF07", "Solicitation UDF 07")%>";
	var RF08 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RF08", "Solicitation UDF 08")%>";
	var RF09 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RF09", "Solicitation UDF 09")%>";
	var RF10 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RF10", "Solicitation UDF 10")%>";

	/*  Voucher UDFs  */
	var IV01 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IV01", "Invoice UDF 01")%>";
	var IV02 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IV02", "Invoice UDF 02")%>";
	var IV03 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IV03", "Invoice UDF 03")%>";
	var IV04 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IV04", "Invoice UDF 04")%>";
	var IV05 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IV05", "Invoice UDF 05")%>";
	var IV06 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IV06", "Invoice UDF 06")%>";
	var IV07 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IV07", "Invoice UDF 07")%>";
	var IV08 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IV08", "Invoice UDF 08")%>";
	var IV09 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IV09", "Invoice UDF 09")%>";
	var IV10 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IV10", "Invoice UDF 10")%>";

	/*  Asset Tracking UDFs  */
	var AUD1 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AUD1", "Asset UDF 1")%>";
	var AUD2 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AUD2", "Asset UDF 2")%>";
	var AUD3 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AUD3", "Asset UDF 3")%>";
	var AUD4 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AUD4", "Asset UDF 4")%>";
	var AUD5 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AUD5", "Asset UDF 5")%>";

	/*  Inventory Bin UDFs  */
	var BN01 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BN01", "Inventory Bin UDF 01")%>";
	var BN02 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BN02", "Inventory Bin UDF 02")%>";
	var BN03 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BN03", "Inventory Bin UDF 03")%>";
	var BN04 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BN04", "Inventory Bin UDF 04")%>";
	var BN05 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BN05", "Inventory Bin UDF 05")%>";

	/*  Inventory Form Tables  */
	var FART = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FART", "Form Artwork")%>";		//Form Artwork
	var FBND = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FBND", "Form Binding")%>";		//Form Binding
	var FBLK = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FBLK", "Form Blockout")%>";		//Form Blockout
	var FCLS = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FCLS", "Form Classes")%>";		//Form Classes
	var FCOL = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FCOL", "Form Colors")%>";		//Form Colors
	var FFLD = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FFLD", "Form Folds")%>";		//Form Folds
	var FFNT = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FFNT", "Form Fonts")%>";		//Form Fonts
	var FINK = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FINK", "Form Ink")%>";			//Form Ink
	var FPKG = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FPKG", "Form Packaging")%>";	//Form Packaging
	var FPAD = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FPAD", "Form Padding")%>";		//Form Padding
	var FPCO = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FPCO", "Form Paper Colors")%>";	//Form Paper Colors
	var FPPR = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FPPR", "Form Paper/Cover Stock")%>";	//Form Paper/Cover Stock
	var FGRD = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FGRD", "Form Paper Grades")%>";	//Form Paper Grades
	var FPRT = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FPRT", "Form Prints")%>";		//Form Prints
	var FSIZ = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FSIZ", "Form Sizes")%>";		//Form Sizes
	var FSP1 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FSP1", "Form Spec. UDF 01")%>";	//Form Spec. UDF 01
	var FSP2 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FSP2", "Form Spec. UDF 02")%>";	//Form Spec. UDF 02
	var FSP3 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FSP3", "Form Spec. UDF 03")%>";	//Form Spec. UDF 03
	var FTYP = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FTYP", "Form Types")%>";		//Form Types
	var FUF1 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FUF1", "Form UDF 01")%>";		//Form UDF 01
	var FUF2 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FUF2", "Form UDF 02")%>";		//Form UDF 02
	var FWGT = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FWGT", "Form Weight")%>";		//Form Weight
	var LOB  = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FWGT", "LOB Codes")%>";			//Form Weight
	var PPTP = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "PPTP", "Perforation Type")%>";	//Form Weight
	var FPTT = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "FPTT", "Turn Type")%>";			//Form Weight

	/*  Inventory UDFs  */
	var IN01 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IN01", "Inventory UDF 01")%>";
	var IN02 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IN02", "Inventory UDF 02")%>";
	var IN03 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IN03", "Inventory UDF 03")%>";
	var IN04 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IN04", "Inventory UDF 04")%>";
	var IN05 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IN05", "Inventory UDF 05")%>";

	/*  Inventory Location UDFs  */
	var IL01 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IL01", "Inventory Location UDF 01")%>";
	var IL02 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IL02", "Inventory Location UDF 02")%>";
	var IL03 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IL03", "Inventory Location UDF 03")%>";
	var IL04 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IL04", "Inventory Location UDF 04")%>";
	var IL05 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IL05", "Inventory Location UDF 05")%>";


	/* MRP Tables */
	var MTSK = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "MTSK", "")%>";
	var MMCH = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "MMCH", "")%>";

	/*  Supplier Compliance UDFs  */
	var SCR1 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "SCR1", "Supplier Compliance UDF 1")%>";
	var SCR2 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "SCR2", "Supplier Compliance UDF 2")%>";
	var SCR3 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "SCR3", "Supplier Compliance UDF 3")%>";
	var SCR4 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "SCR4", "Supplier Compliance UDF 4")%>";
	var SCR5 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "SCR5", "Supplier Compliance UDF 5")%>";

	/*  Supplier Contract UDFs  */
	var SCC1 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "SCC1", "Supplier Contract UDF 1")%>";
	var SCC2 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "SCC2", "Supplier Contract UDF 2")%>";
	var SCC3 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "SCC3", "Supplier Contract UDF 3")%>";
	var SCC4 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "SCC4", "Supplier Contract UDF 4")%>";
	var SCC5 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "SCC5", "Supplier Contract UDF 5")%>";

	/*  Supplier UDFs  */
	var VN01 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN01", "Supplier UDF 01")%>";
	var VN02 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN02", "Supplier UDF 02")%>";
	var VN03 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN03", "Supplier UDF 03")%>";
	var VN04 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN04", "Supplier UDF 04")%>";
	var VN05 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN05", "Supplier UDF 05")%>";
	var VN06 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN06", "Supplier UDF 06")%>";
	var VN07 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN07", "Supplier UDF 07")%>";
	var VN08 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN08", "Supplier UDF 08")%>";
	var VN09 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN09", "Supplier UDF 09")%>";
	var VN10 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN10", "Supplier UDF 10")%>";

	var commodityCodeTxt = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "commodity", "Commodity Codes")%>";

	var DFMT = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "DFMT", "Date Formats")%>";
	var LANG = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "LANG", "Languages")%>";
	var TMZN = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "TMZN", "Time Zones")%>";

	/* Inspection  UDFs  */
	var IP01 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IP01", "Inspection UDF 01")%>";
	var IP02 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IP02", "Inspection UDF 02")%>";
	var IP03 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IP03", "Inspection UDF 03")%>";
	var IP04 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IP04", "Inspection UDF 04")%>";
	var IP05 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "IP05", "Inspection UDF 05")%>";

	/* Puridiom Status and Doc types */
	var BUCK = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BUCK", "Budget Check")%>";
	var PERF = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "PERF", "PERF")%>";
	var RQTP = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RQTP", "Req Type")%>";
	var RFTP = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RFTP", "Rfq Type")%>";
	var POTP = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "POTP", "Po Type")%>";
	var RQSB = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RQSB", "RQSB")%>";
	var SQUE = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "SQUE", "SendQueue")%>";
	var STCD = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "STCD", "STCD")%>";
	var USRL = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "USRL", "USRL")%>";
	var VREG = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VREG", "VREG")%>";
	var VSTS = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VSTS", "VSTS")%>";
	
//-->
</script>