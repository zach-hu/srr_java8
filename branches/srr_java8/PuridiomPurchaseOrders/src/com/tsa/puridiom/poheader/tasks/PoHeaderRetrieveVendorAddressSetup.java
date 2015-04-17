/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.poheader.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;

public class PoHeaderRetrieveVendorAddressSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			PoHeader poh = (PoHeader) incomingRequest.get("poHeader") ;
			String vendorContactCode = poh.getVendContactCode();

			if (poh == null) {
				throw new TsaException("The PoHeader entity was not found.");
			}
			String	vendorCode = poh.getVendorId();
			String	contactAddr = poh.getContactAddr();

			if (Utility.isEmpty(contactAddr)) {
			    contactAddr = "DEFAULT";
			}

			/**
			 *  added on 03.14.07 for VSE  - users may enter a 1-time supplier address on the requisition
			 *  if they do, the address type is VENDOR
			 *  and the address code is the requisition number
			 **/
			if (vendorCode.equals(poh.getRequisitionNumber()))
			{
				vendorCode = "VENDOR";
			}

			incomingRequest.put("Address_addressType", vendorCode);
			incomingRequest.put("Address_addressCode", contactAddr);

			if(Utility.isEmpty(vendorContactCode))
				vendorContactCode = "001";

			incomingRequest.put("Contact_contactCode", vendorContactCode);
			incomingRequest.put("Contact_vendorId", poh.getVendorId());
			incomingRequest.put("Vendor_vendorId", poh.getVendorId());

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}

		return result ;
	}
}
