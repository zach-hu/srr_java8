package com.tsa.puridiom.vendorregister.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.VendorRegister;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.TimeZone;

public class VendorRegisterUpdateNotification extends Task
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
            String	sendTo = "";
            String defaultTZ = TimeZone.getDefault().getID();

            String vendorNotificationCol = PropertiesManager.getInstance(organizationId).getProperty("MAILEVENTS", "VENDORADMINCOL", "");

            if (!HiltonUtility.isEmpty(vendorNotificationCol)) {
                Class cls = vendorRegister.getClass() ;
            	Method mth = cls.getMethod("get" + vendorNotificationCol,null);
				String 	value = (String) mth.invoke(vendorRegister,null);
				if (!HiltonUtility.isEmpty(value)) {
				    sendTo = PropertiesManager.getInstance(organizationId).getProperty("MAILEVENTS", "VENDORADMINEMAIL-" + value, "");
				}
            }

            if (HiltonUtility.isEmpty(sendTo)) {
                sendTo = PropertiesManager.getInstance(organizationId).getProperty("MAILEVENTS", "VENDORADMINEMAIL", "");
            }
            if (HiltonUtility.isEmpty(sendTo)) {
                return null;
            }

            incomingRequest.put("SendQueue_action", "EN");
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendfrom", adminEmail);
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_sendto", sendTo);
            incomingRequest.put("SendQueue_doctype", "");
            incomingRequest.put("SendQueue_docic", "");
            incomingRequest.put("SendQueue_subject", vendorRegister.getVendorName() + " Pre-Qualification Information Updated" );
            incomingRequest.put("SendQueue_messagetext", "The pre-qualification information for " + vendorRegister.getVendorName() + " [" + vendorRegister.getComp_id().getVendorId() + "] was updated by the vendor on " + HiltonUtility.getFormattedDate(Dates.today("", defaultTZ), organizationId) + " " + defaultTZ + ".");

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