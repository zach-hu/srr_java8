package com.tsa.puridiom.requisitionline.autorelease.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionLineRetrieveForAutoRelease extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			//String queryString = "from RequisitionLine as RequisitionLine where RequisitionLine.blanketOrder is not null order by RequisitionLine.blanketOrder";
			String queryString = "from RequisitionHeader RequisitionHeader , RequisitionLine RequisitionLine, ShipTo ShipTo, Catalog Catalog " +
					"WHERE left_outer_join(ShipTo.id.icLine) = RequisitionLine.icReqLine AND " +
					"RequisitionHeader.icReqHeader = RequisitionLine.icReqHeader AND " +
					"Catalog.id = RequisitionLine.catalogId AND " +
					"RequisitionLine.status = ? AND " +
					"length(RequisitionLine.blanketOrder) > 0 order by RequisitionLine.blanketOrder, RequisitionLine.requisitionNumber, RequisitionLine.lineNumber";
			List resultList = dbs.query(queryString, new Object[] {DocumentStatus.REQ_APPROVED} , new Type[] { Hibernate.STRING}) ;
			result = resultList;
			System.out.println("lines to Release: " + resultList.size());
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("RequisitionLineRetrieveForAutoRelease failed to retrieve  Req Lines: " + e.getMessage(), e);
		}
		return result;
	}
}