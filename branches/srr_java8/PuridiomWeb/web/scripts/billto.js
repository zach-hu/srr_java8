	function addUp ()
	{
		maxRows = myTable.rows.length;
		var lnqty = 0;
		var totqty = 0;
		var temp = 0;

		if (maxRows > 1)
		{
			for (var ii = 0; ii < maxRows; ii++)
			{
				lnqty = eval(nfilter(frm.BillTo_quantity[ii]));
				if (frm.BillTo_quantity[ii].value != '')
				{
					frm.BillTo_quantity[ii].value = nformat(lnqty,qtyDecimals);
				}
				totqty = totqty + lnqty;
			}
		}
		else if (maxRows == 1)
		{
			if (frm.BillTo_quantity[0]) {
				lnqty = eval(nfilter(frm.BillTo_quantity[0]));
				if (frm.BillTo_quantity[0].value != '') {
					frm.BillTo_quantity[0].value = nformat(lnqty,qtyDecimals);
				}
			} else {
				lnqty = eval(nfilter(frm.BillTo_quantity));
				if (frm.BillTo_quantity.value != '') {
					frm.BillTo_quantity.value = nformat(lnqty,qtyDecimals);
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

	function isRowEmpty (k)
	{
		if (frm.BillTo_billToCode[k])
		{
			if ( isEmpty(frm.BillTo_billToCode[k].value) == false ) return false;
			if ( isEmpty(frm.Address_addressLine1[k].value) == false ) return false;
			if ( isEmpty(frm.BillTo_attention[k].value) == false ) return false;
		}
		else if (frm.BillTo_billToCode && k == 0)
		{
			if ( isEmpty(frm.BillTo_billToCode.value) == false ) return false;
			if ( isEmpty(frm.Address_addressLine1.value) == false ) return false;
			if ( isEmpty(frm.BillTo_attention.value) == false ) return false;
		}
		return ( true );
	}

	function setQty(row)
	{
		var browser = browserTest();
		var i = eval(nfilter(frm.as_maxrows));
		var billQty = eval(nfilter(frm.as_tot_qty));
		var lineQty = eval(nfilter(frm.as_line_qty));

		if ((billQty < lineQty) && defaultQty > 0) {
			if (browser == "Netscape") {
				if (row == undefined) { row = i; }
			}
			else {
				if (row == null) { row = i; }
			}

			if (maxRows > 1)
			{
				if (eval(nfilter(frm.BillTo_quantity[row])) <= 0) {
					frm.BillTo_quantity[row].value = nformat(defaultQty,qtyDecimals);
				}
			}
			else
			{
				if (eval(nfilter(frm.BillTo_quantity)) <= 0) {
					frm.BillTo_quantity.value = nformat(defaultQty,qtyDecimals);
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
		var currentRows = myTable.rows.length;

		if (currentRows == 0) {
			return;
		}
		else if (currentRows > 1) {
			for (var i = row; i < (currentRows - 1); i++) {
				var code = frm.BillTo_billToCode[i + 1].value;
				var addr = frm.Address_addressLine1[i + 1].value;
				var qty = frm.BillTo_quantity[i + 1].value;
				var attn = frm.BillTo_attention[i + 1].value;

				frm.BillTo_billToCode[i].value = code;
				frm.Address_addressLine1[i].value = addr;
				frm.BillTo_quantity[i].value = qty;
				frm.BillTo_attention[i].value = attn;
			}
		}

		myTable.deleteRow(currentRows - 1);

		if (currentRows <= 1)
		{
			frm.deleteall.value = "TRUE";
		}
		addUp();
	}

	function deleteMe(row) {
		if (confirm("Are you sure you wish to delete \"Billing Schedule\" line " + (row+1) + "?")) {
			deleteMeConfirmed(row);
		}
	}

	function deleteAll(){
		if (confirm("Are you sure you wish to delete all Billing Schedules?"))
		{
			frm.deleteall.value = "TRUE";
			count = myTable.rows.length;
			for (i = 0; i < count; i++)
			{
				myTable.deleteRow(0);
			}
			addUp();
		}
	}

	function addNew()
	{
		count = myTable.rows.length;
		count = maxRows;
		maxRows++;

		myRow = createRow(myTable);

		myCell = createCell(myRow);
		id = "bil_code";
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"BillTo_billToCode\" size=15 maxlength=15 value=\"\" onchange=\"upperCase(this); getNewInfo('BillTo_billToCode', this, " + count + ");\" ONFOCUS='setCurrentRow(" + count + ");' tabIndex=" + eval((count*4)+1) + ">";

		myCell = createCell(myRow);
		id = "bil_addr";
		myCell.id = id;
		myCell.width = "200px";
		myCell.innerHTML = "<input type=text name=\"Address_addressLine1\" size=35 maxlength=30 value=\"\" onfocus=\"this.blur();\" disabled >";

		myCell = createCell(myRow);
		id = "bil_qty";
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"BillTo_quantity\" size=15 maxlength=25 value=\"\" style=\"text-align:right\" onchange=\"addUp("+ count + ");\" ONFOCUS='setCurrentRow(" + count + ");' tabIndex=" + eval((count*4)+2) + ">";

		myCell = createCell(myRow);
		id = "bil_attn";
		myCell.id = id;
		myCell.width = "200px";
		myCell.innerHTML = "<input type=text name=\"BillTo_attention\" size=30 maxlength=40 value=\"\" ONFOCUS='setCurrentRow(" + count + ");' tabIndex=" + eval((count*4)+3) + ">";

		myCell = createCell(myRow);
		id = "bil_del_" + count;
		myCell.id = id;
		myCell.width = "10px";
		myCell.innerHTML = "<a href=\"javascript:deleteMe(" + count + "); void(0)\"><img name=\"deletethis\" src=\"<%=contextPath%>/images/delete.gif\" alt=\"Delete\" border=0></a>";

		if (maxRows > 1) {
			frm.BillTo_billToCode[maxRows - 1].focus();
		} else {
			frm.BillTo_billToCode.focus();
		}
		frm.deleteall.value = "FALSE";
	}

	function validateRows() {
		var qtyValid = true;
		var codeValid = true;
		var alertMessage = "";

		for (var row = maxRows-1; row >= 0; row--) {
			if (isRowEmpty(row)) {
				deleteMeConfirmed(row);
			} else {
				if (frm.BillTo_billToCode[row]) {
					if ( isEmpty(frm.BillTo_billToCode[row].value) == true ) {
						codeValid = false;
					}
				}
				else if (frm.BillTo_billToCode && (row) == 0) {
					if ( isEmpty(frm.BillTo_billToCode.value) == true ) {
						codeValid = false;
					}
				}
				if (frm.BillTo_quantity[row]) {
					if ( isEmpty(frm.BillTo_quantity[row].value) == true || eval(frm.BillTo_quantity[row].value) <= 0) {
						qtyValid = false;
					}
				}
				else if (frm.BillTo_quantity && (row) == 0) {
					if ( isEmpty(frm.BillTo_quantity.value) == true || eval(frm.BillTo_quantity.value) <= 0) {
						qtyValid = false;
					}
				}
			}
		}

		if (!codeValid) {
			alertMessage = alertMessage + "\nYou must enter a Bill To Code for each billing schedule record.";
		}
		if (!qtyValid) {
			alertMessage = alertMessage +  "\nYou must enter a Bill To Quantity for each billing schedule record.";
		}

		if (!isEmpty(alertMessage)) {
			alert("Please fix the following problems:\n" + alertMessage);
			return false;
		}
		return true;
	}

	function validateForm(){
		var handler = frm.handler.value;
		if (handler.indexOf("BillToUpdate") >= 0) {
			if (!validateRows()) {
				return false;
			}
			var billQty = frm.as_tot_qty.value;
			var lineQty = frm.as_line_qty.value;
			if (billQty != lineQty && billQty != 0) {
				 alert ( 'Please fix the following problems:\nTotal Bill To Quantity (' + billQty + ') must equal Total Line Quantity (' + lineQty + ').' );
				 return false;
			}
		}
		return true;
	}