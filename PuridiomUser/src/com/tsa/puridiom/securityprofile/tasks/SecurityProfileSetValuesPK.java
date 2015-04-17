package com.tsa.puridiom.securityprofile.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class SecurityProfileSetValuesPK
{
	public void setValues(Map incomingRequest, SecurityProfile securityprofile ) throws Exception
	{
		try
		{
			String profileType = (String ) incomingRequest.get("SecurityProfile_profileType");
			String groupId = (String ) incomingRequest.get("SecurityProfile_groupId");
			String profile = (String ) incomingRequest.get("SecurityProfile_profile");
			SecurityProfilePK comp_id = new SecurityProfilePK();
			comp_id.setGroupId(groupId);
			comp_id.setProfile(profile);
			comp_id.setProfileType(profileType);
			securityprofile.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}