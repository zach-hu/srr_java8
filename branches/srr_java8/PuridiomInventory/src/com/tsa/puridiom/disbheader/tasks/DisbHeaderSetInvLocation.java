/*
 * Created on Dec 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.disbheader.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisbHeaderSetInvLocation extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            List disbList = (List)incomingRequest.get("disbLines");
            boolean setLocation = false;
            String tempLocation = "";
            
            for(int i = 0; i < disbList.size(); i++)
            {
                DisbLine line =(DisbLine)disbList.get(i);
                if(i == 0)
                {
                    tempLocation = line.getItemLocation();
                    setLocation = true;
                }
                else if(tempLocation.equalsIgnoreCase(line.getItemLocation()))
                {
                    setLocation = true;
                }
                else
                {
                    setLocation = false;
                }
            }
            if(setLocation)
            {
                DisbHeader header = (DisbHeader)incomingRequest.get("disbHeader");
                header.setItemLocation(tempLocation);
                incomingRequest.put("disbHeader", header);
            }
            
            this.setStatus(Status.SUCCEEDED);
	    }
	    catch (Exception e) 
	    {
	        this.setStatus(Status.FAILED);
	       throw new TsaException(this.getName(), e);
	    }
	    return super.executeTask(object);
    }
}
