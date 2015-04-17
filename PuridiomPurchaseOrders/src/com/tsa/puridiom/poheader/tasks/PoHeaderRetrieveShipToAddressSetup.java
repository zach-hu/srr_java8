/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderRetrieveShipToAddressSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			PoHeader poh = (PoHeader) incomingRequest.get("poHeader") ;
			if (poh == null) {
				throw new Exception("The PoHeader entity was not found.");
			}
			String shipToCode = poh.getShipToCode();
			String addressType = "ADDR";

			/**
			 *  added on 03.14.07 for VSE  - users may enter a 1-time shipto address on the requisition
			 *  if they do, the address type is SHIPTO
			 *  and the address code is the requisition number
			 **/
			if (shipToCode.equals(poh.getRequisitionNumber()))
			{
				addressType = "SHIPTO";
			}

			incomingRequest.put("Address_addressCode", shipToCode);
			incomingRequest.put("Address_addressType", addressType);

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
