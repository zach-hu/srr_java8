/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.requisitionheader.tasks.RequisitionLineGetHistory.java
 *
 */
package com.tsa.puridiom.requisitionheader.history.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class RequisitionHeaderHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionHeader oldHeader = (RequisitionHeader) incomingRequest.get("originalRequisitionHeader");
            if (oldHeader != null && !Utility.isEmpty(oldHeader.getRequisitionNumber()))
            {
            	PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
                PuridiomProcess process = processLoader.loadProcess("requisition-history.xml");

                Map newIncomingRequest = new HashMap();
                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
                newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                newIncomingRequest.put("userId", incomingRequest.get("userId"));
                newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
                newIncomingRequest.put("RequisitionHeader_oldStatus", incomingRequest.get("RequisitionHeader_status"));
                newIncomingRequest.put("newHistoryRequisitionHeader", oldHeader);
                newIncomingRequest.put("historyStatus", incomingRequest.get("historyStatus"));
                newIncomingRequest.put("BuyerRemarks_icLine", incomingRequest.get("BuyerRemarks_icLine"));
                newIncomingRequest.put("createFromSaveAs", HiltonUtility.ckNull(incomingRequest.get("createFromSaveAs")));

                if (incomingRequest.containsKey("RequisitionHeader_noteCancel")) {
					newIncomingRequest.put("RequisitionHeader_noteCancel", incomingRequest.get("RequisitionHeader_noteCancel"));
				}
                if (incomingRequest.containsKey("ApprovalLog_approverNotes")) {
                    newIncomingRequest.put("ApprovalLog_approverNotes", incomingRequest.get("ApprovalLog_approverNotes"));
                }

                String forwardTo = (String)incomingRequest.get("forwardedTo");
                if (forwardTo == null)
                {
                    forwardTo = "";
                }
                newIncomingRequest.put("forwardedTo", forwardTo);

                String rejectedBy = (String)incomingRequest.get("rejectedBy");
                if (rejectedBy == null)
                {
                    rejectedBy = "";
                }
                newIncomingRequest.put("rejectedBy", rejectedBy);
                newIncomingRequest.put("ipAddress", incomingRequest.get("ipAddress"));

                process.executeProcess(newIncomingRequest);
                this.setStatus(process.getStatus());
            }

            RequisitionHeader newHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            if(!Utility.isEmpty(newHeader.getRequisitionNumber()))
            {
                PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
                PuridiomProcess process = processLoader.loadProcess("requisition-history.xml");

                Map newIncomingRequest = new HashMap();
                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
                newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                newIncomingRequest.put("userId", incomingRequest.get("userId"));
                newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
                newIncomingRequest.put("RequisitionHeader_oldStatus", incomingRequest.get("RequisitionHeader_status"));
                newIncomingRequest.put("newHistoryRequisitionHeader", newHeader);
                newIncomingRequest.put("historyStatus", incomingRequest.get("historyStatus"));
                newIncomingRequest.put("BuyerRemarks_icLine", incomingRequest.get("BuyerRemarks_icLine"));
                newIncomingRequest.put("createFromSaveAs", HiltonUtility.ckNull(incomingRequest.get("createFromSaveAs")));

                String deferTo = (String)incomingRequest.get("deferTo") ;
                
                if(!HiltonUtility.isEmpty(deferTo)){
                	newIncomingRequest.put("deferTo", deferTo);
                }
                
                if (incomingRequest.containsKey("RequisitionHeader_noteCancel")) {
					newIncomingRequest.put("RequisitionHeader_noteCancel", incomingRequest.get("RequisitionHeader_noteCancel"));
				}
                if (incomingRequest.containsKey("ApprovalLog_approverNotes")) {
                    newIncomingRequest.put("ApprovalLog_approverNotes", incomingRequest.get("ApprovalLog_approverNotes"));
                }

                String forwardTo = (String)incomingRequest.get("forwardedTo");
                if (forwardTo == null)
                {
                    forwardTo = "";
                }
                newIncomingRequest.put("forwardedTo", forwardTo);

                String rejectedBy = (String)incomingRequest.get("rejectedBy");
                if (rejectedBy == null)
                {
                    rejectedBy = "";
                }
                newIncomingRequest.put("rejectedBy", rejectedBy);
                newIncomingRequest.put("ipAddress", incomingRequest.get("ipAddress"));

                process.executeProcess(newIncomingRequest);
                this.setStatus(process.getStatus());
            }
            else
            {
                this.setStatus(Status.SUCCEEDED);
            }
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
           throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}