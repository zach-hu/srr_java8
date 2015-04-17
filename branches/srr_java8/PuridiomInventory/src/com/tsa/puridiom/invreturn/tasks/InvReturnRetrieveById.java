package com.tsa.puridiom.invreturn.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class InvReturnRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String requisitionNumber = (String ) incomingRequest.get("InvReturn_requisitionNumber");
			String lineNoString = (String) incomingRequest.get("InvReturn_lineNo");
			BigDecimal lineNo = new BigDecimal ( lineNoString );
			String itemNumber = (String ) incomingRequest.get("InvReturn_itemNumber");

			String queryString = "from InvReturn as InvReturn where InvReturn.id.requisitionNumber = ? and InvReturn.id.lineNo = ? and InvReturn.id.itemNumber = ? ";
			List resultList = dbs.query(queryString, new Object[] {requisitionNumber, lineNo, itemNumber, } , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
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