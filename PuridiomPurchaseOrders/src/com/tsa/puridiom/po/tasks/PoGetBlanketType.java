/*
 * Created on Sep 15, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.po.tasks;.PoGetBlanketType.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.OrderType;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoGetBlanketType extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        String blanketType = OrderType.BLANKET_ORDER;
        try
        {
            Map incomingRequest = (Map)object;
            String orderType = (String)incomingRequest.get("PoHeader_poType");
            if(orderType.equals(OrderType.SERVICE_RELEASE))
            {
                blanketType = OrderType.SERVICE_ORDER;
            }
            else if(orderType.equals(OrderType.DELIVERY_RELEASE))
            {
                blanketType = OrderType.DELIVERY_ORDER;
            }
            else if(orderType.equals(OrderType.PURCHASE_RELEASE))
            {
                blanketType = OrderType.PURCHASE_RELEASE;
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return blanketType;
    }
}
