/*
 * Created on Aug 12, 2004
 */
package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RfqVendor;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RfqSetSelectedVendorInfo extends Task {
	
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		
		try
		{
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			RfqVendor rfqVendor = (RfqVendor) incomingRequest.get("rfqVendor");
			
			reqHeader.setVendorId(rfqVendor.getComp_id().getVendorId());
			reqHeader.setVendContactCode(rfqVendor.getContactId());
			reqHeader.setContactAddr(rfqVendor.getAddressCode());
			
			reqHeader.setDiscountAmount(rfqVendor.getDiscountAmount());
			reqHeader.setDiscountPercent(rfqVendor.getDiscountPercent());
			reqHeader.setDiscountSource(rfqVendor.getDiscountSource());
			reqHeader.setOtherCharges(rfqVendor.getOtherCharges());
			reqHeader.setOtherChargDesc(rfqVendor.getOtherDescription());
			reqHeader.setOtherTaxAmount(rfqVendor.getOtherTaxAmount());
			reqHeader.setShippingCharges(rfqVendor.getShippingCharges());
			reqHeader.setShippingTaxAmt(rfqVendor.getShippingTaxAmt());
			reqHeader.setTaxAmount(rfqVendor.getTaxAmount());
			reqHeader.setTaxCode(rfqVendor.getTaxCode());
			reqHeader.setTaxOther(rfqVendor.getTaxOther());
			reqHeader.setTaxPercent(rfqVendor.getTaxPercent());
			reqHeader.setTaxShipping(rfqVendor.getTaxShipping());
			
			this.setStatus(Status.SUCCEEDED) ;
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
        
		return null;
	}
}