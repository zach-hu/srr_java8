package com.tsa.puridiom.poheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class PoNumberRetrieveContractsByVendorIdFromVinimaya extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = null;
		String poNumber = "";

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			//String vendorId = HiltonUtility.ckNull((String) incomingRequest.get("PoHeader_groupVendorId"));
			String blanketOrder = HiltonUtility.ckNull((String)incomingRequest.get("RequisitionLine_blanketOrder"));

			String queryString = "Select PoHeader.poNumber from PoHeader as PoHeader " +
					"where PoHeader.poType in ('BO','CT') AND " +
					"PoHeader.contractNo = ? AND PoHeader.lastRevision = 'C'";
			result = dbs.query(queryString, new Object[] { blanketOrder } , new Type[] { Hibernate.STRING }) ;
			if (result != null && result.size() > 0)
			{
				poNumber = (String)result.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return poNumber;
	}

}