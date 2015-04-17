package com.tsa.puridiom.bomrouting.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class BomRoutingRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from BomRouting as bomrouting where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("BomRouting_icRouting"))
		{
			String icRouting = (String) incomingRequest.get("BomRouting_icRouting");
			args.add(icRouting);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.icRouting = ?");
		}
		if(incomingRequest.containsKey("BomRouting_parentItem"))
		{
			String parentItem = (String) incomingRequest.get("BomRouting_parentItem");
			args.add(parentItem);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.parentItem = ?");
		}
		if(incomingRequest.containsKey("BomRouting_componentItem"))
		{
			String componentItem = (String) incomingRequest.get("BomRouting_componentItem");
			args.add(componentItem);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.componentItem = ?");
		}
		if(incomingRequest.containsKey("BomRouting_stageId"))
		{
			String stageId = (String) incomingRequest.get("BomRouting_stageId");
			args.add(stageId);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.stageId = ?");
		}
		if(incomingRequest.containsKey("BomRouting_stageSeq"))
		{
			String stageSeq = (String) incomingRequest.get("BomRouting_stageSeq");
			args.add(stageSeq);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.stageSeq = ?");
		}
		if(incomingRequest.containsKey("BomRouting_description"))
		{
			String description = (String) incomingRequest.get("BomRouting_description");
			args.add(description);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.description = ?");
		}
		if(incomingRequest.containsKey("BomRouting_workCenterId"))
		{
			String workCenterId = (String) incomingRequest.get("BomRouting_workCenterId");
			args.add(workCenterId);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.workCenterId = ?");
		}
		if(incomingRequest.containsKey("BomRouting_machineId"))
		{
			String machineId = (String) incomingRequest.get("BomRouting_machineId");
			args.add(machineId);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.machineId = ?");
		}
		if(incomingRequest.containsKey("BomRouting_respons"))
		{
			String respons = (String) incomingRequest.get("BomRouting_respons");
			args.add(respons);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.respons = ?");
		}
		if(incomingRequest.containsKey("BomRouting_utilization"))
		{
			String utilization = (String) incomingRequest.get("BomRouting_utilization");
			args.add(utilization);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.utilization = ?");
		}
		if(incomingRequest.containsKey("BomRouting_qtyDays"))
		{
			String qtyDays = (String) incomingRequest.get("BomRouting_qtyDays");
			args.add(qtyDays);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.qtyDays = ?");
		}
		if(incomingRequest.containsKey("BomRouting_setupHours"))
		{
			String setupHours = (String) incomingRequest.get("BomRouting_setupHours");
			args.add(setupHours);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.setupHours = ?");
		}
		if(incomingRequest.containsKey("BomRouting_partsHour"))
		{
			String partsHour = (String) incomingRequest.get("BomRouting_partsHour");
			args.add(partsHour);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.partsHour = ?");
		}
		if(incomingRequest.containsKey("BomRouting_timePart"))
		{
			String timePart = (String) incomingRequest.get("BomRouting_timePart");
			args.add(timePart);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.timePart = ?");
		}
		if(incomingRequest.containsKey("BomRouting_vendorName"))
		{
			String vendorName = (String) incomingRequest.get("BomRouting_vendorName");
			args.add(vendorName);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.vendorName = ?");
		}
		if(incomingRequest.containsKey("BomRouting_leadTime"))
		{
			String leadTime = (String) incomingRequest.get("BomRouting_leadTime");
			args.add(leadTime);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.leadTime = ?");
		}
		if(incomingRequest.containsKey("BomRouting_outside"))
		{
			String outside = (String) incomingRequest.get("BomRouting_outside");
			args.add(outside);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.outside = ?");
		}
		if(incomingRequest.containsKey("BomRouting_backflush"))
		{
			String backflush = (String) incomingRequest.get("BomRouting_backflush");
			args.add(backflush);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.backflush = ?");
		}
		if(incomingRequest.containsKey("BomRouting_persons"))
		{
			String persons = (String) incomingRequest.get("BomRouting_persons");
			args.add(persons);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.persons = ?");
		}
		if(incomingRequest.containsKey("BomRouting_ccost"))
		{
			String ccost = (String) incomingRequest.get("BomRouting_ccost");
			args.add(ccost);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.ccost = ?");
		}
		if(incomingRequest.containsKey("BomRouting_unitOfMeasure"))
		{
			String unitOfMeasure = (String) incomingRequest.get("BomRouting_unitOfMeasure");
			args.add(unitOfMeasure);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.unitOfMeasure = ?");
		}
		if(incomingRequest.containsKey("BomRouting_poNotes"))
		{
			String poNotes = (String) incomingRequest.get("BomRouting_poNotes");
			args.add(poNotes);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.poNotes = ?");
		}
		if(incomingRequest.containsKey("BomRouting_notes"))
		{
			String notes = (String) incomingRequest.get("BomRouting_notes");
			args.add(notes);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.notes = ?");
		}
		if(incomingRequest.containsKey("BomRouting_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("BomRouting_dateEntered");
			args.add(dateEntered);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.dateEntered = ?");
		}
		if(incomingRequest.containsKey("BomRouting_owner"))
		{
			String owner = (String) incomingRequest.get("BomRouting_owner");
			args.add(owner);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomrouting.owner = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()]));
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}