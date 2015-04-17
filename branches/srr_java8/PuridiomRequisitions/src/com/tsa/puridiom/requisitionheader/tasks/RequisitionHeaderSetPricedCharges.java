/*
 * Created on Dec 2, 2003
 */
package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.Map;
/**
 * @author Kelli
 */
public class RequisitionHeaderSetPricedCharges extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		
		try {
			String	shippingCharges = (String) incomingRequest.get("shippingCharges");
			String	taxAmount = (String) incomingRequest.get("taxAmount");
			BigDecimal bdShipping = new BigDecimal(0);
			BigDecimal bdTax = new BigDecimal(0);
			
			if (shippingCharges != null)
			{
				bdShipping = new BigDecimal(shippingCharges);
			}
			if (taxAmount != null)
			{
				bdTax = new BigDecimal(taxAmount);
			}
			
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			reqHeader.setShippingCharges(bdShipping);
			reqHeader.setTaxAmount(bdTax);
			//incomingRequest.put("RequisitionHeader_shippingCharges", shippingCharges);
			//incomingRequest.put("RequisitionHeader_taxAmount", taxAmount);
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
        
		return null  ;
	}
}
