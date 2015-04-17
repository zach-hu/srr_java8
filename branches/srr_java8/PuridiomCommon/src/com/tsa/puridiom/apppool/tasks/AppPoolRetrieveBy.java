package com.tsa.puridiom.apppool.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class AppPoolRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from AppPool as apppool where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("AppPool_poolid"))
		{			
			String poolid = (String) incomingRequest.get("AppPool_poolid");
			args.add(poolid);
			types.add(Hibernate.STRING);
			queryString.append(" AND apppool.poolid = ?");
		}
		if(incomingRequest.containsKey("AppPool_pooldesc"))
		{			
			String pooldesc = (String) incomingRequest.get("AppPool_pooldesc");
			args.add(pooldesc);
			types.add(Hibernate.STRING);
			queryString.append(" AND apppool.pooldesc = ?");
		}
		if(incomingRequest.containsKey("AppPool_poolflag1"))
		{			
			String poolflag1 = (String) incomingRequest.get("AppPool_poolflag1");
			args.add(poolflag1);
			types.add(Hibernate.STRING);
			queryString.append(" AND apppool.poolflag1 = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}