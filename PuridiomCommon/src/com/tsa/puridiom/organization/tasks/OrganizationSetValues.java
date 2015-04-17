package com.tsa.puridiom.organization.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.sql.Date;
import java.util.Map;

public class OrganizationSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

		try
		{
			Organization organization = (Organization) incomingRequest.get("organization");
			if (organization == null)
			{
				organization = new Organization();
			}

			if (incomingRequest.containsKey("Organization_organizationId"))
			{
				String organizationId = (String) incomingRequest.get("Organization_organizationId");
				if (!Utility.isEmpty(organizationId)) {
					organizationId = organizationId.toUpperCase();
				}
				organization.setOrganizationId(organizationId);
			}
			if (incomingRequest.containsKey("Organization_organizationName"))
			{
				String organizationName = (String) incomingRequest.get("Organization_organizationName");
				organization.setOrganizationName(organizationName);
			}
			if (incomingRequest.containsKey("Organization_dateExpires"))
			{
				String dateExpiresString = (String ) incomingRequest.get("Organization_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString, userDateFormat);
				organization.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("Organization_status"))
			{
				String status = (String) incomingRequest.get("Organization_status");
				organization.setStatus(status);
			}

			result = organization;
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