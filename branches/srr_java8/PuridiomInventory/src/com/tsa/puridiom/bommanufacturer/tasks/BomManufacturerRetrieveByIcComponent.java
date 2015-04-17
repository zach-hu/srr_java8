package com.tsa.puridiom.bommanufacturer.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task ;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class BomManufacturerRetrieveByIcComponent extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from BomManufacturer as BomManufacturer where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("BomManufacturer_icComponent"))
		{
			String icComponent = (String) incomingRequest.get("BomManufacturer_icComponent");
			args.add(icComponent);
			types.add(Hibernate.STRING);
			queryString.append(" AND BomManufacturer.icComponent = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}