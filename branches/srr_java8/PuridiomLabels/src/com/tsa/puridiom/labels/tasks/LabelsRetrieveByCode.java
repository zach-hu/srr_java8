/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.labels.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class LabelsRetrieveByCode extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{

	        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
	        StringBuffer queryString = new StringBuffer("from Labels as labels where 1 = 1 ");
	        List argumentsList = new ArrayList();
	        List typesList = new ArrayList();
	        if(incomingRequest.containsKey("Labels_labelCode"))
	        {
	        	queryString.append(" and labels.labelCode = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_labelCode"));
	        	typesList.add(Hibernate.STRING);
	        }
	        Type hiberTypes[] = (Type[])typesList.toArray(new Type[typesList.size()]);
	        List resultList = dbs.query(queryString.toString(), argumentsList.toArray() , hiberTypes) ;

	        result = resultList;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			e.printStackTrace();
		}

		return result ;
	}
}
