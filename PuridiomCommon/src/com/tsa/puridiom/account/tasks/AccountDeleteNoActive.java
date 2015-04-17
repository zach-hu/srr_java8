package com.tsa.puridiom.account.tasks;

import com.tsa.puridiom.entity.Account;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountDeleteNoActive extends Task	{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		List accountList = null;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("account-delete-by-id.xml");

			accountList = (List) incomingRequest.get("accountListByHeader");

			if (accountList != null && accountList.size() > 0)
			{
				for (int i = 0; i < accountList.size(); i++)
				{
					Account account = (Account) accountList.get(i);
					String status = account.getStatus();
					if (status.equalsIgnoreCase("01"))
					{
						BigDecimal icHeader = account.getComp_id().getIcHeader();
						BigDecimal icLine = account.getComp_id().getIcLine();
						BigDecimal sequence = account.getComp_id().getSequence();

						Map requestParameters = new HashMap();
						requestParameters.put("userId", incomingRequest.get("userId"));
                        requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
                        requestParameters.put("organizationId", incomingRequest.get("organizationId"));
			        	requestParameters.put("dbsession", incomingRequest.get("dbsession"));
			        	requestParameters.put("account", account);
			        	requestParameters.put("Account_icHeader", icHeader.toString());
			        	requestParameters.put("Account_icLine", icLine.toString());
			        	requestParameters.put("Account_sequence", sequence.toString());
			        	//requestParameters.put("RequisitionLine_icReqLine", icLine.toString());

						process.executeProcess(requestParameters);

						this.setStatus(process.getStatus());
						if (process.getStatus() == Status.FAILED)
						{
							break;
						}
					}


				}
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return accountList;
	}
}
