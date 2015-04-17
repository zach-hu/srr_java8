package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class RequisitionLineSaveasList extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map) object;
        Object result = null;

        try
        {
            List reqLineList = (List) incomingRequest.get("requisitionLineList");
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
            PuridiomProcess process = processLoader.loadProcess("requisitionline-saveas.xml");
            
            String createdFrom = (String) incomingRequest.get("createdFrom") ;

            for (int i = 0; i < reqLineList.size(); i++)
            {
                RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);

                incomingRequest.put("requisitionLine", reqLine);
                incomingRequest.put("RequisitionLine_icReqLine", reqLine.getIcReqLine().toString());
        		incomingRequest.put("InspectionLine_icMsrLine", reqLine.getIcLineHistory()) ;

        		if (createdFrom != null && createdFrom.equalsIgnoreCase("SOURCING")) {
        			incomingRequest.put("newRequisitionLine_lineNumber", new BigDecimal(i+1).toString()) ;
        		}
        		
                process.executeProcess(incomingRequest);

                if (process.getStatus() < Status.SUCCEEDED)
                {
                    throw new Exception("Requisition Line save as process failed.");
                }

                reqLineList.set(i, incomingRequest.get("requisitionLine"));
            }

            result = reqLineList;
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