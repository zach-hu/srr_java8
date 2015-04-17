package com.tsa.puridiom.vendorregister.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.VendorRegister;
import com.tsa.puridiom.organization.OrganizationManager;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class VendorRegisterRegistrationNotification extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            VendorRegister vendorRegister = (VendorRegister) incomingRequest.get("vendorRegister");
            String	organizationId = (String) incomingRequest.get("organizationId");
            String	adminEmail = PropertiesManager.getInstance(organizationId).getProperty("MAILEVENTS", "ADMINEMAILADDR", "support@tsagate.com");
            String	vendorAdminEmail = PropertiesManager.getInstance(organizationId).getProperty("MAILEVENTS", "VENDORADMINEMAIL", "");

            incomingRequest.put("SendQueue_action", "EN");
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendfrom", adminEmail);
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_sendto", vendorAdminEmail);
            incomingRequest.put("SendQueue_doctype", "");
            incomingRequest.put("SendQueue_docic", "");
            incomingRequest.put("SendQueue_subject", OrganizationManager.getInstance().getOrganizationName(organizationId) + " Bid Board Registration Notification" );
            incomingRequest.put("SendQueue_messagetext", vendorRegister.getContactDisplayName() + " [" + vendorRegister.getComp_id().getContactEmailAddr() + "] has registered as a new vendor: " + vendorRegister.getVendorName() + " [" + vendorRegister.getComp_id().getVendorId() + "] on " + HiltonUtility.getFormattedDate(vendorRegister.getDateEntered(), organizationId) +".");

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