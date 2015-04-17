/*
 * Created on Dec 13, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoLineSetShippingTaxFromHeader extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            PoHeader header = (PoHeader)incomingRequest.get("poHeader");
            List poLineList = (List)incomingRequest.get("poLineList");
            boolean headerShipTax = header.getShippingTaxable().equals("Y");
            for (Iterator iter = poLineList.iterator(); iter.hasNext();)
            {
                PoLine line = (PoLine) iter.next();
            
                if(headerShipTax && line.getTaxable().equals("Y"))
                {
	                line.setShippingTaxable("Y");
                }
                else
                {
                    line.setShippingTaxable("N");
                }
            }
            
            ret = poLineList;
            
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
        }
        return ret;
    }
}
