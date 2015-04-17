/*
 * Created on Aug 26, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poline.tasks;.PoLineReleaseSetup.java
 * 
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineReleaseSetup extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            List linesList = (List)incomingRequest.get("poLineList");
            String poType = (String)incomingRequest.get("PoHeader_poType");
            String releasePoNumber = (String)incomingRequest.get("release_poNumber");
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            BigDecimal release = poHeader.getReleaseNumber();
            for (Iterator iter = linesList.iterator(); iter.hasNext();)
            {
                PoLine poLine = (PoLine) iter.next();
                if(poType.equalsIgnoreCase("RO"))
                {
                    poLine.setIcReqLine(new BigDecimal(0));
                }
                
                poLine.setPoNumber(releasePoNumber);
                //poLine.setQuantity(new BigDecimal(0));
                //poLine.setLineTotal(new BigDecimal(0));
                poLine.setReleaseNumber(release);
            }
            
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}
