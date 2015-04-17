package com.tsa.puridiom.requisitionheader.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoHeaderRetrieveFromRequisitionById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			if (poHeader != null) {
				result = poHeader;
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			BigDecimal icPoHeader = new BigDecimal(0);
			if(requisitionHeader == null)
			{
				requisitionHeader = new RequisitionHeader();
			}
			else
			{
				icPoHeader = HiltonUtility.ckNull(requisitionHeader.getIcRevisedOrder());
			}
			
			String queryString = "from PoHeader as PoHeader where PoHeader.id = ? ";
			List resultList = dbs.query(queryString, new Object[] {icPoHeader } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
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