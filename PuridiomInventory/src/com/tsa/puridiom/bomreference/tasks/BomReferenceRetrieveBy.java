package com.tsa.puridiom.bomreference.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class BomReferenceRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from BomReference as bomreference where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("BomReference_icReference"))
		{
			String icReference = (String) incomingRequest.get("BomReference_icReference");
			args.add(icReference);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomreference.icReference = ?");
		}
		if(incomingRequest.containsKey("BomReference_parentItem"))
		{
			String parentItem = (String) incomingRequest.get("BomReference_parentItem");
			args.add(parentItem);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomreference.parentItem = ?");
		}
		if(incomingRequest.containsKey("BomReference_componentItem"))
		{
			String componentItem = (String) incomingRequest.get("BomReference_componentItem");
			args.add(componentItem);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomreference.componentItem = ?");
		}
		if(incomingRequest.containsKey("BomReference_icComponent"))
		{
			String icComponent = (String) incomingRequest.get("BomReference_icComponent");
			args.add(icComponent);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomreference.icComponent = ?");
		}
		if(incomingRequest.containsKey("BomReference_referenceId"))
		{
			String referenceId = (String) incomingRequest.get("BomReference_referenceId");
			args.add(referenceId);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomreference.referenceId = ?");
		}
		if(incomingRequest.containsKey("BomReference_refNo"))
		{
			String refNo = (String) incomingRequest.get("BomReference_refNo");
			args.add(refNo);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomreference.refNo = ?");
		}
		if(incomingRequest.containsKey("BomReference_qty"))
		{
			String qty = (String) incomingRequest.get("BomReference_qty");
			args.add(qty);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomreference.qty = ?");
		}
		if(incomingRequest.containsKey("BomReference_methodId"))
		{
			String methodId = (String) incomingRequest.get("BomReference_methodId");
			args.add(methodId);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomreference.methodId = ?");
		}
		if(incomingRequest.containsKey("BomReference_stageId"))
		{
			String stageId = (String) incomingRequest.get("BomReference_stageId");
			args.add(stageId);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomreference.stageId = ?");
		}
		if(incomingRequest.containsKey("BomReference_icRouting"))
		{
			String icRouting = (String) incomingRequest.get("BomReference_icRouting");
			args.add(icRouting);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomreference.icRouting = ?");
		}
		if(incomingRequest.containsKey("BomReference_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("BomReference_dateEntered");
			args.add(dateEntered);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomreference.dateEntered = ?");
		}
		if(incomingRequest.containsKey("BomReference_owner"))
		{
			String owner = (String) incomingRequest.get("BomReference_owner");
			args.add(owner);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomreference.owner = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}