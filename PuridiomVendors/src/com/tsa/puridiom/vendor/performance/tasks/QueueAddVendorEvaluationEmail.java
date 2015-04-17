package com.tsa.puridiom.vendor.performance.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.entity.PerformanceDetail;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class QueueAddVendorEvaluationEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            PerformanceDetail vendorPerformance = (PerformanceDetail) incomingRequest.get("performanceDetail") ;
            PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader") ;
            String sendTo = vendorPerformance.getEvaluator();
            String status = vendorPerformance.getStatus();
            String vendorId = poHeader.getVendorId();
            String vendorName = poHeader.getVendorName();
            String poNumber = poHeader.getDisplayPoNumber().toString();

            Log.debug(this, "Writting send_queue record for vendor evaluation: [" + vendorId + "] - " + vendorName + "; " + poNumber + " ; Evaluator: " + sendTo);

            if((status.equals(DocumentStatus.SUP_PERFORMANCE_INCOMPLETE) || status.equals(DocumentStatus.SUP_PERFORMANCE_INPROGRESS)) && !Utility.isEmpty(sendTo))
            {
                incomingRequest.put("SendQueue_doctype", "VNE");
                incomingRequest.put("SendQueue_docic", String.valueOf(poHeader.getIcPoHeader()));

                String owner = (String) incomingRequest.get("userId") ;

                StringBuffer subject = new StringBuffer("");
                subject.append("Vendor Evaluation requested for " + poNumber  + ".") ;

                String	duplicateNotification = (String) incomingRequest.get("duplicateNotification");
                if (!Utility.isEmpty(duplicateNotification) && duplicateNotification.equalsIgnoreCase("Y")) {
                    subject.append(" [2nd Notice]") ;
                }

                incomingRequest.put("SendQueue_subject",subject.toString() );
                incomingRequest.put("SendQueue_sendfromtype", "E");
                if(Utility.isEmpty(owner))
                {
                	owner = (String) incomingRequest.get("SendQueue_sendfrom") ;
                	incomingRequest.put("SendQueue_sendfrom", owner );
                }
                else
                {
                	incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getMailId() );
                }
                incomingRequest.put("SendQueue_sendtotype", "E");
                incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), sendTo).getMailId() );
                incomingRequest.put("SendQueue_action", EmailActionCodes.HTMLEMAIL);

                PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
                PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
                process.executeProcess(incomingRequest);
                this.status = process.getStatus() ;
            }
            else
            {
                this.setStatus(Status.SUCCEEDED);
                Log.debug(this, "No record will be written for this Order: " + poNumber );
            }
        }
        catch (Exception e)
        {
           this.setStatus(Status.FAILED);
            throw new TsaException("Error processing HTML vendor / order evaluation.");
        }
        return result;
    }
}