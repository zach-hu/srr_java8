package com.tsagate.foundation.database;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class AuditLog
{
	/**
	 * @param transaction
	 * @param entity
	 * @param userId
	 * @param mailId
	 * @param ipAddress
	 * @param organizationId
	 */
	public static void logEvent(String transaction, Object entity, String userId, String mailId, String ipAddress, String organizationId)
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId) ;
			PuridiomProcess process = processLoader.loadProcess("auditrecord-add.xml") ;
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("AuditRecord_transaction", transaction);
			incomingRequest.put("AuditRecord_mailId", mailId);
			incomingRequest.put("AuditRecord_ipAddress", ipAddress);
			incomingRequest.put("AuditRecord_userId", userId);
			if (entity instanceof IEntity)
			{
				IEntity tmpEntity = (IEntity) entity;
				incomingRequest.put("AuditRecord_entity", tmpEntity .serializeMe());
			}
			String temp = entity.getClass().getName();
			temp = temp.substring(temp.lastIndexOf(".") + 1);
			incomingRequest.put("AuditRecord_entityClass", temp);

			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			incomingRequest.put("AuditRecord_ic",ukg.getUniqueKey().toString());
			process.executeProcess(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
