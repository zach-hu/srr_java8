
	function createRow(myTable) {
		var myTbody;
		var myRow;

		if (browser == "NS6") {
			if (myTable.tBodies.length <= 0)
			{
				myTbody = document.createElement("TBODY");
				myTable.appendChild(myTbody);
			}
			else
			{
				myTbody = myTable.tBodies[0];
			}

			myRow = document.createElement("TR");
			myTbody.appendChild(myRow);
		}
		else
		{
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
		setupAreaDisplay(areaName, "hidden", "none");
	}

	function hideAreaWithBlock(areaName) {
		setupAreaDisplay(areaName, "hidden", "block");
	}

	function displayArea(areaName) {
		setupAreaDisplay(areaName, "visible", "block");
	}

	function setupAreaDisplay(areaName, visibilityType, displayType) {
		if (browser == "NS") {
			//The option to display/hide areas is currently not working in this version of Netscape.
		}
		else if ( browser == "IE" ) {
			var d = document.all(areaName);
			var i = 0;
			var multiple = false;
			if (d == null) {
				d = document.all(areaName + i);
				multiple = true;
			}
			while (d != null) {
				if (d.length > 1) {
					for (var i=0; i < d.length; i++) {
						d[i].style.visibility = visibilityType;
						d[i].style.display = displayType;
					}
				} else {
						d.style.visibility = visibilityType;
						d.style.display = displayType;
				}
				if (multiple) {
					i++;
					d = document.all(areaName + i);
				} else {
					d = null;
				}
			}
		}
		else if ( browser == "NS6" ) {
			var d = document.getElementById(areaName);
			var i = 0;
			var multiple = false;
			if (d == null) {
				d = document.getElementById(areaName + i);
				multiple = true;
			}
			while (d != null) {
				if (d.length > 1){ 
					for (var i=0; i < d.length; i++) {
						d[i].style.visibility = visibilityType;
						d[i].style.display = displayType;
					}
				}else{
				d.style.visibility = visibilityType;
				d.style.display = displayType;
				}
				if (multiple) {
					i++;
					d = document.getElementById(areaName + i);
				} else {
					d = null;
				}
			}
		}
		else if (document.ids) {
			eval("document.ids." + areaName +".visibility='" + visibilityType + "'")
			eval("document.ids." + areaName +".display='" + displayType + "'")
		}
		else {
			eval("document.all." + areaName +".style.visibility='" + visibilityType + "'")
			eval("document.all." + areaName +".display='" + displayType + "'")
		}
	}

	function setInnerHTML(areaName, text) {
		if (browser == "NS6") {
			var d = document.getElementById(areaName);
			if (d.length > 1) {
				for (var i=0; i < d.length; i++) {
					d[i].innerHTML = text;
				}
			} else {
					d.innerHTML = text;
			}
		} else {
			var d = document.all(areaName);

			if (d.length > 1) {
				for (var i=0; i < d.length; i++) {
					d(i).innerHTML = text;
				}
			} else {
					d.innerHTML = text;
			}
		}
	}

	function setInnerText(areaName, text) {
		if (browser == "NS6")  {
			var d = document.getElementById(areaName);

			if (d.length > 1) {
				for (var i=0; i < d.length; i++) {
					d[i].innerText = text;
				}
			} else {
					d.innerText = text;
			}
		} else {
			var d = document.all(areaName);

			if (d.length > 1) {
				for (var i=0; i < d.length; i++) {
					d(i).innerText = text;
				}
			} else {
					d.innerText = text;
			}
		}
	}

	function deleteAllRows(myTable) {
		if (browser == "NS6") {
			var rowCount = myTable.childNodes.length;

			for (var i = rowCount - 1; i >= 0; i--) {
				if (myTable.childNodes[i].nodeName == 'TR') {
					rowToDelete = myTable.childNodes[i];
					myTable.removeChild(rowToDelete);
				}
			}
		} else {
			var rowCount = myTable.rows.length;
			for (i = 0; i < rowCount; i++) {
				myTable.deleteRow(0);
			}
		}
	}

	function deleteRow(myTable, row) {
/*
		if (browser == "NS6") {
			var currentRow = 0;
			var rowCount = myTable.childNodes.length;
			for (var i = 0; i < rowCount; i++) {
				if (myTable.childNodes[i].nodeName == 'TR') {
					if (row == currentRow) {
						rowToDelete = myTable.childNodes[i];
						myTable.removeChild(rowToDelete);
						i = rowCount + 1;
					} else {
						currentRow++;
					}
				}
			}
		} else {
*/

			if (myTable.rows.length > row) {
				myTable.deleteRow(row);
			}
	//	}
	}

	function setNewId(currentId, newId) {
		if (browser == "NS6")  {
			var d = document.getElementById(currentId);

			if (d.length > 1) {
				for (var i=0; i < d.length; i++) {
					d[i].id = newId;
				}
			} else {
					d.id = newId;
			}
		} else {
			var d = document.all(currentId);

			if (d.length > 1) {
				for (var i=0; i < d.length; i++) {
					d(i).id = newId;
				}
			} else {
					d.id = newId;
			}
		}
	}