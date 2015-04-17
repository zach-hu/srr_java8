/*
 * Created on Oct 28, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.InvReturn;
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
public class RequisitionHeaderCancelCheck extends Task
{
    
    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        Object ret = "";
        try
        {
            Map incomingRequest = (Map)object;
            String icReqHeader = (String)incomingRequest.get("RequisitionHeader_icReqHeader");
            String selectSql = "SELECT MIN( reqLine.status ) " +
			"FROM RequisitionLine as reqLine " +  
			"WHERE reqLine.icReqHeader = " + icReqHeader ;
            
            DBSession dbs = (DBSession)incomingRequest.get("dbsession");
            List minstat = dbs.query(selectSql);
            
            this.setStatus(dbs.getStatus());
            if(minstat != null)
            {
                String minLineStat = (String)minstat.get(0);
                if(minLineStat.equals(DocumentStatus.CANCELLED))
                {
                    ret = "cancelHeader";
                }
            }
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
    }
}
