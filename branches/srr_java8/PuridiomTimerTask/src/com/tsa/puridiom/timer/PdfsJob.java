package com.tsa.puridiom.timer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.emails.EmailManager;
import com.tsa.puridiom.emails.PdfsJobUtilities;
import com.tsa.puridiom.emails.exception.EmailsException;
import com.tsa.puridiom.entity.SendQueue;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;

public class PdfsJob extends ScheduledJobAction
{
	private String jobType = "pdfs";

	public void processAction(Object objectToProcess)
	{
		SendQueue sendQueue = (SendQueue)objectToProcess;
		if(HiltonUtility.isEmpty(sendQueue.getSendto()))
		{
			this.errorEmail(Status.FAILED, sendQueue, "SendTo was empty");
		}
		else
		{
			try
			{
				Map object = new HashMap();
				object.put("sendQueue", sendQueue);
				object.put("organizationId", this.getOrganizationId());

				List pdfObject = GetPdfToEmailManager.getPDF(object);


	        	int updateSendQueue = this.sendPdf(pdfObject.get(0), sendQueue, pdfObject.get(1));
	        	this.updateSendQueue(updateSendQueue, null, sendQueue);

			}
			catch (Exception e)
			{
				this.updateSendQueue(Status.FAILED, null, sendQueue);
				this.errorEmail(Status.FAILED, sendQueue, "PDF Email failed for: Queueid:" + sendQueue.getQueueid().toString() + ",\r\n Subject" + sendQueue.getSubject() + "\r\n" + e.getMessage());
			}
		}
	}

	public int sendPdf(Object tmpFile, SendQueue sendQueue, Object messageText)
	{
		Log.debug(this, "send pdf file: " + tmpFile);
		int ret = Status.FAILED;
		String results = "";
        try
		{
        	if (sendQueue.getDoctype().equalsIgnoreCase("RFQ") || sendQueue.getDoctype().toUpperCase().startsWith("LT"))
        	{
        		List rfqPdf = (List)tmpFile;
        		boolean isEmailActiveBySendToUser = this.checkEmailActiveBySendToUsers(sendQueue.getSendto());

				if (isEmailActiveBySendToUser) {
					for (int i = 0; i < rfqPdf.size(); i++)
	        		{
		        			Object tempObj[] = (Object[])rfqPdf.get(i);
		        			Log.debug(this, "pdf file: " + tempObj[0]);
		    	            results = EmailManager.getInstance().sendEmail(sendQueue.getSendfrom(), (String)tempObj[2], null, "",sendQueue.getSubject(), (String)tempObj[1], tempObj[0], this.getOrganizationId(), this.getJobType());
	        		}
				}
				else
				{
					this.updateSenqQueueEmailActive(sendQueue);
				}

        		ret = Status.SUCCEEDED;
        	}
        	else
        	{
        		String bcc = "";

        		if(sendQueue.getAction().equalsIgnoreCase(SendQueue.PRINT_PDF_ACTION))
        		{
    				bcc = PdfsJobUtilities.getExtraBcc(this.getOrganizationId(), sendQueue.getSendfrom());
        		}

        		boolean isEmailsActiveBySendToUser = this.checkEmailActiveBySendToUsers(sendQueue.getSendto());

				if (isEmailsActiveBySendToUser)
				{
					results = EmailManager.getInstance().sendEmail(sendQueue.getSendfrom(), sendQueue.getSendto(), null, bcc, sendQueue.getSubject(), (String)messageText, tmpFile, this.getOrganizationId(), this.getJobType());
				}
				else
				{
					this.updateSenqQueueEmailActive(sendQueue);
				}

				ret = Status.SUCCEEDED;
        	}
		}
		catch (EmailsException e)
		{
			this.errorEmail(Status.FAILED, sendQueue, "Pdf Email failed for: Queueid:" + sendQueue.getQueueid().toString() + ",\r\n Subject" + sendQueue.getSubject() + "\r\n" + e.getMessage() + "\r\n\r\n" + results);
			ret = Status.FAILED;
			e.printStackTrace();
		}
		return ret;
	}

	protected void setAction(Map incomingRequest)
	{
		incomingRequest.put("SendQueue_action", SendQueue.PRINT_PDF_ACTION);
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
}
