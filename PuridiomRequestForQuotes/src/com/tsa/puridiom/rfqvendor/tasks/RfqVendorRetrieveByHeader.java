package com.tsa.puridiom.rfqvendor.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class RfqVendorRetrieveByHeader extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icRfqHeader = (String) incomingRequest.get("RfqVendor_icRfqHeader");

		String queryVendors="";
		if (incomingRequest.containsKey("selectedSuppliers"))
		{
			queryVendors += " and ( 0=1";
			String selectedSuppliers[] = ((String)incomingRequest.get("selectedSuppliers")).split(",");

			for (int i=0; i< selectedSuppliers.length ; i++)
			{
				queryVendors += " or rfqvendor.id.vendorId='"+selectedSuppliers[i]+"'";
			}
			queryVendors += ")";
		}

		if (icRfqHeader == null)
		{
			icRfqHeader = (String) incomingRequest.get("RfqHeader_icRfqHeader");
		}
		StringBuffer queryString = new StringBuffer("from RfqVendor as rfqvendor where rfqvendor.id.icRfqHeader = '" + icRfqHeader + "'");

		queryString.append(queryVendors);
		queryString.append(" order by rfqvendor.id.vendorId ASC");

		List result = dbs.query(queryString.toString());
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}