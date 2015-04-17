package com.tsa.puridiom.report.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.entity.ReportQueue;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class ReportExtensiveMail extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			ReportQueue reportQueue = (ReportQueue)incomingRequest.get("reportQueue");

    		StringBuffer subject = new StringBuffer("[MSG] ");
            subject.append(reportQueue.getAlias());
            subject.append("  -  NOTICE  -  REPORT TOO EXTENSIVE - MUST SCHEDULE");

            StringBuffer message = new StringBuffer("Your report exceeded the maximum amount of data to be retrieved during normal business hours.");
            message.append("  Rather than impacting the Puridiom response time for all users, the report has been cut off at 10,000 rows.\n\r");
            message.append("In order to receive a report in full (> 10,000 rows), you must \"Schedule\" it to run during off-hours.\n\r");
            message.append("To schedule, go to Reports, select a report and enter any necessary filter options.  Select \"Schedule Options\" ");
            message.append("link in lower section of page.  Choose the Frequency, Start Date, End Date and Time the report should run.  ");
            message.append("It defaults to 00:05 AM, to run after midnight.  As long as the time falls between 12:00 AM and 5:00 AM, ");
            message.append("the full report will be generated and emailed to you.  Select Submit..");

            incomingRequest.put("SendQueue_doctype", "REP");
            incomingRequest.put("SendQueue_docic", reportQueue.getIcReportQueue().toString());
            incomingRequest.put("SendQueue_subject", subject.toString());
            incomingRequest.put("SendQueue_messagetext", message.toString());
            incomingRequest.put("SendQueue_owner", "PURIDIOM" );
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendfrom", reportQueue.getSendFrom() );
            incomingRequest.put("SendQueue_sendtotype", "E");
            String isReportQueueJob=(String)incomingRequest.get("isReportQueueJob");
            if(isReportQueueJob==null)
            {
            	String ReportQueuesendT=(String)incomingRequest.get("ReportQueue_sendT");
            	incomingRequest.put("SendQueue_sendto", ReportQueuesendT );
            }else{
            	incomingRequest.put("SendQueue_sendto", reportQueue.getSendTo() );
            }


            incomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
            process.executeProcess(incomingRequest);



            this.status = process.getStatus() ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			e.printStackTrace();
			Log.error(this, e.getMessage());
			throw e;
		}
		return result;
	}
}
