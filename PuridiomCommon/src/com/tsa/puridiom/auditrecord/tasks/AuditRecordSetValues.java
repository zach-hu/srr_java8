package com.tsa.puridiom.auditrecord.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Calendar;
import java.util.Map;

public class AuditRecordSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			AuditRecord auditRecord = (AuditRecord) incomingRequest.get("auditRecord");
			if (auditRecord == null){		auditRecord = new AuditRecord();		}

			if (incomingRequest.containsKey("AuditRecord_ic"))
			{
				String icTextString = (String) incomingRequest.get("AuditRecord_ic");
				if (Utility.isEmpty(icTextString)){		icTextString = "0";		}
				BigDecimal ic = new BigDecimal ( icTextString );
				auditRecord.setIc(ic);
			}
			if (incomingRequest.containsKey("AuditRecord_transaction"))
			{
				String transactionString = (String ) incomingRequest.get("AuditRecord_transaction");
				if (Utility.isEmpty(transactionString)){		transactionString = "0";		}
				BigDecimal transaction = new BigDecimal ( transactionString );
				auditRecord.setTransaction(transaction);
			}
			if (incomingRequest.containsKey("AuditRecord_entityClass"))
			{
				String entityClass = (String ) incomingRequest.get("AuditRecord_entityClass");
				auditRecord.setEntityClass(entityClass);
			}
			if (incomingRequest.containsKey("AuditRecord_entity"))
			{
				String entity = (String ) incomingRequest.get("AuditRecord_entity");
				auditRecord.setEntity(entity);
			}
			if (incomingRequest.containsKey("AuditRecord_userId"))
			{
				String userId = (String ) incomingRequest.get("AuditRecord_userId");
				auditRecord.setUserId(userId);
			}
			Date created = Calendar.getInstance().getTime();
			auditRecord.setCreated(created);

			if (incomingRequest.containsKey("AuditRecord_ipAddress"))
			{
				String ipAddress = (String ) incomingRequest.get("AuditRecord_ipAddress");
				auditRecord.setIpAddress(ipAddress);
			}
			if (incomingRequest.containsKey("AuditRecord_mailId"))
			{
				String mailId = (String ) incomingRequest.get("AuditRecord_mailId");
				auditRecord.setMailId(mailId);
			}

			result = auditRecord;
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