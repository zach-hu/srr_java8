	var browser = browserCheck();

	/*	General System Tables*/
	var accountAllocationsAccess = 0;
	var accountUdfsAccess = 0;
	var addressesAccess = 0;
	var insCategoryLevelAccess = 0;
	var bidResponseCodesAccess = 0;
	var bidWaiverCodesAccess = 0;
	var buyerRemarksAccess = 0;
	var carriersAccess = 0;
	var catalogUdfsAccess = 0;
	var checklistEntryAccess = 0;
	var commentsAccess = 0;
	var commodityCodesAccess = 0;
	var currencyCodesAccess = 0;
	var dateFormatAccess = 0;
	var countryAccess = 0;
	var departmentsAccess = 0;
	var dispositionCodesAccess = 0;
	var fiscalYearAccess = 0;
	var fobCodesAccess = 0;
	var formPrintsAccess = 0;
	var inspectionCodesAccess = 0;
	var inspectionCriteriaAccess = 0;
	var inspectionUdfsAccess = 0;
	var languagesAccess = 0;
	var lineUdfsAccess = 0;
	var paymentTermsAccess = 0;
	var perforationTypeAccess = 0;
	var priorityCodesAccess = 0;
	var procurementLevelAccess = 0;
	var productCodesAccess = 0;
	var pCardCodesAccess = 0;
	var purchaseOrderUdfsAccess = 0;
	var questionsAccess = 0;
	var reasonForSavingsAccess = 0;
	var receivingUdfsAccess = 0;
	var rejectionCodesAccess = 0;
	var requisitionUdfsAccess = 0;
	var shipViaCodesAccess = 0;
	var solicitationUdfsAccess = 0;
	var stateProvinceCodesAccess =0;
	var taxCodesAccess = 0;
	var timeZonesAccess = 0;
	var transactionCodesAccess = 0;
	var turnTypeAccess = 0;
	var unitsOfMeasureAccess = 0;
	var userTypesAccess = 0;
	var voucherReasonCodesAccess = 0;
	var voucherUdfsAccess = 0;
	var xrefComboAccess = 0;
	var reviewFinalizeAccess = 0;
	var reviewFinalizeEnabled = 'N';

	/*	Asset Tables	*/
	var buildingCodesAccess = 0;
	var depreciationMethodsAccess = 0;
	var facilityCodesAccess = 0;
	var nameCodesAccess = 0;
	var ownerCodesAccess = 0;
	var purchaseSuppliersAccess = 0;
	var securityCodesAccess = 0;
	var serviceContractAccess = 0;
	var trackingUdfsAccess = 0;
	var upgradeRequirementsAccess = 0;

	/*	Inventory Tables	*/
	var binUdfsAccess = 0;
	var formTablesAccess = 0;
	var itemCatalogsAccess = 0;
	var itemManagedByAccess = 0;
	var reasonCodesAccess = 0;
	var inventoryUdfsAccess = 0;
	var inventoryLocationUdfsAccess = 0;

	var workCenterAccess = 0;
	var machineAccess = 0;
	var routingTaskAccess = 0;
	var routingMethodAccess = 0;
	var routingStageAccess=0;

	/*	Supplier Tables	*/
	var complianceUdfsAccess = 0;
	var contractUdfsAccess = 0;
	var insuranceStatusAccess = 0;
	var insuranceLiabilityCoverageAccess = 0;
	var certificationTypesAccess = 0;
	var ownershipTypesAccess = 0;
	var diverseCertOrgsAccess = 0;
	var supplierTypesAccess = 0;
	var supplierUdfsAccess = 0;
	
	/* Puridiom Status and Document Types*/
	var purstatusanddocstatus = 0;

	function browserCheck() {
		var bw = "";

		if (navigator.appName == "Netscape") {
			bw = "NS";
			if (navigator.appVersion.charAt(0) == "5") {
				bw = "NS6";
			}
		}
		else if (navigator.appVersion.indexOf("MSIE") != -1) {
			bw = "IE";
		}
		return bw;
	}

	function thisLoad() {
		var menuGenerated = false;

		for (x=0; x < leftMenuOptions.length; x++) {
			if (generateMenu(leftMenuOptions[x], "leftMenu")) {
				menuGenerated = true;
			}
		}
		for (x=0; x < rightMenuOptions.length; x++) {
			if (menuGenerated) {
				generateMenu(rightMenuOptions[x], "rightMenu");
			} else {
				generateMenu(rightMenuOptions[x], "leftMenu");
			}
		}

		f_StartIt();
	}

	function generateMenu(x, position) {
		if (!validateArray("MenuArray" + x)) return false;

		generateMenuTable(x, position);
		generateMenuTitle(x);
		generateMenuOptions(x);

		return true;
	}

	function validateArray(arrayname) {
		return ((typeof eval("window." + arrayname) == "object") && (eval(arrayname).length > 1))
	}

	function generateMenuTable(x, position) {
		if (!validateArray("MenuArray" + x)) return false;
		var currentArray = eval("MenuArray" + x);
		var myMenuCell = document.getElementById(position);

		var myParentTableHTML = "<table id='parentTable" + x + "' border=0 cellspacing=1 cellpadding=0 width=100%>";
		var myMenuTitleTableHTML = "<table id='menuTitle" + x + "' border=0 cellspacing=0 cellpadding=0 width=100%></table>";
		var myMenuOptionsTableHTML = "<table id='menuOptions" + x + "' border=0 cellspacing=0 cellpadding=2 width=100%></table>";

		var myTableRow1HTML = "<tr><td style:'width=100%; height=18px; align=center;'>" + myMenuTitleTableHTML + "</td></tr>"
		var myTableRow2HTML = "<tr><td>" + myMenuOptionsTableHTML + "</td></tr>"
		var myTableHTML = myParentTableHTML + myTableRow1HTML + myTableRow2HTML + "</table><br>";

		myMenuCell.innerHTML = myMenuCell.innerHTML + myTableHTML;
	}

	function generateMenuTitle(x) {
		if (!validateArray("MenuArray" + x)) return false;

		var currentArray = eval("MenuArray" + x);
		var myParent = document.getElementById("parentTable" + x);
		var myTable = document.getElementById("menuTitle" + x);
		var myTbody;
		var myRow;
		var myCell;
		var myImg;
		var myClass;

		if (currentArray[0][1] == 1) {
			myClass = currentArray[0][2];
			myImg = "<img id='menuImage" + x + "' src='" + contextPath + "/images/arrows_up.gif' border=0>";
		}
		else {
			myClass = currentArray[0][3];

			myImg = "<img id='menuImage" + x + "' src='" + contextPath + "/images/arrows_down.gif' border=0>";
		}

		myParent.className = myClass;
		myTable.className = myClass;

		if (browser == "NS6") {
			myTbody = document.createElement("TBODY");
  	  		myTable.appendChild(myTbody);

			myTbody.className = myClass;

			myRow = document.createElement("TR");
			myTbody.appendChild(myRow);

			myRow.className = myClass;

			myCell = document.createElement("TD");
			myRow.appendChild(myCell);

			myCell.style.width = "90%";
			myCell.align = "center";
			myCell.className = myClass;
			myCell.innerHTML = currentArray[0][0];

			myCell = document.createElement("TD");
			myRow.appendChild(myCell);

			myCell.align = "right";
			myCell.style.width = "10%";
			myCell.className = myClass;
			myCell.innerHTML = "<a href='javascript: toggleDisplay(" + x + "); void(0);'>" + myImg + "</a>";
		}
		else {
			myRow = myTable.insertRow();

			myRow.className = myClass;

			myCell = myRow.insertCell();
			myCell.style.width = "90%";
			myCell.align = "center";
			myCell.className = myClass;
			myCell.innerText = currentArray[0][0];

			myCell = myRow.insertCell();
			myCell.align = "right";
			myCell.style.width = "10%";
			myCell.className = myClass;
			myCell.innerHTML = "<a href='javascript: toggleDisplay(" + x + "); void(0);'>" + myImg + "</a>";
		}
	}

	function generateMenuOptions(x) {
		if (browser == "NS" || browser == "NS6") {
			generateMenuOptionsNS(x);

		}
		else {
			var currentArray = eval("MenuArray" + x);
			var myTable = document.getElementById("menuOptions" + x);
			var myRow;
			var myCell;
			var myHTML = "";

			myTable.className = "moption";

			for (i=1; i < currentArray.length; i++) {
				myRow = myTable.insertRow();
				myRow.className = "moption";

				myCell = myRow.insertCell();
				myCell.style.width = "100%";
				myCell.className = "moption";

				if (currentArray[i][1].length > 0) {
					myHTML = "<a href=" + '"' + currentArray[i][1] + '"' + ">" + currentArray[i][0] + "</a>";
				}
				else {
					myHTML = currentArray[i][0];
				}

				myCell.innerHTML = currentArray[i][2] + myHTML + currentArray[i][3];

				if (currentArray[i].length > 5) {
					submenuArray = currentArray[i][5];
					generateSubmenuOptions(submenuArray, myTable);
				}
			}
		}
	}

