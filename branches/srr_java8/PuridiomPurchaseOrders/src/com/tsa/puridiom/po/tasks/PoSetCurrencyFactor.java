/*
 * Created on Apr 4, 2005
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
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoSetCurrencyFactor extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Log.debug(this, "execute Task");
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            BigDecimal factor = (BigDecimal)incomingRequest.get("CurrCode_factor");
            PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
            String CurrCode_currencyCode = (String)incomingRequest.get("CurrCode_currencyCode");
            if(factor != null)
            {
                Log.debug(this, "Currency: " + factor.toString() + " Code: " + CurrCode_currencyCode);
                poHeader.setCurrencyFactor(factor);
                poHeader.setCurrencyCode(CurrCode_currencyCode);
            }
            ret = poHeader;
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("No currency Code found!");
        }

        return ret;
    }
}
