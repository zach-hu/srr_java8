frm = document.purchaseform;
/*
var fldObject = null;
var fldFromObject = null;
var fldToObject = null;
var fldSession = 'GCS_SESSION';
var taxArray = new Array();
var populated = false;
*/

function distributeShipping ()
{
	/*distribute shipping charges*/
	var hdr_ship_amount = nfilter(frm.PoHeader_shippingCharges);

	hdr_ship_amount = nformat(hdr_ship_amount, 2);
	frm.PoHeader_shippingCharges.value = hdr_ship_amount;

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
		var po_subtotal = frm.PoHeader_subtotal.value - frm.PoHeader_discountAmount.value;
		var lin_subtotal = 0;

		for (var i = 0; i < rowcount; i++)
		{
			lin_subtotal = frm.computed_subtotal[i].value - frm.PoLine_discountAmount[i].value;
			if (po_subtotal != 0)
			{
				lin_ship_amount = eval(nformat((lin_subtotal/po_subtotal) * hdr_ship_amount, 2));
			}
			else
			{
				lin_ship_amount = eval(nformat(hdr_ship_amount / rowcount, 2));
			}
			frm.PoLine_shippingCharges[i].value = nformat(lin_ship_amount, 2);
			lin_ship_total = lin_ship_total + lin_ship_amount;
			if ((ship_ovr == "Y") && (i == 0))
			{
				alert("Header Shipping will now be distributed across all line items.");
			}
			frm.PoLine_shipOvr[i].value = "N";
		}

		/*make sure sum of line item values equals what was entered at the header*/
		diff = eval(hdr_ship_amount) - lin_ship_total;
		if (diff != 0)
		{
			frm.PoLine_shippingCharges[i-1].value = nformat(lin_ship_amount + diff, 2);
		}
	}
	else if (rowcount == 1)
	{
		var lin_ship_total = 0;
		var diff = 0;
		var lin_ship_amount = 0;
		var po_subtotal = frm.PoHeader_subtotal.value - frm.PoHeader_discountAmount.value;
		var lin_subtotal = 0;


		lin_subtotal = frm.computed_subtotal.value - frm.PoLine_discountAmount.value;
		if (po_subtotal != 0)
		{
			lin_ship_amount = eval(nformat((lin_subtotal/po_subtotal) * hdr_ship_amount, 2));
		}
		else
		{
			lin_ship_amount = eval(nformat(hdr_ship_amount / rowcount, 2));
		}
		frm.PoLine_shippingCharges.value = nformat(lin_ship_amount, 2);
		lin_ship_total = lin_ship_total + lin_ship_amount;
		if ((ship_ovr == "Y") && (i == 0))
		{
			alert("Header Shipping will now be distributed across all line items.");
		}
		frm.PoLine_shipOvr.value = "N";

		/*make sure sum of line item values equals what was entered at the header*/
		diff = eval(hdr_ship_amount) - lin_ship_total;
		if (diff != 0)
		{
			frm.PoLine_shippingCharges.value = nformat(lin_ship_amount + diff, 2);
		}
	}

	ship_ovr="N";
	if ( (frm.PoHeader_shippingTaxable.value == "Y") && (eval(frm.PoHeader_taxPercent.value) > 0) && (tax_ovr == "N") )
	{
		calculateShipTax();
	}
	if (rowcount > 0)
	{
		lineTotal();
	}
}

function distributeOther ()
{
	/*distribute other charges*/
	var hdr_other_amount = nfilter(frm.PoHeader_otherCharges);
	hdr_other_amount = nformat(hdr_other_amount, 2);
	frm.PoHeader_otherCharges.value = hdr_other_amount;

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
		var po_subtotal = frm.PoHeader_subtotal.value - frm.PoHeader_discountAmount.value;
		var lin_subtotal = 0;

		for (var i = 0; i < rowcount; i++)
		{
			lin_subtotal = frm.computed_subtotal[i].value - frm.PoLine_discountAmount[i].value;
			if (po_subtotal != 0)
			{
				lin_other_amount = eval(nformat((lin_subtotal/po_subtotal) * hdr_other_amount, 2));
			}
			else
			{
				lin_other_amount = eval(nformat(hdr_other_amount/rowcount, 2));
			}
			frm.PoLine_otherCharges[i].value = nformat(lin_other_amount, 2);
			lin_other_total = lin_other_total + lin_other_amount;
			if ((other_ovr == "Y") && (i == 0))
			{
				alert("Header Other will now be distributed across all line items.");
			}
			frm.PoLine_otherOvr[i].value = "N";
		}

		/*make sure sum of line item values equals what was entered at the header*/
		diff = eval(hdr_other_amount) - lin_other_total;
		if (diff != 0)
		{
			frm.PoLine_otherCharges[i-1].value = nformat(lin_other_amount + diff, 2);
		}
	}
	else if (rowcount == 1)
	{
		var lin_other_total = 0;
		var diff = 0;
		var lin_other_amount = 0;
		var po_subtotal = frm.PoHeader_subtotal.value - frm.PoHeader_discountAmount.value;
		var lin_subtotal = 0;

		lin_subtotal = frm.computed_subtotal.value - frm.PoLine_discountAmount.value;
		if (po_subtotal != 0)
		{
			lin_other_amount = eval(nformat((lin_subtotal/po_subtotal) * hdr_other_amount, 2));
		}
		else
		{
			lin_other_amount = eval(nformat(hdr_other_amount/rowcount, 2));
		}
			frm.PoLine_otherCharges.value = nformat(lin_other_amount, 2);
			lin_other_total = lin_other_total + lin_other_amount;
			if ((other_ovr == "Y") && (i == 0))
			{
				alert("Header Other will now be distributed across all line items.");
			}
			frm.PoLine_otherOvr.value = "N";

		/*make sure sum of line item values equals what was entered at the header*/
		diff = eval(hdr_other_amount) - lin_other_total;
		if (diff != 0)
		{
			frm.PoLine_otherCharges.value = nformat(lin_other_amount + diff, 2);
		}
	}

	other_ovr="N";
	if ( (frm.PoHeader_otherTaxable.value == "Y") && (eval(frm.PoHeader_taxPercent.value) > 0) && (tax_ovr == "N") )
	{
		calculateOtherTax();
	}

	if (rowcount > 0)
	{
		lineTotal();
	}
}


