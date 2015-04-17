/*
 * Created on Oct 31, 2010
 */
package com.tsa.puridiom.vendoraffiliate.tasks;

import java.util.List;

import java.util.Map;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.entity.VendorAffiliate;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Kelli
 */
public class VendorAffiliateDataSet extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		VendorAffiliate vendorAffiliate = (VendorAffiliate) incomingRequest.get("vendorAffiliate") ;

		vendorAffiliate.setAddress((Address) incomingRequest.get("affiliateAddress")) ;
		vendorAffiliate.setContact((Contact) incomingRequest.get("affiliateContact")) ;

		this.setStatus(Status.SUCCEEDED) ;

		return null  ;
	}
}
