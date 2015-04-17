/*
 * Created on Sep 7, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.po.tasks.PoSetBlanketInfo.java
 * 
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoSetBlanketInfo extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            PoBlanketInfo blanketInfo = (PoBlanketInfo)incomingRequest.get("blanketInfo");
            PoHeader currentPo =  (PoHeader)incomingRequest.get("poHeader");
            
            blanketInfo.setPoEffective(currentPo.getEffectiveDate());
            blanketInfo.setPoExpires(currentPo.getExpirationDate());
            blanketInfo.setPoBlanket(currentPo.getBlanketLimit());
            blanketInfo.setPoReleaseLimit(currentPo.getReleaseLimit());
            blanketInfo.setBoSupercedes(currentPo.getLinkPriorOrder());
            blanketInfo.setBoSuperceded(currentPo.getLinkNextOrder());
            
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
