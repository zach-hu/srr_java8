/*
 * Created on Apr 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisition.buyerassignment.tasks;

import java.util.Map;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class Setup extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            PropertiesManager pm = PropertiesManager.getInstance((String)incomingRequest.get("organizationId"));

            String bSugest = pm.getProperty("ASSIGNMENT", "AssignSuggested", "N");
            Log.debug(this.getName(), "Assign Suggested" + bSugest);
            incomingRequest.put("suggested", bSugest);

            String bCommodity = pm.getProperty("ASSIGNMENT", "AssignCommodity", "N");
            Log.debug(this.getName(), "Assign Commodity" + bCommodity);
            incomingRequest.put("byCommodity", bCommodity);

            String bDept = pm.getProperty("ASSIGNMENT", "AssignDepartmentBuyer", "N");
            Log.debug(this.getName(), "AssignDepartmentBuyer" + bDept);
            incomingRequest.put("byDepartment", bDept);

            String bByLine = pm.getProperty("ASSIGNMENT", "AssignByLine", "N");
            Log.debug(this.getName(), "AssignByLine" + bByLine);
            incomingRequest.put("byLine", bByLine);

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("Buyer Assignment failed!");
        }
        return ret;
    }
}
