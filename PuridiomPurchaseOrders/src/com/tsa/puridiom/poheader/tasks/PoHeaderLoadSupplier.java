/**
 * 
 * Created on Jan 28, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderLoadSupplier.java
 * 
 */
package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderLoadSupplier extends Task
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
			RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			Vendor vendor =(Vendor)incomingRequest.get("vendor");
			
			poHeader.setVendorName(requisitionHeader.getVendorName());
			poHeader.setVendContactCode(requisitionHeader.getVendContactCode());
			poHeader.setContactPhone(requisitionHeader.getContactPhone());
			poHeader.setFobCode(vendor.getFobId());
			poHeader.setPoCopies(vendor.getPoCopies());
			poHeader.setShipViaCode(vendor.getShipVia());
			poHeader.setCurrencyCode(vendor.getCurrencyCode());
			poHeader.setTermsCode(vendor.getVendTerms());
			poHeader.setCurrencyFactor((BigDecimal)incomingRequest.get("CurrCode_factor"));
			poHeader.setInspectionReqd(vendor.getInspectionReqd());
		}
		catch (Exception e)
		{
			throw new TsaException(e.toString());
		}
		return poHeader;
	}

}
