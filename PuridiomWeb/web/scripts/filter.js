	function filterMe()
	{
		var filterValue = trim(frm.filter);
		if ( isEmpty(filterValue ) )
		{
			alert("Don't you want to specify what you want to filter first?")
			frm.filter.focus();
			return;
		}

		var colname = frm.dbcolumn[frm.dbcolumn.selectedIndex].value;
		var operator = frm.filtor[frm.filtor.selectedIndex].value;

		setFilter(colname, operator, filterValue);

		frm.browseName.value = browseName;
		frm.pageSize.value = pageSize;
		doSubmit(frm.browsePage.value, 'BrowseFilterSort');
	}

	function resetMe() {
		var myCell = document.getElementById("filterFields");
		myCell.innerHTML = "";

		frm.browseName.value = browseName;
		frm.pageSize.value = pageSize;
		doSubmit(frm.browsePage.value, 'BrowseFilterSort');
	}

	function resetOriginal() {
		/**  for supplier browse if the user filtered on commodity, if they reset the filter it should call the regular supplier browse  **/
		if (browseName == "supplier_mgt_commodity")
		{
			browseName = "supplier_mgt";
		}

		var filterType = "ADVANCED";
		if (frm.filterType) {
			filterType = frm.filterType.value;
		}
		if (filterType == "SIMPLE") {
			browseFilter(browseName, "/browse/browse_simple_filter_options.jsp");
		} else {
			browseFilter(browseName);
		}
	}

	function setOriginalFilter(colname, operator, filterValue) {
		setAdvancedFilter(colname, operator, filterValue, "AND", "Y", "N");
	}

	function setFilter(colname, operator, filterValue) {
		setAdvancedFilter(colname, operator, filterValue, "AND", "N", "N");
	}

	function setAdvancedFilter(colname, operator, filterValue, logicalOperator, originalFilter, sort) {
		if ( isEmpty(filterValue ) ) {
			return;
		}
		if (filterValue.indexOf("%") >= 0 || colname == "CatalogItem_description") {
			if (operator == "<>") {
				operator = "NOT LIKE";
			} else {
				operator = "LIKE";
			}
			filterValue = filterValue.toUpperCase();
		}

		var myCell = document.getElementById("filterFields");
		var filterFields = myCell.innerHTML;

		filterFields = filterFields + "<input type=hidden name=\"" + "colname" + "\" value=\"" + colname  + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "operator\" value=\"" + operator + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "filter_txt\" value=\"" + filterValue + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "logicalOperator\" value=\"" + logicalOperator + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "originalFilter\" value=\"" + originalFilter + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "sort\" value=\"" + sort + "\">";

		myCell.innerHTML = filterFields;
	}

	function removeFilter(colname) {
		var columns = frm.elements.item("colname");

		if (columns != null) {
			if (columns.length > 1) {
				for (var i=(columns.length - 1); i >= 0; i--) {
					if (columns(i).value == colname) {
//						frm.elements.item("colname")(i).removeNode(true);
//						frm.elements.item("operator")(i).removeNode(true);
//						frm.elements.item("filter_txt")(i).removeNode(true);
//						frm.elements.item("logicalOperator")(i).removeNode(true);
//						frm.elements.item("originalFilter")(i).removeNode(true);
//						frm.elements.item("sort")(i).removeNode(true);
						el = frm.elements.item("colname")(i) ;
						el.parentNode.removeChild(el) ;
						el = frm.elements.item("operator")(i) ;
						el.parentNode.removeChild(el) ;
						el = frm.elements.item("filter_txt")(i) ;
						el.parentNode.removeChild(el) ;
						el = frm.elements.item("logicalOperator")(i) ;
						el.parentNode.removeChild(el) ;
						el = frm.elements.item("originalFilter")(i) ;
						el.parentNode.removeChild(el) ;
						el = frm.elements.item("sort")(i) ;
						el.parentNode.removeChild(el) ;

					}
				}
			} else if (columns.value == colname) {
//				frm.elements.item("colname").removeNode(true);
//				frm.elements.item("operator").removeNode(true);
//				frm.elements.item("filter_txt").removeNode(true);
//				frm.elements.item("logicalOperator").removeNode(true);
//				frm.elements.item("originalFilter").removeNode(true);
//				frm.elements.item("sort").removeNode(true);

				el = frm.elements.item("colname") ;
				el.parentNode.removeChild(el) ;
				el = frm.elements.item("operator") ;
				el.parentNode.removeChild(el) ;
				el = frm.elements.item("filter_txt") ;
				el.parentNode.removeChild(el) ;
				el = frm.elements.item("logicalOperator") ;
				el.parentNode.removeChild(el) ;
				el = frm.elements.item("originalFilter") ;
				el.parentNode.removeChild(el) ;
				el = frm.elements.item("sort") ;
				el.parentNode.removeChild(el) ;
			}
		}
		frm.browseName.value = browseName;
		frm.pageSize.value = pageSize;
		doSubmit(frm.browsePage.value, 'BrowseFilterSort');
	}

	function itemSearch() {
		var itemType = "";
		//var types = frm.elements.item("as_item_type");
		var types = document.getElementsByName("as_item_type");
		var newInputField = "";

		if (types.length > 1) {
			for (var i=0; i < types.length; i++) {
				if (frm.as_item_type[i].checked) {
					itemType = frm.as_item_type[i].value;
				}
			}
		} else {
			itemType = frm.as_item_type.value;
		}

		if (itemType == "CAT") {
			if(orgId=="WPC08P")
			{
				//setAdvancedFilter("CatalogSecurity_accessId", "LIKE", userCode, "OR", "Y", "N");
				//setAdvancedFilter("CatalogSecurity_accessId", "LIKE", userDepartment, "OR", "Y", "N");
				//setAdvancedFilter("CatalogSecurity_accessId", "LIKE", userNameUdf1, "OR", "Y", "N");
				//setAdvancedFilter("CatalogSecurity_accessId", "LIKE", userNameUdf2, "OR", "Y", "N");
				//setAdvancedFilter("CatalogSecurity_accessId", "LIKE", userNameUdf3, "OR", "Y", "N");

				newInputField += "<input type='hidden' name='isCatalogSecurityActive' value='Y'>";
			    setHiddenFields(newInputField);

			}
			frm.browseName.value = "catalogitem";
		} else if (itemType == "INV") {
			frm.browseName.value = "invitem-invlocation";
		} else if (itemType == "REQ") {
			unavailable();
			return;
		} else if (itemType == "DO") {
			setOriginalFilter("PoLine_poNumber", "=", frm.PoHeader_poNumber.value);
			setOriginalFilter("PoLine_releaseNumber", "=", "0");
			frm.browseName.value = "deliveryitem";
		} else if (itemType == "INTERNET") {
			browseExternalCatalog("YAHOO");
			return;
		} else if (itemType == "CON") {
			frm.browseName.value = "consolidateditem";
		}
		setItemKeywordFilterOptions(itemType);

		doSubmit('browse/item_browse.jsp', 'BrowseRetrieve;CatalogInactive');
	}

	function setItemKeywordFilterOptions(itemType) {
		var itemNumber = "";
		if (frm.as_itemNumber != undefined) {
			itemNumber = frm.as_itemNumber.value.toUpperCase();
		}
		var keywords = frm.as_keywords.value.toUpperCase();

		itemNumber = replaceForItemNumber(itemNumber);
		keywords = replaceForKeywords(keywords);

/*		if ( !isEmpty(itemNumber) ) {
			var temp = "%";
			var count = itemNumber.length;
			for (var i = 0; i < count; i++)
			{
				temp = temp + itemNumber.substring(i, i+1);
				temp = temp + "%";
			}
			itemNumber = temp;
		}
*/
		if ( !isEmpty(keywords) ) {
			if( orgId=="BLY07P" && keywords.indexOf("%") >= 0 ) {
				keywords = keywords;
			}
			else {
				keywords = "%" + keywords + "%";
			}
		}

		if (itemType == "CAT") {
			if (!isEmpty(itemNumber)) {
				setOriginalFilter("CatalogItem_id_itemNumber", "LIKE", itemNumber);
			}
			if (!isEmpty(keywords)) {
				if (isEmpty(itemNumber)) {
					//setAdvancedFilter("CatalogItem_id_itemNumber", "LIKE", keywords, "OR", "Y", "N");
					setOriginalFilter("CatalogItem_description", "LIKE", "%" + keywords + "%");
				} else {
					setOriginalFilter("CatalogItem_description", "LIKE", "%" + keywords + "%");
				}
			}
		} else if (itemType == "INV") {
			setOriginalFilter("InvItem_itemNumber", "LIKE", itemNumber);
			if (!isEmpty(keywords)) {
				setOriginalFilter("InvItem_description", "LIKE", "%" + keywords + "%");
			}
			setOriginalFilter("InvLocation_id_itemLocation", "=", itemLocation);
		} else if (itemType == "REQ") {
			// add filters for requisition items
		} else if (itemType == "CON") {
			if (!isEmpty(itemNumber)) {
				setOriginalFilter("ConsolidatedItem_id_itemNumber", "LIKE", itemNumber);
			}
			if (!isEmpty(keywords)) {
				if (isEmpty(itemNumber)) {
					//setAdvancedFilter("ConsolidatedItem_id_itemNumber", "LIKE", keywords, "OR", "Y", "N");
					setOriginalFilter("ConsolidatedItem_description", "LIKE", "%" + keywords + "%");
				} else {
					setOriginalFilter("ConsolidatedItem_description", "LIKE", "%" + keywords + "%");
				}
			}
		}
	}

	function clearFilters() {
		var myCell = document.getElementById("filterFields");
		if (myCell != null && myCell != undefined) {
			myCell.innerHTML = "";
		}

		//var columns = frm.elements.item("colname");
		var columns = document.getElementsByName("colname");
		
		if (columns != null) {
			if (columns.length > 1) {
				for (var i=(columns.length - 1); i >= 0; i--) {
//					frm.elements.item("colname")(i).removeNode(true);
					/*el = frm.elements.item("colname")(i) ;
					el.parentNode.removeChild(el) ;*/
					el = document.getElementsByName("colname")[i] ;
					if(typeof(el)!="undefined" && el != null)
						el.parentNode.removeChild(el) ;
				}
			} else {
//				frm.elements.item("colname").removeNode(true);
					/*el = frm.elements.item("colname") ;
					el.parentNode.removeChild(el) ;*/
				el = document.getElementsByName("colname")[0];
				if(typeof(el)!="undefined" && el != null)
					el.parentNode.removeChild(el) ;
			}
		}

		//columns = frm.elements.item("operator");
		columns = document.getElementsByName("operator");
		if (columns != null) {
			if (columns.length > 1) {
				for (var i=(columns.length - 1); i >= 0; i--) {
//					frm.elements.item("operator")(i).removeNode(true);
					/*el = frm.elements.item("operator")(i) ;
					el.parentNode.removeChild(el) ;*/
					el = document.getElementsByName("operator")[i] ;
					if(typeof(el)!="undefined" && el != null)
						el.parentNode.removeChild(el) ;
				}
			} else {
//				frm.elements.item("operator").removeNode(true);
					/*el = frm.elements.item("operator") ;
					el.parentNode.removeChild(el) ;*/
				el = document.getElementsByName("operator")[0];
				if(typeof(el)!="undefined" && el != null)
					el.parentNode.removeChild(el) ;
			}
		}

		//columns = frm.elements.item("filter_txt");
		columns = document.getElementsByName("filter_txt");
		if (columns != null) {
			if (columns.length > 1) {
				for (var i=(columns.length - 1); i >= 0; i--) {
//					frm.elements.item("filter_txt")(i).removeNode(true);
					/*el = frm.elements.item("filter_txt")(i) ;
					el.parentNode.removeChild(el) ;*/
					el = document.getElementsByName("filter_txt")[i];
					if(typeof(el)!="undefined" && el != null)
						el.parentNode.removeChild(el) ;
				}
			} else {
//				frm.elements.item("filter_txt").removeNode(true);
					/*el = frm.elements.item("filter_txt") ;
					el.parentNode.removeChild(el) ;*/
				el = document.getElementsByName("filter_txt")[0];
				if(typeof(el)!="undefined" && el != null)
					el.parentNode.removeChild(el) ;
			}
		}

		//columns = frm.elements.item("logicalOperator");
		columns = document.getElementsByName("logicalOperator");
		if (columns != null) {
			if (columns.length > 1) {
				for (var i=(columns.length - 1); i >= 0; i--) {
//					frm.elements.item("logicalOperator")(i).removeNode(true);
					/*el = frm.elements.item("logicalOperator")(i) ;
					el.parentNode.removeChild(el) ;*/
					el = document.getElementsByName("logicalOperator")[i];
					if(typeof(el)!="undefined" && el != null)
						el.parentNode.removeChild(el) ;
				}
			} else {
//				frm.elements.item("logicalOperator").removeNode(true);
					/*el = frm.elements.item("logicalOperator") ;
					el.parentNode.removeChild(el) ;*/
				el = document.getElementsByName("logicalOperator")[0];
				if(typeof(el)!="undefined" && el != null)
					el.parentNode.removeChild(el) ;
			}
		}

		//columns = frm.elements.item("originalFilter");
		columns = document.getElementsByName("originalFilter");
		if (columns != null) {
			if (columns.length > 1) {
				for (var i=(columns.length - 1); i >= 0; i--) {
//					frm.elements.item("originalFilter")(i).removeNode(true);
					/*el = frm.elements.item("originalFilter")(i) ;
					el.parentNode.removeChild(el) ;*/
					el = document.getElementsByName("originalFilter")[i];
					if(typeof(el)!="undefined" && el != null)
						el.parentNode.removeChild(el) ;
				}
			} else {
//				frm.elements.item("originalFilter").removeNode(true);
					/*el = frm.elements.item("originalFilter") ;
					el.parentNode.removeChild(el) ;*/
				el = document.getElementsByName("originalFilter")[0];
				if(typeof(el)!="undefined" && el != null)
					el.parentNode.removeChild(el) ;
			}
		}

		//columns = frm.elements.item("sort");
		columns = document.getElementsByName("sort");
		if (columns != null) {
			if (columns.length > 1) {
				for (var i=(columns.length - 1); i >= 0; i--) {
//					frm.elements.item("sort")(i).removeNode(true);
					/*el = frm.elements.item("sort")(i) ;
					el.parentNode.removeChild(el) ;*/
					el = document.getElementsByName("sort")[i];
					if(typeof(el)!="undefined" && el != null)
						el.parentNode.removeChild(el) ;
				}
			} else {
//				frm.elements.item("sort").removeNode(true);
					/*el = frm.elements.item("sort") ;
					el.parentNode.removeChild(el) ;*/
				el = document.getElementsByName("sort")[0];
				if(typeof(el)!="undefined" && el != null)
					el.parentNode.removeChild(el) ;
			}
		}
	}

	function itemSearchWithUpdate(handlers) {
		var itemType = "";
		//var types = frm.elements.item("as_item_type");
		var types = document.getElementsByName("as_item_type");

		if (types.length > 1) {
			for (var i=0; i < types.length; i++) {
				if (frm.as_item_type[i].checked) {
					itemType = frm.as_item_type[i].value;
				}
			}
		} else {
			itemType = frm.as_item_type.value;
		}

		if (itemType == "CAT") {
			if(orgId=="WPC08P")
			{
				var newInputField = "<input type='hidden' name='isCatalogSecurityActive' value='Y'>";
			    setHiddenFields(newInputField);
			}
			frm.browseName.value = "catalogitem";
		} else if (itemType == "INV") {
			frm.browseName.value = "invitem-invlocation";
		} else if (itemType == "REQ") {
			unavailable();
			return;
		}
		setItemKeywordFilterOptions(itemType);

		var len = handlers.length;
		if (len > 0 && handlers.substring(len - 1, len) != ";") {
			handlers = handlers + ";";
		}
		handlers = handlers + "BrowseRetrieve";

		doSubmit('/browse/item_browse.jsp', handlers);
	}
