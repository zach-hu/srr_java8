package com.tsa.puridiom.sendalert.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class SendAlertSetValues extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            SendAlert sendAlert = (SendAlert) incomingRequest.get("sendAlert");
            if (sendAlert == null)
            {
                sendAlert = new SendAlert();
            }

            if (incomingRequest.containsKey("SendQueue_queueid"))
            {
                String queueidString = (String) incomingRequest.get("SendQueue_queueid");
                if (Utility.isEmpty(queueidString))
                {
                    queueidString = "0";
                }
                BigDecimal queueid = new BigDecimal ( queueidString );
                sendAlert.setQueueid(queueid);
            }
            if (incomingRequest.containsKey("SendQueue_doctype"))
            {
                String doctype = (String ) incomingRequest.get("SendQueue_doctype");
                sendAlert.setDoctype(doctype);
            }
            if (incomingRequest.containsKey("SendQueue_docic"))
            {
                String docicString = (String) incomingRequest.get("SendQueue_docic");
                if (Utility.isEmpty(docicString))
                {
                    docicString = "0";
                }
                BigDecimal docic = new BigDecimal ( docicString );
                sendAlert.setDocic(docic);
            }
            if (incomingRequest.containsKey("SendQueue_subject"))
            {
                String subject = (String ) incomingRequest.get("SendQueue_subject");
                sendAlert.setSubject(subject);
            }
            if (incomingRequest.containsKey("SendQueue_messagetext"))
            {
                String messagetext = (String ) incomingRequest.get("SendQueue_messagetext");
                if(messagetext.length() > 4000)
                {
                	messagetext = messagetext.substring(0, 3999);
                }
                sendAlert.setMessagetext(messagetext);
            }
            if (incomingRequest.containsKey("SendQueue_owner"))
            {
                String owner = (String ) incomingRequest.get("SendQueue_owner");
                sendAlert.setOwner(owner);
            }
            if (incomingRequest.containsKey("SendQueue_sendfromtype"))
            {
                String sendfromtype = (String ) incomingRequest.get("SendQueue_sendfromtype");
                sendAlert.setSendfromtype(sendfromtype);
            }
            if (incomingRequest.containsKey("SendQueue_sendfrom"))
            {
                String sendfrom = (String ) incomingRequest.get("SendQueue_sendfrom");
                sendAlert.setSendfrom(sendfrom);
            }
            if (incomingRequest.containsKey("SendQueue_sendtotype"))
            {
                String sendtotype = (String ) incomingRequest.get("SendQueue_sendtotype");
                sendAlert.setSendtotype(sendtotype);
            }
            if (incomingRequest.containsKey("SendQueue_sendto"))
            {
                String sendto = (String ) incomingRequest.get("SendQueue_sendto");
                sendAlert.setSendto(sendto);
            }
            if (incomingRequest.containsKey("SendQueue_status"))
            {
                String status = (String ) incomingRequest.get("SendQueue_status");
                sendAlert.setStatus(status);
            }
            if (incomingRequest.containsKey("SendQueue_dateadded"))
            {
                String dateadded = (String ) incomingRequest.get("SendQueue_dateadded");
                sendAlert.setDateadded(dateadded);
            }
            if (incomingRequest.containsKey("SendQueue_timeadded"))
            {
                String timeadded = (String ) incomingRequest.get("SendQueue_timeadded");
                sendAlert.setTimeadded(timeadded);
            }
            if (incomingRequest.containsKey("SendQueue_action"))
            {
                String action = (String ) incomingRequest.get("SendQueue_action");
                sendAlert.setAction(action);
            }
            if (incomingRequest.containsKey("SendQueue_datesent"))
            {
                String datesent = (String ) incomingRequest.get("SendQueue_datesent");
                sendAlert.setDatesent(datesent);
            }
            if (incomingRequest.containsKey("SendQueue_timesent"))
            {
                String timesent = (String ) incomingRequest.get("SendQueue_timesent");
                sendAlert.setTimesent(timesent);
            }
            if (incomingRequest.containsKey("SendQueue_attachment"))
            {
                String attachment = (String ) incomingRequest.get("SendQueue_attachment");
                sendAlert.setAttachment(attachment);
            }
            if (incomingRequest.containsKey("SendQueue_vendorId"))
            {
                String vendorId = (String ) incomingRequest.get("SendQueue_vendorId");
                sendAlert.setVendorId(vendorId);
            }
            if (incomingRequest.containsKey("SendQueue_attempts"))
            {
                String attempts = (String ) incomingRequest.get("SendQueue_attempts");
                if (Utility.isEmpty(attempts))
                {
                    attempts = "0";
                }
                sendAlert.setAttempts(new BigDecimal(attempts));
            }
            if (incomingRequest.containsKey("SendQueue_errorText"))
            {
                String errorText = (String ) incomingRequest.get("SendQueue_errorText");
                sendAlert.setErrorText(errorText);
            }
            if (incomingRequest.containsKey("SendQueue_args"))
            {
                String args = (String ) incomingRequest.get("SendQueue_args");
                sendAlert.setArgs(args);
            }

            result = sendAlert;
            this.status = Status.SUCCEEDED;
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw e;
        }
        return result;
    }
}