package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.PoHeader ;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class RequisitionRoutingRevisionSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		RequisitionHeader	rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
		BigDecimal bdRevisedOrder = rqh.getIcRevisedOrder() ;
		BigDecimal bdIcLogHeader = new BigDecimal(0) ;

		try {
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String queryString = "from PoHeader as PoHeader where PoHeader.icPoHeader = ?";
			List resultList = dbs.query(queryString,
					bdRevisedOrder,
					Hibernate.BIG_DECIMAL) ;

			if (resultList != null && resultList.size() > 0)
			{
				PoHeader pHdr = (PoHeader) resultList.get(0) ;
				bdIcLogHeader = pHdr.getIcReqHeader();
			}
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		incomingRequest.put("ApprovalLog_icHeader", bdIcLogHeader.toString());

		return null ;
	}
}
