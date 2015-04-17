package com.tsa.puridiom.bommethod.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class BomMethodRetrieveByItem extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from BomMethod as BomMethod where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("InvItem_itemNumber"))
		{
			String itemNumber = (String) incomingRequest.get("InvItem_itemNumber");
			args.add(itemNumber);
			types.add(Hibernate.STRING);
			queryString.append(" AND BomMethod.parentItem = ?");
			queryString.append(" order by BomMethod.icMethod ASC") ;
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}