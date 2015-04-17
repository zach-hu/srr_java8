package com.tsa.puridiom.timer;

import java.io.File;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.emails.EmailManager;
import com.tsa.puridiom.entity.SendQueue;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;

public class EmailsJob extends ScheduledJobAction
{

	public void processAction(Object objectToProcess)
	{
		SendQueue sq = (SendQueue) objectToProcess;
		boolean unknownAttachmentType = false;

		if (HiltonUtility.isEmpty(sq.getSendto()))
		{
			Log.debug(this, "EmailsJob failed sendTo is empty for QueueId: " + sq.getQueueid());

			this.errorEmail(Status.FAILED, sq, "SendTo was empty");

		} else
		{
			try
			{
				String message = sq.getMessage();
				File attachmentFile = new File(sq.getAttachment());

				if (!HiltonUtility.isEmpty(sq.getAttachment()))
				{
					if (attachmentFile.exists())
					{
						Log.debug(this, "EmailsJob has an attachment: " + sq.getAttachment());

						if (attachmentFile.getName().indexOf(".txt") > 0)
						{
							message = HiltonUtility.getFileContents(attachmentFile);

							Log.debug(this, "EmailsJob has a TXT attachment file");

						} else
						{
							unknownAttachmentType = true;
						}
					}
				}

				boolean isEmailsActiveBySendToUser = this.checkEmailActiveBySendToUsers(sq.getSendto());

				if (isEmailsActiveBySendToUser)
				{
					if (unknownAttachmentType)
					{
						EmailManager.getInstance().sendHtmlEmail(sq.getSendfrom(), sq.getSendto(), null, sq.getSubject(), message, attachmentFile, this.getOrganizationId());
					}
					else
					{
						EmailManager.getInstance().sendEmail(sq.getSendfrom(), sq.getSendto(), null, sq.getSubject(), message, sq.getAttachment(), this.getOrganizationId());
					}

					this.updateSendQueue(Status.SUCCEEDED, null, sq);
				}
				else
				{
					this.updateSenqQueueEmailActive(sq);
				}

			} catch (Exception e)
			{
				Log.error(this, "EmailsJob failed for QueueId: " + sq.getQueueid());

				this.updateSendQueue(Status.FAILED, null, sq);

				this.errorEmail(Status.FAILED, sq, "Email failed for: Queueid:" + sq.getQueueid().toString() + ", Subject" + sq.getSubject() + e.getMessage());
			}
		}

	}

	protected void setAction(Map incomingRequest)
	{
		incomingRequest.put("SendQueue_action", SendQueue.TEXT_EMAIL_ACTION);
	}
}
