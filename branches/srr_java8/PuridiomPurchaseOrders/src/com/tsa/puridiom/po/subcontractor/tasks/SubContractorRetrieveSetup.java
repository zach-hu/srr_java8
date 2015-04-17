package com.tsa.puridiom.po.subcontractor.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.Map;

public class SubContractorRetrieveSetup extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

        if (poHeader == null)
        {
            this.setStatus(Status.FAILED);
            throw new Exception("PoHeader cannot be empty.  SubContractors could not be retrieved.");
        }
        else
        {
        	String poNumber = poHeader.getPoNumber();
    		BigDecimal releaseNumber = poHeader.getReleaseNumber();

    		incomingRequest.put("SubContractor_poNumber", poNumber);
    		incomingRequest.put("SubContractor_releaseNumber", releaseNumber.toString());
        }
        this.setStatus(Status.SUCCEEDED);
        return null;

	}

}