function headerTotal ()
{
	/*recalculate header total if no line items*/
	var po_total = 0;
	po_total = - eval(frm.PoHeader_discountAmount.value) + eval(frm.PoHeader_taxAmount.value) + eval(frm.PoHeader_shippingCharges.value) + eval(frm.PoHeader_shippingTax.value) + eval(frm.PoHeader_otherCharges.value) + eval(frm.PoHeader_otherTax.value);
	frm.PoHeader_total.value = nformat(po_total, 2);
}

function total ()
{
	/*recalculate po total based on each line total*/
	var po_total = 0;
	lin_total = 0;

	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			lin_total = eval(nformat(frm.PoLine_lineTotal[i].value, 2));
			po_total = po_total + lin_total;
		}
	}
	else
	{
		lin_total = eval(nformat(frm.PoLine_lineTotal.value, 2));
		po_total = po_total + lin_total;
	}
	frm.PoHeader_total.value = nformat(po_total, 2);
}

function lineTotal ()
{
	/*recalculate total for each line*/
	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			frm.PoLine_lineTotal[i].value = eval(nformat(frm.computed_subtotal[i].value, 2)) - eval(nformat(frm.PoLine_discountAmount[i].value, 2)) + eval(nformat(frm.PoLine_taxAmount[i].value, 2)) + eval(nformat(frm.PoLine_shippingCharges[i].value, 2)) + eval(nformat(frm.PoLine_shippingTax[i].value, 2)) + eval(nformat(frm.PoLine_otherCharges[i].value, 2)) + eval(nformat(frm.PoLine_otherTax[i].value, 2));
			frm.PoLine_lineTotal[i].value = nformat(frm.PoLine_lineTotal[i].value, 2);
		}
	}
	else
	{
		frm.PoLine_lineTotal.value = eval(nformat(frm.computed_subtotal.value, 2)) - eval(nformat(frm.PoLine_discountAmount.value, 2)) + eval(nformat(frm.PoLine_taxAmount.value, 2)) + eval(nformat(frm.PoLine_shippingCharges.value, 2)) + eval(nformat(frm.PoLine_shippingTax.value, 2)) + eval(nformat(frm.PoLine_otherCharges.value, 2)) + eval(nformat(frm.PoLine_otherTax.value, 2));
		frm.PoLine_lineTotal.value = nformat(frm.PoLine_lineTotal.value, 2);
	}
	total();
}

function totalColumn(headerField, lineField)
{
	/*recalculate total of a particular column*/
	column_total = 0;

	if (rowcount >1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			column_total = column_total + eval(nformat(lineField[i].value, 2));
		}
	}
	else
	{
		column_total = column_total + eval(nformat(lineField.value, 2));
	}

	headerField.value = nformat(column_total, 2);
}

function changeHeaderAmount (formField)
{
	/*calculate new tax/discount amount when tax/discount percent is changed*/
	if (rowcount >= 1)
	{
		/*if more than one line item*/
		changeHeaderAmountRow (formField);
		return;
	}
	else if (rowcount < 1)
	{
		if (formField == frm.PoHeader_discountPercent)
		{
			frm.PoHeader_discountAmount.value = "0.00";
		}
		if (formField == frm.PoHeader_taxPercent)
		{
			frm.PoHeader_taxAmount.value = "0.00"
		}
		calculateShipTax();
		calculateOtherTax();
		headerTotal();
		return;
	}
}

