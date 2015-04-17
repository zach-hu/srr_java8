package com.tsa.puridiom.vendoraffiliate.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class VendorAffiliateDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		System.out.println("Vendor AffiliateDeleteById start");
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		VendorAffiliate vendorAffiliate = (VendorAffiliate)incomingRequest.get("vendorAffiliate");
		if(vendorAffiliate == null)
		{
			vendorAffiliate = new VendorAffiliate();
		}
		VendorAffiliateSetValuesPK setValues = new VendorAffiliateSetValuesPK();
		setValues.setValues(incomingRequest, vendorAffiliate);
		dbs.delete(vendorAffiliate);
		this.setStatus(dbs.getStatus()) ;
		return vendorAffiliate ;
	}
}