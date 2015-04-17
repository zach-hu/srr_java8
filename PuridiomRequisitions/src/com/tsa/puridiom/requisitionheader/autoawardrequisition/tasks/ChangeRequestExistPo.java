package com.tsa.puridiom.requisitionheader.autoawardrequisition.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class ChangeRequestExistPo extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String queryString = "from RequisitionHeader rh WHERE rh.requisitionType = 'C' AND rh.status = ? AND " +
			"rh.requisitionNumber IN (SELECT ph.requisitionNumber FROM PoHeader ph WHERE ph.requisitionNumber = rh.requisitionNumber " +
			"OR ph.icReqHeader = rh.icReqHeader )";

			List resultList = dbs.query(queryString, new Object[] {DocumentStatus.REQ_APPROVED} , new Type[] { Hibernate.STRING}) ;
			result = resultList;
			System.out.println("requisitions to AutoAward: " + resultList.size());
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			Log.error(this, "ReqChangeRequestRetrieveAlone failed: " + e.getMessage());
			this.setStatus(Status.FAILED);
			throw new TsaException("RequisitionLineRetrieveForAutoRelease failed to retrieve Requisitions: " + e.getMessage(), e);
		}
		return result;
	}
}