function changeHeaderAmountRow (formField)
{
	/*calculate new tax/discount amount when tax/discount percent is changed*/
	var tax_perc = eval(nfilter(frm.PoHeader_taxPercent));
	var disc_perc = eval(nfilter(frm.PoHeader_discountPercent));
	var lin_tax_amount = 0;
	var lin_disc_amount = 0;
	var hdr_tax_amount = 0;
	var hdr_disc_amount = 0;
	var hdr_subtotal = eval(nfilter(frm.PoHeader_subtotal));
	var hdr_taxable_subtotal = 0;
	var lin_perc = 0;
	var lin_disc_total = 0;

	if (formField == frm.PoHeader_discountPercent)
	{
		frm.PoHeader_discountPercent.value = nformat(disc_perc, 2);

		hdr_disc_amount = eval(nformat(hdr_subtotal * disc_perc * .01, 2));
		frm.PoHeader_discountAmount.value = nformat(hdr_disc_amount, 2);

		if (rowcount > 1)
		{
			for (var i = 0; i < rowcount; i++)
			{
				if (disc_perc != 0) {
					lin_disc_amount  = eval(nformat(frm.computed_subtotal[i].value * disc_perc * .01, 2));
				} else {
					if (hdr_subtotal == 0) {
						lin_perc = eval(0);
					} else {
						lin_perc = eval(frm.computed_subtotal[i].value / hdr_subtotal);
					}
					lin_disc_amount = eval(nformat(lin_perc * hdr_disc_amount, 2));
					lin_disc_total = lin_disc_total + lin_disc_amount;
				}
				frm.PoLine_discountPercent[i].value = nformat(disc_perc, 2);
				frm.PoLine_discountAmount[i].value = nformat(lin_disc_amount, 2);
				lin_disc_total = eval(nformat(lin_disc_total + lin_disc_amount,2));

				if ((disc_ovr == "Y") && (i == 0))
				{
					alert("Discount for all line items will now be calculated based on Header Discount Percent.");
				}
				frm.PoLine_discOvr[i].value = "N";
			}

			if (disc_perc == 0) {
				if (lin_disc_total != hdr_disc_amount) {
					frm.PoLine_discountAmount[0].value = nformat(frm.PoLine_discountAmount[0].value + ( hdr_disc_amount - lin_disc_total),2) ;
				}
			} else {
				hdr_disc_amount = lin_disc_total ;
				frm.PoHeader_discountAmount.value = nformat(hdr_disc_amount, 2);
			}
		}
		else
		{
			if (hdr_subtotal == 0) {
				lin_perc = eval(0);
			} else {
				lin_perc = eval(frm.computed_subtotal.value / hdr_subtotal);
			}
			lin_disc_amount = eval(nformat(lin_perc * hdr_disc_amount, 2));
			frm.PoLine_discountPercent.value = nformat(disc_perc, 2);
			frm.PoLine_discountAmount.value = nformat(lin_disc_amount, 2);

			if ((disc_ovr == "Y") && (i == 0))
			{
				alert("Discount for all line items will now be calculated based on Header Discount Percent.");
			}
			frm.PoLine_discOvr.value = "N";
		}
	}

	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			if (frm.PoLine_taxable[i].value == 'Y')
			{
				hdr_taxable_subtotal = hdr_taxable_subtotal + eval(frm.computed_subtotal[i].value - frm.PoLine_discountAmount[i].value);
			}
		}
	}
	else
	{
		if (frm.PoLine_taxable.value == 'Y')
		{
			hdr_taxable_subtotal = hdr_taxable_subtotal + eval(frm.computed_subtotal.value - frm.PoLine_discountAmount.value);
		}
	}

	hdr_tax_amount = eval(nformat(hdr_taxable_subtotal * tax_perc * .01, 5));
	frm.PoHeader_taxAmount.value = nformat(hdr_tax_amount, 2);
	frm.PoHeader_taxPercent.value = nformat(tax_perc, 5);

	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			if (formField == frm.PoHeader_taxPercent)
			{
				frm.PoLine_taxPercent[i].value = nformat(tax_perc, 5);
				if ((tax_ovr == "Y") && (i == 0))
				{
					alert("Tax for all line items will now be calculated based on Header Tax Percent.");
				}
				frm.PoLine_taxOvr[i].value = "N";
			}
			if (frm.PoLine_taxable[i].value == 'Y')
			{
				if (hdr_taxable_subtotal == 0) {
					lin_perc = eval(0);
				} else {
					lin_perc = eval((frm.computed_subtotal[i].value - frm.PoLine_discountAmount[i].value) / hdr_taxable_subtotal);
				}
				lin_tax_amount = eval(lin_perc * hdr_tax_amount);
				frm.PoLine_taxAmount[i].value = nformat(lin_tax_amount, 2);
			}
		}
	}
	else
	{
		if (formField == frm.PoHeader_taxPercent)
		{
			frm.PoLine_taxPercent.value = nformat(tax_perc, 5);
			if ((tax_ovr == "Y") && (i == 0))
			{
				alert("Tax for all line items will now be calculated based on Header Tax Percent.");
			}

			frm.PoLine_taxOvr.value = "N";
		}
		if (frm.PoLine_taxable.value == 'Y')
		{
			if (hdr_taxable_subtotal == 0) {
				lin_perc = eval(0);
			} else {
				lin_perc = eval((frm.computed_subtotal.value - frm.PoLine_discountAmount.value) / hdr_taxable_subtotal);
			}
			lin_tax_amount = eval(lin_perc * hdr_tax_amount);
			frm.PoLine_taxAmount.value = nformat(lin_tax_amount, 2);
		}
	}

	if (formField == frm.PoHeader_discountPercent)
	{
		disc_ovr = "N";
	}

	if (formField == frm.PoHeader_taxPercent)
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
	var hdr_tax_amount = nfilter(frm.PoHeader_taxAmount);
	hdr_tax_amount = nformat(hdr_tax_amount, 2);
	frm.PoHeader_taxAmount.value = hdr_tax_amount;
	frm.PoHeader_taxPercent.value = '0.0';

	if (rowcount < 1)
	{
		/*if no line items*/
		headerTotal();
		return;
	}

	var taxable_subtotal = 0;
	var total_tax = 0;
	var line_tax = 0;

	/*get subtotal of lines that are taxable*/
	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			frm.PoLine_taxPercent[i].value = '0.0';
			if (frm.PoLine_taxable[i].value == 'Y')
			{
				taxable_subtotal = taxable_subtotal + eval(nformat(frm.computed_subtotal[i].value - frm.PoLine_discountAmount[i].value, 2));
			}
		}
	}
	else
	{
		frm.PoLine_taxPercent.value = '0.0';
		if (frm.PoLine_taxable.value == 'Y')
		{
			taxable_subtotal = taxable_subtotal + eval(nformat(frm.computed_subtotal.value - frm.PoLine_discountAmount.value, 2));
		}
	}

	/*disbribute tax to lines that are taxable*/
	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			if (frm.PoLine_taxable[i].value == 'Y')
			{
				line_taxable = nformat(frm.computed_subtotal[i].value - frm.PoLine_discountAmount[i].value, 2);
				if (taxable_subtotal == 0) {
					line_tax = eval(nformat(0,2));
				} else {
					line_tax = eval(nformat((line_taxable/taxable_subtotal)*hdr_tax_amount, 2));
				}
				frm.PoLine_taxAmount[i].value = nformat(line_tax, 2);
				total_tax = total_tax + line_tax ;
			}
			if ((tax_ovr == "Y") && (i == 0))
			{
				alert("Header Tax will now be distributed across all line items.");
			}
			frm.PoLine_taxOvr[i].value = "N";
		}
		tax_ovr = "N";

		/*make sure sum of line item values equals what was entered at the header*/
		diff = eval(hdr_tax_amount) - total_tax;
		if (diff != 0)
		{
			frm.PoLine_taxAmount[i-1].value = nformat(line_tax + diff, 2);
		}
	}
	else
	{
		if (frm.PoLine_taxable.value == 'Y')
		{
			line_taxable = nformat(frm.computed_subtotal.value - frm.PoLine_discountAmount.value, 2);
			if (taxable_subtotal == 0) {
				line_tax = eval(nformat(0, 2));
			} else {
				line_tax = eval(nformat((line_taxable/taxable_subtotal)*hdr_tax_amount, 2));
			}
			frm.PoLine_taxAmount.value = nformat(line_tax, 2);
			total_tax = total_tax + line_tax ;
		}
		if ((tax_ovr == "Y") && (i == 0))
		{
			alert("Header Tax will now be distributed across all line items.");
		}
		frm.PoLine_taxOvr.value = "N";

		tax_ovr = "N";

		/*make sure sum of line item values equals what was entered at the header*/
		diff = eval(hdr_tax_amount) - total_tax;
		if (diff != 0)
		{
			frm.PoLine_taxAmount.value = nformat(line_tax + diff, 2);
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

/*distribute discount when header discount amount entered directly*/
function distributeDiscount ()
{
	if (rowcount >= 1)
	{
		/*if more than one line item*/
		distributeDiscountRow ();
		return;
	}

	var hdr_disc_amount = nfilter(frm.PoHeader_discountAmount);
	hdr_disc_amount = nformat(hdr_disc_amount, 2);
	frm.PoHeader_discountAmount.value = hdr_disc_amount;
	frm.PoHeader_discountPercent.value = "0.0";

	if (rowcount < 1)
	{
		/*if no lines, total header and return*/
		headerTotal();
		return;
	}
}

/*distribute discount when header discount amount entered directly*/
function distributeDiscountRow ()
{
	var line_total = 0;
	var diff = 0;
	var line_amount = 0;
	var po_subtotal = frm.PoHeader_subtotal.value;
	var lin_subtotal = 0;
	var hdr_disc_amount = nfilter(frm.PoHeader_discountAmount);
	var tax_perc = 0;

	hdr_disc_amount = nformat(hdr_disc_amount, 2);
	frm.PoHeader_discountAmount.value = hdr_disc_amount;

	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			lin_subtotal = frm.computed_subtotal[i].value;
			if (po_subtotal != 0)
			{
				line_amount = eval(nformat((lin_subtotal/po_subtotal) * hdr_disc_amount, 2));
			}
			else
			{
				line_amount = eval(nformat(hdr_disc_amount / rowcount, 2));
			}
			frm.PoLine_discountAmount[i].value = nformat(line_amount, 2);
			line_total = line_total + line_amount;
			frm.PoLine_discountPercent[i].value = '0.0';
			if ((disc_ovr == "Y") && (i == 0))
			{
				alert("Header Discount will now be distributed across all line items.");
			}
			frm.PoLine_discOvr[i].value = "N";
			tax_perc = frm.PoLine_taxPercent[i].value;
			if ((tax_perc != '0.0') && (frm.PoLine_taxable[i].value == 'Y'))
			{
				frm.PoLine_taxAmount[i].value = nformat((frm.computed_subtotal[i].value - frm.PoLine_discountAmount[i].value) * tax_perc * .01, 2);
			}
		}

		disc_ovr = "N";
		frm.PoHeader_discountPercent.value = '0.0';

		/*make sure sum of line item values equals what was entered at the header*/
		diff = eval(hdr_disc_amount) - line_total;
		if (diff != 0)
		{
			frm.PoLine_discountAmount[i-1].value = nformat(line_amount + diff, 2);
		}
	}
	else
	{
		lin_subtotal = frm.computed_subtotal.value;
		if (po_subtotal != 0)
		{
			line_amount = eval(nformat((lin_subtotal/po_subtotal) * hdr_disc_amount, 2));
		}
		else
		{
			line_amount = eval(nformat(hdr_disc_amount / rowcount, 2));
		}
		frm.PoLine_discountAmount.value = nformat(line_amount, 2);
		line_total = line_total + line_amount;
		frm.PoLine_discountPercent.value = '0.0';
		if ((disc_ovr == "Y") && (i == 0))
		{
			alert("Header Discount will now be distributed across all line items.");
		}
		frm.PoLine_discOvr.value = "N";
		tax_perc = frm.PoLine_taxPercent.value;
		if ((tax_perc != '0.0') && (frm.PoLine_taxable.value == 'Y'))
		{
			frm.PoLine_taxAmount.value = nformat((frm.computed_subtotal.value - frm.PoLine_discountAmount.value) * tax_perc * .01, 2);
		}

		disc_ovr = "N";
		frm.PoHeader_discountPercent.value = '0.0';

		/*make sure sum of line item values equals what was entered at the header*/
		diff = eval(hdr_disc_amount) - line_total;
		if (diff != 0)
		{
			frm.PoLine_discountAmount.value = nformat(line_amount + diff, 2);
		}
	}

	lineTotal();
	totalColumn(frm.PoHeader_taxAmount, frm.PoLine_taxAmount);
}

