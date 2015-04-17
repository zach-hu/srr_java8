package com.tsa.puridiom.invalternate.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvAlternateSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvAlternate invAlternate = (InvAlternate) incomingRequest.get("invAlternate");
			if (invAlternate == null)
			{
				invAlternate = new InvAlternate();
			}

			if (incomingRequest.containsKey("InvAlternate_icAlternate"))
			{
				String icAlternateString = (String) incomingRequest.get("InvAlternate_icAlternate");
				if (Utility.isEmpty(icAlternateString))
				{
					icAlternateString = "0";
				}
				BigDecimal icAlternate = new BigDecimal ( icAlternateString );
				invAlternate.setIcAlternate(icAlternate);
			}
			if (incomingRequest.containsKey("InvAlternate_itemNumber"))
			{
				String itemNumber = (String) incomingRequest.get("InvAlternate_itemNumber");
				invAlternate.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("InvAlternate_altItemNumber"))
			{
				String altItemNumber = (String) incomingRequest.get("InvAlternate_altItemNumber");
				invAlternate.setAltItemNumber(altItemNumber);
			}
			if (incomingRequest.containsKey("InvAlternate_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("InvAlternate_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				invAlternate.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("InvAlternate_owner"))
			{
				String owner = (String) incomingRequest.get("InvAlternate_owner");
				invAlternate.setOwner(owner);
			}

			result = invAlternate;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}