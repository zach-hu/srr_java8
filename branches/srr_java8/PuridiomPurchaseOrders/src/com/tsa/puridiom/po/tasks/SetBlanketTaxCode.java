package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author jose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SetBlanketTaxCode extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Log.debug(this, "execute Task");
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;

            PoHeader blanket = (PoHeader)incomingRequest.get("blanket");
            if(blanket == null)
            {
	            this.setStatus(Status.FAILED);
	            throw new TsaException("Blanket Order was not Found.");
            }

            PoHeader newPoHeader = (PoHeader)incomingRequest.get("poHeader");
            if(newPoHeader == null)
            {
	            this.setStatus(Status.FAILED);
	            throw new TsaException("Release Order was not Found.");
            }

            RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            if(requisitionHeader == null)
            {
            	this.setStatus(Status.FAILED);
            	throw new TsaException("Requisition was not Found!.");
            }

            if(blanket.getTaxCode().equals( "SHIPTO"))
            {
               newPoHeader.setTaxCode(requisitionHeader.getShipToAddress().getAddrFld10());
            }

            incomingRequest.put("poHeader", newPoHeader);

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
