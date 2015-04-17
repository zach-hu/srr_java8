/*
 * Created on Jan 25, 2009
 */
package com.tsa.puridiom.labels.tasks;

import java.math.BigDecimal;
import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class LabelsRetrieveByIc extends Task
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
	        if(incomingRequest.containsKey("Labels_icLabel"))
	        {
	        	queryString.append(" and labels.icLabel = ? ");
	        	String sIcLabel = (String)incomingRequest.get("Labels_icLabel");

	        	argumentsList.add(new BigDecimal(sIcLabel));
	        	typesList.add(Hibernate.BIG_DECIMAL);
	        }
	        Type hiberTypes[] = (Type[])typesList.toArray(new Type[typesList.size()]);
	        List resultList = dbs.query(queryString.toString(), argumentsList.toArray() , hiberTypes) ;
	        if(resultList != null && resultList.size() == 1)
	        {
	        	result = resultList.get(0);
	        }

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			e.printStackTrace();
		}

		return result ;
	}
}
