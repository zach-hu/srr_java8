package com.tsa.puridiom.requisitionline.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class RequisitionLineQtyOnBackorder extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String itemLocation = (String)incomingRequest.get("RequisitionLine_itemLocation");
			String itemNumber = (String)incomingRequest.get("RequisitionLine_itemNumber");

			String queryString = "SELECT Sum(requisitionLine.backordered) " +
					"FROM RequisitionLine as requisitionLine WHERE  " +
					"( requisitionLine.itemLocation = ? ) AND " +
					"( requisitionLine.itemNumber = ?) AND " +
					"(requisitionLine.status in (?, ?))";
			List resultList = dbs.query(queryString, new Object[] {itemLocation, itemNumber, DocumentStatus.INV_PARTIAL, DocumentStatus.INV_BACKORDERED} , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
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