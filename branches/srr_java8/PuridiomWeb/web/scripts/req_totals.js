frm = document.purchaseform;

var dollarDecimals = 2;
/*
var fldObject = null;
var fldFromObject = null;
var fldToObject = null;
var fldSession = 'GCS_SESSION';
var taxArray = new Array();
var populated = false;
*/


/*distribute shipping charges*/
function distributeShipping ()
{
	var hdr_ship_amount = nfilter(frm.RequisitionHeader_shippingCharges);

	hdr_ship_amount = nformat(hdr_ship_amount,2);
	frm.RequisitionHeader_shippingCharges.value = hdr_ship_amount;

	if (rowcount < 1)
	{
		/*if no line items*/
		headerTotal();
	}
	else if (rowcount > 1)
	{
		var lin_ship_total = 0;
		var diff = 0;
		var lin_ship_amount = 0;
		var req_subtotal = frm.RequisitionHeader_subtotal.value - frm.RequisitionHeader_discountAmount.value;
		var lin_subtotal = 0;

		for (var i = 0; i < rowcount; i++)
		{
			lin_subtotal = frm.computed_subtotal[i].value - frm.RequisitionLine_discountAmount[i].value;
			if (req_subtotal != 0)
			{
				lin_ship_amount = eval(nformat((lin_subtotal/req_subtotal) * hdr_ship_amount,2));
			}
			else
			{
				lin_ship_amount = eval(nformat(hdr_ship_amount / rowcount,2));
			}
			frm.RequisitionLine_shippingCharges[i].value = nformat(lin_ship_amount,2);
			lin_ship_total = lin_ship_total + lin_ship_amount;
			if ((ship_ovr == "Y") && (i == 0))
			{
				alert("Header Shipping will now be distributed across all line items.");
			}
			frm.RequisitionLine_shipOvr[i].value = "N";
		}

		/*make sure sum of line item values equals what was entered at the header*/
		diff = eval(hdr_ship_amount) - lin_ship_total;
		if (diff != 0)
		{
			frm.RequisitionLine_shippingCharges[i-1].value = nformat(lin_ship_amount + diff,2);
		}
	}
	else if (rowcount == 1)
	{
		var lin_ship_total = 0;
		var diff = 0;
		var lin_ship_amount = 0;
		var req_subtotal = frm.RequisitionHeader_subtotal.value - frm.RequisitionHeader_discountAmount.value;
		var lin_subtotal = 0;

		lin_subtotal = frm.computed_subtotal.value - frm.RequisitionLine_discountAmount.value;

		if (req_subtotal != 0)
		{
			lin_ship_amount = eval(nformat((lin_subtotal/req_subtotal) * hdr_ship_amount,2));
		}
		else
		{
			lin_ship_amount = eval(nformat(hdr_ship_amount / rowcount,2));
		}
		frm.RequisitionLine_shippingCharges.value = nformat(lin_ship_amount,2);
		lin_ship_total = lin_ship_total + lin_ship_amount;
		if (ship_ovr == "Y")
		{
			alert("Header Shipping will now be distributed across all line items.");
		}
		frm.RequisitionLine_shipOvr.value = "N";

		/*make sure sum of line item values equals what was entered at the header*/
		diff = eval(hdr_ship_amount) - lin_ship_total;

		if (diff != 0)
		{
			frm.RequisitionLine_shippingCharges.value = nformat(lin_ship_amount + diff,2);
		}
	}

	ship_ovr="N";

	if ( (frm.RequisitionHeader_taxShipping.value == "Y") && (eval(frm.RequisitionHeader_taxPercent.value) > 0) && (tax_ovr == "N") )
	{
		calculateShipTax();
	}

	if (rowcount > 0)
	{
		lineTotal();
	}
}

/*distribute other charges*/
function distributeOther ()
{
	var hdr_other_amount = nfilter(frm.RequisitionHeader_otherCharges);
	hdr_other_amount = nformat(hdr_other_amount,2);
	frm.RequisitionHeader_otherCharges.value = hdr_other_amount;

	if (rowcount < 1)
	{
		/*if no line items*/
		headerTotal();
	}
	else if (rowcount > 1)
	{
		var lin_other_total = 0;
		var diff = 0;
		var lin_other_amount = 0;
		var req_subtotal = frm.RequisitionHeader_subtotal.value - frm.RequisitionHeader_discountAmount.value;
		var lin_subtotal = 0;
		for (var i = 0; i < rowcount; i++)
		{
			lin_subtotal = frm.computed_subtotal[i].value - frm.RequisitionLine_discountAmount[i].value;
			if (req_subtotal != 0)
			{
				lin_other_amount = eval(nformat((lin_subtotal/req_subtotal) * hdr_other_amount,2));
			}
			else
			{
				lin_other_amount = eval(nformat(hdr_other_amount/rowcount,2));
			}
			frm.RequisitionLine_otherCharges[i].value = nformat(lin_other_amount,2);
			lin_other_total = lin_other_total + lin_other_amount;
			if ((other_ovr == "Y") && (i == 0))
			{
				alert("Header Other will now be distributed across all line items.");
			}
			frm.RequisitionLine_otherOvr[i].value = "N";
		}
		/*make sure sum of line item values equals what was entered at the header*/
		diff = eval(hdr_other_amount) - lin_other_total;
		if (diff != 0)
		{
			frm.RequisitionLine_otherCharges[i-1].value = nformat(lin_other_amount + diff,2);
		}
	}
	else if (rowcount == 1)
	{
		var lin_other_total = 0;
		var diff = 0;
		var lin_other_amount = 0;
		var req_subtotal = frm.RequisitionHeader_subtotal.value - frm.RequisitionHeader_discountAmount.value;
		var lin_subtotal = 0;

			lin_subtotal = frm.computed_subtotal.value - frm.RequisitionLine_discountAmount.value;
			if (req_subtotal != 0)
			{
				lin_other_amount = eval(nformat((lin_subtotal/req_subtotal) * hdr_other_amount,2));
			}
			else
			{
				lin_other_amount = eval(nformat(hdr_other_amount/rowcount,2));
			}
			frm.RequisitionLine_otherCharges.value = nformat(lin_other_amount,2);
			lin_other_total = lin_other_total + lin_other_amount;
			if (other_ovr == "Y")
			{
				alert("Header Other will now be distributed across all line items.");
			}
			frm.RequisitionLine_otherOvr.value = "N";

		/*make sure sum of line item values equals what was entered at the header*/
		diff = eval(hdr_other_amount) - lin_other_total;
		if (diff != 0)
		{
			frm.RequisitionLine_otherCharges.value = nformat(lin_other_amount + diff,2);
		}
	}
	other_ovr="N";
	if ( (frm.RequisitionHeader_taxOther.value == "Y") && (eval(frm.RequisitionHeader_taxPercent.value) > 0) && (tax_ovr == "N") )
	{
		calculateOtherTax();
	}
	if (rowcount > 0)
	{
		lineTotal();
	}
}

