package com.tsa.puridiom.requisition.tasks;

import java.util.Map;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class QueueAddReqFaxPdf extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String oid = (String)incomingRequest.get("organizationId");

            incomingRequest.put("SendQueue_action", "XP");

            String	domain = PropertiesManager.getInstance(oid).getProperty("FAX", "Domain", "") ;
            String	gateway = PropertiesManager.getInstance(oid).getProperty("FAX", "Gateway", "") ;
            String	addressFormat = PropertiesManager.getInstance(oid).getProperty("FAX", "AddressFormat", "[FaxNumber]@[EmailDomain]") ;
            String	subjectFormat = PropertiesManager.getInstance(oid).getProperty("FAX", "SubjectFormat", "") ;
            String	messageFormat = PropertiesManager.getInstance(oid).getProperty("FAX", "MessageFormat", "") ;
            String	password = PropertiesManager.getInstance(oid).getProperty("FAX", "passwd", "") ;

            String	allowSpaces = PropertiesManager.getInstance(oid).getProperty("FAX", "AllowSpaces", "N") ;
            String	allowPerens = PropertiesManager.getInstance(oid).getProperty("FAX", "AllowPerens", "N") ;
            String	allowDashes = PropertiesManager.getInstance(oid).getProperty("FAX", "AllowDashes", "N") ;

            String faxNumber = (String) incomingRequest.get("faxNumber") ;
            String faxToName = (String) incomingRequest.get("faxToName") ;
            String faxToCompany = (String) incomingRequest.get("faxToCompany") ;
            String faxSubject = (String) incomingRequest.get("faxSubject") ;
            String faxMessage = (String) incomingRequest.get("faxMessage") ;

            faxNumber = this.faxNumberFormat(faxNumber, allowSpaces, allowPerens, allowDashes) ;
            String faxAddress = this.faxFormat(addressFormat, domain, gateway, password, faxNumber, faxToName, faxToCompany, faxSubject) ;
            faxSubject = this.faxFormat(subjectFormat, domain, gateway, password, faxNumber, faxToName, faxToCompany, faxSubject) ;
            faxMessage = this.faxFormat(messageFormat, domain, gateway, password, faxNumber, faxToName, faxToCompany, faxSubject) ;

            String email = faxNumber ;
            StringBuffer subject = new StringBuffer(faxSubject);


            if(Utility.isEmpty(email))
            {
                email = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("MAILEVENTS", "AdminEmailAddr", "support@puridiom.com");

                subject.append("Error-" +"The system counld not email Pdf -" + (String)incomingRequest.get("RequisitionHeader_requisitionNumber")) ;
            }
            incomingRequest.put("SendQueue_subject", subject.toString() );
            incomingRequest.put("SendQueue_sendto", email);
            String sendFrom = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), (String)incomingRequest.get("userId")).getMailId();
            if(Utility.isEmpty(sendFrom))
            {
            	sendFrom = PropertiesManager.getInstance(oid).getProperty("MAILEVENTS", "AdminEmailAddr", "");
            }
            incomingRequest.put("SendQueue_sendfrom", sendFrom);
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_doctype", "REQ");
            incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("RequisitionHeader_icReqHeader"));

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
            process.executeProcess(incomingRequest);
            this.status = process.getStatus() ;
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw new TsaException("QueueAddReqPdf failed! " + e.getMessage(), e );
        }
        return result;
    }

    public String faxNumberFormat(String faxNumber, String allowSpaces, String allowPerens, String allowDashes) {

        if (allowSpaces == "N") faxNumber = faxNumber.replaceAll(" ", "") ;
        if (allowPerens == "N") faxNumber = faxNumber.replaceAll("(", "") ;
        if (allowPerens == "N") faxNumber = faxNumber.replaceAll(")", "") ;
        if (allowDashes == "N") faxNumber = faxNumber.replaceAll("-", "") ;

    	return faxNumber ;
    }

    public String faxFormat(String fmt, String domain, String gateway, String passwd, String faxNumber, String faxToName, String faxToCompany, String faxSubject) {

        String data =  fmt.replaceAll("[FAXTONAME]", faxToName) ;
        data = data.replaceAll("[FAXCOMPANY]", faxToCompany) ;
        data = data.replaceAll("[FAXNUMBER]", faxNumber) ;
        data = data.replaceAll("[GATEWAY]",gateway ) ;
        data = data.replaceAll("[DOMAIN]",domain ) ;
        data = data.replaceAll("[SUBJECT]",faxSubject) ;
        data = data.replaceAll("[PASSWORD]",passwd) ;

    	return data ;
    }

}