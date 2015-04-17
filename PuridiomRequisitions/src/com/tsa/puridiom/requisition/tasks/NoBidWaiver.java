/*
 * Created on Apr 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NoBidWaiver extends Task
{

    public Object executeTask(Object object) throws Exception
    {
    	Map incomingRequest = (Map)object;
        Object ret = null;
        try
        {
        	String oid = (String)incomingRequest.get("organizationId");
        	String language = (String)incomingRequest.get("language");
        	String bidWaiverMsg = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "needBidWaiver", "Cannot award request. Must obtain bids prior to award!");
        	incomingRequest.put("bidWaiverMsg", bidWaiverMsg);
        	incomingRequest.put("needBidWaiverPage", "/orders/po_select_type.jsp");

        	ret = "true";
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("BidWaiver Check failed", e);
        }
        return ret;
    }
}
