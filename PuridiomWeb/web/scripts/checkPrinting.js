var icIvcHeader = 'icIvcHeader';
var supplierName = 'supplierName';
var remitAddress = 'remitAddress';
var invoiceNumber = 'invoiceNumber';
var invoiceDate = 'invoiceDate';
var netDate = 'netDate';
var invoiceAmount = 'invoiceAmount';
var discountAmount = 'discountAmount';
var adjustment = 'adjustment';
var paidAmount = 'paidAmount';
var terms = 'terms';

var remitToSite = 'remiToSite';
var address1 = 'address1';
var address2 = 'address2';
var state = 'state';
var country = 'country';
var termsDescription = 'termsDescription';
var discount = 'discount';
var termsDays = 'termsDays';
var subTotal = 'subTotal';
var udf9code = 'udf9Code';
var udf10code = 'udf10Code';

function addInvoiceRow()
{
	myTable = document.getElementById("browseRows");
	myRow = createRow(myTable);
	myCell = createCell(myRow);

	var TotalRows = myTable.rows.length - 1;
	var number = TotalRows;

	var headerPart = '<table width="665" cellspacing="0" cellpadding="1" border="0" class="browseRow">'+
	'<tbody><tr class="browseRow"><td height="18" width="2%" valign="top" align="left" class="browseRow">'+
	'<input type="checkbox" name="c_checkbox" value="Y" onclick="calculateTotals()" />'+
	'<input type="hidden" name="Input_checkbox" value=""/></td>'+
	'<td height="18" width="2%" valign="top" align="left" class="browseRow">'+
	'<input type="checkbox" onclick="takeDiscount(' + number + ')" checked="" name="c_checkbox2" value="Y"/>'+
	'<input type="hidden" name="Input_checkbox2" value="Y"/></td>'+
	'<td height="18" width="10%" valign="top" align="left" class="browseRow">' + supplierName +	'</td>' +
	'<td height="18" width="10%" valign="top" align="left" class="browseRow">' + remitAddress +	'</td>' +
	'<td height="18" width="10%" valign="top" align="left" class="browseRow">'+
	'<a onmouseout="removeHighlight('+ number +');hideDetails('+ number +');" onmouseover="showDetails('+ number +');highlightRow('+ number +');" onclick="javascript: rowSelect=\''+ number +'\';" href="javascript: viewInvoice(\''+ icIvcHeader +'\'); void(0);">'+
	invoiceNumber+
	'</a></td>'+
	'<td height="18" width="12%" valign="top" align="left" class="browseRow">' + invoiceDate + '</td>' +
	'<td height="18" width="12%" valign="top" align="left" class="browseRow">' + netDate + '</td>' +
	'<td height="18" width="10%" valign="top" align="right" class="browseRow">' +
	'<input type="text" readonly="" size="8" style="border: medium none ; text-align: right; background-color: transparent;" value="'+ invoiceAmount +'" name="InvoiceHeader_invoiceTotal"/>'+
	'</td>'+
	'<td height="18" width="10%" valign="top" align="right" class="browseRow">' +
	'<input type="text" readonly="" size="8" style="border: medium none ; text-align: right; background-color: transparent;" value="'+ discountAmount + '" name="InvoiceHeader_invoiceDiscount"/>'+
	'</td>'+
	'<td height="18" width="10%" valign="top" align="right" class="browseRow">' +
	'<input type="text" readonly="" size="8" style="border: medium none ; text-align: right; background-color: transparent;" value="'+ adjustment + '" name="InvoiceHeader_invoiceAdjustment"/>'+
	'</td>'+
	'<td height="18" width="10%" valign="top" align="right" class="browseRow">' +
	'<input type="text" readonly="" size="8" style="border: medium none ; text-align: right; background-color: transparent;" value="'+ paidAmount +'" name="InvoiceHeader_invoicePaid"/>'+
	'</td>' +
	'<td height="18" width="16%" valign="top" align="left" class="browseRow">' +	terms +	'</td>'+
	'<td height="18" align="left" width="2%" valign="top"><a name="deleteLink" href="javascript: deleteMe('+ number +');">'+
	'<IMG SRC="/puridiom/images/delete.gif" ALT="Delete" BORDER="0"></a></td>'+
	'</tr></tbody></table>';


	var detailPart = '<div id="details'+ number +'" class="browseRow" style="visibility: hidden; display: none;" >'+
	'<table width="95%" cellspacing="0" cellpadding="0" border="0" align="right" class="browseRow" id="detailRows">' +
	'<tbody><tr class="browseRow"><td class="browseRow">' +
	'<table width="100%" cellspacing="0" cellpadding="0" border="0" class="browseRow">' +
	'<tbody><tr><td height="18" width="100%" valign="top" class="browseRow" colspan="8">' +
	'<b>Remit To Site:</b>' + remitToSite +
	'</td></tr></tbody></table></td></tr>' +
	'<tr class="browseRow"><td class="browseRow"> '+
	'<table width="100%" cellspacing="0" cellpadding="0" border="0" class="browseRow"><tbody><tr><td height="18" width="40%" valign="top" class="browseRow"> '+
	'<b>Address 1:</b> ' + address1 + '</td>' +
	'<td height="18" width="20%" valign="top" class="browseRow"> ' +
	'<b>Address 2:</b>' + address2 + '</td> ' +
	'<td height="18" width="20%" valign="top" class="browseRow"> ' +
	'<b>State:</b>' + state + '</td>' +
	'<td height="18" width="20%" valign="top" class="browseRow"> ' +
	'<b>Country:</b>' +	country + '</td>' +
	'</tr></tbody></table></td></tr>' +
	'<tr class="browseRow"><td class="browseRow">' +
	'<table width="100%" cellspacing="0" cellpadding="0" border="0" class="browseRow">' +
	'<tbody><tr><td height="18" width="40%" valign="top" class="browseRow">' +
	'<b>Terms Description:</b>' + termsDescription + '</td>' +
	'<td height="18" width="30%" valign="top" class="browseRow">' +
	'<b>Discount:</b>' + discount +	'</td>'+
	'<td height="18" width="30%" valign="top" class="browseRow"> '+
	'<b>Terms Days:</b>' + termsDays + '</td>' +
	'</tr>' + '</tbody></table></td></tr></tbody></table></div>';

	frm.InvoiceHeader_termsDiscdays[number].value = termsDays;
	frm.InvoiceHeader_termsDiscperc[number].value = discount;
	frm.InvoiceHeader_invoiceDate[number].value = invoiceDate;
	frm.InvoiceHeader_icIvcHeader[number].value = icIvcHeader;
	frm.InvoiceHeader_invoiceSubtotal[number].value = subTotal;
	frm.InvoiceHeader_udf9Code[number].value = udf9Code;
	frm.InvoiceHeader_udf10Code[number].value = udf10Code;

	var hiddenPart = '<input type=\"hidden\" name=\"InvoiceHeader_termsDiscdays\" value=\"\">';
	hiddenPart += '<input type=\"hidden\" name=\"InvoiceHeader_termsDiscperc\" value="">';
	hiddenPart += '<input type=\"hidden\" name=\"InvoiceHeader_invoiceDate\" value="">';
	hiddenPart += '<input type=\"hidden\" name=\"InvoiceHeader_icIvcHeader\" value="">';
	hiddenPart += '<input type=\"hidden\" name=\"InvoiceHeader_invoiceSubtotal\" value="">';
	hiddenPart += '<input type=\"hidden\" name=\"InvoiceHeader_udf9Code\" value="">';
	hiddenPart += '<input type=\"hidden\" name=\"InvoiceHeader_udf10Code\" value="">';

	myCell.innerHTML = headerPart + detailPart + hiddenPart;
	takeDiscount(number);
}

