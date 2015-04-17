package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.util.Map;

public class RfqBidHistoryListPriceCheck extends Task{
    public Object  executeTask (Object object) throws Exception
    {
        Map incomingRequest = (Map) object;
        Object ret = null;
        try
        {
            Object RfqBidHistory_unitPriceObj = incomingRequest.get("RfqBidHistory_unitPrice");
            String RfqBidHistory_unitPrice[] = null;

            if ( RfqBidHistory_unitPriceObj instanceof String[]) {
            	RfqBidHistory_unitPrice		= (String[])RfqBidHistory_unitPriceObj;
            }
            else {
            	RfqBidHistory_unitPrice = new String[1];
            	RfqBidHistory_unitPrice[0]	= (String)RfqBidHistory_unitPriceObj;
            }

            if (RfqBidHistory_unitPrice == null || RfqBidHistory_unitPrice.length == 0) {
                this.setStatus(Status.FAILED);
                throw new TsaException("UnitPrice list is necessary to set a bid history list");
            }
            else {
            	incomingRequest.put("RfqBidHistory_unitPrice", RfqBidHistory_unitPrice);
            	this.setStatus(Status.SUCCEEDED);
            }
        }
        catch (Exception e)
        {
            Log.error(this, e.toString());
            this.setStatus(Status.FAILED);
        }
        return ret;
    }

}