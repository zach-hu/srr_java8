/**
 * 
 * Created on Jan 28, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderLoadSupplier.java
 * 
 */
package com.tsa.puridiom.poheader.tasks;

import com.tsa.puridiom.currcode.CurrencyManager;
import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqVendor;
import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoHeaderLoadRfqSupplier extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
		try
		{
		    String	organizationId = (String) incomingRequest.get("organizationId");
		    
			RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");
			RfqVendor rfqVendor =(RfqVendor)incomingRequest.get("rfqVendor");
			Vendor vendor =(Vendor)incomingRequest.get("vendor");
			String contactCode = rfqVendor.getContactId();
			String currencyCode = rfqVendor.getVendCurrency();
			poHeader.setVendContactCode(contactCode);
			
			//poHeader.setVendorName(rfqHeader.getVendorName());
			
			poHeader.setFobCode(rfqVendor.getFob());
			poHeader.setTermsCode(rfqVendor.getPaymentTerms());
			poHeader.setTaxAmount(rfqVendor.getTaxAmount());
			poHeader.setShippingCharges(rfqVendor.getShippingCharges());
			poHeader.setCurrencyCode(currencyCode);
			poHeader.setCurrencyFactor(CurrencyManager.getInstance(organizationId).getCurrencyFactor(currencyCode));
			poHeader.setOtherCharges(rfqVendor.getOtherCharges());
			poHeader.setOtherTax(rfqVendor.getOtherTaxAmount());
			poHeader.setShippingCharges(rfqVendor.getShippingCharges());
			poHeader.setShippingTax(rfqVendor.getShippingTaxAmt());
			
			// Use Currency Manager to get factor instead - removed process to populate CurrCode_factor - KK (09-21-06)
			//poHeader.setCurrencyFactor((BigDecimal)incomingRequest.get("CurrCode_factor"));
			
//			need to get current value for vendor_class from vendor table instead of rfq_vendor table
			poHeader.setVendorClass(vendor.getVendorClass());
			
//			need to get ship via from vendor if not on rfq
			if (Utility.isEmpty(rfqHeader.getShippingCode())) {
				poHeader.setShipViaCode(vendor.getShipVia());
			}
			
			poHeader.setEdiOrder(vendor.getPrintFaxCode());
			
		}
		catch (Exception e)
		{
			throw new TsaException(e.toString());
		}
		return poHeader;
	}

}
