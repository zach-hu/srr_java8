package com.tsa.puridiom.userstatistic.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class UserStatisticSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UserStatisticPK comp_id = new UserStatisticPK();
			UserStatistic userStatistic = (UserStatistic) incomingRequest.get("userStatistic");
			if (userStatistic == null)
			{
				userStatistic = new UserStatistic();
			}

			if (incomingRequest.containsKey("UserStatistic_userId"))
			{
				String userId = (String) incomingRequest.get("UserStatistic_userId");
				comp_id.setUserId(userId);
			}
			if (incomingRequest.containsKey("UserStatistic_statType"))
			{
				String statType = (String) incomingRequest.get("UserStatistic_statType");
				comp_id.setStatType(statType);
			}
			if (incomingRequest.containsKey("UserStatistic_statKey"))
			{
				String statKey = (String) incomingRequest.get("UserStatistic_statKey");
				comp_id.setStatKey(statKey);
			}
			if (incomingRequest.containsKey("UserStatistic_statYear"))
			{
				String statYear = (String) incomingRequest.get("UserStatistic_statYear");
				comp_id.setStatYear(statYear);
			}
			if (incomingRequest.containsKey("UserStatistic_statMonth"))
			{
				String statMonth = (String) incomingRequest.get("UserStatistic_statMonth");
				comp_id.setStatMonth(statMonth);
			}
			if (incomingRequest.containsKey("UserStatistic_statCount"))
			{
				String statCountString = (String) incomingRequest.get("UserStatistic_statCount");
				if (Utility.isEmpty(statCountString))
				{
					statCountString = "0";
				}
				BigDecimal statCount = new BigDecimal ( statCountString );
				userStatistic.setStatCount(statCount);
			}
			if (incomingRequest.containsKey("UserStatistic_statTotal"))
			{
				String statTotalString = (String) incomingRequest.get("UserStatistic_statTotal");
				if (Utility.isEmpty(statTotalString))
				{
					statTotalString = "0";
				}
				BigDecimal statTotal = new BigDecimal ( statTotalString );
				userStatistic.setStatTotal(statTotal);
			}
			userStatistic.setComp_id(comp_id);

			result = userStatistic;
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