/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class RequisitionHeaderRetrieveVendorAddressSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try
		{
			RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			if (rqh == null) {
				throw new Exception("The RequisitionHeader entity was not found.");
			}
			String	vendorCode = rqh.getVendorId();
			String	contactAddr = rqh.getContactAddr();

			/**
			 *  added on 02.08.07 for VSE  - users may enter a 1-time supplier address
			 *  if they do, the address type is VENDOR
			 *  and the address code is the requisition number
			 **/
			if (vendorCode.equals(rqh.getRequisitionNumber()))
			{
				vendorCode = "VENDOR";
			}

			incomingRequest.put("Address_addressType", vendorCode);
			incomingRequest.put("Vendor_vendorId", vendorCode);
			incomingRequest.put("Address_addressCode", contactAddr);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null;
	}
}