/*recalculates line amounts and header total when any line amount is changed*/
function changeLineAmount(formField,x)
{
	if (rowcount >= 1)
	{
		/*if more than one line item*/
		changeLineAmountRow (formField, x);
	}
	return;
}

/*recalculates line amounts and header total when any line amount is changed*/
function changeLineAmountRow (formField, x)
{
	var line_tax = 0;

	if (rowcount > 1)
	{
		formField[x].value = nformat(nfilter(formField[x]), 2);
	}
	else
	{
		formField.value = nformat(nfilter(formField), 2);
	}

	if (formField == frm.PoLine_discountPercent)
	{
		if (rowcount > 1)
		{
			frm.PoLine_discountAmount[x].value = nformat(frm.computed_subtotal[x].value * frm.PoLine_discountPercent[x].value * .01, 2);
		}
		else
		{
			frm.PoLine_discountAmount.value = nformat(frm.computed_subtotal.value * frm.PoLine_discountPercent.value * .01, 2);
		}

		totalColumn(frm.PoHeader_discountAmount, frm.PoLine_discountAmount);
		if (disc_ovr == "N")
		{
			alert("Header Discount amount will now be based on the Discount amount entered/calculated at each line item.");
		}
		setFlag(frm.PoLine_discOvr, "Y");
	}

	if (formField == frm.PoLine_discountAmount)
	{
		if (rowcount > 1)
		{
			frm.PoLine_discountPercent[x].value = '0.0';
		}
		else
		{
			frm.PoLine_discountPercent.value = '0.00';
		}

		totalColumn(frm.PoHeader_discountAmount, frm.PoLine_discountAmount);
		if (disc_ovr == "N")
		{
			alert("Header Discount amount will now be based on the Discount amount entered/calculated at each line item.");
		}
		setFlag(frm.PoLine_disc_ovr, "Y");
	}

	if (rowcount > 1)
	{
		if ((formField == frm.PoLine_taxPercent) || (((formField == frm.PoLine_discountPercent) || (formField == frm.PoLine_discountAmount)) && (eval(frm.PoLine_taxPercent[x].value) != 0)))
		{
			line_tax = (eval(nformat(frm.computed_subtotal[x].value - frm.PoLine_discountAmount[x].value, 2)))* frm.PoLine_taxPercent[x].value * .01;
			frm.PoLine_taxAmount[x].value = nformat(line_tax, 2);
			totalColumn(frm.PoHeader_taxAmount, frm.PoLine_taxAmount);
			if (formField == frm.PoLine_taxPercent)
			{
				if (tax_ovr == "N")
				{
					alert("Header Tax amount will now be based on the Tax amount entered/calculated at each line item.");
				}
				setFlag(frm.PoLine_taxOvr, "Y");
				calculateShipTax();
				calculateOtherTax();
			}
		}
	}
	else
	{
		if ((formField == frm.PoLine_taxPercent) || (((formField == frm.PoLine_discountPercent) || (formField == frm.PoLine_discountAmount)) && (eval(frm.PoLine_taxPercent.value) != 0)))
		{
			line_tax = (eval(nformat(frm.computed_subtotal.value - frm.PoLine_discountAmount.value, 2)))* frm.PoLine_taxPercent.value * .01;
			frm.PoLine_taxAmount.value = nformat(line_tax, 2);
			totalColumn(frm.PoHeader_taxAmount, frm.PoLine_taxAmount);
			if (formField == frm.PoLine_taxPercent)
			{
				if (tax_ovr == "N")
				{
					alert("Header Tax amount will now be based on the Tax amount entered/calculated at each line item.");
				}
				setFlag(frm.PoLine_taxOvr, "Y");
				calculateShipTax();
				calculateOtherTax();
			}
		}
	}

	if (formField == frm.PoLine_taxAmount)
	{
		if (rowcount > 1)
		{
			frm.PoLine_taxPercent[x].value = '0.0';
		}
		else
		{
			frm.PoLine_taxPercent.value = '0.00';
		}
		totalColumn(frm.PoHeader_taxAmount, frm.PoLine_taxAmount);
		if (tax_ovr == "N")
		{
			alert("Header Tax amount will now be based on the Tax amount entered/calculated at each line item.");
		}
		setFlag(frm.PoLine_taxOvr, "Y");
	}

	if (formField == frm.PoLine_shippingCharges)
	{
		totalColumn(frm.PoHeader_shippingCharges, frm.PoLine_shippingCharges);
		if (ship_ovr == "N")
		{
			alert("Header Shipping amount will now be based on the Shipping amount entered at each line item.");
		}
		setFlag(frm.PoLine_shipOvr, "Y");

		if (rowcount > 1)
		{
			//if ( (frm.PoHeader_shippingTaxable.value == "Y") && (eval(frm.PoLine_taxPercent[x].value) > 0) && (tax_ovr == "N") )
			if ( (frm.PoHeader_shippingTaxable.value == "Y") && (eval(frm.PoLine_taxPercent[x].value) > 0) )
			{
				frm.PoLine_shippingTax[x].value = nformat(frm.PoLine_shippingCharges[x].value * frm.PoLine_taxPercent[x].value * .01, 2);
				totalColumn(frm.PoHeader_shippingTax, frm.PoLine_shippingTax);
			}
		}
		else
		{
			//if ( (frm.PoHeader_shippingTaxable.value == "Y") && (eval(frm.PoLine_taxPercent.value) > 0) && (tax_ovr == "N") )
			if ( (frm.PoHeader_shippingTaxable.value == "Y") && (eval(frm.PoLine_taxPercent.value) > 0) )
			{
				frm.PoLine_shippingTax.value = nformat(frm.PoLine_shippingCharges.value * frm.PoLine_taxPercent.value * .01, 2);
				totalColumn(frm.PoHeader_shippingTax, frm.PoLine_shippingTax);
			}
		}
	}

	if (formField == frm.PoLine_shippingTax)
	{
		totalColumn(frm.PoHeader_shippingTaxAmt, frm.PoLine_shippingTax);
	}

	if (formField == frm.PoLine_otherCharges)
	{
		totalColumn(frm.PoHeader_otherCharges, frm.PoLine_otherCharges);
		if (other_ovr == "N")
		{
			alert("Header Other amount will now be based on the Other amount entered at each line item.");
		}
		setFlag(frm.PoLine_otherOvr, "Y");
		if (rowcount > 1)
		{
			//if ( (frm.PoHeader_otherTaxable.value == "Y") && (eval(frm.PoLine_taxPercent[x].value) > 0) && (tax_ovr == "N") )
			if ( (frm.PoHeader_otherTaxable.value == "Y") && (eval(frm.PoLine_taxPercent[x].value) > 0) )
			{
				frm.PoLine_otherTax[x].value = nformat(frm.PoLine_otherCharges[x].value * frm.PoLine_taxPercent[x].value * .01, 2);
				totalColumn(frm.PoHeader_otherTax, frm.PoLine_otherTax);
			}
		}
		else
		{
			//if ( (frm.PoHeader_otherTaxable.value == "Y") && (eval(frm.PoLine_taxPercent.value) > 0) && (tax_ovr == "N") )
			if ( (frm.PoHeader_otherTaxable.value == "Y") && (eval(frm.PoLine_taxPercent.value) > 0) )
			{
				frm.PoLine_otherTax.value = nformat(frm.PoLine_otherCharges.value * frm.PoLine_taxPercent.value * .01, 2);
				totalColumn(frm.PoHeader_otherTax, frm.PoLine_otherTax);
			}
		}
	}

	if (formField == frm.PoLine_otherTax)
	{
		totalColumn(frm.PoHeader_otherTax, frm.PoLine_otherTax);
	}

	if ((formField != frm.PoLine_taxPercent) && (formField != frm.PoLine_discountPercent))
	{
		if (rowcount > 1)
		{
			formField[x].value = nformat(formField[x].value, 2);
		}
		else
		{
			formField.value = nformat(formField.value, 2);
		}
	}
	lineTotal();
}

