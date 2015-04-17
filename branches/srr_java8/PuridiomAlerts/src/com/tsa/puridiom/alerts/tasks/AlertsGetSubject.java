package com.tsa.puridiom.alerts.tasks;

import java.lang.reflect.Method;
import java.util.Map;

import com.tsa.puridiom.alerts.Alert;
import com.tsa.puridiom.alerts.AlertManager;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class AlertsGetSubject extends Task
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
			StringBuffer subject = new StringBuffer();
			if(!Utility.isEmpty(alert.getSubject()))
			{
				String sendQueueSubject = (String)incomingRequest.get("SendQueue_subject");
				if(!HiltonUtility.isEmpty(sendQueueSubject))
				{
					subject.append(sendQueueSubject);
				}
				else
				{
					subject.append(alert.getSubject());
					this.replaceMe(alert.getSubject(), incomingRequest, subject);
				}
			}
			ret = subject.toString();
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("AlertGetProcessFromType failed!" + e.getMessage(), e);
		}
		return ret;
	}

	public void replaceMe(String lineTemplate, Map incomingRequest, StringBuffer messageSB)
	{
		int index = lineTemplate.indexOf("$F{");
		int endIndex = lineTemplate.indexOf("}", index);

		while (index > -1)
		{
			String field = lineTemplate.substring(index + 3, endIndex);
			Object value = "";
			int _index = field.indexOf("_");
			Object obj = incomingRequest.get(field.substring(0, _index));
			if(obj != null)
			{
				String fieldName = field.substring(_index + 1);
				fieldName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				value = this.getValueFromMethod(obj, fieldName);
				messageSB.replace(index, endIndex + 1, value.toString());
			}
			index = lineTemplate.indexOf("${", endIndex);
			endIndex = lineTemplate.indexOf("}", index);
		}
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
}
