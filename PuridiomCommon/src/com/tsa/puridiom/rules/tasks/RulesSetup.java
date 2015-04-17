package com.tsa.puridiom.rules.tasks;

import java.util.Map;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

public class RulesSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
        	UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

        	String userId = (String) incomingRequest.get("userId");
        	String organizationId = (String) incomingRequest.get("organizationId");
        	String userTimeZone = (String) incomingRequest.get("userTimeZone");
        	String userDateFormat = (String) incomingRequest.get("userDateFormat");
        	if (Utility.isEmpty(userDateFormat)) {
        		userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        	}
        	String today = Dates.today(userDateFormat, userTimeZone);

            incomingRequest.put("Rules_icRule", ukg.getUniqueKey().toString());
            incomingRequest.put("Rules_owner", userId);
            incomingRequest.put("Rules_lastChangeBy", userId);
            incomingRequest.put("Rules_lastChangeDate", today);

            this.status = Status.SUCCEEDED;
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw new TsaException(this.getName(), e);
        }
        return result;
    }
}
