package com.tsa.puridiom.approvals;

import java.util.Map;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class QueueAddChangeRequestRequisitionerEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String	organizationId = (String)incomingRequest.get("organizationId");
            String  userId = (String) incomingRequest.get("userId");
            RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            PoHeader poh = (PoHeader)incomingRequest.get("poHeader");

            if(rqh == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " RequisitionHeader was not found!");
            }
            if(poh == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " PoHeader was not found!");
            }
            if(Utility.isEmpty(userId))
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " Current user id was not found!");
            }
            String	requisitionNumber = rqh.getRequisitionNumber();
            String	requisitionerCode = rqh.getRequisitionerCode();
            if(requisitionNumber == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " RequisitionNumber was not found!");
            }
            if (Utility.isEmpty(requisitionerCode))
            {
                requisitionerCode = rqh.getOwner();
                if (Utility.isEmpty(requisitionerCode))
                {
	                this.setStatus(Status.FAILED);
	                throw new TsaException(this.getName() + " Requisitioner / Owner were not found.  Email record cannot be written to notify the requisitioner that the requisition has been approved by the backup approver!");
                }
            }

            StringBuffer subject = new StringBuffer("");
            incomingRequest.put("SendQueue_doctype", "REQ");
            incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("RequisitionHeader_icReqHeader"));

            if (rqh != null)
			{
				if (rqh.getRequisitionType().equals("C"))
				{
					subject.append("Requisition #" + poh.getRequisitionNumber() + " has been placed on Order #" + poh.getPoNumber());
					if (poh.getRevisionNumber().intValue() > 0)
						subject.append(" Revision " + poh.getRevisionNumber().intValue());
					if (!HiltonUtility.isEmpty(poh.getVendorId()))
						subject.append(" to Supplier " + VendorManager.getInstance().getVendorName(organizationId, poh.getVendorId()));
				}
			}
            subject.append(".");

            incomingRequest.put("SendQueue_subject",subject.toString());
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser(organizationId, userId).getMailId());
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser(organizationId, requisitionerCode).getMailId());
            incomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);
            incomingRequest.put("SendQueue_messagetext", subject.toString());

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
            process.executeProcess(incomingRequest);
            this.status = process.getStatus() ;
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw e;
        }
        return result;
    }
}