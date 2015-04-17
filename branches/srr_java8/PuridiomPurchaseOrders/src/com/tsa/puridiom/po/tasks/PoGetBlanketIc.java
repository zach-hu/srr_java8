/*
 * Created on Sep 15, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.po.tasks;.PoGetBlanketIc.java
 * 
 */
package com.tsa.puridiom.po.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;

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
public class PoGetBlanketIc extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        
        try
        {
            Map incomingRequest = (Map)object;
            DBSession dbs =(DBSession)incomingRequest.get("dbsession");
            String poNumber = (String)incomingRequest.get("PoHeader_poNumber");
            
            String sql = "Select poHeader.icPoHeader, poHeader.vendorClass from PoHeader as poHeader " +
            		"Where poHeader.poNumber = ? AND " +
            		"poHeader.releaseNumber = 0 AND " +
            		"poHeader.lastRevision = 'C'";
            
            List resultList = dbs.query(sql, new Object[] {poNumber} , new Type[] {Hibernate.STRING});
			
			
			if(resultList != null && resultList.size() > 0)
			{
			    Object returnArray[] = (Object []) resultList.get(0);
			    ret = HiltonUtility.ckNull(returnArray[0]);
			    incomingRequest.put("PoHeader_icPoHeader", HiltonUtility.ckNull(returnArray[0]).toString());
			    incomingRequest.put("PoHeader_vendorClass", HiltonUtility.ckNull((String)returnArray[1]));
			    this.setStatus(Status.SUCCEEDED);
			}
			else
			{
			    this.setStatus(Status.FAILED);
	            throw new TsaException("Blanket Order was not found!");
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
