	function filterMe()
	{
		var filterValue = frm.filter.value;

		if ( isEmpty(filterValue ) )
		{
			alert("Don't you want to specify what you want to filter first?")
			frm.filter.focus();
			return;
		}

		var colname = frm.dbcolumn[frm.dbcolumn.selectedIndex].value;
		var operator = frm.filtor[frm.filtor.selectedIndex].value;

		setFilter(colname, operator, filterValue);
		browse(browseName);
/*
		frm.where.value = f + " " + o + " '" + s + "'";
		frm.filter.value = " '" + s + "'";
		if ( ( s.substring(s.length - 1, s.length) == "%" ) && ( s.substring(0, 1) == "%" ) )
		{
			//o = s.substring(1, s.length - 1);
			//s = "POS(UPPER(String(" + f + ")),'" + o + "') > 0";
			s = "(" + f + " LIKE '" + s + "')";
			frm.where.value = s;
		}
		else if ( s.substring(s.length - 1, s.length) == "%" )
		{
			//o = s.substring(0, s.length - 1);
			//s = "LEFT(UPPER(String(" + f + "))," + o.length + ") = '"+ o + "'";
			s = "(" + f + " LIKE '" + s + "')";
			frm.where.value = s;
		}
		else if ( s.substring(0, 1) == "%" )
		{
			//o = s.substring(1, s.length);
			//s = "RIGHT(UPPER(String(" + f + "))," + o.length + ") = '"+ o + "'";
			s = "(" + f + " LIKE '" + s + "')";
			frm.where.value = s;
		}
		//else if ( (f == "sdt_std_text") || (f == "rqh_internal_comments") ) {
		else if ( (f == "std_text.std_text") || (f == "requisition_header.internal_comments") ) {
			//o = s;
			//s = "POS(UPPER(String(" + f + ")),'" + o + "') > 0";
			s = "(" + f + " LIKE '%" + s + "%')";
			frm.where.value = s;
		}
*/

	}

	function resetMe() {
		var myCell = document.getElementById("filterFields");
		myCell.innerHTML = "";

		frm.browseName.value = browseName;
		frm.pageSize.value = pageSize;
		browse(browseName);
	}

	function resetOriginal() {
		if (browseName.indexOf("rfq-bidboard-posts") >= 0) {
			doSubmit('/browse/rfq_browse_filter_options.jsp', 'DoNothing');
		} else {
			browseFilter(browseName);
		}
	}

	function setOriginalFilter(colname, operator, filterValue) {
		if ( isEmpty(filterValue ) ) {
			return;
		}

		var myCell = document.getElementById("filterFields");
		var filterFields = myCell.innerHTML;

		filterFields = filterFields + "<input type=hidden name=\"" + "colname" + "\" value=\"" + colname  + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "operator\" value=\"" + operator + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "filter_txt\" value=\"" + filterValue + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "logicalOperator\" value=\"AND\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "originalFilter\" value=\"Y\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "sort\" value=\"N\">";

		myCell.innerHTML = filterFields;
	}

	function setFilter(colname, operator, filterValue) {
		if ( isEmpty(filterValue ) ) {
			return;
		}

		var myCell = document.getElementById("filterFields");
		var filterFields = myCell.innerHTML;

		filterFields = filterFields + "<input type=hidden name=\"" + "colname" + "\" value=\"" + colname  + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "operator\" value=\"" + operator + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "filter_txt\" value=\"" + filterValue + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "logicalOperator\" value=\"AND\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "originalFilter\" value=\"N\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "sort\" value=\"N\">";

		myCell.innerHTML = filterFields;
	}

	function setOriginalFilterWithLogicalOper(colname, operator, filterValue, logicalOperator) {
		if ( isEmpty(filterValue ) ) {
			return;
		}

		var myCell = document.getElementById("filterFields");
		var filterFields = myCell.innerHTML;

		filterFields = filterFields + "<input type=hidden name=\"" + "colname" + "\" value=\"" + colname  + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "operator\" value=\"" + operator + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "filter_txt\" value=\"" + filterValue + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "logicalOperator\" value=\"" + logicalOperator + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "originalFilter\" value=\"Y\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "sort\" value=\"N\">";

		myCell.innerHTML = filterFields;
	}

	function setFilterWithLogicalOper(colname, operator, filterValue, logicalOperator) {
		if ( isEmpty(filterValue ) ) {
			return;
		}

		var myCell = document.getElementById("filterFields");
		var filterFields = myCell.innerHTML;

		filterFields = filterFields + "<input type=hidden name=\"" + "colname" + "\" value=\"" + colname  + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "operator\" value=\"" + operator + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "filter_txt\" value=\"" + filterValue + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "logicalOperator\" value=\"" + logicalOperator + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "originalFilter\" value=\"N\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "sort\" value=\"N\">";

		myCell.innerHTML = filterFields;
	}

	function setOriginalSort(colname, sortOrder) {
		if (isEmpty(colname)) {
			return;
		}
		var myCell = document.getElementById("filterFields");
		var filterFields = myCell.innerHTML;

		filterFields = filterFields + "<input type=hidden name=\"" + "colname" + "\" value=\"" + colname  + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "operator\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "filter_txt\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "logicalOperator\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "originalFilter\" value=\"Y\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "sort\" value=\"" + sortOrder + "\">";

		myCell.innerHTML = filterFields;
	}

	function removeFilter(colname) {
		var columns = frm.elements.item("colname");

		if (columns != null) {
			if (columns.length > 1) {
				for (var i=(columns.length - 1); i >= 0; i--) {
					if (columns(i).value == colname) {
						frm.elements.item("colname")(i).removeNode(true);
						frm.elements.item("operator")(i).removeNode(true);
						frm.elements.item("filter_txt")(i).removeNode(true);
						frm.elements.item("logicalOperator")(i).removeNode(true);
						frm.elements.item("originalFilter")(i).removeNode(true);
						frm.elements.item("sort")(i).removeNode(true);
					}
				}
			} else if (columns.value == colname) {
				frm.elements.item("operator").removeNode(true);
				frm.elements.item("filter_txt").removeNode(true);
				frm.elements.item("logicalOperator").removeNode(true);
				frm.elements.item("originalFilter").removeNode(true);
				frm.elements.item("sort").removeNode(true);
			}
		}
		browse(browseName);
	}

	function itemSearch() {
		var itemType = "";
		var types = frm.elements.item("as_item_type");

		for (var i=0; i < types.length; i++) {
			if (frm.as_item_type[i].checked) {
				itemType = frm.as_item_type[i].value
			}
		}

		if (itemType == "CAT") {
			frm.browseName.value = "catalogitem";
		} else if (itemType == "INV") {
			frm.browseName.value = "invitem-invlocation";
		} else if (itemType == "REQ") {
			unavailable();
			return;
		}
		setItemKeywordFilterOptions(itemType);

		doSubmit('browse/item_browse.jsp', 'BrowseRetrieve');
	}

	function setItemKeywordFilterOptions(itemType) {
		var keywords = frm.as_keywords.value.toUpperCase();

		if ( !isEmpty(keywords) ) {
			keywords = "%" + keywords + "%";
		}

		if (itemType == "CAT") {
			setOriginalFilter("CatalogItem_description", "LIKE", keywords);
		} else if (itemType == "INV") {
			setOriginalFilter("InvItem_description", "LIKE", keywords);
			setOriginalFilter("InvLocation_id_itemLocation", "=", itemLocation);
		} else if (itemType == "REQ") {
			// add filters for requisition items
		}
	}

	function clearFilters() {
		var myCell = document.getElementById("filterFields");
		if (myCell != null && myCell != undefined) {
			myCell.innerHTML = "";
		}

		var columns = frm.elements.item("colname");
		if (columns != null) {
			if (columns.length > 1) {
				for (var i=(columns.length - 1); i >= 0; i--) {
					frm.elements.item("colname")(i).removeNode(true);
				}
			} else {
				frm.elements.item("colname").removeNode(true);
			}
		}

		columns = frm.elements.item("operator");
		if (columns != null) {
			if (columns.length > 1) {
				for (var i=(columns.length - 1); i >= 0; i--) {
					frm.elements.item("operator")(i).removeNode(true);
				}
			} else {
				frm.elements.item("operator").removeNode(true);
			}
		}

		columns = frm.elements.item("filter_txt");
		if (columns != null) {
			if (columns.length > 1) {
				for (var i=(columns.length - 1); i >= 0; i--) {
					frm.elements.item("filter_txt")(i).removeNode(true);
				}
			} else {
				frm.elements.item("filter_txt").removeNode(true);
			}
		}

		columns = frm.elements.item("logicalOperator");
		if (columns != null) {
			if (columns.length > 1) {
				for (var i=(columns.length - 1); i >= 0; i--) {
					frm.elements.item("logicalOperator")(i).removeNode(true);
				}
			} else {
				frm.elements.item("logicalOperator").removeNode(true);
			}
		}

		columns = frm.elements.item("originalFilter");
		if (columns != null) {
			if (columns.length > 1) {
				for (var i=(columns.length - 1); i >= 0; i--) {
					frm.elements.item("originalFilter")(i).removeNode(true);
				}
			} else {
				frm.elements.item("originalFilter").removeNode(true);
			}
		}

		columns = frm.elements.item("sort");
		if (columns != null) {
			if (columns.length > 1) {
				for (var i=(columns.length - 1); i >= 0; i--) {
					frm.elements.item("sort")(i).removeNode(true);
				}
			} else {
				frm.elements.item("sort").removeNode(true);
			}
		}
	}