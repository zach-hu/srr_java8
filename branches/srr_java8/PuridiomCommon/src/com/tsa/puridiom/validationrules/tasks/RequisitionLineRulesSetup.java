/*
 * Created on Nov 4, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.validationrules.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RequisitionLineRulesSetup extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            String icReqHeader = (String)incomingRequest.get("RequisitionHeader_icReqHeader");
            String icReqLine = (String)incomingRequest.get("RequisitionLine_icReqLine");

            if(icReqHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("Please select a Requisition to Validate");
            }
            BigDecimal ic = new BigDecimal(icReqHeader);
            BigDecimal icLine = new BigDecimal(icReqLine);
            //incomingRequest.put("RequisitionHeader_icReqHeader", ic);
            incomingRequest.put("RequisitionLine_icReqHeader", icReqHeader);
            incomingRequest.put("RequisitionLine_icReqLine", icReqLine);
            incomingRequest.put("Account_icHeader", icReqHeader);
            incomingRequest.put("DocComment_icHeader", icReqHeader);
            incomingRequest.put("DocAttachment_icLine", icReqLine);
            incomingRequest.put("formType", "REQLN");
            incomingRequest.put("Labels_moduleAccess", "REQUISITIONLINE");
            incomingRequest.put("Rules_moduleAccess", "REQUISITIONLINE");
            incomingRequest.put("Rules_enabled", "Y");
            incomingRequest.put("valType", "REQUISITIONLINE");
            

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return null;
    }
}

