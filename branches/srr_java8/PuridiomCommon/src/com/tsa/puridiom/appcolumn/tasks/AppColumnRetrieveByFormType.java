package com.tsa.puridiom.appcolumn.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class AppColumnRetrieveByFormType extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String formType = (String)incomingRequest.get("AppColumn_formType");
		
      	String queryString = "from AppColumn as a where a.id.formType = ?";
				
		List result = dbs.query(queryString,	
				new Object[] {formType },
					new Type[] {Hibernate.STRING}) ;
					
		this.setStatus(dbs.getStatus()) ;
					
		return result ;

	}
}