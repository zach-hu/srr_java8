package com.tsa.puridiom.hostuser.tasks;

import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class HostUserRetrieveByNewMailId extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
		    // Always use host database configuration for this table
		    DBSession dbs = new DBSession("host");
			String mailId = (String ) incomingRequest.get("newHostUser_mailId");
			
			mailId = Utility.ckNull(mailId).toLowerCase();

			String queryString = "from HostUser as HostUser where HostUser.mailId = ? ";
			List resultList = dbs.query(queryString, new Object[] {mailId, } , new Type[] { Hibernate.STRING}) ;
					
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