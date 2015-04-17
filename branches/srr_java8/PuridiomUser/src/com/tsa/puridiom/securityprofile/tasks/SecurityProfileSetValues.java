package com.tsa.puridiom.securityprofile.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class SecurityProfileSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			SecurityProfilePK comp_id = new SecurityProfilePK();
			SecurityProfile securityProfile = (SecurityProfile) incomingRequest.get("securityProfile");
			if (securityProfile == null)
			{
				securityProfile = new SecurityProfile();
			}

			if (incomingRequest.containsKey("SecurityProfile_profileType"))
			{
				String profileType = (String ) incomingRequest.get("SecurityProfile_profileType");
				comp_id.setProfileType(profileType);
			}
			if (incomingRequest.containsKey("SecurityProfile_groupId"))
			{
				String groupId = (String ) incomingRequest.get("SecurityProfile_groupId");
				comp_id.setGroupId(groupId);
			}
			if (incomingRequest.containsKey("SecurityProfile_profile"))
			{
				String profile = (String ) incomingRequest.get("SecurityProfile_profile");
				comp_id.setProfile(profile);
			}
			if (incomingRequest.containsKey("SecurityProfile_flags"))
			{
				String flags = (String ) incomingRequest.get("SecurityProfile_flags");
				securityProfile.setFlags(flags);
			}
			if (incomingRequest.containsKey("SecurityProfile_accessValue"))
			{
				String accessValueString = (String) incomingRequest.get("SecurityProfile_accessValue");
				if (Utility.isEmpty(accessValueString))
				{
					accessValueString = "0";
				}
				BigDecimal accessValue = new BigDecimal ( accessValueString );
				securityProfile.setAccessValue(accessValue);
			}
			securityProfile.setComp_id(comp_id);

			result = securityProfile;
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