function takeDiscount(ln)
{
	var percent = nformat(eval(nfilter(frm.InvoiceHeader_termsDiscperc[ln])), 2);
	var discDays = frm.InvoiceHeader_termsDiscdays[ln].value;
	var total = nformat(eval(nfilter(frm.InvoiceHeader_invoiceTotal[ln])), 2);
	var adjustment = nformat(eval(nfilter(frm.InvoiceHeader_invoiceAdjustment[ln])), 2);
	var subTotal = nformat(eval(nfilter(frm.InvoiceHeader_invoiceSubtotal[ln])), 2);

	frm.InvoiceHeader_invoiceDiscount[ln].value = nformat( (total * percent)/100, 2 );

	if (frm.InvoiceHeader_udf9Code[ln].value == 'Y')
	{
		frm.InvoiceHeader_invoiceDiscount[ln].value = nformat( (total * percent)/100, 2 );
	}
	else if (frm.InvoiceHeader_udf10Code[ln].value == 'Y')
	{
		frm.InvoiceHeader_invoiceDiscount[ln].value = nformat( (subTotal * percent)/100, 2 );
	}

	var discount = nformat(eval(nfilter(frm.InvoiceHeader_invoiceDiscount[ln])), 2);

	if ( (frm.c_checkbox2.length && frm.c_checkbox2[ln].checked) || frm.c_checkbox2.checked )
	{
		frm.InvoiceHeader_invoicePaid[ln].value = nformat( total - discount - adjustment , 2 );
	}
	else
	{
		var invoiceDateString = (frm.InvoiceHeader_invoiceDate[ln].value).replace('-','/');
		var invoiceDate = new Date(invoiceDateString);
		invoiceDate.setTime(invoiceDate.getTime() + discDays * 24 * 60 * 60 * 1000);
		var month = invoiceDate.getMonth() + 1;
		if (month < 9) month = '0' + month;
		var day = invoiceDate.getDate();
		if (day < 9) day = '0' + day;
		var invoiceDateLimit = month + '-' + day + '-' + invoiceDate.getFullYear();
		var today = new Date();
		var month = today.getMonth() + 1;
		if (month < 9) month = '0' + month;
		var day = today.getDate();
		if (day < 9) day = '0' + day;
		var todayString = month + '-' + day + '-' + today.getFullYear();

		if (compareDateValuesGreaterThan(invoiceDateLimit,todayString) == true )
		{
			frm.InvoiceHeader_invoicePaid[ln].value = nformat( total - adjustment - discount , 2 );
		}
		else
		{
			frm.InvoiceHeader_invoicePaid[ln].value = nformat( total - adjustment, 2 );
		}
	}

	calculateTotals();
}

