/*
 * Created on Oct 25, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.invreturn.tasks;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;
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
public class UpdateDsbStatus extends Task
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
            String selectSql = "SELECT MIN( dsbLine.status ) " +
            							"FROM DisbLine as dsbLine " +  
            							"WHERE dsbLine.icDsbHeader = " + invReturn.getIcDsbHeader().toString() ;
            
            DBSession dbs = (DBSession)incomingRequest.get("dbsession");
            List result = dbs.query(selectSql);
            
            if(result == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("Requisition Line status could not be found!");
            }
            else
            {
                String dsbStatus = DocumentStatus.INV_RETURNING;
                String minLineStatus = (String)result.get(0);
                if(rejectReturn.equalsIgnoreCase("Y"))
                {
                	dsbStatus = DocumentStatus.REQ_REJECTED;
                }
                else
                {
	                if(minLineStatus.equals(DocumentStatus.INV_RETURNING))
	                {
	                	dsbStatus = DocumentStatus.INV_RETURNING;
	                }
	                else if(minLineStatus.equals(DocumentStatus.INV_RETURNED))
	                {
	                	dsbStatus = DocumentStatus.INV_RETURNED;
	                }
                }                
                
                String updateSelect = "UPDATE disb_header SET status = ? WHERE ic_dsb_header = ?";
                Object [] args = {dsbStatus, invReturn.getIcDsbHeader().toString()};
                Integer [] types = {Types.VARCHAR, Types.VARCHAR};
                
                dbs.sqlUpdate(updateSelect, args, types);
                this.setStatus(dbs.getStatus());
            }            
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return null;
    }
}