
	function createRow(myTable) {
		var myRow;

		if (browser == "NS6") {
			myRow = document.createElement("TR");
			myTable.appendChild(myRow);
		}
		else {
			myRow = myTable.insertRow();
		}

		return myRow;
	}

	function createCell(myRow) {
		var myCell;

		if (browser == "NS6") {
			myCell = document.createElement("TD");
			myRow.appendChild(myCell);
		}
		else {
			myCell = myRow.insertCell();
		}

		return myCell;
	}

	function createDiv(myCell) {
		var myDiv = document.createElement("DIV");
		myCell.appendChild(myDiv);

		return myDiv;
	}

	function hideArea(areaName) {
		if (browser == "NS") {
			//The option to hide rows is currently not working in this version of Netscape.
		}
		else if ( (browser == "IE") || (browser == "NS6") ) {
			var d = document.all(areaName);

			if (d.length > 1) {
				for (var i=0; i < d.length; i++) {
					d(i).style.visibility="hidden";
					d(i).style.display="none";
				}
			} else {
					d.style.visibility="hidden";
					d.style.display="none";
			}
		}
		else {
    			eval("document.all." + areaName +".style.visibility='hidden'")
			eval("document.all." + areaName +".display='none'")
		}
	}

	function hideAreaWithBlock(areaName) {
		if (browser == "NS") {
			//The option to hide rows is currently not working in this version of Netscape.
		}
		else if ( (browser == "IE") || (browser == "NS6") ) {
			var d = document.all(areaName);

			if (d.length > 1) {
				for (var i=0; i < d.length; i++) {
					d(i).style.visibility="hidden";
					d(i).style.display="block";
				}
			} else {
					d.style.visibility="hidden";
					d.style.display="block";
			}
		}
		else {
       		eval("document.all." + areaName +".style.visibility='hidden'")
			eval("document.all." + areaName +".display='block'")
		}
	}

	function displayArea(areaName) {
		if (browser == "NS") {
			//The option to display hidden rows is currently not working in this version of Netscape.
		}
		else if ( (browser == "IE") || (browser == "NS6") ) {
			var d = document.all(areaName);

			if (d.length > 1) {
				for (var i=0; i < d.length; i++) {
					d(i).style.visibility="visible";
					d(i).style.display="block";
				}
			} else {
					d.style.visibility="visible";
					d.style.display="block";
			}
		}
		else {
			eval("document.all." + areaName +".style.visibility='visible'")
			eval("document.all." + areaName +".display='block'")
		}
	}

	function setInnerHTML(areaName, text) {
		var d = document.all(areaName);

		if (d.length > 1) {
			for (var i=0; i < d.length; i++) {
				d(i).innerHTML = text;
			}
		} else {
				d.innerHTML = text;
		}
	}

	function setInnerText(areaName, text) {
		var d = document.all(areaName);

		if (d.length > 1) {
			for (var i=0; i < d.length; i++) {
				d(i).innerText = text;
			}
		} else {
				d.innerText = text;
		}
	}