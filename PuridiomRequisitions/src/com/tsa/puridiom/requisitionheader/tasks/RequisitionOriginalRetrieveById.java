package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RequisitionOriginalRetrieveById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			BigDecimal IcPoHeader = rqh.getIcRevisedOrder();

			if(IcPoHeader != null)
			{
				String queryString = "from PoHeader as PoHeader where PoHeader.icPoHeader= ? ";
				List resultList = dbs.query(queryString, new Object[] {IcPoHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

				if (resultList != null && resultList.size() > 0)
				{
					PoHeader ph = (PoHeader) resultList.get(0);
					BigDecimal IcReqHeader = ph.getIcReqHeader();
					queryString = "from RequisitionHeader as RequisitionHeader where RequisitionHeader.icReqHeader= ? ";
					resultList = dbs.query(queryString, new Object[] {IcReqHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

					if (resultList != null && resultList.size() > 0)
					{
						result = resultList.get(0);
					}
				}
			}

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}