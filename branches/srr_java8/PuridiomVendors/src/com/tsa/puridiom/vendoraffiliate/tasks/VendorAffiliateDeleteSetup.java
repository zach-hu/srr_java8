package com.tsa.puridiom.vendoraffiliate.tasks;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class VendorAffiliateDeleteSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

		String vendorId = (String)incomingRequest.get("VendorAffiliate_vendorId");
		String affiliateId = (String)incomingRequest.get("VendorAffiliate_affiliateId");

		String queryString = "from VendorAffiliate as vendoraffiliate where vendoraffiliate.id.vendorId = ?" +
				" AND vendoraffiliate.id.affiliateId = ?";

		List resultList = dbs.query(queryString, new String[]{vendorId, affiliateId},
				new Type[] { Hibernate.STRING, Hibernate.STRING});

		Object result = null;
		if (resultList != null && resultList.size() > 0) {
			result = resultList.get(0);
		}

		incomingRequest.put("vendorAffiliate", result);
		this.setStatus(dbs.getStatus()) ;
		return result;
	}
}