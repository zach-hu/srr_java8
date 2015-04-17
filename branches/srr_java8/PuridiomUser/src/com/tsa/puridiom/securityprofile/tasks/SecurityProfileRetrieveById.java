package com.tsa.puridiom.securityprofile.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class SecurityProfileRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String profileType = (String ) incomingRequest.get("SecurityProfile_profileType");
			String groupId = (String ) incomingRequest.get("SecurityProfile_groupId");
			String profile = (String ) incomingRequest.get("SecurityProfile_profile");

			String queryString = "from SecurityProfile as SecurityProfile where SecurityProfile.id.profileType = ? and SecurityProfile.id.groupId = ? and SecurityProfile.id.profile = ? ";
			List resultList = dbs.query(queryString, new Object[] {profileType, groupId, profile, } , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}