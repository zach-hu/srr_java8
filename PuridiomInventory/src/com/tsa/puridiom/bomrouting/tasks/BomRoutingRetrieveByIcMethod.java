package com.tsa.puridiom.bomrouting.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class BomRoutingRetrieveByIcMethod extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from BomRouting as BomRouting where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("BomRouting_icMethod"))
		{
			String icMethod = (String) incomingRequest.get("BomRouting_icMethod");
			args.add(icMethod);
			types.add(Hibernate.STRING);
			queryString.append(" AND BomRouting.icMethod = ?" );
			queryString.append(" order by BomRouting.stageId ASC") ;
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}