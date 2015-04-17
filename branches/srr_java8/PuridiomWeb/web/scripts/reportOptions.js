
	var browser = browserCheck();
	var reqAccess = 0;
	var rfqAccess = 0;
	var poAccess = 0;
	var questAccess = 0;
	var bbdocsAccess = 0;
	var bbsupAccess = 0;
	var approver = false;
	var buyer = false;

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
		var myRow;
		var myCell;
		var myImg;
		var myClass;

		if (currentArray[0][1] == 1) {
			myClass = currentArray[0][2];
			myImg = "<img id='menuImage" + x + "' src='<%=contextPath%>/images/arrows_up.gif' border=0>";
		}
		else {
			myClass = currentArray[0][3];

			myImg = "<img id='menuImage" + x + "' src='<%=contextPath%>/images/arrows_down.gif' border=0>";
		}

		myParent.className = myClass;
		myTable.className = myClass;

		if (browser == "NS6") {
			myRow = document.createElement("TR");
			myTable.appendChild(myRow);

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
			}
		}
	}

	function generateMenuOptionsNS(x) {
		var myTable = document.getElementById("menuOptions" + x);
		var myRow;
		var myCell;

		myTable.className = "moption";

		for (i=0; i < menuOptions[x].length; i++) {
			myRow = document.createElement("TR");
			myTable.appendChild(myRow);

			myRow.className = "moption";

			myCell = document.createElement("TD");
			myRow.appendChild(myCell);

			myCell.style.width = "100%";
			myCell.className = "moption";
			myCell.innerHTML = menuOptions[x][i];
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
			var row;

			for (var i = myTitleTable.childNodes.length - 1; i >= 0 ; i--) {
				if (myTitleTable.childNodes[i].nodeName == 'TR') {
					row = myTitleTable.childNodes[i];
					row.className = myClass;

					for (var ic = row.childNodes.length - 1; ic >= 0 ; ic--) {
						cell = row.childNodes[ic];
						cell.className = myClass;
					}
				}
			}

			for (var i = myTable.childNodes.length - 1; i >= 0 ; i--) {
				if (myTable.childNodes[i].nodeName == 'TR') {
					row = myTable.childNodes[i];
					myTable.removeChild(row);
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

	function displayMenuArea(x) {
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
				if (myTitleTable.childNodes[i].nodeName == 'TR') {
					row = myTitleTable.childNodes[i];
					row.className = myClass;

					for (var ic = row.childNodes.length - 1; ic >= 0 ; ic--) {
						cell = row.childNodes[ic];
						cell.className = myClass;
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
			img.src = "<%=contextPath%>/images/arrows_down.gif";

			hideMenuArea(x);
		}
		else {
			img.src = "<%=contextPath%>/images/arrows_up.gif";

			displayMenuArea(x);
		}
	}

	function createMyRequisitionActions(originalArray) {
		var options = new Array();
		var i = 1;

		options[0] = originalArray;

		if (reqAccess > 0) {
			options[1] = new Array("My Incomplete Requisitions","javascript: viewReqs('INCOMPLETE'); void(0);","<font class=raquo>&raquo;</font>","","");
			options[2] = new Array("My Rejected Requisitions","javascript:viewReqs('REJECTED');void(0);","<font class=raquo>&raquo;</font>","","");
			options[3] = new Array("My Approved Requisitions","javascript:viewReqs('APPROVED');void(0);","<font class=raquo>&raquo;</font>","","");
			options[4] = new Array("My Requisitions on Order","javascript:viewReqs('ONORDER');void(0);","<font class=raquo>&raquo;</font>","","");
		}
		return options;
	}

	function createMyActionItems(originalArray) {
		var options = new Array();
		var i = 1;

		options[0] = originalArray;

		if (approver) {
			options[i] = new Array("Requisitions Waiting My Approval","javascript: viewReqs('APPROVAL'); void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (rfqAccess > 0) {
			options[i] = new Array("Solicitations due this week","javascript:viewRfqsDueThisWeek();void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
		}
		if (poAccess > 0) {
			options[i] = new Array("Requisitions Assigned to Me","javascript:viewReqs('ASSIGNED');void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
			options[i] = new Array("Purchase Orders that are Incomplete","javascript: viewOrders('INCOMPLETE'); void(0);","<font class=raquo>&raquo;</font>", "");
			i++;
			options[i] = new Array("Purchase Orders that are Overdue","javascript: viewOrders('OVERDUE'); void(0);","<font class=raquo>&raquo;</font>", "");
			i++;
			options[i] = new Array("Blanket Orders that Expire within 60 days","javascript: viewOrders('BOEXPIRE'); void(0);","<font class=raquo>&raquo;</font>", "");
			i++;
		}
		if (questAccess > 0) {
			options[i] = new Array("Standard Solicitation Questions","javascript:unavailable(); void(0);","<font class=raquo>&raquo;</font>", "");
			i++;
		}
		if (bbdocsAccess > 0) {
			options[i] = new Array("Bid Board Standard Documents","javascript: viewStandardDocuments(); void(0);","<font class=raquo>&raquo;</font>", "");
			i++;
		}
		if (bbsupAccess > 0) {
			options[i] = new Array("View Pre-Qualified Suppliers","javascript: viewPrequalifiedSuppliers(); void(0);","<font class=raquo>&raquo;</font>", "");
			i++;
		}
		if (buyerAssignAccess > 0) {
			options[i] = new Array("Buyer Assignment Workload","javascript: viewBuyerAssignment(); void(0);","<font class=raquo>&raquo;</font>", "");
			i++;
		}

		return options;
	}

	function createMyReceivingActions(originalArray) {
		var options = new Array();
		var i = 1;

		options[0] = originalArray;

		if (rcvByPkgAccess > 0) {
			options[i] = new Array("Receive Packages","javascript: browseFilter('receipt-order-pkg'); void(0);","<font class=raquo>&raquo;</font>", "");
			i++;
		}
		if (rcvByItemAccess >= 3 || rcvByEndUserAccess >= 3) {
			options[i] = new Array("Receive Items","javascript:receiveByItem();void(0);","<font class=raquo>&raquo;</font>", "");
			i++;
		}
		if (rcvFinalizeAccess > 0) {
			options[i] = new Array("Finalize Receipts","javascript:finalizeReceipts();void(0);","<font class=raquo>&raquo;</font>", "");
			i++;
		}
		if (rcvAdjAccess > 0) {
			options[i] = new Array("Adjust Receipt","javascript:adjustReceipts();void(0);","<font class=raquo>&raquo;</font>", "");
			i++;
		}
		if (rcvReturnAccess > 0) {
			options[i] = new Array("Return Received Items","javascript:returnAgainstReceipts();void(0);","<font class=raquo>&raquo;</font>", "");
			i++;
		}

		return options;
	}

	function createMyGraphActions(originalArray, graphName) {
		var options = new Array();
		var i = 1;

		options[0] = originalArray;
		options[1] = new Array(graphName, "", "", "");

		return options;
	}

	function getMyReports(originalArray, reportName) {
		var options = new Array();
		var i = 1;

		options[0] = originalArray;
		options[1] = new Array(reportName, "reports/" + reportName, "", "");

		return options;
	}