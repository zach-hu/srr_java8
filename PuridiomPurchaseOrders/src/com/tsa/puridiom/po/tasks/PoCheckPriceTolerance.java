package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;

import java.util.Map;

public class PoCheckPriceTolerance extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
        	PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId"));
        	String tolerance = propertiesManager.getProperty("PO OPTIONS", "TOLERANCE", "0");

        	PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
        	BigDecimal reqTotal = (BigDecimal) incomingRequest.get("requisitionTotal");

        	if (reqTotal != null)
        	{
        		BigDecimal poTotal = poHeader.getTotal();

        		if (reqTotal.compareTo(poTotal) != 0)
        		{
        			BigDecimal bdTolerance = new BigDecimal(tolerance);
        			BigDecimal bdTol = reqTotal.multiply(bdTolerance).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
        			BigDecimal bdDiff = poTotal.subtract(reqTotal);
        			if (bdDiff.compareTo(bdTol) > 0)
        			{
        				incomingRequest.put("poTolerance", "FAILED");
        			}
        		}

        	}

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("An Error ocurred submitting the current Invoice ", e);
        }
        return null ;
    }
}
