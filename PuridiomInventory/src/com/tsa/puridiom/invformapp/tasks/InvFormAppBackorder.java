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
public class InvFormAppBackorder extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{
	    Object result = null;
		try
        {
		    Map incomingRequest = (Map)object;
		    DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			
			String queryString = "SELECT  reqLine.itemNumber , sum( reqLine.backordered )" +
					"FROM RequisitionLine as reqLine " +
					"WHERE ( reqLine.status = '5005' and " +
					"reqLine.backordered > 0 ) " +
					"group by reqLine.itemNumber";
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
