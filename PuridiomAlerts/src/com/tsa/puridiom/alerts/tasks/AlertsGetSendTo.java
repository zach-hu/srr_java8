package com.tsa.puridiom.alerts.tasks;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.alerts.Alert;
import com.tsa.puridiom.alerts.AlertManager;
import com.tsa.puridiom.alerts.AlertSendTo;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.RuleHelper;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class AlertsGetSendTo extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		String organizationId = (String)incomingRequest.get("organizationId");

		try
		{
			String alertName = (String)incomingRequest.get("alertname");
			Alert alert = AlertManager.getInstance().getAlert(organizationId, alertName);
			List sendToList = alert.getSenTo();
			StringBuffer sendToEmails = new StringBuffer();
			for (Iterator iter = sendToList.iterator(); iter.hasNext();)
			{
				AlertSendTo sendTo = (AlertSendTo) iter.next();
				if(sendTo.isEnabled())
				{
					String sendToType = sendTo.getType();
					if(sendToType.equalsIgnoreCase(AlertSendTo.MANUAL))
					{
						if(sendToEmails.length() > 0){ sendToEmails.append(";");	}
						sendToEmails.append(sendTo.getEmailId());
					}
					else if(sendToType.equalsIgnoreCase(AlertSendTo.RULE))
					{
						RuleHelper rule = new RuleHelper(sendTo.getRuleName());
						if(rule.executeRule(incomingRequest))
						{
							sendToEmails = this.getRuleUserEmail(sendTo, organizationId, sendToEmails, incomingRequest);
						}
					}
					else
					{
						sendToEmails = this.getUserEmail(sendToType, sendToEmails, incomingRequest, organizationId);
					}
				}
			}
			ret = sendToEmails.toString();

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("AlertGetProcessFromType failed!" + e.getMessage(), e);
		}
		return ret;
	}

	public StringBuffer getUserEmail(String type, StringBuffer sendToEmails, Map incomingRequest, String organizationId)
	{
		//String type = sendTo.getType();
		Object value = "";
		int _index = type.indexOf("_");
		Object obj = incomingRequest.get(type.substring(0, _index));
		if(obj != null)
		{
			String fieldName = type.substring(_index + 1);
			fieldName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			value = this.getValueFromMethod(obj, fieldName);
			if(value != null)
			{
				String userCode = (String)value;
				String mailId = "";
				try
				{
					mailId = UserManager.getInstance().getUser(organizationId, userCode).getMailId();
				}
				catch (Exception e)
				{
					// TODO getDefault EMail?
					e.printStackTrace();
					mailId = "";
				}
				if(!Utility.isEmpty(mailId))
				{
					sendToEmails = this.emailSB(sendToEmails, mailId);
				}
			}
		}
		return sendToEmails;
	}

	public StringBuffer emailSB(StringBuffer sendToEmails, String mailId)
	{
		if(sendToEmails.length() > 0)
		{
			sendToEmails.append(";" + mailId);
		}
		else
		{
			sendToEmails.append(mailId);
		}

		return sendToEmails;
	}

	public Object getValueFromMethod(Object obj, String methodName)
    {
        Object result = null;
        try
        {
            if (obj != null)
            {
                Class c = obj.getClass();
                Method method = c.getMethod(methodName, null);
                result = method.invoke(obj, null);
                Log.debug(this, "getValueFromMethod- " + methodName +" -" + result);
            }
        }
        catch(Exception exception)
        {
            Log.error(exception, "getValueFromMethod");
            exception.printStackTrace();
        }
        return result;
    }

	public StringBuffer getRuleUserEmail(AlertSendTo sendTo, String organizationId, StringBuffer sendToEmails, Map incomingRequest)
	{
		String mailId = "";
		if(sendTo.getRuleUserType().equalsIgnoreCase("U"))
		{
			try
			{
				 mailId = UserManager.getInstance().getUser(organizationId, sendTo.getRuleUserId()).getMailId();
			}
			catch (Exception e)
			{
				mailId = sendTo.getRuleUserId();
				e.printStackTrace();
			}
		}
		else if(sendTo.getRuleUserType().equalsIgnoreCase("I"))
		{
			this.getUserEmail(sendTo.getRuleUserId(), sendToEmails, incomingRequest, organizationId);
		}
		else
		{
			mailId = sendTo.getRuleUserId();
		}

		return this.emailSB(sendToEmails, mailId);
	}
}
