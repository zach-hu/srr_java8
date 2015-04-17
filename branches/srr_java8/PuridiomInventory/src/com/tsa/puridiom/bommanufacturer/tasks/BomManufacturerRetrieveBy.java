package com.tsa.puridiom.bommanufacturer.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class BomManufacturerRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from BomManufacturer as bommanufacturer where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("BomManufacturer_icManufacturer"))
		{
			String icManufacturer = (String) incomingRequest.get("BomManufacturer_icManufacturer");
			args.add(icManufacturer);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommanufacturer.icManufacturer = ?");
		}
		if(incomingRequest.containsKey("BomManufacturer_parentItem"))
		{
			String parentItem = (String) incomingRequest.get("BomManufacturer_parentItem");
			args.add(parentItem);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommanufacturer.parentItem = ?");
		}
		if(incomingRequest.containsKey("BomManufacturer_componentItem"))
		{
			String componentItem = (String) incomingRequest.get("BomManufacturer_componentItem");
			args.add(componentItem);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommanufacturer.componentItem = ?");
		}
		if(incomingRequest.containsKey("BomManufacturer_icComponent"))
		{
			String icComponent = (String) incomingRequest.get("BomManufacturer_icComponent");
			args.add(icComponent);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommanufacturer.icComponent = ?");
		}
		if(incomingRequest.containsKey("BomManufacturer_vendorId"))
		{
			String vendorId = (String) incomingRequest.get("BomManufacturer_vendorId");
			args.add(vendorId);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommanufacturer.vendorId = ?");
		}
		if(incomingRequest.containsKey("BomManufacturer_vendorName"))
		{
			String vendorName = (String) incomingRequest.get("BomManufacturer_vendorName");
			args.add(vendorName);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommanufacturer.vendorName = ?");
		}
		if(incomingRequest.containsKey("BomManufacturer_methodId"))
		{
			String methodId = (String) incomingRequest.get("BomManufacturer_methodId");
			args.add(methodId);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommanufacturer.methodId = ?");
		}
		if(incomingRequest.containsKey("BomManufacturer_stageId"))
		{
			String stageId = (String) incomingRequest.get("BomManufacturer_stageId");
			args.add(stageId);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommanufacturer.stageId = ?");
		}
		if(incomingRequest.containsKey("BomManufacturer_partNumber"))
		{
			String partNumber = (String) incomingRequest.get("BomManufacturer_partNumber");
			args.add(partNumber);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommanufacturer.partNumber = ?");
		}
		if(incomingRequest.containsKey("BomManufacturer_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("BomManufacturer_dateEntered");
			args.add(dateEntered);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommanufacturer.dateEntered = ?");
		}
		if(incomingRequest.containsKey("BomManufacturer_owner"))
		{
			String owner = (String) incomingRequest.get("BomManufacturer_owner");
			args.add(owner);
			types.add(Hibernate.STRING);
			queryString.append(" AND bommanufacturer.owner = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}