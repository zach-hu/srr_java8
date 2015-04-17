package com.tsa.puridiom.posecurity.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;

public class PoSecuritySetValuesPK
{
	public void setValues(Map incomingRequest, PoSecurity posecurity ) throws Exception
	{
		try
		{
			String poNumber = (String ) incomingRequest.get("PoSecurity_poNumber");
			String accessType = (String ) incomingRequest.get("PoSecurity_accessType");
			String accessId = (String ) incomingRequest.get("PoSecurity_accessId");
			PoSecurityPK comp_id = new PoSecurityPK();
			comp_id.setAccessId(accessId);
			comp_id.setAccessType(accessType);
			comp_id.setPoNumber(poNumber);
			posecurity.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}