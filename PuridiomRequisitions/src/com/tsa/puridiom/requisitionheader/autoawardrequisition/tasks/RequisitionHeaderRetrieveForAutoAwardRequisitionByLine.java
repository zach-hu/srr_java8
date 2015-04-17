package com.tsa.puridiom.requisitionheader.autoawardrequisition.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionHeaderRetrieveForAutoAwardRequisitionByLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String organizationId = (String)incomingRequest.get("organizationId");
			String additionalFilter = PropertiesManager.getInstance(organizationId).getProperty("AUTOAWARD", "REQBYLINEFILTER", "");

			String queryString = "from RequisitionHeader RequisitionHeader " +
			"WHERE RequisitionHeader.status = ? AND RequisitionHeader.requisitionType = 'E' AND " +
			"RequisitionHeader.requisitionNumber NOT IN (SELECT PoHeader.requisitionNumber FROM PoHeader PoHeader " +
			"WHERE PoHeader.requisitionNumber = RequisitionHeader.requisitionNumber AND PoHeader.poNumber != 'N/A' AND PoHeader.status <> '9020')" + additionalFilter;

			List resultList = dbs.query(queryString, new Object[] {DocumentStatus.REQ_APPROVED} , new Type[] { Hibernate.STRING}) ;
			result = resultList;
			System.out.println("requisitions to AutoAward: " + resultList.size());
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			Log.error(this, "RequisitionHeaderRetrieveForAutoAwardRequisition failed: " + e.getMessage());
			this.setStatus(Status.FAILED);
			throw new TsaException("RequisitionLineRetrieveForAutoRelease failed to retrieve Requisitions: " + e.getMessage(), e);
		}
		return result;
	}
}