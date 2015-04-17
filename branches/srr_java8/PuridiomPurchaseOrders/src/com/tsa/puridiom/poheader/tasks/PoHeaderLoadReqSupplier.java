/**
 * 
 * Created on Jan 28, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderLoadReqSupplier.java
 * 
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;

public class PoHeaderLoadReqSupplier extends Task
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
			String organizationId = (String)incomingRequest.get("organizationId");
			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;
			String getVendorValue = propertiesManager.getProperty("PO DEFAULTS", "POGETVENDORVALUE", "N");
			poHeader.setVendorName(requisitionHeader.getVendorName());
			poHeader.setVendContactCode(requisitionHeader.getVendContactCode());
			poHeader.setContactPhone(requisitionHeader.getContactPhone());
			if (getVendorValue.equalsIgnoreCase("Y") && vendor != null) {
				poHeader.setFobCode(vendor.getFobId());
				if (Utility.isEmpty(requisitionHeader.getShippingCode())) {
					poHeader.setShipViaCode(vendor.getShipVia());
				}
				poHeader.setTermsCode(vendor.getVendTerms());
				poHeader.setVendorClass(vendor.getVendorClass());
				poHeader.setEdiOrder(vendor.getPrintFaxCode());
			}
		}
		catch (Exception e)
		{
			throw new TsaException(e.toString());
		}
		return poHeader;
	}

}
