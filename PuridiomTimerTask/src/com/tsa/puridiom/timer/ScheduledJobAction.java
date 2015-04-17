package com.tsa.puridiom.timer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.xerces.internal.impl.PropertyManager;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.emails.EmailManager;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;

public class ScheduledJobAction extends ScheduledJob
{
	public List getActionList()
	{
		List actionList = new ArrayList();
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("sendqueue-retrieve-by-action-status.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("SendQueue_status", DocumentStatus.SENDQUEUE_NEW);

			setAction(incomingRequest);

			process.executeProcess(incomingRequest);
			if(process.getStatus() == Status.SUCCEEDED)
			{
				actionList = (List)incomingRequest.get("alertsList");
			}
			else
			{
				this.errorEmail(Status.FAILED, null, "ActionList was not retrieved");
			}
		}
		catch (Exception e)
		{
			Log.error(this, "Action could not be executed: " + e.getMessage());
			this.errorEmail(Status.FAILED, null, e.getMessage());
		}
		return actionList;
	}
	protected void updateSendQueue(int status, Map reportsIncomingRequest, SendQueue sendQueue)
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
		PuridiomProcess updateSQ = null;
		try
		{
			updateSQ = processLoader.loadProcess("sendqueue-update.xml");
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
			return;
		}
		Map sqIncomingRequest = new HashMap();
		sqIncomingRequest.put("organizationId", this.getOrganizationId());


		if(status == Status.SUCCEEDED)
		{
			sendQueue.setStatus(DocumentStatus.SENDQUEUE_PROCESSED);
			sendQueue.setErrorText("Email Succesfully Sent");
		}
		else
		{
			sendQueue.setStatus(DocumentStatus.SENDQUEUE_ERROR);
			sendQueue.setErrorText("An error occurred sending email.");
		}

		sendQueue.setDateTimeSent();
		sendQueue.setAttempts(sendQueue.getAttempts().add(new BigDecimal(1)));

		sqIncomingRequest.put("sendQueue", sendQueue);
		try
		{
			updateSQ.executeProcess(sqIncomingRequest);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void errorEmail(int status, SendQueue sq, String exceptionMessage)
	{
		this.updateSendQueue(status, null, sq);
		StringBuffer text = new StringBuffer();
		if(sq != null)
		{
			text.append(sq.getSubject());
			text.append(" could not be send.\r\n");
		}
		else
		{
			text.append("EmailHtmlApprovalsJob failed.\r\n");
		}
		text.append("reason: ");
		text.append(exceptionMessage);
		EmailManager.getInstance().sendErrorEmail(text.toString(), this.getOrganizationId());
	}

	protected PuridiomProcess getProcessForActions()
	{
		PuridiomProcess process = null;
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			process = processLoader.loadProcess(this.getActionsProcessName());
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		return process;
	}

	public String getActionsProcessName()
	{
		return "html-email-approvals.xml";
	}
	public void execute()
	{
		Log.debug(this, this.getClass().getName() + " Job starts");
		this.executeActions();
		Log.debug(this, "Job done");
	}

	protected void executeActions()
	{
		try
		{
			List actionList = this.getActionList();
			System.out.println("sq found: " + actionList.size() + " records");
			for (Iterator iter = actionList.iterator(); iter.hasNext();)
			{
				SendQueue sq = (SendQueue)iter.next();
				System.out.println("Processing queue:" + sq.getQueueid() + "- Subject: " + sq.getSubject());
				this.processAction(sq);
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			System.out.println(this.getClass().getName() + "job done");
		}
	}

	public void processAction(Object objectToProcess)
	{
		//PuridiomProcess process = getProcessForActions();
	}

	public void updateSenqQueueEmailActive(SendQueue sendQueue) {
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
		PuridiomProcess updateSQ = null;
		try
		{
			updateSQ = processLoader.loadProcess("sendqueue-update.xml");
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
			return;
		}
		Map sqIncomingRequest = new HashMap();
		sqIncomingRequest.put("organizationId", this.getOrganizationId());

		sendQueue.setStatus(DocumentStatus.SENDQUEUE_PROCESSED);
		sendQueue.setErrorText("Email was not sent because the sendto user have setup EmailActive = N");
		sendQueue.setDateTimeSent();
		sendQueue.setAttempts(sendQueue.getAttempts().add(new BigDecimal(1)));

		sqIncomingRequest.put("sendQueue", sendQueue);
		try
		{
			updateSQ.executeProcess(sqIncomingRequest);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean checkEmailActiveBySendToUsers(String sendToUsers) {
		boolean isEmailActiveBySendToUser = false;
		UserProfile user = null;
		String[] sendToUsersArrays;

//		String isPSEmailsActive = PropertiesManager.getInstance(
//				this.getOrganizationId()).getProperty("PURIDIOM SERVICES","SEND EMAILS ACTIVE", "N");

//		if (isPSEmailsActive.equalsIgnoreCase("N")) 
//		{
//			return true; 
//		}
		
		if ( !HiltonUtility.isEmpty(sendToUsers)) {

			sendToUsersArrays = sendToUsers.split(";");

			try {
				for (int index = 0 ; index < sendToUsersArrays.length ; index++) {
					user = (UserProfile) UserManager.getInstance().getUserByMailId(this.getOrganizationId(), sendToUsersArrays[index]);

					if (user != null)
		            {
		            	if (user.getEmailsActive().equalsIgnoreCase("Y"))
		                {
		            		isEmailActiveBySendToUser = true;
		            		break;
		                }
		            } else {
		            	isEmailActiveBySendToUser = true;
		            }
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				Log.error(this, "Error get SenTo Users " + e.getMessage());
				isEmailActiveBySendToUser = false;
			}

		}
		return isEmailActiveBySendToUser;
	}
}
