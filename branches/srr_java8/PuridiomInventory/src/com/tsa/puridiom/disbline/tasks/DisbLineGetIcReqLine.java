/*
 * Created on Jan 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.disbline.tasks;

import java.util.Map;

import com.tsa.puridiom.disbline.exceptions.DisbLineNotFoundException;
import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisbLineGetIcReqLine extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            DisbLine disbLine = (DisbLine)incomingRequest.get("disbLine");
            if(disbLine == null)
            {
                this.setStatus(Status.FAILED);
                throw new DisbLineNotFoundException("Disbursement Line was not found!");
            }
            ret = disbLine.getIcReqLine().toString();
            
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
