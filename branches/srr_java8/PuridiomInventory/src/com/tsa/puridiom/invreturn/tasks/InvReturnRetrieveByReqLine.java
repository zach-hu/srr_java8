package com.tsa.puridiom.invreturn.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvReturnRetrieveByReqLine extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String sIcReqLine = (String ) incomingRequest.get("InvReturn_icReqLine");
			if(sIcReqLine == null)
			{
			    this.setStatus(Status.FAILED);
			    throw new TsaException(this.getName() + ", Requisition Line could not be found!");
			}
			
			BigDecimal icReqLine = new BigDecimal(sIcReqLine);
			
			String queryString = "from InvReturn as InvReturn where InvReturn.icReqLine = ? ";
			List resultList = dbs.query(queryString, new Object[] {icReqLine } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
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