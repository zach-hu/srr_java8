package com.tsa.puridiom.poheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class PoHeaderRetrieveContractsByVendorId extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String vendorId = HiltonUtility.ckNull((String) incomingRequest.get("PoHeader_vendorId"));

			String queryString = "from PoHeader as PoHeader where (PoHeader.poType = 'CT' or PoHeader.poType = 'BO' or PoHeader.poType = 'DO' or PoHeader.poType = 'SB') " +
					"AND PoHeader.status >= '" + DocumentStatus.PO_AWARDED + "' AND PoHeader.status < '" + DocumentStatus.CANCELLED + "' AND PoHeader.vendorId = ? " +
					"AND PoHeader.lastRevision = 'C' ";
			result = dbs.query(queryString, new Object[] { vendorId } , new Type[] { Hibernate.STRING }) ;

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