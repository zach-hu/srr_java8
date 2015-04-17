package com.tsa.puridiom.apppooluser.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class AppPooluserRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from AppPooluser as apppooluser where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("AppPooluser_poolid"))
		{			
			String poolid = (String) incomingRequest.get("AppPooluser_poolid");
			args.add(poolid);
			types.add(Hibernate.STRING);
			queryString.append(" AND apppooluser.id.poolid = ?");
		}
		if(incomingRequest.containsKey("AppPooluser_userId"))
		{			
			String userId = (String) incomingRequest.get("AppPooluser_userId");
			args.add(userId);
			types.add(Hibernate.STRING);
			queryString.append(" AND apppooluser.id.userId = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()]));
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}