function calculateShipTax()
{
	/*calculates shipping tax based on header tax percentage*/
	if ( (frm.PoHeader_shippingTaxable.value == "Y") )
	{
		if (tax_ovr == "N")
		{
			if (rowcount < 1)
			{
				frm.PoHeader_shippingTax.value = nformat(frm.PoHeader_shippingCharges.value * frm.PoHeader_taxPercent.value * .01, 2);
				headerTotal();
				return;
			}
			frm.PoHeader_shippingTax.value = nformat(frm.PoHeader_shippingCharges.value * frm.PoHeader_taxPercent.value * .01, 2);
			distributeShipTax();
		}
		else
		{
			if (rowcount < 1)
			{
				frm.PoHeader_shippingTax.value = nformat(frm.PoHeader_shippingCharges.value * frm.PoHeader_taxPercent.value * .01, 2);
				headerTotal();
				return;
			}

			var hdr_ship_tax = 0.00;
			var ln_ship_tax = 0.00;
			if (rowcount > 1)
			{
				for (var i = 0; i < rowcount; i++)
				{
					ln_ship_tax = eval(nformat(frm.PoLine_shippingCharges[i].value * frm.PoLine_taxPercent[i].value * .01, 2));
					frm.PoLine_shippingTax[i].value = ln_ship_tax;
					hdr_ship_tax = eval(hdr_ship_tax + ln_ship_tax);
				}
			}
			else
			{
				frm.PoLine_shippingTax.value = nformat(frm.PoLine_shippingCharges.value * frm.PoLine_taxPercent.value * .01, 2);
				hdr_ship_tax = frm.PoLine_shippingTax.value;
			}
			frm.PoHeader_shippingTax.value = nformat(hdr_ship_tax,2);
			lineTotal();
		}
	}
	else
	{
		if (rowcount < 1)
		{
			frm.PoHeader_shippingTax.value = "0.00";
			headerTotal();
			return;
		}

		if (rowcount > 1)
		{
			for (var i = 0; i < rowcount; i++)
			{
				frm.PoLine_shippingTax[i].value = "0.00";
			}
		}
		else
		{
			frm.PoLine_shippingTax.value = "0.00";
		}
		totalColumn(frm.PoHeader_shippingTax, frm.PoLine_shippingTax)
	}
}

