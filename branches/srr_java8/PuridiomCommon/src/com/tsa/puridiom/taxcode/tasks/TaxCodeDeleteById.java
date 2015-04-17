package com.tsa.puridiom.taxcode.tasks;

import com.tsa.puridiom.entity.TaxCode;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class TaxCodeDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
		TaxCode taxcode = (TaxCode) incomingRequest.get("taxcode");
		if(taxcode == null)
		{
			taxcode = new TaxCode();
		}
		TaxCodeSetValuesPK setValues = new TaxCodeSetValuesPK();
		setValues.setValues(incomingRequest, taxcode);
		dbs.delete(taxcode);
		this.setStatus(dbs.getStatus());
		return taxcode;
	}

}