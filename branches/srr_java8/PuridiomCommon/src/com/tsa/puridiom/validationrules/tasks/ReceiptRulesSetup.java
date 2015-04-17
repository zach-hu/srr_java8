/*
 * Created on Nov 7, 2005
 */
package com.tsa.puridiom.validationrules.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Kelli
 */
public class ReceiptRulesSetup extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;

            incomingRequest.put("createAction", "VALIDATE");
            incomingRequest.put("formType", "REC");
			incomingRequest.put("Labels_moduleAccess", "RECEIVING");
			incomingRequest.put("Rules_moduleAccess", "RECEIVING");
			incomingRequest.put("Rules_enabled", "Y");

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }
        return null;
    }
}