function calculateOtherTax()
{
	/*calculates other tax based on header tax percentage*/
	if ( frm.PoHeader_otherTaxable.value == "Y" )
	{
		if (tax_ovr == "N")
		{
			if (rowcount < 1)
			{
				frm.PoHeader_otherTax.value = nformat(frm.PoHeader_otherCharges.value * frm.PoHeader_taxPercent.value * .01, 2);
				headerTotal();
				return;
			}
			frm.PoHeader_otherTax.value = nformat(frm.PoHeader_otherCharges.value * frm.PoHeader_taxPercent.value * .01, 2);
			distributeOtherTax();
		}
		else
		{
			if (rowcount < 1)
			{
				frm.PoHeader_otherTax.value = nformat(frm.PoHeader_otherCharges.value * frm.PoHeader_taxPercent.value * .01, 2);
				headerTotal();
				return;
			}

			var hdr_other_tax = 0.00;
			var ln_other_tax = 0.00;
			if (rowcount > 1)
			{
				for (var i = 0; i < rowcount; i++)
				{
					ln_other_tax = eval(nformat(frm.PoLine_otherCharges[i].value * frm.PoLine_taxPercent[i].value * .01, 2));
					frm.PoLine_otherTax[i].value = ln_other_tax;
					hdr_other_tax = eval(nformat(hdr_other_tax + ln_other_tax, 2));
				}
			}
			else
			{
				frm.PoLine_otherTax.value = nformat(frm.PoLine_otherCharges.value * frm.PoLine_taxPercent.value * .01, 2);
				hdr_ship_tax = frm.PoLine_otherTax.value;
			}
			frm.PoHeader_otherTax.value = nformat(hdr_other_tax,2);
			lineTotal();
		}
	}
	else
	{
		frm.PoHeader_otherTax.value = "0.00";
		if (rowcount < 1)
		{
			headerTotal();
			return;
		}
		else if (rowcount > 1)
		{
			for (var i = 0; i < rowcount; i++)
			{
				frm.PoLine_otherTax[i].value = "0.00";
			}
		}
		else if (rowcount == 1)
		{
			frm.PoLine_otherTax.value = "0.00";
		}
	}
}