/*recalculate header total if no line items*/
function headerTotal ()
{
	var req_total = 0;
	req_total = - eval(frm.RequisitionHeader_discountAmount.value) + eval(frm.RequisitionHeader_taxAmount.value) + eval(frm.RequisitionHeader_shippingCharges.value) + eval(frm.RequisitionHeader_shippingTaxAmt.value) + eval(frm.RequisitionHeader_otherCharges.value) + eval(frm.RequisitionHeader_otherTaxAmount.value);
	frm.RequisitionHeader_total.value = nformat(req_total,2);
}

/*recalculate req total based on each line total*/
function total ()
{
	var req_total = 0;
	lin_total = 0;

	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			lin_total = eval(nformat(frm.RequisitionLine_lineTotal[i].value,2));
			req_total = req_total + lin_total;
		}
	}
	else
	{
		lin_total = eval(nformat(frm.RequisitionLine_lineTotal.value,2));
		req_total = req_total + lin_total;
	}

	frm.RequisitionHeader_total.value = nformat(req_total,2);
}

/*recalculate total for each line*/
function lineTotal ()
{
	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			frm.RequisitionLine_lineTotal[i].value = eval(nformat(frm.computed_subtotal[i].value,2)) - eval(nformat(frm.RequisitionLine_discountAmount[i].value,2)) + eval(nformat(frm.RequisitionLine_taxAmount[i].value,2)) + eval(nformat(frm.RequisitionLine_shippingCharges[i].value,2)) + eval(nformat(frm.RequisitionLine_shippingTaxAmt[i].value,2)) + eval(nformat(frm.RequisitionLine_otherCharges[i].value,2)) + eval(nformat(frm.RequisitionLine_otherTaxAmount[i].value,2));
			frm.RequisitionLine_lineTotal[i].value = nformat(frm.RequisitionLine_lineTotal[i].value,2);
		}
	}
	else
	{
		frm.RequisitionLine_lineTotal.value = eval(nformat(frm.computed_subtotal.value,2)) - eval(nformat(frm.RequisitionLine_discountAmount.value,2)) + eval(nformat(frm.RequisitionLine_taxAmount.value,2)) + eval(nformat(frm.RequisitionLine_shippingCharges.value,2)) + eval(nformat(frm.RequisitionLine_shippingTaxAmt.value,2)) + eval(nformat(frm.RequisitionLine_otherCharges.value,2)) + eval(nformat(frm.RequisitionLine_otherTaxAmount.value,2));
		frm.RequisitionLine_lineTotal.value = nformat(frm.RequisitionLine_lineTotal.value,2);
	}
	total();
}

/*recalculate total of a particular column*/
function totalColumn(headerField,lineField)
{
	column_total = 0;

	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			column_total = column_total + eval(nformat(lineField[i].value,2));
		}
	}
	else if (rowcount == 1)
	{
		column_total = column_total + eval(nformat(lineField.value,2));
	}

	headerField.value = nformat(column_total,2);
}

/*calculate new tax/discount amount when tax/discount percent is changed*/
function changeHeaderAmount (formField)
{
	if (rowcount >= 1)
	{
		/*if more than one line item*/
		changeHeaderAmountRow (formField);
		return;
	}
	else if (rowcount < 1)
	{
		if (formField == frm.RequisitionHeader_discountPercent)
		{
			frm.RequisitionHeader_discountAmount.value = "0.00";
		}
		if (formField == frm.RequisitionHeader_taxPercent)
		{
			frm.RequisitionHeader_taxAmount.value = "0.00"
		}
		calculateShipTax();
		calculateOtherTax();
		headerTotal();
		return;
	}
}

