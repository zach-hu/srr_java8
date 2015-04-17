/*
 * Created on Jul 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.invformapp.tasks;

import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvFormAppTrigger extends Task
{
    public Object executeTask(Object object) throws Exception 
	{
	    Object result = null;
		try
        {
		    Map incomingRequest = (Map)object;
		    DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			
			String queryString = "select item.id, sum(loc.minOnHand), " +
					"sum(loc.qtyOnOrder + loc.qtyOnHand + loc.qtyRequested - loc.qtyAlloc) " +
					"from InvItem as item, InvLocation as loc, InvFormData as data " +
					"where item.id = loc.id.item_number and " +
					"item.id = data.id and " +
					"item.status <> '03' and " +
					"loc.autoReplenish = 'Y' and " +
					"data.autoReprint = 'N' and " +
					"loc.itemNumber = '230210' " +
					"group by item.iid " +
					"order by item.id;";
			result = dbs.query(queryString) ;
		    
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
		return result;
	}

}