/*distributes shipping tax when entered directly*/
function distributeShipTax ()
{
	var hdr_ship_tax = nfilter(frm.PoHeader_shippingTax);

	if (rowcount < 1)
	{
		frm.PoHeader_shippingTax.value = nformat(hdr_ship_tax, 2);
		headerTotal();
		return;
	}

	var line_total = 0;
	var diff = 0;
	var line_amount = 0;

	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			if (frm.PoHeader_shippingCharges.value == 0) {
				line_amount = eval(nformat(0, 2));
			} else {
				line_amount = eval(nformat((frm.PoLine_shippingCharges[i].value/frm.PoHeader_shippingCharges.value) * hdr_ship_tax, 2));
			}
			frm.PoLine_shippingTax[i].value = nformat(line_amount, 2);
			line_total = line_total + line_amount;
		}
		diff = eval(nformat(hdr_ship_tax, 2)) - line_total;
		if (diff != 0)
		{
			frm.PoLine_shippingTax[i-1].value = nformat(line_amount + diff, 2);
		}
	}
	else
	{
		if (frm.PoHeader_shippingCharges.value == 0) {
			line_amount = eval(nformat(0, 2));
		} else {
			line_amount = eval(nformat((frm.PoLine_shippingCharges.value/frm.PoHeader_shippingCharges.value) * hdr_ship_tax, 2));
		}
		frm.PoLine_shippingTax.value = nformat(line_amount, 2);
		line_total = line_total + line_amount;

		diff = eval(nformat(hdr_ship_tax, 2)) - line_total;
		if (diff != 0)
		{
			frm.PoLine_shippingTax.value = nformat(line_amount + diff, 2);
		}
	}

	frm.PoHeader_shippingTax.value = nformat(hdr_ship_tax, 2);
	lineTotal();
}

/*distributes other tax when entered directly*/
function distributeOtherTax ()
{
	var hdr_other_tax = nfilter(frm.PoHeader_otherTax);
	if (rowcount < 1)
	{
		frm.PoHeader_otherTax.value = nformat(hdr_other_tax, 2);
		headerTotal();
		return;
	}

	var line_total = 0;
	var diff = 0;
	var line_amount = 0;

	if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			if (frm.PoHeader_otherCharges.value == 0) {
				line_amount = eval(nformat(0, 2));
			} else {
				line_amount = eval(nformat((frm.PoLine_otherCharges[i].value/frm.PoHeader_otherCharges.value) * hdr_other_tax, 2));
			}
			frm.PoLine_otherTax[i].value = nformat(line_amount, 2);
			line_total = line_total + line_amount;
		}
		diff = eval(nformat(hdr_other_tax, 2)) - line_total;
		if (diff != 0)
		{
			frm.PoLine_otherTax[i-1].value = nformat(line_amount + diff, 2);
		}
	}
	else
	{
		if (frm.PoHeader_otherCharges.value == 0) {
			line_amount = eval(nformat(0, 2));
		} else {
			line_amount = eval(nformat((frm.PoLine_otherCharges.value/frm.PoHeader_otherCharges.value) * hdr_other_tax, 2));
		}
		frm.PoLine_otherTax.value = nformat(line_amount, 2);
		line_total = line_total + line_amount;

		diff = eval(nformat(hdr_other_tax, 2)) - line_total;
		if (diff != 0)
		{
			frm.PoLine_otherTax.value = nformat(line_amount + diff, 2);
		}
	}

	frm.PoHeader_otherTax.value = nformat(hdr_other_tax, 2);
	lineTotal();
}

