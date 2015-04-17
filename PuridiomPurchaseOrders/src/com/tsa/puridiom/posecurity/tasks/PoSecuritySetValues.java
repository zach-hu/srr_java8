package com.tsa.puridiom.posecurity.tasks;

import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.PoSecurity;
import com.tsa.puridiom.entity.PoSecurityPK;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class PoSecuritySetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String organizationId = (String)incomingRequest.get("organizationId");
		PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
		String userDateFormat = (String) incomingRequest.get("userDateFormat");
		if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = propertiesManager.getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }
		
		Object result = null;

		try
		{
			PoSecurityPK comp_id = new PoSecurityPK();
			PoSecurity poSecurity = (PoSecurity) incomingRequest.get("poSecurity");
			if (poSecurity == null)
			{
				poSecurity = new PoSecurity();
			}

			if (incomingRequest.containsKey("PoSecurity_poNumber"))
			{
				String poNumber = (String) incomingRequest.get("PoSecurity_poNumber");
				comp_id.setPoNumber(poNumber);
			}
			if (incomingRequest.containsKey("PoSecurity_accessType"))
			{
				String accessType = (String) incomingRequest.get("PoSecurity_accessType");
				comp_id.setAccessType(accessType);
			}
			if (incomingRequest.containsKey("PoSecurity_accessId"))
			{
				String accessId = (String) incomingRequest.get("PoSecurity_accessId");
				comp_id.setAccessId(accessId);
			}
			if (incomingRequest.containsKey("PoSecurity_owner"))
			{
				String owner = (String) incomingRequest.get("PoSecurity_owner");
				poSecurity.setOwner(owner);
			}
			if (incomingRequest.containsKey("PoSecurity_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("PoSecurity_dateEntered");
				Date dateEntered = Dates.getSqlDate(dateEnteredString, userDateFormat);
				poSecurity.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("PoSecurity_dateChanged"))
			{
				String dateChangedString = (String) incomingRequest.get("PoSecurity_dateChanged");
				Date dateChanged = Dates.getSqlDate(dateChangedString, userDateFormat);
				poSecurity.setDateChanged(dateChanged);
			}
			if (incomingRequest.containsKey("PoSecurity_lastChangedBy"))
			{
				String lastChangedBy = (String) incomingRequest.get("PoSecurity_lastChangedBy");
				poSecurity.setLastChangedBy(lastChangedBy);
			}
			if (incomingRequest.containsKey("PoSecurity_timeZone"))
			{
				String timeZone = (String) incomingRequest.get("PoSecurity_timeZone");
				poSecurity.setTimeZone(timeZone);
			}
			poSecurity.setComp_id(comp_id);

			result = poSecurity;
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
