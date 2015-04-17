package com.tsa.puridiom.vendordocument.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.util.List;
import java.util.Map;

public class VendorDocumentRetrieveByVendor extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from VendorDocument as vendordocument where 1=1 ");
		String icRfqHeader = (String) incomingRequest.get("VendorDocument_icRfqHeader");
		String vendorId = (String) incomingRequest.get("VendorDocument_vendorId");
		String docType = HiltonUtility.ckNull((String) incomingRequest.get("VendorDocument_docType"));

		if (Utility.isEmpty(icRfqHeader)) {
		    throw new Exception("Cannot retrieve VendorDocumentByVendor - VendorDocument_icRfqHeader was empty.");
		}
		if (Utility.isEmpty(vendorId)) {
		    throw new Exception("Cannot retrieve VendorDocumentByVendor - VendorDocument_vendorId was empty.");
		}
		
		queryString.append(" AND vendordocument.id.icRfqHeader = '" + icRfqHeader + "'");
		queryString.append(" AND vendordocument.id.vendorId = '" + vendorId + "'");
		if (!HiltonUtility.isEmpty(docType)) {
			queryString.append(" AND vendordocument.docType = '" + docType + "'");
		} else {
			queryString.append(" AND (vendordocument.docType IS NULL OR vendordocument.docType = '" + docType + "')");
		}

		List result = dbs.query(queryString.toString());
		this.setStatus(dbs.getStatus());

		return result;
	}
}