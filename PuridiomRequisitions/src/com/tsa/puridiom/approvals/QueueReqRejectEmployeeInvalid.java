package com.tsa.puridiom.approvals;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.apppooluser.tasks.AppPooluserRetrieveByPool;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.AppPooluser;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class QueueReqRejectEmployeeInvalid extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String organizationId = (String)incomingRequest.get("organizationId");
            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
            RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            String requisitionNumber = (String) rqh.getRequisitionNumber();
            String employeeNo = HiltonUtility.ckNull((String) incomingRequest.get("employeeNo"));
            String owner = HiltonUtility.ckNull((String) rqh.getOwner());
            
            String sendTo = "";
            String enableRejectedEmail = propertiesManager.getProperty("FDCS", "ENABLE REJECTED EMAIL TO EMPLOYEE", "N");
            if (enableRejectedEmail.equals("Y")) 
            {
            	sendTo = HiltonUtility.ckNull((String) rqh.getOwner());
                if (HiltonUtility.isEmpty(owner))
                {
                	sendTo = "support@puridiom.com";
                }
            } 
            else 
            {
            	sendTo = "support@puridiom.com";
            }

            incomingRequest.put("SendQueue_doctype", "REQ");
            incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("RequisitionHeader_icReqHeader"));
            
            String subject = "Requisition " + requisitionNumber + " Procurement Action";
            String messagetext = "Requisition " + requisitionNumber + " rejected by FDCS. Employee# " + employeeNo + " is Invalid.";
            incomingRequest.put("SendQueue_subject",subject );
            incomingRequest.put("SendQueue_messagetext",messagetext );
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendfrom", owner );
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_sendto", sendTo);
            incomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
            process.executeProcess(incomingRequest);
            this.status = process.getStatus() ;
        }
        catch (Exception e)
        {
           this.setStatus(Status.FAILED);
            throw new TsaException("Error processing HTML approvals.");
        }
        return result;
    }
}