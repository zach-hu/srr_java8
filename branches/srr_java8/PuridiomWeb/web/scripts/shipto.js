	function addUp ()
	{
		var lnqty = 0;
		var totqty = 0;
		var temp = 0;

		if (maxRows > 1)
		{
			for (var ii=0; ii < maxRows; ii++)
			{
				lnqty = eval(nfilter(frm.ShipTo_quantity[ii]));
				if (frm.ShipTo_quantity[ii].value != '')
				{
					frm.ShipTo_quantity[ii].value = nformat(lnqty,qtyDecimals);
				}
				totqty = totqty + lnqty;
			}
		}
		else if (maxRows == 1)
		{
			if (frm.ShipTo_quantity[0]) {
				lnqty = eval(nfilter(frm.ShipTo_quantity[0]));
				if (frm.ShipTo_quantity[0].value != '')
				{
					frm.ShipTo_quantity[0].value = nformat(lnqty,qtyDecimals);
				}
			} else {
				lnqty = eval(nfilter(frm.ShipTo_quantity));
				if (frm.ShipTo_quantity.value != '')
				{
					frm.ShipTo_quantity.value = nformat(lnqty,qtyDecimals);
				}
			}
			totqty = totqty + lnqty;
		}

		frm.as_tot_qty.value = nformat(totqty,qtyDecimals);

		defaultQty = eval(frm.as_line_qty.value - frm.as_tot_qty.value);
		if (defaultQty < 0)
		{
			defaultQty = 0;
		}
	}

	function setDate()
	{
		if (maxRows > 1)
		{
			for (var ii=0; ii < maxRows; ii++)
			{
				if ( (isRowEmpty(ii) == false) && ( isEmpty(frm.ShipTo_shipDate[ii].value) || frm.ShipTo_shipDate[ii].value == '1900-1-1') )
				{
					frm.ShipTo_shipDate[ii].value = defaultDate;
				}
			}
		}
		else if (maxRows == 1)
		{
			if ( (isRowEmpty(ii) == false) && ( isEmpty(frm.ShipTo_shipDate.value) || frm.ShipTo_shipDate.value == '1900-1-1') )
			{
				frm.ShipTo_shipDate.value = defaultDate;
			}
		}
	}

	function isRowEmpty (k)
	{
		if (frm.ShipTo_shipToCode[k])
		{
			if ( isEmpty(frm.ShipTo_shipToCode[k].value) == false ) return false;
			if ( isEmpty(frm.ShipTo_attention[k].value) == false ) return false;
		}
		else if (frm.ShipTo_shipToCode && k == 0)
		{
			if ( isEmpty(frm.ShipTo_shipToCode.value) == false ) return false;
			if ( isEmpty(frm.ShipTo_attention.value) == false ) return false;
		}
		return ( true );
	}

	function setQty(row)
	{
		var i = maxRows;
		var shipQty = eval(nfilter(frm.as_tot_qty));
		var lineQty = eval(nfilter(frm.as_line_qty));

		if ((shipQty < lineQty) && defaultQty > 0) {
			if (browser == "NS6") {
				if (row == undefined) { row = i; }
			}
			else {
				if (row == null) { row = i; }
			}

			if (frm.ShipTo_quantity[row])
			{
				if (eval(nfilter(frm.ShipTo_quantity[row])) <= 0) {
					frm.ShipTo_quantity[row].value = nformat(defaultQty,qtyDecimals);
				}
			}
			else
			{
				if (!frm.ShipTo_quantity) {
					addNew();
				}
				if (eval(nfilter(frm.ShipTo_quantity)) <= 0) {
					frm.ShipTo_quantity.value = nformat(defaultQty,qtyDecimals);
				}
			}
			defaultQty = 0;
			addUp();
		}
	}

	function setCurrentRow(row) {
		currentRow = row;
	}

	function deleteMeConfirmed(row) {
		var currentRows = maxRows;

		if (currentRows == 0) {
			return;
		}
		else if (currentRows > 1) {
			for (var i = row; i < (currentRows - 1); i++) {
				var code = frm.ShipTo_shipToCode[i + 1].value;
				var addr = frm.Address_addressLine1[i + 1].value;
				var qty = frm.ShipTo_quantity[i + 1].value;
				var attn = frm.ShipTo_attention[i + 1].value;
				var shipDate = frm.ShipTo_shipDate[i + 1].value;

				frm.ShipTo_shipToCode[i].value = code;
				frm.Address_addressLine1[i].value = addr;
				frm.ShipTo_quantity[i].value = qty;
				frm.ShipTo_attention[i].value = attn;
				frm.ShipTo_shipDate[i].value = shipDate;
			}
		}

		myTable = document.getElementById("shp_schedule");

		deleteRow(myTable, currentRows - 1);

		if (currentRows <= 1) {
			frm.deleteall.value = "TRUE";
		}
		maxRows--;
		addUp();
	}

	function deleteMe(row) {
		if (confirm("Are you sure you wish to delete \"Shipping Schedule\" line " + (row + 1) + "?")) {
			deleteMeConfirmed(row);
		}
	}

	function deleteAll()
	{
		if (confirm("Are you sure you wish to delete all Shipping Schedules?"))
		{
			frm.deleteall.value = "TRUE";
			myTable = document.getElementById("shp_schedule");

			deleteAllRows(myTable);
			maxRows = 0;
		}
		addUp();
	}

	function addNew()
	{
		myTable = document.getElementById("shp_schedule");
		count = maxRows;
		maxRows++;

		myRow = createRow(myTable);

		myCell = createCell(myRow);
		myCell.innerHTML = "&nbsp";

		myCell = createCell(myRow);
		id = "shp_code";
		myCell.id = id;
		myCell.width = "15%";
		myCell.innerHTML = "<input type=text name=\"ShipTo_shipToCode\" size=15 maxlength=15 value=\"\" onchange=\"upperCase(this); getNewInfo('ShipTo_shiptoCode', this, " + count + ");\" ONFOCUS='setCurrentRow(" + count + ");' tabIndex=" + eval((count*4)+1) + ">";

		myCell = createCell(myRow);
		id = "shp_addr";
		myCell.id = id;
		myCell.width = "25%";
		myCell.innerHTML = "<input type=text name=\"Address_addressLine1\" size=25 maxlength=30 value=\"\" onfocus=\"this.blur();\" disabled >";

		myCell = createCell(myRow);
		id = "shp_qty";
		myCell.id = id;
		myCell.width = "15%";
		myCell.innerHTML = "<input type=text name=\"ShipTo_quantity\" size=15 maxlength=25 value=\"\" style=\"text-align:right\" onchange=\"addUp();\"\" ONFOCUS='setCurrentRow("+count+");' tabIndex=" + eval((count*4)+2) + ">";

		myCell = createCell(myRow);
		id = "shp_attn";
		myCell.id = id;
		myCell.width = "25%";
		myCell.innerHTML = "<input type=text name=\"ShipTo_attention\" size=25 maxlength=40 value=\"\" ONFOCUS='setCurrentRow(" + count + ");'  tabIndex=" + eval((count*4)+3) + ">";

		myCell = createCell(myRow);
		id = "shp_info";
		myCell.id = id;
		myCell.width = "15%";
		myCell.innerHTML = "<input type=text name=\"ShipTo_shipDate\" size=15 maxlength=10 value=\"" + defaultDate + "\" tabIndex=" + eval((count*4)+4) + "><input type=hidden name=\"ShipTo_shipToPriority\" value=" + (eval(count) + 1) + ">";

		myCell = createCell(myRow);
		id = "shp_cal";
		myCell.id = id;
		myCell.width = "25%";
		myCell.innerHTML = "<a href=\"javascript: getDateField(" + count + "); void(0);\"><img src=\"" + contextPath + "/images/calendar.gif\" border=0></a>";

		if (!displayReqdDate) {
			hideArea('shp_info');
			hideArea('shp_cal');
		}

		myCell = createCell(myRow);
		id = "shp_del_" + count;
		myCell.id = id;
		myCell.width = "5%";
		myCell.innerHTML = "<a href=\"javascript:deleteMe(" + count + "); void(0);\"><img name=\"deletethis\" src=\"" + contextPath + "/images/delete.gif\" alt=\"Delete\" border=0></a>";

		myCell = createCell(myRow);
		myCell.innerHTML = "&nbsp";

		if (count >= 1) {
			frm.ShipTo_shipToCode[count].focus();
			if (browser != "NS6") {
				frm.ShipTo_shipToCode[count].fireEvent("onfocus");
			}
		} else {
			frm.ShipTo_shipToCode.focus();
			if (browser != "NS6") {
				frm.ShipTo_shipToCode.fireEvent("onfocus");
			}
		}

		frm.deleteall.value = "FALSE";
		currentRow = count;
	}

	function validateRows() {
		var qtyValid = true;
		var codeValid = true;
		var dateValid = true;
		var alertMessage = "";

		for (var row = maxRows-1; row >= 0; row--) {
			if (isRowEmpty(row)) {
				deleteMeConfirmed(row);
			} else {
				if (frm.ShipTo_shipToCode[row]) {
					if ( isEmpty(frm.ShipTo_shipToCode[row].value) == true ) {
						codeValid = false;
					}
				} else if (frm.ShipTo_shipToCode && row == 0) {
					if ( isEmpty(frm.ShipTo_shipToCode.value) == true ) {
						codeValid = false;
					}
				}
				if (frm.ShipTo_quantity[row]) {
					if ( isEmpty(frm.ShipTo_quantity[row].value) == true || eval(frm.ShipTo_quantity[row].value) <= 0) {
						qtyValid = false;
					}
				} else if (frm.ShipTo_quantity && row == 0) {
					if ( isEmpty(frm.ShipTo_quantity.value) == true || eval(frm.ShipTo_quantity.value) <= 0) {
						qtyValid = false;
					}
				}
				if (frm.ShipTo_shipDate[row]) {
					if (frm.ShipTo_shipDate[row] && !chkdate(frm.ShipTo_shipDate[row])) {
						dateValid = false;
					}
				} else if (frm.ShipTo_shipDate && row == 0) {
					if (frm.ShipTo_shipDate && !chkdate(frm.ShipTo_shipDate)) {
						dateValid = false;
					}
				}
			}
		}

		if (!codeValid) {
			alertMessage = alertMessage + "\nYou must enter a Ship To Code for each shipping schedule record.";
		}
		if (!qtyValid) {
			alertMessage = alertMessage +  "\nYou must enter a Ship To Quantity for each shipping schedule record.";
		}
		if (!dateValid) {
			alertMessage = alertMessage +  "\nYou must enter a valid Required By date for each shipping schedule record.";
		}

		if (!isEmpty(alertMessage)) {
			alert("Please fix the following problems:\n" + alertMessage);
			return false;
		}
		return true;
	}

	function validateForm(){
		var handler = frm.handler.value;

		if (handler.indexOf("ShipToUpdate") >= 0) {
			if (!validateRows()) {
				return false;
			}
			var shipQty = frm.as_tot_qty.value;
			var lineQty = frm.as_line_qty.value;
			if (shipQty != lineQty && shipQty != 0) {
				alert ('Please fix the following problems:\nTotal Ship Quantity (' + shipQty + ') must equal Total Line Quantity (' + lineQty + ').');
				return false;
			}
		}
		return true;
	}