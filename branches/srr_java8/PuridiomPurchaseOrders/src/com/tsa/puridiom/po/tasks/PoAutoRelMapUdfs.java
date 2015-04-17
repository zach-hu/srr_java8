/**
 *
 * Created on Jan 23, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoReqMapUdfs.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;


public class PoAutoRelMapUdfs extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
        if(poHeader == null)
        {
            throw new TsaException("An error ocurred releasing items!. PoHeader was not Found!");
        }
        try
        {
            RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");

            Map requdfs = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getSection("REQ TO PO");
            Log.debug(this.getName(), "Mappings found: " + requdfs.toString());
            Set udfs = requdfs.keySet();


            for (Iterator iter = udfs.iterator(); iter.hasNext();)
            {
                String reqUdf = (String) iter.next();
                String poUdf = (String)requdfs.get(reqUdf);

                poHeader = MappingUdfs.mapReqToPoUdf(poUdf, reqUdf, reqHeader, poHeader);
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            Log.error(this, e.toString());
            this.setStatus(Status.FAILED);
        }
        return poHeader;
    }

}
