package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoHeaderVerifyCreationChangeRequest extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			BigDecimal icPoHeader = poHeader.getIcPoHeader();
			String queryString = "from RequisitionHeader  RH where RH.icRevisedOrder = ? and RH.status < '3030' and RH.requisitionType = 'C' and RH.requisitionNumber <> 'N/A')";
			List resultList = dbs.query(queryString, new Object[] {icPoHeader } , new Type[] { Hibernate.BIG_DECIMAL}) ;
			if (resultList != null && resultList.size() > 0)
			{
				incomingRequest.put("allowToCreateCheckRequest", "false");
			}else{
				incomingRequest.put("allowToCreateCheckRequest", "true");
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