package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.*;

import java.util.List;
import java.util.Map;

public class RequisitionHeaderSetSourcingStatus extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			List reqLineList = (List) incomingRequest.get("requisitionLineList") ;
			if (rqh != null)
			{
				boolean sourced = false;
				boolean unsourced = false ;
				for (int i = 0; i < reqLineList.size(); i++) {
					RequisitionLine rql = (RequisitionLine) reqLineList.get(i) ;
					if (rql.getStatus().equals(DocumentStatus.REQ_PLANNING_SOURCED)) sourced = true ;
					if (rql.getStatus().equals(DocumentStatus.REQ_PLANNING_APPROVED)) unsourced = true ;
				}
				if (sourced && unsourced) {
					rqh.setStatus(DocumentStatus.REQ_PLANNING_SOURCING) ;

				} else if ( sourced && ! unsourced) {
					rqh.setStatus(DocumentStatus.REQ_PLANNING_SOURCED) ;
				} else 	if (reqLineList.size()  == 0) {
					rqh.setStatus(DocumentStatus.REQ_PLANNING_SOURCED) ;
				}
				incomingRequest.put("requisitionHeader", rqh);
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}

}