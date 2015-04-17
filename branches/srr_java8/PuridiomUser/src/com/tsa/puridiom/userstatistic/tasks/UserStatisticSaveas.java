package com.tsa.puridiom.userstatistic.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class UserStatisticSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain userStatistic */
			UserStatisticPK comp_id = new UserStatisticPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			UserStatistic	originalUserStatistic = (UserStatistic) incomingRequest.get("userStatistic");
			UserStatistic	userStatistic = new UserStatistic();

			comp_id.setUserId(originalUserStatistic.getComp_id().getUserId());
			comp_id.setStatType(originalUserStatistic.getComp_id().getStatType());
			comp_id.setStatKey(originalUserStatistic.getComp_id().getStatKey());
			comp_id.setStatYear(originalUserStatistic.getComp_id().getStatYear());
			comp_id.setStatMonth(originalUserStatistic.getComp_id().getStatMonth());
			userStatistic.setStatCount(originalUserStatistic.getStatCount());
			userStatistic.setStatTotal(originalUserStatistic.getStatTotal());
			userStatistic.setComp_id(comp_id);

			incomingRequest.put("userStatistic", userStatistic);

			UserStatisticAdd addTask = new UserStatisticAdd();
			userStatistic = (UserStatistic) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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