function generateMenuOptionsNS(x) {
		var currentArray = eval("MenuArray" + x);
		var myTable = document.getElementById("menuOptions" + x);
		var myTbody;
		var myRow;
		var myCell;
		var myHTML = "";

		myTable.className = "moption";

		myTbody = document.createElement("TBODY");
		myTable.appendChild(myTbody);

		for (i=1; i < currentArray.length; i++) {

			myRow = document.createElement("TR");
			myRow.className = "moption";
			myTbody.appendChild(myRow);

			myCell = document.createElement("TD");
			myCell.style.width = "100%";
			myCell.className = "moption";
			myRow.appendChild(myCell);

			if (currentArray[i][1].length > 0) {
				myHTML = "<a href=" + '"' + currentArray[i][1] + '"' + ">" + currentArray[i][0] + "</a>";
			}
			else {
				myHTML = currentArray[i][0];
			}

			myCell.innerHTML = currentArray[i][2] + myHTML + currentArray[i][3];

			if (currentArray[i].length > 5) {
				submenuArray = currentArray[i][5];
				generateSubmenuOptionsNS(submenuArray, myTbody);
			}
		}
	}


	function generateSubmenuOptions(submenuArray, parentTable) {
		if ((typeof submenuArray == "object") && (submenuArray.length > 1)) {
			var submenuTable = document.createElement("TABLE");
			submenuTable.border=0;
			submenuTable.style.width = "95%";

			for (isub=1; isub < submenuArray.length; isub++) {
				mySubRow = submenuTable.insertRow();
				mySubRow.className = "moption";

				mySubCell = mySubRow.insertCell();
				mySubCell.style.width = "100%";
				mySubCell.className = "moption";

				if (submenuArray[isub][1].length > 0) {
					myHTML = "<a href=" + '"' + submenuArray[isub][1] + '"' + ">" + submenuArray[isub][0] + "</a>";
				}
				else {
					myHTML = submenuArray[isub][0];
				}
				mySubCell.innerHTML = submenuArray[isub][2] + myHTML + submenuArray[isub][3];
			}
			var myRow = parentTable.insertRow();
			myRow.className = "moption";

			var myCell = myRow.insertCell();
			myCell.style.width = "100%";
			myCell.className = "moption";
			myCell.align = "right";
			myCell.id = submenuArray[0][0];

			if (submenuArray[0][1] == 0) {
				myCell.style.visibility = "hidden";
				myCell.style.display = "none";
			}

			myCell.appendChild(submenuTable);
		}
	}



	function generateSubmenuOptionsNS(submenuArray, parentTbody) {
		if ((typeof submenuArray == "object") && (submenuArray.length > 1)) {
			var submenuTable;
			var submenuTbody;

			var mySubHTML = "";
			var myRow;
			var myCell;

			myRow = document.createElement("TR");
			myRow.className = "moption";
			parentTbody.appendChild(myRow);

			myCell = document.createElement("TD");
			myCell.style.width = "100%";
			myCell.className = "moption";
			myCell.align = "right";
			myCell.id = submenuArray[0][0];
			myRow.appendChild(myCell);

			submenuTable = document.createElement("TABLE");
			submenuTable.border=0;
			submenuTable.style.width = "95%";

			submenuTbody = document.createElement("TBODY");
			submenuTbody.className = "moption";
			submenuTbody.id = submenuArray[0][0];
			submenuTable.appendChild(submenuTbody);



			for (isub=1; isub < submenuArray.length; isub++) {

				var mySubRow = document.createElement("TR");
				mySubRow.className = "moption";
				submenuTbody.appendChild(mySubRow);

				var mySubCell = document.createElement("TD");
				mySubCell.style.width = "100%";
				mySubCell.className = "moption";
				mySubRow.appendChild(mySubCell);

				if (submenuArray[0][1] == 0) {
					myCell.style.visibility = "hidden";
					myCell.style.display = "none";
				}
				myCell.appendChild(submenuTable);

				if (submenuArray[isub][1].length > 0) {
					mySubHTML = "<a href=" + '"' + submenuArray[isub][1] + '"' + ">" + submenuArray[isub][0] + "</a>";
				}
				else {
					mySubHTML = submenuArray[isub][0];
				}

				mySubCell.innerHTML = submenuArray[isub][2] + mySubHTML + submenuArray[isub][3];
			}
		}
	}

	function hideMenu(x) {
		if (!validateArray("MenuArray" + x)) return false;

		var currentArray = eval("MenuArray" + x);
		var myParentTable = document.getElementById("parentTable" + x)
		var myTitleTable = document.getElementById("menuTitle" + x)
		var myTable = document.getElementById("menuOptions" + x);
		var myClass = currentArray[0][3];

		myParentTable.className = myClass;
		myTitleTable.className = myClass;

		if ((browser == "NS" || browser == "NS6") && myTable.hasChildNodes()) {
			var tBody;
			var row;
			var cell;

			for (var i = myTitleTable.childNodes.length - 1; i >= 0 ; i--) {
				if (myTitleTable.childNodes[i].nodeName == 'TBODY') {

				    tBody = myTitleTable.childNodes[i];
				    for(var j = tBody.childNodes.length - 1; j>=0; j--){
				      	if(tBody.childNodes[j].nodeName == 'TR'){
							row = tBody.childNodes[j];
							row.className = myClass;
						}

						for (var ic = row.childNodes.length - 1; ic >= 0 ; ic--) {
							cell = row.childNodes[ic];
							cell.className = myClass;
						}
					}
				}
			}

			for (var i = myTable.childNodes.length - 1; i >= 0 ; i--) {
				if (myTable.childNodes[i].nodeName == 'TBODY') {

					tBody = myTable.childNodes[i];
						for (var j = tBody.childNodes.length - 1; j>=0; j--){
						 if(tBody.childNodes[j].nodeName == 'TR'){
							row = tBody.childNodes[j];
							tBody.removeChild(row);
						}
					}
				}
			}
		}
		else {
			for (var i = myTitleTable.rows.length - 1; i >= 0 ; i--) {
				row = myTitleTable.rows(i);
				row.className = myClass;

				for (var ic = row.cells.length - 1; ic >= 0 ; ic--) {
					cell = row.cells(ic);
					cell.className = myClass;
				}
			}

			for (i = myTable.rows.length - 1; i >= 0; i--) {
				myTable.deleteRow(0);
			}
		}

		currentArray[0][1] = 0;
	}

	function displayMenu(x) {
		if (!validateArray("MenuArray" + x)) return false;

		var currentArray = eval("MenuArray" + x);
		var myClass = currentArray[0][2];
		var myParentTable = document.getElementById("parentTable" + x)
		var myTitleTable = document.getElementById("menuTitle" + x)
		var myTable = document.getElementById("menuOptions" + x);

		myParentTable.className = myClass;
		myTitleTable.className = myClass;

		if ((browser == "NS" || browser == "NS6") && myTable.hasChildNodes()) {
			var row;

			for (var i = myTitleTable.childNodes.length - 1; i >= 0 ; i--) {
				if (myTitleTable.childNodes[i].nodeName == 'TBODY') {

				    tBody = myTitleTable.childNodes[i];
				    for(var j = tBody.childNodes.length - 1; j>=0; j--){
				      	if(tBody.childNodes[j].nodeName == 'TR'){
							row = tBody.childNodes[j];
							row.className = myClass;
						}

						for (var ic = row.childNodes.length - 1; ic >= 0 ; ic--) {
							cell = row.childNodes[ic];
							cell.className = myClass;
						}
					}
				}
			}
		}
		else {
			for (var i = myTitleTable.rows.length - 1; i >= 0 ; i--) {
				row = myTitleTable.rows(i);
				row.className = myClass;

				for (var ic = row.cells.length - 1; ic >= 0 ; ic--) {
					cell = row.cells(ic);
					cell.className = myClass;
				}
			}
		}

		generateMenuOptions(x);
		currentArray[0][1] = 1;
	}

	function toggleDisplay(x) {
		if (!validateArray("MenuArray" + x)) return false;

		var currentArray = eval("MenuArray" + x);
		var img = document.getElementById("menuImage" + x);

		if (currentArray[0][1] == 1) {
			img.src = contextPath + "/images/arrows_down.gif";

			hideMenu(x);

		}
		else {
			img.src = contextPath + "/images/arrows_up.gif";

			displayMenu(x);
		}
	}

	function createGeneralSystemTables(originalArray) {
		var options = new Array();
		var i = 1;

		options[0] = originalArray;

		if (accountAllocationsAccess > 0)
		{
			options[i] = new Array("Account Allocations","javascript: browse('stdaccount-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (accountUdfsAccess > 0)
		{
			options[i] = new Array("Account UDFs","javascript: toggleSubmenuDisplay(101); void(0);","<font class=raquo>&raquo;</font>","","",MenuArray101);
			i++;
		}
		if (addressesAccess > 0)
		{
			options[i] = new Array("Addresses","javascript: browse('address'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (lineUdfsAccess > 0)
		{
			options[i] = new Array("Asset","javascript: browseStdTable('ASSET'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (bidResponseCodesAccess > 0)
		{
			options[i] = new Array("Bid Response Codes","javascript: browseStdTable('RESP'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (bidWaiverCodesAccess > 0)
		{
			options[i] = new Array("Bid Waiver Codes","javascript: browseStdTable('BIDW'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (buyerRemarksAccess > 0)
		{
			options[i] = new Array("Buyer Remarks","javascript: browseStdTable('BYRM'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (carriersAccess > 0)
		{
			options[i] = new Array("Carriers","javascript: browseStdTable('CARR'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (catalogUdfsAccess > 0)
		{
			options[i] = new Array("Catalogs","javascript: toggleSubmenuDisplay(102); void(0);","<font class=raquo>&raquo;</font>","","",MenuArray102);
			i++;
		}
		if (checklistEntryAccess > 0)
		{
			options[i] = new Array("Checklist Entries","javascript: browse('checklistentry-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (commentsAccess > 0)
		{
			options[i] = new Array("Comments (Standard)","javascript: browse('stdcomment-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (commodityCodesAccess > 0)
		{
			options[i] = new Array(commodityCodeTxt,"javascript: browseCommodityAdmin(); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (countryAccess > 0)
		{
			options[i] = new Array("Countries","javascript: browse('country-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (currencyCodesAccess > 0)
		{
			options[i] = new Array("Currency Codes","javascript: browse('currcode-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (dateFormatsAccess > 0)
		{
			options[i] = new Array(DFMT,"javascript: browseStdTable('DFMT'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (departmentsAccess > 0)
		{
			if(oid == "BSC04P"){
				options[i] = new Array("BBUE","javascript: browse('department-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
				i++;
			}
			else if(oid == "MSG07P"){
				options[i] = new Array("Divisions","javascript: browse('department-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
				i++;
			}
			else if(oid == "HOY08P"){
				options[i] = new Array("Labs","javascript: browse('department-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
				i++;
			}
			else if(oid == "BLY07P"){
				options[i] = new Array("Cost Center","javascript: browse('department-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
				i++;
			}
			else{
				options[i] = new Array("Departments","javascript: browse('department-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
				i++;
			}
		}
		if (dispositionCodesAccess > 0)
		{
			options[i] = new Array("Disposition Codes","javascript: browseStdTable('DISP'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (fiscalYearAccess > 0)
		{
			options[i] = new Array("Fiscal Year","javascript: browse('fiscalyear'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (fobCodesAccess > 0)
		{
			options[i] = new Array("FOB Codes","javascript: browseStdTable('FOB'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (inspectionCodesAccess > 0)
		{
			options[i] = new Array("Inspection Codes","javascript: browseStdTable('INSP'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (inspectionCriteriaAccess > 0)
		{
			options[i] = new Array("Inspection Criteria","javascript: browse('inspectioncriteria-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (inspectionUdfsAccess > 0)
		{
			options[i] = new Array("Inspection UDFs","javascript: toggleSubmenuDisplay(450); void(0);","<font class=raquo>&raquo;</font>","","",MenuArray450);
			i++;
		}
		if (languagesAccess > 0)
		{
			options[i] = new Array(LANG,"javascript: browseStdTable('LANG'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (lineUdfsAccess > 0)
		{
			options[i] = new Array("Line UDFs","javascript: toggleSubmenuDisplay(103); void(0);","<font class=raquo>&raquo;</font>","","",MenuArray103);
			i++;
		}
		if (requisitionUdfsAccess > 0)
		{
			options[i] = new Array("MSR/Request Category","javascript: browseStdTable('REQCAT'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (paymentTermsAccess > 0)
		{
			options[i] = new Array("Payment Terms","javascript: browse('paymentterm-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (priorityCodesAccess > 0)
		{
			options[i] = new Array("Priority Codes","javascript: browseStdTable('PRIO'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (procurementLevelAccess > 0)
		{
			options[i] = new Array("Procurement Levels","javascript: browseStdTable('PROCLVL'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (pCardCodesAccess > 0)
		{
			options[i] = new Array("Purchase Cards","javascript: browse('purchasecard-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (purchaseOrderUdfsAccess > 0)
		{
			options[i] = new Array("Purchase Order UDFs","javascript: toggleSubmenuDisplay(105); void(0);","<font class=raquo>&raquo;</font>","","", MenuArray105);
			i++;
		}
		if (purstatusanddocstatus > 0)
		{
			options[i] = new Array("Puridiom Status and Document Types","javascript: toggleSubmenuDisplay(404); void(0);","<font class=raquo>&raquo;</font>","","",MenuArray404);
			i++;
		}
		if (reasonForSavingsAccess > 0)
		{
			options[i] = new Array("Reason For Savings","javascript: browseStdTable('SAVE'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (receivingUdfsAccess > 0)
		{
			options[i] = new Array("Receiving UDFs","javascript: toggleSubmenuDisplay(107); void(0);","<font class=raquo>&raquo;</font>","","", MenuArray107);
			i++;
		}
		if (rejectionCodesAccess > 0)
		{
			options[i] = new Array("Rejection Codes","javascript: browseStdTable('REJ'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (requisitionUdfsAccess > 0)
		{
			options[i] = new Array("Requisition UDFs","javascript: toggleSubmenuDisplay(109); void(0);","<font class=raquo>&raquo;</font>","","", MenuArray109);
			i++;
		}
		if (reviewFinalizeAccess > 0 && reviewFinalizeEnabled == 'Y')
		{
			options[i] = new Array("Review Finalize Entries","javascript: browse('checklistentry-reviewfinalize'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (shipViaCodesAccess > 0)
		{
			options[i] = new Array("Ship Via Codes","javascript: browseStdTable('SHP'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (questionsAccess > 0)
		{
			options[i] = new Array("Solicitation Questions (Standard)","javascript: browse('stdquestion_admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (solicitationUdfsAccess > 0)
		{
			options[i] = new Array("Solicitation UDFs","javascript: toggleSubmenuDisplay(111); void(0);","<font class=raquo>&raquo;</font>","","", MenuArray111);
			i++;
		}
		if (inspectionCriteriaAccess > 0)
		{
			options[i] = new Array("Standard Inspections","javascript: browse('inspectionstd-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (stateProvinceCodesAccess > 0)
		{
			options[i] = new Array("State/Province Codes","javascript: browseStdTable('STAT'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (taxCodesAccess > 0)
		{
			options[i] = new Array("Tax Codes","javascript: javascript: browse('taxcode-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}/*
		if (timeZonesAccess > 0)
		{
			options[i] = new Array(TMZN,"javascript: doSubmit('/admin/systemtables/timezones.jsp', 'StdTableRetrieveTimeZones'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}*/
		if (transactionCodesAccess > 0)
		{
			options[i] = new Array("Transaction Codes","javascript: browseStdTable('TRAN'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (unitsOfMeasureAccess > 0)
		{
			options[i] = new Array("Units of Measure","javascript: browse('unitofmeasure-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (userTypesAccess > 0)
		{
			options[i] = new Array("User Types","javascript: browseStdTable('USRT'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (voucherReasonCodesAccess > 0)
		{
			options[i] = new Array("Voucher Reason Codes","javascript: browseStdTable('RCOD'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (voucherUdfsAccess > 0 )
		{
			options[i] = new Array("Voucher UDFs","javascript: toggleSubmenuDisplay(113); void(0);","<font class=raquo>&raquo;</font>","","", MenuArray113);
			i++;
		}
		if (xrefComboAccess > 0 )
		{
			options[i] = new Array("SOS/GL Account Table","javascript: browseXrefCombo('CARP'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}

		return options;
	}

	function createAssetTables(originalArray) {
		var options = new Array();
		var i = 1;

		options[0] = originalArray;

		if (buildingCodesAccess > 0)
		{
			options[i] = new Array("Asset Building Codes","javascript: browseStdTable('ASTB'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (depreciationMethodsAccess > 0)
		{
			options[i] = new Array("Asset Depreciation Methods","javascript: browseStdTable('ASTD'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (facilityCodesAccess > 0)
		{
			options[i] = new Array("Asset Facility Codes","javascript: browseStdTable('ASTF'); void(0);","<font class=raquo>&raquo;</font>", "", "");
			i++;
		}
		if (nameCodesAccess > 0)
		{
			options[i] = new Array("Asset Name Codes","javascript: browseStdTable('ASTN'); void(0);","<font class=raquo>&raquo;</font>", "", "");
			i++;
		}
		if (ownerCodesAccess > 0)
		{
			options[i] = new Array("Asset Owner Codes","javascript: browseStdTable('ASTO'); void(0);","<font class=raquo>&raquo;</font>", "", "");
			i++;
		}
		if (purchaseSuppliersAccess > 0)
		{
			options[i] = new Array("Asset Purchase Suppliers","javascript: browseStdTable('ASTP'); void(0);","<font class=raquo>&raquo;</font>", "", "");
			i++;
		}
		if (securityCodesAccess > 0)
		{
			options[i] = new Array("Asset Security Codes","javascript: browseStdTable('ASTS'); void(0);","<font class=raquo>&raquo;</font>", "", "");
			i++;
		}
		if (serviceContractAccess > 0)
		{
			options[i] = new Array("Asset Service Contract","javascript: browseStdTable('ASTC'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (trackingUdfsAccess > 0)
		{
			options[i] = new Array("Asset Tracking UDFs","javascript: toggleSubmenuDisplay(201); void(0);","<font class=raquo>&raquo;</font>", "", "", MenuArray201);
			i++;
		}
		if (upgradeRequirementsAccess > 0)
		{
			options[i] = new Array("Asset Upgrade Requirements","javascript: browseStdTable('ASTU'); void(0);","<font class=raquo>&raquo;</font>", "", "");
			i++;
		}

		return options;
	}


	function createInventoryTables(originalArray) {
		var options = new Array();
		var i = 1;

		options[0] = originalArray;

		if (binUdfsAccess > 0 && ((extInvActive || stdInvActive) && invAccess > 0))
		{
			options[i] = new Array("Inventory Bin UDFs","javascript: toggleSubmenuDisplay(301); void(0);","<font class=raquo>&raquo;</font>","","", MenuArray301);
			i++;
		}
		if (formTablesAccess > 0 && ((extInvActive || stdInvActive) && invAccess > 0))
		{
			options[i] = new Array("Inventory Form Tables","javascript: toggleSubmenuDisplay(303); void(0);","<font class=raquo>&raquo;</font>","","", MenuArray303);
			i++;
		}
		/*
		if (itemCatalogsAccess > 0)
		{
			options[i] = new Array("Inventory Item Catalogs","javascript: unavailable(); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		*/
		if (itemManagedByAccess > 0 && ((extInvActive || stdInvActive) && invAccess > 0))
		{
			options[i] = new Array("Inventory Item Managed By","javascript: browseStdTable('MANB'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (reasonCodesAccess > 0 && ((extInvActive || stdInvActive) && invAccess > 0))
		{
			options[i] = new Array("Inventory Reason Codes","javascript: browseStdTable('IVRC'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (inventoryUdfsAccess > 0 && ((extInvActive || stdInvActive) && invAccess > 0))
		{
			options[i] = new Array("Inventory UDFs","javascript: toggleSubmenuDisplay(305); void(0);","<font class=raquo>&raquo;</font>","","", MenuArray305);
			i++;
		}
		if (inventoryLocationUdfsAccess > 0 && ((extInvActive || stdInvActive) && invAccess > 0))
		{
			options[i] = new Array("Inventory Location UDFs","javascript: toggleSubmenuDisplay(306); void(0);","<font class=raquo>&raquo;</font>","","", MenuArray306);
			i++;
		}

		if (productCodesAccess > 0 && ((extInvActive || stdInvActive) && invAccess > 0))
		{
			options[i] = new Array("Product Codes","javascript: browseStdTable('PROD'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (extInvActive)
		{
			options[i] = new Array("Bin Locations","javascript: browseXrefCombo('BINS'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}

		return options;
	}

	function createMrpTables(originalArray) {
		var options = new Array();
		var i = 1;

		options[0] = originalArray;
		if (workCenterAccess > 0)
		{
			options[i] = new Array("Work Centers","javascript: browse('invworkcenter-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (machineAccess > 0)
		{
			options[i] = new Array("Machines", "javascript: browse('invmachine-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (routingTaskAccess > 0)
		{
			options[i] = new Array("Routing Tasks", "javascript: browse('invtask-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (routingStageAccess > 0)
		{
			options[i] = new Array("Routing Stages", "javascript: browse('invstage-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (routingMethodAccess > 0)
		{
			options[i] = new Array("Method Names", "javascript: browse('invmethod-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}

		return options;
	}

	function createSupplierTables(originalArray) {
		var options = new Array();
		var i = 1;

		options[0] = originalArray;

		if (insCategoryLevelAccess > 0)
		{
			options[i] = new Array("Insurance Category Level","javascript: browse('inscategorylevel-admin'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (complianceUdfsAccess > 0)
		{
			options[i] = new Array("Supplier Compliance UDFs","javascript: toggleSubmenuDisplay(401); void(0);","<font class=raquo>&raquo;</font>","","",MenuArray401);
			i++;
		}
		if (contractUdfsAccess > 0)
		{
			options[i] = new Array("Supplier Contract UDFs","javascript: toggleSubmenuDisplay(402); void(0);","<font class=raquo>&raquo;</font>","","",MenuArray402);
			i++;
		}
		if (insuranceStatusAccess > 0)
		{
			options[i] = new Array("Supplier Insurance Status","javascript: browseStdTable('SCST'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (insuranceLiabilityCoverageAccess > 0)
		{
			options[i] = new Array("Supplier Insurance Liability Coverage","javascript: browseStdTable('SILC'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (certificationTypesAccess > 0)
		{
			options[i] = new Array("Supplier Certification Types","javascript: browseStdTable('VSBA', true); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (ownershipTypesAccess > 0)
		{
			options[i] = new Array("Supplier Business Ownership Types","javascript: browseStdTable('VBOT', true); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (diverseCertOrgsAccess > 0)
		{
			options[i] = new Array("Diverse Certified Organizations","javascript: browseStdTable('VDCO', true); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (supplierTypesAccess > 0)
		{
			options[i] = new Array("Supplier Types","javascript: browseStdTable('SCTY'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (supplierUdfsAccess > 0)
		{
			options[i] = new Array("Supplier UDFs","javascript: toggleSubmenuDisplay(403); void(0);","<font class=raquo>&raquo;</font>","","",MenuArray403);
			i++;
		}

		return options;
	}

	function createAutoAccountingTables(originalArray) {
		var options = new Array();
		var i = 1;

		options[0] = originalArray;

		if(oid == "BLY07P"){
			if (1==1)
			{
				options[i] = new Array("Items without Suppliers","javascript: setTableType('mxp-vendor'); browse('mxp-vendor'); void(0);","<font class=raquo>&raquo;</font>","","");
				i++;
			}
			if (2==2)
			{
				options[i] = new Array("Item Warehouse Relationships","javascript: setTableType('mxp-warehouse-item'); browse('mxp-warehouse-item'); void(0);","<font class=raquo>&raquo;</font>","","");
				i++;
			}
		}
		else {
			if (1==1)
			{
				options[i] = new Array("Capital Clearing Account","javascript: setTableType('autoacc-cptclracc'); browse('autoacc_cptclracc'); void(0);","<font class=raquo>&raquo;</font>","","");
				i++;
			}
			if (2==2)
			{
				options[i] = new Array("CAR / Project Combinations","javascript: setTableType('autoacc-carprjcom'); browse('autoacc_carprjcom'); void(0);","<font class=raquo>&raquo;</font>","","");
				i++;
			}
			if (3==3)
			{
				options[i] = new Array("Entity / Department Combinations","javascript: setTableType('autoacc-entdepcom'); browse('autoacc_entdepcom'); void(0);","<font class=raquo>&raquo;</font>","","");
				i++;
			}
			if (4==4)
			{
				options[i] = new Array("Division Specific Combinations","javascript: setOriginalFilter('XrefCombo_code2', '=', UserProfile_nameUdf1 );setTableType('autoacc-divspccom'); browse('autoacc_divspccom'); void(0);","<font class=raquo>&raquo;</font>","","");
				i++;
			}
			if (5==5)
			{
				options[i] = new Array("Commodity Account Combinations","javascript: setTableType('autoacc-cmmacccom'); browse('autoacc_cmmacccom'); void(0);","<font class=raquo>&raquo;</font>","","");
				i++;
			}
		}
		return options;

	}

	function toggleSubmenuDisplay(x) {
		if (!validateArray("MenuArray" + x)) return false;

		var currentArray = eval("MenuArray" + x);

		if (currentArray[0][1] == 1) {
			hideArea(currentArray[0][0]);
			currentArray[0][1] = 0;
		}
		else {
			displayArea(currentArray[0][0]);
			currentArray[0][1] = 1;
		}
	}
