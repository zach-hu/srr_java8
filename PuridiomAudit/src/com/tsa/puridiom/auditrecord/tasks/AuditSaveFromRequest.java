package com.tsa.puridiom.auditrecord.tasks;

/*
 * Saves audit Records.
 */
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class AuditSaveFromRequest extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
/* commented out for security, jrc 20120429		
 * String auditTransactionId = (String)incomingRequest.get("auditTransactionId");
			JSONObject beforeJson = (JSONObject)incomingRequest.get("beforeJson");
			JSONObject afterJson = (JSONObject)incomingRequest.get("afterJson");
			List auditList = new ArrayList();

			String fields[] =JSONObject.getNames(beforeJson);
			if(fields == null)
			{
				result = auditList;
				this.status = Status.SUCCEEDED;
				return result;
			}

			for (int i = 0; i < fields.length; i++)
			{
				AuditRecord auditRecord = new AuditRecord();
				UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
				auditRecord.setIc(new BigDecimal(ukg.getUniqueKey().toString()));
				auditRecord.setTransaction(new BigDecimal(auditTransactionId));
				auditRecord.setEntity1(incomingRequest.get("auditIc").toString());
				auditRecord.setEntity2(beforeJson.getString(fields[i]));
				auditRecord.setEntity3(afterJson.getString(fields[i]));
				auditRecord.setEntity4(fields[i]);

				if (incomingRequest.containsKey("auditTables"))
				{
					String entityClass = (String ) incomingRequest.get("auditTables");
					auditRecord.setEntityClass(entityClass);
				}
				if (incomingRequest.containsKey("userId"))
				{
					String userId = (String ) incomingRequest.get("userId");
					auditRecord.setUserId(userId);
				}
				Date created = Calendar.getInstance().getTime();
				auditRecord.setCreated(created);

				if (incomingRequest.containsKey("ipAddress"))
				{
					String ipAddress = (String ) incomingRequest.get("ipAddress");
					auditRecord.setIpAddress(ipAddress);
				}
				if (incomingRequest.containsKey("AuditRecord_mailId"))
				{
					String mailId = (String ) incomingRequest.get("AuditRecord_mailId");
					auditRecord.setMailId(mailId);
				}

				auditList.add(auditRecord);
			}


			result = auditList; */
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}