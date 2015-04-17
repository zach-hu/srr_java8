/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.*;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.properties.DictionaryManager;
/**
 * @author Administrator
 */
public class RequisitionApprovalSetup extends Task {
    public Object executeTask(Object object) throws Exception {

        Map incomingRequest = (Map) object;

        String icReqHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
        String	fromSystemAlerts = (String) incomingRequest.get("fromSystemAlerts");

        if (Utility.ckNull(fromSystemAlerts).equals("Y")) {
        	Object mainIcObj = incomingRequest.get("mainIc");
            if (mainIcObj != null) {		icReqHeader = mainIcObj.toString();		}
        }

        if (Utility.isEmpty(icReqHeader)) {
            icReqHeader = (String) incomingRequest.get("ApprovalLog_icHeader") ;
            incomingRequest.put("RequisitionLine_icReqHeader",icReqHeader) ;
        }

        if (Utility.isEmpty(icReqHeader)) {
            this.setStatus(Status.FAILED);
            throw new Exception("RequisitionHeader_icReqHeader cannot be empty.  Requisition header could not be retrieved.");
        } else {
            incomingRequest.put("ApprovalLog_icHeader",icReqHeader) ;
        }

        incomingRequest.put("RequisitionHeader_icReqHeader", icReqHeader) ;

        this.setStatus(Status.SUCCEEDED);
        return null ;
    }
}
