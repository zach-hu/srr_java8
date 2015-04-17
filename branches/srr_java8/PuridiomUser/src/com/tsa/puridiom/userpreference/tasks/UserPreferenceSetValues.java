package com.tsa.puridiom.userpreference.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class UserPreferenceSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UserPreferencePK comp_id = new UserPreferencePK();
			UserPreference userPreference = (UserPreference) incomingRequest.get("userPreference");
			if (userPreference == null)
			{
				userPreference = new UserPreference();
			}

			if (incomingRequest.containsKey("UserPreference_userId"))
			{
				String userId = (String ) incomingRequest.get("UserPreference_userId");
				comp_id.setUserId(userId);
			}
			if (incomingRequest.containsKey("UserPreference_property"))
			{
				String property = (String) incomingRequest.get("UserPreference_property");
				comp_id.setProperty(property);
			}
			if (incomingRequest.containsKey("UserPreference_value"))
			{
				String value = (String) incomingRequest.get("UserPreference_value");
				userPreference.setValue(value);
			}
			
			userPreference.setComp_id(comp_id);

			result = userPreference;
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