package com.tsa.puridiom.alerts.tasks;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.alerts.Alert;
import com.tsa.puridiom.alerts.AlertManager;
import com.tsa.puridiom.alerts.AlertMessage;
import com.tsa.puridiom.alerts.AlertMessage.MessageLine;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.DocText;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.properties.DictionaryManager;

public class AlertsGetMessage extends Task
{
	private String oid = "";
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		String organizationId = (String)incomingRequest.get("organizationId");
		this.oid = organizationId;
		try
		{
			String alertName = (String)incomingRequest.get("alertname");
			Alert alert = AlertManager.getInstance().getAlert(organizationId, alertName);
			StringBuffer message = new StringBuffer();

				AlertMessage alertMsg = alert.getMessage();
				List lines = alertMsg.getLines();
				for (Iterator iter = lines.iterator(); iter.hasNext();)
				{
					MessageLine msgLine = (MessageLine) iter.next();
					String lineTemplate = msgLine.getLine();
					StringBuffer messageSB = new StringBuffer();

					if(msgLine.isRepeat())
					{
						int size = this.getRepeatSize(msgLine.getSizeObject(), incomingRequest);
						Object objRepeat = incomingRequest.get(msgLine.getSizeObject());

						for(int i = 0; i < size; i++)
						{
							//messageSB.append(lineTemplate);
							Object tempObject = ((List) objRepeat).get(i);
							this.replaceMe(lineTemplate, incomingRequest, messageSB, tempObject);
						}
					}
					else if(msgLine.isComment())
					{
						message.append(this.getCommentText(msgLine.getCommentId()));
						message.append("\n\n");
					}
					else
					{
						//messageSB.append(lineTemplate);
						this.replaceMe(lineTemplate, incomingRequest, messageSB);
					}
					message.append(messageSB );
				}

			ret = message.toString();

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("AlertGetProcessFromType failed!" + e.getMessage(), e);
		}
		return ret;
	}

	private StringBuffer getCommentText(String commentId)
	{
		StringBuffer commentSB = new StringBuffer();
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.oid);
			PuridiomProcess process = processLoader.loadProcess("stdcomment-retrieve-by-id.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.oid);
			incomingRequest.put("StdComment_commentId", commentId);
			process.executeProcess(incomingRequest);
			if(process.getStatus() == Status.SUCCEEDED)
			{
				DocText docText = (DocText)incomingRequest.get("docText");
				if(docText != null)
				{
					commentSB.append(docText.getStdText());
				}
			}
		}
		catch (Exception e)
		{
			Log.error(this, "Comment: " + commentId + " could not be retrieved! " + e.getMessage());
		}

		return commentSB;
	}

	public void replaceMe(String lineTemplate, Map incomingRequest, StringBuffer messageSB)
	{
		this.replaceMe(lineTemplate, incomingRequest, messageSB, null);
	}
	public String replaceMe(String lineTemplate, Map incomingRequest, StringBuffer messageSB, Object repeatObject)
	{
		lineTemplate = lineTemplate.replaceAll("-tab-", "     ");
		StringBuffer tempSB = new StringBuffer();
		tempSB.setLength(0);
		tempSB.append(lineTemplate);
		tempSB = this.getFields(tempSB, incomingRequest, repeatObject);
		tempSB = this.getLabels(tempSB, incomingRequest, repeatObject);

		messageSB.append(tempSB);
		//messageSB.append("\n");

		return tempSB.toString();
	}

	public StringBuffer getLabels(StringBuffer tempSB, Map incomingRequest, Object repeatObject)
	{

		int index = tempSB.indexOf("$L{");
		int endIndex = tempSB.indexOf("}", index);

		while (index > -1)
		{
			String labelName = tempSB.substring(index + 3, endIndex);
			String defaultLabel = "";

			int defaultIndex = labelName.indexOf(",");
			if( defaultIndex > 0)
			{
				defaultLabel = labelName.substring(defaultIndex + 1).trim();
				labelName = labelName.substring(0, defaultIndex);
			}
			String label = DictionaryManager.getLabel(this.oid, labelName, defaultLabel, true);
			if(label != null)
			{
				tempSB.replace(index, endIndex + 1, label.toString());
			}
			else
			{
				tempSB.replace(index, endIndex + 1, "");
			}
			index = tempSB.indexOf("$L{");
			endIndex = tempSB.indexOf("}", index);
		}

		return tempSB;

	}


	public StringBuffer getFields(StringBuffer tempSB, Map incomingRequest, Object repeatObject)
	{
		int index = tempSB.indexOf("$F{");
		int endIndex = tempSB.indexOf("}", index);

		while (index > -1)
		{
			String field = tempSB.substring(index + 3, endIndex);
			Object value = "";
			int _index = field.indexOf("_");
			String object = field.substring(0, _index);
			Object obj = null;
			if(object.equalsIgnoreCase("repeat"))
			{
				obj = repeatObject;
			}
			else
			{
				obj = incomingRequest.get(object);
			}
			if(obj != null)
			{
				String fieldName = field.substring(_index + 1);
				String type = "";
				int typeIndex = fieldName.indexOf(",");
				if( typeIndex > 0)
				{
					type = fieldName.substring(typeIndex + 1).trim();
					fieldName = fieldName.substring(0, typeIndex);
				}

				fieldName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				value = this.getValueFromMethod(obj, fieldName, type);
				if(value != null)
				{
					tempSB.replace(index, endIndex + 1, value.toString());
				}
				else
				{
					tempSB.replace(index, endIndex + 1, "");
				}
			}
			else
			{
				tempSB.replace(index, endIndex + 1, "");
			}
			index = tempSB.indexOf("$F{");
			endIndex = tempSB.indexOf("}", index);
		}

		return tempSB;
	}

	public Object getValueFromMethod(Object obj, String methodName, String type)
    {
        Object result = null;
        try
        {
            if (obj != null)
            {
                Class c = obj.getClass();
                Method method = c.getMethod(methodName, null);
                result = method.invoke(obj, null);
                result = this.getValueDetails(methodName, result);
                if(!HiltonUtility.isEmpty(type))
                {
                	//result = this.formatValue(result, type);
                	result = HiltonUtility.getFormattedObject(result, type, this.oid);
                }
                Log.debug(this, "getValueFromMethod- " + methodName +" -" + result);
            }
        }
        catch(Exception exception)
        {
            Log.error(exception, "AlertsGetMessage.getValueFromMethod");
            exception.printStackTrace();
        }
        return result;
    }



	public Object getValueDetails(String methodName, Object value)
	{
		Object detail = null;
		if(!HiltonUtility.isEmpty(methodName))
		{
			if(this.isUser(methodName))
			{
				try
				{
					detail = UserManager.getInstance().getUser(this.oid, (String)value).getDisplayName();
				}
				catch (Exception e)
				{
					detail = null;
					Log.error(this, "An error occurred trying to abtain username: " + value + " oid: " + this.oid);
					e.printStackTrace();
				}
			}
			else if(methodName.equalsIgnoreCase("getStatus"))
			{
				detail = DocumentStatus.toString((String)value, this.oid);
			}
			else
			{
				detail = value;
			}
		}
		return detail;
	}

	public boolean isUser(String methodName)
	{
		if(methodName.equalsIgnoreCase("getBuyerCode") || methodName.equalsIgnoreCase("getOwner") || methodName.equalsIgnoreCase("getRequisitionerCode"))
			return true;
		return false;
	}
	private int getRepeatSize(String sizeObject, Map incomingRequest)
	{
		int size = 0;

		Object obj = incomingRequest.get(sizeObject);
		if (obj instanceof List)
		{
			List tempList = (List) obj;
			size = tempList.size();
		}

		return size;
	}
}