function changeHeaderAmountRow (formField)
{
	var tax_perc = eval(nfilter(frm.RequisitionHeader_taxPercent));
	var disc_perc = eval(nfilter(frm.RequisitionHeader_discountPercent));
	var lin_tax_amount = 0;
	var lin_disc_amount = 0;
	var hdr_tax_amount = 0;
	var hdr_disc_amount = 0;
	var hdr_subtotal = eval(nfilter(frm.RequisitionHeader_subtotal));
	var hdr_taxable_subtotal = 0;
	var lin_perc = 0;
	var runTax = 0;

	if (formField == frm.RequisitionHeader_discountPercent)
	{
		frm.RequisitionHeader_discountPercent.value = nformat(disc_perc, 2);

		hdr_disc_amount = eval(nformat(hdr_subtotal * disc_perc * .01,2));
		frm.RequisitionHeader_discountAmount.value = nformat(hdr_disc_amount, dollarDecimals);

		if (rowcount > 1)
		{
			for (var i = 0; i < rowcount; i++)
			{
				if (hdr_subtotal != 0) {
					lin_perc = eval(frm.computed_subtotal[i].value / hdr_subtotal);
				} else {
					lin_perc = 0;
				}
				lin_disc_amount = eval(nformat(lin_perc * hdr_disc_amount, dollarDecimals));
				frm.RequisitionLine_discountPercent[i].value = nformat(disc_perc, 2);
				frm.RequisitionLine_discountAmount[i].value = nformat(lin_disc_amount, dollarDecimals);

				if ((disc_ovr == "Y") && (i == 0))
				{
					alert("Discount for all line items will now be calculated based on Header Discount Percent.");
				}
				frm.RequisitionLine_discOvr[i].value = "N";
			}
		}
		else
		{
			if (hdr_subtotal != 0) {
				lin_perc = eval(frm.computed_subtotal.value / hdr_subtotal);
			} else {
				lin_perc = 0;
			}
			lin_disc_amount = eval(nformat(lin_perc * hdr_disc_amount, dollarDecimals));
			frm.RequisitionLine_discountPercent.value = nformat(disc_perc, 2);
			frm.RequisitionLine_discountAmount.value = nformat(lin_disc_amount, dollarDecimals);

			if (disc_ovr == "Y")
			{
				alert("Discount for line item will now be calculated based on Header Discount Percent.");
			}
			frm.RequisitionLine_discOvr.value = "N";
		}
	}

	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			if (frm.RequisitionLine_taxable[i].value == 'Y')
			{
				hdr_taxable_subtotal = hdr_taxable_subtotal + eval(frm.computed_subtotal[i].value - frm.RequisitionLine_discountAmount[i].value);
			}
		}
	}
	else
	{
		if (frm.RequisitionLine_taxable.value == 'Y')
		{
			hdr_taxable_subtotal = hdr_taxable_subtotal + eval(frm.computed_subtotal.value - frm.RequisitionLine_discountAmount.value);
		}
	}

	hdr_tax_amount = eval(nformat(hdr_taxable_subtotal * tax_perc * .01, 5));
	frm.RequisitionHeader_taxAmount.value = nformat(hdr_tax_amount,2);
	frm.RequisitionHeader_taxPercent.value = nformat(tax_perc, 5);

	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			if (formField == frm.RequisitionHeader_taxPercent)
			{
				frm.RequisitionLine_taxPercent[i].value = nformat(tax_perc, 5);
				if ((tax_ovr == "Y") && (i == 0))
				{
					alert("Tax for all line items will now be calculated based on Header Tax Percent.");
				}
				frm.RequisitionLine_taxOvr[i].value = "N";
			}
			if (frm.RequisitionLine_taxable[i].value == 'Y')
			{
				if (hdr_taxable_subtotal != 0) {
					lin_perc = eval((frm.computed_subtotal[i].value - frm.RequisitionLine_discountAmount[i].value) / hdr_taxable_subtotal);
				} else {
					lin_perc = 0;
				}
				lin_tax_amount = eval(lin_perc * hdr_tax_amount);

				if((i+1) == rowcount)
				{
					runTax1 = hdr_tax_amount - runTax;
					frm.RequisitionLine_taxAmount[i].value = nformat(runTax1, 2);
					runTax = 0;
				}
				else
				{
					runTax = runTax + lin_tax_amount;
					frm.RequisitionLine_taxAmount[i].value = nformat(lin_tax_amount,2);
				}
			}
		}
	}
	else
	{
		if (formField == frm.RequisitionHeader_taxPercent)
		{
			frm.RequisitionLine_taxPercent.value = nformat(tax_perc, 5);
			if (tax_ovr == "Y")
			{
				alert("Tax for all line items will now be calculated based on Header Tax Percent.");
			}
			frm.RequisitionLine_taxOvr.value = "N";
		}
		if (frm.RequisitionLine_taxable.value == 'Y')
		{
			if (hdr_taxable_subtotal != 0) {
				lin_perc = eval((frm.computed_subtotal.value - frm.RequisitionLine_discountAmount.value) / hdr_taxable_subtotal);
			} else {
				lin_perc = 0;
			}
			lin_tax_amount = eval(lin_perc * hdr_tax_amount);
			runTax = runTax + lin_tax_amount;
			frm.RequisitionLine_taxAmount.value = nformat(lin_tax_amount,2);
		}
	}

	if (formField == frm.RequisitionHeader_discountPercent)
	{
		disc_ovr = "N";
	}
	if (formField == frm.RequisitionHeader_taxPercent)
	{
		tax_ovr = "N";
		calculateShipTax();
		calculateOtherTax();
	}
	lineTotal();
}

/*distribute tax when header tax amount entered directly*/
function distributeTax ()
{
	var hdr_tax_amount = nfilter(frm.RequisitionHeader_taxAmount);
	hdr_tax_amount = nformat(hdr_tax_amount,2);
	frm.RequisitionHeader_taxAmount.value = hdr_tax_amount;
	frm.RequisitionHeader_taxPercent.value = '0.0';

	if (rowcount < 1)
	{
		/*if no line items*/
		headerTotal();
		return;
	}

	var taxable_subtotal = 0;
	var total_tax = 0;
	var line_tax = 0;

	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++) /*get subtotal of lines that are taxable*/
		{
			frm.RequisitionLine_taxPercent[i].value = '0.0';
			if (frm.RequisitionLine_taxable[i].value == 'Y') {
				taxable_subtotal = taxable_subtotal + eval(nformat(frm.computed_subtotal[i].value - frm.RequisitionLine_discountAmount[i].value,2));
			}
		}
		for (var i = 0; i < rowcount; i++)  /*disbribute tax to lines that are taxable*/
		{
			if (frm.RequisitionLine_taxable[i].value == 'Y') {
				line_taxable = nformat(frm.computed_subtotal[i].value - frm.RequisitionLine_discountAmount[i].value,2);
				line_tax = eval(nformat((line_taxable/taxable_subtotal)*hdr_tax_amount,2));
				frm.RequisitionLine_taxAmount[i].value = nformat(line_tax,2);
				total_tax = total_tax + line_tax ;
			}
			if ((tax_ovr == "Y") && (i == 0)) {
				alert("Header Tax will now be distributed across all line items.");
			}
			frm.RequisitionLine_taxOvr[i].value = "N";
		}
		tax_ovr = "N";
		/*make sure sum of line item values equals what was entered at the header*/
		diff = eval(hdr_tax_amount) - total_tax;
		if (diff != 0){
			frm.RequisitionLine_taxAmount[i-1].value = nformat(line_tax + diff,2);
		}
	}
	else
	{
		frm.RequisitionLine_taxPercent.value = '0.0';
		if (frm.RequisitionLine_taxable.value == 'Y')
		{
			taxable_subtotal = taxable_subtotal + eval(nformat(frm.computed_subtotal.value - frm.RequisitionLine_discountAmount.value,2));
		}

		if (frm.RequisitionLine_taxable.value == 'Y')
		{
			line_taxable = nformat(frm.computed_subtotal.value - frm.RequisitionLine_discountAmount.value,2);
			line_tax = eval(nformat((line_taxable/taxable_subtotal)*hdr_tax_amount,2));
			frm.RequisitionLine_taxAmount.value = nformat(line_tax,2);
			total_tax = total_tax + line_tax ;
		}
		if (tax_ovr == "Y")
		{
			alert("Header Tax will now be distributed across all line items.");
		}
		frm.RequisitionLine_taxOvr.value = "N";

		tax_ovr = "N";
		/*make sure sum of line item values equals what was entered at the header*/
		diff = eval(hdr_tax_amount) - total_tax;
		if (diff != 0)
		{
			frm.RequisitionLine_taxAmount.value = nformat(line_tax + diff, 2);
		}
	}

	lineTotal();
}


