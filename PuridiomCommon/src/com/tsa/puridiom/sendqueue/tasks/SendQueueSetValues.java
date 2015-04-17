package com.tsa.puridiom.sendqueue.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class SendQueueSetValues extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            SendQueue sendQueue = (SendQueue) incomingRequest.get("sendQueue");
            if (sendQueue == null)
            {
                sendQueue = new SendQueue();
            }

            if (incomingRequest.containsKey("SendQueue_queueid"))
            {
                String queueidString = (String) incomingRequest.get("SendQueue_queueid");
                if (Utility.isEmpty(queueidString))
                {
                    queueidString = "0";
                }
                BigDecimal queueid = new BigDecimal ( queueidString );
                sendQueue.setQueueid(queueid);
            }
            if (incomingRequest.containsKey("SendQueue_doctype"))
            {
                String doctype = (String ) incomingRequest.get("SendQueue_doctype");
                sendQueue.setDoctype(doctype);
            }
            if (incomingRequest.containsKey("SendQueue_docic"))
            {
                String docicString = (String) incomingRequest.get("SendQueue_docic");
                if (Utility.isEmpty(docicString))
                {
                    docicString = "0";
                }
                BigDecimal docic = new BigDecimal ( docicString );
                sendQueue.setDocic(docic);
            }
            if (incomingRequest.containsKey("SendQueue_subject"))
            {
                String subject = (String ) incomingRequest.get("SendQueue_subject");
                sendQueue.setSubject(subject);
            }
            if (incomingRequest.containsKey("SendQueue_messagetext"))
            {
                String messagetext = (String ) incomingRequest.get("SendQueue_messagetext");
                sendQueue.setMessage(messagetext);
            }
            if (incomingRequest.containsKey("SendQueue_messagetext2"))
			{
				String messagetext2 = (String) incomingRequest.get("SendQueue_messagetext2");
				sendQueue.setMessagetext2(messagetext2);
			}
            if (incomingRequest.containsKey("SendQueue_owner"))
            {
                String owner = (String ) incomingRequest.get("SendQueue_owner");
                sendQueue.setOwner(owner);
            }
            if (incomingRequest.containsKey("SendQueue_sendfromtype"))
            {
                String sendfromtype = (String ) incomingRequest.get("SendQueue_sendfromtype");
                sendQueue.setSendfromtype(sendfromtype);
            }
            if (incomingRequest.containsKey("SendQueue_sendfrom"))
            {
                String sendfrom = (String ) incomingRequest.get("SendQueue_sendfrom");
                sendQueue.setSendfrom(sendfrom);
            }
            if (incomingRequest.containsKey("SendQueue_sendtotype"))
            {
                String sendtotype = (String ) incomingRequest.get("SendQueue_sendtotype");
                sendQueue.setSendtotype(sendtotype);
            }
            if (incomingRequest.containsKey("SendQueue_sendto"))
            {
                String sendto = (String ) incomingRequest.get("SendQueue_sendto");
                sendQueue.setSendto(sendto);
            }
            if (incomingRequest.containsKey("SendQueue_status"))
            {
                String status = (String ) incomingRequest.get("SendQueue_status");
                sendQueue.setStatus(status);
            }
            if (incomingRequest.containsKey("SendQueue_dateadded"))
            {
                String dateadded = (String ) incomingRequest.get("SendQueue_dateadded");
                sendQueue.setDateadded(dateadded);
            }
            if (incomingRequest.containsKey("SendQueue_timeadded"))
            {
                String timeadded = (String ) incomingRequest.get("SendQueue_timeadded");
                sendQueue.setTimeadded(timeadded);
            }
            if (incomingRequest.containsKey("SendQueue_action"))
            {
                String action = (String ) incomingRequest.get("SendQueue_action");
                sendQueue.setAction(action);
            }
            if (incomingRequest.containsKey("SendQueue_datesent"))
            {
                String datesent = (String ) incomingRequest.get("SendQueue_datesent");
                sendQueue.setDatesent(datesent);
            }
            if (incomingRequest.containsKey("SendQueue_timesent"))
            {
                String timesent = (String ) incomingRequest.get("SendQueue_timesent");
                sendQueue.setTimesent(timesent);
            }
            if (incomingRequest.containsKey("SendQueue_attachment"))
            {
                String attachment = (String ) incomingRequest.get("SendQueue_attachment");
                sendQueue.setAttachment(attachment);
            }
            if (incomingRequest.containsKey("SendQueue_vendorId"))
            {
                String vendorId = (String ) incomingRequest.get("SendQueue_vendorId");
                sendQueue.setVendorId(vendorId);
            }
            if (incomingRequest.containsKey("SendQueue_attempts"))
            {
                String attempts = (String ) incomingRequest.get("SendQueue_attempts");
                if (Utility.isEmpty(attempts))
                {
                    attempts = "0";
                }
                sendQueue.setAttempts(new BigDecimal(attempts));
            }
            if (incomingRequest.containsKey("SendQueue_errorText"))
            {
                String errorText = (String ) incomingRequest.get("SendQueue_errorText");
                sendQueue.setErrorText(errorText);
            }
            if (incomingRequest.containsKey("SendQueue_args"))
            {
                String args = (String ) incomingRequest.get("SendQueue_args");
                sendQueue.setArgs(args);
            }

            result = sendQueue;
            this.status = Status.SUCCEEDED;
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            
            e.printStackTrace();
            throw e;
        }
        return result;
    }
}