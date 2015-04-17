package com.tsa.puridiom.requisitionheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class RequisitionHeaderRetrieveByNumber extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String requisitionNumber = (String) incomingRequest.get("RequisitionHeader_requisitionNumber");
			if (Utility.isEmpty(requisitionNumber))
			{
				throw new Exception("RequisitionHeader_requisitionNumber cannot be empty.  Requisition Number could not be retrieved.");
			}

			String queryString = "from RequisitionHeader as RequisitionHeader where RequisitionHeader.requisitionNumber = ? ";
			List resultList = dbs.query(queryString, new Object[] {requisitionNumber } , new Type[] { Hibernate.STRING}) ;

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