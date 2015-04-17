	var currentPage=1;
	var currentGroup=1;
	var rowCount = 0;
	var rowSelect = -1;

	function browseDisplaySetup() {
		if (totalRows <= 0) {
			var attributeSet = true;

			if (frm.attributeSet && frm.attributeSet != undefined && frm.attributeSet.value == "false") {
				attributeSet = false;
			}
			if (!attributeSet) {
				document.getElementById("noAttributeSet").style.visibility = "visible";
				document.getElementById("noAttributeSet").style.display = "block";
			} else {
				document.getElementById("noRecords").style.visibility = "visible";
				document.getElementById("noRecords").style.display = "block";
			}
			document.getElementById("browseBorder").style.height = "25px";

			hideArea("filterOptions");
		} else {
			if (pageCount > 1) {
				var temp = new String(currentPage / 5);
				var ind = temp.indexOf(".");
				var group = 1;
				if (ind >= 0) {
					temp = temp.substring(0, ind);
					group = eval(temp) + 1;
				} else {
					group = eval(temp);
				}

				navigateGroup(new String(group));

				displayPageNavigation();
				if (sortedColumn.length > 0) {
					displayArea("findPageOptions");
				}
			} else if (filterSet){
				//displayArea("resetOption");
				displayArea("resetFilterOption");
				hideArea("filterOptions");
			} else {
				hideArea("filterOptions");
			}
			//if (originalFilterSet) {
			//	displayArea("resetOriginalOption");
			//}
		}
		hideArea("resetOriginalOption");
	}


	function navigateGroup(group) {
		hideArea("group" + currentGroup);
		displayArea("group" + group);

		if (group == 1) {
			hideAreaWithBlock("previousPage");
			hideAreaWithBlock("previousGroup");
		} else {
			displayArea("previousPage");
			displayArea("previousGroup");
		}

		if (group >= (pageCount/5)) {
			hideAreaWithBlock("nextGroup");
		} else {
			displayArea("nextGroup");
		}

		currentGroup = group;
	}

	function highlightRow(row) {
		var myTable = document.getElementById("browseRows");
		var myRow = myTable.rows[row];
		setRowClassName(myRow, "selectedRow");

//		highlightDetails(row);
	}

	function highlightDetails(browseRow) {
		var myObject = document.getElementById("detailRows");
		var myTable;

		if (myObject != null) {
			if (myObject.length > 1) {
				myTable = myObject[browseRow];
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
		if (rowSelect == row) {
			return;
		}
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
		var myObject = document.getElementById("detailRows");
		var myTable;

		if (myObject != null) {
			if (myObject.length > 1) {
				myTable = myObject[browseRow];
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
					} else if (	myCell.childNodes[ix].nodeName == 'DIV') {
						var myDiv = myCell.childNodes[ix];

						myDiv.className = className;
						if (myDiv.hasChildNodes()) {
							for (var ixx = myDiv.childNodes.length - 1; ixx >= 0 ; ixx--) {
								if (myDiv.childNodes[ixx].nodeName == 'TABLE') {
									var myTable2 = myDiv.childNodes[ixx];

									for (var ir = myTable2.rows.length - 1; ir >= 0; ir--) {
										setRowClassName(myTable2.rows[ir], className);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	function displayPageNavigation() {
		displayArea("goToPage");
		displayArea("pageNavigation");
		//displayArea("filterOptions");
		displayArea("nextPage");

		if (currentPage != 1) {
			var pg = currentPage;
			var pageLink = "<a href=\"javascript: pageMe(1);\">1</a>";
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
			setInnerHTML("pageLink1", pageLink);
			setInnerHTML("pageLink" + pg, "<b>" + pg + "</b>");

			frm.go_to_pg.value = "";

			currentPage = pg;
			rowCount = iend - ibegin + 1;

			setNewPageOptions();
		}
	}
	
	function pageRetrieveAllRegistersMode(pg)
	{
		if (pg < 1 || pg > pageCount) {
			alert("You must select or enter a valid page number.");
			return;
		}
		if (frm.browsePage.value=="/browse/browse_popup.jsp") {
			if (frm.fromPage.value=="reportqueue")
				addUsers();
		}
		frm.newPage.value = pg;
		if(frm.browsePage.value == "/browse/item_browse.jsp") {
			returnNextItems(frm.browsePage.value);
		} else {
			doSubmit(frm.browsePage.value, "BrowseSetPage");
		}
	}

	function pageMe(pg) {
		if (pg < 1 || pg > pageCount) {
			alert("You must select or enter a valid page number.");
			return;
		}
		if (frm.browsePage.value=="/browse/browse_popup.jsp") {
			if (frm.fromPage.value=="reportqueue")
				addUsers();
		}
		frm.newPage.value = pg;
		if(frm.browsePage.value == "/browse/item_browse.jsp") {
			returnNextItems(frm.browsePage.value);
		} else {
			//doSubmit(frm.browsePage.value, "BrowseSetPage");
			doSubmit(frm.browsePage.value, "BrowseGetNextPageFromDB");
		}
 /*
		var pageLink = "<a href=\"javascript: pageMe(" + currentPage + ");\">" + currentPage + "</a>";
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
		setInnerHTML("pageLink" + pg, "<b>" + pg + "</b>");

		hideArea("page" + currentPage);
		displayArea("page" + pg);

		frm.go_to_pg.value = "";

		currentPage = pg;
		setNewPageOptions();
*/
	}

	function setNewPageOptions() {
	}

	function previousPage() {
		pageMe(currentPage - 1);
	}

	function nextPage() {
		pageMe(currentPage + 1);
	}

	function sortMe(colname) {
		var sortOrder = "ASC";
		var columns = document.getElementsByName("colname");
		var sorts = document.getElementsByName("sort");

		if (frm.sortedOrder)
		{
			var sortedOrder = frm.sortedOrder.value;
			if (sortedOrder == "ASC") { sortOrder = "DESC"; }
		}

		if (columns != null) {
			if (columns.length > 1) {
				for (var i=0; i < columns.length; i++) {
					if (columns[i].value == colname) {
						if (sorts[i].value.toUpperCase() == "ASC") {
							sortOrder = "DESC";
						}
					}
					sorts[i].value = "N";
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

		sortMeOrder(colname, sortOrder);
	}

	function sortMeOrder(colname, sortOrder) {
		var columns = frm.elements.item("colname");

		if (isEmpty(sortOrder)) {
			sortOrder = "ASC";
		}
		var myCell = document.getElementById("filterFields");
		var filterFields = "";

		filterFields = filterFields + "<input type=hidden name=\"" + "colname" + "\" value=\"" + colname  + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "operator\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "filter_txt\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "logicalOperator\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "originalFilter\" value=\"N\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "sort\" value=\"" + sortOrder + "\">";

		myCell.innerHTML = filterFields + myCell.innerHTML;
		frm.browseName.value = browseName;
		frm.pageSize.value = pageSize;
		doSubmit(frm.browsePage.value, 'BrowseFilterSort');
	}
	
	function clearSortMe() {
		var myCell = document.getElementById("filterFields");
		myCell.innerHTML = '';
	}
	
	function findMe() {
		if (isEmpty(frm.finder.value)) {
			alert("You have not specified a value to be found.");
			return;
		}
		doSubmit(frm.browsePage.value, "BrowseFindSortedValue");
	}

	function nextGroup() {
		var group = parseInt (currentGroup) + 1;
		var ibegin = parseInt (((group * 5) - 5)) + 1;
		pageMe(ibegin);
	}

	function previousGroup() {
		var group = parseInt (currentGroup) - 1;
		var ibegin = parseInt((group * 5) - 5) + 1;
		pageMe(ibegin);
	}

	function validateForm() {
		var setCheckboxValues = false;

		if (rowCount == 1) {
			if (browser == "NS6") {
				if (frm.c_checkbox != undefined && frm.Input_checkbox != undefined) {
					setCheckboxValues = true;
				}
			} else {
				if (frm.c_checkbox && frm.Input_checkbox) {
					setCheckboxValues = true;
				}
			}
		} else {
			if (browser == "NS6") {
				if (frm.c_checkbox != undefined && frm.Input_checkbox != undefined && frm.c_checkbox[0] != undefined && frm.Input_checkbox[0] != undefined) {
					setCheckboxValues = true;
				}
			} else {
				if (frm.c_checkbox && frm.Input_checkbox && frm.c_checkbox[0] && frm.Input_checkbox[0]) {
					setCheckboxValues = true;
				}
			}
		}

		if (setCheckboxValues) {
			if (rowCount > 1) {
				for (i = 0; i < rowCount; i++) {
					if (frm.c_checkbox[i] && frm.Input_checkbox[i]) {
						if (frm.c_checkbox[i].checked) {
							frm.Input_checkbox[i].value = "Y";
						} else  {
							frm.Input_checkbox[i].value = "";
						}
					}
				}
			} else {
				if (frm.c_checkbox && frm.Input_checkbox) {
					if (frm.c_checkbox.checked) {
						frm.Input_checkbox.value = "Y";
					} else  {
						frm.Input_checkbox.value = "";
					}
				}
			}
		}

		return true;
	}