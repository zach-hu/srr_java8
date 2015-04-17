package com.tsa.puridiom.vendor.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

public class VendorUpdateNotification extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            Vendor vendor = (Vendor) incomingRequest.get("vendor");
            String	organizationId = (String) incomingRequest.get("organizationId");
            String	adminEmail = PropertiesManager.getInstance(organizationId).getProperty("MAILEVENTS", "ADMINEMAILADDR", "support@tsagate.com");
            String	sendTo = UserManager.getInstance().getUser(organizationId, vendor.getOwner()).getMailId();
            String defaultTZ =TimeZone.getDefault().getID();

            if (HiltonUtility.isEmpty(sendTo)) {
                sendTo = PropertiesManager.getInstance(organizationId).getProperty("MAILEVENTS", "VENDORADMINEMAIL", "");
            }
            if (HiltonUtility.isEmpty(sendTo)) {
            	this.status = Status.SUCCEEDED;
                return null;
            }

            Date lastChangeDate = vendor.getLastChange();

            if (HiltonUtility.isEmpty(String.valueOf(lastChangeDate))) {
                lastChangeDate = Dates.getDate(Dates.today("", defaultTZ));
            }

            incomingRequest.put("SendQueue_action", "EN");
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendfrom", adminEmail);
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_sendto", sendTo);
            incomingRequest.put("SendQueue_doctype", "");
            incomingRequest.put("SendQueue_docic", "");
            incomingRequest.put("SendQueue_subject", vendor.getVendorName() + " Profile Updated" );
            incomingRequest.put("SendQueue_messagetext", "The profile information for " + vendor.getVendorName() + " [" + vendor.getVendorId() + "] was updated by the vendor on " + HiltonUtility.getFormattedDate(lastChangeDate, organizationId) + " " + defaultTZ + ".");

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