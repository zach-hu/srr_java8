package com.tsa.puridiom.auditrecord.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.AuditRecord;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class AuditRecordAddList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List auditRecordList = (List)incomingRequest.get("auditRecordList");

			for(int i = 0; i < auditRecordList.size(); i++)
			{
				AuditRecord auditRecord = (AuditRecord)auditRecordList.get(i);
				Map updateMap = new HashMap();
				updateMap.put("organizationId", incomingRequest.get("organizationId"));
				updateMap.put("auditRecord", auditRecord);
				updateMap.put("dbsession", incomingRequest.get("dbsession"));
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("auditrecord-add-row.xml");

				process.executeProcess(updateMap);
			}


			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}