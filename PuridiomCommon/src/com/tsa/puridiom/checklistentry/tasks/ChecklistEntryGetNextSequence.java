package com.tsa.puridiom.checklistentry.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ChecklistEntryGetNextSequence extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String referenceType = (String) incomingRequest.get("ChecklistEntry_referenceType");
			BigDecimal sequence = new BigDecimal("0");

			String queryString = "select max(ChecklistEntry.sequence) from ChecklistEntry as ChecklistEntry where ChecklistEntry.referenceType = ? ";
			List resultList = dbs.query(queryString, new Object[] {referenceType} , new Type[] { Hibernate.STRING}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				if (resultList.get(0) != null)
				{
					sequence = (BigDecimal) resultList.get(0);
				}
			}
			result = String.valueOf(sequence.add(new BigDecimal(1)));
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}