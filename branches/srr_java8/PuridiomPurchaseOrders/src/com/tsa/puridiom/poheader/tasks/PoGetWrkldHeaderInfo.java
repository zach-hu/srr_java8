/*
 * Created on Aug 31, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poheader.tasks;.PoGetWrkldHeaderInfo.java
 * 
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoGetWrkldHeaderInfo extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            String awardNow = (String)incomingRequest.get("awardNow");
            if(awardNow == null){		awardNow = "N";	}
            if(awardNow.equals("Y"))
            {
                //do this if there is a need to check for shipto, supplier code..
                String shipto = (String)incomingRequest.get("PoHeader_shipToCode");
                String error = "";
                if(shipto == null)
                {
                    error = "Ship To Code ";
                }
                
            }
            incomingRequest.put("PoHeader_poType", "PO");
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
