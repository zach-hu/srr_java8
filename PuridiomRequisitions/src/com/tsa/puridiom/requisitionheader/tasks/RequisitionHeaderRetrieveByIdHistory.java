package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RequisitionHeaderRetrieveByIdHistory extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("oldRequisitionHeader");
			if(reqHeader == null)
			{
				String icReqHeaderString = (String) incomingRequest.get("old_RequisitionHeader_icReqHeader");
				if (Utility.isEmpty(icReqHeaderString))
				{
					throw new Exception("RequisitionHeader_icReqHeader cannot be empty.  Requisition header could not be retrieved.");
				}
				BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );
	
				String queryString = "from RequisitionHeader as header where header.icReqHeader = ? ";
				List resultList = dbs.query(queryString, new Object[] {icReqHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ;
						
				if (resultList != null && resultList.size() > 0)
				{
					result = resultList.get(0);
				}
				this.setStatus(dbs.getStatus()) ;
			}
			else
			{
			    result = reqHeader;
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}