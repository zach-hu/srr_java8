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

		checkGraphData();
		//f_StartIt();
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
			myImg = "<img id='menuImage" + x + "' src='/" + context + "/images/arrows_up.gif' border=0>";
		}
		else
		{
			myClass = currentArray[0][3];

			myImg = "<img id='menuImage" + x + "' src='/" + context + "/images/arrows_down.gif' border=0>";
		}

		myParent.className = myClass;
		myTable.className = myClass;


			myTbody = document.createElement("TBODY");
			myTable.appendChild(myTbody);

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

	function generateMenuOptions(x) {
		var currentArray = eval("MenuArray" + x);
		var myTable = document.getElementById("menuOptions" + x);
		var myRow;
		var myCell;
		var myHTML = "";

		myTable.className = "moption";

		myRow = createRow(myTable);
		myCell = createCell(myRow);
		myDiv = createDiv(myCell);

		myTable = document.createElement("TABLE");
		myTable.className = "moption";
		myTable.style.width = "100%";
		myDiv.appendChild(myTable);

		for (i=1; i < currentArray.length; i++) {
			myRow = createRow(myTable);
			myRow.className = "moption";

			myCell = createCell(myRow);
			myCell.style.width = "100%";
			myCell.className = "moption";

			if (currentArray[i][1].length > 0) {
				if (currentArray[i][4] && !isEmpty(currentArray[i][4]) && currentArray[i][5] && !isEmpty(currentArray[i][5]) && currentArray[i][6] && !isEmpty(currentArray[i][6])) {
					myHTML = "<a href=" + '"' + currentArray[i][1] + '"' + " title=" + '"' + currentArray[i][4] + '"' + " onMouseOver=" + '"' + currentArray[i][5] + '"' + " onMouseOut=" + '"' + currentArray[i][6] + '"' + ">" + currentArray[i][0] + "</a>";
				}
				else if (currentArray[i][5] && !isEmpty(currentArray[i][5]) && currentArray[i][6] && !isEmpty(currentArray[i][6])) {
					myHTML = "<a href=" + '"' + currentArray[i][1] + '"' + " onMouseOver=" + '"' + currentArray[i][5] + '"' + " onMouseOut=" + '"' + currentArray[i][6] + '"' + ">" + currentArray[i][0] + "</a>";
				}
				else if (currentArray[i][4] && !isEmpty(currentArray[i][4])) {
					myHTML = "<a href=" + '"' + currentArray[i][1] + '"' + " title=" + '"' + currentArray[i][4] + '"' + ">" + currentArray[i][0] + "</a>";
				}
				else {
					myHTML = "<a href=" + '"' + currentArray[i][1] + '"' + ">" + currentArray[i][0] + "</a>";
				}
			}
			else {
				myHTML = currentArray[i][0];
			}

			myCell.innerHTML = currentArray[i][2] + myHTML + currentArray[i][3];
		}
	}

	function hideMenuArea(x) {
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
			/*if (myTable.childNodes[i].nodeName == 'TBODY') {

				tBody = myTable.childNodes[i];
				for (var j = tBody.childNodes.length - 1; j>=0; j--){
					if(tBody.childNodes[j].nodeName == 'TR'){
						row = tBody.childNodes[j];
						tBody.removeChild(row);
					}
				}
			}*/
			myTable.removeChild(myTable.childNodes[i]);
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

	function displayMenuArea(x) {
		if (!validateArray("MenuArray" + x)) return false;

		var currentArray = eval("MenuArray" + x);
		var myClass = currentArray[0][2];
		var myParentTable = document.getElementById("parentTable" + x)
		var myTitleTable = document.getElementById("menuTitle" + x)
		var myTable = document.getElementById("menuOptions" + x);

		myParentTable.className = myClass;
		myTitleTable.className = myClass;

		if ((browser == "NS" || browser == "NS6") ) {
			var tbody;
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
			hideMenuArea(x);
			img.src = "/" + context + "/images/arrows_down.gif";


		}
		else {
			img.src = "/" + context + "/images/arrows_up.gif";

			displayMenuArea(x);
		}
	}

	function createMySolicitationOptions(originalArray)
	{
		var options = new Array();
		var i = 1;

		options[0] = originalArray;

		if (rfqsActive) {
			options[i] = new Array(searchsolicitations,"javascript: doSubmit('/browse/rfq_browse_filter_options.jsp', 'DoNothing'); void(0);","<span class=raquo>&raquo;</span>","","Click here to browse solicitations.");
			i++;

			if (!bIsGuest)
			{
				options[i] = new Array(searchmatchingcommodities,"javascript: setSearch('MyCommodities'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show solicitations matching your commodities.");
				i++;
				options[i] = new Array(searchrecentlybidsolicitations,"javascript: setSearch('MyBids'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show solicitations you have bid on.");
				i++;
			}

			options[i] = new Array(searchrecentlyclosedsolicitations,"javascript: setSearch('Closed'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show recently closed solicitations.");
			i++;
		}

		return options;
	}

	function createMyOrderOptions(originalArray)
	{
		var options = new Array();
		var i = 1;

		options[0] = originalArray;

		if (posActive && bIsQualified)
		{
			options[i] = new Array(searchorders,"javascript: searchOrders(); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show your Purchase Orders.");
			i++;
			options[i] = new Array(orderspendingack,"javascript: searchOrdersPendingAck(); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show your Purchase Orders that are pending your acknowledgement.");
			i++;
		}

		return options;
	}

	function createMyInvoiceOptions(originalArray)
	{
		var options = new Array();
		var i = 1;

		options[0] = originalArray;

		if (vouchersActive && bIsQualified)
		{
			options[i] = new Array(newinvoice,"javascript: doSubmit('/invoice/iv_create.jsp', 'DoNothing'); void(0);","<span class=raquo>&raquo;</span>","","Click here to create a new invoice.");
			i++;
			options[i] = new Array(searchinvoices,"javascript: searchInvoices(); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show your Invoices.");
			i++;
		}

		return options;
	}

	function createSurplusOptions(originalArray)
	{
		var options = new Array();
		var i = 1;

		options[0] = originalArray;

		if (salesActive) {
			options[i] = new Array(searchsurplus,"javascript: searchSales(); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show you surplus auctions.");
			i++;
		}

		return options;
	}

	function createOtherOptions(originalArray)
	{
		var options = new Array();
		var i = 1;

		options[0] = originalArray;

		if (bIsQualified)
		{
			options[i] = new Array(myprofile,"javascript: doSubmit('/user/prequalification.jsp', 'VendorRegisterRetrieveByEmail;VendorOptionsRetrieve'); void(0);","<span class=raquo>&raquo;</span>","","Click here to view your profile information.");
//			options[i] = new Array(myprofile,"javascript: doSubmit('/user/profile.jsp', 'VendorContactRetrieveByEmail;VendorOptionsRetrieve'); void(0);","<span class=raquo>&raquo;</span>","","Click here to view your profile information.");
			i++;
		}
		else
		{
			options[i] = new Array(prequalify,"javascript: doSubmit('/user/prequalification.jsp', 'VendorRegisterRetrieveByEmail;VendorOptionsRetrieve'); void(0);","<span class=raquo>&raquo;</span>","","Click here to view your profile information.");
			i++;
		}

		if (!bIsGuest)
		{
			options[i] = new Array(downloaddocs,"javascript: doSubmit('/documents/std_documents_menu.jsp', 'DoNothing'); void(0);","<span class=raquo>&raquo;</span>","","Click here to download solicitation documents.");
			i++;
			options[i] = new Array("Upload Documents","javascript: uploadDocs('C'); void(0);","<span class=raquo>&raquo;</span>","","Click here to upload documents.");
			i++;
			options[i] = new Array(changepswd,"javascript: doSubmit('/user/chg_pswd.jsp', 'DoNothing'); void(0);","<span class=raquo>&raquo;</span>","","Click here to reset your password.");
			i++;
		}

		return options;
	}

	function createOrdersByStatusGraph(originalArray, graphName)
	{
		var options = new Array();
		var i = 1;

		if (!bIsGuest)
		{
			options[0] = originalArray;
			options[1] = new Array(graphName, "", "", "");
		}

		return options;
	}

	function createRfqByStatusGraph(originalArray, graphName)
	{
		var options = new Array();
		var i = 1;

		options[0] = originalArray;
		options[1] = new Array(graphName, "", "", "");

		return options;
	}
