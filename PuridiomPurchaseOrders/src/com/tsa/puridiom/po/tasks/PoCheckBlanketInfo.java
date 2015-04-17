/**
 * 
 * Created on Feb 14, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoCheckBlanketInfo.java
 * 
 */
package com.tsa.puridiom.po.tasks;

import java.util.Date;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.poheader.tasks.UserErrors;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoCheckBlanketInfo extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
		    String	organizationId = (String) incomingRequest.get("organizationId");
			PoHeader blanket = (PoHeader)incomingRequest.get("blanketInfo");
			incomingRequest.put("blanketIc", blanket.getIcPoHeader());
			incomingRequest.put("blanketLimit", blanket.getBlanketLimit());
			incomingRequest.put("releaseLimit", blanket.getReleaseLimit());
			incomingRequest.put("expDate", blanket.getExpirationDate());
			Date orderDate = (Date)incomingRequest.get("orderDate");
			UserErrors errors = (UserErrors)incomingRequest.get("userErrors");
			
			if(blanket.getExpirationDate().before(orderDate))
			{
				if(errors == null){	errors = new UserErrors();	}
				
				errors.addError(PoErrors.blanketOrderExpired(blanket.getPoNumber(), blanket.getExpirationDate(), organizationId), PoErrors.BLANKETEXPIRED);
				incomingRequest.put("userErrors", errors);
			}
		    this.setStatus(Status.SUCCEEDED);	
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return super.executeTask(object);
	}

}
