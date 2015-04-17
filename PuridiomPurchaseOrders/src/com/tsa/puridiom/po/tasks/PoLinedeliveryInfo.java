/*
 * Created on Sep 13, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.po.tasks;.PoLinedeliveryInfo.java
 * 
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.OrderType;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLinedeliveryInfo extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest =(Map)object;
            DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String stringIcRelKey = (String)incomingRequest.get("PoLine_icRelKey");
			BigDecimal icRelKey = new BigDecimal(0);
			
			if(stringIcRelKey != null)
			{
			    icRelKey = new BigDecimal(stringIcRelKey);
			}
			else
			{
			    this.setStatus(Status.FAILED);
			    throw new TsaException("No Line was found!");
			}
			
			String queryString = "Select poLine.poNumber, poLine.releaseNumber, poHeader.revisionNumber, poLine.lineNumber, poLine.quantity, poLine.unitPrice " +
					"FROM PoHeader as poHeader, PoLine as poLine " +
					"WHERE poLine.icPoHeader = poHeader.id AND " +
					"poLine.icRelKey = ? AND " +
					"poHeader.poType = '" + OrderType.DELIVERY_RELEASE + "' AND " +
					"poHeader.lastRevision = 'C' AND " +
					"poHeader.status <>  '" + DocumentStatus.CANCELLED + "'";
			ret = dbs.query(queryString, new Object[] {icRelKey} , new Type[] { Hibernate.BIG_DECIMAL});
            
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
    }
}
