/**
 * Created on Apr 12, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.puridiom.hilton.pp.tasks.PoCancelCheck.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class PoHeaderUpdateStatus extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

        try
        {
        	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        	String oldStatus = (String) incomingRequest.get("PoHeader_status");
            String oid = (String) incomingRequest.get("organizationId");

            PoHeader icPoHeader = (PoHeader)incomingRequest.get("poHeader") ;
            icPoHeader.setStatus(oldStatus);
            dbs.update(icPoHeader);

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
        	this.setStatus(Status.FAILED);
			Log.error(this, "Error Message: " + e.getMessage());
			throw e;
        }
        return result ;
	}

}