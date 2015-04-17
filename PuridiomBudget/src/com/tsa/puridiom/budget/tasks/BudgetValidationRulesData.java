/*
 * Created on Dec 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.budget.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.BudgetCenter;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;


public class BudgetValidationRulesData extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            BudgetCenter header = (BudgetCenter)incomingRequest.get("budgetCenter");
            if(header == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("BudgetCenter was not found!");
            }

            incomingRequest.put("header", header);

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
