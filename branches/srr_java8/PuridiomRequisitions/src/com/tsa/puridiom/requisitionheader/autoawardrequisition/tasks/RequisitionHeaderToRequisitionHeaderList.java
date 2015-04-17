package com.tsa.puridiom.requisitionheader.autoawardrequisition.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionHeaderToRequisitionHeaderList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List resultList = null;
			RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
			if (rqh != null) {
				resultList = new ArrayList();
				resultList.add(rqh);
			}
			result = resultList;
		}
		catch (Exception e)
		{
			Log.error(this, "RequisitionHeaderToRequisitionHeaderList failed: " + e.getMessage());
			this.setStatus(Status.FAILED);
			throw new TsaException("RequisitionHeaderToRequisitionHeaderList failed to retrieve Requisition: " + e.getMessage(), e);
		}
		return result;
	}
}