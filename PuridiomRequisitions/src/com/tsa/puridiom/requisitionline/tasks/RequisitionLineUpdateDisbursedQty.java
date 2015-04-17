/*
 * Created on Jan 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RequisitionLineUpdateDisbursedQty extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionLine reqLine = (RequisitionLine)incomingRequest.get("requisitionLine");

            String newQtyString = (String)incomingRequest.get("DisbLine_quantity");
			BigDecimal newQty = new BigDecimal(newQtyString);
			
			BigDecimal back = reqLine.getQuantity().subtract(newQty);
            if(back.compareTo(new BigDecimal(0)) > 0)
            {
                reqLine.setBackordered(back);
            }
            ret = reqLine;
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
