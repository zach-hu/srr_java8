package com.tsa.puridiom.invbinlocation.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class InvBinLocationGetQuantityAvailable extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String result = null;
		
		try
		{
			InvBinLocation invBinLocation = (InvBinLocation) incomingRequest.get("invBinLocation");
			BigDecimal	bdQtyAvailable = new BigDecimal(0);
			
			if (invBinLocation != null)
			{
				BigDecimal bdQtyOnHand = invBinLocation.getQtyOnHand();
				BigDecimal bdQtyAllocated = invBinLocation.getQtyAlloc();
				
				if (bdQtyOnHand == null)
				{
					bdQtyOnHand = new BigDecimal(0);
				}
				
				if (bdQtyAllocated == null)
				{
					bdQtyAllocated = new BigDecimal(0);
				}
				
				bdQtyAvailable = bdQtyOnHand.subtract(bdQtyAllocated);
			}
			
			result = bdQtyAvailable.toString();
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}