function deleteMe(row)
{
	var ckboxElements = frm.elements.item("c_checkbox");
	ckboxElements(row).disabled = true;

	var myTable = document.getElementById("browseRows");
	var oRow = myTable.rows[row];
//	oRow.deleteCell(0); // not used because hidden were being messed up
	oCell = oRow.cells[0];
	oCell.style.display="none";

	frm.InvoiceHeader_invoiceTotal[row].value = 0;
	frm.InvoiceHeader_invoiceDiscount[row].value = 0;
	frm.InvoiceHeader_invoiceAdjustment[row].value = 0;
	frm.InvoiceHeader_invoicePaid[row].value = 0;

	calculateTotals();
}

function calculateTotals()
{
	var ckboxElements = frm.elements.item("c_checkbox");
	var discounts = frm.elements.item("InvoiceHeader_invoiceDiscount");
	var recordSelected = false;
	var discountTotal = '0';
	var amountTotal = '0';
	var adjustmentTotal = '0';
	var paidTotal = '0';

	if ( ckboxElements.length )
	{
		for (var i = ckboxElements.length - 1; i >= 0 ; i--)
		{
			if (ckboxElements(i).checked)
			{
				discountTotal = parseFloat(discountTotal) + parseFloat(frm.InvoiceHeader_invoiceDiscount[i].value);
				amountTotal = parseFloat(amountTotal) + parseFloat(frm.InvoiceHeader_invoiceTotal[i].value);
				adjustmentTotal = parseFloat(adjustmentTotal) + parseFloat(frm.InvoiceHeader_invoiceAdjustment[i].value);
				paidTotal = parseFloat(paidTotal) + parseFloat(frm.InvoiceHeader_invoicePaid[i].value);
			}
		}
	}
	else
	{
		discountTotal = parseFloat(discountTotal) + parseFloat(frm.InvoiceHeader_invoiceDiscount[0].value);
		amountTotal = parseFloat(amountTotal) + parseFloat(frm.InvoiceHeader_invoiceTotal[0].value);
		adjustmentTotal = parseFloat(adjustmentTotal) + parseFloat(frm.InvoiceHeader_invoiceAdjustment[0].value);
		paidTotal = parseFloat(paidTotal) + parseFloat(frm.InvoiceHeader_invoicePaid[0].value);
	}
	frm.totalAmount.value = nformat(amountTotal,2);
	frm.totalDiscount.value = nformat(discountTotal,2);
	frm.totalAdjustment.value = nformat(adjustmentTotal,2);
	frm.totalPaid.value = nformat(paidTotal,2);
}