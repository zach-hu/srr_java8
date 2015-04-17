package com.tsa.puridiom.invformpart.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvFormPart;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;;

public class InvFormPartSaveasByItemNumber extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map) object;
        Object result = null;

        try
        {
            List invFormPartList = (List) incomingRequest.get("invFormPartList");
            String	newItemNumber = (String) incomingRequest.get("newInvFormPart_itemNumber");

            if (invFormPartList != null && invFormPartList.size() > 0 && Utility.isEmpty(newItemNumber)) {
                throw new Exception("Cannot process save as without a new item number [ newInvFormPart_itemNumber ].");
            }
            
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
            PuridiomProcess process = processLoader.loadProcess("invformpart-saveas.xml");
            
            for (int i = 0; i < invFormPartList.size(); i++)
            {
                InvFormPart invFormPart = (InvFormPart) invFormPartList.get(i);

                incomingRequest.put("invFormPart", invFormPart);

                process.executeProcess(incomingRequest);

                if (process.getStatus() < Status.SUCCEEDED)
                {
                    throw new Exception("InvFormPart save as process failed.");
                }
                
                invFormPartList.set(i, incomingRequest.get("invFormPart"));
            }

            result = invFormPartList;
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }
        return result;
    }

}