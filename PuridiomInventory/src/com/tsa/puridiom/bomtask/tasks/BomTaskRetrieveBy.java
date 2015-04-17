package com.tsa.puridiom.bomtask.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class BomTaskRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from BomTask as bomtask where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("BomTask_icTask"))
		{
			String icTask = (String) incomingRequest.get("BomTask_icTask");
			args.add(icTask);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomtask.icTask = ?");
		}
		if(incomingRequest.containsKey("BomTask_taskId"))
		{
			String taskId = (String) incomingRequest.get("BomTask_taskId");
			args.add(taskId);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomtask.taskId = ?");
		}
		if(incomingRequest.containsKey("BomTask_refNo"))
		{
			String refNo = (String) incomingRequest.get("BomTask_refNo");
			args.add(refNo);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomtask.refNo = ?");
		}
		if(incomingRequest.containsKey("BomTask_stageId"))
		{
			String stageId = (String) incomingRequest.get("BomTask_stageId");
			args.add(stageId);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomtask.stageId = ?");
		}
		if(incomingRequest.containsKey("BomTask_icRouting"))
		{
			String icRouting = (String) incomingRequest.get("BomTask_icRouting");
			args.add(icRouting);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomtask.icRouting = ?");
		}
		if(incomingRequest.containsKey("BomTask_taskType"))
		{
			String taskType = (String) incomingRequest.get("BomTask_taskType");
			args.add(taskType);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomtask.taskType = ?");
		}
		if(incomingRequest.containsKey("BomTask_description"))
		{
			String description = (String) incomingRequest.get("BomTask_description");
			args.add(description);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomtask.description = ?");
		}
		if(incomingRequest.containsKey("BomTask_notes"))
		{
			String notes = (String) incomingRequest.get("BomTask_notes");
			args.add(notes);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomtask.notes = ?");
		}
		if(incomingRequest.containsKey("BomTask_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("BomTask_dateEntered");
			args.add(dateEntered);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomtask.dateEntered = ?");
		}
		if(incomingRequest.containsKey("BomTask_owner"))
		{
			String owner = (String) incomingRequest.get("BomTask_owner");
			args.add(owner);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomtask.owner = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}