/*
 * Created on Jan 12, 2005
 */
package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.poheader.tasks.UserErrors;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import java.util.Map;
/**
 * @author renzo / Kelli
 */
public class PoCheckForErrors extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            UserErrors errors = (UserErrors) incomingRequest.get("userErrors");
            
            for (int i=0; i < errors.size(); i++)
            {
                if (errors.getErrorSeverity(i) < PoErrors.NOTICE)
                {
                    // Process should not continue
                    this.setPostAction("exitProcess");
                    break;
                }
            }
            
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return null;
    }
}
