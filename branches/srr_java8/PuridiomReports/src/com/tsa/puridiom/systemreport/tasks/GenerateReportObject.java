/*
 * Created on Mar 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.systemreport.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GenerateReportObject extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            String browseName = (String)incomingRequest.get("browseName");
            List xmls = new ArrayList();
            if(browseName.equals("none"))
            {
                xmls.add("project-tracking");
                xmls.add("project-tracking2");
            }
            else
            {
                xmls.add(browseName);
            }

            List partialDs = new ArrayList();
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            for(int i = 0; i < xmls.size(); i++)
            {
                PuridiomProcess process = processLoader.loadProcess("report-execute.xml");
                incomingRequest.put("browseName", xmls.get(i));
                process.executeProcess(incomingRequest);
                if(process.getStatus() == Status.SUCCEEDED)
                {
                   partialDs.addAll((List)incomingRequest.get("datasource"));
                }
                else
                {
                    throw new TsaException("An error ocurred generating " + (String)xmls.get(i) + " Report");
                }
            }
            incomingRequest.put("datasource", partialDs);
            ret = incomingRequest.get("browseObject");

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("GenerateReportObject failed! ", e);
        }
        return ret;
    }
}
