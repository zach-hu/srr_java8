/**
 *
 */
package com.tsa.puridiom.timer;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.cxml.entity.CXML;
import com.tsa.puridiom.emails.EmailManager;
import com.tsa.puridiom.emails.exception.EmailsException;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny Zapana
 */
public class SendQueueCXMLJob extends ScheduledJobAction
{
	public void processAction(Object objectToProcess)
	{
		SendQueue sendQueue = (SendQueue) objectToProcess;
		Map incomingRequest = new HashMap();
		int status;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess poRetrieveProcess = processLoader.loadProcess("po-retrieve.xml");
			PuridiomProcess cxmlProcess = processLoader.loadProcess("cxml-builder.xml");
			PoHeader poHeader;
			CXML cXMLResponse;
			UserManager userManager = UserManager.getInstance();
			UserProfile buyer;
			UserProfile requisitioner;
			String[] userMailId;
			String poBusinessKey;
			String poDisplayNumber;

			incomingRequest.put("organizationId", this.getOrganizationId());

			incomingRequest.put("PoHeader_icPoHeader", String.valueOf(sendQueue.getDocic()));

			incomingRequest.put("sendQueue", sendQueue);

			poRetrieveProcess.executeProcess(incomingRequest);
			
			cxmlProcess.executeProcess(incomingRequest);

			poHeader = (PoHeader) incomingRequest.get("poHeader");

			cXMLResponse = (CXML) incomingRequest.get("cXMLResponse");

			poBusinessKey = this.getPoBusinessKey(poHeader);

			poDisplayNumber = poHeader.getDisplayPoNumber().toString();

			sendQueue.setErrorText(cXMLResponse.getResponse().getStatus().getText());

			if ((cxmlProcess.getStatus() == Status.SUCCEEDED) && (cXMLResponse.getResponse().getStatus().getCode().toString().equals(CXML.OK_STATUS_CODE)))
			{
				if (!HiltonUtility.isEmpty(sendQueue.getSendto()))
				{
					buyer = userManager.getUser(this.getOrganizationId(), poHeader.getBuyerCode());

					requisitioner = userManager.getUser(this.getOrganizationId(), poHeader.getRequisitionerCode());

					userMailId = new String[] { buyer.getMailId(), requisitioner.getMailId() };

					this.sendEmail(sendQueue, userMailId, poBusinessKey, poDisplayNumber);
				}

			} else
			{
				throw new Exception("Order: " + poBusinessKey + " NOT sent to supplier. \n The cXML order has NOT been electronically accepted by the supplier.\n"
						+ cXMLResponse.getResponse().getStatus().getText());
			}

			Log.debug(this, "SendQueue QUEUEID: " + sendQueue.getQueueid().toString() + " was processing successfully");

			status = Status.SUCCEEDED;

		} catch (Exception e)
		{
			e.printStackTrace();

			Log.error(this, "Error processing SendQueue QUEUEID: " + sendQueue.getQueueid().toString() + "\r\n" + e.getMessage());

			sendQueue.setErrorText("Error processing: " + e.getMessage());

			status = Status.FAILED;

			this.sendErrorEmail(sendQueue, e.getMessage());
		}

		this.updateSendQueue(status, sendQueue);
	}

	private void sendEmail(SendQueue sendQueue, String[] sendTo, String poBusinessKey, String poDisplayNumber) throws Exception
	{
		StringBuffer subject = new StringBuffer();
		StringBuffer messageText = new StringBuffer();

		subject.append("Order: ");
		subject.append(poBusinessKey);
		subject.append(" sent to supplier.");

		messageText.append("The cXML ");
		messageText.append(poDisplayNumber);
		messageText.append(" has been electronically accepted by the supplier.");

		try
		{
			for (int i = 0; i < sendTo.length; i++)
			{
				EmailManager.getInstance().sendEmail(sendQueue.getSendfrom(), sendTo[i], null, subject.toString(), messageText.toString(), null, this.getOrganizationId());
			}

			Log.debug(this, "Emails to SendQueue QUEUEID: " + sendQueue.getQueueid().toString() + " were sent successfully");
		} catch (EmailsException e)
		{
			e.printStackTrace();

			Log.error(this, "Error sending emails for SendQueue QUEUEID: " + sendQueue.getQueueid().toString() + "\r\n" + e.getMessage());

			throw e;
		}
	}

	private void sendErrorEmail(SendQueue sendQueue, String exceptionMessage)
	{
		StringBuffer text = new StringBuffer();

		text.append(" Queue ID: " + sendQueue.getQueueid() + "\r\n");
		text.append(" Document Type: " + sendQueue.getDoctype() + "\r\n");
		text.append(" Document IC: " + sendQueue.getDocic() + "\r\n");
		text.append(" Subject: " + sendQueue.getSubject() + "\r\n");
		text.append(" Action: " + sendQueue.getAction() + "\r\n");
		text.append(" Status: " + sendQueue.getStatus() + "\r\n");

		text.append("SendQueueCXMLJob failed.\r\n");

		text.append("reason: ");
		text.append(exceptionMessage);

		EmailManager.getInstance().sendErrorEmail(text.toString(), this.getOrganizationId());
	}

	private String getPoBusinessKey(PoHeader poHeader)
	{
		StringBuffer returnValue = new StringBuffer();

		returnValue.append(poHeader.getPoNumber() + "/");

		if (poHeader.getReleaseNumber() != null)
		{
			returnValue.append(poHeader.getReleaseNumber().toString());
		}

		return returnValue.toString();
	}

	protected void setAction(Map incomingRequest)
	{
		incomingRequest.put("SendQueue_action", SendQueue.CXML_EMAIL_ACTION);
	}

	protected void updateSendQueue(int status, SendQueue sendQueue)
	{
		Map incomingRequest = new HashMap();

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("sendqueue-update.xml");

			incomingRequest.put("organizationId", this.getOrganizationId());

			if (status == Status.SUCCEEDED)
			{
				sendQueue.setStatus(DocumentStatus.SENDQUEUE_PROCESSED);
				sendQueue.setErrorText("Email Succesfully Sent");
			} else
			{
				sendQueue.setStatus(DocumentStatus.SENDQUEUE_ERROR);

				if (HiltonUtility.isEmpty(sendQueue.getErrorText()))
				{
					sendQueue.setErrorText("An error occurred sending email.");
				}
			}

			sendQueue.setDateTimeSent();
			sendQueue.setAttempts(sendQueue.getAttempts().add(new BigDecimal(1)));

			incomingRequest.put("sendQueue", sendQueue);

			process.executeProcess(incomingRequest);

			Log.debug(this, "Update of SendQueue QUEUEID: " + sendQueue.getQueueid().toString() + " was successfully");
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();

			Log.error(this, "Error updating SendQueue QUEUEID: " + sendQueue.getQueueid().toString() + "\r\n" + e.getMessage());
		}
	}

}
