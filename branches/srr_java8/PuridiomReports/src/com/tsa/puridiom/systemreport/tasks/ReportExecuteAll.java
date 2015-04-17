/*
 * Created on Jan 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.systemreport.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Report;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReportExecuteAll extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Map reports = new HashMap();
        try
        {
            Map incomingRequest = (Map)object;
            List reportslist = (List)incomingRequest.get("reportsList");
            for(int i = 0; i < reportslist.size(); i++)
            {
                Report report = (Report)reportslist.get(i);
                
                SystemReportExecute task = new SystemReportExecute();
                
                incomingRequest.put("reportName", report.getReportDatawindow());
                String reportName = (String)task.executeTask(incomingRequest);
                reports.put(report.getReportDatawindow(), reportName);
                
            }
            
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return reports;
    }
}
