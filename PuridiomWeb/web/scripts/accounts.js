	var currentRow = 0;
	var currentFld = null;

	function addUp (ix)
    {
		var t = eval(nfilter(frm.as_tot));
    	if (maxRows == 1)
    	{
    		var p = eval(nfilter(frm.Account_allocPercent));
			var q = eval(nfilter(frm.Account_allocQty));

			if ((allocMethod == "PH") || (allocMethod == "PL"))
			{
				frm.Account_allocAmount.value = nformat( p * t * 0.01, 4 );
				frm.Account_allocPercent.value = nformat(eval(nfilter(frm.Account_allocPercent)), 2);
			}
			else if ( (allocMethod == "QL") || (allocMethod == "QH") )
			{
				frm.Account_allocQty.value = nformat( q, qtyDec );
				p = nformat( (q / t) * 100, 2 );
				frm.Account_allocPercent.value = p;
				t = eval(nfilter(frm.as_amount_total));
				frm.Account_allocAmount.value = nformat( p * t * 0.01, 4 );
			}
			else if ( (allocMethod == "AL") || (allocMethod == "AH") )
			{
				//added on 09/10/04 to format the # of decimal places when the user changes the $ amount
				frm.Account_allocAmount.value = nformat(frm.Account_allocAmount.value, 4);
				frm.Account_amountLine.value = nformat(frm.Account_allocAmount.value, 2);
			}
    	}
		else
		{
			var p = eval(nfilter(frm.Account_allocPercent[ix]));
			var q = eval(nfilter(frm.Account_allocQty[ix]));

			if ((allocMethod == "PH") || (allocMethod == "PL"))
			{
				frm.Account_allocAmount[ix].value = nformat( p * t * 0.01, 4 );
				frm.Account_allocPercent[ix].value = nformat(eval(nfilter(frm.Account_allocPercent[ix])), 2);
			}
			else if ( (allocMethod == "QL") || (allocMethod == "QH") )
			{
				frm.Account_allocQty[ix].value = nformat( q, qtyDec );
				p = nformat( (q / t) * 100, 2 );
				frm.Account_allocPercent[ix].value = p;
				t = eval(nfilter(frm.as_amount_total));
				frm.Account_allocAmount[ix].value = nformat( p * t * 0.01, 4 );
			}
			else if ( (allocMethod == "AL") || (allocMethod == "AH") )
			{
				//added on 09/10/04 to format the # of decimal places when the user changes the $ amount
				frm.Account_allocAmount[ix].value = nformat(frm.Account_allocAmount[ix].value, 4);
				frm.Account_amountLine[ix].value = nformat(frm.Account_allocAmount[ix].value, 2);

			}
		}
		total();
	}

	function total ()
	{
		var p = 0;
		var t = 0;
		var q = 0;
		var e = 0;
		var total = 0;
		var accRows = maxRows;

		if (accRows > 1)
		{
			for ( var i = 0; i < accRows; i++)
			{
				e = frm.Account_allocPercent[i].value;
				if ( e.length > 0 )
				{
					p = p + eval(nfilter(frm.Account_allocPercent[i]));
				}
				e = frm.Account_allocQty[i].value;
				if ( e.length > 0 )
				{
					q = q + eval(nfilter(frm.Account_allocQty[i]));
				}
				e = frm.Account_allocAmount[i].value;
				if ( e.length > 0 )
				{
					t = t + eval(nfilter(frm.Account_allocAmount[i]));
				}
			}
		}
		else
		{
			e = frm.Account_allocPercent.value;
			if ( e.length > 0 )
			{
				p = p + eval(nfilter(frm.Account_allocPercent));
			}
			e = frm.Account_allocQty.value;
			if ( e.length > 0 )
			{
				q = q + eval(nfilter(frm.Account_allocQty));
			}
			e = frm.Account_allocAmount.value;
			if ( e.length > 0 )
			{
				t = t + eval(nfilter(frm.Account_allocAmount));
			}
		}

		if (p == 100)
		{
			if ( (allocMethod == "QL") || (allocMethod == "QH") )
			{
				total = eval(nfilter(frm.as_amount_total));
			}
			else
			{
				total = eval(nfilter(frm.as_tot));
			}

			diff = total - t;
			if (diff != 0)
			{
				var a = i - 1;
				if (e.length > 0)
				{
					if (accRows > 1)
					{
						frm.Account_allocAmount[i-1].value = nformat(eval(e) + diff, 4);
					}
					else
					{
						frm.Account_allocAmount.value = nformat(eval(e) + diff, 4);
					}
				}
				t = total;
			}
		}

		if (accRows > 0)
		{
			if (allocMethod == "PH" || allocMethod == "PL")
			{
				frm.as_perc_tot.value = nformat(p,2);
			}
			else if (allocMethod == "QH" || allocMethod == "QL")
			{
				frm.as_qty_tot.value = nformat(q,qtyDec);
			}
			frm.as_amt_tot.value = nformat(t,4);
		}
		else
		{
			frm.as_perc_tot.value = '';
			frm.as_amt_tot.value  = '';
			frm.as_qty_tot.value  = '';
		}
	}

    function isRowEmpty (k)
    {
    	if (maxRows > 1)
    	{
    		if (frm.organizationId.value != "QRI06P")
    		{
				if ( isEmpty(frm.Account_fld1[k].value) == false ) return false;
			}
			if ( isEmpty(frm.Account_fld2[k].value) == false ) return false;
			if ( isEmpty(frm.Account_fld3[k].value) == false ) return false;
			if ( isEmpty(frm.Account_fld4[k].value) == false ) return false;
			if ( isEmpty(frm.Account_fld5[k].value) == false ) return false;
			if ( isEmpty(frm.Account_fld6[k].value) == false ) return false;
			if ( isEmpty(frm.Account_fld7[k].value) == false ) return false;
			if ( isEmpty(frm.Account_fld8[k].value) == false ) return false;
			if ( isEmpty(frm.Account_fld9[k].value) == false ) return false;
			if ( isEmpty(frm.Account_fld10[k].value) == false ) return false;
			if ( isEmpty(frm.Account_fld11[k].value) == false ) return false;
			if ( isEmpty(frm.Account_fld12[k].value) == false ) return false;
			if ( isEmpty(frm.Account_fld13[k].value) == false ) return false;
			if ( isEmpty(frm.Account_fld14[k].value) == false ) return false;
			if ( isEmpty(frm.Account_fld15[k].value) == false ) return false;
		}
		else
		{
			if (frm.organizationId.value != "QRI06P")
    		{
				if ( isEmpty(frm.Account_fld1.value) == false ) return false;
			}
			if ( isEmpty(frm.Account_fld2.value) == false ) return false;
			if ( isEmpty(frm.Account_fld3.value) == false ) return false;
			if ( isEmpty(frm.Account_fld4.value) == false ) return false;
			if ( isEmpty(frm.Account_fld5.value) == false ) return false;
			if ( isEmpty(frm.Account_fld6.value) == false ) return false;
			if ( isEmpty(frm.Account_fld7.value) == false ) return false;
			if ( isEmpty(frm.Account_fld8.value) == false ) return false;
			if ( isEmpty(frm.Account_fld9.value) == false ) return false;
			if ( isEmpty(frm.Account_fld10.value) == false ) return false;
			if ( isEmpty(frm.Account_fld11.value) == false ) return false;
			if ( isEmpty(frm.Account_fld12.value) == false ) return false;
			if ( isEmpty(frm.Account_fld13.value) == false ) return false;
			if ( isEmpty(frm.Account_fld14.value) == false ) return false;
			if ( isEmpty(frm.Account_fld15.value) == false ) return false;
		}

		return ( true );
	}

	function setCurrentRow(row)
	{
		currentRow = row;
	}

	function setMethod()
	{
		if (frm.alloc_method[0].checked == true)
		{
			if (type == "HDR")
			{
				frm.as_alloc_method.value = "PH";
				allocMethod = "PH";
			}
			else if (type == "LIN")
			{
				frm.as_alloc_method.value = "PL";
				allocMethod = "PL";
			}

			if (maxRows > 1)
			{
				for ( var i = 0; i < maxRows; i++ )
				{
					frm.Account_allocPercent[i].value = nformat((frm.Account_allocAmount[i].value / frm.as_tot.value) * 100, 2);

					if (type == "HDR")
					{
						frm.Account_allocMethod[i].value = "PH";
					}
					else if (type == "LIN")
					{
						frm.Account_allocMethod[i].value = "PL";
					}
				}
			}
			else if (maxRows == 1)
			{
				frm.Account_allocPercent.value = nformat((frm.Account_allocAmount.value / frm.as_tot.value) * 100, 2);

				if (type == "HDR")
				{
					frm.Account_allocMethod.value = "PH";
				}
				else if (type == "LIN")
				{
					frm.Account_allocMethod.value = "PL";
				}
			}

			enablePercentFields();
		}
		if (frm.alloc_method[1].checked == true)
		{
			if (type == "HDR")
			{
				frm.as_alloc_method.value = "AH";
				allocMethod = "AH";
			}
			else if (type == "LIN")
			{
				frm.as_alloc_method.value = "AL";
				allocMethod = "AL";
			}

			if (maxRows > 1)
			{
				for ( var i = 0; i < maxRows; i++ )
				{
					frm.Account_allocPercent[i].value = '';

					if (type == "HDR")
					{
						frm.Account_allocMethod[i].value = "AH";
					}
					else if (type == "LIN")
					{
						frm.Account_allocMethod[i].value = "AL";
					}
				}
			}
			else if (maxRows == 1)
			{
				frm.Account_allocPercent.value = '';

				if (type == "HDR")
				{
					frm.Account_allocMethod.value = "AH";
				}
				else if (type == "LIN")
				{
					frm.Account_allocMethod.value = "AL";
				}
			}

			disablePercentFields();
		}
		total();
		if (frm.alloc_method[1].checked == true)
		{
			frm.as_perc_tot.value = '';
		}
	}

	function setCurrentRow(row)
	{
		currentRow = row;
	}

	function checkAllocated ()
	{
		var alertMessage = "";

		var p = eval(nfilter(frm.as_perc_tot));
		var a = eval(nfilter(frm.as_amt_tot));
		var q = eval(nfilter(frm.as_qty_tot));
		var r = eval(nfilter(frm.as_tot));

		var method = allocMethod;

		if (method == 'PH' || method == 'PL' || method == 'P')
		{
			if (frm.alloc_method[1].checked == true)
			{
				if ( (r != a) && (maxRows > 0) )
				{
					alertMessage += 'Amount ('+frm.as_amt_tot.value+') must equal '+frm.as_tot.value+'.\n';
				}
			}
			else
			{
				if ( (p != 100) && (maxRows > 0) )
				{
					alertMessage += 'Percentage (' + frm.as_perc_tot.value + ') must equal 100%.\n';
				}
			}
		}
		else if (method == 'AH' || method == 'AL')
		{
			if ( (r != a) && (maxRows > 0) )
			{
				alertMessage += 'Amount ('+frm.as_amt_tot.value+') must equal '+frm.as_tot.value+'.\n';
			}
		}
		else
		{
			if ( (r != q) && (maxRows > 0) )
			{
				alertMessage += 'Quantity ('+frm.as_qty_tot.value+') must equal '+frm.as_tot.value+'.\n';
			}
		}

		if ( alertMessage.length > 0 )
		{
		     alert ( 'Please fix the following problems:\n'+alertMessage );
		     //submitcount = 0;
		     return false;
		}

		//var comp = frm.componentId.value;
		if (type == "LIN") {
			if (allocMethod == "PH") {
				allocMethod = "PL";
			} else if (allocMethod == "AH") {
				allocMethod = "AL";
			} else if (allocMethod == "QH") {
				allocMethod = "QL";
			}
		}
		//comp = comp + "\u0008alloc_method=" + allocMethod;
		//frm.componentId.value = comp;

		return true;
	}

	function deleteEmptyRows()
	{
		for (var i = maxRows; i > 0; i--)
		{
			if (isRowEmpty(i - 1))
			{
				deleteMe(i - 1);
			}
		}
	}

	function disablePercentFields()
	{
		var percents = document.all("Account_AllocPercent");
		for (var i = 0; i < percents.length; i++)
		{
			percents(i).readOnly = true;
			percents(i).contentEditable = false;
		}
	}

	function disableAmmountPercentFields()
	{
		var percents = document.all("Account_amountLine");
		for (var i = 0; i < percents.length; i++)
		{
			percents(i).readOnly = true;
			percents(i).contentEditable = false;
		}
	}
	function enablePercentFields()
	{
		var percents = document.all("Account_AllocPercent");
		for (var i = 0; i < percents.length; i++)
		{
			percents(i).readOnly = false;
			percents(i).contentEditable = true;
		}
	}

	function distributeRemaining(count)
	{
		if (maxRows > 0)
		{
			if (frm.alloc_method[1].checked)
			{
				var percentTotal = frm.as_perc_tot.value;
				var amtTotal = amtToAllocate;
				var percentLeft = nformat(100.00 - percentTotal, 2);
				var amtLeft = nformat(amtTotal - frm.as_amt_tot.value, 4);

				myCell = document.getElementById("allocAmount_" + count);
				myCell.value = amtLeft;
				myCell = document.getElementById("allocAmountP_" + count);
				myCell.value = amtLeft;

				frm.as_amt_tot.value = amtToAllocate;
				frm.as_perc_tot.value = "";
			}
			else
			{
				var percentTotal = frm.as_perc_tot.value;
				var percentLeft = nformat(100.00 - percentTotal, 2);
				if (percentLeft < 0)
				{
					percentLeft = "0.00";
				}

				myCell = document.getElementById("allocPercent_" + count);
				myCell.value = percentLeft;
				frm.as_perc_tot.value = "100.00";

				myCell = document.getElementById("allocAmount_" + count);
				myCell.value = nformat( percentLeft * eval(nfilter(frm.as_tot)) * 0.01, 4 );

				frm.as_amt_tot.value = nformat(amtToAllocate, 4);
			}
		}
	}

	function validateForm()
	{
		if (maxRows >= 1 && frm.handler.value.indexOf("AccountUpdate") >= 0)
		{
			for (var i = maxRows; i > 0; i--)
			{
				if (isRowEmpty(i - 1))
				{
					deleteMe(i - 1);
				}
			}

			if (!checkAllocated())
			{
				return false;
			}
		}

		setDefaultType();
		return true;
	}

	function deleteMe(row)
	{
		var accRows = maxRows;

		myTable = document.getElementById("accounts");

		if (accRows == 0)
		{
			return;
		}
		else if (accRows > 1)
		{
			for (var i = row; i < (accRows - 1); i++)
			{
				var fld1 = frm.Account_fld1[i + 1].value;
				var fld2 = frm.Account_fld2[i + 1].value;
				var fld3 = frm.Account_fld3[i + 1].value;
				var fld4 = frm.Account_fld4[i + 1].value;
				var fld5 = frm.Account_fld5[i + 1].value;
				var fld6 = frm.Account_fld6[i + 1].value;
				var fld7 = frm.Account_fld7[i + 1].value;
				var fld8 = frm.Account_fld8[i + 1].value;
				var fld9 = frm.Account_fld9[i + 1].value;
				var fld10 = frm.Account_fld10[i + 1].value;
				var fld11 = frm.Account_fld11[i + 1].value;
				var fld12 = frm.Account_fld12[i + 1].value;
				var fld13 = frm.Account_fld13[i + 1].value;
				var fld14 = frm.Account_fld14[i + 1].value;
				var fld15 = frm.Account_fld15[i + 1].value;
				var perc = frm.Account_allocPercent[i + 1].value;
				var amt = frm.Account_allocAmount[i + 1].value;

				frm.Account_fld1[i].value = fld1;
				frm.Account_fld2[i].value = fld2;
				frm.Account_fld3[i].value = fld3;
				frm.Account_fld4[i].value = fld4;
				frm.Account_fld5[i].value = fld5;
				frm.Account_fld6[i].value = fld6;
				frm.Account_fld7[i].value = fld7;
				frm.Account_fld8[i].value = fld8;
				frm.Account_fld9[i].value = fld9;
				frm.Account_fld10[i].value = fld10;
				frm.Account_fld11[i].value = fld11;
				frm.Account_fld12[i].value = fld12;
				frm.Account_fld13[i].value = fld13;
				frm.Account_fld14[i].value = fld14;
				frm.Account_fld15[i].value = fld15;
				frm.Account_allocPercent[i].value = perc;
				frm.Account_allocAmount[i].value = amt;
			}
		}

		deleteRow(myTable, accRows);

		accRows = accRows - 1;

		if (accRows <= 0)
		{
			frm.deleteall.value = "TRUE";
		}

		//to fix totals percent and amount
		var totalPercent = 0;
		var totalAmount = 0;

		if (frm.alloc_method[0].checked)
		{
			if (accRows > 1)
			{
				for (r = 0; r < accRows; r++)
				{
					totalAmount = totalAmount + eval(nfilter(frm.Account_allocAmount[r]));
					totalPercent = totalPercent + eval(nfilter(frm.Account_allocPercent[r]));
				}
			}
			else if (accRows == 1)
			{
				if (frm.Account_allocAmount[0])
				{
					totalAmount = eval(nfilter(frm.Account_allocAmount[0]));
				}
				else
				{
					var amt = document.all("Account_allocAmount");
					totalAmount = eval(nfilter(amt));
				}

				if (frm.Account_allocPercent[0])
				{
					totalPercent = eval(nfilter(frm.Account_allocPercent[0]));
				}
				else
				{
					var perc = document.all("Account_allocPercent");
					totalPercent = eval(nfilter(perc));
				}
			}
			frm.as_perc_tot.value = nformat(totalPercent,2);
			frm.as_amt_tot.value = nformat(totalAmount,2);
		}
		else
		{
			if (accRows > 1)
			{
				for (r = 0; r < accRows; r++)
				{
					totalAmount = totalAmount + eval(nfilter(frm.Account_allocAmount[r]));
				}
			}
			else if (accRows == 1)
			{
				if (frm.Account_allocAmount[0])
				{
					totalAmount = eval(nfilter(frm.Account_allocAmount[0]));
				}
				else
				{
					var amt = document.all("Account_allocAmount");
					totalAmount = eval(nfilter(amt));
				}
			}

			frm.as_amt_tot.value = nformat(totalAmount,2);
		}

		maxRows = maxRows - 1;	//needs to be set so that when validateForm is called it has the appropriate row count
	}

	function deleteAll()
	{
		if (confirm("Are you sure you wish to delete all Accounts?"))
		{
			frm.deleteall.value = "TRUE";
			myTable = document.getElementById("accounts");
			count = maxRows;

			maxRows = 0;

			for(i = 0; i < count; i++)
			{
				myTable.deleteRow(1);
			}

			fixAmounts();
			addNew();
		}
	}

	function fixAmounts()
	{
		//to fix totals percent and amount
		var totalPercent = 0;
		var totalAmount = 0;
		var accRows = maxRows - 1;

		if (frm.alloc_method[0].checked)
		{
			if (accRows > 0)
			{
				for (r = 0; r <= accRows; r++)
				{
					totalAmount = totalAmount + eval(nfilter(frm.Account_allocAmount[r]));
					totalPercent = totalPercent + eval(nfilter(frm.Account_allocPercent[r]));
				}
			}
			else if (accRows == 0)
			{
				totalAmount = eval(nfilter(frm.Account_allocAmount));
				totalPercent = eval(nfilter(frm.Account_allocPercent));
			}
			frm.as_perc_tot.value = nformat(totalPercent,2);
			frm.as_amt_tot.value = nformat(totalAmount,2);
		}
		else
		{
			if (accRows > 0)
			{
				for (r = 0; r <= accRows; r++)
				{
					totalAmount = totalAmount + eval(nfilter(frm.Account_allocAmount[r]));
				}
			}
			else if (accRows == 0)
			{
				totalAmount = eval(nfilter(frm.Account_allocAmount));
			}
			frm.as_amt_tot.value = nformat(totalAmount,2);
		}
	}

	function setDefaultType() {
		var accRows = maxRows;
		if (accRows == 0)
		{
			/*
				needed to add this for the wizard view...
				if the user deletes all the accounts and hits save accountType must be set
			*/
			var hiddenFields = document.getElementById("hiddenFields");
			hiddenFields.innerHTML = hiddenFields.innerHTML + "<input type=hidden name=Account_accountType value=\"" + accountType + "\>";
		}
	}

	function reqSave()
	{
		if (isNA(reqnumber))
		{
			if (autoReqNumber && !showAutoReqNumber)
			{
				doSubmit(currentpage, "RequisitionGetFormNumber;" + currentmethod + ";" + currentprocessmethod);
			}
			else
			{
				popupParameters = "formtype=REQ;formnumber=" + reqnumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=AccountUpdate/AccountRetrieveByLine";
				doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
			}
		}
		else
		{
			doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
		}
	}

	function poSave()
	{
		if (isNA(ponumber))
		{
			popupParameters = "formtype=PO;formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentmethod + "/" + currentprocessmethod;
			//popupParameters = "formtype=PO;formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=save;";
			doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
		}
		else
		{
			doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
		}
	}

	function setAccountPopupParams() {
		if (maxRows > 1) {
			if (frm.organizationId.value == 'VSE06P' && !isEmpty(frm.Account_fld1[currentRow].value)) {
				popupParameters = popupParameters + "Account_fld1_substring=" + (frm.Account_fld1[currentRow].value).substr(0,1) + "%;";
			}
			popupParameters = popupParameters + "Account_fld1=" + frm.Account_fld1[currentRow].value +
				";Account_fld2=" + frm.Account_fld2[currentRow].value +
				";Account_fld3=" + frm.Account_fld3[currentRow].value +
				";Account_fld4=" + frm.Account_fld4[currentRow].value +
				";Account_fld5=" + frm.Account_fld5[currentRow].value +
				";Account_fld6=" + frm.Account_fld6[currentRow].value +
				";Account_fld7=" + frm.Account_fld7[currentRow].value +
				";Account_fld8=" + frm.Account_fld8[currentRow].value +
				";Account_fld9=" + frm.Account_fld9[currentRow].value +
				";Account_fld10=" + frm.Account_fld10[currentRow].value +
				";Account_fld11=" + frm.Account_fld11[currentRow].value +
				";Account_fld12=" + frm.Account_fld12[currentRow].value +
				";Account_fld13=" + frm.Account_fld13[currentRow].value +
				";Account_fld14=" + frm.Account_fld14[currentRow].value +
				";Account_fld15=" + frm.Account_fld15[currentRow].value + ";";
		} else {
			if (frm.organizationId.value == 'VSE06P' && !isEmpty(frm.Account_fld1.value)) {
				popupParameters = popupParameters + "Account_fld1_substring=" + (frm.Account_fld1.value).substr(0,1) + "%;";
			}
			if (frm.BudgetCenter_budget5 != undefined ) {
							popupParameters = popupParameters + "Account_fld1=" + frm.BudgetCenter_budget1.value +
				";Account_fld2=" + frm.BudgetCenter_budget2.value +
				";Account_fld3=" + frm.BudgetCenter_budget3.value +
				";Account_fld4=" + frm.BudgetCenter_budget4.value +
				";Account_fld5=" + frm.BudgetCenter_budget5.value + ";";

			} else {
				popupParameters = popupParameters + "Account_fld1=" + frm.Account_fld1.value +
				";Account_fld2=" + frm.Account_fld2.value +
				";Account_fld3=" + frm.Account_fld3.value +
				";Account_fld4=" + frm.Account_fld4.value +
				";Account_fld5=" + frm.Account_fld5.value +
				";Account_fld6=" + frm.Account_fld6.value +
				";Account_fld7=" + frm.Account_fld7.value +
				";Account_fld8=" + frm.Account_fld8.value +
				";Account_fld9=" + frm.Account_fld9.value +
				";Account_fld10=" + frm.Account_fld10.value +
				";Account_fld11=" + frm.Account_fld11.value +
				";Account_fld12=" + frm.Account_fld12.value +
				";Account_fld13=" + frm.Account_fld13.value +
				";Account_fld14=" + frm.Account_fld14.value +
				";Account_fld15=" + frm.Account_fld15.value + ";";
			}

		}
	}

	function browseAccountFld(frmField, udf) {
		var browseName = "systable";
		if (udf == "AC01") {
			browseName = accountFld1Browse;
			if (maxRows == 1) {
				currentFld = frm.Account_fld1;
			} else {
				currentFld = frm.Account_fld1[currentRow];
			}
		} else if (udf == "AC02") {
			browseName = accountFld2Browse;
			if (maxRows == 1) {
				currentFld = frm.Account_fld2;
			} else {
				currentFld = frm.Account_fld2[currentRow];
			}
		} else if (udf == "AC03") {
			browseName = accountFld3Browse;
			if (maxRows == 1) {
				currentFld = frm.Account_fld3;
			} else {
				currentFld = frm.Account_fld3[currentRow];
			}
		} else if (udf == "AC04") {
			browseName = accountFld4Browse;
			if (maxRows == 1) {
				currentFld = frm.Account_fld4;
			} else {
				currentFld = frm.Account_fld4[currentRow];
			}
		} else if (udf == "AC05") {
			browseName = accountFld5Browse;
			if (maxRows == 1) {
				currentFld = frm.Account_fld5;
			} else {
				currentFld = frm.Account_fld5[currentRow];
			}
		} else if (udf == "AC06") {
			browseName = accountFld6Browse;
			if (maxRows == 1) {
				currentFld = frm.Account_fld6;
			} else {
				currentFld = frm.Account_fld6[currentRow];
			}
			if (frm.organizationId.value == 'VSE06P') {
				// Filter accounts, if AC02 has a value, browse will only show Accounts that begin w/ 6
				//		if AC02 is empty, browse will show all Accounts except those that begin w/ 6
				var pjo = "";
				if (maxRows == 1) {
					pjo = frm.Account_fld2.value;
				} else {
					pjo = frm.Account_fld2[currentRow].value;
				}
				if (!isEmpty(pjo)) {
					if (!isEmpty(popupParameters)) {
						popupParameters = popupParameters + ";";	//needed a ; separator
					}
					popupParameters = popupParameters + "colname=DeltekOrgAcct_acctId;operator=LIKE;filter_txt=6%;logicalOperator=AND;originalFilter=Y;sort=N;"
				} else {
					popupParameters = popupParameters + "colname=DeltekOrgAcct_acctId;operator=NOT LIKE;filter_txt=6%;logicalOperator=AND;originalFilter=Y;sort=N;"
				}
			}
		} else if (udf == "AC07") {
			browseName = accountFld7Browse;
			if (maxRows == 1) {
				currentFld = frm.Account_fld7;
			} else {
				currentFld = frm.Account_fld7[currentRow];
			}
		} else if (udf == "AC08") {
			browseName = accountFld8Browse;
			if (maxRows == 1) {
				currentFld = frm.Account_fld8;
			} else {
				currentFld = frm.Account_fld8[currentRow];
			}
		} else if (udf == "AC09") {
			browseName = accountFld9Browse;
			if (maxRows == 1) {
				currentFld = frm.Account_fld9;
			} else {
				currentFld = frm.Account_fld9[currentRow];
			}
		} else if (udf == "AC10") {
			browseName = accountFld10Browse;
			if (maxRows == 1) {
				currentFld = frm.Account_fld10;
			} else {
				currentFld = frm.Account_fld10[currentRow];
			}
		} else if (udf == "AC11") {
			browseName = accountFld11Browse;
			if (maxRows == 1) {
				currentFld = frm.Account_fld11;
			} else {
				currentFld = frm.Account_fld11[currentRow];
			}
		} else if (udf == "AC12") {
			browseName = accountFld12Browse;
			if (maxRows == 1) {
				currentFld = frm.Account_fld12;
			} else {
				currentFld = frm.Account_fld12[currentRow];
			}
		} else if (udf == "AC13") {
			browseName = accountFld13Browse;
			if (maxRows == 1) {
				currentFld = frm.Account_fld13;
			} else {
				currentFld = frm.Account_fld13[currentRow];
			}
		} else if (udf == "AC14") {
			browseName = accountFld14Browse;
			if (maxRows == 1) {
				currentFld = frm.Account_fld14;
			} else {
				currentFld = frm.Account_fld14[currentRow];
			}
		} else if (udf == "AC15") {
			browseName = accountFld15Browse;
			if (maxRows == 1) {
				currentFld = frm.Account_fld15;
			} else {
				currentFld = frm.Account_fld15[currentRow];
			}
		}

		if (browseName == "systable")
		{
			if (tableType != "AC")
			{
				udf = udf.replace('AC', tableType);
			}
			browseStd(frmField, udf);
		} else {
			setAccountPopupParams();
			browseLookup(frmField, browseName);
		}
	}

	function setProjectCode()
	{
		// this is ONLY for Project Requests
		// DO NOT DELETE!
	}

   function addUpPercent (ix)
    {
		var t = eval(nfilter(frm.as_tot));
    	if (maxRows == 1)
    	{
    		var p = eval(nfilter(frm.Account_allocPercent));
			var q = eval(nfilter(frm.Account_allocQty));

			if ((allocMethod == "PH") || (allocMethod == "PL"))
			{
				if (frm.alloc_method[0].checked == true)
				{
					frm.Account_allocAmount.value = nformat( p * t * 0.01, 4 );
					frm.Account_allocPercent.value = nformat(eval(nfilter(frm.Account_allocPercent)), 2);
				}
				else if (frm.alloc_method[1].checked == true)
				{
					frm.Account_allocAmount.value = nformat(frm.Account_allocAmount.value, 4);
					frm.Account_allocPercent.value = nformat(((frm.Account_allocAmount.value)*100/t), 2);
				}
			}
			else if ( (allocMethod == "QL") || (allocMethod == "QH") )
			{
				frm.Account_allocQty.value = nformat( q, qtyDec );
				p = nformat( (q / t) * 100, 2 );
				frm.Account_allocPercent.value = p;
				t = eval(nfilter(frm.as_amount_total));
				frm.Account_allocAmount.value = nformat( p * t * 0.01, 4 );
			}
			else if ( (allocMethod == "AL") || (allocMethod == "AH") )
			{
				//added on 09/10/04 to format the # of decimal places when the user changes the $ amount
				frm.Account_allocAmount.value = nformat(frm.Account_allocAmount.value, 4);
				//frm.Account_allocAmount.value = nformat( p * t * 0.01, 2 );
				frm.Account_allocPercent.value = nformat((eval(nfilter(frm.Account_allocPercent)*100)/t), 2);
			}
    	}
		else
		{
			var p = eval(nfilter(frm.Account_allocPercent[ix]));
			var q = eval(nfilter(frm.Account_allocQty[ix]));

			if ((allocMethod == "PH") || (allocMethod == "PL"))
			{
				if (frm.alloc_method[0].checked == true)
				{
					frm.Account_allocAmount[ix].value = nformat( p * t * 0.01, 4 );
					frm.Account_allocPercent[ix].value = nformat(eval(nfilter(frm.Account_allocPercent[ix])), 2);
				}
				else if (frm.alloc_method[1].checked == true)
				{
					frm.Account_allocAmount[ix].value = nformat(frm.Account_allocAmount[ix].value, 4);
					frm.Account_allocPercent[ix].value = nformat(((frm.Account_allocAmount[ix].value)*100/t), 2);
				}

			}
			else if ( (allocMethod == "QL") || (allocMethod == "QH") )
			{
				frm.Account_allocQty[ix].value = nformat( q, qtyDec );
				p = nformat( (q / t) * 100, 2 );
				frm.Account_allocPercent[ix].value = p;
				t = eval(nfilter(frm.as_amount_total));
				frm.Account_allocAmount[ix].value = nformat( p * t * 0.01, 4 );
			}
			else if ( (allocMethod == "AL") || (allocMethod == "AH") )
			{
				//added on 09/10/04 to format the # of decimal places when the user changes the $ amount
				frm.Account_allocAmount[ix].value = nformat(frm.Account_allocAmount[ix].value, 4);
				frm.Account_allocPercent.value = nformat((eval(nfilter(frm.Account_allocPercent[ix].value)*100)/t), 2);
			}
		}
		total();
		displayPercentFieldsAll();
	}

	function setMethodPercent()
	{
		if (frm.alloc_method[0].checked == true)
		{
			if (type == "HDR")
			{
				frm.as_alloc_method.value = "PH";
				allocMethod = "PH";
			}
			else if (type == "LIN")
			{
				frm.as_alloc_method.value = "PL";
				allocMethod = "PL";
			}

			if (maxRows > 1)
			{
				for ( var i = 0; i < maxRows; i++ )
				{
					frm.Account_allocPercent[i].value = nformat((frm.Account_allocAmount[i].value / frm.as_tot.value) * 100, 2);

					if (type == "HDR")
					{
						frm.Account_allocMethod[i].value = "PH";
					}
					else if (type == "LIN")
					{
						frm.Account_allocMethod[i].value = "PL";
					}
				}
			}
			else if (maxRows == 1)
			{
				frm.Account_allocPercent.value = nformat((frm.Account_allocAmount.value / frm.as_tot.value) * 100, 2);

				if (type == "HDR")
				{
					frm.Account_allocMethod.value = "PH";
				}
				else if (type == "LIN")
				{
					frm.Account_allocMethod.value = "PL";
				}
			}

			enablePercentFields();
		}
		if (frm.alloc_method[1].checked == true)
		{
			if (type == "HDR")
			{
				frm.as_alloc_method.value = "PH";
				allocMethod = "PH";
			}
			else if (type == "LIN")
			{
				frm.as_alloc_method.value = "PL";
				allocMethod = "PL";
			}

			if (maxRows > 1)
			{
				for ( var i = 0; i < maxRows; i++ )
				{
					frm.Account_allocPercent[i].value = nformat((frm.Account_allocAmount[i].value / frm.as_tot.value) * 100, 2);

					if (type == "HDR")
					{
						frm.Account_allocMethod[i].value = "PH";
					}
					else if (type == "LIN")
					{
						frm.Account_allocMethod[i].value = "PL";
					}
				}
				/// new process
				for ( var i = 0; i < maxRows; i++ )
				{
					frm.Account_amountLine[i].value = '';

					if (type == "HDR")
					{
						frm.Account_allocMethod[i].value = "AH";
					}
					else if (type == "LIN")
					{
						frm.Account_allocMethod[i].value = "AL";
					}
				}

			}
			else if (maxRows == 1)
			{
				frm.Account_allocPercent.value = nformat((frm.Account_allocAmount.value / frm.as_tot.value) * 100, 2);

				if (type == "HDR")
				{
					frm.Account_allocMethod.value = "PH";
				}
				else if (type == "LIN")
				{
					frm.Account_allocMethod.value = "PL";
				}

				/// new process
				frm.Account_amountLine.value = '';

				if (type == "HDR")
				{
					frm.Account_allocMethod.value = "AH";
				}
				else if (type == "LIN")
				{
					frm.Account_allocMethod.value = "AL";
				}
			}

			disableAmmountPercentFields();
		}
		total();
		if (frm.alloc_method[1].checked == true)
		{
			//frm.as_perc_tot.value = '';
		}
		displayPercentFieldsAll();
	}

	function displayPercentFieldsAll()
	{
		if (frm.alloc_method[0].checked)
		{
			//displayPercentAll("percent");
			//hidePercentAll("percentAmt");
		}
		if (frm.alloc_method[1].checked)
		{
			//displayPercentAll("percentAmt");
			//("percent");
		}
   }

   function displayPercentAll(objDisplay)
   {
		if (maxRows > 1)
		{
			for (var r = 0; r < maxRows; r++)
			{
				var obj = document.getElementById(objDisplay + r);
				obj.style.visibility = "visible";
	        	obj.style.display    = "block";
			}
		}
		else
		{
			var obj = document.getElementById(objDisplay + "0");
   			obj.style.visibility = "visible";
	       	obj.style.display    = "block";
		}
   }

   function hidePercentAll(objHide)
   {
		if (maxRows > 1)
		{
			for (var r = 0; r < maxRows; r++)
			{
				var obj = document.getElementById(objHide + r);
       			obj.style.visibility = "hidden";
		       	obj.style.display    = "none";
			}
		}
		else
		{
			var obj = document.getElementById(objHide + "0");
   			obj.style.visibility = "hidden";
	       	obj.style.display    = "none";
		}
   }

   function displayPercentFields()
	{
		if (frm.alloc_method[0].checked)
		{
			//displayPercent("percent");
			//hidePercent("percentAmt");
		}
		if (frm.alloc_method[1].checked)
		{
			//displayPercent("percentAmt");
			//hidePercent("percent");
		}
   }

   function displayPercent(objDisplay)
   {
		if (maxRows > 1)
		{
			var obj = document.getElementById(objDisplay + currentRow);
			obj.style.visibility = "visible";
	        obj.style.display    = "block";
		}
		else
		{
			var obj = document.getElementById(objDisplay + "0");
   			obj.style.visibility = "visible";
	       	obj.style.display    = "block";
		}
   }

   function hidePercent(objHide)
   {
		if (maxRows > 1)
		{
			var obj = document.getElementById(objHide + currentRow);
    		obj.style.visibility = "hidden";
		    obj.style.display    = "none";
		}
		else
		{
			var obj = document.getElementById(objHide + "0");
   			obj.style.visibility = "hidden";
	       	obj.style.display    = "none";
		}
   }