function populateTaxes(ta)
{
	var e = 0;

	for (var i = 0; i < ta.length; i++)
	{
		taxArray[e] = new Array(ta[i][0], ta[i][1]);
		e++;
	}

	populated = true;
}

function distributeDiscount ()
{
	/*distribute discount when header discount amount entered directly*/
	if (rowcount >= 1)
	{
		/*if there are line items*/
		distributeDiscountRow ();
		return;
	}

	var hdr_disc_amount = nfilter(frm.RequisitionHeader_discountAmount);
	hdr_disc_amount = nformat(hdr_disc_amount,2);
	frm.RequisitionHeader_discountAmount.value = hdr_disc_amount;
	frm.RequisitionHeader_discountPercent.value = "0.0";

	if (rowcount < 1)
	{
		/*if no lines, total header and return*/
		headerTotal();
		return;
	}
}

function distributeDiscountRow()
{
	/*distribute discount when header discount amount entered directly*/
	var line_total = 0;
	var diff = 0;
	var line_amount = 0;
	var req_subtotal = frm.RequisitionHeader_subtotal.value;
	var lin_subtotal = 0;
	var hdr_disc_amount = nfilter(frm.RequisitionHeader_discountAmount);
	var tax_perc = 0;

	hdr_disc_amount = nformat(hdr_disc_amount,2);
	frm.RequisitionHeader_discountAmount.value = hdr_disc_amount;

	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			lin_subtotal = frm.computed_subtotal[i].value;
			if (req_subtotal != 0) {
				line_amount = eval(nformat((lin_subtotal/req_subtotal) * hdr_disc_amount,2));
			} else {
				line_amount = eval(nformat(hdr_disc_amount / rowcount,2));
			}
			frm.RequisitionLine_discountAmount[i].value = nformat(line_amount,2);
			line_total = line_total + line_amount;
			frm.RequisitionLine_discountPercent[i].value = '0.0';
			if ((disc_ovr == "Y") && (i == 0)) {
				alert("Header Discount will now be distributed across all line items.");
			}
			frm.RequisitionLine_discOvr[i].value = "N";
			tax_perc = frm.RequisitionLine_taxPercent[i].value;
			if ((tax_perc != '0.0') && (frm.RequisitionLine_taxable[i].value == 'Y')) {
				frm.RequisitionLine_taxAmount[i].value = nformat((frm.computed_subtotal[i].value - frm.RequisitionLine_discountAmount[i].value) * tax_perc * .01,2);
			}
		}
		disc_ovr = "N";
		frm.RequisitionHeader_discountPercent.value = '0.0';
		/*make sure sum of line item values equals what was entered at the header*/
		diff = eval(hdr_disc_amount) - line_total;
		if (diff != 0){
			frm.RequisitionLine_discountAmount[i-1].value = nformat(line_amount + diff,2);
		}
	}
	else
	{
		lin_subtotal = frm.computed_subtotal.value;
		if (req_subtotal != 0)
		{
			line_amount = eval(nformat((lin_subtotal/req_subtotal) * hdr_disc_amount,2));
		}
		else
		{
			line_amount = eval(nformat(hdr_disc_amount / rowcount,2));
		}
		frm.RequisitionLine_discountAmount.value = nformat(line_amount,2);
		line_total = line_total + line_amount;
		frm.RequisitionLine_discountPercent.value = '0.0';
		if (disc_ovr == "Y")
		{
			alert("Header Discount will now be distributed across all line items.");
		}
		frm.RequisitionLine_discOvr.value = "N";
		tax_perc = frm.RequisitionLine_taxPercent.value;
		if ((tax_perc != '0.0') && (frm.RequisitionLine_taxable.value == 'Y'))
		{
			frm.RequisitionLine_taxAmount.value = nformat((frm.computed_subtotal.value - frm.RequisitionLine_discountAmount.value) * tax_perc * .01,2);
		}
		disc_ovr = "N";
		frm.RequisitionHeader_discountPercent.value = '0.0';
		/*make sure sum of line item values equals what was entered at the header*/
		diff = eval(hdr_disc_amount) - line_total;
		if (diff != 0)
		{
			frm.RequisitionLine_discountAmount.value = nformat(line_amount + diff, 2);
		}
	}
	lineTotal();
	totalColumn(frm.RequisitionHeader_taxAmount,frm.RequisitionLine_taxAmount);
}

function changeLineAmount(formField,x)
{
	/*recalculates line amounts and header total when any line amount is changed*/
	if (rowcount >= 1)
	{
		/*if there are line items*/
		changeLineAmountRow (formField,x);
	}
	return;
}

