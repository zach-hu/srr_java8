package com.tsa.puridiom.report.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseObject;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class ReportGetHibernateDataSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        StringBuffer query = new StringBuffer();
        Object result = null;
        try
        {
            Map incomingRequest = (Map) object;
            DBSession dbs = (DBSession) incomingRequest.get("dbsession");
            String o = (String) incomingRequest.get("organizationId");
            String userId = (String) incomingRequest.get("userId");


            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            Log.error(this.getName(), query.toString());
            this.setStatus(Status.FAILED);
            throw e;
            //throw new TsaException(this.getName(), e);
        }
        return result;
    }
}