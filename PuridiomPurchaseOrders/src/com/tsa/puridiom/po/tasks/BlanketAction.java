/*
 * Created on May 22, 2005
 */
package com.tsa.puridiom.po.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.vendor.tasks.VendorRetrieveById;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class BlanketAction extends Task
{
    public boolean isBlanket(PoHeader poHeader)
    {
        if(poHeader.getPoType().equalsIgnoreCase(OrderType.BLANKET_ORDER))
        {
            return true;
        }
        else if(poHeader.getPoType().equalsIgnoreCase(OrderType.SERVICE_BLANKET))
        {
            return true;
        }
        else if(poHeader.getPoType().equalsIgnoreCase(OrderType.DELIVERY_ORDER))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        Map incomingRequest = (Map)object;
        Log.debug(this, "setting blanket Action at forward");
        String flag = "P";
        PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
        try
        {
        	if(poHeader == null)
        	{
        		this.setStatus(Status.FAILED);
        		throw new TsaException("Order was not found!");
        	}
            poHeader = (PoHeader)incomingRequest.get("poHeader");
            //this is checked with a rule at the task/activity level
            //if(this.isBlanket(poHeader))
            //{
                VendorRetrieveById venRetrieve = new VendorRetrieveById();
                String Vendor_vendorId = poHeader.getVendorId();
                Map newInc = new HashMap();
                newInc.put("dbsession", incomingRequest.get("dbsession"));
                newInc.put("organizationId", incomingRequest.get("organizationId"));
                newInc.put("Vendor_vendorId", Vendor_vendorId);

                try
                {
                    Vendor vendor = (Vendor)venRetrieve.executeTask(newInc);
                    if(venRetrieve.getStatus() == Status.SUCCEEDED)
                    {
                        flag = vendor.getPrintFaxCode();
                    }
                    else
                    {
                        flag = "P";
                    }

                }
                catch (Exception e)
                {
                    flag = "P";
                }
                poHeader.setEdiOrder(flag);
                ret = poHeader;
            //}
        }
        catch (Exception e)
        {
        	poHeader.setEdiOrder(flag);
            //throw new TsaException("An error occurred obtaining flag from vendor. [" + e.getMessage() + "]" , e);
        }
        this.setStatus(Status.SUCCEEDED);
        return ret;
    }
}
