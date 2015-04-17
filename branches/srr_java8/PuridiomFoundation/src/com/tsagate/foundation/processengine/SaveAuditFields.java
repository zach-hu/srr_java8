/*
 * Created on Nov 5, 2003
 */
package com.tsagate.foundation.processengine;

import java.util.Map;

import org.json.JSONObject;

/**
 * sets the from/to values to a after/before Json Objects
 * @author Renzo
 */
public class SaveAuditFields extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		try
		{
/* commented out to pass security, jrc 20120429
 * 				//String auditTransactionId = (String)incomingRequest.get("auditTransactionId");

				String auditFields = (String)incomingRequest.get("auditFields");
				
				//decode parentheses
				while (auditFields.indexOf("''") > -1) {
					int i = auditFields.indexOf("''");
					auditFields = auditFields.substring(0, i) + auditFields.substring(i + 1);
				}
				
				//remove | (pipe) characters
				while (auditFields.indexOf("|") > -1) {
					int i = auditFields.indexOf("|");
					auditFields = auditFields.substring(0, 1) + auditFields.substring(i+1);
				}
				
				//String auditTransactionId = (String)incomingRequest.get("auditTransactionId");
				String auditedFields = (String)incomingRequest.get("auditedFields");
				
				//decode parentheses
				while (auditedFields.indexOf("''") > -1) {
					int i = auditedFields.indexOf("''");
					auditedFields = auditedFields.substring(0, i) + auditedFields.substring(i + 1);
				}
				
				//remove | (pipe) characters
				while (auditedFields.indexOf("|") > -1) {
					int i = auditedFields.indexOf("|");
					auditedFields = auditedFields.substring(0, 1) + auditedFields.substring(i+1);
				}
				
				JSONObject auditJson = new JSONObject(auditFields);
				JSONObject auditedJson = new JSONObject(auditedFields);
				String fields[] =JSONObject.getNames(auditJson);
				JSONObject beforeJson = new JSONObject();
				JSONObject afterJson = new JSONObject();
				for (int i = 0; i < fields.length; i++)
				{
					String before = auditJson.getString(fields[i]);
					String after = auditedJson.getString(fields[i]);
					if(!before.equals(after))
					{
						beforeJson.put(fields[i], before);
						afterJson.put(fields[i], after);
					}
				}
				incomingRequest.put("auditIc", auditJson.get("ic"));
				incomingRequest.put("beforeJson", beforeJson);
				incomingRequest.put("afterJson", afterJson);*/
				this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return object;
	}
}

