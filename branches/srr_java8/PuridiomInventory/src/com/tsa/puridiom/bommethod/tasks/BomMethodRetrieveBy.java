package com.tsa.puridiom.bommethod.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class BomMethodRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from BomMethod as bommethod where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("BomMethod_icMethod"))
		{
			String icMethod = (String) incomingRequest.get("BomMethod_icMethod");
			args.add(icMethod);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommethod.icMethod = ?");
		}
		if(incomingRequest.containsKey("BomMethod_parentItem"))
		{
			String parentItem = (String) incomingRequest.get("BomMethod_parentItem");
			args.add(parentItem);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommethod.parentItem = ?");
		}
		if(incomingRequest.containsKey("BomMethod_componentItem"))
		{
			String componentItem = (String) incomingRequest.get("BomMethod_componentItem");
			args.add(componentItem);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommethod.componentItem = ?");
		}
		if(incomingRequest.containsKey("BomMethod_methodId"))
		{
			String methodId = (String) incomingRequest.get("BomMethod_methodId");
			args.add(methodId);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommethod.methodId = ?");
		}
		if(incomingRequest.containsKey("BomMethod_batchSize"))
		{
			String batchSize = (String) incomingRequest.get("BomMethod_batchSize");
			args.add(batchSize);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommethod.batchSize = ?");
		}
		if(incomingRequest.containsKey("BomMethod_unitOfMeasure"))
		{
			String unitOfMeasure = (String) incomingRequest.get("BomMethod_unitOfMeasure");
			args.add(unitOfMeasure);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommethod.unitOfMeasure = ?");
		}
		if(incomingRequest.containsKey("BomMethod_description"))
		{
			String description = (String) incomingRequest.get("BomMethod_description");
			args.add(description);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommethod.description = ?");
		}
		if(incomingRequest.containsKey("BomMethod_notes"))
		{
			String notes = (String) incomingRequest.get("BomMethod_notes");
			args.add(notes);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommethod.notes = ?");
		}
		if(incomingRequest.containsKey("BomMethod_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("BomMethod_dateEntered");
			args.add(dateEntered);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommethod.dateEntered = ?");
		}
		if(incomingRequest.containsKey("BomMethod_owner"))
		{
			String owner = (String) incomingRequest.get("BomMethod_owner");
			args.add(owner);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommethod.owner = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()]));
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}