function changeLineAmountRow (formField,x)
{
	/*recalculates line amounts and header total when any line amount is changed*/
	var line_tax = 0;

	if (rowcount > 1)
	{
		formField[x].value = nformat(nfilter(formField[x]), 2);
	}
	else
	{
		formField.value = nformat(nfilter(formField), 2);
	}

	if (formField == frm.RequisitionLine_discountPercent)
	{
		if (rowcount > 1)
		{
			frm.RequisitionLine_discountAmount[x].value = nformat(frm.computed_subtotal[x].value * frm.RequisitionLine_discountPercent[x].value * .01,dollarDecimals);
		}
		else
		{
			frm.RequisitionLine_discountAmount.value = nformat(frm.computed_subtotal.value * frm.RequisitionLine_discountPercent.value * .01,dollarDecimals);
		}
		totalColumn(frm.RequisitionHeader_discountAmount,frm.RequisitionLine_discountAmount);
		if (disc_ovr == "N")
		{
			alert("Header Discount amount will now be based on the Discount amount entered/calculated at each line item.");
		}
		setFlag(frm.RequisitionLine_discOvr,"Y");
	}
	if (formField == frm.RequisitionLine_discountAmount)
	{
		if (rowcount > 1)
		{
			frm.RequisitionLine_discountPercent[x].value = '0.0';
		}
		else
		{
			frm.RequisitionLine_discountPercent.value = '0.0';
		}
		totalColumn(frm.RequisitionHeader_discountAmount,frm.RequisitionLine_discountAmount);
		if (disc_ovr == "N")
		{
			alert("Header Discount amount will now be based on the Discount amount entered/calculated at each line item.");
		}
		setFlag(frm.RequisitionLine_discOvr,"Y");
	}

	if (rowcount > 1)
	{
		if ((formField == frm.RequisitionLine_taxPercent) || (((formField == frm.RequisitionLine_discountPercent) || (formField == frm.RequisitionLine_discountAmount)) && (eval(frm.RequisitionLine_taxPercent[x].value) != 0)))
		{
			line_tax = (eval(nformat(frm.computed_subtotal[x].value - frm.RequisitionLine_discountAmount[x].value,2)))* frm.RequisitionLine_taxPercent[x].value * .01;
			frm.RequisitionLine_taxAmount[x].value = nformat(line_tax,2);
			frm.RequisitionLine_taxPercent[x].value = nformat(frm.RequisitionLine_taxPercent[x].value, 2);
			totalColumn(frm.RequisitionHeader_taxAmount,frm.RequisitionLine_taxAmount);
			if (formField == frm.RequisitionLine_taxPercent){
				if (tax_ovr == "N") {
					alert("Header Tax amount will now be based on the Tax amount entered/calculated at each line item.");
				}
				setFlag(frm.RequisitionLine_taxOvr,"Y");
				calculateShipTax();
				calculateOtherTax();
			}
		}
	}
	else
	{
		if ((formField == frm.RequisitionLine_taxPercent) || (((formField == frm.RequisitionLine_discountPercent) || (formField == frm.RequisitionLine_discountAmount)) && (eval(frm.RequisitionLine_taxPercent.value) != 0)))
		{
			line_tax = (eval(nformat(frm.computed_subtotal.value - frm.RequisitionLine_discountAmount.value,2)))* frm.RequisitionLine_taxPercent.value * .01;
			frm.RequisitionLine_taxAmount.value = nformat(line_tax,2);
			frm.RequisitionLine_taxPercent.value = nformat(frm.RequisitionLine_taxPercent.value, 2);
			totalColumn(frm.RequisitionHeader_taxAmount,frm.RequisitionLine_taxAmount);
			if (formField == frm.RequisitionLine_taxPercent)
			{
				if (tax_ovr == "N")
				{
					alert("Header Tax amount will now be based on the Tax amount entered/calculated at each line item.");
				}
				setFlag(frm.RequisitionLine_taxOvr,"Y");
				calculateShipTax();
				calculateOtherTax();
			}
		}
	}

	if (formField == frm.RequisitionLine_taxAmount)
	{
		if (rowcount > 1)
		{
			frm.RequisitionLine_taxPercent[x].value = '0.0';
		}
		else
		{
			frm.RequisitionLine_taxPercent.value = '0.0';
		}
		totalColumn(frm.RequisitionHeader_taxAmount,frm.RequisitionLine_taxAmount);
		if (tax_ovr == "N")
		{
			alert("Header Tax amount will now be based on the Tax amount entered/calculated at each line item.");
		}
		setFlag(frm.RequisitionLine_taxOvr,"Y");
	}

	if (formField == frm.RequisitionLine_shippingCharges)
	{
		totalColumn(frm.RequisitionHeader_shippingCharges,frm.RequisitionLine_shippingCharges);
		if (ship_ovr == "N")
		{
			alert("Header Shipping amount will now be based on the Shipping amount entered at each line item.");
		}
		setFlag(frm.RequisitionLine_shipOvr,"Y");

		if (rowcount > 1)
		{
			//if ( (frm.RequisitionHeader_taxShipping.value == "Y") && (eval(frm.RequisitionLine_taxPercent[x].value) > 0) && (tax_ovr == "N") )
			if ( (frm.RequisitionHeader_taxShipping.value == "Y") && (eval(frm.RequisitionLine_taxPercent[x].value) > 0)  )
			{
				frm.RequisitionLine_shippingTaxAmt[x].value = nformat(frm.RequisitionLine_shippingCharges[x].value * frm.RequisitionLine_taxPercent[x].value * .01, 2);
				totalColumn(frm.RequisitionHeader_shippingTaxAmt,frm.RequisitionLine_shippingTaxAmt);
			}
		}
		else
		{
			//if ( (frm.RequisitionHeader_taxShipping.value == "Y") && (eval(frm.RequisitionLine_taxPercent.value) > 0) && (tax_ovr == "N") )
			if ( (frm.RequisitionHeader_taxShipping.value == "Y") && (eval(frm.RequisitionLine_taxPercent.value) > 0) )
			{
				frm.RequisitionLine_shippingTaxAmt.value = nformat(frm.RequisitionLine_shippingCharges.value * frm.RequisitionLine_taxPercent.value * .01, 2);
				totalColumn(frm.RequisitionHeader_shippingTaxAmt,frm.RequisitionLine_shippingTaxAmt);
			}
		}
	}

	if (formField == frm.RequisitionLine_shippingTaxAmt)
	{
		totalColumn(frm.RequisitionHeader_shippingTaxAmt,frm.RequisitionLine_shippingTaxAmt);
	}

	if (formField == frm.RequisitionLine_otherCharges)
	{
		totalColumn(frm.RequisitionHeader_otherCharges,frm.RequisitionLine_otherCharges);
		if (other_ovr == "N")
		{
			alert("Header Other amount will now be based on the Other amount entered at each line item.");
		}
		setFlag(frm.RequisitionLine_otherOvr,"Y");

		if (rowcount > 1)
		{
			//if ( (frm.RequisitionHeader_taxOther.value == "Y") && (eval(frm.RequisitionLine_taxPercent[x].value) > 0) && (tax_ovr == "N") )
			if ( (frm.RequisitionHeader_taxOther.value == "Y") && (eval(frm.RequisitionLine_taxPercent[x].value) > 0) )
			{
				frm.RequisitionLine_otherTaxAmount[x].value = nformat(frm.RequisitionLine_otherCharges[x].value * frm.RequisitionLine_taxPercent[x].value * .01, 2);
				totalColumn(frm.RequisitionHeader_otherTaxAmount,frm.RequisitionLine_otherTaxAmount);
			}
		}
		else
		{
			//if ( (frm.RequisitionHeader_taxOther.value == "Y") && (eval(frm.RequisitionLine_taxPercent.value) > 0) && (tax_ovr == "N") )
			if ( (frm.RequisitionHeader_taxOther.value == "Y") && (eval(frm.RequisitionLine_taxPercent.value) > 0) )
			{
				frm.RequisitionLine_otherTaxAmount.value = nformat(frm.RequisitionLine_otherCharges.value * frm.RequisitionLine_taxPercent.value * .01, 2);
				totalColumn(frm.RequisitionHeader_otherTaxAmount,frm.RequisitionLine_otherTaxAmount);
			}
		}
	}

	if (formField == frm.RequisitionLine_otherTaxAmount)
	{
		totalColumn(frm.RequisitionHeader_otherTaxAmount,frm.RequisitionLine_otherTaxAmount);
	}

	if ((formField != frm.RequisitionLine_taxPercent) && (formField != frm.RequisitionLine_discountPercent))
	{
		if (rowcount > 1)
		{
			formField[x].value = nformat(formField[x].value,2);
		}
		else
		{
			formField.value = nformat(formField.value,2);
		}
	}

	lineTotal();
}

