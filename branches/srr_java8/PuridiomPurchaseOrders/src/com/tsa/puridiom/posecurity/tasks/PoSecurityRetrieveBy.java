package com.tsa.puridiom.posecurity.tasks;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

public class PoSecurityRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String userDateFormat = (String) incomingRequest.get("userDateFormat");
		
		StringBuffer queryString = new StringBuffer("from PoSecurity as posecurity where 1=1 ");
		if(incomingRequest.containsKey("PoSecurity_poNumber"))
		{			
			String poNumber = (String) incomingRequest.get("PoSecurity_poNumber");
			queryString.append(" AND posecurity.id.poNumber = '" + poNumber + "'");
		}
		if(incomingRequest.containsKey("PoSecurity_accessType"))
		{			
			if (incomingRequest.get("PoSecurity_accessType") instanceof String) 
			{
				String accessType = (String) incomingRequest.get("PoSecurity_accessType");
				queryString.append(" AND posecurity.id.accessType = '" + accessType + "'");
			}
		}
		if(incomingRequest.containsKey("PoSecurity_accessId"))
		{	
			if (incomingRequest.get("PoSecurity_accessId") instanceof String) 
			{
				String accessId = (String) incomingRequest.get("PoSecurity_accessId");
				queryString.append(" AND posecurity.id.accessId = '" + accessId + "'");
			}
		}
		if(incomingRequest.containsKey("PoSecurity_owner"))
		{			
			if (incomingRequest.get("PoSecurity_owner") instanceof String) 
			{
				String owner = (String) incomingRequest.get("PoSecurity_owner");
				queryString.append(" AND posecurity.owner = '" + owner + "'");
			}
		}
		List<Date> dates = new ArrayList<Date>();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("PoSecurity_dateEntered"))
		{			
			if (incomingRequest.get("PoSecurity_dateEntered") instanceof String) 
			{
				String strDateEntered = (String) incomingRequest.get("PoSecurity_dateEntered");
				Date dateEntered = Dates.getSqlDate(strDateEntered, userDateFormat);
				dates.add(dateEntered);
				types.add(Hibernate.DATE);
				queryString.append(" AND posecurity.dateEntered = ? ");
			}
		}
		if(incomingRequest.containsKey("PoSecurity_dateChanged"))
		{			
			if (incomingRequest.get("PoSecurity_dateChanged") instanceof String) 
			{
				String strDateChanged = (String) incomingRequest.get("PoSecurity_dateChanged");
				Date dateChanged = Dates.getSqlDate(strDateChanged, userDateFormat);
				dates.add(dateChanged);
				types.add(Hibernate.DATE);
				queryString.append(" AND posecurity.dateChanged = ? ");
			}
		}
		if(incomingRequest.containsKey("PoSecurity_lastChangedBy"))
		{			
			if (incomingRequest.get("PoSecurity_lastChangedBy") instanceof String) 
			{
				String lastChangedBy = (String) incomingRequest.get("PoSecurity_lastChangedBy");
				queryString.append(" AND posecurity.lastChangedBy = '" + lastChangedBy + "'");
			}
		}
		if(incomingRequest.containsKey("PoSecurity_timeZone"))
		{			
			if (incomingRequest.get("PoSecurity_timeZone") instanceof String) 
			{
				String timeZone = (String) incomingRequest.get("PoSecurity_timeZone");
				queryString.append(" AND posecurity.timeZone = '" + timeZone + "'");
			}
		}
		List result;
		if (dates.size() > 0) {
			result = dbs.query(queryString.toString(), dates.toArray(), types.toArray(new Type[types.size()])) ;
		} else {
			result = dbs.query(queryString.toString()) ;
		}
		
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
