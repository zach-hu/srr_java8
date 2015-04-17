/*
 * Created on Oct 25, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.invreturn.tasks;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
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
public class UpdateReqLineStatus extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            InvReturn invReturn = (InvReturn)incomingRequest.get("invReturn");
            String rejectReturn = HiltonUtility.ckNull((String ) incomingRequest.get("InvReturn_rejectReturn"));
            String status = DocumentStatus.INV_RETURNED;
            if(rejectReturn.equalsIgnoreCase("Y"))
            {
            	status = DocumentStatus.REQ_REJECTED;
            }
            BigDecimal icReqLine = invReturn.getIcReqLine();
            String updateSql = "UPDATE requisition_line SET status = ? WHERE ic_req_line = ?";
            Object [] args = {status, icReqLine.toString()};
            Integer [] types = {Types.VARCHAR, Types.VARCHAR};
            
            DBSession dbs = (DBSession)incomingRequest.get("dbsession");
            dbs.sqlUpdate(updateSql, args, types);
            
            this.setStatus(dbs.getStatus());
            
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return null;
    }
}
