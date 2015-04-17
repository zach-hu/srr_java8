package com.tsa.puridiom.timer;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.emails.EmailManager;
import com.tsa.puridiom.emails.exception.EmailsException;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;

public class EmailHtmlApprovalsJob extends ScheduledJobAction
{

	public void execute()
	{
		this.getReports();
		Log.debug(this, "ReportQueueJob done");
	}

	protected void getReports()
	{
		try
		{
			this.getHtml();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getHtml()
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("sendqueue-retrieve-by-action-status.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("SendQueue_status", DocumentStatus.SENDQUEUE_NEW);
			this.setAction(incomingRequest);

			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				List emailList = (List) incomingRequest.get("alertsList");

				Log.debug(this, "[" + emailList.size() + "] Report Records were found.");
				for (Iterator iter = emailList.iterator(); iter.hasNext();)
				{
					SendQueue sq = (SendQueue) iter.next();

					String[] aSendTo = EmailManager.getEmailAddressArray(sq.getSendto());

					for (int i = 0; i < aSendTo.length; i++)
					{
						String sendTo = aSendTo[i];
						Map newIncomingRequest = new HashMap();

						newIncomingRequest.put("organizationId", this.getOrganizationId());
						newIncomingRequest.put("type", sq.getDoctype());
						newIncomingRequest.put("icHeader", sq.getDocic().toString());

						if (HiltonUtility.isEmpty(sendTo))
						{
							this.errorEmail(Status.FAILED, sq, "SendTo was empty");
						} else
						{
							newIncomingRequest.put("UserProfile_mailId", sendTo);
							newIncomingRequest.put("mid", sendTo);
							newIncomingRequest.put("fyi", sq.getAction());
							if(sq.getSubject().indexOf("Over Budget: Please Review CAR") == 0)
							{
								newIncomingRequest.put("messagetext", sq.getMessagetext());
							}
							process = processLoader.loadProcess("html-email-approvals.xml");
							try
							{
								process.executeProcess(newIncomingRequest);

								if (process.getStatus() == Status.SUCCEEDED)
								{
									String approvalTextVersion = "";

									String fileName = (String) newIncomingRequest.get("htmlName");

									boolean isEmailsActiveBySendToUser = this.checkEmailActiveBySendToUsers(sq.getSendto());

									if (isEmailsActiveBySendToUser)
									{
										if (this.sendEmail(sq, sendTo, new File(fileName), process.getStatus(), approvalTextVersion) == Status.SUCCEEDED)
										{
											this.updateSendQueue(process.getStatus(), newIncomingRequest, sq);
										} else
										{
											this.updateSendQueue(Status.FAILED, newIncomingRequest, sq);
										}
									}
									else
									{
										this.updateSenqQueueEmailActive(sq);
									}
								} else
								{
									this.updateSendQueue(process.getStatus(), newIncomingRequest, sq);
									this.errorEmail(Status.FAILED, null, "HTML Email failed for: Queueid:" + sq.getQueueid().toString() + ", Subject" + sq.getSubject());
								}
							} catch (Exception e)
							{
								this.updateSendQueue(Status.FAILED, newIncomingRequest, sq);
								this.errorEmail(Status.FAILED, sq, "HTML Email failed for: Queueid:" + sq.getQueueid().toString() + ", Subject" + sq.getSubject() + e.getMessage());
							}
						}
					}
				}
			}

		} catch (Exception e)
		{
			Log.error(this, "Reports could not be executed: " + e.getMessage());
			this.errorEmail(Status.FAILED, null, e.getMessage());
		}

	}

	protected void setAction(Map incomingRequest)
	{
		incomingRequest.put("SendQueue_action", SendQueue.HTML_EMAIL_ACTION);
	}

	private int sendEmail(SendQueue sq, String sendTo, File emailHtml, int status, String alternativeText)
	{
		int ret = 0;
		String subject = sq.getSubject();

		try
		{
			subject = subject.replaceAll("\\r\\n", " / ");

			if (emailHtml.getName().endsWith(".txt"))
			{
				alternativeText = HiltonUtility.getFileContents(emailHtml);
				EmailManager.getInstance().sendEmail(sq.getSendfrom(), sendTo, null, subject, alternativeText, "", this.getOrganizationId());
				Log.debug(this, "done Sending Html email");
			} else
			{
				Log.debug(this, "Sending Html email");
				if (HiltonUtility.isEmpty(alternativeText))
				{
					alternativeText = sq.getSubject();
				}
				EmailManager.getInstance().sendHtmlEmail(sq.getSendfrom(), sendTo, null, subject, alternativeText, emailHtml, this.getOrganizationId());
				Log.debug(this, "done Sending Html email");
			}

			ret = Status.SUCCEEDED;
		} catch (EmailsException e)
		{
			ret = Status.FAILED;
			sq.setStatus(DocumentStatus.SENDQUEUE_PROCESSED);
			EmailManager.getInstance().sendErrorEmail("SendQueue: " + sq.getQueueid() + "\r\n\r\n Subject: " + sq.getSubject() + " failed!\r\n" + e.getMessage(), this.getOrganizationId());
			e.printStackTrace();
		}
		return ret;
	}


}
