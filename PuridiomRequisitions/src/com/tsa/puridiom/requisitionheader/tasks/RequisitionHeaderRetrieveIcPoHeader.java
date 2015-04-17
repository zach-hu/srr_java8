package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RequisitionHeaderRetrieveIcPoHeader extends Task{
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
			    icReqHeaderString = (String) incomingRequest.get("RequisitionLine_icReqHeader");
			}
			if (Utility.isEmpty(icReqHeaderString))
			{
				throw new Exception("RequisitionHeader_icReqHeader or RequisitionLine_icReqHeader must be set and cannot be empty.  IcPoHeader for the Requisition could not be retrieved.");
			}
			BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );

			String queryString = "select PoHeader.icPoHeader from PoHeader as PoHeader where PoHeader.icReqHeader = ? and PoHeader.lastRevision = 'C' and PoHeader.status <> '" + DocumentStatus.PO_AMENDED + "' and PoHeader.status <> '" + DocumentStatus.CANCELLED + "' and PoHeader.poNumber <> 'N/A'";
			List resultList = dbs.query(queryString, icReqHeader, Hibernate.BIG_DECIMAL) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = String.valueOf(resultList.get(0));
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