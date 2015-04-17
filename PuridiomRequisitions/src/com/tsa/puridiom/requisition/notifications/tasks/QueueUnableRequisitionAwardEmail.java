package com.tsa.puridiom.requisition.notifications.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

@SuppressWarnings(value = { "unchecked" })
public class QueueUnableRequisitionAwardEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;
        try
        {
        	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        	RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
        	PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")) ;
        	String supportEmail = propertiesManager.getProperty("MAILEVENTS", "ADMINEMAILADDR", "procurement@srr.gov");
        	BigDecimal icReqHeader =requisitionHeader.getIcReqHeader();
        	String purpose = requisitionHeader.getInternalComments();
        	Object obj =VendorManager.getInstance().getVendor((String)incomingRequest.get("organizationId"), requisitionHeader.getVendorId()) ;

        	String vendorName = "" ;
        	if (obj != null && obj instanceof Vendor) {
        		Vendor v = (Vendor) obj ;
        		vendorName = HiltonUtility.ckNull(v.getVendorName()) ;
        	}
        	String reqTotal = HiltonUtility.getCurrency(requisitionHeader.getTotal(), requisitionHeader.getCurrencyCode(), (String)incomingRequest.get("organizationId"), true);
        	StringBuffer subject = new StringBuffer("Puridiom Alert: Unable to auto-award Vinimaya Requisition: ");
            subject.append(requisitionHeader.getRequisitionNumber()) ;

            StringBuffer message = new StringBuffer("Requisition No: " + requisitionHeader.getRequisitionNumber() + "\n");
            message.append("Supplier ID: " + requisitionHeader.getVendorId() + "\n") ;
            message.append("Supplier Name: " + vendorName + "\n") ;
            message.append("Request Total: " + reqTotal + "\n\n");
            message.append("Comments: " + purpose);
            StringBuffer nextUser = new StringBuffer(supportEmail + ";");
            nextUser.append(HiltonUtility.ckNull(UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), requisitionHeader.getOwner()).getMailId()));
            if(!HiltonUtility.isEmpty(UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), requisitionHeader.getUdf6Code()).getMailId()))
            {
            	nextUser.append(";" + UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), requisitionHeader.getUdf6Code()).getMailId());
            }

            String owner = (String) incomingRequest.get("userId") ;
            incomingRequest.put("SendQueue_doctype", "REQ");
            incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("RequisitionHeader_icReqHeader"));
            incomingRequest.put("SendQueue_subject", subject.toString() );
            incomingRequest.put("SendQueue_messagetext", message.toString() );
            incomingRequest.put("SendQueue_owner", owner );
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getMailId() );
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_sendto", nextUser.toString());
            incomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
            process.executeProcess(incomingRequest);
            this.status = process.getStatus() ;
        }
        catch (Exception e)
        {
           this.setStatus(Status.FAILED);
           Log.error(this, e);
           throw new TsaException("Requisition Approval Email couldn't be processed.", e);
        }
        return result;
    }
}