package com.tsa.puridiom.subcommodity.tasks;

import com.tsa.puridiom.entity.SubCommodity;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.sql.Date;
import java.util.Map;

public class SubCommoditySetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    SubCommodity subCommodity = (SubCommodity) incomingRequest.get("subCommodity");
			if (subCommodity == null)
			{
				subCommodity = new SubCommodity();
			}

			if (incomingRequest.containsKey("SubCommodity_commodity"))
			{
				String commodityCode = (String) incomingRequest.get("SubCommodity_commodity");
				subCommodity.setCommodity(commodityCode);
			}
			if (incomingRequest.containsKey("SubCommodity_description"))
			{
				String description = (String) incomingRequest.get("SubCommodity_description");
				subCommodity.setDescription(description);
			}
			if (incomingRequest.containsKey("SubCommodity_status"))
			{
				String status = (String) incomingRequest.get("SubCommodity_status");
				subCommodity.setStatus(status);
			}
			if (incomingRequest.containsKey("SubCommodity_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("SubCommodity_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				subCommodity.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("SubCommodity_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("SubCommodity_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				subCommodity.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("SubCommodity_owner"))
			{
				String owner = (String) incomingRequest.get("SubCommodity_owner");
				subCommodity.setOwner(owner);
			}
			if (incomingRequest.containsKey("SubCommodity_lastChgBy"))
			{
				String lastChgBy = (String) incomingRequest.get("SubCommodity_lastChgBy");
				subCommodity.setLastChgBy(lastChgBy);
			}
			else
			{
			    subCommodity.setLastChgBy((String) incomingRequest.get("userId"));
			}
			if (incomingRequest.containsKey("SubCommodity_lastChgDate"))
			{
				String lastChgDateString = (String) incomingRequest.get("SubCommodity_lastChgDate");
				Date lastChgDate = Dates.getDate(lastChgDateString);
				subCommodity.setLastChgDate(lastChgDate);
			}
			else
			{
                String userTimeZone = (String) incomingRequest.get("userTimeZone");
			    subCommodity.setLastChgDate(Dates.getDate(Dates.today("", userTimeZone)));
			}
			if (incomingRequest.containsKey("SubCommodity_nigplevel"))
			{
				String nigplevel = (String) incomingRequest.get("SubCommodity_nigplevel");
				subCommodity.setNigplevel(nigplevel);
			}
			if (incomingRequest.containsKey("SubCommodity_level1"))
			{
				String level1 = (String) incomingRequest.get("SubCommodity_level1");
				subCommodity.setLevel1(level1);
			}
			if (incomingRequest.containsKey("SubCommodity_level2"))
			{
				String level2 = (String) incomingRequest.get("SubCommodity_level2");
				subCommodity.setLevel2(level2);
			}
			if (incomingRequest.containsKey("SubCommodity_level3"))
			{
				String level3 = (String) incomingRequest.get("SubCommodity_level3");
				subCommodity.setLevel3(level3);
			}

			result = subCommodity;
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
