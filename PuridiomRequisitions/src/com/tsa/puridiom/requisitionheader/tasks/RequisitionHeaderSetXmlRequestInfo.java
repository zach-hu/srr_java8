/*
 * Created on March 8, 2003
 */
package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.util.*;

/**
 * @author Kelli
 */
public class RequisitionHeaderSetXmlRequestInfo extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			Catalog catalog = (Catalog) incomingRequest.get("catalog");
			String	organizationId = (String) incomingRequest.get("organizationId");
			String	vendorId = catalog.getVendorId() ;

			if (requisitionHeader != null) {
			    // set values on entity and in incomingRequest in case header is updated using setValues later
			    if (Utility.isEmpty(requisitionHeader.getInternalComments()) && catalog != null) {
			        incomingRequest.put("RequisitionHeader_internalComments", catalog.getTitle());
		            requisitionHeader.setInternalComments(catalog.getTitle()) ;
			    }
			    if (! Utility.isEmpty(vendorId) && !(requisitionHeader.getRequisitionType().equalsIgnoreCase("M"))) {
			    	requisitionHeader.setVendorId(vendorId);
			    	requisitionHeader.setVendorName(VendorManager.getInstance().getVendorName(organizationId, vendorId));
			    }
			    incomingRequest.put("RequisitionHeader_rqSubType", "X");
			    requisitionHeader.setRqSubType("X");
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}
}
