
	var browser = browserCheck();

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
		for (x=0; x < menuTitles.length; x++) {
			generateMenuTitle(x);
			if (displayValue[x] == 0) {
				generateMenuOptions(x);
			}
		}

		f_StartIt();
	}

	function generateMenuTitle(x) {
		var myParent = document.getElementById("parentTable" + x);
		var myTable = document.getElementById("menuTitle" + x);
		var myRow;
		var myCell;
		var myImg;

		if (displayValue[x] == 0) {
			myImg = "<img id='menuImage" + x + "' src='<%=contextPath%>/images/arrow_minimize_blue.gif' border=0>";
		}
		else {
			myImg = "<img id='menuImage" + x + "' src='<%=contextPath%>/images/arrow_maximize_blue.gif' border=0>";
		}

		myParent.className = "bnav";
		myTable.className = "bnav";

		if (browser == "NS6") {
			myRow = document.createElement("TR");
			myTable.appendChild(myRow);

			myRow.className = "bnav";

			myCell = document.createElement("TD");
			myRow.appendChild(myCell);

			myCell.style.width = "90%";
			myCell.align = "center";
			myCell.className = "bnav";
			myCell.innerHTML = menuTitles[x];

			myCell = document.createElement("TD");
			myRow.appendChild(myCell);

			myCell.align = "right";
			myCell.style.width = "10%";
			myCell.className = "bnav";
			myCell.innerHTML = "<a href='javascript: toggleDisplay(" + x + "); void(0);'>" + myImg + "</a>";
		}
		else {
			myRow = myTable.insertRow();

			myRow.className = "bnav";

			myCell = myRow.insertCell();
			myCell.style.width = "90%";
			myCell.align = "center";
			myCell.className = "bnav";
			myCell.innerText = menuTitles[x];

			myCell = myRow.insertCell();
			myCell.align = "right";
			myCell.style.width = "10%";
			myCell.className = "bnav";
			myCell.innerHTML = "<a href='javascript: toggleDisplay(" + x + "); void(0);'>" + myImg + "</a>";
		}
	}

	function generateMenuOptions(x) {
		if (browser == "NS" || browser == "NS6") {
			generateMenuOptionsNS(x);
		}
		else {
			var myTable = document.getElementById("menuOptions" + x);
			var myRow;
			var myCell;

			myTable.className = "brow";

			for (i=0; i < menuOptions[x].length; i++) {
				myRow = myTable.insertRow();
				myRow.className = "brow";

				myCell = myRow.insertCell();
				myCell.style.width = "100%";
				myCell.className = "brow";
				myCell.innerHTML = menuOptions[x][i];
			}
		}
	}

	function generateMenuOptionsNS(x) {
		var myTable = document.getElementById("menuOptions" + x);
		var myRow;
		var myCell;

		myTable.className = "brow";

		for (i=0; i < menuOptions[x].length; i++) {
			myRow = document.createElement("TR");
			myTable.appendChild(myRow);

			myRow.className = "brow";

			myCell = document.createElement("TD");
			myRow.appendChild(myCell);

			myCell.style.width = "100%";
			myCell.className = "brow";
			myCell.innerHTML = menuOptions[x][i];
		}
	}

	function hideArea(x) {
		var myTable = document.getElementById("menuOptions" + x);

		if ((browser == "NS" || browser == "NS6") && myTable.hasChildNodes()) {
			var row;
			for (var i = myTable.childNodes.length - 1; i >= 0 ; i--) {
				if (myTable.childNodes[i].nodeName == 'TR') {
					row = myTable.childNodes[i];
					myTable.removeChild(row);
				}
			}
		}
		else {
			for (i = myTable.rows.length - 1; i >= 0; i--) {
				myTable.deleteRow(0);
			}
		}

		displayValue[x] = 1;
	}

	function displayArea(x) {
		generateMenuOptions(x);
		displayValue[x] = 0;
	}

	function toggleDisplay(x) {
		var img = document.getElementById("menuImage" + x);

		if (displayValue[x] == 0) {
			img.src = "<%=contextPath%>/images/arrow_maximize_blue.gif";

			hideArea(x);
		}
		else {
			img.src = "<%=contextPath%>/images/arrow_minimize_blue.gif";

			displayArea(x);
		}
	}