/*this function sets/resets the override flag for discount, tax, shipping and other*/
function setFlag (formField, x)
{
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

	if (formField == frm.PoLine_discOvr)	{ disc_ovr = x;	}
	if (formField == frm.PoLine_taxOvr)		{ tax_ovr = x;		}
	if (formField == frm.PoLine_shipOvr)	{ ship_ovr = x;	}
	if (formField == frm.PoLine_otherOvr)	{ other_ovr = x;	}
}

function checkTotals ()
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
		var hdr_disc_total = eval(nfilter(frm.PoHeader_discountAmount));
		var hdr_tax_total = eval(nfilter(frm.PoHeader_taxAmount));
		var hdr_ship_total = eval(nfilter(frm.PoHeader_shippingCharges));
		var hdr_shiptax_total = eval(nfilter(frm.PoHeader_shippingTax));
		var hdr_other_total = eval(nfilter(frm.PoHeader_otherCharges));
		var hdr_othertax_total = eval(nfilter(frm.PoHeader_otherTax));
		var hdr_total = eval(nfilter(frm.PoHeader_total));
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
				lin_disc_total = nformat(eval(lin_disc_total) + eval(nfilter(frm.PoLine_discountAmount[i])),2);
				lin_tax_total = nformat(eval(lin_tax_total) + eval(nfilter(frm.PoLine_taxAmount[i])),2);
				lin_ship_total = nformat(eval(lin_ship_total) + eval(nfilter(frm.PoLine_shippingCharges[i])),2);
				lin_shiptax_total = nformat(eval(lin_shiptax_total) + eval(nfilter(frm.PoLine_shippingTax[i])),2);
				lin_other_total = nformat(eval(lin_other_total) + eval(nfilter(frm.PoLine_otherCharges[i])),2);
				lin_othertax_total = nformat(eval(lin_othertax_total) + eval(nfilter(frm.PoLine_otherTax[i])),2);
				lin_total = nformat(eval(lin_total) + eval(nfilter(frm.PoLine_lineTotal[i])),2);
			}
		}
		else
		{
			lin_disc_total = nformat(eval(lin_disc_total) + eval(nfilter(frm.PoLine_discountAmount)),2);
			lin_tax_total = nformat(eval(lin_tax_total) + eval(nfilter(frm.PoLine_taxAmount)),2);
			lin_ship_total = nformat(eval(lin_ship_total) + eval(nfilter(frm.PoLine_shippingCharges)),2);
			lin_shiptax_total = nformat(eval(lin_shiptax_total) + eval(nfilter(frm.PoLine_shippingTax)),2);
			lin_other_total = nformat(eval(lin_other_total) + eval(nfilter(frm.PoLine_otherCharges)),2);
			lin_othertax_total = nformat(eval(lin_othertax_total) + eval(nfilter(frm.PoLine_otherTax)),2);
			lin_total = nformat(eval(lin_total) + eval(nfilter(frm.PoLine_lineTotal)),2);
		}

		if (eval(hdr_disc_total) != eval(lin_disc_total))
		{
			alertMessage += "Header Discount amount does not match total of line Discount amounts.\n";
		}
		if (eval(hdr_tax_total) != eval(lin_tax_total))
		{
			alertMessage += "Header Tax amount does not match total of line Tax amounts.\n";
		}
		if (eval(hdr_ship_total) != eval(lin_ship_total))
		{
			alertMessage += "Header Shipping amount does not match total of line Shipping amounts.\n";
		}
		if (eval(hdr_shiptax_total) != eval(lin_shiptax_total))
		{
			alertMessage += "Header Shipping Tax amount does not match total of line Shipping Tax amounts.\n";
		}
		if (eval(hdr_other_total) != eval(lin_other_total))
		{
			alertMessage += "Header Other amount does not match total of line Other amounts.\n";
		}
		if (eval(hdr_othertax_total) != eval(hdr_othertax_total))
		{
			alertMessage += "Header Other Tax amount does not match total of line Other amounts.\n";
		}
		if (eval(hdr_total) != eval(lin_total))
		{
			alertMessage += "Header Total amount does not match total of line Total amounts.\n";
		}
		if ( alertMessage.length > 0 )
		{
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
	if ( (eval(frm.PoHeader_shippingCharges.value) > 0) || (eval(frm.PoHeader_otherCharges.value) > 0) || ((eval(frm.PoHeader_taxPercent.value) == 0) && (eval(frm.PoHeader_taxAmount.value) > 0)) || ((eval(frm.PoHeader_discountPercent.value) == 0) && (eval(frm.PoHeader_discountAmount.value) > 0)) )
	{
		frm.PoHeader_poRecalc.value = "R";
	}
	else
	{
		frm.PoHeader_poRecalc.value = "N";
	}
}

function copyOtherDesc ()
{
	if (rowcount < 1)
	{
		return;
	}
	else if (rowcount > 1)
	{
		for (var i = 0; i < rowcount; i++)
		{
			frm.PoLine_otherDescription[i].value = frm.PoHeader_otherDescription.value;
		}
	}
	else
	{
		frm.PoLine_otherDescription.value = frm.PoHeader_otherDescription.value;
	}
}

function setOverrideFlags() {
	if (rowcount < 1) {
		tax_ovr = "N";
	}
	else if (rowcount > 1) {
		for (var i = 0; i < rowcount; i++) {
			if (frm.PoLine_taxOvr[i].value == "Y") {
				tax_ovr = "Y";
				return;
			}
		}
		tax_ovr = "N";
	}
	else {
		if (frm.PoLine_taxOvr.value == "Y") {
			tax_ovr = "Y";
			return;
		}
		tax_ovr = "N";
	}
}