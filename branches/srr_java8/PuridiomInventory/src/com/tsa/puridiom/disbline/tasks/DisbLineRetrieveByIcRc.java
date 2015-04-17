package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class DisbLineRetrieveByIcRc extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRcString = (String) incomingRequest.get("DisbLine_icRc");
			BigDecimal icRc = new BigDecimal ( icRcString );

			String queryString = "from DisbLine as disbLine where disbLine.icRc = ?";
			result = dbs.query(queryString, icRc , Hibernate.BIG_DECIMAL) ;
			
			if(result == null){
				result = new ArrayList();
			}
			
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