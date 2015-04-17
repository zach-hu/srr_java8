package com.tsa.puridiom.vendordocument.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import java.util.List;
import java.util.Map;

public class VendorDocumentRetrieveByHeader extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from VendorDocument as vendordocument where 1=1 ");
		String icRfqHeader = (String) incomingRequest.get("VendorDocument_icRfqHeader");
		
		if (Utility.isEmpty(icRfqHeader)) {
		    throw new Exception("Cannot retrieve VendorDocumentByHeader - VendorDocument_icRfqHeader was empty.");
		}

		queryString.append(" AND vendordocument.id.icRfqHeader = '" + icRfqHeader + "'");
		
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}