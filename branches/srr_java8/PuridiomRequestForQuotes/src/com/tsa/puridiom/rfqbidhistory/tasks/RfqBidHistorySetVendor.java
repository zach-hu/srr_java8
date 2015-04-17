package com.tsa.puridiom.rfqbidhistory.tasks;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

public class RfqBidHistorySetVendor extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List ret = new ArrayList();
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqHeader = (String) incomingRequest.get("RfqVendor_icRfqHeader");
			String vendorId = (String) incomingRequest.get("RfqBidHistory_vendorId");
			StringBuffer queryString = new StringBuffer("from RfqVendor as rfqvendor where rfqvendor.id.icRfqHeader = '" + icRfqHeader + "' and rfqvendor.id.vendorId = '" + vendorId + "'");
			List result = dbs.query(queryString.toString()) ;
            if (result.size() > 0) {
                ret.add(result.get(0));
            }
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}

}