function calculateShipTax()
{
	/*calculates shipping tax based on header tax percentage*/
	if ( (frm.RequisitionHeader_taxShipping.value == "Y") )
	{
		if (tax_ovr == "N")
		{
			if (rowcount < 1)
			{
				frm.RequisitionHeader_shippingTaxAmt.value = nformat(frm.RequisitionHeader_shippingCharges.value * frm.RequisitionHeader_taxPercent.value * .01, 2);
				headerTotal();
				return;
			}
			frm.RequisitionHeader_shippingTaxAmt.value = nformat(frm.RequisitionHeader_shippingCharges.value * frm.RequisitionHeader_taxPercent.value * .01, 2);
			distributeShipTax();
		}
		else
		{
			if (rowcount < 1)
			{
				frm.RequisitionHeader_shippingTaxAmt.value = nformat(frm.RequisitionHeader_shippingCharges.value * frm.RequisitionHeader_taxPercent.value * .01, 2);
				headerTotal();
				return;
			}

			var hdr_ship_tax = 0.00;
			var ln_ship_tax = 0.00;
			if (rowcount > 1)
			{
				for (var i = 0; i < rowcount; i++)
				{
					ln_ship_tax = eval(nformat(frm.RequisitionLine_shippingCharges[i].value * frm.RequisitionLine_taxPercent[i].value * .01, 2));
					frm.RequisitionLine_shippingTaxAmt[i].value = ln_ship_tax;
					hdr_ship_tax = eval(hdr_ship_tax + ln_ship_tax);
				}
			}
			else
			{
				frm.RequisitionLine_shippingTaxAmt.value = nformat(frm.RequisitionLine_shippingCharges.value * frm.RequisitionLine_taxPercent.value * .01, 2);
				hdr_ship_tax = frm.RequisitionLine_shippingTaxAmt.value;
			}
			frm.RequisitionHeader_shippingTaxAmt.value = nformat(hdr_ship_tax,2);
			lineTotal();
		}
	}
	else
	{
		frm.RequisitionHeader_shippingTaxAmt.value = "0.00";
		if (rowcount < 1)
		{
			headerTotal();
			return;
		}

		if (rowcount > 1)
		{
			for (var i = 0; i < rowcount; i++)
			{
				frm.RequisitionLine_shippingTaxAmt[i].value = "0.00";
			}
		}
		else
		{
			frm.RequisitionLine_shippingTaxAmt.value = "0.00";
		}
	}
}

