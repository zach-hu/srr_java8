/*
 * Created on Jan 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoSetReleaseTotalInfo extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            PoBlanketInfo blanketInfo = (PoBlanketInfo)incomingRequest.get("blanketInfo");
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            
            blanketInfo.setOrderTotal(poHeader.getTotal());
            BigDecimal avail = blanketInfo.getPoReleaseLimit().subtract(poHeader.getTotal());
            blanketInfo.setAvailableOrder(avail);
            ret = blanketInfo;
            
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
