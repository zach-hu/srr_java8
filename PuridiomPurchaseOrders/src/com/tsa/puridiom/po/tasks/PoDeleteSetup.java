/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoDeleteSetup extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map) object;
		try
		{
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
		
			if (poHeader == null) 
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("Po entity was not found!");
			} 
			else 
			{
				String icPoHeader = poHeader.getIcPoHeader().toString();
				incomingRequest.put("Schedule_icHeader", icPoHeader) ;
				incomingRequest.put("BillTo_icHeader", icPoHeader) ;
				incomingRequest.put("ShipTo_icHeader", icPoHeader) ;
				incomingRequest.put("Account_icHeader", icPoHeader) ;
				incomingRequest.put("DocComment_icHeader", icPoHeader) ;
				incomingRequest.put("DocAttachment_icHeader", icPoHeader) ;
				incomingRequest.put("PoLine_icPoHeader", icPoHeader) ;
				incomingRequest.put("ChecklistResponse_icHeader", icPoHeader) ;
				
				this.setStatus(Status.SUCCEEDED);
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null ;
	}
}