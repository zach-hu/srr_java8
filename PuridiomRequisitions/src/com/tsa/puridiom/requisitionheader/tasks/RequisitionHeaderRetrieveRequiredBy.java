package com.tsa.puridiom.requisitionheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RequisitionHeaderRetrieveRequiredBy extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icReqHeaderString = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
			if (Utility.isEmpty(icReqHeaderString))
			{
				throw new Exception("RequisitionHeader_icReqHeader cannot be empty.  Requisition header could not be retrieved.");
			}
			BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );

			String queryString = "select RequisitionHeader.requiredDate from RequisitionHeader as RequisitionHeader where RequisitionHeader.icReqHeader = ? ";
			List resultList = dbs.query(queryString, icReqHeader, Hibernate.BIG_DECIMAL) ;
					
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