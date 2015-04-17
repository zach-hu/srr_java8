package com.tsa.puridiom.billto.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class BillToRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from BillTo as billto where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("BillTo_icHeader"))
		{			
			String icHeader = (String) incomingRequest.get("BillTo_icHeader");
			args.add(icHeader);
			types.add(Hibernate.STRING);
			queryString.append(" AND billto.id.icHeader = ?");
		}
		if(incomingRequest.containsKey("BillTo_icLine"))
		{			
			String icLine = (String) incomingRequest.get("BillTo_icLine");
			args.add(icLine);
			types.add(Hibernate.STRING);
			queryString.append(" AND billto.id.icLine = ?");
		}
		if(incomingRequest.containsKey("BillTo_billToCode"))
		{			
			String billToCode = (String) incomingRequest.get("BillTo_billToCode");
			args.add(billToCode);
			types.add(Hibernate.STRING);
			queryString.append(" AND billto.id.billToCode = ?");
		}
		if(incomingRequest.containsKey("BillTo_quantity"))
		{			
			String quantity = (String) incomingRequest.get("BillTo_quantity");
			args.add(quantity);
			types.add(Hibernate.STRING);
			queryString.append(" AND billto.quantity = ?");
		}
		if(incomingRequest.containsKey("BillTo_attention"))
		{			
			String attention = (String) incomingRequest.get("BillTo_attention");
			args.add(attention);
			types.add(Hibernate.STRING);
			queryString.append(" AND billto.attention = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}