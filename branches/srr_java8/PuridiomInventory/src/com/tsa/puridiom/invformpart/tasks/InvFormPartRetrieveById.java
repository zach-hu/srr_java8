package com.tsa.puridiom.invformpart.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class InvFormPartRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String itemNumber = (String ) incomingRequest.get("InvFormPart_itemNumber");
			String formPartString = (String) incomingRequest.get("InvFormPart_formPart");
			BigDecimal formPart = new BigDecimal ( formPartString );

			String queryString = "from InvFormPart as InvFormPart where InvFormPart.id.itemNumber = ? and InvFormPart.id.formPart = ? ";
			List resultList = dbs.query(queryString, new Object[] {itemNumber, formPart, } , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;
					
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