function calculateOtherTax()
{
	/*calculates other tax based on header tax percentage*/
	if ( frm.RequisitionHeader_taxOther.value == "Y" )
	{
		if (tax_ovr == "N")
		{
			if (rowcount < 1)
			{
				frm.RequisitionHeader_otherTaxAmount.value = nformat(frm.RequisitionHeader_otherCharges.value * frm.RequisitionHeader_taxPercent.value * .01, 2);
				headerTotal();
				return;
			}
			frm.RequisitionHeader_otherTaxAmount.value = nformat(frm.RequisitionHeader_otherCharges.value * frm.RequisitionHeader_taxPercent.value * .01, 2);
			distributeOtherTax();
		}
		else
		{
			if (rowcount < 1)
			{
				frm.RequisitionHeader_otherTaxAmount.value = nformat(frm.RequisitionHeader_otherCharges.value * frm.RequisitionHeader_taxPercent.value * .01, 2);
				headerTotal();
				return;
			}

			var hdr_other_tax = 0.00;
			var ln_other_tax = 0.00;
			if (rowcount > 1)
			{
				for (var i = 0; i < rowcount; i++)
				{
					ln_other_tax = eval(nformat(frm.RequisitionLine_otherCharges[i].value * frm.RequisitionLine_taxPercent[i].value * .01, 2));
					frm.RequisitionLine_otherTaxAmount[i].value = ln_other_tax;
					hdr_other_tax = eval(nformat(hdr_other_tax + ln_other_tax, 2));
				}
			}
			else
			{
				frm.RequisitionLine_otherTaxAmount.value = nformat(frm.RequisitionLine_otherCharges.value * frm.RequisitionLine_taxPercent.value * .01, 2);
				hdr_ship_tax = frm.RequisitionLine_otherTaxAmount.value;
			}
			frm.RequisitionHeader_otherTaxAmount.value = nformat(hdr_other_tax,2);
			lineTotal();
		}
	}
	else
	{
		frm.RequisitionHeader_otherTaxAmount.value = "0.00";
		if (rowcount < 1)
		{
			headerTotal();
			return;
		}
		else if (rowcount > 1)
		{
			for (var i = 0; i < rowcount; i++)
			{
				frm.RequisitionLine_otherTaxAmount[i].value = "0.00";
			}
		}
		else if (rowcount == 1)
		{
			frm.RequisitionLine_otherTaxAmount.value = "0.00";
		}
	}
}

function distributeShipTax()
{
	/*distributes shipping tax when entered directly or calculated at the header (tax_ovr = N*/
	var hdr_ship_tax = nfilter(frm.RequisitionHeader_shippingTaxAmt);

	if (rowcount < 1)
	{
		frm.RequisitionHeader_shippingTaxAmt.value = nformat(hdr_ship_tax,2);
		headerTotal();
		return;
	}

	var line_total = 0;
	var diff = 0;
	var line_amount = 0;
	var hdr_shipping = eval(frm.RequisitionHeader_shippingCharges.value);

	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			if (hdr_shipping > 0) {
				line_amount = eval(nformat((frm.RequisitionLine_shippingCharges[i].value/frm.RequisitionHeader_shippingCharges.value) * hdr_ship_tax,2));
			} else {
				line_amount = 0;
			}
			frm.RequisitionLine_shippingTaxAmt[i].value = nformat(line_amount,2);
			line_total = line_total + line_amount;
		}

		diff = eval(nformat(hdr_ship_tax,2)) - line_total;
		if (diff != 0)
		{
			frm.RequisitionLine_shippingTaxAmt[i-1].value = nformat(line_amount + diff,2);
		}
	}
	else
	{
		if (hdr_shipping > 0) {
			line_amount = eval(nformat((frm.RequisitionLine_shippingCharges.value/frm.RequisitionHeader_shippingCharges.value) * hdr_ship_tax,2));
		} else {
			line_amount = 0;
		}
		frm.RequisitionLine_shippingTaxAmt.value = nformat(line_amount,2);
		line_total = line_total + line_amount;

		diff = eval(nformat(hdr_ship_tax,2)) - line_total;
		if (diff != 0)
		{
			frm.RequisitionLine_shippingTaxAmt.value = nformat(line_amount + diff,2);
		}
	}

	frm.RequisitionHeader_shippingTaxAmt.value = nformat(hdr_ship_tax,2);
	lineTotal();
}

function distributeOtherTax()
{
	/*distributes other tax when entered directly*/
	var hdr_other_tax = nfilter(frm.RequisitionHeader_otherTaxAmount);
	if (rowcount < 1)
	{
		frm.RequisitionHeader_otherTaxAmount.value = nformat(hdr_other_tax,2);
		headerTotal();
		return;
	}

	var line_total = 0;
	var diff = 0;
	var line_amount = 0;
	var hdr_other = eval(frm.RequisitionHeader_otherCharges.value);

	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			if (hdr_other > 0) {
				line_amount = eval(nformat((frm.RequisitionLine_otherCharges[i].value/hdr_other) * hdr_other_tax,2));
			} else {
				line_amount = 0;
			}
			frm.RequisitionLine_otherTaxAmount[i].value = nformat(line_amount,2);
			line_total = line_total + line_amount;
		}
		diff = eval(nformat(hdr_other_tax,2)) - line_total;
		if (diff != 0){
			frm.RequisitionLine_otherTaxAmount[i-1].value = nformat(line_amount + diff,2);
		}
	}
	else
	{
		if (hdr_other > 0) {
			line_amount = eval(nformat((frm.RequisitionLine_otherCharges.value/frm.RequisitionHeader_otherCharges.value) * hdr_other_tax,2));
		} else {
			line_amount = 0;
		}
		frm.RequisitionLine_otherTaxAmount.value = nformat(line_amount,2);
		line_total = line_total + line_amount;

		diff = eval(nformat(hdr_other_tax,2)) - line_total;
		if (diff != 0)
		{
			frm.RequisitionLine_otherTaxAmount.value = nformat(line_amount + diff,2);
		}
	}

	frm.RequisitionHeader_otherTaxAmount.value = nformat(hdr_other_tax,2);
	lineTotal();
}

function setFlag (formField, x)
{
	/*this function sets/resets the override flag for discount, tax, shipping and other*/
	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			formField[i].value = x;
		}
	}
	else
	{
		formField.value = x;
	}

	if (formField == frm.RequisitionLine_discOvr)		{ disc_ovr = x;	}
	if (formField == frm.RequisitionLine_taxOvr)		{ tax_ovr = x;		}
	if (formField == frm.RequisitionLine_shipOvr)		{ ship_ovr = x;	}
	if (formField == frm.RequisitionLine_otherOvr)	{ other_ovr = x;	}
}

