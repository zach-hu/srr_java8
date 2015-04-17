package com.tsa.puridiom.poheader.tasks;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.tsagate.foundation.utility.Utility;

public class PoHeaderRetrieveRevById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
			BigDecimal icRevisedOrder = rqh.getIcRevisedOrder();
			BigDecimal poTotal = new BigDecimal(0);
			Object[] totalaux = null;

	        if (icRevisedOrder != null && icRevisedOrder.compareTo(new BigDecimal(0)) != 0)
	        {
	        	String queryString = "select poHeader.icPoHeader, poHeader.releaseLimit, poHeader.total from PoHeader as poHeader where poHeader.icPoHeader = ? ";
				List resultList = dbs.query(queryString, new Object[] {icRevisedOrder } , new Type[] { Hibernate.BIG_DECIMAL}) ;

				if (resultList != null && resultList.size() > 0)
				{
					result = resultList.get(0);
					totalaux = (Object[]) result;
					poTotal = (BigDecimal) totalaux[2];
				}
				this.setStatus(dbs.getStatus()) ;
	        }
	        incomingRequest.put("poTotal", poTotal);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}