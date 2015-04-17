package com.tsa.puridiom.poheader.autoawardrevision.tasks;

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
import com.tsa.puridiom.property.PropertiesManager;

public class PoHeaderRetrieveByChangeRequest extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String oid = (String)incomingRequest.get("organizationId");
			boolean fdcsEnabled = PropertiesManager.getInstance(oid).getProperty("FDCS", "Enabled", "N").equalsIgnoreCase("Y") ;
			String	queryString = null ;
			if (fdcsEnabled) {
				queryString =
					"from PoHeader PoHeader where PoHeader.status = ? and PoHeader.revisionNumber > 0 and PoHeader.udf14Code = 'DBS' order by PoHeader.poNumber, PoHeader.releaseNumber, PoHeader.revisionNumber" ;
			} else {
				queryString =
					"from PoHeader PoHeader where PoHeader.status = ? and PoHeader.requisitionNumber in " +
					"(select RequisitionHeader.requisitionNumber from RequisitionHeader RequisitionHeader " +
					"where RequisitionHeader.requisitionType = 'C' )";
			}

			List resultList = dbs.query(queryString, new Object[] {DocumentStatus.PO_INPROGRESS}, new Type[] {Hibernate.STRING});
			result = resultList;
			System.out.println("POs Revision to AutoAward: " + resultList.size());
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			Log.error(this, "PoHeaderRetrieveByChangeRequest failed: " + e.getMessage());
			this.setStatus(Status.FAILED);
			throw new TsaException("PoHeaderRetrieveByChangeRequest failed to retrieve POs: " + e.getMessage(), e);
		}
		return result;
	}
}