package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import java.math.BigDecimal;
import java.util.Map;

public class RequisitionLineRecalculate extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		this.setStatus(Status.SUCCEEDED) ;
		
		try {
			RequisitionLine rql = (RequisitionLine) incomingRequest.get("requisitionLine");
			
			/* Calculate Line Totals */
			String quantity = (String) incomingRequest.get("RequisitionLine_quantity");
			if (quantity == null) {
				quantity = rql.getQuantity().toString();
			}
			BigDecimal bdQuantity = new BigDecimal(quantity);
			
			String unitPrice = (String) incomingRequest.get("RequisitionLine_unitPrice");
			if (unitPrice == null) {
				unitPrice = rql.getUnitPrice().toString();
			}
			BigDecimal bdUnitPrice = new BigDecimal(unitPrice);
			
			String umFactor = (String) incomingRequest.get("RequisitionLine_umFactor");
			if (umFactor == null) {
				umFactor = rql.getUmFactor().toString();
			}
			BigDecimal bdUmFactor = new BigDecimal(umFactor);
			
			BigDecimal bdLineTotal = bdQuantity.multiply(bdUnitPrice).multiply(bdUmFactor).setScale(2, BigDecimal.ROUND_HALF_UP);
			
			/* Calculate Line Discount */
			String discountPercent = (String) incomingRequest.get("RequisitionLine_discountPercent");
			if (discountPercent == null) {
				discountPercent = rql.getDiscountPercent().toString();
			}
			
			String discountAmount = (String) incomingRequest.get("RequisitionLine_discountAmount");
			if (discountAmount == null) {
				discountAmount = rql.getDiscountAmount().toString();
			}
			
			BigDecimal bdDiscountAmount = new BigDecimal(discountAmount);
			BigDecimal bdDiscountPercent = new BigDecimal(discountPercent);
			
			bdDiscountAmount =
				bdDiscountPercent
					.divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP)
					.multiply(bdLineTotal)
					.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			String taxable = (String) incomingRequest.get("RequisitionLine_taxable");
			if (taxable == null) {
				taxable = rql.getTaxable();
				if (taxable == null) {
					taxable = "Y";
				}
			}
			
			/* Calculate Tax */
			String taxPercent = (String) incomingRequest.get("RequisitionLine_taxPercent");
			if (taxPercent == null) {
				taxPercent = rql.getTaxPercent().toString();
			}
			String taxAmount = (String) incomingRequest.get("RequisitionLine_discountPercent");
			if (taxAmount == null) {
				taxAmount = rql.getTaxAmount().toString();
			}
			BigDecimal bdTaxPercent = new BigDecimal(taxPercent);
			BigDecimal bdTaxAmount = new BigDecimal(taxAmount);
			if (taxable.equals("Y")) {
				bdTaxAmount =
					bdTaxPercent
						.divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP)
						.multiply(bdLineTotal.subtract(bdDiscountAmount))
						.setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			else {
				bdTaxAmount = new BigDecimal(0);
			}
			
			/* Calculate shipping charges */
			String shippingCharges = (String) incomingRequest.get("RequisitionLine_shippingCharges");
			if (shippingCharges == null) 
			{
				shippingCharges = rql.getShippingCharges().toString();
			}
			BigDecimal bdShippingCharges = new BigDecimal(shippingCharges);
			
			String taxShipping = (String) incomingRequest.get("RequisitionLine_taxShipping");
			if (taxShipping == null) {
				taxShipping = rql.getTaxShipping();
				if (taxShipping == null) {
					taxShipping = "N";
				}
			}
			
			BigDecimal bdShippingTaxAmt = new BigDecimal(0);
			if (taxShipping.equals("Y")) {
				bdShippingTaxAmt =
					bdTaxPercent	.divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP)
						.multiply(bdShippingCharges)
						.setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			
			String otherCharges = (String) incomingRequest.get("RequisitionLine_otherCharges");
			if (otherCharges == null) {
				otherCharges = rql.getOtherCharges().toString();
			}
			BigDecimal bdOtherCharges = new BigDecimal(otherCharges);
			BigDecimal bdOtherTaxAmt = new BigDecimal(0);
			
			String taxOther = (String) incomingRequest.get("RequisitionLine_taxOther");
			if (taxOther == null) {
				taxOther = rql.getTaxShipping();
				if (taxOther == null) {
					taxOther = "N";
				}
			}
			
			if (taxOther.equals("Y")) {
				bdOtherTaxAmt =
					bdTaxPercent
						.divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP)
						.multiply(bdOtherCharges)
						.setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			
			BigDecimal bdAllocateTotal =
				bdLineTotal
					.subtract(bdDiscountAmount)
					.add(bdTaxAmount)
					.add(bdShippingCharges)
					.add(bdShippingTaxAmt)
					.add(bdOtherCharges)
					.add(bdOtherTaxAmt);
			
			incomingRequest.put("RequisitionLine_lineTotal",	bdLineTotal.toString());
			incomingRequest.put("RequisitionLine_discountAmount",	bdDiscountAmount.toString());
			incomingRequest.put("RequisitionLine_taxAmount",bdTaxAmount.toString());
			incomingRequest.put("RequisitionLine_shippingCharges",bdShippingCharges.toString());
			incomingRequest.put("RequisitionLine_shippingTaxAmt",bdShippingTaxAmt.toString());
			incomingRequest.put("RequisitionLine_otherCharges",bdOtherCharges.toString());
			incomingRequest.put("RequisitionLine_otherTaxAmt",bdOtherTaxAmt.toString());
			
			incomingRequest.put("allocateTotal", bdAllocateTotal.toString());
			incomingRequest.put("recalculate", "N");
			
			if (bdLineTotal.compareTo(rql.getLineTotal()) != 0
				|| bdTaxAmount.compareTo(rql.getTaxAmount()) != 0
				|| bdDiscountAmount.compareTo(rql.getDiscountAmount()) != 0
				|| bdShippingCharges.compareTo(rql.getShippingCharges()) != 0
				|| bdShippingTaxAmt.compareTo(rql.getShippingTaxAmt()) != 0
				|| bdOtherCharges.compareTo(rql.getOtherCharges()) != 0
				|| bdOtherTaxAmt.compareTo(rql.getOtherTaxAmount()) != 0) {
				incomingRequest.put("recalculate", "Y");
			}
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED) ;
			Log.error(this,e.toString()) ;
		}

		return null;
	}
}