function checkTotals()
{
	if (submitcount == 0)
	{
		submitcount++;
	}
	else
	{
//		alert("This form has already been submitted.");
//		return false;
	}

	if (rowcount > 0)
	{
		var alertMessage = "";
		var hdr_disc_total = eval(nfilter(frm.RequisitionHeader_discountAmount));
		var hdr_tax_total = eval(nfilter(frm.RequisitionHeader_taxAmount));
		var hdr_ship_total = eval(nfilter(frm.RequisitionHeader_shippingCharges));
		var hdr_shiptax_total = eval(nfilter(frm.RequisitionHeader_shippingTaxAmt));
		var hdr_other_total = eval(nfilter(frm.RequisitionHeader_otherCharges));
		var hdr_othertax_total = eval(nfilter(frm.RequisitionHeader_otherTaxAmount));
		var hdr_total = eval(nfilter(frm.RequisitionHeader_total));
		var lin_disc_total = 0;
		var lin_tax_total = 0;
		var lin_ship_total= 0;
		var lin_shiptax_total = 0;
		var lin_other_total = 0;
		var lin_othertax_total = 0;
		var lin_total = 0;

		if (rowcount > 1)
		{
			for (var i = 0; i < rowcount; i++)
			{
				lin_disc_total = nformat(eval(lin_disc_total) + eval(nfilter(frm.RequisitionLine_discountAmount[i])),2);
				lin_tax_total = nformat(eval(lin_tax_total) + eval(nfilter(frm.RequisitionLine_taxAmount[i])),2);
				lin_ship_total = nformat(eval(lin_ship_total) + eval(nfilter(frm.RequisitionLine_shippingCharges[i])),2);
				lin_shiptax_total = nformat(eval(lin_shiptax_total) + eval(nfilter(frm.RequisitionLine_shippingTaxAmt[i])),2);
				lin_other_total = nformat(eval(lin_other_total) + eval(nfilter(frm.RequisitionLine_otherCharges[i])),2);
				lin_othertax_total = nformat(eval(lin_othertax_total) + eval(nfilter(frm.RequisitionLine_otherTaxAmount[i])),2);
				lin_total = nformat(eval(lin_total) + eval(nfilter(frm.RequisitionLine_line_total[i])),2);
			}
		}
		else
		{
			lin_disc_total = nformat(eval(lin_disc_total) + eval(nfilter(frm.RequisitionLine_discountAmount)),2);
			lin_tax_total = nformat(eval(lin_tax_total) + eval(nfilter(frm.RequisitionLine_taxAmount)),2);
			lin_ship_total = nformat(eval(lin_ship_total) + eval(nfilter(frm.RequisitionLine_shippingCharges)),2);
			lin_shiptax_total = nformat(eval(lin_shiptax_total) + eval(nfilter(frm.RequisitionLine_shippingTaxAmt)),2);
			lin_other_total = nformat(eval(lin_other_total) + eval(nfilter(frm.RequisitionLine_otherCharges)),2);
			lin_othertax_total = nformat(eval(lin_othertax_total) + eval(nfilter(frm.RequisitionLine_otherTaxAmount)),2);
			lin_total = nformat(eval(lin_total) + eval(nfilter(frm.RequisitionLine_line_total)),2);
		}
		if (eval(hdr_disc_total) != eval(lin_disc_total)) {
			alertMessage += "Header Discount amount does not match total of line Discount amounts.\n";
		}
		if (eval(hdr_tax_total) != eval(lin_tax_total)) {
			alertMessage += "Header Tax amount does not match total of line Tax amounts.\n";
		}
		if (eval(hdr_ship_total) != eval(lin_ship_total)) {
			alertMessage += "Header Shipping amount does not match total of line Shipping amounts.\n";
		}
		if (eval(hdr_shiptax_total) != eval(lin_shiptax_total)) {
			alertMessage += "Header Shipping Tax amount does not match total of line Shipping Tax amounts.\n";
		}
		if (eval(hdr_other_total) != eval(lin_other_total)) {
			alertMessage += "Header Other amount does not match total of line Other amounts.\n";
		}
		if (eval(hdr_othertax_total) != eval(hdr_othertax_total)) {
			alertMessage += "Header Other Tax amount does not match total of line Other amounts.\n";
		}
		if (eval(hdr_total) != eval(lin_total)) {
			alertMessage += "Header Total amount does not match total of line Total amounts.\n";
		}
		if ( alertMessage.length > 0 ) {
			alert ( 'Please fix the following problems:\n'+alertMessage );
			submitcount = 0;
			return false;
		}

		return true;
	}
	else
	{
		return true;
	}
}

function setRecalc()
{
	if ( (eval(frm.RequisitionHeader_shippingCharges.value) > 0) || (eval(frm.RequisitionHeader_otherCharges.value) > 0) || ((eval(frm.RequisitionHeader_taxPercent.value) == 0) && (eval(frm.RequisitionHeader_taxAmount.value) > 0)) || ((eval(frm.RequisitionHeader_discountPercent.value) == 0) && (eval(frm.RequisitionHeader_discountAmount.value) > 0)) )
	{
		frm.RequisitionHeader_reqRecalc.value = "R";
	}
	else
	{
		frm.RequisitionHeader_reqRecalc.value = "N";
	}
}

function copyOtherDesc()
{
	if (rowcount < 1)
	{
		return;
	}
	else if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			frm.RequisitionLine_otherDescription[i].value = frm.RequisitionHeader_otherChargDesc.value;
		}
	}
	else if (rowcount == 1)
	{
		frm.RequisitionLine_otherDescription.value = frm.RequisitionHeader_otherChargDesc.value;
	}
}

function setOverrideFlags() {
	if (rowcount < 1) {
		tax_ovr = "N";
	}
	else if (rowcount > 1) {
		for (var i = 0; i < rowcount; i++) {
			if (frm.RequisitionLine_taxOvr[i].value == "Y") {
				tax_ovr = "Y";
				return;
			}
		}
		tax_ovr = "N";
	}
	else {
		if (frm.RequisitionLine_taxOvr.value == "Y") {
			tax_ovr = "Y";
			return;
		}
		tax_ovr = "N";
	}
}