/*
 * Created on November 3, 2005
 */
package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Kelli
 */
public class RfqValidationRulesData extends Task {

    public Object executeTask(Object object) throws Exception {
        Object ret = null;
        try {
            Map incomingRequest = (Map)object;
            RfqHeader header = (RfqHeader)incomingRequest.get("rfqHeader");
            
            if (header == null) {
                this.setStatus(Status.FAILED);
                throw new Exception("Rfq Header was not found!");
            }

            incomingRequest.put("header", header);

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e) {
            this.setStatus(Status.FAILED);
            throw e;
        }
        return ret;
    }

    /**
     * @param incomingRequest
     */
    private List setUpLineAccounts(Map incomingRequest)
    {
        List lineAccounts = new ArrayList();
        List lineList = (List)incomingRequest.get("lineitems");
        for(int i = 0; i < lineList.size(); i++)
        {
            RequisitionLine line = (RequisitionLine)lineList.get(i);
            List acctLineList = line.getAccountList();
            if(acctLineList == null)
            {
                acctLineList = new ArrayList();
            }
            lineAccounts.add(acctLineList);
        }
        return lineAccounts;

    }
}
