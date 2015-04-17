package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RfqHeaderRetrieveIcPoHeader extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqHeaderString = (String) incomingRequest.get("RfqHeader_icRfqHeader");
			if (Utility.isEmpty(icRfqHeaderString))
			{
			    icRfqHeaderString = (String) incomingRequest.get("RfqLine_icRfqHeader");
			}
			if (Utility.isEmpty(icRfqHeaderString))
			{
				throw new Exception("RfqHeader_icRfqHeader or icRfqHeaderString must be set and cannot be empty.  IcPoHeader for the Solicitation could not be retrieved.");
			}
			BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );

			String queryString = "select PoHeader.icPoHeader from PoHeader as PoHeader where PoHeader.icRfqHeader = ? and PoHeader.lastRevision = 'C' and PoHeader.status <> '" + DocumentStatus.PO_AMENDED + "' and PoHeader.status <> '" + DocumentStatus.CANCELLED + "' and PoHeader.poNumber <> 'N/A'";
			List resultList = dbs.query(queryString, icRfqHeader, Hibernate.BIG_DECIMAL) ;
					
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