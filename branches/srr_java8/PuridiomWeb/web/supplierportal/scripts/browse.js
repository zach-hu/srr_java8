	var currentPage=1;
	var currentGroup=1;

	function browseDisplaySetup() {
		if (totalRows <= 0) {
			document.getElementById("noRecords").style.visibility = "visible";
			document.getElementById("noRecords").style.display = "block";
			document.getElementById("browseBorder").style.height = "25px";
			
			if (originalFilterSet) {
				displayArea("resetOriginalOption");
			}
		} else {
			if (pageCount > 1) {
				displayPageNavigation();
				if (sortedColumn.length > 0) {
					displayArea("findPageOptions");
				}
			} else if (filterSet){
				displayArea("resetOption");
			}
			if (originalFilterSet) {
				displayArea("resetOriginalOption");
			}
			displayArea("page1");
			hideAreaWithBlock("previousPage");
		}
	}

	function highlightRow(row) {
		var myObject = document.all("browseRows");
		var myTable;

		if (myObject.length > 1) {
			myTable = myObject(currentPage - 1);
		} else {
			myTable = myObject;
		}
		var myRow = myTable.rows[row];

		setRowClassName(myRow, "selectedRow");

		highlightDetails(row);
	}

	function highlightDetails(browseRow) {
		var row =  browseRow + (pageSize*currentPage) - pageSize;
		var myObject = document.all("detailRows");
		var myTable;

		if (myObject != null) {
			if (myObject.length > 1) {
				myTable = myObject[row];
			} else {
				myTable = myObject;
			}
			var myRow;

			for (var i=0; i < myTable.rows.length; i++) {
				myRow = myTable.rows[i];
				setRowClassName(myRow, "selectedRow");
			}
		}
	}

	function removeHighlight(row) {
		var myTable = document.getElementById("browseRows");
		var myRow = myTable.rows[row];

		if (row % 2 == 0)
		{
			setRowClassName(myRow, "browseRow");
		}
		else
		{
			setRowClassName(myRow, "summary");
		}

		removeDetailsHighlight(row);
	}

	function removeDetailsHighlight(browseRow) {
		var row =  browseRow + (pageSize*currentPage) - pageSize;
		var myObject = document.all("detailRows");
		var myTable;

		if (myObject != null) {
			if (myObject.length > 1) {
				myTable = myObject[row];
			} else {
				myTable = myObject;
			}
			var myRow;

			for (var i=0; i < myTable.rows.length; i++) {
				myRow = myTable.rows[i];
				setRowClassName(myRow, "browseRow");
			}
		}
	}

	function setRowClassName(myRow, className) {
		var myCell;
		var myTable;

		for (var i = myRow.cells.length - 1; i >= 0 ; i--) {
			myCell = myRow.cells[i];
			myCell.className = className;

			if (myCell.hasChildNodes()) {
				for (var ix = myCell.childNodes.length - 1; ix >= 0 ; ix--) {
					if (myCell.childNodes[ix].nodeName == 'TABLE') {
						myTable = myCell.childNodes[ix];

						for (var ir = myTable.rows.length - 1; ir >= 0; ir--) {
							setRowClassName(myTable.rows[ir], className);
						}
					}
					else if (myCell.childNodes[ix].nodeName == 'A') {
						myCell.childNodes[ix].className = className;
					}
				}
			}
		}
	}

	function displayPageNavigation() {
		displayArea("pageNavigation");
		displayArea("filterOptions");
		displayArea("nextPage");
	}

	function pageMe(pg) {
		if (pg < 1 || pg > pageCount) {
			alert("You must select or enter a valid page number.");
			return;
		}
 
		var pageLink = "<a href=\"javascript: pageMe(" + currentPage + ");\">" + currentPage + "</a>&nbsp;";
		var ibegin = 1 + ((pg - 1) * pageSize);
		var iend = (pg * pageSize);

		if (ibegin == 1) {
			hideAreaWithBlock("previousPage");
		} else {
			displayArea("previousPage");
		}

	 	if (iend >= totalRows) {
			iend = totalRows;
			hideAreaWithBlock("nextPage");
		} else {
			displayArea("nextPage");
		}

		setInnerText("beginRecord", ibegin);
		setInnerText("endRecord", iend);
		setInnerHTML("pageLink" + currentPage, pageLink);
		setInnerHTML("pageLink" + pg, "<b>" + pg + "</b>&nbsp;");
		
		hideArea("page" + currentPage);
		displayArea("page" + pg);
		displayArea("page" + pg);

		frm.go_to_pg.value = "";

		currentPage = pg;
	}

	function previousPage() {
		pageMe(currentPage - 1);
	}

	function nextPage() {
		pageMe(currentPage + 1);
	}

	function sortMe(colname) {
		var sortOrder = "ASC";
		var columns = frm.elements.item("colname");
		var sorts = frm.elements.item("sort");

		if (columns != null) {
			if (columns.length > 1) {
				for (var i=0; i < columns.length; i++) {
					if (columns(i).value == colname) {
						if (sorts(i).value.toUpperCase() == "ASC") {
							sortOrder = "DESC";
						}
					}
					sorts(i).value = "N";
				}
			} else {
				if (columns.value == colname) {
					if (sorts.value.toUpperCase() == "ASC") {
						sortOrder = "DESC";
					}
				}
				sorts.value = "N";
			}
		}
		
		var myCell = document.getElementById("filterFields");
		var filterFields = myCell.innerHTML;

		filterFields = filterFields + "<input type=hidden name=\"" + "colname" + "\" value=\"" + colname  + "\">"; 
		filterFields = filterFields + "<input type=hidden name=\"" + "operator\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "filter_txt\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "logicalOperator\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "originalFilter\" value=\"N\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "sort\" value=\"" + sortOrder + "\">";

		myCell.innerHTML = filterFields;

		browse(browseName);
	}

	function findMe(x) {
		var findString = frm.finder.value.trim();
		var found = false;
		var myObject = document.all("browseRows");

		if (myObject.length > 1) {
			for (var i=0; i < myObject.length; i++) {
				var myTable = myObject(i);

				for (var ir=0; ir < myTable.rows.length; ir++) {
					var pg = i + 1;
					var id = "sortedValue" + pg + "-" + ir;
					var sortedRef = document.getElementById(id);
					var sortedValue = sortedRef.innerHTML;

					if (sortedValue != null && sortedValue.toUpperCase().indexOf(findString) >= 0) {
						if (!found) {
							var fontWeight = sortedRef.currentStyle.fontWeight;
							found = true;
							pageMe(i+1);
							sortedRef.style.fontWeight = fontWeight + 200;
							frm.finder.value = "";
						} else {
							sortedRef.style.fontWeight = "normal";
						}
					} else {
						sortedRef.style.fontWeight = "normal";
					}
				}
			}
		}
		if (!found) {
			alert("The value you entered was not found.");
		}
	}

	function nextGroup() {
		var group = eval(currentGroup) + 1;
		navigateGroup(group);
		var ibegin = ((currentGroup * 5) - 5) + 1;
		pageMe(ibegin);
	}

	function previousGroup() {
		var group = eval(currentGroup) - 1;
		navigateGroup(group);
		var ibegin = ((currentGroup * 5) - 5) + 1;
		pageMe(ibegin);
	}
