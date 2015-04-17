package com.tsa.puridiom.userstatistic.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class UserStatisticSetValuesPK
{
	public void setValues(Map incomingRequest, UserStatistic userstatistic ) throws Exception
	{
		try
		{
			String userId = (String ) incomingRequest.get("UserStatistic_userId");
			String statType = (String ) incomingRequest.get("UserStatistic_statType");
			String statKey = (String ) incomingRequest.get("UserStatistic_statKey");
			String statYear = (String ) incomingRequest.get("UserStatistic_statYear");
			String statMonth = (String ) incomingRequest.get("UserStatistic_statMonth");
			UserStatisticPK comp_id = new UserStatisticPK();
			comp_id.setStatKey(statKey);
			comp_id.setStatMonth(statMonth);
			comp_id.setStatType(statType);
			comp_id.setStatYear(statYear);
			comp_id.setUserId(userId);
			userstatistic.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}