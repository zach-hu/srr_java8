package com.tsa.puridiom.requisitionheader.autoawardrequisition.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * 
 * @author Alexander
 *
 */
public class RequisitionHeaderRetrieveForAutoAwardAllRequisition extends Task
{

    @SuppressWarnings("unchecked")
	public Object executeTask(Object object) throws Exception
    {
       	Map incomingRequest = (Map)object;
    	Object result = null;

    	try
    	{
    		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
    		String queryString = "from RequisitionHeader as rh where rh.status = '1035' and rh.rqSubType = 'PA'";
    		result = dbs.query(queryString);

    		this.setStatus(Status.SUCCEEDED) ;
    	}
    	catch (Exception e)
    	{
    		this.setStatus(Status.FAILED);
    		Log.error(this, "Error Message: " + e.getMessage());
    		throw e;
    	}
    	return result;
    }

}
