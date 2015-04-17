package com.tsa.puridiom.capitalprojectcar.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class CapitalProjectCarRetrieveBy extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from CapitalProjectCar as capitalProjectCar where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if (incomingRequest.containsKey("CapitalProjectCar_icProjectCar"))
		{
			String icProjectCar = (String) incomingRequest.get("CapitalProjectCar_icProjectCar");
			args.add(icProjectCar);
			types.add(Hibernate.STRING);
			queryString.append(" AND capitalProjectCar.icProjectCar = ?");
		}
		if (incomingRequest.containsKey("CapitalProjectCar_division"))
		{
			String division = (String) incomingRequest.get("CapitalProjectCar_division");
			args.add(division);
			types.add(Hibernate.STRING);
			queryString.append(" AND capitalProjectCar.division = ?");
		}
		if (incomingRequest.containsKey("CapitalProjectCar_project"))
		{
			String project = (String) incomingRequest.get("CapitalProjectCar_project");
			args.add(project);
			types.add(Hibernate.STRING);
			queryString.append(" AND capitalProjectCar.project = ?");
		}
		if (incomingRequest.containsKey("CapitalProjectCar_car"))
		{
			String car = (String) incomingRequest.get("CapitalProjectCar_car");
			args.add(car);
			types.add(Hibernate.STRING);
			queryString.append(" AND capitalProjectCar.car = ?");
		}
		if (incomingRequest.containsKey("CapitalProjectCar_description"))
		{
			String description = (String) incomingRequest.get("CapitalProjectCar_description");
			args.add(description);
			types.add(Hibernate.STRING);
			queryString.append(" AND capitalProjectCar.description = ?");
		}
		if (incomingRequest.containsKey("CapitalProjectCar_program"))
		{
			String program = (String) incomingRequest.get("CapitalProjectCar_program");
			args.add(program);
			types.add(Hibernate.STRING);
			queryString.append(" AND capitalProjectCar.program = ?");
		}
		if (incomingRequest.containsKey("CapitalProjectCar_amount"))
		{
			String amount = (String) incomingRequest.get("CapitalProjectCar_amount");
			args.add(amount);
			types.add(Hibernate.STRING);
			queryString.append(" AND capitalProjectCar.amount = ?");
		}
		if (incomingRequest.containsKey("CapitalProjectCar_status"))
		{
			String status = (String) incomingRequest.get("CapitalProjectCar_status");
			args.add(status);
			types.add(Hibernate.STRING);
			queryString.append(" AND capitalProjectCar.status = ?");
		}
		if (incomingRequest.containsKey("CapitalProjectCar_year"))
		{
			String year = (String) incomingRequest.get("CapitalProjectCar_year");
			args.add(year);
			types.add(Hibernate.STRING);
			queryString.append(" AND capitalProjectCar.year = ?");
		}

		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;

	}
}