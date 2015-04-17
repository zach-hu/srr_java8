package com.tsa.puridiom.poheader.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;

import java.util.Map;

public class ContractsRetrieveByVendorIdSetup extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		this.setStatus(Status.SUCCEEDED) ;

		String vendorId = (String) incomingRequest.get("Vendor_vendorId");
		if (vendorId == null)
		{
			this.setStatus(Status.FAILED) ;
			throw new TsaException("Vendor Id was can not be empty!");
		}
		else
		{
			incomingRequest.put("PoHeader_vendorId", vendorId);
		}
		return null;
	}
}