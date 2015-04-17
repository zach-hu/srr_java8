/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.requisitionheader.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal ;
import com.tsa.puridiom.entity.RequisitionHeader ;
import com.tsa.puridiom.entity.RequisitionLine ;
import java.util.List ;

/**
 * @author Administrator 
 */
public class RequisitionHeaderRecalculate extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
			List rqlList = (List) incomingRequest.get("requisitionLineList") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("requisitionline-recalculate.xml");
			
			String discountPercent = (String) incomingRequest.get("RequisitionHeader_discountPercent") ;
			if (discountPercent == null) {
				discountPercent = rqh.getDiscountPercent().toString() ;
			}

			String discountAmount = (String) incomingRequest.get("RequisitionHeader_discountAmount") ;
			if (discountAmount == null) {
				discountAmount = rqh.getDiscountAmount().toString() ;
			}		

			BigDecimal bdDiscountAmount = new BigDecimal(discountAmount) ;
			BigDecimal bdDiscountPercent = new BigDecimal(discountPercent) ;

	 		String taxPercent = (String) incomingRequest.get("RequisitionHeader_taxPercent") ;
			if (taxPercent == null) {
				taxPercent = rqh.getTaxPercent().toString() ;
			}		
			String taxAmount = (String) incomingRequest.get("RequisitionHeader_taxAmount") ;
			if (taxAmount == null) {
				taxAmount = rqh.getTaxAmount().toString() ;
			}
			BigDecimal bdTaxPercent = new BigDecimal(taxPercent);
			BigDecimal bdTaxAmount = new BigDecimal(taxAmount) ;
				
			String shippingCharges = (String) incomingRequest.get("RequisitionHeader_shippingCharges") ;
			if (shippingCharges == null) {
				shippingCharges = rqh.getShippingCharges().toString() ;
			}
			BigDecimal bdShippingCharges = new BigDecimal(shippingCharges) ;
	
			String otherCharges = (String) incomingRequest.get("RequisitionHeader_otherCharges") ;
			if (otherCharges == null) {
				otherCharges = rqh.getOtherCharges().toString() ;
			}
			BigDecimal bdOtherCharges = new BigDecimal(otherCharges) ;

			String taxShipping = (String) incomingRequest.get("RequisitionHeader_taxShipping") ;
			if (taxShipping == null) {
				taxShipping = rqh.getTaxShipping() ;
				if (taxShipping == null) {
					taxShipping = "N" ;
				}
			}

			String taxOther = (String) incomingRequest.get("RequisitionHeader_taxOther") ;
			if (taxOther == null) {
				taxOther = rqh.getTaxShipping() ;
				if (taxOther == null) {
					taxOther = "N" ;
				}
			}

			boolean recalcTaxPercent = (bdTaxPercent.compareTo(rqh.getTaxPercent()) != 0) ;
			boolean recalcTaxAmount = (bdTaxAmount.compareTo(rqh.getTaxAmount()) != 0) ;
			boolean recalcDiscountPercent = (bdDiscountPercent.compareTo(rqh.getDiscountPercent()) != 0) ;
			boolean recalcDiscountAmount =  (bdDiscountAmount.compareTo(rqh.getDiscountAmount()) != 0) ;
			boolean recalcShippingCharges = (bdShippingCharges.compareTo(rqh.getShippingCharges()) != 0);
			boolean recalcOtherCharges =  (bdOtherCharges.compareTo(rqh.getOtherCharges()) != 0) ;

			BigDecimal bdSubtotal = new BigDecimal(0) ;
			BigDecimal bdDiscountTotal = new BigDecimal(0) ;			
			BigDecimal bdOtherTotal = new BigDecimal(0) ;			
			BigDecimal bdShippingTotal = new BigDecimal(0) ;			
			BigDecimal bdTaxTotal = new BigDecimal(0) ;
			BigDecimal bdOtherTaxTotal = new BigDecimal(0) ;			
			BigDecimal bdShippingTaxTotal = new BigDecimal(0) ;
			BigDecimal bdTaxableTotal = new BigDecimal(0) ;
			BigDecimal bdShippingTaxableTotal = new BigDecimal(0) ;
			BigDecimal bdOtherTaxableTotal = new BigDecimal(0) ;
			
			if (rqlList != null) {

				/* Get Line Totals */
			    for (int i = 0; i < rqlList.size(); i++) {
						RequisitionLine rql = (RequisitionLine) rqlList.get(i) ;
						bdSubtotal = bdSubtotal.add(rql.getLineTotal()) ;
			    }
			    
			    BigDecimal percent = null ;
			    
			    for (int i = 0;i < rqlList.size(); i++) {
						RequisitionLine rql = (RequisitionLine) rqlList.get(i) ;

						if (rql.getLineTotal().compareTo(new BigDecimal(0)) != 0) {
							 percent = rql.getLineTotal().divide(bdSubtotal,4,BigDecimal.ROUND_HALF_UP) ;
						} else {
							percent = new BigDecimal(0) ;
						}
						
						if (recalcDiscountPercent) {
							if (rql.getDiscountPercent().compareTo(bdDiscountPercent) != 0) {
								rql.setDiscountPercent(bdDiscountPercent) ;
								rql.setDiscountAmount(bdDiscountPercent.divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP).multiply(rql.getLineTotal()).setScale(2,BigDecimal.ROUND_HALF_UP) ) ;
							}
						} else if (recalcDiscountAmount) {
							if (rql.getLineTotal().compareTo(new BigDecimal(0)) != 0) {
								BigDecimal perc = rql.getLineTotal().divide(bdSubtotal,4,BigDecimal.ROUND_HALF_UP) ;
								rql.setDiscountAmount(perc.multiply(rql.getLineTotal()).setScale(2,BigDecimal.ROUND_HALF_UP)) ;
							}
						}
						
						if (recalcOtherCharges) {
							if (rql.getLineTotal().compareTo(new BigDecimal(0)) != 0) {
								//rql.setOtherCharges(percent.multiply(rql.getLineTotal()).setScale(2,BigDecimal.ROUND_HALF_UP)) ;
								rql.setOtherCharges(percent.multiply(bdOtherCharges).setScale(2,BigDecimal.ROUND_HALF_UP)) ;
							}
						}

						if (recalcShippingCharges) {
							if (rql.getLineTotal().compareTo(new BigDecimal(0)) != 0) {
								//rql.setShippingCharges(percent.multiply(rql.getLineTotal()).setScale(2,BigDecimal.ROUND_HALF_UP)) ;
								rql.setShippingCharges(percent.multiply(bdShippingCharges).setScale(2,BigDecimal.ROUND_HALF_UP)) ;
							}
						}
								
						if (recalcTaxPercent) {
							if (rql.getTaxPercent().compareTo(bdTaxPercent) != 0) {
								rql.setTaxPercent(bdTaxPercent) ;
								rql.setTaxAmount(bdTaxPercent.divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP).multiply(rql.getLineTotal()).setScale(2,BigDecimal.ROUND_HALF_UP) ) ;
								if (taxShipping.equals("Y")) {
									rql.setShippingTaxAmt(bdTaxPercent.divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP).multiply(rql.getShippingCharges()).setScale(2,BigDecimal.ROUND_HALF_UP) ) ;
								}
								if (taxOther.equals("Y")) {
									rql.setOtherTaxAmount(bdTaxPercent.divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP).multiply(rql.getOtherCharges()).setScale(2,BigDecimal.ROUND_HALF_UP) ) ;
								}
							}
						} else if (recalcTaxAmount) {
							if (rql.getLineTotal().compareTo(new BigDecimal(0)) != 0) {
								BigDecimal perc = rql.getLineTotal().divide(bdSubtotal,4,BigDecimal.ROUND_HALF_UP) ;
								rql.setTaxAmount(perc.multiply(rql.getLineTotal().subtract(rql.getDiscountAmount())).setScale(2,BigDecimal.ROUND_HALF_UP)) ;
							}
						}
						
						bdDiscountTotal = bdDiscountTotal.add(rql.getDiscountAmount()) ;
						bdOtherTotal = bdOtherTotal.add(rql.getOtherCharges()) ;
						bdShippingTotal = bdShippingTotal.add(rql.getShippingCharges()) ;
						if (! rql.getTaxable().equals("N")) {
							bdTaxTotal = bdTaxTotal.add(rql.getTaxAmount()) ;
							bdOtherTaxTotal = bdOtherTaxTotal.add(rql.getOtherTaxAmount()) ;
							bdShippingTaxTotal = bdShippingTaxTotal.add(rql.getShippingTaxAmt()) ;
							
							bdTaxableTotal = bdTaxableTotal.add(rql.getLineTotal().subtract(rql.getDiscountAmount())) ;
							bdShippingTaxableTotal = bdShippingTaxableTotal.add(rql.getLineTotal().subtract(rql.getDiscountAmount())) ;
							bdOtherTaxableTotal = bdOtherTaxableTotal.add(rql.getLineTotal().subtract(rql.getDiscountAmount())) ;
						}
						
						if (i == rqlList.size()) {
							if (bdOtherTotal.compareTo(bdOtherCharges) != 0) {
								rql.setOtherCharges(rql.getOtherCharges().add(bdOtherCharges.subtract(bdOtherTotal))) ;
							}
							if (bdShippingTotal.compareTo(bdShippingCharges) != 0) {
								rql.setShippingCharges(rql.getShippingCharges().add(bdShippingCharges.subtract(bdShippingTotal))) ;
							}
							if (bdDiscountTotal.compareTo(bdDiscountAmount) != 0) {
								rql.setDiscountAmount(rql.getDiscountAmount().add(bdDiscountAmount.subtract(bdDiscountTotal))) ;
							}
							if (bdTaxTotal.compareTo(bdTaxAmount) != 0) {
								rql.setTaxAmount(rql.getTaxAmount().add(bdTaxAmount.subtract(bdTaxTotal))) ;
							}
						}
						
						/* Update Line */
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("RequisitionLine_icReqHeader",rql.getIcReqHeader().toString()) ;
						updateParameters.put("RequisitionLine_icReqLine",rql.getIcReqLine().toString()) ;
						updateParameters.put("requisitionLine",rql) ;
						process.executeProcess(updateParameters);
			    }
			}
			    
			BigDecimal bdAllocateTotal = bdSubtotal.subtract(bdDiscountTotal).add(bdTaxTotal)
				.add(bdShippingTotal).add(bdShippingTaxTotal).add(bdOtherTotal).add(bdOtherTaxTotal) ;
				
		    incomingRequest.put("RequisitionHeader_subtotal", bdSubtotal.toString()) ;
			incomingRequest.put("RequisitionHeader_total",bdAllocateTotal.toString()) ;
			incomingRequest.put("RequisitionHeader_discountAmount",bdDiscountTotal.toString()) ;
			incomingRequest.put("RequisitionHeader_taxAmount",bdTaxTotal.toString()) ;
			incomingRequest.put("RequisitionHeader_shippingCharges",bdShippingTotal.toString()) ;		
			incomingRequest.put("RequisitionHeader_shippingTaxAmt",bdShippingTaxTotal.toString()) ;		
			incomingRequest.put("RequisitionHeader_otherCharges",bdOtherTotal.toString()) ;		
			incomingRequest.put("RequisitionHeader_otherTaxAmt",bdOtherTaxTotal.toString()) ;
			incomingRequest.put("allocateTotal",bdAllocateTotal.toString()) ;
			
			incomingRequest.put("Account_icHeader",rqh.getIcReqHeader().toString());
			incomingRequest.put("Account_icLine", "0");
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
