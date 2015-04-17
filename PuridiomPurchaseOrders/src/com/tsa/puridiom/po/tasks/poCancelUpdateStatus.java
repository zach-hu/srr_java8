/**
 * Created on Apr 12, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.pp.tasks.PoCancelCheck.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.po.exceptions.PoCancelException;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;

public class poCancelUpdateStatus extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
        try
        {
        	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
            String newStatus = DocumentStatus.CLOSED ;
            
            List icPoHeader = (List)incomingRequest.get("poCancelRetrieve") ;
            
            for(int x = 0; x < icPoHeader.size(); x++)
			{
            	PoHeader icPo = (PoHeader)icPoHeader.get(x);
            	icPo.setStatus(newStatus);
            	BigDecimal IcReqHeader = icPo.getIcReqHeader();
            	
            	if(IcReqHeader != null)
            	{
            		String	sqlUpdate = "update Requisition_Header set status = ? where Ic_Req_Header = ?";
            		Object [] args = {newStatus, IcReqHeader};
            		Integer [] types = {Types.VARCHAR, Types.VARCHAR};
            		
        			dbs.sqlUpdate(sqlUpdate, args, types);
        			this.setStatus(dbs.getStatus()) ;
            	}
			}
            
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