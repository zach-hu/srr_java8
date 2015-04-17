/*
 * Created on Sep 7, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.po.tasks.PoCreateBlanketInfo.java
 * 
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoCreateBlanketInfo extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            PoBlanketInfo blanketInfo = new PoBlanketInfo();
            PoHeader currentPo = (PoHeader)incomingRequest.get("poHeader");
            incomingRequest.put("PoHeader_poType", currentPo.getPoType());
            incomingRequest.put("PoHeader_poNumber", currentPo.getPoNumber());
            incomingRequest.put("PoHeader_releaseNumber", currentPo.getReleaseNumber());
            incomingRequest.put("blanketIc", currentPo.getIcPoHeader());
            incomingRequest.put("icHeader", currentPo.getIcPoHeader());
            
            String blanketType = currentPo.getPoType();
            if (blanketType.equals(OrderType.DELIVERY_RELEASE))
            {
                blanketType = OrderType.DELIVERY_ORDER;
            }
            else if(blanketType.equals(OrderType.RELEASE_ORDER))
            {
                blanketType = OrderType.RELEASE_ORDER;
            }
            else if(blanketType.equals(OrderType.SERVICE_RELEASE))
            {
                blanketType = OrderType.SERVICE_BLANKET;
            }
            
            incomingRequest.put("blanketType", blanketType);
            
            ret = blanketInfo;
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.SUCCEEDED);
            throw new TsaException("An error ocurred getting Blanket Info");
        }
        return ret;
    }
}
