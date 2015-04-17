package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class RequisitionLineCloneMsrList extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map) object;
        Object result = null;

        try
        {
            List reqLineList = (List) incomingRequest.get("requisitionLineList");
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
            PuridiomProcess process = processLoader.loadProcess("requisitionline-clonemsr.xml");
            
            String createdFrom = (String) incomingRequest.get("createdFrom") ;
            //List reqLinesVinimaya = new ArrayList();
            List newReqLineList = new ArrayList();
            //incomingRequest.put("reqLinesVinimaya",reqLinesVinimaya);
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
                if(incomingRequest.get("requisitionLine")!=null && ((String)incomingRequest.get("ClonedItemForSave")).equals("true"))
                {
                	newReqLineList.add(incomingRequest.get("requisitionLine"));
                }
            }
            result = newReqLineList;
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