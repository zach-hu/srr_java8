/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.disbheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.DisbHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class DisbHeaderRetrieveInventoryAddressSetup extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try
		{
			DisbHeader disbh = (DisbHeader) incomingRequest.get("disbHeader") ;
			if (disbh == null) 
			{
				throw new Exception("The Disbursement entity was not found.");
			}
			String inventoryLocation = disbh.getItemLocation();

			incomingRequest.put("Address_addressCode", inventoryLocation) ;
			incomingRequest.put("Address_addressType", "ADDR") ;
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
