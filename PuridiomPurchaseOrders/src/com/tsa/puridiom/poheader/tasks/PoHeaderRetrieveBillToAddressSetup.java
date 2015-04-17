
package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderRetrieveBillToAddressSetup extends Task 
{
	
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try
		{
			PoHeader poh = (PoHeader) incomingRequest.get("poHeader") ;
			if (poh == null) 
			{
				throw new TsaException("The poHeader entity was not found.");
			}
			String billToCode = poh.getBillToCode();

			incomingRequest.put("Address_addressCode", billToCode) ;
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
