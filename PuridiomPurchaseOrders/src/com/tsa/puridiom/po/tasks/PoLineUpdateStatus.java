/**
 * Created on Apr 12, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.puridiom.hilton.pp.tasks.PoCancelCheck.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class PoLineUpdateStatus extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

        try
        {
        	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        	String oldStatus = "";
            String oid = (String) incomingRequest.get("organizationId");

            PoHeader icPoHeader = (PoHeader)incomingRequest.get("poHeader") ;
            List poLine = (List)incomingRequest.get("poLineList") ;

            for(int x = 0; x < poLine.size(); x++)
			{
            	PoLine icPoLine = (PoLine)poLine.get(x);
            	BigDecimal qty = HiltonUtility.ckNull((BigDecimal) icPoLine.getQuantity());
            	BigDecimal qtyRcv = HiltonUtility.ckNull((BigDecimal) icPoLine.getQtyReceived());
            	qtyRcv = qtyRcv.abs();
            	qty = qty.abs();
            	if (qtyRcv.compareTo(new BigDecimal(0)) == 0) {
            		oldStatus = DocumentStatus.PO_AWARDED;
            	} else if (qtyRcv.compareTo(qty) >= 0) {
            		oldStatus = DocumentStatus.RCV_RECEIVED;
	            } else {
	            	oldStatus = DocumentStatus.RCV_PARTIALLYRECEIVED;
	            }

            	icPoLine.setStatus(oldStatus);
            	dbs.update(icPoLine);
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